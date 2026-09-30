package pageobject;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractcomponents.abstractmethods;

public class costSummaryPage extends abstractmethods{
	
	WebDriver driver;
	String[] dress;
	
	public costSummaryPage(WebDriver driver, String[] dress) {
		
		this.dress = dress;
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="(//li[@class='address_address1 address_address2'])[1]") WebElement address1;
	@FindBy(xpath="(//li[@class='address_address1 address_address2'])[2]") WebElement address2;
	@FindBy(xpath="(//li[@class='address_address1 address_address2'])[3]") WebElement address3;
	@FindBy(xpath="//li[@class='address_city address_state_name address_postcode']") WebElement city;;
	@FindBy(xpath="(//li[@class='address_country_name'])[1]") WebElement country;
	@FindBy(xpath="(//li[@class='address_phone'])[1]") WebElement mobileno;
	@FindBy(xpath="//h4/a") List<WebElement> cartProducts;
	@FindBy(xpath="//td[@class='cart_quantity']") List<WebElement> Quantity;
	@FindBy(css="td[class='cart_total'] p[class='cart_total_price']") List<WebElement> Prices;
	@FindBy(xpath="(//p[@class='cart_total_price'])[last()]") WebElement cartPrice;
	@FindBy(xpath = "//a[normalize-space()='Place Order']")WebElement placeOrder;
	@FindBy(xpath="//h2[@class='heading']") WebElement carttitle;
	
	


	public String getAddress1() {
		return address1.getText();	
	}
	
	public String getAddress2() {
		return address2.getText();	
	}
	
	public String getAddress3() {
		return address3.getText();	
	}
	public String[] getPlace() {
		String[] cityStateZip = city.getText().trim().split("\\s+");
		return cityStateZip;	
	}
	public String getcountry() {
		return country.getText();	
	}
	public String getmobileno() {
		return mobileno.getText();	
	}
	
	public void verifyCart(String dress[]) {

		Boolean isFound = true;
		waitElementVisible(carttitle,10,driver);
		for (String d : dress) {

			for (WebElement cart : cartProducts) {
				String cartProduct = cart.getText();
				if (cartProduct.equals(d)) {
					isFound = true;
				}
			}
			if (isFound) {
				System.out.println(d + " is present in the cart");
			} else {
				System.out.println(d + " is not present in the cart");
			}

		}

	}
	
	public int getquantity() {
		
		int totalQuantity = Quantity.stream()
		        .mapToInt(s -> Integer.parseInt(s.getText()))
		        .sum();		
		return totalQuantity;
	}
	
	public int gettotalPrice() {
		int totalPrice = 0;

		for (WebElement P : Prices) {
			String Price = P.getText().split(" ")[1];
			int indPrice = Integer.parseInt(Price);
			totalPrice += indPrice;
		}
		System.out.println("The sum of the price of the individual products are " + totalPrice);
		return totalPrice;
	}
	
	public int getCartPrice() {
		String totalCost = cartPrice.getText().split(" ")[1];
		int totalCartPrice = Integer.parseInt(totalCost);
		System.out.println("The total price in the cart is: " + totalCartPrice);
		
		return  totalCartPrice;
	}
	
	public void gotopaymentpage() {
		waitelementClickable(placeOrder,10,driver);
		placeOrder.click();
	    waiturlcontains(driver, 10, "payment");

	 
	}
	
}
