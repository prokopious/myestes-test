package eNetPages;

import static org.testng.Assert.assertTrue;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class ENetQuoteHistoryLookupPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetQuoteHistoryLookupPage.class);

	@FindBy(id = "rate_quote_history_quote_number")
	private WebElement quoteNumber;

	@FindBy(xpath = "//*[@id='rate_quote_history_submit_container']/input")
	private WebElement searchBtn;

	@FindBy(css = ".output-table tbody tr:nth-child(1) a")
	private WebElement quoteNumberLink;

	@FindBy(xpath = "//*[@id='quote_history_intro_module']/h1")
	private WebElement pageTitle;

	@FindBy(xpath = "//*[@id='fromDate']")
	private WebElement fromDate;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/tbody/tr[1]/td[3]")
	private WebElement firstCompanyName;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/tbody")
	private WebElement quoteHistoryContainer;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[1]/a")
	private WebElement quoteCol;

	@FindBy(xpath = "//*[@id='logout']")
	private WebElement logout;

	@FindBy(xpath = "/html/body/form/table/tbody/tr[3]/td/input")
	private WebElement logoutConfirmation;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/tbody/tr/td[1]/a")
	private WebElement quoteResultLink;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[2]/a")
	private WebElement quoteTimeStampcol;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[3]/a")
	private WebElement companyNamecol;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[4]/a")
	private WebElement accountNumbercol;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[5]/a")
	private WebElement estChargescol;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[6]/a")
	private WebElement origincol;

	@FindBy(xpath = "//*[@id='quote_history_container']/table/thead/tr/th[7]/a")
	private WebElement userIdcol;

	@FindBy(xpath = "//*[@id= 'show_all_filters']")
	private WebElement showAllFiltersCheckbox;

	@FindBy(xpath = "//*[@id='accountNumber']")
	private WebElement accountNumber;

	@FindBy(how = How.ID, using = "svclvl")
	private WebElement selectService;

	@FindBy(xpath = "//*[@id='additional_filters']//a/img")
	private WebElement binoIcon;

	@FindBy(xpath = "/html/body/div[1]/div[1]")
	private WebElement accountSearchPopup;

	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div")
	private WebElement noReultsMessage;

	@FindBy(xpath = "//*[@id='tpTab0']/a")
	private WebElement accountNameTab;

	@FindBy(xpath = "//*[@id='tpTab1']/a")
	private WebElement addressTab;

	@FindBy(xpath = "//*[@id='nameSearch']/table/tbody/tr[2]/td[2]/input")
	private WebElement clickOnSubmitButtom;

	@FindBy(xpath = "//*[@id='accountName']")
	private WebElement accountName;

	@FindBy(xpath = "//*[@id='addressSearch']/table/tbody/tr[5]/td[2]/input")
	private WebElement clickOnSubmitButtonInAddressTab;

	@FindBy(xpath = "//*[@id='tpPanel1']/form/table/tbody/tr[1]/td[1]")
	private WebElement streetAddressFeild;

	@FindBy(xpath = "//*[@id='tpPanel1']/form/table/tbody/tr[2]/td[1]")
	private WebElement cityFeild;

	@FindBy(xpath = "//*[@id='tpPanel1']/form/table/tbody/tr[3]/td[1]")
	private WebElement stateFeild;

	@FindBy(xpath = "//*[@id='tpPanel1']/form/table/tbody/tr[4]/td[1]")
	private WebElement zipFeild;

	@FindBy(xpath = "//*[@id='streetAddress1']")
	private WebElement streetAddress;

	@FindBy(xpath = "//*[@id='city']")
	private WebElement city;

	@FindBy(xpath = "//*[@id='state']")
	private WebElement state;

	@FindBy(xpath = "//*[@id='zip']")
	private WebElement zip;

	@FindBy(xpath = "/html/body/div[2]/div/div/table/tbody/tr[2]/td[3]")
	private WebElement verifyStreetaddress;

	@FindBy(xpath = "/html/body/div[2]/div/div/table/tbody/tr[2]/td[4]")
	private WebElement verifyCity;

	@FindBy(xpath = "/html/body/div[2]/div/div/table/tbody/tr[2]/td[5]")
	private WebElement verifyState;

	@FindBy(xpath = "/html/body/div[2]/div/div/table/tbody/tr[2]/td[6]")
	private WebElement verifyZip;

	@FindBy(xpath = "/html/body/div[2]/div/div/table/tbody/tr[2]/td[2]/a")
	private WebElement accountNumberLink;

	@FindBy(xpath = "//*[@id='mainpage']")
	private WebElement mainPageFrame;

	@FindBy(xpath = "//*[@id='userId']")
	private WebElement userIDFeild;

	@FindBy(xpath = "//*[@id='additional_filters']/div[3]/div/div/div/div[1]/label")
	private WebElement verifySelectServiceLevel;

	@FindBy(xpath = "//*[@id='vtl_qds_container']/div[1]/fieldset[1]/div/div/div[1]/div[2]/b")
	private WebElement verifyQuoteNumber;

	@FindBy(xpath = "//*[@id='select_another_quote']")
	private WebElement clickSelectAnotherQuoteBtn;

	@FindBy(xpath = "//*[@id='quoteHistoryRequest']/div[1]/div[1]/input")
	private WebElement Collapsebtn;

	@FindBy(xpath = "//*[@id='popPrintButton']/img")
	private WebElement printIcon;

	@FindBy(xpath = "//*[@id='cityOrigin']")
	private WebElement originCity;

	@FindBy(xpath = "//*[@id='zipDestination']")
	private WebElement destZip;

	@FindBy(xpath = "//*[@id='clear_origin']")
	private WebElement clearOriginBtn;

	@FindBy(xpath = "//*[@id='clear_destination']")
	private WebElement clearDestinationBtn;

	@FindBy(xpath = "//*[@id='net_charges_min']")
	private WebElement MinESTChrgValue;

	@FindBy(xpath = "//*[@id='net_charges_max']")
	private WebElement MaxESTChrgValue;

	@FindBy(xpath = "//*[@id='quoteHistoryRequest']/div[1]/div[1]/input")
	private WebElement expandButton;

	@FindBy(xpath = "//*[@id='toDate']")
	private WebElement toDate;

	@FindBy(xpath = "//*[@id='quote_history_container']")
	private WebElement searchResultsGrid;

	@FindBy(id = "show_all_filters")
	private WebElement showFilters;

	@FindBy(tagName = "iframe")
	private List<WebElement> noOfIFrame;

	/****************************** METHODS **********************/

	public void enterQuoteNumber(String quoteNo) {
		logger.info("Enter Quote Number: " + quoteNo);
		testUtil.init(this);
		// WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		// driver.switchTo().frame(frame);
		testUtil.setExplicitWait(quoteNumber, 20);
		quoteNumber.clear();
		quoteNumber.sendKeys(quoteNo);
	}

	public void clickOnSearchButton() {
		logger.info("Click on Search button");
		testUtil.init(this);
		try {
			testUtil.setExplicitWait(searchBtn, 90);
			testUtil.clickElementJavascript(searchBtn);
			testUtil.setHardWait(2000);
		} catch (NoSuchElementException e) {
			e.getMessage();
		}

	}

	public void verifyQuoteNumber(String quoteNo) {
		logger.info("Verify Quote Number in Quote history lookup");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String quoteNum = quoteNumberLink.getText();
		Assert.assertEquals(quoteNum, quoteNo);
	}

	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify Page ");
		// driver.switchTo().frame("mainpage");
		if (noOfIFrame.size() > 0) {
			testUtil.switchToFrame(0);
		}

		testUtil.assetWait(null, pageTitle, 10, 250, TimeUnit.MILLISECONDS);
		String pageTtl = pageTitle.getText().trim();
		Assert.assertEquals(pageTtl, "Quote History", "Page Title does not match.");
	}

	public void enterFromDate() {
		testUtil.init(this);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date date = new Date();
		String todate = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);
		Date todate1 = cal.getTime();
		String fromdate = dateFormat.format(todate1);
		logger.info("From Date : " + fromdate);
		fromDate.click();
		fromDate.sendKeys(fromdate);
	}

	public void verifyResultOccurred() {
		testUtil.init(this);
		logger.info("Verify Search Results displayed or not?");
		String firstComName = firstCompanyName.getText();
		Assert.assertNotNull(firstComName, "Search Result not found.");
	}

	public void clickOnQuoteCol() {
		testUtil.init(this);
		logger.info("Click on Quote column.");
		quoteCol.click();
	}

	public void verifyQuoteHistoryColumnSortedAsc() {
		testUtil.init(this);
		ArrayList<String> quoteHistorystring = new ArrayList<String>();
		String quoteHistory;
		List<WebElement> quoteHistoryAsc = new ArrayList<WebElement>();
		quoteHistoryAsc = quoteHistoryContainer.findElements(By.tagName("tr"));
		for (int i = 0; i < quoteHistoryAsc.size(); i++) {
			quoteHistory = quoteHistoryAsc.get(i).findElement(By.xpath("td[1]/a")).getText();
			quoteHistorystring.add(quoteHistory);
			// logger.info(quoteHistory);
		}

		testUtil.checkStringListSorted(quoteHistorystring, "Quote History column list not sorted in ascending order.");
		logger.info("Quote History column list sorted in ascending order.");

	}

	public void verifyQuoteHistoryColumnSortedDsc() {
		testUtil.init(this);

		ArrayList<String> quoteHistorystring = new ArrayList<String>();
		String quoteHistory;
		List<WebElement> quoteHistoryAsc = new ArrayList<WebElement>();
		quoteHistoryAsc = quoteHistoryContainer.findElements(By.tagName("tr"));
		for (int i = 0; i < quoteHistoryAsc.size(); i++) {
			quoteHistory = quoteHistoryAsc.get(i).findElement(By.xpath("td[1]/a")).getText();
			quoteHistorystring.add(quoteHistory);
			// logger.info(quoteHistory);
		}

		testUtil.checkStringListReverseSorted(quoteHistorystring,
				"Quote History column list not sorted in descending order.");
		logger.info("Quote History column list sorted in descending order.");

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

	public void clickQuoteLink() {
		testUtil.init(this);
		logger.info("Click Quote link.");
		quoteResultLink.click();
	}

	public void verifyAdditinalFiltersisCollapsed() {
		logger.info("Verify Additional filters is not displayed");
		testUtil.init(this);
		Assert.assertFalse(driver
				.findElement(By.xpath("//*[@id= 'additional_filters']/legend[contains(text(),'Additional Filters')]"))
				.isDisplayed());
	}

	public void enterAccountNumber(String acctNum) {
		testUtil.init(this);
		logger.info("Enter Account Number");
		accountNumber.click();
		testUtil.setHardWait(3000);
		accountNumber.clear();
		accountNumber.sendKeys(acctNum);
		testUtil.setHardWait(3000);

	}

	public void clickOnSearchBtn() {
		testUtil.init(this);
		logger.info("Click on Search Button");
		searchBtn.click();
		testUtil.setHardWait(3000);

	}

	public void verifyAccountNumber(String acctNum) {
		testUtil.init(this);
		logger.info("Verify Account Numbers");
		String actual = driver.findElement(By.xpath("//*[@id='accountNumber']")).getAttribute("value");
		System.out.println("Account Number " + actual);
		Assert.assertEquals(actual, acctNum);

	}

	public void verifyAccountNumberExistence(String acctNum) {
		logger.info("Verify Account Number is displayed or not");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("//*[@id='quote_history_container']/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);
		for (int i = 0; i < rowCount; i++) {
			String rowValue = driver
					.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr[" + (i + 1) + "]/td[4]"))
					.getText();
			testUtil.setHardWait(1000);
			Assert.assertEquals(rowValue, acctNum, "Account Number Mismatch");
			logger.info("Account Number Matched" + acctNum);
		}

	}

	public void verifyColumnsDisplayedInQuoteHistoryResultsTable() {
		logger.info("Verify Columns are displayed in Quote Histoy/Results table");

		boolean quoteColumn = quoteCol.isDisplayed();
		assertTrue(quoteColumn);
		testUtil.setHardWait(500);
		logger.info("Quote# is displayed");

		boolean quoteTimestampcolumn = quoteTimeStampcol.isDisplayed();
		assertTrue(quoteTimestampcolumn);
		testUtil.setHardWait(500);
		logger.info("QuoteTimeStamp is displayed");

		boolean companyNamecolumn = companyNamecol.isDisplayed();
		assertTrue(companyNamecolumn);
		testUtil.setHardWait(500);
		logger.info("Company Name is displayed");

		boolean accountNumbercolumn = accountNumbercol.isDisplayed();
		assertTrue(accountNumbercolumn);
		testUtil.setHardWait(500);
		logger.info("Account Number is displayed");

		boolean estChargescolumn = estChargescol.isDisplayed();
		assertTrue(estChargescolumn);
		testUtil.setHardWait(500);
		logger.info("Est Charges col is displayed");

		boolean origincolumn = origincol.isDisplayed();
		assertTrue(origincolumn);
		testUtil.setHardWait(500);
		logger.info("Origin Column is displayed");

		boolean userIDcolumn = userIdcol.isDisplayed();
		assertTrue(userIDcolumn);
		testUtil.setHardWait(500);
		logger.info("User ID is displayed");

	}

	public void selectServiceLevel(String value) {
		testUtil.init(this);
		logger.info("select Term");
		testUtil.setExplicitWait(selectService, 60);
		testUtil.selectUsingVisibleText(selectService, value);

	}

	public void verifyQuotePrefixIsL(String prefixValue) {
		logger.info("Verify Quote Starts With Prefix Value");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("//*[@id='quote_history_container']/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);
		for (int i = 0; i < rowCount; i++) {
			String rowValue = driver
					.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr[" + (i + 1) + "]/td[1]"))
					.getText();
			testUtil.setHardWait(1000);
			String startValue = rowValue.substring(0, 1);
			testUtil.setHardWait(1000);
			Assert.assertEquals(prefixValue, startValue, "Quote does not start with prefix value");
			logger.info("Quote Starts with Prefix Value - " + startValue);
		}

	}

	public void clickOnShowAllFiltersCheckbox() {
		testUtil.init(this);
		logger.info("Click on show all filters checkbox");
		showAllFiltersCheckbox.click();
		testUtil.setHardWait(3000);
	}

	public void clickOnBinocularIcon() {
		testUtil.init(this);
		logger.info("Click on Binocular Icon");
		testUtil.assetWaitClickable(null, binoIcon, 10, 250, TimeUnit.MILLISECONDS);
		binoIcon.click();
		testUtil.setHardWait(3000);
	}

	public void verifyAccountSearchPopupDisplays() {
		logger.info("Verify accounts search popup is displayed");
		testUtil.setHardWait(3000);
		accountSearchPopup.isDisplayed();
		logger.info("Account Search popup is displayed");

	}

	public void verifyNoResultsMesageIsDisplayed() {
		testUtil.init(this);
		logger.info("Verify no results message is displayed");
		String successMsg = noReultsMessage.getText().trim();
		Assert.assertEquals(successMsg, "Your search yielded no results.");

	}

	public void verifyAccountNameTab() {
		logger.info("Verify Account Name tab is displayed");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		List<WebElement> f = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(f.get(1));
		testUtil.assetWaitDisplayed(accountNameTab, 10, 250, TimeUnit.MILLISECONDS);
		accountNameTab.isDisplayed();

	}

	public void verifyAddressTab() {
		logger.info("Verify Addresss displayed");
		testUtil.init(this);
		addressTab.isDisplayed();

	}

	public void clickOnSubmit() {
		testUtil.init(this);
		logger.info("Click on Binocular Icon");
		clickOnSubmitButtom.click();
		testUtil.setHardWait(3000);

	}

	public void enterAccoutName(String acctname) {
		testUtil.init(this);
		logger.info("Enter Account Number");
		accountName.click();
		testUtil.setHardWait(3000);
		accountName.clear();
		accountName.sendKeys(acctname);
		;
		testUtil.setHardWait(3000);

	}

	public void selectAccounNameTab() {
		logger.info("Verify Account Name tab is displayed");
		testUtil.init(this);
		accountNameTab.click();
		testUtil.setHardWait(3000);
	}

	public void verifyAdditinalFiltersExpanded() {
		logger.info("Verify Additional filters is displayed");
		testUtil.init(this);
		Assert.assertTrue(driver
				.findElement(By.xpath("//*[@id= 'additional_filters']/legend[contains(text(),'Additional Filters')]"))
				.isDisplayed());
	}

	public void clickOnAddressTab() {
		logger.info("Verify Address tab is displayed");
		testUtil.init(this);
		addressTab.click();
		testUtil.setHardWait(3000);
	}

	public void verifyExactResultsDisplayedforAccountName(String acctName) {
		logger.info("Verify Account Name is displayed or not");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("//*[@id='sizeDiv136579']/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);
		for (int i = 0; i < rowCount; i++) {
			String rowValue = driver.findElement(By.xpath("//*[@id='sizeDiv136579']/table/tbody/tr[\"+(i+1)+\"]/td[1]"))
					.getText();
			testUtil.setHardWait(1000);
			Assert.assertEquals(rowValue, acctName, "Account Name Mismatch");
			logger.info("Account Name  Matched" + acctName);
		}
	}

	public void verifyFeildsinaddressFeilds() {
		boolean streetfeild = streetAddressFeild.isDisplayed();
		assertTrue(streetfeild);
		testUtil.setHardWait(500);
		logger.info("Street Address feild is displayed");

		boolean cityfeild = cityFeild.isDisplayed();
		assertTrue(cityfeild);
		testUtil.setHardWait(500);
		logger.info("City feild is displayed");

		boolean statefeild = stateFeild.isDisplayed();
		assertTrue(statefeild);
		testUtil.setHardWait(500);
		logger.info("State  feild is displayed");

		boolean zipfeild = zipFeild.isDisplayed();
		assertTrue(zipfeild);
		testUtil.setHardWait(500);
		logger.info("Zip feild is displayed");

	}

	public void enterStreetAddress(String value) {
		testUtil.init(this);
		logger.info("Enter Street Address");
		streetAddress.click();
		testUtil.setHardWait(3000);
		streetAddress.clear();
		streetAddress.sendKeys(value);
		testUtil.setHardWait(3000);

	}

	public void enterCity(String value) {
		testUtil.init(this);
		logger.info("Enter City");
		city.click();
		testUtil.setHardWait(3000);
		city.clear();
		city.sendKeys(value);
		testUtil.setHardWait(3000);

	}

	public void enterState(String value) {
		testUtil.init(this);
		logger.info("Enter State");
		state.click();
		testUtil.setHardWait(3000);
		testUtil.selectUsingVisibleText(state, value);
		testUtil.setHardWait(3000);

	}

	public void enterZip(String value) {
		testUtil.init(this);
		logger.info("Enter Zip");
		zip.click();
		testUtil.setHardWait(3000);
		zip.clear();
		zip.sendKeys(value);
		testUtil.setHardWait(3000);
	}

	public void verifyResutsDisplayedforAddress(String streetaddress, String city, String state, String zip) {
		String actualstreetaddress = verifyStreetaddress.getText();
		testUtil.setHardWait(1000);
		Assert.assertEquals(actualstreetaddress, streetaddress);
		logger.info("Street Address  Matched" + streetaddress);

		String actualcity = verifyCity.getText();
		testUtil.setHardWait(1000);
		Assert.assertEquals(actualcity, city);
		logger.info("City Matched" + city);

		String actualstate = verifyState.getText();
		testUtil.setHardWait(1000);
		Assert.assertEquals(actualstate, state);
		logger.info("State Matched" + state);

		String actualzip = verifyZip.getText();
		testUtil.setHardWait(1000);
		Assert.assertEquals(actualzip, zip);
		logger.info("Zip Matched" + zip);

	}

	public void clickonSubmitButtonInAddressTab() {
		testUtil.init(this);
		logger.info("Click on Submit Button In Address Tab");
		clickOnSubmitButtonInAddressTab.click();
		testUtil.setHardWait(3000);

	}

	public void switchToMainFrame() {
		testUtil.init(this);
		testUtil.setHardWait(2000);
		logger.info("Switch to Main page Frame.");
		driver.switchTo().frame(mainPageFrame);
	}

	public String selectAccountNumberLink() {
		testUtil.init(this);
		logger.info("Record Account Number");
		String actualAcctNumber = accountNumberLink.getText();
		testUtil.setHardWait(1000);

		logger.info("Click Account Number link.");
		accountNumberLink.click();
		return actualAcctNumber;
	}

	public void verifySelectServiceLevelFeild() {
		boolean servicefeild = verifySelectServiceLevel.isDisplayed();
		assertTrue(servicefeild);
		testUtil.setHardWait(500);
		logger.info("Select Service feild is displayed");

	}

	public void validateDropDownOptionsInSeletedServiceLevel(List<String> flagList) {
		List<String> actualList = new ArrayList<String>();
		WebElement ele = driver.findElement(By.xpath("//*[@id='svclvl']"));
		ele.click();
		testUtil.setHardWait(500);
		Select selectEle = new Select(ele);
		List<WebElement> selectService = new ArrayList<WebElement>();

		selectService = selectEle.getOptions();
		for (int i = 0; i < selectService.size(); i++) {

			String data = selectService.get(i).getText();
			actualList.add(data);
			logger.info("Actual Data list" + data);

		}
		for (int j = 0; j < flagList.size(); j++) {
			if (actualList.contains(flagList.get(j))) {
				logger.info("Found " + flagList.get(j));
			} else {
				Assert.fail("Not found " + flagList.get(j));
			}
		}

		logger.info("Servives are Present");
	}

	public void verifyAccountNumberPreFilled(String recordAccountNumber)
			throws UnsupportedFlavorException, IOException {
		testUtil.init(this);
		logger.info("Verify Account Numbers");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		testUtil.assetWaitDisplayed(driver.findElement(By.xpath("//*[@id='accountNumber']")), 10, 250,
				TimeUnit.MILLISECONDS);
		WebElement actual = driver.findElement(By.xpath("//*[@id='accountNumber']"));
		String actualAccountNumber = testUtil.copyStringFromInputBox(actual);
		System.out.println("Account Number " + actualAccountNumber);
		Assert.assertEquals(actualAccountNumber.trim(), recordAccountNumber.trim());

	}

	public String recordQuoteNumber() {
		testUtil.init(this);
		logger.info("Click Quote link.");
		String quoteNos = quoteResultLink.getText();
		return quoteNos;
	}

	public void clearQuoteNumberFeild() {
		testUtil.init(this);
		logger.info("Clear the  Quote Number");

		testUtil.setExplicitWait(quoteNumber, 20);
		quoteNumber.clear();

	}

	public void selectAccountNumberLink(String value) {
		logger.info("Select the given account number link");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("/html/body/div[2]/div[1]/div/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);

		for (int i = 1; i < rowCount; i++) {
			WebElement actual = driver
					.findElement(By.xpath("/html/body/div[2]/div[1]/div/table/tbody/tr[" + (i + 1) + "]/td[2]/a"));
			String rowValue = actual.getText();
			logger.info(rowValue);
			testUtil.setHardWait(10000);
			if (rowValue.equals(value)) {
				actual.click();
				logger.info("Account Number Selected" + rowValue);
				break;
			}
		}
	}

	public void selectAccountNumberThatQuoteFits(String value) {
		logger.info("Select the given account number link");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("/html/body/div[2]/div[1]/div/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);

		for (int i = 1; i < rowCount; i++) {
			WebElement actual = driver
					.findElement(By.xpath("/html/body/div[2]/div[1]/div/table/tbody/tr[" + (i + 1) + "]/td[2]/a"));
			String rowValue = actual.getText();
			logger.info(rowValue);
			testUtil.setHardWait(10000);
			if (rowValue.equals(value)) {
				actual.click();
				logger.info("Account Number Selected" + rowValue);
				break;
			}
		}
	}

	public void veifyMessage() {
		String actual = driver.findElement(By.xpath("//*[@id='quoteHistoryRequest']/span/b")).getText();
		logger.info(actual);
		Assert.assertEquals(actual, "0", "Expected Quotes is not available");

	}

	public void verifyUserFeild() {
		boolean userFeild = userIDFeild.isDisplayed();
		assertTrue(userFeild);
		testUtil.setHardWait(500);
		logger.info("User feild is displayed");
	}

	public void enteruserfeild(String value) {
		logger.info("Enter value in User feild: " + value);
		testUtil.init(this);
		testUtil.setExplicitWait(userIDFeild, 20);
		userIDFeild.clear();
		userIDFeild.sendKeys(value);
	}

	public void verifyUsrIDExistence(String value) {
		logger.info("Verify User ID is displayed or not");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("//*[@id='quote_history_container']/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);
		for (int i = 0; i < rowCount; i++) {
			String rowValue = driver
					.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr[" + (i + 1) + "]/td[7]"))
					.getText();
			testUtil.setHardWait(1000);
			Assert.assertEquals(rowValue, value, "User ID Mismatch");
			logger.info("User ID Matched" + value);
		}
	}

	public void verifyQuotenumberMatches(String quoteNos) {
		String actualQuoteNumber = verifyQuoteNumber.getText();
		testUtil.setHardWait(1000);
		Assert.assertEquals(actualQuoteNumber, quoteNos);
		logger.info("Quote Number  Matched" + quoteNos);

	}

	public void clickOnSelectAnotherQuoteButton() {
		testUtil.init(this);
		logger.info("Click on Select Another Quote button to come back");
		clickSelectAnotherQuoteBtn.click();

	}

	public ArrayList<String> captureResultTable() {
		logger.info("Verify Table");
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@id='quote_history_container']/table/tbody/tr"));
		System.out.println(rowCount.size());
		ArrayList<String> quoteNumber = new ArrayList<String>();

		for (int i = 1; i < rowCount.size(); i = i + 2) {
			quoteNumber.add(
					driver.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr['+ i +']/td[1]"))
							.getText());
			logger.info(quoteNumber);
		}
		return quoteNumber;
	}

	public void clearAccountNumberfeild() {
		testUtil.init(this);
		logger.info("Clear the Account Number feild");
		accountNumber.click();
		testUtil.setHardWait(1000);
		accountNumber.clear();
		testUtil.setHardWait(3000);

	}

	public void enterOriginCity(String oCity) {
		testUtil.init(this);
		logger.info("enter Origin city ");
		originCity.sendKeys(oCity);
		testUtil.setHardWait(500);
	}

	public void enterDestinationZip(String dZip) {
		testUtil.init(this);
		logger.info("enter Destination Zip ");
		destZip.sendKeys(dZip);
		testUtil.setHardWait(500);
	}

	public void selectOriginCity(String oCity) {
		logger.info("Select Origin City ");
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//a[contains(text(),'" + oCity + "')]")).click();
	}

	public void selectDestinationZip(String dZip) {
		logger.info("Select Destination Zip ");
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//a[contains(text(),'" + dZip + "')]")).click();
	}

	public void clickOnClearButtonInOriginCity() {
		testUtil.init(this);
		logger.info("Click On Origin Clear Button");
		clearOriginBtn.clear();
		testUtil.setHardWait(3000);

	}

	public void clickOnClearButtonInDestinationCity() {
		testUtil.init(this);
		logger.info("Click On Destination Clear Button");
		clearDestinationBtn.clear();
		testUtil.setHardWait(3000);
	}

	public void enterESTCharges(String minESTChrg, String maxESTChrg) {
		testUtil.init(this);
		logger.info("enter minium EST Charges ");
		MinESTChrgValue.sendKeys(minESTChrg);
		testUtil.setHardWait(500);
		logger.info("enter maximum EST Charges ");
		MaxESTChrgValue.sendKeys(maxESTChrg);
		testUtil.setHardWait(500);

	}

	public ArrayList<String> captureQuotesResultTable() {

		logger.info("Verify Table");
		testUtil.init(this);
		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("//*[@id='quote_history_container']/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);
		ArrayList<String> quoteNumber = new ArrayList<String>();

		for (int i = 0; i < rowCount; i++) {
			quoteNumber.add(driver
					.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr[" + (i + 1) + "]/td[1]"))
					.getText());
			logger.info(quoteNumber);
		}
		return quoteNumber;
	}

	public void verifyQuotePrefixValue(String prefixValue) {
		logger.info("Verify Quote Starts With Prefix Value");
		testUtil.init(this);

		List<WebElement> row = new ArrayList<WebElement>();
		row = driver.findElements(By.xpath("//*[@id='quote_history_container']/table/tbody/tr"));

		int rowCount = row.size();
		logger.info(rowCount);
		testUtil.setHardWait(1000);
		for (int i = 0; i < rowCount; i++) {
			String rowValue = driver
					.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr[" + (i + 1) + "]/td[1]"))
					.getText();
			testUtil.setHardWait(1000);
			String startValue = rowValue.substring(0, 1);
			testUtil.setHardWait(1000);
			Assert.assertEquals(prefixValue, startValue, "Quote does not start with prefix value");
			logger.info("Quote Starts with Prefix Value - " + startValue);
		}

	}

	public void verifySerachCriteriaFeilds() {
		logger.info("Verify Serach Criteria feilds are displayed");

		boolean quoteNosFeild = quoteNumber.isDisplayed();
		assertTrue(quoteNosFeild);
		testUtil.setHardWait(500);
		logger.info("Quote Number Feild is displayed");

		boolean fromDateFeild = fromDate.isDisplayed();
		assertTrue(fromDateFeild);
		testUtil.setHardWait(500);
		logger.info("From Date Feild is displayed");

		boolean toDateFeild = toDate.isDisplayed();
		assertTrue(toDateFeild);
		testUtil.setHardWait(500);
		logger.info("To Date Feild is displayed");
	}

	public void verifysearchResultsGrid() {
		logger.info("Verify Serach Results Grid are displayed");

		boolean searchGrid = searchResultsGrid.isDisplayed();
		assertTrue(searchGrid);
		testUtil.setHardWait(500);
		logger.info(" Serach Results Grid is displayed");

	}

	public void clickOnShowFilters() {
		logger.info("Click on Show Filters option");
		testUtil.init(this);
		testUtil.setExplicitWait(showFilters, 20);
		Assert.assertEquals(showFilters.isSelected(), false);
		showFilters.click();
	}

	public void clickOnQuoteNumber(String quote) {
		logger.info("Click on quote number");
		testUtil.init(this);

		// WebElement iframe = driver.findElement(By.id("mainpage"));
		// driver.switchTo().frame(iframe);
		// driver.switchTo().frame("mainpage");

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		// wait.until(ExpectedConditions.visibilityOf(
		// driver.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr/td[1]/a"))));

		boolean flag = false;

		int counter = 0;

		do {

			WebElement ele = driver.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr/td[1]/a"));
			String str = ele.getText();
			testUtil.setHardWait(1000);

			if (str.equals(quote)) {
				System.out.println(str);
				ele.click();
				// WebElement
				// clickBtn=driver.findElement(By.xpath("//*[@id='quote_history_container']/table/tbody/tr/td[1]/a"));

				testUtil.clickElementJavascript(ele);
				flag = true;
				break;
			}
			// else if (!str.equals(quote))

			// for (int i=0;i < 10; i++)
			// driver.navigate().refresh();
			// if(str.equals(quote)) {

			counter++;
		} while (counter < 15);

	}

	public void enterQuoteNum(String quoteNumber) {
		testUtil.init(this);
		WebElement quoteNumInput = testUtil.filterSelector("input", null, null, 0, "rate_quote_history_quote_number");
		quoteNumInput.sendKeys(quoteNumber);
	}

	public void clickSearchButton() {
		testUtil.init(this);
		WebElement searchButton = testUtil.filterSelector("input", null, null, 0, "type=\"submit\"", "Search");
		testUtil.clickElementJavascript(searchButton);
	}

	public void clickQuoteNum(String quoteNum) {
		testUtil.init(this);
		WebElement quoteNumber = testUtil.filterSelector("a", null, null, 0, quoteNum);
		testUtil.clickElementJavascript(quoteNumber);
	}

	public void clickSearchAndClickQuoteNum(String quoteNum, int numberOfAttempts) throws Exception {

		for (int i = 0; i <= numberOfAttempts; i++) {

			clickSearchButton();
			//first we get the quote history container, which contains dynamic elements (quote number links, etc.)
			WebElement quoteHistoryContainer = testUtil.filterSelector("div", null, null, 1, "quote_history_container", "!Show All Filters");
			//to the next method we pass in the history container as a parent element, which is the starting point to find the quote num <a> link.
			WebElement quoteNumElement = testUtil.filterXpathTimout(".//a", null, quoteHistoryContainer, 0, 10000, quoteNum);
			
			if (quoteNumElement == null && i < numberOfAttempts) {
				System.out.println("Attempt " + i + " unsuccessful");
			} else if (quoteNumElement == null && i >= numberOfAttempts) {
				throw new Exception("Failed to find element");
			} else {
				System.out.println("Element found: " + quoteNumElement.getText());
				testUtil.clickElementJavascript(quoteNumElement);
				break;
			}
		}
	}

}
