package com.entrata.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.entrata.base.TestBase;
import com.entrata.pages.HomePage;
import com.entrata.utilities.TestUtil;

public class EmptyFormSubmittionTest extends TestBase{
	
	@Test(description = "Verify Empty Form Submittion Throws Error",dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void emptyFormSubmittionTest(Hashtable<String, String> data) throws IOException, InterruptedException {

		if (!(TestUtil.isTestRunnable("emptyFormSubmittionTest", excel))) {

			throw new SkipException(
					"Skipping the test " + "emptyFormSubmittionTest".toUpperCase() + "as the Run mode is NO");
		}

		setUp(data.get("URL"));

		HomePage home = new HomePage();
		
		home.closeCookie();
		home.clickOnRecidentPay("ProductsDD_XPATH");
		
		home.submitEmptyForm();
		
		// Validate Error Message After Empty Submition
		Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("ErrorMsg_XPATH"))).isDisplayed());

		

	}

}
