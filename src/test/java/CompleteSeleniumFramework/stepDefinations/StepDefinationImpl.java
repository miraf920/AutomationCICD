package CompleteSeleniumFramework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import CompleteSeleniumFramework.Pageobjects.CartPage;
import CompleteSeleniumFramework.Pageobjects.CheckoutPage;
import CompleteSeleniumFramework.Pageobjects.ConfirmationPage;
import CompleteSeleniumFramework.Pageobjects.LandingPage;
import CompleteSeleniumFramework.Pageobjects.ProductCatalouge;
import CompleteSeleniumFramework.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public ConfirmationPage confirmationpage;

	@Given("I landed on Ecommerece Page")
	public void  I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingPage = launchApplication();	
	}
	
	//you cannot write any parameteer here so you write (.+) instead of the parameteer
	//if you are writing regular expression it starts with^ and ends with$
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) 
	{
		//we are getting data from feature file
		productCatalouge = landingPage.loginApplication(username,password);	
	}
	
	@When("^I add a product (.+) from Cart$")
	public void I_add_a_product_from_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProducttoCart(productName);
		
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName)
	{
		
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkOutPage = cartPage.goToCheckOut();

		// For everyPage we are creating an object
		// there is a smart way to handle it

		// CheckOutPage
		checkOutPage.selectCountry("india");
		confirmationpage = checkOutPage.submitOrder();
		
	}
	
	//if you already know the value is sitting in string instead of examples then
    @Then( "{string} message is diaplyed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string)
    {
    	String confirmationMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.quit();
    	
    }
    
    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.quit();
    }

	
	
}
