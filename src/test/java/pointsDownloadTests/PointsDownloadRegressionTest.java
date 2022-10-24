package pointsDownloadTests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesWelcomePage;
import pointsDownloadPages.PointsDownloadPage;
import testBase.TestBase;


public class PointsDownloadRegressionTest extends TestBase{
	
	Logger logger = Logger.getLogger(PointsDownloadRegressionTest.class);
	
	
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage=new MyEstesLoginPage();
	PointsDownloadPage pointsDownloadPage=new PointsDownloadPage();
	
	/******************************* TESTS *******************************/
	
	/**
	 * Test passed on 7/25/22
	 * 
	 * Points Download - Authenticated - Error message should be displayed when
	 * required fields left blank in Points Download screen.
	 */
	
	
	@Test(enabled = true, priority = 1)
	
	public void executeQZ_7150() throws InterruptedException {
		
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(password1);
		testUtil.setHardWait(1000);
		pointsDownloadPage.clickSubmit();
		
		myEstesWelcomePage.clickShip();
		myEstesWelcomePage.selectPointsDownload();
		testUtil.setHardWait(2000);
		pointsDownloadPage.clickOnSubmitButton();
		pointsDownloadPage.validateErrorMessage();
	}
	
	
	
	
	
	
	
	
	

}
