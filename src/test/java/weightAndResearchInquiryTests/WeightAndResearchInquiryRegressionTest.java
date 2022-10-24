package weightAndResearchInquiryTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import testBase.TestBase;
import weightAndResearchInquiryPages.AccountSearchPage;
import weightAndResearchInquiryPages.WeightAndResearchInquiryPage;
import weightAndResearchInquiryPages.WeightAndResearchResultPage;

public class WeightAndResearchInquiryRegressionTest extends TestBase {

	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	WeightAndResearchInquiryPage weightAndResearchInquiryPage = new WeightAndResearchInquiryPage();
	WeightAndResearchResultPage weightAndResearchResultPage = new WeightAndResearchResultPage();
	AccountSearchPage accountSearchPage = new AccountSearchPage();

	/******************************* TESTS *******************************/
	// TC-DP2-963
	/*
	 * Weight and Research Inquiry - Verify search by invalid PRO generates error
	 * message.
	 */
	@Test(enabled = true, priority = 1)

	public void executeQZ_3293() throws InterruptedException {

		String inputData = "001-938986";
		String expected = "  ERROR: There are validation errors in the form. Please review the form and submit again.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("test1group");
		myEstesLoginPage.enterPassword("test1group");
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnManageLink();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnWeightResearchInquiry();
		weightAndResearchInquiryPage.selectPRO();
		weightAndResearchInquiryPage.enterSearchItems(inputData);
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateAccountNumberFieldError(expected);

	}

	// TC-DP2-953
	/*
	 * Weight and Research Inquiry - Verify search by invalid PRO generates error
	 * message
	 */
	@Test(enabled = true, priority = 2)
	

	public void executeQZ_3280() throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterUserName("smokenat");
		myEstesLoginPage.enterPassword("smokenat");
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnWeightResearchInquiry();
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateDateRangeError();
		weightAndResearchInquiryPage.selectPRO();
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validatePROError();
		weightAndResearchInquiryPage.selectOther();
		testUtil.setHardWait(3000);
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateOtherError();
	}

	/**
	 * Weight and Research Inquiry - Verify error message displays if there are no
	 * search results.
	 */
	
	@Test(enabled = true, priority = 3)

	public void executeQZ_3278() throws InterruptedException {

		//String inputData = "123343";
		//updated the error message
		String expected ="  ERROR: There are validation errors in the form. Please review the form and submit again.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(password3);
		myEstesLoginPage.clickOnLoginButton();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnManageLink();
		testUtil.setHardWait(1000);
		myEstesHomePage.clickOnWeightResearchInquiry();
		testUtil.setHardWait(2000);
		weightAndResearchInquiryPage.clickOnAccountSearch();
		testUtil.setHardWait(1000);
		weightAndResearchInquiryPage.clickOnAnyAccountNumber();
		weightAndResearchInquiryPage.validateAccountNumberIsDisplayedInTextField();
		weightAndResearchInquiryPage.selectPRO();
		//weightAndResearchInquiryPage.enterSearchItems();
		weightAndResearchInquiryPage.enterSearchItemsInSeparteLines();
		testUtil.setHardWait(1000);
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateErrorMassage(expected);

	}
	
	
	/**
	 * Weight & Research Inquiry - Verify user can search the Account Search modal
	 * sing different search criteria.
	 */

	@Test(enabled = true, priority = 4)
	
	public void executeQZ_7143() throws InterruptedException {
		//step 1 & 2: Enter username, password and click on login
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();
		
		//Step 3: From Manage, Click Weight & Research Inquiry
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnWeightResearchInquiry();
		
		testUtil.setHardWait(3000);
		weightAndResearchInquiryPage.clickOnAccountSearch();
		testUtil.setHardWait(1000);
		//Step 4: From the Account Number dropdown, enter the following enter number in the dropdown
		accountSearchPage.enterTextSearchField("0500845");
		testUtil.setHardWait(1000);
		//Step 5: Click on the account
		accountSearchPage.clickonAccountNumOnAccountDropDown();
		//Validate
		accountSearchPage.validateAccountInformationDetails();
		
		//Step 6: From Account Number dropdown, enter the following account name in the search text field
		weightAndResearchInquiryPage.clickOnAccountSearch();
		weightAndResearchInquiryPage.enterTextSearchFieldForAccountName("Carquest");
		
		//validate account details
		
		weightAndResearchInquiryPage.validateAccountInformationForAccountName();
		
		//Step 7: In the Account Number dropdown, click the next arrow 
		
		weightAndResearchInquiryPage.clickOnNextArrow();
		
		//validate
		weightAndResearchInquiryPage.validateAccountInformationRecord();
		//Step 8: From Account Number dropdown, enter the following account address in the search text field

		weightAndResearchInquiryPage.enterTextSearchFieldForAccountAddress("3661 VALLEY PIKE");
		
		//Validate
		weightAndResearchInquiryPage.validateAccountInformationRecordForAccountAddress();
		
		
		//Step 9:From Account Number dropdown, enter the following account city in the search text field

		weightAndResearchInquiryPage.enterTextSearchFieldForAccountCity("GASTONIA");
		
		//Validate
		
		weightAndResearchInquiryPage.validateAccountInformationRecordForAccountCity();
		
		//Step 10: From Account Number dropdown, enter the following account state in the search text field
		
		weightAndResearchInquiryPage.enterTextSearchFieldForAccountState("VA");
		
		//validate
		weightAndResearchInquiryPage.validateAccountInformationRecordForAccountState();
		
		//Step 11: From Account Number dropdown, enter the following account zip in the search text field

		weightAndResearchInquiryPage.enterTextSearchFieldForAccountZip("22602");
		
		//Validate
		weightAndResearchInquiryPage.validateAccountInformationRecordForAccountZip();
		//Step: Enter the following data: 
		weightAndResearchInquiryPage.enterTextSearchFieldForAccountIncorrectValue("JML");
		//Validate
		weightAndResearchInquiryPage.validateAccountInformationRecordForAccountIncorrectValue();

	}

	
	/*
	 * Weight and Research Inquiry - Verify error message displays for invalid data
	 * entry or no data entry for each search by criteria.
	 */
	

	@Test(enabled = true, priority = 5)
	public void executeQZ_7144()
			throws InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("test1group");
		myEstesLoginPage.enterPassword("test1group");
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnWeightResearchInquiry();
		testUtil.setHardWait(1000);
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateDateRangeError();
		weightAndResearchInquiryPage.selectPRO();
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validatePROError();
		weightAndResearchInquiryPage.enterSearchItems("1");
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validatePROError();
		weightAndResearchInquiryPage.enterSearchItems("...1ff");
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validatePROError();
		weightAndResearchInquiryPage.selectOther();
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateOtherError();
		weightAndResearchInquiryPage.enterSearchItems("1");
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateOtherError();
		weightAndResearchInquiryPage.enterSearchItems(".....1");
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validateOtherError();
		weightAndResearchInquiryPage.selectPRO();
		weightAndResearchInquiryPage.enterSearchItems("0001111");
		weightAndResearchInquiryPage.clickOnSearch();
		weightAndResearchInquiryPage.validatePROError();
	}

	/**
	 * Weight and Research Inquiry - Verify error message displays if no
	 * emailaddress or invalid email address is entered.
	 */
	
	@Test(enabled = true, priority = 6)
	public void executeQZ_7146()
			throws InterruptedException {

		String expected = "  ERROR: There are validation errors in the form. Please review the form and submit again.";

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("testadmin");
		myEstesLoginPage.enterPassword("test2001");
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();
		myEstesHomePage.clickOnManageLink();
		myEstesHomePage.clickOnWeightResearchInquiry();
		weightAndResearchInquiryPage.selectPRO();
		weightAndResearchInquiryPage.enterSearchItems("1030506600");
		weightAndResearchInquiryPage.clickOnSearchButton();
		weightAndResearchInquiryPage.selectPROCheckBox();
		weightAndResearchInquiryPage.clickOnEmailSelectedButton();
		weightAndResearchInquiryPage.clickOnSendButton();
		weightAndResearchInquiryPage.validateError(expected);
		weightAndResearchInquiryPage.enterEmailAddress("qatest-test");
		weightAndResearchInquiryPage.clickOnSendButton();
		weightAndResearchInquiryPage.validateEnterEmailFieldError();

	}

}
