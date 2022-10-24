package eNetTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetDigitalPictureViewingPage;
import eNetPages.ENetExchangeRateMaintenancePage;
import eNetPages.ENetHomePage;
import eNetPages.ENetImageLookupPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetRateQuoteUploadPage;
import eNetPages.ENetStatementRetrieverPage;
import eNetPages.ENetTimeCriticalRateQuotePage;
import eNetPages.ENetUndeliveredFrieghtPage;
import testBase.TestBase;
import util.TestUtil;

public class ENetSmokeTest extends TestBase {

	// poms
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetUndeliveredFrieghtPage eNetUndeliveredFrieght = new ENetUndeliveredFrieghtPage();
	ENetExchangeRateMaintenancePage  eNetExchangeRateMaintenancePage= new ENetExchangeRateMaintenancePage();
	ENetRateQuoteUploadPage eNetRateQuoteUploadPage = new ENetRateQuoteUploadPage();
	ENetStatementRetrieverPage	eNetStatementRetrieverPage= new ENetStatementRetrieverPage();
	ENetTimeCriticalRateQuotePage eNetTimeCriticalRateQoutePage= new ENetTimeCriticalRateQuotePage();
	ENetImageLookupPage eNetImageLookupPage= new ENetImageLookupPage();
	ENetDigitalPictureViewingPage eNetDigitalPictureViewingPage = new ENetDigitalPictureViewingPage();
	
	
	/******************************METHODS****************************/
	
	// Hold according to JIRA
	/**
	 * eNet-Undelivered Freight
	 */
	@Test(enabled = true, priority = 1)

	public void executeQZ_8121() {
		// login
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// click on applications
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnUndeliveredFreight();
		eNetUndeliveredFrieght.clickEstesNorthEast();
		eNetUndeliveredFrieght.clickOnManager();
		eNetUndeliveredFrieght.clickOnTerminal();
		String root = driver.getWindowHandle();
		// get image
		eNetUndeliveredFrieght.clickOnImageY();
		switchToRoot(root);
		switchFrame();
		// click on pdf
		eNetUndeliveredFrieght.clickOnPDF();
		switchToRoot(root);
		switchFrame();

		eNetUndeliveredFrieght.clickOnExcel();
		// logout
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButtonFreight();

	}

	public void switchToRoot(String root) {
		driver.switchTo().window(root);
	}

	public void switchFrame() {
		driver.switchTo().frame(0);
	}

	/*
	 * Rate Quote Upload - Verify an error message displays if the select to upload
	 * file is not in CSV format
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_7124()
			throws InterruptedException {
		// Step#1 : Open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		// Step#2 : Login to *eNet* application using the following credentials:
		// User ID: qaenet01, Password: qaenet01
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		// User is brought to the eNet Homepage.
		eNetHomePage.verifyPageTitleQaenet01();
		testUtil.setHardWait(1000);
		// Step#3 : From *eNet* home page top navigation menu,click *Applications*
		eNetHomePage.clickOnCustomerServiceLink();

		// Step#4 : From the *Applications* page, find the *Customer Service*
		// applications list, and click *Rate Quote Upload* link
		eNetHomePage.clickOnRateQuoteUploadLink();
		// The Time Critical Rate Quote tool is displayed.
		eNetRateQuoteUploadPage.switchToMainFrame();
		eNetRateQuoteUploadPage.verifyPageTitle();
	
		// Step#5 : From the *Rate Quote Upload* page,click *Choose File* button
		// eNetRateQuoteUploadPage.clickOnChooseFile();
		// A dialog box opens
		// Step#6 : Select a file that is not in .CSV format and click the *Open* button
		eNetRateQuoteUploadPage.uploadNonCSVfile();

		// Step#7 : in the *E-mail Results To:* field enter a valid email address:
		// EITQATest@Estes-Express.com
		eNetRateQuoteUploadPage.enterEmailAddress("EITQATest@Estes-Express.com");

		// Step#8 : Click the *Submit* buttonifram
		eNetRateQuoteUploadPage.clickOnSubmit();
		// Verify an error message displayed: *Please choose a csv file to upload*
		eNetRateQuoteUploadPage.verifyNonCSVFileErr();

	}
	
	/*
	 * 01.08.01.01 - Applications_Freight Billing_Hazmat Loading Guide [ALM UFT REG TEST ID: 1779 ]
	 */
	@Test(enabled = true, priority = 3)

	public void executeQZ_7260() throws Exception {

		ENetLoginPage eNetLoginPage = new ENetLoginPage();
		ENetHomePage eNetHomePage = new ENetHomePage();
		ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();

		//Step 1: From the Internet Explorer Browser, Open the following link:
		//https://estesexpress.atlassian.net/browse/QZ-6808
		//Step 2: Login with the following credentials:
		//User ID: qaenet02, Password: qaenet02
		//Step 3: Press <Enter>
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
	
		//Step 4: From main page, click on the APPLICATIONS folder VIEW ALL icon
		eNetHomePage.clickOnApplicationsTab();
		testUtil.setHardWait(2000);

		//Step 5: From under the Freight Billing tab click on the following link < Freight Billing_Hazmat Loading Guide link>
		eNetApplicationsPage.clickOnHazmatLoadingGuide();
		testUtil.setHardWait(2000);

		//Step 6: Verify Hazmat Loading Guide screen is displayed
		driver.switchTo().frame("mainpage");
		boolean title = driver.findElement(By.xpath("//td[contains(text(),'Hazmat Loading Guide')]")).isDisplayed();
		System.out.println(title);
		Assert.assertTrue(title, "Verify screen title");

		//Step 7: Logout and close browser.
		eNetHomePage.clickOnLogout();
		//Confirm logout
	    eNetHomePage.clickOnLogoutButtonFreight();
	}

	
	/*
	 * 01.01.00.19 - Exchange Rate Maintenance
	 */		
	@Test(enabled = true, priority = 4)
	public void executeQZ_9867() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From eNet Home page, click on the APPLICATIONS folder VIEW ALL icon
		eNetHomePage.clickViewAll("Applications");

		// step4:From All APPLICATION folders display,
		// Under ACCOUNTS RECEIVABLE click on Exchange Rate Maintenance
		eNetApplicationsPage.clickExchangeRateMaintenance();
		testUtil.switchToFrame("mainpage");

		/*
		 * //step5: From the Exchange Rate Maintenance screen which displays Exchange
		 * Dates with respectiive rates, Click on the icon next to an Exchange Date
		 */
		eNetExchangeRateMaintenancePage.validatePageTitle("Exchange Rate Maintenance");
		eNetExchangeRateMaintenancePage.verifyTableDisplayed();
		String date = "2017-05-18";
		String inbound = eNetExchangeRateMaintenancePage.getMainInboundValue(date);
		eNetExchangeRateMaintenancePage.clickOnExchangeRateEdit(date);

		// Exchange rate information is displayed for the specific date selected.
		eNetExchangeRateMaintenancePage.validateExRateTableDisplayed("Exchange Rate");
		String date2 = eNetExchangeRateMaintenancePage.getExchangeDate();
		TestUtil.verifyText(date2, date);

		// step6: From the Exchange Rate Maintenance screen, change the Inbound percent
		// rate to 1.00 and click Submit
		String inbound2 = eNetExchangeRateMaintenancePage.getInboundValue();
		TestUtil.verifyText(inbound, inbound2);

		// User is allowed to update the Inbound Percent field
		eNetExchangeRateMaintenancePage.enterInboundValue("1.00");
		eNetExchangeRateMaintenancePage.clickOnSubmitBtn();

		eNetExchangeRateMaintenancePage.validatePageTitle("Exchange Rate Maintenance");
		String inboundUpdated = eNetExchangeRateMaintenancePage.getMainInboundValue(date);
		TestUtil.verifyText(inboundUpdated, "1.00000");

		// step7: log out and close browser
		eNetStatementRetrieverPage.clickOnLogout();
		eNetStatementRetrieverPage.clickOnLogoutIntranet();
	}

	/*
	 * eNet - Time Critical - Verify when origin or destination is Puerto Rico the
	 * Guaranteed LTL Standard Transit and Exclusive Use rates are not calculated
	 * and a 'Contact Me' link is displayed
	 */
	
	@Test(enabled = true, priority =5)
	public void executeQZ_7049()
			throws InterruptedException {

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Login into eNet
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		testUtil.setHardWait(2000);
		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();
		testUtil.setHardWait(2000);
		// Step 4: Click on Time Critical Rate Quote link
		eNetApplicationsPage.clickOnTimeCriticalLink();
		testUtil.setHardWait(2000);
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step 5: Enter Orihin for Puerto Rico - 00603
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterAccountNumber("9451455");
		eNetTimeCriticalRateQoutePage.enterEmail();
		eNetTimeCriticalRateQoutePage.enterPhoneNumber();
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		eNetTimeCriticalRateQoutePage.enterOriginZip("00603");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("20001");
		eNetTimeCriticalRateQoutePage.setTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass("55");
		eNetTimeCriticalRateQoutePage.enterPieces("10");
		eNetTimeCriticalRateQoutePage.enterPiecesType("BAG");
		eNetTimeCriticalRateQoutePage.enterWeight("100");
		eNetTimeCriticalRateQoutePage.enterLength("40");
		eNetTimeCriticalRateQoutePage.enterWidth("5");
		eNetTimeCriticalRateQoutePage.enterHeight("10");

		// Step 6: Select Submit
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();

		// Step 7: Verify Cintact Me is displayed for Guaranteed options
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 5PM");

		/// Click logout link
		eNetHomePage.clickOnLogout();

		// Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();

	}
	/**
	 * 
	 * Cargo Claims Imaging View
	 * @throws InterruptedException
	 */
	
	@Test(enabled = true, priority = 6)
	public void executeQZ_6882() throws InterruptedException {

		// step1:Open the following link:<http://enetqa.estesinternal.com/>
		driver.get(url2);

		// step2: Log into *eNet*
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From the eNet home page, Click on the Applications folder VIEW ALL
		eNetHomePage.clickViewAll("Applications");

		// step4: From the Applications page, click the following link: Cargo Claims Imaging
		eNetApplicationsPage.clickOnCargoClaimsImaging();

		// step5: On Cargo claims Image Lookup screen enter the values and Click Search
		eNetImageLookupPage.validatePagetitle("Image Lookup");
		eNetImageLookupPage.selectDocType("CARGO CLAIMS DOCUMENTS (SCANNED)");
		eNetImageLookupPage.enterLowScanDt("04/01/2019");
		eNetImageLookupPage.enterHighScanDt("04/05/2019");
		eNetImageLookupPage.enterClaimNumber("1405842");
		eNetImageLookupPage.ClickOnSubmit();
		
		// Verify Search result table displays
		eNetImageLookupPage.validateSearchResults();

		// step6: On Search result table, Click on checkbox and click image viewing icon(next to checkbox)
		eNetImageLookupPage.clickOnCheckBox("1405842");
		eNetImageLookupPage.clickOnViewDocumentIcon("1405842");

		// Validate Image file displays in a new tab and verify displayed claim number
		testUtil.switchToWindow(1);
		testUtil.setHardWait(2000);
		
		eNetImageLookupPage.verifyImageDisplayed();
		String number = eNetImageLookupPage.getClaimNum();
		TestUtil.verifyText(number, "1405842");
		
		//step7: Navigate back to Cargo Claims Image Lookup page and enter different data and Click Search
		testUtil.switchToParentWindow();

		eNetImageLookupPage.validatePagetitle("Image Lookup");
		eNetImageLookupPage.selectDocType("FREIGHT INSPECTION REPORT");
		eNetImageLookupPage.enterPro("1768200");
		eNetImageLookupPage.enterOriginalTerminal("027");
		eNetImageLookupPage.ClickOnSubmit();

		// Verify Search result table displays
		eNetImageLookupPage.validateSearchResults();

		// step8: On Search result table,
		// Click on checkbox and click image viewing icon(next to checkbox)
		eNetImageLookupPage.selectCheckbox(2);
		eNetImageLookupPage.clickViewIcon(2);

		// Validate Image file displays in a new tab and verify displayed Pro number

		testUtil.switchToWindow(1);
		testUtil.setHardWait(5000);
		
		eNetImageLookupPage.verifyImageIsdisplayed();
		String num = eNetImageLookupPage.getProNum();
		TestUtil.verifyText(num, "1768200");

		// step9: Logout and close eNet application
		testUtil.switchToParentWindow();

		// Click logout link
		eNetHomePage.clickOnLogout();

		// Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();
	    
		
	}

	/**
	 * 
	 * WIP
	 * 
	 * _eNet Digital Picture Viewing Search
	 * @throws InterruptedException
	 * 
	 */
	@Test(enabled = false, priority = 7)
	public void executeQZ_6885() throws InterruptedException {

		// step1: Open within the Internet Explorer Browser, the following link:
		driver.get(url2);

		// step2: Login the application with the following credentials:
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From the eNet home page, Click on the Applications folder VIEW ALL
		eNetHomePage.clickViewAll("Applications");

		// step4: From the Applications page , click the following link: Digital Picture Viewing
		eNetApplicationsPage.openDigitalPictureViewing();

		// Verify list of Document types displayed
		testUtil.switchToFrame("mainpage");
		eNetDigitalPictureViewingPage.verifyDocTypesDisplayed();

		// step5: Null
		// step6:F rom Doc Type do the following: Select 'Account SetUp'
		eNetDigitalPictureViewingPage.selectDocType("ACCT");
		
		// Verify Document Type Search By fields are displayed
		eNetDigitalPictureViewingPage.verifyTableDisplayed();

		// step7: Enter following values in Document type search fields, Accoun Number: 5032938 Click Submit
		eNetDigitalPictureViewingPage.enterEfwAccNum("5032938");
		eNetDigitalPictureViewingPage.clickSubmit();
		testUtil.setHardWait(3000);
		// Verify Search result open in a new tab
		//testUtil.switchToFrame("mainpage");
		eNetDigitalPictureViewingPage.verifyDocIDisDisplayed();

		// step8: Record Document ID and click on Document ID link
	//	String id = eNetDigitalPictureViewingPage.getDocID();
//		testUtil.setHardWait(1000);
//		eNetDigitalPictureViewingPage.clickOnDocID();

		// Verify user navigates to new tab and Compare the current Document ID with recorded Document ID
//		String id2 = eNetDigitalPictureViewingPage.getDocumentIDAfterLinkOpened("Document ID");
	//	TestUtil.verifyText(id, id2);

		// step9: Navigate back to Application tab and select Imaging-->Digital Picture Viewing link
//		eNetDigitalPictureViewingPage.clickOnApplicationsTab();
//		eNetApplicationsPage.openDigitalPictureViewing();

		// step10: Change Document types and verify the selection box opens
//		testUtil.switchToFrame("mainpage");
//		eNetDigitalPictureViewingPage.selectDocType("CINV");
//		eNetDigitalPictureViewingPage.verifyTableDisplayed();
		
		// Click logout link
//		eNetHomePage.clickOnLogout();

		// Confirm by clicking logout button
//		eNetHomePage.clickOnLogoutButton();

	}


    @Test(enabled = false, priority = 7)
    public void executeQZ_0() throws InterruptedException {
}
}
