package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class TrailerScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public TrailerScreen(SessionVt s) {
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
		session.writeKey(Key.ENTER);
		//Thread.sleep(2000);
		
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
