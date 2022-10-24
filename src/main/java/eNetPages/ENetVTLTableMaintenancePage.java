package eNetPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

public class ENetVTLTableMaintenancePage extends TestBase{
	
	private Logger logger=Logger.getLogger(ENetVTLTableMaintenancePage.class);
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Food Whse')]")
	private WebElement eFoodWhse;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Aged A/R')]")
	private WebElement eAgedAR;
	

	@FindBy(how = How.XPATH, using ="//a[contains(text(),'OR')]")
	private WebElement eOR;

	@FindBy(xpath="//a[contains(text(),'Floor Mins')]")
	private WebElement floorMins;
	
	@FindBy(id="mainpage")
	private WebElement frameElement;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/table/tbody/tr/td[1]/span")
	private WebElement RecordedQuoteNumber;
	
	@FindBy(how = How.ID, using = "analystComment_1")
	private WebElement CommentsField;
	
	@FindBy(how = How.ID, using = "recalculate-button")
	private WebElement RecalculateBtn; 
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]")
	private WebElement pageTitle;
	
	@FindBy(xpath = "/html/body/div/div[3]/form/div/select")
	private WebElement quoteTypeDropdown;	
	
	@FindBy(xpath = "//*[@id=\"accessorialChargeTable\"]/tbody")
	private WebElement accessorialsTable;
	
	
	public void clickOnFoodWhseTab() {
		testUtil.init(this);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("Click On Food Whse Tab");
		eFoodWhse.click();
	}
	
	public void clickOnAgedARTab() {
		logger.info("Click On Aged A/R Tab");
		testUtil.init(this);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		eAgedAR.click();
	}
	

	public void clickOnORTab() {
		logger.info("Click on OR Tab");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setHardWait(1000);
		eOR.click();
	}
	
	public void clickOnFloorMinsLink() {
		logger.info("Click on Floor Mins Link");
		testUtil.init(this);
		driver.switchTo().frame(frameElement);
		floorMins.click();
	}
	public void clickOnRecalculateButton() {
		testUtil.init(this);
		logger.info("click On Recalculate Button");
		RecalculateBtn.click();
	}
	
	public void clickOnAddButton() {
		testUtil.init(this);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("click On Add Button");
		RecalculateBtn.click();
	}
	
	public void verifyRecordAdded(String quoteNumber) throws InterruptedException {
		testUtil.init(this);
		logger.info("Verify record added in entry");
		System.out.println(RecordedQuoteNumber.getText());
		testUtil.setHardWait(500);
		assertEquals(RecordedQuoteNumber.getText(), quoteNumber);
	}
	
	public void verifyQuoteNumberDisplay() {
		testUtil.init(this);
		logger.info("Verify Quote Number Display");
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Quote: ')]")).isDisplayed());
	}
	
	public void enterComments(String com) throws InterruptedException {
		testUtil.init(this);
		logger.info("enter Analyst Comments");
		CommentsField.clear();
		CommentsField.sendKeys(com);
		Thread.sleep(500);
	}
	
	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify VTL Table Maintenance page");
		driver.switchTo().defaultContent();
		String page = pageTitle.getText().toString();
		if(!page.contains("VTL Table Maintenance")) {
			Assert.fail("Page Title doesnot match.");
		}
		logger.info("Found VTL Table Maintenance.");
	}
	
	public void selectQuoteType(String quoteType) {
		testUtil.init(this);
		driver.switchTo().frame("mainpage");
		logger.info("Select Quote type : "+quoteType);
		quoteTypeDropdown.click();
		Select quoteTyp = new Select(quoteTypeDropdown);
		quoteTyp.selectByValue(quoteType);
	}
	
	public List<String> fetchAccessorials() {
		testUtil.init(this);
		logger.info("Fetch Accessorials");
		List<WebElement> rows = new ArrayList<WebElement>();
		List<String> accessories = new ArrayList<String>();
		String accessoryName;
		rows = accessorialsTable.findElements(By.tagName("tr"));
		for(int i=1;i<rows.size();i++) {
			accessoryName = rows.get(i).findElement(By.xpath("td[1]")).getText().toString();
			logger.info("Accessory name : "+accessoryName);
			accessories.add(accessoryName);
		}
		return accessories;
	}
	
	public void deleteAccessory(String accessory) {
		testUtil.init(this);
		logger.info("Delete accessory : "+accessory);
		List<WebElement> rows = new ArrayList<WebElement>();
		List<String> accessories = new ArrayList<String>();
		String accessoryName;
		rows = accessorialsTable.findElements(By.tagName("tr"));
		for(int i=1;i<rows.size();i++) {
			accessoryName = rows.get(i).findElement(By.xpath("td[1]")).toString();
			if(accessoryName.equalsIgnoreCase(accessory)) {
				WebElement closeBtn = rows.get(i).findElement(By.xpath("td[7]/a/img"));
				closeBtn.click();
				break;
			}
		}
	}
	

}
