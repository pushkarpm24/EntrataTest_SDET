package com.entrata.pages;

import java.io.IOException;

import com.entrata.base.TestBase;

public class SignInPage extends TestBase {

	public void clickONTermsOfUse() throws IOException, InterruptedException {

		//ak.click("TermsOfUse_XPATH");
		ak.mouseHoverAndClick("Terms of Use");

	}

	public void clickONPrivacyPolicy() throws IOException, InterruptedException {

		driver.navigate().back();
		//ak.click("PrivacyPolicy_XPATH");
		Thread.sleep(2000);
		ak.mouseHoverAndClick("Privacy Policy");

	}

	public void clickONWebAccess() throws IOException, InterruptedException {

		driver.navigate().back();
		//ak.click("WebAcc_XPATH");
		Thread.sleep(2000);
		ak.mouseHoverAndClick("Web Accessibility Statement");

	}

}
