package accountReceivablesTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetBillQueue;
import eNetPages.ENetCollectionsReportPage;
import eNetPages.ENetExchangeRateMaintenancePage;
import eNetPages.ENetHomePage;
import eNetPages.ENetInvoiceBatchUploadPage;
import eNetPages.ENetInvoiceInquiryPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetStatementRetrieverPage;
import testBase.TestBase;
import util.TestUtil;

public class AccountReceivablesSmokeTest extends TestBase {

	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetStatementRetrieverPage eNetStatementRetrieverPage = new ENetStatementRetrieverPage();
	ENetExchangeRateMaintenancePage eNetExchangeRateMaintenancePage = new ENetExchangeRateMaintenancePage();
	ENetInvoiceBatchUploadPage eNetInvoiceBatchUploadPage = new ENetInvoiceBatchUploadPage();
	ENetInvoiceInquiryPage eNetInvoiceInquiryPage = new ENetInvoiceInquiryPage();
	ENetBillQueue eNetBillQueue = new ENetBillQueue();
	ENetCollectionsReportPage eNetCollectionsReportPage =new ENetCollectionsReportPage();
	
	/*********************** METHODS ************************/

	
	// this test is passing locally but sometimes failing on Jenkins due to file download 
	/**
	 * 
	 * 
	 * 01.01.03.01 - Applications_Accounts Receivable_Invoice Batch Upload
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_7187() {

		// step1:From Internet Explorer Browser, open the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		driver.get(url2);

		// step2: Login with the following credentials:
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From eNet Home page, click on the APPLICATIONS folder VIEW ALL icon
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");

		// step4:From the Applications page under Accounts Receivable section, click
		// Invoice Batch Upload
		eNetApplicationsPage.clickInvoiceBatchUpload();
		testUtil.switchToFrame("mainpage");

		// Verify Invoice batch upload page
		String[] fields = { "Email error report to:", "Email invoices to:", "FROM Email Address:", "Subject Line:",
				"Choose Pro Format:", "Choose file to upload:", "" };
		eNetInvoiceBatchUploadPage.validatePageTitle("Invoice Batch Upload");
		eNetInvoiceBatchUploadPage.verifyTableFields(fields);

		// step5: FOR PRODUCTION: Do not enter any values. Click Submit button.
		String errorEmail = "ldowler@estes-express.com";
		String emailfrom = "qatest@estes-express.com";
		
		//String path = System.getProperty("user.dir") + "\\src\\main\\resources\\TestDataFolder\\UploadTemplate.xls";
		String path = System.getProperty("user.dir") + "/src/main/resources/TestDataFolder/UploadTemplate.xls";

		eNetInvoiceBatchUploadPage.enterEmailErrors(errorEmail);
		eNetInvoiceBatchUploadPage.enterEmailInvoice(emailfrom);
		eNetInvoiceBatchUploadPage.enterEmailFrom(emailfrom);
		eNetInvoiceBatchUploadPage.enterSubjectInfo("Test qz-7187");
		eNetInvoiceBatchUploadPage.selectProFormat1();
		//testUtil.setHardWait(1000);
		eNetInvoiceBatchUploadPage.uploadFile(path);
		eNetInvoiceBatchUploadPage.clickonSubmit();

		// Verify Success message
		eNetInvoiceBatchUploadPage.validateSuccessMessage("Request submitted successfully");

		// step6: Logout and close browser.
		eNetInvoiceBatchUploadPage.clickOnLogout();
		eNetInvoiceBatchUploadPage.clickOnLogoutIntranet();

	}

	/**
	 * This test was tested and passed on 6/9/2022 for Jenkins failure
	 * 
	 *  Applications_Accounts Receivable_Statement Retriever_View
	 */

	@Test(enabled = true, priority = 2)
	public void executeQZ_7189() {

		// step1:Open the following link:<http://enetqa.estesinternal.com/>
		driver.get(url2);

		// step2: Log into *eNet*
		// Username: qaenet02
		// Password: qaenet02
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton();

		// step3: From the eNet home page, Click on the Applications folder VIEW ALL
		eNetHomePage.validatePage();
		eNetHomePage.clickViewAll("Applications");

		// step4: From the Applications page under the Imaging heading/section, click
		// link: Statement Retriever
		eNetApplicationsPage.clickStatementRetriever();

		// Screen to enter Statement # is displayed.
		eNetStatementRetrieverPage.validatePageTitle("Enter statement number.");

		// step5:From the "Enter statement number." page, enter Statement number:6381713
		// // using different data as it is not working
		eNetStatementRetrieverPage.enterstatementNumber("4533332");

		// step6: Click the VIEW button and click SUBMIT button.
		eNetStatementRetrieverPage.selectViewRadioBtn();
		eNetStatementRetrieverPage.clickOnSubmitBtn();
		testUtil.setHardWait(1000);
		// Excel spreadsheet opens and Statement data is displayed
		try {
			testUtil.verifyIsFileDownloaded("./Downloads", "TB004442_RPT.XLS");

			int StatementNum = testUtil.getNumericCellValue("./Downloads/TB004442_RPT.XLS", 1,0);
			Assert.assertEquals(StatementNum, 4533332);
		testUtil.deleteFilesFromFolder("./Downloads", "TB004442_RPT.XLS");
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		// step7: Logout and close browser.
		eNetStatementRetrieverPage.clickOnLogout();
		eNetStatementRetrieverPage.clickOnLogoutIntranet();

	}

	//This test is failing on Jenkins due to FileNotFound error. Its running locally
	
	@Test(enabled = false, priority = 3)

	public void executeQZ_7192()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		// Step#1 : Open within the Internet Explorer Browser, the following link:
		// https://estesexpress.atlassian.net/browse/QZ-6808
		// http://enetqa.estesinternal.com/login.jsp
		driver.get(url2);

		// Step#2 : Login with the following credentials:
		// User ID: qaenet02, Password: qaenet02
		eNetLoginPage.enterUserID(username19);
		eNetLoginPage.enterUserPassword(password19);
		eNetLoginPage.clickOnLoginButton();
		// The ENet home page is displayed
		eNetHomePage.verifyPageTitleQaenet02();

		// Step#3 : From the Applications page under the Pricings heading/section click
		// on the following link:
		eNetHomePage.clickOnApplicationsTab();
		eNetApplicationsPage.verifyPageTtl();

		// Step#4 : From the Applications page under the ACCOUNTS RECEIVABLE section
		// click on the following link:
		// Bill Queue
		eNetApplicationsPage.clickOnBillQueue();
		// Bill Queue report displays with rows of data.
		eNetBillQueue.verifyPageTitle();
		eNetBillQueue.verifyBillQueueTableDataPresent();

		// Step#5 : Click on the Excel link and VIEW EXCEL DOCUMENT/ ALL link
		eNetBillQueue.clickOnExcelLink();
		eNetBillQueue.clickOnExcelViewAll();
		// Entire report is displayed in excel spreadsheet
	//	testUtil.setHardWait(1000);
		eNetBillQueue.verifyAllDataPresentOrNot("BillQueAllExcelServlet.xls", "Pros");

		// Step#6 : Click on one single row. Click on the Excel link and VIEW EXCEL
		// DOCUMENT SELECTED link
		eNetBillQueue.clickOnClose();
		eNetBillQueue.selectOneRecord();
		Map<Integer, List<String>> recordsFetched = new HashMap<Integer, List<String>>();
		recordsFetched = eNetBillQueue.fetchRecordDetails();
		eNetBillQueue.clickOnExcelLink();
		eNetBillQueue.clickOnSelected();
		// Only selected lines will display in an excel spreadsheet
		eNetBillQueue.verifySelectedDataPresentOrNot("BillQueExcelServlet.xls", "Pros", recordsFetched);

		// Step#7 : Click on two individual rows and the EXCEL link. Click on the EMAIL
		// EXCEL DOCUMENT SELECTED link Enter Email Address and click SEND
		eNetBillQueue.clickOnClose();
		eNetBillQueue.selectMultipleRecords(2);
		eNetBillQueue.clickOnExcelLink();
		eNetBillQueue.clickOnEmailExcelSelected();
		eNetBillQueue.enterEmailAddress("mosammat.taslima@estes-express.com");
		// Open email to verify excel document was properly sent.. Confirmation message
		// displays "Email has been sent."
		// This step needs to be verified manually.
	}

	
	/**
	 * 
	 * 01.01.00.12 - Collections Reports
	 * @throws InterruptedException
	 */
	
	@Test(enabled = true, priority = 4)

	public void executeQZ_5341() throws InterruptedException {
		//Step#1 : Open within the Internet Explorer Browser, the following link:
		//http://enetqa.estes-express.com/login.jsp
				driver.get(url2);
						
		//Step#2 : Login with the following credentials:
		//User ID: qaenet02, Password: qaenet02
				eNetLoginPage.enterUserID(username12);
				eNetLoginPage.enterUserPassword(password12);
				eNetLoginPage.clickOnLoginButton();
		//The ENet home page is displayed
				eNetHomePage.verifyPageTitleQaenet02();
				
		//Step#3 : From the eNet home page, Click on the Application folder VIEW ALL icon (Application Tab Link)
				eNetHomePage.clickOnApplicationsTab();
		
		//Step#4 : From the Applications page under the Accounts Receivable heading/section, click on the following link:
		//Collections Reports
				eNetApplicationsPage.verifyPageTtl();
				eNetApplicationsPage.clickCollectionsReports();
				testUtil.setHardWait(3000);
		//Step#5 : From the Collections Reports /  the Collector Activity Sequel page, 
		//verify there is at least one row of data and at least some of the columns show values other than zero.
				eNetCollectionsReportPage.verifyPageTitle();
				eNetCollectionsReportPage.verifyCollectionActivityReportTableIsNotEmpty();
				
		//Step#6 : From the Collections Reports / Collector Activity Sequel page, 
		//enter account number in the Account Number field and email: 
		//5018240 (tester outlook email address)
				eNetCollectionsReportPage.enterAccountNumber("5018240");
				eNetCollectionsReportPage.enterEmailAddress("qatest@estes-express.com");
				eNetCollectionsReportPage.clickOnSubmit();
		//Step#7 : From within the Open AR Report entry box, click the Submit button
		//Email is received with Undiscounted Freight Report attached.
				eNetCollectionsReportPage.enterFrieghtAccountNumber("5018240");
				eNetCollectionsReportPage.enterFrieghtEmailAddress("qatest@estes-express.com");
				eNetCollectionsReportPage.clickOnFrieghtSubmit();
				
		//Step#8 : Logout and close browser.
				eNetCollectionsReportPage.clickOnLogout();
				eNetCollectionsReportPage.clickOnLogoutConfirmation();
	}

}

