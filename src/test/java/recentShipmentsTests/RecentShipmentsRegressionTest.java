package recentShipmentsTests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import testBase.TestBase;

public class RecentShipmentsRegressionTest extends TestBase{

	
	
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	
	/****************************** TESTS ******************************/
	
	// QZ-3296
	// Welcome Page - NATIONAL Account - Verify Error message is displayed when
	// Account Lookup editbox is left blank

	@Test(enabled = true, priority = 1)

	public void executeQZ_3296() throws InterruptedException {

		String Expected_errorMessage = "This field is required.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName(username1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnRecentShipments();
		testUtil.setHardWait(2000);
		myEstesHomePage.clickOnSubmitButton();
		testUtil.setHardWait(500);
		// validate error message received
		String Actual_errorMessage = myEstesLoginPage.getLoginErrorMessage();
		assertEquals(Actual_errorMessage, Expected_errorMessage);
	}

	
	
	/*
	 * Welcome Page - GROUP Account - Verify Error message is displayed when Account
	 * Number edit box is left blank
	 */
	@Test(enabled = true, priority = 2)

	public void executeQZ_7730()
			throws InterruptedException {

		String Expected_errorMessage = "This field is required.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username7);
		myEstesLoginPage.enterPassword(password7);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnRecentShipments();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnSubmitButton();
		testUtil.setHardWait(500);
		// validate error message received
		String Actual_errorMessage = myEstesLoginPage.getLoginErrorMessage();
		assertEquals(Actual_errorMessage, Expected_errorMessage);
	}
}
