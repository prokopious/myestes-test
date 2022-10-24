package invoiceInquiryPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import myEstesPages.MyEstesPickupRequestPage;
import testBase.TestBase;
import util.TestUtil;

public class MyEstesPayInvoicesPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesPickupRequestPage.class);
	
	@FindBy(xpath="//span[text()='Pay Invoices']")
	private WebElement payInvoiceTitle;
	
	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	private WebElement cancelButton;
	
	@FindBy(css="[class=mat-slide-toggle-bar]")
	private WebElement adjustAmountToggle;
	
	@FindBy(css="[formcontrolname='reasonCode']")
	private WebElement reasonCode;
	
	@FindBy(xpath="//span[text()='Other']")
	private WebElement otherReason;
	
	@FindBy(css="[formcontrolname='explain']")
	private WebElement comments;
	
	@FindBy(xpath="//button[contains(text(),'Verify')]")
	private WebElement verifyButton;
	
	@FindBy(css="[class*='mat-header-row'] button")
	private List<WebElement> payInvoiceTableHeader; 
	
	public void verifyPageTitle() {
		logger.info("Verifying Pay Invoices Page title");
		testUtil.init(this);
		TestUtil.waitForPageToLoad();
		boolean actualPageTitle = payInvoiceTitle.isDisplayed();
		Assert.assertTrue(actualPageTitle, "Failed to display 'Pay Invoices' Page");
	}

	public void clickOnCancel() {
		logger.info("Clicking on Cancel button");
		testUtil.init(this);
		testUtil.setExplicitWait(cancelButton, 20);
		testUtil.scrollWebPageToBottom();
		testUtil.clickElementJavascript(cancelButton);
		//cancelButton.click();
	}
	
	public void clickOnAdjustAmountToggle() {
		logger.info("Clicking on Adjust Amount toggle bar");
		testUtil.init(this);
		testUtil.setExplicitWait(adjustAmountToggle, 20);
		adjustAmountToggle.click();
	}
	
	public void clickOnReasonCode() {
		logger.info("Clicking on Reason code");
		testUtil.init(this);
		testUtil.setExplicitWait(reasonCode, 20);
		reasonCode.click();
	}

	public void selectOtherReasonCode() {
		logger.info("Clicking on Other Reason code");
		testUtil.init(this);
		testUtil.setExplicitWait(otherReason, 20);
		otherReason.click();
	}
	
	public void enterComments(String commentsText) {
		logger.info("Entering comments");
		testUtil.init(this);
		testUtil.setExplicitWait(comments, 20);
		comments.sendKeys(commentsText);
	}
	
	public void clickOnVerify() {
		logger.info("Clicking on Verify button");
		testUtil.init(this);
		testUtil.setExplicitWait(verifyButton, 20);
		testUtil.scrollWebPageToBottom();
		testUtil.clickElementJavascript(verifyButton);
		//verifyButton.click();
	}
	
	public void validateWesternUnionPaymentPage() {
		logger.info("Verify 'Western Union Speed Pay' Payment page");
		testUtil.init(this);
		TestUtil.waitForPageToLoad();
		testUtil.setHardWait(3000);
		String currentTitle = testUtil.getCurrentPageTitle();
		Assert.assertTrue(currentTitle.contains("speedpay.com"));	
	}
	
	public void verifyPayInvoiceDetailsColumnHeader(String[] expectedPayInvoiceHeader) {
		logger.info("Verifying Pay Invoice Details Table Column header");
		testUtil.init(this);
		boolean flagVal = false;
		for(int i=0;i<payInvoiceTableHeader.size();i++) {
			String actualColName = payInvoiceTableHeader.get(i).getText().trim();
			if(actualColName.equalsIgnoreCase(expectedPayInvoiceHeader[i])) {
				flagVal = true;
				TestUtil.verifyTrue(flagVal);
			}
		} if(!flagVal) {
			try {
				throw new Exception(new String("Failed to validate Column Name"));
			}catch(Exception e) {}
		}
	}
	
}

