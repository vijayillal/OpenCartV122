package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test (groups= {"Sanity", "Master"})
	public void verify_Login()
	{
		logger.info("**** TC002 Login_Test Started");
		try {
		// home page
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// login page
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage macc= new MyAccountPage(driver);
		
		//Assert.assertTrue(macc.isMyAccountPageExists());
		
		boolean targetpage=macc.isMyAccountPageExists();
		Assert.assertTrue(targetpage);
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*** TC002 Login_Test Ends");
	}
	
	
}
