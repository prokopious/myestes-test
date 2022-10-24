package terminalLookupTests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesBillOfLadingPage;
import myEstesPages.MyEstesClaimsPage;
import myEstesPages.MyEstesDensityCalculatorPage;
import myEstesPages.MyEstesFuelSurchargePage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRecentShipmentsPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesShipmentTrackingPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesTerminalLookupPage;
import myEstesPages.MyEstesTransitTimeCalculatorPage;
import testBase.TestBase;
import util.CreateFreightBill;
import util.ReserveAFreightBill;

public class TerminalLookupRegressionTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesTerminalLookupPage terminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTerminalLookupPage myEstesTerminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	MyEstesDensityCalculatorPage myEstesDensityCalculatorPage = new MyEstesDensityCalculatorPage();

	MyEstesTransitTimeCalculatorPage myEstesTransitTimeCalculatorPage = new MyEstesTransitTimeCalculatorPage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	MyEstesClaimsPage myEstesClaimsPage = new MyEstesClaimsPage();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();

	MyEstesFuelSurchargePage myEstesFuelSurchargePage = new MyEstesFuelSurchargePage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();

	CreateFreightBill createFreightBill = new CreateFreightBill();
	ReserveAFreightBill reserveAFreightBill = new ReserveAFreightBill();

	/******************************* TESTS *******************************/

	/*
	 * Terminal Lookup - Authenticated - Verify User is able to search for Terminals
	 * by Province - Canada.
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_3265() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		myEstesTerminalLookupPage.selectCountry();
		testUtil.setHardWait(40000);
		myEstesTerminalLookupPage.selectCanada();
		//testUtil.setHardWait(9999999);
		myEstesTerminalLookupPage.lookUpBy();
		myEstesTerminalLookupPage.clickOnLookUpByProvince();
		myEstesTerminalLookupPage.clickOnSelectProvince();
		myEstesTerminalLookupPage.selectProvince("ON");
		myEstesTerminalLookupPage.clickOnLOOKUP();
		Thread.sleep(2000);
		myEstesTerminalLookupPage.verifyTerminal();
		myEstesTerminalLookupPage.verifyTerAddress();
		myEstesTerminalLookupPage.verifyTerPhone();
		myEstesTerminalLookupPage.verifyTerFax();
		myEstesTerminalLookupPage.clickOnCaretSymbol();
		myEstesTerminalLookupPage.verifyTerminalAddress();
		myEstesTerminalLookupPage.verifyTerEmail();
		myEstesTerminalLookupPage.verifyTerNCovMap();
		myEstesTerminalLookupPage.verifyLTLRateQuoteCalRate();
		myEstesTerminalLookupPage.verifyweCalTTCalTime();
		myEstesTerminalLookupPage.verifyLTLPRRequestPickup();
	}

	/*
	 * Terminal Lookup - Authenticated - Verify User is able to search for Terminals
	 * by Postal Code, City and State - Mexico
	 */

	@Test(enabled = false, priority = 2) // ******waiting for defect fix *********
	public void executeQZ_3277() throws InterruptedException {

		// String Expected_TerAddress = "506 Enterprise Street";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		// Step: 4
		myEstesHomePage.clickOnTerminallookupLink();

		myEstesTerminalLookupPage.selectCountry();
		myEstesTerminalLookupPage.selectMexico();

		myEstesTerminalLookupPage.enterZipCode("34000");
		// myEstesTerminalLookupPage.clickOnFirstZipCodeDDown();
		Thread.sleep(2000);
		myEstesTerminalLookupPage.clickOnLOOKUP();
		Thread.sleep(1000);
		myEstesTerminalLookupPage.verifyTerminal();
		myEstesTerminalLookupPage.verifyTerAddress();
		myEstesTerminalLookupPage.verifyTerPhone();
		myEstesTerminalLookupPage.verifyTerFax();
		Thread.sleep(1000);
		// String Actual_TerAddress = myEstesTerminalLookupPage.getTerminalAddress();
		// assertEquals(Actual_TerAddress, Expected_TerAddress);
		myEstesTerminalLookupPage.verifyTerEmail();
		myEstesTerminalLookupPage.verifyTerManager();
		myEstesTerminalLookupPage.verifyTerNextDCov();

		// ***** Wait till BUG fixed For next STEP ******
		myEstesTerminalLookupPage.verifyTerNCovMap();

		myEstesTerminalLookupPage.verifyLTLRateQCalFrom();
		myEstesTerminalLookupPage.verifyLTLRateQCalTo();
		myEstesTerminalLookupPage.verifyTTCalculateFrom();
		myEstesTerminalLookupPage.verifyTTCalculateTo();
		myEstesTerminalLookupPage.verifyLTLPRRequestPickup();
	}

	/*
	 * Terminal Lookup - Unauthenticated - Verify User is able to search for
	 * Terminals by State - US.
	 */
	@Test(enabled = true, priority = 3)
	public void executeQZ_3259() throws InterruptedException {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupFromShip();
		testUtil.setHardWait(2000);
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
	 * Terminal Lookup -Unauthenticated - Verify User is able to search for
	 * Terminals by Postal Code, City and Province - Canada
	 */

	@Test(enabled = true, priority = 4)
	public void executeQZ_3276()
			throws InterruptedException {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupFromShip();
		myEstesTerminalLookupPage.selectCountry();
		myEstesTerminalLookupPage.selectCanada();
		myEstesTerminalLookupPage.lookUpBy();
		myEstesTerminalLookupPage.clickOnLookUpByPostalCode();
		myEstesTerminalLookupPage.enterZipCode("T2C3X4");
		myEstesTerminalLookupPage.clickOnLOOKUP();
		myEstesTerminalLookupPage.verifyTerminal();
		myEstesTerminalLookupPage.verifyTerAddress();
		myEstesTerminalLookupPage.verifyTerPhone();
		myEstesTerminalLookupPage.verifyTerFax();
		myEstesTerminalLookupPage.verifyTerEmail();
		myEstesTerminalLookupPage.verifyTerNCovMap();
		myEstesTerminalLookupPage.verifyLTLRateQCalFrom();
		myEstesTerminalLookupPage.verifyLTLRateQCalTo();
		myEstesTerminalLookupPage.verifyTTCalculateFrom();
		myEstesTerminalLookupPage.verifyTTCalculateTo();
		myEstesTerminalLookupPage.verifyLTLPRRequestPickup();
	}

	/*
	 * Terminal Lookup-qz7704 -Unauthenticated - Verify the error messages are
	 * received when all required fields are left blank.
	 */

	@Test(enabled = true, priority = 5)
	public void executeQZ_7704()
			throws InterruptedException {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupFromShip();
		testUtil.setHardWait(2000);
		myEstesTerminalListPage.clickOnLookUp();
		testUtil.setHardWait(3000);
		myEstesTerminalListPage.validateErrorMessage();

	}
	/*
	 * Terminal Lookup-qz7709 -Authenticated - Verify the error messages are
	 */

	@Test(enabled = true, priority = 6)
	public void executeQZ_7709()
			throws InterruptedException {
		testUtil.setHardWait(500);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		myEstesTerminalListPage.clickOnLookUp();
		testUtil.setHardWait(1000);
		myEstesTerminalListPage.validateErrorMessage();

	}

	/*
	 * Terminal Lookup - Verify Nunavut (NU) terminal information is displayed
	 */
	@Test(enabled = true, priority = 7)
	public void executeQZ_7703() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		terminalLookupPage.selectCountry();
		terminalLookupPage.selectCanada();
		terminalLookupPage.lookUpBy();
		terminalLookupPage.clickOnLookUpByProvince();
		Thread.sleep(1000);
		terminalLookupPage.clickOnSelectProvince();
		Thread.sleep(1000);
		terminalLookupPage.selectProvince("NU");
		terminalLookupPage.clickOnLOOKUP();
		terminalLookupPage.verifyTerminal();
		terminalLookupPage.verifyTerAddress();
		terminalLookupPage.verifyTerPhone();
		terminalLookupPage.verifyTerFax();

		terminalLookupPage.clickOnExpandMore();
		terminalLookupPage.verifyTerminalName();
		terminalLookupPage.verifyTerminalAddress();
		terminalLookupPage.verifyTerminalPhone();
		terminalLookupPage.verifyTerminalEmail();
		terminalLookupPage.verifyTerminalFax();
		terminalLookupPage.verifyTerminalManager();
		terminalLookupPage.verifyTerminalNationalCoverageMap();
		terminalLookupPage.verifyTerminalGetRateQuote();
		terminalLookupPage.verifyCalculateRate();
		terminalLookupPage.verifyTerminalCalculateTransitTime();
		terminalLookupPage.verifyCalculateTime();
		terminalLookupPage.verifyTerminalLtlPickupRequest();
		terminalLookupPage.verifyRequestPickup();

	}

	/*
	 * Terminal Lookup - Verify Alaska (AK) terminal information is displayed
	 */
	@Test(enabled = true, priority = 8)
	public void executeQZ_7707() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		terminalLookupPage.selectCountry();
		terminalLookupPage.selectUS();
		terminalLookupPage.lookUpBy();
		Thread.sleep(1000);
		terminalLookupPage.clickOnlookUpByState();
		Thread.sleep(1000);
		terminalLookupPage.clickOnSelectState();
		Thread.sleep(3000);
		terminalLookupPage.selectState("AK");
		Thread.sleep(1000);
		terminalLookupPage.clickOnLOOKUP();
		terminalLookupPage.verifyTerminal();
		terminalLookupPage.verifyTerAddress();
		terminalLookupPage.verifyTerPhone();
		terminalLookupPage.verifyTerFax();

		terminalLookupPage.clickOnExpandMore();
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
	 * Terminal Lookup - Verify Virgin Island (VI) terminal information is displayed
	 */
	@Test(enabled = true, priority = 9)
	public void executeQZ_7706() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		terminalLookupPage.selectCountry();
		terminalLookupPage.selectUS();
		terminalLookupPage.lookUpBy();
		Thread.sleep(1000);
		terminalLookupPage.clickOnlookUpByState();
		Thread.sleep(1000);
		terminalLookupPage.clickOnSelectState();
		Thread.sleep(3000);
		terminalLookupPage.selectState("VI");
		Thread.sleep(1000);
		terminalLookupPage.clickOnLOOKUP();
		terminalLookupPage.verifyTerminal();
		terminalLookupPage.verifyTerAddress();
		terminalLookupPage.verifyTerPhone();
		terminalLookupPage.verifyTerFax();

		terminalLookupPage.clickOnExpandMore();
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
	 * Terminal Lookup - Authenticated - Verify if terminal does not offer Next-day
	 * Coverage then the Next-Day Coverage row is hidden - Canada
	 */
	@Test(enabled = true, priority = 10)
	public void executeQZ_7705()
			throws InterruptedException {
		testUtil.setHardWait(500);
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupLink();
		terminalLookupPage.selectCountry();
		terminalLookupPage.selectCanada();
		terminalLookupPage.lookUpBy();
		terminalLookupPage.clickOnLookUpByProvince();
		Thread.sleep(1000);
		terminalLookupPage.clickOnSelectProvince();
		Thread.sleep(1000);
		terminalLookupPage.selectProvince("AB");
		terminalLookupPage.clickOnLOOKUP();
		terminalLookupPage.clickOnExpandMore();
		terminalLookupPage.verifyTerminalNextDayCoverageisHidden();
	}

	/*
	 * Terminal Lookup - Unauthenticated - Verify User is able to search for
	 * Terminals by State - Mexico.
	 */
	@Test(enabled = true, priority = 11)
	public void executeQZ_7700() throws InterruptedException {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupFromShip();
		terminalLookupPage.selectCountry();
		testUtil.setHardWait(1000);
		terminalLookupPage.selectMexico();
		terminalLookupPage.lookUpBy();
		Thread.sleep(1000);
		terminalLookupPage.selectState();
		Thread.sleep(3000);
		terminalLookupPage.clickOnLOOKUP();
		terminalLookupPage.verifyTerminal();
		terminalLookupPage.verifyTerAddress();
		terminalLookupPage.verifyTerPhone();
		terminalLookupPage.verifyTerFax();

		terminalLookupPage.clickOnExpandMore();
		terminalLookupPage.verifyTerminalName();
		terminalLookupPage.verifyTerminalAddress();
		terminalLookupPage.verifyTerminalPhone();
		terminalLookupPage.verifyTerminalEmail();
		terminalLookupPage.verifyTerminalFax();
		terminalLookupPage.verifyTerminalManager();
		terminalLookupPage.verifyTerminalNextDayCoverage();
		terminalLookupPage.verifyTerminalNationalCoverageMap();
	}

	/*
	 * Terminal Lookup - Unauthenticated - Verify Error message is displayed when
	 * entered Zip, City and State for a different country
	 */
	@Test(enabled = true, priority = 12)
	public void executeQZ_7702()
			throws InterruptedException {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTerminallookupFromShip();
		terminalLookupPage.selectCountry();
		terminalLookupPage.selectCanada();
		terminalLookupPage.lookUpBy();
		Thread.sleep(1000);
		terminalLookupPage.clickOnLookUpByPostalCode();
		Thread.sleep(3000);
		myEstesTerminalLookupPage.enterPostalCode("23230");
		myEstesTerminalLookupPage.enterCity("Richmond");
		myEstesTerminalLookupPage.enterProvince("VA");
		myEstesTerminalLookupPage.clickOnLOOKUP();
		myEstesTerminalLookupPage.verifyErrorMessage();

	}

}
