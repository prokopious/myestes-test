package accountNumberRequestTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesRequestAccountNumberPage;
import myEstesPages.MyEstesResourcesPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesTerminalListPage;

import testBase.TestBase;

public class AccountNumberRequestSmokeTest extends TestBase{
	
	

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesResourcesPage myEstesResourcesPage = new MyEstesResourcesPage();
	MyEstesTerminalListPage myEstesTerminalListPage = new MyEstesTerminalListPage();
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesRequestAccountNumberPage myEstesRequestAccountNumberPage = new MyEstesRequestAccountNumberPage();

	/******************************* TESTS *******************************/
	
	/*
	 * Request Account Number - Authenticated - Verify the user can request an
	 * Account Number by submitting Request Account Number form.
	 */

	@Test(enabled = true, priority = 1)

	public void executeQZ_3244() throws InterruptedException {

		Faker faker = new Faker();
		String message = "Your message has been sent. A customer care representative will respond as soon as possible.";
		System.out.println("The URL is : " + driver.getCurrentUrl());
		
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		//testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnReqAccNumFromDropDown();
		myEstesRequestAccountNumberPage.enterRANYourName(faker.name().fullName());
		String companyName = myEstesRequestAccountNumberPage.enterRANCompanyName(faker.company().name());
		myEstesRequestAccountNumberPage.enterRANEmail(faker.internet().emailAddress());
		myEstesRequestAccountNumberPage.enterRANPhone(faker.phoneNumber().phoneNumber());

		myEstesRequestAccountNumberPage.enterReqCompName(companyName);
		myEstesRequestAccountNumberPage.enterLocAddress(faker.address().fullAddress());
		myEstesRequestAccountNumberPage.clickOnSubmit();
		testUtil.setHardWait(5000);
		String actual = myEstesRequestAccountNumberPage.getTextMessage();
		testUtil.setHardWait(1000);
		System.out.println("Displayed message is :" + actual);
		Assert.assertEquals(actual, message);
		

	}

	

	/*
	 * Request Account Number - Unauthenticated - Verify the user can request an
	 * Account Number by submitting Request Account Number form.
	 */

	@Test(enabled = true, priority = 2)

	public void executeQZ_3248() {

		Faker faker = new Faker();
		String message = "Your message has been sent. A customer care representative will respond as soon as possible.";
		
		System.out.println("The URL is : " + driver.getCurrentUrl());
		
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnCreateAccountLink();
		myEstesSignUpPage.verifyMyEstesSignupTextDisplayed();
		myEstesSignUpPage.clickOnYesButIDonnotKnowComAccNumRadioButton();
		myEstesSignUpPage.clickOnNextButton();
		myEstesSignUpPage.clickOnSubmittingYourReqOnline();

		myEstesRequestAccountNumberPage.enterRANYourName(faker.name().fullName());
		String companyName = myEstesRequestAccountNumberPage.enterRANCompanyName(faker.company().name());
		testUtil.setHardWait(2000);
		myEstesRequestAccountNumberPage.enterRANEmail(faker.internet().emailAddress());
		myEstesRequestAccountNumberPage.enterRANPhone(faker.phoneNumber().phoneNumber());

		myEstesRequestAccountNumberPage.enterReqCompName(companyName);
		myEstesRequestAccountNumberPage.enterLocAddress(faker.address().fullAddress());
		myEstesRequestAccountNumberPage.clickOnSubmit();
		testUtil.setHardWait(4000);
		String actual = myEstesRequestAccountNumberPage.getTextMessage();
		System.out.println("Displayed message is :" + actual);
		testUtil.setHardWait(1000);
		Assert.assertEquals(actual, message);
	}

	

}
