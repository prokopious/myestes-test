package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class DriversLogEntryScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public DriversLogEntryScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterTimeOut(String value) throws JagacyException {
		logger.info("enter terminal time Out value");
		session.waitForChange(2000);
		session.writePosition(11, 7, value);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);

	}

	public void enterTimeIn(String value) throws JagacyException, InterruptedException {

		logger.info("enter terminal time In value");

		session.waitForChange(1000);
		session.writePosition(11, 20, value);
		session.waitForChange(1000);

	}

	public void enterMiles(String value) throws JagacyException, InterruptedException {

		logger.info("enter miles");

		session.waitForChange(1000);
		session.writePosition(11, 65, value);
		session.waitForChange(1000);

	}

	public void enterNumberOfStops(String value) throws JagacyException, InterruptedException {

		logger.info("enter number of stops");

		session.waitForChange(1000);
		session.writePosition(15, 19, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);

	}

}
