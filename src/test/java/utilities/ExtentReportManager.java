package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populates common info on the report
	public ExtentTest test; //creating test case entries in the report and update status of the test methods.
	
	String repName;
	
	public void onStart(ITestContext context) {
		
//	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//	Date dt= new Date();
//	String currentdatetimestamp =df.format(dt);
		
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	repName = "Test-Report-"+timeStamp+".html";
	sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //specify location of report
	
	sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //Title of report
	sparkReporter.config().setReportName("OpenCart Functional Testing"); //name of the report
	sparkReporter.config().setTheme(Theme.STANDARD);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	
	extent.setSystemInfo("Application", "OpenCart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	
	String os = context.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);
	
	String browser = context.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);;
	
	List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
	if(!includeGroups.isEmpty()) {
		extent.setSystemInfo("Groups",includeGroups.toString());
	}
	
	
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //create a new entry in the report
		test.log(Status.PASS, result.getName()+" got successfully executed."); //update status p/f/s/
	}
	
	public void onTestFailure (ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}
		catch (Exception e1){
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped (ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP,"Test case Skipped is :" + result.getName());
	}
	
	public void onFinish (ITestContext context) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			@SuppressWarnings("deprecation")
//			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
//			
//			//Create the email message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("sbietw@gmail.com", "ivmhafgwvdvveawb"));
//			email.setSSLOnConnect(true);
//			email.setFrom("sbietw@gmail.com"); //Sender
//			email.setSubject("Test Result");
//			email.setMsg("Please find Attached Report.....");
//			email.addTo("digitalrajput007@gmail.com");
//			email.attach(url, "extent report", "please check report...");
//			email.send(); //send the email
//		}
//		 catch (Exception e) {
//			 e.printStackTrace();
//		 }
	}
	
	}
	
