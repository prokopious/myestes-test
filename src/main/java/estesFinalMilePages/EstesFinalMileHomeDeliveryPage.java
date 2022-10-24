package estesFinalMilePages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;



public class EstesFinalMileHomeDeliveryPage extends TestBase{
	
 private Logger logger = Logger.getLogger(EstesFinalMileHomeDeliveryPage.class);

	//@FindBy(id="tracking-number")
    @FindBy(how =How.XPATH,using=("//input[@id='tracking-number'] [@type='text']"))
	private WebElement trackingNumber;
	
	//@FindBy(id="zip-code")
	@FindBy(how=How.XPATH,using=("//input[@id='zip-code'] [@type='text']"))
	private WebElement dZipCode;
	
	@FindBy(xpath="//button[text()='Track Now'][@type='submit']")
	private WebElement trackNowBtn;
	
	@FindBy(css="[class='alert alert-danger']")
	private WebElement alertMessage;
	
	@FindBy(css="[class='invalid-feedback']")
	private WebElement invalidErrorMessage;
	
	@FindBy(xpath="//div[@class='invalid-feedback'][text()='Tracking Number is required']")
	private WebElement trackNoError;
	
	@FindBy(xpath="//div[@class='invalid-feedback'][text()='ZIP/Postal Code is required']")
	private WebElement zipError; 
	
	public void enterTrackingNumber(String trackingNo) {
		logger.info("Enter Tracking number");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(trackingNumber,20);
		trackingNumber.clear();
		trackingNumber.sendKeys(trackingNo);
	}
		
	public void enterDeliveryZipCode(String zipCode) {
		logger.info("Enter Delivery Zip code");
		testUtil.init(this);
		testUtil.setExplicitWait(dZipCode,20);
		dZipCode.clear();
		dZipCode.sendKeys(zipCode);
	}	
	
	public void clickOnTrackNowButton() {
		logger.info("Click on Track Now button");
		testUtil.init(this);
		testUtil.setExplicitWait(trackNowBtn, 60);
		testUtil.clickElementJavascript(trackNowBtn);
		//trackNowBtn.click();
	}
	
	public void verifyAlertMessage(String expText) {
		logger.info("Verify Alert message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertMessage, 20);
		String alertText = alertMessage.getText();
		Assert.assertEquals(alertText, expText);
	}
	
	public void validateInvalidTrackingMessage() {
		logger.info("Validating invalid error message is displayed for Tracking number");
		testUtil.init(this);
		testUtil.setExplicitWait(invalidErrorMessage, 20);
		String errorMessage = invalidErrorMessage.getText();
		Assert.assertEquals(errorMessage, "Tracking Number is invalid");
	}
	
	public void validateInvalidZipCodeMessage() {
		logger.info("Validating invalid error message is displayed for destination zip code");
		testUtil.init(this);
		testUtil.setExplicitWait(invalidErrorMessage, 20);
		String errorMessage = invalidErrorMessage.getText();
		Assert.assertEquals(errorMessage, "ZIP/Postal Code is invalid");
	}
	
	public void enterTrackingNumberInFormat(String ot, String pro) {
		logger.info("Enter Tracking number");
		testUtil.init(this);
		String oTerminal = testUtil.checkAndAddLeadingZeroToFBData(3, ot);
		String proNumber = testUtil.checkAndAddLeadingZeroToFBData(7, pro);
		testUtil.assetWait(null, trackingNumber, 10, 250, TimeUnit.MILLISECONDS);
		trackingNumber.clear();
		trackingNumber.sendKeys(oTerminal+proNumber);
	}
	
	public void enterDeliveryZipCodeInFormat(String zipCode) {
		logger.info("Enter Delivery Zip code in Track another shipment section");
		testUtil.init(this);
		String consigneeZip = testUtil.checkAndAddLeadingZeroToFBData(5, zipCode);
		testUtil.setExplicitWait(dZipCode,20);
		dZipCode.clear();
		dZipCode.sendKeys(consigneeZip);
	}

	public void validateErrorTrackingNumberMessage(String error) {
		logger.info("Validating error message is displayed for Tracking Number field");
		testUtil.init(this);
		testUtil.setExplicitWait(trackNoError, 60);
		String errorMessage = trackNoError.getText();
		Assert.assertEquals(errorMessage, error);
	}

	public void validateErrorZipCodeMessage(String error) {
		logger.info("Validating error message is displayed for zip code field");
		testUtil.init(this);
		testUtil.setExplicitWait(zipError, 20);
		String errorMessage = zipError.getText();
		Assert.assertEquals(errorMessage, error);
	}

}

