/**
 * 
 */
package jagacyVT.screens;



import static org.testng.Assert.assertTrue;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class PickupRequestMaintenanceScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public PickupRequestMaintenanceScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
	}

	public void enterShipperNumber(String shipperNumber) throws JagacyException {
		logger.info("Enter shipper number");
		session.writePosition(4, 2, shipperNumber);
		session.waitForChange(1000);
	}
	
	public void enterAction(String action) throws JagacyException {
		logger.info("Enter action");
		session.writePosition(7, 64, action);
		session.waitForChange(1000);
	}
	
	public void enterPieces(String pieces) throws JagacyException {
		logger.info("Enter pieces");
		session.writePosition(8, 16, pieces);
		session.waitForChange(1000);
	}
	
	public void enterWeight(String weight) throws JagacyException {
		logger.info("Enter weight");
		session.writePosition(8, 38, weight);
		session.waitForChange(1000);
	}
	
	public void enterRqstBy(String rqstBy) throws JagacyException {
		logger.info("Enter Rqst by");
		session.writePosition(12, 49, rqstBy);
		session.waitForChange(1000);
	}
	
	public void pressEnter() throws JagacyException {
		logger.info("Press enter");
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}

	public String recordPickupNumber() throws JagacyException {
		// TODO Auto-generated method stub 24 1 33
		logger.info("Record Pickup Number");
		String updateMessage = session.readPosition(23, 1, 33);
		String pickupNumber = updateMessage.split(" ")[2];
		logger.info("Pickup number: [" + pickupNumber + "]");
		return pickupNumber;
	}

	public void pressF11() throws JagacyException {
		logger.info("Press F11 to Finalize");
		session.writeKey(Key.PF11);
		session.waitForChange(1000);
	}

	public void pressF12() throws JagacyException {
		logger.info("Press F12 for previous page");
		session.writeKey(Key.PF12);
		session.waitForChange(1000);
	}
	
	public String recordPickupNumber1() throws JagacyException {
		logger.info("Record Pickup Number");
		session.waitForPosition(1, 27, "Pickup Request Maintenance", 1000);  
		String pickupNumber = session.readPosition(23, 16, 10);
		logger.info("Pickup number: [" + pickupNumber + "]");
		return pickupNumber;
	}
	
	public String recordStopNumber() throws JagacyException {
		logger.info("Record Stop Number");
		String stopNumber = session.readPosition(19, 15, 10); 
		logger.info("Stop number: [" + stopNumber + "]");
		return stopNumber;
	}
}


