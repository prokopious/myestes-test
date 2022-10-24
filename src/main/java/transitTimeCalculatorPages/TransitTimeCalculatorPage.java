package transitTimeCalculatorPages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;


public class TransitTimeCalculatorPage extends TestBase{
	
	private Logger logger = Logger.getLogger(TransitTimeCalculatorPage.class);
	
	@FindBy(how = How.XPATH, using = "(//*[@id='country'])[1]")
	private WebElement OrgSelectCountry;

	@FindBy(how = How.XPATH, using = "(//*[@id='country'])[2]")
	private WebElement DesSelectCountry;
	
	@FindBy(how = How.XPATH, using = "(//input[@id='zip'])[1]")
	private WebElement OrgZip;
	
	@FindBy(how = How.XPATH, using = "(//input[@id='zip'])[2]")
	private WebElement DesZip;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary mr-3']")
	private WebElement SubmitBtn;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default']")
	private WebElement ClearBtn;
	
	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='shipmentDate']")
	private WebElement ShipmentDatefield;
	
	@FindBy(xpath="//h1//span[contains(text(),'Transit Time Calculator')]")
	private WebElement transitTimeCalculatorTitle;

								/*********************METHODS*************************/
	
	
	public void selectOriginCountry(String country) {
		logger.info("Select Origin Country ");
		testUtil.init(this);
		OrgSelectCountry.click();
		testUtil.setHardWait(1000);
		//driver.findElement(By.xpath("//span[contains(text(),'"+country+"')]")).click();	
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+country+"')]")).click();
	}
	
	public void selectDestinationCountry(String country) {
		logger.info("Select Destination Country ");
		testUtil.init(this);
		DesSelectCountry.click();
		testUtil.setHardWait(2000);
		//driver.findElement(By.xpath("//span[contains(text(),'"+country+"')]")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+country+"')]")).click();
	}
	
	public void selectShippingDate() {
		logger.info("Select Shipping Date ");
		testUtil.init(this);
		ShipmentDatefield.click();
		testUtil.setHardWait(500);
		ShipmentDatefield.sendKeys(testUtil.todaysDate());		
	}
	
	
	public void clickOnSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		testUtil.clickElementJavascript(SubmitBtn);
		testUtil.setHardWait(1000);
		
	}
	
	public void clickOnClearButton() {
		logger.info("Click on Clear button");
		testUtil.init(this);
		testUtil.clickElementJavascript(ClearBtn);
	}
	
	public void enterOriginZipCode(String zip) {
		logger.info("Enter Origin zip code");
		testUtil.init(this);
		OrgZip.sendKeys(zip);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[contains(text(),'"+zip+"')]")).click();
	}
	
	public void enterDestinationZipCode(String zip) {
		logger.info("Enter destination zip code");
		testUtil.init(this);
		DesZip.sendKeys(zip);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[contains(text(),'"+zip+"')]")).click();
	}
	
	
	public String validateErrorMessageForAllRequiredField() {
		logger.info("Validate error messages are received for all Required fields.");
		testUtil.init(this);
		String errMsg=driver.findElement(By.xpath("//snack-bar-container[@role='alert']")).getText();	
		testUtil.setHardWait(2000);
		System.out.println(errMsg);
		return errMsg;
	}
	
	public void validateOriginSuportingTerminalDisplay() {
		logger.info("Validate the Origin Supporting Terminal ");
		testUtil.init(this);
		String terminal=driver.findElement(By.xpath("//div[@class='col-12 col-md-4 col-lg-3 pb-3']")).getText();	
		System.out.println(terminal);
		
		if(!terminal.isEmpty()) {
			System.out.println("Origin suport terminal Diplayed !!!");
			assertTrue(true);
		}else {
			System.out.println("terminal NOT Diplayed !!!!");
		}
	}
	
	public void validateDestinationSuportingTerminalDisplay() {
		logger.info("Validate the Destination Supporting Terminal");
		testUtil.init(this);
		String terminal=driver.findElement(By.xpath("//div[@class='col-12 col-md-5 col-lg-3 pb-3']")).getText();	
		System.out.println(terminal);
		
		if(!terminal.isEmpty()) {
			System.out.println("Destination suport terminal Diplayed !!!");
			assertTrue(true);
		}else {
			System.out.println("Terminal NOT Diplayed !!!!");
		}
	}
	
	public void validateServiceDisplay() {
		logger.info("Validate Service - (Days it will take)");
		testUtil.init(this);
		String service=driver.findElement(By.xpath("//p[@class='d-block d-md-flex justify-content-center m-0']")).getText().trim();	
		System.out.println("Service :" +service);
		
		if(!service.isEmpty()) {
			System.out.println("Service - (Days it will take) Diplayed !!!");
			assertTrue(true);
		}else {
			System.out.println("Service - (Days it will take) NOT Displayed !!!!");
		}
	}
	
	
	public void validateOriginPopulated(String origin) {
		logger.info("Verify Origin is populated in Transit Time details");
		testUtil.init(this);
		
		String actual = driver.findElement(By.xpath("//*[@id=\"main\"]/app-transit-time-calculator/mat-card[2]/mat-card-content/div/div[1]/div[1]/span[1]")).getText();
		Assert.assertEquals(actual, origin);
	}
	
	public void validateDestinationPopulated(String dest) {
		logger.info("Verify Destination is populated in Transit Time details");
		testUtil.init(this);
		
		String actual = driver.findElement(By.xpath("//*[@id=\"main\"]/app-transit-time-calculator/mat-card[2]/mat-card-content/div/div[1]/div[3]/span[1]")).getText();
		Assert.assertEquals(actual, dest);
	}
	
	public void verifyTransitTimeCalculatorPageTitle() {
		logger.info("Validate Transit Time Calculator Page Title");
		testUtil.init(this);
		testUtil.getCurrentPageTitle();
	} 
	
	public void verifyTransitTimeCalculatorPage() {
        logger.info("Verify Transit time calculate Page");
        testUtil.init(this);
        testUtil.setExplicitWait(transitTimeCalculatorTitle, 20);
        
        boolean title = transitTimeCalculatorTitle.isDisplayed();
        Assert.assertTrue(title);
  }

}
