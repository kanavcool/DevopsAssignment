package com.DevopsAssignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;

public class BaseTest {
	public WebDriver driver;

	@BeforeMethod
	@Parameters("bname")
	public void setup(String bname) throws Exception {
		System.out.println("Starting setup for browser: " + bname);
		URL gridUrl = new URL("http://localhost:4444/wd/hub");

		if (bname.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");
			driver = new RemoteWebDriver(gridUrl, options);

		} else if (bname.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			driver = new RemoteWebDriver(gridUrl, options);

		} else if (bname.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			driver = new RemoteWebDriver(gridUrl, options);
		} else {
			throw new IllegalArgumentException("Browser not supported: " + bname);
		}

		driver.manage().window().setSize(new Dimension(1366, 768));
		System.out.println("✅ Browser launched: " + bname);
	}
}
