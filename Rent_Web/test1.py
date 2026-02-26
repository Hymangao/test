import requests
import os

# Honeyhive API access code to log content

# Replace with your actual API key from Honeyhive dashboard
API_KEY = os.getenv("HONEYHIVE_API_KEY", "your_api_key_here")
PROJECT_NAME = "your_project_name"  # Replace with your project name
BASE_URL = "https://api.honeyhive.ai"  # Use your server URL if self-hosted

headers = {
    "Authorization": f"Bearer {API_KEY}",
    "Content-Type": "application/json"
}

def log_content_to_honeyhive(content):
    # Start a new session
    session_payload = {
        "project": PROJECT_NAME,
        "name": "content_logging_session"
    }
    session_response = requests.post(f"{BASE_URL}/session/start", json=session_payload, headers=headers)
    if session_response.status_code != 200:
        print(f"Failed to start session: {session_response.text}")
        return

    session_id = session_response.json().get("id")

    # Log the content as a model event (or tool event, depending on your needs)
    event_payload = {
        "session_id": session_id,
        "event_type": "model",  # or "tool" if it's not a model call
        "inputs": {
            "content": content
        },
        "outputs": {
            "status": "logged"
        }
    }
    event_response = requests.post(f"{BASE_URL}/events/model", json=event_payload, headers=headers)
    if event_response.status_code == 200:
        print("Content logged successfully to Honeyhive")
    else:
        print(f"Failed to log event: {event_response.text}")

# Example usage: log some content
sample_content = "This is the content to log to Honeyhive."
log_content_to_honeyhive(sample_content)