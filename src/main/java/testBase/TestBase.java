package testBase;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import util.DeleteRunner;
import util.JavaPropertiesManager;
import util.Recording;
import util.TestListener;
import util.TestUtil;
import util.zapiObjects.ProjectCycles;

@Listeners(value = TestListener.class) // TestNG Listener

public class TestBase {

	final static Logger logger = Logger.getLogger(TestBase.class);

	/*
	 * int counter=0; int retrylimit=2;
	 */

	public static WebDriver driver;
	public static WebDriver driver2;
	public static TestUtil testUtil;
	public static String isJenkinRun;
	public static String headlessMode;

	private static String browser;
	public static String rateQuoteRestrictionUrl;
	public static String url;
	public static String url2;
	public static String url3;
	public static String url4;
	public static String url5;
	public static String url6; 
	public static String efmUrl;
	public static String username1;
	public static String password1;
	public static String username2;
	public static String password2;
	public static String username3;
	public static String password3;
	public static String username4;
	public static String password4;
	public static String username5;
	public static String password5;
	public static String username6;
	public static String password6;
	public static String username7;
	public static String password7;
	public static String username8;
	public static String password8;
	public static String username9;
	public static String password9;
	public static String username10;
	public static String password10;
	public static String username11;
	public static String password11;
	public static String username12;
	public static String password12;
	public static String username13;
	public static String password13;
	public static String username14;
	public static String password14;
	public static String username15;
	public static String password15;
	public static String username16;
	public static String password16;
	public static String username17;
	public static String password17;
	public static String username18;
	public static String password18;
	public static String username19;
	public static String password19;
	public static String username20;
	public static String password20;
	public static String username21;
	public static String password21;
	// api values
	public static String email;
	public static String apiKey;
	public static String cycleName;
	public static String realease;
	public static String projectId;
	public static String secret;
	public String versionId;
	public static String description;
	public static ProjectCycles pc;
	public static ProjectCycles[] cycles;
	public static boolean isSmoke;
	public static String smokeVersion;
	public static boolean jiraReport;

	public static JavaPropertiesManager configProperty;
	public static Properties prop;

	@BeforeClass
	public void beforAllTestStart() {

		BasicConfigurator.configure();
		JavaPropertiesManager configProperty = new JavaPropertiesManager(
				"src/main/resources/configFile/config.properties");
		PropertyConfigurator.configure("src/main/resources/configFile/log4j.properties");

		isJenkinRun = configProperty.readProperty("isJenkin");
		headlessMode = configProperty.readProperty("headlessMode");
		browser = configProperty.readProperty("browserType");
		url = configProperty.readProperty("applicationUrl");
		url2 = configProperty.readProperty("applicationUrl2");
		url3 = configProperty.readProperty("applicationUrl3");
		url4 = configProperty.readProperty("applicationUrl4");
		url5 = configProperty.readProperty("applicationUrl5");
		url6 = configProperty.readProperty("applicationUrl6"); 
		efmUrl = configProperty.readProperty("efmUrl");
		rateQuoteRestrictionUrl = configProperty.readProperty("rateQuoteRestrictionUrl");
		username1 = configProperty.readProperty("userName1");
		password1 = configProperty.readProperty("passWord1");
		username2 = configProperty.readProperty("userName2");
		password2 = configProperty.readProperty("passWord2");
		username3 = configProperty.readProperty("userName3");
		password3 = configProperty.readProperty("passWord3");
		username4 = configProperty.readProperty("userName4");
		password4 = configProperty.readProperty("passWord4");
		username5 = configProperty.readProperty("userName5");
		password5 = configProperty.readProperty("passWord5");
		username6 = configProperty.readProperty("userName6");
		password6 = configProperty.readProperty("passWord6");
		username7 = configProperty.readProperty("userName7");
		password7 = configProperty.readProperty("passWord7");
		username8 = configProperty.readProperty("userName8");
		password8 = configProperty.readProperty("passWord8");
		username9 = configProperty.readProperty("userName9");
		password9 = configProperty.readProperty("passWord9");
		username10 = configProperty.readProperty("userName10");
		password10 = configProperty.readProperty("passWord10");
		username11 = configProperty.readProperty("userName11");
		password11 = configProperty.readProperty("passWord11");
		username12 = configProperty.readProperty("userName12");
		password12 = configProperty.readProperty("passWord12");
		username13 = configProperty.readProperty("userName13");
		password13 = configProperty.readProperty("passWord13");
		username14 = configProperty.readProperty("userName14");
		password14 = configProperty.readProperty("passWord14");
		username15 = configProperty.readProperty("userName15");
		password15 = configProperty.readProperty("passWord15");
		username16 = configProperty.readProperty("userName16");
		password16 = configProperty.readProperty("passWord16");
		username17 = configProperty.readProperty("userName17");
		password17 = configProperty.readProperty("passWord17");
		username18 = configProperty.readProperty("userName18");
		password18 = configProperty.readProperty("passWord18");
		username19 = configProperty.readProperty("userName19");
		password19 = configProperty.readProperty("passWord19");
		username20 = configProperty.readProperty("userName20");
		password20 = configProperty.readProperty("passWord20");
		username21 = configProperty.readProperty("username21");
		password21 = configProperty.readProperty("password21");
		// api values
		// cycleName=configProperty.readProperty("cycleName");//Not using this
		realease = configProperty.readProperty("realease");
		projectId = configProperty.readProperty("projectId");
		email = configProperty.readProperty("email");
		apiKey = configProperty.readProperty("apiKey");
		description = configProperty.readProperty("description");
		jiraReport = Boolean.parseBoolean(configProperty.readProperty("jiraReport"));
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeEachTestStart(Method method) throws Exception {

		DeleteRunner runner = new DeleteRunner();
		runner.deleteFiles(new File(Recording.targetFolder));
		runner.deleteFiles(
				new File(System.getProperty("user.dir") + System.getProperty("file.separetor") + "Test Report"));

		testUtil = new TestUtil(driver);

		if (isJenkinRun.equals("true")) {
			driver = testUtil.startHeadlessChromeDriver();
			logger.info("Headless Browser Is Running On Jenkins");

		} else if (headlessMode.equals("true")) {
			driver = testUtil.startLocalBrowserInHeadlessMode();
			logger.info("Running Local Browser in Headless Mode");
		} else {
			driver = testUtil.startLocalBrowser(browser);
			logger.info("Running Local Browser");
		}

		logger.info(method.getName() + "***************TEST STARTED***************");
	}

	@AfterMethod(alwaysRun = true)
	public void afterEachTestComplete(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {

			logger.info(result.getName() + "********** is FAILED  ***************");
			testUtil.captureScreenshot(result.getName(), "target/images/");

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.info(result.getName() + "********* is PASSED  ***************");

		} else if (result.getStatus() == ITestResult.SKIP) {

			logger.info(result.getName() + "******* is SKIPPED  ***************");

		}
		logger.info(result.getName() + "********** is FINISHED***************");

		driver.close();
	    driver.quit();

		/**
		 *
		 * 
		 * @AfterClass(alwaysRun = true) public void afterTest() throws Exception { if
		 *  (isJenkinRun.equals("false")) { logger.info("The
		 *  browsers quited "); driver.close(); driver.quit(); }
		 *  else { logger.info("The browsers quite "); }
		 */

	}

	public JavaPropertiesManager getJavaPropertiesManager() {
		BasicConfigurator.configure();
		JavaPropertiesManager configProperty = new JavaPropertiesManager(
				"src/main/resources/configFile/config.properties");
		return configProperty;
	}

}
