package accountReceivablesTests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import eNetPages.ENetAccountsReceivableFocusAccountsPage;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetCollectionAgencyReportsPage;
import eNetPages.ENetCollectionsPage;
import eNetPages.ENetCollectorActivityPage;
import eNetPages.ENetCurrentARAgingBalancesPage;
import eNetPages.ENetDelayedProsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetMiscellaneousOpenARPage;
import eNetPages.ENetUndiscountedFreightReportPage;
import testBase.TestBase;

public class AccountReceivablesRegressionTest extends TestBase {

	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetCollectionsPage eNetCollectionsPage = new ENetCollectionsPage();
	ENetCurrentARAgingBalancesPage eNetCurrentARAgingBalancesPage = new ENetCurrentARAgingBalancesPage();
	ENetUndiscountedFreightReportPage eNetUndiscountedFreightReportPage = new ENetUndiscountedFreightReportPage();
	ENetAccountsReceivableFocusAccountsPage eNetAccountsReceivableFocusAccountsPage = new ENetAccountsReceivableFocusAccountsPage();
	ENetMiscellaneousOpenARPage eNetMiscellaneousOpenARPage = new ENetMiscellaneousOpenARPage();
	ENetDelayedProsPage eNetDelayedProsPage = new ENetDelayedProsPage();
	ENetCollectorActivityPage eNetCollectorActivityPage =new ENetCollectorActivityPage();
	ENetCollectionAgencyReportsPage eNetCollectionAgencyReportsPage =new ENetCollectionAgencyReportsPage();
	
	/********************************************************************************************************/

	/* 01.01.09.01 - Applications_Accounts Receiveable_Collections */

	@Test(enabled = true, priority = 1)
	public void executeQZ_7194() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From eNet Home page, click on the APPLICATIONS folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");

		// step4:From the Applications page under Accounts Receivable section,
		// click on the following link: COLLECTIONS
		eNetApplicationsPage.clickCollections();
		testUtil.switchToFrame("mainpage");

		eNetCollectionsPage.verifySummarySection();
		eNetCollectionsPage.verifyCollectorsSection();

		// step5: Click View icon next to one of the collector entries.
		eNetCollectionsPage.clickOnViewIon();
		eNetCollectionsPage.verifyNumbercolumn();
		eNetCollectionsPage.VerifyPastDueDollarAmountcolumn();

		// step 6: Logout and close the browser.
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

	}

	/* Reports_Corporate_Miscellaneous Open AR */

	@Test(enabled = true, priority = 2)
	public void executeQZ_7327() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From the eNet Home page, click on the REPORTS tab link / REPORTS
		// folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Reports");

		// step4: From the Reports page, under the Corporate section, click on the
		// following link: Miscellaneous Open A/R
		String maintab = driver.getWindowHandle();
		eNetApplicationsPage.clickMiscellaneousOpenAR();

		// step5: From View page, close the page
		String secTab = null;
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(maintab)) {
				secTab = actual;
			}
		}

		driver.switchTo().window(secTab);
		eNetMiscellaneousOpenARPage.verifyMiscellaneousOpenARPage();
		driver.close();
		driver.switchTo().window(maintab);

		// step 6: Logout and close the browser.
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

		// step 7&8: Thi steps is done in step6

	}

	/* 05.03.17.01 - Reports_Corporate_Accounts Receivable - Focus Accounts */

	@Test(enabled = true, priority = 3)
	public void executeQZ_7325() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From the eNet Home page, click on the REPORTS tab link / REPORTS
		// folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Reports");

		// step4: From the Reports page, under the Corporate section, click on the
		// following link: Accounts Receivable - Focus Accounts
		String maintab = driver.getWindowHandle();
		eNetApplicationsPage.clickAccountReceivableFocusAccounts();

		// step5: From View page, close the page
		String secTab = null;
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(maintab)) {
				secTab = actual;
			}
		}

		driver.switchTo().window(secTab);
		
		//eNetAccountsReceivableFocusAccountsPage.verifyAccountsReceivableFocusAccounts();
		eNetAccountsReceivableFocusAccountsPage.verifyAccountsReceivableFocusAccountPage();
		
		driver.close();
		driver.switchTo().window(maintab);

		// step 6: Logout and close the browser.
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

		// step 7&8: Thi steps is done in step6

	}

	/*
	 * 05.03.02.01 - Reports_Corporate_Current AR Aging Balances (omit G, I, N, P
	 * classes)
	 */

	@Test(enabled = true, priority = 4)
	public void executeQZ_7311() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From the eNet Home page, click on the REPORTS tab link / REPORTS
		// folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Reports");

		// step4: From the Reports page, under the Corporate section, click on the
		// following link: Current A/R Aging Balances (Omit G,I,N,P Classes)
		String maintab = driver.getWindowHandle();
		eNetApplicationsPage.clickCurrentARAgingBalances();

		// step5: From View page, close the page
		String secTab = null;
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(maintab)) {
				secTab = actual;
			}
		}

		driver.switchTo().window(secTab);
		eNetCurrentARAgingBalancesPage.verifyCurrentARAgingBalancesreport();
		driver.close();
		driver.switchTo().window(maintab);

		// step 6: Logout and close the browser.
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

		// step 7&8: Thi steps is done in step6

	}

	/* 01.01.15.01 - Applications_Accounts Receivable_Undiscounted Freight Report */

	@Test(enabled = true, priority = 5)
	public void executeQZ_7205() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From eNet Home page, click on the APPLICATIONS folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");

		// step4:From the Applications page, Under the ACCOUNTS RECEIVABLE section click
		// UNDISCOUNTED FREIGHT REPORT

		eNetApplicationsPage.clickUndiscountedFreightReport();
		testUtil.switchToFrame("mainpage");

		// step5: Enter ACCOUNT NUMBER (try 5018240) and EMAIL ADDRESS & Click SUBMIT
		// NOTE: mail and FAX will be turned off during this testing.
		String accountNumber = "5018240";
		String emailAddress = "Mosammat.taslima@estes-express.com";

		eNetUndiscountedFreightReportPage.enterAccountNumber(accountNumber);
		eNetUndiscountedFreightReportPage.enterEmailAddress(emailAddress);
		eNetUndiscountedFreightReportPage.clickonSubmitButton();

		// Note: Mail verification is done manually as per UFT script

		// step6: Logout and close browser.
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();
	}

	/* 01.01.00.18 - Delayed Pros */

	@Test(enabled = true, priority = 6)
	public void executeQZ_5301() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From eNet Home page, click on the APPLICATIONS folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");

		// step4:From the Applications page, Under the ACCOUNTS RECEIVABLE section click
		// Delayed Pros
		String maintab = driver.getWindowHandle();
		eNetApplicationsPage.clickDelayedPros();

		// step5: From Count delayed by reason code page, click on the first Reason Code
		// Report displays detail for selected Reason Code

		String secTab = null;
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(maintab)) {
				secTab = actual;
			}
		}

		driver.switchTo().window(secTab);
		eNetDelayedProsPage.verifyDelayedProsPage();
		eNetDelayedProsPage.clickOnFirstReasonCode();
		eNetDelayedProsPage.verifyReortDetails();

		driver.close();
		driver.switchTo().window(maintab);

		// step6: Log out and close browser
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();
	}
	
	
	
	
	
	/*
	 * 01.01.00.14-Focus Account Maintenance
	 */
	@Test(enabled = true, priority = 7)
	public void executeQZ_7181()  {
		//Step#1 : Open within the Internet Explorer Browser, the following link:
		//https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);
		
		//Step#2 : Login with the following credentials:
		//User ID: qaenet02, Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		
		//Step#3 : From the eNet home page, Click on the Applications folder VIEW ALL icon (Applications Tab Link)
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");
		
		//Step#4 : From the Applications page under the Accounts Receivable section, Click on the following link: 
		//Focus Account Maintenance
		eNetApplicationsPage.clickFocusAccountMaintenance();
		
		//Step#5 : Select "Select an Option" from the dropdown.
		eNetAccountsReceivableFocusAccountsPage.verifyAccountsReceivableFocusAccounts();
		//Note: Selecting Add an Account option, so that the search box with the Binocular symbol will appear.
		eNetAccountsReceivableFocusAccountsPage.selectOptionAddanAccount();
		
		//Step#6 : Enter an Account or search for the Account (use the binoculars)
		eNetAccountsReceivableFocusAccountsPage.clickOnBinocularSymbol();
		//Note : Here 12 is used as search keyword/ number to search for the account numbers which will be having these 2 numbers.
		eNetAccountsReceivableFocusAccountsPage.enterAccountNumberKeyWord("12");
		//Click the SUBMIT button
		//Step#7 : Click the SUBMIT button
		eNetAccountsReceivableFocusAccountsPage.clickOnSubmitButton();
		//The account appears in the listing.
		eNetAccountsReceivableFocusAccountsPage.verifySearchResultDisplays();
		
		//Note : As stated by Kathy:
		//QZ-7181-Automate like what is in UFT until step 7 because clicking the print icon would cause issues for Selenium
		
	}
	
	/*
	 * 01.01.11.01 - Applications_Accounts Receivable_Collections Reports
	 */
	
	@Test(enabled = true, priority = 8)
	public void executeQZ_7196() {
		//Step#1 : From the Internet Explorer Browser, open the following link:
		//https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);
		
		//Step#2 : Login with the following credentials
		//User ID: qaenet02, Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		
		//Step#3 : From the eNet home page, click on the Applications folder VIEW ALL icon (Applications Tab Link)
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");
		
		//Step#4 : From the Applications page, click ACCOUNTS RECEIVABLE and then click COLLECTIONS REPORT
		eNetApplicationsPage.clickOnCollectionsReports();
		//Collections Reports displays. Verify there is at least one row of data and at least some of the columns show values other than zero.
		eNetCollectorActivityPage.verifyPage();
		eNetCollectorActivityPage.goInsideFrame(0);
		eNetCollectorActivityPage.checkValuesGreaterThanZeroInRecord();
		
		//Step#5 : From the Collections Reports page under Open AR Report, enter an account number
		//Account Number:5018240, E-mail Address: april.golden@estes-express.com
		eNetCollectorActivityPage.goInsideFrame(1);
		eNetCollectorActivityPage.enterOpenARReportAccountNumber("5018240");
		eNetCollectorActivityPage.enterOpenARReportemail("april.golden@estes-express.com");
		
		//Step#6 : Click Submit Button
		eNetCollectorActivityPage.clickOnOpenARReportSubmit();
		//Email is received with attached Open AR Report.
		
		//Step#7 : From the Collections Reports page under Undiscounted Freight Report, enter an account number in the 
		//Account Number:5018240, E-mail Address: april.golden@estes-express.com
		eNetCollectorActivityPage.goInsideFrame(2);
		eNetCollectorActivityPage.enterUndiscountedFrieghtReportAccountNumber("5018240");
		eNetCollectorActivityPage.enterUndiscountedFrieghtReportemail("april.golden@estes-express.com");
		
		//Step#8 : Click Submit Button
		eNetCollectorActivityPage.clickUndiscountedFrieghtReportSubmit();
		//Email is received with Undiscounted Freight Report attached.
		
		//Step#9 : Logout and close browser.
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogOutButton();
	}
	
	/**
	 * 
	 * This test passed on 7/5/22
	 * _applications_Accounts_Receiveable_Collection_Agency_Reports
	 * @throws InterruptedException
	 * 
	 */
	
	@Test(enabled = true, priority = 9)

	public void executeQZ_7193() throws InterruptedException {
		//Step#1 : From the Internet Explorer Browser, access the following link:
		//https://estesexpress.atlassian.net/browse/QZ-6808
				driver.get(url2);
		
		//Step#2 : Login with the following credentials
		//User ID: qaenet02, Password: qaenet02
				eNetLoginPage.enterUserID(username12);
				eNetLoginPage.enterUserPassword(password12);
				eNetLoginPage.clickOnLoginButton();
		//The ENet home page is displayed
				eNetHomePage.verifyPageTitleQaenet02();
				
		//Step#3 : From the eNet home page, Click on the Applications folder VIEW ALL icon (Applicationss Tab Link)
				eNetHomePage.clickOnApplicationsTab();
				eNetApplicationsPage.verifyPageTtl();
				
		//Step#4 : From the Applications page under the ACCOUNTS RECEIVABLE section, click on the following link: 
		//Collection Agency Reports
				eNetApplicationsPage.clickOnCollectionAgencyReports();
				eNetCollectionAgencyReportsPage.verifyPageTitle();

		//Collection agency selection fields are displayed.
				List<String> fieldsList = new ArrayList<String>();
				fieldsList.add("Report:");
				fieldsList.add("Collector Number:");
				fieldsList.add("Start Date:");
				fieldsList.add("End Date:");
				eNetCollectionAgencyReportsPage.verifyFieldsPresentorNot(fieldsList);
				
		//Step#5 : From the Collection Agency Reports, choose UNDER PAYMENTS from the Report dropdown.
				/*Enter Collector Number.  Enter START and 
				END DATE (use large enough range to reach 
				back prior to last production refresh.
				Click SEARCH
				NOTE:
				Use Collector #4070*/
				eNetCollectionAgencyReportsPage.selectReport("Under Payments");
				eNetCollectionAgencyReportsPage.enterCollectorNumber("4070");
				eNetCollectionAgencyReportsPage.selectPreviousStartYear();
				eNetCollectionAgencyReportsPage.selectPreviousStartMonth();
				eNetCollectionAgencyReportsPage.selectPreviousEndMonth();
				eNetCollectionAgencyReportsPage.clickOnSearchBtn();
				
		//Step#6 : From the Collection Agency Reports, click on Excel icon
				eNetCollectionAgencyReportsPage.clickOnExcelIcon();
		//Pop up to view / save and email. displays
				eNetCollectionAgencyReportsPage.verifyExcelPopupview();
				
				
		//Step#7 : From the Collection Agency Reports, click on view excel document
				eNetCollectionAgencyReportsPage.clickOnViewExcel();
		//Report displays in excel format
				eNetCollectionAgencyReportsPage.verifyReportsExcelServletFiledownloaded();
				
		//Step#8 : From the Collection Agency Reports, click on save document
				eNetCollectionAgencyReportsPage.clickOnSaveExcel();
				testUtil.setHardWait(2000);
		//the Document save in excel
				eNetCollectionAgencyReportsPage.verifyCollectionAgencyTurnoverFilesaved();
				
		//Step#9 : From the Excel icon pop-up window, click on the following link:
		//E-mail Excel Document
				eNetCollectionAgencyReportsPage.clickOnEmailExcel();
		//Confirmation message displays "Email has been sent".  Email is received to selected email address.
		//Step#10 : Enter in tester's email address
		//Step#11 : Click Send
		//Confirmation message displays "Email has been sent".  Email is received to selected email address.
		//Note : Step 10, 11 and 12 are included in one step.
				eNetCollectionAgencyReportsPage.enterEmailAddress();
				eNetCollectionAgencyReportsPage.verifyEmailSentConfirmation();
				
	}
	
	
	
	

}

