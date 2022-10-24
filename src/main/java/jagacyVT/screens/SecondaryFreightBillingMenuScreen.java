package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class SecondaryFreightBillingMenuScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public SecondaryFreightBillingMenuScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterValueOptionField(String option) throws JagacyException, InterruptedException {
		
		logger.info("enter option value ");
		session.waitForCursor(22, 9, 1000);
		session.waitForChange(1000);
		session.writeString(option);
		session.waitForChange(1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}

	public void pressF1() throws JagacyException {
		logger.info("Press F1 to go back to the Freight Billing Menu");
		session.writeKey(Key.PF1);
		session.waitForChange(1000);
	}

}
