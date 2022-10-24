package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class RoadManifestEntryUpdateScreen {

	
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public RoadManifestEntryUpdateScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	public void enterSeal(String seal) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
		session.writePosition(4, 7, seal);
		session.waitForChange(2000);
	}
	
	public void enterFreightBillCanBeAdded(String option) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
		session.writePosition(4, 40, option);
		session.waitForChange(2000);
	}

	
	public void enterFB(String FreightBill) throws JagacyException {
		logger.info("Enter Freight Bill");
		session.waitForCursor(13, 2, 1000);
		session.writeCursor(13, 2);
		session.writePosition(13, 2, FreightBill);
		session.waitForChange(2000);
	}

	public String enterFreightBill(String bill) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
		session.writePosition(13, 2, bill);
		session.waitForChange(2000);
		return bill;
	}
	
	public void clickF1() throws JagacyException, InterruptedException {
		logger.info("Click F1");
		session.waitForChange(2000);
		session.writeKey(Key.PF1);
		session.wait(5000);
	}

	public void verifyRoadManifestEntryUpdateScreen() throws JagacyException{
        logger.info("verify Road Manifest Entry Update Screen");
        session.waitForChange(1000);	
        String expectedScreen = "Road manifest entry/update";
		
	     String actualScreen = session.readRow(0);
	    session.waitForChange(3000);
	 	 if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen + ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);
			 
		} else {
			
			fail(expectedScreen + ": Screen is NOT Displayed");
			 
		}
	     		
	}
	
}


