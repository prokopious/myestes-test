package estesFinalMileTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import jagacyVT.screens.DeliveryAppointmentEntryUpdateScreen;
import jagacyVT.screens.DeliveryAppointmentInquirybyTIDscreen;
import jagacyVT.screens.DeliveryProUpdateScreen;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.MasterMenuScreen;
import jagacyVT.screens.ProsRequiringDeliveryAppointmentScreen;
import jagacyVT.screens.ReserveFreightBillScreen;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import testBase.TestBase;

public class EstesFinalMileTemplateTest extends TestBase {

	String fbNum = null;
	String name = "myJagacyVT";
	String terminal = "dec-vt220";
	String exlaqaName = "exlaqa";
	String commandLine = "call xxc870";
	String ltlption = "1";

	@Test(enabled=true,priority=1)
	public String executeQZ_9215() throws Exception {

		String fOption = "82";
		String terminal1 = "087";
		String noOfFb = "1";
		String shipperCode = "8791534";

		SessionVt session = null;
		session = new SessionVt(name, exlaqaName, terminal);
		session.open(name, exlaqaName, terminal);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		MasterMenuScreen masterMenuScreen = new MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		ReserveFreightBillScreen reserveFBScreen = new ReserveFreightBillScreen(session);

		// Login to exlaqa
		testUtil.setHardWait(2000);
		loginScreen.enterUserName(username16);
		loginScreen.enterPassword(password16);
		loginScreen.pressEnterKey();
		testUtil.setHardWait(2000);

		// LTL Menu Screen
		ibmMainMenuScreen.enterValueToComandLineField(commandLine);
		masterMenuScreen.enterFreightBillMenuOption(ltlption);

		// FB Options
		freightBillingMenuScreen.enterValueOptionField(fOption);
		freightBillingMenuScreen.enterValueUserField("EFM");
		freightBillingMenuScreen.enterTabKey();
		freightBillingMenuScreen.enterTerminalNumber(terminal1);
		freightBillingMenuScreen.pressEnterKey();

		reserveFBScreen.enterNumberOfFB(noOfFb);
		reserveFBScreen.enterShipperCode(shipperCode);
		fbNum = reserveFBScreen.getFBNumber();
		System.out.println("FB: " + fbNum);

		return fbNum;
	}

	public String checkOutboudFilecontainsData(String fileStartsWith, ArrayList<String> data) throws SmbException, MalformedURLException {
		System.out.println("Searching File from //exlaqa/root/edi/xlator/outbound/");
		String date = LocalDate.now().toString().replaceAll("-", "");
		String url = "smb://exlaqa/root/edi/xlator/outbound/"+ date + "/";
		int flag = 0;
		String fileNm = null;
		//Login here
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
		SmbFile smbDir = new SmbFile(url, auth);
		for (SmbFile f : smbDir.listFiles()) {
		if(f.getName().contains(fileStartsWith)) {
		try {
			System.out.println("Last Modified : "+f.getLastModified());
			String fileContent = IOUtils.toString(new SmbFileInputStream(f));
			fileNm = f.getName();
			System.out.println("File name: " + fileNm);
			System.out.println(fileContent);
			for(int d=0; d<data.size(); d++) {
				String dataString = data.get(d);
				if(fileContent.contains(dataString)) {
					System.out.println("File contains : "+dataString);
				}else {
					Assert.fail("Could not find : "+dataString);
				}
			}
			flag = 1;
			break;
		}catch (Exception e){
			System.out.println(e);
			}
		}
		if(flag == 1) {
			break;
		}
	}
		return fileNm;
	}
	
	public void checkLatestOutboudFilecontainsData(String fileStartsWith, ArrayList<String> data, String fileName1) throws UnknownHostException, IOException {
		System.out.println("Searching File from //exlaqa/root/edi/xlator/outbound/");
		String date = LocalDate.now().toString().replaceAll("-", "");
		String url = "smb://exlaqa/root/edi/xlator/outbound/"+ date + "/";
		int flag = 0;
		String fileNm = null;
		
		//Login here
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
		SmbFile smbDir = new SmbFile(url, auth);
		for (SmbFile f : smbDir.listFiles()) {
			fileNm = f.getName();
		if(fileNm.contains(fileStartsWith) && (!fileName1.equals(fileNm))) {
			System.out.println("File found : "+fileNm);
			String fileContent = IOUtils.toString(new SmbFileInputStream(f));
			System.out.println(fileContent);
			for(int d=0; d<data.size(); d++) {
				String dataString = data.get(d);
				if(fileContent.contains(dataString)) {
					System.out.println("File contains : "+dataString);
				}else {
					Assert.fail("Could not find : "+dataString);
				}
			}
			flag = 1;
		}
		if(flag == 1) {
			break;
		}
		}
	}
	/**
	 * 
	 * @author Ajitha
	 * @param billNum
	 * @throws Exception
	 * Setup Delivery Appointment   - Sys of Record now in Regression Project
	 */
	@Test(enabled = true, priority = 2)    
	public void executeQZ_8098(String billNum) throws Exception {

		String accountNumber = "7908810";
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String option = "1";
		String user = "Test";
		String originTerminal = "029";
		String destinationTerminal = "087";
		String option2 = "320";

		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		// no need to pass the same parameter in session.open as session instantiation
		session.open();
		testUtil.setHardWait(3000);

		LoginScreen login = new LoginScreen(session);
		login.enterUserNPasswordCDOC(userName, password);

		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		
		ltl38MasterMenuScreen.verifyScreenTitle();
		ltl38MasterMenuScreen.enterValueOptionField(option);

		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);

		//Step 1: From Freight Billing Menu screen, enter the following values:
		//	Option:320             User: QA                Terminal: #DT (ex: 087)
		//press ENTER
		freightBillingMenuScreen.enterMenuOptionForThreeDigits(option2, user, destinationTerminal);
		jagacyUtil.pressEnter();
		testUtil.setHardWait(3000);

		//Step 2: From "Pros requiring delivery appointment for #DT" screen :
		//press F8 (Add a PRO)
		jagacyUtil.pressF8();
		testUtil.setHardWait(5000);

		//Step 3: From "Delivery appointment inquiry by TID" screen, enter the 	following value:
		DeliveryAppointmentInquirybyTIDscreen deliveryAppointmentInquiryByTIDscreen = new DeliveryAppointmentInquirybyTIDscreen(
				session);

		//Pro to be added: OTPRO
		//press ENTER
		deliveryAppointmentInquiryByTIDscreen.enterFreightBill1(originTerminal, billNum);
		testUtil.setHardWait(5000);

		//then press F1 to Exit/Return
		jagacyUtil.pressF1();
		testUtil.setHardWait(5000);

		//Step 4: From "Pros requiring delivery appointment for DT" screen, enter the Consignee 
		//Name into Position to account name field (Note: Located at the end of 3rd line on the screen)
		//press ENTER
		ProsRequiringDeliveryAppointmentScreen prosRequiringDeliveryAppointmentScreen = new ProsRequiringDeliveryAppointmentScreen(
				session);
		prosRequiringDeliveryAppointmentScreen.searchProNumber(accountNumber);

		//Step 5: Identify OT PRO on the screen, then enter "S" right before the selected record.
		//press ENTER
		prosRequiringDeliveryAppointmentScreen.selectTheProNumber(billNum, "S");
		testUtil.setHardWait(3000);

		// Enter values in Delivery Appointment screen
		DeliveryProUpdateScreen deliveryProUpdateScreen = new DeliveryProUpdateScreen(session);
		String ApptDate = testUtil.addOrSubstractDateFromTodayDateFormat(+1);   //020120
		System.out.println("Date - " + ApptDate);
		testUtil.setHardWait(5000);

		// Step 6: From the "Delivery appointment entry/update" screen, enter the following values:

		//Appt Dt: MMDDYY (Current date+1)        
		//Time: 1115 - 1145   
		//Reason: CR
		//Name: EITQA
		//Phone: 804-804-8004

		//press ENTER
		deliveryProUpdateScreen.enterApptDate(ApptDate);
		deliveryProUpdateScreen.enterTimeRange("0930", "1000");
		deliveryProUpdateScreen.enterReason("CR");
		testUtil.setHardWait(3000);
		deliveryProUpdateScreen.enterName("TEST");
		deliveryProUpdateScreen.enterPhoneNum("804", "555", "8701", "234");
		testUtil.setHardWait(3000);

		jagacyUtil.pressEnter();
		testUtil.setHardWait(7000);

		//Step 7: "Delivery appointment entry/update" screen refreshes and above OTPRO details disappear.
		DeliveryAppointmentEntryUpdateScreen deliveryAppointmentEntryUpdateScreen=new DeliveryAppointmentEntryUpdateScreen(session);
		deliveryAppointmentEntryUpdateScreen.verifyProNumber(billNum);

	}

}
