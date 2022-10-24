package pcRaterDownloadPages;

	import static org.testng.Assert.assertEquals;
	
	import org.apache.log4j.Logger;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
    import org.testng.Assert;

import testBase.TestBase;
	
	public class PCRaterDownloadPage extends TestBase {	
		
		private Logger logger = Logger.getLogger(PCRaterDownloadPage.class);
		
		@FindBy(how = How.XPATH, using = "//h3[contains(text(),'PC Rater Download')]")
		private WebElement clickOnPCRater;
		
		@FindBy(how = How.ID, using = "mat-input-0")
		private WebElement custName;
		
		@FindBy(how = How.ID, using = "mat-input-1")
		private WebElement companyName;
		
		@FindBy(how = How.ID, using = "mat-input-10")
		private WebElement accountNumber;
		
		@FindBy(how = How.ID, using = "mat-input-2")
		private WebElement address;
		
		@FindBy(how = How.ID, using = "mat-input-3")
		private WebElement city;
		
		@FindBy(how = How.ID, using = "mat-input-4")
		private WebElement state;
		
		@FindBy(how = How.ID, using = "mat-input-5")
		private WebElement zipcode;
		
		@FindBy(how = How.ID, using = "mat-input-7")
		private WebElement phone;
		
		@FindBy(how = How.ID, using = "mat-input-9")
		private WebElement email;
		
		@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
		private WebElement submit;
		
		@FindBy(how = How.XPATH, using = "//span[@class='ng-star-inserted']")
		private WebElement errorMessage;
		

		public void clickOnPCRaterDownload() {
			testUtil.init(this);
			logger.info("Click on PC Rater Download link");
			clickOnPCRater.click();
		}
		
		public void enterName() {
			testUtil.init(this);
			logger.info("Enter Name");
			custName.sendKeys("Tester");
		}
		
		public void enterCompanyName() {
			testUtil.init(this);
			logger.info("Enter Company Name");
			companyName.sendKeys("Estes");
		}
		
		public void enterAccountNumber() throws InterruptedException {
			testUtil.init(this);
			logger.info("Enter Company Name");
			Thread.sleep(2000);
			testUtil.clickElementJavascript(accountNumber);
			accountNumber.sendKeys("0500845");
		}
		
		public void enterAddress() {
			testUtil.init(this);
			logger.info("Enter Address");
			address.sendKeys("Atlantis Street");
		}
		
		public void enterCity() {
			testUtil.init(this);
			logger.info("Enter City");
			city.sendKeys("Herndon");
		}
		
		public void enterState() {
			testUtil.init(this);
			logger.info("Enter State");
			state.sendKeys("VA");
		}
		
		public void enterZip() {
			testUtil.init(this);
			logger.info("Enter State");
			zipcode.sendKeys("20171");
		}
		
		public void enterPhoneNumber() {
			testUtil.init(this);
			logger.info("Enter phone number");
			phone.sendKeys("223-423-2122");
		}
		
		public void enterEmail() {
			testUtil.init(this);
			logger.info("Enter email address");
			email.sendKeys("tester@estes.com");
		}
		
		public void clickOnSubmit() {
			testUtil.init(this);
			logger.info("Click on submit button");
			testUtil.setHardWait(2000);
			submit.click();
			testUtil.setHardWait(1000);
		}
		
		public void verifyErrorMessage(String expectedMessage) {
			logger.info("Verify error message");
			testUtil.init(this);
			String UpdatedQMessage = errorMessage.getText();
			System.out.println("Error message is:"+UpdatedQMessage);
			assertEquals(UpdatedQMessage, expectedMessage);
			
			
	}
		
		public void validateErrorMessage() {
			testUtil.init(this);
			logger.info("Verify all required fields");
			String error=driver.findElement(By.id("mat-error-0")).getText();
			System.out.println("The error message is : " + error);
			Assert.assertEquals(error, "This field is required.");
			
			String error1=driver.findElement(By.id("mat-error-1")).getText();
			Assert.assertEquals(error1, "This field is required.");
			String error2=driver.findElement(By.id("mat-error-8")).getText();
			Assert.assertEquals(error2, "This field is required.");	
			
			String error3=driver.findElement(By.id("mat-error-2")).getText();
			Assert.assertEquals(error3, "This field is required.");
			String error4=driver.findElement(By.id("mat-error-3")).getText();
			Assert.assertEquals(error4, "This field is required.");
			
			String error5=driver.findElement(By.id("mat-error-4")).getText();
			Assert.assertEquals(error5, "This field is required.");
			String error6=driver.findElement(By.id("mat-error-5")).getText();
			Assert.assertEquals(error6, "This field is required.");
			
			String error7=driver.findElement(By.id("mat-error-6")).getText();
			Assert.assertEquals(error7, "This field is required.");	
			String error8=driver.findElement(By.id("mat-error-7")).getText();
			Assert.assertEquals(error8, "This field is required.");
			
		}

	}


