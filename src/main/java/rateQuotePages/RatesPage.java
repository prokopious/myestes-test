package rateQuotePages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class RatesPage extends TestBase {
	private Logger logger = Logger.getLogger(RatesPage.class);

	@FindBy(how = How.XPATH, using = "//div[@id='Body_Content']//fieldset[1]//ul[1]//li[1]//a[1]")
	private WebElement LTLRateRequest;
	// Volume/Truckload Rate Quote
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/fieldset[2]/ul/li/a")
	private WebElement weVTLRQuote;
	// Time Critical Rate Quote
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/fieldset[1]/ul/li[2]/a")
	private WebElement weTCritical;

	public void clcikOnLTLRateRequest() {
		testUtil.init(this);
		LTLRateRequest.click();
	}

	// Click on Volume/Truckload Rate Quote Link
	public void clickOnVTLRateQuote() {
		testUtil.init(this);
		weVTLRQuote.click();
	}

	// Click on Time Critical Link
	public void clickOnTimeCriticalLink() {
		testUtil.init(this);
		weTCritical.click();
	}

}
