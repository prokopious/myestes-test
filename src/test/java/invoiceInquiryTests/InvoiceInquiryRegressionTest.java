package invoiceInquiryTests;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetInvoiceInquiryPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetReportsPage;
import invoiceInquiryPages.MyEstesInvoiceInquiryPage;
import invoiceInquiryPages.MyEstesPayInvoicesPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import testBase.TestBase;
import util.TestUtil;

public class InvoiceInquiryRegressionTest extends TestBase {
	
	private Logger logger = Logger.getLogger(InvoiceInquiryRegressionTest.class);
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesInvoiceInquiryPage myEstesInvoiceInquiryPage = new MyEstesInvoiceInquiryPage();
	MyEstesPayInvoicesPage MyEstesPayInvoicesPage = new MyEstesPayInvoicesPage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetReportsPage eNetReportsPage = new ENetReportsPage();
	ENetInvoiceInquiryPage eNetInvoiceInquiryPage =  new ENetInvoiceInquiryPage();

	
	/******************************* TESTS *******************************/

	/**
	 * This test passed on 6/28/22
	 * 
	 * Invoice Inquiry - Verify error message displays if the account does not have
	 * any open invoice.
	 */
	@Test(enabled = true, priority = 1) 
	
	public void executeQZ_7536() throws InterruptedException {
		
		String expectedAlert = "There are no open invoices for this account at this time.";
		
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		//Click Manage--> Invoice Inquiry
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnInvoiceInquiryLink();  
		testUtil.setHardWait(3000);
		myEstesInvoiceInquiryPage.verifyPageTitle();
		
		//Validate Error message
		String actualAlert = myEstesInvoiceInquiryPage.captureAlertMessage();
		TestUtil.verifyText(actualAlert, expectedAlert);
	}
	
	/*
	 * Invoice Inquiry - Verify error message displays when required fields are left
	 * blank.
	 */
	@Test(enabled = true, priority = 2)
	
	public void executeQZ_7539() throws InterruptedException {
		
		//Login to MyEstes
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();
		
		//Click Manage--> Invoice Inquiry
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnInvoiceInquiryLink(); 
		myEstesInvoiceInquiryPage.verifyPageTitle();
		testUtil.setHardWait(1000);
		//Validating required field error 
		myEstesInvoiceInquiryPage.clickOnSearch();
		myEstesInvoiceInquiryPage.verifyRequiredFieldErrorMessage();
		myEstesInvoiceInquiryPage.clickOnSearchBy();
		myEstesInvoiceInquiryPage.selectStatementNumber();
		myEstesInvoiceInquiryPage.clickOnSearch();
		myEstesInvoiceInquiryPage.verifyRequiredFieldErrorMessage();
		myEstesInvoiceInquiryPage.clickOnSearchBy();
		myEstesInvoiceInquiryPage.selectPONumber();
		myEstesInvoiceInquiryPage.clickOnSearch();
		myEstesInvoiceInquiryPage.verifyRequiredFieldErrorMessage();
		myEstesInvoiceInquiryPage.clickOnSearchBy();
		myEstesInvoiceInquiryPage.selectBOLNumber();
		myEstesInvoiceInquiryPage.clickOnSearch();
		myEstesInvoiceInquiryPage.verifyRequiredFieldErrorMessage();
	}
	
	

	/*
	 * Invoice Inquiry - Verify error message displays if the invoice is not billed
	 * yet.
	 */
	
	@Test(enabled = true, priority = 3)
	
    public void executeQZ_8935() throws InterruptedException {
           
           myEstesHomePage.clickOnMyEstes();
           myEstesHomePage.clickOnLogin();
           myEstesLoginPage.enterUserName("RFARLEIGH");
           myEstesLoginPage.enterPassword("american1");
           testUtil.setHardWait(1000);
           myEstesLoginPage.clickOnLoginButton();
           
           myEstesHomePage.clickOnManageLink();
           myEstesHomePage.clickOnInvoiceInquiryLink(); 
           myEstesInvoiceInquiryPage.verifyPageTitle();
                        
           myEstesInvoiceInquiryPage.enterPRONumber("0031900186");
           myEstesInvoiceInquiryPage.clickOnSearch();
           myEstesInvoiceInquiryPage.verifyErrorMessage();
           
           myEstesInvoiceInquiryPage.clickOnClearButton();
           
           myEstesInvoiceInquiryPage.enterPRONumber("0031900186");
           
           myEstesInvoiceInquiryPage.enterPRONumber("0177125733");
           
           myEstesInvoiceInquiryPage.enterPRONumber("0031900186");
           
           myEstesInvoiceInquiryPage.enterPRONumber("0177125733");
           
           myEstesInvoiceInquiryPage.enterPRONumber("0031900186");
                        
           myEstesInvoiceInquiryPage.clickOnSearch();
           myEstesInvoiceInquiryPage.verifyErrorMessage();

	}
	
	/**
	 *passedon 8/5/222
	 * 
	 * Web - Invoice Inquiry - Viewing Invoice Aging Summary
	 */
	@Test(enabled = true,priority=4)
	
	public void executeQZ_8349() { //To be reviewed
		
		String[] reportsHeader=  {"Activity Recap", "Billing Reports", "Corporate", "Estes Integrated", "Fleet Services", 
                "Operations", "Sales", "Shipment Charge Alert", "Terminal", "Traffic", "Weight and Research"};

		
		//Login to eNet application
		driver.get(url2);
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
		
		// Navigate to Reports
		eNetHomePage.clickOnReportsTab();
		eNetReportsPage.switchToFrameElement();
		testUtil.setHardWait(1000);
		eNetReportsPage.verifyReportsHeader(reportsHeader);
		
		// Navigate to Invoice Inquiry
		eNetHomePage.clickOnApplicationWithoutFrame();
		eNetApplicationsPage.clickOnInvoiceInquiryLink();
		eNetInvoiceInquiryPage.switchToFrameElement();
		testUtil.setHardWait(2000);
		//	eNetInvoiceInquiryPage.clickOnSalesDistrictNumberLink("2");
		eNetInvoiceInquiryPage.clickOnSalesDistrictNumberLink("Districts");
		eNetInvoiceInquiryPage.clickOnSalesDistrictNumberLink("BOBBY SIMONS");
		//	eNetInvoiceInquiryPage.clickOnSalesPersonNumberLink("18408");
		eNetInvoiceInquiryPage.clickFirstSalesPersonNumberLink(); // newly created
		//	eNetInvoiceInquiryPage.clickOnCustomerNameLink("B006907");
		eNetInvoiceInquiryPage.clickFirstCustomerNameLink(); // newly created
		eNetInvoiceInquiryPage.clickOnLinkToImages();
		// As per test case, Images does not displayed so ignored step 9 & 10 validation
		}
		
		}
