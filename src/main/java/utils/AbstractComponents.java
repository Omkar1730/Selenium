package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrdersHistoryPage;

public class AbstractComponents {
	
	public OrdersHistoryPage orderHistory;
	WebDriver driver;
	WebDriverWait wait;
	Wait<WebDriver> fwait;
	JavascriptExecutor js;
	
	protected By toastMessage= By.cssSelector("#toast-container");
	public By cartButton=By.cssSelector("[routerlink*='cart']");
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;
	
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		this.fwait= new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(ElementClickInterceptedException.class)
				.ignoring(NoSuchElementException.class);
		
		this.js = (JavascriptExecutor) driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void waitTillElementVisible(By eleToWaitFor) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(eleToWaitFor));
	}
	public void waitTillElementVisible(WebElement eleToWaitFor) {		
		wait.until(ExpectedConditions.visibilityOf(eleToWaitFor));
	}
	public void waitTillElementDisappear(WebElement eleToDisappear) {		
		wait.until(ExpectedConditions.invisibilityOf(eleToDisappear));
	}
	
	public void waitTillElementClickable(WebElement eleToBeClickable) {		
		wait.until(ExpectedConditions.elementToBeClickable(eleToBeClickable));
	}
	
	public void clickElementUntilClickable(By eleToClick) {
		WebElement ele = fwait.until(driver1 -> {
		    WebElement element = driver1.findElement(eleToClick);
		    element.click(); // Try clicking here
		    return element; // Return if click succeeds
		});
	}
	
	public void scrollToDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void javaScriptForceClick(WebElement ele) {
		js.executeScript("arguments[0].click();", ele);
	}
	
	public OrdersHistoryPage navigateToOrderDetails() {
		waitTillElementVisible(ordersButton);
		ordersButton.click();
		orderHistory = new OrdersHistoryPage(driver);
		return orderHistory;
	}
	
	public String captureScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcfile= ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir"+"//reports"+testCaseName+".png")));
		return System.getProperty("user.dir"+"//reports"+testCaseName+".png");
	}
	
}
