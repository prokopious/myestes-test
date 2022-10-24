package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesRatesPage extends TestBase {
	
	//private Logger logger = Logger.getLogger(MyEstesRatesPage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/fieldset[2]/ul/li/a")
	private WebElement VTLRateQuote;
	// Time Critical Rate Quote
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/fieldset[1]/ul/li[2]/a")
	private WebElement weTimeCriticalRate;
	
	
	public void clickOnLTLRateQuoteLink() {
		testUtil.init(this);
		VTLRateQuote.click();
	}	
	//Time Critical Rate Link
	public void clickOnTimeCriticalLink() {
		testUtil.init(this);
		weTimeCriticalRate.click();
	}	
	

}
