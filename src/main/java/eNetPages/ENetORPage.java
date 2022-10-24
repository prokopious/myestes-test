package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class ENetORPage extends TestBase{
	
	
 private Logger logger=Logger.getLogger(ENetORPage.class);

/* public void EnetORPage() {
	 
	 testUtil.init(this);
 }*/

	//*OR %
	@FindBy(how = How.ID, using = "addOrPercent")
	private WebElement addOrPercent;
	
	@FindBy(how = How.ID, using = "addOrPercent")
	private WebElement addOR;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Add']")
	private WebElement eAdd;

	//Add button
	@FindBy(how = How.XPATH, using ="//*[@value='Add']")
	private WebElement addButton;
	
	@FindBy(how = How.ID, using = "addOriginTerminal")
	private WebElement addOriginTerminal;
	
	@FindBy(how = How.ID, using = "addDestinationTerminal")
	private WebElement addDestinationTerminal;
	
	@FindBy(how = How.XPATH, using = "//table[@class='output-table']/tbody/tr/td[1]")
	private List<WebElement> lstOREntries;

	@FindBy(xpath="//*[@class='output-table']//tr")
	private List<WebElement> orTableRows;

	@FindBy(css = "[value='future']")
	private WebElement futureRadioButton;
	
	@FindBy(id = "addFromBalanceAdjustment")
	private WebElement balanceStart;
	
	@FindBy(id = "addToBalanceAdjustment")
	private WebElement balanceEnd;
	
	@FindBy(id = "addOriginRegion")
	private WebElement origRegion; 
	
	@FindBy(id = "addDestinationRegion")
	private WebElement destRegion;
	
	@FindBy(id = "addFromLinearFeet")
	private WebElement startLinearFt;
	
	@FindBy(id = "addToLinearFeet")
	private WebElement endLinearFt;
	
	@FindBy(id = "addEffectiveStartDate")
	private WebElement effectiveStartDate;
	
	@FindBy(id = "addEffectiveEndDate")
	private WebElement effectiveToDate;

	/********************METHODS*******************/
	
	public void enterORPercent(String orPercent) {
		logger.info("Enter OR% as "+orPercent);
		testUtil.init(this);
		addOrPercent.sendKeys(orPercent);
	}
	
	public void clickOnAdd() {
		logger.info("Clikc on Add button");
		testUtil.init(this);
		addButton.click();
	}
	
	public void removeAllORPercent() {
		logger.info("Remove All OR%");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		List <WebElement> rowData = driver.findElements(By.xpath("//table[@class ='output-table']//tr"));
		
		if(rowData.size() > 2 ) {
			
			for(int i = 3;i <= rowData.size(); i++) {
				driver.findElement(By.xpath("//table[@class ='output-table']//tr[3]/td[15]/a")).click();
				testUtil.setHardWait(2000);
				driver.switchTo().alert().accept();
				System.out.println("REMOVED");
				testUtil.setHardWait(2000);
			}
		}
		else {
			System.out.println("Target Operating revenue was not set");
		}
	}

	public void addORPercent(String orPercent) {
		logger.info("Add OR percent");
		testUtil.init(this);
		addOrPercent.sendKeys(orPercent);
		addButton.click();
	}
	
	
	public void enterORNumber(String aNum) {
		testUtil.init(this);
		logger.info("enter OR Number");
		addOR.sendKeys(aNum);
	}
	
	public void clickOnAddButton() {
		testUtil.init(this);
		logger.info("click On Add Button");
		eAdd.click();
	}
	
	public void selectOriginTerminal(String value) {
		logger.info("Select Origin Terminal");
		testUtil.init(this);
		testUtil.setHardWait(500);
		testUtil.selectUsingVisibleText(addOriginTerminal, value);
	//	new Select(addOriginTerminal).selectByVisibleText(value);
	}
	
	public void selectDestinationTerminal(String value) {
		testUtil.init(this);
		logger.info("select Destination Terminal");
		new Select(addDestinationTerminal).selectByVisibleText(value);
	}

	
	public void clickRemove(String value) {
		testUtil.init(this);
		logger.info("Click on Remove");
		WebElement element = driver.findElement(By.xpath("//tr[td[contains(text(),'"+value+"')]]/td/a[contains(@onclick,'remove')]/img"));
		testUtil.clickElementJavascript(element);
		driver.switchTo().alert().accept();
	}
	
	public void verifyOREntries(String value) throws InterruptedException {
		boolean flag = false;
		testUtil.init(this);
		logger.info("Click on Remove");
		Thread.sleep(2000);
		for(WebElement e : lstOREntries) {
			if(e.getText().trim().contains(value)) {
				flag = true;
				break;
			}
		}
		Assert.assertFalse(flag);
	}

	public void selectOrigRegion(String origReg) {
		logger.info("Select Orig Region: ");
		testUtil.init(this);
		testUtil.setExplicitWait(origRegion, 20);
		testUtil.selectUsingVisibleText(origRegion, origReg);
	}
	
	public void selectDestRegion(String destReg) {
		logger.info("Select Dest Region");
		testUtil.init(this);
		testUtil.setExplicitWait(destRegion,20);
		testUtil.selectUsingVisibleText(destRegion, destReg);
	}
	
	public void enterStartLinearFeet(String linearFt) {
		logger.info("Enter Start linear feet");
		testUtil.init(this);
		testUtil.setExplicitWait(startLinearFt,20);
		startLinearFt.sendKeys(linearFt);
	}
	
	public void enterToLinearFeet(String linearFt) {
		logger.info("Enter To linear feet");
		testUtil.init(this);
		testUtil.setExplicitWait(endLinearFt,20);
		endLinearFt.sendKeys(linearFt);
	}
	
	public void enterEffectiveStartDate() {
		logger.info("Enter effective start date");
		testUtil.init(this);
		testUtil.setExplicitWait(effectiveStartDate, 20);
		effectiveStartDate.sendKeys(testUtil.todaysDate());
	}
	
	public void enterEffectiveEndDate() {
		logger.info("Enter effective end date");
		testUtil.init(this);
		testUtil.setExplicitWait(effectiveToDate, 20);
		effectiveToDate.sendKeys(testUtil.todaysDate());
	}
	
	public void verifyORAddedMessageIsDisplayed(String ORPercent) {
		logger.info("Verifying OR added succesfully message is displayed");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'"+ORPercent+"')]"));
		testUtil.setExplicitWait(ele, 20);
		Assert.assertTrue(ele.isDisplayed());
	}
	
	public void clickOnFutureORRadioButton() {
		logger.info("Click on Future OR radio button");
		testUtil.init(this);
		futureRadioButton.click();
	}
	
	public void enterBalanceAdjustmentStart(String value) {
		logger.info("Enter Balance adjustment start amout");
		testUtil.init(this);
		balanceStart.sendKeys(value);
	}
	
	public void enterBalanceAdjustmentEnd(String value) {
		logger.info("Enter Balance adjustment End amount");
		testUtil.init(this);
		balanceEnd.sendKeys(value);
	}
	
	public void verifyLinearFeetIsInRangeFormat(String linear) {
		logger.info("Verify Linear feet is in range format");
		testUtil.init(this);
		for(int i = 3; i <= orTableRows.size(); i++) {
			String linearFt = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]//td[11]")).getText().trim();
			System.out.println(linearFt);
			System.out.println(linear);
			Assert.assertEquals(linearFt, linear);
		}
	}
	
	public void verifyBalanceAdjustmentInRangeFormat(String adjustment) {
		logger.info("Verify Balance Adjustment is in range format");
		testUtil.init(this);
		for(int i = 3; i <= orTableRows.size(); i++) {
			String balanceAdj = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]//td[8]")).getText().trim();
			System.out.println(balanceAdj);
			System.out.println(adjustment);
			Assert.assertEquals(balanceAdj, adjustment);
		}
	}


}





