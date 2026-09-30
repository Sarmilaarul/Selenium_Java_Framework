package pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class invoicepage {
	WebDriver driver;
	public invoicepage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".check_out") WebElement checkoutButton;
	@FindBy(xpath="//a[@data-qa='continue-button']") WebElement continueButton;
	
	public void downloadInvoice() {
		
		
		checkoutButton.click();
		
			System.out.println("Donwload is initiated");
		
	}
	
	public void gotocontinue() {
		continueButton.click();
	}
}
