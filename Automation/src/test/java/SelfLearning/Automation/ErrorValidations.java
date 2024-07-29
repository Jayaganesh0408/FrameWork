package SelfLearning.Automation;

import org.testng.Assert;
import org.testng.annotations.Test;

import SelfLearning.TestComponents.Retry;
import SelfLearning.TestComponents.TestBase;
import SelfLearning.packages.CartPage;
import SelfLearning.packages.ProductPage;

public class ErrorValidations extends TestBase{

	
		@Test(groups = {"smoke"}, retryAnalyzer = SelfLearning.TestComponents.Retry.class)
		public void loginErrorVerification() throws InterruptedException {

			loginPage.loginToApplication("Ben04@gmail.com","Ben#0408");
			Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
			
		
		}
		@Test(groups= {"Purchase"})
		public void productErrorVerification() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductPage productPage =loginPage.loginToApplication("Gwen0408@gmail.com","Gwen#0408");
	
		productPage.addTocart(productName);
		CartPage cartPage=productPage.navigateToCart();

		Boolean match=cartPage.checkCartItem("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
		}

}
