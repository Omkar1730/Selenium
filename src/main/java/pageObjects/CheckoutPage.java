package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".list-group span")
	List<WebElement> countries;
	
	@FindBy(className="action__submit")
	WebElement submitOrder;
	
	By selectCountry = By.cssSelector("[placeholder='Select Country']");
	
	By listCountries = By.cssSelector(".list-group");
	
	public ConfirmationPage addCountryInCheckout(String strToPass, String countryToSelect) {
		waitTillElementVisible(selectCountry);
		country.sendKeys(strToPass);
		waitTillElementVisible(listCountries);
		selectingCountry(countryToSelect);
		submitOrder.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	
	public void selectingCountry(String countryToSelect) {
		countries.stream()
	    .filter(c -> c.getText().trim().equalsIgnoreCase(countryToSelect))
	    .findFirst()
	    .orElseThrow(() -> new RuntimeException("Country not found"))
	    .click();	
	}
	
}
