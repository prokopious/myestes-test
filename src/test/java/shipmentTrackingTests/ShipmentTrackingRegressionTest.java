package shipmentTrackingTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import as400TempletTests.TempletTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.DeliveryAppointmentInquirybyTIDscreen;
import jagacyVT.screens.DeliveryProUpdateScreen;
import jagacyVT.screens.EBolConfirmationScreen;
import jagacyVT.screens.EnterPercentageQuarterTrailerScreen;
import jagacyVT.screens.FreightBillInquiryScreen;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LinehaulDispatchScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.MasterMenuScreen;
import jagacyVT.screens.ProsRequiringDeliveryAppointmentScreen;
import jagacyVT.screens.ReserveFreightBillScreen;
import jagacyVT.screens.RoadManifestCheckoutScreen;
import jagacyVT.screens.RoadManifestEntryUpdateScreen;
import jagacyVT.screens.RoadManifestSetupEntryScreen;
import jagacyVT.screens.RoadManifestToBeUnloadedScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import jagacyVT.screens.UpdateScreen3;
import myEstesPages.MyEstesBillOfLadingPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import rateQuotePages.BookTruckloadShipmentPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQuotePage;
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;
import util.SQLDataList;

public class ShipmentTrackingRegressionTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	SQLDataList sqlDataList = new SQLDataList();
	RateQuotePage rateQuotePage = new RateQuotePage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	BookTruckloadShipmentPage bookTruckloadShipmentPage = new BookTruckloadShipmentPage();
	/******************************* TESTS *******************************/

	// check with Dee about Shipment tracking tests.
	/*
	 * Shipment Tracking - Authenticated - Verify the user can view BOL image
	 */

	@Test(enabled = false, priority = 1)

	public void executeQZ_6009() throws InterruptedException {

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com
		myEstesHomePage.clickOnMyEstes();

		// Step 2: Login to My Estes application
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: From My Estes home page click on the following tab: Track
		myEstesHomePage.clickOnTrack();

		// Step 4: From Track page, navigate to Shipment Tracking application
		myEstesHomePage.clickOnShipmentTraking();

		// Step 5: From Search by drop down select Pro number
		shipmentTrackingPage.selectPRONumber();

		// Step 6: From Enter one or more numbers (one per line) search box,
		// enter a valid Pro number with BOL image
		shipmentTrackingPage.enterPORNumber("1710462591");

		// Step 7: Click on Search button
		shipmentTrackingPage.clickOnSearchButton();
		shipmentTrackingPage.validatePROResultNumber("171-0462591");

		// Step 8: Click on the Caret
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.validatePROResultTable(
				"//*[@id=\"content\"]/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[1]");

		// Step 9: From Tracking Results section click on View link to the right
		// of the BOL Number label to view the BOL image
		// NOTE: Cannot validate the image PRO number matches with selected PRO number
		shipmentTrackingPage.clickOnViewBOLImage();

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab

		testUtil.setHardWait(2000);
		shipmentTrackingPage.validateBOLImageIsDisplayed();

		// Step 10: Validate that the Page x of x should be displayed in the top left
		// corner
		shipmentTrackingPage.validateBOLImagePageNumbersAreDisplayed();

		// Step 11: Validate that the following buttons should be displayed:
		// Zoom In Button, Zoom Out Button, Rotate Button, Print Button
		shipmentTrackingPage.validateBOLImagePageZoomInButton();
		shipmentTrackingPage.validateBOLImagePageZoomOutButton();
		shipmentTrackingPage.validateBOLImageRotateButton();
		shipmentTrackingPage.validateBOLImagePrintButton();

	}




	// TC-DP2-456
	/*
	 * Shipment Tracking - Authenticated - Verify Error message displays when
	 * required fields are left blank
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_3245() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();
		shipmentTrackingPage.clickOnSearchBtn();
		shipmentTrackingPage.validateError();
		shipmentTrackingPage.selectBOLNumber();
		shipmentTrackingPage.clickOnSearchBtn();
		shipmentTrackingPage.validateError();
		shipmentTrackingPage.selectPONumber();
		shipmentTrackingPage.clickOnSearchBtn();
		shipmentTrackingPage.validateError();
		shipmentTrackingPage.selectLoadOrderNumber();
		shipmentTrackingPage.clickOnSearchBtn();
		shipmentTrackingPage.validateError();
		shipmentTrackingPage.selectEXLAID();
		shipmentTrackingPage.clickOnSearchBtn();
		shipmentTrackingPage.validateError();
	}

	/* Disabled the test due to a Blocker with the exla qa flow . Reached out to Dee awaiting her response.*/
	/*
    * Authenticated - Verify Appointment Date in preview bar when
    * Appt Date is greater than EDD (Estimated Delivery Date)
    */
	 @Test(enabled = true, priority = 3)

	   public void executeQZ_3287() throws Exception {

		   String userName = "QATSTFRTBL";
	       String password = "qatest2019";
	       String accountNumber = "2485132";
	       String option = "1";
	       String reserveOption = "82";
	       String originTerminal = "005";
	       String destinationTerminal = "037";
	       String user = "Test";
	       String shipperCode = "0500845";
	       String option1 = "2";
	       String option2 = "18";
	       String option3 ="320";

	       String apptDate = testUtil.getBusinessDateShortenedYear("DATE", +6);
	       SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
	       // Getting current date
	       Calendar cal = Calendar.getInstance();
	       // Displaying current date in the desired format
	       String todayDate = sdf.format(cal.getTime());
	       System.out.println(todayDate);
	       String poNum = todayDate + 3287 +testUtil.randomString(2);
	       String bolNum = "1037545540301";

	       SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
	       session.open();
	       testUtil.setHardWait(3000);

	       // Step 1: Reserve Freight bill, follow the steps in QZ-384 and enter the following data
	       // Login to the following application: EXLAQA

	       LoginScreen loginScreen = new LoginScreen(session);
	       loginScreen.enterUserNPasswordCDOC(userName, password);
	       testUtil.setHardWait(5000);
	       JagacyUtil jagacyUtil = new JagacyUtil(session);
	       //Enter 1

	       Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
	       ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
	       ltl38MasterMenuScreen.enterValueOptionField(option);

	       testUtil.setHardWait(3000);

	       // Step 7: From the Freight Billing Menu screen, enter the following values: Option: 82, User: TEST, Terminal = <135>, press ENTER
	       FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
	       freightBillingMenuScreen.enterValueOptionField(reserveOption);
	       freightBillingMenuScreen.enterValueUserField(user);
	       freightBillingMenuScreen.enterTabKey();
	       freightBillingMenuScreen.enterTerminalNumber(originTerminal);

	       // Step 9 - 11: From the Reserve a String of Freight Bills screen, enter the following values: Bills to reserve: 1, Shipper Code: 2791403
	       // and record the pro number
	       ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
	       reserveFreightBillScreen.enterNumberOfFB("1");
	       reserveFreightBillScreen.enterShipperCode(shipperCode);
	       String billNum = reserveFreightBillScreen.recordFBNumber();
	       String fbNum = originTerminal + billNum;
	       System.out.println("Freight Bill No " + fbNum);

	       // Step 12: Press F3 to exit
	       jagacyUtil.pressF3();

	       // Step 13 - 14: From the Freight Billing Menu, enter Option: 2, Terminal: 135 and Press ENTER
	       FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
	       freightBillingMenuScreen.enterValueOptionField(option1);
	       freightBillingMenuScreen.enterValueUserField(user);
	       freightBillingMenuScreen.enterTabKey();
	       freightBillingMenuScreen.enterTerminalNumber(originTerminal);

	       // Step 15: From the Freight Bill Entry/Update screen, Enter required fields
	       freightBillScreen.enterFreightBill(originTerminal, billNum);
	       testUtil.setHardWait(2000);

	       freightBillingMenuScreen.selectShipper();
	       testUtil.setHardWait(3000);

	       UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
	       freightBillUpdateScreen.enterTS("1");
	       freightBillUpdateScreen.enterPcs("3");
	       freightBillUpdateScreen.enterTerms("PPD");
	       freightBillUpdateScreen.enterWgt("3500");
	       freightBillUpdateScreen.enterCons("2485132");
	       freightBillUpdateScreen.enterMasterBlNo(bolNum);
	       freightBillUpdateScreen.enterPONum(poNum);
	       freightBillUpdateScreen.enterPuDrNum("9999999","9");
	       freightBillUpdateScreen.enterCubicFeet("1");

	       // Step 16: Press ENTER to validate entries
	       jagacyUtil.pressEnter();

	       // Step 17: Press ENTER to advance to next entry page
	       jagacyUtil.pressEnter();

	       // Step 18 - 21: On the second page of the UPDATE screen, enter the following values:
	       UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
	      freightBillUpdateScreen2.enterClass("50");
	       freightBillUpdateScreen2.enterPcs2("3");
	       freightBillUpdateScreen2.enterPK("CT");
	       freightBillUpdateScreen2.enterDesc("Cartons");
	       freightBillUpdateScreen2.enterWgt2("3500");
	       testUtil.setHardWait(5000);

	       // Step 19: Press ENTER to validate entries
	       jagacyUtil.pressEnter();

	       // Step 20: Press ENTER to advance to next entry page
	       jagacyUtil.pressEnter();

	       // Step 21 - 22: Press ENTER
	       jagacyUtil.pressEnter();

	       // Step 23: Press F1 to exit
	       jagacyUtil.pressF1();

	     //Step -45 seect Option to update Freight bill
	       FreightBillingMenuScreen freightBillingmenuScreen = new FreightBillingMenuScreen(session);
	     // freightBillingmenuScreen.enterFreightBillMenuOption(option3, user, destinationTerminal); //Commented and added below line
	        freightBillingmenuScreen.enterValueOptionField(option3);
			freightBillingmenuScreen.enterTabKey();
			freightBillingmenuScreen.enterTabKey();
			testUtil.setHardWait(2000);
			freightBillingmenuScreen.enterTabKey();
			freightBillingmenuScreen.enterTerminalNumber(destinationTerminal);
			testUtil.setHardWait(2000);

	       //STEP-46: Press F8 in Pros Requiring Delivery Appointment screen
	       JagacyUtil jagacyuTil = new JagacyUtil(session);
	       jagacyuTil.pressF8();
	       testUtil.setHardWait(5000);

	       //STEP-47 : Enter Pro number in Delivery Appointment Inquiry by TID screen and press Enter

	       DeliveryAppointmentInquirybyTIDscreen deliveryAppointmentInquiryByTIDscreen = new DeliveryAppointmentInquirybyTIDscreen(session);

	       deliveryAppointmentInquiryByTIDscreen.enterFreightBill1(originTerminal, billNum);
	       testUtil.setHardWait(5000);

	       jagacyuTil.pressF1();
	       testUtil.setHardWait(5000);

	       //Step-48 & 49- Find pro number and select option S

	       ProsRequiringDeliveryAppointmentScreen prosRequiringDeliveryAppointmentScreen = new ProsRequiringDeliveryAppointmentScreen(session);
	       prosRequiringDeliveryAppointmentScreen.searchProNumber(accountNumber);
	       prosRequiringDeliveryAppointmentScreen.selectProNumber( originTerminal,billNum, "S");
	       testUtil.setHardWait(3000);

	       //STEP-50 -Enter values in Delivery Appointment screen
	       DeliveryProUpdateScreen deliveryProUpdateScreen = new DeliveryProUpdateScreen(session);

	       //String ApptDate = testUtil.getBusinessDate("DATE", +6); //Modified to
	       String ApptDate = testUtil.getBusinessDateShortenedYear("DATE", +6);
	       System.out.println("Date - "+ApptDate);
	       testUtil.setHardWait(5000);

	       deliveryProUpdateScreen.enterApptDate(ApptDate);
	       deliveryProUpdateScreen.enterTimeRange("1330","2230");
	       deliveryProUpdateScreen.enterReason("CR");
	       testUtil.setHardWait(3000);
	       deliveryProUpdateScreen.enterName("EITQA");
	       deliveryProUpdateScreen.enterPhoneNum("234","106","8701","254");

	       jagacyuTil.pressEnter();
	       testUtil.setHardWait(5000);
	       //Step 51 - Press F1
	       jagacyuTil.pressF1();

	       prosRequiringDeliveryAppointmentScreen.selectProNumber( originTerminal, billNum, "S");
	       // Step 22: Press Enter
	       jagacyuTil.pressEnter();
	       testUtil.setHardWait(5000);

	       // Step 23: Press F1
	       jagacyuTil.pressF1();

	       // Step 24: Press F1
	       jagacyuTil.pressF1();

	       // Step 25: Access the following URL for My Estes:
	       driver.get(url3);

	       // Step 26: Login with smokenat credentials
	       shipmentTrackingPage.clickOnMyEstes();
	       shipmentTrackingPage.clickOnLogin();
	       myEstesLoginPage.enterUserName(username1);
	       myEstesLoginPage.enterPassword(password1);
	       myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

	       // Step 27: From My Estes home page hover mouse over the following menu: Track
	       myEstesHomePage.clickOnTrack();

	       // Step 28: From Track menu, click on Shipment Tracking
	       myEstesHomePage.clickOnShipmentTraking();

	       // Step 29: From the Tracking page, select Purchase Order Number from the Search by drop down
	       shipmentTrackingPage.clickSearchBy();
	       shipmentTrackingPage.clickSearchByPO();

	       // Step 30: From the Search box, enter a valid enter Purchase Order Number created
	       shipmentTrackingPage.enterPORNumber(poNum);
	       testUtil.setHardWait(20000);

	       // Step 31: Click on the SEARCH button
	       shipmentTrackingPage.clickOnSearchButton();
	       testUtil.setHardWait(10000);

	       // Step 32: Validate the Preview Bar populated with *Appointment Date* in the Estimated Delivery Date Column and other freight data
	       shipmentTrackingPage.verifyPickedUpStatus();
	       shipmentTrackingPage.verifyPreviewBarProNum(fbNum);
	       shipmentTrackingPage.verifyPreviewBarBolNum(bolNum);
	       shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

	       apptDate = testUtil.getBusinessDate("DATE", +6);
	       shipmentTrackingPage.verifyEstimatedDeliveryDate(apptDate);

	       // Step 33: Click the Expand All button
	       shipmentTrackingPage.clickOnShipmentStatusCaret();
	       shipmentTrackingPage.verifyAppointmentDate1(apptDate);
	       shipmentTrackingPage.verifyAppointmentTime1("1:30 PM");
	       shipmentTrackingPage.verifyAppointmentStatus1("Customer requested appointment");
	       shipmentTrackingPage.verifyValuePRONumber();
	       shipmentTrackingPage.verifyValueBOLNumber();
	       shipmentTrackingPage.verifyValuePONumber();
	       shipmentTrackingPage.verifyValueWRCertificate();
	       shipmentTrackingPage.verifyValuePieces();
	       shipmentTrackingPage.verifyValueWeight();
	       shipmentTrackingPage.verifyValuePickupDate();
	       shipmentTrackingPage.verifyValueTransitDays();
	       shipmentTrackingPage.verifyValueShipper();
	       shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
	       shipmentTrackingPage.verifyValueConsignee();
	       shipmentTrackingPage.verifyValueDeliveryReceipt();
	       shipmentTrackingPage.verifyValueDestinationTerminal();
	       shipmentTrackingPage.verifyValueQuestions();
	       shipmentTrackingPage.verifyValueTerminalPhone();

	   }


	// TC-DP2-355
	/**
	 *
	 * @author Nithya- Created on 1/18/2022
	 */

	/*
	 * Shipment Tracking - Auth - Verify error message(s) should be displayed on
	 * invalid data entry and not Party-To search by any reference criteria
	 */

	@Test(enabled = true, priority = 4)
	public void executeQZ_3257() throws Exception {

		String invalidRACPNo3 = "RACP-123333", validPRONo2 = "001-9578647", invalidPRONo1 = "006-238987",
				invalidPURNo2 = "PUR007123";
		String errMsg = "Are you trying to track a pickup request? Shipment details are not yet available for Pickup Request Number ";

		// Step-1 : Access My Estes:
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step-2 : Login to My Estes application with the following credentials:
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		// Step-3 : From My Estes home page hover mouse over the following menu:Track
		myEstesHomePage.clickOnTrack();

		// Step-4 and 5 : From Track menu, click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();

		// Step-6 : From the Tracking page, select Pro Number from the Search by drop
		// down [Its Default selection]

		// Step-7 : From Enter one or more numbers (one per line) box, enter the
		// following invalid data:
		shipmentTrackingPage.enterPORNumber(invalidPRONo1);

		// Step-8 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-8 : Validate the following error message: Not found or tracking
		// information unavailable: 006238987.
		shipmentTrackingPage.validateErrorMessage(invalidPRONo1.replace("-", ""));

		// Step-9 : From the Search dropdown select Bill of Lading Number
		shipmentTrackingPage.selectBOLNumber();

		// Step-10 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-10 : Validate the following error message: Not found or tracking
		// information unavailable: 006238987.
		shipmentTrackingPage.validateErrorMessage(invalidPRONo1);

		// Step-11 : From the Search dropdown select Pickup Request Number
		shipmentTrackingPage.selectPickupRequestNumber();

		// Step-12 : From Enter one or more numbers (one per line) box, enter the
		// following invalid data:
		shipmentTrackingPage.enterPORNumber(invalidPURNo2);

		// Step-13 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-13 : Validate the following error message: Not found or tracking
		// information unavailable: PUR007123.
		//shipmentTrackingPage.validateErrorMessage(invalidPURNo2); //Modified to validate errMsg
		shipmentTrackingPage.validateErrorMessage(invalidPURNo2, errMsg);

		// Step-14 : From the Search dropdown select Purchase Order Number
		shipmentTrackingPage.selectPONumber();

		// Step-15 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-15 : Validate the following error message: Not found or tracking
		// information unavailable: PUR007123.
		shipmentTrackingPage.validateErrorMessage(invalidPURNo2);

		// Step-16 : From the Search dropdown select Interline Pro Number
		shipmentTrackingPage.selectInterlinePRONumber();

		// Step-17 : From Enter one or more numbers (one per line) box, enter the
		// following invalid data:RACP-123333
		shipmentTrackingPage.enterPORNumber(invalidRACPNo3);

		// Step-18 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-18 : Validate the following error message: Not found or tracking
		// information unavailable: RACP-123333.
		shipmentTrackingPage.validateErrorMessage(invalidRACPNo3);

		// Step-19 : From the Search dropdown select Load Order Number
		shipmentTrackingPage.selectLoadOrderNumber();

		// Step-20 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-20 : Validate the following error message: Not found or tracking
		// information unavailable: RACP-123333.
		shipmentTrackingPage.validateErrorMessage(invalidRACPNo3);

		// Step-21 : From the error message bar, click the x
		shipmentTrackingPage.clickTheX();

		// Step-22 : From the Search dropdown select Optional EXLA-ID Number
		shipmentTrackingPage.selectEXLAID();

		// Step-23 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step-23 : Validate the following error message:Not found or tracking
		// information unavailable: RACP-123333.
		shipmentTrackingPage.validateErrorMessage(invalidRACPNo3);

		// Step-24 : Remain log into My Estes

		// Step-25 : Create freight bill data to test party to error, execute test case
		// QZ-6000 until step 8

		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "171";
		String user = "Test";
//	        String shipperCode = "7178618";
		String shipperCode = "5608430";
		String option1 = "2";

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		System.out.println(todayDate);
		String bolNum1 = todayDate + "1";

		String poNum1 = todayDate + "PO1";

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		// Step 1: Reserve Freight bill, follow the steps in QZ-384 and Complete Regular
		// Freight bill
		// Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		/*
		 * Added new method to avoid the failure due to user login and to avoid the
		 * manual step to press enter when we see display comments screen.
		 */
		ltl38MasterMenuScreen.verifyScreenTitle();

		ltl38MasterMenuScreen.enterValueOptionField(option);

		testUtil.setHardWait(3000);

		// ****FB 1****//
		// From the Freight Billing Menu screen, enter the following values: Option: 82,
		// User: TEST, Terminal = <171>, press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the following values
		// and record the pro number
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum1 = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum1);

		String validPro = originTerminal + "-" + billNum;
		// Press F3 to exit
		jagacyUtil.pressF3();

		// From the Freight Billing Menu, enter Option: 2, Terminal: 171 and Press ENTER
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 2: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("4");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3500");
		freightBillUpdateScreen.enterCons("5612290");
		freightBillUpdateScreen.enter3Pt("7178618");
		freightBillUpdateScreen.enterMasterBlNo(bolNum1);
		freightBillUpdateScreen.enterPONum(poNum1);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Step 3: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 4: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 5: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("4");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("3500");
		testUtil.setHardWait(5000);

		// Step 6: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 7: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 8: Press ENTER
		jagacyUtil.pressEnter();

		// Step 9: Press F1 to exit
		jagacyUtil.pressF1();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		driver.get(url3);
		shipmentTrackingPage.selectPRONumber();
		// Step-26 : Navigate back to My Estes, from the search by dropdown select PRO
		// Number and enter the pro created in step 24
		shipmentTrackingPage.enterPORNumber(fbNum1);

		// Step-27 : Click on Search button
		shipmentTrackingPage.clickOnSearchButton();

		/* Checking with Dee as error is not displayed */
		// Step-27 : Validate the following error message: Not found or tracking
		// information unavailable: *xxxxxxxxx*, displayed value = pro created in step
		// 24
		// shipmentTrackingPage.validateErrorMessage(fbNum1);

		// Step-28 : Click on the clear button
		// shipmentTrackingPage.clickClear();

		// Step-29: Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(4000);
		myEstesHomePage.clickOnLogout();

		myEstesLoginPage.clickOnLogoutConfirmButton();

		// Step 31: Access My Estes
		// driver.get(url);

		// Login to MyEstes
		// myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		// myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

		// Step 32: From My Estes home page hover mouse over the following menu: Track
		myEstesHomePage.clickOnTrack();
		// Step 33: From Track menu, click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();

		// Step 34 & 37: From the Search box enter a valid 10 digit freight bill number
		shipmentTrackingPage.enterMultiplePRONumber(fbNum1, invalidPRONo1);
		testUtil.setHardWait(2000);

		// Step 38: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(2000);

		// Step-38 : Not found or tracking information unavailable: 006-1234567.
		shipmentTrackingPage.validateErrorMessage(invalidPRONo1.replace("-", ""));

		// Step-38 : Validate results return for the valid pro number and data should
		// match the freight bill data.
		// shipmentTrackingPage.verifyDataReturnedInPreviewBar();

		// Step 38: Click the Expand All button
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 15: Validate the Milestone Data
		shipmentTrackingPage.verifyMilestone("Picked Up");

		// Step 16: Validate data in the *Shipment Details* should match the data fields
		// for the freight bill
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueBOLNumber();
		shipmentTrackingPage.verifyValuePONumber();
		shipmentTrackingPage.verifyValueWRCertificate();
		shipmentTrackingPage.verifyValuePieces();
		shipmentTrackingPage.verifyValueWeight();
		shipmentTrackingPage.verifyValuePickupDate();
		shipmentTrackingPage.verifyValueTransitDays();
		shipmentTrackingPage.verifyValueShipper();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueConsignee();
		shipmentTrackingPage.verifyValueDeliveryReceipt();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueQuestions();
		shipmentTrackingPage.verifyValueFreightCharges();

	}


	// TC-DP2-303
	/*
	 * Shipment Tracking - Authenticated - Verify the user with alphanumeric account
	 * number can search for shipments by PRO Number.
	 */
	@Test(enabled = true, priority = 5)
	public void executeQZ_3249()
			throws Exception {

		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "001";
		String user = "Test";
		String shipperCode = "C123456";
		String option1 = "2";

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		System.out.println(todayDate);
		String bolNum = todayDate + "3249-1" +testUtil.randomString(2); //Modified to get unique bol#

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		// Step 1 : Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
		ltl38MasterMenuScreen.enterValueOptionField(option);

		testUtil.setHardWait(3000);
		// From the Freight Billing Menu screen, enter the following values:
		// Option: 82, User: TEST, Terminal = <006>, press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the
		// following values: Bills to reserve: 1, Shipper Code: 2791403
		// and record the pro number
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// Step 2: From the Freight Billing Menu, enter Option: 2, Terminal: 006
		// and Press ENTER
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("1");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3500");
		freightBillUpdateScreen.enterCons("0113210");
		freightBillUpdateScreen.enterMasterBlNo(bolNum);
		freightBillUpdateScreen.enterPONum("SL1014401101");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Step 3: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 4: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("1");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("3500");
		testUtil.setHardWait(5000);

		// Step 5: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		testUtil.setHardWait(3000);

		// Step 6: Press ENTER
		jagacyUtil.pressEnter();
		testUtil.setHardWait(3000);
		// Press ENTER
		jagacyUtil.pressEnter();

		testUtil.setHardWait(3000);

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// Step 7: Access Shipment Tracking app
		driver.get(url3);

		// Step 8: Login with smokelocal credentials
		shipmentTrackingPage.clickOnMyEstes();
		shipmentTrackingPage.clickOnLogin();
		myEstesLoginPage.enterUserName("CUSTMAST");
		myEstesLoginPage.enterPassword("CUSTMAST");
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

		// Step 9: From My Estes home page hover mouse over the following menu: Track
		myEstesHomePage.clickOnTrack();
		// Step-10 : From Track menu, click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();
		shipmentTrackingPage.clickSearchBy();
		// Step 11: From the Search box enter a valid 10 digit freight bill number
		shipmentTrackingPage.selectBOLNumber();

		// Step 12: From the Search box , enter the Bill of Lading number associated
		// with alphanumeric account number created
		shipmentTrackingPage.enterBOLNumber(bolNum);
		testUtil.setHardWait(20000);

		// Step 13: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(10000);

		// Step 14: Validate data in the *Preview Bar*
		shipmentTrackingPage.verifyStatus("Picked Up");
		shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

		// Step 15: Click the Expand All button
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 15: Validate the Milestone Data
		shipmentTrackingPage.verifyMilestone("Picked Up");

		// Step 16: Validate data in the *Shipment Details* should match the data fields
		// for the freight bill
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueBOLNumber();
		shipmentTrackingPage.verifyValuePONumber();
		shipmentTrackingPage.verifyValueWRCertificate();
		shipmentTrackingPage.verifyValuePieces();
		shipmentTrackingPage.verifyValueWeight();
		shipmentTrackingPage.verifyValuePickupDate();
		shipmentTrackingPage.verifyValueTransitDays();
		shipmentTrackingPage.verifyValueShipper();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueConsignee();
		shipmentTrackingPage.verifyValueDeliveryReceipt();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueQuestions();
		shipmentTrackingPage.verifyValueFreightCharges();

		// Step 17: Click on Shipment History caret dropdown
		shipmentTrackingPage.clickOnShipmentHistoryCaret2();
		testUtil.setHardWait(4000);

		// Step 17: : Shipment picked up is displayed
		shipmentTrackingPage.verifyShipmentPickedupStatus();
		// Validate shipment history data is returned and match time stamp of when the
		// action(s)
		shipmentTrackingPage.verifyTimeZone("EST");
		testUtil.setHardWait(2000);
		// Step 18: Click the Collapse All button
		shipmentTrackingPage.clickOnCollapseButton();
		testUtil.setHardWait(2000);

		// Step-19: Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	// TC-DP2-1008
	/*
	 * Welcome page - Verify the user is able to search for shipments by entering a
	 * PRO Number in the Search my shipments field
	 */

	@Test(enabled = false, priority = 6)

	public void executeQZ_3253()
			throws InterruptedException {
		// String proNumber = "171-8000004";
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		myEstesRecentShipmentsPage.ClickOnRSConsigneeRadioButton();
		String proNumber = myEstesRecentShipmentsPage.getPRONumForFirstRow();
		myEstesHomePage.enterPRONumber(proNumber);
		testUtil.setHardWait(2000);
		myEstesHomePage.clickOnProNumber(proNumber);
		shipmentTrackingPage.verifySearchFieldPRONumber(proNumber);
	}

	/*
	 * Shipment Tracking - Unauthenticated - Verify when required fields are left
	 * blank error message displays
	 */
	/*
	 * qz-3111 Locally we had qz3111 but it was qz7148 on JIRA.So it is update to
	 * reflect JIRA
	 */

	@Test(enabled = true, priority = 7)
	public void executeQZ_7148() throws InterruptedException {

		myEstesHomePage.clickOnTrack();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnShipmentTraking();
		shipmentTrackingPage.selectPRONumber();
		shipmentTrackingPage.clickOnSearchBtn();

		shipmentTrackingPage.validateError();

		shipmentTrackingPage.selectBOLNumber();
		shipmentTrackingPage.clickOnSearchBtn();

		shipmentTrackingPage.validateError();

		shipmentTrackingPage.selectPONumber();
		shipmentTrackingPage.clickOnSearchBtn();

		shipmentTrackingPage.validateError();

		shipmentTrackingPage.selectInterlinePRONumber();
		shipmentTrackingPage.clickOnSearchBtn();

		shipmentTrackingPage.validateError();

		shipmentTrackingPage.selectLoadOrderNumber();
		shipmentTrackingPage.clickOnSearchBtn();

		shipmentTrackingPage.validateError();

		shipmentTrackingPage.selectEXLAID();
		shipmentTrackingPage.clickOnSearchBtn();

		shipmentTrackingPage.validateError();

	}

	/*
	 * Shipment Tracking - Authenticated - Verify the user can search for shipments
	 * by Bill of Lading Number from Home Page Shipment Tracking Widget.
	 */
	@Test(enabled = false, priority = 8)

	public void executeQZ_7531()
			throws InterruptedException {
		String BOLNum = "8997242";
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();
		shipmentTrackingPage.selectBOLNumber();
		shipmentTrackingPage.enterBOLNumber(BOLNum);
		shipmentTrackingPage.clickOnSearchButton();
		shipmentTrackingPage.verifyHeaderPRONumber();
		shipmentTrackingPage.verifyHeaderPickupDate();
		shipmentTrackingPage.verifyHeaderBOLNumber();
		shipmentTrackingPage.verifyHeaderPONumber();
		shipmentTrackingPage.verifyHeaderStatus();
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueBOLNumber();
		shipmentTrackingPage.verifyValuePONumber();
		shipmentTrackingPage.verifyValueStatus();
		shipmentTrackingPage.verifyValueWRCertificate();
		shipmentTrackingPage.verifyValuePieces();
		shipmentTrackingPage.verifyValueWeight();
		shipmentTrackingPage.verifyValueShipper();
		shipmentTrackingPage.verifyValuePickupDate();
		shipmentTrackingPage.verifyValueDeliveryDate();
		shipmentTrackingPage.verifyValueDeliveryTime();
		shipmentTrackingPage.verifyValueReceivedBy();
		shipmentTrackingPage.verifyValueConsignee();
		shipmentTrackingPage.verifyValueDeliveryReceipt();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueFirstDeliveryDate();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueTerminalFax();
		shipmentTrackingPage.verifyValueFreightCharges();

	}
	/*
	 * Shipment Tracking - Unauthenticated - Verify the user can search for
	 * shipments by Load Order Number from Home Page Shipment Tracking Widget
	 */

	@Test(enabled = false, priority = 9)

	public void executeQZ_6012()
			throws InterruptedException {

		String BOLNum = "3103104";

		myEstesHomePage.selectTrackingType("Load Order Number");
		myEstesHomePage.enterNumber(BOLNum);
		myEstesHomePage.clickOnTrackNow();
		shipmentTrackingPage.verifyHeaderPRONumber();
		shipmentTrackingPage.verifyHeaderStatus();
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueStatus();
		shipmentTrackingPage.verifyValueDeliveryDate();
		shipmentTrackingPage.verifyValueDeliveryTime();
		shipmentTrackingPage.verifyValueReceivedBy();
		shipmentTrackingPage.verifyValueAppointmentDate();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueQuestions();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueTerminalFax();
		shipmentTrackingPage.verifyRequestAddInfoLink();
		shipmentTrackingPage.clickOnMyEstesLink();
		shipmentTrackingPage.verifyMyEstesLoginPage();

		for (int i = 0; i <= 2; i++) {
			try {
				driver.navigate().back();

				break;
			} catch (Exception e) {
				System.out.println("Still throwing Stalemate Reference Exception");
			}
		}
		testUtil.setHardWait(1000);
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.clickOnRequestAddInfo();
		shipmentTrackingPage.verifyRequestAddInfoPage();

	}

	/*
	 * Unauthenticated - Home Page Track a Shipment Widget - Verify the user can
	 * track shipment(s) by Interline Pro Number and EDD range is return when
	 * Residential flag is Y
	 */

	@Test(enabled = true, priority = 10)
	public void executeQZ_6013()
			throws Exception {

		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "037";
		String user = "Test";
		String shipperCode = "3732590";
		String option1 = "2";
		String trackingType = "INTERLINE-PRO"; //Added

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		System.out.println(todayDate);

		// Step 1 : Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
		ltl38MasterMenuScreen.enterValueOptionField(option);

		testUtil.setHardWait(3000);
		// From the Freight Billing Menu screen, enter the following values:
		// Option: 82, User: TEST, Terminal = <006>, press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the
		// following values: Bills to reserve: 1, Shipper Code: 2791403
		// and record the pro number
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// Step 2: From the Freight Billing Menu, enter Option: 2, Terminal: 006
		// and Press ENTER
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("3");
		freightBillUpdateScreen.enterPcs("4");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3500");
		freightBillUpdateScreen.enterCons("7178618");
		freightBillUpdateScreen.enter3Pt("5068692");
		freightBillUpdateScreen.enterMasterBlNo("2020037700-1");
		freightBillUpdateScreen.enterPONum("SL1014202101");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");
		freightBillUpdateScreen.enterILTo("RACP");
		//freightBillUpdateScreen.enterPro("RACP20220110");
		String proNo = "RACP20210603"+testUtil.randomString(2);
		freightBillUpdateScreen.enterPro(proNo);
		String date = todayDate;
		System.out.println("date is " + date);
		freightBillUpdateScreen.enterDate(date);

		// Step 3: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 4: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("4");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("3500");
		testUtil.setHardWait(5000);

		// Step 18: Press ENTER to validate entries
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 19: On the third page of the UPDATE screen, make the following entries
		// under Accessorial Charges: HD
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");

		// Step 20: Press ENTER
		jagacyUtil.pressEnter();

		// Step 21: Press F1 to exit
		jagacyUtil.pressF1();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// Step 7: Access Shipment Tracking app
		//driver.get(url3);
		driver.get(url);

		// Step 11: From the Search box enter a valid 10 digit freight bill number
		//shipmentTrackingPage.selectInterlinePRONumber(); //Modified as per test case
		myEstesHomePage.selectTracking(trackingType);

		// Step 12: From the Search box , enter the Bill of Lading number associated
		// with alphanumeric account number created
		//shipmentTrackingPage.enterBOLNumber("RACP20210603");
		myEstesHomePage.enterNumber(proNo);
		testUtil.setHardWait(20000);

		// Step 13: Click on the SEARCH button
		//shipmentTrackingPage.clickOnSearchButton();
		myEstesHomePage.clickOnTrackNow();
		testUtil.setHardWait(10000);

		// Validate that url in the address bar
		String ActualUrl = driver.getCurrentUrl();
		//String ExpUrl = "https://estes-express-uat.estesinternal.com/myestes/tracking/?type=INTERLINE-PRO&query=RACP20210603";
		String ExpUrl = "https://estes-express-uat.estesinternal.com/myestes/tracking/shipments?type=INTERLINE-PRO&query="+proNo;

		Assert.assertEquals(ActualUrl, ExpUrl);

		// Step 14: Validate data in the *Preview Bar*

		shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

		// Step 15: Click the Expand All button
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 15: Validate the Milestone Data
		shipmentTrackingPage.verifyMilestone("Picked Up");

		// Step 16: Validate data in the *Shipment Details* should match the data fields
		// for the freight bill
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueBOLNumber();
		shipmentTrackingPage.verifyValuePONumber();
		shipmentTrackingPage.verifyValueWRCertificate();
		shipmentTrackingPage.verifyValuePieces();
		shipmentTrackingPage.verifyValueWeight();
		shipmentTrackingPage.verifyValuePickupDate();
		shipmentTrackingPage.verifyValueTransitDays();
		shipmentTrackingPage.verifyValueShipper();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueConsignee();
		shipmentTrackingPage.verifyValueDeliveryReceipt();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueQuestions();
		//shipmentTrackingPage.verifyValueFreightCharges();

		// Step 17: Click on Shipment History caret dropdown
		shipmentTrackingPage.clickOnShipmentHistoryCaret2();
		testUtil.setHardWait(4000);

		// Step 17: : Shipment picked up is displayed
		shipmentTrackingPage.verifyShipmentPickedupStatus();
		// Validate shipment history data is returned and match time stamp of when the
		// action(s)
		//shipmentTrackingPage.verifyTimeZone("EST");
		testUtil.setHardWait(2000);
		// Step 18: Click the Collapse All button
		shipmentTrackingPage.clickOnCollapseButton();
		testUtil.setHardWait(2000);

		// Step-19: Logoff and close browser  //Commented as this is not in test steps - unauthenticated user - logout not necessary
		/*myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();*/

	}


	/**
	 * 
	 * this test test has automaton review label- it needs to be update
	 * 
	 * Shipment Tracking - Unauthenticated - Verify the user can search for
	 * shipments by Load Order Number from Home Page Shipment Tracking
	 * @throws Exception 
	 */
	
	
	@Test(enabled = false, priority = 11) // To be reviewed

	public void executeQZ_9302()
			throws Exception {
		
		SessionVt session= null;
		String userName= "QATSTFRTBL";
		String passWord= "qatest2019";
		String name= "myJagacyVT";
		String shiperCode="3732590";
		String reserveOption="82";
		String user="Test";
		String originTerminal = "037";
		String terminal = "dec-vt220";
		
		session= new SessionVt(name, "exlaqa", terminal);
		session.open();
		Thread.sleep(2000);

		LoginScreen login= new LoginScreen(session);
		login.enterUserNPasswordCDOC(userName, passWord);
		
	
		MasterMenuScreen masterMenuScreen = new MasterMenuScreen(session);
		masterMenuScreen.verifyScreenTitle();
		
		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL FBC870");
		
		jagacy.util.JagacyUtil jagacyUtil= new JagacyUtil(session);
		
		//Step 5: Press enter
		jagacyUtil.pressEnter();
		
		//Step 6:From the Freight Billing Menu screen, enter the following values:
		 
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen= new 	jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		
		//Step : press Enter
		jagacyUtil.pressEnter();
		
		//Step 8: From the Reserve a String of Freight Bills screen, enter the following values
		
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(7000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(shiperCode);
		
		//Step 9: press enter
		
		jagacyUtil.pressEnter();
		
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);
		
		/** this part of the test is for QZ-9302 */
		
		//Step 10: press F3 (exit frome reserve to friehgt billing menu
		jagacyUtil.pressF3();
		

		//Step 2: Complete Regular Freight bill, follow the steps in QZ-387 and enter the following data:
		// *Note* Cart To (step 7), codes in (step 13) not needed.
		
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("4");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3500");
		
		freightBillUpdateScreen.enterCons("7178618");
		freightBillUpdateScreen.enterPONum("1014202101");
		freightBillUpdateScreen.enterPuDrNum("5004", "1");
		freightBillUpdateScreen.enterCubicFeet("1");
		freightBillUpdateScreen.enter3Pt("5068692");
		
		//Step 3: Press ENTER to advance to next entry page Enter
		jagacyUtil.pressEnter();
		
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("4");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("Cartons");
		freightBillUpdateScreen2.enterWgt2("3500");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		
		jagacyUtil.pressF1();
		
		
		
		String EXLAIDNum = "9750423";

		
		myEstesHomePage.selectTrackingType("Optional EXLA-ID");
		myEstesHomePage.enterNumber(EXLAIDNum);
		myEstesHomePage.clickOnTrackNow();

		shipmentTrackingPage.verifyHeaderPRONumber();
		shipmentTrackingPage.verifyHeaderStatus();

		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueStatus();

		shipmentTrackingPage.verifyValueDeliveryDate();
		shipmentTrackingPage.verifyValueDeliveryTime();
		shipmentTrackingPage.verifyValueReceivedBy();
		shipmentTrackingPage.verifyValueAppointmentDate();

		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyDestinationTerminal();
		shipmentTrackingPage.verifyContactUsLink();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueTerminalFax();
		shipmentTrackingPage.verifyMyEstesLink();
		shipmentTrackingPage.verifyRequestAddInfoLink();
		shipmentTrackingPage.clickOnMyEstesLink();
		shipmentTrackingPage.verifyMyEstesLoginPage();
		driver.navigate().back();
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.clickOnRequestAddInfo();
		shipmentTrackingPage.verifyRequestAddInfoPage();

	}

	/**
	 * @author Harish
	 * @throws Exception
	 *
	 * Shipment Tracking - Verify SoapUI web service request returns a response and the data is correct
	 * SOAPUI
	 */

	@Test(enabled = true, priority = 12)
	public void executeQZ_9304()
			throws Exception {

		String pro = null;
		String bol = null;
		String po = null;
		String pickUpDate = null;
		String weight = null;
		String pieces = null;
		String destinationTerminal = null;
		String freightCharges = null;
		String shipper = null;
		String shipperAdd = null;
		String shipperCity = null;
		String shipperstateProvince = null;
		String shipperpostalCode = null;
		String consignee = null;
		String consigneeAdd = null;
		String consigneeCity = null;
		String consigneestateProvince = null;
		String consigneepostalCode = null;

		String baseURI = "https://apitest.estes-express.com/ws/estesrtshipmenttracking.base.ws.provider.soapws:EstesShipmentTracking/estesrtshipmenttracking_base_ws_provider_soapws_EstesShipmentTracking_Port";
		// https://apitest.estes-express.com/ws/estesrtshipmenttracking.base.ws.provider.soapws:EstesShipmentTracking/estesrtshipmenttracking_base_ws_provider_soapws_EstesShipmentTracking_Port

		// Step 1 to 13:
		Response response = given().auth().basic("crowley3", "Crowley1").body(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"https://api.estes-express.com/ws/tools/shipment/tracking/v1.1/\">\r\n"
						+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <v1:shipmentTracking>\r\n"
						+ "         <search>         <requestID>1312020</requestID>           <pro>0244841546</pro>\r\n"
						+ "         </search>\r\n" + "      </v1:shipmentTracking>\r\n" + "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>")
				.when().post(baseURI).then().log().all().statusCode(200).extract().response();


		System.out.println("Response for Search By Pro asString: " + response.asString());
		System.out.println("---------------");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();
			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(response.asString())));

			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("shipments");

//	            for (int i = 0; i < nodeList.getLength(); ++i) {
			Node node = nodeList.item(1);
			System.out.println("\nNode Name :" + node.getNodeName());
			NodeList v = node.getChildNodes();
			System.out.println(v);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element tElement = (Element) node;
				pro = tElement.getElementsByTagName("pro").item(0).getTextContent();
				bol = tElement.getElementsByTagName("bol").item(0).getTextContent();
				po = tElement.getElementsByTagName("po").item(0).getTextContent();
				pickUpDate = tElement.getElementsByTagName("pickupDate").item(0).getTextContent();
				weight = tElement.getElementsByTagName("weight").item(0).getTextContent();
				pieces = tElement.getElementsByTagName("pieces").item(0).getTextContent();
				destinationTerminal = tElement.getElementsByTagName("number").item(0).getTextContent();
				freightCharges = tElement.getElementsByTagName("freightCharges").item(0).getTextContent();

				shipper = tElement.getElementsByTagName("name").item(0).getTextContent();
				shipperAdd = tElement.getElementsByTagName("line1").item(0).getTextContent();
				shipperCity = tElement.getElementsByTagName("city").item(0).getTextContent();
				shipperstateProvince = tElement.getElementsByTagName("stateProvince").item(0).getTextContent();
				shipperpostalCode = tElement.getElementsByTagName("postalCode").item(0).getTextContent();

				consignee = tElement.getElementsByTagName("name").item(1).getTextContent();
				consigneeAdd = tElement.getElementsByTagName("line1").item(1).getTextContent();
				consigneeCity = tElement.getElementsByTagName("city").item(1).getTextContent();
				consigneestateProvince = tElement.getElementsByTagName("stateProvince").item(1).getTextContent();
				consigneepostalCode = tElement.getElementsByTagName("postalCode").item(1).getTextContent();

				System.out.println("Pro: " + pro);
				System.out.println("BOL: " + bol);
				System.out.println("PO: " + po);
				System.out.println("pickupDate: " + pickUpDate);
				System.out.println("weight: " + weight);
				System.out.println("pieces: " + pieces);
				System.out.println("destination terminal: " + destinationTerminal);
				System.out.println("freightCharges: " + freightCharges);
				System.out.println("Shipper name: " + shipper);
				System.out.println("Shipper Address: " + shipperAdd);
				System.out.println("shipperCity: " + shipperCity);
				System.out.println("Shipper stateProvince: " + shipperstateProvince);
				System.out.println("Shipper postalCode: " + shipperpostalCode);
				System.out.println("consignee Name: " + consignee);
				System.out.println("Consignee Address: " + consigneeAdd);
				System.out.println("Consignee City: " + consigneeCity);
				System.out.println("Consignee State: " + consigneestateProvince);
				System.out.println("Consignee postalCode: " + consigneepostalCode);

			}
//	            }

		} catch (Exception e) {
			System.out.println(e);
		}

		// Step 14:Log into an *EXLAQA iSeries* session. Note: use your credentials
		// Step 15:Enter *call XXC870* in the command line and press enter.
		String originTerminal = "024";
		String user = "Test";
		String userName = "QATSTFRTBL";
		String password = "QATEST2019";
		String option = "1";
		String proNum = pro.substring(3);

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		// Log into the iSeries QA environment : EXLAQA
		LoginScreen login = new LoginScreen(session);
		login.enterUserNPasswordCDOC(userName, password);

		// Step 16: From the LTL/38 Master Menu,Select option *1 - Freight billing menu*
		// and press enter
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		ltl38MasterMenuScreen.verifyScreenTitle();
		ltl38MasterMenuScreen.enterValueOptionField(option);

		// Step 17: From Freight Billing Menu, Select option *1 - Freight bill inquiry*
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(option, user, originTerminal);
		testUtil.setHardWait(3000);

		// Step 18:Enter the *Pro Number* and press enter
		FreightBillInquiryScreen freightBillInquiryScreen = new FreightBillInquiryScreen(session);
		freightBillInquiryScreen.enterFBNum(originTerminal, proNum);
		freightBillInquiryScreen.pressEnterKey();
		testUtil.setHardWait(2000);

		// Step 19:From *Request 1* response/ right section, validate all data with
		// AS400 data

		freightBillingMenuScreen.verifyBOL(bol);
		freightBillingMenuScreen.verifyPONumber(po);
		freightBillingMenuScreen.verifyDate(testUtil.changeDateFormat(pickUpDate, "yyyy-MM-dd", "M/dd/yy"));
		freightBillingMenuScreen.verifyWeight(weight);
		freightBillingMenuScreen.verifyPieces(pieces);
		freightBillingMenuScreen.verifyDestinationTerminal(destinationTerminal);
		freightBillingMenuScreen.verifyTotal(freightCharges);
		freightBillingMenuScreen.verifyShipperName(shipper);
		freightBillingMenuScreen.verifyShipperAddressLine1(shipperAdd);
		freightBillingMenuScreen.verifyShipperCityStateZip(shipperCity, shipperstateProvince, shipperpostalCode);
		freightBillingMenuScreen.verifyConsigneeName(consignee);
		freightBillingMenuScreen.verifyConsigneeAddressLine1(consigneeAdd);
		freightBillingMenuScreen.verifyConsigneeCityStateZip(consigneeCity, consigneestateProvince,
				consigneepostalCode);

		// Step 20:From *Request 1* left section delete all and replace with the
		// following SOAPUI Request *Search by BOL*
		Response response2 = given().auth().basic("crowley3", "Crowley1").body(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"https://api.estes-express.com/ws/tools/shipment/tracking/v1.1/\">\n"
						+ "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <v1:shipmentTracking>\n"
						+ "         <search>           <requestID>1312020</requestID>            <bol>19BVZ7SP</bol>\n"
						+ "         </search>\n" + "      </v1:shipmentTracking>\n" + "   </soapenv:Body>\n"
						+ "</soapenv:Envelope>")
				.when().post(baseURI).then().log().all().statusCode(200).extract().response();

		// Step 21:From *Request 1*,(submit request to specified end point URL)
		// Validate response data
		System.out.println("Response for Search By BOL asString: " + response2.asString());
		Assert.assertEquals(response.asString(), response2.asString());

		// Step 22:From *Request 1* left section delete all and replace with the
		// following SOAPUI Request *Search by PO*
		Response response3 = given().auth().basic("crowley3", "Crowley1").body(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"https://api.estes-express.com/ws/tools/shipment/tracking/v1.1/\">\n"
						+ "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <v1:shipmentTracking>\n"
						+ "         <search>          <requestID>1312020</requestID>         <po>02963336695</po>            \n"
						+ "         </search>\n" + "      </v1:shipmentTracking>\n" + "   </soapenv:Body>\n"
						+ "</soapenv:Envelope>")
				.when().post(baseURI).then().log().all().statusCode(200).extract().response();

		// Step 23:From *Request 1*,(submit request to specified end point URL)
		// Validate response data
		System.out.println("Response for Search By PO asString: " + response3.asString());
		Assert.assertEquals(response.asString(), response3.asString());

	}

	/*
	 * Shipment Tracking - Unauth - Search result sort order by PRO Number
	 */

	@Test(enabled = false, priority = 13)

	public void executeQZ_9636() throws Exception {

		String query = "select fhot, fhpro from fbfiles.frp001 where FHDACD <> '' order by fhpud8 desc FETCH FIRST 25 ROWS ONLY";
		// Step 1: Open My estes app
		// Step 2: Click on Track
		myEstesHomePage.clickOnTrack();

		// Step 3: Click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();
		testUtil.setHardWait(1000);
		// Step 4: Select Pro Number in searchby drop down
		shipmentTrackingPage.selectPRONumber();

		// Step 5: Enter up to 25 Pro numbers
		List<String> proNumber = sqlDataList.getProFromQuery(query);

		shipmentTrackingPage.enterMultiplePRONumber(proNumber.get(0), proNumber.get(1), proNumber.get(2),
				proNumber.get(3), proNumber.get(4), proNumber.get(5), proNumber.get(6), proNumber.get(7),
				proNumber.get(8), proNumber.get(9), proNumber.get(10), proNumber.get(11), proNumber.get(12),
				proNumber.get(13), proNumber.get(14), proNumber.get(15), proNumber.get(16), proNumber.get(17),
				proNumber.get(18), proNumber.get(19), proNumber.get(20), proNumber.get(21), proNumber.get(22),
				proNumber.get(23), proNumber.get(24));

		// Step 6: Click search button
		shipmentTrackingPage.clickOnSearchBtn();

		// Verify search results displayed in the order entered

		ArrayList<String> pronum = new ArrayList<String>();

		ArrayList<String> proNumbersDisplayed = shipmentTrackingPage.captureResultTable();
		shipmentTrackingPage.insertDashForAssertTrue(pronum, proNumbersDisplayed);
		testUtil.setHardWait(5000);
		Assert.assertTrue((proNumbersDisplayed.equals(proNumber)));
		// Verify column header
		shipmentTrackingPage.verifyColHeaderForUnAuthicatedUser();

		// Step 7: Click on Expand All button
		shipmentTrackingPage.clickExpandAll();
		// Verify Results are expanded
		shipmentTrackingPage.verifyTrackingResultFieldsForUnAuthenticatedUser();

		// Step 8: Click clear button, Re-Enter Pro number and click on search
		shipmentTrackingPage.clickClear();
		shipmentTrackingPage.selectPRONumber(); // To get pro number field, select pronumber in search by field
		shipmentTrackingPage.enterMultiplePRONumber(proNumber.get(0), proNumber.get(1), proNumber.get(2),
				proNumber.get(3), proNumber.get(4), proNumber.get(5), proNumber.get(6), proNumber.get(7),
				proNumber.get(8), proNumber.get(9), proNumber.get(10), proNumber.get(11), proNumber.get(12),
				proNumber.get(13), proNumber.get(14), proNumber.get(15), proNumber.get(16), proNumber.get(17),
				proNumber.get(18), proNumber.get(19), proNumber.get(20), proNumber.get(21), proNumber.get(22),
				proNumber.get(23), proNumber.get(24));
		shipmentTrackingPage.clickOnSearchBtn();

		// Step 9: Click on caret icon
		shipmentTrackingPage.clickOnExpandArrow();

		// Validate fields populated in tracking results
		shipmentTrackingPage.verifyTrackingResultFieldsForUnAuthenticatedUser();
	}

	 /*
     * Authenticated - Verify that Time Critical Service Service Level should
     * be displayed below Estimated Delivery Date when Guaranteed LTL Standard
     * Transit: 10AM rate quote is created
     * 1/18/2022 ND Changed xyz
     * 1/17/2022 RSH broke xyz
     */

    /**
     *
     *@author Nithya-
     *
     */

    @Test(enabled = true, priority = 14)
    public void executeQZ_11204() throws Exception {

    	String userName = "QATSTFRTBL";
           String password = "qatest2019";
           String option = "1";
           String originTerminal = "171";
           String user = "Test";
           String option1 = "2";

           SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
           // Getting current date
           Calendar cal = Calendar.getInstance();
           // Displaying current date in the desired format
           String todayDate = sdf.format(cal.getTime());
           System.out.println(todayDate);

           // Step 1 - 2: Login to MyEstes as Test admin
           myEstesHomePage.clickOnMyEstes();
           myEstesHomePage.clickOnLogin();
           myEstesLoginPage.enterUserName(username4);
           myEstesLoginPage.enterPassword(password4);
           myEstesLoginPage.clickOnLoginButton();

           // Step 3: From *My Estes* Home page, click *Ship* from the menu then select *Rate Quote*
           myEstesWelcomePage.clickOnShipTab();
           myEstesWelcomePage.clickOnRateQoute();

           // Step 4: From *Rate Quote* step 1 page, select *Time Critical/Expedited* Rate Quote Type
           rateQuotePage.selectOrDeselectTimeCriticalExpedited();
           rateQuotePage.selectOrDeselectLessThanTruckload();
           rateQuotePage.verifyCreateRateQoutePage();

           // Step 5: From *Rate Quote* step 1 page, enter/select the following data :
           rateQuotePage.enterContactName("QA TESTER");
           rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
           rateQuotePage.enterPhoneNo("8885551212");
           rateQuotePage.enterMyRole("Third Party");
           rateQuotePage.enterTerms("Prepaid");

           // Step 6: Populate the Pickup date.
           rateQuotePage.selectTodayDate();

           // Step 7: From the *Routing Information* section, enter/select the following data:
           rateQuotePage.enterOriginZip("02150");
           rateQuotePage.enterDesZip("23200");

           // Step 8: From the *Commodities* section, enter/select the following data:
           rateQuotePage.enterClass("55");
           rateQuotePage.enterPieces("10");
           rateQuotePage.enterPieceType("PALLET");
           rateQuotePage.enterTotalWeight("1500");
           rateQuotePage.enterLength("36");
           rateQuotePage.enterWidth("24");
           rateQuotePage.enterHeight("12");
           rateQuotePage.enterDesc("test");

           // Step 9: Click *Submit Request* button.
           rateQuotePage.clickOnSubmitButton();
           rateQuotePage.verifyRateQuoteResultPage();

           // Step 10: From the *Results* table, click *Get Quote #* for Guaranteed LTL Standard Transit: 10AM service level
           rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");

           // Step 11: Record the *Quote Number*
           String quoteNum10AM = rateQuotePage.recordQuoteNumber("Gauranteed 10 AM");
           System.out.println(quoteNum10AM);

           // Step 12: Select *I accept the Terms and Conditions* check box
           rateQuotePage.clickOnTermsAndConditionsFor("Guaranteed LTL Standard Transit: 10AM");

           // Step 13: Click the *Reserve Shipment* button
           rateQuotePage.verifyReserveShipmentMessageforLTL10AM();;
           rateQuotePage.clickOnReserveShipmentButton();
           rateQuotePage.verifyShipmentInfoMsg();

           // Step 14: Below the *Request Received* button, Click the *Create BOL* button
          rateQuotePage.clickOnCreateBOL3();
           testUtil.setHardWait(2000);

           // Step 15: From the Bill of Lading form, BOL on Details section enter or select the following data: *Record BOL Reference Number*
           // BOL Reference Number: <current date, MMDDYY001>, Select toggle *Auto-assign PRO Number*

           //myEstesBillOfLadingPage.enterBOLReferenceNumber(todayDate + "001");
           myEstesBillOfLadingPage.enterBOLReferenceNumber(todayDate + "001" +testUtil.randomString(2)); //Modified tTo generate unique bolnumber
           myEstesBillOfLadingPage.clickOnAutoAssignPROToggle(); // need fix

           // Step 16: From the Quote Details section, Select (I have read and agree to the Terms of Service) checkbox
           myEstesBillOfLadingPage.clickOnTermsOfService();

           // Step 17: From the Shipper and Consignee Details section, enter the following data:
           myEstesBillOfLadingPage.enterShipperCompanyName("Golden Cannoli");
           myEstesBillOfLadingPage.enterConsigneeCompanyName("AMERISOURCE BERGEN");
           myEstesBillOfLadingPage.enterAddrLine1ForShipper("99 CRESCENT ST");
           testUtil.setHardWait(1000);
           myEstesBillOfLadingPage.enterAddrLine1ForConsignee("9900 JEB STUART PKWY");
           testUtil.setHardWait(500);

           // Step 18: From the Bill Information section: enter or select the following data:
          myEstesBillOfLadingPage.clickBillTo();
           myEstesBillOfLadingPage.enterBillToThirdParty();
           myEstesBillOfLadingPage.clickTerms();
           myEstesBillOfLadingPage.enterTerms("Prepaid");

           // Step 19: From the BILL TO INFO section, select the *Use My Estes Account Info* checkbox
           myEstesBillOfLadingPage.clickOnUseMyEstesAccInfoForThirdParty();

           // Step 20: Click *Submit BOL* button
           myEstesBillOfLadingPage.ClickOnSubmitBOL();

           // Step 21: *Record* 10-digit PRO #
           String proNumber = myEstesBillOfLadingPage.getProNumber();
           System.out.println(proNumber);
           String proNo = proNumber.replace("-", "");
           String bolNumber = myEstesBillOfLadingPage.getBOLNumber();

           // Step 22: *Record* ebol number at the of the url in the address bar
           String strUrl = driver.getCurrentUrl();
           String eBolNumber = strUrl.substring(strUrl.length() - 8);
           System.out.println(eBolNumber);

           // Step 23 - 25: Sign onto EXLAQA with tester credentials
           SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
           session.open();
           testUtil.setHardWait(3000);
           LoginScreen loginScreen = new LoginScreen(session);
           loginScreen.enterUserNPasswordCDOC(userName, password);
           testUtil.setHardWait(5000);
           JagacyUtil jagacyUtil = new JagacyUtil(session);

        // From the LTL/38 Master Menu, Enter 1
        Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
        /*
        * Added new method to avoid the failure due to user login and to avoid the
        * manual step to press enter when we see display comments screen.
        */
        ltl38MasterMenuScreen.verifyScreenTitle();
        ltl38MasterMenuScreen.enterValueOptionField(option);
        testUtil.setHardWait(3000);



           // Step 28 - 29: From the Freight Billing Menu, enter Option: 2, Terminal: 171 and Press ENTER
           FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
           FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
           freightBillingMenuScreen.enterValueOptionField(option1);
           freightBillingMenuScreen.enterValueUserField(user);
           freightBillingMenuScreen.enterTabKey();
           freightBillingMenuScreen.enterTerminalNumber(originTerminal);

           // Step 30 - 31: From the freight bill screen, enter the following:
           freightBillScreen.enterValueFreightBill(proNo);
           freightBillingMenuScreen.enterValueEControlID(eBolNumber);
           jagacyUtil.pressEnter();

           // Step 32: From the eBOL Confirmation screen, press F10 on keyboard to Accept
           EBolConfirmationScreen eBolConfirmationScreen = new EBolConfirmationScreen(session);
           eBolConfirmationScreen.pressF10Key();

           // Step 33: On the second page of the UPDATE screen, enter the following values:
           UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
           freightBillUpdateScreen.enterTS("1");
           freightBillUpdateScreen.enterCons("0103844");
           freightBillUpdateScreen.enterShip("7178618");
           freightBillUpdateScreen.enter3Pt("5068692");
           freightBillUpdateScreen.enterPONum(todayDate+001); //maybe need slashes?
           freightBillUpdateScreen.enterPuDrNum("9999999", "9");
           freightBillUpdateScreen.enterCubicFeet("1");

           // Step 34: Press ENTER to validate entries
           jagacyUtil.pressEnter();

           // Step 35: On the second page of the UPDATE screen, enter the following values:
           UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
           freightBillUpdateScreen2.enterPK("CT");
           freightBillUpdateScreen2.enterDesc("CARTONS");
           testUtil.setHardWait(5000);

           // Step 36: Press ENTER to validate entries
           jagacyUtil.pressEnter();

           // Step 37: Press ENTER to advance to next entry page
           jagacyUtil.pressEnter();
           jagacyUtil.pressEnter();

           // Step 38: Press F1 to exit
           jagacyUtil.pressF1();

           // Step 39 - 40: Access My Estes
           // (No need to log back in)
           driver.get(url);

           // Step 41: From My Estes home page hover mouse over the following menu:
           //Track
           myEstesHomePage.clickOnTrack();

           // Step 42: From Track menu, click on Shipment Tracking
           myEstesHomePage.clickOnShipmentTraking();

           // Step 43: From the Search By select Bill of Lading Number
           shipmentTrackingPage.selectBOLNumber();

           // Step 44: From the Search box enter a valid Bill of Lading number created
           shipmentTrackingPage.enterBOLNumber(bolNumber);
           testUtil.setHardWait(20000);

           // Step 45: Click on the SEARCH button
           shipmentTrackingPage.clickOnSearchButton();

           // Step 46: Validate data in the *Preview Bar* should match the data fields for the freight bill
           shipmentTrackingPage.verifyPickedUpStatus();
           shipmentTrackingPage.verifyPreviewBarProNum(proNo);
           shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

           // Step 47: Click on the Caret dropdown to the right of the shipment Status for the freight bill
           shipmentTrackingPage.clickOnExpandArrow();

           // Step 48: From shipment details validate the data elements for the shipment should match the freight bill
           shipmentTrackingPage.verifyValuePRONumber();
           shipmentTrackingPage.verifyValueBOLNumber();
           shipmentTrackingPage.verifyValueEBL();
           shipmentTrackingPage.verifyValuePONumber();
           shipmentTrackingPage.verifyValueWRCertificate();
           shipmentTrackingPage.verifyValuePieces();
           shipmentTrackingPage.verifyValueWeight();
           shipmentTrackingPage.verifyValuePickupDate();
           shipmentTrackingPage.verifyValueTransitDays();
           shipmentTrackingPage.verifyValueShipper();
           shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
           shipmentTrackingPage.verifyValueTimeCritical();
           shipmentTrackingPage.verifyValueTimeCritical10AM();

           shipmentTrackingPage.verifyValueConsignee();
           shipmentTrackingPage.verifyValueDeliveryReceipt();
           shipmentTrackingPage.verifyValueDestinationTerminal();
           shipmentTrackingPage.verifyValueTerminalPhone();
           shipmentTrackingPage.verifyValueQuestions();

           // Step 49: From the shipment details, click on the Contact us about this shipment hyperlink
           // Cannot validate that email opens

           // Step 50: Validate disclaimers at the bottom of the tracking results
           shipmentTrackingPage.verifyDisclaimer();
    }

    /*
     * Authenticated - Verify column header filter functionality and the order
     * of tracking results by any search criteria (Pro, BOL, PON Number) should
     * be displayed in the exact order as the user entered
     */

	@Test(enabled = true, priority = 15)
	public void executeQZ_11499()
			throws Exception {

		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "171";
		String user = "Test";
//			        String shipperCode = "7178618";
		String shipperCode = "5608430";
		String option1 = "2";

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		System.out.println(todayDate);

		/*String bolNum1 = todayDate + "1";  //Commented and modified as below to et unique bol and po#
		String bolNum2 = todayDate + "2";
		String bolNum3 = todayDate + "3";
		String bolNum4 = todayDate + "4";
		String bolNum5 = todayDate + "5";
		String poNum1 = todayDate + "PO1";
		String poNum2 = todayDate + "PO2";
		String poNum3 = todayDate + "PO3";
		String poNum4 = todayDate + "PO4";
		String poNum5 = todayDate + "PO5";*/

		String bolNum1 = todayDate + "1" +testUtil.randomString(2);
		String bolNum2 = todayDate + "2" +testUtil.randomString(2);
		String bolNum3 = todayDate + "3" +testUtil.randomString(2);
		String bolNum4 = todayDate + "4" +testUtil.randomString(2);
		String bolNum5 = todayDate + "5" +testUtil.randomString(2);
		String poNum1 = todayDate + "PO1" +testUtil.randomString(2);
		String poNum2 = todayDate + "PO2" +testUtil.randomString(2);
		String poNum3 = todayDate + "PO3" +testUtil.randomString(2);
		String poNum4 = todayDate + "PO4" +testUtil.randomString(2);
		String poNum5 = todayDate + "PO5" +testUtil.randomString(2);

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		// Step 1: Reserve Freight bill, follow the steps in QZ-384 and Complete Regular
		// Freight bill
		// Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		/*
		 * Added new method to avoid the failure due to user login and to avoid the
		 * manual step to press enter when we see display comments screen.
		 */
		ltl38MasterMenuScreen.verifyScreenTitle();
		ltl38MasterMenuScreen.enterValueOptionField(option);
		testUtil.setHardWait(3000);

		// ****FB 1****//
		// From the Freight Billing Menu screen, enter the following values: Option: 82,
		// User: TEST, Terminal = <171>, press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the following values
		// and record the pro number
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum1 = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum1);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// From the Freight Billing Menu, enter Option: 2, Terminal: 171 and Press ENTER
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 2: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("4");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3500");
		// freightBillUpdateScreen.enterCons("5608430");
		// freightBillUpdateScreen.enter3Pt("5068692");
		freightBillUpdateScreen.enterCons("5612290");
		freightBillUpdateScreen.enter3Pt("7178618");
		freightBillUpdateScreen.enterMasterBlNo(bolNum1);
		freightBillUpdateScreen.enterPONum(poNum1);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Step 3: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 4: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 5: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("4");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("3500");
		testUtil.setHardWait(5000);

		// Step 6: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 7: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 8: Press ENTER
		jagacyUtil.pressEnter();

		// Step 9: Press F1 to exit
		jagacyUtil.pressF1();

		// ****FB 2****//
		// From the Freight Billing Menu screen, enter the following values: Option: 82,
		// User: TEST, Terminal = <171>, press ENTER
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the following values
		// and record the pro number
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode("5608430");
		billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum2 = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum2);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// From the Freight Billing Menu, enter Option: 2, Terminal: 171 and Press ENTER
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 10: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("2");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("5000");
		freightBillUpdateScreen.enterCons("5612290");
		freightBillUpdateScreen.enter3Pt("7178618");
		freightBillUpdateScreen.enterMasterBlNo(bolNum2);
		freightBillUpdateScreen.enterPONum(poNum2);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// On the second page of the UPDATE screen, enter the following values:
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("2");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("SKIDS");
		freightBillUpdateScreen2.enterWgt2("5000");
		testUtil.setHardWait(5000);

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Press ENTER
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();

		// ****FB 3****//
		// From the Freight Billing Menu screen, enter the following values: Option: 82,
		// User: TEST, Terminal = <171>, press ENTER
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the following values
		// and record the pro number
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode("5608430");
		billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum3 = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum3);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// From the Freight Billing Menu, enter Option: 2, Terminal: 171 and Press ENTER
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 11: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("3");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("2500");
		freightBillUpdateScreen.enterCons("5612290");
		freightBillUpdateScreen.enter3Pt("7178618");
		freightBillUpdateScreen.enterMasterBlNo(bolNum3);
		freightBillUpdateScreen.enterPONum(poNum3);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// On the second page of the UPDATE screen, enter the following values:
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("3");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("SKIDS");
		freightBillUpdateScreen2.enterWgt2("2500");
		testUtil.setHardWait(5000);

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Press ENTER
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();

		// ****FB 4****//
		// From the Freight Billing Menu screen, enter the following values: Option: 82,
		// User: TEST, Terminal = <171>, press ENTER
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the following values
		// and record the pro number
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode("5608430");
		billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum4 = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum4);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// From the Freight Billing Menu, enter Option: 2, Terminal: 171 and Press ENTER
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 12: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("1");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3000");
		freightBillUpdateScreen.enterCons("5612290");
		freightBillUpdateScreen.enter3Pt("7178618");
		freightBillUpdateScreen.enterMasterBlNo(bolNum4);
		freightBillUpdateScreen.enterPONum(poNum4);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// On the second page of the UPDATE screen, enter the following values:
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("1");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("SKIDS");
		freightBillUpdateScreen2.enterWgt2("3000");
		testUtil.setHardWait(5000);

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Press ENTER
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();

		// ****FB 5****//
		// From the Freight Billing Menu screen, enter the following values: Option: 82,
		// User: TEST, Terminal = <171>, press ENTER
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Reserve a String of Freight Bills screen, enter the following values
		// and record the pro number
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode("5608430");
		billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum5 = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum5);

		// Press F3 to exit
		jagacyUtil.pressF3();

		// Step 13 - 14: From the Freight Billing Menu, enter Option: 2, Terminal: 171
		// and Press ENTER
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 13: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("5");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("5000");
		freightBillUpdateScreen.enterCons("5612290");
		freightBillUpdateScreen.enter3Pt("7178618");
		freightBillUpdateScreen.enterMasterBlNo(bolNum5);
		freightBillUpdateScreen.enterPONum(poNum5);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// On the second page of the UPDATE screen, enter the following values:
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("5");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("SKIDS");
		freightBillUpdateScreen2.enterWgt2("5000");
		testUtil.setHardWait(5000);

		// Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Press ENTER
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();

		// Step 14: Access My Estes
		driver.get(url);

		// Step 15: Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Step 16: From My Estes home page hover mouse over the following menu: Track
		myEstesHomePage.clickOnTrack();

		// Step 17: From Track menu, click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();

		// Step 18 - 19: From the Search box enter *all 5 pros* valid 10 digit freight
		// bill number
		shipmentTrackingPage.enterPORNumber(fbNum1 + "\n" + fbNum2 + "\n" + fbNum3 + "\n" + fbNum4 + "\n" + fbNum5);
		testUtil.setHardWait(20000);

		// Step 20: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(10000);
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum1, fbNum2, fbNum3, fbNum4, fbNum5);

		// Step 21: Click on the PRO Number column
		shipmentTrackingPage.clickOnProNumPreviewColumn();
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum1, fbNum2, fbNum3, fbNum4, fbNum5);

		// Step 22: Click on the Pickup Date column
		shipmentTrackingPage.clickOnPickupDatePreviewColumn();
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum1, fbNum2, fbNum3, fbNum4, fbNum5);

		// Step 23: Click on the BOL Number column
		shipmentTrackingPage.clickOnBolNumPreviewColumn();
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum1, fbNum2, fbNum3, fbNum4, fbNum5);

		// Step 24: Click on the Status column
		shipmentTrackingPage.clickOnStatusPreviewColumn();
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum1, fbNum2, fbNum3, fbNum4, fbNum5);

		// Step 25: From the Tracking page, select Bill of Lading Number from the Search
		// by drop down and enter the following data
		shipmentTrackingPage.clickSearchBy();
		shipmentTrackingPage.clickSearchByBOL();
		//shipmentTrackingPage.clickSearchByPO();
		shipmentTrackingPage
				.enterPORNumber(bolNum5 + "\n" + bolNum4 + "\n" + bolNum3 + "\n" + bolNum2 + "\n" + bolNum1);

		// Step 26: Click Search button
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(10000);
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum5, fbNum4, fbNum3, fbNum2, fbNum1);

		// Step 27: From the Tracking page, select Purchase Order Number from the Search
		// by drop down and enter the following data
		shipmentTrackingPage.clickSearchBy();
		shipmentTrackingPage.clickSearchByPO();
		shipmentTrackingPage.enterPORNumber(poNum1 + "\n" + poNum2 + "\n" + poNum3 + "\n" + poNum4 + "\n" + poNum5);

		// Step 28: Click Search button
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(10000);
		shipmentTrackingPage.verifyFivePreviewBarProNumsInOrder(fbNum1, fbNum2, fbNum3, fbNum4, fbNum5);

		// Step 29: Validate data in the *Preview Bar*
		shipmentTrackingPage.verifyPickedUpStatus();
		shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

		// Step 30: Click on the Caret dropdown to the right of the shipment Status for
		// the freight bill
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 31: Validate the Milestone Data
		shipmentTrackingPage.verifyPickedUpMilestone();

		// Step 32: From shipment details validate the data elements for the shipment
		// should match the freight bill
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueBOLNumber();
		shipmentTrackingPage.verifyValuePONumber();
		shipmentTrackingPage.verifyValueWRCertificate();
		shipmentTrackingPage.verifyValuePieces();
		shipmentTrackingPage.verifyValueWeight();
		shipmentTrackingPage.verifyValuePickupDate();
		shipmentTrackingPage.verifyValueTransitDays();
		shipmentTrackingPage.verifyValueShipper();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueConsignee();
		shipmentTrackingPage.verifyValueDeliveryReceipt();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueQuestions();
		shipmentTrackingPage.verifyValueFreightCharges();

		// Step 33: Validate disclaimer should display
		shipmentTrackingPage.verifyDisclaimer();

		// Step 34: Click on Shipment History caret dropdown
		shipmentTrackingPage.clickOnShipmentHistoryCaret2();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div//span[contains(text(),'Shipment picked up')]")).isDisplayed());
		//shipmentTrackingPage.verifyTimeZone("EST"); -Commented this validation referring to Dee's response received over mail for the test 6013 on 07 March 2022


		// Step 35: Click the Collapse All button
		shipmentTrackingPage.clickOnShipmentStatusCaretToClose();

		// Step 36: From the MyEstes dropdown, click on Log Out

	}

	/*
	 * Authenticated - Verify that the EDD (Estimated Delivery Date) returned from
	 * the API should display a range of plus 4 business days for a shipment with
	 * residential flag = Y_In Transit Status
	 */
	@Test(enabled = true, priority = 16)
	public void executeQZ_11203()
			throws Exception {
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "006";
		String destinationTerminal = "171";
		String user = "Test";
		String shipperCode = "6545352";
		String option1 = "2";
		String option2 = "18";

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		// Step 1 - 4: Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		/*
		 * Added new method to avoid the failure due to user login and to avoid the
		 * manual step to press enter when we see display comments screen.
		 */
		ltl38MasterMenuScreen.verifyScreenTitle();
		ltl38MasterMenuScreen.enterValueOptionField(option);
		testUtil.setHardWait(3000);

		// Step 7 - 8: From the Freight Billing Menu screen, enter the following values:
		// Option: 82, User: TEST, Terminal = <006>, press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 9 - 11: From the Reserve a String of Freight Bills screen, enter the
		// following values: Bills to reserve: 1, Shipper Code: 2791403
		// and record the pro number
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Step 12: Press F3 to exit
		jagacyUtil.pressF3();

		// Step 13 - 14: From the Freight Billing Menu, enter Option: 2, Terminal: 006
		// and Press ENTER
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 15: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("5");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("7178618");
		freightBillUpdateScreen.enterMasterBlNo("083020211300");
		freightBillUpdateScreen.enterPONum("UkqGQWBSk");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Step 16: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 17: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("5");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("1000");
		testUtil.setHardWait(5000);

		// Step 18: Press ENTER to validate entries
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 19: On the third page of the UPDATE screen, make the following entries
		// under Accessorial Charges: HD
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");

		// Step 20: Press ENTER
		jagacyUtil.pressEnter();

		// Step 21: Press F1 to exit
		jagacyUtil.pressF1();

		// Step 22: Load pro onto Road Manifest and dispatch
		// Navigate to the Freight Billing Menu and select Option 18, Central Dispatch.
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);
		session.waitForChange(1000);
		// Press SHIFT + F4, Road Manifest Setup Entry.
		LinehaulDispatchScreen linehaulDispatchScreen = new LinehaulDispatchScreen(session);
		linehaulDispatchScreen.enterShiftF4();
		session.waitForChange(5000);

		// From the Road Manifest Setup Entry screen, enter the following values: Dest
		// TID: Trailer# : ?, and press ENTER
		RoadManifestSetupEntryScreen roadManifestSetupEntryScreen = new RoadManifestSetupEntryScreen(session);
		roadManifestSetupEntryScreen.enterDestTID(destinationTerminal);
		roadManifestSetupEntryScreen.enterTrailer("?");
		jagacyUtil.pressEnter();
		session.waitForChange(3000);

		// From the Trailer # pop-up screen: Replace the value LTR with MTY.
		roadManifestSetupEntryScreen.changeStatus("MTY");
		session.waitForChange(3000);

		// Press ENTER
		jagacyUtil.pressEnter();
		session.waitForChange(3000);

		// In the Position To field (Pos to:), enter the following: 52
		roadManifestSetupEntryScreen.changePosTo("28");
		session.waitForChange(5000);

		// Press ENTER
		jagacyUtil.pressEnter();
		session.waitForChange(6000);

		// Place a '1' next to the trailer to be selected. Record your trailer
		// selection.
		roadManifestSetupEntryScreen.selectTrailer2("2");
		jagacyUtil.pressEnter();
		String trailer = roadManifestSetupEntryScreen.recordTrailerNumber();

		// Press ENTER
		jagacyUtil.pressEnter();

		// Press F1
		jagacyUtil.pressF1();
		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Press the PAGE DOWN key to find the newly assigned Trailer# In the O column
		// adjacent to the trailer number,
		// enter the following value: M and press ENTER
		linehaulDispatchScreen.selectTrailer(trailer, "M");
		testUtil.setHardWait(5000);

		// From the Select RM Option window, Type in 1 in the data field at the top of
		// the window and Press ENTER
		linehaulDispatchScreen.selectRMOption("1");
		jagacyUtil.pressEnter();
		RoadManifestEntryUpdateScreen roadManifestEntryUpdateScreen = new RoadManifestEntryUpdateScreen(session);

		// From the Road Manifest Entry/Update screen, enter the following values: Seal:
		// <ANY 4 - 5 DIGIT NUMBER>,
		// Can the Freight be added?: Y, Enter the recently created Freight Bill numbers
		// in the field beneath the OOOBBBBBBB field, Press ENTER
		roadManifestEntryUpdateScreen.enterSeal("654321");
		roadManifestEntryUpdateScreen.enterFreightBillCanBeAdded("Y");
		String bill = fbNum;
		roadManifestEntryUpdateScreen.enterFreightBill(bill);
		jagacyUtil.pressEnter();

		// From the Enter Percent/Quarter Trailer screen, enter any percentage value
		// below 100% in all sections.
		// Press ENTER to validate the data, Press F10 to update the Manifest Cube
		// update, After the Equipment on Trailer
		// pop-up is displayed, press Enter to finalize the cube assignment, Press F1
		EnterPercentageQuarterTrailerScreen enterPercentageQuarterTrailerScreen = new EnterPercentageQuarterTrailerScreen(
				session);
		enterPercentageQuarterTrailerScreen.enterFloorAndHeightForAllSections(" 80");
		jagacyUtil.pressEnter();
		enterPercentageQuarterTrailerScreen.clickF10();
		jagacyUtil.pressEnter();
		enterPercentageQuarterTrailerScreen.clickF1();
		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Line Haul Dispatch screen, Press the PAGE DOWN key to find the newly
		// assigned Trailer#, Type P
		// in the 'O' column. Press ENTER
		linehaulDispatchScreen.selectTrailer(trailer, "P");
		testUtil.setHardWait(5000);

		// From the Select Driver to Pre-plan a Load screen, enter the following values:
		// Name: MISC, Press ENTER to
		// re-sort the driver list, Type in the value 1 adjacent to the MISC DRIVER
		// entry, press ENTER
		linehaulDispatchScreen.selectAndPlanDriver("MISC DRIVER", destinationTerminal);
		testUtil.setHardWait(3000);

		// Enter next terminal and press ENTER
		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// From the Line Haul Dispatch screen, Press the PAGE DOWN key to find the
		// recently assigned Trailer#,
		// Type D in the O column and press ENTER
		linehaulDispatchScreen.selectTrailer(trailer, "D");

		// From the Road Manifest Check-Out screen, enter the following data values,
		// Enter following details in checkout screen:
		// Run number: EXTRA, Tractor: /ONEWAY, HUB?: Y, press ENTER, Equipment errors
		// found. Please verify entry. Are you sure you want to dispatch?
		// Type in Y, press ENTER, Next pop-up window - Type in Y, press ENTER
		RoadManifestCheckoutScreen roadManifestCheckoutScreen = new RoadManifestCheckoutScreen(session);
		roadManifestCheckoutScreen.enterRunNumber("EXTRA");
		roadManifestCheckoutScreen.enterTractor("/ONEWAY");
		roadManifestCheckoutScreen.enterHub("Y");
		jagacyUtil.pressEnter();
		roadManifestCheckoutScreen.enterErrorEquipment("Y");
		jagacyUtil.pressEnter();
		roadManifestCheckoutScreen.enterDispatchType("Y");

		// Step 22: Press Enter
		jagacyUtil.pressEnter();

		// Step 23: Press F1 2x to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF1();

		// Step 24: Access Shipment Tracking app
		driver.get(url3);

		// Step 25: Login with smokelocal credentials
		shipmentTrackingPage.clickOnMyEstes();
		shipmentTrackingPage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

		// Step 26 - 27: Search by Pro Number and enter pro number into Tracking box
//    	        String fbNum = "";
		shipmentTrackingPage.enterPORNumber(fbNum);

		// Step 28: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();

		// Step 29: Validate the Estimated Delivery Column in the preview bar should
		// display the Estimated Delivery
		// Date Range (EDD) of plus 4 business day for a total of 5 business days
		// Note: EDD range should not include weekend
		shipmentTrackingPage.verifyInTransitStatus();
		shipmentTrackingPage.verifyEstimatedDeliveryDateRange();

		// Step 30: Click on the Caret dropdown to the right of the shipment Status for
		// the freight bill
		shipmentTrackingPage.clickOnShipmentStatusCaret();
		shipmentTrackingPage.verifyMilestoneInfo("En route to delivery facility at Billerica, MA (171)");
		shipmentTrackingPage.verifyInTransitMilestone();

		// Step 31: Validate data in the *Shipment Details* should match the data fields
		// for the freight bill and verify the
		// *Estimated Delivery Date* should display range
		shipmentTrackingPage.verifyEstimatedDeliveryDateRange2();

		// Step 32: Validate disclaimers should display at the bottom of the tracking
		// results
		shipmentTrackingPage.verifyDisclaimer();

		// Step 33: Click on Shipment History caret dropdown and Validate the Shipment
		// History data returned should match the freight bill and
		// timestamp reflected should match the timezone
		shipmentTrackingPage.clickOnShipmentHistoryCaret();
		shipmentTrackingPage.validateShipmentHistory("En route to delivery facility at Billerica, MA (171)");
		shipmentTrackingPage.validateShipmentHistory("Departed Estes facility at Edison, NJ (006)");
		shipmentTrackingPage.validateShipmentHistory("Trailer Closed at Estes facility at Edison, NJ (006)");
		shipmentTrackingPage.validateShipmentHistory("Loaded at Estes facility at Edison, NJ (006)");
		shipmentTrackingPage.validateShipmentHistory("Shipment picked up");

		//shipmentTrackingPage.verifyTimeZone("EST"); ----> Commented this validation referring to Dee's response received over mail for the test 6013 on 07 March 2022

		// Step 34: Click the caret dropdown to close
		shipmentTrackingPage.clickOnShipmentStatusCaretToClose();

	}

	/*
	 * Tracking app - Verify that unauthenticated user has access to view certain
	 * shipper and consignee details using Shipment Lifecycle/Tracking app
	 */
	@Test(enabled = true, priority = 17)

	public void executeQZ_11373()
			throws Exception {

		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "135";
		String destinationTerminal = "006";
		String user = "Test";
		String shipperCode = "B018906";
		String option1 = "2";
		String option2 = "18";

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);

		// Step 1 - 4: Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		/*
		 * Added new method to avoid the failure due to user login and to avoid the
		 * manual step to press enter when we see display comments screen.
		 */
		ltl38MasterMenuScreen.verifyScreenTitle();
		ltl38MasterMenuScreen.enterValueOptionField(option);
		testUtil.setHardWait(3000);

		// Step 7: From the Freight Billing Menu screen, enter the following values:
		// Option: 82, User: TEST, Terminal = <135>, press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(reserveOption);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 9 - 11: From the Reserve a String of Freight Bills screen, enter the
		// following values: Bills to reserve: 1, Shipper Code: 2791403
		// and record the pro number
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.enterNumberOfFB("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Step 12: Press F3 to exit
		jagacyUtil.pressF3();

		// Step 13 - 14: From the Freight Billing Menu, enter Option: 2, Terminal: 135
		// and Press ENTER
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 15: From the Freight Bill Entry/Update screen, Enter required fields
		freightBillScreen.enterFreightBill(originTerminal, billNum);
		testUtil.setHardWait(2000);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("5");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("0672288");
		freightBillUpdateScreen.enterMasterBlNo("NS");
		freightBillUpdateScreen.enterPONum("202108241315");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("50");

		// Step 16: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 17: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 18 - 21: On the second page of the UPDATE screen, enter the following
		// values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
//    	        freightBillUpdateScreen2.enterClass("");
		freightBillUpdateScreen2.enterPcs2("5");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("SKIDS");
		freightBillUpdateScreen2.enterWgt2("1000");
		testUtil.setHardWait(5000);

		// Step 19: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 20: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 21 - 22: Press ENTER
		jagacyUtil.pressEnter();

		// Step 23: Press F1 to exit
		jagacyUtil.pressF1();

		// Step 24 - 25: From the Freight Billing Menu, Enter option, user, and terminal
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);
		session.waitForChange(2000);
		// Step 26: Press SHIFT + F4
		LinehaulDispatchScreen linehaulDispatchScreen = new LinehaulDispatchScreen(session);
		linehaulDispatchScreen.enterShiftF4();
		session.waitForChange(5000);

		// Step 27 - 28: From the Road Manifest Setup Entry screen, enter the following
		// values: Dest TID: Trailer# : ?, and press ENTER
		RoadManifestSetupEntryScreen roadManifestSetupEntryScreen = new RoadManifestSetupEntryScreen(session);
		roadManifestSetupEntryScreen.enterDestTID(destinationTerminal);
		roadManifestSetupEntryScreen.enterTrailer("?");
		jagacyUtil.pressEnter();
		session.waitForChange(3000);

		// Step 29: From the Trailer # pop-up screen: Replace the value LTR with MTY.
		roadManifestSetupEntryScreen.changeStatus("MTY");
		session.waitForChange(3000);

		// Step 30: Press ENTER
		jagacyUtil.pressEnter();
		session.waitForChange(3000);

		// Step 31: In the Position To field (Pos to:), enter the following: 52
		roadManifestSetupEntryScreen.changePosTo("52");
		session.waitForChange(5000);

		// Step 32: Press ENTER
		jagacyUtil.pressEnter();
		session.waitForChange(6000);

		// Step 33 - 34: Place a '1' next to the trailer to be selected. Record your
		// trailer selection.
		roadManifestSetupEntryScreen.selectTrailer2();
		jagacyUtil.pressEnter();
		String trailer = roadManifestSetupEntryScreen.recordTrailerNumber();

		// Step 35: Press ENTER
		jagacyUtil.pressEnter();

		// Step 36: Press F1
		jagacyUtil.pressF1();
		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 37 - 38: Press the PAGE DOWN key to find the newly assigned Trailer# In
		// the O column adjacent to the trailer number,
		// enter the following value: M and press ENTER
		linehaulDispatchScreen.selectTrailer(trailer, "M");
		testUtil.setHardWait(5000);

		// Step 39: From the Select RM Option window, Type in 1 in the data field at the
		// top of the window and Press ENTER
		linehaulDispatchScreen.selectRMOption("1");
		jagacyUtil.pressEnter();
		RoadManifestEntryUpdateScreen roadManifestEntryUpdateScreen = new RoadManifestEntryUpdateScreen(session);

		// Step 40 - 41: From the Road Manifest Entry/Update screen, enter the following
		// values: Seal: <ANY 4 - 5 DIGIT NUMBER>,
		// Can the Freight be added?: Y, Enter the recently created Freight Bill numbers
		// in the field beneath the OOOBBBBBBB field, Press ENTER
		roadManifestEntryUpdateScreen.enterSeal("654321");
		roadManifestEntryUpdateScreen.enterFreightBillCanBeAdded("Y");
		String bill = fbNum;
		roadManifestEntryUpdateScreen.enterFreightBill(bill);
		jagacyUtil.pressEnter();

		// Step 42 - 46: From the Enter Percent/Quarter Trailer screen, enter any
		// percentage value below 100% in all sections.
		// Press ENTER to validate the data, Press F10 to update the Manifest Cube
		// update, After the Equipment on Trailer
		// pop-up is displayed, press Enter to finalize the cube assignment, Press F1
		EnterPercentageQuarterTrailerScreen enterPercentageQuarterTrailerScreen = new EnterPercentageQuarterTrailerScreen(
				session);
		enterPercentageQuarterTrailerScreen.enterFloorAndHeightForAllSections(" 80");
		jagacyUtil.pressEnter();
		enterPercentageQuarterTrailerScreen.clickF10();
		jagacyUtil.pressEnter();
		enterPercentageQuarterTrailerScreen.clickF1();
		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 47 - 48: From the Line Haul Dispatch screen, Press the PAGE DOWN key to
		// find the newly assigned Trailer#, Type P
		// in the 'O' column. Press ENTER
		linehaulDispatchScreen.selectTrailer(trailer, "P");
		testUtil.setHardWait(5000);

		// Step 49: From the Select Driver to Pre-plan a Load screen, enter the
		// following values: Name: MISC, Press ENTER to
		// re-sort the driver list, Type in the value 1 adjacent to the MISC DRIVER
		// entry, press ENTER
		linehaulDispatchScreen.selectAndPlanDriver("MISC DRIVER", destinationTerminal);
		testUtil.setHardWait(3000);

		// Step 50: Enter next terminal and press ENTER
		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField(option2);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

		// Step 51 - 52: From the Line Haul Dispatch screen, Press the PAGE DOWN key to
		// find the recently assigned Trailer#,
		// Type D in the O column and press ENTER
		linehaulDispatchScreen.selectTrailer(trailer, "D");

		// Step 53 - 56: From the Road Manifest Check-Out screen, enter the following
		// data values, Enter following details in checkout screen:
		// Run number: EXTRA, Tractor: /ONEWAY, HUB?: Y, press ENTER, Equipment errors
		// found. Please verify entry. Are you sure you want to dispatch?
		// Type in Y, press ENTER, Next pop-up window - Type in Y, press ENTER
		RoadManifestCheckoutScreen roadManifestCheckoutScreen = new RoadManifestCheckoutScreen(session);
		roadManifestCheckoutScreen.enterRunNumber("EXTRA");
		roadManifestCheckoutScreen.enterTractor("/ONEWAY");
		roadManifestCheckoutScreen.enterHub("Y");
		jagacyUtil.pressEnter();
		roadManifestCheckoutScreen.enterErrorEquipment("Y");
		jagacyUtil.pressEnter();
		roadManifestCheckoutScreen.enterDispatchType("Y");
		jagacyUtil.pressEnter();

		// Step 57: Press F1
		jagacyUtil.pressF1();

		// Step 58: Press F1
		jagacyUtil.pressF1();

		// Step 59: Access Shipment Tracking app
		driver.get(url3);

		// Step 60: Search by Pro Number and enter pro number into Tracking box
//    	        String fbNum = "";
		shipmentTrackingPage.enterPORNumber(fbNum);

		// Step 61: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();

		// Step 62: Click on the Expand All button
		// Verify that user is able to access the shipper city, state, and zip code for
		// the searched pro
		// Verify that user is able to access the consignee city and state for the
		// searched pro
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.verifyShipperCityStateAndZIPAreDisplayed("MEMPHIS", "TN", "38141");
		shipmentTrackingPage.verifyConsigneeCityAndStateAreDisplayed("BRIDGEWATER", "NJ");
	}

	/*
	 * Unauthenticated - Verify that Time Critical Service Service Level should be
	 * displayed below Estimated Delivery Date when Guaranteed LTL Standard Transit:
	 * 12PM rate quote is created
	 */
	@Test(enabled = true, priority = 18)
	public void executeQZ_11447()
			throws Exception {
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String originTerminal = "171";
		String user = "Test";
		String option1 = "2";

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		System.out.println(todayDate);

		// Step 1 - 2: Login to MyEstes as Test admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: From *My Estes* Home page, click *Ship* from the menu then select
		// *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Step 4: From *Rate Quote* step 1 page, select *Time Critical/Expedited* Rate
		// Quote Type
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.verifyCreateRateQoutePage();

		// Step 5: From *Rate Quote* step 1 page, enter/select the following data :
		rateQuotePage.enterContactName("QA TESTER");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 6: Populate the Pickup date.
		rateQuotePage.selectTodayDate();

		// Step 7: From the *Routing Information* section, enter/select the following
		// data:
		rateQuotePage.enterOriginZip("02150");
		rateQuotePage.enterDesZip("23059");

		// Step 8: From the *Commodities* section, enter/select the following data:
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("1500");
		rateQuotePage.enterLength("36");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("test");

		// Step 9: Click *Submit Request* button.
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Step 10: From the *Results* table, click *Get Quote #* for Guaranteed LTL
		// Standard Transit: 12PM service level
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");

		// Step 11: Record the *Quote Number*
		String quoteNum12PM = rateQuotePage.recordQuoteNumber("Gauranteed 12 PM");
		System.out.println(quoteNum12PM);
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 12PM");

		// Step 12: Select *I accept the Terms and Conditions* check box
		rateQuotePage.selectIAcceptTheTermsAndConditionsCheckBox12PM();

		// Step 13: Click the *Reserve Shipment* button
		rateQuotePage.verifyReserveShipmentMessageforLTL12PM();
		rateQuotePage.clickOnReserveShipmentButton2();
		rateQuotePage.verifyShipmentInfoMsg();

		// Step 14: Below the *Request Received* button, Click the *Create BOL* button
		rateQuotePage.clickOnCreateBOL12PM();
		testUtil.setHardWait(2000);

		// Step 15: From the Bill of Lading form, BOL on Details section enter or select
		// the following data: *Record BOL Reference Number*
		// BOL Reference Number: <current date, MMDDYY001>, Select toggle *Auto-assign
		// PRO Number*
		myEstesBillOfLadingPage.enterBOLReferenceNumber(todayDate + "001");
		myEstesBillOfLadingPage.clickOnAutoAssignPROToggle(); // need fix

		// Step 16: From the Quote Details section, Select (I have read and agree to the
		// Terms of Service) checkbox
		myEstesBillOfLadingPage.clickOnTermsOfService();

		// Step 17: From the Shipper and Consignee Details section, enter the following
		// data:
		myEstesBillOfLadingPage.enterShipperCompanyName("Golden Cannoli");
		myEstesBillOfLadingPage.enterConsigneeCompanyName("AMERISOURCE BERGEN");
		myEstesBillOfLadingPage.enterAddrLine1ForShipper("99 CRESCENT ST");
		testUtil.setHardWait(1000);
		myEstesBillOfLadingPage.enterAddrLine1ForConsignee("9900 JEB STUART PKWY");
		testUtil.setHardWait(500);

		// Step 18: From the Bill Information section: enter or select the following
		// data:
		myEstesBillOfLadingPage.clickBillTo();
		myEstesBillOfLadingPage.enterBillToThirdParty();
		myEstesBillOfLadingPage.clickTerms();
		myEstesBillOfLadingPage.enterTerms("Prepaid");

		// Step 19: From the BILL TO INFO section, select the *Use My Estes Account
		// Info* checkbox
		myEstesBillOfLadingPage.clickOnUseMyEstesAccInfoForThirdParty();

		// Step 20: Click *Submit BOL* button
		myEstesBillOfLadingPage.ClickOnSubmitBOL();

		// Step 21: *Record* 10-digit PRO #
		String proNumber = myEstesBillOfLadingPage.getProNumber();
		System.out.println(proNumber);
		String proNo = proNumber.replace("-", "");

		// Step 23: *Record* ebol number at the of the url in the address bar
		// TO-DO: grab ebol number from URL
		String strUrl = driver.getCurrentUrl();
		String eBolNumber = strUrl.substring(strUrl.length() - 8);
		System.out.println(eBolNumber);

		// Step 22: From the My Estes dropdown, click on Logout
		myEstesHomePage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		myEstesHomePage.clickOnConfirmButton();

		// Step 24 - 26: Sign onto EXLAQA with tester credentials
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);
		// Step 1 - 4: Login to the following application: EXLAQA
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		testUtil.setHardWait(5000);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// From the LTL/38 Master Menu, Enter 1
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		/*
		 * Added new method to avoid the failure due to user login and to avoid the
		 * manual step to press enter when we see display comments screen.
		 */
		ltl38MasterMenuScreen.verifyScreenTitle();
		ltl38MasterMenuScreen.enterValueOptionField(option);
		testUtil.setHardWait(3000);

		// Step 29 - 30: From the Freight Billing Menu, enter Option: 2, Terminal: 171
		// and Press ENTER
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterValueOptionField(option1);
		freightBillingMenuScreen.enterValueUserField(user);
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(originTerminal);

//    	        String proNo = "";

		// Step 31 - 32: From the freight bill screen, enter the following:
		freightBillScreen.enterValueFreightBill(proNo);
		freightBillingMenuScreen.enterValueEControlID(eBolNumber);
		jagacyUtil.pressEnter();

		// Step 33: From the eBOL Confirmation screen, press F10 on keyboard to Accept
		EBolConfirmationScreen eBolConfirmationScreen = new EBolConfirmationScreen(session);
		eBolConfirmationScreen.pressF10Key();

		// Step 34: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterCons("0103844");
		freightBillUpdateScreen.enterShip("7178618");
		freightBillUpdateScreen.enter3Pt("5068692");
		freightBillUpdateScreen.enterPONum(todayDate + 001); // maybe need slashes?
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");

		// Step 35: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 36: On the second page of the UPDATE screen, enter the following values:
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		testUtil.setHardWait(5000);

		// Step 37: Press ENTER to validate entries
		jagacyUtil.pressEnter();

		// Step 38: Press ENTER to advance to next entry page
		jagacyUtil.pressEnter();

		// Step 21 - 22: Press ENTER
		jagacyUtil.pressEnter();

		// Step 39: Press F1 to exit
		jagacyUtil.pressF1();

		// Step 40: Access Shipment Tracking app
		driver.get(url3);

		// Step 41 - 42: Search by Pro Number and enter pro number into Tracking box
		shipmentTrackingPage.enterPORNumber(proNo);
		testUtil.setHardWait(20000);

		// Step 43: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();
		shipmentTrackingPage.verifyPickedUpStatus();
		shipmentTrackingPage.verifyPreviewBarProNum(proNo);
		shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

		// Step 44: Click on the Caret dropdown to the right of the shipment Status for
		// the freight bill
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 45: From shipment details validate the data elements for the shipment
		// should match the freight bill
		shipmentTrackingPage.verifyValuePRONumber();
		shipmentTrackingPage.verifyValueBOLNumber();
		shipmentTrackingPage.verifyValueEBL();
		shipmentTrackingPage.verifyValuePONumber();
		shipmentTrackingPage.verifyValuePieces();
		shipmentTrackingPage.verifyValueWeight();
		shipmentTrackingPage.verifyValuePickupDate();
		shipmentTrackingPage.verifyValueTransitDays();
		shipmentTrackingPage.verifyValueShipper();
		shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
		shipmentTrackingPage.verifyValueTimeCritical();

		shipmentTrackingPage.verifyValueTimeCritical12PM();
		shipmentTrackingPage.verifyValueConsignee();
		shipmentTrackingPage.verifyValueDestinationTerminal();
		shipmentTrackingPage.verifyValueTerminalPhone();
		shipmentTrackingPage.verifyValueQuestions();

		// Step 46: From the shipment details, click on the Contact us about this
		// shipment hyperlink
		// Cannot validate that email opens

		// Step 47: Validate disclaimers at the bottom of the tracking results
		shipmentTrackingPage.verifyDisclaimer();
	}


	// Verify with Quinci

		/*
		 * Unauthenticated - Home Page Tracking Widget - Verify that Time Critical
		 * Service Service Level should be displayed below Estimated Delivery Date when
		 * Guaranteed LTL Standard Transit: 5PM rate quote is created
		 */
		@Test(enabled = true, priority = 19)
		public void executeQZ_11448()
				throws Exception {

			String userName = "QATSTFRTBL";
			String password = "qatest2019";
			String option = "1";
			String originTerminal = "171";
			String user = "Test";
			String option1 = "2";

			SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
			// Getting current date
			Calendar cal = Calendar.getInstance();
			// Displaying current date in the desired format
			String todayDate = sdf.format(cal.getTime());
			System.out.println(todayDate);

			// Step 1 - 2: Login to MyEstes as Test admin
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username4);
			myEstesLoginPage.enterPassword(password4);
			myEstesLoginPage.clickOnLoginButton();

			// Step 3: From *My Estes* Home page, click *Ship* from the menu then select
			// *Rate Quote*
			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

			// Step 4: From *Rate Quote* step 1 page, select *Time Critical/Expedited* Rate
			// Quote Type
			rateQuotePage.selectOrDeselectTimeCriticalExpedited();
			rateQuotePage.selectOrDeselectLessThanTruckload();
			rateQuotePage.verifyCreateRateQoutePage();

			// Step 5: From *Rate Quote* step 1 page, enter/select the following data :
			rateQuotePage.enterContactName("QA TESTER");
			rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
			rateQuotePage.enterPhoneNo("8885551212");
			rateQuotePage.enterMyRole("Third Party");
			rateQuotePage.enterTerms("Prepaid");

			// Step 6: Populate the Pickup date.
			rateQuotePage.selectTodayDate();

			// Step 7: From the *Routing Information* section, enter/select the following
			// data:
			rateQuotePage.enterOriginZip("02150");
			rateQuotePage.enterDesZip("23059");

			// Step 8: From the *Commodities* section, enter/select the following data:
			rateQuotePage.enterClass("55");
			rateQuotePage.enterPieces("10");
			rateQuotePage.enterPieceType("PALLET");
			rateQuotePage.enterTotalWeight("1500");
			rateQuotePage.enterLength("36");
			rateQuotePage.enterWidth("24");
			rateQuotePage.enterHeight("12");
			rateQuotePage.enterDesc("test");

			// Step 9: Click *Submit Request* button.
			rateQuotePage.clickOnSubmitButton();
			rateQuotePage.verifyRateQuoteResultPage();

			// Step 10: From the *Results* table, click *Get Quote #* for Guaranteed LTL
			// Standard Transit: 12PM service level
			rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");

			// Step 11: Record the *Quote Number*
			String quoteNum5PM = rateQuotePage.recordQuoteNumber("Gauranteed 5 PM");
			System.out.println(quoteNum5PM);

			// Step 12: Select *I accept the Terms and Conditions* check box
			rateQuotePage.selectIAcceptTheTermsAndConditionsCheckBox5PM();

			// Step 13: Click the *Reserve Shipment* button
			rateQuotePage.verifyReserveShipmentMessageforLTL5PM();
			rateQuotePage.clickOnReserveShipmentButton3();
			rateQuotePage.verifyShipmentInfoMsg();

			// Step 14: Below the *Request Received* button, Click the *Create BOL* button
			rateQuotePage.clickOnCreateBOL5PM();
			testUtil.setHardWait(2000);

			// Step 15: From the Bill of Lading form, BOL on Details section enter or select
			// the following data: *Record BOL Reference Number*
			// BOL Reference Number: <current date, MMDDYY001>, Select toggle *Auto-assign
			// PRO Number*
			myEstesBillOfLadingPage.enterBOLReferenceNumber(todayDate + "001");
			myEstesBillOfLadingPage.clickOnAutoAssignPROToggle();

			// Step 16: From the Quote Details section, Select (I have read and agree to the
			// Terms of Service) checkbox
			myEstesBillOfLadingPage.clickOnTermsOfService();

			// Step 17: From the Shipper and Consignee Details section, enter the following
			// data:
			myEstesBillOfLadingPage.enterShipperCompanyName("Golden Cannoli");
			myEstesBillOfLadingPage.enterConsigneeCompanyName("AMERISOURCE BERGEN");
			myEstesBillOfLadingPage.enterAddrLine1ForShipper("99 CRESCENT ST");
			testUtil.setHardWait(1000);
			myEstesBillOfLadingPage.enterAddrLine1ForConsignee("9900 JEB STUART PKWY");
			testUtil.setHardWait(500);

			// Step 18: From the Bill Information section: enter or select the following
			// data:
			myEstesBillOfLadingPage.clickBillTo();
			testUtil.setHardWait(1000);
			myEstesBillOfLadingPage.enterBillToThirdParty();
			myEstesBillOfLadingPage.clickTerms();
			myEstesBillOfLadingPage.enterTerms("Prepaid");

			// Step 19: From the BILL TO INFO section, select the *Use My Estes Account
			// Info* checkbox
			myEstesBillOfLadingPage.clickOnUseMyEstesAccInfoForThirdParty();

			// Step 20: Click *Submit BOL* button
			myEstesBillOfLadingPage.ClickOnSubmitBOL();

			// Step 21: *Record* 10-digit PRO #
			String proNumber = myEstesBillOfLadingPage.getProNumber();
			System.out.println(proNumber);
			String proNo = proNumber.replace("-", "");

			// Step 23: *Record* ebol number at the of the url in the address bar
			String strUrl = driver.getCurrentUrl();
			String eBolNumber = strUrl.substring(strUrl.length() - 8);
			System.out.println(eBolNumber);

			// Step 22: From the My Estes dropdown, click on Logout
			myEstesHomePage.clickOnMyEstes();
			myEstesLoginPage.clickOnLogoutButton();
			myEstesHomePage.clickOnConfirmButton();

			// Step 24 - 26: Sign onto EXLAQA with tester credentials
			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.enterUserNPasswordCDOC(userName, password);
			testUtil.setHardWait(5000);
			JagacyUtil jagacyUtil = new JagacyUtil(session);

			// From the LTL/38 Master Menu, Enter 1
			Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
			/*
			 * Added new method to avoid the failure due to user login and to avoid the
			 * manual step to press enter when we see display comments screen.
			 */
			ltl38MasterMenuScreen.verifyScreenTitle();
			ltl38MasterMenuScreen.enterValueOptionField(option);
			testUtil.setHardWait(3000);

			// Step 29 - 30: From the Freight Billing Menu, enter Option: 2, Terminal: 171
			// and Press ENTER
			FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
			FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(option1);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

//	    	        String proNo = "";

			// Step 31 - 32: From the freight bill screen, enter the following:
			freightBillScreen.enterValueFreightBill(proNo);
			freightBillingMenuScreen.enterValueEControlID(eBolNumber);
			jagacyUtil.pressEnter();

			// Step 33: From the eBOL Confirmation screen, press F10 on keyboard to Accept
			EBolConfirmationScreen eBolConfirmationScreen = new EBolConfirmationScreen(session);
			eBolConfirmationScreen.pressF10Key();

			// Step 34: On the second page of the UPDATE screen, enter the following values:
			UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
			freightBillUpdateScreen.enterTS("1");
			freightBillUpdateScreen.enterCons("0103844");
			freightBillUpdateScreen.enterShip("7178618");
			freightBillUpdateScreen.enter3Pt("5068692");
			String poNum = todayDate + "001" +testUtil.randomString(3); //modified to generate unique po#
			freightBillUpdateScreen.enterPONum(poNum);
			freightBillUpdateScreen.enterPuDrNum("9999999", "9");
			freightBillUpdateScreen.enterCubicFeet("1");

			// Step 35: Press ENTER to validate entries
			jagacyUtil.pressEnter();

			// Step 36: On the second page of the UPDATE screen, enter the following values:
			UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
			freightBillUpdateScreen2.enterPK("CT");
			freightBillUpdateScreen2.enterDesc("CARTONS");
			testUtil.setHardWait(5000);

			// Step 37: Press ENTER to validate entries
			jagacyUtil.pressEnter();

			// Step 38: Press ENTER to advance to next entry page
			jagacyUtil.pressEnter();

			// Step 21 - 22: Press ENTER
			jagacyUtil.pressEnter();

			// Step 39: Press F1 to exit
			jagacyUtil.pressF1();

			// Step 40: Access Shipment Tracking app
			driver.get(url3);

			// Step 41 - 42: Search by Pro Number and enter pro number into Tracking box
			shipmentTrackingPage.selectPONumber();
			shipmentTrackingPage.enterPORNumber(poNum);
			testUtil.setHardWait(20000);

			// Step 43: Click on the SEARCH button
			shipmentTrackingPage.clickOnSearchButton();
			shipmentTrackingPage.verifyPickedUpStatus();
//	    	        shipmentTrackingPage.verifyPreviewBarProNum(proNo);
//	    	        shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

			// Step 44: Click on the Caret dropdown to the right of the shipment Status for
			// the freight bill
			shipmentTrackingPage.clickOnExpandArrow();

			// Step 45: From shipment details validate the data elements for the shipment
			// should match the freight bill
			shipmentTrackingPage.verifyValuePRONumber();
			shipmentTrackingPage.verifyValueBOLNumber();
			shipmentTrackingPage.verifyValueEBL();
			shipmentTrackingPage.verifyValuePONumber();
			shipmentTrackingPage.verifyValuePieces();
			shipmentTrackingPage.verifyValueWeight();
			shipmentTrackingPage.verifyValuePickupDate();
			shipmentTrackingPage.verifyValueTransitDays();
			shipmentTrackingPage.verifyValueShipper();
			shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
			shipmentTrackingPage.verifyValueTimeCritical();
			shipmentTrackingPage.verifyValueTimeCritical5PM();
			shipmentTrackingPage.verifyValueConsignee();
			shipmentTrackingPage.verifyValueDestinationTerminal();
			shipmentTrackingPage.verifyValueTerminalPhone();
			shipmentTrackingPage.verifyValueQuestions();

			// Step 46: From the shipment details, click on the Contact us about this
			// shipment hyperlink
			// Cannot validate that email opens

			// Step 47: Validate disclaimers at the bottom of the tracking results
			shipmentTrackingPage.verifyDisclaimer();
		}

		/*
		 * Unauthenticated - Verify Estimated Delivery Date in preview bar when Appt
		 * Date is less than EDD (Estimated Delivery Date)
		 */

		@Test(enabled = true, priority = 20)
		public void executeQZ_6008() throws Exception {

			String userName = "QATSTFRTBL";
			String password = "qatest2019";
			String accountNumber = "2485132";
			String option = "1";
			String reserveOption = "82";
			String originTerminal = "005";
			String destinationTerminal = "037";
			String user = "Test";
			String shipperCode = "0500845";
			String option1 = "2";
			String option2 = "18";
			String option3 = "320";

			String apptDate = testUtil.getBusinessDateShortenedYear("DATE", +6);
			SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
			// Getting current date
			Calendar cal = Calendar.getInstance();
			// Displaying current date in the desired format
			String todayDate = sdf.format(cal.getTime());
			System.out.println(todayDate);
			String bolNum = todayDate + 6008 +testUtil.randomString(2); //Modified to get unique bol#

			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);

			// Step 1: Follow steps in QZ-3287
			// Login to the following application: EXLAQA
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.enterUserNPasswordCDOC(userName, password);
			testUtil.setHardWait(5000);
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			// Enter 1

			Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
			ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
			testUtil.setHardWait(5000);
			ltl38MasterMenuScreen.enterValueOptionField(option);

			testUtil.setHardWait(5000);

			// From the Freight Billing Menu screen, enter the following values: Option: 82,
			// User: TEST, Terminal = <135>, press ENTER
			FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(reserveOption);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			// From the Reserve a String of Freight Bills screen, enter the following
			// values: Bills to reserve: 1, Shipper Code: 2791403
			// and record the pro number
			ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
			reserveFreightBillScreen.enterNumberOfFB("1");
			reserveFreightBillScreen.enterShipperCode(shipperCode);
			String billNum = reserveFreightBillScreen.recordFBNumber();
			String fbNum = originTerminal + billNum;
			System.out.println("Freight Bill No " + fbNum);

			// Press F3 to exit
			jagacyUtil.pressF3();

			// From the Freight Billing Menu, enter Option: 2, Terminal: 135 and Press ENTER
			FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(option1);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			// From the Freight Bill Entry/Update screen, Enter required fields
			freightBillScreen.enterFreightBill(originTerminal, billNum);
			testUtil.setHardWait(2000);
			freightBillingMenuScreen.selectShipper();
			testUtil.setHardWait(5000);
			UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
			freightBillUpdateScreen.enterTS("1");
			freightBillUpdateScreen.enterPcs("3");
			freightBillUpdateScreen.enterTerms("PPD");
			freightBillUpdateScreen.enterWgt("3500");
			freightBillUpdateScreen.enterCons("2485132");
			freightBillUpdateScreen.enterMasterBlNo(bolNum);
			freightBillUpdateScreen.enterPONum("MsLFL7m6Q");
			freightBillUpdateScreen.enterPuDrNum("9999999", "9");
			freightBillUpdateScreen.enterCubicFeet("1");

			// Press ENTER to validate entries
			jagacyUtil.pressEnter();

			// Press ENTER to advance to next entry page
			jagacyUtil.pressEnter();

			// On the second page of the UPDATE screen, enter the following values:
			UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
			freightBillUpdateScreen2.enterClass("50");
			freightBillUpdateScreen2.enterPcs2("3");
			freightBillUpdateScreen2.enterPK("CT");
			freightBillUpdateScreen2.enterDesc("Cartons");
			freightBillUpdateScreen2.enterWgt2("3500");
			testUtil.setHardWait(5000);

			// Press ENTER to validate entries
			jagacyUtil.pressEnter();

			// Press ENTER to advance to next entry page
			jagacyUtil.pressEnter();

			// Press ENTER
			jagacyUtil.pressEnter();

			// Press F1 to exit
			jagacyUtil.pressF1();

			// Select Option to update Freight bill
			FreightBillingMenuScreen freightBillingmenuScreen = new FreightBillingMenuScreen(session);
//	    	        freightBillingmenuScreen.enterFreightBillMenuOption(option3, user, destinationTerminal);
			freightBillingmenuScreen.enterValueOptionField(option3);
			freightBillingmenuScreen.enterTabKey();
			freightBillingmenuScreen.enterTabKey();
			testUtil.setHardWait(2000);
			freightBillingmenuScreen.enterTabKey();
			freightBillingmenuScreen.enterTerminalNumber(destinationTerminal);
			testUtil.setHardWait(2000);

			// Press F8 in Pros Requiring Delivery Appointment screen
			JagacyUtil jagacyuTil = new JagacyUtil(session);
			jagacyuTil.pressF8();
			testUtil.setHardWait(5000);

			// Enter Pro number in Delivery Appointment Inquiry by TID screen and press
			// Enter
			DeliveryAppointmentInquirybyTIDscreen deliveryAppointmentInquiryByTIDscreen = new DeliveryAppointmentInquirybyTIDscreen(
					session);

			deliveryAppointmentInquiryByTIDscreen.enterFreightBill1(originTerminal, billNum);
			testUtil.setHardWait(5000);

			jagacyuTil.pressF1();
			testUtil.setHardWait(5000);

			// Find pro number and select option S
			ProsRequiringDeliveryAppointmentScreen prosRequiringDeliveryAppointmentScreen = new ProsRequiringDeliveryAppointmentScreen(
					session);
			prosRequiringDeliveryAppointmentScreen.searchProNumber(accountNumber);
			testUtil.setHardWait(3000);
			prosRequiringDeliveryAppointmentScreen.selectProNumber(originTerminal, billNum, "S");
			testUtil.setHardWait(3000);

			// Enter values in Delivery Appointment screen
			DeliveryProUpdateScreen deliveryProUpdateScreen = new DeliveryProUpdateScreen(session);
			System.out.println("Date - " + apptDate);
			testUtil.setHardWait(5000);

			deliveryProUpdateScreen.enterApptDate(apptDate);
			deliveryProUpdateScreen.enterTimeRange("900", "900");
			deliveryProUpdateScreen.enterReason("CR");
			testUtil.setHardWait(3000);
			deliveryProUpdateScreen.enterName("EITQA");
			deliveryProUpdateScreen.enterPhoneNum("234", "106", "8701", "254");

			jagacyuTil.pressEnter();
			testUtil.setHardWait(5000);
			// Step 51 - Press F1
			jagacyuTil.pressF1();

			prosRequiringDeliveryAppointmentScreen.selectProNumber(originTerminal, billNum, "S");
			testUtil.setHardWait(3000);

			jagacyuTil.pressEnter();
			testUtil.setHardWait(5000);

			// Press F1
			jagacyuTil.pressF1();
			jagacyuTil.pressF1();

//	    	       String fbNum = "0058091370";

			// Step 2 - 4: Access the following URL for My Estes:
			driver.get(url3);

			// Step 5: From Enter one or more numbers (one per line) box, enter Bill of
			// Lading Number created
			shipmentTrackingPage.clickSearchBy();
			shipmentTrackingPage.clickSearchByBOL();
			shipmentTrackingPage.enterBOLNumber(bolNum);

			// ARE YOU VALIDATING PRONUM, PICKUP DATE AND ESTIMATED DELIVERY DATE?
			// Step 6: Click Search button
			shipmentTrackingPage.clickOnSearchButton();
			shipmentTrackingPage.verifyPickedUpStatus();
			shipmentTrackingPage.verifyPreviewBarProNum(fbNum);
			shipmentTrackingPage.verifyPreviewBarBolNum(bolNum);
			shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

			// Step 7: Validate the Preview Bar populated with *Estimated Delivery Date* in
			// the Estimated Delivery Date Column
			apptDate = testUtil.getBusinessDate("DATE", +6);
			shipmentTrackingPage.verifyEstimatedDeliveryDate(apptDate);

			// Step 8: Click on the Caret icon
			shipmentTrackingPage.clickOnShipmentStatusCaret();
			shipmentTrackingPage.verifyAppointmentDate(apptDate);
			shipmentTrackingPage.verifyAppointmentTime("9:00 AM");
			shipmentTrackingPage.verifyAppointmentStatus("Customer requested appointment");
			shipmentTrackingPage.verifyValuePRONumber();
			shipmentTrackingPage.verifyValueBOLNumber();
			shipmentTrackingPage.verifyValuePONumber();
			shipmentTrackingPage.verifyValuePieces();
			shipmentTrackingPage.verifyValuePickupDate();
			shipmentTrackingPage.verifyValueTransitDays();
			shipmentTrackingPage.verifyValueShipper();
			shipmentTrackingPage.verifyValueEstimatedDeliveryDate();
			shipmentTrackingPage.verifyValueConsignee();
			shipmentTrackingPage.verifyValueDestinationTerminal();
			shipmentTrackingPage.verifyValueQuestions();
			shipmentTrackingPage.verifyValueTerminalPhone();
			// Terminal Fax field is no longer there- confirmed with Dee

			// Step 9: since this step deal with email validation- its not automated.

		}

		/*
		 * Authenticated - Verify Show only my shipments toggle should not display for Pro Number
		 */

		@Test(enabled = true, priority = 21)

		public void executeQZ_11207() throws InterruptedException {

			// Step 1: Open the following link: https://estes-express-uat.estesinternal.com
			myEstesHomePage.clickOnMyEstes();

			// Step 2: Enter the following credentials
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username4);
			myEstesLoginPage.enterPassword(password4);
			myEstesLoginPage.clickOnLoginButton();

			// From My Estes home page click on the following tab: Track
			myEstesHomePage.clickOnTrack();

			// From Track page, navigate to Shipment Tracking application
			myEstesHomePage.clickOnShipmentTraking();

			// Step 3: Default to Pro Number
			// Validation: Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 4 : From the Search by dropdown, select Bill of Lading Number
			shipmentTrackingPage.selectBOLNumber();

			// Validation: Show only my shipments toggle is ON
			shipmentTrackingPage.verifyToggleIsON();

			// Validation: Toggle color = 'YELLOW'
			shipmentTrackingPage.verifyToggleColor();

			// Step 5 : From the Search by dropdown, select Pickup Request Number
			shipmentTrackingPage.selectPickupRequestNumber();

			// Validation : Show only my shipments toggle is ON
			shipmentTrackingPage.verifyToggleIsON();

			// Validation: Toggle color = 'YELLOW'
			shipmentTrackingPage.verifyToggleColor();

			// Step 6 : From the Search by dropdown, select PO Number
			shipmentTrackingPage.selectPONumber();

			// Validation: Show only my shipments toggle is ON
			shipmentTrackingPage.verifyToggleIsON();

			// Validation: Toggle color = 'YELLOW'
			shipmentTrackingPage.verifyToggleColor();

			// Step-7 : From the Search by dropdown, select Interline PRO Number
			shipmentTrackingPage.selectInterlinePRONumber();

			// Validation: Show only my shipments toggle is ON
			shipmentTrackingPage.verifyToggleIsON();

			// Validation : Toggle color = 'YELLOW'
			shipmentTrackingPage.verifyToggleColor();

			// Step-8 : From the Search by dropdown, select Load Order Number
			shipmentTrackingPage.selectLoadOrderNumber();

			// Validation : Show only my shipments toggle is ON
			shipmentTrackingPage.verifyToggleIsON();

			// Validation: Toggle color = 'YELLOW'
			shipmentTrackingPage.verifyToggleColor();

			// Step-9 : From the Search by dropdown, select EXLAID Number
			shipmentTrackingPage.selectEXLAID();

			// Validation: Show only my shipments toggle is ON
			shipmentTrackingPage.verifyToggleIsON();

			// Validation: Toggle color = 'YELLOW'
			shipmentTrackingPage.verifyToggleColor();
		}

		/*
		 * Unauthenticated - Verify Show only my shipments toggle should not display for
		 * Pro Number
		 */

		@Test(enabled = true, priority = 22)

		public void executeQZ_11208()
				throws InterruptedException {

			// Step 1: Open the following link: https://estes-express-uat.estesinternal.com

			// From My Estes home page click on the following tab: Track
			myEstesHomePage.clickOnTrack();

			// From Track page, navigate to Shipment Tracking application
			myEstesHomePage.clickOnShipmentTraking();

			// Step 2: From Search by drop down deafult is Pro number

			// Validation : Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 3 : From the Search by dropdown, select Bill of Lading Number
			shipmentTrackingPage.selectBOLNumber();

			// Validation: Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 4 : From the Search by dropdown, select Pickup Request Number
			shipmentTrackingPage.selectPickupRequestNumber();

			// Validation: Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 5 : From the Search by dropdown, select PO Number
			shipmentTrackingPage.selectPONumber();

			//Validation: Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 6: From the Search by dropdown, select Interline PRO Number
			shipmentTrackingPage.selectInterlinePRONumber();

			// Validation: Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 7 : From the Search by dropdown, select Load Order Number
			shipmentTrackingPage.selectLoadOrderNumber();

			// Validation: Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

			// Step 8 : From the Search by dropdown, select EXLAID Number
			shipmentTrackingPage.selectEXLAID();

			// Validation : Show only my shipments toggle is not displayed
			shipmentTrackingPage.verifyShowOnlyMyShipmentsToggleIsNotDisplayed();

		}

		/**
		 *
		 * this test is turned off until the method is optimized to break the loop.
		 *
		 * Review comments - 27 April 2022 - Turning off this test as this test need verification by the author of this test. T
		 * Test steps have steps to be followed on day 1 and day 2
		 * As per test steps, Day - 1 FB creation, Road manifest process[Step 1 to 39], Day 2 -  Verify shipment details in my estes app [Step 40 to 49]
		 */
		/*
		 * Authenticated - Verify that for PRO with past Estimated Delivery Date in
		 * Eastern time zone the user should see a disclaimer display in the disclaimer
		 * section of the shipment tracking results
		 */

		@Test(enabled = false, priority = 23)
		public void executeQZ_11198() throws Exception {

			String userName = "QATSTFRTBL";
			String password = "qatest2019";
			String option = "1";
			String reserveOption = "82";
			String originTerminal = "006";
			String destinationTerminal = "171";
			String user = "Test";
			String shipperCode = "6545352";
			String option1 = "2";
			String option2 = "18";

			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);

			// Step 1 - 4: Login to the following application: EXLAQA
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.enterUserNPasswordCDOC(userName, password);
			testUtil.setHardWait(5000);
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			// From the LTL/38 Master Menu, Enter 1
			Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
			ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
			ltl38MasterMenuScreen.enterValueOptionField(option);

			testUtil.setHardWait(3000);
			// Step 7 - 8: From the Freight Billing Menu screen, enter the following values:
			// Option: 82, User: TEST, Terminal = <006>, press ENTER
			FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(reserveOption);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			// Step 9 - 10 From the Reserve a String of Freight Bills screen, enter the
			// following values: Bills to reserve: 1, Shipper Code: 2791403
			// and record the pro number
			ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
			reserveFreightBillScreen.enterNumberOfFB("1");
			reserveFreightBillScreen.enterShipperCode(shipperCode);
			String billNum = reserveFreightBillScreen.recordFBNumber();
			String fbNum = originTerminal + billNum;
			System.out.println("Freight Bill No " + fbNum);

			// Press F3 to exit
			jagacyUtil.pressF3();

			// Step 11-12 : From the Freight Billing Menu, enter Option: 2, Terminal: 006
			// and Press ENTER
			FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(option1);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			// Step 13: From the Freight Bill Entry/Update screen, Enter required fields
			freightBillScreen.enterFreightBill(originTerminal, billNum);
			testUtil.setHardWait(2000);
			UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
			freightBillUpdateScreen.enterTS("1");
			freightBillUpdateScreen.enterDTValue(destinationTerminal); //Added
			freightBillUpdateScreen.enterPcs("5");
			freightBillUpdateScreen.enterTerms("PPD");
			freightBillUpdateScreen.enterWgt("1000");
			freightBillUpdateScreen.enterCons("0617258");
			freightBillUpdateScreen.enter3Pt("5068692");
			freightBillUpdateScreen.enterMasterBlNo("083020211300");
			freightBillUpdateScreen.enterPONum("UkqGQWBSk");
			freightBillUpdateScreen.enterPuDrNum("9999999", "9");
			freightBillUpdateScreen.enterCubicFeet("1");

			// Step 14: Press ENTER to advance to next entry page
			jagacyUtil.pressEnter();

			// Step 15: On the second page of the UPDATE screen, enter the following values:
			UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
			freightBillUpdateScreen2.enterClass("50");
			freightBillUpdateScreen2.enterPcs2("5");
			freightBillUpdateScreen2.enterPK("CT");
			freightBillUpdateScreen2.enterDesc("CARTONS");
			freightBillUpdateScreen2.enterWgt2("1000");
			testUtil.setHardWait(5000);

			// Step 16: Press ENTER to validate entries
			jagacyUtil.pressEnter();

			testUtil.setHardWait(3000);

			// Step 17: Press ENTER
			jagacyUtil.pressEnter();
			testUtil.setHardWait(3000);
			// Press ENTER
			jagacyUtil.pressEnter();

			testUtil.setHardWait(3000);

			// Step 18: Press F1 to exit
			jagacyUtil.pressF1();

			// Step19: Load pro onto Road Manifest and dispatch
			// Navigate to the Freight Billing Menu and select Option 18, Central Dispatch.
			freightBillingMenuScreen.enterValueOptionField(option2);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);
			session.waitForChange(1000);
			// Press SHIFT + F4, Road Manifest Setup Entry.
			LinehaulDispatchScreen linehaulDispatchScreen = new LinehaulDispatchScreen(session);
			linehaulDispatchScreen.enterShiftF4();
			session.waitForChange(5000);

			// From the Road Manifest Setup Entry screen, enter the following values: Dest
			// TID: Trailer# : ?, and press ENTER
			RoadManifestSetupEntryScreen roadManifestSetupEntryScreen = new RoadManifestSetupEntryScreen(session);
			roadManifestSetupEntryScreen.enterDestTID(destinationTerminal);
			roadManifestSetupEntryScreen.enterTrailer("?");
			jagacyUtil.pressEnter();
			session.waitForChange(3000);

			// From the Trailer # pop-up screen: Replace the value LTR with MTY.
			roadManifestSetupEntryScreen.changeStatus("MTY");
			session.waitForChange(3000);

			// Press ENTER
			jagacyUtil.pressEnter();
			session.waitForChange(3000);

			// In the Position To field (Pos to:), enter the following: 52
			roadManifestSetupEntryScreen.changePosTo("52");
			session.waitForChange(5000);

			// Press ENTER
			jagacyUtil.pressEnter();
			session.waitForChange(6000);

			// Place a '1' next to the trailer to be selected. Record your trailer
			// selection.
			roadManifestSetupEntryScreen.selectTrailer2();
			jagacyUtil.pressEnter();
			String trailer = roadManifestSetupEntryScreen.recordTrailerNumber();

			// Press ENTER
			jagacyUtil.pressEnter();

			// Press F1
			jagacyUtil.pressF1();
			jagacyUtil.pressF1();
			freightBillingMenuScreen.enterValueOptionField(option2);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			/**
			 * This method is going in loop "Next Page"
			 */
			// Press the PAGE DOWN key to find the newly assigned Trailer# In the O column
			// adjacent to the trailer number,
			// enter the following value: M and press ENTER
			linehaulDispatchScreen.selectTrailer(trailer, "M");
			testUtil.setHardWait(5000);

			// From the Select RM Option window, Type in 1 in the data field at the top of
			// the window and Press ENTER
			linehaulDispatchScreen.selectRMOption("1");
			jagacyUtil.pressEnter();
			RoadManifestEntryUpdateScreen roadManifestEntryUpdateScreen = new RoadManifestEntryUpdateScreen(session);

			// From the Road Manifest Entry/Update screen, enter the following values: Seal:
			// <ANY 4 - 5 DIGIT NUMBER>,
			// Can the Freight be added?: Y, Enter the recently created Freight Bill numbers
			// in the field beneath the OOOBBBBBBB field, Press ENTER
			roadManifestEntryUpdateScreen.enterSeal("654321");
			roadManifestEntryUpdateScreen.enterFreightBillCanBeAdded("Y");
			String bill = fbNum;
			roadManifestEntryUpdateScreen.enterFreightBill(bill);
			jagacyUtil.pressEnter();

			// From the Enter Percent/Quarter Trailer screen, enter any percentage value
			// below 100% in all sections.
			// Press ENTER to validate the data, Press F10 to update the Manifest Cube
			// update, After the Equipment on Trailer
			// pop-up is displayed, press Enter to finalize the cube assignment, Press F1
			EnterPercentageQuarterTrailerScreen enterPercentageQuarterTrailerScreen = new EnterPercentageQuarterTrailerScreen(
					session);
			enterPercentageQuarterTrailerScreen.enterFloorAndHeightForAllSections(" 80");
			jagacyUtil.pressEnter();
			enterPercentageQuarterTrailerScreen.clickF10();
			jagacyUtil.pressEnter();
			enterPercentageQuarterTrailerScreen.clickF1();
			jagacyUtil.pressF1();
			freightBillingMenuScreen.enterValueOptionField(option2);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			// From the Line Haul Dispatch screen, Press the PAGE DOWN key to find the newly
			// assigned Trailer#, Type P
			// in the 'O' column. Press ENTER
			linehaulDispatchScreen.selectTrailer(trailer, "P");
			testUtil.setHardWait(5000);

			// From the Select Driver to Pre-plan a Load screen, enter the following values:
			// Name: MISC, Press ENTER to
			// re-sort the driver list, Type in the value 1 adjacent to the MISC DRIVER
			// entry, press ENTER
			linehaulDispatchScreen.selectAndPlanDriver("MISC DRIVER", destinationTerminal);
			testUtil.setHardWait(3000);

			// Enter next terminal and press ENTER
			jagacyUtil.pressF1();
			freightBillingMenuScreen.enterValueOptionField(option2);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			// From the Line Haul Dispatch screen, Press the PAGE DOWN key to find the
			// recently assigned Trailer#,
			// Type D in the O column and press ENTER
			linehaulDispatchScreen.selectTrailer(trailer, "D");

			// From the Road Manifest Check-Out screen, enter the following data values,
			// Enter following details in checkout screen:
			// Run number: EXTRA, Tractor: /ONEWAY, HUB?: Y, press ENTER, Equipment errors
			// found. Please verify entry. Are you sure you want to dispatch?
			// Type in Y, press ENTER, Next pop-up window - Type in Y, press ENTER
			RoadManifestCheckoutScreen roadManifestCheckoutScreen = new RoadManifestCheckoutScreen(session);
			roadManifestCheckoutScreen.enterRunNumber("EXTRA");
			roadManifestCheckoutScreen.enterTractor("/ONEWAY");
			roadManifestCheckoutScreen.enterHub("Y");
			jagacyUtil.pressEnter();

			roadManifestCheckoutScreen.enterDispatchType("Y");

			// Press Enter
			jagacyUtil.pressEnter();
			// jagacyUtil.pressEnter();

			// Step-21,22 Press F1 2x to exit
			jagacyUtil.pressF1();
			jagacyUtil.pressF1();
			testUtil.setHardWait(3000);

			// Step 23-24: Enter Option: 18, User: TEST, Terminal: 011
			freightBillingMenuScreen.enterValueOptionField(option2);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber("171");
			testUtil.setHardWait(3000);

			// Step 25: Select option A next to the specific trailer.
			linehaulDispatchScreen.selectTrailer(trailer, "A");
			testUtil.setHardWait(3000);

			// Step 26: Press Enter
			// Note : this step is done in step 21

			// Step 27: From the Road Manifest Check-In screen, do the following: Press
			// ENTER
			jagacyUtil.pressEnter();
			testUtil.setHardWait(3000);

			// Step 28: From The Driver Break window. Press Enter
			jagacyUtil.pressEnter();
			testUtil.setHardWait(3000);

			// Step-29: Press F5 and enter the following values:

			jagacyUtil.pressF17();
			testUtil.setHardWait(3000);

			linehaulDispatchScreen.selectRMOption("11");
			testUtil.setHardWait(3000);

			// Step 30 : Press Enter
			jagacyUtil.pressEnter();
			testUtil.setHardWait(8000);

			// Step 31-34 :From the Road Manifests to be unload screen, locate manifest and
			// place X in the field next to it Unload trailer -> Enter Y next to unload
			// everything and press Enter
			RoadManifestToBeUnloadedScreen roadManifestToBeUnloadedScreen = new RoadManifestToBeUnloadedScreen(session);
			roadManifestToBeUnloadedScreen.unloadTrailer(trailer);
			testUtil.setHardWait(8000);

			// Step-35 : Press F1 2x to exit
			jagacyUtil.pressF1();
			testUtil.setHardWait(2000);
			jagacyUtil.pressF1();
			testUtil.setHardWait(2000);

			// Step-36 : Validate freight is at the DT dock, enter the following values:
			freightBillingMenuScreen.enterValueOptionField("1");

			jagacyUtil.pressEnter();
			testUtil.setHardWait(2000);
			// Step-37 -40
			freightBillScreen.enterFreightBill(originTerminal, billNum);
			testUtil.setHardWait(2000);
			jagacyUtil.pressF8();
			testUtil.setHardWait(3000);

			if (session != null) {
				session.abort();
				session.close();
			}

			session = null;

			// Step 41: Access Shipment Tracking app
			driver.get(url3);

			// Step 42: Login with smokelocal credentials
			shipmentTrackingPage.clickOnMyEstes();
			shipmentTrackingPage.clickOnLogin();
			myEstesLoginPage.enterUserName(username4);
			myEstesLoginPage.enterPassword(password4);
			myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

			// From My Estes home page hover mouse over the following menu: Track
			myEstesHomePage.clickOnTrack();
			// From Track menu, click on Shipment Tracking
			myEstesHomePage.clickOnShipmentTraking();

			// Step 43 & 44: From the Search box enter a valid 10 digit freight bill number
			shipmentTrackingPage.enterPORNumber(fbNum);
			testUtil.setHardWait(20000);

			// Step 45: Click on the SEARCH button
			shipmentTrackingPage.clickOnSearchButton();
			testUtil.setHardWait(10000);

			// Step 45-46 Validate data in the *Preview Bar*
			shipmentTrackingPage.verifyStatus("In Transit");
			//shipmentTrackingPage.verifyDataReturnedInPreviewBar();
			shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

			// Step 47: Click the Expand All button
			shipmentTrackingPage.clickOnExpandArrow();

			// Step 47 Validate the Milestone Data
			//shipmentTrackingPage.verifyMilestone("In Transit");
			shipmentTrackingPage.verifyInTransitMilestone();

			// Step 48: Validate data in the *Shipment Details* should match the data fields
			// for the freight bill
			shipmentTrackingPage.verifyValuePRONumber();
			shipmentTrackingPage.verifyValueBOLNumber();
			shipmentTrackingPage.verifyValuePONumber();
			shipmentTrackingPage.verifyValueWRCertificate();
			shipmentTrackingPage.verifyValuePieces();
			shipmentTrackingPage.verifyValueWeight();
			shipmentTrackingPage.verifyValuePickupDate();
			shipmentTrackingPage.verifyValueTransitDays();
			shipmentTrackingPage.verifyValueShipper();
			shipmentTrackingPage.verifyValueEstimatedDeliveryDateNotDisplayed();
			shipmentTrackingPage.verifyValueConsignee();
			shipmentTrackingPage.verifyValueDeliveryReceipt();
			shipmentTrackingPage.verifyValueDestinationTerminal();
			shipmentTrackingPage.verifyValueTerminalPhone();
			shipmentTrackingPage.verifyValueQuestions();
			shipmentTrackingPage.verifyValueFreightCharges();

			// Step 49: Validate disclaimer should display
			shipmentTrackingPage.validateDisclaimer();

			// Logoff and close browser
			myEstesLoginPage.clickOnMyEstes();
			testUtil.setHardWait(2000);
			myEstesLoginPage.clickOnLogoutButton();

			myEstesLoginPage.clickOnLogoutConfirmButton();

		}


		/**
		 *
		 * Verify Shipment Tracking API response displayed with "Expedited Code &
		 * Description" when the Freight Bill created with CGM12 and request submitted
		 * using PO by a "Not a Party" to Shipment
		 *
		 * @throws Exception
		 */


		@Test(enabled = true, priority = 24)
		public void executeQZ_11733() throws Exception {
			//Step 1 - 13

			String userName = "QATSTFRTBL";
			String passWord = "qatest2019";
			String option = "1";
			String reserveOption = "82";
			String originTerminal = "005";
			String destinationTerminal = "037";
			String user = "APITEST";
			String shipperCode = "0500845";
			String option1 = "2";
			String option2 = "18";
			String consignee = "3015592";

			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);

			// Step 1 - 4: Login to the following application: EXLAQA
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.enterUserNPasswordCDOC(userName, passWord);
			testUtil.setHardWait(5000);
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			// From the LTL/38 Master Menu, Enter 1
			Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
			ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
			ltl38MasterMenuScreen.enterValueOptionField(option);

			testUtil.setHardWait(3000);
			// Step 7 - 8: From the Freight Billing Menu screen, enter the following values:
			// Option: 82, User: TEST, Terminal = <006>, press ENTER
			FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(reserveOption);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
			reserveFreightBillScreen.enterNumberOfFB("1");
			reserveFreightBillScreen.enterShipperCode(shipperCode);
			String billNum = reserveFreightBillScreen.recordFBNumber();
			String fbNum = originTerminal + billNum;
			System.out.println("Freight Bill No " + fbNum);

			// Press F3 to exit
			jagacyUtil.pressF3();

			//Steps 14 - 27

			// Step 11-12 : From the Freight Billing Menu, enter Option: 2, OT
			// and Press ENTER
			FreightBillingMenuScreen freightBillScreen = new FreightBillingMenuScreen(session);
			freightBillingMenuScreen.enterValueOptionField(option1);
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			freightBillScreen.enterFreightBill(originTerminal, billNum);
			testUtil.setHardWait(2000);

			testUtil.verifyReconcilationBySID(session, fbNum);
			testUtil.setHardWait(2500);

			// Step 13: From the Freight Bill Entry/Update screen, Enter required fields

			UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
			freightBillUpdateScreen.enterTS("1");
			freightBillUpdateScreen.enterDTValue(destinationTerminal);
			freightBillUpdateScreen.enterPcs("5");
			freightBillUpdateScreen.enterTerms("PPD");
			freightBillUpdateScreen.enterWgt("1000");
			freightBillUpdateScreen.enterCons(consignee);
			freightBillUpdateScreen.enterMasterBlNo("BOLAPI"+fbNum);
			freightBillUpdateScreen.enterPONum("POAPI"+fbNum);
			freightBillUpdateScreen.enterPuDrNum1("1214", "1");
			freightBillUpdateScreen.enterCubicFeet("5");
			freightBillUpdateScreen.enterCartTo("SPED");
			freightBillUpdateScreen.enterBondShed("NONE");
			freightBillUpdateScreen.enterCustomsBroker("MISC");
			freightBillUpdateScreen.enterCustomsInvoice("Y");
			freightBillUpdateScreen.enterCertOfOrigin("N");

			// Step 14: Press ENTER to advance to next entry page
			jagacyUtil.pressEnter();

			// Step 15: On the second page of the UPDATE screen, enter the following values:
			UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
			freightBillUpdateScreen2.enterClass("50");
			freightBillUpdateScreen2.enterPcs2("5");
			freightBillUpdateScreen2.enterPK("SK");
			freightBillUpdateScreen2.enterDesc("SKIDS");
			freightBillUpdateScreen2.enterWgt2("1000");
			testUtil.setHardWait(5000);

			// Step 16: Press ENTER to validate entries
			jagacyUtil.pressEnter();

			testUtil.setHardWait(3000);

			// Step 17: Press ENTER
			jagacyUtil.pressEnter();
			testUtil.setHardWait(3000);

			UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
			freightBillUpdateScreen3.enterShippingInstruction1("CGM12");

			jagacyUtil.pressEnter();
			testUtil.setHardWait(500);
			jagacyUtil.pressF1();

			testUtil.setHardWait(35000);

			//Step 28 - 40

			String baseURI = "https://uat-cloudapi.estes-express.com/authenticate";
			String username = "smokelocal";
			String password = "smokelocal";

			Response response = given().auth().basic(username, password)
						.header("apikey", "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ").when().post(baseURI).then().log().all().extract()
						.response();

			String token = response.path("token");
			assertNotNull(token);
			System.out.println("Authentication token is " + token);

			Map<String, String> paramValues = new HashMap<>();
			paramValues.put("party-to", "false");
			paramValues.put("po", "POAPI"+fbNum);

			baseURI = "https://uat-cloudapi.estes-express.com/v1/shipments/history";

			given().auth().oauth2(token).header("apikey", "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ")
					.header("Content-Type", "application/json").params(paramValues).when().get(baseURI).then().log().all().statusCode(200)
					.body("data[0].pro", equalTo(fbNum))
					.body("data[0].expeditedCode", equalTo("CGM2"))
					.body("data[0].expeditedDescription", equalTo("Time Critical Shipment Due by 12 pm"));

			freightBillingMenuScreen.enterValueOptionField("90");
			freightBillingMenuScreen.enterValueUserField(user);
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber(originTerminal);

			ltl38MasterMenuScreen.enterValueOptionField("90");


		}

		/**
		 *
		 * @throws Exception
		 * Verify Shipment Tracking API populated with "isTruckload = True" when the pro is Truckload and request submitted by Payor Party to Shipment
		 *
		 */
		@Test(enabled = true, priority = 25)

		public void executeQZ_11742() throws Exception {

		//Step 1: Execute the follow test case: https://estesexpress.atlassian.net/browse/QZ-10382

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
			String passWord = "nithyadev";
			String name = "myJagacyVT";

			String terminal = "dec-vt220";

			session = new SessionVt(name, "exlaqa", terminal);

			session.open();

			testUtil.setHardWait(2000);

			LoginScreen loginScreen = new LoginScreen(session);
			IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);

			Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);

			FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			loginScreen.logon(userName, passWord);
			testUtil.setHardWait(2000);
			ibmMainMenuScreen.verifyIBMMainMenuScreen();
			UpdateScreen updateScreen1 = new UpdateScreen(session);
			UpdateScreen2 updateScreen2 = new UpdateScreen2(session);
			UpdateScreen3 updateScreen3 = new UpdateScreen3(session);


			// Step-19 : From the command line, enter: CALL XXC870

			ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

			// Step-20 : From the *LTL Master Menu*, select: "1"

			ltl38MasterMenuScreen.enterValueOptionField("1");

			// Step-21 : From the *Freight Billing Menu*, enter the following:

			freightBillingMenuScreen.enterValueOptionField("1");
			freightBillingMenuScreen.enterValueUserField("Truckload");
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber("020");


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
			testUtil.setHardWait(4000);
			freightBillingMenuScreen.verifyDate(date);

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

		/* Step 2: From the 'Freight Billing Menu', enter the following option: Option: 3

		 * Step 3: Press Enter

		 * Step 4: Enter the freight bill number on the screen

		 * Step 5: Press Enter

		 * Step 6: On the 'First Update Screen', do the following: Press Enter

		 * Step 7: Press Enter

		 * Step 8: One the 'Second Update Screen': Press Enter

		 * Step 9: On the 'Third Update Screen': Press Enter

		 * Step 10: Press Enter

		 * Step 11: Press Enter

		 */

			jagacyUtil.pressF1();
			freightBillingMenuScreen.enterValueOptionField("3");
			jagacyUtil.pressEnter();
			freightBillingMenuScreen.enterFreightBill("020", proNum);
			updateScreen1.pressEnter();
			jagacyUtil.pressEnter();
			testUtil.setHardWait(1500);
			updateScreen1.inputUpdateScreen("PO TBP754");
			updateScreen2.pressEnterKey();
			updateScreen3.pressEnterKey();
			jagacyUtil.pressEnter();
			jagacyUtil.pressEnter();
			jagacyUtil.pressF1();


		/*

		 * Step 12: Note: Recommended to wait few secs (like 30 secs), Before executing all below steps.

		 * This is just to ensure the Freight Bill is rated and all excepted values are Transferred to Mongodb.

		 */
			testUtil.setHardWait(45000);

		/*

		 * Step 13: Open the following application: Postman

		 * Step 14: From the Postman application, under Collections Tab -> Expand the 'API Strategy Shipment Tracking API' Folder

		 * Step 15: Click: 'Authenticate MyEstes User' Request

		 * Step 16: From Authenticate MyEstes User Window, Click: 'Authorization' Tab

		 * Step 17: From Authorization Tab, Enter the following Values: Username: smokelocal, Password: smokelocal

		 * Step 18: Click Sent Button -> Record the 'token' value between the double quotes from the response.

		 * Step 19: From the 'API Stragey Shipment Tracking API' Folder -> Click History API Request

		 * Step 20: From the 'History API Window' -> Click Authorization Tab

		 * Step 21: From the Authorization Tab, Enter the following Values: Token: <Recorded Above>

		 * Step 22: From the Params Tab, Click <Params> tab

		 * Step 23: Select the following PRO: Check Box and enter the values from above recorded

		 * *Note all the other checkboxes should be de-selected.

		 * Step 24: Click Send

		 */

			String baseURI = "https://uat-cloudapi.estes-express.com/authenticate";
			String username = "smokelocal";
			String password = "smokelocal";

			Response response = given().auth().basic(username, password)

					.header("apikey", "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ").when().post(baseURI).then().log().all().extract()
					.response();

			String authenticationToken = response.path("token");
			assertNotNull(authenticationToken);
			Map<String, String> values = new HashMap<String, String>();
			baseURI = "https://uat-cloudapi.estes-express.com/v1/shipments/history";
			String pro = "020" + proNum;
			values.put("pro", pro);

			response = RestAssured.given().auth().oauth2(authenticationToken).header("apikey", "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ").header("Content-Type", "application/json").given().
				queryParams(values).when().get(baseURI).then().log().all().extract().response();

		/*
		 * Step 25: Verify status = 200
		 * pro = OTPRO#
		 * isResidential = false;
		 * isTrucload = true;
		 */
			assertTrue(response.getStatusCode() == 200);
			assertTrue(response.path("data[0].pro").equals(pro));
			assertFalse(response.path("data[0].isResidential"));
			assertTrue(response.path("data[0].isTruckload"));

			/*
			 * Step 26: Execute below steps to sign off from EXLAQA
			 * Option: 90
			 * User: APITEST
			 * Terminal: OT#
			 * Step 27: Press Enter
			 * Step 28: From LTL/38 Master Menu, enter Option 90
			 * Step 29: Press Enter
			 */

			jagacyUtil.pressF1();
			ltl38MasterMenuScreen.enterValueOptionField("1");
			freightBillingMenuScreen.enterValueOptionField("90");
			freightBillingMenuScreen.enterValueUserField("APITEST  ");
			freightBillingMenuScreen.enterTabKey();
			freightBillingMenuScreen.enterTerminalNumber("020");
			ltl38MasterMenuScreen.enterValueOptionField("90");

		}


		/**@author Ajitha
		 *
		 * Shipment Tracking - Unauth - Verify error message should be displayed on invalid
		 * pickup request data and can be tracked via the Pickup Visibility app
		 * @throws InterruptedException
		 *
		 */

		@Test(enabled = true, priority = 26)
		public void executeQZ_12008() throws InterruptedException {

			String pickupRequestNo 	=  "PUR007123";
			String errMsg 			=  "Are you trying to track a pickup request? Shipment details are not yet available for Pickup Request Number ";
			String newWindowUrl 	=  "https://estes-express-uat.estesinternal.com/myestes/tracking/pickups?pur=";
			String errMsg1 			=  "Are you trying to track a shipment? Pickup Request number "+pickupRequestNo+" not found.";
			String errMultiplePO 	=  "Are you trying to track a pickup request? Shipment details are not yet available for Pickup Request Numbers PUR007124, PUR007125, PUR007126, and 3 others.";
			String errForMultiplePO =  "Are you trying to track a shipment? Pickup Request numbers PUR007124, PUR007125, PUR007126, and 3 others not found.";

			//Step 1: Open the following link: https://estes-express-uat.estesinternal.com
			//Step 2: From My Estes home page hover mouse over the following menu: Track
			myEstesHomePage.clickOnTrack();

			//Step 3: From Track page, navigate to Shipment Tracking application
			//Step 4: From Track page navigate to the following application: <Shipment Tracking>
			myEstesHomePage.clickOnShipmentTraking();

			//Step 5: From the Search drop-down select Pickup Request Number
			shipmentTrackingPage.selectPickupRequestNumber();

			//Step 6: From Enter one or more numbers (one per line) box, enter the following invalid data:PUR007123
			shipmentTrackingPage.enterPORNumber(pickupRequestNo);

			//Step 7: Click the Search button
			shipmentTrackingPage.clickOnSearchButton();

			//Validate the following error message:
			//Are you trying to *track a pickup request*? Shipment details are not yet available for Pickup Request Number PUR007123.
			shipmentTrackingPage.validateErrorMessage(pickupRequestNo, errMsg);

			//Step 8: Click on the *track a pickup request?* link in the error message
			shipmentTrackingPage.clickOnTrackPickupRequestLink();

			//A new window is opened and the user is navigated to the Pickup Visibility app
			shipmentTrackingPage.verifyUserNavigatedToNewWindow(1,newWindowUrl+pickupRequestNo);

			//Step 9:From Pickup Visibility app, validate the error message
			//Validate the following error message:
			//Are you trying to *track a shipment?* Pickup Request number PUR007123 not found.
			shipmentTrackingPage.verifyErrMsgInNewTab(errMsg1);

			//Step 10: Tab back to the Shipment Tracking app and repeat (steps 6, 7)
			shipmentTrackingPage.switchBackToMainPage();

			//Step 11: From Enter one or more numbers (one per line) box, enter the following invalid data:
			shipmentTrackingPage.enterPORNumber("");
			shipmentTrackingPage.enterMultiplePRONumber("PUR007124","PUR007125","PUR007126","PUR007127","PUR007128","PUR007129");

			//Step 12: Click Search button
			shipmentTrackingPage.clickOnSearchButton();

			//Validate the following error message:
			//Are you trying to *track a pickup request?* Shipment details are not yet available for Pickup Request Numbers PUR007124, PUR007125, PUR007126, and 3 *others*.
			shipmentTrackingPage.validateErrMsg(errMultiplePO);

			//Step 13: Click on the *others* link in the error message
			shipmentTrackingPage.clickOnOthersLink();

			//A new window is opened and the user is navigated to the Pickup Visibility app
			shipmentTrackingPage.verifyUserNavigatedToNewWindow(2,newWindowUrl+"PUR007124,PUR007125,PUR007126,PUR007127,PUR007128,PUR007129");

			//Step 14: From Pickup Visibility app, validate the error message
			//Validate the following error message:
			//Are you trying to *track a shipment?* Pickup Request numbers PUR007124, PUR007125, PUR007126, and 3 others not found.
			shipmentTrackingPage.verifyErrMsgInNewTab(errForMultiplePO);

			//Step 15: Validate results are returned in order as entered in data and query string in address should data in the correct order
			//Validate the following query string data:
			//https://estes-express-uat.estesinternal.com/myestes/tracking/pickups?pur=PUR007124,PUR007125,PUR007126,PUR007127,PUR007128,PUR007129
			shipmentTrackingPage.verifyUserNavigatedToNewWindow(2,newWindowUrl+"PUR007124,PUR007125,PUR007126,PUR007127,PUR007128,PUR007129");

			//Step 16: Tab back to the Shipment Tracking app
			shipmentTrackingPage.switchBackToMainPage();

			//Step 17: From the error message bar, click the x
			shipmentTrackingPage.clickOnClosebutton();

		}


		/** @author Ajitha
		 *
		 * Shipment Tracking - Auth - Verify error message should be displayed on
		 * invalid pickup request data and can be tracked via the Pickup Visibility app
		 * @throws InterruptedException
		 *
		 */

		@Test(enabled = true, priority = 27)
		public void executeQZ_12007() throws InterruptedException {

			String pickupReqNo 		= "PUR007123";
			String errMsg 			=  "Are you trying to track a pickup request? Shipment details are not yet available for Pickup Request Number ";
			String newWindowUrl 	=  "https://estes-express-uat.estesinternal.com/myestes/tracking/pickups?pur=";
			String errMsg1 			=  "Are you trying to track a shipment? Pickup Request number "+pickupReqNo+" not found.";
			String errMultiplePO 	=  "Are you trying to track a pickup request? Shipment details are not yet available for Pickup Request Numbers PUR007124, PUR007125, PUR007126, and 3 others.";
			String errForMultiplePO =  "Are you trying to track a shipment? Pickup Request numbers PUR007124, PUR007125, PUR007126, and 3 others not found.";

			//Step 1: Access the following URL for My Estes:
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();

			//Step 2: Login to My Estes application with the following credentials:Smokenat
			myEstesLoginPage.enterUserName(username1);
			myEstesLoginPage.enterPassword(password1);
			myEstesLoginPage.clickOnLoginButton();

			//Step 3: From My Estes home page hover mouse over the following menu:Track
			myEstesHomePage.clickOnTrack();

			//Step 4: From Track menu, click on Shipment Tracking
			//Step 5: From Track page navigate to the following application:<Shipment Tracking>
			myEstesHomePage.clickOnShipmentTraking();

			//Step 6: From the Search dropdown select Pickup Request Number
			shipmentTrackingPage.selectPickupRequestNumber();

			//Step 7: click on the *show my only shipments* toggle to turn off the toggle
			shipmentTrackingPage.turnOffShowOnlyMyShipmentsToggle();

			//Step 8: From Enter one or more numbers (one per line) box, enter the following invalid data: PUR007123
			shipmentTrackingPage.enterPORNumber(pickupReqNo);

			//Step 9: Click the Search button
			shipmentTrackingPage.clickOnSearchButton();

			//Validate the following error message:
			//Are you trying to *track a pickup request*? Shipment details are not yet available for Pickup Request Number PUR007123.
			shipmentTrackingPage.validateErrorMessage(pickupReqNo, errMsg);

			//Step 10: Click on the *track a pickup request?* link in the error message
			shipmentTrackingPage.clickOnTrackPickupRequestLink();

			//A new window is opened and the user is navigated to the Pickup Visibility app
			shipmentTrackingPage.verifyUserNavigatedToNewWindow(1,newWindowUrl+pickupReqNo);

			//Step 11: From Pickup Visibility app, validate the error message

			//Validate the following error message:
			//Are you trying to *track a shipment?* Pickup Request number PUR007123 not found.
			shipmentTrackingPage.verifyErrMsgInNewTab(errMsg1);

			//Step 12: Tab back to the Shipment Tracking app and repeat (steps 6, 7)
			shipmentTrackingPage.switchBackToMainPage();

			//Step 13: From Enter one or more numbers (one per line) box, enter the following invalid data:
			shipmentTrackingPage.enterPORNumber("");
			shipmentTrackingPage.enterMultiplePRONumber("PUR007124","PUR007125","PUR007126","PUR007127","PUR007128","PUR007129");

			//Step 14: Click Search button
			shipmentTrackingPage.clickOnSearchButton();

			//Validate the following error message:
			//Are you trying to *track a pickup request?* Shipment details are not yet available for Pickup Request Numbers PUR007124, PUR007125, PUR007126, and 3 *others*.
			shipmentTrackingPage.validateErrMsg(errMultiplePO);

			//Step 15: Click on the *others* link in the error message
			shipmentTrackingPage.clickOnOthersLink();

			//A new window is opened and the user is navigated to the Pickup Visibility app
			shipmentTrackingPage.verifyUserNavigatedToNewWindow(2,newWindowUrl+"PUR007124,PUR007125,PUR007126,PUR007127,PUR007128,PUR007129");

			//Step 16: From Pickup Visibility app, validate the error message
			//Validate the following error message:
			//Are you trying to *track a shipment?* Pickup Request numbers PUR007124, PUR007125, PUR007126, and 3 others not found.
			shipmentTrackingPage.verifyErrMsgInNewTab(errForMultiplePO);

			//Step 17: Validate results are returned in order as entered in data and query string in address should data in the correct order
			//Validate the following query string data:
			//https://estes-express-uat.estesinternal.com/myestes/tracking/pickups?pur=PUR007124,PUR007125,PUR007126,PUR007127,PUR007128,PUR007129
			shipmentTrackingPage.verifyUserNavigatedToNewWindow(2,newWindowUrl+"PUR007124,PUR007125,PUR007126,PUR007127,PUR007128,PUR007129");

			//Step 18: Tab back to the Shipment Tracking app
			shipmentTrackingPage.switchBackToMainPage();

			//Step 19: From the error message bar, click the x
			shipmentTrackingPage.clickOnClosebutton();

		}
}



