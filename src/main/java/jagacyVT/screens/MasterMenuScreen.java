package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;




public class MasterMenuScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	
	public MasterMenuScreen(final SessionVt session) throws JagacyException {
		this.session = session;				
		props = session.getProperties();
		logger = session.getLoggable();	
	}
	
	public final void enterFreightBillMenuOption(String option) throws Exception {
		
		
		 logger.info("enter FreightBill Menu Option on Master Menu");
		 session.waitForCursor(22, 8, 5000);
		 session.writeString(option);
		 session.writeKey(Key.ENTER);
		 session.waitForChange(1000);
		
		 
		
	}
	
	
	public void verifyScreenTitle() throws JagacyException, InterruptedException {
        logger.info("Verify Screen Title");
        boolean val = false;
        int i = 1;
        
        do {
            String title = session.readPosition(1, 31, 18);
            String titleMsg = session.readPosition(0, 27, 24);
            
            if(title.equalsIgnoreCase("LTL/38 Master Menu")) {
                System.out.println("Screen Title is "+title);
                val = true;
                break;
            }
            else if(titleMsg.contains("Display Program")){
                System.out.println("Display message "+titleMsg);
                JagacyUtil jagacyUtil = new JagacyUtil(session);
                jagacyUtil.pressEnter();
                session.waitForChange(1000);
            }
            else {
                session.waitForChange(2000);
            }
             if(i == 5) {
                System.out.println("Failed to display LTL/38 Master menu");
                break;
            }
            i++;
        }
        while (val==false);
    }
	
	public final void enterMenuOption(String option) throws Exception {
		 logger.info("Enter Option on Master Menu");
		 session.waitForCursor(22, 8, 5000);
		 session.writeString(option);
		 session.waitForChange(2000);
		 session.writeKey(Key.ENTER);
		 session.waitForChange(3000);
	}
	
	
	
}
