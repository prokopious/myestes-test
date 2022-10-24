package jagacyVT.screens;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class ReserveFreightBillScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public ReserveFreightBillScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterNumberOfFB(String numOfFreightBill) throws JagacyException, InterruptedException {

		logger.info("enter number of FB");
		session.waitForChange(1000);
		session.writeString(numOfFreightBill);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		Thread.sleep(2000);

	}

	public void enterShipperCode(String sCode) throws JagacyException, InterruptedException {

		logger.info("enter Shipper Code ");
		session.waitForCursor(7, 40, 1000);
		session.waitForChange(1000);
		session.writeString(sCode);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		Thread.sleep(2000);

	}

	public String getFBNumber() throws JagacyException {
		logger.info("get freight bill numbers ");
		// String fbNum=session.readPosition(9,9,43).toString().substring(30,
		// 42).replaceAll("\\s+", "");
		session.waitForChange(1000);
		System.out.println(session.readRow(9).substring(16).trim());
		System.out.println(session.readRow(11).substring(16).trim());
		System.out.println(
				"FB Number is: " + session.readPosition(9, 9, 43).toString().substring(30, 42).replaceAll("\\s+", ""));
		session.waitForChange(1000);
		return session.readPosition(9, 9, 43).toString().substring(30, 42).replaceAll("\\s+", "");
	}

	public String getNum() throws JagacyException {
		logger.info("get numbers ");
		// String fbNum=session.readPosition(9,9,43).toString().substring(30,
		// 42).replaceAll("\\s+", "");
		session.waitForChange(1000);
		System.out.println(session.readRow(9).substring(16).trim());

		System.out.println("FB Number is: "
				+ session.readPosition(14, 14, 44).toString().substring(30, 42).replaceAll("\\s+", ""));
		session.waitForChange(1000);
		return session.readPosition(9, 9, 44).toString().substring(14, 43).replaceAll("\\s+", "");
	}
//	Ltl38MasterMenuScreen Ltl38MasterMenuScreen=new Ltl38MasterMenuScreen();

	
	public void verifyScreenTitle() throws JagacyException, InterruptedException {
		logger.info("Verify Screen Title");
		String title = session.readPosition(1, 24, 33);
		Thread.sleep(1000);
		System.out.println("Screen Title is " + title);
		Assert.assertTrue(title.equalsIgnoreCase("Reserve a String Of Freight Bills"));
	}



	public void enterBillsToReserve(String numOfFreightBill) throws JagacyException, InterruptedException {
		logger.info("Enter Bills to Reserve");
		session.waitForCursor(5, 40, 1000);
		session.writeString(numOfFreightBill);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}

	public String recordFBNumber() throws JagacyException, InterruptedException {
		logger.info("Capture Freight Bill Numer");
		session.waitForChange(2000);
		String billNo = session.readPosition(9, 44, 7);
		System.out.println("Bill Number is " + billNo);
		session.waitForChange(2000);
		return billNo;
	}
	
	
	public void pressF3() throws JagacyException {
		logger.info("Press F3 to return to Freight Billing Menu");
		session.writeKey(Key.PF3);
		session.waitForChange(1000);
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

}
