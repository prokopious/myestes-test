package addressBookTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesAddNewAddressPage;
import myEstesPages.MyEstesAddressBookPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesWelcomePage;
import testBase.TestBase;

public class AddressBookSmokeTest extends TestBase {


	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesAddressBookPage myEstesAddressBookPage = new MyEstesAddressBookPage();
	MyEstesAddNewAddressPage myEstesAddNewAddressPage = new MyEstesAddNewAddressPage();
	
	
	/**
	 * Fails- when clicking on the address book under myEstes dropdown< page not found> error is thrown
	 * Address Book - Verify user can add address entry
	 */

	@Test(enabled = true, priority = 1)
	public void executeQZ_3273() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes(); // this changed from MyEstes to myAddress
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(3000);
		myEstesLoginPage.clickOnLoginButton();

		myEstesWelcomePage.clickOnMyEstes();
		myEstesWelcomePage.clickOnAddressBook();
		testUtil.setHardWait(1000);
		myEstesAddressBookPage.clickOnAddNewAddressButton();
		testUtil.setHardWait(2000);
		testUtil.reloadPage();
		testUtil.setHardWait(1000);
		myEstesAddressBookPage.clickOnAddNewAddressButton();
		myEstesAddNewAddressPage.enterCompanyName();
		String coName = myEstesAddNewAddressPage.getCompanyName();
		myEstesAddNewAddressPage.selectCountry("United States");
		myEstesAddNewAddressPage.enterAddress();
		myEstesAddNewAddressPage.enterUSCityName();
		testUtil.setHardWait(2000);
		myEstesAddNewAddressPage.selectState("VA");
		myEstesAddNewAddressPage.enterZipCode();
		myEstesAddNewAddressPage.enterPhoneNumber("2021234567");
		myEstesAddNewAddressPage.clickOnSaveButton();
		testUtil.setHardWait(1000);
		myEstesAddressBookPage.enterValueToAdvancedSearchField(coName);
		myEstesAddressBookPage.validateCompanyNameDisplay(coName);
	}

}
