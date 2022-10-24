package myEstesTests;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import claimsPages.ClaimsPage;
import eNetPages.ENetAddNewEmailAddressPage;
import eNetPages.ENetAgedARPage;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetChangeExistingEmailAddressPage;
import eNetPages.ENetDeleteExistingEmailAddress;
import eNetPages.ENetHomePage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetRestrictedPage;
import eNetPages.ENetVTLTableMaintenancePage;
import imageRetrievalPages.MyEstesImageRetrievalPage;
import myEstesPages.MyEstesAccountSearchPage;
import myEstesPages.MyEstesAddCommodityPage;
import myEstesPages.MyEstesAddNewAddressPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesCanadianCurrencyConversionPage;
import myEstesPages.MyEstesCommodityLibraryPage;
import myEstesPages.MyEstesCustomizeQuickLinkPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesNewUserPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import myEstesPages.MyEstesRequestAccountNumberPage;
import myEstesPages.MyEstesRequestAdditionalInfoPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesShipmentTrackingPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesTerminalLookupPage;
import myEstesPages.MyEstesUserProfilePage;
import myEstesPages.MyEstesWelcomePage;
import rateQuotePages.RateQuotePage;
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;
import transitTimeCalculatorPages.TransitTimeCalculatorPage;
import util.SQLDataList;
import util.TestListener;

public class MyEstesSmokeTest extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesSmokeTest.class.getName());

	
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRequestAccountNumberPage myEstesRequestAccountNumberPage = new MyEstesRequestAccountNumberPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();
	MyEstesAddNewAddressPage myEstesAddNewAddressPage = new MyEstesAddNewAddressPage();
	MyEstesCommodityLibraryPage myEstesCommodityLibraryPage = new MyEstesCommodityLibraryPage();
	MyEstesAddCommodityPage myEstesAddCommodityPage = new MyEstesAddCommodityPage();
	MyEstesTerminalLookupPage terminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTerminalLookupPage myEstesTerminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesUserProfilePage myEstesUserProfilePage = new MyEstesUserProfilePage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	ClaimsPage claimsPage = new ClaimsPage();
	MyEstesCanadianCurrencyConversionPage myEstesCanadianCurrencyConversionPage = new MyEstesCanadianCurrencyConversionPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();
	MyEstesAccountSearchPage myEstesAccountSearchPage = new MyEstesAccountSearchPage();
	MyEstesCustomizeQuickLinkPage myEstesCustomizeQuickLinkPage = new MyEstesCustomizeQuickLinkPage();
	SQLDataList sQLDataList = new SQLDataList();
	MyEstesRequestAdditionalInfoPage myEstesRequestAdditionalInfoPage = new MyEstesRequestAdditionalInfoPage();
	MyEstesImageRetrievalPage myEstesImageRetrievalPage = new MyEstesImageRetrievalPage();
	TransitTimeCalculatorPage transitTimeCalculatorPage = new TransitTimeCalculatorPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	MyEstesNewUserPage myEstesNewUserPage =new MyEstesNewUserPage ();
	ENetLoginPage eNetLoginPage= new ENetLoginPage ();
	ENetHomePage eNetHomePage= new ENetHomePage(); 
	ENetRestrictedPage 	eNetRestrictedPage =new ENetRestrictedPage();
	ENetChangeExistingEmailAddressPage eNetChangeExistingEmailAddressPage= new ENetChangeExistingEmailAddressPage();
	ENetAddNewEmailAddressPage  eNetAddNewEmailAddressPage= new  ENetAddNewEmailAddressPage();
	ENetDeleteExistingEmailAddress 	eNetDeleteExistingEmailAddress= new ENetDeleteExistingEmailAddress();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetVTLTableMaintenancePage eNetVTLTableMaintenancePage = new ENetVTLTableMaintenancePage();
	ENetAgedARPage eNetAgedARPage = new ENetAgedARPage();
	
	/******************************* TESTS *******************************/

	/*
	 * MyEstes Signup - Verify the user is able to Sign Up for MyEstes application.
	 * DP2-308
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_3252() {
		
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnCreateAccountLink();
		testUtil.setHardWait(1000);
		myEstesSignUpPage.clickYesAndHaveMyAccountNumberRadioButton();
		myEstesSignUpPage.clickOnNextButton();

		myEstesSignUpPage.enterCompanyAccountNumber();
		myEstesSignUpPage.enterCompanyName();
		myEstesSignUpPage.enterFirstName();
		myEstesSignUpPage.enterLastName();
		myEstesSignUpPage.enterEmailAddress();
		myEstesSignUpPage.enterPhoneNumber();
		myEstesSignUpPage.enterUserName();
		myEstesSignUpPage.enterPassword("12345678");
		myEstesSignUpPage.enterConfirmPassword("12345678");
		myEstesSignUpPage.clickOnSubmitButton();

		myEstesSignUpPage.verifyConfimationMessage();
	}




	

	// Change the profile and password to smokelocal a
	// Unable to verify the login page. Bug in prod. Waiting for the bug fix.
	/**
	 * passed on 8/4/22
	 * Edit My Estes Profile - Verify the user can change password.
	 */

	@Test(enabled = true, priority = 2)

       public void executeQZ_3266() throws InterruptedException {

        String newPassword = "smokelocal", message = "Success! Password changed successfully";

        myEstesHomePage.clickOnMyEstes();
        myEstesHomePage.clickOnLogin();
        myEstesLoginPage.enterUserName("smokelocal");
        myEstesLoginPage.enterPassword("smokelocal");
        testUtil.setHardWait(2000);
        myEstesLoginPage.clickOnLoginButton();

        myEstesLoginPage.clickOnMyEstes();
        myEstesLoginPage.clickOnEditMyProfile();
        testUtil.setHardWait(3000);
        myEstesUserProfilePage.clickOnChangeMyPasswordLink();
        //changed qalocal to testnat according to Rusalina
        myEstesUserProfilePage.enterUserName("smokelocal");
        myEstesUserProfilePage.enterPassword("smokelocal");
        
       // myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();  Delete line
       //testUtil.setHardWait(20000);  Delete line

        myEstesUserProfilePage.clickOnSubmitButton();
        
        myEstesUserProfilePage.enterPassword(newPassword);
        myEstesUserProfilePage.enterConfirmPassword(newPassword);
        myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();
        
        myEstesUserProfilePage.verifySuccessMessage(message);
        
        myEstesLoginPage.enterUserName("smokelocal");
        myEstesLoginPage.enterPassword(newPassword);
        myEstesLoginPage.clickOnLogin1();
        
        myEstesUserProfilePage.verifyProfilePage();
        
        myEstesUserProfilePage.clickOnChangeMyPasswordLink();
        myEstesUserProfilePage.enterUserName("smokelocal");
        myEstesUserProfilePage.enterPassword(newPassword);
        
       // myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();  Delete line
       // testUtil.setHardWait(20000);   Delete line

        myEstesUserProfilePage.clickOnSubmitButton();  
        
        myEstesUserProfilePage.enterPassword("smokelocal");
        myEstesUserProfilePage.enterConfirmPassword("smokelocal");
        myEstesUserProfilePage.clickonSubmitButtonForVerifyLogin();
        
        myEstesUserProfilePage.verifySuccessMessage(message);
        
        myEstesLoginPage.enterUserName("smokelocal");
        myEstesLoginPage.enterPassword("smokelocal");
        myEstesLoginPage.clickOnLogin1();
        
      //myEstesUserProfilePage.verifyProfilePage();// Unable to verify the login page. Bug in prod. Waiting for the bug
       }           

	/*
	 * According to Dee: we should not run this and store it elsewhere just incase
	 */
	/*
	 * Request Additional Information - Authenticated - Verify user can submit
	 * "Request Additional Information" form.
	 */
	/*@Test(enabled = false, priority = 3)

	public void qz7686_verifyUserCanSubmitRequestAdditionalInformationForm() throws InterruptedException {

		String comments = "Test Info",
				expectedMessage = "Your message has been sent. A customer care representative will respond as soon as possible.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(500);
		myEstesLoginPage.enterUserName(username15);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password15);
		testUtil.setHardWait(500);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();
		shipmentTrackingPage.selectPRONumber();
		shipmentTrackingPage.enterPORNumber("1030466692");
		shipmentTrackingPage.clickOnSearchButton();
		testUtil.setHardWait(1000);
		shipmentTrackingPage.clickOnExpandArrow();
		shipmentTrackingPage.clickOnRequestAddInfo();
		testUtil.setHardWait(3000);
	//	shipmentTrackingPage.clickOnRequestAddInfo();
	//	testUtil.setHardWait(3000);

		shipmentTrackingPage.verifyName();
		shipmentTrackingPage.verifyEmail();

		testUtil.setHardWait(2000);
		shipmentTrackingPage.verifyPhoneNo();
		shipmentTrackingPage.verifyPRONo();
		shipmentTrackingPage.verifyUsername();

		shipmentTrackingPage.selectTrackingHelp();
		shipmentTrackingPage.enterComments(comments);
		shipmentTrackingPage.clickSubmit();

		shipmentTrackingPage.verifySuccessMessage(expectedMessage);
	}
*/

	
	/*
	 * Canadian Currency Conversion - Verify US Dollars to CAD rates are available
	 * based on Exchange rates. DP2-308
	 */

	@Test(enabled = true, priority = 4)

	public void executeQZ_3250() throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnResources();
		myEstesHomePage.clickOnCanadianCurrencyConversion();
		myEstesCanadianCurrencyConversionPage.verifyUSDtoCADCurrencyConversionTable();

	}

	// Per Dee- this test needs to be turned off until further notices
	/**
	 * Welcome Page - Verify the Group Account user can get shipment information by
	 * clicking account number on Welcome Page.
	 */

	@Test(enabled = true, priority = 5)

	public void executeQZ_7723()
			throws InterruptedException {

		// Step 1: Launch My Estes application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		// Step 2: Login with group credentials
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		// Step 3: From the My Estes/Welcome Page, Select account from the list for
		// which the Freight bill was created for.
		myEstesWelcomePage.clickOnFirstAccountNumber();
		testUtil.setHardWait(1000);
		// Validate user navigates to <Recent Shipment for xxxxxxx> page
		myEstesRecentShipmentsPage.verifyRecentShipmentPageIsDisplayed();

		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();
		testUtil.setHardWait(4000);
		
		// Step 4: From Recent Shipment page, click on Any available <PRO Number>
		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();

		// Validate User is navigated to <Shipment Tracking> page and Displays
		// <Shipment> information under Tracking Results
		myEstesShipmentTrackingPage.verifyShipmentsTrackingPageDisplayed();

		// Clicking on Expand All button to view shipment information under Tracking
		// Results
		myEstesShipmentTrackingPage.clickOnExpandArrow();
		myEstesShipmentTrackingPage.verifyTrackingResult();
	}

	/**
	 * this test is  passing now after adding <clickOnShipperRadioButton> to display list of pros
	 * 
	 * Welcome Page - Verify the National Account user can get shipment information
	 * by clicking account number on Welcome Page
	 */
	@Test(enabled = true, priority = 6)
	public void executeQZ_7726()
			throws InterruptedException {

		//Step 1 & 2: Open the link and enter credentials.
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		
		//Step 3:From the Welcome Page, Click any of the Account Number listed
		myEstesWelcomePage.accountSearchBarInput("B005833");
		myEstesWelcomePage.clickOnSearchAccountSubmit();
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();
		// Validate user navigates to <Recent Shipment for xxxxxxx> page -commented
		myEstesRecentShipmentsPage.verifyRecentShipmentPageIsDisplayed();
		//Step 4:From Recent Shipment page, Click on Any available
		testUtil.setHardWait(4000);
		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();
		testUtil.setHardWait(1000);
		myEstesShipmentTrackingPage.verifyShipmentsTrackingPageDisplayed();
		testUtil.setHardWait(2000);

		myEstesShipmentTrackingPage.verifyTrackingResult();

	}

	/**
	 * This test passed on 6/14/22
	 * 
	 * Welcome Page - Verify the Local Account user can get shipment information
	 * when clicked on a PRO number
	 */
	@Test(enabled = true, priority = 7)
	public void executeQZ_7725()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();
		myEstesRecentShipmentsPage.clickOnFirstPRONumFFirstRow();
		myEstesShipmentTrackingPage.verifyShipmentsTrackingPageDisplayed();
	}

	/*
	 * This is Jagacy- green screen just a test
	 * 
	 * Method # 1
	 * 
	 * @Test(enabled = false, priority = 0)
	 * 
	 * public void demoTest() throws Exception {
	 * 
	 * AS400Test test = new AS400Test();
	 * 
	 * String FB = test.reserveFreightBill("087", "1", "2302234");
	 * test.updateFreightBill("087", "0879139631", "8700777"); }
	 * 
	 * METHOD # 2
	 * 
	 * /* SessionVt session = new SessionVt("myJagacyVT","exlaqa","dec-vt220");
	 * session.open("myJagacyVT","exlaqa","dec-vt220");
	 * 
	 * LoginScreen loginScreen = new LoginScreen(session);
	 * loginScreen.logon("qatstfrtbl", "qatest2019"); MasterMenuScreen
	 * masterMenuScreen = new MasterMenuScreen(session);
	 * masterMenuScreen.enterFreightBillMenuOption("1");
	 * 
	 * 
	 */

	/*
	 * According to Dee, this test needs to turn off and kept for future just incase
	 */
	/*
	 * Request Additional Info - Verify the user can request an information by
	 * submitting the Request Additional Information form(NEW)
	 */

	/*@Test(enabled = false, priority = 8)

	public void qz8332_verifyTheUserCanRequestAnInformationBySubmittingTheRequestAdditionalInformationForm()
			throws Exception {

		// Step 1: Run the following query in EXLAQA and record any PRO number: select
		// fhot, fhpro from fbfiles.frp001 where fhdc = '' order by fhpud8 desc
		String proNumber = testUtil.getProNumberFromDB();

		// Step 2: Launch My Estes application
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(3000);
		// Step 3: Login with local credentials
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(3000);
		// Step 4: Click on Shipment Tracking
		myEstesHomePage.clickOnTrack(); // Shipment Tracking link is not displaying-added this extra step to click on Shipment Tracking
		myEstesHomePage.clickOnShipmentTraking();
		testUtil.setHardWait(1000);
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

		// Step 8: From Request Additional Information Form, enter a value for the
		// required fields:
		testUtil.setHardWait(2000);
		shipmentTrackingPage.enterName("QATester");
		shipmentTrackingPage.enterEmail("QA@estes-express.com");
		shipmentTrackingPage.enterPhoneNumber("8885637224");
		shipmentTrackingPage.selectTrackingHelp();
		shipmentTrackingPage.enterComments("Testing");

		// Step 9: Click Submit
		shipmentTrackingPage.clickSubmit();

		// Verify Success message
		shipmentTrackingPage.verifySuccessMessage(
				"Your message has been sent. A customer care representative will respond as soon as possible.");
	}
*/
	/*
	 * According to Dee, this test needs turn off and kept just incase
	 */
	/*
	 * Request Additional Info - Verify the user can submit the Request Additional
	 * Information form when Other is selected for Description of Problem (NEW)
	 */
	/*@Test(enabled = false, priority = 9)

	public void qz8333_verifyTheUserCanSubmitTheRequestAdditionalInformationFormWhenOtherIsSelectedForDescriptionOfProblem()
			throws Exception {

		// Step 1: Run the following query in EXLAQA and record any PRO number: select
		// fhot, fhpro from fbfiles.frp001 where fhdc = '' order by fhpud8 desc
		String proNumber = testUtil.getProNumberFromDB();

		// Step 2: Launch My Estes application
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 3: Login with local credentials
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Step 4: Click on Shipment Tracking
		myEstesHomePage.clickOnTrack();
		myEstesHomePage.clickOnShipmentTraking();
		testUtil.setHardWait(1000);
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

		// Step 8: From Request Additional Information Form, enter a value for the
		// required fields:
		shipmentTrackingPage.enterName("QATester");
		shipmentTrackingPage.enterEmail("QA@estes-express.com");
		shipmentTrackingPage.enterPhoneNumber("8885637224");
		shipmentTrackingPage.selectOther();
		shipmentTrackingPage.enterComments("Testing");

		// Step 9: Click Submit
		shipmentTrackingPage.clickSubmit();

		// Verify Success message
		shipmentTrackingPage.verifySuccessMessage(
				"Your message has been sent. A customer care representative will respond as soon as possible.");
		System.out.println("Verify email mannually!!!!!!!!");
	}*/

	/*
	 * According to Dee, this test needs to be turned off and kept just incase
	 */
	
	/*
	 * Request Additional Info - Verify the user can submit the Request Additional
	 * Information form when Tracking Help is selected for Description of Problem
	 * (NEW)
	 */
	
	/*@Test(enabled = false, priority = 10)

	public void qz8334_verifyTheUserCanSubmitTheRequestAdditionalInformationFormWhenTrackingHelpIsSelectedForDescriptionOfProblem()
			throws Exception {

		String query = "select fhot, fhpro from fbfiles.frp001 where fhdc = '' order by fhpud8 desc";

		String successMessage = "Your message has been sent. A customer care representative will respond as soon as possible.";

		if (sQLDataList.validateValueFromDataBase(query) == true) {
			ArrayList<String> fbDetails = sQLDataList.getFirstRowDetailsFromEXLAQA(query);

			String originTerminal = fbDetails.get(0);
			String proNumber = fbDetails.get(1);
			testUtil.setHardWait(1000);
			// Login to MyEstes as Smokenat
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			testUtil.setHardWait(2000);
			myEstesLoginPage.enterUserName(username1);
			myEstesLoginPage.enterPassword(password1);
			myEstesLoginPage.clickOnLoginButton();

		//	myEstesCustomizeQuickLinkPage.addOrRemoveQuickLinks("Shipment Tracking");// why you're adding this?
			myEstesHomePage.clickOnTrack();
			myEstesHomePage.clickOnShipmentTraking();
		
			shipmentTrackingPage.verifyShippingTrackingPage();

			shipmentTrackingPage.enterPRONumberInFormat(originTerminal + proNumber);
			shipmentTrackingPage.clickOnSearchButton();
			shipmentTrackingPage.clickOnFirstRecordFromTrackingResultTable();
			shipmentTrackingPage.clickOnRequestAdditionalInformationLink();

			myEstesRequestAdditionalInfoPage.enterRequestorName("Automation Testing");
			myEstesRequestAdditionalInfoPage.enterRequestorEmailAddress("estesqa@estes-express.com");
			myEstesRequestAdditionalInfoPage.enterRequestorPhoneNumber("8001234567");
			testUtil.setHardWait(2000);
			myEstesRequestAdditionalInfoPage.selectTrackingHelpInDescriptionOfProblem();
			myEstesRequestAdditionalInfoPage.enterRequestorComments("Tracking Help selected for description");
			myEstesRequestAdditionalInfoPage.clickOnSubmitButton();
			myEstesRequestAdditionalInfoPage.verifyRequestorInfoSuccessMessage(successMessage);

			/*
			 * the last step is to <Navigate to Roundcube and verify you recieved the report
			 * via E-mail we do not automate that.
			 */
//		}
//	}

	
	
	/**
	 * test passed on 8/1/2022
	 * Edit My Estes Profile - Admin Account - Verify the user can create a new user
	 * (Sub Account)
	 */
	//BUG: ACCORDING TO DEE ITS A LOW PRIORITY BUG.
	
	@Test(enabled = true, priority = 11)

	public String executeQZ_3268() throws InterruptedException {

			
			String firstName = "QATester";
			String lastName = "QZ3268";
			String emailAddress = "QA@estes-express.com";
			String phoneNumber = "8885637224";
			
			String password = "QA123";
			
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUserName(username4);
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterPassword(password4);
			testUtil.setHardWait(500);
			myEstesLoginPage.clickOnLoginButton();

			myEstesWelcomePage.clickOnEditMyProfile();
			testUtil.setHardWait(1000);
			myEstesUserProfilePage.clickOnCreateNewUser();
			
			String fName= myEstesNewUserPage.enterFirstName(firstName);
			String lName= myEstesNewUserPage.enterLastName(lastName);
			testUtil.setHardWait(2000);
			String email=myEstesNewUserPage.enterEmailAddress(emailAddress);
			String phNum=myEstesNewUserPage.enterPhoneNumber(phoneNumber);
			//String uName=
			String uName = myEstesNewUserPage.enterUserName();
			
			myEstesNewUserPage.enterPassword(password);
			myEstesNewUserPage.confirmPassword(password);
			myEstesNewUserPage.clickOSubmitButton();
			testUtil.setHardWait(5000);
			String message = "Edit User Access for "+myEstesNewUserPage.getUserName();
			logger.info(message);
			myEstesNewUserPage.verifyNewCreatedUser(message);
			testUtil.setHardWait(2000);
			myEstesNewUserPage.clickOnCloseButton();
			myEstesNewUserPage.logout();
			return uName;
		}


	
	/*
	 * eNet - RestrictedEmailMaintenance
	 */
	
	@Test(enabled = true,priority=12)
	
	public void executeQZ_9846() {
		
		String searchCriteria = "Restricted";
		String oEmailAddr = "ABC@GMAIL.COM";
		String cEmailAddr = "ABC123@GMAIL.COM";
		
		//Login to eNet as qaenet02
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		
		//Enter Restricted in Search
		eNetHomePage.enterSearchCriteria(searchCriteria);
		eNetHomePage.clickOnGoButton();

		
		//Click on View link
		eNetRestrictedPage.switchToFrameElement();
		eNetRestrictedPage.clickOnViewLink();
		driver.switchTo().defaultContent();
		
		eNetRestrictedPage.switchToFrameElement();
		eNetRestrictedPage.clickOnAddNewEmailAddress();
		driver.switchTo().defaultContent();
		
		//Add new email address to be restricted
		eNetRestrictedPage.switchToFrameElement();
		eNetAddNewEmailAddressPage.enterEmailAddress(oEmailAddr);
		eNetAddNewEmailAddressPage.clickOnSubmitBtn();
		driver.switchTo().defaultContent();
		testUtil.setHardWait(2000);
		
		//Verify restricted email address 
		eNetRestrictedPage.switchToFrameElement();
		eNetAddNewEmailAddressPage.verifyEmailAddedToRestrictedMessage();
		eNetAddNewEmailAddressPage.clickOnBackToMenuLink();
		driver.switchTo().defaultContent();
		testUtil.setHardWait(2000);
		
		//Change existing email
		eNetRestrictedPage.switchToFrameElement();
		eNetRestrictedPage.clickOnChangeExistingEmailAddress();
		driver.switchTo().defaultContent();
		
		//Enter the existing email address details
		eNetRestrictedPage.switchToFrameElement();
		eNetChangeExistingEmailAddressPage.selectEmailAddress(oEmailAddr);
		eNetChangeExistingEmailAddressPage.enterChangeToEmailAddress(cEmailAddr);
		eNetChangeExistingEmailAddressPage.clickOnSubmitBtn();
		driver.switchTo().defaultContent();
		testUtil.setHardWait(2000);
		
		//Verify existing email has been updated
		eNetRestrictedPage.switchToFrameElement();
		eNetChangeExistingEmailAddressPage.verifyEmailAddressChangeMessage();
		eNetChangeExistingEmailAddressPage.clickOnBackToMenuLink();
		driver.switchTo().defaultContent();
		testUtil.setHardWait(2000);
		
		//Delete the existing email
		eNetRestrictedPage.switchToFrameElement();
		eNetRestrictedPage.clickOnDeleteExistingEmailAddress();
		driver.switchTo().defaultContent();
		
		//Enter the email details
		eNetRestrictedPage.switchToFrameElement();
		eNetDeleteExistingEmailAddress.selectEmailAddress(cEmailAddr);
		eNetDeleteExistingEmailAddress.clickOnSubmitBtn();
		driver.switchTo().defaultContent();
		
		//Verify email has been deleted
		eNetRestrictedPage.switchToFrameElement();
		eNetDeleteExistingEmailAddress.verifyEmailAddressDeleteMessage();
		eNetHomePage.clickOnLogout();

	}

	/*
	 * ENET Table Maintenance_Set Aged AR VTL Account
	 */
	
	@Test(enabled = true, priority = 13)
	
	 public void executeQZ_5085() {
		
		String acctNumber = "6202474"; 
		String comments ="QZ-5085";
		
		 //Step 1: Login
		 driver.get(url2);
		 
		 //Step 2: Login using admin credentials
		 eNetLoginPage.enterUserID(username5);
		 eNetLoginPage.enterUserPassword(password5);
		 eNetLoginPage.clickOnLoginButton();
		 
		 //Step 3: click on applications
		//  eNetHomePage.verifyPageTitle();
		//  eNetHomePage.verifyPageTitleQaenet01();
		 eNetHomePage.clickOnApplicationsTab();
		 
		 
		 testUtil.setHardWait(5000);
		 //Step 4: Select VTL Table Maintenance Link from Customer service column
		 eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		
		 //Step 5: Click on Aged AR tab
		 eNetVTLTableMaintenancePage.clickOnAgedARTab();
		 
		 //Step 6: Enter Acct# and comments and click on Add
		 eNetAgedARPage.enterAccountNumber(acctNumber);
		 eNetAgedARPage.typecomments(comments);
		 eNetAgedARPage.clickOnAddButton();
		 
		 //A new record will be added to the table 
		 eNetAgedARPage.verifyAcctAddedSuccessMsg(acctNumber);
		 eNetAgedARPage.verifyAcctAddedToTheTable(acctNumber);
		 
		 //Step 7: Search the added acct# from the table  and click x icon
		 eNetAgedARPage.removeAccountNumber(acctNumber);
		 //Acct will be removed
		 eNetAgedARPage.verifyAcctRemovedSuccessMsg(acctNumber);
		 
	 }	

		

}

