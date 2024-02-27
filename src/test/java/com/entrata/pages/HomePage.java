package com.entrata.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.entrata.base.TestBase;

public class HomePage extends TestBase {

	public void validateProductsTitles(String Option, String Title) throws IOException, InterruptedException {

		ak.mouseHover(Option);

		WebElement DropdownTitle = driver.findElement(
				By.xpath("//div[@class='fat-nav-grid']/div[@class='nav-group']/a[contains(text(),'" + Title + "')]"));
		ak.flash(DropdownTitle, driver);
		Assert.assertTrue(DropdownTitle.isDisplayed());

	}

	public void validateHeadersTitle(String Option, String Title) throws IOException, InterruptedException {

		ak.mouseHover(Option);

		WebElement DropdownTitle = driver.findElement(
				By.xpath("//div[@class='header-drop-nav']/a[contains(.,'"+Title+"')]"));
		ak.flash(DropdownTitle, driver);
		Assert.assertTrue(DropdownTitle.isDisplayed());

	}
	
	public void closeCookie() throws IOException, InterruptedException {
		ak.click("Cookies_XPATH");
	}
	
	public void clickOnSignIn() throws IOException, InterruptedException {
		ak.click("SignIn_XPATH");
	}
	
	public void clickOnBaseCamp() throws IOException, InterruptedException {
		ak.click("BaseCamp_XPATH");
	}
	
	public void clickOnRecidentPay(String Option) throws IOException, InterruptedException {
		
		ak.mouseHover(Option);
		
		ak.click("RecidentPay_XPATH");
		
	}
	
	public void submitEmptyForm() throws IOException, InterruptedException {
		
		ak.click("ScheduleDemoPay_XPATH");
		
		ak.click("ScheduleFinal_XPATH");
		
		
	}
	

}
