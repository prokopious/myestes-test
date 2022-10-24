package fuelSurchargeTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesFuelSurchargePage;
import myEstesPages.MyEstesHomePage;
import testBase.TestBase;


public class FuelSurchargeRegressionTest extends TestBase {
	
	MyEstesHomePage myEstesHomePage= new MyEstesHomePage();
	MyEstesFuelSurchargePage myEstesFuelSurchargePage = new MyEstesFuelSurchargePage();
	
	/******************************* TESTS *******************************/
	
	/*
	 * Fuel Surcharge - Verify History tab is displayed
	 */

	@Test(enabled = true, priority=1)
	
	public void executeQZ_3324() throws Exception {

		myEstesHomePage.clickOnResourcesLink();
		myEstesHomePage.clickOnFeesAndSurcharges();
		myEstesHomePage.clickOnFuelSurcharge();
		myEstesFuelSurchargePage.clickOnHistoryTab();
		myEstesFuelSurchargePage.verifyHistoryTableDisplayed();

	}
		
	
		
}
