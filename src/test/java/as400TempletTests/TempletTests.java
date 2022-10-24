package as400TempletTests;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;

import org.testng.annotations.Test;

import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.ReserveFreightBillScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import jagacyVT.screens.UpdateScreen3;
import util.Keyboard;
import util.Shortcuts;
import util.TestUtil;
import jagacyVT.screens.ActiveDeliveryManifestScreen;

import jagacyVT.screens.CloseADeliveryManifestScreen;
import jagacyVT.screens.CommandEntryScreen;
import jagacyVT.screens.DeliveryManifestArrivalScreen;
import jagacyVT.screens.DeliveryManifestDispatchScreen;
import jagacyVT.screens.DeliveryManifestEntryUpdateScreen;
import jagacyVT.screens.DeliveryProUpdateScreen;
import jagacyVT.screens.DriversLogEntryScreen;

import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.PnDDriverLogSateEntryScreen;



public class TempletTests {

	//This test is used to resever and create FrieghtBill (QZ-384- reserve FB & QZ-2823- this is for pnd)
	
	/**
	 * qz384_qz2823_reserveAndUpdateFreightBill
	 * 
	 * @param terNum
	 * @param numOfFreightBill
	 * @param sCode
	 * @param terNum1
	 * @param consignee
	 * @param shipper
	 * @param thirdParty
	 * @return
	 * @throws Exception
	 */
	
	@Test(enabled=true)
	
	public String executeQZ_384(String terNum, String numOfFreightBill, String sCode, String terNum1,
			String consignee, String shipper, String thirdParty) throws Exception {

		String TS = "1";
		String PCS = "10";
		String Terms = "ppd";
		String Wgt = "1000";
		String cName = "";
		String Address = "";
		String City = "";
		String State = "";
		String Zip = "";
		String PO = "123456";
		String PUDr = "99999999";
		String cFeet = "11";
		String clss = "50";
		String PCS2 = "10";
		String PK2 = "bx";
		String DES2 = "ITS A TEST";
		String Wgt2 = "1000";
		String option = "1";

		SessionVt session = null;
		String userName = "devabni", password = "nithyadev";
		String name = "myJagacyVT";
		// String host="exlaqa";
		String terminal = "dec-vt220";

		// Start session
		session = new SessionVt(name, "exlaqa", terminal);

		session.open();
		Thread.sleep(2000);
        //Screens
		LoginScreen login = new LoginScreen(session);

		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);

		UpdateScreen updateScreen = new UpdateScreen(session);
		UpdateScreen2 updateScreen2 = new UpdateScreen2(session);
		UpdateScreen3 updateScreen3 = new UpdateScreen3(session);
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);

		login.logon(userName, password);
		
		//Reserve Freight Bill
		// From the LTL/38 Master Menu, Enter 1
				
				ltl38MasterMenuScreen.verifyScreenTitle(); // Added new method to avoid the failure due to user login
			

				Thread.sleep(3000);
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		ltl38MasterMenuScreen.enterValueOptionField("1");
		freightBillingMenuScreen.enterValueOptionField("82");
	
		freightBillingMenuScreen.enterValueUserField("test");
		freightBillingMenuScreen.enterTabKey();
		
		freightBillingMenuScreen.enterTerminalNumber(terNum);
		reserveFreightBillScreen.enterNumberOfFB(numOfFreightBill);
		reserveFreightBillScreen.enterShipperCode(sCode);

		Thread.sleep(4000);

		String fbNum = reserveFreightBillScreen.getFBNumber();
		System.out.println("Freight bill is reserved");

		// Press F3 to exit

		jagacyUtil.pressF3();
		
		//Update Freight Bill

		freightBillingMenuScreen.enterValueOptionField("2");
		freightBillingMenuScreen.enterValueUserField("test");
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(terNum1);

		freightBillingMenuScreen.enterFreightBillNumber(fbNum); // fBillNumber
		Thread.sleep(7000);

		updateScreen.enterValuesToUpdateScreen(TS, PCS, Terms, Wgt, consignee, cName, shipper, Address, City, State,
				Zip, thirdParty, PO, PUDr, cFeet);

		updateScreen2.enterValuesToUpdateScreen2(clss, PCS2, PK2, DES2, Wgt2);
		updateScreen2.pressEnterKey();

		updateScreen3.pressEnterKey();
		Thread.sleep(2000);
		updateScreen3.pressEnterKey();
		Thread.sleep(2000);
		updateScreen3.pressEnterKey();
		Thread.sleep(2000);
		System.out.println("Freight bill is updated");

		if (session != null) {
			session.close();
			session.abort();

		}
		return fbNum;

	}
	
	//Unable to retrieve Costing and region run number
	/**
	 * 
	 * cityDeliveryManifest
	 * 
	 * MAYBE QZ-2661
	 * @param fbNum
	 * @param terNum
	 * @throws JagacyException
	 * @throws Exception
	 */
	@Test

	public void executeQZ_2661(String fbNum , String terNum) throws  JagacyException, Exception {
		SessionVt session=null;

		String userName = "devabni";
		String password = "nithyadev";
		String name = "myJagacyVT";
		String host="exlaqa";
		String terminal = "dec-vt220";
		String value = "?";
		String value2 = "MTY";
		
		session = new SessionVt("myJagacyVT", host);	
		
		session.open();
		
		
		LoginScreen login = new LoginScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
		ActiveDeliveryManifestScreen activeDeliveryManifestScreen = new ActiveDeliveryManifestScreen(session);
		DeliveryManifestEntryUpdateScreen deliveryManifestEntryUpdateScreen = new DeliveryManifestEntryUpdateScreen(session);
		CloseADeliveryManifestScreen closeADeliveryManifestScreen = new CloseADeliveryManifestScreen(session);
		DeliveryManifestDispatchScreen deliveryManifestDispatchScreen = new DeliveryManifestDispatchScreen(session);
		DeliveryManifestArrivalScreen deliveryManifestArrivalScreen = new DeliveryManifestArrivalScreen(session);
		DriversLogEntryScreen driversLogEntryScreen = new DriversLogEntryScreen(session);
		PnDDriverLogSateEntryScreen pnDDriverLogSateEntryScreen = new PnDDriverLogSateEntryScreen(session);
		DeliveryProUpdateScreen deliveryProUpdateScreen = new DeliveryProUpdateScreen(session);
		

		login.logon(userName, password);
		
		iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		ltl38MasterMenuScreen.enterValueOptionField("1");
		freightBillingMenuScreen.enterValueOptionField("25");
		freightBillingMenuScreen.enterValueUserField("QA");
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(terNum);
		
		activeDeliveryManifestScreen.enterF10Key();
		
		deliveryManifestEntryUpdateScreen.enterValueInTLField("?" , "MTY" , "2" ,"1");
//		deliveryManifestEntryUpdateScreen.replaceLTRWithMTY("MTY");
//		deliveryManifestEntryUpdateScreen.enterPosToValue("2");
//		deliveryManifestEntryUpdateScreen.enterValueToSelectTrailer("1");
		deliveryManifestEntryUpdateScreen.enterFB(fbNum);
		
		closeADeliveryManifestScreen.enterValueToDispatchField("Y");
		
		deliveryManifestDispatchScreen.enterValueTractorField("?");
		deliveryManifestDispatchScreen.enterPosToValue("6");
		deliveryManifestDispatchScreen.enterValueToSelectTractor("1");
		deliveryManifestDispatchScreen.enterDriverNumber("1121");
		
		
		//deliveryManifestDispatchScreen.enterPandDCostingRunNum("201");
		//deliveryManifestDispatchScreen.enterPandDRegionRunNum("20002");
		deliveryManifestDispatchScreen.enterPandDCostingAndRegionRunNum(); // Blocker1 -- need to add plus 1 to the existing number untill its valid
		//deliveryManifestDispatchScreen.enterKey();
		
		deliveryManifestDispatchScreen.readPosition();
		deliveryManifestDispatchScreen.enterF1Key();
		
		deliveryManifestEntryUpdateScreen.enterF1Key();
		
		activeDeliveryManifestScreen.enterValueA("A"); //Blocker2
		
		deliveryManifestArrivalScreen.enterValueForProUpdate("Y");
		deliveryManifestArrivalScreen.enterValueSorA("A");
		
		driversLogEntryScreen.enterTimeOut("5");
		driversLogEntryScreen.enterTimeIn("9");
		driversLogEntryScreen.enterMiles("500");
		driversLogEntryScreen.enterNumberOfStops("1");
		
		pnDDriverLogSateEntryScreen.enterOdometerInValue("5000");
		pnDDriverLogSateEntryScreen.enterState("PA");
		pnDDriverLogSateEntryScreen.enterMiles("500");
		pnDDriverLogSateEntryScreen.enterF9Key();
		
		deliveryProUpdateScreen.enterValuesForDeliveryPro("OK", "Tester", "5", "6", "9");
	

}
	
	
	
public void callCommand(String command, String username, String password) throws JagacyException, InterruptedException {
	
	SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
	
	jagacy.util.JagacyUtil jagacyUtil2 = new jagacy.util.JagacyUtil(session);
	
	session.open();
	LoginScreen loginScreen2 = new LoginScreen(session);
	loginScreen2.logon(username, password);
	if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
		jagacyUtil2.pressEnter();
	
	CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
	commandEntryScreen.enterCommand(command);
	if (session != null) {
		session.close();
		session.abort();
	}
}

/**
 * 
 * @Author - Jeff Ying- created on 2/01/22
 * 
 *  Create Freight Bill 
 */

@Test
public void executeQZ_9837() throws Exception {
	// Jagacy RESERVE BILL FIRST

	SessionVt session = null;
	String userName = "QATSTFRTBL";
	String password = "qatest2019";
	String name = "myJagacyVT";
	String sCode = "2302234";
	String consZip = "43228";
	String cons = "8700777";
	String term = "087";

	// String host="exlaqa";
	String terminal = "dec-vt220";
	session = new SessionVt(name, "exlaqa", terminal);

	// MySession session = new MySession();
	session.open();
	Thread.sleep(2000);

	LoginScreen loginScreen = new LoginScreen(session);
	loginScreen.enterUserNPasswordCDOC(userName, password);
	jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
	jagacyUtil.pressEnter();
	//Enter Option 1 in LTL/38 Master Menu
	Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);

	ltl38mastermenu.enterValueOptionField("1");

	
	//Option to reserve Freight bill
	jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(session);
	freightBillingMenuScreen.enterFreightBillMenuOption("82", "Reg", term);

	//Reserve freight Bill
	jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(session);
	Thread.sleep(3000);
	reserveFreightBillScreen.verifyScreenTitle();
	reserveFreightBillScreen.enterBillsToReserve("1");
	reserveFreightBillScreen.enterShipperCode(sCode);

	//Record the pro number
	String billNum = reserveFreightBillScreen.recordFBNumber();
	String fbNum = term+billNum;
	System.out.println("Freight Bill No " +fbNum);

	//Press F3 to exit
	jagacyUtil.pressF3(); 
	
	//1-2: Enter option 2 to for FB entry
	freightBillingMenuScreen.enterFreightBillMenuOption("2", "Reg", term);
	
	//3-4: Enter Reserved FB number
	freightBillingMenuScreen.enterFreightBill(term, billNum);
	
	//5-7: Enter Freight Bill details
	UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
	freightBillUpdateScreen.enterTS("1");
	freightBillUpdateScreen.enterPcs("5");
	freightBillUpdateScreen.enterTerms("PPD");
	freightBillUpdateScreen.enterWgt("550");
	freightBillUpdateScreen.enterCons1(cons);
	freightBillUpdateScreen.enterConsName("REG NOSIGN");
	freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
	freightBillUpdateScreen.enterConsCity("COLUMBUS");
	freightBillUpdateScreen.enterConsState("OH");
	freightBillUpdateScreen.enterConsZip(consZip);
	freightBillUpdateScreen.enterPOnum1("224091819");
	freightBillUpdateScreen.enterPuDrNum("9999999","9");
	freightBillUpdateScreen.enterCubicFeet("11");
	
	jagacyUtil.pressEnter();

	//Press Enter
	jagacyUtil.pressEnter();
	
	//8-10 : Enter 2nd Screen Details for FB
	UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
	freightBillUpdateScreen2.enterClass("50");
	freightBillUpdateScreen2.enterPcs2("5");
	freightBillUpdateScreen2.enterPK("CT");
	freightBillUpdateScreen2.enterDesc("CARTONS");
	freightBillUpdateScreen2.enterWgt2("550");

	jagacyUtil.pressEnter();

	jagacyUtil.pressEnter();
	
	//11-13: Enter 3rd Screen Details for FB
	UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
	freightBillUpdateScreen3.enterShippingInstruction1("HD");
	freightBillUpdateScreen3.enterShippingInstruction1("SIGRQD");

	jagacyUtil.pressEnter();
	Thread.sleep(4000);

	//14: Exit
	jagacyUtil.pressF1();
	jagacyUtil.pressF3(); 

	if(session != null) {
		session.abort();
		session.close();
	}

	
}


	
}




