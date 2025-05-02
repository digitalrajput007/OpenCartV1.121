package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	
	
	@Test (dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verify_loginDDT (String email, String pwd, String exp) {
		
		logger.info("****Started TC003_LoginDDT*****");
//		Homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clkLogin();
		
		//MyAccount
		MyAccountPage map = new MyAccountPage(driver);
		boolean status = map.isMyAccountPageExist();
		
		if (exp.equalsIgnoreCase("Valid")) {
			if (status==true) {
				map.logoutbutton();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (status==true) {
				map.logoutbutton();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(status);
			}
		}
		logger.info("****Finished TC003_LoginDDT*****");
		
	}

}
