package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test (groups= {"Sanity","Master"})
	public void verifyAccountRegistration() {
		logger.info("*****Starting TC001_AccountRegistrationTest****");
		HomePage hp = new HomePage(driver);
				
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount");
		hp.clickRegister();
		logger.info("Clicked on Register");
		AccountRegistrationPage rpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		rpage.setFirstName(randomString().toUpperCase());
		rpage.setLastName(randomString().toUpperCase());
		rpage.setEmail(randomString().toLowerCase()+"@gmail.com");
		String pass = randompnum();
		rpage.setphone(pass);
		rpage.pwd(pass);
		rpage.confirmpwd(pass);
		rpage.privacy();
		rpage.submit();
		
		logger.info("Validating expected message");
		Assert.assertEquals(rpage.Message(), "Your Account Has Been Created!");
		
		hp.clickMyAccount();
		rpage.myLogout();
		}
	}

