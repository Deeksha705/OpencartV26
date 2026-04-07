package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {
	public WebDriver driver;
	public Properties p;
	public Logger logger; // log4j
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		// Loading config.properties File
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		default : System.out.println("Invalid Browser"); return;
		
		}
		
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Assuming 10 based on common practice

	    //driver.get("https://tutorialsninja.com/demo/");
	    driver.get(p.getProperty("appURL")); // reading value or URL from properties file
	    driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown()
	{
	    driver.quit();
	}
	
	  public String randomString()
	    {
	    	String generatedstring = RandomStringUtils.randomAlphabetic(5);
	    	return generatedstring;
	    }

	   public String randomNumber()
	   {
	   	String generatednumber = RandomStringUtils.randomNumeric(10);
	   	return generatednumber;
	   }
	   
	   public String randomAlphaNumeric()
	   {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
	   	String generatednumber = RandomStringUtils.randomNumeric(3);
	   	return (generatednumber+"@"+generatedstring);
	   }
     
	   public String captureScreen(String tname) throws IOException {
		    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		            
		    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		    
		    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		    File targetFile = new File(targetFilePath);
		    
		    sourceFile.renameTo(targetFile);
		    return targetFilePath;
		}


}
