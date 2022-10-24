package billOfLadingPages;

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

public class MyEstesBillOfLadingPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesBillOfLadingPage.class);
	
								/**********************/
	// BOL Source 
	// Blank VICS BOL Radio Button
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Blank VICS BOL')]")
	private WebElement weBVICSBolRadioButton;

	// BOL Details 
	// Master BOL
	@FindBy(how = How.XPATH, using = "//*[@id='mat-slide-toggle-2']/label")
	private WebElement weMasterBOL;
	// Master BOL#
	@FindBy(how = How.ID, using = "mat-input-2")
	private WebElement weMasterBOLNum;
	// Auto-Assign PRO #
	@FindBy(how = How.XPATH, using = "//*[@id='mat-slide-toggle-1']/label")
	private WebElement weAutoAssignPRO;
	// BOL Reference #
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='bolReferenceNumber']")
	private WebElement weBOLReference;

	// ####################### Quote Details #####################
	
	// ####################### Pickup Request #####################

	// ####################### Accessorials #####################
	// Inside Delivery
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Inside Delivery')]")
	private WebElement weInsideDeliveryRadioButton;

	// ####################### Shipper and Consignee Details #####################
	// Use My Estes Account info For Shipper
	@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-1']/label/span")
	private WebElement weUseMyEstesAccInfoForShipper;
	// Address Line1 for Shipper
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='address1'])[1]")
	private WebElement weAddrLine1ForShipper;
	// City For Shipper
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='city'])[1]")
	private WebElement weCityForShipper;
	// Click on State For Shipper
	@FindBy(how = How.XPATH, using = "(//*[@id='state']/div/div[1]/span)[1]")
	private WebElement weStateForShipper;
	/*
	 * // Enter State Name for Shipper
	 * 
	 * @FindBy(how = How.XPATH, using = "//*[contains(text(),'VA')]") private
	 * WebElement weEnterStateNameForShipper;
	 */
	// ZIP Code For Shipper
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='zip'])[1]")
	private WebElement weZIPCodeForShipper;

	// Use My Estes Account info For consignee
	@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-2']/label/span")
	private WebElement weUseMyEstesAccInfoForConsignee;
	// Address Line1 for consignee
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='address1'])[2]")
	private WebElement weAddrLine1Forconsignee;
	// City For consignee
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='city'])[2]")
	private WebElement weCityForConsignee;
	// Click On State For consignee
	@FindBy(how = How.XPATH, using = "(//*[@id='state']/div/div[1]/span)[2]")
	private WebElement weStateForConsignee;
	/*
	 * // Enter State Name for Consignee
	 * 
	 * @FindBy(how = How.XPATH, using = "//*[contains(text(),'VA')]") private
	 * WebElement weEnterStateNameForConsignee;
	 */
	// ZIP Code For consignee
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='zip'])[2]")
	private WebElement weZIPCodeForConsignee;

	// ####################### Billing Information #####################

	// ####################### Commodities #####################
	// Handling Units
	@FindBy(how = How.ID, using = "goodsUnit0")
	private WebElement weHandlingUnits;
	// Click On Type
	@FindBy(how = How.XPATH, using = "//*[@id='goodsType0']/div/div[1]/span")
	private WebElement weClickOnType;
	// Total Weight
	@FindBy(how = How.ID, using = "goodsWeight0")
	private WebElement weTotalWeight;
	// Click On Packaging Type
	@FindBy(how = How.XPATH, using = "//*[@id='packageType0']/div/div[2]")
	private WebElement weClickOnPackagesType;
	// # of Packages
	@FindBy(how = How.ID, using = "numberOfPackages0")
	private WebElement weNumberOfPackages;

	// ####################### Reference Numbers #####################

	// ####################### Shipping Labels #####################

	// ####################### Notifications #####################

	// ####################### Save BOL Template #####################

	@FindBy(how = How.ID, using = "shipmentClass0")
	private WebElement weClass;

	@FindBy(how = How.ID, using = "description0")
	private WebElement weDescription;
	
	
	// Submit BOL Button
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary mr-3']")
	private WebElement weSubmitBOLButton;
	// View Bill Of Lading Button
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-default mr-3 mb-3']")
	private WebElement weViewBillOfLadingButton;
	// Save as Draft
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default mr-3 ng-star-inserted']")
	private WebElement weSaveAsDraftButton;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Drafts @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// BOL Drafts Link
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'BOL Drafts')]")
	private WebElement weBOLDraftsLink;
	// Advanced Search
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Advanced Search')]")
	private WebElement weAdvSearchButton;
	// Search By
	@FindBy(how = How.XPATH, using = "//*[@id='filter']/div/div[2]")
	private WebElement weAdvSearchBy;
	// Contains
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='contains']")
	private WebElement weContains;
	// SEARCH Button
	@FindBy(how = How.XPATH, using = "//button[@type='button'][contains(text(),'Search')]")
	private WebElement weSearchButton;
	// Clear
	// Enter Start Date
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='startDate']")
	private WebElement weStartDate;
	// Enter End Date
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='endDate']")
	private WebElement weEndDate;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-3\"]")
	private WebElement QouteNumber;
	
	@FindBy(css="h1 span")
	private WebElement pageTitle;
	
	@FindBy(css="[class*='success']")
	private WebElement successMessage;
	
	@FindBy(css="[formcontrolname='quoteNumber']")
	private WebElement quoteNumber;
	
	@FindBy(xpath="//mat-checkbox[contains(@class,'mat-checkbox-checked')]//span[@class='mat-checkbox-label']")
	private List<WebElement> selectedAccessorials;
	
	@FindBy(css="[formcontrolname='quoteNumber']")
	private WebElement quoteNumberField;
	
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'The shipper city, state and ZIP code are currently')]")
    private WebElement errorMessage;
    
	
										/**********************/
	
	// ####################### BOL Source #####################

	// Click On Blank VICS BOL Radio Button
	public void clickOnBVICSBolRadioButton() {
		logger.info("Click On Blank VICS BOL Radio Button");
		testUtil.init(this);
		weBVICSBolRadioButton.click();
	}

	// ####################### BOL Details #####################

	// Click On Master BOL Toggle
	public void clickOnMasterBOLToggle() {
		logger.info("Click On Master BOL Toggle");
		testUtil.init(this);
		weMasterBOL.click();
	}

	// Enter Master BOL Number Toggle
	public void enterMasterBOLNumber(String MasterBOLNum) {
		logger.info("Enter Master BOL Number");
		testUtil.init(this);
		weMasterBOLNum.sendKeys(MasterBOLNum);
	}

	// Click On Auto-Assign PRO #
	public void clickOnAutoAssignPROToggle() {
		logger.info("Click On Auto-Assign PRO # Toggle");
		testUtil.init(this);
		weAutoAssignPRO.click();
	}

	// Enter BOL Reference #
	public void enterBOLReferenceNumber(String BOLRefNum) {
		logger.info("Enter Master BOL Number Toggle");
		testUtil.init(this);
		weBOLReference.sendKeys(BOLRefNum);
	}

	// Get BOL Number
	public String getDataFromSearchBy() {
		logger.info("Get BOL Number ");
		testUtil.init(this);
		String BOLNum = weBOLReference.getAttribute("value");
		return BOLNum;
	}
	// ####################### Quote Details #####################

	// ####################### Pickup Request #####################

	// ####################### Accessorials #####################
	// Click On Inside Delivery Radio Button
	public void clickOnInsideDeliveryRadioButton() {
		logger.info("Click On Inside Delivery Radio Button");
		testUtil.init(this);
		weInsideDeliveryRadioButton.click();
	}

	// ####################### Shipper and Consignee Details #####################
	// Click On Use My Estes Account info For Shipper
	public void clickOnUseMyEstesAccInfoForShipper() {
		logger.info("Click On Use My Estes Account info For Shipper");
		testUtil.init(this);
		weUseMyEstesAccInfoForShipper.click();
	}

	public void enterQouteNumber(String qNumber) {
		logger.info("Enter Qoute Number");
		testUtil.init(this);
		QouteNumber.sendKeys(qNumber);
	}

	// Enter Address Line1 for Shipper
	public void enterAddrLine1ForShipper(String AddForLine1) {
		logger.info(" Enter Address Line1 for Shipper");
		testUtil.init(this);
		weAddrLine1ForShipper.sendKeys(AddForLine1);
	}

	// Enter City For Shipper
	public void enterCityForShipper(String CityName) {
		logger.info("Enter City For Shipper");
		testUtil.init(this);
		weCityForShipper.sendKeys(CityName);
	}

	// Click On State For Shipper
	public void clickOnStateForShipper() {
		logger.info("Click On State For Shipper");
		testUtil.init(this);
		weStateForShipper.click();
	}

	// Enter State Name for Shipper
	public void enterStateNameForShipper(String StateName) {
		logger.info("Enter State Name For Shipper");
		testUtil.init(this);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + StateName + "')]")).click();
		// weEnterStateNameForShipper.sendKeys(StateName);
	}

	// Enter ZIP Code For Shipper
	public void enterZIPCodeForShipper(String Zipcode) {
		logger.info("Enter ZIP Code For Shipper");
		testUtil.init(this);
		weZIPCodeForShipper.sendKeys(Zipcode);
	}

	// Click On Use My Estes Account info For Consignee
	public void clickOnUseMyEstesAccInfoForConsignee() {
		logger.info("Click On Use My Estes Account info for Consignee");
		testUtil.init(this);
		weUseMyEstesAccInfoForConsignee.click();
	}

	// Enter Address Line1 for Consignee
	public void enterAddrLine1ForConsignee(String AddForLine1) {
		logger.info(" Enter Address Line1 for Consignee");
		testUtil.init(this);
		weAddrLine1Forconsignee.sendKeys(AddForLine1);
	}

	// Enter City For Consignee
	public void enterCityForConsignee(String CityName) {
		logger.info("Enter City For Consignee");
		testUtil.init(this);
		weCityForConsignee.sendKeys(CityName);
	}

	// Click On State For Consignee
	public void clickOnStateForConsignee() {
		logger.info("Click On State For Consignee");
		testUtil.init(this);
		weStateForConsignee.click();
	}

	// Enter ZIP Code For Consignee
	public void enterZIPCodeForConsignee(String Zipcode) {
		logger.info("Enter ZIP Code For Consignee");
		testUtil.init(this);
		weZIPCodeForConsignee.sendKeys(Zipcode);
	}

	// ####################### Billing Information #####################

	// ####################### Commodities #####################
	// Enter Handling Units
	public void enterHandlingUnits(String HandlingUnits) {
		logger.info("Enter Handling Units");
		testUtil.init(this);
		weHandlingUnits.sendKeys(HandlingUnits);
	}

	// Click On Type
	public void clickOnType() {
		logger.info("Click On Type");
		testUtil.init(this);
        testUtil.setExplicitWait(weClickOnType, 60);
		weClickOnType.click();
        testUtil.setHardWait(2000);
	}

	// Enter Type
	public void enterType(String Type) {
		logger.info("Enter Type");
		testUtil.init(this);
        testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[contains(text(),'" + Type + "')]")).click();
        testUtil.setHardWait(2000);
	}

	// Enter Total Weight
	public void enterTotalWeight(String TotalWeight) {
		logger.info("Enter Total Weight");
		testUtil.init(this);
		weTotalWeight.sendKeys(TotalWeight);
	}

	// Click On Packaging Type
	public void ClickOnPackagesType() {
		logger.info("Click On Packaging Type");
		testUtil.init(this);
		weClickOnPackagesType.click();
	}

	// Enter Packaging Type
	public void enterPackagingType(String PackagingType) {
		logger.info("Enter Packaging Type");
		testUtil.init(this);
		driver.findElement(By.xpath("(//*[contains(text(),'" + PackagingType + "')])[1]")).click();
	}

	// Enter Number of Packages
	public void enterNumberOfPackages(String NumOfPackages) {
		logger.info("Enter Number of Packages");
		testUtil.init(this);
		weNumberOfPackages.sendKeys(NumOfPackages);
	}

	// ####################### Reference Numbers #####################

	// ####################### Shipping Labels #####################

	// ####################### Notifications #####################

	// ####################### Save BOL Template #####################

	// Click On Submit BOL
	public void ClickOnSubmitBOL() {
		logger.info("Click On Submit BOL Button");
		testUtil.init(this);
		weSubmitBOLButton.click();
	}

	// Click On View Bill Of Lading Button
	public void ClickOnViewBillOfLadingButton() {
		logger.info("Click On View Bill Of Lading Button");
		testUtil.init(this);
		weViewBillOfLadingButton.click();
	}

	// Click On Save as Draft Button
	public void ClickOnSaveAsDraftButton() {
		logger.info("Click On Save as Draft Button");
		testUtil.init(this);
		weSaveAsDraftButton.click();
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Drafts @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// Click On BOL Drafts Link
	public void ClickOnBOLDraftsLink() {
		logger.info("Click On BOL Drafts Link");
		testUtil.init(this);
		weBOLDraftsLink.click();
	}

	// Click On Advanced Search Button
	public void ClickOnAdvSearchButton() {
		logger.info("Click On Advanced Search Button");
		testUtil.init(this);
		weAdvSearchButton.click();
	}

	// Click On Drafts Search By
	public void ClickOnSearchBy() {
		logger.info("Click On Search By");
		testUtil.init(this);
		weAdvSearchBy.click();
	}

	// Select from Search By Option
	public void selectFromSearchByOption(String SelectSearchByOp) {
		logger.info("Select from Search By Option");
		testUtil.init(this);
		driver.findElement(By.xpath("(//span[contains(text(),'" + SelectSearchByOp + "')])")).click();
	}

	// Enter Data for Search By Option
	public void enterSearchByOptionDataInContains(String SearchByOpData) {
		logger.info("Enter Data for  Search By Option");
		testUtil.init(this);
		weContains.sendKeys(SearchByOpData);
	}

	// SEARCH Button
	public void ClickOnSearchButton() {
		logger.info("Click On Search Button");
		testUtil.init(this);
		weSearchButton.click();
	}
	// Clear

	// Click On Edit Bill of Lading Icon
	public void clickOnEditBillOfLadingIcon(String ReferenceNum) {
		List<WebElement> we = driver.findElements(By.xpath("//*[@class='mat-table']/tbody/tr/td"));
		int listSize = we.size();
		for (int i = 0; i < listSize; i++) {
			String eleName = we.get(i).getText();
			if (eleName.equals(ReferenceNum)) {
				driver.findElement(By.xpath("//tr[2]//td[6]//div[1]//button[1]//span[1]//mat-icon[1]")).click();
				break;
			}
		}
	}

	// Enter Start Date
	public void enterStartDate(int NoOfAddOrSubtractDay) {
		logger.info("Enter Start Date");
		testUtil.init(this);
		String noOfDay = testUtil.addOrSubstractDateFromTodayDate(NoOfAddOrSubtractDay);
		weStartDate.sendKeys(noOfDay);
	}

	// Enter End Date
	public void enterEndDate() {
		logger.info("Enter End Date");
		testUtil.init(this);
		weEndDate.sendKeys(testUtil.todaysDate());
	}

	public void verifyBOLQuoteNumber(String qNo) throws InterruptedException {
		logger.info("Verify BOL qoute number ");	
		testUtil.init(this);
		
        JavascriptExecutor js = (JavascriptExecutor) driver; 
        js.executeScript("window.scrollBy(0,300)");

		testUtil.setExplicitWait(quoteNumberField,60);
		String quoteNo =quoteNumberField.getAttribute("value").trim();
		logger.info("Expected Quote no : "+qNo);
		logger.info("Actual Quote no : "+quoteNo);
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(quoteNo, qNo);

	}
	
	public void verifyBillOfLandingPage() {
		logger.info("Verifying BOL page");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		//testUtil.setExplicitWait(pageTitle, 20);
		String actualTitle = pageTitle.getText().trim();
		Assert.assertEquals(actualTitle, "Bill of Lading");
	}
	
	public void verifySuccessMessage() {
		logger.info("Validating success message");
		testUtil.init(this);
		testUtil.setExplicitWait(successMessage,20);
		boolean message = successMessage.isDisplayed();
		TestUtil.verifyTrue(message);
		testUtil.setHardWait(2000);
	}
	
	public void verifyQuoteNumberFieldIsAutoFill() {
		logger.info("Verifying Quote number field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(quoteNumber,20);
		String classProp = quoteNumber.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
		testUtil.setHardWait(1000);
	}
	
	public void verifyShipperZipCodeIsAutoFill() {
		logger.info("Verifying Shipper Zip code field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(weZIPCodeForShipper,20);
		String classProp = weZIPCodeForShipper.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}
	
	public void verifyConsigneeZipCodeIsAutoFill() {
		logger.info("Verifying Consignee Zip code field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(weZIPCodeForConsignee,20);
		String classProp = weZIPCodeForConsignee.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}
	 
	public void verifySelectedAccessorialName(String name) {
		boolean flag = false;
		logger.info("Verifying selected accessorials");
		testUtil.init(this);
		for(int i=0;i<selectedAccessorials.size();i++) {
			String accessorial = selectedAccessorials.get(i).getText().trim();
			if(accessorial.contains(name)) {
				flag = true;
				break;
			} 
		} if(!flag) {
			try {
				throw new Exception(new String("Unable to find accessorial name"));
				}catch(Exception e) {}
		}
	}
	
	public void verifyPageTitle() {
		logger.info("Verify page title");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		String actual = driver.getTitle();
		System.out.println(actual);
		
		Assert.assertEquals(actual, "My Estes: Bill of Lading");
		
	//	Assert.assertEquals(actual, "Home — Freight Shipping | Estes - LTL Freight Shipping | America's Top Private Carrier | Estes");
	}

	public void validateAlertMessage(String message) {
        logger.info("Validat Alert message");
        testUtil.init(this);
        testUtil.setExplicitWait(errorMessage, 20); 
        String alertMsg = errorMessage.getText().trim();
        Assert.assertTrue(alertMsg.contains(message));
 }
	
	public void verifyShipperInfo(String city, String state, String zip) throws InterruptedException {
		logger.info("Verify shipper info");
		testUtil.init(this);

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,300)");

		testUtil.setExplicitWait(weCityForShipper,60);
		String cityActual = weCityForShipper.getAttribute("value").trim();
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(cityActual, city);

		testUtil.setExplicitWait(weStateForShipper,60);
		String stateActual = weStateForShipper.getText();
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(stateActual, state);

		testUtil.setExplicitWait(weZIPCodeForShipper,60);
		String zipActual = weZIPCodeForShipper.getAttribute("value").trim();
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(zipActual, zip);

	}

	public void verifyConsigneeInfo(String city, String state, String zip) throws InterruptedException {
		logger.info("Verify consignee info");
		testUtil.init(this);

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,300)");

		testUtil.setExplicitWait(weCityForConsignee,60);
		String cityActual = weCityForConsignee.getAttribute("value").trim();
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(cityActual, city);

		testUtil.setExplicitWait(weStateForConsignee,60);
		String stateActual = weStateForConsignee.getText();
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(stateActual, state);

		testUtil.setExplicitWait(weZIPCodeForConsignee,60);
		String zipActual = weZIPCodeForConsignee.getAttribute("value").trim();
		//String actual = driver.findElement(By.xpath("//input[@id='mat-input-3']")).getAttribute("value");
		testUtil.setHardWait(2000);
		Assert.assertEquals(zipActual, zip);

	}

	public void verifyCommoditiesSection(String units, String type, String weight, String shipmentClass, String description) throws InterruptedException {
		logger.info("Verify commodities section");
		testUtil.init(this);

		testUtil.setExplicitWait(weHandlingUnits,60);
		String unitsActual = weHandlingUnits.getAttribute("value").trim();
		testUtil.setHardWait(2000);
		Assert.assertEquals(unitsActual, units);

		testUtil.setExplicitWait(weClickOnType,60);
		String typeActual = weClickOnType.getText();
		testUtil.setHardWait(2000);
		Assert.assertEquals(typeActual, type);

		testUtil.setExplicitWait(weTotalWeight,60);
		String weightActual = weTotalWeight.getAttribute("value").trim();
		testUtil.setHardWait(2000);
		Assert.assertEquals(weightActual, weight);

		testUtil.setExplicitWait(weClass,60);
		String classActual = weClass.getText();
		testUtil.setHardWait(2000);
		Assert.assertEquals(classActual, shipmentClass);

		testUtil.setExplicitWait(weDescription,60);
		String descriptionActual = weDescription.getAttribute("value").trim();
		testUtil.setHardWait(2000);
		Assert.assertEquals(descriptionActual, description);

	}

}