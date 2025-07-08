package com.DevopsAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AmazonTest {
	 public WebDriver driver;

	    @SuppressWarnings("deprecation")
		@Parameters({ "bname" })
	    @Test
	    public void crossBrowserTesting(String bname) throws MalformedURLException, InterruptedException {
	        System.out.println("🔁 RemoteDriver connectivity is started");

	        if (bname.equalsIgnoreCase("Chrome")) {
	            ChromeOptions option = new ChromeOptions();
	            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), option);
	            System.out.println("✅ Session created on Chrome");
	        } else if (bname.equalsIgnoreCase("Firefox")) {
	            FirefoxOptions option = new FirefoxOptions();
	            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), option);
	            System.out.println("✅ Session created on Firefox");
	        } else if (bname.equalsIgnoreCase("Edge")) {
	            EdgeOptions option = new EdgeOptions();
	            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), option);
	            System.out.println("✅ Session created on Edge");
	        }

	        System.out.println("✅ RemoteDriver connectivity is completed");

	        driver.get("https://www.amazon.in/");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // Scroll to bottom
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	        // Wait for footer
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navFooter")));

	        // Extract and print footer links in single line
	        List<WebElement> links = footer.findElements(By.tagName("a"));
	        StringBuilder allLinks = new StringBuilder("👉 ");
	        for (WebElement link : links) {
	            String text = link.getText().trim();
	            if (!text.isEmpty()) {
	                allLinks.append(text).append(" | ");
	            }
	        }

	        System.out.println("Browser: " + ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
	        System.out.println("Title: " + driver.getTitle());
	        System.out.println(allLinks.substring(0, allLinks.length() - 3)); // remove last ' | '

	        driver.quit();
	        System.out.println("🚫 Browser closed\n");
	    }
		
}
