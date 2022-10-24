package quickLinksTests;

import org.testng.annotations.Test;

import eNetPages.ENetRestrictedPage;
import imageRetrievalPages.MyEstesImageRetrievalPage;
import myEstesPages.MyEstesCustomizeQuickLinkPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesTerminalLookupPage;
import rateQuotePages.RateQuotePage;
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;
import transitTimeCalculatorPages.TransitTimeCalculatorPage;

public class QuickLinksSmokeTest extends TestBase {
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesCustomizeQuickLinkPage myEstesCustomizeQuickLinkPage = new MyEstesCustomizeQuickLinkPage();
	ENetRestrictedPage 	eNetRestrictedPage =new ENetRestrictedPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	MyEstesTerminalLookupPage terminalLookupPage = new MyEstesTerminalLookupPage();
	TransitTimeCalculatorPage transitTimeCalculatorPage = new TransitTimeCalculatorPage();
	MyEstesImageRetrievalPage myEstesImageRetrievalPage = new MyEstesImageRetrievalPage();
	
	
	/******************************* TESTS *******************************/
	/*	
	 * Quick Links - Authenticated - Verify user is able to see and click on all
	 * default Quick Links and navigate to selected application page
	 */

	@Test(enabled = true, priority = 1)
	public void executeQZ_9246()
			throws Exception {

		// Login to MyEstes as Smokenat
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(500);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Shipment Tracking
		myEstesCustomizeQuickLinkPage.addOrRemoveQuickLinks("Shipment Tracking");// why you're adding this?
		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();
		
		shipmentTrackingPage.verifyShippingTrackingPage();

		driver.navigate().back();

		// LTL Rate Quote
		myEstesCustomizeQuickLinkPage.addOrRemoveQuickLinks("LTL Rate Quote");// why you're adding this?
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLTLRateQuote();
		rateQuotePage.verifyRateQuotePageTitle();

		driver.navigate().back();
		testUtil.setHardWait(1000);
	
		// Image Retrieval
		myEstesCustomizeQuickLinkPage.addOrRemoveQuickLinks("Document Retrieval ");
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnDocumentRetrieval();
		testUtil.setHardWait(1000);
		myEstesImageRetrievalPage.verifyDocumentViewingPage(); 

		driver.navigate().back();
		
		// Terminal Lookup
		testUtil.setHardWait(2000);
		myEstesCustomizeQuickLinkPage.addOrRemoveQuickLinks("Terminal Lookup");
		myEstesHomePage.clickOnTerminallookupLink();
		terminalLookupPage.verifyTerminalLookupPage();
		testUtil.setHardWait(500);
		driver.navigate().back();
		testUtil.setHardWait(1000);
		// Transit Time Calculator

		myEstesCustomizeQuickLinkPage.addOrRemoveQuickLinks("Transit Time Calculator");

		myEstesHomePage.clickOnTransitTimeCalculator();
		transitTimeCalculatorPage.verifyTransitTimeCalculatorPage();

	}
	
}
