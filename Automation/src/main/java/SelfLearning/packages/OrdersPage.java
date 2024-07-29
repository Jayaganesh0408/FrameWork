package SelfLearning.packages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SelfLearning.Abstarctcomponents.AbstarctComponent;

public class OrdersPage extends AbstarctComponent{
	
public WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderedItems;
	

	
	public Boolean checkOrderDisplay(String productName) {
		navigateToOrders();
		Boolean match= OrderedItems.stream().anyMatch(n->n.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	
	

}
