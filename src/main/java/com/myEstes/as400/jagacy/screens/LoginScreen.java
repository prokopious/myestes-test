package com.myEstes.as400.jagacy.screens;
/*
import static org.testng.Assert.assertFalse;

import com.jagacy.Key;
import com.myEstes.as400.jagacy.fields.EntryField;
import com.myEstes.as400.jagacy.fields.LabelField;
import com.myEstes.as400.jagacy.session.Session;



public class LoginScreen {
	
	private Session session;
	private String screenCrc= "0x9662fa10";
	
	// Screen fields
	private LabelField waitForLoginScreenSignOn = new LabelField(0, 35, "Sign On");
	
	
	public LoginScreen(final Session s) throws Exception {
		this.session = s;		
		try {
			if(! session.waitForTextLabel(waitForLoginScreenSignOn)) {
				throw new IllegalStateException("Not as400 Login Screen!");
				
			}
			
			 if (session.getCrc32() != Long.decode(screenCrc)) {
		            throw new IllegalStateException("Login Screen has been changed!");
		        }
			 
		}catch(Exception e) {
			session.logger.fatal(e.getMessage());
			assertFalse(true, "Step Failed...");
		}
		session.isDemo();
	}
	
	public final MasterMenuScreen enterUserNamePassword() throws Exception {
		
		 session.logger.info("enter user name and password");
		 
		 EntryField userName = new EntryField(5, 52);
		 session.setEntryFieldValue(userName, "qatstfrtbl");
		 
		 EntryField password = new EntryField(6, 52);
		 session.setEntryFieldValue(password, "qatest2019");

		 session.writeKey(Key.ENTER);
		 session.waitForChange(session.DEFAULT_TIMEOUT);
		 
		return new MasterMenuScreen(session);
	}
}
*/