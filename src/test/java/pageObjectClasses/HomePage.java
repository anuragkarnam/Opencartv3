package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//locators
	@FindBy(xpath="//a[@title='My Account']")
	WebElement	linkMyAccount;	
	
	//or
	
	/*@FindBy(xpath="//span[@class='caret']")
	WebElement drpdMyAccount;
	*/
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement linkLogin;
	
	public void clickMyAccount() {
		linkMyAccount.click();
	}
	
	public void clickRegister() {
		linkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	
}
