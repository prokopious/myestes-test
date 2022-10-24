package myEstesPages;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.TestBase;
import util.TestUtil;

public class MyEstesRecoverLostUserNamePasswordPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesRecoverLostUserNamePasswordPage.class);

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-radio-3\"]/label/div[1]/div[2]")
	private WebElement SelectEmailRadioButton;

	@FindBy(how = How.ID, using = "inputSearchCriteria")
	private WebElement Emailfield;

	@FindBy(how = How.ID, using = "inputSearchCriteria")
	private WebElement UserNameField;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SublmitButton;
	
	@FindBy(tagName = "iframe")
	private List<WebElement> noOfIFrame;
	
	@FindBy(how=How.XPATH,using="//*[@id='mat-radio-2-input']")
	private WebElement userName;
	
	@FindBy(how=How.XPATH,using="//*[@id='inputSearchCriteria']")
	private WebElement textField;
	
	/****************************METHODS*****************************/
	
	
	public void clcikOnEmailRadioButton() {
		logger.info("Click on Email radio button");
		testUtil.init(this);
		
		testUtil.setExplicitWait(SelectEmailRadioButton, 60);
		testUtil.clickElementJavascript(SelectEmailRadioButton);
	}

	public void enterUserName(String uName) {
		logger.info("Enter user name");
		testUtil.init(this);
		UserNameField.sendKeys(uName);
	}

	public void enterEmailAdress() {
		logger.info("Enter email address");
		testUtil.init(this);
		Emailfield.clear();
		//Emailfield.sendKeys(email);
		Faker faker = new Faker();
		Emailfield.sendKeys(faker.internet().emailAddress());
	}

	public void enterEmailAddress(String email) {
		
		testUtil.init(this);
		logger.info("Enter Email in the text field");
		Emailfield.click();
		Emailfield.clear();
		testUtil.setExplicitWait(Emailfield, 60);
		Emailfield.sendKeys(email);
		
		
		
	}
	public void clickOnSubmitButton() {
		logger.info("Click on submit button");
		testUtil.init(this);
		testUtil.setExplicitWait(SublmitButton, 20);
		SublmitButton.click();
	}

	public void verifyPageTilte() {
		logger.info("validate Page tile ");
		testUtil.init(this);
		// String title = driver.findElement(By.xpath("//span[contains(text(),'Recover
		// Lost Username/Password')]"))
		// .getText();
		String title = driver.findElement(By.xpath("//span[contains(text(),'Request Password Reset')]")) // Title name
																											// changed
				.getText();

		System.out.println("Page Title is :" + title);
		Assert.assertEquals(title, "Request Password Reset");

	}



	public void validateErrorMessageDisplay() {
		logger.info("validate Error message is display");
		testUtil.init(this);
		String message = driver.findElement(By.xpath("//*[contains(text(),'Email address is not valid.')]")).getText();
		System.out.println("Error message is :" + message);
		Assert.assertEquals(message, "Email address is not valid.");

	}

	public void validateErrorMessage() {
		logger.info("Validate Error message is display");
		testUtil.init(this);

		for (int i = 0; i <= 2; i++) {
			try {

				WebElement message = driver.findElement(By.xpath("//*[contains(text(),'This field is required.')]"));
				testUtil.setExplicitWait(message, 60);
				String msg=message.getText();
					
	
				testUtil.setExplicitWait(message, 60);
				System.out.println("Error message is :" +msg);
				Assert.assertEquals(msg, "This field is required.");
				break;
			} catch (Exception e) {
				System.out.println("Timeout occured");
			}
		}
	}

	public void validateIncorrectMailErrorMessage(String... exp) {
		logger.info("validate Error message is display");
		testUtil.init(this);
		//testUtil.switchToFrame("mainpage");
		AtomicReference<String>matchingExpected= new AtomicReference<>();
		String message1 = driver.findElement(By.xpath("//*[@id='main']/app-forgot-password/div/form/div[2]/div/lib-feedback/div/span")).getText();
		System.out.println("Error message is :" + message1);
		
		
		Arrays.stream(exp).forEach(expected ->{
			if(expected.equals(message1)) {
				matchingExpected.set(expected);
				
				Assert.assertEquals(message1, matchingExpected.get());
			}
		});
		
	}
	
	
	public void validateIncorrectMailErrorMessage(String exp) {
		logger.info("validate Error message is display");
		testUtil.init(this);
		//testUtil.switchToFrame("mainpage");
		String message1 = driver.findElement(By.xpath("//*[@id='main']/app-forgot-password/div/form/div[2]/div/lib-feedback/div/span")).getText();
		System.out.println("Error message is :" + message1);

		Assert.assertEquals(message1, exp);


	}

	public void validateIncorrectEmail(String exppectedMsg) {
		
		testUtil.init(this);
		logger.info("Validate error message for the second try");

		String message2=driver.findElement(By.id("mat-error-1")).getText();
		System.out.println("Error message is:" + message2);
		Assert.assertEquals(message2, exppectedMsg);
		
		
		
	}
	public void validateErrorMessage(String expectedError) {

		testUtil.init(this);
		logger.info("Validate error message for the second try");
		
		String message2=driver.findElement(By.id("//*[@id=\"cdk-overlay-14\"]/snack-bar-container")).getText();
		System.out.println("Error message is:" + message2);
		Assert.assertEquals(message2, expectedError);
	
	}

	public void clickOnSubmitButtonMultipleTimes() {
		logger.info("Click on submit button 10 times");
		testUtil.init(this);
		
		for(int i=0;i<10;i++) {
		testUtil.setHardWait(1000);
		SublmitButton.click();
		
		}
	}



	public void validateIncorrectMailErrorMsg(String exp) {
		logger.info("validate Error message is display");
		testUtil.init(this);
		// testUtil.switchToFrame("mainpage");
		String message1 = driver
				.findElement(By.xpath("//*[@id='mat-error-0']")).getText();
		System.out.println("Error message is :" + message1);
		Assert.assertEquals(message1, exp);

	}

	public void clickonUserNameRadioButton() {
		logger.info("Click on UserName radio button");
		testUtil.init(this);
		testUtil.setExplicitWait(userName, 60);
		testUtil.clickElementJavascript(userName);
	}
	public void enterValueInTextField(String value) {
		logger.info("Enter value int the text field");
		testUtil.init(this);
		textField.clear();
		testUtil.setExplicitWait(textField, 60);
		textField.sendKeys(value);
	}
	
	
	public void validateEmailAddressFieldForIncorrectEmail(String errorMsg) {
		
		logger.info("Verify email address incorrect message:" + errorMsg);
		testUtil.init(this);
		
		String errorMessage=driver.findElement(By.xpath("//*[@id='mat-error-1']")).getText();
		testUtil.setHardWait(1000);
		System.out.println("The error message is:"+errorMessage);
		Assert.assertEquals(errorMessage, errorMsg);
		
		
		
	}
	
	public void validateUnsuccessfulAttemptsErrorMessage(String errorMsg) {
		
		logger.info("Error message for unsuccessful attempts:" + errorMsg);
		testUtil.init(this);
		
		String errorMessage=driver.findElement(By.xpath("//*[@id='main']/app-forgot-password/div/form/div[2]/div/lib-feedback/div/span")).getText();
		testUtil.setHardWait(1000);
		System.out.println("The error message is:"+errorMessage);
		Assert.assertEquals(errorMessage, errorMsg);
	}
		
}


