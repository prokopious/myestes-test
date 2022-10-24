package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesEDIPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesEDIPage.class);

	//Estes EDI Implementation Request
	@FindBy(how = How.XPATH, using = "//a[@routerlink='/implementation-request']")
	private WebElement weEDIImplRequest;
	



	//Estes EDI Implementation Request
	public void clickOnEDIImplementationRequest() {
		testUtil.init(this);
		logger.info("click on Estes EDI Implementation Request");
		testUtil.setExplicitWait(weEDIImplRequest, 20);
		testUtil.clickElementJavascript(weEDIImplRequest);
		//weEDIImplRequest.click();
	}
	


}
