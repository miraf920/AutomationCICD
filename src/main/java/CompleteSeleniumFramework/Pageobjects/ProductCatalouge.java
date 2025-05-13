package CompleteSeleniumFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CompleteSeleniumFramework.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents{

	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// to call the find element methods via the //PageFactory Design Pattern
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> itemList = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> itemList;
	
	@FindBy(css=".ng-animating")
	WebElement Loader;
	
	
	By productsBy= By.cssSelector(".mb-3");
	
	By toastContainer = By.id("toast-container");
	
	
	//ActionMethods
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return itemList;
	}
	
	//Stream will help you iterate through all elements
	//we added findfirst() so that in case there are multiple products with anme zara coat 3 it returns the first one.
	//orElse(null)==if element is not found it will then return null
	public WebElement getProductByName(String ProductName) {
		WebElement requiredProduct= getProductList().stream().filter(s->
		s.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return requiredProduct;
		
	}
	
	
	/*Wait for the product added to cart message
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	
	
	//We also have to wait that the circle that depicts loading disappears as well before proceeding
	//you can ask the developer the locater used for loading as it disappears quickly
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animated")));//This is taking so much time -performnace issue
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));*/
	
	public void addProducttoCart(String ProductName ) throws InterruptedException {
		WebElement Prod = getProductByName(ProductName);
		Prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(Loader);
		
		
		//It is taking 4 seconds to resume the clicking of cart function
		//because there was hidden spinner that the script was waiting fot it as well to get invisible so we can use thread .sleep instaed of spinner to get invisible
		
		
		//we will not put cart here we will make the header functions in the abstract componenets as it will be in everyppage
		
	}
	


	
	
}
