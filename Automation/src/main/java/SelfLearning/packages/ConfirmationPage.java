package SelfLearning.packages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import SelfLearning.Abstarctcomponents.AbstarctComponent;

public class ConfirmationPage extends AbstarctComponent{
public WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	By confirmMessage =By.cssSelector(".hero-primary");
	
	public String getOrderConfirmationMessage() {
		 return driver.findElement(confirmMessage).getText();
	}

}
