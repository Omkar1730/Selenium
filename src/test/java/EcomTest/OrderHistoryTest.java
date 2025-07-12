package EcomTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.OrdersHistoryPage;


public class OrderHistoryTest extends BaseTest {

	@Test(dependsOnGroups = {"placeOrder"}, groups= {"Smoke"})
	public void verifyProductInOrderHistory() {
		HomePage homepage = login.loginTest("omkarp800@gmail.com", "Omkar@5137");
		OrdersHistoryPage orderHistory= homepage.navigateToOrderDetails();
		Boolean match = orderHistory.verifyProductInOrderHistory(productName);
		Assert.assertTrue(match);
	}
}
