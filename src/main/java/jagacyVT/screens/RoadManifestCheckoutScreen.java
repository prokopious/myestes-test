package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class RoadManifestCheckoutScreen {
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public RoadManifestCheckoutScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterRunNumber(String num) throws JagacyException, InterruptedException {
		logger.info("Enter Run Number");

		logger.info("Enter Run Number");
		session.waitForChange(2000);
		session.writePosition(7, 34, num);
		session.waitForChange(2000);

	}

	public void enterTractor(String tractor) throws JagacyException, InterruptedException {
		logger.info("Enter Tractor");
		session.waitForChange(9, 34);
		session.writeCursor(9, 34);
		session.writePosition(9, 34, tractor);
		session.waitForChange(2000);
	}

	public void enterHub(String option) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
		session.writePosition(11, 55, option);
		session.waitForChange(2000);
		
	}
	
	public void delete(String option) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
	session.writePosition(11, 53, "");
	session.waitForChange(2000);
	

	 session.waitForCursor(11, 53, 5000);
	 for(int i = 1; i<=5;i++)
	 {
		 session.writeKey(Key.DELETE);
	 }
	 session.waitForChange(2000);
	}
	

	
	public void enterErrorEquipment(String option) throws JagacyException, InterruptedException {
		logger.info("Enter Error Equipment option");
		session.waitForChange(2000);
		session.writePosition(4, 43, option);
		session.waitForChange(2000);
	}

	public void enterDispatchType(String option) throws JagacyException, InterruptedException {
		logger.info("Enter Dispatch Type option");
		session.waitForChange(2000);
		session.writePosition(9, 57, option);
		session.waitForChange(2000);
	}

	public void clickF1() throws JagacyException, InterruptedException {
		logger.info("Click F1");
		session.waitForChange(2000);
		session.writeKey(Key.PF1);
		session.wait(5000);
	}

	public void verifyRoadManifestCheckOutScreen() throws JagacyException {
		logger.info("verify Road Manifest Check Out Screen");
		session.waitForChange(1000);
		String expectedScreen = "Road manifest check-out";

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

	public void enterPosToValue(String value) throws JagacyException, InterruptedException {

		logger.info("enter position value ");

		session.waitForChange(1000);
		session.writePosition(3, 53, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);

	}

	public void enterValueToSelectTractor(String value) throws JagacyException, InterruptedException {

		logger.info("Select Tractor ");

		session.waitForChange(1000);
		session.writePosition(8, 27, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);

	}

	public void enterHubValue(String value) throws JagacyException, InterruptedException {

		logger.info("enter HUB value ");

		session.waitForChange(1000);
		session.writePosition(11, 55, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);

	}

	public void enterEquipmentError() throws JagacyException {
		logger.info("Enter Miniumum Cube");
		session.waitForChange(3000);
		session.writePosition(4, 43, "Y");
		session.waitForChange(3000);
	}

	public void enterMinimumCubeError() throws JagacyException {
		logger.info("Enter Miniumum Cube");
		session.waitForChange(3000);
		session.writePosition(9, 57, "Y");
		session.waitForChange(3000);
	}
}
