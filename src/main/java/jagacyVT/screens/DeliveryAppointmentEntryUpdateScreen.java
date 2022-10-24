package jagacyVT.screens;

import org.testng.Assert;

import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import testBase.TestBase;

public class DeliveryAppointmentEntryUpdateScreen extends TestBase {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public DeliveryAppointmentEntryUpdateScreen(SessionVt s) {

		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterDate(String date) throws JagacyException, InterruptedException {
		session.writePosition(16, 9, date);
		session.waitForChange(1000);
	}

	public void enterTime(String start, String end) throws JagacyException, InterruptedException {
		session.writePosition(16, 22, start);
		session.waitForChange(1000);
		session.writePosition(16, 29, end);
		session.waitForChange(1000);
	}

	public void enterName(String first, String last) throws JagacyException, InterruptedException {
		session.writePosition(16, 52, first);
		session.waitForChange(1000);
		session.writePosition(16, 63, last);
		session.waitForChange(1000);
	}

	public void enterReason(String reason) throws JagacyException, InterruptedException {
		session.writePosition(16, 42, reason);
		session.waitForChange(1000);
	}

	public void enterPhone(String phone, String ext) throws JagacyException, InterruptedException {
		session.writePosition(18, 8, phone);
		session.waitForChange(1000);
		session.writePosition(18, 29, ext);
		session.waitForChange(1000);
	}

	public String getFBnum() throws JagacyException, InterruptedException {
		String fbNum = session.readPosition(4, 15, 11).trim().replace(" ", "");
		logger.info("FB #: " + fbNum);
		return fbNum;
	}
	
	public void verifyProNumber(String proNum) throws JagacyException{
		logger.info("Verify absence of Pro  number");
		session.waitForChange(1000);
		String expectedScreen = proNum;
		String actualScreen = session.readRow(5);
		session.waitForChange(3000);
		Assert.assertFalse(actualScreen.contains(expectedScreen));
	}

}
