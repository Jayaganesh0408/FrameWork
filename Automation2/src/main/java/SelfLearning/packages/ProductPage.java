package SelfLearning.packages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelfLearning.Abstarctcomponents.AbstarctComponent;

public class ProductPage extends AbstarctComponent{
	
public WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	By addToCart=By.cssSelector("button:last-of-type");
	By message=By.cssSelector("#toast-container");
	

	
	@FindBy(css=".ng-animating")
	WebElement loading;
	
	@FindBy(css=".card")
	List<WebElement> products;
	
//	@FindBy(css=".card")
//	WebElement items;
	
	
	public List<WebElement> getProductList() {
		waitUntilElementStable(driver.findElement(By.cssSelector(".card")));
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod =getProductList().stream().filter(n->n.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addTocart(String productName) throws InterruptedException {
		WebElement prod= getProductByName(productName);
		prod.findElement(addToCart).click();
		waitUntilElementVisible(message);
		waitUntilElementInvisible(loading);
	}
	

}



