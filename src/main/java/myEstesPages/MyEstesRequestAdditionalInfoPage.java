package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesRequestAdditionalInfoPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesRequestAdditionalInfoPage.class);
	@FindBy(css="[formcontrolname='name']")
	private WebElement name;
	
	@FindBy(css="[formcontrolname='emailAddress']")
	private WebElement emailAddress;
	
	@FindBy(css="[formcontrolname='phoneNumber']")
	private WebElement phoneNumber;
	
	@FindBy(css="[formcontrolname='problem']")
	private WebElement descriptionOfProblem;
	
	@FindBy(xpath="//span[contains(text(),'Tracking Help')]")
	private WebElement trackingHelpText;
	
	@FindBy(css="[formcontrolname='description']")
	private WebElement comments;
	
	@FindBy(css="[type='submit']")
	private WebElement submitBtn;
	
	@FindBy(css="[class*='alert-success'] span")
	private WebElement alertSuccess;
	
	/********************METHODS********************/
	
	public void enterRequestorName(String rName) {
		logger.info("Enter name: "+rName);
		testUtil.init(this);
		testUtil.setExplicitWait(name, 20);
		name.clear();
		name.sendKeys(rName);
	}
	
	public void enterRequestorEmailAddress(String rEmail) {
		logger.info("Enter email address: "+rEmail);
		testUtil.init(this);
		emailAddress.click();
		testUtil.setHardWait(500);
		emailAddress.clear();
		testUtil.setExplicitWait(emailAddress,10);
		emailAddress.sendKeys(rEmail);
	}
	
	public void enterRequestorPhoneNumber(String rPhone) {
		logger.info("Enter Phone number: "+rPhone);
		testUtil.init(this);
		testUtil.setExplicitWait(phoneNumber, 10);
		phoneNumber.clear();
		phoneNumber.click();
		testUtil.setHardWait(1000);
		phoneNumber.sendKeys(rPhone);
	}
	
	public void selectTrackingHelpInDescriptionOfProblem() {
		logger.info("Select Description of Problem");
		testUtil.init(this);
		testUtil.setExplicitWait(descriptionOfProblem, 60);
		testUtil.clickElementJavascript(descriptionOfProblem);
		//descriptionOfProblem.click();
		testUtil.setExplicitWait(trackingHelpText, 30);
		testUtil.clickElementJavascript(trackingHelpText);
		//trackingHelpText.click();
	}
	
	public void enterRequestorComments(String rComments) {
		logger.info("Enter Requestor comments: "+rComments);
		testUtil.init(this);
		testUtil.setExplicitWait(comments, 20);
		comments.sendKeys(rComments);
	}
	
	public void clickOnSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		submitBtn.click();
	}
	
	public void verifyRequestorInfoSuccessMessage(String successMsg) {
		logger.info("Verifying requestor info success message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertSuccess, 20);
		String successMessage = alertSuccess.getText().trim();
		testUtil.setHardWait(1000);
		Assert.assertEquals(successMessage, successMsg);
	}
	


}
