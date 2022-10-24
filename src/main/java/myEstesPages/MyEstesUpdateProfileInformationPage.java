package myEstesPages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class MyEstesUpdateProfileInformationPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesUpdateProfileInformationPage.class);

	/*********** ELEMENTS ***********/


	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Update Profile Information')]")
	private WebElement updateProfilePage;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Save & Continue')]")
	private WebElement save;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='email']")
	private WebElement email;
	
	@FindBy(css="[formcontrolname='firstName']")
    private WebElement firstName;

    @FindBy(css="[formcontrolname='lastName']")
    private WebElement lastName;
    
	@FindBy(how = How.ID, using = "inputCompany")
	private WebElement cmpnyName;
	
	@FindBy(how = How.ID, using = "inputPhone")
	private WebElement phNum;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Profile Updated Successfully')]")
	private WebElement sucessMsg;
	

    


	/**********************************************************************/

	


	public void verifyUpdateProfilePage() throws InterruptedException {
		logger.info("Validate update profile Page is displayed");
		testUtil.init(this);
		testUtil.setHardWait(4000);
		Assert.assertEquals(updateProfilePage.isDisplayed(), true);
	}

	
	public void clickOnSaveAndContinueButton() {
		logger.info("Click save and continue button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		save.click();

	}

	
	public void enterFirstName(String FirstName) {
		logger.info("Enter First Name");
		testUtil.init(this);
		testUtil.setExplicitWait(firstName, 20);
		firstName.clear();
		firstName.sendKeys(FirstName);
	}

	public void enterLastName(String LastName) {
		logger.info("Enter Last Name");
		testUtil.init(this);
		testUtil.setExplicitWait(lastName, 20);
		lastName.clear();
		lastName.sendKeys(LastName);
	}
	
	public void enterCompanyName(String companyName) {
		logger.info("Enter company name");
		testUtil.init(this);
		testUtil.setExplicitWait(cmpnyName, 20);
		cmpnyName.clear();
		cmpnyName.sendKeys(companyName);
	}
	
	public void enterPhoneNumber(String phNo) {
		logger.info("Enter phone number");
		testUtil.init(this);
		testUtil.setExplicitWait(phNum, 20);
		phNum.clear();
		phNum.sendKeys(phNo);
	}
	
	public void verifySuccessMessage() throws InterruptedException {
		logger.info("Verify sucess message is displayed");
		testUtil.init(this);
		
		
		//sucessMsg.getText();
		
		WebDriverWait wait= new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Profile Updated Successfully')]")));
		
		
		Assert.assertEquals(sucessMsg.isDisplayed(), true);
	
	}

}
