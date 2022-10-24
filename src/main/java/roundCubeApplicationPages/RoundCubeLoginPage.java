package roundCubeApplicationPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class RoundCubeLoginPage extends TestBase {

	private Logger logger = Logger.getLogger(RoundCubeLoginPage.class);

	/**************************** ELEMENTS ****************************/
	
	@FindBy(id = "rcmloginuser")
	private WebElement userName;
	
	@FindBy(id = "rcmloginpwd")
	private WebElement password;
	
	@FindBy(id = "rcmloginsubmit")
	private WebElement loginButton;
	
	/*************************** METHODS *****************************/
	
	public void enterUserName() {
		logger.info("Enter username");
		testUtil.init(this);
		userName.sendKeys(username17);
	}

	public void enterPassword() {
		logger.info("Enter password");
		testUtil.init(this);
		password.sendKeys(password17);
	}

	public void clickonLogintButton() {
		logger.info("Click on login button");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, loginButton, 10, 250, TimeUnit.MILLISECONDS);
		loginButton.click();
	}
	
}
