package pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class signupPage {
	
	WebDriver driver;

	public signupPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[@href='/login']") WebElement login;
	@FindBy(xpath="//div[@class='signup-form']/h2") WebElement Signupform;
	@FindBy(xpath="//input[@data-qa='signup-name']") WebElement signupname;
	@FindBy(xpath="//form/button[@data-qa='signup-button']") WebElement signupbutton;
	@FindBy(css="input[data-qa='signup-email']") WebElement email;
	@FindBy(xpath="(//div/form/p)[1]") WebElement text;
	@FindBy(id="id_gender2") WebElement gender;
	@FindBy(id="name") WebElement name;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	@FindBy(id="days") WebElement days;
	@FindBy(xpath="//select[@id='months']") WebElement months;
	@FindBy(id="years") WebElement years;
	@FindBy(id="newsletter") WebElement checkbox1;
	@FindBy(id="optin") WebElement checkbox2;
	@FindBy(id="first_name") WebElement firstname;
	@FindBy(xpath="//input[@name='last_name']") WebElement lastname;
	@FindBy(id="company") WebElement company;
	@FindBy(id="address1") WebElement address1;
	@FindBy(id="address2") WebElement address2;
	@FindBy(id="country") WebElement country;
	@FindBy(id="state") WebElement state;
	@FindBy(id="city") WebElement city;
	@FindBy(id="zipcode") WebElement zipcode;
	@FindBy(id="mobile_number") WebElement mobileno;
	@FindBy(xpath="//button[@data-qa='create-account']") WebElement createbutton;
	@FindBy(xpath="//h2[@data-qa='account-created']/b") WebElement message;
	@FindBy(xpath="//a[@data-qa='continue-button']") WebElement continuebutton;
	@FindBy(xpath="(//div[@class='shop-menu pull-right']/ul/li)[10]/a/b") WebElement loggedinas;
	
	
	public void signup() {
		login.click();
		//a.assertEquals(Signupform.isDisplayed(),"New User Signup!", "Sign up Title is incorrect");
		signupname.sendKeys("sarmila");
		email.sendKeys("sharmitest15@gmail.com");
		signupbutton.click();
		 
	}
		
		public void signupDetails() {
		gender.click();// recheck once that course is completed
		name.click();
		password.sendKeys("Sharmiqa");

		// Check chatgpt is this is the correct way to handle multiple drop downs
		WebElement staticdropdown, dp2, dp3, country;
		staticdropdown = days;
		Select dropdown = new Select(staticdropdown);
		dropdown.selectByValue("1");

		dp2 = months;
		Select dropdown1 = new Select(dp2);
		dropdown1.selectByVisibleText("January");

		dp3 = years;
		Select dropdown2 = new Select(dp3);
		dropdown2.selectByVisibleText("1992");

		// Checkboxes
		checkbox1.click();
		checkbox2.click();
		//System.out.println(driver.findElement(By.id("newsletter")).isSelected());
		//System.out.println(driver.findElement(By.xpath("//input[@type='checkbox']")).getSize());// check the size again

		firstname.sendKeys("Test");
		lastname.sendKeys("sharmi");
		company.sendKeys("Kristech");
		address1.sendKeys("Laman baiduri");
		address2.sendKeys("Badla");

		// country dropdown
		//Select dropdown3 = new Select(country);
		//dropdown3.selectByVisibleText("India");

		state.sendKeys("Tamilnadu");
		city.sendKeys("Pondi");
		zipcode.sendKeys("675787");
		mobileno.sendKeys("7958347890");
		createbutton.click();
		Assert.assertEquals(message.getText(),
				"ACCOUNT CREATED!", "Account creation failed");
		System.out.println("Signup Test is verified");
		continuebutton.click();
		String user =loggedinas.getText();
		System.out.println(user);
		System.out.println();
		Assert.assertEquals(user, "sarmila", "Login is failed");
		System.out.println("Login is verified upon account creation");
	}
	
	public String emailValidation() {
		return text.getText();
	}
	

}
