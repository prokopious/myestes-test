package imageRetrievalPages;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesImageRetrievalPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesImageRetrievalPage.class);

	//########################## SHIPMENT INFORMATION ##########################
	// This field is required Message
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'This field is required.')])[1]")
	private WebElement weRequiredMessage;
	// Tracking Number
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='trackingNumber']")
	private WebElement weTrackingNum;
	// Destination ZIP
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='destinationZip']")
	private WebElement weDestinationZip;
	// Bill of Lading(BOL) Check Box
	@FindBy(how = How.CSS, using = "#docTypeBOL-input")
	private WebElement weBillOfLading;
	// Delivery Receipt (DR) Check Box
	@FindBy(how = How.CSS, using = "#docTypeDR-input")
	private WebElement weDeliveryReceipt;
	
	@FindBy(how = How.CSS, using = "//mat-option[@id='mat-option-6']")
	private WebElement weWeightsAndResearch;
	
	//@FindBy(how = How.XPATH, using = "//mat-select[@id='documentType']//div[@class='mat-select-value']")
	//private WebElement SelectArrow;
	
	@FindBy(how = How.XPATH, using="//*[@id=\"documentType\"]/div/div[2] [@class=\"mat-select-arrow-wrapper\"]")
	private WebElement SelectArrow;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-option-5\"]/span")
	private WebElement seBillOfLading;
	
	// Delivery Receipt (DR) Check Box
	@FindBy(how = How.CSS, using =  "#mat-option-6")
	private WebElement seDeliveryReceipt;


	//########################## DELIVERY METHODS ##########################
	//Email Radio Button
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Email')]")
	private WebElement weEmailRadioButton;
	// Fax Radio Button
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Fax')]")
	private WebElement weFaxRadioButton;
	//  Email Addresses(one per line)
	@FindBy(how = How.XPATH, using = "//mat-radio-button[@id='mat-radio-5']//div[@class='mat-radio-inner-circle']")
	private WebElement PRORadio;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button[@id='mat-radio-6']//div[@class='mat-radio-outer-circle']")
	private WebElement BOLRadio;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button[@id='mat-radio-7']//div[@class='mat-radio-outer-circle']")
	private WebElement PORadio;
	
	@FindBy(how = How.XPATH, using = "//mat-radio-button[@id='mat-radio-8']//div[@class='mat-radio-outer-circle']")
	private WebElement InterlinePORadio;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='email']")
	private WebElement weEmailAddresses;


	// Submit Button
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SubmitButton;
	
	
	@FindBy(how = How.XPATH, using = "//button[@id='imageViewingSubmitButton']")
	private WebElement SearchButton;
	// Clear Button
	
	// Error Message
	@FindBy(how = How.XPATH, using = "//span[@class='ng-star-inserted']")
	private WebElement weZipErrorMessage;
	
	@FindBy(xpath = "//h1//span[contains(text(),'Document Retrieval')]")
	private WebElement documentRetrievalTitle;

	
	@FindBy(xpath = "//h1//span[contains(text(),'Document Viewing')]")
	 private WebElement documentViewing;

	/*****************************************/

	public void selectBOL(String imageType) {
		testUtil.init(this);
		logger.info("Select Bill of Lading ");
		testUtil.setHardWait(4000);
		testUtil.clickElementJavascript(SelectArrow);
		WebElement role = driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + imageType + "')]"));
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(role);
		testUtil.setHardWait(1000);
	}
	
	
	public void selectDeliveryReceipt(String imageType) {
		testUtil.init(this);
		logger.info("Select Delivery Receipt");
		testUtil.clickElementJavascript(SelectArrow);
		testUtil.setHardWait(1000);
		WebElement role = driver.findElement(By.xpath("//span[contains(text(),'" + imageType + "')]"));
		testUtil.clickElementJavascript(role);
		testUtil.setHardWait(1000);

	}
	
	public void selectWeightsAndResearch() {
		testUtil.init(this);
		logger.info("Select Weights and Research");
		SelectArrow.click();
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(weWeightsAndResearch);
		//weWeightsAndResearch.click();
	}
	
	//########################## SHIPMENT INFORMATION ##########################
	// Verify This field is required Message
	public void verifyThisfieldIsRequiredMess () {
		logger.info("Verify This field is required Message");
		testUtil.init(this);	
		weRequiredMessage.isDisplayed();
	}
	// Enter Tracking Number
	public void enterTrackingNum(String trackingNum) {
		logger.info(" Enter Tracking Number");
		testUtil.init(this);
		weTrackingNum.sendKeys(trackingNum);
	}
	// Enter Destination ZIP
	public void enterDestinationZip(String destinationZip) {
		logger.info(" Enter Destination ZIP");
		testUtil.init(this);
		weDestinationZip.sendKeys(destinationZip);
	}
	// Click On Bill of Lading(BOL) Check Box
	public void clickOnBillOfLading () {
		logger.info("Click on Bill of Lading(BOL) Check Box");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		testUtil.clickElementJavascript(weBillOfLading);
		//weBillOfLading.click();		
	}
	// Delivery Receipt (DR) Check Box
	public void clickOnDeliveryReceipt () {
		logger.info("Click on Delivery Receipt (DR) Check Box");
		testUtil.init(this);
		testUtil.clickElementJavascript(weDeliveryReceipt);
		//weDeliveryReceipt.click();		
	}

	//########################## DELIVERY METHODS ##########################
	// Click On Email Radio Button
	public void clickOnEmailRadioButton () {
		logger.info("Click On Email Radio Button");
		testUtil.init(this);
		weEmailRadioButton.click();		
	}
	// Click On Fax Radio Button
	public void clickOnPRORadioButton () {
		logger.info("Click On PRO Radio Button");
		testUtil.init(this);
		PRORadio.click();		
	}
	
	public void clickOnBOLRadioButton () {
		logger.info("Click On BOL Radio Button");
		testUtil.init(this);
		testUtil.setExplicitWait(BOLRadio, 60);
		testUtil.clickElementJavascript(BOLRadio);
	
	}
	
	public void clickOnPORadioButton () {
		logger.info("Click On PO Radio Button");
		testUtil.init(this);
		testUtil.setExplicitWait(PORadio, 60);
		
		int count= 0;
		while (count <3) {
			try {
				testUtil.clickElementJavascript(PORadio);
				count=count+3;
			}
			catch(TimeoutException e) {
				count=count+1;
				System.out.println("Timeout occured");
				continue;
				
			}
		}
			
	}
	
	
	public void clickOnInterlinePORadioButton () {
		logger.info("Click On Interline PO Radio Button");
		testUtil.init(this);
	//	InterlinePORadio.click();		
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(InterlinePORadio);
	}
	
	public void clickOnFaxRadioButton () {
		logger.info("Click On Fax Radio Button");
		testUtil.init(this);	
		testUtil.clickElementJavascript(weFaxRadioButton);
	}
	
	
	public void clickOnSearchButton () {
		logger.info("Click On Search Button");
		testUtil.init(this);
		testUtil.setExplicitWait(SearchButton, 60);
		testUtil.clickElementJavascript(SearchButton);
			
	}
	
	public void clickOnSubmitButton () {
		logger.info("Click On Submit Button");
		testUtil.init(this);
		testUtil.clickElementJavascript(SubmitButton);
			
	}
	
	//  Enter Email Addresses(one per line)
	public void enterEmailAddresses(String emailAddress) {
		logger.info(" Enter Tracking Number");
		testUtil.init(this);
		weEmailAddresses.sendKeys(emailAddress);
	}

	/*// Click On image Retrieval Submit Button
	public void clickOnImageRetSubmitButton() {
		logger.info("Click On image Retrieval Submit Button");
		testUtil.init(this);
		weImageRetSubmitButton.click();		
	}*/
	// Click On Clear Button
	
	// Verify Mismatch of Tracking number and Zip  Error Message
	public void verifyMismatchOfTrackingNumberAndZipErrorMessage () {
		logger.info("Verify Error Message");
		testUtil.init(this);	
		String ErrorMessage = weZipErrorMessage.getText();
		System.out.println("Error message is : " + ErrorMessage);
		String Expected = "Shipment information is not currently available for the tracking number and ZIP combination you entered. Please verify that both numbers are correct. Need assistance? Simply call customer care at (877) 268-4555.";
		Assert.assertEquals(ErrorMessage, Expected);
	} 
	
	public void verifyErrorMessageFromRequirdFields() {
		logger.info("Verify error message from all the required fields");
		testUtil.init(this);	
		testUtil.setHardWait(3000);
		
		String mess=driver.findElement(By.xpath("//mat-error[@id='mat-error-0']")).getText();
		System.out.println("Tracking Number field : " + mess);
		Assert.assertEquals(mess, "This field is required.");
		
		String mess1=driver.findElement(By.xpath("//mat-error[@id='mat-error-1']")).getText();
		System.out.println("Destination Zip/Code field : " + mess1);
		Assert.assertEquals(mess1, "This field is required.");
		testUtil.setHardWait(3000);
		
		String mess2=driver.findElement(By.xpath("//div[@class='validation-error ng-star-inserted']")).getText();
		System.out.println("Document type : " + mess2);
		Assert.assertEquals(mess2, "Please select at least one option.");
		
		testUtil.setHardWait(3000);
		String mess3=driver.findElement(By.xpath("//mat-error[@id='mat-error-2']")).getText();
		System.out.println("Delivery method : " + mess3);
		Assert.assertEquals(mess3, "This field is required.");
		
	} 
	public void verifyImageRetrievalPageTile() {
		logger.info("Verify Image Retrieval page title");
		testUtil.init(this);	
		testUtil.getCurrentPageTitle();
	} 
	
	public void verifyDocumentRetrievalPage() {
		logger.info("Verify Document Retreival Page");
		testUtil.init(this);
		testUtil.setExplicitWait(documentRetrievalTitle, 60);
		boolean title = documentRetrievalTitle.isDisplayed();
		Assert.assertTrue(title);
	}

	public void verifyDocumentViewingPage() {
        logger.info("Verify Document Viewing Page");
        testUtil.init(this);
        testUtil.setExplicitWait(documentViewing, 20);
        boolean title = documentViewing.isDisplayed();
        Assert.assertTrue(title);
 }



}
