package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	//constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement h1myAccountMsg;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement linkLogout;
	
	
	//ActionMethods
	
	public boolean validateConfirmationMsg() {
		try {
		return(h1myAccountMsg.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		linkLogout.click();
	}
}
