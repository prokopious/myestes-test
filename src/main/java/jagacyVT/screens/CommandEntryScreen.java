package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;



public class CommandEntryScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public CommandEntryScreen(SessionVt s) throws JagacyException {
		this.session = s;		
		props = s.getProperties();
		logger = s.getLoggable();
	}
	
	public final void enterValueToComandLineField(String ComandLineField) throws Exception {
		
		 logger.info("enter value to the comand line field");
		 session.waitForCursor(20, 6, 1000);
		 session.waitForChange(1000);
		 session.writeString(ComandLineField);
		 session.waitForChange(2000);		 
		 session.writeKey(Key.ENTER);	
		 session.waitForChange(2000);
	}
	
	

	public final void enterCommand(String command)
			throws JagacyException, InterruptedException {
		logger.info("Enter command");
		session.writeKey(Key.ENTER);
		session.waitForCursor(17, 5, 5000);
		session.writeString(command);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
	}
	
	public void enterF3Key() throws JagacyException {
		logger.info("press f3 key");
		session.waitForChange(2000);
		session.writeKey(Key.PF3);
		session.waitForChange(3000);
	}
	
	public void pressEnterKey() throws JagacyException {
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
	}

	//I need to be able to press tab twice so the cursor lands on the correct field
		public final void enterValueToComandLineField1(String ComandLineField) throws Exception {
			 logger.info("enter value to the comand line field");
			 session.waitForChange(2000); 
			 session.writeCursor(20, 6);
			 session.waitForChange(2000); 
			 session.writeAfterLabel("===>", ComandLineField);
			 session.waitForChange(2000); 
			 session.writeKey(Key.ENTER); 
		}
}
