package eNetPages;

import testBase.TestBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;


public class ENetBillQueue extends TestBase {
	
private Logger logger=Logger.getLogger(ENetBillQueue.class);
	
	@FindBy(xpath = "//*[@id='pageTitle']")
	private WebElement pageTtl;
	
	@FindBy(xpath = "/html/body/form[2]/div/div/div/table/tbody")
	private WebElement billQueueTable;
	
	@FindBy(xpath = "//*[@id='status']/span[2]/button/img")
	private WebElement excelLink;
	
	@FindBy(xpath = "//*[@id='excel']/ul/li[3]/a")
	private WebElement excelViewAll;
	
	@FindBy(xpath = "//*[@id='billQue']")
	private WebElement billQue;
	
	@FindBy(xpath = "//*[@id='excel']/p/a")
	private WebElement closeBtn;
	
	@FindBy(xpath = "//*[@id='excel']/ul/li[2]/a")
	private WebElement selectedBtn;
	
	@FindBy(xpath = "//*[@id='excel']/ul/li[8]/a")
	private WebElement emailSelectedBtn;
	
	@FindBy(xpath = "//*[@id='emailAddress']")
	private WebElement emailBox;
	
	@FindBy(xpath = "//*[@id='email']/input[2]")
	private WebElement emailSendBtn;
	
	/********************METHODS********************/
	
	
	public void verifyPageTitle() {
		testUtil.init(this);
	
		logger.info("Verify Page Title.");
		testUtil.assetWaitDisplayed(pageTtl, 10, 250, TimeUnit.MILLISECONDS);
		String pageTitle = pageTtl.getText();
		logger.info("Page Title : "+pageTitle);
		Assert.assertEquals(pageTitle, "Bill Queue", "Page Title doesnot match.");
	}
	
	public void verifyBillQueueTableDataPresent() {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		logger.info("Verify Bill Queue Table contains data or not.");
		List<String> data = new ArrayList<String>();
		testUtil.assetWait(null, billQueueTable, 10, 250, TimeUnit.MILLISECONDS);
		List<WebElement> billrows = new ArrayList<WebElement>();
		billrows = billQueueTable.findElements(By.tagName("tr"));
		for(int i=1;i<billrows.size();i++) {
			WebElement col1 = billrows.get(i).findElement(By.xpath("td[2]"));
			WebElement col2 = billrows.get(i).findElement(By.xpath("td[3]"));
			data.add(col1.getText());
			data.add(col2.getText());
		}
		
		if(!data.isEmpty()) {
			logger.info("BillQueue Table has data in rows.");
		}else {
			Assert.fail("BillQueue Table does not contain data in rows.");
		}
	}
	
	public void clickOnExcelLink() {
		testUtil.init(this);
		logger.info("Click On Excel Link.");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		testUtil.assetWaitClickable(null, excelLink, 10, 250, TimeUnit.MILLISECONDS);
		excelLink.click();
	}
	
	public void clickOnExcelViewAll() {
		logger.info("Click on Excel View All link");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, excelViewAll, 10, 250, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(excelViewAll);
		
	}

	public void verifyAllDataPresentOrNot(String fileName, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		testUtil.init(this);
		
		logger.info("Verify All data present or not.");
		testUtil.assetWait(null, billQue, 10, 250, TimeUnit.MILLISECONDS);
		String billQueNum = billQue.getText();
		int index = billQueNum.indexOf(")");
		billQueNum = billQueNum.substring(0, index);
		logger.info("billQueNum : "+billQueNum);
		int recordCount = Integer.parseInt(billQueNum.substring(billQueNum.length()-2, billQueNum.length()));
		logger.info("recordCount : "+recordCount);
		int rowCount = testUtil.fetchNumberofRows(sheetName, fileName);
		if(rowCount>recordCount) {
			logger.info("All data present in the Excel file.");
		}else {
			Assert.fail("All data not present in the Excel file.");
		}
	}

	public void clickOnClose() {
		testUtil.init(this);		
		logger.info("Click On Close button.");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		closeBtn.click();
	}

	public void selectOneRecord() {
		testUtil.init(this);
		logger.info("Select one record.");
		WebElement firstRow = billQueueTable.findElement(By.xpath("tr[2]/td[1]/input"));
		firstRow.click();
	}
	
	public void selectMultipleRecords(int selectNum) {
		testUtil.init(this);
		logger.info("Select multiple records.");
		List<WebElement> billQueue = new ArrayList<WebElement>();
		billQueue = billQueueTable.findElements(By.tagName("tr"));
		int count = 0;
		for(int i =0;i<billQueue.size();i++) {
			WebElement billcheckBox = billQueue.get(i+1).findElement(By.xpath("td[1]/input"));
			if(!billcheckBox.isSelected()) {
				billcheckBox.click();
				count++;
				if(count==selectNum) {
					break;
				}
			}else {
				billcheckBox.click();
			}
			
		}
	}
	
	public void clickOnSelected() {
		testUtil.init(this);
		logger.info("Click On Selected.");
		selectedBtn.click();
	}
	
	public Map<Integer,List<String>> fetchRecordDetails(){
		testUtil.init(this);
		Map<Integer,List<String>> recordsFetched = new HashMap<Integer,List<String>>();
		List<String> tableRecords = new ArrayList<String>();
		List<WebElement> billrows = new ArrayList<WebElement>();
		billrows = billQueueTable.findElements(By.tagName("tr"));
		String data;
		List<WebElement> col = new ArrayList<WebElement>();
		for(int i=1;i<billrows.size();i++) {
			WebElement checkBox = billrows.get(i).findElement(By.xpath("td[1]/input"));
			if(checkBox.isSelected()) {
				logger.info("Found selected record");
			col = billrows.get(i).findElements(By.tagName("td"));
			for(int j=1;j<col.size();j++) {
				data = col.get(j).getText();
				if(data.isEmpty()) {
					data = "null";
				}
				logger.info("data : "+data);
				tableRecords.add(data);
			}
			recordsFetched.put(i, tableRecords);
			break;
			}
		}
		return recordsFetched;
	}
	
	public void verifySelectedDataPresentOrNot(String fileName, String sheetName, Map<Integer,List<String>> records) throws EncryptedDocumentException, InvalidFormatException, IOException {
		testUtil.init(this);
		testUtil.setHardWait(3000);
		logger.info("Verify All data present or not.");
		int rowCount = testUtil.fetchNumberofRows(sheetName, fileName);
		logger.info("Number of selected records : "+records.size());
		if(rowCount == records.size()) {
			logger.info("Number of records are same.");
			Map<Integer,List<String>> excelRecordsFetched = testUtil.fetchRecordsfromExcel(sheetName, fileName);
			if(records.keySet().equals(excelRecordsFetched.keySet())) {
				logger.info("Found the selected records");
			}else {
				Assert.fail("Selected records not found.");
			}
		}else {
			Assert.fail("Number of records are not same.");
		}
	}
	
	public void clickOnEmailExcelSelected() {
		testUtil.init(this);
		logger.info("Click on Email Excel Selected.");
		emailSelectedBtn.click();
	}
	
	public void enterEmailAddress(String email) {
		testUtil.init(this);
		logger.info("Enter email address : "+email);
		emailBox.sendKeys(email);
		emailSendBtn.click();
		logger.info("Clicked send button.");
	}
	
}

