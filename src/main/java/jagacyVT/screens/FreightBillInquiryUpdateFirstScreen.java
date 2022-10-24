package jagacyVT.screens;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;

public class FreightBillInquiryUpdateFirstScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public FreightBillInquiryUpdateFirstScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void verifyQuoteNumber(String qNo) throws Exception{
		logger.info("Verify Quote Number");
		session.writeCursor(1, 21);
		session.waitForCursor(1, 21, 1000);
		String eControlID = session.readPosition(1,  21, 7);
		Assert.assertTrue(eControlID.contains(qNo));
		
	}
	
	public void verifyPieceNumber(String piece) throws Exception{
		logger.info("Verify Piece Number");
		session.writeCursor(2, 19);
		session.waitForCursor(2, 19, 1000);
		String actualPiece = session.readPosition(2,  19, 1);
		Assert.assertTrue(actualPiece.contains(piece));
		
	}
	
	public void verifyTerms(String terms) throws Exception{
		logger.info("Verify Terms");
		session.writeCursor(2, 27);
		session.waitForCursor(2, 27, 1000);
		String actualTerms = session.readPosition(2,  27, 3);
		Assert.assertTrue(actualTerms.contains(terms));
		
	}
	
	public void verifyWeight(String weight) throws Exception{
		logger.info("Verify Weight");
		session.writeCursor(2, 38);
		session.waitForCursor(2, 38, 1000);
		String actualWeight = session.readPosition(2,  38, 4);
		Assert.assertTrue(actualWeight.contains(weight));
		
	}
	
	public void verifyShipperName(String shipperName) throws Exception{
		logger.info("Verify Shipper Name");
		session.writeCursor(4, 14);
		session.waitForCursor(4, 14, 1000);
		String actualShipper = session.readPosition(4, 14, 5);
		Assert.assertTrue(actualShipper.contains(shipperName));
		
	}
	
	public void verifyConsName(String consName) throws Exception{
		logger.info("Verify Consignee Name");
		session.writeCursor(6, 14);
		session.waitForCursor(6, 14, 1000);
		String actualCons = session.readPosition(6, 14, 20);
		Assert.assertTrue(actualCons.contains(consName));
		
	}
	
	public void pressEnterKey() {
		logger.info("Enter Password");
		JagacyUtil.pressKeys(session, Key.ENTER, 1000);
	}

	public void enterTSValue(String tsValue) throws Exception {
		logger.info("Enter TS value");
		session.writeCursor(2, 9);
		session.waitForCursor(2, 9, 1000);
		session.writeString(tsValue);
		session.waitForChange(1000);
	}
	
	public void enterPuDrValue(String pudr) throws Exception {
		logger.info("Enter pudr value");
		session.writeCursor(10, 48);
		session.waitForCursor(10, 48, 1000);
		session.writeString(pudr);
		session.waitForChange(1000);

	}
	
	public void enterCubicFtValue(String cubicFt) throws Exception {
		logger.info("Enter cubic ft value");
		session.writeCursor(13, 38);
		session.waitForCursor(13, 38, 1000);
		session.writeString(cubicFt);
		session.waitForChange(1000);

	}
	
	public void enterPoNumberValue(String poNum) throws Exception {
		logger.info("Enter PO number value");
		session.writeCursor(10, 6);
		session.waitForCursor(10, 6, 1000);
		session.writeString(poNum);
		session.waitForChange(1000);

	}
}
