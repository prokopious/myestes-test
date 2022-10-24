package fuelSurchargePages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class FeeAndSurchargesPage extends TestBase{
	
  private Logger logger = Logger.getLogger(FeeAndSurchargesPage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@id='main-1']/div/div/div/ul/li[3]/a/div/h3[contains(text(),'Fuel Surcharge')]")
	private WebElement FuelSurcharge;
	
	/*****************************METHODS***********************************/
	public void clickOnFuelSurcharge() {
		logger.info("Click on Fees and Surcharges");
        testUtil.init(this);
        
       // WebElement feesAndSurcharges = testUtil.filterSelector("a", null, null, 0, "Fees and Surcharges");
        testUtil.clickElementJavascript(FuelSurcharge);
	}

}

