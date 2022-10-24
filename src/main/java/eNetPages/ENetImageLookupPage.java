package eNetPages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetImageLookupPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetLTLRateRequestPage.class);

	@FindBy(how = How.ID, using = "docType")
	private WebElement DocType;

	@FindBy(how = How.ID, using = "lowScanDate")
	private WebElement LowScanDt;

	@FindBy(how = How.ID, using = "highScanDate")
	private WebElement HighScanDt;

	@FindBy(how = How.ID, using = "submitButton")
	private WebElement SubmitBtn;

	@FindBy(how = How.ID, using = "CLAIMNO")
	private WebElement ClaimNumber;

	@FindBy(how = How.ID, using = "PRO")
	private WebElement ProSequence;

	@FindBy(how = How.ID, using = "OT")
	private WebElement OriginalTerminal;

	// New Tab Index Information Elements
	
	@FindBy(how = How.XPATH, using = "//*[text()='Claim Number ']/following::tr[1]/td[1]")
	private WebElement ClaimNum;
	
	@FindBy(how = How.XPATH, using = "//img[@id='image']")
	private WebElement Image;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Pro Sequence ']/following::tr[1]/td[1]")
	private WebElement ProNum;

	/*************************************************************************/
	
	
	public void validatePagetitle(String expectedTitle) {
		logger.info("Validate page title");
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		String title = driver.findElement(By.xpath("//*[text()='Image Lookup']")).getText();
		System.out.println(title);
		Assert.assertTrue(title.contains(expectedTitle));

	}

	public void selectDocType(String type) {
		logger.info("Select Document type");
		testUtil.init(this);
		testUtil.selectUsingVisibleText(DocType, type);
	}

	public void enterLowScanDt(String dt) {
		testUtil.init(this);
		LowScanDt.sendKeys(dt);
		logger.info("Entered low scan date " + dt);
	}

	public void enterHighScanDt(String dt) {
		testUtil.init(this);
		HighScanDt.sendKeys(dt);
		logger.info("Entered High scan date " + dt);
	}

	public void ClickOnSubmit() {
		testUtil.init(this);
		SubmitBtn.click();
		logger.info("Clicked on Submit Button");
		testUtil.setHardWait(1000);
	}

	public void enterClaimNumber(String num) {
		testUtil.init(this);
		ClaimNumber.sendKeys(num);
		logger.info("Entered Claim Number " + num);
	}

	public void validateSearchResults() {
		logger.info("Validate Document type Search Results");
		testUtil.init(this);
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='documentSearchDisplayResults']/descendant::tr"));

		if (rows.size() >= 2)
			logger.info("Total " + rows.size() + " results for Document type are displayed");
		else
			logger.info("Not one Result for Document type are displayed");

	}

	public void clickOnCheckBox(String claimNum) {
		logger.info("Click on CheckBox For the Claim Number " + claimNum);
		testUtil.init(this);
		WebElement checkBox = driver
				.findElement(By.xpath("//*[text()='" + claimNum + "']/preceding-sibling::td[4]/input"));
		assertEquals(checkBox.isSelected(), false);
		checkBox.click();

	}

	public void clickOnViewDocumentIcon(String claimNum) {
		logger.info("Click on View Icon Next to Checkbox to View Imaging");
		testUtil.init(this);
		WebElement view = driver.findElement(By.xpath("//*[text()='" + claimNum + "']/preceding-sibling::td[4]/a"));
		assertEquals(view.isDisplayed(), true);
		view.click();

	}

	public String getClaimNum() {
		logger.info("Get Claim Number from Index Information");
		testUtil.init(this);
		String num = ClaimNum.getText();
		return num;
	}

	public void enterPro(String pro) {
		testUtil.init(this);
		ProSequence.sendKeys(pro);
		logger.info("Entered Pro Sequence is " + pro);
	}

	public void enterOriginalTerminal(String ot) {
		testUtil.init(this);
		OriginalTerminal.sendKeys(ot);
		logger.info("Entered Original Terminal is  " + ot);
	}

	public void selectCheckbox(int row) {
		logger.info("SelectCheckbox");
		testUtil.init(this);
		WebElement checkBox = driver.findElement(By
				.xpath("//*[@id='documentSearchDisplayResults']/descendant::table/tbody/tr[" + row + "]/td[1]/input"));
		assertEquals(checkBox.isSelected(), false);
		checkBox.click();
	}

	public void clickViewIcon(int row) {
		logger.info("Click on View Icon Next to Checkbox to View Imaging");
		testUtil.init(this);
		WebElement checkbox = driver.findElement(
				By.xpath("//*[@id='documentSearchDisplayResults']/descendant::table/tbody/tr[" + row + "]/td[1]/a"));
		checkbox.click();
	}
	
	public void verifyImageIsdisplayed() {
		logger.info("Verify Attached Image is displayed or not");
		testUtil.init(this);
		assertEquals(Image.isDisplayed(),true);
		logger.info("Image is displayed");
		}
	
	public String getProNum() {
		logger.info("Get PRO Number from Index Information");
		testUtil.init(this);
		String num = ProNum.getText();
		return num;
	}

	public void verifyImageDisplayed() {
		testUtil.init(this);
		WebElement image= driver.findElement(By.xpath("//*[@id='image']"));
		assertEquals(image.isDisplayed(),true);
		logger.info("Image is displayed");
	}

}
