package jagacyVT.screens;



import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;
import jagacy.util.JagacyUtil;


public class LinehaulDispatchScreen {

	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public LinehaulDispatchScreen(SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}
	
	public void getTrailerField( ) throws JagacyException {
		logger.info("Get Trailer Number");
		System.out.println(session.readPosition(5, 16, 6));
	}
	
	public void selectTrailer(String trailer, String option) throws JagacyException, InterruptedException {
		logger.info("Select specified Trailer ");
		String actual;
		int i = 5;
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		boolean val = false;
		do {
			actual = session.readPosition(i, 16, 6);
			if(actual.equals(trailer)) {
				System.out.println("Trailer Found!!!!");
				session.writeCursor(i, 1);
				session.writePosition(i, 1, option);
				Thread.sleep(5000);
				System.out.println("Option set as "+option);
				jagacyUtil.pressEnter();
				Thread.sleep(5000);
				val = true;
				Thread.sleep(5000);
				break;
			}
			if((i == 18) && (!session.readPosition(19, 73, 6).contains("Bottom"))) {
				System.out.println("Next Page");
				jagacyUtil.pressPageDown();
				i = 4;
			}
			else if((i == 18) && (session.readPosition(19, 73, 6).contains("Bottom"))) {
				System.out.println("Reached last row!!!");
				break;
			}
			i++;
		}
		while (!val);
	}
	
	public void selectAndPlanDriver(String driver, String nextTerminal) throws JagacyException, InterruptedException {
		logger.info("Select and plan driver - "+driver);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		session.waitForChange(2000);
		session.waitForCursor(9, 36, 2000);
		session.writeString(driver);
		Thread.sleep(2000);
		jagacyUtil.pressEnter();
		session.waitForCursor(11, 23, 2000);
		session.writeString("1");
		session.waitForChange(2000);
		jagacyUtil.pressEnter();
		session.waitForChange(2000);
		session.waitForCursor(10, 69, 2000);
		session.writeString(nextTerminal);
		Thread.sleep(5000);
		jagacyUtil.pressEnter();
		Thread.sleep(5000);
		session.waitForChange(5000);
		Thread.sleep(5000);
	}

	public void enterShiftF4()throws JagacyException, InterruptedException {
		logger.info("Click Shift and F4 key");
		session.waitForChange(8000);
		session.writeKey(Key.PF16);
		session.waitForChange(5000);
	}
	

	public void selectRMOption(String option)throws JagacyException, InterruptedException {
		logger.info("Select RM option");

		session.waitForChange(2000);
		session.writePosition(2, 68, option);
		session.waitForChange(2000);

	}	
}


