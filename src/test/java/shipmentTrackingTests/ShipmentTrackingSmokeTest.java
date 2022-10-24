package shipmentTrackingTests;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import jagacy.util.JagacyUtil;
import jagacyVT.screens.DeliveryAppointmentInquirybyTIDscreen;
import jagacyVT.screens.DeliveryProUpdateScreen;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.ProsRequiringDeliveryAppointmentScreen;
import jagacyVT.screens.ReserveFreightBillScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import myEstesPages.MyEstesBillOfLadingPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQuotePage;
import shipmentTrackingPages.EstesHomeTrackingPage;
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;

public class ShipmentTrackingSmokeTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	EstesHomeTrackingPage estesHomeTrackingPage= new EstesHomeTrackingPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	
	/******************************* TESTS *******************************/
	
	/*
	 * Authenticated - Verify the user can track shipment(s) and view results when
	 * search by PRO Number
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_6000() throws Exception {
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "171";
		String user = "Test";
//        String shipperCode = "7178618";
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

		// this is the new method for verifying screen title for step 1

		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
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
		//freightBillUpdateScreen.enterCons("5608430");
		//freightBillUpdateScreen.enter3Pt("5068692");
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

		// Step 10: Access My Estes
		driver.get(url);

		// Step 11: Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Step 12: From My Estes home page hover mouse over the following menu: Track
		myEstesHomePage.clickOnTrack();
		// Step 13: From Track menu, click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();

		// Validate that url in the address bar
		String ActualUrl = driver.getCurrentUrl();
		//String ExpUrl = "https://estes-express-uat.estesinternal.com/myestes/home/myestes/recent-shipments/7178618"; //Modified as per test step
		String ExpUrl = "https://estes-express-uat.estesinternal.com/myestes/tracking/shipments";

		Assert.assertEquals(ActualUrl, ExpUrl);

		// Step 14 & 15: From the Search box enter a valid 10 digit freight bill number
		shipmentTrackingPage.enterPORNumber(fbNum1);
		testUtil.setHardWait(20000);

		// Step 16: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(10000);

		// Step 17: Validate data in the *Preview Bar*
		shipmentTrackingPage.verifyPickedUpStatus();
		shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

		// Step 18: Click the Expand All button
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 19: Validate the Milestone Data
		shipmentTrackingPage.verifyPickedUpMilestone();

		// Step 20: Validate data in the *Shipment Details* should match the data fields
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

		// Step 21: Validate disclaimer should display
		shipmentTrackingPage.verifyDisclaimer();

		// Step 22: Click on Shipment History caret dropdown
		shipmentTrackingPage.clickOnShipmentHistoryCaret2();
		testUtil.setHardWait(4000);

		// Shipment picked up is displayed
		shipmentTrackingPage.verifyShipmentPickedupStatus();
		// Validate shipment history data is returned and match time stamp of when the
		// action(s)
		shipmentTrackingPage.verifyTimeZone("EST");
		testUtil.setHardWait(2000);
		// Step 23: Click the Collapse All button
		shipmentTrackingPage.clickOnCollapseButton();
		testUtil.setHardWait(2000);

		// Step-24: Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

	}

	
	/**
	 * 
	 * @author Nithay
	 */

	/*
	 * Shipment Tracking - Unauth - Home Page Track a Shipment Widget - Verify the
	 * user can track shipments by Purchase Order Number
	 */
	@Test(enabled = true, priority = 2)
	public void executeQZ_7532() throws Exception {

		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String reserveOption = "82";
		String originTerminal = "037";
		String user = "Test";
		String shipperCode = "3732590";
		String option1 = "2";

		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		String todayDate = sdf.format(cal.getTime());
		System.out.println(todayDate);
		String bolNum1 = todayDate + "1";

		String poNum1 = todayDate + "PO1" +testUtil.randomString(2);

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
		freightBillUpdateScreen.enterCons("7178618");
		freightBillUpdateScreen.enter3Pt("5068692");
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
		testUtil.setHardWait(2000);
		// Step 9: Press F1 to exit
		jagacyUtil.pressF1();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// Step 8: Access Shipment Tracking app
		driver.get(url3);

		// Step 9: Search by Pro Number and enter pro number into Tracking box
		shipmentTrackingPage.selectPONumber();

		// Step-10: From the Enter Purchase Order Number text box , enter the Purchase
		// Order Number created
		shipmentTrackingPage.enterPORNumber(poNum1);
		testUtil.setHardWait(20000);

		// Step 11: Click on the SEARCH button
		shipmentTrackingPage.clickOnSearchButton();

		// Validate that url in the address bar
		String ActualUrl = driver.getCurrentUrl();
		String ExpUrl = "https://estes-express-uat.estesinternal.com/myestes/tracking/shipments";

		Assert.assertEquals(ActualUrl, ExpUrl);

		// Step 12: Validate data in the *Preview Bar*
		shipmentTrackingPage.verifyPickedUpStatus();
		shipmentTrackingPage.verifyPreviewBarFieldsNotBlank();

		// Step 13: Click the Expand All button
		shipmentTrackingPage.clickOnExpandArrow();

		// Step 13: Validate the Milestone Data
		shipmentTrackingPage.verifyPickedUpMilestone();

		// Step 14: Validate data in the *Shipment Details* should match the data fields
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

		// Step 15: Click on Shipment History caret dropdown
		shipmentTrackingPage.clickOnShipmentHistoryCaret2();
		testUtil.setHardWait(4000);

		// Step 16: Click the Collapse All button
		shipmentTrackingPage.clickOnCollapseButton();
		testUtil.setHardWait(2000);

	}

	/**
	 * @author lemmoju
	 * Shipment Tracking - Unauth - Verify the user can track shipment(s) and view results when search by PRO Number
	 * @throws InterruptedException
	 */
	

	@Test(enabled = true, priority = 3)
	
	public void executeQZ_11874() throws Exception {
		/*Step 1/2: Reserve Freight bill, follow the steps in QZ-384 and enter the following data:
		 * Note* Cart To (step 7), codes in (step 13) not needed.
		 * Shipper Account Code: 7178618
		 * Updating Shipper Account per Dee's permission: B018906
		 * Terminal: 171
		 * TS - 1
		 * Pcs - 4
		 * Terms - PPD
		 * Wgt - 3500
		 * Master BL - 3020037700-1
		 * Cons - <5608430>
		 * PO - <SL1014202101>
		 * PUDr - 9999999 9
		 * Cubic Feet - 1
		 * 3 pty: 5068692
		 */
		
		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originTerm = "135"; 
		String destTerm = "018"; 
		String shipperCode = "B018906"; 
		String consigneeNum = "5608430";
		String poNum = "SL1014202101"; 
		String thirdParty = "5068692"; 

		// Reserve Freight Bill (QZ-384)
		SessionVt session = null;
		session = new SessionVt(name, "exlaqa", terminal);
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();
		LoginScreen loginScreen = new LoginScreen(session);

		// Login to EXLAQA
		loginScreen.enterUserNPasswordCDOC(userName, password);
		Thread.sleep(2000);

		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38MasterMenu = new Ltl38MasterMenuScreen(session);
		ltl38MasterMenu.verifyScreenTitle();
		ltl38MasterMenu.enterValueOptionField1("1");
		jagacyUtil.pressEnter();

		// From the Freight Billing Menu screen, enter the following values:
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originTerm);

		// Reserve freight bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);

		// Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Create a Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("4");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("3500");
		freightBillUpdateScreen.enterDTValue(destTerm);
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("1");
		freightBillUpdateScreen.enter3Pt(thirdParty); 
		jagacyUtil.pressEnter();
		//Step 3/4 Press Enter to validate entries and advance to next entry page.
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		/*Step 5: On the second page of the UPDATE screen, enter the following values:
		 * Class - 50
		 * Pcs - 4
		 * Pk - CT
		 * Description - Cartons
		 * Wgt - 3500
		 */
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("4");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("Cartons");
		freightBillUpdateScreen2.enterWgt2("3500");
		
		//Steps 6/7/8 Press Enter
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		//Step 9: Press F1 Exit
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}
		
		/*Step 10: 
		 * Access the following URL for My Estes:
		 * <<MyEstes QA URL>>QZ-9814 
		 */
		driver.get(url); 
		testUtil.setHardWait(5000); 
		
		/*
		 * Step 11: From My Estes home page hover mouse over the following menu: Track
		 * Step 12: From Track menu, click on Shipment Tracking
		 * Validate that url in the address bar points to =>
		 * https://esetes-express-uat.estesinternal.com/myestes/tracking/shipments
		 */
		estesHomeTrackingPage.clickTrackOnNavBar();
		estesHomeTrackingPage.clickOnShipmentTracking(); 
		
		String urlToValidate = "https://estes-express-uat.estesinternal.com/myestes/tracking/shipments"; 
		String currentUrl = driver.getCurrentUrl(); 
		
		Assert.assertTrue(currentUrl.equals(urlToValidate));
		
		/*
		 * Step 13: From the Tracking page, select Pro Number from the Search by drop down
		 */
		shipmentTrackingPage.selectPRONumber(); 
		
		// Step 14: From the Search box enter a valid 10 digit freight bill number
		shipmentTrackingPage.enterPORNumber(fbNum);
		testUtil.setHardWait(5000); 
		
		// Step 15: Click Search button
		shipmentTrackingPage.clickOnSearchButton(); 
		
		// Step 16: Validate data in the *Preview Bar*
		shipmentTrackingPage.validateProNumber(fbNum); 
		//shipmentTrackingPage.verifyShipmentPickedupStatus(); //Modified 
		 shipmentTrackingPage.verifyPickedUpStatus();
		
		/*
		 * Step 17: Click the Expand All button
		 * Results are expanded after one click of the button and not after multiple clicks*
		 */
		shipmentTrackingPage.clickOnExpandArrow(); 
		
		/*
		 * Step 18: Validate the Milestone data
		 * Shipment Milestone Status <Picked Up>
		 * Milestone Information bar: Shipment picked up
		 */
		//shipmentTrackingPage.verifyShipmentPickedupStatus(); //Modified
		shipmentTrackingPage.verifyPickedUpMilestone();
		
		/*
		 * Step 19: Validate data in the *Shipment Details* should match the data field values for the freight bill 
		 * <<Shipment Tracking - Unauthenticated_Shipment Details>>QZ-11458
		 */
		shipmentTrackingPage.validateShipmentDetails("MEMPHIS, TN 38141 US", "4", "3500"); 
		
		/*
		 * Step 20: Click on Shipment History caret dropdown
		 * Validate shipment history data is returned and match time stamp of when the action(s) occurred
		 */
		shipmentTrackingPage.clickOnShipmentHistoryCaret(); 
		String todaysDate = testUtil.todaysDate(); 
		shipmentTrackingPage.verifyShipmentDateInHistory(todaysDate);
		
		/*
		 *  Step 21: Validate disclaimer should display
		 *  Estimated Delivery Date is not guaranteed. For Guaranteed Service please call 1-866-ESTES-4U, Option 2.
		 */
		shipmentTrackingPage.verifyDisclaimer(); 
		
		// Step 22: Click the Collapse All button *Results are collapsed after one click of the button and not after multiple clicks*
		shipmentTrackingPage.clickOnCollapseButton(); 
		
		// Step 23: From the MyEstes dropdown, click on Log Out
		shipmentTrackingPage.clickOnMyEstesLink(); 
	}
	
	 /* Disabled the test due to a Blocker with the exla qa flow . Reached out to Dee awaiting her response.*/
	 /* Fixed - Test passed - 4/22/2022*/
	 /*
	  * Authenticated - Verify Appointment Date in preview bar when 
	  * Appt Date is greater than EDD (Estimated Delivery Date)
	  */
	 @Test(enabled = true, priority = 4)
	   
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
	       String poNum = todayDate + 3287 +testUtil.randomString(2); //Modified to get unique po#
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
	     //freightBillingmenuScreen.enterFreightBillMenuOption(option3, user, destinationTerminal);
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

	       //String ApptDate = testUtil.getBusinessDate("DATE", +6);  //Modified to get date format MM/dd/yy
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
	 
	 
	 /**
		 * Shipment Tracking - Auth - Home Page Track a Shipment Widget -Verify 
		 * the user can track shipments by Purchase Order Number
		 */

		@Test(enabled = true, priority = 5)
		public void executeQZ_11875()
				throws Exception {

			String userName = "QATSTFRTBL";
			String password = "qatest2019";
			String option = "1";
			String reserveOption = "82";
			String originTerminal = "037";
			String user = "Test";
			String shipperCode = "3732590";
			String option1 = "2";

			SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
			// Getting current date
			Calendar cal = Calendar.getInstance();
			// Displaying current date in the desired format
			String todayDate = sdf.format(cal.getTime());
			System.out.println(todayDate);
			String poNum = todayDate + "32491";

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
			freightBillUpdateScreen.enterPcs("4");
			freightBillUpdateScreen.enterTerms("PPD");
			freightBillUpdateScreen.enterWgt("3500");
			freightBillUpdateScreen.enterCons("7178618");
			freightBillUpdateScreen.enter3Pt("5068692");
			//freightBillUpdateScreen.enterMasterBlNo(bolNum);
			freightBillUpdateScreen.enterPONum(poNum);
			freightBillUpdateScreen.enterPuDrNum("9999999", "9");
			freightBillUpdateScreen.enterCubicFeet("1");

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
			myEstesLoginPage.enterUserName("smokelocal");
			myEstesLoginPage.enterPassword("smokelocal");
			myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

			// Step 9: From My Estes home page hover mouse over the following menu: Track
			myEstesHomePage.clickOnTrack();
			// Step-10 : From Track menu, click on Shipment Tracking
			myEstesHomePage.clickOnShipmentTraking();
			testUtil.setHardWait(2000);
			shipmentTrackingPage.clickSearchBy();
			// Step 11: From the Search box enter a valid 10 digit freight bill number
			shipmentTrackingPage.selectPONumber();

			// Step 12: From the Search box , enter the Bill of Lading number associated
			// with alphanumeric account number created
			shipmentTrackingPage.enterPORNumber(poNum);
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


	
}


