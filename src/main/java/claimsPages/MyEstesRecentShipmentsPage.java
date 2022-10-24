package claimsPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesRecentShipmentsPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesRecentShipmentsPage.class);


	// Recent Shipments for Text
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Recent Shipments')])[2]")
	private WebElement weRecentShipmentsText;
	// PRO Number for First Row
	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/tbody/tr[1]/td[1]")
	private WebElement wePRONumForFirstRow;
	//  PRO Number Ascending Sign
	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/thead/tr/th[1]/div/div")
	private WebElement wePRONumAscendingSign;
	// Pop Up Shipper Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Shipper')])[2]")
	private WebElement wePopUpShipperRadioButton;
	// Pop Up Consignee Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Consignee')])[1]")
	private WebElement wePopUpConsigneeRadioButton;
	// Pop Up Third Party Payor Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Third Party Payor')])[2]")
	private WebElement wePopUpThirdPPayorRadioButton;
	//Recent Shipments Shipper Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Shipper')])[1]")
	private WebElement weRSShipperRadioButton;
	//Recent Shipments Consignee Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Consignee')])[1]")
	private WebElement weRSConsigneeRadioButton;
	//Recent Shipments Third Party Payor Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Third Party Payor')])[1]")
	private WebElement weRSThirdPPayorRadioButton;
	// Set Viewing Preference
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Set Viewing Preference')]")
	private WebElement weSetViewingPreference;	




	////
	// Verify Recent Shipments for Text Displayed
	public void verifyRecentShipmentsTextDisplayed() {
		testUtil.init(this);
		logger.info("Verify Recent Shipments Text Displayed");
		weRecentShipmentsText.isDisplayed();
	}
	// Get PRO Number for First Row
	public String getPRONumForFirstRow() {
		testUtil.init(this);
		logger.info("Get PRO Number for First Row");
		String PRONumber = wePRONumForFirstRow.getText();
		return PRONumber;
	}
	// Click On PRO Number Ascending Sign
	public void clickOnPRONumAscendingSign () {
		logger.info("Click On PRO Number Ascending Sign");
		testUtil.init(this);
		wePRONumAscendingSign.click();		
	}
	// Click On First PRO Number From First Row
	public void clickOnFirstPRONumFFirstRow () {
		logger.info("Click On First PRO Number From First Row");
		testUtil.init(this);
		wePRONumForFirstRow.click();		
	}
	// Click On Pop Up Shipper Radio Button
	public void clickOnPopUpShipperRadioButton () {
		logger.info("Click On Pop Up Shipper Radio Button");
		testUtil.init(this);
		wePopUpShipperRadioButton.click();		
	}
	// Verify Resent Shipments Shipper Radio Button Displayed
	public void verifyShipperRadioButtonDisplayed () {
		logger.info("Verify Recent Shipments Shipper Radio Button Displayed");
		testUtil.init(this);
		weRSShipperRadioButton.isDisplayed();		
	}
	// Click On Resent Shipments Shipper Radio Button 
		public void ClickOnRSShipperRadioButton () {
			logger.info("Click On Recent Shipments Shipper Radio Button");
			testUtil.init(this);
			weRSShipperRadioButton.click();		
		}
	// Click On Pop Up Consignee Radio Button
	public void clickOnPopUpConsigneeRadioButton () {
		logger.info("Click On Pop Up Consignee Radio Button");
		testUtil.init(this);
		wePopUpConsigneeRadioButton.click();		
	}
	// Verify Resent Shipments Consignee Radio Button Displayed
	public void verifyRSConsigneeRadioButtonDisplayed () {
		logger.info("Verify Resent Shipments Consignee Radio Button Displayed");
		testUtil.init(this);
		weRSConsigneeRadioButton.isDisplayed();		
	}
	// Click On Pop Up Third Party Payor Radio Button
	public void clickOnPopUpThirdPPayorRadioButton () {
		logger.info("Click On Pop Up Third Party Payor Radio Button");
		testUtil.init(this);
		wePopUpThirdPPayorRadioButton.click();		
	}
	// Verify Resent Shipments Third Party Payor Radio Button Displayed
	public void verifyRSThirdPPayorRadioButtonDisplayed () {
		logger.info("Verify Resent Shipments Third Party Payor Radio Button Displayed");
		testUtil.init(this);
		weRSThirdPPayorRadioButton.isDisplayed();		
	}
	// Click On Click On Set Viewing Preference
	public void clickOnSetViewingPreference () {
		logger.info("Click On Set Viewing Preference");
		testUtil.init(this);
		weSetViewingPreference.click();		
	}



}
