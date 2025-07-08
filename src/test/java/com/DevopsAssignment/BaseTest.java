package com.DevopsAssignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

import java.net.URL;

public class BaseTest {

	protected WebDriver driver;

	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				capabilities.setBrowserName("chrome");
				break;
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
				capabilities.setBrowserName("firefox");
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless=new");
				capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				throw new Exception("❌ Unsupported browser: " + browser);
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			System.out.println("✅ " + browser + " browser launched successfully on Grid.");

		} catch (Exception e) {
			System.out.println("❌ Error while initializing WebDriver: " + e.getMessage());
			e.printStackTrace();
			assert false : "WebDriver setup failed.";
		}
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			System.out.println("Closing browser...");
			driver.quit();
		}
	}
}
