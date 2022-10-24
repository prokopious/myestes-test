package rateQuoteTests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import billOfLadingPages.MyEstesBillOfLadingPage;
import eNetPages.ENetAgedARPage;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetFloorMinsDefinitionPage;
import eNetPages.ENetFoodWarehouseDistributionCenterMaintenancePage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetMyEstesManagementToolPage;
import eNetPages.ENetMyEstesUserDetailPage;
import eNetPages.ENetORPage;
import eNetPages.ENetQuoteAuditTrailPage;
import eNetPages.ENetQuoteHistoryLookupPage;
import eNetPages.ENetRateRetrieverPage;
import eNetPages.ENetTimeCriticalRateQuotePage;
import eNetPages.ENetVTLQouteExceptionQueuePage;
import eNetPages.ENetVTLRateQuotePage;
import eNetPages.ENetVTLTableMaintenancePage;
import eNetPages.VolumeAndTruckloadQuoteMaintenance;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import jagacyVT.screens.DisplayCommentsScreen;
import jagacyVT.screens.DisplayRequestServiceInfoScreen;
import jagacyVT.screens.GoldMedalMainMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.SubsetRequestServiceInfoScreen;
import jagacyVT.screens.WorkWithRequestScreen;
import myEstesPages.MyEstesAddCommodityPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesCommodityLibraryPage;
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
import shipmentTrackingPages.ShipmentTrackingPage;
import testBase.TestBase;
import util.SQLDataList;
import util.TestListener;
import util.TestUtil;

public class RateQuoteRegressionTest extends TestBase {

	private Logger logger = Logger.getLogger(RateQuoteRegressionTest.class.getClass());

	TestListener testListener;
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	RateQouteRateEstimatePage rateEstimatePage = new RateQouteRateEstimatePage();
	RateQuotePage ltlRateQuotePage = new RateQuotePage();
	RatesPage ratesPage = new RatesPage();
	RateQouteRateRequestPage rateRequestPage = new RateQouteRateRequestPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQouteQuoteHistoryPage QuoteHistoryPage = new RateQouteQuoteHistoryPage();
	ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage();
	RateQouteAccountSearch ltlAccountSearch = new RateQouteAccountSearch();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();;
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
	ENetORPage eNetORPage = new ENetORPage();
	ENetQuoteAuditTrailPage eNetQuoteAuditTrailPage = new ENetQuoteAuditTrailPage();
	ENetFloorMinsDefinitionPage eNetFloorMinsDefinitionPage = new ENetFloorMinsDefinitionPage();
	ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
	ENetVTLRateQuotePage eNetVTLRateQuotePage = new ENetVTLRateQuotePage();
	ENetQuoteHistoryLookupPage eNetQuoteHistoryLookupPage = new ENetQuoteHistoryLookupPage();
	SQLDataList sqlDataList = new SQLDataList();
	MyEstesCommodityLibraryPage myEstesCommodityLibraryPage = new MyEstesCommodityLibraryPage();
	MyEstesAddCommodityPage myEstesAddCommodityPage = new MyEstesAddCommodityPage();
	VolumeAndTruckloadQuoteMaintenance volumeAndTruckloadQuoteMaintenance = new VolumeAndTruckloadQuoteMaintenance();

	/******************************* TESTS *******************************/

	/**
	 * 
	 * Test passed on 6/23/22 Rate Quotes - Commodities - Verify record selected
	 * from the 'Commodity Library Modal' is saved properly.< dp2-2494>
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_6767() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		// Verifying the pre-requisite //Added
		rateQuotePage.clickOnAddFromCommodityLibrary();
		testUtil.setHardWait(5000);
		boolean dataExistense = rateQuotePage.verifyDataDisplayedInCommodityLibraryModal();
		System.out.println(dataExistense);
		if (dataExistense == false) { // Steps to Add data if doesn't exist

			myEstesLoginPage.clickOnMyEstes();
			myEstesLoginPage.clickOnCommodityLibrary();
			testUtil.setHardWait(3000);
			myEstesCommodityLibraryPage.clickOnAddCommodity();
			myEstesAddCommodityPage.enterProductID("1001");
			myEstesAddCommodityPage.enterGoodsQuantity("10");
			myEstesAddCommodityPage.selectGoodsType("BOX");
			myEstesAddCommodityPage.typeDescription("Test");
			myEstesAddCommodityPage.EnterTotalWeight("100");
			myEstesAddCommodityPage.selectClass("55");
			myEstesAddCommodityPage.clickOnSaveButton();
			testUtil.setHardWait(3000);

			myEstesWelcomePage.clickOnShipTab();
			myEstesWelcomePage.clickOnRateQoute();

		}
		testUtil.setHardWait(2000);

		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName();
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("qatest@estes-express.com");
		rateQuotePage.enterTerms("Collect");
		rateQuotePage.enterPhoneNo("2021234567");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("90007");
		rateQuotePage.selectTodayDate();
		rateQuotePage.clickOnAddFromCommodityLibrary();
		rateQuotePage.verifyAddFromCommodityLibraryPage();
		rateQuotePage.clickFirstCommodityOnCommodityLibrary();
		// rateQuotePage.addFromCommodityLibrary();
		rateQuotePage.verifyNewCommodityRecordAdded();
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyRateQuotePageTitle();

	}

	/**
	 * This test passsed on 6/23/22
	 * 
	 * Rate Quotes - Requestor Information - Verify record selected from the Address
	 * Book Modal' is saved properly to the form. <dp2-2368>
	 */

	@Test(enabled = true, priority = 2)

	public void executeQZ_6768() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.verifyCreateRateQoutePage();
		rateQuotePage.clickOnAddressBookLink();
		myEstesAddressBookPage.validateAddressBookPageTitle();
		testUtil.setHardWait(1000);
		myEstesAddressBookPage.selectRecordFromAddressBookPage();
		rateQuotePage.validateRequesterInformationField();
		rateQuotePage.clickOnAddressBookLink();
		myEstesAddressBookPage.validateAddressBookPageTitle();
		testUtil.setHardWait(2000);
		myEstesAddressBookPage.selectSecondRecordFromAddressBookPage();
		rateQuotePage.validateRequesterInformationField();
	}

	// THIS TEST IS NOT CALCULATING TRANSIT CHARGES AND WILL REMAIND TURNED OFF
	// UNTIL THE ISSUE IS FIXED.

	/*
	 * Time Critical Rate Quote - Verify Guaranteed LTL Standard Transit/Time
	 * Critical rates are not generated if total linear feet exceeds 12.
	 */

	@Test(enabled = true, priority = 3)

	public void executeQZ_3192() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.enterContactName("kaiser wali");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("qatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8885551211");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("3000");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.clickOnAddCommodityButton();
		rateQuotePage.enterClass2("50");
		rateQuotePage.enterPieces2("2");
		rateQuotePage.enterPieceType2("PALLET");
		rateQuotePage.enterTotalWeight2("1200");
		rateQuotePage.enterLength2("49");
		rateQuotePage.enterWidth2("48");
		rateQuotePage.enterHeight2("48");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyBasicCharge(" LTL Standard Transit", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);
		testUtil.setHardWait(500);
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.enterLength2("48");
		rateQuotePage.clikOnSubmitButton();
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 5PM", true);
	}
	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link displayed when Origin
	 * terminal is located in Hawaii.
	 */

	@Test(enabled = true, priority = 4)

	public void executeQZ_3183() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QZ-3183");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("5546648842");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("96708");
		rateQuotePage.enterDesZip("89107");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("2700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validates");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link is displayed when Origin
	 * terminal is located in Canada.
	 */

	@Test(enabled = true, priority = 5)

	public void executeQZ_3181() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("EIT Tester");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("2023035544");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("Canada");
		rateQuotePage.enterOriginZip("G1A0A7");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("60099");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validates");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link displays when Origin Terminal
	 * is located in Puerto Rico.
	 */

	@Test(enabled = true, priority = 6)

	public void executeQZ_3171() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("4154164117");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("00907");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("33109");
		rateQuotePage.enterPieces("20");
		rateQuotePage.enterPieceType("CARTON");
		rateQuotePage.enterTotalWeight("3700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("36");
		rateQuotePage.enterDesc("This test case validates what you test");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		rateQuotePage.selectDestinationCountry("Canada");
		rateQuotePage.enterDesZip("G1N4G1");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link is displayed when Destination
	 * terminal is located in Puerto Rico.
	 */

	@Test(enabled = true, priority = 7)

	public void executeQZ_3165() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("6504654454");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("90007");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("00602");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Contact Me link displayed when the Destination
	 * terminal is located in Mexico.
	 */

	@Test(enabled = true, priority = 8)

	public void executeQZ_3163() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("6540646461");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("Mexico");
		rateQuotePage.enterDesZip("44100");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		testUtil.setHardWait(40000);
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		rateQuotePage.selectOriginCountry("Canada");
		rateQuotePage.enterOriginZip("G1A0A7");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * Select Service Level - Verify if 'Pickup Date' is on a Saturday, Charges are
	 * not calculated for Volume and Truckload Guaranteed Standard and economy.
	 * 
	 */

	@Test(enabled = true, priority = 9)

	public void executeQZ_3155() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("6540646461");

		rateQuotePage.selectNextSaturday();

		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("9700");
		rateQuotePage.enterLength("24");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.clickOnReviseQouteButton("Volume and Truckload Basic");
		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		rateQuotePage.selectTodayDate();
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Quote displays Contact Me link when Origin
	 * terminal is located in Alaska.
	 */

	@Test(enabled = true, priority = 10)

	public void executeQZ_3149() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("6540646461");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("99507");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23230");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Quote displays Contact Me link when Origin
	 * terminal is an Interline point.
	 */

	@Test(enabled = true, priority = 11)

	public void executeQZ_3148() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("94937");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23059");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("59773");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link displays when Origin terminal
	 * is an Agent point.
	 */

	@Test(enabled = true, priority = 12)

	public void executeQZ_3147() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.selectTodayDate();

		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("02672");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23230");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * Rate Quote - Verify Guaranteed Volume and Truckload rates are not generated
	 * if dimensions total linear feet exceeds 23
	 */

	/*
	 * @Test(enabled = true, priority = 13)// Duplicate of QZ-3150
	 * 
	 * public void
	 * qz3143_verifyGuarantyVolumeAndTruckloadRatesAreNotGeneratedIfDimensionsTotalLinearExceeds23
	 * () throws InterruptedException {
	 * 
	 * myEstesHomePage.clickOnMyEstes(); myEstesHomePage.clickOnLogin();
	 * myEstesLoginPage.enterUserName(username4);
	 * myEstesLoginPage.enterPassword(password4);
	 * myEstesLoginPage.clickOnLoginButton();
	 * 
	 * myEstesWelcomePage.clickOnShipTab(); myEstesWelcomePage.clickOnRateQoute();
	 * 
	 * rateQuotePage.selectOrDeselectValumeAndTruckload();
	 * rateQuotePage.selectOrDeselectTimeCriticalExpedited();
	 * rateQuotePage.selectOrDeselectLessThanTruckload();
	 * 
	 * rateQuotePage.enterContactName("QA Testing");
	 * rateQuotePage.enterMyRole("Third Party");
	 * rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
	 * rateQuotePage.enterTerms("Prepaid");
	 * rateQuotePage.enterPhoneNo("8885551212"); rateQuotePage.selectTodayDate();
	 * rateQuotePage.selectOriginCountry("United States");
	 * rateQuotePage.enterOriginZip("16159");
	 * rateQuotePage.selectDestinationCountry("United States");
	 * rateQuotePage.enterDesZip("30307"); rateQuotePage.enterClass("50");
	 * rateQuotePage.enterPieces("3"); rateQuotePage.enterPieceType("PALLET");
	 * rateQuotePage.enterTotalWeight("3000"); rateQuotePage.enterLength("92");
	 * rateQuotePage.enterWidth("50"); rateQuotePage.enterHeight("50");
	 * rateQuotePage.enterDesc("This is test case validate");
	 * rateQuotePage.clikOnSubmitButton();
	 * 
	 * rateQuotePage.verifyBasicCharge("olume and Truckload Basic", true);
	 * rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy",
	 * true);
	 * rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard",
	 * true);
	 * 
	 * rateQuotePage.clickOnReviseQouteButton(); rateQuotePage.enterLength("93");
	 * rateQuotePage.clikOnSubmitButton();
	 * 
	 * rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy",
	 * false);
	 * rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard",
	 * false); }
	 */

	/*
	 * MyEstes VTL Rate Quote Verify Contact Me link displays when Destination
	 * terminal is located in Alaska.
	 */

	@Test(enabled = true, priority = 14)

	public void executeQZ_3141() throws InterruptedException {

		// Step 1: Launch application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// step 2: Login as admin
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Click on *Ship* and click *Rate Quote* in the sub-menu
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Step 4: In the *Select Quote Type* section, select *Volume and Truckload
		// (incl. Guaranteed)*; deselect *Less Than Truckload*
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Step 5: Enter Requester info
		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8885551212");

		// Step 6: In the *Pickup Details* section, enter or select the following value:
		rateQuotePage.selectTodayDate();

		// Step 7: Enter routing information: Origin 16159, Destination 99502
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("99502");

		// Step 8: Enter Commodity details
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");

		// Step 9: Click *Submit Request* button
		rateQuotePage.clikOnSubmitButton();

		// Step10: From Rate Quotes page 2, verify Volume and Truckload Basic Charges
		// equal "Contact Me" link.
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);

		// Step 11: From Volume and Truckload Rate Quote page 2 verify Guaranteed Volume
		// and Truckload Standard and Economy charges are not calculated; blank instead
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		// Step 12: From Rate Quotes page 2 under the progress bar, click *Revise Quote*
		// button
		rateQuotePage.clickOnReviseQouteButton();

		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		// Step 13: In the *Routing Information* section, update the *Origin* field
		// Note: From Alaska to Alaska
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("99567");

		// Step 14: Click Submit
		rateQuotePage.clikOnSubmitButton();

		// Step 15: From Rate Quotes page 2, verify Volume and Truckload Basic Charges
		// equal "Contact Me" link.
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);

		// Step 16: From Rate Quote page 2, verify Guaranteed Volume and Truckload
		// Standard and Economy charges are not calculated and are blank instead.
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * MyEstes VTL Rate Quote Verify Contact Me link displays if Destination
	 * terminal is an Interline point
	 */

	@Test(enabled = true, priority = 15)

	public void executeQZ_3140() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("98396");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link displayed when Destination
	 * terminal is located in Hawaii.
	 */

	@Test(enabled = true, priority = 16)

	public void executeQZ_3135() throws InterruptedException {
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("5046042244");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("96807");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Guaranteed Standard and Economy
	 * Volume and Truckload rates are not calculated if keyed in total linear feet
	 * exceeds 23.
	 */

	@Test(enabled = true, priority = 17)

	public void executeQZ_3126() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("7500");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.enterLinearFeet("28");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();

		rateQuotePage.enterLinearFeet("27");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);
	}

	/** Update the test per new changes hence turned on the test **/
	/*
	 * Rate Quote - Volume and Truckload - Verify when 'Secured Locations Pickup
	 * prisons, military bases, airports, etc ...' accessorial is selected Volume
	 * and Truckload Guaranteed Economy and Standard charges are blank.
	 */

	@Test(enabled = true, priority = 18)
	public void executeQZ_3190() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("6200");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validate");
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectSecuredLocationsPickupCheckBox();
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false); // Updated per manual test
		rateQuotePage.verifyContactMeByServiceLevel("Guaranteed Volume and Truckload Economy");
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
		rateQuotePage.verifyContactMeByServiceLevel("Guaranteed Volume and Truckload Standard");// Updated per manual
																								// test

	}

	/**
	 * 
	 * this test passed on 7/25/2022 Rate Quote - Volume and Truckload - Quote
	 * Details - Verify Accessorials are displayed as separate line items.
	 */

	@Test(enabled = true, priority = 19)

	public void executeQZ_3178() throws InterruptedException {

		String disclosures = "RATE QUOTE DISCLOSURES AND TERMS OF SERVICE";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("4154164117");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23059");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("This is test case validation");
		// rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.clickOnViewAllAccessorials(); // -- step is needed
//		rateQuotePage.selectOrDeselectInsideDelivery();
//		rateQuotePage.clickOnViewAllAccessorials();    --commanded
		// rateQuotePage.selectOrDeselectLiftGateServiceDelivery();
		rateQuotePage.selectOrDeselectAccessorialsList("Lift-Gate Service (Delivery)");
		testUtil.setHardWait(500);
		// rateQuotePage.selectOrDeselectAppointmentRequest();
		rateQuotePage.selectOrDeselectAccessorialsList("Appointment Request");
		testUtil.setHardWait(500);
		rateQuotePage.selectOrDeselectAccessorialsList("Inside Pickup");
		
		rateQuotePage.selectOrDeselectAccessorialsList("Notify Request");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
		rateQuotePage.verifySelectedAccessorials();

		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.clickOnViewAllAccessorials();
		testUtil.setHardWait(4000);
		rateQuotePage.selectOrDeselectAccessorialsList(" Unloading Services Requested By Consignee ");

		testUtil.setHardWait(2000);
		rateQuotePage.selectOrDeselectSchoolDeliveryAccessorial();
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("Guaranteed Volume and Truckload Standard");
		rateQuotePage.recordQuoteNumber("Volume and Truckload Standard");
		rateQuotePage.verifySelectedAccessorials();
		rateQuotePage.verifyDisclaimerMessageIsDisplayed(disclosures);
	}

	/*
	 * Rate Quote - Quote Details - Verify 'LTL Deficit Rate' exist and is added as
	 * a commodity line item.
	 */

	@Test(enabled = true, priority = 20)

	public void executeQZ_3133() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8043531900");
		testUtil.setHardWait(5000);
		rateQuotePage.enterPhoneExtentionNum("3170");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23233");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4780");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("50");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyRateQuotePageTitle();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.verifyCommoditiesSection();
		rateQuotePage.verifyCommoditiesDetails();

	}

	/*
	 * Rate Quote - LTL - Verify 'Contact Us' is displayed for Guaranteed service
	 * levels when Limited Access Accessorial is selected.
	 */

	@Test(enabled = true, priority = 21)

	public void executeQZ_820() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.enterAccountNumber("6202474");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("975");

		rateQuotePage.clickOnViewAllAccessorials();
		testUtil.setHardWait(2000);
		rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
		rateQuotePage.selectOrDeselectExhibitionChargeCallForChargesForLasVegasChicagoOrNY();
		rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
		rateQuotePage.selectOrDeselectSecuredLocationsPickupPrisonsMilitaryBases();
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.selectOrDeselectExhibitionChargeCallForChargesForLasVegasChicagoOrNY();
		rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
		rateQuotePage.selectOrDeselectSecuredLocationsPickupPrisonsMilitaryBases();
		rateQuotePage.selectOrDeselectConstructionSiteDelivery();
		rateQuotePage.selectOrDeselectResidentialDelivery();
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 12PM", true);

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Contact Me link is displayed when
	 * Picks up from a prohibited Food Warehouse Distribution Center site
	 */

	@Test(enabled = true, priority = 22)

	public void executeQZ_3174() throws Exception {

		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnFoodWhseTab();
		eNetFoodWarehousePage.selectCompany("Lowe's");
		eNetFoodWarehousePage.selectCountry("United States");
		eNetFoodWarehousePage.entryZipCode("30307");
		eNetFoodWarehousePage.entryRate("1");
		eNetFoodWarehousePage.checkProhibited();
		eNetFoodWarehousePage.clickOnAddButton();

		testUtil.openNewTab(url);

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("QA EIT TESTS");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("6452453444");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23233");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("50");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clikOnFreightPickUpOrDeliverAtFoodWarehouseOrDistributionCenter();
		rateQuotePage.selectWarehouse("Lowe");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("90007");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);

		testUtil.switchToParentWindow();
		eNetFoodWarehousePage.deleteCompanyNameIfExistInTheTable();

	}

	/**
	 * The test fails becaue Get rate quote button is not appearing for 10 AM
	 * 
	 * Rate Quote - Guaranteed by 10AM/12PM/5PM/Exclusive use - Quote Details -
	 * Verify a message advising the user to type 'Guaranteed xxx' on their document
	 * if they are not using Estes BOL is displayed <qz-6766>
	 */

	@Test(enabled = true, priority = 23)
	public void executeQZ_6766() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("QA EIT TESTS");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		testUtil.setHardWait(1000);
		rateQuotePage.enterPhoneNo("8043531900");
		testUtil.setHardWait(3000);
		rateQuotePage.enterPhoneExtentionNum("1234567");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("7500");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Guaranteed Message");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyRateQuotePageTitle();
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.verifyMessageForRateQuote();

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.verifyMessageForRateQuote();

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");
		rateQuotePage.verifyMessageForRateQuote();

		rateQuotePage.clickOnGetQuoteButton("Guaranteed Exclusive Use");
		rateQuotePage.verifyMessageForRateQuote();

	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Contact Me link displayed when
	 * Drops off at a prohibited Warehouse Distribution Center site. <qz-3154>
	 */

	@Test(enabled = true, priority = 24)

	public void executeQZ_3154() throws Exception {

		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnFoodWhseTab();
		eNetFoodWarehousePage.selectCompany("Lowe's");
		eNetFoodWarehousePage.selectCountry("United States");
		eNetFoodWarehousePage.entryZipCode("30307");
		eNetFoodWarehousePage.entryRate("1");
		eNetFoodWarehousePage.checkProhibited();
		eNetFoodWarehousePage.clickOnAddButton();

		testUtil.openNewTab(url);

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();

		rateQuotePage.enterContactName("QA EIT TESTS");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("4045046644");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23233");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clikOnFreightPickUpOrDeliverAtFoodWarehouseOrDistributionCenter();
		rateQuotePage.selectWarehouse("Lowe");
		rateQuotePage.clikOnSubmitButton();
		testUtil.setHardWait(1000);
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23060");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);

		testUtil.switchToParentWindow();
		eNetFoodWarehousePage.deleteCompanyNameIfExistInTheTable();

	}

	/*
	 * MyEstes VTL Rate Quote Verify a message informing the customer that Volume
	 * and Truckload charges are not calculated due to aged VTL account
	 * receivables.<qz-3169>
	 */

	@Test(enabled = true, priority = 25)

	public void executeQZ_3169() throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username11);
		myEstesLoginPage.enterPassword(password11);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		String AccNum = myEstesWelcomePage.recordAccountNumber();

		testUtil.openNewTab(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnAgedARTab();
		eNetAgedARPage.enterAccountNumber(AccNum);
		eNetAgedARPage.typecomments("est account set to Aged " + testUtil.todaysDate());
		eNetAgedARPage.clickOnAddButton();

		testUtil.switchToParentWindow();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA EIT TESTS");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8048404465");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		// rateQuotePage.clikOnSubmitButton();////used method with Do While loop for
		// submit button
		rateQuotePage.clickOnSubmitButton();

		// rateQuotePage.verifyAttentionMessage();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		testUtil.setHardWait(3000);
		testUtil.switchToWindow(1);
		testUtil.setHardWait(2000);
		eNetAgedARPage.filterAccount();

		eNetAgedARPage.deleteAccountNumberIfExistInTheTable();
		testUtil.switchToParentWindow();
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);
	}

	/*
	 * MyEstes VTL Rate Quote - Verify Contact Me link is displayed when Origin
	 * terminal is located in Mexico.
	 */

	@Test(enabled = true, priority = 26)

	public void executeQZ_3184() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName(" Testers QA");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterPhoneNo("8046059977");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("Mexico");
		rateQuotePage.enterOriginZip("26700");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("75007");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * Rate Quote - National Account customers cannot enter accounts not tied to
	 * them
	 */

	@Test(enabled = true, priority = 27)
	public void executeQZ_3095() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("testnat");
		myEstesLoginPage.enterPassword("testnat");
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();

		rateQuotePage.enterContactName();
		rateQuotePage.enterAccountNumber("5023958");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterPhoneNo("8046059977");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("90001");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23219");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("1500");
		rateQuotePage.enterLength("36");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("National Account Test");
		rateQuotePage.clickOnSubmit();

		rateQuotePage.varifyEnterValidAccountNumberError();
		rateQuotePage.enterAccountNumber("4136995");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

	}

	// STEPS UPDATED & REVIEWED :11/22/2019//

	/*
	 * Rate Quote - Verify Guaranteed Volume and Truckload rates are not generated
	 * if dimensions total linear feet exceeds 23
	 */

	@Test(enabled = true, priority = 28)

	public void executeQZ_3150() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QZ-3150");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("8046059977");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("3000");
		rateQuotePage.enterLength("80");
		rateQuotePage.enterWidth("50");
		rateQuotePage.enterHeight("50");
		rateQuotePage.enterDesc("Max Linear Feet for Guaranteed V/TL");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);

		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();

		rateQuotePage.enterLength("82");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		// rateQuotePage.recordRateQouteNumber();
		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
	}

	// MANUAL STEPS ARE BEING UPDATED. WILL TURN IT ON WHEN IT IS READY FOR
	// AUTOMATION REVIEW.

	/*
	 * Rate Quote - Commodities - Verify if 'Piece Type' equals Truckload, total
	 * linear feet is set to 53, guaranteed service levels are not calculated and a
	 * message is displayed.
	 */

	/*
	 * Per Nora, this test case is superseded by QZ-9474. We can pull it out of the
	 * regression run. 
	 * 
	 */

	/*
	 * @Test(enabled = false, priority = 31)//MOVED TO FLACKY TEST CLASS public void
	 * qz6950_verifyIfPieceTypeEqualsTruckloadTotalLinearFeetIsSetTo53GuaranteedServiceLevelsAreNotCalculatedAndMessageIsDisplayed
	 * () throws InterruptedException {
	 * 
	 * logger.
	 * info("********************FAILS-MESSAGE NOT DISPLAYED*******************");
	 * 
	 * myEstesHomePage.clickOnMyEstes(); myEstesHomePage.clickOnLogin();
	 * myEstesLoginPage.enterUserName(username4);
	 * myEstesLoginPage.enterPassword(password4);
	 * myEstesLoginPage.clickOnLoginButton();
	 * 
	 * myEstesWelcomePage.clickOnShipTab(); myEstesWelcomePage.clickOnRateQoute();
	 * 
	 * rateQuotePage.selectOrDeselectValumeAndTruckload();
	 * rateQuotePage.selectOrDeselectLessThanTruckload();
	 * 
	 * rateQuotePage.enterContactName();
	 * rateQuotePage.enterYourEmail("eitqa@estes-express.com");
	 * rateQuotePage.enterMyRole("Shipper");
	 * rateQuotePage.enterPhoneNo("4154164117");
	 * rateQuotePage.enterTerms("Prepaid"); rateQuotePage.selectTodayDate();
	 * rateQuotePage.selectOriginCountry("United States");
	 * rateQuotePage.enterOriginZip("30307");
	 * rateQuotePage.selectDestinationCountry("United States");
	 * rateQuotePage.enterDesZip("23059"); rateQuotePage.enterClass("55");
	 * rateQuotePage.enterPieces("4"); rateQuotePage.enterPieceType("TRUCKLOAD");
	 * rateQuotePage.enterTotalWeight("9700"); rateQuotePage.enterLength("48");
	 * rateQuotePage.enterWidth("48"); rateQuotePage.enterHeight("48");
	 * rateQuotePage.enterDesc("This is test case validation");
	 * rateQuotePage.clickOnSubmitButton();
	 * 
	 * rateQuotePage.verifyMessage();
	 * 
	 * rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
	 * rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
	 * rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
	 * 
	 * }
	 */

	/*
	 * Rate Quote - Verify when Secured Divider Service accessorial is selected,
	 * Guaranteed Volume and Truckload Economy and Standard charges are blank
	 */

	// THIS IS KNOW FAILURE. AUTOMATION STEPS NEED TO BE
	@Test(enabled = true, priority = 29)
	public void executeQZ_3191() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QZ-3191");
		rateQuotePage.enterYourEmail("qa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("5");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("6200");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is a validation step");
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectSecuredDividerService();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * Rate Quote - Verify when PRISDL is selected as an accessorial Guaranteed
	 * Volume and Truckload Economy and Standard charges are blank
	 */

	@Test(enabled = true, priority = 30)
	public void executeQZ_3189() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Test");
		rateQuotePage.enterYourEmail("qa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("2042052002");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("6200");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);
	}

	/*
	 * Rate Quote - Volume and Truckload - Verify a message informing the customer
	 * that Volume and Truckload Guaranteed charges are not calculated due to aged
	 * TC account receivables
	 */

	@Test(enabled = true, priority = 31)
	public void executeQZ_3179() throws Exception {

		driver.navigate().to(url2);
		eNetLoginPage.enterUserID("qaenet01");
		eNetLoginPage.enterUserPassword("qaenet01");
		eNetLoginPage.clickOnLoginButton();
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnAgedARTab();
		testUtil.setHardWait(2000);
		eNetAgedARPage.enterAccountNumber("5023958");
		eNetAgedARPage.typecomments("Test Account set to Aged on" + testUtil.todaysDate());
		testUtil.setHardWait(2000);
		eNetAgedARPage.clickOnAddButton();

		testUtil.setHardWait(2000);
		testUtil.openNewTab(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("testadmin1");
		myEstesLoginPage.enterPassword("testadmin1");
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Test");
		rateQuotePage.enterYourEmail("qa@estes-express.com");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterPhoneNo("2042052002");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		// rateQuotePage.clikOnSubmitButton(); //used method with Do While loop for
		// submit button
		rateQuotePage.clickOnSubmitButton();
		// rateQuotePage.verifyAttentionMessage();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		testUtil.switchToParentWindow();
		testUtil.setHardWait(2000);
		eNetAgedARPage.filterAccount();
		eNetAgedARPage.deleteAccountNumberIfExistInTheTable();
		testUtil.switchToWindow(2);
		rateQuotePage.clickOnReviseQouteButton();
		// rateQuotePage.clikOnSubmitButton();//used method with Do While loop for
		// submit button
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", true);

	}

	/*
	 * Rate Quote - LTL - Verify Manhattan and Alaska Freight and Fuel line items
	 * and charges display under Charge Items when they are selected as Origin and
	 * Destination.
	 */

	@Test(enabled = true, priority = 32)
	public void executeQZ_829() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.enterAccountNumber("6202474");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("10014");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("99502");
		rateQuotePage.enterClass("100");
		rateQuotePage.enterTotalWeight("987");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnSubmitButton();

		// testUtil.waitForNextPageToBeReady();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyChargeItemsTable("Manhattan, NY Pickup Charge", "$0.00");
		rateQuotePage.validateChargeItemsTableDescription("ALASKA FREIGHT CHARGES");
		rateQuotePage.validateChargeItemsTableDescription("ALASKA FUEL SURCHARGE");

	}

	/**
	 * 
	 * this test has validation error This test still fails after updated by Manisha
	 * Rate Quote - Time Critical/Expedited - Verify an error message displays if
	 * Total Weight' is greater than 42,500 pounds.
	 */

	@Test(enabled = true, priority = 33)
	public void executeQZ_7046() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username11);
		myEstesLoginPage.enterPassword(password11);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Test");
		rateQuotePage.enterYourEmail("qa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("90007");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("42501");
		rateQuotePage.enterLength("8");
		rateQuotePage.enterWidth("12");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.verifyCommodityWeightErrorMessage();
		testUtil.setHardWait(1000);
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		rateQuotePage.verifyValidationErrorMessage();

	}

	/*
	 * MyEstes VTL Rate Quote-Verify requestor Name Phone and Email Address are
	 * displayed on Quote Details.
	 */

	@Test(enabled = true, priority = 34)
	public void executeQZ_3173() throws InterruptedException {

		String name = "QA Test";
		String phone = "(645) 214-5522";
		String email = "eitqa@estes-express.com";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName(name);
		rateQuotePage.enterYourEmail(email);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo(phone);
		rateQuotePage.enterTerms("Prepaid");

		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("9700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		rateQuotePage.verifyVTLBasicContactName(name);
		rateQuotePage.verifyVTLBasicContactPhone(phone);
		rateQuotePage.verifyVTLBasicContactEmail(email);

	}

	/*
	 * VTL Rate Quote - Verify when EXHIB selected as an accessorial charge no
	 * calculated amounts are displayed for Volume and Truckload Guaranteed Standard
	 * or Economy services.
	 */

	@Test(enabled = true, priority = 35)
	public void executeQZ_3151() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("QA Test");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("6452145522");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("9700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectExhibitionCharge();
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * Rate Quote - Determine if the request qualifies for VTL Guaranteed service
	 * per Time Critical service guidelines
	 */

	@Test(enabled = true, priority = 36)
	public void executeQZ_5983() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		;

		rateQuotePage.enterContactName("QA Testing");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterPhoneNo("8044142255");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("24");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Guaranteed Standard", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Guaranteed Economy", true);

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
		rateQuotePage.clickOnReviseQouteButtonUnderVTLQouteDetails();
		rateQuotePage.verifyFieldsArePopulatedWithCurrentQuoteData();

		rateQuotePage.selectNextSaturday();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Guaranteed Standard", false);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Guaranteed Economy", false);

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
		rateQuotePage.clickOnReviseQouteButtonUnderVTLQouteDetails();

		rateQuotePage.selectTodayDate();
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectSecuredDividerService();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

	}

	/*
	 * Rate Quote - Verify Guaranteed Volume and Truckload charges are calculated
	 * for qualifying request.
	 */

	@Test(enabled = true, priority = 37)
	public void executeQZ_3152() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("QA Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("6244453322");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("77007");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("60607");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("5");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("9700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("62");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clikOnSubmitButton();

		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			Connection con = DriverManager.getConnection("jdbc:as400:10.28.49.11/exla", "QATSTFRTBL", "qatest2019");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * From fbfiles.FRP011 where ACKEY='GMV2");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/*
	 * MyEstes VTL Rate Quote Details Verify user can schedule a pickup after
	 * booking a shipment.
	 */

	@Test(enabled = true, priority = 38)
	public void executeQZ_3124() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("qa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("2021234567");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("16159");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("2700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		rateQuotePage.clikOnRequestPickup();
		myEstesPickupRequestPage.verifyPageTitle();

	}

	/*
	 * MyEstes VTL Rate Quote Details Verify user can create a BOL after booking a
	 * shipment.
	 */

	@Test(enabled = true, priority = 39)
	public void executeQZ_3123() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("8041245522");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("6700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This test case validates BOL data");

		rateQuotePage.selectOrDeselectAccessorialsList("Inside Delivery");
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectAccessorialsList("Unloading Services Requested By Consignee");

		rateQuotePage.clickOnSubmitButton();

		// Step 11: From the *Results* table,
		// select *Get Quote #* for any guaranteed service level and record the Quote
		// Number
		rateQuotePage.clickOnGetQuoteButton("Guaranteed Exclusive Use");
		String quoteNum = rateQuotePage.recordQuoteNumber("Guaranteed Exlusive Use");

		// Step 12: From the *Details section* Select the *I accept the Terms and
		// Conditions* check box
		rateQuotePage.clickOnTermsAndConditionsFor("Guaranteed Exclusive Use");

		// Step 13: Click *Reserve Shipment* button
		rateQuotePage.clickOnResreveShipmentFor("Guaranteed Exclusive Use");
		rateQuotePage.verifyRequestReceivedMessage();

		// Step 14: Select *Create BOL*
		rateQuotePage.clickOnCreateBOL();
		testUtil.setHardWait(20000);
		myEstesBillOfLadingPage.verifyBOLQuoteNumber(quoteNum);

		// Step 15: From the *Quote Details* section, verify *Enter Quote #* field is
		// populated with the correct data
		myEstesBillOfLadingPage.verifyBOLQuoteNumber(quoteNum);

		// Step 16: Scroll down to the *Accessorials* section, verify the following are
		// selected
		myEstesBillOfLadingPage.verifySelectedAccessorialName("Inside Delivery");
		myEstesBillOfLadingPage.verifySelectedAccessorialName("Unloading Services Requested By Consignee");

		// Step 17: Scroll down to the *Shipper and Consignee Details* section, verify
		// the following data
		myEstesBillOfLadingPage.verifyShipperInfo("WEST MIDDLESEX", "PA", "16159");
		myEstesBillOfLadingPage.verifyConsigneeInfo("ATLANTA", "GA", "30307");

		// Step 18: Scroll down to the *Commodities* section, verify the following data
		myEstesBillOfLadingPage.verifyCommoditiesSection("10", "PALLET", "6700", "50",
				"This test case validates BOL data");

	}

	/*
	 * MyEstes VTL Rate Quote Volume and Truckload Guaranteed Economy service
	 * delivery date equals standard LTL service delivery date plus 1 day.
	 */

	@Test(enabled = true, priority = 40)
	public void executeQZ_3117() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.verifyRateQuotePageTitle();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("5042043311");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4500");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation BOL data");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyGuarantedLTLstandardTransit5PMdeliveryDate();
		rateQuotePage.verifyGuaranteedVolumeAndTruckloadEconomyDeliveryDate();
	}

	/*
	 * Rate Quote - LTL - Verify deficit weight information is displayed in
	 * Commodities section of Quote Details.
	 */

	@Test(enabled = true, priority = 41)
	public void executeQZ_840() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("480");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyCommoditiesDetails();

	}

	/*
	 * MyEstes Rate Quote Volume and Truckload Guaranteed Standard service delivery
	 * date equals standard LTL service delivery date by 5pm.
	 */

	@Test(enabled = true, priority = 42)

	public void executeQZ_3119() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password4);

		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		rateQuotePage.enterContactName("Testing EIT");
		rateQuotePage.enterYourEmail("etiqa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("2021234567");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4500");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This is test case validation");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyGuarantedLTLstandardTransit5PMdeliveryDate();
		rateQuotePage.verifyGuaranteedVolumeAndTruckloadEconomyDeliveryDate();
		rateQuotePage.verifyGuaranteedVolumeAndTruckloadStandartDeliveryTime();
	}

	// ALL THE ABOVE 50 SCRIPTS WERE UPDATED ON 12/2/2019

	/**
	 * This test passed on 6/23/22
	 * 
	 * Rate Quote - Select Quote Type - Verify Time Critical/Expedited and Volume
	 * and Truckload (incl. Guaranteed) check boxes are disabled for unauthorized.
	 */

	@Test(enabled = true, priority = 43)

	public void executeQZ_7140() throws InterruptedException {

		// Launch eNet application
		driver.get(url2);

		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnMyEstesManagementToolLink();
		eNetMyEstesManagementToolPage.enterUserName("TESTSAT");
		eNetMyEstesManagementToolPage.enterAccountCode("5068692");
		eNetMyEstesManagementToolPage.clickOnSearchButton();

		/*
		 * if(!eNetMyEstesManagementToolPage.selectTestSAT()) {
		 * eNetMyEstesManagementToolPage.clickOnNextActivePage();
		 * eNetMyEstesManagementToolPage.selectTESTSAT(); }
		 */
		eNetMyEstesManagementToolPage.selectTESTSAT();

		eNetMyEstesUserDetailPage.verifyTitle();

		eNetMyEstesUserDetailPage.clickOnEditMenuItemsLink();
		eNetMyEstesUserDetailPage.removeCode("A55 Time Critical Rate Quote");
		eNetMyEstesUserDetailPage.removeCode("A70 VTL Rate Quote");
		eNetMyEstesUserDetailPage.addCode("C01", "LTL Rate Request");
		eNetMyEstesUserDetailPage.addCode("A60", "LTL Rate Quote");

		// Launch My Estes application
		testUtil.openNewTab(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		myEstesLoginPage.enterUserName(username6);
		myEstesLoginPage.enterPassword(password6);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.verifyQuoteTypeIsDisable("Time Critical");
		rateQuotePage.verifyQuoteTypeIsDisable("Volume and Truckload");
		rateQuotePage.verifyRestrictedMessage();

		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("60002");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("487");
		rateQuotePage.enterDesc("This is test case validates");

		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.clickOnReviseQouteButton("LTL Standard Transit");

		rateQuotePage.verifyQuoteTypeIsDisable("Time Critical/Expedited");
		rateQuotePage.verifyQuoteTypeIsDisable("Volume and Truckload (incl. Guaranteed)");
		rateQuotePage.verifyRestrictedMessage();
	}

	/*
	 * MyEstes Rate Quote - Verify Rate Quote application is not available for
	 * unauthorized users and a permission message is displayed.
	 */

	@Test(enabled = true, priority = 44)
	public void executeQZ_7138() throws Exception {

		driver.navigate().to(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		/*
		 * eNetHomePage.clickOnApplicationsTab();
		 * eNetApplicationsPage.clickOnMyEstesManagementTool();
		 * eNetMyEstesManagementToolPage.enterAccountCode("5068692");
		 * eNetMyEstesManagementToolPage.clickOnSearch();
		 * eNetMyEstesManagementToolPage.clickOnNextActivePage();
		 * eNetMyEstesManagementToolPage.clickOnTestSat();
		 * eNetMyEstesManagementToolPage.clickOnEditMenuItems();
		 * 
		 * eNetMyEstesManagementToolPage.clickOnRemove("C01", "LTL Rate Request");
		 * eNetMyEstesManagementToolPage.clickOnRemove("A60", "LTL Rate Quote");
		 * eNetMyEstesManagementToolPage.clickOnRemove("A70", "VTL Rate Quote");
		 * eNetMyEstesManagementToolPage.clickOnRemove("A55",
		 * "Time Critical Rate Quote");
		 */

		eNetHomePage.clickOnApplicationsTab();
		testUtil.setHardWait(1000);
		eNetApplicationsPage.clickOnMyEstesManagementTool();
		testUtil.setHardWait(2000);
		eNetMyEstesManagementToolPage.enterUserName("TESTSAT");

		eNetMyEstesManagementToolPage.enterAccountCode("5068692");
		eNetMyEstesManagementToolPage.clickOnSearchButton();
		eNetMyEstesManagementToolPage.selectTESTSAT();
		eNetMyEstesUserDetailPage.verifyTitle();
		eNetMyEstesUserDetailPage.clickOnEditMenuItemsLink();

		eNetMyEstesUserDetailPage.removeCode("C01 LTL Rate Request");
		eNetMyEstesUserDetailPage.removeCode("A60 LTL Rate Quote");
		eNetMyEstesUserDetailPage.removeCode("A70 VTL Rate Quote");
		eNetMyEstesUserDetailPage.removeCode("A55 Time Critical Rate Quote");

		driver.navigate().to(url);
		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnLoginFromDDown();
		myEstesLoginPage.enterUnameAndpwd(username6, password6);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		myEstesHomePage.verifyErrorMessage();
		myEstesHomePage.verifyWelcomeBack();
		myEstesHomePage.clickOnWelcomeBack();
		// myEstesHomePage.clickOnStartRequest();
		myEstesWelcomePage.clickOnStartRequestButton();
		myEstesHomePage.verifyErrorMessage();
		myEstesHomePage.verifyWelcomeBack();
	}

	/**
	 * Test passed o 6/29/22
	 * 
	 * Rate Quote - Time Critical - Verify Total Estimated Freight Charges and
	 * specific selected Accessorials Charges display as line items with $ amount
	 * for Guaranteed Exclusive Use service level.
	 */

	@Test(enabled = true, priority = 45)

	public void executeQZ_7977() throws InterruptedException {

		String chargesDesc1 = "Lift-Gate Service (Delivery)";
		String chargesDesc2 = "Total Estimated Freight Charges";
		String chargeDesc3 = "Fuel Surcharge";

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);

		// Create a new Rate Quote
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Consignee");
		rateQuotePage.enterTerms("Collect");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("77071");
		rateQuotePage.enterClass("100");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("7900");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("24");
		rateQuotePage.enterDesc("Creating New Rate Quote");

		rateQuotePage.clickOnViewAllAccessorials();

		rateQuotePage.selectOrDeselectAccessorials("Appointment Request");
		rateQuotePage.selectOrDeselectAccessorials("Inside Delivery Charge (Collect)");
		rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Delivery)");
		rateQuotePage.selectOrDeselectAccessorials("Unloading Services Requested By Consignee");

		/*
		 * rateQuotePage.clickOnAppointmentRequest();
		 * rateQuotePage.clickOnInsideDeliveryChargeCollect();
		 * rateQuotePage.clickOnLiftGateServiceDelivery();
		 * rateQuotePage.clickOnUnloadingServiceRequestedByConsignee()
		 */

		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.clickOnGuaranteedExclusiveUse();
		testUtil.setHardWait(2000);
		rateQuotePage.verifyGuranteedExclusiveBOLMessage();
		rateQuotePage.verifyChargeItemsTableDescription(chargesDesc1);
		rateQuotePage.verifyTotalEstimatedFreightCharges(chargesDesc2);
		rateQuotePage.verifyChargeItemsTableDescriptionNotPresent(chargeDesc3);

		// List<String> chargeItems = rateQuotePage.validateChargeItemsDetails();
		// Assert.assertFalse(chargeItems.contains(chargeDesc3));

		/*
		 * ArrayList<String> chargeItems = rateQuotePage.validateChargeItemsDetails();
		 * Assert.assertTrue(chargeItems.contains(chargesDesc1));
		 * Assert.assertTrue(chargeItems.contains(chargesDesc2));
		 * Assert.assertFalse(chargeItems.contains(chargeDesc3));
		 */

	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Guaranteed Volume and Truckload
	 * charges are calculated if class is blank, Volume and Truckload Basic charges
	 * are calculated, and no other exception exists.
	 */

	@Test(enabled = true, priority = 46)

	public void executeQZ_7156() throws InterruptedException {

		String vltBasic = "Volume and Truckload Basic";
		String guaranteedVTLEconomy = "Guaranteed Volume and Truckload Economy";
		String guaranteedVTLStandard = "Guaranteed Volume and Truckload Standard";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("qa@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("6200");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Creating New Rate Quote");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.validateGetQuoteButtonByServiceLevel(vltBasic);
		rateQuotePage.validateGetQuoteButtonByServiceLevel(guaranteedVTLEconomy);
		rateQuotePage.validateGetQuoteButtonByServiceLevel(guaranteedVTLStandard);

		rateQuotePage.clickOnGetQuoteButton();
		String classDetails = rateQuotePage.captureCommodiesDetails("Class");
		TestUtil.verifyText(classDetails, "0");
	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Contact Me link displayed when
	 * Destination terminal is located in Canada.
	 */

	@Test(enabled = true, priority = 47)

	public void executeQZ_3134() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName("QA Tester");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("Canada");
		rateQuotePage.enterDesZip("30007");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("8700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("32");

		// Click on Submit
		rateQuotePage.clikOnSubmitButton();

		// Verify Volume and Truckload Basic charge is blank and *Contact Me* displayed
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", false);

		// Verify Guaranteed Volume and Truckload Standard and Economy Charges are not
		// calculated and a *Contact Me* link is Not displayed
		rateQuotePage.verifyContactMe("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyContactMe("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
	}

	/**
	 * This test passed on 6/29/22
	 * 
	 * Rate Quote - Select Quote Type - Verify Time Critical/Expedited check box is
	 * disabled for unauthorized users.
	 * 
	 */

	@Test(enabled = true, priority = 48)

	public void executeQZ_7139() throws InterruptedException {

		// Launch eNet application
		driver.get(url2);

		// Login to eNet application
		eNetLoginPage.enterUserID("qaenet02");
		eNetLoginPage.enterUserPassword("qaenet02");
		eNetLoginPage.clickOnLoginButton();

		// Click Applications -> My Estes Management tool
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnMyEstesManagementToolLink();
		eNetMyEstesManagementToolPage.enterUserName("TESTSAT");

		// Search for account - 5068692
		eNetMyEstesManagementToolPage.enterAccountCode("5068692");
		eNetMyEstesManagementToolPage.clickOnSearchButton();

		// Select User name - TESTSAT

		/*
		 * if(!eNetMyEstesManagementToolPage.selectTestSAT()) {
		 * eNetMyEstesManagementToolPage.clickOnNextActivePage();
		 * eNetMyEstesManagementToolPage.selectTESTSAT(); }
		 */

		eNetMyEstesManagementToolPage.selectTESTSAT();
		// Verify user detail page displays
		eNetMyEstesUserDetailPage.verifyTitle();

		// Click on Edit Menu Items link
		eNetMyEstesUserDetailPage.clickOnEditMenuItemsLink();

		// Remove ‘A55 Time Critical Rate Quote’ from the list
		eNetMyEstesUserDetailPage.removeCode("A55 Time Critical Rate Quote");

		// Verify these codes are added - ‘C01 LTL Rate Request’, ‘A60 LTL Rate Quote’,
		// ‘A70 VTL Rate Quote'
		eNetMyEstesUserDetailPage.addCode("C01", "LTL Rate Request");
		eNetMyEstesUserDetailPage.addCode("A60", "LTL Rate Quote");
		eNetMyEstesUserDetailPage.addCode("A70", "VTL Rate Quote");

		// Launch My Estes application
		testUtil.openNewTab(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Login
		myEstesLoginPage.enterUserName(username6);
		myEstesLoginPage.enterPassword(password6);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Verify TimeCritical/Expedited checkbox is disabled
		rateQuotePage.verifyQuoteTypeIsDisable("Time Critical");

		// Verify restriction message is displayed as 'One or more quote types are
		// restricted for this account'
		rateQuotePage.verifyRestrictedMessage();

		// Select Role as Shipper
		rateQuotePage.enterMyRole("Shipper");

		// Select Terms as Prepaid
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("30307");

		rateQuotePage.enterDesZip("60002");

		// Enter Commodities info
		rateQuotePage.enterClass("50");

		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterDesc("This is test case validates");
		rateQuotePage.clikOnSubmitButton();

		// Click on any one of the Get Quote button

		testUtil.setHardWait(1000);

		// Capture Rate Quote#
		rateQuotePage.recordRateQouteNumber();
		// Click on Revise Quote button
		rateQuotePage.clickOnReviseQouteButton();

		// Verify TimeCritical/Expedited checkbox is disabled
		rateQuotePage.verifyQuoteTypeIsDisable("Time Critical");

		// Verify restriction message is displayed as 'One or more quote types are
		// restricted for this account'
		rateQuotePage.verifyRestrictedMessage();
		// Changed "verifyRestrictionMsg" to "verifyRestrictedMessge" To be Verified

	}

	/*
	 * Rate Quotes - Requester Information - Verify selecting the 'Use My Estes
	 * Account Info' checkbox pulls in all available user info and populates the
	 * correct fields.
	 */
	@Test(enabled = true, priority = 49)

	public void executeQZ_7735() throws InterruptedException {

		String contactName = "Troy Washington";// this changed from Troy Washington
		String phoneNumber = "(636) 537-6800";
		String emailAddress = "QATEST@ESTES-EXPRESS.COM";
		// String emailAddress = "DIGITALTEAM@ESTES-EXPRESS.COM";

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Create Rate Quote by 'My Estes Account' information
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();

		rateQuotePage.selectOrDeselectLessThanTruckload();

		// validate MyEstes Account Info Check box populates correct information
		rateQuotePage.clickOnMyEstesAccountInfo();
		rateQuotePage.verifyContactName(contactName);
		rateQuotePage.verifyPhoneNumber(phoneNumber);
		testUtil.setHardWait(1000);
		rateQuotePage.verifyEmailAddress(emailAddress);
	}

	/**
	 * 
	 * This test passed on 6/23/22
	 * 
	 * Rate Quote - Select Quote Type - Verify Less than truckload (incl.
	 * Guaranteed) and Time Critical/Expedited check boxes are disabled for
	 * unauthorized users.
	 */

	@Test(enabled = true, priority = 50)

	public void executeQZ_7142() throws InterruptedException {

		String accountCode = "5068692";
		String ltlRateRequest = "C01 - LTL Rate Request";
		String ltlRateQuote = "A60 - LTL Rate Quote";
		String timeCriticalRate = "A55 - Time Critical Rate Quote";
		String vtlRateQuote = "A70 - VTL Rate Quote";

		// Login to eNet
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnMyEstesManagementToolLink();
		eNetMyEstesManagementToolPage.enterUserName("TESTSAT");
		eNetMyEstesManagementToolPage.enterAccountCode(accountCode);
		eNetMyEstesManagementToolPage.clickOnSearchButton();

		/*
		 * if(!eNetMyEstesManagementToolPage.selectTestSAT()) {
		 * eNetMyEstesManagementToolPage.clickOnNextActivePage();
		 * eNetMyEstesManagementToolPage.selectTESTSAT(); }
		 */
		eNetMyEstesManagementToolPage.selectTESTSAT();
		eNetMyEstesUserDetailPage.clickOnEditMenuItemsLink();
		eNetMyEstesUserDetailPage.deleteMenuItem(ltlRateRequest);
		eNetMyEstesUserDetailPage.deleteMenuItem(ltlRateQuote);
		eNetMyEstesUserDetailPage.deleteMenuItem(timeCriticalRate);
		eNetMyEstesUserDetailPage.addMenuItems(vtlRateQuote);

		// Navigate to MyEstes Rate Quote
		testUtil.openNewTab(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username6);
		myEstesLoginPage.enterPassword(password6);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);
		rateQuotePage.verifyQuoteTypeIsDisable("TC");
		rateQuotePage.verifyQuoteTypeIsDisable("LTL");
		rateQuotePage.verifyQuoteTypeCheckboxIsSelected("VTL");
		rateQuotePage.verifyRestrictedMessage();

		// Create a new Quote
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("60002");
		rateQuotePage.enterDesZip("23230");
		rateQuotePage.enterPieces("8");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("12700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("56");
		rateQuotePage.enterDesc("LTL&TC checkbox disable for Unauthorized user");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.clickOnGetQuoteButton();

		// Validate Quote Type check box and Restricted message
		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.verifyQuoteTypeIsDisable("TC");
		rateQuotePage.verifyQuoteTypeIsDisable("LTL");
		rateQuotePage.verifyQuoteTypeCheckboxIsSelected("VTL");
		rateQuotePage.verifyRestrictedMessage();
	}

	/**
	 * This test passed on 6/29//22
	 * 
	 * Rate Quote - Select Quote Type - Verify Volume and Truckload (incl.
	 * Guaranteed) check box is disabled for unauthorized users.
	 */

	@Test(enabled = true, priority = 51) // This script was updated and reviewed on 12/31/2019

	public void executeQZ_7141() throws InterruptedException {

		String accountCode = "5068692";
		String vtlRateQuote = "A70 - VTL Rate Quote";
		String ltlRateRequest = "C01 - LTL Rate Request";
		String ltlRateQuote = "A60 - LTL Rate Quote";
		String timeCriticalRate = "A55 - Time Critical Rate Quote";

		// Login to eNet
		driver.get(url2);
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnMyEstesManagementToolLink();
		eNetMyEstesManagementToolPage.enterUserName("TESTSAT");
		testUtil.setHardWait(2000);
		eNetMyEstesManagementToolPage.enterAccountCode(accountCode);
		eNetMyEstesManagementToolPage.clickOnSearchButton();

		/*
		 * if(!eNetMyEstesManagementToolPage.selectTestSAT()) {
		 * eNetMyEstesManagementToolPage.clickOnNextActivePage();
		 * eNetMyEstesManagementToolPage.selectTESTSAT(); }
		 */

		eNetMyEstesManagementToolPage.selectTESTSAT();
		eNetMyEstesUserDetailPage.clickOnEditMenuItemsLink();
		eNetMyEstesUserDetailPage.deleteMenuItem(vtlRateQuote);
		eNetMyEstesUserDetailPage.addMenuItems(ltlRateRequest);
		eNetMyEstesUserDetailPage.addMenuItems(ltlRateQuote);
		eNetMyEstesUserDetailPage.addMenuItems(timeCriticalRate);

		// Navigate to MyEstes Rate Quote
		testUtil.openNewTab(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username6);
		myEstesLoginPage.enterPassword(password6);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.verifyRestrictedMessage();

		// Create a new Quote
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("23230");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("VTL checkbox disable for Unauthorized user");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.clickOnGetQuoteButton();

		// Validate VTL check box and Restricted message
		rateQuotePage.verifyQuoteTypeIsDisable("VTL");
		rateQuotePage.clickOnReviseQouteButton();
	}

	/*
	 * Rate Quote - Time Critical - Verify user can reserve a Guaranteed LTL
	 * Standard Transit: 5PM service level rate quote.
	 * 
	 */

	@Test(enabled = true, priority = 52)

	public void executeQZ_3093() throws InterruptedException {

		// Login to My Estes application as group user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Rate Quote page is displayed
		rateQuotePage.verifyCreateRateQoutePage();

		// Select Time Critical/Expedited quote type
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Enter Requester information
		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterAccountNumber("2485132");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter today's date as pickup date
		rateQuotePage.selectTodayDate();

		// Enter Routing information
		rateQuotePage.enterOriginZip("90001");
		rateQuotePage.enterDesZip("23001");

		// Enter commodities information
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("1500");
		rateQuotePage.enterLength("36");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("Test");

		// Click Submit Request
		rateQuotePage.clickOnSubmitButton();

		// Get Quote # for Guaranteed LTL Standard Transit: 5PM service level
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");

		// Record Quote# // String QuoteNo =
		rateQuotePage.recordQuoteNumber("Gauranteed 5 PM");

		// Select *I accept the Terms and Conditions* check box
		rateQuotePage.clickOnTermsAndConditionsFor("Guaranteed LTL Standard Transit: 5PM");

		/*
		 * // Click on Reserve Shipment rateQuotePage.
		 * clickOnResreveShipmentFor("Guaranteed LTL Standard Transit: 5PM");
		 * 
		 * // Verify Reserve Shipment button changed to Request Received
		 * rateQuotePage.verifyRequestReceivedMessage();
		 * 
		 * // Verify Thank You message displays under Request Received button
		 * rateQuotePage.verifyRequestReceivedDisclaimerMsg();
		 */
	}

	/*
	 * Rate Quote - Time Critical - Verify Total Estimated Freight Charges, selected
	 * Accessorials, Service Level Adjustment, and Fuel Surcharge Charges display as
	 * line items with $ amount for Guaranteed LTL Standard Transit.
	 * 
	 */

	@Test(enabled = true, priority = 53)

	public void executeQZ_3097() throws InterruptedException {
		logger.info("***GUARANTEED SERVICE LEVEL-GET QUOTE BUTTON DOES NOT DISPLY:12/19/2019***");
		String accessorial1 = "Appointment Request";
		String accessorial2 = "Inside Delivery Charge (Collect)";
		String accessorial3 = "Lift-Gate Service (Delivery)";
		String accessorial4 = "Unloading Services Requested By Consignee";
		String serviceLvlAdj = "Service Level Adjustment";
		String fuelSurcharge = "Fuel Surcharge";
		String totalEstimate = "Total Estimated Freight Charges";

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Create a new Quote
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Consignee");
		rateQuotePage.enterTerms("Collect");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("77071");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("BOX");
		rateQuotePage.enterTotalWeight("900");
		rateQuotePage.enterLength("24");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("Verifying Total Estimated Freight Charges");
		rateQuotePage.clickOnViewAllAccessorials();

		/*
		 * rateQuotePage.clickOnAppointmentRequest();
		 * rateQuotePage.clickOnInsideDeliveryChargeCollect();
		 * rateQuotePage.clickOnLiftGateServiceDelivery();
		 * rateQuotePage.clickOnUnloadingServiceRequestedByConsignee();
		 * rateQuotePage.selectOrDeselectUnloadingServicesRequestedByCons();
		 */

		rateQuotePage.selectOrDeselectAccessorials("Appointment Request");
		rateQuotePage.selectOrDeselectAccessorials("Inside Delivery Charge (Collect)");
		rateQuotePage.selectOrDeselectAccessorials(" Lift-Gate Service (Delivery) ");
		rateQuotePage.selectOrDeselectAccessorials("Unloading Services Requested By Consignee");

		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");

		// Validate Charge Items & Disclaimer Content
		rateQuotePage.validateChargeItemsTableDescription(totalEstimate);
		rateQuotePage.validateChargeItemsTableDescription(accessorial1);
		testUtil.setHardWait(4000);
		String appointmentCharge = rateQuotePage.getChargeItemsTableCharges(accessorial1);
		Assert.assertTrue(appointmentCharge.contains("$0.00"));

		rateQuotePage.validateChargeItemsTableDescription(accessorial2);
		String insideDeliveryCharge = rateQuotePage.getChargeItemsTableCharges(accessorial2);
		Assert.assertTrue(insideDeliveryCharge.contains("$"));

		rateQuotePage.validateChargeItemsTableDescription(accessorial3);
		String liftGateCharge = rateQuotePage.getChargeItemsTableCharges(accessorial3);
		Assert.assertTrue(liftGateCharge.contains("$"));

		rateQuotePage.validateChargeItemsTableDescription(accessorial4);
		String unloadServiceCharge = rateQuotePage.getChargeItemsTableCharges(accessorial4);
		Assert.assertTrue(unloadServiceCharge.contains("$"));

		rateQuotePage.validateChargeItemsTableDescription(fuelSurcharge);
		String fuelCharge = rateQuotePage.getChargeItemsTableCharges(fuelSurcharge);
		Assert.assertTrue(fuelCharge.contains("$"));

		rateQuotePage.validateChargeItemsTableDescription(serviceLvlAdj);
		String serviceLevelCharge = rateQuotePage.getChargeItemsTableCharges(serviceLvlAdj);
		Assert.assertTrue(serviceLevelCharge.contains("$"));
		rateQuotePage.verifyDisclaimerContent();
	}

	/*
	 * Rate Quote - Time Critical - Verify Puerto Rico locations receive 'Contact
	 * Me' message for all Guaranteed LTL Standard Transit and Guaranteed Exclusive
	 * Use service levels.
	 * 
	 */

	@Test(enabled = true, priority = 54)

	public void executeQZ_3090() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Create a new Quote
		rateQuotePage.enterContactName();
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("00603");
		rateQuotePage.enterDesZip("23219");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("5");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("28");
		rateQuotePage.enterWidth("28");
		rateQuotePage.enterHeight("28");
		rateQuotePage.enterDesc("Testing Puerto Rico Location");
		rateQuotePage.clickOnSubmitButton();

		// Validating Contact Me for Guaranteed service level

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);
		rateQuotePage.verifyContactMe("Guaranteed Exclusive Use", true);

	}

	// Updated script per new changes hence turned on the test.
	/*
	 * Rate Quote - Verify a Disclaimer is displayed, Overlength Charge-12.00' -
	 * 19.99' accessorial is automatically added, and Guaranteed LTL Standard
	 * Transit rates are not calculated when a single piece length is between 12.00'
	 * and 19.99'.
	 * 
	 */

	@Test(enabled = true, priority = 56)

	public void executeQZ_3075() throws InterruptedException {

		// Login to My Estes application as group user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Rate Quote page is displayed
		rateQuotePage.verifyCreateRateQoutePage();

		// Select Time Critical/Expedited quote type
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Enter Requester information
		rateQuotePage.enterContactName("QZ-3075");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		rateQuotePage.selectTodayDate();
		// Enter Routing information
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30007");

		// Enter commodities information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceTypeSkid();
		rateQuotePage.enterTotalWeight("700");
		rateQuotePage.enterLength("144");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		// Click Submit Request
		rateQuotePage.clickOnSubmitButton();

		// verify disclaimer message and contact me
		rateQuotePage.verifyDisclaimer();
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

		// click on getquote
		rateQuotePage.clickOnGetQuoteButton();
		testUtil.setHardWait(2000);
		// recordquotenumber
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		testUtil.setHardWait(1000);
		// verify overlength freight
		rateQuotePage.verifyOverlengthCharge();

		rateQuotePage.clickOnReviseQouteButton("LTL Standard Transit");

		rateQuotePage.enterLength("239");
		testUtil.setHardWait(2000);
		rateQuotePage.selectOrDeselectOverLengthFreight();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyDisclaimer();
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

		// click on getquote
		rateQuotePage.clickOnGetQuoteButton();
		testUtil.setHardWait(2000);
		// recordquotenumber
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// verify overlength freight
		rateQuotePage.verifyOverlengthCharge();
		rateQuotePage.clickOnReviseQouteButtonBlowQouteOption();
		String length = "96";
		rateQuotePage.enterLength(length);

		rateQuotePage.verifyOverlengthFreightIsSelected();

		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyTransitRates("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.verifyTransitRates("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.verifyTransitRates("Guaranteed LTL Standard Transit: 5PM");

		// click on getquote
		rateQuotePage.clickOnGetQuoteButton();
		testUtil.setHardWait(2000);
		// recordquotenumber
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		rateQuotePage.verifyOverlengthFreightIsDisplayed(length);

	}

	/*
	 * Rate Quote - Verify if 'Pickup Date' is on a Sunday or a Holiday, Charges are
	 * not calculated for Volume and Truckload Guaranteed Standard and Economy.
	 * 
	 */

	@Test(enabled = true, priority = 57)

	public void executeQZ_3157() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		// Rate Quote page is displayed
		rateQuotePage.verifyCreateRateQoutePage();

		// Select Volume and Truckload Guaranteed type
		rateQuotePage.selectOrDeselectValumeAndTruckload();

		// Enter Requester information
		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("5042043311");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Enter pickup date as coming sunday
		rateQuotePage.selectNextSunday();

		// Enter Routing information
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("90007");

		// Enter commodities information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("5900");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Test");

		// Click on Sumbit
		rateQuotePage.clickOnSubmitButton();

		// Verify Volume and Truckload Basic Charge is calculated
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

		// Verify Volume and Truckload Guaranteed Standard Charge is not calculated,
		// blank instead
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Standard", false);

		// Verify Volume and Truckload Guaranteed Economy Charge is not calculated,
		// blank instead
		rateQuotePage.verifyBasicCharge("Guaranteed Volume and Truckload Economy", false);
	}

	/*
	 * MyEstes VTL Rates Use global OR if no other target OR exist in the Target
	 * Operating Revenue Table.
	 * 
	 */

	@Test(enabled = true, priority = 58)

	public void executeQZ_5090() throws InterruptedException {

		// Login to enet app
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Click on Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Click on VTL Tab
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();

		// Navigate to OR tab
		eNetVTLTableMaintenancePage.clickOnORTab();

		// From the *Target Operating Revenue* page, enter the following value: OR% - 77
		eNetORPage.removeAllORPercent();
		eNetORPage.addORPercent("77");

		// Launch my estes application
		testUtil.openNewTab(url);

		// Login to my estes as admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Rate Quote page is displayed
		rateQuotePage.verifyCreateRateQoutePage();

		// Select Volume and Truckload Guaranteed type
		rateQuotePage.selectOrDeselectValumeAndTruckload();

		// Enter Requester information
		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("8042043311");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Enter today's date as pickup date
		rateQuotePage.selectTodayDate();

		// Enter Routing information
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("97005");

		// Enter commodities information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("8");
		rateQuotePage.enterPieceType("CARTON");
		rateQuotePage.enterTotalWeight("8800");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Test");

		// Click on Sumbit
		rateQuotePage.clickOnSubmitButton();

		// Get Quote # for Volume and Truckload Basic service level
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Record Quote#
		String QuoteNo1 = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Click on Applications tab
		testUtil.switchToParentWindow();
		eNetHomePage.clickOnApplicationsTab();

		// Click on Quote Audit Service under customer service
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Search the recorded quote#
		// String QNo = QuoteNo.split(":")[1].trim();
		// System.out.println(QNo);
		// eNetQuoteAuditTrailPage.enterQuoteNo(QNo);
		eNetQuoteAuditTrailPage.enterQuoteNo(QuoteNo1);
		eNetQuoteAuditTrailPage.clickOnSubmit();

		// Verify OR% reflected in Change description
		eNetQuoteAuditTrailPage.verifyORPercent("77");
		testUtil.switchToParentWindow();

		// Click on Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Click on VTL Tab
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();

		// Navigate to OR tab
		eNetVTLTableMaintenancePage.clickOnORTab();

		// From the *Target Operating Revenue* page, remove the following value: OR% -
		// 77
		eNetORPage.removeAllORPercent();

		// testUtil.switchToChildWindow();
		testUtil.switchToWindow(1);
		testUtil.setHardWait(1000);

		// Click Revise Quote Button
		rateQuotePage.clickOnReviseQouteButton();
		testUtil.setHardWait(1000);

		// Click Submit button
		rateQuotePage.clickOnSubmitButton();

		// Get Quote # for Volume and Truckload Basic service level
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Record Quote#
		String QuoteNo = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
		System.out.println("Revised Quote number : " + QuoteNo);

		// Search the recorded quote number in Quote Trail Page in eNet
		// Click on Applications tab
		testUtil.switchToParentWindow();
		eNetHomePage.clickOnApplicationsTab();

		// Click on Quote Audit Service under customer service
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Search the recorded quote#
		// String rQNo = QuoteNo1.split(":")[1].trim();
		// System.out.println(rQNo);
		eNetQuoteAuditTrailPage.enterQuoteNo(QuoteNo);

		eNetQuoteAuditTrailPage.clickOnSubmit();

		// Verify OR% reflected in Change description as 100
		eNetQuoteAuditTrailPage.verifyORPercent("100");
	}

	/**
	 * Rate Quote - Verify a Disclaimer is displayed, Overlength Charge - 28' or
	 * Greater accessorial is automatically added, and Guaranteed LTL Standard
	 * Transit rates are not calculated when a single piece length is between 28 and
	 * 53 feet.
	 */

	/* NEEDS TO BE REMOVED FROM AUTOMATION SUITE FOR NOW-KATHY! */

	@Test(enabled = true, priority = 59)

	public void executeQZ_3077() throws InterruptedException {

		String expected = "Pieces, weights or dimensions may require special handling to ensure the guaranteed service level. Please contact the Estes Solution Center at 1-800-645-3952.";
		String accessorial = "OVERLENGTH CHARGE - 40' OR GREATER";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Login
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Check Volume and Truckload checkbox
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("QZ-3077");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30007");

		// Enter Commodity info
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("700");
		rateQuotePage.enterLength("480");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyDisclaimerIsDisplayed(expected);
		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 10AM ", true);
		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 12PM  ", true);
		rateQuotePage.verifyContactMe(" Gu Guaranteed LTL Standard Transit: 5PM ", true);

		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");

		// rateQuotePage.verifyChargeItems("OVERLENGTH CHARGE - 28' OR GREATER");
		rateQuotePage.verifyChargeItems(accessorial);
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.enterLength("636");
		// rateQuotePage.selectOrDelselectOverlengthCheckBox28feetOrGreater();
		rateQuotePage.selectOrDeselectAccessorials("Overlength Freight (40 feet or greater)");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyDisclaimerIsDisplayed(expected);
		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 10AM ", true);
		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 12PM  ", true);
		rateQuotePage.verifyContactMe(" Gu Guaranteed LTL Standard Transit: 5PM ", true);

		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		// rateQuotePage.verifyChargeItems("OVERLENGTH CHARGE - 28' OR GREATER");
		rateQuotePage.verifyChargeItems(accessorial);
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.enterLength("96");

		rateQuotePage.validateAccessorials("Overlength Freight (40 feet or greater)");

		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 10AM ", false);
		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 12PM  ", false);
		rateQuotePage.verifyContactMe(" Gu Guaranteed LTL Standard Transit: 5PM ", false);

		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");

		rateQuotePage.validateChargeItemsDetails();

	}

	/*
	 * Rate Quote - Time Critical - Verify length calculated based on the dimensions
	 * entered by the user override selected overlength accessorial.
	 */

	/* NEEDS TO BE REMOVED FROM AUTOMATION SUITE FOR NOW-KATHY! */

	@Test(enabled = false, priority = 60)

	public void executeQZ_3086() throws InterruptedException {

		String expected = "Pieces, weights or dimensions may require special handling to ensure the guaranteed service level. Please contact the Estes Solution Center at 1-800-645-3952.";
		// String expected = "Please contact the Estes Solution Center at
		// 1-800-645-3952, or your local servicing terminal to confirm driver
		// availability for all pickups required after 3 p.m. local time.";

		String accessorial = "Overlength Freight (20.00' to 27.99')";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Check Volume and Truckload checkbox
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("kaiser");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.enterDesZip("30307");

		// Enter Commodity info
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("400");
		rateQuotePage.enterLength("244");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("24");

		// select accoserials
		rateQuotePage.selectOverlengthCheckBox();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyDisclaimerIsDisplayed(expected);

		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 10AM ", true);
		rateQuotePage.verifyContactMe(" Guaranteed LTL Standard Transit: 12PM  ", true);
		rateQuotePage.verifyContactMe(" Gu Guaranteed LTL Standard Transit: 5PM ", true);

		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");

		rateQuotePage.verifyChargeItems("OVERLENGTH CHARGE-20.00' - 27.9'");
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.validateAccessorials(accessorial);

	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Contact Me link displays if
	 * Destination terminal is an Agent point.
	 */

	@Test(enabled = true, priority = 61)

	public void executeQZ_3139() throws InterruptedException {

		Faker faker = new Faker();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Login
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Check Volume and Truckload checkbox
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName(faker.name().fullName());
		rateQuotePage.enterYourEmail(faker.internet().emailAddress());
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.enterDesZip("59522");
		rateQuotePage.enterPieces("5");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("Creating New Rate Quote");
		rateQuotePage.clickOnSubmitButton();

		// Verify Volume and Truckload Basic charge is blank and *Contact Me* displayed
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", false);

		// Verify Guaranteed Volume and Truckload Standard and Economy Charges are not
		// calculated and a *Contact Me* link is Not displayed
		rateQuotePage.verifyContactMe("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyContactMe("Guaranteed Volume and Truckload Standard", false);

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("02672");
		rateQuotePage.clickOnSubmitButton();

		// Verify Volume and Truckload Basic charge is blank and *Contact Me* displayed
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", false);

		// Verify Guaranteed Volume and Truckload Standard and Economy Charges are not
		// calculated and a *Contact Me* link is Not displayed
		rateQuotePage.verifyContactMe("Guaranteed Volume and Truckload Economy", false);
		rateQuotePage.verifyContactMe("Guaranteed Volume and Truckload Standard", false);

	}

	/*
	 * Rate Quote - Volume and Truckload - Verify Total Estimated Freight Charges
	 * displays as a line item with $ amount, Accessorial Charges display 'Included'
	 * instead of $ amount, and Fuel Surcharge is not displayed.
	 */

	@Test(enabled = true, priority = 62)

	public void executeQZ_3187() throws InterruptedException {

		// Login to estes as admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Rate Quote page is displayed
		rateQuotePage.verifyCreateRateQoutePage();

		// Select Volume and Truckload Guaranteed type
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Enter Requester information
		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("5042043311");
		rateQuotePage.enterMyRole("Consignee");

		// Enter today's date as pickup date
		rateQuotePage.selectTodayDate();

		// Enter Routing information
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("77071");

		// Enter commodity information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("9");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("9700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Test");

		// From the *Accessorials* section, check the following:- Appointment Request, -
		// Unloading Services Requested By Consignee, - Inside Delivery
		// rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectAccessorialsList("Appointment Request");
		rateQuotePage.selectOrDeselectAccessorialsList("Inside Delivery");
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectAccessorialsList("Unloading Services Requested By Consignee");

		// Click on Sumbit
		rateQuotePage.clickOnSubmitButton();

		// Get Quote # for Volume and Truckload Basic service level
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Verify the selected accessorial is displays with charge as 'Inlcuded'
		rateQuotePage.verifyChargeItemsTable("Appointment Request", "Included");
		rateQuotePage.verifyChargeItemsTable("Inside Delivery", "Included");
		rateQuotePage.verifyChargeItemsTable("Unloading Services Requested By Consignee", "Included");

		// Verify Total Estimated Freight Charge is displayed as a line item
		String estimatedCharge = rateQuotePage.getChargeItemsTableCharges("Total Estimated Freight Charges");
		rateQuotePage.verifyChargeItemsTable("Total Estimated Freight Charges", estimatedCharge);

		// Verify Fuel surcharge is displayed as a line item
		Assert.assertFalse(rateQuotePage.verifyFuelSurchargeIsNotDisplayed());

	}

	/*
	 * MyEstes Rates Use the outbound target OR if it is effective on the quote
	 * request date.
	 */

	@Test(enabled = true, priority = 63)

	public void executeQZ_5086() throws InterruptedException {

		String ORvalue1 = "95";
		String ORvalue2 = "85";

		// Login to eNet
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnORTab();
		eNetORPage.enterORNumber(ORvalue1);
		eNetORPage.clickOnAddButton();
		eNetORPage.enterORNumber(ORvalue2);
		eNetORPage.selectOriginTerminal("001");
		eNetORPage.selectDestinationTerminal("041");
		eNetORPage.clickOnAddButton();

		driver.get(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		// Create a new Quote
		rateQuotePage.enterContactName("Jim Jill");
		rateQuotePage.enterYourEmail("QA@estes-express.com");
		rateQuotePage.enterPhoneNo("8415546046");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("9700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("Testing");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		String quoteNumber = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		driver.get(url2);

		eNetQuoteAuditTrailPage.verifyIsDisplayed("OR determined to be 85.00%");
		eNetHomePage.clickOnApplicationWithoutFrame();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnORTab();
		eNetORPage.clickRemove(ORvalue1);
		eNetORPage.verifyOREntries(ORvalue1);
		eNetORPage.clickRemove(ORvalue2);
		eNetORPage.verifyOREntries(ORvalue2);

	}

	/*
	 * Rate Quote - LTL - Verify a Reserve Shipment button is displayed for
	 * Guaranteed option quotes.
	 */

	@Test(enabled = true, priority = 64)

	public void executeQZ_825() throws InterruptedException {

		// Login to My Estes application as group user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Enter Requester information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");

		// Enter commodities information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("975");

		// Click Submit Request
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Get Quote # for Guaranteed LTL Standard Transit: 5PM service level

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 5PM");
	}

	/*
	 * Rate Quote - Time Critical - Verify Contact Me displays for Guaranteed
	 * Exclusive use service level when Origin is Canada/M1B6H8 and Guaranteed LTL
	 * Standard Transit 10:AM, 12:PM, and 5:PM charges are calculated.
	 * 
	 */

	@Test(enabled = true, priority = 65)
	public void executeQZ_3088() throws InterruptedException {

		String expectedAlert = "Note: Transit times may be impacted by events at the border beyond Estes control.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Create a new quote with Canada location
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.selectOriginCountry("Canada");
//		rateQuotePage.enterOriginZip("M1B6H8");
//		rateQuotePage.enterDesZip("23219");
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30007");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("10");
		rateQuotePage.enterPieceType("BOX");
		rateQuotePage.enterTotalWeight("2700");
		rateQuotePage.enterLength("36");
		rateQuotePage.enterWidth("12");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("Canada location");
		rateQuotePage.clickOnSubmitButton();

		// Validate LTL Guaranteed charges are displayed
		testUtil.setHardWait(4000);
		String ltlTenAMCharges = rateQuotePage.getChargesByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		Assert.assertTrue(ltlTenAMCharges.contains("$"));
		String ltlTwelvePMCharges = rateQuotePage.getChargesByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		Assert.assertTrue(ltlTwelvePMCharges.contains("$"));
		String ltlFivePMCharges = rateQuotePage.getChargesByServiceLevel("Guaranteed LTL Standard Transit: 5PM");
		Assert.assertTrue(ltlFivePMCharges.contains("$"));

		testUtil.setHardWait(40000);
		// Validate 'Contact Me' is displayed for LTL Guaranteed Exclusive Use
		rateQuotePage.verifyContactMe("Guaranteed Exclusive Use", true); // changed method from validate contact me to
																			// verify contact me.
		// rateQuotePage.clickOnGetQouteButton();
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
		String actualAlert = rateQuotePage.captureAlertMessage();
		Assert.assertEquals(actualAlert, expectedAlert);
	}

	/*
	 * Rate Quote - Verify a Disclaimer is displayed, Overlength Charge-20.00' -
	 * 27.99' accessorial is automatically added, and Guaranteed LTL Standard
	 * Transit rates are not calculated when a single piece length is between 20.00'
	 * and 27.99'.
	 * 
	 */

	/* NEEDS TO BE REMOVED FROM AUTOMATION SUITE FOR NOW-KATHY! */

	@Test(enabled = false, priority = 67)

	public void executeQZ_3076() throws InterruptedException {

		// Login as admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Rate Quote page is displayed
		rateQuotePage.verifyCreateRateQoutePage();

		// Select Time Critical
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Enter Requester info
		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("5042043311");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Enter today's date as pickup date
		rateQuotePage.selectTodayDate();

		// Enter Routing information
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30007");

		// Enter commodity information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("700");
		rateQuotePage.enterLength("240");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("Test");

		// Click on Submit
		rateQuotePage.clickOnSubmitButton();

		// Verify disclaimer message displayed for overlength
		rateQuotePage.verifyDisclaimerMsgForOverLengthCharge();

		// Verify all Guaranteed LTL Standard Transit rates display Contact Me
		rateQuotePage.verifyContactMe("Gauranteed 10 AM", true);
		rateQuotePage.verifyContactMe("Gauranteed 12 PM", true);
		rateQuotePage.verifyContactMe("Gauranteed 5 PM", true);

		// *Get Quote #* for LTL Standard Transit service level
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Validate *OVERLENGTH CHARGE-20.00' - 27.99'* accessorial is displayed as a
		// line item
		String charge = rateQuotePage.getChargeItemsTableCharges("OVERLENGTH CHARGE-20.00' - 27.9'");
		rateQuotePage.verifyChargeItemsTable("OVERLENGTH CHARGE-20.00' - 27.9'", charge);

		// Click on Revise Quote
		rateQuotePage.clickOnReviseQouteButton("LTL Standard Transit");// changed revise quote method

		// Update lenghth as 335
		rateQuotePage.enterLength("335");

		// Deselect *Overlength Freight (20.00' to 27.99')*
		// rateQuotePage.selectOrDeselectAccessorials(" Overlength Freight (20.00' to
		// 27.99') ");
		rateQuotePage.selectOrDeselectOverLengthAccessorials();

		// Click on submit
		rateQuotePage.clickOnSubmitButton();

		// Verify disclaimer message displayed for overlength
		rateQuotePage.verifyDisclaimerMsgForOverLengthCharge();

		// Verify all Guaranteed LTL Standard Transit rates display Contact Me
		rateQuotePage.verifyContactMe("Gauranteed 10 AM", true);
		rateQuotePage.verifyContactMe("Gauranteed 12 PM", true);
		rateQuotePage.verifyContactMe("Gauranteed 5 PM", true);

		// *Get Quote #* for LTL Standard Transit service level
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Validate *OVERLENGTH CHARGE-20.00' - 27.99'* accessorial is displayed as a
		// line item
		String charge1 = rateQuotePage.getChargeItemsTableCharges("OVERLENGTH CHARGE-20.00' - 27.9'");
		rateQuotePage.verifyChargeItemsTable("OVERLENGTH CHARGE-20.00' - 27.9'", charge1);

		// Click on Revise Quote
		rateQuotePage.clickOnReviseQouteButton("LTL Standard Transit");// changed revise quote method

		// Update length as 95
		rateQuotePage.enterLength("95");
		rateQuotePage.enterHeight("32");

		// Verify Over-length Freight (20.00' to 27.99')* is selected
		rateQuotePage.verifyOverLength20To27AccessorialIsChecked(true);
		testUtil.setHardWait(1000);

		// Click on Submit
		rateQuotePage.clickOnSubmitButton();

		// Verify all Guaranteed LTL Standard Transit rates are calculated
		rateQuotePage.verifyBasicCharge("Gauranteed 10 AM", true);
		rateQuotePage.verifyBasicCharge("Gauranteed 12 PM", true);
		rateQuotePage.verifyBasicCharge("Gauranteed 5 PM", true);

		// *Get Quote #* for LTL Standard Transit service level
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Validate *OVERLENGTH CHARGE-20.00' - 27.99'* accessorial is not displayed as
		// a line item
		Assert.assertFalse(rateQuotePage.verifyOverLengthChargeElementExistence());

	}

	/*
	 * MyEstes V/TL Rates Use floor minimum if the calculated rate is less than the
	 * V/TL floor minimum rate.
	 * 
	 */

	@Test(enabled = true, priority = 68)

	public void executeQZ_5993() throws InterruptedException {

		String accountNumber = "5068692";
		String vtlBasicMinimumAmt = "400";
		String vtlBasicReviseAmt = "350";

		// Login to eNet
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnFloorMinsLink();

		eNetFloorMinsDefinitionPage.verifyPageHeader();
		eNetFloorMinsDefinitionPage.deleteAccountNumberIfExist(accountNumber);
		eNetFloorMinsDefinitionPage.enterAccountNumber(accountNumber);
		eNetFloorMinsDefinitionPage.enterMinimumAmount(vtlBasicMinimumAmt);
		eNetFloorMinsDefinitionPage.clickOnAddButton();
		eNetFloorMinsDefinitionPage.verifySuccessMessage();

		testUtil.openNewTab(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("4154164117");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("23059");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("Verifying Minimum calculated rates");
		rateQuotePage.clickOnSubmitButton();

		String minimumCharges = rateQuotePage.getChargesByServiceLevel("Volume and Truckload Basic");
		Assert.assertTrue(minimumCharges.contains("$" + vtlBasicMinimumAmt + ".00"));

		testUtil.switchToParentWindow();
		eNetFloorMinsDefinitionPage.switchToFrameElement();
		eNetFloorMinsDefinitionPage.deleteAccountNumberIfExist(accountNumber);

		eNetFloorMinsDefinitionPage.enterAccountNumber(accountNumber);
		eNetFloorMinsDefinitionPage.enterMinimumAmount(vtlBasicReviseAmt);
		eNetFloorMinsDefinitionPage.clickOnAddButton();
		eNetFloorMinsDefinitionPage.verifySuccessMessage();

		testUtil.switchToWindow(1);
		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(3000);
		String reviseCharges = rateQuotePage.getChargesByServiceLevel("Volume and Truckload Basic");
		Assert.assertTrue(reviseCharges.contains("$" + vtlBasicReviseAmt + ".00"));
	}


	/*
	 * Test passed on 9/9/2022
	 */
	/*
	 * Rate Quote - Time Critical - Verify Guaranteed Exclusive Use minimum charges
	 * apply when applicable.
	 * 
	 */
	@Test(enabled = true, priority = 69)

	public void executeQZ_3079() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Create a new Quote
		rateQuotePage.enterContactName("QZ-3079");
		rateQuotePage.enterAccountNumber("4136995");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("23233");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("2000");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("24");
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		// Validating exclusive charge
//		testUtil.setHardWait(40000);
		String charge = rateQuotePage.getChargesByServiceLevel("Guaranteed Exclusive Use");
		Assert.assertEquals(charge, "$1,195.00");
	}

	/*
	 * Rate Quote - Less Than Truckload - Verify Guaranteed LTL 10AM, 12PM & 5PM
	 * display a 'Contact Me' link for invalid service in points file.
	 * 
	 */

	@Test(enabled = true, priority = 70)

	public void executeQZ_5092() throws InterruptedException {

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Create new quotes with invalid service points
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("22911");
		rateQuotePage.enterDesZip("41230");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);

		rateQuotePage.clickOnStartNewQuote();
		rateQuotePage.clickOnConfirmButton();
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("23219");
		rateQuotePage.enterDesZip("37304");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);

		rateQuotePage.clickOnStartNewQuote();
		rateQuotePage.clickOnConfirmButton();
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("88201");
		rateQuotePage.enterDesZip("83672");
		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);
	}

	/**
	 * The test fails because <Contact Me> link appears instead of <GET QUOTE>
	 * 
	 * Rate Quote - LTL - Verify Buttons and messages are displayed when Guaranteed
	 * options selected.
	 * 
	 */

	@Test(enabled = true, priority = 71)

	public void executeQZ_836() throws InterruptedException {

		// login to My Estes application as Smokelocal
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// enter Requester information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// enter Routing information
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("23059");

		// enter commodities information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("975");

		// click Submit Request
		rateQuotePage.clickOnSubmitButton();

		// get Quote # for Guaranteed LTL Standard Transit: 5PM service level

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.verifyMessageforLTL10AM();
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 10AM");

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.verifyMessageforLTL12PM();
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 12PM");

		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 5PM");
		rateQuotePage.verifyMessageforLTL5PM();
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 5PM");
	}

	/*
	 * MyEstes Rates VTL Weight to Linear Feet determination
	 */

	@Test(enabled = true, priority = 72)

	public void executeQZ_5995() throws InterruptedException {

		Faker faker = new Faker();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName(faker.name().fullName());
		rateQuotePage.enterYourEmail(faker.internet().emailAddress());
		rateQuotePage.enterPhoneNo(faker.phoneNumber().phoneNumber());
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("10800");
		rateQuotePage.enterLength("8");
		rateQuotePage.enterWidth("8");
		rateQuotePage.enterHeight("8");
		rateQuotePage.enterDesc("Creating New Rate Quote qz-5995");
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		String quoteNumber = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();
		eNetQuoteAuditTrailPage.enterQuoteNo(quoteNumber);
		eNetQuoteAuditTrailPage.clickOnSubmitButton();
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Chosen linear footage to rate = 12");
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Linear Feet Calculated as 12");

	}

	/**
	 * this test fails because its not finding the text, need to check
	 * 
	 * Rate Quote - LTL - Verify Accessorial Charges are displayed under Charge
	 * Items with associated costs in the rate quote details.
	 * 
	 */

	@Test(enabled = true, priority = 73)

	public void executeQZ_850() throws InterruptedException {

		// Login as group user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Enter Requester information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("30301");

		// Enter commodities information
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("6450");
		rateQuotePage.enterDesc("Test");

		// Select Accessorial
		rateQuotePage.selectOrDeselectAccessorials("Appointment Request");

		rateQuotePage.selectOrDeselectInsideDelivery();

		rateQuotePage.clickOnViewAllAccessorials();

		rateQuotePage.selectOrDeselectAccessorials("Notify Request");
		rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Delivery) ");
		rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Pickup) ");
		rateQuotePage.selectOrDeselectResidentialDelivery();

		rateQuotePage.selectOrDeselectAccessorials("Unloading Services Requested By Consignee");
		testUtil.setHardWait(1000);

		// Click submit
		rateQuotePage.clickOnSubmitButton();

		// Get Quote for LTL Standard transit
		rateEstimatePage.clickOnGetQuoteButtonByServiceLevel("LTL Standard Transit");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Validate the selected Accessorials are listed as *Charge Items* with their
		// associated *Charge*
		// NOTE: $0.00 is a valid charge amount as specific charges may be waived for a
		// customer
		String charge1 = rateQuotePage.getChargeItemsTableCharges("Appointment Request");
		rateQuotePage.verifyChargeItemsTable("Appointment Request", charge1);

		String charge2 = rateQuotePage.getChargeItemsTableCharges("Inside Delivery");
		rateQuotePage.verifyChargeItemsTable("Inside Delivery", charge2);

		String charge3 = rateQuotePage.getChargeItemsTableCharges("Notify Request");
		rateQuotePage.verifyChargeItemsTable("Notify Request", charge3);

		String charge4 = rateQuotePage.getChargeItemsTableCharges("Lift-Gate Service (Delivery)");
		rateQuotePage.verifyChargeItemsTable("Lift-Gate Service (Delivery)", charge4);

		String charge5 = rateQuotePage.getChargeItemsTableCharges("Lift-Gate Service (Pickup)");
		rateQuotePage.verifyChargeItemsTable("Lift-Gate Service (Pickup)", charge5);

		String charge6 = rateQuotePage.getChargeItemsTableCharges("Residential Delivery");
		rateQuotePage.verifyChargeItemsTable("Residential Delivery", charge6);

		String charge7 = rateQuotePage.getChargeItemsTableCharges("Unloading Services Requested By Consignee");
		rateQuotePage.verifyChargeItemsTable("Unloading Services Requested By Consignee", charge7);

	}
	/*
	 * Rate Quote - LTL - Verify the Fuel Surcharge link is functioning properly
	 * 
	 */

	@Test(enabled = true, priority = 74)

	public void executeQZ_831() throws InterruptedException {
		// Login as group user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Enter Account Number: 3878407
		rateQuotePage.enterAccountNumber("3878407");

		// Enter Requester information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30307");

		// Enter commodity information
		rateQuotePage.selectClass("50");
		rateQuotePage.enterTotalWeight("1500");

		// Click on submit
		rateQuotePage.clickOnSubmitButton();

		// Get Quote# from LTL Standard Transit
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Click on FuelSurcharge link from charge items section
		Assert.assertTrue(rateQuotePage.verifyFuelSurchargeIsNotDisplayed());
		rateQuotePage.clickOnFuelSurchargeLink();
		testUtil.setHardWait(500);
		// Validate user navigates to Fuel Surcharge application screen
		testUtil.switchToWindow(1);
		Assert.assertEquals(testUtil.getTitle(), "Fuel Surcharge | Estes Express Lines");

	}

	/*
	 * Rate Quote - LTL Verify 'Contact Me' email contains correct verbiage and
	 * disclaimers.
	 * 
	 */

	// EAMIL STEP WITH ROUNDCUBE CAN BE VARIFIED MANUALLY.IT'S NOT ADDED IN THE
	// SCRIPT

	// AS PER DEE, TESTS THAT VERIFY EMAIL IN THE ROUNDCUBE SHOULD BE TUNRED OFF
	// UNTIL FURTHER NOTICE

	@Test(enabled = false, priority = 75)

	public void executeQZ_823() throws InterruptedException {

		// Login as local user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Enter Requester information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");

		// Enter commodity information
		rateQuotePage.selectClass("50");
		rateQuotePage.enterTotalWeight("975");

		// Select any over-length accessorial
		rateQuotePage.selectOrDeselectOverLengthFreight();

		// Click on submit
		rateQuotePage.clickOnSubmitButton();

		// Click on one of the contact me link
		rateQuotePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");

		// Verify Contact Us dialog displayed
		rateQuotePage.verifyContactUsDialogExistence();

		// Enter data Contact Us dialog
		rateQuotePage.enterContactName("QA Tester");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("5546648842");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterLength("20");
		rateQuotePage.enterWidth("20");
		rateQuotePage.enterHeight("20");

		// Click on confirm button
		rateQuotePage.clickOnConfirmButton();
		logger.info("Verify email content manually!!!!!!");
	}

	/*
	 * Rate Quote - Time Critical - Verify Hawaii location receives 'Contact Me'
	 * message for Guaranteed Exclusive Use, Guaranteed LTL Standard 10AM and 12PM
	 * service levels.
	 * 
	 */

	@Test(enabled = true, priority = 76)

	public void executeQZ_3089() throws InterruptedException {

		// Login as group user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password3);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Select Time Critical
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		// De-select Less Than Truckload
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Enter Requester info
		rateQuotePage.enterContactName("Tester");
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("5042043311");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter today's date as pickup date
		rateQuotePage.selectTodayDate();

		// Enter Routing information
		rateQuotePage.enterOriginZip("96801");
		rateQuotePage.enterDesZip("23219");

		// Enter commodity information
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("28");
		rateQuotePage.enterWidth("28");
		rateQuotePage.enterHeight("28");
		rateQuotePage.enterDesc("Test");

		// Click on Submit
		rateQuotePage.clickOnSubmitButton();

		// 'Contact Me' link should be displayed for Guaranteed Exclusive Use,
		// Guaranteed LTL Standard Transit: 10AM, Guaranteed LTL Standard Transit: 12PM
		rateQuotePage.verifyContactMe("Guaranteed Exclusive Use", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
	}

	/*
	 * Rate Quote - LTL - Verify an Account Number can be entered and then a Rate
	 * Quote request submitted.
	 * 
	 */

	@Test(enabled = true, priority = 77) // DISABLED IN FLAKY CLASS AS IT IS PASSING NOW

	public void executeQZ_845() throws InterruptedException {
		// Login as national user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Enter Account Number: 6202474
		rateQuotePage.enterAccountNumber("6202474");

		// Enter Requester information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");

		// Enter commodity information
		rateQuotePage.selectClass("50");
		rateQuotePage.enterTotalWeight("500");

		// Click on submit
		rateQuotePage.clickOnSubmitButton();

		// Verify RateQuote Results screen is displayed with service level options for
		// LTL
		rateQuotePage.verifyTableResult();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

	}

	// UPDATED: 11/14/2019: 10:00AM

	/*
	 * Rate Quote - Verify Guaranteed Exclusive Use can handle more than 10
	 * commodities.
	 * 
	 */

	@Test(enabled = true, priority = 78)

	public void executeQZ_3094() throws InterruptedException {

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Create a new Quote
		rateQuotePage.enterContactName();
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("90001");
		rateQuotePage.enterDesZip("23219");
		// enter commodity 1 values
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("20");
		rateQuotePage.enterPieceType("CARTON");
		rateQuotePage.enterTotalWeight("5001");
		rateQuotePage.enterLength("12");
		rateQuotePage.enterWidth("12");
		rateQuotePage.enterHeight("6");
		rateQuotePage.enterDesc("Commodity1");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 2 values
		rateQuotePage.enterClass2("100");
		rateQuotePage.enterPieces2("3");
		rateQuotePage.enterPieceType2("CASE");
		rateQuotePage.enterTotalWeight2("5000");
		rateQuotePage.enterLength2("8");
		rateQuotePage.enterWidth2("8");
		rateQuotePage.enterHeight2("8");
		rateQuotePage.enterDesc2("Commodity2");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 3 values
		rateQuotePage.enterClass3("55");
		rateQuotePage.enterPieces3("3");
		rateQuotePage.enterPieceType3("PALLET");
		rateQuotePage.enterTotalWeight3("5000");
		rateQuotePage.enterLength3("48");
		rateQuotePage.enterWidth3("32");
		rateQuotePage.enterHeight3("32");
		rateQuotePage.enterDesc3("Commodity3");

		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 4 values
		rateQuotePage.enterClass4("100");
		rateQuotePage.enterPieces4("7");
		rateQuotePage.enterPieceType4("BOX");
		rateQuotePage.enterTotalWeight4("5000");
		rateQuotePage.enterLength4("20");
		rateQuotePage.enterWidth4("16");
		rateQuotePage.enterHeight4("16");
		rateQuotePage.enterDesc4("Commodity4");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 5 values
		rateQuotePage.enterClass5("55");
		rateQuotePage.enterPieces5("2");
		rateQuotePage.enterPieceType5("ROLL");
		rateQuotePage.enterTotalWeight5("1000");
		rateQuotePage.enterLength5("10");
		rateQuotePage.enterWidth5("18");
		rateQuotePage.enterHeight5("10");
		rateQuotePage.enterDesc5("Commodity5");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 6 values
		rateQuotePage.enterClass6("50");
		rateQuotePage.enterPieces6("20");
		rateQuotePage.enterPieceType6("BARREL");
		rateQuotePage.enterTotalWeight6("5000");
		rateQuotePage.enterLength6("12");
		rateQuotePage.enterWidth6("12");
		rateQuotePage.enterHeight6("12");
		rateQuotePage.enterDesc6("Commodity6");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 7 values
		rateQuotePage.enterClass7("100");
		rateQuotePage.enterPieces7("3");
		rateQuotePage.enterPieceType7("BAG");
		rateQuotePage.enterTotalWeight7("5000");
		rateQuotePage.enterLength7("12");
		rateQuotePage.enterWidth7("16");
		rateQuotePage.enterHeight7("12");
		rateQuotePage.enterDesc7("Commodity7");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 8 values
		rateQuotePage.enterClass8("55");
		rateQuotePage.enterPieces8("3");
		rateQuotePage.enterPieceType8("DRUM");
		rateQuotePage.enterTotalWeight8("5000");
		rateQuotePage.enterLength8("48");
		rateQuotePage.enterWidth8("32");
		rateQuotePage.enterHeight8("32");
		rateQuotePage.enterDesc8("Commodity8");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 9 values
		rateQuotePage.enterClass9("77.5");
		rateQuotePage.enterPieces9("7");
		rateQuotePage.enterPieceType9("PAIL");
		rateQuotePage.enterTotalWeight9("5000");
		rateQuotePage.enterLength9("20");
		rateQuotePage.enterWidth9("16");
		rateQuotePage.enterHeight9("16");
		rateQuotePage.enterDesc9("Commodity9");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 10 values
		rateQuotePage.enterClass10("60");
		rateQuotePage.enterPieces10("5");
		rateQuotePage.enterPieceType10("KIT");
		rateQuotePage.enterTotalWeight10("1000");
		rateQuotePage.enterLength10("10");
		rateQuotePage.enterWidth10("8");
		rateQuotePage.enterHeight10("8");
		rateQuotePage.enterDesc10("Commodity10");
		// click on add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// enter commodity 11 values
		rateQuotePage.enterClass11("60");
		rateQuotePage.enterPieces11("5");
		rateQuotePage.enterPieceType11("CAN");
		rateQuotePage.enterTotalWeight11("500");
		rateQuotePage.enterLength11("8");
		rateQuotePage.enterWidth11("4");
		rateQuotePage.enterHeight11("4");
		rateQuotePage.enterDesc11("Commodity11");
		rateQuotePage.verifyAlertMessage();
		rateQuotePage.enterTotalWeight("5000");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.clickOnGetQuoteButton("Guaranteed Exclusive Use");
		rateQuotePage.verifyCommoditiesTable();

	}

	// UPDATED: 11/18/2019: 1:00PM

	/*
	 * Rate Quote - LTL - Verify when 'Create BOL' button is clicked the user is
	 * navigated to the BOL Application and details in the Rate Quote form are
	 * transferred.
	 * 
	 */

	// KEERTIKA'S SCRIPT AND PRIOIRITY SET TO 94

	@Test(enabled = true, priority = 79)

	public void executeQZ_827() throws InterruptedException {

		// Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Requester Information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");

		// Commodities
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("975");
		rateQuotePage.enterDesc("From Rate Quote to Create BOL");

		// Accessorials
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.clickOnAppointmentRequest();
		rateQuotePage.clickOnLiftGateServiceDelivery();
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Get Quote
		rateQuotePage.clickOnGetQuoteButton("10AM");
		rateQuotePage.recordQuoteNumber("Gauranteed 10 AM");

		// Reserve Shipment
		rateQuotePage.selectIAcceptTheTermsAndConditionsCheckBox();
		rateQuotePage.clickOnReserveShipmentButton();
		rateQuotePage.validateReserveShipmentModelIsOpened();
		rateQuotePage.enterReserveShipmentEmailAddress("abc@estes-express.com");
		rateQuotePage.clickReserveShipmentButtonOnDialog();
		rateQuotePage.verifyRequestReceivedMessage();
		rateQuotePage.clickOnCreateBOLButton();

		// Validation
		myEstesBillOfLadingPage.verifyBillOfLandingPage();
		myEstesBillOfLadingPage.verifySuccessMessage();
		myEstesBillOfLadingPage.verifyQuoteNumberFieldIsAutoFill();
		myEstesBillOfLadingPage.verifyShipperZipCodeIsAutoFill();
		myEstesBillOfLadingPage.verifyConsigneeZipCodeIsAutoFill();
		myEstesBillOfLadingPage.verifySelectedAccessorialName("Appointment Request");
		myEstesBillOfLadingPage.verifySelectedAccessorialName("Lift-Gate Service (Delivery)");
	}

	/*
	 * Rate Quote - Verify Pickup Request can be created from Rate Quote app and
	 * quote info. is pulled into the Pickup Request form.
	 * 
	 */

	/*
	 * Rate Quote - LTL - Verify a Minimum Charge line item and a charge amount not
	 * equal to zero is displayed.
	 * 
	 */

	// NITHYA'S SCRIPT AND PRIOIRITY SET TO 96

	@Test(enabled = true, priority = 81)

	public void executeQZ_841() throws InterruptedException {

		// Login as local user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		// select quote type
		rateQuotePage.lessThanTruckloadSelected();
		// Enter Requester information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter Routing information
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");

		// Enter commodity information
		rateQuotePage.selectClass("50");
		rateQuotePage.enterTotalWeight("50");
		// Click on submit
		rateEstimatePage.clickOnSubmitButton();
		// click on get quote button for LTL
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		// verify commodity rate is 0
		rateQuotePage.verifyRateIsZero();
		// verify commodity charge is 0
		rateQuotePage.verifyChargeIsZero();
		// verify min change is displayed and its not equal to 0
		rateQuotePage.verifyMinChargeItem();

	}

	/**
	 * 
	 * This test has <automation_review> labels in JIRA
	 * 
	 * Rate Quote - Time Critical - Dispatch - Pickup request App 10AM
	 * 
	 */

	@Test(enabled = true, priority = 82)

	public void executeQZ_3091() throws InterruptedException {

		// Login as local user
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		// Enter Routing information
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		// Create a new Quote
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("23219");
		// enter commodity 1 values
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("1500");
		rateQuotePage.enterLength("36");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("Test");
		rateEstimatePage.clickOnSubmitButton();
		// click on LTL 10 AM
		rateQuotePage.clickOnGetQuoteButton("Guaranteed LTL Standard Transit: 10AM");
		// verify Reserve Shipment Button
		rateQuotePage.verifyReserveShipment("Guaranteed LTL Standard Transit: 10AM");
		// click on terms and conditions checkbox
		rateQuotePage.clickOnTermsAndConditionsFor("Guaranteed LTL Standard Transit: 10AM");
		// Verify Reserve shipment Message
		rateQuotePage.verifyReserveShipmentMessageforLTL10AM();
		// click on reserve shipment button
		rateQuotePage.clickOnReserveShipmentButton();
		// verify shipment information message
		rateQuotePage.verifyShipmentInfoMsg();
		// clcik on pickup request
		rateQuotePage.clickOnPickupRequestButton();
		// verify pick up
		rateQuotePage.verifyPickupRequestPage();

	}

	/*
	 * Web - Quote History - Lookups are by user signon
	 */

	@Test(enabled = true, priority = 83)

	public void executeQZ_3104() throws InterruptedException {
		// Step 1: Launch application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as testgroup1 user
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: From Ship, Select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);

		// Step 4: Confirm that only LTL (Incl. Guaranteed) is selected under Rate Quote
		// Type.
		// By default only LTL will be selected. So no action performed here.

		// Enter the required fields: --> Requester Information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("90007");

		// Step 5: Under 'Commodities', populate the following fields:
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterDesc("Verify lookups are by signon");// ?

		// Step 6: Click 'Submit Request' button.
		rateQuotePage.clickOnSubmitButton();

		// Step 7: Select a service level and record the quote number.
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		String quote = rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Step 8: Click *Rate Quote History*
		rateQuotePage.clickOnQouteHistoryTab();
		testUtil.setHardWait(3000);

		// Validate the *Quote Number* captured in the previous step is displayed in
		// history
		QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable(quote, true,5000,0);

		// Step 9: Click 'Logout'.
		myEstesHomePage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		myEstesHomePage.clickOnConfirmButton();

		// Step 10: Login to 'MyEstes' application using provided credentials:
		// testgroup2,testgroup2

		testUtil.setHardWait(1000);
		driver.get(url);
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		myEstesLoginPage.enterUserName(username9);
		myEstesLoginPage.enterPassword(password9);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 11: From Ship, Select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);

		// Step 12: Confirm that only LTL (incl. Guaranteed) is selected under Rate
		// Quote Type. Enter info in all required fields.
		// By default only LTL will be selected. So no action performed here.

		// Requester Information
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("23060");
		rateQuotePage.enterDesZip("77071");

		// Step 13: Under Commodities, populate the following fields:
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("900");
		rateQuotePage.enterDesc("Verify lookups are by signon");

		// Step 14: Click Submit Request button.
		rateQuotePage.clickOnSubmitButton();

		// Step 15: Click on any service level quote and record quote#
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		String quote1 = rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		// Step 16: Click on 'Quote History'.
		rateQuotePage.clickOnQouteHistoryTab();
		testUtil.setHardWait(2000);

		// Validate the *Quote Number* captured in the previous step is displayed in
		// history
		QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable(quote1, true,5000,0);

		// Step 17: Validate quote created by testgroup1 user is *NOT* displayed in
		// history
		QuoteHistoryPage.verifyQuoteNumPresentOrAbsentInTable(quote, false,5000,0);

	}
	/*
	 * Volume and Truckload - Verify when Volume and Truckload Basic charges are
	 * selected all calculated quotes are saved to Quote History
	 */

	@Test(enabled = true, priority = 84)
	public void executeQZ_3193() {

		String vtlBasic = "Volume and Truckload Basic";

		// Login to eNet application
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Applications--> VTL Rate Quote
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLRateQuoteLink();
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("Estes");
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber("5068692");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("estes@estes-express.com");
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole("Third Party");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8001234569");
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms("Pre-paid");
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("16159");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("30307");
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();
		eNetVTLRateQuotePage.selectVTLRateQuoteClass("50");
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("3");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("PALLET");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("9700");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("48");

		// Click on Submit button and record Quote Number
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		String basicQuoteNo = eNetVTLRateQuotePage.recordQuoteNumber(vtlBasic);
		String QuoteNo = (String) basicQuoteNo.subSequence(1, basicQuoteNo.length());

		// Applications--> Quote History Lookup
		eNetHomePage.clickOnApplicationWithoutFrame();
		eNetApplicationsPage.clickOnQuoteHistoryLookupLink();

		testUtil.setHardWait(65000); // Not in step: Added long wait to search quote history
		testUtil.reloadPage(); // Not in step: reload needed

		// Validate VTL basic charges
		testUtil.switchToFrame("mainpage");
		eNetQuoteHistoryLookupPage.enterQuoteNumber(basicQuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber(basicQuoteNo);

		// Validate LTL Standard Transit
		eNetQuoteHistoryLookupPage.enterQuoteNumber("L" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("L" + QuoteNo);

		// Validate Guaranteed Exclusive Use
		eNetQuoteHistoryLookupPage.enterQuoteNumber("X" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("X" + QuoteNo);

		// Validate Guaranteed Volume and Truckload Economy charges
		eNetQuoteHistoryLookupPage.enterQuoteNumber("E" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("E" + QuoteNo);

		// Validate Guaranteed Volume and Truckload Standard charges
		eNetQuoteHistoryLookupPage.enterQuoteNumber("S" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("S" + QuoteNo);

		// Validate Guaranteed LTL Standard Transit: 10AM
		eNetQuoteHistoryLookupPage.enterQuoteNumber("3" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("3" + QuoteNo);

		// Validate Guaranteed LTL Standard Transit: 5PM
		eNetQuoteHistoryLookupPage.enterQuoteNumber("1" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("1" + QuoteNo);

		// Validate Guaranteed LTL Standard Transit: 12PM
		eNetQuoteHistoryLookupPage.enterQuoteNumber("2" + QuoteNo);
		eNetQuoteHistoryLookupPage.clickOnSearchButton();
		eNetQuoteHistoryLookupPage.verifyQuoteNumber("2" + QuoteNo);
	}

	/*
	 * MyEstes LTL Rate Quote uses Floor Minimum charge if the calculated rate is
	 * less then the minimum rate
	 */

	@Test(enabled = true, priority = 85)

	public void executeQZ_9353() throws InterruptedException {

		// Step : Launch application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step : Login as testgroup1 user
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Step : From Ship, Select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step : Confirm that only LTL (incl. Guaranteed) is selected under Rate Quote
		// Type. Enter info in all required fields.
		// By default only LTL will be selected. So no action performed here.

		// Requester Information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("23111");

		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("100");
		rateQuotePage.enterDesc("testing test case qz-9353");

		// Step : Click Submit Request button.
		rateQuotePage.clickOnSubmitButton();

		// Step : Click on any service level quote and record quote#
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyRateIsZero();

		// click on revise quote button
		rateQuotePage.clickOnReviseQouteButton();

		// Routing Information
		rateQuotePage.enterClass("200");
		rateQuotePage.enterTotalWeight("1000");

		// Step : Click Submit Request button.
		rateQuotePage.clickOnSubmitButton();

		// Step : Click on any service level quote and record quote#
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// verify
		rateQuotePage.verifyRateIsZero();
		rateQuotePage.verifyDiscountChargeItem();

	}

	/*
	 * MyEstes LTL Rate Quote displays Estimated Freight Charges and Mexico
	 * accessorials when the shipment is to or from Mexico
	 */

	@Test(enabled = true, priority = 86)

	public void executeQZ_9352() throws InterruptedException {

		// Step : Launch application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step : Login as testgroup1 user
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Step : From Ship, Select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step : Confirm that only LTL (incl. Guaranteed) is selected under Rate Quote
		// Type. Enter info in all required fields.
		// By default only LTL will be selected. So no action performed here.

		// Requester Information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Routing Information
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.selectDestinationCountry("Mexico");
		rateQuotePage.enterDesZip("44100");

		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterDesc("testing test case qz-9352");

		// Step : Click Submit Request button.
		rateQuotePage.clickOnSubmitButton();

		// Step: Verify LTL Standard Transit Charges is greater than $0.00
		rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);

		// Step: Click the *Get Quote #* on the *LTL Standard Transit* line
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// verify
		rateQuotePage.verifyMexicoFreightChargeChargeItem();
		rateQuotePage.verifyMexicoFuelSurchargeChargeItem();

		// click on revise quote button
		rateQuotePage.clickOnReviseQouteButton();

		// Routing Information
		rateQuotePage.selectOriginCountry("Mexico");
		rateQuotePage.enterOriginZip("44100");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("23233");

		// Step : Click Submit Request button.
		rateQuotePage.clickOnSubmitButton();

		// Step: Verify LTL Standard Transit Charges is greater than $0.00
		rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);

		// Step: Click the *Get Quote #* on the *LTL Standard Transit* line
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// verify
		rateQuotePage.verifyMexicoFreightChargeChargeItem();
		rateQuotePage.verifyMexicoFuelSurchargeChargeItem();

	}

	/**
	 * this test fails HAWAIIAN TERMINAL HANDLING FEE is not present in the CHARGE
	 * ITEMS table
	 * 
	 * Rate Quote LTL - Verify Total Estimated Freight Charges displays when
	 * Destination terminal is located in Hawaii
	 */

	@Test(enabled = true, priority = 87)

	public void executeQZ_9348() throws InterruptedException {

		String hawaiianFees = "HAWAIIAN TERMINAL HANDLING FEE";
		String hawaiianTax = "HAWAIIAN STATE TAX";

		// Login to MyEstes as Test Admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Requester Information
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.enterContactName("QZ-9348");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8005551212");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Pickup Information
		rateQuotePage.selectTodayDate();

		// Routing Information
		rateQuotePage.enterOriginZip("77041");
		rateQuotePage.enterDesZip("96801");

		// Commodities
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This test case validates what you test.");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero("Total Estimated Freight Charges");
		rateQuotePage.validateChargeItemsTableDescription(hawaiianFees);
		rateQuotePage.validateChargeItemsTableDescription(hawaiianTax);
	}

	/*
	 * Rate Quote LTL for Puerto Rico includes accessorials SED and FVC when
	 * Declared Value is greater than 10,001
	 */
	@Test(enabled = true, priority = 88)

	public void executeQZ_9329() throws InterruptedException {

		String fullCoverageAcc = "Full Value Coverage (FVC)";
		String prAcc = "PR ELECTRONIC EXPORT TRANSACTION";

		// Login to MyEstes as Test Admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Requester Information
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.enterContactName("QZ-9329");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8005551212");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Pickup Information
		rateQuotePage.selectTodayDate();

		// Routing Information
		rateQuotePage.enterOriginZip("90007");
		rateQuotePage.enterDesZip("00602");

		// Commodities
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("3700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This test case validates what you test.");
		rateQuotePage.enterFullValueCoverage("10001");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero("Total Estimated Freight Charges");
		rateQuotePage.validateChargeItemsTableDescription(fullCoverageAcc);
		rateQuotePage.validateChargeItemsTableDescription(prAcc);
	}

	/*
	 * Rate Quote LTL returns Estimated Freight Charges using the Per CWT
	 * calculation type
	 */

	// retryAnalyzer=analyzer.RetryAnalyzer.class
	@Test(enabled = true, priority = 89)

	public void executeQZ_9333() {
		String query = "select gsqtnum from fbfiles.gsc00p110 where gsqid = ";
		driver.get(url2);

		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		eNetHomePage.clickOnApplicationWithoutFrame();
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		// Contact and Scheduling Information
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("QA Tester");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("qatest@estes-express.com");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("804 353 1900");
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber("5038029");
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole("Shipper");
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms("Pre-paid");
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("62025");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("33619");

		// Scheduling
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();

		// Commodities
		eNetVTLRateQuotePage.selectVTLRateQuoteClass("100");
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("1");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("PALLET");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("230");
		testUtil.setHardWait(2000);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("41");
		testUtil.setHardWait(2000);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("40");
		testUtil.setHardWait(2000);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("25");
		testUtil.setHardWait(3000);
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();
		// eNetVTLRateQuotePage.recordQuoteNumberFromVTLAndLTLTable("Volume and
		// Truckload Basic");
		eNetVTLRateQuotePage.verifyVTLRateQuotePage2IsDisplayed();

		String qNo = eNetVTLRateQuotePage.recordQuoteNumberFromVTLAndLTLTable("LTL Standard Transit");
		List<String> returnVal = sqlDataList.getFirstRowDetailsFromEXLAQA(query + "'" + qNo + "'");
		TestUtil.verifyText(returnVal.get(0), "CWT");
	}

	/*
	 * MyEstes Quote provides Full Value Coverage for LTL option
	 */

	@Test(enabled = true, priority = 90)

	public void executeQZ_9355() throws InterruptedException {

		String fullCoverageAcc = "Full Value Coverage (FVC)";
		String fullCoverageWaived = "Full Value Coverage (FVC) - WAIVED";

		// Login to MyEstes as Test Admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Requester Information
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.enterContactName("QZ-9355");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Pickup Information
		rateQuotePage.selectTodayDate();

		// Routing Information
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("75320");

		// Commodities
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("343");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("This test case validates what you test.");
		rateQuotePage.enterFullValueCoverage("10001");
		rateQuotePage.verifyCheckBoxIsChecked();
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.validateChargeItemsTableDescription(fullCoverageAcc);
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero("Full Value Coverage (FVC)");
		rateQuotePage.clickOnReviseQuoteButton();
		rateQuotePage.deselectAdditionalCoverage();
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.validateChargeItemsTableDescription(fullCoverageWaived);
		rateQuotePage.verifyChargeItemTableChargesEqualToZero(fullCoverageWaived);

	}

	/*
	 * Rate Quote LTL returns Estimated Freight Charges using the Per Unit
	 * calculation type
	 */

	@Test(enabled = true, priority = 91)
	public void executeQZ_9332() throws Exception {

		// Step 1: Launch eNet application
		driver.get(url2);

		// Step 2: Login with your own credentials
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: From eNet page top navigation, Click *Applications*
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: From the Applications page, find the *Customer Service* applications
		// list. Click *VTL Rate Quote*
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		// Step 5: From the *Volume & Truckload Rate Quote* page 1, fill all required
		// fields and enter or select the following values:
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("QA Tester");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("qatest@estes-express.com");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900");
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole("Shipper");
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms("Pre-paid");
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber("4633688");
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("32810");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("35904");

		// Step 6: Enter *Commodities* details
		eNetVTLRateQuotePage.selectVTLRateQuoteClass("50");
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("1");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("PALLET");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("915");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("40");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("45");

		// Step 7: Click *Submit*
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		// Step 8: From Volume & Truckload Rate Quote page 2, select *LTL Standard
		// Transit* charges (displays as a link)
		eNetVTLRateQuotePage.selectQouteByServiceLevel("TC", "LTL Standard Transit");
		testUtil.setHardWait(1000);
		// Step 9: From Volume & Truckload Rate Quote page 3, record the *Quote Number*
		String quoteNumber = eNetVTLRateQuotePage.getQuoteNumber();

		// Step 10: Query the database table to verify the calculation type: *select
		// gsqtnum from fbfiles.gsc00p110 where gsqid = 'quote number from previous
		// step'*
		String actual = testUtil
				.getValueFromQuery("select gsqtnum from fbfiles.gsc00p110 where gsqid = '" + quoteNumber + "'");

		// Verify Returned value = *UNI*
		TestUtil.verifyText(actual, "UNI");
	}

	/*
	 * Rate Quote LTL - Verify Total Estimated Freight Charges displays when
	 * Destination terminal is located in Puerto Rico
	 */

	@Test(enabled = true, priority = 92)
	public void executeQZ_9327() throws InterruptedException {
		// Step 1: Launch my estes application
		// Step 2: Login to application as admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Click Ship
		myEstesHomePage.clickOnShipTab();
		// Step 4: Select Rate Quote
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);

		// Step 5: Section, select *Volume and Truckload (incl. Guaranteed)*; Do not
		// deselect *Less Than Truckload*
		rateQuotePage.selectOrDeselectValumeAndTruckload();

		// Step 6:In Requester information, enter required details
		rateQuotePage.enterContactName();
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterPhoneNo("8005551212");

		// Step 7: Enter today's date as pickup date
		rateQuotePage.selectTodayDate();

		// Step 8: Enter Origin: Zip=90007 and Destination: Zip=00602
		rateQuotePage.enterOriginZip("90007");
		rateQuotePage.enterDesZip("00602");

		// Step 9: Select Commodity details, Pieces: 7, Piece Type: Pallet, Total
		// Weight: 3700, Length: 48
		// Width:48, Height: 48, Description: This test case validates what you test.
		rateQuotePage.enterClass("50"); // Class field is required. So added step to enter class
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("3700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");

		// Step 10: Click Submit button
		rateQuotePage.clickOnSubmitButton();

		// Step 11: From 'Rate Quotes' page , Click *Get Quote#* on the line for *LTL
		// Standard Transit*
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Verify Total Estimated Freight Charges
		rateQuotePage.getChargeItemsTableCharges("Total Estimated Freight Charges");

		// Verify charges for *PR DOCUMENTATION CHARGE* and *PR OCEAN CHARGES* have been
		// added
		rateQuotePage.getChargeItemsTableCharges("PR DOCUMENTATION CHARGE");
		rateQuotePage.getChargeItemsTableCharges("PR OCEAN CHARGES");
	}

	/** Updated steps per new changes hence turned on the test **/
	/**
	 * This test fails because its expecting CAP but returns EXL
	 * 
	 * Rate Quote LTL returns Estimated Freight Charges using the Per Cubic Capacity
	 * calculation type
	 */

	@Test(enabled = true, priority = 93)
	public void executeQZ_9334() throws Exception {
		// Step 1: Launch eNet application
		driver.get(url2);

		// Step 2: Login with your own credentials
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: From eNet page top navigation, Click *Applications*
		eNetHomePage.clickOnApplicationsTab();
		testUtil.setHardWait(500);
		// Step 4: From the Applications page, find the *Customer Service* applications
		// list. Click *VTL Rate Quote*
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		// Step 5: From the *Volume & Truckload Rate Quote* page 1, fill all required
		// fields and enter or select the following values:
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("QA Tester");
		eNetVTLRateQuotePage.selectSelectOne("Base Rate");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("qatest@estes-express.com");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900");
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("30307");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("23059");

		// Step 6: Enter *Commodities* details
		eNetVTLRateQuotePage.selectVTLRateQuoteClass("50");
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("5");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("PALLET");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("2500");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("48");

		// Step 7: Click *Submit*
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		// Step 8: From Volume & Truckload Rate Quote page 2, select *LTL Standard
		// Transit* charges (displays as a link)
		eNetVTLRateQuotePage.selectQouteByServiceLevel("TC", "LTL Standard Transit");

		// Step 9: From Volume & Truckload Rate Quote page 3, record the *Quote Number*
		String quoteNumber = eNetVTLRateQuotePage.getQuoteNumber();

		// Step 10: Query the database table to verify the calculation type: *select
		// gsqtnum from fbfiles.gsc00p110 where gsqid = 'quote number from previous
		// step'*
		String actual = testUtil
				.getValueFromQuery("select gsqtnum from fbfiles.gsc00p110 where gsqid = '" + quoteNumber + "'");

		// Verify Returned value = *EXL*
		TestUtil.verifyText(actual, "EXL");

		// Step 11: Click *Revise Quote*
		eNetVTLRateQuotePage.clickOnUpdateQuote();

		// Step 12: Change the commodity dimensions to the following values:
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("60");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("60");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("60");

		eNetVTLRateQuotePage.enterVTLRateQuotePieces("3"); // Updated to 3 from 4 per manual steps
		// Step 13: Click on Submit
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		// Step 14: From Volume & Truckload Rate Quote page 2,select *LTL Standard
		// Transit* charges (displays as link)
		eNetVTLRateQuotePage.selectQouteByServiceLevel("TC", "LTL Standard Transit");

		// Step 15: From Volume & Truckload Rate Quote page 3, record the *Quote Number*
		String quoteNumber1 = eNetVTLRateQuotePage.getQuoteNumber();

		// Step 16: Query the database table to verify the calculation type: *select
		// gsqtnum from fbfiles.gsc00p110 where gsqid = 'quote number from previous
		// step'*
		String actual1 = testUtil
				.getValueFromQuery("select gsqtnum from fbfiles.gsc00p110 where gsqid = '" + quoteNumber1 + "'");

		// Returned value = *CAP*
		TestUtil.verifyText(actual1, "CAP");
	}

	/**
	 * 
	 * This test needs to be updated
	 * 
	 * MyEstes Rates Use regional OR if it is effective on the quote request date
	 * The Balance Adjustment Claculation applies - Functional (ALM TEST ID: 8029)
	 */

	@Test(enabled = true, priority = 94)
	public void executeQZ_5984() throws InterruptedException {

		String message = "OR% 36 added successfully.";
		String orPercent = "36";
		String expectedOR = "36.00";
		String startLinearFt = "1";
		String endLinearFt = "10";
		String balanceAdjStart = "1";
		String balanceAdjEnd = "9";

		// Login to eNet application
		driver.get(url2);
		System.out.println(
				"THIS TEST NEEDS TO BE UPDATED*****************************************************************>");
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Add 36% to Target Operating revenue
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLTableMaintenanceLink();
		eNetVTLTableMaintenancePage.clickOnORTab();
		eNetORPage.removeAllORPercent();
		eNetORPage.clickOnFutureORRadioButton();
		eNetORPage.enterORPercent(orPercent);
		eNetORPage.selectOrigRegion("Estes Northeast");
		eNetORPage.selectDestRegion("Estes Northeast");
		eNetORPage.enterBalanceAdjustmentStart(balanceAdjStart);
		eNetORPage.enterBalanceAdjustmentEnd(balanceAdjEnd);
		eNetORPage.enterStartLinearFeet(startLinearFt);
		eNetORPage.enterToLinearFeet(endLinearFt);
		eNetORPage.enterEffectiveStartDate();
		eNetORPage.enterEffectiveEndDate();
		eNetORPage.clickOnAddButton();
		eNetORPage.verifyORAddedMessageIsDisplayed(message);
		eNetORPage.verifyLinearFeetIsInRangeFormat(startLinearFt + " - " + endLinearFt);
		eNetORPage.verifyBalanceAdjustmentInRangeFormat(balanceAdjStart + " - " + balanceAdjEnd);

		// Login to MyEstes
		driver.get(url);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();
		rateQuotePage.enterContactName("QZ-5984");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
//		rateQuotePage.selectTodayDate();   //removed as it selects plus 2 date
		rateQuotePage.selectCurrentDate(); // Added newly
		rateQuotePage.enterOriginZip("10001");
		rateQuotePage.enterDesZip("10002");

		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("BAG");
		rateQuotePage.enterTotalWeight("200");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("QZ-5984");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Get Quote number from VTL basics
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		String quoteNo = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		driver.get(url2);
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();
		eNetVTLQouteExceptionQueuePage.enterQouteNumber(quoteNo);
		eNetVTLQouteExceptionQueuePage.clickOnSubmitButton();
		eNetVTLQouteExceptionQueuePage.clickOnFirstVTLQuoteExceptionLink();
		eNetVTLQouteExceptionQueuePage.validateTargetORSystem(expectedOR);
	}

	/*
	 * Rate Quote - Verify FVC Functionality to Freight Information Section for LTL
	 * Quote
	 */

	@Test(enabled = true, priority = 95)

	public void executeQZ_9409() throws Throwable {

		String fullCoverageAcc = "Full Value Coverage (FVC)";
		String fullCoverageWaived = "Full Value Coverage (FVC) - WAIVED";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(2000);
		// myEstesLoginPage.clickonSubmitButton();
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("75320");

		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("343");
		rateQuotePage.enterFullValueCoverage("10001");
		rateQuotePage.verifyCheckBoxIsChecked();
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		rateQuotePage.validateChargeItemsTableDescription(fullCoverageAcc);// add
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero("Full Value Coverage (FVC)");// add

		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.deselectAdditionalCoverage();
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");// add
		rateQuotePage.validateChargeItemsTableDescription(fullCoverageWaived);// add
		rateQuotePage.verifyChargeItemTableChargesEqualToZero(fullCoverageWaived);// add

	}
	/*
	 * MyEstes LTL Rate Quote displays Total Estimated Freight Charges and Alaska
	 * accessorials when shipment is to or from Alaska
	 */

	@Test(enabled = true, priority = 96)

	public void executeQZ_9351() throws InterruptedException {

		String alaskaFC = "ALASKA FREIGHT CHARGES";
		String alaskaSC = "ALASKA FUEL SURCHARGE";

		// Login to MyEstes as Test Admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.verifyCreateRateQoutePage();

		// Rate Quote Information
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("99502");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterDesc("Any");
		rateQuotePage.clickOnSubmitButton();

		// Validate Alaska charges & accessorials
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero("Total Estimated Freight Charges");
		rateQuotePage.verifyServiceChargeItemDesc(alaskaFC);
		rateQuotePage.verifyServiceChargeItemDesc(alaskaSC);
		testUtil.setHardWait(1000);
		// Revise Quote
		rateQuotePage.clickOnReviseQouteButton();
		rateQuotePage.enterOriginZip("99567");
		rateQuotePage.enterDesZip("16159");
		rateQuotePage.clikOnSubmitButton();

		// Validate Alaska charges & accessorials
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero("Total Estimated Freight Charges");
		rateQuotePage.verifyServiceChargeItemDesc(alaskaFC);
		rateQuotePage.verifyServiceChargeItemDesc(alaskaSC);
	}

	/*
	 * Rate Quote - Verify a Message is displayed and LTL rates are not displayed
	 * when the calculated length is greater then 20 feet
	 */

	/* NEEDS TO BE REMOVED FROM AUTOMATION SUITE FOR NOW-KATHY! */

	@Test(enabled = true, priority = 97)

	public void executeQZ_9443() throws Throwable {

		String expectedAcc = "OVERLENGTH CHARGE-20.00' - 27.9'";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.verifyCreateRateQoutePage();
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		rateQuotePage.enterContactName("QZ-3076");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30007");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("700");
		rateQuotePage.enterLength("241");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyCreateRateQoutePage();
		rateQuotePage.verifyDisclaimerMsgForOverLengthCharge();

		rateQuotePage.verifyContactMe("Gauranteed 10 AM", true);
		rateQuotePage.verifyContactMe("Gauranteed 12 PM", true);
		rateQuotePage.verifyContactMe("Gauranteed 5 PM", true);

		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.verifyChargeItems(expectedAcc);

		rateQuotePage.clickOnReviseQuoteButton();

		rateQuotePage.verifyCreateRateQoutePage();
		rateQuotePage.enterPieces("6");

		testUtil.setHardWait(1000);

		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyCreateRateQoutePage();
		rateQuotePage.verifyDisclaimerForLinearFootage();

		rateQuotePage.verifyBasicCharge(" LTL Standard Transit", false);

		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

	}

	/*
	 * Rate Quote - Verify a Disclaimer is displayed, Overlength Charge-28.00' -
	 * 39.99' accessorial is automatically added, and Guaranteed LTL Standard
	 * Transit rates are not calculated when a single piece length is between 28.00'
	 * and 39.99'
	 */
	@Test(enabled = true, priority = 98)

	public void executeQZ_9349() throws InterruptedException {

		String errMessage = "Pieces, weights or dimensions may require special handling to ensure the guaranteed service level. Please contact the Estes Solution Center at 1-800-645-3952.";
		String expectedAcc = "OVERLENGTH CHARGE-28.00' - 39.9'";
		String accName = "Overlength Freight (28.00' to 39.99')";

		// Login to MyEstes as Test admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		rateQuotePage.verifyRateQuotePageTitle();

		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Rate quote details
		rateQuotePage.enterContactName("QZ-9349");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();

		rateQuotePage.enterOriginZip("30303");
		rateQuotePage.enterDesZip("77051");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("200");
		rateQuotePage.enterLength("336");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("24");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Validate Contact me & disclaimer message
		rateQuotePage.validateContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.validateContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.validateContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 5PM");
		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.verifyDisclaimerIsDisplayed(errMessage);
		rateQuotePage.verifyChargeItems(expectedAcc);

		// Reverse quote and update length as 479
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.verifyRateQuotePageTitle();
		rateQuotePage.enterLength("479");
		rateQuotePage.deselectSelectedAccessorials(accName); // deselect
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Validate Contact me & disclaimer message
		rateQuotePage.verifyDisclaimerIsDisplayed(errMessage);
		rateQuotePage.validateContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.validateContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.validateContactMeLinkByServiceLevel("Guaranteed LTL Standard Transit: 5PM");
		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.verifyChargeItems(expectedAcc);

		// Reverse quote and update length as 95
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.verifyRateQuotePageTitle();
		rateQuotePage.enterLength("95");
		rateQuotePage.verifyAccessorialIsSelected(accName);
		rateQuotePage.clickOnSubmitButton();

		// Validate charges
		rateQuotePage.validateGetQuoteButtonByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		rateQuotePage.validateGetQuoteButtonByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		rateQuotePage.validateGetQuoteButtonByServiceLevel("Guaranteed LTL Standard Transit: 5PM");
		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		boolean exist = rateQuotePage.verifyOverLengthChargesExistence(expectedAcc);
		Assert.assertFalse(exist);
	}

	/*
	 * Rate Quote- LTL - Verify when selecting Origin or Destination terminal info a
	 * Supporting Terminal Information modal displays with the correct data
	 */

	@Test(enabled = true, priority = 99)
	public void executeQZ_847() throws InterruptedException {

		// Step 1: Open application
		// Step 2: Login
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Ship--> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: Select *Less than Truckload (incl. Guaranteed)* // No action done as
		// this is service level is selected by default

		// Step 5: Select *Consignee as Roles and Collect as Terms //No action done to
		// select term as Collect is the default term for Consignee
		rateQuotePage.enterMyRole("Consignee");

		// Step 6: Origin -> 30307, Destination -> 77071
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("77071");

		// Step 7: Class -> 100, Total Weight -> 879, Description -> QZ-847
		rateQuotePage.enterClass("100");
		rateQuotePage.enterTotalWeight("879");
		rateQuotePage.enterDesc("QZ-847");

		// Step 8: Click on Submit
		rateQuotePage.clickOnSubmitButton();

		// Step 9: Get Quote for LTL Standard Transit
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Step 10: Click on City/State/Zip link under origin
		rateQuotePage.clickOnZipInQuoteDetails("30307");
		// Validate Supporting Terminal modal is displayed
		rateQuotePage.verifySupportingTerminalInfoDisplayed();

		// Step 11: Validate Supporting Terminal Details
		rateQuotePage.verifySupportingTerminalDetails();

		// Step 12 & 13 : Click Terminal email address --> This step is to verify email.
		logger.info("Verify Email manually!!!!");

		// Step 14: Close Supporting Terminal modal
		rateQuotePage.clickCloseButtonOnPopup();

		// Step 15: Click on City/State/Zip link under Destination
		rateQuotePage.clickOnZipInQuoteDetails("77071");
		// Validate Supporting Terminal modal is displayed
		rateQuotePage.verifySupportingTerminalInfoDisplayed();

		// Step 16: Validate Supporting Terminal Details
		rateQuotePage.verifySupportingTerminalDetails();

		// Step 17 & 18: Click Terminal email address --> This step is to verify email.
		logger.info("Verify Email manually!!!!");

		// Step 19: Close Supporting Terminal modal
		rateQuotePage.clickCloseButtonOnPopup();
	}

	/*
	 * Rate Quote - Verify when a require field is left blank on submit an inline
	 * error displays
	 * 
	 */
	@Test(enabled = true, priority = 100)
	public void executeQZ_6006() throws InterruptedException {

		// Step 1: Open My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		// Step 2: Login as smokelocal
		myEstesLoginPage.enterUserName(username11);
		myEstesLoginPage.enterPassword(password11);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Navigate to Ship -> Rate Quote
		myEstesHomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		// Step 4: check *Volume and Truckload (incl. Guaranteed)* and *Time Critical
		// Expedited* quote type.
		// Note: all types should be checked
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Step 5: Without entering any data, click *Submit Request*
		rateQuotePage.clickOnSubmit();

		// Step 6: Inline error *This field is required.* display for all required
		// fields
		rateQuotePage.verifyReqFldErrForReqInfo();
		rateQuotePage.verifyReqFldErrForPickupDate();
		rateQuotePage.verifyReqFldErrForRoutingInfo();
		rateQuotePage.verifyReqFldErrForCommodity();
	}

	/**
	 * 
	 * This test passed on 6/29/22
	 * 
	 * Rate Quote - LTL - Verify when total weight is over the LTL weight limit a
	 * message advising the user to select the Volume and Truckload quote type is
	 * displayed
	 */
	@Test(enabled = true, priority = 101)
	public void executeQZ_843() throws InterruptedException {

		String expectedErrMsg = "To receive rates for a shipment weighing more than 20,000 lbs., please select the Volume and Truckload option in the Quote Type section above.";

		// Step 1: Open My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);

		// Step 2: Login as smokelocal
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		// Step 3: Navigate to Ship -> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		// Step 4: Only *Less than Truckload (incl. Guaranteed)* quote type is selected
		// by default.
		// This is selected by default

		// Step 5: Entre requester info
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.selectRole();
		rateQuotePage.selectTerms();

		// Step 6: Enter routing info
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("30307");

		// Step 7: Enter commdoity details
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("10000");
		rateQuotePage.enterDesc("Testdata2");

		// Step 8: Click on Add Commodity button
		rateQuotePage.clickOnAddCommodityButton();

		// Step 9: Enter commodity details
		rateQuotePage.enterClass2("50");
		rateQuotePage.enterTotalWeight2("5000");
		rateQuotePage.enterDesc2("TestData2");

		// Step 10: Click on Add commodity button
		rateQuotePage.clickOnAddCommodityButton();

		// Step 11: Enter commodity details
		rateQuotePage.enterClass3("50");
		rateQuotePage.enterTotalWeight3("5001");
		rateQuotePage.enterDesc3("TestData3");

		// Verify error message
		String actualErrMsg = testUtil
				.getTextOfElement(driver.findElement(By.xpath("//app-commodity-list//lib-feedback//span")));
		Assert.assertEquals(actualErrMsg, expectedErrMsg);
	}

	/**
	 * This test passed on 6/29/22
	 * 
	 * Rate Quote - LTL - Verify error message is received if Account number entered
	 * is not party to the Account - National Account
	 */

	@Test(enabled = true, priority = 102)
	public void executeQZ_835() throws InterruptedException {

		String expectedErrMsg = "Please enter a valid account number to rate this quote.";

		// Step 1: Open My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as smokelocal
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(500);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Navigate to Ship -> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: Select only *LTL (Incl. Guaranteed)* checkbox. --> No action
		// performed here as this is the default selection

		// Step 5: Enter requester info
		rateQuotePage.enterAccountNumber("0062372");
		rateQuotePage.selectRole();
		rateQuotePage.selectTerms();

		// Step 6: Enter routing info
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");

		// Step 7: Enter details in commodity section
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("9500");

		// Step 8: Click on submit
		rateQuotePage.clickOnSubmit();
		// Verify error message
		String actualErrMsg = testUtil
				.getTextOfElement(driver.findElement(By.xpath("//app-create-rate-quote//lib-feedback//span")));
		Assert.assertEquals(actualErrMsg, expectedErrMsg);

	}

	/*
	 * Rate Quote - LTL - Verify a Disclaimer is displayed and Guaranteed LTL
	 * Standard Transit rates are not calculated when Overlength Charge - 40' or
	 * Greater accessorial is selected
	 * 
	 */

	// AS PER DEE, TESTS THAT VERIFY EMAIL IN THE ROUNDCUBE SHOULD BE TUNRED OFF
	// UNTIL FURTHER NOTICE

	@Test(enabled = false, priority = 103)
	public void executeQZ_866() throws InterruptedException {

		// Step 1: Open My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as admin
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Navigate to Ship -> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: Select only *LTL (Incl. Guaranteed)* checkbox. --> No action
		// performed here as this is the default selection

		// Step 5: Select *Consignee as Roles and Collect as Terms --> No action done to
		// select term as Collect is the default term for Consignee
		rateQuotePage.enterMyRole("Consignee");

		// Step 6: Origin -> 30307, Destination -> 77071
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("77071");

		// Step 7: Class -> 100, Total Weight -> 879, Description -> QZ-847
		rateQuotePage.enterClass("100");
		rateQuotePage.enterTotalWeight("879");
		rateQuotePage.enterDesc("QZ-866");

		// Step 8: Select *Overlength Freight (40 feet or greater)* accessorial
		rateQuotePage.selectOrDeselectAccessorials("Overlength Freight (40 feet or greater)");

		// Step 9: Click on Submit
		rateQuotePage.clickOnSubmitButton();

		// Step 10: Verify disclaimer
		rateQuotePage.verifyDisclaimer();

		// Step 11: Verify all Gauranteed rates display contact me
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);

		// Step 12: Click Contact me for any gauranteed service level, enter req fields
		// and submit
		rateQuotePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");

		// Verify Contact Us dialog displayed
		rateQuotePage.verifyContactUsDialogExistence();

		// Enter data Contact Us dialog
		rateQuotePage.enterContactName("QA Tester");
		rateQuotePage.enterYourEmail("EITQA@estes-express.com");
		rateQuotePage.enterPhoneNo("5546648842");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterLength("20");
		rateQuotePage.enterWidth("20");
		rateQuotePage.enterHeight("20");

		// Click on confirm button
		rateQuotePage.clickOnConfirmButton();

		// Step 13: Access round cube and verify email
		logger.info("Verify email content manually!!!!!!");
	}

	// AS PER DEE, TESTS THAT VERIFY EMAIL IN THE ROUNDCUBE SHOULD BE TUNRED OFF
	// UNTIL FURTHER NOTICE.

	@Test(enabled = false, priority = 104)
	public void executeQZ_3071() throws InterruptedException {
		// Step 1: Login as admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username11);
		myEstesLoginPage.enterPassword(password11);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(5000);

		// Step 2: Navigate to Ship -> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 3: Select Time Critical
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Step 4: Enter required details
		rateQuotePage.enterContactName("QZ-3071");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterTerms("Collect");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("90007");

		// Step 5: Enter commodity details
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("32");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("This is test case validation");

		// Step 6: Click on submit
		rateQuotePage.clickOnSubmitButton();

		// Step 7: Click gauaranteed option where contact me displayed
		rateQuotePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");

		// Step 8 to 12 deals with email
		logger.info("Verify freight charges in email manually!!!!!!");
	}

	/**
	 * This test fails because Service Temporarily Suspended message is not
	 * displayed anywhere on the page
	 * 
	 * Rate Quote - LTL - Verify when origin or/and destination are blocked points
	 * an error message is displayed
	 */
	@Test(enabled = true, priority = 105)

	public void executeQZ_824() throws InterruptedException {

		String expectedErrMsg = "Service Temporarily Suspended";

		// Step 1: Open application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as group user
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Navigate Ship -> Rate
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);
		// Step 4: Only *Less than Truckload (Incl. Guaranteed)* rate quote type is
		// selected (by default)
		// Note -> No action performed here as this is selected by default

		// Step 5: Enter requester info
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.selectRole();
		rateQuotePage.selectTerms();

		// Step 6: Enter routing info
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("80023");

		// Step 7: Enter commodity details
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("1500");

		// Step 8: Click on Submit
		rateQuotePage.clickOnSubmit();
		String actualTxt = testUtil.getTextOfElement(driver.findElement(By.xpath("//lib-feedback//div/span")));
		Assert.assertEquals(actualTxt, expectedErrMsg);

	}

	/*
	 * Web - Quote History - Search by Date Range
	 */
	@Test(enabled = true, priority = 106)

	public void executeQZ_8569() throws Exception {

		ArrayList<String> quoteNumbers = null;
		int searchString = 0;
		String qDte = null;
		String query = null;

		// Step 1: Open My estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		// Step 2: Login as local user
		myEstesLoginPage.enterUserName(username2);
		testUtil.setHardWait(2000);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(5000);

		// Step 3: Navigate to Ship
		myEstesHomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		// Step 4: Click on Rate Quote un Ship
		myEstesHomePage.clickOnRateQoute();

		// Step 5: Click on Rate Quote History tab
		rateQuotePage.clickOnQouteHistoryTab();
		testUtil.setHardWait(3000);
		QuoteHistoryPage.verifyPageTitle();

		// Step 6: Click on Advanced search button
		QuoteHistoryPage.clickOnAdvancedSearch();
		String quoteDate = testUtil.addPastWeekDay();

		String[] qDate = quoteDate.split("/", 3);

		String qmth = qDate[0];
		qDte = qDate[1];

		qDte = qDte.trim();

		while (qDte.indexOf("0") == 0) {
			qDte = qDte.substring(1);
		}

		while (qmth.indexOf("0") == 0) {
			qmth = qmth.substring(1);
		}

		String newDate = qDate[0] + "/" + qDte + "/" + qDate[2];
		System.out.println("Date " + newDate);

		// Step 7: select from date calendar widget icon
		// Step 8: select from date via calendar widget
		QuoteHistoryPage.selectFromDateFromWidget(qDte);

		// Step 9: Select To date calendar widget icon
		// Step 10: Select to date via calendar widget
		QuoteHistoryPage.selectToDateFromWidget(qDte);

		// Verify selected date is populated in from date in MM/DD/YYY format
		// QuoteHistoryPage.verifySelectedDateISPopulate("fromDate", quoteDate);

		QuoteHistoryPage.verifySelectedDateISPopulate("fromDate", newDate);

		// Verify selected date is populated in to date in MM/DD/YYY format
		// QuoteHistoryPage.verifySelectedDateISPopulate("toDate", quoteDate);
		QuoteHistoryPage.verifySelectedDateISPopulate("toDate", newDate);

		QuoteHistoryPage.clickOnSearchButton();

		// Step 11: Enter acct number for national user
		quoteNumbers = QuoteHistoryPage.captureResultTable();
		searchString = quoteNumbers.size();

		// Step 12: Create search string based on search criteria
		query = "select GSRID, GSRTMST, GSRAPP, GSROZIP, GSROST, GSRDZIP, GSRDST,  GSQPRIC from FBFILES.GSC00P100 a left join FBFILES.GSC00P110 b on"
				+ " a.GSRID = b.GSQID where GSRQID =0 and GSQPRIC > 0.00 and GSRRNAM =  'SMOKELOCAL' and GSRACT = '7178618' and GSRTMST >= "
				+ "'" + qDate[2] + "-" + qmth + "-" + qDte + " 00:00:00.000000' and GSRTMST <= '" + qDate[2] + "-"
				+ qmth + "-" + qDte
				+ " 23:59:59.000000' and GSQSEL ='Y' and GSQDSP='S' Order by GSRTMST Desc FETCH FIRST " + searchString
				+ " ROWS ONLY";

		List<String> quoteNumbersFromDB = sqlDataList.getQuoteFromQuery(query);
		testUtil.setHardWait(2000);
		// Verify results with returned search results
		Assert.assertTrue((quoteNumbers.equals(quoteNumbersFromDB)));
	}

	/*
	 * Verify if keyed in value for 'Total Linear Feet needed for this shipment' is
	 * greater than the system total calculated length of the shipment, then
	 * VolumeverifyIfKeyedInValueForTotalLinearFeetNeededForThisShipmentisGreaterThanTheSystemTotalCalculatedLengthOfTheShipmentThenVolumeAndTruckload
	 * and Truckload charges are calculated based on the customer's entered length
	 */
	// THIS IS A DUPLICATE TEST

	/*
	 * @Test(enabled = true, priority = 118) // in progress
	 * 
	 * public void
	 * qz9750_verifyIfKeyedInValueForTotalLinearFeetNeededForThisShipmentisGreaterThanTheSystemTotalCalculatedLengthOfTheShipmentThenVolumeAndTruckload
	 * () throws Throwable {
	 * 
	 * myEstesHomePage.clickOnMyEstes(); myEstesHomePage.clickOnLogin();
	 * 
	 * myEstesLoginPage.enterUserName(username14);
	 * myEstesLoginPage.enterPassword(password14);
	 * 
	 * myEstesLoginPage.clickOnLoginButton();
	 * 
	 * myEstesHomePage.clickOnShipTab(); myEstesHomePage.clickOnRateQoute();
	 * 
	 * rateQuotePage.selectOrDeselectValumeAndTruckload();
	 * rateQuotePage.selectOrDeselectLessThanTruckload();
	 * 
	 * rateQuotePage.enterContactName("QZ-9750");
	 * rateQuotePage.enterYourEmail("EITQA@Estes-Express.com");
	 * 
	 * rateQuotePage.enterPhoneNo("8043531900");
	 * rateQuotePage.enterMyRole("Third Party");
	 * rateQuotePage.enterTerms("Prepaid"); rateQuotePage.selectTodayDate();
	 * 
	 * rateQuotePage.enterOriginZip("30307"); rateQuotePage.enterDesZip("30307");
	 * 
	 * rateQuotePage.enterPieces("7"); rateQuotePage.enterPieceType("PALLET");
	 * rateQuotePage.enterTotalWeight("6200"); rateQuotePage.enterLength("96");
	 * rateQuotePage.enterWidth("48"); rateQuotePage.enterHeight("48");
	 * rateQuotePage.enterDesc("QZ-9750, Customer linear feet");
	 * 
	 * rateQuotePage.clickOnAddCommodityButton();
	 * 
	 * rateQuotePage.enterPieces2("5"); rateQuotePage.enterPieceType2("SKID");
	 * rateQuotePage.enterTotalWeight2("3100"); rateQuotePage.enterLength2("120");
	 * rateQuotePage.enterWidth2("48"); rateQuotePage.enterHeight2("48");
	 * rateQuotePage.enterDesc2("QZ-9750 Customer Linear Fee");
	 * rateQuotePage.enterLinearFeet("37");
	 * rateQuotePage.clickOnArePalletStackable();
	 * 
	 * rateQuotePage.clickOnSubmitButton();
	 * 
	 * rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);
	 * 
	 * rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic"); String
	 * rateQuoteNum = rateQuotePage.recordRateQouteNumber(); String quoteNumber =
	 * rateQuoteNum.split(":")[1].trim();
	 * 
	 * driver.get(url2);
	 * 
	 * eNetLoginPage.enterUserID(username5);
	 * eNetLoginPage.enterUserPassword(password5);
	 * eNetLoginPage.clickOnLoginButton();
	 * 
	 * eNetHomePage.clickOnApplicationsTab();
	 * eNetApplicationsPage.clickOnQuoteAuditTrailLink();
	 * eNetQuoteAuditTrailPage.enterQuoteNumber(quoteNumber);
	 * eNetQuoteAuditTrailPage.clickOnSubmit();
	 * 
	 * eNetQuoteAuditTrailPage.
	 * verifyChangeDescriptionIsDisplayed("Chosen linear footage to rate = 37");
	 * eNetQuoteAuditTrailPage.
	 * verifyChangeDescriptionIsDisplayed("Linear Feet calculated as 37");
	 * 
	 * }
	 */

	/*
	 * eNet Rate Quote - Verify if keyed in value for 'Total Linear Feet needed for
	 * this shipment' is greater than the system total calculated length of the
	 * shipment, then Volume and Truckload charges are calculated based on the
	 * customer's entered length
	 */
	@Test(enabled = true, priority = 107)
	public void executeQZ_9751() {

		String linearFeet = "31";
		String changeDesc = "Chosen linear footage to rate = 31";

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click VTL Rate Quote link
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		// Step 5: Enter contact information
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("QZ-9751");
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber("C123455");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("QATest@estes-express.com");
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole("Shipper");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900 x3170");
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms("Pre-paid");

		// Step 6: Enter routing information
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("77007");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("23059");

		// Step 7: Enter scheduling information
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();

		// Step 8: Enter commodities in line1
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("3");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("PALLET");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("3000");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("60");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("48");
		eNetVTLRateQuotePage.enterDescription("QZ-9751, Customer Linear Feet");

		// Step 9: Enter commodities in line2
		eNetVTLRateQuotePage.enterVTLRateQuotePieces1("7");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType1("SKID");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight1("17000");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength1("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth1("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight1("48");
		eNetVTLRateQuotePage.enterDescription1("QZ-9751, Customer Linear Feet");

		// Step 10: Total Linear Feet
		eNetVTLRateQuotePage.enterTotalLinearFeet(linearFeet);

		// Step 11: Click on submit button
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		// Step 12: Verify Basic charges are calculated
		eNetVTLRateQuotePage.verifyChargesByServiceLevel("Volume and Truckload Basic");

		// Step 13: Click Volume Truckload Basic charges -> This step is included in
		// Step 12

		// Step 14: Record quote
		String quoteNo = eNetVTLRateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Step 15: Navigate to Application
		eNetHomePage.clickOnApplicationWithoutFrame();

		// Step 16: Click Quote Audit Trail
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Step 17: Enter Recorded quote#
		eNetQuoteAuditTrailPage.enterQuoteNumber(quoteNo);

		// Step 18: Click Submit
		eNetQuoteAuditTrailPage.clickOnSubmit();

		// Step 19: Verify Chosen linear footage to rate* value = 31
		eNetQuoteAuditTrailPage.verifyChangeDescriptionIsDisplayed(changeDesc);

		// Step 20: Verify *Linear Feet calculated as* value is less than 31
		eNetQuoteAuditTrailPage.verifyLinearFootageIsLessThan(linearFeet);

	}

	/*
	 * eNet Rate Quote - Verify if keyed in value for 'Total Linear Feet needed for
	 * this shipment' is equal or less than the system total calculated length of
	 * the shipment, then Volume and Truckload charges are calculated based on the
	 * system calculated length
	 */
	@Test(enabled = true, priority = 108)

	public void executeQZ_9747() throws InterruptedException {

		String linearFeet = "17";

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click VTL Rate Quote link
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		// Step 5: Enter contact information
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("QZ-9747");
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber("C123455");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("QATest@estes-express.com");
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole("Shipper");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900 x3170");
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms("Pre-paid");

		// Step 6: Enter routing information
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("77007");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("23059");

		// Step 7: Enter scheduling informatio
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();

		// Step 8: Enter commodities in line1
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("3");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("PALLET");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("3000");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("60");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("48");
		eNetVTLRateQuotePage.enterDescription("QZ-9747, system linear feet");

		// Step 9: Enter commodities in line2
		eNetVTLRateQuotePage.enterVTLRateQuotePieces1("7");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType1("SKID");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight1("17000");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength1("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth1("48");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight1("48");
		eNetVTLRateQuotePage.enterDescription1("QZ-9747, System Calculated Linear Feet");

		// Step 10: Total Linear Feet
		eNetVTLRateQuotePage.enterTotalLinearFeet(linearFeet);

		// Step 11: Click on submit button
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		// Step 12: Verify Basic charges are calculated
		eNetVTLRateQuotePage.verifyChargesByServiceLevel("Volume and Truckload Basic");

		// Step 13: Click on Volume Truckload basic charges -> This step is included in
		// step 12

		// Step 14: Record quote
		String quoteNo = eNetVTLRateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Step 15: Navigate to Application
		eNetHomePage.clickOnApplicationWithoutFrame();

		// Step 16: Click Quote Audit Trail
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Step 17: Enter Recorded quote#
		eNetQuoteAuditTrailPage.enterQuoteNumber(quoteNo);

		// Step 18: Click Submit
		eNetQuoteAuditTrailPage.clickOnSubmit();

		// Step 19: Verify Chosen linear footage to rate is greater than keyed in 17
		eNetQuoteAuditTrailPage.verifyChosenLinearFootageIsGreaterThan(linearFeet);

		// Step 20: Verify *Linear Feet calculated as* value is less than 17
		eNetQuoteAuditTrailPage.verifyLinearFootageIsGreaterThan(linearFeet);
	}

	/*
	 * V/TL Exception Q - Verify if keyed in value for 'Total Linear Feet needed for
	 * this shipment' is equal or less than the system total calculated length of
	 * the shipment, then Volume and Truckload charges are calculated based on the
	 * system calculated length
	 */

	@Test(enabled = true, priority = 109)
	public void executeQZ_9748() {

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click VTL Rate Quote link
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		// Step 5: Enter contact information
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName("QZ-9748");
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber("C123455");
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail("QATest@estes-express.com");
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole("Shipper");
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber("8043531900 x3170");
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms("Pre-paid");

		// Step 6: Enter routing information
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress("30307");
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress("23230");

		// Step 7: Enter scheduling information
		eNetVTLRateQuotePage.enterVTLRateQuotePickupDate();

		// Step 8: Enter commodities in line1
		eNetVTLRateQuotePage.enterVTLRateQuotePieces("20");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType("ROLL");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight("9500");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength("300");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth("24");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight("18");
		eNetVTLRateQuotePage.enterDescription("QZ-9748, System Calculated Linear Feet");

		// Step 9: Enter commodities in line2
		eNetVTLRateQuotePage.enterVTLRateQuotePieces1("10");
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType1("BOX");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight1("3000");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength1("24");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth1("12");
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight1("12");
		eNetVTLRateQuotePage.enterDescription1("QZ-9748, System Calculated Linear Feet");

		// Step 10: Click on Is freight stackable
		eNetVTLRateQuotePage.clickOnIsFreightStackable();

		// Step 11: Engter total linear feet
		eNetVTLRateQuotePage.enterTotalLinearFeet("12");

		// Step 12: Click on submit button
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		// Step 13: Verify Basic charges are calculated
		eNetVTLRateQuotePage.verifyChargesByServiceLevel("Volume and Truckload Basic");

		// Step 14: Click Basic charges link and Record quote#
		String quoteNo = eNetVTLRateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Step 15: Navigate to Application
		eNetHomePage.clickOnApplicationWithoutFrame();

		// Step 16: Click VTL Quote Exeption Queue Link
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();

		// Step 17: Search for recorded Quote#
		eNetVTLQouteExceptionQueuePage.enterQouteNumber(quoteNo);
		eNetVTLQouteExceptionQueuePage.clickOnSubmitButton();

		// Step 18: Validate no exception is displayed
		eNetVTLQouteExceptionQueuePage.verifyNoExceptionDisplayed(quoteNo);

		// Step 19: Select Quote# link
		eNetVTLQouteExceptionQueuePage.clickOnQuoteLink();

		// Step 20: Validate Linear feet = 26
		eNetVTLQouteExceptionQueuePage.validateLinearFeet("26");

		// Step 21: Enter total linear feet as 24
		eNetVTLQouteExceptionQueuePage.entertotalLinearFeet("25");

		// Step 22: Click on Recalculate button
		eNetVTLQouteExceptionQueuePage.clickOnRecalculateButton();

		// Step 23: Validate Linear feet = 26
		eNetVTLQouteExceptionQueuePage.validateLinearFeet("26");

		// Step 24: Enter total linear feet as 26
		eNetVTLQouteExceptionQueuePage.entertotalLinearFeet("26");

		// Step 25: Click on Recalculate button
		eNetVTLQouteExceptionQueuePage.clickOnRecalculateButton();

		// Step 26: Validate Linear Feet = 26
		eNetVTLQouteExceptionQueuePage.validateLinearFeet("26");

		// Step 27: Click on Applications
		eNetHomePage.clickOnApplicationWithoutFrame();

		// Step 28: Click on Quote Audit Trail link
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Step 29: Enter recorded quote#
		eNetQuoteAuditTrailPage.enterQuoteNumber(quoteNo);

		// Step 30: Click on Submit
		eNetQuoteAuditTrailPage.clickOnSubmit();

		// Step 31: Verify *Chosen linear footage to rate* value = 26
		eNetQuoteAuditTrailPage.verifyChangeDescriptionIsDisplayed("Chosen linear footage to rate = 26");

		// Step 32: Verify *Linear Feet calculated as* value = 26
		eNetQuoteAuditTrailPage.verifyChangeDescriptionIsDisplayed("Linear Feet calculated as 26");

	}

	@Test(enabled = true, priority = 110)

	public void executeQZ_9750() throws InterruptedException {

		// Launch My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		// Login to My Estes app
		myEstesLoginPage.enterUserName(username15);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Select *Volume and Truckload (incl. Guaranteed) deselect *Less Than
		// Truckload*
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// In Requester Info section, enter the following
		rateQuotePage.enterContactName("QZ-9750");
		rateQuotePage.enterYourEmail("EITQA@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Enter today's date pickup date
		rateQuotePage.selectTodayDate();

		// In Routing information, enter the following,
		rateQuotePage.enterOriginZip("30307");
		rateQuotePage.enterDesZip("60007");

		// In Commodity section row1, enter the following values,
		rateQuotePage.enterPieces("7");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("6200");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("QZ-9750 Customer linear feet");

		// Add commodity to enter commodity 2
		rateQuotePage.clickOnAddCommodityButton();

		// In commodity section row 2, Enter following values

		rateQuotePage.enterPieces2("5");
		rateQuotePage.enterPieceType2("SKID");
		rateQuotePage.enterTotalWeight2("3100");
		rateQuotePage.enterLength2("120");
		rateQuotePage.enterHeight2("48");
		rateQuotePage.enterWidth2("48");
		rateQuotePage.enterDesc2(" QZ-9750 Customer Linear Feet");

		// Select are pallets stackable? checked/yes
		rateQuotePage.clickOnArePalletStackable();

		// Enter linear feet
		rateQuotePage.enterTotalLinearFeet("37");
		// click on submit button
		rateQuotePage.clickOnSubmitButton();

		// Verify the basic charges are calculated for VTL Basic
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

		// Select get quote for VTL Basic and record the quote number
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		String quoteNumber = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Launch enet app
		driver.get(url2);

		// Login to enet app
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Click Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Click on quote audit trail link
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Enter the recorded quote number
		eNetQuoteAuditTrailPage.enterQuoteNo(quoteNumber);

		// click on submit button
		eNetQuoteAuditTrailPage.clickOnSubmitButton();

		// Validate Linear Feet = 37
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Chosen linear footage to rate = 37");

		eNetQuoteAuditTrailPage.verifyIsDisplayed("Linear Feet calculated as 29");

	}

	/*
	 * 
	 */
	// AS PER DEE, TESTS THAT VERIFY EMAIL IN THE ROUNDCUBE SHOULD BE TUNRED OFF
	// UNTIL FURTHER NOTICE
	@Test(enabled = false, priority = 111)

	public void executeQZ_3185() throws InterruptedException {

		String serviceLevel = "Guaranteed Volume and Truckload Standard";

		// Launch My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Login to My Estes app
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Select *Volume and Truckload (incl. Guaranteed) deselect *Less Than
		// Truckload*
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// In Requester Info section, enter the following
		rateQuotePage.enterContactName("QZ-3185");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterPhoneExtentionNo("3170");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();

		// In Routing information, enter the following,
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");

		// In Commodity section row1, enter the following values,
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("68");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("QZ-3185 Test");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton(serviceLevel);
		rateQuotePage.clickOnTermsAndConditionsFor(serviceLevel);
		rateQuotePage.clickOnResreveShipmentFor(serviceLevel);

		rateQuotePage.verifySuccessMessage();

		// The last 3 steps are to verify email in the RoundCube which we do not do
		// that.

	}

	/*
	 * My Estes Rate Quote -Verify if keyed in value for 'Total Linear Feet needed
	 * for this shipment' is equal or less than the system total calculated length
	 * of the shipment, then Volume and Truckload charges are calculated based on
	 * the system calculated length
	 */

	@Test(enabled = true, priority = 112)

	public void executeQZ_9746() throws InterruptedException {

		String keyedLinearFeet1 = "16";

		String keyedLinearFeet2 = "12";

		// Step-1 : Launch My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step-2 : Login to My Estes app
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();

		// Step-3 : Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Step-4 :Select *Volume and Truckload (incl. Guaranteed) deselect *Less Than
		// Truckload*
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Step-5 : In Requester Info section, enter the following
		rateQuotePage.enterContactName("QZ-9746");
		rateQuotePage.enterYourEmail("EITQA@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step-6 : Enter today's date pickup date
		rateQuotePage.selectTodayDate();

		// Step-7 : In Routing information, enter the following,
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.enterDesZip("90007");

		// Step-8 : In Commodity section row1, enter the following values,
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("5");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("5400");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("QZ-9746, System Calculated Linear Feet");

		// Add commodity to enter commodity 2
		rateQuotePage.clickOnAddCommodityButton();

		// Step-9 : In commodity section row 2, Enter following values
		rateQuotePage.enterClass2("55");
		rateQuotePage.enterPieces2("3");
		rateQuotePage.enterPieceType2("SKID");
		rateQuotePage.enterTotalWeight2("2700");
		rateQuotePage.enterLength2("48");
		rateQuotePage.enterHeight2("48");
		rateQuotePage.enterWidth2("60");
		rateQuotePage.enterDesc2("QZ-9746, System Calculated Linear Feet");

		// Step-10 : Select are pallets stackable? checked/yes
		rateQuotePage.clickOnArePalletStackable();

		// Step-11 : Enter linear feet as 16
		rateQuotePage.enterTotalLinearFeet(keyedLinearFeet1);

		// Step-12 : click on submit button
		rateQuotePage.clickOnSubmitButton();

		// Step-13 : Verify the basic charges are calculated for VTL Basic
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

		// Step-14 : Select get quote for VTL Basic record the quote number
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Step-15 : record the quote number and verify system linear feet value = keyed
		// in linear feet value(16)
		String quoteNumber = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
		rateQuotePage.verifyLinearFeet(keyedLinearFeet1);

		// Step-16 : click on Revise quote
		rateQuotePage.clickOnReviseQouteButtonBelowQouteOption();
		rateQuotePage.verifyRateQuotePageTitle();

		// Step-17 : Update the linear feet to 12
		rateQuotePage.enterTotalLinearFeet(keyedLinearFeet2);

		// Step-18 : Click on submit
		rateQuotePage.clickOnSubmitButton();

		// Step-19 : Verify the basic charges are calculated for VTL Basic
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

		// Step-20 : Select get quote for VTL Basic and record the quote number
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Step-21 : record the quote number and verify system linear feet value = keyed
		// in linear feet value(12)
		String quoteNumber2 = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		rateQuotePage.verifyLinearFeet2(keyedLinearFeet2);

		// Step-22 : Launch enet app
		driver.get(url2);

		// Step-23 : Login to enet app
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step-24 : Click Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Step-25 : Click on quote audit trail link
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Step-26 : Enter the recorded quote number
		eNetQuoteAuditTrailPage.enterQuoteNo(quoteNumber);

		// Step-27 : click on submit button
		eNetQuoteAuditTrailPage.clickOnSubmitButton();

		// Step-28 : Validate Linear Feet = 16
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Chosen linear footage to rate = 16");

		// Step-29 : Validate Linear Feet calculated as 16
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Linear Feet calculated as 16");

		// Step-30 : Enter the recorded quote number
		eNetQuoteAuditTrailPage.reEnterQuoteNo(quoteNumber2);

		// Step-31 : click on submit button
		eNetQuoteAuditTrailPage.clickOnSubmitButton();

		// Step-32 : Validate Linear Feet is > 12
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Chosen linear footage to rate = 16");

		// Step-33 : Validate Linear Feet calculated as >12
		eNetQuoteAuditTrailPage.verifyIsDisplayed("Linear Feet calculated as 16");

		// Step-34 : Click logout link
		eNetHomePage.clickOnLogout();

		// Step-35 : Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();

	}

	/*
	 * Undiscounted Freight Report
	 */

	@Test(enabled = true, priority = 113)
	public void executeQZ_5344() {

		String accountNum = "5018240";
		String email = "qatest@estes-express.com";
		String message = "Your request has been submitted.";

		driver.get(url2);

		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		testUtil.setHardWait(2000);
		eNetHomePage.clickOnAccountRecievable();
		eNetHomePage.clickOnUndiscountedFreightReport();
		eNetHomePage.enterAccountNumber(accountNum);
		eNetHomePage.enterEmail(email);
		eNetHomePage.clickOnSubmitButton();
		eNetHomePage.verifySuccessMessage(message);
		// step 8 is not automated as it deals with email verification

	}

	/*
	 * My Estes Rate Quote -Verify if keyed in value for 'Total Linear Feet needed
	 * for this shipment' is equal or less than the system total calculated length
	 * of the shipment, then Volume and Truckload charges are calculated based on
	 * the system calculated length
	 */

	@Test(enabled = true, priority = 114) // Review

	public void executeQZ_9752() throws InterruptedException {

		String keyedLinearFeet = "22";
		String Exception1 = "Chosen linear footage to rate = 23";
		String Exception2 = "Linear Feet calculated as 21";

		// Step-1 : Launch My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step-2 : Login to My Estes app
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();

		// Step-3 : Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		testUtil.setHardWait(500);
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(500);

		// Step-4 :Select *Volume and Truckload (incl. Guaranteed) deselect *Less Than
		// Truckload*
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Step-5 : In Requester Info section, enter the following
		rateQuotePage.enterContactName("QZ-9752");
		rateQuotePage.enterYourEmail("EITQA@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step-6 : Enter today's date pickup date
		rateQuotePage.selectTodayDate();

		// Step-7 : In Routing information, enter the following,
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");

		// Step-8 : In Commodity section row1, enter the following values,
		rateQuotePage.enterPieces("8");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("7600");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");
		rateQuotePage.enterDesc("QZ-9752, Customer Linear Feet");

		// Add commodity to enter commodity 2
		rateQuotePage.clickOnAddCommodityButton();

		// Step-9 : In commodity section row 2, Enter following values

		rateQuotePage.enterPieces2("20");
		rateQuotePage.enterPieceType2("BOX");
		rateQuotePage.enterTotalWeight2("5200");
		rateQuotePage.enterLength2("24");
		rateQuotePage.enterHeight2("36");
		rateQuotePage.enterWidth2("24");
		rateQuotePage.enterDesc2("QZ-9752, Customer Linear Feet");

		// Step-10 : Select are pallets stackable? checked/yes
		rateQuotePage.clickOnArePalletStackable();

		// Step-11 : Enter linear feet as 16
		rateQuotePage.enterTotalLinearFeet(keyedLinearFeet);

		// Step-12 : click on submit button
		rateQuotePage.clickOnSubmitButton();

		// Step-13 : Verify the Guaranteed Volume and Truckload Economy and Standard
		// charges are calculated

		// Step-14 : Verify the basic charges are calculated for VTL Basic
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", true);

		// Step-15 : Select get quote for VTL Basic record the quote number
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Step-16 : record the quote number
		String quoteNumber = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");

		// Step-17 : Launch enet app
		driver.get(url2);

		// Step-18 : Login to enet app
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step-19 : Click Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Step 20: Click onn VTL Quote Exception Queue Link
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();

		// Step-21 : enter the recorded quote number
		eNetVTLQouteExceptionQueuePage.enterQouteNumber(quoteNumber);

		eNetVTLQouteExceptionQueuePage.clickOnSubmitButton();

		// Step-22 : Verify under the *Exceptions* column, validate no exception is
		// displayed

		eNetVTLQouteExceptionQueuePage.verifyNoExceptionDisplayed(quoteNumber);

		// Step-23 : Select Quote# link and verify quote maintenance page

		eNetVTLQouteExceptionQueuePage.clickOnQuoteNumber(quoteNumber);

		volumeAndTruckloadQuoteMaintenance.verifyVTLQuoteMaintenancePage();

		// Step-24 : Validate Linear feet = 21

		volumeAndTruckloadQuoteMaintenance.verifyLinearFeet("21");

		// Step-25 : Enter total linear feet = 28

		volumeAndTruckloadQuoteMaintenance.enterTotalLinearFeet("23");

		// Step-26 : Click on create new quote button and record the new quote number

		volumeAndTruckloadQuoteMaintenance.clickOnCreateNewQuote();

		String quoteNumber2 = volumeAndTruckloadQuoteMaintenance.getQuoteNumber("");

		// Step-27 : Validate Linear feet = 27

		volumeAndTruckloadQuoteMaintenance.verifyLinearFeet("21");

		// Step-28 : Click on Applications

		eNetHomePage.clickOnApplicationWithoutFrame();

		// Step-29 : Click on quote audit trail link
		eNetApplicationsPage.clickOnQuoteAuditTrailLink();

		// Step-30 : Enter the recorded quote number2
		eNetQuoteAuditTrailPage.enterQuoteNo(quoteNumber2);

		// Step-31 : click on submit button
		eNetQuoteAuditTrailPage.clickOnSubmitButton();

		// Step-32 : Validate Linear Feet = 28
		eNetQuoteAuditTrailPage.verifyIsDisplayed(Exception1);

		// Step-33 : Validate Linear Feet calculated as 27
		eNetQuoteAuditTrailPage.verifyIsDisplayed(Exception2);

		// Step-34 : Click logout link
		eNetHomePage.clickOnLogout();

		// Step-35 : Confirm by clicking logout button
		eNetHomePage.clickOnLogoutButton();

	}

	/*
	 * Verify when Guaranteed Time Critical service level are requested, then, total
	 * linear feet are calculated based only on the provided dimensions, % of space
	 * used logic should not apply
	 */
	@Test(enabled = true, priority = 115)

	public void executeQZ_9845() throws Exception {

		String serviceLevel = "LTL Standard Transit";

		// Login to MyEstes as QARegress1
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Enter Rate Quote Details
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.enterContactName("RQE-165");
		rateQuotePage.enterYourEmail("eitqa@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23069");
		rateQuotePage.enterDesZip("60079");

		// Commodity 1
		rateQuotePage.enterClass("60");
		rateQuotePage.enterPieces("1");
		rateQuotePage.enterPieceType("CYL");
		rateQuotePage.enterTotalWeight("900");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("60");
		rateQuotePage.enterHeight("60");
		rateQuotePage.enterDesc("RQE-165, Total Linear Feet");

		// Commodity 2
		rateQuotePage.clickOnAddCommodityButton();
		rateQuotePage.enterClass2("60");
		rateQuotePage.enterPieces2("1");
		rateQuotePage.enterPieceType2("SKID");
		rateQuotePage.enterTotalWeight2("500");
		rateQuotePage.enterLength2("48");
		rateQuotePage.enterWidth2("48");
		rateQuotePage.enterHeight2("48");
		rateQuotePage.enterDesc2("RQE-165, Total Linear Feet");

		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton(serviceLevel);
		String quoteNumber = rateQuotePage.recordQuoteNumber(serviceLevel);
		System.out.println(quoteNumber);

		String query = "Select GSQID,GSQLNFT from FBFILES.GSC00P110 where GSQref =(select gsqref from fbfiles.gsc00p110 where gsqid = '"
				+ quoteNumber.trim() + "')";
		String quoteNum = quoteNumber.replaceFirst("L", "");
		String expVal[] = { "L" + quoteNum + " 12", "1" + quoteNum + " 12", "2" + quoteNum + " 12",
				"3" + quoteNum + " 12", "X" + quoteNum + " 12", "V" + quoteNum + " 10", "S" + quoteNum + " 10",
				"E" + quoteNum + " 10" };

		List<String> dbValue = sqlDataList.fetchAllRowDetailsFromEXLAQA(query, 1, 2);
		List<String> expValue = new ArrayList<>(Arrays.asList(expVal));
		dbValue.retainAll(expValue);
		Assert.assertTrue(dbValue.containsAll(expValue));
	}

	/*
	 * Rate Quote - Verify Contact Name, Phone Number, Extension, and Email Address
	 * are displayed on Quote Details
	 */

	@Test(enabled = true, priority = 116)

	public void executeQZ_3158() throws InterruptedException {

		String ctName1 = "QZ3158", ctPhoneNumber = "(804) 353-1900", ctEmail = "EITQA@Estes-Express.com";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login as testadmin
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3&4: Navigate to Ship -> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 5: From *Select Quote Type* section, check *Time Critical/Expedited*
		// check box.
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Step 6: Record Full Name, Email, Phone, and Extension data
		// Contact Name: QZ3158
		rateQuotePage.enterContactName(ctName1);
		// Email Address: eitqa@estes-express.com
		rateQuotePage.enterYourEmail(ctEmail);
		// Phone Number: 8043531900
		testUtil.setHardWait(1000);
		rateQuotePage.enterPhoneNo(ctPhoneNumber);
		testUtil.setHardWait(2000);
		// Extension: 1234567
		rateQuotePage.enterPhoneExtentionNo("1234567");
		// Role: Shipper
		rateQuotePage.enterMyRole("Shipper");
		// Terms: Pre-paid
		rateQuotePage.enterTerms("Prepaid");
		// Enter today's date pickup date
		rateQuotePage.selectTodayDate();
		// Origin: Zip=23233, Destination: Zip=30307
		rateQuotePage.enterOriginZip("23233");
		rateQuotePage.enterDesZip("30307");

		// Step 7: Enter commodities values
		// Class: 50
		rateQuotePage.enterClass("50");
		// Pieces: 12
		rateQuotePage.enterPieces("12");
		// Piece Type: Carton
		rateQuotePage.enterPieceType("CARTON");
		// Total Weight: *3700*
		rateQuotePage.enterTotalWeight("3700");
		// Length: 48
		rateQuotePage.enterLength("24");
		// Width:48
		rateQuotePage.enterWidth("12");
		// Height: 48
		rateQuotePage.enterHeight("18");
		// Description: Piece Type = Carton
		rateQuotePage.enterDesc("Piece Type = Carton");

		// Step 8: Click Submit
		rateQuotePage.clickOnSubmitButton();

		// Step 9: select *Get Quote #* for any service level
		rateQuotePage.clickOnGetQuoteButton();

		// Step 10: Record Quote Number
		rateQuotePage.recordRateQouteNumber();

		// Step 11: Review Name Data from Contact information
		rateQuotePage.verifyContactName(ctName1);

		// Step 12: Review Phone Data from Contact information
		rateQuotePage.verifyPhoneNumber(ctPhoneNumber);
		// "(804) 353-1900"
		// Step 13: Review Email Data from Contact information
		rateQuotePage.verifyEmailAddress(ctEmail);

		// Last step pertains to email validation which is yet to be automated

	}

	// Verifies the search by quote and search by Date range returns proper values.

	/*
	 * Web - Quote History - Verify Quote History Search functionality
	 */

	@Test(enabled = true, priority = 117)
	public void executeQZ_3109() throws Exception {

		// Step 1: Record Total Count # - covered in Step 15

		ArrayList<String> quoteNumbers = null;
		int searchString = 0;
		String qDte = null;
		String query = null;

		ArrayList<String> tableHeaders = new ArrayList<String>(
				Arrays.asList("Quote Number", "Quote Date", "Service Level", "Origin Zip", "Origin State",
						"Destination Zip", "Destination State", "Est. Charges", ""));

		// Step 2: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 3: Login as SmokeLocal
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Step 4: click the Request a Quote "Start Request" button
		myEstesHomePage.clickOnRequestAQuote();

		// Step 5: Verify title on page "Rate Quote"
		rateQuotePage.verifyRateQuotePageTitle();

		// Step 6: Verify tabs for Create RateQuote, Rate Quote History appears on page.
		rateQuotePage.verifyCreateRateQuoteTabDisplayed();
		rateQuotePage.verifyRateQuoteHistoryTabDisplayed();

		// Step 7: Click Rate Quote History tab
		rateQuotePage.clickOnQouteHistoryTab();

		// Step 8: Verify title "HISTORY" is displayed
		rateQuotePage.verifyHistoryTitleDisplayed();

		// Step 9: verify the column headings:"Quote Number","Quote Date","Service
		// Level","Origin Zip","Origin State","Destination Zip","Destination
		// State","Est. Charges"
		rateQuotePage.verifyQuoteHistoryTableHeader(tableHeaders);

		// Step 10: click on the Advanced Search Filter and verify sub title *Search
		// Options* is displayed
		rateQuotePage.clikOnAdvanceSearchButton();
		rateQuotePage.verifySearchOptionDisplayed();

		// Step 11: Verify calendar icon to the right of the box for "From Date" and "To
		// Date'
		rateQuotePage.verifyFromDateCalenderDisplayed();
		rateQuotePage.verifyToDateCalenderDisplayed();

		// Step 12: Verify the check-box and text appears "Show All Filters"
		rateQuotePage.verifyShowAllFilterCheckBoxDisplayed();
		rateQuotePage.verifyShowAllFilterTextDisplayed();

		// Step 13: Verify Buttons, "SEARCH" and "Clear" are on page.
		rateQuotePage.verifySearchButtonDisplayed();
		rateQuotePage.verifyClearButtonDisplayed();

		// Step 14: Based on the information from step 1 enter a *From* date and *To*
		// date and click *SEARCH*
		String quoteDate = testUtil.addPastWeekDay();
		String[] qDate = quoteDate.split("/", 3);
		String qmth = qDate[0];
		qDte = qDate[1];
		qDte = qDte.trim();
		while (qDte.indexOf("0") == 0) {
			qDte = qDte.substring(1);
		}
		while (qmth.indexOf("0") == 0) {
			qmth = qmth.substring(1);
		}
		String newDate = qDate[0] + "/" + qDte + "/" + qDate[2];
		System.out.println("Date " + newDate);
		testUtil.setHardWait(1000);
		QuoteHistoryPage.selectFromDateFromWidget(qDte);
		QuoteHistoryPage.selectToDateFromWidget(qDte);
		rateQuotePage.clikOnSearchButton();
		testUtil.setHardWait(2000);
		// Step 15: Verify the *Quote Count* displayed as 1-xxx of xxx agree with the
		// SQL results from step 1.
		quoteNumbers = QuoteHistoryPage.captureResultTable();
		searchString = quoteNumbers.size();
		query = "select GSRID, GSRTMST, GSRAPP, GSROZIP, GSROST, GSRDZIP, GSRDST,  GSQPRIC from FBFILES.GSC00P100 a left join FBFILES.GSC00P110 b on"
				+ " a.GSRID = b.GSQID where GSRQID =0 and GSQPRIC > 0.00 and GSRRNAM =  'SMOKELOCAL' and GSRACT = '7178618' and GSRTMST >= "
				+ "'" + qDate[2] + "-" + qmth + "-" + qDte + " 00:00:00.000000' and GSRTMST <= '" + qDate[2] + "-"
				+ qmth + "-" + qDte
				+ " 23:59:59.000000' and GSQSEL ='Y' and GSQDSP='S' Order by GSRTMST Desc FETCH FIRST " + searchString
				+ " ROWS ONLY";

		List<String> quoteNumbersFromDB = sqlDataList.getQuoteFromQuery(query);
		testUtil.setHardWait(2000);
		Assert.assertTrue((quoteNumbers.equals(quoteNumbersFromDB)));

		// Step 16: Click on the *Clear* button.
		rateQuotePage.clikOnAdvanceSearchButton();
		rateQuotePage.clikOnClearButton();

		// Step 17: Run query for quote number
		String query1 = "Select GSRID, GSRTMST, GSROCITY, GSROZIP, GSROST, GSRDZIP, GSRDST, GSQPRIC, GSRRNAM, GSRACT, GSQSEL, GSQDSP from FBFILES.GSC00P100 a left join FBFILES.GSC00P110 b on a.GSRID =b.GSQID where GSQPRIC > 0.00 and GSRRNAM = 'SMOKELOCAL' and GSRACT ='7178618' and GSRTMST >= '2020-8-25 00:00:00.000000' and GSQSEL = 'Y' AND GSQDSP = 'S' Order by GSRTMST desc";
		if (sqlDataList.validateValueFromDataBase(query1) == true) {
			List<String> fbDetails2 = sqlDataList.getFirstRowDetailsFromEXLAQA(query1);
			String quoteNum = fbDetails2.get(0);
			System.out.println(quoteNum);
			testUtil.setHardWait(1000);
			// Step 18: Using the data obtained in step 17, enter a Quote # and click
			// *SEARCH*
			rateQuotePage.enterQuoteNumber(quoteNum);
			rateQuotePage.clikOnSearchButton();

			// Step 19: Verify the data in the table.
			rateQuotePage.verifyTableResult();

			// Step 20: Click on the *RESET* button.
			rateQuotePage.clikOnAdvanceSearchButton();
			rateQuotePage.clikOnClearButton();

			// Step 21: Click Logoff
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogout();

			// Step 22: close the page
			// Covered in after test

			// Step 23: close the browser
			// Covered in after test

		}
	}

	/*
	 * Rate Quote - Verify when rates are calculated the user can view and print the
	 * quote details and the printed info are correct
	 */
	// THIS TEST RUNS LOCALLY AS IT DEALS WITH DOWNLOAD
	@Test(enabled = false, priority = 118)

	public void executeQZ_839() throws InterruptedException, IOException {

		// Step-1 : Launch My Estes app
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step-2 : Login to My Estes app
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		myEstesLoginPage.clickOnLoginButton();

		// Step-3 : Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Step-4 :Only *Less than Truckload (incl. Guaranteed)* default service level
		// is selected

		// Step-5 : In Requester Info section, enter the following
		rateQuotePage.enterAccountNumber("3878407");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Step-6 : Enter today's date pickup date
		// rateQuotePage.selectTodayDate();

		// Step-7 : In Routing information, enter the following,
		rateQuotePage.enterOriginZip("23059");
		rateQuotePage.enterDesZip("77071");

		// Step-8 : In Commodity section row1, enter the following values,
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("500");
		rateQuotePage.enterDesc("Testdata1");

		// Add commodity to enter commodity 2
		rateQuotePage.clickOnAddCommodityButton();

		// Step-9 : In commodity section row 2, Enter following values

		rateQuotePage.enterClass2("50");
		rateQuotePage.enterTotalWeight2("480");
		rateQuotePage.enterDesc2("Testdata2");
		// Step-10 : From the *Freight Information* section,Enter Full Value Coverage:

		rateQuotePage.enterFullValueCoverage("10700");

		rateQuotePage.selectOrDeselectInsideDelivery();

		rateQuotePage.clickOnViewAllAccessorials();

		rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Delivery) ");

		rateQuotePage.selectOrDeselectAccessorials("Unloading Services Requested By Consignee");

		// Step-12 : click on submit button
		rateQuotePage.clickOnSubmitButton();

		// Step 13: Get Quote for LTL Standard Transit
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Download PDF file
		rateQuotePage.clickOnViewQuoteButton();
		testUtil.setHardWait(3000);

		String fileName = "ESTES_Rate_Quote";
		String extNme = ".pdf";

		String downloadPath = TestUtil.getCurrentWorkingPath() + "/Downloads/";

		testUtil.verifyIsFileDownloaded(downloadPath, fileName);

		String fileFound = testUtil.verifyDownloadedFileName(downloadPath, extNme, "");
		System.out.println(fileFound);

		String PDFPath = TestUtil.getCurrentWorkingPath() + "\\Downloads\\" + fileFound + "";

		String text = " Service Level: LTL Standard Transit";

		File file = new File(PDFPath);

		FileInputStream fis = new FileInputStream(file);

		PDFParser parser = new PDFParser(fis);

		parser.parse();

		COSDocument cosDoc = parser.getDocument();

		PDDocument pdDoc = new PDDocument(cosDoc);

		PDFTextStripper strip = new PDFTextStripper();

		String data = strip.getText(pdDoc);

		System.out.println(data);

		Assert.assertTrue(data.contains(text));

		cosDoc.close();
		pdDoc.close();

		System.out.println("Text found on the pdf file");

		testUtil.deleteFilesFromFolder(downloadPath, fileFound);

	}

	/*
	 * Test passed on 8th Sep 2022
	 */
	/*
	 * Verify that when the rate quote is class rated and cube is less than 750 and
	 * linear feet is greater than 20 then the LTL charges are displayed
	 */

	@Test(enabled = true, priority = 119)

	public void executeQZ_10131() throws InterruptedException {

		String contactName = "RQE-227";
		String emailAdd = "QATEST@Estes-Express.com";
		String phoneNum = "8043531900";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.enterContactName(contactName);
		rateQuotePage.enterYourEmail(emailAdd);
		rateQuotePage.enterPhoneNo(phoneNum);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("28115");
		rateQuotePage.enterDesZip("45315");

		rateQuotePage.enterClass("65");
		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("7685");
		rateQuotePage.enterLength("60");
		rateQuotePage.enterWidth("49");
		rateQuotePage.enterHeight("60");
		rateQuotePage.enterDesc("Cube < 750 & Total Linear Feet >20");

		testUtil.setHardWait(2000);
		rateQuotePage.clickOnAddCommodityButton();

		rateQuotePage.enterClass2("65");
		rateQuotePage.enterPieces2("3");
		rateQuotePage.enterPieceType2("SKID");
		rateQuotePage.enterTotalWeight2("4168");
		rateQuotePage.enterLength2("60");
		rateQuotePage.enterWidth2("48");
		rateQuotePage.enterHeight2("48");
		rateQuotePage.enterDesc2("Cube < 750 & Total Linear Feet >20");

		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyRateQuoteResultPage();

		String rateQuoteNum = rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		System.out.println("The quote number is" + rateQuoteNum);

		String sqlQuerry = "Select GSQID, GSQTFC, GSQTPCS, GSQCUFT, GSQLNFT, GSQTNUM  from fbfiles.gsc00p110 where gsqid = '"
				+ rateQuoteNum + "' ";

		List<String> dataBaseValue = sqlDataList.getFirstRowDetailsFromEXLAQA(sqlQuerry);
		// verify the values in the database
		String GSQCUFT = dataBaseValue.get(3);
		System.out.println(GSQCUFT);
		int value = Integer.parseInt(GSQCUFT);
		Assert.assertTrue(value < 750);

		String GSQLNFT = dataBaseValue.get(4);
		System.out.println(GSQLNFT);
		int value2 = Integer.parseInt(GSQLNFT);
		Assert.assertTrue(value2 > 20);

		String GSQTNUM = dataBaseValue.get(5);
		System.out.println(GSQTNUM);
		Assert.assertEquals(GSQTNUM, "EXL");

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();
		myEstesLoginPage.verifyLoginPage();

	}

	/**
	 * this test is failing because basic charges are being displayed.
	 * 
	 * Verify that when the rate quote is CAP rated then the LTL charges are not
	 * displayed if total linear feet is greater than 20
	 */
	@Test(enabled = true, priority = 120)

	public void executeQZ_10128() throws InterruptedException {

		String contactName = "RQE-224";
		String emailAdd = "QATEST@Estes-Express.com";
		String phoneNum = "8043531900";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.enterContactName(contactName);
		rateQuotePage.enterYourEmail(emailAdd);
		rateQuotePage.enterPhoneNo(phoneNum);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("48455");
		rateQuotePage.enterDesZip("11717");

		rateQuotePage.enterClass("250");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("1400");
		rateQuotePage.enterLength("84");
		rateQuotePage.enterWidth("55");
		rateQuotePage.enterHeight("75");
		rateQuotePage.enterDesc("RQE-224, Total Linear Feet >20");

		testUtil.setHardWait(2000);

		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("LTL Standard Transit", false);
		testUtil.setHardWait(1000);
		rateQuotePage.verifyDisclaimerForLinearFootage();

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		testUtil.setHardWait(1000);

		String rateQuoteNum = rateQuotePage.recordQuoteNumber("Volume and Truckload Basic");
		System.out.println("The quote number is :" + rateQuoteNum);

		String quotNum = rateQuoteNum.replace("V", "L");

		String sqlQuerry = "Select GSQID, GSQTFC, GSQTPCS, GSQCUFT, GSQLNFT, GSQTNUM  from fbfiles.gsc00p110 where gsqid = '"
				+ quotNum + "' ";

		List<String> dataBaseValue = sqlDataList.getFirstRowDetailsFromEXLAQA(sqlQuerry);
		// verify the values in the database
		/*
		 * String GSQCUFT=dataBaseValue.get(3); System.out.println(GSQCUFT); int
		 * value=Integer.parseInt(GSQCUFT); Assert.assertTrue(value< 750);
		 */
		testUtil.setHardWait(3000);
		String GSQLNFT = dataBaseValue.get(4);
		System.out.println(GSQLNFT);
		int value2 = Integer.parseInt(GSQLNFT);
		Assert.assertTrue(value2 > 20);

		String GSQTNUM = dataBaseValue.get(5);
		System.out.println(GSQTNUM);
		Assert.assertEquals(GSQTNUM, "CAP");

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();
		myEstesLoginPage.verifyLoginPage();

	}

	/**
	 * This is AS400 test that uses Jagacy- it runs locally as it is inconsistent
	 **/
	// Moved from MyEstesAS400RegressionTest class
	/*
	 * Rate Quote - Time Critical - Verify selected Guaranteed LTL Standard Transit
	 * quotes are save to the GMS system in AS400 once the service level is selected
	 */

	@Test(enabled = false, priority = 121)

	public void executeQZ_3081() throws JagacyException, Exception {

		// Login to MyEstes as Test admin
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Ship--> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.verifyRateQuotePageTitle();
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Rate quote details
		rateQuotePage.enterContactName("qz-3081");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterYourEmail("eitqatest@estes-express.com");
		rateQuotePage.enterTerms("Collect");
		rateQuotePage.enterPhoneNo("8885551212");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("90007");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("32");
		rateQuotePage.enterDesc("This is a test");
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		rateQuotePage.clickOnGetQuoteButton("12PM");
		String quoteNum12PM = rateQuotePage.recordQuoteNumber("Gauranteed 12 PM");
		String quoteNum10AM = quoteNum12PM.replace("2", "3");

		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String searchJob = "CALL GMR000";
		String searchValue = "5";
		String type = "5";
		String name = "myJagacyVT";
		// String host="exlaqa";
		String terminal = "dec-vt220";

//		session = new SessionVt(name, "exlaqa", terminal);
//		// MySession session = new MySession();
//		session.open();

		// Start session
		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
		WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
		SubsetRequestServiceInfoScreen subsetRequestServiceInfoScreen = new SubsetRequestServiceInfoScreen(session);
		DisplayCommentsScreen displayCommentsScreen = new DisplayCommentsScreen(session);
		DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(session);
		// Step:8
		loginScreen.logon(userName, password);

		Thread.sleep(3000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();
		// Step:9
		ibmMainMenuScreen.enterValueToComandLineField(searchJob);
		goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();
		// Step:10
		goldMedalMainMenuScreen.enterSelectionValue(searchValue);
		testUtil.setHardWait(500);
		workWithRequestScreen.verifyWorkWithRequestInfoScreen();

		// Step:11
		testUtil.setHardWait(5000);
		workWithRequestScreen.enterF17Key();
		Thread.sleep(2000);
		subsetRequestServiceInfoScreen.verifySubsetRequestInfoScreen();
		// Step:12 ,13
		subsetRequestServiceInfoScreen.clearUserIdField2();

		workWithRequestScreen.verifyWorkWithRequestInfoScreen();

		// Step:14
		workWithRequestScreen.enterQuoteNumber(quoteNum12PM);

		// Step:15
		workWithRequestScreen.enterValueInOptField(type);
		testUtil.setHardWait(2000);
		displayRequestServiceInfoScreen.verifyDisplayRequestScreen();
		// Step:16
		displayRequestServiceInfoScreen.verifyCommodityDataIsDisplayed();
		// step:17
		displayRequestServiceInfoScreen.enterF20Key();
		displayCommentsScreen.verifyDisplayCommentsScreen();
		// Step:18

		displayCommentsScreen.verifyDataIsDisplayedInComments();
		// step:19
		displayCommentsScreen.enterF3Key();
		workWithRequestScreen.verifyWorkWithRequestInfoScreen();
		// Step:20

		testUtil.switchToWindow(0);
		testUtil.setHardWait(2000);

		rateEstimatePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");
		testUtil.setHardWait(2000);
		// Step:21
		testUtil.switchToWindow(1);
		testUtil.setHardWait(2000);
		workWithRequestScreen.enterQuoteNumberWithPrefix3(quoteNum10AM);
		testUtil.setHardWait(2000);
		// Step:22

		workWithRequestScreen.verify12PMQuoteIsNotDisplayed(quoteNum12PM);

		testUtil.switchToWindow(0);
		testUtil.setHardWait(2000);
		// Step:23
		rateQuotePage.clickOnGetQuoteButton("Guaranteed Exclusive Use");
		String quoteNumExclusive = rateQuotePage.recordQuoteNumber("Guaranteed Exlusive Use");

		testUtil.switchToWindow(1);
		testUtil.setHardWait(2000);
		// Step:24
		workWithRequestScreen.enterQuoteNumber(quoteNumExclusive);
		// Step:25
		workWithRequestScreen.verify10AMQuoteIsNotDisplayed(quoteNum10AM);

		if (session != null) {
			session.abort();
			session.close();

		}
	}

	/**
	 * This test is failing because ContacMe link is displayed instead of Get Quote
	 * Button
	 * 
	 * Verify that when a rate quote total volume linear feet is greater than 27
	 * then a Contact Me link is displayed for Guaranteed Volume and Truckload
	 * Standard and Economy service level
	 */

	@Test(enabled = true, priority = 122)

	public void executeQZ_10413() throws InterruptedException {

		// Step 1 & 2-Login to My Estes by enter credential and clicking on login
		System.out.println("THIS TEST IS FAILING BECAUSE CONTACT ME LINK IS DISPLAYED INSTEAD OF GET QUOTE");

		System.out.println("The URL is: " + driver.getCurrentUrl());
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();
		//testUtil.setHardWait(40000);

		// Step 3-From *My Estes* Home page, select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		// Step 4: Select Time Critical Expedited
//		testUtil.setHardWait(40000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		// Step 5: In the *Requester Information* section,enter or select the following
		// values:
		rateQuotePage.enterContactName("RQE-300");
		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 6: Select today's date
		rateQuotePage.selectTodayDate();
		// Step 7: Enter zip code
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("37010");
//		testUtil.setHardWait(9999999);
		// Step 8: in the commodities section, enter the following values
		rateQuotePage.enterClass("60");
		rateQuotePage.enterPieces("12");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("9800");
		rateQuotePage.enterLength("55");
		rateQuotePage.enterWidth("44");
		rateQuotePage.enterHeight("88");
		rateQuotePage.enterDesc("RQE-300 Total linear feet greater than 27");

		// Step 9: Click on Submit button
		rateQuotePage.clickOnSubmitButton();
		// Step 10: Verify Guaranteed volumne and Truckload economy
		rateQuotePage.verifyContactMeByServiceLevel("Guaranteed Volume and Truckload Economy");
		// Step 11: from the table results, verify the Guaranteed volume and track load
		// standard
		rateQuotePage.verifyContactMeByServiceLevel("Guaranteed Volume and Truckload Standard");
		// Step 12: Click on revise quote
		rateQuotePage.clickOnReviseQouteButton();
		// Step 13: From the commodites section updted the following data
		rateQuotePage.enterLength("54");
		// Step 14: click on submit request button
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(40000);
		// Step 15: From the result table, verify charges for following:
		rateQuotePage.getChargesByServiceLevel("Guaranteed Volume and Truckload Economy");
		// Step 16: From the result table, verify charges for following:
		rateQuotePage.getChargesByServiceLevel("Guaranteed Volume and Truckload Standard");
		// Step 17: From the result table, verify charges for following:
		rateQuotePage.clickOnGetQuoteButton("Guaranteed Volume and Truckload Economy");
		// Step 18:From the quote details section, record the quote number
		String recordQuoteNum = rateQuotePage.recordQuoteNum();

		// Step 19:From myEstes click on log out button
		myEstesHomePage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		// Step 20: click on Confirm button
		myEstesHomePage.clickOnConfirmButton();
	}

	/**
	 * This test passed on 9/8/22
	 * 
	 * Verify when Time Critical/Expedited service level & more than one commodity &
	 * 96 =< length < 144 inches for at least one commodity then Overlength Charge
	 * (8.00' to 11.99') is auto displayed & (Overlength Freight (8.00' to 11.99')
	 * is auto selected
	 */

	@Test(enabled = true, priority = 123)

	public void executeQZ_10793() throws InterruptedException {

		// Step 1 & 2-Login to My Estes by enter credential and clicking on login

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();
		//testUtil.setHardWait(40000);

		// Step 3-From *My Estes* Home page, select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		// Step 4: Select Time Critical Expedited
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		// Step 5: In the *Requester Information* section,enter or select the follow

		rateQuotePage.enterContactName("TBP-1518");
		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterPhoneExtentionNum("3170");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 6: Pickup Details section
		rateQuotePage.selectTodayDate();

		// Step 7: Enter zip code
		rateQuotePage.enterOriginZip("78045");
		rateQuotePage.enterDesZip("23230");
		// Step 8: in the commodities section, enter the following values
		rateQuotePage.enterClass("55");
		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("ROLL");
		rateQuotePage.enterTotalWeight("200");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("18");
		rateQuotePage.enterHeight("12");
		rateQuotePage.enterDesc("TBP-1518");

		// Step 8: in the commodities secion row 1, enter the following values
		rateQuotePage.clickOnAddCommodityButton();
		// Step 9:In the *Commodities* section row 2,enter or select the following
		// values:
		rateQuotePage.enterClass2("50");
		rateQuotePage.enterPieces2("1");
		rateQuotePage.enterPieceType2("ROLL");
		rateQuotePage.enterTotalWeight2("120");
		rateQuotePage.enterLength2("143");
		rateQuotePage.enterWidth2("12");
		rateQuotePage.enterHeight2("24");
		rateQuotePage.enterDesc2("TBP-1518");
		// Step 10: Freight information section selected as Yes
		rateQuotePage.clickOnArePalletStackable();
		// Step 12:Click on Submit Button
		rateQuotePage.clickOnSubmitButton();
		// Step 13: From the *Results* table, click the caret for *LTL Standard Transit*
		// service level
		// Note: Caret is not being displayed instead i coded to click on the LTL
		// Standard Transite direckly
		rateQuotePage.clickOnGetQuoteButton();
		testUtil.setHardWait(2000);
		// Step 15: Verify the charges
		String captureQuote = rateQuotePage.verifyOverLenghtChargeForLTLStandardTransite();
		logger.info(captureQuote);
		// Step 17: From the Quote Options section, click on Revise Quote
		rateQuotePage.clickOnReviseQouteButtonBlowQouteOption();
		// Step 18: Verify overlength checkbox is selected
		rateQuotePage.verifyOverlengthIsSelected();
		// Step 19: Verify overlength
		rateQuotePage.verifyOverlengthIsDisplayed();

		// Step 20:From myEstes click on log out button
		myEstesHomePage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		// Step 21: click on Confirm button
		myEstesHomePage.clickOnConfirmButton();

	}

	/**
	 * 
	 * eNet Verify when Time Critical Rate Quote and one commodity & length = 96
	 * inches then Overlength Charge (8.00' to 11.99') is systematically displayed
	 * as a charge item
	 */

	@Test(enabled = true, priority = 124)

	public void executeQZ_10794() throws InterruptedException {

		System.out.println("THIS TEST IS FAILING BECAUSE CONTACT ME LINK IS DISPLAYED INSTEAD OF CHARGES");
		// Step 1: Open the following link:
		driver.get(url2);
		// Step 2: Login to *eNet* application using the following credentials:
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		// Step 3: From *eNet* home page top navigation menu, click *Applications*
		eNetHomePage.clickOnApplicationsTab();
		// Step 4: From the *Applications* page,find the *Customer Service* applications
		// list, and click *Time Critical Rate Quote* link
		// eNetHomePage.clickOnCustomerServiceLink();
		eNetHomePage.clickOnTimeCriticalRateQuoteLink();
		// Step 5: From the *Time Critical Rate Quote* page, *Contact and Routing
		// Information* section,
		// enter/select the following data:

		eNetTimeCriticalRateQoutePage.enterFullName("TBP-1519");
		eNetTimeCriticalRateQoutePage.enterAccountNumber("C123455");
		eNetTimeCriticalRateQoutePage.enterEmail("QATest@Estes-Express.com");

		eNetTimeCriticalRateQoutePage.enterPhoneNumber("804 353-1900");

		eNetTimeCriticalRateQoutePage.selectRole("Shipper");
		eNetTimeCriticalRateQoutePage.selectTerm("Pre-paid");
		eNetTimeCriticalRateQoutePage.enterOriginZip("16159");
		eNetTimeCriticalRateQoutePage.enterDestinationZip("60607");
		// Step 6: From the *Scheduling* section, enter/select today's date (not a
		// weekend or an Estes holiday)
		eNetTimeCriticalRateQoutePage.setTodayDate();
		// Step 7: From the *Commodities* section,enter/select the following data:
		eNetTimeCriticalRateQoutePage.selectClass("100");
		eNetTimeCriticalRateQoutePage.enterPieces("2");
		eNetTimeCriticalRateQoutePage.enterPiecesType("ROLL");
		eNetTimeCriticalRateQoutePage.enterWeight("700");
		eNetTimeCriticalRateQoutePage.enterLength("96");
		eNetTimeCriticalRateQoutePage.enterWidth("24");
		eNetTimeCriticalRateQoutePage.enterHeight("24");
		eNetTimeCriticalRateQoutePage.enterDescription("RQE-298 Quote Expiration Date & Time");

		// Step 8: Note: no overlength charge is selected from the *Accessorials*
		// section

		// Step 9: Click submit

		eNetTimeCriticalRateQoutePage.clickOnSubmitButton();
		// Step 10: select:From the Quote selection page, select:
		eNetTimeCriticalRateQoutePage.verifyChargesByServiceLevelDisplays("Guaranteed LTL Standard Transit: 12PM");
		// Step 11: From the *Quote Details* page,note the following data:
		eNetTimeCriticalRateQoutePage.recordQuoteNumber();
		// Step 12:From the *Charge Items* section, verify *OVERLENGTH CHARGE 8.00' to
		// 11.99'* is displayed as a charge item
		eNetTimeCriticalRateQoutePage.verifyOverLengthChargeFor8();

		// Step 13: Click logout
		eNetTimeCriticalRateQoutePage.clickLogout();
		// Step 14: click Logout confirmation
		eNetTimeCriticalRateQoutePage.clickLogoutConfirmation();

	}

	/**
	 * This test passed on 6/29/22
	 * 
	 * Verify when a Volume shipment go through a series of breaks and relays, then,
	 * all applicable terminals premiums are separately added to the Additional
	 * Charges section & the total is displayed in the Total Calculation Charges
	 * section & added to final rate
	 */

	@Test(enabled = true, priority = 125)
	public void executeQZ_9844() throws Exception {

		// Step-1 : Per Nora Step 1 not needed for automation
		// Step 2: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 3: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName("QARegress1");
		myEstesLoginPage.enterPassword("QARegress1");
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 4: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 5: select *Volume and Truckload (incl. Guaranteed) and Time
		// Critical/Expedited*, in addition to the default selection *Less Than
		// Truckload*
		testUtil.setHardWait(1000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();

		// Step 6: In the *Requester Information* section,enter or select the follow

		rateQuotePage.enterContactName("RQE-215");
		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterPhoneExtentionNum("3170");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 7: In the *Pickup Details* section, enter or select the following
		// values:
		rateQuotePage.selectTodayDate();

		// Step 8: In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.selectOriginCountry("Canada");
		rateQuotePage.enterOriginZip("T8R1K7");
		rateQuotePage.enterDesZip("12065");
		// Step 9: In the *Commodities* section, row 1, enter or select the following
		// values:
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("6");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("2400");
		rateQuotePage.enterLength("52");
		rateQuotePage.enterWidth("44");
		rateQuotePage.enterHeight("88");
		rateQuotePage.enterDesc("Terminal Premiums Applied");

		// Step 10: Click on submit request
		rateQuotePage.clickOnSubmitButton();

		// Step-11 :From the *Results* table,verify Volume and Truckload Basic Charges
		// are not calculated, Contact me instead
		rateQuotePage.verifyContactMe("Volume and Truckload Basic", true);
		rateQuotePage.verifyBasicCharge("Volume and Truckload Basic", false);
		// Step-12 : Select *Contact Me* for Volume and Truckload Basic service level

		rateEstimatePage.clickOnContactMe("Volume and Truckload Basic");

		// Message display: Success:
		rateQuotePage.verifyMessForContMe();

		// Record quote created tim
		String timeval = testUtil.getCurrentTimeViaDate();
		System.out.println(timeval);

		// Step-13 : For Manual testing validation. Select *Get Quote#* for *LTL
		// Standard Transit* note the quote number and replace the prefix with V to get
		// the Volume and Truckload quote number

		// Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

		// Step 14: launch enet
		driver.get(url2);

		// Step 15: Login to eNet application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 16: Click on Applications Tab
		eNetHomePage.clickOnApplicationsTab();

		// Step 17: Click onn VTL Quote Exception Queue Link
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();

		testUtil.setHardWait(4000);
		// Step 18: Find the quote created above with the recorded time stamp
		String quoteNo = eNetVTLQouteExceptionQueuePage.getQuoteByTimeStamp(timeval);
		testUtil.setHardWait(3000);
		// Step 19: Verify the following exceptions are displayed:

		eNetVTLQouteExceptionQueuePage.verifyExceptionByQuote(quoteNo, "Agent Point");

		eNetVTLQouteExceptionQueuePage.verifyExceptionByQuote(quoteNo, "International Origin");

		// Step 20: Select Quote# link

		eNetVTLQouteExceptionQueuePage.clickOnQuoteNumber(quoteNo);

		// *Volume and Truckload Quote Maintenance* page displays
		volumeAndTruckloadQuoteMaintenance.verifyVTLQuoteMaintenancePage();

		// Step-21 :Validate Terminal Charge Applied
		volumeAndTruckloadQuoteMaintenance.validateTerminalChargesApplied();

		// From *eNet* home page top navigation menu,click *Log Out*
		eNetHomePage.clickOnLogout();

		// Confirm by clicking the *Logout* button
		eNetHomePage.clickOnLogoutButton();

	}

	/**
	 * @author coxda
	 * @throws Throwable
	 */

	/*
	 * Verify when Guaranteed Volume and Truckload Standard is rated & the shipment
	 * has one or more handling units between 20 & 27.99 feet long then 'Guaranteed
	 * Overlength 20-27.99 Feet' with Included charge is added as a charge item in
	 * the quote details section
	 */

	@Test(enabled = true, priority = 126)
	public void executeQZ_11545() throws Throwable {

		// Step 1: Open the following link:

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to My Estes application using the following credentials:
		myEstesLoginPage.enterUserName(username15);
		myEstesLoginPage.enterPassword(password15);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: From My Estes Home page,
		// select Ship from the menu, then select Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: In the Select Quote Type section, select Volume and Truckload (incl.
		// Guaranteed)
		rateQuotePage.selectOrDeselectValumeAndTruckload();

		// Step 5: Deselect/uncheck Less than Truckload (incl. Guaranteed

		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Step 6:In the Requester Information section,

		rateQuotePage.enterYourFullName("CUSTTEAM4-305");
		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Consignee");
		rateQuotePage.enterTerms("Collect"); // prefilled

		// Step 7: In the Pickup Details section,
		// enter or select the following value:
		// Pickup Date: Today's date
		rateQuotePage.enterPickupDate(testUtil.todaysDate());

		// Step 8: In the Routing Information section,
		// enter or select the following values:

		rateQuotePage.enterOriginZip("77007");
		rateQuotePage.enterDesZip("60045");

		// Step 9: In the Commodities section row 1,
		// enter or select the following values:

		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("ROLL");
		rateQuotePage.enterTotalWeight("250");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("10");
		rateQuotePage.enterHeight("10");
		rateQuotePage.enterDesc("CUSTTEAM4-305 GMOL28");
		rateQuotePage.clickAddCommodity();

		// Step 10: In the Commodities section row 2, enter or select the following
		// values:

		rateQuotePage.enterPieces2("2");
		rateQuotePage.enterPieceType2("ROLL");
		rateQuotePage.enterTotalWeight2("750");
		rateQuotePage.enterLength2("335");
		rateQuotePage.enterWidth2("8");
		rateQuotePage.enterHeight2("8");
		rateQuotePage.enterDesc2("CUSTTEAM4-305 GMOL28");

		// Step 11 Click Submit Request button
		rateQuotePage.clickOnSubmit();

		// Step 12: From the Results table,
		// select Get Quote # for Guaranteed Volume and Truckload Standard service level

		// Step 13 If the Quote Details section is not displayed,
		// click the caret
		rateQuotePage.clickGetQuoteOrClickCaret("Guaranteed Volume and Truckload Standard");

		// Step 14: From Quote Details,
		// record the Quote Number:
		// Quote Number
		String quoteNumber = rateQuotePage.recordQuoteNumber("Volume and Truckload Standard");

		// Step 15: From the Charge Items section:
		// validate OVERLENGTH CHARGE-20.00' - 27.9'' is displayed as a charge item
		rateQuotePage.validateChargeItem(quoteNumber, "OVERLENGTH CHARGE-20.00' - 27.9'");

		// Step 16: From the Charge Items section:
		// validate GUARANTEED OVERLENGTH 20-27.99 FEET is displayed as a charge item
		rateQuotePage.validateChargeItem(quoteNumber, "GUARANTEED OVERLENGTH 20-27.99 FEET");

		// Step 17: From the Charge column:
		// validate GUARANTEED OVERLENGTH 20-27.99 FEET charge displays Included
		// Verified:
		// GUARANTEED OVERLENGTH 20-27.99 FEET charge displays Included
		rateQuotePage.validateChargeItemIsIncluded(quoteNumber, "OVERLENGTH CHARGE-20.00' - 27.9'");
		rateQuotePage.validateChargeItemIsIncluded(quoteNumber, "GUARANTEED OVERLENGTH 20-27.99 FEET");

		// Step 18: From My Estes Home page, select My Estes from the menu then select
		// Logout
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();

		// Step 19 Logout by clicking the
		// Confirm button
		myEstesHomePage.clickOnConfirmButton();

		// Step 20 Close the Browser
		// Note: recommended to avoid automation false failures

	}

	// Work in progress!

	/*
	 * Test passed on 9/8/2022
	 */
	/**
	 * @author habibja- created on 1/20/2022
	 * @throws InterruptedException updated by Jeff on 1/25/2022
	 * 
	 */

	/*
	 * Verify when Guaranteed Volume and Truckload Standard service level is
	 * requested & the shipment has at least one handling unit equal to 28 feet long
	 * (336 inches) then Guaranteed Volume and Truckload Standard charges display
	 * Contact Me
	 */

	@Test(enabled = true, priority = 27)

	public void executeQZ_11547() throws InterruptedException {

		// Step 1 &2: Login to *My Estes* application using the following credentials:

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();
		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnRateQoute();

		// Step 4:In the *Select Quote Type* section, select *Volume and Truckload
		// (incl. Guaranteed)*
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		// Step 5:Deselect/uncheck *Less than Truckload (incl. Guaranteed*
		rateQuotePage.selectOrDeselectLessThanTruckload();
		// Step 6: In the *Requester Information* section, enter or select the following
		// values:

		rateQuotePage.enterYourFullName("CUSTTEAM4-377");

		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 7:In the *Pickup Details* section, enter or select the following value:
		// rateQuotePage.selectTodayDate(); ---commanded as it selects plus 2 date
		rateQuotePage.selectCurrentDate(); // --added newly

		// Step 8:In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("60045");

		// Step 9: In the *Commodities* section row 1,enter or select the following
		// values:

		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("ROLL");
		rateQuotePage.enterTotalWeight("250");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("12");

		rateQuotePage.enterHeight("12");

		rateQuotePage.enterDesc("CUSTTEAM4-377, Long28");

		// Step 10:
		rateQuotePage.clickOnAddCommodityButton();

		rateQuotePage.enterPieces2("7");

		rateQuotePage.enterPieceType2("ROLL");
		rateQuotePage.enterTotalWeight2("750");
		rateQuotePage.enterLength2("336");
		rateQuotePage.enterWidth2("8");

		rateQuotePage.enterHeight2("8");

		rateQuotePage.enterDesc2("CUSTTEAM4-377, Long28");

		// Step 11: From the *Freight Information* section,select:

		rateQuotePage.clickOnArePalletStackable();

		// Step 12: Click *Submit Request* button
		rateQuotePage.clickOnSubmitButton();
		// Step 13:From the *Results* table,select *Get Quote #* for *Volume and
		// Truckload Basic* service level

		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		testUtil.setHardWait(40000);

		// Step 14:From *Quote Details*,record the Quote Number:
		String quote = rateQuotePage.recordQuoteNumberFromResultTable();

		// Step 15:From the *Results* table, verify *Get Quote #* is not displayed for
		// *Guaranteed Volume and Truckload Standard* service level, *Contact Me*
		// linkinstead

		rateQuotePage.verifyGetQuoteButtonIsNotDisplayedOnTheResultsPage("Guaranteed Volume and Truckload Standard");
		// rateQuotePage.verifyContactMeByServiceLevel("Guaranteed Volume and Truckload
		// Standard");
		testUtil.setHardWait(1000);
		// Step 16: Click the Contact me like
		rateQuotePage.clickOnContactMe("Guaranteed Volume and Truckload Standard");

		// Validate toast message
		rateQuotePage.verifyMessageDisplayed();

		// Step 18: logout

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();

		// Step 19 Logout by clicking the
		// Confirm button
		myEstesHomePage.clickOnConfirmButton();

		// Step 20 Close the Browser
		// Note: recommended to avoid automation false failures

	}

	/**
	 * @author habibja- created on 1/19/2022
	 * 
	 *
	 */

	/**
	 * Verify when Guaranteed LTL Standard Transit 10AM is rated & the shipment has
	 * at least one handling unit between 8 & 11.99 feet long then 'Guaranteed
	 * Overlength 8-11.99 Feet' with a $50.00 charge is added as a charge item in
	 * the quote details section
	 */

	@Test(enabled = true, priority = 128)

	public void executeQZ_11546() throws InterruptedException {

		// Step 1&2: Login to myEstes application
		System.out.println("THIS TEST IS FAILING BECAUSE CONTACT ME LINK IS DISPLAYED INSTEAD OF GET QUOTE");

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnRateQoute();

		// Step 4:In the *Select Quote Type* section, select *Time Critical /Expedited*
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Step 5: Deselect/uncheck *Less than Truckload (incl. Guaranteed*Note: Less
		// than Truckload (incl. Guaranteed) is selected by default

		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Step 6:In the *Requester Information* section, enter or select the following
		// values:

		rateQuotePage.enterYourFullName("CUSTTEAM4-314");

		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Step 7: In the *Pickup Details* section, enter or select the following value:

		rateQuotePage.selectTodayDate();

		// Step 8: In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");

		// Step 9: In the *Commodities* section row 1,enter or select the following
		// values:

		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("2");
		rateQuotePage.enterPieceType("ROLL");
		rateQuotePage.enterTotalWeight("50");
		rateQuotePage.enterLength("1");
		rateQuotePage.enterWidth("10");
		rateQuotePage.enterHeight("10");

		rateQuotePage.enterDesc("CUSTTEAM4-314 GMOL12");
		// Step 10:In the *Commodities* section row 2, enter or select the following
		// values:

		rateQuotePage.clickOnAddCommodityButton();

		rateQuotePage.enterClass2("65");
		rateQuotePage.enterPieces2("1");

		rateQuotePage.enterPieceType2("ROLL");
		rateQuotePage.enterTotalWeight2("270");
		rateQuotePage.enterLength2("143");
		rateQuotePage.enterWidth2("4");

		rateQuotePage.enterHeight2("2");

		rateQuotePage.enterDesc2("CUSTTEAM4-314 GMOL12");
		// Step 11:From the *Freight Information* section,select:

		rateQuotePage.clickOnArePalletStackable();

		// Step 12:Click *Submit Request* button

		rateQuotePage.clickOnSubmitButton();

		// Step 13:From the *Results* table, select *Get Quote #* for *Guaranteed LTL
		// Standard Transit: 10AM* service level

		testUtil.setHardWait(40000);
		rateQuotePage.clickOnGetQuoteByServiceLevel("Guaranteed LTL Standard Transit: 10AM");
		testUtil.setHardWait(2000);
		// Step 14:If the *Quote Details* section is not displayed,click the caret
		rateQuotePage.clickOnCraretSymbolIfQuoteDetailsNotDisplayed();

		// Step 15:From *Quote Details*,record the Quote Number:

		String quoteNum = rateQuotePage.recordQuoteNumberFromResultTable();

		// Step 16: From the *Charge Items* section:validate *OVERLENGTH CHARGE 8.00' -
		// 11.99'* is displayed as a charge item

		rateQuotePage.verifyChargeItemsTable("OVERLENGTH CHARGE 8.00' - 11.99'", "$185.00");
		// Step 17: From the *Charge Items* section:validate *GUARANTEED OVERLENGTH
		// 8-11.9 FEET* is displayed as a charge item
		rateQuotePage.verifyChargeItemsTable("GUARANTEED OVERLENGTH 8-11.99 FEET", "$50.00");
		// Step 19:From *My Estes* Home page,select *My Estes* from the menu then select
		// *Logout*

//		myEstesHomePage.clickOnMyEstes();
		// myEstesHomePage.clickOnLogout();

		// Step 20: Logout by clicking the
		// Confirm button
//		myEstesHomePage.clickOnConfirmButton();

	}

	/**
	 * @author Daniel Cox- created on 1/20/2022
	 */

	@Test(enabled = true, priority = 129)

	public void executeQZ_9749() {

		// Step 1 - 11: Make a SOAP request and retrieve the
		// Volume and Truckload Basic Quote Number
		String endpointURL = "http://10.28.69.64:8316/tools/rating/ratequote/v4.0/services/RateQuoteService?WSDL";
		String soapAction = "http://ws.estesexpress.com/ratequote/getQuote";

		String payload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:rat=\"http://ws.estesexpress.com/ratequote\" xmlns:rat1=\"http://ws.estesexpress.com/schema/2019/01/ratequote\">\r\n"
				+ "   <soapenv:Header>\r\n" + "      <rat:auth>\r\n" + "         <rat:user>qaregress1</rat:user>\r\n"
				+ "         <rat:password>qaregress1</rat:password>\r\n" + "      </rat:auth>\r\n"
				+ "   </soapenv:Header>\r\n" + "   <soapenv:Body>\r\n" + "      <rat1:rateRequest>\r\n"
				+ "         <rat1:requestID>77</rat1:requestID>\r\n"
				+ "         <rat1:account>C123455</rat1:account>\r\n" + "         <rat1:originPoint>\r\n"
				+ "            <rat1:countryCode>US</rat1:countryCode>\r\n"
				+ "            <rat1:postalCode>23233</rat1:postalCode>\r\n" + "            <!--Optional:-->\r\n"
				+ "            <rat1:city>Richmond</rat1:city>\r\n" + "            <!--Optional:-->\r\n"
				+ "            <rat1:stateProvince>VA</rat1:stateProvince>\r\n" + "         </rat1:originPoint>\r\n"
				+ "         <rat1:destinationPoint>\r\n" + "            <rat1:countryCode>US</rat1:countryCode>\r\n"
				+ "            <rat1:postalCode>30307</rat1:postalCode>\r\n" + "            <!--Optional:-->\r\n"
				+ "            <rat1:city>ATLANTA</rat1:city>\r\n" + "            <!--Optional:-->\r\n"
				+ "            <rat1:stateProvince>GA</rat1:stateProvince>\r\n"
				+ "         </rat1:destinationPoint>\r\n" + "         <rat1:payor>T</rat1:payor>\r\n"
				+ "         <rat1:terms>P</rat1:terms>\r\n" + "         <!--Optional:-->\r\n"
				+ "         <rat1:pickup>\r\n" + "            <rat1:date>2020-05-22</rat1:date>\r\n"
				+ "            <!--Optional:-->\r\n" + "            <rat1:ready>08:30:00</rat1:ready>\r\n"
				+ "            <!--Optional:-->\r\n" + "            <rat1:close>14:45:00</rat1:close>\r\n"
				+ "         </rat1:pickup>\r\n" + "         <!--Optional:-->\r\n"
				+ "         <rat1:declaredValue>17500</rat1:declaredValue>\r\n" + "         <!--Optional:-->\r\n"
				+ "         <rat1:declaredValueWaived>N</rat1:declaredValueWaived>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <rat1:stackable>Y</rat1:stackable>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <rat1:linearFeet>21</rat1:linearFeet>\r\n"
				+ "         <!--You have a CHOICE of the next 2 items at this level-->\r\n"
				+ "         <rat1:fullCommodities>\r\n" + "            <!--1 to 99 repetitions:-->\r\n"
				+ "            <rat1:commodity>\r\n" + "               <!--Optional:-->\r\n"
				+ "               <rat1:class>60</rat1:class>\r\n"
				+ "               <rat1:weight>5000</rat1:weight>\r\n"
				+ "               <rat1:pieces>11</rat1:pieces>\r\n"
				+ "               <rat1:pieceType>PT</rat1:pieceType>\r\n" + "               <rat1:dimensions>\r\n"
				+ "                  <rat1:length>96</rat1:length>\r\n"
				+ "                  <rat1:width>48</rat1:width>\r\n"
				+ "                  <rat1:height>48</rat1:height>\r\n" + "               </rat1:dimensions>\r\n"
				+ "               <!--Optional:-->\r\n"
				+ "               <rat1:description>Rate Quote Enhancement Line 1</rat1:description>\r\n"
				+ "            </rat1:commodity>\r\n" + "         </rat1:fullCommodities>\r\n"
				+ "      </rat1:rateRequest>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		Response response = given().header("SOAPAction", soapAction).header("Content-Type", "text/xml").and()
				.body(payload).when().post(endpointURL).then().log().all().statusCode(200).extract().response();

		XmlPath xmlPath1 = new XmlPath(response.asString());
		String quoteNumber = xmlPath1.getString("Envelope.Body.rateQuote.quoteInfo.quote[1].quoteNumber");

		String letter = "V";
		// assertNotNull(quoteNumber.contains("Z"));
		Assert.assertEquals(quoteNumber.startsWith(letter), true);
		System.out.println("quoteNumber is: " + quoteNumber);

		// Step 12 - 13: Log into eNet
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 14 - 19: Verify info on Quote Audit Trail Page
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnQuoteAuditTrail();
		eNetQuoteAuditTrailPage.enterQuoteNo(quoteNumber);
		eNetQuoteAuditTrailPage.clickOnSubmit();
		eNetQuoteAuditTrailPage.verifyChangeDescriptionIsDisplayed("Chosen linear footage to rate = 22");
		eNetQuoteAuditTrailPage.verifyChangeDescriptionIsDisplayed("Linear Feet calculated as 22");
		eNetQuoteAuditTrailPage.clickHome();
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButton();
	}

	/*
	 * Passed on 9/8/2022
	 */
	/**
	 * @author habibja
	 * @throws InterruptedException
	 */

	/*
	 * Verify when Guaranteed Volume and Truckload Economy service level is
	 * requested & the shipment has at least one handling unit greater than 28 feet
	 * long (337 to 636) then Guaranteed Volume and Truckload Economy charges
	 * display Contact Me
	 */

	@Test(enabled = true, priority = 130)

	public void executeQZ_11550() throws InterruptedException {

		// Step 1&2: Login to myEstes application

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnRateQoute();

		// Step 4: In the *Select Quote Type* section, select *Volume and Truckload
		// (incl. Guaranteed)*

		rateQuotePage.selectOrDeselectValumeAndTruckload();

		testUtil.setHardWait(3000);
		// Step 5: Deselect/uncheck *Less than Truckload (incl. Guaranteed*Note: Less
		// than Truckload (incl. Guaranteed) is selected by default
		rateQuotePage.selectOrDeselectLessThanTruckload();

		// Step 6: In the *Requester Information* section, enter or select the following
		// values:
		rateQuotePage.enterYourFullName("CUSTTEAM4-305");

		rateQuotePage.enterYourEmail("QATest@estes-express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Consignee");
		rateQuotePage.enterTerms("Collect");
		// Step 7: In the *Pickup Details* section,enter or select the following value:

		// rateQuotePage.selectTodayDate(); --changed to below method
		rateQuotePage.selectCurrentDate(); // <<---

		// Step 8: In the *Routing Information* section, enter or select the following
		// values:

		rateQuotePage.enterOriginZip("60045");
		rateQuotePage.enterDesZip("30307");

		// Step 9: In the *Commodities* section row 1,enter or select the following
		// values:

		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("ROLL");
		rateQuotePage.enterTotalWeight("375");
		rateQuotePage.enterLength("96");
		rateQuotePage.enterWidth("10");
		rateQuotePage.enterHeight("10");
		rateQuotePage.enterDesc("CUSTTEAM4-378, Long28");

		// Step 10: In the *Commodities* section row 2,enter or select the following
		// values:

		rateQuotePage.clickOnAddCommodityButton();

		rateQuotePage.enterPieces2("5");
		rateQuotePage.enterPieceType2("ROLL");
		rateQuotePage.enterTotalWeight2("2750");
		rateQuotePage.enterLength2("337");
		rateQuotePage.enterWidth2("8");
		rateQuotePage.enterHeight2("8");
		rateQuotePage.enterDesc2("CUSTTEAM4-378, Long28");

		// Step 11:From the *Freight Information* section,select:
		rateQuotePage.clickOnArePalletStackable();

		// Step 12: Click *Submit Request* button
		rateQuotePage.clickOnSubmitButton();
		// Step 13: From the *Results* table, select *Get Quote #* for *Volume and
		// Truckload Basic* service level
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Step 14:If the *Quote Details* section is not displayed,click the caret

		// Step 15: From *Quote Details*,record the Quote Number:

		String recordQuote = rateQuotePage.recordQuoteNumberFromResultTable();

		// Step 16:From the *Results* table, verify *Get Quote #* is not displayed for
		// *Guaranteed Volume and Truckload Economy* service level, *Contact Me* link
		// instead
		rateQuotePage.verifyGetQuoteButtonIsNotDisplayedOnTheResultsPage("Guaranteed Volume and Truckload Economy");
		// Step :17: Click the *Contact Me* link
		rateQuotePage.clickOnContactMe("Guaranteed Volume and Truckload Economy");

		// Step 18: Validate toast message
		// rateQuotePage.verifyMessageDisplayed(); ---commanded -Msg in it doesn't suit
		// the test step
		rateQuotePage.verifyToastMessage(); // --newly added method

		// Step 19-21: log out of myEstes

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();

		// Step 20: Logout by clicking the
		// Confirm button
		myEstesHomePage.clickOnConfirmButton();
	}

}
