package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesAddressBookAddEditDeletePage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesAddressBookAddEditDeletePage.class);
	
// Add Button
	@FindBy(how = How.ID, using = "add" )
	private WebElement weAddButton;
	// Verify Address
	@FindBy(how = How.XPATH, using = "//*[@id='inputForm']/table[2]/tbody/tr[2]/td[2]" )
	private WebElement weVerifyAddress;
	
	//////
	// Click On Add Button
	public void clickOnAddButton ()
	{
		logger.info("Click On Address Book Add Button");
		testUtil.init(this);
		weAddButton.click();		
	}
	//Verify Saved Address
		public void verifySavedAddressDisplay ()
		{
			logger.info("Verify Saved Address");
			testUtil.init(this);
			weVerifyAddress.isDisplayed();		
		}
	
}
