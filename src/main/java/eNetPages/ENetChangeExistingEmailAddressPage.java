package eNetPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class ENetChangeExistingEmailAddressPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetChangeExistingEmailAddressPage.class);

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "newemail")
	private WebElement changeEmail;

	@FindBy(xpath = "//input[@name='Submit']")
	private WebElement submitBtn;

	@FindBy(xpath = "//html/body/p")
	private WebElement message;

	@FindBy(xpath = "//a[contains(text(),'Back to Menu')]")
	private WebElement backToMenuLink;

	public void selectEmailAddress(String value) {
		logger.info("Select email address");
		testUtil.init(this);
		testUtil.selectUsingValue(email, value);
	}

	public void enterChangeToEmailAddress(String value) {
		logger.info("Enter Change to email address");
		testUtil.init(this);
		changeEmail.sendKeys(value);
	}

	public void clickOnSubmitBtn() {
		logger.info("Click on submit button");
		testUtil.init(this);
		submitBtn.click();
	}

	public void verifyEmailAddressChangeMessage() {
		logger.info("Verify email address change message");
		testUtil.init(this);
		String rmessage = message.getText();
		Assert.assertTrue(rmessage.contains("changed to"));
	}

	public void clickOnBackToMenuLink() {
		logger.info("Click on Back to Menu Link");
		testUtil.init(this);
		backToMenuLink.click();
	}
}
