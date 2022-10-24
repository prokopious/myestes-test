package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class PnDDriverLogSateEntryScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public PnDDriverLogSateEntryScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterOdometerInValue(String value) throws JagacyException {
		logger.info("enter odometer In value");
		session.waitForChange(2000);
		session.writePosition(5, 61, value);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);

	}

	public void enterState(String value) throws JagacyException, InterruptedException {

		logger.info("enter state value");

		session.waitForChange(1000);
		session.writePosition(11, 8, value);
		session.waitForChange(1000);

	}
	
	public void enterMiles(String value) throws JagacyException, InterruptedException {

		logger.info("enter miles value");

		session.waitForChange(1000);
		session.writePosition(11, 15, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);

	}
	
	public void enterF9Key() throws JagacyException, InterruptedException {

		logger.info("enter f9 key");

		session.waitForChange(1000);
	
	
		session.writeKey(Key.PF9);
		session.waitForChange(2000);
		
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
		
		

	}
	



}
