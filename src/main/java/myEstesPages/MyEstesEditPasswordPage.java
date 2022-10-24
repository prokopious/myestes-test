package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesEditPasswordPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesEditPasswordPage.class);
	
	// Username
	@FindBy(how = How.ID, using = "username")
	private WebElement weEditUserName;
	// Password
	@FindBy(how = How.ID, using = "password")
	private WebElement weEditUserPassword;
	// Submit Button
	@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/form/table/tbody/tr[4]/td/input")
	private WebElement weSubmitButton;
	
	/*****************************************************/
	
	////
	//Enter username
	public void enterEditUserName(String UserName) {
		testUtil.init(this);
		logger.info("Enter User Name");
		weEditUserName.sendKeys(UserName);
	}
	//Enter Password
	public void enterEditUserPassword(String UserPassword) {
		testUtil.init(this);
		logger.info("Enter User Password");
		weEditUserPassword.sendKeys(UserPassword);
	}
	// Click On Submit Button
	public void clickOnSubmitButton() {
		testUtil.init(this);
		logger.info("Click On Submit Button");
		weSubmitButton.click();
	}
	
	
}
