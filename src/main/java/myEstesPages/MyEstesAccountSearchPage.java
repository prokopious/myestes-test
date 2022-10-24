package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesAccountSearchPage extends TestBase{
	
	private Logger logger = Logger.getLogger(MyEstesAccountSearchPage.class);
    
    @FindBy(how = How.XPATH, using = "//mat-card[1]/mat-card-header[1]/div[2]/div[1]/div[1]/div[1]/input[1]")
    private WebElement AccountSearchField;
    
    @FindBy(how = How.XPATH, using = "(//a[@class='ng-star-inserted'])[6]")
    private WebElement AccountNumbers;
    
    /*******************************************************/

	public void enterSearchValue(String serchValue) throws Exception {
		logger.info("Enter Search Value " + serchValue);
		testUtil.setHardWait(2000);
		// testUtil.switchToWindow(1);
		testUtil.highlightElement(By.xpath("//mat-card[1]/mat-card-header[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
		AccountSearchField.click();

		// AccountSearchField.clear();
		testUtil.setHardWait(1000);
		AccountSearchField.sendKeys(serchValue);

		/*
		 * driver.switchTo().activeElement().click(); testUtil.setHardWait(2000);
		 * driver.switchTo().activeElement().sendKeys(serchValue);
		 * testUtil.setHardWait(2000);
		 */
	}

	public void validateSearchResult(String result) {

		logger.info("validate Search result is ");

		String resultText = driver.findElement(By.xpath("//body//mat-row[1]")).getText();
		System.out.println("Search result is : " + resultText);
		testUtil.setHardWait(2000);
		Assert.assertEquals(resultText, resultText.contains(result));
	}

	public void clickOnAccountNumber() {
		logger.info("Click On Account Number");
		testUtil.init(this);
		testUtil.clickElementJavascript(AccountNumbers);
	}

}

	