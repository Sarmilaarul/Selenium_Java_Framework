import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class checkbox {

	
	public static void main(String[] args) throws InterruptedException{
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com");
		/*WebElement checkbox = driver.findElement(By.id("checkBoxOption1"));
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected(),"Checkbox is not selected");
		System.out.println("Checkbox1 is selected successfully");
		
		checkbox.click();
		Assert.assertFalse(checkbox.isSelected(),"Checkbox is still selected");
		System.out.println("Checkbox1 is unselected successfully");
		List<WebElement> checkboxes =
		        driver.findElements(By.xpath("//input[@type='checkbox']"));

		System.out.println("Total checkboxes: " + checkboxes.size());*/
		
		/*Form
		driver.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys("Testdemo");
		driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("test@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("testqa");
		WebElement dp=driver.findElement(By.id("exampleFormControlSelect1"));
		Select select = new Select(dp);
		select.selectByVisibleText("Female");
		driver.findElement(By.id("inlineRadio2")).click();
		driver.findElement(By.xpath("//input[@name='bday']")).sendKeys("02-09-1990");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement mesg=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
		Assert.assertEquals(mesg.getText().trim(), "Success! The Form has been submitted successfully!.","Form not submitted");
		*/
		
		/*String country = "Ind";
		String actualcountry = "India";
		driver.findElement(By.id("autocomplete")).sendKeys(country);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> dp = wait.until(
		        ExpectedConditions.visibilityOfAllElementsLocatedBy(
		                By.xpath("//li[contains(@class,'ui-menu-item')]/div")
		        )
		);		
		
		WebElement countrydp =dp.stream().filter(s->s.getText().equalsIgnoreCase(actualcountry)).findFirst().orElseThrow();
		
		countrydp.click();
		System.out.println("Selected!!");*/
		
		/*WebElement table = driver.findElement(By.xpath("//table[@name='courses']"));
		int RowCount = table.findElements(By.xpath(".//tr")).size();
		System.out.println("Rowcount is: "+RowCount);	
		
		int colCount = table.findElements(By.xpath(".//tr/th")).size();
		System.out.println("Column count is: "+ colCount);
		
	    List<WebElement> secRow = table.findElements(By.xpath("(.//tbody/tr)[3]/td"));
	    
	    for(int i=0;i<secRow.size();i++) {
	    	
	    	System.out.println(secRow.get(i).getText());
	    }*/
		
		driver.findElement(By.xpath("//a[@href='/windows']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("/windows"));
		driver.findElement(By.xpath("//a[@href='/windows/new']")).click();
		
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		
		String parent=it.next();
		String child = it.next();
		
		driver.switchTo().window(child);
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
		driver.switchTo().window(parent);
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());

	}
}
