package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class InformationCategoryScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public InformationCategoryScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterValueOptions(String option) throws JagacyException, InterruptedException {
		
		logger.info("enter Information category option ");
		session.writeCursor(6, 24);
		session.waitForCursor(6, 24, 1000);
		session.waitForChange(1000);
		session.writeString(option);
		session.waitForChange(1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}
	
}
