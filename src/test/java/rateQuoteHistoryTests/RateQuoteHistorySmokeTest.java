package rateQuoteHistoryTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import rateQuotePages.VolumeTruckloadRateQuote;
import testBase.TestBase;

public class RateQuoteHistorySmokeTest extends TestBase{

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	VolumeTruckloadRateQuote vtlRateQuote = new VolumeTruckloadRateQuote();
	
	
	
	
	
	/*
	 * This test passed on 9/26/22
	 *
	 */
	
	
	//the new verbiage changed from old verbiage Time Critical/Expedited new verbiage Time Critical Guaranteed.
	//the new verbiage changed from old verbiage Less than Truckload(incl. Guaranteed) new verbiage Less than Truckload.
	// the script is updated but the manual test yet to be updated
	/** 
	 * Time Critical Rate Quote- Verify when Guaranteed LTL Standard Transit:
	 * 12PMcharges are selected the quote is saved to Quote History.<VTL-314>
	 * @throws Exception 
	 * */
	 

	@Test(enabled = true, priority = 1)

	public void executeQZ_3254() throws Exception {

		String serviceLevel = " Guaranteed LTL Standard Transit: 12PM ";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username4);
		testUtil.setHardWait(500);
		myEstesLoginPage.enterPassword(password4);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnRateQoute();
		vtlRateQuote.selectOrDeselectTimeCriticalExpedited();
		testUtil.setHardWait(1000);
		vtlRateQuote.selectOrDeselectLessThanTruckload();

		vtlRateQuote.enterContactName();
		vtlRateQuote.enterMyRole("Third Party");
		vtlRateQuote.enterYourEmail("eitqa@estes-express.com");
		vtlRateQuote.enterPhoneNo("8043531900");
		vtlRateQuote.enterTerms("Prepaid");
		vtlRateQuote.selectOriginCountry("United States");
		vtlRateQuote.enterOriginZip("16159");
		vtlRateQuote.selectDestinationCountry("United States");
		vtlRateQuote.enterDesZip("30307");
		vtlRateQuote.selectTodayDate();
		vtlRateQuote.enterClass("100");
		vtlRateQuote.enterPieces("2");
		vtlRateQuote.enterPieceType("PALLET");
		vtlRateQuote.enterTotalWeight("5000");
		vtlRateQuote.enterLength("48");
		vtlRateQuote.enterWidth("48");
		vtlRateQuote.enterHeight("48");
		// vtlRateQuote.clikOnSubmitButton();
		vtlRateQuote.clickOnSubmitButton();

		vtlRateQuote.clickOnRateQuoteButton(serviceLevel);
		testUtil.setHardWait(1000);
		String qNum = vtlRateQuote.verifyVTLQNumberPrefixIs2();
		vtlRateQuote.clickOnQouteHistoryTab();
		testUtil.setHardWait(3000);

		//vtlRateQuote.validateQouteNumber(qNum);
		vtlRateQuote.refreshAndValidateQuoteNum(qNum, 20);
	}
}






