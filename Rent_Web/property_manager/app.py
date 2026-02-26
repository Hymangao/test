from flask import Flask, render_template, request, redirect, url_for, flash, session, g
from flask_sqlalchemy import SQLAlchemy
from flask_babel import Babel, gettext as _, lazy_gettext as _l
from models import db, Property, LandlordContract, TenantContract, UserAuth
from forms import PropertyForm, LandlordContractForm, TenantContractForm, UserAuthForm, LoginForm, UserManageForm
from datetime import datetime, timedelta
import os
import secrets
import string
from sqlalchemy import or_

app = Flask(__name__)
app.config['SECRET_KEY'] = 'your-secret-key-here'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///property_manager.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['WTF_CSRF_ENABLED'] = False

# Babel configuration
app.config['BABEL_DEFAULT_LOCALE'] = 'zh'
app.config['BABEL_SUPPORTED_LOCALES'] = ['zh', 'en']
app.config['BABEL_DEFAULT_TIMEZONE'] = 'UTC'

babel = Babel()

db.init_app(app)

def ensure_user_auth_schema():
    inspector = db.inspect(db.engine)
    if 'user_auth' not in inspector.get_table_names():
        return
    columns = {col['name'] for col in inspector.get_columns('user_auth')}
    if 'username' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN username VARCHAR(50)'))
    if 'wechat_id' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN wechat_id VARCHAR(100)'))
    if 'is_active' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN is_active BOOLEAN DEFAULT 1'))
        db.session.execute(db.text('UPDATE user_auth SET is_active = 1 WHERE is_active IS NULL'))
    if 'email' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN email VARCHAR(120)'))
    if 'address' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN address VARCHAR(200)'))
    if 'emergency_contact_name' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN emergency_contact_name VARCHAR(100)'))
    if 'emergency_contact_phone' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN emergency_contact_phone VARCHAR(20)'))
    if 'notes' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN notes TEXT'))
    if 'date_of_birth' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN date_of_birth DATE'))
    if 'is_admin' not in columns:
        db.session.execute(db.text('ALTER TABLE user_auth ADD COLUMN is_admin BOOLEAN DEFAULT 0'))
        db.session.execute(db.text('UPDATE user_auth SET is_admin = 0 WHERE is_admin IS NULL'))
    db.session.commit()

def get_locale():
    # Try to get locale from session, then from request, default to Chinese
    locale = session.get('locale')
    if locale not in app.config['BABEL_SUPPORTED_LOCALES']:
        locale = request.accept_languages.best_match(app.config['BABEL_SUPPORTED_LOCALES'])
    return locale or 'zh'

babel.init_app(app, locale_selector=get_locale)


@app.before_request
def load_current_user():
    g.user = None
    if 'auth_code' in session:
        g.user = UserAuth.query.filter_by(auth_code=session['auth_code']).first()


@app.context_processor
def inject_locale():
    return dict(get_locale=get_locale)


@app.route('/set_language/<lang>')
def set_language(lang):
    if lang in app.config['BABEL_SUPPORTED_LOCALES']:
        session['locale'] = lang
    return redirect(request.referrer or url_for('index'))

# Authorization check
def require_auth():
    if 'auth_code' not in session:
        return redirect(url_for('login'))
    auth = UserAuth.query.filter_by(auth_code=session['auth_code']).first()
    if not auth or auth.expiry_date < datetime.now().date() or not auth.is_active:
        session.pop('auth_code', None)
        return redirect(url_for('login'))
    return None

def require_admin():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    auth = UserAuth.query.filter_by(auth_code=session['auth_code']).first()
    if not auth or not auth.is_admin:
        flash(_('Admin access required.'), 'danger')
        return redirect(url_for('index'))
    return None

def build_property_address(province, city, district, detailed_address):
    parts = [province, city, district, detailed_address]
    return ' '.join(part for part in parts if part)

# Routes
@app.route('/')
def index():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    properties = Property.query.all()
    return render_template('index.html', properties=properties)

@app.route('/add_property', methods=['GET', 'POST'])
def add_property():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    form = PropertyForm()
    if form.validate_on_submit():
        address = build_property_address(
            form.province.data,
            form.city.data,
            form.district.data,
            form.detailed_address.data
        )
        property = Property(
            address=address,
            property_type=form.property_type.data,
            area_sqm=form.area_sqm.data,
            bedrooms=form.bedrooms.data,
            bathrooms=form.bathrooms.data,
            description=form.description.data,
            province=form.province.data,
            city=form.city.data,
            district=form.district.data,
            detailed_address=form.detailed_address.data,
            monthly_rent_to_tenant=form.monthly_rent_to_tenant.data,
            status=form.status.data
        )
        try:
            db.session.add(property)
            db.session.commit()
            flash(_('Property added successfully!'), 'success')
            return redirect(url_for('index'))
        except Exception:
            db.session.rollback()
            flash(_('Failed to save property. Please check and try again.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    return render_template('property_form.html', form=form, title='Add Property')

@app.route('/edit_property/<int:id>', methods=['GET', 'POST'])
def edit_property(id):
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    property = Property.query.get_or_404(id)
    form = PropertyForm(obj=property)
    if form.validate_on_submit():
        property.address = build_property_address(
            form.province.data,
            form.city.data,
            form.district.data,
            form.detailed_address.data
        )
        property.property_type = form.property_type.data
        property.area_sqm = form.area_sqm.data
        property.bedrooms = form.bedrooms.data
        property.bathrooms = form.bathrooms.data
        property.description = form.description.data
        property.province = form.province.data
        property.city = form.city.data
        property.district = form.district.data
        property.detailed_address = form.detailed_address.data
        property.monthly_rent_to_tenant = form.monthly_rent_to_tenant.data
        property.status = form.status.data
        try:
            db.session.commit()
            flash(_('Property updated successfully!'), 'success')
            return redirect(url_for('index'))
        except Exception:
            db.session.rollback()
            flash(_('Failed to update property. Please check and try again.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    return render_template('property_form.html', form=form, title='Edit Property')

@app.route('/delete_property/<int:id>')
def delete_property(id):
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    property = Property.query.get_or_404(id)
    try:
        db.session.delete(property)
        db.session.commit()
        flash(_('Property deleted successfully!'), 'success')
    except Exception:
        db.session.rollback()
        flash(_('Failed to delete property. Please try again.'), 'danger')
    return redirect(url_for('index'))

@app.route('/add_landlord_contract', methods=['GET', 'POST'])
def add_landlord_contract():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    form = LandlordContractForm()
    form.property_id.choices = [(p.id, f"{p.detailed_address} ({p.city})") for p in Property.query.all()]
    if form.validate_on_submit():
        contract = LandlordContract(
            property_id=form.property_id.data,
            landlord_name=form.landlord_name.data,
            start_date=form.start_date.data,
            end_date=form.end_date.data,
            monthly_rent=form.monthly_rent.data,
            payment_method=form.payment_method.data,
            notes=form.notes.data,
            attachments=form.attachments.data,
            signed=form.signed.data
        )
        try:
            db.session.add(contract)
            db.session.commit()
            flash(_('Landlord contract added successfully!'), 'success')
            return redirect(url_for('index'))
        except Exception:
            db.session.rollback()
            flash(_('Failed to save landlord contract. Please check and try again.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    return render_template('contract_form.html', form=form, title='Add Landlord Contract')

@app.route('/add_tenant_contract', methods=['GET', 'POST'])
def add_tenant_contract():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    form = TenantContractForm()
    form.property_id.choices = [(p.id, f"{p.detailed_address} ({p.city})") for p in Property.query.all()]
    if form.validate_on_submit():
        contract = TenantContract(
            property_id=form.property_id.data,
            tenant_name=form.tenant_name.data,
            start_date=form.start_date.data,
            end_date=form.end_date.data,
            monthly_rent=form.monthly_rent.data,
            payment_method=form.payment_method.data,
            notes=form.notes.data,
            attachments=form.attachments.data,
            signed=form.signed.data
        )
        try:
            db.session.add(contract)
            db.session.commit()
            flash(_('Tenant contract added successfully!'), 'success')
            return redirect(url_for('index'))
        except Exception:
            db.session.rollback()
            flash(_('Failed to save tenant contract. Please check and try again.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    return render_template('contract_form.html', form=form, title='Add Tenant Contract')

@app.route('/dashboard')
def dashboard():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    # Calculate monthly income from active tenant contracts
    active_tenant_contracts = TenantContract.query.filter(
        TenantContract.start_date <= datetime.now().date(),
        TenantContract.end_date >= datetime.now().date()
    ).all()
    monthly_income = sum(contract.monthly_rent for contract in active_tenant_contracts)

    # Calculate monthly expenses from active landlord contracts
    active_landlord_contracts = LandlordContract.query.filter(
        LandlordContract.start_date <= datetime.now().date(),
        LandlordContract.end_date >= datetime.now().date()
    ).all()
    monthly_expenses = sum(contract.monthly_rent for contract in active_landlord_contracts)

    net_profit = monthly_income - monthly_expenses

    return render_template('dashboard.html', 
                         monthly_income=monthly_income, 
                         monthly_expenses=monthly_expenses, 
                         net_profit=net_profit)

@app.route('/reminders')
def reminders():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response
    today = datetime.now().date()
    upcoming_days = 7

    # Upcoming tenant rent collections
    upcoming_tenant_payments = TenantContract.query.filter(
        TenantContract.end_date >= today,
        TenantContract.end_date <= today + timedelta(days=upcoming_days)
    ).all()

    # Upcoming landlord rent payments
    upcoming_landlord_payments = LandlordContract.query.filter(
        LandlordContract.end_date >= today,
        LandlordContract.end_date <= today + timedelta(days=upcoming_days)
    ).all()

    # Render reminders template with the upcoming payments
    return render_template('reminders.html', tenant_payments=upcoming_tenant_payments, landlord_payments=upcoming_landlord_payments)

@app.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        filters = {'auth_code': form.auth_code.data}
        if form.login_type.data == 'phone':
            filters['phone'] = form.identifier.data
        elif form.login_type.data == 'username':
            filters['username'] = form.identifier.data
        else:
            filters['wechat_id'] = form.identifier.data
        auth = UserAuth.query.filter_by(**filters).first()
        if auth and auth.expiry_date >= datetime.now().date() and auth.is_active:
            session['auth_code'] = form.auth_code.data
            return redirect(url_for('index'))
        else:
            flash(_('Invalid or expired credentials.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    return render_template('login.html', form=form)

@app.route('/logout')
def logout():
    session.pop('auth_code', None)
    return redirect(url_for('login'))

@app.route('/admin/auth', methods=['GET', 'POST'])
def manage_auth():
    redirect_response = require_admin()
    if redirect_response:
        return redirect_response
    form = UserAuthForm()
    if form.validate_on_submit():
        if form.username.data and UserAuth.query.filter_by(username=form.username.data).first():
            flash(_('Username already exists.'), 'danger')
            return render_template('manage_auth.html', form=form, auth_codes=UserAuth.query.all())
        if form.wechat_id.data and UserAuth.query.filter_by(wechat_id=form.wechat_id.data).first():
            flash(_('WeChat ID already exists.'), 'danger')
            return render_template('manage_auth.html', form=form, auth_codes=UserAuth.query.all())
        if UserAuth.query.filter_by(phone=form.phone.data).first():
            flash(_('Phone already exists.'), 'danger')
            return render_template('manage_auth.html', form=form, auth_codes=UserAuth.query.all())
        # Generate auth code
        auth_code = ''.join(secrets.choice(string.ascii_uppercase + string.digits) for _ in range(10))
        expiry_date = datetime.now().date() + timedelta(days=365)  # 1 year expiry
        user_auth = UserAuth(
            name=form.name.data,
            phone=form.phone.data,
            username=form.username.data or None,
            wechat_id=form.wechat_id.data or None,
            id_number=form.id_number.data,
            email=form.email.data or None,
            address=form.address.data or None,
            emergency_contact_name=form.emergency_contact_name.data or None,
            emergency_contact_phone=form.emergency_contact_phone.data or None,
            notes=form.notes.data or None,
            date_of_birth=form.date_of_birth.data or None,
            auth_code=auth_code,
            expiry_date=expiry_date
        )
        if UserAuth.query.filter_by(is_admin=True).count() == 0:
            user_auth.is_admin = True
        else:
            user_auth.is_admin = form.is_admin.data
        try:
            db.session.add(user_auth)
            db.session.commit()
            flash(_(f'Authorization code generated: {auth_code} (Expires: {expiry_date})'), 'success')
            return redirect(url_for('manage_auth'))
        except Exception:
            db.session.rollback()
            flash(_('Failed to generate authorization code. Please check and try again.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    auth_codes = UserAuth.query.all()
    return render_template('manage_auth.html', form=form, auth_codes=auth_codes)

@app.route('/admin/auth/delete/<int:id>')
def delete_auth(id):
    redirect_response = require_admin()
    if redirect_response:
        return redirect_response
    auth = UserAuth.query.get_or_404(id)
    try:
        db.session.delete(auth)
        db.session.commit()
        flash(_('Authorization code deleted.'), 'success')
    except Exception:
        db.session.rollback()
        flash(_('Failed to delete authorization code.'), 'danger')
    return redirect(url_for('manage_auth'))

@app.route('/admin/users')
def manage_users():
    redirect_response = require_admin()
    if redirect_response:
        return redirect_response
    users = UserAuth.query.order_by(UserAuth.id.desc()).all()
    return render_template('user_management.html', users=users)

@app.route('/admin/users/<int:id>', methods=['GET', 'POST'])
def edit_user(id):
    redirect_response = require_admin()
    if redirect_response:
        return redirect_response
    user = UserAuth.query.get_or_404(id)
    form = UserManageForm(obj=user)
    if form.validate_on_submit():
        if form.username.data and UserAuth.query.filter(UserAuth.username == form.username.data, UserAuth.id != user.id).first():
            flash(_('Username already exists.'), 'danger')
            return render_template('user_edit.html', form=form, user=user)
        if form.wechat_id.data and UserAuth.query.filter(UserAuth.wechat_id == form.wechat_id.data, UserAuth.id != user.id).first():
            flash(_('WeChat ID already exists.'), 'danger')
            return render_template('user_edit.html', form=form, user=user)
        if UserAuth.query.filter(UserAuth.phone == form.phone.data, UserAuth.id != user.id).first():
            flash(_('Phone already exists.'), 'danger')
            return render_template('user_edit.html', form=form, user=user)
        user.name = form.name.data
        user.phone = form.phone.data
        user.username = form.username.data or None
        user.wechat_id = form.wechat_id.data or None
        user.id_number = form.id_number.data
        user.email = form.email.data or None
        user.address = form.address.data or None
        user.emergency_contact_name = form.emergency_contact_name.data or None
        user.emergency_contact_phone = form.emergency_contact_phone.data or None
        user.notes = form.notes.data or None
        user.date_of_birth = form.date_of_birth.data or None
        user.is_admin = form.is_admin.data
        user.expiry_date = form.expiry_date.data
        user.is_active = form.is_active.data
        try:
            db.session.commit()
            flash(_('User updated successfully.'), 'success')
            return redirect(url_for('manage_users'))
        except Exception:
            db.session.rollback()
            flash(_('Failed to update user.'), 'danger')
    elif request.method == 'POST':
        flash(_('Please check the form and try again.'), 'warning')
    return render_template('user_edit.html', form=form, user=user)

@app.route('/admin/users/<int:id>/toggle')
def toggle_user(id):
    redirect_response = require_admin()
    if redirect_response:
        return redirect_response
    user = UserAuth.query.get_or_404(id)
    user.is_active = not user.is_active
    try:
        db.session.commit()
        flash(_('User status updated.'), 'success')
    except Exception:
        db.session.rollback()
        flash(_('Failed to update user status.'), 'danger')
    return redirect(url_for('manage_users'))

@app.route('/admin/users/<int:id>/delete')
def delete_user(id):
    redirect_response = require_admin()
    if redirect_response:
        return redirect_response
    user = UserAuth.query.get_or_404(id)
    try:
        db.session.delete(user)
        db.session.commit()
        flash(_('User deleted.'), 'success')
    except Exception:
        db.session.rollback()
        flash(_('Failed to delete user.'), 'danger')
    return redirect(url_for('manage_users'))

@app.route('/reports')
def reports():
    redirect_response = require_auth()
    if redirect_response:
        return redirect_response

    keyword = (request.args.get('keyword') or '').strip()
    status = request.args.get('status') or 'all'
    province = request.args.get('province') or 'all'
    city = request.args.get('city') or 'all'
    contract_type = request.args.get('contract_type') or 'all'
    signed = request.args.get('signed') or 'all'

    properties_query = Property.query
    if status != 'all':
        properties_query = properties_query.filter(Property.status == status)
    if province != 'all':
        properties_query = properties_query.filter(Property.province == province)
    if city != 'all':
        properties_query = properties_query.filter(Property.city == city)
    if keyword:
        like_value = f"%{keyword}%"
        properties_query = properties_query.filter(
            or_(
                Property.address.ilike(like_value),
                Property.detailed_address.ilike(like_value),
                Property.property_type.ilike(like_value)
            )
        )
    properties = properties_query.order_by(Property.id.desc()).all()

    landlord_contracts = []
    tenant_contracts = []
    if contract_type in ('all', 'landlord'):
        landlord_query = LandlordContract.query.join(Property)
        if keyword:
            like_value = f"%{keyword}%"
            landlord_query = landlord_query.filter(
                or_(
                    LandlordContract.landlord_name.ilike(like_value),
                    Property.address.ilike(like_value),
                    Property.detailed_address.ilike(like_value)
                )
            )
        if signed != 'all':
            landlord_query = landlord_query.filter(LandlordContract.signed == (signed == 'yes'))
        landlord_contracts = landlord_query.order_by(LandlordContract.id.desc()).all()

    if contract_type in ('all', 'tenant'):
        tenant_query = TenantContract.query.join(Property)
        if keyword:
            like_value = f"%{keyword}%"
            tenant_query = tenant_query.filter(
                or_(
                    TenantContract.tenant_name.ilike(like_value),
                    Property.address.ilike(like_value),
                    Property.detailed_address.ilike(like_value)
                )
            )
        if signed != 'all':
            tenant_query = tenant_query.filter(TenantContract.signed == (signed == 'yes'))
        tenant_contracts = tenant_query.order_by(TenantContract.id.desc()).all()

    province_options = [p[0] for p in db.session.query(Property.province).distinct().all() if p[0]]
    city_options = [c[0] for c in db.session.query(Property.city).distinct().all() if c[0]]

    return render_template(
        'reports.html',
        keyword=keyword,
        status=status,
        province=province,
        city=city,
        contract_type=contract_type,
        signed=signed,
        properties=properties,
        landlord_contracts=landlord_contracts,
        tenant_contracts=tenant_contracts,
        province_options=province_options,
        city_options=city_options
    )

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
        ensure_user_auth_schema()
    app.run(port=5001, debug=True)