/**
 * 
 */
package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class FreightBillInquiryScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public FreightBillInquiryScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
	}
	
	public void enterFBNum(String ter, String num) throws JagacyException, Exception{
		session.waitForCursor(4, 41, 5000);
		session.writeString(ter);
		session.waitForChange(1000);
		session.writeString(num);
		session.waitForChange(1000);
	}

	public void pressEnterKey() throws JagacyException {
		logger.info("press Enter key");
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		
	}



}
