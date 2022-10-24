package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.TestBase;

public class MyEstesPCRaterDownloadPage extends TestBase{
	
  private Logger logger = Logger.getLogger(MyEstesPCRaterDownloadPage.class);

	@FindBy(css="[class*='fas fa-question']")
	private WebElement accontNumberQuestionMark; 
	
	@FindBy(xpath="//a[contains(text(),'click here')]")
	private WebElement clickHere;
	
	/*****************METHODS****************************/
	 
	public void clickOnAccountNumberQuestionMarkIcon() {
		logger.info("Click on Account number question mark icon ");
		testUtil.init(this);
		testUtil.setExplicitWait(accontNumberQuestionMark, 20);
	//	accontNumberQuestionMark.click();
		testUtil.clickElementJavascript(accontNumberQuestionMark);
		testUtil.setHardWait(500);
	}
	
	public void clickOnAccountNumberClickHereLink() {
		logger.info("Click on Account number Click Here link");
		testUtil.init(this);
		testUtil.setExplicitWait(clickHere, 60);
		testUtil.clickElementJavascript(clickHere);
	}
}


