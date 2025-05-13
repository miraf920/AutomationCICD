package CompleteSeleniumFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import CompleteSeleniumFramework.Pageobjects.CartPage;
import CompleteSeleniumFramework.Pageobjects.CheckoutPage;
import CompleteSeleniumFramework.Pageobjects.ConfirmationPage;
import CompleteSeleniumFramework.Pageobjects.LandingPage;
import CompleteSeleniumFramework.Pageobjects.OrdersPage;
import CompleteSeleniumFramework.Pageobjects.ProductCatalouge;
import CompleteSeleniumFramework.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName="ZARA COAT 3";
	
	//Submitting orders
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {	
		
		
//		we will not add it here instead we will be adding it in the listeners
//		String path = System.getProperty("user.dir")+"\\ExtentReports\\index.html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("Web Automation Results");
//		reporter.config().setDocumentTitle("Test Results");
//		
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
//		test = extent.setSystemInfo("Tester", "Mir");
		
		
		
		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"),input.get("password"));

		// ProductCatolouge Page
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProducttoCart(input.get("productName"));
		CartPage cartPage = productCatalouge.goToCartPage();

		// CartPage
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		// Validations cannot go in page object files, the page object files should only
		// have the code to perform the task
		Assert.assertTrue(match);
		CheckoutPage checkOutPage = cartPage.goToCheckOut();

		// For everyPage we are creating an object
		// there is a smart way to handle it

		// CheckOutPage
		checkOutPage.selectCountry(input.get("country"));
		ConfirmationPage confirmationpage = checkOutPage.submitOrder();

		// Confirmation Message
		String confirmationMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
	}
	
	
	//to verify if zara coat3 is displaying in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalouge productCatalouge = landingPage.loginApplication("Ronald_Joe@hotmail.com", "Qwer123$$");
		OrdersPage ordersPage= productCatalouge.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	
	
	
	/*Regular Method where we are hard coding the values
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"Ronald_Joe@hotmail.com","Qwer123$$","ZARA COAT 3","Indonesia"},{"sofhar@gmail.com","Qwer123$$","ADIDAS ORIGINAL","India"}};
	}
	*/
	
	//Second Method where we are using HashMaps
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email","Ronald_Joe@hotmail.com");
		map.put("password", "Qwer123$$");
		map.put("productName","ZARA COAT 3");
		map.put("country", "Indonesia");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email","sofhar@gmail.com");
		map1.put("password", "Qwer123$$");
		map1.put("productName","ADIDAS ORIGINAL");
		map1.put("country", "India");
		return new Object[][] {{map},{map1}};
	}*/
	
	//Third Method where we are bring data via Json
	//The other way is to put the method in the base test so it will inherit the methods without creating the objects
	
	
	@DataProvider
	public Object[][] getData() throws IOException{ 
		//one way is to create an object of the dataReader.java class and calling it 
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\CompleteSeleniumFramework\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	
	
	 

	

}
