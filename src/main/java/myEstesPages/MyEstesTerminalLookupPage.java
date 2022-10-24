package myEstesPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesTerminalLookupPage extends TestBase{

	private Logger logger = Logger.getLogger(MyEstesTerminalLookupPage.class);
	//Terminal Look Up Link
	@FindBy(how = How.XPATH, using = "html/body/ul/li[3]/a")
	private WebElement weTermLookUP;
	//Select Country
	@FindBy(how = How.XPATH, using = "//mat-select[@id='country']//div[@class='mat-select-value']")
	private WebElement SelectCountry;
	//  Select Mexico Country
	@FindBy(how = How.XPATH, using = "//span[text()=' Mexico ']")
	private WebElement SelectMexico;
	//  Select Canada Country
	@FindBy(how = How.XPATH, using = "//span[contains(text(),' Canada ')]")
	private WebElement SelectCanada;
//  Select US Country
	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'United States')]")
	private WebElement SelectUS;
	// Lookup by
	@FindBy(how = How.ID, using = "lookupBy")
	private WebElement weLookupBy;
	//// Lookup By Zip
	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'ZIP Code')]")
	private WebElement weLookupByZip;
	// Enter Zip Code Number
	@FindBy(how = How.ID, using = "zip")
	private WebElement weEnterZipCode;
	
	// Enter Zip Code Number
	@FindBy(how = How.ID, using = "city")
	private WebElement cityName;
	
	@FindBy(how = How.ID, using = "state")
	private WebElement provinceName;
	
	@FindBy(how = How.XPATH, using = "//lib-snackbar-message[@class='ng-star-inserted']")
	private WebElement errorMessage;
		
	// Click on First Zipcode Drop down
	@FindBy(how = How.XPATH, using = "(//*[@class='ng-tns-c3-1'])[5]")
	private WebElement weFirstZipCodeDDown;
	// Lookup By Postal Code
	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'Postal Code')]")
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
	
	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'State')]")
	private WebElement selectState;
	//Click On Select State
	@FindBy(how = How.XPATH, using = "//mat-select[@id='stateSelect']")
	private WebElement weSelectState;

	//Enter State Name
	@FindBy(how = How.ID, using = "inputState")
	private WebElement weEnterStateName;
	/*//Click On LOOKUP
		@FindBy(how = How.XPATH, using = "//*[@type='submit']")
		private WebElement weClickOnLookUP;*/

	// LOOKUP
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement weLOOKUP;
	//Terminal W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[1]")
	private WebElement weTerminal;
	//Addres W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[2]")
	private WebElement weAddress;
	//Phone W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[3]")
	private WebElement wePhone;
	//Fax W/O Caret Symbol
	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[4]")
	private WebElement weFax;
	//Click on Caret
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'expand_more')])[1]")
	private WebElement weClickOnCaretSymbol;
	
	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'expand-cell')]/i")
	private List<WebElement> expandTerminal;
	
	@FindBy(how = How.XPATH, using = "//tr[td[label[contains(text(),'Terminal')]]]/td[2]")
	private WebElement terminalName;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Address')]]/td[2]")
	private WebElement terminalAddress;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Phone')]]/td[2]")
	private WebElement terminalPhone;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Fax')]]/td[2]")
	private WebElement terminalFax;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Email')]]/td[2]")
	private WebElement terminalEmail;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Manager')]]/td[2]")
	private List<WebElement> terminalManager;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Next-Day Coverage')]]/td[2]")
	private List<WebElement> terminalNextDayCov;
	
	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'National Coverage Map')]]/td[2]")
	private List<WebElement> terminalNatCovMap;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Get a Rate Quote']")
	private WebElement terminalGetRateQuote;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Calculate Rate']")
	private WebElement buttonCalculateRate;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Calculate Transit Time']")
	private WebElement terminalCalculateTransitTime;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Calculate Time']")
	private WebElement buttonCalculateTime;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='LTL Pickup Request']")
	private WebElement terminalLtlPickupRequest;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Request Pickup']")
	private WebElement buttonRequestPickup;	
	
	// Terminal Address
	@FindBy(how = How.XPATH, using = "(//*[@class='ng-star-inserted'])[1]")
	private WebElement weTerminalAddress;
	//Email
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Email')]")
	private WebElement weTerminalEmail;
	//Manager
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Manager')]")
	private WebElement weTerminalManager;
	//Next-Day Coverage
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Next-Day Coverage')]")
	private WebElement weTerminalNextDCov;
	// National Coverage
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'National Coverage Map')]")
	private WebElement weTerminalNCovMap;

	// LTL Rate Quote CALCULATE RATE	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[1]")
	private WebElement weLTLRQCalculateRate;
	//Calculate Transit Time Calculate time 		
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[3]")
	private WebElement weCalTTCalTime;

	// LTL Rate Quote Calculate From	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[1]")
	private WebElement weLTLRateQuoteCalFrom;
	//LTL Rate Quote Calculate to	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[2]")
	private WebElement weLTLRQCalculateTo;

	//Calculate Transite Time Calculate From
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[4]")
	private WebElement weTTCalculateFrom;
	//Calculate Transite Time Calculate to		
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Calculate')])[5]")
	private WebElement weTTCalculateTo;

	//LTL Pickup Request REQUEST PICKUP		
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Request Pickup')])[1]")
	private WebElement weLTLPRRequestPickup;
	
	@FindBy(xpath = "//h1//span[contains(text(),'Terminal Lookup')]")
	private WebElement terminalLookupTitle;

	
	/*****************************************************************************/
	// Click on Terminal Lookup Link
	public void clickOnTerminalLookupLink() {
		testUtil.init(this);		
		logger.info("click on Terminal Lookup Link");	
		weTermLookUP.click();
	}
	//Select Country
	public void selectCountry() {
		logger.info("Select Country");
		testUtil.init(this);
		//SelectCountry.click();
		testUtil.clickElementJavascript(SelectCountry);
		testUtil.setHardWait(1000);
	}
	//Select Mexico Country
	public void selectMexico() {

		testUtil.init(this);
		logger.info("Select Mexico as a Country");
		SelectMexico.click();
//		testUtil.filterSelector(null, SelectMexico, null,0, "Mexico", "mat-option-text").click();
	}
	
	//Select Canada Country
	public void selectCanada() {
		logger.info("Select Canada as a Country");
		testUtil.init(this);		
		SelectCanada.click();
//        testUtil.filterSelector(null, SelectCanada, null, 0, ">").click();
    }
	
	
	//Select US Country
		public void selectUS() {
			logger.info("Select US as a Country");
			testUtil.init(this);		
			SelectUS.click();
		}
	// Lookup by	
	public void lookUpBy() {

		testUtil.init(this);
		logger.info("Select Lookup By");
		testUtil.setHardWait(1000);
		weLookupBy.click();
	}
	// Click On Lookup By Postal Code	
	public void clickOnLookUpByPostalCode() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click On Lookup By Postal Code");
		Thread.sleep(2000);
		weLookupByPostalCode.click();
	}
	// Click On Lookup By Province	
	public void clickOnLookUpByProvince() {
		testUtil.init(this);
		logger.info("Click On Lookup By Province");
		weLookupByProvince.click();
	}
	// Click On Select Province	
	public void clickOnSelectProvince() {
		testUtil.init(this);
		logger.info("Click On Select Province");
		testUtil.setHardWait(2000);
		weClickOnSelectProvince.click();
		testUtil.setHardWait(2000);
	}
	// Lookup by Zip	
	public void lookUpByZip() {
		testUtil.init(this);
		logger.info("Select Lookup BY Zip");
		weLookupByZip.click();
	}
	// Enter Zip Code
	public void enterZipCode(String Zip) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter Zip	Code");
		weEnterZipCode.sendKeys(Zip);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(text(),'"+Zip+"')]")).click();
		//weEnterZipCode.click();
	}
	
	public void enterPostalCode(String postalCode) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter postal Code");
		weEnterZipCode.sendKeys(postalCode);
		Thread.sleep(1000);
	}
	
	public void enterCity(String city) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter city name");
		cityName.sendKeys(city);
		Thread.sleep(1000);
	}
	
	public void enterProvince(String province) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter province	name");
		provinceName.sendKeys(province);
		Thread.sleep(1000);
	}
	// Click On First Zip Code Drop Down
	public void clickOnFirstZipCodeDDown() {
		testUtil.init(this);
		logger.info("Click On First Zip Code Drop Down");
		weFirstZipCodeDDown.click();
	}
	// Lookup by City	
	public void lookUpByCity(String ByCity) {
		testUtil.init(this);
		logger.info("Select Lookup By City");
		weLookupByCity.sendKeys(ByCity);
	}
	// Click On Lookup by State	
	public void clickOnlookUpByState() {
		testUtil.init(this);
		logger.info("Select Lookup By State");
		weLookupByState.click();
	}
	
	
	// Click On Lookup by State	
		public void selectState() {
			testUtil.init(this);
			logger.info("Select Lookup By State");
			selectState.click();
		}
		
	// Click On Select State	
	public void clickOnSelectState() {
		testUtil.init(this);
		logger.info("Click On Select State");
		weSelectState.click();
	}
		
	// Enter State Name 
	public void enterStateName(String StateName) {
		testUtil.init(this);
		logger.info("Enter State Name");
		weEnterStateName.sendKeys(StateName);
	}
	// Select  State
	// Use State Abbreviation Name Ex: "VA", "NJ"
	public void selectState(String state) throws InterruptedException {
		testUtil.init(this);
		logger.info(" Select State");
		Thread.sleep(1000);
		WebElement e = driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+state+"')]"));
		e.click();
	}

	// Select Province for CANADA
	// Use State Abbreviation Name Ex: "NT", "ON"
	public void selectProvince(String ProvinceAbbreviationName) {
		testUtil.init(this);
		logger.info(" Select Province");
		driver.findElement(By.xpath("//*[contains(text(),'" + ProvinceAbbreviationName + "')]")).click();
	}
	// Click on LOOKUP
	public void clickOnLOOKUP() {
		testUtil.init(this);	
		testUtil.setHardWait(2000);
		logger.info(" Click on Lookup Link");
		
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", weLOOKUP);
		 
		// weLOOKUP.click();
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
	// 	Next-Day Coverage
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
	}
	
	public void clickOnExpandMore() throws InterruptedException {
		testUtil.init(this);		
		logger.info("Click on Expand More");	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		testUtil.setHardWait(1000);
		for(WebElement e: expandTerminal)
		{
			if(e.getText().trim().equalsIgnoreCase("expand_more")) {
				e.click();
				break;
			}
		}
		
		
		}
	
	// Verify Terminal Address	
	public String getTerminalAddress() {

		testUtil.init(this);
		logger.info("Verify Terminal Address");
		String TAddress = weTerminalAddress.getText();
		return TAddress;
	}



	// Verify Get an LTL Rate Quote Calculate Rate Displayed
	public void verifyLTLRateQuoteCalRate() {
		testUtil.init(this);
		logger.info("Verify Get an LTL Rate Quote Calculate Rate");
		weLTLRQCalculateRate.isDisplayed();
	}
	//Verify Calculate Transit Time Calculate time Displayed	
	public void verifyweCalTTCalTime() {
		testUtil.init(this);
		logger.info("Verify  Calculate Transite Calculate time Displayed");
		weCalTTCalTime.isDisplayed();
	}
	//Verify LTL Rate Quote Calculate From	
	public void verifyLTLRateQCalFrom() {
		testUtil.init(this);
		logger.info("Verify LTL Rate Quote Calculate Rate");
		weLTLRateQuoteCalFrom.isDisplayed();
	}

	//Verify LTL Rate Quote Calculate To	
	public void verifyLTLRateQCalTo() {
		testUtil.init(this);
		logger.info("Verify LTL Rate Quote Calculate To");
		weLTLRQCalculateTo.getAttribute("value");
	}
	//Verify Calculate Transit Time Calculate From
	public void verifyTTCalculateFrom() {
		testUtil.init(this);
		logger.info("Verify  Calculate Transite Time CALCUTE From");
		weTTCalculateFrom.isDisplayed();
	}
	//Verify Calculate Transit Time Calculate To	
	public void verifyTTCalculateTo() {
		testUtil.init(this);
		logger.info("Verify  Calculate Transite Time CALCUTE TO");
		weTTCalculateTo.getAttribute("value");
	}
	//Verify LTL Pickup Request REQUEST PICKUP	
	public void verifyLTLPRRequestPickup() {
		testUtil.init(this);
		logger.info("Verify LTL Pickup Request 	REQUEST PICKUP");
		weLTLPRRequestPickup.isDisplayed();
	}

	public void verifyTerminalName() {
		testUtil.init(this);
		logger.info("Verify Terminal Name");
		terminalName.isDisplayed();
	}
	
	public void verifyTerminalAddress() {
		testUtil.init(this);
		logger.info("Verify Terminal Address ");
		terminalAddress.isDisplayed();
		
	}
	
	public void verifyTerminalPhone() {
		testUtil.init(this);
		logger.info("Verify Terminal Phone ");
		terminalPhone.isDisplayed();
	}
	
	public void verifyTerminalFax() {
		testUtil.init(this);
		logger.info("Verify Terminal Fax");
		terminalFax.isDisplayed();
	}
	
	public void verifyTerminalEmail() {
		testUtil.init(this);
		logger.info("Verify Terminal Email");
		terminalEmail.isDisplayed();
	}
	
	public void verifyTerminalManager() {
		testUtil.init(this);
		logger.info("Verify Terminal Manager");
		for(WebElement e: terminalManager) {
			e.isDisplayed();
			break;
		}
	}
	
	public void verifyTerminalNextDayCoverage() {
		testUtil.init(this);
		logger.info("Verify Terminal National Coverage Map");
		for(WebElement e: terminalNextDayCov) {
			e.isDisplayed();
			break;
		}
	}
	
	public void verifyTerminalNextDayCoverageisHidden() {
		testUtil.init(this);
		logger.info("Verify Terminal National Coverage Map");
		Assert.assertEquals(terminalNextDayCov.size(), 0);
	}
	
	public void verifyTerminalNationalCoverageMap() {
		testUtil.init(this);
		logger.info("Verify Terminal National Coverage Map");
		for(WebElement e: terminalNatCovMap) {
			e.isDisplayed();
			break;
		}
	}
	
	public void verifyTerminalGetRateQuote() {
		testUtil.init(this);
		logger.info("Verify Terminal Get Rate Quote");
		terminalGetRateQuote.isDisplayed();
	}
	
	public void verifyCalculateRate() {
		testUtil.init(this);
		logger.info("Verify Calculate Rate button ");
		buttonCalculateRate.isDisplayed();
	}
	
	public void getRateQuoteFrom(String zip)
	{
		testUtil.init(this);
		logger.info("Verify Get Rate Quote From button ");
		WebElement element = driver.findElement(By.xpath("//div[div[strong[text()='Get a Rate Quote']]]/div[2]/button[contains(text(),'Calculate from "+zip+"')]"));
		element.isDisplayed();
	
	}
	
	public void getRateQuoteTo(String zip) {
		testUtil.init(this);
		logger.info("Verify Get Rate Quote To button ");
		WebElement element = driver.findElement(By.xpath("//div[div[strong[text()='Get a Rate Quote']]]/div[3]/button[contains(text(),'Calculate to "+zip+"')]"));
		element.isDisplayed();
	}
	
	public void calculateTransitTimeFrom(String zip)
	{
		testUtil.init(this);
		logger.info("Verify Get Rate Quote From button ");
		WebElement element = driver.findElement(By.xpath("//div[div[strong[text()='Calculate Transit Time']]]/div[5]/button[contains(text(),'Calculate from "+zip+"')]"));
		element.isDisplayed();
	
	}
	
	public void calculateTransitTimeTo(String zip) {
		testUtil.init(this);
		logger.info("Verify Get Rate Quote To button ");
		WebElement element = driver.findElement(By.xpath("//div[div[strong[text()='Calculate Transit Time']]]/div[6]/button[contains(text(),'Calculate to "+zip+"')]"));
		element.isDisplayed();
	}
	
	public void verifyTerminalCalculateTransitTime() {
		testUtil.init(this);
		logger.info("Verify Terminal Calculate Transit Time");
		terminalCalculateTransitTime.isDisplayed();
	}
	
	public void verifyCalculateTime() {
		testUtil.init(this);
		logger.info("Verify Calculate Time ");
		buttonCalculateTime.isDisplayed();
	}
	
	public void verifyTerminalLtlPickupRequest() {
		testUtil.init(this);
		logger.info("Verify Terminal Ltl Pickup Request");
		terminalLtlPickupRequest.isDisplayed();
	}
	
	public void verifyRequestPickup() {
		testUtil.init(this);
		logger.info("Verify Request Pickup ");
		buttonRequestPickup.isDisplayed();
	}
	
	public void verifyErrorMessage() {
		testUtil.init(this);
		logger.info("Verify Error Message");
		testUtil.setHardWait(1000);
		Assert.assertEquals(errorMessage.getText().trim(), "ERROR: No terminal info available.");
	}

	public void verifyTerminalLookupPageTile() {
		testUtil.init(this);
		logger.info("Verify Terminal Lookup page title");
		testUtil.getCurrentPageTitle();
	} 
	
	public void verifyTerminalLookupPage() {
		logger.info("Verify Terminal Lookup Page title");
		testUtil.init(this);
		testUtil.setExplicitWait(terminalLookupTitle, 20);
		boolean title = terminalLookupTitle.isDisplayed();
		Assert.assertTrue(title);
	}

}
