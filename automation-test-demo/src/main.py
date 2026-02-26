from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.edge.service import Service as EdgeService
from webdriver_manager.microsoft import EdgeChromiumDriverManager
import os
import time
from pathlib import Path
from utils.screenshot import take_screenshot
from config.settings import USERNAME, PASSWORD, EDGE_DRIVER_PATH

def main():
    # Step 1: Open Edge browser
    driver_path = os.environ.get("EDGE_DRIVER_PATH") or EDGE_DRIVER_PATH
    service = EdgeService(executable_path=driver_path) if driver_path and Path(driver_path).exists() else EdgeService(EdgeChromiumDriverManager().install())
    driver = webdriver.Edge(service=service)

    try:
        # Step 2: Navigate to JD.com
        driver.get("https://www.jd.com")
        take_screenshot(driver, "step2_jd_homepage.png")

        # Step 3: Click login and enter credentials
        login_button = driver.find_element(By.LINK_TEXT, "登录")
        login_button.click()
        time.sleep(2)  # Wait for the login form to appear
        take_screenshot(driver, "step3_login_form.png")

        username_input = driver.find_element(By.NAME, "username")
        password_input = driver.find_element(By.NAME, "password")

        username_input.send_keys(USERNAME)
        password_input.send_keys(PASSWORD)
        take_screenshot(driver, "step3_credentials_entered.png")

        # Step 4: Click login button
        login_submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
        login_submit_button.click()
        time.sleep(5)  # Wait for login to complete
        take_screenshot(driver, "step4_logged_in.png")

        # Step 5: Search for "手机"
        search_box = driver.find_element(By.NAME, "keyword")
        search_box.send_keys("手机" + Keys.RETURN)
        time.sleep(5)  # Wait for search results to load
        take_screenshot(driver, "step5_search_results.png")

    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        driver.quit()

if __name__ == "__main__":
    main()