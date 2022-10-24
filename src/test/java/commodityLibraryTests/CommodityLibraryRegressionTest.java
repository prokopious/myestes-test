package commodityLibraryTests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import myEstesPages.MyEstesAddCommodityPage;
import myEstesPages.MyEstesCommodityLibraryPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesWelcomePage;
import testBase.TestBase;

public class CommodityLibraryRegressionTest extends TestBase {

	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesCommodityLibraryPage myEstesCommodityLibraryPage = new MyEstesCommodityLibraryPage();
	MyEstesAddCommodityPage myEstesAddCommodityPage = new MyEstesAddCommodityPage();
	
	
	
	/*
	 * Commodity Library - Verify the user can edit Commodity details  
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_7535() throws Exception {
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(4000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();
		myEstesCommodityLibraryPage.clickEditCommodity();
		String prId = myEstesAddCommodityPage.enterProductID();
		myEstesAddCommodityPage.enterGoodsQuantity("6");
		myEstesAddCommodityPage.selectGoodsType("BAG");
		myEstesAddCommodityPage.typeDescription("test for editing commodity details.");
		myEstesAddCommodityPage.EnterTotalWeight("6000");
		myEstesAddCommodityPage.selectClass("50");
		myEstesAddCommodityPage.EnterNMFC("10", "10");
		myEstesAddCommodityPage.clickOnSaveButton();
		myEstesCommodityLibraryPage.validateRecordAddedtoCommodityLibraray(prId);

	}
	/*
	 * Commodity Library - Verify the user can delete Commodity Details  
	 */
	
	@Test(enabled = true, priority = 2)

	public void qexecuteQZ_7534() throws Exception {

		String message = "Success! Commodity deleted successfully.";
		List<String> prID = new ArrayList<String>();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();
		testUtil.setHardWait(1000);
		for (int i = 0; i < 3; i++) {
			myEstesCommodityLibraryPage.clickOnAddCommodity();
			myEstesAddCommodityPage.clickOnHazmatCheckBox();
			prID.add(myEstesAddCommodityPage.enterProductID());
			myEstesAddCommodityPage.typeDescription("test for adding Hazmat details to the commodity library");
			myEstesAddCommodityPage.clickOnSaveButton();
			myEstesCommodityLibraryPage.validateRecordAddedtoCommodityLibraray(prID.get(prID.size() - 1));
			System.out.println(prID.toString());
		}

		myEstesCommodityLibraryPage.clickSortArrow();
		testUtil.setHardWait(1000);
		for (int i = 0; i < 2; i++) {
			System.out.println(prID.get(i));
			myEstesCommodityLibraryPage.clickDeleteCommodity(prID.get(i));
			testUtil.setHardWait(2000);
			myEstesCommodityLibraryPage.clickConfirmButton();
			testUtil.setHardWait(2000);
			myEstesCommodityLibraryPage.verifySuccessMessage(message);
			testUtil.setHardWait(2000);
			driver.navigate().refresh();
			myEstesCommodityLibraryPage.verifyRecordDeletedSuccessfully(prID.get(i));
		}
	}
	
	/*
	 * Commodity Library - Verify the user can cancel Adding a Commodity by clicking
	 * on Cancel button within the modal
	 */

	@Test(enabled = true, priority = 3)

	public void executeQZ_7154()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();

		myEstesAddCommodityPage.clickOnAddCommodityButton();
		myEstesAddCommodityPage.enterProductID();
		myEstesAddCommodityPage.typeDescription("Test Description");
		myEstesAddCommodityPage.clickOnCancleButton();

		myEstesAddCommodityPage.verifyCommodityDetailsNotDisplay();

	}
	
	/*
	 * Verify the user can Add a Commodity with Hazmat details and validate it in
	 * the Maintain Commodities table
	 */

	@Test(enabled = true, priority = 4)

	public void executeQZ_7151()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();
		myEstesCommodityLibraryPage.clickOnAddCommodity();
		myEstesAddCommodityPage.clickOnHazmatCheckBox();
		String prID = myEstesAddCommodityPage.enterProductID();
		myEstesAddCommodityPage.typeDescription("test for adding Hazmat details to the commodity library");
		myEstesAddCommodityPage.clickOnSaveButton();
		myEstesCommodityLibraryPage.validateRecordAddedtoCommodityLibraray(prID);
	}
	
	
	/*
	 * Verify the user can Add a Commodity with Hazmat details and validate it in the Maintain Commodities table
	 */
	
	@Test(enabled = true, priority = 5)

	public void executeQZ_7145() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("smokenat");
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();
		myEstesCommodityLibraryPage.clickOnAddCommodity();
		myEstesAddCommodityPage.enterProductID("");
		myEstesAddCommodityPage.typeDescription("");
		myEstesAddCommodityPage.clickOnSaveButton();
		myEstesAddCommodityPage.verifyErrorMessage();

	}
	
	
}
