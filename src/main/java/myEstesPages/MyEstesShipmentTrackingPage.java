package myEstesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class MyEstesShipmentTrackingPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesShipmentTrackingPage.class);

	/*
	 * @FindBy(how = How.XPATH, using = "//div[@class='mat-select-value']") private
	 * WebElement PRONumberDropDown;
	 * 
	 * @FindBy(how = How.XPATH, using = "//*[@id=\"mat-option-0\"]/span") private
	 * WebElement PRONumber;
	 * 
	 * @FindBy(how = How.ID, using = "inputShipmentTrackingBy") private WebElement
	 * PRONumberField;
	 * 
	 * @FindBy(how = How.XPATH, using = "//button[@type='submit']") private
	 * WebElement SearchButton;
	 */

	// Recent Shipments for Text
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Recent Shipments')])[2]")
	private WebElement weRecentShipmentsText;
	// PRO Number for First Row
	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/tbody/tr[1]/td[1]")
	private WebElement wePRONumForFirstRow;
	// PRO Number Ascending Sign
	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/thead/tr/th[1]/div/div")
	private WebElement wePRONumAscendingSign;
	// Pop Up Shipper Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Shipper')])[2]")
	private WebElement wePopUpShipperRadioButton;
	// Pop Up Consignee Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Consignee')])[3]")
	private WebElement wePopUpConsigneeRadioButton;
	// Pop Up Third Party Payor Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Third Party Payor')])[2]")
	private WebElement wePopUpThirdPPayorRadioButton;
	// Recent Shipments Shipper Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Shipper')])[1]")
	private WebElement weRSShipperRadioButton;
	// Recent Shipments Consignee Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Consignee')])[1]")
	private WebElement weRSConsigneeRadioButton;
	// Recent Shipments Third Party Payor Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Third Party Payor')])[1]")
	private WebElement weRSThirdPPayorRadioButton;
	// Set Viewing Preference
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Set Viewing Preference')]")
	private WebElement weSetViewingPreference;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Tracking')]")
	private WebElement ShipTrackingText;

	@FindBy(how = How.XPATH, using = "//div[@class='mat-select-value']")
	private WebElement DropDown;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'PRO Number')]")
	private WebElement PRONumberDropDown;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-option-0\"]/span")
	private WebElement PRONumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Bill of Lading Number')]")
	private WebElement BOLnumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Load Order Number')]")
	private WebElement LoadOrderNumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Optional EXLA-ID')]")
	private WebElement EXLAID;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-option-0\"]/span")
	private WebElement BOLnumberField;

	@FindBy(how = How.ID, using = "inputShipmentTrackingBy")
	private WebElement PRONumberField;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SearchButton;

//	@FindBy(how = How.XPATH, using = "//*[@id='content']/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[1]/td")
    @FindBy(how=How.XPATH,using="//table[2]/tbody/tr[1]/td[2]/span")
	private WebElement PRO;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'BOL #')]")
	private WebElement BOL;

	@FindBy(how = How.XPATH, using = "//span[@class='mat-option-text'][contains(text(),'Purchase Order Number')]")
	private WebElement PO;

	@FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td")
	private WebElement Status;

	@FindBy(how = How.XPATH, using = "//tbody[@class='ng-tns-c1-1']//tr[3]//td[1]")
	private WebElement PickupDate;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Shipper')]")
	private WebElement Shipper;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Delivery Date')]")
	private WebElement DeliveryDate;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Received By')]")
	private WebElement ReceivedBy;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Consignee')]")
	private WebElement Consignee;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Delivery Receipt')]")
	private WebElement DeliveryReceipt;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Destination Terminal')]")
	private WebElement DestinationTerminal;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Terminal Phone')]")
	private WebElement TerminalPhone;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'Terminal Fax')]")
	private WebElement TerminalFax;

	@FindBy(how = How.XPATH, using = "//span[@class='fal fa-chevron-down']")
	private WebElement ExpandArrow;

	@FindBy(how = How.XPATH, using = "//span[@class='fal fa-chevron-down']")
	private WebElement weClickOnCaretSymbol;

	/*
	 * public void selectPRONumber() {
	 * 
	 * testUtil.init(this);
	 * 
	 * PRONumberDropDown.click(); //
	 * testUtil.selectUsingVisibleText(PRONumber," PRO Number "); PRONumber.click();
	 * 
	 * }
	 * 
	 * public void enterPORNumber(String PRONum) {
	 * 
	 * testUtil.init(this); PRONumberField.sendKeys(PRONum); }
	 * 
	 * public void clickOnSearchButton() {
	 * 
	 * testUtil.init(this); SearchButton.click();
	 * 
	 * }
	 */

	// Verify Recent Shipments for Text Displayed
	public void verifyRecentShipmentsTextDisplayed() {
		logger.info("Verify recent shipments text displayed");
		testUtil.init(this);
		weRecentShipmentsText.isDisplayed();
	}

	// Get PRO Number for First Row
	public String getPRONumForFirstRow() {
		logger.info("Get PRO number for first row");
		testUtil.init(this);
		String PRONumber = wePRONumForFirstRow.getText();
		return PRONumber;
	}

	// Click On PRO Number Ascending Sign
	public void clickOnPRONumAscendingSign() {
		logger.info("Click on PRO number ascending sign");
		testUtil.init(this);
		wePRONumAscendingSign.click();
	}

	// Click On First PRO Number From First Row
	public void clickOnFirstPRONumFFirstRow() {
		logger.info("Click on first PRO number from first row");
		testUtil.init(this);
		wePRONumForFirstRow.click();
	}

	// Click On Pop Up Shipper Radio Button
	public void clickOnPopUpShipperRadioButton() {
		logger.info("Click on pop up shipper radio button");
		testUtil.init(this);
		wePopUpShipperRadioButton.click();
	}

	// Verify Resent Shipments Shipper Radio Button Displayed
	public void verifyShipperRadioButtonDisplayed() {
		logger.info("Verify recent shipments shipper radio button displayed");
		testUtil.init(this);
		weRSShipperRadioButton.isDisplayed();
	}

	// Click On Resent Shipments Shipper Radio Button
	public void ClickOnRSShipperRadioButton() {
		logger.info("Click on recent shipments shipper radio button");
		testUtil.init(this);
		weRSShipperRadioButton.click();
	}

	// Click On Pop Up Consignee Radio Button
	public void clickOnPopUpConsigneeRadioButton() {
		logger.info("Click on pop up consignee radio button");
		testUtil.init(this);
		wePopUpConsigneeRadioButton.click();
	}

	// Verify Resent Shipments Consignee Radio Button Displayed
	public void verifyRSConsigneeRadioButtonDisplayed() {
		logger.info("Verify recent shipments consignee radio button displayed");
		testUtil.init(this);
		weRSConsigneeRadioButton.isDisplayed();
	}

	// Click On Pop Up Third Party Payor Radio Button
	public void clickOnPopUpThirdPPayorRadioButton() {
		logger.info("Click on pop up third party payor radio button");
		testUtil.init(this);
		wePopUpThirdPPayorRadioButton.click();
	}

	// Verify Resent Shipments Third Party Payor Radio Button Displayed
	public void verifyRSThirdPPayorRadioButtonDisplayed() {
		logger.info("Verify resent shipments third party payor radio button displayed");
		testUtil.init(this);
		weRSThirdPPayorRadioButton.isDisplayed();
	}

	
	
	
	// Click On Click On Set Viewing Preference
	public void clickOnSetViewingPreference() {
		logger.info("Click on set viewing preference");
		testUtil.init(this);
		weSetViewingPreference.click();
	}

	// *******
	public void verifyShipTrackingTextDisplayed() {
		logger.info("Verify shipment tracking text displayed");
		testUtil.init(this);
		boolean ele= ShipTrackingText.isDisplayed();
		Assert.assertTrue(ele);
	}

	public void selectPRONumber() {
		logger.info("Enter PRO number  ");
		testUtil.init(this);
		PRONumberDropDown.click();
		PRONumber.click();

	}

	public void selectBOLNumber() {
		logger.info("Select BOL number");
		testUtil.init(this);
		PRONumberDropDown.click();
		BOLnumber.click();
	}

	public void selectPONumber() {
		logger.info("Select PO number");
		testUtil.init(this);
		BOLnumber.click();
		PO.click();
	}

	public void selectLoadOrderNumber() {
		logger.info("Select Load Order number");
		testUtil.init(this);
		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Number')])[1]")).click();
		LoadOrderNumber.click();
	}

	public void selectEXLAID() {
		logger.info("Select Optional EXLA_ID");
		testUtil.init(this);
		LoadOrderNumber.click();
		EXLAID.click();
	}

	public void enterPORNumber(String PRONum) {
		logger.info("Enter PRO number");
		testUtil.init(this);
		PRONumberField.sendKeys(PRONum);
	}

	public void enterBOLNumber(String BOLNum) {
		logger.info("Enter BOL number");
		testUtil.init(this);
		PRONumberField.sendKeys(BOLNum);
	}

	public void clickOnSearchButton() {
		logger.info("Click on search button ");
		testUtil.init(this);
		SearchButton.click();

	}

	public void verifyPRONumber() {
		logger.info("Verify PRO number");
		testUtil.init(this);
		String PROText = PRO.getText();
		System.out.println(PROText);
		assertEquals(PROText, "171-0462591");

	}

	public String verifyPRONum() {
		logger.info("Verify PRO number displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String ProNum = PRO.getText();
		return ProNum;
	}

	public void verifyPONumber() {
		logger.info("Verify PRO number");
		testUtil.init(this);
		String POText = PO.getText();
		System.out.println(POText);
		assertEquals(POText, "");

	}

	public void verifyBOLNumber() {
		logger.info("Verify BOL number");
		testUtil.init(this);
		String BOLText = BOL.getText();
		System.out.println(BOLText);
		assertEquals(BOLText, "");

	}

	public void verifyStatus() {
		logger.info("Verify status");
		testUtil.init(this);
		String StatusText = Status.getText();
		System.out.println(StatusText);
		assertEquals(StatusText, "Delivered");

	}

	public void verifyPickupDate() {
		logger.info("Verify pick up date");
		testUtil.init(this);
		String PickupDateText = PickupDate.getText();
		System.out.println(PickupDateText);
		assertEquals(PickupDateText, "Not picked up");

	}

	public void verifyReceivedBy() {
		logger.info("Verify recieved by");
		testUtil.init(this);
		String ReceivedByText = ReceivedBy.getText();
		System.out.println(ReceivedByText);
		assertEquals(ReceivedByText, "");

	}

	public void verifyConsignee() {
		logger.info("Verify consignee");
		testUtil.init(this);
		String ConsigneeText = Consignee.getText();
		System.out.println(ConsigneeText);
		assertEquals(ConsigneeText, "");

	}

	public void verifyDeliveryDate() {
		logger.info("Verify delivery date");
		testUtil.init(this);
		String DeleiverDateText = DeliveryDate.getText();
		System.out.println(DeleiverDateText);
		assertEquals(DeleiverDateText, "");

	}

	public void verifyDestinationTerminal() {
		logger.info("Verify destination terminal");
		testUtil.init(this);
		String DestinationTerminalText = DestinationTerminal.getText();
		System.out.println(DestinationTerminalText);
		assertEquals(DestinationTerminalText, "");

	}

	public void verifyTerminalPhoneNumber() {
		logger.info("Verify terminal phone number");
		testUtil.init(this);
		String PhoneNumber = TerminalPhone.getText();
		System.out.println(PhoneNumber);
		assertEquals(PhoneNumber, "");

	}

	public void verifyTerminalFaxNumber() {
		logger.info("Verify terminal fax number");
		testUtil.init(this);
		String Fax = TerminalFax.getText();
		System.out.println(Fax);
		assertEquals(Fax, "");

	}

	public void clickOnExpandArrow() {
		logger.info("Click on arrow");
		testUtil.init(this);
		try {
			testUtil.assetWait(null, ExpandArrow, 10, 250, TimeUnit.MILLISECONDS);
			testUtil.clickElementJavascript(ExpandArrow);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExpandArrow.click();
	}

	public void clickOnCaretSymbol() {
		testUtil.init(this);
		logger.info("Click on Caret symbol");
		testUtil.clickElementJavascript(weClickOnCaretSymbol);
		//weClickOnCaretSymbol.click();
	}

	public void validateError() throws InterruptedException {
		logger.info("Validate error message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//*[@id=\"mat-error-0\"]/b")).getText();
		System.out.println(error);// *[@id="mat-error-0"]/span
		Thread.sleep(2000);
		assertTrue(error.contains(error), "required");
	}

	public void verifyTrackingResult() {
		logger.info("Validate tracking result ");
		testUtil.init(this);
		testUtil.setHardWait(2000);
	

		try {
			WebElement result = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/span")); 
			System.out.println(result.getText());
			testUtil.setHardWait(500);

			if (result.isDisplayed()) {
				System.out.println("Shipment Information Displayed");
			} else {
				System.out.println("Shipment Information NOT Displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateInvalidPRONumberError() {
		logger.info("Validate error message ");
		testUtil.init(this);
		String errorMessage = driver
				.findElement(By.xpath("//div[@class='alert alert-danger ng-tns-c2-1 ng-star-inserted']")).getText();
		System.out.println(errorMessage);
		assertTrue(errorMessage.contains("Invalid PRO number."));
	}

	public void validatePROResultNumber(String pro) {
		logger.info("Validate pro result ");
		testUtil.init(this);
		String number = driver
				.findElement(By.xpath(
						"//*[@id=\"content\"]/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[1]"))
				.getText();
		System.out.println(number);
		assertTrue(number.contains(pro));// "070-0551248"
	}

	public void validatePROResultTable(String pro) {
		logger.info("Validate pro result table");
		testUtil.init(this);
		String number = driver
				.findElement(By.xpath(
						"//*[@id=\"content\"]/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[1]"))
				.getText();
		System.out.println(number);
		assertTrue(number.contains(pro));// "070-0551248"
	}

	public void verifyShipmentsTrackingPageDisplayed() {
		testUtil.init(this);
		logger.info("Verify shipments tracking page displayed");
		testUtil.setHardWait(3000);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		String titleName= js.executeScript("return document.title;").toString();
		System.out.println(titleName);
		
	}

	public void clickExpandArrow() {
		logger.info("Click expand arrow.");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"[class='fal fa-chevron-down']\").click();");
		testUtil.setHardWait(2000);
	}

}
