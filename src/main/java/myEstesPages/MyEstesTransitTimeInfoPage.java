package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesTransitTimeInfoPage extends TestBase{

	private Logger logger = Logger.getLogger(MyEstesTransitTimeInfoPage.class);
	
	// Origin Supporting Terminal
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/table/tbody/tr[4]/td[1]/u")
	private WebElement weOriginSTerminal;
	
	//Destination Supporting Terminal
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/table/tbody/tr[4]/td[2]/u")
	private WebElement weDesSTerminal;
	
	//Verify Origin Supporting Terminal
	public void verifyOriginSTer() {
		testUtil.init(this);
		weOriginSTerminal.isDisplayed();
	}
	//Verify Destination Supporting Terminal
	public void verifyDesSTer() {
		testUtil.init(this);
		weDesSTerminal.isDisplayed();
	}
	
	
	
}
