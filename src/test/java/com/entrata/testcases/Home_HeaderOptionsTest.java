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

public class Home_HeaderOptionsTest extends TestBase {

	@Test(description = "Verify Home Page Header Options",dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void home_HeaderOptionsTest(Hashtable<String, String> data) throws IOException, InterruptedException {

		if (!(TestUtil.isTestRunnable("home_HeaderOptionsTest", excel))) {

			throw new SkipException(
					"Skipping the test " + "home_HeaderOptionsTest".toUpperCase() + "as the Run mode is NO");
		}

		setUp(data.get("URL"));

		HomePage home = new HomePage();

		// Validate Products Dynamic Dropdown Titles
		home.validateProductsTitles("ProductsDD_XPATH", "Property Management");
		home.validateProductsTitles("ProductsDD_XPATH", "Marketing & Leasing");
		home.validateProductsTitles("ProductsDD_XPATH", "Accounting");
		home.validateProductsTitles("ProductsDD_XPATH", "Utilities");

		// Validate Solutions Dynamic Dropdown Titles
		home.validateHeadersTitle("SolutionsDD_XPATH", "All Solutions");

		// Validate Resources Dynamic Dropdown Titles
		home.validateHeadersTitle("ResourcesDD_XPATH", "All Resources");

		// Validate Watch Demo Button is enabled
		Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("WatchDemo_XPATH"))).isEnabled());

		// Validate Sign In Button is enabled
		Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("SignIn_XPATH"))).isEnabled());
		
		home.closeCookie();
		
		System.out.println("All Home Page Headers Validation Passed");

	}

}
