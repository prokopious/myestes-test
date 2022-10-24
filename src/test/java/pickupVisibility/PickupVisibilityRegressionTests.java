package pickupVisibility;

import testBase.TestBase;

	import java.util.Set;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import jagacy.util.JagacyUtil;
	import myEstesPages.MyEstesHomePage;
	import myEstesPages.MyEstesLoginPage;
	import myEstesPages.MyEstesPickupVisibility;
	import shipmentTrackingPages.ShipmentTrackingPage;


	
	public class PickupVisibilityRegressionTests extends TestBase {
		
		MyEstesHomePage myEstesHomePage = new MyEstesHomePage(); 
		MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage(); 
		MyEstesPickupVisibility myEstesPickupVisibility = new MyEstesPickupVisibility(); 
		ShipmentTrackingPage shipmentTrackingPage = new ShipmentTrackingPage(); 
		
		/******************************* TESTS *******************************/
		
		/*
		 * Pickup Visibility - Auth - Verify error message should be displayed on invalid pickup request data and can be tracked via the Shipment Tracking app
		 */
		
		@Test(enabled = true, priority = 1)

		public void executeQZ_12009() throws InterruptedException {
			
			String pickupRequestNumber = "PUR024156";
			String errorSinglePO = "Are you trying to track a shipment? Pickup Request number "+pickupRequestNumber+" not found.";
			String shipmentTrackingMessageForSinglePO = "Not found or tracking information unavailable: "+pickupRequestNumber+".";  
			String errorMultiplePO = "Are you trying to track a shipment? Pickup Request numbers PUR027134, PUR037135, PUR047136, and 3 others not found.";
			String shipmentTrackingMessageForMultPO = "Not found or tracking information unavailable: PUR027134, PUR037135, PUR047136, PUR057137, PUR067138, PUR077139."; 
			
			//Step 1: Access the following URL for My Estes: <<MyEstes QA URL>>QZ-9814
			//Step 2: Login to My Estes application with the following credentials: username: smokenat - password: smokenat
			myEstesHomePage.clickOnMyEstes();
			myEstesHomePage.clickOnLogin();
			myEstesLoginPage.enterUnameAndpwd(username1, password1);
			myEstesLoginPage.clickonSubmitButton(); 
			
			/*Step 3: From My Estes home page hover mouse over the following menu: Track
			 * Step 4: From Track menu, click on Pickup Visibility
			 * Step 5: From Track page navigate to the following application: <Pickup Visibility>
			 */
			myEstesHomePage.clickPickupVisibility(); 
			
			/*Step 6: From search box, enter the following invalid data: PUR024156
			 * Step 7: Click on Search button
			 * Validate the following error message:
			 * Are you trying to *track a shipment?* Pickup Request number PUR024156 not found.
			 */
			myEstesPickupVisibility.enterPickupRequestNumbers1(pickupRequestNumber);
			myEstesPickupVisibility.clickSearch();
			myEstesPickupVisibility.validateShipmentNotFoundMessage(errorSinglePO); 
			String mainWindow = driver.getWindowHandle(); 
			
			//Step 8: Click on the *track a shipment?* link in the error message -> A new window is opened and the user is navigated to the Shipment Tracking app
			myEstesPickupVisibility.clickOnTrackAShipmentLink(); 
			testUtil.setHardWait(1500);
			
			Set<String> allWindows = driver.getWindowHandles(); 
			testUtil.switchWindow(allWindows, mainWindow);
			
			//Step 9: From Shipment Tracking app, validate the error message -> Validate the following error message: 'Not found or tracking information unavailable:PUR024156'
			shipmentTrackingPage.validateNotFoundMessage(shipmentTrackingMessageForSinglePO);
			
			//Step 10: Tab back to the Pickup Visibility app and repeat (steps 6, 7)
			driver.close();
			driver.switchTo().window(mainWindow); 
			
			//Step 11: From search box, enter the following invalid data: 
			/*
			PUR027134
			PUR037135
			PUR047136
			PUR057137
			PUR067138
			PUR077139
			*/
			myEstesPickupVisibility.enterPickupRequestNumbers("PUR027134", "PUR037135", "PUR047136", "PUR057137", "PUR067138", "PUR077139"); 
			
			//Step 12: Click on Search Button: 
			/*Validate the following error message:
			Are you trying to *track a shipment* Pickup Request Numbers PUR027134
			PUR037135
			PUR047136
			PUR057137
			PUR067138
			PUR077139, and 3 *others* not found.
			*/
			myEstesPickupVisibility.clickSearch(); 
			testUtil.setHardWait(1500);
			
			/*Step 13: Click on the *others* link in the error message: A new window is opened and the user is navigated to the Shipment Tracking app
			 * Step 14: From Shipment Tracking app, validate the error message: Validate the following error message: Not found or tracking information unavailable: PUR027134
				*PUR037135
				*PUR047136
				*PUR057137
				*PUR067138
				*PUR077139
			 */
			myEstesPickupVisibility.validateShipmentNotFoundMessage(errorMultiplePO);
			myEstesPickupVisibility.clickOnTrackAShipmentLink(); 
			
			testUtil.setHardWait(5000);
			
			allWindows = driver.getWindowHandles(); 
			testUtil.switchWindow(allWindows, mainWindow);
			
			testUtil.setHardWait(2500);
			shipmentTrackingPage.validateNotFoundMessage(shipmentTrackingMessageForMultPO);
			/*Step 15: Validate results are returned in order as entered in data and query string in address should data in the correct order: 
			 *Validate the following query string data:
			 *https://estes-express-uat.estesinternal.com/myestes/tracking/shipments?type=PRO&query=PUR027134,PUR037135,PUR047136,PUR057137,PUR067138,PUR077139
			*/
			String expectedCurrentUrl = "https://estes-express-uat.estesinternal.com/myestes/tracking/shipments?type=PRO&query=PUR027134,PUR037135,PUR047136,PUR057137,PUR067138,PUR077139"; 
			Assert.assertEquals(driver.getCurrentUrl(), expectedCurrentUrl);
			
			//Step 16: Tab back to the Shipment Tracking app: Message bar closes
			//Step 17: From the error message bar, click the x: Message bar closes
			driver.close();
			driver.switchTo().window(mainWindow); 
			
		}
		
		/*
		 * Pickup Visibility - Unauth - Verify error message should be displayed on invalid pickup request data and can be tracked via the Shipment Tracking app
		 */
		
		@Test(enabled = true, priority = 2)

		public void executeQZ_12010() throws InterruptedException {
			/* Step 1: Access the following URL for My Estes: <<MyEstes QA URL>>QZ-9814
			 * Step 2: From My Estes home page hover mouse over the following menu: Track
			 * Step 3: From Track menu, click on Pickup Visibility
			 * Step 4: From Track page navigate to the following application: <Pickup Visibility>
			 */
			myEstesHomePage.clickOnTrackHeaderTitle();
			myEstesHomePage.clickPickupVisibility(); 
			
			//Step 5: From Enter one or more numbers (one per line) box, enter the following invalid data: PUR024156
			String pickupRequestNumber = "PUR024156"; 
			myEstesPickupVisibility.enterPickupRequestNumbers1(pickupRequestNumber);
			
			 /* Step 6: Click on Search button
			 * Step 7: Click on the *track a pickup request?* link in the error message
			 * A new window is opened and the user is navigated to the Shipment Tracking app
			 * Validate the following error message: Validate the following error message: Are you trying to *track a shipment?* Pickup Request number PUR024156 not found.
			 */
			String errorSinglePO = "Are you trying to track a shipment? Pickup Request number "+pickupRequestNumber+" not found.";
			myEstesPickupVisibility.clickSearch();
			myEstesPickupVisibility.validateShipmentNotFoundMessage(errorSinglePO); 
			String mainWindow = driver.getWindowHandle(); 
			
			/*Step 8: From Shipment Tracking app, validate the error message 	
			 * Validate the following error message: Not found or tracking information unavailable:PUR024156
			 */
			myEstesPickupVisibility.clickOnTrackAShipmentLink(); 
			testUtil.setHardWait(1500);
			
			Set<String> allWindows = driver.getWindowHandles(); 
			testUtil.switchWindow(allWindows, mainWindow);
			
			String shipmentTrackingMessageForSinglePO = "Not found or tracking information unavailable: "+pickupRequestNumber+".";  
			shipmentTrackingPage.validateNotFoundMessage(shipmentTrackingMessageForSinglePO);
			
			//Step 9: Tab back to the Shipment Tracking app and repeat (steps 6, 7)
			driver.close();
			driver.switchTo().window(mainWindow); 
			
			
			//Step 10: From Search box, enter the following invalid data:
			/*
			PUR027134
			PUR037135
			PUR047136
			PUR057137
			PUR067138
			PUR077139
			*/
			myEstesPickupVisibility.enterPickupRequestNumbers("PUR027134", "PUR037135", "PUR047136", "PUR057137", "PUR067138", "PUR077139"); 
			
			//Step 11: Click on Search Button: 
			/*Validate the following error message:
			Are you trying to *track a shipment* Pickup Request Numbers PUR027134
			PUR037135
			PUR047136
			PUR057137
			PUR067138
			PUR077139, and 3 *others* not found.
			*/
			myEstesPickupVisibility.clickSearch(); 
			testUtil.setHardWait(1500);
			
			/*Step 12: Click on the *others* link in the error message: A new window is opened and the user is navigated to the Shipment Tracking app
			 * Step 13: From Shipment Tracking app, validate the error message: Validate the following error message: Not found or tracking information unavailable: PUR027134
				*PUR037135
				*PUR047136
				*PUR057137
				*PUR067138
				*PUR077139
			 */
			String errorMultiplePO = "Are you trying to track a shipment? Pickup Request numbers PUR027134, PUR037135, PUR047136, and 3 others not found.";
			String shipmentTrackingMessageForMultPO = "Not found or tracking information unavailable: PUR027134, PUR037135, PUR047136, PUR057137, PUR067138, PUR077139."; 
			
			myEstesPickupVisibility.validateShipmentNotFoundMessage(errorMultiplePO);
			myEstesPickupVisibility.clickOnTrackAShipmentLink(); 
			
			testUtil.setHardWait(5000);
			
			allWindows = driver.getWindowHandles(); 
			testUtil.switchWindow(allWindows, mainWindow);
			
			testUtil.setHardWait(2500);
			shipmentTrackingPage.validateNotFoundMessage(shipmentTrackingMessageForMultPO);
			/*Step 14: Validate results are returned in order as entered in data and query string in address should data in the correct order: 
			 *Validate the following query string data:
			 *https://estes-express-uat.estesinternal.com/myestes/tracking/shipments?type=PRO&query=PUR027134,PUR037135,PUR047136,PUR057137,PUR067138,PUR077139
			*/
			String expectedCurrentUrl = "https://estes-express-qa.estesinternal.com/myestes/tracking/shipments?type=PRO&query=PUR027134,PUR037135,PUR047136,PUR057137,PUR067138,PUR077139"; 
			Assert.assertEquals(driver.getCurrentUrl(), expectedCurrentUrl);
			
			//Step 15: Tab back to the Shipment Tracking app: Message bar closes
			//Step 16: From the error message bar, click the x: Message bar closes
			driver.close();
			driver.switchTo().window(mainWindow); 
			
			
		}
		
	}

