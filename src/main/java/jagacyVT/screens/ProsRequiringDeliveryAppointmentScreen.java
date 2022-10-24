
package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import jagacy.util.JagacyUtil;


public class ProsRequiringDeliveryAppointmentScreen {

	 private SessionVt session;
     public Loggable logger;
     public JagacyProperties props;
     
     public ProsRequiringDeliveryAppointmentScreen(SessionVt s) {
         this.session=s;
         props = s.getProperties();
         logger=s.getLoggable();
     }
     
     public void searchProNumber(String accountPO) throws JagacyException {
         logger.info("Enter position to account number");
         session.writeKey(Key.TAB);
         session.waitForChange(2000);
         session.waitForCursor(3, 70, 5000);
         session.writeCursor(3,70);
         session.writeString(accountPO);
         session.waitForChange(2000);
         session.writeKey(Key.ENTER);
         session.waitForChange(2000);
     }
     
     public void selectProNumber(String ot,String billNum, String option) throws JagacyException, InterruptedException {
         logger.info("Select specified bill number ");
         String actual;
         int i = 6;
         JagacyUtil jagacyUtil = new JagacyUtil(session);
         boolean val = false;
         do {
             actual = session.readPosition(i, 10, 7);
             //if(actual.contains(ot + " " +billNum)) {
             if(actual.contains(billNum)) { //Modified 
             System.out.println("Pro Number Found!!!!");
             session.writeCursor(i, 2);
             session.writePosition(i, 2, option);
             Thread.sleep(5000);
             System.out.println("Option set as "+option);
             jagacyUtil.pressEnter();
             Thread.sleep(5000);
             val = true;
             Thread.sleep(5000);
             break;
     }
         
     if((i == 20) && (!session.readPosition(21, 73, 6).contains("Bottom"))) {
         System.out.println("Next Page");
         jagacyUtil.pressPageDown();
         i = 4;
     }
     else if((i == 20) && (session.readPosition(21, 73, 6).contains("Bottom"))) {
         System.out.println("Reached last row!!!");
         break;
     }
         i++;
     }
     while (!val);

 }
     
     
     public void enterOptionToPronum(String num, String op) throws JagacyException, Exception {
 		logger.info("Select specified Trailer ");
 		String actual;
 		int i = 6;
 		JagacyUtil jagacyUtil = new JagacyUtil(session);
 		boolean val = false;
 		do {
 			actual = session.readPosition(i, 6, 11);
 			System.out.println(actual);
 			if(actual.equals(num)) {
 				System.out.println("Trailer Found!!!!");
 				session.writeCursor(i, 2);
 				session.writePosition(i,2, op);
 				Thread.sleep(5000);
 				System.out.println("Option set as "+op);
 				jagacyUtil.pressEnter();
 				Thread.sleep(5000);
 				val = true;
 				Thread.sleep(5000);
 				break;
 				}
 			if((i == 20) && (!session.readPosition(21, 72, 6).contains("Bottem"))) {
 				System.out.println("Next Page");
 				jagacyUtil.pressPageDown();
 				i = 5;
 			}
 			else if((i == 20) && (session.readPosition(21, 72, 6).contains("Bottom"))) {
 				System.out.println("Reached last row!!!");
 				break;
 			}
 		i++;
 		}
 		while (!val);
 	}

        
        public void selectTheProNumber(String billNum, String option) throws JagacyException, InterruptedException {
            logger.info("Select specified bill number ");
            String actual;
            int i = 6;
            JagacyUtil jagacyUtil = new JagacyUtil(session);
            boolean val = false;
            do {
                actual = session.readPosition(i, 10, 7);
                System.out.println("Actual Pros's "+actual);
                if(actual.equals(billNum)) {
                System.out.println("Pro Number Found!!!!");
                session.writeCursor(i, 2);
                session.writePosition(i, 2, option);
                Thread.sleep(5000);
                System.out.println("Option set as "+option);
                jagacyUtil.pressEnter();
                Thread.sleep(5000);
                val = true;
                Thread.sleep(5000);
                break;
        }
            
        if((i == 20) && (!session.readPosition(21, 73, 6).contains("Bottom"))) {
            System.out.println("Next Page");
            jagacyUtil.pressPageDown();
            i = 4;
        }
        else if((i == 20) && (session.readPosition(21, 73, 6).contains("Bottom"))) {
            System.out.println("Reached last row!!!");
            break;
        }
            i++;
        }
        while (!val);

    }
        
        
}
