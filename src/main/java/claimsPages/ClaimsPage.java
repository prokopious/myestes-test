package claimsPages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import myEstesPages.MyEstesLoginPage;
import testBase.TestBase;



public class ClaimsPage extends TestBase {

	//Global logclass= new Global();
	
	private static  Logger logger = Logger.getLogger(ClaimsPage.class);
	 
//	private Logger logger = Logger.getLogger(ClaimsPage.class);

										/*********** WEB ELEMENTS ***********/

	@FindBy(how = How.ID, using = "account")
	private WebElement AccountNumber;

	@FindBy(how = How.XPATH, using = "//span[@class='mat-option-text'][contains(text(),'Estes Claim Number')]")
	private WebElement SearchBy;

	@FindBy(how = How.ID, using = "searchBy")
	private WebElement drpdwnSearchBy;

	@FindBy(how = How.XPATH, using = "//mat-option/span[text()='PRO Number']")
	private WebElement proNumber;

	@FindBy(how = How.ID, using = "numbers")
	private WebElement ClaimNumberField;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Look Up')]")
	private WebElement LookupButton;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement LogoutButton;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default mb-4']")
	private WebElement FileClaimButton;

	@FindBy(how = How.XPATH, using = "//option[@value='DATERANGE']")
	private WebElement DateRange;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Claim Number')]")
	private WebElement claimNoField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'PRO Number')]")
	private WebElement proNoField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Status')]")
	private WebElement statusField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Date Filed')]")
	private WebElement dateField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Ref. Number')]")
	private WebElement refNoField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Claim Amount')]")
	private WebElement claimAmtField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Remit to')]")
	private WebElement remitToField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Account Search')]")
	private WebElement accountSearch;

	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[1]")
	private WebElement firstAccNum;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'File a Claim')]")
	private WebElement fileClaimTab;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary mr-2']")
	private WebElement submit;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'ERROR: There are validation errors in the form.  Please review the form and submit again.')]")
	private WebElement error;
	

	@FindBy(how = How.XPATH, using = "//input[@id='addressSelect']")
	private WebElement accNum;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-tab-content-0-0\"]/div/app-claims-inquiry/mat-card[1]/div/div[2]")
	private WebElement outside;
	

									/*********** METHODS ***********/

	public void selectAccoutNumber(String AccoutNum) {
		logger.info("Select account number  ");
		testUtil.init(this);
		testUtil.selectUsingValue(AccountNumber, AccoutNum);
	}

	public void selectSearchBy() {
		logger.info("Select search ");
		testUtil.init(this);
		driver.findElement(By.xpath("//span[contains(text(),'Date Range')]")).click();
		SearchBy.click();

	}

	public void selectDateRange() {
		logger.info("Select data ");
		testUtil.init(this);
		SearchBy.click();
		testUtil.setHardWait(2000);
		DateRange.click();

	}

	public void enterClaimNumber() {
		logger.info("Enter claim number");
		testUtil.init(this);
		ClaimNumberField.sendKeys("1091838");
	}

	public void clickOnLookupButton() throws InterruptedException {
		logger.info("Click on lookup button ");
		testUtil.init(this);
		testUtil.setExplicitWait(LookupButton, 120);
		LookupButton.click();
		testUtil.setHardWait(2000);
	}

	public void clickFileClaimButton() {
		logger.info("Click on file claim ");
		testUtil.init(this);
		FileClaimButton.click();
	}

	public void clickOnLogout() {
		logger.info("Click on logout button");
		testUtil.init(this);
		LogoutButton.click();
	}

	public void verifyClaimsResultDisplay(String name) {
		logger.info("Verify claims result ");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData("");
	}

	public void selectPRONumber() throws InterruptedException {
		logger.info("Select PRO number  ");
		testUtil.init(this);
		testUtil.clickElementJavascript(drpdwnSearchBy);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(proNumber);
	}

	public void enterPRONumber(String number) {
		logger.info("Enter PRO number ");
		testUtil.init(this);
		ClaimNumberField.sendKeys(number);
	}

	public void verifyClaimNumberField() {
		logger.info("Verify claim number field");
		testUtil.init(this);
		testUtil.setHardWait(2000);
        JavascriptExecutor js = (JavascriptExecutor)driver; 
        js.executeScript("window.scrollBy(0,300)"); 
        Assert.assertEquals(claimNoField.isDisplayed(), true);
 }


	public void verifyPRONumberField() {
		logger.info("Verify PRO number field");
		testUtil.init(this);
		Assert.assertEquals(proNoField.isDisplayed(), true);
	}

	public void verifyStatusField() {
		logger.info("Verify Status field");
		testUtil.init(this);
		Assert.assertEquals(statusField.isDisplayed(), true);
	}

	public void verifyDateField() {
		logger.info("Verify date field");
		testUtil.init(this);
		Assert.assertEquals(dateField.isDisplayed(), true);
	}

	public void verifyRefNumberField() {
		logger.info("Verify reference number field");
		testUtil.init(this);
		Assert.assertEquals(refNoField.isDisplayed(), true);
	}

	public void verifyClaimAmountField() {
		logger.info("Verify claim amount field");
		testUtil.init(this);
		Assert.assertEquals(claimAmtField.isDisplayed(), true);
	}

	public void verifyRemitToField() {
		logger.info("Verify remit to field");
		testUtil.init(this);
		Assert.assertEquals(remitToField.isDisplayed(), true);

	}

	public void clickOnAccountSearch() {
		logger.info("Click on account search");
		testUtil.init(this);
		accountSearch.click();

	}

	public void clickOnFirstAccountNumber() {
		logger.info("Click on account number");
		testUtil.init(this);
		firstAccNum.click();

	}

	public void selectSearchByRefNum() {
		logger.info("Select search by reference");
		testUtil.init(this);
		driver.findElement(By.xpath("//span[contains(text(),'Your Reference Number')]")).click();
		SearchBy.click();

	}

	public void clickOnFileClaimTab() throws InterruptedException {
		logger.info("Click on file claim tab ");
		testUtil.init(this);
		fileClaimTab.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnSubmitButton() throws InterruptedException {
		logger.info("Click on submit button ");
		testUtil.init(this);
		submit.click();

	}

	public void verifyErrorMessage() throws InterruptedException {
		logger.info("Verify error message is displayed");
		testUtil.init(this);
		
		WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'ERROR: There are validation errors in the form.  Please review the form and submit again.')]"));
		String error=ele.getText();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ERROR: There are validation errors in the form.  Please review the form and submit again.')]")));
		Assert.assertEquals(ele.isDisplayed(), true);
	
	}

	public void selectSearchBy(String searchBy) {
        logger.info("Select search ");
        testUtil.init(this);
        driver.findElement(By.xpath("//mat-select[@id='searchBy']//div[@class='mat-select-value']")).click();
        testUtil.setHardWait(2000);
        driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+searchBy+"')]")).click();
  }

  public void selectAccountFromSearch(String acctNumber) {
        logger.info("Select Account from Account Search");
        testUtil.init(this);
        driver.findElement(By.xpath("//a[contains(text(),'Account Search')]")).click();
        testUtil.setHardWait(2000);
  driver.findElement(By.xpath("//a[contains(text(),'"+acctNumber+"')]")).click();
        testUtil.setHardWait(2000);
  }
  
  public void enterClaimNumber(String claimNo) {
        logger.info("Enter claim number");
        testUtil.init(this);
        ClaimNumberField.clear();
        ClaimNumberField.sendKeys(claimNo);
  }
  
  public void clickOnCaretIcon() {
        logger.info("Click on caret icon");
        testUtil.init(this);
        driver.findElement(By.xpath("//i[contains(text(),' expand_more ')]")).click();
        testUtil.setHardWait(3000);
  }
  
  public void verifyClaimResult() {
        logger.info("Verify Claim result section");
        testUtil.init(this);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,200)");

  Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Claimant')]")).isDisplayed());
  Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Remit to')]")).isDisplayed());
  Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Check Number')]")).isDisplayed());
  Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Check Date')]")).isDisplayed());
  Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Check Amount')]")).isDisplayed());
  }
  
	public void enterMultiplePRONumber(String... proNumbers) {
		
		StringBuilder proNumber = new StringBuilder();
		logger.info("Enter multiple PRO number ");
		testUtil.init(this);
		for(int i=0;i<proNumbers.length;i++)
		{
			proNumber = proNumber.append(proNumbers[i]).append((i==proNumbers.length-1)?"":"\n");
		}
		ClaimNumberField.sendKeys(proNumber);
		
	}
	
	
	  public void enterAccountNumber(String accNumber) {
	        logger.info("Enter the account number");
	        testUtil.init(this);
	        testUtil.setHardWait(2000);
	        accNum.sendKeys(accNumber);
	  }

	  
	  public void selectStatus(String status) {
          logger.info("Select search ");
          testUtil.init(this);
          driver.findElement(By.xpath("//mat-select[@id='status']//div[@class='mat-select-value']")).click();
          testUtil.setHardWait(2000);
          driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+status+"')]")).click();
    }
    

      public void selectFieldDateRange(String searchBy) {
          logger.info("Select search ");
          testUtil.init(this);
          driver.findElement(By.id("filedDateRange")).click();
          testUtil.setHardWait(2000);
         driver.findElement(By.xpath("//span[contains(text(),'"+searchBy+"')]")).click();
    }

      public void addCalendarDate(String calType, int count) {
          
          logger.info("Enter customized calender date");
          testUtil.init(this);
          Calendar cal = Calendar.getInstance();

          
          if (calType.equals("DATE")) {
              cal.add(Calendar.DATE, count);
        } else if (calType.equals("MONTH")) {
              cal.add(Calendar.MONTH, count);
        } else if (calType.equals("YEAR")) {
              cal.add(Calendar.YEAR, count);
        }

          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          sdf.format(cal.getTime());

          String newDate = sdf.format(cal.getTime());

          System.out.println("Incremented/Decremented " + calType + " " + "by " + count);
          driver.findElement(By.xpath("//*[@formcontrolname='startDate']")).click();
          driver.findElement(By.xpath("//*[@formcontrolname='startDate']")).sendKeys(newDate);
          testUtil.setHardWait(2000);
   }
      public String enterTodaysDate() {
          logger.info("Enter todays date");
            testUtil.init(this);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            // Getting current date
            Calendar cal = Calendar.getInstance();
            // Displaying current date in the desired format
            String todayDate = sdf.format(cal.getTime());
             driver.findElement(By.xpath("//*[@formcontrolname='endDate']")).click();
             driver.findElement(By.xpath("//*[@formcontrolname='endDate']")).sendKeys(todayDate);
            testUtil.setHardWait(2000);
            return todayDate;
      }
 
      public void enterReferenceNumber(String number) {
          logger.info("Enter reference number ");
          testUtil.init(this);
          ClaimNumberField.sendKeys(number);
    }

      public void clickOnPRONumberSearchBox() {
  		
  		logger.info("Click on PRO number ");
  		testUtil.init(this);
  		 testUtil.setHardWait(2000);
  		ClaimNumberField.click();
  		
  	}
      
      public void clickOutsideOfSearchBox() {
    		
    		logger.info("Click outside of search box");
    		testUtil.init(this);
    		 testUtil.setHardWait(2000);
    		 JavascriptExecutor js = (JavascriptExecutor) driver; 
    	        js.executeScript("window.scrollBy(0,300)");
    		 outside.click();
    		
    	}
      
      public void verifyErrorMessageFromRequirdFields() {
  		logger.info("Verify error message from required fields");
  		testUtil.init(this);	
  		testUtil.setHardWait(3000);
  		
  		String mess=driver.findElement(By.xpath("//mat-error[@role='alert']")).getText();
  		System.out.println("Error message is : " + mess);
  		Assert.assertEquals(mess, "This field is required.");
      }
	  
}
