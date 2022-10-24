/**
 * 
 */
package shipmentTrackingPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.TestBase;

public class EstesHomeTrackingPage extends TestBase{

private Logger logger = LogManager.getLogger(EstesHomeTrackingPage.class);
	
	@FindBy(xpath="(//a[(contains(text(), 'Track'))])[1]")
	private WebElement trackOnNavigationBar; 
	
	@FindBy(xpath="//a[(contains(text(), 'Shipment Tracking'))]")
	private WebElement clickOnShipmentTracking; 

	
	
	
	public void clickTrackOnNavBar() {
		testUtil.init(this); 
		logger.info("Click on Shipment Tracking in Track nav bar.");
		trackOnNavigationBar.click(); 
		testUtil.setHardWait(500);
	}
	
	public void clickOnShipmentTracking() {
		testUtil.init(this); 
		clickOnShipmentTracking.click(); 
		testUtil.setHardWait(500);
	}

}
