def take_screenshot(driver, filename):
    """Capture a screenshot with the active Selenium driver."""

    # Save screenshot to the provided path using the existing driver instance
    driver.save_screenshot(filename)