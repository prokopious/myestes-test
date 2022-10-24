package eNetPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class ENetRestrictedPage extends TestBase {
	private Logger logger = Logger.getLogger(ENetRestrictedPage.class);

	@FindBy(xpath = "//a[contains(text(),'view')]")
	private WebElement viewLink;

	@FindBy(xpath = "//a[contains(text(),'Add New Email Address')]")
	private WebElement addNewEmailAddress;

	@FindBy(xpath = "//a[contains(text(),'Change Existing Email Address')]")
	private WebElement changeExistingEmailAddress;

	@FindBy(xpath = "//a[contains(text(),'Delete Existing Email Address')]")
	private WebElement deleteExistingEmailAddress;

	@FindBy(id = "mainpage")
	private WebElement frameElement;

	/********************METHODS*********************/
	
	public void switchToFrameElement() {
		logger.info("Switch to VTL Floor Mins frame Element");
		testUtil.init(this);
		driver.switchTo().frame(frameElement);
	}

	public void clickOnViewLink() {
		logger.info("Click on View Link");
		testUtil.init(this);
		viewLink.click();
	}

	public void clickOnAddNewEmailAddress() {
		logger.info("Click on add new email address");
		testUtil.init(this);
		addNewEmailAddress.click();
	}

	public void clickOnChangeExistingEmailAddress() {
		logger.info("Click on Change existing email address");
		testUtil.init(this);
		changeExistingEmailAddress.click();
	}

	public void clickOnDeleteExistingEmailAddress() {
		logger.info("Click on Delete existing email address");
		testUtil.init(this);
		deleteExistingEmailAddress.click();
	}
}
