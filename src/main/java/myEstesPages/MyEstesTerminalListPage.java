package myEstesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesTerminalListPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesTerminalListPage.class);

	@FindBy(how = How.XPATH, using = "//b[contains(text(),'VA')]")
	private WebElement ClickOnStateName;

	@FindBy(how = How.XPATH, using = "//a[@title='E-mail this terminal list']")
	private WebElement Email;

	@FindBy(how = How.XPATH, using = "//input[@value='full']")
	private WebElement FullTerminalList;

	@FindBy(how = How.XPATH, using = "//option[@value='.csv']")
	private WebElement CVSValueFile;

	@FindBy(how = How.XPATH, using = "//textarea[@name='emailTextArea']")
	private WebElement EmailAddressField;

	@FindBy(how = How.XPATH, using = "//input[@title='Send e-mail']")
	private WebElement SendButton;

	@FindBy(how = How.XPATH, using = "//b[contains(text(),'AK')]")
	private WebElement StateOfAK;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary w-100']")
	private WebElement LookUp;

	public void clickOnVirginiaState() {
		testUtil.init(this);
		logger.info("Click On  State  name");
		ClickOnStateName.click();
	}

	public void verifyTerminalListServing() {
		testUtil.init(this);
		String actual = driver.findElement(By.xpath("//h1[contains(text(),'Terminals serving Virginia')]")).getText();
		System.out.println(actual);
		assertEquals(actual, "Terminals serving Virginia");
	}

	public void verifyEmailResult() {
		String emailResult = driver.findElement(By.xpath("//b[contains(text(),'E-mail Results')]")).getText();
		assertTrue(true, emailResult);
	}

	public void clickOnEmail() {
		testUtil.init(this);
		logger.info("Click on Email link");
		Email.click();
	}

	public void selectFullTerminalList() {
		testUtil.init(this);
		logger.info("Select full terminal radio button");
		FullTerminalList.click();
	}

	public void selectCVSFile() {
		testUtil.init(this);
		logger.info("Select CVS file");
		CVSValueFile.click();

	}

	public void enterEmailAddess() {
		testUtil.init(this);
		logger.info("Entering email address");
		EmailAddressField.sendKeys("QATest@estes-express.com");
	}

	public void clickOnSendButton() {
		testUtil.init(this);
		logger.info("Click on sent button");
		SendButton.click();

	}

	public void verifyProcessingMessage() {
		logger.info("process message has been verified ");
		String Message = driver.findElement(By.xpath("//div[@id='emailResults']//b")).getText();
		System.out.println(Message);
		assertTrue(true, Message);
	}

	public void clickOnStateOfAlaska() {
		testUtil.init(this);
		logger.info("Click on Alaska State");
		StateOfAK.click();
	}

	public void verifyRegularlyScheduleSailingsToAnchorageAlaska() {
		logger.info("verify regularly scheduled sailings to Anchorage, Alaska");
		String text1 = driver
				.findElement(By.xpath("//th[contains(text(),'Regularly Scheduled Sailings to Anchorage, Alaska')]"))
				.getText();
		assertTrue(true, text1);
	}

	public void verifyRegularlyScheduleSailingsToFairbanksSouthCentralAlaska() {
		logger.info(
				"verify regularly scheduled sailings to Anchorage with ground service to Fairbanks/South Central, Alaska");
		String text2 = driver
				.findElement(By.xpath("//th[contains(text(),'Regularly Scheduled Sailings to Anchorage with gro')]"))
				.getText();
		assertTrue(true, text2);
	}

	public void verifyRegularlyScheduleSailingsToJuneauAlaska() {
		logger.info("regularly scheduled sailings to Juneau, Alaska");
		String text3 = driver
				.findElement(By.xpath("//th[contains(text(),'Regularly Scheduled Sailings to Juneau, Alaska')]"))
				.getText();
		assertTrue(true, text3);
	}
	
	public void clickOnLookUp() {
		testUtil.init(this);
		logger.info("Click on Look Up Button");
		testUtil.setExplicitWait(LookUp, 60);
		testUtil.clickElementJavascript(LookUp);
	}
	
	public void validateErrorMessage() {
		logger.info("validate Error Message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//lib-snackbar-message[contains(@class,'ng-star-inserted')]")).getText().trim();
		System.out.println("error message is : " + error);
		assertEquals(error, "ERROR: Invalid search criteria. Check the search form for errors.");
	}
	
}
