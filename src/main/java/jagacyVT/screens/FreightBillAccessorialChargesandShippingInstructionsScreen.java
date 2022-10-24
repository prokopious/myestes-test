/**
 * 
 */
package jagacyVT.screens;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;
import com.jagacy.util.Logger;

/**
 * @author habibja
 *
 */
public class FreightBillAccessorialChargesandShippingInstructionsScreen {

	
	
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public FreightBillAccessorialChargesandShippingInstructionsScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterValueFreightBill(String fb) throws Exception {
		
		logger.info("enter freight bill ");
		session.waitForCursor(9, 41, 1000);
		session.waitForChange(1000);
		session.writeString(fb);
		session.waitForChange(1000);
	}
	
	public void enterShippingInstructions(HashMap<String,String> shippingInstructions) throws JagacyException, InterruptedException {
		logger.info("Enter Shipment Instructions");
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		Thread.sleep(2000);
		
		
		session.waitForChange(1000);
		for(Map.Entry m : shippingInstructions.entrySet()){    
		    System.out.println(m.getKey()+" "+m.getValue());    
		    session.writeString(m.getKey().toString());
		    Thread.sleep(500);
		    session.writeString(m.getValue().toString());
		    session.writeKey(Key.TAB);
		    Thread.sleep(500);
		   }  
	}
	
	public void editCPhoneShippingInstruction(String cphone) throws JagacyException, InterruptedException {
		logger.info("Edit CPhone ShippingInstruction");
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
		session.writeString(cphone);
		session.waitForChange(1000);
	}
	
	public void deleteShipingInstructionCodeX() throws JagacyException, InterruptedException {
		logger.info("Edit code X ShippingInstruction");
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.writeKey(Key.TAB);
		session.waitForChange(3000);
		session.writeKey(Key.DELETE);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(1000);
		session.writeKey(Key.DELETE);
		session.waitForChange(2000);
	}
}


