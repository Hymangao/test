# Automation Test Demo

## Overview
This project is designed to automate the testing of web applications using Python and Selenium. The automation script performs a series of actions in a web browser, captures screenshots, and generates an HTML report of the test results.

## Project Structure
```
automation-test-demo
├── src
│   ├── main.py               # Main entry point for the automation test
│   ├── utils
│   │   └── screenshot.py      # Utility functions for taking screenshots
│   ├── reports
│   │   └── testAI_demo.html   # Generated HTML report of the automation test
│   └── config
│       └── settings.py        # Configuration settings for the automation test
├── requirements.txt           # Python dependencies required for the project
└── README.md                  # Documentation for the project
```

## Setup Instructions
1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd automation-test-demo
   ```

2. **Install dependencies**:
   Make sure you have Python installed. Then, install the required packages using pip:
   ```
   pip install -r requirements.txt
   ```

3. **Configure settings**:
   Open `src/config/settings.py` and update the browser options and login credentials as needed.

## Usage
To run the automation test, execute the following command:
```
python src/main.py
```

The script will open the Edge browser, perform the specified actions, and generate a report in `src/reports/testAI_demo.html`.

## Contributing
Feel free to submit issues or pull requests if you have suggestions or improvements for the project.