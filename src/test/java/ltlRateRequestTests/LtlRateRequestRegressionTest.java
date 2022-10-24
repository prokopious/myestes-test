package ltlRateRequestTests;

import static org.testng.Assert.assertTrue;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import billOfLadingPages.MyEstesBillOfLadingPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQouteRateEstimatePage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;

public class LtlRateRequestRegressionTest extends TestBase {

	
	Logger logger = Logger.getLogger(LtlRateRequestRegressionTest.class.getClass());
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	RateQouteRateEstimatePage rateEstimatePage = new RateQouteRateEstimatePage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetLTLRateRequestPage eNetLTLRateRequestPage = new ENetLTLRateRequestPage();
	ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
	/******************************* TESTS *******************************/
	
	
	/**
	 * This fails because Get quote is displed instead of contact me link
	 * 
	 * Rate Quote - Less Than Truckload - Verify Guaranteed LTL 10AM, 12PM & 5PM
	 * display a 'Contact Me' link for invalid service in points file.
	 * 
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_5092()
			throws InterruptedException {

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Create new quotes with invalid service points
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("22911");
		rateQuotePage.enterDesZip("55307");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);

		rateQuotePage.clickOnStartNewQuote();
		rateQuotePage.clickOnConfirmButton();
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("23219");
		rateQuotePage.enterDesZip("37304");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);

		rateQuotePage.clickOnStartNewQuote();
		rateQuotePage.clickOnConfirmButton();
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("22911");
		rateQuotePage.enterDesZip("83012");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);
	}
	
	 //AS PER DEE, TESTS THAT VERIFY EMAIL IN THE ROUNDCUBE SHOULD BE TUNRED OFF UNTIL FURTHER NOTICE 
	
		@Test(enabled = true, priority = 2)
		public void executeQZ_866()
				throws InterruptedException {

			// Step 1: Open My Estes app
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();

			// Step 2: Login as admin
			myEstesLoginPage.enterUserName(username15);
			myEstesLoginPage.enterPassword(password15);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Navigate to Ship -> Rate Quote
			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			// Step 4: Select only *LTL (Incl. Guaranteed)* checkbox. --> No action
			// performed here as this is the default selection

			// Step 5: Select *Consignee as Roles and Collect as Terms --> No action done to
			// select term as Collect is the default term for Consignee
			rateQuotePage.enterMyRole("Consignee");

			// Step 6: Origin -> 30307, Destination -> 77071
			rateQuotePage.enterOriginZip("30307");
			rateQuotePage.enterDesZip("77071");

			// Step 7: Class -> 100, Total Weight -> 879, Description -> QZ-847
			rateQuotePage.enterClass("100");
			rateQuotePage.enterTotalWeight("879");
			rateQuotePage.enterDesc("QZ-866");

			// Step 8: Select *Overlength Freight (40 feet or greater)* accessorial
			rateQuotePage.selectOrDeselectAccessorials("Overlength Freight (40 feet or greater)");

			// Step 9: Click on Submit
			rateQuotePage.clickOnSubmitButton();

			// Step 10: Verify disclaimer
			rateQuotePage.verifyDisclaimer();

			// Step 11: Verify all Gauranteed rates display contact me
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

			// Step 12: Click Contact me for any gauranteed service level, enter req fields
			// and submit
			rateQuotePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");
			testUtil.setHardWait(1000);
			// Verify Contact Us dialog displayed
			rateQuotePage.verifyContactUsDialogExistence();

			// Enter data Contact Us dialog
			rateQuotePage.enterContactName("QA Tester");
			rateQuotePage.enterYourEmail("EITQA@estes-express.com");
			rateQuotePage.enterPhoneNo("5546648842");
			rateQuotePage.selectTodayDate();
			rateQuotePage.enterPieces("1");
			rateQuotePage.enterPieceType("PALLET");
			rateQuotePage.enterLength("20");
			rateQuotePage.enterWidth("20");
			rateQuotePage.enterHeight("20");

			// Click on confirm button
			rateQuotePage.clickOnConfirmButton();

			// Step 13: Access round cube and verify email
			logger.info("Verify email content manually!!!!!!");
		}
	
		/**
		 * 
		 * This passed on 6/27/22 after failing on Jenkins
		 * 
		 * Rate Quote - LTL Rate Request - Verify Disclaimer and 'Contact Me' message is
		 * displayed when 'Overlength Freight (12.00' to 19.99')' accessorial is
		 * selected.
		 * 
		 */
		
		/**Updated script per new changes hence turned on the test**/
		
		@Test(enabled = true, priority = 3)

		public void executeQZ_864()
				throws InterruptedException {

			// Login to My Estes application as group user
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			testUtil.setHardWait(500);
			myEstesLoginPage.enterUserName(username2);
			myEstesLoginPage.enterPassword(password2);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Rate Quote page is displayed
			rateQuotePage.verifyCreateRateQoutePage();

			// Enter Requester information
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("90001");
			rateQuotePage.enterDesZip("23001");
			testUtil.setHardWait(1000);
			// Enter commodities information
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("1500");

			// Select Accessorials
			testUtil.setHardWait(2000);
			rateQuotePage.selectOrDeselectOverLengthFreight();

			// Click Submit Request
			rateQuotePage.clickOnSubmitButton();
			testUtil.setHardWait(3000);
			// verify disclaimer message and contact me
			rateQuotePage.verifyDisclaimer();
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

			// click on getquote
			rateQuotePage.clickOnGetQuoteButton();
			// recordquotenumber
			testUtil.setHardWait(1000);
			rateQuotePage.recordQuoteNumber("LTL Standard Transit");
			// verify overlength freight
			rateQuotePage.verifyOverlengthFreight();

		}
		
		/*
		 * Rate Quote - LTL - Verify on the fly lookup functionality for Origin and
		 * Destination zips.
		 * 
		 */

		@Test(enabled = true, priority = 4)
		public void executeQZ_858() throws InterruptedException {

			// Login to MyEstes
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username7);
			myEstesLoginPage.enterPassword(password7);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Ship--> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();
			testUtil.setHardWait(2000);
			rateQuotePage.enterAccountNumber("3878407");
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("500");
			String originAddress = rateQuotePage.clickAndCaptureOriginZipFlyLookup("16159");
			System.out.println("The origin address is: " + originAddress);
			String destinationAddress = rateQuotePage.clickAndCaptureDestZipFlyLookup("30307");
			System.out.println("The destination  address is: " + destinationAddress);
			rateQuotePage.clickOnSubmitButton();
			rateQuotePage.clickOnGetQuoteButton();
			testUtil.setHardWait(3000);
			// Validating Fly Lookup address
			rateQuotePage.verifyOriginAddressFromContactInfo(originAddress);
			rateQuotePage.verifyDestinationAddressFromContactInfo(destinationAddress);
		}
		
		
		
		/**
		 * 
		 * This test needs tobe turned off until its  its updated
		 * 
		 * Rate Quote - LTL - Verify Accessorial Charges are displayed under Charge
		 * Items with associated costs in the rate quote details.
		 * 
		 */

		@Test(enabled = false, priority = 5)

		public void executeQZ_850()
				throws InterruptedException {

			// Login as group user
			myEstesHomePage.clickOnMyEstes();
			testUtil.setHardWait(2000);
			myEstesHomePage.clickOnLogin();
			testUtil.setHardWait(3000);
			myEstesLoginPage.enterUserName(username3);
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterPassword(password3);
			myEstesLoginPage.clickOnLoginButton();

			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();
			testUtil.setHardWait(2000);
			// Enter Requester information
			rateQuotePage.enterAccountNumber("3878407");
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("30307");
			rateQuotePage.enterDesZip("30301");

			// Enter commodities information
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("6450");
			rateQuotePage.enterDesc("Test");

			// Select Accessorial
			//rateQuotePage.selectOrDeselectAccessorials("Appointment Request");
	        
	        //rateQuotePage.selectOrDeselectInsideDelivery();
	        
	        rateQuotePage.clickOnViewAllAccessorials();

	        /*
	         * *Appointment Request
	         *Inside Delivery
	         *Notify Request
	         *Lift-Gate Service (Pickup)
	         *Lift-Gate Service (Delivery)
	         *Residential Delivery
	         *Unloading Services Requested By Consignee

	         */
	        rateQuotePage.selectOrDeselectAccessorials("Appointment Request");
			rateQuotePage.selectOrDeselectAccessorials("Notify Request");
			rateQuotePage.selectOrDeselectResidentialDelivery();
			rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Delivery) ");
			rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Pickup) ");
			//rateQuotePage.selectOrDeselectResidentialDelivery();
			rateQuotePage.selectOrDeselectAccessorials("Unloading Services Requested By Consignee");  
		

			// Click submit
			rateQuotePage.clickOnSubmitButton();

			// Get Quote for LTL Standard transit
			rateEstimatePage.clickOnGetQuoteButtonByServiceLevel("LTL Standard Transit");
			testUtil.setHardWait(2000);
			// Record Quote#
			rateQuotePage.recordQuoteNumber("LTL Standard Transit");

			// Validate the selected Accessorials are listed as *Charge Items* with their
			// associated *Charge*
			// NOTE: $0.00 is a valid charge amount as specific charges may be waived for a
			// customer
			String charge1 = rateQuotePage.getChargeItemsTableCharges("Appointment Request");
			rateQuotePage.verifyChargeItemsTable("Appointment Request", charge1);

			String charge2 = rateQuotePage.getChargeItemsTableCharges("Inside Delivery");
			rateQuotePage.verifyChargeItemsTable("Inside Delivery", charge2);

			String charge3 = rateQuotePage.getChargeItemsTableCharges("Notify Request");
			rateQuotePage.verifyChargeItemsTable("Notify Request", charge3);

			String charge4 = rateQuotePage.getChargeItemsTableCharges("Lift-Gate Service (Delivery)");
			rateQuotePage.verifyChargeItemsTable("Lift-Gate Service (Delivery)", charge4);

			String charge5 = rateQuotePage.getChargeItemsTableCharges("Lift-Gate Service (Pickup)");
			rateQuotePage.verifyChargeItemsTable("Lift-Gate Service (Pickup)", charge5);

			String charge6 = rateQuotePage.getChargeItemsTableCharges("Residential Delivery");
			rateQuotePage.verifyChargeItemsTable("Residential Delivery", charge6);

			String charge7 = rateQuotePage.getChargeItemsTableCharges("Unloading Services Requested By Consignee");
			rateQuotePage.verifyChargeItemsTable("Unloading Services Requested By Consignee", charge7);

		}
		
		
		/*
		 * Rate Quote- LTL - Verify when selecting Origin or Destination terminal info a
		 * Supporting Terminal Information modal displays with the correct data
		 */

		@Test(enabled = true, priority = 6)
		public void executeQZ_847()
				throws InterruptedException {

			// Step 1: Open application
			// Step 2: Login
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username2);
			myEstesLoginPage.enterPassword(password2);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Ship--> Rate Quote
			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			// Step 4: Select *Less than Truckload (incl. Guaranteed)* // No action done as
			// this is service level is selected by default

			// Step 5: Select *Consignee as Roles and Collect as Terms //No action done to
			// select term as Collect is the default term for Consignee
			rateQuotePage.enterMyRole("Consignee");

			// Step 6: Origin -> 30307, Destination -> 77071
			rateQuotePage.enterOriginZip("30307");
			rateQuotePage.enterDesZip("77071");

			// Step 7: Class -> 100, Total Weight -> 879, Description -> QZ-847
			rateQuotePage.enterClass("100");
			rateQuotePage.enterTotalWeight("879");
			rateQuotePage.enterDesc("QZ-847");

			// Step 8: Click on Submit
			rateQuotePage.clickOnSubmitButton();

			// Step 9: Get Quote for LTL Standard Transit
			rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

			// Step 10: Click on City/State/Zip link under origin
			rateQuotePage.clickOnZipInQuoteDetails("30307");
			// Validate Supporting Terminal modal is displayed
			rateQuotePage.verifySupportingTerminalInfoDisplayed();

			// Step 11: Validate Supporting Terminal Details
			rateQuotePage.verifySupportingTerminalDetails();

			// Step 12 & 13 : Click Terminal email address --> This step is to verify email.
			logger.info("Verify Email manually!!!!");

			// Step 14: Close Supporting Terminal modal
			rateQuotePage.clickCloseButtonOnPopup();

			// Step 15: Click on City/State/Zip link under Destination
			rateQuotePage.clickOnZipInQuoteDetails("77071");
			// Validate Supporting Terminal modal is displayed
			rateQuotePage.verifySupportingTerminalInfoDisplayed();

			// Step 16: Validate Supporting Terminal Details
			rateQuotePage.verifySupportingTerminalDetails();

			// Step 17 & 18: Click Terminal email address --> This step is to verify email.
			logger.info("Verify Email manually!!!!");

			// Step 19: Close Supporting Terminal modal
			rateQuotePage.clickCloseButtonOnPopup();
		}

		
		/*
		 * Rate Quote - LTL - Verify an Account Number can be entered and then a Rate
		 * Quote request submitted.
		 * 
		 */

		@Test(enabled = true, priority = 7) 

		public void executeQZ_845() throws InterruptedException {
			// Login as national user
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username1);
			myEstesLoginPage.enterPassword(password1);
			testUtil.setHardWait(500);
			myEstesLoginPage.clickOnLoginButton();

			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();
			testUtil.setHardWait(1000);
			// Enter Account Number: 6202474
			rateQuotePage.enterAccountNumber("6202474");

			// Enter Requester information
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("16159");
			rateQuotePage.enterDesZip("30307");

			// Enter commodity information
			rateQuotePage.selectClass("50");
			rateQuotePage.enterTotalWeight("500");

			// Click on submit
			rateQuotePage.clickOnSubmitButton();

			// Verify RateQuote Results screen is displayed with service level options for
			// LTL
			rateQuotePage.verifyTableResult();
			rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
			rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		}
		
		
		
		/**
		 * 
		 * Test passed on 6/24/22
		 * Rate Quote - LTL - Verify when total weight is over the LTL weight limit a
		 * message advising the user to select the Volume and Truckload quote type is
		 * displayed
		 */
		@Test(enabled = true, priority = 8)
		public void executeQZ_843()
				throws InterruptedException {

			String expectedErrMsg = "To receive rates for a shipment weighing more than 20,000 lbs., please select the Volume and Truckload option in the Quote Type section above.";

			// Step 1: Open My Estes app
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			testUtil.setHardWait(1000);
			// Step 2: Login as smokelocal
			myEstesLoginPage.enterUserName(username7);
			myEstesLoginPage.enterPassword(password7);
			testUtil.setHardWait(3000);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Navigate to Ship -> Rate Quote
			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			// Step 4: Only *Less than Truckload (incl. Guaranteed)* quote type is selected
			// by default.
			// This is selected by default
			testUtil.setHardWait(2000);
			// Step 5: Entre requester info
			rateQuotePage.enterAccountNumber("3878407");
			rateQuotePage.selectRole();
			rateQuotePage.selectTerms();

			// Step 6: Enter routing info
			rateQuotePage.enterOriginZip("23059");
			rateQuotePage.enterDesZip("30307");

			// Step 7: Enter commdoity details
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("10000");
			rateQuotePage.enterDesc("Testdata2");

			// Step 8: Click on Add Commodity button
			rateQuotePage.clickOnAddCommodityButton();

			// Step 9: Enter commodity details
			rateQuotePage.enterClass2("50");
			rateQuotePage.enterTotalWeight2("5000");
			rateQuotePage.enterDesc2("TestData2");

			// Step 10: Click on Add commodity button
			rateQuotePage.clickOnAddCommodityButton();

			// Step 11: Enter commodity details
			rateQuotePage.enterClass3("50");
			rateQuotePage.enterTotalWeight3("5001");
			rateQuotePage.enterDesc3("TestData3");

			// Verify error message
			String actualErrMsg = testUtil
					.getTextOfElement(driver.findElement(By.xpath("//app-commodity-list//lib-feedback//span")));
			Assert.assertEquals(actualErrMsg, expectedErrMsg);
		}
		
		
		
		@Test(enabled = true, priority = 9)

		public void executeQZ_841()
				throws InterruptedException {

			// Login as local user
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username4);
			myEstesLoginPage.enterPassword(password4);
			myEstesLoginPage.clickOnLoginButton();
			testUtil.setHardWait(1000);
			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();
			// select quote type
			rateQuotePage.lessThanTruckloadSelected();
			// Enter Requester information
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("16159");
			rateQuotePage.enterDesZip("30307");

			// Enter commodity information
			rateQuotePage.selectClass("50");
			rateQuotePage.enterTotalWeight("50");
			// Click on submit
			rateEstimatePage.clickOnSubmitButton();
			// click on get quote button for LTL
			rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
			// verify commodity rate is 0
			rateQuotePage.verifyRateIsZero();
			// verify commodity charge is 0
			rateQuotePage.verifyChargeIsZero();
			// verify min change is displayed and its not equal to 0
			rateQuotePage.verifyMinChargeItem();

		}
		
		/**
		 * This test fails because contact me link displays intead of Get Quote
		 * 
		 * Rate Quote - LTL - Verify Buttons and messages are displayed when Guaranteed
		 * options selected.
		 * 
		 */

		@Test(enabled = true, priority = 10)

		public void executeQZ_836() throws InterruptedException {

			// login to My Estes application as Smokelocal
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username2);
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterPassword(password2);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// enter Requester information
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// enter Routing information
			rateQuotePage.enterOriginZip("23230");
			rateQuotePage.enterDesZip("23059");

			// enter commodities information
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("975");

			// click Submit Request
			rateQuotePage.clickOnSubmitButton();

			// get Quote # for Guaranteed LTL Standard Transit: 5PM service level

			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
			rateQuotePage.verifyMessageforLTL10AM();
			rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 10AM");

			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");
			rateQuotePage.verifyMessageforLTL12PM();
			rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 12PM");

			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");
			rateQuotePage.verifyMessageforLTL5PM();
			rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 5PM");
		}

		

		/*
		 * Rate Quote - LTL - Verify error message is received if Account number entered
		 * is not party to the Account - National Account
		 */
		@Test(enabled = true, priority = 11)
		public void executeQZ_835()
				throws InterruptedException {

			String expectedErrMsg = "Please enter a valid account number to rate this quote.";

			// Step 1: Open My Estes app
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();

			// Step 2: Login as smokelocal
			myEstesLoginPage.enterUserName(username1);
			myEstesLoginPage.enterPassword(password1);
			testUtil.setHardWait(1000);

			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Navigate to Ship -> Rate Quote
			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			// Step 4: Select only *LTL (Incl. Guaranteed)* checkbox. --> No action
			// performed here as this is the default selection
			testUtil.setHardWait(3000);
			// Step 5: Enter requester info
			rateQuotePage.enterAccountNumber("0062372");
			rateQuotePage.selectRole();
			rateQuotePage.selectTerms();

			// Step 6: Enter routing info
			rateQuotePage.enterOriginZip("23230");
			rateQuotePage.enterDesZip("30307");

			// Step 7: Enter details in commodity section
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("9500");

			// Step 8: Click on submit
			rateQuotePage.clickOnSubmit();
			// Verify error message
			String actualErrMsg = testUtil
					.getTextOfElement(driver.findElement(By.xpath("//app-create-rate-quote//lib-feedback//span")));
			Assert.assertEquals(actualErrMsg, expectedErrMsg);

		}

		
		
		/*
		 * Rate Quote - LTL - Verify the Fuel Surcharge link is functioning properly
		 * 
		 */

		@Test(enabled = true, priority = 12)

		public void executeQZ_831() throws InterruptedException {
			// Login as group user
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username3);
			testUtil.setHardWait(3000);
			myEstesLoginPage.enterPassword(password3);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Enter Account Number: 3878407
			rateQuotePage.enterAccountNumber("3878407");

			// Enter Requester information
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");
			testUtil.setHardWait(2000);
			// Enter Routing information
			rateQuotePage.enterOriginZip("23059");
			rateQuotePage.enterDesZip("30307");

			// Enter commodity information
			rateQuotePage.selectClass("50");
			rateQuotePage.enterTotalWeight("1500");

			// Click on submit
			rateQuotePage.clickOnSubmitButton();

			// Get Quote# from LTL Standard Transit
			rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

			// Record Quote#
			rateQuotePage.recordQuoteNumber("LTL Standard Transit");

			// Click on FuelSurcharge link from charge items section
			Assert.assertTrue(rateQuotePage.verifyFuelSurchargeIsNotDisplayed());
			rateQuotePage.clickOnFuelSurchargeLink();
			testUtil.setHardWait(1000);
			// Validate user navigates to Fuel Surcharge application screen
			testUtil.switchToWindow(1);
			testUtil.setHardWait(1000);
			Assert.assertEquals(testUtil.getTitle(), "Fuel Surcharge | Estes Express Lines");
		}

		/*
		 * Rate Quote - LTL - Verify Manhattan and Alaska Freight and Fuel line items
		 * and charges display under Charge Items when they are selected as Origin and
		 * Destination.
		 */

		@Test(enabled = true, priority = 13) 
		public void executeQZ_829()
				throws InterruptedException {

			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			testUtil.setHardWait(3000);
			myEstesLoginPage.enterUserName(username1);
			testUtil.setHardWait(2000);
			myEstesLoginPage.enterPassword(password1);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			rateQuotePage.enterAccountNumber("6202474");
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");
			rateQuotePage.selectOriginCountry("United States");
			rateQuotePage.enterOriginZip("10014");
			rateQuotePage.selectDestinationCountry("United States");
			testUtil.setHardWait(2000);
			rateQuotePage.enterDesZip("99502");
			rateQuotePage.enterClass("100");
			rateQuotePage.enterTotalWeight("987");
			rateQuotePage.enterDesc("This is test case validation");
			rateQuotePage.clickOnSubmitButton();

			// testUtil.waitForNextPageToBeReady();

			rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
			rateQuotePage.verifyChargeItemsTable("Manhattan, NY Pickup Charge", "$0.00");
	        rateQuotePage.validateChargeItemsTableDescription("ALASKA FREIGHT CHARGES");
	        rateQuotePage.validateChargeItemsTableDescription("ALASKA FUEL SURCHARGE"); 


		}	
		
		
		/**
		 * Rate Quote - LTL - Verify when 'Create BOL' button is clicked the user is
		 * navigated to the BOL Application and details in the Rate Quote form are
		 * transferred
		 * 
		 * @throws InterruptedException
		 */
		
		
		@Test(enabled = true, priority = 14)

		public void executeQZ_827()
				throws InterruptedException {

			// Login to MyEstes
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterUserName(username7);
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterPassword(password7);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Ship--> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Requester Information
			rateQuotePage.enterAccountNumber("3878407");
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Routing Information
			rateQuotePage.enterOriginZip("23230");
			rateQuotePage.enterDesZip("30307");

			// Commodities
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("975");
			rateQuotePage.enterDesc("From Rate Quote to Create BOL");
			testUtil.setHardWait(1000);
			// Accessorials
			rateQuotePage.clickOnViewAllAccessorials();
			rateQuotePage.clickOnAppointmentRequest();
			rateQuotePage.clickOnLiftGateServiceDelivery();
			rateQuotePage.clickOnSubmitButton();
			rateQuotePage.verifyRateQuoteResultPage();

			// Get Quote
			rateQuotePage.clickOnGetQuoteButton("10AM");
			rateQuotePage.recordQuoteNumber("Gauranteed 10 AM");
			rateQuotePage.recordQuoteNumberFromResultTable();
			// Reserve Shipment
			rateQuotePage.selectIAcceptTheTermsAndConditionsCheckBox();
			rateQuotePage.clickOnReserveShipmentButton();
			rateQuotePage.validateReserveShipmentModelIsOpened();
			rateQuotePage.enterReserveShipmentEmailAddress("abc@estes-express.com");
			rateQuotePage.clickReserveShipmentButtonOnDialog();
			rateQuotePage.verifyRequestReceivedMessage();
			rateQuotePage.clickOnCreateBOLButton();

			// Validation
			myEstesBillOfLadingPage.verifyBillOfLandingPage();
			myEstesBillOfLadingPage.verifySuccessMessage();
			myEstesBillOfLadingPage.verifyQuoteNumberFieldIsAutoFill();
			myEstesBillOfLadingPage.verifyShipperZipCodeIsAutoFill();
			myEstesBillOfLadingPage.verifyConsigneeZipCodeIsAutoFill();
			myEstesBillOfLadingPage.verifySelectedAccessorialName("Appointment Request");
			myEstesBillOfLadingPage.verifySelectedAccessorialName("Lift-Gate Service (Delivery)");
		}	
		
		
		
		/*
		 * Rate Quote - LTL - Verify a Reserve Shipment button is displayed for
		 * Guaranteed option quotes.
		 */
		
		
		@Test(enabled = true, priority = 15)

		public void executeQZ_825() throws InterruptedException {

			
			// Login to My Estes application as group user
			myEstesHomePage.clickOnMyEstes();
			testUtil.setHardWait(1000);
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username3);
			testUtil.setHardWait(2000);
			myEstesLoginPage.enterPassword(password3);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();
			testUtil.setHardWait(2000);
			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Enter Requester information
			rateQuotePage.enterAccountNumber("3878407");
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("23230");
			rateQuotePage.enterDesZip("30307");

			// Enter commodities information
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("975");

			// Click Submit Request
			rateQuotePage.clickOnSubmitButton();
	        rateQuotePage.verifyRateQuoteResultPage(); 

			// Get Quote # for Guaranteed LTL Standard Transit: 5PM service level

			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
			rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 10AM");
			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");
			rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 12PM");
			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");
			rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 5PM");
		}
		
		
		/**
		 * Fails because error message is not being displyed.
		 * 
		 * Rate Quote - LTL - Verify when origin or/and destination are blocked points
		 * an error message is displayed
		 */
		@Test(enabled = true, priority =16)

		public void executeQZ_824()
				throws InterruptedException {

			String expectedErrMsg = "Additional charges may apply. Please call our rate quote department at 804-353-1900, Ext. 2269, so we can help make sure you are aware of any applicable charges.";

			// Step 1: Open application
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();

			// Step 2: Login as group user
			myEstesLoginPage.enterUserName(username7);
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterPassword(password7);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Navigate Ship -> Rate
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Step 4: Only *Less than Truckload (Incl. Guaranteed)* rate quote type is
			// selected (by default)
			// Note -> No action performed here as this is selected by default
			testUtil.setHardWait(1000);
			// Step 5: Enter requester info
			rateQuotePage.enterAccountNumber("3878407");
			rateQuotePage.selectRole();
			rateQuotePage.selectTerms();

			// Step 6: Enter routing info
			rateQuotePage.enterOriginZip("23230");
			rateQuotePage.enterDesZip("98303");

			// Step 7: Enter commodity details
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("1500");

			// Step 8: Click on Submit
			rateQuotePage.clickOnSubmit();
			String actualTxt = testUtil.getTextOfElement(driver.findElement(By.xpath("//lib-feedback//div/span")));
			Assert.assertEquals(actualTxt, expectedErrMsg);

		}
		

		// EAMIL STEP WITH ROUNDCUBE CAN BE VARIFIED MANUALLY.IT'S NOT ADDED IN THE
		// SCRIPT

		 //AS PER DEE, TESTS THAT VERIFY EMAIL IN THE ROUNDCUBE SHOULD BE TUNRED OFF UNTIL FURTHER NOTICE 
		
		@Test(enabled = false, priority = 17)

		public void executeQZ_823() throws InterruptedException {

			// Login as local user
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username2);
			myEstesLoginPage.enterPassword(password2);
			myEstesLoginPage.clickOnLoginButton();

			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Enter Requester information
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("16159");
			rateQuotePage.enterDesZip("30307");

			// Enter commodity information
			rateQuotePage.selectClass("50");
			rateQuotePage.enterTotalWeight("975");

			// Select any over-length accessorial
			rateQuotePage.selectOrDeselectOverLengthFreight();

			// Click on submit
			rateQuotePage.clickOnSubmitButton();

			// Click on one of the contact me link
			rateQuotePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");

			// Verify Contact Us dialog displayed
			rateQuotePage.verifyContactUsDialogExistence();

			// Enter data Contact Us dialog
			rateQuotePage.enterContactName("QA Tester");
			rateQuotePage.enterYourEmail("EITQA@estes-express.com");
			rateQuotePage.enterPhoneNo("5546648842");
			rateQuotePage.selectTodayDate();
			rateQuotePage.enterPieces("7");
			rateQuotePage.enterPieceType("PALLET");
			rateQuotePage.enterLength("20");
			rateQuotePage.enterWidth("20");
			rateQuotePage.enterHeight("20");

			// Click on confirm button
			rateQuotePage.clickOnConfirmButton();
			logger.info("Verify email content manually!!!!!!");
		}
	
		

		/*
		 * Rate Quote - LTL - Verify 'Contact Us' is displayed for Guaranteed service
		 * levels when Limited Access Accessorial is selected.
		 */

		@Test(enabled = true, priority = 18)

		public void executeQZ_820()
				throws InterruptedException {

			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username1);
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterPassword(password1);
			testUtil.setHardWait(4000);
			myEstesLoginPage.clickOnLoginButton();
			testUtil.setHardWait(1000);
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			rateQuotePage.enterAccountNumber("6202474");
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");
			rateQuotePage.selectOriginCountry("United States");
			rateQuotePage.enterOriginZip("23230");
			rateQuotePage.selectDestinationCountry("United States");
			rateQuotePage.enterDesZip("30307");
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("975");
			
			rateQuotePage.clickOnViewAllAccessorials();
			rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
			rateQuotePage.selectOrDeselectExhibitionChargeCallForChargesForLasVegasChicagoOrNY();
			rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
			rateQuotePage.selectOrDeselectSecuredLocationsPickupPrisonsMilitaryBases();
			rateQuotePage.clikOnSubmitButton();

			rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
			rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

			rateQuotePage.clickOnReviseQouteButton();
			rateQuotePage.selectOrDeselectExhibitionChargeCallForChargesForLasVegasChicagoOrNY();
			rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
			rateQuotePage.selectOrDeselectSecuredLocationsPickupPrisonsMilitaryBases();
			rateQuotePage.selectOrDeselectConstructionSiteDelivery();
			rateQuotePage.selectOrDeselectResidentialDelivery();
			rateQuotePage.clikOnSubmitButton();

			rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);
			rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 10AM", true);
			rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 12PM", true);
			rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 12PM", true);

			rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
			rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		}
		
		
		//This script deals with database, which change the current date to Holiday and Undo the process
		
		@Test(enabled = false, priority = 19)
		public void executeQZ_844() throws InterruptedException, ClassNotFoundException, SQLException {
			String todayDate = testUtil.getTodayDateByFormat("YYYYMMdd");
			String updateAsHoliday = "Update FBFILES.FRP932 set WDEXCP = 'H' where WDDAT8 = '"+todayDate+"'";
			String updateAsWorkingDay = "Update FBFILES.FRP932 set WDEXCP = '' where WDDAT8 = '"+todayDate+"'";
			
			//Update the current date as Holiday
			testUtil.sendQuery(updateAsHoliday);
			
			try {
				//Login to MyEstes
				myEstesHomePage.clickOnMyEstes();
				myEstesHomePage.clickOnLogin();
				myEstesLoginPage.enterUserName(username2);
				myEstesLoginPage.enterPassword(password2);
				myEstesLoginPage.clickOnLoginButton();
						
				//Ship--> Rate Quote
				myEstesWelcomePage.clickOnShipTab();
				myEstesWelcomePage.clickOnRateQoute();
				rateQuotePage.enterMyRole("Third Party");
				rateQuotePage.enterTerms("Prepaid");
				rateQuotePage.enterOriginZip("23233");
				rateQuotePage.enterDesZip("30307");
				rateQuotePage.enterClass("50");
				rateQuotePage.enterTotalWeight("1000");
				rateQuotePage.clickOnSubmitButton();
				
				//Validate Guaranteed as 'Contact Me'
				rateQuotePage.clickOnContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
				rateQuotePage.clickOnContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
				rateQuotePage.clickOnContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 5PM");
				
				//Undo the current date
				testUtil.sendQuery(updateAsWorkingDay);
				rateQuotePage.clickOnReviseQouteButton();
				rateQuotePage.clickOnSubmitButton();
				
				//Validate Guaranteed as 'Rates'
				rateQuotePage.validateGetQuoteButtonByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
				rateQuotePage.validateGetQuoteButtonByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
				rateQuotePage.validateGetQuoteButtonByServiceLevel("Guaranteed LTL Standard Transit: 5PM");
			
			} catch (Exception e) {
				throw(e);
			
			} finally {
				testUtil.sendQuery(updateAsWorkingDay);
			}
		}
		

		
		/*
		 * Web - LTL - Verify Request Pickup button is not displayed for all 
		 * Guaranteed Service level options (ALM TEST ID: 505 - not in ALM, removed UFT label)
		 */
		
		
		@Test(enabled = true, priority = 20)

		public void executeQZ_5093() throws InterruptedException {

			
			// Login to My Estes application as testadmin user
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username4);
			myEstesLoginPage.enterPassword(password4);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Navigate to Ship -> Rate Quote
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Enter Requester information
			
			rateQuotePage.enterMyRole("Shipper");
			rateQuotePage.enterTerms("Prepaid");

			// Enter Routing information
			rateQuotePage.enterOriginZip("30307");
			rateQuotePage.enterDesZip("23230");

			// Enter commodities information
			rateQuotePage.enterClass("50");
			rateQuotePage.enterTotalWeight("4500");
			rateQuotePage.enterDesc("Testdata");

			// Click Submit Request
			rateQuotePage.clickOnSubmitButton();
	        rateQuotePage.verifyRateQuoteResultPage(); 

			// Validate *Request Pickup* button is Displayed or *NOT* displayed under *Next Steps* section

	        rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
	        rateQuotePage.verifyRequestPickupButtonIsDisplayed();
			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
			rateQuotePage.verifyRequestPickupButtonIsNotDisplayed();
			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");
			rateQuotePage.verifyRequestPickupButtonIsNotDisplayed();
			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");
			rateQuotePage.verifyRequestPickupButtonIsNotDisplayed();
		}
		

		/* LTL Rate Quote - Verify LTL Standard Transit quote ID prefix is L */

		@Test(enabled = true, priority = 21)

		public void executeQZ_5485() throws InterruptedException {

			String accontNumber = "5068692";

			// Login to application
			driver.get(url2);
			eNetLoginPage.enterUserID(username16);
			eNetLoginPage.enterUserPassword(password16);
			eNetLoginPage.clickOnLoginButton();
			testUtil.setHardWait(2000);

			// Click Applications
			eNetHomePage.clickOnApplicationWithoutFrame();
			testUtil.setHardWait(3000);
			// Click Rate Retriever link
			eNetHomePage.clickOnRateRetrieverLink();

			// Enter values and click submit
			eNetLTLRateQuotePage.enterAccountNumber(accontNumber);
			eNetLTLRateQuotePage.selectRole("Third Party");
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			eNetLTLRateQuotePage.enterOriginZip("16159");
			eNetLTLRateQuotePage.enterDestinationZip("90007");
			eNetLTLRateQuotePage.selectClass("100");
			testUtil.setHardWait(2000);
			eNetLTLRateQuotePage.enterWeight("975");
			eNetLTLRateQuotePage.clickOnSubmitButton();

			// Verify Quote number returned
			String quoteNumber = eNetLTLRateRequestPage.recordQuoteNo();

			// Verify the quote prefix is L
			assertTrue(quoteNumber.contains("L"));

		}

		
		
}
