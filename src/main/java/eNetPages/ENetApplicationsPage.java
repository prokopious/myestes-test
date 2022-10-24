package eNetPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetApplicationsPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetApplicationsPage.class);

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'VTL Quote Exception Queue')]")
	private WebElement QouteExceptionQueue;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Rate Retriever')]")
	private WebElement RateRetriever;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Time Critical Rate Quote')]")
	private WebElement TimeCritical;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'VTL Table Maintenance')]")
	private WebElement VTLTableMaintenance;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'VTL Quote Exception Queue')]")
	private WebElement VTLquoteExeptionQueue;


	@FindBy(how = How.XPATH, using = "//a[contains(text(),'My Estes Management Tool')]")
	private WebElement myEstesManagementTool;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Quote Audit Trail')]")
	private WebElement quoteAuditTrail;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Quote Audit Trail')]")
	private WebElement myEstesQuoteAuditTrail;

	@FindBy(xpath = "//a[text()='VTL Rate Quote']")
	private WebElement vtlRateQuote;

	@FindBy(xpath = "//a[text()='Quote History Lookup']")
	private WebElement quoteHistoryLookup;

	@FindBy(xpath = "//a[contains(text(),'Invoice Inquiry')]")
	private WebElement invoiceInquiry;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Claims Entry')]")
	private WebElement claimsEntry;

	@FindBy(how = How.LINK_TEXT, using = "Undelivered Freight")
	private WebElement undeliveredFreight;
	
	@FindBy(xpath = "//a[contains(text(),'Truckload Brokerage Rate Quote')]")
	private WebElement tlRateQuote;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Hazmat  Loading Guide')]")
	private WebElement HazmatLoadingGuide;
	

	@FindBy(how = How.LINK_TEXT, using = "Invoice Batch Upload")
	private WebElement InvoiceBatchUpload;

	@FindBy(how = How.LINK_TEXT, using = "Statement Retriever")
	private WebElement StatementRetriever;
	
	@FindBy(how = How.LINK_TEXT, using = "Exchange Rate Maintenance")
	private WebElement ExchangeRateMaintenance;
	
	@FindBy(how = How.LINK_TEXT, using = "Collections")
	private WebElement Collections;
	
	@FindBy(how = How.LINK_TEXT, using = "Miscellaneous Open A/R")
	private WebElement MiscellaneousOpenAR;
	
	@FindBy(how = How.LINK_TEXT, using = "Accounts Receivable - Focus Accounts")
	private WebElement AccountReceivableFocusAccounts;
	
	@FindBy(how = How.LINK_TEXT, using = "Undiscounted Freight Report")
	private WebElement UndiscountedFreightReport;
	
	@FindBy(how = How.LINK_TEXT, using = "Delayed Pros")
	private WebElement DelayedPros;
	
	@FindBy(how = How.LINK_TEXT, using = "Focus Account Maintenance")
	private WebElement FocusAccountMaintenance;
	
	@FindBy(how = How.LINK_TEXT, using = "Current A/R Aging Balances (Omit G,I,N,P Classes)")
	private WebElement CurrentARAgingBalances;
	
	@FindBy(xpath = "/html/body/h3")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[contains(text(),'Bill Queue')]")
	private WebElement billQueueLink;
	

	@FindBy(how = How.LINK_TEXT, using = "Collections Reports")
	private WebElement collectionsReports;

	@FindBy(how =How.XPATH, using="//a[contains(text(), 'Time Critical Exception Queue')]")
	private WebElement timeCriticalExceptionQueue; 

	
	@FindBy(xpath = "//*[contains(text(),'Collection Agency Reports')]")
	private WebElement collectionAgencyReports;
	
	@FindBy(how = How.LINK_TEXT, using = "Cargo Claims Imaging")
	private WebElement ClaimsImaging;
	
	@FindBy(how = How.LINK_TEXT, using = "Digital Picture Viewing")
	private WebElement DigitalPIcView;
	/*********************************************************************************/

	public void clickOnQouteExceptionQueueLink() {

		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("click On VTL Qoute Exception Queue Link");
		QouteExceptionQueue.click();
	}

	public void clickOnMyEstesManagementToolLink() {
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("Click On My Estes Management Tool link");
		myEstesManagementTool.click();
	}

	public void clickOnVTLTableMaintenanceLink() {
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("Click on VTL Table Maintenance link");
		testUtil.setHardWait(1000);
		VTLTableMaintenance.click();
	}

	public void clickOnVTLQuoteExeptionQueueLink() {
		testUtil.init(this);
		logger.info("click On VTL Quote Exeption Queue");
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		testUtil.setHardWait(1000);
		driver.switchTo().frame(frame);
		VTLquoteExeptionQueue.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnRateRetrieverLink() {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		//WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame("mainpage");
		logger.info("click On Rate Retriever Link");
		RateRetriever.click();
	}

	public void clickOnTimeCriticalLink() {
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("click On time Critical Link");
		System.out.println(TimeCritical.getText());
		TimeCritical.click();
	}

	public void clickOnMyEstesManagementTool() {
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("click On My Estes Management Tool Link");
		myEstesManagementTool.click();
	}

	public void clickOnQuoteAuditTrailLink() {
		logger.info("Click On Quote Audit Trail link");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setHardWait(500);
		quoteAuditTrail.click();
	}

	public void clickOnQuoteAuditTrail() {
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		logger.info("click On Quote Audit Trail Link");
		myEstesQuoteAuditTrail.click();
	}

	public void clickOnVTLRateQuoteLink() {
		logger.info("Click on VTL Rate Quote link");
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setExplicitWait(vtlRateQuote, 20);
		vtlRateQuote.click();
	}

	public void clickOnQuoteHistoryLookupLink() {
		logger.info("Click on Quote history lookup link");
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setExplicitWait(quoteHistoryLookup, 20);
		quoteHistoryLookup.click();
	}

	public void clickOnInvoiceInquiryLink() {
		logger.info("Click on Invoice Inquiry link");
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setExplicitWait(invoiceInquiry, 20);
		invoiceInquiry.click();
	}

	public void clickOnClaimsEntryLink() {
		logger.info("click On Claims Entry Link");
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		claimsEntry.click();
	}

	public void clickOnUndeliveredFreight() {
		testUtil.init(this);
		driver.switchTo().frame(0);
		logger.info("Click On Undelivered Freight");
		testUtil.setHardWait(1000);
		undeliveredFreight.click();
	}

	
	public void clickOnTLRateQuoteLink() {
		logger.info("Click on truckload brokerage Rate Quote link");
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setExplicitWait(tlRateQuote, 20);
		tlRateQuote.click();
	}


	public void clickOnquoteHistoryLookupLink() {
		logger.info("Click on Quote history lookup link");
		testUtil.init(this);
		testUtil.setExplicitWait(quoteHistoryLookup, 90);
		quoteHistoryLookup.click();
	}
	
	public void clickOnHazmatLoadingGuide() {
		logger.info("Click on Hazmat Loading Guide link");
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setExplicitWait(HazmatLoadingGuide, 20);
		HazmatLoadingGuide.click();
	}
	
	public void clickMiscellaneousOpenAR() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		MiscellaneousOpenAR.click();
		logger.info("Opened Miscellaneous Open A/R page");
		
	}

	public void clickAccountReceivableFocusAccounts() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		AccountReceivableFocusAccounts.click();
		logger.info("Opened Accounts Receivable - Focus Accounts page");
				
	}

	public void clickUndiscountedFreightReport() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		UndiscountedFreightReport.click();
		logger.info("Opened Undiscounted Freight Report page");
		
	}

	public void clickDelayedPros() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		DelayedPros.click();
		logger.info("Opened Delayed PRO's page");
		
	}

	public void clickFocusAccountMaintenance() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		FocusAccountMaintenance.click();
		logger.info("Opened Focus Account Maintenance page");
		
	}

	public void clickCurrentARAgingBalances() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		CurrentARAgingBalances.click();
		logger.info("Opened  Current A/R Aging Balances (Omit G,I,N,P Classes) page");
		
	}
	
	public void clickInvoiceBatchUpload () {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		InvoiceBatchUpload.click();
		logger.info("Opened Invoice Batch Upload  page");
	}
	
	public void clickStatementRetriever() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		StatementRetriever.click();
		logger.info("Opened Statement Retriever page");
	}
	
	public void clickExchangeRateMaintenance() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		ExchangeRateMaintenance.click();
		logger.info("Opened Exchange Rate Maintenance page");
	}



	public void clickCollections() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		Collections.click();
		logger.info("Opened Collections page");
	}


	public void verifyPageTtl() {
		testUtil.init(this);
		logger.info("Verify Page Title.");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		String pageTtl = pageTitle.getText();
		logger.info("Page title : "+pageTtl);
		Assert.assertEquals(pageTtl, "Applications", "Page Title doesnot match.");
	}
	
	public void clickOnBillQueue() {
		testUtil.init(this);
		logger.info("Click on Bill Queue.");
		billQueueLink.click();
	}
	
	public void clickOnCollectionsReports() {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		logger.info("Click on Collections Reports");
		testUtil.clickElementJavascript(collectionsReports);
		//collectionsReports.click();
}
	
	public void clickOnTimeCriticalExceptionQueue() {
		testUtil.init(this); 
		logger.info("Clicking on Time Critical Exception Queue link"); 
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame("mainpage"); 
		timeCriticalExceptionQueue.click();
	}

	public void clickCollectionsReports() {
		testUtil.init(this);
		logger.info("Click Collections Reports.");
		testUtil.assetWait(null, collectionsReports, 10, 12, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(collectionsReports);
	
	}
	public void clickOnCollectionAgencyReports() {
		testUtil.init(this);
		logger.info("Click On Collection Agency reports link.");
		collectionAgencyReports.click();
	}

	

	public void clickOnCargoClaimsImaging() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		ClaimsImaging.click();
		logger.info("clicked On Cargo Claims Imaging Link");
	}
	
	public void openDigitalPictureViewing() {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		DigitalPIcView.click();
		logger.info("Opened Digital Picture Viewing page");
	}
	
}


