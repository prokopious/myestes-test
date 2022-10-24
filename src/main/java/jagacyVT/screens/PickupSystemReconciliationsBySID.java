/**
 * 
 */
package jagacyVT.screens;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class PickupSystemReconciliationsBySID {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public PickupSystemReconciliationsBySID(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
	}
	
	public boolean isCurrentScreen() throws JagacyException {
		logger.info("Pickup System Reconciliations By SID is current screen");
		String actual = session.readPosition(1, 15, 50);
		return actual.contains("Pickup System - Reconciliations by SID");
	}
	
	// Under construction
	public void selectLastPickupNumber() throws JagacyException, InterruptedException {
		logger.info("Verify and select last pickup number");
		String error = "Roll up or down past the first or last record in file.";
		while (!session.readPosition(23, 1, 54).equals(error)) {
			session.writeKey(Key.PAGE_DOWN);
			session.waitForChange(1000);
		}
		int row = 20;
		while (session.readPosition(row, 8, 10).trim().equals("") && row >= 5) {
			row -= 2;
		}
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
		session.writePosition(row - 3, 1, "x");
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}
	
	public void selectPickupNumber(String pickupNumber) throws JagacyException {
		logger.info("Verify and select first pickup number");
		boolean found = false;
		while (!found) {
			int row = 5;
			while (row <= 19) {
				if (session.readPosition(row + 1, 8, 10).equals(pickupNumber)
						|| session.readPosition(row, 36, 17).contains("None of the above")) {
					session.writePosition(row, 1, "x");
					session.waitForChange(2000);
					found = true;
					break;
				}
				row += 2;
				session.writeKey(Key.TAB);
				session.waitForChange(1000);
			}
			if (found) break;
			session.writeKey(Key.PAGE_DOWN);
			session.waitForChange(1000);
			// Jagacy will freeze after trying to page down the final page
			assertFalse(session.readPosition(22, 31, 54).contains("Roll up or down past the first or last record in file."));
		}
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}
}
