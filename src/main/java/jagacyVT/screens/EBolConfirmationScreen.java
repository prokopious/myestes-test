package jagacyVT.screens;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class EBolConfirmationScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public EBolConfirmationScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void verifyEControlBillID(String billID) throws Exception {
		
		logger.info("Verify eControl Bill id");
		session.writeCursor(4, 50);
		session.waitForCursor(4, 50, 1000);
		String eControlID = session.readPosition(4,  50, 8);
		Assert.assertTrue(eControlID.contains(billID));
	}
	
	public void pressF10Key() {
		logger.info("Enter F10");
		JagacyUtil.pressKeys(session, Key.PF10, 1000);
	}
}
