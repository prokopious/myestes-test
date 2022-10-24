package jagacyVT.screens;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

import com.jagacy.SessionVt;

public class IBMMainMenuScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public IBMMainMenuScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyIBMMainMenuScreen() throws JagacyException {

		logger.info("verify Screen is displayed");
		session.waitForChange(3000);
		
		String expectedScreen = "IBM i Main Menu";
		String actualScreen = session.readRow(0);

		if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen + ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);

		} 
		else if(actualScreen.contains("Display Program")){
			System.out.println("Display message "+actualScreen);
			session.writeKey(Key.ENTER);
			session.waitForChange(1000);
		}
		else {

			fail(expectedScreen + ": Screen is NOT Displayed");

		}

	}

	public void enterValueToComandLineField(String value) throws Exception {

		logger.info("enter value to the comand line field");
		session.waitForCursor(19, 6, 1000);
		session.waitForChange(2000);
		/*
		 * if (session.readRow(0).indexOf("MAIN") != -1) {
		 * logger.fatal("Incorrect Main Screen"); assertFalse(true, "Step Failed..."); }
		 */
		session.writeString(value);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		Thread.sleep(5000);
	}

}
