package CompleteSeleniumFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CompleteSeleniumFramework.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	WebDriver driver;

	// before you touch anything in class the constructor is the first to be
	// executed
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// to call the find element methods via the //PageFactory Design Pattern
		PageFactory.initElements(driver, this);

	}
	
	

	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> orderList;
	
	@FindBy(css= ".totalRow button")
	WebElement checkout_Button;
	
	
public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match=  orderList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	
	
}

