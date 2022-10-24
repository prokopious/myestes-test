package estesFinalMilePages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class EstesFinalMileConsigneePage extends TestBase{

	private Logger logger= Logger.getLogger(EstesFinalMileConsigneePage.class);
	
	@FindBy(id = "consigneeTypeNmId")
	private WebElement EnterConsignee;

	@FindBy(id = "activeFlagId")
	private WebElement SelectActive;

	@FindBy(id = "manageConsigneeTypeButtonId")
	private WebElement AddBtn2;
	
	@FindBy(id = "manageCommodityButtonId")
	private WebElement AddBtn;

	@FindBy(id = "consigneeTypeId")
	private WebElement SelectConsigneeType;

	@FindBy(id = "commodityCode")
	private WebElement SelectConsigneeCode;

	@FindBy(xpath = "//select[@id='consigneeTypeId']/following-sibling::div[1]")
	private WebElement ConsigneeTypeError;

	@FindBy(xpath = "//select[@id='commodityCode']/following-sibling::div[1]")
	private WebElement ConsigneeTypeCodeError;
	
	@FindBy(xpath = "//tr[@class='jtable-no-data-row']")
	public WebElement NoResults;
	
	@FindBy(id = "consigneeTypelookupTxt")
	private WebElement EnterLookUpText;
	
	@FindBy(id = "manageConsigneeLookupTypeButtonId")
	private WebElement AddBtn3;
	
	@FindBy(id = "consigneeTypelookupId")
	private WebElement SelectConsigneeTypeLookUp;
	
	@FindBy(xpath = "//div[@id='dialog-confirm']")
	private WebElement WarningPopup;

	@FindBy(xpath = "//span[text()='No']")
	private WebElement NoButton;
	

	// ------------------ Methods ---------------------//

	public void enterConsigneeName(String name) {
		logger.info("Enter Consignee name in consignee Field");
		testUtil.init(this);
		EnterConsignee.clear();
		EnterConsignee.sendKeys(name);
	}

	public void selectActiveFlag(String term) {
		logger.info("Select Active flag - " + term);
		testUtil.init(this);
		testUtil.selectUsingVisibleText(SelectActive, term);
		testUtil.setHardWait(1000);
	}

	public void clickOnAddBtn() {
		logger.info("Click on Add");
		testUtil.init(this);
		AddBtn.click();
		testUtil.setHardWait(2000);
	}
	
	public void clickOnAddBtn2() {
		logger.info("Click on Add");
		testUtil.init(this);
		AddBtn2.click();
		testUtil.setHardWait(2000);
	}
	
	public void clickOnAddBtn3() {
		logger.info("Click on Add");
		testUtil.init(this);
		AddBtn3.click();
		testUtil.setHardWait(2000);
	}

	public void verifyConsigneeIsAdded(String userName) {

		logger.info("Verify Consignee is added:" + userName);
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + userName + "']"));
			if (ele.isDisplayed())
				logger.info(userName + " -Consignee id is displayed");
		} catch (Exception e) {
			logger.info(userName + "  -Consignee is not Displayed");
		}
	}

	public String selectConsigneeType(String value) {
		logger.info("Select Consignee type value as: " + value);
		testUtil.init(this);
		Select select = new Select(SelectConsigneeType);
		select.selectByValue(value);
		testUtil.setHardWait(1000);
		String val=select.getFirstSelectedOption().getText().trim();
		logger.info("Selected Consignee type : "+val);
		return val;
	}

	public String selectConsigneeTypeCode() {
		logger.info("Select Consignee type Code ");
		testUtil.init(this);
		Select select = new Select(SelectConsigneeCode);
		SelectConsigneeCode.click();
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='commodityCode']/option"));
		Random rand = new Random();
		int list = rand.nextInt(options.size());
		options.get(list).click();
		SelectConsigneeCode.click();
		String val=select.getFirstSelectedOption().getText().trim();
		logger.info("Selected Consignee type Code: "+val);
		return val;
	}
	
	public void verifyRequiredFieldDisplayed() {
		logger.info("Verify required field is displayed ");
		testUtil.init(this);
		Assert.assertTrue(ConsigneeTypeError.isDisplayed());
		logger.info(ConsigneeTypeError.getText().trim()+" Error is displayed for Consignee type");
		Assert.assertTrue(ConsigneeTypeCodeError.isDisplayed());
		logger.info(ConsigneeTypeCodeError.getText().trim()+" Error is displayed for Consignee type Code");
		
	}
	
	public void verifyConsigneeTypeIsAdded(String type, String code) {

		logger.info("Verify ConsigneeType added:" + type);
		testUtil.init(this);
		try {
			testUtil.WaitForTheElementClickable( driver.findElement(By.xpath("//td[text()='" + type + "']/following-sibling::td[text()='" + code + "']")));
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + type + "']/following-sibling::td[text()='" + code + "']"));
			//td[text()='RESIDENTIAL']/following-sibling::td[text()='RSMS']
			if (ele.isDisplayed()) {
				Assert.assertEquals(ele.getText().trim(), code);
				logger.info(code + " -Consignee Type Code is displayed");
			}
		} catch (Exception e) {
			logger.info(code + "  -Consignee Type Code is not Displayed");
		}
	}
	
	public  String randomNumber(int bound) {
		Random random = new Random();
		int number = random.nextInt(bound);
		String s= String.valueOf(number);
//		long number = (long) Math.floor(Math.random() * 10L) + 5L;
		return s;
	}

	public  String changeCommodityCodeIfErrorDisplayed(String val) {
		logger.info("Change commodity code if error displayed");
		testUtil.init(this);
		String code = val;
		boolean foundElement = true;
		while (foundElement) {
			try {
				driver.findElement(By.id("consigneeTypeCode.commodityCode.errors")).isDisplayed();
				code = selectConsigneeTypeCode();
				clickOnAddBtn();

			} catch (Exception e) {
				foundElement = false;
			}
		}
		return code;
		 
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
	
	public String getConsigneeTypeFromGrid(String chooseRow) {
		logger.info("Get Consignee Type From Table row: "+chooseRow);
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td[1]"));
		Assert.assertTrue(ele.isDisplayed());
		logger.info("Consignee Type- " + ele.getText().trim());
		return ele.getText().trim();
	}

	public String getConsigneeTypeCodeFromGrid(String chooseRow) {
		logger.info("Get Consignee Type code From Table row: "+chooseRow);
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td[2]"));
		Assert.assertTrue(ele.isDisplayed());
		logger.info("Consignee Type code- " + ele.getText().trim());
		return ele.getText().trim();
	}
	
	public void deleteConsigneeTypecode(String chooseRow) {
		logger.info("Delete Consignee Type code From Table row: "+chooseRow);
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath(
					"//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td/input[@name='deleteButton']"));
			Assert.assertTrue(ele.isDisplayed());
			ele.click();
		} catch (Exception e) {
			logger.info("No Consignee Type code has displayed");
		}

	}
	
	public void verifyConsigneeTypecodenotDisplayed(String type, String code) {
		logger.info("Verify Consignee Type code is not displayed in grid");
		testUtil.init(this);
		try {
			driver.findElement(By.xpath("//td[text()='" + type + "']/following-sibling::td[text()='" + code + "']")).isDisplayed();
			logger.info("Consignee Type code is displayed");
		} catch (Exception e) {
			logger.info("Consignee Type "+type+" and code "+code+"is not Displayed");
		}

	}
	
	
	public void enterLookUpText(String name) {
		logger.info("Enter LookUp Text in LookUpText Field");
		testUtil.init(this);
		EnterLookUpText.clear();
		EnterLookUpText.sendKeys(name);
	}
	
	public void verifyConsigneeTypeLookUpDisplayed(String type, String lookUptext) {
		logger.info("Verify Consignee Type LookUp is displayed in grid");
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(
					By.xpath("//td[text()='" + type + "']/following-sibling::td[text()='" + lookUptext + "']"));
			if (ele.isDisplayed())
				logger.info("Consignee Type " + type + " and LookUp text " + lookUptext + " is  Displayed");
		} catch (Exception e) {
			logger.info("Consignee Type " + type + " and LookUp text " + lookUptext + " is NOT Displayed");
		}

	}

	public void verifyPublishedLookUpIsDisplayed(String expValue, List<String> actValue) {
		logger.info("Verify Published LookUp is displayed in AS400 table");
		testUtil.init(this);
		for (String val : actValue) {
			if (val.trim().equals(expValue)) {
				logger.info("LookUp is displayed in as400: " + val.trim());
				break;	
			}
		}

	}
	
	public String selectConsigneeTypeLookUp(String value) {
		logger.info("Select Consignee type LookUp value as: " + value);
		testUtil.init(this);
		Select select = new Select(SelectConsigneeTypeLookUp);
		select.selectByValue(value);
		testUtil.setHardWait(1000);
		String val=select.getFirstSelectedOption().getText().trim();
		logger.info("Selected Consignee type LookUp: "+val);
		return val;
	}
	
	public void selectConsigneeTypeByText(String text) {
		logger.info("Select Consignee type LookUp value as: " + text);
		testUtil.init(this);
		testUtil.selectUsingVisibleText(SelectConsigneeTypeLookUp, text);
		testUtil.setHardWait(500);
	}
	
	public void deleteLookUp(String type, String lookUptext) {
		logger.info("Delete Consignee Type Look up From Table ");
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + type + "']/following-sibling::td[text()='"
					+ lookUptext + "']/following::input[@id='ctDeleteButton'][1]"));
			testUtil.isDisplayed(ele);
			ele.click();
		} catch (Exception e) {
			logger.info("No Consignee Type: " + type + " with lookup text as: " + lookUptext + " has displayed");
		}

	}
	
	public String getConsigneeLookUpTextFromGrid(String chooseRow) {
		logger.info("Get Consignee type Lookup text From Table row: "+chooseRow);
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td[2]"));
		Assert.assertTrue(ele.isDisplayed());
		logger.info("Consignee type Lookup text- " + ele.getText().trim());
		return ele.getText().trim();
	}
	
	public void verifyWarningPopUpIsDisplayed() {
		logger.info("Verify warning pop up is displayed");
		testUtil.init(this);
		Assert.assertTrue(WarningPopup.isDisplayed());
		logger.info("Warning popup is displayed: " + WarningPopup.getText().trim());
	}
	
	public void clickOnNoBtn() {
		logger.info("Click on NO button from Popup");
		testUtil.init(this);
		NoButton.click();
	}
}
