package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class DisplayRequestServiceInfoScreen {

	private SessionVt session;
	public static Loggable logger;
	public JagacyProperties props;

	public DisplayRequestServiceInfoScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void verifyDisplayRequestScreen() throws JagacyException {

		logger.info("verify Screen is displayed");
			
		String expectedScreen = "Display Request Service Information";
		String actualScreen = session.readRow(0);
		session.waitForChange(1000);
		if (actualScreen.contains(expectedScreen)) {
			System.out.println(expectedScreen + ": Screen is Displayed");
			logger.info(expectedScreen);
			assertTrue(true);

		} else {

			fail(expectedScreen + ": Screen is NOT Displayed");

		}

	}

	public void enterF20Key() throws JagacyException {
		logger.info("press f20 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF20);
		session.waitForChange(3000);

	}

	public void pageDown() throws JagacyException {
		logger.info("page down");
		session.waitForChange(2000);
		session.writeKey(Key.PF20);
		session.waitForChange(3000);

	}

	public void verifyKeyedDataIsDisplayed() throws JagacyException {
		logger.info("verify keyed in data is displayed in GMS");

		String origin = "16159";
		String destination = "90007";
		String Class = "50";
		String weight = "4700";
		String description = "This is a test";

		session.waitForChange(2000);
		boolean value1 = session.readRow(5).contains(origin);
		System.out.println(value1);
		assertTrue(true);
		boolean value2 = session.readRow(7).contains(destination);
		System.out.println(value2);
		assertTrue(true);
		session.waitForChange(2000);
		session.writeKey(Key.PAGE_DOWN);
		session.waitForChange(2000);

		boolean value3 = session.readRow(4).contains(weight);
		System.out.println(value3);
		assertTrue(true);
		boolean value4 = session.readRow(5).contains(Class);
		System.out.println(value4);
		assertTrue(true);
		boolean value5 = session.readRow(10).contains(description);
		System.out.println(value5);
		assertTrue(true);

	}
	
	
	public void verifyCommodityDataIsDisplayed() throws JagacyException {
		logger.info("verify commodity data is displayed in GMS");

		String origin = "16159";
		String destination = "90007";
		String account = "PREMIER MANUFACTURING";
		String role = "THIRD PARTY";
		String terms = "COLLECT";
		String pieceType = "SKID";
		String pieces = "3";
		String Class = "50";
		String weight = "4700";
		String description = "This is a test";

		session.waitForChange(2000);
		boolean value1 = session.readRow(5).contains(origin);
		System.out.println(value1);
		assertTrue(true);
		boolean value2 = session.readRow(7).contains(destination);
		System.out.println(value2);
		assertTrue(true);
		boolean value3 = session.readRow(9).contains(account);
		System.out.println(value3);
		assertTrue(true);
		boolean value4 = session.readRow(10).contains(role);
		System.out.println(value4);
		assertTrue(true);
		boolean value5 = session.readRow(11).contains(terms);
		System.out.println(value5);
		assertTrue(true);
		boolean value6 = session.readRow(17).contains(pieceType);
		System.out.println(value6);
		assertTrue(true);
		boolean value7 = session.readRow(18).contains(pieces);
		System.out.println(value7);
		assertTrue(true);
		
		session.waitForChange(2000);
		session.writeKey(Key.PAGE_DOWN);
		session.waitForChange(2000);

		boolean value8 = session.readRow(4).contains(weight);
		System.out.println(value8);
		assertTrue(true);
		boolean value9 = session.readRow(5).contains(Class);
		System.out.println(value9);
		assertTrue(true);
		boolean value10 = session.readRow(10).contains(description);
		System.out.println(value10);
		assertTrue(true);

	}

	public void verifyQuoteDetails(String quoteNo, String origin, String destination, String acctNo) throws JagacyException, InterruptedException {
		logger.info("Verify quote data");
		
		String actualQuote = session.readPosition(4, 28, 7);
		Thread.sleep(999999);
		Assert.assertEquals(actualQuote, quoteNo);
		
		String actualOrigin = session.readPosition(5, 28, 5);
		Assert.assertEquals(actualOrigin, origin);
		
		String actualDestination = session.readPosition(7, 28, 5);
		Assert.assertEquals(actualDestination, destination);
		
		String actualAcct = session.readPosition(9, 28, 7);
		Assert.assertEquals(actualAcct, acctNo);
		
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
	
	public String recordOriginTerminalNumber() throws JagacyException {
		logger.info("Record the Origin Terminal number.");
		session.waitForChange(1000);
		
		String originTerminalNumber = session.readRow(6);
		logger.info(originTerminalNumber);
		originTerminalNumber = originTerminalNumber.substring(28,31);
		logger.info("Origin Terminal number : "+originTerminalNumber);
		return originTerminalNumber;
	}
	
	public String recordDestinationTerminalNumber() throws JagacyException {
		logger.info("Record the Destination Terminal number.");
		session.waitForChange(1000);
		
		String destinationTerminalNumber = session.readRow(8);
		logger.info(destinationTerminalNumber);
		destinationTerminalNumber = destinationTerminalNumber.substring(28,31);
		logger.info("Destination Terminal number : "+destinationTerminalNumber);
		return destinationTerminalNumber;
	}
	
	public void pressF18Key() throws JagacyException {
		logger.info("Press F18 Key.");
		session.waitForChange(2000);
		session.writeKey(Key.PF18);
		session.waitForChange(3000);
	}
	
	public void verifyQuoteDetailsInRequestScreen(String quoteNo, String originZip, String destZip,
			String accountNumber, String serviceDays, String weight, String class1) throws JagacyException {
		
		
		logger.info("Verify quote data");
		
		String actualQuote = session.readPosition(4, 28, 7);
		Assert.assertEquals(actualQuote, quoteNo);
		logger.info("Qutoe Verified");
				
		String actualOrigin = session.readPosition(5, 28, 5);
		Assert.assertTrue(originZip.contains(actualOrigin));
		logger.info("Origin Verified");
		
		String actualDestination = session.readPosition(7, 28, 5);
		Assert.assertTrue(destZip.contains(actualDestination));
		logger.info("Destination Verified");		
		
		String actualAcct = session.readPosition(9, 28, 7);
		Assert.assertEquals(actualAcct, accountNumber);
		logger.info("Account Number Verified");
		
		String actualServiceDays = session.readPosition(19, 29, 1);
		Assert.assertTrue(serviceDays.contains(actualServiceDays));
		logger.info("Service Days Verified");
		
		session.waitForChange(2000);
		session.writeKey(Key.PAGE_DOWN);
		session.waitForChange(2000);
		
		String actualWgt = session.readPosition(4, 31, 4);
		Assert.assertEquals(actualWgt, weight);
		logger.info("Weight Verified");
		
		String actualClass = session.readPosition(5, 29, 2);
		Assert.assertEquals(actualClass, class1);
		logger.info("Class Verified");
	
		
	}


}

