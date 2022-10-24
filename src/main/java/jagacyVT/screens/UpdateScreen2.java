package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class UpdateScreen2 {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public UpdateScreen2(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();

	}


	public void enterValuesToUpdateScreen2 (String clss,String PCS2, String PK2,String DES2,String Wgt2) throws JagacyException, InterruptedException {

		logger.info("enter values to update screen2");

		session.writeCursor(5, 6);
		session.waitForChange(1000);
		session.writeString(clss);
		session.waitForChange(1000);

		//session.waitForCursor(5, 13, 1000);
		session.writeCursor(5, 13);
		session.waitForChange(1000);
		session.writeString(PCS2);
		session.waitForChange(1000);


		// session.waitForCursor(5, 19, 1000);
		session.writeCursor(5, 19);
		session.waitForChange(1000);
		session.writeString(PK2);
		session.waitForChange(1000);


		//session.waitForCursor(5, 22, 1000);
		session.writeCursor(5, 23);
		session.waitForChange(1000);
		session.writeString(DES2);
		session.waitForChange(1000);


		//session.waitForCursor(5, 58, 1000);
		session.writeCursor(5, 58);
		session.waitForChange(1000);
		session.writeString(Wgt2);
		session.waitForChange(1000);




	}

	public void pressEnterKey() throws JagacyException {
		logger.info("Press enter key");
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);

	}

	public final void enterClass(String clas) throws Exception {
		logger.info("Enter class  on Update screen2");
		session.waitForCursor(5, 6, 5000);
		session.writeAfterLabel("Clas", "");
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForCursor(5, 6, 5000);
		session.writeString(clas);
		session.waitForChange(2000);
		session.writeKey(Key.TAB);
		session.waitForChange(2000);
	}

	public final void enterPcs2(String pcs) throws Exception {
		logger.info("Enter Pieces  on Update screen2");
		session.waitForCursor(5, 13, 5000);
		// session.writeString(pcs);
		session.waitForChange(1000);
		session.writePosition(5, 13, pcs);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}

	public final void enterPK(String pk) throws Exception {
		logger.info("Enter Package  on Update screen2");
		session.waitForCursor(5, 19, 5000);
		//session.writeString(pk);
		session.waitForChange(1000);
		session.writePosition(5, 19, pk);
		session.waitForChange(1000);
	}

	public final void enterDesc(String desc) throws Exception {
		logger.info("Enter description  on Update screen2");
		session.waitForCursor(5, 22, 5000);
		// session.writeString(desc);
		session.writePosition(5, 22, desc);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(2000);
	}

	public final void enterWgt2(String wgt) throws Exception {
		logger.info("Enter Weight  on Update screen2");
		session.waitForCursor(5, 58, 5000);
		session.writeString(wgt);
		session.waitForChange(1000);
	}

	public final void enterUNNo(String unNo, String uNo) throws Exception {
		logger.info("Enter UN#  on Update screen2");
		session.writeCursor(6, 72);
		session.writeString(unNo);
		session.waitForChange(1000);
		session.writePosition(6, 79, uNo);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
	}
	
	
	  public final void enterOnlyUNNo(String unNo) throws Exception {
	         logger.info("Enter UN#  on Update screen2");
	         session.writePosition(6, 72, unNo);
	         session.waitForChange(5000);
	         session.writeKey(Key.ENTER);
	         session.waitForChange(7000);
	     }


}
