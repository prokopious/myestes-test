package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

public class DeliveryAppointmentInquirybyTIDscreen {

    private SessionVt session;
    public static Loggable logger;
    public JagacyProperties props;    

 

    public DeliveryAppointmentInquirybyTIDscreen(SessionVt s) throws JagacyException {
        this.session = s;
        props = s.getProperties();
        logger = s.getLoggable();
    }
    
    public void enterFreightBill1(String originTerminal, String billNum) throws JagacyException {
        logger.info("Enter Option on Master Menu");
        session.waitForCursor(5, 43, 5000);
        session.writeCursor(5, 43);
        session.writeString(originTerminal);
        session.waitForChange(5000);
        session.waitForCursor(5, 47, 5000);
        session.writeCursor(5, 47);
        session.writeString(billNum);
        session.waitForChange(5000);
        session.writeKey(Key.ENTER);
        session.waitForChange(2000);
    }

}




