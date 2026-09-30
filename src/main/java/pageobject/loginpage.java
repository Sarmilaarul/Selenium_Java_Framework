package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.abstractmethods;

public class loginpage extends abstractmethods{


	 WebDriver driver;
	
	public  loginpage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//a[@href='/login']")
	WebElement login;
	
	@FindBy(xpath="//div[@class='login-form']/h2")
	WebElement loginform;
	
	@FindBy(xpath="//input[@data-qa='login-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@data-qa='login-password']")
	WebElement password;
	
	@FindBy(xpath="//form/input/following-sibling::button[@data-qa='login-button']")
	WebElement loginButton;
	
	@FindBy(id="dismiss-button-element")
	WebElement dismisspopup;
	
	@FindBy(tagName = "iframe")
	List<WebElement> frames;

	@FindBy(xpath = "//*[name()='svg']/..")
	List<WebElement> closeButtons;
	
	@FindBy(tagName = "iframe")
	WebElement currentFrames;
	

	public void logintest(String emailaddress,String Password) throws InterruptedException {
		login.click();
		System.out.println("The title of the page is "+loginform.getText());
		email.sendKeys(emailaddress);
		password.sendKeys(Password);
		//email.sendKeys("sharmitest1@gmail.com");
		//password.sendKeys("Sharmiqa");
		loginButton.click();
		//waitelementClickable(dismisspopup,10,driver);

	}

	
	
}
