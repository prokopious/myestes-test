package pickupRequestTests;


import java.text.ParseException;
import java.util.Set;

import org.testng.annotations.Test;

import com.jagacy.Key;
import com.jagacy.SessionVt;


import as400TempletTests.TempletTests;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.FreightBillInquiryEnterFieldValuesScreen;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.PickupRequestMaintenanceScreen;
import jagacyVT.screens.PickupRequestProcessing;
import jagacyVT.screens.PickupSystemMenuScreen;
import jagacyVT.screens.PickupSystemReconciliationsBySID;
import jagacyVT.screens.PickupSystemRequestQueueScreen;
import jagacyVT.screens.SecondaryFreightBillingMenuScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import jagacyVT.screens.UpdateScreen3;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesPickupRequestPage;
import myEstesPages.MyEstesPickupVisibility;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQuotePage;
import rateQuotePages.RatesPage;
import testBase.TestBase;
import util.TestUtil;

public class PickupRequestRegressionTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesPickupRequestPage myEstesPickupRequestPage = new MyEstesPickupRequestPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	RatesPage ratesPage = new RatesPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	
	
	
	/******************************* TESTS *******************************/
	
	/*
	 * Pickup Request - Verify error message generates when the Total Weight is
	 * greater than 45,000.
	 */
	
	@Test(enabled = true, priority = 1)

	public void executeQZ_7565() throws InterruptedException {

		String expectedAlert = "The weight cannot exceed 45,000 pounds.";

		// Launch application
		myEstesHomePage.clickOnMyEstes();

		// Login to application
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate Shipment tab -> Pickup request
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnPickupRequest();
		testUtil.setHardWait(500);
		// Fill out required information
		// Enter Contact name
		myEstesPickupRequestPage.enterRandomContactName();
		// Enter Requester Phone#
		testUtil.setHardWait(3000);
		myEstesPickupRequestPage.enterRandomRequesterPhoneNumber();
		// Enter Email
		myEstesPickupRequestPage.enterRandomRequesterEmail();
		// Select Role as Shipper
		myEstesPickupRequestPage.selectShipperRole();
		// Enter Shipper contact name
		myEstesPickupRequestPage.enterRandomShipperContactName();
		// Enter shipper email
		myEstesPickupRequestPage.enterRandomShipperEmail();
		// Enter Pickup Date
		String todayDate = testUtil.todaysDate();
		myEstesPickupRequestPage.enterPickupDate(todayDate);

		// Enter Destination, Total Piece
		myEstesPickupRequestPage.enterDestinationZip("23230");
		myEstesPickupRequestPage.enterTotalPiece("10");

		// Enter Weight as "45001"
		myEstesPickupRequestPage.enterTotalWeight("45001");

		// Click on submit
		myEstesPickupRequestPage.clickOnSubmit();
		testUtil.setHardWait(4000);
		// Verify the following error displayed 'The weight cannot exceed 45,000
		// pounds.'
		String actualAlert = myEstesPickupRequestPage.captureAlertMessage();
		TestUtil.verifyText(actualAlert, expectedAlert);
	}

	/*
	 * Pickup Request - Verify error message generates for Pickup Date in the past
	 * and when extension is more than 5 digits
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_7560()
			throws Exception {

		String expectedErrMsg = "This field has reached its maximum length of 5 characters.";
		String expectedErrMsg1 = "Minimum date is " + testUtil.todaysDate() + ".";

		// Launch application
		myEstesHomePage.clickOnMyEstes();

		// Login to application
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate Shipment tab -> Pickup request
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnPickupRequest();

		// Enter other required fields in Requester info
		myEstesPickupRequestPage.enterRandomContactName();
		myEstesPickupRequestPage.enterRandomRequesterPhoneNumber();
		myEstesPickupRequestPage.enterRandomRequesterEmail();
		Thread.sleep(2000);
		// Select Role as Shipper
		myEstesPickupRequestPage.selectShipperRole();
		// Enter Account# - 3713175
		myEstesPickupRequestPage.enterAccountNumber("3713175");

		// Enter details in Shipper info
		// myEstesPickupRequestPage.enterRandomShipperContactName();
		Thread.sleep(2000);
		// myEstesPickupRequestPage.enterRandomShipperPhoneNumber();

		// Enter Shipper extension in 6 digits - 123456
		myEstesPickupRequestPage.enterShipperPhoneExtension("123456");
		myEstesPickupRequestPage.enterRandomShipperEmail();

		// Verify error message: This field has reached its maximum length of 5
		// characters.
		testUtil.setHardWait(1000);
		String actualErrMsg = myEstesPickupRequestPage.captureMaxErrorMessage();
		System.out.println(actualErrMsg);
		TestUtil.verifyText(actualErrMsg, expectedErrMsg);

		// Update Shipper extension to 5 digits
		myEstesPickupRequestPage.enterShipperPhoneExtension("12345");

		// Enter past date as Pickup date
		myEstesPickupRequestPage.enterPickupDate(testUtil.addPastWeekDay());
		myEstesPickupRequestPage.enterRandomShipperEmail();

		// Verify error message: Pick up date field highlighted in red 'Minimum date is
		// Current Date (MM/DD/YYYY).'
		String actualErrMsg1 = myEstesPickupRequestPage.captureMaxErrorMessage();
		TestUtil.verifyText(actualErrMsg1, expectedErrMsg1);

	}

	/**
	 * 
	 * This fails because data is not prefilled int eh Requester info section 
	 * 
	 * 
	 * Pickup Request - Verify the user can submit a Pickup Request when Pickup Date
	 * selected is 30 days from current date.
	 */
	
	@Test(enabled = true, priority = 3)

	public void executeQZ_7561()
			throws InterruptedException, ParseException {

		// Launch application
		myEstesHomePage.clickOnMyEstes();

		// Login to application
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate Shipment tab -> Pickup request
		//myEstesHomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnPickupRequest();

		// Select Address from address book

		/** THE STEP BELOW IS AN EXTRA STEP */
		testUtil.setHardWait(3000);
		
		// myEstesPickupRequestPage.selectRequesterAddressFromAddressBook("test");
		
		 myEstesPickupRequestPage.selectFirstAddressFromRequesterAddressBook(); 
		 testUtil.setHardWait(3000);
		// Select Role as Consignee
		myEstesPickupRequestPage.selectConsigneeRole();

		// Click on use Requester info in Shipper info region
		myEstesPickupRequestPage.clickOnUseRequesterInfoCheckbox();

		// Enter required fields
		myEstesPickupRequestPage.enterCompanyName("ADVANCE AUTO PARTS");
		myEstesPickupRequestPage.enterAddressLine1("134 CANAL ST");
		myEstesPickupRequestPage.enterCityName("Salem");
		myEstesPickupRequestPage.enterStateName("MA");
		myEstesPickupRequestPage.enterZipCode("01970");

		// Select pickup Date 30 days from current date
		String pickDte = testUtil.isWeekend(testUtil.addOrSubstractDateFromTodayDate(25));
		myEstesPickupRequestPage.enterPickupDate(pickDte);

		// Enter Shipment information details
		myEstesPickupRequestPage.enterDestinationZip("23230");
		myEstesPickupRequestPage.enterTotalPiece("2");
		myEstesPickupRequestPage.enterTotalWeight("1000");

		// Click on submit
		myEstesPickupRequestPage.clickOnSubmit();
		testUtil.setHardWait(4000);
		// Verify Confirmation
		myEstesPickupRequestPage.verifyConfirmationMsg();

	}

	/*
	 * Pickup Request - Verify error message generates for Pickup Date more than 30
	 * days.
	 */
	@Test(enabled = true, priority = 4)

	public void executeQZ_7557()
			throws InterruptedException, ParseException {

		String expectedErrMsg = "Maximum date is " + testUtil.addOrSubstractDateFromTodayDate(30) + ".";

		// Launch application
		myEstesHomePage.clickOnMyEstes();

		// Login to application
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate Shipment tab -> Pickup request
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnPickupRequest();

		// Enter other required fields in Requester info
		myEstesPickupRequestPage.enterRandomContactName();
		// Select Role as Shipper
		myEstesPickupRequestPage.selectShipperRole();
		myEstesPickupRequestPage.enterRandomRequesterPhoneNumber();
		myEstesPickupRequestPage.enterRandomRequesterEmail();
		myEstesPickupRequestPage.enterRandomShipperContactName();
		myEstesPickupRequestPage.enterRandomShipperPhoneNumber();
		myEstesPickupRequestPage.enterRandomShipperEmail();

		// Select pickup Date more than 30 days from current date
		String pickDte = testUtil.isWeekend(testUtil.addOrSubstractDateFromTodayDate(35));
		myEstesPickupRequestPage.enterPickupDate(pickDte);
		myEstesPickupRequestPage.enterRandomShipperEmail();
		testUtil.setHardWait(4000);
		// Verify the following error(s) should be displayed 'Maximum Date is "Current
		// Date+30 days" (MM/DD/YYYY).
		String actualErrMsg = myEstesPickupRequestPage.captureMaxErrorMessage();
		TestUtil.verifyText(actualErrMsg, expectedErrMsg);

	}
	// THIS IS PICKUP HISTORY THAT HAS BEEN ADDED TO THIS. BUT IT WILL BE MOVED INTO
	// A SEPARATE PACKAGE ----> MOVED TO PICKUP HISTORY PACKAGE
	/*
	 * @Test(enabled = true, priority = 5) public void
	 * qz7552_verifyTheUserCanViewPickupRequestDetailsByClickingOnRequestNumberLink
	 * () throws InterruptedException {
	 * 
	 * //Launch application myEstesHomePage.clickOnMyEstes();
	 * 
	 * //Login to application myEstesHomePage.clickOnLogin();
	 * myEstesLoginPage.enterUserName(username1);
	 * myEstesLoginPage.enterPassword(password1);
	 * myEstesLoginPage.clickOnLoginButton();
	 * 
	 * //Navigate Shipment tab -> Pickup request myEstesHomePage.clickOnShipTab();
	 * myEstesHomePage.clickOnPickupRequest();
	 * 
	 * //Navigate to View Pickup request tab
	 * myEstesPickupRequestPage.clickOnViewPickupHistory();
	 * 
	 * //Click on any request number link String selectedReqNum =
	 * myEstesPickupRequestPage.capturAndSelectFirstRequestNo();
	 * 
	 * //Verify Pick details dialog displayed
	 * myEstesPickupRequestPage.verifyPickUpDetails(selectedReqNum);
	 * 
	 * }
	 */

	/*
	 * Pickup Request - Verify error message display when required fields are blank
	 */
	@Test(enabled = true, priority = 5)

	public void executeQZ_7558() throws InterruptedException {

		// Launch application
		myEstesHomePage.clickOnMyEstes();

		// Login to application
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate Shipment tab -> Pickup request
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnPickupRequest();
		// Click on Hazmat Checkbox
		myEstesPickupRequestPage.clickOnHazmat();

		// Select Guaranteed radiobutton
		myEstesPickupRequestPage.selectShipmentType("Guaranteed/TimeCritical");
		myEstesPickupRequestPage.clearRequesterEmail();
		myEstesPickupRequestPage.clearContactName(); // Added extra step to clear the autofilled data
		// Click Submit without entering any values
		myEstesPickupRequestPage.clickOnSubmit();

		// Verify Required field error message displayed for Requester info section
		myEstesPickupRequestPage.verifyReqFields("RequesterInfo");

		// Verify Required field error message displayed for Shipper info section
		myEstesPickupRequestPage.verifyReqFields("ShipperInfo");

		// Verify Required field error message displayed for Pickup details section
		myEstesPickupRequestPage.verifyReqFields("PickupDetails");

		// Verify Required field error message displayed for Shipment details section
		myEstesPickupRequestPage.verifyReqFields("ShipmentDetails");

		// Verify Required field displayed for Special Instructions field
		myEstesPickupRequestPage.verifyReqFields("Hazmat");

		// Verify Required field displayed for Terms of Service
		myEstesPickupRequestPage.verifyReqFields("GauranteedTerms");

	}
	//Turned off on Jenkins- runs locally-fails on selectPartyRole step
	/*
	 * Pickup Request - Verify the user can add multiple shipment line in the same
	 * request and classify each with multiple service/shipment type and delete
	 * shipment line.
	 * 
	 */
	@Test(enabled = false, priority = 6)

	public void executeQZ_7559()
			throws InterruptedException {
	
         //Step 1: Launch application
         myEstesHomePage.clickOnMyEstes();

         //Step 2: Login to application as local user
         myEstesHomePage.clickOnLogin();
         myEstesLoginPage.enterUserName(username2);
         myEstesLoginPage.enterPassword(password2);
         myEstesLoginPage.clickOnLoginButton();

         //Step 3: Navigate Shipment tab -> Pickup request
         myEstesHomePage.clickOnShipTab();
         myEstesHomePage.clickOnPickupRequest();
         testUtil.setHardWait(2000);
         /** THE STEP BELOW IS AN EXTRA STEP- WAITING AJITHA'S CONFIRMATION */  //--> Verified and updated based on test steps
         //Step 4: Select Role as Third Party
         myEstesPickupRequestPage.selectThirdPartyRole();
         
         //Step 5: Shipper Info section, click on Address Book link
       //  myEstesPickupRequestPage.selectShipperAddressFromAddressBook("test");
         myEstesPickupRequestPage.SelectFirstAddressFromShipperAddressBook();
         
         testUtil.setHardWait(3000);
         //Step 6: Fill required fields
         myEstesPickupRequestPage.enterRequesterContactName("QA Tester");
         myEstesPickupRequestPage.enterRequesterEmail("qaTest@estes-express.com");

         //Step 7: Enter current date as pickup date
         myEstesPickupRequestPage.enterPickupDate(testUtil.todaysDate());
         testUtil.setHardWait(2000);
         //Step 8: Select available by 9:15 AM, Select Close by 6:30 PM
         myEstesPickupRequestPage.selectAvailableBy("9", "15", "AM");
         testUtil.setHardWait(2000);
         myEstesPickupRequestPage.selectClosesBy("6", "30", "PM");

         testUtil.setHardWait(2000);
         myEstesPickupRequestPage.enterPickUpZip("20171");
         //Step 9:  Click on Lift gate services and Do not stack pallets checkbox
         myEstesPickupRequestPage.clickOnLiftGateService();
         myEstesPickupRequestPage.clickOnDoNotStckPallets();

         //Step 10: Enter Destination for first line and other required fields
         myEstesPickupRequestPage.enterDestinationZip("28212");
         myEstesPickupRequestPage.enterTotalPiece("1");
         myEstesPickupRequestPage.enterTotalWeight("2000");
         
         // Click on Accepted Request in email notification section
         myEstesPickupRequestPage.clickOnAcceptedReq();

         //Step 11: Click on Add Another line button
         myEstesPickupRequestPage.clickOnAddAnotherLineButton();
         
         //Step 12: Enter destination and other required fields 
         myEstesPickupRequestPage.enterDestinationZipByLine(1, "23225");
         myEstesPickupRequestPage.enterTotalPieceByLine(1, "2");
         myEstesPickupRequestPage.enterTotalWeightByLine(1, "3000");
         
         //Click Estes Forwarding Radio button
        // myEstesPickupRequestPage.clickOnEstesForwardingRadioButtonByLine(1);
         
         // Click on Oversize checkbox
         myEstesPickupRequestPage.clickOnOverSizeByLine(1);
         testUtil.setHardWait(3000);

         //Step 13: Click Add Another line button
         myEstesPickupRequestPage.clickOnAddAnotherLineButton();
         
         //Step 14: Enter destination and other required fields for 
         myEstesPickupRequestPage.enterDestinationZipByLine(2, "30307");
         myEstesPickupRequestPage.enterTotalPieceByLine(2, "5");
         myEstesPickupRequestPage.enterTotalWeightByLine(2, "5500");
         
         // Click on Hazmat
         myEstesPickupRequestPage.clickOnHazmatByLine(2);
         myEstesPickupRequestPage.enterSpecialInstructionsBy(2, "Testing is fun");
         // Click on Estes Forwarding Worldwide radio button
     //    myEstesPickupRequestPage.clickOnEstesForwardingRadioButtonByLine(2);

         // Click on Freeze and Poison checkbox
         myEstesPickupRequestPage.clickOnFreezeByLine(2);
         myEstesPickupRequestPage.clickOnPoisonByLine(2);
         myEstesPickupRequestPage.clickOnFoodByLine(2);
         // Click on Accepted Request
         myEstesPickupRequestPage.clickOnAcceptedReqByLine(2);
         
         //Step 15: Click on Add Another line
         myEstesPickupRequestPage.clickOnAddAnotherLineButton();
         
         //Step 16: Enter shipment details 
         // Enter destination and other required fields
         myEstesPickupRequestPage.enterDestinationZipByLine(2, "30666");
         myEstesPickupRequestPage.enterTotalPieceByLine(2, "6");
         myEstesPickupRequestPage.enterTotalWeightByLine(2, "6000");

         // Click Oversize,Freeze,Food and Poison checkbox
         myEstesPickupRequestPage.clickOnOverSizeByLine(2);
         myEstesPickupRequestPage.clickOnFoodByLine(2);
         myEstesPickupRequestPage.clickOnFreezeByLine(2);
         myEstesPickupRequestPage.clickOnPoisonByLine(2);

         // Click on Accepted Request 
         myEstesPickupRequestPage.clickOnAcceptedReqByLine(2);
         
         //Step 17: Click on the delete line button and delete any one row 
         // Delete one of the line
         myEstesPickupRequestPage.clickOnDeleteLineByLine(2);
         
         //Step 18: Click Confirm to Delete
         myEstesPickupRequestPage.clickOnConfirmButton();
         
         //Step 19: Click Submit
         myEstesPickupRequestPage.clickOnSubmit();

         // Verify confirmation message
         myEstesPickupRequestPage.verifyConfirmationMsg();
         
         //Step 20: Click on Request Another Pickup button
         myEstesPickupRequestPage.clickOnRequestAnotherPickup();

         // Verify user is navigated to Pickup request page
         myEstesPickupRequestPage.verifyPageTitle();
         
         //Step 21: Select Role as consignee
         myEstesPickupRequestPage.selectConsigneeRole();
         
         //Enter required fields in requester info
         myEstesPickupRequestPage.enterRandomContactName();
         // Enter Requester Phone#
         myEstesPickupRequestPage.enterRequesterPhoneNumber("8001236789");
         // Enter Email
         myEstesPickupRequestPage.enterRandomRequesterEmail();

         // Enter required fields in shipper info
         myEstesPickupRequestPage.enterPickUpZip("02150"); 
         myEstesPickupRequestPage.clickOnUseMyEstesAccountInfo();
         myEstesPickupRequestPage.enterShipperContactName("Test");
         myEstesPickupRequestPage.enterRandomShipperEmail();
         testUtil.setHardWait(2000);

         // Enter destination and other required fields
         myEstesPickupRequestPage.enterDestinationZip("89102");
         myEstesPickupRequestPage.enterTotalPiece("1");
         myEstesPickupRequestPage.enterTotalWeight("7000");
         
         // Click Oversize,Freeze,Food and Poison checkbox
         myEstesPickupRequestPage.clickOnOversize();
         myEstesPickupRequestPage.clickOnFood();
         myEstesPickupRequestPage.clickOnFreeze();
         myEstesPickupRequestPage.clickOnPoison();

         // Click on Accepted Request 
         myEstesPickupRequestPage.clickOnAcceptedReq();
         
         //Step 22: Click on Submit
         myEstesPickupRequestPage.clickOnSubmit();
         
         // Verify confirmation message
         myEstesPickupRequestPage.verifyConfirmationMsg();
         
         //Step 23: Click on Request Another Pickup button
         myEstesPickupRequestPage.clickOnRequestAnotherPickup();

         // Verify user is navigated to Pickup request page
         myEstesPickupRequestPage.verifyPageTitle();
         
         //Enter required fields
         myEstesPickupRequestPage.enterRandomContactName();
         // Enter Requester Phone#
         myEstesPickupRequestPage.enterRandomRequesterPhoneNumber();
         // Enter Email
         myEstesPickupRequestPage.enterRandomRequesterEmail();
         
         //Step 24: Select Role as other
         myEstesPickupRequestPage.selectOtherRole();
         
         myEstesPickupRequestPage.enterPickUpZip("02150"); 
         // Enter required fields
         myEstesPickupRequestPage.clickOnUseMyEstesAccountInfo();
         myEstesPickupRequestPage.enterShipperContactName("Test");
         myEstesPickupRequestPage.enterRandomShipperEmail();
         testUtil.setHardWait(2000);
         
         //Step 25: Select prior pickup request
         myEstesPickupRequestPage.selectPriorPickupRequest("Pcs 1 / Wgt 7000");

         // Verify Destination total piece and weight displayed for the selected record
         myEstesPickupRequestPage.verifyShipmentInfo("89102", "1", "7000");

         //Step 26: Click on Accepted Request
         myEstesPickupRequestPage.clickOnAcceptedReq();

         //Step 27: Click Submit
         myEstesPickupRequestPage.clickOnSubmit();

         // Verify confirmation message
         myEstesPickupRequestPage.verifyConfirmationMsg();
         
  }

	
	
	@Test(enabled = true, priority = 7)

	public void executeQZ_8372()
			throws InterruptedException {

		String name = "Kevin Calib";
		String phoneNumber = "6452144488";
		String emailAddress = "EITQA@estes-express.com";
		String origzip = "16159";
		String destzip = "30307";
		String pieces = "2";
		String weight = "7500";

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Create a new Quote
		rateQuotePage.enterContactName(name);
		rateQuotePage.enterYourEmail(emailAddress);
		rateQuotePage.enterPhoneNo(phoneNumber);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		//rateQuotePage.selectTodayDate();  //--Changed to below line as its selecting plus 2 day
		rateQuotePage.setTodayDate();  //newly added
		rateQuotePage.enterOriginZip(origzip);
		rateQuotePage.enterDesZip(destzip);
		// enter commodity values
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces(pieces);
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight(weight);
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Smoke test package");
		// click on submit button
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		// click on get quote button
		 rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		// verify rate quote number prefix is V
		 rateQuotePage.verifyQuoteNumberPrefixIs("V");
		// click on request pickup
		rateQuotePage.clikOnRequestPickup();
		testUtil.setHardWait(2000);
		myEstesPickupRequestPage.verifyPageTitle();
		// verify fields are autopopulated with details from the Quote created
		myEstesPickupRequestPage.verifyName(name);
		myEstesPickupRequestPage.verifyPhoneNumberFromContactInformation(phoneNumber);
		myEstesPickupRequestPage.verifyEmailAddressFromContactInformation(emailAddress);
		
		myEstesPickupRequestPage.verifyZipFromContactInformation(origzip);
		myEstesPickupRequestPage.verifyDestZipFromContactInformation(destzip);
		myEstesPickupRequestPage.verifyPiecesFromContactInformation(pieces);
		myEstesPickupRequestPage.verifyWeightFromContactInformation(weight);
		// select shipper from role dropdown
		myEstesPickupRequestPage.selectShipperRole();
		// click on submit
		myEstesPickupRequestPage.clickOnSubmit();
		
		// verify the sections are populated based on data entry
		myEstesPickupRequestPage.verifyRequesterContactName();
		myEstesPickupRequestPage.verifyRequesterPhoneNumber();
		myEstesPickupRequestPage.verifyRequesterEmailAddress();
		myEstesPickupRequestPage.verifyShipperContactName();
		myEstesPickupRequestPage.verifyShipperPhoneNumber();
		myEstesPickupRequestPage.verifyShipperEmailAddress();
		myEstesPickupRequestPage.verifyShipperCompanyName();
		myEstesPickupRequestPage.verifyShipperAddress();
		myEstesPickupRequestPage.verifyShipperApptDate();
		myEstesPickupRequestPage.verifyShipperApptStartTime();
		myEstesPickupRequestPage.verifyShipperApptEndTime();
		myEstesPickupRequestPage.verifyShipperRequestNumber();
		myEstesPickupRequestPage.verifyShipperPostalCode();

	}
	
	//IMPORTANT NOTE!-QZ-10146 per the test case description it should be executed after 3PM Origin Terminal local time.  
	/*
	 * Rate Quote - Verify 3PM disclaimer is displayed in the Pickup app for Pickup
	 * request created after 3PM local time from Rate Quote app
	 */
	
	@Test(enabled = false, priority = 8)

	public void executeQZ_10146()
			throws InterruptedException {

		String name = "Kevin Calib";
		String phoneNumber = "6452144488";
		String emailAddress = "EITQA@estes-express.com";
		String origzip = "16159";
		String destzip = "30307";
		String pieces = "2";
		String weight = "7500";
		
		String expMsg = "You have requested same-day pickup service and it is currently after 3:00 p.m. local time at your pickup location. Please call your local terminal to confirm availability, otherwise we may need to pick up your shipment on the following business day.";

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		//From the *Ship* menu select *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		
		//In the *Select Quote Type* section, select  *Volume and Truckload (incl. Guaranteed)*; deselect *Less Than Truckload* 
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		//In the *Requester Information* section, enter or select the following values
		rateQuotePage.enterContactName(name);
		rateQuotePage.enterYourEmail(emailAddress);
		rateQuotePage.enterPhoneNo(phoneNumber);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		
		//In the Pickup Details section, enter or select the following values
		rateQuotePage.selectTodayDate();
		
		//In the *Routing Information* section, enter or select the following values
		rateQuotePage.enterOriginZip(origzip);
		rateQuotePage.enterDesZip(destzip);
		
		// enter commodity values
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces(pieces);
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight(weight);
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Smoke test package");
		
		// click on submit button
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		
		//*Rate Quote* step 2 page displays
		rateQuotePage.verifyRateQuoteResultPage();
		
		// click on get quote button
		 rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		 
		// verify rate quote number prefix is V
		 rateQuotePage.verifyQuoteNumberPrefixIs("V");
		 
		// click on request pickup
		rateQuotePage.clikOnRequestPickup();
		testUtil.setHardWait(2000);
		
		// Validate user navigates to Pickup Request page 
		myEstesPickupRequestPage.verifyPageTitle();
		
		// verify fields are autopopulated with details from the Quote created
		myEstesPickupRequestPage.verifyName(name);
		myEstesPickupRequestPage.verifyPhoneNumberFromContactInformation(phoneNumber);
		myEstesPickupRequestPage.verifyEmailAddressFromContactInformation(emailAddress);
		
		myEstesPickupRequestPage.verifyZipFromContactInformation(origzip);
		
		myEstesPickupRequestPage.verifyDestZipFromContactInformation(destzip);
		
		myEstesPickupRequestPage.verifyPiecesFromContactInformation(pieces);
		myEstesPickupRequestPage.verifyWeightFromContactInformation(weight);
		testUtil.setHardWait(3000);
		//Verify Message
		
		myEstesPickupRequestPage.verifyMessageDisplayed(expMsg);
		
		//Enter required fields and select shipper from role dropdown
		myEstesPickupRequestPage.selectShipperRole();

        testUtil.setHardWait(2000);
        
        //Select available by 9:15 AM, Select Close by 6:30 PM
        myEstesPickupRequestPage.selectAvailableBy("9", "15", "AM");
        testUtil.setHardWait(2000);
        myEstesPickupRequestPage.selectClosesBy("6", "30", "PM");
 
		// click on submit
		myEstesPickupRequestPage.clickOnSubmit();
		
		myEstesPickupRequestPage.verifyPageTitle();
		
		// verify the sections are populated based on data entry
		
		//*Requester Information:*
		myEstesPickupRequestPage.verifyRequesterContactName();
		myEstesPickupRequestPage.verifyRequesterPhoneNumber();
		myEstesPickupRequestPage.verifyRequesterEmailAddress();
  
		//*Shipper Info:*
		myEstesPickupRequestPage.verifyShipperContactName();
		myEstesPickupRequestPage.verifyShipperCompanyName();
		myEstesPickupRequestPage.verifyShipperAddress();
		myEstesPickupRequestPage.verifyShipperPhoneNumber();
		myEstesPickupRequestPage.verifyShipperEmailAddress();
		myEstesPickupRequestPage.verifyShipperApptDate();
		myEstesPickupRequestPage.verifyShipperApptStartTime();
		myEstesPickupRequestPage.verifyShipperApptEndTime();

		//*Shipment Information:*
		myEstesPickupRequestPage.verifyShipperRequestNumber();
		myEstesPickupRequestPage.verifyShipperPostalCode();
		myEstesPickupRequestPage.verifyPieces();
		myEstesPickupRequestPage.verifyWeight();
		myEstesPickupRequestPage.verifySkids();
		myEstesPickupRequestPage.verifyHazmat();
		myEstesPickupRequestPage.verifyYourRefNumber();
		myEstesPickupRequestPage.verifyDetails();

	}


	/*IMPORTANT NOTE! : Per manual test case this test should be executed after 3PM Origin Terminal local time. Hence this test has to be run locally*/
	
	/*
	 * Pickup Request - Verify 3PM disclaimer is displayed for Pickup created after 3PM local time
	 */
	
	@Test(enabled = false, priority = 9)

	public void executeQZ_10141() throws InterruptedException {

		String expMsg = "You have requested same-day pickup service and it is currently after 3:00 p.m. local time at your pickup location. Please call your local terminal to confirm availability, otherwise we may need to pick up your shipment on the following business day.";

		// After 3PM local time, Launch application
		myEstesHomePage.clickOnMyEstes();

		// Login to application
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate Shipment tab -> Pickup request
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnPickupRequest();
		testUtil.setHardWait(3000);
		// Fill out required information
		
		// Select Role as Shipper
		myEstesPickupRequestPage.selectShipperRole();
		
		// Enter Pickup Date
		String todayDate = testUtil.todaysDate();
		myEstesPickupRequestPage.enterPickupDate(todayDate);
		
        testUtil.setHardWait(2000);
      
        //Select available by 9:15 AM, Select Close by 6:30 PM
        myEstesPickupRequestPage.selectAvailableBy("9", "15", "AM");
        testUtil.setHardWait(2000);
        myEstesPickupRequestPage.selectClosesBy("6", "30", "PM");
 
        //Verify the 3 p.m. disclaimer is displayed 	
      	myEstesPickupRequestPage.verifyMessageDisplayed(expMsg);
      	
        //From the Shipper Info section, Enter the following values 
      	myEstesPickupRequestPage.enterShipperContactName("SMOKE TEST LOCAL");
      	myEstesPickupRequestPage.enterShipperPhoneExtension("12345");
      	
		//In the Shipment Information section, populate the required fields:
		myEstesPickupRequestPage.enterDestinationZip("23230");
		myEstesPickupRequestPage.enterTotalPiece("4");
		myEstesPickupRequestPage.enterTotalWeight("5000");
		myEstesPickupRequestPage.enterTotalSkids("1");
		
		//Enter the following value in your reference number field
		myEstesPickupRequestPage.enterRefNumber("PH123658");

		// Click on submit
		myEstesPickupRequestPage.clickOnSubmit();
		testUtil.setHardWait(4000);
 
		myEstesPickupRequestPage.verifyPageTitle();
		// verify the sections are populated based on data entry
		
		//*Requester Information:*
		myEstesPickupRequestPage.verifyRequesterContactName();
		myEstesPickupRequestPage.verifyRequesterPhoneNumber();
		myEstesPickupRequestPage.verifyRequesterEmailAddress();
  
		//*Shipper Info:*
		myEstesPickupRequestPage.verifyShipperContactName();
		myEstesPickupRequestPage.verifyShipperCompanyName();
		myEstesPickupRequestPage.verifyShipperAddress();
		myEstesPickupRequestPage.verifyShipperPhoneNumber();
		myEstesPickupRequestPage.verifyShipperEmailAddress();
		myEstesPickupRequestPage.verifyShipperApptDate();
		myEstesPickupRequestPage.verifyShipperApptStartTime();
		myEstesPickupRequestPage.verifyShipperApptEndTime();

		//*Shipment Information:*
		myEstesPickupRequestPage.verifyShipperRequestNumber();
		myEstesPickupRequestPage.verifyShipperPostalCode();
		myEstesPickupRequestPage.verifyPieces();
		myEstesPickupRequestPage.verifyWeight();
		myEstesPickupRequestPage.verifySkids();
		myEstesPickupRequestPage.verifyHazmat();
		myEstesPickupRequestPage.verifyYourRefNumber();
		myEstesPickupRequestPage.verifyDetails();
		

		//Click on the Request Another Pickup link
		myEstesPickupRequestPage.clickOnRequestAnotherPickup();
		
		//User is navigated back to the pickup request form
		myEstesPickupRequestPage.verifyPageTitle();
		
	}
	
	
	

	/**
	 * Pickup Visibility - Auth - Verify that pro number is found in Tracking
	 * Numbers section of Pickup Visibility when pro is reconciled to pickup request
	 * on iSeries (FB Update)
	 * - Saunders Cox
	 * @throws Exception 
	 */
	@Test(enabled=true,priority=10)
	public void executeQZ_11820() throws Exception {
		// Step 1 - 12: Set up a freight bill and record the PRO number
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		LoginScreen login = new LoginScreen(session);
		login.startSessionAndLoginToMasterMenu(username16, password16);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		String proNumber = ltl38MasterMenuScreen.reserveFreightBillFromMasterMenu("TEST", "135", "3500962");
		
		// Step 13 - 22: Add pickup request, record pickup number, and finalize pickup
		ltl38MasterMenuScreen.enterValueOptionField("1");
		FreightBillingMenuScreen freightBillingMenu = new FreightBillingMenuScreen(session);
		freightBillingMenu.enterFreightBillMenuOption("89", "TEST", "135");
		SecondaryFreightBillingMenuScreen secondaryFreightBillingMenu = new SecondaryFreightBillingMenuScreen(session);
		secondaryFreightBillingMenu.enterValueOptionField("70");
		secondaryFreightBillingMenu.pressEnterKey(); 
		PickupSystemMenuScreen pickupSystemMenu = new PickupSystemMenuScreen(session);
		pickupSystemMenu.enterValueOptionField("2");
		pickupSystemMenu.pressEnterKey(); 
		PickupSystemRequestQueueScreen pickupSystemRequestQueue = new PickupSystemRequestQueueScreen(session);
		pickupSystemRequestQueue.pressF6Key();
		PickupRequestMaintenanceScreen pickupRequestMaintenance = new PickupRequestMaintenanceScreen(session);
		pickupRequestMaintenance.enterShipperNumber("3500962");
		pickupRequestMaintenance.enterAction("LL");
		pickupRequestMaintenance.enterPieces("5");
		pickupRequestMaintenance.enterWeight("2500");
		pickupRequestMaintenance.enterRqstBy("S");
		pickupRequestMaintenance.pressEnter();
		String pickupNumber = pickupRequestMaintenance.recordPickupNumber();
		pickupRequestMaintenance.pressF11();

		// Step 23 - 26: Accept the pickup request from the queue
		pickupRequestMaintenance.pressF12();
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.enterFirstLineOption("9");
		session.waitForChange(30000);
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyPickupStatusCode("O");

		// Step 27 - 44: Update the freight bill
		pickupSystemRequestQueue.pressF3();
		pickupSystemMenu.enterValueOptionField("90");
		pickupSystemMenu.pressEnterKey(); 
		secondaryFreightBillingMenu.pressF1();
		freightBillingMenu.enterFreightBillMenuOption("2", "TEST", "135");
		String ts = "1"; 
		String pcs = "5"; 
		String terms = "PPD"; 
		String wgt = "2500"; 
		String consigneeCode = "4913037"; 
		String puDr1="135132";     
		String puDr2 = "1"; 
		String cubicFeet = "50"; 
		
		freightBillingMenu.enterFreightBill(proNumber.substring(0, 3), proNumber.substring(3));
		PickupSystemReconciliationsBySID pickupSystemReconciliations = new PickupSystemReconciliationsBySID(session);
		if (pickupSystemReconciliations.isCurrentScreen()) {
			pickupSystemReconciliations.selectPickupNumber(pickupNumber);
		}
		UpdateScreen updateScreen = new UpdateScreen(session);
		updateScreen.enterTS(ts);
		updateScreen.enterPcs(pcs);
		updateScreen.enterTerms(terms);
		updateScreen.enterWgt(wgt);
		updateScreen.enterConsigneeCode(consigneeCode);
		updateScreen.enterPONum("PO" + proNumber);
		updateScreen.enterPuDrNum1(puDr1, puDr2);
		updateScreen.enterCubicFeet(cubicFeet);
		updateScreen.enterMasterBlNo("BOL" + proNumber);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		/*
		 *  Pcs - 5
		 *  Pk - SK
		 *  Description - SKIDS
		 *  Wgt - 2500
		 */
		String desc = "SKIDS"; 
		UpdateScreen2 updateScreen2 = new UpdateScreen2(session);
		updateScreen2.enterPcs2(pcs);
		updateScreen2.enterPK("SK");
		updateScreen2.enterDesc(desc);
		updateScreen2.enterWgt2(wgt); 
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		freightBillingMenu.verifyFreightBillUpdated(proNumber);
		session.writeKey(Key.PF1);
		session.waitForChange(1000);
		session.writeKey(Key.PF1);
		session.waitForChange(1000);
		
		session.close();
		
		// Step 45: Validate details on MyEstes Pickup Visibility
		driver.get("https://estes-express-qa.estesinternal.com/myestes/tracking/pickups");
		MyEstesPickupVisibility myEstespickupVisibility = new MyEstesPickupVisibility();
		myEstespickupVisibility.login(username1, password1);
		myEstespickupVisibility.clickPickupVisibility();
		testUtil.setHardWait(2000);
		myEstespickupVisibility.enterPickupRequestNumbers(pickupNumber);
		myEstespickupVisibility.clickSearch();
		myEstespickupVisibility.clickFirstTrackingResultsDropdown();
		myEstespickupVisibility.verifyProNumberTrackingResults2(proNumber);
		myEstespickupVisibility.verifyPiecesTrackingResults2("5");
		myEstespickupVisibility.verifyWeightTrackingResults2("2500");
		myEstespickupVisibility.verifyConsigneeLocationTrackingResults2("Memphis", "TN", "38118");
	}

	
	

	
	/**
	 * Pickup Visibility - Unauth - Verify that pro number is found in Tracking
	 * Numbers section of Pickup Visibility when pro is reconciled to pickup request
	 * on iSeries (FB Update)
	 * @throws Exception 
	 * 
	 * This test passed on 8/12/2022
	 */
	
	@Test(enabled=true,priority=11)
	public void executeQZ_11817() throws Exception {
		// Step 1 - 12: Set up a freight bill and record the PRO number
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		TempletTests templateTest = new TempletTests();
		
		LoginScreen login = new LoginScreen(session);
		login.enterUserNPasswordCDOC(username16, password16);
		login.pressEnterKey(); 
		session.waitForChange(1000);
		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");
		session.waitForChange(1000);
		Ltl38MasterMenuScreen masterMenu = new Ltl38MasterMenuScreen(session);
		masterMenu.verifyScreenTitle();
		String proNumber = masterMenu.reserveFreightBillFromMasterMenu("TEST", "006", "0642291");
		
		// Step 13 - 22: Add pickup request, record pickup number, and finalize pickup
		masterMenu.enterValueOptionField("1");
		FreightBillingMenuScreen freightBillingMenu = new FreightBillingMenuScreen(session);
		freightBillingMenu.enterFreightBillMenuOption("89", "TEST", "006");
		SecondaryFreightBillingMenuScreen secondaryFreightBillingMenu = new SecondaryFreightBillingMenuScreen(session);
		secondaryFreightBillingMenu.enterValueOptionField("70");
		secondaryFreightBillingMenu.pressEnterKey(); 
		PickupSystemMenuScreen pickupSystemMenu = new PickupSystemMenuScreen(session);
		pickupSystemMenu.enterValueOptionField("2");
		pickupSystemMenu.pressEnterKey(); 
		PickupSystemRequestQueueScreen pickupSystemRequestQueue = new PickupSystemRequestQueueScreen(session);
		pickupSystemRequestQueue.pressF6Key();
		PickupRequestMaintenanceScreen maintenance = new PickupRequestMaintenanceScreen(session);
		maintenance.enterShipperNumber("0642291");
		maintenance.enterAction("LL");
		maintenance.enterPieces("5");
		maintenance.enterWeight("2500");
		maintenance.enterRqstBy("S");
		maintenance.pressEnter();
		String pickupNumber = maintenance.recordPickupNumber();
		maintenance.pressF11();

		// Step 23 - 26: Accept the pickup request from the queue
		maintenance.pressF12();
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.enterFirstLineOption("9");
		session.waitForChange(30000);
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyPickupStatusCode("O");
		
		// Step 27 - 44: Update the freight bill
		pickupSystemRequestQueue.pressF3();
		pickupSystemMenu.enterValueOptionField("90");
		pickupSystemMenu.pressEnterKey(); 
		secondaryFreightBillingMenu.pressF1();
		freightBillingMenu.pressF1();
		// weight different here
		masterMenu.createFreightBillFromMasterMenu("TEST", proNumber, "1", "5", "PPD", "2500", "4913037", "6026", "1",
				"50", "SKIDS", pickupNumber);
		session.close();

		// Step 45:
		driver.get("https://estes-express-qa.estesinternal.com/myestes/tracking/pickups");
		MyEstesPickupVisibility pickupVisibility = new MyEstesPickupVisibility();
		pickupVisibility.login(username1, password1);
		pickupVisibility.clickPickupVisibility();
		testUtil.setHardWait(2000);
		pickupVisibility.enterPickupRequestNumbers(pickupNumber);
		pickupVisibility.clickSearch();
		pickupVisibility.clickFirstTrackingResultsDropdown();
		pickupVisibility.verifyProNumberTrackingResults2(proNumber);
		pickupVisibility.verifyPiecesTrackingResults2("5");
		pickupVisibility.verifyWeightTrackingResults2("2500");
		pickupVisibility.verifyConsigneeLocationTrackingResults2("BRIDGEWATER", "NJ", "08807");
	}
	

	
	/**
	 * @author lemmoju
	 * 
	 * Pickup Visibility - Verify in Pickup Visibility app that the correct milestones are set in Milestone Tracking Bar when pickup request goes from status O to status C (Completed)
	 * @throws Exception 
	 * 
	 * This test passed on 8/19/2022 - without change
	 */
	
	@Test(enabled = true, priority = 12)

	public void executeQZ_11977() throws Exception {
		
		
		
		// Step 1-8: Login to the following application: EXLAQA
		
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		LoginScreen login = new LoginScreen(session);
		login.startSessionAndLoginToMasterMenu(username16, password16); 
		Ltl38MasterMenuScreen masterMenu = new Ltl38MasterMenuScreen(session);
		
		/* Step 9: From the Reserve a String of Freight Bills screen, enter the following values
		 * Bills To Reserve: 1
		 * Shipper Code: B006256
		 * Step 10-12: Reserve Pro# that is reserved, F3 to exit the screen.
		 */
		String proNumber = masterMenu.reserveFreightBillFromMasterMenu("TEST", "006", "6774346");
		
		// Step 13 - 22: Add pickup request, record pickup number, and finalize pickup
		masterMenu.enterValueOptionField("1");
		FreightBillingMenuScreen freightBillingMenu = new FreightBillingMenuScreen(session);
		freightBillingMenu.enterFreightBillMenuOption("89", "TEST", "006");
		SecondaryFreightBillingMenuScreen secondaryFreightBillingMenu = new SecondaryFreightBillingMenuScreen(session);
		secondaryFreightBillingMenu.enterValueOptionField("70");
		secondaryFreightBillingMenu.pressEnterKey(); 
		PickupSystemMenuScreen pickupSystemMenu = new PickupSystemMenuScreen(session);
		pickupSystemMenu.enterValueOptionField("2");
		pickupSystemMenu.pressEnterKey(); 
		PickupSystemRequestQueueScreen pickupSystemRequestQueue = new PickupSystemRequestQueueScreen(session);
		pickupSystemRequestQueue.pressF6Key();
		PickupRequestMaintenanceScreen maintenance = new PickupRequestMaintenanceScreen(session);
		maintenance.enterShipperNumber("6774346");
		maintenance.enterAction("LL");
		maintenance.enterPieces("6");
		maintenance.enterWeight("3000");
		maintenance.enterRqstBy("S");
		maintenance.pressEnter();
		String pickupNumber = maintenance.recordPickupNumber();
		maintenance.pressF11();
		
		// Step 23 - 26 Update Pickup System
		maintenance.pressF12();
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.enterFirstLineOption("9");
		session.waitForChange(30000);
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyPickupStatusCode("O");
		
		// Step 27 - 30: Update the freight bill
		pickupSystemRequestQueue.pressF3();
		pickupSystemMenu.enterValueOptionField("90");
		pickupSystemMenu.pressEnterKey(); 
		secondaryFreightBillingMenu.pressF1();
		freightBillingMenu.pressF1();

		// Step 31: Open the following link in Chrome: https://estes-express-uat.estesinternal.com/myestes/tracking/pickups
		driver.get("https://estes-express-uat.estesinternal.com/myestes/tracking/pickups");
		MyEstesPickupVisibility pickupVisibility = new MyEstesPickupVisibility();
		testUtil.setHardWait(5000);
		pickupVisibility.enterPickupRequestNumbers(pickupNumber);
		pickupVisibility.clickSearch();
		pickupVisibility.clickDropDown();
		pickupVisibility.validateResultsFromTrackingBar("Pickup Accepted");
		
		/*
		 * Step 35-39
		 * 
		 * TS - 1
		 * Pcs - 6
		 * Terms - PPD
		 * Wgt - 3000
		 * Master BL - BOL<OTPRO>
		 * Cons - 4913037
		 * PO - PO<OTPRO>
		 * PUDr - 6026    1
		 * Cubic Feet - 50
		 * 
		 * Update Screen 2
		 *  Pcs - 6
		 *  Pk - SK
		 *  Description - SKIDS
		 *  Wgt - 3000
		 */
		masterMenu.enterValueOptionField("1");
		freightBillingMenu.enterFreightBillMenuOption("2", "TEST", "006");
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session); 
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(proNumber); 
		freightBillInquiryEnterFieldValuesScreen.pressEnterKey();
		JagacyUtil jagacyUtil = new JagacyUtil(session); 
		jagacyUtil.verifyReconcilationBySID1(session, pickupNumber); 
		UpdateScreen updateScreen = new UpdateScreen(session);
		updateScreen.enterValuesToUpdateScreen("1", "6", "PPD", "3000", "4913037", "", "", "PO"+proNumber, "6026","1", "50"); 
		updateScreen.enterMasterBlNo("BOL" + proNumber);
		updateScreen.pressEnter();
		updateScreen.pressEnter();
		UpdateScreen2 updateScreen2 = new UpdateScreen2(session); 
		updateScreen2.enterValuesToUpdateScreen2("", "6", "SK", "SKIDS", "3000");
		updateScreen2.pressEnterKey(); 
		updateScreen2.pressEnterKey(); 
		
		//Step 45 - 46: On Third Page for the Update Screen, make no additional entries. Press Enter
		UpdateScreen3 updateScreen3 = new UpdateScreen3(session); 
		updateScreen3.pressEnterKey(); 
		
		//Step 47: Press F1 to exit. 
		session.writeKey(Key.PF1);
		
		if (session != null) {
			session.close();
			session.abort();
		}
		
		/*
		 * Step 48: From Pickup Visibility screen, do the following: Click on the Search Button
		 * Step 49: Expand on the details for the pickup requests being searched. 
		 * Verify that the following milestones are shown in the Milestone Tracking Bar:
		 * Pickup Accepted - green with check mark
		 * Driver En Route - gray
		 * Driver Accepted - gray
		 * Departed Pickup Location - gray
		 * Pickup Completed - green with check mark
		 */
		
		pickupVisibility.clearResults(); 
		pickupVisibility.enterPickupRequestNumbers(pickupNumber);
		pickupVisibility.clickSearch();
		pickupVisibility.clickDropDown(); 
		pickupVisibility.validateResultsFromTrackingBar("Pickup Accepted", "Pickup Completed");
		
	}
	
	/*
	 * @author lemmoju
	 * Pickup Visibility - Verify in Pickup Visibility app that the correct milestones are set in Milestone Tracking Bar when pickup request goes from status O to status R (Rejected)
	 */
	@Test(enabled = true, priority = 13)

	public void executeQZ_11975() throws Exception {
		// Step 1: Login to the following application: EXLAQA
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		LoginScreen login = new LoginScreen(session);
		/*
		 * Step 2: Press ENTER
		 * Step 3: From the IBM i Main Menu, enter the following values: Call XXC870
		 * Step 4: Press ENTER
		 */
		login.startSessionAndLoginToMasterMenu(username16, password16);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		//Step 5: From the LTL/38 Master Menu, enter the following: Option: 1
		//Step 6: Press Enter
		ltl38MasterMenuScreen.enterValueOptionField("1");
		/*
		 * Step 7: From the Freight Billing Menu, enter the following:
           Option: 89
           User: TEST
           Terminal: 006
           Step 8: Press ENTER
		 */
		FreightBillingMenuScreen freightBillingMenu = new FreightBillingMenuScreen(session);
		freightBillingMenu.enterFreightBillMenuOption("89", "TEST", "006");
		testUtil.setHardWait(3000); 
		/*
		 * Step 9: From the Secondary Freight Billing Menu, enter the following:
           Option: 70
         * Step 10: Press Enter
		 */
		SecondaryFreightBillingMenuScreen secondaryFreightBillingMenu = new SecondaryFreightBillingMenuScreen(session);
		secondaryFreightBillingMenu.enterValueOptionField("70");
		secondaryFreightBillingMenu.pressEnterKey(); 
		/*
		 * Step 11: From the Pickup System Menu, enter the following:
           Option: 2
		 */
		PickupSystemMenuScreen pickupSystemMenu = new PickupSystemMenuScreen(session);
		pickupSystemMenu.enterValueOptionField("2");
		pickupSystemMenu.pressEnterKey(); 
		PickupSystemRequestQueueScreen pickupSystemRequestQueue = new PickupSystemRequestQueueScreen(session);
		//Step 12: From the Pickup System - Request Queue screen, do the following: Press F6
		pickupSystemRequestQueue.pressF6Key();
		/*
		 * Step 13: From the Pickup Request Maintenance, enter the following:
		 * Shipper - 0672288
		 * Action - LL
		 * Pieces - 5
		 * Weight - 1000
		 * Rqst by - S
		 * Step 14: Press Enter
		 */
		String accountNumber = "0672288"; 
		PickupRequestMaintenanceScreen pickupRequestMaintenance = new PickupRequestMaintenanceScreen(session);
		pickupRequestMaintenance.enterShipperNumber(accountNumber);
		pickupRequestMaintenance.enterAction("LL");
		pickupRequestMaintenance.enterPieces("5");
		pickupRequestMaintenance.enterWeight("1000");
		pickupRequestMaintenance.enterRqstBy("S");
		pickupRequestMaintenance.pressEnter();
		//Step 15: Record the PU number generated
		testUtil.setHardWait(1000); 
		String pickupNumber = pickupRequestMaintenance.recordPickupNumber();
		//Recording stop number
		testUtil.setHardWait(1000); 
		String stopNumber = pickupRequestMaintenance.recordStopNumber(); 
		//Step 16: Press F11 to finalize PU
		pickupRequestMaintenance.pressF11();
		//Step 17: Press F12
		pickupRequestMaintenance.pressF12();
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		/*
		 * Step 18: From the Pickup System - Request Queue screen, do the following:
		 * Find your pickup request in the view
		 * Once found, enter option 9 next to your request
		 * Step 19: Press Enter
		 */
		pickupSystemRequestQueue.enterFirstLineOption("9");
		session.waitForChange(30000);
		/*
		 * Step 20: From the Pickup System - Request Queue screen, do the following:
		 * After waiting about 2 minutes, press F5 to refresh the view
		 * Verify that status of your pickup request is now 'O'
		 */
		pickupSystemRequestQueue.pressF5Key(); 
		pickupSystemRequestQueue.enterRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyFirstLineRequestNumber(pickupNumber);
		pickupSystemRequestQueue.verifyPickupStatusCode("O");
		
		//Step 21: Press F3
		pickupSystemRequestQueue.pressF3();
		
		/*
		 * Step 22: Open the following link in Chrome:
		 * https://estes-express-uat.estesinternal.com/myestes/tracking/pickups
		 * Step 23: From the Pickup Visibility screen, do the following: Enter your pickup numbers in the search box
		 */
		driver.get(url6); 
		MyEstesPickupVisibility myEstespickupVisibility = new MyEstesPickupVisibility();
		myEstespickupVisibility.enterPickupRequestNumbers(pickupNumber);
		
		//Step 24: Click on the Search button
		myEstespickupVisibility.clickSearch();
		
		/*
		 * Step 25: Expand on the details for the pickup requests being searched
		 * Verify that the following milestones are shown in the Milestone Tracking Bar:
		 * Pickup Accepted - green with check mark
		 * Driver En Route - gray
		 * Driver Accepted - gray
		 * Departed Pickup Location - gray
		 * Pickup Completed - gray
		 */
		myEstespickupVisibility.clickDropDown(); 
		myEstespickupVisibility.validateResultsFromTrackingBar("Pickup Accepted"); 
		
		/*
		 * Step 26: From the Pickup System Menu, enter the following:
		 * Option: 1
		 * Step 27: Press Enter
		 */
		PickupSystemMenuScreen pickupSystemMenuScreen = new PickupSystemMenuScreen(session);
		pickupSystemMenuScreen.enterValueOptionField("1");
		pickupSystemMenuScreen.pressEnterKey(); 
		
		/*
		 * Step 28: From the Pickup System - Stop Processing screen, do the following: 
		 * Select option 3 next to your shipper account and stop number
		 * Step 29: Press Enter
		 */
		PickupSystemRequestQueueScreen pickupSystemRequestQueueScreen = new PickupSystemRequestQueueScreen(session);
		pickupSystemRequestQueueScreen.enterStopNumber(stopNumber); 
		pickupSystemRequestQueueScreen.pressEnterKey(); 
		Integer specificLine = pickupSystemRequestQueueScreen.validateStopDate(accountNumber); 
		pickupSystemRequestQueueScreen.enterSpecificLineOption("3", specificLine); 
		
		/*
		 * Step 30: From the Pickup Request Processing screen, do the following:
		 * Select option R next to your pickup request number
		 * Step 31: Press Enter
		 */
		PickupRequestProcessing pickupRequestProcessing = new PickupRequestProcessing(session); 
		pickupRequestProcessing.validatePickupRequestNumber(pickupNumber, "R"); 
		
		/*
		 * Step 32: From the Reject Reason Code window, enter the following:
		 * Rejection Reason - CC
		 * Text - Customer cancelled due to inability to pay
		 * Step 33: Press Enter
		 */
		pickupRequestProcessing.rejectionReasonCode("CC", "Customer cancelled due to inability to pay"); 
		pickupRequestProcessing.pressEnterKey(); 
		String date = testUtil.todaysDate(); 
		String cancellationTime = testUtil.getCurrentHourMinuteTime(); 
		testUtil.setHardWait(1500); 
		pickupRequestProcessing.validateRejectionMessage(pickupNumber); 
		
		/*
		 * Step 34: Press F3
		 * Step 35: Press F3
		 */
		pickupRequestProcessing.pressF3(); 
		pickupSystemRequestQueueScreen.pressF3(); 
		
		/*
		 * Step 36: From the Pickup System Menu, enter the following: 
		 * 	Option: 90
		 * Step 37: Press Enter
		 */
		pickupSystemMenuScreen.enterValueOptionField("90"); 
		pickupSystemMenuScreen.pressEnterKey(); 
		
		//Step 38: From the Secondary Freight Billing Menu, do the following: Press F1
		secondaryFreightBillingMenu.pressF1(); 
		
		/*
		 * Step 39: From Pickup Visibility screen, do the following:  Click on the Search button
		 * Step 40: Expand on the details for the pickup requests being searched
		 * Verify that the following milestones are shown in the Milestone Tracking Bar:
		 * Pickup Accepted - green with check mark
		 * Driver En Route - gray
		 * Driver Accepted - gray
		 * Departed Pickup Location - gray
		 * Pickup Completed - yellow with exclamation point
		 */
		myEstespickupVisibility.clickClearButton(); 
		myEstespickupVisibility.enterPickupRequestNumbers(pickupNumber);
		myEstespickupVisibility.clickSearch();
		myEstespickupVisibility.clickDropDown(); 
		myEstespickupVisibility.validateResultsFromTrackingBar("Pickup Accepted"); 
		testUtil.setHardWait(2500); 
		myEstespickupVisibility.validateExclamationPointIsVisible(); 
		myEstespickupVisibility.validateCancelledMessage(date, cancellationTime); 
	}
	
}

