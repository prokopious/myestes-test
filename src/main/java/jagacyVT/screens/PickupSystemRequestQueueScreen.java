package jagacyVT.screens;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;
import util.TestUtil;

public class PickupSystemRequestQueueScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public PickupSystemRequestQueueScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterValueRequestNumber(String reqNum) throws JagacyException, InterruptedException {
		
		logger.info("enter request number ");
		session.writeCursor(10, 33);
		session.waitForCursor(10, 33, 1000);
		session.waitForChange(1000);
		session.writeString(reqNum);
		session.waitForChange(1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}
	
	public void enterValueOptions(String option) throws JagacyException, InterruptedException {
		
		logger.info("enter option number ");
		session.writeCursor(11, 1);
		session.waitForCursor(11, 1, 1000);
		session.waitForChange(1000);
		session.writeString(option);
		session.waitForChange(1000);
	}
	
	public void verifyPickupRequestAcceptedMessage() throws Exception {
		
		logger.info("Verify Pickup Request Accepted message");
		session.waitForChange(3000);	
		String title = JagacyUtil.readLineFromScreen(session, 23);
		
		if(title.contains("Pickup Request")) {
			JagacyUtil.pressKeys(session, Key.ENTER, 1000);
		}
	}
	
	public void pressF5Key() {
		
		logger.info("Enter F5 key");
		JagacyUtil.pressKeys(session, Key.PF5, 1000);
	}
	
	public void verifyPickupStatusCode(String expStatus) throws Exception {
		
		logger.info("Verify Pickup Status Code");
		session.waitForChange(3000);	
		String status = session.readPosition(11, 78, 1);
		System.out.println("Status = "+status);
		Assert.assertEquals(status, expStatus);
	}

	public void pressF6Key() throws JagacyException {
		session.writeKey(Key.PF6);
		session.waitForChange(1000);
	}

	public void pressF3() throws JagacyException {
		logger.info("Press F3 to exit back to the Secondary Freight Billing Menu");
		session.writeKey(Key.PF3);
		session.waitForChange(1000);
	}
	
	public void enterRequestNumber(String requestNumber) throws JagacyException {
		logger.info("Enter Request#");
		session.writePosition(10, 33, requestNumber);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
	}

	public void verifyFirstLineRequestNumber(String requestNumber) throws JagacyException {
		logger.info("Verify first line request number");
		String actual = session.readPosition(11, 33, 10);
		assertTrue(requestNumber.equals(actual), "Exepcted: [" + requestNumber + "], Actual: [" + actual + "]");
	}
	
	public void enterFirstLineOption(String option) throws JagacyException {
		logger.info("Enter first line option");
		session.writePosition
		(11, 1, option);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}
	
	public void enterShipperAccount(String accountNumber) throws JagacyException {
		logger.info("Enter Shipper Account Number");
		session.writeCursor(10, 3);
		session.waitForCursor(10, 3, 1000);
		session.waitForChange(1000);
		session.writeString(accountNumber);
		session.waitForChange(1000);
	}
	
	/*Validating that todays date shows for this specific account within the Pickup Request - Stop Processing screen. Also, provides some flexibility if there are more than 
	 * one pickup/stop number available for a specific account number then it returns uniquely todays row and uses that output as an input for entering the specific line option
	 * for the next method. -> Please see method: 'enterSpecificLineOption'
	 */
	public Integer validateStopDate(String accountNumber){
		logger.info("Todays date is the date that I am validating that there was an update made to the shipper's account");
		TestUtil testUtil = new TestUtil(); 
		String today = testUtil.getTodayDateByFormat("MM/dd/yy"); 
		int i = 11; 
		try {
			while(session.readPosition(i, 3, 7).equals(accountNumber)) {
				String stopDate = session.readPosition(11, 33, 8);
				System.out.println(stopDate); 
				if(today.equals(stopDate)) {
					return i; 
				}else {	
					i++; 
				}
			}
		} catch (JagacyException e) {
			logger.info("Breaking test as the current stop date should be todays date upon validating "+ accountNumber + "'s updates for a pickup request"); 
		}
		return null;  
	}
	
	public void enterSpecificLineOption(String option, Integer line) throws JagacyException {
		logger.info("Enter first line option");
		session.writePosition
		(line, 1, option);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}
	
	public void enterStopNumber(String stopNumber) throws JagacyException {
		logger.info("Enter Stop Number");
		session.writeCursor(10, 22);
		session.waitForCursor(10, 22, 1000);
		session.waitForChange(1000);
		session.writeString(stopNumber);
		session.waitForChange(1000);
	}

}


