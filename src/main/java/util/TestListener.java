package util;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import extentReport.ExtentManager;
import extentReport.ExtentTestManager;
import hudson.FilePath;
import hudson.model.Hudson;
import hudson.model.TopLevelItem;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testBase.TestBase;
import util.jira.Client;
import util.jiraObjects.Version;
import util.zapi.ZapiClient;
import util.zapiObjects.CycleDetails;
import util.zapiObjects.Execution;
import util.zapiObjects.ExecutionDetails;
import util.zapiObjects.ExecutionStatus;
import util.zapiObjects.ExecutionStatusEnum;
import util.zapiObjects.ProjectCycles;

//IRetryAnalyzer, IAnnotationTransformer,
public class TestListener extends TestBase implements ITestListener,

		ISuiteListener {

	public static  String appName;
	public static final String pass = "PASS";
	public static final String fail = "FAIL";
	private Logger logger = LogManager.getLogger(TestListener.class);
	private int counter = 0;
	private int retryLimit = 1;
	private ExtentTest extentTest;
	private ZapiClient zapi = new ZapiClient();
	private Client jiraClient;
	private String issue;
	private String accessKey;
	private String secretKey;
	private String accountId;
	private JiraClient jira;
	private FilePath workspaceFilePath;


	@Override
	public void onTestStart(ITestResult result) {
		deleteOldFiles();
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		//checkIsJenkins();
		checkIsJiraReport(result);
		extentTest = ExtentTestManager.getTest();
	}

	private void checkIsJiraReport(ITestResult result) {
		if (jiraReport) {
			reportToJira(result.getTestContext().getSuite());
			testStartReportToJira(result);
		}
	}



	private void testStartReportToJira(ITestResult result) {
		logger.info(cycleName);
		logger.info(isSmoke);
		//setSmokeTestLevel(result);
		logger.info(pc.versionId + pc.name + pc.id);
		setIssue(result);
		String[] test = new String[1];
		test[0] = issue;
		logger.info(issue);
		try {
			logger.info(pc.id + pc.name);
			logger.info(versionId);

			zapi.addTestToCycle(pc.id, projectId, versionId, test);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setIssue(ITestResult result) {
		Pattern pattern = Pattern.compile("executeQZ_(\\d+)|qz(\\d+)");
		Matcher m = pattern.matcher(result.getMethod().getMethodName());
		if (m.find()) {
			if (m.group(1) != null) {
				issue = m.group(1);

			} else {
				issue = m.group(2);
			}

			issue = "QZ-" + issue;

		} else {
			Assert.fail("IMPROPER METHOD NAME: (Method name should be executeQZ_####)");

		}
	}

	//this is for recording
	private void deleteOldFiles() {
		DeleteRunner runner = new DeleteRunner();
		runner.deleteFiles(new File(Recording.targetFolder));
		runner.deleteFiles(new File(
				System.getProperty("user.dir") + System.getProperty("file.separator") + "TestReport"));
	}


	private ExecutionDetails getExecutionDetails(String status) {

		logger.info(versionId + " " + pc.versionId);
		logger.info(projectId);
		logger.info(pc.id);
		logger.info(pc.name);
		CycleDetails cycleDetails;
		cycleDetails = zapi.getCycle(projectId, String.valueOf(pc.versionId), pc.id);

		Execution execution;
		logger.info("Searching issue");

		logger.info(issue);
		logger.info(Arrays.toString(cycleDetails.searchObjectList));
		List<Execution> executionList = Arrays.stream(cycleDetails.searchObjectList)
				.filter(c -> c.issueKey.equals(issue)).collect(Collectors.toList());
		execution = executionList.get(0);
		logger.info(execution.execution.id);
		execution.execution.status = setStatus(status);

		return execution.execution;


	}

	private ExecutionStatus setStatus(String status) {
		ExecutionStatus executionStatus = new ExecutionStatus();
		executionStatus.id = ExecutionStatusEnum.getStatusCode(status);
		executionStatus.name = ExecutionStatusEnum.StatusCode.valueOf(status).name();
		executionStatus.description = "From Automation Framework";
		return executionStatus;
	}

	//
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out
				.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		if (jiraReport) {
			try {
				ExecutionDetails executionDetails = getExecutionDetails(pass);
				logger.info(executionDetails.executionId);
				boolean check = zapi.updateExecution(executionDetails);
				logger.info(check);
				//the commentted lines are to record and upload attachments to JIRA!!!

	/*			List<File> screenshotFile = getScreenshotFile(testMethodName, pass);
				addAttachmentsToIssue(screenshotFile);
				if(!Boolean.parseBoolean(isJenkinRun)) {
					Recording.endRecording();
					File file=Recording.screenRecorder.getCreatedMovieFiles().stream().sorted(Comparator.comparingLong(File::lastModified)).collect(Collectors.toList()).get(0);
					addAttachmentToIssue(file);
			}

	*/
				//	Recording.endRecording();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "Test Failed!");
		extentTest.log(Status.ERROR, result.getThrowable().toString());
		String testMethodName = result.getName().trim();
		if (jiraReport) {
			ExecutionDetails executionDetails = getExecutionDetails(fail);
			logger.info(executionDetails.id + executionDetails.issueId);
			boolean check = zapi.updateExecution(executionDetails);
			logger.info(check);
		}
		try {
			List<File> screenshotFile = getScreenshotFile(testMethodName, fail);
			//the commentted lines are to record and upload attachments to JIRA!!!

//				screenshotFile.forEach(f->zapi.createAttachment(executionDetails,f,"image/png"));

//				addAttachmentsToIssue(screenshotFile);
//				if(!Boolean.parseBoolean(isJenkinRun)){
//				File file=Recording.screenRecorder.getCreatedMovieFiles().stream().sorted(Comparator.comparingLong(File::lastModified)).collect(Collectors.toList()).get(0);
//					addAttachmentToIssue(file);
//				}
			//		Recording.endRecording();
		} catch (Exception e) {
			e.printStackTrace();
		}
		extentTest.log(Status.FAIL, "Test Failed");
	}

	private void addAttachmentsToIssue(List<File> screenshotFile) {
		screenshotFile.forEach(f -> {
			try {
				jira.getIssue(issue).addAttachment(f);
			} catch (JiraException e) {
				e.printStackTrace();
			}
		});
	}

	private void addAttachmentToIssue(File file) {
		try {
			jira.getIssue(issue).addAttachment(file);
		} catch (JiraException e) {
			e.printStackTrace();
		}

	}

	private List<File> getScreenshotFile(String testMethodName, String status) throws IOException {
		ScreenShot screenShot = new ScreenShot();
		List<File> files = new ArrayList<>();
		File file = null;

		if (driver != null) {
			file = screenShot.takeScreenShot(driver, testMethodName);
			File screenshotFile = createScreenshot(status, "Web");
			FileUtils.copyFile(file, screenshotFile);
			files.add(screenshotFile);
		}

		return files;
	}

	private File createScreenshot(String status, String driver) {
		return new File(
				"screenshots/ScreenShot/" + issue + "/" + status + "/" + driver + LocalDate.now()
						+ ".png");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		extentTest.log(Status.SKIP, "Test Skipped");
		if (jiraReport) {
			ExecutionDetails executionDetails = getExecutionDetails("UNEXECUTED");
			boolean check = zapi.updateExecution(executionDetails);
			logger.info(check);
		}
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

//		@Override
//		public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
//				Method testMethod) {
//			annotation.setRetryAnalyzer(this.getClass());
//		}
	//
//		@Override
//		public boolean retry(ITestResult result) {
//			if (counter < retryLimit) {
	//
//				counter++;
//				return true;
//			}
//			return false;
	//
//		}

	@Override
	public void onStart(ISuite suite) {

		PropertyConfigurator.configure("src/main/resources/configFile/log4j.properties");
		JavaPropertiesManager configProperty = getJavaPropertiesManager();
		jiraReport = Boolean.parseBoolean(configProperty.readProperty("jiraReport"));
		String name = suite.getAllMethods().get(0).getTestClass().getRealClass().getName()
				.toLowerCase();
		logger.info(name);
//    if(jiraReport) {
//
//      reportToJira(suite);
//    }
	}
 // this is for running classes
	private void reportToJira(ISuite suite) {
		isSmoke = suite.getName().toLowerCase().contains("smoke");// the getname refer to class 
		//suite not a test suite run
		logger.info(suite.getName() + " " + "This is the suite");
		if (!suite.getName().toLowerCase().contains("suite")) {
			checkCycleExists(createCycleName(), suite, isSmoke);
		} else {
			String name = suite.getAllMethods().get(0).getTestClass().getRealClass().getName()
					.toLowerCase();
			logger.info(name);
			boolean isSmoke = name.contains("smoke");
			//if smoke is true for individual Test to put in regression or smoke version
			checkCycleExists(createCycleName(), suite, isSmoke);
		}
	}


	public String createCycleName() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyy");
		return formatter.format(new Date()) + "_";
	}


	private void checkCycleExists(String name, ISuite suite, boolean isSmoke) {
		logger.info(name);
		logger.info(projectId);
		createClient(name, suite, isSmoke);
		logger.info(Arrays.stream(cycles).anyMatch(cycle -> cycle.name
				.equals(cycleName)));
		if (Arrays.stream(cycles).filter(c -> c.name.contains(name)).anyMatch(cycle -> cycle.name
				.equals(cycleName))) {

			pc = Arrays.stream(cycles).filter(cycle -> cycle.name
					.equals(cycleName)).collect(Collectors.toList()).get(0);
		} else {
			pc = zapi.createCycle(cycleName, versionId, projectId);
			logger.info(pc.name);
		}
		logger.info(pc.id);

	}

	private void createClient(String cycle, ISuite suite, boolean isSmoke) {
		// TODO Auto-generated method stub
		setJiraFields();
		jiraClient = getClient();
		jira = getJiraClient();
		Version version = createVersion(suite, isSmoke);
		checkVersionExists(version);
		cycles = zapi.getCycleList(projectId, versionId).cycles;
		cycleName = cycle + getCount(cycles) + "_" + appName;
		logger.info("cycleName is " + cycleName);

	}

	private String getCount(ProjectCycles[] cycles) {
		String creationDate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		long count=Arrays.stream(cycles).filter(cycle->cycle.creationDate.contains(creationDate)).count();
		return Arrays.stream(cycles).anyMatch(cycle->cycle.creationDate.contains(creationDate))?String.valueOf(count):String.valueOf(count+1);
	}


	private Client getClient() {
		return new Client(email, secret);
	}

	private JiraClient getJiraClient() {
		return new JiraClient("https://estesexpress.atlassian.net",
				new BasicCredentials(email, secret));
	}

	private void setJiraFields() {
		BasicConfigurator.configure();
		JavaPropertiesManager configProperty = getJavaPropertiesManager();
		accessKey = configProperty.readProperty("accessKey");
		secretKey = configProperty.readProperty("secretKey");
		accountId = configProperty.readProperty("accountId");
		projectId = configProperty.readProperty("projectId");
		email = configProperty.readProperty("email");
		apiKey = configProperty.readProperty("apiKey");
		secret = configProperty.readProperty("secret");
		realease = configProperty.readProperty("realease");
		description = configProperty.readProperty("description");
		smokeVersion = configProperty.readProperty("smokeVersion");
		zapi.setAccessKey(accessKey);
		zapi.setAccountId(accountId);
		zapi.setSecretKey(secretKey);


	}

	private void checkVersionExists(Version version) {
		final String name = version.name;
		Version[] versions = jiraClient.getVersions(projectId);
		if (Arrays.stream(versions).anyMatch(v -> v.name.equals(name))) {
			versionId = Arrays.stream(versions).filter(v -> v.name.equals(name))
					.collect(Collectors.toList()).get(0).id;
			logger.info(versionId + "exists");
		} else {
			Version versionPost = jiraClient.createVersion(version);
			versionId = versionPost.id;
			logger.info(versionPost.id);


		}
	}

	private Version createVersion(ISuite suite, Boolean smoke) {
		Version v = new Version();
		v.archived = false;
		v.releaseDate = LocalDate.now().toString();
		//this is used for putting all smoke under one folder

		v.name = versionName(suite, smoke);
		logger.info(v.name);
		//v.name=LocalDate.now().getMonth().name()+LocalDate.now().getYear()+realease;
		v.description = description;
		v.projectId = Integer.parseInt(projectId);
		v.released = false;
		return v;
	}

	public String getMonth() {
		char[] month = LocalDate.now().getMonth().name().toLowerCase().toCharArray();
		String first = String.valueOf(month[0]).toUpperCase();
		String rest = String.valueOf(month[1]) + month[2];
		logger.info(first + rest);
		return first + rest;
	}

	public String versionName(ISuite suite, Boolean smoke) {
		String typeTest = smoke ? "Smk" : "Reg";
		Class classInfoSuite = suite.getAllMethods().get(BigDecimal.ZERO.intValue()).getTestClass()
				.getRealClass();
		String blank = "";
		appName = classInfoSuite.getName().replace("Regression", blank).replace("Smoke",
				blank).replace("Test", blank).split("\\.")[1];
		return getMonth() + "_" + LocalDate.now().getYear() + "_" + "A_" + typeTest + "_"
				+ appName;
	}

	@Override
	public void onFinish(ISuite suite) {

}

}
