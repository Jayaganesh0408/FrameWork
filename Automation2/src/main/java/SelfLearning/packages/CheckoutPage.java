package SelfLearning.packages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SelfLearning.Abstarctcomponents.AbstarctComponent;

public class CheckoutPage extends AbstarctComponent{
	
public WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//div[@class='form__cc']/div/div/div/following-sibling::input)[2]")
	WebElement cvv;
	
	@FindBy(xpath="(//div[@class='form__cc']/div/div/div/following-sibling::input)[3]")
	WebElement nameOnCard;
	
	@FindBy(name="coupon")
	WebElement coupon;
	
	@FindBy(css=".btn-primary")
	WebElement applyCouponButton;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryInput;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countries;
	
	@FindBy(css="a[class*='action__submit']")
	WebElement placeOrderButton;
	
	By couponMessage = By.cssSelector(".mt-1.ng-star-inserted");
	
	public void fillDetails() {
		cvv.sendKeys("143");
		nameOnCard.sendKeys("Ben 10");
		coupon.sendKeys("rahulshettyacademy");
		applyCouponButton.click();
		waitUntilElementVisible(couponMessage);
	}
	
	public void selectCountry(String country) {
		countryInput.sendKeys(country);
		WebElement requiredCountry=countries.stream().filter(n->n.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		requiredCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	

	

}
