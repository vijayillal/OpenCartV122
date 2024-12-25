package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_UserName;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_Password;
	@FindBy(xpath="//input[@value='Login']") WebElement btn_Login;
	
	public void setUserName(String user)
	{
		txt_UserName.sendKeys(user);
	}
	
	public void setPassword(String pwd)
	{
		txt_Password.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btn_Login.click();
	}
	
	
	
	
	

}