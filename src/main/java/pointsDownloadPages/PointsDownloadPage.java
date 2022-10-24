package pointsDownloadPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class PointsDownloadPage extends TestBase{
	
private	Logger logger = Logger.getLogger(PointsDownloadPage.class);
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	private WebElement Submit;
	
	public void clickOnSubmitButton() {
		logger.info("Click on submit button");
		testUtil.init(this);
		Submit.click();	
		testUtil.setHardWait(500);
	}
	
	public void validateErrorMessage() {
		testUtil.init(this);
		logger.info("validate error message");
		String error=driver.findElement(By.xpath("//mat-error[@id='mat-error-0']")).getText();
		System.out.println("The error message is : " + error);
		Assert.assertEquals(error, "This field is required.");
		String error1=driver.findElement(By.xpath("//mat-error[@id='mat-error-1']")).getText();
		Assert.assertEquals(error1, "This field is required.");
		String error2=driver.findElement(By.xpath("//mat-error[@id='mat-error-2']")).getText();
		Assert.assertEquals(error2, "This field is required.");		
	}
	
	
	public void clickSubmit() {
		logger.info("Click on submit button");
		testUtil.init(this);
		WebElement e = driver.findElement(By.xpath("//*[@id='login-component']//div//button[@type='submit']"));
		testUtil.assetWait(null, e, 33, 333, TimeUnit.MILLISECONDS);
		e.click();
		testUtil.setHardWait(5000);
	}
	

}
