package eNetPages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetExchangeRateMaintenancePage extends TestBase {

	private Logger logger = Logger.getLogger(ENetHomePage.class);

	// Elements
	@FindBy(how = How.ID, using = "title-bar")
	private WebElement PageTitle;

	@FindBy(how = How.XPATH, using = "//*[@class='data-table']/tbody/tr/th")
	private List<WebElement> TotalHeaders;

	@FindBy(how = How.XPATH, using = "//*[@class='data-table']")
	private WebElement DataTable;
	
	@FindBy(how = How.XPATH, using = "//*[@class='data-table']/tbody/tr/th")
	private WebElement ExRateDataTable;

	@FindBy(how = How.ID, using = "exchangeDate")
	private WebElement ExDate;
	
	@FindBy(how = How.ID, using = "inboundPercent")
	private WebElement InboundPercent;
	
	@FindBy(how = How.ID, using = "submitButton")
	private WebElement SubmitBtn;
	
	
	/*************************************************************************/

	public void validatePageTitle(String expTitle) {
		testUtil.init(this);
		String title = PageTitle.getText();
		Assert.assertTrue(title.contains(expTitle));
		logger.info("Page Title is " + title);
	}

	public void verifyColumnDisplayed(String col) {
		testUtil.init(this);
		for (WebElement header : TotalHeaders) {
			String colName = header.getText();
			if (colName.equalsIgnoreCase(col))
				logger.info("Column Name " + colName + " is displayed");
			else
				logger.info("Column Name " + colName + " is not displayed");
		}
	}

	public void verifyTableDisplayed() {
		testUtil.init(this);
		assertEquals(testUtil.isDisplayed(DataTable),true);
		logger.info("Data Table is displayed");
	}
	
	public void clickOnExchangeRateEdit(String date) {
		testUtil.init(this);
		WebElement editBtn = driver.findElement(By.xpath("//*[text()='"+date+"']/preceding-sibling::td/a"));
		assertEquals(testUtil.isDisplayed(editBtn),true);
		editBtn.click();
		logger.info("clicked On Exchange Rate Edit Button");
	}
	
	public void validateExRateTableDisplayed(String name) {
		testUtil.init(this);
		assertEquals(testUtil.isDisplayed(ExRateDataTable),true);
		assertEquals(ExRateDataTable.getText(), name);
		logger.info("Table "+ExRateDataTable.getText()+" is Displayed");
	}
	
	public String getExchangeDate() {
		testUtil.init(this);
		logger.info("Get Exchange Date From Exchange-Rate Table");
		String value = ExDate.getAttribute("value");
		return value;
	}
	
	public String getMainInboundValue(String date) {
		testUtil.init(this);
		logger.info("Get Inbound Value from DataTable");
		String value = driver.findElement(By.xpath("//*[text()='"+date+"']/following-sibling::td[3]")).getText();
		return value;
	}
	
	public String getInboundValue() {
		testUtil.init(this);
		logger.info("Get Inbound Percent from Exchange Rate DataTable");
		String value =InboundPercent.getAttribute("value");
		return value;
	}
	
	public void enterInboundValue(String percent) {
		testUtil.init(this);
		logger.info("Enter Inbound Percent from Exchange Rate DataTable");
		InboundPercent.clear();
		InboundPercent.sendKeys(percent);
		
	}
	
	public void clickOnSubmitBtn() {
		testUtil.init(this);
		logger.info("Click on Submit Button");
		SubmitBtn.click();
	}
}