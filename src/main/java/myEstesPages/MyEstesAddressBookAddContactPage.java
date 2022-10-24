package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesAddressBookAddContactPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesAddressBookAddContactPage.class);

	// Company Name
	@FindBy(how = How.ID, using = "company")
	private WebElement weCompanyName;
	// Phone Area Code
	@FindBy(how = How.ID, using = "pn_ac")
	private WebElement wePhoneAreaCode;
	// Phone Exchange
	@FindBy(how = How.ID, using = "pn_nx")
	private WebElement wePhoneEechangeNo;
	// Phone Number(Last 4 Digit)
	@FindBy(how = How.ID, using = "pn_l4")
	private WebElement wePhoneLast4Digit;
	// Street Address
	@FindBy(how = How.ID, using = "str_1")
	private WebElement weStreet;
	// City
	@FindBy(how = How.ID, using = "city")
	private WebElement weCity;
	// State
	@FindBy(how = How.ID, using = "state")
	private WebElement weState;
	// Zip
	@FindBy(how = How.ID, using = "zip")
	private WebElement weZip;
	// Save Button
	@FindBy(how = How.ID, using = "add")
	private WebElement weSaveButton;
	// Cancel Button
	@FindBy(how = How.NAME, using = "cancel")
	private WebElement weCancelButton;

	/////
	// Enter Address Book All Require fields
	public void enterAddressBookRequiredField(String companyName, String areaCode, String PExchangeNo,
			String PhoneLast4Digit, String street, String city, String state, String zip) {
		logger.info("Enter Address Book All Require fields");
		testUtil.init(this);
		weCompanyName.sendKeys(companyName);
		wePhoneAreaCode.sendKeys(areaCode);
		wePhoneEechangeNo.sendKeys(PExchangeNo);
		wePhoneLast4Digit.sendKeys(PhoneLast4Digit);
		weStreet.sendKeys(street);
		weCity.sendKeys(city);
		weState.sendKeys(state);
		weZip.sendKeys(zip);
	}

	// Click On Save Button
	public void clickOnSaveButton() {
		logger.info("Click On Address Book Save Button");
		testUtil.init(this);
		weSaveButton.click();
	}

}
