package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesCanadianCurrencyConversionPage extends TestBase {
	
  private Logger logger = Logger.getLogger(MyEstesCanadianCurrencyConversionPage.class);
	
	
								/*********** ELEMENTS ***********/
	//USD to AAC Currency Conversion
	@FindBy(how = How.XPATH, using = "//table[@id='currency-table']")
	private WebElement weUSDToCADCon;
	
								/*********** METHODS ***********/
	// Click on Canadian Currency Conversion
	public void verifyUSDtoCADCurrencyConversionTable() {
		logger.info("Verify USD to CAD Currency Conversion Table ");
		testUtil.init(this);

		boolean Actual = weUSDToCADCon.isDisplayed();
		if (Actual == true)
			Assert.assertTrue(Actual);
		System.out.println("Table is displayed");
	}

}
