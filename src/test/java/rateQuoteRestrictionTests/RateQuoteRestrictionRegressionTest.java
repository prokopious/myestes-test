package rateQuoteRestrictionTests;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import rateQuoteRestrictionMaintenancePages.RateQuoteRestrictionMaintenanceLoginPage;
import rateQuoteRestrictionMaintenancePages.RateQuoteRestrictionMaintenancePage;
import rateQuoteRestrictionMaintenancePages.RestrictionSetUpPage;
import testBase.TestBase;

public class RateQuoteRestrictionRegressionTest extends TestBase{

	private final Logger logger= Logger.getLogger(RateQuoteRestrictionRegressionTest.class);

	RateQuoteRestrictionMaintenanceLoginPage rateQuoteRestrictionMaintenanceLoginPage = new RateQuoteRestrictionMaintenanceLoginPage();
	RestrictionSetUpPage restrictionSetUpPage = new RestrictionSetUpPage();
	RateQuoteRestrictionMaintenancePage rateQuoteRestrictionMaintenancePage= new RateQuoteRestrictionMaintenancePage();

	/*******************************************************TESTS****************************************/

	/**
	 *@author Ajitha
	 *
	 * RQRM - Verify Restrictions Details "Clear" button cleared the text and selections of all fields when the user clicked Clear button
	 *
	 */


	@Test(enabled = true, priority = 1)

	public void executeQZ_12252() {

		String eventName = "Regression test case to Clear option";
		String effDate = testUtil.getBusinessDate("DATE", +4);
		String expDate = testUtil.getBusinessDate("DATE", +5);
		String terminal = "202 - KENT";
		String zipCodeOption = "Select All";
		String msg ="This restriction created to test clear button functionality";

		//Step 1: Open the following test template to capture the URL and Test account details:
		//https://ratequote-restriction-rstr-qa.apps.ocpnonprod.estesinternal.com/login.html
		//Step 2: Open the above captured URL in a browser:
		driver.get(rateQuoteRestrictionUrl);

//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		
		//Step 3: From login page enter the following values:
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);
		//Step 4: Click *Login*
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();
		
		//Step 5: From the *RESTRICTIONS DETAILS* section,
		restrictionSetUpPage.enterEventName(eventName);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(expDate);
	
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.clickOnBothDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg);

		//Step 6: Click *Clear*
		restrictionSetUpPage.clickOnClearButton();
		//Step 7: Verify the Restrictions Details section got reset and all fields got cleared from above entered data:
		restrictionSetUpPage.verifyRestrictionDetailsCleared();
		//Step 8: From the Restrictions home page,
		restrictionSetUpPage.logout();


	}




	/**
	 * @author Ajitha
	 * RQRM - Verify RQRM displayed overlapping warning message when the user is
	 * creating a new restriction and it's overlapping with existing restriction
	 * between effective and expiration dates
	 */


	@Test(enabled = true, priority = 2)

	public void executeQZ_12258() {

		String eventName = "Regression test case for create overlap message";
		String effDate = testUtil.getBusinessDate("DATE", +5);
		String expDate = testUtil.getBusinessDate("DATE", +5);
		String overlapDate = testUtil.getBusinessDate("DATE", +4);
		String terminal = "202 - KENT";
		String zipCodeOption = "Deselect All";
		String zipCode1 ="98004";
		String zipCode2 ="98005";
		String zipCode3 ="98006";
		String successMsg = "Information successfully saved.";
		String overlapMsg = "This restriction created to test overlapping warning message when user creating new restriction with existing combination";
		//String restrictionWarningMsg = "The selected zip codes and direction already has an existing rate quote restriction with dates: "+effDate+" to " +expDate+". Do you want to proceed?";
		String restrictionWarningMsg= "Restriction Warning";
		//Step 1 &2 : Open the following test template to capture the URL and Test account details:

		driver.get(rateQuoteRestrictionUrl);

//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		//Step 3:From login page enter the following values:

		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);
		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();
		//Step 5: From the *RESTRICTIONS DETAILS* section,

		restrictionSetUpPage.enterEventName(eventName);
		testUtil.setHardWait(1000);

		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(2000);
		restrictionSetUpPage.enterExpirationDate(expDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.checkZipCode(zipCode1);
		restrictionSetUpPage.checkZipCode(zipCode2);
		restrictionSetUpPage.checkZipCode(zipCode3);
		restrictionSetUpPage.clickOnOutboundDirection();

		restrictionSetUpPage.enterRateQuoteResponseMsg(overlapMsg);

		//Step 6: Click Add button
		restrictionSetUpPage.clickOnAddButton();
		//The Restrictions page will display the following message: <*Information successfully saved.* >
		rateQuoteRestrictionMaintenancePage.bypassIfRestrictionMessage();
	
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);
		//Step 7: From the *RESTRICTIONS DETAILS* section,
		//enter the following values:
		restrictionSetUpPage.enterEventName(eventName);
		restrictionSetUpPage.enterEffectiveDate(overlapDate);
		restrictionSetUpPage.enterExpirationDate(overlapDate);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.checkZipCode(zipCode1);
		restrictionSetUpPage.checkZipCode(zipCode2);
		restrictionSetUpPage.checkZipCode(zipCode3);
		restrictionSetUpPage.clickOnOutboundDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(overlapMsg);

		//Step 8: Click Add
		restrictionSetUpPage.clickOnAddButton();
		
		//Step 9: Verify the Restrictions page displayed with the following pop-up Restriction Warning Message window :
		testUtil.setHardWait(1000);
		
		restrictionSetUpPage.verifyWarningMsg(restrictionWarningMsg);
		testUtil.setHardWait(1000);
		rateQuoteRestrictionMaintenancePage.bypassIfRestrictionMessage();
		//Step 10: From the Restriction Warning pop-up window:
		//restrictionSetUpPage.clickOnConfirmationYesBtn();

		//The Restrictions page will display the following message: <*Information successfully saved.* >
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);

		//Step 11: Note: When you have many restrictions in the table, it's difficult to find the above created restriction. In this case, continue below steps to find created
		//restriction  otherwise you can skip below search steps and go to Validation step#15
		//Step 12: From the Restrictions home page, under Restrictions table scroll down to the end.
		//Then click the Row Count drop down and click the following number: Row count - 500
		restrictionSetUpPage.selectRowCount("500");

		//Step 13: From the Restrictions home page, Click  the following keys:
		//Step 14: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details.

		//Step 14 :Validate the Restrictions table displayed the following columns with data used in above creation process
		restrictionSetUpPage.verifyRowIsDisplayed(eventName, terminal, "98004 ,98005 ...(3)", "OUT", effDate, expDate, overlapMsg);

		//Step 15: The following steps were added to delete the restriction upon successful execution of above steps:
		//Note: Delete steps should avoid errors in Rate Quote regression / smoke / Manual test cases.
		//Step 16: The following steps were added to delete the restriction upon successful execution of above steps:
		//Note: Delete steps should avoid errors in Rate Quote regression / smoke / Manual test cases.
		//Step 17: From the Restrictions table, on the right side of page against above created restriction under *Actions* column:
		//Click *Delete*
		//Step 18: From the *Delete Restriction Warning* pop-up message, check the message displayed with above selected restriction details:
		//Step 19: Click Yes

		restrictionSetUpPage.deleteRow(eventName, terminal, "98004 ,98005 ...(3)", "OUT", effDate, expDate, overlapMsg);

		//Step 20: Repeat the *Delete steps 17 , 18 and 19* for all above created restrictions.
		//All above created restrictions got deleted from the Restrictions table.
		//restrictionSetUpPage.deleteRow(eventName, terminal, "98004 ,98005 ...(3)", "OUT", effDate, expDate, overlapMsg);  --changed to below line
		restrictionSetUpPage.deleteCreatedRow(eventName, terminal, "98004 ,98005 ...(3)", "OUT", effDate, expDate, overlapMsg); //added newly

		//Step 21: Logout
		restrictionSetUpPage.logout();


	}

	/**
	 * @author - Ajitha
	 * RQRM - Verify "Restrictions" table displayed with restriction when the
	 * admin user created a new restriction with ONLY required fields and Multiple Zip Codes
	 */


	@Test(enabled = true, priority = 3)
	public void executeQZ_12192() {

		String eventName = "Regression test case to create with only required fields";
		String effDate = testUtil.getBusinessDate("DATE", +1);
		String expDate = testUtil.getBusinessDate("DATE", +10);
		String terminal = "243 - MEDFORD";
		String zipCodeOption = "Deselect All";
		String zipCode1 ="95531";
		String zipCode2 ="95538";
		String zipCode3 ="95567";
		String successMsg = "Information successfully saved.";
		String msg = "This test case validates, whether admin user is able to create a restriction successfully by entering only required fields for a specific Terminal with multiple zip codes in a direction between effective and expiration dates. Then the same is displayed in the Restrictions table";

		//Step 1: Open the following test template to capture the URL and Test account details:
		//Step 2: Open the above captured URL in a browser:
		driver.get(rateQuoteRestrictionUrl);
//
//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		
		//Step 3:From login page enter the following values:
		//Note: You can use tester credentials or above generic account details
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);

		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();

		//Step 5: From the *RESTRICTIONS DETAILS* section,
		//enter the following values:
		/*
		 *  1. Effective Date: <Current Date+1 in MM/DD/YYYY >
			2. Expiration Date: <Current Date+10 in MM/DD/YYYY>
			3. Terminal: 243 (Select from drop down or user can type 243 to search on field)
			4. *Zip code*: From drop down click *X Deselect All* then select the top 3 check-boxes *95531, 95538, 95567*
			5. Direction: Both (Radio button)
			6. Rate Quote Response Message: <This test case validates, whether admin user is able to create a restriction successfully by entering only required fields
			for a specific Terminal with multiple zip codes in a direction between effective and expiration dates. Then the same is displayed in the Restrictions table.>
		 */
		restrictionSetUpPage.enterEventName(eventName);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(expDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.checkZipCode(zipCode1);
		restrictionSetUpPage.checkZipCode(zipCode2);
		restrictionSetUpPage.checkZipCode(zipCode3);
		restrictionSetUpPage.clickOnBothDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg);

		//Step 6: Click Add button
		restrictionSetUpPage.clickOnAddButton();

		//The Restrictions page will display the following message: <Information successfully saved. >
		//Note: The message will be displayed above the RESTRICTIONS DETAILS title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);

		//Step 7: Note: When you have many restrictions in the table, it's difficult to find the above created restriction.
		//In this case, continue below steps to find created restriction  otherwise you can skip below search steps and go to Validation step#11
		//Step 8: From the Restrictions home page, under Restrictions table scroll down to the end.
		//Then click the Row Count drop down and click the following number:
		restrictionSetUpPage.selectRowCount("500");

		//Step 9: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details
		//Step 10: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details.
		//Validate the Restrictions table displayed the following columns with data used in above creation process
		restrictionSetUpPage.verifyRowIsDisplayed(eventName, terminal, "95531 ,95538 ...(3)", "BOTH", effDate, expDate, msg);

		//Deleting the created data for clean up
		restrictionSetUpPage.deleteRow(eventName, terminal, "95531 ,95538 ...(3)", "BOTH", effDate, expDate, msg);

		//Logout
		restrictionSetUpPage.logout();

	}

	/**@author - Ajitha
	 * RQRM - Verify "Restrictions" table displayed with
	 * restriction when the admin user created a new restriction with all fields
	 */

	@Test(enabled = true, priority = 4)
	public void executeQZ_12193() {

		String eventName = "Regression test case with all fields";
		String effDate = testUtil.getBusinessDate("DATE", +1);
		String expDate = testUtil.getBusinessDate("DATE", +10);
		String terminal = "202 - KENT";
		String zipCodeOption = "Select All";
		String successMsg = "Information successfully saved.";
		String msg = "This test case validates, whether admin user is able to create a restriction successfully by entering all fields for a specific Terminal with all zip codes in a direction between effective and expiration dates. Then the same is displayed in the Restrictions table.";

		//Step 1: Open the following test template to capture the URL and Test account details:
		//Step 2: Open the above captured URL in a browser:
		driver.get(rateQuoteRestrictionUrl);

		
//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		
		//Step 3:From login page enter the following values:
		//Note: You can use tester credentials or above generic account details
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);

		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();

		//Step 5: From the *RESTRICTIONS DETAILS* section,
		//enter the following values:
		/*
		 *  1. Effective Date: <Current Date+1 in MM/DD/YYYY >
			2. Expiration Date: <Current Date+10 in MM/DD/YYYY>
			3. Terminal: 243 (Select from drop down or user can type 243 to search on field)
			4. *Zip code*: From drop down click *X Deselect All* then select the top 3 check-boxes *95531, 95538, 95567*
			5. Direction: Both (Radio button)
			6. Rate Quote Response Message: <This test case validates, whether admin user is able to create a restriction successfully by entering only required fields
			for a specific Terminal with multiple zip codes in a direction between effective and expiration dates. Then the same is displayed in the Restrictions table.>
		 */
		restrictionSetUpPage.enterEventName(eventName);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(expDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.clickOnInboundDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg);

		//Step 6: Click Add button
		restrictionSetUpPage.clickOnAddButton();

		//The Restrictions page will display the following message: <Information successfully saved. >
		//Note: The message will be displayed above the RESTRICTIONS DETAILS title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);

		//Step 7: Note: When you have many restrictions in the table, it's difficult to find the above created restriction.
		//In this case, continue below steps to find created restriction  otherwise you can skip below search steps and go to Validation step#11
		//Step 8: From the Restrictions home page, under Restrictions table scroll down to the end.
		//Then click the Row Count drop down and click the following number:
		restrictionSetUpPage.selectRowCount("500");

		//Step 9: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details
		//Step 10: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details.
		//Step11: Validate the Restrictions table displayed the following columns with data used in above creation process
		restrictionSetUpPage.verifyRowIsDisplayed(eventName, terminal, "ALL ZIP CODES", "IN", effDate, expDate, msg);

		//Step 12 to 15: Deleting the created data for clean up
		restrictionSetUpPage.deleteRow(eventName, terminal, "ALL ZIP CODES", "IN", effDate, expDate, msg);

		//Step 16: Logout
		restrictionSetUpPage.logout();
	}


	/**@author - Ajitha
	 * RQRM - Verify on Rate Quote Restriction Maintenance app that the
	 * admin user is able to create a new restriction with ONLY required fields
	 */


	@Test(enabled = true, priority = 5)
	public void executeQZ_12194() {

//		String effDate = testUtil.getBusinessDate("DATE", +1);  -removed (test steps as been updated to current date)
//		String expDate = testUtil.getBusinessDate("DATE", +10); -removed
		
		String effDate = testUtil.todaysDate(); //-Changed to current date
		String expDate = testUtil.todaysDate(); //changed to current date

		String terminal = "202 - KENT";
		String zipCodeOption = "Deselect All";
		String zipCode1 ="98004";
		String zipCode2 ="98005";
		String zipCode3 ="98006";
		String msg = "This test case validates, whether admin user is able to create a new restriction with ONLY required fields";
		String successMsg = "Information successfully saved.";

		//Step 1: Open the following test template to capture the URL and Test account details:
		//Step 2: Open the above captured URL in a browser:
		driver.get(rateQuoteRestrictionUrl);

//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		
		//Step 3:From login page enter the following values:
		//Note: You can use tester credentials or above generic account details
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);

		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();
		rateQuoteRestrictionMaintenanceLoginPage.validateLoginPage();
		
		
		//Step 5: From the *RESTRICTIONS DETAILS* section,
		//enter the following values:
		/*
		 *  1. Effective Date: <Current Date+1 in MM/DD/YYYY >
			2. Expiration Date: <Current Date+10 in MM/DD/YYYY>
			3. Terminal: 243 (Select from drop down or user can type 243 to search on field)
			4. *Zip code*: From drop down click *X Deselect All* then select the top 3 check-boxes *95531, 95538, 95567*
			5. Direction: Both (Radio button)
			6. Rate Quote Response Message: <This test case validates, whether admin user is able to create a restriction successfully by entering only required fields
			for a specific Terminal with multiple zip codes in a direction between effective and expiration dates. Then the same is displayed in the Restrictions table.>
		 */
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(expDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.checkZipCode(zipCode1);
		restrictionSetUpPage.checkZipCode(zipCode2);
		restrictionSetUpPage.checkZipCode(zipCode3);
		restrictionSetUpPage.clickOnOutboundDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg);

		//Step 6: Click Add button
		restrictionSetUpPage.clickOnAddButton();

		//Step 7: The Restrictions page will display the following message: <Information successfully saved. >
		//Note: The message will be displayed above the RESTRICTIONS DETAILS title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);
		
		//Deleting the created data for clean up
		restrictionSetUpPage.selectRowCount("500");
		restrictionSetUpPage.deleteRow(terminal,  "98004 ,98005 ...(3)", "OUT", effDate, expDate, msg);

		//Step 8: Logout
		restrictionSetUpPage.logout();
	}


	/**@author - Ajitha
	 * RQRM - Verify "Effective Date" field displayed with error message to correct the date when
	 * user entered date is less than current date while creating restriction
	 * @throws Exception
	 */

	@Test(enabled = true, priority = 6)
	public void executeQZ_12257() throws Exception {

		String effDate = testUtil.changeDateFormat(testUtil.addOrSubstractDateFromTodayDateFormat(-3),"MMddyy","MM/dd/yyyy");
		String msg = "Effective date must be "+testUtil.changeDateFormat(testUtil.getTodayDate(),"MMddyy","MM/dd/yyyy")+" or later.";

		//Step 1: Open the following test template to capture the URL and Test account details:
		//Step 2: Open the above captured URL in a browser:
		driver.get(rateQuoteRestrictionUrl);

		
//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
//		
		//Step 3:From login page enter the following values:
		//Note: You can use tester credentials or above generic account details
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);

		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();

		//Step 5: From the *RESTRICTIONS DETAILS* section,
		//enter the following values:
		/*
		 *  1. Effective Date: <Current Date-3 in MM/DD/YYYY >
		 */
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate("");

		//Step 6: Validate Alert pop-window displayed with below message, as soon as the above previous date is entered :
		//*Alert*: 		Effective date must be MM/DD/YYYY or later.
		//Note: MM/DD/YYYY = Current Date
		restrictionSetUpPage.verifyWarningMsg(msg);

		//Step 7: Click Ok
		restrictionSetUpPage.clickOnConfirmationOkBtn();

		//Step 8: From Restrictions Details section,
		//Verify the *Effective Date* field got reset and no dates displayed:
		restrictionSetUpPage.verifyEffDateIsReset();

		//Step 9: Logout
		restrictionSetUpPage.logout();
	}


	/**
	 * RQRM - Verify the deleted restriction record is not displayed in the Restrictions table
	 * when the user clicked Yes on the delete warning message
	 */

	@Test(enabled = true, priority = 7)
	public void executeQZ_12254() throws ParseException {

		String eventName = "Regression test case for delete message";
		String effDate = testUtil.getBusinessDate("DATE", +3);
		String expDate = testUtil.getBusinessDate("DATE", +5);
		String terminal = "202 - KENT";
		String zipCodeOption = "Select All";
		String msg = "This restriction created to test delete warning message when user deleting an existing restriction";
		String successMsg = "Information successfully saved.";

		//Step 1: Open the following test template to capture the URL and Test account details:
		//Step 2: Open the above captured URL in a browser:
		driver.get(rateQuoteRestrictionUrl);

//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		
		
		//Step 3:From login page enter the following values:
		//Note: You can use tester credentials or above generic account details
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);

		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();

		//Step 5: From the *RESTRICTIONS DETAILS* section,
		//enter the following values:
		/*
		 *  - *Event Name*: <Regression test case for delete message>
			- *Effective Date*: <Current Date+3 in MM/DD/YYYY >
			- *Expiration Date*: <Current Date+5 in MM/DD/YYYY>
			- *Terminal*: 202 (Select from drop down or user can type 202 to search on field)
			- *Zip code*: <ALL ZIP CODES>
			- *Direction*: Both (Radio button)
			- *Rate Quote Response Message*: <This restriction created to test delete warning message when user deleting an existing restriction>
		 */
		restrictionSetUpPage.enterEventName(eventName);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(expDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.clickOnBothDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg);

		//Step 6: Click Add button
		restrictionSetUpPage.clickOnAddButton();
		
		testUtil.setHardWait(9000);  //added
		
		//The Restrictions page will display the following message: <Information successfully saved. >
		//Note: The message will be displayed above the RESTRICTIONS DETAILS title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);
		
		//Not in the test steps:
		testUtil.reloadPage();        //added newly
		testUtil.setHardWait(9000);   //added newly
		
		restrictionSetUpPage.verifyRowIsDisplayed(eventName, terminal, "ALL ZIP CODES", "BOTH", effDate, expDate, msg);

		//Step 7: Note: When you have many restrictions in the table, it's difficult to find the above created restriction. In this case, continue below steps to find created restriction
		//otherwise you can skip below search steps and go to step#11
		//Step 8: From the Restrictions home page, under Restrictions table scroll down to the end. Then click the Row Count drop down and click the following number:
		restrictionSetUpPage.selectRowCount("500");

		//Step 9: From the Restrictions home page,
		//Click  the following keys:
		//Note: Keys from your machine keypad or you can click <find> option from the menu of the browser
		//Step 10: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details.
		//Step 11: From the Restrictions table, on the right side of page against above created restriction under *Actions* column:
		//Click *Delete*
		//Step 12: Verify the *Delete Restriction Warning* pop-up window displayed with the following message and above selected restriction details:
		//Step 13: Click Yes
		//restrictionSetUpPage.deleteRow(eventName, terminal, "ALL ZIP CODES", "BOTH", effDate, expDate, msg);  --changed to below line
		testUtil.setHardWait(9000);   //added newly
		restrictionSetUpPage.deleteCreatedRow(eventName, terminal, "ALL ZIP CODES", "BOTH", effDate, expDate, msg);
		
		//Not in test steps:
		restrictionSetUpPage.logout();
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();
		testUtil.reloadPage();      
		testUtil.setHardWait(9000);   

		//Step 14: From the Restrictions table, validate the above deleted restriction is not displayed.
		//*Event Name* : Regression test case for delete message
		restrictionSetUpPage.verifyRowIsNotDisplayed(eventName, terminal, "ALL ZIP CODES", "BOTH", effDate, expDate, msg);

		//Step 15: From the Restrictions home page,
		//Click *LOGOUT*
		restrictionSetUpPage.logout();
	}
	
	
	/** @author - Ajitha

	 *

	 * RQRM - Verify RQRM displayed overlapping warning message when the user is updating an existing restriction

	 * and overlapping with an existing restriction between effective and expiration dates

	 * @throws Exception

	 */


	@Test(enabled = true, priority = 8)


	public void executeQZ_12251() throws Exception {


		String eventName = "Regression test case for edit overlap message";
		String eventName1 = "Regression test case to see edit overlap message";
		String effDate = testUtil.changeDateFormat(testUtil.addOrSubstractDateFromTodayDateFormat(3),"MMddyy","MM/dd/yyyy");
		String expDate = testUtil.changeDateFormat(testUtil.addOrSubstractDateFromTodayDateFormat(5),"MMddyy","MM/dd/yyyy");
		String currentDate = testUtil.getTodayDateByFormat("MM/dd/yyyy");
		String effDate1 = testUtil.changeDateFormat(testUtil.addOrSubstractDateFromTodayDateFormat(4),"MMddyy","MM/dd/yyyy");
		String expDate1 = testUtil.changeDateFormat(testUtil.addOrSubstractDateFromTodayDateFormat(4),"MMddyy","MM/dd/yyyy");
		String terminal = "202 - KENT";
		String zipCodeOption = "Deselect All";
		String zipCodeOption1 = "Select All";
		String zipCode1 ="98004";
		String zipCode2 ="98005";
		String zipCode3 ="98006";
		String successMsg = "Information successfully saved.";
		String msg = "This restriction created to test overlapping warning message when user editing an existing restriction and same combination restriction exits";
		String msg1 ="This restriction created to test overlapping warning message when user editing an existing restriction and same combination restriction already exists.";
		String warningMsg = "The selected zip codes and direction already has an existing rate quote restriction with dates: "+effDate+" to "+expDate+". Do you want to proceed?";
		//Step 1: Open the following test template to capture the URL and Test account details:

		//Step 2: Open the above captured URL in a browser:

		driver.get(rateQuoteRestrictionUrl);
		//Step 3:From login page enter the following values:

//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
		
		//Note: You can use tester credentials or above generic account details
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);
		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();
		//Step 5: From the *RESTRICTIONS DETAILS* section,

		//enter the following values:

		/*

		 *  - *Event Name*: <Regression test case for edit overlap message>
			- *Effective Date*: <Current Date+3 in MM/DD/YYYY >
			- *Expiration Date*: <Current Date+5 in MM/DD/YYYY>
			- *Terminal*: 202 (Select from drop down or user can type 202 to search on field)
			- *Zip code*: From drop down click *X Deselect All* then select the check-boxes *98004, 98005, 98006*
			- *Direction*: Outbound (Radio button)
			- *Event Name*: <This restriction created to test overlapping warning message when user editing an existing restriction and same combination restriction exits>

		 */
		restrictionSetUpPage.enterEventName(eventName);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(effDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(expDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.checkZipCode(zipCode1);
		restrictionSetUpPage.checkZipCode(zipCode2);
		restrictionSetUpPage.checkZipCode(zipCode3);
		restrictionSetUpPage.clickOnOutboundDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg);


		//Step 6: Click Add button

		restrictionSetUpPage.clickOnAddButton();
		//The Restrictions page will display the following message: <*Information successfully saved.* >
		
		//Note: The message will be displayed above the RESTRICTIONS DETAILS" title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);

		//Step 7: From the *RESTRICTIONS DETAILS* section,

		//enter the following values:

		/*

		 *  - *Event Name*: <Regression test case to see edit overlap message>
			- *Effective Date*: <Current Date in MM/DD/YYYY >
			- *Expiration Date*: <Current Date in MM/DD/YYYY>
			- *Terminal*: 202 (Select from drop down or user can type 202 to search on field)
			- *Zip code*: From drop down click *X Deselect All* then select the check-boxes *98004, 98005, 98006*
			- *Direction*: Outbound (Radio button)
			- *Rate Quote Response Message*: <This restriction created to test overlapping warning message when user editing an existing restriction and same  combination restriction already exists.>
		 */

		restrictionSetUpPage.enterEventName(eventName1);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterEffectiveDate(currentDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.enterExpirationDate(currentDate);
		testUtil.setHardWait(1000);
		restrictionSetUpPage.selectTerminal(terminal);
		restrictionSetUpPage.selectZipCode(zipCodeOption);
		restrictionSetUpPage.checkZipCode(zipCode1);
		restrictionSetUpPage.checkZipCode(zipCode2);
		restrictionSetUpPage.checkZipCode(zipCode3);
		restrictionSetUpPage.clickOnOutboundDirection();
		restrictionSetUpPage.enterRateQuoteResponseMsg(msg1);

		//Step 8: Click Add button

		restrictionSetUpPage.clickOnAddButton();

		//The Restrictions page will display the following message: <*Information successfully saved.* >

		//Note: The message will be displayed above the "RESTRICTIONS DETAILS" title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);

		//Note: When you have many restrictions in the table, it's difficult to find the above created restriction.
		//In this case, continue below steps to find created restriction  otherwise you can skip below search steps and go to step#13
		//Step 10: From the Restrictions home page, under Restrictions table scroll down to the end.
		//Then click the Row Count drop down and click the following number:
		//Step 11: From the Restrictions home page,
		//Click  the following keys:
		//Note: Keys from your machine keypad or you can click <find> option from the menu of the browser
		//Step 12: From the Search pop-up box, enter the following value:
		//Note: User will search the restriction using above created restriction details.
		restrictionSetUpPage.selectRowCount("500");
		restrictionSetUpPage.verifyRowIsDisplayed(eventName1, terminal, "98004 ,98005 ...(3)", "OUT", currentDate, currentDate, msg1);

		//Step 13: From the Restrictions table, on the right side of page against above created restriction under *Actions* column: Click *Edit*

		//Step 14: The above selected restriction for editing is displayed in the Restrictions Details section
		restrictionSetUpPage.editRow(eventName1, terminal, "98004 ,98005 ...(3)", "OUT", currentDate, currentDate, msg1);

		//Step 15: From the Restrictions Details section, do the Update/Modify the following fields :

		/*
		 *  - *Effective Date*: <Current Date+4 in MM/DD/YYYY >
			- *Expiration Date*: <Current Date+4 in MM/DD/YYYY>
			- *Zip Code* : Click *Select All* from the drop down
			- *Direction*: Click *Both* radio button
		 */

		restrictionSetUpPage.enterEffectiveDate(effDate1);
		restrictionSetUpPage.enterExpirationDate(expDate1);
		restrictionSetUpPage.selectZipCode(zipCodeOption1);
		restrictionSetUpPage.clickOnBothDirection();

		//Step 16: Click *Update*

		restrictionSetUpPage.clickOnUpdateBtn();

		//Step 17: Verify the Restrictions page displayed with the following pop-up Restriction Warning Message window :

		//<*The selected zip codes and direction already has an existing rate quote restriction with dates: DD/MM/YYYY to DD/MM/YYYY. Do you want to proceed?*>

		//Note: If there are no existing restriction in the Restrictions table, then the DD/MM/YYY will be displayed with above created Restriction dates from Step 5.

		restrictionSetUpPage.verifyWarningMsg(warningMsg);

		//Step 18: From the Restriction Warning pop-up window: Click *Yes*
		restrictionSetUpPage.clickOnConfirmationYesBtn();
		//The Restrictions page will display the following message: <*Information successfully saved.* >

		//Note: The message will be displayed above the "RESTRICTIONS DETAILS" title
		restrictionSetUpPage.verifySuccessMsg();
		restrictionSetUpPage.verifyMsg(successMsg);
		restrictionSetUpPage.verifyRowIsDisplayed(eventName1, terminal, "ALL ZIP CODES", "BOTH", effDate1, expDate1, msg1);


		//Step 19: The following steps were added to delete the restriction upon successful execution of above steps:
		//Note: Delete steps should avoid errors in Rate Quote regression / smoke / Manual test cases.
		//Step 20: From the Restrictions table, on the right side of page against above created restriction under *Actions* column:Click *Delete*
		//Step 21: From the *Delete Restriction Warning* pop-up message, check the message displayed with above selected restriction details:
		//Warning Message: *Are you sure that you want to delete Restriction: Terminal: 202 - KENT with Dates: MM/DD/YYYY to MM/DD/YYYY ?*
		//Step 22: Click YES
		
		//Step 23: Repeat the *Delete steps 20 , 21 and 22* for all above created restrictions.
		restrictionSetUpPage.deleteRow(eventName1, terminal, "ALL ZIP CODES", "BOTH", effDate1, expDate1, msg1);
		restrictionSetUpPage.deleteRow(eventName, terminal, "98004 ,98005 ...(3)", "OUT", effDate, expDate, msg);

		//Step 24: From the Restrictions home page, Click *LOGOUT*
		restrictionSetUpPage.logout();

	}

	/**
	 * @author - Ajitha
	 *
	 * RQRM - Verify "Required Field" text displayed right below the fields which are tagged with asterisk(*) when the user missed to
	 * enter or select the Mandatory fields with values and clicked Add
	 */


	@Test(enabled = true, priority = 9)

	public void executeQZ_12256() {

		String effDateField = "Effective Date";
		String expDateField = "Expiration Date";
		String terminalField = "Terminal";
		String zipCodeField = "Zip Code";
		String directionField = "Direction";
		String rateQuoteResMsgField = "Rate Quote Response Message";

		//Step 1: Open the following test template to capture the URL and Test account details:

		//Step 2: Open the above captured URL in a browser:

		driver.get(rateQuoteRestrictionUrl);
		//Step 3:From login page enter the following values:
		//Note: You can use tester credentials or above generic account details

//		/**
//		 * the following lines (4 lines) added to click on <advanced> and then proceed
//		 * with unsafe mode before login in the website this is for OCP4 testing
//		 */
//
//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		element.click();
//		newElement.click();
//		
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);
		
		//Step 4: Click Login
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton();

		//Step 5: From the *RESTRICTIONS DETAILS* section,
		//do not enter the following values:
		restrictionSetUpPage.enterEventName("");
		restrictionSetUpPage.enterEffectiveDate("");
		restrictionSetUpPage.enterExpirationDate("");
		restrictionSetUpPage.enterRateQuoteResponseMsg("");


		//Step 6: Click Add

		restrictionSetUpPage.clickOnAddButton();
		//Step 7: Validate the Restrictions Details section displayed with *Required Field* in red color for the following fields:

		  /*- *Effective Date*:
			- *Expiration Date*:
			- *Terminal*:
			- *Zip code*:
			- *Direction*:
			- *Rate Quote Response Message*:*/

		restrictionSetUpPage.verifyRequiredFieldErrMsgIsDisplayed(effDateField);
		restrictionSetUpPage.verifyRequiredFieldErrMsgIsDisplayed(expDateField);
		restrictionSetUpPage.verifyRequiredFieldErrMsgIsDisplayed(terminalField);
		restrictionSetUpPage.verifyRequiredFieldErrMsgIsDisplayed(zipCodeField);
		restrictionSetUpPage.verifyRequiredFieldErrMsgIsDisplayed(directionField);
		restrictionSetUpPage.verifyRequiredFieldErrMsgIsDisplayed(rateQuoteResMsgField);

		//Step 8: From the Restrictions home page, Click *LOGOUT*
		restrictionSetUpPage.logout();


	}

}

