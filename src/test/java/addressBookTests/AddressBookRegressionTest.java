package addressBookTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import myEstesPages.MyEstesAddNewAddressPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesEditAddressPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import myEstesPages.MyEstesRecoverLostUserNamePasswordPage;
import myEstesPages.MyEstesRequestAccountNumberPage;
import myEstesPages.MyEstesShipmentTrackingPage;
import myEstesPages.MyEstesUploadAddressBookPage;
import myEstesPages.MyEstesWelcomePage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;
import util.TestUtil;
import util.zapi.ZapiClient;
import util.zapiObjects.CycleDetails;

public class AddressBookRegressionTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();

	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();
	MyEstesEditAddressPage myEstesEditAddressPage = new MyEstesEditAddressPage();
	MyEstesAddNewAddressPage myEstesAddNewAddressPage = new MyEstesAddNewAddressPage();
	MyEstesRequestAccountNumberPage myEstesRequestAccountNumberPage = new MyEstesRequestAccountNumberPage();
	MyEstesRecoverLostUserNamePasswordPage myEstesRecoverLostUserNamePasswordPage = new MyEstesRecoverLostUserNamePasswordPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();
	RateQuotePage ltlRateQuotePage = new RateQuotePage();
	MyEstesUploadAddressBookPage myEstesUploadAddressBookPage = new MyEstesUploadAddressBookPage();

	/****************************** TESTS ******************************/

	// this test is failing to to lack of data
	

	// dp2-1359-Address Book - Verify the user can search for addresses by Advanced
	// Search

	@Test(enabled = true, priority = 1)

	public void executeQZ_7153() throws InterruptedException {

		/*AddressBookSmokeTest smoke= new AddressBookSmokeTest();
		smoke.qz3273_verifyUserCanAddAddressEntry();*/
		
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		
		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnAddressBook();

		if(myEstesAddressBookPage.isDisplyed()==false){
			
			/*myEstesAddressBookPage.clickOnAddNewAddressButton();
			testUtil.setHardWait(2000);
			testUtil.reloadPage();*/
			testUtil.setHardWait(1000);
			myEstesAddressBookPage.clickOnAddNewAddressButton();
			myEstesAddNewAddressPage.enterCompanyName();
			String coName = myEstesAddNewAddressPage.getCompanyName();
			myEstesAddNewAddressPage.selectCountry("United States");
			myEstesAddNewAddressPage.enterAddress();
			myEstesAddNewAddressPage.enterUSCityName();
			myEstesAddNewAddressPage.selectState("VA");
			myEstesAddNewAddressPage.enterZipCode();
			myEstesAddNewAddressPage.enterPhoneNumber("2021234567");
			myEstesAddNewAddressPage.clickOnSaveButton();
			
		}
    
		testUtil.setHardWait(10000);
		String coName = myEstesAddressBookPage.recordCompanyName();
		String lNum = myEstesAddressBookPage.recordLocationNumber();
		String sADD = myEstesAddressBookPage.recordStreetAddress();
		String cName = myEstesAddressBookPage.recordCityName();
		String sName = myEstesAddressBookPage.recordStateName();
		testUtil.setHardWait(1000);
		String z = myEstesAddressBookPage.recordZipCode();
		myEstesAddressBookPage.clickOnAdvanceSearchButton();

		myEstesAddressBookPage.enterCompanyName(coName);
		myEstesAddressBookPage.clickOnSearchButton();
		myEstesAddressBookPage.validateCompanyNameDisplay(coName);

		myEstesAddressBookPage.clickOnAdvanceSearchButton();
		myEstesAddressBookPage.enterLocationNumber(lNum);
		myEstesAddressBookPage.clickOnSearchButton();
		myEstesAddressBookPage.validateLocationDisplay(lNum);

		myEstesAddressBookPage.clickOnAdvanceSearchButton();
		myEstesAddressBookPage.enterStreetAddress(sADD);
		myEstesAddressBookPage.clickOnSearchButton();
		myEstesAddressBookPage.validateAddressDisplay(sADD);

		myEstesAddressBookPage.clickOnAdvanceSearchButton();
		myEstesAddressBookPage.enterCity(cName);
		myEstesAddressBookPage.clickOnSearchButton();
		myEstesAddressBookPage.validateAddressDisplay(cName);

		myEstesAddressBookPage.clickOnAdvanceSearchButton();
		myEstesAddressBookPage.enterState(sName);
		myEstesAddressBookPage.clickOnSearchButton();
		myEstesAddressBookPage.validateAddressDisplay(sName);

		myEstesAddressBookPage.clickOnAdvanceSearchButton();
		myEstesAddressBookPage.enterZipCode(z);
		myEstesAddressBookPage.clickOnSearchButton();
		myEstesAddressBookPage.validateAddressDisplay(z);

	}

	// DP2-281- Address Book - Verify user can Append an existing Address Book

	// HEADLESS IS NOT SUPPORTING ROBOT CLASS FOR UPLOADS. THIS RUNS IN UI

	// THIS TEST DEALS WITH DOWNLOADS AND IS RUNNING LOCALLY
	@Test(enabled = false, priority = 2)

	public void executeQZ_6086()
			throws InterruptedException, AWTException, IOException {

		String companyNme = "Advance Auto CO";

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnLoginFromDDown();
		myEstesLoginPage.enterUnameAndpwd("smokenat", "smokenat");
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnAddBookFromMyEstes();
		myEstesAddressBookPage.clickOnUploadButton();
		myEstesUploadAddressBookPage.clickOnDownloadTemplate();
		testUtil.setHardWait(3000);
		String fileName = "AddressBook.csv";
		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		myEstesUploadAddressBookPage.verifyIsFileDownloaded(downloadPath, fileName);
		myEstesUploadAddressBookPage.verifyDownloadedFileName(downloadPath, fileName);

		testUtil.deleteARowInCsvFile(TestUtil.getCurrentWorkingPath() + "\\Downloads\\AddressBook.csv");

		String csvFilePath = TestUtil.getCurrentWorkingPath() + "\\Downloads\\AddressBook.csv";

		testUtil.setHardWait(2000);

		testUtil.writeToCsvFile(csvFilePath, "Advance Auto CO", "Josh", "Hense", "", "804", "353", "1900", "1200",
				"804", "343", "1600", "1501 N. Hamilton St", "", "Richmond", "VA", "23230", "", "US",
				"qatest@estes-express.com");

		myEstesUploadAddressBookPage.selectAddAddrToYCurAddBookRadioButton();

		testUtil.setHardWait(2000);
		myEstesUploadAddressBookPage.clickOnBrowseButton(csvFilePath);

		myEstesUploadAddressBookPage.clickOnUploadButton();

		testUtil.setHardWait(2000);
		myEstesUploadAddressBookPage.validateTheCompanyNameDisplayed(companyNme);

		myEstesUploadAddressBookPage.clickOnDeleteButton(companyNme); // Added this step to delete the added account so
																		// it wont
																		// impact the script in execution.
		testUtil.setHardWait(2000);
		myEstesUploadAddressBookPage.deleteFilesFromFolder(downloadPath, fileName); // Adding this step to cleanup the
																					// added data
	}

	// dp2-287
	/**
	 * This test passed on 6/29/22
	 * 
	 * Address Book - Verify the user can add Canadian address entry
	 */
	@Test(enabled = true, priority = 3) // Cannot select the province

	public void executeQZ_6083() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnAddressBookLink();
		testUtil.setHardWait(2000);
		myEstesAddressBookPage.clickOnAddNewAddressButton();
		myEstesAddNewAddressPage.validateTitle();

		myEstesAddNewAddressPage.enterCompanyName();
		myEstesAddNewAddressPage.selectCountry("Canada");
		myEstesAddNewAddressPage.enterAddress();

		String addr = myEstesAddNewAddressPage.recordAddress();
		myEstesAddNewAddressPage.enterCityName();
		myEstesAddNewAddressPage.selectState("ON");
		myEstesAddNewAddressPage.enterPostalCode();
		myEstesAddNewAddressPage.enterPhoneNumber("4165550130");
		myEstesAddNewAddressPage.clickOnSaveButton();
		myEstesAddNewAddressPage.validaAddressDisplay(addr);
	}

	/*
	 * Address Book - Verify the user can search for information located in the
	 * address book.
	 */
	@Test(enabled = true, priority = 4)

	public void executeQZ_3289() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnAddressBookLink();
		testUtil.setHardWait(1000);
		myEstesAddressBookPage.enterValueToSearchField("CCI Thermal");
		myEstesAddressBookPage.validateContactNameDisplay("CCI Thermal");
		myEstesAddressBookPage.enterValueToSearchField("1800 E");
		myEstesAddressBookPage.validateAddressDisplay("1800 E");
		myEstesAddressBookPage.enterValueToSearchField("ANCHORA");
		myEstesAddressBookPage.validateLocationDisplay("ANCHORAGE");

	}



	// dp2-283
	/*
	 * Address Book - Verify user can Edit Address Entry
	 */
	@Test(enabled = true, priority = 5)

	public void executeQZ_3260() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnAddressBookLink();
		myEstesAddressBookPage.clickOnEditIcon();
		testUtil.setHardWait(1000);
		myEstesEditAddressPage.validateTitle();
		myEstesEditAddressPage.enterFaxNumber();
		myEstesEditAddressPage.clickOnSaveButton();

	}

	
	
	/*
	 * Address Book - Verify user can Delete Address Entry
	 */

	@Test(enabled = true, priority = 6)

	public void executeQZ_3262() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnAddressBookLink();
		testUtil.setHardWait(1000);
		myEstesAddressBookPage.clickOnDeleteIcon();
		myEstesAddressBookPage.validateTitle();
		myEstesAddressBookPage.clickOnDeleteButton();
	}
	

}

