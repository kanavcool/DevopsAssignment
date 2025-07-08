package com.DevopsAssignment;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	
	protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();

}
    
    
}
