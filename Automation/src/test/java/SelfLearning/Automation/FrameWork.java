package SelfLearning.Automation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import SelfLearning.TestComponents.TestBase;
import SelfLearning.packages.CartPage;
import SelfLearning.packages.CheckoutPage;
import SelfLearning.packages.ConfirmationPage;
import SelfLearning.packages.OrdersPage;
import SelfLearning.packages.ProductPage;

public class FrameWork extends TestBase{

		
		@Test(dataProvider = "getData", groups = {"Purchase"})
		public void testOrderSubmission(HashMap<String, String> input) throws InterruptedException {
		
		
		ProductPage productPage =loginPage.loginToApplication(input.get("email"),input.get("password"));
	
		productPage.addTocart(input.get("productName"));
		CartPage cartPage=productPage.navigateToCart();

		Boolean match=cartPage.checkCartItem(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.checkOut();
	
		checkoutPage.fillDetails();
		checkoutPage.selectCountry("ind");
		ConfirmationPage cfPage=checkoutPage.submitOrder();
		
		String message=cfPage.getOrderConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		
		}
		
		@Test(dependsOnMethods = {"testOrderSubmission"})
		public void checkSubmittedOrder() {
			 String productName = "ZARA COAT 3";
			ProductPage productPage =loginPage.loginToApplication("Ben0408@gmail.com","Ben#0408");
			OrdersPage orderPage=productPage.navigateToOrders();
			Assert.assertTrue(orderPage.checkOrderDisplay(productName));
			
			
			
		}
		
		@DataProvider
		public Object[][] getData() throws IOException {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("email", "Ben0408@gmail.com");
//			map.put("password", "Ben#0408");
//			map.put("productName", "ZARA COAT 3");
//			
//			HashMap<String, String> map2 = new HashMap<String, String>();
//			map2.put("email", "Gwen0408@gmail.com");
//			map2.put("password", "Gwen#0408");
//			map2.put("productName", "ADIDAS ORIGINAL");
			
			List<HashMap<String,String>> data= getDatafromJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SelfLearning\\Data\\TestData.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
		
		

}
