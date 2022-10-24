package eNetPages;

import static org.testng.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.TestBase;


public class ENetTimeCriticalRateQuotePage extends TestBase{
	
  private Logger logger=Logger.getLogger(ENetTimeCriticalRateQuotePage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@id='accountNumber']")
	private WebElement accountNumberField;
	
	@FindBy(how = How.XPATH, using = "//*[@id='full_name']")
	private WebElement weFullName;
	
	@FindBy(how = How.XPATH, using = "//*[@id='contact_email']")
	private WebElement weEmail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='user_telephone']")
	private WebElement wePhone;
	
	@FindBy(how = How.XPATH, using = "//*[@id='my_role']")
	private WebElement weMyRole;
	
	@FindBy(how = How.XPATH, using = "//*[@id='terms']")
	private WebElement weTerms;
	
	@FindBy(how = How.ID, using = "zipOrigin")
	private WebElement zipOrigin;
	
	@FindBy(how = How.ID, using = "zipDestination")
	private WebElement zipDest;
	
	@FindBy(how = How.ID, using = "commClass0")
	private WebElement commClass0;
	
	@FindBy(how = How.ID, using = "weight0")
	private WebElement weight0;
	
	@FindBy(how = How.ID, using = "length0")
	private WebElement length0;
	
	@FindBy(how = How.ID, using = "height0")
	private WebElement height0;
	
	@FindBy(how = How.XPATH, using = "//*[@id='pickupDate'] [@name='scheduling.pickupDate']")
	private WebElement wePickupDate;
	
	@FindBy(how = How.ID, using = "pieces0")
	private WebElement pieces0;
	
	@FindBy(how = How.ID, using = "pieceType0")
	private WebElement pieceType0;
	
	@FindBy(how = How.ID, using = "width0")
	private WebElement width0;
	
	@FindBy(how = How.ID, using = "ratequoteSubmitButton")
	private WebElement submit;
		
	@FindBy(how = How.XPATH, using = "//*[@id='app_intro_module']/h1[contains(text(),'Time Critical Rate Quote')]")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[@id='addComButton']")
	private WebElement addCommodityButton;
	
	@FindBy(xpath = "//*[@id='mainpage']")
	private WebElement mainPageFrame;
	
	@FindBy(xpath = "//*[@id='startTimeHH']")
	private WebElement availableHourSelect;
	
	@FindBy(xpath = "//*[@id='startTimeMM']")
	private WebElement availableMinSelect;
	
	@FindBy(xpath = "//*[@id=\"startTimeAMPM\"]")
	private WebElement availableTmAMPMSelect;
	
	@FindBy(xpath = "//*[@id='endCloseTimeHH']")
	private WebElement closeHourSelect;
	
	@FindBy(xpath = "//*[@id='endCloseTimeMM']")
	private WebElement closeMinSelect;
	
	@FindBy(xpath = "//*[@id='endCloseTimeAMPM']")
	private WebElement closeTmAMPMSelect;
	
	@FindBy(xpath = "//*[@id='commodities_table']")
	private WebElement commodityTable;
	
	@FindBy(xpath = "//*[@id=\"freightInfo.pickupAtWareHouse2\"]")
	private WebElement pickupAtWareHouse2;
	
	@FindBy(xpath = "//*[@id='stackable']")
	private WebElement freightStackable;
	
	@FindBy(xpath = "//*[@id=\"comments\"]")
	private WebElement comments;
	
	@FindBy(xpath = "//*[@id=\"wareHouse\"]")
	private WebElement wareHouse;
	
	@FindBy(xpath = "//*[@id=\"APT\"]")
	private WebElement appointmentRequest;
	
	@FindBy(xpath = "//*[@id=\"tc_table\"]/tbody")
	private WebElement exclusiveOptionTable;
	
	@FindBy(xpath = "//*[@id=\"fee_summary_container_vtl\"]/table/tbody")
	private WebElement feeSummaryTable;
	
	@FindBy(xpath = "//*[@id=\"tos\"]")
	private WebElement termsOfService;
	
	@FindBy(xpath = "//*[@id=\"book_shipment_button_enet\"]")
	private WebElement bookShipmentButtonEnet;
	
	@FindBy(xpath = "//*[@id=\"logout\"]")
	private WebElement logout;
	
	@FindBy(xpath = "/html/body/form/table/tbody/tr[3]/td/input")
	private WebElement logoutConfirmation;
	
	@FindBy(xpath = "//*[@id=\"INS\"]")
	private WebElement insideDelivery;
	
	@FindBy(xpath = "//*[@id=\"NCM\"]")
	private WebElement notifyRequest;
	
	@FindBy(xpath = "//*[@id='tc_table']/tbody")
	private WebElement timeCriticalTable;
	
	@FindBy(xpath = "//*[@id=\"quote_actions\"]/input[2]")
	private WebElement faxQuoteButton;
	
	@FindBy(xpath = "//*[@id=\"faxNumber\"]")
	private WebElement faxNumber;
	
	@FindBy(xpath = "//*[@id='quote_number']")
	private WebElement quoteNum;
	
	@FindBy(xpath = "//*[@id='LONG16']")
	private WebElement overLength12to19;
	
	@FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div[2]/div[2]/div/div[1]")
	private WebElement totalLinearFootage;
	
	@FindBy(xpath= "//*[@id='tos']")
	private WebElement agreeCheck;
	
	@FindBy(xpath="//*[@id='print_quote_button']")
	private WebElement printQuoteButton;
	
	@FindBy(xpath = "//*[@id='print']")
	private WebElement printPDFButton;
	
	@FindBy(xpath = "/html/body/pdf-viewer")
	private WebElement pdfViewer;
	
	@FindBy(xpath = "//*[@id='vtl_qds_container']/div[1]/fieldset[1]/div/div/div[2]/div[2]")
	private WebElement serviceLevel;
	
	@FindBy(xpath = "//*[@id='vtl_qds_container']/div[1]/fieldset[1]/div/div/div[1]/div[2]/b")
	private WebElement quote;
	
	@FindBy(xpath = "//*[@id='charge_items']/table/tfoot/tr/td[2]")
	private WebElement quotePrice;


	@FindBy(xpath = "//*[@id='charge_items']/table/tbody/tr[6]/td[1]")
	private WebElement overLengthCharge;

	@FindBy(how = How.ID, using = "desc0")
	private WebElement desc0;
	
	@FindBy(xpath = "//*[@id='app_wrapper']/div[2]/div[1]/h2")
	private WebElement timeCriticalSummaryLabel;
	
	@FindBy(xpath = "//*[@id='routing']/div")
	private WebElement routingElements;
	
	@FindBy(xpath = "//*[@id='Applications']")
	private WebElement applicationsTab;
	
	@FindBy(xpath = "/html/body/div[5]/div[15]/a")
	private WebElement vtlTableMaintenance;
		
	@FindBy(xpath = "//*[@id='rate_disclaimers']")
	private WebElement vltTableeNetNotes;
	
	@FindBy(how = How.ID, using = "pickupDate")
	private WebElement pickupDate;
	
	@FindBy(xpath = "//*[@id=\"scheduling_container\"]/div/div/div/div[2]/img")
	private WebElement pickupDateCalender;
	
	@FindBy(xpath = "//*[@id=\"startTimeHH\"]")
	private WebElement startHour;
	
	@FindBy(xpath = "//*[@id=\"startTimeMM\"]")
	private WebElement startMin;
	
	@FindBy(xpath = "//*[@id=\"startTimeAMPM\"]")
	private WebElement startAMPM;
	
	@FindBy(xpath = "//*[@id=\"endCloseTimeHH\"]")
	private WebElement endHour;
	
	@FindBy(xpath = "//*[@id=\"endCloseTimeMM\"]")
	private WebElement endMin;
	
	@FindBy(xpath = "//*[@id=\"endCloseTimeAMPM\"]")
	private WebElement endAMPM;
	
	@FindBy(xpath = "//*[@id=\"charNum\"]")
	private WebElement commentCharLimit;
	
	@FindBy(xpath = "//*[@id=\"accessorials_container\"]/div[1]/div[2]/label[8]")
	private WebElement protectFromFreezing;
	
	@FindBy(xpath = "//*[@id='cityOrigin']")
	private WebElement originCity;
	
	@FindBy(xpath = "//*[@id='cityDestination']")
	private WebElement destinationCity;
	
	@FindBy(xpath = "//*[@id='update_quote']")
	private WebElement updateQuoteBtn;
	
	@FindBy(xpath = "//*[@id='accessorials_container']/div[1]/div[1]/label[6]")
	private WebElement constructionSiteDelivery;
	
	@FindBy(xpath = "//*[@id='vtl_qds_container']/div[2]/fieldset[3]/div/p")
	private WebElement quoteComments;
	
	@FindBy(tagName = "iframe")
	private List<WebElement> noOfIFrame;
	
	@FindBy(xpath= "//*[@id='charge_items']/table/tbody/tr[3]/td[2]")
	private WebElement chargeItem;
	
	/****************************METHODS******************************/
	
	private String fullNamestr;
	
	
	/**
	 * @return the fullNamestr
	 */
	public String getFullNamestr() {
		return fullNamestr;
	}

	/**
	 * @param fullNamestr the fullNamestr to set
	 */
	public void setFullNamestr(String fullNamestr) {
		this.fullNamestr = fullNamestr;
	}
	
	private String email;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String todayDate;

	/**
	 * @return the todayDate
	 */
	public String getTodayDate() {
		return todayDate;
	}

	/**
	 * @param todayDate the todayDate to set
	 */
	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public void enterFullName() {
		testUtil.init(this);
		logger.info("Enter Full Name");
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		logger.info("Full name : "+fullName);
		weFullName.click();
		weFullName.sendKeys(fullName);
		setFullNamestr(fullName);
	}
	
	public void enterFullName(String name) {
		testUtil.init(this);
		logger.info("Enter Full Name");
		logger.info("Full name : "+name);
		if(!noOfIFrame.isEmpty()) {
			testUtil.switchToFrame(0);
		}
		weFullName.click();
		weFullName.sendKeys(name);
		setFullNamestr(name);
	}
	
	public void enterEmail(String eml) {
		testUtil.init(this);
		logger.info("Enter email address");
		weEmail.sendKeys(eml);
		setEmail(eml);
	}
	
	public void enterAccountNumber(String aNum) {
		testUtil.init(this);
		logger.info("Enter Account Number");
		accountNumberField.sendKeys(aNum);
	}
	
	public void enterEmail() {
		testUtil.init(this);
		logger.info("enter email address");
		Faker faker = new Faker();
		String emailId = faker.internet().emailAddress();
		weEmail.sendKeys(emailId);
		setEmail(emailId);
		
	}
	
	public void enterPhoneNumber() {
		testUtil.init(this);
		logger.info("enter Phone Number");
		wePhone.sendKeys("2021234567");
	}
	
	public void enterPhoneNumber(String phoneNo) {
		testUtil.init(this);
		logger.info("enter Phone Number : "+phoneNo);
		wePhone.click();
		wePhone.sendKeys(phoneNo);
	}
	
	public void setTodayDate() {
		logger.info("Enter date");
		testUtil.init(this);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		testUtil.setHardWait(1000);
		String strTodayDate = dtf.format(localDate);
		logger.info("todayDate : "+strTodayDate);
		wePickupDate.sendKeys(strTodayDate);
		setTodayDate(strTodayDate);
	}
	
	public void selectRole(String role) {
		testUtil.init(this);
		logger.info("select Role");
		testUtil.selectUsingVisibleText(weMyRole, role);
	}
	
	public void selectTerm(String term) {
		testUtil.init(this);
		logger.info("select Term");
		testUtil.selectUsingVisibleText(weTerms, term);
	}
	
	public void selectClass(String mClass) {
		testUtil.init(this);
		logger.info("select Class");
		testUtil.selectUsingVisibleText(commClass0, mClass);
	}
	
	public void enterOriginZip(String oZip) {
		testUtil.init(this);
		logger.info("enter Origin Zip ");
		zipOrigin.sendKeys(oZip);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//a[contains(text(),'"+oZip+"')]")).click();
	}
	
	public void enterDestinationZip(String dZip) {
		testUtil.init(this);
		logger.info("enter Destination Zip ");
		zipDest.sendKeys(dZip);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//a[contains(text(),'"+dZip+"')]")).click();
	}
	
	public void enterWeight(String weight) {
		testUtil.init(this);
		logger.info("enter Weight");
		weight0.sendKeys(weight);
	}
	
	public void enterHeight(String height) {
		testUtil.init(this);
		logger.info("enter Height");
		height0.sendKeys(height);
	}
	
	public void enterPieces(String pieces) {
		testUtil.init(this);
		logger.info("enter Pieces");
		pieces0.sendKeys(pieces);
	}
	
	public void enterPiecesType(String type) {
		testUtil.init(this);
		logger.info("enter Pieces Type");
		testUtil.assetWait(null, pieceType0, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(pieceType0);
		testUtil.selectUsingVisibleText(pieceType0, type);
	}
	
	public void enterLength(String length) {
		testUtil.init(this);
		logger.info("enter Length");
		length0.sendKeys(length);
	}
	
	public void enterWidth(String width) {
		testUtil.init(this);
		logger.info("enter Width");
		width0.sendKeys(width);
	}
	
	public void clickOnSubmitButton() {
		testUtil.init(this);
		logger.info("click On Submit Button");
		testUtil.assetWaitClickable(null, submit, 10, 200, TimeUnit.MICROSECONDS);
		submit.submit();
		   testUtil.waitForPageToLoad();//-->newly added 

		   
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


	public void verifyChargesByServiceLevel(String serviceLvl) {
		logger.info("Verifying charges rate by service level");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+serviceLvl+"')]//parent::tr/td[4]"));
		testUtil.setExplicitWait(ele, 60);
		String rate = ele.getText().trim();
		Assert.assertFalse(rate.equalsIgnoreCase("Contact Me"));
	}
	
	public void verifyChargesByServiceLevelDisplays(String serviceLevel) {
		testUtil.init(this);
		logger.info("Verify the charges for service level is displayed");
		WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+serviceLevel+"')]//parent::tr/td[4]"));
		String charges=ele.getText().trim();
		logger.info("The chareges is:" + charges);
	
		Assert.assertTrue(charges.contains("$"));
		testUtil.setHardWait(1000);
		logger.info("Click on the charges");
		driver.findElement(By.xpath("//*[@id='tc_table']/tbody/tr[3]/td[4]/a")).click();
		
	}

	
	public void selectQouteByServiceLevel(String tableName, String serviceLevel) {   
		logger.info("Click on Service Level "+ serviceLevel);
		testUtil.init(this);
		List <WebElement> rowCount = new ArrayList<>();
		
		testUtil.assetWait("//*[@id='tc_table']/tbody/tr", null, 10, 200, TimeUnit.MILLISECONDS);
		if(tableName.equals("TC")) {
			rowCount	 = driver.findElements(By.xpath("//*[@id='tc_table']/tbody/tr"));
		}
		else if(tableName.equals("VTL")){
			rowCount = driver.findElements(By.xpath("//*[@id='vtl_table']/tbody/tr"));
		}
		 
		for(int i = 1; i<= rowCount.size(); i++) {
		 String actual = driver.findElement(By.xpath("//*[@id='tc_table']/tbody/tr["+i+"]/td[3]")).getText();
			if (actual.equals(serviceLevel)) {
				driver.findElement(By.xpath("//*[@id='tc_table']/tbody/tr[" + i + "]/td[4]/a")).click();
				logger.info("Quote selected successfully");
				break;
		 	}}
		}
	
	public void verifyAccessorialIsDisplayedWithCharge(String accName) {
		logger.info("Verify Accessorial is displayed in Quote Summary");
		testUtil.init(this);
		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@id='charge_items']/table/tbody/tr[*]"));
		boolean val = false;

		for (int i = 1; i <= rowCount.size(); i++) {
			String actual = driver.findElement(By.xpath("//*[@id='charge_items']/table/tbody/tr[" + i + "]/td[1]"))
					.getText();
			logger.info(actual + "!!!!!!");
			if (actual.equals(accName)) {
				val = true;
				String actualCharge = driver
						.findElement(By.xpath("//*[@id='charge_items']/table/tbody/tr[" + i + "]/td[2]")).getText();
				assertNotNull(actualCharge);
				break;
			}
		}
		Assert.assertTrue(val);
	}
	
	public void verifyPage() {
		testUtil.init(this);
		
		driver.switchTo().frame("mainpage");
		logger.info("Click on iframe before verifying page title");
		String pageName = pageTitle.getText();
		logger.info("Page Title : "+pageName);
	//	Assert.assertEquals(pageName, "Time Critical Rate Quote","Time Critical Rate Quote page title does not match.");
		Assert.assertEquals(pageName, "Time Critical Rate Quote");
	}
	
	
	
	public void verifyPageWithoutIFrame() {
		testUtil.init(this);
		
	//	driver.switchTo().frame("mainpage");
	//	logger.info("Click on iframe before verifying page title");
		String pageName = pageTitle.getText();
		logger.info("Page Title : "+pageName);
	//	Assert.assertEquals(pageName, "Time Critical Rate Quote","Time Critical Rate Quote page title does not match.");
		Assert.assertEquals(pageName, "Time Critical Rate Quote");
	}
	public void clickOnAddCommodityButton() {
		testUtil.init(this);
		logger.info("Click On AddCommodity Button.");
		addCommodityButton.click();
	}
	
	public void scrollToAddCommodity() {
		testUtil.init(this);
		logger.info("Scroll to add commodity button.");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCommodityButton);
	}
	
	public void scrollToPageTitle() {
		testUtil.init(this);
		logger.info("Scroll to FullName.");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pageTitle);
	}
	
	public void scrollToCommodityNo() {
		testUtil.init(this);
		WebElement commodityNum = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/form/span[2]/div/div[3]/div[1]/div/div/div[3]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", commodityNum);
	}
	
	public void switchToMainFrame() {
		testUtil.init(this);
		testUtil.setHardWait(2000);
		logger.info("Switch to Main page Frame.");
		driver.switchTo().frame(mainPageFrame);
	}
	
	public void enterLastDateofMonth() {
		testUtil.init(this);
		logger.info("Enter the last date of the month.");
		Date today = new Date();  

		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(today);  

		calendar.add(Calendar.MONTH, 1);  
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		calendar.add(Calendar.DATE, -1);  

		Date lastDayOfMonth = calendar.getTime();  

		DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");  
		String lastDate = sdf.format(lastDayOfMonth);
		logger.info("Last Day of Month: " + lastDate);
		wePickupDate.click();
		wePickupDate.sendKeys(lastDate);
		
	}
	
	public void setAvailableByTime(String hrs, String mins, String tm) {
		testUtil.init(this);
		Select startHourSelect = new Select(availableHourSelect);
		startHourSelect.selectByValue(hrs);
		Select startMinSelect = new Select(availableMinSelect);
		startMinSelect.selectByValue(mins);
		Select startTime = new Select(availableTmAMPMSelect);
		startTime.selectByValue(tm);
	}
	
	public void setCloseAtTime(String hrs, String mins, String tm) {
		testUtil.init(this);
		Select closeHour = new Select(closeHourSelect);
		closeHour.selectByValue(hrs);
		Select closeMin = new Select(closeMinSelect);
		closeMin.selectByValue(mins);
		Select closeTime = new Select(closeTmAMPMSelect);
		closeTime.selectByValue(tm);
	}
	
	public Map<String,ArrayList<String>> fillCommoditiesDetails() {
		testUtil.init(this);
		ArrayList<String> commodity = new ArrayList<>();
		HashMap<String,ArrayList<String>> commodityMap = new HashMap<>();
		List<WebElement> commodities = commodityTable.findElements(By.tagName("div"));
		List<WebElement> commoditiesAct = new ArrayList<>();
		for(int i=0;i<commodities.size();i++) {
			String classAtt = commodities.get(i).getAttribute("class");
			if(classAtt.equals("tableRow removable")) {
				commoditiesAct.add(commodities.get(i));
			}
		}
		logger.info("Commodity size : "+commoditiesAct.size());
		for(int i=0;i<commoditiesAct.size();i++) {
			logger.info("Commodity List count : "+i);
			scrollToCommodityNo();
			//Select Commodity Class
			WebElement commodityElement = commoditiesAct.get(i);
			logger.info("Commodity Element : "+commodityElement.getText());
			WebElement commodityClass = commodityElement.findElement(By.xpath("div[1]"));
			WebElement commodityClassSelect = commodityClass.findElement(By.xpath("select"));
			Select classSelect = new Select(commodityClassSelect);
			classSelect.selectByIndex(i+1);
			String classVal = classSelect.getFirstSelectedOption().getText();
			logger.info("Class Value : "+classVal);
			commodity.add(classVal);
			
			//Enter Pieces
			WebElement piece = commodityElement.findElement(By.xpath("div[2]/input"));
			String pieceVal = String.valueOf(i+1);
			piece.sendKeys(pieceVal);
			logger.info("Piece Value : "+pieceVal);
			commodity.add(pieceVal);
			
			testUtil.setHardWait(200);
			
			//Select piece type
			WebElement pieceType = commodityElement.findElement(By.xpath("div[3]/select"));
			Select pieceTypeSelect = new Select(pieceType);
			pieceTypeSelect.selectByIndex(i+1);
			String pieceTypeVal = pieceTypeSelect.getFirstSelectedOption().getText();
			logger.info("Piece Type Value : "+pieceTypeVal);
			commodity.add(pieceTypeVal);

			//Enter Total Weight
			WebElement totalWeight = commodityElement.findElement(By.xpath("div[4]/input"));
			String totalWeightVal = String.valueOf(i+1);
			totalWeight.sendKeys(totalWeightVal);
			logger.info("Total Weight Value : "+totalWeightVal);
			commodity.add(totalWeightVal);
			
			//Enter Length
			WebElement length = commodityElement.findElement(By.xpath("div[5]/input"));
			String lengthVal = String.valueOf(i+1);
			length.sendKeys(lengthVal);
			logger.info("Length Value : "+lengthVal);
			commodity.add(lengthVal);
			
			//Enter Width
			WebElement width = commodityElement.findElement(By.xpath("div[6]/input"));
			String widthVal = String.valueOf(i+1);
			width.sendKeys(widthVal);
			logger.info("Width Value : "+widthVal);
			commodity.add(widthVal);
			
			//Enter Height
			WebElement height = commodityElement.findElement(By.xpath("div[7]/input"));
			String heightVal = String.valueOf(i+1);
			height.sendKeys(heightVal);
			logger.info("Height Value : "+heightVal);
			commodity.add(heightVal);
			
			//Enter Description
			WebElement description = commodityElement.findElement(By.xpath("div[8]/input[1]"));
			String descriptionVal = "Description_"+ (i+1);
			description.sendKeys(descriptionVal);
			logger.info("Description Value : "+descriptionVal);
			commodityMap.put(descriptionVal, commodity);
			
			testUtil.setHardWait(200);
		}
		return commodityMap;
	}
	
	public void selectYesforFreightPickUp() {
		testUtil.init(this);
		logger.info("Does freight pick up or deliver at a food warehouse or distribution center? : Yes");
		pickupAtWareHouse2.click();
	}
	
	public void isFreightStackable() {
		testUtil.init(this);
		logger.info("Is your freight stackable? : Checked.");
		freightStackable.click();
	}
	
	public void selectWareHouse(String wareHVal) {
		testUtil.init(this);
		logger.info("Select value : "+wareHVal);
		Select wareH = new Select(wareHouse);
		wareH.selectByValue(wareHVal);
	}
	
	public void enterComments(String comment) {
		testUtil.init(this);
		logger.info("Enter the Freight related comments.");
		comments.clear();
		comments.sendKeys(comment);
	}
	
	public void checkAppointmentRequest() {
		testUtil.init(this);
		logger.info("Check Appointment Request option.");
		appointmentRequest.click();
	}
	
	public void checkInsideDelivery() {
		testUtil.init(this);
		logger.info("Check Inside Delivery option.");
		insideDelivery.click();
	}
	
	public void checkNotifyRequest() {
		testUtil.init(this);
		logger.info("Check Notify Request option.");
		notifyRequest.click();
	}
	
	public void checkOverLength12to19() {
		testUtil.init(this);
		logger.info("Check assessorial for Overlength 12.00' to 15.99'");
		testUtil.assetWait(null, overLength12to19, 10, 200, TimeUnit.MILLISECONDS);
		overLength12to19.click();
	}
	
	public void clickExclusiveUseChargesOption() {
		testUtil.init(this);
		logger.info("Click on Exclusive Use option with charges.");
		List<WebElement> options = exclusiveOptionTable.findElements(By.tagName("tr"));
		for(int i=0;i<options.size();i++) {
			WebElement exclusiveOption = options.get(i).findElement(By.xpath("td[3]"));
			String optionVal = exclusiveOption.getText();
			if(optionVal.equals("Guaranteed Exclusive Use")) {
				WebElement exclusiveActionOption = options.get(i).findElement(By.xpath("td[4]/a"));
				exclusiveActionOption.click();
				break;
			}
		}
		
	}
	
	public void verifyFeeSummary(Map<String,ArrayList<String>> commodityMap) {
		testUtil.init(this);
		
		ArrayList<String> commodity = new ArrayList<>();
		HashMap<String,ArrayList<String>> commoditiesMap = new HashMap<>();
		List<WebElement> feeSummaryList = feeSummaryTable.findElements(By.tagName("tr"));
		for(int i=0;i<feeSummaryList.size();i++) {
			String description = feeSummaryList.get(i).findElement(By.xpath("td[1]")).getText();
			String classVal = feeSummaryList.get(i).findElement(By.xpath("td[2]")).getText();
			String pieceVal = feeSummaryList.get(i).findElement(By.xpath("td[3]")).getText();
			String pieceTypeVal = feeSummaryList.get(i).findElement(By.xpath("td[4]")).getText();
			String weightVal = feeSummaryList.get(i).findElement(By.xpath("td[5]")).getText();
			String lengthVal = feeSummaryList.get(i).findElement(By.xpath("td[6]")).getText();
			String widthVal = feeSummaryList.get(i).findElement(By.xpath("td[7]")).getText();
			String heightVal = feeSummaryList.get(i).findElement(By.xpath("td[8]")).getText();

			commodity.add(classVal);
			commodity.add(pieceVal);
			commodity.add(pieceTypeVal);
			commodity.add(weightVal);
			commodity.add(lengthVal);
			commodity.add(widthVal);
			commodity.add(heightVal);
			commoditiesMap.put(description, commodity);
		}
		
		if(commoditiesMap.equals(commodityMap)) {
			logger.info("Commodities lists are same.");
		}
		else {
			Assert.fail("Number of commodities listed in Fee Summary are not same.");
		}
	}
	
	public void selectTermsofServices() {
		testUtil.init(this);
		logger.info("Select the 'Terms of Service' checkbox under 'Quote Actions.'");
		termsOfService.click();
	}
	
	public void clickBookShipmentButtonEnet() {
		testUtil.init(this);
		logger.info("Click book_shipment_button_enet");
		bookShipmentButtonEnet.click();
	}
	
	public void clickLogout() {
		testUtil.init(this);
		logger.info("Click on Logout button.");
		driver.switchTo().defaultContent();
		logout.click();
	}
	
	public void clickLogoutConfirmation() {
		testUtil.init(this);
		logger.info("Click on Logout confirmation button.");
		driver.switchTo().frame("mainpage");
		logoutConfirmation.click();
	}
	
	public void clickOnGuranteedBy12PMprice() {
		testUtil.init(this);
		logger.info("Click on GuranteedBy12PMprice");
		List<WebElement> options = timeCriticalTable.findElements(By.tagName("tr"));
		for(int i=0;i<options.size();i++) {
			WebElement guranteedOption = options.get(i).findElement(By.xpath("td[3]"));
			String optionVal = guranteedOption.getText();
			if(optionVal.equals("Guaranteed LTL Standard Transit: 12PM")) {
				WebElement guranteed12PMOption = options.get(i).findElement(By.xpath("td[4]/a"));
				guranteed12PMOption.click();
				break;
			}
		}
	}

	
	public void clickOnGuranteedBy5PMprice() {
		testUtil.init(this);
		logger.info("Click on GuranteedBy5PMprice");
		List<WebElement> options = timeCriticalTable.findElements(By.tagName("tr"));
		for(int i=0;i<options.size();i++) {
			WebElement guranteedOption = options.get(i).findElement(By.xpath("td[3]"));
			String optionVal = guranteedOption.getText();
			if(optionVal.equals("Guaranteed LTL Standard Transit: 5PM")) {
				WebElement guranteed5PMOption = options.get(i).findElement(By.xpath("td[4]/a"));
				guranteed5PMOption.click();
				break;
			}
		}
	}
	
	public void checkFaxQuoteButton() {
		testUtil.init(this);
		logger.info("Check Fax Quote button is enabled.");
		if(faxQuoteButton.isDisplayed()){
			logger.info("Fax Quote Button is displayed.");
		}else {
			Assert.fail("Fax Quote Button is not displayed.");
		}
	}
	
	public void enterFaxNumber(String faxNo) {
		testUtil.init(this);
		logger.info("Enter Fax number : "+faxNo);
		faxNumber.sendKeys(faxNo);
	}
	
	public void clickFaxButton() {
		testUtil.init(this);
		logger.info("Click on Fax Button.");
		faxQuoteButton.click();
	}
	
	public String fetchQuoteNumber() {
		testUtil.init(this);
		logger.info("Fetch Quote number.");
		String quotNo = quoteNum.getText();
		String separator =":";
	      int sepPos = quotNo.indexOf(separator);
	      if (sepPos == -1) {
	         logger.info("");
	      }
	      String quoteNo = quotNo.substring(sepPos + separator.length()).trim();
		logger.info("Quote Number : "+quoteNo);
		return quoteNo;
	}
	
	public void verifyTotalLinearFootageExceeds20feet() {
		testUtil.init(this);
		logger.info("Verify disclaimer displays stating that the total linear footage exceeds 20 feet displays at the top.");
		String linearFootage = totalLinearFootage.getText();
		logger.info("Linear Footage : "+linearFootage);
		String expectedLinearFootage = "Attention: Your total linear footage exceeds the 20 foot threshold for an LTL rate. "
				+ "If your shipment exceeds maximum length, give us a call at 804-353-1900, Ext. 2269.";
		Assert.assertEquals(linearFootage, expectedLinearFootage, "TotalLinearFootageExceeds20feet message doesnot match.");
	}
	
	public void verifyLTLTimeCriticalRatesNotGenerated() {
		testUtil.init(this);
		logger.info("");
		
		List<WebElement> timeCriticalList = timeCriticalTable.findElements(By.tagName("tr"));
		for (int i = 0; i < timeCriticalList.size(); i++) {
			String serviceLevelStr = timeCriticalList.get(i).findElement(By.xpath("td[3]")).getText();
			
			if(serviceLevelStr.contains("LTL")) {
				//String value = timeCriticalList.get(i).findElement(By.xpath("td[4]/a")).getText().trim();-->commented and added below line
				String value = timeCriticalList.get(i).findElement(By.xpath("//*[@id='tc_table']/tbody/tr[3]/td[4]/a")).getText().trim(); 
				if(value.equals("Contact Me")) {
					logger.info("Rate Value found : "+value);
				}else {
					Assert.fail("Found rate for LTL Time Critical.");
				}
			}
		}
		
		logger.info("Rates for LTL Time Critical are not generated.");
	
	}
	
	
	public void clickOnAgreeCheck() {
		testUtil.init(this);
		logger.info("Check the checkbox  I have read and agree to the disclaimers and Terms of Service below.");
		agreeCheck.click();
	}
	
	public void clickPrintQuote() {
		testUtil.init(this);
		logger.info("Click on Print Quote button.");
		testUtil.clickElementJavascript(printQuoteButton);
	//	printQuoteButton.click();
	}
	
	public void clickPrintDocument() {
		testUtil.init(this);
		testUtil.setHardWait(1000);
		logger.info("Click on print option.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.print();");
	}

	public String recordQuoteNumber() {
		logger.info("Record quote number");
		testUtil.init(this);
		WebElement quoteNumberEle= driver.findElement(By.cssSelector("#quote_number"));  
		
		String quoteNumber =quoteNumberEle.getText().substring(14,21);
		logger.info("The quote number is:" +quoteNumber);
		return quoteNumber;
	}
	
	public void checkServiceLevel(String serLevel) {
		testUtil.init(this);
		logger.info("Check Service Level.");
		String level = serviceLevel.getText().trim();
		Assert.assertEquals(level, serLevel.trim(),"Service Levels are not same.");
	}

	public void clickOnLTLStandardTransitRate() {
		testUtil.init(this);
		logger.info("Click on the hyperlink for the LTL Standard Transit rate");
		List<WebElement> options = exclusiveOptionTable.findElements(By.tagName("tr"));
		for(int i=0;i<options.size();i++) {
			WebElement exclusiveOption = options.get(i).findElement(By.xpath("td[3]"));
			String optionVal = exclusiveOption.getText();
			if(optionVal.equals("LTL Standard Transit")) {
				WebElement exclusiveActionOption = options.get(i).findElement(By.xpath("td[4]/a"));
				exclusiveActionOption.click();
				break;
			}
		}
		
	}
		public void verifyOverLengthCharge() {
			testUtil.init(this);
			logger.info("Verify that the accessorial change for *OVERLENGTH CHARGE-20.00' - 27.9' * is listed under Charges.");
			Assert.assertTrue(overLengthCharge.isDisplayed());
			logger.info("Accessorial change for *OVERLENGTH CHARGE-20.00' - 27.9' * is listed under Charges.");
		}

		public void verifyOverLengthChargeFor8(){
			testUtil.init(this);
			logger.info("Verify *OVERLENGTH CHARGE for 8.00' - 11.99' *.");
			Assert.assertTrue(chargeItem.isDisplayed());
		
			
		}
		public void verifyOverLengthCharge40() {
			testUtil.init(this);
			logger.info("Verify that the accessorial change for *OVERLENGTH CHARGE - 40' OR GREATER* is listed under Charges.");
			Assert.assertTrue(overLengthCharge.isDisplayed());
			Assert.assertEquals(overLengthCharge.getText().trim(), "OVERLENGTH CHARGE - 40' OR GREATER".trim(),"OVERLENGTH CHARGE - 40' OR GREATER doesnot match.");
			logger.info("Accessorial change for *OVERLENGTH CHARGE - 40' OR GREATER* is listed under Charges.");
		}

		
		public String getQuote() {
			testUtil.init(this);
			logger.info("Get Quote.");
			String quoteNo = quote.getText().trim();
			logger.info("Quote Number : "+quoteNo);
			return quoteNo;
		}
		
		public String getQuotePrice() {
			testUtil.init(this);
			logger.info("Get Quote Price.");
			String price = quotePrice.getText().trim();
			logger.info("Quote Price : "+price);
			return null;
		}

		public String getAccountNumber() {
			testUtil.init(this);
			logger.info("Get Account Number from Contact and Routing Information");
			return accountNumberField.getAttribute("value"); 
		}
		
		public String getMyRoleStatus() {
			testUtil.init(this);
			logger.info("Get MyRole Status from Contact and Routing Information");
			return testUtil.getSelectedValue(weMyRole);
		}
		
		public String getTermsStatus() {
			testUtil.init(this);
			logger.info("Get Terms Status from Contact and Routing Information");
			return testUtil.getSelectedValue(weTerms);
		}
		
		public String getOriginZip() {
			testUtil.init(this);
			logger.info("Get OriginZip from Contact and Routing Information");
			return zipOrigin.getAttribute("value");
		}
		
		public String getDestZip() {
			testUtil.init(this);
			logger.info("Get DestZip from Contact and Routing Information");
			return zipDest.getAttribute("value");
		}
		
		public String getSelectedClass() {
			testUtil.init(this);
			logger.info("Get Selected Class Value from Commodities");
			return testUtil.getSelectedValue(commClass0);
		}
		
		public String getTotalWeight() {
			testUtil.init(this);
			logger.info("Get Total Weight from Commodities");
			return weight0.getAttribute("value");
		}
		
		public String getDescription() {
			testUtil.init(this);
			logger.info("Get Description value from Commodities");
			return desc0.getAttribute("value");
		}
			
		public void verifyTimeCriticalSummaryDisplayed() {
			testUtil.init(this);
			logger.info("Verify Time Critical Summary is displayed or not.");
			String label = timeCriticalSummaryLabel.getText();
			logger.info("Label : "+label);
			Assert.assertEquals(label.trim(), "Time Critical Quote Detail Summary".trim(),"Time Critical Summary is not displayed");
		}
		
		public List<String> fetchAllPrices() {
			testUtil.init(this);
			logger.info("Fetch all prices");
			List<WebElement> options = timeCriticalTable.findElements(By.tagName("tr"));
			ArrayList<String> prices = new ArrayList<>();
			for(int i=0;i<options.size();i++) {
				WebElement guranteedOption = options.get(i).findElement(By.xpath("td[4]"));
				String optionVal = guranteedOption.getText();
				logger.info("Prices : "+optionVal);
				prices.add(optionVal);
			}
			return prices;
		}
		
		public List<String> fetchVLTTableeNetNotes() {
			testUtil.init(this);
			logger.info("Fetch the VLT Table eNet Notes");
			List<WebElement> notes = vltTableeNetNotes.findElements(By.tagName("p"));
			ArrayList<String> vltList = new ArrayList<>();
			for(int i=0;i<notes.size();i++) {
				WebElement vltNotes = notes.get(i);
				String vltNotesStr = vltNotes.getText();
				logger.info("VLTnotesstr : "+vltNotesStr);
				vltList.add(vltNotesStr);
			}
			return vltList;
	}
		
		public void setNext7daysDate() {
			logger.info("Enter date");
			testUtil.init(this);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();
			LocalDate thenDate = localDate.plusDays(7);
			testUtil.setHardWait(2000);
			pickupDate.clear();
			pickupDateCalender.click();
			testUtil.setHardWait(200);
			String pickDate = dtf.format(thenDate);
			pickupDate.sendKeys(pickDate);
			setTodayDate(pickDate);
		}
		
		public void selectAvailableByandCloseByOptions(String starthours, String startmins, String startdayTime, String endhours, String endmins, String enddayTime) {
			testUtil.init(this);
			logger.info("Select Available By data");
			Select starthourSelect = new Select(startHour);
			starthourSelect.selectByValue(starthours);
			Select startMinSelect = new Select(startMin);
			startMinSelect.selectByValue(startmins);
			Select startAMPMSelect = new Select(startAMPM);
			startAMPMSelect.selectByValue(startdayTime);
			Select endHourSelect = new Select(endHour);
			endHourSelect.selectByValue(endhours);
			Select endMinSelect = new Select(endMin);
			endMinSelect.selectByValue(endmins);
			Select endAMPMSelect = new Select(endAMPM);
			endAMPMSelect.selectByValue(enddayTime);
		}
		
		public List<String> verifyContactMeLinkNotPresence() {
			testUtil.init(this);
			testUtil.setHardWait(1000);
			logger.info("Check Contact me link presence");
			
			List<WebElement> options = timeCriticalTable.findElements(By.tagName("tr"));
		
			String contactMeService;
			List<String> contactMeServices = new ArrayList<>();
			for(int i=0;i<options.size();i++) {
				WebElement contactMeLink = options.get(i).findElement(By.xpath("td[4]/a"));
				String optionVal = contactMeLink.getText();
				if(!optionVal.contains("Contact Me")) {
					contactMeService = options.get(i).findElement(By.xpath("td[3]")).getText();
					logger.info("contactMeService : "+contactMeService);
					contactMeServices.add(contactMeService);
				}
			}
			return contactMeServices;
		}
		
		public List<String> verifyContactMeLinkPresence() {
			testUtil.init(this);
			testUtil.setHardWait(1000);
			logger.info("Check Contact me link presence");
			List<WebElement> options = timeCriticalTable.findElements(By.tagName("tr"));
			String contactMeService;
			List<String> contactMeServices = new ArrayList<>();
			for(int i=0;i<options.size();i++) {
				WebElement contactMeLink = options.get(i).findElement(By.xpath("td[4]/a"));
				String optionVal = contactMeLink.getText().trim();
				if(optionVal.equalsIgnoreCase("Contact Me".trim())) {
					contactMeService = options.get(i).findElement(By.xpath("td[3]")).getText();
					logger.info("contactMeService : "+contactMeService);
					contactMeServices.add(contactMeService);
				}
			}
			return contactMeServices;
		}
		
		public void clickOnUpdateQuote() {
			testUtil.init(this);
			testUtil.setHardWait(1000);
			logger.info("Click on Update Quote button");
			updateQuoteBtn.click();
		}
		
		public void clickOnConstructionSiteDelivery() {
			testUtil.init(this);
			testUtil.setHardWait(1000);
			logger.info("Click on construction site delivery");
			constructionSiteDelivery.click();
		}
		
		public void clickOnProtectFromFreezing() {
			testUtil.init(this);
			logger.info("Click on Protect From Freezing checkbox");
			protectFromFreezing.click();
		}
		
		public void clickOnGuranteedBy5PMRate() {
			testUtil.init(this);
			logger.info("Click on Guaranteed LTL Standard Transit: 5PM");
			List<WebElement> options = exclusiveOptionTable.findElements(By.tagName("tr"));
			for(int i=0;i<options.size();i++) {
				WebElement exclusiveOption = options.get(i).findElement(By.xpath("td[3]"));
				String optionVal = exclusiveOption.getText();
				if(optionVal.equals("Guaranteed LTL Standard Transit: 5PM")) {
					WebElement exclusiveActionOption = options.get(i).findElement(By.xpath("td[4]/a"));
					exclusiveActionOption.click();
					break;
				}
			}
		}
		
		public String fetchComment() {
			testUtil.init(this);
			logger.info("Fetch comment.");
			String comment = quoteComments.getText();
			logger.info("Comment : "+comment);
			return comment;
		}
		
		public void checkCommentsCharacterlimit() {
			testUtil.init(this);
			logger.info("");
			String limitText = commentCharLimit.getText().trim();
			Assert.assertEquals(limitText, "you have reached the limit".trim(), "Characters limit not reach.");
		}
		
		public Map<String,String> recordRoutingInformation() {
			testUtil.init(this);
			logger.info("Record Routing information.");
			List<WebElement> routingList = routingElements.findElements(By.className("tableRow"));
			logger.info("Routing data list size : "+routingList.size());
			Map<String,String> routingInfo = new HashMap<>();
			String serviceLane = routingList.get(0).getText();
			logger.info("Service Lane : "+serviceLane);
			routingInfo.put("Service Lane", serviceLane);
			String origin = routingList.get(1).getText();
			logger.info("Origin : "+origin);
			routingInfo.put("Origin", origin);
			String destination = routingList.get(2).getText();
			logger.info("Destination : "+destination);
			routingInfo.put("Destination", destination);
			String terminalRoute = routingList.get(3).getText();
			logger.info("Terminal Route : "+terminalRoute);
			routingInfo.put("Terminal Route", terminalRoute);
			
			return routingInfo;
		}
		
		public void clickOnGuranteedBy10AMprice() {
			testUtil.init(this);
			logger.info("Click on GuranteedBy10AMprice");
			List<WebElement> options = timeCriticalTable.findElements(By.tagName("tr"));
			for(int i=0;i<options.size();i++) {
				WebElement guranteedOption = options.get(i).findElement(By.xpath("td[3]"));
				String optionVal = guranteedOption.getText();
				if(optionVal.equals("Guaranteed LTL Standard Transit: 10AM")) {
					WebElement guranteed5PMOption = options.get(i).findElement(By.xpath("td[4]/a"));
					guranteed5PMOption.click();
					break;
				}
			}
		}
		
		public void clearOriginCity() {
			testUtil.init(this);
			logger.info("Clear Origin City");
			originCity.clear();
		}
		
		public void clearDestinationCity() {
			testUtil.init(this);
			logger.info("Clear Destination City");
			destinationCity.clear();
		}
		
		public String getLength() {
			testUtil.init(this);
			logger.info("Get Lenght value from Commodities");
			return length0.getAttribute("value");
		}
		
		public String getHeight() {
			testUtil.init(this);
			logger.info("Get Height value from Commodities");
			return height0.getAttribute("value");
		}
		
		public String getWidth() {
			testUtil.init(this);
			logger.info("Get Width value from Commodities");
			return width0.getAttribute("value");
		}
		
		public void verifyReqAccessorialIsselected(String val) {
			logger.info("Verify required accessorial from the list is Selected");
			testUtil.init(this);
			List<WebElement> accessValues = driver.findElements(By.xpath("//*[@name='accessorialValueArray']/following-sibling::label"));
			for (WebElement value : accessValues) {
				String v = value.getText();
				if (v.equals(val)) {
					boolean act = driver.findElement(By.xpath("//*[text()=\" "+v+"\"]/preceding-sibling::input[2]")).isSelected();
					logger.info(driver.findElement(By.xpath("//*[text()=\" "+v+"\"]/preceding-sibling::input[2]"))+"!!!!!");
					Assert.assertEquals(act,true);
					break;
				}
			}
		}
		
		public void enterDescription(String desc) {
			testUtil.init(this);
			logger.info("Enter Description");
			desc0.sendKeys(desc);
			
		}
		
		//WIP
		
		public void verifyNoOverlengthIsSelected() {
			testUtil.init(this);
			logger.info("Verify no overlength is checked");
			
			
			List  <WebElement>checkBox=driver.findElements(By.xpath("//div[1]/input[@name='accessorialValueArray']"));      
				
			int listSize=checkBox.size();
			logger.info("Size of the ele" +listSize);
			
			for(int i= 0; i <listSize;i ++) {
				
				String eleName=checkBox.get(i).getText();
				logger.info("Name of element" + eleName);
			}
			

		}
		
		public void verifytimeCriticalPage() {
			testUtil.init(this);
			String pageName = pageTitle.getText();
			logger.info("Page Title : "+pageName);
			Assert.assertEquals(pageName, "Time Critical Rate Quote","Time Critical Rate Quote page title does not match.");
		}
				}
				

		


