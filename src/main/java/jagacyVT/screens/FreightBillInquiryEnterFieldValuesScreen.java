package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class FreightBillInquiryEnterFieldValuesScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public FreightBillInquiryEnterFieldValuesScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterValueFreightBill(String fb) throws Exception {
		
		logger.info("enter freight bill ");
		session.waitForCursor(9, 41, 1000);
		session.waitForChange(1000);
		session.writeString(fb);
		session.waitForChange(1000);
	}
	
	public void enterValueEControlID(String eCntrlID) throws Exception {
		
		logger.info("enter eControl ID ");
		session.writeCursor(12, 41);
		session.waitForCursor(12, 41, 1000);
		session.waitForChange(1000);
		session.writeString(eCntrlID);
		session.waitForChange(1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}
	
	
	//Enter FB Number for Screen 'Freight Bill Inquiry' - This is needed after Freight Menu Options,
		public void enterFreightBillNum(String terminal, String freightBillNum) throws Exception {
			logger.info("Enter Freight Bill Number in 'Freight Bill Inquiry' Screen"); 
			session.waitForCursor(4, 41, 1000); 
			session.waitForChange(1000); 
			session.writeString(terminal);
			session.waitForChange(1000); 
			session.writeString(freightBillNum);
			session.waitForChange(1000); 
			session.writeKey(Key.ENTER); 
			session.waitForChange(3000);

}}
