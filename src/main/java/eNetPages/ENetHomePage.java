package eNetPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class ENetHomePage extends TestBase {

	private Logger logger = Logger.getLogger(ENetHomePage.class);

	@FindBy(how = How.XPATH, using = "//a[@id='Applications']")
	private WebElement Applications;

	@FindBy(css = "[id='contentTable'] a[id='Applications']")
	private WebElement applicationTab;

	@FindBy(id = "Reports")
	private WebElement reportsTab;

	@FindBy(xpath = "//a[contains(text(),'Customer Service')]")
	private WebElement customerServiceLink;

	@FindBy(xpath = "//a[contains(text(),'Rate Retriever')]")
	private WebElement rateRetrieverLink;

	@FindBy(how = How.XPATH, using = "//a[@id='logout']")
	private WebElement logout;

	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	private WebElement logoutButton;

	// @FindBy(how = How.XPATH,using = "//a[contains(text(),'Accounts
	// Receivable')]")
	@FindBy(how = How.XPATH, using = "//*[@id=\"ApplicationsContent\"]/a[1]")
	private WebElement accountRecievble;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Undiscounted Freight Report')]")
	private WebElement undiscountedFreight;

	@FindBy(how = How.XPATH, using = "//*[@id='accountNumber']")
	private WebElement accountNum;

	@FindBy(how = How.XPATH, using = "//*[@id='emailAddress']")
	private WebElement emailAdd;

	@FindBy(how = How.ID, using = "submitReport")
	private WebElement submitBtn;

	@FindBy(id = "searchCriteria")
	private WebElement searchCriteria;

	@FindBy(xpath = ".//input[@value = 'Go']")
	private WebElement goBtn;
	
	@FindBy(tagName = "iframe")
	private List<WebElement> noOfIFrame;
	
	//@FindBy(how = How.XPATH, using ="//*[@id='ApplicationsContent']/a[18]")
	@FindBy(how = How.XPATH, using ="//a[contains(text(),'Time Critical Rate Quote')]")
    private WebElement timeCriticalRateQuoteLink;
    
  //  @FindBy(xpath = "/html/body/div[4]/div[2]/div[2]/div[1]/a[12]")
    @FindBy(xpath = "//*[@id='ApplicationsContent']/a[12]")
    private WebElement quoteHistoryLookup;
    
    @FindBy(xpath = "//*[@id='pageTitle']")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[@id='Applications']/div[1]/div[2]/a")
    private WebElement viewAll;
    
    @FindBy(xpath = "/html/body/div[5]/div[11]/a")
    private WebElement timeCriticalRateQuoteviewAllLink;

	@FindBy(xpath = "//*[@id='ApplicationsContent']/a[10]")
    private WebElement rateQuoteUploadLink;
	
	@FindBy(how = How.XPATH, using ="//*[text()='VTL Table Maintenance']")
    private WebElement vtlTableMaintenanceLink;
	
	@FindBy(how=How.XPATH,using="//*[@id='full_name']")
	private WebElement fullName;
	
	/*************************************************************************/

	public void clickOnApplicationsTab() {
		logger.info("Click on Applications tab");
		testUtil.init(this);
		WebElement appTab = testUtil.filterSelector("a", null, null, 0, "id=\"Applications\"");
		testUtil.clickElementJavascript(appTab);
	}

	public void clickOnApplicationWithoutFrame() {
		logger.info("Click on Application tab");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		testUtil.setHardWait(1000);
		applicationTab.click();
	}

	public void clickOnReportsTab() {
		logger.info("Click on Reports tab");
		testUtil.init(this);
		testUtil.setExplicitWait(reportsTab, 20);
		reportsTab.click();
	}

	public void clickOnCustomerServiceLink() {
		logger.info("Click on Customer service link");
		testUtil.init(this);
		driver.switchTo().frame("mainpage");
		testUtil.filterSelector("a", null, null, 0, "Customer Service").click();
	}

	public void clickOnRateRetrieverLink() {
		logger.info("Click on Rate Retriever link");
		testUtil.init(this);
		
		driver.switchTo().frame(0);
		WebElement rateRetrieverLink = testUtil.filterXpath("//a", null, null, 0, "Rate Retriever");
		testUtil.clickElementJavascript(rateRetrieverLink);
	}

	public void clickOnLogout() {
		logger.info("Click on logout");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		testUtil.setHardWait(1000);
		logout.click();

	}

	public void clickOnLogoutButton() {
		logger.info("Click on logout button");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		testUtil.setHardWait(1000);
		logoutButton.click();

	}

	public void clickOnAccountRecievable() {

		logger.info("Click on Account Recievable link");
		testUtil.init(this);

		/*
		 * WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		 * driver.switchTo().frame(frame);
		 */
		testUtil.switchToFrame("mainpage");
		testUtil.setHardWait(4000);
		// testUtil.setExplicitWait(accountRecievble, 60);
		try {
			testUtil.clickElementJavascript(accountRecievble);
		} catch (TimeoutException e) {
			e.printStackTrace();
			System.out.println("Timeout Exceptions");
		}
	}

	public void clickOnUndiscountedFreightReport() {

		logger.info("Click on Undiscounted Frieght Report");
		testUtil.init(this);

		/*
		 * WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		 * driver.switchTo().frame(frame);
		 */
		testUtil.setHardWait(2000);
		// testUtil.setExplicitWait(accountRecievble, 60);
		testUtil.clickElementJavascript(undiscountedFreight);
	}

	public void enterAccountNumber(String accNum) {
		logger.info("Enter Account Number And Email address");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.switchToFrame("mainpage");
		testUtil.clickElementJavascript(accountNum);
		accountNum.sendKeys(accNum);
		testUtil.setHardWait(2000);

	}

	public void enterEmail(String email) {
		logger.info("Enter Email address");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		// testUtil.switchToFrame("mainpage");
		testUtil.clickElementJavascript(emailAdd);
		emailAdd.sendKeys(email);

	}
	
	public void enterFullName(String name) {
		logger.info("Enter full name");
		testUtil.init(this);
		testUtil.assetWait(null, fullName, 10, 200, TimeUnit.MILLISECONDS);
		fullName.sendKeys(name);
		
		
	}

	public void clickOnSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		submitBtn.click();

	}

	public void verifySuccessMessage(String expectedMessage) {

		logger.info("Verify success message");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String message = driver.findElement(By.xpath("//div[contains(text(),'Your request has been submitted.')]"))
				.getText();
		System.out.println("The success message is:" + message);
		Assert.assertEquals(message, expectedMessage);
	}

	public void enterSearchCriteria(String search) {
		logger.info("Enter Search Criteria");
		testUtil.init(this);
		searchCriteria.sendKeys(search);
	}

	public void clickOnGoButton() {
		logger.info("Click on Go button");
		testUtil.init(this);
		testUtil.setExplicitWait(goBtn, 60);
		goBtn.click();
	}

	public void clickOnLogoutButtonFreight() {
		logger.info("Click on logout button");
		testUtil.init(this);
		driver.switchTo().frame(0);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(logoutButton);

	}

	public void verifyPageTitle() {
		testUtil.init(this);
		logger.info("The Home Page displys successfully");

		//String expectedTitle = "EPDS IntranetENet - Company Homepage";
		
		WebElement ele= driver.findElement(By.xpath("//*[@id='pageTitle']"));
		String str=ele.getText();
		System.out.println(str);
		Assert.assertEquals(ele.isDisplayed(), true);

	}
	
	public void clickOnTimeCriticalRateQuoteLink() {
		testUtil.init(this);
		logger.info("Click on Time Critical Rate Quote Link.");
		if(noOfIFrame.size() > 0) {
			testUtil.switchToFrame(0);
		}

		testUtil.assetWaitClickable(null, timeCriticalRateQuoteLink, 10, 200, TimeUnit.MILLISECONDS);
		timeCriticalRateQuoteLink.click();
	}
	
	public void clickQuoteHistoryLookup() {
		testUtil.init(this);
		logger.info("Click Quote History Lookup.");
		if(noOfIFrame.size()> 0){
			testUtil.switchToFrame(0);
		}
		testUtil.assetWaitClickable(null, quoteHistoryLookup, 10, 200, TimeUnit.MILLISECONDS);
		quoteHistoryLookup.click();
	}
	
	public void validatePage() {
		testUtil.init(this);
		logger.info("Validate Page.");
		String pageTtl = pageTitle.getText();
		Assert.assertEquals(pageTtl, "Company Homepage","Page Title doesnot match.");
		logger.info("Page title matched : "+pageTtl);
	}
	
	public void clickViewAll() {
		testUtil.init(this);
		logger.info("Click the View All button.");
		driver.switchTo().frame("mainpage");
		viewAll.click();
	}
	
	public void clickTimeCriticalRateQuoteviewAllLink() {
		testUtil.init(this);
		logger.info("Click viewAll time Critical Rate Quote Link.");
		driver.switchTo().frame("mainpage");
		timeCriticalRateQuoteviewAllLink.click();
	}

	public void verifyPageTitleQaenet02() {
		testUtil.init(this);
		logger.info("The Home Page displys successfully");

		String expectedTitle = "EPDS Intranet - Company Homepage";
		String actualTitle = driver.getTitle();

		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);

	}

	public void clickOnRateQuoteUploadLink() {
		testUtil.init(this);
		logger.info("Click on RateQuoteUploadLink");
		testUtil.setHardWait(1000);
		rateQuoteUploadLink.click();
	}
	
	public void verifyPageTitleQaenet01() {
		testUtil.init(this);
		logger.info("The Home Page displys successfully");

		String expectedTitle = "EPDS IntranetENet - Company Homepage";
		String actualTitle = driver.getTitle();
	
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);

	}

	public void clickOnLogOutButton() {
		logger.info("Click on logout button");
		testUtil.init(this);
		driver.switchTo().frame(0);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(logoutButton);

	}

	public void clickOnVTLTableMaintenanceLink() {
		testUtil.init(this);
		logger.info("Click on VTL Table maintenance link.");
		testUtil.setHardWait(1000);
		vtlTableMaintenanceLink.click();
	}
	public void clickViewAll(String tab) {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		driver.findElement(By.xpath("//*[@id='"+tab+"']/descendant::a[text()='View All']")).click();
		logger.info("Clicked on View All under "+tab+" Tab");

	}
	
}
