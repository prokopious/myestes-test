package rateQuoteRestrictionTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import rateQuoteRestrictionMaintenancePages.RateQuoteRestrictionMaintenanceLoginPage;
import rateQuoteRestrictionMaintenancePages.RateQuoteRestrictionMaintenancePage;
import rateQuoteRestrictionMaintenancePages.RestrictionSetUpPage;
import rateQuoteTests.RateQuoteSmokeTest;
import testBase.TestBase;

public class RateQuoteRestrictionSmokeTest extends TestBase{
	
	private Logger logger= Logger.getLogger(RateQuoteRestrictionSmokeTest.class);
	
	
	
	RateQuoteRestrictionMaintenanceLoginPage rateQuoteRestrictionMaintenanceLoginPage =new RateQuoteRestrictionMaintenanceLoginPage();

	RateQuoteRestrictionMaintenancePage rateQuoteRestrictionMaintenancePage =new RateQuoteRestrictionMaintenancePage();

	RestrictionSetUpPage restrictionSetUpPage = new RestrictionSetUpPage();

	
	/*********************************************************TEST****************************************************/
	
	
	/**
	 * @Justin- needs to update the test
	 * this test fails because of un expected error <The selected zip codes and direction already has an existing rate quote restriction with dates: 08/03/2022 to 08/03/2022. Do you want to proceed?>
	 * 
	 * @author lemmoju
	 * RQRM - Verify Restriction got updated and displayed in the grid when the user updated existing restriction and clicked Yes on edit warning message
	 * @throws InterruptedException
	 */
	@Test(enabled =false , priority= 1)
	
	public void executeQZ_12253() throws InterruptedException {
		/*Step 1: Open the following test template to capture the URL and Test account details: https://estesexpress.atlassian.net/browse/QZ-11944
		 * URL: https://ratequote-restriction-rstr-qa.apps.ocpnonprod.estesinternal.com/login.html
		 * Test Account: 
		 * User Name: test.admin
		 * Password: tadmin1
		 */
		// Step 2-4 Open the above captured URL in a browser: and sign in. 
		driver.get(url5);
		
		/**
		 * the following lines (4 lines) added to click on <advanced> and then proceed
		 * with unsafe mode before login in the website this is for OCP4 testing
		 */

//		WebElement element = driver.findElement(By.xpath("//*[@id='details-button']"));
//		testUtil.clickElementJavascript(element);
//		testUtil.setHardWait(2000);
//		WebElement newElement = driver.findElement(By.xpath("//*[@id='proceed-link']"));
//		testUtil.clickElementJavascript(newElement);
//		testUtil.setHardWait(1000);
		
		rateQuoteRestrictionMaintenanceLoginPage.validateRestrictionPage();
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(username20);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(password20);
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton(); 
		testUtil.setHardWait(1000);
		/*
		 * Step 5: From the *RESTRICTIONS DETAILS* section, enter the following values:
		 * - *Event Name*: <Smoke test case for edit message>
		 * - *Effective Date*: <Current Date in MM/DD/YYYY >
		 * - *Expiration Date*: <Current Date in MM/DD/YYYY>
		 * - *Terminal*: 202 (Select from drop down or user can type 202 to search on field)
		 * - *Zip code*: From drop down click *X Deselect All* then select the check-boxes *98008*
		 * - *Direction*: Outbound (Radio button)
		 * - *Rate Quote Response Message*: <This restriction created to test edit pop-up window when user selected existing restriction for editing>
		 */
		String smokeTestName = "executeQZ_12253"; 
		String date = testUtil.todaysDate(); 
		String terminal = "202"; 
		String zipCode = "98008"; 
		String rateQuoteMessage = "This restriction created to test edit pop-up window when user selected existing restriction for editing"; 
		
		rateQuoteRestrictionMaintenancePage.enterEventName(smokeTestName);
		rateQuoteRestrictionMaintenancePage.enterEffectiveDate(date);
		rateQuoteRestrictionMaintenancePage.enterExpirationDate(date);
		rateQuoteRestrictionMaintenancePage.enterTerminalName(terminal); 
		testUtil.setHardWait(1000);
		rateQuoteRestrictionMaintenancePage.selectZipCode(zipCode);
		rateQuoteRestrictionMaintenancePage.selectRadioButton("outbound");
		rateQuoteRestrictionMaintenancePage.enterRateQuoteResponseMessage(rateQuoteMessage);
		testUtil.setHardWait(5000);
		/*
		 * Step 6: Click Add
		 * Validate -> The Restrictions page will display the following message: <*Information successfully saved.* >
		 * Note: The message will be displayed above the "
		 * RESTRICTIONS DETAILS" title
		 */
		rateQuoteRestrictionMaintenancePage.clickAddButton();
		testUtil.setHardWait(3000);
		rateQuoteRestrictionMaintenancePage.bypassIfRestrictionMessage(); 
		testUtil.setHardWait(3000);
		//rateQuoteRestrictionMaintenancePage.successMessageIsVisible(); 
		testUtil.setHardWait(2000);
		/*
		 * Step 7: Note: When you have many restrictions in the table, it's difficult to find the above created restriction. 
		 * In this case, continue below steps to find created restriction  otherwise you can skip below search steps and go to Validation step#11
		 * Step 8: From the Restrictions home page, under Restrictions table scroll down to the end. Then click the Row Count drop down and click the following number:
		 */
		rateQuoteRestrictionMaintenancePage.clickOnRowCountDropDown(); 
		testUtil.setHardWait(9000);
		/*
		 * Step 9-11: From the Restrictions home page, Click  the following keys: <Ctrl>+<F>
		 * Note: Keys from your machine keypad or you can click <find> option from the menu of the browser
		 */
		rateQuoteRestrictionMaintenancePage.clickEditButton(smokeTestName);
		testUtil.setHardWait(2000);
		/*
		 * Step 12: The above selected restriction for editing is displayed in the Restrictions Details section
		 * Step 13: From the Restrictions Details section, do the Update/Modify the following fields :- *Zip Code* : Click *Select All* from the drop down- *Direction*: Click *Both* radio button
		 * Step 14: Click Update
		 * Step 15: Verify the Edit Warning pop-up window displayed with following message:
		 * Step 16: From the Edit Warning pop-up window: Click *Yes*
 		 */
		rateQuoteRestrictionMaintenancePage.selectAllZipCodes();
		rateQuoteRestrictionMaintenancePage.selectRadioButton("both");
		rateQuoteRestrictionMaintenancePage.clickUpdateButton(); 
		rateQuoteRestrictionMaintenancePage.validateUpdateMessage(); 
		testUtil.setHardWait(2000);
		//rateQuoteRestrictionMaintenancePage.clickYesButton(); 
	
	
		/*
		 * Step 17: The following steps were added to delete the restriction upon successful execution of above steps: Note: Delete steps should avoid errors in
		 * Rate Quote regression / smoke / Manual test cases.
		 * Step 18: From the Restrictions table, on the right side of page against above created restriction under *Actions* column: Click *Delete*
		 * Step 19: From the *Delete Restriction Warning* pop-up message, check the message displayed with above selected restriction details:
		 * Step 20: Click Yes
		 * Step 21: Click Logout
		 */
		rateQuoteRestrictionMaintenancePage.clickDeleteButton(smokeTestName);
		rateQuoteRestrictionMaintenancePage.validateDeleteMessage(smokeTestName, date, date);
		rateQuoteRestrictionMaintenancePage.clickYesButton();
		rateQuoteRestrictionMaintenancePage.clickLogOut();
		
	}
	
	
	/**
	 * @author lemmoju
	 * RQRM - Verify "Expiration Date" field displayed with error message to correct the date when user entered 
	 * date is before the effective date while creating restriction
	 */


	@Test(enabled = true, priority = 2)
	public void executeQZ_12255() {
		/*Step 1 - Open the following test template to capture the URL and Test account details: https://estesexpress.atlassian.net/browse/QZ-11944
		 * URL: https://ratequote-restriction-rstr-qa.apps.ocpnonprod.estesinternal.com/login.html
		 * User Name: test.admin
		 * Password: tadmin1
		 * Step 2 - Open the above captured URL in a browser: Chrome
		 */
		String userName = "test.admin"; 
		String passWord = "tadmin1"; 
		
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
		
		//Step 3-4  From login page enter the following values: Note: You can use tester credentials or above generic account details and Login
		rateQuoteRestrictionMaintenanceLoginPage.enterUserID(userName);
		rateQuoteRestrictionMaintenanceLoginPage.enterUserPassword(passWord);
		rateQuoteRestrictionMaintenanceLoginPage.clickOnLoginButton(); 
		
		/*Step 5: From the *RESTRICTIONS DETAILS* section, enter the following values:
		 * - *Event Name*: <Regression test case for date validation>
		 * - *Effective Date*: <Current Date + 3 in MM/DD/YYYY >
		 * - *Expiration Date*: <Current Date in MM/DD/YYYY>
		 * Note: Date should be entered in above format, app won't all you to select from previous dates in calendar option.
		 */
		String effDate = testUtil.getBusinessDate("DATE", +3);
		String expDate = testUtil.getTodayDateByFormat("MM/dd/yyyy"); 
		restrictionSetUpPage.enterEventName("executeQZ_12255"); 
		restrictionSetUpPage.enterEffectiveDate(effDate);
		restrictionSetUpPage.enterExpirationDate(expDate);
		restrictionSetUpPage.clickOnAddButton(); 
		testUtil.setHardWait(1500);
		
		/*
		 * Step 6-7: Validate Alert pop-window displayed with below message, as soon as the above Expiration date is entered :
		 * *Alert: Expiration date should be on or after effective date.*
		 * Press 'Ok'
		 */
		restrictionSetUpPage.validateMessage("Expiration date should be on or after effective date."); 
		restrictionSetUpPage.clickOnConfirmationOkBtn();
		
		/*
		 * Step 8: From the Restrictions home page, Click *LOGOUT*
		 */
		restrictionSetUpPage.logout(); 

	}

	
	
	
	

}
