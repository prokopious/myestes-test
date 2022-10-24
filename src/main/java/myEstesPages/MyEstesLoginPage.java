package myEstesPages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesLoginPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesLoginPage.class);

	/*********** ELEMENTS ***********/
	// My Estes
	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement weMyEstes;
	// Request Account Number From Drop Down
	@FindBy(how = How.XPATH, using = "//*[@id=\"main-nav\"]/ul[2]/li/div/a[2]")
	private WebElement weReqAccNumFromDropDown;
	// My Estes Login From Drop Down
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[1]")
	private WebElement weLoginFromDropDown;
	// Create Account From My Estes Drop Down
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[2]")
	private WebElement weCreateAccFromDropDown;
	// Login Button
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement weLoginButton;
	// Login Button Error Message
	@FindBy(how = How.ID, using = "mat-error-0")
	private WebElement weLoginErrorMessage;
	// Invalid Credentials
	@FindBy(how = How.XPATH, using = "//*[@id='login-component']/form/div/div[1]/div[1]")
	private WebElement weInvalidCredentialMessage;
	// Logout Button
	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'][contains(text(),'Logout')]")
	private WebElement weLogoutButton;
	// Logout confirm Button
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Confirm')]")
	private WebElement weLogoutConfirmButton;
	// My Estes Login Text
	@FindBy(how = How.XPATH, using = "//*[@id='content']/div/div[1]/lib-login/h1/span")
	private WebElement weMyEstesLoginText;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign Up')]")
	private WebElement SignUp;

	@FindBy(how = How.ID, using = "inputUsername")
	private WebElement UserName;

	@FindBy(how = How.ID, using = "inputPassword")
	private WebElement Password;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement LogInButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"login-component\"]/form/div/div[1]/div[3]/a")
	private WebElement ForgotPassword;

	// LTL Rate Quote
	@FindBy(how = How.XPATH, using = "//*[@id='quickLinks']/div/div[2]/ul/li[2]/a")
	private WebElement weLTLRAteQuote;

	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item']//span[@class='fal fa-cog']")
	private WebElement EditMyProfile;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'My Estes Login')]")
	private WebElement loginpage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'The username/password you entered does not match')]")
	private WebElement loginError;

	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item']//span[@class='fal fa-book-open']")
	private WebElement commodityLibrary;


	@FindBy(how = How.XPATH, using = "//*[@id='j_username']")
	private WebElement userID;

	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	private WebElement loginButton;



	/******************************************************************/

	public void enterUnameAndpwd(String username, String password) {
		logger.info("Enter user and password -");
		testUtil.init(this);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		UserName.click();
		testUtil.setHardWait(1000);
		UserName.sendKeys(username);
		logger.info("Enter username -");
		testUtil.setHardWait(1000);
		Password.click();
		testUtil.setExplicitWait(Password, 60);
		Password.sendKeys(password);

	}

	public void enterUserName() {
		logger.info("Enter username");
		testUtil.init(this);
		testUtil.assetWait(null, UserName, 10, 200, TimeUnit.MILLISECONDS);
		UserName.sendKeys(username1);
	}

	public void enterPassword() {
		logger.info("Enter password");
		testUtil.init(this);

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputPassword")));
		driver.findElement(By.id("inputPassword")).click();
		driver.findElement(By.id("inputPassword")).sendKeys(password1);

	}

	public void clickonSubmitButton() {
		logger.info("Click on login button");
		testUtil.init(this);
		LogInButton.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnEditMyProfile() {
		logger.info("Click on Edit my profile link");
		testUtil.init(this);
		testUtil.setExplicitWait(EditMyProfile, 120);
		testUtil.clickElementJavascript(EditMyProfile);

	}

	public void clickonForgotPasswordLink() {
		logger.info("Click on Forgot Password link");
		testUtil.init(this);
		ForgotPassword.click();
		testUtil.setHardWait(500);
	}

	public void clickonSignUpLink() {
		logger.info("Click on SignUp Now link");
		testUtil.init(this);
		SignUp.click();
	}

	// click on LTL Rate Quote
	public void clickOnLTLRateQuoteLink() {
		logger.info("Click on LTL Rate Quote");
		testUtil.init(this);
		weLTLRAteQuote.click();
	}

	// click on My Estes
	public void clickOnMyEstes() {
		logger.info("Click on My Estes");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		testUtil.clickElementJavascript(weMyEstes);
		// weMyEstes.click();
	}

	// Click On Request Account Number From Drop Down
	public void clickOnReqAccNumFromDropDown() {
		logger.info("Click on Request Account number from drop down");
		testUtil.init(this);
		WebElement ele = testUtil.filterXpath("//a", null, null, 1, "Request Account Number");
		testUtil.clickElementJavascript(ele);

	}

	public void clickOnLoginFromDDown() {
		logger.info("Click on My Estes Login From Drop Down");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		weLoginFromDropDown.click();
	}

	public void clickOnLoginButton() throws InterruptedException {
		logger.info("Click on My Estes login button");
		testUtil.init(this);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
			weLoginButton.click();
			testUtil.clickElementJavascript(weLoginButton);
			testUtil.setHardWait(1000);
			testUtil.fluentWait(By.xpath("//span[contains(text(),'My Estes')]"), 55, 5, "My Estes");
			testUtil.setHardWait(500);
		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}
	}
	
	// An alternative method for login with awaitility class
	
	
	
	// anther login method without fluet wait
	
	public void clickOnLoginButtonWithoutFluentWait() throws InterruptedException {
        logger.info("Click on My Estes login button");
        testUtil.init(this);
        WebElement submitButton = testUtil.filterSelector(null, weLoginButton, null, 1);
        testUtil.clickElementJavascript(submitButton);
        verifySuccessfulLogin();
    }
    
	    public void verifySuccessfulLogin() {
        logger.info("Verify successful login");
        testUtil.init(this);
        WebElement dropdown = testUtil.filterSelector("span", null, null, 0, "fa-sign-out");
        Assert.assertNotNull(dropdown);
    }
	

	


	public void clickOnLogin1() throws InterruptedException {
		logger.info("Click on My Estes login button");
		testUtil.init(this);
		testUtil.setExplicitWait(weLoginButton, 60);
		weLoginButton.click();

		testUtil.fluentWait(By.xpath("//span[contains(text(),'Your My Estes User Profile')]"), 45, 5,
				"Your My Estes User Profile");
		// testUtil.setHardWait(5000);

	}

	public void clickOnLogin2() throws InterruptedException {
		logger.info("Click on My Estes login button");
		testUtil.init(this);
		testUtil.setExplicitWait(weLoginButton, 60);
		weLoginButton.click();

		testUtil.fluentWait(
				By.xpath("//mat-card/mat-card-content/h4[contains(text(),'You do not have access to this page.')]"), 45,
				5, "You do not have access to this page.");
		// testUtil.setHardWait(5000);

	}

	public void clickOnLoginWithoutFluentWait() throws InterruptedException {
		logger.info("Click on My Estes login button");
		testUtil.init(this);
		testUtil.setExplicitWait(weLoginButton, 60);
		weLoginButton.click();
		// testUtil.fluentWait(By.xpath("//lib-routable-component-header/h1/span[contains(text(),'Online
		// Reporting')]"), 45, 5, "Online Reporting".trim());

	}

	public void clickOnCreateAccFromDropDown() {
		logger.info("Click on My Estes create account from My Estes drop down");
		testUtil.init(this);
		weCreateAccFromDropDown.click();
	}

	// Get Login Error Message
	public String getLoginErrorMessage() {
		logger.info("Get login error message");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String ErrorMessage = weLoginErrorMessage.getText();
		return ErrorMessage;

	}

	// Get Invalid Credential Message
	public void invidCredentialMessageDisplayed() {
		testUtil.init(this);
		logger.info("Get login error message");
		weInvalidCredentialMessage.isDisplayed();
	}

	// Click On My Estes Logout Button
	public void clickOnLogoutButton() {
		logger.info("Click on My Estes Logout button");
		testUtil.init(this);
		testUtil.setExplicitWait(weLogoutButton, 60);
		testUtil.clickElementJavascript(weLogoutButton);
	}

	// Click On My Estes Logout Confirm Button
	public void clickOnLogoutConfirmButton() {
		logger.info("Click on My Estes Logout confirm button");
		testUtil.init(this);
		weLogoutConfirmButton.click();
	}

	// Get My Estes Login Text
	public void getMyEstesloginTextDisplayed() {
		logger.info("Get My Estes login text");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/div[1]/lib-login/h1/span")));
		weMyEstesLoginText.isDisplayed();

	}

	public void enterUserName(String uName) {
		logger.info("Enter username -" + uName);
		testUtil.init(this);
		testUtil.assetWait(null, UserName, 10, 200, TimeUnit.MILLISECONDS);
		UserName.click();
		UserName.sendKeys(uName);

		// testUtil.setExplicitWait(UserName, 90);

		/*
		 * for(int i=0; i<= 2; i++){ try { testUtil.clickElementJavascript(UserName);
		 * break; } catch(Exception e) {
		 * System.out.println("No such element expection occured");
		 * 
		 * } }
		 */
		/*
		 * new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class)
		 * .until(ExpectedConditions.elementToBeClickable(By.id("inputUsername")));
		 * driver.findElement(By.id("inputUsername")).sendKeys(uName);
		 * 
		 * testUtil.setHardWait(2000);
		 */
	}

	public void enterPassword(String pass) {
		logger.info("Enter user password -");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, Password, 13, 250, TimeUnit.MILLISECONDS);
		Password.click();
		// testUtil.setHardWait(1000);
		Password.sendKeys(pass);
		// driver.findElement(By.id("inputPassword")).sendKeys(pass);

	}

	public boolean isLoginPageDisplayed() {
		logger.info("Login page displayed");
		boolean login = loginpage.isDisplayed();
		return login;
	}

	public void verifyLoginPage() {
		logger.info("Verify my estes login page is displayed");
		assertTrue(isLoginPageDisplayed());
	}

	public void loginPageDisplayed() {
		logger.info("Verify MyEstes login page is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		loginpage.isDisplayed();
		testUtil.setHardWait(1000);
	}

	public void verifyLoginErrorMessage() {
		logger.info("Get login error message");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String expected = "The username/password you entered does not match our records. Please try again.";
		String actual = loginError.getText();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	public void clickOnCommodityLibrary() {
		logger.info("Click on Commodity library");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityLibrary, 20);
		commodityLibrary.click();
	}

	public void usernameInput(String username) {
		testUtil.init(this);
		logger.info("Sign into Username");
		userID.sendKeys(username);
		testUtil.setHardWait(500);
	}

	public void passwordInput(String passwordInput) {
		testUtil.init(this);
		logger.info("Sign into Username");
		password.sendKeys(passwordInput);
		testUtil.setHardWait(500);
	}


	public void clickLoginButton() {
		testUtil.init(this);
		logger.info("Click Login Button");
		loginButton.click();
		testUtil.setHardWait(500);
	}


}
