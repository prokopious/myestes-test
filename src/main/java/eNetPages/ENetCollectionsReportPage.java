package eNetPages;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import testBase.TestBase;
import util.TestUtil;

public class ENetCollectionsReportPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetCollectionsReportPage.class);

	@FindBy(xpath = "//*[@id='pageTitle']")
	private WebElement pageTtl;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody")
	private WebElement collectionActivityReportGrid;
	
	@FindBy(xpath = "//*[@id='accountNumber']")
	private WebElement accountNUmberInput;
	
	@FindBy(xpath = "/html/body/div/form/table/tbody/tr[1]/td[2]/input")
	private WebElement frieghtAccount;
	
	@FindBy(xpath = "//*[@id='emailAddress']")
	private WebElement emailIdInput;
	
	@FindBy(xpath = "/html/body/div/form/table/tbody/tr[2]/td[2]/input")
	private WebElement frieghtEmail;
	
	@FindBy(xpath = "/html/body/div/form/table/tbody/tr[3]/td[2]/input")
	private WebElement submitBtn;
	
	@FindBy(xpath = "/html/body/div/form/table/tbody/tr[3]/td[2]/input")
	private WebElement frieghtSubmitBtn;
	
	@FindBy(xpath = "//*[@id='logout']")
	private WebElement logoutBtn;
	
	@FindBy(xpath = "/html/body/form/table/tbody/tr[3]/td/input")
	private WebElement logoutConfirmationBtn;
	
	public void verifyPageTitle() {
		testUtil.init(this);
		logger.info("Verify the Collections Report page.");
	
			String pageTitle="Collections Reports";
			
			testUtil.setExplicitWait(pageTtl, 90);
			WebElement title= testUtil.pollDOM(null, pageTtl, 60);
			String page = testUtil.getTextFromElement(title);
			
			//Assert.assertEquals(page, "Collections Reports","Page title doesnot match.");
			
			assertTrue(page.contains(pageTitle));
			
	
	}
	public void switchSubFrames(int frameIndex) {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		WebElement frameNum = driver.switchTo().frame("mainpage").findElements(By.tagName("iframe")).get(frameIndex);
		driver.switchTo().frame(frameNum);
	}
	
	public void verifyCollectionActivityReportTableIsNotEmpty() {
		testUtil.init(this);
		//Switch to first sub frame.
		switchSubFrames(0);
		String firstRecord = collectionActivityReportGrid.findElement(By.xpath("tr[2]/td[1]")).getText();
		if(!firstRecord.isEmpty()) {
			logger.info("Table data is not empty.");
		}else {
			Assert.fail("Table is empty.");
		}
	}
	
	public void enterAccountNumber(String accNumber) {
		testUtil.init(this);
		switchSubFrames(1);
		logger.info("Enter Account Number.");
		accountNUmberInput.sendKeys(accNumber);
	}
	
	public void enterEmailAddress(String email) {
		testUtil.init(this);
		logger.info("Enter Email Address.");
		emailIdInput.sendKeys(email);
	}
	
	public void clickOnSubmit() {
		testUtil.init(this);
		logger.info("Click on Submit button.");
		submitBtn.click();
	}
	
	public void enterFrieghtAccountNumber(String frieghtAccNumber) {
		testUtil.init(this);
		switchSubFrames(2);
		logger.info("Enter Frieght Account number.");
		frieghtAccount.sendKeys(frieghtAccNumber);
	}
	
	public void enterFrieghtEmailAddress(String frghtEmail) {
		testUtil.init(this);
		logger.info("Enter Frieght Email Address.");
		frieghtEmail.sendKeys(frghtEmail);
	}
	
	public void clickOnFrieghtSubmit() {
		testUtil.init(this);
		logger.info("Click on Frieght Submit button.");
		frieghtSubmitBtn.click();
	}
	
	public void clickOnLogout() {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		logger.info("Click On Logout button.");
		logoutBtn.click();
	}
	
	public void clickOnLogoutConfirmation() {
		testUtil.init(this);
		driver.switchTo().frame("mainpage");
		logger.info("Click On Logout confirmation button.");
		logoutConfirmationBtn.click();
	}
	

}



