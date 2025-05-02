package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test (groups= {"Regression","Master"})
	public void verify_Login() {
		
		logger.info("****Starting TC002_LoginTest***");
		
	
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clkLogin();
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean status = map.isMyAccountPageExist();
		Assert.assertEquals(status, true, "Login failed..");
		
		logger.info("****Login finished****");
		map.logoutbutton();
	}
}
