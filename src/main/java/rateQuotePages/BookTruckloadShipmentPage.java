package rateQuotePages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class BookTruckloadShipmentPage extends TestBase {

	private Logger logger = Logger.getLogger(BookTruckloadShipmentPage.class);

	/************************** ELEMENTS *******************************/

	// Requester Info
	@FindBy(how = How.ID, using = "requesterContactName")
	private WebElement ContactNameField;

	@FindBy(how = How.ID, using = "requesterContactCompany")
	private WebElement companyName;

	@FindBy(how = How.ID, using = "requesterContactEmail")
	private WebElement emailId;

	@FindBy(how = How.ID, using = "requesterContactPhone")
	private WebElement phoneNo;

	@FindBy(how = How.ID, using = "requesterContactPhoneExt")
	private WebElement phoneExt;

	// Origin Info
	@FindBy(how = How.ID, using = "originContactName")
	private WebElement originContactName;

	@FindBy(how = How.ID, using = "originContactEmail")
	private WebElement originEmail;

	@FindBy(how = How.ID, using = "originContactPhone")
	private WebElement originPhoneNo;

	@FindBy(how = How.ID, using = "originContactPhoneExt")
	private WebElement originPhoneExt;

	@FindBy(how = How.ID, using = "originStreetAddress1")
	private WebElement originAddress;

	@FindBy(how = How.ID, using = "originStreetAddress2")
	private WebElement originAddress2;

	@FindBy(how = How.ID, using = "originAppointmentRequired")
	private WebElement originAppointment;

	@FindBy(how = How.ID, using = "originStartTimeHour")
	private WebElement pickupByHour;

	@FindBy(how = How.ID, using = "originStartTimeMinute")
	private WebElement pickupByMinutes;

	@FindBy(how = How.ID, using = "originStartTimeAm")
	private WebElement pickupByAmPm;

	@FindBy(how = How.ID, using = "originEndTimeHour")
	private WebElement pickupEndHour;

	@FindBy(how = How.ID, using = "originEndTimeMinute")
	private WebElement pickupEndMinutes;

	@FindBy(how = How.ID, using = "originEndTimeAm")
	private WebElement pickupEndAmPm;

	@FindBy(how = How.ID, using = "originSpecialInstructions")
	private WebElement originSpecial;

	// Destination Info
	@FindBy(how = How.ID, using = "destinationContactName")
	private WebElement destContactName;

	@FindBy(how = How.ID, using = "destinationContactEmail")
	private WebElement destEmail;

	@FindBy(how = How.ID, using = "destinationContactPhone")
	private WebElement destPhone;

	@FindBy(how = How.ID, using = "destinationContactPhoneExt")
	private WebElement destPhoneExt;

	@FindBy(how = How.ID, using = "destinationStreetAddress1")
	private WebElement destAddress1;

	@FindBy(how = How.ID, using = "destinationStreetAddress2")
	private WebElement destAddress2;

	@FindBy(how = How.ID, using = "destinationAppointmentRequired")
	private WebElement destAppointment;

	@FindBy(how = How.ID, using = "destinationStartTimeHour")
	private WebElement deliveryByHour;

	@FindBy(how = How.ID, using = "destinationStartTimeMinute")
	private WebElement deliveryByMinutes;

	@FindBy(how = How.ID, using = "destinationStartTimeAm")
	private WebElement deliveryByAmPm;

	@FindBy(how = How.ID, using = "destinationEndTimeHour")
	private WebElement deliveryToHour;

	@FindBy(how = How.ID, using = "destinationEndTimeMinute")
	private WebElement deliveryToMinutes;

	@FindBy(how = How.ID, using = "destinationEndTimeAm")
	private WebElement deliveryToAmPm;

	@FindBy(how = How.ID, using = "destinationSpecialInstructions")
	private WebElement destSpecial;

	// Freight Information

	@FindBy(how = How.XPATH, using = "//mat-select[@id='commodityType']//div[@class='mat-select-arrow']")
	private WebElement commodityType;

	@FindBy(css = "[formcontrolname='quantity']")
	private WebElement quantity;

	@FindBy(how = How.ID, using = "freightDescription")
	private WebElement freightDesc;

	@FindBy(how = How.ID, using = "freightValue")
	private WebElement freightValue;

	@FindBy(how = How.ID, using = "referenceNumber")
	private WebElement reference;

	@FindBy(how = How.ID, using = "poNumber")
	private WebElement poNum;

	@FindBy(how = How.ID, using = "temperatureLow")
	private WebElement tempFrom;

	@FindBy(how = How.ID, using = "temperatureHigh")
	private WebElement tempTo;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement shipmentBtn;

	@FindBy(xpath = "//*[contains(text(),'Success! Your booking is confirmed')]")
	private WebElement successMessage;
	
	@FindBy(xpath = "//*[contains(text(),'Your requested pickup time is too early')]")
	private WebElement message;
	

	/************************************ METHODS***************************************/


	public void enterContactName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		testUtil.setExplicitWait(ContactNameField, 20);
		ContactNameField.clear();
		ContactNameField.sendKeys(name);
	}

	public void enterCompanyName(String name) {
		logger.info("Enter company name");
		testUtil.init(this);
		testUtil.setExplicitWait(companyName, 20);
		companyName.clear();
		companyName.sendKeys(name);
	}

	public void enterYourEmail(String email) {
		logger.info("Enter email address");
		testUtil.init(this);
		testUtil.setExplicitWait(emailId, 20);
		emailId.clear();
		emailId.sendKeys(email);
	}

	public void enterPhoneNo(String phone) {
		logger.info("Enter phone number");
		testUtil.init(this);
		testUtil.setExplicitWait(phoneNo, 20);
		phoneNo.clear();
		phoneNo.sendKeys(phone);

	}

	public void enterPhoneExtentionNo(String exNum) {
		logger.info("Enter phone number extention ");
		testUtil.init(this);
		testUtil.setExplicitWait(phoneExt, 20);
		phoneExt.clear();
		phoneExt.sendKeys(exNum);
	}

	public void enterOriginContactName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		testUtil.setExplicitWait(originContactName, 20);
		originContactName.clear();
		originContactName.sendKeys(name);
	}

	public void enterOriginEmail(String email) {
		logger.info("Enter email address");
		testUtil.init(this);
		testUtil.setExplicitWait(originEmail, 20);
		originEmail.clear();
		originEmail.sendKeys(email);
	}

	public void enterOriginPhoneNo(String phone) {
		logger.info("Enter phone number");
		testUtil.init(this);
		testUtil.setExplicitWait(originPhoneNo, 20);
		originPhoneNo.clear();
		originPhoneNo.sendKeys(phone);

	}

	public void enterOriginPhoneExtentionNo(String exNum) {
		logger.info("Enter phone number extention ");
		testUtil.init(this);
		testUtil.setExplicitWait(originPhoneExt, 20);
		originPhoneExt.clear();
		originPhoneExt.sendKeys(exNum);
	}

	public void enterOriginAddress1(String address) {
		logger.info("Enter Address line 1");
		testUtil.init(this);
		testUtil.setExplicitWait(originAddress, 20);
		originAddress.clear();
		originAddress.sendKeys(address);
	}

	public void enterOriginAddress2(String address) {
		logger.info("Enter Address line 2");
		testUtil.init(this);
		originAddress2.clear();
		testUtil.setExplicitWait(originAddress2, 20);
		originAddress2.sendKeys(address);
	}

	public void clickOnOriginAppointmentCheckbox() {
		logger.info("Click on appointment required checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(originAppointment, 20);
		originAppointment.click();

	}

	public void enterOriginSpecialInstructions(String special) {
		logger.info("Enter origin special instructions");
		testUtil.init(this);
		testUtil.setExplicitWait(originSpecial, 20);
		originSpecial.clear();
		originSpecial.sendKeys(special);
	}

	public void enterDestContactName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		testUtil.setExplicitWait(destContactName, 20);
		destContactName.clear();
		destContactName.sendKeys(name);
	}

	public void enterDestEmail(String email) {
		logger.info("Enter email address");
		testUtil.init(this);
		testUtil.setExplicitWait(destEmail, 20);
		destEmail.clear();
		destEmail.sendKeys(email);
	}

	public void enterDestPhoneNo(String phone) {
		logger.info("Enter phone number");
		testUtil.init(this);
		testUtil.setExplicitWait(destPhone, 20);
		destPhone.clear();
		destPhone.sendKeys(phone);

	}

	public void enterDestPhoneExtentionNo(String exNum) {
		logger.info("Enter phone number extention ");
		testUtil.init(this);
		testUtil.setExplicitWait(destPhoneExt, 20);
		destPhoneExt.clear();
		destPhoneExt.sendKeys(exNum);
	}

	public void enterDestAddress1(String address) {
		logger.info("Enter Address line 1");
		testUtil.init(this);
		testUtil.setExplicitWait(destAddress1, 20);
		destAddress1.clear();
		destAddress1.sendKeys(address);
	}

	public void enterDestAddress2(String address) {
		logger.info("Enter Address line 2");
		testUtil.init(this);
		testUtil.setExplicitWait(destAddress2, 20);
		destAddress2.clear();
		destAddress2.sendKeys(address);
	}

	public void clickOnDestAppointmentCheckbox() {
		logger.info("Click on appointment required checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(destAppointment, 20);
		destAppointment.click();

	}

	public void enterDestSpecialInstructions(String special) {
		logger.info("Enter destination special instructions");
		testUtil.init(this);
		testUtil.setExplicitWait(destSpecial, 20);
		destSpecial.clear();
		destSpecial.sendKeys(special);
	}

	public void selectPickupEndTime(String avlHour, String avlMnts, String avlAmPm) {
		logger.info("Select pickup end time as: " + avlHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupEndHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlHour + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Minutes as: " + avlMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupEndMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlMnts + "')]"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Am/Pm as: " + avlAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupEndAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlAmPm + "')]"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}

	public void selectPickupStartTime(String avlHour, String avlMnts, String avlAmPm) {
		logger.info("Select pickup start time as: " + avlHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		WebElement element = driver.findElement(By.id("originStartTimeHour"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();

		testUtil.setHardWait(2000);
		pickupByHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlHour + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Minutes as: " + avlMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupByMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlMnts + "')]"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Am/Pm as: " + avlAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupByAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlAmPm + "')]"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}

	public void selectDeliveryStartTime(String closeHour, String closeMnts, String closeAmPm) {
		logger.info("Select delivery start time as: " + closeHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryByHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeHour + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Minutes as: " + closeMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryByMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeMnts + "')]"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Am/Pm as: " + closeAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryByAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeAmPm + "')]"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}

	public void selectDeliveryEndTime(String closeHour, String closeMnts, String closeAmPm) {
		logger.info("Select delivery end time as: " + closeHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryToHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeHour + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Minutes as: " + closeMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryToMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeMnts + "')]"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Am/Pm as: " + closeAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryToAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeAmPm + "')]"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}

	public void enterCommodityType(String Type) throws InterruptedException {
		logger.info("Enter commodity Type");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityType, 20);
		testUtil.clickElementJavascript(commodityType);
		testUtil.setHardWait(2000);

		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + Type + "')]")).click();
		testUtil.setHardWait(2000);
	}

	public void enterQuantity(String Qty) {
		logger.info("Enter quantity");
		testUtil.init(this);
		testUtil.setExplicitWait(quantity, 20);
		quantity.clear();
		quantity.sendKeys(Qty);
	}

	public void enterFreightDesc(String desc) {
		logger.info("Enter Freight Description");
		testUtil.init(this);
		testUtil.setExplicitWait(freightDesc, 20);
		freightDesc.clear();
		freightDesc.sendKeys(desc);
	}

	public void enterFreightValue(String value) {
		logger.info("Enter freight value");
		testUtil.init(this);
		testUtil.setExplicitWait(freightValue, 20);
		freightValue.clear();
		freightValue.sendKeys(value);
	}

	public void enterReferenceNumber(String ref) {
		logger.info("Enter quote number in reference field");
		testUtil.init(this);
		testUtil.setExplicitWait(reference, 20);
		reference.clear();
		testUtil.setHardWait(1000);
		reference.sendKeys(ref);
	}

	public void enterPONumber(String po) {
		logger.info("Enter PO number in reference field");
		testUtil.init(this);
		testUtil.setExplicitWait(poNum, 20);
		poNum.clear();
		poNum.sendKeys(po);
	}

	public void enterTempNumberRangeFrom(String temp) {
		logger.info("Enter temperature range from ");
		testUtil.init(this);
		testUtil.setExplicitWait(tempFrom, 20);
		tempFrom.clear();
		tempFrom.sendKeys(temp);
	}

	public void enterTempNumberRangeTo(String temp) {
		logger.info("Enter temperature range to");
		testUtil.init(this);
		testUtil.setExplicitWait(tempTo, 20);
		tempTo.clear();
		tempTo.sendKeys(temp);
	}

	public void clickOnBookTruckloadShipmentButton() {
		logger.info("Click on book truckload button");
		testUtil.init(this);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		
		int i = 0;
		int exit = 0;

		do {
			testUtil.clickElementJavascript(shipmentBtn);
			
			WebElement ele=	driver.findElement(By.xpath("//*[@id='main']/app-rate-quote/lib-routable-component-header/h1/span[contains(text(),'Rate Quote')]"));
		
			if(ele.isDisplayed()) {
				
			exit=1;
		}
		
			i++;
	
	}while (i <= 5 && exit !=1); 
			 
	
	}



	public void verifySuccessMessageIsDisplayed() {
		logger.info("Verify the success message");
		testUtil.init(this);
		testUtil.setExplicitWait(successMessage, 90);
		boolean verifyMessage = successMessage.isDisplayed();
		Assert.assertEquals(verifyMessage, true);
		testUtil.setHardWait(1000);
	}

	public String recordPRONum() {
		logger.info("Capture PRO number");
		testUtil.init(this);

		testUtil.setExplicitWait(driver.findElement(By.xpath("//lib-form-header/h6[1]")), 2000);
		String quoteNumber = driver.findElement(By.xpath("//lib-form-header/h6[1]")).getText().substring(16).trim();
		System.out.println("Quote Number is :" + quoteNumber);
		return quoteNumber;
	}
	

	public void verifyAMessageIsDisplayed(String message2) {
		logger.info("Verify the message");
		testUtil.init(this);
		boolean verifyMessage = message.isDisplayed();
		System.out.println(verifyMessage);
		Assert.assertEquals(verifyMessage, true);
		testUtil.setHardWait(2000);
	}
	
	
	public String recordPRONum1() { // changed xpath because method above wasn't working for qz10382
		logger.info("Capture PRO number");
		testUtil.init(this);

		testUtil.setExplicitWait(
				driver.findElement(By.xpath(
						"//div[@id='main']/app-rate-quote/app-truckload-booking-success/div/div/lib-form-header/h6")),
				2000);
		String quoteNumber = driver
				.findElement(By.xpath(
						"//div[@id='main']/app-rate-quote/app-truckload-booking-success/div/div/lib-form-header/h6"))
				.getText().substring(16).trim();
		System.out.println("PRO Number is: " + quoteNumber);
		return quoteNumber;
}
	
	public String recordCharges() {
		logger.info("Capture Charges" );
		testUtil.init(this);

		testUtil.setExplicitWait(
				driver.findElement(By.xpath("//div[@id='main']/app-rate-quote/app-truckload-booking-success/div/div/div[2]/div[3]")),
				2000);
		String quoteNumber = driver
				.findElement(By.xpath("//div[@id='main']/app-rate-quote/app-truckload-booking-success/div/div/div[2]/div[3]")).getText().substring(10).trim();
		System.out.println("Charges are:" + quoteNumber);
		return quoteNumber;
	}


}
