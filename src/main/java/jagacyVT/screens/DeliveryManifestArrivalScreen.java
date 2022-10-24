package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class DeliveryManifestArrivalScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public DeliveryManifestArrivalScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterValueForProUpdate(String value) throws JagacyException {
		logger.info("enter pro value update value");
		session.waitForChange(2000);
		session.writePosition(12, 33, value);
		session.waitForChange(1000);
		
		
		
	}

public void enterValueSorA(String value) throws JagacyException, InterruptedException {
	
	logger.info("enter value A");

	session.waitForChange(1000);
	session.writePosition(15, 19, value);
	session.waitForChange(1000);
	
    session.writeKey(Key.ENTER);
    
    session.waitForChange(2000);
	

}

}
