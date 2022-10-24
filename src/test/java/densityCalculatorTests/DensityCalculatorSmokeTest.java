package densityCalculatorTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import densityCalculatorPages.MyEstesDensityCalculatorPage;
import myEstesPages.MyEstesHomePage;
import testBase.TestBase;


public class DensityCalculatorSmokeTest extends TestBase{
	
	
	MyEstesHomePage myEstesHomePage= new MyEstesHomePage();
	MyEstesDensityCalculatorPage myEstesDensityCalculatorPage = new MyEstesDensityCalculatorPage();
	
	/*******************************TESTS *******************************/
	
	
	/*
	 * Density Calculator-Verify user is able to retrieve result using Palletized
	 * Freight Density Calculator based on desired inputs.
	 */
	
	@Test (enabled = true, priority = 1,retryAnalyzer= analyzer.Retry.class)
	public void executeQZ_3270() throws InterruptedException {
		
		String length="46";
		String width="42";
		String totalHeight="6";
		String weight="120";
		String result="17.89";
		
		
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnDensityCalculatorFromShip();
		myEstesDensityCalculatorPage.enterPalletizedFLength(length);
		myEstesDensityCalculatorPage.enterPalletizedFWidth(width);
		myEstesDensityCalculatorPage.enterPalletizedFHeightOfPallet(totalHeight);
		myEstesDensityCalculatorPage.enterPalletizedFWeight(weight);
		myEstesDensityCalculatorPage.clickOnPalFCalculateButton();
		
		String caculationResult=myEstesDensityCalculatorPage.getPalletizedFreightDensity();
		Assert.assertEquals(caculationResult, result);
		
		
	}

}
