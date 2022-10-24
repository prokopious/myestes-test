package transitTimeCalculatorTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import testBase.TestBase;
import transitTimeCalculatorPages.TransitTimeCalculatorPage;

public class TransitTimeCalculatorRegressionTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	TransitTimeCalculatorPage transitTimeCalculatorPage = new TransitTimeCalculatorPage();

	/******************************* TESTS *******************************/

	/*
	 * Transit Time Calculator - Unauthenticated - Verify error message is displayed
	 * when required field is left blank.
	 */
	
	@Test(enabled = true, priority = 1)
	public void executeQZ_7721() {

		String errorMsg = "  ERROR: There are validation errors in the form. Please review the form and submit again.";

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTransitTimeCalculator(); 
		transitTimeCalculatorPage.clickOnSubmitButton();
		String error = transitTimeCalculatorPage.validateErrorMessageForAllRequiredField();
		testUtil.setHardWait(2000);
		Assert.assertEquals(error, errorMsg);

	}

	/*
	 * Transit Time Calculator - Unauthenticated - Validate user is able to Select
	 * Origin as Canada and Destination as USA, Canada, Mexico and Retrieve Transit
	 * Time Information.
	 */

	@Test(enabled = true, priority = 2)
	public void executeQZ_7719() {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTransitTimeCalculator(); // TURN IT ON
		transitTimeCalculatorPage.selectOriginCountry("Canada");
		transitTimeCalculatorPage.enterOriginZipCode("M4C1V2");
		transitTimeCalculatorPage.selectDestinationCountry("United States");
		transitTimeCalculatorPage.enterDestinationZipCode("23230");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Canada");
		transitTimeCalculatorPage.enterOriginZipCode("M4C1V2");
		transitTimeCalculatorPage.selectDestinationCountry(" Canada ");
		transitTimeCalculatorPage.enterDestinationZipCode("H1A0A1");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Canada");
		transitTimeCalculatorPage.enterOriginZipCode("M4C1V2");
		transitTimeCalculatorPage.selectDestinationCountry(" Mexico ");
		transitTimeCalculatorPage.enterDestinationZipCode("55020");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();
	}

	/*
	 * Transit Time Calculator - Unauthenticated - Validate user is able to Select
	 * Origin as Mexico and Destination as USA, Canada, Mexico and Retrieve Transit
	 * Time Information.
	 */
	@Test(enabled = true, priority = 3)

	public void executeQZ_7718() {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTransitTimeCalculator(); // TURN IT ON
		transitTimeCalculatorPage.selectOriginCountry("Mexico");
		transitTimeCalculatorPage.enterOriginZipCode("65200");
		transitTimeCalculatorPage.selectDestinationCountry("United States");
		transitTimeCalculatorPage.enterDestinationZipCode("23230");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Mexico");
		transitTimeCalculatorPage.enterOriginZipCode("65200");
		transitTimeCalculatorPage.selectDestinationCountry("Canada");
		transitTimeCalculatorPage.enterDestinationZipCode("M4C1V2");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Mexico");
		transitTimeCalculatorPage.enterOriginZipCode("65200");
		transitTimeCalculatorPage.selectDestinationCountry(" Mexico ");
		transitTimeCalculatorPage.enterDestinationZipCode("31000");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();
	}

	/*
	 * Transit Time Calculator - Unauthenticated - Validate user is able to Select
	 * Origin as USA and Destination as USA, Canada, Mexico and Retrieve Transit
	 * Time Information
	 */
	
	@Test(enabled = true, priority = 4)

	public void executeQZ_7714() {

		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTransitTimeCalculator(); // TURN IT ON
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		transitTimeCalculatorPage.selectDestinationCountry(" United States ");
		transitTimeCalculatorPage.enterDestinationZipCode("90224");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateServiceDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		transitTimeCalculatorPage.selectDestinationCountry(" Canada ");
		transitTimeCalculatorPage.enterDestinationZipCode("M4C1V2");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		transitTimeCalculatorPage.selectDestinationCountry(" Mexico ");
		transitTimeCalculatorPage.enterDestinationZipCode("65200");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();
	}

	/*
	 * Transit Time Calculator - Authenticated - Validate user is able to Select
	 * Origin as USA and Destination as USA, Canada, Mexico and Retrieve Transit
	 * Time Information.
	 */

	@Test(enabled = true, priority = 5)

	public void executeQZ_7717()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnTransitTimeCalculator();
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		transitTimeCalculatorPage.selectDestinationCountry(" United States ");
		transitTimeCalculatorPage.enterDestinationZipCode("90224");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateServiceDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		transitTimeCalculatorPage.selectDestinationCountry(" Canada ");
		transitTimeCalculatorPage.enterDestinationZipCode("M4C1V2");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		transitTimeCalculatorPage.selectDestinationCountry(" Mexico ");
		transitTimeCalculatorPage.enterDestinationZipCode("65200");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

	}

	/*
	 * Transit Time Calculator - Authenticated - Validate user is able to Select
	 * Origin as Canada and Destination as USA, Canada, Mexico and Retrieve Transit
	 * Time Information.
	 */
	@Test(enabled = true, priority = 6)

	public void executeQZ_7716()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnTransitTimeCalculator();
		testUtil.setHardWait(2000);
		transitTimeCalculatorPage.selectOriginCountry("Canada");
		transitTimeCalculatorPage.enterOriginZipCode("M4C1V2");
		transitTimeCalculatorPage.selectDestinationCountry("United States");
		transitTimeCalculatorPage.enterDestinationZipCode("23230");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Canada");
		transitTimeCalculatorPage.enterOriginZipCode("M4C1V2");
		transitTimeCalculatorPage.selectDestinationCountry(" Canada ");
		transitTimeCalculatorPage.enterDestinationZipCode("H1A0A1");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Canada");
		transitTimeCalculatorPage.enterOriginZipCode("M4C1V2");
		transitTimeCalculatorPage.selectDestinationCountry(" Mexico ");
		transitTimeCalculatorPage.enterDestinationZipCode("55020");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();
	}

	/*
	 * Transit Time Calculator - Authenticated - Verify error message is displayed
	 * when required field is left blank
	 */

	@Test(enabled = true, priority = 7)

	public void executeQZ_7712() throws InterruptedException {

		String errorMsg = "  ERROR: There are validation errors in the form. Please review the form and submit again.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnTransitTimeCalculator();
		transitTimeCalculatorPage.clickOnSubmitButton();
		testUtil.setHardWait(2000);
		String error = transitTimeCalculatorPage.validateErrorMessageForAllRequiredField();
		Assert.assertEquals(error, errorMsg);
	}

	/*
	 * Transit Time Calculator - Authenticated - Validate user is able to Select
	 * Origin as Mexico and Destination as USA, Canada, Mexico and Retrieve Transit
	 * Time Information.
	 */
	@Test(enabled = true, priority = 8)

	public void executeQZ_7710()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(4000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnShipTab();
		myEstesWelcomePage.clickOnTransitTimeCalculator();
		transitTimeCalculatorPage.selectOriginCountry("Mexico");
		transitTimeCalculatorPage.enterOriginZipCode("33942");
		transitTimeCalculatorPage.selectDestinationCountry(" United States ");
		transitTimeCalculatorPage.enterDestinationZipCode("23220");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Mexico");
		transitTimeCalculatorPage.enterOriginZipCode("33942");
		transitTimeCalculatorPage.selectDestinationCountry(" Canada ");
		transitTimeCalculatorPage.enterDestinationZipCode("M5A0A4");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

		transitTimeCalculatorPage.clickOnClearButton();
		transitTimeCalculatorPage.selectOriginCountry("Mexico");
		transitTimeCalculatorPage.enterOriginZipCode("33942");
		transitTimeCalculatorPage.selectDestinationCountry(" Mexico ");
		transitTimeCalculatorPage.enterDestinationZipCode("32721");
		transitTimeCalculatorPage.selectShippingDate();
		transitTimeCalculatorPage.clickOnSubmitButton();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();
	}

	/*
	 * Transit Time Calculator - Unauthenticated- Verify the user is able to
	 * calculate based on desired locations on Transit Time Calculator
	 */
	@Test(enabled = true, priority = 9)

	public void executeQZ_6085()
			throws InterruptedException {

		// Navigate to Shipment tab -> Transit Time Calculator
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTransitTimeCalculator(); // TURN IT ON

		// Fill origin details
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");

		// Fill Destination details
		transitTimeCalculatorPage.selectDestinationCountry("United States");
		transitTimeCalculatorPage.enterDestinationZipCode("30002");
		// Click on submit
		transitTimeCalculatorPage.clickOnSubmitButton();
		testUtil.setHardWait(3000);
		// Verify Transit time details section are displayed
		transitTimeCalculatorPage.validateOriginPopulated("RICHMOND, VA 23230");
		transitTimeCalculatorPage.validateDestinationPopulated("AVONDALE ESTATES, GA 30002");
		transitTimeCalculatorPage.validateServiceDisplay();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();

	}
	
	/*
	 * Transit Time Calculator - Unauthenticated -TransitTimeCalculatorScreen
	 */
	
	@Test(enabled = true, priority = 10)

	public void executeQZ_5805() {

		// Navigate to Shipment tab -> Transit Time Calculator
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnTransitTimeCalculator(); 
		
		// Fill origin details
		transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		
		// Fill Destination details
		transitTimeCalculatorPage.selectDestinationCountry(" United States ");
		transitTimeCalculatorPage.enterDestinationZipCode("90224");
		
		//Select shipment date
		transitTimeCalculatorPage.selectShippingDate();
		
		// Click on submit
		transitTimeCalculatorPage.clickOnSubmitButton();
		
		//Capture and save the value for the service 
		transitTimeCalculatorPage.validateServiceDisplay();
		
		
	}
}
