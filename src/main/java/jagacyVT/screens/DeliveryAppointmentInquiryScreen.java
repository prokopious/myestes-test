package jagacyVT.screens;

import testBase.TestBase;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class DeliveryAppointmentInquiryScreen extends TestBase{
	

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public DeliveryAppointmentInquiryScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
	
}

	public void enterProNumber(String num) throws JagacyException, InterruptedException {
		session.writePosition(5, 43, num);
		session.waitForChange(1000);

	}

}

	

