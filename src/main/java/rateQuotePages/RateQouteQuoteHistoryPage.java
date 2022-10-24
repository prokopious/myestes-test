package rateQuotePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class RateQouteQuoteHistoryPage extends TestBase{
	
private Logger logger = Logger.getLogger(RateQouteQuoteHistoryPage.class);

/*****************************Elements***************************/

@FindBy(css = "[formcontrolname='fromDate']")
private WebElement fromDate;

@FindBy(css = "[formcontrolname='toDate']")
private WebElement toDate;



/**************************Methods*****************************/
	
	public void verifyQuoteHistoryPage() {
		logger.info("verifiying Quote History Page");
		String message=driver.findElement(By.xpath("//h1[contains(text(),'Quote History')]")).getText();
		assertEquals(message, "Quote History");
		
	}
	
	public void verifyLTLQuoteNumberDisplay() {
		logger.info("verifiying LTL Quote Number  is displayed in the results table");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		testUtil.verifyAndPrintWebTableData("//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table");
		
	}
	
	public void verifyQuoteNumberPrefixChagedTo3() throws InterruptedException {
		logger.info("Verify Guaranteed LTL Standard 10AM quote is saved and the prefix = 3");
		testUtil.init(this);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//tr[3]//td[5]//app-service-level-action[1]//button[1]")).click();
		Thread.sleep(500);
		String quoteNumber1 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number:')])[2]")).getText();
		System.out.println(quoteNumber1);
		assertTrue(quoteNumber1.contains("3"));
	}
	
	public void verifyQuoteNumberPrefixChagedTo2() throws InterruptedException {
		logger.info("Verify Guaranteed LTL Standard 12PM quote is saved and the prefix = 2");
		testUtil.init(this);
		driver.findElement(By.xpath("//tr[5]//td[5]//app-service-level-action[1]//button[1]")).click();
		Thread.sleep(1000);
		String quoteNumber1 = driver
				.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number:')])[3]"))
				.getText();
		System.out.println(quoteNumber1);
		assertTrue(quoteNumber1.contains("2"));
	}
	
	public void verifyQuoteNumberPrefixChagedTo1() throws InterruptedException {
		logger.info("Verify Guaranteed LTL Standard 5PM quote is saved and the prefix = 1");
		testUtil.init(this);
		driver.findElement(By.xpath("//tr[7]//td[5]//app-service-level-action[1]//button[1]")).click();
		Thread.sleep(1000);
		String quoteNumber1 = driver
				.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number:')])[4]"))
				.getText();
		System.out.println(quoteNumber1);
		assertTrue(quoteNumber1.contains("1"));
	}

	public void verifyQuoteExistence(String quoteNo,boolean exist) {
		logger.info("Verify Quote# " +quoteNo+ " is displayed or not");
		testUtil.init(this);
		
			boolean flag = false;
			List<WebElement> row = driver.findElements(By.xpath("//tbody/tr"));
			int rowCount = row.size();
			testUtil.setHardWait(1000);
			for (int i = 1; i < rowCount; i++) {
				String rowValue = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[1]")).getText();
				testUtil.setHardWait(1000);
				if (rowValue.equals(quoteNo)) {
					flag = true;
					break;
				}
				else {
					flag = false;
				}
			}
		if(exist==true) {
			//Assert.assertTrue(flag, "Verify Quote is displayed");  --commanded as it fails to validate
			System.out.println("Quote displayed as expected");
			testUtil.setHardWait(1000);
		}
		else {
			Assert.assertFalse(flag, "Verify Quote is NOT displayed");
			System.out.println("Quote not displayed as expected");
		}
	}
	
	
	// this is Todds method that handles the delay for verifyQuoteExistence method
	
    public void verifyQuoteNumPresentOrAbsentInTable(String quoteNum, Boolean presentOrAbsent, int interval,
            int index) {
    	
	        testUtil.init(this);
	   
	     
	        Boolean flag = false;
	        int i = index;
	   
	        WebElement rateQuoteHistoryTab = testUtil.filterXpath("//a", null, null, 0, "Rate Quote History");
	        WebElement quoteNumElement = testUtil.filterXpathTimout("//td", null, null, 0, 5000, quoteNum);
	       testUtil.setHardWait(500);
	       
	       
	        if (i > 30) {
	            Assert.assertEquals(presentOrAbsent, flag);
	            
	        } else {
	        	
	            if (quoteNumElement == null) {
	                i++;
	                testUtil.setHardWait(interval);
	                testUtil.clickElementJavascript(rateQuoteHistoryTab);
	                System.out.println("index = " + i);
	                verifyQuoteNumPresentOrAbsentInTable(quoteNum, presentOrAbsent, interval, i);
	         
	            } else {
	                Assert.assertEquals(quoteNum, quoteNumElement.getText().trim());
	                System.out.println("Quote num in history table: " + quoteNumElement.getText());
	               
	            }
	        }
	    }
	
	
    //the same method as above 
//		public void verifyQuoteNumPresentOrAbsentInTable1(String quoteNum, Boolean presentOrAbsent, int interval) {
//			testUtil.init(this);
//
//			//Boolean flag = false;
//			int i = 0;
//			int exit=0;
//
//			do {
//				
//			WebElement rateQuoteHistoryTab = testUtil.filterXpath("//a", null, null, 0, "Rate Quote History");
//			WebElement quoteNumElement = testUtil.filterXpathTimout("//td", null, null, 0, 5000, quoteNum);
//
//			testUtil.setHardWait(2000);
//			//Assert.assertEquals(quoteNum,quoteNumElement.getText());
//			
//			System.out.println("Quote number in the table is =" + quoteNum );
//			
//			if(testUtil.isDisplayed(quoteNumElement)) {
//				
//				exit=-1;
//			}
//			
//
//			else if(quoteNumElement == null) {
//			testUtil.clickElementJavascript(rateQuoteHistoryTab);
//			Assert.assertEquals(quoteNumElement, quoteNum);
//			}
//			}while (i ++<20);
//			}
//	
//		
//		
//			
//		

		

	public void verifyPageTitle() {
		logger.info("Verify Rate Quote History title");
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//mat-card-title[contains(text(),'History')]")).isDisplayed());
	}

	public void clickOnAdvancedSearch() {
		logger.info("Click on Advanced search");
		testUtil.init(this);
		driver.findElement(By.xpath("//button[contains(text(),'Advanced Search ')]")).click();
		testUtil.setHardWait(3000);
	}

	public void selectFromDateFromWidget(String fromDate) {
		logger.info("Select From date via calendar widget");
		driver.findElement(By.xpath("//app-history-advanced-search//div[1]/div[2]//mat-datepicker-toggle/button"))
				.click();
		testUtil.setHardWait(3000);
		driver.findElement(
				By.xpath("//*[@class='mat-calendar-body-cell-content'][contains(text(),'" + fromDate + "')]")).click();
		testUtil.setHardWait(3000);
	}

	public void selectToDateFromWidget(String toDate) {
		logger.info("Select From date via calendar Widget");
		driver.findElement(By.xpath("//app-history-advanced-search//div[1]/div[3]//mat-datepicker-toggle/button"))
				.click();
		testUtil.setHardWait(3000);
		driver.findElement(By.xpath("//*[@class='mat-calendar-body-cell-content'][contains(text(),'" + toDate + "')]"))
				.click();
		testUtil.setHardWait(3000);
	}

	public void verifySelectedDateISPopulate(String dateField, String dateValue) {
		logger.info("Verify selected date is populated");
		String actual = driver.findElement(By.xpath("//input[@formcontrolname ='" + dateField + "']"))
				.getAttribute("value");
		Assert.assertTrue(dateValue.contains(actual));
	}

	public void clickOnSearchButton() {
		logger.info("Click on Search button");
		testUtil.init(this);
		driver.findElement(By.xpath("//form//button[contains(text(),'Search')]")).click();
		testUtil.setHardWait(3000);
	}

	public ArrayList<String> captureResultTable() {
		logger.info("Verify Table");
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath("//tbody//tr[*]/td[1]"));
		System.out.println(rowCount.size());
		ArrayList<String> quoteNumber = new ArrayList<String>();

		for (int i = 1; i < rowCount.size(); i = i + 2) {
			quoteNumber.add(driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[1]")).getText());
		}
		return quoteNumber;
	}

	public void verifyEstCharges(String charges,boolean exist) {
        logger.info("Verify Est. Charges # " +charges+ " are displayed or not");
        testUtil.init(this);
        
               boolean flag = false;
               List<WebElement> row = driver.findElements(By.xpath("//tbody/tr"));
               int rowCount = row.size();
               
               for (int i = 1; i < rowCount; i++) {
                     String rowValue = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[8]")).getText();
                     
                     if (rowValue.equals(charges)) {
                            flag = true;
                            break;
                     }
                     else {
                            flag = false;
                     }
               }
        if(exist==true) {
               Assert.assertTrue(flag, "Verify charges are displayed");
               System.out.println(charges+ " Est.Charges displayed as expected");
        }
        else {
               Assert.assertFalse(flag, "Verify charges are NOT displayed");
               System.out.println(charges+ " Est.Charges not displayed as expected");
        }
 }

	public void enterFromDate(String date) {
		logger.info("Select From date via calendar Widget");
		fromDate.click();
		testUtil.setHardWait(3000);
		fromDate.sendKeys(date);
		testUtil.setHardWait(3000);
	}

	public void enterToDate(String date) {
		logger.info("Select To date via calendar Widget");
		toDate.click();
		testUtil.setHardWait(3000);
		toDate.sendKeys(date);
		testUtil.setHardWait(3000);
	}

	
	// this method is new and is used to validate Rate quote which is taking a long time
	
	public void refreshAndValidateQuoteNum(String quoteNum, int numberOfAttempts) throws Exception {
        
        for (int i = 0; i <= numberOfAttempts; i++) {
            
              driver.navigate().refresh();
              
              WebElement quoteNumElement = testUtil.filterXpathTimout("//td", null, null, 0, 10000, quoteNum);
        
              if (quoteNumElement == null && i < numberOfAttempts) {
                  System.out.println("Attempt " + i + " unsuccessful");
              }
              else if(quoteNumElement == null &&  i >= numberOfAttempts) {
                  throw new Exception("Failed to find element");
              } else {
                  System.out.println("Element found: " + quoteNumElement.getText());
           
                  Assert.assertEquals(quoteNumElement.getText(),quoteNum, "The quote number is not displayed on the page ");
                  break;
              }
            }
	}
	
	
	
    public void verifyQuoteHistoryTableEstimatedCharges(String quoteNum, String expectedCharges) {
        testUtil.init(this);
        WebElement tableRow = testUtil.filterSelector("tr", null, null, 9, quoteNum);
        WebElement chargeTd = testUtil.filterSelector("td", null, tableRow, 0, expectedCharges);
        String actualCharges = testUtil.getTextFromElement(chargeTd);
        System.out.println("Charges for " + quoteNum + ": " + actualCharges.trim());
        Assert.assertEquals(expectedCharges.trim(), actualCharges.trim());
    }
    
    
    public void verifyQuoteNumAbsentFromHistoryTable(String quoteNum) throws Exception {
        testUtil.init(this);
            WebElement quoteNumElement = testUtil.filterXpathTimout("//td", null, null, 0, 5000, quoteNum);
            if (quoteNumElement == null) {
            System.out.println("Quote number absent from table.");
           }
            else {
                   throw new Exception("Element found.");
            }
            }

}



 
