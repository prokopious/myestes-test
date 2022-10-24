package rateQuotePages;

import org.openqa.selenium.By;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class RateQouteAccountSearch extends TestBase{
	
	private Logger logger = Logger.getLogger(RateQouteAccountSearch.class);
	
	public void enterAccountNumber() {
		logger.info("Enter Account Number");
		testUtil.init(this);
		driver.findElement(By.xpath("//a[contains(text(),'Account Search')]")).click();	
		driver.findElement(By.xpath("//mat-row[1]//mat-cell[1]")).click();
	}

}
