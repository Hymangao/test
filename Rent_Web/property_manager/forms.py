from flask_wtf import FlaskForm
from flask_babel import lazy_gettext as _l
from wtforms import StringField, FloatField, DateField, BooleanField, SelectField, SubmitField, IntegerField, TextAreaField
from wtforms.validators import DataRequired, Length, NumberRange, Optional, ValidationError

# Predefined choices
PROPERTY_TYPES = [
    ('apartment', _l('Apartment')),
    ('house', _l('House')),
    ('office', _l('Office')),
    ('commercial', _l('Commercial')),
    ('land', _l('Land'))
]

PAYMENT_METHODS = [
    ('bank_transfer', _l('Bank Transfer')),
    ('cash', _l('Cash')),
    ('check', _l('Check')),
    ('credit_card', _l('Credit Card'))
]

PROVINCES = [
    ('北京市', '北京市'),
    ('上海市', '上海市'),
    ('天津市', '天津市'),
    ('重庆市', '重庆市'),
    ('广东省', '广东省'),
    ('浙江省', '浙江省'),
    ('江苏省', '江苏省'),
    ('四川省', '四川省')
]

CITIES = [
    ('北京市', '北京市'),
    ('上海市', '上海市'),
    ('天津市', '天津市'),
    ('重庆市', '重庆市'),
    ('广州市', '广州市'),
    ('深圳市', '深圳市'),
    ('杭州市', '杭州市'),
    ('南京市', '南京市'),
    ('成都市', '成都市')
]

DISTRICTS = [
    ('朝阳区', '朝阳区'),
    ('海淀区', '海淀区'),
    ('浦东新区', '浦东新区'),
    ('黄浦区', '黄浦区'),
    ('和平区', '和平区'),
    ('渝中区', '渝中区'),
    ('南山区', '南山区'),
    ('天河区', '天河区'),
    ('西湖区', '西湖区'),
    ('玄武区', '玄武区'),
    ('武侯区', '武侯区'),
    ('锦江区', '锦江区')
]

class PropertyForm(FlaskForm):
    property_type = SelectField(_l('Property Type'), choices=PROPERTY_TYPES, validators=[DataRequired()])
    area_sqm = FloatField(_l('Area (sqm)'), validators=[DataRequired(), NumberRange(min=1)])
    bedrooms = IntegerField(_l('Bedrooms'), validators=[DataRequired(), NumberRange(min=0)])
    bathrooms = IntegerField(_l('Bathrooms'), validators=[DataRequired(), NumberRange(min=0)])
    description = TextAreaField(_l('Description'), validators=[Length(max=1000)])
    province = SelectField(_l('Province'), choices=PROVINCES, validators=[DataRequired()])
    city = SelectField(_l('City'), choices=CITIES, validators=[DataRequired()])
    district = SelectField(_l('District'), choices=DISTRICTS, validators=[DataRequired()])
    detailed_address = StringField(_l('Detailed Address'), validators=[DataRequired(), Length(max=500)])
    monthly_rent_to_tenant = FloatField(_l('Monthly Rent to Tenant'), validators=[DataRequired(), NumberRange(min=0)])
    status = SelectField(_l('Status'), choices=[('vacant', _l('Vacant')), ('occupied', _l('Occupied'))])
    submit = SubmitField(_l('Save Property'))

class LandlordContractForm(FlaskForm):
    property_id = SelectField(_l('Property'), coerce=int, validators=[DataRequired()])
    landlord_name = StringField(_l('Landlord Name'), validators=[DataRequired(), Length(max=100)])
    start_date = DateField(_l('Start Date'), validators=[DataRequired()])
    end_date = DateField(_l('End Date'), validators=[DataRequired()])
    monthly_rent = FloatField(_l('Monthly Rent'), validators=[DataRequired(), NumberRange(min=0)])
    payment_method = SelectField(_l('Payment Method'), choices=PAYMENT_METHODS, validators=[DataRequired()])
    notes = TextAreaField(_l('Notes'), validators=[Length(max=1000)])
    attachments = StringField(_l('Attachments (simulated)'), validators=[Length(max=500)])
    signed = BooleanField(_l('Contract Signed'))
    submit = SubmitField(_l('Save Contract'))

class TenantContractForm(FlaskForm):
    property_id = SelectField(_l('Property'), coerce=int, validators=[DataRequired()])
    tenant_name = StringField(_l('Tenant Name'), validators=[DataRequired(), Length(max=100)])
    start_date = DateField(_l('Start Date'), validators=[DataRequired()])
    end_date = DateField(_l('End Date'), validators=[DataRequired()])
    monthly_rent = FloatField(_l('Monthly Rent'), validators=[DataRequired(), NumberRange(min=0)])
    payment_method = SelectField(_l('Payment Method'), choices=PAYMENT_METHODS, validators=[DataRequired()])
    notes = TextAreaField(_l('Notes'), validators=[Length(max=1000)])
    attachments = StringField(_l('Attachments (simulated)'), validators=[Length(max=500)])
    signed = BooleanField(_l('Contract Signed'))
    submit = SubmitField(_l('Save Contract'))

class UserAuthForm(FlaskForm):
    name = StringField(_l('Name'), validators=[DataRequired(), Length(max=100)])
    phone = StringField(_l('Phone'), validators=[DataRequired(), Length(max=20)])
    username = StringField(_l('Username'), validators=[Optional(), Length(max=50)])
    wechat_id = StringField(_l('WeChat ID'), validators=[Optional(), Length(max=100)])
    id_number = StringField(_l('ID Number'), validators=[DataRequired(), Length(max=20)])
    email = StringField(_l('Email'), validators=[Optional(), Length(max=120)])
    address = StringField(_l('Address'), validators=[Optional(), Length(max=200)])
    emergency_contact_name = StringField(_l('Emergency Contact Name'), validators=[Optional(), Length(max=100)])
    emergency_contact_phone = StringField(_l('Emergency Contact Phone'), validators=[Optional(), Length(max=20)])
    date_of_birth = DateField(_l('Date of Birth'), validators=[Optional()])
    notes = TextAreaField(_l('Notes'), validators=[Optional(), Length(max=1000)])
    is_admin = BooleanField(_l('Grant Admin Access'))
    submit = SubmitField(_l('Generate Auth Code'))

class LoginForm(FlaskForm):
    login_type = SelectField(_l('Login Method'), choices=[('phone', _l('Phone')), ('username', _l('Username')), ('wechat', _l('WeChat ID'))], validators=[DataRequired()])
    identifier = StringField(_l('Login Identifier'), validators=[DataRequired(), Length(max=100)])
    auth_code = StringField(_l('Verification Code'), validators=[DataRequired(), Length(min=10, max=10)])
    submit = SubmitField(_l('Login'))

class UserManageForm(FlaskForm):
    name = StringField(_l('Name'), validators=[DataRequired(), Length(max=100)])
    phone = StringField(_l('Phone'), validators=[DataRequired(), Length(max=20)])
    username = StringField(_l('Username'), validators=[Optional(), Length(max=50)])
    wechat_id = StringField(_l('WeChat ID'), validators=[Optional(), Length(max=100)])
    id_number = StringField(_l('ID Number'), validators=[DataRequired(), Length(max=20)])
    email = StringField(_l('Email'), validators=[Optional(), Length(max=120)])
    address = StringField(_l('Address'), validators=[Optional(), Length(max=200)])
    emergency_contact_name = StringField(_l('Emergency Contact Name'), validators=[Optional(), Length(max=100)])
    emergency_contact_phone = StringField(_l('Emergency Contact Phone'), validators=[Optional(), Length(max=20)])
    date_of_birth = DateField(_l('Date of Birth'), validators=[Optional()])
    notes = TextAreaField(_l('Notes'), validators=[Optional(), Length(max=1000)])
    expiry_date = DateField(_l('Expiry Date'), validators=[DataRequired()])
    is_active = BooleanField(_l('Active'))
    is_admin = BooleanField(_l('Admin'))
    submit = SubmitField(_l('Save User'))