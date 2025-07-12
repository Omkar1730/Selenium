package EcomTest;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginPageTest extends BaseTest {
	
	@Test (groups= {"Smoke"})
	public void loginFailure()
	{
		login.loginTest("omkarp800@gmail.com", "Omkar5137");
		String error=login.getErrorMessage();
		Assert.assertTrue(error.equalsIgnoreCase("Incorrect email or password."));
	}
}
