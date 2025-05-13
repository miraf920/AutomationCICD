package CompleteSeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CompleteSeleniumFramework.Pageobjects.CartPage;
import CompleteSeleniumFramework.Pageobjects.OrdersPage;

//It will have all the reusable components

public class AbstractComponents {
	
	
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orders;
	
	//WebDriver wait is being used again and again henece we write it in abstract utilities which will be inherited by all children
    //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	//It is taking 4 seconds to resume the clicking of cart function
			//because there was hidden spinner that the script was waiting fot it as well to get invisible so we can use thread .sleep instaed of spinner to get invisible
	
	public void waitForElementToDisappear(WebElement findBy) throws InterruptedException {
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	//	wait.until(ExpectedConditions.invisibilityOf(findBy));

	}
	
	
	public  CartPage goToCartPage() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	

	public  OrdersPage goToOrderPage() {
		orders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
