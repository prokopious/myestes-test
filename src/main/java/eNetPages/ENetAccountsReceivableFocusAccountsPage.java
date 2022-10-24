package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import testBase.TestBase;

public class ENetAccountsReceivableFocusAccountsPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetAccountsReceivableFocusAccountsPage.class);

	/****************************** METHODS **************************/
	
	public void verifyAccountsReceivableFocusAccounts() {
		logger.info("Verify page");
		testUtil.init(this);
		driver.switchTo().frame("mainpage");
		String pageTtl = driver.findElement(By.className("title")).getText().trim();
		Assert.assertEquals(pageTtl, "Accounts Receivable - Focus Accounts ".trim(), "Page Title does not match.");
		logger.info("Accounts Receivable - Focus Accounts Page is displayed");	
	}
	

	public void verifyAccountsReceivableFocusAccountPage() {
		logger.info("Verify page");
		testUtil.init(this);
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertTrue(title.contains("Accounts Receivable - Focus Accounts")); 
	}
	
	public void selectOptionAddanAccount() {
		testUtil.init(this);
		logger.info("Select Option Add an Account");
		Select sel = new Select(driver.findElement(By.id("option")));
		sel.selectByValue("A");
	}
	
	public void clickOnBinocularSymbol() {
		testUtil.init(this);
		logger.info("Click on Binocular symbol.");
		WebElement binocularSymbol = driver.findElement(By.xpath("//div[@id='account']/a/img"));
		binocularSymbol.click();
	}
	
	public void validateAccountSearchWindow() {
		testUtil.init(this);
		logger.info("Verify the Account Search Window.");
		String paneTitle = driver.findElement(By.id("paneTitle")).getText();
		Assert.assertEquals(paneTitle, "Account Search".trim(), "Pane Title does not match.");
	}
	
	public void enterAccountNumberKeyWord(String keyWord) {
		testUtil.init(this);
		logger.info("Enter Keyword for Account number.");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("fullViewIframe");
		WebElement accNumber = driver.findElement(By.id("accountName"));
		accNumber.sendKeys(keyWord);
	}
	
	public void clickOnSubmitButton() {
		testUtil.init(this);
		logger.info("Click on Submit Button.");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
	}
	
	public void clickOnPrintButton() throws AWTException {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		logger.info("Click On Print Button.");
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/button[1]")).click();
		testUtil.setHardWait(2000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
//		Actions ac = new Actions(driver);
//		ac.keyDown(Keys.ENTER).build().perform();
//		Alert alert = driver.switchTo().alert();
//		alert.accept();
//		Actions ac = new Actions(driver);
//		ac.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void verifySearchResultDisplays() {
		testUtil.init(this);
		logger.info("Verify Account Search Columns.");
		
		//Changed the locator as it is not valid one to check is displayed
		//WebElement resultLabel = testUtil.assetWaitDisplayed(driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr/td[1]")), 10, 100, TimeUnit.MILLISECONDS);
		
		WebElement resultLabel = driver.findElement(By.xpath("//td[contains(text(),'Results')]"));  
		
		if(resultLabel.isDisplayed()) {
			logger.info("Search Result appeared.");
		}else {
			Assert.fail("Search result not present.");
		}
		
	}
	
	//driver.switchTo().defaultContent();
//	driver.switchTo().frame("fullViewIframe");
	
}


