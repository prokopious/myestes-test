package signupTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesSignUpPage;
import testBase.TestBase;

public class SignupSmokeTest extends TestBase{

	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	
	
	/******************************* TESTS *******************************/
	

	/*
	 * MyEstes Signup - Verify the user is able to Sign Up for MyEstes application.
	 * DP2-308
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_3252() {
		
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
		myEstesSignUpPage.enterUserName();
		myEstesSignUpPage.enterPassword("12345678");
		myEstesSignUpPage.enterConfirmPassword("12345678");
		myEstesSignUpPage.clickOnSubmitButton();

		myEstesSignUpPage.verifyConfimationMessage();
	}


}
