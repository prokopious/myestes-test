package eNetPages;



import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetVTLQouteExceptionQueuePage extends TestBase{
	
	private Logger logger=Logger.getLogger(ENetVTLQouteExceptionQueuePage.class);
	
	@FindBy(how = How.ID, using = "quoteNumber")
	private WebElement QouteNumber;
	
	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	private WebElement Submit;
	@FindBy(how = How.XPATH, using = "//*[@class='output-table']/tbody/tr/td[1]/a")
	private WebElement QuoteNumberRecord;

	@FindBy(how = How.XPATH, using = "//*[@class='output-table']/tbody/tr/td[5]")
	private WebElement ExceptionMessage;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'V')]")
	private WebElement QuoteLink; 
	
	@FindBy(xpath = "//*[@class='output-table']//tr[2]//a")
	private WebElement vtlQuoteExecptionLink; 
	
	@FindBy(id = "targetOrSystem")
	private WebElement targetOrSystem;
	
	@FindBy(id = "linearFeetSystem")
    private WebElement linearFeetSystem;

    @FindBy(id ="totalLinearFeet")
    private WebElement totalLinearFeet;
    
    @FindBy(id ="recalculate-button")
    private WebElement recalculateButton;

	/********************METHODS*******************/
	
	public void enterQouteNumber(String qNum) {
		logger.info("Enter User ID");
		testUtil.init(this);

		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setExplicitWait(QouteNumber, 120);
		QouteNumber.clear();
		testUtil.setHardWait(1000);
		QouteNumber.sendKeys(qNum);
	}
	

	
	  public void clickSubmitAndClickQuoteNumLink(String quoteNum, int numberOfAttempts) throws Exception {
	        
	        for (int i = 0; i < numberOfAttempts; i++) {
	            
	            clickOnSubmitButton();
	              
	              WebElement quoteNumLink = testUtil.filterXpathTimout("//a", null, null, 0, 5000, quoteNum);
	              
	              if (quoteNumLink == null && i < numberOfAttempts) {
	                  System.out.println("Attempt " + i + " unsuccessful");
	              }
	              else if(quoteNumLink == null &&  i > numberOfAttempts) {
	                  throw new Exception("Failed to find element");
	              } else {
	                  System.out.println("Link found on " + (i + 1) + "st attempt for quote number: " + quoteNumLink.getText());
	                  
	                  testUtil.clickElementJavascript(quoteNumLink);
	                  break;
	              }
	            }
	   }
	  
	  public void clickOnSubmitButton() {
	        logger.info("Click On Submit Button");
	        testUtil.init(this);

	        WebElement submit = testUtil.pollDOM(null, Submit, 20);
	        testUtil.clickElementJavascript(submit);
//	        logger.info("Click On Submit Button");
//	        testUtil.assetWait(null, Submit, 15, 200, TimeUnit.MILLISECONDS);
//	        testUtil.clickElementJavascript(Submit);
	       // testUtil.setHardWait(2000); //Using explicit wait here will make the test fail.

	  }
	public void verifyRecordAdded(String quoteNumber) {
		testUtil.init(this);
		logger.info("Verify record added in entry");
		assertEquals(QuoteNumberRecord.getText(), quoteNumber);
	}

	public void verifyExceptionMessage() {
		String expectedMessage = "Customer Linear Feet Too Small";
		testUtil.init(this);
		logger.info("Verify exception message in the table");
		assertEquals(ExceptionMessage.getText().trim(), expectedMessage);
	}
	public void clickOnQuoteLink() {
		testUtil.init(this);
		logger.info("Click On Quote Link");
		//testUtil.setExplicitWait(QuoteLink, 120);
		testUtil.setHardWait(3000);
		Actions action= new Actions(driver);
		action.doubleClick(QuoteLink).build().perform();
		
		testUtil.fluentWait(null, 0, 0, apiKey);
		//testUtil.clickElementJavascript(QuoteLink);
	
	} 
	
	public void clickOnQuoteLink(String quoteNumber) {
        testUtil.init(this);
        logger.info("Click On Quote Link");
        WebElement quoteLink = testUtil.filterSelector("a", null, null, 0, quoteNumber, "validate");
        testUtil.clickElementJavascript(quoteLink);
    }
	
	public void clickOnFirstVTLQuoteExceptionLink() {
		logger.info("Click on first displayed VTL quote exception link");
		testUtil.init(this);
		testUtil.setExplicitWait(vtlQuoteExecptionLink, 20);
		vtlQuoteExecptionLink.click();
	}
	
	public void validateTargetORSystem(String expected) {
		logger.info("Validating Target OR system");
		testUtil.init(this);
		testUtil.setExplicitWait(targetOrSystem, 20);
		String actual = targetOrSystem.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}

	public String getQuoteByTimeStamp(String timeStamp) { 
		logger.info("Get Quote by Time Stamp");
		testUtil.init(this);
		
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		testUtil.setHardWait(1000);
		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@class='output-table']//tr"));
		String quoteNo =null;
		
		for(int i = 2; i <= rowCount.size(); i++) {
			String rowVal = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]/td[2]")).getText();
			System.out.println(rowVal);
			
			if (rowVal.contains(timeStamp)) {
				quoteNo = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]/td[1]")).getText();
				System.out.println("Quote Number is "+quoteNo);
				
				break;
			}
		}
		testUtil.setHardWait(2000);
		return quoteNo;
		
		
	}
	// verify an exception is on table
	
	public void verifyExceptionByQuote(String quoteNumber, String exception) {
		logger.info("Verify Exception for Quote# "+quoteNumber);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		
		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@class='output-table']//tr"));
		
		boolean found = false;
		
		testUtil.setHardWait(2000);
		for(int i = 2; i <= rowCount.size(); i++) {
			
			String rowVal = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]/td[1]")).getText();
			
			if(rowVal.equals(quoteNumber)) {
				
				String actualException = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]/td[5]")).getText();
				System.out.println("Actual Exception:" +actualException);
				
				testUtil.setHardWait(1000);
				if(actualException.contains(exception)) {
					found = true;
					break;
				}			
			}		
		Assert.assertTrue(found);
		testUtil.setHardWait(2000);
		}
		
	}
	
	public void clickOnQuoteNumber(String quoteNo) {
		logger.info("Click on Quote - "+quoteNo);
		testUtil.init(this);
		driver.findElement(By.xpath("//*[@class='output-table']//a[contains(text(),'"+quoteNo+"')]")).click();
		testUtil.setHardWait(3000);
	}

	
	public void verifyQuoteDisplayedInResultTable(String quoteNo) {
		logger.info("Verify Quote# in result table"+quoteNo);
		testUtil.init(this);
		String actualQuoteNo =  driver.findElement(By.xpath("//*[@class='output-table']//tr[2]/td[1]")).getText();
		Assert.assertEquals(actualQuoteNo, quoteNo);
	}
	
	public void verifyNoExceptionDisplayed(String quoteNumber) {
		logger.info("Verify No Exception displayed for Quote# "+quoteNumber);
		testUtil.init(this);
		
		List<WebElement> rowCount =  driver.findElements(By.xpath("//*[@class='output-table']//tr")); 
		
		boolean found = false;
	  
		for(int i = 2; i <= rowCount.size(); i++) { 
		  String rowVal =  driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]/td[1]")).getText();
	  System.out.println(rowVal);
		  if(rowVal.equals(quoteNumber)) {
			  String actualException = driver.findElement(By.xpath("//*[@class='output-table']//tr["+i+"]/td[5]")).getText();
			  Assert.assertTrue(actualException.isEmpty());
			  found = true;
			  break;
		  } 
		}
		Assert.assertTrue(found);
	}

	


	public void validateLinearFeet(String expected) {
		logger.info("Validate Linear feet");
		testUtil.init(this);
		testUtil.setExplicitWait(linearFeetSystem, 20);
		String actual = linearFeetSystem.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}

	public void entertotalLinearFeet(String totalLinearFt) {
		logger.info("Enter Total Linear Feet");
		testUtil.init(this);
		testUtil.setExplicitWait(totalLinearFeet, 20);
		totalLinearFeet.clear();
		totalLinearFeet.sendKeys(totalLinearFt);
	}

	public void clickOnRecalculateButton() {
		logger.info("Click on Recalculate button");
		testUtil.init(this);
		recalculateButton.click();
	}

}
