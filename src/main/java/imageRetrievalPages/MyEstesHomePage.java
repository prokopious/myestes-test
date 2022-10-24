package imageRetrievalPages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesHomePage extends TestBase {

	
	//DON'T USE THIS PAGE FOR <MYESTES HOME PAGE> USE THE MAIN ONE. THIS WILL BE MERGED IN TO THE MAIN PAGE					
	
 	private Logger logger = Logger.getLogger(MyEstesHomePage.class);

	// Account Number not found Message
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Account number not found')]")
	private WebElement weAccNumNotFoundMess;

	@FindBy(how = How.XPATH, using = "//*[@id=\"tab-tools\"]/div/a")
	private WebElement toolsLink;

	@FindBy(how = How.ID, using = "my-estes-dropdown")
	private WebElement myEstes;

	@FindBy(how = How.XPATH, using = "//li[@id='tab-resources']//div[1]")
	private WebElement resources;

	// ########################## My Estes ##########################
	// Edit My Profile From My Estes
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[1]")
	private WebElement weEditMyProfileFromMyEstes;
	// Request Account Number From My Estes
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[2]")
	private WebElement weReqAccNumFromMyEstes;
	// Address Book From My Estes
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[3]")
	private WebElement weAddBookFromMyEstes;
	// Commodity Library From My Estes
	// Logout
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[5]")
	private WebElement weLogoutFromMyEstes;
	// CONFIRM Button
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Confirm')]")
	private WebElement weConfirmButton;
	// Submit Button
	@FindBy(how = How.XPATH, using = "//*[contains(@type, 'submit')]")
	private WebElement weSubmitButton;
	// Account Lookup
	@FindBy(how = How.ID, using = "addressSelect")
	private WebElement weAccLookup;

	// ########################## Ship ##########################
	// Click on Ship
	@FindBy(how = How.ID, using = "dropdown-0")
	private WebElement weShipLink;
	// Bill of Lading From Ship
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Bill of Lading')]")
	private WebElement weBillOfLading;
	// Terminal lookup From Ship
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Terminal Lookup')]")
	private WebElement weTerminalLookup;
	// Density Calculator From Ship
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Density Calculator')]")
	private WebElement weDensityCalculator;
	// Transit Time Calculator From Ship
	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'[contains(text(),'Transit Time Calculator')]")
	private WebElement weTransitTimeCal;

	// Click on Track
	// ########################## Manage ##########################
	// Click on Manage
	// @FindBy(linkText = "Manage")
	@FindBy(how = How.LINK_TEXT, using = "Manage")
	private WebElement weManageLink;
	// Claims from Manage
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[1]/li[3]/div/a[7]")
	private WebElement weClaimsFromManage;
	// Invoices from Manage
	@FindBy(how = How.XPATH, using = "(//a[contains(text(),'Invoices')])[1]")
	private WebElement weInvoicesFromManage;

	@FindBy(linkText = "Document Retrieval & Viewing")
	private WebElement weImageRetriAndViewingFromManage;

	// ########################## Services ##########################

	// ########################## Resources ##########################

	// Click on Resources
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Resources')])[1]")
	private WebElement weResourcesLink;
	// Fuel Surcharge from Resources
	@FindBy(how = How.XPATH, using = "//*[@id=\"main-nav\"]/ul[1]/li[5]/div/a[3]")
	private WebElement weFuelSurcharge;
	// Click on Contact

	// First Account#
	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/tbody/tr[1]/td[1]/a")
	private WebElement weFirstAccountLink;

	// ########################## Contact ##########################
	////
	// ########################## My Estes ##########################
	// Click on Edit My Profile From My Estes
	public void clickOnEditMyProfileFromMyEstes() {
		logger.info("Click on Edit My Profile From My Estes");
		testUtil.init(this);
		weEditMyProfileFromMyEstes.click();
	}

	// Click On Request Account Number From My Estes
	public void clickOnReqAccNumFromMyEstes() {
		logger.info("Click on Request Account Number From My Estes");
		testUtil.init(this);
		weReqAccNumFromMyEstes.click();
	}

	// Click On Address Book From My Estes
	public void clickOnAddBookFromMyEstes() {
		logger.info("Click on Address Book From My Estes");
		testUtil.init(this);
		weAddBookFromMyEstes.click();
	}

	public void clickOnLogin() {
		testUtil.init(this);
		driver.findElement(By.partialLinkText("Login")).click();
		logger.info("click on Login button");
	}

	// Commodity Library From My Estes
	// Click on Logout From My Estes
	public void clickOnLogout() {
		logger.info("Click on Logout Button From My Estes");
		testUtil.init(this);
		weLogoutFromMyEstes.click();
	}

	// Verify Account Number Not Found Message
	public void verifyAccNumNotFoundMessDisplayed() {
		testUtil.init(this);
		logger.info("Verify Account Number Not Found Message");
		weAccNumNotFoundMess.isDisplayed();
	}

	// Enter Account Lookup
	public void enterAccountLookup(String accountNum) {
		logger.info(" Enter Account Lookup");
		testUtil.init(this);
		weAccLookup.sendKeys(accountNum);
	}

	// Click On CONFIRM Button
	public void clickOnConfirmButton() {
		logger.info("Click on CONFIRM Button");
		testUtil.init(this);
		weConfirmButton.click();
	}

	// Click on Submit Button
	public void clickOnSubmitButton() {
		logger.info("Click on Submit Button");
		testUtil.init(this);
		weSubmitButton.click();
	}

	// Copy account Number
	public String getAccNumForFirstRow() {
		String accNumber = driver.findElement(By.xpath("//*[@class='mat-card-content']/table/tbody/tr[1]/td[1]/a"))
				.getText();
		logger.info("Account Number: " + accNumber);
		return accNumber;
	}

	// ########################## Ship ##########################
	// Click On Ship Link
	public void clickOnShipLink() {
		logger.info("Clicking on Ship Link");
		testUtil.init(this);
		weShipLink.click();
	}

	// Click on Terminal lookup From Ship
	public void clickOnTerminallookupFromShip() {
		logger.info("Click on Terminal lookup From Ship");
		testUtil.init(this);
		weTerminalLookup.click();
	}

	// Click on Bill of Lading From Ship
	public void clickOnBillOfLadingFromShip() {
		logger.info("Click on Bill of Lading From Ship");
		testUtil.init(this);
		weBillOfLading.click();
	}

	// Click on Density Calculator From Ship
	public void clickOnDensityCalculatorFromShip() {
		logger.info("Click on Density Calculator From Ship");
		testUtil.init(this);
		weDensityCalculator.click();
	}

	// Click On Transit Time Calculator From Ship
	public void clickOnTransitTimeCalFromShip() {
		logger.info("Click On Transit Time Calculator From Ship");
		testUtil.init(this);
		weTransitTimeCal.click();
	}

	// ########################## Manage ##########################
	// Click on Manage
	public void clickOnManageTab() {
		logger.info("Click on Manage tab");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(weManageLink);
	}

	// Click on Claims From Manage
	public void clickOnClaimsFromManage() {
		logger.info("Click on Claims from Manage");
		testUtil.init(this);
		weClaimsFromManage.click();
	}

	// Click on Invoices From Manage
	public void clickOnInvoicesFromManage() {
		logger.info("Click on Invoices from Manage");
		testUtil.init(this);
		weInvoicesFromManage.click();
	}

	// Click On Image Retrieval & Viewing From Manage
	public void clickOnDocumentRetrievalAndViewingFromManage() {
		logger.info("Click on Image Retrieval & Viewing from Manage");
		testUtil.init(this);
		weImageRetriAndViewingFromManage.click();
		testUtil.setHardWait(1000);

	}

	// ########################## Services ##########################

	// ########################## Resources ##########################

	// Click on Resources Link
	public void clickOnResourcesLink() {
		logger.info("Click on Resources Link");
		testUtil.init(this);
		weResourcesLink.click();
	}

	// Click on Fuel Surcharge from Resources
	public void clickOnFuelSurcharge() {
		logger.info("Click on Fuel Surcharge from Resources");
		testUtil.init(this);
		weFuelSurcharge.click();
	}

	public void clickOnTools() {
		testUtil.init(this);
		toolsLink.click();
		logger.info("clicking on tools button");
	}

	public void clickOnMyEstes() {
		testUtil.init(this);
		myEstes.click();
		logger.info("clicking on MyEstes button");
		myEstes.click();
	}

	public void clickOnResources() {
		testUtil.init(this);
		resources.click();
		logger.info("clicking on Resources button ");
		resources.click();
	}

	// Click On First Account# Link
	public void clickOnFirstAccountNumLink() {
		testUtil.init(this);
		logger.info("Click On First Account# Link");
		weFirstAccountLink.click();
	}
	// ########################## Contact ##########################

	public void writeToASpecificCell(String path, String sheetName, int rownum, int cellnum, String result) {
		// Specify the file path which you want to create or write
		File src = new File(path);
		try (// Load the file
				FileInputStream fis = new FileInputStream(src);
				// load the workbook
				XSSFWorkbook wb = new XSSFWorkbook(fis);) {
			// get the sheet which you want to modify or create
			XSSFSheet sh1 = wb.getSheet(sheetName);
			sh1.getRow(rownum).createCell(cellnum).setCellValue(result);
			FileOutputStream fout = new FileOutputStream(new File(path));
			// finally write content
			wb.write(fout);
			// close the file
			fout.close();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

}
