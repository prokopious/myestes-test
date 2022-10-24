package transitTimeCalculatorTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import testBase.TestBase;
import transitTimeCalculatorPages.TransitTimeCalculatorPage;

	
public class TransitTimeCalculatorSmokeTest extends TestBase{
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	TransitTimeCalculatorPage transitTimeCalculatorPage=new TransitTimeCalculatorPage();

	/******************************* TESTS *******************************/
	
	@Test(enabled = true, priority = 1)
	
	public void executeQZ_3255() throws InterruptedException  {
		
		//Launch application
		myEstesHomePage.clickOnMyEstes();
		//Login to application
		myEstesHomePage.clickOnLogin();
	  	myEstesLoginPage.enterUserName(username1);
	  	myEstesLoginPage.enterPassword(password1);
	  	testUtil.setHardWait(3000);
	  	myEstesLoginPage.clickOnLoginButton();
	  
	  	//Navigate to Shipment tab -> Transit Time Calculator
	  	myEstesHomePage.clickOnShipTab();
	    myEstesHomePage.clickOnTransitTimeCalculator();
		testUtil.setHardWait(2000);
	  	//Fill origin details
	  	transitTimeCalculatorPage.selectOriginCountry("United States");
		transitTimeCalculatorPage.enterOriginZipCode("23230");
		
		//Fill Destination details
		transitTimeCalculatorPage.selectDestinationCountry("United States");
		transitTimeCalculatorPage.enterDestinationZipCode("30002");
		
		//Click on submit
		transitTimeCalculatorPage.clickOnSubmitButton();
		testUtil.setHardWait(3000);
		
		//Verify Transit time details section are displayed
		transitTimeCalculatorPage.validateOriginPopulated("RICHMOND, VA 23230");
		transitTimeCalculatorPage.validateDestinationPopulated("AVONDALE ESTATES, GA 30002");
		transitTimeCalculatorPage.validateServiceDisplay();
		transitTimeCalculatorPage.validateOriginSuportingTerminalDisplay();
		transitTimeCalculatorPage.validateDestinationSuportingTerminalDisplay();
			
	}

	
} 

