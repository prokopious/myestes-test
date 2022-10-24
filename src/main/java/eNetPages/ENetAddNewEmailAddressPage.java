package eNetPages;

	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.testng.Assert;
	import org.testng.log4testng.Logger;

import testBase.TestBase;

	public class ENetAddNewEmailAddressPage extends TestBase {

		private Logger logger = Logger.getLogger(ENetAddNewEmailAddressPage.class);
		
		@FindBy(id = "email")
		private WebElement email;
		
		@FindBy(xpath="//input[@name='Submit']")
		private WebElement submitBtn;
		
		@FindBy(xpath = "//a[contains(text(),'Back to Email Address Maintanace')]")
		private WebElement backToEmailAddressMaintance;
		
		@FindBy(xpath = "//html/body/p")
		private WebElement message;
		
		@FindBy(xpath = "//a[contains(text(),'Back to Menu')]")
		private WebElement backToMenuLink;
		
		public void enterEmailAddress(String emailAddr) {
			logger.info("Enter Email address");
			testUtil.init(this);
			email.sendKeys(emailAddr);
		}
		
		public void clickOnSubmitBtn() {
			logger.info("Click on submit button");
			testUtil.init(this);
			submitBtn.click();
		}
		
		public void clickOnBackToEmailAddressMaintance() {
			logger.info("Click on Back to email address maintance");
			testUtil.init(this);
			backToEmailAddressMaintance.click();
		}
		
		public void verifyEmailAddedToRestrictedMessage() {
			logger.info("Verify email added to restricted message");
			testUtil.init(this);
			String rmessage = message.getText();
			Assert.assertTrue(rmessage.contains("added to restricted list"));
		}
		
		public void clickOnBackToMenuLink() {
			logger.info("Click on Back to Menu Link");
			testUtil.init(this);
			backToMenuLink.click();
		}
	}


