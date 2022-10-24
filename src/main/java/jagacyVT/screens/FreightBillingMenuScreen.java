package jagacyVT.screens;

import static org.testng.Assert.assertTrue;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class FreightBillingMenuScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public FreightBillingMenuScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterValueOptionField(String option) throws JagacyException, InterruptedException {

		logger.info("Enter option value ");
		session.waitForCursor(22, 9, 1000);
		session.waitForChange(1000);
		session.writeString(option);
		session.waitForChange(1000);
//		session.writeKey(Key.ENTER);
//		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public void enterValueUserField(String user) throws JagacyException, InterruptedException {

		logger.info("Enter User value ");
		session.waitForCursor(22, 22, 1000);
		session.waitForChange(1000);
		session.writeString(user);
		session.waitForChange(1000);
		// Thread.sleep(2000);

	}

	public void enterTerminalNumber(String terNum) throws JagacyException, InterruptedException {

		logger.info("Enter terminal number");
		session.waitForCursor(22, 48, 1000);
		session.waitForChange(1000);
		session.writeString(terNum);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);

	}

	public void enterTabKey() throws JagacyException {
		logger.info("press tab key");
		session.writeKey(Key.TAB);
		session.waitForChange(1000);

	}

	public void enterFreightBillNumber(String FB) throws JagacyException, InterruptedException {

		logger.info("Enter Terminal Number");
		session.waitForCursor(22, 48, 1000);
		session.waitForChange(1000);
		session.writeString(FB);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		System.out.println(FB);

	}

	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}

	public final void enterFreightBillMenuOption(String option, String user, String terminal)
			throws JagacyException, InterruptedException {
		logger.info("Enter FreightBill option and Terminal number");
		session.waitForCursor(22, 8, 5000);
		session.writeString(option);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
		session.waitForCursor(22, 22, 5000);
		session.writeString(user);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForCursor(22, 48, 5000);
		session.writeString(terminal);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
	}

	public final void enterFreightBill(String ot, String bill) throws Exception {
		logger.info("Enter Option on Master Menu");
		session.waitForCursor(9, 40, 5000);
		session.writeString(ot);
		session.waitForChange(1000);
		session.waitForCursor(9, 45, 5000);
		session.writeString(bill);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
	}

	public void verifyPROIsInRStatus() throws JagacyException {
		logger.info("Verify the PRO  is in R/Reserve Status");
		session.waitForChange(2000);
		String status = "R";
		String pro = session.readPosition(0, 54, 1);
		System.out.println("Status is " + pro);
		Assert.assertTrue(pro.equals(status));
		session.waitForChange(2000);
	}

	public void verifyShipperCode(String code) throws JagacyException {
		logger.info("Verify the Shipper Code is " + code);
		session.waitForChange(2000);
		String actual = session.readPosition(12, 1, 7);
		System.out.println("Actual code is " + actual);
		Assert.assertTrue(actual.equals(code));
		session.waitForChange(2000);
	}

	public void verifyWeight(String weight) throws JagacyException {
		logger.info("Verify the Weight is " + weight);
		session.waitForChange(2000);
		String actual = session.readPosition(17, 56, 7);
		System.out.println("Actual weight is " + actual);
		Assert.assertTrue(actual.contains(weight));
		session.waitForChange(2000);
	}

	public void verifyTotal(String total) throws JagacyException {
		logger.info("Verify the Total is " + total);
		session.waitForChange(2000);
		String actual = session.readPosition(8, 70, 9);
		System.out.println("Actual total is " + total);
		Assert.assertTrue(actual.contains(total));
		session.waitForChange(2000);
	}

	public void verifyFRevAndERevEqualTotal(String total) throws JagacyException, ParseException {
		logger.info("Verify F.Rev + E.Rev = Total");
		session.waitForChange(2000);

		total = total.replaceAll(",", "");
		Double totalDouble = Double.parseDouble(total);

		String fRev = session.readPosition(5, 71, 8);
		String eRev = session.readPosition(7, 71, 6);

		fRev = fRev.replaceAll(",", "");
		fRev = fRev.replaceAll(" ", "");
		Double fRevDouble = Double.parseDouble(fRev);

		eRev = eRev.replaceAll(",", "");
		eRev = eRev.replaceAll(" ", "");
		Double eRevDouble = Double.parseDouble(eRev);

		double actualTotal = fRevDouble + eRevDouble;
		System.out.println("fRev: " + fRevDouble + " + " + "eRev: " + eRevDouble + " = total: " + actualTotal);
		Assert.assertTrue(actualTotal == totalDouble);
		session.waitForChange(2000);
	}

	public void verifyConsigneeCityStateZip(String city, String state, String zip) throws JagacyException {
		logger.info("Verify the Consignee city is " + city);
		session.waitForChange(2000);
		String actual = session.readPosition(10, 43, 30);
		System.out.println("Actual Consignee City/State/Zip is " + actual);
		Assert.assertTrue(actual.contains(city));
		session.waitForChange(2000);
		logger.info("Verify the Consignee state is " + state);
		Assert.assertTrue(actual.contains(state));
		session.waitForChange(2000);
		logger.info("Verify the Consignee zip code is " + zip);
		Assert.assertTrue(actual.contains(zip));
		session.waitForChange(2000);
	}

	public void verifyShipperCityStateZip(String city, String state, String zip) throws JagacyException {
		logger.info("Verify the Shipper city is " + city);
		session.waitForChange(2000);
		String actual = session.readPosition(12, 43, 30);
		System.out.println("Actual Shipper City/State/Zip is " + actual);
		Assert.assertTrue(actual.contains(city));
		session.waitForChange(2000);
		logger.info("Verify the Shipper state is " + state);
		Assert.assertTrue(actual.contains(state));
		session.waitForChange(2000);
		logger.info("Verify the Shipper zip code is " + zip);
		Assert.assertTrue(actual.contains(zip));
		session.waitForChange(2000);
	}

	public void verifyConsigneeAddressLine1(String address) throws JagacyException {
		logger.info("Verify the Consignee address line 1 is " + address);
		session.waitForChange(2000);
		String actual = session.readPosition(9, 43, 30);
		System.out.println("Actual Consignee address line 1 is " + address);
		Assert.assertTrue(actual.contains(address));
		session.waitForChange(2000);
	}

	public void verifyConsigneeAddressLine2(String address) throws JagacyException {
		logger.info("Verify the Consignee address line 2 is " + address);
		session.waitForChange(2000);
		String actual = session.readPosition(10, 11, 30);
		System.out.println("Actual Consignee address line 2 is " + address);
		Assert.assertTrue(actual.contains(address));
		session.waitForChange(2000);
	}

	public void verifyShipperAddressLine1(String address) throws JagacyException {
		logger.info("Verify the Shipper address line 1 is " + address);
		session.waitForChange(2000);
		String actual = session.readPosition(11, 43, 30);
		System.out.println("Actual Shipper address line 1 is " + address);
		Assert.assertTrue(actual.contains(address));
		session.waitForChange(2000);
	}

	public void verifyShipperAddressLine2(String address) throws JagacyException {
		logger.info("Verify the Shipper address line 2 is " + address);
		session.waitForChange(2000);
		String actual = session.readPosition(12, 11, 30);
		System.out.println("Actual Shipper address line 2 is " + address);
		Assert.assertTrue(actual.contains(address));
		session.waitForChange(2000);
	}

	public void verifyQuantity(String quantity) throws JagacyException {
		logger.info("Verify the quantity is " + quantity);
		session.waitForChange(2000);
		String actual = session.readPosition(17, 3, 3);
		actual = actual.replaceAll(" ", "");
		System.out.println("Actual quantity is " + quantity);
		Assert.assertTrue(actual.equals(quantity));
		session.waitForChange(2000);
	}

	public void verifyDescription(String description) throws JagacyException {
		logger.info("Verify the description is " + description);
		session.waitForChange(2000);
		String actual = session.readPosition(17, 19, 35);
		System.out.println("Actual description is " + description);
		Assert.assertTrue(actual.equals(description));
		session.waitForChange(2000);
	}

	public void verifyPONumber(String poNum) throws JagacyException {
		logger.info("Verify the PO Number is " + poNum);
		session.waitForChange(2000);
		String actual = session.readPosition(15, 4, 15);
		System.out.println("Actual description is " + poNum);
		Assert.assertTrue(actual.contains(poNum));
		session.waitForChange(2000);
	}

	public void verifyDate(String date) throws JagacyException {
		logger.info("Verify the Date is " + date);
		try {
			session.waitForChange(2000);
			String actual = session.readPosition(4, 51, 8);
//			actual = actual.replaceAll(" ", ""); -changed to below line
			actual = actual.replaceAll(" ", "0");   //newly added
			System.out.println("Actual date is " + actual);
			Assert.assertTrue(actual.contains(date));
		} catch (JagacyException e) {
			e.printStackTrace();
		}
		session.waitForChange(2000);
	}

	public void verifyCommodityType(String type) throws JagacyException { // field is called "PC" on freight bill
		logger.info("Verify the commodity type is " + type);
		session.waitForChange(2000);
		String actual = session.readPosition(17, 16, 2);
		actual = actual.replaceAll(" ", "");
		System.out.println("Actual commodity type is " + type);
		Assert.assertTrue(actual.equals(type));
		session.waitForChange(2000);
	}

	public void verifyTerms(String terms) throws JagacyException {
		logger.info("Verify the terms are " + terms);
		session.waitForChange(2000);
		String actual = session.readPosition(3, 33, 4);
		actual = actual.replaceAll(" ", "");
		System.out.println("Actual terms are " + terms);
		Assert.assertTrue(actual.equals(terms));
		session.waitForChange(2000);
	}

	public void verifyQuoteNumber(String quote) throws JagacyException {
		logger.info("Verify the quote number is " + quote);
		session.waitForChange(2000);
		String actual = session.readPosition(13, 71, 7);
		System.out.println("Actual quote number is " + quote);
		Assert.assertTrue(actual.equals(quote));
		session.waitForChange(2000);
	}

	public void enterValueFreightBill(String fb) throws Exception {

		logger.info("enter freight bill ");
		session.waitForCursor(9, 41, 1000);
		session.waitForChange(1000);
		session.writeString(fb);
		session.waitForChange(1000);
	}

	public void enterValueEControlID(String eCntrlID) throws Exception {

		logger.info("enter eControl ID ");
		session.writeCursor(12, 41);
		session.waitForCursor(12, 41, 1000);
		session.waitForChange(1000);
		session.writeString(eCntrlID);
		session.waitForChange(1000);
	}

	public void enterMenuOptionForThreeDigits(String option, String user, String terminal) throws Exception {
		session.waitForCursor(22, 8, 5000);
		session.writePosition(22, 9, option);
		session.waitForChange(1000);
		session.writePosition(22, 22, user);
		session.waitForChange(1000);
		session.writePosition(22, 48, terminal);
		session.waitForChange(1000);
	}

	public void verifyScreenTitle() throws JagacyException, InterruptedException {
		logger.info("Verify Screen Title");
		boolean val = false;
		int i = 1;

		do {
			String title = session.readPosition(0, 11, 18);
			String titleMsg = session.readPosition(0, 30, 20);
			if (titleMsg.contains("Estes Express Lines")) {
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

	public void selectShipper() throws JagacyException, InterruptedException, Exception{
		String titleMsg = session.readPosition(0, 30, 20);
		System.out.print(titleMsg);
		if (titleMsg.contains("Estes Express Lines")) {
			logger.info("Select Shipper with X");
			String actual;
			int i = 5;
			int j = 0;
			JagacyUtil jagacyUtil = new JagacyUtil(session);
			boolean val = false;
			do {
				actual = session.readPosition(i, 36, 20);
				if (actual.contains("None of the above")) {
					System.out.println("shipper found");
					session.writeCursor(i, 1);
					session.writePosition(i, 1, "X");
					Thread.sleep(5000);
					jagacyUtil.pressEnter();
					Thread.sleep(5000);
					val = true;
					Thread.sleep(5000);
					break;
				}
				if ((i == 18) && (!session.readPosition(19, 73, 6).contains("Bottom"))) {
					System.out.println("Next Page");
					jagacyUtil.pressPageDown();
					i = 4;

				} else if ((i == 18) && (session.readPosition(19, 73, 6).contains("Bottom"))) {
					System.out.println("Reached last row!!!");
					break;
				} else if ((i == 18) && (j == 30)) {
					System.out.println("Searched Maximum pages, Trailer not found");
					break;
				}
				i++;
				j++;
			} while (!val);
		}
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
	
		
	}

	public void pressF1() throws JagacyException {
		logger.info("Press F1 to return to the master menu");
		session.writeKey(Key.PF1);
		session.waitForChange(1000);
	}

	public void verifyFreightBillUpdated(String otPro) throws JagacyException {
		String actual = session.readPosition(7, 38, 12);
		actual = actual.replaceAll("\\s", "").trim();
		assertTrue(otPro.equals(actual), "Expected: [" + otPro + "], Actual: [" + actual + "]");
	}

	public void verifyBOL(String bol) throws JagacyException {
		logger.info("Verify the BOL  is : " + bol);
		session.waitForChange(2000);
		String actual = session.readPosition(4, 4, 10);
		System.out.println("Actual quote number is " + actual);
		Assert.assertTrue(actual.trim().contains(bol));
		session.waitForChange(2000);
	}

	public void verifyPieces(String pieces) throws JagacyException {
		logger.info("Verify the pieces are " + pieces);
		session.waitForChange(2000);
		String actual = session.readPosition(3, 21, 4);
		System.out.println("Actual pieces are " + actual);
		Assert.assertTrue(actual.trim().contains(pieces));
		session.waitForChange(2000);
	}

	public void verifyDestinationTerminal(String dt) throws JagacyException {
		logger.info("Verify the destination terminal is " + dt);
		session.waitForChange(2000);
		String actual = session.readPosition(3, 4, 3);
		System.out.println("Actual destination terminal is " + actual);
		Assert.assertTrue(actual.trim().contains(dt));
		session.waitForChange(2000);
	}

	public void verifyShipperName(String name) throws JagacyException {
		logger.info("Verify the Shipper name is " + name);
		session.waitForChange(2000);
		String actual = session.readPosition(11, 11, 17);
		System.out.println("Actual Shipper name is " + actual);
		Assert.assertTrue(actual.trim().contains(name));
		session.waitForChange(2000);
	}

	public void verifyConsigneeName(String name) throws JagacyException {
		logger.info("Verify the Consignee name is " + name);
		session.waitForChange(2000);
		String actual = session.readPosition(9, 11, 25);
		System.out.println("Actual Consignee name is " + actual);
		Assert.assertTrue(actual.trim().contains(name));
		session.waitForChange(2000);
	}

}
