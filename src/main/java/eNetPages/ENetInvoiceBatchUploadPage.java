package eNetPages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class ENetInvoiceBatchUploadPage extends TestBase {
	
	private Logger logger = Logger.getLogger(ENetHomePage.class);

	// Elements
	@FindBy(how = How.XPATH, using = "//table[1]/tbody/tr/td[1]")
	private List<WebElement> TableFields;

	@FindBy(how = How.XPATH, using = "//*[text()='Invoice Batch Upload']")
	private WebElement PageTitle ;

	@FindBy(how = How.ID, using = "emailInvoices")
	private WebElement EmailInvoice;
	
	@FindBy(how = How.ID, using = "subjectLine")
	private WebElement SubjectLine;
	
	@FindBy(how = How.ID, using = "proFormat1")
	private WebElement ProFormat1;
	
	@FindBy(how = How.ID, using = "proFormat2")
	private WebElement ProFormat2;
	
	@FindBy(how = How.ID, using = "emailErrors")
	private WebElement EmailErrors;
	
	@FindBy(how = How.ID, using = "emailFrom")
	private WebElement EmailFrom;
	
	@FindBy(how = How.ID, using = "uploadFile")
	private WebElement UploadFile;
	
	@FindBy(how = How.ID, using = "batchSubmit")
	private WebElement BatchSubmit;
	
	@FindBy(how = How.ID, using = "runtimeSuccess")
	private WebElement SuccessMessage;
	
	
	/************************************Methods*************************************/
	
	public void validatePageTitle(String expTitle) {
		testUtil.init(this);
		logger.info("Verifying page title");
		String title = PageTitle.getText();
		Assert.assertTrue(title.contains(expTitle));
		logger.info("Page Title is " + title);
	}
	
	public void verifyTableFields(String[] fields) {
		testUtil.init(this);
		logger.info("Verifying table feilds");
		for(int i=0;i<TableFields.size();i++) {
			String value = TableFields.get(i).getText();
			assertEquals(value, fields[i]);
			logger.info("Field "+value+" is displayed");
		}
	}
	
	public void enterEmailErrors(String email) {
		testUtil.init(this);
		logger.info("Enter Email error report to: "+email);
		EmailErrors.clear();
		EmailErrors.sendKeys(email);
	}
	
	public void enterEmailInvoice(String email) {
		testUtil.init(this);
		logger.info("Enter Email Invoice:  "+email);
		EmailInvoice.clear();
		EmailInvoice.sendKeys(email);
	}
	
	public void enterEmailFrom(String email) {
		testUtil.init(this);
		logger.info("Enter Email From: "+email);
		EmailFrom.clear();
		EmailFrom.sendKeys(email);
	}
	
	public void enterSubjectInfo(String info) {
		testUtil.init(this);
		logger.info("EnterSubject Info: "+info);
		testUtil.assetWait(null, SubjectLine, 10, 250, TimeUnit.MILLISECONDS);
		SubjectLine.clear();
		SubjectLine.sendKeys(info);
	}
	
	public void uploadFile(String path) {
		testUtil.init(this);
		logger.info("Uploading  file... ");
		testUtil.assetWait(null, UploadFile, 10, 250, TimeUnit.MILLISECONDS);
		UploadFile.clear();
		UploadFile.sendKeys(path);
	}
	
	public void clickonSubmit() {
		testUtil.init(this);
		logger.info("Click on sublit button");
		testUtil.assetWait(null, BatchSubmit, 10, 250, TimeUnit.MILLISECONDS);
		BatchSubmit.click();
	}
	
	public void validateSuccessMessage(String message) {
		
		testUtil.init(this);
		logger.info("Validate Success message: "+message);
		String mess=SuccessMessage.getText();
		assertEquals(mess, message);
	}
	public void selectProFormat1() {
		testUtil.init(this);
		logger.info("Click on Pro Format 1");
		testUtil.assetWait(null, ProFormat1, 12, 250, TimeUnit.MICROSECONDS);
		if(ProFormat1.isSelected())
			logger.info("Pro format is selected in default");
		else
			testUtil.clickElementJavascript(ProFormat1);
		//	ProFormat1.click();
		testUtil.assetWaitDisplayed(ProFormat1, 10, 250, TimeUnit.MILLISECONDS);
		assertEquals(ProFormat1.isSelected(), true);
		
	}
	
	public void clickOnLogout() {
		logger.info("Logging out from Enet");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		WebElement ele = driver.findElement(By.xpath("//a[@id='logout']"));
		testUtil.WaitForTheElementClickable(ele);
		ele.click();
		testUtil.setHardWait(1000);

	}

	public void clickOnLogoutIntranet() {
		logger.info("Logging out from Intranet");
		testUtil.init(this);
		driver.switchTo().frame(0);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Logout']")));
		ele.click();
	}
	
	
	
}
