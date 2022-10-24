package ltlRateEstimateTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import jagacyVT.screens.DisplayCommentsScreen;
import jagacyVT.screens.DisplayRequestServiceInfoScreen;
import jagacyVT.screens.GoldMedalMainMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.SubsetRequestServiceInfoScreen;
import jagacyVT.screens.WorkWithRequestScreen;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesTests.MyEstesRegressionTest;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQouteRateEstimatePage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;
import util.TestUtil;

public class LtLRateEstimateRegressionTest extends TestBase {

	Logger logger = Logger.getLogger(LtLRateEstimateRegressionTest.class);

	RateQuotePage rateQuotePage = new RateQuotePage();
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	RateQouteRateEstimatePage rateEstimatePage = new RateQouteRateEstimatePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();

	/******************************* TESTS *******************************/

	/**
	 * LTL Rate Estimate - Verify FVC line is displayed in Charge Items section for
	 * LTL
	 * 
	 * This test passed on 11-08-2022 -without changes
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_9401() throws InterruptedException {

		String fullCoverageAcc = "Full Value Coverage (FVC)";
		String fullCoverageWaived = "Full Value Coverage (FVC) - WAIVED";
		String name = "Kevin Calib";
		String origzip = "23226";
		String destzip = "30307";
		String selectclass = "50";
		String weight = "900";
		String CoverageValue = "10001";
		String desc = "This discription is for QZ-9401";

		// Ship--> Rate Quote
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);
		// Requester Information
		rateEstimatePage.enterContactName(name);
		rateEstimatePage.enterOrginZip(origzip);
		rateEstimatePage.enterDestinationZip(destzip);
		rateEstimatePage.selectClass(selectclass);
		rateEstimatePage.enterTotalWeight(weight);
		rateEstimatePage.enterDesc(desc);

		// Enter freight information
		rateQuotePage.enterFullValueCoverage(CoverageValue);
		rateQuotePage.verifyCheckBoxIsChecked();
		rateQuotePage.clikOnRateEstimateSubmitButton();
		// rateQuotePage.clikOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Click on get quote for LTL standard transit
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateEstimatePage.verifyChargedItemsDisplay();
		testUtil.setHardWait(1000);
		rateQuotePage.validateChargeItemsTableDescription(fullCoverageAcc);
		rateQuotePage.verifyChargeItemTableChargesAreNotEqualToZero(fullCoverageAcc);

		// Click on revise quote and verify autopopulated values
		rateQuotePage.clickOnReviseQuoteButton();
		// verify fields are autopopulated with details from the Quote created
		rateEstimatePage.verifyName(name);

		// Uncheck the full value coverage
		rateQuotePage.deselectAdditionalCoverage();
		rateQuotePage.clickOnSubmitButton();
		rateQuotePage.verifyRateQuoteResultPage();

		// Click on get quote for LTL standard transit
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.validateChargeItemsTableDescription(fullCoverageWaived);
		rateQuotePage.verifyChargeItemTableChargesEqualToZero(fullCoverageWaived);

	}

	/** This is AS400 test that uses Jagacy **/

	// Moved from MyEstesAS400RegressionTest class
	/*
	 * LTL Rate Estimate - Verify selected Guaranteed LTL Standard Transit quotes
	 * are save to the GMS system in AS400 once the service level is
	 * selected(Unathenticated)
	 */
	
	/**Tunred off- it runs locally as it is inconsistent**/

	@Test(enabled = false, priority = 2)

	public void executeQZ_7875()
			throws Exception {
		// Do not login
		myEstesHomePage.clickOnStartRequestButton();

		// Enter Rate quote details
		rateEstimatePage.enterContactName("QZ-7785");
		rateEstimatePage.selectOriginCountry("United States");
		rateEstimatePage.enterOrginZip("16159");
		rateEstimatePage.selectDestinationCountry("United States");
		rateEstimatePage.enterDestinationZip("90007");
		rateEstimatePage.selectClass("50");
		rateEstimatePage.enterTotalWeight("4700");
		rateEstimatePage.enterDesc("This is a test");
		rateEstimatePage.clickOnSubmitButton();

		rateEstimatePage.validatePageTitle();

		rateQuotePage.clickOnGetQuoteButton("12PM");
		testUtil.setHardWait(1000);
		String quoteNum = rateQuotePage.recordQuoteNumber("Gauranteed 12 PM");

		String quoteNum2 = quoteNum.replace("2", "3");

		testUtil.setHardWait(2000);

		// Jagacy

		// SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String searchJob = "CALL GMR000";
		String searchValue = "5";
		String type = "5";
		String name = "myJagacyVT";
		// String host="exlaqa";
		String terminal = "dec-vt220";

		// session = new SessionVt(name, "exlaqa", terminal);
		// MySession session = new MySession();
		// session.open();

		// Start session
		SessionVt session = null;
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
		// Step:7
		loginScreen.logon(userName, password);
		Thread.sleep(2000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();

		// Step:8
		ibmMainMenuScreen.enterValueToComandLineField(searchJob);
		goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();
		// Step:9
		goldMedalMainMenuScreen.enterSelectionValue(searchValue);

		workWithRequestScreen.verifyWorkWithRequestInfoScreen();
		Thread.sleep(4000);
		// Step:10
		workWithRequestScreen.enterF17Key();
		Thread.sleep(3000);
		subsetRequestServiceInfoScreen.verifySubsetRequestInfoScreen();
		// Step:11 ,12
		subsetRequestServiceInfoScreen.clearUserIdField2();
		workWithRequestScreen.verifyWorkWithRequestInfoScreen();

		// Step:13
		workWithRequestScreen.enterQuoteNumber(quoteNum);

		// Step:14
		workWithRequestScreen.enterValueInOptField(type);
		testUtil.setHardWait(2000);
		displayRequestServiceInfoScreen.verifyDisplayRequestScreen();
		// Step:15
		displayRequestServiceInfoScreen.verifyKeyedDataIsDisplayed();
		// step:16
		displayRequestServiceInfoScreen.enterF20Key();
		displayCommentsScreen.verifyDisplayCommentsScreen();
		// Step:17

		displayCommentsScreen.verifyDataIsDisplayed();
		// step:18
		displayCommentsScreen.enterF3Key();
		workWithRequestScreen.verifyWorkWithRequestInfoScreen();
		// Step:19
		testUtil.setHardWait(1000);
		testUtil.switchToWindow(0);
		testUtil.setHardWait(2000);

		rateEstimatePage.clickOnContactMe("Guaranteed LTL Standard Transit: 10AM");
		testUtil.setHardWait(2000);
		// Step:20
		testUtil.switchToWindow(1);
		testUtil.setHardWait(2000);
		workWithRequestScreen.enterQuoteNumberWithPrefix3(quoteNum2);
		testUtil.setHardWait(2000);
		// Step:21

		workWithRequestScreen.verify12PMQuoteIsNotDisplayed(quoteNum);

		if (session != null) {
			session.abort();
			session.close();
		}

	}

	/*
	 * LTL Rate Estimate - Commodities - Verify if Total Weight is greater than
	 * 11,999 lbs an error message is displayed, 'log in' display as a link and when
	 * selected direct the user to My Estes Login page.
	 * 
	 */

	@Test(enabled = true, priority = 3)

	public void executeQZ_7130()
			throws InterruptedException {

		String alertMessage = "To receive rates for a shipment weighing more than 20,000 lbs., please log in and select the Volume and Truckload quote type, or call our V/TL team at 1-866-378-3748, Press 3.";

		// Open MyEstes app and click Start request
		myEstesHomePage.clickOnStartRequestButton();
		rateEstimatePage.validatePageTitle();
		testUtil.setHardWait(2000);
		rateEstimatePage.verifyRateQuoteEstimateTitle();

		// Requester Information
		rateEstimatePage.enterContactName();

		// Routing Information
		rateEstimatePage.enterOrginZip("16159");
		rateEstimatePage.enterDestinationZip("30307");

		// Commodities
		rateEstimatePage.selectClass("100");
		rateEstimatePage.enterTotalWeight("20001");
		rateEstimatePage.clickOnSubmitButton();
		testUtil.setHardWait(2000);
		// Validating error message
		rateEstimatePage.validateAlertMessage(alertMessage);

		// Login Page
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.loginPageDisplayed();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButtonWithoutFluentWait();
		//testUtil.setHardWait(4000);  
		//testUtil.setHardWait(60000);//<<---Added  long wait to load page
		// Validating Rate Quote - Create Rate Quote
		rateQuotePage.verifyCreateRateQoutePage();
	}

	/*
	 * LTL Rate Estimate - For an unauthenticated customer, verify Discount is
	 * displayed as a line item, with amount, in the Charge Items section.
	 * 
	 * This test passed on 11-08-2022 -without changes
	 */

	@Test(enabled = true, priority = 4)
	public void executeQZ_6760()
			throws InterruptedException {

		String expected = "RATE QUOTE DISCLOSURES AND TERMS OF SERVICE";

		myEstesHomePage.clickOnStartRequestButton();
		testUtil.setHardWait(2000);
		rateEstimatePage.enterContactName();
		rateEstimatePage.selectOriginCountry("United States");
		rateEstimatePage.enterOrginZip("16159");
		rateEstimatePage.selectDestinationCountry("United States");
		rateEstimatePage.enterDestinationZip("30307");
		rateEstimatePage.selectClass("100");
		rateEstimatePage.enterTotalWeight("975");
		rateEstimatePage.clickOnSubmitButton();

		rateEstimatePage.validatePageTitle();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		testUtil.setHardWait(1000);
		rateQuotePage.validateRateQuoteInformations2();
		rateQuotePage.verifyChargeItems("$763.32");

		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		rateQuotePage.validateRateQuoteDisclosuresAndTermsOfService(expected);
	}

	/*
	 * LTL Rate Estimate - Verify Manhattan and Alaska Accessorials display in the
	 * Quote results for an unauthenticated customer. <qz-5486>
	 * 
	 * This test passed on 10-08-2022 -without changes
	 */

	@Test(enabled = true, priority = 5)

	public void executeQZ_5486()
			throws InterruptedException {

		myEstesHomePage.clickOnStartRequestButton();
		testUtil.setHardWait(3000);
		rateEstimatePage.enterContactName();
		rateEstimatePage.selectOriginCountry("United States");
		rateEstimatePage.enterOrginZip("10014");
		rateEstimatePage.selectDestinationCountry("United States");
		rateEstimatePage.enterDestinationZip("99502");
		rateEstimatePage.selectClass("100");
		rateEstimatePage.enterTotalWeight("5000");
		rateEstimatePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.verifyChargeItems("$0.00");
		// rateEstimatePage.verifyChargedItemsDisplay();

	}

	/*
	 * LTL Rate Estimate - Verify an unauthenticated customer can retrieve a Rate
	 * Estimate.
	 * 
	 * This test passed on 11-08-2022 -without changes
	 */

	@Test(enabled = true, priority = 6)

	public void executeQZ_846() throws InterruptedException {

		// Click *Start Request* from the *Request a Quote* option
		myEstesHomePage.clickOnStartRequestButton();

		// Enter Requester information
		rateEstimatePage.enterContactName();

		// Enter Routing information
		rateEstimatePage.enterOrginZip("23226");
		rateEstimatePage.enterDestinationZip("30307");

		// Enter Commodities
		rateEstimatePage.selectClass("50");
		rateEstimatePage.enterTotalWeight("900");
		testUtil.setHardWait(1000);
		// Click on submit
		rateEstimatePage.clickOnSubmitButton();

		// Get Quote# from LTL Standard Transit
		rateEstimatePage.clickOnGetQuoteButtonByServiceLevel("LTL Standard Transit");

		// Record Quote#
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

	}

	/**
	 * 
	 * Passed on 7/5/22
	 * 
	 * LTL Rate Estimate - Verify an unauthenticated customer receives Informational
	 * Message when request a quote with either a Non-Direct Origin or Destination
	 * Point.
	 */

	// 
	@Test(enabled = true, priority = 7)

	public void executeQZ_842()
			throws InterruptedException {

		myEstesHomePage.clickOnStartRequestButton();

		rateEstimatePage.enterContactName();
		rateEstimatePage.selectOriginCountry("Mexico");
		rateEstimatePage.enterOrginZip("77539");
		rateEstimatePage.selectDestinationCountry("United States");
		rateEstimatePage.enterDestinationZip("30307");
		rateEstimatePage.selectClass("50");
		rateEstimatePage.enterTotalWeight("9700");
		rateEstimatePage.enterDesc("This is test case validation");
		rateEstimatePage.clickOnSubmitButton();

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		testUtil.setHardWait(1000);
		rateEstimatePage.verifyAttentionMessageOriginPoint();

		rateEstimatePage.clickOnReviseQouteButton();
		rateEstimatePage.selectOriginCountry("United States");
		rateEstimatePage.enterOrginZip("23219");
		rateEstimatePage.selectDestinationCountry("United States");
		rateEstimatePage.enterDestinationZip("98625");
		rateEstimatePage.enterDestinationCity("CLOVERDALE");
		rateEstimatePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
	//	testUtil.setHardWait(2000);

		rateEstimatePage.verifyAttentionMessageDestinationPoint();

	}

	/*
	 * LTL Rate Estimate - Verify an unauthenticated user can view and print a Rate
	 * Quote and all data is correct
	 */
	// THIS TEST DEALS WITH DOWNLOADS AND IS RUNNING LOCALLY
	@Test(enabled = false, priority = 8)

	public void executeQZ_838()
			throws InterruptedException, IOException {

		// Click *Start Request* from the *Request a Quote* option
		myEstesHomePage.clickOnStartRequestButton();

		// Enter Requester information
		rateEstimatePage.enterContactName();

		// Enter Routing information
		rateEstimatePage.enterOrginZip("23226");
		rateEstimatePage.enterDestinationZip("30307");

		// Enter Commodities
		rateEstimatePage.selectClass("50");
		rateEstimatePage.enterTotalWeight("970");
		rateEstimatePage.enterDesc("QZ-838");

		// From the *Freight Information* section,Enter Full Value Coverage:

		rateEstimatePage.enterFullValueCoverage("7300");

		rateQuotePage.selectOrDeselectInsideDelivery();

		rateQuotePage.clickOnViewAllAccessorials();

		rateQuotePage.selectOrDeselectAccessorials("Lift-Gate Service (Delivery) ");

		rateQuotePage.selectOrDeselectAccessorials("Unloading Services Requested By Consignee");

		// click on submit button
		rateQuotePage.clickOnSubmitButton();

		// Get Quote for LTL Standard Transit
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
		logger.info("Data: " + data);
		if (data.contains(text)) {
			Assert.assertTrue(data.contains(text));
		}
		cosDoc.close();
		pdDoc.close();

		System.out.println("Text found on the pdf file");

		testUtil.deleteFilesFromFolder(downloadPath, fileFound);

	}

	/*
	 * LTL Rate Estimate - Verify 'Contact Me' is displayed for Guaranteed LTL
	 * Standard Transit service level when 'Total Weight' is greater than 10,000
	 * lbs.
	 * 
	 * 
	 * This test passed on 11-08-2022 -without changes
	 */

	@Test(enabled = true, priority = 9)

	public void executeQZ_837()
			throws InterruptedException {

		// click on start request button
		myEstesHomePage.clickOnStartRequestButton();

		// fill required fields
		rateEstimatePage.enterContactName();
		rateEstimatePage.selectOriginCountry("United States");
		rateEstimatePage.enterOrginZip("23226");
		rateEstimatePage.selectDestinationCountry("United States");
		rateEstimatePage.enterDestinationZip("30307");
		rateEstimatePage.selectClass("50");
		rateEstimatePage.enterTotalWeight("10100");
		rateEstimatePage.clickOnSubmitButton();

		// LTL Standard Transit service level options display a *Contact Me*
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyContactMe("Guaranteed LTL Standard Transit: 5PM", true);
		// Select *Get Quote #* for LTL Standard Transit
		rateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		// record the Quote Number
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");
		// Select *Revise Quote*
		rateQuotePage.clickOnReviseQuoteButton();
		// update *Commodities* section weight
		rateEstimatePage.enterTotalWeight("10000");
		rateEstimatePage.clickOnSubmitButton();
		// LTL Standard Transit service level options are calculated and displays *Get
		// Quote #*
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 10AM", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 12PM", true);
		rateQuotePage.verifyBasicCharge("Guaranteed LTL Standard Transit: 5PM", true);

		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");
		rateQuotePage.recordQuoteNumber("LTL Standard Transit");

	}

}
