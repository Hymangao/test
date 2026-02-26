from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

db = SQLAlchemy()

class Property(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    address = db.Column(db.String(200), nullable=False)
    property_type = db.Column(db.String(50), nullable=False)  # e.g., apartment, house, office
    area_sqm = db.Column(db.Float, nullable=False)
    bedrooms = db.Column(db.Integer, nullable=False)
    bathrooms = db.Column(db.Integer, nullable=False)
    description = db.Column(db.Text)
    province = db.Column(db.String(100), nullable=False)
    city = db.Column(db.String(100), nullable=False)
    district = db.Column(db.String(100), nullable=False)
    detailed_address = db.Column(db.String(500))
    monthly_rent_to_tenant = db.Column(db.Float, nullable=False)
    status = db.Column(db.String(20), default='vacant')  # vacant or occupied
    landlord_contracts = db.relationship('LandlordContract', backref='property', lazy=True)
    tenant_contracts = db.relationship('TenantContract', backref='property', lazy=True)

class LandlordContract(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    property_id = db.Column(db.Integer, db.ForeignKey('property.id'), nullable=False)
    landlord_name = db.Column(db.String(100), nullable=False)
    start_date = db.Column(db.Date, nullable=False)
    end_date = db.Column(db.Date, nullable=False)
    monthly_rent = db.Column(db.Float, nullable=False)
    payment_method = db.Column(db.String(50), nullable=False)  # e.g., bank transfer, cash
    notes = db.Column(db.Text)
    attachments = db.Column(db.String(500))  # simulated file path
    signed = db.Column(db.Boolean, default=False)

class TenantContract(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    property_id = db.Column(db.Integer, db.ForeignKey('property.id'), nullable=False)
    tenant_name = db.Column(db.String(100), nullable=False)
    start_date = db.Column(db.Date, nullable=False)
    end_date = db.Column(db.Date, nullable=False)
    monthly_rent = db.Column(db.Float, nullable=False)
    payment_method = db.Column(db.String(50), nullable=False)  # e.g., bank transfer, cash
    notes = db.Column(db.Text)
    attachments = db.Column(db.String(500))  # simulated file path
    signed = db.Column(db.Boolean, default=False)

class UserAuth(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    phone = db.Column(db.String(20), nullable=False)
    username = db.Column(db.String(50), unique=True)
    wechat_id = db.Column(db.String(100), unique=True)
    id_number = db.Column(db.String(20), nullable=False, unique=True)
    email = db.Column(db.String(120))
    address = db.Column(db.String(200))
    emergency_contact_name = db.Column(db.String(100))
    emergency_contact_phone = db.Column(db.String(20))
    notes = db.Column(db.Text)
    date_of_birth = db.Column(db.Date)
    auth_code = db.Column(db.String(10), nullable=False, unique=True)
    expiry_date = db.Column(db.Date, nullable=False)
    is_active = db.Column(db.Boolean, default=True)
    is_admin = db.Column(db.Boolean, default=False)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)