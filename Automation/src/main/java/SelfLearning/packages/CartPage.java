package SelfLearning.packages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SelfLearning.Abstarctcomponents.AbstarctComponent;

public class CartPage extends AbstarctComponent{
	
 public WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow .btn-primary")
	WebElement checkoutButton;
	
	public Boolean checkCartItem(String productName) {
		Boolean match= cartItems.stream().anyMatch(n->n.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage checkOut() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
	
	
	

}
