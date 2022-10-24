package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class SubsetRequestServiceInfoScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public SubsetRequestServiceInfoScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifySubsetRequestInfoScreen() throws JagacyException {

		logger.info("verify Screen is displayed");
		session.waitForChange(4000);	
		String expectedScreen = "Subset Request Service Information";
		String actualScreen = session.readRow(0);

		if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen + ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);

		} else {

			fail(expectedScreen + ": Screen is NOT Displayed");

		}

	}

	public void clearUserIdField2() throws JagacyException {
		logger.info("clear user id field");
		session.waitForChange(1000);
		// session.waitForCursor(18, 16, 1000);
		// session.writeCursor(18, 26);
		// session.writeString(" ");

		session.writePosition(18, 26, "       ");
		session.waitForChange(1000);
		session.writePosition(18, 26, "*ALL");
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);

	}

	public void clearUserIdField() throws Exception {
		logger.info("clear user id field");
		session.waitForChange(1000);
		session.writeCursor(18, 26);
		for (int i = 0; i < 10; i++) {
			session.writeKey(Key.DELETE);
			Thread.sleep(200);
		}
		session.writeString("");
		session.waitForChange(1000);
		session.writeCursor(18, 26);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
	}

}
