package shipmentTrackingPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class ShipmentTrackingPage extends TestBase {

	private Logger logger = Logger.getLogger(ShipmentTrackingPage.class);

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'LTL Rate Quote')]")
	private WebElement LTLRAteQoute;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='searchBy']//div[@class='mat-select-value']")
	private WebElement DropDown;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'PRO Number')]")
	private WebElement PRONumberDropDown;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-option-0\"]/span")
	private WebElement PRONumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Bill of Lading Number')]")
	private WebElement BOLnumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Purchase Order Number')]")
	private WebElement purchaseOrderNumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Load Order Number')]")
	private WebElement LoadOrderNumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Optional EXLA-ID')]")
	private WebElement EXLAID;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-option-0\"]/span")
	private WebElement BOLnumberField;

	@FindBy(how = How.ID, using = "criteria")
	private WebElement PRONumberField;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SearchButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[1]/td")
	private WebElement PRO;

	@FindBy(how = How.XPATH, using = "//th[contains(text(),'BOL #')]")
	private WebElement BOL;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Purchase Order Number')]")
	private WebElement PO;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Interline PRO Number')]")
	private WebElement InterlinePRO;

	@FindBy(how = How.XPATH, using = "//*[@id='content']/div/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td")
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

	@FindBy(how = How.XPATH, using = "//span[@class='fal fa-chevron-up']")
	private WebElement weClickOnCaretSymbol;

	//@FindBy(how = How.XPATH, using = "//thead/tr/th[contains(text(),'PRO')]")
	@FindBy(how = How.XPATH, using = "//*[text()='PRO Number']")
	private WebElement headerPRONumber;

	@FindBy(how = How.XPATH, using = "//span[text()='Pickup Date']")
	private WebElement headerPickupDate;

	@FindBy(how = How.XPATH, using = "//span[text()='BOL Number']")
    private WebElement headerBOLNumber;


	@FindBy(how = How.XPATH, using = "//thead/tr/th/span[contains(text(),'PO Number')]")
	private WebElement headerPONumber;

	@FindBy(how = How.XPATH, using = "//button[text()='Status']")
	private WebElement headerStatus;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Estes PRO Number']]/td")
	private List<WebElement> valuePRONumber;

	@FindBy(how = How.XPATH, using = "//tr[th/span[text()='Shipper Bill of Lading Number']]/td")
	private List<WebElement> valueBOLNumber;

	@FindBy(how = How.XPATH, using = "//tr[th/span[text()='Purchase Order Number']]/td")
	private List<WebElement> valuePONumber;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Status']]/td")
	private List<WebElement> valueStatus;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='W&R Certificate']]/td")
	private List<WebElement> valueWRCertificate;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Pieces']]/td")
	private List<WebElement> valuePieces;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Weight (lbs.)']]/td")
	private List<WebElement> valueWeight;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Pickup Date']]/td")
	private List<WebElement> valuePickupDate;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Shipper']]/td")
	@FindBy(how = How.XPATH, using = "//tr[th[contains(text(),'Shipper')]]/td")
	private List<WebElement> valueShipper;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Expected Delivery Date']]/td")
	private List<WebElement> valueExpectedDeliveryDate;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Estimated Delivery Date']]/td")
	private List<WebElement> valueEstimatedDeliveryDate;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Delivery Date']]/td")
	private List<WebElement> valueDeliveryDate;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Delivery Time']]/td")
	private List<WebElement> valueDeliveryTime;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Received By']]/td")
	private List<WebElement> valueReceivedBy;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Consignee']]/td")
	@FindBy(how = How.XPATH, using ="//tr[th[contains(text(),'Consignee')]]/td")
	private List<WebElement> valueConsignee;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Delivery Receipt']]/td")
	private List<WebElement> valueDeliveryReceipt;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='First Delivery Date']]/td")
	private List<WebElement> valueFirstDeliveryDate;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Appointment Date']]/td")
	private List<WebElement> valueAppointmentDate;

	@FindBy(how = How.XPATH, using = "//tr[th[text()=' Appointment Time ']]/td")
	private List<WebElement> valueDeliveryAppointmentTime ;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Appointment Status']]/td")
	private List<WebElement> valueAppointmentStatus;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Destination Terminal']]/td")
	@FindBy(how = How.XPATH, using = "//h6[contains(text(),'Destination Terminal')]")
	private List<WebElement> valueDestinationTerminal;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Terminal Phone']]/td")
	@FindBy(how = How.XPATH, using ="//tr[th[text()='Telephone']]/td")
	private List<WebElement> valueTerminalPhone;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Terminal Fax']]/td")
	private List<WebElement> valueTerminalFax;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Freight Charges']]/td")
	private List<WebElement> valueFreightCharges;

	@FindBy(how = How.XPATH, using = "//tr[th[contains(text(),'Questions?')]]/td")
	private List<WebElement> valueQuestions;

	@FindBy(how = How.XPATH, using = "//a[text()='Request Additional Information']")
	private WebElement requestAddInfo;

	@FindBy(how = How.XPATH, using = "//a[text()='My Estes']")
	private WebElement myEstesLink;

	@FindBy(how = How.XPATH, using = "//h1/span")
	private WebElement title;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Request Additional Info')]")
	private WebElement requestAddInfoTitle;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='name']")
	private WebElement name;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='emailAddress']")
	private WebElement email;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='phoneNumber']")
	private WebElement phoneNo;

	@FindBy(how = How.XPATH, using = "//mat-select[@formcontrolname='problem']")
	private WebElement problem;

	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'Tracking Help')]")
	private WebElement trackingHelp;

	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'Other')]")
	private WebElement other;

	@FindBy(how = How.XPATH, using = "//textarea[@formcontrolname='description']")
	private WebElement comments;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Submit')]")
	private WebElement btnSubmit;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'ng-star-inserted')]/span")
	private WebElement successMessage;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/app-root/div/mat-card/mat-card-header/button")
	private WebElement expandAll;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Clear')]")
	private WebElement clear;

	//@FindBy(how = How.XPATH, using = "//h1/span[contains(text(),'Shipment Tracking')]")
	@FindBy(how = How.XPATH, using = "//h1/span[contains(text(),'Tracking')]")

	private WebElement shipmentPage;

	@FindBy(how = How.XPATH, using = "(//a[contains(text(),'Contact us about this shipment')])[1]")
	private WebElement contactUsLink;

	@FindBy(id="criteria")
	private WebElement criteriaSearch;

	@FindBy(css="tbody>tr>td[class*='cdk-column-pro mat-column-pro']")
	private WebElement trackingResultProNumber;

	@FindBy(xpath = "//a[contains(text(),'Request Additional Information')]")
	private WebElement requestAdditionalInfoLink;

	@FindBy(xpath = "(//a[contains(text(),'View')])[2]")
	private WebElement viewBOLImageLink;

	@FindBy(css=".outfordelivery > .ng-tns-c2-1")
	private WebElement statusBanner;
	// bar that shows shipment status ex. "En route to Estes facility at Edison, NJ (006)"

	@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[1]/td[5]")
	private WebElement estimatedDelivery;

	//@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[9]/td")
	@FindBy(xpath="//app-home//mat-card-content/table[2]//tr[2]/td/div/div/div/div[6]/table//tr[3]/td")
	private WebElement estimatedDeliveryDate;


	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement MyEstes;

	@FindBy(partialLinkText = "Login")
	private WebElement login;

	@FindBy(css=".fa-chevron-down")
	private WebElement shipmentStatusCaret;

	@FindBy(css=".fa-chevron-up")
	private WebElement shipmentStatusCaretClose;

	@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/mat-accordion")
	private WebElement shipmentHistoryCaret2;

	@FindBy(xpath="//mat-expansion-panel-header[@id='mat-expansion-panel-header-0']/span")
	private WebElement shipmentHistoryCaret;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='EBL']]/td")
	private List<WebElement> valueEBL;

	@FindBy(how = How.XPATH, using = "//tr[th[text()='Transit Days']]/td")
	private List<WebElement> valueTransitDays;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Time Critical Service']]/td")
	@FindBy(how = How.XPATH, using ="//tr[th[text()='Time Critical Guaranteed Service Requested']]/td")
	private List<WebElement> valueTimeCritical;

	@FindBy(xpath = "//span[contains(text(),'Show only my shipments')]")
	private WebElement myShipmentsToggle;

	@FindBy(xpath = "//mat-slide-toggle[@id='partyToCheckToggle']")
	private WebElement toggleSwitch;

	@FindBy(xpath = "//*[@id='partyToCheckToggle']/label/div/div")
	private WebElement toggleColor;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Pickup Request Number')]")
	private WebElement pickupRequest;


	//@FindBy(how = How.XPATH, using ="//tr[th[text()='Time Critical Service']]/td")
	@FindBy(how = How.XPATH, using ="//td[contains(text(),'Time Critical Shipment Due by 10 am')]")
	private List<WebElement> valueTimeCritical10AM;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Time Critical Shipment Due by 12 pm']]/td")
	@FindBy(how = How.XPATH, using ="//td[contains(text(),'Time Critical Shipment Due by 12 pm')]")
	private List<WebElement> valueTimeCritical12PM;

	//@FindBy(how = How.XPATH, using = "//tr[th[text()='Time Critical Shipment Due by 5 pm']]/td")
	@FindBy(how = How.XPATH, using ="//td[contains(text(),'Time Critical Shipment Due by 5 pm')]")
	private List<WebElement> valueTimeCritical5PM;

	@FindBy(css=".d-inline")
	private WebElement proNumberPreviewColumn;

	@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/thead/tr/th[3]/div/button/span")
	private WebElement pickupDatePreviewColumn;

	@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/thead/tr/th[4]/div/button/span")
	private WebElement bolNumberPreviewColumn;

	@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/thead/tr/th[5]/div/button/span")
	private WebElement estimatedDeliveryPreviewColumn;

	@FindBy(xpath="//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/thead/tr/th[6]/div/button")
	private WebElement statusPreviewColumn;

	@FindBy(xpath="//*[@id='searchBy']/div")
	private WebElement searchBy;
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Collapse All ')]")
	private WebElement collapseBtn;

	@FindBy(xpath="//div[3]/div/ngb-alert/div")
	private WebElement errorMsg;

	@FindBy(xpath="//span[contains(text(),'×')]")
	private WebElement clickX;

	@FindBy(xpath ="//div[contains(text(),'Are you trying to')]//a['track a pickup request?']")
	private WebElement trackPickupReqLink;

	@FindBy(xpath = "//div[contains(text(),'Are you trying to')]//span/a['others']")
	private WebElement othersLink;


	@FindBy(xpath = "//form//button[@aria-label='Close']")
	private WebElement closeIconInErrMsg;


	/*************************METHODS********************/

	public void clickOnLTLRateQuoteLink() {
		logger.info("click on LTL Rate Qoute");
		testUtil.init(this);
		testUtil.clickElementJavascript(LTLRAteQoute);

	}

	public void selectPRONumber() throws InterruptedException {
		logger.info("select PRO number");
		testUtil.init(this);
		//testUtil.setHardWait(2000);
		testUtil.WaitForTheElementClickable(PRONumberDropDown);
		testUtil.clickElementJavascript(PRONumberDropDown);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(PRONumber);
		testUtil.setHardWait(1000);
	}

	public void selectBOLNumber() throws InterruptedException {
		logger.info("select BOL number");
		testUtil.init(this);
		//DropDown.click();
		//BOLnumber.click();
        testUtil.WaitForTheElementClickable(PRONumberDropDown); //Modified
        testUtil.clickElementJavascript(PRONumberDropDown);
        testUtil.setHardWait(1000);
        testUtil.clickElementJavascript(BOLnumber);
        testUtil.setHardWait(2000);
	}

	public void selectPONumber() throws InterruptedException {
		logger.info("Select PO number");
		testUtil.init(this);
		testUtil.setHardWait(1000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();",DropDown );
		 testUtil.clickElementJavascript(DropDown);

         testUtil.setHardWait(500);
         testUtil.clickElementJavascript(PO);
         testUtil.setHardWait(1000);
	}

	public void selectInterlinePRONumber() throws InterruptedException {
		logger.info("Select Interline PO number");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", DropDown);
		testUtil.clickElementJavascript(DropDown);
		testUtil.setHardWait(1000);
		InterlinePRO.click();
		testUtil.setHardWait(1000);

	}

	public void selectLoadOrderNumber() throws InterruptedException {
		logger.info("Select Load Order number");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", DropDown);
		testUtil.clickElementJavascript(DropDown);
		testUtil.setHardWait(1000);
		LoadOrderNumber.click();
		testUtil.setHardWait(1000);
	}

	public void selectEXLAID() throws InterruptedException {
		logger.info("Select Optional EXLA_ID");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", DropDown);
		testUtil.clickElementJavascript(DropDown);
		testUtil.setHardWait(1000);
		EXLAID.click();
		testUtil.setHardWait(1000);
	}

	public void enterPORNumber(String PRONum) {
		logger.info("Enter PRO number ");
		testUtil.init(this);
		PRONumberField.clear();
		testUtil.setHardWait(500);
		PRONumberField.sendKeys(PRONum);
	}

	public void enterMultiplePRONumber(String... proNumbers) {

		StringBuilder proNumber = new StringBuilder();
		logger.info("Enter multiple PRO number ");
		testUtil.init(this);

		for(int i=0;i<proNumbers.length;i++)
		{
			proNumber = proNumber.append(proNumbers[i]).append((i==proNumbers.length-1)?"":"\n");
		}
		PRONumberField.sendKeys(proNumber);
	}

	public void enterBOLNumber(String BOLNum) {
		logger.info("Enter BOL number ");
		testUtil.init(this);
		PRONumberField.sendKeys(BOLNum);
	}

	public void clickOnSearchButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click on search button ");
		testUtil.setExplicitWait(SearchButton, 120);
		testUtil.clickElementJavascript(SearchButton);
		testUtil.setHardWait(5000);
		//testUtil.fluentWait(By.xpath("(//th[@role='columnheader'])[2]"), 100, 5, "PRO Number");

	}

	public void clickOnSearchBtn() throws InterruptedException {
		testUtil.init(this);
		testUtil.setHardWait(3000);
		testUtil.WaitForTheElementClickable(SearchButton);
		testUtil.setHardWait(1000);
		logger.info("Click on search button ");
		testUtil.clickElementJavascript(SearchButton);
		testUtil.setHardWait(1000);
	}

	public void validateErrorMessage() throws InterruptedException {
		logger.info("Validate error message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//*[@class='ng-star-inserted']")).getText();
	    System.out.println(error);
		Thread.sleep(2000);
		assertTrue(error.contains(error), "ERROR");
	}

	public void verifyPRONumber() {
		logger.info("Verify PRO number ");
		testUtil.init(this);
		String PROText = PRO.getText();
		System.out.println(PROText);
		assertEquals(PROText, "171-0462591");

	}

	public void verifyPONumber() {
		logger.info("verify PO number ");
		testUtil.init(this);
		String POText = PO.getText();
		System.out.println(POText);
		assertEquals(POText, "");

	}

	public void verifyBOLNumber() {
		logger.info("verify BOL number ");
		testUtil.init(this);
		String BOLText = BOL.getText();
		System.out.println(BOLText);
		assertEquals(BOLText, "");

	}


	public void verifyInTransitStatus() {
		logger.info("verify status ");
		testUtil.init(this);
		WebElement inTransit = driver.findElement(By.xpath("//*[@id=\"main\"]/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[1]/td[6]/div/span"));
		String statusText = inTransit.getText();
		System.out.println(statusText);
		assertEquals(statusText, "In Transit");
	}


	public void verifyPickupDate() {
		logger.info("verify puckup date ");
		testUtil.init(this);
		String PickupDateText = PickupDate.getText();
		System.out.println(PickupDateText);
		assertEquals(PickupDateText, "Not picked up");

	}

	public void verifyReceivedBy() {
		logger.info("verify Received By ");
		testUtil.init(this);
		String ReceivedByText = ReceivedBy.getText();
		System.out.println(ReceivedByText);
		assertEquals(ReceivedByText, "");

	}

	public void verifyConsignee() {
		logger.info("verify consignee");
		testUtil.init(this);
		String ConsigneeText = Consignee.getText();
		System.out.println(ConsigneeText);
		assertEquals(ConsigneeText, "");

	}

	public void verifyDeliveryDate() {
		logger.info("verify delivery date ");
		testUtil.init(this);
		String DeleiverDateText = DeliveryDate.getText();
		System.out.println(DeleiverDateText);
		assertEquals(DeleiverDateText, "");

	}

	public void verifyDestinationTerminal() {
		logger.info("Verify destination terminal ");
		testUtil.init(this);
		//testUtil.setExplicitWait(DestinationTerminal, 20);
		testUtil.setHardWait(2000);

		String DestinationTerminalText = DestinationTerminal.getText();
        System.out.println(DestinationTerminalText);
        assertEquals(DestinationTerminalText, "Destination Terminal");

	}

	public void verifyTerminalPhoneNumber() {
		logger.info("verify Terminal Phone number ");
		testUtil.init(this);
		String PhoneNumber = TerminalPhone.getText();
		System.out.println(PhoneNumber);
		assertEquals(PhoneNumber, "");

	}

	public void verifyTerminalFaxNumber() {
		logger.info("verify Terminal Fax number ");
		testUtil.init(this);
		String Fax = TerminalFax.getText();
		System.out.println(Fax);
		assertEquals(Fax, "");

	}

	public void clickOnExpandArrow() {
		logger.info("Click on arrow");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ExpandArrow);
		testUtil.setExplicitWait(ExpandArrow, 20);
		testUtil.clickElementJavascript(ExpandArrow);

	}

	public void clickOnCaretSymbol() {
		testUtil.init(this);
		logger.info("Click on Caret symbol");
		weClickOnCaretSymbol.click();
	}

	public void validateError() throws InterruptedException {
		logger.info("Validate error message");
		testUtil.init(this);
		String error = driver
				.findElement(By.xpath("//*[@id='mat-error-0'][contains(text(),'This field is required.')]"))
				.getText();
		System.out.println(error);
		testUtil.setHardWait(2000);
		Assert.assertEquals(error, "This field is required.");
	}

	public void verifyTrackingResult() {
		logger.info("Validate tracking result ");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData("//table[@class='table table-bordered table-striped table-hover table-sm detail']");
		//testUtil.verifyAndPrintWebTableData(tableName);
		/*
		 * String result=driver.findElement(By.
		 * xpath("//table[@class='table table-bordered table-striped table-hover table-sm detail']"
		 * )).getText(); System.out.println(result); assertEquals(result, "required");
		 */
	}

	public void validateInvalidPRONumberError(String proNumber) {
		logger.info("Validate error message ");
		testUtil.init(this);
		String errorMessage = driver.findElement(By.xpath("//tr[td[span[text()='"+proNumber+"']]]/td[7]/span")).getText();
		System.out.println(errorMessage);
		assertEquals(errorMessage, "ERROR: Invalid PRO number.");
	}

	public void validateValidPRONumber(String proNumber) {
		logger.info("Validate Valid PRO message ");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",ExpandArrow );
		//testUtil.clickElementJavascript(ExpandArrow);
		String message = driver.findElement(By.xpath("//tr[td[span[text()='"+proNumber+"']]]/td[7]/span")).getText();
		System.out.println(message);
		assertNotEquals(message, "ERROR: Invalid PRO number.");
	}


	public void validatePROResultNumber(String pro) {
		logger.info("validate pro result ");
		testUtil.init(this);
		String number = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[1]")).getText();
		System.out.println(number);
		assertTrue(number.contains(pro));//"070-0551248"
	}

	public void validatePROResultTable(String tableName) {
		logger.info("Validate pro result table");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData(tableName);
		//String number = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/app-root/div/mat-card/mat-card-content/table/tbody/tr[1]")).getText();
		//System.out.println(number);
		//assertTrue(number.contains(pro));//"070-0551248"
	}

	public void verifyHeaderPRONumber() {
		testUtil.init(this);
		//testUtil.setHardWait(3000);
		logger.info("Verify Header PRO Number");
		testUtil.assetWait(null, headerPRONumber, 10, 200, TimeUnit.MILLISECONDS);
		Assert.assertEquals(headerPRONumber.isDisplayed(), true);
	}

	public void verifyHeaderPickupDate() {
		testUtil.init(this);
		logger.info("Verify Header Pickup Date");
		testUtil.setExplicitWait(headerPickupDate, 60);
		Assert.assertEquals(headerPickupDate.isDisplayed(), true);
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

	public void verifyValuePRONumber() {
		testUtil.init(this);
		logger.info("Verify PRO value");
		if(valuePRONumber.size()>0)
			Assert.assertTrue(valuePRONumber.size()>0);
		Assert.assertFalse(valuePRONumber.isEmpty());
	}

	public void verifyValueBOLNumber() {
		testUtil.init(this);
		logger.info("Verify BOL Value");
		if(valueBOLNumber.size()>0)
			Assert.assertTrue(valueBOLNumber.size()>0);
		Assert.assertFalse(valueBOLNumber.isEmpty());
	}

	public void verifyValuePONumber() {
		testUtil.init(this);
		logger.info("Verify PO Value");
		if(valuePONumber.size()>0)
			Assert.assertTrue(valuePONumber.size()>0);
		Assert.assertFalse(valuePONumber.isEmpty());
	}

	public void verifyValueStatus() {
		testUtil.init(this);
		logger.info("Verify Status");
		if(valueStatus.size()>0)
			Assert.assertTrue(valueStatus.size()>0);
	}

	public void verifyValueWRCertificate() {
		testUtil.init(this);
		logger.info("Verify WRCertificate");
		if(valueWRCertificate.size()>0)
			Assert.assertTrue(valueWRCertificate.size()>0);
	}

	public void verifyValuePieces() {
		testUtil.init(this);
		logger.info("Verify Pieces");
		if(valuePieces.size()>0)
			Assert.assertTrue(valuePieces.size()>0);
		Assert.assertFalse(valuePieces.isEmpty());
	}

	public void verifyValueWeight() {
		testUtil.init(this);
		logger.info("Verify Weight");
		if(valueWeight.size()>0)
			Assert.assertTrue(valueWeight.size()>0);
		Assert.assertFalse(valueWeight.isEmpty());
	}

	public void verifyValuePickupDate() {
		testUtil.init(this);
		logger.info("Verify Pickup Date");
		if(valuePickupDate.size()>0)
			Assert.assertTrue(valuePickupDate.size()>0);
		Assert.assertFalse(valuePickupDate.isEmpty());
	}

	public void verifyValueShipper() {
		testUtil.init(this);
		logger.info("Verify Shipper");
		if(valueShipper.size()>0)
			Assert.assertTrue(valueShipper.size()>0);
		Assert.assertFalse(valueShipper.isEmpty());
	}

	public void verifyValueExpectedDeliveryDate() {
		testUtil.init(this);
		logger.info("Verify Expected Delivery Date");
		if(valueExpectedDeliveryDate.size()>0)
			Assert.assertTrue(valueExpectedDeliveryDate.size()>0);
		Assert.assertFalse(valueEstimatedDeliveryDate.isEmpty());
	}

	public void verifyValueEstimatedDeliveryDate() {
		testUtil.init(this);
		logger.info("Verify Estimated Delivery Date");
		if(valueEstimatedDeliveryDate.size()>0)
			Assert.assertTrue(valueEstimatedDeliveryDate.size()>0);
		Assert.assertFalse(valueEstimatedDeliveryDate.isEmpty());
	}

	public void verifyValueDeliveryDate() {
		testUtil.init(this);
		logger.info("Verify Delivery Date");
		if(valueDeliveryDate.size()>0)
			Assert.assertTrue(valueDeliveryDate.size()>0);
	}

	public void verifyValueDeliveryTime() {
		testUtil.init(this);
		logger.info("Verify Delivery Time");
		if(valueDeliveryTime.size()>0)
			Assert.assertTrue(valueDeliveryTime.size()>0);
	}

	public void verifyValueReceivedBy() {
		testUtil.init(this);
		logger.info("Verify Received By");
		if(valueReceivedBy.size()>0)
			Assert.assertTrue(valueReceivedBy.size()>0);
	}

	public void verifyValueConsignee() {
		testUtil.init(this);
		logger.info("Verify Consignee");
		if(valueConsignee.size()>0)
			Assert.assertTrue(valueConsignee.size()>0);
		Assert.assertFalse(valueConsignee.isEmpty());
	}

	public void verifyValueDeliveryReceipt() {
		testUtil.init(this);
		logger.info("Verify Delivery Receipt");
		if(valueDeliveryReceipt.size()>0)
			Assert.assertTrue(valueDeliveryReceipt.size()>0);
	}

	public void verifyValueFirstDeliveryDate() {
		testUtil.init(this);
		logger.info("Verify First Delivery Date");
		if(valueFirstDeliveryDate.size()>0)
			Assert.assertTrue(valueFirstDeliveryDate.size()>0);
	}

	public void verifyValueAppointmentDate() {
		testUtil.init(this);
		logger.info("Verify Appointment Date");
		if(valueDeliveryReceipt.size()>0)
			Assert.assertTrue(valueDeliveryReceipt.size()>0);
	}

	public void verifyValueAppointmentTime() {
		testUtil.init(this);
		logger.info("Verify Appointment Time");
		if(valueDeliveryReceipt.size()>0)
			Assert.assertTrue(valueDeliveryReceipt.size()>0);
	}

	public void verifyValueAppointmentStatus() {
		testUtil.init(this);
		logger.info("Verify Appointment Status");
		if(valueDeliveryReceipt.size()>0)
			Assert.assertTrue(valueDeliveryReceipt.size()>0);
	}

	public void verifyValueDestinationTerminal() {
		testUtil.init(this);
		logger.info("Verify Destination Terminal");
		if(valueDestinationTerminal.size()>0)
			Assert.assertTrue(valueDestinationTerminal.size()>0);
		Assert.assertFalse(valueDestinationTerminal.isEmpty());
		testUtil.setHardWait(2000);
	}

	public void verifyValueTerminalPhone() {
		testUtil.init(this);
		logger.info("Verify Terminal Phone");
		if(valueTerminalPhone.size()>0)
			Assert.assertTrue(valueTerminalPhone.size()>0);
		Assert.assertFalse(valueTerminalPhone.isEmpty());
	}

	public void verifyValueTerminalFax() {
		testUtil.init(this);
		logger.info("Verify Terminal Fax");
		if(valueTerminalFax.size()>0)
			Assert.assertTrue(valueTerminalFax.size()>0);

	}

	public void verifyValueFreightCharges() {
		testUtil.init(this);
		logger.info("Verify Freight Charges");
		if(valueFreightCharges.size()>0)
			Assert.assertTrue(valueFreightCharges.size()>0);
	}

	public void clickOnRequestAddInfo() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click on Request Additional Information button ");
		testUtil.setHardWait(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();",requestAddInfo );
		testUtil.clickElementJavascript(requestAddInfo);
		testUtil.setHardWait(2000);

	}

	public void verifyRequestAddInfoPage() {
		testUtil.init(this);
		logger.info("Verify Request Additional Information page");
		String text = title.getText().trim();
		Assert.assertEquals(text,"Request Additional Info");
	}

	public void verifySearchFieldPRONumber(String proNumber) throws InterruptedException {
		testUtil.init(this);
		Thread.sleep(5000);
		logger.info("Verify Search Field PRO Number");
		JavascriptExecutor js = (JavascriptExecutor)driver;

		String text = (String) js.executeScript("return document.getElementById('criteria').value");
		Assert.assertEquals(text,proNumber);
	}

	public void enterName(String infoName) {
		logger.info("enter Name ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		name.clear();
		name.click();
		name.sendKeys(infoName);
	}

	public void enterEmail(String infoEmail) {
		logger.info("enter Email ");
		testUtil.init(this);
		email.clear();
		email.click();
		testUtil.setHardWait(1000);
		email.sendKeys(infoEmail);
	}

	public void enterPhoneNumber(String infoPhone) {
		logger.info("enter Phone Number ");
		testUtil.init(this);
		phoneNo.clear();
		phoneNo.click();
		testUtil.setHardWait(500);
		phoneNo.sendKeys(infoPhone);
	}

	public void selectTrackingHelp() throws InterruptedException {
		logger.info("select Tracking Help");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		problem.click();
		testUtil.setHardWait(1000);
		trackingHelp.click();

	}

	public void selectOther() throws InterruptedException {
		logger.info("select Other");
		testUtil.init(this);
		problem.click();
		other.click();
		Thread.sleep(1000);
	}

	public void enterComments(String infoComments) {
		logger.info("enter Comments ");
		testUtil.init(this);
		comments.click();
		comments.sendKeys(infoComments);
	}

	public void clickSubmit() throws InterruptedException {
		logger.info("Click Submit button");
		testUtil.init(this);
		testUtil.setExplicitWait(btnSubmit, 120);
		btnSubmit.click();
		testUtil.setHardWait(1000);
	}

	public void verifySuccessMessage(String expected) {
		testUtil.init(this);
		logger.info("Verify Success Message");
		testUtil.setExplicitWait(successMessage, 60);
		Assert.assertEquals(successMessage.getText().trim(),expected);
	}

	public void verifyName() {
		testUtil.init(this);
		logger.info("Verify name is auto populated");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		testUtil.setHardWait(1000);
		String name = (String) js.executeScript("return document.getElementById('mat-input-0').value");
		Assert.assertEquals((!name.equals("") && name!=null),true);
	}

	public void verifyEmail() {
		testUtil.init(this);
		logger.info("Verify email is auto populated");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String name = (String) js.executeScript("return document.getElementById('mat-input-1').value");
		Assert.assertEquals((!name.equals("") && name!=null),true);
	}

	public void verifyPhoneNo() {
		testUtil.init(this);
		logger.info("Verify phone number is auto populated");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String name = (String) js.executeScript("return document.getElementById('mat-input-2').value");
		Assert.assertEquals((!name.equals("") && name!=null),true);
	}

	public void verifyPRONo() {
		testUtil.init(this);
		logger.info("Verify PRO number is auto populated");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String name = (String) js.executeScript("return document.getElementById('mat-input-5').value");
		Assert.assertEquals((!name.equals("") && name!=null),true);
	}

	public void verifyUsername() {
		testUtil.init(this);
		logger.info("Verify username is auto populated");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String name = (String) js.executeScript("return document.getElementById('mat-input-0').value");
		Assert.assertEquals((!name.equals("") && name!=null),true);
	}

	public void clickExpandAll() throws InterruptedException {
		logger.info("Click Expand All button");
		testUtil.init(this);
	    testUtil.setHardWait(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		testUtil.setHardWait(3000);
		// testUtil.clickElementJavascript(expandAll);
		expandAll.click();

	}

	public void clickClear() throws InterruptedException {
		logger.info("Click Clear button");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();",clear );
		 testUtil.clickElementJavascript(clear);
	//	clear.click();
		testUtil.setHardWait(1000);
	}

	public void verifyRequestAddInfoLink() {
		testUtil.init(this);
		testUtil.setHardWait(1000);
		logger.info("Verify Request Additional Information link");
		Assert.assertEquals(requestAddInfo.isDisplayed(), true);
	}

	public void clickOnMyEstesLink() throws InterruptedException {
		testUtil.init(this);
		logger.info("clicking on My Estes Link ");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();",myEstesLink );
		testUtil.clickElementJavascript(myEstesLink);
		//requestAddInfo.click();
		Thread.sleep(1000);
	}

	public void verifyMyEstesLoginPage() {
		testUtil.init(this);
		logger.info("Verify My Estes Login page");
		String text = title.getText().trim();
		Assert.assertEquals(text,"My Estes Login");
	}

	public void verifyRequestaddinfo() {
		testUtil.init(this);
		logger.info("Verify Request additional info page");
		String text = requestAddInfoTitle.getText().trim();
		Assert.assertEquals(text,"Request Additional Info");
	}

	public void deleteAutoPopulatedValues() {
		logger.info("Delete Auto-populated values in Request additional info section");
		testUtil.init(this);

		name.click();



		int val = name.getAttribute("value").length();
		for(int i = 0 ; i <= val; i++) {
			testUtil.setHardWait(1000);
			name.sendKeys(Keys.BACK_SPACE);
		}
		name.sendKeys(Keys.TAB);

		email.click();
		val = email.getAttribute("value").length();
		for(int i = 1 ; i <= val; i++) {

			testUtil.setHardWait(1000);
			email.sendKeys(Keys.BACK_SPACE);
		}
		email.sendKeys(Keys.TAB);

		phoneNo.click();
		val = phoneNo.getAttribute("value").length();
		for(int i = 1 ; i <= val; i++) {
			testUtil.setHardWait(1000);
			phoneNo.sendKeys(Keys.BACK_SPACE);
		}
		phoneNo.sendKeys(Keys.TAB);
	}

	public void verifyErrorMsgForReqFields( ) {
		logger.info("Verify required fields in Request additional information section");
		testUtil.init(this);
		//Name
		Assert.assertEquals(name.getAttribute("aria-invalid"), "true");
		//Email
		Assert.assertEquals(email.getAttribute("aria-invalid"), "true");
		//Phone number
		Assert.assertEquals(phoneNo.getAttribute("aria-invalid"), "true");
		//Comments
		Assert.assertEquals(comments.getAttribute("aria-invalid"), "true");
	}
	public void verifyShippingTrackingPage() {
		testUtil.init(this);
		logger.info("Verify shipping tracking page is displayed");
		testUtil.setHardWait(1000);
		//testUtil.setExplicitWait(shipmentPage, 60);-->commented and increased the time in below line
		testUtil.setExplicitWait(shipmentPage, 100); 
		shipmentPage.isDisplayed();
	}

	public void verifyMyEstesLink() {
		testUtil.init(this);
		logger.info("Verify my estes link");
		Assert.assertEquals(myEstesLink.isDisplayed(), true);
	}

	public void verifyContactUsLink() {
		testUtil.init(this);
		logger.info("Verify contact us about this shipment link");
		testUtil.setHardWait(2000);
		Assert.assertEquals(contactUsLink.isDisplayed(), true);
	}


	public void validateErrorMessageForReqFields() {
		testUtil.init(this);
		logger.info("Verify all required fields");

	/*	String error = driver.findElement(By.id("mat-error-0")).getText();
		System.out.println("The error message is : " + error);
		Assert.assertEquals(error, "This field is required.");

		String error1 = driver.findElement(By.id("mat-error-1")).getText();
		Assert.assertEquals(error1, "This field is required.");*/

		String error2 = driver.findElement(By.id("mat-error-2")).getText();
		Assert.assertEquals(error2, "This field is required.");

		String error3 = driver.findElement(By.id("mat-error-4")).getText();
		Assert.assertEquals(error3, "This field is required.");
		String error4 = driver.findElement(By.id("mat-error-3")).getText();
		Assert.assertEquals(error4, "This field is required.");

	}
	public void enterPRONumberInFormat(String proNo) {
		logger.info("Enter Pro number in 10 digit format");
		testUtil.init(this);
		String proNumber;
		for(int i=0;i<2;i++) {
			if(proNo.length()<10) {
				proNumber = "0"+proNo;
				proNo = proNumber;
			}
		} criteriaSearch.sendKeys(proNo);
	}

	public void clickOnFirstRecordFromTrackingResultTable() {
		logger.info("Click on First Record from the tracking result table");
		testUtil.init(this);
		testUtil.setExplicitWait(trackingResultProNumber, 20);
		//testUtil.setHardWait(3000);
		testUtil.clickElementJavascript(trackingResultProNumber);
	}

	public void clickOnRequestAdditionalInformationLink() {
		logger.info("Click on Request Additional Information link");
		testUtil.init(this);
		testUtil.setExplicitWait(requestAdditionalInfoLink, 20);
		testUtil.clickElementJavascript(requestAdditionalInfoLink);
	}


	public ArrayList<String> captureResultTable() {
		logger.info("Verify Table");
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath("//tbody//tr[*]/td[2]"));
		System.out.println(rowCount.size());

		ArrayList<String> proNumber = new ArrayList<String>();

		for (int i = 0; i < rowCount.size(); i++) {
			proNumber.add(rowCount.get(i).getText());
		}

		return proNumber;
	}

	public void verifyColHeader() {
		logger.info("Verify column header");
		testUtil.init(this);
		testUtil.setHardWait(4000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,300)");
        testUtil.setHardWait(4000);

		WebElement proNumber=driver.findElement(By.cssSelector(".mat-header-cell:nth-child(2)"));
		WebElement pickUpDate=driver.findElement(By.cssSelector(".mat-header-cell:nth-child(3)"));
		WebElement bol=driver.findElement(By.cssSelector(".mat-header-cell:nth-child(4)"));
		WebElement po=driver.findElement(By.cssSelector(".mat-header-cell:nth-child(5)"));
		//WebElement status=driver.findElement(By.cssSelector(".mat-header-cell:nth-child(6)"));
		setWait(proNumber,pickUpDate,bol,po);
		//setWait(status);
		Assert.assertEquals("PRO Number", proNumber.getText());
		Assert.assertEquals("Pickup Date", pickUpDate.getText());
		Assert.assertEquals("BOL Number", bol.getText());
		Assert.assertEquals("PO Number", po.getText());
        Assert.assertEquals(headerStatus.isDisplayed(),true);


	}

	public void verifyTrackingResultFields() {
		logger.info("Verify Tracking Results fields");
		testUtil.init(this);

		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'PRO Number')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'BOL Number')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'PO Number')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Status')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Pieces')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Weight')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Shipper')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Consignee')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Delivery Receipt')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Destination Terminal')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Questions')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Terminal Phone')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Terminal Fax')]")).isDisplayed());
	}

	public void enterMultipleBOLNumber(String... bolNumbers) {
        StringBuilder bolNumber = new StringBuilder();
        logger.info("Enter multiple BOL number ");
        testUtil.init(this);
        for(int i=0;i<bolNumbers.length;i++)
        {
               bolNumber = bolNumber.append(bolNumbers[i]).append((i==bolNumbers.length-1)?"":"\n");
        }
        PRONumberField.sendKeys(bolNumber);
  }

  public void verifyColHeaderForUnAuthicatedUser() {
        logger.info("Verify column header");
        testUtil.init(this);
         testUtil.setExplicitWait(driver.findElement(By.xpath("//thead")), 30); //Added
        Assert.assertEquals("PRO Number", driver.findElement(By.xpath("//thead//th[2]")).getText());
        Assert.assertEquals("Status", driver.findElement(By.xpath("//thead//th[5]")).getText());

  }

	public void setWait(WebElement... elements) {
		Arrays.stream(elements).forEach(ele-> testUtil.setExplicitWait(ele, 60));
	}



  public void verifyTrackingResultFieldsForUnAuthenticatedUser() {
        logger.info("Verify Tracking Results fields");
        testUtil.init(this);

		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'PRO Number')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Status')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Destination Terminal')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Questions')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//a[contains(text(),'Contact us about this shipment')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//th[contains(text(),'Need a delivery receipt or BOL?')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Terminal Phone')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//th[contains(text(),'Terminal Fax')]")).isDisplayed());
	}

  public void insertDashForAssertTrue(ArrayList<String> formattedProNumbers, ArrayList<String> proNumbersDisplayed) {
		proNumbersDisplayed.forEach(p->{

			if(!p.contains("-")) {
			//	logger.info(p.substring(0,3)+"-"+p.substring(3));
				formattedProNumbers.add(p.substring(0,3)+"-"+p.substring(3));
			}
			else {
				logger.info(p);
				formattedProNumbers.add(p);
			}
		});
	}

  public void clickOnViewBOLImage() {
		logger.info("click on View to open new tab with BOL image");
		testUtil.init(this);
		testUtil.clickElementJavascript(viewBOLImageLink);
	}


	public void validateBOLImageIsDisplayed() {
		logger.info("validate BOL image is displayed");
		testUtil.init(this);
		WebElement imgBOL = driver.findElement(By.xpath("//img[@name='image1']"));
		System.out.println(imgBOL);
		assertTrue(imgBOL.isDisplayed());
	}

	public void validateBOLImagePageNumbersAreDisplayed() {
		logger.info("Validate page numbers are displayed");
		testUtil.init(this);
		WebElement pageNumber = driver.findElement(By.xpath("//*[text()[contains(., 'Page 1 of 1')]]"));
		System.out.println(pageNumber);
		assertTrue(pageNumber.isDisplayed());
	}

	public void validateBOLImagePageZoomInButton() {
		logger.info("Validate zoom in is displayed");
		testUtil.init(this);
		WebElement zoomInButton = driver.findElement(By.xpath("//div[@onclick=\"zoomImage('image1', 1.1)\"]"));
		System.out.println(zoomInButton);
		assertTrue(zoomInButton.isDisplayed());
	}


	public void validateBOLImagePageZoomOutButton() {
		logger.info("Validate zoom out is displayed");
		testUtil.init(this);
		WebElement zoomOutButton = driver.findElement(By.xpath("//div[@onclick=\"zoomImage('image1', 0.9)\"]"));
		System.out.println(zoomOutButton);
		assertTrue(zoomOutButton.isDisplayed());
	}

	public void validateBOLImageRotateButton() {
		logger.info("Validate rotate is displayed");
		testUtil.init(this);
		WebElement rotateButton = driver.findElement(By.cssSelector(".fa-redo-alt"));
		System.out.println(rotateButton);
		assertTrue(rotateButton.isDisplayed());
	}

	public void validateBOLImagePrintButton() {
		logger.info("validate print is displayed");
		testUtil.init(this);
		WebElement printButton = driver.findElement(By.xpath("//div[@onclick='printPage()']"));
		System.out.println(printButton);
		assertTrue(printButton.isDisplayed());
	}


	public void deleteAutoPopulatedFields() {
		logger.info("Delete Auto-populated values in Request additional info section");
		testUtil.init(this);

		testUtil.setHardWait(1000);

		name.sendKeys(Keys.CONTROL + "a");

		name.sendKeys(Keys.DELETE);

		testUtil.setHardWait(1000);

		email.sendKeys(Keys.CONTROL + "a");
		email.sendKeys(Keys.DELETE);

		testUtil.setHardWait(1000);

		phoneNo.sendKeys(Keys.CONTROL + "a");
		phoneNo.sendKeys(Keys.DELETE);

		testUtil.setHardWait(1000);

	}

	public void verifyConsigneeCityAndStateAreDisplayed(String city, String state) {
		logger.info("verify consignee city and state are displayed");
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[10]/td/div[2]")).getText();
		//System.out.println("City and State are: " + actual);
		String expected = city + ", " + state;
		//Assert.assertTrue(actual.contains(expected));
		boolean actual = false;
		 try {
			 actual = driver.findElement(By.xpath("//td[contains(text(),'" +expected+ "')]")).isDisplayed();
	        }
		 catch (org.openqa.selenium.NoSuchElementException e) {
	        	System.out.println(e);
       }
		 Assert.assertTrue(actual);
	}

	public void verifyShipperCityStateAndZIPAreDisplayed(String city, String state, String zip) {
		logger.info("Verify shipper city, state, and zip are displayed");
		//String actual = driver.findElement(By.cssSelector(".ng-tns-c2-1:nth-child(8) > .ng-tns-c2-1 > .ng-tns-c2-1")).getText();
		/*String actual =driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[8]/td/div")).getText();
		System.out.println("City, state, and zip are: " + actual);*/
		String expected = city + ", " + state + " " + zip;
		//Assert.assertTrue(actual.contains(expected));

		boolean actual = false;
		 try {
			 actual = driver.findElement(By.xpath("//td[contains(text(),'" +expected+ "')]")).isDisplayed();
	        }
		 catch (org.openqa.selenium.NoSuchElementException e) {
	        	System.out.println(e);
        }
		 Assert.assertTrue(actual);
	}

	public void verifyEstimatedDeliveryDateRange() throws Exception { // validates dates in preview bar
		logger.info("Verify earliest possible delivery date range");
		testUtil.init(this);
		testUtil.setExplicitWait(estimatedDelivery, 20);
		String dateText = estimatedDelivery.getText();
		System.out.println(dateText);
		String tomorrowDate = testUtil.addTomorrowDate2();
		String fiveDaysDate = testUtil.addDateFiveBusinessDaysInFuture2();
		System.out.println(tomorrowDate + " – " + fiveDaysDate);
		Assert.assertTrue(dateText.contains(tomorrowDate + " – " + fiveDaysDate));
	}

	public void verifyEstimatedDeliveryDateRange2() throws Exception { // validates dates in shipmentdetails
		logger.info("Verify earliest possible delivery date range");
		testUtil.init(this);
		testUtil.setExplicitWait(estimatedDeliveryDate, 20);
		String dateText = estimatedDeliveryDate.getText();
		System.out.println(dateText);
		String tomorrowDate = testUtil.addTomorrowDate2();
		String fiveDaysDate = testUtil.addDateFiveBusinessDaysInFuture2();
		System.out.println(tomorrowDate + " – " + fiveDaysDate);
		Assert.assertTrue(dateText.contains(tomorrowDate + " – " + fiveDaysDate));
	}

//	public void verifyInTransitStatus() {
//		String expectedStatus = "Picked Up";
//		logger.info("verify status is " + expectedStatus);
//		String actual = driver.findElement(By.cssSelector(".align-items-center > .ng-tns-c2-12:nth-child(2)")).getText();
//		System.out.println("Actual status is: " + actual);
//		Assert.assertTrue(actual.contains(expectedStatus));
//	}

	public void verifyPickedUpStatus() {
		String expectedStatus = "Picked Up";
		logger.info("verify status is " + expectedStatus);
		String actual = driver.findElement(By.cssSelector(".align-items-center")).getText();
		System.out.println("Actual status is: " + actual);
		Assert.assertTrue(actual.contains(expectedStatus));
	}

	public void verifyMilestoneInfo(String expected) {
		logger.info("verify milestone info is " + expected);

		String actual = driver.findElement(By.xpath("//*[@id=\"main\"]/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div/div/div")).getText();

		System.out.println("Actual milestone info is: " + actual);
		Assert.assertTrue(actual.contains(expected));
	}

	public void clickOnMyEstes() {
		logger.info("Click on MyEstes button");
		testUtil.init(this);
		WebDriverWait wait= new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='my-estes-dropdown']")));
		testUtil.clickElementJavascript(MyEstes);
		testUtil.setHardWait(2000);

	}

	public void clickOnLogin() {
		logger.info("Click on login button");
		testUtil.init(this);
		testUtil.assetWait(null, login, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(login);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

	}

	public void clickOnShipmentStatusCaret() {
		testUtil.init(this);
		logger.info("Click on caret to view shipment status");
		shipmentStatusCaret.click();
		testUtil.setHardWait(3000);
	}

	public void clickOnShipmentHistoryCaret() {
		testUtil.init(this);
		logger.info("Click on caret to view shipment history");
		shipmentHistoryCaret.click();
	}

	public void clickOnShipmentStatusCaretToClose() {
		testUtil.init(this);
		logger.info("Click on caret to close shipment history");
		shipmentStatusCaretClose.click();
	}
	public void clickOnShipmentHistoryCaret2() {
		testUtil.init(this);
		logger.info("Click on caret to view shipment history");
		shipmentHistoryCaret2.click();
	}

	public void verifyInTransitMilestone() {
		logger.info("verify in transit milestone");
		String milestone = driver.findElement(By.cssSelector("#step-2 > .step-info")).getText();
		Assert.assertTrue(milestone.contains("In Transit"));
	}

	public void verifyDisclaimer() {
		logger.info("verify disclaimer");
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Estimated Delivery Date is not guaranteed. For Guaranteed Service please call 1-866-ESTES-4U, Option 2.')]")).isDisplayed());
	}

	public void verifyTimeZone(String zone) throws Exception {
		logger.info("verify time zone is " + zone);
		String shipmentDate = driver.findElement(By.xpath("//dd")).getText();
		System.out.println("freight bill date and time is: " + shipmentDate);
		String hour = testUtil.getHourBasedOnTimeZone(zone);
		System.out.println("current hour in " + zone + " is " + hour);
		Assert.assertTrue(shipmentDate.contains(hour + ":"));
	}

	public void verifyPickedUpMilestone() {
		logger.info("verify picked up milestone");
		String milestone = driver.findElement(By.xpath("//*[@id=\"step-1\"]/div[2]")).getText();
		Assert.assertTrue(milestone.contains("Picked Up"));
	}

	public void verifyPreviewBarProNum(String expectedPro) {
		logger.info("verify pro is " + expectedPro);
		String actual = driver.findElement(By.cssSelector(".cdk-column-pro > .noprint")).getText();
		System.out.println("Actual pro is: " + actual);
		Assert.assertTrue(actual.contains(expectedPro));
	}

	public void verifyFivePreviewBarProNumsInOrder(String expectedPro1, String expectedPro2, String expectedPro3, String expectedPro4, String expectedPro5) {
		logger.info("verify pro 1 is " + expectedPro1);
		String actual1 = driver.findElement(By.xpath("//div[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[1]")).getText();
		System.out.println("Actual pro 1 is: " + actual1);
		Assert.assertTrue(actual1.contains(expectedPro1));

		logger.info("verify pro 2 is " + expectedPro2);
		String actual2 = driver.findElement(By.xpath("//div[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[3]")).getText();
		System.out.println("Actual pro 2 is: " + actual2);
		Assert.assertTrue(actual2.contains(expectedPro2));

		logger.info("verify pro 3 is " + expectedPro3);
		String actual3 = driver.findElement(By.xpath("//div[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[5]")).getText();
		System.out.println("Actual pro 3 is: " + actual3);
		Assert.assertTrue(actual3.contains(expectedPro3));

		logger.info("verify pro 4 is " + expectedPro4);
		String actual4 = driver.findElement(By.xpath("//div[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[7]")).getText();
		System.out.println("Actual pro 4 is: " + actual4);
		Assert.assertTrue(actual4.contains(expectedPro4));

		logger.info("Verify pro 5 is " + expectedPro5);

		logger.info("verify pro 5 is " + expectedPro5);

		String actual5 = driver.findElement(By.xpath("//div[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[9]")).getText();
		System.out.println("Actual pro 5 is: " + actual5);
		Assert.assertTrue(actual5.contains(expectedPro5));
	}


	public void verifyPreviewBarFieldsNotBlank() {

		logger.info("verify data returned in preview bar");
		String proNum = driver.findElement(By.cssSelector(".cdk-column-pro > .noprint")).getText();
		String pickupDate = driver.findElement(By.cssSelector(".mat-cell:nth-child(3)")).getText();

		String bolNumber = driver.findElement(By.cssSelector(".cdk-column-BOL > .noprint")).getText();
		String estDeliveryDate = driver.findElement(By.cssSelector(".mat-cell:nth-child(5)")).getText();

		Assert.assertFalse(proNum.isEmpty());
		Assert.assertFalse(pickupDate.isEmpty());
		Assert.assertFalse(bolNumber.isEmpty());
		Assert.assertFalse(estDeliveryDate.isEmpty());
	}

	public void verifyValueEBL() {
		testUtil.init(this);
		logger.info("Verify EBL value");
		if(valueEBL.size()>0)
			Assert.assertTrue(valueEBL.size()>0);
	}

	public void verifyValueTransitDays() {
		testUtil.init(this);
		logger.info("Verify transit days value");
		if(valuePRONumber.size()>0)
			Assert.assertTrue(valueTransitDays.size()>0);
		Assert.assertFalse(valueTransitDays.isEmpty());
	}

	public void verifyValueTimeCritical() {
		testUtil.init(this);
		logger.info("Verify Time Critical value");
		if(valuePRONumber.size()>0)
			Assert.assertTrue(valueTimeCritical.size()>0);


	}

	public boolean verifyShowOnlyMyShipmentsToggleIsNotDisplayed() {
        logger.info("Verify show only my shipments toggle is not displayed");
        testUtil.init(this);

        try {
            myShipmentsToggle.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

	}



	public void verifyToggleIsON() {
		logger.info("Verify toggle is ON");
		testUtil.init(this);
		toggleSwitch.isSelected();

	}

	public void verifyToggleColor() {
		logger.info("Verify shipments toggle color is Yellow");
		testUtil.init(this);
		String color = toggleColor.getCssValue("color");
		String[] hexValue = color.replace("rgba(", "").replace(")","").split(",");
		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1,hexValue2,hexValue3);
		System.out.println(actualColor);
		Assert.assertTrue(actualColor.equals("#000000"));

	}

	public void selectPickupRequestNumber() throws InterruptedException {
		logger.info("Select pickup request number");
		testUtil.init(this);
		testUtil.setHardWait(1000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();",DropDown );
		 testUtil.clickElementJavascript(DropDown);

         testUtil.setHardWait(500);
         testUtil.clickElementJavascript(pickupRequest);
	}

	public void verifyDataReturnedInPreviewBar() {

		logger.info("verify data returned in preview bar");
		String proNum = driver.findElement(By.cssSelector(".cdk-column-pro > .noprint")).getText();
		String pickupDate = driver.findElement(By.cssSelector(".mat-cell:nth-child(3)")).getText();
		String bolNumber = driver.findElement(By.cssSelector(".cdk-column-BOL > .noprint")).getText();
		String estDeliveryDate = driver.findElement(By.cssSelector(".mat-cell:nth-child(5)")).getText();


		Assert.assertFalse(proNum.isEmpty());
		Assert.assertFalse(pickupDate.isEmpty());
		Assert.assertFalse(bolNumber.isEmpty());
		Assert.assertTrue(estDeliveryDate.isEmpty());
	}


	public void verifyEstimatedDeliveryDate(String date) throws Exception { // validates dates in preview bar
		logger.info("Verify delivery date is " + date);
		testUtil.init(this);
		testUtil.setExplicitWait(estimatedDelivery, 20);
		String dateText = estimatedDelivery.getText();
		System.out.println(dateText);
		Assert.assertTrue(dateText.contains(date));
	}

	public void verifyPreviewBarBolNum(String expectedBol) {
		logger.info("verify bol is " + expectedBol);
		String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[1]/td[4]/span")).getText();
		System.out.println("Actual bol is: " + actual);
		Assert.assertTrue(actual.contains(expectedBol));
	}

	public void verifyAppointmentDate(String expectedDate) {
		logger.info("verify Appointment date is " + expectedDate);
		testUtil.init(this);
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[1]/td")).getText();
		String actual = driver.findElement(By.xpath("//mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[6]/table/tbody/tr[2]/td/span[1]")).getText();
		System.out.println("Actual appt date is: " + actual);
		Assert.assertTrue(actual.contains(expectedDate));
	}

	public void verifyAppointmentTime(String expectedTime) {
		logger.info("verify time is " + expectedTime);
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td/span")).getText();
		String actual = driver.findElement(By.xpath("//mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[6]/table/tbody/tr[2]/td/span[2]")).getText();
		System.out.println("Actual time is: " + actual);
		Assert.assertTrue(actual.contains(expectedTime));
	}

	public void verifyAppointmentStatus(String expectedStatus) {
		logger.info("verify status is " + expectedStatus);
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[3]/td")).getText();
		String actual = driver.findElement(By.xpath("//mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[6]/table/tbody/tr[3]/td")).getText();
		System.out.println("Actual status is: " + actual);
		Assert.assertTrue(actual.contains(expectedStatus));
	}

	public void validateShipmentHistory(String history) {
		logger.info("validate shipment history contains: " + history);
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//div//span[contains(text(),'"+history+"')]")).isDisplayed());
	}

	public void clickOnCollapseButton() {
		testUtil.init(this);
		logger.info("Click on collapse button");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)");
		//collapseBtn.click();

		testUtil.clickElementJavascript(collapseBtn);
		testUtil.setHardWait(2000);
	}

	public void verifyShipmentPickedupStatus() {
		testUtil.init(this);
		logger.info("Verify shipment picked up status");
		Assert.assertTrue(
				driver.findElement(By.xpath("//div//span[contains(text(),'Shipment picked up')]")).isDisplayed());
		testUtil.setHardWait(2000);
	}



	public void verifyValueTimeCritical10AM() {
		testUtil.init(this);
		logger.info("Verify Time Critical 10AM text");
		if(valuePRONumber.size()>0)
			Assert.assertTrue(valueTimeCritical10AM.size()>0);
	}
	public void verifyValueTimeCritical12PM() {
		testUtil.init(this);
		logger.info("Verify Time Critical 12PM text");
		if(valuePRONumber.size()>0)
			Assert.assertTrue(valueTimeCritical12PM.size()>0);
	}

	public void verifyValueTimeCritical5PM() {
		testUtil.init(this);
		logger.info("Verify Time Critical 5PM text");
		if(valuePRONumber.size()>0)
			Assert.assertTrue(valueTimeCritical5PM.size()>0);
	}

	public void clickOnProNumPreviewColumn() {
		testUtil.init(this);
		logger.info("Click on PRO Number preview column header");
		proNumberPreviewColumn.click();
	}

	public void clickOnPickupDatePreviewColumn() {
		testUtil.init(this);
		logger.info("Click on Pickup Date preview column header");
		pickupDatePreviewColumn.click();
	}

	public void clickOnBolNumPreviewColumn() {
		testUtil.init(this);
		logger.info("Click on BOL Number preview column header");
		bolNumberPreviewColumn.click();
	}

	public void clickOnEstimatedDeliveryPreviewColumn() {
		testUtil.init(this);
		logger.info("Click on Estimated Delivery preview column header");
		estimatedDeliveryPreviewColumn.click();
	}

	public void clickOnStatusPreviewColumn() {
		testUtil.init(this);
		logger.info("Click on Status preview column header");
		statusPreviewColumn.click();
	}

	public void clickSearchBy() {
		logger.info("click on Search By dropdown");
		testUtil.init(this);
		testUtil.clickElementJavascript(searchBy);
	}

	public void clickSearchByBOL() throws InterruptedException {
		logger.info("Enter Type");
		testUtil.init(this);
		testUtil.assetWait("//span[@class='mat-option-text'][contains(text(),'Bill of Lading')]", null, 10, 200, TimeUnit.MILLISECONDS);
		WebElement bol = driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Bill of Lading')]"));
		testUtil.clickElementJavascript(bol);
	}

	public void clickSearchByPO() throws InterruptedException {
		logger.info("Enter Type");
		testUtil.init(this);
		testUtil.assetWait("//span[@class='mat-option-text'][contains(text(),'Purchase Order')]", null, 10, 200, TimeUnit.MILLISECONDS);
		WebElement po = driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Purchase Order')]"));
		testUtil.clickElementJavascript(po);
	}


	public void verifyStatus(String status) {
		logger.info("Verify status");
		String result = driver.findElement(By.xpath("//div/span[contains(text(),'"+status+"')]")).getText();
		System.out.println("Status is :"+ result);
		String expected = status;
		assertEquals(result, expected);
	}


	public void verifyMilestone(String expectedmilestone) {
		logger.info("verify milestone");
		String actualMilestone = driver.findElement(By.xpath("//*[@id=\"step-1\"]/div[2]")).getText();
		Assert.assertTrue(actualMilestone.contains(expectedmilestone));
	}

	public void verifyValueEstimatedDeliveryDateNotDisplayed() {
		testUtil.init(this);
		logger.info("Verify Estimated Delivery Date Is Not Displayed");
		if(valueEstimatedDeliveryDate.size()>0)
			Assert.assertFalse(valueEstimatedDeliveryDate.size()>0);
		Assert.assertTrue(valueEstimatedDeliveryDate.isEmpty());
	}

	public void validateDisclaimer() {
		logger.info("verify disclaimer");
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'We're sorry, but we cannot calculate an estimated delivery date.')]")).isDisplayed());
	}

	public void validateErrorMessage(String invalidPRO) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMsg, 120);
		String message = errorMsg.getText();
		System.out.println(message);
		testUtil.setHardWait(2000);
		assertEquals(message,
				"Not found or tracking information unavailable: " + invalidPRO + ".");

	}

	public void validateErrorMessage(String invalidPRO, String errMsg) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMsg, 120);
		String message = errorMsg.getText();
		System.out.println(message);
		testUtil.setHardWait(2000);
		assertEquals(message,errMsg+ invalidPRO + ".");

	}

	public void clickTheX() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click the X");
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(clickX);

	}
	public void validateProNumber(String proNum) {
		logger.info("Verify PRO number ");
		testUtil.init(this);
		//assertTrue(driver.findElement(By.xpath("(//span[(contains(text(), '"+proNum+"'))])[2]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//td[@role='gridcell']//span[(contains(text(), '"+proNum+"'))]")).isDisplayed());
	}

	public void validateShipmentDetails(String ...details) {
		testUtil.init(this);
		assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+details[0]+"')]")).isDisplayed());
		for(int i = 1; i < details.length; i++) {
			assertTrue(driver.findElement(By.xpath("(//td[text()='"+details[i]+"'])[2]")).isDisplayed());
		}
	}

	public void verifyShipmentDateInHistory(String history) {
		logger.info("validate shipment history contains: " + history);
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'"+history+"')]")).isDisplayed());
	}


	public void verifyAppointmentDate1(String expectedDate) {
		logger.info("verify Appointment date is " + expectedDate);
		testUtil.init(this);
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[1]/td")).getText();
		String actual = driver.findElement(By.xpath("//mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[6]/table/tbody/tr[3]/td/span[1]")).getText();
		System.out.println("Actual appt date is: " + actual);
		Assert.assertTrue(actual.contains(expectedDate));
	}

	public void verifyAppointmentTime1(String expectedTime) {
		logger.info("verify time is " + expectedTime);
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td/span")).getText();
		String actual = driver.findElement(By.xpath("//mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[6]/table/tbody/tr[3]/td/span[2]")).getText();
		System.out.println("Actual time is: " + actual);
		Assert.assertTrue(actual.contains(expectedTime));
	}

	public void verifyAppointmentStatus1(String expectedStatus) {
		logger.info("verify status is " + expectedStatus);
		//String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-root/div/mat-card/mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/table/tbody/tr[3]/td")).getText();
		String actual = driver.findElement(By.xpath("//mat-card-content/table[2]/tbody/tr[2]/td/div/div/div/div[6]/table/tbody/tr[4]/td")).getText();
		System.out.println("Actual status is: " + actual);
		Assert.assertTrue(actual.contains(expectedStatus));
	}




	public void verifyPROHeader() {
		testUtil.init(this);
		WebElement prh = driver.findElement(By.xpath("//button/span[text()='PRO Number']"));
		testUtil.assetWait(null, prh, 70, 700, TimeUnit.MILLISECONDS);
		Assert.assertEquals(prh.isDisplayed(), true);
	}

	public void clickExpandArrow() {
		logger.info("Click expand arrow.");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"[class='fal fa-chevron-down']\").click();");
		testUtil.setHardWait(2000);
}



	public void verifyStatusHeader() {
		testUtil.init(this);
		WebElement sh = driver.findElement(By.xpath("//button[text()='Status']"));
		testUtil.assetWait(null, sh, 70, 700, TimeUnit.MILLISECONDS);
		Assert.assertEquals(sh.isDisplayed(), true);
	}

	public void verifyPickupHeader() {
		testUtil.init(this);
		WebElement ph = driver.findElement(By.xpath("//button/span[text()='Pickup Date']"));
		testUtil.assetWait(null, ph, 70, 700, TimeUnit.MILLISECONDS);
		Assert.assertEquals(ph.isDisplayed(), true);
	}

	public void verifyBOLHeader() {
		testUtil.init(this);
		WebElement bh = driver.findElement(By.xpath("//button/span[text()='BOL Number']"));
		testUtil.assetWait(null, bh, 70, 700, TimeUnit.MILLISECONDS);
		Assert.assertEquals(bh.isDisplayed(), true);
	}


	public void verifyAndPrintAnyPROTable() {

		// Get the table based on a <tr> element which has at least 5 siblings. Then
		// get the first table ancestor of that element.

		WebElement table = driver.findElement(By.xpath("//tr/following-sibling::tr[4]/ancestor::table"));

		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String Expected = "CellName";

		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				System.out.println(col.getText() + "\t");
				String Actual = col.getText();
				if (Actual.equals(Expected)) {
					System.out.println("Verified.");
				}
			}
			System.out.println();
		}
	}


	public void verifyValueQuestions() {
		testUtil.init(this);
		logger.info("Verify Questions");
		if (valueQuestions.size() > 0)
			Assert.assertTrue(valueQuestions.size() > 0);
	}

	public void validateNotFoundMessage(String expectedMessage) {
		logger.info("Validate the tracking number"); 
		String visibleMessage = driver.findElement(By.xpath("//div[@class='ng-tns-c3-1 ng-star-inserted'][text() = ' Not found or tracking information unavailable: ']")).getText(); 
		logger.info(visibleMessage); 
		Assert.assertEquals(visibleMessage, expectedMessage); 
	}	
	public void clickOnTrackPickupRequestLink() {
		testUtil.init(this);
		logger.info("Click on Track Pickup Request Link");
		testUtil.clickElementJavascript(trackPickupReqLink);
	}

	public void verifyUserNavigatedToNewWindow(int windowID, String expectedUrl) {
		testUtil.init(this);
		logger.info("Verify user is navigated to new window");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String actualUrl = driver.switchTo().window(tabs.get(windowID)).getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	public void verifyErrMsgInNewTab(String expectedMsg) {
		testUtil.init(this);
		logger.info("Verify error message in new window");
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Are you trying to')]")).getText(), expectedMsg);

	}

	public void switchBackToMainPage() {
		testUtil.init(this);
		logger.info("Verify user is navigated to new window");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

	public void clickOnOthersLink() {
		testUtil.init(this);
		logger.info("Click on Others Link");
		testUtil.clickElementJavascript(othersLink);
		testUtil.setHardWait(5000);
	}

	public void clickOnClosebutton() {
		testUtil.init(this);
		logger.info("Click on the close icon in error message");
		closeIconInErrMsg.click();
	}

	public void validateErrMsg(String errMsg) {
		logger.info("Validate error message");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMsg, 120);
		String message = errorMsg.getText();
		System.out.println(message);
		testUtil.setHardWait(2000);
		assertEquals(message,errMsg);

	}

	public void turnOffShowOnlyMyShipmentsToggle()  {
		logger.info("Trun off the toggle 'Show my only Shipments'");
		testUtil.init(this);
		if(myShipmentsToggle.isSelected()) {
			myShipmentsToggle.click();
		}

	}
}






