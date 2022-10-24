package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesChangeYourMyEstesPasswordPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesChangeYourMyEstesPasswordPage.class);
	 
									/*********** ELEMENT ***********/
	@FindBy(how = How.ID, using = "password_req")
	private WebElement weChangePassword;

	@FindBy(how = How.ID, using = "pw_confirm")
	private WebElement weVerifyNPassword;

	@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/form/table/tbody/tr[5]/td/input")
	private WebElement weCPSubmitButton;


								/*********** METHODS ***********/

	public void enterNewPassword(String ChangePassword) {
		logger.info("Enter new password");
		testUtil.init(this);
		logger.info("Enter new password");
		weChangePassword.sendKeys(ChangePassword);
	}

	
	public void enterVNPassword(String VerifyNPassword) {
		logger.info("Enter verify new password");
		testUtil.init(this);
		weVerifyNPassword.sendKeys(VerifyNPassword);
	}

	public void clickOnCPasswordSubmitButton() {
		testUtil.init(this);
		logger.info("Click On change password submit button");
		weCPSubmitButton.click();
	}
	//UPDATED ON 11/26/2019@3:20
}
