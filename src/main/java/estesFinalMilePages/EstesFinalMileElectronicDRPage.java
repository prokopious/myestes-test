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

public class EstesFinalMileElectronicDRPage extends TestBase {

	private Logger logger = Logger.getLogger(EstesFinalMileMaintenceApp.class);

	@FindBy(id = "accessorialCode")
	private WebElement SelectAccessorial;
	
	@FindBy(xpath = "//div[@id='dialog-confirm']")
	private WebElement WarningPopupBody;
	
	@FindBy(xpath = "//span[text()='Yes']")
	private WebElement YesBtn;
	
	@FindBy(id = "manageOutboundAccessorialButtonId")
	private WebElement AddBtn;
	
	@FindBy(id = "manageShippingInstructionsButtonId")
	private WebElement AddBtn2;
	
	@FindBy(id = "shippingCode")
	private WebElement SelectShippingInst;
	
	///=================================================//
	
	public String selectAccessorialRandomly() {
		logger.info("Select Accessorial randomly ");
		testUtil.init(this);
		Select select = new Select(SelectAccessorial);
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='accessorialCode']/option"));
		Random rand = new Random();
		int list = rand.nextInt(options.size());
		options.get(list).click();
		String val=select.getFirstSelectedOption().getText().trim();
		logger.info("Selected accessorial Code: "+val);
		return val;
	}
	
	public void selectAccessorial(String text) {
		logger.info("Select Accessorial by text: "+text);
		testUtil.init(this);
		testUtil.selectUsingVisibleText(SelectAccessorial, text);
	}
	
	public void deleteAccessorial(String accessorial) {
		logger.info("Delete Accessorial From Table ");
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + accessorial + "']"));
			if (ele.isDisplayed()) {
				driver.findElement(By.xpath("//td[text()='" + accessorial + "']/following::input[1]")).click();
				testUtil.setHardWait(1000);
				testUtil.isDisplayed(WarningPopupBody);
				YesBtn.click();
			}

		} catch (Exception e) {
			logger.info(accessorial + " Accessorial is not displayed in grid");
		}

	}
	
	public void clickOnAddBtn() {
		logger.info("Click on Add button");
		testUtil.init(this);
		AddBtn.click();
		testUtil.setHardWait(2000);
	}
	
	public void clickOnAddBtn2() {
		logger.info("Click on Add button");
		testUtil.init(this);
		AddBtn2.click();
		testUtil.setHardWait(2000);
	}
	
	public void verifyAccessorialIsAdded(String Accessorial) {

		logger.info("Verify user is added:" + Accessorial);
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + Accessorial + "']"));
			if (ele.isDisplayed()) 
				logger.info(Accessorial + " -Accessorial is displayed");
		} catch (Exception e) {
			logger.info(Accessorial + "  -Accessorial is not Displayed");
		}	
	}
	
	public void verifyAccessorialIsDisplayedinExla(String expValue, List<String> actValue) {
		logger.info("Verify Published Accessorial is displayed in AS400 table");
		testUtil.init(this);
		for (String val : actValue) {
			if (val.trim().equals(expValue.trim())) {
				logger.info("Accessorial is displayed in as400: " + val.trim());
				break;	
			}
		}

	}
	
	public String getAccessorialFromGrid(String chooseRow) {
		logger.info("Get Accessorial From Table row: "+chooseRow);
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//tr[contains(@class,'jtable-data-row')][" + chooseRow + "]/td[1]"));
		Assert.assertTrue(ele.isDisplayed());
		logger.info("Accessorial- " + ele.getText().trim());
		return ele.getText().trim();
	}
	
	public void verifyAccessorialnotDisplayed(String accessorial) {
		logger.info("Verify Accessorial is not displayed in grid");
		testUtil.init(this);
		try {
			driver.findElement(By.xpath("//td[text()='" + accessorial + "']")).isDisplayed();
			logger.info("Accessorial is displayed in table");
		} catch (Exception e) {
			logger.info("Accessorial: "+accessorial+" is not Displayed in table");
		}

	}
	
	public void selectShipppingInstruction(String text) {
		logger.info("Select Shippping Instruction by text: "+text);
		testUtil.init(this);
		testUtil.selectUsingVisibleText(SelectShippingInst, text);
	}
	
	public void verifyShippingInstructionIsAdded(String shippingInstruction) {

		logger.info("Verify Shipping Instruction is added:" + shippingInstruction);
		testUtil.init(this);
		try {
			WebElement ele = driver.findElement(By.xpath("//td[text()='" + shippingInstruction + "']"));
			if (ele.isDisplayed()) 
				logger.info(shippingInstruction + " -Shipping Instruction is displayed");
		} catch (Exception e) {
			logger.info(shippingInstruction + "  -Shipping Instruction is not Displayed");
		}	
	}
	
}
