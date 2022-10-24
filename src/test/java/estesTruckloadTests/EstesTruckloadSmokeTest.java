package estesTruckloadTests;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetQuoteAuditTrailPage;
import eNetPages.ENetTruckloadRateQuotePage;
import eNetPages.ENetVTLQouteExceptionQueuePage;
import eNetPages.ENetVTLRateQuotePage;
import eNetPages.VolumeAndTruckloadQuoteMaintenance;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.BookTruckloadShipmentPage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;

public class EstesTruckloadSmokeTest extends TestBase {

	Logger logger = Logger.getLogger(EstesTruckloadSmokeTest.class);

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetVTLQouteExceptionQueuePage eNetVTLQouteExceptionQueuePage = new ENetVTLQouteExceptionQueuePage();
	VolumeAndTruckloadQuoteMaintenance volumeAndTruckloadQuoteMaintenance = new VolumeAndTruckloadQuoteMaintenance();
	ENetVTLRateQuotePage enetVTLRateQuotePage = new ENetVTLRateQuotePage();
	BookTruckloadShipmentPage bookTruckloadShipmentPage = new BookTruckloadShipmentPage();
	ENetVTLRateQuotePage eNetVTLRateQuotePage = new ENetVTLRateQuotePage();
	ENetQuoteAuditTrailPage eNetQuoteAuditTrailPage = new ENetQuoteAuditTrailPage();
	ENetTruckloadRateQuotePage eNetTruckloadRateQuotePage = new ENetTruckloadRateQuotePage();

	/******************************* TESTS *******************************/

	/*
	 * Verify that when MyEstes user book an Estes Truckload quote with Dry Van
	 * equipment type then a success message is displayed the order is successfully
	 * created on CHR side and a PRO is reserved
	 * 
	 * This test got passed on 8/30/2022 - Without any change
	 */

	@Test(enabled = true, priority = 1)
	public void executeQZ_10237()
			throws Exception {

		String AccNum = "B000718";

		String ContactName = "Contact First Last Name";
		String CompanyName = "Truckload Brokerage ";
		String EmailAddress = "QATEST@Estes-Express.com";
		String PhoneNumber = "8043531900";
		String PhoneExt = "1234567";
		String OriginContactName = "Origin First Last Name";
		String OriginEmailAddress = "QATESTOrigin@Estes-Express.com";
		String OriginPhoneNumber = "8043531900";
		String OriginPhoneExt = "1111";
		String AddressLine1 = "5451 Highway 42";
		String AddressLine2 = "Terminal 041";
		String SpecialInstruction = "TBP-697 Book a Truckload Brokerage quote with Dry Van equipment - Origin Special Instructions";
		String DestContactName = "Destination First Last Name";
		String DestEmailAddress = "QATESTDestination@Estes-Express.com";
		String DestPhoneNumber = "8041234567";
		String DestPhoneExt = "7799";
		String DestAddressLine1 = "3914 East Shelby Drive";
		String DestAddressLine2 = "Terminal 037";
		String DestSpecialInstruction = "TBP-697 Book a Truckload Brokerage quote with Dry Van equipment - Destination Special Instructions";

		String Quantity = "75";
		String FreightDescription = "Truckload Order with Dry Van Type. TBP-697";
		String FreightValue = "75000";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

		// Step 5: In the *Requester Information* section, Select an Account from the
		// *Account Search* popup window or enter *B000718*
		rateQuotePage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumberInAccSearch(AccNum);
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnFirstAccountNumber();

		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(2000);
		rateQuotePage.enterOriginZip("30307");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("37602");

		// Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.enterWeight("22321");

		// Step 8: Click on submit request
		rateQuotePage.clickOnSubmitButton();

		// Step 9: Verify estes truckload charges are calculated
		rateQuotePage.verifyTuckloadBasicCharge("Truckload Brokerage", true);

		// Step 10 : Click *Book Shipment* button
		rateQuotePage.clickOnBookShipmentButton();

		rateQuotePage.verifyBookTruckloadShipmentPageDisplayed();

		testUtil.setHardWait(2000);
		// Step 11 : From the *Shipment Details* section record quote number for step-15
		String quoteNumber = rateQuotePage.recordQuoteNum();

		// Step 12 : In the *Requestor Information* section enter the following values:

		bookTruckloadShipmentPage.enterContactName(ContactName);
		bookTruckloadShipmentPage.enterCompanyName(CompanyName);
		bookTruckloadShipmentPage.enterYourEmail(EmailAddress);
		bookTruckloadShipmentPage.enterPhoneNo(PhoneNumber);
		bookTruckloadShipmentPage.enterPhoneExtentionNo(PhoneExt);

		// Step 13 : In the *Routing - Origin Information* section enter the following
		// values:

		bookTruckloadShipmentPage.enterOriginContactName(OriginContactName);
		bookTruckloadShipmentPage.enterOriginEmail(OriginEmailAddress);
		bookTruckloadShipmentPage.enterOriginPhoneNo(OriginPhoneNumber);
		bookTruckloadShipmentPage.enterOriginPhoneExtentionNo(OriginPhoneExt);
		bookTruckloadShipmentPage.enterOriginAddress1(AddressLine1);
		bookTruckloadShipmentPage.enterOriginAddress2(AddressLine2);

		bookTruckloadShipmentPage.clickOnOriginAppointmentCheckbox();
		bookTruckloadShipmentPage.selectPickupStartTime("9", "45", "AM");
		bookTruckloadShipmentPage.selectPickupEndTime("3", "00", "PM");
		bookTruckloadShipmentPage.enterOriginSpecialInstructions(SpecialInstruction);

		// Step 14 : In the *Routing - Destination Information* section enter the
		// following values:

		bookTruckloadShipmentPage.enterDestContactName(DestContactName);
		bookTruckloadShipmentPage.enterDestEmail(DestEmailAddress);
		bookTruckloadShipmentPage.enterDestPhoneNo(DestPhoneNumber);
		bookTruckloadShipmentPage.enterDestPhoneExtentionNo(DestPhoneExt);
		bookTruckloadShipmentPage.enterDestAddress1(DestAddressLine1);
		bookTruckloadShipmentPage.enterDestAddress2(DestAddressLine2);
		bookTruckloadShipmentPage.clickOnDestAppointmentCheckbox();
		bookTruckloadShipmentPage.selectDeliveryStartTime("1", "30", "PM");
		bookTruckloadShipmentPage.selectDeliveryEndTime("5", "15", "PM");
		bookTruckloadShipmentPage.enterDestSpecialInstructions(DestSpecialInstruction);

		// Step 15 : From the *Freight Information* section, enter or select the
		// following values:

		bookTruckloadShipmentPage.enterCommodityType("PALLET");
		bookTruckloadShipmentPage.enterQuantity(Quantity);
		bookTruckloadShipmentPage.enterFreightDesc(FreightDescription);
		bookTruckloadShipmentPage.enterFreightValue(FreightValue);
		bookTruckloadShipmentPage.enterReferenceNumber(quoteNumber);

		// Step 16 : Click the *Book Truckload Shipment* button
		bookTruckloadShipmentPage.clickOnBookTruckloadShipmentButton();
		testUtil.setHardWait(3000);
		// Verify Success message
		bookTruckloadShipmentPage.verifySuccessMessageIsDisplayed();

		// Step 17 : From the *Booking Confirmation* page, note the PRO Number
		String proNum = bookTruckloadShipmentPage.recordPRONum();
		String terminalNo = "020";

		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";
		String terminal = "dec-vt220";

		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// Step-18 : Logon to the *EXLAQA* server, using your credentials:
		loginScreen.logon(userName, password);
		Thread.sleep(2000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();

		// Step-19 : From the command line, enter: CALL XXC870
		ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Step-20 : From the *LTL Master Menu*, select: "1"
		ltl38MasterMenuScreen.enterValueOptionField("1");

		// Step-21 : From the *Freight Billing Menu*, enter the following:

		freightBillingMenuScreen.enterValueOptionField("1");

		freightBillingMenuScreen.enterValueUserField("Truckload");
		freightBillingMenuScreen.enterTabKey();

		freightBillingMenuScreen.enterTerminalNumber("002");

		// Step-22 : Key in the PRO Number noted in step 17
		freightBillingMenuScreen.enterFreightBill(terminalNo, proNum);

		// Verify the PRO is in R/Reserve Status
		freightBillingMenuScreen.verifyPROIsInRStatus();

		// Step-23 : To log out, press/enter
		// F1 (to exit FB inquiry)
		// 90 (to exit FB menu)
		// 90 (to exit and logout)

		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField("90");
		ltl38MasterMenuScreen.enterValueOptionField("90");

		if (session != null) {
			session.abort();
			session.close();
		}

	}

	
	// THIS IS INCLUDED IN THE LOCAL DAILY RUNS
		/*
		 * IMPORTANT NOTE from manual testcase: This test case cannot be executed on a
		 * Friday since pickup is not allowed on the weekend and the next pickup date is
		 * Monday (if not a holiday) and this is more than a day.
		 */

		/*
		 * Verify when the Estes Truckload quote selected pickup time is tomorrow and
		 * the user selects an appointment range with start time less than 20 hours lead
		 * time (based on current time) then an error message is displayed and booking
		 * cannot be completed
		 */

		@Test(enabled = false, priority = 2)
		public void executeQZ_10260()
				throws Exception {

			String AccNum = "B000718";

			String ContactName = "Contact First Last Name";
			String CompanyName = "Truckload Brokerage ";
			String EmailAddress = "QATEST@Estes-Express.com";
			String PhoneNumber = "8043531900";
			String PhoneExt = "3170";
			String OriginContactName = "Origin First Last Name";
			String OriginEmailAddress = "QATESTOrigin@Estes-Express.com";
			String OriginPhoneNumber = "8043531900";
			String OriginPhoneExt = "1111";
			String AddressLine1 = " 5451 Highway 42";
			String AddressLine2 = "Terminal 041";
			String SpecialInstruction = "TBP-697 Book a Truckload Brokerage quote with Dry Van equipment Origin Special Instructions";
			String DestContactName = "Destination First Last Name";
			String DestEmailAddress = "QATESTDestination@Estes-Express.com";
			String DestPhoneNumber = "8041234567";
			String DestPhoneExt = "7799";
			String DestAddressLine1 = "3914 East Shelby Drive";
			String DestAddressLine2 = "Terminal 037";
			String DestSpecialInstruction = "TBP-697 Book a Truckload Brokerage quote with Dry Van equipment  - Destination Special Instructions";

			String Quantity = "75";
			String FreightDescription = "Less than 20 hrs lead time TBP-759";
			String FreightValue = "75000";

			String message = "Please note that next day pickups can be difficult to accommodate. We urge you to book this quote as quickly as possible to help ensure your shipment needs are met.";

			String message2 = "Your requested pickup time is too early. We require at least 20 hours between booking and pickup time. You can request a later pickup time or contact our Truckload team at 1-866-378-3748. press 31 for direct assistance.";

			// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();

			// Step 2: Login to *My Estes* application using the following
			// credentials:testnat
			myEstesLoginPage.enterUserName(username8);
			myEstesLoginPage.enterPassword(password8);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Select *Ship* from the menu, then select *Rate Quote*
			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			// Step 4: the default selection *Less Than Truckload*

			rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

			// Step 5: In the *Requester Information* section, Select an Account from the
			// *Account Search* popup window or enter *B000718*
			rateQuotePage.clickOnAccountSearchLink();
			testUtil.setHardWait(2000);
			rateQuotePage.enterAccountNumberInAccSearch(AccNum);
			testUtil.setHardWait(2000);
			rateQuotePage.clickOnFirstAccountNumber();

			// Step 6: In the *Routing Information* section, enter or select the following
			// values:
			testUtil.setHardWait(1000);
			rateQuotePage.enterOriginZip("30307");
			testUtil.setHardWait(1000);
			rateQuotePage.enterDesZip("37602");

			// Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in
			// the future
			rateQuotePage.enterPickupDate(testUtil.addTomorrowDate());

			rateQuotePage.enterWeight("22321");
			testUtil.setHardWait(2000);
			// Step 8: Click on submit request
			rateQuotePage.clikOnSubmitButton();

			rateQuotePage.verifyTheMessageDisplayed(message);

			// Step 9: Verify estes truckload charges are calculated
			rateQuotePage.verifyTuckloadBasicCharge("Truckload Brokerage", true);

			// Step 10 : Click *Book Shipment* button
			rateQuotePage.clickOnBookShipmentButton();

			rateQuotePage.verifyBookTruckloadShipmentPageDisplayed();

			testUtil.setHardWait(2000);

			// Step 12 : From the *Shipment Details* section record quote number for step-15
			String quoteNumber = rateQuotePage.recordQuoteNum();

			// Step 13 : In the *Requestor Information* section enter the following values:

			bookTruckloadShipmentPage.enterContactName(ContactName);
			bookTruckloadShipmentPage.enterCompanyName(CompanyName);
			bookTruckloadShipmentPage.enterYourEmail(EmailAddress);
			bookTruckloadShipmentPage.enterPhoneNo(PhoneNumber);
			bookTruckloadShipmentPage.enterPhoneExtentionNo(PhoneExt);

			// Step 14 : In the *Routing - Origin Information* section enter the following
			// values:

			bookTruckloadShipmentPage.enterOriginContactName(OriginContactName);
			bookTruckloadShipmentPage.enterOriginEmail(OriginEmailAddress);
			bookTruckloadShipmentPage.enterOriginPhoneNo(OriginPhoneNumber);
			bookTruckloadShipmentPage.enterOriginPhoneExtentionNo(OriginPhoneExt);
			bookTruckloadShipmentPage.enterOriginAddress1(AddressLine1);
			bookTruckloadShipmentPage.enterOriginAddress2(AddressLine2);

			bookTruckloadShipmentPage.clickOnOriginAppointmentCheckbox();

			bookTruckloadShipmentPage.selectPickupStartTime("6", "00", "AM");// 8:pm
			bookTruckloadShipmentPage.selectPickupEndTime("8", "00", "AM");// 11pm
			bookTruckloadShipmentPage.enterOriginSpecialInstructions(SpecialInstruction);

			// Step 15 : In the *Routing - Destination Information* section enter the
			// following values:

			bookTruckloadShipmentPage.enterDestContactName(DestContactName);
			bookTruckloadShipmentPage.enterDestEmail(DestEmailAddress);
			bookTruckloadShipmentPage.enterDestPhoneNo(DestPhoneNumber);
			bookTruckloadShipmentPage.enterDestPhoneExtentionNo(DestPhoneExt);
			bookTruckloadShipmentPage.enterDestAddress1(DestAddressLine1);
			bookTruckloadShipmentPage.enterDestAddress2(DestAddressLine2);
			bookTruckloadShipmentPage.clickOnDestAppointmentCheckbox();
			bookTruckloadShipmentPage.selectDeliveryStartTime("1", "30", "PM");
			bookTruckloadShipmentPage.selectDeliveryEndTime("5", "15", "PM");
			bookTruckloadShipmentPage.enterDestSpecialInstructions(DestSpecialInstruction);

			// Step 16 : From the *Freight Information* section, enter or select the
			// following values:

			bookTruckloadShipmentPage.enterCommodityType("PALLET");
			bookTruckloadShipmentPage.enterQuantity(Quantity);
			bookTruckloadShipmentPage.enterFreightDesc(FreightDescription);

			bookTruckloadShipmentPage.enterFreightValue(FreightValue);
			bookTruckloadShipmentPage.enterReferenceNumber(quoteNumber);

			// Step 17 : Click the *Book Truckload Shipment* button
			bookTruckloadShipmentPage.clickOnBookTruckloadShipmentButton();

			// Step 18 : Verify a message is displayed
			bookTruckloadShipmentPage.verifyAMessageIsDisplayed(message2);

			// Step 19 : In the *Routing - Origin Information* section, update:
			bookTruckloadShipmentPage.selectPickupStartTime("9", "00", "AM");// 8:pm
			bookTruckloadShipmentPage.selectPickupEndTime("11", "00", "AM");// 11pm

			// Step 20 : Click the *Book Truckload Shipment* button
			bookTruckloadShipmentPage.clickOnBookTruckloadShipmentButton();

			// Verify a message is displayed
			bookTruckloadShipmentPage.verifySuccessMessageIsDisplayed();

			// Step 21 : From the *Booking Confirmation* page, note the PRO Number
			String proNum = bookTruckloadShipmentPage.recordPRONum();
			String terminalNo = "020";

			testUtil.setHardWait(2000);

			// Step-22: select *My Estes* from the menu then select
			myEstesLoginPage.clickOnMyEstes();
			testUtil.setHardWait(2000);
			myEstesLoginPage.clickOnLogoutButton();
			// Step-23 : Logout by clicking the *Confirm* button
			myEstesLoginPage.clickOnLogoutConfirmButton();

		}

	
		/**
		 * 
		 * passed on 6/17/22
		 * 
		 * Verify when an Estes Truckload quote is successfully booked then the PRO
		 * number is reserved and the specific quote data are added (Quote, Total, Shpr
		 * acct, org address, dest address, Pcs, Wgt, # Pallets, freight desc, PO if
		 * provided)
		 */
		@Test(enabled = true, priority = 3)
		public void executeQZ_10382()
				throws Exception {
			// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();

			// Step 2: Login to *My Estes* application using the following
			// credentials: testnat
			myEstesLoginPage.enterUserName(username8);
			myEstesLoginPage.enterPassword(password8);
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: Select *Ship* from the menu, then select *Rate Quote*
			myEstesHomePage.clickOnShipTab();
			myEstesHomePage.clickOnRateQoute();

			// Step 4: the default selection *Less Than Truckload* select *Estes Truckload*
			rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

			// Step 5: In the *Requester Information* section, Select an Account from the
			// *Account Search* popup window or enter *B000718*
			rateQuotePage.clickOnAccountSearchLink();
			testUtil.setHardWait(2000);
			rateQuotePage.enterAccountNumberInAccSearch("B000718");
			testUtil.setHardWait(2000);
			rateQuotePage.clickOnFirstAccountNumber();

			// Step 6: In the *Routing Information* section, enter or select the following
			// values:
			testUtil.setHardWait(1000);
			rateQuotePage.enterOriginZip("60045");
			testUtil.setHardWait(1000);
			rateQuotePage.enterDesZip("23059");

			// Step 7: In the *Shipment Information* section, enter Pickup Date: 2 days in
			// the future
			String pickupDate = testUtil.addFutureWeekDay();
			System.out.println(pickupDate);
			rateQuotePage.enterPickupDate(pickupDate);
			rateQuotePage.enterWeight("21330");

			// Step 8: Click on submit request
			rateQuotePage.selectSubmitButton();

			// Step 9: From the *Results* table, verify *Estes Truckload* charges are
			// calculated
			rateQuotePage.verifyTuckloadBasicCharge("Estes Truckload", true);

			// Step 10: Click *Book Shipment* button
			rateQuotePage.clickOnBookShipmentButton();
			rateQuotePage.verifyBookTruckloadShipmentPageDisplayed();
			testUtil.setHardWait(2000);

			// Step 11: From the *Shipment Details* section record quote number
			String quoteNumber = rateQuotePage.recordQuoteNum();

			// Step 12: In the *Requestor Information* section enter the following values:
			bookTruckloadShipmentPage.enterContactName("Requester First Last Name");
			bookTruckloadShipmentPage.enterCompanyName("Estes Truckload Order");
			bookTruckloadShipmentPage.enterYourEmail("QATEST@Estes-Express.com");
			bookTruckloadShipmentPage.enterPhoneNo("8043531900");
			bookTruckloadShipmentPage.enterPhoneExtentionNo("1234567");

			// Step 13: In the *Routing - Origin Information* section enter the following
			// values:
			bookTruckloadShipmentPage.enterOriginContactName("Origin First Last Name");
			bookTruckloadShipmentPage.enterOriginEmail("QATESTOrigin@Estes-Express.com");
			bookTruckloadShipmentPage.enterOriginPhoneNo("8043531900");
			bookTruckloadShipmentPage.enterOriginPhoneExtentionNo("1111");
			bookTruckloadShipmentPage.enterOriginAddress1("316 North Roadway Lane");
			bookTruckloadShipmentPage.enterOriginAddress2("Terminal 111");
			bookTruckloadShipmentPage.clickOnOriginAppointmentCheckbox();
			bookTruckloadShipmentPage.selectPickupStartTime("6", "00", "AM");
			bookTruckloadShipmentPage.selectPickupEndTime("1", "45", "PM");
			bookTruckloadShipmentPage.enterOriginSpecialInstructions("TBP-754 Origin Special Instructions");

			// Step 14: In the *Routing - Destination Information* section enter the
			// following values:
			bookTruckloadShipmentPage.enterDestContactName("Destination First Last Name");
			bookTruckloadShipmentPage.enterDestEmail("QATESTDestination@Estes-Express.com");
			bookTruckloadShipmentPage.enterDestPhoneNo("8041234567");
			bookTruckloadShipmentPage.enterDestPhoneExtentionNo("7799");
			bookTruckloadShipmentPage.enterDestAddress1("1200 Commerce Road");
			bookTruckloadShipmentPage.enterDestAddress2("Terminal 001");
			bookTruckloadShipmentPage.clickOnDestAppointmentCheckbox();
			bookTruckloadShipmentPage.selectDeliveryStartTime("1", "30", "PM");
			bookTruckloadShipmentPage.selectDeliveryEndTime("5", "15", "PM");
			bookTruckloadShipmentPage.enterDestSpecialInstructions("TBP-754 Destination Special Instructions");

			// Step 15: From the *Freight Information* section, enter or select the
			// following values:
			bookTruckloadShipmentPage.enterCommodityType("PALLET");
			bookTruckloadShipmentPage.enterQuantity("21");
			bookTruckloadShipmentPage.enterFreightDesc("Estes Truckload Special Inst TBP-754");
			bookTruckloadShipmentPage.enterFreightValue("45700");
			bookTruckloadShipmentPage.enterReferenceNumber(quoteNumber);
			bookTruckloadShipmentPage.enterPONumber("PO TBP754");
			testUtil.setHardWait(3000);
			// Step 16: Click the *Book Truckload Shipment* button
			bookTruckloadShipmentPage.clickOnBookTruckloadShipmentButton();
			testUtil.setHardWait(5000);
			bookTruckloadShipmentPage.verifySuccessMessageIsDisplayed();

			// Step 17 : From the *Booking Confirmation* page, note the PRO Number
			String proNum = bookTruckloadShipmentPage.recordPRONum1();
			String total = bookTruckloadShipmentPage.recordCharges();

			// Step-18 : Logon to the *EXLAQA* server, using your credentials:
			SessionVt session = null;

			String userName = "devabni";
			String password = "nithyadev";
			String name = "myJagacyVT";
			String terminal = "dec-vt220";

			session = new SessionVt(name, "exlaqa", terminal);
			session.open();

			Thread.sleep(2000);

			LoginScreen loginScreen = new LoginScreen(session);
			IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
			Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
			FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			loginScreen.logon(userName, password);
			Thread.sleep(2000);
			ibmMainMenuScreen.verifyIBMMainMenuScreen();

			// Step-19 : From the command line, enter: CALL XXC870
			ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

			// Step-20 : From the *LTL Master Menu*, select: "1"
			ltl38MasterMenuScreen.enterValueOptionField("1");

			// Step-21 : From the *Freight Billing Menu*, enter the following:
			freightBillingMenuScreen.enterValueOptionField("1");
			freightBillingMenuScreen.enterValueUserField("Truckload");
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber("002");

			// Step-22 : Key in the PRO Number noted in step 17
			freightBillingMenuScreen.enterFreightBill("020", proNum);
			freightBillingMenuScreen.verifyPROIsInRStatus();

			// Step 23: Validate Terms value
			freightBillingMenuScreen.verifyTerms("RP");

			// Step 24: Verify Shipper #, Wgt, and date
			freightBillingMenuScreen.verifyShipperCode("B000718");
			freightBillingMenuScreen.verifyWeight("21330");
			String date = testUtil.addFutureWeekDayFormattedForEXLAVerification();
			System.out.println(date);
			String formatDate = testUtil.formatDate(date, "MM/dd/yy"); //newly added
			testUtil.setHardWait(5000);
//			freightBillingMenuScreen.verifyDate(date);  --changed to below line
			freightBillingMenuScreen.verifyDate(formatDate); //<<--

			// Step 25: Validate the following data agrees with data recorded in step 11:
			// Note: select F8 to view the Quote Number
			freightBillingMenuScreen.verifyTotal(total);
			jagacyUtil.pressF8();
			freightBillingMenuScreen.verifyQuoteNumber(quoteNumber);
			jagacyUtil.pressF1();
			jagacyUtil.pressEnter();

			// Step 26: Validate charges are broken down and the sum equals Total charges:
			freightBillingMenuScreen.verifyFRevAndERevEqualTotal(total);

			// Step 27: Validate the following origin data agrees with Quote/Booking keyed
			// in data
			freightBillingMenuScreen.verifyConsigneeCityStateZip("GLEN ALLEN", "VA", "23059");
			freightBillingMenuScreen.verifyConsigneeAddressLine1("1200 Commerce Road");
			freightBillingMenuScreen.verifyConsigneeAddressLine2("Terminal 001");

			// Step 28: Validate the following data destination agrees with Quote/Booking
			// keyed in data
			freightBillingMenuScreen.verifyShipperCityStateZip("LAKE FOREST", "IL", "60045");
			freightBillingMenuScreen.verifyShipperAddressLine1("316 North Roadway Lane");
			freightBillingMenuScreen.verifyShipperAddressLine2("Terminal 111");

			// Step 29: Validate the following data agrees with Booking keyed in data:
			freightBillingMenuScreen.verifyCommodityType("SK");
			freightBillingMenuScreen.verifyQuantity("21");
			freightBillingMenuScreen.verifyDescription("Estes Truckload Special Inst TBP-75");
			freightBillingMenuScreen.verifyPONumber("PO TBP754");
		}
		

	
	/**
	 * 
	 * @author habibja
	 */
	// passed on 9/27/22
	
	/**
	 * Verify when Estes Truckload & 'Hazardous Materials' is selected & the Contact
	 * Me link is selected populated & submitted then the correct hazmat data is
	 * saved to the Estes Truckload quote in the Exception Queue
	 */
	

	@Test(enabled = true, priority = 4)

	public void executeQZ_11049()
			throws Exception {

		// Step 1 &2: Open the link and enter credentail
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		myEstesLoginPage.clickOnLoginButton();
		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		
		
		
		// Step 4: From the *Select Quote Type* section, select *Estes Truckload*

		rateQuotePage.selectOrDeselectEstesTruckload();
		// Step 5: From the *Requester Information* section
		testUtil.setHardWait(1000);
		rateQuotePage.enterAccountNumber("B000718");
		testUtil.setHardWait(1000);
		// Step 6:In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("37602");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("23230");
		// Step 7: In the *Shipment Information* section,enter or select the following
		// values:
		testUtil.setHardWait(2000);
		rateQuotePage.addTomorrowDate1();
		

		rateQuotePage.enterWeightForShipmentInfo("6000");
		testUtil.setHardWait(1000);
		rateQuotePage.clickOnHazardousMaterialsCheckBox(); // Step 8: In the
		rateQuotePage.clickOnTankerEndorsementCheckBox(); // Step 9: Enter or select
		rateQuotePage.enterUnNumber("1234");
		rateQuotePage.selectPackagingCode("I");
		rateQuotePage.selectCommodityType("SKID");
		rateQuotePage.enterHazmatPieceCount("11");
		rateQuotePage.enterHazmatWeight("1000");
		rateQuotePage.enterHazmatDescription(" QZ- 11049 TBP- 1496 Row 1, Hazmat Shipment");

		// Step 10: click on additional row button

		rateQuotePage.clickOnAddAdditionalRowButton();

		// Step 11: Enter or select the following values

		rateQuotePage.enterUnNumber1("2345");
		rateQuotePage.selectPackagingCode1("II");
		rateQuotePage.selectCommodityType1("ROLL");
		rateQuotePage.enterHazmatPieceCount1("12");
		rateQuotePage.enterHazmatWeight1("2000");

		rateQuotePage.enterHazmatDescription1("QZ- 11049 TBP- 1496 Row 2, Hazmat Shipment");
		// rateQuotePage.clickOnSubmitButton();

		// Step 12: Click on +Add Additional row button
		rateQuotePage.clickOnAddAdditionalRowButton();

		// Step 13: Enter or select the following values:

		rateQuotePage.enterUnNumber2("3456");
		rateQuotePage.selectPackagingCode2("III");
		rateQuotePage.selectCommodityType2("REEL");
		rateQuotePage.enterHazmatPieceCount2("13");
		rateQuotePage.enterHazmatWeight2("3000");
		rateQuotePage.enterHazmatDescription2("TBP- 1496 Row 3, Hazmat Shipment");
		// Step 14: Click on Submit request- record the quote Timestamp

		//getting the timestamp
		String str2=testUtil.getTimestamp();
		System.out.println("The current time captured for this step is:" + str2);
		
		rateQuotePage.clickOnSubmitButton();
		 
		
		
		 // Date dt= new Date(); String ts=new Timestamp(dt.getTime()).toString(); String
		//  replc=ts.replace("-","::").replace(" ", "------").replace(".", "::");
		//  logger.info("The date is: " +replc);
		  
		  //The date is: 2021-12-15 21:34:18.051 //2021-12-15------21:56:38::681
		 
		//Step 15: from the results table, click Contact Me.
		
		rateQuotePage.clickOnContMe();
		  
		//Step 16: int he contact US popup window, enter the following data
		
		rateQuotePage.enterContactName("Test");
		rateQuotePage.enterYourEmail("QATest@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterPhoneExtentionNo("3170");
		rateQuotePage.clickOnConfirmButton();
		
		//Step 17: verify success message
//		rateQuotePage.verifyMessageDisplayed();  --changed to below method
		rateQuotePage.verifySuccessMessageDisplayed(); //<<--
		
		//step19: logout
		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		myEstesLoginPage.clickOnLogoutConfirmButton();
		
		//Step 21 & 22: open enet link and enter username and password
		
		driver.get(url2);
		
		
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		
		// Step 23: From *Volume and Truckload Quote Exception Queue*,select the quote
		// with a *Quote Timestamp* matching/very close to the one recorded in step 14
		// and a Hazmat Accessorial Exception
		
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();
	
		//Step 34:From *Volume and Truckload Quote Exception Queue*,
		//select the quote with a *Quote Timestamp* matching/very close to the one recorded in step 14 and a Hazmat Accessorial Exception 
		
		eNetVTLQouteExceptionQueuePage.getQuoteByTimeStamp(str2);
		
		
	}
	
	
	/*
	 Verify that when MyEstes user book an Estes Truckload quote with Refrigerated equipment type then a 
	 success message is displayed the order is successfully created on CHR side and a PRO is reserved
	 */


	@Test(enabled = true, priority = 5)
	public void executeQZ_10247()
			throws Exception {
		
		String AccNum = "B000718";
		
		String ContactName = "Contact First Last Name";
		String CompanyName = "Truckload Brokerage ";
		String EmailAddress = "QATEST@Estes-Express.com";
		String PhoneNumber = "8043531900";
		String PhoneExt = "3170";
		String OriginContactName = "Origin First Last Name";
		String OriginEmailAddress = "QATESTOrigin@Estes-Express.com";
		String OriginPhoneNumber = "8043531900";
		String OriginPhoneExt = "1111";
		String AddressLine1 = "3901 West Broad Street";
		String AddressLine2 = "Terminal 002";
		String SpecialInstruction = "TBP-700 Book a Truckload Brokerage quote Refrigerated equipment Origin Special Instructions";
		String DestContactName = "Destination First Last Name";
		String DestEmailAddress = "QATESTDestination@Estes-Express.com";
		String DestPhoneNumber = "8041234567";
		String DestPhoneExt = "7799";
		String DestAddressLine1 = "3914 East Shelby Drive";
		String DestAddressLine2 = "Terminal 037";
		String DestSpecialInstruction = "TBP-700 Book a Truckload Brokerage quote with Refrigerated equipment  - Destination Special Instructions";
		
		
		String Quantity = "21";
		String FreightDescription = "Truckload Order with Refrigerated Type. TBP-700";
		String FreightValue = "87500";
		String poNum = "020 3012021";
		
		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		myEstesLoginPage.clickOnLoginButton();
	
		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();
	
		// Step 5: In the *Requester Information* section, Select an Account from the *Account Search* popup window or enter *B000718*
		rateQuotePage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumberInAccSearch(AccNum);
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnFirstAccountNumber();
		

		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
	
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("37602");

		//Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.selectEquipmentType("Refrigerated");
		rateQuotePage.enterWeight("30121");

		// Step 8: Click on submit request
		rateQuotePage.clickOnSubmitButton();
		
		// Step 9: Verify estes truckload charges are calculated
		rateQuotePage.verifyTuckloadBasicCharge("Truckload Brokerage", true);
		
		//Step 10 : Expand the results and verify quote details page displayed
		rateQuotePage.expandResultsSection();
		rateQuotePage.verifyQuoteDetailsPageDisplayed();
		
		//Step 11 : Click *Book Shipment* button
		rateQuotePage.clickOnBookShipmentButton();
		
		testUtil.setHardWait(2000);
		//Step 12 : From the  *Shipment Details* section record quote number for step-15
		String quoteNumber = rateQuotePage.recordQuoteNum();
		
		//Step 13 : In the  *Requestor Information* section enter the following values:
		
		bookTruckloadShipmentPage.enterContactName(ContactName);
		bookTruckloadShipmentPage.enterCompanyName(CompanyName);
		bookTruckloadShipmentPage.enterYourEmail(EmailAddress);
		bookTruckloadShipmentPage.enterPhoneNo(PhoneNumber);
		bookTruckloadShipmentPage.enterPhoneExtentionNo(PhoneExt);
		
		//Step 14 : In the  *Routing - Origin Information* section enter the following values:
		
		bookTruckloadShipmentPage.enterOriginContactName(OriginContactName);
		bookTruckloadShipmentPage.enterOriginEmail(OriginEmailAddress);
		bookTruckloadShipmentPage.enterOriginPhoneNo(OriginPhoneNumber);
		bookTruckloadShipmentPage.enterOriginPhoneExtentionNo(OriginPhoneExt);
		bookTruckloadShipmentPage.enterOriginAddress1(AddressLine1);
		bookTruckloadShipmentPage.enterOriginAddress2(AddressLine2);
		
		bookTruckloadShipmentPage.clickOnOriginAppointmentCheckbox();
		bookTruckloadShipmentPage.selectPickupStartTime("8", "15", "AM");
		bookTruckloadShipmentPage.selectPickupEndTime("3", "30", "PM");
		bookTruckloadShipmentPage.enterOriginSpecialInstructions(SpecialInstruction);
		
		//Step 15 : In the  *Routing - Destination Information* section enter the following values:
		
		bookTruckloadShipmentPage.enterDestContactName(DestContactName);
		bookTruckloadShipmentPage.enterDestEmail(DestEmailAddress);
		bookTruckloadShipmentPage.enterDestPhoneNo(DestPhoneNumber);
		bookTruckloadShipmentPage.enterDestPhoneExtentionNo(DestPhoneExt);
		bookTruckloadShipmentPage.enterDestAddress1(DestAddressLine1);
		bookTruckloadShipmentPage.enterDestAddress2(DestAddressLine2);
		bookTruckloadShipmentPage.clickOnDestAppointmentCheckbox();
		bookTruckloadShipmentPage.selectDeliveryStartTime("12", "30", "PM");
		bookTruckloadShipmentPage.selectDeliveryEndTime("4", "45", "PM");
		bookTruckloadShipmentPage.enterDestSpecialInstructions(DestSpecialInstruction);
		
		// Step 16 : From the *Freight Information* section, enter or select the following values:
		
		bookTruckloadShipmentPage.enterCommodityType("SKID");
		bookTruckloadShipmentPage.enterQuantity(Quantity);
		bookTruckloadShipmentPage.enterFreightDesc(FreightDescription);
		bookTruckloadShipmentPage.enterTempNumberRangeFrom("33");
		bookTruckloadShipmentPage.enterTempNumberRangeTo("39");
		bookTruckloadShipmentPage.enterFreightValue(FreightValue);
		bookTruckloadShipmentPage.enterReferenceNumber(quoteNumber);
		bookTruckloadShipmentPage.enterPONumber(poNum);
		
		
		//Step 17 : Click the  *Book Truckload Shipment* button
		bookTruckloadShipmentPage.clickOnBookTruckloadShipmentButton();
		
		//Verify Success message
		bookTruckloadShipmentPage.verifySuccessMessageIsDisplayed();
		
		// Step 18 : From the  *Booking Confirmation* page, note the PRO Number
		String proNum = bookTruckloadShipmentPage.recordPRONum();
		String terminalNo = "020";

		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";
		String terminal = "dec-vt220";


		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		
		
		// Step-19 : Logon to the  *EXLAQA* server, using your credentials:
		loginScreen.logon(userName, password);
		Thread.sleep(2000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();
		
		//Step-20 : From the command line, enter: CALL XXC870
		ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Step-21 : From the  *LTL Master Menu*, select: "1"
		ltl38MasterMenuScreen.enterValueOptionField("1");
		
		//Step-22 : From the  *Freight Billing Menu*, enter the following:
		
		freightBillingMenuScreen.enterValueOptionField("1");
	
		freightBillingMenuScreen.enterValueUserField("Truckload");
		freightBillingMenuScreen.enterTabKey();
		
		freightBillingMenuScreen.enterTerminalNumber("002");
		
		//Step-23 : Key in the PRO Number noted in step 17
		freightBillingMenuScreen.enterFreightBill(terminalNo, proNum);
		
	
		
		//Verify the PRO  is in R/Reserve Status
		freightBillingMenuScreen.verifyPROIsInRStatus();
		
		
		//Step-24 : To log out, press/enter
		//F1 (to exit FB inquiry)
		//90 (to exit FB menu)
		//90 (to exit and logout)
		
		jagacyUtil.pressF1();	
		freightBillingMenuScreen.enterValueOptionField("90");
		jagacyUtil.pressEnter();
		ltl38MasterMenuScreen.enterValueOptionField("90");
	
		if (session != null) {
			session.abort();
			session.close();
		}
		
		
	}

}
