package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetClaimsEntryPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetClaimsEntryPage.class);
	
	/*************************************************************Web Elements**************************************************************************/

	@FindBy(how = How.ID, using = "claimNumClaimSearchEnum")
	private WebElement claimNum;
	

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
	private WebElement SearchBtn;
	
	
/*************************************************************Methods**************************************************************************/

	public void enterClaimNumber(String cNum) {
		testUtil.init(this);
		logger.info("Enter claim Number");

	    WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame1);

		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(claimNum);
		claimNum.clear();
		claimNum.sendKeys(cNum);
	}

	public void clickOnSearchButton() {
		logger.info("Click On Search button");
		testUtil.init(this);
		SearchBtn.click();

	}
	
	public void validateTheMessageIsDisplayed(String claimNum) {
		logger.info("Validate the message is displayed as expected");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		String Message = driver
				.findElement(By.xpath("/html[1]/body[1]/span[1]")).getText();
		System.out.println(Message);
		Assert.assertTrue(Message.contains(claimNum));
		testUtil.setHardWait(1000);

	}


}



