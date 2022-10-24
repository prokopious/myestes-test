package rateQuotePages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class MyEstesWelcomePage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesWelcomePage.class);
								
											/*********** ELEMENTS ***********/

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'LTL Rate Quote')]")
	private WebElement Rate;

	@FindBy(how = How.XPATH, using = "//div[@class='Yellow_Callout_Content']//input[@name='search_criteria']")
	private WebElement Search;

	@FindBy(how = How.XPATH, using = "//div[@id='shiptracking']//input[@value='Submit']")
	private WebElement Submit;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dropdown-0']")
	private WebElement weShipLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Rate Quote')]")
	private WebElement RateQoute;

	@FindBy(xpath = "//*[@id='sidebar']/lib-left-navigation/div/ul/li[4]/a")
	private WebElement onLineReportingLink;

	// *** Manage ***
	// Click on Manage
	@FindBy(how = How.ID, using = "dropdown-2")
	private WebElement weManageLink;
	// Claims from Manage
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[1]/li[3]/div/a[7]")
	private WebElement weClaimsFromManage;

	@FindBy(partialLinkText = "Image Retrieval & Viewing")
	private WebElement ImageViewing;
	
	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement MyEstes;//*[@id="dropdown-0"]

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Weight & Research Inquiry')]")
	private WebElement WeightReaearch;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"main-nav\"]/ul[2]/li/div/div/span")
	private WebElement AccountNum;
	
	@FindBy(linkText = "Transit Time Calculator")
	private WebElement TransitTimeCalculator;
	
	@FindBy(xpath = "//mat-card-content[h4[text()='Request a Quote']]/a/button")
	private WebElement startRequestBtn;

										/*********** METHODS ***********/
	
	public void clickOnShipTab() {
		logger.info("Click on Ship tab");
		testUtil.init(this);
		/*WebElement element=driver.findElement(By.xpath("//button[contains(text(),'Track Now')]"));
		testUtil.WaitForElementVisibleWithPollingTime(element, 15, 25);*/
		WebDriverWait wait= new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dropdown-0']")));
		weShipLink.click();
	}
	
	public void clickOnTransitTimeCalculator() {
		testUtil.init(this);
		logger.info("Click on Transit Time Calculator tab");
		testUtil.setHardWait(1000);
		TransitTimeCalculator.click();
	}
	
	public void clickOnRateQoute() {
		logger.info("Click on Rate Quote ");
		testUtil.init(this);
		testUtil.assetWait(null, RateQoute, 10, 200, TimeUnit.MILLISECONDS);
		//RateQoute.click();
		testUtil.clickElementJavascript(RateQoute);
		testUtil.setHardWait(1000);
	}

	public void clickOnLTLRateQoute() {
		logger.info("Click on LTL Rate Quote");
		testUtil.init(this);
		Rate.click();
	}
	
	public void clickOnMyEstes() {
		testUtil.init(this);
		MyEstes.click();
		logger.info("Click on MyEstes button");
		MyEstes.click();
	}

	public void enterPROValue() {
		logger.info("Enter PRO Nmuber and click on Search");
		testUtil.init(this);
		Search.sendKeys("0700551248");
	}

	public void clickOnSubmit() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		Submit.submit();
	}
	
	public void clickOnManageLink () throws InterruptedException {
		logger.info("Clicking on Manage Link");
		testUtil.init(this);
		Thread.sleep(2000);
		weManageLink.click();		
	}	
	// Click on Claims From Manage 
	public void clickOnClaimsFromManage () {
		logger.info("Click on Claims From Manage");
		testUtil.init(this);
		weClaimsFromManage.click();		
	}
	
	public void clickOnWeightResearchInquiry() {
		logger.info("Click on Weight and Research Inquiry");
		testUtil.init(this);
		WeightReaearch.click();		
	}
	
	public void clickOnShipmentManifest() {
		logger.info("Click on Weight and Research Inquiry");
		testUtil.init(this);
		WebDriverWait wait= new WebDriverWait(driver,90);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Shipment Manifest")));
		driver.findElement(By.partialLinkText("Shipment Manifest")).click();
	}
	
	public String recordAccountNumber() {
		logger.info("Record my estes login accont number");
		testUtil.init(this);
		String num=AccountNum.getText();
		System.out.println("Account number is :" + num);
		return num;
	}
	
	public void clickOnThirdPartyRadioButton() {
		logger.info("Click on Third Payty ");
		testUtil.init(this);
		driver.findElement(By.id("mat-radio-4")).click();
	} 
	
	public void clickOnShipperRadioButton() {
		logger.info("Click on Shipper Radio button ");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		driver.findElement(By.id("mat-radio-2")).click();
	} 
	public void clickOnStartRequestButton() {
		logger.info("Click on Start request button on Welcome page");
			testUtil.init(this);
			testUtil.setExplicitWait(startRequestBtn, 30);
			startRequestBtn.click();
		}
	
	
	public void clickOnReportingLink() {
		logger.info("Click on the reporting link on the left navigation bar");
		testUtil.init(this);
		testUtil.setExplicitWait(onLineReportingLink, 60);
		onLineReportingLink.click();

	}
	
	
	
	
	

}
