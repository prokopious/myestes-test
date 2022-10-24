package com.myEstes.as400.jagacy.screens;
/*
import static org.testng.Assert.assertFalse;

import com.jagacy.Key;
import com.jagacy.util.JagacyException;
import com.myEstes.as400.jagacy.fields.EntryField;
import com.myEstes.as400.jagacy.fields.LabelField;
import com.myEstes.as400.jagacy.session.Session;

public class ReserveScreen {
	
	private Session session;
	private String screenCrc= "0x962cea44";
	
	private LabelField waitForReserveFreightBillScreenReserve = new LabelField(1, 24, "Reserve");
	
	public ReserveScreen(final Session s) throws JagacyException {
		this.session = s;
		try {
			if(! session.waitForTextLabel(waitForReserveFreightBillScreenReserve)) {
				throw new IllegalStateException("Not Reserve Screen!");
			}
			
			 if (session.getCrc32() != Long.decode(screenCrc)) {
		            throw new IllegalStateException("Fright Bill Screen has been changed!");
		        }
			 
		}catch(Exception e) {
			session.logger.fatal(e.getMessage());
			assertFalse(true, "Step Failed...");
		}
		
		
		try {
			session.isDemo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public final void enterNumberOfFreightBill() throws JagacyException{
	
		session.logger.info("enter Number of the bill needs to be Reserved");
		
		EntryField option = new EntryField(5, 40);
		 session.setEntryFieldValue(option, "1");
		 session.writeKey(Key.ENTER);
		 session.waitForChange(session.DEFAULT_TIMEOUT);
		 
	}

	
public final ReserveFreightBillScreen enterShipperCode() throws JagacyException{
	
	session.logger.info("enter Shipper code ");
	 
	 EntryField option1 = new EntryField(7, 40);
	 session.setEntryFieldValue(option1, "0500845");		 
	 session.writeKey(Key.ENTER);
	 session.waitForChange(session.DEFAULT_TIMEOUT);
	 
	return new ReserveFreightBillScreen(session);
}

}
*/