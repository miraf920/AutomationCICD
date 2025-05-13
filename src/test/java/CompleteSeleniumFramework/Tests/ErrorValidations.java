package CompleteSeleniumFramework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import CompleteSeleniumFramework.Pageobjects.CartPage;
import CompleteSeleniumFramework.Pageobjects.ProductCatalouge;
import CompleteSeleniumFramework.TestComponents.BaseTest;
import CompleteSeleniumFramework.TestComponents.Retry;

public class ErrorValidations extends BaseTest {
//retryAnalyzer=Retry.class
	@Test(groups = {"ErrorHandling"})
	public void logInErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("12Ronald_Joe@hotmail.com", "Qwer123$$");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	
	@Test 
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalouge productCatalouge = landingPage.loginApplication("abty@gmail.com", "Qwer123$$");

		// ProductCatolouge Page
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProducttoCart(productName);
		CartPage cartPage = productCatalouge.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("zara product 33");
		Assert.assertFalse(match);

}
}
