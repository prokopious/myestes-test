package recoverUsernameAndPasswordTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRecoverLostUserNamePasswordPage;
import testBase.TestBase;

public class RecoverUsernameAndPasswordRegressionTest extends TestBase {

	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRecoverLostUserNamePasswordPage myEstesRecoverLostUserNamePasswordPage = new MyEstesRecoverLostUserNamePasswordPage();
	
	/****************************** TESTS ******************************/
	
	/*
	 * Recover Username Password - Verify Error message is received when E-mail
	 * address does not exist or incorrect format
	 * 
	 */
	@Test(enabled = true, priority = 1)
	public void executeQZ_3309() {

		String expected = " We couldn't find your account based on the email address you provided. Please try another email address or contact Web Support for help.";
		String expected1 = "Email address is not valid.";
		String expected2 = "We couldn't find your account based on the email address you provided. Please try another email address or contact Web Support for help.";
		String email = "mikejones@gmail.com";
		String email2 = "mikejones@gmail.";
		String email3 = "mikejonesgmail.com";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickonForgotPasswordLink();
		myEstesRecoverLostUserNamePasswordPage.verifyPageTilte();
		myEstesRecoverLostUserNamePasswordPage.clcikOnEmailRadioButton();
		myEstesRecoverLostUserNamePasswordPage.enterEmailAdress();

		myEstesRecoverLostUserNamePasswordPage.clickOnSubmitButton();
		testUtil.setHardWait(2000);
		myEstesRecoverLostUserNamePasswordPage.validateIncorrectMailErrorMessage(expected.trim());
		testUtil.setHardWait(2000);

		myEstesRecoverLostUserNamePasswordPage.enterEmailAddress(email2);

		myEstesRecoverLostUserNamePasswordPage.validateIncorrectEmail(expected1.trim());
		testUtil.setHardWait(1000);
		myEstesRecoverLostUserNamePasswordPage.enterEmailAddress(email3);
		testUtil.setHardWait(1000);
		myEstesRecoverLostUserNamePasswordPage.clickOnSubmitButtonMultipleTimes();
		myEstesRecoverLostUserNamePasswordPage.validateUnsuccessfulAttemptsErrorMessage(expected2);

	}
	
	/*
	 *   Verify Error message is displayed when username or e-mail address text field is left blank 
	 */

	@Test(enabled = true, priority = 2)

	public void executeQZ_3310() {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickonForgotPasswordLink();// Change of webelement
		myEstesRecoverLostUserNamePasswordPage.verifyPageTilte(); // Change in page title
		myEstesRecoverLostUserNamePasswordPage.clickOnSubmitButton();
		myEstesRecoverLostUserNamePasswordPage.validateErrorMessage();
		myEstesRecoverLostUserNamePasswordPage.clcikOnEmailRadioButton();
		myEstesRecoverLostUserNamePasswordPage.clickOnSubmitButton();
		myEstesRecoverLostUserNamePasswordPage.validateErrorMessage();

	}

	/**
	 * This test passed on 6/27/22 after failing on Jenkins
	 * 
	 * Verify Error message is received incorrect username and limit on reset attempt
	 */
	
	@Test(enabled = true, priority = 3)

	public void executeQZ_3326() {

		String expected = "We couldn't find your account based on the username you provided. Please try another username or contact Web Support for help. ";
		String errorMessage1 = "You have made too many unsuccessful attempts to request a password reset. Please try again after one hour or contact Web Support for help.";
		String expected1 = "Email address is not valid.";
		String emailAdd = "Rainny@test";
		String emailAdd1 = "Rainny@test.com";
		String text = "Tesla";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		myEstesLoginPage.clickonForgotPasswordLink();
		myEstesRecoverLostUserNamePasswordPage.verifyPageTilte();
		myEstesRecoverLostUserNamePasswordPage.clickonUserNameRadioButton();
		myEstesRecoverLostUserNamePasswordPage.enterValueInTextField(text);
		myEstesRecoverLostUserNamePasswordPage.clickOnSubmitButton();

		myEstesRecoverLostUserNamePasswordPage.validateIncorrectMailErrorMessage(errorMessage1.trim(), expected.trim());
		myEstesRecoverLostUserNamePasswordPage.clcikOnEmailRadioButton();
		myEstesRecoverLostUserNamePasswordPage.enterValueInTextField(emailAdd);
		testUtil.setHardWait(1000);
		myEstesRecoverLostUserNamePasswordPage.validateIncorrectMailErrorMessage(errorMessage1.trim(), expected.trim());
		myEstesRecoverLostUserNamePasswordPage.validateEmailAddressFieldForIncorrectEmail(expected1.trim());
		myEstesRecoverLostUserNamePasswordPage.enterValueInTextField(emailAdd1);
		testUtil.setHardWait(2000);
		myEstesRecoverLostUserNamePasswordPage.clickOnSubmitButtonMultipleTimes();
		testUtil.setHardWait(2000);
		myEstesRecoverLostUserNamePasswordPage.validateUnsuccessfulAttemptsErrorMessage(errorMessage1.trim());
	}

	

	
	
	
	
}
