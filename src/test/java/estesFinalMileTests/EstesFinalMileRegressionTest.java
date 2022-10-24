package estesFinalMileTests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jagacyVT.screens.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import as400TempletTests.TempletTests;
import estesFinalMilePages.EstesFinalMileConsigneePage;
import estesFinalMilePages.EstesFinalMileElectronicDRPage;
import estesFinalMilePages.EstesFinalMileHomeDeliveryPage;
import estesFinalMilePages.EstesFinalMileMaintenceApp;
import estesFinalMilePages.EstesFinalMileShipmentDetails;
import estesFinalMilePages.EstesFinalMileUserPage;
import myEstesPages.MyEstesHomePage;
import solutionsPages.MyEstesSolutionsPage;
import testBase.TestBase;
import util.FreightBill;
import util.SQLDataList;
import util.TestListener;
import util.TestUtil;

@Listeners(value = TestListener.class)
public class EstesFinalMileRegressionTest extends TestBase {

	private Logger logger = Logger.getLogger(EstesFinalMileRegressionTest.class);

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	EstesFinalMileHomeDeliveryPage efmHomeDeliveryPage = new EstesFinalMileHomeDeliveryPage();
	MyEstesSolutionsPage myestesSolutionPage = new MyEstesSolutionsPage();
	EstesFinalMileShipmentDetails efmShipmentDetailsPage = new EstesFinalMileShipmentDetails();
	SQLDataList sqlDatalist = new SQLDataList();
	EstesFinalMileMaintenceApp estesEfmMaintenceApp = new EstesFinalMileMaintenceApp();
	EstesFinalMileUserPage estesFinalMileUserPage = new EstesFinalMileUserPage();
	EstesFinalMileConsigneePage estesFinalMileConsigneePage = new EstesFinalMileConsigneePage();
	EstesFinalMileElectronicDRPage estesFinalMileElectronicDRPage = new EstesFinalMileElectronicDRPage();


	/******************************* TESTS *******************************/
	@Test(enabled = true, priority = 1) // To be reviewed// PASSD ACCORDING TO SUBHASH

	public void executeQZ_8423() {

		String query = "select fhot, fhpro, fhczip from fbfiles.frp001 where fhdc = '' order by fhpud8 desc fetch first 1 rows only";
		String errorMsg = "Shipment details are not available for the tracking number and ZIP/postal code combination you entered. Please verify that both the number and delivery ZIP/postal code are correct.";
		String invalidZip = "11111";
		String invalidFB = "1111111";

		if (sqlDatalist.validateValueFromDataBase(query) == true) {
			List<String> fbDetails = sqlDatalist.getFirstRowDetailsFromEXLAQA(query);

			String originTerminal = fbDetails.get(0);
			String proNumber = fbDetails.get(1);
			String zipCode = fbDetails.get(2);

			String oTerminal = testUtil.checkAndAddLeadingZeroToFBData(3, originTerminal);
			String proNum = testUtil.checkAndAddLeadingZeroToFBData(7, proNumber);
			String deliveryZip = testUtil.checkAndAddLeadingZeroToFBData(5, zipCode);

			// Solutions - Residential - EFM
			myEstesHomePage.clickOnSolutionsTab();

			//myestesSolutionPage.clickOnResidentialLink();

			myestesSolutionPage.clickOnFinalMileLink();
			myestesSolutionPage.clickOnEstesFinalMileLink();

			// Invalid Tracking Number & Valid Zip Code
			efmHomeDeliveryPage.enterTrackingNumber(oTerminal + invalidFB);
			testUtil.setHardWait(1000);
			efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(zipCode);
			efmHomeDeliveryPage.clickOnTrackNowButton();
//			efmHomeDeliveryPage.verifyAlertMessage(errorMsg);
			driver.navigate().back();

			// Valid Tracking Number & Invalid Zip Code
			efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, proNumber);
			efmHomeDeliveryPage.enterDeliveryZipCode(invalidZip);
			testUtil.setHardWait(1000);
			efmHomeDeliveryPage.clickOnTrackNowButton();
//			efmHomeDeliveryPage.verifyAlertMessage(errorMsg);
			driver.navigate().back();

			// Valid Tracking Number with "&"
			efmHomeDeliveryPage.enterTrackingNumber(oTerminal + "&" + proNum);
			efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(zipCode);
			efmHomeDeliveryPage.clickOnTrackNowButton();
			efmHomeDeliveryPage.validateInvalidTrackingMessage();

			// Valid Zip Code with "&"
			efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, proNumber);
			efmHomeDeliveryPage.enterDeliveryZipCode(deliveryZip.substring(0, 3) + "&" + deliveryZip.substring(3));
			efmHomeDeliveryPage.clickOnTrackNowButton();
			efmHomeDeliveryPage.validateInvalidZipCodeMessage();
		}
	}

	/*
	 * Estes Final Mile - Error message should be displayed when required fields are
	 * left blank on the Shipment Details page
	 */
	//New changes are being interduced- it should not run until further notice from DEE
	//Phone Number is needed
	@Test(enabled = false, priority = 2) // To be reviewed

	public void executeQZ_8424()
			throws Exception {

		//String query = "select fhot, fhpro, fhczip from fbfiles.frp001 where fhdc = '' order by fhpud8 desc fetch first 1 rows only";
		String query = "SELECT A.FhOT, A.FhPRO, a.FHCZIP FROM fbfiles.frp001 as a join fbfiles.frp002 as b on a.fhot = b.fdot and a.fhpro = b.fdpro WHERE fdcmcl in (SELECT CODE FROM fbfiles.efm00p120 WHERE CONSIGNEETYPEID = 1) and fhdc <> 'Y' and fdcmcl <> 'SIGRQD'";

		String successMessage = "Contact information saved successfully";
		String errorMsg = "Shipment details are not available for the tracking number and ZIP/postal code combination you entered. Please verify that both the number and delivery ZIP/postal code are correct.";


		if (sqlDatalist.validateValueFromDataBase(query) == true) {
			List<String> fbDetails = sqlDatalist.getFirstRowDetailsFromEXLAQA(query);

			String originTerminal = fbDetails.get(0);
			String proNumber = fbDetails.get(1);
			String zipCode = fbDetails.get(2);

			// Solutions - Residential - EFM
			myEstesHomePage.clickOnSolutionsTab();

			//myestesSolutionPage.clickOnResidentialLink();

			myestesSolutionPage.clickOnFinalMileLink();
			myestesSolutionPage.clickOnEstesFinalMileLink();

			// Enter Tracking number & Zip Code
			efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, proNumber);
			efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(zipCode);
			efmHomeDeliveryPage.clickOnTrackNowButton();
			efmShipmentDetailsPage.verifyShipmentDetailsPage();

			// Click On Unattended Delivery link and click finish
			efmShipmentDetailsPage.clickOnUnattendedDeliveryLink();
			efmShipmentDetailsPage.clickOnUnattendedDeliveryFinishButton();
			efmShipmentDetailsPage.validateUnattendedNameRequiredError();
			efmShipmentDetailsPage.validateUnattendedAuthorizationRequiredError();
			efmShipmentDetailsPage.clickCloseOnUnattendedDeliveryPopup();

			// Select email notification checkbox and click update
			efmShipmentDetailsPage.selectORDeselectEmailNotificationCheckBox();
			efmShipmentDetailsPage.clickOnUpdateButton();
			efmShipmentDetailsPage.validateEmailRequiredMessage();

			// Enter Phone & Secondary Phone number
			efmShipmentDetailsPage.enterPhoneNumber("804-353-1900 1");
			efmShipmentDetailsPage.enterSecondaryPhoneNumber("804-353-1900 1");
			efmShipmentDetailsPage.clickOnUpdateButton();
			efmShipmentDetailsPage.validateEmailRequiredMessage();
			efmShipmentDetailsPage.validatePhoneNumberRequiredMessage();
			efmShipmentDetailsPage.validateSecondaryPhoneNumberRequiredMessage();

			// Deselect mail notification and click update
			efmShipmentDetailsPage.selectORDeselectEmailNotificationCheckBox();
			efmShipmentDetailsPage.enterPhoneNumber("804-353-1900 12");
			efmShipmentDetailsPage.enterSecondaryPhoneNumber("804-353-1900 12");
			efmShipmentDetailsPage.clickOnUpdateButton();
			efmShipmentDetailsPage.verifyContactSavedMessage(successMessage);

			// Track other shipment - Empty
			efmShipmentDetailsPage.enterTrackingNumber("");
			efmShipmentDetailsPage.enterDeliveryZipCode("");
			efmShipmentDetailsPage.clickOnTrackNowButton();
			efmShipmentDetailsPage.verifyTrackingNoAlertMessage();
			efmShipmentDetailsPage.verifyZipCodeAlertMessage();

			// Track other shipment - Invalid Tracking no
			efmShipmentDetailsPage.enterTrackingNumber("0299975111");
			efmShipmentDetailsPage.enterDeliveryZipCodeInFormat(zipCode);
			efmShipmentDetailsPage.clickOnTrackNowButton();
			efmShipmentDetailsPage.verifyAlertMessage(errorMsg);

			// Track other shipment - Invalid Zip code
			efmShipmentDetailsPage.enterTrackingNumberInFormat(originTerminal, proNumber);
			efmShipmentDetailsPage.enterDeliveryZipCode("11111");
			efmShipmentDetailsPage.clickOnTrackNowButton();
			efmShipmentDetailsPage.verifyAlertMessage(errorMsg);
		}
	}

	/*
	 * Estes Final Mile - Verify for freight with Canada Destination Zip Code Email
	 * Address and Email Notification should not display on the Shipment Details
	 * page
	 */

	//New changes are being interduced- it should not run until further notice from DEE
	//Stop execution untill the issue is fixed per DEE.
	@Test(enabled = false, priority = 3) // To be reviewed

	public void executeQZ_8596() {

		//String query = "select fhot, fhpro, fhczip from fbfiles.frp001 where fhdc = '' order by fhpud8 desc fetch first 1 rows only";
		String query = "SELECT A.FhOT, A.FhPRO, a.FHCZIP FROM fbfiles.frp001 as a join fbfiles.frp002 as b on a.fhot = b.fdot and a.fhpro = b.fdpro WHERE fdcmcl in (SELECT CODE FROM fbfiles.efm00p120 WHERE CONSIGNEETYPEID = 1) and fhdc <> 'Y' and fdcmcl <> 'SIGRQD'";

		String successMessage = "Contact information saved successfully";
		String errorMsg = "Shipment details are not available for the tracking number and ZIP/postal code combination you entered. Please verify that both the number and delivery ZIP/postal code are correct.";


		if (sqlDatalist.validateValueFromDataBase(query) == true) {
			List<String> canadaFBDetails = sqlDatalist.getFirstRowDetailsFromEXLAQA(query);

			String originTerminal = canadaFBDetails.get(0);
			String proNumber = canadaFBDetails.get(1);
			String zipCode = canadaFBDetails.get(2);

			String oTerminal = testUtil.checkAndAddLeadingZeroToFBData(3, originTerminal);
			String proNum = testUtil.checkAndAddLeadingZeroToFBData(7, proNumber);
			String deliveryZip = testUtil.checkAndAddLeadingZeroToFBData(5, zipCode);

			// Home Delivery - Estes Final Mile
			myEstesHomePage.clickOnHomeDeliveryLink();
			efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, proNumber);
			efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(zipCode);
			efmHomeDeliveryPage.clickOnTrackNowButton();

			// Validate Phone fields are displayed
			efmShipmentDetailsPage.verifyAddressBarLinkContainsTrackingInfo(originTerminal, proNumber, zipCode);
			efmShipmentDetailsPage.verifyPhoneNumberFieldIsDisplayed();
			efmShipmentDetailsPage.verifySecondaryPhoneNoFieldIsDisplayed();
			driver.navigate().back();

			// Add space between Tracking numbers
			efmHomeDeliveryPage.enterTrackingNumber(oTerminal + " " + proNum);
			testUtil.setHardWait(1000);
			efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(zipCode);
			efmHomeDeliveryPage.clickOnTrackNowButton();
			efmShipmentDetailsPage.verifyShipmentDetailsPage();
			driver.navigate().back();

			// Add space between Zip code
			efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, proNumber);
			efmHomeDeliveryPage.enterDeliveryZipCode(deliveryZip.substring(0, 3) + " " + deliveryZip.substring(3));
			efmHomeDeliveryPage.clickOnTrackNowButton();
			efmShipmentDetailsPage.verifyShipmentDetailsPage();
		}
	}

	/*
	 * Estes Final Mile - Error message should be displayed when required fields are
	 * left blank on the Estes Final Mile landing page - Track a Shipment Component
	 */

	//New changes are being interduced- it should not run until further notice from DEE
	@Test(enabled = false, priority = 4)// THIS IS PASSING ACCORDING TO SUBHASH
	public void executeQZ_8072() {

		String errorTrack = "Tracking Number is required", errorZip = "ZIP/Postal Code is required";

		myEstesHomePage.clickOnSolutionsTab();

		//myestesSolutionPage.clickOnResidentialLink();

		myestesSolutionPage.clickOnFinalMileLink();
		myestesSolutionPage.clickOnEstesFinalMileLink();

		testUtil.setHardWait(3000);
		efmHomeDeliveryPage.clickOnTrackNowButton();

		efmHomeDeliveryPage.validateErrorTrackingNumberMessage(errorTrack);

		efmHomeDeliveryPage.validateErrorZipCodeMessage(errorZip);

	}

	/*
	 * Estes Final Mile - Disables the unattended delivery request form based on
	 * Signature Required Commodity code on PRO
	 */
	// to be run locally as it deals with Jagacy


	//Stop execution untill the issue is fixed per DEE. Issue fixed according to Nithya

	@Test(enabled = false, priority = 5)

	public void executeQZ_8070() throws JagacyException, Throwable {

		//Declarations
		String expText = "* Date is based on current service standards and is not guaranteed. Please note that for delivery to occur, you must have an appointment. We will contact you to set up the appointment once your shipment arrives at our local facility.";
		String expMsg = "We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur.";
		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String originTerminal = "087";
		String sCode = "2302234";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// MySession session = new MySession();
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();
		//Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);

		ltl38mastermenu.enterValueOptionField(option);


		//Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);

		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(7000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		//Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);

		//Press F3 to exit
		//	jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressF3(); 

		//Enter Freight bill
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
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();

		//Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");

		jagacyUtil.pressEnter();

		jagacyUtil.pressEnter();

		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterShippingInstruction1("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("SIGRQD");

		jagacyUtil.pressEnter();

		//Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3(); 

		if(session != null) {
			session.abort();
			session.close();
		}

		session = null;

		//EFM		
		// Solutions - Residential - EFM
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		testUtil.setHardWait(1000);
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Enter Tracking number & Zip Code
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(10000);
		efmHomeDeliveryPage.clickOnTrackNowButton();	

		//From the Shipment details page, the following should be displayed above the Shipment Status bar and populated with shipment data: *Tracking Number*:*ZIP/Postal Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRange();

		//*Status Bar*: Shipped *(GREEN)* in color
		efmShipmentDetailsPage.verifySHippedColor();

		//Verify Shipment Details page is displayed with below message:"We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur."
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		//From the Shipment Details Page, click on the View Shipment Details Accordion.
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		//Verify Shipment Notes section and text should be displayed
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRangeInShipmentDetails();
		efmShipmentDetailsPage.verifyShipmentDetailsDateMessageDisplays(expText);

		//Want to receive text and email updates on your shipment? section, is displayed with the following fields - Primary,secondary and other phone number along with email address
		efmShipmentDetailsPage.verifyPrimaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifySecondaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifyEmailAddressDisplays();
		efmShipmentDetailsPage.verifyOtherPhoneNumberDisplays();

		//Verify *Unattended Delivery Signup* component and link not displayed any where on the page; only the following components are displayed: *Track another Shipment**Contact Us*	
		efmShipmentDetailsPage.verifyUnattendedDeliveryDisabled();

	}
	// to be run locally as it deals with Jagacy
	//Stop execution untill the issue is fixed per DEE.
	@Test(enabled = false, priority = 6)
	public void executeQZ_8077() throws JagacyException, Throwable {

		String expText = "* Date is based on current service standards and is not guaranteed.";
		String expMsg = "Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.";

		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String originTerminal = "087";
		String sCode = "2302234";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// MySession session = new MySession();
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);

		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(
				session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(
				session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		// Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + fbNum);

		// Press F3 to exit
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
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

		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("NOSIGN");

		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// EFM
		// Solutions - Residential - EFM
		myEstesHomePage.clickOnSolutionsTab();
		//				myestesSolutionPage.clickOnResidentialLink();
		myestesSolutionPage.clickOnFinalMileLink();
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Enter Tracking number & Zip Code
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		testUtil.setHardWait(1000);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(10000);
		efmHomeDeliveryPage.clickOnTrackNowButton();

		// From the Shipment details page, the following should be displayed above the
		// Shipment Status bar and populated with shipment data: *Tracking
		// Number*:*ZIP/Postal Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRange();

		// *Status Bar*: Shipped *(GREEN)* in color
		efmShipmentDetailsPage.verifySHippedColor();

		// Verify Shipment Details page is displayed with below message:"We will contact
		// you to set up an appointment. Please note that a scheduled appointment is
		// required for delivery to occur."
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		// From the Shipment Details Page, click on the View Shipment Details Accordion.
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		// Verify Shipment Notes section and text should be displayed
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRangeInShipmentDetails();
		efmShipmentDetailsPage.verifyShipmentDetailsDateMessageDisplays(expText);

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
	// to be reviewed

	// to be run locally as it deals with Jagacy
	//Stop execution untill the issue is fixed per DEE.
	@Test(enabled = false, priority = 7)

	public void executeQZ_8074() throws JagacyException, Exception {

		//Declarations
		String expText = "* Date is based on current service standards and is not guaranteed. Please note that for delivery to occur, you must have an appointment. We will contact you to set up the appointment once your shipment arrives at our local facility.";
		String expMsg = "We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur.";
		String option = "1";
		String reserveOption = "82";
		String user = "Reg";//changed it from "Test" based on Carletons branch
		String originTerminal = "087";
		String sCode = "2302234";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// MySession session = new MySession();
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);

		//Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		//Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(5000);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		//Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);

		//Press F3 to exit
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressF3(); 

		//Enter Freight bill
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
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();

		//Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		//Adding shipment instructions
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("SIGRQD");
		freightBillUpdateScreen3.enterShippingInstruction2("NOSIGN");
		jagacyUtil.pressEnter();

		//Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3(); 

		if(session != null) {
			session.abort();
			session.close();
		}

		session = null;

		//EFM		
		// Solutions - Final Mile - EFM
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Enter Tracking number & Zip Code
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		testUtil.setHardWait(1000);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(5000);
		efmHomeDeliveryPage.clickOnTrackNowButton();	

		//From the Shipment details page, the following should be displayed above the Shipment Status bar and populated with shipment data: *Tracking Number*:*ZIP/Postal Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRange();
		testUtil.setHardWait(2000);
		//*Status Bar*: Shipped *(GREEN)* in color
		efmShipmentDetailsPage.verifySHippedColor();

		//Verify Shipment Details page is displayed with below message:"We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur."
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		//From the Shipment Details Page, click on the View Shipment Details Accordion.
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		//Verify Shipment Notes section and text should be displayed
		efmShipmentDetailsPage.verifyShipmentDetailsDateMessageDisplays(expText);
		efmShipmentDetailsPage.verifyEarliestPossibleDeliveryDateRangeInShipmentDetails();

		//Want to receive text and email updates on your shipment? section, is displayed with the following fields - Primary,secondary and other phone number along with email address
		efmShipmentDetailsPage.verifyPrimaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifySecondaryPhoneNumberDisplays();
		efmShipmentDetailsPage.verifyEmailAddressDisplays();
		efmShipmentDetailsPage.verifyOtherPhoneNumberDisplays();

		//Verify *Unattended Delivery Signup* component and link not displayed any where on the page; only the following components are displayed: *Track another Shipment**Contact Us*	
		efmShipmentDetailsPage.verifyUnattendedDeliveryDisabled();

	}

	/*
	 * Estes Final Mile - Unattended delivery sign up is requested prior to contact
	 * information provided validate frp002 commodity codes are populated on the
	 * freight bill and Email notification is sent to consignee - Shipped Status
	 */
	//Stop execution untill the issue is fixed per DEE.
	@Test(enabled = false, priority = 8)

	public void executeQZ_8073()
			throws JagacyException, Exception {

		/* For validation steps-not needed:String query = "SELECT A.FhOT, A.FhPRO,
		 a.FHCZIP FROM fbfiles.frp001 as a join fbfiles.frp002 as b on a.fhot = b.fdot
		 and a.fhpro = b.fdpro WHERE fdcmcl in (SELECT CODE FROM fbfiles.efm00p120
		 WHERE CONSIGNEETYPEID = 1) and fhdc <> 'Y' and fdcmcl <> 'SIGRQD'";*/

		String expMsg = "You have opted out of an appointment and no signature is required. We will place your shipment out for delivery in accordance with the instructions you provided as soon as possible.";
		String radiobutton = "BACK DOOR", unattendedDeliveryName = "Dee", gateOrCode = "358";
		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String originTerminal = "087";

		// Step 1: Use shipper account 2302234 in next Reserve Freight Bill step
		String sCode = "2302234";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String name = "myJagacyVT";

		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// Step 2: Reserve and create freight bill
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);

		jagacy.util.JagacyUtil jagacyUtil= new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();
		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(
				session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(
				session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
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
		freightBillUpdateScreen3.enterShippingInstruction1("HD");
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// Step 3: Open My Estes using QA url
		// Step 4: N/A
		// Step 5: From Estes QA Homepage, select *Solutions*, next select *Final Mile*
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();

		// Step 6: From the Residential page, select *Estes Final Mile*
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Step 7: Estes Final Mile Home Delivery page, enter Tracking Number & Zip Code
		// on the Track a Shipment component
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(6000);
		// Step 8: Click on *Track Now* button-Address link contains OTPRO and
		// Destination Zip Code in URL
		efmHomeDeliveryPage.clickOnTrackNowButton();
		efmShipmentDetailsPage.verifyAddressBarLinkContainsTrackingInfo(originTerminal, billNum, consZip);

		// Step 9: From the Shipment details page, the following should be displayed
		// above the Shipment Status
		// bar and populated with shipment data: *Tracking Number*:*ZIP/Postal
		// Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();

		// Step 10: The following should be displayed on the Shipment Status bar:
		// Shipped *(GREEN)*,
		// Appointment Set, Out For Delivery, Delivered
		efmShipmentDetailsPage.verifySHippedColor();
		efmShipmentDetailsPage.verifyShippedStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyAppointmentSetStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyOutForDeliveryStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyDeliveredStatusBarTextIsDisplayed();

		// Step 11: From Shipment Details page, Click on the following link in message:
		efmShipmentDetailsPage.clickOnUnattendedDeliveryLink();

		// Step 12: From the Unattended Delivery Signup popup page, enter the following
		// values:
		efmShipmentDetailsPage.unattendedDeliveryName(unattendedDeliveryName);
		efmShipmentDetailsPage.unattendedDeliveryGateOrCode(gateOrCode);
		efmShipmentDetailsPage.radioButtonUnattendedDelivery(radiobutton);
		efmShipmentDetailsPage.unattendedDeliveryAuthorizeCheckBox();

		// Step 13: Click on *Finish* button
		efmShipmentDetailsPage.clickOnUnattendedDeliveryFinishButton();
		testUtil.setHardWait(8000);
		testUtil.reloadPage(); // added reload
		testUtil.setHardWait(2000);

		// Step 14: From the Shipment details page, the following should be displayed
		// above the Shipment Status bar
		// and populated with shipment data: *Tracking Number*:*ZIP/Postal Code*Earliest
		// Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();

		// Step 15: The following should be displayed on the Shipment Status bar:
		// Shipped *(GREEN)*, Out For Delivery, Delivered
		efmShipmentDetailsPage.verifySHippedColor();
		efmShipmentDetailsPage.verifyShippedStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyOutForDeliveryStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyDeliveredStatusBarTextIsDisplayed();

		// Step 16: Verify Shipment Details page is displayed with below message:"You
		// have opted out of an appointment
		// and no signature is required. We will place your shipment out for delivery in
		// accordance with the instructions
		// you provided as soon as possible."
		testUtil.reloadPage(); // refresh to display correct message
		testUtil.setHardWait(1000);
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		// Step 17: From the Shipment Details Page, click on the View Shipment Details
		// Accordion.
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		// Step 18: Verify *Unattended Delivery Signup* component and link not displayed
		// any where on the page; only the
		// following components are displayed: *Track another Shipment* *Contact Us*
		efmShipmentDetailsPage.verifyUnattendedDeliveryDisabled();

		// Step 19: From the "Want to receive text and email updates on your shipment?"
		// section, Enter the following values:

		efmShipmentDetailsPage.enterPhoneNumber("804-353-1901");
		efmShipmentDetailsPage.enterSecondaryPhoneNumber("804-353-1800");
		efmShipmentDetailsPage.selectORDeselectPrimaryPhoneNotificationsCheckBox();
		efmShipmentDetailsPage.selectORDeselectSecondaryPhoneNotificationsCheckBox();
		efmShipmentDetailsPage.enterEmail("qatest@estes-express.com");
		efmShipmentDetailsPage.enterOtherPhoneNumber("800-566-7777 1277777");

		// Step 20: Select the Email Address check box that reads: *Check here to send
		// shipment status updates to this email address.*
		efmShipmentDetailsPage.selectORDeselectEmailNotificationCheckBox();

		// Step 21: Click the *Update* button and validate Confirmation message is
		// received
		efmShipmentDetailsPage.clickOnUpdateButton();
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifyContactInfoUpdateConfirmationMessageIsDisplayed();

		// Step 22: From the Want to receive text and email updates on your shipment?
		// section, verify form fields retains values entered: The Values should be
		// CHECKED & MASKED

		testUtil.reloadPage();
		efmShipmentDetailsPage.verifyContactInfoFieldsAreMasked();
		efmShipmentDetailsPage.verifyContactInfoCheckboxesAreChecked();

		// Step 23 - Step 27: N/A - RoundCube email steps cannot be automated

		/*
		 * // NOTE: Steps 28 - 34 cannot be completed because the freight bill fields
		 * will not populate in Jagacy for the validation steps in EXLAQA(back-end
		 * validation which we do not automated for now)
		 * 
		 * // Step 28: Navigate to AS400 EXLAQA session and log in with tester
		 * credentials
		 * 
		 * driver.navigate().back(); if (session != null) {
		 * System.out.println("Closing existing session"); session.abort();
		 * session.close(); }
		 * 
		 * testUtil.setHardWait(4000);
		 * 
		 * //Start session SessionVt session1 = null; session1 = new SessionVt(name,
		 * "exlaqa", terminal); session1.open(); testUtil.setHardWait(4000);
		 * 
		 * LoginScreen loginScreen1 = new LoginScreen(session1);
		 * loginScreen1.enterUserNPasswordCDOC(userName, password);
		 * Ltl38MasterMenuScreen ltl38mastermenu1 = new Ltl38MasterMenuScreen(session1);
		 * 
		 * // Step 29: From the LTL/38 master menu enter Option = 1 // Step 30: Press
		 * Enter ltl38mastermenu1.enterValueOptionField(option);
		 * 
		 * // Step 31: From the Freight Billing menu enter Option = 1, User - Test
		 * jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen1 = new
		 * jagacyVT.screens.FreightBillingMenuScreen(session1);
		 * freightBillingMenuScreen1.enterValueOptionField(option);
		 * freightBillingMenuScreen1.enterValueUserField(user);
		 * freightBillingMenuScreen1.enterTabKey();
		 * freightBillingMenuScreen1.enterTerminalNumber(originTerminal);
		 * 
		 * // Step 32: Press Enter freightBillingMenuScreen1.pressEnterKey();
		 */

		// Step 33: From the Freight bill inquiry menu, enter the 10 digit Origin
		// Terminal PRO number
		// Step 34: Press Enter
		/*
		 * Validate the following commodity codes are displayed on the freight bill:
		 * Example Template* WBCCN1 Primary 455-555-5555 1805 053020 WBCCN6 Pri Mobile
		 * text Upd = Y 1805 053020 WBCCN2 Secondary 343-434-3434 1805 053020 WBCCN7 2nd
		 * Mobile text Upd = Y 1805 053020 WBCCN5 Other 800-566-7777 x1277777 WBCCN5
		 * 1805 053020 WBCCN3 qatest@estes-express.com WBCCN3 1805 053020 WBCCN4 Email
		 * Notify = Y 1805 053020 NOSIGN NO SIGNATURE REQUIRED NSAUTH AUT- Dee GATECD
		 * Gate Code- 358 DELPRF Delivery Pref- BACK DOOR
		 */
		// *Note* if codes NOSIGN, NSAUTH, GATECD, DELPRF are not on the freight bill,
		// job EFM00R190 will need to be turned on
		/*
		 * FreightBillInquiryEnterFieldValuesScreen freightBillInqScreen = new
		 * FreightBillInquiryEnterFieldValuesScreen(session1);
		 * freightBillInqScreen.enterValueFreightBill(billNum); // TO-DO (if issue with
		 * freight bill inquiry screen is resolved): validation steps (try using
		 * DisplayCommentsScreen in jagacyVT.screens for these steps)
		 */
		// Step 35: Validate the contact information is being updated in the
		// efm.contact_notification database
		// NOTE: This step is not being performed because database validation cannot be
		// automated
	}

	/**
	 * 
	 * @auther Jeff
	 * 
	 */

	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * 
	 * 
	 * EFM - EFM Maintenance App - Verify E211 generated with Signature Required
	 * (OTD) code when account setup for Signature Required-Always
	 */

	@Test(enabled= false, priority=9)

	public void executeQZ_9536() throws JagacyException, Throwable{


		driver.navigate().to(efmUrl);

		//step 2: Login: test.admin/tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		Thread.sleep(1000);

		//Step 3: Click Signature Options
		estesEfmMaintenceApp.clickSignatureOptions();
		Thread.sleep(2000);

		//Step 4-5: Enter Values: Account and Click Add

		estesEfmMaintenceApp.enterSignatureOptionsAll("5093257", "Signature Required", "Always", "Appointment Required");


		//JAGACY\

		//Step 6 - 8:Reserve Freight BillCreate Freight Bill

		/*
		 * Reserve Bill
		 */		
		SessionVt session = null;
		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originalTerm="087";

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();		
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		Thread.sleep(1000);
		if(session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();
		Thread.sleep(1000);
		//Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField1("1");
		jagacyUtil.pressEnter();
		Thread.sleep(1000);
		//Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originalTerm);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode("1129358");
		//Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originalTerm+billNum;
		System.out.println("Freight Bill No " +fbNum);
		jagacyUtil.pressF3();

		/* Create Freight Bill */
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originalTerm);
		freightBillingMenuScreen.enterFreightBill(originalTerm, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("8700777");
		freightBillUpdateScreen.enterConsName("REG SIGRQD");
		freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
		freightBillUpdateScreen.enterConsCity("COLUMBUS");
		freightBillUpdateScreen.enterConsState("OH");
		freightBillUpdateScreen.enterConsZip("43228");
		freightBillUpdateScreen.enter3Pt("5093257");
		freightBillUpdateScreen.enterCartTo("E087");
		freightBillUpdateScreen.enterPONum("224091819");
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");

		Faker faker = new Faker();
		String blNumber = "S" + faker.number().digits(7);
		freightBillUpdateScreen.enterMasterBlNo(blNumber);
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		//page 2
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		//page 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstruction1("HD");
		jagacyUtil.pressEnter();
		Thread.sleep(2000);	

		//TIME STAMP FOR 1ST FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp1 = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR FIRST FILE: " +timeStamp1);



		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		//Step 9-13: 
		//GO to Folder on Network drive : \\exlaqa\root\edi\xlator\outbound
		//Login with "QATSTFRTBL"/"QATEST2019"
		//Select Folder with Today's Date
		//Look for "E211" file with the containing the Freight Bill Num
		//Assert " AT5***NO SIGNATURE REQUIRED" is not in the file
		Thread.sleep(5000);
		testUtil.readerOutboundFileAssertStringIsNotThere("E211", fbNum, timeStamp1, "AT5***SIGNATURE REQUIRED");

		logger.info("Wait 30 seconds for Time Stamp difference");
		Thread.sleep(30000);


		//Step 14: From the AS400 application, on command line enter the following value: Call FBPGMS/EFM00C110, CALL FBPGMS/EFM00R111
		//Start new Session with Don's credentials, it takes you right to Command Screen. (F7 to get to command Screen Does not work while Automated)

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		//Get past Intermediate screen if exists
		if(session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();


		//Step 15: Call FBPGMS/EFM00C110, Call FBPGMS/EFM00R111
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(2000);
		//Second Time Stamp here
		//TIME STAMP FOR 2nd FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm2 = new Timestamp(System.currentTimeMillis());
		String tmString2 = tm2.toString();
		String timeStamp2 = tmString2.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " +timeStamp2);


		//Step 15-16: Get to Master Menu, Enter Option 1 from Master Menu
		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		//Step 17: From Freight Billing Menu screen, enter the following: Option 1 (Freight bill inquiry )
		//User : Reg 
		//Terminal : 087
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "Reg", "087");

		//Step 18: From Freight bill inquiry screen, enter FB number
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		//Step 19 Verify following values populated on Freight Bill details:
		//Code: SIGRQD 
		//Description: SIGNATURE REQUIRED
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCodeAndDescription("SIGRQD", "SIGNATURE REQUIRED");

		//Step 20: Press F10 to view Freight Bill comments
		jagacyUtil2.pressF10();;
		Thread.sleep(2000);

		//Step 21: Press Page down
		jagacyUtil2.pressPageDown();
		Thread.sleep(2000);

		//Step 22: Verify following values populated on the Freight Bill comments:
		//ACT: SGN
		//Comment: Signature Required
		freightBillDetailsScreen.checkActAndComment("SGN", "Signature Required");

		if (session2 != null) {
			session2.abort();
			session2.close();
		}

		//Step 23-28: Go back to \\exlaqa\root\edi\xlator\outbound, Check for new file created by timestamp
		//Assert:  L11*<OTPRO>*CN~
		//AT5***SIGNATURE REQUIRED~
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp2, "AT5***SIGNATURE REQUIRED");


		Thread.sleep(2000);

		//Step 29 - 31: Delete Account Number: "5093257"
		//Account Number on 2nd Page
		estesEfmMaintenceApp.clickNextButton();
		Thread.sleep(1000);
		estesEfmMaintenceApp.deleteAccNumIfExistsInTable("5093257");

		//Step 30: Log out
		estesEfmMaintenceApp.logout();


	}

	/*
	 * EFMReferenceMaintenence - User Page - Verify the user can Add new user  to the Terminal 
	 */


	@Test(enabled= true, priority=10)

	public void executeQZ_6929()
			throws SQLException, ClassNotFoundException {

		String userID = "TEST.ADMIN";
		// Step 1:Refer to attached Baseline testing document and Validate USER Page
		// USER Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin, Password : tadmin1

		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:User
		estesFinalMileUserPage.validateHeader("Welcome");
		estesFinalMileUserPage.clickOnLink("User");
		estesFinalMileUserPage.validatePageTitle("User");

		// Step 5:From User page, Verify the following fields are
		// displayed:User,Terminal Id
		String[] val = { "User", "Terminal Id" };
		estesFinalMileUserPage.verifyFields(val);

		// Step 6:From User Info section, enter value for the following field:User
		/*
		 * Note: Before a user added for any specific terminal he/she should have access
		 * to EFM Ref. Maint. for testing purposes users (TEST.USER, GARIPBA) if these
		 * users are already added, delete first than add again.
		 */
		estesFinalMileUserPage.deleteUserIfDisplayed(userID);
		estesFinalMileUserPage.enterUserName(userID);

		// Step 7:From User Info section, select value from the following drop
		// down:Terminal Id
		estesFinalMileUserPage.selectTerminal("001 - RICHMOND");

		// Step 8:Click on Add button
		estesFinalMileUserPage.clickOnAddBtn();
		// Validate the following message:Information successfully saved.
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");
		// Validate above added User displayed in the Users Screen
		estesFinalMileUserPage.verifyUserIsAdded(userID);

		// Step 9:Execute SQL query to verify the above User added in the EFM00P170
		// table
		// Above added user displayed in the EFM00P170 table
		String query = "SELECT Username FROM fbfiles.efm00p170 where createtimestamp=(select max(createtimestamp) FROM fbfiles.efm00p170)";
		String actval = sqlDatalist.getColumnValue(query);
		TestUtil.verifyText(actval.trim(), userID);
		estesEfmMaintenceApp.logout();

	}

	/*
	 * EFMReferenceMaintenence - User Page - Verify Edit action is functioning for User page 
	 */
	@Test(enabled= true, priority=11)

	public void executeQZ_6928() throws Exception {

		// Step 1:Refer to attached Baseline testing document and Validate USER Page
		// USER Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:User
		estesFinalMileUserPage.validateHeader("Welcome");
		estesFinalMileUserPage.clickOnLink("User");
		estesFinalMileUserPage.validatePageTitle("User");

		// Step 5:From Users screen, click on Edit button for any User Id
		estesFinalMileUserPage.verifyUserPageHasData();
		estesFinalMileUserPage.editUser("1");
		// Validate User Id and Terminal get populated under USER INFO section
		String Uid = estesFinalMileUserPage.getUserID();

		// Step 6:From Terminal Id drop down, select any other Terminal Note: Record the
		// updated values
		String terminal2 = "003 - NORFOLK";
		estesFinalMileUserPage.selectTerminal(terminal2);

		// Step 7:Click on Update button
		estesFinalMileUserPage.clickOnUpdateBtn();

		// Step 8:From User Name Warning pop up window, click Yes button
		estesFinalMileUserPage.validateUserNameWarningDisplayed();
		estesFinalMileUserPage.clickOnYesBtn();
		// The following message will be displayed:
		// Information successfully saved.Validate the updates in the Users screen
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");
		estesFinalMileUserPage.verifyTerminalUpdated(Uid, terminal2);

		// Step 9:Execute SQL query to verify the above User added in the EFM00P170
		// table
		// Above made updates displayed in the EFM00P170 table
		String query = "SELECT terminalid FROM fbfiles.efm00p170 WHERE USERNAME ='" + Uid + "'";
		String actval = sqlDatalist.getColumnValue(query);
		TestUtil.verifyText(actval, "3");
		estesEfmMaintenceApp.logout();

	}

	/*
	 * EFMReferenceMaintenence - User Page - Verify Delete action is functioning for User page 
	 */
	@Test(enabled= true, priority=12)

	public void executeQZ_6927()
			throws Exception {

		// Step 1:Refer to attached Baseline testing document and Validate USER Page
		// USER Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin ,Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:User
		estesFinalMileUserPage.validateHeader("Welcome");
		estesFinalMileUserPage.clickOnLink("User");
		estesFinalMileUserPage.validatePageTitle("User");

		// Step 5:From User page, Verify the following fields are displayed:User
		// ,Terminal Id
		String[] val = { "User", "Terminal Id" };
		estesFinalMileUserPage.verifyFields(val);

		// Step 6:From Users screen, click on Delete button for any User Id
		estesFinalMileUserPage.verifyUserPageHasData();
		String userID = estesFinalMileUserPage.getUserIdFromGrid("1");
		String terminal = estesFinalMileUserPage.getTerminalFromGrid("1");
		estesFinalMileUserPage.deleteUser("1");
		// Delete User Warning pop up window will be displayed
		estesFinalMileUserPage.verifyDeletePopUpIsDisplayed();

		// Step 7:From Delete User Warning pop up window, click on Yes button
		estesFinalMileUserPage.clickOnYesBtn();
		testUtil.setHardWait(2000);
		// Step 8:From Users screen, verify deleted User Id is not displayed
		// Above deleted User Id is not displayed in the Users screen
		estesFinalMileUserPage.verifyUserIDnotDisplayed(userID);

		// Step 9:Execute SQL query to verify the above User Deleted from the EFM00P170
		// table
		// Above user is not displayed in the EFM00P170 table
		String query = "SELECT * FROM fbfiles.efm00p170 WHERE USERNAME ='" + userID + "'";
		String actval = sqlDatalist.getColumnValue(query);
		TestUtil.verifyText(actval, null);
		estesEfmMaintenceApp.logout();

	}
	/*
	 * EFMReferenceMaintenance - Consignee Page - Verify the user can Add Consignee Type 
	 */
	@Test(enabled= true, priority=13)

	public void executeQZ_6916() throws Exception {

		String consigneeName = "TESTER CONSIGNEE " + testUtil.randomString(2);

		// Step 1:Refer to attached Baseline testing document and Validate Consignee
		// Page
		// Consignee Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html

		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin, Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:Consignee
		estesFinalMileUserPage.validateHeader("Welcome");
		estesFinalMileUserPage.clickOnLink("Consignee");

		// Step 5:From Consignee drop down menu, click on Type
		estesFinalMileUserPage.clickOnLink("Type");

		// Step 6:From Consignee Type page, verify the following fields are
		// displayed:Consignee Type,Active
		estesFinalMileUserPage.validatePageTitle("Consignee Type");
		String[] val = { "Consignee Type", "Active" };
		estesFinalMileUserPage.verifyFields(val);

		// Step 7:From CONSIGNEE TYPE SETUP screen, enter value into the following
		// field:Consignee Type
		estesFinalMileConsigneePage.enterConsigneeName(consigneeName);

		// Step 8:From CONSIGNEE TYPE SETUP screen, select value from the following drop
		// down:Active
		estesFinalMileConsigneePage.selectActiveFlag("Y");

		// Step 9:Click on Add button
		estesFinalMileConsigneePage.clickOnAddBtn2();
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");
		// Newly added Consignee Type will be displayed in the Consignee Types screen
		estesFinalMileConsigneePage.verifyConsigneeIsAdded(consigneeName);

		// Step 10:Execute the following SQL query to verify the above Consignee Type
		// added in the EFM00P110 table
		// Select * from fbfiles. EFM00P110;
		String query = "SELECT CONSIGNEETYPEDESCRIPTION FROM fbfiles.EFM00P110 where createtimestamp=(select max(createtimestamp) FROM fbfiles.EFM00P110)";
		String actval = sqlDatalist.getColumnValue(query);
		// Above added Consignee type displayed in the EFM00P110 table
		TestUtil.verifyText(actval.trim(), consigneeName);

		estesEfmMaintenceApp.logout();

	}

	/*
	 * EFMReferenceMaintenance - Consignee Page - Verify the user can Add Consignee Type Codes 
	 */
	@Test(enabled= true, priority=14)

	public void executeQZ_6917()
			throws Exception {



		String typeValue = "1";
		String actCode = null;

		// Step 1:Refer to attached Baseline testing document and Validate Consignee
		// Page
		// Consignee Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:Consignee
		estesFinalMileUserPage.clickOnLink("Consignee");

		// Step 5:From Consignee drop down menu, click on Code
		estesFinalMileUserPage.clickOnLink("Code");

		// Step 6:From Consignee Type Codes page, verify the following fields are
		// displayed:Consignee Type Consignee Type code
		estesFinalMileUserPage.validatePageTitle("Consignee Type Codes");
		String[] val = { "Consignee Type", "Consignee Type Code" };
		estesFinalMileUserPage.verifyFields(val);

		// Step 7:From CONSIGNEE TYPE INFO screen, click on Add button without entering
		// any values into the fields
		estesFinalMileConsigneePage.clickOnAddBtn();
		// Validate the Consignee Type and Consignee Type Code fields are highlighted
		// and
		// the following Error message is displayed for blank left fields:Required Field
		estesFinalMileConsigneePage.verifyRequiredFieldDisplayed();

		// Step 8:From CONSIGNEE TYPE CODE SETUP screen, select value from the following
		// drop downs:
		// Consignee Type Consignee type Code
		String type = estesFinalMileConsigneePage.selectConsigneeType(typeValue);
		actCode = estesFinalMileConsigneePage.selectConsigneeTypeCode();
		// step 9:Click on Add button
		estesFinalMileConsigneePage.clickOnAddBtn();
		String actCode2 = estesFinalMileConsigneePage.changeCommodityCodeIfErrorDisplayed(actCode);
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");
		// Newly added Consignee Type with Consignee Type Code will be
		// displayed in the Consignee Type Codes screen
		String[] actVal = actCode2.split("-");
		estesFinalMileConsigneePage.verifyConsigneeTypeIsAdded(type, actVal[0].trim());

		// Execute SQL query to verify the above Consignee Type Code is Added to the
		// select * from fbfiles.EFM00P110 order by updatetimestamp desc
		// and select * from fbfiles.EFM00P120 order by updatetimestamp desc tables
		// Above added Consignee type displayed in the EFM00P110 and EFM00120 tables
		String query = "SELECT CONSIGNEETYPEID FROM fbfiles.EFM00P110 where CONSIGNEETYPEDESCRIPTION = '" + type
				+ "' ";
		String typeId = sqlDatalist.getColumnValue(query);
		TestUtil.verifyText(typeId.trim(), typeValue);

		String query2 = "SELECT code FROM fbfiles.EFM00P120 where CONSIGNEETYPEID = '" + typeId
				+ "' order by updatetimestamp desc";
		List<String> actcode = sqlDatalist.getFirstRowDetailsFromEXLAQA(query2);
		TestUtil.verifyText(actcode.get(0).trim(), actVal[0].trim());

		estesEfmMaintenceApp.logout();

	}

	/*
	 * EFMReferenceMaintenance - Consignee Page - Verify the user can Add Consignee Type Codes 
	 */
	@Test(enabled= true, priority=15)

	public void executeQZ_6919()
			throws Exception {

		// Step 1:Refer to attached Baseline testing document and Validate Consignee
		// Page
		// Consignee Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:Consignee
		estesFinalMileUserPage.clickOnLink("Consignee");

		// Step 5:From Consignee drop down menu, click on Code
		estesFinalMileUserPage.clickOnLink("Code");

		// Step 6:From CONSIGNEE TYPE INFO screen, click on Delete button for any
		// Consignee Type code
		estesFinalMileConsigneePage.verifyUserPageHasData();
		String typeValue = estesFinalMileConsigneePage.getConsigneeTypeFromGrid("1");
		String actCode = estesFinalMileConsigneePage.getConsigneeTypeCodeFromGrid("1");
		estesFinalMileConsigneePage.deleteConsigneeTypecode("1");

		// Step 7:From Delete Consignee Type Code Warning pop up window, click Yes
		// button
		estesFinalMileUserPage.verifyDeletePopUpIsDisplayed();
		estesFinalMileUserPage.clickOnYesBtn();
		testUtil.setHardWait(2000);

		// Validate deleted consignee code is not displayed in the Consignee Type Codes
		// screen
		estesFinalMileConsigneePage.verifyConsigneeTypecodenotDisplayed(typeValue, actCode);
		estesEfmMaintenceApp.logout();

	}
	/*
	 * EFMReferenceMaintenance - Consignee Page - Verify the user can Add Consignee Type Lookup 
	 */
	@Test(enabled= true, priority=16)

	public void executeQZ_6918()
			throws Exception {

		String typeValue = "1";
		String lookUpText = "EFM_TESTER_" + testUtil.randomString(2);

		// Step 1:Refer to attached Baseline testing document and Validate Consignee
		// Page
		// Consignee Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:Consignee
		estesFinalMileUserPage.clickOnLink("Consignee");

		// Step 5:From Consignee drop down menu, click on Type Lookup
		estesFinalMileUserPage.clickOnLink("Type Lookup");

		// Step 6:From Consignee Type page, verify the following fields are displayed:
		// Consignee Type, `Lookup Text
		estesFinalMileUserPage.validatePageTitle("Consignee Type Lookup");
		String[] val = { "Consignee Type", "Lookup Text" };
		estesFinalMileUserPage.verifyFields(val);

		// Step 7:From CONSIGNEE TYPE LOOKUPSETUP screen, select valuefrom the following
		// field:Consignee Type
		String type = estesFinalMileConsigneePage.selectConsigneeTypeLookUp(typeValue);

		// Step 8:From CONSIGNEE TYPE LOOKUP SETUP screen, enter value into the
		// following field:Lookup Text
		estesFinalMileConsigneePage.enterLookUpText(lookUpText);

		// Step 9:Click on Add button
		estesFinalMileConsigneePage.clickOnAddBtn3();
		// The following message will be dislayed:Information successfully saved.
		// Validate above added Consignee Type lookup in the Consignee Type Lookup
		// screen
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");
		estesFinalMileConsigneePage.verifyConsigneeTypeLookUpDisplayed(type, lookUpText);

		// Step 10:Execute SQL query to verify the above Consignee Type Lookup added in
		// the
		// select * from fbfiles.EFM00P000 table
		String query = "select PROPERTYVALUE from fbfiles.EFM00P000 where PROPERTYDESCRIPTION = 'LOOKUP WORD FOR "
				+ type + "'";
		List<String> actval = sqlDatalist.getValuesFromExlaDatabase(query);
		System.out.println(actval);
		// Above added Consignee type Lookup displayed in the EFM00P000 table
		estesFinalMileConsigneePage.verifyPublishedLookUpIsDisplayed(lookUpText, actval);
		estesEfmMaintenceApp.logout();

	}

	/*
	 *  EFMReferenceMaintenance - Consignee Page - Verify the user can Delete Consignee Type Lookup 
	 */

	@Test(enabled = true, priority = 17)

	public void executeQZ_6920()
			throws Exception {

		// Step 1:Refer to attached Baseline testing document and Validate Consignee
		// Page
		// Consignee Page is as before
		// Step 2:Open the folowing link:
		// http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);

		// Step 3:Login to EFM Reference Maintenance application
		// Use the following credentials: User Name: test.admin Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		// Step 4:From EFM Reference Maintenance homepage, click on the following
		// tab:Consignee
		estesFinalMileUserPage.clickOnLink("Consignee");

		// Step 5:From Consignee drop down menu, click on Type Lookup
		estesFinalMileUserPage.clickOnLink("Type Lookup");

		// Step 6:From Consignee Type page, verify the following fields are displayed:
		// Consignee Type, `Lookup Text
		estesFinalMileUserPage.validatePageTitle("Consignee Type Lookup");
		String[] val = { "Consignee Type", "Lookup Text" };
		estesFinalMileUserPage.verifyFields(val);

		//Step 7:From CONSIGNEE TYPE LOOKUPSETUP screen, select already existing 
		//value from the following drop down:Consignee Type
		estesFinalMileConsigneePage.verifyUserPageHasData();
		String type = estesFinalMileConsigneePage.getConsigneeTypeFromGrid("1");
		String actLookUpText = estesFinalMileConsigneePage.getConsigneeLookUpTextFromGrid("1");
		estesFinalMileConsigneePage.selectConsigneeTypeByText(type);

		//Step 8:From CONSIGNEE TYPE LOOKUP SETUP screen, enter already existing value
		//for selected Consignee Type into the following field: Lookup Text
		estesFinalMileConsigneePage.enterLookUpText(actLookUpText);

		//Step 9:Click on Add button
		estesFinalMileConsigneePage.clickOnAddBtn3();
		//Dublicate Consignee Type Lookup Warning window pops up
		estesFinalMileConsigneePage.verifyWarningPopUpIsDisplayed();

		//Step 10:From Dublicate Consignee Type Lookup Warning pop up window, click on NO button
		estesFinalMileConsigneePage.clickOnNoBtn();

		//Step 11:From Consignee Type Lookup screen, click Delete button for any Consignee Type Lookup
		estesFinalMileConsigneePage.deleteLookUp(type, actLookUpText);

		//step 12:From Delete Consignee Lookup Text Warning pop up window, Click Yes
		estesFinalMileUserPage.verifyDeletePopUpIsDisplayed();
		estesFinalMileUserPage.clickOnYesBtn();
		testUtil.setHardWait(1000);
		//Validate above deleted Consignee Type Lookup is not displayed in the Consignee Type Lookup screen
		estesFinalMileConsigneePage.verifyConsigneeTypecodenotDisplayed(type, actLookUpText);

		//Step 13:Execute  SQL query to verify the above Consignee Type Lookup deleted from the EFM00P000 table
		//Above deleted Consignee type Lookup is not displayed in the EFM00P000 table
		String query = "select * from fbfiles.EFM00P000 where PROPERTYDESCRIPTION = 'LOOKUP WORD FOR RESIDENTIAL' and PROPERTYVALUE ='EFM_TESTER_NA'";
		String actval = sqlDatalist.getColumnValue(query);
		System.out.println(actval);
		TestUtil.verifyText(actval, null);

	}

	/*
	 * EFM RM Site - Verify Operations User is able to ADD Accessorial alert code on
	 * EFM Maintenance site
	 */

	@Test(enabled = true, priority = 18)

	public void executeQZ_6904()
			throws Exception {

		String accessorial ="ABB - ABB POWER WT INSPE";
		//Stepp 1:Call <EFM Reference Maintenance>

		driver.get(efmUrl);
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		//Ste 2:From EFM Reference Maintenance Home Page (Terminal Tab)
		estesFinalMileUserPage.validatePageTitle("Terminal");

		//Ste 3:Click <Electronic DR > tab	Note: Tab name might change in UAT
		estesFinalMileUserPage.clickOnLink("Electronic DR");

		//Step 4:From the sub-tab options,Click <Outbound Accessorials> Note: Sub-tab name might change in UAT
		estesFinalMileUserPage.clickOnLink("OutBound Accessorials");

		//Step 5:From the Outbound Accessorials Controls page, below Outbound Accessorials Form Setup section,
		//Click *Accessorial: drop down
		estesFinalMileUserPage.validatePageTitle("Outbound Accessorials Controls");

		//Step 6:From the Accessorial drop down list, 
		//Click <the first code>  (Note: Select is excluded in dropdown)	Ex: First Code can ABB, ADV, ACH or anything
		estesFinalMileElectronicDRPage.deleteAccessorial(accessorial);
		estesFinalMileElectronicDRPage.selectAccessorial(accessorial);

		//Step 7:Click Add button
		estesFinalMileElectronicDRPage.clickOnAddBtn();

		//Step 8:From Outbound Accessorials Controls page, 
		//Verify below message populated on the screen	----Information successfully saved.
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");

		//Step 9:From Outbound Accessorials Controls page, 
		//Verify the above added code populated in the Outbound Accessorial Options section.
		estesFinalMileElectronicDRPage.verifyAccessorialIsAdded(accessorial);

		// Step 10:From IBM ACS or System i Navigator or STRSQL on AS400, exceute below SQL query:
		// Validate above added code populated in the table EDI30P1602.
		/*
		 * SELECT TRADINGPARTNERID, ACCESSORIALCODE,BOLCHARGECODE,DSDESC,DSTYPE FROM
		 * FBFILES.EDI30P1602 JOIN FBFILES.FRP012 ON ACCESSORIALCODE = DSCODE WHERE
		 * TRADINGPARTNERID = 'EFMD';
		 */
		String[] val = accessorial.split("-");
		String query = "SELECT ACCESSORIALCODE FROM FBFILES.EDI30P1602 "
				+ "JOIN FBFILES.FRP012 ON ACCESSORIALCODE = DSCODE WHERE TRADINGPARTNERID = 'EFMD'";
		List<String> actval = sqlDatalist.getValuesFromExlaDatabase(query);
		System.out.println(actval);
		estesFinalMileElectronicDRPage.verifyAccessorialIsDisplayedinExla(val[0], actval);
		estesEfmMaintenceApp.logout();

	}

	/*
	 * EFMReferenceMaintenance - Electronic DR - Verify Operations User is able to
	 * Delete Accessorial alert code on EFM Maintenance site
	 */

	@Test(enabled = true, priority = 19)

	public void executeQZ_6922()
			throws Exception {

		//Step 1:Open the folowing link: 
		//http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);
		//Step 2:Login to EFM Reference Maintenance application
		//Use the following credentials: User Name: test.admin	Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		//Step 3: From EFM Reference Maintenance Home Page, Click on Electronic DR  tab
		estesFinalMileUserPage.clickOnLink("Electronic DR");

		//Step 4: From Electronic DR  sub-tab options, click on the following: Outbound Accessorials 
		estesFinalMileUserPage.clickOnLink("OutBound Accessorials");

		//Step 5: From the Outbound Accessorials Options screen, click on Delete button for any Accessorial Code
		String acc = estesFinalMileElectronicDRPage.getAccessorialFromGrid("1");
		//Step 6 :From the Delete Accessorial Warning pop up window, click Yes button
		estesFinalMileElectronicDRPage.deleteAccessorial(acc);
		testUtil.setHardWait(2000);

		//The following message will be dispayed:Information successfully saved.
		/** success message is not dispayed after deleting accessorial **/
		//estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");

		//Step 7 :From Outbound Accessorial Options screen, verify deleted Accessorial code is not displayed
		estesFinalMileElectronicDRPage.verifyAccessorialnotDisplayed(acc);

		/* Step 8:
		 * From IBM ACS or System i Navigator or STRSQL on AS400, exceute below SQL
		 * query:
		 * 
		 * Validate above deleted code not displayed in the table EDI30P1602.
		 * 
		 * SQL: SELECT TRADINGPARTNERID, ACCESSORIALCODE,BOLCHARGECODE,DSDESC,DSTYPE
		 * FROM FBFILES.EDI30P1602 JOIN FBFILES.FRP012 ON ACCESSORIALCODE = DSCODE WHERE
		 * TRADINGPARTNERID = 'EFMD';
		 */

		String query ="SELECT * FROM FBFILES.EDI30P1602 "
				+ "JOIN FBFILES.FRP012 ON ACCESSORIALCODE = DSCODE "
				+ "WHERE TRADINGPARTNERID = 'EFMD' and ACCESSORIALCODE = '"+acc+"'";
		String actval = sqlDatalist.getColumnValue(query);
		System.out.println(actval);
		TestUtil.verifyText(actval, null);
		estesEfmMaintenceApp.logout();				


	}

	/*
	 * EFM RM Site - Verify Operations User is able to ADD Shipping instructions alert code on EFM Maintenance site 
	 */
	@Test(enabled = true, priority = 20)

	public void executeQZ_6905()
			throws Exception {

		String shpInstruction = "A - AMOUNT DUE EXLA";

		//Step 1:Call <EFM Reference Maintenance>
		//http://qa.estesinternal.com/efmmaintenance/login.html
		driver.get(efmUrl);
		//Step 2:Login to EFM Reference Maintenance application
		//Use the following credentials: User Name: test.admin	Password : tadmin1
		estesEfmMaintenceApp.login(username20, password20);
		testUtil.setHardWait(1000);

		//Step 3:From EFM Reference Maintenance Home Page (Terminal Tab)
		estesFinalMileUserPage.validatePageTitle("Terminal");
		estesFinalMileUserPage.clickOnLink("Electronic DR");

		//Step 4:From the sub-tab options,Click <Shipping Instructions> 
		//Note: Sub-tab name might change in UAT
		estesFinalMileUserPage.clickOnLink("Shipping Instructions");

		//Step 5:From the Shipping Instructions Controls page, 
		//On Shipping Instructions Form Setup section,Click *Shipping Instruction: drop down
		estesFinalMileUserPage.validatePageTitle("Shipping Instructions Controls");
		estesFinalMileElectronicDRPage.deleteAccessorial(shpInstruction);
		//Step 6:From the Shipping Instruction drop down list, 
		//Click <the first code>  (Note: Select code is	excluded in dropdown)
		estesFinalMileElectronicDRPage.selectShipppingInstruction(shpInstruction);

		//Step 7:Click Add button
		estesFinalMileElectronicDRPage.clickOnAddBtn2();

		//Ste 8:From Shipping Instructions Controls page, 
		//Verify below message populated on the screen	Information successfully saved.
		estesFinalMileUserPage.validateSuccessMsg("Information successfully saved.");

		/*
		 * //Stepp 9:From Shipping Instructions Controls page,
		 * 
		 * Verify the above added code populated in the Shipping Instructions Options
		 * section.
		 * 
		 * Ex: Shipping Instruction Handling Code Actions
		 * 
		 * ADH - ADHESIVE DIVISION MOT-Other DELETE (button)
		 */

		estesFinalMileElectronicDRPage.verifyShippingInstructionIsAdded(shpInstruction);

		/*
		 * //Ste 10:From IBM ACS or System i Navigator or STRSQL on AS400, exceute below
		 * SQL query:
		 * 
		 * Validate above added code do not transmit flag not populated in the table
		 * EDI30P0152.
		 * 
		 * SQL: SELECT TRADINGPARTNERID,
		 * SHIPINSTRCODE,SPECIALHANDLINGCODE,DONOTTRANSMIT, DSDESC,DSTYPE FROM
		 * FBFILES.EDI30P0152 JOIN FBFILES.FRP012 ON SHIPINSTRCODE = DSCODE WHERE
		 * SIXPRTNRID = 'EFMD' AND DONOTTRANSMIT <> 'X';
		 */
		//As we need to verify DONOTTRANSMIT VALUE query modified
		logger.info("DONOTTRANSMIT value should be empty");
		String query= "SELECT DONOTTRANSMIT FROM FBFILES.EDI30P0152 "
				+ "JOIN FBFILES.FRP012 ON SHIPINSTRCODE = DSCODE "
				+ "WHERE SIXPRTNRID = 'EFMD' AND DONOTTRANSMIT <> 'X' ORDER BY SHIPINSTRCODE asc ";
		String actval = sqlDatalist.getColumnValue(query);
		System.out.println(actval);
		TestUtil.verifyText(actval," ");
		estesEfmMaintenceApp.logout();			


	}

	/**
	 * @author Saunders
	 */

	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * EFM - Verify EDI 211 triggered to EFM when bill changes from No Signature
	 * Required to Signature Required(UPDATED)
	 */

	@Test(enabled = false, priority = 21)
	public void executeQZ_6938()
			throws Exception {

		// Step 1: Open the folowing link:

		driver.get(efmUrl);

		// Step 2: Login to the EFM Reference Maintenance application

		estesEfmMaintenceApp.login(username20, password20);
		// 3 From EFM Reference Maintenance homepage, Click on the following tab:
		estesEfmMaintenceApp.clickAccountExclusions();

		// 4 From Account Exclusions page, Accounts screen,Verify the following Shipper
		// and 3rd Party Accounts are not displayed on the list.

		String shipperAccountNo = "1487062";
		String billerAccountNo = "5020031";
		assertFalse(estesEfmMaintenceApp.isAccountDisplayed(new String[] { shipperAccountNo, billerAccountNo }));

		// 5 From EFM Reference Maintenance top menu bar, Click on the following tab:
		estesEfmMaintenceApp.clickSignatureOptions();

		// 6 Add below 3rd Party Account (Billing Party) as below:
		// Account : 5020031
		// Signature Option : No Signature Required


		// 7 Click on ADD button
		estesEfmMaintenceApp.enterSignatureOptionsAll(billerAccountNo, "No Signature Required", "Always",
				"No Appointment Required");

		// Note: If an Error message displays, find out the account
		// in the Signature Options screen and click Delete button, then try to add
		// again

		estesEfmMaintenceApp.deleteAccountIfErrorIsDisplayedAndAddAgain(billerAccountNo, "No Signature Required",
				"Always", "No Appointment Required");

		// Step 8:  Data Need: Use below Shipper and Consignee details for reserving and
		FreightBill freightBill = new FreightBill();
		freightBill.setSignatureTestValues();

		//Step 9 10: Call <Reserve Freight Bills> and Call <Freight Bill - Create>

		String proNumber = freightBill.reserveAndCreateFreightBill();

		// 11 From AS400, Execute Business Rules job by inputting the following value in
		// the command line:
		// Call FBPGMS/EFM00C110
		// Jagacy
		// 12 Press Enter
		//			logger.info("Wait 30 seconds for Time Stamp difference");

		freightBill.runOutputCommands();

		// 13 Access below FTP path to view EDI 211 files.
		// PATH -
		// exlaqa\root\edi\xlator\outbound
		// 14 Open the sub-folder dated with current date
		// 15 From the sub-folder dated with current date, Identify the E211 flat file
		// that got created with above OTPRO(FB#), the most recent system date & time

		//Step 16:  Open E211 file(Note: open the E211 file in Notepad)

		String timeCommandRun = freightBill.getTimeCommandRun();
		testUtil.validateFreightBillOutputHasNoSignatureRequired(proNumber, timeCommandRun);

		// Step 17: Open the folowing link:

		driver.get(efmUrl);

		//Step 18:  Login to the EFM Reference Maintenance application

		estesEfmMaintenceApp.login("test.admin", "tadmin1");

		// Step 19: Click on Signature Options Tab
		estesEfmMaintenceApp.clickSignatureOptions();

		//Step 20:  From Signature Options screen, Actions column, Click on Edit for the

		estesEfmMaintenceApp.clickEditAcount(billerAccountNo);
		estesEfmMaintenceApp.verifySignatureOptionsArePopulated("5020031", "No Signature Required", "Always",
				"No Appointment Required");

		// From Signature Option drop down menu, change the value as below:
		// No Signature Required to Signature Required
		// No Appointment Required to Appointment Required
		estesEfmMaintenceApp.editSignatureOptions("Signature Required", "Always", "Appointment Required");
		// NOTE: For *Signature Required* - *appointments are required* - that is a
		// recipient of the package needs to be physically present for the delivery to
		// take place so they can physically provide a signature that they received the
		// package.

		// 22 Click on Update.
		// From pop up window, Click on <Yes>
		estesEfmMaintenceApp.clickUpdateButton();

		// 23 From AS400, Execute Business Rules job by inputting the following value in
		// the command line:
		// Call FBPGMS/EFM00C110
		// Press Enter
		// Note: Press F7 to display command linerc
		freightBill.runOutputCommands();

		// 24 Access below link to view EDI 211 files.
		// PATH -
		// exlaqa\root\edi\xlator\outbound

		// 25 Open the sub-folder dated with current date

		// 26 From the sub-folder dated with current date, Identify the E211 flat file
		// that got created with above OTPRO(FB#), the most recent system date & time

		// 27 Open E211 file(Note: open the E211 file in Notepad) and verify Freight
		// bill details and below segment information is populated for above processed
		// Freight bill
		// - AT5 Segment with - OTD
		// Note: OTD stands for "Signature Required"
		// AT5 Segment with - OTD/SIGNATURE REQUIRED
		// Note: OTD stands for "Signature Required"
		timeCommandRun = freightBill.getTimeCommandRun();
		testUtil.validateFreightBillOutputHasSignatureRequired(proNumber, timeCommandRun);

		// 28 Navigate back to EFM Reference Maintenance
		// From the Signature Options tab, Identify the below Account in Signature
		// Options table:
		// Account Customer Name Signature options Signature Option Type Actions
		// 5020031 OCEAN WORLD SHIPPING Signature Required Always Edit Delete
		estesEfmMaintenceApp.isAccountDisplayed(
				new String[] { "5020031", "OCEAN WORLD SHIPPING", "Signature Required", "Always", "" });
	}


	/**
	 * 
	 * @Author Saunders
	 */


	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * EFM - Verify EDI 211 triggered to EFM when bill changes from Signature
	 * Required to No Signature Required(UPDATED)
	 */


	@Test(enabled = false, priority =22)
	public void executeQZ_6939()
			throws Exception {

		//Step 1: Open the following template and select the QA url


		driver.get("https://efm-maintenance-efm-qa.apps.ocpnonprod.estesinternal.com/login.html");

		//Step 2: Login to the EFM Reference Maintenance application

		estesEfmMaintenceApp.login("test.admin", "tadmin1");

		//Step 3: From EFM Reference Maintenance homepage, Click on the following tab:Account Exclusions
		estesEfmMaintenceApp.clickAccountExclusions();

		// Step 4: From Account Exclusions page, Accounts screen,Verify the following Shipper
		// and 3rd Party Accounts are not displayed on the list shipper

		String shipperAccountNo = "1487062";
		String billerAccountNo = "5020031";
		assertFalse(estesEfmMaintenceApp.isAccountDisplayed(new String[] { shipperAccountNo, billerAccountNo }));

		//Step 5: From EFM Reference Maintenance top menu bar, Click on the following tab:
		// Signature Options
		estesEfmMaintenceApp.clickSignatureOptions();

		//Step 6: Add below 3rd Party Account (Billing Party) as below:


		//Step 7: Click on ADD button.

		estesEfmMaintenceApp.enterSignatureOptionsAll(billerAccountNo, "Signature Required", "Always",
				"Appointment Required");
		estesEfmMaintenceApp.deleteAccountIfErrorIsDisplayedAndAddAgain(billerAccountNo, "Signature Required", "Always",
				"Appointment Required");

		//Step 8: Data Need: Use below Shipper and Consignee details for reserving and
		// creating a Freight Bill.

		FreightBill freightBill = new FreightBill();
		freightBill.setSignatureTestValues();

		//Step 9 & 10: Call <Reserve Freight Bills> <Freight Bill - Create>
		String proNumber = freightBill.reserveAndCreateFreightBill();

		// Step 11: From the AS400 application, on command line call the following two jobs
		// (EFM Business rule job):

		// Note: Press F7 key display command line
		// 1. Call FBPGMS/EFM00C110
		// Press Enter
		// 2. CALL FBPGMS/EFM00R111
		// Press Enter
		//Step 12:  Press Enter
		freightBill.runOutputCommands();

		//Step 13: Access below FTP path to view EDI 211 files.
		// PATH -
		// exlaqa\root\edi\xlator\outbound
		// Step 14: Open the sub-folder dated with current date
		// Step 15: From the sub-folder dated with current date, Identify the E211 flat file
		// that got created with above OTPRO(FB#), the most recent system date & time
		// Step 16: Open E211 file(Note: open the E211 file in Notepad)
		// Validate Freight bill details and below segment information is populated for
		// above processed Freight bill:
		// NEW:
		// AT5*SIGNATURE REQUIRED~
		// __________
		// OLD: (*we don’t have the code OTD anymore)
		// AT5 Segment with - OTD/SIGNATURE REQUIRED
		// Note: OTD used to stand for "Signature Required"
		String timeCommandRun = freightBill.getTimeCommandRun();
		testUtil.validateFreightBillOutputHasSignatureRequired(proNumber, timeCommandRun);

		//Step 17: Open the following template and select the QA url

		driver.get("https://efm-maintenance-efm-qa.apps.ocpnonprod.estesinternal.com/login.html");

		//Step 18: Login to the EFM Reference Maintenance application

		estesEfmMaintenceApp.login(username20, password20);

		//Step 19: Click on Signature Options Tab
		estesEfmMaintenceApp.clickSignatureOptions();

		//Step 20: From Signature Options screen, Actions column, Click on Edit for the
		// following 3rd Party Account (Biller):
		estesEfmMaintenceApp.clickEditAcount(billerAccountNo);
		estesEfmMaintenceApp.verifySignatureOptionsArePopulated("5020031", "Signature Required", "Always",
				"Appointment Required");

		//Step 21: From Signature Option drop down menu, change the value as below:
		// Signature Required to No Signature Required
		estesEfmMaintenceApp.editSignatureOptions("No Signature Required", "Always", "No Appointment Required");

		//Step 22: Click on Update button
		// From Pop up window, click <Yes> button
		estesEfmMaintenceApp.clickUpdateButton();

		// Step 23: Again from the AS400 application, on command line call the following two
		// jobs (EFM Business rule job):
		// *one at a time
		// Note: Press F7 key display command line

		freightBill.runOutputCommands();

		//Step 24: Access below link to view EDI 211 files.
		// PATH -
		// exlaqa\root\edi\xlator\outbound
		// 25 Open the sub-folder dated with current date
		// 26 From the sub-folder dated with current date, Identify the E211 flat file
		// that got created with above OTPRO(FB#), the most recent system date & time
		// 27 Open E211 file(Note: open the E211 file in Notepad) and verify Freight
		// bill details and below segment information is populated for above processed
		// Freight bill:
		// Validate Freight bill details and below segment information is populated for
		// above processed Freight bill
		// New:
		// AT5*NO SIGNATURE REQ'D_UNATTENDED~
		// __________
		// OLD:
		// AT5 Segment with ‘DRO/ NO SIGNATURE REQ'
		// **we don’t have the code DRO anymore (DRO used to stand for "No Signature
		// Required”)
		timeCommandRun = freightBill.getTimeCommandRun();
		testUtil.validateFreightBillOutputHasNoSignatureRequired(proNumber, timeCommandRun);

	}

	/**
	 * 
	 *@author 
	 */

	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * 
	 * EFM - EFM Maintenance App - Verify E211 generated with No Signature Required
	 * (DRO) code when account setup for No Signature Required-Always
	 */


	@Test(enabled = false, priority=23)
	public void executeQZ_9537()
			throws JagacyException, Throwable {
		// step 1: Go to

		driver.navigate().to(efmUrl);

		estesEfmMaintenceApp.login(username20, password20);
		Thread.sleep(1000);

		// Step 3: Click Signature Options
		estesEfmMaintenceApp.clickSignatureOptions();
		Thread.sleep(2000);

		// Step 4-5: From Signature Options Controls page, enter the following values: and click on Add

		estesEfmMaintenceApp.enterSignatureOptionsAll("5002013", "No Signature Required", "Always",
				"No Appointment Required");

		// JAGACY

		// Step 6 - 8:
		// Reserve Freight Bill
		// Create Freight Bill


		/*
		 * Reserve Bill
		 */
		SessionVt session = null;
		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originalTerm = "087";

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		Thread.sleep(2000);

		if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();
		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField1("1");
		jagacyUtil.pressEnter();
		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(
				session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originalTerm);
		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(
				session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode("2302234");
		// Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originalTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		/* Create Freight Bill */
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originalTerm);
		freightBillingMenuScreen.enterFreightBill(originalTerm, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("8700777");
		freightBillUpdateScreen.enterConsName("NOSIGN ALWAYS");
		freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
		freightBillUpdateScreen.enterConsCity("COLUMBUS");
		freightBillUpdateScreen.enterConsState("OH");
		freightBillUpdateScreen.enterConsZip("43228");
		freightBillUpdateScreen.enter3Pt("5002013");
		freightBillUpdateScreen.enterCartTo("E087");
		freightBillUpdateScreen.enterPONum("224091819");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		// page 2
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		// page 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstruction1("HD");
		jagacyUtil.pressEnter();
		Thread.sleep(2000);

		// TIME STAMP FOR 1ST FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16)
		// HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp1 = tmString.substring(2, 16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "")
				.replaceAll(" ", "");
		System.out.println("TIME STAMP FOR FIRST FILE: " + timeStamp1);

		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		// Step 9-12:
		// GO to Folder on Network drive : \\exlaqa\root\edi\xlator\outbound
		// Login with "QATSTFRTBL"/"QATEST2019"
		// Select Folder with Today's Date
		// Look for "E211" file with the containing the Freight Bill Num
		// Assert " AT5***NO SIGNATURE REQUIRED" is not in the file
		Thread.sleep(5000);
		testUtil.readerOutboundFileAssertStringIsNotThere("E211", fbNum, timeStamp1, "AT5***NO SIGNATURE");

		// Step 9-12:
		// GO to Folder on Network drive : \\exlaqa\root\edi\xlator\outbound
		// Login with "QATSTFRTBL"/"QATEST2019"
		// Select Folder with Today's Date
		// Look for "E211" file with the containing the Freight Bill Num
		// Assert " AT5***NO SIGNATURE REQUIRED" is not in the file

		logger.info("Wait 30 seconds for Time Stamp difference");
		Thread.sleep(30000);

		// 13: From the AS400 application, on command line enter the following value:
		// Call FBPGMS/EFM00C110, CALL FBPGMS/EFM00R111
		// Start new Session with Don's credentials, it takes you right to Command
		// Screen. (F7 to get to command Screen Does not work while Automated)

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(2000);
		// Second Time Stamp here
		// TIME STAMP FOR 1ST FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16)
		// HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm2 = new Timestamp(System.currentTimeMillis());
		String tmString2 = tm2.toString();
		String timeStamp2 = tmString2.substring(2, 16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "")
				.replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " + timeStamp2);

		// Step 14: Get to Master Menu
		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		// Step 15: Enter Option 1 from Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 16: From Freight Billing Menu screen, enter the following: Option 1
		// (Freight bill inquiry )
		// User : Reg
		// Terminal : 087
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(
				session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "Reg", "087");

		// Step 17: From Freight bill inquiry screen, enter FB number
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(
				session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 18 Verify following values populated on Freight Bill details:
		// Code: NOSIGN
		// Description: NO SIGNATURE REQUIRED
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForNoSignCodeAndDescription();

		// Step 19: Press F10 to view Freight Bill comments
		jagacyUtil2.pressF10();
		;
		Thread.sleep(2000);

		// Step 20: Press Page down
		jagacyUtil2.pressPageDown();
		Thread.sleep(2000);

		// Step 21: Verify following values populated on the Freight Bill comments:
		// ACT: NSG
		// Comment: No Signature Required
		freightBillDetailsScreen.checkForNoSignatureActAndComment();

		if (session2 != null) {
			session2.abort();
			session2.close();
		}

		// Step 22-27: Go back to \\exlaqa\root\edi\xlator\outbound, Check for new file
		// created by timestamp
		// Assert: L11*<OTPRO>*CN~
		// AT5***NO SIGNATURE REQUIRED~
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp2, "AT5***NO SIGNATURE");

		Thread.sleep(2000);

		// Step 28 - 29: Delete Account Number: "5002013"
		estesEfmMaintenceApp.deleteAccNumIfExistsInTable("5002013");

		// Step 30: Log out
		estesEfmMaintenceApp.logout();

	}

	/**
	 * 
	 * @author Jeff
	 */

	/*
	 * Passed on 10 Aug 2022
	 * Added 2 seconds more to the wait before session open
	 * EFM - Verify updated E211 file generated when CEMAIL added or updated on
	 * freight bill and status is not Delivered
	 */

	@Test(enabled= true,priority=24)

	public void executeQZ_9535() throws JagacyException, Throwable{


		//JAGACY\

		//Step 1-3:Reserve Freight and Create Freight Bill


		/*
		 * Reserve Bill
		 */		
		SessionVt session = null;
		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originalTerm="087";

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();		
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		Thread.sleep(1000);
		if(session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();
		Thread.sleep(1000);
		//Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField1("1");
		jagacyUtil.pressEnter();
		Thread.sleep(1000);
		//Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originalTerm);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode("2302234");
		//Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originalTerm+billNum;
		System.out.println("Freight Bill No " +fbNum);
		jagacyUtil.pressF3();

		/* Create Freight Bill */
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originalTerm);
		freightBillingMenuScreen.enterFreightBill(originalTerm, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);

		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("8700777");
		freightBillUpdateScreen.enterConsName("REG NOSIG");
		freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
		freightBillUpdateScreen.enterConsCity("COLUMBUS");
		freightBillUpdateScreen.enterConsState("OH");
		freightBillUpdateScreen.enterConsZip("43228");
		freightBillUpdateScreen.enterCartTo("E087");
		freightBillUpdateScreen.enterPONum("224091819");
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		Faker faker = new Faker();
		String blNumber = "S" + faker.number().digits(7);
		freightBillUpdateScreen.enterMasterBlNo(blNumber);
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		//page 2
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		//page 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("CPHONE", "8008008000", "CEMAIL", "QA@ESTES.COM");
		jagacyUtil.pressEnter();

		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR FIRST FILE: " +timeStamp);
		Thread.sleep(2000);	



		if (session != null) {
			session.abort();
			session.close();
		}

		logger.info("Wait 12 seconds for Time Stamp Difference");
		Thread.sleep(12000);


		//Step 4-6: CALL EFM BUSINESS RULES
		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		//Get past Intermediate screen if exists
		if(session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();


		//Call FBPGMS/EFM00C110, Call FBPGMS/EFM00R111
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");


		//Step 7 Call SQL query
		String query = "SELECT FHOT,FHPRO,FHCNM,FDCMCL,FDDES FROM FBFILES.FRP001 JOIN FBFILES.FRP002 ON FHOT = FDOT AND FHPRO=FDPRO WHERE FDCMCL = 'CEMAIL' AND FHOT=087 AND FHPRO = " + billNum;
		List<String> fbDetails = sqlDatalist.getFirstRowDetailsFromEXLAQA(query);
		System.out.println(fbDetails);
		String FHOT = "0" + fbDetails.get(0).trim();
		String FHPRO = fbDetails.get(1).trim();
		String FHCNM = fbDetails.get(2).trim();
		String FDDES = fbDetails.get(4).trim();

		//Step 8-11: Verify above SQL query output results matches the following segment data in the E211 file(recorded value):
		//OTPRO in  L11* = FHOT,FHPRO 
		//Consignee Name and EMAIL in G61* = FHCNM, FDDES
		//E211 file contains OTPRO, Consignee Name and Email ID data that matches with SQL query output results
		testUtil.readerOutboundFileAssertEmailIsThereAndL11ProNum("E211", fbNum, timeStamp, FHOT, FHPRO, FHCNM, FDDES);

		//Step 12: From the Freight Billing Menu screen, enter Option 3 (Freight bill update)
		//Get to Master Screen
		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);
		//Option 1
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		//Option 3 to update bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("3", "Reg", "087");

		//Step 13: From Freight bill inquiry screen, enter FB number
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);

		//Step 14: From Freight Bill UPDATE screen, press Enter press Enter
		jagacyUtil2.pressEnter();
		jagacyUtil2.pressEnter();
		jagacyUtil2.pressEnter();

		//Step 15-16: From screen 3 (Accessorial Charges and Shipping Instructions), on Shipping Instructions section, find Code, CEMAIL.
		//Update the following: Email ID: EIT@Estes.com (or anything else)
		UpdateScreen3 updateScreen3 = new UpdateScreen3(session2);
		updateScreen3.updateEmail("EIT@Estes.com");
		Thread.sleep(2000);
		jagacyUtil2.pressEnter();
		//SECOND TIME STAMP HERE
		Timestamp tm2 = new Timestamp(System.currentTimeMillis());
		String tmString2 = tm2.toString();
		String timeStamp2 = tmString2.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " +timeStamp2);

		//Step 17 Run SQL query
		String query2 = "SELECT FHOT,FHPRO,FHCNM,FDCMCL,FDDES FROM FBFILES.FRP001 JOIN FBFILES.FRP002 ON FHOT = FDOT AND FHPRO=FDPRO WHERE FDCMCL = 'CEMAIL' AND FHOT=087 AND FHPRO = " + billNum;
		List<String> fbDetails2 = sqlDatalist.getFirstRowDetailsFromEXLAQA(query2);
		System.out.println(fbDetails2);

		String FHOT2 = "0" + fbDetails2.get(0).trim();
		String FHPRO2 = fbDetails2.get(1).trim();
		String FHCNM2 = fbDetails2.get(2).trim();
		String FDDES2 = fbDetails2.get(4).trim();


		//Step 18-21. Validate E211 file data with SQL data
		//OTPRO in  L11* = FHOT,FHPRO 
		//Consignee Name and Email in G61* = FHCNM, FDDES
		//E211 file contains OTPRO, Consignee Name and Email ID data that matches with SQL query output results
		Thread.sleep(3000);
		testUtil.readerOutboundFileAssertEmailIsThereAndL11ProNum("E211", fbNum, timeStamp2, FHOT2, FHPRO2, FHCNM2, FDDES2);

	}

	/**
	 * @author Jeff
	 */

	/*
	 * EFM - EFM Inbound - Verify EDI 214 received with Invalid AT5(UPDATED)
	 */

	@Test(enabled= true, priority=25)

	public void executeQZ_6935() throws JagacyException, Throwable{

		//Step 1-4 Reverse FB and Create and Update FB

		SessionVt session = null;
		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originalTerm="087";

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();		
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		Thread.sleep(1000);
		if(session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();
		//Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField1("1");
		jagacyUtil.pressEnter();
		Thread.sleep(1000);
		//Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originalTerm);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode("1487062");
		//Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originalTerm+billNum;
		System.out.println("Freight Bill No " +fbNum);
		jagacyUtil.pressF3();

		/* Create Freight Bill */
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originalTerm);
		freightBillingMenuScreen.enterFreightBill(originalTerm, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		testUtil.setHardWait(1000);
		freightBillUpdateScreen.enterCons("8700777");
		freightBillUpdateScreen.enterConsName("REG NOSIG");
		freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
		freightBillUpdateScreen.enterConsCity("COLUMBUS");
		freightBillUpdateScreen.enterConsState("OH");
		freightBillUpdateScreen.enterConsZip("43228");
		freightBillUpdateScreen.enterCartTo("E087");
		freightBillUpdateScreen.enterPONum("224091819");
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		Faker faker = new Faker();
		String blNumber = "S" + faker.number().digits(7);
		freightBillUpdateScreen.enterMasterBlNo(blNumber);
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		//page 2
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		//page 3
		jagacyUtil.pressEnter();
		if (session != null) {
			session.abort();
			session.close();
		}

		logger.info("Wait 5 seconds for File Creating");
		Thread.sleep(5000);


		//Step 4-17: Edit File to the given formats and save it to the \\exlaqa\root\edi\xlator\inbound folder
		testUtil.saveOutboundFileToInboundFolder("E211", fbNum);
		logger.info("Wait 10 secs for System to process file and move to Archive Folder");
		testUtil.setHardWait(7000);


		//Step 18-19 : Call Business Rules
		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		//Get past Intermediate screen if exists
		if(session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();


		//Call FBPGMS/EFM00C110, Call FBPGMS/EFM00R111
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(2000);

		//Step 20: Go to master menu
		commandEntryScreen.enterCommand("Call XXC870");
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);

		//Step 21: Enter 1 for Freight Billing Menu
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		//Step 22-23: From Freight Billing Menu screen, enter the following: Option 1 (Freight bill inquiry )
		//User : Reg 
		//Terminal : 087
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "Reg", "087");

		//Step 24: From Freight bill inquiry screen, enter FB number
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		//Step 25: Verify following values is NOT populated on Freight Bill details:
		//Code: XYZ
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCodeNotSupposeToBeThere("XYZ");

		//Step 26: Press F10 to view Freight Bill comments
		//Verify follwing values is NOT populated
		//Act : AUC
		//USER : FBR10R105
		//COMMENT: XYZ added - ACC UPDT
		jagacyUtil2.pressF10();;
		Thread.sleep(2000);

		//Verify
		freightBillDetailsScreen.checkForAcctUserCommentNotSupposeToBeThere("AUC", "FBR10R105", "XYZ added - ACC UPDT");


		//Step 27-28: Exit Jagacy
		if (session2 != null) {
			session2.abort();
			session2.close();
		}
	}

	/**
	 * According to Ruslina, this test will fail as they are expecing changes.Todd will follow up
	 *@author Quinci Cuthbert
	 */

	/*
	 * My Estes: EFM Unattended Delivery - Verify if a PRO contains Hazmat,
	 * the consignee cannot sign up for unattended/no signature required via the website
	 */
	@Test(enabled = false, priority = 26)
	public void executeQZ_11583() throws JagacyException, Throwable {


		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originTerm = "021";
		String shipperCode = "2195135";
		String consigneeNum = "B036051";
		String consZip = "85251";
		String poNum = testUtil.getTodayDate() + "11583";
		String expMsg = "We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur.";

		// Step 1: Reserve Freight Bill (QZ-384)
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

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originTerm);

		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);

		// Step 2: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 3: Create a Hazmat Freight Bill (QZ-8094)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterConsName("LINDA CARDELLINI");
		freightBillUpdateScreen.enterConsAddress("3370 N HAYDEN RD");
		freightBillUpdateScreen.enterConsCity("SCOTTSDALE");
		freightBillUpdateScreen.enterConsState("AZ");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterCartTo("E297");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2, including UN Number (necessary for Hazmat FB)
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("1000");
		freightBillUpdateScreen2.enterOnlyUNNo("UN1818");
		jagacyUtil.pressEnter();
		
		
		
		

		// From Guidelines for Hazardous Materials Inquiry screen, enter the following value against selected Hazard Class.
		GuidelinesForHazardousMaterialsInquiryScreen guidelinesForHazardousMaterialsInquiryScreen = new GuidelinesForHazardousMaterialsInquiryScreen(session);
		guidelinesForHazardousMaterialsInquiryScreen.selectUnCode("X");

		// From the HAZMAT Skeleton Screen Builder screen, enter the following value(s):
		HazmatSkeletonScreen hazmatSkeletonScreen = new HazmatSkeletonScreen(session);
		hazmatSkeletonScreen.enterZone("B");
		jagacyUtil.pressF10();
		jagacyUtil.pressEnter();

		// From next Update FB screen, enter the following value(s)
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("HAZMAT");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 4 - 5: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 6 - 7: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(2000);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 8 - 9: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 021
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", "021");

		// Step 10: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 11: Verify the code ‘HAZ’ is populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("HAZ");

		// Step 12: Go to EFM Delivery Page
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Step 13: Enter the Pro number in to the ‘Tracking Number’ text box and the DT/Consignee zip code into the ‘Delivery Zip/postal Code’ text box
		efmHomeDeliveryPage.enterTrackingNumber(fbNum);
		testUtil.setHardWait(1000);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);

		// Step 14: Press ‘TRACK NOW’
		efmHomeDeliveryPage.clickOnTrackNowButton();

		// Step 15: Verify if the following message is NOT displayed under the “View Shipment Details” on the webpage:
		// Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.
		// Expected message is: We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur.
		// (Confirmed with Rusalina)
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * 
	 *@author Quinci
	 */

	/*
	 * My Estes: EFM Unattended Delivery- Verify if a PRO is already tagged as no
	 * signature required, the consignee cannot sign up for unattended/no signature
	 * required via the website
	 */

	@Test(enabled = true, priority = 27)
	public void executeQZ_11584() throws JagacyException, Throwable {


		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originTerm = "021";

		//String shipperCode = "0536400";

		//String shipperCode = "2195135";--> not valid shipper code
		String shipperCode = "2195135";//newly added

		String consigneeNum = "B036051";
		String consZip = "85251";
		String poNum = testUtil.getTodayDate() + "11584";
		String expMsg = "Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.";

		// Step 1: Reserve Freight Bill (QZ-384)
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

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originTerm);

		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);

		// Step 2: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 3: Create a Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterConsName("OLIVIA HOLT  ");
		freightBillUpdateScreen.enterConsAddress("3370 N HAYDEN RD");
		freightBillUpdateScreen.enterConsCity("SCOTTSDALE");
		freightBillUpdateScreen.enterConsState("AZ");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterCartTo("E297");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("NOSIGN");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 4 - 5: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		testUtil.setHardWait(5000);
		session = new SessionVt(name, "exlaqa", terminal);
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session);
		session.open();
		
		LoginScreen loginScreen2 = new LoginScreen(session);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 6 - 7: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(2000);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 8 - 9: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 021
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", "021");

		// Step 10: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 11: Verify the code ‘NOSIGN’ is populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("NOSIGN");

		// Step 12: Go to EFM Delivery Page
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Step 13: Enter the Pro number in to the ‘Tracking Number’ text box and the DT/Consignee zip code into the ‘Delivery Zip/postal Code’ text box
		efmHomeDeliveryPage.enterTrackingNumber(fbNum);
		testUtil.setHardWait(1000);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);

		// Step 14: Press ‘TRACK NOW’
		efmHomeDeliveryPage.clickOnTrackNowButton();

		// Step 15: Verify if the following message is displayed under the “View Shipment Details” on the webpage:
		// Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.
		testUtil.setHardWait(40000);
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);
	}


	/**
	 * 
	 * @auther Justin
	 */

	/*
	 * My Estes: EFM Unattended Delivery - Verify if the PRO requires an appointment
	 * but no signature required, Consignee can sign-up for unattended/no signature
	 * required via the website
	 */

	@Test(enabled = true, priority=28)
	public void executeQZ_11574() throws Exception {


		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String user1 = "QATEST"; 
		String originTerminal = "086";
		String sCode = "3013340";
        String consZip = "22603";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "devabni", password = "nithyadev";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		//Step 1: Call <Reserve a Freight Bill>
		session.open();	
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();

		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		//Step 2: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		testUtil.setHardWait(40000);
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);

		//Press F3 to exit
		jagacyUtil.pressF3(); 

		//Step 3: Call <Create a Freight Bill>
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		logger.info("originTerminal : "+originTerminal);
		logger.info("billNum : "+billNum);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		testUtil.setHardWait(40000);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("0500777");
		freightBillUpdateScreen.enterConsName("EDWARD JOHNSON");
		freightBillUpdateScreen.enterConsAddress("220 Ashland Dr.");
		freightBillUpdateScreen.enterConsCity("WINCHESTER");
		freightBillUpdateScreen.enterConsState("VA");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterPONum("123456");						
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		freightBillUpdateScreen.enterCartTo("E005");
		jagacyUtil.pressEnter();

		//Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("Its a test");
		freightBillUpdateScreen2.enterWgt2("1000");

		jagacyUtil.pressEnter();

		jagacyUtil.pressEnter();

		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);

		//freightBillUpdateScreen3.enterAccessoryInstructions("HD", "APT");  -Commanded the line as it is not entering the value properly  
		freightBillUpdateScreen3.enterAccessorialCode("APT");
		freightBillUpdateScreen3.enterShippingInstruction2("HD");

		jagacyUtil.pressEnter();

		//Step 4: Press F1 and return to Freight Bill Menu
		jagacyUtil.pressF1();

		//Step 5: Press F7()
		jagacyUtil.pressF7(); 

		//Step 6: Run the following two commands (EFM Business rule job): *One at a time*
		//1.)Call FBPGMS/EFM00C110 - Press Enter
		//2.)Call FBPGMS/EFM00R111 - Press Enter
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session); 
		testUtil.setHardWait(2000);
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00C110");
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00R111");

		//Step 7: Press F3
		commandEntryScreen.enterF3Key();

		//Step 8: From the 'Freight Billing Menu' screen, select Option: 1, User: QATEST, Terminal: 086
		freightBillingMenuScreen.enterFreightBillMenuOption(option, user1, originTerminal);

		//Step 9: Press Enter 
		//		jagacyUtil.pressEnter();

		//Step 10: Enter Pro Number captured earlier
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session); 
		freightBillInquiryEnterFieldValuesScreen.enterFreightBillNum(originTerminal, billNum); 
		testUtil.setHardWait(5000); 
		jagacyUtil.pressEnter();

		//Step 11: Verify the code ‘APT’ is populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session); 
		freightBillDetailsScreen.verifyCodeNDescription("APT", "APPOINTMENT CHARGE"); 

		if(session != null) {
			session.abort();
			session.close();
		}                         

		//Step 12: enter URL    
		//driver.get(efmUrl); //changed the URL
		driver.get(url4); 

		//Step 13: Enter the Pro number in to the ‘Tracking Number’ text box and the DT/Consignee zip code into the ‘Delivery Zip/postal Code’ text box
		efmHomeDeliveryPage.enterTrackingNumber(fbNum); 
		efmHomeDeliveryPage.enterDeliveryZipCode(consZip);

		//Step 14: Press 'Track Now'
		efmHomeDeliveryPage.clickOnTrackNowButton();

		//Step 15: Verify if the following message is displayed under the "View Shipment Details" on the webpage
		efmShipmentDetailsPage.validateFullUnattendedDeliveryMessage();      

	}

	/**
	 * @author Justin
	 */

	/*
	 * My Estes: EFM Unattended Delivery- Verify if the service level on the PRO is
	 * greater than CURBSIDE, the consignee cannot sign up for unattended/no
	 * signature required via the website
	 * 
	 */

	@Test(enabled = true, priority=29)
	public void executeQZ_11575() throws Exception {

		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String user1 = "QATEST"; 
		String originTerminal = "004";
		String sCode = "4073438";
		String consZip = "95688";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "devabni", password = "nithyadev";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		//Step 1: Call <Reserve a Freight Bill>
		session.open();	
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();

		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		//Step 2: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);

		//Press F3 to exit
		jagacyUtil.pressF3(); 

		//Step 3: Call <Create a Freight Bill>
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("B000007");
		freightBillUpdateScreen.enterConsName("RJ MARSHALL");
		freightBillUpdateScreen.enterConsAddress("707 ALDRIDGE RD");
		freightBillUpdateScreen.enterConsCity("VACAVILLE");
		freightBillUpdateScreen.enterConsState("CA");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterPONum("123456");						
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		freightBillUpdateScreen.enterCartTo("E212");
		jagacyUtil.pressEnter();

		//Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("Its a test");
		freightBillUpdateScreen2.enterWgt2("1000");

		jagacyUtil.pressEnter();

		jagacyUtil.pressEnter();

		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstructions("HD", "INS", "ASMBL");

		jagacyUtil.pressEnter();

		//Step 4: Press F1 and return to Freight Bill Menu
		jagacyUtil.pressF1();

		//Step 5: Press F7()
		jagacyUtil.pressF7(); 

		//Step 6: Run the following two commands (EFM Business rule job): *One at a time*
		//1.)Call FBPGMS/EFM00C110 - Press Enter
		//2.)Call FBPGMS/EFM00R111 - Press Enter
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session); 
		testUtil.setHardWait(2000);
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00C110");
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00R111");

		//Step 7: Press F3
		commandEntryScreen.enterF3Key();

		//Step 8: From the 'Freight Billing Menu' screen, select Option: 1, User: QATEST, Terminal: 086
		freightBillingMenuScreen.enterFreightBillMenuOption(option, user1, originTerminal);

		//Step 9: Press Enter 
		//		jagacyUtil.pressEnter();

		//Step 10: Enter Pro Number captured earlier
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session); 
		freightBillInquiryEnterFieldValuesScreen.enterFreightBillNum(originTerminal, billNum); 
		testUtil.setHardWait(5000); 
		jagacyUtil.pressEnter();

		//Step 11: Verify the code ‘APT’ is populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session); 
		freightBillDetailsScreen.verifyCodeNDescription("INS", "INSIDE DELIVERY CHGS"); 

		if(session != null) {
			session.abort();
			session.close();
		}

		//Step 12: enter URL
		driver.get("https://estes-express-uat.estesinternal.com/solutions/final-mile/home-delivery"); 

		//Step 13: Enter the Pro number in to the ‘Tracking Number’ text box and the DT/Consignee zip code into the ‘Delivery Zip/postal Code’ text box
		efmHomeDeliveryPage.enterTrackingNumber(fbNum); 
		efmHomeDeliveryPage.enterDeliveryZipCode(consZip);

		//Step 14: Press 'Track Now'
		efmHomeDeliveryPage.clickOnTrackNowButton();

		//Step 15: Verify if the following message is displayed under the "View Shipment Details" on the webpage
		/*
		“Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.”
		 */
		efmShipmentDetailsPage.validateSetUpApptMessage();
	}



	/**
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * @author Quinci Cuthbert - created on 1/21/2022
	 * 
	 * My Estes: EFM Unattended Delivery (STANDARD)- Verify if PRO is residential and within the lower 48 US mainlands,
	 * Consignee cannot sign-up for  unattended/no signature required via the website
	 */
	@Test(enabled = false, priority = 30)
	public void executeQZ_11588() throws JagacyException, Throwable {

		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originTerm = "152";
		String shipperCode = "0252464";
		String consigneeNum = "4029969";
		String consZip = "30458";
		String poNum = "000011588";
		String expMsg = "Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.";

		// Step 1: Reserve Freight Bill (QZ-384)
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

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originTerm);

		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);

		// Step 2: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 3: Create a Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterCartTo("E040");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		freightBillUpdateScreen.enterDescOnPONumScreen("QZ11588");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HDP");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 4 - 5: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		session.open();
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session);
		LoginScreen loginScreen2 = new LoginScreen(session);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 6 - 7: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(180000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(180000);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 8 - 9: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 152
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", originTerm);

		// Step 10: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 11: Verify the codes 'UNATT' and ‘NOSIGN’ are populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("UNATT");
		freightBillDetailsScreen.checkForCode("NOSIGN");

		// Step 12: Go to EFM Delivery Page
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Step 13: Enter the Pro number in to the ‘Tracking Number’ text box and the DT/Consignee zip code into the ‘Delivery Zip/postal Code’ text box
		efmHomeDeliveryPage.enterTrackingNumber(fbNum);
		testUtil.setHardWait(1000);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);

		// Step 14: Press ‘TRACK NOW’
		efmHomeDeliveryPage.clickOnTrackNowButton();

		// Step 15: Verify if the following message is displayed under the “View Shipment Details” on the webpage:
		// Our records indicate no signature is required. We will place your shipment out for delivery as soon as possible.
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);
	}

	/**
	 * 
	 * @author Justin
	 * Created: 01/20/2022
	 */

	@Test(enabled = true, priority=30)
	public void executeQZ_11577() throws Exception {


		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String user1 = "QATEST"; 
		String originTerminal = "004";
		String sCode = "4073438";
		String consZip = "29617";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "devabni", password = "nithyadev";

		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		//Step 1: Call <Reserve a Freight Bill>
		session.open();	
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();

		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		//Step 2: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);

		//Press F3 to exit
		jagacyUtil.pressF3(); 

		//Step 3: Call <Create a Freight Bill>
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("B017667");
		freightBillUpdateScreen.enterConsName("Tripp Harrell                ");
		freightBillUpdateScreen.enterConsAddress("846 N Highway 25 BYP");
		freightBillUpdateScreen.enterConsCity("Greenville");
		freightBillUpdateScreen.enterConsState("SC");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterPONum("YYMMDDHHMM");						
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		freightBillUpdateScreen.enterCartTo("E031");
		jagacyUtil.pressEnter();

		//Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("Its a test");
		freightBillUpdateScreen2.enterWgt2("1000");

		jagacyUtil.pressEnter();

		jagacyUtil.pressEnter();

		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstructions("HD", "APT");
		freightBillUpdateScreen3.enterShippingInstruction("SIGRQD", 2); 

		jagacyUtil.pressEnter();

		//Step 4: Press F1 and return to Freight Bill Menu
		jagacyUtil.pressF1();

		//Step 5: Press F7()
		jagacyUtil.pressF7(); 

		//Step 6: Run the following two commands (EFM Business rule job): *One at a time*
		//1.)Call FBPGMS/EFM00C110 - Press Enter
		//2.)Call FBPGMS/EFM00R111 - Press Enter
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session); 
		testUtil.setHardWait(2000);
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00C110");
		testUtil.setHardWait(250000);
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00R111");

		//Step 7: Press F3
		commandEntryScreen.enterF3Key();

		//Step 8: From the 'Freight Billing Menu' screen, select Option: 1, User: QATEST, Terminal: 004
		freightBillingMenuScreen.enterFreightBillMenuOption(option, user1, originTerminal);

		//Step 9: Press Enter 
		//		jagacyUtil.pressEnter();

		//Step 10: Enter Pro Number captured earlier
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session); 
		freightBillInquiryEnterFieldValuesScreen.enterFreightBillNum(originTerminal, billNum); 
		testUtil.setHardWait(5000); 
		jagacyUtil.pressEnter();

		//Step 11: Verify the code ‘APT’ is populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session); 
		freightBillDetailsScreen.verifyCodeNDescription("NOAPT", "NO APPOINTMENT CHARGE"); 
		freightBillDetailsScreen.verifyCodeNDescription("SIGRQD", "SIGNATURE REQUIRED"); 

		if(session != null) {
			session.abort();
			session.close();
		}

		//Step 12: enter URL
		driver.get("https://estes-express-uat.estesinternal.com/solutions/final-mile/home-delivery"); 

		//Step 13: Enter the Pro number in to the ‘Tracking Number’ text box and the DT/Consignee zip code into the ‘Delivery Zip/postal Code’ text box
		efmHomeDeliveryPage.enterTrackingNumber(fbNum); 
		efmHomeDeliveryPage.enterDeliveryZipCode(consZip);

		//Step 14: Press 'Track Now'
		efmHomeDeliveryPage.clickOnTrackNowButton();

		//Step 15: Verify if the following message is displayed under the "View Shipment Details" on the webpage
		efmShipmentDetailsPage.validateFullUnattendedDeliveryMessageContact(); 
	}


	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * EFM 990 file processing - Verify 990 file is processed by the ESB and
	 * comments are added to freight bill
	 * 
	 * @author coxda
	 * @throws Exception
	 */

	@Test(enabled= false, priority=31)

	public void executeQZ_9402() throws Exception {

		// Steps 1 - 2: Create freight bill
		FreightBill freightBill = new FreightBill();
		//	freightBill.setCartTo("E087");
		//	testUtil.setHardWait(2000);
		//	String otProNumber = freightBill.reserveAndCreateFreightBill();
		String otProNumber="0878108890";
		/* ACCEPT TEST */

		// Steps 3 - 4: Process the 990 accept file
		testUtil.placeThe990AcceptTestFileOnTheESBFolderForProcessing(otProNumber);

		// Step 5: Verify the 990 accept file finished processing
		testUtil.verifyTheProcessed990FileIsDisplayed(otProNumber, "accept");

		// Step 6: Ensure that the file is processed in the database

		String st02Segment = "497800002";
		String query = "SELECT * FROM FBFILES.CCI10P990H WHERE HDRTRNSCN = '" + st02Segment + "'";
		List<List<String>> records = sqlDatalist.viewQueryResults(query);
		String hdrprocflg = records.get(records.size() - 1).get(9);
		logger.info("[" + hdrprocflg + "]");

		// Step 7: Verify a new record is added in the database based on the timestamp

		// Wait to process:
		testUtil.setHardWait(30000);

		String ot = otProNumber.substring(0, 3); // 2nd column
		String pro = otProNumber.substring(3); // 3rd column
		String query2 = "SELECT * FROM FBFILES.CCI10P990D WHERE DTLOT='" + ot + "' AND DTLPRO='" + pro + "'";
		List<List<String>> records2 = sqlDatalist.viewQueryResults(query2);
		assertTrue(records2.size() == 1, "There was not exactly one corresponding new record.");
		String dtlcrttmsp = sqlDatalist.viewQueryResults(query2).get(0).get(14);
		String formattedDate = dtlcrttmsp.substring(5, 7) + dtlcrttmsp.substring(8, 10) + dtlcrttmsp.substring(2, 4);
		assertTrue(formattedDate.equals(testUtil.getTodayDate()),
				"Date of database entry does not match today's date.");

		// Step 8: Process the records with the command
		TempletTests templateTest = new TempletTests();
		templateTest.callCommand("CALL CCI990C100", username16, password16);

		// Step 9: Check SCAC
		sqlDatalist.viewQueryResults("SELECT * FROM FBFILES.FRP002 WHERE FDOT='" + ot + "' and FDPRO='" + pro + "'");
		sqlDatalist.viewQueryResults("SELECT FHA5 FROM FBFILES.FRP001 WHERE FHOT='" + ot + "' AND FHPRO='" + pro + "'");
		logger.info("Verify SCAC: [" + records2.get(0).get(10) + "]");

		// Step 10 - 15: Make a Freight Bill Inquiry and confirm the EFM text displays
		freightBill.verifyFreightBillHasDNPCodeWithEFMDescription(otProNumber);

		/* DECLINE TEST */

		// Step 16 (17.3 - 17.4): Process the 990 decline file
		testUtil.placeThe990DeclineTestFileOnTheESBFolderForProcessing(otProNumber);

		// Step 17.5: Verify the 990 decline file finished processing
		testUtil.verifyTheProcessed990FileIsDisplayed(otProNumber, "decline");

		// Step 17.6: Ensure that the file is processed in the database

		String stSegment = "497800002";
		String query3 = "SELECT * FROM FBFILES.CCI10P990H WHERE HDRTRNSCN = '" + stSegment + "'";
		List<List<String>> records3 = sqlDatalist.viewQueryResults(query3);
		String hdrprocflg2 = records3.get(records3.size() - 1).get(9);
		logger.info("[" + hdrprocflg2 + "]");

		// Step 17.7: Verify a new record is added in the database based on the
		// timestamp
		testUtil.setHardWait(30000);
		String query4 = "SELECT * FROM FBFILES.CCI10P990D WHERE DTLOT='" + ot + "' AND DTLPRO='" + pro
				+ "' ORDER BY DTLCRTTMSP DESC";
		List<List<String>> records4 = sqlDatalist.viewQueryResults(query4);

		String dtlcrttmsp2 = records4.get(0).get(14);
		String formattedDate2 = dtlcrttmsp2.substring(5, 7) + dtlcrttmsp2.substring(8, 10)
		+ dtlcrttmsp2.substring(2, 4);
		assertTrue(formattedDate2.equals(testUtil.getTodayDate()),
				"Date of database entry does not match today's date.");

		templateTest.callCommand("CALL CCI990C100", username16, password16);
		List<List<String>> records5 = sqlDatalist.viewQueryResults(query4);

		sqlDatalist.viewQueryResults("SELECT FHA5 FROM FBFILES.FRP001 WHERE FHOT='" + ot + "' AND FHPRO='" + pro + "'");
		sqlDatalist.viewQueryResults("SELECT * FROM FBFILES.FRP002 WHERE FDOT='" + ot + "' and FDPRO='" + pro + "'");
		List<List<String>> result = sqlDatalist
				.viewQueryResults("SELECT FHA5 FROM FBFILES.FRP001 WHERE FHOT='" + ot + "' AND FHPRO='" + pro + "'");
		assertTrue(result.get(0).get(0).trim().equals(""),
				"FHA5 was not removed, so the freight bill was not declined successfully.");

		List<List<String>> records6 = sqlDatalist
				.viewQueryResults("SELECT * FROM FBFILES.CCI10P990D WHERE DTLOT='" + ot + "' AND DTLPRO='" + pro + "'");
		assertTrue(records6.get(records6.size() - 1).get(7).equals("D"),
				"Most recent freight bill did not show D in the DTLAODFLG field.");

	}


	/**
	 * 
	 * @author Nithya
	 * Created: 01/26/2022
	 */

	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * 
	 * Estes Final Mile - Verify user is able to sign up for Unattended Delivery on 
	 * EFM tracking site and track status after signup - Appointment Set Status 
	 */

	@Test(enabled = false, priority = 32)

	public void executeQZ_6944()
			throws JagacyException, Exception {


		String expMsg1 = "You may sign up for an unattended delivery, or we will contact you to set up an appointment. Please note if you do not sign up for unattended delivery, we will give you a call to schedule an appointment when the freight has reached our final facility.";
		String expMsg = "You have opted out of an appointment and no signature is required. We will place your shipment out for delivery in accordance with the instructions you provided as soon as possible.";
		String radiobutton = "BACK DOOR", unattendedDeliveryName = "Dee", gateOrCode = "358";
		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String originTerminal = "087";

		// Step 1: Use shipper account 2302234 in next Reserve Freight Bill step
		String sCode = "2302234";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String name = "myJagacyVT";

		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// Step 2: Reserve and create freight bill
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);

		jagacy.util.JagacyUtil jagacyUtil= new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();
		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);

		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(
				session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(
				session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
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
		freightBillUpdateScreen3.enterShippingInstruction1("HD");
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;

		// Step 3: Open My Estes using QA url
		// Step 4: N/A
		// Step 8: From Estes QA Homepage, select *Solutions*, next select *Final Mile*
		myEstesHomePage.clickOnSolutionsTab();
		myestesSolutionPage.clickOnFinalMileLink();

		// Step 8: From the Residential page, select *Estes Final Mile*
		myestesSolutionPage.clickOnEstesFinalMileLink();

		// Step 9: Estes Final Mile Home Delivery page, enter Tracking Number & Zip Code
		// on the Track a Shipment component
		efmHomeDeliveryPage.enterTrackingNumberInFormat(originTerminal, billNum);
		efmHomeDeliveryPage.enterDeliveryZipCodeInFormat(consZip);
		testUtil.setHardWait(6000);
		// Step 10: Click on *Track Now* button-Address link contains OTPRO and
		// Destination Zip Code in URL
		efmHomeDeliveryPage.clickOnTrackNowButton();
		efmShipmentDetailsPage.verifyAddressBarLinkContainsTrackingInfo(originTerminal, billNum, consZip);

		// Step 11: From the Shipment details page, the following should be displayed
		// above the Shipment Status
		// bar and populated with shipment data: *Tracking Number*:*ZIP/Postal
		// Code*Earliest Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();

		// Step 12: The following should be displayed on the Shipment Status bar:
		// Shipped *(GREEN)*,
		// Appointment Set, Out For Delivery, Delivered
		efmShipmentDetailsPage.verifySHippedColor();
		efmShipmentDetailsPage.verifyShippedStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyAppointmentSetStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyOutForDeliveryStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyDeliveredStatusBarTextIsDisplayed();

		// Step 13: Verify Shipment Details page is displayed with below message:"You
		// may sign up
		// for an unattended delivery, or we will contact you to set up an appointment.
		// Please note if you do not sign up for an unattended delivery, an appointment
		// must be set for your delivery to occur."
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg1);

		// Step 14: From the Shipment Details Page, click on the View Shipment Details
		// Accordion.
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		// Step 15: Verify *Unattended Delivery Signup* component and link not displayed
		// any where on the page; only the
		// following components are displayed: *Track another Shipment* *Contact Us*
		efmShipmentDetailsPage.verifyUnattendedDeliveryDisabled();

		// Step 16: From the "Want to receive text and email updates on your shipment?"
		// section, Enter the following values:

		efmShipmentDetailsPage.enterPhoneNumber("804-353-1901");
		efmShipmentDetailsPage.enterSecondaryPhoneNumber("804-353-1800");
		efmShipmentDetailsPage.selectORDeselectPrimaryPhoneNotificationsCheckBox();
		efmShipmentDetailsPage.selectORDeselectSecondaryPhoneNotificationsCheckBox();
		efmShipmentDetailsPage.enterEmail("qatest@estes-express.com");
		efmShipmentDetailsPage.enterOtherPhoneNumber("800-566-7777 1277777");

		// Step 17: Select the Email Address check box that reads: *Check here to send
		// shipment status updates to this email address.*
		efmShipmentDetailsPage.selectORDeselectEmailNotificationCheckBox();

		// Step 18: Click the *Update* button and validate Confirmation message is
		// received
		efmShipmentDetailsPage.clickOnUpdateButton();
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifyContactInfoUpdateConfirmationMessageIsDisplayed();

		// Step 19: From Shipment Details page, Click on the following link in message:
		efmShipmentDetailsPage.clickOnUnattendedDeliveryLink();

		// Step 20: From the Unattended Delivery Signup popup page, enter the following
		// values:
		efmShipmentDetailsPage.unattendedDeliveryName(unattendedDeliveryName);
		efmShipmentDetailsPage.unattendedDeliveryGateOrCode(gateOrCode);
		efmShipmentDetailsPage.radioButtonUnattendedDelivery(radiobutton);
		efmShipmentDetailsPage.unattendedDeliveryAuthorizeCheckBox();

		// Step 21: Click on *Finish* button
		efmShipmentDetailsPage.clickOnUnattendedDeliveryFinishButton();
		testUtil.setHardWait(8000);
		testUtil.reloadPage(); // added reload
		testUtil.setHardWait(2000);

		// Step 22: From the Shipment details page, the following should be displayed
		// above the Shipment Status bar
		// and populated with shipment data: *Tracking Number*:*ZIP/Postal Code*Earliest
		// Possible Delivery Date*
		efmShipmentDetailsPage.verifyTrackingNumberDisplays();
		efmShipmentDetailsPage.verifyZipOrPostalCodeDisplays();
		efmShipmentDetailsPage.verifyEarliestDeliveryDateDisplays();

		// Step 23: The following should be displayed on the Shipment Status bar:
		// Shipped *(GREEN)*, Out For Delivery, Delivered
		efmShipmentDetailsPage.verifySHippedColor();
		efmShipmentDetailsPage.verifyShippedStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyOutForDeliveryStatusBarTextIsDisplayed();
		efmShipmentDetailsPage.verifyDeliveredStatusBarTextIsDisplayed();

		// Step 24: Verify Shipment Details page is displayed with below message:"You
		// have opted out of an appointment
		// and no signature is required. We will place your shipment out for delivery in
		// accordance with the instructions
		// you provided as soon as possible."
		testUtil.reloadPage(); // refresh to display correct message
		testUtil.setHardWait(1000);
		efmShipmentDetailsPage.verifyScheduledDeliveryMessageDisplays(expMsg);

		// Step 25: From the Shipment Details Page, click on the View Shipment Details
		// Accordion.
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.clickOnViewShipmentDetailsButton();

		// Step 26,27: Verify *Unattended Delivery Signup* component and link not displayed
		// any where on the page; only the
		// following components are displayed: *Track another Shipment* *Contact Us*
		efmShipmentDetailsPage.verifyUnattendedDeliveryDisabled();

		// Step 28: From the "Want to receive text and email updates on your shipment?"
		// section, Enter the following values:

		efmShipmentDetailsPage.enterPhoneNumber("804-353-1901");
		efmShipmentDetailsPage.enterSecondaryPhoneNumber("804-353-1800");
		efmShipmentDetailsPage.selectORDeselectPrimaryPhoneNotificationsCheckBox();
		efmShipmentDetailsPage.selectORDeselectSecondaryPhoneNotificationsCheckBox();
		efmShipmentDetailsPage.enterEmail("qatest@estes-express.com");
		efmShipmentDetailsPage.enterOtherPhoneNumber("800-566-7777 1277777");

		// Step 28: Select the Email Address check box that reads: *Check here to send
		// shipment status updates to this email address.*
		efmShipmentDetailsPage.selectORDeselectEmailNotificationCheckBox();

		// Step 28: Click the *Update* button and validate Confirmation message is
		// received
		efmShipmentDetailsPage.clickOnUpdateButton();
		testUtil.setHardWait(2000);
		efmShipmentDetailsPage.verifyContactInfoUpdateConfirmationMessageIsDisplayed();


		// Step 29 - Step 35: N/A - RoundCube email steps cannot be automated

		/*
		 * // NOTE: Steps 29 - 35 cannot be completed because the freight bill fields
		 * will not populate in Jagacy for the validation steps in EXLAQA(back-end
		 * validation which we do not automated for now)
		 * 
		 * // Step 28: Navigate to AS400 EXLAQA session and log in with tester
		 * credentials
		 * 
		 * driver.navigate().back(); if (session != null) {
		 * System.out.println("Closing existing session"); session.abort();
		 * session.close(); }
		 * 
		 * testUtil.setHardWait(4000);
		 * 
		 * //Start session SessionVt session1 = null; session1 = new SessionVt(name,
		 * "exlaqa", terminal); session1.open(); testUtil.setHardWait(4000);
		 * 
		 * LoginScreen loginScreen1 = new LoginScreen(session1);
		 * loginScreen1.enterUserNPasswordCDOC(userName, password);
		 * Ltl38MasterMenuScreen ltl38mastermenu1 = new Ltl38MasterMenuScreen(session1);
		 * 
		 * // Step 29: From the LTL/38 master menu enter Option = 1 // Step 30: Press
		 * Enter ltl38mastermenu1.enterValueOptionField(option);
		 * 
		 * // Step 31: From the Freight Billing menu enter Option = 1, User - Test
		 * jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen1 = new
		 * jagacyVT.screens.FreightBillingMenuScreen(session1);
		 * freightBillingMenuScreen1.enterValueOptionField(option);
		 * freightBillingMenuScreen1.enterValueUserField(user);
		 * freightBillingMenuScreen1.enterTabKey();
		 * freightBillingMenuScreen1.enterTerminalNumber(originTerminal);
		 * 
		 * // Step 32: Press Enter freightBillingMenuScreen1.pressEnterKey();
		 */

		// Step 33: From the Freight bill inquiry menu, enter the 10 digit Origin
		// Terminal PRO number
		// Step 34: Press Enter
		/*
		 * Validate the following commodity codes are displayed on the freight bill:
		 * Example Template* WBCCN1 Primary 455-555-5555 1805 053020 WBCCN6 Pri Mobile
		 * text Upd = Y 1805 053020 WBCCN2 Secondary 343-434-3434 1805 053020 WBCCN7 2nd
		 * Mobile text Upd = Y 1805 053020 WBCCN5 Other 800-566-7777 x1277777 WBCCN5
		 * 1805 053020 WBCCN3 qatest@estes-express.com WBCCN3 1805 053020 WBCCN4 Email
		 * Notify = Y 1805 053020 NOSIGN NO SIGNATURE REQUIRED NSAUTH AUT- Dee GATECD
		 * Gate Code- 358 DELPRF Delivery Pref- BACK DOOR
		 */
		// *Note* if codes NOSIGN, NSAUTH, GATECD, DELPRF are not on the freight bill,
		// job EFM00R190 will need to be turned on
		/*
		 * FreightBillInquiryEnterFieldValuesScreen freightBillInqScreen = new
		 * FreightBillInquiryEnterFieldValuesScreen(session1);
		 * freightBillInqScreen.enterValueFreightBill(billNum); // TO-DO (if issue with
		 * freight bill inquiry screen is resolved): validation steps (try using
		 * DisplayCommentsScreen in jagacyVT.screens for these steps)
		 */
		// Step 35: Validate the contact information is being updated in the
		// efm.contact_notification database
		// NOTE: This step is not being performed because database validation cannot be
		// automated
	}


	/**
	 *Based on Ruslina's, this  test should not run as they expecting changes to come! Todd to follow up
	 *
	 *@author Quinci Cuthbert - created on 1/27/2022
	 */

	/*
	 * EFM CDA/SIGRQD -Verify when a PRO with specific shipper/3rd party account requires no delivery appointment
	 * and also no signature, but the FB contains HAZMAT, then CDA and SIGRQD codes should be added to the FB
	 */

	@Test(enabled = false, priority = 33)
	public void executeQZ_11581() throws JagacyException, Throwable {

        String shipperCode = "";
        String originTerm = "";
        String name = "myJagacyVT";
        String terminal = "dec-vt220";
        String userName = "QATSTFRTBL";
        String password = "qatest2019";
        String consigneeNum = "0039115";
        String poNum = testUtil.getTodayDate() + "11581";


		// Steps 1 - 12 are optional
        
      //Step 1: Select a Shipper account from the customer master table with the following query-
        //SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' 
        
		List<String> shipperData = null;
		if (sqlDatalist.validateValueFromDataBase(
				"SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' ") == true) {
			shipperData = sqlDatalist.sqlQueryResultsForShipperDetails(
					"SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' ",
					1);
		}

        shipperCode = shipperData.get(0);
        originTerm = shipperData.get(5);

        // Step 2: Or, use the following Data: Shipper: 7843170 OT: 078

        // Step 3: Go to EFM Reference Maintenance Website (QA url):
        // https://efm-maintenance-efm-qa.apps.ocpnonprod.estesinternal.com/login.html
        driver.get("https://efm-maintenance-efm-qa.apps.ocpnonprod.estesinternal.com");
        estesEfmMaintenceApp.login(username20, password20);

        // Step 4: If required UAT url:
        // https://efm-maintenance-efm-uat.apps.ocp.estesinternal.com/login.html

        // Step 5: From the Homepage, click on the 'Account Exclusions' tab
        estesEfmMaintenceApp.clickAccountExclusions();

        // Step 6,7 &8: Verify if the shipper account is not already on the list
		if (estesEfmMaintenceApp.isAccountDisplayed(shipperCode)) {
		
			boolean alreadyDisplayed = true;
			int i = 1;

			while (alreadyDisplayed) {

				if (sqlDatalist.validateValueFromDataBase(
						"SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS='S' and CMSTAT= 'A'") == true) {
					shipperData = sqlDatalist.sqlQueryResultsForShipperDetails(
							"SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS='S' and CMSTAT= 'A'",
							i);
				}

				if (!estesEfmMaintenceApp.isAccountDisplayed(shipperData.get(0))) {
					alreadyDisplayed = false;
					shipperCode = shipperData.get(0);
					originTerm = shipperData.get(5);
				} else {
					i++;
				}

			}

		}

        // Step 9: Click on the 'Signature Options' tab
        estesEfmMaintenceApp.clickSignatureOptions();

        // Step 10/11: Verify if the Shipper Account is not already on the List, if so,
        // delete it.
        estesEfmMaintenceApp.deleteAccNumIfExistsInTable(shipperCode);

        /*
        * Step 12: Add the Shipper/3rd Party Account w/ Following Options: *Signature
        * Option: NO Signature Required Signature Option Type: Always Appointment
        * Option: No Appointment Required
        */
        estesEfmMaintenceApp.enterSignatureOptionsAll(shipperCode, "No Signature Required", "Always",
                                      "No Appointment Required");


		// Step 13: From EXLAQA, Reserve and create a FB with the above shipper/3rd party account and OT.
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

		// Step 14: Reserve freight Bill (QZ-384)
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption("82", "TEST", originTerm);

		// Reserve freight bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(shipperCode);

		// Step 15: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 16: Create a Hazmat Freight Bill (QZ-8094)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Step 17: Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterCartTo("E029");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2, including UN Number (necessary for Hazmat FB)
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("CT");
		freightBillUpdateScreen2.enterDesc("CARTONS");
		freightBillUpdateScreen2.enterWgt2("1000");
		freightBillUpdateScreen2.enterOnlyUNNo("UN1818");
		
		jagacyUtil.pressEnter();

		// Step 18 - 20: From Guidelines for Hazardous Materials Inquiry screen, enter the following value against selected Hazard Class.
		GuidelinesForHazardousMaterialsInquiryScreen guidelinesForHazardousMaterialsInquiryScreen = new GuidelinesForHazardousMaterialsInquiryScreen(session);
		guidelinesForHazardousMaterialsInquiryScreen.selectUnCode("X");

		// Step 21 - 25: From the HAZMAT Skeleton Screen Builder screen, press F10 and then press Enter
		HazmatSkeletonScreen hazmatSkeletonScreen = new HazmatSkeletonScreen(session);
		jagacyUtil.pressF10();
		jagacyUtil.pressEnter();

		// Step 26 - 27: From next Update FB screen, enter the following value(s)
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstructions("NOHDP", "HAZ");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 28 - 29: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 30 - 31: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(300000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(30000);
		//Time Stamp here
		//TIME STAMP FOR FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " +timeStamp);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		// Enter option 1
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 32 - 33: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 021
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", "029");

		// Step 34: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 35: Verify the codes 'CDA’ and 'SIGRQD' are populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("CDA");
		jagacyUtil2.pressPageDown();
		freightBillDetailsScreen.checkForCode2("SIGRQD");

		// Step 36 - 37: Access the link below to view EDI 211 files. (outbound)
		// PATH - \\exlaqa\root\edi\xlator\outbound
		// Verify If the CDA and SIGRQD commodity codes were populated in the E211 file
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***CALL FOR DELIVERY APPOINTMENT");
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***SIGNATURE REQUIRED");
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "K1*HAZMAT*Hazardous Material");

	}

	/**
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * @author Justin
	 * Created: 01/28/2022
	 * 
	 * EFM CDA/SIGRQD - Verify when a PRO with specific shipper/3rd party account requires no delivery appointment and also no signature, 
	 * but the PRO has level of service above Curbside (INS/ASMBL), then CDA and SIGRQD codes should be added to the FB
	 */

	@Test(enabled = false, priority=34)
	public void executeQZ_11579() throws Exception {

		//Step 1: Select a Shipper account from the customer master table with the following query. 
		String shipperNum=""; 
		String originTerm = ""; 

		List<String> shipperData = null; 
		if (sqlDatalist.validateValueFromDataBase("SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' ") == true) {
			shipperData = sqlDatalist.sqlQueryResultsForShipperDetails("SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' ", 1); 
		}
		//Shipper AccountNum/ShipperName/OriginTerminal
		shipperNum = shipperData.get(0); 
		originTerm = shipperData.get(5); 

		//Step 2: Or, use the following Data: Shipper: 7843170 OT: 078

		//Step 3: Go to EFM Reference Maintenance Website (QA url): https://efm-maintenance-efm-qa.apps.ocpnonprod.estesinternal.com/login.html
		driver.get(efmUrl); 
		estesEfmMaintenceApp.login(username20, password20); 

		//Step 4: If required UAT url: https://efm-maintenance-efm-uat.apps.ocp.estesinternal.com/login.html

		//Step 5: From the Homepage, click on the 'Account Exclusions' tab
		estesEfmMaintenceApp.clickAccountExclusions();

		//Step 6,7 &8: Verify if the shipper account is not already on the list
		if(estesEfmMaintenceApp.isAccountDisplayed(shipperNum)) {
			boolean alreadyDisplayed = true; 
			int i = 1; 

			while(alreadyDisplayed) {

				if (sqlDatalist.validateValueFromDataBase("SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS='S' and CMSTAT= 'A'") == true) {
					shipperData = sqlDatalist.sqlQueryResultsForShipperDetails("SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS='S' and CMSTAT= 'A'", i); 
				}

				if(!estesEfmMaintenceApp.isAccountDisplayed(shipperData.get(0))) {
					alreadyDisplayed = false; 
					shipperNum = shipperData.get(0); 
					originTerm = shipperData.get(5);
				}else {
					i++; 
				}

			}

		}

		//Step 9: Click on the 'Signature Options' tab
		estesEfmMaintenceApp.clickSignatureOptions(); 

		//Step 10/11: Verify if the Shipper Account is not already on the List, if so, delete it. 
		estesEfmMaintenceApp.deleteAccNumIfExistsInTable(shipperNum); 

		/*Step 12: Add the Shipper/3rd Party Account w/ Following Options:
		 * *Signature Option: NO Signature Required
		 * Signature Option Type: Always
		 * Appointment Option: No Appointment Required
		 */
		estesEfmMaintenceApp.enterSignatureOptionsAll(shipperNum, "No Signature Required", "Always", "No Appointment Required"); 

		//Step 13: From EXLAQA, create/reserve FB w/ above shipper account

		//Step 14 Call <Reserve a Freight Bill>

		String option = "1";
		String reserveOption = "82";
		String user = "Test";
		String user1 = "QATEST"; 
		String originTerminal = originTerm; 
		String sCode = shipperNum; 
		String consZip = "77447";
		String option1 = "2";
		String pcs = "10"; 
		String wgt = "1000"; 


		// Jagacy
		SessionVt session = null;
		String userName = "devabni", password = "nithyadev";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		session.open();	
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		jagacyUtil.pressEnter();

		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		iBMMainMenuScreen.enterValueToComandLineField("CALL FBC870");

		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);
		testUtil.setHardWait(2000);
		//Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
		testUtil.setHardWait(5000);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve("1");
		reserveFreightBillScreen.enterShipperCode(sCode);

		//Step 15: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerminal+billNum;
		System.out.println("Freight Bill No " +fbNum);

		//Press F3 to exit
		jagacyUtil.pressF3(); 

		//Step 16/17: Call <Create Freight Bill> => Use below details to complete FB.
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs(pcs);
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt(wgt);
		freightBillUpdateScreen.enterCons("6592344");
		freightBillUpdateScreen.enterConsName("Kerry Smith   ");
		freightBillUpdateScreen.enterConsAddress("23430 BAUER HOCKLEY RD");
		freightBillUpdateScreen.enterConsCity("HOCKLEY");
		freightBillUpdateScreen.enterConsState("TX");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterPONum("123456");						
		freightBillUpdateScreen.enterPuDrNum("9999999","9");
		freightBillUpdateScreen.enterCubicFeet("11");
		freightBillUpdateScreen.enterCartTo("E156");
		jagacyUtil.pressEnter();

		jagacyUtil.pressEnter();

		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2(pcs);
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2(wgt);

		jagacyUtil.pressEnter();

		jagacyUtil.pressEnter();

		//Update Screen 3, add the Accessorial Code: HD
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstructions("HD", "INS");

		jagacyUtil.pressEnter();

		//Step 18: Press F1()
		jagacyUtil.pressF1();

		//Step 19: Press F7()
		jagacyUtil.pressF7(); 

		//Step 20: Run the following two commands (EFM Business rule job): *One at a time*
		//1.)Call FBPGMS/EFM00C110 - Press Enter
		//2.)Call FBPGMS/EFM00R111 - Press Enter
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session); 
		testUtil.setHardWait(2000);
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00C110");
		session.waitForChange(147000);
		commandEntryScreen.enterValueToComandLineField1("CALL FBPGMS/EFM00R111");
		session.waitForChange(147000); 

		//Step 21: Press F3()
		jagacyUtil.pressF3();

		//Step 22: From the 'Freight Billing Menu' screen, select Option: 1, User: QATEST, Terminal: 086
		freightBillingMenuScreen.enterFreightBillMenuOption(option, user1, originTerminal);

		//Step 23: Press Enter 
		//		jagacyUtil.pressEnter();

		//Step 24: Enter Pro Number captured earlier
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session); 
		freightBillInquiryEnterFieldValuesScreen.enterFreightBillNum(originTerminal, billNum); 
		testUtil.setHardWait(5000); 
		jagacyUtil.pressEnter();

		//Step 25: Verify if all the line items are added to the FB - Validate: Pcs, Code, PC, Desc, Wgt for all three items
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session); 
		freightBillDetailsScreen.verifyCodeNDescription("CDA", "CALL FOR DELIVERY APPOINTMENT***"); 
		freightBillDetailsScreen.verifyCodeNDescription("SIGRQD", "SIGNATURE REQUIRED"); 

		//Step 26: Access below link to view EDI 211 files
		//TIME STAMP FOR 1ST FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp1 = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR FIRST FILE: " +timeStamp1);

		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		/*Step 27: Verify if CDA and SIGRQD commodity codes were populated in the E211 Files
		GO to Folder on Network drive : \\exlaqa\root\edi\xlator\outbound
		Login with "QATSTFRTBL"/"QATEST2019"
		Select Folder with Today's Date
		Look for "E211" file with the containing the Freight Bill Num
		Assert " AT5***NO SIGNATURE REQUIRED" is not in the file
		 */

		Thread.sleep(5000);
		String [] commodityTotals = {"AT5***CALL FOR DELIVERY APPOINTMENT~", "AT5***SIGNATURE REQUIRED~"}; 
		testUtil.verifyFileContains("E211_EFMD_", fbNum, timeStamp1, commodityTotals); 


	}
	/**
	 *According to Ruslina this test will fail. Todd will follow up
	 *
	 *@author Quinci Cuthbert - created on 1/31/2022
	 *
	 *EFM CDA/SIGRQD -Verify when a PRO with specific shipper/3rd party account requires delivery appointment
	 * but no signature, then freight bill should be updated with the code CDA and SIGRQD
	 */


	@Test(enabled = false, priority = 35)
	public void executeQZ_11582() throws JagacyException, Throwable {
		
		
		String shipperCode = "";
        String originTerm = "";
        String name = "myJagacyVT";
        String terminal = "dec-vt220";
        String userName = "QATSTFRTBL";
        String password = "qatest2019";
        String consigneeNum = "0039115";
        String poNum = testUtil.getTodayDate() + "11582";

		 // NOTE: if this test fails due to CDA or NOSIGN code not present,
		// Check shipper info in EFM Reference maintenance website and change shipper settings to
		// *Signature Option: NO Signature Required
		// *Signature Option Type: Always
		// *Appointment Option: Appointment Required

		// Steps 1 - 14 can be completed manually only when necessary (see above NOTE)

        //Step 1: Select a Shipper account from the customer master table with the following query-
        //SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' 
        
        List<String> shipperData = null;
        if (sqlDatalist.validateValueFromDataBase(
                                      "SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' ") == true) {
                       shipperData = sqlDatalist.sqlQueryResultsForShipperDetails(
                                                     "SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS in ('R','S') and CMSTAT= 'A' ",
                                                     1);
        }

        shipperCode = shipperData.get(0);
        originTerm = shipperData.get(5);

        // Step 2: Or, use the following Data: Shipper: 7843170 OT: 078

        // Step 3: Go to EFM Reference Maintenance Website (QA url):
        // https://efm-maintenance-efm-qa.apps.ocpnonprod.estesinternal.com/login.html
        driver.get(efmUrl);
        estesEfmMaintenceApp.login(username20, password20);

        // Step 4: If required UAT url:
        // https://efm-maintenance-efm-uat.apps.ocp.estesinternal.com/login.html

        // Step 5: From the Homepage, click on the 'Account Exclusions' tab
        estesEfmMaintenceApp.clickAccountExclusions();

        // Step 6,7 &8: Verify if the shipper account is not already on the list
        if (estesEfmMaintenceApp.isAccountDisplayed(shipperCode)) {
                       boolean alreadyDisplayed = true;
                       int i = 1;

                       while (alreadyDisplayed) {

                                      if (sqlDatalist.validateValueFromDataBase(
                                                                    "SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS='S' and CMSTAT= 'A'") == true) {
                                                     shipperData = sqlDatalist.sqlQueryResultsForShipperDetails(
                                                                                   "SELECT cmcls, cmcust, cmname, cmzip, cmst, cmcity, cmadr1, cmtb, cmtid FROM FBFILES.RAP001 Where CMCLS='S' and CMSTAT= 'A'",
                                                                                   i);
                                      }

                                      if (!estesEfmMaintenceApp.isAccountDisplayed(shipperData.get(0))) {
                                                     alreadyDisplayed = false;
                                                     shipperCode = shipperData.get(0);
                                                     originTerm = shipperData.get(5);
                                      } else {
                                                     i++;
                                      }

                       }

        }

        // Step 9: Click on the 'Signature Options' tab
        estesEfmMaintenceApp.clickSignatureOptions();

        // Step 10/11: Verify if the Shipper Account is not already on the List, if so,
        // delete it.
        estesEfmMaintenceApp.deleteAccNumIfExistsInTable(shipperCode);

        /*
        * Step 12: Add the Shipper/3rd Party Account w/ Following Options: *Signature
        * Option: NO Signature Required Signature Option Type: Always Appointment
        * Option: No Appointment Required
        */
        estesEfmMaintenceApp.enterSignatureOptionsAll(shipperCode, "No Signature Required", "Always",
                                      "Appointment Required");


        
        
		testUtil.setHardWait(2000);
		// Step 15: Reserve Freight Bill (QZ-384)
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

		// Step 18 (out of order): Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 16: Create a Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Step 17: Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterCartTo("E042");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HDP");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 19: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 20 - 22: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(30000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(30000);
		//Time Stamp here
		//TIME STAMP FOR FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " +timeStamp);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		// Enter option 1
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 23 - 24: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 224
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", originTerm);

		// Step 25: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 26: Verify the codes 'CDA’ and 'NOSIGN' are populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("CDA");
		freightBillDetailsScreen.checkForCode("NOSIGN");

		// Step 27 - 28: Access the link below to view EDI 211 files. (outbound)
		// PATH - \\exlaqa\root\edi\xlator\outbound
		// Verify If the CDA and SIGRQD commodity codes were populated in the E211 file
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***NO SIGNATURE REQ'D_UNATTENDED");
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***CALL FOR DELIVERY APPOINTMENT");

	}

	/**
	 *
	 *@author Quinci Cuthbert - created on 2/3/2022
	 *
	 *(EFM CDA/SIGRQD- Verify when CDA is already on Freight Bill then SIGRQD code is not added to the FB after EFM
	 * Business rule process runs and there are no-account level overrides for Shipper/3rd party accounts regarding
	 * appointment and signature.
	 *
	 */


	@Test(enabled = true, priority = 36)
	public void executeQZ_11587() throws JagacyException, Throwable {

		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originTerm = "291";
		String destTerm = "005";
		String shipperCode = "B000397"; // NOTE: if this test fails check shipper info in EFM Reference maintenance website and make sure shipper is deleted from both "Account Exclusions" and "Signature Options"
		String consigneeNum = "0500777";
		String poNum = testUtil.getTodayDate() + "11587";

		// Steps 1 - 12 are to be completed manually only when necessary (see above note)

		// Step 13: Reserve Freight Bill (QZ-384)
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

		// Step 14: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 15: Create a Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Step 16: Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterDTValue(destTerm);
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterConsName("Edward Johnson");
		freightBillUpdateScreen.enterConsAddress("220 Ashland Dr");
		freightBillUpdateScreen.enterConsCity("Winchester");
		freightBillUpdateScreen.enterConsState("VA");
		freightBillUpdateScreen.enterConsZip("22603");
		freightBillUpdateScreen.enterCartTo("E005");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("CDA");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 17: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 18 - 20: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(30000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(30000);
		//Time Stamp here
		//TIME STAMP FOR FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " +timeStamp);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		// Enter option 1
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 21 - 22: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 291
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", originTerm);

		// Step 23: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 24: Verify 'CDA’ is populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("CDA");

		// Step 25 - 26: Access the link below to view EDI 211 files. (outbound)
		// PATH - \\exlaqa\root\edi\xlator\outbound
		// Verify If only the CDA code is populated in the E211 file, and SIGRQD is not populated.
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***CALL FOR DELIVERY APPOINTMENT");
		testUtil.readerOutboundFileAssertStringIsNotThere("E211", fbNum, timeStamp, "AT5***SIGNATURE REQUIRED~");

	}


	/**
	 *
	 *
	 *
	 * Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * @author Quinci Cuthbert - created on 2/3/2022
	 *
	 *   EFM CDA/SIGRQD -Verify when SIGRQD is already on FB then CDA code is
	 *   populated on FB after the EFM Business rules process runs and there
	 *   are no-account level overrides for Shipper/3rd party accounts
	 *   regarding appointment and signature.
	 *
	 */


	@Test(enabled = false, priority = 37)
	public void executeQZ_11586() throws JagacyException, Throwable {

		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String originTerm = "291";
		String destTerm = "005";
		String shipperCode = "B000397"; // NOTE: if this test fails check shipper info in EFM Reference maintenance website and make sure shipper is deleted from both "Account Exclusions" and "Signature Options"
		String consigneeNum = "0500777";
		String poNum = testUtil.getTodayDate() + "11586";

		// Steps 1 - 12 are to be completed manually only when necessary (see above note)

		// Step 13: Reserve Freight Bill (QZ-384)
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

		// Step 14: Capture the Pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();
		String fbNum = originTerm + billNum;
		System.out.println("Freight Bill No " + fbNum);
		jagacyUtil.pressF3();

		// Step 15: Create a Freight Bill (QZ-387)
		freightBillingMenuScreen.enterFreightBillMenuOption("2", "TEST", originTerm);
		freightBillingMenuScreen.enterFreightBill(originTerm, billNum);

		// Step 16: Enter values provided for Update Screen 1
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterDTValue(destTerm);
		freightBillUpdateScreen.enterCons(consigneeNum);
		freightBillUpdateScreen.enterConsName("Edward Johnson");
		freightBillUpdateScreen.enterConsAddress("220 Ashland Dr");
		freightBillUpdateScreen.enterConsCity("Winchester");
		freightBillUpdateScreen.enterConsState("VA");
		freightBillUpdateScreen.enterConsZip("22603");
		freightBillUpdateScreen.enterCartTo("E005");
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 2
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Enter values provided for Update Screen 3
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessorialCode("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("SIGRQD");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Step 17: Press F1 and return to Freight Billing Menu
		jagacyUtil.pressF1();
		Thread.sleep(2000);
		jagacyUtil.pressF1();
		if (session != null) {
			session.abort();
			session.close();
		}

		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon("PEACHDO", "AUG08Y14");

		// Get past Intermediate screen if exists
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();

		// Step 18 - 20: Run the following two commands (EFM Business rule job): One at a time
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session2);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(30000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");
		Thread.sleep(30000);
		//Time Stamp here
		//TIME STAMP FOR FILE CREATED -- FORMAT YEAR(ex 21) MONTH(ex 12) DAY(ex 16) HOUR(ex 12) MIN(ex 34) --> 2112161234
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		String tmString = tm.toString();
		String timeStamp = tmString.substring(2,16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		System.out.println("TIME STAMP FOR SECOND FILE: " +timeStamp);

		commandEntryScreen.enterCommand("Call XXC870");
		Thread.sleep(2000);

		// Enter option 1
		Ltl38MasterMenuScreen ltl38mastermenu2 = new Ltl38MasterMenuScreen(session2);
		ltl38mastermenu2.enterValueOptionField1("1");
		jagacyUtil2.pressEnter();

		// Step 21 - 22: From the freight billing menu, Select Option 1,
		// User: QATEST, Terminal: 291
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen2 = new jagacyVT.screens.FreightBillingMenuScreen(session2);
		freightBillingMenuScreen2.enterFreightBillMenuOption("1", "QATEST", originTerm);

		// Step 23: Enter the Pro number captured earlier, Press Enter
		FreightBillInquiryEnterFieldValuesScreen freightBillInquiryEnterFieldValuesScreen = new FreightBillInquiryEnterFieldValuesScreen(session2);
		freightBillInquiryEnterFieldValuesScreen.enterValueFreightBill(fbNum);
		jagacyUtil2.pressEnter();

		// Step 24: Verify 'CDA’ and 'SIGRQD' are populated on the FB
		FreightBillDetailsScreen freightBillDetailsScreen = new FreightBillDetailsScreen(session2);
		Thread.sleep(2000);
		freightBillDetailsScreen.checkForCode("CDA");
		freightBillDetailsScreen.checkForCode("SIGRQD");

		// Step 25 - 26: Access the link below to view EDI 211 files. (outbound)
		// PATH - \\exlaqa\root\edi\xlator\outbound
		// Verify If the CDA and SIGRQD commodity codes were populated in the E211 file
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***CALL FOR DELIVERY APPOINTMENT");
		testUtil.readerOutboundFileAssertStringIsThereAndL11ProNum("E211", fbNum, timeStamp, "AT5***SIGNATURE REQUIRED~");

	}


	/**
	 * 
	 * 
	 *  Rusalina suggests that we disable this test. Once everything is working correctly, we can turn it back on.
	 * 
	 * @author coxda
	 * @throws Exception
	 * 
	 * EFM 990 file processing - Verify 990 file is processed by the ESB and comments are added to freight bill 
	 */

	@Test(enabled=false,priority=38)

	public void qz9402_verify990FileIsProcessedByTheESBAndCommentsAreAddedToFreightBill() throws Exception {
		// Steps 1 - 2: Create freight bill
		FreightBill freightBill = new FreightBill();
		freightBill.setCartTo("E087");
		String otProNumber = freightBill.reserveAndCreateFreightBill();

		/* ACCEPT TEST */

		// Steps 3 - 4: Process the 990 accept file
		testUtil.placeThe990AcceptTestFileOnTheESBFolderForProcessing(otProNumber);

		// Step 5: Verify the 990 accept file finished processing
		testUtil.verifyTheProcessed990FileIsDisplayed(otProNumber, "accept");

		// Step 6: Ensure that the file is processed in the database

		String st02Segment = "497800002";
		String query = "SELECT * FROM FBFILES.CCI10P990H WHERE HDRTRNSCN = '" + st02Segment + "'";
		List<List<String>> records = sqlDatalist.viewQueryResults(query);
		String hdrprocflg = records.get(records.size() - 1).get(9);
		logger.info("[" + hdrprocflg + "]");

		// Step 7: Verify a new record is added in the database based on the timestamp

		// Wait to process:
		testUtil.setHardWait(30000);

		String ot = otProNumber.substring(0, 3); // 2nd column
		String pro = otProNumber.substring(3); // 3rd column
		String query2 = "SELECT * FROM FBFILES.CCI10P990D WHERE DTLOT='" + ot + "' AND DTLPRO='" + pro + "'";
		List<List<String>> records2 = sqlDatalist.viewQueryResults(query2);
		assertTrue(records2.size() == 1, "There was not exactly one corresponding new record.");
		String dtlcrttmsp = sqlDatalist.viewQueryResults(query2).get(0).get(14);
		String formattedDate = dtlcrttmsp.substring(5, 7) + dtlcrttmsp.substring(8, 10) + dtlcrttmsp.substring(2, 4);
		assertTrue(formattedDate.equals(testUtil.getTodayDate()),
				"Date of database entry does not match today's date.");

		// Step 8: Process the records with the command
		TempletTests templateTest = new TempletTests();
		templateTest.callCommand("CALL CCI990C100", username16, password16);

		// Step 9: Check SCAC
		sqlDatalist.viewQueryResults("SELECT * FROM FBFILES.FRP002 WHERE FDOT='" + ot + "' and FDPRO='" + pro + "'");
		sqlDatalist.viewQueryResults("SELECT FHA5 FROM FBFILES.FRP001 WHERE FHOT='" + ot + "' AND FHPRO='" + pro + "'");
		logger.info("Verify SCAC: [" + records2.get(0).get(10) + "]");

		// Step 10 - 15: Make a Freight Bill Inquiry and confirm the EFM text displays
		freightBill.verifyFreightBillHasDNPCodeWithEFMDescription(otProNumber);

		/* DECLINE TEST */
		// Steps 3 - 4: Process the 990 decline file
		testUtil.placeThe990DeclineTestFileOnTheESBFolderForProcessing(otProNumber);

		// Step 5: Verify the 990 decline file finished processing
		testUtil.verifyTheProcessed990FileIsDisplayed(otProNumber, "decline");

		// Step 6: Ensure that the file is processed in the database

		String stSegment = "497800002";
		String query3 = "SELECT * FROM FBFILES.CCI10P990H WHERE HDRTRNSCN = '" + stSegment + "'";
		List<List<String>> records3 = sqlDatalist.viewQueryResults(query3);
		String hdrprocflg2 = records3.get(records3.size() - 1).get(9);
		logger.info("[" + hdrprocflg2 + "]");

		// Step 7: Verify a new record is added in the database based on the timestamp
		testUtil.setHardWait(30000);
		String query4 = "SELECT * FROM FBFILES.CCI10P990D WHERE DTLOT='" + ot + "' AND DTLPRO='" + pro + "' ORDER BY DTLCRTTMSP DESC";
		List<List<String>> records4 = sqlDatalist.viewQueryResults(query4);

		String dtlcrttmsp2 = records4.get(0).get(14);
		String formattedDate2 = dtlcrttmsp2.substring(5, 7) + dtlcrttmsp2.substring(8, 10)
		+ dtlcrttmsp2.substring(2, 4);
		assertTrue(formattedDate2.equals(testUtil.getTodayDate()),
				"Date of database entry does not match today's date.");

		templateTest.callCommand("CALL CCI990C100", username16, password16);
		List<List<String>> records5 = sqlDatalist.viewQueryResults(query4);

		sqlDatalist.viewQueryResults("SELECT FHA5 FROM FBFILES.FRP001 WHERE FHOT='" + ot + "' AND FHPRO='" + pro + "'");
	}



	/**
	 * @author Jeff Ying 1/26/22
	 * 
	 */
	@Test(enabled=true,priority=39)
	public void executeQZ_9366() throws Exception{

		// 1: RUN SQL QUERY
		String otx07 = " ";
		String ddc = "DDC";
		String bv = "BILLING VENDORS";
		String query2 = "SELECT DISTINCT CASE WHEN OTX07 = '" + otx07 + "' THEN '" + ddc + "' ELSE OTX07 END AS \"" + bv
				+ "\" FROM FBFILES.DDP000";

		// 2: Billing Vendors are captured in the list below. Either DDC or WNS
		List<List<String>> records2 = sqlDatalist.viewQueryResults(query2);

		// 3: Access FTP server: ftp://ddcftptest.us.dom:21
		FTPClient client = new FTPClient();
		client.connect("ddcftptest.us.dom");
		client.enterLocalPassiveMode();
		client.login("DDCQA", "gSCu3DuP");

		// 4 - 5: Go into /CommonFiles/<todaysDate>
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String date = f.format(new Date());
		FTPFile[] files = client.listFiles("/CommonFiles/" + date);

		// 6: Verify files have the correct naming conventions:
		// CustomerMasterData_yyyyMMdd_hhmm.csv
		// FreightBillEditsFile_yyyyMMdd_hhmm.csv
		// MultipleID_yyyyMMdd_hhmm.csv
		// SkeletonFile_yyyyMMdd_hhmm.csv
		for (FTPFile file : files) {
			String[] startNameAndDate = file.getName().toString().split("_");
			System.out.println(Arrays.toString(startNameAndDate));
			// Check for starting name
			if (startNameAndDate[0].equals("CustomerMasterData") || startNameAndDate[0].equals("FreightBillEditsFile")
					|| startNameAndDate[0].equals("MultipleID") || startNameAndDate[0].equals("SkeletonFile")) {
				System.out.println("Starting name is correct");
			} else {
				Assert.fail("Starting name is incorrect");
			}
			// Check for date
			if (!startNameAndDate[1].equals(date)) {
				Assert.fail("Date is incorrect");
			} else {
				System.out.println("Date is correct");
			}
			// Check for Time and File Extension
			String[] timeAndExtention = startNameAndDate[2].split("\\.");
			System.out.println(Arrays.toString(timeAndExtention));
			if (timeAndExtention[0].length() == 4 && Integer.parseInt(timeAndExtention[0]) <= 2400) {
				System.out.println("Time format is correct");
			} else {
				Assert.fail("Time format is incorrect");
			}
			// Check for file extension
			if (timeAndExtention[1].equals("csv")) {
				System.out.println("File extension is correct");
			} else {
				Assert.fail("File extension is incorrect");
			}
		}

		// 7 - 11 : Go in directory /<Billing Vendor 1> (WNS) /CommonFiles/<todaysDate>/
		String WNS = records2.get(0).get(0).trim();
		files = client.listFiles("/" + WNS + "/CommonFiles/" + date);

		// 12: Repeat step 6 and validate all naming conventions for the files in folder
		for (FTPFile file : files) {
			String[] startNameAndDate = file.getName().toString().split("_");
			System.out.println(Arrays.toString(startNameAndDate));
			// Check for starting name
			if (startNameAndDate[0].equals("CustomerMasterData") || startNameAndDate[0].equals("FreightBillEditsFile")
					|| startNameAndDate[0].equals("MultipleID") || startNameAndDate[0].equals("SkeletonFile")) {
				System.out.println("Starting name is correct");
			} else {
				Assert.fail("Starting name is incorrect");
			}
			// Check for date
			if (!startNameAndDate[1].equals(date)) {
				Assert.fail("Date is incorrect");
			} else {
				System.out.println("Date is correct");
			}
			// Check for Time and File Extension
			String[] timeAndExtention = startNameAndDate[2].split("\\.");
			System.out.println(Arrays.toString(timeAndExtention));
			if (timeAndExtention[0].length() == 4 && Integer.parseInt(timeAndExtention[0]) <= 2400) {
				System.out.println("Time format is correct");
			} else {
				Assert.fail("Time format is incorrect");
			}
			// Check for file extension
			if (timeAndExtention[1].equals("csv")) {
				System.out.println("File extension is correct");
			} else {
				Assert.fail("File extension is incorrect");
			}
		}

		// 13: Go in directory /<Billing Vendor 2> (DDC) /CommonFiles/<todaysDate>/
		// Repeat step 6 and validate all naming conventions for the files in folder
		String DDC = records2.get(1).get(0).trim();
		files = client.listFiles("/" + DDC + "/CommonFiles/" + date);

		for (FTPFile file : files) {
			String[] startNameAndDate = file.getName().toString().split("_");
			System.out.println(Arrays.toString(startNameAndDate));
			// Check for starting name
			if (startNameAndDate[0].equals("CustomerMasterData") || startNameAndDate[0].equals("FreightBillEditsFile")
					|| startNameAndDate[0].equals("MultipleID") || startNameAndDate[0].equals("SkeletonFile")) {
				System.out.println("Starting name is correct");
			} else {
				Assert.fail("Starting name is incorrect");
			}
			// Check for date
			if (!startNameAndDate[1].equals(date)) {
				Assert.fail("Date is incorrect");
			} else {
				System.out.println("Date is correct");
			}
			// Check for Time and File Extension
			String[] timeAndExtention = startNameAndDate[2].split("\\.");
			System.out.println(Arrays.toString(timeAndExtention));
			if (timeAndExtention[0].length() == 4 && Integer.parseInt(timeAndExtention[0]) <= 2400) {
				System.out.println("Time format is correct");
			} else {
				Assert.fail("Time format is incorrect");
			}
			// Check for file extension
			if (timeAndExtention[1].equals("csv")) {
				System.out.println("File extension is correct");
			} else {
				Assert.fail("File extension is incorrect");
			}
		}

	}

}



