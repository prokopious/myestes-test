
package rateQuotePages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.apache.log4j.Logger;

import com.github.javafaker.Faker;

import testBase.TestBase;
import util.TestUtil;

public class RateQouteRateEstimatePage extends TestBase {

	private Logger logger = Logger.getLogger(RateQouteRateEstimatePage.class);

	@FindBy(how = How.ID, using = "full_name")
	private WebElement YourNameField;

	@FindBy(how = How.ID, using = "contactName")
	private WebElement ContactNameField;

	@FindBy(how = How.ID, using = "mat-input-0")
	private WebElement OrginZipField;

	@FindBy(how = How.ID, using = "mat-input-3")
	private WebElement DestinationZipField;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass0']//div[@class='mat-select-arrow']")
	private WebElement SelectClass;
	
	
	@FindBy(how=How.XPATH,using="//mat-select[@id=\"shipClass1\"]//div[@class='mat-select-arrow']")
	private WebElement selectClass1;
	
	@FindBy(how = How.ID, using = "weight0")
	private WebElement TotalWeightField;

	@FindBy(how = How.XPATH, using = "//*[@id='weight1']")
	private WebElement totalWeightField1;
	
	@FindBy(how = How.ID, using = "mat-input-1")
	private WebElement City;

	@FindBy(how = How.ID, using = "mat-input-4")
	private WebElement DesCity;

	@FindBy(how = How.ID, using = "mat-input-2")
	private WebElement State;

	@FindBy(how = How.ID, using = "mat-input-5")
	private WebElement DesState;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SubmitButton;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-0']//div[@class='mat-select-arrow']")
	private WebElement Country;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-1']")
	private WebElement DesCountry;

	@FindBy(how = How.XPATH, using = "//tr[1]//td[3]//app-service-level-action[1]//button[1]")
	private WebElement Qoute;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(),'Clear')])[1]")
	private WebElement OrClear;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(),'Clear')])[2]")
	private WebElement DesClear;

	@FindBy(how = How.ID, using = "description0")
	private WebElement weDesc;

	@FindBy(how=How.XPATH,using="//*[@id='description1']")
	private WebElement descriptionField1;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),' Revise Quote ')]")
	private WebElement Revise;
	
	@FindBy(css="h1 span")
	private WebElement pageTitle; 
	
	@FindBy(css="div[class*='ng-star-inserted alert alert-danger'] span")
	private WebElement alertMessage;
	
	@FindBy(xpath="//tr[2]//span[text()='Create BOL']")
	private WebElement createBOL;
	
	@FindBy(xpath="//*[text()='Results']")
	private WebElement rateQuoteResult;

	@FindBy(id = "declaredValue")
	private WebElement fullValueCoverage;
	
	@FindBy(xpath="//mat-checkbox[@id='declaredValueWaived']")
    private WebElement checkboxFullCoverage;
	
	@FindBy(how=How.XPATH,using="//div[1]/button[contains(text(), 'Add Commodity')]")
    private WebElement addCommodityBtn;
	
	@FindBy(how=How.XPATH,using="//*[@id='accessorial14']/label/div")
	private WebElement overlenghtCheckbox;
	
	@FindBy(how=How.XPATH,using="//*[@id='accessorial21']/label/div")
	private WebElement residentialDelivery;
	/********************************METHODS******************************/
	// This is testing

	public void enterYourName() {
		logger.info("Enter your name");
		testUtil.init(this);
		Faker faker = new Faker();
		YourNameField.sendKeys(faker.address().firstName());
	}

	public void enterContactName() {
		logger.info("Enter Contact name");
		testUtil.init(this);
		Faker faker = new Faker();
		ContactNameField.sendKeys(faker.address().firstName());
	}

	public void enterOrginZip(String oZip) {
		logger.info("Enter Origin Zip Code");
		testUtil.init(this);
		testUtil.setHardWait(2000);
	    OrginZipField.clear();
	    OrginZipField.click();
	    OrginZipField.sendKeys(oZip);
		driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
		testUtil.setHardWait(2000);

	}

	public void enterOriginCity() {
		logger.info("Enter Origin City name");
		testUtil.init(this);
		City.sendKeys("Richmond");
		// driver.findElement(By.xpath("//span[contains(text(),'RICHMOND, VA
		// 23230')]")).click();

	}

	public void enterOriginState(String state) {
		logger.info("Enter origin state name");
		testUtil.init(this);
		State.clear();
		State.sendKeys(state);
		// driver.findElement(By.xpath("//span[contains(text(),'RICHMOND,
		// VA')]")).click();

	}

	public void enterDestinationState(String state) throws InterruptedException {
		logger.info("enter Destination State Name");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		DesState.clear();
		DesState.sendKeys(state);
		testUtil.setHardWait(2000);
		// driver.findElement(By.xpath("//span[contains(text(),'ATLANTA, GA
		// 30307')]")).click();

	}

	public void enterDestinationCity(String city) {
		logger.info("enter Destination City Name");
		testUtil.init(this);
		DesCity.clear();
		DesCity.sendKeys(city);
		// driver.findElement(By.xpath("//input[@id='mat-input-4']")).click();

	}

	public void enterDestinationZip(String dZip) {
		logger.info("Enter Destination Zip Code");
		testUtil.init(this);
		DestinationZipField.clear();
		DestinationZipField.sendKeys(dZip);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
	}

	public void selectClass(String ClassNo) throws InterruptedException {
		logger.info("Select Class");
		testUtil.init(this);
		testUtil.clickElementJavascript(SelectClass);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + ClassNo + "')]")).click();
	}

	public void selectClass1(String classNo) {
		logger.info("Select Class1 after clickling on Add commodity");
		testUtil.init(this);
		testUtil.clickElementJavascript(selectClass1);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();	
		
	}
	
	public void selectOriginCountry(String CountryName) {
		testUtil.init(this);
		logger.info("Select Origin Country");
		testUtil.clickElementJavascript(OrClear);
		testUtil.clickElementJavascript(Country);
		WebElement CName = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + CountryName + "')]"));
		testUtil.clickElementJavascript(CName);

	}

	public void selectDestinationCountry(String CountryN) {
		logger.info("Select destination country");
		testUtil.init(this);
		testUtil.clickElementJavascript(DesClear);
		testUtil.setExplicitWait(DesCountry, 60);
		testUtil.clickElementJavascript(DesCountry);
		WebElement CName1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + CountryN + "')]"));
		testUtil.clickElementJavascript(CName1);
	}

	public void enterDesc(String des) {
		logger.info("Enter descriptions");
		testUtil.init(this);
		weDesc.clear();
		weDesc.sendKeys(des);
	}
	
	public void enterDesc1(String des) {
		logger.info("Enter descriptions 1");
		testUtil.init(this);
		testUtil.assetWait(null, descriptionField1, 10, 200, TimeUnit.MILLISECONDS);
		descriptionField1.clear();
		descriptionField1.sendKeys(des);
	}
	


	public void enterTotalWeight(String weight) {
		logger.info("Enter \"Total Weight\"");
		testUtil.init(this);
		TotalWeightField.clear();
		TotalWeightField.sendKeys(weight);
	}
	
	public void enterTotalWeight1(String weight) {
		logger.info("Enter Total Weight1");
		testUtil.init(this);
		totalWeightField1.clear();
		totalWeightField1.sendKeys(weight);
	}

	public void clickOnSubmitButton() {
		logger.info("Click on \"Submit Request\" button");
		testUtil.init(this);
		testUtil.setHardWait(4000);
		testUtil.assetWaitClickable(null, SubmitButton, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(SubmitButton); 
	}

	public void clickOnGetQouteButton() throws InterruptedException {
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(Qoute);
		logger.info("Click on GET QUOTE button");
		testUtil.clickElementJavascript(Qoute);
		testUtil.setHardWait(2000);
	}

	public void clickOnReviseQouteButton() {
		logger.info("Click on Revise Quote button");
		testUtil.init(this);
		Revise.click();
	}

	public void clickOnTheShipmentTrackingLinkUnderTheQuickLinks() {
		logger.info("Click on the *Shipment Tracking* link under the Quick Links");
		testUtil.init(this);
		driver.findElement(By.xpath("//i[@class='link-icon fal fa-box-alt']")).click();
	}

	public void clickOnTheImageRetrievalLinkUnderTheQuickLinks() {
		logger.info("Click on the *Image Retrieval* link under the Quick Links.");
		testUtil.init(this);
		driver.findElement(By.xpath("//span[contains(text(),'Image Retrieval')]")).click();
	}

	public void verifyQuoteNumberPrefixIsL(String letter) throws InterruptedException {
		logger.info("Verify Qoute Number Parefix is Letter L");
		testUtil.init(this);
		WebElement qoute = driver.findElement(By.xpath("(//button[contains(text(),'Quote #')])[1]"));
		testUtil.clickElementJavascript(qoute);
		Thread.sleep(2000);
		String quoteNumber = driver.findElement(By.xpath("(//*[contains(text(),'Rate Quote Number: ')])[1]")).getText();
		System.out.println(quoteNumber);
		assertTrue(quoteNumber.startsWith(letter, 19));
	}

	public void verifyAttentionMessageOriginPoint() {
		logger.info("verify attention message");
		String message = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-create-rate-estimate/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-estimate-drawer/div/div/div[1]/app-quote-details/app-transit-message/lib-feedback/div"))
				.getText();
		System.out.println("Message is :" + message);
		String expected = "Origin point is not serviced direct. Service is 9 days from LAREDO, TX exchange point. Call LAREDO, TX for assistance.";
		Assert.assertEquals(message, expected);
	}

	public void verifyAttentionMessageDestinationPoint() {
		logger.info("Verify attention message");
		String message = driver.findElement(By.xpath(
				"//*[@id='content']/div/div[1]/app-create-rate-estimate/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-estimate-drawer/div/div/div[1]/app-quote-details/app-transit-message/lib-feedback/div"))
				.getText();
		System.out.println("Message is :" + message);
		//String expected = "Destination point is interlined with TP FREIGHT LINES. Service is 9 days to PORTLAND, OR exchange point." //changed to below line
		String expected = "Destination point is interlined with TP FREIGHT LINES. Service is 7 days to PORTLAND, OR exchange point.";
		Assert.assertEquals(message, expected);
	}

	public void verifyResultTable() throws InterruptedException {
		logger.info("verify result table display");
		testUtil.checkPageIsReady();
		testUtil.printWebTableData();
	}

	public void verifyDiscountIdDisplay() {
		logger.info("verify discount ID display");
		testUtil.verifyAndPrintWebTableData(
				"//*[@id=\"content\"]/div/div[1]/app-create-rate-estimate/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-estimate-drawer/div/app-quote-charge-items/div/table");

	}

	public void verifyChargedItemsDisplay() {
		logger.info("Verify charged items display");
		testUtil.verifyAndPrintWebTableData(
				"//*[@id=\"content\"]/div/div[1]/app-create-rate-estimate/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-estimate-drawer/div/app-quote-charge-items/div/table");

	}

	public void validateQuickLinksAreDisplayedOnTheLeftSideNavigationMenu() {
		logger.info("Validate Quick Links are displayed on the left side navigation menu.");
		testUtil.isDisplayed(driver.findElement(By.xpath("//div[@class='quick-link quick-link--header']")));

	}

	public void validatePageTitle() {
		logger.info("Validate Current Page Title");
		testUtil.getCurrentPageTitle();

	}

	public void clickOnRequestPickup() {
		logger.info("Click on Request Pickup button under Quote Details");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		driver.findElement(By.xpath("(//span[contains(text(),'Request Pickup')])")).click();

	}

	public void clickOnGetQuoteButtonByServiceLevel(String eleName) {
		logger.info("Click on Get Quote button");
		testUtil.init(this);
		testUtil.assetWaitClickable("//td[contains(text(),  '" + eleName + "')]", null, 10, 200, TimeUnit.MILLISECONDS);;
		driver.findElement(By.xpath("//td[contains(text(),  '" + eleName + "')]")).click();
	}
	// UPDATED: 11/14/2019: 10:00AM
	
	public void verifyRateQuoteEstimateTitle() {
		logger.info("Validating Rate Quote Estimation Page title");
		testUtil.init(this);
		String title = pageTitle.getText().trim();
		Assert.assertEquals(title, "Rate Quote Estimate");
	}

	public void validateAlertMessage(String alert) {
		logger.info("Capturing alert message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertMessage, 20);
		String message = alertMessage.getText().trim();
		Assert.assertTrue(message.contains(alert));
	}
	
	public void clickOnCreateBOL() {
		logger.info("Clicking on Create BOL button");
		testUtil.init(this);
		testUtil.setExplicitWait(createBOL, 20);
		createBOL.click();
	}

	public void verifyRateQuoteResultPage() {
		logger.info("Validating Rate Quote result page");
		testUtil.init(this);
		testUtil.setExplicitWait(rateQuoteResult,20);
		boolean resultPage = rateQuoteResult.isDisplayed();
		testUtil.setHardWait(1000);
		TestUtil.verifyTrue(resultPage);
	}
	
	public void enterFullValueCoverage(String value) {
		logger.info("Enter Full value coverage: "+value);
		testUtil.init(this);
		testUtil.setExplicitWait(fullValueCoverage, 10);
		fullValueCoverage.sendKeys(value);
		testUtil.setHardWait(1000);
	}
	
	public void verifyCheckBoxIsChecked() {
		logger.info("Validate I would like additional checkbox is checked");
		testUtil.init(this);
		String properties = checkboxFullCoverage.getAttribute("class");
		//String properties= checkboxFullCoverage.getText();
		Assert.assertTrue(properties.contains("mat-checkbox-checked"));
	}

	public void verifyFieldsArePopulatedWithCurrentQuoteData() {
		logger.info("Verify Fields Are Populated With Current Quote Data");

		boolean contactName = driver.findElement(By.id("contactName")).isDisplayed();
		assertTrue(contactName);
		testUtil.setHardWait(500);
		logger.info("Contact Name Field Displayed");
		
		boolean oCountry = driver.findElement(By.xpath(
				"//app-routing-information[1]/mat-card[1]/mat-card-content[1]/form[1]/div[1]//mat-select[1]/div[1]/div[1]/span[1]"))
				.isSelected();
		assertFalse(oCountry);
		testUtil.setHardWait(500);
		logger.info("Origin Country is selected");
		
		boolean oZip = driver.findElement(By.xpath("(//*[@ng-reflect-name=\"zip\"])[1]")).isDisplayed();
		assertTrue(oZip);
		testUtil.setHardWait(500);
		logger.info("Origin Zip fieldis displayed");
		
		boolean oCity = driver.findElement(By.xpath("(//*[@ng-reflect-name=\"city\"])[1]")).isDisplayed();
		assertTrue(oCity);
		testUtil.setHardWait(500);
		logger.info("Origin City field is displayed");
		
		boolean oState = driver.findElement(By.xpath("(//*[@ng-reflect-name=\"state\"])[1]")).isDisplayed();
		assertTrue(oState);
		testUtil.setHardWait(500);
		logger.info("Origin State field is displayed");
		
		boolean dCountry = driver.findElement(By.xpath(
				"//app-routing-information[1]/mat-card[1]/mat-card-content[1]/form[1]/div[2]/app-point-control[1]/form[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]/mat-select[1]"))
				.isSelected();
		assertFalse(dCountry);
		testUtil.setHardWait(500);
		logger.info("Destination Country is selected");
		
		boolean dZip = driver.findElement(By.xpath("(//*[@ng-reflect-name=\"city\"])[2]")).isDisplayed();
		assertTrue(dZip);
		testUtil.setHardWait(500);
		logger.info("Destination Zip fieldis displayed");
		
		boolean dCity = driver.findElement(By.xpath("(//*[@ng-reflect-name=\"zip\"])[2]")).isDisplayed();
		assertTrue(dCity);
		testUtil.setHardWait(500);
		logger.info("Destination City field is displayed");
		
		boolean dState = driver.findElement(By.xpath("(//*[@ng-reflect-name=\"state\"])[2]")).isDisplayed();
		assertTrue(dState);
		testUtil.setHardWait(500);
		logger.info("Destination State field is displayed");
			
		boolean weight = driver.findElement(By.xpath("//*[@ng-reflect-name=\"weight\"]")).isDisplayed();
		assertTrue(weight);
		testUtil.setHardWait(500);
		logger.info("Weight field is displayed");

}
	
	public void clickOnContactMe(String serviceLevel) {
        logger.info("Click Contact Me in " + serviceLevel);
        testUtil.init(this);

        List<WebElement> rowCount = driver.findElements(By.xpath("//*[@id='main']//app-rate-request-results/mat-card/table/tbody//tr[*]"));
        System.out.println(rowCount.size());
        for (int i = 0; i < rowCount.size(); i++) {
                       int j = i + 1;
                       String actualServiceLevel = driver.findElement(By.xpath("//*[@id='main']//app-rate-request-results/mat-card/table/tbody/tr["+ j + "]/td[1]")).getText().trim();
                       if (actualServiceLevel.equals(serviceLevel)) {
                                      driver.findElement(By.xpath("//*[@id='main']//app-rate-request-results/mat-card/table/tbody/tr["+ j + "]//span/a")).click();
                                      break;
                       }
        }
	}
	
	
	public void enterContactName(String name) {
		logger.info("Enter Contact name");
		testUtil.init(this);
		ContactNameField.clear();
		ContactNameField.sendKeys(name);
	}

	public void verifyName(String name) {
        logger.info("Verify Rate Quote Name from Contact Information");
        testUtil.init(this);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String contactNme = (String) js.executeScript("return document.getElementById('contactName').value");
        Assert.assertEquals(contactNme, name);
 }

 

 public void verifyZipFromContactInformation(String zip) {
        logger.info("Verify Rate Quote ZIP from Contact Information");
        testUtil.init(this);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String zipcode = (String) js.executeScript("return document.getElementById('mat-input-0').value");
        Assert.assertEquals(zipcode, zip);
 }

 public void verifyDestZipFromContactInformation(String zip) {
        logger.info("Verify Rate Quote Destination ZIP from Contact Information");
        testUtil.init(this);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String zipcode = (String) js.executeScript("return document.getElementById('mat-input-3').value");
        Assert.assertEquals(zipcode, zip);
 }

 public void verifyWeightFromContactInformation(String weight) {
        logger.info("Verify Rate Quote Weight from Contact Information");
        testUtil.init(this);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String weightNo = (String) js.executeScript("return document.getElementById('weight0').value");
        Assert.assertEquals(weightNo, weight);
 }

 public void verifyvalueFromFullValueCoverage(String value) {
        logger.info("Verify Rate Quote Weight from Contact Information");
        testUtil.init(this);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String valueNo = (String) js.executeScript("return document.getElementById('declaredValue').value");
        Assert.assertEquals(valueNo, value);
 }

	public void clickOnAddCommodity() {
		logger.info("Click on \"Add Commodity\"");
		testUtil.init("this");
		testUtil.setExplicitWait(addCommodityBtn, 20);
		addCommodityBtn.click();

	}

	
	public void clickOnOverlengthFreight() {
		logger.info("Click on \"Overlengh Freight\"");
		testUtil.init(this);
		testUtil.setExplicitWait(overlenghtCheckbox, 90);
		overlenghtCheckbox.click();

	}
	
	public void clickOnResidentialDelivery() {
		
		testUtil.init(this);
		logger.info("Click on \"Residential Delivery\" under accessorials");
		testUtil.setExplicitWait(residentialDelivery, 60);
		residentialDelivery.click();
	}
}
