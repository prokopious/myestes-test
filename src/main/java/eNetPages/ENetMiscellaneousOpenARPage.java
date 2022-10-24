package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import testBase.TestBase;

public class ENetMiscellaneousOpenARPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetMiscellaneousOpenARPage.class);

	/****************************** METHODS **************************/
	
	public void verifyMiscellaneousOpenARPage() {
		String pageTtl = driver.findElement(By.xpath("/html/body/h2")).getText().trim();
		Assert.assertEquals(pageTtl, "Misc. Open A/R - company".trim(), "Page Title does not match.");
		logger.info("Misc. Open A/R Page is displayed");
		
	}
	
}

