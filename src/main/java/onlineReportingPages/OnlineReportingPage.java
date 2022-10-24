package onlineReportingPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class OnlineReportingPage extends TestBase {

	private Logger logger = Logger.getLogger(OnlineReportingPage.class);

	@FindBy(xpath = "//*[@id=\"mat-tab-label-0-1\"]/div[contains(text(),'Create a Report')]")
	private WebElement createReportTab;

	@FindBy(xpath = "//*[@id=\"reportSelection\"]/div/div[2]")
	private WebElement ReportDdown;

	@FindBy(xpath = "//*[@id='reportName']")
	private WebElement enterText;

	@FindBy(xpath = "//*[@id='scheduleReport']")
	private WebElement scheduleReportBtn;

	@FindBy(xpath = "//*[@id=\"saveSchedule\"]")
	private WebElement submitBtn;

	@FindBy(xpath = "//*[@id=\"seeScheduleDetails\"]")
	private WebElement detailsLink;

	@FindBy(xpath = "//*[@id=\"cancelSchedule\"]")
	private WebElement cancelBtn;

	@FindBy(xpath = "//*[@id=\"saveReport\"]")
	private WebElement saveReportBtn;

	@FindBy(xpath = "//*[@id='mat-tab-content-0-0']/div/div/mat-card[1]/mat-card-content/div/mat-table/mat-row[*]")
	private List<WebElement> accountTable;

	@FindBy(xpath = "//button[@id='deleteReportCancel']")
	private WebElement cancelButton;

	@FindBy(id = "removeSchedule")
	private WebElement scheduleLink;

	@FindBy(xpath = "//label[contains(text(),'Schedule Details:')]")
	private WebElement details;

	@FindBy(id = "removeScheduleButton")
	private WebElement removeSchedule;

	@FindBy(xpath = "//span[contains(text(),'Copy Report')]")
	private WebElement copyReportTab;

	@FindBy(xpath = "//span[contains(text(),'Fields')]")
	private WebElement fields;

	@FindBy(xpath = "//span[contains(text(),'Select All')]")
	private WebElement selectAll;

	@FindBy(xpath = "//button[contains(text(),'Apply')]")
	private WebElement applyBtn;

	@FindBy(id = "resetReport")
	private WebElement resetBtn;

	@FindBy(xpath = "//*[contains(text(),'Pro #')]//*[@title='Click to sort Asc']")
	private WebElement proSort;

	@FindBy(id = "downloadReport")
	private WebElement downloadReport;

	@FindBy(id = "downloadButton")
	private WebElement downloadBtn;

	@FindBy(xpath = "//mat-radio-group[@role='radiogroup']")
	private WebElement radioGroup;

	@FindBy(xpath = "//mat-radio-button[@id='oneAccount']")
	private WebElement oneAccount;

	@FindBy(xpath = "//a[contains(text(),'Account Search')]")
	private WebElement accountSearch;

	@FindBy(xpath = "//*[contains(text(),'Success!')]")
	private WebElement successMsg;

	@FindBy(xpath = "//div[contains(text(),'My Reports')]")
	private WebElement myReport;

	@FindBy(xpath = "//button[@id='dontSave']")
	private WebElement dontSave;

	/********************** METHODS **********************/

	public void clickOnCreateReportTab() {
		logger.info("Click on Create a Report tab");
		testUtil.init(this);
		// testUtil.setExplicitWait(createReportTab, 60);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//*[@id='mat-tab-label-0-1']/div[contains(text(),'Create a Report')]")));

		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(createReportTab);
		testUtil.setHardWait(2000);
	}

	public void selectOnReportDropDown() {
		logger.info("Select on Report Drop Down");
		testUtil.init(this);
		testUtil.setExplicitWait(ReportDdown, 60);
		testUtil.clickElementJavascript(ReportDdown);
		testUtil.setHardWait(3000);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"1\"]/span"));
		testUtil.clickElementJavascript(ele);
		testUtil.setHardWait(2000);
	}

//	public void enterValueInTextBox(String value) {
//		logger.info("Enter value in the text box");
//		testUtil.init(this);
//		testUtil.setExplicitWait(enterText, 120);
//		enterText.sendKeys(value);
//		testUtil.setHardWait(2000);
//	}

//	public void clickOnSchduleReport() {
//		logger.info("Click on schedule report");
//		testUtil.init(this);
//		testUtil.setExplicitWait(scheduleReportBtn, 90);
//		testUtil.clickElementJavascript(scheduleReportBtn);
//		testUtil.setHardWait(2000);
//	}

	  public void clickOnSchduleReport() {
	        logger.info("Click on schedule report");
	        testUtil.init(this);
	        WebElement scheduleReportButton = testUtil.pollDOM(null, scheduleReportBtn, 20);
	        testUtil.clickElementJavascript(scheduleReportButton);
	        testUtil.setHardWait(2000);
	    }
	
	
	public void enterEmailAddressOnTheReport(String email) {
		logger.info("Enter email address");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"emailAddresses\"]"));
		testUtil.setExplicitWait(ele, 60);
		ele.sendKeys(email);
		testUtil.setHardWait(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		testUtil.setHardWait(2000);

	}

	public void selectReportFormat() {
		logger.info("Select Report format");
		testUtil.init(this);
		WebElement eleDD = driver.findElement(By.xpath("//mat-select[@id='reportFormatCode']"));
		testUtil.clickElementJavascript(eleDD);
		testUtil.setHardWait(2000);
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Text/CSV File (*.csv)')]"));
		testUtil.clickElementJavascript(ele);
		testUtil.setHardWait(2000);
	}

	public void clickOnSubmitButton() {
		logger.info("Click on submit button");
		testUtil.init(this);

		testUtil.setExplicitWait(submitBtn, 120);
		// WebDriverWait wait = new WebDriverWait(driver, 60);
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='saveSchedule']")));
		testUtil.clickElementJavascript(submitBtn);
		testUtil.setHardWait(2000);

	}

	public void clickOnSeeDetailsLink() {
		logger.info("Click on see details link");
		testUtil.init(this);
		testUtil.setExplicitWait(detailsLink, 120);
		testUtil.clickElementJavascript(detailsLink);
	}

	public void verifyDataOnScheduleReport() {

		logger.info("Verify data entered in previous step");
		testUtil.init(this);
		// verifying frequency as "Daily"
		boolean frequency = driver.findElement(By.xpath("//*[@id=\"frequency\"]/div/div[1]")).isDisplayed();
		System.out.println("Verify frequency is displayed as Daily");
		Assert.assertEquals(frequency, true);
		// verify report duration checkbox
		testUtil.setHardWait(2000);
		boolean duration = driver.findElement(By.xpath("//*[@id=\"durationOnce\"]/label/div[1]/div[1]")).isDisplayed();
		System.out.println("Verify report duration checkbox is displayed as \"Send once\"");
		Assert.assertEquals(duration, true);

		testUtil.setHardWait(2000);
		boolean email = driver.findElement(By.xpath("//*[@id='emailAddresses']")).isDisplayed();
		System.out.println("Verify email address field");
		Assert.assertEquals(email, true);

		testUtil.setHardWait(2000);

		boolean reportFormat = driver.findElement(By.xpath("//*[@id=\"reportFormatCode\"]/div/div[1]")).isDisplayed();
		System.out.println("Verify Report format is displyed");
		Assert.assertEquals(reportFormat, true);

		testUtil.setHardWait(2000);
	}

	public void clickOnCancelButton() {
		logger.info("Click on cancel button");
		testUtil.init(this);
		testUtil.setExplicitWait(cancelBtn, 60);
		cancelBtn.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnSaveReportButton() {
		logger.info("Click on save report button");
		testUtil.init(this);
		testUtil.setExplicitWait(saveReportBtn, 120);
		testUtil.clickElementJavascript(saveReportBtn);
		testUtil.setHardWait(1000);
	}

	public void verifyReportUnderActiveReports(String report) {
		logger.info("Verify the new created report and delete if displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		logger.info("Searching account" + report + "....");

		List<WebElement> acc = driver.findElements(By.xpath(
				"//*[@id='mat-tab-content-0-0']/div/div/mat-card[1]/mat-card-content/div/mat-table/mat-row[*]/mat-cell[1]/span"));

		for (int i = 0; i < acc.size(); i++) {

			String reportName = acc.get(i).getText();
			testUtil.setHardWait(1000);

			if (reportName.contains(report)) {

				System.out.println("The report name exist!!" + report);
				break;
			} else {
				System.out.println("The report name does not exist!! " + report);
			}

		}

	}

	public void clickOnDeleteButton(String reportNme) throws InterruptedException {
		logger.info("Delete the added report : " + reportNme);
		testUtil.setHardWait(3000);

		WebElement ele = driver.findElement(
				By.xpath("//mat-card[1]/mat-card-content/div/mat-table/mat-row/mat-cell[5]/button[4]/span/mat-icon"));
		testUtil.setExplicitWait(ele, 90);
		testUtil.clickElementJavascript(ele);

		testUtil.setHardWait(2000);

		WebElement ele1 = driver.findElement(By.xpath("//button[@id='deleteReportButton']"));
		testUtil.clickElementJavascript(ele1);
		testUtil.setHardWait(1000);
	}

	public void deleteExpiredReports() throws InterruptedException {
		logger.info("Delete the Expired report");
		testUtil.setHardWait(1000);

		WebElement ele = driver.findElement(
				By.xpath("//mat-card[2]/mat-card-content/div/mat-table/mat-row/mat-cell[5]/button[4]/span/mat-icon"));
		testUtil.setExplicitWait(ele, 60);
		testUtil.clickElementJavascript(ele);
		testUtil.setHardWait(2000);

	}

	public void verifyReportIsNotDisplayed(String report) {
		logger.info("Verify the new created report and delete if displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		logger.info("Searching account" + report + "....");

		List<WebElement> acc = driver.findElements(By.xpath(
				"//*[@id='mat-tab-content-0-0']/div/div/mat-card[1]/mat-card-content/div/mat-table/mat-row[*]/mat-cell[1]/span"));

		for (int i = 0; i < acc.size(); i++) {

			String reportName = acc.get(i).getText();
			testUtil.setHardWait(1000);
			//changed contains to equal
			if (!reportName.equals(report)) {

				System.out.println("The report name does not exist!!" + report);
				assertTrue(true);
				break;
			} else {
				fail(report + ": report is Displayed");
			}

		}

	}

	public void clickOnCancelBtn() throws InterruptedException {
		logger.info("Click on cancel button");
		testUtil.setHardWait(1000);
		cancelButton.click();
		testUtil.setHardWait(1000);
	}


	public void clickOnRemoveScheduleLink() throws InterruptedException {
		logger.info("Click on Remove Schedule Link");
		testUtil.setHardWait(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		// scheduleLink.click();
		testUtil.clickElementJavascript(scheduleLink);
		testUtil.setHardWait(2000);
	}

	public void clickOnRemoveScheduleButton() throws InterruptedException {
		logger.info("Click on Remove Schedule button");
		testUtil.setExplicitWait(removeSchedule, 60);
		testUtil.clickElementJavascript(removeSchedule);
		testUtil.setHardWait(1000);
	}

	public void verifyScheduleDetailsNotDisplayed() {
		logger.info("Verify schedule details is no longer displayed");
		testUtil.init(this);
		testUtil.setHardWait(7000);
		boolean exist = testUtil.isDisplayed(details);
		assertFalse(exist);
	}

	public void verifyCopyReportTabIsDisplayed() {
		logger.info("Verify copy report tab is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		copyReportTab.isDisplayed();
		testUtil.setHardWait(1000);
	}

	public void verifyReportHasCopyAppended(String reportName) {
		logger.info("Verify report name has copy appended to it");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String actualName = (String) js.executeScript("return document.getElementById('reportName').value");
		System.out.println("The value is: " + actualName);
		Assert.assertEquals(actualName, reportName);
	

	}


	public void dragSecondElementToTop() {
		// this takes one element (drag element) and moves it into the position above
		// the drop element, which is the first element
		logger.info("Drag and drop the second element to the top ");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		Actions act = new Actions(driver);

		WebElement drag = driver.findElement(By.xpath("//ul/li[2]/span[2]"));
		testUtil.setExplicitWait(drag, 60);
		// find element which we need to drop
		WebElement drop = driver.findElement(By.xpath("//ul/li[1]/span[2]"));
		testUtil.setExplicitWait(drop, 60);
		// this will drag element to destination
		act.dragAndDrop(drag, drop).build().perform();
		testUtil.setHardWait(2000);
	}

	public void clickOnFieldsIcon() {
		logger.info("Click on fields icon");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-300)");
		fields.click();
		testUtil.setHardWait(1000);
	}

	public void verifyTheOrderOfColumns(String expMsg) {
		logger.info("Verify the columns order");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div[2]/div[1]"))
				.getText();
		System.out.println(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void selectFirstTenColumnsOnList() {
		logger.info("Select the first 10 columns on the list");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		List<WebElement> listofItems = driver
				.findElements(By.xpath("//*[@id='fm-pivot-view']/div[3]/div/div[2]/div[1]/div[2]/ul/li[*]/span[1]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);

		for (int i = 1; i <= 10; i++) {

			listofItems = driver.findElements(
					By.xpath("//*[@id='fm-pivot-view']/div[3]/div/div[2]/div[1]/div[2]/ul/li[*]/span[1]"));

			wait.until(ExpectedConditions.visibilityOf(listofItems.get(i - 1)));

			// Clicking on the first element
			listofItems.get(i - 1).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.print(i + " element clicked\t--");
			System.out.println("pass");

		}
	}

	public void unselectSelectAll() {
		logger.info("Unselect select all checkbox");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		selectAll.click();

	}

	public void clickOnApplyButton() {
		logger.info("Click on apply button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		applyBtn.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnResetButton() {
		logger.info("Click on reset button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		resetBtn.click();
		testUtil.setHardWait(2000);
	}


	//this returns the text inside the leftmost header in the formatted table
	public String returnLeftmostColumnHeader() {
		logger.info("Verify original layout");
		testUtil.init(this);
		testUtil.setHardWait(700);
		WebElement leftmostHeader = driver.findElement(By.cssSelector("*[data-c='0'][class*='draggable']"));
		testUtil.setExplicitWait(leftmostHeader, 20);
		System.out.println("value of leftmost header in table: " + leftmostHeader.getText());
		return leftmostHeader.getText();
	}

	// this is the same method as above but optimized method 
	
	public void clickLeftmostHeaderToSort1() {
        logger.info("Rename the report");
        testUtil.init(this);
        testUtil.setHardWait(700);
        WebElement dataGrid = testUtil.filterSelector("fm-pivot", null, null, 1);
        WebElement leftmostHeader = testUtil.filterSelector("*", null, dataGrid, 2, "data-c=\"0\"");
        testUtil.clickElementJavascript(leftmostHeader);
        testUtil.setHardWait(700);
    }
	public void verifyLayoutOrderChanged(String expected) {
		logger.info("Verify layout order changed");
		testUtil.init(this);
		String actual = returnLeftmostColumnHeader();
		System.out.println("The value is: " + actual);
		Assert.assertEquals(actual, expected);
		testUtil.setHardWait(700);
	}

	public void renameTheReport(String newReportName) {
		logger.info("Rename the report");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(enterText, 60);
		enterText.clear();
		enterText.sendKeys(newReportName);
		testUtil.setHardWait(1000);
	}

//	public void clickTheArrowForPro() {
//		logger.info("Rename the report");
//		testUtil.init(this);
//		testUtil.setHardWait(2000);
//		proSort.click();
//		testUtil.setHardWait(1000);
//	}
	
	

	//the leftmost table header, when clicked, sorts the column.
	public void clickLeftmostHeaderToSort() {
		logger.info("Rename the report");
		testUtil.init(this);
		testUtil.setHardWait(700);
		WebElement dataGrid = testUtil.filterSelector("fm-pivot", null, null, 1);
		WebElement leftmostHeader = testUtil.filterSelector("*", null, dataGrid, 2, "data-c=\"0\"");
		testUtil.clickElementJavascript(leftmostHeader);
	}

	public void deleteReportIfExistInTheTable(String reportName) {
		logger.info("Delete report number if it  already exists in the table");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		List<WebElement> we = driver.findElements(By.xpath(
				"//*[@id='mat-tab-content-0-0']/div/div/mat-card[1]/mat-card-content/div/mat-table/mat-row[*]/mat-cell[1]/span"));
		int listsize = we.size();

		System.out.println("Element size : " + listsize);

		for (int i = 0; i < listsize; i++) {

			String eleName = we.get(i).getText();
			System.out.println("Name of element is : " + eleName);
			System.out.println(i);

			if (eleName.equals(reportName)) {

				testUtil.setHardWait(1000);
				driver.findElement(By.xpath(
						"//mat-card[1]/mat-card-content/div/mat-table/mat-row[1]/mat-cell[5]/button[4]/span/mat-icon"))
						.click();
				testUtil.setHardWait(1000);

				driver.findElement(By.xpath("//button[@id='deleteReportButton']")).click();
				testUtil.setHardWait(1000);

				break;
			}
		}
	}

	public void clickOnDownloadReportButton() {
		logger.info("Click on download report button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		// downloadReport.click();
		testUtil.assetWaitClickable(null, downloadReport, 10, 200, TimeUnit.MILLISECONDS);

		testUtil.clickElementJavascript(downloadReport);
		// downloadReport.click();
		testUtil.setHardWait(1000);
	}

	public void selectReportFormatRadioBtn(String format) {
		WebElement buttonBox = testUtil.filterSelector("mat-radio-button", null, null, 1, format);
		WebElement formatButton = testUtil.filterSelector("input", null, buttonBox, 0);
		testUtil.clickElementJavascript(formatButton);
	}


	public void verifyReportIsDisplayed(String report) {
		logger.info("Verify the new created report is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		logger.info("Searching account" + report + "....");

		List<WebElement> acc = driver.findElements(By.xpath(
				"//*[@id='mat-tab-content-0-0']/div/div/mat-card[1]/mat-card-content/div/mat-table/mat-row[*]/mat-cell[1]/span"));

		for (int i = 0; i < acc.size(); i++) {

			String reportName = acc.get(i).getText();
			testUtil.setHardWait(1000);

			if (reportName.contains(report)) {

				System.out.println("The report name exist!!" + report);
				assertTrue(true);
				break;
			} else {
				fail(report + ": report is not Displayed");
			}

		}

	}
	
	public void clickDownloadIcon(String reportNme) throws InterruptedException {
		logger.info("Edit the added report : " + reportNme);
		testUtil.init(this);
	//Element was taking its sweet time to appear, so I added 10 seconds.
		testUtil.setHardWait(7000);
		 JavascriptExecutor js = (JavascriptExecutor) driver; 
	     js.executeScript("window.scrollBy(0,300)");
		WebElement ele = driver.findElement(
				By.xpath("//*[text()='" + reportNme + "']/parent::mat-cell/following-sibling::mat-cell[4]/button[1]"));
		testUtil.assetWait(null, ele, 70, 700, TimeUnit.MILLISECONDS);
		ele.click();
		testUtil.setHardWait(7000);
	}

	public void verifyReportDropDownDisplayed() {
		logger.info("verify select a report drop down is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(ReportDdown, 60);
		ReportDdown.isDisplayed();
	}

	public void verifyRadioButtonsAreDisplayed() {
		logger.info("verify radio buttons are displayed to select a report");
		testUtil.init(this);
		testUtil.setExplicitWait(radioGroup, 60);
		radioGroup.isDisplayed();
	}

	public void verifyTitleOfTheReportDisplayed(String title) {
		logger.info("verify the reports title is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		WebElement result = driver.findElement(By.xpath("//mat-card-title[contains(text(),'" + title + "')]"));
		result.isDisplayed();
	}

	public void clickOneAccountRadioButton() {
		logger.info("Click on One Account Radio Button");
		testUtil.init(this);
		WebElement oneAccountRadioButton = testUtil.filterSelector("mat-radio-button", null, null, 1, "One Account");
		WebElement oneAccountRadioButtonInput = testUtil.filterSelector("input", null, oneAccountRadioButton, 0);
		testUtil.clickElementJavascript(oneAccountRadioButtonInput);
		testUtil.setHardWait(1000);
	}

	public void clickOnOneAccountRadioButton() {
        logger.info("Click on One Account Radio Button");
        testUtil.init(this);
        WebElement oneAccountRadioButton = testUtil.filterSelector("mat-radio-button", null, null, 1, "One Account");
        WebElement oneAccountRadioButtonInput = testUtil.filterSelector("input", null, oneAccountRadioButton, 0);
        testUtil.clickElementJavascript(oneAccountRadioButtonInput);
        testUtil.setHardWait(1000);
    }


	public void verifyAccountSearchIsActive() {
		logger.info("Verifying account search is active");
		testUtil.init(this);
		// testUtil.setExplicitWait(accountSearch, 20);
		accountSearch.isEnabled();

	}

	public void verifySuccess() {
		logger.info("verify success message");
		testUtil.init(this);
		testUtil.setHardWait(1700);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String successMsg = (String) js.executeScript("return document.querySelector(\"*[class*='snack']\").innerHTML");
		testUtil.setHardWait(700);
		boolean succcess = successMsg.contains("Success");
		TestUtil.verifyTrue(succcess);
	}

	public void verifySuccessMessage() {
		logger.info("verify success message");
		testUtil.init(this);
		testUtil.setExplicitWait(successMsg, 60);
		successMsg.isDisplayed();
	}
	
	 public void verifyPopupMessage(String containsText) {
	        testUtil.init(this);
	        logger.info("Verify pop-up message contains: " + containsText);
	        WebElement snackbarMessage = testUtil.filterSelector("snack-bar-container", null, null, 1, containsText);
	        testUtil.verifyElementHasTextContent(snackbarMessage);
	    }

	public void clickOnMyReportsTab() {
		logger.info("Click on my reports tab");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)");
		testUtil.setExplicitWait(myReport, 60);
		myReport.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnDontSave() {
		logger.info("Click on dont save button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		dontSave.click();
		testUtil.setHardWait(1000);
	}

	public void deleteCopyReportIfExistInTheTable(String reportName) {
		logger.info("Delete report number if it  already exists in the table");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		List<WebElement> we = driver.findElements(By.xpath(
				"//*[@id='mat-tab-content-0-0']/div/div/mat-card[1]/mat-card-content/div/mat-table/mat-row[*]/mat-cell[1]/span"));
		int listsize = we.size();

		System.out.println("Element size : " + listsize);

		for (int i = 0; i < listsize; i++) {

			String eleName = we.get(i).getText();
			System.out.println("Name of element is : " + eleName);
			System.out.println(i);

			if (eleName.equals(reportName)) {

				testUtil.setHardWait(2000);
				driver.findElement(By.xpath(
						"//mat-card[1]/mat-card-content/div/mat-table/mat-row[2]/mat-cell[5]/button[4]/span/mat-icon"))
						.click();
				testUtil.setHardWait(1000);

				driver.findElement(By.xpath("//button[@id='deleteReportButton']")).click();
				testUtil.setHardWait(1000);

				break;
			}
		}
	}

	public void selectOnReportDropDown(String reportName) {
		logger.info("Select on Report Drop Down");
		testUtil.init(this);
		testUtil.setExplicitWait(ReportDdown, 60);
		testUtil.clickElementJavascript(ReportDdown);
		testUtil.setHardWait(3000);
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + reportName + "')]"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
		testUtil.setHardWait(2000);
	}
	
//	public void enterValueTextBox(String value) {
//		logger.info("Enter value in the text box");
//		testUtil.init(this);
//		testUtil.setHardWait(700);
//		WebElement textBox = driver.findElement(By.cssSelector("[id='reportName']"));
//		testUtil.setExplicitWait(textBox, 20);
//		textBox.sendKeys(value);
//		testUtil.setHardWait(1700);
//	}
	
	
	public void enterValueTextBox(String value) {
		logger.info("Enter value in the text box");
		testUtil.init(this);
		WebElement textBox = testUtil.filterXpath("//input", null, null, 0, "reportName");
		testUtil.setAngularInputValue(textBox, value);
		testUtil.setHardWait(700);
	}

    
    
	public void clickOnEditIcon(String reportNme) throws InterruptedException {
		logger.info("Edit the added report : " + reportNme);
		testUtil.setHardWait(15000);
		try {
			WebElement ele = driver.findElement(
					By.xpath("//*[text()='" + reportNme + "']/parent::mat-cell/following-sibling::mat-cell[4]/button[2]"));
			testUtil.setExplicitWait(ele, 60);
			ele.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		testUtil.setHardWait(1700);
	}
	
	public void clickOnDownloadButton() {
		logger.info("Click on download report button");
		testUtil.init(this);
		testUtil.setHardWait(700);
		WebElement e = driver.findElement(By.cssSelector("*[id='downloadButton']"));
		testUtil.setExplicitWait(e, 20);
		
		testUtil.clickElementJavascript(e);
		testUtil.setHardWait(1700);
	}
	
	public void clickOnDownloadIcon(String reportNme) throws InterruptedException {
		logger.info("Edit the added report : " + reportNme);
		testUtil.init(this);
		testUtil.setHardWait(15000);
		WebElement ele = driver.findElement(
				By.xpath("//*[text()='" + reportNme + "']/parent::mat-cell/following-sibling::mat-cell[4]/button[1]"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
		testUtil.setHardWait(700);
	}
	

	public void clickOnCopyIcon(String reportNme) throws InterruptedException {
		logger.info("Edit the added report : " + reportNme);
		testUtil.setHardWait(15000);
		WebElement ele = driver.findElement(
				By.xpath("//*[text()='" + reportNme + "']/parent::mat-cell/following-sibling::mat-cell[4]/button[3]"));
		testUtil.setExplicitWait(ele, 60);
		testUtil.clickElementJavascript(ele);
		testUtil.setHardWait(1700);
	}
	
	public String returnSecondElement() {
		logger.info("Return value of top element.");
		testUtil.init(this);
		WebElement top = driver.findElement(By.xpath("//ul/li[2]/span[2]"));
		testUtil.setExplicitWait(top, 60);
		return top.getText();
	}
	
	public enum Icon {
		DOWNLOAD, EDIT, COPY, DELETE
	}
	
	public void clickIconButton(String reportNme, Icon iconType) throws InterruptedException {

		
		
		logger.info("Click " + iconType.toString() + " icon.");
		testUtil.init(this);
		
		if (iconType == Icon.DELETE) {
			
			WebElement matRow = testUtil.filterSelector("mat-row", null, null, 5, reportNme);
			
			WebElement button = testUtil.filterSelector("button", null, matRow, 3, iconType.toString().toLowerCase());
			testUtil.clickElementJavascript(button);
			
			WebElement deleteReportButton = testUtil.filterSelector("button", null, null, 0, "Delete Report");
			testUtil.clickElementJavascript(deleteReportButton);
		
		} else {
			WebElement matRow = testUtil.filterSelector("mat-row", null, null, 5, reportNme);
			WebElement button = testUtil.filterSelector("button", null, matRow, 3, iconType.toString().toLowerCase());
			testUtil.clickElementJavascript(button);
		}
		testUtil.setHardWait(700);
	}

	public void clickSaveReportButton() {
		logger.info("Click on save report button");
		testUtil.init(this);
		WebElement saveReport = testUtil.filterSelector("button", null, null, 0, "Save Report");
		testUtil.clickElementJavascript(saveReport);
		testUtil.setHardWait(2000);
	}
}