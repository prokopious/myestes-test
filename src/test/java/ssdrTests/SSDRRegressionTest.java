package ssdrTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import imageRetrievalPages.MyEstesHomePage;
import imageRetrievalPages.MyEstesImageRetrievalPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesBillOfLadingPage;
import myEstesPages.MyEstesClaimsPage;
import myEstesPages.MyEstesDensityCalculatorPage;
import myEstesPages.MyEstesFuelSurchargePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesShipmentTrackingPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesTerminalLookupPage;
import myEstesPages.MyEstesTransitTimeCalculatorPage;
import testBase.TestBase;
import util.CreateFreightBill;
import util.ReserveAFreightBill;

public class SSDRRegressionTest extends TestBase {
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();

	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	MyEstesDensityCalculatorPage myEstesDensityCalculatorPage = new MyEstesDensityCalculatorPage();
	MyEstesTerminalLookupPage terminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTransitTimeCalculatorPage myEstesTransitTimeCalculatorPage = new MyEstesTransitTimeCalculatorPage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	MyEstesClaimsPage myEstesClaimsPage = new MyEstesClaimsPage();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();

	MyEstesImageRetrievalPage myEstesImageRetriPage = new MyEstesImageRetrievalPage();
	MyEstesFuelSurchargePage myEstesFuelSurchargePage = new MyEstesFuelSurchargePage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();

	CreateFreightBill createFreightBill = new CreateFreightBill();
	ReserveAFreightBill reserveAFreightBill = new ReserveAFreightBill();
	
	/******************************* TESTS *******************************/

	/*
	 * SSDR - Verify the error message is received if Tracking Number and
	 * Destination Zip don't match
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_3329() throws Exception {

		myEstesHomePage.clickOnManageTab();
		myEstesHomePage.clickOnDocumentRetrievalAndViewingFromManage();

		myEstesImageRetriPage.enterTrackingNum("171-0372189");
		myEstesImageRetriPage.enterDestinationZip("12077");
		myEstesImageRetriPage.clickOnBillOfLading();
		myEstesImageRetriPage.clickOnEmailRadioButton();
		myEstesImageRetriPage.enterEmailAddresses("qatest@estes-express.com");
		myEstesImageRetriPage.clickOnSubmitButton();
		myEstesImageRetriPage.verifyMismatchOfTrackingNumberAndZipErrorMessage();

	}

	/*
	 * SSDR - Verify the error message is received when all fields are left blank
	 */

	@Test(enabled = true, priority = 2)
	public void executeQZ_3308() throws Exception {

		// Actions action= new Actions(driver); //THIS METHOD IS TO OPEN DEV TOOL
		// action.sendKeys(Keys.F12).build().perform(); /
		myEstesHomePage.clickOnManageTab();
		myEstesHomePage.clickOnDocumentRetrievalAndViewingFromManage();

		testUtil.setHardWait(2000);
		myEstesImageRetriPage.clickOnSubmitButton();
		testUtil.setHardWait(2000);
		myEstesImageRetriPage.verifyErrorMessageFromRequirdFields();
	}

	
	// THIS IS IMAGE RETRIVAL AND VIEWING TEST
	/*
	 * Image Retrieval & Viewing - Verify error message displays when required
	 * fields are left blank.
	 */

	@Test(enabled = true, priority = 3)
	public void executeQZ_7155() throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		
		myEstesHomePage.clickOnManageTab();

		myEstesHomePage.clickOnDocumentRetrievalAndViewingFromManage();
		myEstesImageRetriPage.selectBOL("Bill of Lading");

		myEstesImageRetriPage.clickOnSearchButton();
		
		myEstesImageRetriPage.verifyThisfieldIsRequiredMess();

		myEstesImageRetriPage.clickOnBOLRadioButton();
		myEstesImageRetriPage.clickOnSearchButton();
		
		myEstesImageRetriPage.verifyThisfieldIsRequiredMess();

		myEstesImageRetriPage.selectDeliveryReceipt("Delivery Receipt");
		myEstesImageRetriPage.clickOnPORadioButton();
		myEstesImageRetriPage.clickOnSearchButton();
		
		myEstesImageRetriPage.verifyThisfieldIsRequiredMess();

		myEstesImageRetriPage.clickOnInterlinePORadioButton();
		myEstesImageRetriPage.clickOnSearchButton();
		
		myEstesImageRetriPage.verifyThisfieldIsRequiredMess();

		myEstesImageRetriPage.clickOnFaxRadioButton();
		myEstesImageRetriPage.clickOnSearchButton();
		
		myEstesImageRetriPage.verifyThisfieldIsRequiredMess();

	}

}
