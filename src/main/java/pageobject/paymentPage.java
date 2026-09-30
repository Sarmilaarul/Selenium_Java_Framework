package pageobject;


import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.CloseAd;

public class paymentPage {
	
	WebDriver driver;
	CloseAd ad;
	public paymentPage(WebDriver driver) {
		
		this.driver=driver;
		ad = new CloseAd(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='name_on_card']") WebElement name;
	@FindBy(css="h2[class='heading']") WebElement header;
	@FindBy(xpath="//input[@data-qa='card-number']") WebElement cardNo;
	@FindBy(xpath="//input[@data-qa='cvc']") WebElement cvc;
	@FindBy(xpath="//input[@data-qa='expiry-month']") WebElement month;
	@FindBy(xpath="//input[@data-qa='expiry-year']") WebElement year;
	@FindBy(id="submit") WebElement submitButton;
	@FindBy(xpath="//section[@id='form']/div/div/div/p") WebElement msg;
	
	public void verifyPayment() throws TimeoutException {
		
				System.out.println("The title of the page is :"+header.getText());
				name.sendKeys("testsharmi");
				cardNo.sendKeys("12345678");
				cvc.sendKeys("123");
				month.sendKeys("02");
				year.sendKeys("2029");
				submitButton.click();
				System.out.println("Payment is completed");
				
		 }
		 
		public String confirmationMsg() {

			return msg.getText();
		 
			}
	

}
