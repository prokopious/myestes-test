package rateQuoteRestrictionMaintenancePages;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class RateQuoteRestrictionMaintenanceLoginPage extends TestBase{

	
private Logger logger=Logger.getLogger(RateQuoteRestrictionMaintenanceLoginPage.class);

	
	@FindBy(how = How.XPATH, using = "//*[@id='username']")

	private WebElement UserID;

	
	@FindBy(how = How.XPATH, using = "//*[@id='password']")

	private WebElement password;


	@FindBy(how = How.XPATH, using = "//*[@id='loginButton']")

	private WebElement LoginButton;
	
	@FindBy(how=How.XPATH, using = "//span[contains(text(), 'Rate Quote Restriction Maintenance')]")
	private WebElement webPageTitle; 

	
	/***************************************************METHODS***************************************************/
	
	public void validateRestrictionPage() {
		logger.info("Validating that webpage is displayed before proceeding to signing in.");
		testUtil.init(this); 
		TestUtil.waitForPageToLoad();
		if(!webPageTitle.isDisplayed()) {
			throw new NoSuchElementException("Restriction Page is not visible when logging in"); 
		}
	}
		

	
	public void enterUserID(String userId) {
		logger.info("Enter user name");
		testUtil.init(this);
		testUtil.setExplicitWait(UserID, 60);
		testUtil.clickElementJavascript(UserID);
		UserID.sendKeys(userId);

		}


	
	public void enterUserPassword(String pwd) {

		logger.info("Enter password");
		testUtil.init(this);
		testUtil.setExplicitWait(password, 60);
		testUtil.clickElementJavascript(password);
		password.sendKeys(pwd);
	}

	
	public void clickOnLoginButton() {

		logger.info("Click on Login button");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, LoginButton, 10, 250, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(LoginButton);

	}

	
	public void validateLoginPage() {
		
		testUtil.init(this);
		logger.info("Varify login page");
		
		WebElement pageTitle= driver.findElement(By.xpath("//*[@id='restrictionForm']/div[1]"));
		String title=pageTitle.getText();
		System.out.println("Page title is: " + title);
		Assert.assertEquals(title, "RESTRICTION SETUP");
		
		
	}
	
}


