package customerReportsTests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesWelcomePage;
import onlineReportingPages.OnlineReportingPage;
import testBase.TestBase;

public class CustomerReportsSmokeTest extends TestBase {

	private Logger logger = Logger.getLogger(CustomerReportsSmokeTest.class.getName());

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	OnlineReportingPage onlineReportingPage = new OnlineReportingPage();

	/******************************* TESTS *******************************/

	/**
	 * 
	 * this test calls QZ-11162 which is login in credentials
	 * Passed on 6/13/2022
	 * 
	 * Verify a My Estes User can Create, Save, and Schedule a Report
	 */
	
	@Test(enabled = true, priority = 1)

	public void executeQZ_10170() throws InterruptedException {

		String reportName = "Automated Regression";
		String emailAdd = "qatest@estes-express.com";
		System.out.println("The URL is: " + driver.getCurrentUrl());

		// step #2: login in
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(500);
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		testUtil.setHardWait(2000);
		// step #3:On the left navigation panel, click on the following link
		myEstesWelcomePage.clickOnReportingLink();
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		// step #4:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// step #5:Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown();
		// step #6: Enter values
		testUtil.setHardWait(1000);
		onlineReportingPage.enterValueTextBox(reportName);
		// step #7:Click on schedule report
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnSchduleReport();
		// step #8:Enter data
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();
		// step #9: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		// step #10:Click see details and varify
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// step #11:Click cancel
		onlineReportingPage.clickOnCancelButton();
		// step #12: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #13: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// this step calls QZ-10178 which is to delete previously created report
		
		/*
		 * The step below is not in the test case. This step is used to delete the
		 * account generated in the previous step as duplicate values are not possible.
		 */
		onlineReportingPage.clickOnDeleteButton(reportName);

	}

}
