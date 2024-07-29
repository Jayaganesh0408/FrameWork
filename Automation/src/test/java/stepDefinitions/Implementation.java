package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import SelfLearning.TestComponents.TestBase;
import SelfLearning.packages.CartPage;
import SelfLearning.packages.CheckoutPage;
import SelfLearning.packages.ConfirmationPage;
import SelfLearning.packages.LoginPage;
import SelfLearning.packages.ProductPage;
import io.cucumber.java.en.*;

public class Implementation extends TestBase{

		public LoginPage loginPage;
		public ProductPage productPage;
		public CartPage cartPage;
		public CheckoutPage checkoutPage;
		public ConfirmationPage cfPage;
		
		
	    @Given("User landed on Ecommerce Page")
	    public void user_land_on_page() throws IOException {
	    	loginPage=launchApp();
	    }
	
	    @Given("^Loggin in with (.+) and (.+)$")
	    public void loggin_with_cred(String username,String password) {
	    	productPage =loginPage.loginToApplication(username,password);
	    }

	    @When("^User add product (.+) to cart$")
	    public void add_product(String productName) throws InterruptedException {
	    	productPage.addTocart(productName);
	    }
	    
	    @And("^User checkout (.+) and submit order$")
	    public void checkout_sumit(String productName) {
	    	cartPage=productPage.navigateToCart();

			Boolean match=cartPage.checkCartItem(productName);
			Assert.assertTrue(match);
			checkoutPage=cartPage.checkOut();
		
			checkoutPage.fillDetails();
			checkoutPage.selectCountry("ind");
			cfPage=checkoutPage.submitOrder();
	    }
	    
	    @Then("{string} message is shown in confirmation page")
	    public void confirm(String confirm) {
	    	String message=cfPage.getOrderConfirmationMessage();
			Assert.assertTrue(message.equalsIgnoreCase(confirm));
			driver.close();
	    }
	    
	    @Then("{string} popup is shown")
	    public void incorrect_email_or_password_popup_is_shown(String popup) throws Throwable {
			Assert.assertEquals(loginPage.getErrorMessage(), popup);   
	    }
}
