package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;



public class GoldMedalMainMenuScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public GoldMedalMainMenuScreen(SessionVt s) throws JagacyException {
		this.session = s;		
		props = s.getProperties();
		logger = s.getLoggable();
	}
	
 
public void verifyGoldMedalMainMenuScreen() throws JagacyException{

         
         
         logger.info("verify Gold Medal Main Selection Menu Screen");
         session.waitForChange(1000);	
         String expectedScreen = "Gold Medal Main Selection Menu";
 		
 	     String actualScreen = session.readRow(0);
 	    session.waitForChange(3000);
 	 	 if (actualScreen.contains(expectedScreen)) {
 			System.out.println(expectedScreen + ": Screen is Displayed");
 			logger.info(expectedScreen);
 			assertTrue(true);
 			 
 		} else {
 			
 			fail(expectedScreen + ": Screen is NOT Displayed");
 			 
		}
 	     
 			
 	}
         
	
	public final void enterSelectionValue(String searchValue) throws Exception {
		
		 logger.info("enter selection value");
		 session.waitForChange(1000);
			session.writePosition(20, 6, searchValue);
			session.waitForChange(1000);
			session.writeKey(Key.ENTER);
			
			
			
	}
	
	public final void enterOption(String option) throws Exception {
		 logger.info("enter selection value");
		 session.waitForChange(1000);
		 session.writePosition(20, 5, option);
		 session.waitForChange(1000);
		 session.writeKey(Key.ENTER);
		 session.waitForChange(1000);
	}

	public void enterF3Key() throws JagacyException {
		logger.info("press f3 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF3);
		session.waitForChange(3000);
	}
	
	// newly added method
	
public void enterF17Key() throws JagacyException {
	 logger.info("Enter F17");
	 session.waitForChange(2000);
	 session.writeKey(Key.PF17);
	 session.waitForChange(2000);
	
}
}

