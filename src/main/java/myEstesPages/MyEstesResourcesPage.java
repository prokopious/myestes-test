package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesResourcesPage extends TestBase{
	
	private Logger logger = Logger.getLogger(MyEstesResourcesPage.class);

	@FindBy(how = How.CSS, using = "div:nth-child(1) div.Left_Nav:nth-child(3) ul:nth-child(1) li.Left_Subnav:nth-child(8) > a:nth-child(1)")
	private WebElement TerminalList;
	// Canadian Currency Conversion
	@FindBy(how = How.XPATH, using = "//*[@id=\"Body_Content\"]/table/tbody/tr[3]/td[1]/a")
	private WebElement weCCurConversionTerminalList;
	
	
	public void clickOnTerminalLink() {
		testUtil.init(this);
		logger.info("Click on Terminal list");
		TerminalList.click();
	}

	// Click on Canadian Currency Conversion
	public void clickOnCCConversionLink() {
		testUtil.init(this);
		logger.info("Click on Canadian Currency Conversion link");
		weCCurConversionTerminalList.click();
	}
	
	}
