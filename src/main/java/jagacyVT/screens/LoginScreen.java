package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;
import testBase.TestBase;

public class LoginScreen extends TestBase {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	
	public LoginScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
		
	}
	
 public void logon(String userName, String password) throws JagacyException {
		 
		 logger.info("Enter username and password");
		// session.waitForPosition(5, 16, "User", 3, 3);
		 /* if (session.readRow(5).indexOf("User") == -1)  {
         session.logger.fatal("Incorrect Login Screen");
         assertFalse(true, "Step Failed...");
        }*/
		session.waitForCursor(5, 52, 2000);
		session.waitForChange(1000);
		session.writeString(userName);
		session.waitForChange(1000);

		session.writeKey(Key.TAB);
		session.waitForChange(3000);

		session.waitForCursor(6, 52, 1000);
		session.writeString(password);
		session.waitForChange(1000);

		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		 
	    }
 
	public final void enterUserNPasswordCDOC(String uName, String pass) throws JagacyException {

		logger.info("Enter user name and password CDOC");

		session.waitForCursor(5, 52, 1000);
		session.writeString(uName);
		session.waitForChange(1000);
		if (uName.length() < 10) {
			session.writeKey(Key.TAB);
			session.waitForChange(1000);
		}
		session.waitForCursor(6, 52, 1000);
		session.writeString(pass);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(5000);
	}


	public void enterUserName(String userName) {
		logger.info("Enter User Name");
		JagacyUtil.writePositionMethod(session, 5, 52, userName, 1000);
	}
	
	public void enterPassword(String password) {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.TAB, 1000);
		JagacyUtil.waitForChangeMethod(session, 1000);
		JagacyUtil.waitForCursorMethod(session, 6, 52, 1000);
		JagacyUtil.writeStringMethod(session, password, 1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}
	
	public static SessionVt startAS400() throws JagacyException {
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		session.open();
		return session;
	}
	
	
	public void startSessionAndLoginToMasterMenu(String userName, String password) throws Exception {
		enterUserNPasswordCDOC(userName, password);
		if (session.readPosition(0, 27, 7).toString().trim().equals("Display")) {
			session.writeKey(Key.ENTER);
			session.waitForChange(1000);
		}
		session.waitForChange(1000);
		if (session.readPosition(0, 33, 13).equals("Command Entry")) {
			IBMMainMenuScreen iBMMainMenuScreen = new IBMMainMenuScreen(session);
			iBMMainMenuScreen.enterValueToComandLineField("CALL XXC870");
			session.waitForChange(1000);
		}
		Ltl38MasterMenuScreen masterMenu = new Ltl38MasterMenuScreen(session);
		masterMenu.verifyScreenTitle();
	}


}
