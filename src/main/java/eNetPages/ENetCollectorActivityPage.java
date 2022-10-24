package eNetPages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;

public class ENetCollectorActivityPage extends TestBase{

	private Logger logger = Logger.getLogger(ENetApplicationsPage.class);
	
	/*************************************Elements***************************************/
	
	
	@FindBy(xpath = "/html/body/p[1]")
	private WebElement pageTtl;
	
	@FindBy(id = "sqtable")
	private WebElement collectorActivityReportTable;
	
	@FindBy(id = "accountNumber")
	private WebElement openARaccountNum;
	
	@FindBy(id = "emailAddress")
	private WebElement openARemailAddress;
	
	@FindBy(xpath = "//input[@value = 'Submit']")
	private WebElement openARsubmitBtn;
	
	@FindBy(id = "accountNumber")
	private WebElement UndiscountedFrghtRptAccNum;
	
	@FindBy(id = "emailAddress")
	private WebElement UndiscountedFrghtRptemailAddress;
	
	@FindBy(xpath = "//input[@value = 'Submit']")
	private WebElement UndiscountedFrghtRptsubmitBtn;
	
	/*************************************Method*****************************************/
	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify page.");
		driver.switchTo().frame("mainpage");
		String ttl = pageTtl.getText();
		logger.info("Page title : "+ttl);
		Assert.assertEquals(ttl, "Collector Activity", "Page title does not match.");
	}
	
	public void goInsideFrame(int frameNo) {
		testUtil.init(this);
		logger.info("Go inside frame : "+frameNo);
		driver.switchTo().defaultContent();
		//switch to mainpage frame
		driver.switchTo().frame("mainpage");
		List<WebElement> frames = new ArrayList<WebElement>();
		frames = driver.findElements(By.tagName("iframe"));
		driver.switchTo().frame(frames.get(frameNo));
	}
	
	public void checkValuesGreaterThanZeroInRecord() {
		testUtil.init(this);
		logger.info("Check values greater than zero in records.");
		int count = 0;
		List<WebElement> rows = new ArrayList<WebElement>();
		List<WebElement> values = new ArrayList<WebElement>();
		rows = collectorActivityReportTable.findElements(By.tagName("tr"));
		
		for(int r=1; r<rows.size(); r++) {
			values = rows.get(r).findElements(By.tagName("td"));
			for(int v=3; v<values.size(); v++) {
				int val = Integer.parseInt(values.get(v).getText());
				if(val>0) {
					count = 1;
					break;
				}
			}
			if(count==1) {
				break;
			}
		}
		if(count==1) {
			logger.info("Found record values greater than 0.");
		}else {
			Assert.fail("Cannot find record values greater than 0.");
		}
		
	}
	
	public void enterOpenARReportAccountNumber(String accNum) {
		testUtil.init(this);
		logger.info("Enter Open AR Report Account Number : "+accNum);
		openARaccountNum.sendKeys(accNum);
	}
	
	public void enterOpenARReportemail(String email) {
		testUtil.init(this);
		logger.info("Enter Open AR Report Email Address : "+email);
		openARemailAddress.sendKeys(email);
	}
	
	public void clickOnOpenARReportSubmit() {
		testUtil.init(this);
		logger.info("Click on OpenAR Report Submit button.");
		openARsubmitBtn.click();
	}
	
	public void enterUndiscountedFrieghtReportAccountNumber(String accNum) {
		testUtil.init(this);
		logger.info("Enter Undiscounted Frieght Report Account Number : "+accNum);
		UndiscountedFrghtRptAccNum.sendKeys(accNum);		
	}
	
	public void enterUndiscountedFrieghtReportemail(String email) {
		testUtil.init(this);
		logger.info("Enter Open AR Report Email Address : "+email);
		UndiscountedFrghtRptemailAddress.sendKeys(email);
	}
	
	public void clickUndiscountedFrieghtReportSubmit() {
		testUtil.init(this);
		logger.info("Click on OpenAR Report Submit button.");
		UndiscountedFrghtRptsubmitBtn.click();
	}


}
