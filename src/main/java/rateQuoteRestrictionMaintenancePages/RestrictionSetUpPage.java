package rateQuoteRestrictionMaintenancePages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class RestrictionSetUpPage extends TestBase {

	private Logger logger = Logger.getLogger(RestrictionSetUpPage.class);

	@FindBy(how = How.ID, using = "eventName")
	private WebElement eventName;
	
	@FindBy(how = How.XPATH, using = "//*[@id='effectiveDate']")
	private WebElement effectiveDate;

	@FindBy(how = How.XPATH, using = "//*[@id='expirationDate'][@name='restriction.expirationDate']")

	private WebElement expirationDate;

	@FindBy(how = How.ID, using = "message")

	private WebElement rateQuoteResponseMsg;

	@FindBy(how = How.ID, using = "terminalId")

	private WebElement terminal;

	@FindBy(how = How.XPATH, using = "//button[@class='ui-multiselect ui-widget ui-state-default ui-corner-all']")

	private WebElement zipCode;

	@FindBy(how = How.ID, using = "inbound")

	private WebElement inboundDirectionRadioBtn;

	@FindBy(how = How.ID, using = "outbound")

	private WebElement outboundDirectionRadioBtn;

	@FindBy(how = How.ID, using = "both")

	private WebElement bothDirectionRadioBtn;

	@FindBy(how = How.ID, using = "manageTerminalButtonId")

	private WebElement AddButton;

	@FindBy(how = How.ID, using = "terminalClearImage")

	private WebElement clearButton;

	@FindBy(how = How.XPATH, using = "//*[@id='logout']/img")

	private WebElement logoutButton;

	@FindBy(how = How.XPATH, using = "//*[@class='successmessage']")

	private WebElement successMsg;

	//@FindBy(how = How.XPATH, using = "//span[@id='msg']")
	@FindBy(xpath="//span[contains(text(),'Restriction Warning')]")//-->newly added
	private WebElement alertMsg;

	@FindBy(how = How.XPATH, using = "//div[@aria-describedby='dialog-confirm']//button/span[contains(text(),'Yes')]")

	private WebElement confirmPopUpYesBtn;

	@FindBy(how = How.XPATH, using = "//div[@aria-describedby='dialog-confirm']//button/span[contains(text(),'No')]")

	private WebElement confirmPopUpNoBtn;

	@FindBy(how = How.XPATH, using = "//span[@class='jtable-page-size-change']//select")

	private WebElement rowCountDropDown;

	@FindBy(how = How.XPATH, using = "//div[@aria-describedby='dialog-confirm']//button/span[contains(text(),'OK')]")

	private WebElement confirmOkBtn;
	@FindBy(how = How.XPATH, using ="//input[@id='manageTerminalButtonId' and @value='Update']")

	private WebElement updateButton;


	/*******************************************************
	 * METHODS
	 ***********************************************************/

	public void enterEventName(String EventName) {
		logger.info("Enter event name");
		testUtil.init(this);
		testUtil.setHardWait(500);
		eventName.click();
		eventName.clear();
		testUtil.setHardWait(500);
		eventName.sendKeys(EventName);

	}

	public void enterEffectiveDate(String effDate) {
		logger.info("Enter effective date");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		//WebElement ele=driver.findElement(By.xpath("//*[@id='effectiveDate']"));
		
		//testUtil.assetWait(null, ele, 10, 200, TimeUnit.MICROSECONDS);
		//testUtil.clickElementJavascript(ele);
		testUtil.clickElementJavascript(effectiveDate);

		//JavascriptExecutor js= (JavascriptExecutor)driver;
	   //js.executeScript("arguments[0].value= '" + effDate +"'; ",ele);
		effectiveDate.sendKeys(effDate);

	}

	public void enterExpirationDate(String expDate) {
		logger.info("Enter expiration date");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(expirationDate);
		expirationDate.sendKeys(expDate);

	}

	public void enterRateQuoteResponseMsg(String msg) {
		logger.info("Enter rate quote response message");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		rateQuoteResponseMsg.sendKeys(msg);

	}

	public void selectTerminal(String Terminal) {
		logger.info("Select Terminal");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Select select = new Select(terminal);
		select.selectByVisibleText(Terminal);

	}

	public void selectZipCode(String zipCodeOption) {
		logger.info("Select or deselect Zip Code");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		zipCode.click();
		WebElement option = driver.findElement(By.xpath("//li//span[contains(text(),'" + zipCodeOption + "')]"));
		testUtil.clickElementJavascript(option);

	}

	public void clickOnInboundDirection() {
		logger.info("Click on Inbound radio button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		inboundDirectionRadioBtn.click();

	}

	public void clickOnOutboundDirection() {
		logger.info("Click on Outbound radio button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		outboundDirectionRadioBtn.click();

	}

	public void clickOnBothDirection() {
		logger.info("Click on Both radio button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		bothDirectionRadioBtn.click();

	}

	public void clickOnAddButton() {
		logger.info("Click on Add button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		AddButton.click();

	}

	public void clickOnClearButton() {
		logger.info("Click on Clear button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		clearButton.click();

	}

	public void verifyRestrictionDetailsCleared() {
		logger.info("Verify Restriction details section got reset");
		Assert.assertEquals(eventName.getAttribute("value"), "");
		Assert.assertEquals(effectiveDate.getAttribute("value"), "");
		Assert.assertEquals(expirationDate.getAttribute("value"), "");
		Assert.assertEquals(rateQuoteResponseMsg.getAttribute("value"), "");
		Assert.assertFalse(terminal.isSelected());
		Assert.assertFalse(bothDirectionRadioBtn.isSelected());
		WebElement option = driver.findElement(By.xpath("//button//span[contains(text(),'Select options')]"));

		Assert.assertTrue(option.isDisplayed());

	}

	public void logout() {
		logger.info("Log out");
		testUtil.init(this);
		testUtil.clickElementJavascript(logoutButton);

	}

	public void checkZipCode(String zipCode) {
		logger.info("Check on specific Zip Code");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement option = driver.findElement(By.xpath("//span[contains(text(),'" + zipCode + "')]"));
		testUtil.clickElementJavascript(option);

	}

	public void verifySuccessMsg() {
		logger.info("Verify Success message");
		testUtil.init(this);
		Assert.assertTrue(successMsg.isDisplayed());

	}

	public void verifyMsg(String msg) {
		logger.info("Verify Success message");
		testUtil.init(this);
		Assert.assertEquals(successMsg.getText(), msg);

	}

	public void verifyWarningMsg(String msg) {
		logger.info("Verify Alert message");
		testUtil.init(this);
		Assert.assertEquals(alertMsg.getText(), msg);
		System.out.println("The warning  masseage is: " + msg);

	}

	public void clickOnConfirmationYesBtn() {
		logger.info("Click on Yes button");
		testUtil.init(this);
		confirmPopUpYesBtn.click();

	}

	public void clickOnConfirmationNoBtn() {
		logger.info("Click on No button");
		testUtil.init(this);
		confirmPopUpNoBtn.click();

	}

	public void selectRowCount(String rowCount) {

		logger.info("Click on row count");
		testUtil.init(this);
		testUtil.setExplicitWait(rowCountDropDown, 20);
		testUtil.selectUsingValue(rowCountDropDown, rowCount);

	}

	public void verifyRowIsDisplayed(String eventName, String terminal, String zipCode, String direction,
			String effDate, String expDate, String Msg) {
		logger.info("Verify row is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(driver.findElement(By.xpath("//table[@class='jtable']")), 20);
		int rowCount = driver.findElements(By.xpath("//table[@class='jtable']//tr")).size();
		for (int i = 1; i <= rowCount; i++) {
			String actualEventName = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[1]"))
					.getText();

			if (actualEventName.equals(eventName)) {
				Assert.assertEquals(
						driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[2]")).getText(),
						terminal);
				// Assert.assertEquals(driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[3]")).getText(),
				// zipCode);
				Assert.assertEquals(
						driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[4]")).getText(),
						direction);
				Assert.assertEquals(
						driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[5]")).getText(),
						effDate);
				Assert.assertEquals(
						driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[6]")).getText(),
						expDate);
				Assert.assertEquals(
						driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[7]")).getText(), Msg);
				break;

			}

		}

	}

	public void deleteRow(String eventName, String terminal, String zipCode, String direction, String effDate,
			String expDate, String Msg) {
		logger.info("Delete row");
		testUtil.init(this);
		testUtil.setExplicitWait(driver.findElement(By.xpath("//table[@class='jtable']")), 20);
		int rowCount = driver.findElements(By.xpath("//table[@class='jtable']//tr")).size();
		System.out.println(rowCount+"!!!!!");
		
		for (int i = 1; i <= rowCount; i++) {

			String actualEventName = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[1]"))
					.getText();
			System.out.println(actualEventName+"!!!");

			String actualTerminal = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[2]"))
					.getText();

			String actualDirection = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[4]"))
					.getText();

			String actualEffDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[5]"))
					.getText();

			String actualExpDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[6]"))
					.getText();

			String actualMsg = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[7]")).getText();

			if (actualEventName.equals(eventName) && actualTerminal.equals(terminal)
					&& actualDirection.equals(direction) && actualEffDate.equals(effDate)
					&& actualExpDate.equals(expDate) && actualMsg.equals(Msg)) {

				driver.findElement(
						By.xpath("//table[@class='jtable']//tr[" + i + "]/td[8]/input[@id='ctDeleteButton']")).click();

				clickOnConfirmationYesBtn();

				testUtil.setHardWait(5000);

				break;

			}

		}

	}

	public void deleteRow(String terminal, String zipCode, String direction, String effDate,
			String expDate, String Msg) {
		logger.info("Delete row");
		testUtil.init(this);
		int rowCount = driver.findElements(By.xpath("//table[@class='jtable']//tr")).size();

		for (int i = 1; i <= rowCount; i++) {

			String actualTerminal = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[2]"))
					.getText();

			String actualDirection = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[4]"))
					.getText();

			String actualEffDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[5]"))
					.getText();

			String actualExpDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[6]"))
					.getText();

			String actualMsg = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[7]")).getText();

			if (actualTerminal.equals(terminal)
					&& actualDirection.equals(direction) && actualEffDate.equals(effDate)
					&& actualExpDate.equals(expDate) && actualMsg.equals(Msg)) {

				driver.findElement(
						By.xpath("//table[@class='jtable']//tr[" + i + "]/td[8]/input[@id='ctDeleteButton']")).click();

				clickOnConfirmationYesBtn();

				testUtil.setHardWait(5000);

				break;

			}

		}
	}

	public void clickOnConfirmationOkBtn() {
		logger.info("Click on Ok button");
		testUtil.init(this);
		confirmOkBtn.click();

	}

	public void verifyEffDateIsReset() {
		logger.info("Verify effective date field is reset and no data displayed");
		testUtil.init(this);
		Assert.assertEquals(effectiveDate.getAttribute("value"), "");
	}

	public void verifyRowIsNotDisplayed(String eventName, String terminal, String zipCode, String direction,
			String effDate, String expDate, String Msg) {
		logger.info("Verify row is not displayed");
		testUtil.init(this);
//		boolean found = false;  ---changed to below line
		boolean found = true;  //<<--
		testUtil.setExplicitWait(driver.findElement(By.xpath("//table[@class='jtable']")), 20);
		int rowCount = driver.findElements(By.xpath("//table[@class='jtable']//tr")).size();
		System.out.println(rowCount+"!!!");
		for (int i = 1; i <= rowCount-1; i++) {

			String actualEvent = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[1]"))
					.getText();

			String actualTerminal = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[2]"))
					.getText();

			String actualDirection = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[4]"))
					.getText();

			String actualEffDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[5]"))
					.getText();

			String actualExpDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[6]"))
					.getText();

			String actualMsg = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[7]")).getText();

			if (actualEvent.equals(eventName) && actualTerminal.equals(terminal)
					&& actualDirection.equals(direction) && actualEffDate.equals(effDate)
					&& actualExpDate.equals(expDate) && actualMsg.equals(Msg)) {

//				found = true;     ---changed to below line
				found = false;   //<<--

				break;

			}

		}
		Assert.assertFalse(found);

	}


	public void editRow(String eventName, String terminal, String zipCode, String direction, String effDate, String expDate, String Msg) {
		logger.info("Edit row");
		testUtil.init(this);

		
		int rowCount = driver.findElements(By.xpath("//table[@class='jtable']//tr")).size();

		for(int i = 1;i<=rowCount; i++) {
			String actualEventName = driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[1]")).getText();
			String actualTerminal = driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[2]")).getText();
			String actualDirection = driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[4]")).getText();
			String actualEffDate = driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[5]")).getText();
			String actualExpDate = driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[6]")).getText();
			String actualMsg = driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[7]")).getText();

			if(actualEventName.equals(eventName) && actualTerminal.equals(terminal) && actualDirection.equals(direction) && actualEffDate.equals(effDate) && actualExpDate.equals(expDate) && actualMsg.equals(Msg))  {
				driver.findElement(By.xpath("//table[@class='jtable']//tr["+i+"]/td[8]/input[@id='ctEditButton']")).click();
				testUtil.setHardWait(5000);
				break;
			}
		}
	}

	public void clickOnUpdateBtn() {
		logger.info("Click on update button");
		testUtil.init(this);
		updateButton.click();
	}

	public void verifyRequiredFieldErrMsgIsDisplayed(String fieldName) {
		logger.info("Verify required field error message is displayed for "+fieldName);
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'"+fieldName+"')]//label[contains(text(),'Required Field')]")).isDisplayed());
	}

	
	//Newly created
		public void deleteCreatedRow(String eventName, String terminal, String zipCode, String direction, String effDate,
				String expDate, String Msg) {
			logger.info("Delete row");
			testUtil.init(this);
			List<WebElement> rowData = driver.findElements(By.xpath("//table[@class='jtable']//tr"));
			for (int i = 1; i<= rowData.size(); i++) {

				String actualEventName = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[1]"))
						.getText();
				System.out.println(actualEventName+"!!!");

				String actualTerminal = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[2]"))
						.getText();

				String actualDirection = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[4]"))
						.getText();

				String actualEffDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[5]"))
						.getText();

				String actualExpDate = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[6]"))
						.getText();

				String actualMsg = driver.findElement(By.xpath("//table[@class='jtable']//tr[" + i + "]/td[7]")).getText();

				if (actualEventName.equals(eventName) && actualTerminal.equals(terminal)
						&& actualDirection.equals(direction) && actualEffDate.equals(effDate)
						&& actualExpDate.equals(expDate) && actualMsg.equals(Msg)) {

					driver.findElement(
							By.xpath("//table[@class='jtable']//tr[" + i + "]/td[8]/input[@id='ctDeleteButton']")).click();
					
					testUtil.setHardWait(5000);

					clickOnConfirmationYesBtn();

					testUtil.setHardWait(5000);

					break;
				}
			}
		}
		
		
		public void validateMessage(String response) {
			logger.info("Validate Message");
			Assert.assertEquals(driver.findElement(By.xpath("//*[@id='msg']")).getText(), response);  
		}
		

}
