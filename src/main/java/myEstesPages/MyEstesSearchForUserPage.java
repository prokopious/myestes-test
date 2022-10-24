package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesSearchForUserPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesSearchForUserPage.class);
	
	// Application access for XXXXX saved 
	@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/span/font/b")
	private WebElement weCNUApplicationAccessMesg;
	
	
	

	// Verify Application Access Text Message
	// Verify Password Change Text Message
			public String getCNUApplicationAccessMesg() {
				testUtil.init(this);
				logger.info("Verify Password Change Text Message");
				String CNUAppAccessMesg = weCNUApplicationAccessMesg.getText();
				return CNUAppAccessMesg;
			}
	
	
}
