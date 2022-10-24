package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesYourMyEstesUserProfilePage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesEditPasswordPage.class);
	
	// Change my username
	// Change my Password
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Change my password')]")
	private WebElement weChangePassword;
	// Change my e-mail address
	// Change my e-mail update preference
	//Create New User
	@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/table/tbody/tr[6]/td[3]/a")
	private WebElement weCreateNUser;

	// Password Change Text Message
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Password successfully changed')]")
	private WebElement wePWordChangeTMessage;
	// Password Successfully Changed Message
		@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']")
		private WebElement wePWordSuccessfullyCTMessage;
		
	
	/////
	// Click on Change my password
		public void clickOnChangePassword() {
			testUtil.init(this);
			logger.info("Click On Change my password");
			weChangePassword.click();
		}
		// Verify Password Change Text Message
		public String getPWordChangeextMessage() {
			testUtil.init(this);
			logger.info("Verify Password Change Text Message");
			String PWordChangeextMessage = wePWordChangeTMessage.getText();
			return PWordChangeextMessage;
		}
		//  Verify Password Successfully Changed Message
				public String getPWordSuccCTMessage() {
					testUtil.init(this);
					logger.info("Verify Password Change Text Message");
					String PWordSuccessfullyChangeTextMessage = wePWordSuccessfullyCTMessage.getText();
					return PWordSuccessfullyChangeTextMessage;
				}
				// Click on Create New User
				public void clickOnCreateNUserChangePassword() {
					testUtil.init(this);
					logger.info("Click On Create New User");
					weCreateNUser.click();
				}
	
}
