package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.TestBase;

public class MyEstesToolsPage extends TestBase {

private Logger logger = Logger.getLogger(MyEstesToolsPage.class);
	
	@FindBy(xpath="//h3[contains(text(),'PC Rater Download')]")
	private WebElement pcRaterDownload;
	
	/*******************METHODS***************************/

	public void clickOnPCRaterDownload() {
		logger.info("Click on PC Rater download link ");
		testUtil.init(this);
		testUtil.setExplicitWait(pcRaterDownload, 20);
		testUtil.clickElementJavascript(pcRaterDownload);
	}
	
	
}
