package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesEditAddressPage extends TestBase{
	
	private Logger logger= Logger.getLogger(MyEstesEditAddressPage.class);
	
	@FindBy(how = How.ID, using = "inputFax")
	private WebElement Fax;
	
	@FindBy(how = How.XPATH, using = " //span[contains(text(),'Save')]")
	private WebElement Save;
	
	public void enterFaxNumber() {
		testUtil.init(this);
		logger.info("Enter fax number ");
		Long number=testUtil.randomNumber();
		String str1 = String.valueOf(number);
		Fax.clear();
		Fax.sendKeys(str1);
	}

	public void validateTitle() {
		logger.info("validate title");
		testUtil.init(this);
		String title1=driver.getTitle();
		String title=driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/address-dialog/mat-card/form/mat-card-header/div[1]/mat-card-title")).getText();
		System.out.println(title);
		System.out.println(title1);
		String expected= "Edit Address";
		Assert.assertEquals(title, expected);
	}
	
	public void clickOnSaveButton() {
		testUtil.init(this);
		Save.click();
		logger.info("click on Save button");
	}
}
