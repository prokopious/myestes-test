 package myEstesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesCommodityLibraryPage extends TestBase {

private Logger logger = Logger.getLogger(MyEstesCommodityLibraryPage.class);

	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Add Commodity')]")
	private WebElement Add;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-sort-header-container mat-sort-header-sorted']//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition']")
	private WebElement sortOrderArrow;
	
	@FindBy(how = How.XPATH, using = "//*[@title='Delete Commodity']/span/mat-icon")
	private WebElement deleteCommodity;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Confirm')]")
	private WebElement confirmDelete;
	
	@FindBy(how = How.XPATH, using = "//lib-snackbar-message[contains(@class,'ng-star-inserted')]")
	private WebElement message;
	
	
	
	public void clickOnAddCommodity() {
		logger.info("Click on add commodity");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,-250)");
		testUtil.clickElementJavascript(Add);
		
		

	}
	
	public void clickSortArrow() {
		logger.info("Click on sort arrow");
		testUtil.init(this);
		sortOrderArrow.click();

	}
	
	public void clickConfirmButton() {
		logger.info("Click on confirm button");
		testUtil.init(this);
		testUtil.clickElementJavascript(confirmDelete);
		//confirmDelete.click();

	}
	
	public void clickOnDeleteCommodity() {
		logger.info("Click on delete Commodity button");
		testUtil.init(this);
		deleteCommodity.click();

	}
	
	public void clickDeleteCommodity(String data) {
		logger.info("Click on delete Commodity button");
		testUtil.init(this);
		driver.findElement(By.id("filter")).sendKeys(data);//Added code to search for particular data and then delete
		WebElement we = driver.findElement(
				By.xpath("//tr[td[text()='"+data+"']]/td/div/button[2]"));
		we.click();

	}
	
	public void clickEditCommodity() {
		logger.info("Click on delete Commodity button");
		testUtil.init(this);
		WebElement we = driver.findElement(
				By.xpath("//*[@class='mat-table']/tbody//tr[1]//td[8]//div[1]//button[1]"));
		we.click();

	}
	
	public void verifySuccessMessage(String expectedMessage) {
		logger.info("verify success message displayed");
		String actual = message.getText().trim();
		assertEquals(actual, expectedMessage);
	}

	public void validateRecordAddedtoCommodityLibraray(String prID) {
		testUtil.init(this);
		logger.info("Validate added commodity ");
		List<WebElement> we = driver.findElements(
				By.xpath("//*[@id=\"content\"]/div/div[1]/app-commodity-list/mat-card/table/tbody/tr[*]/td[1]"));

		int listSize = we.size();
		for (int i = 0; i < listSize; i++) {
			String id = we.get(i).getText();
	//		System.out.println(we.get(i).getText());

			if (id.contains(prID)) {

				System.out.println("Added Commodity Library is exist and validated");
				
				break;
			}else{
				
			System.out.println("Not Exist!!!");}
			
			
			
			
		}
		
			
	}
	
	public void verifyRecordDeletedSuccessfully(String prID) {
		testUtil.init(this);
		logger.info("Validate delete commodity is successful");

		List<WebElement> we = driver.findElements(By.xpath("//table[@class='mat-table']/tbody/tr/td[1]"));
		for (WebElement e : we) {
			if (e.getText().trim().equals(prID)) {
				Assert.assertTrue(false);
			}
		}
	}
	

	
	
	
	
	
	
	
	
}
