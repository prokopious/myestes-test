package jagacyVT.screens;

import org.testng.Assert;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class UpdateScreen3 {
	  
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public UpdateScreen3(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
	}

	public void enterValuesToUpdateScreen3() throws JagacyException {

		logger.info("enter values to update screen3");

		session.writeKey(Key.ENTER);
		session.waitForChange(1000);

	}
	
	public void pressEnterKey() throws JagacyException {
		logger.info("Press enter key");	
		 session.writeKey(Key.ENTER);
		 session.waitForChange(1000);
		
	}
	
	public void verifyShipperInsCode(String code) throws Exception {
		logger.info("Verify Shipper Instruction code");
		session.writeCursor(18, 11);
		session.waitForCursor(18, 11, 1000);
		String actualCode = session.readPosition(18,  11, 3);
		Assert.assertTrue(actualCode.contains(code));
	}

	public void verifyShipperInsDescription(String codeDesc) throws Exception {
		logger.info("Verify Shipper Instruction Description");
		session.writeCursor(18, 21);
		session.waitForCursor(18, 21, 1000);
		String actualDesc = session.readPosition(18,  21, 35);
		Assert.assertTrue(actualDesc.contains(codeDesc));
	}
	
	public void verifyLastFreightBillUpdateMessage() throws Exception {
		logger.info("Verify Last Freight Bill Updated Message");
		session.writeCursor(7, 10);
		session.waitForCursor(7, 10, 1000);
		String actualDesc = session.readPosition(7, 10, 35);
		Assert.assertTrue(actualDesc.contains("Last Freight Bill Updated"));
	}
	
	public final void enterAccessoryInstruction1(String code) throws Exception{
		logger.info("Enter Accessory Instruction Code ");
		session.waitForCursor(18, 11, 5000);
		session.writeString(code);
	}


	public final void enterShippingInstruction1(String shipCode) throws Exception {
		 logger.info("Enter Shipping Instruction Code ");
		 session.waitForCursor(18, 11, 5000);
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
		 session.writeString(shipCode);
		 session.waitForChange(1000);
	}

	public final void enterShippingInstruction2(String shipCode) throws Exception {
		 logger.info("Enter Shipping Instruction Code 2");
		 session.waitForCursor(18, 11, 5000);
		 session.writeKey(Key.TAB);
		 session.writeString(shipCode);
		 session.waitForChange(1000);
	}

	public final void enterAccessorialCode(String code) throws Exception {
		logger.info("Enter Accessorial Code");
		session.waitForCursor(8, 11, 5000);
		session.waitForChange(1000);
		session.writeString(code);
		session.waitForChange(2000);
	}

	public final void enterShippingInstruction1(String shipCode, String instructions, String shipCode2, String instructions2) throws Exception {
		logger.info("Enter Shipping Instruction Code ");
		session.waitForCursor(18, 11, 5000);
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
		session.writeString(shipCode+instructions);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
		session.writeString(shipCode2+instructions2);
		session.waitForChange(1000);
	}

	
	public void updateEmail(String email) throws Exception{
		for (int i= 18; i<23; i++) {
			if(session.readPosition(i, 11, 6).equals("CEMAIL")) {
				System.out.println("CEMAIL FOUND - Updating Email");
				session.writePosition(i, 21, email);
			}
		}
	}
	
	
	
	public final void enterAccessoryInstructions(String ...code) throws Exception{
		logger.info("Enter Accessory Instruction Code ");
		session.waitForCursor(18, 11, 5000);
		int totalargs = code.length;
		int i = 0; 
		do {
			session.waitForChange(1000); 
			session.writeString(code[i]);
			session.waitForChange(1000); 
			session.writeKey(Key.TAB); 
			session.writeKey(Key.TAB); 
			i++; 
		}while(i < totalargs); 

	}
	
	//I did not have to use this method for this test because of a misunderstanding, but this method works well with the above method: enterAccessoryInstructions(String ...code); 
	public final void enterShippingInstruction(String shipCode, int numOfCodes) throws Exception {
		 logger.info("Enter Shipping Instruction Code ");
//		 Pertaining to how many tabs will be needed from the cursor's place after inputting multiple accessorial codes. 
		 int remainderTabs = 16 - (numOfCodes * 2 + 2); //Adding 2 at end because enterAccessoryInstructions() method, within the do/while loop tabs 2 more times after input.
		 
		 while(remainderTabs >= 0) {
			 session.waitForChange(1000); 
			 session.writeKey(Key.TAB);	
			 session.writeKey(Key.TAB); 
			 remainderTabs -=2;
		 }
		 session.waitForChange(1000);
		 session.writeString(shipCode);
	}

}


