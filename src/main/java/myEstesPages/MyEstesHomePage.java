

package myEstesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Resources;

import testBase.TestBase;
import util.TestUtil;

public class MyEstesHomePage extends TestBase {
	
/*	public void MyEstesHomPage() {
	testUtil.init(this);
	
	}*/ 
	
  Logger logger = Logger.getLogger(MyEstesHomePage.class);
  
							/*********** ELEMENTS ***********/

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Account number not found')]")
	private WebElement weAccNumNotFoundMess;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Address Book')]")
	private WebElement AddressBook;

	@FindBy(linkText = "Create Account")
	private WebElement CreateAccount;

	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']//a[contains(text(),'Tools')]")
	private WebElement ToolsLink;  

	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement MyEstes;

	@FindBy(how = How.XPATH, using = "//a[@class='nav-link dropdown-toggle'][contains(text(),'Resources')]")
	private WebElement Resources;

	@FindBy(how = How.XPATH, using = "//a[@href='/myestes/home/login']")
	private WebElement Login;

	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'][contains(text(),'Request Account Number')]")
	private WebElement RequestAccountNumber;

	// Account Look up

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='accountLookup']")
	private WebElement weAccLookup;

	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'][contains(text(),'Logout')]")
	//@FindBy(how=How.XPATH,using="//*[@id='my-estes-dropdown']")
	private WebElement weLogout;

	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Confirm')]")
	private WebElement weConfirmButton;

	@FindBy(how = How.XPATH, using = "//*[contains(@type, 'submit')]")
	private WebElement weSubmitButton;

	/* Ship */

	@FindBy(linkText = "Ship")
	private WebElement weShipLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Bill of Lading')]")
	private WebElement weBillOfLading;
	// Terminal Look up from Ship

	@FindBy(how = How.XPATH, using = "//ul/a[text()='Terminal Lookup']")
	private WebElement weTerminalLookup;

	@FindBy(partialLinkText = "Terminal Lookup")
	private WebElement terminalLookup; 
	
	// Density Calculator From Ship

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Density Calculator')]")

	private WebElement weDensityCalculator;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Rate Quote')]")
	private WebElement RateQoute;

	// Click on Track
	@FindBy(linkText = "Track")
	private WebElement Track;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Shipment Tracking')]")
	private WebElement ShipmentTracking;
	
	@FindBy(how = How.XPATH, using = "(//a[contains(text(), 'Pickup Visibility')])[1]")
	private WebElement pickupVisibility; 

	/* Manage */

	@FindBy(how = How.LINK_TEXT, using = "Manage")
	private WebElement weManageLink;

	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[1]/li[3]/div/a[7]")
	private WebElement weClaimsFromManage;

	@FindBy(partialLinkText = "Document Retrieval")
	private WebElement documentRetrieval;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Weight & Research Inquiry')]")
	private WebElement WeightReaearch;

	/* Resources */

	@FindBy(how = How.XPATH, using = "//a[@href='javascript:void(0)'][contains(text(),'Resources')]")
	private WebElement ResourcesLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Fees and Surcharges')]")
	private WebElement FeesandSurcharges;

	// Fuel Surcharge from Resources
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Fuel Surcharge')]")
	private WebElement weFuelSurcharge;
	// Click on Contact

	// First Account#
	//@FindBy(how = How.XPATH, using = "//*[@class='mat-card-content']/table/tbody/tr[1]/td[1]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='main']/lib-welcome-page/mat-card/mat-card-content/table/tbody/tr[1]/td[1]/a")
	private WebElement weFirstAccountLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Claims')]")
	private WebElement Claims;

	// Address Book From My Estes
	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'][contains(text(),'Address Book')]")
	private WebElement weAddBookFromMyEstes;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Digital Services')]")
	private WebElement Digital;

	@FindBy(how = How.XPATH, using = "//*[@class='mat-card-content']/h4")
	private WebElement ErrorHeaderMessage;

	@FindBy(how = How.XPATH, using = "//*[@class='mat-card-content']/p")
	private WebElement ErrorMessageContent;

	@FindBy(how = How.XPATH, using = "//button[text()='Back to Welcome Page']")
	private WebElement WelcomeBack;

	// @FindBy(how = How.XPATH, using = "//mat-card-content[h4[text()='Request a
	// Quote']]/a/button")
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Start Request')]")
	private WebElement StartRequest;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary ng-star-inserted']")
	private WebElement Addcommodity;

	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[1]/li[5]/ul/a[6][contains(text(),'Fees and Surcharges')]")
	private WebElement FeesAndSurcharges;

	@FindBy(how = How.ID, using = "filter")
	private WebElement proNumber;

	@FindBy(xpath = "//a[text()='Invoice Inquiry'][contains(@class,'drop')]")
	private WebElement invoiceInquiry;

	@FindBy(linkText = "Transit Time Calculator")
	private WebElement TransitTimeCalculator;

	// Pickup Request
	@FindBy(xpath = "(//a[contains(text(),'Pickup Request')])[1]")
	// @FindBy(css = "a[href$='pickup-request/'][class*='dropdown']")
	private WebElement wepickupRequest;

	@FindBy(id = "tracking-number-type")
	private WebElement trackShipment;

	@FindBy(id = "pro-number")
	private WebElement txtNumber;

	@FindBy(xpath = "//button[text()='Track Now']")
	private WebElement btnTrackNow;


	@FindBy(xpath = "//a[@id='home-first-cta-block-btn']")
	private WebElement startRequest;

	@FindBy(xpath = "//a[contains(text(),'Canadian Currency Conversion')]")
	private WebElement cndCurrency;

	@FindBy(xpath = "//ul[@class='list-group']/li/a")
	private List<WebElement> blockedApps;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Recent Shipments')]")
	private WebElement recentShipments;
	
	@FindBy(xpath ="//div//span[contains(text(),'LTL Rate Quote')]")
	private WebElement QuickLinkLTLRateQuote;

	@FindBy(how = How.XPATH, using = "//div[@class='quick-link quick-link--header']")
	private WebElement QuickLinks; 

	@FindBy(partialLinkText = "LTL Rate Quote")
	private WebElement LTLRateQuote; 
	
//	@FindBy(xpath = "//*[@id='main-nav']/ul[1]/li[1]/ul/a[6][contains(text(),'Transit Time Calculator')]")  --changed to below line
	@FindBy(xpath = "//a[contains(@class,'dropdown-item')][contains(text(),'Transit Time Calculator')]")
	private WebElement calculateTransitTimesLink;

//	@FindBy(xpath = "//*[@id='main-nav']/ul[1]/li/ul/a[contains(text(),'Bill of Lading')]")   --changed to below line
	@FindBy(xpath = "//a[contains(@class,'dropdown-item')][contains(text(),'Bill of Lading')]")
	private WebElement createBillOfLadingLink;
	
//	@FindBy(xpath = "//*[@id='main-nav']/ul[1]/li/ul/a[contains(text(),'Pickup Request')]")  --changed to below line
	@FindBy(xpath = "//a[contains(text(),'Pickup Request')]")
	private WebElement scheduleAPickupLink;
	
//	@FindBy(xpath = "//*[@id='sidebar']/lib-quick-links/div/div[4]/a/div/i")   --changed to below line
	@FindBy(xpath = "//span[contains(text(),'Document Retrieval')]")
	private WebElement viewShippingDocumentsLink;
	
//	@FindBy(xpath = "//*[@id='main-nav']/ul[1]/li/ul/a[contains(text(),'Terminal Lookup')]")   --changed to below line
	@FindBy(xpath = "//li[contains(@class,'list-group-item')]//a[contains(@href,'terminal-lookup')]")
	private WebElement terminalLookupLink;

	@FindBy(css = "[class='card promo'] a")
	private WebElement signUpLink;

	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'PREMIER MANUFACTURING')]")
	private WebElement companyName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'5068692')]")
	private WebElement accNumber;
	
	@FindBy(how = How.ID, using = "addressSelect")
	private WebElement accLookup; 
	
	@FindBy(xpath="//*[@id='main-nav']/ul[1]/li[4]/a")
    private WebElement solutionsTab;

	@FindBy(xpath="//a[text()='Home Delivery']")
	private WebElement homeDeliveryLink;
	
	@FindBy(xpath = "//button[text()='Back to Welcome Page']")
	private WebElement backToWelcomePage;
	
	@FindBy(partialLinkText = "Login")
	private WebElement login;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/lib-recent-shipments/div[1]/div[2]/mat-card/mat-card-content/a/button")
	private WebElement RequestAQuote;
	
	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'You do not have access to this page.')]")
	private WebElement accessMsg;
	
	@FindBy(xpath = "//span[contains(text(),'Terminal Lookup')]")  //newly added
	private WebElement terminalLookUpQuickLink;

				/******************************************************************/

	// Verify Account Number Not Found Message
	public void verifyAccNumNotFoundMessDisplayed() {
		logger.info("Verify Account Number Not Found Message");
		testUtil.init(this);
		weAccNumNotFoundMess.isDisplayed();
	}

	public void clickOnRequestAccountNumber() {
		logger.info("Click on Request Account Number Link");
	testUtil.init(this);
		RequestAccountNumber.click();
		testUtil.setHardWait(1000);

	}
//
//	public void clickOnLogin() {
//		logger.info("Click on login button");
//		testUtil.init(this);
//		try {
//			testUtil.assetWait(null, login, 13, 250, TimeUnit.MILLISECONDS);
//			testUtil.clickElementJavascript(login);
//		} catch (NoSuchElementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	//	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//
//	}
	
	public void clickOnLogin() {
		logger.info("Click on login button");
		testUtil.init(this);
		testUtil.filterSelector(null, login, null, 1, "Login").click();
		testUtil.setHardWait(2000);
	}

	
	public void verifyAccessMessageIsDisplayed2() {
		logger.info("Verify You do not have access to this page is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(accessMsg, 20);
		accessMsg.isDisplayed();
	}
	
	
	public void clickOnDigitalService() throws Exception {
		logger.info("Click on Digital Service tab");
		testUtil.init(this);
		// Digital.click();
		// testUtil.highlightElement(By.xpath("//*[contains(text(),'Digital
		// Services')]"));
		testUtil.setHardWait(500);
		testUtil.clickElementJavascript(Digital);
		
		

	}
	
	public void enterAcctNumClickSubmit(String acct) {

		testUtil.init(this);
		WebElement e = driver.findElement(By.cssSelector("input[id='addressSelect']"));
		testUtil.assetWait(null, e, 20, 200, TimeUnit.MILLISECONDS);
		e.sendKeys(acct);

		WebElement s = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		testUtil.assetWait(null, e, 20, 200, TimeUnit.MILLISECONDS);
		s.click();
		testUtil.setHardWait(3000);

	}

	public void clickOnAddressBookLink() {
		logger.info("Click on Address Book link");
		testUtil.init(this);
		testUtil.setExplicitWait(AddressBook, 90);
		AddressBook.click();
		testUtil.setHardWait(1000);

	}

	// updated this method for failure(qz-10512-rateQuoteSmoke)
	public void clickOnRateQoute() {
		logger.info("Click on Rate Qoute Tab");
		testUtil.init(this);
		testUtil.assetWait(null, RateQoute, 12, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(RateQoute);
		//testUtil.setHardWait(2000);

	}

	public void clickOnImageRetrievalViewing() {
		logger.info("Click on image retrieval and veiwing ");
		testUtil.init(this);
		documentRetrieval.click();

	}

	public void clickOnTrack() {
		testUtil.init(this);
		logger.info("Click on Track Tab ");
		testUtil.setExplicitWait(Track, 60);
		testUtil.clickElementJavascript(Track);
		
	}

	public void clickOnShipmentTraking() throws InterruptedException {
		logger.info("Click on Shipment Tracking button ");
		testUtil.init(this);
		testUtil.assetWait(null, ShipmentTracking, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(ShipmentTracking);
		testUtil.setHardWait(3000);
		testUtil.fluentWait(By.xpath("//span[contains(text(),'Shipment Tracking')]"), 4, 5, "Shipment Tracking");
		testUtil.setHardWait(500);
	
	}

	// Enter Account Lookup
	public void enterAccountLookup(String AccountNum) {
		logger.info(" Enter Account Lookup");
		testUtil.init(this);
		weAccLookup.sendKeys(AccountNum);
	}

	// Click on Logout
	public void clickOnLogout() {
		logger.info("Click on Logout button");
		testUtil.init(this);
		//testUtil.setExplicitWait(weLogout, 90);
		testUtil.clickElementJavascript(weLogout);
		testUtil.setHardWait(1000);

	}

	// Click On CONFIRM Button
//	public void clickOnConfirmButton() {
//		logger.info("Click on Confirm button");
//		testUtil.init(this);
//		testUtil.setExplicitWait(weConfirmButton, 60);
//		testUtil.clickElementJavascript(weConfirmButton);
//		testUtil.setHardWait(500);
//	}
	
	// Click On CONFIRM Button
	public void clickOnConfirmButton() {
		logger.info("Click on Confirm button");
		testUtil.init(this);
		WebElement confirmButton = testUtil.filterSelector(null, weConfirmButton, null, 0, ">");
		testUtil.clickElementJavascript(confirmButton);
		testUtil.setHardWait(1000);
		
	}

	// Click on Submit Button
	public void clickOnSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		weSubmitButton.click();
		testUtil.setHardWait(1000);
	}

	// Copy account Number
	public String getAccNumForFirstRow() {
		String AccNumber = driver.findElement(By.xpath("//*[@class='mat-card-content']/table/tbody/tr[1]/td[1]/a"))
				.getText();
		System.out.println("Account Number: " + AccNumber);
		return AccNumber;
	}

	// *** Ship ***
	// Click On Ship Link
	public void clickOnShipTab() {
		logger.info("Click on Ship tab");
		testUtil.init(this);
		testUtil.assetWait(null, weShipLink, 10, 200, TimeUnit.MILLISECONDS);
		weShipLink.click();

	}

	// Click on Terminal lookup From Ship without logging in
	public void clickOnTerminallookupFromShip() throws InterruptedException {
		logger.info("Click on Terminal lookup From Ship");
		testUtil.init(this);
		testUtil.setExplicitWait(weTerminalLookup, 60);
		testUtil.clickElementJavascript(weTerminalLookup);
		
	}

	// Click on Terminal lookup From Ship after logging in
	public void clickOnTerminallookupLink() throws InterruptedException {
		logger.info("Click on Terminal lookup From Ship after logging in ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		terminalLookup.click();
		testUtil.setHardWait(1000);
	}

	// Click on Bill of Lading From Ship
	public void clickOnBillOfLadingFromShip() {
		logger.info("Click on Bill of Lading from Ship tab");
		testUtil.init(this);
		testUtil.setExplicitWait(weBillOfLading, 60);
		weBillOfLading.click();
	}

	// Click on Density Calculator From Ship
	public void clickOnDensityCalculatorFromShip() {
		logger.info("Click on Density Calculator from Ship");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		weDensityCalculator.click();
	}

	// *** Manage ***
	// Click on Manage
	public void clickOnManageLink() {
		logger.info("Click on Manage link");
		testUtil.init(this);
		weManageLink.click();
	}

	// Click on Claims From Manage
	public void clickOnClaimsFromManage() {
		logger.info("Click on Claims From Manage");
		testUtil.init(this);
		weClaimsFromManage.click();
	}

	public void clickOnWeightResearchInquiry() {
		logger.info("Click on Weight and Research Inquiry");
		testUtil.init(this);
		WeightReaearch.click();
	}

	// *** Resources ***

	// Click on Resources Link
	public void clickOnResourcesLink() {
		logger.info("Click on Resources Link");
		testUtil.init(this);
		ResourcesLink.click();
	}

	/*
	 * public void clickOnFeesandSurcharges() {
	 * logger.info("Click on Resources Link"); testUtil.init(this);
	 * FeesandSurcharges.click(); }
	 */

	// Click on Fuel Surcharge from Resources

	public void clickOnFuelSurcharge() {
		logger.info("Click on Fuel Surcharge from Resources");
		testUtil.init(this);
		testUtil.setExplicitWait(weFuelSurcharge, 90);
		weFuelSurcharge.click();
	}

	public void clickOnTools() {
		logger.info("Click on tools button");
		testUtil.init(this);
		testUtil.setExplicitWait(ToolsLink,120);
		testUtil.clickElementJavascript(ToolsLink);
	//	ToolsLink.click();

	}

	public void clickOnCreateAccountLink() {
		logger.info("Click on Create Account link");
		testUtil.init(this);
		testUtil.setExplicitWait(CreateAccount, 60);
		testUtil.clickElementJavascript(CreateAccount);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
	}

//	public void clickOnMyEstes() {
//		logger.info("Click on MyEstes button");
//		testUtil.init(this);
//		//WebDriverWait wait= new WebDriverWait(driver, 120);
//		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='my-estes-dropdown']")));
//		testUtil.assetWaitClickable(null, MyEstes, 12, 250, TimeUnit.MILLISECONDS);
//		testUtil.clickElementJavascript(MyEstes);
//		testUtil.setHardWait(2000);
//		
//	}
	public void clickOnMyEstes() {
		logger.info("Click on MyEstes button");
		testUtil.init(this);
		testUtil.filterSelector(null, MyEstes, null, 2).click();
	}

	// Click on Invoice Inquiry
	public void clickOnInvoiceInquiryLink() {
		logger.info("Click on Invoice Inquiry link");
		testUtil.init(this);
		testUtil.setExplicitWait(invoiceInquiry, 60);
		testUtil.clickElementJavascript(invoiceInquiry);
	
	}

	public void clickOnResources() throws Exception {
		logger.info("Click on Resources button ");
		testUtil.init(this);
		try {
			testUtil.setHardWait(1000);
			// testUtil.highlightElement(By.xpath("//a[@class='nav-link dropdown-toggle
			// dropdown-toggle'][contains(text(),'Resources')]"));
			// Resources.click();
			testUtil.clickElementJavascript(Resources);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Click On First Account# Link
	public void clickOnFirstAccountNumLink() {
		logger.info("Click On First Account# link");
		testUtil.init(this);
		testUtil.setExplicitWait(weFirstAccountLink, 30);

		testUtil.clickElementJavascript(weFirstAccountLink);

	}

	public void clickOnClaims() {
		logger.info("Click on Claims button ");
		testUtil.init(this);
		testUtil.setExplicitWait(Claims, 120);
		testUtil.clickElementJavascript(Claims);
		testUtil.setHardWait(500);
	}

	// Click On Address Book From My Estes
	public void clickOnAddBookFromMyEstes() {
		logger.info("Click on Address Book from My Estes");
		testUtil.init(this);
		testUtil.setHardWait(500);
		weAddBookFromMyEstes.click();
	}

	public void clickOnTransitTimeCalculator() {
		logger.info("Click on Transit Time Calculator tab");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(TransitTimeCalculator);
		//TransitTimeCalculator.click();
	}

	// click on Pickup Request
	public void clickOnPickupRequest() {
		logger.info("Click on Pickup Request");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, wepickupRequest, 10, 250, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(wepickupRequest);
		testUtil.setHardWait(1000);

	}

	public String getErrorMessage() {
		String headerMessage = ErrorHeaderMessage.getText().trim();
		String messageContent = ErrorMessageContent.getText().trim();
		String errorMessage = headerMessage + messageContent;
		System.out.println("Account Number: " + errorMessage);
		return errorMessage;
	}

	public void verifyErrorMessage() {
		logger.info("Verify error message displayed");
		testUtil.setHardWait(1000);
		String expectedErrorMessage = "You do not have access to this page.Your user profile does not have permission to access this application. If you have any questions, please contact your company’s My Estes administrator or contact our Sales Support department at (804) 353-1900, Ext. 2699.";
		assertEquals(getErrorMessage(), expectedErrorMessage);
	}

	public boolean isWelcomeBackDisplayed() {
		logger.info("Check welcome back button displayed");
		boolean welcomeBack = WelcomeBack.isDisplayed();
		return welcomeBack;
	}

	public void verifyWelcomeBack() {
		logger.info("Verify error message displayedwelcome back button displayed");
		assertTrue(isWelcomeBackDisplayed());
	}

	public void clickOnWelcomeBack() {
		logger.info("Click on Welcome Back");
		testUtil.init(this);
		WelcomeBack.click();
	}

	public void clickOnStartRequest() {
		logger.info("Click on Start Request");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		StartRequest.click();
	}

	public void clickOnAddCommodity() {
		logger.info("Click on Add Commodity");
		testUtil.init(this);
		Addcommodity.click();
	}

	public void clickOnFeesAndSurcharges() throws Exception {
		logger.info("Click on Fees and Surcharges");
		testUtil.init(this);
		try {
			testUtil.setHardWait(2000);
			testUtil.clickElementJavascript(FeesAndSurcharges);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void enterPRONumber(String PRONum) {
		logger.info("Enter PRO number ");
		testUtil.init(this);
		proNumber.sendKeys(PRONum);
	}

	public void clickOnProNumber(String proNumber) throws InterruptedException {
		logger.info("Click on PRO  Number ");
		testUtil.init(this);
		Thread.sleep(500);
		WebElement ele=driver.findElement(By.xpath("//a[text()='" + proNumber + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		
	}

	public void selectTrackingType(String type) throws InterruptedException {
		logger.info("Select Tracking Type");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.selectUsingVisibleText(trackShipment, type);
	//	new Select(trackShipment).selectByVisibleText(type);
	}

	public void enterNumber(String number) {
		logger.info("Enter search criteria number");
		testUtil.init(this);
		txtNumber.sendKeys(number);
	}

	public void clickOnTrackNow() throws InterruptedException {
		logger.info("Click on Track Now");
		testUtil.init(this);
		btnTrackNow.click();
		testUtil.setHardWait(2000);
	}

    public void clickOnStartRequestButton() {
        logger.info("Click on Request a Rate Quote");
        testUtil.init(this);
        WebElement startReq = testUtil.pollDOM(null, startRequest, 90);
        testUtil.clickElementJavascript(startReq);
    }

	

	public void clickOnCanadianCurrencyConversion() {
		logger.info("Click on Claims button ");
		testUtil.init(this);
		testUtil.setHardWait(500);
		cndCurrency.click();
		testUtil.setHardWait(500);

	}

	public void verifyBlockedApplication(String expectedText) {
		logger.info("Verify Blocked Applications");
		boolean flag = false;
		testUtil.init(this);
		for (WebElement e : blockedApps) {
			if (e.getText().trim().equals(expectedText)) {
				flag = true;
				break;
			}

		}
		Assert.assertEquals(flag, false);
	}


	public void clickOnRecentShipments() throws InterruptedException {
		logger.info("Click on recent shipments");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		// ShipmentTracking.click();
		testUtil.clickElementJavascript(recentShipments);
		testUtil.setHardWait(2000);
	}

	
	public void clickOnQuickLinksLTLRateRequest () {
		logger.info("Click on LTL Rate Quotefrom Quick links");
		testUtil.init(this);
		//QuickLinkLTLRateQuote.click();
		//testUtil.setExplicitWait(QuickLinkLTLRateQuote, 120);
		
		WebDriverWait wait= new WebDriverWait(driver, 60);
		WebElement quickLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span[contains(text(),'LTL Rate Quote')]")));
		quickLink.click();
	//	testUtil.clickElementJavascript(QuickLinkLTLRateQuote);
		testUtil.setHardWait(2000);
	}
	
	public void verifyQuickLiks() {
		logger.info("Quick Links displayed");
		System.out.println(QuickLinks.getText());
		testUtil.setExplicitWait(QuickLinks, 60);
		assertTrue(QuickLinks.isDisplayed());
	} 

	public void verifyShipmentsTrackingPageDisplayed() {
		testUtil.init(this);
		logger.info("Verify Shipments Tracking Page Displayed");
		testUtil.getCurrentPageTitle();
	}

	public void clickOnLTLRateQuote() {
		logger.info("Click on LTL Rate Quote Link ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		LTLRateQuote.click();

	}

	public void clickOnDocumentRetrieval() {
		logger.info("Click on document retrieval Link ");
		testUtil.init(this);
		testUtil.setExplicitWait(documentRetrieval, 60);
		testUtil.clickElementJavascript(documentRetrieval);


	}
	
	public void clickOnTerminalLookupLink() throws InterruptedException {
		logger.info("Click on Terminal Lookup link ");
		testUtil.init(this);
		terminalLookup.click();
		testUtil.setHardWait(1000);
	} 
	public void clickOnPTCalculateTransitTimeLink() {
		logger.info("Click Calculate Transit Time link on Popular tools");
		testUtil.init(this);
		testUtil.setExplicitWait(calculateTransitTimesLink, 90);
		calculateTransitTimesLink.click();
	}
	
	public void clickOnPTCreateBillOfLadingLink() {
		logger.info("Click Create Bill of Lading link on Popular tools");
		testUtil.init(this);
		testUtil.setExplicitWait(createBillOfLadingLink, 20);
		createBillOfLadingLink.click();
	}
	
	public void clickOnPTScheduleAPickupLink() {
		logger.info("Click Schedule a Pickup link on Popular tools");
		testUtil.init(this);
		testUtil.setExplicitWait(scheduleAPickupLink, 20);
		scheduleAPickupLink.click();
	}
	
	public void clickOnPTViewShippingDocumentsLink() {
		logger.info("Click View Shipping Documents link on Popular tools");
		testUtil.init(this);
		testUtil.setExplicitWait(viewShippingDocumentsLink, 60);
		viewShippingDocumentsLink.click();
	}
	
	public void clickOnPTTerminalLookupLink() {
		logger.info("Click Terminal Lookup link on Popular tools");
		testUtil.init(this);
		testUtil.setExplicitWait(terminalLookupLink, 20);
		terminalLookupLink.click();
	}
	public void clickOnPTTerminalLookup() {
		logger.info("Click Terminal Lookup link on Popular tools");
		testUtil.init(this);
		WebElement ele=driver.findElement(By.xpath("//*[@id=\"main-nav\"]/ul[1]/li[1]/div/a[contains(text(),'Terminal Lookup')]"));
		ele.click();
	}
	
	
	public void clickOnSignUpLink() {
		logger.info("Click on Sign up link ");
		testUtil.init(this);
		testUtil.setExplicitWait(signUpLink, 20);
		testUtil.clickElementJavascript(signUpLink);
	}

	public void verifyCompanyNameDisplayed() {
		logger.info("Verify company name displayed");
		testUtil.init(this);
		//companyName.isDisplayed();
		Assert.assertEquals(companyName.isDisplayed(), true);
		
	}
	
	public void verifyAccNumDisplayed() {
		logger.info("Verify Account Number is displayed");
		testUtil.init(this);
		accNumber.isDisplayed();
	} 

	public void enterAccountNumber(String AccountNum) {
		logger.info(" Enter Account Lookup");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		accLookup.sendKeys(AccountNum);
	} 
	
	public void clickOnSolutionsTab() {
	       logger.info("Click on Solutions tab");
	       testUtil.init(this);
	       solutionsTab.click();
	    }

	public void clickOnHomeDeliveryLink() {
		 logger.info("Click on Home Delivery link");
		 testUtil.init(this);
		 testUtil.setExplicitWait(homeDeliveryLink, 20);
		 homeDeliveryLink.click();
	}
	//div/a[contains(text(),'Home Delivery')]

		
	public void verifyBackToWelcomePageExistence() {
		logger.info("Verify Back to Welcome Page existence");
		testUtil.init(this);
		testUtil.setExplicitWait(backToWelcomePage, 400);
		boolean exits = backToWelcomePage.isDisplayed();
		TestUtil.verifyTrue(exits);
	}

	public void clickOnRequestAQuote() throws InterruptedException {
		logger.info("Click on Request a Quote Start Request button ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(RequestAQuote);
		testUtil.setHardWait(1000);

	}
	
	public void verifyAccessMessageIsDisplayed() {
		logger.info("Verify You do not have access to this page is displayed");
		testUtil.init(this);

		WebElement accessMessage = testUtil.filterSelector("h4", null, null, 0, "You do not have access to this page.");
		testUtil.verifyElementHasTextContent(accessMessage);
	}
	
	public void selectTracking(String type) throws InterruptedException {
		logger.info("Select Tracking Type");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		trackShipment.click(); 
		WebElement trackShip = driver.findElement(By.xpath("//select[@id='tracking-number-type']"));
		testUtil.selectUsingValue(trackShip, type);
	}
	
	public void clickPickupVisibility() {
		logger.info("Hovering over 'Track' in header bar, then selecting 'Pickup Visibility'");
		Actions actions = new Actions(driver); 
		actions.moveToElement(Track); 
		actions.moveToElement(pickupVisibility); 
		actions.click().build().perform(); 
	}

	
	
	public void clickOnTrackHeaderTitle() {
		logger.info("Click on Track header title");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, Track, 12, 250, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(Track);
		testUtil.setHardWait(2000);
		
	}
	
	//newly created
		public void clickOnTerminallookupQuickLink() throws InterruptedException {  
			logger.info("Click on Terminal lookup Quick Link");
			testUtil.init(this);
			testUtil.setExplicitWait(terminalLookUpQuickLink, 60);
			testUtil.clickElementJavascript(terminalLookUpQuickLink);
		}

}

