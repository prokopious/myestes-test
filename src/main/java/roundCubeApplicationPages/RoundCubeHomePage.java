package roundCubeApplicationPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class RoundCubeHomePage extends TestBase {
	
	private Logger logger = Logger.getLogger(RoundCubeHomePage.class);

	/**************************** ELEMENTS ****************************/
		
	@FindBy(xpath = "//*[@id=’message-part1’]/div")
	private WebElement messageBody;
	
	@FindBy(xpath = "//a[@class='button-logout']")
	private WebElement logOutBtn;
	
	@FindBy(xpath = "//span[contains(text(),'noreply@estes-express.com')]")
//	@FindBy(xpath= "//td[@class='header from']//span//a[contains(@href,'mailto')]")
	private WebElement from;
	
	@FindBy(xpath = "//font[contains(text(),'Service Level: Estes Truckload')]")
	private WebElement serviceLevel;
	
	@FindBy(xpath = "//tbody/tr[1]/td[1]/br[1]")
	private WebElement type;
	
//	@FindBy(xpath = "//div[@class='message-htmlpart']//div")sss
	@FindBy(xpath = "//*[@id='message-htmlpart1']")
	private WebElement emailBody;
	
	@FindBy(tagName= "//iframe")
	private List<WebElement>noOfIframe;

	/**************************** WEB ELEMENTS ***********************/
	
	public void verifyEmailContent(String expText) {
		logger.info("Verifying Email Content");
		testUtil.init(this);
		String actualText = messageBody.getText();
		System.out.println(actualText);
		System.out.println(expText);
		Assert.assertTrue(actualText.contains(expText));
		
	}
	
	public void clickOnLogoutBtn() {
		logger.info("Click on Logout Button");
		testUtil.init(this);
		logOutBtn.click();
	}

	public void verifySubjectLine(String subLine, String startTime, String endTime) {
		logger.info("Verify Subject Line");
		testUtil.init(this);
		List<WebElement> ele = driver.findElements(By.xpath("//span[contains(text(),'"+subLine+"')]//parent::a//ancestor::tr"));
		
		for(int i =0; i<ele.size(); i++) {
			String idValue = ele.get(i).getAttribute("id");
			testUtil.assetWait("//span[contains(text(),'"+subLine+"')]//parent::a//ancestor::tr[@id='"+idValue+"']//td[@class='date']", null, 10, 200, TimeUnit.MILLISECONDS);
			String receivedTime = driver.findElement(By.xpath("//span[contains(text(),'"+subLine+"')]//parent::a//ancestor::tr[@id='"+idValue+"']//td[@class='date']")).getText();
			if(receivedTime.contains(startTime)|| receivedTime.contains(endTime)) {
				
				System.out.println(subLine+" Email received successfully...");
			//	ele.get(i).click();
				testUtil.setHardWait(1000);
				ele.get(i).click();
				break;
			} else {
				System.out.println("Iterating next Ele");
			}
		}
	}

	public void validateFromEmail(String email) {
		logger.info("Verifying Email Content");
		testUtil.init(this);

		String actualText = from.getText();
		Assert.assertTrue(actualText.contains(email));
		
		
	}
	
	public void validateEmailContent(String expText) {
		logger.info("Verifying Email Content");
		testUtil.init(this);
		String actualText = emailBody.getText();
		System.out.println("actualText : "+actualText);
		System.out.println("expText : "+expText);
		Assert.assertTrue(actualText.contains(expText));
		
	}
	
	public void switchIframe() {
		logger.info("Switch to iframe");
		testUtil.init(this);
		System.out.println("the number of iframe is:" + noOfIframe.size());
		if(noOfIframe.size() > 0) {
			testUtil.switchToFrame(0);
		}
	}
	
}
