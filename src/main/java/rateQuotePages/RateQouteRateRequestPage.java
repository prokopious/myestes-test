package rateQuotePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class RateQouteRateRequestPage extends TestBase {

	private Logger logger = Logger.getLogger(RateQouteRateRequestPage.class);

	@FindBy(how = How.XPATH, using = "//label[@for='guaranteed_10']")
	private WebElement GuaranteedBy10AM;
	
	@FindBy(how = How.XPATH, using = "//font[contains(text(),'Quote History')]")
	private WebElement QuoteHistory;

	public void selectGuaranteedBy10AM() {

		testUtil.init(this);
		GuaranteedBy10AM.click();
	}

	public void verifyQuoteNumberPrefixISL() throws InterruptedException {
		logger.info("verify Qoute Number prefix is L");
		testUtil.init(this);	
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]")).getText();
		System.out.println("Qoute Number is :" + quoteNumber);
		assertTrue(quoteNumber.contains("L"));// assertEquals(quoteNumber, "0LQSGGQ");
	}

	public void verifyQuoteNumberPrefixChagedTo3() throws InterruptedException {
		logger.info("verify Qoute Number prefix is 3");
		testUtil.init(this);
		Thread.sleep(1000);
		String quoteNumber1 = driver
				.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]"))
				.getText();
		System.out.println("Qoute Number is :" + quoteNumber1);
		assertTrue(quoteNumber1.contains("3"));// assertEquals(quoteNumber, "0LQSGGQ");
	}
	
	public void verifyQuoteNumberPrefixChagedTo2() throws InterruptedException {
		logger.info("verify Qoute Number prefix is 2");
		testUtil.init(this);
		Thread.sleep(1000);
		String quoteNumber2 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[3]")).getText();
		System.out.println("Qoute Number is :" + quoteNumber2);
		assertTrue(quoteNumber2.contains("2"));// assertEquals(quoteNumber, "0LQSGGQ");
	}
	
	public void verifyQuoteNumberPrefixChagedTo1() throws InterruptedException {
		logger.info("verify Qoute Number prefix is 1");
		testUtil.init(this);
		Thread.sleep(1000);
		String quoteNumber3 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[4]")).getText();
		System.out.println("Qoute Number is :" + quoteNumber3);
		assertTrue(quoteNumber3.contains("1"));// assertEquals(quoteNumber, "0LQSGGQ");
	}

	public void clickOnQuoteHistory() {
		testUtil.init(this);
		QuoteHistory.click();
	}
	
	public void clickOnGetQuoteButton(String eleName) {
		logger.info("click on " + eleName  +  " Get Quote Button"); 
		testUtil.init(this);
		driver.findElement(By.xpath("//td[contains(text(),  '"+ eleName +"')]")).click();
	} 
	
	public void verifyQuoteNumberPrefixChagedTo3(String ch) throws InterruptedException {
		logger.info("verify Quote Number prefix is 3");
		testUtil.init(this);
		Thread.sleep(40000);
		String quoteNumber1 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]")).getText();
		System.out.println("quoteNumber1 : "+quoteNumber1);
		assertTrue(quoteNumber1.substring(19).startsWith(ch));
		System.out.println("Prefix is: "+ quoteNumber1.substring(19, 20));
		//assertTrue(quoteNumber1.startsWith("3"));// assertEquals(quoteNumber, "0LQSGGQ");
	}
	

	public void verifyQuoteNumberPrefixChagedTo2(String ch) throws InterruptedException {
		logger.info("verify Qoute Number prefix is 2");
		testUtil.init(this);
		Thread.sleep(1000);
		String quoteNumber2 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[3]")).getText();
		System.out.println("quoteNumber2 : "+quoteNumber2);
		assertTrue(quoteNumber2.substring(19).startsWith(ch));
		System.out.println("Prefix is: "+ quoteNumber2.substring(19, 20));
		//assertTrue(quoteNumber2.contains("2"));// assertEquals(quoteNumber, "0LQSGGQ");
	}

	
	public void verifyQuoteNumberPrefixChagedTo1(String ch) throws InterruptedException {
		logger.info("verify Qoute Number prefix is 1");
		testUtil.init(this);
		Thread.sleep(1000);
		String quoteNumber3 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[4]")).getText();
		System.out.println("Qoute Number is :" + quoteNumber3);
		assertTrue(quoteNumber3.substring(19).startsWith(ch));
		System.out.println("Prefix is: "+ quoteNumber3.substring(19, 20));
		//assertTrue(quoteNumber3.contains("1"));// assertEquals(quoteNumber, "0LQSGGQ");
	} 
	
	
	public String getQuoteNumber(String serviceLevel) throws InterruptedException {
		logger.info("Get Quote Number");
		testUtil.init(this);	
		Thread.sleep(1000);
		
		logger.info("Capture quote number for " + serviceLevel);
		testUtil.init(this);

		String serviceLevelCode = null;
		switch (serviceLevel) {
		case "LTL Standard Transit":
			serviceLevelCode = "L";
			break;
		case "Gauranteed 10 AM":
			serviceLevelCode = "3";
			break;
		case "Gauranteed 12 PM":
			serviceLevelCode = "2";
			break;
		case "Gauranteed 5 PM":
			serviceLevelCode = "1";
			break;
		case "Volume and Truckload Basic":
			serviceLevelCode = "V";
			break;
		case "Volume and Truckload Economy":
			serviceLevelCode = "E";
			break;
		case "Volume and Truckload Standard":
			serviceLevelCode = "S";
			break;
		case "Guaranteed Exlusive Use":
			serviceLevelCode = "X";
			break;
			
		case "Estes Truckload":
			serviceLevelCode = "T";
			break;
		}

	
        String quoteNumber = driver
                        .findElement(By.xpath("//h4[contains(text(),'Rate Quote Number: "+serviceLevelCode+"')]")).getText();
                        
		System.out.println("Qoute Number is :" + quoteNumber);
		return quoteNumber;
	}
	
	
	
	public void verifyLastSixFromAllQuoteNo(String quoteNo) throws InterruptedException {
		logger.info("verify last six digits of quote number");
		testUtil.init(this);
		Thread.sleep(1000);
		
		boolean val = false;
		
		RateQuotePage ltlRateQuotePage = new RateQuotePage();
		ltlRateQuotePage.clickOnGetQuoteButton(" LTL Standard Transit ");
		String quoteNumber1 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]")).getText().substring(20,26);
		
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 10AM ");
		String quoteNumber2 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]")).getText().substring(20,26);
		
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 12PM ");
		String quoteNumber3 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[3]")).getText().substring(20,26);
		
		ltlRateQuotePage.clickOnGetQuoteButton(" Guaranteed LTL Standard Transit: 5PM ");
		String quoteNumber4 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[4]")).getText().substring(20,26);
		
		if(quoteNumber1.equals(quoteNo) && quoteNumber2.equals(quoteNo) && quoteNumber3.equals(quoteNo) && quoteNumber4.equals(quoteNo)) {
			val = true;
		}
		else {
			val = false;
		}
		Assert.assertTrue(val);
		
	}
	
	 public String recordQuoteNumber(String serviceLevel) {
	        logger.info("Capture quote number");
	        testUtil.init(this);
	        WebElement quoteDrawer = testUtil.filterSelector("app-quote-drawer", null, null, 1, serviceLevel);
	        System.out.println(testUtil.getTextFromElement(quoteDrawer));
	        WebElement quoteNumHeader = testUtil.filterSelector("h4", null, quoteDrawer, 0);
	        testUtil.setHardWait(700);
	        String quoteNumber = testUtil.getTextFromElement(quoteNumHeader).substring(19);
	        System.out.println(quoteNumber);
	        return quoteNumber;
	    }
	    
	    public void verifyQuoteNumberPrefix(String serviceLevel, String prefix) {
	        testUtil.init(this);
	        testUtil.setHardWait(700);
	        String quoteNumber = recordQuoteNumber(serviceLevel);
	        System.out.println("quoteNumber "+ quoteNumber);
	        assertTrue(quoteNumber.startsWith("prefix :" + prefix));
	        System.out.println("Prefix is: "+ quoteNumber.substring(0, 1));
	    }
	
	
	
	
}
	
