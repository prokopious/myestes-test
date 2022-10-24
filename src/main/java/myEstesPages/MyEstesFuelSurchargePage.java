package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesFuelSurchargePage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesFuelSurchargePage.class);
	
	
	// Surcharges Tab
	@FindBy(how = How.ID, using = "tab-link-surcharges")
	private WebElement weSurchargesTab;
	// Surcharges Table
	@FindBy(how = How.ID, using = "tab-surcharges")
	private WebElement weSurchargesTable;
	// History Tab
	@FindBy(how = How.ID, using = "tab-link-history")
	private WebElement weHistoryTab;
	// History Table
	@FindBy(how = How.ID, using = "tab-history")
	private WebElement weHistoryTable;
	
	
	//
	
	
	////
	// Click On Surcharges Tab
	public void clickOnSurchargesTab () {
		logger.info("Click On Surcharges Tab");
		testUtil.init(this);
		weSurchargesTab.click();		
	}
	// Verify Surcharges Table Displayed
	public void verifySurchargesTabDisplayed() {
		testUtil.init(this);
		logger.info("Verify Surcharges Tab displayed");
		weSurchargesTable.isDisplayed();
	}
	// Click On History Tab
	public void clickOnHistoryTab () {
		logger.info("Click On History Tab");
		testUtil.init(this);
		weHistoryTab.click();		
	}
	// Verify History Table Displayed
		public void verifyHistoryTableDisplayed() throws InterruptedException {
			testUtil.init(this);
			logger.info("Verify History Table Displayed");
			Thread.sleep(1000);
			if (weHistoryTable.isDisplayed()) {
				System.out.println("The Tab is dispaled");
			}else {
				System.out.println("the Tab is NOT dispaled");
			}
		}

		
		public void verifySurchargesTableDisplayed() {
			testUtil.init(this);
			logger.info("Verify Surcharges Table Displayed");
			testUtil.verifyAndPrintWebTableData("//table[@id='fuel-surcharge-table']");
			
		}
		//*[@id="tab-history"]

		public void verifyHistoryDataDisplayed() {
			testUtil.init(this);
			logger.info("Verify Surcharges Table Displayed for History");
			
			testUtil.verifyAndPrintWebTableData("//*[@id='history-table']");
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
