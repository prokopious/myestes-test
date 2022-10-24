package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.TestBase;
import util.TestUtil;

public class ENetFloorMinsDefinitionPage extends TestBase {
	
	private Logger logger = Logger.getLogger(ENetFloorMinsDefinitionPage.class);

	@FindBy(css="[class*='section-header']")
	private WebElement pageHeader;
	
	@FindBy(id="accountNumber")
	private WebElement accountNumber;
	
	@FindBy(id="minimumAmount")
	private WebElement minimumAmount;
	
	@FindBy(css="[value='Add']")
	private WebElement addButton;
	
	@FindBy(id="mainpage")
	private WebElement frameElement;
	
	@FindBy(css="[class*=output-table] tr")
	private List<WebElement> floorMinTableRows;
	
	@FindBy(css="[class='success']")
	private WebElement successMessage;
	 
	public void switchToFrameElement() {
		logger.info("Switch to VTL Floor Mins frame Element");
		testUtil.init(this);
		driver.switchTo().frame(frameElement);
	}
	
	public void verifyPageHeader() {
		logger.info("Verify Floor Mins Page");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String pageTitle = pageHeader.getText().trim();
		TestUtil.verifyText(pageTitle, "Floor Minimum Definitions");
	}
	
	public void enterAccountNumber(String accNum) {
		logger.info("EnterAccount Number");
		testUtil.init(this);
		accountNumber.sendKeys(accNum);
	}
	
	public void enterMinimumAmount(String miniAmount) {
		logger.info("Enter Minimum Amount");
		testUtil.init(this);
		minimumAmount.sendKeys(miniAmount);
	}
	
	public void clickOnAddButton() {
		logger.info("Click on Add button");
		testUtil.init(this);
		addButton.click();
	}
	
	public void deleteAccountNumberIfExist(String accNum) {
		logger.info("Delete Account Number if exist in the table: "+accNum);
		testUtil.init(this);
		boolean flagval = false;
		
		for(int i=1;i<floorMinTableRows.size();i++) {
			String accountNumber = testUtil.getWebElement(floorMinTableRows.get(i), "td:nth-child(1)").getText().trim();
			if(accountNumber.equals(accNum)) {
				flagval = true;
				logger.info("Try to Delete Account number: "+accNum);
				testUtil.getWebElement(floorMinTableRows.get(i), "td:nth-child(4) a").click();
				testUtil.setHardWait(2000);
				testUtil.switchToAlertAndAccept();
				testUtil.setHardWait(2000);
				break;
			}
		} if(!flagval) {
			logger.info("Account Number already deleted from the table: "+accNum);
		}
	}
	
	public void verifySuccessMessage() {
		logger.info("Verify successfully added message");
		testUtil.init(this);
		testUtil.setHardWait(1000);
	//	testUtil.setExplicitWait(successMessage, 20);
		boolean successMsg = successMessage.isDisplayed();
		TestUtil.verifyTrue(successMsg);
	}

}
