package estesFinalMileTests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import estesFinalMilePages.EstesFinalMileHomeDeliveryPage;
import estesFinalMilePages.EstesFinalMileShipmentDetails;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.CommandEntryScreen;
import jagacyVT.screens.FreightBillAccessorialChargesandShippingInstructionsScreen;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.MasterMenuScreen;
import jagacyVT.screens.ReserveFreightBillScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import jagacyVT.screens.UpdateScreen3;
import myEstesPages.MyEstesHomePage;
import solutionsPages.MyEstesSolutionsPage;
import testBase.TestBase;
import util.SQLDataList;

public class EstesFinalMileSmokeTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	EstesFinalMileHomeDeliveryPage efmHomeDeliveryPage = new EstesFinalMileHomeDeliveryPage();
	MyEstesSolutionsPage myestesSolutionPage = new MyEstesSolutionsPage();
	EstesFinalMileShipmentDetails efmShipmentDetailsPage = new EstesFinalMileShipmentDetails();
	SQLDataList sqlDatalist = new SQLDataList();
	EstesFinalMileTemplateTest estesFinalMileTemplateTest = new EstesFinalMileTemplateTest();

	/******************************* TESTS *******************************/

	  /**This is test is flaky on Jenkins- should run locally**/
	
	@Test(enabled = true, priority = 1)
	public void executeQZ_6945() throws JagacyException, Exception {
	
		String expMsg = "You may sign up for an unattended delivery, or we will contact you to set up an appointment. Please note if you do not sign up for unattended delivery, we will give you a call to schedule an appointment when the freight has reached our final facility.";

		String expMsg2 = "You have opted out of an appointment and no signature is required. We will place your shipment out for delivery in accordance with the instructions you provided as soon as possible.";
		String radiobutton = "BACK DOOR", unattendedDeliveryName = "EITQA", gateOrCode = "12345";
		String unattendedDeliveryMessageRight = "Opt out of needing an appointment with our unattended delivery option.";
		String contactUsMessageRight = "Contact us for help with all of your shipment questions!";
		String contactUsEmailAddress = "delivery@estes-express.com";
		String contactUsPhoneNumber = "1-888-858-2684";
		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String originTerminal = "087";
		String sCode = "2302234";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// MySession session = new MySession();
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);


		jagacyUtil.pressEnter(); /* UnComment this line when you see an error "Job Devabni started on.... in subsystem ...devabni is allocated to anotherjob.Press Enter to Contiune"*/

		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
//		ltl38mastermenu.enterValueOptionField(option);
		ltl38mastermenu.enterValueOptionField("1");

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(
				session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);

		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(
				session);
		//reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		// Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Press F3 to exit

		jagacyUtil.pressF3();

		// Enter Freight bill
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("8700777");
		freightBillUpdateScreen.enterConsName("REG NOSIGN");
		freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
		freightBillUpdateScreen.enterConsCity("COLUMBUS");
		freightBillUpdateScreen.enterConsState("OH");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterPONum("224091819");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();

		// Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Adding shipment instructions
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("CPHONE", "8043531900", "CEMAIL", "QATEST@ESTES.COM");
		jagacyUtil.pressEnter();
//		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// EFM
		// Solutions - Final Mile - EFM
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		testUtil.setHardWait(1000);
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Enter Tracking number & Zip Code
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(10000);
		efmHomeDeliveryPage.clickOnTrackNowButton();

		// Click on *Track Now* button-Address link contains OTPRO and Destination Zip
		// Code in URL

		efmShipmentDetailsPage.verifyAddressBarLinkContainsTrackingInfo(originTerminal, billNum, consZip);
		//	driver.navigate().refresh();

		// From the Shipment details page, the following should be displayed above the
		// Shipment Status bar and populated with shipment data: *Tracking
		// Number*:*ZIP/Postal Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();

		// *Status Bar*: Shipped *(GREEN)* in color
		efmShipmentDetailsPage.verifySHippedColor();

		// Verify Shipment Details page is displayed with below message:"You may sign up
		// for an unattended delivery, or we will contact you to set up an appointment.
		// Please note if you do not sign up for an unattended delivery, an appointment
		// must be set for your delivery to occur."
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		// From the Shipment Details Page, click on the View Shipment Details Accordion.
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		// Shipment Details page, Verify the following components is populated on right
		// side of the page with message *Unattended Delivery * with *Signup* button Opt
		// out of needing an appointment with our unattended delivery option
		efmShipmentDetailsPage.verifyUnattendedDeliveryTextInRightDisplays();
		efmShipmentDetailsPage.verifyUnattendedDeliveryMessageInRightDisplays(unattendedDeliveryMessageRight);
		efmShipmentDetailsPage.verifyUnattendedDeliveryButtonInRightDisplays();

		// Verify the following should be displayed on the right hand side of the page.
		// Track another Shipment component*Tracking Number* text field*Zip Code* text
		// field *Track Now* button
		efmShipmentDetailsPage.verifyTrackAnotherShipmentTextInRightDisplays();
		efmShipmentDetailsPage.verifyTrackAnotherShipmentTrackingNumberInRightDisplays();
		efmShipmentDetailsPage.verifyTrackAnotherShipmentZipCodeInRightDisplays();
		efmShipmentDetailsPage.verifyTrackAnotherShipmentTrackNowButtonInRightDisplays();

		// Verify the following should be displayed d on the right hand side of the
		// page.*Contact Us component*Contact us for help with all of your shipment
		// questions!*Email* link <homedelivery@estes-express.com>*Phone Number* link
		// 1-888-858-2684
		efmShipmentDetailsPage.verifyContactUsTextInRightDisplays();
		efmShipmentDetailsPage.verifyContactUsMessageInRightDisplays(contactUsMessageRight);
		efmShipmentDetailsPage.verifyContactUsEmailAddressInRightDisplays(contactUsEmailAddress);
		efmShipmentDetailsPage.verifyContactUsPhoneNumberInRightDisplays(contactUsPhoneNumber);

		// From Shipment Details page, Click on the following link in message:
		efmShipmentDetailsPage.clickOnUnattendedDeliveryLink();

		// From the Unattended Delivery Signup popup page, enter the following values:
		efmShipmentDetailsPage.unattendedDeliveryName(unattendedDeliveryName);
		efmShipmentDetailsPage.unattendedDeliveryGateOrCode(gateOrCode);
		efmShipmentDetailsPage.radioButtonUnattendedDelivery(radiobutton);
		efmShipmentDetailsPage.unattendedDeliveryAuthorizeCheckBox();

		// Click on *Finish* button
		efmShipmentDetailsPage.clickOnUnattendedDeliveryFinishButton();
		testUtil.setHardWait(8000);
		testUtil.reloadPage();// added reload
		testUtil.setHardWait(2000); // extended time

		// From the Shipment details page, the following should be displayed above the
		// Shipment Status bar and populated with shipment data: *Tracking
		// Number*:*ZIP/Postal Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRange();

		testUtil.setHardWait(2000);
		// *Status Bar*: Shipped *(GREEN)* in color
		efmShipmentDetailsPage.verifySHippedColor();
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRangeInShipmentDetails();

		// Verify Shipment Details page is displayed with below message:"You have opted
		// out of an appointment and no signature is required. We will place your
		// shipment out for delivery in accordance with the instructions you provided as
		// soon as possible."
		testUtil.reloadPage();
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg2);

		// Want to receive text and email updates on your shipment? section, is
		// displayed with the following fields - Primary,secondary and other phone
		// number along with email address
		efmShipmentDetailsPage.verifyPrimaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifySecondaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifyEmailAddressDisplays();
		efmShipmentDetailsPage.verifyOtherPhoneNumberDisplays();

		// Verify *Unattended Delivery Signup* component and link not displayed any
		// where on the page; only the following components are displayed: *Track
		// another Shipment**Contact Us*
		efmShipmentDetailsPage.verifyUnattendedDeliveryDisabled();


	}
	
	
	/**
	 * 
	 * @Author Quinci
	 */
	/*
	 * Estes Final Mile - Verify EDD (Estimated Delivery Date) Range should not display when Shipment is Non-Residential
	 *
	 */
	
	
	@Test(enabled = true, priority = 2)
	public void executeQZ_11287() throws Exception {
		
		String expMsg = "You may sign up for an unattended delivery, or we will contact you to set up an appointment. Please note if you do not sign up for unattended delivery, we will give you a call to schedule an appointment when the freight has reached our final facility.";
		String expMsg2 = "Opt out of needing an appointment with our unattended delivery option.";
		String contactUsMessageRight = "Contact us for help with all of your shipment questions!";
		String contactUsEmailAddress = "delivery@estes-express.com";
		String contactUsPhoneNumber = "1-888-858-2684";
		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String originTerminal = "171";
		String sCode = "7178618";
		String consZip = "02138";
		String option1 = "2";

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		String poNum = todayDate + "11287";

		// Jagacy
		SessionVt session = null;
		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";

		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// Step 1: Reserve pro for a shipper serviced by an operational terminal (QZ-384)
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);

		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login

		testUtil.setHardWait(3000);
		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);

		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		// Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// Step 2 - 3: Create Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		freightBillingMenuScreen.selectShipper();
		testUtil.setHardWait(5000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("5608430");
		freightBillUpdateScreen.enter3Pt("5068692");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterMasterBlNo("09172019H1K");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// Step 4 - 6: From Estes QA Homepage, select *Solutions*, next select *Final Mile*
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		testUtil.setHardWait(1000);

		// Step 7: From the Residential page, select *Estes Final Mile*
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Step 8: Estes Final Mile Home Delivery page, enter test data created in the following fields on the Track a Shipment component
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(20000);

		// Step 9: Click on *Track Now* button
		efmHomeDeliveryPage.clickOnTrackNowButton();
		efmShipmentDetailsPage.verifyAddressBarLinkContainsTrackingInfo(originTerminal, billNum, consZip);

		// Step 10: From the Shipment details page, the following should be displayed above the
		// Shipment Status bar and populated with shipment data: *Tracking
		// Number*:*ZIP/Postal Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		testUtil.setHardWait(1000);
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();

		// Step 11: The following should be displayed on the Shipment Status bar:
		// *Status Bar*:
		// - Shipped *(GREEN)* with status date if available
		// - Appointment Set
		// - Out For Delivery
		// - Delivered
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifySHippedColor();
		efmShipmentDetailsPage.validateShippedDate();
		efmShipmentDetailsPage.verifyAppointmentSetStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyOutForDeliveryStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyDeliveredStatusBarTextIsDisplayed();

		// Step 12: Verify Shipment Details page is displayed with below message:"You may sign up
		// for an unattended delivery, or we will contact you to set up an appointment.
		// Please note if you do not sign up for an unattended delivery, an appointment
		// must be set for your delivery to occur."
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		// Step 13: From the Shipment Details Page, click on the View Shipment Details Accordion and the shipment details is displayed.
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		// Step 14: Want to receive text and email updates on your shipment? section, is
		// displayed with the following fields - Primary and secondary mobile number, and email address
		efmShipmentDetailsPage.verifyPrimaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifySecondaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifyEmailAddressDisplays();

		// Step 15: Don't want to receive texts or emails? section is displayed with the below blank fields:
		// *Other Phone Number
		efmShipmentDetailsPage.verifyOtherPhoneNumberDisplays();

		// Step 16: Shipment Details page, Verify the following components is populated on right side of the page with message
		// *Unattended Delivery* with *Signup* button
		// Opt out of needing an appointment with our unattended delivery option
		efmShipmentDetailsPage.verifyUnattendedDeliveryTextInRightDisplays();
		efmShipmentDetailsPage.verifyUnattendedDeliveryMessageInRightDisplays(expMsg2);
		efmShipmentDetailsPage.verifyUnattendedDeliveryButtonInRightDisplays();

		// Step 17: Verify the following should be displayed on the right hand side of the page.
		// Track another Shipment component
		// *Tracking Number* text field
		// *Zip Code* text field
		// *Track Now* button
		efmShipmentDetailsPage.verifyTrackAnotherShipmentTextInRightDisplays();
		efmShipmentDetailsPage.verifyTrackAnotherShipmentTrackingNumberInRightDisplays();
		efmShipmentDetailsPage.verifyTrackAnotherShipmentZipCodeInRightDisplays();
		efmShipmentDetailsPage.verifyTrackAnotherShipmentTrackNowButtonInRightDisplays();

		// Step 18: Verify the following should be displayed on the right hand side of the page.
		// *Contact Us component*
		// Contact us for help with all of your shipment questions!
		// *Email* link <delivery@estes-express.com>
		// *Phone Number* link
		// 1-888-858-2684
		efmShipmentDetailsPage.verifyContactUsTextInRightDisplays();
		efmShipmentDetailsPage.verifyContactUsMessageInRightDisplays(contactUsMessageRight);
		efmShipmentDetailsPage.verifyContactUsEmailAddressInRightDisplays(contactUsEmailAddress);
		efmShipmentDetailsPage.verifyContactUsPhoneNumberInRightDisplays(contactUsPhoneNumber);

		// Step 19: From Shipment Details page, Click on the following link in message:
		// *unattended delivery*
		efmShipmentDetailsPage.clickOnUnattendedDeliveryLink();
		efmShipmentDetailsPage.validateUnattendedDeliveryPopup();

		// Step 20: Unattended Delivery Signup popup page, click on the x in the right corner to close the popup
		efmShipmentDetailsPage.clickCloseOnUnattendedDeliveryPopup();

	}
	
	/*
	 * EFM - Verify updated E211 file generated when CPHONE added or updated on freight bill and status is not Delivered
	 */
	
	@Test(enabled = true, priority = 3)
	public void executeQZ_9533() throws Exception {
		
		//Step#1 : Use the following details to reserve and create a Freight Bill: 

		String userName = "PEACHDO";
		String password = "aug08y14";
		String originTerminal = "054";
		String numOfFreightBills = "1";
		String sCode = "2302234";
		String TS = "1";
		String PCS = "10";
		String Terms = "P";
		String Wgt = "1000";
		String PO = "2132608";
		String PUDr = "9999999";
		String dNo = "9";
		String cFeet = "16";
		String PK = "PC";
		String DES = "FAK";
		String consignee = "8700777";
		String shipperCode1 = "CPHONE";
		String shipperInstruction1 = "8008008000";
		String shipperCode2 = "CEMAIL";
		String shipperInstruction2 = "QA@ESTES.COM";
		String consigneeName = "REG NOSIG";
		String consigneeAddress = "2 KINGSHILL DR";
		String consigneeCity = "COLUMBUS";
		String consigneeState = "OH";
		String consigneeZip = "43228";
				
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);
		testUtil.setHardWait(3000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		UpdateScreen updateScreen = new UpdateScreen(session);
		UpdateScreen2 updateScreen2 = new UpdateScreen2(session);
		FreightBillAccessorialChargesandShippingInstructionsScreen freightBillAccessorialChargesandShippingInstructionsScreen = new FreightBillAccessorialChargesandShippingInstructionsScreen(session);
		commandEntryScreen.enterCommand("CALL FBC870");
		MasterMenuScreen masterMenuScreen = new MasterMenuScreen(session);
		masterMenuScreen.verifyScreenTitle();
		
		freightBillingMenuScreen.enterValueOptionField("82");
		freightBillingMenuScreen.enterValueUserField("test");
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);
		reserveFreightBillScreen.enterBillsToReserve(numOfFreightBills);
		reserveFreightBillScreen.enterShipperCode(sCode);
		
		Thread.sleep(4000);

		String fbNum = reserveFreightBillScreen.getFBNumber();
		System.out.println("Freight bill number " + fbNum);
		System.out.println("Freight bill is reserved");
		
		// Press F3 to exit
		jagacyUtil.pressF3();
		
		//Logging out and Logging in
		freightBillingMenuScreen.enterValueOptionField("90");
		masterMenuScreen.enterMenuOption("90");
		loginScreen.logon(userName, password);
		commandEntryScreen.enterCommand("CALL FBC870");

		// Update Freight Bill
			
		freightBillingMenuScreen.enterValueOptionField("2");
		freightBillingMenuScreen.enterValueUserField("test");
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);
		Thread.sleep(2000);
		freightBillingMenuScreen.enterFreightBillNumber(fbNum); // fBillNumber

		Thread.sleep(2000);

		updateScreen.enterValuesToUpdateScreen(TS, PCS, Terms, Wgt, consignee, sCode, consigneeName, consigneeAddress, consigneeCity, consigneeState, consigneeZip, "", PO, PUDr, cFeet);
		updateScreen.enterCartTo("E054");
		updateScreen.enterPuDrNum(PUDr, dNo);
		updateScreen.deleteDTValue();
		jagacyUtil.pressEnter();
		testUtil.setHardWait(2000);
		updateScreen2.enterValuesToUpdateScreen2("", PCS, PK, DES, Wgt);
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		Thread.sleep(2000);
		
		HashMap<String,String> shippingInstructions = new HashMap<String,String>();
		shippingInstructions.put(shipperCode1, shipperInstruction1);
		shippingInstructions.put(shipperCode2, shipperInstruction2);
		freightBillAccessorialChargesandShippingInstructionsScreen.enterShippingInstructions(shippingInstructions);
		Thread.sleep(2000);
		jagacyUtil.pressEnter();
		Thread.sleep(2000);
		System.out.println("Freight bill is updated");
		// Press F1 to exit
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		
		//Logging out and Logging in
		freightBillingMenuScreen.enterValueOptionField("90");
		masterMenuScreen.enterMenuOption("90");
		loginScreen.logon(userName, password);
		commandEntryScreen.enterCommand("CALL FBC870");
		
		//Step#4 : Run the following query :
		//SELECT FHOT,FHPRO,FHCNM,FDCMCL,FDDES FROM FBFILES.FRP001 JOIN FBFILES.FRP002 ON FHOT = FDOT AND FHPRO=FDPRO WHERE FDCMCL = 'CPHONE'
		//AND FHOT =087 AND FHPRO = 9610290; Note: Update FHOT and FHPRO values with newly created Freight Bill number
		//Step#5 : Record output results for below fields: FHOT,FHPRO,FHCNM,FDCMCL,FDDES
		
		String query = "SELECT F1.FHOT, F1.FHPRO, F1.FHCNM, F2.FDCMCL, F2.FDDES FROM FBFILES.FRP001 F1 INNER JOIN FBFILES.FRP002 F2 ON F1.FHOT = F2.FDOT AND F1.FHPRO= F2.FDPRO WHERE F2.FDCMCL = 'CPHONE' AND F1.FHOT ="+originTerminal+" AND F1.FHPRO = "+fbNum.substring(3);
		
		List<String> accountNumbers=sqlDatalist.getDataFromFRP001andFRP002Tables(query);
		System.out.println("size : "+accountNumbers.size());
		
		for(int a=0; a<accountNumbers.size(); a++) {
			System.out.println("Data "+a+" : "+accountNumbers.get(a));
		}
		String otPro = "L11*0"+accountNumbers.get(0)+accountNumbers.get(1)+"*CN~";
		System.out.println("otPro : "+otPro);
		String consigneeNm = "G61*CN*"+accountNumbers.get(2);
		System.out.println("consigneeNm : "+consigneeNm);
		String consigneePhone = "*TE*"+accountNumbers.get(4);
		System.out.println("consigneePhone : "+consigneePhone);
		ArrayList<String> fileData = new ArrayList<String>();
		fileData.add(otPro);
		fileData.add(consigneePhone.trim());
		fileData.add(consigneeNm.trim());
		Thread.sleep(4000);
		
		//Step#6 : Open the following link in IE: \\exlaqa\root\edi\xlator\outbound
		//No need to open in IE
		//Step#7 : Find the folder name with current date (today's) and open it
		//Step#8 : Find the E211 file with newly created freight bill#  and open it
		//Step#9 : Verify above SQL query output results matches the following segment data in E211 file: 
		// OTPRO in  L11* = FHOT,FHPRO Consignee Name and Phone in G61* = FHCNM, FDDES
		//E211 file contains OTPRO, Consignee Name and Cphone data.
		String subFileName = "E211_EFMD_"+fbNum;
		String fileName1 = estesFinalMileTemplateTest.checkOutboudFilecontainsData(subFileName, fileData);
		
		//Step#10 : From the Freight Billing Menu screen, enter   option 3 (Freight bill update)
		freightBillingMenuScreen.enterValueOptionField("3");
		freightBillingMenuScreen.enterValueUserField("test");
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);
		Thread.sleep(2000);
		
		//Step#11 : From Freight Bill screen, enter the newly created Freight Bill# .
		freightBillingMenuScreen.enterFreightBillNumber(fbNum);//(fbNum);
		
		//Step#12 : From Freight Bill UPDATE screen, press Enter, press Enter
		jagacyUtil.pressEnter();
		Thread.sleep(7000);
		jagacyUtil.pressEnter();
		Thread.sleep(7000);
		
		//Step#13 : From screen 3 (Accessorial Charges and Shipping Instructions), on Shipping Instructions section, find code, CPHONE.
	
		freightBillAccessorialChargesandShippingInstructionsScreen.editCPhoneShippingInstruction("8048048004");
		freightBillAccessorialChargesandShippingInstructionsScreen.deleteShipingInstructionCodeX();
		
		//Step#14 : Press Enter
		jagacyUtil.pressEnter();
		Thread.sleep(7000);
		
		//Step#15 : Run the following query : 
		//SELECT FHOT,FHPRO,FHCNM,FDCMCL,FDDES FROM FBFILES.FRP001 JOIN FBFILES.FRP002 ON FHOT = FDOT AND FHPRO=FDPRO WHERE 
		//FDCMCL = 'CPHONE' AND FHOT =087 AND FHPRO = 9610290;
		String query1 = "SELECT FHOT,FHPRO,FHCNM,FDCMCL,FDDES FROM FBFILES.FRP001 JOIN FBFILES.FRP002 ON FHOT = FDOT AND FHPRO=FDPRO WHERE FDCMCL = 'CPHONE' AND FHOT ="+originTerminal+" AND FHPRO = "+fbNum.substring(3);
		List<String> accountNumbers1=sqlDatalist.getDataFromFRP001andFRP002Tables(query1);
		System.out.println("size : "+accountNumbers1.size());
		for(int a=0; a<accountNumbers1.size(); a++) {
			System.out.println("Data "+a+" : "+accountNumbers1.get(a));
		}
		String otPro1 = "L11*0"+accountNumbers1.get(0)+accountNumbers1.get(1)+"*CN~";
		System.out.println("otPro : "+otPro1);
		String consigneeNm1 = "G61*CN*"+accountNumbers1.get(2);
		System.out.println("consigneeNm : "+consigneeNm1);
		String consigneePhone1 = "*TE*"+accountNumbers1.get(4);
		System.out.println("consigneePhone : "+consigneePhone1);
		ArrayList<String> fileData1 = new ArrayList<String>();
		fileData1.add(otPro1);
		fileData1.add(consigneePhone1.trim());
		fileData1.add(consigneeNm1.trim());
		Thread.sleep(4000);
		
		//Step#16 : Open the following link in IE: \\exlaqa\root\edi\xlator\outbound
		//Step#17 : Find the folder name with current date (today's) and open it
		//Step#18 : Find the new E211 file with recently created freight bill# and open it
		//Note: Open the most recent (date&time) E211 file with above FB number
		//Step#19 : Verify above SQL query output results matches following segment data:
		//OTPRO in  L11* = FHOT,FHPRO Consignee Name and Phone in G61* = FHCNM, FDDES
		//E211 file contains OTPRO, Consignee Name and Cphone data that matches with SQL query output results
		estesFinalMileTemplateTest.checkLatestOutboudFilecontainsData(subFileName, fileData1, fileName1);
		
	}
	
	
	


}


