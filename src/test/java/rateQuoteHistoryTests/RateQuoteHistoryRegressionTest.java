package rateQuoteHistoryTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQouteQuoteHistoryPage;
import rateQuotePages.RateQouteRateEstimatePage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;
import util.SQLDataList;

public class RateQuoteHistoryRegressionTest extends TestBase{

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	RateQouteRateEstimatePage rateEstimatePage = new RateQouteRateEstimatePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	RateQouteQuoteHistoryPage QuoteHistoryPage = new RateQouteQuoteHistoryPage();
	SQLDataList sqlDataList = new SQLDataList();
	/******************************* TESTS *******************************/
	
    /*
    * Web - Quote History - Search by Date Range
    */
    @Test(enabled = true, priority = 1)

    public void executeQZ_8569() throws Exception {

           ArrayList<String> quoteNumbers = null;
           int searchString = 0;
           String qDte = null;
           String query = null;

           // Step 1: Open My estes app
           myEstesHomePage.clickOnMyEstes();
           myEstesHomePage.clickOnLogin();

           // Step 2: Login as local user
           myEstesLoginPage.enterUserName(username2);
           myEstesLoginPage.enterPassword(password2);
           testUtil.setHardWait(1000);
           myEstesLoginPage.clickOnLoginButton();
           testUtil.setHardWait(5000);

           // Step 3: Navigate to Ship
           myEstesHomePage.clickOnShipTab();

           // Step 4: Click on Rate Quote un Ship
           myEstesHomePage.clickOnRateQoute();

           // Step 5: Click on Rate Quote History tab
           rateQuotePage.clickOnQouteHistoryTab();
           testUtil.setHardWait(3000);
           QuoteHistoryPage.verifyPageTitle();

           // Step 6: Click on Advanced search button
           QuoteHistoryPage.clickOnAdvancedSearch();
           String quoteDate = testUtil.addPastWeekDay();

           String[] qDate = quoteDate.split("/", 3);

           String qmth = qDate[0];
           qDte = qDate[1];

           qDte = qDte.trim();

           while (qDte.indexOf("0") == 0) {
                  qDte = qDte.substring(1);
           }

           while (qmth.indexOf("0") == 0) {
                  qmth = qmth.substring(1);
           }

           String newDate = qDate[0] + "/" + qDte + "/" + qDate[2];
           System.out.println("Date " + newDate);

           // Step 7: select from date calendar widget icon
           // Step 8: select from date via calendar widget
         //  QuoteHistoryPage.selectFromDateFromWidget(qDte);
           
           QuoteHistoryPage.enterFromDate(newDate);

           // Step 9: Select To date calendar widget icon
           // Step 10: Select to date via calendar widget
           //QuoteHistoryPage.selectToDateFromWidget(qDte);
           
           QuoteHistoryPage.enterToDate(newDate);

           // Verify selected date is populated in from date in MM/DD/YYY format
           // QuoteHistoryPage.verifySelectedDateISPopulate("fromDate", quoteDate);

           QuoteHistoryPage.verifySelectedDateISPopulate("fromDate", newDate);

           // Verify selected date is populated in to date in MM/DD/YYY format
           // QuoteHistoryPage.verifySelectedDateISPopulate("toDate", quoteDate);
           QuoteHistoryPage.verifySelectedDateISPopulate("toDate", newDate);

           QuoteHistoryPage.clickOnSearchButton();

           // Step 11: Enter acct number for national user
           quoteNumbers = QuoteHistoryPage.captureResultTable();
           searchString = quoteNumbers.size();

           // Step 12: Create search string based on search criteria
           query = "select GSRID, GSRTMST, GSRAPP, GSROZIP, GSROST, GSRDZIP, GSRDST,  GSQPRIC from FBFILES.GSC00P100 a left join FBFILES.GSC00P110 b on"
                        + " a.GSRID = b.GSQID where GSRQID =0 and GSQPRIC > 0.00 and GSRRNAM =  'SMOKELOCAL' and GSRACT = '7178618' and GSRTMST >= "
                        + "'" + qDate[2] + "-" + qmth + "-" + qDte + " 00:00:00.000000' and GSRTMST <= '" + qDate[2] + "-"
                        + qmth + "-" + qDte
                        + " 23:59:59.000000' and GSQSEL ='Y' and GSQDSP='S' Order by GSRTMST Desc FETCH FIRST " + searchString
                        + " ROWS ONLY";

           List<String> quoteNumbersFromDB = sqlDataList.getQuoteFromQuery(query);
           testUtil.setHardWait(2000);
           // Verify results with returned search results
           Assert.assertTrue((quoteNumbers.equals(quoteNumbersFromDB)));
    }

//	Verifies the search by quote and search by Date range returns proper values.

	/*
	 * Web - Quote History - Verify Quote History Search functionality
	 */

	@Test(enabled = true, priority = 2)
	public void executeQZ_3109() throws Exception {

		//Step 1: Record Total Count # - covered in Step 15

		ArrayList<String> quoteNumbers = null;
		int searchString = 0;
		String qDte = null;
		String query = null;

		ArrayList<String> tableHeaders = new ArrayList<String>(Arrays.asList("Quote Number","Quote Date","Service Level","Origin Zip","Origin State","Destination Zip","Destination State","Est. Charges",""));

		
		// Step 2: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 3: Login as SmokeLocal
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		//Step 4: click the Request a Quote "Start Request" button
		myEstesHomePage.clickOnRequestAQuote();

		//Step 5: Verify title on page "Rate Quote"
		rateQuotePage.verifyRateQuotePageTitle();

		//Step 6: Verify tabs for Create RateQuote, Rate Quote History appears on page.
		rateQuotePage.verifyCreateRateQuoteTabDisplayed();
		rateQuotePage.verifyRateQuoteHistoryTabDisplayed();

		//Step 7: Click  Rate Quote History tab
		rateQuotePage.clickOnQouteHistoryTab();

		//Step 8: Verify title "HISTORY" is displayed 
		rateQuotePage.verifyHistoryTitleDisplayed();

		//Step 9: verify the  column headings:"Quote Number","Quote Date","Service Level","Origin Zip","Origin State","Destination Zip","Destination State","Est. Charges"
		rateQuotePage.verifyQuoteHistoryTableHeader(tableHeaders);

		//Step 10: click on the Advanced Search Filter and verify sub title *Search Options* is displayed
		rateQuotePage.clikOnAdvanceSearchButton();
		rateQuotePage.verifySearchOptionDisplayed();

		//Step 11: Verify calendar icon to the right of the box for "From Date" and "To Date'
		rateQuotePage.verifyFromDateCalenderDisplayed();
		rateQuotePage.verifyToDateCalenderDisplayed();

		//Step 12: Verify the check-box and text appears "Show All Filters"
		rateQuotePage.verifyShowAllFilterCheckBoxDisplayed();
		rateQuotePage.verifyShowAllFilterTextDisplayed();

		//Step 13: Verify Buttons, "SEARCH" and "Clear" are on page.
		rateQuotePage.verifySearchButtonDisplayed();
		rateQuotePage.verifyClearButtonDisplayed();

		//Step 14: Based on the information from step 1 enter a *From* date and *To* date and click *SEARCH*	
		String quoteDate = testUtil.addPastWeekDay();
		String[] qDate = quoteDate.split("/", 3);
		String qmth = qDate[0];
		qDte = qDate[1];
		qDte = qDte.trim();
		while (qDte.indexOf("0") == 0) {
			qDte = qDte.substring(1);
		}
		while (qmth.indexOf("0") == 0) {
			qmth = qmth.substring(1);
		}
		String newDate = qDate[0] + "/" + qDte + "/" + qDate[2];
		System.out.println("Date " + newDate);
		testUtil.setHardWait(1000);
		QuoteHistoryPage.selectFromDateFromWidget(qDte);
		QuoteHistoryPage.selectToDateFromWidget(qDte);
		rateQuotePage.clikOnSearchButton();
		testUtil.setHardWait(2000);
		//Step 15: Verify the *Quote Count* displayed as 1-xxx of xxx agree with the SQL results from step 1.
		quoteNumbers = QuoteHistoryPage.captureResultTable();
		searchString = quoteNumbers.size();
		query = "select GSRID, GSRTMST, GSRAPP, GSROZIP, GSROST, GSRDZIP, GSRDST,  GSQPRIC from FBFILES.GSC00P100 a left join FBFILES.GSC00P110 b on"
				+ " a.GSRID = b.GSQID where GSRQID =0 and GSQPRIC > 0.00 and GSRRNAM =  'SMOKELOCAL' and GSRACT = '7178618' and GSRTMST >= "
				+ "'" + qDate[2] + "-" + qmth + "-" + qDte + " 00:00:00.000000' and GSRTMST <= '" + qDate[2] + "-"
				+ qmth + "-" + qDte
				+ " 23:59:59.000000' and GSQSEL ='Y' and GSQDSP='S' Order by GSRTMST Desc FETCH FIRST " + searchString
				+ " ROWS ONLY";

		List<String> quoteNumbersFromDB = sqlDataList.getQuoteFromQuery(query);
		testUtil.setHardWait(2000);
		Assert.assertTrue((quoteNumbers.equals(quoteNumbersFromDB)));

		//Step 16: Click on the *Clear* button.
		rateQuotePage.clikOnAdvanceSearchButton();
		rateQuotePage.clikOnClearButton();

		//Step 17: Run query for quote number
		String query1 = "Select GSRID, GSRTMST, GSROCITY, GSROZIP, GSROST, GSRDZIP, GSRDST, GSQPRIC, GSRRNAM, GSRACT, GSQSEL, GSQDSP from FBFILES.GSC00P100 a left join FBFILES.GSC00P110 b on a.GSRID =b.GSQID where GSQPRIC > 0.00 and GSRRNAM = 'SMOKELOCAL' and GSRACT ='7178618' and GSRTMST >= '2020-8-25 00:00:00.000000' and GSQSEL = 'Y' AND GSQDSP = 'S' Order by GSRTMST desc";
		if (sqlDataList.validateValueFromDataBase(query1) == true) {
			List<String> fbDetails2 = sqlDataList.getFirstRowDetailsFromEXLAQA(query1);
			String quoteNum = fbDetails2.get(0);
			System.out.println(quoteNum);
			testUtil.setHardWait(1000);
			//Step 18: Using the data obtained in step 17, enter a Quote # and click *SEARCH*
			rateQuotePage.enterQuoteNumber(quoteNum);
			rateQuotePage.clikOnSearchButton();

			//Step 19: Verify the data in the table.
			rateQuotePage.verifyTableResult();

			//Step 20: Click on the *RESET* button.
			rateQuotePage.clikOnAdvanceSearchButton();
			rateQuotePage.clikOnClearButton();

			//Step 21: Click Logoff
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogout();

			//Step 22: close the page
			//Covered in after test

			//Step 23: close the browser
			//Covered in after test

		}
	}
	
	
	/*
	 * Web - Quote History - Lookups are by user signon
	 */

	@Test(enabled = true, priority = 3)

	public void executeQZ_3104() throws InterruptedException {
		// Step 1: Launch application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as testgroup1 user
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: From Ship, Select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(3000);

		// Step 4: Confirm that only LTL (Incl. Guaranteed) is selected under Rate Quote
		// Type.
		// By default only LTL will be selected. So no action performed here.

		// Enter the required fields: --> Requester Information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("90007");

		// Step 5: Under 'Commodities', populate the following fields:
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterDesc("Verify lookups are by signon");// ?

		// Step 6: Click 'Submit Request' button.
		rateQuotePage.clickOnSubmitButton();

		// Step 7: Select a service level and record the quote number.
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		String quote = rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Step 8: Click *Rate Quote History*
		rateQuotePage.clickOnQouteHistoryTab();
		testUtil.setHardWait(3000);

		// Validate the *Quote Number* captured in the previous step is displayed in
		// history
		//QuoteHistoryPage.verifyQuoteExistence(quote, true);

		QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable(quote, true, 5000, 0);
		//QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable1(quote, true, 5000);
		// Step 9: Click 'Logout'.
		myEstesHomePage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		myEstesHomePage.clickOnConfirmButton();

		// Step 10: Login to 'MyEstes' application using provided credentials:
		// testgroup2,testgroup2

        testUtil.setHardWait(1000); 
        driver.get(url);
        testUtil.setHardWait(1000);
        myEstesHomePage.clickOnMyEstes();
        myEstesHomePage.clickOnLogin();   


		myEstesLoginPage.enterUserName(username9);
		myEstesLoginPage.enterPassword(password9);
		testUtil.setHardWait(4000);
		myEstesLoginPage.clickOnLoginButton(); 

		// Step 11: From Ship, Select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);

		// Step 12: Confirm that only LTL (incl. Guaranteed) is selected under Rate
		// Quote Type. Enter info in all required fields.
		// By default only LTL will be selected. So no action performed here.

		// Requester Information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("23060");
		rateQuotePage.enterDesZip("77071");

		// Step 13: Under Commodities, populate the following fields:
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("900");
		rateQuotePage.enterDesc("Verify lookups are by signon");

		// Step 14: Click Submit Request button.
		rateQuotePage.clickOnSubmitButton();

		// Step 15: Click on any service level quote and record quote#
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		String quote1 = rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Step 16: Click on 'Quote History'.
		rateQuotePage.clickOnQouteHistoryTab();
		testUtil.setHardWait(2000);

		// Validate the *Quote Number* captured in the previous step is displayed in
		// history
		//QuoteHistoryPage.verifyQuoteExistence(quote1, true);
		QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable(quote1, true, 5000, 0);
		// Step 17: Validate quote created by testgroup1 user is *NOT* displayed in
		// history
		//QuoteHistoryPage.verifyQuoteExistence(quote, false);
		QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable(quote, false, 5000, 0);

	}
	
}
