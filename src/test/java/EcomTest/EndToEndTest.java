package EcomTest;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.HomePage;
import utils.JsonDataProvider;
import utils.LoginData;

public class EndToEndTest extends BaseTest {
	
	String country = "India";

	@Test (dataProvider = "loginData", dataProviderClass = JsonDataProvider.class,
			groups= {"placeOrder","Smoke"})
	public void submitOrder(LoginData data) throws IOException {	
		String productName= data.getProduct();
		HomePage homepage=login.loginTest(data.getUsername(), data.getPassword());
		
		homepage.addItemToCart(productName);
		CartPage cartpage=homepage.navigateToCart();
		
		boolean itemPresent = cartpage.verifyItemAddedInCart(productName);
		Assert.assertTrue(itemPresent);	
		
		CheckoutPage checkout=cartpage.checkout();
		ConfirmationPage confirmationPage=checkout.addCountryInCheckout(country.substring(0, 3), country);
		
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		System.out.println(confirmationMessage);
		
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
	}
	
}
