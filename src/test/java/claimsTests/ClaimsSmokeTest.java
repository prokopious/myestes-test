package claimsTests;


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

public class ClaimsSmokeTest extends TestBase {

	private Logger logger = Logger.getLogger(ClaimsRegressionTest.class.getName());
	
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	ClaimsPage claimsPage = new ClaimsPage();
	FileAClaimPage fileAClaimPage = new FileAClaimPage();
	SQLDataList sqlDataList = new  SQLDataList();
	MyEstesClaimsPage myEstesClaimsPage = new MyEstesClaimsPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetMyEstesManagementToolPage eNetMyEstesManagementToolPage = new ENetMyEstesManagementToolPage();
	ENetClaimsEntryPage eNetClaimsEntryPage = new ENetClaimsEntryPage();
	ENetClaimsEntryDetailPage eNetClaimsEntryDetailPage = new ENetClaimsEntryDetailPage();
	
	/*******************************TESTS *******************************/

	//this test need to remaind turned off due to changes. this has HOLD Label
	
	/*
	 * Claims Filing - Local Account -Verify customer can file a Damage claim
	 */

	//This test is being run locally.

	@Test(enabled = false, priority = 1)

	public void executeQZ_3247() throws Exception, InterruptedException {

		String additionalComments = "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine.";
		String csvFilePath = TestUtil.getCurrentWorkingPath() + "\\Downloads\\DummyFile.xls";
		String fileName = "DummyFile.xls";
		String Qty = "10";
		String claimAmt = "225.00";
		String Qty2 = "5";
		String claimAmt2 = "133.84";
		String totalClaimAmt = "358.84";

		// Login to myestes with smokelocal credentials
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username12);
		myEstesLoginPage.enterPassword(password12);
		myEstesLoginPage.clickOnLoginButton();

		// Recent Shipment - Shipper
		testUtil.setHardWait(1000);
		myEstesRecentShipmentsPage.ClickOnRSShipperRadioButton();
		myEstesRecentShipmentsPage.clickOnSort();

		// Get pro number
		String proNumber = myEstesRecentShipmentsPage.getPRONumForFirstRow();

		String pro = proNumber.split("-")[1].trim();
		System.out.println(pro);
		
		//String proNumber = "171-8001667";
		//String pro = "8001667";

		// Goto Claims
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();
		claimsPage.clickOnFileClaimTab();
		fileAClaimPage.clickOnFileClaimButton();

		// Enter the Pro number for which recent FB's are created
		fileAClaimPage.enterPRONumber(proNumber);

		testUtil.setHardWait(2000);
		// Select Loss in the claim type
		fileAClaimPage.selectClaimType("Damage");

		fileAClaimPage.selectFreightType("Boxes");
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

		// Click on invoice browse button to upload a dummy file
		fileAClaimPage.clickOnVendorInvoiceBrowseLink(csvFilePath);


		// Enter description in Item-1
		myEstesClaimsPage.writeLossDamageDesc();

		// Enter quantity
		myEstesClaimsPage.enterQty(Qty);

		// Enter claim amount
		myEstesClaimsPage.enterTotalCost(claimAmt);

		// Enter description in Item-2
		myEstesClaimsPage.writeLossDamageDescItem2();

		// Enter quantity in Item2
		myEstesClaimsPage.enterQtyInItem2(Qty2);

		// Enter claim amount in Item-2
		myEstesClaimsPage.enterTotalCostInItem2(claimAmt2);

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
		testUtil.setHardWait(2000);
		eNetClaimsEntryDetailPage.verifyPRONumIsDisplayed(pro);
		eNetClaimsEntryDetailPage.verifyClaimAmountIsDisplayed(totalClaimAmt);

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

	// According to Kathy- this test should not be excuted.
	/*
	 * Claims Inquiry - Local Account - Verify customer can search and view a claim
	 * by PRO Number.
	 */
	@Test(enabled = false, priority = 2)

	public void executeQZ_7627() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);// shiper number is:71788618 for smokelocal
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(500);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnClaims();
		testUtil.setHardWait(500);
		claimsPage.selectPRONumber();
		claimsPage.enterPRONumber("1718007054");
		
		claimsPage.clickOnLookupButton();
		testUtil.setHardWait(2000);
		claimsPage.verifyClaimNumberField();
		claimsPage.verifyPRONumberField();
		claimsPage.verifyStatusField();
		claimsPage.verifyDateField();
		claimsPage.verifyRefNumberField();
		claimsPage.verifyClaimAmountField();
		claimsPage.verifyRemitToField();

	}

}

