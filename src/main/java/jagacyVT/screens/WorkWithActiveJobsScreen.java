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

public class WorkWithActiveJobsScreen {
	
	private SessionVt session;
	private JagacyUtil jagacyUtil;
	public Loggable logger;
	public JagacyProperties props;

	public WorkWithActiveJobsScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
		jagacyUtil = new JagacyUtil(session);
	}

	public void verifyJobIsRunningWithStatus(String jobName, String jobStatus) throws JagacyException {
		jagacyUtil.pressF7();
		session.waitForCursor(4, 31, 1000);
		session.writeString(jobName);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
		assertTrue(session.readPosition(9, 8, 9).equals(jobName));
		assertTrue(session.readPosition(9, 63, 4).equals(jobStatus));
	}

}

