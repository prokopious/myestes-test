package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesAddressBookHomePage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesAddressBookHomePage.class);

	// Add/Edit/Delete
	@FindBy(how = How.XPATH, using = "//*[@id='menuForm']/table/tbody/tr[3]/td/table/tbody/tr[1]/td[1]/a")
	private WebElement ClickOnAddEditDelete;

	public void clickOnAddEditDelete() {
		logger.info("Click On Add/Edit/Delete");
		testUtil.init(this);
		ClickOnAddEditDelete.click();
	}

}
