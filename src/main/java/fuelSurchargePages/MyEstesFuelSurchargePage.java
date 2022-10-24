package fuelSurchargePages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
	
	@FindBy(how = How.ID, using = "tab-link-surcharges")
	private WebElement Surcharges;
	
	
	
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
		logger.info("Verify Surcharges Tab Displayed");
		weSurchargesTable.isDisplayed();
	}
	// Click On History Tab
	public void clickOnHistoryTab () {
		logger.info("Click On History Tab");
		testUtil.init(this);
		weHistoryTab.click();		
	}
	// Verify History Table Displayed
		public void verifyHistoryTableDisplayed() {
			testUtil.init(this);
			logger.info("Verify History Table Displayed");
			weHistoryTable.isDisplayed();
		}
		
		public void verifySurchargesHistoryTabDisplayed() {
			testUtil.init(this);
			logger.info("Verify Surcharges History Table Displayed");
			Surcharges.isDisplayed();
			weHistoryTable.isDisplayed();
		}
		
		public void verifySurchargesTableDisplayed() {
			testUtil.init(this);
			logger.info("Verify Surcharges Table Displayed");
			testUtil.printWebTableData();
		}

}
