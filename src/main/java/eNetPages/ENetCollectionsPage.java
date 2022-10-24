package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class ENetCollectionsPage extends TestBase  {

	private Logger logger = Logger.getLogger(ENetCollectionsPage .class);
	
	@FindBy(xpath= "/html/body/div[4]/div/table/tbody/tr/td")
	private WebElement SummarySection;
	
	@FindBy(xpath= "/html/body/div[5]/div/table/tbody/tr/td")
	private WebElement CollectorSection;
	
	@FindBy(xpath= "/html/body/div[5]/div/div/table/tbody/tr[2]/td[3]/a")
	private WebElement viewIcon;
	
	@FindBy(xpath= "/html/body/div[4]/div[1]/div/div/div/table/tbody/tr[1]/th[2]")
	private WebElement PastDueDollarAmountcolumn;
	
	@FindBy(xpath= "/html/body/div[4]/div[1]/div/div/div/table/tbody/tr[1]/th[3]")
	private WebElement Numbercolumn;
	
	
	/****************************** METHODS **********************/


	public void verifySummarySection() {
		testUtil.init(this);
		testUtil.setHardWait(5000);
		
		SummarySection.isDisplayed();
		logger.info("Summary Section is displayed");
		
	}

	public void verifyCollectorsSection() {
		testUtil.init(this);
		SummarySection.isDisplayed();
		logger.info("Collector Section is displayed");
		
	}

	public void clickOnViewIon() {
		testUtil.init(this);
		logger.info("Click on View Icon");
		viewIcon.click();
	}

	public void verifyNumbercolumn() {
		testUtil.init(this);
		Numbercolumn.isDisplayed();
		logger.info("Number column is displayed");
		
	}

	public void VerifyPastDueDollarAmountcolumn() {
		testUtil.init(this);
		PastDueDollarAmountcolumn.isDisplayed();
		logger.info("Past Due Dollar Amount column is displayed");
		
	}

}
