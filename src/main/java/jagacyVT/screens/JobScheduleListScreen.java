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


public class JobScheduleListScreen {
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public JobScheduleListScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyScreenTitle() throws JagacyException, InterruptedException {
		logger.info("Verify screen title");
		session.waitForChange(1000);
		assertTrue(session.readPosition(0, 30, 17).equals("Job Schedule List"), "Could not verify screen title");
	}

	public void enterVerifyAndReleaseJob(String job) throws JagacyException {
		logger.info("Enter job");
		session.waitForCursor(4, 31, 5000);
		session.writeString(job);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		assertTrue(session.readPosition(8, 17, 10).trim().equals(job));
		if (session.readPosition(8, 5, 6).trim().equals("HOLD")) {
			logger.info("Release the job");
			session.waitForCursor(8, 1, 5000);
			session.writeString("R");
			session.waitForChange(1000);
			session.writeKey(Key.ENTER);
			session.waitForChange(2000);
			assertTrue(session.readPosition(8, 5, 6).trim().equals("EVERY"));
		}
		else {
			logger.info("Job does not need to be released");
		}
	}

	public void enterVerifyAndHoldJob(String job) throws JagacyException {
		logger.info("Enter job");
		session.waitForCursor(4, 31, 5000);
		session.writeString(job);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		assertTrue(session.readPosition(8, 17, 10).trim().equals(job));
		if (session.readPosition(8, 5, 6).trim().equals("EVERY")) {
			logger.info("Hold the job");
			session.waitForCursor(8, 1, 5000);
			session.writeString("H");
			session.waitForChange(1000);
			session.writeKey(Key.ENTER);
			session.waitForChange(2000);
			assertTrue(session.readPosition(8, 5, 6).trim().equals("HOLD"));
		}
		else {
			logger.info("Job does not need to be held");
		}
	}

	public void exit() throws JagacyException {
		logger.info("Press F3 to exit to the previous screen");
		session.writeKey(Key.PF3);
		session.waitForChange(2000);
	}

}
