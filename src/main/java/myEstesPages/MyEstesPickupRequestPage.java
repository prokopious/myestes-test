
package myEstesPages;

import static org.testng.Assert.assertEquals;

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

import com.github.javafaker.Faker;

import testBase.TestBase;
import util.TestUtil;

public class MyEstesPickupRequestPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesPickupRequestPage.class);

	/*********** ELEMENTS ***********/
	// Requester Info
	@FindBy(id = "name")
	private WebElement contactName;

	@FindBy(css = "[formgroupname='user'] [formcontrolname='phone']")
	private WebElement phoneNumber;

	@FindBy(css = "[formgroupname='user'] [formcontrolname='phoneExt']")
	private WebElement phoneNumberExt;

	@FindBy(css = "[formgroupname='user'] [formcontrolname='email']")
	private WebElement emailAddress;

	@FindBy(css = "mat-select[formcontrolname='role']")
	private WebElement role;

	// @FindBy(xpath= "//mat-select[@id='mat-select-0']")


	@FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'Shipper')]")
	// @FindBy(css="[ng-reflect-value='SHIPPER']")
	private WebElement shipperRole;

	@FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'Consignee')]")
	private WebElement consigneeRole;

	@FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'Third Party')]")
	private WebElement thirdPartyRole;

	@FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'Other')]")
	private WebElement other;

	// Shipper Info
	@FindBy(css = "[formcontrolname='company']")
	private WebElement companyName;

	@FindBy(css = "[formgroupname='shipper'] [formcontrolname='name']")
	private WebElement shipperContactName;

	@FindBy(css = "[formcontrolname='addressLine1']")
	private WebElement addressLine1;

	@FindBy(css = "[formcontrolname='addressLine2']")
	private WebElement addressLine2;

	@FindBy(css = "[formcontrolname='city']")
	private WebElement cityName;

	@FindBy(css = "[formcontrolname='state']")
	private WebElement stateName;

	@FindBy(css = "[formcontrolname='zip']")
	private WebElement zipCode;

	@FindBy(css = "[formcontrolname='zipExt']")
	private WebElement zipExtension;

	@FindBy(css = "[formgroupname='shipper'] [formcontrolname='phone']")
	private WebElement shipperPhoneNumber;

	@FindBy(css = "[formgroupname='shipper'] [formcontrolname='phoneExt']")
	private WebElement shipperPhoneNumberExt;

	@FindBy(css = "[formgroupname='shipper'] [formcontrolname='email']")
	private WebElement shipperEmailAddress;

	// Pickup details
	@FindBy(css = "[formcontrolname='pickupDate']")
	private WebElement pickupDate;

	@FindBy(xpath = "//*[@id=\"availableByHour\"]")
	private WebElement availableByHour;

	@FindBy(xpath = "//*[@id=\"availableByMinutes\"]")
	private WebElement availableByMinutes;

	@FindBy(xpath = "//*[@id=\"availableByAmPm\"]")
	private WebElement availableByAmPm;

	@FindBy(xpath = "//*[@id=\"closesByHour\"]")
	private WebElement closesByHour;

	@FindBy(xpath = "//*[@id=\"closesByMinutes\"]")
	private WebElement closesByMinutes;

	@FindBy(xpath = "//*[@id=\"closesByAmPm\"]")
	private WebElement closesByAmPm;

	@FindBy(xpath = "//*[@id=\"hookOrDrop\"]//div")
	private WebElement hookOrDropCheckBox;

	@FindBy(xpath = "//*[@id=\"liftgateRequired\"]//div")
	private WebElement liftGateService;

	@FindBy(xpath = "//*[@id=\"noStackPallet\"]//div")
	private WebElement doNotStackPallets;

	// Shipment Information
	@FindBy(css = "[formcontrolname='destinationZipCode']")
	private WebElement destZipCode;

	@FindBy(css = "[formcontrolname='totalPieces']")
	private WebElement totalPiece;

	@FindBy(css = "[formcontrolname='totalWeight']")
	private WebElement totalWeight;

	@FindBy(css = "[formcontrolname='totalSkids']")
	private WebElement totalSkids;

	@FindBy(id = "pickupRequestSubmitButton")
	private WebElement submit;

	@FindBy(css = "[class*='ng-star-inserted alert alert-danger'] span")
	private WebElement alertMessage;

	@FindBy(css = "[class*='mat-error ng-star-inserted']")
	private WebElement maxError;

	@FindBy(css = "[class*='mat-select-placeholder ng-tns-c9-52 ng-star-inserted']")
	private WebElement RequesterAddressBook;

	@FindBy(css = "[class*='mat-option-text']")
	private WebElement addressOptions;

	@FindBy(id = "addressSelect")
	private WebElement accountNumber;

	@FindBy(xpath = "//*[@id=\"useRequestorInfo\"]/label/div")
	private WebElement useRequesterInfoCheckbox;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Hazmat')]")
	private WebElement hazmatCheckbox;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Use My Estes Account Info')]")
	private WebElement useMyEstesAcctInfo;

	@FindBy(css = "[class*='alert-success']")
	private WebElement successMsg;

	@FindBy(xpath = "//*[text()='View Pickup History']")
	private WebElement viewPickupHistory;

	@FindBy(css = "[class='d-none d-lg-flex mat-cell cdk-column-requestNumber mat-column-requestNumber ng-star-inserted']")
	private WebElement firstRequestNo;

	@FindBy(xpath = "//*[@id=\"mat-dialog-title-0\"]")
	private WebElement pickupDetails;

	@FindBy(xpath = "//*[@id=\"pickupRequestFormContainer\"]//mat-card-content[1]//a[text()=' Address Book']")
	private WebElement requesterAddressBook;

	@FindBy(xpath = "//div[@class='mat-radio-label-content'][contains(text(),'Less Than Truckload or Volume/Truckload Pickup')]")
	private WebElement truckloadRadioButton;

	@FindBy(xpath = "//div[@class='mat-radio-label-content'][contains(text(),'Guaranteed/Time Critical')]")
	private WebElement gauranteedRadioButton;

	@FindBy(xpath = "//div[@class='mat-radio-label-content'][contains(text(),'Estes Forwarding Worldwide')]")
	private WebElement estesForwardingRadioButton;

	@FindBy(xpath = "//button[contains(text(),'Add another line')]")
	private WebElement addAnotherLineButton;

	@FindBy(xpath = "//button[contains(text(),'Delete Line')]")
	private WebElement deleteLine;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Freeze')]")
	private WebElement freeze;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Food')]")
	private WebElement food;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Oversize')]")
	private WebElement oversize;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Poison')]")
	private WebElement poison;

	@FindBy(xpath = "//span[@class='mat-checkbox-label'][contains(text(),'Accepted Request')]")
	private WebElement acceptedReq;

	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	private WebElement confirmButton;

	@FindBy(xpath = "//button[contains(text(),'Request Another Pickup')]")
	private WebElement requestAnotherPickup;

	@FindBy(xpath = "//mat-card-content[3]/div[*]/div/mat-checkbox/label/div")
	private WebElement termsOfService;

	// @FindBy(xpath
	// ="//mat-card-content[3]/div[*]/div/div[*]/div/mat-form-field/div/div[*]/div[3]")

	// @FindBy(xpath ="//*[@formarrayname ='shipmentInfo']/div[1]/div/div[1]")
	// private WebElement recentRequestShipperInfo;

	@FindBy(xpath = "//mat-card-content[3]/div[*]/div/div[*]/div/mat-form-field/div/div[*]/div[3]")
	private WebElement recentRequestShipperInfo;

	@FindBy(xpath = "(//label[text()='Contact Name'])[1]")
	private WebElement requesterContactName;

	@FindBy(xpath = "(//label[text()='Phone Number'])[1]")
	private WebElement requesterPhoneNumber;

	@FindBy(xpath = "(//label[text()='Email Address'])[1]")
	private WebElement requesterEmailAddress;

	@FindBy(xpath = "(//label[text()='Contact Name'])[1]")
	private WebElement shipperContName;

	@FindBy(xpath = "(//label[text()='Phone Number'])[2]")
	private WebElement shipperPhoneNo;

	@FindBy(xpath = "(//label[text()='Email Address'])[2]")
	private WebElement shipperEmailAddr;

	@FindBy(xpath = "//label[text()='Company Name']")
	private WebElement shipperCompanyName;

	@FindBy(xpath = "//label[text()='Address']")
	private WebElement shipperAddress;

	@FindBy(xpath = "//label[text()='Appt. Date']")
	private WebElement shipperApptDate;

	@FindBy(xpath = "//label[text()='Appt. Start Time']")
	private WebElement shipperApptStartTime;

	@FindBy(xpath = "//label[text()='Appt. End Time']")
	private WebElement shipperApptEndTime;

	@FindBy(xpath = "//label[text()='Request Number']")
	private WebElement shipperRequestNo;

	@FindBy(xpath = "//label[text()='Dest. ZIP/Postal Code']")
	private WebElement shipperPostalCode;

	@FindBy(css = "[class*='column-requestNumber'] a")
	private List<WebElement> requestNumberList;

	@FindBy(css = "h1 span")
	private WebElement pageTitle;

	@FindBy(xpath = "//div[@formgroupname='shipper']//a[text()=' Address Book']")
	private WebElement shipperAddressBookLink;

	@FindBy(xpath = "//div[@formgroupname='user']//input[@formcontrolname='email']")
	private WebElement requesterEmail;

	@FindBy(xpath = "//mat-table/mat-row/mat-cell[1]/a")
	private WebElement address;
	
    @FindBy(css = "[formcontrolname='pickupZip']")
	private WebElement pickupZip;
    
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'You have requested same-day pickup service and it is currently after 3:00 p.m.')]")
    private WebElement message;
	
	@FindBy(xpath = "//label[text()='Pieces']")
	private WebElement pieces;
	
	@FindBy(xpath = "//label[text()='Weight']")
	private WebElement weight;
	
	@FindBy(xpath = "//label[text()='Skids']")
	private WebElement skids;
	
	@FindBy(xpath = "//label[text()='Hazmat']")
	private WebElement hazmat;
	
	@FindBy(xpath = "//label[text()='Details']")
	private WebElement details;
	
	@FindBy(xpath = "//label[text()='Your Ref. Number']")
	private WebElement refNum;
	
	@FindBy(css = "[formcontrolname='referenceNumber']")
	private WebElement referenceNo;
	

	/*********************** METHODS ************************/

	public void verifyPageTitle() {
		logger.info("Verify page title");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String title = pageTitle.getText().trim();
		Assert.assertEquals(title, "Pickup Request");
	}

	// Requester Info
	public void enterRequesterContactName(String name) {
		logger.info("Enter contact name: " + name);
		testUtil.init(this);
		contactName.clear();
		contactName.sendKeys(name);
	}

	// Enter random contact name
	public void enterRandomContactName() {
		logger.info("Enter Random contact name");
		testUtil.init(this);
		Faker faker = new Faker();
		contactName.clear();
		contactName.sendKeys(faker.name().firstName());
	}

	// Enter random phone number
	public void enterRandomRequesterPhoneNumber() {
		logger.info("Enter Random Phone number");
		testUtil.init(this);
		Faker faker = new Faker();
		phoneNumber.clear();
		phoneNumber.sendKeys(faker.phoneNumber().phoneNumber());
	}

	public void enterRequesterPhoneNumber(String phone) {
		logger.info("Enter phone number: " + phone);
		testUtil.init(this);
		phoneNumber.sendKeys(phone);
	}

	public void enterRequesterPhoneExtension(String extension) {
		logger.info("Enter Phone Extension: " + extension);
		testUtil.init(this);
		phoneNumberExt.sendKeys(extension);
	}

	public void enterRequesterEmail(String email) {
		logger.info("Enter Email address: " + email);
		testUtil.init(this);
		emailAddress.clear();
		emailAddress.sendKeys(email);
	}

	// Enter Random RequesterEmail()
	public void enterRandomRequesterEmail() {
		logger.info("Enter Random contact name");
		testUtil.init(this);
		Faker faker = new Faker();
		emailAddress.clear();
		emailAddress.sendKeys(faker.internet().emailAddress());
	}

	public void selectShipperRole() {
		logger.info("Select requester Role: " + role);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		role.click();
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(shipperRole, 30);
		shipperRole.click();
		testUtil.setHardWait(2000);
	}

	// Select Role as Consignee
	public void selectConsigneeRole() {
		logger.info("Select requester Role: " + role);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		role.click();
		testUtil.setExplicitWait(consigneeRole, 30);
		consigneeRole.click();
		testUtil.setHardWait(2000);
	}

	// Select Role as Third party
	public void selectThirdPartyRole() {
		logger.info("Selectrequester Role: " + role);
		testUtil.init(this);
		//testUtil.setExplicitWait(role, 60);
		
		WebDriverWait wait= new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("mat-select[formcontrolname='role']")));
		testUtil.clickElementJavascript(role);
		/*for (int i = 0; i <= 2; i++) {
			try {
				testUtil.clickElementJavascript(role);
				break;
			} catch (Exception e) {
				System.out.println("Timeout exception  occured");
			}*/
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(thirdPartyRole, 60);
		testUtil.clickElementJavascript(thirdPartyRole);
		testUtil.setHardWait(2000);
	}

	// Select Role as Other
	public void selectOtherRole() {
		logger.info("Select requester Role: " + role);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		role.click();
		testUtil.setExplicitWait(other, 60);
		other.click();
		testUtil.setHardWait(2000);
	}

	// Shipper Info
	public void enterCompanyName(String company) {
		logger.info("Enter shipper company name: " + company);
		testUtil.init(this);
		companyName.sendKeys(company);
	}

	public void enterShipperContactName(String Name) {
		logger.info("Enter shipper Contact Name: " + contactName);
		testUtil.init(this);
		shipperContactName.clear();
		shipperContactName.click();
		shipperContactName.sendKeys(Name);
	}

	// Enter random Shipper contact name
	public void enterRandomShipperContactName() {
		logger.info("Enter Random user name for random shipper");
		testUtil.init(this);
		Faker faker = new Faker();
		shipperContactName.click();
		shipperContactName.clear();
		shipperContactName.sendKeys(faker.name().username());
	}

	public void enterAddressLine1(String addressLine) {
		logger.info("Enter Address Line 1: " + addressLine);
		testUtil.init(this);
		addressLine1.sendKeys(addressLine);
	}

	public void enterAddressLine2(String addressLine) {
		logger.info("Enter Address Line 2: " + addressLine);
		testUtil.init(this);
		addressLine2.sendKeys(addressLine);
	}

	public void enterCityName(String city) {
		logger.info("Enter City: " + city);
		testUtil.init(this);
		cityName.sendKeys(city);
	}

	public void enterStateName(String state) {
		logger.info("Entering State: " + state);
		testUtil.init(this);
		stateName.sendKeys(state);
	}

	public void enterZipCode(String zip) {
		logger.info("Enter Zip code: " + zip);
		testUtil.init(this);
		zipCode.sendKeys(zip);
	}

	public void enterZipExt(String ext) {
		logger.info("Entering zip code: " + ext);
		testUtil.init(this);
		zipExtension.sendKeys(ext);
	}

	public void enterShipperPhoneNumber(String phone) {
		logger.info("Enter phone number: " + phone);
		testUtil.init(this);
		shipperPhoneNumber.sendKeys(phone);
	}

	public void enterRandomShipperPhoneNumber() {
		logger.info("Enter phone number");
		testUtil.init(this);
		Faker faker = new Faker();
		shipperPhoneNumber.clear();
		shipperPhoneNumber.sendKeys(faker.phoneNumber().phoneNumber());
	}

	public void enterShipperPhoneExtension(String extension) {
		logger.info("Enter phone extension: " + extension);
		testUtil.init(this);
		shipperPhoneNumberExt.clear();
		shipperPhoneNumberExt.sendKeys(extension);
	}

	public void enterShipperEmail(String email) {
		logger.info("Enteri email address: " + email);
		testUtil.init(this);
		shipperEmailAddress.sendKeys(email);
	}

	public void enterRandomShipperEmail() {
		logger.info("Enter email address");
		testUtil.init(this);
		Faker faker = new Faker();
		shipperEmailAddress.clear();
		shipperEmailAddress.sendKeys(faker.internet().emailAddress());
	}

	public void enterPickupDate(String date) {
		logger.info("Enter pickup date: " + date);
		testUtil.init(this);
		pickupDate.sendKeys(date);
	}

	public void enterDestinationZip(String zip) {
		logger.info("Enter Destination Zip: " + zip);
		testUtil.init(this);
		destZipCode.sendKeys(zip);
	}

	public void enterTotalPiece(String pieces) {
		logger.info("Enter total piece: " + pieces);
		testUtil.init(this);
		totalPiece.sendKeys(pieces);
	}

	public void enterTotalWeight(String weight) {
		logger.info("Enterg total weight: " + weight);
		testUtil.init(this);
		totalWeight.sendKeys(weight);
	}

	public void clickOnSubmit() {
		logger.info("Click on Submit");
		testUtil.init(this);
		testUtil.setExplicitWait(submit, 120);
		testUtil.clickElementJavascript(submit);
		//submit.click();
		testUtil.setHardWait(2000);
	}

	public String captureAlertMessage() {
		logger.info("Capture displayed alert message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertMessage, 60);
		return testUtil.getTextOfElement(alertMessage);
	}

	public void enterAccountNumber(String acctNo) {
		logger.info("Enter Account#: " + acctNo);
		testUtil.init(this);
		accountNumber.sendKeys(acctNo);
	}

	public String captureMaxErrorMessage() {
		logger.info("Capture displayed error message");
		testUtil.init(this);
		testUtil.setExplicitWait(maxError, 30);
		return testUtil.getTextOfElement(maxError);
	}

	public void selectRequesterAddressFromAddressBook(String address) {
		logger.info("Select Requester Address from address Book");
		testUtil.init(this);
		testUtil.setExplicitWait(requesterAddressBook, 30);
		testUtil.clickElementJavascript(requesterAddressBook);
		// requesterAddressBook.click();
		testUtil.setExplicitWait(
				driver.findElement(By.xpath(
						"//address-selector-dialog/mat-card/mat-card-content/mat-table//a[text()='" + address + "']")),
				30);
		driver.findElement(
				By.xpath("//address-selector-dialog/mat-card/mat-card-content/mat-table//a[text()='" + address + "']"))
				.click();
	}

	public void clickOnUseRequesterInfoCheckbox() {
		logger.info("Click on use Requester info check box");
		testUtil.init(this);
		testUtil.setExplicitWait(useRequesterInfoCheckbox, 30);
		useRequesterInfoCheckbox.click();
	}

	public void verifyConfirmationMsg() {
		logger.info("Verify presence of confirmation message");
		testUtil.init(this);
		testUtil.setExplicitWait(successMsg, 30);
		Assert.assertEquals(successMsg.isDisplayed(), true);
	}

	public void clickOnViewPickupHistory() {
		logger.info("Click on View Pickup History tab");
		testUtil.init(this);
		testUtil.assetWait(null, viewPickupHistory, 15, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(viewPickupHistory);
		
	}

	public String capturAndSelectFirstRequestNo() {
		logger.info("Select First listed Request Number");
		testUtil.init(this);
		testUtil.setExplicitWait(firstRequestNo, 30);
		String reqNum = firstRequestNo.getText();
		testUtil.clickElementJavascript(
				driver.findElement(By.xpath("//app-pickup-history//a[text()='" + reqNum + "']")));
		System.out.println("Selected Request Numer:" + reqNum);

		return reqNum;
	}

	public void verifyPickUpDetails(String ReqNum) {
		logger.info("Verfy pickup details displayed for Request# " + ReqNum);
		testUtil.init(this);

//		testUtil.setExplicitWait(driver.findElement(By.xpath("//*[@id=\"mat-dialog-title-0\"]")), 60);
//		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"mat-dialog-title-0\"]")).isDisplayed(), true);
		testUtil.setHardWait(2000);
//		String actual = driver.findElement(By.cssSelector("[class='col-8']")).getText();
		String actual = driver.findElement(By.xpath("//*[@id='main']/app-home/app-pickup-request/div/mat-card/mat-card-content/table[2]/tbody/tr[1]/td[1]/span")).getText();
		System.out.println(actual);
		testUtil.verifyText(actual, ReqNum);
	}

	public void clickOnHazmat() {
		logger.info("Click on Hazmat checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(hazmatCheckbox, 30);
		hazmatCheckbox.click();
	}

	public void selectShipmentType(String shipType) {
		logger.info("Select Shipment type: " + shipType);
		testUtil.init(this);

		if (shipType == "TruckloadPrVolume/Truckload") {
			truckloadRadioButton.click();
			;
		} else if (shipType == "Guaranteed/TimeCritical") {
			gauranteedRadioButton.click();
		} else {
			estesForwardingRadioButton.click();
		}
	}

	public void verifyReqFields(String sectionName) {
		logger.info("Verify required field in " + sectionName + " Info section");
		testUtil.init(this);

		String objProp = null;
		int expectedCount = 0;
		switch (sectionName) {
		case "RequesterInfo":
			objProp = "//mat-card-content[1]/div/div[1]/div[1]/div[*]/div/mat-form-field//mat-error";
			expectedCount = 3;
		case "ShipperInfo":
			objProp = "//mat-card-content[2]/div[5]/div/div[*]/div/mat-form-field/div//mat-error";
			expectedCount = 8;
			break;
		case "PickupDetails":
			objProp = "//mat-card-content[2]/div[2]/div[1]/mat-form-field/div/div[2]/div/mat-error";
			expectedCount = 1;
			break;
		case "ShipmentDetails":
			objProp = "//mat-card-content[3]/div[1]/div/div[2]/div[1]/div[*]/mat-form-field/div/div[2]/div/mat-error";
			expectedCount = 3;
			break;
		case "Hazmat":
			objProp = "//mat-card-content[3]/div[*]/div/div[*]/div[4]/div[*]/mat-form-field//div[*]//mat-error";
			expectedCount = 1;
			break;
		case "GauranteedTerms":
			objProp = "//mat-card-content[3]/div[3]/div/mat-error";
			expectedCount = 1;
			break;
		}

		List<WebElement> ErrMsg = driver.findElements(By.xpath(objProp));
		testUtil.setHardWait(1000);
		Assert.assertEquals(ErrMsg.size(), expectedCount);
	}

	public void selectAvailableBy(String avlHour, String avlMnts, String avlAmPm) {
		logger.info("Select Available Hour as: " + avlHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		availableByHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlHour + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Minutes as: " + avlMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		availableByMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlMnts + "')]"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Available Am/Pm as: " + avlAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		availableByAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + avlAmPm + "')]"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}

	public void selectClosesBy(String closeHour, String closeMnts, String closeAmPm) {
		logger.info("Select Closes Hour as: " + closeHour);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		closesByHour.click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeHour + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Minutes as: " + closeMnts);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		closesByMinutes.click();
		WebElement ele1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeMnts + "')]"));
		testUtil.setExplicitWait(ele1, 30);
		ele1.click();
		testUtil.setHardWait(2000);

		logger.info("Select Closes Am/Pm as: " + closeAmPm);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		closesByAmPm.click();
		WebElement ele2 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + closeAmPm + "')]"));
		testUtil.setExplicitWait(ele2, 30);
		ele2.click();
		testUtil.setHardWait(2000);

	}

	public void clickOnHookOrDrop() {
		logger.info("Click on Hoor/Drop checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(hookOrDropCheckBox, 30);
		hookOrDropCheckBox.click();
	}

	public void clickOnLiftGateService() {
		logger.info("Click on Lift Gate Service checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(liftGateService, 30);
		liftGateService.click();
	}

	public void clickOnDoNotStckPallets() {
		logger.info("Click on donot stack pallets checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(doNotStackPallets, 30);
		doNotStackPallets.click();
	}

	public void clickOnAddAnotherLineButton() {
		logger.info("Click on Add another line button");
		testUtil.init(this);
		testUtil.setExplicitWait(addAnotherLineButton, 30);
		addAnotherLineButton.click();
	}

	public void clickOnFreeze() {
		logger.info("Click on Freeze checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(freeze, 30);
		freeze.click();
	}

	public void clickOnFood() {
		logger.info("Click on Food checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(food, 30);
		food.click();
	}

	public void clickOnOversize() {
		logger.info("Click on Oversize checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(oversize, 30);
		oversize.click();
	}

	public void clickOnPoison() {
		logger.info("Click on Poison checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(poison, 30);
		poison.click();
	}

	public void clickOnAcceptedReq() {
		logger.info("Click on Accepted Request");
		testUtil.init(this);
		testUtil.setExplicitWait(acceptedReq, 30);
		acceptedReq.click();
	}

	public void enterDestinationZipByLine(int line, String zip) {
		logger.info("Enter destination zip: " + zip + " in line " + line);
		testUtil.init(this);
		List<WebElement> destination = driver.findElements(By.cssSelector("[formcontrolname='destinationZipCode']"));
		destination.get(line).clear();
		destination.get(line).sendKeys(zip);
	}

	public void enterTotalPieceByLine(int line, String pieces) {
		logger.info("Enter total piece: " + pieces + " in line " + line);
		testUtil.init(this);
		List<WebElement> totalPiece = driver.findElements(By.cssSelector("[formcontrolname='totalPieces']"));
		totalPiece.get(line).clear();
		totalPiece.get(line).sendKeys(pieces);
	}

	public void enterTotalWeightByLine(int line, String weight) {
		logger.info("Enter Total Weight: " + weight + " in line " + line);
		testUtil.init(this);
		List<WebElement> totalWeight = driver.findElements(By.cssSelector("[formcontrolname='totalWeight']"));
		totalWeight.get(line).clear();
		totalWeight.get(line).sendKeys(weight);
	}

	public void clickOnAcceptedReqByLine(int line) {
		logger.info("Click on Accepted Request");
		testUtil.init(this);
		List<WebElement> accReq = driver
				.findElements(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'Accepted Request')]"));
		accReq.get(line).click();
	}

	public void clickOnHazmatByLine(int line) {
		logger.info("Click on Hazmat for line " + line);
		testUtil.init(this);
		List<WebElement> hazmat = driver
				.findElements(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'Hazmat')]"));
		hazmat.get(line).click();
	}

	public void clickOnFreezeByLine(int line) {
		logger.info("Click on Freeze checkbox");
		testUtil.init(this);
		List<WebElement> freeze = driver
				.findElements(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'Freeze')]"));
		freeze.get(line).click();
	}

	public void clickOnFoodByLine(int line) {
		logger.info("Click on food checkbox");
		testUtil.init(this);
		List<WebElement> food = driver
				.findElements(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'Food')]"));
		food.get(line).click();
	}

	public void clickOnOverSizeByLine(int line) {
		logger.info("Click on Oversize checkbox");
		testUtil.init(this);
		List<WebElement> oversize = driver
				.findElements(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'Oversize')]"));
		oversize.get(line).click();
	}

	public void clickOnPoisonByLine(int line) {
		logger.info("Click on food checkbox");
		testUtil.init(this);
		List<WebElement> poison = driver
				.findElements(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'Poison')]"));
		poison.get(line).click();
	}

	public void clickOnTruckloadRadioButtonByLine(int line) {
		logger.info("Click on Truckload radio button");
		testUtil.init(this);
		List<WebElement> truckload = driver.findElements(By.xpath(
				"//div[@class='mat-radio-label-content'][contains(text(),'Less Than Truckload or Volume/Truckload Pickup')]"));
		truckload.get(line).click();
	}

	public void clickOnGauranteedRadioButtonByLine(int line) {
		logger.info("Click on Gauranteed radio button");
		testUtil.init(this);
		List<WebElement> gauranteed = driver.findElements(
				By.xpath("//div[@class='mat-radio-label-content'][contains(text(),'Guaranteed/Time Critical')]"));
		gauranteed.get(line).click();
	}

	public void clickOnEstesForwardingRadioButtonByLine(int line) {
		logger.info("Click on Estes Forwarding radio button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		List<WebElement> estesForwarding = driver.findElements(
				By.xpath("//div[@class='mat-radio-label-content'][contains(text(),'Estes Forwarding Worldwide')]"));
		estesForwarding.get(line).click();
	}

	public void clickOnTermsOfService() {
		logger.info("Click on Terms Of Service");
		testUtil.init(this);
		testUtil.setExplicitWait(termsOfService, 30);
		termsOfService.click();

	}

	public void clickOnDeleteLineByLine(int line) {
		logger.info("Click on Delete line");
		testUtil.init(this);
		List<WebElement> deleteLine = driver.findElements(By.xpath("//button[contains(text(),'Delete Line')]"));
		deleteLine.get(line).click();
	}

	public void clickOnConfirmButton() {
		logger.info("Click on Confirmation button");
		testUtil.init(this);
		testUtil.setExplicitWait(confirmButton, 30);
		confirmButton.click();
		testUtil.setHardWait(5000);
	}

	public void clickOnRequestAnotherPickup() {
		logger.info("Click on request another pickup");
		testUtil.init(this);
		testUtil.setExplicitWait(requestAnotherPickup, 30);
		requestAnotherPickup.click();
	}

	// Select prior pickup request from shipment information
	public void selectPriorPickupRequest(String priorReq) {
		logger.info("Click on Recent Request");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(recentRequestShipperInfo, 50);
		recentRequestShipperInfo.click();
		testUtil.setHardWait(2000);
		// driver.findElement(By.xpath("//mat-card-content[3]/div[*]//div/mat-form-field//div[*]/div[3]/mat-select/div/div[1]/span")).click();
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + priorReq + "')]"));
		testUtil.setExplicitWait(ele, 30);
		ele.click();
	}

	public void verifyShipmentInfo(String dest, String pieces, String weight) {
		logger.info("Verify records displayed for the selected data");
		testUtil.init(this);

		TestUtil.verifyText(
				driver.findElement(By.xpath("//input[@formcontrolname='destinationZipCode']")).getAttribute("value"),
				dest);
		TestUtil.verifyText(
				driver.findElement(By.xpath("//input[@formcontrolname='totalPieces']")).getAttribute("value"), pieces);
		TestUtil.verifyText(
				driver.findElement(By.xpath("//input[@formcontrolname='totalWeight']")).getAttribute("value"), weight);

	}

	public void enterSpecialInstructionsBy(int line, String instr) {
		logger.info("Enter Special instruction");
		testUtil.init(this);
		List<WebElement> specialInstruction = driver
				.findElements(By.xpath("//*[@formcontrolname=\"specialInstructions\"]"));
		specialInstruction.get(line).sendKeys(instr);
		testUtil.setHardWait(1000);
	}

	public void clickOnUseMyEstesAccountInfo() {
		logger.info("Click o Use my estes account info");
		testUtil.init(this);
		useMyEstesAcctInfo.click();
		testUtil.setHardWait(2000);
	}

	public void verifyName(String name) {
		logger.info("Verify Rate Quote Name from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String contactNme = (String) js.executeScript("return document.getElementById('name').value");
		Assert.assertEquals(contactNme, name);
	}

	public void verifyPhoneNumberFromContactInformation(String phone) {
		logger.info("Verify Rate Quote Phone Number from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String phoneNum = (String) js.executeScript("return document.getElementById('mat-input-1').value");
		String phNumber = phoneNum.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
		Assert.assertEquals(phNumber, phone);
	}

	public void verifyEmailAddressFromContactInformation(String email) {
		logger.info("Verify Rate Quote Email Address from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String emailAddr = (String) js.executeScript("return document.getElementById('mat-input-3').value");
		Assert.assertEquals(emailAddr, email);
	}

	public void verifyZipFromContactInformation(String zip) {
		logger.info("Verify Rate Quote ZIP from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String zipcode = (String) js.executeScript("return document.getElementById('mat-input-11').value");
		Assert.assertEquals(zipcode, zip);
	}

	public void verifyDestZipFromContactInformation(String zip) {
		logger.info("Verify Rate Quote Destination ZIP from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String zipcode = (String) js.executeScript("return document.getElementById('mat-input-16').value");
		Assert.assertEquals(zipcode, zip);
	}

	public void verifyPiecesFromContactInformation(String pieces) {
		logger.info("Verify Rate Quote Pieces from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String piecesNo = (String) js.executeScript("return document.getElementById('mat-input-18').value");
		Assert.assertEquals(piecesNo, pieces);
	}

	public void verifyWeightFromContactInformation(String weight) {
		logger.info("Verify Rate Quote Weight from Contact Information");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String weightNo = (String) js.executeScript("return document.getElementById('mat-input-19').value");
		Assert.assertEquals(weightNo, weight);
	}

	public void verifyRequestInfoSection() {
		logger.info("verify error message");
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0, 450)");
		testUtil.verifyAndPrintWebTableData("//body//mat-card-content[2]");

	}

	public void verifyRequesterContactName() {
		logger.info("Verify requester contact name");
		testUtil.init(this);
		Assert.assertEquals(requesterContactName.isDisplayed(), true);
	}

	public void verifyRequesterPhoneNumber() {
		logger.info("Verify requester phone number");
		testUtil.init(this);
		Assert.assertEquals(requesterPhoneNumber.isDisplayed(), true);
	}

	public void verifyRequesterEmailAddress() {
		logger.info("Verify requester Email Address");
		testUtil.init(this);
		Assert.assertEquals(requesterEmailAddress.isDisplayed(), true);
	}

	public void verifyShipperContactName() {
		logger.info("Verify Shipper contact name");
		testUtil.init(this);
		Assert.assertEquals(shipperContName.isDisplayed(), true);
	}

	public void verifyShipperPhoneNumber() {
		logger.info("Verify Shipper phone number");
		testUtil.init(this);
		Assert.assertEquals(shipperPhoneNo.isDisplayed(), true);
	}

	public void verifyShipperEmailAddress() {
		logger.info("Verify Shipper Email Address");
		testUtil.init(this);
		Assert.assertEquals(shipperEmailAddr.isDisplayed(), true);
	}

	public void verifyShipperCompanyName() {
		logger.info("Verify Shipper Company name");
		testUtil.init(this);
		Assert.assertEquals(shipperCompanyName.isDisplayed(), true);
	}

	public void verifyShipperAddress() {
		logger.info("Verify Shipper Address");
		testUtil.init(this);
		Assert.assertEquals(shipperAddress.isDisplayed(), true);
	}

	public void verifyShipperApptDate() {
		logger.info("Verify Shipper Appt Date");
		testUtil.init(this);
		Assert.assertEquals(shipperApptDate.isDisplayed(), true);
	}

	public void verifyShipperApptStartTime() {
		logger.info("Verify Shipper Appt Start Time");
		testUtil.init(this);
		Assert.assertEquals(shipperApptStartTime.isDisplayed(), true);
	}

	public void verifyShipperApptEndTime() {
		logger.info("Verify Shipper Appt End Time");
		testUtil.init(this);
		Assert.assertEquals(shipperApptEndTime.isDisplayed(), true);
	}

	public void verifyShipperRequestNumber() {
		logger.info("Verify Shipper Request Number");
		testUtil.init(this);
		Assert.assertEquals(shipperRequestNo.isDisplayed(), true);
	}

	public void verifyShipperPostalCode() {
		logger.info("Verify Shipper Postal Code");
		testUtil.init(this);
		Assert.assertEquals(shipperPostalCode.isDisplayed(), true);
	}

	// UPDATED ON 11/26/2019@3:30

	public void verifyRequestNoListedInPickupHistory(String requestNo) {
		boolean flagVal = false;
		logger.info("Validate request number is listed in Pickup Request history page");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		for (int i = 0; i < requestNumberList.size(); i++) {
			String reqNumber = requestNumberList.get(i).getText().trim();
			if (requestNo.contains(reqNumber)) {
				flagVal = true;
				break;
			}
		}
		if (flagVal == false) {
			try {
				throw (new Exception(new String("Unable to find Request number: " + requestNo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void selectShipperAddressFromAddressBook(String address) {
		logger.info("Select Shipper Address from address Book");
		testUtil.init(this);
		testUtil.setExplicitWait(shipperAddressBookLink, 30);
		testUtil.clickElementJavascript(shipperAddressBookLink);
		testUtil.setExplicitWait(
				driver.findElement(By.xpath(
						"//address-selector-dialog/mat-card/mat-card-content/mat-table//a[text()='" + address + "']")),
				30);
		driver.findElement(
				By.xpath("//address-selector-dialog/mat-card/mat-card-content/mat-table//a[text()='" + address + "']"))
				.click();
	}

	public void clearRequesterEmail() {
		logger.info("Clear Requester email");
		testUtil.init(this);
		int val = requesterEmail.getAttribute("value").length();
		for (int i = 0; i <= val; i++) {
			testUtil.setHardWait(100);
			requesterEmail.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void SelectFirstAddressFromShipperAddressBook() {
		logger.info("Click on shipper address book modal and select first address");
		testUtil.init(this);
		testUtil.setExplicitWait(shipperAddressBookLink, 30);
		testUtil.clickElementJavascript(shipperAddressBookLink);
		testUtil.setExplicitWait(address, 30);
		address.click();

	}

	public void selectFirstAddressFromRequesterAddressBook() {
		logger.info("Click on requester address book modal and select first address");
		testUtil.init(this);
		testUtil.setExplicitWait(requesterAddressBook, 120);
		testUtil.clickElementJavascript(requesterAddressBook);
		testUtil.setExplicitWait(address, 90);
		testUtil.clickElementJavascript(address);
	//	address.click();

	}
	
	public void enterPickUpZip(String zip) {
		logger.info("Enter pickup zip code");
		testUtil.init(this);
		pickupZip.clear();
		pickupZip.click();
		testUtil.setHardWait(500);
		pickupZip.sendKeys(zip);
		
		
	}
	
	public void clearContactName() {
		logger.info("Clear contact name");
		testUtil.init(this);
		int val = contactName.getAttribute("value").length();
		for (int i = 0; i <= val; i++) {
			testUtil.setHardWait(100);
			contactName.sendKeys(Keys.BACK_SPACE);
		}
	}
	
	
	public void verifyMessageDisplayed(String expMsg) {
		logger.info("Verify a message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
	    String actualMsg = message.getText();
        System.out.println(actualMsg);
        assertEquals(actualMsg, expMsg);
    }
	
	public void verifyPieces() {
		logger.info("Verify pieces");
		testUtil.init(this);
		Assert.assertEquals(pieces.isDisplayed(), true);
	}
	
	public void verifyWeight() {
		logger.info("Verify Weight");
		testUtil.init(this);
		Assert.assertEquals(weight.isDisplayed(), true);
	}
	
	public void verifySkids() {
		logger.info("Verify Skids");
		testUtil.init(this);
		Assert.assertEquals(skids.isDisplayed(), true);
	}
	
	public void verifyHazmat() {
		logger.info("Verify Hazmat");
		testUtil.init(this);
		Assert.assertEquals(hazmat.isDisplayed(), true);
	}
	
	public void verifyYourRefNumber() {
		logger.info("Verify Your Ref Number");
		testUtil.init(this);
		Assert.assertEquals(refNum.isDisplayed(), true);
	}
	
	public void verifyDetails() {
		logger.info("Verify Details");
		testUtil.init(this);
		Assert.assertEquals(details.isDisplayed(), true);
	}
	
	public void enterTotalSkids(String skid) {
		logger.info("Enterg total skid: " + skid);
		testUtil.init(this);
		totalSkids.sendKeys(skid);
	}
	
	public void enterRefNumber(String ref) {
		logger.info("Enterg total skid: " + ref);
		testUtil.init(this);
		referenceNo.sendKeys(ref);
	}
}


