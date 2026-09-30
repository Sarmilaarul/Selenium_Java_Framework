package pageobject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.CloseAd;
import abstractcomponents.abstractmethods;

public class productsPage extends abstractmethods{

WebDriver driver;
//String[] dress;
String[] dress = { "Winter Top", "Summer White Top" };
String item;
CloseAd ad;
	
	public  productsPage(WebDriver driver){
		this.driver = driver;
		ad = new CloseAd(driver);
		//this.dress = dress;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='overlay-content']/p") List<WebElement> products;
	@FindBy(xpath="//button[@class='btn btn-success close-modal btn-block']") WebElement shop;
	@FindBy(id="search_product") WebElement search;
	@FindBy(id="submit_search") WebElement searchButton;
	@FindBy(xpath="//div/h2[text() ='Category']") WebElement categorytitle;
	@FindBy(xpath="//h4/a") List<WebElement> categorylist;
	@FindBy(xpath="//div[@class='panel-collapse in']/div/ul/li") List<WebElement> subcategorylist;
    @FindBy(xpath="//div/p") List<WebElement> search1;

	
	public void gotoProductspage() {
		driver.get("https://automationexercise.com/products");
		waiturlcontains(driver,10,"/products");
		ad.removeAdIfPresent();
	}
	
	public void addtoCart() {
		for (int page = 0; page < dress.length; page++) {

			for (int i = 0; i < products.size(); i++) {

				String productName = products.get(i).getAttribute("textContent");

				if (productName.equals(dress[page])) {

					WebElement product = products.get(i)
							.findElement(By.xpath("./ancestor::div[contains(@class,'product-image-wrapper')]"));

					product.findElement(By.xpath(".//a[text()='Add to cart']")).click();
                    
					waitelementClickable(shop,10,driver);

					if (page < dress.length - 1) {
						shop.click();
					} else {
						
						System.out.println("The products are added successfully");
					}

					break;
				}
			}
		}

	
	}
	
	public void search(String item) throws InterruptedException {
		
		search.sendKeys(item);
		searchButton.click();
		ad.removeAdIfPresent();
		WebElement expecteditem = search1.stream().filter(s->s.getText().contains(item)).findFirst().get();
		System.out.println(expecteditem.getText());
		//. means search inside the element
	    WebElement productInfo = expecteditem.findElement(
	            By.xpath("./parent::div")
	    );

	    WebElement AddToCart = productInfo.findElement(
	            By.xpath(".//a[contains(@class,'add-to-cart')]")
	    );

		waitelementClickable(AddToCart,30,driver);
		AddToCart.click();
		
	}
	
	public String getcategorytitle() {
		return categorytitle.getText();
	}
	
	public void categoryselection() {
		

		categorylist.stream().map(s->s.getText()).forEach(s->System.out.println(s));
		
		WebElement category1 = categorylist.stream().filter(s->s.getText().toLowerCase().contains("women")).findFirst().orElseThrow();
		category1.findElement(By.cssSelector("i[class='fa fa-plus']")).click();
		
		WebElement subCategory1 = subcategorylist.stream().filter(s->s.getText().contains("Dress")).findFirst().get();
		subCategory1.click();
		System.out.println(driver.getTitle());
		
		products.stream().forEach(s->System.out.println(s.getText()));
			
	}


	
}
