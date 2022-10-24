package myEstesPages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesRequestAccountNumberPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesRequestAccountNumberPage.class);

	@FindBy(how = How.ID, using = "inputUserName")
	private WebElement weRANYourName;

	@FindBy(how = How.ID, using = "inputCompany")
	private WebElement weRANCompanyName;

	@FindBy(how = How.ID, using = "inputEmail")
	private WebElement weRANEmail;

	@FindBy(how = How.ID, using = "inputPhone")
	private WebElement weRANPhone;

	@FindBy(how = How.ID, using = "inputRequestedCompany")
	private WebElement weReqCompName;
	
	@FindBy(how = How.ID, using = "inputAddresses")
	private WebElement weLocAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@type='submit']")
	private WebElement weSubmit;
	// Message
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Your message has been sent')]")
	private WebElement weRANMessage;
	// *[@id="content"]/div/div[1]/lib-request-account-number/mat-card/mat-card-content/form/div[7]
	@FindBy(css="[id='content'] h1")
	private WebElement requestAccountNumberText;
	
	/*************************METHODS***************************/

	public void enterRANYourName(String RANYourName) {
		logger.info("Enter request account number your name");
		testUtil.init(this);
		weRANYourName.sendKeys(RANYourName);
	}

	public String enterRANCompanyName(String RANCompanyName) {
		logger.info("Enter request account number company name");
		testUtil.init(this);	
		weRANCompanyName.sendKeys(RANCompanyName);
		testUtil.setHardWait(1000);
		String comName=weRANCompanyName.getAttribute("value");
		System.out.println("Company Name is : " + comName);
		return comName;
		
	}

	public void enterRANEmail(String RANEmail) {
		logger.info("Enter request account email address");
		testUtil.init(this);
		weRANEmail.sendKeys(RANEmail);
	}

	public void enterRANPhone(String RANPhone) {
		testUtil.init(this);
		logger.info("Enter request account phone number");
		weRANPhone.sendKeys(RANPhone);
	}

	public void enterReqCompName(String ReqCompName) {
		testUtil.init(this);
		logger.info("Enter request company name");
		weReqCompName.sendKeys(ReqCompName);
	}

	public void enterLocAddress(String LocAddress) {
		testUtil.init(this);
		logger.info("Enter location Address(es)(one per line)");
		WebDriverWait wait= new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputAddresses")));
		weLocAddress.sendKeys(LocAddress);
	}

	public void clickOnSubmit() {
		testUtil.init(this);
		logger.info("Click On submit button");
		testUtil.assetWait(null, weSubmit, 13, 200, TimeUnit.MILLISECONDS);
		//WebDriverWait wait= new WebDriverWait(driver, 120);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='submit']")));
		weSubmit.click();
		
		
	}

	public String getTextMessage() {
		testUtil.init(this);
		logger.info("Verify RAN message after submit");
		testUtil.setHardWait(2000);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Your message has been sent')]")));
		String RANTextMessage = weRANMessage.getText();
		return RANTextMessage;
		
	}

	public void verifyRequiredFieldsErrorMessage() {
		testUtil.init(this);
		logger.info("Validate error message displays");
		testUtil.verifyAndPrintWebTableData("//form[@name='accountForm']");
		
	}

	public void verifyPageTilte() {
		testUtil.init(this);
		logger.info("Display the page title");
		String title = driver.getTitle();
		System.out.println(title);

	}
	
	public void verifyRequestAccountNumberPage() {
		testUtil.init(this);
		logger.info("Verify Request Account number page ");
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(requestAccountNumberText, 60);
		String requestAccNo = requestAccountNumberText.getText();
		Assert.assertEquals(requestAccNo, "Request Account Number");
	}
	

}
