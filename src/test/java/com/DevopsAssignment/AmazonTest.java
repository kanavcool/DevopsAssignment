package com.DevopsAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.util.List;

public class AmazonTest extends BaseTest {

	@Test
	public void testAmazonFooterLinks() throws Exception {
		driver.get("https://www.amazon.in/");
		Thread.sleep(5000);

		// Scroll in steps to avoid tab crash
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 10; i++) {
			js.executeScript("window.scrollBy(0,500)");
			Thread.sleep(300);
		}

		WebElement footer = driver.findElement(By.id("navFooter"));
		List<WebElement> links = footer.findElements(By.tagName("a"));

		StringBuilder sb = new StringBuilder("👉 ");
		for (WebElement link : links) {
			String text = link.getText().trim();
			if (!text.isEmpty()) {
				sb.append(text).append(" | ");
			}
		}

		System.out.println("===============================");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
		System.out.println("Browser: " + ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
		System.out.println("Title: " + driver.getTitle());
		System.out.println(sb.substring(0, sb.length() - 3));
		System.out.println("===============================\n");

		driver.quit();
		System.out.println("🚫 Browser closed");
	}
}
