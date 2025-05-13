package CompleteSeleniumFramework.Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CompleteSeleniumFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	// before you touch anything in class the constructor is the first to be
	// executed
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// to call the find element methods via the //PageFactory Design Pattern
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login_Button;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement error_Message;

	public ProductCatalouge loginApplication(String Email, String Password) {
		userEmail.sendKeys(Email);
		userPassword.sendKeys(Password);
		login_Button.click();
		//you know you are landing on  product Catalouge page when you are clicking on the login button above
		// so we can do the new creation inside the mrthod only
		
		return new ProductCatalouge(driver);

	}
	
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(error_Message);
		return error_Message.getText();
	}

	public void goTo() {
		//here you can specify url of any enviroment  qa/dev/uat
		driver.get("https://rahulshettyacademy.com/client/");

	}
	
	

}
