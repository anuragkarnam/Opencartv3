package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectClasses.AccountLoginPage;
import pageObjectClasses.HomePage;
import pageObjectClasses.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_AccountLogin_DDT_Test extends BaseClass {

	@Test(dataProvider ="LoginData" , dataProviderClass=DataProviders.class, groups="DataDriven")
	public void loginTest_DDT(String emailID, String password, String exp_result) {
		logger.info("*****************************");
		logger.info(getClass().toString()+"*****loginTest_DDT test has started...****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("login link clicked...");

			AccountLoginPage alp = new AccountLoginPage(driver);
			alp.setEmailAddress(emailID);
			alp.setPassword(password);
			logger.info("login details passed and login button clicked...");
			alp.clickLoginBtn();

			MyAccountPage  myp = new MyAccountPage(driver);
			boolean expectedMsg = myp.validateConfirmationMsg();

			//test validation
			//conditions
			/*
			 * 1) valid data > validationmsg passed login > test passed > logout
			 * 2) invalid data > validationmsg failed > test passed
			 * 3) valid data > validationmsg failed login failed> test failed
			 * 4) invalid data > validationmsg passed login > test failed > logout
			 */

			if(exp_result.equalsIgnoreCase("Valid")) {
				if(expectedMsg==true) {
					logger.info("test passed...");
					myp.clickLogout();
					Assert.assertTrue(true);
					logger.info("account logged out...");
				}
				else if (expectedMsg==false){
					logger.info("test failed...");
					Assert.assertTrue(false);
				}

			}
			if(exp_result.equalsIgnoreCase("Invalid")) {
				if(expectedMsg==true) {
					logger.info("test failed...");
					myp.clickLogout();
					Assert.assertTrue(false);
				}
				else if(expectedMsg==false) {	
					logger.info("test passed...");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*****test finished...*****");

	}
}
