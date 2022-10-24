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

public class RobotAutomatedOperationsSolutionScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public RobotAutomatedOperationsSolutionScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyScreenTitle() throws JagacyException, InterruptedException {
		logger.info("Verify screen title");
		session.waitForChange(1000);
		assertTrue(session.readPosition(0, 22, 35).equals("Robot Automated Operations Solution"),
				"Could not verify screen title");
	}

	public void enterOption(String option) throws JagacyException {

		logger.info("Enter option");
		session.waitForCursor(20, 17, 5000);
		session.writeString(option);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
	}
}

