package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;
import junit.framework.Assert;

public class WorkWithCommentsScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public WorkWithCommentsScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyWorkWithCommentsScreen() throws JagacyException {

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

	
	public void enterF3Key() throws JagacyException {
		logger.info("press f3 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF3);
		session.waitForChange(3000);
	}
	
	public void enterStartingLineNumber(String lineNum) throws JagacyException {
		logger.info("Enter the Starting Line number.");
		session.waitForChange(2000);
		session.writePosition(7, 20, lineNum);
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
	
	public void verifyCommentEntered(String comment, String userId) throws JagacyException {
		logger.info("Comment : "+comment);
		logger.info("User Id : "+userId);
		String rowValue = session.readRow(18);
		logger.info("Row Value : "+rowValue);
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/dd/yyyy");  
		   LocalDateTime now = LocalDateTime.now();  
		   String todayDate = dtf.format(now);  
		   logger.info("Today's Date : "+todayDate);
		if(rowValue.contains(comment) && rowValue.contains(userId) && rowValue.contains(todayDate)) {
			logger.info("Found all the required components.");
		}else {
			Assert.fail("Does not found all required elements");
		}
	}
}

