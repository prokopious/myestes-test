package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetQuoteAuditTrailPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetQuoteAuditTrailPage.class);

	// Quote Number
	@FindBy(how = How.XPATH, using = "//*[@name='quoteNumber']")
	private WebElement quoteNumber;

	// Submit button
	@FindBy(how = How.XPATH, using = "//*[@value='Submit']")
	private WebElement submitButton;

	@FindBy(how = How.NAME, using = "quoteNumber")
	private WebElement quoteNo;

	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Submit')]")
	private WebElement btnSubmit;

	@FindBy(how = How.ID, using = "addDestinationTerminal")
	private WebElement addDestinationTerminal;
	
	@FindBy(how = How.XPATH, using = "//*[@id='home']")
	private WebElement home;
	
	/**************************METHODS**************************************/

	public void enterQuoteNo(String quoteNo) {
		logger.info("Enter Quote number " + quoteNo);
		testUtil.init(this);
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		quoteNumber.sendKeys(quoteNo);
	}

	public void clickOnSubmit() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		submitButton.click();
	}

	// Verify OR% in Change description
	public void verifyORPercent(String orPercent) {
		logger.info("Verify change description");
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@class='output-table']//tr"));

		for (int i = 2; i <= rowCount.size(); i++) {
			String actual = driver.findElement(By.xpath("//*[@class='output-table']//tr[" + i + "]/td[3]")).getText();

			if (actual.contains("OR determined to be ")) {
				Assert.assertEquals(actual, "OR determined to be " + orPercent + ".00%");
			} else if (actual.contains("OR % determined to be ")) {
				Assert.assertEquals(actual, "OR % determined to be " + orPercent + ".0");
			}
		}
	}

	public void enterQuoteNumber(String aNum) {
		testUtil.init(this);
		logger.info("Enter Quote number");
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		quoteNo.sendKeys(aNum);
	}

	public void clickOnSubmitButton() {
		testUtil.init(this);
		logger.info("Click On Submit button");
		btnSubmit.click();
		testUtil.setHardWait(2000);
	}

	public void verifyORRecord(String value) {
		testUtil.init(this);
		logger.info("Verify or record");

		WebElement record = driver.findElement(By.xpath("//tr[td[text()='VTL'] and td[contains(text(),'OR')]]/td[3]"));
		Assert.assertTrue(record.getText().contains(value));
	}

	public void verifyIsDisplayed(String chargeDescription) throws InterruptedException {
		logger.info("Verify  a row stating VTL " + chargeDescription + " is displayed");
		List<WebElement> ele = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[*]/td[3]"));
		testUtil.setHardWait(1000);
		for (WebElement myEle : ele) {
			String Charge = myEle.getText();
			System.out.println(Charge);

			if (Charge.equalsIgnoreCase(chargeDescription)) {
				System.out.println("Displayed!!!!!");
				Assert.assertTrue(true);
				break;
			} else {
				System.out.println("Not Displayed");
			}
		}

	}

	
	public void verifyChangeDescriptionIsDisplayed(String changeDesc) {
		logger.info("Verify change description");
		testUtil.init(this);
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='output-table']//tr[*]/td[3]"));
		boolean val = false;
		for (int i = 2; i <= rows.size(); i++) {
			String actualVal = driver.findElement(By.xpath("//table[@class='output-table']//tr[" + i + "]/td[3]"))
					.getText();
			if (actualVal.equals(changeDesc)) {
				val = true;
				break;
			}
		}
		Assert.assertTrue(val);
	}

    public void verifyLinearFootageIsLessThan(String linearFootage) {
          logger.info("Verify Linear footage is lesser than "+linearFootage);
          testUtil.init(this);
          String[] desc = driver.findElement(By.xpath("//tbody//td[contains(text(),'Linear Feet calculated')]")).getText().split("as ");
          int linearFt = Integer.parseInt(desc[1]);
          boolean val = false;
          
          if(linearFt <= Integer.parseInt(linearFootage)) {
                 
                 val = true;
          }
          
          Assert.assertTrue(val);
    }

	public void verifyLinearFootageIsGreaterThan(String linearFootage) {
		logger.info("Verify Linear footage is lesser than " + linearFootage);
		testUtil.init(this);
		String[] desc = driver.findElement(By.xpath("//tbody//td[contains(text(),'Linear Feet calculated')]")).getText()
				.split("as ");
		int linearFt = Integer.parseInt(desc[1]);
		boolean val = false;

		if (linearFt >= Integer.parseInt(linearFootage)) {

			val = true;
		}

		Assert.assertTrue(val);
	}

	public void verifyChosenLinearFootageIsGreaterThan(String linearFootage) {
		logger.info("Verify Chosen Linear footage is lesser than " + linearFootage);
		testUtil.init(this);
		String[] desc = driver.findElement(By.xpath("//tbody//td[contains(text(),'Chosen linear footage to rate ')]"))
				.getText().split("= ");
		int linearFt = Integer.parseInt(desc[1]);
		boolean val = false;

		if (linearFt >= Integer.parseInt(linearFootage)) {

			val = true;
		}

		Assert.assertTrue(val);
	}


	public void reEnterQuoteNo(String quoteNo) {
        logger.info("Enter Quote number " + quoteNo);
        testUtil.init(this);
        quoteNumber.clear();
        quoteNumber.sendKeys(quoteNo);
 }
	
	public void testverifyIsDisplayed(String chargeDescription) throws InterruptedException {
		logger.info("Verify  a row stating VTL " + chargeDescription + " is displayed");
		List<WebElement> ele = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[*]/td[3]"));
		testUtil.setHardWait(1000);
		for (WebElement myEle : ele) {
			String Charge = myEle.getText();
			System.out.println(Charge);

			if (Charge.equalsIgnoreCase(chargeDescription)) {
				System.out.println("Displayed!!!!!");
				Assert.assertTrue(true);
				break;
			} else {
				System.out.println("Not Displayed");
			}
		}

	}
	

	
	public void verifyExceptionByQuote(String quoteNumber, String exception) {
		logger.info("Verify Exception for Quote# "+quoteNumber);
		testUtil.init(this);
		testUtil.setHardWait(2000);
	//	/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[*]/td[3]
		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@class='output-table']//tr"));
		boolean found = false;
		testUtil.setHardWait(1000);
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

		public void clickHome() {
			logger.info("Click on Home tab");
			driver.switchTo().defaultContent();
			testUtil.init(this);
			home.click();
			testUtil.setHardWait(1000);
		}
		
}
