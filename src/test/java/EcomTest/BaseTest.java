package EcomTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.*;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;
import pageObjects.LoginPage;

public class BaseTest {

	public String productName = "ZARA COAT 3";
	public WebDriver driver;
	public LoginPage login;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null
				? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			applyHeadlessConfig(browserName, options);
			driver = new ChromeDriver(options);

		} else if (browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			applyHeadlessConfig(browserName, options);
			driver = new EdgeDriver(options);

		} else if (browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			applyHeadlessConfig(browserName, options);
			driver = new FirefoxDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if (!browserName.contains("headless")) {
			driver.manage().window().maximize();
		}

		return driver;
	}

	private void applyHeadlessConfig(String browserName, ChromeOptions options) {
		if (browserName.contains("headless")) {
			options.addArguments("--headless=new", "--window-size=1440,900");
		}
	}

	private void applyHeadlessConfig(String browserName, EdgeOptions options) {
		if (browserName.contains("headless")) {
			options.addArguments("--headless=new", "--window-size=1440,900");
		}
	}

	private void applyHeadlessConfig(String browserName, FirefoxOptions options) {
		if (browserName.contains("headless")) {
			options.addArguments("--headless", "--width=1440", "--height=900");
		}
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() throws IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.goTo();
		return login;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String captureScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png";
		FileUtils.copyFile(srcFile, new File(destPath));
		return destPath;
	}
}