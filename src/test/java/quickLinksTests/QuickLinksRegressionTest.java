package quickLinksTests;

import org.testng.annotations.Test;

import billOfLadingPages.MyEstesBillOfLadingPage;
import imageRetrievalPages.MyEstesImageRetrievalPage;
import myEstesPages.MyEstesAddNewAddressPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesCustomizeQuickLinkPage;
import myEstesPages.MyEstesEditAddressPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesPickupRequestPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesShipmentTrackingPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesTerminalLookupPage;
import myEstesPages.MyEstesUserProfilePage;
import myEstesPages.MyEstesWelcomePage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;
import transitTimeCalculatorPages.TransitTimeCalculatorPage;

public class QuickLinksRegressionTest extends TestBase{
	
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalLookupPage myEstesterminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesUserProfilePage myEstesUserProfilePage = new MyEstesUserProfilePage();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();
	MyEstesEditAddressPage myEstesEditAddressPage = new MyEstesEditAddressPage();
	MyEstesAddNewAddressPage myEstesAddNewAddressPage = new MyEstesAddNewAddressPage();
	MyEstesImageRetrievalPage myEstesImageRetrievalPage = new MyEstesImageRetrievalPage();
	TransitTimeCalculatorPage transitTimeCalculatorPage = new TransitTimeCalculatorPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesBillOfLadingPage myestesBillOfLadingPage = new MyEstesBillOfLadingPage();
	MyEstesPickupRequestPage myestesPickupRequestPage = new MyEstesPickupRequestPage();
	MyEstesCustomizeQuickLinkPage myEstesCustomizeQuickLinkPage = new MyEstesCustomizeQuickLinkPage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();
	RateQuotePage ltlRateQuotePage = new RateQuotePage();
	
	/**
	 * This test case needs to be updated in JIRA as many UI have changed. This is passing automation as
	 * we updated it but test case needs to be updated.
	 * 
	 * Quick Links - Authenticated - Verify set of link(s) are displayed and
	 * functions correctly in Popular Tools widget on the Estes homepage
	 */
	
	
	@Test(enabled = true, priority = 1)
	public void executeQZ_9248()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();

		//Step 5: From *Welcome Page*, click on the *Estes* link on Top left corner to navigate to HomePage
		myEstesWelcomePage.clickOnEstesLogoLink();
		//Step 6: From Popular Tools widget, Click *Calculate Transit Times* link
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnPTCalculateTransitTimeLink();
		testUtil.setHardWait(1000);
		transitTimeCalculatorPage.verifyTransitTimeCalculatorPage();
		//Step 7: Click *Back* button on browser
		driver.navigate().back();		

		//Step 8: From Popular Tools widget, Click *Create a Bill of Lading* link
		myEstesWelcomePage.clickOnShipTab();
		myEstesHomePage.clickOnPTCreateBillOfLadingLink();
		myestesBillOfLadingPage.verifyPageTitle();
		
		//Step 9: Click *Back* button on browser
		driver.navigate().back();

		//Step 10: From Popular Tools widget, Click *Schedule a Pickup* link
		myEstesWelcomePage.clickOnShipTab();
		myEstesHomePage.clickOnPTScheduleAPickupLink();
		myestesPickupRequestPage.verifyPageTitle();
		
		//Step 11: Click *Back* button on browser
		driver.navigate().back();

		//Step 12: From Popular Tools widget, Click *Document Viewing* link
		myEstesWelcomePage.clickOnShipTab();  
		myEstesHomePage.clickOnPTScheduleAPickupLink();  
		testUtil.setHardWait(3000);
		
		myEstesHomePage.clickOnPTViewShippingDocumentsLink();
		myEstesImageRetrievalPage.verifyDocumentViewingPage();
		
		//Step 13: Click *Back* button on browser
		driver.navigate().back();

		//Step 14: From Popular Tools widget, Click *Terminal Lookup* link
//		myEstesHomePage.clickOnPTTerminalLookupLink();	   //changed to below line	
		myEstesHomePage.clickOnTerminallookupQuickLink();   //<<--
		myEstesterminalLookupPage.verifyTerminalLookupPage();
	}

	
	
	/*
	 * Quick Links - Unauthenticated - Verify set of link(s) are displayed and
	 * functions correctly in Popular Tools widget on the Estes homepage
	 */

	@Test(enabled = true, priority = 2)

		public void executeQZ_9247() throws InterruptedException {

			// Step 3: From Estes Homepage under Popular Tools Widget, Click *Calculate
					// Transit Times* link
					myEstesWelcomePage.clickOnShipTab();
					myEstesHomePage.clickOnPTCalculateTransitTimeLink();
					// Validate user navigates to Transit Time Calculator application page.
					transitTimeCalculatorPage.verifyTransitTimeCalculatorPage();
					testUtil.setHardWait(10000);
					// Step 4: Click *Back* button on browser
					driver.navigate().back();

			        //Step 5: From Popular Tools widget, Click *Create a Bill of Lading* link
					myEstesWelcomePage.clickOnShipTab();
					myEstesHomePage.clickOnPTCreateBillOfLadingLink();
					//Validate user navigates to My Estes Login Page.
					myEstesLoginPage.loginPageDisplayed();
					testUtil.setHardWait(10000);
					//Click Back button on browser
					driver.navigate().back();

					//Step 7: From Popular Tools widget, Click *Schedule a Pickup* link
					myEstesWelcomePage.clickOnShipTab();
					myEstesHomePage.clickOnPTScheduleAPickupLink();
					//Validate user navigates to My Estes Login Page.
					myEstesLoginPage.loginPageDisplayed();
					testUtil.setHardWait(10000);
					//Click Back button on browser
					driver.navigate().back();

					//Step 9: From Popular Tools widget, Click *View Shipping Documents* link
					myEstesHomePage.clickOnPTViewShippingDocumentsLink();
					myEstesImageRetrievalPage.verifyDocumentRetrievalPage();  //changed to below method
					testUtil.setHardWait(10000);
					//Click Back button on browser
					driver.navigate().back();

					//Step 11: From Popular Tools widget, Click *Terminal Lookup* link
//					myEstesHomePage.clickOnPTTerminalLookupLink();   --changed to below line
					myEstesHomePage.clickOnTerminallookupQuickLink();
					myEstesterminalLookupPage.verifyTerminalLookupPage();
				}



	
	/**
	 * Quick Links - Authenticated - Verify user is not able to add more than 5Quick
	 * links and receives an error when adding sixth quick link
	 */

	@Test(enabled = true, priority = 3)
	public void executeQZ_7685()
			throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnCustomizeLink();
		myEstesCustomizeQuickLinkPage.validateTitle();

		do {

			myEstesCustomizeQuickLinkPage.clickOnAddButton();

		}

		while (myEstesCustomizeQuickLinkPage.validateErrorMessage());

	}
	
	// UPDATED ON 12/5/2019

		/*
		 * Quick Links - Unauthenticated - Verify user is able to see and click on all
		 * default Quick Links and navigate to selected application page.
		 */

		@Test(enabled = true, priority = 4)

		public void executeQZ_7682()
				throws InterruptedException {

			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			myEstesHomePage.verifyQuickLiks();
			testUtil.setHardWait(2000);
			myEstesHomePage.clickOnTrack();
			myEstesHomePage.clickOnShipmentTraking();
			myEstesShipmentTrackingPage.verifyShipmentsTrackingPageDisplayed();
			testUtil.setHardWait(1000);
			myEstesHomePage.clickOnLTLRateQuote();
			testUtil.setHardWait(2000);
			ltlRateQuotePage.verifyRateQuoteEstimatePage();
			testUtil.setHardWait(1000);
			myEstesHomePage.clickOnDocumentRetrieval();
			myEstesImageRetrievalPage.verifyImageRetrievalPageTile();
			testUtil.setHardWait(1000);
			myEstesHomePage.clickOnTerminalLookupLink();
			myEstesterminalLookupPage.verifyTerminalLookupPageTile();
			testUtil.setHardWait(1000);
			myEstesHomePage.clickOnTransitTimeCalculator();
			transitTimeCalculatorPage.verifyTransitTimeCalculatorPage();

		}

		


		
}
