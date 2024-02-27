package com.entrata.pages;

import java.io.IOException;

import com.entrata.base.TestBase;

public class BaseCampPage extends TestBase {

	public void clickOnRegisterNow() throws InterruptedException, IOException {

		ak.STOREWINDOWID();
		ak.SWITCHWINDOW();

		ak.click("MenuOpt_XPATH");
		ak.click("RegMenuOpt_XPATH");
		ak.click("RegisterNow_XPATH");

	}

	public void fillPersonalInfo(String FirstName, String LastName, String Company, String Title, String Email,
			String Mobile) throws IOException, InterruptedException {

		ak.input("FirstName_XPATH", FirstName);
		ak.input("LastName_XPATH", LastName);
		ak.input("Company_XPATH", Company);
		ak.input("Title_XPATH", Title);
		ak.input("Email_XPATH", Email);
		ak.input("Mobile_XPATH", Mobile);

		ak.click("Cancel_XPATH");

	}

}
