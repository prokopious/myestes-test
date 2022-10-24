package eNetPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class ENetRateQuoteUploadPage extends TestBase {

	
	
private Logger logger = Logger.getLogger(ENetRateQuoteUploadPage.class);
	
	@FindBy(xpath = "//*[@id=\"title-bar\"]")
	private WebElement pageTitle;
	
	@FindBy(xpath = "/html/body/div[2]/form/div/table[1]/tbody/tr/td[2]/input")
	private WebElement chooseFile;
	
	@FindBy(xpath = "//*[@id='mainpage']")
	private WebElement mainPageFrame;
	
	@FindBy(xpath = "//*[@id=\"email1\"]")
	private WebElement emailAddress;
	
	@FindBy(xpath = "//*[@id=\"container\"]/form/div/div[1]/input")
	private WebElement submitBtn;
	
	@FindBy(xpath = "/html/body/div[2]/div/div")
	private WebElement nonCSVFileErr;
	
	/**************************Method***************************/
	public void verifyPageTitle() {
		testUtil.init(this);
		logger.info("Verify Page Title.");
		testUtil.assetWait(null,pageTitle, 10, 250, TimeUnit.MILLISECONDS);
		String pageTtl = pageTitle.getText().trim();
		logger.info("Page Title found : "+pageTtl);
		Assert.assertEquals(pageTtl, "Rate Quote Upload".trim(),"Page Title doesnot match.");

	}
	
	public void clickOnChooseFile() {
		testUtil.init(this);
		testUtil.setHardWait(1000);
		logger.info("Click on Choose File.");
		chooseFile.click();
	}
	
	public void uploadNonCSVfile() {
		testUtil.init(this);
		logger.info("Upload a non CSV file");
		testUtil.setHardWait(1000);
		String path = TestUtil.getCurrentWorkingPath()+"/pom.xml";
		logger.info("Path : "+path);
		chooseFile.sendKeys(path);
	}
	
	public void switchToMainFrame() {
		testUtil.init(this);
		testUtil.setHardWait(2000);
		logger.info("Switch to Main page Frame.");

		driver.switchTo().frame(mainPageFrame);
	}
	
	public void enterEmailAddress(String email) {
		testUtil.init(this);
		logger.info("Enter Email Address.");
		emailAddress.sendKeys(email);
	}
	
	public void clickOnSubmit() {
		testUtil.init(this);
		logger.info("Click on Submit button.");
		submitBtn.click();
	}
	
	public void verifyNonCSVFileErr() {
		testUtil.init(this);
		logger.info("Verify an error message displayed: *Please choose a csv file to upload*");
		testUtil.setHardWait(500);
		String csvErr = nonCSVFileErr.getText();
		logger.info("CSV File Error : "+csvErr);
		Assert.assertEquals(csvErr.trim(), "Please choose a csv file to upload".trim(),"Non CSV file error not correct.");
	}
}


