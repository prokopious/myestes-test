package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.TestBase;

public class MyEstesNewUserPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesNewUserPage.class);
	


	// @FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-2\"]")

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-3\"]")
	private WebElement firstNameField;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-4\"] [contains(text(),formcontrolname)]")
	private WebElement lastNameField;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-5\"] [contains(text(),email)]")
	private WebElement emailAddField;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-6\"] [contains(text(),phone)]")
	private WebElement phoneNumberField;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-7\"][contains(text(),username)]")
	private WebElement userNameField;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-8\"] [contains(text(),password)]")
	private WebElement passwordField;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-9\"] [contains(text(),confirmPassword)]")
	private WebElement confirmPwdField;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")

	private WebElement submitBtn;

	@FindBy(how = How.CSS, using = ".dropdown-menu-right > .dropdown-item:nth-child(7)")
	private WebElement logoutBtn;

	@FindBy(how = How.CSS, using = ".m-0 > .btn-primary")
	private WebElement confirmLogoutBtn;

	@FindBy(how = How.CSS, using = ".d-flex:nth-child(1) > .btn")
	private WebElement closeBtn;

	private String userName;

	/************************************************/

	public String enterFirstName(String firstName) {

		logger.info("Enter first name");
		testUtil.init(this);
		firstNameField.click();
		firstNameField.clear();
		testUtil.setExplicitWait(firstNameField, 60);
		firstNameField.sendKeys(firstName);
		return firstName;

	}

	public String enterLastName(String lastName) {

		logger.info("Enter last name");
		testUtil.init(this);
		lastNameField.click();
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		return lastName;
	}

	public String enterEmailAddress(String email) {

		logger.info("Enter email address");
		testUtil.init(this);
		emailAddField.click();
		emailAddField.clear();
		emailAddField.sendKeys(email);
		return email;

	}

	public String enterPhoneNumber(String phoneNum) {

		logger.info("Enter phone number");
		testUtil.init(this);
		phoneNumberField.click();
		phoneNumberField.clear();
		phoneNumberField.sendKeys(phoneNum);
		System.out.println("The phone number is:" + phoneNum);
		return phoneNum;

	}
	
		public String enterUserName() {

			logger.info("Enter user name");
			testUtil.init(this);
			userNameField.click();
			Faker faker = new Faker();
			userName=faker.name().username();
			checkFakerUserName();
			userNameField.sendKeys(userName);
			return userName;
		}

		private void checkFakerUserName() {
			if(userName.chars().count()>10) {
				userName=userName.substring(0, 9);
				if(userName.contains(".")) {
					userName=userName.replace(".", "");
				}
			}
		}
		
		public String getUserName() {
			return userName;
		}

		
		
		
		
		
		
		
		
		
		
		
	
	public void enterPassword(String password) {

		logger.info("Enter password");
		testUtil.init(this);
		passwordField.click();
		passwordField.sendKeys(password);

	}

	public void confirmPassword(String confirmPass) {

		logger.info("Confirm password");
		testUtil.init(this);
		confirmPwdField.click();

		confirmPwdField.clear();
		testUtil.setExplicitWait(confirmPwdField, 60);
		confirmPwdField.sendKeys(confirmPass);

	}

	public void clickOSubmitButton() {

		logger.info("Click on submit button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		submitBtn.click();
		testUtil.setHardWait(1000);

	}
	
	public void clickOnCloseButton() {
		logger.info("Click on close button");
		testUtil.init(this);
		testUtil.setExplicitWait(closeBtn, 60);
		closeBtn.click();
	}
	
	public void logout() {
		logger.info("Logging out");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(logoutBtn);
		testUtil.setExplicitWait(confirmLogoutBtn, 10);
		testUtil.clickElementJavascript(confirmLogoutBtn);
	}

	
	
	public void verifyNewCreatedUser(String expected) {
		logger.info("Verify new create user name");
		testUtil.init(this);
		WebElement element= driver.findElement(By.xpath("//*[@id='mat-dialog-0']/lib-user-access-modal/mat-card/mat-card-header/div/mat-card-title"));
		testUtil.setHardWait(2000);
		System.out.println(element.getText());
		Assert.assertEquals(element.getText(), expected);

	}


}
