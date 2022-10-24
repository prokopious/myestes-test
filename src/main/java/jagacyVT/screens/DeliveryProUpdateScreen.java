package jagacyVT.screens;


import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class DeliveryProUpdateScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;

	public DeliveryProUpdateScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}

	public void enterValuesForDeliveryPro(String BB , String name , String timeIN , String timeStart , String timeOut ) throws JagacyException {
		logger.info("enter odometer In value");
		session.waitForChange(2000);
		session.writePosition(9, 15, BB);
		session.waitForChange(1000);
		
		session.waitForChange(2000);
		session.writePosition(10, 18, name);
		session.waitForChange(1000);
		
		
		session.waitForChange(2000);
		session.writePosition(10, 51, timeIN);
		session.waitForChange(1000);
		
		session.waitForChange(2000);
		session.writePosition(10, 64, timeStart);
		session.waitForChange(1000);
		
		session.waitForChange(2000);
		session.writePosition(10, 75, timeOut);
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);

	}


	public void enterApptDate(String date) throws JagacyException {

		logger.info("Enter Date");
//        session.writeCursor(16, 9);
//        session.writeString(date);
//        session.waitForChange(1000);
		session.waitForCursor(16, 9, 5000);
		session.waitForChange(1000);
		session.writeAfterLabel("Appt Dt", date);
		session.waitForChange(1000);
	}

public void enterTimeRange(String timeIn, String timeOut) throws JagacyException {
        
        logger.info("Enter TimeRange");
        session.writeCursor(16, 22);
        session.writeString(timeIn);
        session.waitForChange(1000);
        session.writeCursor(16, 29);
        session.writeString(timeOut);
        
    }

	public void enterReason(String reason) throws JagacyException {
		logger.info("Enter Reason");
		//session.waitForCursor(16, 42, 5000);
		//session.writeString(reason);
		//session.waitForChange(1000);
		session.waitForCursor(16, 42, 5000);
		session.waitForChange(1000);
		session.writeAfterLabel("Reason", reason);
		session.waitForChange(1000);
		session.writeKey(Key.TAB);
		session.waitForChange(1000);
	}


    public void enterName(String name) throws JagacyException {
        logger.info("Enter Name");
        session.waitForCursor(16, 52, 5000);
        session.writeString(name);
        session.waitForChange(1000);
    }

 

    public void enterPhoneNum(String ph1, String ph2, String ph3, String extension) throws JagacyException {
        
        logger.info("Enter Phone Number with Extension");
        session.writeCursor(18, 8);
        session.writeString(ph1);
        session.waitForChange(1000);
        session.writeCursor(18, 14);
        session.writeString(ph2);
        session.waitForChange(1000);
        session.writeCursor(18, 20);
        session.writeString(ph3);
        session.waitForChange(1000);
        session.writeCursor(18, 29);
        session.writeString(extension);
        session.waitForChange(1000);
    } 
    
    public void enterBBCode(String bb) throws JagacyException {
    	logger.info("Enter BB code");
    	session.waitForChange(1000);
    	 session.writeCursor(9, 15);
		session.writePosition(9, 15, bb);
		session.waitForChange(1000);
    }
    
    public void enterReceiverName(String receiverName) throws JagacyException {
    	logger.info("Enter receiver name");
    	session.waitForChange(1000);
    	 session.writeCursor(10, 18);
		session.writePosition(10, 18, receiverName);
		session.waitForChange(1000);
    }
    
    public void enterTime(String inTime, String startTime, String outTime) throws JagacyException {
    	logger.info("Enter Time");
    	session.waitForChange(1000);
    	session.writeCursor(10, 51);
		session.writePosition(10, 51, inTime);
		session.waitForChange(1000);
		session.writeCursor(10, 64);
		session.writePosition(10, 64, startTime);
		session.waitForChange(1000);
		session.writeCursor(10, 75);
		session.writePosition(10, 75, outTime);
		session.waitForChange(1000);
    }


public void entertimerange(String timeIn, String timeOut) throws JagacyException {
        
        logger.info("Enter TimeRange");
        session.writeCursor(16, 22);
        session.writeString(timeIn);
        session.waitForChange(1000);
        session.writeCursor(16, 29);
        session.writeString(timeOut);
        
    }
}
