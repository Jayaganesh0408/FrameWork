package SelfLearning.Abstarctcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SelfLearning.packages.CartPage;
import SelfLearning.packages.OrdersPage;

public class AbstarctComponent {
	
public WebDriver driver;
	
	public AbstarctComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartTab;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement ordersTab;
	
	
	public void waitUntilElementVisible(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitUntilElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilElementStable(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.stalenessOf(findBy));
	}
	
	public void waitUntilElementInvisible(WebElement element) throws InterruptedException {
		Thread.sleep(3000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage navigateToCart() {
		cartTab.click();
		return new CartPage(driver);
	}
	
	public OrdersPage navigateToOrders() {
		ordersTab.click();
		return new OrdersPage(driver);
	}

}
