package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class DeliveryManifestEntryUpdateScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public DeliveryManifestEntryUpdateScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
		
	
public void enterValueInTLField(String value1, String value2, String value3, String value4) throws JagacyException, InterruptedException {
		
		logger.info("enter option value ");
		session.waitForCursor(4, 13, 1000);
		session.waitForChange(3000);
		session.writeString(value1);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		
		session.waitForCursor(5, 28, 1000);
		session.writePosition(5, 28, value2);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		
		session.waitForCursor(3, 53, 1000);
		session.writePosition(3, 53, value3);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		
		session.waitForChange(1000);
		session.waitForCursor(8, 27, 1000);
		session.writePosition(8, 27, value4);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		
		
	}

public void replaceLTRWithMTY(String value) throws JagacyException, InterruptedException {
	
	logger.info("enter User value ");
	session.writePosition(5, 28, value);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	session.waitForChange(1000);
	
}


public void enterPosToValue(String value) throws JagacyException, InterruptedException {
	
	logger.info("enter User value ");
	session.waitForChange(1000);
	session.writePosition(3, 53, value);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	
}

public void enterValueToSelectTrailer(String value) throws JagacyException, InterruptedException {
	
	logger.info("enter User value ");
	session.waitForChange(1000);
	session.writePosition(8, 27, value);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	session.waitForChange(1000);
	
}
public void enterFB(String FB) throws JagacyException, InterruptedException {
	
	logger.info("enter User value ");
	session.waitForChange(2000);
	session.writePosition(13, 3, FB);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	session.waitForChange(1000);
	
}

public void enterF1Key() throws JagacyException {
	logger.info("press f1 key");
	session.waitForChange(2000);
	session.writeKey(Key.PF1);
	session.waitForChange(1000);
	
}

}
