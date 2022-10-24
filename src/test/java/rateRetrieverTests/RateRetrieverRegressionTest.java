package rateRetrieverTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetExchangeRateMaintenancePage;
import eNetPages.ENetHomePage;
import eNetPages.ENetInvoiceBatchUploadPage;
import eNetPages.ENetInvoiceInquiryPage;
import eNetPages.ENetLTLQuoteHistoryDetailPage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetStatementRetrieverPage;
import eNetPages.ENetTimeCriticalRateQuotePage;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.CommandEntryScreen;
import jagacyVT.screens.DisplayCommentsScreen;
import jagacyVT.screens.DisplayRequestServiceInfoScreen;
import jagacyVT.screens.GoldMedalMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.WorkWithRequestScreen;
import testBase.TestBase;
import timeCriticalRateQuoteTests.TimeCriticalRateQuoteRegressionTest;
import util.TestUtil;

public class RateRetrieverRegressionTest extends TestBase{
	
	private Logger logger = Logger.getLogger(RateRetrieverRegressionTest.class);
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
	ENetLTLRateRequestPage eNetLTLRateRequestPage = new ENetLTLRateRequestPage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetTimeCriticalRateQuotePage eNetTimeCriticalRateQoutePage=new ENetTimeCriticalRateQuotePage();
	ENetLTLQuoteHistoryDetailPage eNetLTLQuoteHistoryDetailPage=new ENetLTLQuoteHistoryDetailPage();
	ENetInvoiceInquiryPage eNetInvoiceInquiryPage= new ENetInvoiceInquiryPage();
	ENetInvoiceBatchUploadPage eNetInvoiceBatchUploadPage= new ENetInvoiceBatchUploadPage();
	ENetStatementRetrieverPage	eNetStatementRetrieverPage= new ENetStatementRetrieverPage();
	ENetExchangeRateMaintenancePage  eNetExchangeRateMaintenancePage= new ENetExchangeRateMaintenancePage();
	
	/******************************* TESTS *******************************/
	/*
	 * Rate Retriever - Verify when Overlength Freight (28.00' to 39.99')
	 * accessorial is selected charges are displayed in Quote Summary and Guaranteed
	 * By 10 AM, 12 PM, and 5 PM are not calculated Contact Us instead
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_9754()
			throws InterruptedException {

		String account = "9451455";
		String accName = "Overlength Freight (28.00' to 39.99')";
		String charge = "$0.00";

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click Rate Retriever from Customer Service list
		eNetApplicationsPage.clickOnRateRetrieverLink();

		// Step 5: Enter contact and routing information
		eNetLTLRateQuotePage.enterAccountNumber(account);
		eNetLTLRateQuotePage.selectRole("Shipper");
		eNetLTLRateQuotePage.selectTerm("Pre-paid");
		eNetLTLRateQuotePage.enterOriginZip("23230");
		eNetLTLRateQuotePage.enterDestinationZip("30307");

		// Step 6: Enter commodities in commodity line 1
		eNetLTLRateQuotePage.selectClass("50");
		eNetLTLRateQuotePage.enterWeight("1500");
		eNetLTLRateQuotePage.enterDesc("QZ-9754 Long40");

		// Step 7: Enter commodities in commodity line 2
		eNetLTLRateQuotePage.selectClass1("70");
		eNetLTLRateQuotePage.enterWeight1("470");
		eNetLTLRateQuotePage.enterDesc1("QZ-9754 Long40");

		// Step 8: Click Submit
		eNetLTLRateQuotePage.clickOnSubmitButton();

		// Step 9: Verify guaranteed rates are calculated
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");

		// Step 10: Click on Update Quote
		eNetLTLRateRequestPage.clickOnUpdateQuote();

		// Step 11: Select Overlength Freight (28.00' to 39.99') checkbox accessorial
		eNetLTLRateQuotePage.clickOnOverlengthAccessorial(accName);

		// Step 12: Click Submit
		eNetLTLRateQuotePage.clickOnSubmitButton();

		// Step 13: Record Quote#
		eNetLTLRateRequestPage.recordQuoteNo();

		// Step 14: Verify *Overlength Freight (28.00' to 39.99')* is displayed with
		// Charges. (Charges could be $0.00)
		eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(accName, charge);

		// Step 15: Verify Contact us displayed for guaranteed services
		eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
		eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");
		eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed5");
	}
	
	/*
	 * Rate Retriever - Verify when Overlength Freight (12.00' to 19.99') accessorial is selected charges are displayed in 
	 * Quote Summary and Guaranteed By 10 AM, 12 PM, and 5 PM are not calculated Contact Us instead 
	 */
	
	@Test(enabled = true, priority = 2)
	public void executeQZ_916() throws InterruptedException {
	
		 String acctNo = "9451455";
		 String accessorialName ="Overlength Freight (12.00' to 15.99')";
		 String charge = "$0.00";
		 
		 //Step 1: Open enet application
		 driver.get(url2);
		 
		 //Step 2: Login to eNet app
	     eNetLoginPage.enterUserID(username5);
	     eNetLoginPage.enterUserPassword(password5);
	     eNetLoginPage.clickOnLoginButton();
	     
	     //Step 3: Click on Applications 
	     eNetHomePage.clickOnApplicationsTab();
	     
	     //Step 4: Click on Rate Retriever link from customer service section
	     eNetHomePage.clickOnRateRetrieverLink();
	     
	     //Step 5: Enter data in Contact and Routing information
	     eNetLTLRateQuotePage.enterAccountNumber(acctNo);
	     eNetLTLRateQuotePage.selectRole("Shipper");
	     eNetLTLRateQuotePage.selectTerm("Collect");;
	     eNetLTLRateQuotePage.enterOriginZip("30307");
	     eNetLTLRateQuotePage.enterDestinationZip("60007");
	     
	     //Step 6: Enter Commodities details in line 1
	     eNetLTLRateQuotePage.selectClass("50");
	     eNetLTLRateQuotePage.enterWeight("800");
	     eNetLTLRateQuotePage.enterDesc("QZ-916 Long 20");
	     
	     //Step 7: Enter Commodities details in line 2 
	     eNetLTLRateQuotePage.selectClass1("60");
	     eNetLTLRateQuotePage.enterWeight1("150");
	     eNetLTLRateQuotePage.enterDesc1("QZ-916 Long 20");
	     
	     //Step 8: Click on Submit button
	     eNetLTLRateQuotePage.clickOnSubmitButton();
	     
	     //LTL Rate Request page displays
	     eNetLTLRateRequestPage.verifLTLRateRequestPage();
	     
	     //Step 9: Verify Guaranteed by 10 AM,12PM and 5 PM are calculated
	     eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
	     eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
	     eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");
	     
	     //Step 10: Click on Update Quote
	     eNetLTLRateRequestPage.clickOnUpdateQuote();
     driver.switchTo().defaultContent();
	     
	     //LTL Rate Quote page displays and populated with keyed in data
	     eNetLTLRateQuotePage.verifyPage();
	     eNetLTLRateQuotePage.verifyAccountNumber(acctNo);
	     eNetLTLRateQuotePage.verifySelectedValue("my_role","Shipper");
	     eNetLTLRateQuotePage.verifySelectedValue("terms","Collect");
	     eNetLTLRateQuotePage.verifyEnteredValue("zipOrigin", "30307");
	     eNetLTLRateQuotePage.verifyEnteredValue("zipDestination", "60007");
	     eNetLTLRateQuotePage.verifySelectedValue("commClass0","50");
	     eNetLTLRateQuotePage.verifyEnteredValue("weight0", "800");
	     eNetLTLRateQuotePage.verifyEnteredValue("desc0", "QZ-916 Long 20");
	     eNetLTLRateQuotePage.verifySelectedValue("commClass1","60");
	     eNetLTLRateQuotePage.verifyEnteredValue("weight1", "150");
	     eNetLTLRateQuotePage.verifyEnteredValue("desc1", "QZ-916 Long 20");
	     

	     
	     //Step 11: Click on Over-length Freight (20.00' to 27.99') checkbox
	     eNetLTLRateQuotePage.clickOnOverlengthAccessorial(accessorialName);
	     
	     //Step 12: Click on Submit button
	     eNetLTLRateQuotePage.clickOnSubmitButton();
	     
	     //Step 13: Record quote#
	     String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();
	     
	     //Step 14: Verify Over-length Freight (20.00' to 27.99') displayed with charges
	     eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(accessorialName, charge);
	     
	     //Step 15: Verify Guaranteed displays Contact Us
	     eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
	     eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");
	     eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed5");
	     
	     //Step 16: Logout
	     eNetHomePage.clickOnLogout();
	     
	     //Step 17: Confirm logout
	     eNetHomePage.clickOnLogOutButton();
		
	}
	
	/*
	 * Rate Retriever - Verify when Overlength Freight (40 feet or greater) accessorial is selected charges are displayed in 
	 * Quote Summary and Guaranteed By 10 AM, 12 PM, and 5 PM are not calculated Contact Us instead
	 */
	
	@Test(enabled = true, priority = 3)
	public void executeQZ_918() throws InterruptedException {
		
		 String acctNo = "9451455";
		 String accessorialName ="Overlength Freight (40 feet or greater)";
		 String charge = "$0.00";
		 
		 //Step 1: Open enet application
		 driver.get(url2);
		 
		 //Step 2: Login to eNet app
	     eNetLoginPage.enterUserID(username5);
	     eNetLoginPage.enterUserPassword(password5);
	     eNetLoginPage.clickOnLoginButton();
	     
	     //Step 3: Click on Applications 
	     eNetHomePage.clickOnApplicationsTab();
	     
	     //Step 4: Click on Rate Retriever link from customer service section
	     eNetHomePage.clickOnRateRetrieverLink();
	     
	     //Step 5: Enter data in Contact and Routing information
	     eNetLTLRateQuotePage.enterAccountNumber(acctNo);
	     eNetLTLRateQuotePage.selectRole("Shipper");
	     eNetLTLRateQuotePage.selectTerm("Pre-paid");;
	     eNetLTLRateQuotePage.enterOriginZip("30307");
	     eNetLTLRateQuotePage.enterDestinationZip("77007");
	     
	     //Step 6: Enter Commodities details in line 1
	     eNetLTLRateQuotePage.selectClass("50");
	     eNetLTLRateQuotePage.enterWeight("1200");
	     eNetLTLRateQuotePage.enterDesc("QZ-918 40 feet or greater");
	     
	     //Step 7: Enter Commodities details in line 2 
	     eNetLTLRateQuotePage.selectClass1("60");
	     eNetLTLRateQuotePage.enterWeight1("700");
	     eNetLTLRateQuotePage.enterDesc1("QZ-918 40 feet or greater");
	     
	     //Step 8: Click on Submit button
	     eNetLTLRateQuotePage.clickOnSubmitButton();
	     
	     //LTL Rate Request page displays
	     eNetLTLRateRequestPage.verifLTLRateRequestPage();
	     
	     //Step 9: Verify Guaranteed by 10 AM,12PM and 5 PM are calculated
	     eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
	     eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
	     eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");
	     
	     //Step 10: Click on Update Quote
	     eNetLTLRateRequestPage.clickOnUpdateQuote();
	     
	     driver.switchTo().defaultContent();
	     
	     //LTL Rate Quote Page displayed and populated with keyed in data
	     eNetLTLRateQuotePage.verifyPage();
	     eNetLTLRateQuotePage.verifySelectedValue("my_role","Shipper");
	     eNetLTLRateQuotePage.verifySelectedValue("terms","Pre-paid");
	     eNetLTLRateQuotePage.verifyEnteredValue("zipOrigin", "30307");
	     eNetLTLRateQuotePage.verifyEnteredValue("zipDestination", "77007");
	     eNetLTLRateQuotePage.verifySelectedValue("commClass0","50");
	     eNetLTLRateQuotePage.verifyEnteredValue("weight0", "1200");
	     eNetLTLRateQuotePage.verifyEnteredValue("desc0", "QZ-918 40 feet or greater");
	     eNetLTLRateQuotePage.verifySelectedValue("commClass1","60");
	     eNetLTLRateQuotePage.verifyEnteredValue("weight1", "700");
	     eNetLTLRateQuotePage.verifyEnteredValue("desc1", "QZ-918 40 feet or greater");

	     //Step 11: Click on Over-length Freight (40 Feet or greater) checkbox
	     eNetLTLRateQuotePage.clickOnOverlengthAccessorial(accessorialName);
	     
	     //Step 12: Click on Submit button
	     eNetLTLRateQuotePage.clickOnSubmitButton();
	     eNetLTLRateRequestPage.verifyPage();
	     //Step 13: Record quote#
	     String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();
	     
	     //Step 14: Verify Over-length Freight (20.00' to 27.99') displayed with charges
	     eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(accessorialName, charge);
	     
	     //Step 15: Verify Guaranteed displays Contact Us
	     eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
	     eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");
	     eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed5");
	     
	     //Step 16: Logout
	     eNetHomePage.clickOnLogout();
	     
	     //Step 17: Confirm logout
	     eNetHomePage.clickOnLogOutButton();
	}
	
	/*
	 * ENet Rate Retriever - Verify Tariff Override functionality
	 */
	
	@Test(enabled = true, priority = 4)
	public void executeQZ_932() throws InterruptedException {
		
		 String errorText = "Attention: Tariff Num/Name/Suffix is invalid, inactive, or not in-effect for values entered.";                                                                                                                                                                                                                         
		 
		 //Step 1: Open enet application
		 driver.get(url2);
		 
		 //Step 2: Login to eNet app
	     eNetLoginPage.enterUserID(username5);
	     eNetLoginPage.enterUserPassword(password5);
	     testUtil.setHardWait(1000);
	     eNetLoginPage.clickOnLoginButton();
	     
	     //Step 3: Click on Applications 
	     eNetHomePage.clickOnApplicationsTab();
	     
	     //Step 4: Click on Rate Retriever link from customer service section
	     eNetHomePage.clickOnRateRetrieverLink();
	     eNetLTLRateQuotePage.verifLTLRateQuotePage();
	     
	     //Step 5: Select Base Rate from Select One drop down
	     eNetLTLRateQuotePage.selectOne("BR");
	     
	     //Verify Tariff field displays
	     eNetLTLRateQuotePage.verifyTariffFieldIsDisplayed(true);
	     
	     WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		 testUtil.switchToFrame(frame1);
		    
		 //Step 6: Enter following data in tariff field
	     eNetLTLRateQuotePage.entertrafficNumber("NFL (invalid)");
	     eNetLTLRateQuotePage.entertrafficName("550");
	     eNetLTLRateQuotePage.entertrafficSuffix("01");
	     
	     //Step 7: Enter Origin and destination
	     eNetLTLRateQuotePage.enterOriginZip("23233");
	     eNetLTLRateQuotePage.enterDestinationZip("77071");
	     
	     //Step 8: Enter Commodities details in line 1
	     eNetLTLRateQuotePage.selectClass("50");
	     eNetLTLRateQuotePage.enterWeight("850");
	     eNetLTLRateQuotePage.enterDesc("Line 1");
	     	
	     //Step 9: Click on Submit button
	     eNetLTLRateQuotePage.clickOnSubmit();
	     
	     //Verify error message displays
	     eNetLTLRateQuotePage.verifyErrorMsg(errorText);
	     
	     //Step 10: Enter following data in tariff override field
	     eNetLTLRateQuotePage.entertrafficNumber("EXL");
	     eNetLTLRateQuotePage.entertrafficName("100 (invalid)");
	     eNetLTLRateQuotePage.entertrafficSuffix("01");
	     
	     //Step 11: Click on Submit
	     eNetLTLRateQuotePage.clickOnSubmit();
	     
	     //Verify error message displayed
	     eNetLTLRateQuotePage.verifyErrorMsg(errorText);
	     
	     //Step 12: Enter following data in tariff override field
	     eNetLTLRateQuotePage.entertrafficNumber("EXL");
	     eNetLTLRateQuotePage.entertrafficName("550");
	     eNetLTLRateQuotePage.entertrafficSuffix("10 (invalid)");
	     
	     //Step 13: Click on submit
	     eNetLTLRateQuotePage.clickOnSubmit();
	     
	     //Verify error message displays
	     eNetLTLRateQuotePage.verifyErrorMsg(errorText);
	     
	     //Step 14: Enter the following values in tarrif field
	     eNetLTLRateQuotePage.entertrafficNumber("EXL");
	     eNetLTLRateQuotePage.entertrafficName(" ");
	     eNetLTLRateQuotePage.entertrafficSuffix("01");
	     
	     //Step 15: Click on submit
	     eNetLTLRateQuotePage.clickOnSubmit();
	     
	     //Verify error message displays
	     eNetLTLRateQuotePage.verifyErrorMsg(errorText);
	     
	     //Step 16: Enter the following data in tariff field
	     eNetLTLRateQuotePage.entertrafficNumber("EXL");
	     eNetLTLRateQuotePage.entertrafficName("550");
	     eNetLTLRateQuotePage.entertrafficSuffix("01");
	     
	     //Step 17: Click submit
	     eNetLTLRateQuotePage.clickOnSubmitButton(); 
	     
	     //Verify LTL Rate Request page displays
	     eNetLTLRateRequestPage.verifLTLRateRequestPage();
	     
	     //Step 18: Click on update quote button
	     eNetLTLRateRequestPage.clickOnUpdateQuote();
	     driver.switchTo().defaultContent();
	     
	     testUtil.setHardWait(3000);
	     eNetLTLRateQuotePage.verifLTLRateQuotePage();
	     
	     //Step 19: Verify tariff fields are blank
	     testUtil.switchToFrame(frame1);
	     
	     eNetLTLRateQuotePage.verifyEnteredValue("trafficNumber", "");
	     
	     //Step 20: Verify data saved are populated for origin
	     eNetLTLRateQuotePage.verifyEnteredValue("zipOrigin","23233");
	     
	     //Verify data saved are populated for destination
	     eNetLTLRateQuotePage.verifyEnteredValue("zipDestination","77071");
	     
	     //Verify data saved are populated for weight 
	     eNetLTLRateQuotePage.verifyEnteredValue("weight0","850");
	     
	     //Verify data saved are populated for desc
	     eNetLTLRateQuotePage.verifyEnteredValue("desc0","Line 1");
	     
	     //Verify data saved are populated for class
	     eNetLTLRateQuotePage.verifyEnteredValue("uiId1","1");
	     

	     
	     /*WebElement ele = driver.findElement(By.id("trafficNumber"));
	     eNetLTLRateQuotePage.verifyDataByField(ele,"");
	     
	     //Step 20: Verify data saved are populated for origin
	     WebElement origin = driver.findElement(By.id("zipOrigin"));
	     eNetLTLRateQuotePage.verifyDataByField(origin,"23233");
	     
	     //Verify data saved are populated for destination
	     WebElement destination = driver.findElement(By.id("zipDestination"));
	     eNetLTLRateQuotePage.verifyDataByField(destination,"77071");
	     
	     //Verify data saved are populated for weight 
	     WebElement weight = driver.findElement(By.id("weight0"));
	     eNetLTLRateQuotePage.verifyDataByField(weight,"850");
	     
	     //Verify data saved are populated for desc
	     WebElement desc = driver.findElement(By.id("desc0"));
	     eNetLTLRateQuotePage.verifyDataByField(desc,"Line 1");
	     
	     //Verify data saved are populated for class
	     WebElement classValue = driver.findElement(By.id("uiId1"));
	     eNetLTLRateQuotePage.verifyDataByField(classValue,"1");
	     */
	}
	
	/*
	 * ENet Rate Request App - Verify Tariff Override Code  Applied
	 */
	
	@Test(enabled = true, priority = 5)
	public void executeQZ_935() {
		
		 //Step 1: Login to enet application
		 driver.get(url2);
	     eNetLoginPage.enterUserID(username5);
	     eNetLoginPage.enterUserPassword(password5);
	     testUtil.setHardWait(1000);
	     eNetLoginPage.clickOnLoginButton();
	     
	     //Step 2: Click on Applications and click on Rate Retriever link
	     eNetHomePage.clickOnApplicationsTab();
	     eNetHomePage.clickOnRateRetrieverLink();
	     
	     //Rate quote page is displayed
	     eNetLTLRateQuotePage.verifLTLRateQuotePage();
	     
	     //Step 3: Select Base Rate from Select One drop down and enter the following data
	     eNetLTLRateQuotePage.selectOne("BR");
	     eNetLTLRateQuotePage.enterOriginZip("23230");
	     eNetLTLRateQuotePage.enterDestinationZip("30303");
	     eNetLTLRateQuotePage.selectClass("50");
	     eNetLTLRateQuotePage.enterWeight("500");
	     	
	     //Step 4: Click on Submit button
	     eNetLTLRateQuotePage.clickOnSubmit();
	     
	     //Rate results are returned
	     eNetLTLRateRequestPage.verifLTLRateRequestPage();
	     
	     //Step 5: Record Tariff and Estimated Freight Charges
	     String tariff = eNetLTLRateRequestPage.recordTariff();
	     String freightCharge = eNetLTLRateRequestPage.recordEstimatedFreightCharge();
	     
	     //Tariff value should be EXL 550 01
	     Assert.assertEquals(tariff, "EXL 550 01");
	     
	     //Step 6: Click on update quote button
	     eNetLTLRateRequestPage.clickOnUpdateQuote();
	     driver.switchTo().defaultContent();
	     
	     //Rate request screen is displayed
	     eNetLTLRateQuotePage.verifLTLRateQuotePage();
	     WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		 testUtil.switchToFrame(frame1);
		    
		 //Step 7: Enter following data in tariff field
	     eNetLTLRateQuotePage.entertrafficNumber("LIT");
	     eNetLTLRateQuotePage.entertrafficName("ECZ");
	     eNetLTLRateQuotePage.entertrafficSuffix("02");
	     
	     //Step 8: Click on Submit
	     eNetLTLRateQuotePage.clickOnSubmit();
	     
	     //Rate results are displayed
	     eNetLTLRateRequestPage.verifLTLRateRequestPage();
	     
	     //Step 9: Verify Tariff value is same as entered in step 7 
	     String tariffNew = eNetLTLRateRequestPage.recordTariff();
	     Assert.assertEquals(tariffNew, "LIT ECZ 02");
	     
	     //Step 10: Verify Estimated freight charge is not same as recorded in previous step
	     String freightChargeNew = eNetLTLRateRequestPage.recordEstimatedFreightCharge();
	     Assert.assertNotEquals(freightCharge, freightChargeNew);
	     
	}
	
	
	
	@Test(enabled = true, priority = 6)
	public void executeQZ_912() throws InterruptedException {

		//Step1: Open the following link:
		driver.get(url2);

		//Step2: Log into *eNet*
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		//Step3: From eNet page top navigation Click *Applications*
		eNetHomePage.clickOnApplicationsTab();

		//Step4: click *Rate Retriever* From the Applications page, find the *Customer Service* applications list
		eNetApplicationsPage.clickOnRateRetrieverLink();
		
		//LTL Rate Quote* form displays
		eNetLTLRateQuotePage.verifLTLRateQuotePage();
		 
		//Step5: CLONE - From the Applications page, find the *Customer Service*  applications list click *Rate Retriever* 
		//*LTL Rate Quote* form displays
		// skipping step5 as it seems same as step 4.

		//Step6: From the *Contact and Routing Information* section,
		// enter/select the following data:
		// My Role: Shipper,Terms: Pre-paid,Origin: 23233,Destination: 77071,
		String account = "5068692";
		eNetLTLRateQuotePage.enterAccountNumber(account);
		eNetLTLRateQuotePage.selectRole("Shipper");
		eNetLTLRateQuotePage.selectTerm("Pre-paid");
		eNetLTLRateQuotePage.enterOriginZip("23233");
		eNetLTLRateQuotePage.enterDestinationZip("77071");

		//Step7: From the *Commodities* section,validate 3 commodities rows are displayed
		eNetLTLRateQuotePage.validateCommodityRowsDisplayed(3);

		//Step8: Select *Class* and validate the dropdown includes the following list:
		/*
		 * 50 55 60 65 70 77.5 85 92.5 100 110 125 150 175 200 250 300 400 500
		 */

		String val = " 50 55 60 65 70 77.5 85 92.5 100 110 125 150 175 200 250 300 400 500 ";
		String defVal = eNetLTLRateQuotePage.getDefaultClassValue();
		Assert.assertEquals(val, defVal);

		//Step9: Validate *Total Weight* field is numeric and accepts up to 7 number (no decimals)
		eNetLTLRateQuotePage.enterWeight("1.23.45.67.");
		eNetLTLRateQuotePage.clickOnSubmit();
		String actWeight = eNetLTLRateQuotePage.getWeightValue(0);
		eNetLTLRateQuotePage.validateWeightFieldLength("7");
		Assert.assertEquals(actWeight, "1234567");

		//Step10: Select *Additional Commodity* button,validate that more than *10 Commodities* can be added,
		eNetLTLRateQuotePage.addAdditionalCommodities(11);
		
		// User is able to allow for more than 10 commodity groups as required.
		// The maximum number of commodities is configurable by changing the value in
		// the properties file.
		eNetLTLRateQuotePage.validateCommodityRowsDisplayed(12);

		//Step11: From the *Commodities* section,add 11 commodities, enter/select the
		// following data:
		/*
		 * Class: 50 Total Weight: 250 Description: Line 1 Class: 55 Total Weight: 250
		 * Description: Line 2 Class: 60 Total Weight: 150 Description: Line 3 Class: 65
		 * Total Weight: 80 Description: Line 4 Class: 70 Total Weight: 250 Description:
		 * Line 5 Class: 77.5 Total Weight: 150 Description: Line 6 Class: 85 Total
		 * Weight: 80 Description: Line 7 Class: 92.5 Total Weight: 250 Description:
		 * Line 8 Class: 100 Total Weight: 150 Description: Line 9 Class: 100 Total
		 * Weight: 80 Description: Line 9 Class: 110 Total Weight: 75 Description: Line
		 * 10 Class: 125 Total Weight: 27 Description: Line 11
		 */
		String[] classValues = { "50", "55", "60", "65", "70", "77.5", "85", "92.5", "100", "110", "125" };
		String[] weights = { "250", "250", "150", "80", "250", "150", "80", "250", "150", "75", "27" };
		String des = "Line ";

		eNetLTLRateQuotePage.enterMultipleCommodities(11, classValues, weights, des);

		//Step12: Click *Submit* button
		eNetLTLRateQuotePage.clickOnSubmit();
		
		// *LTL Rate Request* page displays
		eNetLTLRateRequestPage.verifLTLRateRequestPage();

		//Step13: Scroll down to *Commodities section, verify all 11 commodities are displayed and the data is correct: Note: system add a deficit weight of 288
		String weight = eNetLTLRateRequestPage.getAddedWeight();
		Assert.assertEquals(weight, "288");

		eNetLTLRateRequestPage.validateCommodityValue(des, classValues, weights);

		//Step14: Click *Log Out* from top navigation
		eNetHomePage.clickOnLogout();
		eNetHomePage.clickOnLogoutButtonFreight();

	}
	
	/*
	 * Rate Retriever - Verify on the fly lookup functionality for Origin and Destination fields
	 */
	
	@Test(enabled = true, priority = 7)
	public void executeQZ_913() throws InterruptedException {

		//Step1:Open the following link:<http://enetqa.estesinternal.com/>
		driver.get(url2);

		//Step2: Log into *eNet*
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		//Step3: From eNet page top navigation Click *Applications*
		eNetHomePage.clickOnApplicationsTab();

	    //Step4: From the *Applications* page, find the *Customer Service* applications list, and click *Rate Retriever* link
		eNetApplicationsPage.clickOnRateRetrieverLink();

		// *LTL Rate Quote* form displays
		eNetLTLRateQuotePage.verifLTLRateQuotePage();

		//Step5: From the *LTL Rate Quote* page, *Contact and Routing Information* section,
		//enter/select the following data: 
		//Account: 5068692,My Role: Third
		//Party,Terms: Collect
		String account = "5068692";
		eNetLTLRateQuotePage.enterAccountNumber(account);
		eNetLTLRateQuotePage.selectRole("Third Party");
		eNetLTLRateQuotePage.selectTerm("Collect");

		//Step6:Enter a valid Origin Zip
		/*
		 * User is provided with a 'on-the-fly' list of options associated with the
		 * *Origin Zip* entered; this list may contain 1 or up to 10 valid locations for
		 * selection
		 */
		eNetLTLRateQuotePage.enterOrigZip("2323");
		eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();

		//Step7:Select a link
		//Origin is populated with the selected Zip, City, and State
		eNetLTLRateQuotePage.clickOnReqSuggests("RICHMOND, VA 23230");

		//Step8:Enter a valid Destination Zip
		eNetLTLRateQuotePage.enterDestZip("2323");
		
		/*
		 * User is provided with a 'on-the-fly' list of options associated with the
		 * *Destination Zip* entered; this list may contain 1 or up to 10 valid
		 * locations for selection
		 */
		eNetLTLRateQuotePage.verifyDestSuggestDisplayed();

		//Step9:Select a link.Destination is populated with the selected Zip, City, and State
		eNetLTLRateQuotePage.clickOnReqSuggests("HENRICO, VA 23233");

		//Step10:From the *LTL Rate Quote* page, *Commodities* section,
		//enter/select the following :Class: 50Total Weight: 970
		//Description: QZ-913 on the fly lookup
		eNetLTLRateQuotePage.selectClass("50");
		eNetLTLRateQuotePage.enterWeight("970");
		eNetLTLRateQuotePage.enterDesc("QZ-913 on the fly lookup");

		//Step11: Click *Submit*
		eNetLTLRateQuotePage.clickOnSubmit();
		eNetLTLRateRequestPage.verifLTLRateRequestPage();

		String originZip = eNetLTLRateRequestPage.getRouteOrigin();
		TestUtil.verifyText(originZip, "RICHMOND, VA 23230 US");
		String destZip = eNetLTLRateRequestPage.getRouteDest();
		TestUtil.verifyText(destZip, "HENRICO, VA 23233 US");

		//Step12: Scroll down and click the *Update Quote* button
		eNetLTLRateRequestPage.clickOnUpdateQuote();

		//Step13:From the *LTL Rate Quote* page, *Contact and Routing Information* section,click *Clear* Origin button
		eNetLTLRateQuotePage.clickOnOrigClrBtn();

		//Step14: Click *Clear* Destination button
		eNetLTLRateQuotePage.clickOnDestClrBtn();

		//Step15: Enter a valid Origin City
		eNetLTLRateQuotePage.enterOrigCity("ACHILLES");
		eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();
		
		//Step16: Select a link
		eNetLTLRateQuotePage.clickOnReqSuggests("ACHILLES, VA 23001");

		//Step17: Enter a valid Destination City
		eNetLTLRateQuotePage.enterDestCity("AMELIA");
		eNetLTLRateQuotePage.verifyDestSuggestDisplayed();
		
		//Step18: Select a link
		eNetLTLRateQuotePage.clickOnReqSuggests("AMELIA COURT HOUSE, VA 23002");

		//Step19: Click *Submit*
		eNetLTLRateQuotePage.clickOnSubmit();
		eNetLTLRateRequestPage.verifLTLRateRequestPage();

		String originCity = eNetLTLRateRequestPage.getRouteOrigin();
		TestUtil.verifyText(originCity, "ACHILLES, VA 23001 US");
		String destCity = eNetLTLRateRequestPage.getRouteDest();
		TestUtil.verifyText(destCity, "AMELIA COURT HOUSE, VA 23002 US");
		
		//Step20: Scroll down and click the *Update Quote* button
		eNetLTLRateRequestPage.clickOnUpdateQuote();

		//Step21: From the *LTL Rate Quote* page, *Contact and Routing Information* section,  click *Clear* Origin button
		eNetLTLRateQuotePage.clickOnOrigClrBtn();

		//Step22: Click *Clear* Destination button
		eNetLTLRateQuotePage.clickOnDestClrBtn();

		//Step23: Enter a valid Origin State
		eNetLTLRateQuotePage.enterOrigState("VA");
		eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();
		
		//Step24: Select a link
		eNetLTLRateQuotePage.clickOnReqSuggests("ALDIE, VA 20105");

		//Step25: Enter a valid Destination State
		eNetLTLRateQuotePage.enterDestState("SC");
		eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();
		
		//Step26: Select a link
		eNetLTLRateQuotePage.clickOnReqSuggests("BALLENTINE, SC 29002");

		//Step27: Click *Submit*
		eNetLTLRateQuotePage.clickOnSubmit();

		//Step28:From the *LTL Rate Request* page,
		//Validate Origin and Destination data in the *Routing* section are correct
		eNetLTLRateRequestPage.verifLTLRateRequestPage();

		String originState = eNetLTLRateRequestPage.getRouteOrigin();
		TestUtil.verifyText(originState, "ALDIE, VA 20105 US");
		String destState = eNetLTLRateRequestPage.getRouteDest();
		TestUtil.verifyText(destState, "BALLENTINE, SC 29002 US");

		//Step29: From *eNet* home page top navigation menu,click *Log Out*
		eNetHomePage.clickOnLogout();
		
		//Step30: Confirm by clicking the *Logout* button
		eNetHomePage.clickOnLogoutButtonFreight();

	}
	
	
	/*
	 * Rate Retriever - Verify when pickup date is on a holiday, Guaranteed Standard
	 * Charges for 10AM, 12PM, and 5PM are not calculated
	 */	
	@Test(enabled = true, priority = 8)
	public void executeQZ_897() throws InterruptedException {

		String account = "5068692";
//		Step1: Open the following link: http://enetqa.estesinternal.com/
		driver.get(url2);
//		Step2: Login to *eNet* application using the following credentials: User ID: qaenet01 Password: qaenet01
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();
//		Step3: From *eNet*  home page top navigation menu, click *Applications*
		eNetHomePage.clickOnApplicationsTab();
//		Step4: From the *Applications* page, find the *Customer Service* applications list, and click *Rate Retriever* link
		eNetApplicationsPage.clickOnRateRetrieverLink();
//		Step5: From the *LTL Rate Quote* page, *Contact and Routing Information* section, enter/select the following data:
//		Account: 5068692
//		My Role: Third Party
//		Terms: Pre-paid
//		Pickup Date: Defaults to today's date
//		Origin: 23059
//		Destination: 16159
		eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
		eNetLTLRateQuotePage.enterAccountNumberWithoutFrame(account);
		eNetLTLRateQuotePage.selectRole("Third Party");
		eNetLTLRateQuotePage.selectTerm("Pre-paid");
		eNetLTLRateQuotePage.enterOrigZip("23059");
		eNetLTLRateQuotePage.clickOnReqSuggests("GLEN ALLEN, VA 23059");
		eNetLTLRateQuotePage.enterDestZip("16159");
		eNetLTLRateQuotePage.clickOnReqSuggests("WEST MIDDLESEX, PA 16159");
//		Step6: From the *LTL Rate Quote* page, *Commodities* section, enter/select the following data:
//		Class: 50
//		Total Weight: 927
//		Description: QZ-897, Holiday Pickup
		eNetLTLRateQuotePage.selectClass("50");
		eNetLTLRateQuotePage.enterWeight("350");
		eNetLTLRateQuotePage.enterDesc("QZ-897, Holiday Pickup");
//		Step7: Click *Submit*
//		*LTL Rate Request* page displays and all LTL Standard and Guaranteed Charges are calculated
		eNetLTLRateQuotePage.clickOnSubmit();
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");
//		Step8: Scroll Down and click the *Update Quote* button
//		*LTL Rate Quote* page displays and is populated with the previously keyed in data
		eNetLTLRateRequestPage.clickOnUpdateQuote();
		String accNum = eNetTimeCriticalRateQoutePage.getAccountNumber();
		String myRole = eNetTimeCriticalRateQoutePage.getMyRoleStatus();
		String terms = eNetTimeCriticalRateQoutePage.getTermsStatus();
		String origin = eNetTimeCriticalRateQoutePage.getOriginZip();
		String dest = eNetTimeCriticalRateQoutePage.getDestZip();
		TestUtil.verifyText(accNum, account);
		TestUtil.verifyText(myRole, "Third Party");
		TestUtil.verifyText(terms, "Pre-paid");
		TestUtil.verifyText(origin, "23059");
		TestUtil.verifyText(dest, "16159");
		String cls = eNetTimeCriticalRateQoutePage.getSelectedClass();
		String weight = eNetTimeCriticalRateQoutePage.getTotalWeight();
		String des = eNetTimeCriticalRateQoutePage.getDescription();
		TestUtil.verifyText(cls, "50");
		TestUtil.verifyText(weight, "350");
		TestUtil.verifyText(des, "QZ-897, Holiday Pickup");
//		Step9: From the *LTL Rate Quote* page, *Contact and Routing Information* section, update the following field:
//		Pickup Date: any *Holiday Date* - Saturday or Sunday
//		Step10: Click *Submit*
//		String holiday = testUtil.selectWeekendDays();//holiday selection is not working 
//		eNetLTLRateQuotePage.enterPickupDate(holiday);
		eNetLTLRateQuotePage.clickOnSubmit();
//		Verify:
//		1- LTL Standard Charges are calculated 
//		2- Guaranteed By 10 AM display *Contact Us*
//		3- Guaranteed By 12 PM display *Contact Us*
//		4- Guaranteed By 5 PM display *Contact Us*
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
		eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");
//		Step11: From *eNet*  home page top navigation menu, click *Log Out*
		eNetLTLQuoteHistoryDetailPage.clickOnLogout();
//		Step12: Confirm by clicking the *Logout* button
		eNetLTLQuoteHistoryDetailPage.clickOnLogoutIntranet();

}

	/*
	 * eNet Rate Request - Verify the Estimated Freight Charges are calculated based
	 * on rate specified at the selected pickup date
	 */	
		@Test(enabled = true, priority = 9)
		public void executeQZ_925() throws Exception {

//			Step1: Open the following link to get the most recent general rate increase date.
//			https://www.estesexpressannouncesgeneralincrease
//			current date: Feb 04, 2019
//			Step2: Open the following link: http://enetqa.estesinternal.com/login.jsp
			driver.get(url2);
//			Step3: Log into *eNet*
//			Username: qaenet01
//			Password: qaenet01
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();
//			Step4: From eNet page top navigation Click *Applications*
			eNetHomePage.clickOnApplicationsTab();
//			Step5: From the Applications page, find the *Customer Service* applications list click *Rate Retriever*
//			*LTL Rate Quote* page displays
			eNetApplicationsPage.clickOnRateRetrieverLink();
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
//			Step6: From the *Contact and Routing Information* section,  select *Base Rate* from *Select One* drop down option. displayed.
//			*Tariff Override* fields display and is blank and *Pickup Date* defaults to today's date
			eNetLTLRateQuotePage.selectOneWithoutFrame("Base Rate");
//			Step7: Enter/select the following data:
//			Origin: 23060
//			Destination: 30307
			eNetLTLRateQuotePage.enterOrigZip("23060");
			eNetLTLRateQuotePage.clickOnReqSuggests("GLEN ALLEN, VA 23060");
			eNetLTLRateQuotePage.enterDestZip("30307");
			eNetLTLRateQuotePage.clickOnReqSuggests("ATLANTA, GA 30307");
//			Step8: From the *Commodities* section, enter/select the following data:
//			Class: 60
//			Total Weight: 490
//			Description: Line 1
			eNetLTLRateQuotePage.selectClass("60");
			eNetLTLRateQuotePage.enterWeight("490");
			eNetLTLRateQuotePage.enterDesc("QZ-925");
//			Step9: Click *Submit*
//			*LTL Rate Request* page displays
			eNetLTLRateQuotePage.clickOnSubmit();
			eNetLTLRateRequestPage.validatePagetitle("LTL Rate Request"); //Updated this method 
//			Step10: From *LTL Rate Request* page, record the *Estimated Freight Charges* for the LTL Standard service level
//			Estimated Freight Charges #1:
			String estFreight1 = eNetLTLRateRequestPage.getFreightCharges();
//			Step11: Scroll down and click *Update Quote* button
//			*LTL Rate Quote* page displays
			eNetLTLRateRequestPage.clickOnUpdateQuote();
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
//			Step12: Change the *Pickup Date* to a date before (the date of the most recent general rate increase)  Note: date captured in step 1
			
			//Added the below line to enter a date - not in test steps
			String date = testUtil.addOrSubstractDateFromTodayDate(1);
			String actualDate = testUtil.formatDate(date, "MM-dd-yyyy");
			
			eNetLTLRateQuotePage.enterPickupDate(actualDate);
//			Step13: Click *Submit*
//			*LTL Rate Request* page displays
			eNetLTLRateQuotePage.clickOnSubmit();
			eNetLTLRateRequestPage.validatePagetitle("LTL Rate Request");
//			Step14: From *LTL Rate Request* page, record the *Estimated Freight Charges* for the LTL Standard service level
//			Estimated Freight Charges #2:
			String estFreight2 = eNetLTLRateRequestPage.getFreightCharges();
//			Step15: Compare the results from both dates, validate the quote from before the rate increase should be less than the quote from today.
//			Estimated Freight Charges #1 > Estimated Freight Charges #2
			TestUtil.verifyFirstValueGreaterThanSecond(estFreight1, estFreight2);
//			Step16: Click *Log Out* from top navigation
			eNetLTLQuoteHistoryDetailPage.clickOnLogout();
			eNetLTLQuoteHistoryDetailPage.clickOnLogoutIntranet();
		}

		/*
		 * Enet Rate Request App - Tariff Override not displayed when Account Specific
		 * Option selected
		 */	
		@Test(enabled = true, priority = 10)
		public void executeQZ_933() throws InterruptedException {

			//Step1: Open within the Internet Explorer Browser, the following link: http://enetqa.estes-express.com/login.jsp
			driver.get(url2);
			//Step2: Login with the following credentials:
			//User ID: qaenet02
			//Password: qaenet02
			//The ENet home page is displayed
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();
			eNetHomePage.verifyPageTitle();
			//Step3: From the eNet home page, Click on the Applications folder VIEW ALL icon (Applications Tab Link)
			eNetHomePage.clickOnApplicationsTab();
			//Step4: From the Applications screen page under the Customer Service heading, click on the following link: Rate Retriever
			//The Rate Retriever tool is displayed.
			eNetApplicationsPage.clickOnRateRetrieverLink();
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
			//Step5: In the Select One drop down, select Account Specific.
			//The Tarrif Override fields does not display.
			eNetLTLRateQuotePage.selectOneWithoutFrame("Account Specific");
			eNetLTLRateQuotePage.verifyTariffNumberIsEnabledOrNot(false);
			//Step6: Select Base Rate from the Select One drop down.
			//The Tariff Override field is displayed. 
			eNetLTLRateQuotePage.selectOneWithoutFrame("Base Rate");
			eNetLTLRateQuotePage.verifyTariffNumberIsEnabledOrNot(true);
			eNetLTLQuoteHistoryDetailPage.clickOnLogout();
			eNetLTLQuoteHistoryDetailPage.clickOnLogoutIntranet();
		}

	
		/*
		 * Rate Retriever - Verify when Need It Faster? link is selected, then the user is 
		 * directed to the Time Critical Rate Quote form 
		 * and the fields are populated with keyed in data
		 */
		
		@Test(enabled = true, priority = 11)
		public void executeQZ_878() throws InterruptedException {
			String account = "9451455";
			String role = "Shipper";
			String term = "Pre-paid";
			String origin = "30307";
			String destination = "90007";
			String cls    = "50";
			String weight = "970";
			String des    = "QZ-878 Need It Faster";
			String height = "1";
			String width  = "1";
			String length = "1";
			String acc1 = "Notify Request";
			String acc2 = "Inside Pickup";
			String accCode = "INP";
			String acc3 = "Overlength Freight (8.00' to 11.99')";
			String acc4 = "Protect from Freezing";
			String accCode1 = "LONG12";
			
			//Step1: Open eNet application
			driver.get(url2);

			//Step2: Log into *eNet*
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();

			//Step3: From eNet page top navigation Click *Applications*
			eNetHomePage.clickOnApplicationsTab();
			
			//Step4: Click Rate Retriever link
			eNetApplicationsPage.clickOnRateRetrieverLink();

			//Step5: From the *LTL Rate Quote* page, *Enter Contact and Routing Information*
			eNetLTLRateQuotePage.verifLTLRateQuotePage();
			eNetLTLRateQuotePage.enterAccountNumber(account);
			eNetLTLRateQuotePage.selectRole(role);
			eNetLTLRateQuotePage.selectTerm(term);
			eNetLTLRateQuotePage.enterOrigZip(origin);
			eNetLTLRateQuotePage.clickOnReqSuggests("ATLANTA, GA 30307");
			eNetLTLRateQuotePage.enterDestZip(destination);
			eNetLTLRateQuotePage.clickOnReqSuggests("LOS ANGELES, CA 90007");

			//Step6: Record all data 
			//We have all these data in Step 5

			//Step7: From the *Commodities* section,enter/select the following data:
			eNetLTLRateQuotePage.selectClass(cls);
			eNetLTLRateQuotePage.enterWeight(weight);
			eNetLTLRateQuotePage.enterDesc(des);
			
			//Step8: Record commodities data:
			//We have this data in Step 7

			//Step9: From the *Accessorials* section, select and record the following:
			// Notify Request,Inside Pickup,Overlength (8.01'-11.99'),Protect from Freezing
			eNetLTLRateQuotePage.clickOnExpandButton();
			eNetLTLRateQuotePage.clickOnAccessorial(acc1);
			eNetLTLRateQuotePage.clickOnAccessorialByCode(accCode);
			eNetLTLRateQuotePage.clickOnAccessorialByCode(accCode1);
			eNetLTLRateQuotePage.clickOnAccessorial(acc4);
			
			//Record accessorial data
			//We have this data in Step 9
			
			//Step10: Click *Submit*
			eNetLTLRateQuotePage.clickOnSubmit();
			
			//LTL Rate Request* page displays
			eNetLTLRateRequestPage.verifyPage();

			//Step11: From the *Adjust Service Level*,scroll down to the *Additional Options* section
			//Step12: Click the *Time Critical Need It Faster?* link
			eNetLTLRateRequestPage.clickOnTimeCritical();
			
			//Time Critical Rate Quote form displays
			//eNetTimeCriticalRateQoutePage.verifyPage();
			eNetTimeCriticalRateQoutePage.verifytimeCriticalPage();
			//Step13: From the *Contact and Routing Information* section
			// Verify the following fields are populated with the correct data: 
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getAccountNumber(), account);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getMyRoleStatus(), role);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getTermsStatus(), term);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getOriginZip(), origin);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getDestZip(), destination);

			//Step14: From the *Commodities* section
			// Verify the following fields are populated with the correct data
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getSelectedClass(), cls);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getTotalWeight(), weight);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getDescription(), des);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getHeight(), height);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getWidth(), width);
			Assert.assertEquals(eNetTimeCriticalRateQoutePage.getLength(), length);

			//Step15: From the *Accessorials* section
			// Verify the following accessorials are checked:
			eNetTimeCriticalRateQoutePage.verifyReqAccessorialIsselected(acc1);
			eNetTimeCriticalRateQoutePage.verifyReqAccessorialIsselected(acc2);
			eNetTimeCriticalRateQoutePage.verifyReqAccessorialIsselected(acc3);
			eNetTimeCriticalRateQoutePage.verifyReqAccessorialIsselected(acc4);
			driver.switchTo().defaultContent();
			
			//Step16: Click the back *<-* arrow
			testUtil.navigateBack();
			driver.switchTo().frame("mainpage");
			eNetLTLRateRequestPage.verifyPage();
			
			//Step17: From *eNet* home page top navigation menu,click *Log Out*
			eNetHomePage.clickOnLogout();
			
			//Step18: Confirm by clicking the *Logout* button
			eNetHomePage.clickOnLogOutButton();

		}
	
		/*
		 * eNet - Validate when total weight is > than 'the specified total weight
		 * qualifier for Less Than Truckload Standard', an error message is displayed
		 * (currently 20,000 lbs)
		 */
		@Test(enabled = true, priority = 12)
		public void executeQZ_896()
				throws InterruptedException {

			String account = "5068692";
			String role = "Shipper"; 
			String term = "Pre-paid";
			String origin = "23059";
			String dest = "30307";
			String cls = "50";
			String weight = "20000";
			String description = "QZ-896";
			String msg = "Attention: Your commodity weight exceeds the max of 20,000 lbs. "
					+ "For shipments weighing more than 20,000 lbs., please use the Volume/Truckload Rate Quote tool.";
			
			//Step 1: Open eNet application
			driver.get(url2);

			//Step 2: Log into application
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();

			//Step 3: Click Applications tab
			eNetHomePage.clickOnApplicationsTab();

			//Step 4: Click on Rate Retriever link from Customer Service menu
			eNetApplicationsPage.clickOnRateRetrieverLink();

			//Step 5: Enter Contact and Routing Information
			eNetLTLRateQuotePage.enterAccountNumber(account);
			eNetLTLRateQuotePage.selectRole(role);
			eNetLTLRateQuotePage.selectTerm(term);
			eNetLTLRateQuotePage.enterOrigZip(origin);
			eNetLTLRateQuotePage.clickOnReqSuggests("GLEN ALLEN, VA 23059");
			eNetLTLRateQuotePage.enterDestZip(dest);
			eNetLTLRateQuotePage.clickOnReqSuggests("ATLANTA, GA 30307");

			//Step 6: Enter details in Commodities section
			eNetLTLRateQuotePage.selectClass(cls);
			eNetLTLRateQuotePage.enterWeight(weight);
			eNetLTLRateQuotePage.enterDesc(description);

			//Step 7: Click Submit
			eNetLTLRateQuotePage.clickOnSubmit();

			//LTL Rate Request page displays and LTL Estimated Freight Charges are calculated
			eNetLTLRateRequestPage.verifyPage();
			eNetLTLRateRequestPage.verifyEstimatedFreightCharge();

			//Step 8: Click the Update Quote button
			eNetLTLRateRequestPage.clickOnUpdateQuote();
			
			//LTL Rate Quote page displays and is populated with the previously keyed in data
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
			
			Assert.assertEquals(account, eNetLTLRateQuotePage.getAccountNumber());
			Assert.assertEquals(role, eNetLTLRateQuotePage.getMyRoleStatus());
			Assert.assertEquals(term, eNetLTLRateQuotePage.getTermsStatus());
			Assert.assertEquals(origin, eNetLTLRateQuotePage.getOriginZip());
			Assert.assertEquals(dest, eNetLTLRateQuotePage.getDestZip());
			
			Assert.assertEquals(cls, eNetLTLRateQuotePage.getSelectedClass(0));
			Assert.assertEquals(weight, eNetLTLRateQuotePage.getWeightValue(0));
			Assert.assertEquals(description, eNetLTLRateQuotePage.getDescription(0)); 

			//Step 9: Click the + icon from Commodities section
			eNetLTLRateQuotePage.clickOnAddCommodity();

			//Step 10: Enter value that commodity section
			eNetLTLRateQuotePage.selectClass1("50");
			eNetLTLRateQuotePage.enterWeight1("1");
			eNetLTLRateQuotePage.enterDesc1(" QZ-896, Total Weight Exceeded the LTL Limit");

			//Step 1:Click Submit
			eNetLTLRateQuotePage.clickOnSubmit();

			//LTL Rate Quote page re-display with the following message: 
			//Attention: Your commodity weight exceeds the max of 20,000 lbs. For shipments weighing more
			//than 20,000 lbs., please use the Volume/Truckload Rate Quote tool.
			eNetLTLRateQuotePage.ValidateAttentionMessage(msg);

			//Step 12: Click Log Out
			eNetHomePage.clickOnLogout();
			
			//Step 13: Confirm Logout
			eNetHomePage.clickOnLogoutButton();
		}
	
	
		/*
		 * Rate Retriever - Verify when origin or destination is a non-direct point,
		 * then an informational message is displayed in the routing section and
		 * Guaranteed charges are not calculated
		 */
		@Test(enabled = true, priority = 13)
		public void executeQZ_895() throws InterruptedException {

			String account = "5068692";
			String account2 = "9451455";
			
			//Step 1: Open eNet application 
			driver.get(url2);

			//Step 2: Login to eNet application
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();

			//Step 3: Click Application
			eNetHomePage.clickOnApplicationsTab();

			//Step 4: Click Rate Retriever link
			eNetApplicationsPage.clickOnRateRetrieverLink();

			//Step 5: Enter the contact and routing information
			eNetLTLRateQuotePage.enterAccountNumber(account);
			eNetLTLRateQuotePage.selectRole("Third Party");
			eNetLTLRateQuotePage.selectTerm("Collect");
			//Note: Origin given in the test case does not return contact us for rate. So changed origin data refereing UFT script
			eNetLTLRateQuotePage.enterOrigZip("83627");
			eNetLTLRateQuotePage.enterOrigCity("Indian Cove");
			eNetLTLRateQuotePage.clickOnReqSuggests("INDIAN COVE, ID 83627");
			eNetLTLRateQuotePage.enterDestZip("77007");
			eNetLTLRateQuotePage.clickOnReqSuggests("HOUSTON, TX 77007");

			//Step 6: Enter details in Commodities section
			eNetLTLRateQuotePage.selectClass("50");
			eNetLTLRateQuotePage.enterWeight("970");
			eNetLTLRateQuotePage.enterDesc("QZ-895 Non-Direct Origin Point");

			 //Step 7: Click *Submit*
			 //Note: in this sceanrio Origin point is not serviced direct. Service is 7 days from 
			 //LAREDO, TX exchange point. Call LAREDO, TX for assistance.
			eNetLTLRateQuotePage.clickOnSubmit();

			//Verify error message displayed
			String serviceDays = eNetLTLRateRequestPage.getServiceDays();
			String message = "Origin point is not serviced direct. Service is "+serviceDays+" from BOISE, ID exchange point. Call BOISE, ID for assistance.";
			eNetLTLRateRequestPage.validateRoutingError(message);

			//Step 8: From the *Adjust Service Level* section
			// Verify Guaranteed charges are not calculated. *Contact Us* or blank instead
			eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
			eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");
			eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed5");
			
			//Step 9: Scroll down and click *Generate New Quote* button
			eNetLTLRateRequestPage.clickOnGenerateQuote();
			driver.switchTo().defaultContent();
			
			//Step 10: Enter Contact and Routing Information
			eNetLTLRateQuotePage.enterAccountNumber(account2);
			eNetLTLRateQuotePage.selectRole("Shipper");
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			eNetLTLRateQuotePage.enterOrigZip("90007");
			eNetLTLRateQuotePage.clickOnReqSuggests("LOS ANGELES, CA 90007");
			eNetLTLRateQuotePage.enterDestCity("Indian Cove");
			eNetLTLRateQuotePage.clickOnReqSuggests("INDIAN COVE, ID 83627");

			//Step 11: Enter details in commodities section
			eNetLTLRateQuotePage.selectClass("70");
			eNetLTLRateQuotePage.enterWeight("4750");
			eNetLTLRateQuotePage.enterDesc("QZ-895 Non-Direct Destination Point");

			//Step 12: Click *Submit*
			eNetLTLRateQuotePage.clickOnSubmit();
			
			//Verify routing error displayed
			serviceDays = eNetLTLRateRequestPage.getServiceDays();
			String message2 = "Destination point is interlined with DIAMOND LINE DELIVERY SYSTEM. Service is "+serviceDays+" to BOISE, ID exchange point.";
			
			eNetLTLRateRequestPage.validateRoutingError(message2);

			//Step 13: Verify Guaranteed charges are not calculated. *Contact Us* or blank instead
			eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");
			eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
			eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed5");

			//Step 14: Click LogOut
			eNetHomePage.clickOnLogout();
			
			//Step 15: Confirm Logout
			eNetHomePage.clickOnLogoutButton();
			
		}
	
		/*
		 * eNet - LTL - Verify deficit weight information is displayed in the Commodities section of Quote Details
		 */
		
		@Test(enabled = true, priority = 14)
		public void executeQZ_893()
				throws InterruptedException {

			//Step 1: Open the enet application
			driver.get(url2);

			//Step 2: Log into *eNet*
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();

			//Step 3: From eNet page top navigation Click *Applications*
			eNetHomePage.clickOnApplicationsTab();

			//Step 4: Click Rate Retriever link 
			eNetApplicationsPage.clickOnRateRetrieverLink();
			
			//Verify LTL Rate Quote page displays
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
			driver.switchTo().defaultContent();
			
			//Step 5: Enter details in contact and routing information
			//String account = "5068692";
			String account = "9451455";
			eNetLTLRateQuotePage.enterAccountNumber(account);
			eNetLTLRateQuotePage.selectRole("Shipper");
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			eNetLTLRateQuotePage.enterOriginZip("23233");
			eNetLTLRateQuotePage.enterDestinationZip("77071");

			//Step 6: Enter commodity details
			String[] classValues = { "50", "100", "60" };
			String[] weights = { "250", "150", "80" };
			String des = "Line ";
			eNetLTLRateQuotePage.enterMultipleCommodities(3, classValues, weights, des);

			//Step 7: Click Submit button
			eNetLTLRateQuotePage.clickOnSubmit();
			
			//eNetLTLRateQuotePage.verifLTLRateQuotePage();
			//Verify LTL Rate Request page displays
			eNetLTLRateRequestPage.verifLTLRateRequestPage();

			 //Step 8: Verify commodity rows are added and quote displays deficit weight
			eNetLTLRateRequestPage.verifyAddedWeightIsDisplayed();
			eNetLTLRateRequestPage.validateCommodityValue(des, classValues, weights);

			//Step 9: Verify that Class: is blank,Weight is 20,Rate is blank,Charge: a value in currency format ($x,xxx.xx)
			eNetLTLRateRequestPage.verifyAddedWeightValues("Class", "");
			eNetLTLRateRequestPage.verifyAddedWeightValues("Weight", "20");
			eNetLTLRateRequestPage.verifyAddedWeightValues("Rate", "");
			//eNetLTLRateRequestPage.verifyAddedWeightValues("Charge", "$46.72");
			eNetLTLRateRequestPage.verifyAddedWeightValues("Charge", "$11.80");
			
			
			//Step 10: Click *Log Out* from top navigation
			eNetHomePage.clickOnLogout();
			eNetHomePage.clickOnLogoutButton();

		}
		
		/*
		 * eNet - Verify when Consignee is selected Terms is automatically set to Collect and it can not be changed
		 */
		
		@Test(enabled = true, priority = 15)
		public void executeQZ_889() {

			//Step 1: Open eNet application
			driver.get(url2);

			//Step2: Log into *eNet*
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();

			//Step 3: From eNet page top navigation Click *Applications*
			eNetHomePage.clickOnApplicationsTab();

			//Step 4: Click *Rate Retriever*
			eNetApplicationsPage.clickOnRateRetrieverLink();
			
			//Verify LTL Rate Quote page displays
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
			driver.switchTo().defaultContent();
			
			//Step 5: Enter contact and routing information
			String account = "5068692";
			eNetLTLRateQuotePage.enterAccountNumber(account);
			eNetLTLRateQuotePage.selectRole("Consignee");

			//Step 6: Note *Terms* field is automatically set to *Collect*
			String term = eNetLTLRateQuotePage.getTermsStatus();
			TestUtil.verifyText(term, "Collect");

			//Step 7: Try updating the *Terms* field
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			
			//Terms	 field is disabled and can not be changed
			eNetLTLRateQuotePage.verifyTermStatusIsEnabled(false);
			String term2 = eNetLTLRateQuotePage.getTermsStatus();
			TestUtil.verifyText(term2, "Collect");

			//Step 8: Logout
			eNetHomePage.clickOnLogout();
			eNetHomePage.clickOnLogoutButton();

		}

		/*
		 * ENet - Rate Request - Pickup Date field and calendar icon functionalities
		 */	
		@Test(enabled = true, priority = 16)
		public void executeQZ_928() {

//			Step1: Open the following link: http://enetqa.estesinternal.com/login.jsp
			driver.get(url2);
//			Step2: Log into *eNet* Username: qaenet01 Password: qaenet01
			eNetLoginPage.enterUserID(username5);
			eNetLoginPage.enterUserPassword(password5);
			eNetLoginPage.clickOnLoginButton();
//			Step3: From eNet page top navigation Click *Applications*
			eNetHomePage.clickOnApplicationsTab();
//			Step4: From the Applications page, find the *Customer Service* applications list click *Rate Retriever*
			eNetApplicationsPage.clickOnRateRetrieverLink();
//			*LTL Rate Quote* form displays
			eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");
//			driver.switchTo().defaultContent();
//			Step5: From the *Contact and Routing Information* section, validate that by default, *Base Rate* is selected in the *Select One* drop down box
			//Default selection would be Account Specific hence followed UFT flow
			eNetLTLRateQuotePage.selectOneWithoutFrame("Base Rate");
			eNetLTLRateQuotePage.verifyGivenValueInSelectOne("Base Rate");
//			Step6: Validate that the calendar functionality meets the current application design and usability standards:  - by default, *Pickup Date* displays *Current/Today's date* in the following format: MM/DD/YYYY
			String currentPickUpDate = eNetLTLRateQuotePage.getPickUpDate();
			testUtil.setHardWait(1000);
			logger.info("Current Pickup Date:" +currentPickUpDate);
			String todaysDate = testUtil.getTodayDateByFormat("MM/dd/yyyy");
			logger.info("Todays Date:" +todaysDate);
			TestUtil.verifyText(currentPickUpDate, todaysDate);
//			Step7: Click the *Pickup Date Calendar Icon* - validate the calendar opens with Today’s Date elected as the default. The user can then click on the date and the date will be entered into the field.
			eNetLTLRateQuotePage.clickOnPickUpDateCalender();
			String currentCalenderDate = eNetLTLRateQuotePage.getPickUpDate();
			TestUtil.verifyText(currentCalenderDate, todaysDate);
//			Step8: -The calendar module displays a drop down to select the month and a drop down to select the year.
			eNetLTLRateQuotePage.validateMonthSelectionsDisplayed(12);
//			Step9: -Users should have the ability to either key in the date using the MM/DD/YYYY format or clicking on the calendar icon to populate the calendar field.
//			User can key in the date using the MM/DD/YYYY format or select from the calendar module
			eNetLTLRateQuotePage.enterPickupDate(todaysDate);
			testUtil.setHardWait(3000);
			String enteredDate = eNetLTLRateQuotePage.getPickUpDate();
			TestUtil.verifyText(enteredDate, todaysDate);
//			Step10: -The user can use arrows in the header to scroll back to the previous month or forward to the next month.
			eNetLTLRateQuotePage.verifyCalenderPreviouMonthSelectionDisplayed();
			eNetLTLRateQuotePage.verifyCalenderNextMonthSelectionDisplayed();
//			Step11: -The user can use arrows in the header to scroll back to the previous year or forward to the next year.
			//to scroll next year is not available in date picker.
//			The calendar functionality meets the current application design and usability standards
//			Step12: Click *Log Out* from top navigation
			eNetHomePage.clickOnLogout();
			eNetHomePage.clickOnLogoutButton();

		}	

		/*
		 * 01.01.00.19 - Exchange Rate Maintenance
		 */		
		@Test(enabled = true, priority = 17)
		public void executeQZ_9867() {

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
		 * UAT Invoice Inquiry
		 */			
		@Test(enabled = true , priority = 18)
		public void executeQZ_8104() {
			
			// step1:Open within the Internet Explorer Browser, the following link:
			// https://estesexpress.atlassian.net/browse/QZ-6808
			driver.get(url2);

			// step2: Login with the following credentials:
			// Username: qaenet02
			// Password: qaenet02
			eNetLoginPage.enterUserID(username12);
			eNetLoginPage.enterUserPassword(password12);
			eNetLoginPage.clickOnLoginButton();

			// step3: From the eNet home page, click on the Applications category, 
			//under the Account Receivable link, click on the following link:  Invoice Inquiry 
			eNetHomePage.clickOnApplicationsTab();
			eNetApplicationsPage.clickOnInvoiceInquiryLink();
			testUtil.switchToFrame("mainpage");
			//Invoice Inquiry screen is displayed.
			eNetInvoiceInquiryPage.validatePageTitle("Invoice Inquiry");
			
			//step4: From the Invoice Inquiry page, search using PRO #.
			//pro-	003-1982014,	003-2039004,	048-2152657,	B001122
//			eNetInvoiceInquiryPage.enterTerminal("020"); --updated the below test data as the pro is not working 
//			eNetInvoiceInquiryPage.enterProNumber("8000105");
			eNetInvoiceInquiryPage.enterTerminal("103");
			eNetInvoiceInquiryPage.enterProNumber("0553110");
			
			eNetInvoiceInquiryPage.clickOnSearch();
			//Report displays for selected PRO number.
			eNetInvoiceInquiryPage.validateResultsTableDisplayed();
			String pro = eNetInvoiceInquiryPage.getValuefromTable("Pro Number", 2);
			TestUtil.verifyText(pro, "103-0553110");
			String customerNumber = eNetInvoiceInquiryPage.getValuefromTable("Customer Number", 2);
			
			//step5: From the Invoice Inquiry page, search using Customer #.
			eNetHomePage.clickOnApplicationWithoutFrame();
			eNetApplicationsPage.clickOnInvoiceInquiryLink();
			testUtil.switchToFrame("mainpage");
			eNetInvoiceInquiryPage.validatePageTitle("Invoice Inquiry");
			eNetInvoiceInquiryPage.enterAccNumber(customerNumber);
			eNetInvoiceInquiryPage.clickOnAccountSearch();
			
			//Report displays for selected Customer number.
			eNetInvoiceInquiryPage.validateResultsTableDisplayed();
			String customerNumber2 = eNetInvoiceInquiryPage.getValuefromTable("Customer Number", 2);
			TestUtil.verifyText(customerNumber2,customerNumber);
			
			//step6: Enter e-mail address.SUBMIT
			String pro2 = eNetInvoiceInquiryPage.getValuefromTable("Pro Number", 2);
			eNetInvoiceInquiryPage.selectProFromResults(pro2);
			eNetInvoiceInquiryPage.enterEmailID("qatest@estes-express.com");
			eNetInvoiceInquiryPage.clickOnEmailSubmit();
			
			//Receive message, "Email sent successfully."
			eNetInvoiceInquiryPage.validateSuccessMessage("Email sent successfully.");
			
			//step7: Email is received with report.//Email is received with report.
			//This step should check manually.
			
			//step8: Log off and close browser.
			eNetInvoiceBatchUploadPage.clickOnLogout();
			eNetInvoiceBatchUploadPage.clickOnLogoutIntranet();
		}	
		
		
		/** ENet  Rate Request App - Base Rate - Guaranteed Option Quote Request Data Written to GMS when BOL link sent for Guaranteed Option 
		 * */	

		@Test(enabled = true, priority = 19)

		public void executeQZ_870() throws Exception {


			//Step1: Open the following link to get the most recent general rate increase date.

			driver.get(url2);
			
			//Step2: Log into *eNet*

			eNetLoginPage.enterUserID(username16);
			eNetLoginPage.enterUserPassword(password16);
			eNetLoginPage.clickOnLoginButton();
			
			//Step3: From eNet page Click *Customer Service* in applications list
			eNetHomePage.clickOnCustomerServiceLink();

			//Step4: From the Applications page, find the *Customer Service* applications list click *Rate Retriever*
			eNetHomePage.clickOnRateRetrieverLink();
		    eNetLTLRateQuotePage.verifLTLRateQuotePage();
		    
			//Step5: From the LTL Rate Quote page, enter data in Required fields and press Submit.
			String accountNumber = "9451455";
			String weight = "1000";
			String class1 ="55";
			String desc = "Qz-870 Test";
			
			eNetLTLRateQuotePage.enterAccountNumber(accountNumber);	
			eNetLTLRateQuotePage.selectRole("Shipper");
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			String todaysDate = testUtil.getTodayDateByFormat("MM/dd/YYYY");
			eNetLTLRateQuotePage.enterPickupDate(todaysDate);
			eNetLTLRateQuotePage.enterOrigZip("22911");
			eNetLTLRateQuotePage.clickOnReqSuggests("CHARLOTTESVILLE, VA 22911");
			eNetLTLRateQuotePage.enterDestZip("23219");
			eNetLTLRateQuotePage.clickOnReqSuggests("RICHMOND, VA 23219");
			eNetLTLRateQuotePage.selectClass(class1);
		    eNetLTLRateQuotePage.enterWeight(weight);
			eNetLTLRateQuotePage.enterDesc(desc);		     
		    
			//Click on Submit button
			eNetLTLRateQuotePage.clickOnSubmitButton();
			testUtil.setHardWait(5000);

			//Verify LTL Rate Request page displays
			eNetLTLRateRequestPage.verifLTLRateRequestPage();
			
		    //Step 6: Select the radio button next to a Guaranteed Option that a price was returned for and click the Create BOL Link.
			eNetLTLRateRequestPage.SelectQuoteByServiceLevel("guaranteed12PM");
			eNetLTLRateRequestPage.clickCreateBOLLink();
			testUtil.setHardWait(5000);							
			//Step 7: Enter data in Required Fields Quote and click Submit.  Click Close

			eNetLTLRateRequestPage.switchToFancyFrame();
			eNetLTLRateRequestPage.enterBOLName("Test Email");
			eNetLTLRateRequestPage.enterBOLUserName("GOLDEN");
			eNetLTLRateRequestPage.enterBOLEmail("april.golden@estes-express.com");
			eNetLTLRateRequestPage.enterBOLComments("Testing Create BOL Link - My Estes");
			
			//Click *Submit*
			eNetLTLRateRequestPage.clickOnBOLsubmit();
			eNetLTLRateRequestPage.verifySuccessMessage();
			eNetLTLRateRequestPage.clickOnClose();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainpage");

			//Step 8: Record quote#
			String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();
			System.out.println(quoteNo);
	    	String serviceDays  = eNetLTLRateRequestPage.getServiceDays();
			String originZip = eNetLTLRateRequestPage.getRouteOrigin();
			String destZip = eNetLTLRateRequestPage.getRouteDest();

			//Record Data
			String fuel = eNetLTLRateRequestPage.getQuoteSummaryValueOf("Fuel Surcharge");
			String fuelChrg=fuel.replace("$", "").trim();
			String estFreightChrg = eNetLTLRateRequestPage.getFreightCharges();
					
			//Step 9 : Log into EXLAQA AS400.
			String userName = "PEACHDO";
			String password = "aug08y14";
			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();

			testUtil.setHardWait(3000);
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.logon(userName, password);

			//Step 10: Enter "call GMR000" in the command line and press enter.
			CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
			commandEntryScreen.enterCommand("call GMR000");
			
			//The Gold Medal Main Selection Menu is displayed.
			GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
			goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();
			
			//Step 11: Select option 1 and press Enter.
			goldMedalMainMenuScreen.enterOption("5");
			
			//Verify Work With Request Service Information screen is displayed.
			WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
			workWithRequestScreen.verifyWorkWithRequestInfoScreen();
	
			//Step 12: Find the Quote Number recorded in Step 8, enter 5 in the Opt field to the left of it, and click Enter.
			workWithRequestScreen.enterQuoteNumber(quoteNo);
			workWithRequestScreen.enterValueInOptField("5");
			//Verify The Display Request Service Information screen for the selected quote is displayed.
			DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(session);
			displayRequestServiceInfoScreen.verifyDisplayRequestScreen();

			//Step 13: Verify that all of the data recorded is displayed.
			displayRequestServiceInfoScreen.verifyQuoteDetailsInRequestScreen(quoteNo, originZip, destZip, accountNumber,serviceDays,weight,class1);

			//Step 14: Click Shift F8 to view Comments 
			displayRequestServiceInfoScreen.enterF20Key();
			
			//Verify Comments
			DisplayCommentsScreen displayCommentsScreen = new DisplayCommentsScreen(session);
			displayCommentsScreen.verifyDisplayCommentsScreen();
			
			String headerComment = " Online Req from eNet LTL";
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(headerComment);

			String[] serviceDay = serviceDays.split(" ");
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(serviceDay[0]+" day(s)");

			
			String pUDate = testUtil.getTodayDateByFormat("YYYY-MM-dd");
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("PU = "+pUDate);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(estFreightChrg);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(fuelChrg);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Cl: "+class1);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Wt: "+weight);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Ds: "+desc);
			
			//Step 15: Log out of the systems and close windows

			JagacyUtil jagacyUtil = new JagacyUtil(session);
			jagacyUtil.pressF3();
			jagacyUtil.pressF3();
			testUtil.setHardWait(5000);
			jagacyUtil.pressF3();

			//signoff

			if(session != null) {
		         session.abort();
		         session.close();
			}    
			
			//Logout
			eNetHomePage.clickOnLogout();
			eNetHomePage.clickOnLogoutButton();

		
}

		

		/**
		 * 
		 *  ENet Rate Request App - Base Rate - Guaranteed Option Quote Request Data Written to GMS when Save Quote clicked for Guaranteed option 
		 *  */	

		@Test(enabled = true, priority = 20)

		public void qz871_ENetRateRequestAppBaseRateGuaranteedOptionQuoteRequestDataWrittentoGMSwhenSaveQuoteclickedforGuaranteedOption() throws Exception {


			//Step1: Open the following link to get the most recent general rate increase date.
			driver.get(url2);
			//Step2: Log into *eNet*
			eNetLoginPage.enterUserID(username16);
			eNetLoginPage.enterUserPassword(password16);
			eNetLoginPage.clickOnLoginButton();
				
			//Step3: From eNet page Click *Customer Service* in applications list
			eNetHomePage.clickOnCustomerServiceLink();
			
			//Step4: From the Applications page, find the *Customer Service* applications list click *Rate Retriever*
			eNetHomePage.clickOnRateRetrieverLink();
		    eNetLTLRateQuotePage.verifLTLRateQuotePage();

			//Step5: From the LTL Rate Quote page, enter data in Required fields and press Submit.
			String accountNumber = "5068692";
			String weight = "1000";
			String class1 ="55";
			String desc = "Qz-871 Test";
			eNetLTLRateQuotePage.enterAccountNumber(accountNumber);	
			eNetLTLRateQuotePage.selectRole("Shipper");
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			String todaysDate = testUtil.getTodayDateByFormat("MM/dd/YYYY");
			eNetLTLRateQuotePage.enterPickupDate(todaysDate);
			eNetLTLRateQuotePage.enterOrigZip("23230");
			eNetLTLRateQuotePage.clickOnReqSuggests("RICHMOND, VA 23230");
			eNetLTLRateQuotePage.enterDestZip("30362");
			eNetLTLRateQuotePage.clickOnReqSuggests("ATLANTA, GA 30362");
			eNetLTLRateQuotePage.selectClass(class1);
		    eNetLTLRateQuotePage.enterWeight(weight);
			eNetLTLRateQuotePage.enterDesc(desc);		
			eNetLTLRateQuotePage.clickOnAccessorialByCode("INS");		    
			//Click on Submit button
			eNetLTLRateQuotePage.clickOnSubmitButton();
			testUtil.setHardWait(5000);			
			//Verify LTL Rate Request page displays
			eNetLTLRateRequestPage.verifLTLRateRequestPage();						
		    //Step 6: Select the radio button next to a Guaranteed Option that a price was returned for and click the Create BOL Link.
			eNetLTLRateRequestPage.SelectQuoteByServiceLevel("guaranteed10AM");
			eNetLTLRateRequestPage.clickSaveComments();
			testUtil.setHardWait(10000);		
			//Step 7: Record quote#

			String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();
			System.out.println(quoteNo);
		    String serviceDays  = eNetLTLRateRequestPage.getServiceDays();
			String originZip = eNetLTLRateRequestPage.getRouteOrigin();
			String destZip = eNetLTLRateRequestPage.getRouteDest();	
			String fuel = eNetLTLRateRequestPage.getQuoteSummaryValueOf("Fuel Surcharge");
			String fuelChrg=fuel.replace("$", "").trim();
			String estFreightChrg = eNetLTLRateRequestPage.getFreightCharges();
											
			//Step 8 : Log into EXLAQA AS400.

			String userName = "PEACHDO";
			String password = "aug08y14";
			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.logon(userName, password);

			//Step 9: Enter "call GMR000" in the command line and press enter.
			CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
			commandEntryScreen.enterCommand("call GMR000");
			
			//The Gold Medal Main Selection Menu is displayed.
			GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
			goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();

			//Step 10: Select option 1 and press Enter.
			goldMedalMainMenuScreen.enterOption("5");
			
			//Verify Work With Request Service Information screen is displayed.
			WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
			workWithRequestScreen.verifyWorkWithRequestInfoScreen();
			
			//Step 11: Find the Quote Number recorded in Step 8, enter 5 in the Opt field to the left of it, and click Enter.
			workWithRequestScreen.enterQuoteNumber(quoteNo);
			workWithRequestScreen.enterValueInOptField("5");

			//Verify The Display Request Service Information screen for the selected quote is displayed.
			DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(session);
			displayRequestServiceInfoScreen.verifyDisplayRequestScreen();
		
			//Verify that all of the data recorded is displayed.
			displayRequestServiceInfoScreen.verifyQuoteDetailsInRequestScreen(quoteNo, originZip, destZip, accountNumber,serviceDays,weight,class1);
		
			//Step 12: Click Shift F8 to view Comments 
			displayRequestServiceInfoScreen.enterF20Key();
		
			//Verify Comments
			DisplayCommentsScreen displayCommentsScreen = new DisplayCommentsScreen(session);
			displayCommentsScreen.verifyDisplayCommentsScreen();
		
			String headerComment = " Online Req from eNet LTL";
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(headerComment);

			
			String[] serviceDay = serviceDays.split(" ");

			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(serviceDay[0]+" day(s)");

			
			String pUDate = testUtil.getTodayDateByFormat("YYYY-MM-dd");
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("PU = "+pUDate);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(estFreightChrg);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(fuelChrg);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Cl: "+class1);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Wt: "+weight);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Ds: "+desc);
			
			//Step 13: Log out of the systems and close windows

			JagacyUtil jagacyUtil = new JagacyUtil(session);
			jagacyUtil.pressF3();
			jagacyUtil.pressF3();
			testUtil.setHardWait(5000);
			jagacyUtil.pressF3();
  
			//signoff

		if(session != null) {
		         session.abort();
		         session.close();
			}    
			
			//Logout
			eNetHomePage.clickOnLogout();
			eNetHomePage.clickOnLogoutButton();

		
}


		/* ENet Rate Request App - Base Rate - Guaranteed Option Quote Request Data Written to GMS when Send to Call Center clicked */	

		@Test(enabled = true, priority = 21)

		public void qz872_ENetRateRequestAppBaseRateGuaranteedOptionQuoteRequestDataWrittentoGMSwhenSendtoCallCenterclicked() throws Exception {

			//Step1: Open the following link to get the most recent general rate increase date.

			driver.get(url2);

			
			//Step2: Log into *eNet*
			eNetLoginPage.enterUserID(username16);
			eNetLoginPage.enterUserPassword(password16);
			eNetLoginPage.clickOnLoginButton();
				
			//Step3: From eNet page Click *Customer Service* in applications list

			eNetHomePage.clickOnCustomerServiceLink();

			
			//Step4: From the Applications page, find the *Customer Service* applications list click *Rate Retriever*

			eNetHomePage.clickOnRateRetrieverLink();

		    eNetLTLRateQuotePage.verifLTLRateQuotePage();


			//Step5: From the LTL Rate Quote page, enter data in Required fields and press Submit.

			String accountNumber = "5068692";
			String weight = "1000";
			String class1 ="55";
			String desc = "Qz-872 Test";
			
			eNetLTLRateQuotePage.enterAccountNumber(accountNumber);	
			eNetLTLRateQuotePage.selectRole("Shipper");
			eNetLTLRateQuotePage.selectTerm("Pre-paid");
			String todaysDate = testUtil.getTodayDateByFormat("MM/dd/YYYY");
			eNetLTLRateQuotePage.enterPickupDate(todaysDate);
			eNetLTLRateQuotePage.enterOrigZip("23230");
			eNetLTLRateQuotePage.clickOnReqSuggests("RICHMOND, VA 23230");
			eNetLTLRateQuotePage.enterDestZip("30362");
			eNetLTLRateQuotePage.clickOnReqSuggests("ATLANTA, GA 30362");
			eNetLTLRateQuotePage.selectClass(class1);
		    eNetLTLRateQuotePage.enterWeight(weight);
			eNetLTLRateQuotePage.enterDesc(desc);		

			eNetLTLRateQuotePage.clickOnAccessorialByCode("INS");

			//Click on Submit button

			eNetLTLRateQuotePage.clickOnSubmitButton();
			testUtil.setHardWait(5000);
			
			//Verify LTL Rate Request page displays
			eNetLTLRateRequestPage.verifLTLRateRequestPage();
					
		    //Step 6: Select the radio button next to a Guaranteed Option that a price was returned for and click the Create BOL Link.

			eNetLTLRateRequestPage.SelectQuoteByServiceLevel("guaranteed5PM");
			eNetLTLRateRequestPage.clickSendToCallCenter();
			testUtil.setHardWait(5000);

			//Step 7: Enter data in Required Fields Quote and click Submit.  Click Close
			eNetLTLRateRequestPage.switchToFancyFrame();
			eNetLTLRateRequestPage.enterfullName("Test Email");
			eNetLTLRateRequestPage.enterEmail("qa.test@estes-express.com");
			eNetLTLRateRequestPage.enterPhone("707-716-5737 x9293220");
			eNetLTLRateRequestPage.enterCommentsinSendToCallCenterPopup("Testing - Please discard this email");

			//Click *Submit*
			eNetLTLRateRequestPage.clickOnSendToCallCenterSubmit();
			eNetLTLRateRequestPage.verifySuccessMessage();
			eNetLTLRateRequestPage.clickOnClose();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainpage");

			//Step 8: Record quote#

			String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();
			System.out.println(quoteNo);
	    	String serviceDays  = eNetLTLRateRequestPage.getServiceDays();
			String originZip = eNetLTLRateRequestPage.getRouteOrigin();
			String destZip = eNetLTLRateRequestPage.getRouteDest();	
			//Record Data
			String fuel = eNetLTLRateRequestPage.getQuoteSummaryValueOf("Fuel Surcharge");
			String fuelChrg=fuel.replace("$", "").trim();
			String estFreightChrg = eNetLTLRateRequestPage.getFreightCharges();

			//Step 9 : Log into EXLAQA AS400.
			String userName = "PEACHDO";
			String password = "aug08y14";
			SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
			session.open();
			testUtil.setHardWait(3000);
			LoginScreen loginScreen = new LoginScreen(session);
			loginScreen.logon(userName, password);
			//Step 10: Enter "call GMR000" in the command line and press enter.

			CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);

			commandEntryScreen.enterCommand("call GMR000");

			
			//The Gold Medal Main Selection Menu is displayed.

			GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
			goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();

			//Step 11: Select option 1 and press Enter.
			goldMedalMainMenuScreen.enterOption("5");

			//Verify Work With Request Service Information screen is displayed.
			WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
			workWithRequestScreen.verifyWorkWithRequestInfoScreen();
			//Step 12: Find the Quote Number recorded in Step 8, enter 5 in the Opt field to the left of it, and click Enter.
			workWithRequestScreen.enterQuoteNumber(quoteNo);
			workWithRequestScreen.enterValueInOptField("5");
			//Verify The Display Request Service Information screen for the selected quote is displayed.
			DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(session);
			displayRequestServiceInfoScreen.verifyDisplayRequestScreen();

			//Step 13: Verify that all of the data recorded is displayed.
			displayRequestServiceInfoScreen.verifyQuoteDetailsInRequestScreen(quoteNo, originZip, destZip, accountNumber,serviceDays,weight,class1);
			//Step 14: Click Shift F8 to view Comments 
			displayRequestServiceInfoScreen.enterF20Key();

			//Verify Comments
			DisplayCommentsScreen displayCommentsScreen = new DisplayCommentsScreen(session);
			displayCommentsScreen.verifyDisplayCommentsScreen();
			String headerComment = " Online Req from eNet LTL";
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(headerComment);
			String[] serviceDay = serviceDays.split(" ");
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(serviceDay[0]+" day(s)");
			String pUDate = testUtil.getTodayDateByFormat("YYYY-MM-dd");
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("PU = "+pUDate);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(estFreightChrg);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen(fuelChrg);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Cl: "+class1);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Wt: "+weight);
			displayCommentsScreen.verifyDetailsInDisplaCommentsScreen("Ds: "+desc);
		
			//Step 15: Log out of the systems and close windows
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			jagacyUtil.pressF3();
			jagacyUtil.pressF3();
			testUtil.setHardWait(5000);
			jagacyUtil.pressF3();

			//signoff
			if(session != null) {
		         session.abort();
		         session.close();

			}    
	
			//Logout

			eNetHomePage.clickOnLogout();
			eNetHomePage.clickOnLogoutButton();

		
}


	
	
	
	

		
		
		
		
	
}

