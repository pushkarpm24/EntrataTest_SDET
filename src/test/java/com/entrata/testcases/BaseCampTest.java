package com.entrata.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.entrata.base.TestBase;
import com.entrata.pages.BaseCampPage;
import com.entrata.pages.HomePage;
import com.entrata.pages.SignInPage;
import com.entrata.utilities.TestUtil;

public class BaseCampTest extends TestBase {

	@Test(description = "Verify Registration Page Fields and nevagition",dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void baseCampTest(Hashtable<String, String> data) throws IOException, InterruptedException {

		if (!(TestUtil.isTestRunnable("baseCampTest", excel))) {

			throw new SkipException("Skipping the test " + "baseCampTest".toUpperCase() + "as the Run mode is NO");
		}

		setUp(data.get("URL"));

		HomePage home = new HomePage();
		BaseCampPage base = new BaseCampPage();

		home.closeCookie();
		home.clickOnBaseCamp();

		base.clickOnRegisterNow();

		base.fillPersonalInfo(data.get("FirstName"), data.get("LastName"), data.get("Company"), data.get("Title"), data.get("Email"), data.get("Mobile"));

		driver.close();
		ak.SWICHTOPARENTWIN();
		
		// Validate BaseCamp Option to verify navigation is working fine after closing registration page
		Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("BaseCamp_XPATH"))).isDisplayed());
		
	}

}
