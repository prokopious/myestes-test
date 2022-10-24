package jagacyVT.screens;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;
import testBase.TestBase;

public class PickupRequestProcessing extends TestBase {

		private SessionVt session;
		private Loggable logger;
		private JagacyProperties props;
		private JagacyUtil util; 
		
		public PickupRequestProcessing(SessionVt s) throws JagacyException {
			this.session = s; 
			props = s.getProperties();
			logger=s.getLoggable();
			util = new JagacyUtil(session); 
			
		}
		
		public void validatePickupRequestNumber(String requestNumber, String option) throws JagacyException, InterruptedException {
			logger.info("Verify Pickup Request Number: "+ requestNumber);
			int i = 16; 
			String pickupNumber = session.readPosition(i, 3, 10); 
			while(!pickupNumber.equals(requestNumber)) { 
				//There are only 5 values that are shown and then there is a page down indicator, this is why 20 is set as the conditional statement if i is greater or equal to page down. 
				if(i > 20) {
					util.pressPageDown(); 
					i=16; 
				}
				pickupNumber = session.readPosition(i, 3, 10); 
				session.writeCursor(i, 1);
				i++; 	
			}
			session.writeString(option);
			util.pressEnter();  
		}
		
		public void enterSpecificLineOption(String option, Integer line) throws JagacyException {
			logger.info("Enter first line option");
			session.writePosition
			(line, 1, option);
			session.writeKey(Key.ENTER);
			session.waitForChange(1000);
		}
		
		public void rejectionReasonCode(String code, String reason) throws JagacyException, InterruptedException {
			logger.info("Enter The Rejection Reason Code");
			session.waitForChange(2000);
			session.writePosition(8, 32, code);
			session.waitForChange(2000);
			session.writePosition(11, 8, reason);
		}
		
		public void pressF3() throws JagacyException {
			logger.info("Press F3 to exit back to the Pickup System - Stop Processing");
			session.writeKey(Key.PF3);
			session.waitForChange(1000);
		}
		
		public void validateRejectionMessage(String pickupNumber) throws JagacyException {
			logger.info("Validate the message in the bottom of screen is visible: 'Reject request for Pickup "+ pickupNumber + " successful");
			testUtil.setHardWait(2500);
			String expected = "Reject request for Pickup "+ pickupNumber + " successful"; 
			String message = session.readPosition(23, 1, 47); 
			Assert.assertEquals(message, expected);
		}
		
		public void pressEnterKey() {
			logger.info("Enter Password");
			JagacyUtil.pressKeys(session, Key.ENTER, 1000);
		}
}
