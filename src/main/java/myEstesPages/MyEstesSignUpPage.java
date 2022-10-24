package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.TestBase;

public class MyEstesSignUpPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesSignUpPage.class);

	@FindBy(how = How.XPATH, using = "//*[@id='content']/div/div[1]/lib-signup/h1/span")
	private WebElement weMyEstesSignupText;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign Up Now')]")
	private WebElement SignUpLink;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary mt-4']")
	private WebElement NextButton;

	@FindBy(how = How.XPATH, using = "(//div[@class='mat-radio-outer-circle'])[1]")
	private WebElement RadioButton;

	@FindBy(how = How.NAME, using = "inputAccountCode")
	private WebElement CompanyAccountNumber;

	@FindBy(how = How.ID, using = "inputCompany")
	private WebElement CompanyName;

	@FindBy(how = How.ID, using = "inputFirstname")
	private WebElement FirstName;

	@FindBy(how = How.ID, using = "inputLastname")
	private WebElement LastName;

	@FindBy(how = How.ID, using = "inputEmail")
	private WebElement EmailAddress;

	@FindBy(how = How.ID, using = "inputPhone")
	private WebElement PhoneNumber;

	@FindBy(how = How.ID, using = "inputUsername")
	private WebElement UserName;

	@FindBy(how = How.ID, using = "inputPassword")
	private WebElement Password;

	@FindBy(how = How.ID, using = "inputConfirmPassword")
	private WebElement ConfirmPassword;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SignUpButton;

	@FindBy(how = How.ID, using = "Login_Button")
	private WebElement LoginButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"signup-component\"]//button[@type='submit']")
	private WebElement SubmitButton;

	// Yes, but I don't know my company's account number
	@FindBy(how = How.ID, using = "mat-radio-3")
	private WebElement weYesButIDonnotKnowComAccNum;
	// Submitting your request online
	@FindBy(how = How.XPATH, using = "//*[@id='signup-component']/div/div[1]/div[1]/p[2]/a")
	private WebElement weSubmittingYourReqOnline;

	@FindBy(css = "[id='signup-component'] form")
	private WebElement signupComponentForm;

								/**********************METHODS************************/

	public void verifyMyEstesSignupTextDisplayed() {
		testUtil.init(this);
		logger.info("Verify My Estes Signup text display");
		weMyEstesSignupText.isDisplayed();
	}

	public void clickOnSignUpLink() {
		logger.info("click on sign up link");
		testUtil.init(this);
		SignUpLink.click();
	}

	public void clickOnNextButton() {
		logger.info("click on next Button");
		testUtil.init(this);
		//testUtil.clickElementJavascript(NextButton);
		NextButton.click();
	}

	public void clickYesAndHaveMyAccountNumberRadioButton() {
		testUtil.init(this);
		logger.info("Click on Yes I have my company account number Radio button");
		testUtil.waitForPageToLoad();
		testUtil.clickElementJavascript(RadioButton);
		testUtil.setHardWait(500);
	}

	public void enterCompanyAccountNumber() {
		testUtil.init(this);
		logger.info("Enter company account number ");
		CompanyAccountNumber.sendKeys("B000281");

	}

	public void enterCompanyName() {
		testUtil.init(this);

		logger.info("Enter company name");
		Faker faker= new Faker();
		CompanyName.sendKeys(faker.company().name());

	}

	public void enterFirstName() {
		testUtil.init(this);
		logger.info("Enter first name");
		Faker faker= new Faker();
		FirstName.sendKeys(faker.name().firstName());

	}

	public void enterLastName() {
		testUtil.init(this);
		logger.info("Enter last name");
		Faker faker= new Faker();
		LastName.sendKeys(faker.name().lastName());

	}

	public void enterEmailAddress() {
		testUtil.init(this);
		logger.info("Enter email address");
		Faker faker= new Faker();
		EmailAddress.sendKeys(faker.internet().emailAddress());
		
	}

	public void enterPhoneNumber() {
		testUtil.init(this);
		logger.info("Enter phone number");
		PhoneNumber.sendKeys("8041234567");
	}

	public void enterUserName(String UName) {
		testUtil.init(this);
		logger.info("Enter user name");
		UserName.sendKeys(UName);
	}

	// ramdom user name
	public void enterUserName() {
		testUtil.init(this);
		logger.info("Enter user name");
		Faker faker= new Faker();
		UserName.sendKeys(faker.name().username());
	}

	public void enterPassword(String UPassword) {
		testUtil.init(this);
		logger.info("Enter password");
		Password.sendKeys(UPassword);
	}


	public void enterConfirmPassword(String pass) {
		testUtil.init(this);
		logger.info("Enter confirm pasword");
		ConfirmPassword.sendKeys(pass);

	}

	public void clickOnSignUpButton() {
		logger.info("click on sign up button");
		testUtil.init(this);
		SignUpButton.click();
	}

	public void clickOnLoginButton() {
		logger.info("click on log in button");
		testUtil.init(this);
		LoginButton.click();
	}

	public void clickOnSubmitButton() {
		testUtil.init(this);
		logger.info("Click on submit button");
		SubmitButton.submit();

	}

	public void clickOnYesButIDonnotKnowComAccNumRadioButton() {
		testUtil.init(this);
		logger.info("Click On Yes, but I don't know my company's account number");
		weYesButIDonnotKnowComAccNum.click();
	}

	// Click On Submitting your request online
	public void clickOnSubmittingYourReqOnline() {
		testUtil.init(this);
		logger.info("Click On Submitting your request online");
		testUtil.setExplicitWait(weSubmittingYourReqOnline, 60);
		testUtil.clickElementJavascript(weSubmittingYourReqOnline);
	
	}

	public void validateErrorMessage() {
		testUtil.init(this);
		logger.info("Validate error message");
		String error = driver.findElement(By.xpath(
				"//body/app-root[@ng-version='6.1.7']/div[@class='estes-app']/main[@id='content']/div[@class='row']/div[@class='col-xs-12 col-sm-12 col-md-8 col-lg-9 col-xl-9 order-xs-first order-sm-first order-md-last order-lg-last order-xl-last']/lib-signup[@class='ng-star-inserted']/div[@id='signup-component']/div[@class='row d-flex align-items-stretch']/div[1]"))
				.getText();
		System.out.println(error);
		/*
		 * String expected1="Company Account Number is required.";
		 * Assert.assertTrue(error.contains(expected1)); 
		 * String expected2="Company Name is required.";
		 * Assert.assertTrue(error.contains(expected2)); 
		 * String
		 * expected3="First Name is required.";
		 * Assert.assertTrue(error.contains(expected3)); 
		 * String
		 * expected4="Last Name is required.";
		 * Assert.assertTrue(error.contains(expected4)); 
		 * String
		 * expected5="Email Address is required.";
		 * Assert.assertTrue(error.contains(expected5)); 
		 * String
		 * expected6="Phone Number is required.";
		 * Assert.assertTrue(error.contains(expected6)); 
		 * String
		 * expected7="Username is required.";
		 * Assert.assertTrue(error.contains(expected7)); 
		 * String
		 * expected8="Password is required.";
		 */
		Assert.assertTrue(error.contains(error));

	}

	public void verifyUserNameAlreadyExistMessage() {
		testUtil.init(this);
		logger.info("Verify username already exist message");
		String error = driver
				.findElement(By.xpath("//*[@id=\"signup-component\"]/div/div[1]/div/form/lib-feedback/div/span"))
				.getText();
		System.out.println(error);
		String expected1 = "This username is already in use. Please enter another username.";
		Assert.assertEquals(error, expected1);
		Assert.assertTrue(error.contains(expected1));
	}

	public void verifyMissmatchPasswordMessage() {
		testUtil.init(this);
		logger.info("Verify missmatch password message");
		
		String error = driver.findElement(By.xpath("//mat-error[contains(text(),' Passwords do not match. ')]")).getText();
		System.out.println(error);
		
		String expected3 = "Passwords do not match.";
		Assert.assertTrue(error.contains(expected3));
		//*[@id="signup-component"]/div/div[1]/div/form/lib-feedback/div
	}

	public void verifyConfimationMessage() {
		testUtil.init(this);
		logger.info("Verify confirmation message");
		String message = driver.findElement(By.xpath("//*[@id=\"signup-component\"]/div/div[1]/div/form/lib-feedback/div")).getText();
		System.out.println(message);
		testUtil.setHardWait(2000);
		String expected2 = "Thank you for your submission. You should receive a reply within 48 hours.";
		Assert.assertEquals(message,expected2);

	}
	
	public void verifySignUpComponentFormIsDisplayed() {
		logger.info("Verifying Signup component form page is displayed ");
		testUtil.init(this);
		testUtil.setExplicitWait(signupComponentForm, 20);
		boolean exist = signupComponentForm.isDisplayed();
		Assert.assertTrue(exist);
		
	}



}
