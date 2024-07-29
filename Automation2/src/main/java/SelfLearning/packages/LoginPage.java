package SelfLearning.packages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelfLearning.Abstarctcomponents.AbstarctComponent;

public class LoginPage extends AbstarctComponent{
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement emailField;
	
	@FindBy(id="userPassword")
	WebElement passwordField;
	
	@FindBy(id="login")
	WebElement loginButton;
	//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductPage loginToApplication(String username, String password) {
		emailField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		return new ProductPage(driver);
	}
	public void launchLoginPage() {
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	public String getErrorMessage() {
		waitUntilElementVisible(errorMessage);
		return errorMessage.getText();
	}
	

	
}

