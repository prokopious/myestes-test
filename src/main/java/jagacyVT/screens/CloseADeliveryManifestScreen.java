package jagacyVT.screens;


import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class  CloseADeliveryManifestScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public  CloseADeliveryManifestScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
public void enterValueToDispatchField(String value) throws JagacyException, InterruptedException {
		
		logger.info("enter value ");
		
		session.waitForChange(3000);
		session.writePosition(14, 15, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		
		
	}

public void enterValueUserField(String user) throws JagacyException, InterruptedException {
	
	logger.info("enter User value ");
	session.waitForCursor(22, 22, 1000);
	session.waitForChange(1000);
	session.writeString(user);
	session.waitForChange(1000);
	//Thread.sleep(2000);
	
}


}
