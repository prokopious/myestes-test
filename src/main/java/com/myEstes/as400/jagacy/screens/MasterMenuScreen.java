package com.myEstes.as400.jagacy.screens;
/*
import static org.testng.Assert.assertFalse;

import com.jagacy.Key;
import com.jagacy.util.JagacyException;
import com.myEstes.as400.jagacy.fields.EntryField;
import com.myEstes.as400.jagacy.fields.LabelField;
import com.myEstes.as400.jagacy.session.Session;

public class MasterMenuScreen {

	private Session session;
	private String screenCrc= "0x8bc9e615";
	
	// Screen fields
	private LabelField waitForMasterMenuScreenMaster = new LabelField(1, 38, "master");
	
	
	public MasterMenuScreen(final Session s) throws JagacyException {
		this.session = s;
		try {
			if(! session.waitForTextLabel(waitForMasterMenuScreenMaster)) {
				throw new IllegalStateException("Not LTL/38 master Screen!");
			}
			
			 if (session.getCrc32() != Long.decode(screenCrc)) {
		            throw new IllegalStateException("Master Menu Screen has been changed!");
		        }
			 
		}catch(Exception e) {
			session.logger.fatal(e.getMessage());
			assertFalse(true, "Step Failed...");
		}
		
			session.isDemo();		
	}
	
	public final FreightBillMenuScreen enterFreightBillMenuOption() throws Exception {
		
		session.logger.info("enter FreightBill Menu Option");
		
		 EntryField option = new EntryField(22, 9);
		 session.setEntryFieldValue(option, "1");
		 
		 session.writeKey(Key.ENTER);
		 session.waitForChange(session.DEFAULT_TIMEOUT);
		 
		return new FreightBillMenuScreen(session);
	}
	
	
	
}
*/