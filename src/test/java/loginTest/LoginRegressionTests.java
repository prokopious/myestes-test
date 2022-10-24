package loginTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import testBase.TestBase;

public class LoginRegressionTests extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	
	
	/****************************** TESTS ******************************/
	/**
	 * This fails because the validation step is not showing the company name
	 * 
	 * MyEstes Login - Verify the user is able to Log Out
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_3286() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesLoginPage.clickOnMyEstes();
		myEstesLoginPage.clickOnLogoutButton();
		myEstesLoginPage.clickOnLogoutConfirmButton();
		myEstesLoginPage.getMyEstesloginTextDisplayed();
		myEstesLoginPage.enterUserName(username4);
		myEstesLoginPage.enterPassword(password4);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.verifyCompanyNameDisplayed();
		myEstesHomePage.verifyAccNumDisplayed();
		myEstesLoginPage.clickOnLogoutButton();
		myEstesLoginPage.clickOnLogoutConfirmButton();

	}
	
	
	// QZ-3283
		// MyEstes Login - Verify Error message displayed when required fields are left
		// blank

		@Test(enabled = true, priority = 2)

		public void executeQZ_3283() throws InterruptedException {
		    testUtil.setHardWait(5000);
	        testUtil.printIdentifiers("frmLogin");
			String Expected_errorMessage = "This field is required.";
		    testUtil.setHardWait(5000);
	        testUtil.printIdentifiers("frmLogin");
			myEstesLoginPage.clickOnMyEstes();
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginFromDDown();
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickonSubmitButton();

			String Actual_errorMessage = myEstesLoginPage.getLoginErrorMessage();
			assertEquals(Actual_errorMessage, Expected_errorMessage);
		}
	
	
		// QZ-3282
		/*
		 * MyEstes Login - Verify Error message displayed when invalid data entered in
		 * username and password fields.
		 */

		@Test(enabled = true, priority = 3)

		public void executeQZ_3282()
				throws InterruptedException {

			myEstesLoginPage.clickOnMyEstes();
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickOnLoginFromDDown();
			testUtil.setHardWait(1000);
			myEstesLoginPage.enterUnameAndpwd("abcd", "1234");
			testUtil.setHardWait(1000);
			myEstesLoginPage.clickonSubmitButton();

			myEstesLoginPage.invidCredentialMessageDisplayed();
		}


	
	
	
	
}
