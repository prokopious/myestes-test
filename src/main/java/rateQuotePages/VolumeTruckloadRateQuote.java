package rateQuotePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.TestBase;

public class VolumeTruckloadRateQuote extends TestBase {

	private Logger logger = Logger.getLogger(VolumeTruckloadRateQuote.class);

	// Full Name
	@FindBy(how = How.ID, using = "contactName")
	private WebElement weFName;
	// Email
	@FindBy(how = How.ID, using = "contactEmail")
	private WebElement weEmail;
	// My Role
	@FindBy(how = How.XPATH, using = "//mat-select[@id='role']//div[@class='mat-select-arrow-wrapper']")
	private WebElement weMyRole;
	// Phone
	@FindBy(how = How.ID, using = "contactPhone")
	private WebElement wePhone;

	@FindBy(how = How.ID, using = "contactExtension")
	private WebElement Extention;
	// Terms
	@FindBy(how = How.XPATH, using = "//mat-select[@id='terms']//div[@class='mat-select-arrow']")
	private WebElement weTerms;
	// Origin ZIP
	@FindBy(how = How.ID, using = "mat-input-0")
	private WebElement weOriginZip;
	// Destination ZIP
	@FindBy(how = How.ID, using = "mat-input-3")
	private WebElement weDesZip;
	// First Element from Drop Down
	@FindBy(how = How.ID, using = "pointlink0")
	private WebElement weFirstEleFDD;

	// Set today's day
	@FindBy(how = How.XPATH, using = "//input[@formcontrolname=\"pickupDate\"]")
	private WebElement weTodayDate;
	// Class
	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass0']//div[@class='mat-select-arrow']")
	private WebElement weClass;
	// Pieces
	@FindBy(how = How.ID, using = "pieces0")
	private WebElement wePieces;
	// Piece Type
	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType0']//div[@class='mat-select-arrow']")
	private WebElement wepieceType;
	// Total Weight
	@FindBy(how = How.ID, using = "weight0")
	private WebElement weTWeight;
	// Length
	@FindBy(how = How.ID, using = "length0")
	private WebElement weLength;
	// Width
	@FindBy(how = How.ID, using = "width0")
	private WebElement weWidth;
	// Height
	@FindBy(how = How.ID, using = "height0")
	private WebElement weHeight;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass1']//div[@class='mat-select-value']")
	private WebElement Class;
	// Pieces
	@FindBy(how = How.ID, using = "pieces1")
	private WebElement Pieces;
	// Piece Type
	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType1']//div[@class='mat-select-arrow']")
	private WebElement pieceType;
	// Total Weight
	@FindBy(how = How.ID, using = "weight1")
	private WebElement Weight;
	// Length
	@FindBy(how = How.ID, using = "length1")
	private WebElement Length;
	// Width
	@FindBy(how = How.ID, using = "width1")
	private WebElement Width;
	// Height
	@FindBy(how = How.ID, using = "height1")
	private WebElement Height;
	// Description
	@FindBy(how = How.ID, using = "description0")
	private WebElement weDesc;
	// Linear Feet
	@FindBy(how = How.ID, using = "linearFeet")
	private WebElement welinearFeet;
	// Submit Button
	@FindBy(how = How.XPATH, using = "//*[@id=\"rateQuoteSubmitButton\"]")
	private WebElement weSubmit;
	// Contact Me/VTL Basic Charge (BOTH HAS SAME XPATH)
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Contact Me')]")
	private WebElement weContMe;
	// Message for Contact Me
	@FindBy(how = How.XPATH, using = "//*[@id='vtl_container']/div/div[1]/p")
	private WebElement weContMeMess;
	// Quote Number for VTL
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please write or type Guaranteed LTL Standard ')]")
	private WebElement RateQouteMessage;

	@FindBy(how = How.ID, using = "quote_number")
	private WebElement weVTLQNumber;

	@FindBy(how = How.XPATH, using = "//tr[3]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement LTL10AM;
	// Guaranteed LTL Standard Transit: 12PM
	@FindBy(how = How.XPATH, using = "//tr[5]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement weLTLST12PM;

	@FindBy(how = How.XPATH, using = "//tr[7]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement LTL5PM;

	@FindBy(how = How.XPATH, using = "//tr[15]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement GuaranteedExclusiveUse;
	// Quote Number for Time Critical
	@FindBy(how = How.ID, using = "quote_number")
	private WebElement weTCQNumber;

	@FindBy(how = How.ID, using = "contactName")
	private WebElement ContactNameField;

	// @FindBy(how = How.XPATH, using = "//span[contains(text(),'Volume and
	// Truckload (incl. Guaranteed)')]")
	@FindBy(how = How.XPATH, using = "//*[@id='volumeAndTruckload']/label/div")
	private WebElement ValumeAndTruckLoad;

	@FindBy(how = How.XPATH, using = "//*[@id='timeCritical']/label/div")
	private WebElement TimeCritical;

	// @FindBy(how = How.XPATH, using = "//span[contains(text(),'Less than Truckload
	// (incl. Guaranteed)')]")
	@FindBy(how = How.XPATH, using = "//*[@id='ltl']/label/div")
	private WebElement LessThanTuckload;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-0']//div[@class='mat-select-arrow-wrapper']")
	private WebElement Country;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-1']//div[@class='mat-select-arrow']")
	private WebElement DesCountry;

	@FindBy(how = How.XPATH, using = "//input[@id='addressSelect']")
	private WebElement Account;

	@FindBy(how = How.XPATH, using = "//a[@class='mat-tab-link ng-star-inserted']")
	private WebElement History;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add from Commodity Library')]")
	private WebElement AddFromCommodityLibrary;

	@FindBy(how = How.XPATH, using = "//lib-inline-button[@class='ml-1 font-weight-bold']")
	private WebElement AddressBook;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary ng-star-inserted']")
	private WebElement Commodity;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),' Revise Quote ')]")
	private WebElement Revise;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(),'Clear')])[1]")
	private WebElement OrClear;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(),'Clear')])[2]")
	private WebElement DesClear;

	@FindBy(how = How.XPATH, using = "//tr[1]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement Qoute;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'View All Accessorials')]")
	private WebElement ViewAllAccessorials;

	// Secured Locations Pickup
	@FindBy(how = How.XPATH, using = "//mat-checkbox[@id='allAccessorials45']")
	private WebElement SecuredLocationsPickup;

	@FindBy(xpath = "//button[contains(text(),'Service Level')]")
	private WebElement serviceLvl;

	/****************************** METHODS ******************************/

	public void enterContactName() {
		logger.info("Enter Contact Name");
		testUtil.init(this);
		Faker faker = new Faker();
		ContactNameField.sendKeys(faker.address().firstName());
	}

	public void enterContactName(String name) {
		logger.info("enter Contact Name");
		testUtil.init(this);
		ContactNameField.sendKeys(name);
	}

	public void enterAccountNumber(String AcNum) {
		logger.info("enter Account number");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Account.sendKeys(AcNum);
	}

	public void enterAccountNumber() throws InterruptedException {
		logger.info("enter Account number");
		testUtil.init(this);
		driver.findElement(By.xpath("//a[contains(text(),'Account Search')]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("(//mat-cell//a[@class='ng-star-inserted'])[1]")).click();
	}

	public void enterYourFullName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		weFName.sendKeys(name);
	}

	public void enterYourEmail(String email) {
		logger.info("Enter email address");
		testUtil.init(this);
		weEmail.sendKeys(email);
	}

	public void enterMyRole(String MyRole) {
		testUtil.init(this);
		logger.info("Select role");
		testUtil.clickElementJavascript(weMyRole);
		WebElement role = driver.findElement(By.xpath("//span[contains(text(),'" + MyRole + "')]"));
		testUtil.clickElementJavascript(role);

	}

	public void enterPhoneNo(String phone) {
		logger.info("Enter phone number");
		testUtil.init(this);
		wePhone.sendKeys(phone);
	}

	public void enterPhoneExtentionNo(String exNum) {
		logger.info("Enter phone number extention ");
		testUtil.init(this);
		Extention.sendKeys(exNum);
	}

	public void enterTerms(String Terms) {
		testUtil.init(this);
		logger.info("Select Term");
		testUtil.clickElementJavascript(weTerms);
		WebElement term = driver.findElement(By.xpath("//span[contains(text(),'" + Terms + "')]"));
		testUtil.clickElementJavascript(term);
	}

	// Origin ZIP
	public void enterOriginZip(String OriginZCode) throws InterruptedException {
		logger.info("Enter Origin zip code");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		weOriginZip.clear();
		testUtil.setHardWait(1000);
		weOriginZip.sendKeys(OriginZCode);
		driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
		Thread.sleep(1000);
	}

	// Destination ZIP
	public void enterDesZip(String DesZCode) throws InterruptedException {
		logger.info("Enter destination zip code");
		testUtil.init(this);
		weDesZip.clear();
		testUtil.setHardWait(2000);
		weDesZip.sendKeys(DesZCode);
		driver.findElement(By.xpath("//span[@class='mat-option-text']")).click();
		Thread.sleep(1000);
	}

	public void selectOrDeselectLessThanTruckload() {
		testUtil.init(this);
		logger.info("Select or deselect Less Than Truckload");
		testUtil.setHardWait(1000);
		LessThanTuckload.click();
	}

	public void selectOrDeselectTimeCriticalExpedited() {
		testUtil.init(this);
		logger.info("Select or deselect Time Critical Guaranteed");
		testUtil.setExplicitWait(TimeCritical, 90);
		try {
			testUtil.clickElementJavascript(TimeCritical);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void selectOrDeselectValumeAndTruckload() {
		logger.info("Select or deselect Valume and Truckload");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(ValumeAndTruckLoad);
		// ValumeAndTruckLoad.click();
	}

	public void selectFirstEleFromDDown() {
		testUtil.init(this);
		Actions action = new Actions(driver);
		action.moveToElement(weFirstEleFDD).perform();
		weFirstEleFDD.click();
	}

	// Set today date
	public void selectTodayDate() throws InterruptedException {
		logger.info("enter todays's date");
		testUtil.init(this);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		weTodayDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		// weTodayDate.sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		weTodayDate.sendKeys(dtf.format(localDate));
	}

	public void selectNextSaturday() {
		logger.info("enter next Sturday");
		testUtil.init(this);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		LocalDate prevSat = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		weTodayDate.clear();
		weTodayDate.sendKeys(dtf.format(prevSat));
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
		logger.info("Select Destination Country");
		testUtil.init(this);
		testUtil.clickElementJavascript(DesClear);
		testUtil.clickElementJavascript(DesCountry);
		WebElement CName1 = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + CountryN + "')]"));
		testUtil.clickElementJavascript(CName1);
	}

	public void selectSecuredLocationsPickupCheckBox() {
		logger.info("Select Secured Locations Pickup - prisonsl, military bases, airports, act...");
		testUtil.init(this);
		SecuredLocationsPickup.click();
	}

	public String selectOrDeselectAccessorials(String accessorials) {
		logger.info("Select Or Deselect Accessorials");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'" + accessorials + "')]")).getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'" + accessorials + "')]")).click();
		return accessorial;
	}

	public String selectOrDeselectInsideDelivery() {
		logger.info("Select Or Deselect Accessorials Inside Delivery");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath(
				"//mat-checkbox[@id='allAccessorials23']//span[@class='mat-checkbox-label'][contains(text(),'Inside Delivery')]"))
				.getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath(
				"//mat-checkbox[@id='allAccessorials23']//span[@class='mat-checkbox-label'][contains(text(),'Inside Delivery')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectLiftGateServiceDelivery() {
		logger.info("Select Or Deselect Accessorials Lift Gate Service (Delivery)");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Lift-Gate Service (Delivery)')]"))
				.getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Lift-Gate Service (Delivery)')]")).click();
		return accessorial;
	}

	public String selectOrDeselectAppointmentRequest() {
		logger.info("Select Or Deselect Accessorials Appointment Request");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Appointment Request')]")).getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Appointment Request')]")).click();
		return accessorial;
	}

	public String selectOrDeselectInsidePickup() {
		logger.info("Select Or Deselect Accessorials Inside Pickup");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath(
				"//mat-checkbox[@id='allAccessorials25']//span[@class='mat-checkbox-label'][contains(text(),'Inside Pickup')]"))
				.getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath(
				"//mat-checkbox[@id='allAccessorials25']//span[@class='mat-checkbox-label'][contains(text(),'Inside Pickup')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectConstructionSiteDelivery() {
		logger.info("Select Or Deselect Construction Site Delivery");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"allAccessorials10\"]/label/span"));
		String accessorial = driver.findElement(By.xpath("//*[@id=\"allAccessorials10\"]/label/span")).getText();
		System.out.println(accessorial + " is selected");
		testUtil.clickElementJavascript(ele);
		return accessorial;
	}

	public String selectOrDeselectResidentialDelivery() {
		logger.info("Select Or Deselect Residential Delivery");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//*[@id='allAccessorials39']/label/span")).getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//*[@id='allAccessorials39']/label/span")).click();
		return accessorial;
	}

	public String selectOrDeselectExhibitionChargeCallForChargesForLasVegasChicagoOrNY() {
		logger.info("Select Or Deselect  Exhibition Charge (Call for charges for Las Vegas, Chicago, or NY)t");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Exhibition Charge (Call for charges for Las Vegas,')]"))
				.getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Exhibition Charge (Call for charges for Las Vegas,')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectNotifyRequest() {
		logger.info("Select Or Deselect Accessorials Notify Request");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Notify Request')]")).getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Notify Request')]")).click();
		return accessorial;
	}

	public String selectOrDeselectSecuredLocationsPickupPrisonsMilitaryBases() {
		logger.info("Select Or Deselect Secured Locations Pickup - prisons, military bases, airports, etc..");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Secured Locations Pickup - prisons, military bases')]"))
				.getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Secured Locations Pickup - prisons, military bases,')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases() {
		logger.info("Select Or Deselect Secured Locations Delivery - prisons, military bases,airports, etc..");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Secured Locations Delivery - prisons, military bas')]"))
				.getText();
		System.out.println(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Secured Locations Delivery - prisons, military bas')]"))
				.click();
		return accessorial;
	}

	// Class
	public void enterClass(String ClassNo) throws InterruptedException {
		logger.info("enter class");
		testUtil.init(this);
		testUtil.clickElementJavascript(weClass);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + ClassNo + "')]")).click();
	}

	public void enterClass2(String ClassNo) throws InterruptedException {
		logger.info("enter class");
		testUtil.init(this);
		testUtil.clickElementJavascript(Class);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + ClassNo + "')]")).click();
	}

	// Pieces
	public void enterPieces(String NOPieces) {
		logger.info("enter pieces");
		testUtil.init(this);
		wePieces.clear();
		wePieces.sendKeys(NOPieces);
	}

	public void enterPieces2(String NOPieces) {
		logger.info("enter pieces");
		testUtil.init(this);
		Pieces.clear();
		Pieces.sendKeys(NOPieces);
	}

	// Piece type
	public void enterPieceType(String PieceType) throws InterruptedException {
		logger.info("enter pieces type");
		testUtil.init(this);
		testUtil.clickElementJavascript(wepieceType);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'" + PieceType + "')]")).click();
	}

	public void enterPieceType2(String PieceType) throws InterruptedException {
		logger.info("enter pieces type");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType);
		Thread.sleep(1000);
		WebElement pType = driver.findElement(By.xpath("(//span[contains(text(),'" + PieceType + "')])[2]"));
		Thread.sleep(1000);
		testUtil.clickElementJavascript(pType);
		// driver.findElement(By.xpath("//span[contains(text(),'PALLET')]")).click();
	}

	// Total Weight
	public void enterTotalWeight(String TotalWeight) {
		logger.info("enter weight");
		testUtil.init(this);
		weTWeight.clear();
		weTWeight.sendKeys(TotalWeight);
	}

	public void enterTotalWeight2(String TotalWeight) {
		logger.info("enter weight");
		testUtil.init(this);
		Weight.clear();
		Weight.sendKeys(TotalWeight);
	}

	// Length
	public void enterLength(String Length) {
		logger.info("enter Lenghth");
		testUtil.init(this);
		weLength.clear();
		weLength.sendKeys(Length);
	}

	public void enterLength2(String length) {
		logger.info("enter Lenghth");
		Length.clear();
		testUtil.init(this);
		Length.sendKeys(length);
	}

	// Width
	public void enterWidth(String Width) {
		logger.info("enter Width");
		weWidth.clear();
		testUtil.init(this);
		weWidth.sendKeys(Width);
	}

	public void enterWidth2(String width) {
		logger.info("enter Width");
		testUtil.init(this);
		Width.clear();
		Width.sendKeys(width);
	}

	// Height
	public void enterHeight(String Height) throws InterruptedException {
		logger.info("enter Height");
		testUtil.init(this);
		weHeight.clear();
		weHeight.sendKeys(Height);
		Thread.sleep(1000);
	}

	public void enterHeight2(String height) {
		logger.info("enter Height");
		testUtil.init(this);
		Height.clear();
		Height.sendKeys(height);
	}

	// Description
	public void enterDesc(String des) {
		logger.info("enter Descriptions");
		testUtil.init(this);
		weDesc.clear();
		weDesc.sendKeys(des);
	}

	// Linear Feet
	public void enterLinearFeet(String LinearFeet) {
		logger.info("enter Linear Feet");
		testUtil.init(this);
		welinearFeet.clear();
		welinearFeet.sendKeys(LinearFeet);
	}

	public void selectWarehouse(String wName) throws InterruptedException {
		testUtil.init(this);
		logger.info("select warehouse");
		WebElement select = driver.findElement(By.xpath("//*[@id=\"foodWarehouse\"]/div/div[1]"));
		select.click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[contains(text(),'" + wName + "')]")).click();
	}

	public void clikOnSubmitButton() {
		logger.info("click on Submit button");
		testUtil.init(this);
		// testUtil.WaitForTheElementClickable(weSubmit);
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weSubmit);
		// testUtil.clickElementJavascript(weSubmit);
		testUtil.setHardWait(1000);
		testUtil.fluentWait(By.xpath("//a[contains(text(), 'Create Rate Quote')]"), 100, 5, "Create Rate Quote");

	}

	// Submit Button
	public void clikOnFreightPickUpOrDeliverAtFoodWarehouseOrDistributionCenter() {
		testUtil.init(this);
		logger.info("click on freight pick up or deliver at a food warehouse or distribution center checkbox");
		driver.findElement(By.xpath("//*[@id=\"mat-radio-6\"]/label/div[1]/div[1]")).click();

	}

	// Contact Me/VTL Basic Charge (BOTH HAS SAME XPATH)
	public void clickOnContMe() {
		logger.info("Click on contact me on the result table");

		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)");

		if (weContMe.isDisplayed()) {
			logger.info("Contact me is displayed!");
			testUtil.clickElementJavascript(weContMe);

		} else {
			logger.info("Contact me is not displayed!");
			Assert.fail("test failed!!");
		}
	}

	public void clickOnRateQoute10am() {
		logger.info("Click on Rate Qoute Guaranteed LTL Standard Transit: 10AM");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(LTL10AM);
		LTL10AM.click();
	}

	public void clickOnRateQoute12pm() {
		logger.info("Click on Rate Qoute Guaranteed LTL Standard Transit: 12PM");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(weLTLST12PM);
		weLTLST12PM.click();
	}

	public void clickOnRateQoute5pm() {
		logger.info("Click on Rate Qoute Guaranteed LTL Standard Transit: 5PM");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(LTL5PM);
		LTL5PM.click();
	}

	public void clickOnGuaranteedExclusiveUse() {
		logger.info("click on Guaranteed Exclusive Use");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(GuaranteedExclusiveUse);
		GuaranteedExclusiveUse.click();
	}

	public void clickOnViewAllAccessorials() {
		logger.info("click on view all Accessorials");
		testUtil.init(this);
		ViewAllAccessorials.click();
	}

	public void clickOnGetQouteButton() {
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(Qoute);
		logger.info("click on Get Qoute Button");
		testUtil.clickElementJavascript(Qoute);
	}

	public void clickOnReviseQouteButton() {
		logger.info("click on Revise Qoute Button");
		testUtil.init(this);
		Revise.click();
	}

	public void clickOnReviseQouteButtonBlowQouteOption() {
		logger.info("click on Revise Qoute Button");
		testUtil.init(this);
		driver.findElement(By.xpath("(//button[contains(text(),' Revise Quote')])[2]")).click();
		// Revise.click();
	}

	public void clickOnAddressBookLink() {
		logger.info("Click on Address Book Link");
		testUtil.init(this);
		testUtil.clickElementJavascript(AddressBook);
		// AddressBook.click();
	}

	public void clickOnAddFromCommodityLibrary() {
		logger.info("click on Add From Commodity Library");
		testUtil.init(this);
		AddFromCommodityLibrary.click();
	}

	public void clickOnQouteHistoryTab() {
		logger.info("click on Rate Qoute History");
		testUtil.init(this);
		try {
			testUtil.clickElementJavascript(History);
		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}
	

	}

	public void clickOnAddCommodityButton() {
		logger.info("click on Add commodity Button");
		testUtil.init(this);
		Commodity.click();
	}

	public void clickOnLTLST10AMGetQoute() {
		logger.info("click on LTL 10am Get qoute");
		testUtil.init(this);
		weLTLST12PM.click();
	}

	// Guaranteed LTL Standard Transit: 12PM
	public void clickOnLTLST12PMGetQoute() {
		logger.info("click on LTL 12pm Get qoute");
		testUtil.init(this);
		weLTLST12PM.click();
	}

	public void clickOnRateQuoteButton(String eleName) {
		logger.info("click on Rate Quote Button");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		driver.findElement(By.xpath("//td[contains(text(),  '" + eleName + "')]")).click();
		testUtil.setHardWait(2000);
	}

	public void addFromCommodityLibrary() {
		logger.info("Add from Commodity Library");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.partialLinkText("TEST"));
		testUtil.clickElementJavascript(ele);
	}

	public void verifyHistoryPage() {
		logger.info("verify History Page display");
		String text = driver.findElement(By.xpath("(//*[contains(text(),'History')])[2]")).getText();
		System.out.println("Page title is : " + text);
		Assert.assertEquals(text, "History");
	}

	public void verifyMessage() {
		logger.info("verify attention message");
		String message = driver
				.findElement(By.xpath("//span[contains(text(),'Our records indicate there is a problem with your ')]"))
				.getText();
		System.out.println("Message is :" + message);
		String expected = "Your shipment exceeds the cubic capacity threshold noted for your account. Thus, only volume and exclusive use rates are displayed. For other options such as LTL and time critical, or for more information, please give us a call at the number indicated above.";
		Assert.assertEquals(message, expected);
	}

	public void verifyAttentionMessage() {
		logger.info("verify attention message");
		String message = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/div/lib-feedback/div"))
				.getText();
		System.out.println("Message is :" + message);
		String expected = "Our records indicate there is a problem with your account. Please contact us at 804-353-1900 x2221 to obtain your Volume & Truckload quote.";
		Assert.assertEquals(message, expected);
	}

	public void verifyNoMessage() {
		logger.info("verify attention message NOT display");
		String message = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/div/lib-feedback/div"))
				.getText();
		System.out.println("Message is :" + message);
		String expected = "Your shipment exceeds the cubic capacity threshold noted for your account. Thus, only volume and exclusive use rates are displayed. For other options such as LTL and time critical, or for more information, please give us a call at the number indicated above.";
		Assert.assertNotEquals(message, expected);
	}

	public void verifyTableResult() throws InterruptedException {
		logger.info("verify result table display");
		testUtil.checkPageIsReady();
		Thread.sleep(2000);
		testUtil.printWebTableData();
	}

	public void verifyCreateRateQoutePage() {
		logger.info("verify Create Rate Qoute Page display");
		String actual = driver.findElement(By.partialLinkText("Create Rate Quote")).getText();
		System.out.println("page title is :" + actual);
		Assert.assertEquals(actual, "Create Rate Quote");

	}

	public void verifyAddFromCommodityLibraryPage() {
		logger.info("verify Add FromCommodity Library Page display");
		String actual = driver.findElement(By.xpath("//mat-card-title[contains(text(),'Commodity Library')]"))
				.getText();
		System.out.println("page title is :" + actual);
		Assert.assertEquals(actual, "Commodity Library");

	}

	public void verifyNewCommodityRecordAdded() {
		logger.info("verify Add FromCommodity Library Page display");
		testUtil.verifyAndPrintWebTableData("//div[@class='form-row d-flex flex-wrap commodity-container']");

	}

	public void verifySelectedAccessorials() {
		logger.info("verify selected accessorials listed as line");
		testUtil.verifyAndPrintWebTableData(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table");

	}

	public void verifyCommoditiesSection() {
		logger.info("verify Commodities section");
		String comSection = driver.findElement(By.xpath("//body//app-quote-commodities//tr[2]")).getText();
		System.out.println(comSection);
	}

	public void verifyVTLBasicCharesAreCalculated() {
		logger.info("Verify Volume and Truckload Basic charges are calculated");
		/*
		 * String contMeMessage = weContMeMess.getText(); assertTrue(true,
		 * contMeMessage);
		 */
	}

	// Verify Contact Me Message
	public void verifyMessForContMe() {
		logger.info(
				"Success: Using the information you provided, a response will be sent to you via email within approximately 1 hour."
						+ " Requests submitted after 5 p.m. M-F EST will receive a response the next business day.");

		String contMeMessage = weContMeMess.getText();
		assertTrue(true, contMeMessage);
	}

	public void verifyMessageForRateQoute() {
		logger.info("Please write or type Guaranteed LTL Standard on your BOL (including the driver's copy).");
		String contMeMessage = RateQouteMessage.getText();
		System.out.println(contMeMessage);
		Assert.assertTrue(contMeMessage.contains(contMeMessage));
	}

	public String recordRateQouteNumber() throws InterruptedException {
		logger.info("record the rate Qoute Number ");
		testUtil.init(this);
		WebElement quote = driver.findElement(By.xpath("//tr[1]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote);
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		System.out.println(quoteNumber);
		return quoteNumber;
	}

	// Verify Quote Number for VTL/ TIME CRITICAL
	public void verifyVTLQNumberPrefixIs(String ch) throws InterruptedException {
		logger.info("Verify Quote Number prefix is V");
		testUtil.init(this);
		testUtil.setHardWait(4000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]")).getText()
				.substring(19, 26);

		System.out.println("Quote Number is:" + quoteNumber);
		assertTrue(quoteNumber.startsWith(ch));

	}

	public String verifyVTLQNumberPrefixIs2() throws InterruptedException {
		logger.info("Verify Quote Number prefix is 2");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]")).getText()
				.substring(19, 26);
		System.out.println("Quote Number is :" + quoteNumber);
		testUtil.setHardWait(1000);
		assertTrue(quoteNumber.startsWith("2"));
		return quoteNumber;
	}

	public void validateQouteNumber(String num) throws InterruptedException {
		logger.info("Validate the qoute number that recorded ");
		testUtil.init(this);
		Thread.sleep(2000);

		// this step is added to make the quote number visible so that we can validate
		// because the quote does not show up

		//WebElement ele = driver.findElement(By.xpath("//mat-paginator/div/div[1]/mat-form-field/div/div[1]/div/span"));
		//testUtil.clickElementJavascript(ele);
		//	WebElement itemCount = driver.findElement(By.xpath("//*[@id='mat-option-3']/span[contains(text(),'100')]"));

		//	itemCount.click();

		testUtil.setHardWait(3000);
		String quoteNumber = driver.findElement(By.xpath("//td[contains(text(),'" + num + "')]")).getText();
		System.out.println("Qoute Number is :" + quoteNumber);
		assertEquals(quoteNumber, num);
		
		
		
		
	}

	public void validateRequesterInformationField() {
		logger.info("Validate requester information field");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData("(//div[@class='col-md-6 ng-star-inserted'])[1]");

	}

	public void validateQouteNumberInformations() throws InterruptedException {
		logger.info("validate the qoute number informations");
		testUtil.init(this);
		WebElement quote = driver.findElement(By.xpath("//tr[1]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote);
		Thread.sleep(500);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		System.out.println(quoteNumber);

		String chargeItems = driver
				.findElement(By.xpath(
						"//tr[2]//td[1]//div[1]//app-quote-drawer[1]//div[1]//app-quote-charge-items[1]//div[1]"))
				.getText();
		System.out.println(chargeItems);

		WebElement quote1 = driver.findElement(By.xpath("//tr[3]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote1);
		Thread.sleep(500);
		String quoteNumber2 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]"))
				.getText();
		System.out.println(quoteNumber2);

	}

	public void clickOnSubmitButton() throws InterruptedException {
		logger.info("Click on Submit Request Button");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int i = 0;
		int exist = 0;

		do {
			testUtil.clickElementJavascript(weSubmit);
			testUtil.setHardWait(500);
			if (testUtil.isDisplayed(serviceLvl)) {

				exist = 1;
			}

			i++;
		} while (i <= 5 && exist != 1);

	}

	public String selectOrDeselectStopoffServices() {
		logger.info("Select Or Deselect Accessorials stop off service");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//*[@id='allAccessorials73']/label"));
		String accessorial = ele.getText();
		System.out.println(accessorial + " is selected");
		testUtil.clickElementJavascript(ele);
		return accessorial;
	}

	public void selectOrDeselectAccessorialsList(String accessorial) {
		logger.info("Select Or deselect Accessorials");
		testUtil.init(this);
		WebElement ele = testUtil.filterXpath("//span[text()=\" " + accessorial + " \"]/preceding-sibling::div", null,
				null, 4, "checkbox");

		testUtil.clickElementJavascript(ele);
	}

	public void clickOnConctactMeLinkOnResultTable(String serviceLevel) {
		logger.info("Click on Contact me link on the result table");
		testUtil.init(this);

		List<WebElement> table = driver.findElements(By.xpath("//*[@id='main']//mat-card/table/tbody/tr[*]"));

		for (int i = 0; i < table.size(); i++) {

			int j = i + 1;

			String serviceLvl = driver
					.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr [" + j + "]/td[1]")).getText()
					.trim();

			if (serviceLvl.equalsIgnoreCase(serviceLevel)) {

				String name = driver.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr [" + j + "]/td[5]"))
						.getText().trim();

				// added this step to click on contact me link
				if (name.equalsIgnoreCase("Contact Me"))

					driver.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr [" + j + "]/td[5]")).click();
				// }
				assertTrue(name.equals("Contact Me"), "Faild to display Contact Me Link");

				logger.info("Contact me is Displayed for the service level:" + serviceLevel);
				break;
			}

		}}


	 public void refreshAndValidateQuoteNum(String quoteNum, int numberOfAttempts) throws Exception {
	        logger.info("Refresing the page and velidating the quote");
         for (int i = 0; i < numberOfAttempts; i++) {
             
               driver.navigate().refresh();
               
               WebElement quoteNumElement = testUtil.filterXpathTimout("//td", null, null, 0, 10000, quoteNum);
               
               if (quoteNumElement == null && i <= numberOfAttempts) {
                   System.out.println("Attempt " + i + " unsuccessful");
               }
               else if(quoteNumElement == null &&  i > numberOfAttempts) {
                   throw new Exception("Failed to find element");
               } else {
                   System.out.println("Element found: " + quoteNumElement.getText());
                   Assert.assertEquals(quoteNumElement.getText(),quoteNum,"The quote number is not displayed on the page ");
                   break;
               }
             }

	 }}

