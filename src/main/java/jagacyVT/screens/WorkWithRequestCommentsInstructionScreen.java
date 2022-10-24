package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class WorkWithRequestCommentsInstructionScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public WorkWithRequestCommentsInstructionScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyWorkWithCommentsInstructionScreen() throws JagacyException {

		logger.info("Verify Screen is displayed");
		session.waitForChange(3000);
		String expectedScreen = "Work With Request Service Information";

		String actualScreen = session.readRow(0);

		if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen + ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);

		} else {

			System.out.println(expectedScreen + ": Screen is NOT Displayed");

		}

	}

	public void enterF17Key() throws JagacyException {
		logger.info("Press f17 key");
		session.waitForChange(4000);
		session.writeKey(Key.PF17);
		//session.waitForChange(3000);

	}

	public final int enterQuoteNumber(String quoteNum) throws Exception, JagacyException {

		logger.info("enter quote number");
		session.waitForChange(2000);
		session.writePosition(2, 20, quoteNum);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
		String rowValue = null;
		int count = 0;
		for(int i=1;i<=12;i++) {
			rowValue = session.readRow(i+7);
			logger.info("Row Value : "+rowValue);
			logger.info("quoteNum : "+quoteNum);
			if(rowValue.contains(quoteNum)) {
				logger.info("Found correct Quote row position.");
				count = i;
				session.waitForChange(2000);
			}
			if(count>0) {
				break;
			}
		}
		return count;
		
	}

	public final void enterValueInOptField(String type) throws Exception, JagacyException {

		logger.info("enter type value in opt field");
		session.waitForChange(1000);
		session.writePosition(9, 1, type);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}
	
	public final void enterValueInOptField(int row, String type) throws Exception, JagacyException {

		logger.info("enter type value in opt field");
		session.waitForChange(1000);
		session.writePosition(row, 1, type);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}

	public final void enterQuoteNumberWithPrefix3(String quoteNum2) throws Exception, JagacyException {

		logger.info("enter quote number of 10 AM");
		session.waitForChange(1000);
		session.writePosition(2, 20, quoteNum2);
		session.waitForChange(1000);
		System.out.println("Prefix is changed to 3 :" + quoteNum2);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		
	}

	public void verify12PMQuoteIsNotDisplayed(String quoteNum) throws Exception, JagacyException {
		
		logger.info("enter 12 PM quote number and verify quote is not displayed");
		session.waitForChange(1000);
		session.writePosition(2, 20, quoteNum);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		

		String currentQuote = JagacyUtil.getText(session, 9, 5, 11);
		String expectedQuote = quoteNum ;
			
		if (!currentQuote.contains(expectedQuote)) {
			System.out.println(expectedQuote + ": 12PM quote is not Displayed");
			logger.info(expectedQuote);
			assertTrue(true);
					 
		} else {
			fail(expectedQuote + ": 12 PM quote is Displayed");
			
		}

		
	}
	
	
public void verify10AMQuoteIsNotDisplayed(String quoteNum) throws Exception, JagacyException {
		
		logger.info("enter 10 AM quote number and verify quote is not displayed");
		session.waitForChange(1000);
		session.writePosition(2, 20, quoteNum);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		

		String currentQuote = JagacyUtil.getText(session, 9, 5, 11);
		String expectedQuote = quoteNum ;
			
		if (!currentQuote.contains(expectedQuote)) {
			System.out.println(expectedQuote + ": 10 AM quote is not Displayed");
			logger.info(expectedQuote);
			assertTrue(true);
					 
		} else {
			fail(expectedQuote + ": 10 AM quote is Displayed");
			
		}
		
	}

	public void enterF3Key() throws JagacyException {
		logger.info("press f3 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF3);
		session.waitForChange(3000);
	}
	
	public void enterStartingLineNumber(String lineNum) throws JagacyException {
		logger.info("Enter the Starting Line number.");
		session.waitForChange(2000);
		session.writePosition(4, 20, lineNum);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
	}
	
	public void pressEnter() throws JagacyException {
		logger.info("Press Enter Key.");
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
	}
}

