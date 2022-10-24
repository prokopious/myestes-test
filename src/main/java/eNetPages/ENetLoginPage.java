package eNetPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;



public class ENetLoginPage extends TestBase{
	
	private Logger logger=Logger.getLogger(ENetLoginPage.class);
	
	@FindBy(how = How.ID, using = "j_username")
	private WebElement UserID;
	
	@FindBy(how = How.XPATH, using = "//input[@name='j_password']")
	private WebElement Password;

	@FindBy(how = How.XPATH, using = "//input[@value='Login']")
	private WebElement LoginButton;
	
	public void enterUserID(String userId) {
		logger.info("Enter user name");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		UserID.sendKeys(userId);
	}
	
	public void enterUserPassword(String password) {
		logger.info("Enter user password");
		testUtil.init(this);
		testUtil.setHardWait(500);
		Password.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		logger.info("Click on Login button");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, LoginButton, 20, 250, TimeUnit.MILLISECONDS);
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
	//	js.executeScript("arguments[0].setAttribute('style','border:2px solid red;background:yellow')",LoginButton);
		testUtil.highlightElementBeforeClickAction(LoginButton);
		testUtil.clickElementJavascript(LoginButton);
	
	}
	public void login(String userName, String password) {
		testUtil.init(this);
		logger.info("Enter username, password and login");
		enterUserID(userName);
		enterUserPassword(password);
		clickOnLoginButton();
	}
	
}
