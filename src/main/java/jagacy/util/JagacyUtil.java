
package jagacy.util;

import com.jagacy.util.JagacyException;
import com.jagacy.util.Loggable;

import testBase.TestBase;

import com.jagacy.util.JagacyProperties;

import java.awt.AWTException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.jagacy.Key;
import com.jagacy.SessionVt;

public class JagacyUtil extends TestBase {

	//Logger logger= Logger.getLogger(JagacyUtil.class);
	
	public static JagacyUtil jagacyUtil;
	private static SessionVt session;
	public static Loggable logger;
	public JagacyProperties props;

	public JagacyUtil(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public static String getText(SessionVt session, int row, int col, int length) {

		String textVal = null;
		try {
			textVal = session.readPosition(row, col, length);
		} catch (JagacyException e) {
			e.printStackTrace();
		}
		return textVal;

	}

	public static String getScreen(SessionVt session) {
		StringBuffer screen = new StringBuffer();
		try {
			String[] lines = session.readScreen();
			for (int i = 0; i < lines.length; i++) {
				screen.append(lines[i]).append("\n");
			}
		} catch (JagacyException je) {
			throw new RuntimeException("Could not retrieve text from the current screen", je);
		}
		return screen.toString();
	}

	public static String verifyScreen(SessionVt session) throws JagacyException {
		StringBuffer screen = new StringBuffer();

		try {
			String blu = "Work With Request Service Information";
			boolean line = session.readRow(0).contains(blu);
			System.out.println(line);

		} catch (JagacyException je) {
			throw new RuntimeException("Could not retrieve text from the current screen", je);
		}
		return screen.toString();
	}

	public static void waitForCursorMethod(SessionVt sessionObj, int row, int col, int timeOut) {
		try {
			sessionObj.waitForCursor(row, col, timeOut);
		} catch (JagacyException e) {
			e.printStackTrace();
		}
	}

	public static void writePositionMethod(SessionVt sessionObj, int row, int col, String text, int timeOut) {
		try {
			sessionObj.waitForCursor(row, col, timeOut);
			sessionObj.writePosition(row, col, text);
			Thread.sleep(2000);
			sessionObj.waitForChange(timeOut);
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Interrupted!"+ e);
		}
	}

	public static void waitForChangeMethod(SessionVt sessionObj, int timeOut) {
		try {
			sessionObj.waitForChange(timeOut);
		} catch(JagacyException e) {
			e.printStackTrace();
		}
	}

	public static void writeStringMethod(SessionVt sessionObj, String text, int timeOut) {
		try {
			sessionObj.writeString(text);
			Thread.sleep(2000);
			sessionObj.waitForChange(timeOut);
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Interrupted!"+ e);
		}
	}

	public static void pressKeys(SessionVt sessionObj, Key keyName, int timeOut) {
		try {
			sessionObj.writeKey(keyName);
			Thread.sleep(2000);
			sessionObj.waitForChange(10000);
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Interrupted!"+ e);
		}
	}
	
	public static String readLineFromScreen(SessionVt sessionObj, int rowNo) {
		String rowTxt = null;
		try {
			rowTxt = sessionObj.readRow(rowNo).trim();
		} catch(JagacyException e) {
			e.printStackTrace();
		}
		return rowTxt;
	}
	
	public void pressEnter() throws JagacyException, InterruptedException {
		logger.info("Press Enter");
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
	}
	
	public void pressF1() throws JagacyException, InterruptedException {
		logger.info("Press F1");
		session.writeKey(Key.PF1);
		session.waitForChange(3000);
	}
	public void pressF3() throws JagacyException, InterruptedException {
		logger.info("Press F3");
		session.writeKey(Key.PF3);
		session.waitForChange(3000);
	}

	public void pressF8() throws JagacyException, InterruptedException {
		logger.info("Press F8");
		session.writeKey(Key.PF8);
		session.waitForChange(3000);
	}
	
	public void pressPageDown() throws JagacyException, InterruptedException {
		logger.info("Press Page Down");
		session.writeKey(Key.PAGE_DOWN);
		session.waitForChange(3000);
	}

	public void pressF10() throws JagacyException, InterruptedException {
		logger.info("Press F10");
		session.writeKey(Key.PF10);
		session.waitForChange(5000);
	}

	public void pressF16() throws JagacyException, InterruptedException {

		logger.info("Press F16");
		session.waitForChange(3000);
		session.writeKey(Key.PF16);
		session.waitForChange(5000);
	}
	
	public void pressF17() throws JagacyException {
		logger.info("Press F17");
		session.waitForChange(3000);
		session.writeKey(Key.PF17);
		session.waitForChange(5000);
	}

	
	//F7 Key not available choice, building one out
	public void pressF7() throws JagacyException{
		logger.info("Press F7");
		session.waitForChange(3000); 
		session.writeKey(Key.PF7);
		session.waitForChange(5000); 
	}
	
	
	public void moveKeyTimes(Key k, int times) throws JagacyException {
		for(int t=0; t<times; t++) {
			session.writeKey(k);
			session.waitForChange(500);
		}
	}
	
	public void verifyReconcilationBySID1(SessionVt session, String pickUpNumber) throws JagacyException, InterruptedException {
		logger.info("Validate if 'Pickup System - Reconciliations by SID' is visible");
		int i = 6;
		String value = null; 
		boolean found = false; 
		try {
			if(session.readPosition(1, 20, 39).toString().trim().equalsIgnoreCase("Pickup System - Reconciliations by SID")) {
				do {
					value = session.readPosition(i, 8, 10).trim(); 
					if(value.equals(pickUpNumber)) {
						session.writeCursor(i-1, 1);
						session.writePosition(i-1, 1, "X");
						session.writeKey(Key.ENTER); 
						Thread.sleep(5000);
						found = true; 
						break; 
					}
					if(i == 19) {
						if(session.readPosition(20, 78, 2000).trim().equals("+")) {
							session.writeKey(Key.PAGE_DOWN); 
						}else {
							logger.info("There isn't an option to select for inputting the updated FB at this time");
							break; 
						}
					}
					i+=2; 
				}while(!found); 
			}
		}catch(JagacyException e){
			logger.info("Screen is not visible, continue to next line of code.");
			
		}
	}
}
	


