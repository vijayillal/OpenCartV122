package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;  //log4j
import org.apache.logging.log4j.Logger;  //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver; // static because refer 8.4 step in framework document print
public Logger logger;  //log4j
public Properties p;
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		// loading config.properties file
		//FileInputStream file=new FileInputStream(".//src/test/resources/config.properties");
		FileReader file= new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))  // execution environment is remote
		{
			DesiredCapabilities capabilities= new DesiredCapabilities();
			
			// setting up os give the name as it is in master.xml
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching of OS");
				return;
			}
			
			// setting up browse
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			case "edge": capabilities.setBrowserName("microsoftedge"); break;
			default : System.out.println("No matching of browser"); return;
			}
			
			// set up driver for remote
			driver= new RemoteWebDriver(new URL("http://192.168.29.69:4444/wd/hub"), capabilities);
			
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())   //for local environment setup
			{
			case "chrome": driver= new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			case "firefox": driver= new FirefoxDriver(); break;
			default: System.out.println("invalid browser name"); return;
			
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		
		driver.get(p.getProperty("appURL1"));  //reading properties file from config.properties
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		 String geneartedString=RandomStringUtils.randomAlphabetic(5);
		return geneartedString;
	}
	
	public String randomNumber()
	{
		String geneartedNumber=RandomStringUtils.randomNumeric(10);
		return geneartedNumber;
		
	}
	
	
	public String randomAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return (generatedString+generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timestamp= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		TakesScreenshot takesscreenshot= (TakesScreenshot) driver;
		File sourcefile= takesscreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath=System.getProperty("user.dir") +"\\screenshots\\" + tname + "_ " + timestamp + ".png";
		File targetfile= new File(targetfilepath);
		
		sourcefile.renameTo(targetfile);
		return targetfilepath;
		
		
	}
	
	
}
