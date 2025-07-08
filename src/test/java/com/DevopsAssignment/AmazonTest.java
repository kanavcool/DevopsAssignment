package com.DevopsAssignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AmazonTest extends BaseTest {
	
	
	
	 @Test
	    public void printAmazonFooterLinks() {
	        driver.get("https://www.amazon.in/");
	        List<WebElement> footerLinks = driver.findElements(By.cssSelector("div.navFooterVerticalRow li"));
	        System.out.println("Footer Links:");
	        for (WebElement el : footerLinks) {
	            System.out.println(el.getText());
	        }

}
	 
}
