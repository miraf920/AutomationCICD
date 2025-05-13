package CompleteSeleniumFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	//You will need the object of this method henece making it static would help us retrieve it from anywhere
	//you wont have to create an object for this class because of the static keyword
	
	public static ExtentReports getReportObject() {
	    String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");	
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Mir");
		
		//This step attaches the test method to the reports but there could be 1000 of testcases hence we do not go and write it in every testcase ins
		//instead we write it in testNG listeners
		return extent;
	}

}
