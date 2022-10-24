package eNetPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

public class ENetInvoiceInquiryPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetInvoiceInquiryPage.class);

	@FindBy(id = "mainpage")
	private WebElement frameElement;

	@FindBy(css = "[src*='images/check']")
	private List<WebElement> linkToImages;
	
	@FindBy(how = How.ID, using = "ot")
	private WebElement Terminal;

	@FindBy(how = How.ID, using = "pro")
	private WebElement ProNumber;

	@FindBy(how = How.ID, using = "submitButton")
	private WebElement ProSearch;

	@FindBy(how = How.XPATH, using = "//div[starts-with(@id,'sizeDiv')]")
	private WebElement SearchResultTable;

	@FindBy(how = How.NAME, using = "accountNumber")
	private WebElement AccountNumber;

	@FindBy(how = How.ID, using = "submitButton2")
	private WebElement AccountSearch;

	@FindBy(how = How.ID, using = "emailAddress")
	private WebElement EmailAddress;

	@FindBy(how = How.ID, using = "submitButton1")
	private WebElement EmailSubmit;
	
	@FindBy(how = How.XPATH, using = "//tr[@class='even'][1]//td[2]//a")  //newly added
	private WebElement firstSalesPersonNumberLink;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customerTable']//tr[2]//a") //newly added
	private WebElement firstCustomerNameLink;

				/************************************************************/

	public void switchToFrameElement() {
		logger.info("Switch to VTL Floor Mins frame Element");
		testUtil.init(this);
		testUtil.setExplicitWait(frameElement, 60);
		driver.switchTo().frame(frameElement);
	}

	public void clickOnSalesDistrictNumberLink(String districtNo) {
		logger.info("Click on sales District number link");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//a[text()='" + districtNo + "']"));
		testUtil.setExplicitWait(ele, 20);

		ele.click();
	}

	public void clickOnSalesPersonNumberLink(String salesPerson) {
		logger.info("Click on sales person number link");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//a[text()='" + salesPerson + "']"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
	}

	public void clickOnCustomerNameLink(String customerName) {
		logger.info("Click on customer name link");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//a[text()='" + customerName + "']"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
	}

	public void clickOnLinkToImages() {
		logger.info("Click on link to images link");
		testUtil.init(this);
		testUtil.setExplicitWait(linkToImages.get(0), 20);
		if (linkToImages.size() > 0) {
			linkToImages.get(0).click();
		}
	}
	
	public void validatePageTitle(String name) {
		logger.info("Validate Page Title " + name);
		testUtil.init(this);
		String title = driver.getTitle();
		testUtil.setHardWait(5000);
		Assert.assertTrue(title.contains(name));
		logger.info("Page Title is " + title);
	}

	public void clickOnLogout() {
		logger.info("Logging out from Enet");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		WebElement ele = driver.findElement(By.xpath("//a[@id='logout']"));
		testUtil.WaitForTheElementClickable(ele);
		ele.click();
		testUtil.setHardWait(1000);

	}

	public void clickOnLogoutIntranet() {
		logger.info("Logging out from Intranet");
		testUtil.init(this);
		driver.switchTo().frame(0);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Logout']")));
		ele.click();
	}

	public void enterTerminal(String number) {
		logger.info("Enter OT in Pro field");
		testUtil.init(this);
		Terminal.sendKeys(number);
	}

	public void enterProNumber(String number) {
		logger.info("Enter Pro in Pro field");
		testUtil.init(this);
		ProNumber.sendKeys(number);
	}

	public void clickOnSearch() {
		logger.info("Click On Pro Search Button");
		testUtil.init(this);
		ProSearch.click();
	}

	public void validateResultsTableDisplayed() {
		logger.info("Verify table is displayed");
		testUtil.init(this);
		assertEquals(testUtil.isDisplayed(SearchResultTable), true);
	}

	public String getValuefromTable(String colName, int row) {
		logger.info("Get Pro Number from Results displayed");
		testUtil.init(this);
		String value = null;
		List<WebElement> cols = driver.findElements(By.xpath("//div[starts-with(@id,'sizeDiv')]/table/tbody/tr/th"));
		for (int i = 0; i < cols.size(); i++) {
			String name = cols.get(i).getText();
			if (name.contains(colName)) {
				value = driver
						.findElement(By.xpath(
								"//div[starts-with(@id,'sizeDiv')]/table/tbody/tr[" + row + "]/td[" + (i + 1) + "]"))
						.getText();
				logger.info("Value of " + colName + " from row number " + row + " is " + value);
				break;

			}
		}
		return value;
	}

	public void enterAccNumber(String number) {
		logger.info("Enter Account Number " + number);
		testUtil.init(this);
		AccountNumber.sendKeys(number);
	}

	public void clickOnAccountSearch() {
		logger.info("Click On Account number Search Button");
		testUtil.init(this);
		AccountSearch.click();
	}

	public void clickOnEmailSubmit() {
		logger.info("Click On Email Submit Button");
		testUtil.init(this);
		EmailSubmit.click();
	}

	public void enterEmailID(String email) {
		logger.info("Enter Email ID: " + email);
		testUtil.init(this);
		EmailAddress.sendKeys(email);
	}

	public void selectProFromResults(String pro) {
		logger.info("Select checkBox of pro number: " + pro);
		testUtil.init(this);
		WebElement chkBox = driver.findElement(By.xpath("//*[text()='" + pro + "']/preceding-sibling::td[3]/input"));
		if (!chkBox.isSelected()) {
			chkBox.click();
			logger.info("Checkbox for pro number " + pro + " is selected");
		} else
			logger.info("Checkbox for pro number " + pro + " is already selected");
		assertEquals(chkBox.isSelected(), true);
	}
	
	public void validateSuccessMessage(String message) {
		logger.info("validate Success Message:  " + message);
		testUtil.init(this);
		String mess = driver.findElement(By.xpath("//*[text()[normalize-space()='Email sent successfully.']]")).getText();
		assertTrue(mess.contains(message));
	
	}
		
	

	//Method to select First Sales PersonNumber link -newly created
	public void clickFirstSalesPersonNumberLink() {
		logger.info("Click on first sales person number link");
		testUtil.init(this);
		testUtil.setExplicitWait(firstSalesPersonNumberLink, 20);
		firstSalesPersonNumberLink.click();
	}
	
	//Method to select First Customer Name Link -newly created
	public void clickFirstCustomerNameLink() {
		logger.info("Click on first sales person number link");
		testUtil.init(this);
		testUtil.setExplicitWait(firstCustomerNameLink, 20);
		firstCustomerNameLink.click();
	}
		
	}

