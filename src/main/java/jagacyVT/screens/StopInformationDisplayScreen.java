package jagacyVT.screens;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;
import testBase.TestBase;

public class StopInformationDisplayScreen extends TestBase {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public StopInformationDisplayScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}

	public void verifyDate() {
		
		logger.info("Verify Date");
		String date = testUtil.getTodayDateByFormat("MM/dd/yyyy");
		String actualDate = JagacyUtil.readLineFromScreen(session, 5);
		Assert.assertTrue(actualDate.contains(date));
	}
	
	public void verifyShipperName(String shipperName) {
		
		logger.info("Verify Stop Number");
		String shipperNme = JagacyUtil.readLineFromScreen(session, 7);
		Assert.assertTrue(shipperNme.contains(shipperName));
	}
	
	public void pressF3Key() {
		logger.info("Enter F3");
		JagacyUtil.pressKeys(session, Key.PF3, 1000);
	}
	
	public void pressF1Key() {
		logger.info("Enter F1");
		JagacyUtil.pressKeys(session, Key.PF1, 1000);
	}
	
	public void verifyPickupStatusCode(String expStatus) throws Exception {
		
		logger.info("Verify Pickup Status Code");
		session.waitForChange(3000);	
		String status = session.readPosition(20, 19, 1);
		System.out.println("Status = "+status);
		Assert.assertEquals(status, expStatus);
	}
}
