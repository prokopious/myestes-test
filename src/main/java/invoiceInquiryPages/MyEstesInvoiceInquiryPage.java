package invoiceInquiryPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import myEstesPages.MyEstesPickupRequestPage;
import testBase.TestBase;
import util.TestUtil;

public class MyEstesInvoiceInquiryPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesPickupRequestPage.class);
	
	@FindBy(xpath="//span[text()='Invoice Inquiry']")
	private WebElement invoiceInquiryTitle;
	
	//@FindBy(css="[class*='alert-danger'] span")
	@FindBy(xpath="//mat-card-content/div/div[3]/lib-feedback/div/span[contains(text(),'There are no open invoices for this account at this time.')]")
	private WebElement alertMessage; 
	
	@FindBy(css="[formcontrolname='searchType']")
	private WebElement searchBy;
	
	@FindBy(xpath="//span[text()='Statement Number']")
	private WebElement statementNumber;
	
	@FindBy(xpath="//span[text()='PO Number']")
	private WebElement poNumber;
	
	@FindBy(xpath="//span[text()='BOL Number']")
	private WebElement bolNumber;

	@FindBy(css="[type='submit']")
	private WebElement searchButton;
	
	@FindBy(css="[class*='error']")
	private WebElement requiredFieldError;
	
	@FindBy(xpath="//a[contains(text(),'View All Open')]")
	private WebElement viewAllOpenInvoice;
	
	@FindBy(css="mat-header-row div[class*='mat-checkbox-inner-container']")
	private WebElement invoiceAgingHeaderCheckbox;	
	
	@FindBy(css="[class='mat-table']>mat-row")
	private List<WebElement> invoiceAgingTableDetails;
	
	@FindBy(xpath="//button[contains(text(),'Pay Selected')]")
	private WebElement paySelectedButton;
	
	@FindBy(css="[class*='mat-header-row'] button")
	private List<WebElement> invoiceInquiryTableHeader;
	
	@FindBy(css="mat-row")
	private List<WebElement> rowLists;
	
	@FindBy(id="mat-input-0")
	private WebElement PROField; 
	
	@FindBy(xpath="//button[@class='btn btn-default ml-2']")
	private WebElement ClearButton; 
	
	public void verifyPageTitle() {
		logger.info("Verifying Invoice Inquiry Page title");
		testUtil.init(this);
		TestUtil.waitForPageToLoad();
		boolean actualPageTitle = invoiceInquiryTitle.isDisplayed();
		Assert.assertTrue(actualPageTitle, "Failed to display 'Invoice Inquiry' Page");
	}
	
	public String captureAlertMessage() {
		logger.info("Capturing displayed Alert message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertMessage, 30);
		return testUtil.getTextOfElement(alertMessage);
	}
	
	public void clickOnSearchBy() {
		logger.info("Clicking on Search By");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(searchBy);
		//searchBy.click();
	}
	
	public void selectStatementNumber() {
		logger.info("Selecting Statement Number from Search By");
		testUtil.init(this);
		testUtil.setExplicitWait(statementNumber, 20);
		statementNumber.click();
	}
	
	public void selectPONumber() {
		logger.info("Selecting PO number from Search By");
		testUtil.init(this);
		testUtil.setExplicitWait(poNumber, 20);
		poNumber.click();
	}
	
	public void selectBOLNumber() {
		logger.info("Select BOL number from Search By");
		testUtil.init(this);
		testUtil.setExplicitWait(bolNumber, 20);
		bolNumber.click();
	}
	
	public void clickOnSearch() {
		logger.info("Click on Search button");
		testUtil.init(this);
		testUtil.setExplicitWait(searchButton, 20);
		testUtil.clickElementJavascript(searchButton);
		//searchButton.click();
	}
	
	public void verifyRequiredFieldErrorMessage() {
		logger.info("Verify Required field error message");
		testUtil.init(this);
		testUtil.setExplicitWait(requiredFieldError, 30);
		boolean existence = requiredFieldError.isDisplayed();
		Assert.assertTrue(existence);
	}
	
	public void clickOnViewAllOpenInvoice() {
		logger.info("Click on View All Open Invoice link");
		testUtil.init(this);
		viewAllOpenInvoice.click();
	}
	
	public void selectAllRecordsInInvoiceTable() {
		logger.info("Select all records from Invoice Aging Details Table");
		testUtil.init(this);
		WebElement element= driver.findElement(By.xpath("//mat-row//td[1]"));
		testUtil.setExplicitWait(element, 60);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",element);
		
	
		testUtil.setExplicitWait(invoiceAgingHeaderCheckbox, 20);
		if(invoiceAgingTableDetails.size()>0) {
			invoiceAgingHeaderCheckbox.click();
		} else {
			try {
				throw new Exception(new String("Failed to display Invoice Inquiry details"));
			} catch (Exception e) {}
		}
	}
	
	public void clickOnPaySelected() {
		logger.info("Clicking on Pay Selected button");
		testUtil.init(this);
		testUtil.setHardWait(5000);
		testUtil.WaitForTheElementClickable(paySelectedButton);
		testUtil.setExplicitWait(paySelectedButton, 120);
		testUtil.clickElementJavascript(paySelectedButton);
		//paySelectedButton.click();
	}
	
	public void verifyInvoiceInquiryDetailsColumnHeader(String[] expectedInvoiceInquiryHeader) {
		logger.info("Verifying Invoice Inquiry Details Table Column header");
		testUtil.init(this);
		boolean flagVal = false;
		testUtil.setHardWait(2000);
		for(int i=0;i<invoiceInquiryTableHeader.size();i++) {
			String actualColName = invoiceInquiryTableHeader.get(i).getText().trim();
			if(actualColName.equalsIgnoreCase(expectedInvoiceInquiryHeader[i])) {
				flagVal = true;
				TestUtil.verifyTrue(flagVal);
			}
		} if(!flagVal) {
			try {
				throw new Exception(new String("Failed to validate Column Name"));
			}catch(Exception e) {}
		}
	}
	
	public void selectInvoiceWithAnOpenAmount() {
		logger.info("Selecting Invoice with an Open Amount");
		testUtil.init(this);
		for(int col=0;col<invoiceInquiryTableHeader.size();col++) {
			String columnName = invoiceInquiryTableHeader.get(col).getText().trim();
			if(columnName.equalsIgnoreCase("Pending Pay")) {
				for(int row=0;row<rowLists.size();row++) {
					String pendingPay = testUtil.getWebElement(rowLists.get(row),"mat-cell:nth-child("+(col+2)+")").getText().trim();
					if(pendingPay.equals("$0.00")) {
						testUtil.getWebElement(rowLists.get(row),"[class*='mat-checkbox-inner']").click();
						break;
					}}
				} else {
					logger.info("Failed to display Pending Pay amount column");
				}
			} 
	}
	
	public void enterPRONumber(String PRO){	
		logger.info("Enter PRO Number");
		testUtil.init(this);
		PROField.sendKeys(PRO);	
		testUtil.setHardWait(500);
		PROField.sendKeys(Keys.ENTER);
	} 
	
		public void verifyErrorMessage() {
				logger.info("Validate error messages:::");
				boolean error=driver.findElement(By.xpath("//div[@class='ng-star-inserted alert alert-danger']")).isDisplayed();
				System.out.println( driver.findElement(By.xpath("//div[@class='ng-star-inserted alert alert-danger']")).getText());
				Assert.assertTrue(error);
			} 
		 

		public void clickOnClearButton() {
			logger.info("Click on Clear button");
			testUtil.init(this);
			testUtil.setExplicitWait(ClearButton, 20);
			testUtil.clickElementJavascript(ClearButton);
		} 
}
