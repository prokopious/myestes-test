package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetQuoteSummaryDetailsPage extends TestBase {

private Logger logger = Logger.getLogger(ENetQuoteSummaryDetailsPage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@id= 'app_wrapper']/div[2]//h2")
	private WebElement quoteSummaryPageTitle;
	
	@FindBy(how = How.ID, using = "faxNumber")
	private WebElement FaxNumber;
	
	@FindBy(how = How.ID, using = "email_addresses")
	private WebElement EmailAddress;
	
	@FindBy(how = How.XPATH, using = "//input[@name ='sendFax']")
	private WebElement clickOnFaxQuote;
	
	@FindBy(how = How.XPATH, using = "//input[@name ='sendEmail']")
	private WebElement clickOnEmailAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content_ENET']/div[2]/div[1]/p")
	private WebElement eMailSuccessMessage;
	
	/**************************METHODS*********************/
	
	public void verifyQuoteSummaryPage() {
		testUtil.init(this);
		logger.info("Qutoe History Lookup Page displayed successfully");

		String expectedTitle = "Quote Detail Summary";
		String actualTitle = quoteSummaryPageTitle.getText();

		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	
	public void clickOnFaxQuoteButton() {
		logger.info("Click on Fax Quote button");
		testUtil.init(this);
		testUtil.setExplicitWait(clickOnFaxQuote, 20);
		clickOnFaxQuote.click();
		
	}

	public void enterEmailAddress(String emailAddress) {
		logger.info("Enter email address: "+emailAddress);
		testUtil.init(this);
		testUtil.setExplicitWait(EmailAddress, 20);
		EmailAddress.clear();
		EmailAddress.sendKeys(emailAddress);
		
	}

	public void clickOnEmailAddressButton() {
		logger.info("Click on email address button");
		testUtil.init(this);
		testUtil.setExplicitWait(clickOnEmailAddress, 20);
		clickOnEmailAddress.click();
		
	}

	public void verifyEmailAddressSuccessMessage() {
		testUtil.init(this);
		logger.info("Verif success message is displayed");
		String successMsg = eMailSuccessMessage.getText().trim();
		Assert.assertEquals(successMsg, "Success: Your quote has been sent to the email address(es) that you entered.");
		
	}

	public void enterFaxQuoteNumber(String faxNumber) {
		logger.info("Enter email address: "+FaxNumber);
		testUtil.init(this);
		testUtil.setExplicitWait(FaxNumber, 20);
		FaxNumber.clear();
		FaxNumber.sendKeys(faxNumber);
		
	}
	
}


