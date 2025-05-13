package CompleteSeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import CompleteSeleniumFramework.Pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		// This is one browser , so what you can do is store a global prperty file where
		// you can chhose which browser to use

		// if you set global property to chrome it will run the chrome settings
		// if you do firefox it will run for firefox
		// if you do edge it will run for edge

		// There ia a class in java called properties that can read global properties
		Properties prop = new Properties();
		// The path is so long and when you give this project to someone they wont have
		// this path and this will fail hence you
		// C:\\Users\\miran\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\CompleteSeleniumFramework\\resources\\GlobalData.properties
		// can dynamically generate the path
		// before the src in the path you can give System.getProperty("user.dir")
		//
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\CompleteSeleniumFramework\\resources\\GlobalData.properties");
		prop.load(fis);
		// this is to read the browser value given through cmd maven commands

		// using ternary operator that says if condition !=null do this : else do this
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			// for the headless mode we are writing the following line
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,990));//run in fullscreen
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// For fileutils you have to add the follwoing dependency in your pom.xml file
		// https://mvnrepository.com/artifact/commons-io/commons-io
		//
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});// result={map,map1}

		return data;

	}

	public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {

		// The driver has no life it just pointing to WebDriver driver hence we can use
		// listeners
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png");
		FileUtils.copyFile(source, file);

		// String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);

		// Another way is converting the source screenshot instead of taking another one
		/*
		 * byte[] fileContent = FileUtils.readFileToByteArray(file); String
		 * base64Screenshot = Base64.getEncoder().encodeToString(fileContent);
		 * 
		 * return new String[] {filePath,base64Screenshot};
		 */

		return System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png";
	}

	// Extent Reports - html reports

	// The before and after method should not be given a specific group as tommorow
	// you have another group then you will have to keep adding or updating instead
	// you use (alwaysrun=true)

	@BeforeMethod(alwaysRun = true)

	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
