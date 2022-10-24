package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesEditUserAccessPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesEditPasswordPage.class);
	
	// Appliction Access for user
	@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/div[2]/table/tbody/tr[1]/td/span")
	private WebElement weCNUserConfPwd;
	// Authorized Applications
		@FindBy(how = How.XPATH, using = "//*[@id='selectAccess']/option[5]")
		private WebElement weCNUAuthAppAddBook;
	// Edit User Access Submit Button
		@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/div[2]/table/tbody/tr[4]/td/input")
		private WebElement weCNUEditUSubmitButton;
	
	
	
	////
	// Get User Name
	public void CNUNUserNameDisplay() {
		testUtil.init(this);
		logger.info("Get User Name");
		weCNUserConfPwd.isDisplayed();
	}	
	// Click On Authorized Applications Address Book
	public void clickOnCNUAuthAppAddBook() {
		testUtil.init(this);
		logger.info("Click On Authorized Applications Address Book");
		weCNUAuthAppAddBook.click();
	}
	// Click On Edit User Access Submit Button
		public void clickOnCNUEditUSubmitButton() {
			testUtil.init(this);
			logger.info("Click On Edit User Access Submit Button");
			weCNUEditUSubmitButton.click();
		}
	
}
