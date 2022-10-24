package eNetPages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetTimeCriticalExceptionQueue extends TestBase {

	private Logger logger = Logger.getLogger(ENetTimeCriticalExceptionQueue.class);

	@FindBy(how = How.XPATH, using = "(//input[@id='fromDate'])[1]")
	private WebElement dateFrom;

	@FindBy(how = How.XPATH, using = "//select[@id='fromTimeMarker']")
	private WebElement dateFromAmOrPm;

	@FindBy(how = How.XPATH, using = "(//input[@name='accountNumber'])[1]")
	private WebElement account;

	@FindBy(how = How.XPATH, using = "(//input[@name='companyName'])[1]")
	private WebElement companyName;

	@FindBy(how = How.XPATH, using = "//input[@value='Submit']")
	private WebElement submitButton;
	
	@FindBy(how=How.XPATH, using="//iframe[@id='mainpage']")
    private WebElement mainPage;
	
	@FindBy(how=How.XPATH, using = "//select[@id='myRole']/option[@selected]")
    private WebElement role;
	/************************************ METHODS ***************************/
	
	//@ Justin- need to update the element for the lines commentted below for all the (mainPage)

	public void inputDateFrom(String mmDDyyyy) {
		logger.info("Inputting mm/dd/yyyy to identify specific quote " + mmDDyyyy);
		testUtil.init(this);
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(1000);
		dateFrom.sendKeys(mmDDyyyy);
	}

	public void inputTimeOfDay(String amOrPm) {
		logger.info("Inputting timeOfDay: " + amOrPm);
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.selectUsingVisibleText(dateFromAmOrPm, amOrPm);
	}

	public void inputAccount(String accountNum) {
		logger.info("Input Account: " + accountNum);
		testUtil.init(this);
		testUtil.setHardWait(1000);
		account.sendKeys(accountNum);
	}

	public void clickSubmitButton() {
		logger.info("Click Submit Button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		submitButton.click();
	}

	public String getQuoteByTimeStamp1(String timeStamp) {
		logger.info("Get Quote by Time Stamp");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String quoteNo = null;

		List<WebElement> rowCount = driver.findElements(By.xpath("(//*[@class='output-table']//tr)[2]"));
		for (int i = 0; i <= rowCount.size(); i++) {
			int j = i + 2;
			String rowVal = driver.findElement(By.xpath(("//*[@class='output-table']//tr['" + j + "']/td[2]")))
					.getText();
			rowVal = rowVal.substring(0, rowVal.length() - 6); // Ignore miliseconds are always different when timestamp
																// is initiated and data is triggered onto a new screen.
			if (rowVal.length() >= 20) {
				rowVal = rowVal.substring(0, rowVal.length() - 1);
			}
			System.out.println("Time Sheet: " + rowVal);

			String timeStampValue = (String) timeStamp.subSequence(0, timeStamp.length() - 3);
			String timeQuoteValue = (String) rowVal.subSequence(0, rowVal.length() - 3);

			long timeStampValueInt = Long
					.parseLong(timeStampValue.replace("-", "").replace(" ", "").replace(":", "").trim());
			long timeQuoteValueInt = Long
					.parseLong(timeQuoteValue.replace("-", "").replace(" ", "").replace(":", "").trim());

			int newRowVal = Integer
					.parseInt(rowVal.substring(rowVal.length() - 2).replace(":", "").replace(" ", "").trim());
			int newTimeStamp = Integer
					.parseInt(timeStamp.substring(timeStamp.length() - 2).replace(":", "").replace(" ", "").trim());

			System.out
					.println("timeStampValueString: " + timeStampValue + " -> timeQuoteValueString: " + timeQuoteValue);
			// comparing timestamp vs. quotestamp (miliseconds and seconds)
			if ((newRowVal - newTimeStamp) < 5 && (timeQuoteValueInt - timeStampValueInt) <= 1) {
				quoteNo = driver.findElement(By.xpath("//*[@class='output-table']//tr[" + j + "]/td[1]")).getText();
				System.out.println("Quote Number is " + quoteNo);
				break;
			}
		}
		testUtil.setHardWait(2000);
		return quoteNo;
	}

	public void verifyExceptionDisplayed1(String quoteNumber, String exception) {
		logger.info("Verify No Exception displayed for Quote# " + quoteNumber);
		testUtil.init(this);
		driver.switchTo().frame(mainPage);
		int counter = 1;
		while (true) {
			counter++;
			String rowVal = driver.findElement(By.xpath("//*[@class='output-table']//tr[" + counter + "]/td[1]"))
					.getText().trim().replaceAll(" ", "");
			exception = exception.trim().replaceAll(" ", "");
			System.out.println(rowVal);
			if (rowVal.equals(quoteNumber)) {
				String actualException = driver
						.findElement(By.xpath("//*[@class='output-table']//tr[" + counter + "]/td[5]")).getText()
						.replaceAll(" ", "").replaceAll("\n", "").trim();
				System.out.println("exception: " + exception);
				System.out.println("actualException: " + actualException);
				Assert.assertTrue(actualException.contains(exception));
				break;
			}
		}
	}

	public void selectQuoteNumber(String quoteNum) {
		logger.info("Select Quote Number");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement quote = driver.findElement(By.xpath("//a[contains(text(), '" + quoteNum + "')]"));
		quote.click();

	}

	public void verifyExceptions(String... exceptionValues) {
		logger.info("Validate Exceptions");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(1000);
		logger.info("Identify specific Exceptions on screen");
		for (int i = 0; i < exceptionValues.length; i++) {
			assertTrue(driver.findElement(By.xpath("//span[contains(text(), '" + exceptionValues[i] + "')]"))
					.isDisplayed());
		}
	}

	
	//@Justin- update the lines commented below 
	public void verifyContactShippingInfo(String... contactShippingDetails) {
		logger.info("Validate Contact Shipping Info");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(1000);
		logger.info("Validate Shipping and Contact information");
		assertTrue(role.getText().contains(contactShippingDetails[0]));
		assertTrue(driver.findElement(By.xpath(
				"//select[@id='terms']/option[@selected and contains(text(), '" + contactShippingDetails[1] + "')]"))
				.isDisplayed());
		assertTrue(
				driver.findElement(By.xpath("(//input[@value='" + contactShippingDetails[2] + "'])[2]")).isDisplayed());

		for (int i = 3; i < contactShippingDetails.length; i++) {
			assertTrue(
					driver.findElement(By.xpath("//input[@value='" + contactShippingDetails[i] + "']")).isDisplayed());
		}
	}

	public void validateCommodityClass(String... values) {
		logger.info("Validate Commodity Class Pieces");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(
					driver.findElement(By.xpath("//select[@name='commodityClass_" + (i + 1) + "']/option[@selected]"))
							.getText()));
		}
	}

	public void validateCommodityPieces(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(
					driver.findElement(By.xpath("//input[@name='commodityPieces_" + (i + 1) + "']")).getText()));
		}
	}

	public void validateCommodityPiecesType(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(driver
					.findElement(By.xpath("//select[@name='commodityPieceType_" + (i + 1) + "']/option[@selected]"))
					.getText()));
		}
	}

	public void validateCommodityTotalWeight(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(
					driver.findElement(By.xpath("//input[@name='commodityTotalWeight_" + (i + 1) + "']")).getText()));
		}
	}

	public void validateCommodityLength(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(driver
					.findElement(By.xpath("//input[@name='commodityDimensionLength_" + (i + 1) + "']")).getText()));
		}
	}

	public void validateCommodityWidth(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(driver
					.findElement(By.xpath("//input[@name='commodityDimensionWidth_" + (i + 1) + "']")).getText()));
		}
	}

	public void validateCommodityHeight(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(driver
					.findElement(By.xpath("//input[@name='commodityDimensionHeight_" + (i + 1) + "']")).getText()));
		}
	}

	public void validateCommodityDescription(String... values) {
		logger.info("Validate Commodity Class Values");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < values.length; i++) {
			assertTrue(values[i].contains(
					driver.findElement(By.xpath("//input[@name='commodityDescription_" + (i + 1) + "']")).getText()));
		}
	}

	public void validateStackable() {
		logger.info("Validate if stackable is selected");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		assertTrue(stackableBox.isSelected());
	}

	@FindBy(how = How.XPATH, using = "//input[@id='stackable']")
	private WebElement stackableBox;

	public void validateAccessorials(String... accessorials) {
		logger.info("Validate if Accessorial is present ");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		for (int i = 0; i < accessorials.length; i++) {
			assertTrue(driver.findElement(By.xpath("//select[@name='accessorial_" + (i + 1)
					+ "']/option[@selected and (contains(text(),'" + accessorials[i] + "'))]")).isDisplayed());
		}
	}

	public void validateAccessorial(String accessorial) {
		logger.info("Validate if Accessorial is present " + accessorial);
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		assertTrue(driver.findElement(By.xpath("//input[@value= \"" + accessorial + "\" ]")).isDisplayed());
	}

	public void clickLogOut() {
		logger.info("Click Logout Button");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		logout.click();
		testUtil.setHardWait(1000);
	}

	@FindBy(how = How.XPATH, using = "//a[@id='logout']")
	private WebElement logout;

	public void clickLogOutFromIntranet() {
		logger.info("Click Logout Button");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainPage);
		testUtil.setHardWait(2000);
		logout2.click();
	}

	@FindBy(how = How.XPATH, using = "//input[@value='Logout']")
	private WebElement logout2;

	public void inputCompanyName(String companyNum) {
		logger.info("Input Account: " + companyNum);
		testUtil.init(this);
		testUtil.setHardWait(1000);
		companyName.sendKeys(companyNum);
	}
}
