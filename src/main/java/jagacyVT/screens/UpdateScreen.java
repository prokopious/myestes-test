package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class UpdateScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public UpdateScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterValuesToUpdateScreen(String TS, String PCS, String Terms, String Wgt, String Consinee,
			String shipper, String cName, String Address, String City, String State, String Zip, String thirdParty,
			String PO, String PUDr, String cFeet) throws JagacyException, InterruptedException {

		logger.info("enter values to update screen");
		Thread.sleep(5000);
		session.waitForCursor(2, 9, 1000);
		session.writeString(TS);
		session.waitForChange(1000);

		session.waitForCursor(2, 15, 1000);
		session.writeString(PCS);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);

		session.waitForCursor(2, 27, 1000);
		session.writeString(Terms);
		session.waitForChange(1000);
		session.writeAfterLabel("Wgt", Wgt);
		session.waitForCursor(2, 35, 1000);
		//session.writeString(Wgt);
		session.waitForChange(1000);

		session.waitForCursor(4, 6, 1000);
		session.writeAfterLabel("Cons", Consinee);
		// session.writeString(Cons);
		session.waitForChange(3000);
		System.out.println(Consinee);

		session.waitForCursor(6, 6, 1000);
		session.writePosition(6, 6, shipper);
		// session.writeAfterLabel("Cons", shipper);
		// session.writeString(Cons);
		session.waitForChange(3000);

		session.writePosition(8, 6, thirdParty);
		// session.writeAfterLabel("Cons", thirdParty);
		// session.writeString(Cons);
		session.waitForChange(3000);
		System.out.println(thirdParty);

		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		Thread.sleep(5000);

		session.waitForCursor(4, 13, 1000);
		session.writeString(cName);
		session.waitForChange(1000);

		session.writeKey(Key.TAB);

		session.waitForCursor(4, 47, 1000);
		session.writeString(Address);
		session.waitForChange(1000);

		session.writeKey(Key.DOWN_ARROW);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		jagacyUtil.moveKeyTimes(Key.LEFT_ARROW,Address.length());
		

		
		session.waitForCursor(5, 47, 1000);
		session.writeString(City);
		session.waitForChange(1000);
		
		session.writeKey(Key.TAB);

		session.waitForCursor(5, 68, 1000);
		session.writeString(State);
		session.waitForChange(1000);
		
		session.waitForCursor(5, 71, 1000);
		session.writeString(Zip);
		session.waitForChange(1000);
		
		session.waitForCursor(6, 6, 1000);
		session.writePosition(6, 6, shipper);
		// session.writeAfterLabel("Cons", shipper);
		// session.writeString(Cons);
		session.waitForChange(3000);

		session.writePosition(8, 6, thirdParty);
		session.waitForChange(3000);
		System.out.println(thirdParty);
		
		session.waitForCursor(10, 6, 1000);
		session.writeAfterLabel("PO#", PO);
		// session.writeString(PO);
		session.waitForChange(1000);

		session.waitForCursor(10, 48, 1000);
		session.writeAfterLabel("PUDr", PUDr);
		// session.writeString(PUDr);
		session.waitForChange(1000);

		session.waitForCursor(13, 38, 1000);
		session.writeAfterLabel("Cubic Feet", cFeet);
		// 2session.writeString(cFeet);
		session.waitForChange(1000);

		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		Thread.sleep(5000);

		Thread.sleep(5000);

	}

	public final void enterTS(String ts) throws Exception {
		logger.info("Enter ts on Update screen" +ts);
		session.waitForCursor(2, 9, 5000);
		//session.writeString(ts);
		session.writeAfterLabel("TS", ts);
		session.waitForChange(1000);
	}

	public final void enterPcs(String pcs) throws Exception {
		logger.info("Enter pcs on Update screen");
		session.waitForCursor(2, 15, 5000);
		session.writePosition(2,15,pcs);
		//session.writeString(pcs);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterTerms(String terms) throws Exception {
		logger.info("Enter terms on Update screen");
		session.waitForCursor(2, 27, 5000);
		session.writeString(terms);
		session.waitForChange(1000);
	}

	public final void enterWgt(String wgt) throws Exception {
		logger.info("Enter Wgt on Update screen");
		session.waitForCursor(2, 35, 5000);
		session.writeString(wgt);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterMasterBlNo(String masterBlNo) throws Exception {
		logger.info("Enter Master BL# on Update screen");
		session.waitForCursor(3, 12, 5000);
		// session.writeString(masterBlNo);
		session.writePosition(3, 12, masterBlNo);
		// session.writeAfterLabel("BLNO#", masterBlNo);
		session.waitForChange(1000);
	}

	public final void enterCons(String cons) throws Exception {
		logger.info("Enter Consignee on Update screen");
		session.waitForCursor(4, 6, 5000);
		session.waitForChange(2000);
		session.writeAfterLabel("Cons", cons);
		//session.writeString(cons);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);


	}

	public final void enterConsName(String consName) throws Exception {
		logger.info("Enter Consignee Name on Update screen");
		session.waitForCursor(4, 14, 5000);
		session.writeString(consName);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterConsAddress(String consAddress) throws Exception {
		logger.info("Enter Consignee Address on Update screen");
		session.waitForCursor(4, 47, 5000);
		session.writeString(consAddress);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterConsCity(String consCity) throws Exception {
		logger.info("Enter Consignee City on Update screen");
		session.waitForCursor(5, 47, 5000);
		session.writeString(consCity);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterConsState(String consState) throws Exception {
		logger.info("Enter Consignee City on Update screen");
		session.waitForCursor(5, 68, 5000);
		session.writeString(consState);
		session.waitForChange(1000);
		// session.writeKey(Key.TAB);
		// session.waitForChange(1000);
	}

	public final void enterConsZip(String consZip) throws Exception {
		logger.info("Enter Consignee City on Update screen");
		session.waitForCursor(5, 71, 5000);
		session.writeString(consZip);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterPONum(String poNo) throws Exception {
		logger.info("Enter PO# on Update screen");
		session.waitForCursor(10, 6, 5000);
		session.waitForChange(1000);
		//session.writeString(poNo);

		session.writeAfterLabel("PO#", poNo);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForChange(2000);
	}

	public final void enterPuDrNum(String driverNo, String dNo) throws Exception {
		logger.info("Enter PU Dr# on Update screen");
		session.waitForCursor(10, 48, 5000);
		session.waitForChange(2000);
		session.writeAfterLabel("PUDr", driverNo);
		// session.waitForChange(2000);
		// session.writeKey(Key.TAB);
		session.waitForChange(2000);
		session.waitForCursor(10, 56, 5000);
		session.writeString(dNo);
		session.waitForChange(2000);
	}
	
	//Adding more flexibility, two method parameters, the tab allows the cursor to move over to the next input.
	public final void enterPuDrNum1(String driverNo, String dNo) throws Exception {
		logger.info("Enter PU Dr# on Update screen");
		session.waitForCursor(10, 48, 5000);
		session.waitForChange(2000);
		session.writeAfterLabel("PUDr", driverNo);
		session.waitForChange(2000);
		if(driverNo.length() <= 7) {
			session.writeKey(Key.TAB);
		}
		// session.waitForChange(2000);
		// session.waitForCursor(10, 56, 5000);
		session.writeString(dNo);
		session.waitForChange(2000);
	}

	public final void enterCubicFeet(String cubicFt) throws Exception {
		logger.info("Enter Cubic feet on Update screen");
		session.waitForCursor(13, 38, 5000);
		session.waitForChange(1000);
		session.writeAfterLabel("Cubic Feet", cubicFt);
		session.waitForChange(1000);
	}

	public final void enterPONumPopup(String poNo) throws Exception {
		logger.info("Enter PO# on Update screen");
		session.waitForCursor(9, 18, 5000);
		session.writeString(poNo);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}

	public final void enterCartTo(String cartTo) throws Exception {
		logger.info("Enter Cart To on Update Screen");
		session.waitForCursor(13, 38, 5000);
		session.waitForChange(1000);
		session.writeAfterLabel("Cart to", cartTo);
		session.waitForChange(1000);
	}

	public final void enterShip(String ship) throws Exception {
		logger.info("Enter Shipper on Update screen");
		session.waitForCursor(6, 6, 5000);
		session.writeAfterLabel("Ship", ship);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enter3Pt(String thirdParty) throws Exception {
		logger.info("Enter Third Party on Update screen");
		session.waitForCursor(8, 6, 5000);
		session.writeAfterLabel("3 pt", thirdParty);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}
	
	public void enterDTValue(String DT)
			throws JagacyException, InterruptedException {
		logger.info("Enter DT on Update screen");
		session.waitForChange(2000);	
		session.writePosition(0, 65, DT);
		session.waitForChange(1000);

}
	
	
	
	public final void enterThirdParty(String thirdParty) throws JagacyException {
		logger.info("Enter Third Party on Update screen");
		session.waitForCursor(8, 6, 5000);
		session.writeAfterLabel("3 pt", thirdParty);
		session.waitForChange(2000);
	}

	public String recordZipCode() throws JagacyException {
		logger.info("Capture Zip Code");
		session.waitForChange(2000);
		String zipCode = session.readPosition(5, 71, 7);
		System.out.println("Zip Code is " + zipCode);
		session.waitForChange(2000);
		return zipCode;
	}


	
	
	public void enterValuesToUpdateScreen(String TS, String PCS, String Terms, String Wgt, String Consinee, String shipper, String thirdParty, String PO, String driverNo,String dNo, String cFeet)
			throws JagacyException, InterruptedException {

		logger.info("enter values to update screen");
		logger.info("Enter TS on Update screen");
		
//		session.waitForCursor(2, 9, 2000);
//		session.writePosition(2, 9, TS);
		session.waitForChange(1000);
		session.writeAfterLabel("TS", TS);
		session.waitForChange(1000);

		logger.info("Enter PCS on Update screen");
//		session.waitForCursor(2, 15, 1000);
//		session.writePosition(2, 15, PCS);
		session.waitForChange(1000);
		session.writeAfterLabel("Pcs", PCS);
		session.waitForChange(1000);
		

		logger.info("Enter terms on Update screen");
//		session.waitForCursor(2, 27, 1000);
//		session.writePosition(2, 27, Terms);
		
		session.waitForChange(1000);
		session.writeAfterLabel("Terms", Terms);
		session.waitForChange(1000);

		logger.info("Enter wgt on Update screen");
//		session.waitForCursor(2, 35, 1000);
//		session.writePosition(2, 35, Wgt);
		
		session.waitForChange(1000);
		session.writeAfterLabel("Wgt", Wgt);
		session.waitForChange(1000);

		logger.info("Enter consignee on Update screen");
		session.waitForCursor(4, 6, 1000);
		session.writeAfterLabel("Cons", Consinee);
		session.waitForChange(3000);
		System.out.println(Consinee);

		session.waitForCursor(6, 6, 1000);
		session.writePosition(6, 6, shipper);
		session.waitForChange(3000);

		session.writePosition(8, 6, thirdParty);
		session.waitForChange(3000);
		System.out.println(thirdParty);

		logger.info("Enter PO# on Update screen");
		session.waitForCursor(10, 6, 1000);
		session.writeAfterLabel("PO#", PO);
		session.waitForChange(1000);

		logger.info("Enter PU Dr# on Update screen");
		session.waitForCursor(10, 48, 5000);
		session.waitForChange(2000);
		session.writeAfterLabel("PUDr", driverNo);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForCursor(10, 56, 5000);
		session.writeString(dNo);
		session.waitForChange(2000);

		logger.info("Enter cubic feet on Update screen");
		session.waitForCursor(13, 38, 1000);
		session.writeAfterLabel("Cubic Feet", cFeet);
		session.waitForChange(1000);
		session.waitForChange(1000);
		Thread.sleep(2000);
	}
	
	public void enterILTo(String IT)
			throws JagacyException, InterruptedException {

		logger.info("Enter IL to on Update screen");
		session.waitForChange(2000);	
		session.writePosition(18, 9, IT);
		session.waitForChange(1000);
}
	
	public void enterPro(String pro)
			throws JagacyException, InterruptedException {

		logger.info("Enter pro on Update screen");
		session.waitForChange(2000);	
		session.writePosition(18, 37, pro);
		session.waitForChange(1000);
}
	
	public void enterDate(String date)
			throws JagacyException, InterruptedException {

		logger.info("Enter pro on Update screen");
		session.waitForChange(2000);	
		session.writePosition(18, 58, date);
		session.waitForChange(1000);
}

	
	

	public void deleteDTValue() throws JagacyException {
		logger.info("Delete DT value");
		session.waitForCursor(0, 65, 2000);
		session.writeKey(Key.DELETE);
		session.writeKey(Key.DELETE);
		session.waitForChange(2000);
	}
	

	public final void enterDescOnPONumScreen(String txt) throws Exception { // I'm not sure what screen this is, but it's necessary for qz11581
		logger.info("Enter text to move past this screen");
		session.waitForCursor(9, 18, 5000);
		session.waitForChange(1000);
		session.writePosition(9, 18, txt);
		session.waitForChange(1000);
	}
	
	public void enterCons1(String cons) throws Exception {
		logger.info("Enter Consignee on Update screen");
		session.writePosition(4, 6, cons);
	}

	

	public void enterPOnum1(String poNo) throws Exception {
		logger.info("Enter PO# on Update screen");
		session.writePosition(10, 6, poNo);
	}
	
	public void pressEnter() throws JagacyException {
		logger.info("Press enter to go to the next update page");
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}

	public void enterConsigneeCode(String consigneeCode) throws JagacyException {
		logger.info("Enter consignee code");
		session.writePosition(4, 6, consigneeCode);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}
	
	public void enterBondShed(String value) throws JagacyException {
		logger.info("Inputting Bond Shed value");
		session.writePosition(20, 37, value); 
		session.waitForChange(1000); 
	}
	
	public void enterCustomsBroker(String broker) throws JagacyException {
		logger.info("Inputting Customs Broker");
		session.writePosition(20, 58, broker); 
		session.waitForChange(1000); 
	}
	
	public void enterCustomsInvoice(String customsInvoice) throws JagacyException {
		logger.info("Inputting yes or no for Customs Invoice");
		session.writePosition(21, 45, customsInvoice); 
		session.waitForChange(1000); 
	}
	
	public void enterCertOfOrigin(String coa) throws JagacyException {
		logger.info("Inputting yes or no for Cert of Origin");
		session.writePosition(21, 69, coa); 
		session.waitForChange(1000); 
	}
	
	public void inputUpdateScreen(String value) throws JagacyException {
		logger.info("Adding the value needed per the screen's requirement request: "+ value);
		session.writePosition(9, 18, value);
		session.waitForChange(1000); 
	}
}





