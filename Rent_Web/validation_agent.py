import requests
import time
import sys

def validate_app(base_url, app_name):
    print(f"Validating {app_name} at {base_url}")
    try:
        # Test root endpoint
        response = requests.get(f"{base_url}/")
        if response.status_code == 200:
            print(f"✓ {app_name} root page loads (status {response.status_code})")
        else:
            print(f"✗ {app_name} root page failed (status {response.status_code})")
            return False

        # Test reminders endpoint
        response = requests.get(f"{base_url}/reminders")
        if response.status_code == 200:
            print(f"✓ {app_name} reminders page loads (status {response.status_code})")
            if "Payment Reminders" in response.text:
                print(f"✓ {app_name} reminders content appears correct")
            else:
                print(f"⚠ {app_name} reminders content may be incomplete")
        else:
            print(f"✗ {app_name} reminders page failed (status {response.status_code})")
            return False

        # Test properties endpoint (if exists)
        response = requests.get(f"{base_url}/properties")
        if response.status_code == 200:
            print(f"✓ {app_name} properties page loads (status {response.status_code})")
        elif response.status_code == 404:
            print(f"- {app_name} properties page not available (404)")
        else:
            print(f"✗ {app_name} properties page failed (status {response.status_code})")

        return True
    except requests.exceptions.RequestException as e:
        print(f"✗ {app_name} connection failed: {e}")
        return False

def main():
    print("Validation Agent Starting...")
    print("=" * 50)

    # Wait a bit for servers to be ready
    time.sleep(2)

    apps = [
        ("http://127.0.0.1:5000", "Property Management App"),
        ("http://127.0.0.1:5001", "Property Manager")
    ]

    all_passed = True
    for base_url, app_name in apps:
        if not validate_app(base_url, app_name):
            all_passed = False
        print()

    if all_passed:
        print("🎉 All validations passed!")
        sys.exit(0)
    else:
        print("❌ Some validations failed!")
        sys.exit(1)

if __name__ == "__main__":
    main()