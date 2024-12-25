package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver)
	{
		super (driver);
	}
	
	
	@FindBy(xpath="//a[@title='My Account']") WebElement link_myAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement link_register;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement link_login;
	
	
	
	public void clickMyAccount()
	{
		link_myAccount.click();
	}
	
	public void clickRegister()
	{
		link_register.click();
	}
	
	public void clickLogin()
	{
		link_login.click();
	}
	
}
