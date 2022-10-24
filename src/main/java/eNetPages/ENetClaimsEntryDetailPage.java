package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetClaimsEntryDetailPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetClaimsEntryDetailPage.class);

	/************************************************************** Web Elements **************************************************************************/
	
	

	@FindBy(how = How.ID, using = "PROClaimHeaderField")
	private WebElement proNum;

	@FindBy(how = How.ID, using = "claimAmountClaimHeaderField")
	private WebElement claimAmt;

	@FindBy(how = How.ID, using = "confirmButton")
	private WebElement confirmBtn;

	/************************************************************** Methods  **************************************************************************/


	
	public void verifyPRONumIsDisplayed(String expectedPRONum) {
		testUtil.init(this);
		logger.info("Verify Pro number is populated as expected");

		testUtil.setHardWait(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = (String) js.executeScript("return document.getElementById('PROClaimHeaderField').value");
		Assert.assertTrue(text.contains(expectedPRONum));

	}

	public void verifyClaimAmountIsDisplayed(String expectedClaimAmt) {
		logger.info("Verify claim amount is displayed as expected");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = (String) js.executeScript("return document.getElementById('claimAmountClaimHeaderField').value");
		Assert.assertTrue(text.contains(expectedClaimAmt));

	}

	public void clickOnConfirmButton() {
		logger.info("Click on confirm button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		confirmBtn.click();

	}

}
