package terminalLookupPages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesTerminalLookupPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesTerminalLookupPage.class);
	// Terminal Look Up Link
	@FindBy(how = How.XPATH, using = "html/body/ul/li[3]/a")
	private WebElement weTermLookUP;
	// Select Country
	@FindBy(how = How.XPATH, using = "//mat-select[@id='country']//div[@class='mat-select-value']")
	private WebElement weSelectCountry;
	// Select Mexico Country
	@FindBy(how = How.XPATH, using = "//*[@id='mat-option-2']/span")
	private WebElement weSelectMexico;
	// Select Canada Country
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Canada')]")
	private WebElement weSelectCanada;
	//
	// Lookup by
	@FindBy(how = How.ID, using = "lookupBy")
	private WebElement weLookupBy;
	//// Lookup By Zip
	@FindBy(how = How.XPATH, using = "//*[@id='selectLookupBy']/div/div[2]")
	private WebElement weLookupByZip;
	// Enter Zip Code Number
	@FindBy(how = How.ID, using = "zip")
	private WebElement weEnterZipCode;
	// Click on First Zipcode Drop down
	@FindBy(how = How.XPATH, using = "(//*[@class='ng-tns-c3-1'])[5]")
	private WebElement weFirstZipCodeDDown;
	// Lookup By Postal Code
	@FindBy(how = How.XPATH, using = "//span[@class='mat-option-text'][contains(text(),'Postal Code')]")
	private WebElement weLookupByPostalCode;
	// Lookup By Province
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Province')]")
	private WebElement weLookupByProvince;
	// Click On Select Province
	@FindBy(how = How.XPATH, using = "//mat-select[@id='stateSelect']")
	private WebElement weClickOnSelectProvince;
	// Lookup By City
	@FindBy(how = How.ID, using = "inputCity")
	private WebElement weLookupByCity;
	// Lookup By State
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'State')])[3]")
	private WebElement weLookupByState;
	// Click On Select State
	@FindBy(how = How.XPATH, using = "//*[@id=\"stateSelect\"]/div/div[1]")
	private WebElement weSelectState;
	// Enter State Name
	@FindBy(how = How.ID, using = "inputState")
	private WebElement weEnterStateName;
	/*
	 * //Click On LOOKUP
	 * 
	 * @FindBy(how = How.XPATH, using = "//*[@type='submit']") private
	 * WebElement weClickOnLookUP;
	 */

	// LOOKUP
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement weLOOKUP;
	// Terminal W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[1]")
	private WebElement weTerminal;
	// Addres W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[2]")
	private WebElement weAddress;
	// Phone W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[3]")
	private WebElement wePhone;
	// Fax W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[4]")
	private WebElement weFax;
	// Click on Caret
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'expand_more')])[1]")
	private WebElement weClickOnCaretSymbol;
	// Terminal Address
	@FindBy(how = How.XPATH, using = "(//*[@class='ng-star-inserted'])[1]")
	private WebElement weTerminalAddress;
	// Email
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Email')]")
	private WebElement weTerminalEmail;
	// Manager
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Manager')]")
	private WebElement weTerminalManager;
	// Next-Day Coverage
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Next-Day Coverage')]")
	private WebElement weTerminalNextDCov;
	// National Coverage
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'National Coverage Map')]")
	private WebElement weTerminalNCovMap;

	// LTL Rate Quote CALCULATE RATE
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[1]")
	private WebElement weLTLRQCalculateRate;
	// Calculate Transit Time Calculate time
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[3]")
	private WebElement weCalTTCalTime;

	// LTL Rate Quote Calculate From
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[1]")
	private WebElement weLTLRateQuoteCalFrom;
	// LTL Rate Quote Calculate to
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[2]")
	private WebElement weLTLRQCalculateTo;

	// Calculate Transite Time Calculate From
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[4]")
	private WebElement weTTCalculateFrom;
	// Calculate Transite Time Calculate to
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[5]")
	private WebElement weTTCalculateTo;

	// LTL Pickup Request REQUEST PICKUP
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Request Pickup')])[1]")
	private WebElement weLTLPRRequestPickup;

	////
	// Click on Terminal Lookup Link
	public void clickOnTerminalLookupLink() {
		testUtil.init(this);
		logger.info("click on Terminal Lookup Link");
		weTermLookUP.click();
	}
	// Select Country
	public void selectCountry() {
		logger.info("Select Country");
		testUtil.init(this);
		weSelectCountry.click();
	}
	// Select Mexico Country
	public void selectMexico() {
		logger.info("Select Mexico as a Country");
		testUtil.init(this);
		weSelectMexico.click();
	}
	// Select Canada Country
	public void selectCanada() {
		logger.info("Select Canada as a Country");
		testUtil.init(this);
		weSelectCanada.click();
	}
	// Lookup by
	public void lookUpBy() {
		logger.info("Select Lookup By");
		testUtil.init(this);
		weLookupBy.click();
	}
	// Click On Lookup By Postal Code
	public void clickOnLookUpByPostalCode() {
		logger.info("Click On Lookup By Postal Code");
		testUtil.init(this);
		weLookupByPostalCode.click();
	}
	// Click On Lookup By Province
	public void clickOnLookUpByProvince() throws InterruptedException {
		logger.info("Click On Lookup By Province");
		testUtil.init(this);
		weLookupByProvince.click();
		Thread.sleep(1000);
	}
	// Click On Select Province
	public void clickOnSelectProvince() throws InterruptedException {
		logger.info("Click On Select Province");
		testUtil.init(this);
		weClickOnSelectProvince.click();
		Thread.sleep(1000);
	}
	// Lookup by Zip
	public void lookUpByZip() {
		logger.info("Select Lookup BY Zip");
		testUtil.init(this);
		weLookupByZip.click();
	}
	// Enter Zip Code
	public void enterZipCode(String Zip) {
		logger.info("Enter Zip	Code");
		testUtil.init(this);
		weEnterZipCode.sendKeys(Zip);
		driver.findElement(By.xpath("//*[contains(text(),'" + Zip + "')]"))
				.click();
		weEnterZipCode.click();
	}
	// Click On First Zip Code Drop Down
	public void clickOnFirstZipCodeDDown() {
		logger.info("Click On First Zip Code Drop Down");
		testUtil.init(this);
		weFirstZipCodeDDown.click();
	}
	// Lookup by City
	public void lookUpByCity(String ByCity) {
		logger.info("Select Lookup By City");
		testUtil.init(this);
		weLookupByCity.sendKeys(ByCity);
	}
	// Click On Lookup by State
	public void clickOnlookUpByState() {
		logger.info("Select Lookup By State");
		testUtil.init(this);
		weLookupByState.click();
	}
	// Click On Select State
	public void clickOnSelectState() {
		logger.info("Click On Select State");
		testUtil.init(this);
		weSelectState.click();
	}
	// Enter State Name
	public void enterStateName(String StateName) {
		logger.info("Enter State Name");
		testUtil.init(this);
		weEnterStateName.sendKeys(StateName);
	}
	// Select State
	// Use State Abbreviation Name Ex: "VA", "NJ"
	public void selectState(String StateAbbreviationName) throws InterruptedException {
		testUtil.init(this);
		logger.info(" Select State");
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[contains(text(),'" + StateAbbreviationName + "')]")).click();
	}

	// Select Province for CANADA
	// Use State Abbreviation Name Ex: "NT", "ON"
	public void selectProvince(String ProvinceAbbreviationName) {
		testUtil.init(this);
		logger.info(" Select Province");
		driver.findElement(By.xpath("//*[contains(text()," + "'"
				+ ProvinceAbbreviationName + "')]")).click();
	}
	// Click on LOOKUP
	public void clickOnLOOKUP() {
		testUtil.init(this);
		logger.info("click on Lookup Link");
		weLOOKUP.click();
	}
	// Terminal
	public void verifyTerminal() {
		testUtil.init(this);
		logger.info("Verify Terminal");
		weTerminal.isDisplayed();
	}
	// Address
	public void verifyTerAddress() {

		testUtil.init(this);
		logger.info("Verify Address");
		weAddress.isDisplayed();
	}
	// Phone
	public void verifyTerPhone() {

		testUtil.init(this);
		logger.info("Verify Phone");
		wePhone.isDisplayed();
	}
	// Fax
	public void verifyTerFax() {
		testUtil.init(this);
		logger.info("Verify Fax");
		weFax.isDisplayed();
	}
	// Email
	public void verifyTerEmail() {
		testUtil.init(this);
		logger.info("Verify Fax");
		weTerminalEmail.isDisplayed();
	}
	// Manager
	public void verifyTerManager() {
		testUtil.init(this);
		logger.info("Verify Manager");
		weTerminalManager.isDisplayed();
	}
	// Next-Day Coverage
	public void verifyTerNextDCov() {
		testUtil.init(this);
		logger.info("Verify Next-Day Coverage");
		weTerminalNextDCov.isDisplayed();
	}
	// National Coverage Map
	public void verifyTerNCovMap() {
		testUtil.init(this);
		logger.info("Verify National Coverage Map");
		weTerminalNCovMap.isDisplayed();
	}
	// Click on Caret Symbol
	public void clickOnCaretSymbol() throws InterruptedException {
		testUtil.init(this);
		logger.info("click on Caret Symbol");
		weClickOnCaretSymbol.click();
		Thread.sleep(1000);
	}
	// Verify Terminal Address
	public void verifyTerminalAddress() {
		testUtil.init(this);
		logger.info("Verify Terminal Address");
		String Expected_TerAddress = "919 Boundary Street";
		String Actual_TerAddress = weTerminalAddress.getText();
		assertEquals(Actual_TerAddress, Expected_TerAddress);
	}

	// Verify Get an LTL Rate Quote Calculate Rate Displayed
	public void verifyLTLRateQuoteCalRate() {
		testUtil.init(this);
		logger.info("Verify Get an LTL Rate Quote Calculate Rate");
		weLTLRQCalculateRate.isDisplayed();
	}
	// Verify Calculate Transit Time Calculate time Displayed
	public void verifyweCalTTCalTime() {
		testUtil.init(this);
		logger.info("Verify  Calculate Transite Calculate time Displayed");
		weCalTTCalTime.isDisplayed();
	}
	// Verify LTL Rate Quote Calculate From
	public void verifyLTLRateQCalFrom() {
		testUtil.init(this);
		logger.info("Verify LTL Rate Quote Calculate Rate");
		weLTLRateQuoteCalFrom.isDisplayed();
	}

	// Verify LTL Rate Quote Calculate To
	public void verifyLTLRateQCalTo() {
		testUtil.init(this);
		logger.info("Verify LTL Rate Quote Calculate To");
		weLTLRQCalculateTo.getAttribute("value");
	}
	// Verify Calculate Transit Time Calculate From
	public void verifyTTCalculateFrom() {
		testUtil.init(this);
		logger.info("Verify  Calculate Transite Time CALCUTE From");
		weTTCalculateFrom.isDisplayed();
	}
	// Verify Calculate Transit Time Calculate To
	public void verifyTTCalculateTo() {
		testUtil.init(this);
		logger.info("Verify  Calculate Transite Time CALCUTE TO");
		weTTCalculateTo.getAttribute("value");
	}
	// Verify LTL Pickup Request REQUEST PICKUP
	public void verifyLTLPRRequestPickup() {
		testUtil.init(this);
		logger.info("Verify LTL Pickup Request 	REQUEST PICKUP");
		weLTLPRRequestPickup.isDisplayed();
	}

}
