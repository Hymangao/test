import pytest
from app import app, db

@pytest.fixture
def client():
    app.config['TESTING'] = True
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///:memory:'
    with app.app_context():
        db.create_all()
        yield app.test_client()

def test_index(client):
    response = client.get('/')
    assert response.status_code == 200
    assert b'Properties' in response.data

def test_add_property(client):
    response = client.post('/add_property', data={
        'address': '123 Test St',
        'monthly_rent_to_tenant': '1000',
        'status': 'vacant'
    }, follow_redirects=True)
    assert response.status_code == 200
    assert b'Property added successfully' in response.data

def test_dashboard(client):
    response = client.get('/dashboard')
    assert response.status_code == 200
    assert b'Financial Dashboard' in response.data

def test_reminders(client):
    response = client.get('/reminders')
    assert response.status_code == 200
    assert b'Upcoming Reminders' in response.data