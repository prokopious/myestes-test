
package eNetPages;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class ENetLTLRateQuotePage extends TestBase {
	private Logger logger = Logger.getLogger(ENetLTLRateQuotePage.class);

	@FindBy(how = How.ID, using = "selectOne")
	private WebElement selectOne;

	@FindBy(how = How.ID, using = "discount")
	private WebElement discount;

	@FindBy(how = How.ID, using = "trafficNumber")
	private WebElement trafficNumber;

	@FindBy(how = How.ID, using = "trafficName")
	private WebElement trafficName;

	@FindBy(how = How.ID, using = "trafficSuffix")
	private WebElement trafficSuffix;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div/div[2]/form/div[2]/div[3]/div[1]/div/div/div[2]/span[1]/div[2]/input")
	private WebElement AccountNumberField;

	@FindBy(how = How.ID, using = "my_role")
	private WebElement Role;

	@FindBy(how = How.ID, using = "terms")
	private WebElement Term;

	@FindBy(how = How.ID, using = "zipOrigin")
	private WebElement OriginZip;

	@FindBy(how = How.ID, using = "zipDestination")
	private WebElement DesZip;

	@FindBy(how = How.ID, using = "commClass0")
	private WebElement Class;

	@FindBy(how = How.ID, using = "weight0")
	private WebElement Weight;

	@FindBy(how = How.ID, using = "desc0")
	private WebElement Description;

	@FindBy(how = How.ID, using = "commClass1")
	private WebElement Class1;

	@FindBy(how = How.ID, using = "weight1")
	private WebElement Weight1;

	@FindBy(how = How.ID, using = "desc1")
	private WebElement Description1;

	@FindBy(how = How.ID, using = "ratequoteSubmitButton")
	private WebElement Submit;
	@FindBy(how = How.ID, using = "commClass2")
	private WebElement Class2;

	@FindBy(how = How.ID, using = "weight2")
	private WebElement Weight2;

	@FindBy(how = How.ID, using = "desc2")
	private WebElement Description2;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'exceptionMessage')]//li")
	private WebElement errMsg;
	
	@FindBy(how = How.ID, using ="clear_origin")
	private WebElement orginClearBtn;
	
	@FindBy(how = How.ID, using ="clear_destination")
	private WebElement destinationClearBtn;
	
	@FindBy(how = How.ID, using ="countryDestination")
	private WebElement countryDestination;
	
	@FindBy(how = How.ID, using ="countryOrigin")
	private WebElement countryOrigin;
	
	@FindBy(xpath = "//*[@id='ltl_rate_request_intro_module']/h1")
	private WebElement pageTitle;
	
	@FindBy(xpath ="//input[@type = 'button' and @name = 'expand']")
	private WebElement expandButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='tableRow removable']")
	private List<WebElement> commodityRows;
	
	@FindBy(how = How.ID, using = "ltlAddComButton")
	private WebElement addCommodity;
	
	@FindBy(how = How.ID, using = "suggestOrigin")
	private WebElement originSuggetions;

	@FindBy(how = How.ID, using = "suggestDestination")
	private WebElement destSuggetions;
	
	@FindBy(how = How.ID, using = "clear_origin")
	private WebElement origClearBtn;

	@FindBy(how = How.ID, using = "clear_destination")
	private WebElement destClearBtn;
	
	@FindBy(how = How.ID, using = "cityOrigin")
	private WebElement originCity;

	@FindBy(how = How.ID, using = "cityDestination")
	private WebElement destCity;

	@FindBy(how = How.ID, using = "stateOrigin")
	private WebElement origState;

	@FindBy(how = How.ID, using = "stateDestination")
	private WebElement destState;

	@FindBy(how = How.XPATH, using = "//*[@class='button expand']")
	private WebElement expandBtn;
	
	//@FindBy(how = How.ID, using = "pickupDate")
	@FindBy(how = How.XPATH, using = "//*[@id='pickupDate'] [@name='scheduling.pickupDate']")
	
	private WebElement pickupDate;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Estimated Freight Charges']/following-sibling::td")
	private WebElement EstimatedFreightCharges;
	
	@FindBy(how = How.XPATH, using = "//*[@class='ui-datepicker-trigger']")
	private WebElement pickupDateCalender;
	
	@FindBy(how = How.XPATH, using = "//*[@class='ui-datepicker-month']")
	private WebElement pickupDateMonthSelector;
	
	@FindBy(how = How.XPATH, using = "//*[@class='ui-datepicker-prev ui-corner-all']")
	private WebElement pickupDateBeforePerview;
	
	@FindBy(how = How.XPATH, using = "//*[@class='ui-datepicker-next ui-corner-all']")
	private WebElement pickupDateAfterPreview;

	
	/*********************METHODS***********************/

	public void selectOne(String SelectOne) {
		logger.info("Select one");
		testUtil.init(this);
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame1);
		testUtil.selectUsingValue(selectOne, SelectOne);
	}

	public void enterAccountNumber(String aNum) {
		logger.info("Enter Account Number");
		testUtil.init(this);
		//WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']")); --Removed as there is no frame
		try {
			//driver.switchTo().frame(frame1);  --Removed the line
			testUtil.assetWait(null, AccountNumberField, 10, 200, TimeUnit.MILLISECONDS);
			AccountNumberField.sendKeys(aNum);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void enterDiscount(String discnt) {
		logger.info("Enter Discount");
		testUtil.init(this);
		discount.sendKeys(discnt);
	}

	public void selectRole(String role) {
		logger.info("Select Role");
		testUtil.init(this);
		testUtil.selectUsingVisibleText(Role, role);
	}

	public void entertrafficNumber(String trafficNum) {
		logger.info("Enter Traffic Number");
		testUtil.init(this);
		trafficNumber.clear();
		trafficNumber.sendKeys(trafficNum);
	}

	public void entertrafficSuffix(String trafficSufFix) {
		logger.info("Enter Traffic Suffix");
		testUtil.init(this);
		trafficSuffix.clear();
		trafficSuffix.sendKeys(trafficSufFix);
	}

	public void selectTerm(String term) {
		logger.info("select Term");
		testUtil.init(this);
		testUtil.selectUsingVisibleText(Term, term);
	}

	public void enterOriginZip(String oZip) {
		logger.info("enter Origin Zip ");
		testUtil.init(this);
		OriginZip.sendKeys(oZip);
		testUtil.setHardWait(500);
		driver.findElement(By.id("suggestOrigin")).click();
		testUtil.setHardWait(1000);
	}

	public void enterDestinationZip(String dZip) {
		logger.info("enter Destination Zip ");
		testUtil.init(this);
		DesZip.sendKeys(dZip);
		testUtil.setHardWait(500);
		driver.findElement(By.id("suggestDestination")).click();
		testUtil.setHardWait(1000);
	}

	public void selectClass(String mClass) {
		testUtil.init(this);
		logger.info("select Class");
		testUtil.selectUsingVisibleText(Class, mClass);
	}

	public void enterWeight(String weight) {
		testUtil.init(this);
		logger.info("enter Weight");
		Weight.sendKeys(weight);
	}

	public void enterDesc(String desc) {
		testUtil.init(this);
		logger.info("Enter description");
		testUtil.setExplicitWait(Description, 30);
		Description.sendKeys(desc);
	}

	public void selectClass1(String mClass) {
		testUtil.init(this);
		logger.info("select Class");
		testUtil.selectUsingVisibleText(Class1, mClass);
	}

	public void enterWeight1(String weight) {
		testUtil.init(this);
		logger.info("enter Weight");
		Weight1.sendKeys(weight);
	}

	public void enterDesc1(String desc) {
		testUtil.init(this);
		logger.info("enter Description");
		Description1.sendKeys(desc);
	}

	public void clickOnSubmitButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("click On Submit Button");
		testUtil.setExplicitWait(Submit, 60);
		int i = 0;
		int exist = 0;

		do {
			testUtil.clickElementJavascript(Submit);
			testUtil.setHardWait(1000);
			if (testUtil.isDisplayed(driver.findElement(By.xpath("//h1[contains(text(),'LTL Rate Request')]")))) {

				exist = 1;
			}

			i++;
		} while (i <= 5 && exist != 1);
	}

	public void clickOnOverlengthAccessorial(String accName) {
		testUtil.init(this);
		logger.info("Click on Accessorial " + accName);
		driver.findElement(By.xpath("//label[contains(text(),\"" + accName + "\")]")).click();
		//driver.findElement(By.xpath("//label[contains(text(),'" + accName + "')]")).click();
		testUtil.setHardWait(3000);
	}
	
	public void verifyQuoteSummaryByDesc(String description) {
		logger.info("Verify " + description + " is returned");
		testUtil.init(this);
		List<WebElement> quoteSummary = driver.findElements(By.xpath("//*[@id='quote_summary']//tbody//tr[*]"));
		boolean val = false;
		for (int i = 1; i <= quoteSummary.size(); i++) {
			String actualDesc = driver.findElement(By.xpath("//*[@id='quote_summary']//tbody//tr[" + i + "]/td[1]"))
					.getText();
			if (actualDesc.equalsIgnoreCase(description)) {
				String total = driver.findElement(By.xpath("//*[@id='quote_summary']//tbody//tr[" + i + "]/td[2]"))
						.getText();
				System.out.println("Charge for " + description + " is " + total);
				val = true;
				break;
			}
		}
		Assert.assertTrue(val);
	}

	public void verifyEstimatedFreightCharge() {
		logger.info("Verify estimated freight charge is displayed");
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='quote_summary']//tfoot//td[1]")).isDisplayed());
		Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='quote_summary']//tfoot//td[2]")).getText());
	}
	public void selectClass2(String mClass) {
		testUtil.init(this);
		logger.info("select Class");
		testUtil.selectUsingVisibleText(Class2, mClass);
	}

	public void enterWeight2(String weight) {
		testUtil.init(this);
		logger.info("enter Weight");
		Weight2.sendKeys(weight);
	}

	public void enterDesc2(String desc) {
		testUtil.init(this);
		logger.info("enter Description");
		Description2.sendKeys(desc);
	}

	public void verifyDefaultValueInSelectOne() {
		logger.info("Verify default value selected is Account Specific");
		testUtil.init(this);
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame1);
		Select select = new Select(selectOne);
		WebElement option = select.getFirstSelectedOption();
		String selectedValue = option.getText();
		Assert.assertEquals("Account Specific",selectedValue);
	}
	
	public void verifyDiscountFieldIsDisplayed(boolean existence) {
		logger.info("Verify Discount field is displayed");
		testUtil.init(this);
	boolean val;
		try {
			WebElement discountField = driver.findElement(By.id("discount"));
			val = discountField.isDisplayed();
		   }
		catch (NoSuchElementException e){
			val = false;
		}
		if(existence) {
			Assert.assertTrue(val);
			Assert.assertEquals(discount.getAttribute("value"), "70");
		}
		else {
			Assert.assertFalse(val);
		}
		driver.switchTo().defaultContent();
	}
	
	public void clickOnSubmit() {
		testUtil.init(this);
		logger.info("click On Submit Button");
		testUtil.assetWaitClickable(null, Submit, 10, 200, TimeUnit.MILLISECONDS);;
		testUtil.clickElementJavascript(Submit);
		testUtil.setHardWait(1000);
	}
	
	public void verifyErrorMsg(String errorText) {
		logger.info("Verify Error message");
		testUtil.init(this);
		String actualText = errMsg.getText();
		System.out.println(actualText);
		Assert.assertEquals(actualText.trim(), errorText.trim());
	}
	
	public void clickOnOriginClearButton() {
		logger.info("Click on Origin clear button");
		testUtil.init(this);
		orginClearBtn.click();
	}
	
	public void clickOnDestinationClearButton() {
		logger.info("Click on Origin clear button");
		testUtil.init(this);
		destinationClearBtn.click();
	}
	
	public void selectOriginCountry(String country) {
		logger.info("Select country");
		testUtil.init(this);
		testUtil.selectUsingValue(countryOrigin, country);
	}
	
	public void selectDestinationCountry(String country) {
		logger.info("Select country");
		testUtil.init(this);
		testUtil.selectUsingValue(countryDestination, country);
	}
		
	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify the LTL Rate Quote page.");
		driver.switchTo().frame("mainpage");
		testUtil.assetWait(null, pageTitle, 10, 200, TimeUnit.MILLISECONDS);
		String pageTtl = pageTitle.getText();
		logger.info("Page title : "+pageTtl);
		Assert.assertEquals(pageTtl, "LTL Rate Quote");
		//Assert.assertEquals(pageTtl, "LTL Rate Quote", "Page Title doesnot match.");
		testUtil.setHardWait(2000);
	}
	
	public void verifyTariffFieldIsDisplayed(boolean existence) {
		logger.info("Verify Tarrif field is displayed");
		testUtil.init(this);
		boolean val;
		try {
			WebElement tariffField = driver.findElement(By.id("trafficNumber"));
			val = tariffField.isDisplayed();
		}
		catch (NoSuchElementException e){
			val = false;
		}
		if(existence) {
			Assert.assertTrue(val);
		}
		else {
			Assert.assertFalse(val);
		}
		driver.switchTo().defaultContent();
	}

	public void verifLTLRateQuotePage() {
		logger.info("Verify LTL Rate Request page is displayed");
		testUtil.init(this);
		testUtil.assetWait("//iframe[@id='mainpage']", null, 10, 200, TimeUnit.MILLISECONDS);
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
	    testUtil.switchToFrame(frame1);
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),"LTL Rate Quote");
		//driver.switchTo().defaultContent();
	}
	
	public void verifyDataByField(WebElement ele, String dataToCheck) {
		logger.info("Verify values in field");
		testUtil.init(this);
		String value = ele.getAttribute("value");
		Assert.assertEquals(value, dataToCheck);
	}

	public void entertrafficName(String TrafficName) {
		logger.info("Enter traffic Name");
		testUtil.init(this);
		trafficName.clear();
		trafficName.sendKeys(TrafficName);
	}
	
	public void clickOnAccessorial(String accName) {
		testUtil.init(this);
		logger.info("Click on Accessorial " + accName);
		
		driver.findElement(By.xpath("//label[contains(text(),\"" + accName + "\")]")).click();
		testUtil.setHardWait(3000);
	}
	
	public void clickOnAccessorialByCode(String accCode) {
		testUtil.init(this);
		logger.info("Click on Accessorial " + accCode);
		driver.findElement(By.xpath("//input[@id= '"+accCode+"' and @type = 'checkbox']")).click();
		testUtil.setHardWait(3000);
	}
	
	public void clickOnExpandButton() {
		testUtil.init(this);
		logger.info("Click on Expand button");
		expandButton.click();
		testUtil.setHardWait(3000);
	}
	
	
	public void validateCommodityRowsDisplayed(int expectedRows) {
		logger.info("Validate Commodity Rows are displayed.");
		testUtil.init(this);
		int rows = commodityRows.size();
		System.out.println(rows);
		Assert.assertEquals(rows, expectedRows);
		for (WebElement row : commodityRows) {
			Assert.assertEquals(row.isDisplayed(), true);
		}
	}
	
	public String getDefaultClassValue() {
		logger.info("Validate  default Commodity class values .");
		testUtil.init(this);
		String options = Class.getText().replaceAll("\\s+", " ");
		System.out.println(options);
		return options;
	}
	
	public String getWeightValue(int row) {
		testUtil.init(this);
		logger.info("Get Weight Field Value");
		String act = driver.findElement(By.id("weight" + row)).getAttribute("value");
		return act;
	}

	public void validateWeightFieldLength(String exp) {
		testUtil.init(this);
		logger.info("Verify Weight Field Allows not morethan 7");
		String act = Weight.getAttribute("maxlength");
		Assert.assertEquals(act, exp);
	}
	
	public void addAdditionalCommodities(int totalRows) {
		testUtil.init(this);
		logger.info("Add Additional Commodities");
		for (int i = 0; i < totalRows; i++) {
			testUtil.WaitForTheElementClickable(addCommodity);
			addCommodity.click();
			testUtil.setHardWait(1000);
		}
	}

	public void enterMultipleCommodities(int totalRows, String[] classVal, String[] weight, String des) {
		testUtil.init(this);
		logger.info("Add Multiple Commodities- Class,Weight,Description");
		for (int i = 0; i < totalRows; i++) {
			testUtil.selectUsingVisibleText(driver.findElement(By.id("commClass" + i)), classVal[i]);

			WebElement wei = driver.findElement(By.id("weight" + i));
			wei.clear();
			wei.sendKeys(weight[i]);

			WebElement description = driver.findElement(By.id("desc" + i));
			description.clear();
			description.sendKeys(des + i);
		}

	}
	
	public void enterOrigZip(String oZip) {
		logger.info("enter Origin Zip ");
		testUtil.init(this);
		OriginZip.clear();
		OriginZip.sendKeys(oZip);
		testUtil.setHardWait(1000);
	}
	
	public void verifyOriginSuggestDisplayed() {
		testUtil.init(this);
		logger.info("Verify Suggentions is Displayed while entering Origin");
		Assert.assertEquals(testUtil.isDisplayed(originSuggetions),true);
	}
	
	public void clickOnReqSuggests(String reqNum) {
		logger.info("Click on required value Suggestions list");
		testUtil.init(this);
		List<WebElement> suggestions = driver.findElements(By.className("suggestLine"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (WebElement ele : suggestions) {
			if (ele.getText().contains(reqNum)) {
				testUtil.setHardWait(1000);
				js.executeScript("arguments[0].click();", ele);
				break;
			}
		}
	}
	
	public void verifyDestSuggestDisplayed() {
		testUtil.init(this);
		logger.info("Verify Suggentions is Displayed while entering Origin");
		testUtil.isDisplayed(destSuggetions);
	}

	public void enterDestZip(String oZip) {
		logger.info("enter Destination Zip ");
		testUtil.init(this);
		DesZip.clear();
		DesZip.sendKeys(oZip);
		testUtil.setHardWait(1000);
	}

	public void clickOnOrigClrBtn() {
		testUtil.init(this);
		logger.info("Click on Origin Clear Button");
		origClearBtn.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnDestClrBtn() {
		testUtil.init(this);
		logger.info("Click on Destination Clear Button");
		destClearBtn.click();
		testUtil.setHardWait(1000);
	}
	
	public void enterOrigCity(String oCity) {
		logger.info("Enter Origin City");
		testUtil.init(this);
		originCity.clear();
		originCity.sendKeys(oCity);
		testUtil.setHardWait(1000);
	}

	public void enterDestCity(String dCity) {
		logger.info("Enter Destination City");
		testUtil.init(this);
		destCity.clear();
		destCity.sendKeys(dCity);
		testUtil.setHardWait(1000);
	}

	public void enterOrigState(String oState) {
		logger.info("Enter Origin State");
		testUtil.init(this);
		origState.clear();
		origState.sendKeys(oState);
		testUtil.setHardWait(1000);
	}

	public void enterDestState(String dState) {
		logger.info("Enter Destination State");
		testUtil.init(this);
		destState.clear();
		destState.sendKeys(dState);
		testUtil.setHardWait(1000);
	}
	
	
	public void selectOneWithoutFrame(String SelectOne) {
		logger.info("Select one");
		testUtil.init(this);
		testUtil.selectUsingVisibleText(selectOne, SelectOne);
	}

	public void enterAccountNumberWithoutFrame(String aNum) {
		logger.info("Enter Account Number");
		testUtil.init(this);
		testUtil.setExplicitWait(AccountNumberField, 90);
		AccountNumberField.sendKeys(aNum);
	}

	public void validatePagetitle(String expectedTitle) {
		logger.info("Validate page title");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		String title = driver.findElement(By.xpath("//*[text()='LTL Rate Quote']")).getText();
		System.out.println(title);
		Assert.assertTrue(title.contains(expectedTitle));
	}

	

	
	public void enterPickupDate(String date) {
		logger.info("Enter pickup date: " + date);
		testUtil.init(this);
		//pickupDate.clear();
		pickupDate.sendKeys(date);
	}
	
	public void verifyTariffNumberIsEnabledOrNot(boolean status) {
		logger.info("Verify Tariff Number is Enabled or not");
		testUtil.init(this);
		boolean st = trafficNumber.isDisplayed();
		Assert.assertEquals(st, status);
	}
	
	
	
	public String getFreightCharges() {
		logger.info("Click on Time Critical in Additional Options");
		testUtil.init(this);
		String charges=testUtil.getTextOfElement(EstimatedFreightCharges).replace("$", "").trim();
		return charges;
	}
	
	
	public void verifyAccountNumber(String aNum) {
			logger.info("Enter Account Number");
			testUtil.init(this);
			String actualAcctNum = AccountNumberField.getAttribute("value");
			Assert.assertEquals(actualAcctNum, aNum);
		}

	public void verifyEnteredValue(String element, String value) {
		logger.info("Verify entered value");
		WebElement Ele = driver.findElement(By.id(element));
		Assert.assertEquals(Ele.getAttribute("value"), value);
	}

	public void verifySelectedValue(String element, String value) {
		logger.info("Get Selected value from options");
		WebElement Ele = driver.findElement(By.id(element));
		Select s = new Select(Ele);
		WebElement option = s.getFirstSelectedOption();
		String SMCStatus = option.getText();
		Assert.assertEquals(SMCStatus, value);
	}
	public String getAccountNumber() {
		testUtil.init(this);
		logger.info("Get Account Number from Contact and Routing Information");
		String accNum=AccountNumberField.getAttribute("value");
		return accNum;
	}
	
	public String getMyRoleStatus() {
		testUtil.init(this);
		logger.info("Get MyRole Status from Contact and Routing Information");
		String myRole=testUtil.getSelectedValue(Role);
		return myRole;
	}
	
	public String getTermsStatus() {
		testUtil.init(this);
		logger.info("Get Terms Status from Contact and Routing Information");
		String term=testUtil.getSelectedValue(Term);
		return term;
	}
	
	public String getOriginZip() {
		testUtil.init(this);
		logger.info("Get OriginZip from Contact and Routing Information");
		String orig=OriginZip.getAttribute("value");
		return orig;
	}
	
	public String getDestZip() {
		testUtil.init(this);
		logger.info("Get DestZip from Contact and Routing Information");
		String dest=DesZip.getAttribute("value");
		return dest;
	}
	
	public String getSelectedClass(int row) {
		testUtil.init(this);
		logger.info("Get Selected Class Value from Commodities");
		 WebElement clsValue = driver.findElement(By.id("commClass" + row));
		String classV=testUtil.getSelectedValue(clsValue);
		return classV;
	}
	
	public String getDescription(int row) {
		testUtil.init(this);
		logger.info("Get Description value from Commodities");
		String description=driver.findElement(By.id("desc" + row)).getAttribute("value");
		return description;
	}
	
	public void ValidateAttentionMessage(String message) {
		logger.info("Get Attention/Error message ");
		testUtil.init(this);
		String error=driver.findElement(By.className("exceptionMessage")).getText();
		System.out.println(error);
		Assert.assertEquals(error, message);
	}
	
	public void clickOnAddCommodity() {
		testUtil.init(this);
		logger.info("Click on Additional Commodity");
		addCommodity.click();
	}
	
	public void verifyTermStatusIsEnabled(boolean status) {
		logger.info("Verify Terms Field is Enabled or not");
		testUtil.init(this);
		boolean st = Term.isEnabled();
		Assert.assertEquals(st, status);
	}
	
	public void verifyGivenValueInSelectOne(String givenValue) {
		logger.info("Verify given value selected in SelectOne");
		testUtil.init(this);
//		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
//		driver.switchTo().frame(frame1);
		Select select = new Select(selectOne);
		WebElement option = select.getFirstSelectedOption();
		String selectedValue = option.getText();
		Assert.assertEquals(givenValue,selectedValue);
	}
	
	public String getPickUpDate() {
		testUtil.init(this);
		logger.info("Get PickUpDate ");
		String pcDate =pickupDate.getAttribute("value");
		return pcDate;
	}

	public void verifyCalenderPreviouMonthSelectionDisplayed() {
		testUtil.init(this);
		logger.info("Verify Previous Month Selection Displayed");
		Assert.assertEquals(testUtil.isDisplayed(pickupDateBeforePerview),true);
	}
	
	public void clickOnPickUpDateCalender() {
		testUtil.init(this);
		logger.info("Click on Calender");
		pickupDateCalender.click();
		testUtil.setHardWait(3000);
	}
	
	public void validateMonthSelectionsDisplayed(int expectedRows) {
		logger.info("Validate Month Sekections are displayed.");
		testUtil.init(this);
		Select select = new Select(pickupDateMonthSelector);
		List<WebElement> option = select.getOptions();
		int rows = option.size();
		System.out.println(rows);
		Assert.assertEquals(rows, expectedRows);
	}
	
	public void verifyCalenderNextMonthSelectionDisplayed() {
		testUtil.init(this);
		logger.info("Verify Next Month Selection Displayed");
		Assert.assertEquals(testUtil.isDisplayed(pickupDateAfterPreview),true);
	}
	
}


