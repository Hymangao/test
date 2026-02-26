# Configuration settings for the automation test

# Browser options
BROWSER = 'edge'  # Specify the browser to use for automation

# Login credentials
USERNAME = '1234567890'  # Replace with your username
PASSWORD = '123456'  # Replace with your password

# URL for the website to test
URL = 'https://www.jd.com'  # The website to open for testing

# Screenshot settings
SCREENSHOT_DIR = './reports/screenshots/'  # Directory to save screenshots

# Report settings
REPORT_FILE = './reports/testAI_demo.html'  # Path to save the HTML report

# Timeout settings
TIMEOUT = 10  # Default timeout for waiting for elements (in seconds)

# Optional: specify a local EdgeDriver executable path to avoid downloads
EDGE_DRIVER_PATH = None