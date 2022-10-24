package signupTests;

import org.testng.annotations.Test;

import imageRetrievalPages.MyEstesImageRetrievalPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;
import myEstesPages.MyEstesTerminalLookupPage;
import testBase.TestBase;


public class SignupRegressionTest extends TestBase{

	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalLookupPage myEstesterminalLookupPage = new MyEstesTerminalLookupPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesImageRetrievalPage myEstesImageRetrievalPage = new MyEstesImageRetrievalPage();

	
	
	
	@Test(enabled = true, priority = 1)

	public void executeQZ_3311() {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		myEstesLoginPage.clickonSignUpLink();
		myEstesSignUpPage.clickYesAndHaveMyAccountNumberRadioButton();
		myEstesSignUpPage.clickOnNextButton();

		myEstesSignUpPage.enterCompanyAccountNumber();
		myEstesSignUpPage.enterCompanyName();
		myEstesSignUpPage.enterFirstName();
		myEstesSignUpPage.enterLastName();
		myEstesSignUpPage.enterEmailAddress();
		myEstesSignUpPage.enterPhoneNumber();

		myEstesSignUpPage.enterUserName("smokelocal");
		myEstesSignUpPage.enterPassword("smokelocal");
		myEstesSignUpPage.enterConfirmPassword("abcd1234");
		myEstesSignUpPage.clickOnSubmitButton();
		myEstesSignUpPage.verifyMissmatchPasswordMessage();
	}
	
	
	
	/*
	 * MyEstes Signup - Verify Error message is displayed when all required fields
	 * are left blank.(TC-DP2-419)
	 */

	@Test(enabled = true, priority = 2)

	public void executeQZ_3291() {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnCreateAccountLink();
		myEstesSignUpPage.clickYesAndHaveMyAccountNumberRadioButton();
		myEstesSignUpPage.clickOnNextButton();
		myEstesSignUpPage.clickOnSubmitButton();

		myEstesSignUpPage.validateErrorMessage();

	}
	
	
	

	/*
	 * MyEstes Signup - Verify Error message is displayed when already existing
	 * username is entered for signup page.(TC-DP2-416)
	 */

	@Test(enabled = true, priority = 3)

	public void executeQZ_3290()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnCreateAccountLink();
		myEstesSignUpPage.clickYesAndHaveMyAccountNumberRadioButton();
		myEstesSignUpPage.clickOnNextButton();

		myEstesSignUpPage.enterCompanyAccountNumber();
		myEstesSignUpPage.enterCompanyName();
		myEstesSignUpPage.enterFirstName();
		myEstesSignUpPage.enterLastName();
		myEstesSignUpPage.enterEmailAddress();
		myEstesSignUpPage.enterPhoneNumber();
		myEstesSignUpPage.enterUserName("smokelocal");
		myEstesSignUpPage.enterPassword("somokelocal");
		myEstesSignUpPage.enterConfirmPassword("somokelocal");
		myEstesSignUpPage.clickOnSubmitButton();

		myEstesSignUpPage.verifyUserNameAlreadyExistMessage();

	}
	
}
