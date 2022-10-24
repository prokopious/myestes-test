package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class ENetLTLQuoteHistoryDetailPage extends TestBase {
	
	private Logger logger = Logger.getLogger(ENetQuoteHistoryLookupPage.class);

	/********************************************************************/

	@FindBy(id = "rate_quote_history_quote_number")
	private WebElement quoteNumber;

	/********************************************************************/

	public void validatePagetitle(String expectedTitle) {
		logger.info("Validate page title");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainpage");
		String title = driver.findElement(By.xpath("//h1[text()='LTL Quote History Detail']")).getText();
		System.out.println(title);
		Assert.assertTrue(title.contains(expectedTitle));
	}

	public String getServiceInfo(String name) {
		logger.info("Get Value of " + name + " ");
		testUtil.init(this);
		String value = driver
				.findElement(By.xpath("//*[@class='colOne']/descendant::*[text()='" + name + "']/following::div[1]"))
				.getText();
		System.out.println("Service information is: " + value);
		return value;
	}

	public String getRoutingInfo(String name) {
		logger.info("Get Value of " + name + " ");
		testUtil.init(this);
		String text = driver
				.findElement(By.xpath("//*[@class='colTwo']/descendant::*[text()='" + name + "']/following::div[1]"))
				.getText();
		
		return text;
	}

	public String getFeeSummaryValueOf(String name) {
		logger.info("Get Value of " + name + " ");
		testUtil.init(this);
		List<WebElement> headers = driver
				.findElements(By.xpath("//*[@id='fee_summary_container_vtl']/table/thead/tr/th"));
		String val = null;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).getText().equalsIgnoreCase(name)) {
				val = driver
						.findElement(
								By.xpath("//*[@id='fee_summary_container_vtl']/table/tbody/tr/td[" + (i + 1) + "]"))
						.getText();
				break;
			}
		}
		return val;
	}

	public String getChargeItems(String name) {
		logger.info("Get Value of " + name + " ");
		testUtil.init(this);
		String text = driver
				.findElement(By.xpath("//*[@id='charge_items']/descendant::*[contains(text(),'" + name + "')]/following::td[1]"))
				.getText();
		return text;
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


