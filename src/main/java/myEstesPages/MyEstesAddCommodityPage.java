package myEstesPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.TestBase;

public class MyEstesAddCommodityPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesAddCommodityPage.class);

	
									/*********** ELEMENTS ***********/
	@FindBy(how = How.ID, using = "id")
	private WebElement ProductID;

	@FindBy(how = How.ID, using = "description")
	private WebElement Descriprion;

	@FindBy(how = How.ID, using = "goodsQuantity")
	private WebElement GoodsQuantity;

	@FindBy(how = How.ID, using = "weight")
	private WebElement Weight;

	@FindBy(how = How.ID, using = "nmfc")
	private WebElement nmfc;

	@FindBy(how = How.ID, using = "nmfcSub")
	private WebElement nmfcSub;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Save')]")
	private WebElement SaveBtn;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]")
	private WebElement CancleBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='hazmat-input']")
	private WebElement HazmatCheckBox;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add Commodity')]")
	private WebElement AddCommodityBtn;

										/*********** METHODS ***********/
	
	public String enterProductID() {
		logger.info("Enter product ID");
		testUtil.init(this);
		Faker faker = new Faker();
		int num = faker.number().numberBetween(1000, 9999);
		testUtil.setHardWait(1000);
		ProductID.clear();
		testUtil.setHardWait(1000);
		ProductID.sendKeys(String.valueOf(num));
		System.out.println("Added product ID is :" + num);
		return String.valueOf(num);

	}

	public void enterProductID(String no) {
		logger.info("Enter product ID");
		testUtil.init(this);
		ProductID.clear();
		ProductID.sendKeys(no);
	}

	public void enterGoodsQuantity(String no) {
		logger.info("Enter goods quantity");
		testUtil.init(this);
		GoodsQuantity.clear();
		GoodsQuantity.sendKeys(no);
	}

	public void typeDescription(String des) {
		logger.info("Type the description");
		testUtil.init(this);
		testUtil.assetWait(null, Descriprion, 10, 250, TimeUnit.MILLISECONDS);
		Descriprion.clear();
		Descriprion.sendKeys(des);

	}

	public void selectGoodsType(String Type) {
		logger.info("Select goods type");
		testUtil.init(this);
		driver.findElement(By.id("goodsType")).click();
		testUtil.setHardWait(1000);
		//driver.findElement(By.xpath("//*[contains(text(),'" + Type + "')]")).click();
		testUtil.clickElementJavascript(driver.findElement(By.xpath("//*[contains(text(),'" + Type + "')]")));

	}

	public void selectClass(String Type) throws InterruptedException {
		logger.info("Select class");
		testUtil.init(this);
		WebElement classElement = driver.findElement(By.xpath("//span[contains(.,'Class')]"));
		testUtil.WaitForTheElementClickable(classElement);
		testUtil.clickElementJavascript(classElement);

		Thread.sleep(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][text()='" + Type + "']")).click();

	}

	public void EnterTotalWeight(String wgt) {
		testUtil.init(this);
		logger.info("Enter total weight");
		Weight.clear();
		Weight.sendKeys(wgt);

	}

	public void EnterNMFC(String nmfcValue, String nmfcSubValue) {
		testUtil.init(this);
		logger.info("Enter NMFC");
		nmfc.clear();
		nmfc.sendKeys(nmfcValue);
		nmfcSub.clear();
		nmfcSub.sendKeys(nmfcSubValue);
	}

	public void clickOnSaveButton() {
		testUtil.init(this);
		logger.info("Click on Save button");
		testUtil.assetWait(null, SaveBtn, 10, 250, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(SaveBtn);
		// SaveBtn.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnHazmatCheckBox() {
		testUtil.init(this);
		logger.info("Click on Hazmat check box");
		testUtil.clickElementJavascript(HazmatCheckBox);
	}

	public void clickOnCancleButton() {
		testUtil.init(this);
		logger.info("Click on Cancle button");
		CancleBtn.click();
	}

	public void verifyErrorMessage() {
		testUtil.init(this);
		logger.info("Verify error message");
		String error = driver.findElement(By.xpath("(//mat-error[@role='alert'])[1]")).getText();
		Assert.assertEquals(error, "This field is required.");
		String error1 = driver.findElement(By.xpath("(//mat-error[@role='alert'])[2]")).getText();
		Assert.assertEquals(error1, "This field is required.");
		System.out.println("erroe message is" + error);

	}

	public void clickOnAddCommodityButton() {

		logger.info("Click on Add Commodity button");
		testUtil.init(this);
		testUtil.clickElementJavascript(AddCommodityBtn);
		testUtil.setHardWait(2000);
	}

	public void verifyCommodityDetailsNotDisplay() {
		logger.info("Validate Commodity Details you have entered in are not available in Maintain Commodities screen");
		testUtil.init(this);
		driver.findElement(By.xpath("//mat-card-title[@class='mat-card-title']")).isDisplayed();
		System.out.println(driver.findElement(By.xpath("//mat-card-title[@class='mat-card-title']")).getText());
	}

}
