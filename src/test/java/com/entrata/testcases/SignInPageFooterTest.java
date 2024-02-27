package com.entrata.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.entrata.base.TestBase;
import com.entrata.pages.HomePage;
import com.entrata.pages.SignInPage;
import com.entrata.utilities.TestUtil;

public class SignInPageFooterTest extends TestBase {

	@Test(description = "Verify Sigh In Footer Links are Working Fine",dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void signInPageFooterTest(Hashtable<String, String> data) throws IOException, InterruptedException {

		if (!(TestUtil.isTestRunnable("signInPageFooterTest", excel))) {

			throw new SkipException(
					"Skipping the test " + "signInPageFooterTest".toUpperCase() + "as the Run mode is NO");
		}

		setUp(data.get("URL"));

		HomePage home = new HomePage();
		SignInPage si = new SignInPage();

		home.closeCookie();
		home.clickOnSignIn();
		si.clickONTermsOfUse();

		// Validate Terms Of Use Footer link
		String TOUTitle = ak.EXTRCT("TearmsOfUseTitle_XPATH");
		Assert.assertEquals(TOUTitle, "Terms Of Use");

		
		si.clickONPrivacyPolicy();
		
		// Validate Privacy Policy Footer link
		String PPTitle = ak.EXTRCT("PrivacyPolicyTitle_XPATH");
		Assert.assertEquals(PPTitle, "Privacy Policies");
		
		si.clickONWebAccess();

		// Validate Web Accessibility Footer link
		String WATitle = ak.EXTRCT("WebAccTitle_XPATH");
		Assert.assertEquals(WATitle, "Accessibility Statement");
		
		System.out.println("All Footer Links Validation Passed");

	}

}
