
package myEstesTests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.dev.RecordLister;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import billOfLadingPages.MyEstesBillOfLadingPage;
import claimsPages.FileAClaimPage;
import imageRetrievalPages.MyEstesImageRetrievalPage;
import jagacyVT.screens.DisplayCommentsScreen;
import jagacyVT.screens.DisplayRequestServiceInfoScreen;
import jagacyVT.screens.GoldMedalMainMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.SubsetRequestServiceInfoScreen;
import jagacyVT.screens.WorkWithRequestScreen;
import myEstesPages.MyEstesAddCommodityPage;
import myEstesPages.MyEstesAddNewAddressPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesCommodityLibraryPage;
import myEstesPages.MyEstesCustomizeQuickLinkPage;
import myEstesPages.MyEstesDigitalServicesPage;
import myEstesPages.MyEstesEDIFormTransmissionRequestPage;
import myEstesPages.MyEstesEDIPage;
import myEstesPages.MyEstesEditAddressPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesPCRaterDownloadPage;
import myEstesPages.MyEstesPickupRequestPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import myEstesPages.MyEstesRecoverLostUserNamePasswordPage;
import myEstesPages.MyEstesRequestAccountNumberPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesShipmentTrackingPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesTerminalLookupPage;
import myEstesPages.MyEstesToolsPage;
import myEstesPages.MyEstesUpdateProfileInformationPage;
import myEstesPages.MyEstesUploadAddressBookPage;
import myEstesPages.MyEstesUserProfilePage;
import myEstesPages.MyEstesWelcomePage;
import pcRaterDownloadPages.PCRaterDownloadPage;
import rateQuotePages.RateQouteRateEstimatePage;
import rateQuotePages.RateQuotePage;
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;
import transitTimeCalculatorPages.TransitTimeCalculatorPage;
import util.CreateFreightBill;
import util.JDBCFirstRow;
import util.ReserveAFreightBill;
import util.SQLDataList;
import util.TestListener;
import util.TestUtil;

public class MyEstesRegressionTest extends TestBase {
	Logger logger = Logger.getLogger(MyEstesRegressionTest.class);

	TestListener testListener;
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
	MyEstesRequestAccountNumberPage myEstesRequestAccountNumberPage = new MyEstesRequestAccountNumberPage();
	MyEstesRecoverLostUserNamePasswordPage myEstesRecoverLostUserNamePasswordPage = new MyEstesRecoverLostUserNamePasswordPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesCustomizeQuickLinkPage myEstesCustomizeQuickLinkPage = new MyEstesCustomizeQuickLinkPage();
	MyEstesUploadAddressBookPage myEstesUploadAddressBookPage = new MyEstesUploadAddressBookPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();
	RateQuotePage ltlRateQuotePage = new RateQuotePage();
	MyEstesImageRetrievalPage myEstesImageRetrievalPage = new MyEstesImageRetrievalPage();
	TransitTimeCalculatorPage transitTimeCalculatorPage = new TransitTimeCalculatorPage();

	CreateFreightBill createFreightBill = new CreateFreightBill();
	ReserveAFreightBill reserveAFreightBill = new ReserveAFreightBill();

	MyEstesCommodityLibraryPage myEstesCommodityLibraryPage = new MyEstesCommodityLibraryPage();
	MyEstesAddCommodityPage myEstesAddCommodityPage = new MyEstesAddCommodityPage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	MyEstesBillOfLadingPage myestesBillOfLadingPage = new MyEstesBillOfLadingPage();
	MyEstesPickupRequestPage myestesPickupRequestPage = new MyEstesPickupRequestPage();

	MyEstesDigitalServicesPage myestesDigitalServicePage = new MyEstesDigitalServicesPage();
	MyEstesEDIPage myestesEDIpage = new MyEstesEDIPage();
	MyEstesEDIFormTransmissionRequestPage myestesEDIformTransmissionPage = new MyEstesEDIFormTransmissionRequestPage();
	MyEstesToolsPage myestesToolPage = new MyEstesToolsPage();

	FileAClaimPage fileAClaimPage = new FileAClaimPage();
	PCRaterDownloadPage pcRaterDownloadPage = new PCRaterDownloadPage();
	RateQouteRateEstimatePage rateEstimatePage = new RateQouteRateEstimatePage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	SQLDataList sqlDataList = new  SQLDataList();
	
	MyEstesUpdateProfileInformationPage myEstesUpdateProfileInformationPage = new MyEstesUpdateProfileInformationPage();

	/****************************** TESTS ******************************/

	/*
	 * MyEstes Signup - Verify Error message is displayed when all required fields
	 * are left blank.(TC-DP2-419)
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_3291() {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnCreateAccountLink();
		myEstesSignUpPage.clickYesAndHaveMyAccountNumberRadioButton();
		myEstesSignUpPage.clickOnNextButton();
		myEstesSignUpPage.clickOnSubmitButton();
		testUtil.setHardWait(3000);
		myEstesSignUpPage.validateErrorMessage();

	}

	/*
	 * MyEstes Signup - Verify the Error message is received when entered mismatch
	 * passwords.(TC-DP2-402)
	 */

	/*
	 * @Test(enabled = true, priority = 3) //DUPLICATE
	 * 
	 * public void qz3311_verifyErrorMessageIsReceivedWhenEnteredMismatchPassword()
	 * {
	 * 
	 * myEstesHomePage.clickOnMyEstes(); myEstesHomePage.clickOnCreateAccountLink();
	 * myEstesSignUpPage.clickYesAndHaveMyAccountNumberRadioButton();
	 * myEstesSignUpPage.clickOnNextButton();
	 * 
	 * myEstesSignUpPage.enterCompanyAccountNumber();
	 * myEstesSignUpPage.enterCompanyName(); myEstesSignUpPage.enterFirstName();
	 * myEstesSignUpPage.enterLastName(); myEstesSignUpPage.enterEmailAddress();
	 * myEstesSignUpPage.enterPhoneNumber();
	 * myEstesSignUpPage.enterUserName("HelloEstes");
	 * myEstesSignUpPage.enterPassword("somokelocal");
	 * myEstesSignUpPage.enterConfirmPassword("12345");
	 * myEstesSignUpPage.clickOnSubmitButton();
	 * 
	 * myEstesSignUpPage.verifyMissmatchPasswordMessage();
	 * 
	 * }
	 */

	// TC-DP2-843
	/*
	 * Edit My Estes Profile - Change my e-mail address - Verify Error message
	 * displays when required fields are left blank.
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_3246()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		myEstesUserProfilePage.clickOnChangeMyEmailAddressLink();
		myEstesUserProfilePage.clickonSubmitButton();

		myEstesUserProfilePage.validateErrorMessage();
		myEstesUserProfilePage.enterUserName(username1);
		myEstesUserProfilePage.enterPassword(password1);

		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();
		myEstesUserProfilePage.clickonSubmitButtonForChangeEmailAddress();
		myEstesUserProfilePage.validateNewEmailErrorMessage();

	}
	/**
	 * 
	 * @throws InterruptedException
	 */
	// THIS SCRIPT FOR CHANCHING USERNAME(SMOKELOCA) NEEDS TO BE TURNED OFF
	// get with kathy to get another user and pass for this testcases- and turn it
	// back on
	/*
	 * Passed on 11 Aug 2022
	 * Edit My Estes Profile - Verify the user can change username
	 */
	@Test(enabled = true, priority = 3)

	public void executeQZ_3267() throws InterruptedException {
		String newUsername = "qa1test";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		testUtil.setHardWait(1000);
		myEstesUserProfilePage.clickOnChangeMyUseNameLink();

		myEstesUserProfilePage.enterUserName(username2);
		myEstesUserProfilePage.enterPassword(password2);
		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();

		myEstesUserProfilePage.verifyChangeYourUserPopupDisplayed();

		myEstesUserProfilePage.enterNewUserName(newUsername);
		myEstesUserProfilePage.clickonSubmitButtonForChangeUserName();
//		testUtil.setHardWait(40000);
		myEstesLoginPage.enterUserName(newUsername);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

		myEstesUserProfilePage.verifyProfilePage();
		myEstesUserProfilePage.clickOnChangeMyUseNameLink();
		myEstesUserProfilePage.enterUserName(newUsername);
		myEstesUserProfilePage.enterPassword(password2);
		testUtil.setHardWait(1000);
		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();

		myEstesUserProfilePage.verifyChangeYourUserPopupDisplayed();

		myEstesUserProfilePage.enterNewUserName(username2);
		myEstesUserProfilePage.clickonSubmitButtonForChangeUserName();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		myEstesUserProfilePage.validateUserName(username2);

	}


	/*
	 * @Test(enabled = true) // LABLE:DONOT AUTOMATED ME IN JIRA
	 * 
	 * public void
	 * qz7683_verifyUseCanCustomaizeQuikLinksByclickingOnCustomizeButton() throws
	 * Exception {
	 * 
	 * myEstesHomePage.clickOnMyEstes(); myEstesHomePage.clickOnLogin();
	 * myEstesLoginPage.enterUserName(username1);
	 * myEstesLoginPage.enterPassword(password1);
	 * myEstesLoginPage.clickOnLoginButton();
	 * myEstesWelcomePage.clickOnCustomizeLink();
	 * myEstesCustomizeQuickLinkPage.validateTitle(); String name =
	 * myEstesCustomizeQuickLinkPage.clickOnRemoveButton();
	 * myEstesCustomizeQuickLinkPage.validateQuickLinkIsRemoved(name); String aName
	 * = myEstesCustomizeQuickLinkPage.clickOnAddButton();
	 * myEstesCustomizeQuickLinkPage.validateAddToQuickLink(aName);
	 * myEstesCustomizeQuickLinkPage.clickOnReturnToWelcomePageButton();
	 * myEstesWelcomePage.validatequickLinkList();
	 */

	

	


	// THIS CLICKS ON SHIPPER,CONSIGNEE AND THIRDPARTY BUT IT FAILS. NEEDS TO BE
	// UPDATED
	// DP2-578
	/*
	 * Welcome Page - LOCAL Account - Verify the user can select shipment party
	 */

	@Test(enabled = true, priority = 4)

	public void executeQZ_7147() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Shipper
		myEstesWelcomePage.verifyWelcomePage();

		// Recent Shipment - Shipper
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();
		testUtil.setHardWait(1000);
		//updated the "replace method
		String expectedShipperPro = myEstesRecentShipmentsPage.getPRONumForFirstRow().replace("-","");

		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();

		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();

		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		testUtil.setHardWait(2000);
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedShipperPro);
		driver.navigate().back();
		testUtil.setHardWait(2000);
		// Recent Shipment - Consignee
		myEstesRecentShipmentsPage.ClickOnRSConsigneeRadioButton();
		//updated the "replace method
		String expectedConsigneePro = myEstesRecentShipmentsPage.getPRONumForFirstRow().replace("-","");
		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();
		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedConsigneePro);
		driver.navigate().back();
		testUtil.setHardWait(3000);

		// Recent Shipment - Third Party Payor
		myEstesRecentShipmentsPage.ClickOnRSThirdPartyPayorRadioButton();
		String expectedPayorPro = myEstesRecentShipmentsPage.getPRONumForFirstRow().replace("-","");

		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();

		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();

		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedPayorPro);
	}

	/*
	 * Welcome Page - LOCAL Account - Verify user is able to customize Preferences
	 * from Welcome page.
	 */

	@Test(enabled = true, priority = 5)

	public void executeQZ_3274() throws InterruptedException {

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnLoginFromDDown();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUnameAndpwd("smokelocal", "smokelocal");
		myEstesLoginPage.clickOnLoginButton();

		myEstesRecentShipmentsPage.clickOnSetViewingPreference();
		myEstesRecentShipmentsPage.clickOnPopUpConsigneeRadioButton();
		myEstesHomePage.clickOnConfirmButton();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();

		myEstesHomePage.clickOnConfirmButton();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUnameAndpwd("smokelocal", "smokelocal");
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesRecentShipmentsPage.verifyRSConsigneeRadioButtonDisplayed();
	}

	
	/**
	 * 
	 * Test passed on 7/25/22
	 * 
	 * Welcome Page - Verify the National Account user can get shipment information
	 * when account number is entered into Account Lookup field
	 */

	@Test(enabled = true, priority = 6) 

	public void executeQZ_7722()
			throws InterruptedException, HeadlessException, ClassNotFoundException, AWTException,
			UnsupportedFlavorException, IOException {

		// Step-1 : Open MyEstes

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step-2 : login in with smokenat credentials
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Verify welcome page
		myEstesWelcomePage.verifyWelcomePage();

		// Step-3 : Click on the account number
		
		myEstesWelcomePage.accountSearchBarInput("0500845");
		//Step 4: click on Submit button
		
		myEstesWelcomePage.clickOnSearchAccountSubmit();
		testUtil.setHardWait(2000);
		//validate user navigates to "Recent Shipment" page
		myEstesRecentShipmentsPage.verifyRecentShipmentsTextDisplayed();
		// Step 5: From Recent Shipment page, click on Any available <PRO Number>
		
		String Expected_proNumber = myEstesRecentShipmentsPage.getPRONumForFirstRow().replace("-", "");
		System.out.println("PRO Number: " + Expected_proNumber);
		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();

		//  Validate user is navigated to shipment tracking page
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();

		// Step-5 : Validate shipment information under tracking result
		String Actual_proNumber = myEstesShipmentTrackingPage.verifyPRONum();
		assertEquals(Actual_proNumber, Expected_proNumber);

	}

	// dp2-1518- Commodity Library - Verify the user can cancel Adding a Commodity
	// by clicking on Cancel button within the modal

	@Test(enabled = true, priority = 7)

	public void executeQZ_7254()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();
		myEstesCommodityLibraryPage.clickOnAddCommodity();
		String prID = myEstesAddCommodityPage.enterProductID();
		myEstesAddCommodityPage.typeDescription("test for cancle adding commodity library");
		myEstesAddCommodityPage.clickOnCancleButton();
		myEstesCommodityLibraryPage.validateRecordAddedtoCommodityLibraray(prID);
	}

	// Accordingn to Dee, this test needs to turn off and kept for future just in case
	
	/*@Test(enabled = false, priority = 8)

	public void qz7687_verifyrrorMessageDisplaysWhenRequiredFieldsAreLeftBlank() throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();
		shipmentTrackingPage.selectPRONumber();
		shipmentTrackingPage.enterPORNumber(testUtil.getProNumberFromDB());
		shipmentTrackingPage.clickOnSearchButton();
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.clickOnRequestAddInfo();
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(2000);
		shipmentTrackingPage.validateErrorMessageForReqFields();

	}*/

	// According to Dee, this test needs to turned off and kept for future just in case
	/*@Test(enabled = false, priority = 9)

	public void qz7688_verifyUserCanSubmitRequestAdditionalInformationForm() throws Exception {

		String proNumber = testUtil.getProNumberFromDB(), name = "Test", email = "smith@estes.com",
				phone = "8885637224", comments = "Test Info",
				expectedMessage = "Your message has been sent. A customer care representative will respond as soon as possible.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnTrack();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnShipmentTraking();
		testUtil.setHardWait(1000);
		shipmentTrackingPage.selectPRONumber();
		shipmentTrackingPage.enterPORNumber(proNumber);
		shipmentTrackingPage.clickOnSearchButton();

		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.clickOnRequestAddInfo();
		shipmentTrackingPage.enterName(name);
		shipmentTrackingPage.enterEmail(email);
		shipmentTrackingPage.enterPhoneNumber(phone);
		shipmentTrackingPage.selectTrackingHelp();
		shipmentTrackingPage.enterComments(comments);
		shipmentTrackingPage.clickSubmit();

		shipmentTrackingPage.verifySuccessMessage(expectedMessage);

		driver.navigate().back();
		shipmentTrackingPage.enterPORNumber(proNumber);
		shipmentTrackingPage.clickOnSearchButton();
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.clickOnRequestAddInfo();
		testUtil.setHardWait(1000);
		shipmentTrackingPage.enterName(name);
		shipmentTrackingPage.enterEmail(email);
		shipmentTrackingPage.enterPhoneNumber(phone);
		shipmentTrackingPage.selectOther();
		shipmentTrackingPage.enterComments(comments);
		shipmentTrackingPage.clickSubmit();

		shipmentTrackingPage.verifySuccessMessage(expectedMessage);
	}*/

	/*
	 * Edit My Estes Profile - Change my password - Verify Error message displays
	 * when required fields are left blank
	 */

	@Test(enabled = true, priority = 8)

	public void executeQZ_7645() throws InterruptedException {

		String expectedMessage = "This field is required.", confirmPwdMessage = "Passwords do not match.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		testUtil.setHardWait(1000);
		myEstesUserProfilePage.clickOnChangeMyPasswordLink();
		myEstesUserProfilePage.clearUserName();
		myEstesUserProfilePage.clearPassword();
		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();
		myEstesUserProfilePage.verifyUsernameErrorMessage(expectedMessage);
		myEstesUserProfilePage.verifyPasswordErrorMessage(expectedMessage);

		myEstesUserProfilePage.enterUserName(username1);
		myEstesUserProfilePage.enterPassword(password1);
		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();

		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();
		myEstesUserProfilePage.verifyChangeYourPasswordPopupDisplayed();
		myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();
		testUtil.setHardWait(2000);

		myEstesUserProfilePage.verifyPasswordErrorMessage(expectedMessage);
		myEstesUserProfilePage.verifyConfirmPasswordErrorMessage(confirmPwdMessage);
	}

	/*
	 * Edit My Estes Profile - Admin account - Verify the user can View or Edit User
	 */

	@Test(enabled = true, priority = 9)

	public void executeQZ_7646() throws InterruptedException {

		String message = "Success! Given apps have been added to blocked apps successfully";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username10);
		myEstesLoginPage.enterPassword(password10);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		myEstesUserProfilePage.clickOnViewEditUserLink();

		myEstesUserProfilePage.verifyAvailableUserName();

		myEstesUserProfilePage.clickUsername();
		myEstesUserProfilePage.clickBlock();

		myEstesUserProfilePage.verifySuccessMessage(message);
		testUtil.setHardWait(2000);
		myEstesUserProfilePage.clickOnUnblock();
		// myEstesUserProfilePage.clickBlock();

	}

	/*
	 * Passed on 11 Aug 2022
	 * Edit My Estes Profile - Admin account - Verify the user can View or Edit
	 * User_Disable User.
	 */

	@Test(enabled = true, priority = 10)

	public void executeQZ_7648() throws InterruptedException {

		String userName = "BATMAN4";
		// Login to My Estes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username10);
		myEstesLoginPage.enterPassword(password10);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		myEstesUserProfilePage.clickOnViewEditUserLink();

		myEstesUserProfilePage.enterUserName(userName);
		myEstesUserProfilePage.clickonSubmitButton();
		myEstesUserProfilePage.verifyAvailableUserName();
		myEstesUserProfilePage.verifyAndClickOnEnableButton();
		myEstesUserProfilePage.clickOnDisableButton();
		myEstesUserProfilePage.validateLinkTurnsEnable();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();

		myEstesLoginPage.enterUserName(userName);
		myEstesLoginPage.enterPassword(userName);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();
		testUtil.setHardWait(2000);
		myEstesLoginPage.verifyLoginErrorMessage();

	}

	/**
	 * Test passed on 6/28/22
	 * 
	 * Edit My Estes Profile - Verify the user can change e-mail address
	 */

	@Test(enabled = true, priority = 11)

	public void executeQZ_7652() throws InterruptedException {

		// login to myestes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(4000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		// click on edit profile
		myEstesLoginPage.clickOnEditMyProfile();
		// click on change my email link
		myEstesUserProfilePage.clickOnChangeMyEmailAddressLink();
		// enter the credentials you have logged in with
		myEstesUserProfilePage.enterUserName(username1);
		myEstesUserProfilePage.enterPassword(password1);
		myEstesUserProfilePage.clickonSubmitButton();
		testUtil.setHardWait(1000);
		// enter a email id
		myEstesUserProfilePage.enterEmail("testemail@estes-express.com");
		// click on submit button new email pop up
		myEstesUserProfilePage.clickonSubmitButtonForChangeEmailAddress();
		// validate the success message pop up
		testUtil.setHardWait(1000);
		myEstesUserProfilePage.validateNewEmailSuccessMessage();

	}

	// NITHYA'S SCRIPT-PRIORITY SET TO 35
	/*
	 * Edit MyEstes Profile - Verify an Admin can restrict the access of the users
	 * for certain application.
	 * 
	 */

	@Test(enabled = true, priority = 12)

	public void executeQZ_7649() throws InterruptedException {

		String app = "Address Book";
		// login to myestes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesLoginPage.clickOnMyEstes();
		// click on edit profile
		myEstesLoginPage.clickOnEditMyProfile();
		// click on View Edit User link
		myEstesUserProfilePage.clickOnViewEditUserLink();
		// click on user link
		myEstesUserProfilePage.clickUsername();
		// click on block apps


		if (!myEstesUserProfilePage.isDisplay()) {
			logger.info("myEstesUserProfilePage is not displayed.");
			myEstesUserProfilePage.clickBlock();
		}
		testUtil.setHardWait(500);
		// verify apps blocked
		myEstesUserProfilePage.verifyBlockedApplication(app);
		myEstesUserProfilePage.clickOnClose();
		
//		MyEstesSmokeTest myEstesSmokeTest = new MyEstesSmokeTest();
//		String name = myEstesSmokeTest.executeQZ_3268();
//		logger.info("name : "+name);

		testUtil.setHardWait(1000);
//		myEstesUserProfilePage.verifyAndClickOnEnableButton();
		myEstesUserProfilePage.fetchAndClickOnFirstEnabledUserButton();
		
		// logout
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();
		testUtil.setHardWait(1000);
		// login with the username on which changes are made
//		myEstesLoginPage.enterUserName("ADMINTEST");
		//myEstesLoginPage.enterUserName("TESTS101");-->commented and added below line
		myEstesLoginPage.enterUserName(username4); 
		testUtil.setHardWait(1000);
//		myEstesLoginPage.enterPassword("ADMINTEST");
		//myEstesLoginPage.enterPassword("TESTS101");-->commented and added below line
		myEstesLoginPage.enterPassword(password4); 
		testUtil.setHardWait(20000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.verifyBlockedApplication(app);
		// logout
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();
	}

	// THIS SCRIPT IS CLICKING ON SHIPPER,CONSIGNEE AND THIRD PARTY.DATA IS NOT
	// DISPLAYING FOR CONSIGNEE
	// AND SHIPPER.TURNED OFF UNTIL IT GETS UPDATED

	/*
	 * Welcome Page - GROUP Account - Verify the user can select shipment party
	 * after selecting an Account.
	 */

	@Test(enabled = true, priority = 13)

	public void executeQZ_7729()
			throws InterruptedException {

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password3);
		testUtil.setHardWait(500);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.verifyWelcomePage();

		myEstesHomePage.clickOnFirstAccountNumLink();
		myEstesRecentShipmentsPage.verifyRecentShipmentPageIsDisplayed();

		// Recent Shipment - Shipper
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton(); // --> Modified Locator
		testUtil.setHardWait(2000);
		//String expectedShipperPro = myEstesRecentShipmentsPage.returnPROFirstRowNoHyphen(); --commanded and added below steps
		
		String proNumForFirstRow = myEstesRecentShipmentsPage.getPRONumForFirstRow();  //added
		String expectedShipperPro = proNumForFirstRow.replace("-", ""); //added
																						
		myEstesRecentShipmentsPage.clickPROFirstRow(); // --> Modified Locator
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed(); // Added hard wait
		myEstesShipmentTrackingPage.clickOnExpandArrow();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedShipperPro); // Modified loctor
		testUtil.setHardWait(1000);
		driver.navigate().back();
		testUtil.setHardWait(2000);
		testUtil.setHardWait(10000);  //---Added extra waits to load page
		// Recent Shipment - Consignee
		myEstesRecentShipmentsPage.ClickOnRSConsigneeRadioButton();

		String expectedConsigneePro = myEstesRecentShipmentsPage.returnPROFirstRowNoHyphen();
		myEstesRecentShipmentsPage.clickPROFirstRow();
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();
		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedConsigneePro);
		driver.navigate().back();
		testUtil.setHardWait(4000);
		// Recent Shipment - Third Party Payor
		myEstesRecentShipmentsPage.ClickOnRSThirdPartyPayorRadioButton();
		String expectedPayorPro = myEstesRecentShipmentsPage.returnPROFirstRowNoHyphen();
		myEstesRecentShipmentsPage.clickPROFirstRow();
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();
		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedPayorPro);
	}

	/*
	 * Welcome Page - GROUP Account - Verify user is able to customize Preferences
	 * from Recent shipment page.
	 * 
	 */

	@Test(enabled = true, priority = 14)
	public void executeQZ_7727()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnFirstAccountNumLink();
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.verifyRecentShipmentPageIsDisplayed();

		myEstesRecentShipmentsPage.clickOnSetViewingPreference();
		myEstesRecentShipmentsPage.verifySetViewingPreferenceDialogIsDisplayed();

		testUtil.setHardWait(2000);

		String label = myEstesRecentShipmentsPage.selectViewingPreferenceAndOption();

		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnRecentShipments();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnFirstAccountNumLink();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.verifyWelcomePage();

		myEstesRecentShipmentsPage.verifyRadioButtonIsSelected(label);
	}

	
	/*
	 * Edit My Estes Profile - Change my username - Verify Error message displays
	 * when required fields are left blank
	 */

	@Test(enabled = true, priority = 15)
	public void executeQZ_7651() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username3);
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnEditMyProfile();
		myEstesUserProfilePage.clickOnChangeMyUseNameLink();
		myEstesUserProfilePage.clickonSubmitButtonForChangeUserName();
		myEstesUserProfilePage.validateErrorMessage();

		myEstesUserProfilePage.enterUserName(username3);
		myEstesUserProfilePage.enterPassword(password3);
		myEstesUserProfilePage.clickonSubmitButtonForChangeUserName();

		myEstesUserProfilePage.clickonSubmitButtonForChangeUserName();
		myEstesUserProfilePage.validateErrorMessage();

	}

	/*
	 * Edit MyEstes Profile - Verify Error message displayed when required fields
	 * are left blank in Create New User form
	 */

	@Test(enabled = true, priority = 16)

	public void executeQZ_7650()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username10);
		myEstesLoginPage.enterPassword(password10);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnEditMyProfile();

		myEstesUserProfilePage.clickOnChangeMyUseNameLink();
		myEstesUserProfilePage.clickonSubmitButton();
		myEstesUserProfilePage.validateErrorMessage();
		myEstesUserProfilePage.clickOnCancelButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();
	}

	// According to Dee, this test need to turn off and kept for future just in case
	/*
	 * Request Additional Info - Verify error message displays when required fields
	 * are left blank (NEW)
	 */

	// getProNumberFromDB(); needs to be added to the Util Class

	/*@Test(enabled = false, priority = 19)
	public void qz8330_verifyErrorMessageDisplaysWhenRequiredFieldsAreLeftBlank() throws Exception {

		// Step 1: Run the following query in EXLAQA and record any PRO number: select
		// fhot, fhpro from fbfiles.frp001 where fhdc = '' order by fhpud8 desc
		String proNumber = testUtil.getProNumberFromDB();

		// Step 2: Launch My Estes application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 3: Login with local credentials
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		// Step 4: Click on Shipment Tracking
		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();

		// Step 5: Enter recorded Pro Number
		shipmentTrackingPage.selectPRONumber();
		shipmentTrackingPage.enterPORNumber(proNumber);

		// Step 6: Click on search button
		shipmentTrackingPage.clickOnSearchButton();

		// Step 7: From Results section, Click on the following link: Request Additional
		// information
		shipmentTrackingPage.clickOnExpandArrow(); // Added code to click on pro-number as the Request Additional
													// information link will display only when we select the proNumber
		shipmentTrackingPage.clickOnRequestAddInfo();

		// Step 8: From Request Additional Information Form, delete values from the
		// following auto-populated fields: Name,Phone,Email Address,
		// Comments/Descriptions
		shipmentTrackingPage.deleteAutoPopulatedFields();

		// Step 9: Click Submit
		shipmentTrackingPage.clickSubmit();
		testUtil.setHardWait(3000);

		// Verify error message displayed for required fields
		shipmentTrackingPage.verifyErrorMsgForReqFields();

	}*/

	/**
	 * 
	 * This test failed on 6/28/22- No data in the shipping tracking 
	 * 
	 * Welcome Page - Verify the Group Account user can get shipment information
	 * when account number is Searched by Account Number field
	 */

	@Test(enabled = true, priority = 17) // ITS PASSING NOW- DISABLED IT IN FLACKY CLASS
	public void executeQZ_7724()
			throws InterruptedException {

		// login to myestes
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnRecentShipments();
		myEstesHomePage.enterAccountNumber("0062372"); // 2205132
		
		myEstesHomePage.clickOnSubmitButton();

		myEstesRecentShipmentsPage.verifyRecentShipmentsTextDisplayed();
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();

		//myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();
		myEstesRecentShipmentsPage.clickPROFirstRow();
		// validate user navigated to shipment page and displayes shipment info
		shipmentTrackingPage.verifyShippingTrackingPage();
		testUtil.setHardWait(2000);
		shipmentTrackingPage.verifyPROHeader();
		shipmentTrackingPage.verifyPickupHeader();
		shipmentTrackingPage.verifyBOLHeader();
		//shipmentTrackingPage.verifyHeaderPONumber();
		shipmentTrackingPage.verifyStatusHeader();
		shipmentTrackingPage.clickExpandArrow();
		shipmentTrackingPage.verifyAndPrintAnyPROTable();
       // shipmentTrackingPage.validatePROResultTable("//*[@class='mat-card-content ng-tns-c3-1']");
	
	}
	/*
	 * Welcome Page - Verify user can search the Account Search modal using
	 * different search criteria
	 */
	@Test(enabled = true, priority = 18)
	public void executeQZ_7731()
			throws InterruptedException {

		// login to myestes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnRecentShipments();
		fileAClaimPage.clickOnAccountSearch();

		fileAClaimPage.enterInAccountSearch("0500845");// now this account has different address and city which are not
														// same as manual steps.
		fileAClaimPage.verifyAccountNumberHeader();
		fileAClaimPage.verifyAccountNameHeader();
		fileAClaimPage.verifyAddressHeader();
		fileAClaimPage.verifyCityHeader();
		fileAClaimPage.verifyStateHeader();
		fileAClaimPage.verifyZipHeader();
		fileAClaimPage.verifyAccountNumberValue();
		fileAClaimPage.verifyAccountNameValue();
		fileAClaimPage.verifyAddressValue();
		fileAClaimPage.verifyCityValue();
		fileAClaimPage.verifyStateValue();
		fileAClaimPage.verifyZipValue();

		fileAClaimPage.enterInAccountSearch("carquest");
		testUtil.setHardWait(1000);
		fileAClaimPage.verifyAccountNumberHeader();
		fileAClaimPage.verifyAccountNameHeader();
		fileAClaimPage.verifyAddressHeader();
		fileAClaimPage.verifyCityHeader();
		fileAClaimPage.verifyStateHeader();
		fileAClaimPage.verifyZipHeader();
		fileAClaimPage.verifyAccountNumberValue();
		fileAClaimPage.verifyAccountNameValue();
		fileAClaimPage.verifyAddressValue();
		fileAClaimPage.verifyCityValue();
		fileAClaimPage.verifyStateValue();
		fileAClaimPage.verifyZipValue();

		fileAClaimPage.enterInAccountSearch("3661 VALLEY PIKE");
		fileAClaimPage.verifyAccountNumberHeader();
		fileAClaimPage.verifyAccountNameHeader();
		fileAClaimPage.verifyAddressHeader();
		fileAClaimPage.verifyCityHeader();
		fileAClaimPage.verifyStateHeader();
		fileAClaimPage.verifyZipHeader();
		fileAClaimPage.verifyAccountNumberValue();
		fileAClaimPage.verifyAccountNameValue();
		fileAClaimPage.verifyAddressValue();
		fileAClaimPage.verifyCityValue();
		fileAClaimPage.verifyStateValue();
		fileAClaimPage.verifyZipValue();

		fileAClaimPage.enterInAccountSearch("WINCHESTER");
		fileAClaimPage.verifyAccountNumberHeader();
		fileAClaimPage.verifyAccountNameHeader();
		fileAClaimPage.verifyAddressHeader();
		fileAClaimPage.verifyCityHeader();
		fileAClaimPage.verifyStateHeader();
		fileAClaimPage.verifyZipHeader();
		fileAClaimPage.verifyAccountNumberValue();
		fileAClaimPage.verifyAccountNameValue();
		fileAClaimPage.verifyAddressValue();
		fileAClaimPage.verifyCityValue();
		fileAClaimPage.verifyStateValue();
		fileAClaimPage.verifyZipValue();

		fileAClaimPage.enterInAccountSearch("VA");
		fileAClaimPage.verifyAccountNumberHeader();
		fileAClaimPage.verifyAccountNameHeader();
		fileAClaimPage.verifyAddressHeader();
		fileAClaimPage.verifyCityHeader();
		fileAClaimPage.verifyStateHeader();
		fileAClaimPage.verifyZipHeader();
		fileAClaimPage.verifyAccountNumberValue();
		fileAClaimPage.verifyAccountNameValue();
		fileAClaimPage.verifyAddressValue();
		fileAClaimPage.verifyCityValue();
		fileAClaimPage.verifyStateValue();
		fileAClaimPage.verifyZipValue();

		fileAClaimPage.enterInAccountSearch("22602");
		fileAClaimPage.verifyAccountNumberHeader();
		fileAClaimPage.verifyAccountNameHeader();
		testUtil.setHardWait(1000);
		fileAClaimPage.verifyAddressHeader();
		fileAClaimPage.verifyCityHeader();
		fileAClaimPage.verifyStateHeader();
		fileAClaimPage.verifyZipHeader();
		fileAClaimPage.verifyAccountNumberValue();
		fileAClaimPage.verifyAccountNameValue();
		fileAClaimPage.verifyAddressValue();
		fileAClaimPage.verifyCityValue();
		fileAClaimPage.verifyStateValue();
		fileAClaimPage.verifyZipValue();

		fileAClaimPage.enterInAccountSearch("22account");
		fileAClaimPage.verifyResultsText("No accounts found.");

	}

	/**
	 * test passed o n7/25/22
	 * 
	 * Welcome Page - NATIONAL Account - Verify user is able to customize
	 * Preferences from Recent shipment page.
	 */
	@Test(enabled = true, priority =19)
	public void executeQZ_7732()
			throws InterruptedException {
		// login to myestes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		// myEstesLoginPage.clickOnMyEstes();
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnRecentShipments();
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.verifyRecentShipmentsText();
		testUtil.setHardWait(3000);
		myEstesRecentShipmentsPage.clickAcctNumFirstRow();
		myEstesRecentShipmentsPage.clickOnSetViewingPreference();

		String label = myEstesRecentShipmentsPage.selectViewingPreferenceAndOption();

		myEstesRecentShipmentsPage.clickAcctNumFirstRow();
		myEstesHomePage.clickOnRecentShipments();
		myEstesWelcomePage.verifyWelcomePage();
		myEstesHomePage.clickOnFirstAccountNumLink();

		testUtil.setHardWait(3000);
		myEstesRecentShipmentsPage.verifyRadioButtonIsSelected(label);

	}

	
	
	/*
	 * Edit MyEstes Profile - Verify the admin can search for the user by First
	 * Name, Last Name, Username
	 */
	@Test(enabled = true, priority = 20)
	public void executeQZ_7653() throws InterruptedException {

		String FirstName = "LISA";
		String LastName = "NASH";
		String UserName = "ADMINTEST";

		// Step 1: Open MyEstes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as QAAdmin
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Click on Edit My Profile
		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();

		// Step 4: Click on View Edit User link
		myEstesUserProfilePage.clickOnViewEditUserLink();

		// Step 5: Enter first name
		myEstesUserProfilePage.enterFirstName(FirstName);

		myEstesUserProfilePage.enterLastName(LastName);
		myEstesUserProfilePage.enterUserName(UserName);
		// Step 6: Click submit
		myEstesUserProfilePage.clickonSubmitButtonForChangeUserName();

		// Validate search result

		myEstesUserProfilePage.verifySearchResult("UserName", UserName);

	}

	/**
	 * Failed because of no data, pro number
	 * 
	 * Welcome Page - National Account - Verify the user can select shipment party
	 * after selecting an Account.
	 */

	@Test(enabled = true, priority = 21)

	public void executeQZ_7728()
			throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username2); // Per Dee using smokelocal credentials to be able to finish the test
		testUtil.setHardWait(1000);											// as national account DB has no accounts for cmcls type= "R"
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(2000);		
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.verifyWelcomePage();

		// Recent Shipment - Shipper
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();
		testUtil.setHardWait(3000);
		String expectedShipperPro = myEstesRecentShipmentsPage.returnPROFirstRowNoHyphen();
		testUtil.setHardWait(3000);
		myEstesRecentShipmentsPage.clickPROFirstRow();
		
		testUtil.setHardWait(1000);
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();

		myEstesShipmentTrackingPage.clickOnCaretSymbol();

		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedShipperPro);
		driver.navigate().back();

		// Recent Shipment - Consignee
		myEstesRecentShipmentsPage.ClickOnRSConsigneeRadioButton();

		String expectedConsigneePro = myEstesRecentShipmentsPage.returnPROFirstRowNoHyphen();
		
		myEstesRecentShipmentsPage.clickPROFirstRow();
		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();
		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedConsigneePro);
		driver.navigate().back();
		testUtil.setHardWait(3000);

		myEstesRecentShipmentsPage.ClickOnRSThirdPartyPayorRadioButton();

		String expectedPayorPro = myEstesRecentShipmentsPage.returnPROFirstRowNoHyphen();

		myEstesRecentShipmentsPage.clickPROFirstRow();

		myEstesShipmentTrackingPage.verifyShipTrackingTextDisplayed();

		myEstesShipmentTrackingPage.clickOnCaretSymbol();
		assertEquals(myEstesShipmentTrackingPage.verifyPRONum(), expectedPayorPro);

	}

	/*
	 * Edit My Estes Profile - Verify Update Profile Information page should be 
	 * displayed for a profile with missing information
	 */
	@Test(enabled = true, priority = 22)

	public void executeQZ_10093()
			throws InterruptedException {

		//Data Needs : Reset the following values to blank each time the test runs
		String query = "Update ESTESRTGY2.QNP230 set QSFNAM = '', QSLNAM = '', QSCNAM = '',  QSPNAC = '', QSPNFP= '', QSPNLP= ''  WHERE QSUN = 'SMOKELOCAL'";

		//ArrayList<String> dbValue = sqlDataList.getFirstRowDetailsFromEXLAQA(query);
		sqlDataList.updateRecordInTable(query);

		String firstName = "automationTest";
		String lastName = "automationTest";
		String companyName = "estes";
		String phoneNumber = "(124) 332-1232";
		//Step 1 &2: log on to the application 
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(500);
		myEstesLoginPage.clickOnLoginButton();
		//Step 3:From My Estes dropdown, Click on Edit My  Profile application
		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();

		myEstesUpdateProfileInformationPage.verifyUpdateProfilePage();

		//Step 4:From the Update Profile Information screen, fill out all required fields mark by asterisk (*)
		myEstesUpdateProfileInformationPage.enterFirstName(firstName);
		myEstesUpdateProfileInformationPage.enterLastName(lastName);
		myEstesUpdateProfileInformationPage.enterCompanyName(companyName);
		myEstesUpdateProfileInformationPage.enterPhoneNumber(phoneNumber);

		//Step 5:Click the Save & Continue button     
		myEstesUpdateProfileInformationPage.clickOnSaveAndContinueButton();

		// myEstesUpdateProfileInformationPage.verifySuccessMessage();

		//Step 6:user is navigated to the Your My Estes Profile page
		myEstesUserProfilePage.verifyProfilePage();

		// Step 7:Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

		//Step 8:Login back with smokelocal credentials
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();

		//Step 9: From My Estes dropdown, Click on Edit My  Profile application
		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnEditMyProfile();
		
		//Validate user navigates to "Your My Estes User Profile* page
		myEstesUserProfilePage.verifyProfilePage();

	}
}
	