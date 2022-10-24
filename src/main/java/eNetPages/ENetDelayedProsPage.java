
package eNetPages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetDelayedProsPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetDelayedProsPage.class);
	
	/***************************** WEB ELEMENTS *********************/
		
	@FindBy(how = How.XPATH, using = "//*[@id='sqtable']/tbody/tr[2]/td[1]/a")
	private WebElement FirstReasonCode;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[1]")
	private WebElement oTCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[2]")
	private WebElement proCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[3]")
	private WebElement freightBillDateCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[4]")
	private WebElement dateDeleyedCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[5]")
	private WebElement billingTermsCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[6]")
	private WebElement pickUpDateCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[7]")
	private WebElement shipperCodeCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[8]")
	private WebElement shipperNameCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[9]")
	private WebElement consigneeCodeCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[10]")
	private WebElement consigneeNameCol;
		
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[11]")
	private WebElement thirdPartyBillToCodeCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[12]")
	private WebElement thirdPartyNameCol;
	
	@FindBy(xpath = "//*[@id='sqtable']/tbody/tr[1]/th[13]")
	private WebElement lastDeliveryStatusCodeCol;
	
	/****************************** METHODS **************************/
	
	public void verifyDelayedProsPage() {
		String pageTtl = driver.findElement(By.xpath("/html/body/h2")).getText().trim();
		Assert.assertEquals(pageTtl, "Count delayed by reason code".trim(), "Page Title does not match.");
		logger.info("Page is displayed");	
	}

	public void clickOnFirstReasonCode() {
		testUtil.init(this);
		logger.info("click On First Reason Code");
		 FirstReasonCode.click();
	}

	public void verifyReortDetails() {
				
		boolean oTColumn = oTCol.isDisplayed();
		assertTrue(oTColumn);
		testUtil.setHardWait(500);
		logger.info("OT Column is displayed");
		
		boolean proColumn = proCol.isDisplayed();
		assertTrue(proColumn);
		testUtil.setHardWait(500);
		logger.info("Pro Column is displayed");
		
		boolean fBColumn = freightBillDateCol.isDisplayed();
		assertTrue(fBColumn);
		testUtil.setHardWait(500);
		logger.info("Freight Bill Date Column is displayed");
		
		boolean dateDelayColumn = dateDeleyedCol.isDisplayed();
		assertTrue(dateDelayColumn);
		testUtil.setHardWait(500);
		logger.info("Date Delayed Column is displayed");
		
		boolean billTermColumn = billingTermsCol.isDisplayed();
		assertTrue(billTermColumn);
		testUtil.setHardWait(500);
		logger.info("Billing Terms Column is displayed");
		
		boolean pickDateColumn = pickUpDateCol.isDisplayed();
		assertTrue(pickDateColumn);
		testUtil.setHardWait(500);
		logger.info("Pickup Date Column is displayed");
		
		boolean shipCodeColumn = shipperCodeCol.isDisplayed();
		assertTrue(shipCodeColumn);
		testUtil.setHardWait(500);
		logger.info("Shipper Code Column is displayed");
		
		boolean shipNameColumn = shipperNameCol.isDisplayed();
		assertTrue(shipNameColumn);
		testUtil.setHardWait(500);
		logger.info("Shipper Name Column is displayed");
		
		boolean consCodeColumn = consigneeCodeCol.isDisplayed();
		assertTrue(consCodeColumn);
		testUtil.setHardWait(500);
		logger.info("Consignee Code Column is displayed");
		
		boolean consNameColumn = consigneeNameCol.isDisplayed();
		assertTrue(consNameColumn);
		testUtil.setHardWait(500);
		logger.info("Consignee Name Column is displayed");
		
		boolean thirdPartyBillColumn = thirdPartyBillToCodeCol.isDisplayed();
		assertTrue(thirdPartyBillColumn);
		testUtil.setHardWait(500);
		logger.info("Third Party Bill Column is displayed");
	
		boolean partyNameColumn = thirdPartyNameCol.isDisplayed();
		assertTrue(partyNameColumn);
		testUtil.setHardWait(500);
		logger.info("Third Party Name Column is displayed");
		
		boolean deliveryStatusCodeColumn = lastDeliveryStatusCodeCol.isDisplayed();
		assertTrue(deliveryStatusCodeColumn);
		testUtil.setHardWait(500);
		logger.info("Last Delivery Status Code Column is displayed");
	
}
}