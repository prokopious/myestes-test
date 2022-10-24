package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class GuidelinesForHazardousMaterialsInquiryScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public GuidelinesForHazardousMaterialsInquiryScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger=s.getLoggable();
	}
	
	public final void selectUnCode(String option) throws Exception {
		 logger.info("Select UN code");
		 session.waitForChange(3000);
		 session.writeCursor(8, 2);
		 session.writePosition(8, 2, option);
		 session.waitForChange(1000);
		 session.writeKey(Key.ENTER);
		 session.waitForChange(3000);
		 session.waitForPosition(18, 67, "ZONE", 3000);
		 
	}
}