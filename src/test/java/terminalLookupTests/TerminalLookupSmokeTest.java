package terminalLookupTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesTerminalLookupPage;
import testBase.TestBase;

public class TerminalLookupSmokeTest extends TestBase {
	
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesTerminalLookupPage terminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTerminalLookupPage myEstesTerminalLookupPage = new MyEstesTerminalLookupPage();
	
	
	
	/*
	 * Terminal Lookup - Authenticated - Verify User is able to search for Terminals
	 * by State - US.
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_3279() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password1);
		
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		myEstesTerminalLookupPage.lookUpBy();
		myEstesTerminalLookupPage.clickOnlookUpByState();
		myEstesTerminalLookupPage.clickOnSelectState();
		myEstesTerminalLookupPage.selectState("VA");
		myEstesTerminalLookupPage.clickOnLOOKUP();

		myEstesTerminalLookupPage.verifyTerminal();
		myEstesTerminalLookupPage.verifyTerAddress();
		myEstesTerminalLookupPage.verifyTerPhone();
		myEstesTerminalLookupPage.verifyTerFax();

		myEstesTerminalLookupPage.clickOnCaretSymbol();

		terminalLookupPage.verifyTerminalName();
		terminalLookupPage.verifyTerminalAddress();
		terminalLookupPage.verifyTerminalPhone();
		terminalLookupPage.verifyTerminalEmail();
		terminalLookupPage.verifyTerminalFax();
		terminalLookupPage.verifyTerminalManager();
		terminalLookupPage.verifyTerminalNextDayCoverage();
		terminalLookupPage.verifyTerminalNationalCoverageMap();
		terminalLookupPage.verifyTerminalGetRateQuote();
		terminalLookupPage.verifyCalculateRate();
		terminalLookupPage.verifyTerminalCalculateTransitTime();
		terminalLookupPage.verifyCalculateTime();
		terminalLookupPage.verifyTerminalLtlPickupRequest();
		terminalLookupPage.verifyRequestPickup();
	}

	
	
	/*
	 * Terminal Lookup - Unauthenticated - Verify User is able to search for
	 * Terminals by Zip, City and State - US.
	 */
	@Test(enabled = true, priority = 2)
	public void executeQZ_3258() throws InterruptedException {

		String zip = "24566";

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupFromShip();
		testUtil.setHardWait(1000);
		myEstesTerminalLookupPage.lookUpBy();
		myEstesTerminalLookupPage.lookUpByZip();
		myEstesTerminalLookupPage.enterZipCode(zip);
		myEstesTerminalLookupPage.clickOnLOOKUP();

		myEstesTerminalLookupPage.verifyTerminal();
		myEstesTerminalLookupPage.verifyTerAddress();
		myEstesTerminalLookupPage.verifyTerPhone();
		myEstesTerminalLookupPage.verifyTerFax();

		myEstesTerminalLookupPage.clickOnExpandMore();

		terminalLookupPage.verifyTerminalName();
		terminalLookupPage.verifyTerminalAddress();
		terminalLookupPage.verifyTerminalPhone();
		terminalLookupPage.verifyTerminalEmail();
		terminalLookupPage.verifyTerminalFax();
		terminalLookupPage.verifyTerminalManager();
		terminalLookupPage.verifyTerminalNextDayCoverage();
		terminalLookupPage.verifyTerminalNationalCoverageMap();
		terminalLookupPage.verifyTerminalGetRateQuote();

		terminalLookupPage.getRateQuoteFrom(zip);
		terminalLookupPage.getRateQuoteTo(zip);

		terminalLookupPage.verifyTerminalCalculateTransitTime();

		terminalLookupPage.calculateTransitTimeFrom(zip);
		terminalLookupPage.calculateTransitTimeTo(zip);

		terminalLookupPage.verifyTerminalLtlPickupRequest();
		terminalLookupPage.verifyRequestPickup();
	}
}
