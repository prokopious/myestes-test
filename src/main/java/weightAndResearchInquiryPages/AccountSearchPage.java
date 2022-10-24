package weightAndResearchInquiryPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class AccountSearchPage extends TestBase{
	
	private Logger logger = Logger.getLogger(AccountSearchPage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@id='mat-input-3']")
	private WebElement SearchField;
	

	
	public void enterTextSearchField(String text) throws InterruptedException {
		logger.info("enter text to the search field");
		testUtil.init(this);
		SearchField.clear();
		testUtil.setExplicitWait(SearchField, 120);
		SearchField.click();
		SearchField.sendKeys(text);	
		Thread.sleep(1000);
	}
	

	
	public void verifyAccountDetails() {
		testUtil.init(this);
		logger.info("verify Account details");
		String text=driver.findElement(By.xpath("//mat-row[@class='d-flex mat-row ng-star-inserted']")).getText();
		System.out.println(text);
	}
	
	public void verifyNOAccountFound() {
		testUtil.init(this);
		logger.info("verify Account details");
		String text=driver.findElement(By.xpath("//p[@class='ng-star-inserted']")).getText();
		System.out.println(text);
		Assert.assertEquals(text, "No accounts found.");
	}
	
	
	public void clickonAccountNumOnAccountDropDown() {
		testUtil.init(this);
		logger.info("click on account Number");
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//table/tbody[1]/tr[4]/td[1]")).click();
		
	}
	public void validateAccountInformationDetails() {
		testUtil.init(this);
		logger.info("validate all the account information");
		String accountNum=driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/span")).getText();
		System.out.println("Account number is: " + accountNum);
		Assert.assertNotNull(accountNum);
	
		String companyNam=driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]")).getText();
		System.out.println("Company name is: " + companyNam);
		Assert.assertNotNull(accountNum);
		
		String add=driver.findElement(By.xpath("//table/tbody/tr[3]/td[1]/span")).getText();
		System.out.println("Address is: " + add);
		Assert.assertNotNull(add);
	
	}	

}
