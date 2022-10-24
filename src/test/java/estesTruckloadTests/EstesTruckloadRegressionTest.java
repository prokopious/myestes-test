package estesTruckloadTests;

import org.apache.log4j.Logger;
import org.joda.time.LocalTime;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import testBase.TestBase;
import util.TestUtil;
import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetTruckloadRateQuotePage;
import eNetPages.ENetVTLQouteExceptionQueuePage;
import eNetPages.ENetVTLRateQuotePage;
import eNetPages.VolumeAndTruckloadQuoteMaintenance;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.BookTruckloadShipmentPage;
import rateQuotePages.RateQuotePage;
import roundCubeApplicationPages.RoundCubeHomePage;
import roundCubeApplicationPages.RoundCubeLoginPage;

public class EstesTruckloadRegressionTest extends TestBase {

	Logger logger = Logger.getLogger(EstesTruckloadRegressionTest.class);

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	RateQuotePage rateQuotePage = new RateQuotePage();
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage();
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
	ENetVTLQouteExceptionQueuePage eNetVTLQouteExceptionQueuePage = new ENetVTLQouteExceptionQueuePage();
	VolumeAndTruckloadQuoteMaintenance volumeAndTruckloadQuoteMaintenance = new VolumeAndTruckloadQuoteMaintenance();
	ENetVTLRateQuotePage enetVTLRateQuotePage = new ENetVTLRateQuotePage();
	ENetTruckloadRateQuotePage eNetTruckloadRateQuotePage = new ENetTruckloadRateQuotePage();
	RoundCubeLoginPage roundCubeLoginPage = new RoundCubeLoginPage();
	RoundCubeHomePage roundCubeHomePage = new RoundCubeHomePage();
	BookTruckloadShipmentPage bookTruckloadShipmentPage = new BookTruckloadShipmentPage();

	/******************************* TESTS *******************************/


	/*
	 * Verify when the Estes Truckload quote selected pickup date is more than 30
	 * days in the future then an attention message displays on Submit Request
	 */

	@Test(enabled = true, priority = 1)
    public void executeQZ_10258()
                  throws Exception {

           String message = "Your chosen Pickup Date is more than 30 days from the current date. Please change your Pickup Date to a date within the next 30 days for an auto-rate, or contact our Truckload team at 1-866-378-3748, Press 31 for direct assistance.";

           // Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
           myEstesHomePage.clickOnMyEstes();
           myEstesHomePage.clickOnLogin();

           // Step 2: Login to *My Estes* application using the following
           // credentials:testnat
           myEstesLoginPage.enterUserName(username14);
           myEstesLoginPage.enterPassword(password14);
           myEstesLoginPage.clickOnLoginButton();

           // Step 3: Select *Ship* from the menu, then select *Rate Quote*
           // default selection *Less Than Truckload*
           myEstesHomePage.clickOnShipTab();
           myEstesHomePage.clickOnRateQoute();

           // Step 4: From the *Select Quote Type* section, select *Estes Truckload*

           rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

           // Step 5: In the *Routing Information* section, enter or select the following
           // values:
           testUtil.setHardWait(1000);
           rateQuotePage.enterOriginZip("23230");
           testUtil.setHardWait(1000);
           rateQuotePage.enterDesZip("27802");

           // Step 6 : In the *Shipment Information* section,enter or select the following
           // values:
           // Pickup Date: *32 days in the future (not a weekend or holiday)*
           rateQuotePage.enterPickupDate(testUtil.getBusinessDate("DATE", +32));

           rateQuotePage.enterWeight("31121");

           // Step 7: Click on submit request
           rateQuotePage.selectSubmitButton();

           // Step 8: Validate a message is displayed:
           rateQuotePage.verifyPickupDateMessageDisplayed(message);

           // Step-9: select *My Estes* from the menu then select
           myEstesLoginPage.clickOnMyEstes();
           testUtil.setHardWait(2000);
           myEstesLoginPage.clickOnLogoutButton();
           testUtil.setHardWait(1000);
          //Step-10 : Logout by clicking the  *Confirm* button
           myEstesLoginPage.clickOnLogoutConfirmButton();


	}
	/**
	 * 
	 * @author habibja
	 */
	/*
	 * Verify when the Estes Truckload keyed in Total Weight value is greater than 45,000 lb then a message 
	 * 'Total commodity weight exceeds the maximum of 45,000 lbs.' displays on Submit Request
	 */

	@Test(enabled = true, priority = 2)
	public void executeQZ_10257()
			throws Exception {

		String message = "Total commodity weight exceeds the maximum of 45,000 lbs.";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		// default selection *Less Than Truckload*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: From the *Select Quote Type* section, select *Estes Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

		// Step 5: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("30307");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("27802");

		// Step 6 : In the *Shipment Information* section,enter or select the following
		
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());

		rateQuotePage.enterWeight("45001");

		// Step 7: Click on submit request
		rateQuotePage.selectSubmitButton();

		// Step 8: Validate a message is displayed:
		rateQuotePage.verifyCommodityWgtMessageDisplayed(message);

		// Step-9: select *My Estes* from the menu then select
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();
        //Step-10 : Logout by clicking the  *Confirm* button
		myEstesLoginPage.clickOnLogoutConfirmButton();

	}
	
	/*
	 * Verify when the Estes Truckload quote selected pickup date equals today's date (but not a weekend or holiday) 
	 * then an attention message displays on Submit Request 
	 */

	@Test(enabled = true, priority = 3)
	public void executeQZ_10256()
			throws Exception {

		String message = "Your chosen Pickup Date cannot be automatically accommodated with such a short lead time. Please push your Pickup Date out another day and try your request again, or contact our Truckload team at 1-866-378-3748, Press 31 for direct assistance.`";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		// default selection *Less Than Truckload*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: From the *Select Quote Type* section, select *Estes Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();


		// Step 5: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("77007");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("27802");

		// Step 6 : In the *Shipment Information* section,enter or select the following
		
		rateQuotePage.enterPickupDate(testUtil.todaysDate());

		rateQuotePage.enterWeight("31121");

		// Step 7: Click on submit request
		rateQuotePage.selectSubmitButton();

		// Step 8: Validate a message is displayed:
		rateQuotePage.verifyTodaysPickupDateMessageDisplayed(message);

		// Step-9: select *My Estes* from the menu then select
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();
        //Step-10 : Logout by clicking the  *Confirm* button
		myEstesLoginPage.clickOnLogoutConfirmButton();

	}
	
	/*
	 * Verify that when eNet user selects Hazardous Materials on the Estes Truckload
	 * quote page then a message is displayed on the Results page Estimated Delivery 
	 * Date is blank and a Contact Me link is displayed under Charges

	 */

	@Test(enabled = true, priority = 4)
	public void executeQZ_10242()
			throws Exception {

		

		String SuccessMsg = "Attention: Hazmat shipments cannot be auto-rated at this time, please select Contact Me for assistance.";

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click Truckload Brokerage Rate Quote link
		eNetApplicationsPage.clickOnTLRateQuoteLink();

		eNetTruckloadRateQuotePage.switchToFrame();
		// Step 5: Enter routing information
		eNetTruckloadRateQuotePage.enterTLRateQuoteOriginAddress("94577");
		eNetTruckloadRateQuotePage.enterTLRateQuoteDestinationAddress("35016");

		// Step 6 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		eNetTruckloadRateQuotePage.enterTLRateQuotePickupDate(testUtil.addFutureWeekDay());

		eNetTruckloadRateQuotePage.SelectEquipmentType("Refrigerated");
		eNetTruckloadRateQuotePage.enterTotalWeight("31221"); // this should be 31700 not 31221. Verify!
		
		//Step-7 : From the  *Accessorials* section, select:
		eNetTruckloadRateQuotePage.clickOnHazardousMaterialsCheckBox();

		// Step 8: Click on submit button
		// Step 8: Select the following: Tanker Endorsement Needed: Selected/Checked
				eNetTruckloadRateQuotePage.clickOnTankerEndorsementCheckBox();

				// Step 9: Enter or select the following values:
				//- Un Number: 0007
				//- Packaging Code: II
				//- Commodity Type: Pallet
				//- Quantity: 27
				//- Weight: 21809
				//- Freight Description: Hazmat Shipment, QZ-10242
				eNetTruckloadRateQuotePage.enterAccessorialsUNNumber("0007");
				eNetTruckloadRateQuotePage.selectPackagingCode("II");
				eNetTruckloadRateQuotePage.selectCommodityType("PALLET");
				eNetTruckloadRateQuotePage.enterAccessorialsQuantity("27");
				eNetTruckloadRateQuotePage.enterAccessorialsWeight("21809");
				eNetTruckloadRateQuotePage.enterAccessorialsDescription("Hazmat Shipment, QZ-10242");

				// Step 10: Click on submit button
				
		eNetTruckloadRateQuotePage.clickOnTLRateQuoteSubmitBtn();
		testUtil.setHardWait(3000);
		//Step -9 : From the  *Results* table, verify a Hazmat message displays
		eNetTruckloadRateQuotePage.verifyHazmatMessageDisplays(SuccessMsg);
		
		//Step-10 : From the  *Results* table, verify Estimated Delivery Date is blank
		eNetTruckloadRateQuotePage.verifyEstimatedDeliveryDateIsBlank();
		
		//Step-11 : From the  *Results* table, verify *Estes Truckload* charges are Not calculated, A  *Contact Me* link is displayed
		eNetTruckloadRateQuotePage.verifyContactMeByServiceLevel("Estes Truckload");

		//Step-12 : click  *Log Out*
		eNetHomePage.clickOnLogout();
		
		//Step-13 : Confirm by clicking the *Logout* button
		eNetHomePage.clickOnLogoutButton();
	

}
	
	/*
	 *Verify that when MyEstes user selects Hazardous Materials on the Estes Truckload 
	 *quote page then a message is displayed on the Results page Estimated Delivery Date 
	 *and Charges are blank and a Contact Me link is displayed
	 */

	@Test(enabled = true, priority = 5)
	public void executeQZ_10241()
			throws Exception {

		String AccNum = "B000718";
		String SuccessMsg = "Hazmat shipments cannot be auto-rated at this time, please select Contact Me for assistance.";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

		// Step 5: In the *Requester Information* section, Select an Account from the
		// *Account Search* popup window or enter *B000718*
		rateQuotePage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumberInAccSearch(AccNum);
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnFirstAccountNumber();
		
		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("77007");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("23230");

		// Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.enterWeight("22421");
		// equipment type: dry van (default)
		// total weight: 22421
		rateQuotePage.clickOnHazardousMaterialsCheckBox();

		// Step 8: In the *Hazardous Materials* section, select the following: Tanker
		// Endorsement: Selected/Checked
		rateQuotePage.clickOnTankerEndorsementCheckBox();

		// Step 9: Enter or select the following values:
		// - Un Number: 1234
		//- Packaging Code: I
		//- Commodity Type: Jerrican
		//- Piece Count: 27
		//- Weight: 12421
		//- Freight Description: Hazmat Shipment
		rateQuotePage.enterUnNumber("1234");
		rateQuotePage.selectPackagingCode("I");
		rateQuotePage.selectCommodityType("JERRICAN");
		rateQuotePage.enterHazmatPieceCount("27");
		rateQuotePage.enterHazmatWeight("12421");
		rateQuotePage.enterHazmatDescription("Hazmat Shipment");

		
		// Step 10: Click on submit button
		rateQuotePage.clikOnSubmitButton();

		// Step-11 : From the *Results* table, verify a Hazmat message displays
		rateQuotePage.verifyHazmatMessageDisplays(SuccessMsg);

		// Step-12 : From the *Results* table, verify Estimated Delivery Date is blank
		rateQuotePage.verifyEstimatedDeliveryDateIsBlank();
		
		//Step-13: From the  *Results* table, verify *Estes Truckload* Charges are not calculated/blank
		rateQuotePage.verifyBasicCharge("Estes Truckload", false);

		// Step-14 : From the *Results* table, verify *Estes Truckload* charges are Not
		// calculated, A *Contact Me* link is displayed
		rateQuotePage.verifyContactMeByServiceLevel("Estes Truckload");

		// Step-16 : click *Log Out*
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();
		// Step-14 : Confirm by clicking the *Logout* button
		myEstesLoginPage.clickOnLogoutConfirmButton();
		
}
	
	/**
	 * 
	 * This test passed on 6/28/22
	 * 
	 * Verify when MyEstes user creates an Estes Truckload quote selects Contact Me link 
	 * enter valid contact us data and click on Confirm then the record is saved to the V/TL 
	 * Exception queue along with the exception reason and contact data
	 */

	@Test(enabled = true, priority = 6)
	public void executeQZ_10352()
			throws Exception {

		String AccNum = "B000718";
		
		String contactName = "TBP-708";
		String contactEmail = "QATest@Estes-express.com";
		String contactPhone = "8003531900";
		String PhoneExt = "1234567";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload* select  *Estes Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

		// Step 5: In the *Requester Information* section, Select an Account from the
		// *Account Search* popup window or enter *B000718*
		rateQuotePage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumberInAccSearch(AccNum);
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnFirstAccountNumber();

		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("77007");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("23230");

		// Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.enterWeight("22421");

		rateQuotePage.clickOnHazardousMaterialsCheckBox();

		// Step 8: In the *Hazardous Materials* section, select the following: Tanker Endorsement: Selected/Checked
		rateQuotePage.clickOnTankerEndorsementCheckBox();

		// Step 9: Enter or select the following values:
		// - Un Number: 0100
		//- Packaging Code: III
		//- Commodity Type: Skid
		//- Piece Count: 27
		//- Weight: 22421
		//- Freight Description: Hazmat Shipment, QZ-10352
		rateQuotePage.enterUnNumber("0100");
		rateQuotePage.selectPackagingCode("III");
		rateQuotePage.selectCommodityType("SKID");
		rateQuotePage.enterHazmatPieceCount("27");
		rateQuotePage.enterHazmatWeight("22421");
		rateQuotePage.enterHazmatDescription("Hazmat Shipment, QZ-10352");

		// Step 10: Click on submit request
		// Step 8: Click on submit request
		rateQuotePage.clickOnSubmitButton();

		//Step 9 : From the  *Results* table,verify a  *Contact Me* link is displayed
		rateQuotePage.verifyContactMeByServiceLevel("Estes Truckload");

		//Step-10 : Select the *Contact Me* link
		rateQuotePage.clickOnContMe();

		//Step-11 :From the *Contact Us* popup window,enter the following values:
		rateQuotePage.enterContactName(contactName);

		rateQuotePage.enterYourEmail(contactEmail);

		rateQuotePage.enterPhoneNo(contactPhone);

//		testUtil.setHardWait(40000);
		rateQuotePage.enterPhoneExtentionNo(PhoneExt);

		//Step-12 : Click *Confirm* button
		rateQuotePage.clickOnConfirmButton();

		// Record quote created tim
		String timeval = testUtil.getCurrentTimeViaDate();
		System.out.println(timeval);

		//Step-13 : Verify a success message id displayed:
		rateQuotePage.verifySuccessMessageDisplayed();

		//Step-14 : Verify the *Contact Me* button has a check icon next to it and cannot be clicked again
		rateQuotePage.verifyContactMeButtonHasCheckIconAndIsDisabled();

		// Step-15 & 16 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();

		// Step 17: launch enet
		driver.get(url2);

		// Step 18: Login to eNet application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 19: Click on Applications Tab
		eNetHomePage.clickOnApplicationsTab();

		// Step 20: Click onn VTL Quote Exception Queue Link
		eNetApplicationsPage.clickOnVTLQuoteExeptionQueueLink();

		testUtil.setHardWait(4000);
		// Step 21: Find the quote created above with the recorded time stamp
		String quoteNo = eNetVTLQouteExceptionQueuePage.getQuoteByTimeStamp(timeval);
		testUtil.setHardWait(2000);
		// Step 22: Verify the following exceptions are displayed:
		// *Truckload Shipment - Hazmat Accessorial*

		eNetVTLQouteExceptionQueuePage.verifyExceptionByQuote(quoteNo, "Hazmat Accessorial");

		// Step 23: Select Quote# link

		eNetVTLQouteExceptionQueuePage.clickOnQuoteNumber(quoteNo);

		//From the *Volume and Truckload Quote Maintenance* page
		volumeAndTruckloadQuoteMaintenance.verifyVTLQuoteMaintenancePage();

		// Step 24 : Validate *Hazmat Accessorials* display  above the form to the right hand side
		volumeAndTruckloadQuoteMaintenance.verifyVTLQuoteMaintenceExceptionText("Hazmat Accessorial");

		//Step 25 : From the *Contact Information* section, validate the following fields data agree with the data keyed in step 11
		volumeAndTruckloadQuoteMaintenance.verifyContactName(contactName);

		volumeAndTruckloadQuoteMaintenance.verifyPhoneNumber("800-353-1900 x1234567");

		volumeAndTruckloadQuoteMaintenance.verifyEmail(contactEmail);

		//Step-26 : From the *Accessorials* section
		//Validate *Hazardous Materials* is selected and *System* value = 0.00
		
		volumeAndTruckloadQuoteMaintenance.validateHazardousMaterialsIsSelected();
		volumeAndTruckloadQuoteMaintenance.verifySystemValue("0.00");

		//Step-27 : From  *eNet*  home page top navigation menu,click  *Log Out*
		eNetHomePage.clickOnLogout();

		//Step-28 : Confirm by clicking the  *Logout* button
		eNetHomePage.clickOnLogoutButton();

	}
	
	/*
	 * Verify when Estes Truckload destination city/state/zip are not in contiguous US 
	 * then an error message 'The destination address provided is not in the contiguous 
	 * United States. xxx' is returned 
	 */

	@Test(enabled = true, priority = 7)
	public void executeQZ_10346()
			throws Exception {

		String message ="The destination address provided is not in the contiguous United States. Please update your request and submit again. For shipments outside of the contiguous United States, please contact our Truckload team 1-866-378-3748, press 31 for direct assistance.";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload* select  *Estes Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();


		// Step 5: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("23230");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("96807");

		// Step 6 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.enterWeight("32221");


		// Step 7: Click on submit request
		 rateQuotePage.selectSubmitButton();


		// Step 8: Validate a message is displayed:
        rateQuotePage.verifyADestinationMessageDisplayed(message);

		// Step-9 & 10 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();
}
	
	/*
	 * Verify when Estes Truckload origin city/state/zip are not in contiguous US 
	 * then an error message 'The origin address provided is not in the contiguous 
	 * United States. xxx' is returned 
	 */

	@Test(enabled = true, priority = 8)
	public void executeQZ_10345()
			throws Exception {

		String message ="The origin address provided is not in the contiguous United States. Please update your request and submit again. For shipments outside of the contiguous United States, please contact our Truckload team 1-866-378-3748, press 31 for direct assistance.";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username14);
		myEstesLoginPage.enterPassword(password14);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload* select  *Estes Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();


		// Step 5: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("99507");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("27802");

		// Step 6 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.enterWeight("32221");


		// Step 7: Click on submit request
		 rateQuotePage.selectSubmitButton();


		// Step 8: Validate a message is displayed:
        rateQuotePage.verifyAOriginMessageDisplayed(message);

		// Step-9 & 10 : Logoff and close browser
		myEstesLoginPage.clickOnMyEstes();
		testUtil.setHardWait(2000);
		myEstesLoginPage.clickOnLogoutButton();

		myEstesLoginPage.clickOnLogoutConfirmButton();
}
		
	//This test is going all the way to roundcube and validate
	
	/**
	 * Verify that when MyEstes user selects Email Quote button from Estes Truckload
	 * quote details section and all keyed in emails are in the correct format then
	 * a success message is displayed the emails are sent and the emails content is
	 * correct
	 */

	@Test(enabled = true, priority = 9)
	public void executeQZ_10358()
			throws Exception {

		String AccNum = "3737170";
		String url = "http://smtpmx-r01.us.dom/webmail/";
		String email1= "EITQA@estes-express.com";
		String email2 ="qatest@estes-express.com";
		String message = "  Success! Your quote has been sent to the email address(es) that you entered.";
		String wgt ="32321";
	

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username8);
		myEstesLoginPage.enterPassword(password8);
		testUtil.setHardWait(1000);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload* select  *Estes Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

		// Step 5: In the *Requester Information* section, Select an Account from the
		// *Account Search* popup window or enter *3737170*
		rateQuotePage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumberInAccSearch(AccNum);
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnFirstAccountNumber();

		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(1000);
		rateQuotePage.enterOriginZip("98004");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("77004");

		// Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.enterWeight(wgt);

		// Step 8: Click on submit request
		rateQuotePage.clickOnSubmitButton();
		
		//Step -9 : From the  *Results* table,expand the caret to view quote details 
		rateQuotePage.clickOnCaretSymbol();
		
		//Step-10 : From the  *Quote Details* section,Note the following data:
	  
	    String quoteNum = rateQuotePage.recordQuoteNumber("Estes Truckload");
	    System.out.println("quoteNum : "+quoteNum);
	    
//	    String quote = quoteNum.substring(19, 26);   //newly added
	    
	    System.out.println("quoteNum!!!!!!!:"+quoteNum);
	    
    	String wgt2 = rateQuotePage.recordWeight(wgt);
//    	testUtil.setHardWait(40000);
		
		String freightCharges = rateQuotePage.getChargesByServiceLevel("Estes Truckload");
		
		//Get start time
		LocalTime time =  LocalTime.now();
		String ctime = time.toString();
		String currentTime[] = ctime.split(":");
		String startTime = currentTime[0]+":"+currentTime[1];
		
		//Step-11 : Click *Email Quote* button
		rateQuotePage.clickOnEmailQuoteButton();
		
		//Step-12 : From the *Email Rate Quote* popup window,enter the following:
		rateQuotePage.enterYourEmails(email1, email2);
		
		//Step-13 : Click the *Send* button
		rateQuotePage.clickOnSendButton();
		
		//Get end time
		LocalTime time1 =  LocalTime.now();
		String ctime1 = time1.toString();
		String currentTime1[] = ctime1.split(":");
		String endTime = currentTime1[0]+":"+currentTime1[1];
		
		
		//Step-14 : Validate a success message is displayed:
		rateQuotePage.verifySuccessMessageDisplayed(message);
		
		/*added last 2 steps here as it makes more sense to logout before going to roundcube*/
		// Step-21: select *My Estes* from the menu then select
        myEstesLoginPage.clickOnMyEstes();
        testUtil.setHardWait(2000);
        myEstesLoginPage.clickOnLogoutButton();
        testUtil.setHardWait(1000);
        //Step-22 : Logout by clicking the  *Confirm* button
        myEstesLoginPage.clickOnLogoutConfirmButton();
	
		String messageBody = "This rate is provided by Estes Truckload. If you contact an Estes Express Lines terminal to schedule pickup, the rate quote will not apply.";
				
		String messageBody1 = "To schedule pickup, you may book the shipment online by logging into your MyEstes account on our website. You can also call Estes Truckload at 866-378-3748 option 31 to book your Estes Truckload quote. Please do not call your local Estes terminal for pickup. This may result in additional charges.";
		String messageBody2 = "NOTICE TO SHIPPER / CUSTOMER: This rate is provided by Estes Truckload, do not contact an Estes terminal for pickup. You must either book online or call Estes Truckload to schedule pickup. Estes Truckload number is 866-378-3748 option 31.";
	
	//	String messageBody3 ="TERMS & CONDITIONS: ESTES QUOTE NUMBER MUST BE NOTED ON BILL OF LADING. Rate is based on a 53' trailer. Shipper load/consignee unload, no touch freight. If freight exceeds the specifications described on this quote, the customer will be charged for the additional services rendered. Carrier's maximum liability under provisions of this quote shall not exceed $100,000 cargo liability unless specifically noted in the quote.";
		
		String messageBody3="TERMS & CONDITIONS: ESTES QUOTE NUMBER MUST BE NOTED ON BILL OF LADING. Rate is based on a 53' trailer. Shipper load/consignee unload, no touch freight. If freight exceeds the specifications described on this quote, the customer will be charged for the additional services rendered. Estes Truckload quotes include 2 hours of free time for loading at origin and 2 hours free time for unloading at destination. Loading and/or unloading past 2 hours will incur detention charges at $70 per hour or $35 per half hour or less. Carrier's maximum liability under provisions of this quote shall not exceed $100,000 cargo liability unless specifically noted in the quote.";
			
		String subLine = "Estes Truckload Quote Information";
		
		String emailFrom = "noreply@estes-express.com";
		String serviceLvl ="Service Level: Estes Truckload";
		String type ="Dry Van";
		String totalWeight = "32,321";
	
		
		//Step-15 : Open Round Cube application
		driver.get(url);
		
		//Step-16 : Login to  *Roundcube* application
		roundCubeLoginPage.enterUserName();
		roundCubeLoginPage.enterPassword();
		roundCubeLoginPage.clickonLogintButton();
		testUtil.setHardWait(2000);
		
		//Step-17 : Find email(s) with subject line *Estes Truckload Quote Information*
		roundCubeHomePage.verifySubjectLine(subLine, startTime, endTime);
		testUtil.setHardWait(5000);
//		roundCubeHomePage.switchIframe();
		
		//Step-18 : Open one of the emails and validate the following:
		roundCubeHomePage.validateFromEmail(emailFrom);
		driver.switchTo().frame("messagecontframe");
		roundCubeHomePage.validateEmailContent(serviceLvl);
		roundCubeHomePage.validateEmailContent(type);
		
		//Step-19 : Validate the following data is correct and agrees with the data noted in step 10:
		roundCubeHomePage.validateEmailContent(quoteNum);
		roundCubeHomePage.validateEmailContent(totalWeight);
		roundCubeHomePage.validateEmailContent(freightCharges);
		
		//Step-20 : Validate the following disclaimers are displayed:
		roundCubeHomePage.validateEmailContent(messageBody);
		roundCubeHomePage.validateEmailContent(messageBody1);
		roundCubeHomePage.validateEmailContent(messageBody2);
		roundCubeHomePage.validateEmailContent(messageBody3);
	 
		
		
}
	

	
	/*
	 * Verify that when MyEstes user book an Estes Truckload quote with Refrigerated
	 * equipment type then a success message is displayed the order is successfully
	 * created on CHR side and a PRO is reserved
	 * 
	 * This test got passed on 8/30/2022 - Without change in code
	 */

	@Test(enabled = true, priority =10)
	public void executeQZ_10247()
			throws Exception {

		String AccNum = "B000718";

		String ContactName = "Contact First Last Name";
		String CompanyName = "Truckload Brokerage ";
		String EmailAddress = "QATEST@Estes-Express.com";
		String PhoneNumber = "8043531900";
		String PhoneExt = "3170";
		String OriginContactName = "Origin First Last Name";
		String OriginEmailAddress = "QATESTOrigin@Estes-Express.com";
		String OriginPhoneNumber = "8043531900";
		String OriginPhoneExt = "1111";
		String AddressLine1 = "3901 West Broad Street";
		String AddressLine2 = "Terminal 002";
		String SpecialInstruction = "TBP-700 Book a Truckload Brokerage quote Refrigerated equipment Origin Special Instructions";
		String DestContactName = "Destination First Last Name";
		String DestEmailAddress = "QATESTDestination@Estes-Express.com";
		String DestPhoneNumber = "8041234567";
		String DestPhoneExt = "7799";
		String DestAddressLine1 = "3914 East Shelby Drive";
		String DestAddressLine2 = "Terminal 037";
		String DestSpecialInstruction = "TBP-700 Book a Truckload Brokerage quote with Refrigerated equipment  - Destination Special Instructions";

		String Quantity = "21";
		String FreightDescription = "Truckload Order with Refrigerated Type. TBP-700";
		String FreightValue = "87500";
		String poNum = "020 3012021";

		// Step 1: Open the following link: https://estes-express-uat.estesinternal.com/
		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();

		// Step 2: Login to *My Estes* application using the following
		// credentials:testnat
		myEstesLoginPage.enterUserName(username8);
		testUtil.setHardWait(1000);
		myEstesLoginPage.enterPassword(password8);
		myEstesLoginPage.clickOnLoginButton();

		// Step 3: Select *Ship* from the menu, then select *Rate Quote*
		myEstesHomePage.clickOnShipTab();
		myEstesHomePage.clickOnRateQoute();

		// Step 4: the default selection *Less Than Truckload*

		rateQuotePage.clickOnEstesTruckloadBrokerageRadioButton();

		// Step 5: In the *Requester Information* section, Select an Account from the
		// *Account Search* popup window or enter *B000718*
		rateQuotePage.clickOnAccountSearchLink();
		testUtil.setHardWait(2000);
		rateQuotePage.enterAccountNumberInAccSearch(AccNum);
		testUtil.setHardWait(2000);
		rateQuotePage.clickOnFirstAccountNumber();

		// Step 6: In the *Routing Information* section, enter or select the following
		// values:
		testUtil.setHardWait(2000);
		rateQuotePage.enterOriginZip("23059");
		testUtil.setHardWait(1000);
		rateQuotePage.enterDesZip("37602");

		// Step 7 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		rateQuotePage.enterPickupDate(testUtil.addFutureWeekDay());
		rateQuotePage.selectEquipmentType("Refrigerated");
		rateQuotePage.enterWeight("30121");

		// Step 8: Click on submit request
		rateQuotePage.clickOnSubmitButton();

		// Step 9: Verify estes truckload charges are calculated
		rateQuotePage.verifyTuckloadBasicCharge("Truckload Brokerage", true);

		// Step 10 : Expand the results and verify quote details page displayed
		rateQuotePage.expandResultsSection();
		rateQuotePage.verifyQuoteDetailsPageDisplayed();

		// Step 11 : Click *Book Shipment* button
		rateQuotePage.clickOnBookShipmentButton();

		testUtil.setHardWait(2000);
		// Step 12 : From the *Shipment Details* section record quote number for step-15
		String quoteNumber = rateQuotePage.recordQuoteNum();

		// Step 13 : In the *Requestor Information* section enter the following values:

		bookTruckloadShipmentPage.enterContactName(ContactName);
		bookTruckloadShipmentPage.enterCompanyName(CompanyName);
		bookTruckloadShipmentPage.enterYourEmail(EmailAddress);
		bookTruckloadShipmentPage.enterPhoneNo(PhoneNumber);
		bookTruckloadShipmentPage.enterPhoneExtentionNo(PhoneExt);

		// Step 14 : In the *Routing - Origin Information* section enter the following
		// values:

		bookTruckloadShipmentPage.enterOriginContactName(OriginContactName);
		bookTruckloadShipmentPage.enterOriginEmail(OriginEmailAddress);
		bookTruckloadShipmentPage.enterOriginPhoneNo(OriginPhoneNumber);
		bookTruckloadShipmentPage.enterOriginPhoneExtentionNo(OriginPhoneExt);
		bookTruckloadShipmentPage.enterOriginAddress1(AddressLine1);
		bookTruckloadShipmentPage.enterOriginAddress2(AddressLine2);

		bookTruckloadShipmentPage.clickOnOriginAppointmentCheckbox();
		bookTruckloadShipmentPage.selectPickupStartTime("8", "15", "AM");
		bookTruckloadShipmentPage.selectPickupEndTime("3", "30", "PM");
		bookTruckloadShipmentPage.enterOriginSpecialInstructions(SpecialInstruction);

		// Step 15 : In the *Routing - Destination Information* section enter the
		// following values:

		bookTruckloadShipmentPage.enterDestContactName(DestContactName);
		bookTruckloadShipmentPage.enterDestEmail(DestEmailAddress);
		bookTruckloadShipmentPage.enterDestPhoneNo(DestPhoneNumber);
		bookTruckloadShipmentPage.enterDestPhoneExtentionNo(DestPhoneExt);
		bookTruckloadShipmentPage.enterDestAddress1(DestAddressLine1);
		bookTruckloadShipmentPage.enterDestAddress2(DestAddressLine2);
		bookTruckloadShipmentPage.clickOnDestAppointmentCheckbox();
		bookTruckloadShipmentPage.selectDeliveryStartTime("12", "30", "PM");
		bookTruckloadShipmentPage.selectDeliveryEndTime("4", "45", "PM");
		bookTruckloadShipmentPage.enterDestSpecialInstructions(DestSpecialInstruction);

		// Step 16 : From the *Freight Information* section, enter or select the
		// following values:

		bookTruckloadShipmentPage.enterCommodityType("SKID");
		bookTruckloadShipmentPage.enterQuantity(Quantity);
		bookTruckloadShipmentPage.enterFreightDesc(FreightDescription);
		bookTruckloadShipmentPage.enterTempNumberRangeFrom("33");
		bookTruckloadShipmentPage.enterTempNumberRangeTo("39");
		bookTruckloadShipmentPage.enterFreightValue(FreightValue);
		bookTruckloadShipmentPage.enterReferenceNumber(quoteNumber);
		bookTruckloadShipmentPage.enterPONumber(poNum);

		// Step 17 : Click the *Book Truckload Shipment* button
		bookTruckloadShipmentPage.clickOnBookTruckloadShipmentButton();

		// Verify Success message
		bookTruckloadShipmentPage.verifySuccessMessageIsDisplayed();

		// Step 18 : From the *Booking Confirmation* page, note the PRO Number
		String proNum = bookTruckloadShipmentPage.recordPRONum();
		String terminalNo = "020";

		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";
		String terminal = "dec-vt220";

		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// Step-19 : Logon to the *EXLAQA* server, using your credentials:
		loginScreen.logon(userName, password);
		Thread.sleep(2000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();

		// Step-20 : From the command line, enter: CALL XXC870
		ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Step-21 : From the *LTL Master Menu*, select: "1"
		ltl38MasterMenuScreen.enterValueOptionField("1");

		// Step-22 : From the *Freight Billing Menu*, enter the following:

		freightBillingMenuScreen.enterValueOptionField("1");

		freightBillingMenuScreen.enterValueUserField("Truckload");
		freightBillingMenuScreen.enterTabKey();

		freightBillingMenuScreen.enterTerminalNumber("002");

		// Step-23 : Key in the PRO Number noted in step 17
		freightBillingMenuScreen.enterFreightBill(terminalNo, proNum);

		// Verify the PRO is in R/Reserve Status
		freightBillingMenuScreen.verifyPROIsInRStatus();

		// Step-24 : To log out, press/enter
		// F1 (to exit FB inquiry)
		// 90 (to exit FB menu)
		// 90 (to exit and logout)

		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField("90");
		jagacyUtil.pressEnter();
		ltl38MasterMenuScreen.enterValueOptionField("90");

		if (session != null) {
			session.abort();
			session.close();
		}

	}
	
	/*
	 * Verify that when Enet user book an Estes Truckload quote with Refrigerated
	 * equipment type then a success message is displayed the order is successfully
	 * created on CHR side and a PRO is reserved
	 */

	@Test(enabled = true, priority = 11)
	public void executeQZ_10264()
			throws Exception {

		String ContactName = "Contact First Last Name";
		String CompanyName = "Truckload Brokerage ";
		String EmailAddress = "QATEST@Estes-Express.com";
		String PhoneNumber = "8043531900x3170";
		String OriginContactName = "Origin First Last Name";
		String OriginEmailAddress = "QATESTOrigin@Estes-Express.com";
		String OriginPhoneNumber = "8043531900x1111";
		String AddressLine1 = "5451 Highway 42";
		String AddressLine2 = "Terminal 041";
		String SpecialInstruction = "TBP-712 Book a Truckload Brokerage quote with Refrigerated equipment. Origin Special Instructions";
		String DestContactName = "Destination First Last Name";
		String DestEmailAddress = "QATESTDestination@Estes-Express.com";
		String DestPhoneNumber = "8041234567x7799";
		String DestAddressLine1 = "3914 East Shelby Drive";
		String DestAddressLine2 = "Terminal 037";
		String DestSpecialInstruction = "TBP-712 Book a Truckload Brokerage quote with Refrigerated equipment  - Destination Special Instructions";

		String Quantity = "37";
		String FreightDescription = "Truckload Order with Refrigerated Type. TBP-712";
		String FreightValue = "87500";

		String SuccessMsg = "Your booking is confirmed. Please make note of your PRO number in your records. Your PRO number can be used to check the current status of your shipment with our Shipment Tracking tool. If you have any questions, please contact our Truckload team at 1-866-378-3748, press 31 for assistance.";
		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click Truckload Brokerage Rate Quote link
		eNetApplicationsPage.clickOnTLRateQuoteLink();

		eNetTruckloadRateQuotePage.switchToFrame();
		// Step 5: Enter routing information
		eNetTruckloadRateQuotePage.enterTLRateQuoteOriginAddress("37188");
		eNetTruckloadRateQuotePage.enterTLRateQuoteDestinationAddress("76001");

		// Step 6 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		eNetTruckloadRateQuotePage.enterTLRateQuotePickupDate(testUtil.addFutureWeekDay());

		eNetTruckloadRateQuotePage.SelectEquipmentType("Refrigerated");
		eNetTruckloadRateQuotePage.enterTotalWeight("31221");

		// Step 7: Click on submit button
		eNetTruckloadRateQuotePage.clickOnTLRateQuoteSubmitBtn();

		// Step 8: select the *Estes Truckload* service level Charges and verify
		// *Truckload Rate Quote Details* page displays
		eNetTruckloadRateQuotePage.clickOnTruckloadCharges();

		eNetTruckloadRateQuotePage.verifyTLPageDisplayed();

		// Step 9 : Note Quote Number and Quote Expiration:

		String quoteNo = eNetTruckloadRateQuotePage.recordQuoteNumber();
		String quoteExp = eNetTruckloadRateQuotePage.recordQuoteExpiration();

		// Step 10 : From the *Quote Actions* section, Select i agree checkbox

		eNetTruckloadRateQuotePage.clickOnIagreeCheckbox();

		// Verify the *Book Shipment* button become active
		eNetTruckloadRateQuotePage.verifyBookShipmentButtonEnabled();

		// Step 11: Click the *Book Shipment* button
		eNetTruckloadRateQuotePage.clickOnBookShipmentButton();

		// Step 12 : In the *Contact Information* section enter the following values:

		eNetTruckloadRateQuotePage.enterContactName(ContactName);
		eNetTruckloadRateQuotePage.enterCompanyName(CompanyName);
		eNetTruckloadRateQuotePage.enterYourEmail(EmailAddress);
		eNetTruckloadRateQuotePage.enterPhoneNo(PhoneNumber);

		// Step 13 : In the *Routing - Origin Information* section enter the following
		// values:

		eNetTruckloadRateQuotePage.enterOriginContactName(OriginContactName);
		eNetTruckloadRateQuotePage.enterOriginEmail(OriginEmailAddress);
		eNetTruckloadRateQuotePage.enterOriginPhoneNo(OriginPhoneNumber);
		eNetTruckloadRateQuotePage.enterOriginAddress1(AddressLine1);
		eNetTruckloadRateQuotePage.enterOriginAddress2(AddressLine2);

		eNetTruckloadRateQuotePage.clickOnOriginAppointmentCheckbox();
		eNetTruckloadRateQuotePage.selectPickupStartTime("6", "30", "AM");
		eNetTruckloadRateQuotePage.selectPickupEndTime("10", "00", "AM");
		eNetTruckloadRateQuotePage.enterOriginSpecialInstructions(SpecialInstruction);

		// Step 14 : In the *Routing - Destination Information* section enter the
		// following values:

		eNetTruckloadRateQuotePage.enterDestContactName(DestContactName);
		eNetTruckloadRateQuotePage.enterDestEmail(DestEmailAddress);
		eNetTruckloadRateQuotePage.enterDestPhoneNo(DestPhoneNumber);
		eNetTruckloadRateQuotePage.enterDestAddress1(DestAddressLine1);
		eNetTruckloadRateQuotePage.enterDestAddress2(DestAddressLine2);
		eNetTruckloadRateQuotePage.clickOnDestAppointmentCheckbox();
		eNetTruckloadRateQuotePage.selectDeliveryStartTime("2", "30", "PM");
		eNetTruckloadRateQuotePage.selectDeliveryEndTime("5", "15", "PM");
		eNetTruckloadRateQuotePage.enterDestSpecialInstructions(DestSpecialInstruction);

		// Step 15 : From the *Freight Information* section, enter or select the
		// following values:

		eNetTruckloadRateQuotePage.enterCommodityType("SKID");
		eNetTruckloadRateQuotePage.enterQuantity(Quantity);
		eNetTruckloadRateQuotePage.enterFreightDesc(FreightDescription);
		eNetTruckloadRateQuotePage.enterTempNumberRangeFrom("34");
		eNetTruckloadRateQuotePage.enterTempNumberRangeTo("38");
		eNetTruckloadRateQuotePage.enterFreightValue(FreightValue);
		eNetTruckloadRateQuotePage.enterReferenceNumber(quoteNo);
		eNetTruckloadRateQuotePage.enterPONumber(quoteExp);

		// Step 16 : Click the submit button
		eNetTruckloadRateQuotePage.clickOnSubmitButton();

		// Verify Success message
		eNetTruckloadRateQuotePage.verifySuccessMessageDisplays(SuccessMsg);

		// Step 17 : From the *Booking Confirmation* page, note the PRO Number
		String proNum = eNetTruckloadRateQuotePage.recordPRONum();
		String terminalNo = "020";

		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";
		String terminal = "dec-vt220";

		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// Step-18 : Logon to the *EXLAQA* server, using your credentials:
		loginScreen.logon(userName, password);
		Thread.sleep(2000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();

		// Step-19 : From the command line, enter: CALL XXC870
		ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Step-20 : From the *LTL Master Menu*, select: "1"
		ltl38MasterMenuScreen.enterValueOptionField("1");

		// Step-21 : From the *Freight Billing Menu*, enter the following:

		freightBillingMenuScreen.enterValueOptionField("1");

		freightBillingMenuScreen.enterValueUserField("Truckload");
		freightBillingMenuScreen.enterTabKey();

		freightBillingMenuScreen.enterTerminalNumber("002");

		// Step-22 : Key in the PRO Number noted in step 17
		freightBillingMenuScreen.enterFreightBill(terminalNo, proNum);

		// Verify the PRO is in R/Reserve Status
		freightBillingMenuScreen.verifyPROIsInRStatus();

		// Step-23 : To log out, press/enter
		// F1 (to exit FB inquiry)
		// 90 (to exit FB menu)
		// 90 (to exit and logout)

		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField("90");
		jagacyUtil.pressEnter();
		ltl38MasterMenuScreen.enterValueOptionField("90");

		if (session != null) {
			session.abort();
			session.close();
		}

	}
	

	/*
	 * Verify that when Enet user book an Estes Truckload quote with Dry Van
	 * equipment type then a success message is displayed the order is successfully
	 * created on CHR side and a PRO is reserved
	 */

	@Test(enabled = true, priority = 12)
	public void executeQZ_10263()
			throws Exception {

		String ContactName = "Contact First Last Name";
		String CompanyName = "Truckload Brokerage ";
		String EmailAddress = "QATEST@Estes-Express.com";
		String PhoneNumber = "8043531900x3170";
		String OriginContactName = "Origin First Last Name";
		String OriginEmailAddress = "QATESTOrigin@Estes-Express.com";
		String OriginPhoneNumber = "8043531900x1111";
		String AddressLine1 = "5451 Highway 42";
		String AddressLine2 = "Terminal 041";
		String SpecialInstruction = "TBP-713 Book a Truckload Brokerage quote with Dry Van equipment. Origin Special Instructions";
		String DestContactName = "Destination First Last Name";
		String DestEmailAddress = "QATESTDestination@Estes-Express.com";
		String DestPhoneNumber = "8041234567x7799";
		String DestAddressLine1 = "3914 East Shelby Drive";
		String DestAddressLine2 = "Terminal 037";
		String DestSpecialInstruction = "TBP-713 Book a Truckload Brokerage quote with Dry Van equipment  - Destination Special Instructions";

		String Quantity = "35";
		String FreightDescription = "Truckload Order with Dry Van Type. TBP-713";
		String FreightValue = "65700";

		String SuccessMsg = "Your booking is confirmed. Please make note of your PRO number in your records. Your PRO number can be used to check the current status of your shipment with our Shipment Tracking tool. If you have any questions, please contact our Truckload team at 1-866-378-3748, press 31 for assistance.";

		// Step 1: Open eNet application
		driver.get(url2);

		// Step 2: Log in to eNet Application
		eNetLoginPage.enterUserID(username5);
		eNetLoginPage.enterUserPassword(password5);
		eNetLoginPage.clickOnLoginButton();

		// Step 3: Click Applications
		eNetHomePage.clickOnApplicationsTab();

		// Step 4: Click Truckload Brokerage Rate Quote link
		eNetApplicationsPage.clickOnTLRateQuoteLink();

		eNetTruckloadRateQuotePage.switchToFrame();
		// Step 5: Enter routing information
		eNetTruckloadRateQuotePage.enterTLRateQuoteOriginAddress("37188");
		eNetTruckloadRateQuotePage.enterTLRateQuoteDestinationAddress("76001");

		// Step 6 : In the *Shipment Information* section, enter Pickup Date: 2 days in
		// the future
		eNetTruckloadRateQuotePage.enterTLRateQuotePickupDate(testUtil.addFutureWeekDay());

		eNetTruckloadRateQuotePage.SelectEquipmentType("Dry Van");
		eNetTruckloadRateQuotePage.enterTotalWeight("31221");

		// Step 7: Click on submit button
		eNetTruckloadRateQuotePage.clickOnTLRateQuoteSubmitBtn();

		// Step 8: select the *Estes Truckload* service level Charges and verify
		// *Truckload Rate Quote Details* page displays
		eNetTruckloadRateQuotePage.clickOnTruckloadCharges();

		eNetTruckloadRateQuotePage.verifyTLPageDisplayed();

		// Step 9 : Note Quote Number and Quote Expiration:

		String quoteNo = eNetTruckloadRateQuotePage.recordQuoteNumber();
		String quoteExp = eNetTruckloadRateQuotePage.recordQuoteExpiration();

		// Step 10 : From the *Quote Actions* section, Select i agree checkbox

		eNetTruckloadRateQuotePage.clickOnIagreeCheckbox();

		// Verify the *Book Shipment* button become active
		eNetTruckloadRateQuotePage.verifyBookShipmentButtonEnabled();

		// Step 11: Click the *Book Shipment* button
		eNetTruckloadRateQuotePage.clickOnBookShipmentButton();

		// Step 12 : In the *Contact Information* section enter the following values:

		eNetTruckloadRateQuotePage.enterContactName(ContactName);
		eNetTruckloadRateQuotePage.enterCompanyName(CompanyName);
		eNetTruckloadRateQuotePage.enterYourEmail(EmailAddress);
		eNetTruckloadRateQuotePage.enterPhoneNo(PhoneNumber);

		// Step 13 : In the *Routing - Origin Information* section enter the following
		// values:

		eNetTruckloadRateQuotePage.enterOriginContactName(OriginContactName);
		eNetTruckloadRateQuotePage.enterOriginEmail(OriginEmailAddress);
		eNetTruckloadRateQuotePage.enterOriginPhoneNo(OriginPhoneNumber);
		eNetTruckloadRateQuotePage.enterOriginAddress1(AddressLine1);
		eNetTruckloadRateQuotePage.enterOriginAddress2(AddressLine2);

		eNetTruckloadRateQuotePage.clickOnOriginAppointmentCheckbox();
		eNetTruckloadRateQuotePage.selectPickupStartTime("5", "45", "AM");
		eNetTruckloadRateQuotePage.selectPickupEndTime("1", "00", "PM");
		eNetTruckloadRateQuotePage.enterOriginSpecialInstructions(SpecialInstruction);

		// Step 14 : In the *Routing - Destination Information* section enter the
		// following values:

		eNetTruckloadRateQuotePage.enterDestContactName(DestContactName);
		eNetTruckloadRateQuotePage.enterDestEmail(DestEmailAddress);
		eNetTruckloadRateQuotePage.enterDestPhoneNo(DestPhoneNumber);
		eNetTruckloadRateQuotePage.enterDestAddress1(DestAddressLine1);
		eNetTruckloadRateQuotePage.enterDestAddress2(DestAddressLine2);
		eNetTruckloadRateQuotePage.clickOnDestAppointmentCheckbox();
		eNetTruckloadRateQuotePage.selectDeliveryStartTime("2", "30", "PM");
		eNetTruckloadRateQuotePage.selectDeliveryEndTime("7", "15", "PM");
		eNetTruckloadRateQuotePage.enterDestSpecialInstructions(DestSpecialInstruction);

		// Step 15 : From the *Freight Information* section, enter or select the
		// following values:

		eNetTruckloadRateQuotePage.enterCommodityType("SKID");
		eNetTruckloadRateQuotePage.enterQuantity(Quantity);
		eNetTruckloadRateQuotePage.enterFreightDesc(FreightDescription);
		eNetTruckloadRateQuotePage.enterFreightValue(FreightValue);
		eNetTruckloadRateQuotePage.enterReferenceNumber(quoteNo);
		eNetTruckloadRateQuotePage.enterPONumber(quoteExp);

		// Step 16 : Click the submit button
		eNetTruckloadRateQuotePage.clickOnSubmitButton();

		// Verify Success message
		eNetTruckloadRateQuotePage.verifySuccessMessageDisplays(SuccessMsg);

		// Step 17 : From the *Booking Confirmation* page, note the PRO Number
		String proNum = eNetTruckloadRateQuotePage.recordPRONum();
		String terminalNo = "020";

		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";
		String terminal = "dec-vt220";

		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		JagacyUtil jagacyUtil = new JagacyUtil(session);

		// Step-18 : Logon to the *EXLAQA* server, using your credentials:
		loginScreen.logon(userName, password);
		Thread.sleep(2000);
		
		ibmMainMenuScreen.verifyIBMMainMenuScreen();

		// Step-19 : From the command line, enter: CALL XXC870
		ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Step-20 : From the *LTL Master Menu*, select: "1"
		ltl38MasterMenuScreen.enterValueOptionField("1");

		// Step-21 : From the *Freight Billing Menu*, enter the following:

		freightBillingMenuScreen.enterValueOptionField("1");

		freightBillingMenuScreen.enterValueUserField("Truckload");
		freightBillingMenuScreen.enterTabKey();

		freightBillingMenuScreen.enterTerminalNumber("002");

		// Step-22 : Key in the PRO Number noted in step 17
		freightBillingMenuScreen.enterFreightBill(terminalNo, proNum);

		// Verify the PRO is in R/Reserve Status
		freightBillingMenuScreen.verifyPROIsInRStatus();

		// Step-23 : To log out, press/enter
		// F1 (to exit FB inquiry)
		// 90 (to exit FB menu)
		// 90 (to exit and logout)

		jagacyUtil.pressF1();
		freightBillingMenuScreen.enterValueOptionField("90");
		jagacyUtil.pressEnter();
		ltl38MasterMenuScreen.enterValueOptionField("90");

		if (session != null) {
			session.abort();
			session.close();
		}
	
	}

}