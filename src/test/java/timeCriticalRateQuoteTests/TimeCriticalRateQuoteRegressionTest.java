package timeCriticalRateQuoteTests;

import static org.testng.Assert.assertTrue;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.jagacy.SessionVt;

import billOfLadingPages.MyEstesBillOfLadingPage;
import eNetPages.ENetAgedARPage;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetFoodWarehouseDistributionCenterMaintenancePage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetMyEstesManagementToolPage;
import eNetPages.ENetMyEstesUserDetailPage;
import eNetPages.ENetQuoteHistoryLookupPage;
import eNetPages.ENetRateRetrieverPage;
import eNetPages.ENetTimeCriticalRateQouteHistoryPage;
import eNetPages.ENetTimeCriticalRateQuotePage;
import eNetPages.ENetVTLQouteExceptionQueuePage;
import eNetPages.ENetVTLRateQuotePage;
import eNetPages.ENetVTLTableMaintenancePage;
import jagacyVT.screens.CommandEntryScreen;
import jagacyVT.screens.DisplayCommentsScreen;
import jagacyVT.screens.DisplayRequestServiceInfoScreen;
import jagacyVT.screens.EstesExpressLinesLaneFileDisplayScreen;
import jagacyVT.screens.GoldMedalMainMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LaneRoutingInformationScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.WorkWithCommentsScreen;
import jagacyVT.screens.WorkWithRequestCommentsInstructionScreen;
import jagacyVT.screens.WorkWithRequestScreen;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesPickupRequestPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQouteAccountSearch;
import rateQuotePages.RateQouteQuoteHistoryPage;
import rateQuotePages.RateQouteRateEstimatePage;
import rateQuotePages.RateQouteRateRequestPage;
import rateQuotePages.RateQuotePage;
import rateQuotePages.RatesPage;
import rateQuotePages.VolumeTruckloadRateQuote;
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;
import util.SQLDataList;
import util.TestUtil;

public class TimeCriticalRateQuoteRegressionTest extends TestBase {

	private Logger logger = Logger.getLogger(TimeCriticalRateQuoteRegressionTest.class);

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	RateQouteRateEstimatePage ltlRateEstimatePage = new RateQouteRateEstimatePage();
	RateQuotePage ltlRateQuotePage = new RateQuotePage();
	RatesPage ratesPage = new RatesPage();
	RateQouteRateRequestPage ltlRateRequestPage = new RateQouteRateRequestPage();
	VolumeTruckloadRateQuote vtlRateQuote = new VolumeTruckloadRateQuote();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQouteQuoteHistoryPage ltlQuoteHistoryPage = new RateQouteQuoteHistoryPage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	RateQouteAccountSearch ltlAccountSearch = new RateQouteAccountSearch();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetVTLQouteExceptionQueuePage eNetVTLQouteExceptionQueuePage = new ENetVTLQouteExceptionQueuePage();
	ENetRateRetrieverPage eNetRateRetrieverPage = new ENetRateRetrieverPage();
	ENetLTLRateRequestPage eNetLTLRateRequestPage = new ENetLTLRateRequestPage();
	ENetTimeCriticalRateQuotePage eNetTimeCriticalRateQoutePage = new ENetTimeCriticalRateQuotePage();
	ENetVTLTableMaintenancePage eNetVTLTableMaintenancePage = new ENetVTLTableMaintenancePage();
	ENetFoodWarehouseDistributionCenterMaintenancePage eNetFoodWarehousePage = new ENetFoodWarehouseDistributionCenterMaintenancePage();
	ENetAgedARPage eNetAgedARPage = new ENetAgedARPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	MyEstesPickupRequestPage myEstesPickupRequestPage = new MyEstesPickupRequestPage();
	ENetMyEstesManagementToolPage eNetMyEstesManagementToolPage = new ENetMyEstesManagementToolPage();
	ENetMyEstesUserDetailPage eNetMyEstesUserDetailPage = new ENetMyEstesUserDetailPage();
	RateQouteQuoteHistoryPage QuoteHistoryPage = new RateQouteQuoteHistoryPage();
	ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
	ENetVTLRateQuotePage eNetVTLRateQuotePage = new ENetVTLRateQuotePage();
	ENetQuoteHistoryLookupPage eNetQuoteHistoryLookupPage = new ENetQuoteHistoryLookupPage();
	ENetTimeCriticalRateQouteHistoryPage eNetTimeCriticalRateQouteHistoryPage = new ENetTimeCriticalRateQouteHistoryPage();
	SQLDataList sqlDatalist = new SQLDataList();

	/*******************************
	 * Global Variable
	 *******************************/

	//getting current working directory
	String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";

	/******************************* TESTS *******************************/

	// this is run locally by Nithya

	/*
	 * Rate Quotes - Commodities - Verify record selected from the 'Commodity
	 * Library Modal' is saved properly.< dp2-2494>
	 */

	@Test(enabled = false, priority = 1)

	public void executeQZ_9722()
			throws InterruptedException {

		String accessorialName = "OVERLENGTH CHARGE-28.00' - 39.9'";

		// Launch enet app
		driver.get(url2);

		// Login to enet app
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// Click Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Click on Time Critical rate quote Link
		eNetApplicationsPage.clickOnTimeCriticalLink();

		// Enter contact and routing information
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterEmail();

		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900 x3170");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		testUtil.setHardWait(1000);
		eNetTimeCriticalRateQoutePage.enterOriginZip("23230");
		testUtil.setHardWait(1000);
		eNetTimeCriticalRateQoutePage.enterDestinationZip("30307");

		// Select Date
		eNetTimeCriticalRateQoutePage.setTodayDate();

		// Enter commodities in commodity line 1
		eNetTimeCriticalRateQoutePage.selectClass("50");
		eNetTimeCriticalRateQoutePage.enterPieces("1");
		eNetTimeCriticalRateQoutePage.enterPiecesType("SKID");
		eNetTimeCriticalRateQoutePage.enterWeight("700");
		eNetTimeCriticalRateQoutePage.enterLength("384");
		eNetTimeCriticalRateQoutePage.enterWidth("32");
		eNetTimeCriticalRateQoutePage.enterHeight("32");

		// Click on submit button
		eNetVTLQouteExceptionQueuePage.clickOnSubmitButton();

		testUtil.setHardWait(5000);
		// Verify Contact is displayed for all guaranteed services execept for Exclusive
		// use

		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 10AM");

		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 12PM");

		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 5PM");

		// Verify charges are displayed for Guaranteed Exclusive Use

		eNetTimeCriticalRateQoutePage.verifyChargesByServiceLevel("Guaranteed Exclusive Use");

		// select *LTL Standard Transit* charges (displays as a link)

		eNetTimeCriticalRateQoutePage.selectQouteByServiceLevel("TC", "LTL Standard Transit");

		// Verify *Overlength Freight (28.00' to 39.99')* is listed under charges

		eNetTimeCriticalRateQoutePage.verifyAccessorialIsDisplayedWithCharge(accessorialName);

		eNetHomePage.clickOnLogout();

		// Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();

	}

	/**
	 * This test passed on 6/27/22
	 * 
	 * Enet TC Rate Request - LONG28 Accessorial automatically added when DIMS between 20.00 and 27.99 feet
	 * 
	 * @throws InterruptedException
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_7074()
			throws InterruptedException {
		// Step#1 : Log into eNet QA with the following credentials and URL:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		// UserID: qaenet02
		// Password: qaenet02
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#2 : From the home page under Applications, click on Customer Service.
		// Click Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#3 : Enter the following account code and commodity info and populate the
		// remaining Required Fields.
		// Account code: 5068692,Class: 50,Pieces: 1,Piece Type: Skid,Total Weight:
		// 700,Length: between 240 and 335, inclusive,Width: 32,Height: 32
		// Data displays as entered
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterEmail();
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterOriginZip("23230");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("30362");
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676583");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		eNetTimeCriticalRateQoutePage.setTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass("50");
		eNetTimeCriticalRateQoutePage.enterPieces("1");
		eNetTimeCriticalRateQoutePage.enterPiecesType("SKID");
		eNetTimeCriticalRateQoutePage.enterWeight("700");
		// Fetching random number in between 240 & 335
		Random rand = new Random();
		int randomNum = rand.nextInt((335 - 240) + 1) + 240;
		String length = String.valueOf(randomNum);
		logger.info("Random number in between 240 & 335 : " + length);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth("32");
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676583");
		eNetTimeCriticalRateQoutePage.enterHeight("32");
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();

		// Step#4 : Click Submit
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Quote is displayed and all Guaranteed options except Exclusive Use show
		// "Contact Me"
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		eNetTimeCriticalRateQoutePage.verifyChargesByServiceLevel("Guaranteed Exclusive Use");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("LTL Standard Transit");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 5PM");

		// Step#5 : Click on the hyperlink for the LTL Standard Transit rate
		eNetTimeCriticalRateQoutePage.clickOnLTLStandardTransitRate();
		// The Quote Details page is displayed
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step#6 : Verify that the accessorial change for *OVERLENGTH CHARGE-20.00' -
		// 27.9' * is listed under Charges.
		eNetTimeCriticalRateQoutePage.verifyOverLengthCharge();

		// Step#7 : Logout
		eNetTimeCriticalRateQoutePage.clickLogout();
		eNetTimeCriticalRateQoutePage.clickLogoutConfirmation();

	}

	/**
	 * 
	 * 
	 * eNet - Time Critical - Exclusive Use can handle more than 10 commodities
	 */

	@Test(enabled = true, priority = 3)

	public void executeQZ_7051() throws InterruptedException {
		// Step#1 : Login to Enet using the following URL:

		driver.get(url2);
		/*
		 * eNetLoginPage.enterUserID(username12);
		 * eNetLoginPage.enterUserPassword(password12);
		 * eNetLoginPage.clickOnLoginButton();
		 */
		eNetLoginPage.login(username12, password12);

		// Step#2 : From the eNet Home Page, select 'Customer Service' link in the
		// 'Application' section.
		// Customer Service link when selected contains additional sub sections based on
		// user permissions.
		eNetHomePage.clickOnCustomerServiceLink();

		// Step#3 : Select 'Time Critical Rate Quote' link.
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// User is brought to the 'Time Critical Rate Quote' screen.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#4 : Select more than 10 commodities.
		// Scroll to Add commodity button
		for (int i = 0; i < 7; i++) {
			logger.info("Add commodity.");
			eNetTimeCriticalRateQoutePage.scrollToAddCommodity();
			eNetTimeCriticalRateQoutePage.clickOnAddCommodityButton();
		}

		// Step#5 : Enter data in required fields.
		eNetTimeCriticalRateQoutePage.scrollToPageTitle();
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterEmail();
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterOriginZip("75432");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("75431");
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676583");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		eNetTimeCriticalRateQoutePage.enterLastDateofMonth();
		eNetTimeCriticalRateQoutePage.setAvailableByTime("11", "00", "AM");
		eNetTimeCriticalRateQoutePage.setCloseAtTime("8", "00", "PM");
		Map<String, ArrayList<String>> commodityMap = eNetTimeCriticalRateQoutePage.fillCommoditiesDetails();
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();

		// Step#6 : Click Submit button.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		//created metod with out frame
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step#7 : Select an Exclusive Use option with charges.
		eNetTimeCriticalRateQoutePage.clickExclusiveUseChargesOption();
		eNetTimeCriticalRateQoutePage.verifyFeeSummary(commodityMap);

		// Step#8 : Select the 'Terms of Service' checkbox under 'Quote Actions.'
		eNetTimeCriticalRateQoutePage.selectTermsofServices();

		// Step#9 : Select <Book Shipment> button.
		eNetTimeCriticalRateQoutePage.clickBookShipmentButtonEnet();
		testUtil.setHardWait(1000);
		//created metod with out frame
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step#10 : Logout
		eNetTimeCriticalRateQoutePage.clickLogout();
		eNetTimeCriticalRateQoutePage.clickLogoutConfirmation();

	}

	/**
	 * 
	 * This test passedon 6/27/22
	 * Enet TC Rate Request - LONG53 Accessorial automatically added when DIMS  exceeds 40 feet 
	 * 
	 * @throws InterruptedException
	 */
	@Test(enabled = true, priority = 4)

	public void executeQZ_7075()
			throws InterruptedException {
		// Step#1 : From Internet Explorer, Log into eNet QA with the following
		// credentials and URL:
		// https://estesexpress.atlassian.net/browse/QZ-6808 UserID: qaenet02, Password:
		// qaenet02
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#2 : From the home page under Applications, click on Customer Service.
		// Click Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// Validate eNetTimeCriticalRateQoute Page
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#3 : Enter data in all Required Fields for an Account Specific rate quote
		// using the following account and commodity information:
		// Account Code: 5068692
		/*
		 * Class: 50, Pieces: 1 Piece Type: Skid Total Weight: 700 Length: 481 Width: 32
		 * Height: 32
		 */
		eNetTimeCriticalRateQoutePage.enterFullName();
		String fullName = eNetTimeCriticalRateQoutePage.getFullNamestr();
		eNetTimeCriticalRateQoutePage.enterEmail();
		String emailId = eNetTimeCriticalRateQoutePage.getEmail();
		String accountNo = "5068692", originZip = "23230", destinatonZip = "30362", weight = "700", length = "481",
				width = "32", height = "32", phoneNo = "20212345676513", role = "Shipper", term = "Pre-paid",
				classes = "50", pieces = "1", pieceType = "SKID";
		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNo);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();

		// Step#4 : Click Submit.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Quote is displayed and all Guaranteed options except Exclusive Use show
		// "Contact Me"
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		eNetTimeCriticalRateQoutePage.verifyChargesByServiceLevel("Guaranteed Exclusive Use");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("LTL Standard Transit");
		eNetTimeCriticalRateQoutePage.verifyContactMeByServiceLevel("Guaranteed LTL Standard Transit: 5PM");

		// Step#5 : Click on the hyperlink for the LTL Standard Transit rate
		eNetTimeCriticalRateQoutePage.clickOnLTLStandardTransitRate();
		// The Quote Details page is displayed
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step#6 : Verify that the accessorial change for *OVERLENGTH CHARGE - 40' OR
		// GREATER* is listed under Charges.
		eNetTimeCriticalRateQoutePage.verifyOverLengthCharge40();

		// Step#7 : Log out
		eNetTimeCriticalRateQoutePage.clickLogout();
		eNetTimeCriticalRateQoutePage.clickLogoutConfirmation();
		// testUtil.setHardWait(40000);

	}

	// this test has a do not automate me label in JIRA
	/**
	 * This test passed on 6/27/22
	 * 
	 * ENet TC Rate Request
	 */
	@Test(enabled = true, priority = 5)

	public void executeQZ_7059() throws InterruptedException {
		// Step#1 : Open within the Internet Explorer Browser, the following link:
		// http://enetqa.estes-express.com/login.jsp
		driver.get(url2);

		// Step#2 : Login with the following credentials:
		// User ID: qaenet02, Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// Step#3 : From the Applications page, under the Customer Service section,
		// click the following link:
		// Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#4 : From the Time Critical Rate Quote page, enter data in all Required
		// Fields for Base Rate. Click Submit.
		// Origin Zip: 23230, Destination Zip: 30362, Pickup Date: >current date, Class:
		// 50, Pieces: 5, Piece Type: Bale,
		// Total Weight: 500, Length: 32, Width: 32
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterEmail();
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterOriginZip("23230");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("30362");
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676583");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		eNetTimeCriticalRateQoutePage.setTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass("50");
		eNetTimeCriticalRateQoutePage.enterPieces("5");
		eNetTimeCriticalRateQoutePage.enterPiecesType("BALE");
		eNetTimeCriticalRateQoutePage.enterWeight("500");
		eNetTimeCriticalRateQoutePage.enterLength("32");
		eNetTimeCriticalRateQoutePage.enterWidth("32");
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676583");
		eNetTimeCriticalRateQoutePage.enterHeight("10");
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();

		// Step#5 : From the Time Critical Rate Quote Select Rate to get quote # page,
		// Click on the Guaranteed by 12pm price.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		testUtil.setHardWait(2000);
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy12PMprice();
		// The Time Critical Summary Screen is displayed.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step#6 : From the Quote Actions section of the Time Critical Rate Quote page,
		// Verify that the Fax Quote button is displayed in the Quote Action Section.
		// Fax Quote button is displayed.
		eNetTimeCriticalRateQoutePage.checkFaxQuoteButton();

		// Step#7 : From the Quote Actions section of the Time Critical Rate Quote page,
		// Enter QA Fax number (804-420-3206) in the Fax Number field and Click Fax
		// Quote Button.
		eNetTimeCriticalRateQoutePage.enterFaxNumber("804-420-3206");
		eNetTimeCriticalRateQoutePage.clickFaxButton();

		// Step#8 : Verify fax is received.
		// Fax received
		// Step#9 : Verify fax content matches data that was input.
		// Step#10 : Log out and close browser.
		eNetTimeCriticalRateQoutePage.clickLogout();
		eNetTimeCriticalRateQoutePage.clickLogoutConfirmation();
	}

	/**
	 * This test passed on 7/25/22
	 * 
	 * eNet - Quote History - Verify Quote History Sort functionality
	 */
	@Test(enabled = true, priority = 6)

	public void executeQZ_7106() throws InterruptedException {
		// Step#1 : Open within the Internet Explorer Browser, the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// Step#2 : Login with the following credentials:Login with the following
		// credentials:
		// User ID: qaenet02, Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// Step#3 : From eNet home page screen, Click Customer Services link under
		// Application section
		eNetHomePage.clickOnCustomerServiceLink();

		// Step#4 : From eNet home page screen, Applications / Customer Service Menu,
		// click on the following link:
		// Rate Retriever
		eNetHomePage.clickOnRateRetrieverLink();
		// Verify LTL Rate request page has been displayed
		eNetLTLRateQuotePage.verifyPage();
		testUtil.setHardWait(2000);
		// Step#5 : From LTL Rate Quote, enter required information for LTL Rate Request
		// and click submit. Click on any displayed charges
		eNetLTLRateQuotePage.enterAccountNumber("9451455");
		eNetLTLRateQuotePage.selectRole("Shipper");
		eNetLTLRateQuotePage.selectTerm("Pre-paid");
		eNetLTLRateQuotePage.enterOriginZip("96001");
		eNetLTLRateQuotePage.enterDestinationZip("35020");
		eNetLTLRateQuotePage.selectClass("55");
		eNetLTLRateQuotePage.enterWeight("1000");
		eNetLTLRateQuotePage.clickOnSubmitButton();
		// Verify Quote number has been displayed
		// Note: Capture the Quote number and shipping information
		eNetLTLRateRequestPage.verifyPage();
		String quoteNum = eNetLTLRateRequestPage.recordQuoteNo();

		// Step#6 : From LTL Rate Quote, click on Home tab --> Customer Services link
		// under Application section
		eNetLTLRateRequestPage.clickOnHomeTab();

		// Step#7 : From eNet home page screen, under Application section click on the
		// following link:
		// Customer Services
		eNetHomePage.clickOnCustomerServiceLink();

		// Step#8 : From eNet home page screen under Application section on the Customer
		// Service menu, click on the following link:
		// Quote History Lookup
		eNetHomePage.clickQuoteHistoryLookup();
		eNetQuoteHistoryLookupPage.verifyPage();

		// Step#9 : From Quote History page, enter Date range and click Search
		eNetQuoteHistoryLookupPage.enterFromDate();
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		// Verify Search result has been displayed
		eNetQuoteHistoryLookupPage.verifyResultOccurred();

		// Step#10 : From the search result do the following task: Click on Quote# and
		// verify records has been sorted in Descending order
		eNetQuoteHistoryLookupPage.clickOnQuoteCol();
		eNetQuoteHistoryLookupPage.verifyQuoteHistoryColumnSortedAsc();

		// Step#11 : From the search result do the following task: Click Quote# again to
		// sort the records in Ascending order
		eNetQuoteHistoryLookupPage.clickOnQuoteCol();
		eNetQuoteHistoryLookupPage.verifyQuoteHistoryColumnSortedDsc();

		// Step#12 : Logout and close the application
		eNetQuoteHistoryLookupPage.clickLogout();
		eNetQuoteHistoryLookupPage.clickLogoutConfirmation();

	}

	/**
	 * this test failed due to screen not being displayed
	 * 
	 * ENet TC Rate Request - Quote Request Data Written to GMS
	 */
	
	@Test(enabled = true, priority = 7)

	public void executeQZ_7076() throws Exception {

		String[] originZipArr = { "90001", "23219", "99205" };
		String[] destinationZipArr = { "99205", "30362", "90001" };
		String[] phoneNoArr = { "20212345676511", "20212345676512", "20212345676513" };

		for (int i = 0; i < 3; i++) {
			// Step#1 : From Internet Explorer Log into eNet QA with the following
			// credentials and URL:
			// https://estesexpress.atlassian.net/browse/QZ-6808, UserID: qaenet02,
			// Password: qaenet02
			driver.get(url2);
			eNetLoginPage.enterUserID(username12);
			eNetLoginPage.enterUserPassword(password12);
			eNetLoginPage.clickOnLoginButton();
			// The ENet home page is displayed
			eNetHomePage.verifyPageTitleQaenet02();

			// Step#2 : From the eNet home page under Applications, click on Customer
			// Service. Click Time Critical Rate Quote.
			eNetHomePage.clickOnCustomerServiceLink();
			eNetHomePage.clickOnTimeCriticalRateQuoteLink();

			// Step#3 : From The Time Critical Rate Quote tool is display, enter data in all
			// Required Fields for a Base Rate
			// request using an Origination zip code in Richmond. (example - 23219) Select
			// multiple Accessorials and Commodities. Record data entered.
			//eNetTimeCriticalRateQoutePage.switchToMainFrame();
			eNetTimeCriticalRateQoutePage.verifyPage();
			eNetTimeCriticalRateQoutePage.enterFullName();
			eNetTimeCriticalRateQoutePage.enterEmail();
			List<String> requestInfo = new ArrayList<String>();
			String accountNo = "5068692", weight = "700", length = "481", width = "32", height = "32", role = "Shipper",
					term = "Pre-paid", classes = "50", pieces = "1", pieceType = "SKID";
			requestInfo.add(0, accountNo);
			requestInfo.add(1, originZipArr[i]);
			requestInfo.add(2, destinationZipArr[i]);
			requestInfo.add(3, pieces);
			requestInfo.add(4, pieceType);
			eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
			eNetTimeCriticalRateQoutePage.enterOriginZip(originZipArr[i]);
			eNetTimeCriticalRateQoutePage.enterDestinationZip(destinationZipArr[i]);
			eNetTimeCriticalRateQoutePage.selectRole(role);
			eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNoArr[i]);
			eNetTimeCriticalRateQoutePage.selectTerm(term);
			eNetTimeCriticalRateQoutePage.setTodayDate();
			String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
			logger.info("Todays date : " + todayDate);
			requestInfo.add(5, todayDate);
			eNetTimeCriticalRateQoutePage.selectClass(classes);
			eNetTimeCriticalRateQoutePage.enterPieces(pieces);
			eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
			eNetTimeCriticalRateQoutePage.enterWeight(weight);
			eNetTimeCriticalRateQoutePage.enterLength(length);
			eNetTimeCriticalRateQoutePage.enterWidth(width);
			eNetTimeCriticalRateQoutePage.selectRole(role);
			eNetTimeCriticalRateQoutePage.enterHeight(height);
			eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
			eNetTimeCriticalRateQoutePage.isFreightStackable();
			eNetTimeCriticalRateQoutePage.selectWareHouse("5");
			eNetTimeCriticalRateQoutePage.enterComments("Estes");
			eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
			eNetTimeCriticalRateQoutePage.checkInsideDelivery();
			eNetTimeCriticalRateQoutePage.checkNotifyRequest();

			// Step#4 : Click Submit.
			eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
			// Time Critical Rate Quote page will be displayed with all possible rates
			eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

			// Step#5 : From Time Critical Rate Quote page, click on one of the Time
			// Critical rates returned. Record which service is selected.
			// NOTE: If no Time Critical quotes return, use the query in the Description to
			// find a zip code that returns data.
			String serviceLevel = "Guaranteed Exclusive Use";
			eNetTimeCriticalRateQoutePage.clickExclusiveUseChargesOption();
			// The Quote Summary screeen is displayed. Selected service level is recorded.
			eNetTimeCriticalRateQoutePage.checkServiceLevel(serviceLevel);

			// Step#6 : From The Quote Summary screen, record Quote #.
			// Quote # is recorded.
			String quoteNo = eNetTimeCriticalRateQoutePage.getQuote();
			requestInfo.add(6, quoteNo);

			// Step#7 : Record the quote pricing information.
			// Quote pricing information is recorded.
			String quotePrice = eNetTimeCriticalRateQoutePage.getQuotePrice();
			requestInfo.add(7, quotePrice);

			// Step#8 : Log into EXLAQA
			String userName = "PEACHDO";
			String password = "aug08y14";

			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);

			// Log into the iSeries QA environment : EXLAQA
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.logon(userName, password);
			// Step#9 : From EXLAQA Main Menu Screen display, enter "call GMR000" in the
			// command line and press enter.
			CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
			commandEntryScreen.enterCommand("call GMR000");

			// Step#10 : From The Gold Medal Main Selection Menu display, in the command
			// line enter "5" and Press Enter.
			GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
			goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();
			goldMedalMainMenuScreen.enterOption("5");

			// Step#11 : From The Work With Request Service Information screen display, find
			// the Quote Number recorded in Step 6,
			// enter 5 in the Opt field to the left of it, and click Enter.
			WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
			workWithRequestScreen.verifyWorkWithRequestInfoScreen();
			logger.info("Quote No : " + quoteNo);
			workWithRequestScreen.enterQuoteNumber(quoteNo);
			workWithRequestScreen.enterValueInOptField("5");

			// Step#12 : From The Display Request Service Information screen for the
			// selected quote display, verify that To, From, Pickup Date, Delivery Date,
			// and Number of Pieces matches data recorded in
			// Step 3.The fields will represent the "best" GM
			// code and time from the points file.
			DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(
					session);
			displayRequestServiceInfoScreen.verifyDisplayRequestScreen();

			displayRequestServiceInfoScreen.verifyServiceInformationDisplayed(requestInfo);
			// Step#13 : Hit Shift F8. (F20)
			displayRequestServiceInfoScreen.enterF20Key();

			// Step#14 : From The Comments screen display, Verify that all of the rate quote
			// pricing data recorded in Step 6 is displayed on the screen. Verify that
			// accessorials recorded
			// in Step 3 are displayed. Verify dimensions, pieces, piece type, and commodity
			// descriptions are displayed.
			DisplayCommentsScreen displayCommentsScreen = new DisplayCommentsScreen(session);
			displayCommentsScreen.verifyDisplayCommentsScreen();
			displayCommentsScreen.verifyDataIsDisplayed();
			displayCommentsScreen.verifyDataIsDisplayedInComments();
			displayCommentsScreen.enterF3Key();
			workWithRequestScreen.enterF3Key();
			goldMedalMainMenuScreen.enterF3Key();
			commandEntryScreen.enterF3Key();
			commandEntryScreen.pressEnterKey();
			IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
			ibmMainMenuScreen.verifyIBMMainMenuScreen();
			ibmMainMenuScreen.enterValueToComandLineField("90");
			session.close();

			eNetTimeCriticalRateQoutePage.clickLogout();
			eNetTimeCriticalRateQoutePage.clickLogoutConfirmation();
			// Step#15 : Repeat steps to create 2 more quotes using different parameters and
			// Guaranteed options. Verify that data is written to GMS.
			// NOTE: The Origin and Destination zip codes may be swapped.
			// A for loop has been used for this step.
		}

	}

	/**
	 * This test fails because its takes a long time for quoteto appear in eNet
	 * 
	 * Quote History - Verify selecting a Time Critical Quote # from Rate Quote
	 * History displays the Time-Critical Quote History Detail
	 */
	@Test(enabled = true, priority = 8)

	public void executeQZ_7089()
			throws InterruptedException {

		String accountNo = "5068692", originZip = "23230", destinatonZip = "30362", role = "Shipper",
				phoneNo = "20212345676513";
		String term = "Pre-paid", classes = "50", pieces = "5", pieceType = "BALE", weight = "500", length = "32",
				width = "32", height = "10";
		// Step#1 : Execute the following test case and record all keyed in data:
		// https://estesexpress.atlassian.net/browse/QZ-7056
		// Record all keyed in data and the selected Time Critical Quote Number
		// Record all keyed in data and the selected Time Critical Quote Number

		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// User is brought to the eNet Homepage.
		eNetHomePage.validatePage();
		// From eNet Home Page, select 'Customer Service' link in the 'Application'
		// section.
		// Customer Service link when selected contains additional sub sections based on
		// user permissions.
		eNetHomePage.clickOnCustomerServiceLink();
		// From the Customer Service Page, select 'Time Critica Rate Quotel' link.
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// User is brought to the 'Time Critical Rate Quote' screen
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();
		// Enter the required data to submit a TC request.
		eNetTimeCriticalRateQoutePage.enterFullName();
		String fullName = eNetTimeCriticalRateQoutePage.getFullNamestr();
		eNetTimeCriticalRateQoutePage.enterEmail();
		String emailId = eNetTimeCriticalRateQoutePage.getEmail();
		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNo);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();
		// Select <Submit>.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		// Select from the Guaranteed options links available. (one that does not
		// indicate 'Contact Me')
		testUtil.setHardWait(2000);
		eNetTimeCriticalRateQoutePage.clickExclusiveUseChargesOption();
		// User is brought to the 'Time Critical Quote Detail Summary' section where the
		// 'Quote Number' is displayed.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		
		String quoteNo = eNetTimeCriticalRateQoutePage.fetchQuoteNumber();
		Assert.assertNotNull(quoteNo, "Quote number not displayed.");

		// Step#2 : Open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		// Open another tab
		((JavascriptExecutor) driver).executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		logger.info("ArrayList Size : " + tabs.size());
		driver.switchTo().window(tabs.get(1));

		// Step#3 : Login to *eNet* application using the following credentials:
		// User ID: qaenet01, Password: qaenet01
		driver.get(url2);
		// User is brought to the eNet Homepage.
		eNetHomePage.validatePage();

		// Step#4 : From *eNet* home page top navigation menu, click *Applications*
		eNetHomePage.clickOnApplicationsTab();

		// Step#5 : From the *Applications* page, find the *Customer Service*
		// applications list, and click *Quote History Lookup* link
		eNetApplicationsPage.clickOnQuoteHistoryLookupLink();

		// Step#6 : From the *Search Criteria* section, enter: Quote Number: Quote from
		// step 1
		testUtil.switchToFrame("mainpage");

		logger.info("quoteNo : " + quoteNo);
		eNetQuoteHistoryLookupPage.enterQuoteNumber(quoteNo);
		
		testUtil.setHardWait(99000);  //added long wait to retrieve quote number

		// Step#7 : Click *Search*
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		// Search results display the unique Quote Number
		testUtil.setHardWait(30000);
//		driver.navigate().refresh();   ---commanded
		eNetQuoteHistoryLookupPage.verifyQuoteNumber(quoteNo);

		// Step#8 : Select the *Quote #* link
		eNetQuoteHistoryLookupPage.clickQuoteLink();
		// *Time-Critical Quote History* page displays
		eNetTimeCriticalRateQouteHistoryPage.verifyPage();

		// Step#9 : From the *Quote Detail Summary* section
		// Validate all data is correct
		String accountNum = eNetTimeCriticalRateQouteHistoryPage.fetchAccountNo();
		Assert.assertEquals(accountNum, accountNo, "Account number doesnot match.");
		String roleName = eNetTimeCriticalRateQouteHistoryPage.fetchRole();
		Assert.assertEquals(roleName, role, "Role doesnot match.");
		String terms = eNetTimeCriticalRateQouteHistoryPage.fetchTerms();
		Assert.assertEquals(terms, term, "Term doesnot match.");
		String pickUp = eNetTimeCriticalRateQouteHistoryPage.fetchpickUpDate();
		StringBuilder str = new StringBuilder(todayDate);
		todayDate = str.deleteCharAt(6).deleteCharAt(6).toString();
		logger.info("todayDate : " + todayDate);
		Assert.assertEquals(pickUp, todayDate, "PickUps doesnot match.");
		String phone = eNetTimeCriticalRateQouteHistoryPage.fetchPhoneNumber().replaceAll("\\s", "");
		Assert.assertEquals(phone, phoneNo, "Phone number doesnot match.");

		// Step#10 : From *eNet* home page top navigation menu, click *Log Out*
		eNetTimeCriticalRateQouteHistoryPage.clickLogout();

		// Step#11 : Confirm by clicking the *Logout* button
		eNetTimeCriticalRateQouteHistoryPage.clickLogoutConfirmation();
	}
	
	/**
	 * This test fail because charges are displayed instead of Contact me link
	 * @throws InterruptedException
	 * ENet TC Rate Request - Dimensions override Long Accessorial selected.
	 */

	@Test(enabled = true, priority = 9)

	public void executeQZ_7066() throws InterruptedException {
		// Step#1 : Log into ENet QA.
		// https://estesexpress.atlassian.net/browse/QZ-6808, UserID: QAENET01,
		// Password: QAENET01
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.validatePage();

		// Step#2 : From home page under Applications, click View All.
		eNetHomePage.clickViewAll();

		// Step#3 : From Applications View All menu under Customer Service, click Time
		// Critical Rate Quote.
		// The Time Critical Rate Quote tool is displayed.
		eNetTimeCriticalRateQoutePage.switchToMainFrame();
        eNetHomePage.clickOnTimeCriticalRateQuoteLink();

		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#4 : Enter info in all Required Fields and use the following Dimensions:
		// Length: 150, Width: 32, Height: 32
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.getFullNamestr();
		eNetTimeCriticalRateQoutePage.enterEmail();
		eNetTimeCriticalRateQoutePage.getEmail();
		String accountNo = "5068692", originZip = "23230", destinatonZip = "30362", weight = "100", length = "150",
				width = "32", height = "32", phoneNo = "20212345676513", role = "Shipper", term = "Pre-paid",
				classes = "55", pieces = "10", pieceType = "BALE";
		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNo);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		eNetTimeCriticalRateQoutePage.getTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		// Step#5 : Select the assessorial for Overlength 12.00' to 19.99'.
		// Data displays as entered
		eNetTimeCriticalRateQoutePage.checkOverLength12to19();

		// Step#6 : Click Submit
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// The disclaimer displays stating that the total linear footage exceeds 20 feet
		// displays at the top.
		testUtil.setHardWait(2000);
		eNetTimeCriticalRateQoutePage.verifyTotalLinearFootageExceeds20feet();

		// Step#7 : Verify that rates for LTL Time Critical are not generated.
		// Legacy will not provide rates for LTL Time Critical if dimension total linear
		// feet is greater than 20
		eNetTimeCriticalRateQoutePage.verifyLTLTimeCriticalRatesNotGenerated();

	}

	/**
	 * 
	 * This test passed on 7/6/22
	 * ENet TC Rate Request - Exclusive Use within the same zip code
	 * @throws InterruptedException
	 * 
	 * 
	 */
	@Test(enabled = true, priority = 10)

	public void executeQZ_7072() throws InterruptedException {
		// Step#1 : Before midnight, log into ENet QA with the following credentials and
		// URL:
		// UserID: qaenet02, Password: qaenet02
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.validatePage();

		// Step#2 : From Under Applications, click on Customer Service. Click Time
		// Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#3 : Enter data in all Required Fields for a Base Rate quote.
		// For Origin and Destination zip codes, enter the same zip in the Eastern Time
		// Zone (such as 23230).
		eNetTimeCriticalRateQoutePage.enterFullName();
		String fullName = eNetTimeCriticalRateQoutePage.getFullNamestr();
		eNetTimeCriticalRateQoutePage.enterEmail();
		String emailId = eNetTimeCriticalRateQoutePage.getEmail();
		String accountNo = "5068692", originZip = "23230", destinatonZip = "30362", weight = "50", length = "32",
				width = "32", height = "32", phoneNo = "20212345676513", role = "Shipper", term = "Pre-paid",
				classes = "50", pieces = "5", pieceType = "BALE";
		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNo);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();
		// Step#4 : Click Submit.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Data is recorded. Quote is displayed.
		// Data displays as entered
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		testUtil.setHardWait(2000);
		// Step#5 : Select the Exclusive Use charge.
		eNetTimeCriticalRateQoutePage.clickExclusiveUseChargesOption();
		// The Exclusive Use rate returns the minimum charge in the Charges section.

	}

	/**
	 * This test is failing because contact me is not being displayed at last step
	 * 
	 * eNet - Time Critical - Verify Quote Number display
	 */

	@Test(enabled = true, priority = 11)
	public void executeQZ_7056() {

		// Step 1: Login to enet application
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// Step 2: Click on Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Step 3: Click on Time Critical rate quote link
		eNetApplicationsPage.clickOnTimeCriticalLink();

		// Verify Time Critical page is displayed
		eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step 4: Enter required date to submit Time critical rate quote
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterEmail();
		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900 x3170");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		testUtil.setHardWait(1000);
		eNetTimeCriticalRateQoutePage.enterOriginZip("23230");
		testUtil.setHardWait(1000);
		eNetTimeCriticalRateQoutePage.enterDestinationZip("30307");
		eNetTimeCriticalRateQoutePage.setTodayDate();
		eNetTimeCriticalRateQoutePage.selectClass("50");
		eNetTimeCriticalRateQoutePage.enterPieces("1");
		eNetTimeCriticalRateQoutePage.enterPiecesType("SKID");
		eNetTimeCriticalRateQoutePage.enterWeight("700");
		eNetTimeCriticalRateQoutePage.enterLength("384");
		eNetTimeCriticalRateQoutePage.enterWidth("32");
		eNetTimeCriticalRateQoutePage.enterHeight("32");

		// Step 5: Click on submit button
		eNetVTLQouteExceptionQueuePage.clickOnSubmitButton();
		testUtil.setHardWait(5000);

		// Step 6: Select option which does not have contact me
		eNetTimeCriticalRateQoutePage.selectQouteByServiceLevel("TC", "LTL Standard Transit");

		// Step 7: Verify Quote number is displayed
		eNetTimeCriticalRateQoutePage.recordQuoteNumber();
	}

	/**
	 * This test fails in the last step due to validation
	 * 
	 * ENet TC Rate Request Result Details Screen - Print Option 
	 */
	@Test(enabled = true, priority = 12)

	public void executeQZ_7080()
			throws InterruptedException, IOException, PrinterException {
	
		// Step#1 : Log into eNet QA with your AD credentials and the following URL:
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// User is brought to the eNet Homepage.
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#2 : From eNet Home page under Applications, click on Customer Service.
		// Click Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//	eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#3 : Enter data in all Required Fields for a Base Rate quote.
		// Include multiple accessorials and commodities. Record data entered and click
		// Submit.
		eNetTimeCriticalRateQoutePage.enterFullName();
		String fullName = eNetTimeCriticalRateQoutePage.getFullNamestr();
		eNetTimeCriticalRateQoutePage.enterEmail();
		String emailId = eNetTimeCriticalRateQoutePage.getEmail();
		String accountNo = "5068692", originZip = "23230", destinatonZip = "30362", weight = "50", length = "32",
				width = "32", height = "32", phoneNo = "20212345676513", role = "Shipper", term = "Pre-paid",
				classes = "50", pieces = "5", pieceType = "BALE";
		/*
		 * Adding data to ArrayList.
		 */
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(accountNo);
		dataList.add(originZip);
		dataList.add(destinatonZip);
		dataList.add(role);
		dataList.add(term);

		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNo);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		testUtil.setHardWait(3000);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		testUtil.setHardWait(3000);
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		dataList.add(todayDate);
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Data is recorded. Quote is displayed.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		testUtil.setHardWait(2000);
		// Record the data for all prices
		List<String> remainingData = new ArrayList<String>();
		List<String> prices = new ArrayList<String>();
		prices = eNetTimeCriticalRateQoutePage.fetchAllPrices();
		remainingData.addAll(prices);

		// Step#4 : Click on the Guaranteed by 5pm price.
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy5PMprice();
		// The Time Critical Summary Screen is displayed.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		String quoteNo = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
		dataList.add(quoteNo);

		// Record VLT eNet notes
		List<String> VLTlist = new ArrayList<String>();
		VLTlist = eNetTimeCriticalRateQoutePage.fetchVLTTableeNetNotes();
		remainingData.addAll(VLTlist);

		// Step#5 : Check the checkbox "I have read and agree to the disclaimers and
		// Terms of Service below."
		// Click the Print Quote button
		// Print view opens.
		eNetTimeCriticalRateQoutePage.clickOnAgreeCheck();
		eNetTimeCriticalRateQoutePage.clickPrintQuote();
		
		testUtil.deleteFilesFromFolder(downloadPath, "print.pdf"); //Added method to delete existing files

		// Step#6 : Click the Print icon, and then click the Print button on the print
		// dialog.
		// Document is printed
		// This step doesnot involve any verification as the printed document is a
		// physical thing.
		testUtil.printPDFFile(downloadPath + "print.pdf");
		testUtil.setHardWait(1000);
		// Step#7 : Verify that the details for the Guaranteed by 5pm quote are
		// displayed in the top section and
		// that a table of all pricing options is displayed at the bottom of the page.
		// The details for the Guaranteed by 5pm quote are displayed in the top section
		// and
		// that a table of all pricing options is displayed at the bottom of the page.
		testUtil.verifyContentofPDF(downloadPath + "print.pdf", dataList);

		// Step#8 : Verify that the remaining data displays to match was entered
		// previously.
		testUtil.verifyContentofPDF(downloadPath + "print.pdf", prices);
		testUtil.deleteFilesFromFolder(downloadPath, "print.pdf");
	}

	/**
	 * 
	 * ENet TC Rate Request Result Details Screen - Print Option shows Weight Deficits 
	 */
	@Test(enabled = true, priority = 13)

	public void executeQZ_7081()
			throws InterruptedException, IOException, PrinterException {
		// Step#1 : Log into eNet QA with the following credentials and URL:
		// https://estesexpress.atlassian.net/browse/QZ-6808, UserID: qaenet02,
		// Password: qaenet02
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#2 : On the Applications section, click View All. From the Customer
		// Service section, click Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#3 : Enter data in Required Fields for the following Account Code and
		// commodity information. Populate other required fields. Click Submit.
		String weight = "480", length = "10", width = "5", height = "5", role = "Shipper", term = "Pre-paid",
				classes = "50", pieces = "10", pieceType = "PALLET";
		String destinatonZip = "23005", originZip = "24007", accountNo = "5068692";
		/*
		 * Adding data to ArrayList.
		 */
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(accountNo);
		dataList.add(originZip);
		dataList.add(destinatonZip);
		dataList.add(role);
		dataList.add(term);

		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterEmail("QATEST@ESTES-EXPRESS.COM");
		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676511");
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		logger.info("Todays date : " + todayDate);
		dataList.add(todayDate);
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Resulting quote is displayed.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Record all data
		List<String> remainingData = new ArrayList<String>();
		List<String> prices = new ArrayList<String>();
		prices = eNetTimeCriticalRateQoutePage.fetchAllPrices();
		remainingData.addAll(prices);

		// Step#4 : Click on the Guaranteed by 12pm price.
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy12PMprice();
		// The Time Critical Summary Screen is displayed.
		eNetTimeCriticalRateQoutePage.verifyTimeCriticalSummaryDisplayed();
		String quoteNo = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
		dataList.add(quoteNo);

		// Step#5 : Click the Print Quote Button
		// Print view opens.
		eNetTimeCriticalRateQoutePage.clickPrintQuote();
		
		testUtil.deleteFilesFromFolder(downloadPath, "print.pdf");  //Added step to delete file

		// Step#6 : Click the Print icon, and then click the Print button on the print
		// dialog.
		// Document is printed
		// This step doesnot involve any verification as the printed document is a
		// physical thing.
		testUtil.printPDFFile(downloadPath + "print.pdf");

		// Step#7 : Verify that the details for the Guaranteed by 12pm quote are
		// displayed in the top section
		// and that a table of all pricing options is displayed at the bottom of the
		// page.
		testUtil.verifyContentofPDF(downloadPath + "print.pdf", dataList);

		// Step#8 : Verify that the deficit weight of 20 has been added under the
		// line(s) for the commodity class/pieces/weight/dimensions.
		testUtil.fetchStringfromPDF("20", downloadPath + "print.pdf");

	}

	/**
	 * 
	 * this test fails because contact is not displayed for all the charges
	 * 
	 * ENet TC Rate Request - Contact Us for Limited Access Accessorials
	 */
	@Test(enabled = true, priority = 14)

	public void executeQZ_7058() throws InterruptedException {
		// As per UFT script the test steps are reordered.
		// Step#5 : Log into EXLAQA and run the following query and note an accessorial
		// that is allowed where GSGTYP = 'TC'.
		// Here in this step, EXLAQA is needed to be logged in, as directly from the
		// database records are getting fetched.
		
		String query = "SELECT * FROM FBFILES.GSC00P540 where GSGSTS='A'";
		List<String> records = new ArrayList<>();
		records = sqlDatalist.getFirstRowDetailsFromEXLAQA(query);
		for (int i = 0; i < records.size(); i++) {
			logger.info("FB files details : " + records.get(i));
		}

		// Step#6 : If GSGTYP='TC' is not found, update a record using the following
		// query:
		// UPDATE FBFILES.GSC00P540 SET GSGTYP='TC' WHERE GSGACC='<selected
		// accessorial>'
		
		if (!records.contains("TC")) {
			String updatequery1 = "UPDATE FBFILES.GSC00P540 SET GSGTYP='TC' WHERE GSGACC='CONST'";
			sqlDatalist.updateRecordInTable(updatequery1);
		}

		// Step#7 : Under Applications, click on VTL Table Maintenance and select 'Time
		// Critical'.
		/*
		 * Select an Accessorial that displayed in GSC00P540 that does not display in
		 * the VTL Table as Prohibited. (OR click X to remove it from the table) //NOTE:
		 * Ensure that the accessorial selected does not point to a specific account
		 * unless that is the account to be entered in Step 5.
		 */
		driver.get(url2);
		eNetLoginPage.enterUserID(username16);
		eNetLoginPage.enterUserPassword(password16);
		eNetLoginPage.clickOnLoginButton();
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.verifyPage();
		eNetVTLTableMaintenancePage.selectQuoteType("TC");
		List<String> accessories = new ArrayList<String>();
		accessories = eNetVTLTableMaintenancePage.fetchAccessorials();
		for (int i = 0; i < accessories.size(); i++) {
			if (accessories.get(i).contains("Construction") || accessories.get(i).contains("construction")) {
				eNetVTLTableMaintenancePage.deleteAccessory(accessories.get(i));
				break;
			}
		}
		// Step#1 : From Internet Explorer Browser, Log into ENet QA with your AD
		// credentials using the following URL and user credential:
		// https://estesexpress.atlassian.net/browse/QZ-6808, UserID: qaenet01,
		// Password: qaenet01
		// Opening new tab for VTL Table Maintenance page operation
		String mainWindow = driver.getWindowHandle();
		String otherTab = null;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
		testUtil.setHardWait(2000);
		Set<String> tabSet = new HashSet<String>();
		tabSet = driver.getWindowHandles();
		logger.info("Set size : " + tabSet.size());
		Iterator<String> tab = tabSet.iterator();
		System.out.println("The NewData values are: ");
		while (tab.hasNext()) {
			String tabName = tab.next().toString();
			logger.info("Tab name : " + tabName);
			if (!tabName.equals(mainWindow)) {
				otherTab = tabName;
				driver.switchTo().window(otherTab);
				break;
			}
		}
		driver.get(url2);

		// Step#2 : From the eNet Home Page display under Applications, click on
		// Customer Service. Click Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// User is brought to the 'Time Critical Rate Quote' screen
		eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		// Step#3 : From the Time Critical Rate Quote tool display, enter data in all
		// Required Fields.
		/*
		 * Account: 5068692, Origin: 23230, Destination: 30362, Pickup Date: > current
		 * date, Total Weight: 500, Length: 32, Width: 32, Height: 32
		 */
		// Enter the required data to submit a TC request.
		eNetTimeCriticalRateQoutePage.enterFullName("ESTES");
		String fullName = eNetTimeCriticalRateQoutePage.getFullNamestr();
		eNetTimeCriticalRateQoutePage.enterEmail("ESTES@ESTES.COM");
		String emailId = eNetTimeCriticalRateQoutePage.getEmail();
		String accountNo = "5068692", originZip = "23230", destinatonZip = "30362", weight = "500", length = "10",
				width = "5", height = "5", phoneNo = "2341068701254  ", role = "Shipper", term = "Pre-paid",
				classes = "55", pieces = "10", pieceType = "PALLET";
		eNetTimeCriticalRateQoutePage.enterAccountNumber(accountNo);
		eNetTimeCriticalRateQoutePage.enterOriginZip(originZip);
		eNetTimeCriticalRateQoutePage.enterDestinationZip(destinatonZip);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber(phoneNo);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setNext7daysDate();
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		testUtil.setHardWait(1000);
		eNetTimeCriticalRateQoutePage.selectAvailableByandCloseByOptions("10", "00", "PM", "11", "00", "PM");
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");

		// Step#4 : Click SUBMIT
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Quote is displayed and all Guaranteed and Exclusive Use options display
		// quotes.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		// Verify all quotes have values not Contact me link.
		List<String> contactMeServices = new ArrayList<String>();
		contactMeServices = eNetTimeCriticalRateQoutePage.verifyContactMeLinkPresence();
		if (contactMeServices.size() == 0) {
			logger.info("Found Contact Me link.");
		} else {
			logger.info("Did not found Contact Me link.");
		}

		eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();
		eNetTimeCriticalRateQoutePage.clickOnConstructionSiteDelivery();
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		List<String> checkcontactMeServices = new ArrayList<String>();

		checkcontactMeServices = eNetTimeCriticalRateQoutePage.verifyContactMeLinkNotPresence();
		if (checkcontactMeServices.size() == 1) {
			if (checkcontactMeServices.contains("LTL Standard Transit")) {
				logger.info("Found the correct Service with charge.");
			} else {
				Assert.fail("Not found with correct service label.");
			}
		} else {
			Assert.fail("Found more than one Service Labels with the charges.");
		}

		// Step#11 : Run the following query and note an accessorial that is ignored:
		/* SELECT * FROM FBFILES.GSC00P540 where GSGSTS='I' */
		String query1 = "SELECT * FROM FBFILES.GSC00P540 where GSGSTS='I'";
		List<String> records1 = new ArrayList<>();
		records1 = sqlDatalist.getFirstRowDetailsFromEXLAQA(query);
		for (int i = 0; i < records1.size(); i++) {
			logger.info("FB files details where GSGSTS='I' : " + records1.get(i));
		}

		// Step#13 : Confirm that GSGTYP = 'TC'. Set to 'TC', if needed, using the
		// following query:
		/*
		 * UPDATE FBFILES.GSC00540 SET GSGTYP='TC' WHERE GSGACC=<accessorial selected>
		 */
		if (!records1.contains("TC")) {
			String updatequery2 = "UPDATE FBFILES.GSC00P540 SET GSGTYP='TC' WHERE GSGACC='FRZ'";
			sqlDatalist.updateRecordInTable(updatequery2);
		}

		// Step#12 : Under Applications, click on VTL Table Maintenance and select 'Time
		// Critical'. Select an Accessorial that displayed in GSC00P540 as ignored that
		// does not display in the VTL Table as Prohibited. (OR click X to remove it
		// from the table)
		/*
		 * NOTE: Ensure that the accessorial selected does not point to a specific
		 * account unless that is the account to be entered in 'Update Quote'.
		 */
		/* For example, GSGACC, FAIRDL = Fair/Amusement Park Delivery */
		/* Here just refreshing the page. */
		driver.switchTo().window(mainWindow);
		driver.navigate().refresh();
		eNetVTLTableMaintenancePage.selectQuoteType("TC");
		List<String> accessories1 = new ArrayList<String>();
		accessories1 = eNetVTLTableMaintenancePage.fetchAccessorials();
		for (int i = 0; i < accessories1.size(); i++) {
			if (accessories1.get(i).contains("FRZ")) {
				eNetVTLTableMaintenancePage.deleteAccessory(accessories1.get(i));
				break;
			}
		}

		
		// Note: Step 14 involves step: 8, 9, 10.
		// Step#14 : Select Update Quote, change the accessorial to one that is ignored
		// in GSC00P540 and it not prohibited in VTL Table Maintenance.
		/*
		 * Quote is displayed and all Guaranteed and Exclusive Use options display
		 * 'Contact Me'.
		 */
		// Step#8 : Under Applications, click on Customer Service. Click Time Critical
		// Rate Quote.
		// Note : Instead of going through home page, the page navigation is directed
		// through Update Quote.
		driver.switchTo().window(otherTab);
		driver.switchTo().frame("mainpage");
		eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();

		// Step#9 : Enter data in all Required Fields. Select accessorial chosen
		// previously from the list of Accessorials.
		// Here only selecting protect from freezing checkbox, instead of filling all
		// the data again
		eNetTimeCriticalRateQoutePage.clickOnConstructionSiteDelivery();
		eNetTimeCriticalRateQoutePage.clickOnProtectFromFreezing();

		// Step#10 : Click Submit.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Quote is displayed and all Guaranteed and Exclusive Use options display
		// 'Contact Me'.
		checkcontactMeServices = null;
		checkcontactMeServices = eNetTimeCriticalRateQoutePage.verifyContactMeLinkNotPresence();
		if (checkcontactMeServices.size() == 1) {
			if (checkcontactMeServices.contains("LTL Standard Transit")) {
				logger.info("Found the correct Service with charge.");
			} else {
				Assert.fail("Not found with correct service label.");
			}
		} else {
			Assert.fail("Found more than one Service Labels with the charges.");
		}
	}

	/**
	 * This test passed on 6/27/22
	 * 
	 * ENet TC Rate Request Results Screen - Comments field feeds into GMS
	 */
	@Test(enabled = true, priority = 15)

	public void executeQZ_7083() throws Exception {
		// Step#1 : **NOTE - Create Rate quote before 3PM EDT. From Internet Browser,
		// Log into MyEstes QA with the the following credentials:

		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#2 : From the top menu, click Ship and select Rate Quote.
		// The Rate Quote tool is displayed.
		// Step#3 : Select the checkbox for Time Critical/Expedited and unselect the
		// checkbox for Less than Truckload (incl. Guaranteed).
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#4 : Enter data in all required fields.
		/*
		 * Origin: 23230 Destination: 30362 Length: 33 Width: 33 Height: 33
		 */
		String role = "Shipper", term = "Pre-paid";
		eNetTimeCriticalRateQoutePage.enterFullName("Estes");
		eNetTimeCriticalRateQoutePage.enterEmail("ESTES@estes-express.com");
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676511");
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		eNetTimeCriticalRateQoutePage.enterOriginZip("23230");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("30362");
		eNetTimeCriticalRateQoutePage.selectClass("50");
		eNetTimeCriticalRateQoutePage.enterPieces("2");
		eNetTimeCriticalRateQoutePage.enterPiecesType("SKID");
		eNetTimeCriticalRateQoutePage.enterWeight("100");
		eNetTimeCriticalRateQoutePage.enterLength("33");
		eNetTimeCriticalRateQoutePage.enterWidth("33");
		eNetTimeCriticalRateQoutePage.enterHeight("33");
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.checkInsideDelivery();
		eNetTimeCriticalRateQoutePage.checkNotifyRequest();

		// Step#5 : Click Submit Request.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();

		// Step#6 : Click on the Guaranteed by 5pm quote option.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy5PMRate();
		// The Time Critical Summary Screen is displayed.
		eNetTimeCriticalRateQoutePage.verifyTimeCriticalSummaryDisplayed();

		// Step#7 : Record the Quote Number.
		String quoteNum1 = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
		// logger.info("Quote Number : "+quoteNum);

		// Step#8 : Click Revise Quote under Quote Options on the right side.
		eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();
		// rateQuotePage.clickOnReviseQuoteButtonServiceLevel("Guaranteed LTL Standard
		// Transit: 5PM");

		// Step#9 : Under Freight Information, populate the Comments field with data and
		// make note of the comment entered.
		// NOTE: A total of 100 characters may be added.
		eNetTimeCriticalRateQoutePage.enterComments("A total of 100 characters may be added.");

		// Step#10 : Click Submit Request.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();

		// Step#11 : Click on 5PM Guaranteed quote and make note of the quote number.
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy5PMRate();
		String quoteNum2 = eNetTimeCriticalRateQoutePage.recordQuoteNumber();

		// Step#12 : Log into EXLAQA AS400.
		// EXLAQA Main Menu Screen displays
		String userName = "PEACHDO";
		String password = "aug08y14";
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		// Step#13 : Enter "call GMR000" in the command line and press enter.
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		commandEntryScreen.enterCommand("call GMR000");
		// The Gold Medal Main Selection Menu is displayed.
		GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
		goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();

		// Step#14 : Select option 1 and press Enter.
		goldMedalMainMenuScreen.enterOption("1");
		// The Work With Comments screen displays.
		WorkWithRequestCommentsInstructionScreen workWithRequestCommentsInstructionScreen = new WorkWithRequestCommentsInstructionScreen(
				session);
		workWithRequestCommentsInstructionScreen.verifyWorkWithCommentsInstructionScreen();

		// Step#15 : Find the Quote Number recorded previously.
		// NOTE: Tester may need to hit Shift F5 and clear the username field.
		logger.info("Quote No : " + quoteNum2);
		workWithRequestCommentsInstructionScreen.enterQuoteNumber(quoteNum2);

		// Step#16 : In the Opt field to the left of the Quote ID, type 20.
		workWithRequestCommentsInstructionScreen.enterValueInOptField("20");

		// Step#17 : Verify that the comments entered previously are displayed and that
		// the User ID and Date Stamp is correct.
		WorkWithCommentsScreen workWithCommentsScreen = new WorkWithCommentsScreen(session);
		workWithCommentsScreen.verifyWorkWithCommentsScreen();
		// Going to the next page to find the Comment line.
		workWithCommentsScreen.enterStartingLineNumber("11");
		// workWithCommentsScreen.verifyCommentEntered("A total of 100 characters may be
		// added.", "TCREQUEST");

		// Exlaqa exit steps add.
		workWithCommentsScreen.enterF3Key();
		workWithRequestCommentsInstructionScreen.enterF3Key();
		goldMedalMainMenuScreen.enterF3Key();
		commandEntryScreen.enterF3Key();
		commandEntryScreen.pressEnterKey();
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		ibmMainMenuScreen.enterValueToComandLineField("90");

	}

	/*
	 * ENet TC Rate Request Results Screen - Comments can be entered, edited, and deleted. 
	 */
	@Test(enabled = true, priority = 16)

	public void executeQZ_7082() throws Exception {
		// Step#1 : Call test, ENet TC Rate Request Results Screen - Comments field
		// feeds into GMS
		// https://estesexpress.atlassian.net/browse/QZ-7083
		executeQZ_7083();

		// Step#2 : Delete the data entered in the Comments field.
		String quoteNum1 = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
		eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();
		eNetTimeCriticalRateQoutePage.enterComments("");

		// Step#3 : Click Submit Request.
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();

		// Step#4 : Click on the Guaranteed by 5pm rate.
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy5PMRate();
		// Quote is displayed.
		String quoteNum2 = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
		if (!quoteNum2.isEmpty()) {
			logger.info("Quote found : " + quoteNum2);
		}

		// Step#5 : From the Time Critical Summary Screen display, verify that the
		// deleted comments no longer display.
		String comment = eNetTimeCriticalRateQoutePage.fetchComment();

		// Step#6 : Click Revise Quote again.
		eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();

		// Step#7 : Add data to Comments for 101 characters.
		String cmt = "This is Testing the Comments Field DOES NOT Accept more than 100 characters. ONLY 100 characters all.";
		eNetTimeCriticalRateQoutePage.enterComments(cmt);

		// Step#8 : Click Submit Request.
	
		eNetTimeCriticalRateQoutePage.checkCommentsCharacterlimit();
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();

		/*
		 * These below steps are not applicable as, the Comment textbox has an
		 * auto-limiter to not let the number of characters exceed 100. //Step#9 :
		 * Modify the Comments field to contain less than 101 characters. (Make note of
		 * the comment entered.) //Step#10 : Click Submit Request. //The quote request
		 * is submitted.
		 */
		// Step#11 : Scroll down to view the Comments for the request.
		eNetTimeCriticalRateQoutePage.clickOnGuranteedBy5PMRate();
		String comment1 = eNetTimeCriticalRateQoutePage.fetchComment();
		if (cmt.contains(comment1)) {
			logger.info("The comment field contains the modfied comment.");
		} else {
			Assert.fail("The comment field does not contain the modfied comment.");
		}

		// Step#12 : Record Quote #.
		// Quote Number recorded.
		String quoteNum3 = eNetTimeCriticalRateQoutePage.recordQuoteNumber();

		// Step#13 : Log into EXLAQA AS400 and "call GMR00" on the command line.
		// EXLAQA Main Menu Screen displays
		String userName = "PEACHDO";
		String password = "aug08y14";
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		testUtil.setHardWait(3000);
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(userName, password);

		// Enter "call GMR000" in the command line and press enter.
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		commandEntryScreen.enterCommand("call GMR000");
		// The Gold Medal Main Selection Menu is displayed.
		GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
		goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();

		// Comments match comments recorded in Step 13 and include
		// the following data: Date, Time, User ID
		// Step#14 : Select option 1. Find and recall Quote# recorded previously.
		// The comments displayed match the comments entered previously.
		goldMedalMainMenuScreen.enterOption("1");
		// The Work With Comments screen displays.
		WorkWithRequestCommentsInstructionScreen workWithRequestCommentsInstructionScreen = new WorkWithRequestCommentsInstructionScreen(
				session);
		workWithRequestCommentsInstructionScreen.verifyWorkWithCommentsInstructionScreen();

		// Find the Quote Number recorded previously.
		// NOTE: Tester may need to hit Shift F5 and clear the username field.
		logger.info("Quote No : " + quoteNum3);
		workWithRequestCommentsInstructionScreen.enterQuoteNumber(quoteNum3);

		// In the Opt field to the left of the Quote ID, type 20.
		workWithRequestCommentsInstructionScreen.enterValueInOptField("20");

		// Verify that the comments entered previously are displayed and that the User
		// ID and Date Stamp is correct.
		WorkWithCommentsScreen workWithCommentsScreen = new WorkWithCommentsScreen(session);
		workWithCommentsScreen.verifyWorkWithCommentsScreen();
		// Going to the next page to find the Comment line.
		workWithCommentsScreen.enterStartingLineNumber("11");

		// Exlaqa exit steps add.
		workWithCommentsScreen.enterF3Key();
		workWithRequestCommentsInstructionScreen.enterF3Key();
		goldMedalMainMenuScreen.enterF3Key();
		commandEntryScreen.enterF3Key();
		commandEntryScreen.pressEnterKey();
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		ibmMainMenuScreen.enterValueToComandLineField("90");
	

	}

	/**
	 * This test fail because screen is not being displayed in step 13
	 * 
	 * ENet TC Rate Request - Routing Information Displayed 
	 */
	@Test(enabled = true, priority = 17)

	public void executeQZ_7077() throws Exception {
	
		String weight = "500", length = "10", width = "5", height = "5", role = "Shipper", term = "Pre-paid",
				classes = "55", pieces = "10", pieceType = "PALLET";
		
		// Step#1 : From Internet Explorer, Log into eNet QA with the following
		// credentials and URL:
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#2 : From the home page under Applications, click on Customer Service. Click Time Critical Rate Quote.
		eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// The Time Critical Rate Quote tool is displayed.
		//eNetTimeCriticalRateQoutePage.switchToMainFrame();
		eNetTimeCriticalRateQoutePage.verifyPage();

		// Step#3 : Enter data in all Required Fields for quote using the following
		// email address, Origin, and Destination. Click Submit.
		/*
		 * Account: 5068692 Email: QATEST@ESTES-EXPRESS.COM Origin: 24007 Destination:
		 * 23005
		 */
		
		eNetTimeCriticalRateQoutePage.enterFullName();
		eNetTimeCriticalRateQoutePage.enterEmail("QATEST@ESTES-EXPRESS.COM");
		eNetTimeCriticalRateQoutePage.enterAccountNumber("5068692");
		eNetTimeCriticalRateQoutePage.enterOriginZip("24007");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("23005");
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterPhoneNumber("20212345676511");
		eNetTimeCriticalRateQoutePage.selectTerm(term);
		testUtil.setHardWait(2000);
		eNetTimeCriticalRateQoutePage.setTodayDate();
		String todayDate = eNetTimeCriticalRateQoutePage.getTodayDate();
		logger.info("Todays date : " + todayDate);
		eNetTimeCriticalRateQoutePage.selectClass(classes);
		eNetTimeCriticalRateQoutePage.enterPieces(pieces);
		eNetTimeCriticalRateQoutePage.enterPiecesType(pieceType);
		eNetTimeCriticalRateQoutePage.enterWeight(weight);
		eNetTimeCriticalRateQoutePage.enterLength(length);
		eNetTimeCriticalRateQoutePage.enterWidth(width);
		eNetTimeCriticalRateQoutePage.selectRole(role);
		eNetTimeCriticalRateQoutePage.enterHeight(height);
		eNetTimeCriticalRateQoutePage.selectYesforFreightPickUp();
		eNetTimeCriticalRateQoutePage.isFreightStackable();
		eNetTimeCriticalRateQoutePage.selectWareHouse("5");
		eNetTimeCriticalRateQoutePage.enterComments("Estes");
		eNetTimeCriticalRateQoutePage.checkAppointmentRequest();
		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Quote Options are displayed.
		eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();

		String quoteNumber = null;
		Map<String, String> routingInfo = new HashMap<String, String>();

		for (int i = 0; i < 3; i++) {
			logger.info("Value of i : " + i);
			if (i == 0) {
				// Step#4 : Click on the Guaranteed by 5pm rate.
				eNetTimeCriticalRateQoutePage.clickOnGuranteedBy5PMRate();
				// Step#5 : Record Quote Number and Terminal Route that is displayed.
				// Quote Number and Routing information recorded.
				quoteNumber = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
				routingInfo = eNetTimeCriticalRateQoutePage.recordRoutingInformation();
				eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();
				eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
				eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
			} else if (i == 1) {
				// Step#16 : From eNet, click the 'Update Quote' tab to create a quote and
				// select the Guaranteed by 10am. (Repeat steps 3-15 skipping step 4.)
				eNetTimeCriticalRateQoutePage.clickOnGuranteedBy10AMprice();
				// Step#5 : Record Quote Number and Terminal Route that is displayed.
				// Quote Number and Routing information recorded.
				quoteNumber = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
				routingInfo = eNetTimeCriticalRateQoutePage.recordRoutingInformation();
				eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();
				eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
				eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
			} else if (i == 2) {
				// Step#17 : From eNet, click the 'Update Quote' tab to create a quote and
				// switch Origin and Destination zip codes.
				
				/* Select the Guaranteed by 12pm quote to verify that correct routing
				 * information including bypass/relay terminals is displayed in Enet. (Repeat
				 * steps 3 - 15 skipping step 4.)*/
				 
				eNetTimeCriticalRateQoutePage.clickOnGuranteedBy12PMprice();
				// Step#5 : Record Quote Number and Terminal Route that is displayed.
				// Quote Number and Routing information recorded.
				quoteNumber = eNetTimeCriticalRateQoutePage.recordQuoteNumber();
				routingInfo = eNetTimeCriticalRateQoutePage.recordRoutingInformation();
				eNetTimeCriticalRateQoutePage.clickOnUpdateQuote();
				eNetTimeCriticalRateQoutePage.clearOriginCity();
				eNetTimeCriticalRateQoutePage.enterOriginZip("23005");
				eNetTimeCriticalRateQoutePage.clearDestinationCity();
				eNetTimeCriticalRateQoutePage.enterDestinationZip("24007");
				eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
				eNetTimeCriticalRateQoutePage.verifyPageWithoutIFrame();
			}

			// Step#6 : Log into EXLAQA
			String userName = "PEACHDO";
			String password = "aug08y14";

			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);

			// EXLAQA Main Menu Screen displays
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.logon(userName, password);

			// Step#7 : Enter "call GMR000" in the command line and press enter.
			CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
			commandEntryScreen.enterCommand("call GMR000");

			// The Gold Medal Main Selection Menu is displayed.
			GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
			goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();

			// Step#8 : In the command line enter "5" and Press Enter.
			goldMedalMainMenuScreen.enterOption("5");
			// The Work With Request Service Information screen is displayed.
			WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
			workWithRequestScreen.verifyWorkWithRequestInfoScreen();

			// Step#9 : Press shift+F5 and clear the User ID field. Press ENTER.
			workWithRequestScreen.clearUser();
			workWithRequestScreen.pressEnter();
			// All quotes display.

			// Step#10 : Find the Quote Number recorded in Step 5, enter 5 in the Opt field
			// to the left of it, and click Enter.
			logger.info("Quote No : " + quoteNumber);
			workWithRequestScreen.enterQuoteNumber(quoteNumber);
			workWithRequestScreen.enterValueInOptField("5");
			// The Display Request Service Information screen for the selected quote is
			// displayed.
			DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(
					session);
			displayRequestServiceInfoScreen.verifyDisplayRequestScreen();

			// Step#11 : Record the Origin and Destination Terminal numbers.
			String originTerminalNumber = displayRequestServiceInfoScreen.recordOriginTerminalNumber();
			String destinationTerminalNumber = displayRequestServiceInfoScreen.recordDestinationTerminalNumber();
			// Origin and Destination Terminal numbers are recorded - 007 and 001.

			// Step#12 : Click Shift F6. (for F18 - Lane Display)
			displayRequestServiceInfoScreen.pressF18Key();
			// The Lane file display screen is displayed.
			EstesExpressLinesLaneFileDisplayScreen estesExpressLinesLaneFileDisplayScreen = new EstesExpressLinesLaneFileDisplayScreen(
					session);

			// Step#13 : Enter the Terminal Number recorded in Step 10 in the From and To
			// fields. Click Enter.
			estesExpressLinesLaneFileDisplayScreen.enterfromValue(originTerminalNumber);
			estesExpressLinesLaneFileDisplayScreen.enterToValue(destinationTerminalNumber);
			estesExpressLinesLaneFileDisplayScreen.pressEnterKey();
			// The Lane and Routing information is displayed.
			LaneRoutingInformationScreen laneRoutingInformationScreen = new LaneRoutingInformationScreen(session);

			// Step#14 : Record the Terminal routing and Bypass/Relay routing info.
			String[] terminalRoutingValue = new String[2];
			terminalRoutingValue = laneRoutingInformationScreen.recordTerminalRoutingValue();
			String bypassorRelayRoutingValue = laneRoutingInformationScreen.recordBypassorRelayRoutingValue();
			// Routing data (From & To) is recorded - 007 and 001.

			// Step#15 : Verify that Terminal Routing information recorded in Step 13
			// matches the routing information recorded in Step 5.
			Assert.assertEquals(originTerminalNumber, terminalRoutingValue[0]);
			Assert.assertEquals(destinationTerminalNumber, terminalRoutingValue[1]);

			// Making previous values null for next iteration.
			routingInfo = null;

			// Exit from currect EXLAQA session
			session.close();
		}

	}

}