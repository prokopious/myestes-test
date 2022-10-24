
package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import testBase.TestBase;

public class ENetCurrentARAgingBalancesPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetCurrentARAgingBalancesPage.class);

	/****************************** METHODS **************************/
	
	public void verifyCurrentARAgingBalancesreport() {
		String pageTtl = driver.findElement(By.xpath("/html/body/h2")).getText().trim();
		Assert.assertEquals(pageTtl, "Current AR Aging Balances (omit G, I, N,P classes)".trim(), "Page Title does not match.");
		logger.info("Current AR Aging Balances Page is displayed");
		
	}
	
}