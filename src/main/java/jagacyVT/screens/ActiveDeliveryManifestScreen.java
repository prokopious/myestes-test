package jagacyVT.screens;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;



public class ActiveDeliveryManifestScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public ActiveDeliveryManifestScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterF10Key() throws JagacyException {
		logger.info("press f10 key");
		session.waitForChange(3000);
		session.writeKey(Key.PF10);
		session.waitForChange(1000);
		
	}

public void enterValueA(String value) throws JagacyException, InterruptedException {
	
	logger.info("enter value A");

	session.waitForChange(3000);
	session.writePosition(15, 2, value);
	session.waitForChange(1000);
	
    session.writeKey(Key.ENTER);
    session.waitForChange(3000);
	
}





 
	public void enterValueALoop(String value) throws JagacyException, InterruptedException {

		logger.info("enter value A");

		List<String> results = new ArrayList<String>();

		for (int row = 9; row <= 11; row++) {
			// if (!session.readPosition(row, 9, 40).trim().equals("")) {
			results.add(session.readPosition(row, 9, 43).trim());
			// }
		}

//if (!session.readRow(9).equals("")) {

		System.out.println("FreightBill Numbers are :" + results);
		String shipmentNo = results.toString().substring(16, 27).replaceAll("\\s+", "");

		System.out.println("FreightBill Number is :" + shipmentNo);

		//return freightBillNo;
	}





public static String getScreen(SessionVt session){
    StringBuffer screen = new StringBuffer();
    try{
        String[] lines = session.readScreen();
        
        
      // if (lines.)
        for (int i = 0; i < lines.length; i++) {
            screen.append(lines[i]).append("\n");
        }
    }catch(JagacyException je){
        throw new RuntimeException("Could not retrieve text from the current screen", je);
    }
    return screen.toString();
}

	

}
