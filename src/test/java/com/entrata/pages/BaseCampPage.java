package com.entrata.pages;

import java.io.IOException;

import com.entrata.base.TestBase;

public class BaseCampPage extends TestBase{
	
	public void clickOnRegisterNow() throws InterruptedException, IOException {
		
		ak.STOREWINDOWID();
		ak.SWITCHWINDOW();
		
		Thread.sleep(2000);
		
		ak.click("MenuOpt_XPATH");
		ak.click("RegMenuOpt_XPATH");
		Thread.sleep(1000);
		ak.click("RegisterNow_XPATH");
		
		
	}
	
	public void fillPersonalInfo(String FirstName, String LastName, String Company, String Title, String Email, String Mobile) throws IOException, InterruptedException {
		
		ak.input("FirstName_XPATH", FirstName);
		ak.input("LastName_XPATH", LastName);
		ak.input("Company_XPATH", Company);
		ak.input("Title_XPATH", Title);
		ak.input("Email_XPATH", Email);
		ak.input("Mobile_XPATH", Mobile);
		
		Thread.sleep(2000);
		ak.click("Cancel_XPATH");
		
	}
	
	

}
