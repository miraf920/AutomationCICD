package CompleteSeleniumFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CompleteSeleniumFramework.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;
	
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	/*	  
	 // you can use this as weed"
	  //driver.findElement(By.cssSelector("form-group")).sendKeys("in);
	  

	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
	  
	  Actions action = new Actions(driver);
	  
	  action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item span")));
	  
	  
	  List <WebElement> .ta-item span = driver.findElements(By.cssSelector(".ta-item span"));
	  
	 WebElement country_needed= list.stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
	 
	 country_needed.click();
	 
	 
	 driver.findElement(By.cssSelector(".action__submit ")).click();*/
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country_Dropdown;
	
	
	By countrySuggestionListBox = By.cssSelector(".ta-item span");
	
	@FindBy(css=".ta-item span")
	List<WebElement> SuggestionList;
	
	@FindBy(css =".action__submit")
	WebElement submitOrderButton;
	
	
	public void selectCountry(String CountryName) {
		  Actions action = new Actions(driver);
		  action.sendKeys(country_Dropdown, "ind").build().perform();
		  waitForElementToAppear(countrySuggestionListBox);
          WebElement country_needed= SuggestionList.stream().filter(s->s.getText().equalsIgnoreCase(CountryName)).findFirst().orElse(null);
          country_needed.click();
          

	}
	
	public ConfirmationPage submitOrder() {
		submitOrderButton.click();
		return new ConfirmationPage(driver);
		
	}
	
	
	
	
	
	

}
