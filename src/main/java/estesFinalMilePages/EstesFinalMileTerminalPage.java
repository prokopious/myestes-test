/**
 * 
 */
package estesFinalMilePages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;

/**
 * @author habibja
 *
 */
public class EstesFinalMileTerminalPage extends TestBase {
	private Logger logger = Logger.getLogger(EstesFinalMileTerminalPage.class);

	@FindBy(xpath = "//div[@class = 'pageTitle']/span")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[@id='menu-edi']/div/a")
	private WebElement specialHandling;
	
	/******************************METHODS****************************/
	
	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify Page title.");
		String page = pageTitle.getText();
		logger.info("Page title : "+page);
		Assert.assertEquals(page, "Terminal", "Page title doesnot match.");
	}
	
	public void clickOnSpecialHandlingLink() {
		testUtil.init(this);
		logger.info("click on SpecialHandling Link");
		specialHandling.click();
	}
}


