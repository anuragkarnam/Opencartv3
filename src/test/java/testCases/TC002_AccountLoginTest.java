package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectClasses.AccountLoginPage;
import pageObjectClasses.HomePage;
import pageObjectClasses.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass {

	@Test(groups={"Sanity", "Master"})
	public void verify_MyAccount_login() {

		try {
			logger.info("**********************************");
			logger.info(getClass().toString()+"****verify_MyAccount_login test has been started...****");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("login link has been cicked...");

			AccountLoginPage alp = new AccountLoginPage(driver);
			alp.setEmailAddress(p.getProperty("email"));
			alp.setPassword(p.getProperty("password"));
			logger.info("email and password fields are filled...");
			alp.clickLoginBtn();
			logger.info("login button clicked...");

			MyAccountPage myp = new MyAccountPage(driver);
			boolean epectedmsg =myp.validateConfirmationMsg();
			//Assert.assertEquals(epectedmsg, true, "Login failed");
			Assert.assertTrue(epectedmsg);
			logger.info(" Validation msg confirmed...");
		}
		catch(Exception e) {
			//Assert.assertTrue(false);
			Assert.fail();
		}

		logger.info("*****Test execution finished...*****");
	}


}
