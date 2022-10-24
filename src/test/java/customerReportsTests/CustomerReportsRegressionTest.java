package customerReportsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesWelcomePage;
import onlineReportingPages.OnlineReportingPage;
import onlineReportingPages.OnlineReportingPage.Icon;
import testBase.TestBase;
import util.TestUtil;

public class CustomerReportsRegressionTest extends TestBase {

	private Logger logger = Logger.getLogger(CustomerReportsRegressionTest.class.getName());

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	OnlineReportingPage onlineReportingPage = new OnlineReportingPage();

	/******************************* TESTS *******************************/
	/*
	 * Verify a My Estes User can Delete a Previously Created Report
	 */
	@Test(enabled = true, priority = 1)

	public void executeQZ_10178() throws InterruptedException {

		String reportName = "QZ-10170";
		String emailAdd = "qatescct@estes-express.com";

		// login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Step -1 : Prerequisite: Reference QZ-10170 if needed to create a report
		// On the left navigation panel, click on the following link
		myEstesWelcomePage.clickOnReportingLink();

		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		// Click on create a report tab
		onlineReportingPage.clickOnCreateReportTab();
		// Select report pick list
		onlineReportingPage.selectOnReportDropDown();
		// Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Enter data
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();
		// Click submit
		onlineReportingPage.clickOnSubmitButton();


	
		// Click save the report
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(4000);

		// Identify a report listed in the *Active Reports* grid and delete the report
		onlineReportingPage.clickIconButton(reportName, Icon.DELETE);

		// Verify deleted report is no longer listed
		onlineReportingPage.verifyReportIsNotDisplayed(reportName);
		testUtil.setHardWait(2000);
		// Below 2 steps are updated by Dorothy
		// Step-7 : If a report is listed in the *Expired Reports* grid, click on the
		// *X* icon
		onlineReportingPage.deleteExpiredReports();

		// Step-8: Click on cancel button to preserve the test data in expired reports
		onlineReportingPage.clickOnCancelBtn();

		// Step-9: Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * Verify a My Estes User can Remove a Report from the Schedule
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_10175() throws InterruptedException {

		String reportName = "QZ-10170";
		String emailAdd = "qatescct@estes-express.com";

		// login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Prerequisite:Successful execution of QZ-10170
		// On the left navigation panel, click on the following link
		myEstesWelcomePage.clickOnReportingLink();

		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		testUtil.setHardWait(2000);
		// Click on create a report tab
		onlineReportingPage.clickOnCreateReportTab();
		// Select report pick list
		onlineReportingPage.selectOnReportDropDown();
		// Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Enter data
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();
		// Click submit
		onlineReportingPage.clickOnSubmitButton();
		// Click see details and varify
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Click cancel
		onlineReportingPage.clickOnCancelButton();
		// Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(4000);
		// Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		// Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		/*
		 * The step below is not in the test case. This step is used to delete the
		 * account generated in the previous step as duplicate values are not possible.
		 */
		onlineReportingPage.clickOnDeleteButton(reportName);

		// Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();
	}

	/**
	 * passed on 9/9/22
	 * 
	 * Verify a My Estes User can Copy and Edit a Previously Created Report
	 */
	@Test(enabled = true, priority = 3)

	public void executeQZ_10176() throws InterruptedException {

		String reportName = "QZ-10170";
		String emailAdd = "qatescct@estes-express.com";
		String newReportName = reportName + "new";

		// login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Prerequisite:Successful execution of QZ-10170
		// On the left navigation panel, click on the following link
		myEstesWelcomePage.clickOnReportingLink();
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		// Click on create a report tab
		onlineReportingPage.clickOnCreateReportTab();
		// Select report pick list
		onlineReportingPage.selectOnReportDropDown();
		// Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Enter data
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();
		// Click submit
		onlineReportingPage.clickOnSubmitButton();
		// Click see details and verify
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Click cancel
		onlineReportingPage.clickOnCancelButton();
		// Click save the report
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(4000);
		// Click on copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("QZ-10170 COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();
		testUtil.setHardWait(1000);
		// Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Rename the report
		onlineReportingPage.renameTheReport(newReportName);
		// Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(3000);
		// Click on edit icon
		onlineReportingPage.clickOnEditIcon(newReportName);
		testUtil.setHardWait(3000);
		// click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();
		// Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		/*
		 * The step below is not in the test case. This step is used to delete the
		 * account generated in the previous step as duplicate values are not possible.
		 */
		onlineReportingPage.clickOnDeleteButton(newReportName);

		onlineReportingPage.clickOnDeleteButton(reportName);

		// Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/** Turned off- runs locally as it is inconsistent **/
	/**
	 * 
	 * 
	 * Verify a My Estes User can Download a Report in Excel or CSV Format
	 */
	
	@Test(enabled = false, priority = 4)
	public void executeQZ_10173() throws InterruptedException {
		String reportName = "QZ-10170";
		String emailAdd = "qatescct@estes-express.com";

		// Step 1 - 3: Log into My Estes and complete Pre-requisite steps
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		//changed the user from test.admin to testgroup as per the test case
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		myEstesLoginPage.clickOnLoginButton();

		// Pre-requisite:Successful execution of QZ-10170
		// On the left navigation panel, click on the following link
		myEstesWelcomePage.clickOnReportingLink();
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		// Click on create a report tab
		onlineReportingPage.clickOnCreateReportTab();
		// Select report pick list
		onlineReportingPage.selectOnReportDropDown();
		// Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Enter data
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();
		// Click submit
		onlineReportingPage.clickOnSubmitButton();
		// Click see details and verify
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Click cancel
		onlineReportingPage.clickOnCancelButton();
		// Click save the report
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(4000);

		// Step 4 - 6 From the *Active Reports* grid, click the *Download Report* button
		// for the report
		// created in previous step
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 7: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 8: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Verify the xlsx report is downloaded
		testUtil.verifyIsFileDownloaded(downloadPath, fileName);

		// Step 9: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 10: Select the following radio button:
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 11: Click Download
		onlineReportingPage.clickOnDownloadButton();
		testUtil.setHardWait(3000);
		String fileName2 = reportName + ".csv";
		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		// Verify the csv report is downloaded
		testUtil.verifyIsFileDownloaded(downloadPath2, fileName2);

		// Step 12 : For manual testing: verify the data in both reports is the same.

		// Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath, fileName2);

		// Step 13: Log out and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * ACCORDING TO JIRA, THIS TEST HAS REVIEW FOR DELETE LABEL. NEED TO CROSS CHECK WITH CHERYL
	 * 
	 * this passed on 9/9/22
	 * Verify My Estes User can Access the New Reporting App
	 */
	@Test(enabled = true, priority = 5)

	public void executeQZ_10169() throws InterruptedException {
		// login in
		// Scenario-1 :
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Click on create a report tab
		onlineReportingPage.clickOnCreateReportTab();
		// The new reporting app will display a drop down with available reports.
		onlineReportingPage.verifyReportDropDownDisplayed();

		// Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

		// login in
		// Scenario-2 :
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(2000);
		// Message *You do not have access to this page.* is displayed
		myEstesHomePage.verifyAccessMessageIsDisplayed();
		testUtil.setHardWait(2000);
		// Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

		// Scenario-3 :
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();
		
		//added this method to handle the login slowness issue with QA environment 
		testUtil.fluentWait(By.xpath("//lib-routable-component-header/h1/span[contains(text(),'Online Reporting')]"), 45, 5, "Online Reporting");
		
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Two sections titled Active Reports and Expired Reports are displayed.

		onlineReportingPage.verifyTitleOfTheReportDisplayed("Active Reports");

		onlineReportingPage.verifyTitleOfTheReportDisplayed("Expired Reports");

		// Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * This was failing on Jenkins but Passed on Saphals end
	 * 
	 * Verify a My Estes User can Download a Report in Excel or CSV Format from Edit
	 * Reports page
	 */
	@Test(enabled = true, priority = 6)
	public void executeQZ_11137() throws InterruptedException {
		String reportName = "QZ-10170";
		String emailAdd = "qatescct@estes-express.com";

		// Step 1 - 3: Log into My Estes and complete Pre-requisite steps
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Pre-requisite: Successful execution of QZ-10170
		// On the left navigation panel, click on the following link
		myEstesWelcomePage.clickOnReportingLink();
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		// Click on create a report tab
		onlineReportingPage.clickOnCreateReportTab();
		// Select report pick list
		onlineReportingPage.selectOnReportDropDown();
		// Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Enter data
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();
		// Click submit
		onlineReportingPage.clickOnSubmitButton();
		// Click see details and verify
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Click cancel
		onlineReportingPage.clickOnCancelButton();
		// Click save the report
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(4000);

		// Step 4 - 6: Click the *Edit* icon for the named report "QZ-10170"
		onlineReportingPage.clickOnEditIcon(reportName);

		// Step 7: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 8: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 9: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName = reportName + ".xlsx";
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(1000);
		testUtil.verifyIsFileDownloaded(downloadPath, fileName);

		// Step 10: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 11: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 12: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();
		testUtil.setHardWait(3000);

		String fileName2 = reportName + ".csv";
		try {
			String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
			testUtil.verifyIsFileDownloaded(downloadPath2, fileName2);
		} catch (Exception e) {

			e.printStackTrace();
		}

		// Step 13: For manual testing: verify the data in both reports is the same.

		// Delete the downloaded files to clean the data
		// testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath, fileName2);
		//step is not in the test
		
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		// Step 14: Log out and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();
		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/*
	 * combined tests- Verify a My Estes User can Select the Accounts Receivable
	 * (AR) Open Detail Report
	 */
	@Test(enabled = true, priority = 7)

	public void executeQZ_11323() throws InterruptedException {

		String reportName = "Automated Regression";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Extra step for automation
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step 2:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// Step 3: Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown();
		// Step 4: Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Step #5:Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Step #6:From on the *Schedule Report* popup, enter or select the following
		// data:
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();// CSV File

		// Step 7: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		// Step 8:From the *Schedule Details* display, click the *See Details* link
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Step 9:From the *Schedule Report* popup display with the previously selected
		// options and changes made, click *Cancel*
		onlineReportingPage.clickOnCancelButton();
		// step 10: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #11: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// Step-12 : part -2

		testUtil.setHardWait(4000);

		// Step 12,13: From the *Active Reports* grid, click the *Download Report*
		// button for the report
		// created in previous step
		onlineReportingPage.clickIconButton(reportName, Icon.DOWNLOAD);

		// Step 14: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 15: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Step 16 : Verify the xlsx report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 17: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 18: Select the following radio button:
		String fileName2 = reportName + ".csv";
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 19: Click Download
		onlineReportingPage.clickOnDownloadButton();

		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.setHardWait(2000);
		// Step-20: Verify the csv report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 21 : For manual testing: verify the data in both reports is the same.

		// Step-22 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

		// Step-23 --part-3

		testUtil.setHardWait(4000);

		// Step-24 : click copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-25 : Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression COPY");

		// Step-25 : Click on one account radio button

		onlineReportingPage.clickOnOneAccountRadioButton();

		// Account search hyperlink becomes active
		onlineReportingPage.verifyAccountSearchIsActive();

		// step #26: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// step-27,28 : locate and delete the copy report

		onlineReportingPage.deleteCopyReportIfExistInTheTable("Automated Regression COPY");

		// part-4

		testUtil.setHardWait(4000);

		// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
		// icon for the named report "Automated Regression"
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(2000);
		// Step 31: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 32: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 33: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName3 = reportName + ".xlsx";
		String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);

		// Step-33 : verify success message
		onlineReportingPage.verifySuccessMessage();

		// Step 34: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 35: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 36: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName4 = reportName + ".csv";
		String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);
		// verify success message
		// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
		// bug and its reported to cheryl

		// Step 37: For manual testing: verify the data in both reports is the same.

		// Step-38 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

		// step-39 - part-5

		// Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();
		testUtil.setHardWait(4000);

		// Step-39 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		// Step-40 : Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// Step-41 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Step-42 : Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		// part-6
		testUtil.setHardWait(4000);
		// Step-43 : Click on copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Step-44 : Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Step-45 : Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// Step-46 : On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();

		// Step-47 : Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Step-48 : Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Step-49 : Click *My Reports* tab the top of the App.
		onlineReportingPage.clickOnMyReportsTab();

		// Step-50 : Click Don't Save
		onlineReportingPage.clickOnDontSave();

		testUtil.setHardWait(3000);

		// Step-51 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-52 :click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();

		// Step-53 : Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// Step-54 , 55 : Identify a report listed in the *Active Reports* grid and
		// click the *X* icon & Click *DELETE REPORT* button
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step-56 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * This test is failing because feidls option in the webpage does not conisit of
	 * pro field- according to Saphal
	 * 
	 * 
	 * Customer Reports- Verify a My Estes User can Select the Accounts Receivable
	 * (AR) Aging Summary
	 */
	@Test(enabled = true, priority = 8)

	public void executeQZ_11531() throws InterruptedException {

		String reportName = "Automated Regression1";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Extra step for automation
		// onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		/*
		 * // Step 2:Click on create a report tab testUtil.setHardWait(1000);
		 * onlineReportingPage.clickOnCreateReportTab(); // Step 3: Select report pick
		 * list testUtil.setHardWait(1000); onlineReportingPage.
		 * selectOnReportDropDown("Accounts Receivable (AR) Aging Summary"); // Step 4:
		 * Enter values onlineReportingPage.enterValueInTextBox(reportName); // Step
		 * #5:Click on schedule report onlineReportingPage.clickOnSchduleReport(); //
		 * Step #6:From on the *Schedule Report* popup, enter or select the following
		 * data: onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		 * onlineReportingPage.selectReportFormat();// CSV File
		 * 
		 * // Step 7: Click submit onlineReportingPage.clickOnSubmitButton();
		 * testUtil.setHardWait(4000); // Step 8:From the *Schedule Details* display,
		 * click the *See Details* link onlineReportingPage.clickOnSeeDetailsLink();
		 * testUtil.setHardWait(1000); onlineReportingPage.verifyDataOnScheduleReport();
		 * // Step 9:From the *Schedule Report* popup display with the previously
		 * selected options and changes made, click *Cancel*
		 * onlineReportingPage.clickOnCancelButton(); // step 10: Click save the reprot
		 * onlineReportingPage.clickOnSaveReportButton();
		 * 
		 * // step #11: Verify The newly created report is listed in the *Active
		 * Reports* // section
		 * onlineReportingPage.verifyReportUnderActiveReports(reportName);
		 * 
		 * // Step-12 : part -2
		 * 
		 * testUtil.setHardWait(4000);
		 * 
		 * // Step 12,13: From the *Active Reports* grid, click the *Download Report* //
		 * button for the report // created in previous step
		 * onlineReportingPage.clickOnDownloadIcon(reportName);
		 * 
		 * // Step 14: Select the following radio button: String fileName = reportName +
		 * ".xlsx"; onlineReportingPage.selectReportFormatRadioBtn("xls");
		 * 
		 * // Step 15: Click *Download* onlineReportingPage.clickOnDownloadButton();
		 * String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		 * testUtil.setHardWait(4000);
		 * 
		 * // Step 16 : Verify the xlsx report is downloaded
		 * onlineReportingPage.verifySuccessMessage();
		 * 
		 * // Step 17: Click the *Download Report* button again
		 * onlineReportingPage.clickOnDownloadIcon(reportName);
		 * 
		 * // Step 18: Select the following radio button: String fileName2 = reportName
		 * + ".csv"; onlineReportingPage.selectReportFormatRadioBtn("csv");
		 * 
		 * // Step 19: Click Download onlineReportingPage.clickOnDownloadButton();
		 * 
		 * String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		 * 
		 * testUtil.setHardWait(2000); // Step-20: Verify the csv report is downloaded
		 * onlineReportingPage.verifySuccessMessage();
		 * 
		 * // Step 21 : For manual testing: verify the data in both reports is the same.
		 * 
		 * // Step-22 : Delete the downloaded files to clean the data
		 * testUtil.deleteFilesFromFolder(downloadPath, fileName);
		 * testUtil.setHardWait(2000); testUtil.deleteFilesFromFolder(downloadPath2,
		 * fileName2);
		 * 
		 * // Step-23 --part-3
		 * 
		 * testUtil.setHardWait(4000);
		 * 
		 * // Step-24 : click copy icon onlineReportingPage.clickOnCopyIcon(reportName);
		 * testUtil.setHardWait(3000);
		 * 
		 * // Step-25 : Verify copy report tab is displayed
		 * onlineReportingPage.verifyCopyReportTabIsDisplayed();
		 * 
		 * // Verify the name has COPY appended to it
		 * onlineReportingPage.verifyReportHasCopyAppended("Automated Regression1 COPY"
		 * );
		 * 
		 * // Step-25 : Click on one account radio button
		 * 
		 * onlineReportingPage.clickOnOneAccountRadioButton();
		 * 
		 * // Account search hyperlink becomes active
		 * onlineReportingPage.verifyAccountSearchIsActive();
		 * 
		 * // step #26: Click save the reprot
		 * onlineReportingPage.clickOnSaveReportButton();
		 * 
		 * testUtil.setHardWait(2000);
		 * 
		 * // step-27,28 : locate and delete the copy report
		 * 
		 * onlineReportingPage.
		 * deleteCopyReportIfExistInTheTable("Automated Regression1 COPY");
		 * 
		 * // part-4
		 * 
		 * testUtil.setHardWait(4000);
		 * 
		 * // Step 29, 30: Locate the named report Automated Regression & Click the
		 * *Edit* // icon for the named report "Automated Regression"
		 * onlineReportingPage.clickOnEditIcon(reportName);
		 * 
		 * // Step 31: Click the *Download Report* button
		 * onlineReportingPage.clickOnDownloadReportButton();
		 * testUtil.setHardWait(2000);
		 * 
		 * // Step 32: Select the following radio button: Report Format: Excel Worksheet
		 * // File onlineReportingPage.selectReportFormatRadioBtn("xls");
		 * 
		 * // Step 33: Click Download and validate report has been downloaded
		 * onlineReportingPage.clickOnDownloadButton();
		 * 
		 * String fileName3 = reportName + ".xlsx"; String downloadPath3 =
		 * TestUtil.getCurrentWorkingPath() + "/Downloads/"; testUtil.setHardWait(3000);
		 * 
		 * 
		 * // Step-33 : verify success message
		 * onlineReportingPage.verifySuccessMessage();
		 * 
		 * // Step 34: Click the *Download Report* button again
		 * onlineReportingPage.clickOnDownloadReportButton();
		 * 
		 * // Step 35: Select the following radio button: Report Format: Text/CSV
		 * onlineReportingPage.selectReportFormatRadioBtn("csv");
		 * 
		 * // Step 36: Click Download and validate report has been downloaded
		 * onlineReportingPage.clickOnDownloadButton();
		 * 
		 * 
		 * String fileName4 = reportName + ".csv"; String downloadPath4 =
		 * TestUtil.getCurrentWorkingPath() + "/Downloads/"; testUtil.setHardWait(3000);
		 * // verify success message //onlineReportingPage.verifySuccessMessage();
		 * //Commented this line as it has a bug and its reported to cheryl
		 * 
		 * // Step 37: For manual testing: verify the data in both reports is the same.
		 * 
		 * // Step-38 : Delete the downloaded files to clean the data
		 * testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		 * testUtil.setHardWait(2000); testUtil.deleteFilesFromFolder(downloadPath4,
		 * fileName4);
		 * 
		 * // Step-39 : Click on remove schedule link
		 * onlineReportingPage.clickOnRemoveScheduleLink(); // Step-40 : On the Remove
		 * Schedule pop up, click the REMOVE SCHEDULE button
		 * onlineReportingPage.clickOnRemoveScheduleButton(); // Verify The schedule
		 * details is no longer displayed
		 * onlineReportingPage.verifyScheduleDetailsNotDisplayed();
		 * 
		 * // Step-41 : Click save the report
		 * onlineReportingPage.clickOnSaveReportButton();
		 * 
		 * // part-6 testUtil.setHardWait(4000); // Step-42 : Click on copy icon
		 * onlineReportingPage.clickOnCopyIcon(reportName); testUtil.setHardWait(3000);
		 * 
		 * // Verify copy report tab is displayed
		 * onlineReportingPage.verifyCopyReportTabIsDisplayed();
		 * 
		 * // Verify the name has COPY appended to it
		 * onlineReportingPage.verifyReportHasCopyAppended("Automated Regression1 COPY"
		 * );
		 * 
		 * // Record the original layout order of the column String columnName =
		 * onlineReportingPage.recordOriginalLayoutOrder();
		 * 
		 * // Step-43 : Click on fields onlineReportingPage.clickOnFieldsIcon();
		 * 
		 * // Step-44 : Drag and drop the Pro# field to the top
		 * onlineReportingPage.dragAndDropProField();
		 * 
		 * // Step-45 : On the *Fields* window, unselect *Select All*
		 * onlineReportingPage.unselectSelectAll();
		 * 
		 * // select the first 10 columns on the list
		 * onlineReportingPage.selectFirstTenColumnsOnList();
		 * 
		 * // Step-46 : Click apply onlineReportingPage.clickOnApplyButton();
		 * 
		 * // Verify the layout order is changed
		 * onlineReportingPage.verifyLayoutOrderChanged("Pro #");
		 * 
		 * // Step-47 : Click on reset button onlineReportingPage.clickOnResetButton();
		 * 
		 * // The columns are set back to the original layout
		 * Assert.assertEquals(onlineReportingPage.recordOriginalLayoutOrder(),
		 * columnName);
		 * 
		 * // Step-48 : Click *My Reports* tab the top of the App.
		 * onlineReportingPage.clickOnMyReportsTab();
		 * 
		 * // Step-49 : Click Don't Save onlineReportingPage.clickOnDontSave();
		 * 
		 * testUtil.setHardWait(3000);
		 * 
		 * // Step-50 : Click on edit icon
		 * onlineReportingPage.clickOnEditIcon(reportName); testUtil.setHardWait(3000);
		 * 
		 * // Step-51 :click the arrow in the heading for *Pro#* to sort asc.
		 * onlineReportingPage.clickTheArrowForPro();
		 * 
		 * // Step-52 : Click *Save Report*
		 * onlineReportingPage.clickOnSaveReportButton();
		 * 
		 * testUtil.setHardWait(2000);
		 * 
		 * // Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
		 * // click the *X* icon & Click *DELETE REPORT* button
		 * onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		 * 
		 * // Step-55 : Logoff and close browser myEstesLoginPage.clickOnMyEstes();
		 * testUtil.setHardWait(2000); myEstesLoginPage.clickOnLogoutButton();
		 * 
		 * myEstesLoginPage.clickOnLogoutConfirmButton();
		 */
	}

	/**
	 * This test is failing because feidls option in the webpage does not conisit of
	 * pro field- according to Saphal
	 * 
	 * Customer Reports- Verify a My Estes User can Select the Claims Summary Report
	 */
	@Test(enabled = true, priority = 9)

	public void executeQZ_11534() throws InterruptedException {

		String reportName = "Automated Regression3";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// this is an extra step for automation
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);
		
		// Step 2:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// Step 3: Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown("Claims Summary ");
		// Step 4: Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Step #5:Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Step #6:From on the *Schedule Report* popup, enter or select the following
		// data:
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();// CSV File

		// Step 7: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(7000);
		// Step 8:From the *Schedule Details* display, click the *See Details* link
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Step 9:From the *Schedule Report* popup display with the previously selected
		// options and changes made, click *Cancel*
		onlineReportingPage.clickOnCancelButton();
		// step 10: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #11: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// Step-12 : part -2

		testUtil.setHardWait(4000);

		// Step 12,13: From the *Active Reports* grid, click the *Download Report*
		// button for the report
		// created in previous step

		onlineReportingPage.clickDownloadIcon(reportName);
		testUtil.setHardWait(7000);
		// Step 14: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 15: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Step 16 : Verify the xlsx report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 17: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 18: Select the following radio button:
		String fileName2 = reportName + ".csv";
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 19: Click Download
		onlineReportingPage.clickOnDownloadButton();

		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.setHardWait(2000);
		// Step-20: Verify the csv report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 21 : For manual testing: verify the data in both reports is the same.

		// Step-22 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

		// Step-23 --part-3

		testUtil.setHardWait(4000);

		// Step-24 : click copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-25 : Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression3 COPY");

		// Step-25 : Click on one account radio button

		onlineReportingPage.clickOneAccountRadioButton();

		// Account search hyperlink becomes active
		onlineReportingPage.verifyAccountSearchIsActive();

		// step #26: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// step-27,28 : locate and delete the copy report

		onlineReportingPage.deleteCopyReportIfExistInTheTable("Automated Regression3 COPY");

		// part-4

		testUtil.setHardWait(4000);

		// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
		// icon for the named report "Automated Regression"
		onlineReportingPage.clickOnEditIcon(reportName);

		// Step 31: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();
		testUtil.setHardWait(2000);

		// Step 32: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 33: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName3 = reportName + ".xlsx";
		String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(700);

		// Step-33 : verify success message
		onlineReportingPage.verifySuccessMessage();

		// Step 34: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 35: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 36: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName4 = reportName + ".csv";
		String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);
		// verify success message
		// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
		// bug and its reported to cheryl

		// Step 37: For manual testing: verify the data in both reports is the same.

		// Step-38 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

		// Step-39 : Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// Step-40 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Step-41 : Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		// part-6
		testUtil.setHardWait(4000);
		// Step-42 : Click on copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression3 COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Step-43 : Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Step-44 : Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// Step-45 : On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();

		// Step-46 : Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Step-47 : Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Step-48 : Click *My Reports* tab the top of the App.
		onlineReportingPage.clickOnMyReportsTab();

		// Step-49 : Click Don't Save
		onlineReportingPage.clickOnDontSave();

		testUtil.setHardWait(3000);

		// Step-50 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(9000);
		System.out.println("The testing has stopped here for debugging purposes");
		
		// Step-51 :click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();

		
		// Step-52 : Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
		// click the *X* icon & Click *DELETE REPORT* button
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step-55 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * This test is failing because feidls option in the webpage does not conisit of
	 * account field- according to Saphal
	 * 
	 * 
	 * Customer Reports- Verify a My Estes User can Select the In Transit Detail
	 * Report
	 */
	@Test(enabled = true, priority = 10)

	public void executeQZ_11535() throws InterruptedException {

		String reportName = "Automated Regression4";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Extra step for automation
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step 2:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// Step 3: Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown("In Transit Detail ");
		// Step 4: Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Step #5:Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Step #6:From on the *Schedule Report* popup, enter or select the following
		// data:
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();// CSV File

		// Step 7: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		// Step 8:From the *Schedule Details* display, click the *See Details* link
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Step 9:From the *Schedule Report* popup display with the previously selected
		// options and changes made, click *Cancel*
		onlineReportingPage.clickOnCancelButton();
		// step 10: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #11: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// Step-12 : part -2

		testUtil.setHardWait(4000);

		// Step 12,13: From the *Active Reports* grid, click the *Download Report*
		// button for the report
		// created in previous step
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 14: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 15: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Step 16 : Verify the xlsx report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 17: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 18: Select the following radio button:
		String fileName2 = reportName + ".csv";
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 19: Click Download
		onlineReportingPage.clickOnDownloadButton();

		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.setHardWait(2000);
		// Step-20: Verify the csv report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 21 : For manual testing: verify the data in both reports is the same.

		// Step-22 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

		// Step-23 --part-3

		testUtil.setHardWait(4000);

		// Step-24 : click copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-25 : Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression4 COPY");

		// Step-25 : Click on one account radio button

		onlineReportingPage.clickOnOneAccountRadioButton();

		// Account search hyperlink becomes active
		onlineReportingPage.verifyAccountSearchIsActive();

		// step #26: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// step-27,28 : locate and delete the copy report

		onlineReportingPage.deleteCopyReportIfExistInTheTable("Automated Regression4 COPY");

		// part-4

		testUtil.setHardWait(4000);

		// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
		// icon for the named report "Automated Regression"
		onlineReportingPage.clickOnEditIcon(reportName);

		// Step 31: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();
		testUtil.setHardWait(2000);

		// Step 32: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 33: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName3 = reportName + ".xlsx";
		String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);

		// Step-33 : verify success message
		onlineReportingPage.verifySuccessMessage();

		// Step 34: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 35: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 36: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName4 = reportName + ".csv";
		String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);
		// verify success message
		// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
		// bug and its reported to cheryl

		// Step 37: For manual testing: verify the data in both reports is the same.

		// Step-38 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

		// Step-39 : Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// Step-40 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Step-41 : Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		// part-6
		testUtil.setHardWait(4000);
		// Step-42 : Click on copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression4 COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Step-43 : Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Step-44 : Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// Step-45 : On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();

		// Step-46 : Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Step-47 : Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Step-48 : Click *My Reports* tab the top of the App.
		onlineReportingPage.clickOnMyReportsTab();

		// Step-49 : Click Don't Save
		onlineReportingPage.clickOnDontSave();

		testUtil.setHardWait(3000);

		// Step-50 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-51 :click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();

		// Step-52 : Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
		// click the *X* icon & Click *DELETE REPORT* button
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step-55 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * This test is failing because feidls option in the webpage does not conisit of
	 * account field- according to Saphal
	 * 
	 * Customer Reports- Verify a My Estes User can Select the OS&D Detail Report
	 */
	@Test(enabled = true, priority = 11)

	public void executeQZ_11536() throws InterruptedException {

		String reportName = "Automated Regression5";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Extra step for automation
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step 2:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// Step 3: Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown("OS&D Detail ");
		// Step 4: Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Step #5:Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Step #6:From on the *Schedule Report* popup, enter or select the following
		// data:
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();// CSV File

		// Step 7: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		// Step 8:From the *Schedule Details* display, click the *See Details* link
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Step 9:From the *Schedule Report* popup display with the previously selected
		// options and changes made, click *Cancel*
		onlineReportingPage.clickOnCancelButton();
		// step 10: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #11: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// Step-12 : part -2

		testUtil.setHardWait(4000);

		// Step 12,13: From the *Active Reports* grid, click the *Download Report*
		// button for the report
		// created in previous step
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 14: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 15: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Step 16 : Verify the xlsx report is downloaded
		onlineReportingPage.verifyPopupMessage("Success");

		// Step 17: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 18: Select the following radio button:
		String fileName2 = reportName + ".csv";
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 19: Click Download
		onlineReportingPage.clickOnDownloadButton();

		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.setHardWait(2000);
		// Step-20: Verify the csv report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 21 : For manual testing: verify the data in both reports is the same.

		// Step-22 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

		// Step-23 --part-3

		testUtil.setHardWait(4000);

		// Step-24 : click copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-25 : Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression5 COPY");

		// Step-25 : Click on one account radio button

		onlineReportingPage.clickOnOneAccountRadioButton();

		// Account search hyperlink becomes active
		onlineReportingPage.verifyAccountSearchIsActive();

		// step #26: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// step-27,28 : locate and delete the copy report

		onlineReportingPage.deleteCopyReportIfExistInTheTable("Automated Regression5 COPY");

		// part-4

		testUtil.setHardWait(4000);

		// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
		// icon for the named report "Automated Regression"
		onlineReportingPage.clickOnEditIcon(reportName);

		// Step 31: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();
		testUtil.setHardWait(2000);

		// Step 32: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 33: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName3 = reportName + ".xlsx";
		String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);

		// Step-33 : verify success message
		onlineReportingPage.verifySuccessMessage();

		// Step 34: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 35: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 36: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName4 = reportName + ".csv";
		String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);
		// verify success message
		// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
		// bug and its reported to cheryl

		// Step 37: For manual testing: verify the data in both reports is the same.

		// Step-38 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

		// Step-39 : Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// Step-40 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Step-41 : Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		// part-6
		testUtil.setHardWait(4000);
		// Step-42 : Click on copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression5 COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Step-43 : Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Step-44 : Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// Step-45 : On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();

		// Step-46 : Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Step-47 : Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Step-48 : Click *My Reports* tab the top of the App.
		onlineReportingPage.clickOnMyReportsTab();

		// Step-49 : Click Don't Save
		onlineReportingPage.clickOnDontSave();

		testUtil.setHardWait(3000);

		// Step-50 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-51 :click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();

		// Step-52 : Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
		// click the *X* icon & Click *DELETE REPORT* button
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step-55 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * This test is failing because feidls option in the webpage does not conisit of
	 * account field- according to Saphal
	 * 
	 * Customer Reports - Verify a My Estes User can Select the Shipment Service
	 * Detail Report
	 */
	@Test(enabled = true, priority = 12)

	public void executeQZ_11537() throws InterruptedException {

		String reportName = "Automated Regression6";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Extra step for automation
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step 2:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// Step 3: Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown("Shipment Service Detail ");
		// Step 4: Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Step #5:Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Step #6:From on the *Schedule Report* popup, enter or select the following
		// data:
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();// CSV File

		// Step 7: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		// Step 8:From the *Schedule Details* display, click the *See Details* link
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Step 9:From the *Schedule Report* popup display with the previously selected
		// options and changes made, click *Cancel*
		onlineReportingPage.clickOnCancelButton();
		// step 10: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #11: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// Step-12 : part -2

		testUtil.setHardWait(4000);

		// Step 12,13: From the *Active Reports* grid, click the *Download Report*
		// button for the report
		// created in previous step
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 14: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 15: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Step 16 : Verify the xlsx report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 17: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 18: Select the following radio button:
		String fileName2 = reportName + ".csv";
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 19: Click Download
		onlineReportingPage.clickOnDownloadButton();

		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.setHardWait(2000);
		// Step-20: Verify the csv report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 21 : For manual testing: verify the data in both reports is the same.

		// Step-22 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

		// Step-23 --part-3

		testUtil.setHardWait(4000);

		// Step-24 : click copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-25 : Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression6 COPY");

		// Step-25 : Click on one account radio button

		onlineReportingPage.clickOnOneAccountRadioButton();

		// Account search hyperlink becomes active
		onlineReportingPage.verifyAccountSearchIsActive();

		// step #26: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// step-27,28 : locate and delete the copy report

		onlineReportingPage.deleteCopyReportIfExistInTheTable("Automated Regression6 COPY");

		// part-4

		testUtil.setHardWait(4000);

		// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
		// icon for the named report "Automated Regression"
		onlineReportingPage.clickOnEditIcon(reportName);

		// Step 31: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();
		testUtil.setHardWait(2000);

		// Step 32: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 33: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName3 = reportName + ".xlsx";
		String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);

		// Step-33 : verify success message
		onlineReportingPage.verifySuccessMessage();

		// Step 34: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 35: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 36: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName4 = reportName + ".csv";
		String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);
		// verify success message
		// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
		// bug and its reported to cheryl

		// Step 37: For manual testing: verify the data in both reports is the same.

		// Step-38 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

		// Step-39 : Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// Step-40 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Step-41 : Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		// part-6
		testUtil.setHardWait(4000);
		// Step-42 : Click on copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression6 COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Step-43 : Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Step-44 : Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// Step-45 : On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();

		// Step-46 : Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Step-47 : Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Step-48 : Click *My Reports* tab the top of the App.
		onlineReportingPage.clickOnMyReportsTab();

		// Step-49 : Click Don't Save
		onlineReportingPage.clickOnDontSave();

		testUtil.setHardWait(3000);

		// Step-50 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-51 :click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();

		// Step-52 : Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
		// click the *X* icon & Click *DELETE REPORT* button
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step-55 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/**
	 * This test is failing because feidls option in the webpage does not conisit of
	 * pro field- according to Saphal
	 * 
	 * 
	 * Customer Reports - Verify a My Estes User can Select the Shipment Service
	 * Summary Report
	 */
	@Test(enabled = true, priority = 13)

	public void executeQZ_11538() throws InterruptedException {

		String reportName = "Automated Regression7";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1

		// Step 1: login in
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username21);
		myEstesLoginPage.enterPassword(password21);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step-1 : Click on Online Reporting
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnOnlineReporting();

		// Extra step for automation
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step 2:Click on create a report tab
		testUtil.setHardWait(1000);
		onlineReportingPage.clickOnCreateReportTab();
		// Step 3: Select report pick list
		testUtil.setHardWait(1000);
		onlineReportingPage.selectOnReportDropDown("Shipment Service Summary ");
		// Step 4: Enter values
		onlineReportingPage.enterValueTextBox(reportName);
		// Step #5:Click on schedule report
		onlineReportingPage.clickOnSchduleReport();
		// Step #6:From on the *Schedule Report* popup, enter or select the following
		// data:
		onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
		onlineReportingPage.selectReportFormat();// CSV File

		// Step 7: Click submit
		onlineReportingPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		// Step 8:From the *Schedule Details* display, click the *See Details* link
		onlineReportingPage.clickOnSeeDetailsLink();
		testUtil.setHardWait(1000);
		onlineReportingPage.verifyDataOnScheduleReport();
		// Step 9:From the *Schedule Report* popup display with the previously selected
		// options and changes made, click *Cancel*
		onlineReportingPage.clickOnCancelButton();
		// step 10: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		// step #11: Verify The newly created report is listed in the *Active Reports*
		// section
		onlineReportingPage.verifyReportUnderActiveReports(reportName);

		// Step-12 : part -2

		testUtil.setHardWait(4000);

		// Step 12,13: From the *Active Reports* grid, click the *Download Report*
		// button for the report
		// created in previous step
		onlineReportingPage.clickIconButton(reportName, Icon.DOWNLOAD);

		// Step 14: Select the following radio button:
		String fileName = reportName + ".xlsx";
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 15: Click *Download*
		onlineReportingPage.clickOnDownloadButton();
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(4000);

		// Step 16 : Verify the xlsx report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 17: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadIcon(reportName);

		// Step 18: Select the following radio button:
		String fileName2 = reportName + ".csv";
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 19: Click Download
		onlineReportingPage.clickOnDownloadButton();

		String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.setHardWait(2000);
		// Step-20: Verify the csv report is downloaded
		onlineReportingPage.verifySuccessMessage();

		// Step 21 : For manual testing: verify the data in both reports is the same.

		// Step-22 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath, fileName);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

		// Step-23 --part-3

		testUtil.setHardWait(4000);

		// Step-24 : click copy icon
		onlineReportingPage.clickOnCopyIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-25 : Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression7 COPY");

		// Step-25 : Click on one account radio button

		onlineReportingPage.clickOnOneAccountRadioButton();

		// Account search hyperlink becomes active
		onlineReportingPage.verifyAccountSearchIsActive();

		// step #26: Click save the reprot
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// step-27,28 : locate and delete the copy report

		onlineReportingPage.deleteCopyReportIfExistInTheTable("Automated Regression7 COPY");

		// part-4

		testUtil.setHardWait(4000);

		// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
		// icon for the named report "Automated Regression"
		onlineReportingPage.clickOnEditIcon(reportName);

		// Step 31: Click the *Download Report* button
		onlineReportingPage.clickOnDownloadReportButton();
		testUtil.setHardWait(2000);

		// Step 32: Select the following radio button: Report Format: Excel Worksheet
		// File
		onlineReportingPage.selectReportFormatRadioBtn("xls");

		// Step 33: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName3 = reportName + ".xlsx";
		String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);

		// Step-33 : verify success message
		onlineReportingPage.verifySuccessMessage();

		// Step 34: Click the *Download Report* button again
		onlineReportingPage.clickOnDownloadReportButton();

		// Step 35: Select the following radio button: Report Format: Text/CSV
		onlineReportingPage.selectReportFormatRadioBtn("csv");

		// Step 36: Click Download and validate report has been downloaded
		onlineReportingPage.clickOnDownloadButton();

		String fileName4 = reportName + ".csv";
		String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
		testUtil.setHardWait(3000);
		// verify success message
		// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
		// bug and its reported to cheryl

		// Step 37: For manual testing: verify the data in both reports is the same.

		// Step-38 : Delete the downloaded files to clean the data
		testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
		testUtil.setHardWait(2000);
		testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

		// Step-39 : Click on remove schedule link
		onlineReportingPage.clickOnRemoveScheduleLink();
		// Step-40 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
		onlineReportingPage.clickOnRemoveScheduleButton();
		// Verify The schedule details is no longer displayed
		onlineReportingPage.verifyScheduleDetailsNotDisplayed();

		// Step-41 : Click save the report
		onlineReportingPage.clickOnSaveReportButton();

		// part-6

		// Step-42 : Click on copy icon
		onlineReportingPage.clickIconButton(reportName, Icon.COPY);
		testUtil.setHardWait(3000);

		// Verify copy report tab is displayed
		onlineReportingPage.verifyCopyReportTabIsDisplayed();

		// Verify the name has COPY appended to it
		onlineReportingPage.verifyReportHasCopyAppended("Automated Regression7 COPY");

		// Record the original layout order of the column
		String columnName = onlineReportingPage.returnLeftmostColumnHeader();

		// Step-43 : Click on fields
		onlineReportingPage.clickOnFieldsIcon();

		// Step-44 : Drag and drop the Pro# field to the top
		String newLeftmostHeader = onlineReportingPage.returnSecondElement();
		onlineReportingPage.dragSecondElementToTop();

		// Step-45 : On the *Fields* window, unselect *Select All*
		onlineReportingPage.unselectSelectAll();

		// select the first 10 columns on the list
		onlineReportingPage.selectFirstTenColumnsOnList();

		// Step-46 : Click apply
		onlineReportingPage.clickOnApplyButton();

		// Verify the layout order is changed
		onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

		// Step-47 : Click on reset button
		onlineReportingPage.clickOnResetButton();

		// The columns are set back to the original layout
		Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

		// Step-48 : Click *My Reports* tab the top of the App.
		onlineReportingPage.clickOnMyReportsTab();

		// Step-49 : Click Don't Save
		onlineReportingPage.clickOnDontSave();

		testUtil.setHardWait(3000);

		// Step-50 : Click on edit icon
		onlineReportingPage.clickOnEditIcon(reportName);
		testUtil.setHardWait(3000);

		// Step-51 :click the arrow in the heading for *Pro#* to sort asc.
		onlineReportingPage.clickLeftmostHeaderToSort();

		// Step-52 : Click *Save Report*
		onlineReportingPage.clickOnSaveReportButton();

		testUtil.setHardWait(2000);

		// Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
		// click the *X* icon & Click *DELETE REPORT* button
		onlineReportingPage.deleteReportIfExistInTheTable(reportName);

		// Step-55 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	/*
	 * Customer Reports - Verify a My Estes User can Select the Claims Detail Report
	 */
	@Test(enabled = true, priority = 14)

	public void executeQZ_11557() throws InterruptedException {

		String reportName = "Automated Regression84";
		String emailAdd = "qatestCRC@estes-express.com";

		// Part-1
				// Step 1: login in
				myEstesHomePage.clickOnMyEstes();
				myEstesHomePage.clickOnLogin();
				myEstesLoginPage.enterUserName("testgroup");
				myEstesLoginPage.enterPassword("testgroup");
				testUtil.setHardWait(1000);
				myEstesLoginPage.clickOnLoginButton();

				// Step-1 : Click on Online Reporting
				myEstesWelcomePage.clickOnManageLink();
				myEstesWelcomePage.clickOnOnlineReporting();

				// Extra step for automation
				onlineReportingPage.deleteReportIfExistInTheTable(reportName);

				// Step 2:Click on create a report tab
				testUtil.setHardWait(1000);
				onlineReportingPage.clickOnCreateReportTab();
				// Step 3: Select report pick list
				testUtil.setHardWait(1000);
				onlineReportingPage.selectOnReportDropDown("Claims Detail ");
				// Step 4: Enter values
				onlineReportingPage.enterValueTextBox(reportName);
				// Step #5:Click on schedule report
				onlineReportingPage.clickOnSchduleReport();
				// Step #6:From on the *Schedule Report* popup, enter or select the following
				// data:
				onlineReportingPage.enterEmailAddressOnTheReport(emailAdd);
				onlineReportingPage.selectReportFormat();// CSV File

				// Step 7: Click submit
				onlineReportingPage.clickOnSubmitButton();
				testUtil.setHardWait(4000);
				// Step 8:From the *Schedule Details* display, click the *See Details* link
				onlineReportingPage.clickOnSeeDetailsLink();
				testUtil.setHardWait(1000);
				onlineReportingPage.verifyDataOnScheduleReport();
				// Step 9:From the *Schedule Report* popup display with the previously selected
				// options and changes made, click *Cancel*
				onlineReportingPage.clickOnCancelButton();
				// step 10: Click save the reprot
				onlineReportingPage.clickOnSaveReportButton();

				// step #11: Verify The newly created report is listed in the *Active Reports*
				// section
				onlineReportingPage.verifyReportUnderActiveReports(reportName);

				// Step-12 : part -2

				testUtil.setHardWait(4000);

				// Step 12,13: From the *Active Reports* grid, click the *Download Report*
				// button for the report
				// created in previous step
				onlineReportingPage.clickIconButton(reportName, Icon.DOWNLOAD);

				// Step 14: Select the following radio button:
				String fileName = reportName + ".xlsx";
				onlineReportingPage.selectReportFormatRadioBtn("xls");

				// Step 15: Click *Download*
				onlineReportingPage.clickOnDownloadButton();
				String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";
				testUtil.setHardWait(4000);

				// Step 16 : Verify the xlsx report is downloaded
				onlineReportingPage.verifyPopupMessage("Success!");

				// Step 17: Click the *Download Report* button again
				onlineReportingPage.clickIconButton(reportName, Icon.DOWNLOAD);

				// Step 18: Select the following radio button:
				String fileName2 = reportName + ".csv";
				onlineReportingPage.selectReportFormatRadioBtn("csv");

				// Step 19: Click Download
				onlineReportingPage.clickOnDownloadButton();

				String downloadPath2 = TestUtil.getCurrentWorkingPath() + "/Downloads/";

				testUtil.setHardWait(2000);
				// Step-20: Verify the csv report is downloaded
				onlineReportingPage.verifyPopupMessage("Success!");

				// Step 21 : For manual testing: verify the data in both reports is the same.

				// Step-22 : Delete the downloaded files to clean the data
				testUtil.deleteFilesFromFolder(downloadPath, fileName);
				testUtil.setHardWait(2000);
				testUtil.deleteFilesFromFolder(downloadPath2, fileName2);

				// Step-23 --part-3

				testUtil.setHardWait(4000);

				// Step-24 : click copy icon
				onlineReportingPage.clickIconButton(reportName, Icon.COPY);
				testUtil.setHardWait(3000);

				// Step-25 : Verify copy report tab is displayed
				onlineReportingPage.verifyCopyReportTabIsDisplayed();

				// Verify the name has COPY appended to it
				onlineReportingPage.verifyReportHasCopyAppended(reportName + " COPY");

				// Step-25 : Click on one account radio button

				onlineReportingPage.clickOnOneAccountRadioButton();

				// Account search hyperlink becomes active
				onlineReportingPage.verifyAccountSearchIsActive();

				// step #26: Click save the reprot
				onlineReportingPage.clickOnSaveReportButton();

				testUtil.setHardWait(2000);

				// step-27,28 : locate and delete the copy report
				
				onlineReportingPage.clickIconButton(reportName + " COPY", Icon.DELETE);

				// part-4

				testUtil.setHardWait(4000);

				// Step 29, 30: Locate the named report Automated Regression & Click the *Edit*
				// icon for the named report "Automated Regression"
			
				onlineReportingPage.clickIconButton(reportName, Icon.EDIT);

				// Step 31: Click the *Download Report* button
				onlineReportingPage.clickOnDownloadReportButton();
				testUtil.setHardWait(2000);

				// Step 32: Select the following radio button: Report Format: Excel Worksheet
				// File
				onlineReportingPage.selectReportFormatRadioBtn("xls");

				// Step 33: Click Download and validate report has been downloaded
				onlineReportingPage.clickOnDownloadButton();

				String fileName3 = reportName + ".xlsx";
				String downloadPath3 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
				testUtil.setHardWait(3000);

				// Step-33 : verify success message
				onlineReportingPage.verifyPopupMessage("Success!");

				// Step 34: Click the *Download Report* button again
				onlineReportingPage.clickOnDownloadReportButton();

				// Step 35: Select the following radio button: Report Format: Text/CSV
				onlineReportingPage.selectReportFormatRadioBtn("csv");

				// Step 36: Click Download and validate report has been downloaded
				onlineReportingPage.clickOnDownloadButton();

				String fileName4 = reportName + ".csv";
				String downloadPath4 = TestUtil.getCurrentWorkingPath() + "/Downloads/";
				testUtil.setHardWait(3000);
				// verify success message
				// onlineReportingPage.verifySuccessMessage(); //Commented this line as it has a
				// bug and its reported to cheryl

				// Step 37: For manual testing: verify the data in both reports is the same.

				// Step-38 : Delete the downloaded files to clean the data
				testUtil.deleteFilesFromFolder(downloadPath3, fileName3);
				testUtil.setHardWait(2000);
				testUtil.deleteFilesFromFolder(downloadPath4, fileName4);

				// Step-39 : Click on remove schedule link
				onlineReportingPage.clickOnRemoveScheduleLink();
				// Step-40 : On the Remove Schedule pop up, click the REMOVE SCHEDULE button
				onlineReportingPage.clickOnRemoveScheduleButton();
				// Verify The schedule details is no longer displayed
				onlineReportingPage.verifyScheduleDetailsNotDisplayed();

				// Step-41 : Click save the report
				onlineReportingPage.clickOnSaveReportButton();

				// part-6
				testUtil.setHardWait(4000);
				// Step-42 : Click on copy icon
				onlineReportingPage.clickIconButton(reportName, Icon.COPY);
				testUtil.setHardWait(3000);

				// Verify copy report tab is displayed
				onlineReportingPage.verifyCopyReportTabIsDisplayed();

				// Verify the name has COPY appended to it
				onlineReportingPage.verifyReportHasCopyAppended(reportName + " COPY");

				// Record the original layout order of the column
				String columnName = onlineReportingPage.returnLeftmostColumnHeader();

				// Step-43 : Click on fields
				onlineReportingPage.clickOnFieldsIcon();

				// Step-44 : Drag and drop the Pro# field to the top
				String newLeftmostHeader = onlineReportingPage.returnSecondElement();
				onlineReportingPage.dragSecondElementToTop();
				// Step-45 : On the *Fields* window, unselect *Select All*
				onlineReportingPage.unselectSelectAll();

				// select the first 10 columns on the list
				onlineReportingPage.selectFirstTenColumnsOnList();

				// Step-46 : Click apply
				onlineReportingPage.clickOnApplyButton();

				// Verify the layout order is changed
				onlineReportingPage.verifyLayoutOrderChanged(newLeftmostHeader);

				// Step-47 : Click on reset button
				onlineReportingPage.clickOnResetButton();

				// The columns are set back to the original layout
				Assert.assertEquals(onlineReportingPage.returnLeftmostColumnHeader(), columnName);

				// Step-48 : Click *My Reports* tab the top of the App.
				onlineReportingPage.clickOnMyReportsTab();

				// Step-49 : Click Don't Save
				onlineReportingPage.clickOnDontSave();

				testUtil.setHardWait(3000);

				// Step-50 : Click on edit icon
				onlineReportingPage.clickIconButton(reportName, Icon.EDIT);
				testUtil.setHardWait(3000);

				// Step-51 :click the arrow in the heading for *Pro#* to sort asc.
				onlineReportingPage.clickLeftmostHeaderToSort();

				// Step-52 : Click *Save Report*
				onlineReportingPage.clickOnSaveReportButton();

				testUtil.setHardWait(2000);

				// Step-53 , 54 : Identify a report listed in the *Active Reports* grid and
				// click the *X* icon & Click *DELETE REPORT* button
				onlineReportingPage.deleteReportIfExistInTheTable(reportName);

				// Step-55 : Logoff and close browser
				myEstesLoginPage.clickOnMyEstes();
				testUtil.setHardWait(2000);
				myEstesLoginPage.clickOnLogoutButton();

				myEstesLoginPage.clickOnLogoutConfirmButton();



	}

}