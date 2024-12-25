package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*  Data is valid-  login success-test pass-logout
    Data is valid- login fails-test fail
    
    Data is Invalid-login success-test fails-logout
    Data is invalid- login fails-test pass
 */

public class TC003_LoginDDT extends BaseClass{

	@Test (dataProvider="Logindata", dataProviderClass=DataProviders.class, groups="DataDriven")  //getting data providers from different package and class
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("***** Started TC003_Login_Test*******");
		// home page
		try {
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
				
				/*  Data is valid-  login success-test pass-logout
			    Data is valid- login fails-test fail
			    
			    Data is Invalid-login success-test fails-logout
			    Data is invalid- login fails-test pass
			 */
				if(exp.equalsIgnoreCase("valid"))  //if data is valid
				{
					if(targetpage==true)    //login success
					{
						macc.clickLogout();			//log out
						Assert.assertTrue(true);  //test pass
						
					}
					else
					{
						Assert.assertTrue(false);
					}
				}
				
				if(exp.equalsIgnoreCase("invalid"))   //if data is invalid
				{
					if(targetpage==true)     // login success
					{
						macc.clickLogout();    // logout
						Assert.assertTrue(false);    //test fails
					}
					
					else
					{
						Assert.assertTrue(true);   //test passed
					}
				}
				
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC003 Login Test ******");
				
				
	}
}
