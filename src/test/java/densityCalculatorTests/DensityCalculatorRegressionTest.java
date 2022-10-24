package densityCalculatorTests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
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

import org.apache.log4j.Logger;

public class DensityCalculatorRegressionTest extends TestBase{
	
 	private Logger logger = Logger.getLogger(DensityCalculatorRegressionTest.class);
	
	MyEstesHomePage myEstesHomePage= new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRecentShipmentsPage myEstesRecentShipmentsPage = new MyEstesRecentShipmentsPage();
	MyEstesDensityCalculatorPage myEstesDensityCalculatorPage = new MyEstesDensityCalculatorPage();
	MyEstesTerminalLookupPage terminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTransitTimeCalculatorPage myEstesTransitTimeCalculatorPage = new MyEstesTransitTimeCalculatorPage();
	MyEstesBillOfLadingPage myEstesBillOfLadingPage = new MyEstesBillOfLadingPage();
	MyEstesClaimsPage myEstesClaimsPage = new MyEstesClaimsPage();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();
	
	MyEstesFuelSurchargePage myEstesFuelSurchargePage = new MyEstesFuelSurchargePage();
	MyEstesShipmentTrackingPage myEstesShipmentTrackingPage = new MyEstesShipmentTrackingPage();

	CreateFreightBill createFreightBill = new CreateFreightBill();
	ReserveAFreightBill reserveAFreightBill = new ReserveAFreightBill();

	
	/*******************************TESTS *******************************/
	
	/*
	 *  Density Calculator-Verify user is able to retrieve result using Cylindrical Freight Density Calculator based on desired inputs
	 */
	
	
	@Test (enabled = true, priority = 1)
	public void executeQZ_3271() throws InterruptedException {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnDensityCalculatorFromShip();
		myEstesDensityCalculatorPage.enterCynFHeightOrLength("10");
		myEstesDensityCalculatorPage.enterCynFWeight("20");
		myEstesDensityCalculatorPage.enterCynFGreatestDimension("15");
		myEstesDensityCalculatorPage.clickOnCynFCalculateButton();
		myEstesDensityCalculatorPage.validateCylindricalFreightDensityCalculatorCalcualtetionResult();
	}

	/*
	 * Density Calculator-Verify user is able to retrieve result using Nonpalletized
	 * Freight Density Calculator based on desired inputs.
	 */
	
	@Test (enabled = true, priority = 2)
	public void executeQZ_3269() throws InterruptedException {

		

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnDensityCalculatorFromShip();
		myEstesDensityCalculatorPage.enterNonPalletizedFLength("25");
		myEstesDensityCalculatorPage.enterNonPalletizedFWidth("24");
		myEstesDensityCalculatorPage.enterNonPalletizedFHeight("23");
		myEstesDensityCalculatorPage.enterNonPalletizedFWeight("90");
		myEstesDensityCalculatorPage.clickOnNonPalFCalculateButton();
		myEstesDensityCalculatorPage.validatePalletizedAndNonpalletizedShipmentCalcualtetionResult();

	}
	

	/*
	 * Density Calculator-Verify message displays when using Cylindrical,
	 * Palletized, Nonpalletized Freight Density Calculators with a blank data field.
	 */
	@Test (enabled = true, priority = 3)
	public void executeQZ_3323() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokelocal");
		myEstesLoginPage.enterPassword("smokelocal");
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnDensityCalculatorFromShip();
		myEstesDensityCalculatorPage.enterNonPalletizedFLength("");
		myEstesDensityCalculatorPage.enterNonPalletizedFWidth("");		
		myEstesDensityCalculatorPage.enterNonPalletizedFHeight("");
		myEstesDensityCalculatorPage.enterNonPalletizedFWeight("");
		myEstesDensityCalculatorPage.clickOnNonPalFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForLengthField();
		
		myEstesDensityCalculatorPage.enterNonPalletizedFLength("1");
		myEstesDensityCalculatorPage.enterNonPalletizedFWidth("");		
		myEstesDensityCalculatorPage.enterNonPalletizedFHeight("");
		myEstesDensityCalculatorPage.enterNonPalletizedFWeight("");
		myEstesDensityCalculatorPage.clickOnNonPalFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForWidthField();
		
		myEstesDensityCalculatorPage.enterNonPalletizedFLength("1");
		myEstesDensityCalculatorPage.enterNonPalletizedFWidth("1");		
		myEstesDensityCalculatorPage.enterNonPalletizedFHeight("");
		myEstesDensityCalculatorPage.enterNonPalletizedFWeight("");
		myEstesDensityCalculatorPage.clickOnNonPalFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForHeightField();
		
		myEstesDensityCalculatorPage.enterNonPalletizedFLength("1");
		myEstesDensityCalculatorPage.enterNonPalletizedFWidth("1");		
		myEstesDensityCalculatorPage.enterNonPalletizedFHeight("1");
		myEstesDensityCalculatorPage.enterNonPalletizedFWeight("");
		myEstesDensityCalculatorPage.clickOnNonPalFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForWeightField();
		
		myEstesDensityCalculatorPage.enterCynFHeightOrLength("");
		myEstesDensityCalculatorPage.enterCynFWeight("");
		myEstesDensityCalculatorPage.enterCynFGreatestDimension("");
		myEstesDensityCalculatorPage.clickOnCynFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForHeightFieldForCylindricalFreightDensityCalculator();
		
		myEstesDensityCalculatorPage.enterCynFHeightOrLength("1");
		myEstesDensityCalculatorPage.enterCynFWeight("");
		myEstesDensityCalculatorPage.enterCynFGreatestDimension("");
		myEstesDensityCalculatorPage.clickOnCynFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForWeightForCylindricalFreightDensityCalculator();
		
		myEstesDensityCalculatorPage.enterCynFHeightOrLength("1");
		myEstesDensityCalculatorPage.enterCynFWeight("1");
		myEstesDensityCalculatorPage.enterCynFGreatestDimension("");
		myEstesDensityCalculatorPage.clickOnCynFCalculateButton();
		myEstesDensityCalculatorPage.validateHoveOverMessageForGreatestDimentionForCylindricalFreightDensityCalculator();
	}

}
