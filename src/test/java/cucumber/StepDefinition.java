package cucumber;

import EcomTest.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pageObjects.*;

import java.io.IOException;

public class StepDefinition extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public CartPage cartpage;
    public ConfirmationPage confirmationPage;
    String country = "India";

    @Given("I landed on the Ecom page")
    public void I_landed_on_the_Ecom_page() throws IOException {
       loginPage= launchApp();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void Login_with_Username_Password(String username, String password){
        homePage = loginPage.loginTest(username,password);
    }
    @When("^I add a product (.+) to cart$")
    public void add_product_to_cart(String productName){
        homePage.addItemToCart(productName);
        cartpage=homePage.navigateToCart();
        boolean itemPresent = cartpage.verifyItemAddedInCart(productName);
        Assert.assertTrue(itemPresent);
    }
    @When("^checkout and Submit the order$")
    public void checkout_submit_order(){
        CheckoutPage checkout=cartpage.checkout();
        confirmationPage=checkout.addCountryInCheckout(country.substring(0, 3), country);
    }

    @Then("{string} message is displayed on the confirmation page")
    public void confirmation_message(String string){
        String confirmationMessage=confirmationPage.getConfirmationMessage();
        System.out.println(confirmationMessage);
        Assert.assertEquals(confirmationMessage, string);
        driver.close();
    }
}
