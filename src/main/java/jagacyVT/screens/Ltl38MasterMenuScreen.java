package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;



public class Ltl38MasterMenuScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public Ltl38MasterMenuScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterValueOptionField(String option) throws JagacyException {

		logger.info("Enter option value ");
		System.out.println(session);
		session.waitForCursor(22, 9, 1000);
		session.writeString(option);
		session.waitForChange(3000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);

	}


	public void enterValueOptionField1(String option) throws JagacyException {

		logger.info("Enter option value 1 ");
		session.waitForCursor(20, 6, 1000);
		session.writeString(option);
		session.waitForChange(1000);

	}

	public void verifyScreenTitle() throws JagacyException, InterruptedException {
		logger.info("Verify Screen Title");
		boolean val = false;
		int i = 1;

		do {
			String title = session.readPosition(1, 31, 18);
			String titleMsg = session.readPosition(0, 27, 24);

			if (title.equalsIgnoreCase("LTL/38 Master Menu")) {
				System.out.println("Screen Title is " + title);
				val = true;
				break;
			} else if (titleMsg.contains("Display Program")) {
				System.out.println("Display message " + titleMsg);
				jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
				jagacyUtil.pressEnter();
				session.waitForChange(1000);
			} else {
				session.waitForChange(2000);
			}

			if (i == 5) {
				System.out.println("Failed to display LTL/38 Master menu");
				break;
			}
			i++;
		} while (val == false);

	}

	/**
	 * Reserve a freight bill to return a pro number to operate on given the session
	 * is sitting on the master menu (use startSessionAndLoginToMasterMenu() first)
	 * 
	 * @param session The jagacy session at the master menu
	 * @return The PRO Number which has yet to be reserved
	 * @throws InterruptedException
	 * @throws JagacyException
	 */
	public String reserveFreightBillFromMasterMenu(String user, String originTerminal,
			String shipperCode) throws JagacyException, InterruptedException {
		Ltl38MasterMenuScreen masterMenu = new Ltl38MasterMenuScreen(session);
		masterMenu.enterValueOptionField("1");
		FreightBillingMenuScreen freightBillingMenu = new FreightBillingMenuScreen(session);
		freightBillingMenu.enterFreightBillMenuOption("82", user, originTerminal);
		Thread.sleep(3000);
		ReserveFreightBillScreen reserveFreightBill = new ReserveFreightBillScreen(session);
		reserveFreightBill.verifyScreenTitle();
		reserveFreightBill.enterBillsToReserve("1");
		reserveFreightBill.enterShipperCode(shipperCode);
		String proNumber = originTerminal + reserveFreightBill.recordFBNumber();
		reserveFreightBill.pressF3();
		freightBillingMenu.pressF1();
		masterMenu.verifyScreenTitle();
		return proNumber;
	}

	public void createFreightBillFromMasterMenu(String user, String otPro, String ts, String pcs,
			String terms, String wgt, String consigneeCode, String puDr1, String puDr2, String cubicFeet, String desc, String pickupNumber)
			throws Exception {
		Ltl38MasterMenuScreen masterMenu = new Ltl38MasterMenuScreen(session);
		masterMenu.enterValueOptionField("1");
		FreightBillingMenuScreen freightBillingMenu = new FreightBillingMenuScreen(session);
		freightBillingMenu.enterFreightBillMenuOption("2", user, otPro.substring(0, 3));
		freightBillingMenu.enterFreightBill(otPro.substring(0, 3), otPro.substring(3));
		PickupSystemReconciliationsBySID pickupSystemReconciliations = new PickupSystemReconciliationsBySID(session);
		if (pickupSystemReconciliations.isCurrentScreen()) {
			pickupSystemReconciliations.selectPickupNumber(pickupNumber);
		}
		UpdateScreen updateScreen = new UpdateScreen(session);
		updateScreen.enterTS(ts);
		updateScreen.enterPcs(pcs);
		updateScreen.enterTerms(terms);
		updateScreen.enterWgt(wgt);
		updateScreen.enterConsigneeCode(consigneeCode);
		updateScreen.enterPONum("PO" + otPro);
		updateScreen.enterPuDrNum1(puDr1, puDr2);
		updateScreen.enterCubicFeet(cubicFeet);
		updateScreen.enterMasterBlNo("BOL" + otPro);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		UpdateScreen2 updateScreen2 = new UpdateScreen2(session);
		updateScreen2.enterPcs2(pcs);
		updateScreen2.enterPK("SK");
		updateScreen2.enterDesc(desc);
		updateScreen2.enterWgt2(wgt);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		freightBillingMenu.verifyFreightBillUpdated(otPro);
		session.writeKey(Key.PF1);
		session.waitForChange(1000);
		session.writeKey(Key.PF1);
		session.waitForChange(1000);
	}
	
	public void createFreightBillFromMasterMenu(String user, String otPro, String ts, String pcs,
			String terms, String wgt, String consigneeCode, String puDr1, String puDr2, String cubicFeet, String desc) throws Exception {
		createFreightBillFromMasterMenu(user, otPro, ts, pcs, terms, wgt, consigneeCode, puDr1, puDr2, cubicFeet, desc, "");
	}
}

