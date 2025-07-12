package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import utils.AbstractComponents;

public class HomePage extends AbstractComponents {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By products= By.cssSelector(".mb-3");
	By addToCartButton=By.cssSelector(".card-body button:last-of-type");
	
	
	@FindBy(css=".mb-3")
	List<WebElement> productsList;
	
	@FindBy(css=".ngx-spinner-overlay")
	WebElement spinner;
	
	public void addItemToCart(String productName) {
		waitTillElementVisible(products);
		WebElement prod = productsList.stream()
			    .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
			    .findFirst()
			    .orElseThrow(() -> new RuntimeException("Product not found"));
		prod.findElement(addToCartButton).click();
		waitTillElementVisible(toastMessage);
		waitTillElementDisappear(spinner);
	}
	
	public CartPage navigateToCart() {
		clickElementUntilClickable(cartButton);	
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	

	
}
