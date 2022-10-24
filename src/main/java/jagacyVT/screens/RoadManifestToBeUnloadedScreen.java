package jagacyVT.screens;


import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class RoadManifestToBeUnloadedScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public RoadManifestToBeUnloadedScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}

	
	public void unloadTrailer(String trailer) throws JagacyException, InterruptedException {
		logger.info("Select Trailer to unload "+trailer);
		
		String actual;
		int i = 4;
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);
		boolean val = false;
		int j = 0;
		do {
			actual = session.readPosition(i, 33, 6);
			if(actual.equals(trailer)) {
				System.out.println("Trailer Found!!!!");
				session.writeCursor(i, 1);
				Thread.sleep(5000);
				session.writePosition(i, 1, "x");
				Thread.sleep(5000);
				jagacyUtil.pressEnter();
				Thread.sleep(5000);
				session.writeCursor(4, 22);
				session.writePosition(4, 22, "Y");
				Thread.sleep(5000);
				jagacyUtil.pressEnter();
				val = true;
				Thread.sleep(5000);
				break;
			}
			if((i == 18) && (j<=30)) {
				System.out.println("Next Page");
				jagacyUtil.pressPageDown();
				i = 4;
			}
			else if((i == 18) && (j==30)) {
				System.out.println("Searched Maximum pages, Trailer not found");
				break;
			}
			i++;
			j++;
		}
		while (!val);
	}
	
}
