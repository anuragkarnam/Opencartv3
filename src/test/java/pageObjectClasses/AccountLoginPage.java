package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLoginPage extends BasePage{

	//constructor
	public AccountLoginPage(WebDriver driver) {
		super(driver);
	}

	//locators

	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;

	//action methods
	
	public void setEmailAddress(String emailID) {
		txtEmailAddress.sendKeys(emailID);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		btnLogin.click();
	}
	
}
