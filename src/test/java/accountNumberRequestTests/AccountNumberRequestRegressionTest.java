package accountNumberRequestTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesPCRaterDownloadPage;
import myEstesPages.MyEstesRequestAccountNumberPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesToolsPage;
import pcRaterDownloadPages.PCRaterDownloadPage;
import testBase.TestBase;

public class AccountNumberRequestRegressionTest extends TestBase {

	
	
	

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRequestAccountNumberPage myEstesRequestAccountNumberPage = new MyEstesRequestAccountNumberPage();
	MyEstesToolsPage myestesToolPage = new MyEstesToolsPage();
	MyEstesPCRaterDownloadPage myestesPCRatedownloadPage = new MyEstesPCRaterDownloadPage();

	/*****j************************* TESTS ******************************/
	/*
	 * Request Account Number - Verify error messages are displayed when mandatory
	 * fields are submitted blank
	 */
	@Test(enabled = true, priority = 1)

	public void executeQZ_3327() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnRequestAccountNumber();
		myEstesRequestAccountNumberPage.clickOnSubmit();
		myEstesRequestAccountNumberPage.verifyRequiredFieldsErrorMessage();
	}


}
