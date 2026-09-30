package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageobject.Cartpage;
import pageobject.costSummaryPage;
import pageobject.invoicepage;
import pageobject.loginpage;
import pageobject.paymentPage;
import pageobject.productsPage;
import testcomponents.baseTest;


@Listeners(testcomponents.Listeners.class)
public class submitOrdertest extends baseTest{

	String[] dress = { "Winter Top", "Summer White Top" };
	String address1="Kristech";
	String address2="Laman baiduri";
	String address3="Badla";
	String city="Pondi";
	String country="India";
	String state="Tamilnadu";
	String mobileno="7958347890";
	String zipcode="675787";
	SoftAssert a = new SoftAssert();
	
	@Test(dataProvider = "credentials",groups = {"Cart"})

	public void addtoCart(String emailaddress,String Password) throws IOException, InterruptedException {


			//Login scenario
			loginpage login = new loginpage(driver);
			login.logintest(emailaddress, Password);
			
			//Addtocart scenraio
			productsPage prod = new productsPage(driver);
			prod.gotoProductspage();
			prod.addtoCart();
			
			//Remove product from cart scenario
			Cartpage cart = new Cartpage(driver);
			cart.gotoCartpage();
			cart.removeCart();
			cart.gotocspage();
	}
	
	        @Test(groups={"Cart"},dependsOnMethods="addtoCart")
	        
	        public void submitOrder() throws TimeoutException {
			//addressCheck
			costSummaryPage costSummary = new costSummaryPage(driver,dress);
			Assert.assertEquals(costSummary.getAddress1(), address1, "Address is incorrect");
			Assert.assertEquals(costSummary.getAddress2(), address2,"Address is incorrect");
	        Assert.assertEquals(costSummary.getAddress3(), address3,"Address is incorrect"); 
	        String[] actualPlace = costSummary.getPlace();
	        Assert.assertEquals(actualPlace[0],city, "City is incorrect");
	        Assert.assertEquals(actualPlace[1], state, "State is incorrect");
	        Assert.assertEquals(actualPlace[2], zipcode, "Zipcode is incorrect");
	        Assert.assertEquals(costSummary.getcountry(), country,"Address is incorrect");
	        Assert.assertEquals(costSummary.getmobileno(), mobileno,"Phone number is incorrect");

	        //VerifyCart
			costSummary.verifyCart(dress);
			System.out.println("The Total quantity of products present in the cart is " +costSummary.getquantity());
			
			//Price verification
			Assert.assertEquals(costSummary.gettotalPrice(), costSummary.getCartPrice(), "The total Cart price is not verified");
			System.out.println("The Price is verified");
			costSummary.gotopaymentpage();
			
			paymentPage payment = new paymentPage(driver);
			payment.verifyPayment();
			Assert.assertEquals(payment.confirmationMsg(),
					"Congratulations! Your order has been confirmed!", "Something went wrong-Order is not completed");
			System.out.println("Order is successfully placed");
			
			invoicepage invoice = new invoicepage(driver);
			invoice.downloadInvoice();
			invoice.gotocontinue();
			a.assertAll();
	        }


	
//using dataprovider multi dimentional array
	/*@DataProvider(name ="credentials")
	
	public Object[][] getdata()
	{
		
		Object[][] data = new Object[2][2];
		
		data[0][0] = "sharmitest1@gmail.com";
		data[0][1] = "Sharmiqa";
		data[1][0] = "sharmitest2@gmail.com";
		data[1][1] = "Sharmiqa";
		return data;

	//Using json data extraction
@DataProvider(name ="credentials")
	
	public Object[][] getdata()
	{
		
	List<HashMap<String, String>> data = getjsonData(
		    System.getProperty("user.dir") + "\\src\\main\\java\\Data\\data.json"
		);
		
		return new Object[][] {
			{ data.get(0).get("email"), data.get(0).get("password") },
	        { data.get(1).get("email"), data.get(1).get("password") }
		};
	}*/
	
	@DataProvider(name = "credentials")
	public Object[][] getdata() throws IOException {

	    String path = "C:\\Users\\Ashok\\eclipse-workspace\\excel.xlsx";

	    return excelData(path);
	}
	    
	}
	

