package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utils.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".subtotal button")
	WebElement checkout;
			
	public boolean verifyItemAddedInCart(String productName) {
		boolean productPresence = cartItems.stream()
				.anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return productPresence;
	}
	
	public CheckoutPage checkout() {
		waitTillElementClickable(checkout);
		scrollToDown();
		javaScriptForceClick(checkout);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
	
	 // force click

}
