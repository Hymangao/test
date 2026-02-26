# Property Manager

A professional web application for property management targeting sub-landlords ("二房东") who rent properties from owners, renovate them, and sublet to tenants.

## Features

- **Property Management**: Add, edit, delete properties with comprehensive details including location, type, area, and status
- **Contract Management**: Manage landlord and tenant contracts with payment methods and notes
- **Financial Dashboard**: View monthly income, expenses, and net profit
- **Reminders System**: Get notifications for upcoming payments within 7 days
- **Authorization System**: Secure access with generated authorization codes

## Installation

### One-Command Start (Workspace)
From `Rent_Web` run:
```
powershell -ExecutionPolicy Bypass -File .\start_rent_web.ps1
```
This will install missing dependencies from `requirements.txt` and start the app.

### Option 1: Run from Source
1. Install Python 3.8+
2. Clone or download the project
3. Navigate to the project directory
4. Install dependencies:
   ```
   pip install -r requirements.txt
   ```
5. Run the application:
   ```
   python app.py
   ```
6. Access at `http://127.0.0.1:5000/`

### Option 2: Standalone Executable (Windows)
1. Download the `PropertyManager.exe` from the `dist` folder
2. Run the executable directly
3. The app will start automatically in your default browser

## Usage

1. **First Time Setup**: Access `/admin/auth` to generate authorization codes for users
2. **Login**: Use the generated authorization code to access the application
3. **Add Properties**: Start by adding your rental properties with detailed information
4. **Manage Contracts**: Create contracts with landlords and tenants
5. **Monitor Finances**: Check the dashboard for income/expense tracking
6. **Stay Updated**: Review reminders for upcoming payments

## Authorization System

- Access the admin panel at `/admin/auth`
- Enter user details (name, phone, ID number) to generate a unique 10-character authorization code
- Codes expire after 1 year
- Users must enter their code to access the application

## Technical Details

- **Backend**: Python Flask with SQLAlchemy ORM
- **Database**: SQLite (embedded, no separate installation needed)
- **Frontend**: Bootstrap 5 with custom styling and Font Awesome icons
- **Forms**: Flask-WTF with validation
- **Packaging**: PyInstaller for standalone executable

## Security Features

- Authorization code required for access
- CSRF protection disabled for simplicity (enable in production)
- Session-based authentication
- Input validation and sanitization

## Development

To modify the application:
- Edit `app.py` for backend logic
- Modify templates in `templates/` for UI changes
- Update models in `models.py` for database schema changes
- Run `python app.py` for development

## Building Standalone Executable

To create a new executable:
1. Install PyInstaller: `pip install pyinstaller`
2. Run: `pyinstaller --clean property_manager.spec`
3. Find the executable in `dist/PropertyManager.exe`

## Support

For issues or questions, please check the code comments or contact the developer.