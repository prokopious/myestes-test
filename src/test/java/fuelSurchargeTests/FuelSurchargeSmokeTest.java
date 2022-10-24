package fuelSurchargeTests;

import org.testng.annotations.Test;

import fuelSurchargePages.FeeAndSurchargesPage;
import myEstesPages.MyEstesFuelSurchargePage;
import myEstesPages.MyEstesHomePage;
import testBase.TestBase;

public class FuelSurchargeSmokeTest extends TestBase{
	
	MyEstesHomePage myEstesHomePage= new MyEstesHomePage();
	MyEstesFuelSurchargePage myEstesFuelSurchargePage = new MyEstesFuelSurchargePage();
	FeeAndSurchargesPage feeAndSurchargesPage = new FeeAndSurchargesPage();
	
	/******************************* TESTS *******************************/
	
	/**
	 * Fuel Surcharge - Verify Fuel Surcharge Table is displayed based on the
	 * National Average On-Highway Diesel Price.
	 */

	@Test(enabled = true, priority = 1)
	public void executeQZ_3292()
			throws Exception {

		myEstesHomePage.clickOnResources();
		myEstesHomePage.clickOnFeesAndSurcharges();
		feeAndSurchargesPage.clickOnFuelSurcharge();
		//myEstesFuelSurchargePage.verifySurchargesTabDisplayed();
		myEstesFuelSurchargePage.verifySurchargesTableDisplayed();
		myEstesFuelSurchargePage.clickOnHistoryTab();
		myEstesFuelSurchargePage.verifyHistoryDataDisplayed();

	}

}
