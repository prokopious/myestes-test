package rateQuotePages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesHomePage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesHomePage.class);

	/*********** ELEMENTS ***********/

	@FindBy(how = How.ID, using = "user_name")
	private WebElement UserNameField;

	@FindBy(how = How.ID, using = "password")
	private WebElement PasswordField;

	@FindBy(how = How.ID, using = "Login_Button")
	private WebElement LoginButton;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'LTL Rate Quote')]")
	private WebElement LTLRAteQoute;

	@FindBy(how = How.XPATH, using = "//*[@id='rates']/a")
	private WebElement RatesLink;

	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement MyEstesDropDown;

	@FindBy(how = How.XPATH, using = "//div[@class='dropdown-menu dropdown-menu-right ng-tns-c0-0 ng-star-inserted']//a[1]")
	private WebElement LoginLink;

										/*********** METHODS ***********/

	public void enterUserNameAndPassword(String Uname, String pass) {
		logger.info("Enter user name and password");
		testUtil.init(this);
		UserNameField.sendKeys(username1);
		PasswordField.sendKeys(password1);

	}

	/*
	 * public void clickOnLogin() {
	 * 
	 * testUtil.init(this); logger.info("click on Login Button");
	 * LoginButton.click();
	 * 
	 * }
	 */

	public void clickOnLoginButton() {
		logger.info("Click on login button");
		testUtil.init(this);
		LoginButton.click();
	}

	public void clickOnLTLRateQuoteLink() {
		logger.info("Click on LTL Rate Quote link");
		testUtil.init(this);
		LTLRAteQoute.click();
	}

	public void selectLogIn() {
		logger.info("Select login");
		testUtil.init(this);
		// MyEstesDropDown.click();
		testUtil.selectUsingVisibleText(MyEstesDropDown, " Login");
		LoginLink.click();
	}
	
	//UPDATED ON 11/26/2019 @3:25

}
