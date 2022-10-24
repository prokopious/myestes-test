package eNetPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class ENetDeleteExistingEmailAddress extends TestBase {

	private Logger logger = Logger.getLogger(ENetChangeExistingEmailAddressPage.class);

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(xpath = "//input[@name='Submit']")
	private WebElement submitBtn;

	@FindBy(xpath = "//html/body/p")
	private WebElement message;

	public void selectEmailAddress(String value) {
		logger.info("Select email address");
		testUtil.init(this);
		testUtil.selectUsingValue(email, value);
	}

	public void clickOnSubmitBtn() {
		logger.info("Click on submit button");
		testUtil.init(this);
		submitBtn.click();
	}

	public void verifyEmailAddressDeleteMessage() {
		logger.info("Verify email address delete message");
		testUtil.init(this);
		String rmessage = message.getText();
		Assert.assertTrue(rmessage.contains("has been deleted"));
	}

}
