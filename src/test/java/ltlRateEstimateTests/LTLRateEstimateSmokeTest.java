package ltlRateEstimateTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.RateQuotePage;
import testBase.TestBase;

public class LTLRateEstimateSmokeTest extends TestBase {

	
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	
	/******************************* TESTS *******************************/
	
	
	
	//Rate Quote - LTL - Verify California Compliance Surcharge are displayed (ALM Test ID: 576)
	
	//QZ-5480- is in evniroment smoke test
	
	/*
	 * LTL Rate Estimate - Verify user can submit an LTL Rate Estimate quote and the
	 * LTL Standard Transit quote number prefix is L
	 */

	@Test(enabled = true, priority = 1)
	public void executeQZ_3284()
			throws Throwable {

		String prefix = "L";
		String expected = "RATE QUOTE DISCLOSURES AND TERMS OF SERVICE";
		testUtil.setHardWait(500);
		myEstesHomePage.clickOnStartRequestButton();
		rateQuotePage.enterContactName();
		rateQuotePage.selectOriginCountry("United States");
		rateQuotePage.enterOriginZip("16159");
		rateQuotePage.selectDestinationCountry("United States");
		rateQuotePage.enterDesZip("30307");
		rateQuotePage.enterClass("100");
		rateQuotePage.enterTotalWeight("975");
		rateQuotePage.clikOnRateEstimateSubmitButton();
		testUtil.setHardWait(3000);
		rateQuotePage.clickOnGetQuoteButton("LTL Standard Transit");

		rateQuotePage.verifyQuoteNumberPrefixIs(prefix);
		rateQuotePage.validateRateQuoteDisclosuresAndTermsOfService(expected);
	}

	
	
	
	
	
}
