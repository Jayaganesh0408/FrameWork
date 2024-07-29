package SelfLearning.Automation;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumCodeAlone {

	
	public static void main(String args[]){
		
		ChromeOptions option = new ChromeOptions();
		option.setAcceptInsecureCerts(true);
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.rahulshettyacademy.com/client");
		//Username = Ben0408@gmail.com
		//Password = Ben#0408
		driver.findElement(By.id("userEmail")).sendKeys("Ben0408@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ben#0408");
		driver.findElement(By.id("login")).click();
		String productName = "ZARA COAT 3";

		
		// Wait mechanism to avoid stale element reference and to avoid element not intractable or click intercepted issues
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(".card"))));
		
		//Getting all products on page and finding the required product and add to cart
		List<WebElement> availableProducts = driver.findElements(By.cssSelector(".card"));
		WebElement prod =availableProducts.stream().filter(n->n.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		
		//To check whether item got added we need to check the popup showing post add to cart button click but as there is loading screen coming when we add to cart we need to use explicit wait to handle
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	
		//To check whether the added items are displayed in the cart page and click on checkout button
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= cartItems.stream().anyMatch(n->n.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
		
		//Fill the checkout page and select country
		
		driver.findElement(By.xpath("(//div[@class='form__cc']/div/div/div/following-sibling::input)[2]")).sendKeys("143");
		driver.findElement(By.xpath("(//div[@class='form__cc']/div/div/div/following-sibling::input)[3]")).sendKeys("Ben 10");
		driver.findElement(By.name("coupon")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector(".btn-primary")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mt-1.ng-star-inserted"))));
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> country = driver.findElements(By.cssSelector(".ta-results button"));
		WebElement requiredCountry=country.stream().filter(n->n.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		requiredCountry.click();
		
		//Place order and verify
		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}
}
