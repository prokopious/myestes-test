package util;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import freightBillPages.FreightBillUpdateMainScreen;
import freightBillPages.FreightBillUpdateScreen1;
import freightBillPages.FreightBillUpdateScreen2;
import freightBillPages.FreightBillUpdateScreen3;
import freightBillPages.FreightBillingMenu;
import freightBillPages.LTL38MasterMenu;
import freightBillPages.Login;
import freightBillPages.ReserveFreightBills;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.CommandEntryScreen;
import jagacyVT.screens.FreightBillInquiryEnterFieldValuesScreen;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.ReserveFreightBillScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import jagacyVT.screens.UpdateScreen3;
import testBase.TestBase;

public class FreightBill extends TestBase {
	
	/*public FreightBill(WebDriver _driver) {
		driver = _driver;
	}*/
	
	private Logger logger = Logger.getLogger(FreightBill.class);


	/* FREIGHT BILL CREDENTIALS */
	private String userName;
	private String password;
	private String name;

	/* AS400 SPECIFIC */
	private final String optionFreightBillingMenu = "1";
	private final String optionFreightBillInquiry = "1";
	private final String optionFreightBillEntry = "2";
	private final String optionReserveFreightBill = "82";
	private List<String> commands;

	/* FREIGHT BILL CONTACT INFO */
	private String consigneeCode;
	private String consigneeName;
	private String consigneeAddress;
	private String consigneeCity;
	private String consigneeState;
	private String consigneeZipCode;
	private String thirdPartyBill;
	private String cartTo;
	private String accessorialChargeCode;

	/* FREIGHT BILL OPTIONS */
	private String ts;
	private String pcs;
	private String terms;
	private String wgt;
	private String masterBlNo;
	private String poNum;
	private String puDrNum1;
	private String puDrNum2;
	private String cubicFeet;

	private String user;
	private String originTerminal;
	private String shipperCode;

	/* FREIGHT BILL OPERATION TIMESTAMPS */
	private String timeFreightBillCreated;
	private String timeCommandRun;

	/* POST-CREATION FREIGHT BILL REFERENCE VALUES */
	private String billNum;
	private String otProNum;
	
	
	public FreightBill() {

		// Default freight bill configuration
		userName = "QATSTFRTBL"; // no access to command line
		password = "qatest2019";

		ts = "1";
		pcs = "10";
		terms = "PPD";
		wgt = "1000";
		masterBlNo = "";

		consigneeCode = "8700777";
		consigneeName = "REG NOSIG";
		consigneeAddress = "2 KINGSHILL DR";
		consigneeCity = "COLUMBUS";
		consigneeState = "OH";
		consigneeZipCode = "43228";
		thirdPartyBill = "5020031";
		cartTo = "E087";
		accessorialChargeCode = "HD";

		poNum = "224091819";
		puDrNum1 = "9999999";
		puDrNum2 = "9";
		cubicFeet = "11";

		user = "REG";
		originTerminal = "087";
		shipperCode = "1487062";

		commands = new ArrayList<>();
		
		
	}
	
	//SETTERS
	
	public void setUsername(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setOriginTerminal(String originTerminal) {
		this.originTerminal = originTerminal;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public void setConsigneeCode(String consigneeCode) {
		this.consigneeCode = consigneeCode;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public void setConsigneeState(String consigneeState) {
		this.consigneeState = consigneeState;
	}

	public void setConsigneeZipCode(String consigneeZipCode) {
		this.consigneeZipCode = consigneeZipCode;
	}

	public void setThirdPartyBill(String thirdPartyBill) {
		this.thirdPartyBill = thirdPartyBill;
	}

	public void setCartTo(String cartTo) {
		this.cartTo = cartTo;
	}

	public void setAccessorialChargeCode(String accessorialChargeCode) {
		this.accessorialChargeCode = accessorialChargeCode;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public void setPcs(String pcs) {
		this.pcs = pcs;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public void setWgt(String wgt) {
		this.wgt = wgt;
	}

	public void setMasterBlNo(String masterBlNo) {
		this.masterBlNo = masterBlNo;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	public void setPuDrNum1(String puDrNum1) {
		this.puDrNum1 = puDrNum1;
	}

	public void setPuDrNum2(String puDrNum2) {
		this.puDrNum2 = puDrNum2;
	}

	public void setCubicFeet(String cubicFeet) {
		this.cubicFeet = cubicFeet;
	}

	// GETTERS

	public String getUserName() {
		return userName;

	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getTs() {
		return ts;
	}

	public String getPcs() {
		return pcs;
	}

	public String getTerms() {
		return terms;
	}

	public String getWgt() {
		return wgt;
	}

	public String getMasterBlNo() {
		return masterBlNo;
	}

	public String getConsigneeCode() {
		return consigneeCode;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public String getConsigneeCity() {
		return consigneeCity;
	}

	public String getConsigneeState() {
		return consigneeState;
	}

	public String getConsigneeZipCode() {
		return consigneeZipCode;
	}

	public String getThirdPartyBill() {
		return thirdPartyBill;
	}

	public String getCartTo() {
		return cartTo;
	}

	public String getAccessorialChargeCode() {
		return accessorialChargeCode;
	}

	public String getPoNum() {
		return poNum;
	}

	public String getPuDrNum1() {
		return puDrNum1;
	}

	public String getPuDrNum2() {
		return puDrNum2;
	}

	public String getCubicFeet() {
		return cubicFeet;
	}

	public String getUser() {
		return user;
	}

	public String getOriginTerminal() {
		return originTerminal;
	}

	public String getShipperCode() {
		return shipperCode;
	}

	public String getBillNum() {
		return billNum;
	}

	public String getOtProNumber() {
		return otProNum;
	}

	public void addCommand(String command) {
		commands.add(command);
	}

	public void clearCommands() {
		commands.clear();
	}



	public static FreightBill freightBill;
	
	static Login login;
	static LTL38MasterMenu ltl38MasterMenu;
	static FreightBillingMenu freightBillingMenu;
	static FreightBillUpdateMainScreen freightBillUpdateMainScreen;
	static FreightBillUpdateScreen1 freightBillUpdateScreen1;
	static FreightBillUpdateScreen2 freightBillUpdateScreen2;
	static FreightBillUpdateScreen3 freightBillUpdateScreen3;
	static ReserveFreightBills reserveFreightBills;

	public String reserveAFreightBillWithExcel(String uName, String Pass,
		
			
			String option, String user, String terNum, String NumOfBill,
			String SHCode)
			throws AWTException, InterruptedException, HeadlessException,
			UnsupportedFlavorException, IOException, ClassNotFoundException {
		
		logger.info("reserve FreightBill method lunching ....");
		testUtil.init(this);
		
		String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";

		
		Runtime.getRuntime().exec(path1);
		Shortcuts.pressEnter();
		Sleep.sleepMedium();

		login.signOn(uName, Pass);
		Sleep.sleepShort();
		ltl38MasterMenu.selectFrieghtBill("1");
		Sleep.sleepShort();
		freightBillingMenu.selectOption(option, user, terNum);
		Sleep.sleepShort();
		reserveFreightBills.enterNumberOfBills(NumOfBill);
		Sleep.sleepShort();
		reserveFreightBills.enterShipperCode(SHCode);
		Sleep.sleepShort();
		reserveFreightBills.submitRequest();
		Sleep.sleepShort();
		String pro = reserveFreightBills.getBillNumber();
		Sleep.sleepShort();
		reserveFreightBills.getMessage();
		Sleep.sleepShort();

		Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
		Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");

		return pro;

	}
	
	
	
	
	public String reserveAndCreateFreightBill() throws Exception {

		SessionVt session = null;
		session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();

		LoginScreen loginScreen = new LoginScreen(session);

		loginScreen.enterUserNPasswordCDOC(userName, password);
		if (session.readPosition(0, 27, 7).toString().trim().equals("Display")) {
			jagacyUtil.pressEnter();
			testUtil.setHardWait(2000);
		}

		// Commands screen
		if (session.readPosition(0, 33, 13).equals("Command Entry") && commands.size() > 0) {
			
			IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
			
			for (String command : commands) {
				iBMMainMenuScreen.enterValueToComandLineField(command);
				
			}
		}
		session.waitForChange(2000);
		
		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(optionFreightBillingMenu);

		// Option to reserve Freight bill
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(optionReserveFreightBill, user, originTerminal);
		Thread.sleep(3000);

		// Reserve freight Bill
		ReserveFreightBillScreen reserveFreightBillScreen = new ReserveFreightBillScreen(session);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve(optionFreightBillingMenu);
		reserveFreightBillScreen.enterShipperCode(shipperCode);

		// Record the pro number
		billNum = reserveFreightBillScreen.recordFBNumber();
		otProNum = originTerminal + billNum;
		System.out.println("Freight Bill No " + otProNum);
		// Press F3 to exit
		jagacyUtil.pressF3();

		// Enter Freight bill
		freightBillingMenuScreen.enterFreightBillMenuOption(optionFreightBillEntry, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS(ts);
		freightBillUpdateScreen.enterPcs(pcs);
		freightBillUpdateScreen.enterTerms(terms);
		freightBillUpdateScreen.enterWgt(wgt);
		freightBillUpdateScreen.enterCons(consigneeCode);
		freightBillUpdateScreen.enterConsName(consigneeName);
		freightBillUpdateScreen.enterConsAddress(consigneeAddress);
		freightBillUpdateScreen.enterConsCity(consigneeCity);
		freightBillUpdateScreen.enterConsState(consigneeState);
		freightBillUpdateScreen.enterConsZip(consigneeZipCode);
		if (consigneeZipCode.equals("")) {
			consigneeZipCode = freightBillUpdateScreen.recordZipCode();
		}
		freightBillUpdateScreen.enterPONum(poNum);
		freightBillUpdateScreen.enterPuDrNum(puDrNum1, puDrNum2);
		freightBillUpdateScreen.enterCubicFeet(cubicFeet);
		freightBillUpdateScreen.enterCartTo(cartTo);

		// bl number needs to be different from previous rerun faker if test fails
		if (masterBlNo.equals("")) {
			Faker faker = new Faker();
			String fakeMasterBlNo = "S" + faker.number().digits(7);
			freightBillUpdateScreen.enterMasterBlNo(fakeMasterBlNo);
		} else {
			freightBillUpdateScreen.enterMasterBlNo(masterBlNo);
		}

		if (!thirdPartyBill.equals("")) {
			freightBillUpdateScreen.enter3Pt(thirdPartyBill);
		}

		jagacyUtil.pressEnter();

		// Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");
		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();

		// Adding shipment instructions
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstruction1(accessorialChargeCode);
		jagacyUtil.pressEnter();

		// Timestamp for first file created
		timeFreightBillCreated = generateOutboundFileNameDateTime();

		// Press F1 to exit
		jagacyUtil.pressF1();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;
		logger.info("Returning PRO Number: [" + otProNum + "]");
		return otProNum;
	}

	public void setSignatureTestValues() {
		setShipperCode("1487062");
		setConsigneeCode("8700777");
		setConsigneeName("REG NOSIG");
		setConsigneeAddress("2 KINGSHILL DR");
		setConsigneeCity("COLUMBUS");
		setConsigneeState("OH");
		setConsigneeZipCode("43228");
		setThirdPartyBill("5020031");
		setCartTo("E087");
		setAccessorialChargeCode("HD");
	}

	public String getTimeFreightBillCreated() {
		if (timeFreightBillCreated == null) {
			logger.info("Freight bill not yet created! Returned null");
			return null;
		}
		logger.info("Got timestamp for freight bill creation");
		return timeFreightBillCreated;
	}

	public String getTimeCommandRun() {
		if (timeCommandRun == null) {
			logger.info("Output command not yet called! Returned null");
			return null;
		}
		logger.info("Got timestamp for command run");
		return timeCommandRun;
	}

	public String generateOutboundFileNameDateTime() {
		// Format: yymmddhhmm (2112211153)
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		String str = ts.toString();
//		return str.substring(2, 16).replaceAll("\\.", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		return formatter.format(new Date());
	}

	public void runOutputCommands() throws JagacyException, InterruptedException {
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		session.open();
		LoginScreen loginScreen2 = new LoginScreen(session);
		loginScreen2.logon(username16, password16);

		// Get past Intermediate screen if exists
		if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();

		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00C110");
		Thread.sleep(2000);
		commandEntryScreen.enterCommand("Call FBPGMS/EFM00R111");

		// Store the time when the command was run
		timeCommandRun = generateOutboundFileNameDateTime();
		Thread.sleep(2000);
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();

		if (session != null) {
			session.abort();
			session.close();
		}
		session = null;
	}

	public void clearConsigneeInfo() {
		setConsigneeName("");
		setConsigneeAddress("");
		setConsigneeCity("");
		setConsigneeState("");
		setConsigneeZipCode("");
	}

	public void verifyFreightBillHasDNPCodeWithEFMDescription(String otProNumber) throws Exception {
		SessionVt session = null;
		session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		session.open();

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);

		if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();

		// Commands screen
		if (session.readPosition(0, 33, 13).equals("Command Entry") && commands.size() > 0) {
			IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
			for (String command : commands) {
				iBMMainMenuScreen.enterValueToComandLineField(command);
			}
		}

		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(optionFreightBillingMenu);
		Thread.sleep(2000);

		// Option to make a Freight bill inquiry
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		freightBillingMenuScreen.enterFreightBillMenuOption(optionFreightBillInquiry, user, originTerminal);
		Thread.sleep(2000);

		FreightBillInquiryEnterFieldValuesScreen inquiryScreen = new FreightBillInquiryEnterFieldValuesScreen(session);
		inquiryScreen.enterFreightBillNum(otProNumber.substring(0, 3), otProNumber.substring(3));
		Thread.sleep(2000);

		for (int i = 0; i < 6; i++) {
			String code = session.readPosition(17 + i, 7, 6);
			logger.info("Code: [" + code + "]");
			if (code.trim().equals("DNP")) {
				String description = session.readPosition(17 + i, 19, 3);
				logger.info("Description: [" + description + "]");
				assertTrue(description.equals("EFM"), "DNP description is not prefixed with EFM");
				break;
			}
		}
		session.abort();
		session.close();
	}

		
}
