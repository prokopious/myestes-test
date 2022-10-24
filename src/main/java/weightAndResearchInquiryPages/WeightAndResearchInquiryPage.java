package weightAndResearchInquiryPages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class WeightAndResearchInquiryPage extends TestBase {

	private Logger logger = Logger.getLogger(WeightAndResearchInquiryPage.class);

	@FindBy(how = How.ID, using = "searchType")
	private WebElement selectBy;

	@FindBy(how = How.ID, using = "searchTerm")
	private WebElement PROField;

	//@FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div/div[1]/app-weight-and-research/mat-card[1]/mat-card-content/div[4]/button[1]")
	@FindBy(how = How.XPATH, using = "//*[@id='main']/app-weight-and-research/mat-card[1]/mat-card-content/div[4]/button[1]")
	private WebElement Search;
	
	//@FindBy(xpath = "//*[@id='my-element']")
	@FindBy(xpath = "//*[@id='selectElement']/div/div[1]/div[3]")
	private WebElement AccountSearch;
	
	@FindBy(how = How.ID, using = "mat-radio-2-input")
	private WebElement DateRange;
	
	@FindBy(how = How.ID, using = "mat-radio-3-input")
	private WebElement PRO;
	
	@FindBy(how = How.ID, using = "mat-radio-4-input")
	private WebElement Other;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-checkbox-2\"]/label/div")
	private WebElement PROCheckBox;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Email Selected')]")
	private WebElement EmailSelected;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Send')]")
	private WebElement SendBtn;
	
	@FindBy(how = How.ID, using = "emailAddresses")
	private WebElement EmailAddressField;
	
	@FindBy(how=How.XPATH,using="//*[@id='mat-input-5']")
	private WebElement searchFiled1;
	
	@FindBy(how=How.XPATH,using="//*[@id='mat-input-7']")
	private WebElement searchFiled2;
	/************************************METHODS*************************************/

	public void selectPRO() {
		testUtil.init(this);
		logger.info("Select PRO");
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(PRO);
		//PRO.click();
		
	}
	
	public void selectDateRange() {
		logger.info("Select Date Range");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(DateRange);
	//	DateRange.click();
	}
	
	public void selectOther() {
		logger.info("Select Other");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(Other);
	//	Other.click();
	}
	
	public void selectPROCheckBox() {
		logger.info("select PRO check box");
		testUtil.init(this);
		PROCheckBox.click();
	}

	public void enterSearchItems(String PRONumber) throws InterruptedException {
		logger.info("enter Search Items");
		testUtil.init(this);
		PROField.clear();
		Thread.sleep(500);
		PROField.sendKeys(PRONumber);
		Thread.sleep(2000);
		
	}
	
	//this method is to enter account number  separately 
	
	
	public void enterSearchItemsInSeparteLines() throws InterruptedException {
		logger.info("Enter account number in separate lines");
		testUtil.init(this);
		
		WebElement ele=driver.findElement(By.id("searchTerm"));
		//ele.sendKeys(Keys.ENTER);
		ele.sendKeys("1\n 2\n 3\n 3\n 4\n 3");
		
	
	}
	
	public void enterEmailAddress(String email) throws InterruptedException {
		logger.info("enter Email Address");
		testUtil.init(this);
		EmailAddressField.clear();
		Thread.sleep(500);
		EmailAddressField.sendKeys(email);
		Thread.sleep(4000);
	}

	public void clickOnSearch() throws InterruptedException {
		testUtil.init(this);		
		testUtil.WaitForTheElementClickable(Search);
		logger.info("click on Search Button");
		//Search.click();
		testUtil.clickElementJavascript(Search);
	
	}
	
	public void clickOnSearchButton() throws InterruptedException {
		testUtil.init(this);		
		testUtil.WaitForTheElementClickable(Search);
		logger.info("click on Search Button");
		Search.click();
		Thread.sleep(500);
	}
	
	 public void clickOnAccountSearch() {
	        testUtil.init(this);
	        logger.info("click on Account Search ");
	       // WebElement searchField = testUtil.filterSelector("span", null,null, 0, "Account #");
	        WebElement searchField= driver.findElement(By.xpath("//*[@id='accountSelectId']/div/div[1]/span"));
	        searchField.click();
	        testUtil.setHardWait(3000);
	    }
	
	  public void clickOnAnyAccountNumber() {
	        testUtil.init(this);
	        logger.info("click on Any Account Number");
	        WebElement accountNumTableRow = testUtil.filterSelector("tr", null, null, 3,  "accounts-row");
	        accountNumTableRow.click();
	        testUtil.setHardWait(500);
	    }
	

	public void clickOnEmailSelectedButton() {
		testUtil.init(this);
		logger.info("click on Email Selected Button");
		testUtil.WaitForTheElementClickable(EmailSelected);
		EmailSelected.click();		
	}
	
	public void clickOnSendButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("click on Send Button");
		testUtil.WaitForTheElementClickable(SendBtn);
		SendBtn.click();	
		Thread.sleep(2000);
	}
	
	public void validateAccountNumberIsDisplayedInTextField() {
		   logger.info("validate Account number is Displayed in Text field");
	        testUtil.init(this);
	        String accountNum = testUtil.filterXpath("//*[text()=\"Account Number\"]/following-sibling::td", null, null, 1, "td").getText();
	        WebElement searchField = testUtil.filterSelector("mat-select-trigger", null, null, 5,"span", accountNum);
	        System.out.println(searchField.getText());
	        Assert.assertTrue(searchField.isDisplayed());
	
	}
	
	public void validateError(String expected) {
		logger.info("validate Error Message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//snack-bar-container[@role='alert']")).getText();
		System.out.println("error message is : " + error);
		testUtil.verifyText(error, expected);			
	}
	
	public void validateErrorMassage(String expected) {
		logger.info("validate Error Message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//snack-bar-container/lib-snackbar-message")).getText();
		System.out.println("Error message is : " + error);
		testUtil.verifyText(error, expected);			
	}
	
	//*[@id="cdk-overlay-6"]/snack-bar-container/lib-snackbar-message/
	
	
	public void validateAccountNumberFieldError(String expected1) {
		logger.info("Validate Date Range Error Message");
		testUtil.init(this);
		String error1 = driver.findElement(By.xpath("//snack-bar-container[@role='alert']")).getText();
		System.out.println("error message is : " + error1);
		testUtil.setHardWait(1000);
		Assert.assertEquals(error1, expected1);
		
	}
	
	public void validateEnterEmailFieldError() {
		logger.info("validate Error Message");
		testUtil.init(this);
		String error = driver.findElement(By.id("mat-error-4")).getText();
		System.out.println("error message is : " + error);
		assertEquals(error, "One or more email addresses is not valid.");
		
	}
	
	public void validateDateRangeError() {
		logger.info("Validate Date Range Error Message");
		testUtil.init(this);
		
		WebElement error = driver.findElement(By.xpath("//mat-error[@id='mat-error-2']"));
		testUtil.setExplicitWait(error, 60);
		String msg=error.getText();
		testUtil.setHardWait(1000);
		
		WebElement error1 = driver.findElement(By.xpath("//mat-error[@id='mat-error-0']"));
		testUtil.setExplicitWait(error1, 60);
		String msg1=error1.getText();
		testUtil.setHardWait(1000);
		
		WebElement error2 = driver.findElement(By.xpath("//mat-error[@id='mat-error-1']"));
		testUtil.setExplicitWait(error2, 60);
		String msg2=error2.getText();
		testUtil.setHardWait(1000);
		
		System.out.println("The error message is : " + msg);
		assertEquals(msg, "This field is required.");
		assertEquals(msg1, "This field is required.");
		assertEquals(msg2, "This field is required.");
		
	}
	
	public void validatePROError() throws InterruptedException {
		logger.info("validate PRO Error Message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//mat-error[@id='mat-error-2']")).getText();
		Thread.sleep(500);
		System.out.println("The error message is : " + error);
		assertEquals(error, "This field is required.");
		
	}
	
	public void validateOtherError() {
		logger.info("validate Other Error Message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//mat-error[@id='mat-error-2']")).getText();
		System.out.println("The error message is : " + error);
		assertEquals(error, "This field is required.");
		
		
	}
	
	public void enterTextSearchFieldForAccountName(String text) throws InterruptedException {
		logger.info("Enter text in the search field" +text);
		testUtil.init(this);
		searchFiled1.clear();
		testUtil.setExplicitWait(searchFiled1, 120);
		searchFiled1.click();
		searchFiled1.sendKeys(text);	
		Thread.sleep(1000);
	}
	
	
	public void enterTextSearchFieldForAccountAddress(String text) throws InterruptedException {
		logger.info("Enter text in the search field for account address" +text);
		testUtil.init(this);
		searchFiled1.clear();
		testUtil.setExplicitWait(searchFiled1, 120);
		searchFiled1.click();
		searchFiled1.sendKeys(text);	
		Thread.sleep(1000);
	}
	
	public void enterTextSearchFieldForAccountCity(String text) throws InterruptedException {
		logger.info("Enter text in the search field for account city" +text);
		testUtil.init(this);
		searchFiled1.clear();
		testUtil.setExplicitWait(searchFiled1, 120);
		searchFiled1.click();
		searchFiled1.sendKeys(text);	
		Thread.sleep(1000);
	}
	
	public void enterTextSearchFieldForAccountState(String text) throws InterruptedException {
		logger.info("Enter text in the search field for account state" +text);
		testUtil.init(this);
		searchFiled1.clear();
		testUtil.setExplicitWait(searchFiled1, 120);
		searchFiled1.click();
		searchFiled1.sendKeys(text);	
		Thread.sleep(1000);
	}
	
	public void enterTextSearchFieldForAccountZip(String text) throws InterruptedException {
		logger.info("Enter text in the search field for account zip" +text);
		testUtil.init(this);
		searchFiled1.clear();
		testUtil.setExplicitWait(searchFiled1, 120);
		searchFiled1.click();
		searchFiled1.sendKeys(text);	
		Thread.sleep(1000);
	}
	
	public void enterTextSearchFieldForAccountIncorrectValue(String text) throws InterruptedException {
		logger.info("Enter text in the search field for incorrect value" +text);
		testUtil.init(this);
		searchFiled1.clear();
		testUtil.setExplicitWait(searchFiled1, 120);
		searchFiled1.click();
		searchFiled1.sendKeys(text);	
		Thread.sleep(1000);
	}
	

	
	public void validateAccountInformationForAccountName() {
		logger.info("Validate account information for account name");
		testUtil.init(this);

		String str=driver.findElement(By.xpath("//tbody/tr/td/div/div/span/span[1][contains(text(),'Displaying 1 - 25 of 127')]")).getText();
		System.out.println("The number of records displayed are: " +str);
		Assert.assertEquals(str, "Displaying 1 - 25 of 127");
		testUtil.setHardWait(2000);
		testUtil.verifyAndPrintWebTableData("//*[@id='main']/app-weight-and-research/mat-card//table/tbody");
	}
	
	
	
	public void clickOnNextArrow() {
		logger.info("Click on next arrow");
		testUtil.init(this);
		driver.findElement(By.xpath("//tbody/tr[1]/td/div/div[2]/span/span[3]")).click();
		
		
		
	}
	
	public void validateAccountInformationRecord() {
		logger.info("Validate account informaiotn with  26-50 records");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String str=driver.findElement(By.xpath("//tbody/tr[1]/td/div/div[2]/span/span[1][contains(text(),'Displaying 26 - 50 of 127')]")).getText();
		Assert.assertEquals(str, "Displaying 26 - 50 of 127");
		System.out.println("The number of records displayed are:"  +str);
}
	
	public void validateAccountInformationRecordForAccountAddress() {
		logger.info("Validate account informaiotn with  5-5 records");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String str=driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/div[2]/span/span[1][contains(text(),'Displaying 1 - 5 of 5')]")).getText();
		Assert.assertEquals(str, "Displaying 1 - 5 of 5");
		System.out.println("The number of records displayed are:"  +str);
		testUtil.setHardWait(2000);
		testUtil.verifyAndPrintWebTableData("//*[@id='main']/app-weight-and-research/mat-card//table/tbody");
}
	
	public void validateAccountInformationRecordForAccountCity() {
		logger.info("Validate account informaiotn with  4-4 records");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String str=driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/div[2]/span/span[1][contains(text(),'Displaying 1 - 4 of 4')]")).getText();
		Assert.assertEquals(str, "Displaying 1 - 4 of 4");
		System.out.println("The number of records displayed are:"  +str);
		testUtil.setHardWait(2000);
		testUtil.verifyAndPrintWebTableData("//*[@id='main']/app-weight-and-research/mat-card//table/tbody");
}
	
	public void validateAccountInformationRecordForAccountState() {
		logger.info("Validate account informaiotn with  25-4503 records");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String str=driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/div[2]/span/span[1][contains(text(),'Displaying 1 - 25 of 4503')]")).getText();
		Assert.assertEquals(str, "Displaying 1 - 25 of 4503");
		System.out.println("The number of records displayed are:"  +str);
		testUtil.setHardWait(2000);
		testUtil.verifyAndPrintWebTableData("//*[@id='main']/app-weight-and-research/mat-card//table/tbody");
}
	
	public void validateAccountInformationRecordForAccountZip() {
		logger.info("Validate account informaiotn with  5-5 records");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String str=driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/div[2]/span/span[1][contains(text(),'Displaying 1 - 5 of 5')]")).getText();
		Assert.assertEquals(str, "Displaying 1 - 5 of 5");
		System.out.println("The number of records displayed are:"  +str);
		testUtil.setHardWait(2000);
		testUtil.verifyAndPrintWebTableData("//*[@id='main']/app-weight-and-research/mat-card//table/tbody");
}
	
	public void validateAccountInformationRecordForAccountIncorrectValue() {
		logger.info("Validate account informaiotn with  0-0 records");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String str=driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/div[2]/span/span[1][contains(text(),'Displaying 0 - 0 of 0')]")).getText();
		Assert.assertEquals(str, "Displaying 0 - 0 of 0");
		System.out.println("The number of records displayed are:"  +str);
		testUtil.setHardWait(2000);
		
		String errorMsg=driver.findElement(By.xpath("//table/tbody/tr[4]/td/span[contains(text(),'No accounts found.')]")).getText();
		System.out.println("Error message is: " +errorMsg);
		Assert.assertEquals(errorMsg, "No accounts found.");
		
	}

}
