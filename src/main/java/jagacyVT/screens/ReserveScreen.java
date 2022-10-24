
package jagacyVT.screens;

import java.util.ArrayList;
import java.util.List;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class ReserveScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public ReserveScreen(final SessionVt s) throws JagacyException {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();
	}

	public void enterBillsToReserve(String numOfFreightBill) throws JagacyException, InterruptedException {

		logger.info("enter Bills to Reserve");
		session.waitForCursor(5, 40, 1000);
		session.writeString(numOfFreightBill);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		
	}

	public void enterShipperCode(String sCode) throws JagacyException, InterruptedException {

		logger.info("enter Shipper Code");
		session.waitForCursor(7, 40, 1000);
		session.writeString(sCode);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(1000);
		//Thread.sleep(1000);
	}

	public String getFreightBillNumber() throws JagacyException, InterruptedException {

		logger.info("Get Freight Bill number");
		
		//  session.waitForChange(5000); System.out.println(session.readPosition(row,
		//  arg1)); session.waitForChange(5000); Thread.sleep(5000);
		 

		List<String> results = new ArrayList<String>();

		for (int row = 9; row <= 11; row++) {
			//if (!session.readPosition(row, 9, 40).trim().equals("")) {
				 results.add(session.readPosition(row, 9, 43).trim());
			//}
		}

		//if (!session.readRow(9).equals("")) {

			System.out.println("FreightBill Numbers are :" + results);
			String freightBillNo = results.toString().substring(16, 27).replaceAll("\\s+", "");
		//}
		//session.wait(2000);
		//List<String> billNo = results.subList(0, 1);
		//String freightBillNo = billNo.toString().substring(16, 27).replaceAll("\\s+", "");
		System.out.println("FreightBill Number is :" + freightBillNo);

		return freightBillNo;
	}

}

