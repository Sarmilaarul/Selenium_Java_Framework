package tests;
import org.testng.annotations.Test;

import Resources.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;

import pageobject.signupPage;
import testcomponents.baseTest;

public class errorValidation extends baseTest{
	
	@Test(retryAnalyzer = Retry.class)
	
	public void emailValidation() throws IOException {
		
		initializeDriver();
		signupPage sign = new signupPage(driver);
		sign.signup();
		
		AssertJUnit.assertEquals(sign.emailValidation(),"Email Address already exist!");
		System.out.println("EmailID already exists");
	}
	

}
