package claimsPages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import org.openqa.selenium.support.FindAll;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class FileAClaimPage extends TestBase {

	private Logger logger = Logger.getLogger(FileAClaimPage.class);

	@FindBy(how = How.XPATH, using = "//button[text()='File a Claim']")
	private WebElement fileClaimButton;

	@FindBy(how = How.XPATH, using = "//a[text()='Account Search']")
	private WebElement lnkAccountSearch;

	@FindBy(how = How.XPATH, using = "//div[mat-card-title[contains(text(),'Account Search')]]/descendant::input")
	private WebElement txtboxAccountSearch;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Account Number')]")
	private WebElement acctNoHeader;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Account Name')]")
	private WebElement acctNameHeader;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Address')]")
	private WebElement addressHeader;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'City')]")
	private WebElement cityHeader;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'State')]")
	private WebElement stateHeader;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Zip')]")
	private WebElement zipHeader;

	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'accountNumber')]/a")
	private WebElement acctNoValue;

	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'name')]/span")
	private WebElement acctNameValue;

	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'streetAddress')]/span")
	private WebElement addressValue;

	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'city')]/span")
	private WebElement cityValue;

	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'state')]/span")
	private WebElement stateValue;

	@FindBy(how = How.XPATH, using = "//mat-cell[contains(@class,'zip')]/span")
	private WebElement zipValue;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'no-results')]/p")
	private WebElement textNoresults;

	@FindBy(how = How.XPATH, using = "//mat-row[2]//mat-cell[1]/a")
	private WebElement firstAccNum2;

	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[1]/a")
	private WebElement firstAccNum;

	@FindBy(how = How.XPATH, using = "//input[@id='addressSelect']")
	private WebElement AccNum;

	@FindBy(how = How.ID, using = "otPro")
	private WebElement PRONum;

	@FindBy(how = How.ID, using = "invoiceFile")
	private WebElement browse;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary mr-2']")
	private WebElement submit;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Account Search')]")
	private WebElement accountSearchLink;

	@FindBy(how = How.XPATH, using = "//input[@id='mat-input-41']")
	private WebElement accSearch;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-tab-content-2-1\"]/div/app-file-claim/div/lib-feedback/div/span")
	private WebElement message;

	@FindBy(how = How.ID, using = "mat-error-16")
	private WebElement invoiceError;

	@FindBy(how = How.ID, using = "mat-error-17")
	private WebElement BOLError;

	@FindBy(how = How.ID, using = "mat-error-18")
	private WebElement estesFreightError;

	@FindBy(how = How.ID, using = "mat-error-19")
	private WebElement otherError;

	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'File exceeds size limit of 10MB ')]")
	private WebElement errorMsg;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'The total size of the documents you are attempting to upload is larger than 50 MB.')]")
	private WebElement errorMessage;

	@FindBy(how = How.ID, using = "bol")
	private WebElement bolNumberField;

	@FindBy(how = How.ID, using = "referenceNumber")
	private WebElement refNumberField;

	/********************** METHODS ******************************/

	public void clickOnFileClaimButton() throws InterruptedException {
		logger.info("Click on file claim button ");
		testUtil.init(this);
		fileClaimButton.click();
	}

	public void clickOnAccountSearch() throws InterruptedException {
		logger.info("Click on Account search");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		lnkAccountSearch.click();
	}

	public void enterInAccountSearch(String value) throws InterruptedException {
		logger.info("Enter in Account search");
		testUtil.init(this);
		txtboxAccountSearch.clear();
		testUtil.setExplicitWait(txtboxAccountSearch, 90);
		txtboxAccountSearch.sendKeys(value);
		testUtil.setHardWait(2000);
	}

	public void verifyAccountNumberHeader() {
		testUtil.init(this);
		logger.info("Verify Account Number Header");
		testUtil.setExplicitWait(acctNoHeader, 60);
		Assert.assertEquals(acctNoHeader.isDisplayed(), true);
		testUtil.setHardWait(1000);
	}

	public void verifyAccountNameHeader() {
		testUtil.init(this);
		logger.info("Verify Account Name Header");
		Assert.assertEquals(acctNameHeader.isDisplayed(), true);
	}

	public void verifyAddressHeader() {
		testUtil.init(this);
		logger.info("Verify Address Header");
		Assert.assertEquals(addressHeader.isDisplayed(), true);
	}

	public void verifyCityHeader() {
		testUtil.init(this);
		logger.info("Verify City Header");
		Assert.assertEquals(cityHeader.isDisplayed(), true);
	}

	public void verifyStateHeader() {
		testUtil.init(this);
		logger.info("Verify State Header");
		Assert.assertEquals(stateHeader.isDisplayed(), true);
	}

	public void verifyZipHeader() {
		testUtil.init(this);
		logger.info("Verify Zip Header");
		Assert.assertEquals(zipHeader.isDisplayed(), true);
	}

	public void verifyAccountNumberValue() {
		testUtil.init(this);
		logger.info("Verify Account Number Value");
		Assert.assertEquals(acctNoValue.isDisplayed(), true);
	}

	public void verifyAccountNameValue() {
		testUtil.init(this);
		logger.info("Verify Account Name Value");
		Assert.assertEquals(acctNameValue.isDisplayed(), true);
	}

	public void verifyAddressValue() {
		testUtil.init(this);
		logger.info("Verify Address Value");
		Assert.assertEquals(addressValue.isDisplayed(), true);
	}

	public void verifyCityValue() {
		testUtil.init(this);
		logger.info("Verify City Value");
		Assert.assertEquals(cityValue.isDisplayed(), true);
	}

	public void verifyStateValue() {
		testUtil.init(this);
		logger.info("Verify State Value");
		Assert.assertEquals(stateValue.isDisplayed(), true);
	}

	public void verifyZipValue() {
		testUtil.init(this);
		logger.info("Verify Zip Value");
		Assert.assertEquals(zipValue.isDisplayed(), true);
	}

	public void verifyResultsText(String expectedText) {
		testUtil.init(this);
		logger.info("Verify Results Text");
		Assert.assertEquals(textNoresults.getText().trim(), expectedText);
	}

	public String getAccNumForFirstRow() {
		String AccNumber = driver.findElement(By.xpath("//mat-row[1]//mat-cell[1]")).getText();
		System.out.println("Account Number: " + AccNumber);
		return AccNumber;
	}

	public String getAccNumForSecondRow() {
		String AccNumber = driver.findElement(By.xpath("//mat-row[2]//mat-cell[1]/a")).getText();
		System.out.println("Account Number: " + AccNumber);
		return AccNumber;
	}

	public void clickOnSecondAccountNumber() {
		logger.info("Click on second account number");
		testUtil.init(this);
		firstAccNum2.click();

	}

	public void clickOnFirstAccountNumber() {
		logger.info("Click on first account number");
		testUtil.init(this);
		testUtil.clickElementJavascript(firstAccNum);

	}

	public void enterAccountNumber(String AccNumber) {
		logger.info("Enter account number");
		testUtil.init(this);

		AccNum.sendKeys(AccNumber);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
		testUtil.setHardWait(3000);

	}

	public void clickOnAccountSearchLink() {
		logger.info("Click on account search link");
		testUtil.init(this);
		// accountSearchLink.click();

		testUtil.WaitForTheElementClickable(accountSearchLink);

		testUtil.setHardWait(1000);

		testUtil.clickElementJavascript(accountSearchLink);

		testUtil.setHardWait(3000);

	}

	public void enterAccountNumberInAccSearch(String AccNumber) {
		logger.info("Enter account number");
		testUtil.init(this);
		accSearch.clear();
		testUtil.setHardWait(2000);
		accSearch.sendKeys(AccNumber);
		testUtil.setHardWait(2000);

	}

	public void enterPRONumber(String proNumber) {
		logger.info("Enter PRO number");
		testUtil.init(this);
		PRONum.clear();
		PRONum.sendKeys(proNumber);

	}

	public void selectClaimType(String type) {
		logger.info("select claim type ");
		testUtil.init(this);
		driver.findElement(By.xpath("//mat-select[@id='claimType']//div[@class='mat-select-value']")).click();
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + type + "')]")).click();
	}

	public void verifyClaimantInfoFieldsArePrefilled() {
		logger.info("Verifying claimant info fields are populated and not empty.....");
		testUtil.init(this);
		String name = driver.findElement(By.id("name")).getText();
		String address1 = driver.findElement(By.id("streetAddress1")).getText();
		String city = driver.findElement(By.id("city")).getText();
		String state = driver.findElement(By.id("state")).getText();
		String zip = driver.findElement(By.id("zip")).getText();
		String phone = driver.findElement(By.id("phone")).getText();
		String email = driver.findElement(By.id("email")).getText();
		Assert.assertNotNull(name);
		Assert.assertNotNull(address1);
		Assert.assertNotNull(city);
		Assert.assertNotNull(state);
		Assert.assertNotNull(zip);
		Assert.assertNotNull(phone);
		Assert.assertNotNull(email);

	}

	public void verifyRemitInfoFieldsArePrefilled() {
		logger.info("Verifying Remit info fields are populated and not empty.....");
		testUtil.init(this);
		String name = driver.findElement(By.id("remitName")).getText();
		String address1 = driver.findElement(By.id("remitStreetAddress1")).getText();
		String city = driver.findElement(By.id("remitCity")).getText();
		String state = driver.findElement(By.id("remitState")).getText();
		String zip = driver.findElement(By.id("remiZip")).getText();
		String phone = driver.findElement(By.id("remitPhone")).getText();

		Assert.assertNotNull(name);
		Assert.assertNotNull(address1);
		Assert.assertNotNull(city);
		Assert.assertNotNull(state);
		Assert.assertNotNull(zip);
		Assert.assertNotNull(phone);

	}

	public void verifyPRONumsAndClaimTypeArePreFilled() {
		logger.info("Verifying PRO number and claim type section fields are populated and not empty.....");
		testUtil.init(this);
		String bolNum = driver.findElement(By.id("bol")).getText();
		String proDate = driver.findElement(By.xpath(
				"//*[@id=\"mat-tab-content-0-1\"]/div/app-file-claim/form/mat-card/mat-card-content[1]/div[1]/div[2]/span"))
				.getText();
		String bolDate = driver.findElement(By.xpath(
				"//*[@id=\"mat-tab-content-0-1\"]/div/app-file-claim/form/mat-card/mat-card-content[1]/div[2]/div[2]/span"))
				.getText();

		Assert.assertNotNull(bolNum);
		Assert.assertNotNull(proDate);
		Assert.assertNotNull(bolDate);

	}

	public void verifyShipperAndConsigneeFieldsArePreFilled() {
		logger.info("Verifying Shipper and Consignee fields are populated and not empty.....");
		testUtil.init(this);

		String shipper = driver.findElement(By.xpath("//mat-card/mat-card-content[3]/div/div[1]/span")).getText();
		String consignee = driver.findElement(By.xpath("//mat-card/mat-card-content[3]/div/div[3]/span")).getText();

		Assert.assertNotNull(shipper);
		Assert.assertNotNull(consignee);

	}

	public void clickOnInvoiceBrowseButton() {
		logger.info("Click on invoice browse button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		// browse.click();
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.id("invoiceFile"));
		actions.doubleClick(elementLocator).perform();
		testUtil.setHardWait(1000);

	}

	// Click On Browse
	public void clickOnBrowseButton() {
		logger.info("Click on Browse Button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.id("addressFile"));
		actions.doubleClick(elementLocator).perform();

	}

	public void clickOnSubmitButton() {
		logger.info("Enter account number");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		submit.click();
		testUtil.setHardWait(1000);

	}

	public void validateTheMessageIsDisplayed(String claimNum) {
		logger.info("Validate the message is displayed as expected");
		testUtil.init(this);
		testUtil.setHardWait(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		testUtil.setHardWait(3000);

		String Message = driver
				.findElement(By.xpath("//*[@id='mat-tab-content-0-1']/div/app-file-claim/div/lib-feedback/div/span"))
				.getText();
		System.out.println(Message);
		Assert.assertTrue(Message.contains(claimNum));

	}

	public void selectFreightType(String type) {
		logger.info("select freight type ");
		testUtil.init(this);
		driver.findElement(By.xpath("//mat-select[@id='freightType']//div[@class='mat-select-value']")).click();
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + type + "')]")).click();
	}

	public void clickOnVendorInvoiceBrowseLink(String filePath) {
		logger.info("Click on invoice browse button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("invoiceFile")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void validateErrorMessageForVendorInvoice(String error) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMsg, 60);
		String message = errorMsg.getText();
		System.out.println(message);
		assertEquals(message, error);
	}

	public void validateErrorMessageForBOL(String error) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMsg, 60);
		String message = errorMsg.getText();
		System.out.println(message);
		assertEquals(message, error);
	}

	public void validateErrorMessageForEstesFreightBill(String error) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(estesFreightError, 60);
		String message = estesFreightError.getText();
		System.out.println(message);
		assertEquals(message, error);
	}

	public void validateErrorMessageForOther(String error) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(otherError, 60);
		String message = otherError.getText();
		System.out.println(message);
		assertEquals(message, error);
	}

	public void clickOnBOLBrowseLink(String filePath) {
		logger.info("Click on BOL browse button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("bolFile")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnEstesFBBrowseLink(String filePath) {
		logger.info("Click on estes FB browse button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("fbFile")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnOtherBrowseLink(String filePath) {
		logger.info("Click on other browse button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("otherFile")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnDocument1BrowseLink(String filePath) {
		logger.info("Click document 1 browse link");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("fileDetails1")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnDocument2BrowseLink(String filePath) {
		logger.info("Click document 2 browse link");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("fileDetails2")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnDocument3BrowseLink(String filePath) {
		logger.info("Click document 3 browse link");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("fileDetails3")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnDocument4BrowseLink(String filePath) {
		logger.info("Click document 4 browse link");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("fileDetails4")).sendKeys(filePath);
		testUtil.setHardWait(1000);
	}

	public void clickOnDocument5BrowseLink(String filePath) {
		logger.info("Click document 5 browse link");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.id("fileDetails5")).sendKeys(filePath);
		testUtil.setHardWait(1000);

	}

	public void clickOnAddDetail() {
		logger.info("Click add detail button ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//button[contains(text() ,'Add Detail')]")).click();
		testUtil.setHardWait(1000);

	}

	public void verifyErrorMessages(String sectionName) {
		logger.info("Verify error message in " + sectionName + " Info section");
		testUtil.init(this);

		String objProp = null;
		int expectedCount = 0;

		switch (sectionName) {
		case "Upload Freight Documents":
			objProp = "//mat-card-content[4]/div[*]/div[2]/mat-error";
			expectedCount = 4;
			break;
		case "Claim Details":
			objProp = "//mat-card-content[5]/div[*]/div[2]/div[2]/div[2]/div[2]/mat-error";
			expectedCount = 5;
			break;

		}

		List<WebElement> ErrMsg = driver.findElements(By.xpath(objProp));
		testUtil.setHardWait(1000);
		Assert.assertEquals(ErrMsg.size(), expectedCount);

		logger.info("Validate error message text");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMsg, 60);
		String message = errorMsg.getText();
		System.out.println(message);
		assertEquals(message, "File exceeds size limit of 10MB");
	}

	public void validateErrorMessage(String error) {
		logger.info("Verify error message ");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMessage, 60);
		String message = errorMessage.getText();
		System.out.println(message);
		assertEquals(message, error);

	}

	public void enterBOLNumber(String number) {
		logger.info("Enter BOL number ");
		testUtil.init(this);
		bolNumberField.sendKeys(number);
	}

	public void enterReferenceNumber(String number) {
		logger.info("Enter reference number ");
		testUtil.init(this);
		refNumberField.sendKeys(number);
	}
}
