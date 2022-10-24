package eNetPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class ENetReportsPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetReportsPage.class);
	
	@FindBy(css = "[class='menuHeaderText']")
	private List<WebElement> reportsHeader;
	
	@FindBy(id="mainpage")
	private WebElement frameElement;
	
	
	/*********************************************/
	
	public void switchToFrameElement() {
		logger.info("Switch to VTL Floor Mins frame Element");
		testUtil.init(this);
		driver.switchTo().frame(frameElement);
	}
	
	public void verifyReportsHeader(String[] rHeader) {
		logger.info("Verify Reports Header");
		testUtil.init(this);
		if(reportsHeader.size() > 0) {
			for(int i=0;i<reportsHeader.size();i++) {
				String headerText = reportsHeader.get(i).getText();
				Assert.assertEquals(headerText, rHeader[i]);
			}
		} else {
			try {
				throw new Exception(new String("Failed to display reports header"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}



















































