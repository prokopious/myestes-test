package quoteHistoryLookupTests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetQuoteHistoryLookupPage;
import eNetPages.ENetQuoteSummaryDetailsPage;
import rateQuotePages.RateQouteQuoteHistoryPage;
import testBase.TestBase;
import util.SQLDataList;

public class QuoteHistoryLookupRegressionTest extends TestBase {

	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetQuoteHistoryLookupPage eNetQuoteHistoryLookupPage = new ENetQuoteHistoryLookupPage();
	ENetQuoteSummaryDetailsPage eNetQuoteSummaryDetailsPage = new ENetQuoteSummaryDetailsPage();
	ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
	ENetLTLRateRequestPage eNetLTLRateRequestPage = new ENetLTLRateRequestPage();
	RateQouteQuoteHistoryPage QuoteHistoryPage = new RateQouteQuoteHistoryPage();
	SQLDataList sqlDataList = new SQLDataList();

	/**********************************************/

	// This test has "DO-NOT_AUTOMATE" label in JIRA
	/* Quote Email - Design Standards */

	@Test(enabled = true, priority = 1)

	public void executeQZ_7113() throws Exception {

		String emailAddress = "test@estes-express.com";
		// Step 1: Open the following link to eNet application
		// URL: http://enetqa85.estesinternal.com/login.jsp
		driver.get(url2);

		// Step 2: Login with the following credentials,
		// User name: Peachdo
		// Password: aug08y14
		eNetLoginPage.enterUserID(username16);
		eNetLoginPage.enterUserPassword(password16);

		// Step 3: Click Login
		eNetLoginPage.clickOnLoginButton();

		// Step 4: From The ENet home page, Under Applications, click on Customer
		// Service --> Quote History Lookup.
		// Verify Quote History page is displayed.
		eNetHomePage.clickOnCustomerServiceLink();

		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
	//	testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();

		// Step 5: Click on first Quote number displayed on search result table
		// Verify Quote Summary page is displayed.
		eNetQuoteHistoryLookupPage.clickQuoteLink();
		eNetQuoteSummaryDetailsPage.verifyQuoteSummaryPage();

		// Step 6: On Email Quote text box enter Email address
		// Click on Email Quote button
		// Verify Confirmation message has been displayed and verify the email has been
		// received to the receive
		eNetQuoteSummaryDetailsPage.enterEmailAddress(emailAddress);
		eNetQuoteSummaryDetailsPage.clickOnEmailAddressButton();
		eNetQuoteSummaryDetailsPage.verifyEmailAddressSuccessMessage();

		// Step 7: Logout and close eNet application
		eNetHomePage.clickOnLogout();

		// Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();
	}

	/* eNet - Quote History - Verify Additional Filters section functionality */

	@Test(enabled = true, priority = 2)

	public void executeQZ_7099() throws Exception {

		String acctNum = "9451455";
		// Step 1: Open within the Internet Explorer Browser, the following link:
		driver.get(url2);

		// Step 2: Login with the following credentials:
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: From eNet page top navigation Click *Applications*
		// Step 4: From the Applications page, find the *Customer Service* applications
		// list click *Quote History Lookup*

		eNetHomePage.clickOnCustomerServiceLink();
		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
	//	testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();

		// Step 5: Under the *Search Criteria* section, select *Show All
		// Filters*checkbox

		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAdditinalFiltersExpanded();

		// Step 6: Enter the following search criteria:
		// Note: if the first account # does not return results try the second account

		eNetQuoteHistoryLookupPage.enterAccountNumber(acctNum);

		// Step 7: Under the *Search Criteria* section,uncheck *Show All Filters*
		// checkbox
		// Note: validate that collapsing the section should not clear the filters.
		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAdditinalFiltersisCollapsed();

		// Step 8: Click *Search*
		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
		eNetQuoteHistoryLookupPage.verifyAccountNumberExistence(acctNum);

		// Step 9: Under the *Search Criteria* section, select *Show All Filters*
		// checkbox
		// Verify the *Account* field is populated with the account number entered in
		// step 6
		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAccountNumber(acctNum);

		// STep 10: Click *Log Out* from top navigation
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

	}

	/* eNet - Quote History - Verify Results table columns */

	@Test(enabled = true, priority = 3)

	public void executeQZ_7097() throws Exception {

		String acctNum = "5068692";
		// Step 1: Open within the Internet Explorer Browser, the following link:
		driver.get(url2);

		// Step 2: Login with the following credentials:
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: From eNet page top navigation Click *Applications*
		// Step 4: From the Applications page, find the *Customer Service* applications
		// list .click *Quote History Lookup*

		eNetHomePage.clickOnCustomerServiceLink();
		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
	//	testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();

		// Step 5:From the *Quote History/Results* table, validate the following columns
		// are displayed:

		eNetQuoteHistoryLookupPage.verifyColumnsDisplayedInQuoteHistoryResultsTable();

		// Step 6: Under the *Search Criteria* section, select *Show All Filters*
		// checkbox

		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAdditinalFiltersExpanded();

		// Step 7: Enter the following search criteria:

		eNetQuoteHistoryLookupPage.enterAccountNumber(acctNum);

		// Step 8: Click *Search*

		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
		eNetQuoteHistoryLookupPage.verifyAccountNumberExistence(acctNum);

		// Step 9: Click *Log Out* from top navigation
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

	}

	/*
	 * Quote History - Verify selecting an LTL Quote # from Rate Quote History
	 * displays the LTL Quote History Detail
	 */

	@Test(enabled = true, priority = 4)

	public void executeQZ_7094()
			throws Exception {

		// Step 1: Open within the Internet Explorer Browser, the following link:
		driver.get(url2);

		// Step 2: Login with the following credentials:
	
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: From eNet page top navigation Click *Applications*
		// Step 4: From the Applications page, find the *Customer Service* applications
		// list click *Quote History Lookup*
	
		eNetHomePage.clickOnCustomerServiceLink();
		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
	//	testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();

		// Step 5: Under the *Search Criteria* section, select *Show All Filters* checkbox

		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAdditinalFiltersExpanded();

		// Step 6: From the *Additional Filters*
		eNetQuoteHistoryLookupPage.selectServiceLevel("LTL Standard Transit");

		// Step 7: Click *Search*
	
		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
		eNetQuoteHistoryLookupPage.verifyQuotePrefixIsL("L");

		// Step 8: Select any *Quote #* link
		
		eNetQuoteHistoryLookupPage.clickQuoteLink();

		// Step 9: Click *Log Out* from top navigation
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();
	}
	
	 /* eNet - Quote History - Filter by Quote Type dropdown */
	
	 @Test(enabled = true, priority = 5)

   public void executeQZ_7090() throws Exception {
   	
   	//Step 1: Open within the Internet Explorer Browser, the following link:
   	driver.get(url2);
   	
	   	//Step 2: Login with the following credentials:
	   	//User ID: qaenet01
	   	//Password: qaenet01
	   	eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		
		//Step 3: From eNet page top navigation Click *Applications*
		//Step 4: From the Applications page, find the *Customer Service* applications list
		//click *Quote History Lookup*
		//The *Quote History* page displays
		eNetHomePage.clickOnCustomerServiceLink();
		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
		//	testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();
		
		//Step 5: Under the *Search Criteria* section, select *Show All Filters* checkbox
		//The *Additional Filters* section expanded and more filters are displayed
		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAdditinalFiltersExpanded();
		
		//Step 6: From the *Additional Filters* section, 
		//locate the *Selected Service* field
		eNetQuoteHistoryLookupPage.verifySelectServiceLevelFeild();
		
		//Step 7: From the *Selected Service* filed,
		//validate the following drop down options are available:
		//LTL Standard Transit
		//Guaranteed V/TL Standard
		//Guaranteed V/TL Economy
		//Volume and Truckload Basic
		//Guaranteed LTL Standard 10AM
		//Guaranteed LTL Standard 12M
		//Guaranteed Exclusive Use

	    List<String> flagList = new ArrayList<String>();
	    flagList.add("LTL Standard Transit");
	    flagList.add("Guaranteed V/TL Standard");
	    flagList.add("Guaranteed V/TL Economy");
	    flagList.add("Volume and Truckload Basic");
	    flagList.add("Guaranteed LTL Standard 10AM");
	    flagList.add("Guaranteed LTL Standard 12PM");
	    flagList.add("Guaranteed Exclusive Use");
		
		eNetQuoteHistoryLookupPage.validateDropDownOptionsInSeletedServiceLevel(flagList);
		
		//Step 8: From the *Selected Service* filed,
		//select *Volume and Truckload Basic*
		eNetQuoteHistoryLookupPage.selectServiceLevel("Volume and Truckload Basic");
		
		//Step 9: Click *Search* button
		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
						
		//Step 10: From the *Quote HistoryResults section,
		//validate all *Quote # starts with V*
		//Only *Volume and Truckload Basic* quotes are displayed 
		eNetQuoteHistoryLookupPage.verifyQuotePrefixValue("V");
		
		//Step 11: From the *Selected Service* filed,
		//select *LTL Standard Transit*
		eNetQuoteHistoryLookupPage.selectServiceLevel("LTL Standard Transit");
		
		//Step 12: Click *Search* button
		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
		
		//Step 13: From the *Quote HistoryResults section,
		//validate all *Quote # starts with L*
		//Only *LTL Standard Transit* quotes are displayed 
		eNetQuoteHistoryLookupPage.verifyQuotePrefixValue("L");
		
		//Step 14: From the *Selected Service* filed,
		//select *Guaranteed LTL Standard 10PM*
		eNetQuoteHistoryLookupPage.selectServiceLevel("Guaranteed LTL Standard 10AM");
		
		//Step 15: Click *Search* button
		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
		
		//Step 16: From the *Quote HistoryResults section,
		//validate all *Quote # starts with 3*
		//Only *Guaranteed LTL Standard 10PM* quotes are displayed 
		eNetQuoteHistoryLookupPage.verifyQuotePrefixValue("3");
		
		//Step 17: From the *Selected Service* filed,
		//select *Guaranteed Exclusive Use*
		eNetQuoteHistoryLookupPage.selectServiceLevel("Guaranteed Exclusive Use");
		
		//Step 18: Click *Search* button
		eNetQuoteHistoryLookupPage.clickOnSearchBtn();
		
		//step 19: From the *Quote HistoryResults section,
		//validate all *Quote # starts with X*
		//Only *Guaranteed Exclusive Use* quotes are displayed
		eNetQuoteHistoryLookupPage.verifyQuotePrefixValue("X");
		
		//Click *Log Out* from top navigation
		eNetHomePage.clickOnLogout();

		//Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();

	 }

	 /* ENet Quote History Lookup - Filter by User ID  */
		
		@Test(enabled = true, priority = 6)
		
		public void executeQZ_7111() throws Exception {
			 
			ArrayList<String> quoteNumbers = null;
			int searchString = 0;
			String query = null;
		    	
			//Step 1: Open within the Internet Explorer Browser, the following link:
			//step 2: Login with the following credentials:
			//User ID: qaenet02
			//Password: qaenet02
			driver.get(url2);
					    	
			eNetLoginPage.enterUserID(username12);
			eNetLoginPage.enterUserPassword(password12);
			eNetLoginPage.clickOnLoginButton();
			
			// The ENet home page is displayed
			eNetHomePage.validatePage();

			//Step 3: From eNet Home page, Select 'Customer Service' link in the 'Application' section
			eNetHomePage.clickOnCustomerServiceLink();
			
			//Step 4: From Customer Service page, Select 'Quote History Lookup' link
			eNetApplicationsPage.clickOnquoteHistoryLookupLink();
			eNetQuoteHistoryLookupPage.verifyPage();
			
			//Step 5: Check the box for Show All Filters
			eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
			
			//Step 6: From the Quote History page with Show All Filters enabled, Verify that the User ID field is displayed under additional filter section.
			//The User ID field is displayed
			eNetQuoteHistoryLookupPage.verifyUserFeild();
			
			//Step 7: Enter the known User ID (ex: TESTADMIN) and click Submit.
			eNetQuoteHistoryLookupPage.enteruserfeild("TESTADMIN");
			eNetQuoteHistoryLookupPage.clickOnSearchButton();
			
			//Results are filtered and only those under the TESTADMIN User ID are displayedResults are filtered and only those under the TESTADMIN User ID are displayed
			eNetQuoteHistoryLookupPage.verifyUsrIDExistence("TESTADMIN");
			
			//Step 8: Enter a different User ID (try SMOKELOCAL) and click Submit.
			eNetQuoteHistoryLookupPage.enteruserfeild("SMOKELOCAL");
			eNetQuoteHistoryLookupPage.clickOnSearchButton();
			
			//Results are filtered and only those under the User ID are displayed
			eNetQuoteHistoryLookupPage.verifyUsrIDExistence("SMOKELOCAL");
			
			//Step 9: Click on one of the filtered quotes
			String quoteNumber = eNetQuoteHistoryLookupPage.recordQuoteNumber();
			eNetQuoteHistoryLookupPage.clickQuoteLink();
			
			//Quote displays and quote numbers match.
			eNetQuoteHistoryLookupPage.verifyQuotenumberMatches(quoteNumber);
			
			//Step 10 : Click the Back button.
			eNetQuoteHistoryLookupPage.clickOnSelectAnotherQuoteButton();
	
			//Search results grid is displayed  
			eNetQuoteHistoryLookupPage.verifysearchResultsGrid();
			
			//Step 11 : Click Modify Search     
			eNetQuoteHistoryLookupPage.clickOnSearchButton();
			
			//The Rate Quote History Search criteria box is displayed     
			eNetQuoteHistoryLookupPage.verifySerachCriteriaFeilds();
			
			//Step 12 : In the User ID field, enter JROGE and click Submit
			eNetQuoteHistoryLookupPage.enteruserfeild("JROGE");
			eNetQuoteHistoryLookupPage.clickOnSearchButton();
		
			//Search results grid is displayed.    
			eNetQuoteHistoryLookupPage.verifysearchResultsGrid();
			
			quoteNumbers = eNetQuoteHistoryLookupPage.captureQuotesResultTable();
		 	searchString = quoteNumbers.size(); 
		
		 	//Step 13 : Connect to EXLAQA and run the following query:
			/*Select GSRID FROM fbfiles.gsc00p100
			Where gsrrnam='JROGE' And GSRACT Not in
			(Select CMCUST From Fbfiles.rap001 Where CMSTAT='I') 
			ORDER BY GSRID DESC*/
		 	//Used the below query by refering the UFT test - as the above query returns different quote numbers 		 	
     
		 	query = "Select gsrid, gsratmst, gsranam, GSRACT, GSRORG, GSQPRIC, GSRRNAM, GSRTYP, GSRAPP, GSQSVC FROM fbfiles.gsc00p100 as a left join FBFILES.gsc00p110 as b on a.gsrid = b.gsqid Where gsrrnam='JROGE' And GSRACT Not in (Select CMCUST From Fbfiles.rap001 Where CMSTAT='I') and GSQPRIC <> 0 and GSQDSP ='S' ORDER BY  gsratmst desc";

		 	//Query results are returned
	        List<String> quoteNumbersFromDB = sqlDataList.getQuoteFromQuery(query);
	        testUtil.setHardWait(2000);
	        
	        //Step 14 : Verify that quote numbers returned in Step 13 match quote numbers returned in Step 12
	        Assert.assertTrue((quoteNumbers.equals(quoteNumbersFromDB)));
  	 
			//Logout and close eNet application
			eNetHomePage.clickOnLogout();

			// Confirm by clicking logout button
			eNetHomePage.clickOnLogoutButton();	
		
	 }






}
