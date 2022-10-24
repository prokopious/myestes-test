package jagacyVT.screens;

import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;



public class DeliveryManifestDispatchScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public DeliveryManifestDispatchScreen(SessionVt s) {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
public void enterValueTractorField(String value) throws JagacyException, InterruptedException {
		
		logger.info("enter ? to tractor field ");
		session.waitForChange(1000);
		session.writePosition(7, 34, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
	}

public void enterPosToValue(String value) throws JagacyException, InterruptedException {
	
	logger.info("enter position value ");
	
	session.waitForChange(1000);
	session.writePosition(3, 53, value);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	session.waitForChange(3000);
	
}
public void enterValueToSelectTractor(String value) throws JagacyException, InterruptedException {
	
	logger.info("Select Tractor ");
	
	session.waitForChange(1000);
	session.writePosition(8, 27, value);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	session.waitForChange(3000);
	
}
public void enterDriverNumber(String num) throws JagacyException, InterruptedException {
	
	logger.info("enter driver number");
	
	session.waitForChange(1000);
	session.writePosition(7, 59, num);
	session.waitForChange(1000);
	
	session.waitForChange(1000);
	
}

public void enterPandDCostingRunNum(String num) throws JagacyException, InterruptedException {
	
	logger.info("enter P&D costing number ");
	
	session.waitForChange(1000);
	session.writePosition(9, 34, num);
	session.waitForChange(1000);
	session.writeKey(Key.TAB);
	session.waitForChange(1000);
	
}

public void enterPandDCostingAndRegionRunNum() throws JagacyException, InterruptedException {
	
	logger.info("enter P&D region number ");

	String text = "already" ;
	String actualScreenText = session.readRow(23);
	String text2 ="invalid";
	String actualScreenText2 = session.readRow(23);
	
	session.waitForChange(1000);
	session.writePosition(9, 34, "209");
	session.waitForChange(1000);
	session.writeKey(Key.TAB);
	session.waitForChange(1000);					
	
	session.waitForChange(1000);
	session.writePosition(11, 34, "20009");
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	
	
   int i = 1;
   do {
	int a = 209 + i;
	int b = 20009 + i;
	
	String c = Integer.toString(a);
	String d = Integer.toString(b);
	
	
	session.waitForChange(2000);
	session.writePosition(9, 34, "   ");
	session.waitForChange(3000);
	session.writePosition(9, 34, c);

	
	session.waitForChange(1000);
	
	//session.writeKey(Key.TAB);
	session.waitForChange(1000);
	session.writePosition(11, 34, "      ");
	session.waitForChange(3000);
	session.writePosition(11, 34, d);
	session.waitForChange(2000);
	session.writeKey(Key.ENTER);
	session.waitForChange(1000);
 
}while (actualScreenText.contains(text)||actualScreenText2.contains(text2));
	
}



public void enterKey() throws JagacyException {
	logger.info("press enter key");
	session.waitForChange(2000);
	session.writeKey(Key.ENTER);
	session.waitForChange(1000);
	
}


public void clearUserIdField() throws Exception {
	logger.info("clear user id field");
	session.waitForChange(1000);
	session.writeCursor(18, 26);
	for (int i = 0; i < 10; i++) {
		session.writeKey(Key.DELETE);
		Thread.sleep(200);
	}
	session.writeString("");
	session.waitForChange(1000);
	session.writeCursor(18, 26);
	session.waitForChange(1000);
	session.writeKey(Key.ENTER);
	session.waitForChange(2000);
}



public String readPosition(){
    String value = null;
   try{
        value = session.readPosition(14, 43, 47);
        System.out.println("Manifest num is :" + value);
   }catch(JagacyException je){
       throw new RuntimeException("Could not retrieve text from the position row .", je);
   }
   return value;
}




public void enterF1Key() throws JagacyException {
	logger.info("press f1 key");
	session.waitForChange(2000);
	session.writeKey(Key.PF1);
	session.waitForChange(3000);
	
}


}
