package com.myEstes.as400.jagacy.screens;
/*
import static org.testng.Assert.assertFalse;

import com.jagacy.Key;
import com.jagacy.util.JagacyException;
import com.myEstes.as400.jagacy.fields.EntryField;
import com.myEstes.as400.jagacy.fields.LabelField;
import com.myEstes.as400.jagacy.session.Session;

public class FreightBillMenuScreen {
	
	private Session session;
	private String screenCrc= "0x30dc9fb1";
	
	private LabelField waitForLabel = new LabelField(1, 30, "Freight");
	
	public FreightBillMenuScreen(final Session s) throws JagacyException {
		this.session = s;
		try {
			if(! session.waitForTextLabel(waitForLabel)) {
				throw new IllegalStateException("Not Freight Bill Menu Screen!");
			}
			
			 if (session.getCrc32() != Long.decode(screenCrc)) {
		            throw new IllegalStateException("Freight Billing Menu Screen has been changed!");
		        }
			 
		}catch(Exception e) {
			session.logger.fatal(e.getMessage());
			assertFalse(true, "Step Failed...");
		}		
		session.isDemo();
	}
	
	
	public final ReserveScreen enterFreightBillOptionAndUserAndTeminalNumber() throws JagacyException {
		
		session.logger.info("enter freightBill option and Termainal Number");
		
		EntryField option = new EntryField(22, 9);
		 session.setEntryFieldValue(option, "82");
		 session.writeKey(Key.ENTER);
		 session.waitForChange(session.DEFAULT_TIMEOUT);
		 
		 EntryField option1 = new EntryField(22, 22);
		 session.setEntryFieldValue(option1, "test");		 
		 session.writeKey(Key.TAB);
		 session.waitForChange(session.DEFAULT_TIMEOUT);
		 
		 EntryField option2 = new EntryField(22, 48);
		 session.setEntryFieldValue(option2, "005");		 
		 session.writeKey(Key.ENTER);
		 session.waitForChange(session.DEFAULT_TIMEOUT);
		
		return new ReserveScreen(session);
	}
	
	/*public final ReserveScreen enterUser() throws Exception {
	 EntryField option1 = new EntryField(22, 22);
	 session.setEntryFieldValue(option1, "test");		 
	 session.writeKey(Key.ENTER);
	 session.waitForChange(session.DEFAULT_TIMEOUT);
	 
	 return new ReserveScreen(session);
	}

}
*/