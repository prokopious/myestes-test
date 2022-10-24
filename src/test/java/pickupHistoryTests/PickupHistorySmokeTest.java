package pickupHistoryTests;

import org.testng.annotations.Test;

import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesPickupRequestPage;
import myEstesPages.MyEstesPickupVisibility;

import java.text.ParseException;

import testBase.TestBase;

	public class PickupHistorySmokeTest extends TestBase {
	
	  MyEstesHomePage myEstesHomePage 	= new MyEstesHomePage();
	  MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	  MyEstesPickupRequestPage myEstesPickupRequestPage = new MyEstesPickupRequestPage();
	  MyEstesPickupVisibility myEstesPickupVisibility = new MyEstesPickupVisibility();
	
	  /******************************* TESTS *******************************/
	 
	/*
	 * Pickup History - Verify the user can view pickup request details by clicking
	 * on Request Number link.
	 */
	  
	  @Test(enabled = true, priority = 1) 
	  
	  public void executeQZ_7552() throws InterruptedException {
		  
		  //Launch application
		  myEstesHomePage.clickOnMyEstes();
		  
		  //Login to application
		  myEstesHomePage.clickOnLogin();
		  myEstesLoginPage.enterUserName(username1);
		  myEstesLoginPage.enterPassword(password1);
		  testUtil.setHardWait(3000);
		  myEstesLoginPage.clickOnLoginButton();
		  
		  //Navigate Shipment tab -> Pickup request
		  myEstesHomePage.clickOnShipTab();
		  myEstesHomePage.clickOnPickupRequest();	
		  testUtil.setHardWait(2000);
		  //Navigate to View Pickup request tab
		  myEstesPickupRequestPage.clickOnViewPickupHistory();
		  
		  //Click on any request number link
		  String selectedReqNum = myEstesPickupRequestPage.capturAndSelectFirstRequestNo();
		  
		  //Verify Pick details dialog displayed
		 // myEstesPickupRequestPage.verifyPickUpDetails(selectedReqNum);
		  
		  testUtil.setHardWait(2000);
		  myEstesPickupVisibility.verifyResultNumber(selectedReqNum);
		  
	  }
	  
	  
	  
	  
}  
