package solutionsPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.TestBase;




public class MyEstesSolutionsPage extends TestBase {
	
	Logger logger = Logger.getLogger(MyEstesSolutionsPage.class);

	@FindBy(xpath="//h3[text()='Estes Final Mile']")
	private WebElement estesFinalMileText;
	
	@FindBy(xpath="//a[text()='Final Mile']")
	private WebElement finalMileLink;
	
	/************************************************/
	
	public void clickOnEstesFinalMileLink() {
		logger.info("Click on Estes Final Mile link");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(estesFinalMileText);
		//estesFinalMileText.click();
	}
	
	public void clickOnFinalMileLink() {
		logger.info("Click on Residential link");
		testUtil.init(this);
		testUtil.setExplicitWait(finalMileLink, 20);
		finalMileLink.click();
	}
}

	

