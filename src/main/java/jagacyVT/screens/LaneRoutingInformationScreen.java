package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class LaneRoutingInformationScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public LaneRoutingInformationScreen(SessionVt s) throws JagacyException {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		String expectedScreen = "Route description";
//		String actualScreen = session.readRow(6);  changed the row number to below line
		String actualScreen = session.readRow(5);  //<<--
		logger.info("Actual Screen : "+actualScreen);

		if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen+ ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);

		} else {

			fail(expectedScreen+ " : Screen is NOT Displayed");

		}
		
	}
	
public void pressTab() throws JagacyException, InterruptedException {
		
		logger.info("enter Information category option ");
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}
	
	public String[] recordTerminalRoutingValue() throws JagacyException {
		logger.info("Fetch Terminal Routing value");
		session.waitForChange(1000);
		String [] terminalRoutingValue = new String[2];
//		terminalRoutingValue[0] = session.readRow(14).substring(21, 24);  --changed to below line
		terminalRoutingValue[0] = session.readRow(3).substring(34, 37);   //<<-
		logger.info(terminalRoutingValue[0]);
//		terminalRoutingValue[1] = session.readRow(14).substring(77, 80);  --changed to below line
		terminalRoutingValue[1] = session.readRow(4).substring(34, 37);  //<<--
		logger.info(terminalRoutingValue[1]);
		
		return terminalRoutingValue;
	}
	
	public String recordBypassorRelayRoutingValue() throws JagacyException {
		logger.info("Fetch Bypass or Relay Routing value");
		session.waitForChange(1000);
		String bypassorRelayRoutingValue = session.readRow(15);
		logger.info("Bypass or Relay Routing value : "+bypassorRelayRoutingValue);
		return bypassorRelayRoutingValue;
	}
	
	public void enterF3Key() throws JagacyException {
		logger.info("press f3 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF3);
		session.waitForChange(3000);
	}
	
}
