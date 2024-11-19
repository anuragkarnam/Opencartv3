package pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {

	//constructor
	public AccountRegisterPage(WebDriver driver) {
		super(driver);
	}

	//locators


	@FindBy(xpath="//h1[normalize-space()='Register Account']")
	WebElement h1displayText;

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath="//label[@class='radio-inline']//input[@value='1']")
	WebElement chkSubscribeYes;

	@FindBy(xpath="//label[@class='radio-inline']//input[@value='0']")
	WebElement chkSubscribeNo;

	//xpath="//div[@class='pull-right']"

	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;


	//action methods

	public void setFirstname(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastname(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String emailID) {
		txtEmail.sendKeys(emailID);
	}

	public void setTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void clickPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();
		//or
		/*btnContinue.submit();
		//or
		Actions act = new Actions(driver);
		act.moveToElement(btnContinue).click().build().perform();
		//or
		btnContinue.sendKeys(Keys.RETURN);
		//or
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();	
		 */
	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
}
