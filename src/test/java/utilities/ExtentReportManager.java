package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;//responsible for UI part
	public ExtentReports extent;// contains common info about the project and reports
	public ExtentTest  test;// responsible for all the entries such as passed, skipped and failed.
	
	String repName;
	
	public void onStart(ITestContext context) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter("./reports/"+repName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);//now extent is allowed to access all the tests assigned with sparkReporter object as it is the report assigner
	
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		//context.getCurrentXmlTest().getParameter("os"); this method will open the current .xml file and read the parameter we suggest 
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OS", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
	
		try {
		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
			if(!groups.isEmpty()) {
				extent.setSystemInfo("Groups", groups.toString());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(com.aventstack.extentreports.Status.PASS, result.getName()+" - Test case passed...");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(com.aventstack.extentreports.Status.FAIL, result.getName()+" - Test case failed...");
		test.log(com.aventstack.extentreports.Status.FAIL, result.getThrowable().getMessage());
		
		try{
			String imgPath=new BaseClass().captureScreenShot(result.getClass().getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e) {
			//e.getMessage();
			e.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(com.aventstack.extentreports.Status.SKIP, "Test case skipped is : "+result.getName());
		test.log(com.aventstack.extentreports.Status.SKIP, result.getThrowable().getMessage());

	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String extentReportPath=System.getProperty("user.dir")+"\\reports\\"+repName;
		File file = new File(extentReportPath);
		
		try {
			Desktop.getDesktop().browse(file.toURI());//this method opens the extent report automatically without user interaction
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*check out this later
		 * find more email snippets at https://commons.apache.org/proper/commons-email/userguide.html
		try {
			URL url = new URL("file:////"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			//create the email message
			// Create the attachment
			  EmailAttachment attachment = new EmailAttachment();
			  attachment.setPath("mypictures/john.jpg");//path of the attachment you want to send
			  attachment.setDisposition(EmailAttachment.ATTACHMENT);
			  attachment.setDescription("Picture of John");
			  attachment.setName("John");

			  // Create the email message
			  MultiPartEmail email = new MultiPartEmail();
			  email.setHostName("mail.myserver.com");
			  email.addTo("jdoe@somewhere.org", "John Doe");//distribution list email - every team member will have this email
			  email.setFrom("name@org.com", "Me");//sender Email 
			  email.setSubject("The picture");// msg you want to send...
			  email.setMsg("Here is the picture you wanted");//msg you want to send...

			  // add the attachment
			  email.attach(attachment);

			  // send the email
			  email.send();
			
		} catch (MalformedURLException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	
	}


}
