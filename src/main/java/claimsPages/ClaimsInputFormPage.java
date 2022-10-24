package claimsPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;


public class ClaimsInputFormPage extends TestBase{
	
	private Logger logger = Logger.getLogger(ClaimsInputFormPage.class);
	
									/**********************/
	@FindBy(how=How.ID, using="accountNumber")
	private WebElement AccountNumber;
	
									/**********************/
	public void selectAccountNumber() {
		logger.info("Click on account number");
		testUtil.init(this);
		testUtil.selectUsingValue(AccountNumber, "");
	}

}
