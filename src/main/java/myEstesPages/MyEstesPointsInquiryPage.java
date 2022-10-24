package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesPointsInquiryPage extends TestBase{
	
	private Logger logger = Logger.getLogger(MyEstesPointsInquiryPage.class);
	
	
	// Transit Time Calculator
			@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/ul/li[5]/a")
			private WebElement weTTimeCal;
			
			
			
			
			
			// Click on Transit Time Calculator Link
			public void clickOnTransitTimeCalculatorLink() {
				logger.info("click on Transit Time Calculato Link");
				testUtil.init(this);
				weTTimeCal.click();
			}
			
			
			
			

}
