package eNetPages;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class ENetTruckloadRateQuotePage extends TestBase {
	
	private Logger logger=Logger.getLogger(ENetTruckloadRateQuotePage.class);	
	
	
	
	@FindBy(id = "zipOrigin")
	private WebElement zipOrigin;
	
	@FindBy(id = "suggestOrigin")
	private WebElement originSuggestion;
	
	@FindBy(id = "zipDestination")
	private WebElement zipDestination;
	
	@FindBy(id = "suggestDestination")
	private WebElement destinationSuggestion;
	
	@FindBy(id = "pickupDate")
	private WebElement pickupDate;
	
	@FindBy(id = "equipmentType")
	private WebElement equipmentType;
	
	@FindBy(xpath = "//input[@class='totalWeight']")
	private WebElement weight;
	
	@FindBy(id = "ratequoteSubmitButton")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//*[@id='tc_table']/tbody/tr/td[4]/a")
	private WebElement charges;

	@FindBy(xpath = "//h1[contains(text(),'Truckload Rate Quote')]")
	private WebElement pageTitle;
	
	@FindBy(id = "quote_number")
	private WebElement quoteNumber;
	
	@FindBy(id = "tos_label")
	private WebElement checkbox;

	@FindBy(id = "book_shipment_button_enet")
	private WebElement shipmentBtn;
	
	//Contact Info Section
	
	 //Requester Info
		@FindBy(how = How.ID, using = "contact_name")
		private WebElement ContactNameField;

		@FindBy(how = How.ID, using = "company_name")
		private WebElement companyName;

		@FindBy(how = How.ID, using = "contact_email")
		private WebElement emailId;

		@FindBy(how = How.ID, using = "contact_phone")
		private WebElement phoneNo;
		
		@FindBy(how = How.ID, using = "requesterContactPhoneExt")
		private WebElement phoneExt;
		
		//Origin Info
		@FindBy(how = How.ID, using = "origin_contact_name")
		private WebElement originContactName;

		@FindBy(how = How.ID, using = "origin_contact_email")
		private WebElement originEmail;
			
		@FindBy(how = How.ID, using = "origin_contact_phone")
		private WebElement originPhoneNo;

		@FindBy(how = How.ID, using = "originContactPhoneExt")
		private WebElement originPhoneExt;
		
		@FindBy(how = How.ID, using = "origin_street_address1")
		private WebElement originAddress;
		
		@FindBy(how = How.ID, using = "street_address2")
		private WebElement originAddress2;
		
		@FindBy(how = How.ID, using = "origin_appointmentRequired")
		private WebElement originAppointment;
		
		
		@FindBy(how = How.ID, using = "origin_appointmentStartTimeHH")
		private WebElement pickupByHour;
		
		@FindBy(how = How.ID, using = "origin_appointmentStartTimeMM")
		private WebElement pickupByMinutes;
		
		@FindBy(how = How.ID, using = "origin_appointmentStartAmPm")
		private WebElement pickupByAmPm;
		
		
		@FindBy(how = How.ID, using = "origin_appointmentEndTimeHH")
		private WebElement pickupEndHour;
		
		@FindBy(how = How.ID, using = "origin_appointmentEndTimeMM")
		private WebElement pickupEndMinutes;
		
		@FindBy(how = How.ID, using = "origin_appointmentEndAmPm")
		private WebElement pickupEndAmPm;
		
		@FindBy(how = How.ID, using = "origin_specialInstructions")
		private WebElement originSpecial;

		//Destination Info
		@FindBy(how = How.ID, using = "destination_contact_name")
		private WebElement destContactName;

		@FindBy(how = How.ID, using = "destination_contact_email")
		private WebElement destEmail;
			
		@FindBy(how = How.ID, using = "destination_contact_phone")
		private WebElement destPhone;

		@FindBy(how = How.ID, using = "destinationContactPhoneExt")
		private WebElement destPhoneExt;
		
		@FindBy(how = How.ID, using = "destination_street_address1")
		private WebElement destAddress1;

		@FindBy(how = How.ID, using = "destination_street_address2")
		private WebElement destAddress2;
		
		@FindBy(how = How.ID, using = "destination_appointmentRequired")
		private WebElement destAppointment;
		
		@FindBy(how = How.ID, using = "destination_appointmentStartTimeHH")
		private WebElement deliveryByHour;
		
		@FindBy(how = How.ID, using = "destination_appointmentStartTimeMM")
		private WebElement deliveryByMinutes;
		
		@FindBy(how = How.ID, using = "destination_appointmentStartAmPm")
		private WebElement deliveryByAmPm;
		
		@FindBy(how = How.ID, using = "destination_appointmentEndTimeHH")
		private WebElement deliveryToHour;
		
		@FindBy(how = How.ID, using = "destination_appointmentEndTimeMM")
		private WebElement deliveryToMinutes;
		
		@FindBy(how = How.ID, using = "destination_appointmentEndAmPm")
		private WebElement deliveryToAmPm;
		
		@FindBy(how = How.ID, using = "destination_specialInstructions")
		private WebElement destSpecial;


	    //Freight Information
	 
		@FindBy(how = How.XPATH, using = "//select[@id='commodity_type']")
		private WebElement commodityType;
		
		@FindBy(how = How.ID, using = "quantity")
		private WebElement quantity;
		
		@FindBy(how = How.ID, using = "freightDescription")
		private WebElement freightDesc;
		
		@FindBy(how = How.ID, using = "freightValue")
		private WebElement freightValue;
		
		@FindBy(how = How.ID, using = "referenceNumber")
		private WebElement reference;
		
		@FindBy(how = How.ID, using = "poNumber")
		private WebElement poNum;
		
		@FindBy(how = How.ID, using = "tempRangeFrom")
		private WebElement tempFrom;
		
		@FindBy(how = How.ID, using = "tempRangeTo")
		private WebElement tempTo;
		
		@FindBy(how = How.XPATH, using = "//*[@id='submit_container']/input")
		private WebElement submit;
		
		
		@FindBy(xpath="//*[contains(text(),'Success! Your booking is confirmed')]")
		private WebElement successMessage;
		
		@FindBy(id = "HAZ")
		private WebElement hazardCheckBx;
		@FindBy(id = "hzTankerEndorsement")
		private WebElement tankerCheckBx;

		@FindBy(id = "hzUNnumber0")
		private WebElement accessorialsUNNumber;

		@FindBy(id = "hzPackagingCode0")
		private WebElement accessorialsPackagingCode;

		@FindBy(id = "hzCommodityType0")
		private WebElement accessorialsCommodityType;

		@FindBy(id = "hzPieces0")
		private WebElement accessorialsQuantity;

		@FindBy(id = "hzWeight0")
		private WebElement accessorialsWeight;

		@FindBy(id = "hzDescription0")
		private WebElement accessorialsDescription;

		@FindBy(xpath="(//a[contains(text(), 'Contact Me')])[1]")
        private WebElement contactMe;
		
		@FindBy(xpath="//a[@id='Applications']")
		private WebElement applicationsLink;
		
		@FindBy(xpath="//p[contains(text(), 'Success: Using the information you provided, we will contact you shortly. In the meantime, feel free to give us a call at (800) 645-3952 if you have any questions.')]")
        private WebElement confirmationMessage;
	
	/******************************METHODS***********************************/
	
	
	
	public void switchToFrame() {
		testUtil.init(this);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
	}
	
	public void enterTLRateQuoteOriginAddress(String originZip) {
		logger.info("Enter TL Rate Quote Origin Address: "+originZip);
		testUtil.init(this);
		testUtil.setExplicitWait(zipOrigin, 90);
		zipOrigin.sendKeys(originZip);
		testUtil.setExplicitWait(originSuggestion, 30);
		originSuggestion.click();
	}
	
	public void enterTLRateQuoteDestinationAddress(String destZip) {
		logger.info("Enter TL Rate Quote Destination Address: "+destZip);
		testUtil.init(this);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		testUtil.setExplicitWait(zipDestination, 30);
		zipDestination.sendKeys(destZip);
		testUtil.setHardWait(2000);
		destinationSuggestion.click();
		testUtil.setHardWait(2000);
	}
	
	public void enterTLRateQuotePickupDate(String date) {
		logger.info("Enter pickup date: " + date);
		testUtil.init(this);
		testUtil.setExplicitWait(pickupDate, 20);
		pickupDate.click();
		pickupDate.sendKeys(date);
	}
	
	public void SelectEquipmentType(String Type) throws InterruptedException {
		logger.info("Enter equipment Type");
		testUtil.init(this);
		testUtil.clickElementJavascript(equipmentType);
		testUtil.setHardWait(2000);
	
		driver.findElement(By.xpath("//option[contains(text(),'" + Type + "')]")).click(); 
	}

	public void enterTotalWeight(String wgt) {
		logger.info("Enter total weight: " + wgt);
		testUtil.init(this);
		weight.sendKeys(wgt);
	}
	
	public void clickOnTLRateQuoteSubmitBtn() {
		logger.info("Click on TL rate quote submit button");
		testUtil.init(this);
		testUtil.setExplicitWait(submitBtn, 120);
		testUtil.clickElementJavascript(submitBtn);
		testUtil.setHardWait(2000);
	}
	
	public void clickOnTruckloadCharges() {
		logger.info("Click on *Estes Truckload* service level Charges");
		testUtil.init(this);
		testUtil.setExplicitWait(charges, 60);
		testUtil.clickElementJavascript(charges);
		testUtil.setHardWait(2000);
	}
	
	public void verifyTLPageDisplayed() {
		logger.info("Verify TL page displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		boolean isDisplayed = pageTitle.isDisplayed();
		TestUtil.verifyTrue(isDisplayed);
	}
	
	public String recordQuoteNumber() {
		logger.info("Record Quote number");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String quoteNumberTxt = quoteNumber.getText();
		System.out.println("Quote NO: "+quoteNumberTxt);
		String[] quoteNo = quoteNumberTxt.split(":");
		return quoteNo[1].trim();
	}
	

	public void clickOnIagreeCheckbox() {
		logger.info("Click on I have read and agree to the disclaimers and Terms of Service below check box");
		testUtil.init(this);
		testUtil.setExplicitWait(checkbox, 60);
		testUtil.clickElementJavascript(checkbox);
		testUtil.setHardWait(2000);
	}
	
	public void verifyBookShipmentButtonEnabled() {
		logger.info("Verify the *Book Shipment* button become active");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Assert.assertEquals(shipmentBtn.isEnabled(), true);
	}
	
	public void clickOnBookShipmentButton() {
		logger.info("Click on book shipment button");
		testUtil.init(this);
		testUtil.setExplicitWait(shipmentBtn, 60);
		testUtil.clickElementJavascript(shipmentBtn);
		testUtil.setHardWait(2000);
	}
	

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
				.findElement(By.xpath("//select[@id='origin_appointmentEndTimeHH']//option[@value='" + avlHour + "']"));
		
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Minutes as: " + avlMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupEndMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//select[@id='origin_appointmentEndTimeMM']//option[@value='" + avlMnts + "']"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Am/Pm as: " + avlAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupEndAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//select[@id='origin_appointmentEndAmPm']//option[@value='" + avlAmPm + "']"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}
	
	
	
	public void selectPickupStartTime(String avlHour, String avlMnts, String avlAmPm) {
		logger.info("Select pickup start time as: " + avlHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupByHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//select[@id='origin_appointmentStartTimeHH']//option[@value='" + avlHour + "']"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Minutes as: " + avlMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupByMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//select[@id='origin_appointmentStartTimeMM']//option[@value='" + avlMnts + "']"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Am/Pm as: " + avlAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		pickupByAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//select[@id='origin_appointmentStartAmPm']//option[@value='" + avlAmPm + "']"));
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
				.findElement(By.xpath("//select[@id='destination_appointmentStartTimeHH']//option[@value='" + closeHour + "']"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Minutes as: " + closeMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryByMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//select[@id='destination_appointmentStartTimeMM']//option[@value='" + closeMnts + "']"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Am/Pm as: " + closeAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryByAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//select[@id='destination_appointmentStartAmPm']//option[@value='" + closeAmPm + "']"));
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
				.findElement(By.xpath("//select[@id='destination_appointmentEndTimeHH']//option[@value='" + closeHour + "']"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Minutes as: " + closeMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryToMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//select[@id='destination_appointmentEndTimeMM']//option[@value='" + closeMnts + "']"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Am/Pm as: " + closeAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		deliveryToAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//select[@id='destination_appointmentEndAmPm']//option[@value='" + closeAmPm + "']"));
		
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
	
		driver.findElement(By.xpath("//select[@id='commodity_type']//option[@value='" + Type + "']")).click(); 
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
		reference.sendKeys(ref);
	}
	
	public void enterPONumber(String po) {
		logger.info("Enter PO number in reference field");
		testUtil.init(this);
		testUtil.setExplicitWait(poNum, 90);
		poNum.clear();
		poNum.sendKeys(po);
		System.out.println("PO number is "+ po);
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
	
	public void clickOnSubmitButton() {
		logger.info("Click on submit button");
		testUtil.init(this);
		testUtil.setExplicitWait(submit, 20);
		testUtil.setHardWait(2000);
		submit.click();
		testUtil.setHardWait(2000);
		
	}
	
	public void verifySuccessMessageIsDisplayed() {
		logger.info("Verify the success message");
		testUtil.init(this);
		boolean verifyMessage = successMessage.isDisplayed();
		Assert.assertEquals(verifyMessage, true);
		testUtil.setHardWait(2000);
	}
	
	public String recordPRONum() {
		logger.info("Capture PRO number" );
		testUtil.init(this);

		testUtil.setExplicitWait(
				driver.findElement(By.id("quote_number")),
				2000);
		String quoteNumber = driver
				.findElement(By.id("quote_number")).getText().substring(16).trim();
		System.out.println("Quote Number is :" + quoteNumber);
		return quoteNumber;
	}
	
	public void verifySuccessMessageDisplays(String expMsg) {
		logger.info("Verify success message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
	       String actualMsg = driver.findElement(By.xpath("//div[@class='successMessage']")).getText();
           System.out.println(actualMsg);
           assertEquals(actualMsg, expMsg);
    }
	

	
	public String recordQuoteExpiration() {
		logger.info("Record quote expiration");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		String expNum = driver
				.findElement(By.xpath("//*[@id='vtl_qds_container']/div[1]/fieldset/div/div/div[5]/div[2]")).getText().substring(0, 12);
		System.out.println("PO NO: "+poNum);
		return expNum;
	}
	
	public void clickOnHazardousMaterialsCheckBox() {
		logger.info("Click on Hazardous Materials CheckBox");
		testUtil.init(this);
		testUtil.setExplicitWait(hazardCheckBx, 120);
		testUtil.clickElementJavascript(hazardCheckBx);
		testUtil.setHardWait(2000);
	}
	
	public void verifyHazmatMessageDisplays(String expMsg) {
		logger.info("Verify attention message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
	       String actualMsg = driver.findElement(By.xpath("//div[@class='exceptionMessage']")).getText();
           System.out.println(actualMsg);
           assertEquals(actualMsg, expMsg);
    }
	
	public void verifyContactMeByServiceLevel(String serviceLvl) {
		logger.info("Verifying contact me link by service level");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+serviceLvl+"')]//parent::tr/td[4]"));
		testUtil.setExplicitWait(ele, 60);
		String contactMe = ele.getText().trim();
		Assert.assertTrue(contactMe.equalsIgnoreCase("Contact Me"));
		testUtil.setHardWait(1000);
	}

	public void verifyEstimatedDeliveryDateIsBlank() {
		logger.info("Verify Estimated Delivery Date Is blank");
		testUtil.init(this);

		String date = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();

		Assert.assertEquals(date.isEmpty(), true);

	}
	
	public void clickOnTankerEndorsementCheckBox() {
		logger.info("Click on Tanker Endorsement Needed CheckBox");
		testUtil.init(this);
		testUtil.setExplicitWait(tankerCheckBx, 120);
		testUtil.clickElementJavascript(tankerCheckBx);
		testUtil.setHardWait(2000);
	}

	public void enterAccessorialsUNNumber(String num) {
		logger.info("Enter UN number: " + num);
		testUtil.init(this);
		accessorialsUNNumber.sendKeys(num);
	}

	public void enterAccessorialsQuantity(String quantity) {
		logger.info("Enter quantity: " + quantity);
		testUtil.init(this);
		accessorialsQuantity.sendKeys(quantity);
	}

	public void enterAccessorialsWeight(String wgt) {
		logger.info("Enter weight: " + wgt);
		testUtil.init(this);
		accessorialsWeight.sendKeys(wgt);
	}

	public void enterAccessorialsDescription(String description) {
		logger.info("Enter description: " + description);
		testUtil.init(this);
		accessorialsDescription.sendKeys(description);
	}

	public void selectPackagingCode(String code) throws InterruptedException {
		logger.info("Enter packaging code");
		testUtil.init(this);
		testUtil.clickElementJavascript(accessorialsPackagingCode);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//option[contains(text(),'" + code + "')]")).click();
	}

	public void selectCommodityType(String type) throws InterruptedException {
		logger.info("Enter equipment Type");
		testUtil.init(this);
		testUtil.clickElementJavascript(accessorialsCommodityType);
		testUtil.setHardWait(2000);

		driver.findElement(By.xpath("//option[contains(text(),'" + type + "')]")).click();
	}
	public void clickContactMe() {
        testUtil.init(this);
        logger.info("Click Contact Me"); 
        contactMe.click(); 
}
	
	public void verifyConfirmationMessage() {
        testUtil.init(this);
        logger.info("Verify that 'ContactMe message has been successfully selected and confirmation message received");
        assertTrue(confirmationMessage.isDisplayed()); 
	
	
	}
	
	public void clickOnApplicationWithoutFrame() {
		logger.info("Click on Application tab");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		testUtil.setHardWait(1000);
		applicationsLink.click();
	}
	
}
	
