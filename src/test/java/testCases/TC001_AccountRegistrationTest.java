package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectClasses.AccountRegisterPage;
import pageObjectClasses.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest  extends BaseClass{
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("******************************");
		logger.info(getClass().toString()+"*****test case has Started...********");
	
		try {
		//page object class object creation 
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount Link...");
		hp.clickRegister();
		logger.info("clicked on Register Link...");
		
		//page object class object creation 
		AccountRegisterPage arp = new AccountRegisterPage(driver);
		logger.info("Setting the info for Account registration...");
		arp.setFirstname(randomString().toUpperCase());
		arp.setLastname(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setTelephone(randomNumber());
		
		String pwd = randomAlphaNumeric();
		
		arp.setPassword(pwd);
		arp.setConfirmPassword(pwd);
		arp.clickPolicy();
		arp.clickContinue();
		
		//Thread.sleep(3000);
		
		//Test-assertion
		logger.info("Validating the confirmation message...");
		String confirmation_msg =arp.getConfirmationMsg();
		
		if(confirmation_msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed...");
			//logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confirmation_msg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info(getClass().toString()+"****** Test case execution Finished...******");
	}
		
}
