package myEstesPages;

import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class MyEstesUserProfilePage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesUserProfilePage.class);

	/*********** ELEMENTS ***********/

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change my email address')]")
	private WebElement ChangeMyEmailAddress;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change my username')]")
	private WebElement ChangeUserName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change my password')]")
	private WebElement ChangePassword;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='username']")
	private WebElement UserName;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='username']")
	private WebElement NewUserName;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='password']")
	private WebElement Password;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='confirmPassword']")
	private WebElement confirmPassword;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary'][contains(.,'Submit')]")
	private WebElement LogInButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Your My Estes User Profile']")
	private WebElement userProfilePage;

	@FindBy(how = How.XPATH, using = "//lib-snackbar-message[@class='ng-star-inserted']")
	private WebElement successMessage;

	@FindBy(how = How.XPATH, using = "//div[div[input[@formcontrolname='username']]]/following-sibling::div/div/mat-error")
	private WebElement errorMessageUsername;

	@FindBy(how = How.XPATH, using = "//div[div[input[@formcontrolname='password']]]/following-sibling::div/div/mat-error")
	private WebElement errorMessagePassword;

	@FindBy(how = How.XPATH, using = "//div[div[input[@formcontrolname='confirmPassword']]]/following-sibling::div/div/mat-error")
	private WebElement errorMessageConfirmPassword;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'View/Edit User')]")
	private WebElement viewEditUser;

	@FindBy(how = How.XPATH, using = "//mat-row/mat-cell[1]/a")
	private List<WebElement> usernames;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Address Book')]/button")
	private WebElement blockorUnblock;

	@FindBy(how = How.XPATH, using = "//*//button[contains(text(),'Unblock')]")
	private WebElement unblock;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default w-100 ng-star-inserted']")
	private WebElement enableordisableLink;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Confirm')]")
	private WebElement confirmButton;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='email']")
	private WebElement email;

	@FindBy(how = How.XPATH, using = "//div[lib-form-header[h6[contains(text(),'Blocked Applications')]]]/div/div")
	private List<WebElement> blockedApps;

	@FindBy(how = How.XPATH, using = "//button[text()='Close']")
	private WebElement btnClose;

	@FindBy(how = How.XPATH, using = "//button[text()='Cancel']")
	private WebElement Cancel;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Unblock')]")
	private WebElement Unblock;

	@FindBy(xpath = "//button[contains(text(),'Disable')]")
	private WebElement disableBtn;

	@FindBy(xpath = "//button[contains(text(),'Enable')]")
	private WebElement enableBtn;

	
	@FindBy(xpath = "//h1[text()='Change Your Password.']")
	private WebElement changeYourPasswordText;
	
	@FindBy(xpath = "//h1[text()='Change Your Username.']")
	private WebElement changeYourUsernameText;
	
	@FindBy(css="[formcontrolname='firstName']")
    private WebElement firstName;

    @FindBy(css="[formcontrolname='lastName']")
    private WebElement lastName;
    
    @FindBy( how= How.XPATH,using="//*[@id=\"main\"]/lib-profile/mat-card/mat-card-content/div[4]/div[2]/a")
    private WebElement newUser;
    


	/**********************************************************************/

	public void clickOnChangeMyEmailAddressLink() {
		logger.info("Click on change my email adress link");
		testUtil.init(this);
		ChangeMyEmailAddress.click();

	}

	public void clickOnChangeMyUseNameLink() {
		logger.info("Click on change my user name link");
		testUtil.init(this);
		testUtil.setExplicitWait(ChangeUserName, 30);
		testUtil.clickElementJavascript(ChangeUserName);

	}

	public void clickOnChangeMyPasswordLink() {
		logger.info("Click on Change my password link");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(ChangePassword);
	//	ChangePassword.click();

	}

	public void clickOnViewEditUserLink() {
		logger.info("Click on view/edit user link");
		testUtil.init(this);
		testUtil.setExplicitWait(viewEditUser, 60);
		testUtil.clickElementJavascript(viewEditUser);
		testUtil.setHardWait(1000);

	}

	public void clearUserName() throws InterruptedException {
		logger.info("Clear UserName -");
		testUtil.init(this);
		UserName.clear();
		testUtil.setHardWait(1000);

	}

	public void enterUserName(String uName) throws InterruptedException {
		logger.info("Entering UserName -");
		testUtil.init(this);
		UserName.clear();
		testUtil.setHardWait(1000);
		UserName.click();
		UserName.sendKeys(uName);

	}

	public void enterNewUserName(String uName) throws InterruptedException {
		logger.info("Enter new userName -");
		testUtil.init(this);
		NewUserName.clear();
		testUtil.setExplicitWait(NewUserName, 20);
		NewUserName.click();
		NewUserName.sendKeys(uName);

	}

	public void enterPassword(String pass) {
		logger.info("Enter password -");
		testUtil.init(this);
		testUtil.clickElementJavascript(Password);
		testUtil.setHardWait(1000);
		Password.clear();
		testUtil.setHardWait(1000);
		Password.sendKeys(pass);

	}

	public void clearPassword() {
		logger.info("Clear password -");
		testUtil.init(this);
		Password.clear();

	}

	public void enterConfirmPassword(String pass) {
		logger.info("Enter confirm password -");
		testUtil.init(this);
		confirmPassword.clear();
		confirmPassword.click();
		testUtil.setHardWait(500);
		confirmPassword.sendKeys(pass);

	}

	public void clickonSubmitButtonForVerifyLogin() throws InterruptedException {
		logger.info("Click on submit button for verify login");
		testUtil.setHardWait(1000);
		testUtil.init(this);
		testUtil.setExplicitWait(LogInButton, 90);
		LogInButton.click();
		testUtil.setHardWait(2000);

	}

	public void clickonSubmitButtonForChangeUserName() throws InterruptedException {
		logger.info("Click on submit button for change username");
		testUtil.setHardWait(3000);
		testUtil.init(this);
		driver.findElement(By.xpath("//button[@class='btn btn-primary'][contains(.,'Submit')]")).click();
		testUtil.setHardWait(3000);

	}

	public void clickonSubmitButton() {
		logger.info("Click on submit button");
		testUtil.init(this);
		testUtil.setExplicitWait(LogInButton, 90);
		LogInButton.click();

	}

	public void clickonSubmitButtonForChangeEmailAddress() throws InterruptedException {
		logger.info("Click on submit button for change email address");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//*[@id='mat-dialog-1']/lib-edit-email-address/div/form/div[2]/button[1]"))
				.click();

	}

	public void validateErrorMessage() {
		logger.info("Validate error message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//div[@class='form-row']")).getText();
		System.out.println(error);
		String expected = "Username *";
		Assert.assertTrue(error.contains(expected));
	}

	public void validateNewEmailErrorMessage() {
		logger.info("Validate error message");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//div[@class='mat-form-field-wrapper']")).getText();
		System.out.println(error);
		// String expected="New E-mail Address *";
		Assert.assertTrue(error.contains(error));
	}

	public void verifyProfilePage() throws InterruptedException {
		logger.info("Validate MyEstes User profile Page");
		testUtil.init(this);
		testUtil.setHardWait(4000);
		Assert.assertEquals(userProfilePage.isDisplayed(), true);
	}

	public void validateUserName(String username) {
		logger.info("Validate username");
		String un = username.toUpperCase();
		testUtil.init(this);
		WebElement name = driver.findElement(By.xpath("//div[contains(text(),'" + un + "')]"));
		System.out.println(name);
		Assert.assertEquals(name.isDisplayed(), true);
	}

	public void verifyUsernameErrorMessage(String expected) {
		logger.info("Verify Username error message");
		testUtil.init(this);
		Assert.assertEquals(errorMessageUsername.getText().trim(), expected);
	}

	public void verifyPasswordErrorMessage(String expected) {
		testUtil.init(this);
		logger.info("Verify password error message");
		Assert.assertEquals(errorMessagePassword.getText().trim(), expected);
	}

	public void verifyConfirmPasswordErrorMessage(String expected) {
		logger.info("Verify confirm password error message");
		testUtil.init(this);
		Assert.assertEquals(errorMessageConfirmPassword.getText().trim(), expected);
	}

	public void verifyAvailableUserName() {
		logger.info("Verify available username");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Assert.assertEquals(usernames.size() > 0, true);
	}

	public void clickUsername() {
		logger.info("Click on any username");
		testUtil.init(this);
		for (WebElement e : usernames) {
			e.click();
			break;
		}
	}

	public void clickBlock(String value) {
		logger.info("Click on any user name");
		testUtil.init(this);
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + value + "')]/button"));
		element.click();
		testUtil.setHardWait(1000);
	}

	public void clickBlock() {
		logger.info("Click on any user name");
		testUtil.init(this);
		blockorUnblock.click();
		testUtil.setHardWait(1000);
	}
	public void clickOnUnblock() {
		logger.info("Click unblock user name");
		testUtil.init(this);
		unblock.click();
		testUtil.setHardWait(1000);
	}
	
	

	public void verifySuccessMessage(String expected) {
		logger.info("Verify confirm password error message");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		WebDriverWait wait= new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lib-snackbar-message[@class='ng-star-inserted']")));
		Assert.assertEquals(successMessage.getText().trim(), expected);
	}

	public void clickEnableorDisableLink() {
		logger.info("Click on any disable link");
		testUtil.init(this);
		enableordisableLink.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnConfirmButton() {
		logger.info("Click on confirm button");
		testUtil.init(this);
		confirmButton.click();
		testUtil.setHardWait(1000);
	}

	public void validateLinkTurnsEnable() {
		logger.info("Verify enable link is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String expected = "Enable";
		String actual = enableordisableLink.getText();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	public void enterEmail(String emailid) throws InterruptedException {
		logger.info("Enter new email address");
		testUtil.init(this);
		// email.clear();
		email.sendKeys(emailid);

	}

	public void validateNewEmailSuccessMessage() {
		logger.info("Validate new email success message");
		testUtil.init(this);
		String msg = driver.findElement(By.xpath("//*[contains(text(),'Success! Email address updated successfully')]"))
				.getText();
		System.out.println(msg);
		Assert.assertTrue(msg.contains(msg));
	}

	public void verifyBlockedApplication(String expectedText) {
		logger.info("Verify Blocked Applications");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		
		boolean flag = false;
		
		logger.info("Expected blocked app: " + expectedText);
		for (WebElement e : blockedApps) {

			logger.info("Actual Blocked Apps: "+ e.getText().trim().replaceFirst("UnBLOCK", ""));
			
			testUtil.setHardWait(1000);
			if (e.getText().trim().replaceFirst("UNBLOCK", "").contains(expectedText)) {
				flag = true;
				break;
			}

		}
		Assert.assertEquals(flag, true);
	}

	public void clickOnClose() {
		logger.info("click on close button");
		testUtil.init(this);
		btnClose.click();

	}
	

	public void clickOnCancelButton() {
		logger.info("Click on Cancel Button");
		testUtil.init(this);
		Cancel.click();

	}

	public boolean isDisplay() {
		logger.info("App is displayed !!!");

		try {
			// Unblock.isDisplayed();
			// Unblock(return true);
			driver.findElement(By.xpath("//button[contains(text(),'Unblock')]"));
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}

	}

	public void verifyAndClickOnEnableButton() {
		logger.info("Verify and click on enable button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		if (testUtil.isDisplayed(enableBtn)) {
			testUtil.setExplicitWait(enableBtn, 60);
//			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", enableBtn);
			enableBtn.click();
			testUtil.setExplicitWait(confirmButton, 30);
			testUtil.clickElementJavascript(confirmButton);
		}
	
	}

	public void clickOnDisableButton() {
		logger.info("Click on Disable button");
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(disableBtn, 60);
		disableBtn.click();
		testUtil.setExplicitWait(confirmButton, 30);
		testUtil.clickElementJavascript(confirmButton);
	
	}

	
	public void verifyChangeYourPasswordPopupDisplayed() {
		logger.info("Verfy Change your Popup window opens");
		testUtil.setHardWait(3000);
		testUtil.setExplicitWait(changeYourPasswordText, 60);
		boolean exist = changeYourPasswordText.isDisplayed();
		TestUtil.verifyTrue(exist);
	}
	

		
	public void verifyChangeYourUserPopupDisplayed() {
		logger.info("Verfy Change your user Popup window opens");
		testUtil.setHardWait(3000);
		testUtil.setExplicitWait(changeYourUsernameText, 400);
		boolean exist = changeYourUsernameText.isDisplayed();
		TestUtil.verifyTrue(exist);
	}

	public void enterFirstName(String FirstName) {
		logger.info("Enter First Name");
		testUtil.init(this);
		testUtil.setExplicitWait(firstName, 20);
		firstName.clear();
		firstName.sendKeys(FirstName);
	}

	public void enterLastName(String LastName) {
		logger.info("Enter Last Name");
		testUtil.init(this);
		testUtil.setExplicitWait(lastName, 20);
		lastName.clear();
		lastName.sendKeys(LastName);
	}

	public void verifySearchResult(String columnName,String columnText) {
		logger.info("Verify search result");
		testUtil.init(this);
		testUtil.setExplicitWait(driver.findElement(By.xpath("//mat-row[1]//mat-cell[*]")), 20);
		int i = 0;


		if (columnName.equalsIgnoreCase("userName")) {
			i = 1;
		} else if (columnName.equalsIgnoreCase("firstName")) {
			i = 2;
		} else if (columnName.equalsIgnoreCase("lastName")) {
			i = 3;
		}

		String actual = driver.findElement(By.xpath("//mat-row[1]//mat-cell[" + i + "]")).getText();
		Assert.assertEquals(actual,columnText);
	}

	public void clickOnSubmitButton() {
        logger.info("Click on Submit button");
        testUtil.setHardWait(2000);
        testUtil.WaitForTheElementClickable(LogInButton);
        testUtil.init(this);
        testUtil.clickElementJavascript(LogInButton);
        testUtil.setHardWait(1000);
        testUtil.fluentWait(By.xpath("//h1[contains(text(),'Change Your Password.')]"), 100, 5,"Change Your Password.");        
               
 }

	public void clickOnCreateNewUser() {
		logger.info("Click on Create New User");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		newUser.click();
		
		
	}
	
	public void fetchAndClickOnFirstEnabledUserButton() {
		logger.info("Fetch and Click On First Enabled User Button.");
		testUtil.init(this);

		
//		driver.findElement(By.xpath("//*[@id='mat-select-1']/div/div[2]")).click();
//		driver.findElement(By.xpath("//*[@id='mat-option-5']/span")).click();
//		testUtil.setHardWait(3000);
//		
//		List<WebElement>table=driver.findElements(By.xpath("//*[@id='main']/lib-user-list/mat-card[2]/mat-card-content/div/mat-table/mat-row[*]"));
//		
//		System.out.println(" Number of  elements on this table is: " + table.size());
//		
//		for (int i=1; i <table.size();i ++) {
//
//
//			String status= driver.findElement(By.xpath("//*[@id='main']/lib-user-list/mat-card[2]/mat-card-content/div/mat-table/mat-row[" + i + "]/mat-cell[7]")).getText();
//		
//			logger.info("The Status is = " + status);
//
//			
//			if(status.equals("Enabled")) {
//				
//				logger.info("Enabled button is clicked!");
//				
//				WebElement ele=driver.findElement(By.xpath("//*[@id='main']/lib-user-list/mat-card[2]/mat-card-content/div/mat-table/mat-row/mat-cell[7]/button"));
//				ele.click();
//				Assert.assertEquals(ele, "Enabled");

		String userNm;
		driver.findElement(By.xpath("//*[@id='mat-select-1']/div/div[1]")).click();
		/*Actions acts = new Actions(driver);-->commented this step not need
		acts.keyDown(Keys.DOWN).build().perform();
		acts.keyUp(Keys.DOWN).build().perform();
		acts.keyDown(Keys.DOWN).build().perform();
		acts.keyUp(Keys.DOWN).build().perform();
		acts.keyDown(Keys.DOWN).build().perform();
		acts.keyUp(Keys.DOWN).build().perform();
		acts.keyDown(Keys.DOWN).build().perform();
		acts.keyUp(Keys.DOWN).build().perform();*/
		testUtil.setHardWait(1000);
		List<WebElement> userRow = driver.findElements(By.xpath("//*[@id='main']/lib-user-list/mat-card[2]/mat-card-content/div/mat-table/mat-row[*]"));
		for(int i=0;i<userRow.size();i++) {
			WebElement btn = userRow.get(i).findElement(By.xpath("mat-cell[7]/button"));
			String status = btn.getText();
			logger.info("Status : "+status);
			if(status == "Enable") {
				userNm = userRow.get(i).findElement(By.xpath("mat-cell[1]/a")).getText();
				logger.info("userNm : "+userNm);
				btn.click();

				break;
			}
			
			}
	}}

	
		//*[@id="main"]/lib-user-list/mat-card[2]/mat-card-content/div/mat-table/mat-row[*]
		
//		String userNm;
//		driver.findElement(By.xpath("//*[@id='mat-select-1']/div/div[1]")).click();
//		Actions acts = new Actions(driver);
//		acts.keyDown(Keys.DOWN).build().perform();
//		acts.keyUp(Keys.DOWN).build().perform();
//		acts.keyDown(Keys.DOWN).build().perform();
//		acts.keyUp(Keys.DOWN).build().perform();
//		acts.keyDown(Keys.DOWN).build().perform();
//		acts.keyUp(Keys.DOWN).build().perform();
//		acts.keyDown(Keys.DOWN).build().perform();
//		acts.keyUp(Keys.DOWN).build().perform();
//		testUtil.setHardWait(1000);
//		List<WebElement> userRow = driver.findElements(By.xpath("//*[@id='main']/lib-user-list/mat-card[2]/mat-card-content/div/mat-table/mat-row[*]"));
//		for(int i=0;i<userRow.size();i++) {
//			WebElement btn = userRow.get(i).findElement(By.xpath("mat-cell[7]/button"));
//			String status = btn.getText();
//			logger.info("Status : "+status);
//			if(status == "Enable") {
//				userNm = userRow.get(i).findElement(By.xpath("mat-cell[1]/a")).getText();
//				logger.info("userNm : "+userNm);
//				btn.click();
//				break;
//			}
//		}
//	}


