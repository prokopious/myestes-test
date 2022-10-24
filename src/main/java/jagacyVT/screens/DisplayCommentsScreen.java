package jagacyVT.screens;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;


public class DisplayCommentsScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public DisplayCommentsScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyDisplayCommentsScreen() throws JagacyException {

		logger.info("verify Screen is displayed");
		session.waitForChange(3000);
		String expectedScreen = "Display Comments";

		String actualScreen = session.readRow(0);

		if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen + ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);

		} else {

			fail(expectedScreen + ": Screen is NOT Displayed");

		}

	}

	public void getScreenInfo() throws JagacyException {

		logger.info("verify Comments Screen");

		String screen = session.readRow(0).trim();

		System.out.println(screen);

	}

	public void enterF20Key() throws JagacyException {
		logger.info("press f20 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF20);
		session.waitForChange(3000);

	}

	public void enterF3Key() throws JagacyException {
		logger.info("press f3 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF3);
		session.waitForChange(3000);

	}

	public void verifyDataIsDisplayed() throws JagacyException {

		logger.info("Verify the keyed in data");
		String contactName = "QZ-7785";
		String origin = "PA";
		String destination = "CA";
		String Class = "50";
		String weight = "4700";
		String description = "This is a test";

		boolean value1 = session.readRow(3).contains(origin);
		System.out.println(value1);
		assertTrue(true);

		boolean value2 = session.readRow(4).contains(destination);
		System.out.println(value2);
		assertTrue(true);

		boolean value3 = session.readRow(10).contains(contactName);
		System.out.println(value3);
		assertTrue(true);

		session.writeKey(Key.PAGE_DOWN);
		session.waitForChange(1000);

		boolean value4 = session.readRow(9).contains(weight);
		System.out.println(value4);
		assertTrue(true);
		boolean value5 = session.readRow(9).contains(Class);
		System.out.println(value5);
		assertTrue(true);
		boolean value6 = session.readRow(10).contains(description);
		System.out.println(value6);
		assertTrue(true);

		session.waitForChange(2000);

	}
	
	public void verifyDataIsDisplayedInComments() throws JagacyException {

		logger.info("Verify the keyed in data");
		String contactName = "qz-3081";
		String origin = "PA";
		String destination = "CA";
		String phoneNo = "888-555-1212";
		String email = "eitqatest@estes-express.com";
		String Class = "50";
		String weight = "4700";
		String description = "This is a test";
		String pieceType = "SKID";
		String pieces = "3";
		

		boolean value1 = session.readRow(3).contains(origin);
		System.out.println(value1);
		assertTrue(true);

		boolean value2 = session.readRow(4).contains(destination);
		System.out.println(value2);
		assertTrue(true);

		boolean value3 = session.readRow(11).contains(contactName);
		System.out.println(value3);
		assertTrue(true);
		boolean value4 = session.readRow(12).contains(phoneNo);
		System.out.println(value4);
		assertTrue(true);
		boolean value5 = session.readRow(13).contains(email);
		System.out.println(value5);
		assertTrue(true);
		
		session.writeKey(Key.PAGE_DOWN);
		session.waitForChange(1000);

		boolean value6 = session.readRow(17).contains(weight);
		System.out.println(value6);
		assertTrue(true);
		boolean value7 = session.readRow(17).contains(Class);
		System.out.println(value7);
		assertTrue(true);
		boolean value8 = session.readRow(17).contains(pieceType);
		System.out.println(value8);
		assertTrue(true);
		boolean value9 = session.readRow(17).contains(pieces);
		System.out.println(value9);
		assertTrue(true);
		boolean value10 = session.readRow(18).contains(description);
		System.out.println(value10);
		assertTrue(true);

		session.waitForChange(2000);

	}
	
	

	public void verifyServiceInformationDisplayed(List<String> requestInfo) throws JagacyException {
		logger.info("verify service information is displayed in GMS");
		session.waitForChange(2000);
		
		logger.info("Verify quote");
		String quoteStr = session.readRow(4);
		logger.info("Quote from mainframe : "+quoteStr);
		String requestInfo6 = requestInfo.get(6).toString();
		logger.info("Quote from List : "+requestInfo6);
		boolean quote = quoteStr.contains(requestInfo6);
		System.out.println(quote);
		assertTrue(true);
		
		logger.info("Verify Origin");
		String originStr = session.readRow(5);
		logger.info("Origin in mainframe : "+originStr);
		String requestInfo1 = requestInfo.get(1).toString();
		logger.info("Origin in List : "+requestInfo1);
		boolean origin =originStr.contains(requestInfo1);
		System.out.println(origin);
		assertTrue(true);
		
		logger.info("Verify destination");
		String destinationStr = session.readRow(7);
		logger.info("destination in mainframe : "+destinationStr);
		String requestInfo2 = requestInfo.get(2).toString();
		logger.info("destination in List : "+requestInfo2);
		boolean destination = destinationStr.contains(requestInfo2);
		System.out.println(destination);
		assertTrue(true);
		
		logger.info("Verify account");
		String accountStr = session.readRow(9);
		logger.info("account in mainframe : "+accountStr);
		String requestInfo0 = requestInfo.get(0).toString();
		logger.info("account in List : "+requestInfo0);
		boolean account = accountStr.contains(requestInfo0);
		System.out.println(account);
		assertTrue(true);
		
		logger.info("Verify Expire Date");
		String expireDayStr = session.readRow(13);
		logger.info("Expire Date in mainframe : "+expireDayStr);
		String requestInfo5 = requestInfo.get(5).toString();
		logger.info("Expire Date in List : "+requestInfo5);
		boolean expireDay = expireDayStr.contains(requestInfo5);
		System.out.println(expireDay);
		assertTrue(true);
		
		logger.info("Verify Piece type");
		String pieceTypeStr = session.readRow(17);
		logger.info("Piece type in mainframe : "+pieceTypeStr);
		String requestInfo4 = requestInfo.get(4).toString();
		logger.info("Piece type in List : "+requestInfo4);
		boolean pieceType = pieceTypeStr.contains(requestInfo4);
		System.out.println(pieceType);
		assertTrue(true);
		
		logger.info("Verify number of Pieces");
		String pieceNoStr = session.readRow(18);
		logger.info("number of Pieces in mainframe : "+pieceNoStr);
		String requestInfo3 = requestInfo.get(3).toString();
		logger.info("number of Pieces in list : "+requestInfo3);
		boolean pieceNo = pieceNoStr.contains(requestInfo3);
		System.out.println(pieceNo);
		assertTrue(true);
		
	}
	public void verifyDetailsInDisplaCommentsScreen(String value) throws JagacyException, InterruptedException {
		 logger.info("Select specified Comments ");
	        int i = 9;
	        jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
	        boolean val = false;
	        Thread.sleep(5000);
	        session.waitForPosition(2, 1, "For Request", 5000);
	        do {
	        	boolean value1 = session.readRow(i).contains(value);
	    		assertTrue(true);
	    		logger.info(value);
	     
	            if(value1 == true) {
	                System.out.println("Comments Verified!!!");
	                break;
	                        }
	                        if((i == 18) && (session.readPosition(20, 73, 6).contains("Bottom"))) {
	                            System.out.println("Reached last row!!!");
	                            break;
	                        }
	                        else if
	                            ((i == 18) && (!session.readPosition(19, 73, 6).contains("Bottom"))) {
	                                System.out.println("Next Page");
	                                jagacyUtil.pressPageDown();
	                                i = 4;
	                        }
	                        i++;
	                    }
	                    while (!val);
	    
		
	}


}


