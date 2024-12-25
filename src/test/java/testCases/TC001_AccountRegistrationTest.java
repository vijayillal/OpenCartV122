package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{

	@Test (groups= {"Regression", "Master"})
	public void test_Account_Registration()
	{
		logger.info("**** TC001_Account Registration Test Started *****");
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details");
		
		regpage.SetFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setCheckedPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("test failed");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("\"**** Finished TC001_Account Registration Test Finished *****\"");
	}
	
	
	
}
