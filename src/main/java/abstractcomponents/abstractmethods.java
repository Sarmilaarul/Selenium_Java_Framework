package abstractcomponents;
import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class abstractmethods {

	
public void waitelementClickable(WebElement element,int duration,WebDriver driver) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
	wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void waitstaleness(WebElement element,int duration,WebDriver driver) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(duration));
	wait.until(ExpectedConditions.stalenessOf(element));
}

public void waiturlcontains(WebDriver driver,int duration,String parameter) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
	wait.until(ExpectedConditions.urlContains(parameter));
}
public void waitElementVisible(WebElement element, int duration, WebDriver driver) {

    WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(duration));

    wait.until(ExpectedConditions.visibilityOf(element));
}

}

