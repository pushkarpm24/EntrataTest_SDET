package com.entrata.utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.entrata.base.TestBase;
import com.entrata.listeners.CustomListeners;

public class CommonActionKeywords extends TestBase {

	// public static WebElement element;
	public static JavascriptExecutor je;
	public static String parentwindow;

	
	public void click(String locator) throws IOException, InterruptedException {

		je = (JavascriptExecutor) driver;

		try {

			if (locator.endsWith("_CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();

			} else if (locator.endsWith("_XPATH")) {
				ExWait(locator);

				element = driver.findElement(By.xpath(OR.getProperty(locator)));
				je.executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();

			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).click();

			}

			log.info("  Clicked Successfully On_  " + locator);
			CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " + locator);

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			CustomListeners.testReport.get().log(Status.FAIL, "Clicking on : " + locator);
			assertion.fail("***********Not Able To Click************");
			// assertion.assertAll();
		}
	}


	public void input(String locator, String value) {

		try {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);

			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);

			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}

			CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + value);
			log.info("Sent value as: " + value + "    In field: " + locator);

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			CustomListeners.testReport.get().log(Status.FAIL, "Typing in : " + locator + " entered value as " + value);
			assertion.fail("***********Not Able To SendKey************");
			// assertion.assertAll();
		}
	}

	// To handle mouse hover actions
	public void mouseHoverAndClick(String value) throws IOException, InterruptedException {

		try {
			element = driver.findElement(By.xpath("//*[normalize-space()='" + value + "']"));

			Actions a = new Actions(driver);
			// a.moveToElement(driver.findElement(By.xpath(OR.getProperty(locator)))).perform();

			ExWait(element);
			Thread.sleep(1000);
			a.moveToElement(element).click().build().perform();

			CustomListeners.testReport.get().log(Status.INFO, "Mouse Hover in : " + value);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			CustomListeners.testReport.get().log(Status.FAIL, "Failed to Mouse Hover in : " + value);
			assertion.fail("***********Not Able To Hover And Click************");
		}
	}

	// To handle mouse hover actions
	public void mouseHover(String locator) throws IOException, InterruptedException {
		Actions a = new Actions(driver);
		ExWait(locator);
		element = driver.findElement(By.xpath(OR.getProperty(locator)));
		a.moveToElement(element).perform();
	}

	public void select(String locator, String value) {

		je = (JavascriptExecutor) driver;

		try {
			if (locator.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				element = driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				element = driver.findElement(By.id(OR.getProperty(locator)));
			}

			je.executeScript("arguments[0].scrollIntoView(true);", element);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			Thread.sleep(1000);

			CustomListeners.testReport.get().log(Status.INFO,
					"Selecting from dropdown : " + locator + " value as " + value);

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			CustomListeners.testReport.get().log(Status.FAIL,
					"Selecting from dropdown : " + locator + " value as " + value);
			assertion.fail("***********Not Able To Select************");
			// assertion.assertAll();
		}

	}


	public void BorderHighlight(String locator) {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			JavascriptExecutor js = (JavascriptExecutor) augmentedDriver;
			js.executeScript("arguments[0].setAttribute('style','border: solid 3px red');", OR.getProperty(locator));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tab(String locator) {
		try {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(Keys.TAB);
		} catch (Exception e) {

			log.error("Unable to tab " + locator + " " + e.getMessage());
			e.printStackTrace();
			e.getMessage();
			assertion.fail("***********Not Able To Tab************");
			// assertion.assertAll();
		}
	}

	public void Clear(String locator) throws InterruptedException {

		driver.findElement(By.xpath(OR.getProperty(locator))).clear();
		Thread.sleep(3000);

	}


	public void ENTER(String locator) {

		try {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			assertion.fail("***********Not Able To Enter************");
			// assertion.assertAll();

		}
	}


	public void ExWait(String locator) throws IOException, InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(locator))));

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			assertion.fail("***********Not Able To Wait************");
			// assertion.assertAll();

		}

	}

	public void ExWait(WebElement element) throws IOException, InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			assertion.fail("***********Not Able To ExWait************");
			// assertion.assertAll();
		}

	}


	public String generateRanNum() {
		String Num = RandomStringUtils.randomNumeric(6);
		return (Num);
	}

	public String generateBorrowerName() {
		String UserName = RandomStringUtils.randomAlphabetic(8);
		return (UserName);
	}

	public void flash(WebElement element, WebDriver driver) throws IOException, InterruptedException {

		je = (JavascriptExecutor) driver;

		// element = driver.findElement(By.xpath(OR.getProperty(locator)));

		ExWait(element);

		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 2; i++) {
			changeColor("rgb(255,13,13)", element, driver);
			changeColor(bgcolor, element, driver);
		}

	}

	public void flash(String locator, WebDriver driver) throws IOException, InterruptedException {

		je = (JavascriptExecutor) driver;

		element = driver.findElement(By.xpath(OR.getProperty(locator)));
		ExWait(element);

		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 2; i++) {
			changeColor("rgb(255,13,13)", element, driver);
			changeColor(bgcolor, element, driver);
		}

	}

	public void changeColor(String color, WebElement element, WebDriver driver) {
		je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public String EXTRCT(String object) {

		try {
			
			ExWait(object);
			element = driver.findElement(By.xpath(OR.getProperty(object)));

			flash(element, driver);
			String Result = element.getText();
			log.info("Successfully extracted value ");
			System.out.println("Lead Extracted");
			CustomListeners.testReport.get().log(Status.INFO, "****Sucessfully extracted value**** " + object);

			return Result;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			assertion.fail("***********Not Able To EXTRCT************");
			// assertion.assertAll();
			CustomListeners.testReport.get().log(Status.FAIL, "****Fail to extract value**** " + object);
			return "";
		}

	}
	
	public void STOREWINDOWID() {

		try {
			parentwindow = driver.getWindowHandle();
			log.info("Parent window is stored as " + parentwindow);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void SWITCHWINDOW() {
		try {

			for (String winhandle : driver.getWindowHandles()) {
				driver.switchTo().window(winhandle);
				driver.manage().window().maximize();
				log.info("Swiched to window name" + driver.getTitle() + " with id " + winhandle);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void SWICHTOPARENTWIN() {

		try {
			driver.switchTo().window(parentwindow);
			log.info("Swiched to parent window " + driver.getTitle() + " with ID " + parentwindow);
			System.out.println("Swiched to parent window " + driver.getTitle() + " with ID " + parentwindow);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	

}