package eNetPages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class ENetLTLRateRequestPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetLTLRateRequestPage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@value='Update Quote']")
	private WebElement updateQuote;

	@FindBy(how = How.XPATH, using = "//fieldset/div/div/div[1]/div[2]")
	private WebElement quoteNo;

	@FindBy(how = How.XPATH, using ="//label[@for='guaranteed_12']")
	private WebElement guaranteed12Pm;
	
	@FindBy(how = How.ID, using ="comments")
	private WebElement commentsField;
	
	@FindBy(how = How.ID, using ="saveComments")
	private WebElement saveButton;
	
	@FindBy(how = How.XPATH, using ="//input[@value='Print Quote']")
	private WebElement printQuoteButton;
	
	@FindBy(how = How.XPATH, using ="//input[@value='View Quote History']")
	private WebElement viewQuoteHistoryButton;
	
	@FindBy(how = How.ID, using ="from_address")
	private WebElement originLink;
	
	@FindBy(how = How.ID, using ="to_address")
	private WebElement destinationLink;
	
	@FindBy(how = How.XPATH, using ="//h2[contains(text(),'Supporting Terminal')]")
	private WebElement supportingTerminalPopup;
	
	@FindBy(how = How.XPATH, using ="//*[@title='Close']")
	private WebElement closeIcon;
	

	@FindBy(xpath = "//*[@id='ltl_rate_request_intro_module']/h1")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[@id='home']")
	private WebElement homeTab;
	
	@FindBy(xpath = "//*[@id='buttonsDiv']/input[4]")
	private WebElement viewQuoteHistory;
	
	@FindBy(how = How.ID, using = "from_address")
	private WebElement routeOrigin;

	@FindBy(how = How.ID, using = "to_address")
	private WebElement routeDestination;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Estimated Freight Charges']/following-sibling::td")
	private WebElement EstimatedFreightCharges;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Time Critical']")
	private WebElement TimeCritical;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Generate New Quote']")
	private WebElement GenerateQuote;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Added weight')]")
	private WebElement AddedWeight;
	
	@FindBy(how = How.XPATH, using = "//a[@id='Applications']")
	private WebElement Applications;
	
	

	@FindBy(how = How.XPATH, using = "//*[@value='Create BOL Link']")
	private WebElement createBOLLink;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Save Comments']")
	private WebElement saveComments;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Send to Call Center']")
	private WebElement sendToCallCenter;
			
	@FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/p")
	private WebElement successMessage;
	
	@FindBy(how = How.XPATH, using = "//*[@name='fullname']")
	private WebElement BOLName;
	
	@FindBy(how = How.ID, using = "contact_username")
	private WebElement BOLUsername;
	
	@FindBy(how = How.ID, using = "contact_email")
	private WebElement BOLEmail;
	
	@FindBy(how = How.ID, using = "comments")
	private WebElement BOLComments;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Cancel']")
	private WebElement cancelBOLlink;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Submit']")
	private WebElement submitBOLlink;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div/div/div/div/input")
	private WebElement closeBOLlink;
	
	@FindBy(how = How.XPATH, using = "//*[@class='fancybox-iframe']")
	private WebElement fancyFrame;
	
	@FindBy(how = How.XPATH, using = "//*[@id='contact_name']")
	private WebElement fullNameInSendTocallCenterPopup;

	@FindBy(how = How.XPATH, using = "//*[@id='contact_email']")
	private WebElement eMailInSendTocallCenterPopup;
	
	@FindBy(how = How.XPATH, using = "//*[@id='contact_phone']")
	private WebElement phoneInSendTocallCenterPopup;
	
	@FindBy(how = How.XPATH, using = "//*[@id='comments']")
	private WebElement commentsInSendTocallCenterPopup;

	@FindBy(how = How.XPATH, using = "//*[@value='Submit']")
	private WebElement submitSentToCallCenter;
	
	@FindBy(how = How.XPATH, using = "//*[@id='quote_summary']/table/tbody/tr[3]/td[2]")
	private WebElement FuelCharges;
	
	/****************************METHODS****************************************/

	public void ValidateLTLStandardTransitQuoteIDPrefixIsL() throws InterruptedException {
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("(//div[@class='tableCell'])[1]"));

		logger.info("validate LTL Standard Transit Qoute ID Prifix is L");
		String qoute = driver
				.findElement(By.xpath("//*[@id=\"quote_information_container\"]/div[1]/fieldset/div/div/div[1]/div[2]"))
				.getText();
		System.out.println(qoute);
		assertTrue(qoute.contains("L"));

	}


	public void verifyRateDisplayed(String serviceLevel) { // ex: guaranteed10,ltlStandard
		logger.info("Verify rate displayed for " + serviceLevel);
		testUtil.init(this);
		String rate = driver.findElement(By.xpath("//*[@id='" + serviceLevel + "']//span")).getText();
		//Assert.assertTrue(rate.equals("Contact Us"));
		Assert.assertTrue(rate.contains("+$"));
	}

	public void verifyContactUsDisplayed(String serviceLevel) { // ex: guaranteed10,ltlStandard
		logger.info("Verify Contact Us displayed for " + serviceLevel);
		testUtil.init(this);
		String text = driver.findElement(By.xpath("//*[@id='" + serviceLevel + "']//span")).getText();
		Assert.assertTrue(text.equals("Contact Us"));
	}

	public String recordQuoteNo() {
		logger.info("Record Quote#");
		testUtil.init(this);
		String quoteNum = quoteNo.getText();
		System.out.println("Record Quote# :" + quoteNum);
		return quoteNum;
	}

	public void clickOnUpdateQuote() {
		logger.info("Click on update quote");
		testUtil.init(this);
		updateQuote.click();
	}

	public void verifyAccessorialIsDisplayedWithCharge(String accName, String charge) {
		logger.info("Verify Accessorial is displayed in Quote Summary");
		testUtil.init(this);
		List<WebElement> rowCount = driver.findElements(By.xpath("//div[@id='quote_summary']//tbody//tr[*]"));
		boolean val = false;

		for (int i = 1; i <= rowCount.size(); i++) {
			String actual = driver.findElement(By.xpath("//div[@id='quote_summary']//tbody//tr[" + i + "]/td[1]"))
					.getText();
			System.out.println(actual + "!!!!!!");
			if (actual.equals(accName)) {
				val = true;
				String actualCharge = driver
						.findElement(By.xpath("//div[@id='quote_summary']//tbody//tr[" + i + "]/td[2]")).getText();
				Assert.assertEquals(actualCharge, charge);
				break;
			}
		}
		Assert.assertTrue(val);
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

	public void verifLTLRateRequestPage() {
		logger.info("Verify LTL Rate Request page is displayed");
		testUtil.init(this);
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),"LTL Rate Request");
	}
	
	public void SelectQuoteByServiceLevel(String serviceLevel) {
		logger.info("Click on "+serviceLevel);
		testUtil.init(this);
		String ele = null;
		switch (serviceLevel) {
			case "gauranteed10AM":
				ele = "//label[@for='guaranteed_10']";
				break;
			case "gauranteed12PM":
				ele = "//label[@for='guaranteed_12']/span";
				break;
			case "gauranteed5PM":
				ele = "//label[@for='guaranteed_5']";
				break;
			case "ltlStandard":
				ele = "//label[@for='ltl_standard']";
				break;
		}
		
		driver.findElement(By.xpath(ele)).click();
		testUtil.setHardWait(3000);
		System.out.println("!!!!");
	}
	
	public void validateTotalChargeByServiceLevel(String serviceLevel) {
		logger.info("Validate Total Charge of "+serviceLevel);
		testUtil.init(this);
		String ele = null;
		switch (serviceLevel) {
			case "gauranteed10AM":
				ele = "//label[@for='guaranteed_10']//span[contains(text(),'Total')]";
				break;
			case "gauranteed12PM":
				ele = "//label[@for='guaranteed_12']//span[contains(text(),'Total')]";
				break;
			case "gauranteed5PM":
				ele = "//label[@for='guaranteed_5']//span[contains(text(),'Total')]";
				break;
			case "ltlStandard":
				ele = "//label[@for='ltl_standard']//span[contains(text(),'Total')]";
				break;
		}
		Assert.assertNotNull(driver.findElement(By.xpath(ele)).getText());
	}
	
	public void enterComments(String comments) {
		logger.info("Enter comments");
		testUtil.init(this);
		commentsField.sendKeys(comments);
	}
	
	public void clickOnSaveButton() {
		logger.info("Click on Save button");
		testUtil.init(this);
		saveButton.click();
	}
	
	public void clickOnPrintButton() {
		logger.info("Click on Print Quote");
		testUtil.init(this);
		printQuoteButton.click();
	}
	
	public void clickOnViewQuoteHistoryButton() {
		logger.info("Click on View Quote History");
		testUtil.init(this);
		viewQuoteHistoryButton.click();
	}
	
	public void verifyComments(String comments) {
		logger.info("Verify comments");
		testUtil.init(this);
		String actual = driver.findElement(By.xpath("//fieldset[3]//p")).getText();
		Assert.assertEquals(actual, comments);
	}
	
	public void clickOnOriginLink() {
		logger.info("Click on Origin Terminal Link");
		testUtil.init(this);
		originLink.click();
	}
	
	public void clickOnDestinationLink() {
		logger.info("Click on Destination Terminal Link");
		testUtil.init(this);
		destinationLink.click();
	}
	
	public void verifySupportingTerminalPopup() {
		logger.info("Verify Supporting Terminal pop up is displayed");
		testUtil.init(this);
		WebElement frameName = driver.findElement(By.xpath("//iframe[contains(@name, 'fancybox')]"));
		driver.switchTo().frame(frameName);
		Assert.assertTrue(supportingTerminalPopup.isDisplayed());
	}
	
	public void verifyAddressDetailsInSupportingTerminalPopup(String address, String phone, String fax)
	{
		String actualAddress = driver.findElement(By.xpath("//*[@id='popup_content_container']/fieldset/div/div/div[1]/div[2]")).getText();
		Assert.assertTrue(actualAddress.contains(address));
		String actualPhone = driver.findElement(By.xpath("//*[@id='popup_content_container']/fieldset/div/div/div[3]/div[2]")).getText();
		Assert.assertEquals(actualPhone, phone);
		String actualFax = driver.findElement(By.xpath("//*[@id='popup_content_container']/fieldset/div/div/div[5]/div[2]")).getText();
		Assert.assertEquals(actualFax, fax);
		driver.switchTo().defaultContent();
	}
	
	public void clickOnCloseInPopUp() {
		logger.info("Click on Close Icon");
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		closeIcon.click();
		testUtil.setHardWait(3000);
	}

	public void verifyAccountInQuoteInfoSection(String expectedAcct) {
		logger.info("Verify Account");
		testUtil.init(this);
		String actualAcct = driver.findElement(By.xpath("//*[contains(@class, 'colOne')]//div[2]/div[2]")).getText();
		Assert.assertEquals(actualAcct.trim(), expectedAcct.trim());
	}
	
	public void verifyDiscountInQuoteSummarySection(String discount) {
		logger.info("Verify Discount field displayed in Quote Summary section");
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+discount+"')]")).isDisplayed());
	}
	
	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify LTL Rate Request page");
//		driver.switchTo().frame("mainpage");
		String pageTtl = pageTitle.getText();
		Assert.assertEquals(pageTtl, "LTL Rate Request","Page Title doesnot match.");
	}
	
	public void clickOnHomeTab() {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		logger.info("Click Home tab.");
		homeTab.click();
	}
	
	public void clickOnViewQuoteHistory() {
		testUtil.init(this);
		logger.info("Click on View Quote History.");
		viewQuoteHistory.click();
	}

	public String recordTariff() {
		logger.info("Record Tariff");
		testUtil.init(this);
		String tarrif = driver.findElement(By.xpath("//*[@id='quote_information_container']//div[5]/div[2]")).getText();
		return tarrif;
	}
	
	public String recordEstimatedFreightCharge() {
		logger.info("Record Freight charge");
		testUtil.init(this);
		String estimatedfbCharge = driver.findElement(By.xpath("//*[@id='quote_summary']//tfoot//td[2]")).getText();
		return estimatedfbCharge;
	}
	
	public String getAddedWeight() {
		logger.info("Validate page title");
		testUtil.init(this);
		String addWeight = driver.findElement(By.xpath("//*[contains(text(),'Added weight')]/following-sibling::td[2]")).getText();
		return addWeight;
	}
	
	public void validateCommodityValue(String exdes, String[] exclassValues, String[] exweights) {
		logger.info("Validate Commodity Values are same");
		testUtil.init(this);
		List<WebElement> actRows = driver.findElements(By.xpath("//td[contains(text(),'Line')]"));
		for (int i = 0; i < actRows.size(); i++) {
			logger.info("Verify comodity value in Line" + i);
			String desc = actRows.get(i).getText();
			Assert.assertTrue(desc.contains(exdes + i));
			String classVal = driver
					.findElement(By.xpath("//td[contains(text(),'Line " + i + "')]/following-sibling::td[1]"))
					.getText();
			Assert.assertEquals(classVal, exclassValues[i]);
			String actWeigh = driver
					.findElement(By.xpath("//td[contains(text(),'Line " + i + "')]/following-sibling::td[2]"))
					.getText();
			Assert.assertEquals(actWeigh, exweights[i]);

		}
	}

	public String getRouteOrigin() {
		logger.info("Get Origin Route Information");
		testUtil.init(this);
		String actOrigAd = testUtil.getTextOfElement(routeOrigin);
		actOrigAd = actOrigAd.trim();
		return actOrigAd;
	}

	public String getRouteDest() {
		logger.info("Get Destination Route Information");
		testUtil.init(this);
		String actDestAd = testUtil.getTextOfElement(routeDestination);
		actDestAd = actDestAd.trim();
		return actDestAd;
	}
	
	public void validatePagetitle(String expectedTitle) {
		logger.info("Validate page title");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		//String title = driver.findElement(By.xpath("//*[text()='LTL Rate Quote']")).getText();//Commented and added thebelow line
		String title = driver.findElement(By.xpath("//*[text()='LTL Rate Request']")).getText();
		
		System.out.println(title);
		Assert.assertTrue(title.contains(expectedTitle));
	}

	
	public String getFreightCharges() {
		logger.info("Click on Time Critical in Additional Options");
		testUtil.init(this);
		String charges=testUtil.getTextOfElement(EstimatedFreightCharges).replace("$", "").trim();
		return charges;
	}
	
	
	public void verifySelectedValue(String element, String value) {
		logger.info("Get Selected value from options");
		WebElement Ele = driver.findElement(By.id(element));
		Select s = new Select(Ele);
		WebElement option = s.getFirstSelectedOption();
		String SMCStatus = option.getText();
		Assert.assertEquals(SMCStatus, value);
	}
public void verifyEnteredValue(String element, String value) {
		logger.info("Verify entered value");
		WebElement Ele = driver.findElement(By.id(element));
		Assert.assertEquals(Ele.getAttribute("value"), value);
	}

public void clickOnTimeCritical() {
	logger.info("Click on Time Critical in Additional Options");
	testUtil.init(this);
	testUtil.WaitForTheElementClickable(TimeCritical);
	TimeCritical.click();
}

public void validateRoutingError(String message) {
	logger.info("Validate Routing Error message");
	testUtil.init(this);
	String mess=driver.findElement(By.xpath("//*[@class='tableCell wrappable exceptionMessage']")).getText();
	mess=mess.trim();
	System.out.println(mess);
	Assert.assertEquals(mess, message);
}

public void clickOnGenerateQuote() {
	logger.info("Click on Generate New quote");
	testUtil.init(this);
	GenerateQuote.click();
}

public String getServiceDays() {
	logger.info("Get service days");
	testUtil.init(this);
	String serviceDays = driver.findElement(By.xpath("//div[contains(text(),' Days')]")).getText();
	serviceDays = serviceDays.replace("Days", "days");
	return serviceDays;
	
}

public void verifyAddedWeightIsDisplayed() {
	logger.info("Verify Added weight to provide next lower rate: IsDisplayed in Commodities");
	testUtil.init(this);
	Assert.assertEquals(testUtil.isDisplayed(AddedWeight),true);
}

public void verifyAddedWeightValues(String name,String expVal) {
	logger.info("Verify Added Weight Required Value Of "+name);
	testUtil.init(this);
	switch(name)
	{
		case "Class":
		String cls=driver.findElement(By.xpath("//*[contains(text(),'Added weight')]/following-sibling::td[1]")).getText();
		Assert.assertEquals(cls, expVal);
		break;
		
		case "Weight":
			String wt=driver.findElement(By.xpath("//*[contains(text(),'Added weight')]/following-sibling::td[2]")).getText();
			Assert.assertEquals(wt, expVal);
			break;
			
		case "Rate":
			String rate=driver.findElement(By.xpath("//*[contains(text(),'Added weight')]/following-sibling::td[3]")).getText();
			Assert.assertEquals(rate, expVal);
			break;
			
		case "Charge":
			String chrg=driver.findElement(By.xpath("//*[contains(text(),'Added weight')]/following-sibling::td[4]")).getText();
			Assert.assertEquals(chrg, expVal);
			break;
			
		default:
			logger.info("Given Name Is Not Available In Commodities");
			break;
	}
}

public String getQuoteInfo(String name) {
	logger.info("Get Value of "+name+" ");
	testUtil.init(this);
	String quote=driver.findElement(By.xpath("//*[text()='"+name+"']/following::div[1]")).getText();
	System.out.println("The recorded quote is: "+ quote);
	return quote;
}

public String getRateFromCommodities(int row) {
	logger.info("Get Value of Rate from row Number "+row+" ");
	testUtil.init(this);
	String rate=driver.findElement(By.xpath("//*[@id='commodities_container']/table/tbody/tr["+row+"]/td[4]")).getText();
	return rate;
}

public String getQuoteSummaryValueOf(String name) {
	logger.info("Get Value of "+name+" from Quote summary");
	testUtil.init(this);
	String value=driver.findElement(By.xpath("//*[contains(text(),'"+name+"')]/following::td[1]")).getText();
	return value;
}

public void clickOnApplicationWithoutFrame() {
	logger.info("Click on Application tab");
	testUtil.init(this);
	driver.switchTo().defaultContent();
	testUtil.setHardWait(1000);
	Applications.click();
}


public void selectOptionReturnedWithRate() {
	logger.info("Verify rate displayed for ");
	String[] serviceLevel = { "guaranteed10", "guaranteed12", "guaranteed5", "ltlStandard"};
	
	
	for (int i = 0; i == 3 ; i++) {
		
		String value = driver.findElement(By.xpath("//*[@id='" + serviceLevel[i] + "']//span")).getText();
		System.out.println(value);
		
		if(value.contains("+$")) {
			logger.info("Option with price is displayed ");
			WebElement option = driver.findElement(By.xpath("//*[@id='" + serviceLevel[i] + "']//label"));
			
			String selectedOption = driver.findElement(By.xpath("//*[@id='" + serviceLevel[i] + "']//label")).getText();
			System.out.println(selectedOption);
			option.click();
			logger.info("Option is clicked");
		}	
		
	}

}


public void clickCreateBOLLink() {
	logger.info("Click on create BOL link");
	testUtil.init(this);
	createBOLLink.click();
}

public void enterBOLName(String name) {
	logger.info("Enter BOL Full Name");
	testUtil.init(this);
	BOLName.sendKeys(name);
	
}

public void enterBOLUserName(String name) {
	logger.info("Enter BOL Username");
	testUtil.init(this);
	BOLUsername.sendKeys(name);
	
}

public void enterBOLEmail(String name) {
	logger.info("Enter BOL Email");
	testUtil.init(this);
	BOLEmail.sendKeys(name);
	
}

public void enterBOLComments(String name) {
	logger.info("Enter BOL comments");
	testUtil.init(this);
	BOLComments.sendKeys(name);
	
}

public void clickOnBOLsubmit() {
	logger.info("Click on submit to generate BOL Link");
	testUtil.init(this);
	submitBOLlink.click();
	
}

public void clickOnClose() {
	logger.info("Click on Close me to close BOL");
	testUtil.init(this);
	testUtil.setHardWait(2000);
	closeBOLlink.click();
}


public void switchToFancyFrame() {
	driver.switchTo().frame(fancyFrame);
	logger.info("Switched to frame ");
	
}

public void verifySuccessMessage() {
	testUtil.init(this);
	logger.info("Verify Success Message in BOL popup ");
	String successMsg = successMessage.getText().trim();
	Assert.assertEquals(successMsg, "Thank you! Your request was successfully processed.");
	
}


public String getFuelCharge() {
	logger.info("Record Fuel Charges");
	testUtil.init(this);
	String charges=testUtil.getTextOfElement(FuelCharges).replace("$", "").trim();
	return charges;
	
}


public void clickSaveComments() {
	logger.info("Click on Save Comments");
	testUtil.init(this);
	saveComments.click();
	
}


public void clickSendToCallCenter() {
	logger.info("Click on Send To Call Center");
	testUtil.init(this);
	sendToCallCenter.click();
	
}

public void enterfullName(String value) {
	logger.info("Enter Full Name");
	testUtil.init(this);
	fullNameInSendTocallCenterPopup.sendKeys(value);
}

public void enterEmail(String value) {
	logger.info("Enter Email");
	testUtil.init(this);
	eMailInSendTocallCenterPopup.sendKeys(value);
	
}

public void enterPhone(String value) {
	logger.info("Enter Phone ");
	testUtil.init(this);
	phoneInSendTocallCenterPopup.sendKeys(value);
	
}

public void enterCommentsinSendToCallCenterPopup(String value) {
	logger.info("Enter Comments");
	testUtil.init(this);
	commentsInSendTocallCenterPopup.sendKeys(value);
	
}


public void clickOnSendToCallCenterSubmit() {
	logger.info("Click on submit to send to call center");
	testUtil.init(this);
	submitSentToCallCenter.click();
	
}
	
}
