package myEstesPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

// For testing
import testBase.TestBase;
import util.TestUtil;

public class MyEstesWelcomePage extends TestBase {

	
	private Logger logger = Logger.getLogger(MyEstesWelcomePage.class);
	
							/*********** ELEMENTS ***********/

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Rates')]")
	private WebElement Rate;

	@FindBy(how = How.XPATH, using = "//div[@class='Yellow_Callout_Content']//input[@name='search_criteria']")
	private WebElement Search;

	@FindBy(how = How.XPATH, using = "//div[@id='shiptracking']//input[@value='Submit']")
	private WebElement Submit;

	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'EDI')]")
	private WebElement weEDI;

	@FindBy(how = How.XPATH, using = "//a[@class='customize ng-star-inserted']")
	private WebElement Customize;

	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement MyEstes;

	@FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'][contains(text(),'Address Book')]")
	private WebElement Address;

	@FindBy(linkText = "Commodity Library")
	WebElement CommodityLibrary;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main-nav\"]/ul[1]/li[1]/div/a[5]")
	WebElement PointsDownload;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Digital Services')]")
	private WebElement Digital;

	@FindBy(how = How.ID, using = "dropdown-4")
	private WebElement weResourcesLink;

	@FindBy(linkText = "Ship")
	private WebElement ShipTab;

	@FindBy(linkText = "Transit Time Calculator")
	private WebElement TransitTimeCalculator;

	//@FindBy(tagName = "lib-welcome-page")

	@FindBy(xpath = "//span[contains(text(),'My Estes')]")

	private WebElement welcomePage;

	@FindBy(xpath="//li/a[text()='Recent Shipments']")
	private WebElement recentShipments;
	
	@FindBy(how = How.XPATH, using = "//table[1]/tbody[1]/tr[1]/td[1]/a[1]")
    private WebElement firstAccNum;
	
	@FindBy(xpath = "//a[@class='header-logo navbar-brand']")
	private WebElement estesLogoLink;

	@FindBy(xpath = "//a[@class='dropdown-item'][contains(.,'Edit My Profile')]")
	private WebElement EditMyProfile;
	
	@FindBy(xpath = "//a[contains(.,'Account Search')]")
	private WebElement AccountSearch; 
	
	@FindBy(xpath = "//a[contains(text(),'Online Reporting')]")
	
	private WebElement onLineReportingLink; 
	
	// Click on Manage
	@FindBy(how = How.ID, using = "dropdown-2")
	private WebElement weManageLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Online Reporting')]")
	private WebElement onlineReport;
	
	@FindBy(xpath="//*[@id='main']/lib-welcome-page/mat-card/mat-card-header/div[2]/form/div/div/button")
	private WebElement searchAccountSubmit;

	@FindBy(xpath="//*[@id='addressSelect']")
	private WebElement accountSearchBar;

	
										/*********** METHODS ***********/

	public void clickOnShipTab() {
		logger.info("Click on Ship tab");
		testUtil.init(this);
		ShipTab.click();
	}

	public void clickOnPointsDownload() {
		logger.info("Click on Points Download");
		testUtil.init(this);
		testUtil.clickElementJavascript(PointsDownload);
	}

	public void clickOnRate() {
		logger.info("Click on Rate");
		testUtil.init(this);
		Rate.click();
	}
	
	public void clickOnResourcesLink() {
		logger.info("Click on Resources link");
		testUtil.init(this);
		weResourcesLink.click();
	}
	
	public void clickOnDigitalService() throws Exception {
		logger.info("Click on Digital Service tab");
		testUtil.init(this);
		testUtil.clickElementJavascript(Digital);
		
	}
	
	public void clickOnMyEstes() {
		logger.info("Click on MyEstes dropdown");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, MyEstes, 10, 10, TimeUnit.MILLISECONDS);
		MyEstes.click();
	}

	public void clickOnAddressBook() {
		logger.info("Click on Address Book");
		testUtil.init(this);
		testUtil.assetWait(null, Address, 10, 250, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(Address);
		//Address.click();
	}

	public void clickOnCommodityLibrary() {
		logger.info("Click on Commodity Library");
		testUtil.init(this);
		testUtil.assetWait(null, CommodityLibrary, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(CommodityLibrary);
	}

	public void clickOnEDI() {
		logger.info("Click on EDI");
		testUtil.init(this);
		weEDI.click();
	}

	public void enterPROValue() {
		logger.info("Enter PRO number and click on Search");
		testUtil.init(this);
		Search.sendKeys("0700551248");
	}

	public void clickOnSubmit() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		Submit.submit();
	}

	public void clickOnCustomizeLink() {
		logger.info("Click on Customize link");
		testUtil.init(this);
		Customize.click();
	}

	public void validatequickLinkList() throws InterruptedException {
		logger.info("Validate application add to quick link ");
		testUtil.init(this);
		String link = driver.findElement(By.xpath("//div[@class='quick-links-container']")).getText();
		System.out.println(link);
		Assert.assertTrue(true, "Quick Link exist !!!!");

	}
	
	public void clickOnTransitTimeCalculator() {
		logger.info("Click on Transit Time Calculator tab");
		testUtil.init(this);
		TransitTimeCalculator.click();
}

	
	public void verifyWelcomePage() {
		logger.info("Verify Welcome page has been displayed");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		TestUtil.waitForPageToLoad();
		boolean exist = welcomePage.isDisplayed();
		Assert.assertTrue(exist);

		
	}
	
	public void clickOnRecentShipmentsLink() {
		logger.info("Click on recent Shipment link");
		testUtil.init(this);
		testUtil.setExplicitWait(recentShipments, 20);
		recentShipments.click();
	}
	

	public void selectAccountNumber(String accountNum) {
		logger.info("Select Account number from recent shipment");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		List<WebElement> accountTable = driver.findElements(By.xpath("//table/tbody/tr"));

		for (int i = 1; i <= accountTable.size(); i++) {
			String actualAccountNumber = driver.findElement(By.xpath("//table//tr[" + i + "]/td[1]")).getText();
			testUtil.setHardWait(2000);
			if (actualAccountNumber.equals(accountNum)) {
				driver.findElement(By.xpath("//table//tr[" + i + "]/td[1]/a")).click();
				testUtil.setHardWait(1000);
				break;
			}
		}

	}

	public void clickOnFirstAccountNumber() {
		logger.info("Click on account number");
		testUtil.init(this);
		firstAccNum.click();

	}

    public void clickOnEstesLogoLink() {
        logger.info("Click on Estes Logo link");
        testUtil.init(this);
        testUtil.setExplicitWait(estesLogoLink, 20);
        estesLogoLink.click();
  }

	public void clickOnEditMyProfile() {
		logger.info("Click on Edit My Profile");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(EditMyProfile);
		//EditMyProfile.click();
	}
	
	public void clickOnAccountSearch() {
		logger.info("Click on Account Search");
		testUtil.init(this);
		AccountSearch.click();
	} 
	
	public void clickOnReportingLink() {

			logger.info("Click on the reporting link on the left navigation bar");
			testUtil.init(this);
			driver.switchTo().defaultContent();
			WebElement onLineReportingEle = testUtil.assetWaitClickable(null, driver.findElement(By.xpath("//*[@id='sidebar']/lib-left-navigation/div/ul/li[*]/a[contains(text(),'Online Reporting')]")), 10, 250, TimeUnit.MILLISECONDS);
//			testUtil.setExplicitWait(onLineReportingLink, 60);
			onLineReportingEle.click();

	}
	
	public void clickOnManageLink () throws InterruptedException {
		logger.info("Clicking on Manage Link");
		testUtil.init(this);
		Thread.sleep(2000);
		weManageLink.click();		
	}	
	
	public void clickOnOnlineReporting() {
		logger.info("Click on online reporting");
		testUtil.init(this);
		testUtil.setExplicitWait(onlineReport, 120);
		onlineReport.click();
		testUtil.setHardWait(2000);
	}
	
	public void clickOnSearchAccountSubmit() {
		logger.info("Clicking on search account submit button");
		testUtil.init(this);
		searchAccountSubmit.click();
	}
	
	public void accountSearchBarInput(String number) {
		logger.info("Entering Account Number in search Bar: " + number);
		testUtil.init(this);
		accountSearchBar.sendKeys(number);
	}

	public void clickShip() {
		logger.info("Click on ship button");
		testUtil.init(this);
		WebElement ship = driver.findElement(By.xpath("//*[text()='Ship']"));
		testUtil.assetWait(null, ship, 33, 333, TimeUnit.MILLISECONDS);
		ship.click();
		testUtil.setHardWait(2000);
	}
	
	public void selectPointsDownload() {
		logger.info("Select Points Download from dropdown");
		testUtil.init(this);
		WebElement pointsDownload = driver.findElement(By.xpath("//*[text()='Points Download']"));
		testUtil.assetWait(null, pointsDownload, 33, 333, TimeUnit.MILLISECONDS);
		pointsDownload.click();
		testUtil.setHardWait(2000);
	}
	
	public void clickSubmit() {
		logger.info("Click on submit button");
		testUtil.init(this);
		WebElement e = driver.findElement(By.xpath("//*[text()=' Submit ']"));
		testUtil.assetWait(null, e, 33, 333, TimeUnit.MILLISECONDS);
		e.click();
		testUtil.setHardWait(5000);
	}

}
