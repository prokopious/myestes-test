package jagacyVT.screens;


import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class EnterPercentageQuarterTrailerScreen {
	
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public EnterPercentageQuarterTrailerScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterFloorAndHeightForAllSections(String num) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
		session.writePosition(6, 30, num);	
		
		session.waitForChange(2000);
		session.writePosition(6, 44, num);
	
		session.waitForChange(2000);
		session.writePosition(9, 30, num);
		
		session.waitForChange(2000);
		session.writePosition(9, 44, num);
	
		session.waitForChange(2000);
		session.writePosition(14, 30, num);
	
		session.waitForChange(2000);
		session.writePosition(14, 44, num);
	
		session.waitForChange(2000);
		session.writePosition(17, 30, num);
		
		session.waitForChange(2000);
		session.writePosition(17, 44, num);
		session.waitForChange(2000);
		
	}

	public void enterSectionAFloor(String val) throws JagacyException {
		logger.info("Enter Floor% in Section A");
		session.waitForChange(2000);
		session.writeCursor(6, 31);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionAHeight(String val) throws JagacyException {
		logger.info("Enter Height% in Section A");
		session.waitForChange(2000);
		session.writeCursor(6, 45);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionBFloor(String val) throws JagacyException {
		logger.info("Enter Floor% in Section B");
		session.waitForChange(2000);
		session.writeCursor(9, 31);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionBHeight(String val) throws JagacyException {
		logger.info("Enter Height% in Section B");
		session.waitForChange(2000);
		session.writeCursor(9, 45);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionCFloor(String val) throws JagacyException {
		logger.info("Enter Floor% in Section C");
		session.waitForChange(2000);
		session.writeCursor(14, 31);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionCHeight(String val) throws JagacyException {
		logger.info("Enter Height% in Section C");
		session.waitForChange(2000);
		session.writeCursor(14, 45);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionDFloor(String val) throws JagacyException {
		logger.info("Enter Floor% in Section D");
		session.waitForChange(2000);
		session.writeCursor(17, 31);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void enterSectionDHeight(String val) throws JagacyException {
		logger.info("Enter Height% in Section D");
		session.waitForChange(2000);
		session.writeCursor(17, 45);
		session.writeString(val);
		session.waitForChange(2000);
	}
	
	public void clickF10() throws JagacyException, InterruptedException {
		logger.info("Click F10");
		session.waitForChange(2000);
		session.writeKey(Key.PF10);
		session.waitForChange(3000);
	}
	
	public void clickF1() throws JagacyException, InterruptedException {
		logger.info("Click F1");
		session.waitForChange(2000);
		session.writeKey(Key.PF1);
		session.waitForChange(3000);
	}
}

