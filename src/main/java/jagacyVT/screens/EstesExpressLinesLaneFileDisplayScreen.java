package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class EstesExpressLinesLaneFileDisplayScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public EstesExpressLinesLaneFileDisplayScreen(SessionVt s) throws JagacyException {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		String expectedScreen1 = "Estes Express Lines";
		String expectedScreen2 = "Lane file display";
		String actualScreen = session.readRow(0)+" "+session.readRow(1);
		logger.info("Actual Screen : "+actualScreen);

		if (actualScreen.contains(expectedScreen1) || actualScreen.contains(expectedScreen2)) {
			System.out.println(expectedScreen1+" "+expectedScreen2 + ": Screen is Displayed");
			logger.info(expectedScreen1+" "+expectedScreen2);
			assertTrue(true);

		} else {

			fail(expectedScreen1+" "+expectedScreen2 + ": Screen is NOT Displayed");

		}
		
	}
	
	public void enterfromValue(String option) throws JagacyException, InterruptedException {
		
		logger.info("enter Information category option ");
		session.waitForChange(1000);
		session.writePosition(5, 40, option);
		session.waitForChange(1000);
	}
	
public void pressTab() throws JagacyException, InterruptedException {
		
		logger.info("enter Information category option ");
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}
	
public void enterToValue(String option) throws JagacyException, InterruptedException {
		
		logger.info("enter Information category option ");
		session.waitForChange(1000);
		session.writePosition(7, 40, option);
		session.waitForChange(1000);
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}
	
}
