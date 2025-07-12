package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.AbstractComponents;

public class LoginPage extends AbstractComponents{

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPass;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public HomePage loginTest(String user, String password) {
		
		userName.sendKeys(user);
		userPass.sendKeys(password);
		submit.click();
		waitTillElementVisible(toastMessage);
		HomePage homepage = new HomePage(driver);
		return homepage;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitTillElementVisible(errorMessage);
		return errorMessage.getText();
	}
	
}
