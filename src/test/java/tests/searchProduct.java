package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobject.loginpage;
import pageobject.productsPage;
import testcomponents.baseTest;


import java.io.IOException;
import org.testng.annotations.DataProvider;




public class searchProduct extends baseTest {

	SoftAssert a = new SoftAssert();
	@Test(dataProvider = "searchData")
	
	public void searchitem(String emailaddress, String Password, String item) throws IOException, InterruptedException {
		
		loginpage login = new loginpage(driver);
		login.logintest(emailaddress, Password);
		
		productsPage prod = new productsPage(driver);
		prod.gotoProductspage();
		prod.search(item);

	}
	
	@Test
	public void category() throws IOException, InterruptedException {

	    launchApp();

	    loginpage login = new loginpage(driver);
	    login.logintest("sharmitest1@gmail.com", "Sharmiqa");

	    productsPage prod = new productsPage(driver);
	    prod.gotoProductspage();

	    a.assertEquals(
	        prod.getcategorytitle(),
	        "Category",
	        "The title is incorrect"
	    );

	    prod.categoryselection();
	    a.assertAll();
	}

	
	@DataProvider(name = "searchData")
	
	public Object[][] getdata() {
	
		return new Object[][]{
		{"sharmitest1@gmail.com","Sharmiqa","Winter Top"},
		//{"Summer White top"},
		//{"Blue Top"}
		};
	}
	
	
}
