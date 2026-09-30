package abstractcomponents;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloseAd extends abstractmethods{

    WebDriver driver;

    public CloseAd(WebDriver driver) {
        this.driver = driver;
    }
    

    public void removeAdIfPresent() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "document.querySelectorAll('ins.adsbygoogle')" +
            ".forEach(ad => ad.remove());"
        );

    }
    
    public void closeAdpopup() throws TimeoutException {

        WebDriverWait wait =
		        new WebDriverWait(driver, Duration.ofSeconds(3));

		WebElement closeButton = wait.until(
		        ExpectedConditions.elementToBeClickable(
		                By.id("dismiss-button")
		        )
		);

		closeButton.click();

		System.out.println("Interstitial ad closed");
    }
}