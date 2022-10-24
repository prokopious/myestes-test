
package eNetPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class ENetUndiscountedFreightReportPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetUndiscountedFreightReportPage.class);
	
	/***************************** WEB ELEMENTS *********************/
	
	@FindBy(xpath = "//*[@id='accountNumber']")
	private WebElement acctNumber;
	
	@FindBy(xpath = "//*[@id='emailAddress']")
	private WebElement emailAddress;
	
	@FindBy(xpath = "//*[@id='submitReport']")
	private WebElement submitButton;
	
	/****************************** METHODS **************************/

	
	public void enterAccountNumber(String acctNo) {
		logger.info("Enter Account Number: " +acctNo);
		testUtil.init(this);
		testUtil.setExplicitWait(acctNumber, 20);
		acctNumber.clear();
		acctNumber.sendKeys(acctNo);
	}
	
	
	public void clickonSubmitButton() {
		testUtil.init(this);
		logger.info("Click on Submit Button");
		submitButton.click();
		testUtil.setHardWait(3000);
		
	}

	public void enterEmailAddress(String emailAddr) {
		logger.info("Enter Email Address: " +emailAddr);
		testUtil.init(this);
		testUtil.setExplicitWait(emailAddress, 20);
		emailAddress.clear();
		emailAddress.sendKeys(emailAddr);
	}
}
