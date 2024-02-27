package com.entrata.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import com.entrata.utilities.CommonActionKeywords;
import com.entrata.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static String browser;
	public static String Module;
	public static WebElement element;
	public static String TestCaseType;

	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static SoftAssert assertion = new SoftAssert();
	public static FileInputStream fis;
	public WebDriverWait wait;

	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(

			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");

	public static CommonActionKeywords ak = new CommonActionKeywords();

	public void setUp(String TestURL) {

		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Config file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			log.debug("OR file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Jenkins Browser filter configuration
		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

			browser = System.getenv("browser");
		} else {

			browser = config.getProperty("browser");

		}

		config.setProperty("browser", browser);

		if (config.getProperty("browser").equals("firefox")) {

			// System.setProperty("webdriver.gecko.driver", "gecko.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("chrome")) {

//			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");  

			ChromeOptions opt = new ChromeOptions();

//			opt.addExtensions(new File("D:\\HDFC_Automation\\CRM_Automation_Azure\\HDFC_Bank\\Automation\\CRM\\drivers\\extension_1_6_2_0.crx"));
			opt.addArguments("--remote-allow-origins=*");

			// opt.setBrowserVersion("stable");
			opt.addArguments("--window-size=1920,1080");

			// opt.addArguments("--headless");
			// opt.setAcceptInsecureCerts(true);

			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);

			log.debug("Chrome Launched !!!");

		} else if (config.getProperty("browser").equals("ie")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		driver.get(TestURL);
		log.debug("Navigated to : " + TestURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
		log.debug("test execution completed !!!");

	}

}
