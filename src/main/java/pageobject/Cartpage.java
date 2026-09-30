package pageobject;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.CloseAd;
import abstractcomponents.abstractmethods;

public class Cartpage extends abstractmethods {

WebDriver driver;
CloseAd ad;
	
	public  Cartpage(WebDriver driver){
		this.driver = driver;
		ad = new CloseAd(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h4/a")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//td[@class='cart_description']/h4/a")
	List<WebElement> updatedCart;
	
	@FindBy(css=".check_out")
	WebElement checkout;
	
	@FindBy(xpath="//u[contains(text(),'View Cart')]")
	WebElement viewCart;
	
	public void gotoCartpage() {
		
		//driver.get("https://automationexercise.com/view_cart");
		viewCart.click();
		ad.removeAdIfPresent();
	}
	public void removeCart() 
		{
		
			WebElement deleteProduct = cartProducts.stream().filter(s -> s.getText().toLowerCase().contains("winter top"))
					.findFirst().orElseThrow();
			WebElement row = deleteProduct.findElement(By.xpath("./ancestor::tr"));

			row.findElement(By.cssSelector("a.cart_quantity_delete")).click();
			waitstaleness(row,20,driver);
			
			boolean isPresent = updatedCart.stream().anyMatch(s -> s.getText().toLowerCase().contains("winter top"));
			System.out.println("Winter Top present after deletion: " + isPresent);

		}
	
	public void gotocspage() {
		checkout.click();
		ad.removeAdIfPresent();
	}
	
	
	}

