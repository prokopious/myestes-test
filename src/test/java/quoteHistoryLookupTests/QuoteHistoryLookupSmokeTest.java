package quoteHistoryLookupTests;

import org.testng.annotations.Test;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;

import eNetPages.ENetLoginPage;
import eNetPages.ENetQuoteHistoryLookupPage;

import testBase.TestBase;


public class QuoteHistoryLookupSmokeTest extends TestBase {


	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetQuoteHistoryLookupPage eNetQuoteHistoryLookupPage = new ENetQuoteHistoryLookupPage();

	
	/*****************************************/
	
	
	/* eNet - Quote History - Account Number lookup No Search criteria message */

	@Test(enabled = true, priority = 1)

	public void executeQZ_7084() throws Exception {

		// Step 1: Open within the Internet Explorer Browser, the following link:
		driver.get(url2);

		// Step 2: Login with the following credentials:
		// User ID: qaenet01
		// Password: qaenet01
		eNetLoginPage.enterUserID(username16);
		eNetLoginPage.enterUserPassword(password16);

		// Step 3: Click on Login Button
		eNetLoginPage.clickOnLoginButton();
		testUtil.setHardWait(2000);
		// Step 4: From the enet page, find the *Customer Service* applications list
		eNetHomePage.clickOnCustomerServiceLink();

		// Step 5: click *Quote History Lookup*
		
		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
		testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();

		// Step 6: Under the *Search Criteria* section, select *Show All Filters*
		// checkbox
		
		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.verifyAdditinalFiltersExpanded();

		// Step 7: From the *Additional Filters* section, select the *Binocular* icon
		eNetQuoteHistoryLookupPage.clickOnBinocularIcon();
		eNetQuoteHistoryLookupPage.verifyAccountSearchPopupDisplays();
		eNetQuoteHistoryLookupPage.verifyAccountNameTab();
		eNetQuoteHistoryLookupPage.verifyAddressTab();

		// Step 8: Select the 'Account Name' tab
		eNetQuoteHistoryLookupPage.selectAccounNameTab();

		// Step 9: Enter 'Mosquito Bay' in the 'Account Name' field
		String accountName = "Mosquito Bay";
		eNetQuoteHistoryLookupPage.enterAccoutName(accountName);

		// Step 10: Click *Submit*
		
		eNetQuoteHistoryLookupPage.clickOnSubmit();
		eNetQuoteHistoryLookupPage.verifyNoResultsMesageIsDisplayed();

		// Step 11: Click *Log Out* from top navigation
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();

	}
	
	//This test is failing on Jenkins. It runs locally
	
	 /* eNet - Quote History - Account Search using the Binocular icon */
	
	 @Test(enabled = false, priority = 2)

   public void executeQZ_7088() throws Exception {
   	
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
	testUtil.switchToFrame("mainpage");
	eNetQuoteHistoryLookupPage.verifyPage();
	
	//Step 5: Under the *Search Criteria* section, select *Show All Filters* checkbox
	//The *Additional Filters* section expanded and more filters are displayed
	eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
	eNetQuoteHistoryLookupPage.verifyAdditinalFiltersExpanded();
	
	//Step 6: From the *Additional Filters* section, select the *Binocular* icon next to the Account field
	//Verify *Account Search* popup window displays
	eNetQuoteHistoryLookupPage.clickOnBinocularIcon();
	eNetQuoteHistoryLookupPage.verifyAccountSearchPopupDisplays();
	
	//Step 7: From the *Account Search* page,
	//validate *Search By* defaults to *Account Name* and the tab is selected.
	eNetQuoteHistoryLookupPage.verifyAccountNameTab();
	
	//Step 8: Search by *Home Depot*
	String accountName ="Home Depot";
	eNetQuoteHistoryLookupPage.enterAccoutName(accountName);
	
	//Step 9: Click *Submit*
	//Verify *Exact Results* and *Results* tables are displayed for *Home Depot* accounts
	eNetQuoteHistoryLookupPage.clickOnSubmit();
	eNetQuoteHistoryLookupPage.verifyExactResultsDisplayedforAccountName(accountName);
	
	//Step 10: Click the *Address* tab
	//Verify the following fields display:
	//- Street Address
	//- City
	//- State
	//- Zip
	eNetQuoteHistoryLookupPage.clickOnAddressTab();
	eNetQuoteHistoryLookupPage.verifyFeildsinaddressFeilds();
	
	//Step 11:Enter/select the following data:
	//Street Address: BELVIDERE & BROAD ST
	//City: RICHMOND
	//State: Virginia
	//Zip: 23220
	eNetQuoteHistoryLookupPage.enterStreetAddress("BELVIDERE & BROAD ST");
	eNetQuoteHistoryLookupPage.enterCity("RICHMOND");
	eNetQuoteHistoryLookupPage.enterState("Virginia");
	eNetQuoteHistoryLookupPage.enterZip("23220");
	
	eNetQuoteHistoryLookupPage.clickonSubmitButtonInAddressTab();

	//Step 12: Verify *Results* table displays for the search criteria
	eNetQuoteHistoryLookupPage.verifyResutsDisplayedforAddress("BELVIDERE & BROAD ST", "RICHMOND","VA", "23220" );
	
	//Step 13: Select the *Account Number* link 
	//The popup window closes and the Account field in the *Additional Filters* section is populated with the selected value
	String recordAccountNumber = eNetQuoteHistoryLookupPage.selectAccountNumberLink();
	System.out.println("Recored Account number"+recordAccountNumber);
	testUtil.setHardWait(5000);
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainpage");
	eNetQuoteHistoryLookupPage.verifyPage();
	eNetQuoteHistoryLookupPage.verifyAccountNumberPreFilled(recordAccountNumber);
					
	//Step 14: Click *Log Out* from top navigation
	eNetHomePage.clickOnLogout();

	//Confirm by clicking logout button
	eNetHomePage.clickOnLogoutButton();
   
   }
	 
		
	//This test is failing on Jenkins. It runs locally
	 
	 /* eNet - Quote History - Account Number lookup Search results Account Number link with results */
		
	 @Test(enabled = false, priority = 3)

    public void executeQZ_7086() throws Exception {
    	
    	//Step 1: Open within the Internet Explorer Browser, the following link:
		//Login with the following credentials:
    	//User ID: qaenet01
    	//Password: qaenet01
    	driver.get(url2);
    			    	
    	eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		
		//Step 2: From eNet Home page, Select 'Customer Service' link in the 'Application' section
		//Customer Service link when selected contains additional sub sections based on user permissions.
		eNetHomePage.clickOnCustomerServiceLink();
		
		//Step 3: From Customer Service page, Select 'Quote History Lookup' link
		//User is brought to the 'Quote History' screen where the 'Account Number' lookup (binoculars) functionality is available.
		eNetApplicationsPage.clickOnquoteHistoryLookupLink();
		testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();
		
		//Step 4: Check the box for Show All Filters, then Select the 'Binocular' widget
		//The 'Account Search' popup window is displayed to the user where two Search by tabs are available:
		eNetQuoteHistoryLookupPage.clickOnShowAllFiltersCheckbox();
		eNetQuoteHistoryLookupPage.clickOnBinocularIcon();
		eNetQuoteHistoryLookupPage.verifyAccountSearchPopupDisplays();
		
		//Step 5: Select the 'Account Name' tab
		eNetQuoteHistoryLookupPage.verifyAccountNameTab();
		eNetQuoteHistoryLookupPage.selectAccounNameTab();
		
		//Step 6: Enter 'Test' in the 'Account Name' field
		String accountName ="CDM TEST"; //Using test data as the given data is not working
		eNetQuoteHistoryLookupPage.enterAccoutName(accountName);
		
		//Step 7: Click *Submit*
		//User is presented with search results fitting the criteria entered in Step 6.
		eNetQuoteHistoryLookupPage.clickOnSubmit();
		eNetQuoteHistoryLookupPage.verifyExactResultsDisplayedforAccountName(accountName);
		
		//Step 8: Scroll the displayed results and select the 'Account Number' link to the account number 'C123455'
		//User is returned to the 'Rate Quote History Lookup' screen where the Account Number selected 
		//via the link in Step 7 is pre-filled in the 'Customer Number' field.
		eNetQuoteHistoryLookupPage.selectAccountNumberLink("C123455");
		testUtil.setHardWait(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		eNetQuoteHistoryLookupPage.verifyPage();
		testUtil.setHardWait(4000);
		eNetQuoteHistoryLookupPage.verifyAccountNumberPreFilled("C123455");
		
		//Step 9: Click <Search>
		eNetQuoteHistoryLookupPage.clickOnBinocularIcon();
		
		//Not in test steps to get the acount number in step -10 followed these tests 
		eNetQuoteHistoryLookupPage.verifyAccountNameTab();
		
		String accountName1 ="TEST"; 
		eNetQuoteHistoryLookupPage.enterAccoutName(accountName1);
		eNetQuoteHistoryLookupPage.clickOnSubmit();
					
		//Step 10;Scroll the displayed results and select the 'Account Number' link to the quote fitting account number '0101000'
		//User is returned to the 'Rate Quote History Lookup' screen where the Account Number selected via the link in Step 7 is 
		//pre-filled in the 'Customer Number' field
		eNetQuoteHistoryLookupPage.verifyAccountNameTab();
		eNetQuoteHistoryLookupPage.selectAccountNumberLink("0101000");
		testUtil.setHardWait(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		
		eNetQuoteHistoryLookupPage.verifyPage();
		eNetQuoteHistoryLookupPage.verifyAccountNumberPreFilled("0101000");
						
		//Step 11: Click <Search>
		//User is presented with the message '0 Quotes Available'
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.veifyMessage();
		
		//Logout and close eNet application
		eNetHomePage.clickOnLogout();

		// Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();	
		
	 }
	 


}
