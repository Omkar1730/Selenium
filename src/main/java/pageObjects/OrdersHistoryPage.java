package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class OrdersHistoryPage extends AbstractComponents{

	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	public OrdersHistoryPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyProductInOrderHistory(String productName) {
		boolean productPresence = orderedProducts.stream()
				.anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return productPresence;
	}
	
	
}
