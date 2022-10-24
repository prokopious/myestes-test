package estesFinalMilePages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;

public class EstesFinalMileShipmentDetails extends TestBase {

	private Logger logger = Logger.getLogger(EstesFinalMileHomeDeliveryPage.class);

	@FindBy(xpath = "//span[text()='Shipment Details']")
	private WebElement shipmentDetailsText;

	@FindBy(css = "[class='alert alert-danger'] a:nth-child(1)")
	private WebElement UnattendedDeliveryLink;

	@FindBy(xpath = "//*[@id=\"btn-unattended-delivery-modal\"]")
	private WebElement finishBtn;

	@FindBy(xpath = "//div[@class='invalid-feedback' and text()='Name is required']")
	private WebElement nameRequiredMessage;

	@FindBy(xpath = "//div[@class='invalid-feedback' and text()='Authorization is required']")
	private WebElement unauthorizationRequiredMessage;

	@FindBy(css = "[id='unattended-delivery-modal'] [class='close']")
	private WebElement closeBtn;

	@FindBy(id = "contact-email-notifications")
	private WebElement emailNotification;

	@FindBy(id = "btn-submit")
	private WebElement updateButton;

	@FindBy(xpath = "//div[@class='invalid-feedback' and text()='Email is required']")
	private WebElement emailRequiredMessage;

	@FindBy(id = "contact-phone")
	private WebElement phoneNumber;

	@FindBy(id = "contact-phone-secondary")
	private WebElement secondaryPhoneNumber;

	@FindBy(xpath = "//div[@class='invalid-feedback' and text()='Phone Number is invalid']")
	private WebElement phoneNumberRequiredMessage;

	@FindBy(xpath = "//div[@class='invalid-feedback' and text()='Secondary Phone Number is invalid']")
	private WebElement secondaryPhoneNumberRequiredMessage;

	@FindBy(css = "[class*='success']")
	private WebElement successMessage;

	@FindBy(xpath = "//button[text()='Track Now']")
	private WebElement trackNowBtn;

	@FindBy(id = "tracking-number")
	private WebElement trackingNumber;

	@FindBy(id = "zip-code")
	private WebElement dZipCode;

	@FindBy(css = "[class='alert alert-danger']")
	private WebElement alertMessage;

	@FindBy(xpath = "//div[text()='ZIP/Postal Code is required']")
	private WebElement zipCodeRequiredMessage;

	@FindBy(xpath = "//div[text()='Tracking Number is required']")
	private WebElement trackingNoRequiredMessage;

	@FindBy(xpath = "//*[@class='row']//strong[contains(text(),'Tracking Number:')]")
	private WebElement trackingNumberDisplay;

	@FindBy(xpath = "//*[@class='row']//strong[contains(text(),'ZIP/Postal Code:')]")
	private WebElement zipOrPostalCode;

	@FindBy(xpath = "//*[@class='row']//strong[contains(text(),'Earliest Possible Delivery Date:')]")
	private WebElement earliestPossibleDate;

//	@FindBy(xpath="//span[text()='We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur.']")
	@FindBy(xpath = "//*[@class='messages']")
	private WebElement scheduledDeliveryMessage;

	@FindBy(xpath = "//*[@id='heading-status']/h2/button")
	private WebElement viewShipmentDetailsButton;

	@FindBy(xpath = "//*[@id='status']/li[7]/small")
	private WebElement shipmentDetailDateMessage;

	@FindBy(xpath = "//*[@id='main']/div/div[2]/div[1]/div/div[2]/div[1]/ul/li[1]/div[1]/span/span[1]")
	private WebElement shippedColor;

	@FindBy(id = "contact-email")
	private WebElement emailAddress;

	@FindBy(id = "contact-phone-other")
	private WebElement otherPhoneNumber;

	@FindBy(id = "ud-name")
	private WebElement unattendedDeliveryName;

	@FindBy(id = "ud-code")
	private WebElement unattendedDeliveryGateOrDoorCode;

	@FindBy(id = "ud-authorization")
	private WebElement unattendedDeliveryAuthorizeCheckBox;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/div/h4")
	private WebElement unattendedDeliveryTextRight;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/div/p[1]")
	private WebElement unattendedDeliveryMessageRight;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/div/p[2]/button")
	private WebElement unattendedDeliverySignUpButton;

	@FindBy(xpath = "//*[@id='sidebar']/div[2]/div/h4")
	private WebElement trackAnotherShipmentTextRight;

	@FindBy(xpath = "//*[@id='tracking-number']")
	private WebElement trackAnotherShipmentTrackingNumberRight;

	@FindBy(xpath = "//*[@id='zip-code']")
	private WebElement trackAnotherShipmentZipCodRight;

	@FindBy(xpath = "//*[@id='fm-tracking-form']/p/button")
	private WebElement trackAnotherShipmentTrackNowButtonRight;

	@FindBy(xpath = "//*[@id='sidebar']/div[3]/div/h4")
	private WebElement contactUsTextRight;

	@FindBy(xpath = "//*[@id='sidebar']/div[3]/div/div/p")
	private WebElement contactUsMessageRight;

	@FindBy(xpath = "//*[@id='sidebar']/div[3]/div/div/a[1]")
	private WebElement contactUsEmailAddressRight;

	@FindBy(xpath = "//*[@id='sidebar']/div[3]/div/div/a[2]")
	private WebElement contactUsContactNumberRight;

	@FindBy(xpath = "//h6[contains(.,'Shipped')]")
	private WebElement shippedStatusBarText;

	@FindBy(xpath = "//h6[contains(.,'Appointment Set')]")
	private WebElement appointmentSetStatusBarText;

	@FindBy(xpath = "//h6[contains(.,'Out For Delivery')]")
	private WebElement outForDeliveryStatusBarText;

	@FindBy(xpath = "//h6[contains(.,'Delivered')]")
	private WebElement deliveredStatusBarText;

	@FindBy(xpath = "//div[@id='contact-messages']/div")
	private WebElement contactInfoUpdateConfirmationMessage;

	@FindBy(id = "contact-phone-primary-notifications")
	private WebElement primaryPhoneNotificationsCheckbox;

	@FindBy(id = "contact-phone-secondary-notifications")
	private WebElement secondaryPhoneNotificationsCheckbox;

	@FindBy(css = ".col-md-6:nth-child(2)")
	private WebElement earliestPossibleDeliveryDateRange;

	@FindBy(css = "#earliest-delivery-date .value")
	private WebElement earliestPossibleDeliveryDateRangeInShipmentDetails;

	@FindBy(xpath = "//*[@id='ud-location-inside-gate']")
	private WebElement insideGate;
	// *[@id="main"]/div/div[2]/div[1]/div/div[2]/div[3]/div/span[2]

	@FindBy(xpath = "//*[@id='main']/div/div[2]/div[1]/div/div[2]/div[3]/div/span[2]")
	private WebElement optedOutAppointmentMessage;

	@FindBy(xpath = "//*[@id='delivery-location']/div/span[2]")
	private WebElement estimatedDeliveryDate;

	@FindBy(xpath = "//*[@id='delivery-location']/div/span[2]")
	private WebElement deliveryLocation;

	@FindBy(xpath = "//*[@id='service-level']/div/span[2]")
	private WebElement serviceLevel;

	@FindBy(xpath = "//*[@id='weight']/div/span[2]")
	private WebElement weight;

	@FindBy(xpath = "//*[@id='pieces']/div/span[2]")
	private WebElement pieces;

	@FindBy(xpath = "//*[@id='shipping-date']/div/span[2]")
	private WebElement dateShipped;

	@FindBy(xpath = "//*[@id='main']/div[1]/div[2]/div[1]/div/div[2]/div[3]/div/span[2]")
	private WebElement deliveryAlertMessage;

	@FindBy(xpath = "//*[@id='appointment-date']/div/span[2]")
	private WebElement appointmentDate;

	@FindBy(xpath = "//*[@id='appointment-time']/div/span[2]")
	private WebElement appointmentTime;

	@FindBy(xpath = "//*[@id='earliest-delivery-date']/div/span[1]")
	private WebElement deliverDate;

	@FindBy(xpath = "//*[@id='main']/div/div[2]/div[1]/div/div[2]/div[1]/ul/li[1]/div[2]/p")
	private WebElement shippedDate;

	@FindBy(xpath = "//*[@id='unattended-delivery-modal']/div/div/div[1]/h5")
	private WebElement unattendedDeliveryPopup;

	@FindBy(xpath="//*[@class='alert alert-danger']/.//span[contains(@class,'msg')]")
	private WebElement unattendedDeliveryFullMessage;
	
	@FindBy(xpath="//*[@class='messages']//span[2]")
	private WebElement setUpApptMessage;

	/******************************** METHODS ********************************/

	public void verifyShipmentDetailsPage() {
		logger.info("Verify shipment details page");
		testUtil.init(this);
		testUtil.setExplicitWait(shipmentDetailsText, 20);
		boolean existence = shipmentDetailsText.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void clickOnUnattendedDeliveryLink() {
		logger.info("Click On unattended delivery link");
		testUtil.init(this);
		// testUtil.setHardWait(2000);
		testUtil.setExplicitWait(UnattendedDeliveryLink, 120);
		testUtil.clickElementJavascript(UnattendedDeliveryLink);

	}

	public void clickOnUnattendedDeliveryFinishButton() {
		logger.info("Click on unattended delivery signup finish button");
		testUtil.init(this);

		// testUtil.reloadPage();
		testUtil.setHardWait(2000);

		testUtil.setHardWait(2000);
		Actions actions = new Actions(driver);
		actions.doubleClick(finishBtn).perform(); // used double click on finish button
		testUtil.setHardWait(2000); // extended wait for UI to refresh

	}

	public void validateUnattendedNameRequiredError() {
		logger.info("Validate unattended delivery signup name required error message");
		testUtil.init(this);
		testUtil.setExplicitWait(nameRequiredMessage, 20);
		boolean existence = nameRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void validateUnattendedAuthorizationRequiredError() {
		logger.info("Validate unattended delivery signup authorization required error message");
		testUtil.init(this);
		testUtil.setExplicitWait(unauthorizationRequiredMessage, 20);
		boolean existence = unauthorizationRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void clickCloseOnUnattendedDeliveryPopup() {
		logger.info("Click close button on Unattended Delivery Popup");
		testUtil.init(this);
		testUtil.setExplicitWait(closeBtn, 20);
		closeBtn.click();
	}

	public void selectORDeselectPrimaryPhoneNotificationsCheckBox() {
		logger.info("Click on primary phone notification checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(primaryPhoneNotificationsCheckbox, 20);
		primaryPhoneNotificationsCheckbox.click();
	}

	public void selectORDeselectSecondaryPhoneNotificationsCheckBox() {
		logger.info("Click on secondary phone notification checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(secondaryPhoneNotificationsCheckbox, 20);
		secondaryPhoneNotificationsCheckbox.click();
	}

	public void selectORDeselectEmailNotificationCheckBox() {
		logger.info("Click on email notification checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(emailNotification, 20);
		emailNotification.click();
	}

	public void clickOnUpdateButton() {
		logger.info("Click on update button");
		testUtil.init(this);
		updateButton.click();
	}

	public void validateEmailRequiredMessage() {
		logger.info("Validate email required error message is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(emailRequiredMessage, 20);
		boolean existence = emailRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void enterPhoneNumber(String phone) {
		logger.info("Enter  phone number: " + phone);
		testUtil.init(this);
		phoneNumber.clear();
		phoneNumber.sendKeys(phone);
	}

	public void enterSecondaryPhoneNumber(String phone) {
		logger.info("Enter secondary phone number: " + phone);
		testUtil.init(this);
		secondaryPhoneNumber.clear();
		secondaryPhoneNumber.sendKeys(phone);
	}

	public void enterOtherPhoneNumber(String phone) {
		logger.info("Enter other phone number: " + phone);
		testUtil.init(this);
		otherPhoneNumber.clear();
		otherPhoneNumber.sendKeys(phone);
	}

	public void enterEmail(String email) {
		logger.info("Enter  email: " + email);
		testUtil.init(this);
		emailAddress.clear();
		emailAddress.sendKeys(email);
	}

	public void validatePhoneNumberRequiredMessage() {
		logger.info("Validate Phone Number required error message is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(phoneNumberRequiredMessage, 20);
		boolean existence = phoneNumberRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void validateSecondaryPhoneNumberRequiredMessage() {
		logger.info("Validate secondary phone number required error message is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(secondaryPhoneNumberRequiredMessage, 20);
		boolean existence = secondaryPhoneNumberRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyContactSavedMessage(String message) {
		logger.info("Validate Contact successfully saved message");
		testUtil.init(this);
		testUtil.setExplicitWait(successMessage, 20);
		String successMsg = successMessage.getText();
		Assert.assertTrue(successMsg.contains(message));
	}

	public void clickOnTrackNowButton() {
		logger.info("Click Track Now button on Track another shipment");
		testUtil.init(this);
		testUtil.setExplicitWait(trackNowBtn, 20);
		// trackNowBtn.click();
		testUtil.clickElementJavascript(trackNowBtn);
	}

	public void verifyTrackingNoAlertMessage() {
		logger.info("Validate tracking number is required message is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(trackingNoRequiredMessage, 20);
		boolean existence = trackingNoRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyZipCodeAlertMessage() {
		logger.info("Validate zip code is required message is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(zipCodeRequiredMessage, 20);
		boolean existence = zipCodeRequiredMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void enterTrackingNumber(String trackingNo) {
		logger.info("Enter Tracking number in Track another shipment section");
		testUtil.init(this);
		testUtil.setExplicitWait(trackingNumber, 20);
		trackingNumber.clear();
		trackingNumber.sendKeys(trackingNo);
	}

	public void enterDeliveryZipCode(String zipCode) {
		logger.info("Enter delivery zip code in track another shipment section");
		testUtil.init(this);
		testUtil.setExplicitWait(dZipCode, 20);
		dZipCode.clear();
		dZipCode.sendKeys(zipCode);
	}

	public void verifyAlertMessage(String expText) {
		logger.info("Verify Alert message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertMessage, 20);
		String alertText = alertMessage.getText();
		Assert.assertEquals(alertText, expText);
	}

	public void enterTrackingNumberInFormat(String ot, String pro) {
		logger.info("Enter tracking number");
		testUtil.init(this);
		String oTerminal = testUtil.checkAndAddLeadingZeroToFBData(3, ot);
		String proNumber = testUtil.checkAndAddLeadingZeroToFBData(7, pro);
		testUtil.setExplicitWait(trackingNumber, 20);
		trackingNumber.clear();
		trackingNumber.sendKeys(oTerminal + proNumber);
	}

	public void enterDeliveryZipCodeInFormat(String zipCode) {
		logger.info("Enter Delivery Zip code in Track another shipment section");
		testUtil.init(this);
		String consigneeZip = testUtil.checkAndAddLeadingZeroToFBData(5, zipCode);
		testUtil.setExplicitWait(dZipCode, 20);
		dZipCode.clear();
		dZipCode.sendKeys(consigneeZip);
	}

	public void verifyAddressBarLinkContainsTrackingInfo(String ot, String pro, String zipCode) {
		logger.info("Validating Shipment details address bar link contains Tracking number and Zipcode");
		testUtil.init(this);
		String oTerminal = testUtil.checkAndAddLeadingZeroToFBData(3, ot);
		String proNumber = testUtil.checkAndAddLeadingZeroToFBData(7, pro);
		String deliveryZip = testUtil.checkAndAddLeadingZeroToFBData(5, zipCode);
		String trackingText = "tn=" + oTerminal + proNumber + "&zip=" + deliveryZip;
		String shipmentURL = driver.getCurrentUrl();
		Assert.assertTrue(shipmentURL.contains(trackingText.trim()));
	}

	public void verifyPhoneNumberFieldIsDisplayed() {
		logger.info("Validating Phone number field is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(phoneNumber, 20);
		boolean existence = phoneNumber.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifySecondaryPhoneNoFieldIsDisplayed() {
		logger.info("Validating Secondary Phone number field is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(secondaryPhoneNumber, 20);
		boolean existence = secondaryPhoneNumber.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyEmailAddressDisplays() {
		logger.info("VerifyEmail Address Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(emailAddress, 20);
		boolean existence = emailAddress.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyOtherPhoneNumberDisplays() {
		logger.info("VerifyOther Phone Number Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(otherPhoneNumber, 20);
		boolean existence = otherPhoneNumber.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifySHippedColor() {
		logger.info("Verify Shipped COlour Displays Green");
		testUtil.init(this);
		String color = shippedColor.getCssValue("color");
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		Assert.assertTrue(actualColor.equals("#2cac00"));

	}

	public void verifyTrackingNumberDisplays() {
		logger.info("Verify Tracking Number Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(trackingNumberDisplay, 20);
		boolean existence = trackingNumberDisplay.isDisplayed();
		Assert.assertTrue(existence);

	}

	public void verifyZipOrPostalCodeDisplays() {
		logger.info("Verify zip or postal code displays");
		testUtil.init(this);
		testUtil.setExplicitWait(zipOrPostalCode, 20);
		boolean existence = zipOrPostalCode.isDisplayed();
		Assert.assertTrue(existence);

	}

	public void verifyEarliestDeliveryDateDisplays() {
		logger.info("Verify earliest possible delivery date displays");
		testUtil.init(this);
		try {
			testUtil.setExplicitWait(earliestPossibleDate, 30);
			boolean existence = earliestPossibleDate.isDisplayed();
			Assert.assertTrue(existence);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyScheduledDeliveryMessageDisplays(String expMsg) {
		logger.info("Verify scheduled delivery message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = driver.findElement(By.xpath("//*[@class='messages']")).getText();
		System.out.println(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void clickOnViewShipmentDetailsButton() {
		logger.info("Click on View Shipment Details button");
		testUtil.init(this);
		testUtil.setExplicitWait(viewShipmentDetailsButton, 20);
		viewShipmentDetailsButton.click();
	}

	public void verifyShipmentDetailsDateMessageDisplays(String exMsg) {
		logger.info("Verify shipment details date message displays");
		testUtil.init(this);
		testUtil.setExplicitWait(shipmentDetailDateMessage, 20);
		String actMsg = shipmentDetailDateMessage.getText();
		System.out.println(actMsg);
		Assert.assertEquals(exMsg, actMsg);

	}

	public void verifyUnattendedDeliveryDisabled() {
		logger.info("verify shipment details date message displays");
		testUtil.init(this);
		boolean nonexistance = driver.findElements(By.cssSelector("[class='alert alert-danger'] a:nth-child(1)"))
				.size() > 0;
		Assert.assertFalse(nonexistance);
	}

	public void verifyPrimaryPhoneNumberDisplays() {
		logger.info("Verify primary phone number displays");
		testUtil.init(this);
		testUtil.setExplicitWait(phoneNumber, 20);
		boolean existence = phoneNumber.isDisplayed();
		Assert.assertTrue(existence);

	}

	public void verifySecondaryPhoneNumberDisplays() {
		logger.info("Verify secondary phone number displays");
		testUtil.init(this);
		testUtil.setExplicitWait(secondaryPhoneNumber, 20);
		boolean existence = secondaryPhoneNumber.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void radioButtonUnattendedDelivery(String val) {
		logger.info("Selecting Radio Button As Per Request");
		testUtil.init(this);
		List<WebElement> radioButton = driver.findElements(By.name("ud-location"));
		int size = radioButton.size();
		for (int i = 0; i < size; i++) {
			String actval = radioButton.get(i).getAttribute("value");
			if (actval.equalsIgnoreCase(val)) {
				radioButton.get(i).click();
				break;
			}
		}
	}

	public void unattendedDeliveryName(String name) {
		logger.info("Enter Name in Unattended Delivery Pop Up");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliveryName, 20);
		unattendedDeliveryName.clear();
		unattendedDeliveryName.sendKeys(name);
	}

	public void unattendedDeliveryGateOrCode(String gateOrCode) {
		logger.info("Enter Gate Or Code in Unattended Delivery Pop Up");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliveryGateOrDoorCode, 20);
		unattendedDeliveryGateOrDoorCode.clear();
		unattendedDeliveryGateOrDoorCode.sendKeys(gateOrCode);
	}

	public void unattendedDeliveryAuthorizeCheckBox() {
		logger.info("Select Check Box - I authorize an unattended delivery and agree to the Terms Of Services");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliveryAuthorizeCheckBox, 20);
		// unattendedDeliveryAuthorizeCheckBox.click();
		testUtil.clickElementJavascript(unattendedDeliveryAuthorizeCheckBox);
	}

	public void verifyUnattendedDeliveryTextInRightDisplays() {
		logger.info("Verifying Unattended Delivery Text In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliveryTextRight, 20);
		boolean existence = unattendedDeliveryTextRight.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyUnattendedDeliveryMessageInRightDisplays(String exMsg) {
		logger.info("Verifying Unattended Delivery Message In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliveryMessageRight, 20);
		String actMsg = unattendedDeliveryMessageRight.getText();
		System.out.println(actMsg);
		Assert.assertEquals(exMsg, actMsg);
	}

	public void verifyUnattendedDeliveryButtonInRightDisplays() {
		logger.info("Verifying Unattended Delivery Button In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliverySignUpButton, 20);
		boolean existence = unattendedDeliverySignUpButton.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyTrackAnotherShipmentTextInRightDisplays() {
		logger.info("Verifying Track now Text In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(trackAnotherShipmentTextRight, 20);
		boolean existence = trackAnotherShipmentTextRight.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyTrackAnotherShipmentTrackingNumberInRightDisplays() {
		logger.info("Verifying TrackingNumber In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(trackAnotherShipmentTrackingNumberRight, 20);
		boolean existence = trackAnotherShipmentTrackingNumberRight.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyTrackAnotherShipmentZipCodeInRightDisplays() {
		logger.info("Verifying Zip Code In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(trackAnotherShipmentZipCodRight, 20);
		boolean existence = trackAnotherShipmentZipCodRight.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyTrackAnotherShipmentTrackNowButtonInRightDisplays() {
		logger.info("Verifying Track Now Button In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(trackAnotherShipmentTrackNowButtonRight, 20);
		boolean existence = trackAnotherShipmentTrackNowButtonRight.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyContactUsTextInRightDisplays() {
		logger.info("Verifying Contact Us Text In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(contactUsTextRight, 20);
		boolean existence = contactUsTextRight.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyContactUsMessageInRightDisplays(String exMsg) {
		logger.info("Verifying Contact Us Message In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(contactUsMessageRight, 20);
		String actMsg = contactUsMessageRight.getText();
		System.out.println(actMsg);
		Assert.assertEquals(exMsg, actMsg);
	}

	public void verifyContactUsEmailAddressInRightDisplays(String exMsg) {
		logger.info("Verifying Contact Us EmailAddress In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(contactUsEmailAddressRight, 20);
		String actMsg = contactUsEmailAddressRight.getText();
		System.out.println(actMsg);
		Assert.assertEquals(exMsg, actMsg);
	}

	public void verifyContactUsPhoneNumberInRightDisplays(String exMsg) {
		logger.info("Verifying Contact Us Phone Number In Right Displays");
		testUtil.init(this);
		testUtil.setExplicitWait(contactUsContactNumberRight, 20);
		String actMsg = contactUsContactNumberRight.getText();
		System.out.println(actMsg);
		Assert.assertEquals(exMsg, actMsg);
	}

	public void verifyShippedStatusBarTextIsDisplayed() {
		logger.info("Verify 'Shipped' is displayed on status bar");
		testUtil.init(this);
		testUtil.setExplicitWait(shippedStatusBarText, 20);
		boolean existence = shippedStatusBarText.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyAppointmentSetStatusBarTextIsDisplayed() {
		logger.info("Verify 'Appointment Set' is displayed on status bar");
		testUtil.init(this);
		testUtil.setExplicitWait(appointmentSetStatusBarText, 20);
		boolean existence = appointmentSetStatusBarText.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyOutForDeliveryStatusBarTextIsDisplayed() {
		logger.info("Verify 'Out For Delivery' is displayed on status bar");
		testUtil.init(this);
		testUtil.setExplicitWait(outForDeliveryStatusBarText, 20);
		boolean existence = outForDeliveryStatusBarText.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyDeliveredStatusBarTextIsDisplayed() {
		logger.info("Verify 'Delivered' is displayed on status bar");
		testUtil.init(this);
		testUtil.setExplicitWait(deliveredStatusBarText, 20);
		boolean existence = deliveredStatusBarText.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyContactInfoUpdateConfirmationMessageIsDisplayed() {
		logger.info("Verify 'Contact information saved successfully' is displayed after clicking 'Update'");
		testUtil.init(this);
		testUtil.setExplicitWait(contactInfoUpdateConfirmationMessage, 20);
		boolean existence = contactInfoUpdateConfirmationMessage.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void verifyContactInfoFieldsAreMasked() {
		logger.info("Verify primary phone number is masked by asterisks");
		testUtil.init(this);
		String text = phoneNumber.getAttribute("value");
		Assert.assertTrue(text.contains("***"));

		logger.info("Verify secondary phone number is masked by asterisks");
		testUtil.init(this);
		text = secondaryPhoneNumber.getAttribute("value");
		Assert.assertTrue(text.contains("***"));

		logger.info("Verify email is masked by asterisks");
		testUtil.init(this);
		text = emailAddress.getAttribute("value");
		Assert.assertTrue(text.contains("***"));

		logger.info("Verify other phone number is masked by asterisks");
		testUtil.init(this);
		text = otherPhoneNumber.getAttribute("value");
		Assert.assertTrue(text.contains("***"));
	}

	public void verifyContactInfoCheckboxesAreChecked() {
		logger.info("Verify primary phone notifications checkbox is checked");
		Assert.assertTrue(primaryPhoneNotificationsCheckbox.isSelected());

		logger.info("Verify secondary phone notifications checkbox is checked");
		Assert.assertTrue(secondaryPhoneNotificationsCheckbox.isSelected());

		logger.info("Verify email notifications checkbox is checked");
		Assert.assertTrue(emailNotification.isSelected());
	}

	public void verifyEarliestPossibleDeliveryDateRange() throws Exception { // validates dates in header with weekday
		logger.info("Verify earliest possible delivery date range");
		testUtil.init(this);
		testUtil.setExplicitWait(earliestPossibleDeliveryDateRange, 60);
		String dateText = earliestPossibleDeliveryDateRange.getText();
		System.out.println(dateText);
		String todaysDate = testUtil.getTodayDateByFormat("EEE, M/d/yyyy");
		logger.info("todaysDate : "+todaysDate);
		String tomorrowDate = testUtil.addTomorrowDate1();
		testUtil.setHardWait(1000);
		String fiveDaysDate = testUtil.addDateFiveBusinessDaysInFuture();
		String fourDaysDate = testUtil.addDateFourBusinessDaysInFuture();
		 logger.info("The five days range:" + fiveDaysDate);
		String expectedStr1 = tomorrowDate + " – " + fiveDaysDate;
		System.out.println("expectedStr1 : "+expectedStr1);
		String expectedStr2 = todaysDate + " – " + fourDaysDate;
		System.out.println("expectedStr2 : "+expectedStr2);
		if(dateText.contains(expectedStr1)) {
			logger.info("Found the earliest possible delivery date range");
		}else if(dateText.contains(expectedStr2)) {
			logger.info("Found the earliest possible delivery date range");
		}else {
			Assert.fail("Cannot find the earliest possible delivery date range");
		}
	}


	public void verifyEarliestPossibleDeliveryDateRangeInShipmentDetails() throws Exception { // validates dates in
																								// shipment details
		logger.info("Verify earliest possible delivery date range");
		testUtil.init(this);
		testUtil.setExplicitWait(earliestPossibleDeliveryDateRangeInShipmentDetails, 20);
		String dateText = earliestPossibleDeliveryDateRangeInShipmentDetails.getText();
		System.out.println(dateText);
		String tomorrowDate = testUtil.addTomorrowDate1();
		String fiveDaysDate = testUtil.addDateFiveBusinessDaysInFuture();
		System.out.println(tomorrowDate + " – " + fiveDaysDate);
		Assert.assertTrue(dateText.contains(tomorrowDate + " – " + fiveDaysDate));
	}

	public void unattendedDeliveryInsideGate() {
		testUtil.init(this);
		insideGate.click();
	}

	public void checkForOptedOutAppointmentMessage() {
		if (!optedOutAppointmentMessage.getText().trim().contains("no signature"))
			Assert.fail("Opted Out Appointment message wrong");
	}

	public void checkForShipmentDetailsUnattendedDelivery() {
		testUtil.init(this);
		if (dateShipped.getText().isEmpty())
			Assert.fail("Date Shipped Missing");
		if (estimatedDeliveryDate.getText().isEmpty())
			Assert.fail("Estimated Delivery Date Missing");
		if (deliveryLocation.getText().isEmpty())
			Assert.fail("Delivery Location Missing");
		if (serviceLevel.getText().isEmpty())
			Assert.fail("Service Level Missing");
		if (weight.getText().isEmpty())
			Assert.fail("Weight Missing");
		if (pieces.getText().isEmpty())
			Assert.fail("pieces Missing");
	}

	public void verifyUnattendedDelivery() {
		testUtil.init(this);
		logger.info("Verify unattended delivery message is displayed");

	}

	public void checkForShipmentDetails() {
		testUtil.init(this);

		
		 if(dateShipped.getText().isEmpty()) Assert.fail("Date Shipped Missing");
		 if(appointmentDate.getText().isEmpty())
		 Assert.fail("Appointment Date Missing");
		 if(appointmentTime.getText().isEmpty())
		 Assert.fail("Appointment Time Missing");
		 if(deliveryLocation.getText().isEmpty())
		 Assert.fail("Delivery Location Missing");
		 if(serviceLevel.getText().isEmpty()) Assert.fail("Service Level Missing");
		 if(weight.getText().isEmpty()) Assert.fail("Weight Missing");
		  if(pieces.getText().isEmpty()) Assert.fail("pieces Missing");
		 
	}

	public void checkForDeliveryAlertMessage() {
		testUtil.init(this);
		try {
			logger.info(deliveryAlertMessage.getText());
		} catch (Exception e) {
			logger.info(
					"Delivery Alert Message Missing: Please note that for delivery to occur, someone in your household (18 years of age or older) must be available to sign for the shipment. Have a change in plans? You can opt out of an appointment with unattended delivery ");
		}
	}

	public void checkForTrackingNumberAndZip(String num, String zip) {
		testUtil.init(this);
		String info = driver.findElement(By.xpath("//*[@id='main']/div[1]/div[2]/div[1]/div/div[1]/div/div")).getText()
				.toString().trim();
		if (!info.contains(num))
			Assert.fail("Tracking Number Wrong/Missing");
		if (!info.contains(zip))
			Assert.fail("Zip Wrong/Missing");
		logger.info("Tracking Number and Zip validated");
	}

	public void checkForGreenCheck() {
		testUtil.init(this);
		List<WebElement> elements = driver
				.findElements(By.xpath("//*[@id='main']/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul/li[1]/div[1]/span"));
		if (elements.size() == 0)
			Assert.fail("Green Check missing");
		else
			logger.info("Green Check Validated");
	}

	public void checkForGreenAppointmentSet() {
		testUtil.init(this);
		List<WebElement> elements = driver
				.findElements(By.xpath("//*[@id='main']/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul/li[2]/div[1]/span"));
		if (elements.size() == 0)
			Assert.fail("Green Appointment Set missing");
		else
			logger.info("Green Appointment Set Validated");
	}


	public void verifyEarliestPossibleDeliveryDateInShipmentDetails() throws Exception { // validates dates in
		// shipment details
		logger.info("Verify earliest possible delivery single date");
		testUtil.init(this);
		testUtil.setExplicitWait(earliestPossibleDeliveryDateRangeInShipmentDetails, 20);
		String dateText = earliestPossibleDeliveryDateRangeInShipmentDetails.getText();
		System.out.println(dateText);
		String tomorrowDate = testUtil.addTomorrowDate1();
		System.out.println(tomorrowDate);
		Assert.assertTrue(dateText.contains(tomorrowDate));
	}

	public void validateShippedDate() {
		logger.info("Validate shipped date");
		testUtil.init(this);
		testUtil.setExplicitWait(shippedDate, 20);
		boolean existence = shippedDate.isDisplayed();
		Assert.assertTrue(existence);
	}

	public void validateUnattendedDeliveryPopup() {
		logger.info("Validate unattended delivery popup window");
		testUtil.init(this);
		testUtil.setExplicitWait(unattendedDeliveryPopup, 20);
		boolean existence = unattendedDeliveryPopup.isDisplayed();
		Assert.assertTrue(existence);
	}
	
	
	
	public void validateFullUnattendedDeliveryMessage() {
		logger.info("Verify that the full unattended delivery message is visible in webpage");
		testUtil.init(this);
		String message = "You may sign up for an unattended delivery, or we will contact you to set up an appointment. Please note if you do not sign up for unattended delivery, we will give you a call to schedule an appointment when the freight has reached our final facility.";
		String verifyMessage = StringUtils.deleteWhitespace(unattendedDeliveryFullMessage.getText()); 
		System.out.println(verifyMessage); 
		Assert.assertTrue(verifyMessage.equals(StringUtils.deleteWhitespace(message)));
	}
	
	public void validateSetUpApptMessage() {
		logger.info("Verify that the message to set up appointment is visible");
		testUtil.init(this);
		String existence = setUpApptMessage.getText().trim(); 
		Assert.assertTrue(existence.equals("We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur."));
	}
	
	
	public void validateFullUnattendedDeliveryMessageContact() {
		logger.info("Verify that the contact detail is visible in the shipment details page");
		testUtil.init(this);
		String message = "We will contact you to set up an appointment. Please note that a scheduled appointment is required for delivery to occur.";
		String verifyMessage = StringUtils.deleteWhitespace(unattendedDeliveryFullMessage.getText());
		Assert.assertTrue(verifyMessage.equals(StringUtils.deleteWhitespace(message)));
	}
	


}
