package commodityLibraryTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesAddCommodityPage;
import myEstesPages.MyEstesCommodityLibraryPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesWelcomePage;
import testBase.TestBase;

public class CommodityLibrarySmokeTest extends TestBase{

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesCommodityLibraryPage myEstesCommodityLibraryPage = new MyEstesCommodityLibraryPage();
	MyEstesAddCommodityPage myEstesAddCommodityPage = new MyEstesAddCommodityPage();
	
	/******************************* TESTS *******************************/
	
	/*
	 * Commodity Library - Verify the user can add a Commodity. //dp2-1516
	 */

	@Test(enabled = true, priority = 1)
	public void executeQZ_7152() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnCommodityLibrary();
		testUtil.setHardWait(2000);
		myEstesCommodityLibraryPage.clickOnAddCommodity();
		String prID = myEstesAddCommodityPage.enterProductID();
		myEstesAddCommodityPage.typeDescription("test for add commodity");
		myEstesAddCommodityPage.clickOnSaveButton();

		myEstesCommodityLibraryPage.validateRecordAddedtoCommodityLibraray(prID);

	}

	
}
