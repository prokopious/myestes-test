package claimsTests;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import claimsPages.ClaimsPage;
import claimsPages.FileAClaimPage;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetClaimsEntryDetailPage;
import eNetPages.ENetClaimsEntryPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetMyEstesManagementToolPage;
import myEstesPages.MyEstesClaimsPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import testBase.TestBase;
import util.SQLDataList;
import util.TestUtil;

public class ClaimsRegressionTest extends TestBase {

	private Logger logger = Logger.getLogger(ClaimsRegressionTest.class.getName());
	
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	ClaimsPage claimsPage = new ClaimsPage();
	FileAClaimPage fileAClaimPage = new FileAClaimPage();
	SQLDataList sqlDataList = new  SQLDataList();
	ENetLoginPage enetLoginPage = new ENetLoginPage();
	MyEstesClaimsPage myEstesClaimsPage = new MyEstesClaimsPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetMyEstesManagementToolPage eNetMyEstesManagementToolPage = new ENetMyEstesManagementToolPage();
	ENetClaimsEntryPage eNetClaimsEntryPage = new ENetClaimsEntryPage();
	ENetClaimsEntryDetailPage eNetClaimsEntryDetailPage = new ENetClaimsEntryDetailPage();

	
			/*******************************TESTS *******************************/

	//This test is on hold and should not be executed until further notice. Dee/Kathy
	// THIS IS GREEN SCREEN
	/*
	 * Claims Inquiry - Group Account - Verify customer can search and view a claim
	 * by Claim Number.
	 */
	@Test(enabled = false, priority = 1)

	public void executeQZ_7149() throws InterruptedException {
		
        String acctNumber = "5068692";
        String query = "select ahclm from fbfiles.CLP001 where ahscd = '"+acctNumber+"'  or ahccd = '"+acctNumber+"' or ahcust = '"+acctNumber+"' and AHPAMT <> 0 and AHDCHK <> 0  order by ahdad8 desc";
        List<String> dbValue = sqlDataList.getFirstRowDetailsFromEXLAQA(query);
      //  String claimNumber = dbValue.get(0);
        
        String claimNumber = "1498145"; 
        myEstesHomePage.clickOnMyEstes();
        myEstesHomePage.clickOnLogin();
        testUtil.setHardWait(1000);
        myEstesLoginPage.enterUserName("test1group");
        myEstesLoginPage.enterPassword("test1group");
        myEstesLoginPage.clickOnLoginButton();

        myEstesHomePage.clickOnManageLink();
        myEstesHomePage.clickOnClaims();
        claimsPage.selectAccountFromSearch(acctNumber);
        claimsPage.selectSearchBy("Estes Claim Number");
        //claimsPage.enterClaimNumber();
        claimsPage.enterClaimNumber(claimNumber);
        claimsPage.clickOnLookupButton();
        testUtil.setHardWait(500);
        //claimsPage.verifyClaimsResultDisplay("");
        claimsPage.verifyClaimNumberField();
        claimsPage.verifyPRONumberField();
        claimsPage.verifyStatusField();
        claimsPage.verifyDateField();
        claimsPage.verifyRefNumberField();
        claimsPage.verifyClaimAmountField();
        claimsPage.verifyRemitToField();
        claimsPage.clickOnCaretIcon();
        claimsPage.verifyClaimResult();
        //claimsPage.clickOnLogout();

	}


	//This test is on hold and should not be executed until further notice. Dee/Kathy
	// TC-FP2-535- THIS IS GREEN SCREEN
	/*
	 * Claim Inquiry - Verify the customer can search by selecting Custom from Date
	 * Range dropdown.
	 */
	
	@Test(enabled = false, priority = 2)

	public void executeQZ_6084() throws InterruptedException,
			HeadlessException, ClassNotFoundException, AWTException, UnsupportedFlavorException, IOException {

		String acctNumber = "5068692";

		// Login to MyEstes APP
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		// Click on manage link
		myEstesHomePage.clickOnManageLink();


		// Click on Claims
		myEstesHomePage.clickOnClaims();

		// Enter the specifiled account number
		claimsPage.selectAccountFromSearch(acctNumber);

		// claimsPage.enterAccountNumber(acctNumber);
		testUtil.setHardWait(2000);

		// Select Date Range from search by drop down
		claimsPage.selectSearchBy("Date Range");

		// Select custom from field date range dropdown
		claimsPage.selectFieldDateRange("Custom");
		testUtil.setHardWait(2000);

		claimsPage.selectStatus("Paid");

		// Enter custom date in start and end date
		claimsPage.addCalendarDate("MONTH", -8);
		testUtil.setHardWait(2000);
		claimsPage.enterTodaysDate();

		// Click on Lookup button
		claimsPage.clickOnLookupButton();
		testUtil.setHardWait(500);
		// Verify The Claims Results table populated with the following fields:

		claimsPage.verifyClaimNumberField();
		claimsPage.verifyPRONumberField();
		claimsPage.verifyStatusField();
		claimsPage.verifyDateField();
		claimsPage.verifyRefNumberField();
		claimsPage.verifyClaimAmountField();
		claimsPage.verifyRemitToField();

		// Click on caret Icon
		claimsPage.clickOnCaretIcon();

		// Verify paid claim details
		claimsPage.verifyClaimResult();

	}

	
	// Hold execution until further notice. Kathy and Dee
	/*
	 * Claims Inquiry - National Account - Verify customer can search and view a
	 * claim by Reference Number.
	 */

	// TC-DP2-937
	@Test(enabled =false, priority = 3) 

	public void executeQZ_7632() throws InterruptedException {

        String acctNumber = "5068692";

        String query = "select ahcref  from fbfiles.CLP001 where ahscd = '" + acctNumber + "' or ahccd = '" + acctNumber
                     + "' or ahcust = '" + acctNumber + "' and AHCREF <> '' order by ahdad8 desc";
        List<String> dbValue = sqlDataList.getFirstRowDetailsFromEXLAQA(query);
        
        String claimNumber = dbValue.get(0);
  
         // Login to MyEstes APP
        myEstesHomePage.clickOnMyEstes();
        myEstesHomePage.clickOnLogin();
        myEstesLoginPage.enterUserName(username4);
        myEstesLoginPage.enterPassword(password4);
        testUtil.setHardWait(1000);
        myEstesLoginPage.clickOnLoginButton();

        // Click on manage link
        myEstesHomePage.clickOnManageLink();
        
        // Click on Claims
        myEstesHomePage.clickOnClaims();

        // Select Your Reference Number from search by drop down
        claimsPage.selectSearchBy("Your Reference Number");

        // Enter Reference number
        claimsPage.enterReferenceNumber(claimNumber);

        // Click on Lookup button
        claimsPage.clickOnLookupButton();
        testUtil.setHardWait(3000);

        // Verify The Claims Results table populated with the following fields:

        claimsPage.verifyClaimNumberField();
        claimsPage.verifyPRONumberField();
        claimsPage.verifyStatusField();
        claimsPage.verifyDateField();
        claimsPage.verifyRefNumberField();
        claimsPage.verifyClaimAmountField();
        claimsPage.verifyRemitToField();

	}

	//This test is on hold and should not be executed until further notice. Dee/Kathy
	/*
	 * Claims Inquiry - Verify Customer can search for more than one Pro Number
	 */
	@Test(enabled = false, priority = 4)

	public void executeQZ_7629()
			throws InterruptedException, Exception, IndexOutOfBoundsException {

		String accNumber = "5068692";

		String query = " select ahot , ahpro from fbfiles.CLP001 where ahscd = '5068692' or ahccd = '5068692' or ahcust = '5068692' and AHPAMT <> 0 and AHDCHK <> 0 and AHOT ='103'order by ahdad8 desc";

		// Login to myEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Click on manage link
		myEstesHomePage.clickOnManageLink();

		// Click on claims
		myEstesHomePage.clickOnClaims();
		testUtil.setHardWait(1000);
		// Enter the specifiled account number
		claimsPage.enterAccountNumber(accNumber);

		// Select PRO number from the search by drop down
		claimsPage.selectSearchBy();
		claimsPage.selectPRONumber();

		testUtil.setHardWait(2000);

		//Enter upto 5 pro numbers
		
		List<String> proNumber = sqlDataList.getProFromQuery(query);

		claimsPage.enterMultiplePRONumber(proNumber.get(0).replace("-", ""), proNumber.get(1).replace("-", ""),
				proNumber.get(2).replace("-", ""), proNumber.get(3).replace("-", ""),
				proNumber.get(4).replace("-", ""));
		
        // Click on Lookup button
		claimsPage.clickOnLookupButton();

		testUtil.setHardWait(3000);
        
		//Verify The Claims Results table populated with the following fields:
		
		claimsPage.verifyClaimNumberField();
		claimsPage.verifyPRONumberField();
		claimsPage.verifyStatusField();
		claimsPage.verifyDateField();
		claimsPage.verifyRefNumberField();
		claimsPage.verifyClaimAmountField();
		claimsPage.verifyRemitToField();
		claimsPage.clickOnCaretIcon();
		claimsPage.verifyClaimResult();

	}
	//This test is on hold and should not be executed until further notice. Dee/Kathy
	
	// TC-DP2-937
	/*
	 * Claims Filing - Verify error message displays when required fields are left
	 * blank.
	 */
	@Test(enabled = false, priority = 5)

	public void executeQZ_7622() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();
		claimsPage.clickOnFileClaimTab();
		claimsPage.clickFileClaimButton();
		claimsPage.clickOnSubmitButton();
		claimsPage.verifyErrorMessage();

	}

	//This test is on hold and should not be executed until further notice. Dee/Kathy
	/*
	 * Claims Filing - Verify user can search the Account Search modal using
	 * different search criteria.
	 */
	@Test(enabled = false, priority = 6)
	
	public void executeQZ_7624()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();
		testUtil.setHardWait(1000);
		claimsPage.clickOnFileClaimTab();
		fileAClaimPage.clickOnFileClaimButton();
		fileAClaimPage.clickOnAccountSearch();

		fileAClaimPage.enterInAccountSearch("0500845");
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

		fileAClaimPage.enterInAccountSearch("GASTONIA");
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

	
	//This needs to be remaind turned off due to some changes. 
	/*
	 * Claims Filing - Group Account - Verify customer can file a Loss claim
	 */
	// It is not running in headless mode. This needs to run locally
	@Test(enabled = false, priority = 7)

	public void executeQZ_7625() throws Exception, InterruptedException {

		String additionalComments = "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine.";
		String csvFilePath = TestUtil.getCurrentWorkingPath() + "\\Downloads\\DummyFile.xls";
		//String fileName = "DummyFile.xls";
		String Qty = "12";
		String claimAmt = "130.00";

		// Login to myestes with test1group credentials
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username12);
		myEstesLoginPage.enterPassword(password12);
		myEstesLoginPage.clickOnLoginButton();

		//The following steps are added to get the required data but are not in the steps
		// Get the first account number
		testUtil.setHardWait(3000);
		String AccNum = myEstesHomePage.getAccNumForFirstRow();
		
		myEstesHomePage.clickOnFirstAccountNumLink();
		// Recent Shipment - Shipper
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();

		// Get pro number
	String proNumber = myEstesRecentShipmentsPage.getPRONumForFirstRow();

		String pro = proNumber.split("-")[1].trim();
	System.out.println(pro);
		
		
	//	String proNumber = "222-8022221";
	//	String pro = "8022221";

		// Goto Claims
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();
		claimsPage.clickOnFileClaimTab();
		fileAClaimPage.clickOnFileClaimButton();
		fileAClaimPage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		fileAClaimPage.enterAccountNumberInAccSearch(AccNum);
		fileAClaimPage.clickOnFirstAccountNumber();

		// Enter account number
		fileAClaimPage.enterAccountNumber(AccNum);

		// Enter the Pro number for which recent FB's are created
		fileAClaimPage.enterPRONumber(proNumber);

		testUtil.setHardWait(2000);
		// Select Loss in the claim type
		fileAClaimPage.selectClaimType("Loss");
		testUtil.setHardWait(1000);

		// Verify PRO Number and claim Type section fields are prefilled
		fileAClaimPage.verifyPRONumsAndClaimTypeArePreFilled();

		// Select my info for claimant
		myEstesClaimsPage.clickOnClaimantIMyInfoLink();

		// Verify fields are prefilled for claimant
		fileAClaimPage.verifyClaimantInfoFieldsArePrefilled();

		// Click on remitInfo Same as claimant
		myEstesClaimsPage.clickOnRemitInfoSameAsClaimantLink();

		// Verify fields are prefilled for Remit
		fileAClaimPage.verifyRemitInfoFieldsArePrefilled();

		// Verify Shipper and Consignee fields are populated as per the PRO Number entry
		fileAClaimPage.verifyShipperAndConsigneeFieldsArePreFilled();


		// Clicl on invoice browse button to upload a dummy file
		fileAClaimPage.clickOnVendorInvoiceBrowseLink(csvFilePath);

		// Enter description
		myEstesClaimsPage.writeLossDamageDesc();

		// Enter quantity
		myEstesClaimsPage.enterQty(Qty);

		// Enter claim amount
		myEstesClaimsPage.enterTotalCost(claimAmt);

		// Enter maximum number of characters in the 'Additional Comments' box
		myEstesClaimsPage.writeAdditionalComments(additionalComments);

		// Check the acknowledgment box
		myEstesClaimsPage.clickOnAcknowledgeButton();

		// click on submit button
		fileAClaimPage.clickOnSubmitButton();

		testUtil.setHardWait(3000);
		// Validate the expected message
		fileAClaimPage.validateTheMessageIsDisplayed("");

		// Get the claim number associated with the PRO number from the DB
		String query = "select * from fbfiles.CLM10P101 where pro_num = '" + pro + "' ";
		List<String> dbValue = sqlDataList.getFirstRowDetailsFromEXLAQA(query);
		String claimNumber = dbValue.get(0);
		System.out.println(claimNumber);

		// Navigate to Enet application
		driver.get(url2);

		// Login with qaenet credentials
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// click on Applications tab
		eNetHomePage.clickOnApplicationsTab();

		// Click on claims entry link
		eNetApplicationsPage.clickOnClaimsEntryLink();

		// Enter the recorded claim number
		eNetClaimsEntryPage.enterClaimNumber(claimNumber);
		testUtil.setHardWait(2000);

		// Click on Search button
		eNetClaimsEntryPage.clickOnSearchButton();

		// Validate data on the Claims Entry - Detail form matches what was entered on
		// the UI.
		eNetClaimsEntryDetailPage.verifyPRONumIsDisplayed(pro);
		eNetClaimsEntryDetailPage.verifyClaimAmountIsDisplayed(claimAmt);

		// Click on confirm button
		eNetClaimsEntryDetailPage.clickOnConfirmButton();

		// Validate the confirmation message
		eNetClaimsEntryPage.validateTheMessageIsDisplayed("");

		// Run the query to get the results for the test claim number displays in the
		// table
		String query2 = "select * from fbfiles.CLP001 where ahclm = '" + claimNumber + "' ";
		List<String> dbValue2 = sqlDataList.getFirstRowDetailsFromEXLAQA(query2);
		String claimResult = dbValue2.get(0);
		System.out.println(claimResult);
		Assert.assertTrue(claimResult.equals(claimNumber));
	}
	
	// this test need to remain turned off due to some changes.
	/*
	 * Claims Filing - Verify error message displays when the file upload size has
	 * reached the max size
	 */

	//This test runs locally as it deals with file uploads
	@Test(enabled = false, priority = 8)

	public void executeQZ_8244()
			throws Exception, InterruptedException {

		String sample10MBFile = TestUtil.getCurrentWorkingPath() + "\\Downloads\\sample10-mb.pdf";
		String sample9MBFile = TestUtil.getCurrentWorkingPath() + "\\Downloads\\sample9.9mb.pdf";
		String sample5MBFile = TestUtil.getCurrentWorkingPath() + "\\Downloads\\sample5mb.pdf";

		String errorMessage = "File exceeds size limit of 10MB";
		String errorMessage2 = "The total size of the documents you are attempting to upload is larger than 50 MB. Please reduce the size of your documents and try resubmitting your claim.";
		String claimAmt = "130.00";

		// Login to myestes with smokelocal credentials
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		// Recent Shipment - Shipper
		testUtil.setHardWait(2000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();

		// Get pro number
		String proNumber = myEstesRecentShipmentsPage.getPRONumForSecondRow();

		String pro = proNumber.split("-")[1].trim();
		System.out.println(pro);

		// String proNumber = "171-8003948";

		// Goto Claims
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();
		claimsPage.clickOnFileClaimTab();
		fileAClaimPage.clickOnFileClaimButton();

		// Enter the Pro number for which recent FB's are created
		fileAClaimPage.enterPRONumber(proNumber);

		testUtil.setHardWait(2000);
		// Select Loss in the claim type
		fileAClaimPage.selectClaimType("Loss");
		testUtil.setHardWait(1000);

		// Verify PRO Number and claim Type section fields are prefilled
		fileAClaimPage.verifyPRONumsAndClaimTypeArePreFilled();

		// Select my info for claimant
		myEstesClaimsPage.clickOnClaimantIMyInfoLink();

		// Verify fields are prefilled for claimant
		fileAClaimPage.verifyClaimantInfoFieldsArePrefilled();

		// Click on remitInfo Same as claimant
		myEstesClaimsPage.clickOnRemitInfoSameAsClaimantLink();

		// Verify fields are prefilled for Remit
		fileAClaimPage.verifyRemitInfoFieldsArePrefilled();

		// From the Upload Freight Documents section, Click on Choose browse link next
		// to each document type and upload a 10 MB file

		// VendorInvoice
		fileAClaimPage.clickOnVendorInvoiceBrowseLink(sample10MBFile);

		// BOL
		fileAClaimPage.clickOnBOLBrowseLink(sample10MBFile);

		// Estes Freight Bill/Invoice
		fileAClaimPage.clickOnEstesFBBrowseLink(sample10MBFile);

		// Other
		fileAClaimPage.clickOnOtherBrowseLink(sample10MBFile);

		// Validate error messages for upload freight documents section
		fileAClaimPage.verifyErrorMessages("Upload Freight Documents");

		// From the Claim Details section, click on the Browse link per document upload
		// field and upload a 10 MB file per field

		// Enter claim amount
		myEstesClaimsPage.enterTotalCost(claimAmt);

		// Click on add deatil button
		fileAClaimPage.clickOnAddDetail();

		// Upload Document1
		fileAClaimPage.clickOnDocument1BrowseLink(sample10MBFile);
		// Upload Document2
		fileAClaimPage.clickOnDocument2BrowseLink(sample10MBFile);
		// Upload Document3
		fileAClaimPage.clickOnDocument3BrowseLink(sample10MBFile);
		// Upload Document4
		fileAClaimPage.clickOnDocument4BrowseLink(sample10MBFile);
		// Upload Document5
		fileAClaimPage.clickOnDocument5BrowseLink(sample10MBFile);

		// Validate error messages for Claim Details section
		fileAClaimPage.verifyErrorMessages("Claim Details");

		// From the Upload Freight Documents section and Claim Details section, upload a
		// file totaling 50 MB

		fileAClaimPage.clickOnVendorInvoiceBrowseLink(sample9MBFile);

		fileAClaimPage.clickOnBOLBrowseLink(sample9MBFile);

		fileAClaimPage.clickOnDocument1BrowseLink(sample9MBFile);

		fileAClaimPage.clickOnDocument2BrowseLink(sample9MBFile);

		fileAClaimPage.clickOnDocument3BrowseLink(sample9MBFile);

		fileAClaimPage.clickOnDocument4BrowseLink(sample5MBFile);

		fileAClaimPage.clickOnDocument5BrowseLink(sample5MBFile);

		// Check the acknowledgment box
		myEstesClaimsPage.clickOnAcknowledgeButton();

		// click on submit button
		fileAClaimPage.clickOnSubmitButton();

		testUtil.setHardWait(3000);

		// Validate the expected error message

		fileAClaimPage.validateErrorMessage(errorMessage2);

	}
	
	//This test is on hold and should not be executed until further notice. Dee/Kathy
	/*
	 * Claims Inquiry - Verify when required fields are left blank error message displays
	 */
	@Test(enabled = false, priority = 9)

	public void executeQZ_7628() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();

		//Step 3: Click on manage link and then click on Claims
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();

		//Step 4: Select PRO number from the search by drop down
		claimsPage.selectSearchBy();
		claimsPage.selectPRONumber();
		testUtil.setHardWait(2000);
		//Step 5:Click the PRO Numbers 
		claimsPage.clickOnPRONumberSearchBox();
		testUtil.setHardWait(2000);

		claimsPage.clickOutsideOfSearchBox();

		// Verify error message displays
		claimsPage.verifyErrorMessageFromRequirdFields();

		testUtil.setHardWait(3000);

		//Step 7: Select Your Reference Number from search by drop down
		claimsPage.selectSearchBy("Your Reference Number");
		// Verify error message displays
		claimsPage.verifyErrorMessageFromRequirdFields();
		
		//Step 8: Select Estes Claim Number from search by drop down
		claimsPage.selectSearchBy("Estes Claim Number");

		// Verify error message displays
		claimsPage.verifyErrorMessageFromRequirdFields();

		//Step 9: Select Date Range from search by drop down
		claimsPage.selectSearchBy("Date Range");

		//Step 10: Select custom from field date range dropdown
		claimsPage.selectFieldDateRange("Custom");
		testUtil.setHardWait(2000);

		//Step 11: leave start date and end date blank

		claimsPage.clickOnLookupButton();
		testUtil.setHardWait(500);
		// Verify error message displays
		claimsPage.verifyErrorMessageFromRequirdFields();

	}

}
