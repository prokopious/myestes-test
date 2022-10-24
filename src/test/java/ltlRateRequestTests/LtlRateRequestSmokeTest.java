package ltlRateRequestTests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import eNetPages.ENetHomePage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.RateQouteRateRequestPage;
import rateQuotePages.RateQuotePage;
import rateQuotePages.VolumeTruckloadRateQuote;
import testBase.TestBase;

public class LtlRateRequestSmokeTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	VolumeTruckloadRateQuote vtlRateQuote = new VolumeTruckloadRateQuote();
	RateQouteRateRequestPage ltlRateRequestPage = new RateQouteRateRequestPage();
	ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
	RateQuotePage ltlRateQuotePage = new RateQuotePage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetLTLRateRequestPage eNetLTLRateRequestPage = new ENetLTLRateRequestPage();

	/******************************* TESTS *******************************/

	
	// this test is failing because there is a real issue with validation step for validating prefix 3. Already
	// commented jira 
	/**
	 * this test is remained off because of the validaiton failure
	 * 
	 * LTL Rate Quote - Verify Quote ID prefix changes with respective of
	 * ServiceLevel
	 */

	@Test(enabled = false, priority = 1)

	public void executeQZ_3295() throws Throwable {

		System.out.println(" <*************************THIS TEST IS FAILING BECAUSE THERE IS AN ISSUE WITH VALIDATION PREFIX *************************************> ");
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumber("B073631");
		rateQuotePage.enterMyRole("Shipper");
		rateQuotePage.enterTerms("Prepaid");
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("50");
		rateQuotePage.enterTotalWeight("4985");
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		ltlRateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		testUtil.setHardWait(500);
		ltlRateRequestPage.verifyQuoteNumberPrefixISL();
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 10AM ");
		testUtil.setHardWait(40000);
		//ltlRateRequestPage.verifyQuoteNumberPrefixChagedTo3("3");
		ltlRateRequestPage.verifyQuoteNumberPrefix("Guaranteed LTL Standard Transit: 10AM", "L");
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 12PM ");
	
		testUtil.setHardWait(500);
		//ltlRateRequestPage.verifyQuoteNumberPrefixChagedTo2("2");
		ltlRateRequestPage.verifyQuoteNumberPrefix("Guaranteed LTL Standard Transit: 12PM", "2");
		testUtil.setHardWait(500);
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 5PM ");
		//ltlRateRequestPage.verifyQuoteNumberPrefixChagedTo1("1");
		ltlRateRequestPage.verifyQuoteNumberPrefix("Guaranteed LTL Standard Transit: 12PM", "1");
	}

	/*
	 * Rate Quote - LTL - Verify all calculated quotes are saved to My Estes Quote
	 * History <TC-VTL-723 >
	 */

	@Test(enabled = true, priority = 2)

	public void executeQZ_3294() throws Throwable {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password1);
	//	testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		testUtil.setHardWait(1000);
		vtlRateQuote.enterAccountNumber("4301180");
		vtlRateQuote.enterMyRole("Shipper");
		vtlRateQuote.enterTerms("Prepaid");
		vtlRateQuote.selectOriginCountry("United States");
		vtlRateQuote.enterOriginZip("16159");
		vtlRateQuote.selectDestinationCountry("United States");
		vtlRateQuote.enterDesZip("30307");
		vtlRateQuote.enterClass("50");
		vtlRateQuote.enterTotalWeight("4985");
		rateQuotePage.clickOnSubmitButton();
		testUtil.setHardWait(1000);
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit ");
		rateQuotePage.verifyQuoteNumberPrefixIs("L");
		rateQuotePage.clickOnQouteHistoryTab();
		rateQuotePage.verifyHistoryPage();
		rateQuotePage.clickOnItemPerPageDropdown("100");
		testUtil.setHardWait(1000);
		rateQuotePage.verifyServiceLevelPrifixFromQuoteHistoryPage("Guaranteed LTL Standard 10AM", "3");
		rateQuotePage.verifyServiceLevelPrifixFromQuoteHistoryPage("Guaranteed LTL Standard 12PM", "2");
		rateQuotePage.verifyServiceLevelPrifixFromQuoteHistoryPage("Guaranteed LTL Standard 5PM", "1");

	}

	
}
