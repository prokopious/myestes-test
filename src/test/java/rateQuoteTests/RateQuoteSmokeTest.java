package rateQuoteTests;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

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
import eNetPages.ENetRateRetrieverPage;
import eNetPages.ENetTimeCriticalExceptionQueue;
import eNetPages.ENetTimeCriticalQuoteMaintenancePage;
import eNetPages.ENetTimeCriticalRateQuotePage;
import eNetPages.ENetTruckloadRateQuotePage;
import eNetPages.ENetVTLQouteExceptionQueuePage;
import eNetPages.ENetVTLRateQuotePage;
import eNetPages.ENetVTLTableMaintenancePage;
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

public class RateQuoteSmokeTest extends TestBase {

	private Logger logger = Logger.getLogger(RateQuoteSmokeTest.class);
	// TestListener testListener;

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
	SQLDataList sqlDataList = new SQLDataList();
	ENetTruckloadRateQuotePage eNetTruckloadRateQuotePage = new ENetTruckloadRateQuotePage();
	ENetTimeCriticalExceptionQueue eNetTimeCriticalExceptionQueue = new ENetTimeCriticalExceptionQueue();
	ENetTimeCriticalQuoteMaintenancePage eNetTimeCriticalQuoteMaintenancePage = new ENetTimeCriticalQuoteMaintenancePage();

	/******************************* TESTS *******************************/

	/**
	 * 
	 * 
	 * taking a long time for the quote to appear in the exception queue
	 * 
	 * Volume and Truckload Quote Maintenance - Verify when Contact me is selected
	 * for 'Volume and Truckload Basic' the quote is directed to the exception queue
	 * and selecting the Recalculate button will not generate a new quote.
	 * @throws Exception 
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_3263() throws Exception {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		vtlRateQuote.selectOrDeselectValumeAndTruckload();

		vtlRateQuote.enterContactName();
		vtlRateQuote.enterYourEmail("qatest@estes-express.com");
		vtlRateQuote.enterMyRole("Third Party");
		vtlRateQuote.enterPhoneNo("6452134488");
		vtlRateQuote.enterTerms("Prepaid");
		vtlRateQuote.selectOriginCountry("United States");
		vtlRateQuote.enterOriginZip("16159");
		vtlRateQuote.selectDestinationCountry("United States");
		vtlRateQuote.enterDesZip("30307");
		vtlRateQuote.selectTodayDate();
		vtlRateQuote.enterClass("50");
		vtlRateQuote.enterPieces("4");
		vtlRateQuote.enterPieceType("PALLET");
		vtlRateQuote.enterTotalWeight("9700");
		vtlRateQuote.enterLength("48");
		vtlRateQuote.enterWidth("48");
		vtlRateQuote.enterHeight("24");
		testUtil.setHardWait(1000);

		vtlRateQuote.clickOnViewAllAccessorials();
		testUtil.setHardWait(2000);
		vtlRateQuote.selectOrDeselectAccessorialsList("Stop-off Services");

		vtlRateQuote.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		vtlRateQuote.clickOnConctactMeLinkOnResultTable("Volume and Truckload Basic");
		// String qouteNum = vtlRateQuote.recordRateQouteNumber();
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		String qouteNum = rateQuotePage.recordQuoteNumber("LTL Standard Transit");

		testUtil.setHardWait(1000);
		// Navigate to eNet application:

		// driver.navigate().to(url2);
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();
		testUtil.setHardWait(2000);
		// String qNum = qouteNum.substring(20, 26);

		String qNum = qouteNum.replace("L", "V");
		eNetVTLQouteExceptionQueuePage.enterQouteNumber(qNum);

		eNetVTLQouteExceptionQueuePage.clickSubmitAndClickQuoteNumLink(qNum, 10);
		testUtil.setHardWait(1000);
		//eNetVTLQouteExceptionQueuePage.clickOnQuoteLink(qNum);
		eNetVTLTableMaintenancePage.verifyQuoteNumberDisplay();
		eNetVTLTableMaintenancePage.enterComments("test");
		eNetVTLTableMaintenancePage.clickOnRecalculateButton();
		// eNetVTLTableMaintenancePage.verifyRecordAdded("Quote: " + "V" + qNum);
		eNetVTLTableMaintenancePage.verifyRecordAdded("Quote: " + qNum);
	}

	/**
	 * 
	 * Passed locally on 6/21/22
	 * 
	 * Volume and Truckload Rate Quote - Verify Volume and Truckload Basic quote ID
	 * prefix is V.
	 */

	@Test(enabled = true, priority = 2)

	public void executeQZ_3275() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		testUtil.setHardWait(500);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		vtlRateQuote.selectOrDeselectValumeAndTruckload();
		vtlRateQuote.selectOrDeselectLessThanTruckload();
		vtlRateQuote.enterYourFullName("QA Tester");
		vtlRateQuote.enterYourEmail("eitqa@estes-express.com");
		vtlRateQuote.enterMyRole("Third Party");
		vtlRateQuote.enterTerms("Prepaid");
		vtlRateQuote.enterPhoneNo("6452134488");
		vtlRateQuote.selectTodayDate();
		vtlRateQuote.selectOriginCountry("United States");
		vtlRateQuote.enterOriginZip("16159");
		vtlRateQuote.selectDestinationCountry("United States");
		vtlRateQuote.enterDesZip("30307");
		vtlRateQuote.enterPieces("2");
		vtlRateQuote.enterPieceType("PALLET");
		vtlRateQuote.enterTotalWeight("7500");
		vtlRateQuote.enterLength("48");
		vtlRateQuote.enterWidth("48");
		vtlRateQuote.enterHeight("48");
		rateQuotePage.enterDesc("Smoke test package");
		// vtlRateQuote.clikOnSubmitButton();
		vtlRateQuote.clickOnSubmitButton();
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");
		testUtil.setHardWait(2000);
		vtlRateQuote.verifyVTLQNumberPrefixIs("V");

	}

	/**
	 * This test passed on 6/21/22
	 * 
	 * LTL Rate Quote - Verify Guaranteed LTL Standard Transit: 10AM quote ID prefix
	 * is 3
	 */

	@Test(enabled = true, priority = 3) // to be reviewed.

	public void executeQZ_5536() throws Throwable {

		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogin();

		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("4977");
		rateQuotePage.clickOnSubmitButton();

		ltlRateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		ltlRateRequestPage.verifyQuoteNumberPrefixISL();
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 10AM ");
		ltlRateRequestPage.verifyQuoteNumberPrefixChagedTo3("3");

	}

	/**
	 * This test is failing due to rate quote history taking a very long time to be found. Turned off
	 * 
	 * This test passed on 6/21/22. Initially it was failing due to data sent to
	 * Rate Quote history would take over five minutes. Had to increased the wait to
	 * 5 minutes.
	 * 
	 * Rate Quote- Verify when Guaranteed LTL Standard Transit 12PM charges are
	 * selected only that quote is saved to quote History table
	 * @throws Exception 
	 */

	@Test(enabled = true, priority = 4)

	public void executeQZ_5540() throws Exception {

		String serviceLevel = "Guaranteed LTL Standard Transit: 12PM";

		// Launch My Estes app
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		// Login to My Estes app
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();

		// Navigate to Ship -> Rate Quote
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(3000);

		// Select *TimeCriticalExpedited
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// In Requester Info section, enter the following
		rateQuotePage.enterContactName();
		rateQuotePage.enterYourEmail("EITQATest@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");

		// Enter today's date pickup date
//		rateQuotePage.selectTodayDate();commented --> its selecting today + 2 days
		rateQuotePage.selectCurrentDate();

		// In Routing information, enter the following,
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.enterDesZip("30307");

		// In Commodity section row1, enter the following values,
		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("3");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("4700");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("48");

		// click on submit button
		rateQuotePage.clickOnSubmitButton();

		// Get total estimated charges for 12PM
		// String totalEstCharges = rateQuotePage.getChargesByServiceLevel("12PM");

		testUtil.setHardWait(1000);
		// Select a service level and record the quote number.

		rateQuotePage.clickOnGetQuoteButton(serviceLevel);
		testUtil.setHardWait(2000);
		String quoteNum12PM = rateQuotePage.recordQuoteNumber("Guaranteed LTL Standard Transit: 12PM");

		// Validate the ratequote prefix is 2 for 12PM quote
		rateQuotePage.verifyQuoteNumberPrefix("Guaranteed LTL Standard Transit: 12PM", "2");
		// this step is not in the test step

		String charges=rateQuotePage.getChargesByServiceLevel("Guaranteed LTL Standard Transit: 12PM");
		testUtil.setHardWait(1000);

		// From the 'Rate Quotes' screen, Click *Rate Quote History*
		rateQuotePage.clickOnQouteHistoryTab();

		// Validate the *Quote Number* captured in the previous step is displayed in
		// history

		//QuoteHistoryPage.verifyQuoteExistence(quoteNum12PM, true);
		QuoteHistoryPage.refreshAndValidateQuoteNum(quoteNum12PM, 25);
		
		// Validate the *EST. Charges* captured in the previous step is displayed in
		// history
		
		QuoteHistoryPage.verifyQuoteHistoryTableEstimatedCharges(quoteNum12PM, charges);
		testUtil.setHardWait(1000);
		//	QuoteHistoryPage.verifyEstCharges("$750", true);//--> changed the Est charges to below line  
		//QuoteHistoryPage.verifyEstCharges("$1,303.57", true);

		// Verify the other service level for the same quote are not saved to *History*
		// The quotes with different prefix

        String quoteNum10AM = quoteNum12PM.replace("2", "3");
    QuoteHistoryPage.verifyQuoteNumAbsentFromHistoryTable(quoteNum10AM);
        
        String quoteNum5PM = quoteNum12PM.replace("2", "1");
    QuoteHistoryPage.verifyQuoteNumAbsentFromHistoryTable(quoteNum5PM);
        
        String quoteNumLTL = quoteNum12PM.replace("2", "L");
    QuoteHistoryPage.verifyQuoteNumAbsentFromHistoryTable(quoteNumLTL);
        
        String quoteNumVolEconomy = quoteNum12PM.replace("2", "E");
    QuoteHistoryPage.verifyQuoteNumAbsentFromHistoryTable(quoteNumVolEconomy);

        String quoteNumVolStandard = quoteNum12PM.replace("2", "S");
    QuoteHistoryPage.verifyQuoteNumAbsentFromHistoryTable(quoteNumVolStandard);

        String quoteNumExlUse = quoteNum12PM.replace("2", "X");
    QuoteHistoryPage.verifyQuoteNumAbsentFromHistoryTable(quoteNumExlUse);


	}

	/**
	 * 
	 * This test passed on 7/26/22
	 * 
	 * Verify that when the rate quote is class rated and cube is greater than 750
	 * and linear feet is greater than 20 then the LTL charges are not displayed
	 */

	@Test(enabled = true, priority = 5)

	public void executeQZ_10129() throws Exception {

		String contactName = "RQE-225";
		String emilAdd = "QATEST@Estes-Express.com";
		String phoneNum = "8043531900";

		// step 1:login to the MyEstes Application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(3000);
		myEstesLoginPage.enterUserName(username14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(3000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.enterContactName(contactName);
		rateQuotePage.enterYourEmail(emilAdd);
		rateQuotePage.enterPhoneNo(phoneNum);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("23058");

		rateQuotePage.enterClass("50");
		rateQuotePage.enterPieces("9");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("19120");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("45");
		rateQuotePage.enterHeight("70");
		rateQuotePage.enterDesc("Cube > 750 & Total Linear Feet >20");

		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyCreateRateQuoteTabDisplayed();
		rateQuotePage.verifyRateQuoteResultPage();
		rateQuotePage.verifyBasicCharge(" LTL Standard Transit", false);
		testUtil.setHardWait(3000);
		rateQuotePage.verifyDisclaimerForLinearFootage();
		testUtil.setHardWait(2000);
		// rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// String quoteNum = rateQuotePage.recordQuoteNumber("Volume and Truckload
		// Basic");
		rateQuotePage.clickOnGetQuoteByServiceLevel("Volume and Truckload Basic");
		testUtil.setHardWait(3000);
		String quoteNum = rateQuotePage.recordQuoteNumberFromResultTable("Volume and Truckload Basic");

		String quoteNumber = quoteNum.replace("V", "L");
		testUtil.setHardWait(3000);
		String sqlQuery = "select GSQID, GSQTFC, GSQTPCS, GSQCUFT, GSQLNFT, GSQTNUM  from fbfiles.gsc00p110 where gsqid  = '"
				+ quoteNumber + "' ";

		try {
			List<String> dbValue2 = sqlDataList.getFirstRowDetailsFromEXLAQA(sqlQuery);
			testUtil.setHardWait(7000);
			String GSQCUFT = dbValue2.get(3);
			testUtil.setHardWait(2000);
			System.out.println(GSQCUFT);
			testUtil.setHardWait(3000);
			int value1 = Integer.parseInt(GSQCUFT);
			testUtil.setHardWait(5000);
			Assert.assertTrue(value1 > 750);

			String GSQLNFT = dbValue2.get(4);
			System.out.println(GSQLNFT);
			int value2 = Integer.parseInt(GSQLNFT);

			Assert.assertTrue(value2 > 20);

			String GSQTNUM = dbValue2.get(5);
			System.out.println(GSQTNUM);
			Assert.assertEquals(GSQTNUM, "EXL");

		} catch (Exception e) {
			e.printStackTrace();

		}

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();

	}

	/**
	 * 
	 * This test pased on 6/21/22
	 * 
	 * Verify that when the rate quote is class rated and cube is greater than 750
	 * and linear feet is less than 20 then the LTL charges are displayed
	 * 
	 */

	@Test(enabled = true, priority = 6)

	public void executeQZ_10130() throws InterruptedException {

		String contactName = "RQE-226";
		String emailAdd = "QATEST@Estes-Express.com";
		String phoneNum = "8043531900";
		ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
		// step 1:login to the MyEstes Application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(3000);
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		rateQuotePage.selectOrDeselectValumeAndTruckload();
		rateQuotePage.enterContactName(contactName);
		rateQuotePage.enterYourEmail(emailAdd);
		rateQuotePage.enterPhoneNo(phoneNum);
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectTodayDate();
		rateQuotePage.enterOriginZip("78045");
		rateQuotePage.enterDesZip("54409");

		rateQuotePage.enterClass("125");
		rateQuotePage.enterPieces("5");
		rateQuotePage.enterPieceType("PALLET");
		rateQuotePage.enterTotalWeight("3265");
		rateQuotePage.enterLength("54");
		rateQuotePage.enterWidth("44");
		rateQuotePage.enterHeight("76");
		rateQuotePage.enterDesc("Cube > 750 & Total Linear Feet <20");

		testUtil.setHardWait(2000);
		rateQuotePage.clickOnAddCommodityButton();

		rateQuotePage.enterClass2("175");
		rateQuotePage.enterPieces2("2");
		rateQuotePage.enterPieceType2("SKID");
		rateQuotePage.enterTotalWeight2("1444");
		rateQuotePage.enterLength2("54");
		rateQuotePage.enterWidth2("44");
		rateQuotePage.enterHeight2("88");
		rateQuotePage.enterDesc2(" Cube > 750 & Total Linear Feet <20");
		rateQuotePage.clickOnSubmitButton();

		rateQuotePage.verifyBasicCharge("LTL Standard Transit", true);

		rateQuotePage.verifyTableResult();// check again

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		testUtil.setHardWait(3000);
		String quoteNum = rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		testUtil.setHardWait(3000);
		System.out.println("The recorded quote number is" + quoteNum);

		String sqlQuery = "Select GSQID, GSQTFC, GSQTPCS, GSQCUFT, GSQLNFT, GSQTNUM  from fbfiles.gsc00p110 where gsqid = '"
				+ quoteNum + "'";

		List<String> dataBaseValue = sqlDataList.getFirstRowDetailsFromEXLAQA(sqlQuery);

		// verify values int he database

		String GSQCUFT = dataBaseValue.get(3);
		System.out.println(GSQCUFT);
		int value1 = Integer.parseInt(GSQCUFT);
		Assert.assertTrue(value1 > 750);

		String GSQLNFT = dataBaseValue.get(4);
		System.out.println(GSQLNFT);
		int value2 = Integer.parseInt(GSQLNFT);
		Assert.assertTrue(value2 < 20);

		String GSQTNUM = dataBaseValue.get(5);
		System.out.println(GSQTNUM);
		Assert.assertEquals(GSQTNUM, "EXL");

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();
		myEstesLoginPage.verifyLoginPage();

	}

	/**
	 * 
	 * this test passedon 7/26/22 after updating the residential delivery xpath
	 * 
	 * Verify when LTL rate estimate and Residential Delivery & Overlength
	 * (16.00'-19.99') are selected and the total weight of the shipment is greater
	 * than 500 lbs then the additional charge per shipment is $115.00 (current
	 * HDOL3 value)
	 */

	@Test(enabled = true, priority = 7)

	public void executeQZ_10512() throws InterruptedException {

		String orverlenghtVal = "Overlength Freight";
		String residentialDeliverVal = "Residential Delivery";
		String homeDelivery = "HOME DELV.";

		// Step 2:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		// Step3:In the *Requester Information* section, enter:
		ltlRateEstimatePage.enterContactName("RQE-373");
		// Step 4:In the *Routing Information* section, enter or select the following
		// values:
		ltlRateEstimatePage.enterOrginZip("87002");
		ltlRateEstimatePage.enterDestinationZip("54409");
		// Step 5: In the *Commodities* section row 1,enter or select the following
		// values:
		ltlRateEstimatePage.selectClass("50");
		ltlRateEstimatePage.enterTotalWeight("300");
		ltlRateEstimatePage.enterDesc("RQE-373 HDOL3");
		ltlRateEstimatePage.clickOnAddCommodity();
		// Step 6:In the *Commodities* section row 2, enter or select the following
		// values:

		ltlRateEstimatePage.selectClass1("55");
		testUtil.setHardWait(2000);
		ltlRateEstimatePage.enterTotalWeight1("201");
		ltlRateEstimatePage.enterDesc1("RQE-373 HDOL3");

		// Step 7: From accessorials, select Overlenght Delivery and resedential
		// delivery
		ltlRateEstimatePage.clickOnOverlengthFreight();
		ltlRateEstimatePage.clickOnResidentialDelivery();
		// Step 8: click on sumit request button
		ltlRateEstimatePage.clickOnSubmitButton();
		// step 9: From the *Results* table, click the caret for *LTL Standard Transit*
		// service level
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		// Step 10: From Rate Datails, record the quote number
		String rateQuoteNum = rateQuotePage.recordRateQouteNumber();
		testUtil.setHardWait(1000);

		// step 11: Validate *Residential Delivery'

		boolean expectedVal1 = rateQuotePage.verifyOverLengthChargesExistence(residentialDeliverVal);
		Assert.assertTrue(expectedVal1);
		// step 12:validate *Overlength Freight

		boolean expectedVal = rateQuotePage.verifyOverLengthChargesExistence(orverlenghtVal);
		Assert.assertTrue(expectedVal);
		// step 13:Validate Home Delv
		boolean expectedVal2 = rateQuotePage.verifyOverLengthChargesExistence(homeDelivery);
		Assert.assertTrue(expectedVal2);
		// step 14: Validate charege itam value for Home Deli
		rateQuotePage.verifyChargeItemsTable("HOME DELV. EXTRA LABOR MT 500 >12'", "$115.00");
		logger.info("The charges for Home Delv-Extra Lobor Mt is verified");
		// the following steps are in the test case but are not applicable as there is
		// no login
		// in the first place to logout at the end.
		/*
		 * myEstesHomePage.clickOnMyEstes(); myEstesHomePage.clickOnLogout();
		 */

	}

	/**
	 * 
	 * Passed on 9/28/22 after updating verify charge item method.
	 * 
	 * Verify when LTL quote type and Residential Delivery & Overlength
	 * (16.00'-19.99') are selected and the total weight of the shipment is less
	 * than 500 lbs then the additional charge per shipment is $55.00 (current HDOL1
	 * value)
	 */

	@Test(enabled = true, priority = 8)
	public void executeQZ_10511() throws InterruptedException {

		String ressidentialDel = "Residential Delivery";
		// String overlenghtFrt="Overlengh Freight";
		String homeDel = "HOME DELV.";
		// step 1:login to the MyEstes Application
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);

		myEstesLoginPage.clickOnLoginButton();
		// Step 3 From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		// Step 4:Select Quote type: this is selected by defualt
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		// Step 5 - 9: Enter information in the requester information section
		rateQuotePage.enterMyRole("Third Party");
		testUtil.setHardWait(1000);
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.enterOriginZip("78045");
		rateQuotePage.enterDesZip("54409");
		rateQuotePage.enterClass("125");
		rateQuotePage.clickAddCommodity();
		rateQuotePage.enterTotalWeight("326");
		rateQuotePage.enterClass2("100");
		rateQuotePage.enterTotalWeight2("144");
		rateQuotePage.enterDesc("RQE-378 HDOL1");
		rateQuotePage.enterDesc2("RQE-378 HDOL1");
		
	    rateQuotePage.selectOrDeselectAccessorialsList("Residential Delivery");
        rateQuotePage.selectOrDeselectAccessorialsList("Overlength Freight (16.00' to 19.99')");

		// Step 10: Click on submit button
		//rateQuotePage.clickOnSubmit();
        rateQuotePage.clikOnSubmitButton();
		// Step 11: Select LTL Standard Transit
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		// Step 12: Record the quote number
		String quoteNumber = rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		// Step 13: Verify the charge items for residential delivery
		 // Step 13: Verify the charge items for residential delivery
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Residential Delivery", "$");
        // Step 14: Verify the charge items for overlength
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Overlength Freight (16.00' to 19.99')", "$");
        // step 15: verify charge for home devl.
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","HOME DELV. EXTRA LABOR LT 500 LBS.", "$55.00");
        testUtil.setHardWait(500);
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();

	}

	/**
	 * This test passed on 9/27/22
	 * 
	 * Verify when Guaranteed Exclusive Use service level quote is rated/calculated
	 * then the line item charges for commodity total, specific accessorials, and
	 * the Total Estimated Freight Charges dollar amount are correct
	 */

	@Test(enabled = true, priority = 9)

	public void executeQZ_11461() throws InterruptedException {

		// Step 1 &2: Open the following link and Login to *My Estes* application using
		// the following credentials:

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		// Step 4: In the *Select Quote Type* section, only Less than Truckload (incl.
		// Guaranteed) default option is selected
		// rateQuotePage.selectOrDeselectTimeCriticalExpedited();

		// Step 5: From the *Select Quote Type* section *Standard Options*, select:
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		// Step 6: In the *Requester Information* section, enter or select the following
		// values:

		rateQuotePage.enterContactName("ENHNC-695");
		rateQuotePage.enterYourEmail("QATest@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterPhoneExtentionNum("3170");
		rateQuotePage.enterMyRole("Consignee");
		rateQuotePage.enterTerms("Collect");
		// Step 7: In the *Pickup Details* section, select:Note: should not be a holiday
		// or a weekend day
		rateQuotePage.selectTodayDate();
		// Step 8: In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("48455");
		rateQuotePage.enterDesZip("94107");
		// Step 9:In the *Commodities* section row 1,enter or select the following
		// values:
		rateQuotePage.enterClass("60");
		rateQuotePage.enterPieces("20");
		rateQuotePage.enterPieceType("CASE");
		rateQuotePage.enterTotalWeight("1000");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("24");
		rateQuotePage.enterHeight("36");
		rateQuotePage.enterDesc(" Validating Charges 1");

		// Step 10:Click the *Add Commodity* button
		rateQuotePage.clickOnAddCommodityButton();
		// Step 11: In the *Commodities* section row 2,enter or select the following
		// values:

		rateQuotePage.enterClass2("55");
		rateQuotePage.enterPieces2("7");
		rateQuotePage.enterPieceType2("ROLL");
		rateQuotePage.enterTotalWeight2("2700");
		rateQuotePage.enterLength2("60");
		rateQuotePage.enterWidth2("24");
		rateQuotePage.enterHeight2("24");
		rateQuotePage.enterDesc2("Validating Charges 2");

		// Step 12: From the *Accessorials* section,select:
		rateQuotePage.selectOrDeselectAccessorialsList("Inside Delivery");
		rateQuotePage.selectOrDeselectAccessorialsList("Lift-Gate Service (Delivery)");
		rateQuotePage.selectOrDeselectAccessorialsList("Notify Request");
		// this step is not in the test but added for automation purposes
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectAccessorialsList("Unloading Services Requested By Consignee");

		// step 13:From the *Freight Information* section,select:
		rateQuotePage.clickOnArePalletStackable();

		// Step 14: Click *Submit Request* button
		rateQuotePage.clickOnSubmitButton();

		// step 15: From the *Results* table, select *Get Quote #* for *Guaranteed
		// Exclusive Use* service level

		rateQuotePage.clickOnGetQuoteByServiceLevel("Guaranteed Exclusive Use");
		// rateQuotePage.clickOnGetQuoteButton("Guaranteed Exclusive Use");
		// step 16: From the *Quote Details* section,record the Quote Number:
		testUtil.setHardWait(3000);
		String quoteNum = rateQuotePage.recordQuoteNumber("Guaranteed Exclusive Use");
		// String quoteNum=rateQuotePage.recordQuoteNumber("Guaranteed Exclusive Use");
		testUtil.setHardWait(3000);

		// JavascriptExecutor js= (JavascriptExecutor) driver;
		// js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		// js.executeScript("scroll(0,700)");
		// Step 19: From the *Charge Items* section, validate *Commodity Total* Charges
		// = $19320.00

		rateQuotePage.verifyChargeItemByServiceLevel("Guaranteed Exclusive Use", "Commodity Total", "$19,320.00");
		testUtil.setHardWait(1000);
		// Step 20: Validate *Lift-Gate Service (Delivery)* Charges = $275

		   rateQuotePage.verifyChargeItemByServiceLevel("Guaranteed Exclusive Use", "Lift-Gate Service (Delivery)", "$275.00");
		testUtil.setHardWait(1000);
		// Step 21: Validate *Inside Delivery* Charges = $225.00

		rateQuotePage.verifyChargeItemByServiceLevel("Guaranteed Exclusive Use", "Inside Delivery", "$225.00");
		testUtil.setHardWait(1000);
		// Step 22: Validate *Total Estimated Freight* Charges = $19,820.00

		  rateQuotePage.verifyChargeItemByServiceLevel("Guaranteed Exclusive Use", "Total Estimated Freight Charges", "$19,820.00");

	}

	/**
	 * ACCORDING TO JIRA, THIS TEST HAS <AUTOMATE_ME> LABEL. commented jira awaiting Nora/Dee response
	 * 
	 * 
	 * This test fails because the overlengh charges were different. DIMs Verify
	 * when Residential Delivery and a shipment has one or more handling units
	 * exceeding 96 inches in length and weight per handling unit is less than 500
	 * lbs then the additional charge per shipment is $55.00 (current HDOL1 value)
	 */

	@Test(enabled = true, priority = 10)

	public void executeQZ_10506() throws InterruptedException {

		// Step 1 & 2: Enter user and password and click on login button.

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		// Step 4: In the *Select Quote Type* section, select *Time Critical /Expedited*
		// Note: keep Less than Truckload (incl. Guaranteed) default selected
		rateQuotePage.selectOrDeselectTimeCriticalExpedited();
		// Step 5:In the *Requester Information* section,enter or select the following
		// values:

		rateQuotePage.enterContactName("ENHNC-339");
		rateQuotePage.enterYourEmail("QATest@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 6: In the *Pickup Details* section,enter or select the following value:
		rateQuotePage.selectTodayDate();

		// Step 7: In the *Routing Information* section, enter or select the following
		// values:

		rateQuotePage.enterOriginZip("23230");
		rateQuotePage.enterDesZip("30307");

		// Step 8: In the *Commodities* section row 1, enter or select the following
		// values:

		rateQuotePage.enterClass("60");
		rateQuotePage.enterPieces("6");
		rateQuotePage.enterPieceType("CARTON");
		rateQuotePage.enterTotalWeight("500");
		rateQuotePage.enterLength("240");
		rateQuotePage.enterWidth("18");
		rateQuotePage.enterHeight("24");
		rateQuotePage.enterDesc("RQE-339 HDOL1");

		// Step 9:In the *Commodities* section row 2, enter or select the following
		// values:

		// this step is not on the test step

		rateQuotePage.clickAddCommodity();

		rateQuotePage.enterClass2("100");
		rateQuotePage.enterPieces2("3");
		rateQuotePage.enterPieceType2("PALLET");
		rateQuotePage.enterTotalWeight2("3800");
		rateQuotePage.enterLength2("144");
		rateQuotePage.enterWidth2("48");
		rateQuotePage.enterHeight2("24");
		rateQuotePage.enterDesc2("RQE-339 HDOL1");
		// Step 10: From the *Accessorials* section,select:

		rateQuotePage.selectOrDeselectAccessorialsList("Residential Delivery");
		// Step 11: Click *Submit Request* button

		rateQuotePage.selectSubmitButton();

		// Step 12:From the *Results* table, click the caret for *LTL Standard Transit*
		// service level
		rateQuotePage.clickOnGetQuoteButton();
		testUtil.setHardWait(2000);

		// Step 13: From *Quote Details*,record the Quote Number:
		String quoteNum = rateQuotePage.recordRateQouteNumber();

		// Step 14: From the *Charge Items* section: validate *Residential Delivery'* is
		// displayed as a charge item
		rateQuotePage.verifyChargeItemsTable("Residential Delivery", "$");
		// Step 15: From the *Charge Items* section:validate *OVERLENGTH CHARGE-20.00' -
		// 27.9'* is displayed as a charge item
		rateQuotePage.verifyChargeItemsTable("OVERLENGTH CHARGE-20.00' - 27.9'", "$");
		// Step 16: From the *Charge Items* section:validate *Home Delv. Extra Labor LT
		//step 17:From  the *Charge* column:validate a *$55.00* charge is displayed  for  *Home Delv. Extra Labor LT 500 Lbs* charge item
		//combined step 16 &17 into one line for displaying $55.00
		// 500 Lbs* is displayed as a charge item
		rateQuotePage.verifyChargeItemsTable("HOME DELV. EXTRA LABOR LT 500 LBS.", "$55.00");

		
		testUtil.setHardWait(1000);
		// Step 18 -20: logout
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();

	}

	/**
	 * 
	 * This test passed on 8/4/22
	 * 
	 * @author habibja
	 * @throws InterruptedException
	 *
	 *                              For charges item, the only needed charges is
	 *                              Overlenght 8-11:9 to be varified
	 *
	 *                              Verify when Overlength (8.00'-11.99') is
	 *                              selected & Guaranteed LTL Standard Transit 5PM
	 *                              is rated then 'Guaranteed Overlength 8-11.99
	 *                              Feet' with a $50.00 charge is added as a charge
	 *                              item in the quote details section
	 * 
	 * @throws InterruptedException
	 */

	@Test(enabled = true, priority = 11)

	public void executeQZ_11544() throws InterruptedException {

		// Step 1 & 2: Enter user and password and click on login button.

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// Step 4:In the *Select Quote Type* section, only Less than Truckload (incl.
		// Guaranteed) default option is selected (per default)

		// Step 5:In the *Requester Information* section, enter or select the following
		// values:
		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");

		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("78045");
		rateQuotePage.enterDesZip("77007");
		// Step 7: In the *Commodities* section row 1,enter or select the following
		// values:

		rateQuotePage.enterClass("55");
		rateQuotePage.enterTotalWeight("326");
		rateQuotePage.enterDesc("CUSTTEAM4-296 GMOL12");
		// Step 8: In the *Commodities* section row 2, enter or select the following
		// values:

		rateQuotePage.clickAddCommodity();

		rateQuotePage.enterClass2("100");
		rateQuotePage.enterTotalWeight2("144");
		rateQuotePage.enterDesc2("CUSTTEAM4-296 GMOL12");
		// Step 9: From the *Accessorials* section,select:
		rateQuotePage.selectOrDeselectAccessorialsList("Residential Delivery");
		rateQuotePage.selectOrDeselectAccessorialsList("Overlength (8.00'-11.99')");
		// Step 10: Click *Submit Request* button
		rateQuotePage.clickOnSubmitButton();
		// Step 11: From the *Results* table, select *Get Quote* for *Guaranteed LTL
		// Standard Transit: 5PM* service level
		rateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 5PM");
		// Step12:
		// Step 13: From *Quote Details*,record the Quote Number:
		testUtil.setHardWait(1000);
		String quoteNum = rateQuotePage.recordQuoteNumberFromResultTable(" Guaranteed LTL Standard Transit: 5PM");

		// Step 14: From the *Charge Items* section:validate *Residential Delivery'* is
		// displayed as a charge item
		rateQuotePage.verifyChargeItemsTable1("Residential Delivery", "$ ");

		// Step 15: From the *Charge Items* section: validate *Overlength Freight (8.00'
		// to 11.99')* is displayed as a charge item
		rateQuotePage.verifyChargeItemsTable1("Overlenght(8.00'- 11.99'", "$185.00");

		// Step 16: From the *Charge Items* section: validate *HOME DELV. EXTRA LABOR LT
		// 500 LBS.* is displayed as a charge item
		rateQuotePage.verifyChargeItemsTable1("HOME DELV. EXTRA LABOR LT 500 LBS", "$55.00");
		// Step 17: From the *Charge Items* section: validate *GUARANTEED OVERLENGTH
		// 8-11.9 FEET* is displayed as a charge item
		// Note: the only charges that needs tobe validated- according to JIRA
		rateQuotePage.verifyChargeItemsTable1("GUARANTEED OVERLENGTH 8-11.9 FEET", "$50.00");
		// Step 18: From the *Charge* column: validate a *$50.00* charge is displayed
		// for *GUARANTEED OVERLENGTH 8-11.9 FEET*
		// Step 19: Logout by clicking the *Confirm* button
		testUtil.setHardWait(500);
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogout();
		myEstesHomePage.clickOnConfirmButton();

	}

	/**
	 * *****ACORDING TO TEST CASE THE CHARGES SHOULD BE $1752.12. I updated to the
	 * new amount but need Dee and Nora confirmation
	 * 
	 * Verify when the Volume and Truckload Basic service level quote is
	 * rated/calculated then the line item charges for accessorials and surcharges
	 * show Included (no $ values) and the Total Estimated Freight Charges dollar
	 * amount is correct
	 */

	@Test(enabled = true, priority = 12)
	public void executeQZ_11460() throws InterruptedException {

		// Step 1 & 2: Enter user and password and click on login button.

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		// Step 4: In the *Select Quote Type* section, only Less than Truckload (incl.
		// Guaranteed) default option is selected

		// Step 5: From the *Select Quote Type* section *Standard Options*, de-select:

		rateQuotePage.selectOrDeselectValumeAndTruckload();
		// Step 6: From the *Select Quote Type* section *Standard Options*, select:
		rateQuotePage.selectOrDeselectLessThanTruckload();
		// Step 7: In the *Requester Information* section, enter or select the following
		// values:
		rateQuotePage.enterContactName("ENHNC-694");
		rateQuotePage.enterYourEmail("QATest@Estes-Express.com");
		rateQuotePage.enterPhoneNo("8043531900");
		rateQuotePage.enterPhoneExtentionNum("3170");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		// Step 8: In the *Pickup Details* section, select:Note: should not be a holiday
		// or a weekend day
		rateQuotePage.selectTodayDate();

		// Step 9: In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("48455");
		rateQuotePage.enterDesZip("11717");
		// Step 10: In the *Commodities* section row 1,enter or select the following
		// values:

		rateQuotePage.enterPieces("4");
		rateQuotePage.enterPieceType("SKID");
		rateQuotePage.enterTotalWeight("2970");
		rateQuotePage.enterLength("48");
		rateQuotePage.enterWidth("48");
		rateQuotePage.enterHeight("36");
		rateQuotePage.enterDesc("Validating Charges 1");
		// Step 11: click the Add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// Step 12: In the *Commodities* section row 2,enter or select the following
		// values:

		rateQuotePage.enterPieces2("7");
		rateQuotePage.enterPieceType2("PALLET");
		rateQuotePage.enterTotalWeight2("5000");
		rateQuotePage.enterLength2("48");
		rateQuotePage.enterWidth2("48");
		rateQuotePage.enterHeight2("48");
		rateQuotePage.enterDesc2("Validating Charges 2");

		// Step 13:From the *Accessorials* section,select:

		// This step is not on the test step but added to make more accessorial visible
		rateQuotePage.selectOrDeselectAccessorialsList("Lift-Gate Service (Delivery)");
		rateQuotePage.selectOrDeselectAccessorialsList("Notify Request");
		// this step is not in the test but added for automation purposes
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectAccessorialsList("Unloading Services Requested By Consignee");

		// Step 14:Click *Submit Request* button
		rateQuotePage.clickOnSubmitButton();
		// Step 15: From the *Results* table, select *Get Quote #* for *Volume and
		// Truckload Basic* service level
		rateQuotePage.clickOnGetQuoteButton("Volume and Truckload Basic");

		// Step 16:From the *Quote Details* section,record the Quote Number:
		String quoteNumber = rateQuotePage.recordRateQouteNumber();

		// Step 17:Scroll down to the *Charge Items* section

		// Step 18:Validate *Lift-Gate Service (Delivery)* Charges = Included
		rateQuotePage.verifyChargeItemsTable("Lift-Gate Service (Delivery)", "Included");

		// Step 19:Validate *Notify Request* Charges = Included
		rateQuotePage.verifyChargeItemsTable("Notify Request", "Included");

		// Step 20: Validate *Unloading Services Requested By Consignee* Charges =
		// Included
		rateQuotePage.verifyChargeItemsTable("Unloading Services Requested By Consignee", "Included");

		// Step 21:Validate *Manhattan, NY Delivery Charge* Charges = Included
		rateQuotePage.verifyChargeItemsTable("Manhattan, NY Delivery Charge", "Included");

		// Step 22:Validate *TO REMOTE ACCESS AREA - TABLE B* Charges = Included

		rateQuotePage.verifyChargeItemsTable("TO REMOTE ACCESS AREA - TABLE B", "Included");
		// Step 23: Validate *Total Estimated Freight* Charges = $1,752.12

		// ******************ACORDING TO TEST CASE THE CHARGES SHOULD BE
		// $1752.12************

	     rateQuotePage.verifyChargeItemByServiceLevel("Volume and Truckload Basic", "Total Estimated Freight Charges", "$1,675.17");
		// Step 24:From *My Estes* Home page,select *My Estes* from the menu then select
		// *Logout*
		// Step 25:Logout by clicking the *Confirm* button
		// Step 26:Close the *Browser*

	}

	/**
	 *  test passed on 6/28/22
	 * 
	 * Verify when the LTL Standard Transit service level quote is rated/calculated
	 * then the line item charges for commodity total, accessorials, and surcharges
	 * display the correct dollar amount
	 */

	@Test(enabled = true, priority = 13)

	public void executeQZ_11457() throws InterruptedException {
		// Step 1 & 2: Enter user and password and click on login button.

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3:From *My Estes* Home page,select *Ship* from the menu, then select
		// *Rate Quote*
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();

		// no action is need for this step
		// In the *Select Quote Type* section, only Less than Truckload (incl.
		// Guaranteed) default option is selected
		// Step 5: In the *Requester Information* section, enter or select the following
		// values:

		rateQuotePage.enterMyRole("Third Party");
		rateQuotePage.enterTerms("Prepaid");
		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		rateQuotePage.enterOriginZip("76107");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("90007");
		// Step 7: In the *Commodities* section row 1,enter or select the following
		// values:

		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("925");

		rateQuotePage.enterDesc("Validate Line Item $ Charges 1");
		// Step 8: click the Add commodity button
		rateQuotePage.clickOnAddCommodityButton();
		// Step 9: In the *Commodities* section row 2,enter or select the following
		// values:

		rateQuotePage.enterClass2("92.5");
		rateQuotePage.enterTotalWeight2("50");
		rateQuotePage.enterDesc2(" Validate Line Item $ Charges 2");
		// Step 10:From the *Accessorials* section,select:
		rateQuotePage.selectOrDeselectAccessorialsList("Inside Delivery");
	
		rateQuotePage.selectOrDeselectAccessorialsList("Overlength Freight (16.00' to 19.99')");
	
		// this step is not on the manual step
		rateQuotePage.clickOnViewAllAccessorials();
		rateQuotePage.selectOrDeselectAccessorialsList("Unloading Services Requested By Consignee");
		
		// Step 11:Click *Submit Request* button

		rateQuotePage.clickOnSubmitButton();

		// Step 12: From the *Results* table, select *Get Quote #* for *LTL Standard
		// Transit* service level
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		// Step 16:From the *Quote Details* section,record the Quote Number:
		String quoteNumber = rateQuotePage.recordRateQouteNumber();

		// Step 18: In the *Charge Items* section, validate *Commodity Total* Charges =
        // $1,985.28
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Commodity Total", "$1,985.28");

       // Step 19: Validate *Overlength Freight (16.00' to 19.99')* Charges = $385.00
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Overlength Freight (16.00' to 19.99')", "$");

       // Step 20: Validate *Unloading Services Requested By Consignee* Charges =
        // $25.00
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Unloading Services Requested By Consignee", "$25.00");

       // Step 21: Validate *Inside Delivery* Charges = $100.00
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Inside Delivery", "$100.00");

       // Step 22: Validate *CALIFORNIA COMPLIANCE SURCHARGE* Charges = $11.00
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","CALIFORNIA COMPLIANCE SURCHARGE", "$"); // $15.00 on webpage
    
        // Step 23: Validate *Fuel SURCHARGE* Charges are displayed
        rateQuotePage.verifyChargeItemByServiceLevel("LTL Standard Transit","Fuel Surcharge", "$");
//				Step 24: From  *My Estes* Home page,
//				select  *My Estes* from the menu then select  *Logout*
		myEstesHomePage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnLogout();

		// Step 25:Logout by clicking the *Confirm* button
		myEstesHomePage.clickOnConfirmButton();

	}

	// Do NOT run this test, this test is breaking because of production issues on
	// Step 12 --> quote is not properly stored in 'Time Critical Exception Queue'
	/**
	 * @author lemmoju eNet Rate Quote - Verify when multiple exceptions & Contact
	 *         Me is selected for Guaranteed V/TL Economy then the quote is directed
	 *         to the Time Critical Exception queue with the correct data & all
	 *         exceptions are listed in TC Quote Maintenance
	 * 
	 * @throws InterruptedException
	 */
	@Test(enabled = false, priority = 14)

	public void executeQZ_11832() throws InterruptedException {
		// Step 1: Open the following link: http://enetqa.estesinternal.com/index.jsp
		driver.get(url2);

		/*
		 * Step 2: Login to *eNet* application using the following credentials: User ID:
		 * qaenet01 Password: qaenet01
		 */

		myEstesLoginPage.usernameInput(username5);
		myEstesLoginPage.passwordInput(password5);
		testUtil.setHardWait(1000);

		myEstesLoginPage.clickLoginButton();
		// Step 3: From *eNet* home page top navigation menu, click *Applications*
		eNetHomePage.clickOnApplicationsTab();


		/*
		 * Step 4: From the *Applications* page, find the *Customer Service*
		 * applications list, and click *VTL Rate Quote* link
		 */
		eNetApplicationsPage.clickOnVTLRateQuoteLink();

		/*
		 * Step 5: From the *Volume and Truckload Rate Quote* page, *Contact and Routing
		 * Information* section, enter/select the following data: Account: C123455 Full
		 * Name: TBP-1972 Email: QATest@Estes-Express.com Phone: 804 353-1900 My Role:
		 * Third Party Terms: Pre-paid Origin Zip Code : 90007 City: Los Angeles\ State:
		 * CA Destination: Zip Code : 30307 City: Atlanta State: GA
		 */
		String account = "C123455", fullName = "TBP-1972", email = "QATest@Estes-Express.com", phone = "804 353-1900",
				role = "Third Party", terms = "Pre-paid", originZipCode = "90007", originCity = "Los Angeles",
				originState = "CA", destinationZipCode = "30307", destinationCity = "Atlanta", destinationState = "GA";
		eNetVTLRateQuotePage.enterVTLRateQuoteFullName(fullName);
		eNetVTLRateQuotePage.enterVTLRateQuoteAccountNumber(account);
		eNetVTLRateQuotePage.enterVTLRateQuoteEmail(email);
		eNetVTLRateQuotePage.selectVTLRateQuoteMyRole(role);
		eNetVTLRateQuotePage.enterVTLRateQuotePhoneNumber(phone);
		eNetVTLRateQuotePage.selectVTLRateQuoteTerms(terms);
		eNetVTLRateQuotePage.enterVTLRateQuoteOriginAddress(originZipCode);
		eNetVTLRateQuotePage.enterVTLRateQuoteDestinationAddress(destinationZipCode);

		// Step 6: From the *Scheduling* section, enter/select a weekend date
		eNetVTLRateQuotePage.enterVTLRateQuotePickupWeekendDate();

		/*
		 * Step 7: From the *Commodities* section row 1, enter/select the following
		 * data: Note: Length > 335 Class: 50 Pieces: 7 Piece Type: Roll Total Weight:
		 * *12000* Length: *336* Width: 6 Height: 6 Description: TBP-1972 Guaranteed
		 * V/TL Exceptions
		 */
		String classNum = "50", pieces = "7", pieceType = "ROLL", totalWeight = "12000", length = "336", width = "6",
				height = "6", description = "TBP-1972 Guaranteed V/TL Exceptions";
		eNetVTLRateQuotePage.selectVTLRateQuoteClass(classNum);
		eNetVTLRateQuotePage.enterVTLRateQuotePieces(pieces);
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType(pieceType);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight(totalWeight);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength(length);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth(width);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight(height);
		eNetVTLRateQuotePage.enterDescription(description);

		/*
		 * Step 8: From the *Commodities* section row 2, enter/select the following
		 * data: Note: row 1 + row 2 total weight > 22,000 lb Class: 70 Pieces: 5 Piece
		 * Type: Pallet Total Weight: *10001* Length: 48 Width: 48 Height: 48
		 * Description: TBP-1972 Guaranteed V/TL Exceptions
		 */
		String classNum2 = "70", pieces2 = "5", pieceType2 = "PALLET", totalWeight2 = "10001", length2 = "48",
				width2 = "48", height2 = "48", description2 = "TBP-1972 Guaranteed V/TL Exceptions";
		eNetVTLRateQuotePage.selectVTLRateQuoteClass1(classNum2);
		eNetVTLRateQuotePage.enterVTLRateQuotePieces1(pieces2);
		eNetVTLRateQuotePage.selectVTLRateQuotePiecesType1(pieceType2);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWeight1(totalWeight2);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalLength1(length2);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalWidth1(width2);
		eNetVTLRateQuotePage.enterVTLRateQuoteTotalHeight1(height2);
		eNetVTLRateQuotePage.enterDescription1(description2);

		// Step 9: From the *Freight Information* section, check: Is your freight
		// stackable? (checked)
		eNetVTLRateQuotePage.clickOnIsFreightStackable();

		// Step 10: From the *Accessorials* section, click the *Expand* button
		eNetVTLRateQuotePage.clickOnExpandButton();

		// Step 11: From the *Accessorials* section, select the following accessorial:
		// Blind Shipment Coordination

		eNetVTLRateQuotePage.selectBlindShipmentCoordination();

		// Step 12: Click *Submit*
		eNetVTLRateQuotePage.clickOnVTLRateQuoteSubmitBtn();

		/*
		 * Step: 13: From the Quote selection page, note: Guaranteed Volume and
		 * Truckload Economy display 'Contact Me' Step: 14: Click/select 'Contact Me'
		 * link note: a success message is displayed, record the timeStamp Message:
		 * Success: Using the information you provided, we will contact you shortly. In
		 * the meantime, feel free to give us a call at (800) 645-3952 if you have any
		 * questions.
		 */
		eNetTruckloadRateQuotePage.clickContactMe();
		String recordTimeStamp = testUtil.getTodayDateByFormat("yyyy-MM-dd HH:mm:ss");
		String specificTimeStamp = testUtil.getTodayDateByFormat("MM/dd/yyyy HH mm a");
		String timeOfDay = (String) specificTimeStamp.substring(specificTimeStamp.length() - 2);
		String monthDayYear = (String) specificTimeStamp.subSequence(0, 10);
		specificTimeStamp = (String) specificTimeStamp.subSequence(0, specificTimeStamp.length() - 3);
		System.out.println("Recorded Time: " + recordTimeStamp);
		eNetTruckloadRateQuotePage.verifyConfirmationMessage();

		/*
		 * Step 15: From the *Applications* page, find the *Customer Service*
		 * applications list, and click *Time Critical Exception Queue* link
		 */
		eNetTruckloadRateQuotePage.clickOnApplicationWithoutFrame();
		eNetApplicationsPage.clickOnTimeCriticalExceptionQueue();

		/*
		 * Step 16: From the *Current Exceptions* list, Find the quote with the
		 * following criteria: 1- timeStamp matching the timestamp noted in the previous
		 * steps 2- Company Name = CDM TEST 3 - Account = C123455 4- record the quote
		 * number:
		 */
		String companyName = "CDM TEST";
		eNetTimeCriticalExceptionQueue.inputDateFrom(monthDayYear);
		eNetTimeCriticalExceptionQueue.inputTimeOfDay(timeOfDay);
		eNetTimeCriticalExceptionQueue.inputAccount(account);
		eNetTimeCriticalExceptionQueue.inputCompanyName(companyName);
		eNetTimeCriticalExceptionQueue.clickSubmitButton();

		String quoteNumber = eNetTimeCriticalExceptionQueue.getQuoteByTimeStamp1(recordTimeStamp);

		/*
		 * Step 17: Validate the Exceptions reason are: Weekend Pickup* Restricted
		 * Accessorial* Invalid length on commodity* Shipment too heavy for GTD rate*
		 */
		eNetTimeCriticalExceptionQueue.verifyExceptionDisplayed1(quoteNumber,
				"Weekend Pickup Restricted Accessorial Invalid length on commodity Shipment too heavy for GTD rate");

		// Step 18: Click/select the Quote/record - The *Time Critical Quote
		// Maintenance* page displays
		eNetTimeCriticalExceptionQueue.selectQuoteNumber(quoteNumber);

		/*
		 * Step 19: Validate the Exception reason are displayed under Exceptions Weekend
		 * Pickup* Restricted Accessorial* Invalid length on commodity* Shipment too
		 * heavy for GTD rate* are displayed under Exceptions (top right)
		 */
		eNetTimeCriticalQuoteMaintenancePage.verifyExceptions("Weekend Pickup", "Restricted Accessorial",
				"Invalid length on commodity", "Shipment too heavy for GTD rate");

		/*
		 * Step 20: Validate the *Contact Information & Shipping Information* data agree
		 * with step 5 data: Account: C123455 Full Name: TBP-1972 Email:
		 * QATest@Estes-Express.com Phone: 804 353-1900 My Role: Third Party Terms:
		 * Pre-paid Origin Zip Code : 90007 City: Los Angeles State: CA Destination: Zip
		 * Code : 30307 City: Atlanta State: GA
		 */
		eNetTimeCriticalQuoteMaintenancePage.verifyContactShippingInfo(role, terms, account, companyName, fullName,
				phone, email, originZipCode, originCity, originState, destinationZipCode, destinationCity,
				destinationState);
		/*
		 * Step 21/22: Validate *Commodity* data row 1 agree with step 7 data: Class: 50
		 * Pieces: 7 Piece Type: Roll Total Weight: *12000* Length: *336* Width: 6
		 * Height: 6 Description: TBP-1972 Guaranteed V/TL Exceptions Class: 70 Pieces:
		 * 5 Piece Type: Pallet Total Weight: *10001* Length: 48 Width: 48 Height: 48
		 * Description: TBP-1972 Guaranteed V/TL Exceptions
		 */
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityClass(classNum, classNum2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityPieces(pieces, pieces2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityPiecesType(pieceType, pieceType2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityTotalWeight(totalWeight, totalWeight2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityLength(length, length2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityWidth(width, width2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityHeight(height, height2);
		eNetTimeCriticalQuoteMaintenancePage.validateCommodityDescription(description, description2);

		/*
		 * Step 23: Validate Stackable is selected
		 */
		eNetTimeCriticalQuoteMaintenancePage.validateStackable();

		/*
		 * Step 24: Validate *Blind Shipment Coordination* displays in the
		 * *Accessorials* section with charges (could be $0.00 waived)
		 */
		eNetTimeCriticalQuoteMaintenancePage.validateAccessorials("Blind Shipment Coordination");

		/*
		 * Step 25: Validate *Overlength Charge - 28.00' - 39.9'* displays in the
		 * *Additional Accessorials* section with charges (could be $0.00)
		 */
		eNetTimeCriticalQuoteMaintenancePage.validateAccessorial("OVERLENGTH CHARGE-28.00' - 39.9'");

		/*
		 * Step 26: Validate *California Compliance Surcharge* displays in the
		 * *Additional Accessorials* section with charges (could be $0.00 waived)
		 */
		eNetTimeCriticalQuoteMaintenancePage.validateAccessorial("CALIFORNIA COMPLIANCE SURCHARGE");

		/*
		 * Step 27/28: From *eNet* home page top navigation menu, click *Log Out*
		 * Confirm by clicking the *Logout* button
		 */
		eNetTimeCriticalQuoteMaintenancePage.clickLogOut();
		eNetTimeCriticalQuoteMaintenancePage.clickLogOutFromIntranet();

	}

}
