package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesDigitalServicesPage extends TestBase{
	
	private Logger logger = Logger.getLogger(MyEstesDigitalServicesPage.class);
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'EDI')]")
	private WebElement weEDI; 
	
	/*******************METHODs************************/
	public void clickOnEDI() {
		logger.info("Click on EDI");
		testUtil.init(this);
		weEDI.click();
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
