package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws Exception {

		FileReader file = new FileReader("./src//test//resources//config.properties");//reading of properties file
		p= new Properties();
		p.load(file);//loading the properties file

		logger = LogManager.getLogger(this.getClass()); //log4j2

		if(p.getProperty("environment_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("No matching os found...!");
				return;
			}

			switch(br.toLowerCase()) {
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("Firefox"); break;
			default : System.out.println("Mo matiching browser found....!"); return; 
			}
			URL url = new URL("http://localhost:4444/wd/hub");//http://localhost:4444/wd/hub
			driver = new RemoteWebDriver(url,cap);

		}

		if(p.getProperty("environment_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid Browser....try again!"); return; 
			}
		}
 
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL1"));//accessing the url from properties file
	}

	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		driver.quit();
	}


	public String randomString() {
		@SuppressWarnings("deprecation")
		String randomGeneratedString=RandomStringUtils.randomAlphabetic(5);
		return randomGeneratedString;
	}

	public String randomNumber() {
		@SuppressWarnings("deprecation")
		String randomGeneratedNumber=RandomStringUtils.randomNumeric(10);
		return randomGeneratedNumber;		
	}

	public String randomAlphaNumeric() {
		@SuppressWarnings("deprecation")
		String randomString=RandomStringUtils.randomAlphabetic(3);
		@SuppressWarnings("deprecation")
		String randomNumber=RandomStringUtils.randomNumeric(3);
		return(randomString+"@"+randomNumber);
	}


	public String captureScreenShot(String tname) throws IOException {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takeScreenShot = (TakesScreenshot)driver; //casting driver with TakesScreenshot , we are assigning driver to TakesScreenShot interface;
		File sourceFile=takeScreenShot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp;
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
