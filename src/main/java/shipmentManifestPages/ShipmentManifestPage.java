package shipmentManifestPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ShipmentManifestPage extends TestBase {

	private Logger logger = Logger.getLogger(ShipmentManifestPage.class);

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement Submit;

	@FindBy(how = How.ID, using = "account")
	private WebElement Account;

	@FindBy(how = How.XPATH, using = "//*[@id=\"shipmentType\"]/div/div[1]")
	private WebElement ShipmentType;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Outbound')]")
	private WebElement Outbound;

	@FindBy(how = How.XPATH, using = "(//span[@class='fal fa-chevron-down'])[1]")
	private WebElement Caret;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Third Party Bill-to')]")
	private WebElement thirdParty;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Inbound')]")
	private WebElement inBound;

	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1']//div[1]//table[1]//thead[1]//tr[1]//th[1]")
	private WebElement headerPRONumber;

	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1']//div[1]//table[1]//thead[1]//tr[1]//th[2]")
	private WebElement headerPickupDate;

	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1']//div[1]//table[1]//thead[1]//tr[1]//th[3]")
	private WebElement headerDeliveryDate;

	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1']//div[1]//table[1]//thead[1]//tr[1]//th[4]")
	private WebElement headerBOLNumber;

	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1']//div[1]//table[1]//thead[1]//tr[1]//th[5]")
	private WebElement headerPONumber;

	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1']//div[1]//table[1]//thead[1]//tr[1]//th[5]")
	private WebElement headerStatus;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Account Search')]")
	private WebElement accountSearchLink;

	@FindBy(how = How.XPATH, using = "//mat-row[2]//mat-cell[1]")
	private WebElement firstAccNum2;

	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[1]")
	private WebElement firstAccNum;

	public void clickOnSubmitButton() {
		logger.info("Click on submit button ");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(Submit);
		testUtil.clickElementJavascript(Submit);
		testUtil.setHardWait(3000);
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 */

	}

	public void clickOnCaret() {
		logger.info("click on Caret ");
		testUtil.init(this);
		Caret.click();
	}

	public void enterAccountNumber(String AccNum) {
		logger.info("enter Account Number ");
		testUtil.init(this);
		Account.clear();
		Account.sendKeys(AccNum);
	}

	public void selectShipmentType() {
		logger.info("select shipment type ");
		testUtil.init(this);
		ShipmentType.click();

	}

	public void selectOutbound() {
		logger.info("select Oubound ");
		testUtil.init(this);
		Outbound.click();

	}

	// (//table[@class='mat-table'])[1]
	public void verifyShimentDetailsResult() {
		logger.info("verify shipment details results ");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData("//tr[@class='element-row mat-row ng-tns-c3-1 ng-star-inserted'][1]");
		String details = driver
				.findElement(By.xpath("//tr[@class='element-row mat-row ng-tns-c3-1 ng-star-inserted'][1]")).getText();
		System.out.println(details);

		// String details=driver.findElement(By.xpath(("(//tr[@class='mat-header-row
		// ng-tns-c3-1 ng-star-inserted'])[1]"))).getText();
		// String detail=driver.findElement(By.xpath(("(//tr[@class='mat-header-row
		// ng-tns-c3-1 ng-star-inserted'])[1]"))).getAttribute("");
		// System.out.println(details);
		// System.out.println(detail);
	}

	public void verifyShimentInformationResult() {
		logger.info("verify shipment information results ");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData("(//tr[@class='detail-row mat-row ng-tns-c3-1 ng-star-inserted'])[1]");

	}

	public void selectInbound() {
		logger.info("select Inbound ");
		testUtil.init(this);
		inBound.click();
		testUtil.setHardWait(2000);

	}

	public void selectThirdParty() {
		logger.info("select ThirdParty ");
		testUtil.init(this);
		thirdParty.click();

	}

	public void verifyHeaderPRONumber() {
		testUtil.init(this);
		testUtil.setHardWait(3000);
		logger.info("Verify Header PRO Number");
		Assert.assertEquals(headerPRONumber.isDisplayed(), true);
	}

	public void verifyHeaderPickupDate() {
		testUtil.init(this);
		logger.info("Verify Header Pickup Date");
		Assert.assertEquals(headerPickupDate.isDisplayed(), true);
	}

	public void verifyHeaderDeliveryDate() {
		testUtil.init(this);
		logger.info("Verify Header Pickup Date");
		Assert.assertEquals(headerDeliveryDate.isDisplayed(), true);
	}

	public void verifyHeaderBOLNumber() {
		testUtil.init(this);
		logger.info("Verify Header BOL Number");
		Assert.assertEquals(headerBOLNumber.isDisplayed(), true);
	}

	public void verifyHeaderPONumber() {
		testUtil.init(this);
		logger.info("Verify Header PO Number");
		Assert.assertEquals(headerPONumber.isDisplayed(), true);
	}

	public void verifyHeaderStatus() {
		testUtil.init(this);
		logger.info("Verify Header Status");
		Assert.assertEquals(headerStatus.isDisplayed(), true);
	}

	public void clickOnAccountSearchLink() {
		logger.info("Click on account search");
		testUtil.init(this);
		// accountSearchLink.click();

		testUtil.WaitForTheElementClickable(accountSearchLink);

		testUtil.setHardWait(1000);

		testUtil.clickElementJavascript(accountSearchLink);

		testUtil.setHardWait(3000);

	}

	public void clickOnSecondAccountNumber() {
		logger.info("Click on second account number");
		testUtil.init(this);
		firstAccNum2.click();

	}

	public void clickOnFirstAccountNumber() {
		logger.info("Click on first account number");
		testUtil.init(this);
		firstAccNum.click();

	}

	// mat-row[1]//mat-cell[1]
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

}
