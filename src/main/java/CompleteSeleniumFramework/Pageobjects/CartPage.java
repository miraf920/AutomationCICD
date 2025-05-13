package CompleteSeleniumFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CompleteSeleniumFramework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	// before you touch anything in class the constructor is the first to be
	// executed
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// to call the find element methods via the //PageFactory Design Pattern
		PageFactory.initElements(driver, this);

	}
	
	
	/* List <WebElement> boughtProductList = driver.findElements(By.cssSelector(".cartSection h3"));
	   
	  Boolean match=  boughtProductList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	
	  Assert.assertTrue(match);
	  
	  driver.findElement(By.cssSelector(".totalRow button")).click();*/
	
	@FindBy(css=".cartSection h3")
	List<WebElement> boughtProductList;
	
	@FindBy(css= ".totalRow button")
	WebElement checkout_Button;
	
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match=  boughtProductList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	public CheckoutPage goToCheckOut() {
		
		
		checkout_Button.click();
		CheckoutPage checkOutPage = new CheckoutPage(driver);
		return checkOutPage;
	}
	
	
	
}

