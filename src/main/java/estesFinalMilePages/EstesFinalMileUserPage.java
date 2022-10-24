package estesFinalMilePages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class EstesFinalMileUserPage extends TestBase{
	
	private Logger logger = Logger.getLogger(EstesFinalMileMaintenceApp.class);

	@FindBy(xpath = "//a[text()='User']")
	private WebElement UserMenu;

	@FindBy(xpath = "//td[@class='headerText']")
	private WebElement HeaderText;

	@FindBy(xpath = "//div[@class='pageTitle']/span")
	private WebElement PageTitle;

	@FindBy(xpath = "//div[@id='dialog-confirm']")
	private WebElement DeletePopupBody;

	@FindBy(xpath = "//span[text()='Yes']")
	private WebElement YesBtn;

	@FindBy(xpath = "//span[contains(text(),'No')]")
	private WebElement NoBtn;
	
	@FindBy(id = "userNameId")
	private WebElement EnterUserName;
	
	@FindBy(id = "userTerminalId")
	private WebElement SelectTerminal;
	
	@FindBy(id = "manageUserButtonId")
	private WebElement AddBtn;
	
	@FindBy(xpath = "//div[@class='successmessage']")
	private WebElement SuccessMessage;
	
	@FindBy(xpath = "//span[contains(text(),'User Name Warning')]")
	private WebElement UserNameWrng;
	
	/******************************METHODS****************************/
	
	

	public void clickOnLink(String linkName) {
		logger.info("Click on Menu: " + linkName);
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(driver.findElement(By.xpath("//a[contains(text(),'" + linkName + "')]")));
		driver.findElement(By.xpath("//a[contains(text(),'" + linkName + "')]")).click();
		testUtil.setHardWait(2000);
	}

	public void validateHeader(String expText) {
		logger.info("Validate Header text from Home page : " + expText);
		testUtil.init(this);
		testUtil.isDisplayed(HeaderText);
		Assert.assertTrue(HeaderText.getText().trim().contains(expText));
	}

	public void verifyFields(String[] val) {
		logger.info("Validate Fields are dislayed");
		testUtil.init(this);
		List<WebElement> fields = driver.findElements(By.xpath("//td[@class='captionsbold']"));
		for (int i = 0; i < fields.size(); i++) {
			String value = fields.get(i).getText();
			Assert.assertTrue(value.contains(val[i]));
			logger.info(value + " -Field is displayed");
		}
	}

	public void validatePageTitle(String exptitle) {
		logger.info("Validate Page Title with : " + exptitle);
		testUtil.init(this);
		Assert.assertEquals(PageTitle.getText().trim(), exptitle);

	}

	public void verifyUserPageHasData() {
		logger.info("Verify User data has displayed in User Page");
		testUtil.init(this);
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='jtable-no-data-row']")).isDisplayed());
			logger.info("No Data is displayed in User Page");
		} catch (Exception e) {
			logger.info("User data has displayed in User page");
		}

	}

	public void deleteUser(String chooseRow) {
		logger.info("Delete any User data from User Page");
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath(
					"//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td/input[@name='deleteButton']"));
			Assert.assertTrue(ele.isDisplayed());
			ele.click();
		} catch (Exception e) {
			logger.info("No User Data has displayed");
		}

	}
	
	public void editUser(String chooseRow) {
		logger.info("Edit any User data from User Page");
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath(
					"//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td/input[@name='editButton']"));
			Assert.assertTrue(ele.isDisplayed());
			ele.click();
		} catch (Exception e) {
			logger.info("No User Data has displayed");
		}

	}

	public void verifyDeletePopUpIsDisplayed() {
		logger.info("Verify Delete pop up is displayed");
		testUtil.init(this);
		Assert.assertTrue(DeletePopupBody.isDisplayed());
		logger.info("Delete popup is displayed: " + DeletePopupBody.getText().trim());
	}

	public void clickOnYesBtn() {
		logger.info("Click on YES button from Popup");
		testUtil.init(this);
		YesBtn.click();
	}

	public String getUserIdFromGrid(String chooseRow) {
		logger.info("Get User Id From Table");
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td[1]"));
		Assert.assertTrue(ele.isDisplayed());
		logger.info("User ID- " + ele.getText().trim());
		return ele.getText().trim();
	}

	public String getTerminalFromGrid(String chooseRow) {
		logger.info("Get terminal From Table");
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td[2]"));
		Assert.assertTrue(ele.isDisplayed());
		logger.info("Terminal- " + ele.getText().trim());
		return ele.getText().trim();
	}

	public void verifyUserIDnotDisplayed(String user) {
		logger.info("Verify User is not displayed in grid");
		testUtil.init(this);
		try {
			driver.findElement(By.xpath("//td[text()='" + user + "']")).isDisplayed();
			logger.info("User id displayed");
		} catch (Exception e) {
			logger.info(user + "  -User is not Displayed");
		}

	}

	public void deleteUserIfDisplayed(String user) {
		logger.info("Delete User from grid : " + user);
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + user + "']"));
			if (ele.isDisplayed()) {
				WebElement delete = driver.findElement(
						By.xpath("(//td[text()='" + user + "']/following::td/input[@name='deleteButton'])[1]"));
				Assert.assertTrue(delete.isDisplayed());
				delete.click();
				testUtil.setHardWait(2000);
				YesBtn.click();
				logger.info(user + " -User id is deleted");
			}

		} catch (Exception e) {
			logger.info(user + "  -User is not Displayed");
		}

	}

	public void selectTerminal(String term) {
		logger.info("Select Terminal - " + term);
		testUtil.init(this);
		testUtil.selectUsingVisibleText(SelectTerminal, term);
		testUtil.setHardWait(1000);
	}
	
	public void clickOnAddBtn() {
		logger.info("Click on Add");
		testUtil.init(this);
		AddBtn.click();
		testUtil.setHardWait(2000);
	}
	
	public void validateSuccessMsg(String message) {
		logger.info("Validate Success message: "+message);
		testUtil.init(this);
		testUtil.isDisplayed(SuccessMessage);
		TestUtil.verifyText(message, SuccessMessage.getText().trim());
	}
	
	public void verifyUserIsAdded(String userName) {

		logger.info("Verify user is added:" + userName);
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + userName + "']"));
			if (ele.isDisplayed()) 
				logger.info(userName + " -User id is displayed");
		} catch (Exception e) {
			logger.info(userName + "  -User is not Displayed");
		}	
	}
	
	public void enterUserName(String name) {
		logger.info("Enter user name in User Field");
		testUtil.init(this);
		EnterUserName.clear();
		EnterUserName.sendKeys(name);
	}
	
	public String getUserID() {
		logger.info("Get user name From User Field");
		testUtil.init(this);
		String val = EnterUserName.getAttribute("value").trim();
		logger.info(val+" ");
		return val;
	}
	
	public String getTerminalID() {
		logger.info("Get Terminal ID From Terminal Field");
		testUtil.init(this);
		String val = SelectTerminal.getAttribute("value").trim();
		logger.info(val+" ");
		return val;
	}
	
	public void clickOnUpdateBtn() {
		logger.info("Click on Update button");
		testUtil.init(this);
		AddBtn.click();
		testUtil.setHardWait(2000);
	}
	
	public void validateUserNameWarningDisplayed() {
		logger.info("Validate username warning is displayed when user updates");
		testUtil.init(this);
		Assert.assertTrue(UserNameWrng.isDisplayed());
	}
	
	public void verifyTerminalUpdated(String uName,String expTerminal) {
		logger.info("Verify Updated terminal is reflected in Grid");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+uName+"')]/following-sibling::td[1]"));
		Assert.assertEquals(ele.getText().trim(), expTerminal);
		logger.info(ele.getText().trim()+"  -Updated terminal is reflected in Grid");
	}
}


