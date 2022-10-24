package eNetPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class ENetStatementRetrieverPage extends TestBase {
	
	private Logger logger = Logger.getLogger(ENetHomePage.class);
	
	@FindBy(how = How.ID, using = "instructions")
	private WebElement PageTitle;
	
	@FindBy(how = How.ID, using = "statementNumber")
	private WebElement StatementNumber;
	
	@FindBy(how = How.XPATH, using = "//input[@value='VIEW']")
	private  WebElement ViewRadioBtn;
	
	@FindBy(how = How.ID, using = "formSubmitButton")
	private WebElement SubmitButton;
	
	
	/*************************************************************************/
	
	public void validatePageTitle(String expTitle) {
		testUtil.init(this);
		testUtil.switchToFrame("mainpage");
		String title =PageTitle.getText();
		Assert.assertTrue(title.contains(expTitle));
		logger.info("Page Title is "+title);
	}
	
	public void selectViewRadioBtn() {
		testUtil.init(this);
		if(ViewRadioBtn.isSelected())
			logger.info("View radio button is selected in default");
		else
			testUtil.clickElementJavascript(ViewRadioBtn);
			
		logger.info("View radio button is selected ");
		
	}
	
	public void enterstatementNumber(String number) {
		testUtil.init(this);
		StatementNumber.sendKeys(number);
		logger.info("Entered StatementNumber "+number);
	}
	
	public void clickOnSubmitBtn() {
		logger.info("Clicked on Submit Button");
		testUtil.init(this);
		testUtil.assetWaitClickable(null, SubmitButton, 10, 250, TimeUnit.MILLISECONDS);
		SubmitButton.click();
	
	}
	
	public void clickOnLogout() {
		logger.info("Logging out from Enet");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		WebElement ele = driver.findElement(By.xpath("//a[@id='logout']"));
		testUtil.WaitForTheElementClickable(ele);
		ele.click();
		testUtil.setHardWait(1000);

	}

	public void clickOnLogoutIntranet() {
		logger.info("Logging out from Intranet");
		testUtil.init(this);
		driver.switchTo().frame(0);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Logout']")));
		ele.click();
	}
	

}
