package eNetPages;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class ENetCollectionAgencyReportsPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetCollectionAgencyReportsPage.class);

	@FindBy(xpath = "//*[@id='pageTitle']")
	private WebElement pageTitle;
	
	@FindBy(xpath = "/html/body/table/tbody/tr[1]")
	private WebElement fields;
	
	@FindBy(xpath = "//*[@id='report']")
	private WebElement reportDropdown;
	
	@FindBy(xpath = "//*[@id='collectorNumber']")
	private WebElement collectorInput;
	
	@FindBy(xpath = "//*[@id='startDate-year']")
	private WebElement startYear;
	
	@FindBy(xpath = "//*[@id='startDate-month']")
	private WebElement startMonth;
	
	@FindBy(xpath = "//*[@id='endDate-month']")
	private WebElement endMonth;
	
	@FindBy(xpath = "//*[@name = 'Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "/html/body/table/tbody/tr[3]/td[1]/button/img")
	private WebElement excelIcon;
	
	@FindBy(xpath = "//*[@id='excel']")
	private WebElement excelPopup;
	
	@FindBy(xpath = "//*[@id='excel']/a[1]")
	private WebElement viewExcelDoc;
	
	@FindBy(xpath = "//*[@id='excel']/a[2]")
	private WebElement saveExcelDoc;
	
	@FindBy(xpath = "//*[@id='excel']/a[3]")
	private WebElement emailExcelDoc;
	
	@FindBy(xpath = "//*[@id='emailAddress']")
	private WebElement emailInput;
	
	@FindBy(xpath = "//*[@id='email']/input[2]")
	private WebElement sendBtn;
	
	@FindBy(xpath = "//*[@id='message']/div")
	private WebElement emailSentConfirmation;
	
	public void verifyPageTitle() {
		testUtil.init(this);
		logger.info("Verify Page Title.");
		String page = pageTitle.getText();
		logger.info("Page Title fetched : "+page);
		Assert.assertEquals(page.trim(), "Collection Agency Reports".trim(),"Page Title does not match.");
	}
	
	public void verifyFieldsPresentorNot(List<String> fieldsList) {
		testUtil.init(this);
		driver.switchTo().frame("mainpage");
		logger.info("Verify the required fiels are present or not.");
		List<WebElement> requiredFieldEles = new ArrayList<WebElement>();
		requiredFieldEles = fields.findElements(By.tagName("td"));
		String fieldValue, requiredValue;
		List<String> fieldValueList = new ArrayList<String>();
		
		for(int i=0;i<requiredFieldEles.size();i++) {
			fieldValue = requiredFieldEles.get(i).getText();
			logger.info("Requireed field : "+fieldValue);
			fieldValueList.add(fieldValue);
		}
		
		for(int j=0;j<fieldsList.size();j++) {
			requiredValue = fieldsList.get(j);
			if(fieldValueList.contains(requiredValue)) {
				logger.info("Found field value : "+requiredValue);
			}else {
				Assert.fail("Cannot find the required field : "+requiredValue);
			}
		}
	}
	
	public void selectReport(String reportName) {
		testUtil.init(this);
		logger.info("Select Report name.");
		Select reports = new Select(reportDropdown);
		reports.selectByVisibleText(reportName);
		logger.info("Report selected : "+reportName);
	}
	
	public void enterCollectorNumber(String collectorNum) {
		testUtil.init(this);
		logger.info("Enter Collector Number."+collectorNum);
		collectorInput.sendKeys(collectorNum);
	}
	
	public void selectPreviousStartYear() {
		testUtil.init(this);
		Select startY = new Select(startYear);
		String sYear = startY.getFirstSelectedOption().getText();
		logger.info("Selected previous start year : "+sYear);
		int index = 0;
		String yearVal;
		List<WebElement> yearList = startY.getOptions();
		
		for(int i=0;i<yearList.size();i++) {
			yearVal = yearList.get(i).getText();
			if(yearVal.equals(sYear)) {
				logger.info("Found selected year : "+yearVal);
				index = i-1;
			}
		}
		startY.selectByIndex(index);
	}
	
	public void selectPreviousStartMonth() {
		testUtil.init(this);
		Select startM = new Select(startMonth);
		String sMonth = startM.getFirstSelectedOption().getText();
		logger.info("Selected previous start month : "+sMonth);
		int index = 0;
		String monthVal;
		List<WebElement> monthList = startM.getOptions();
		
		for(int i=0;i<monthList.size();i++) {
			monthVal = monthList.get(i).getText();
			if(monthVal.equals(sMonth)) {
				logger.info("Found selected month : "+monthVal);
				index = i-1;
			}
		}
		startM.selectByIndex(index);
	}
	
	public void selectPreviousEndMonth() {
		testUtil.init(this);
		logger.info("Select previous End Month");
		Select endM = new Select(endMonth);
		String eMonth = endM.getFirstSelectedOption().getText();
		logger.info("Selected previous end month : "+eMonth);
		int index = 0;
		String monthVal;
		List<WebElement> monthList = endM.getOptions();
		
		for(int i=0;i<monthList.size();i++) {
			monthVal = monthList.get(i).getText();
			if(monthVal.equals(eMonth)) {
				logger.info("Found selected month : "+monthVal);
				index = i-1;
			}
		}
		endM.selectByIndex(index);
	}
	
	public void clickOnSearchBtn() {
		testUtil.init(this);
		logger.info("Click on Search Button.");
		searchBtn.click();
	}
	
	public void clickOnExcelIcon() {
		testUtil.init(this);
		testUtil.setHardWait(500);
		logger.info("Click on Excel Icon.");
		excelIcon.click();
	}
	
	public void verifyExcelPopupview() {
		testUtil.init(this);
		logger.info("Verfy Excel popup is displayed or not.");
		boolean exceldisplayed = excelPopup.isDisplayed();
		Assert.assertTrue(exceldisplayed, "Excel Popup not displayed");
		String viewExcelText = viewExcelDoc.getText();
		Assert.assertEquals(viewExcelText.trim(), "View Excel Document".trim(), "View Excel Document link not found");
		String saveExcelText = saveExcelDoc.getText();
		Assert.assertEquals(saveExcelText.trim(), "Save Excel Document".trim(), "Save Excel Document link not found");
		String emailExcelText = emailExcelDoc.getText();
		Assert.assertEquals(emailExcelText.trim(), "E-mail Excel Document".trim(), "E-mail Excel Document link not found");
	}
	
	public void clickOnViewExcel() {
		testUtil.init(this);
		logger.info("Click on View Excel link.");
		viewExcelDoc.click();
	}
	
	public void clickOnSaveExcel() {
		testUtil.init(this);
		logger.info("Click on Save Excel link.");
		try {
			testUtil.setExplicitWait(saveExcelDoc, 60);
			//saveExcelDoc.click();
			testUtil.clickElementJavascript(saveExcelDoc);
		} catch (NullPointerException e) {

			e.printStackTrace();
		}
	}
	
	public void clickOnEmailExcel() {
		testUtil.init(this);
		logger.info("Click on Email Excel link.");
		emailExcelDoc.click();
	}
	
	public void verifyReportsExcelServletFiledownloaded() {
		testUtil.init(this);
		testUtil.setHardWait(1000);
		logger.info("Verify the ReportsExcelServlet.xls file is downloaded.");
		try {
			String fileDirectory = TestUtil.getCurrentWorkingPath()+"\\Downloads\\";
			String fileName = "ReportsExcelServlet.xls";
			logger.info("Full File path : "+fileDirectory+fileName);
			testUtil.verifyIsFileDownloaded(fileDirectory, fileName);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifyCollectionAgencyTurnoverFilesaved() {
		testUtil.init(this);
		testUtil.setHardWait(1000);
		logger.info("Verify the CollectionAgencyTurnover.xls file is saved.");
		String fileDirectory = TestUtil.getCurrentWorkingPath()+"\\Downloads\\";
		String fileName = "CollectionAgencyTurnover.xls";
		logger.info("Full File path : "+fileDirectory+fileName);
		testUtil.verifyIsFileDownloaded(fileDirectory, fileName);
	}
	
	public void enterEmailAddress() {
		testUtil.init(this);
		logger.info("Enter Email address.");
		emailInput.sendKeys("april.golden@estes-express.com");
		logger.info("Click on Send Button.");
		sendBtn.click();
	}
	
	public void verifyEmailSentConfirmation() {
		testUtil.init(this);
		logger.info("Verify the Email sent confirmation message.");
		String emailSentMessage = emailSentConfirmation.getText();
		Assert.assertEquals(emailSentMessage, "E-mail has been sent.", "email sent confirmation message not found.");
	}

}

