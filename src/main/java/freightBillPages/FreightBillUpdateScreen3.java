package freightBillPages;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;

public class FreightBillUpdateScreen3 extends TestBase{
	private Logger logger = Logger.getLogger(FreightBillUpdateScreen3.class);
	public void enterData(String accesorialCode) throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		keyboard.type(accesorialCode);
		logger.info("enter accesorial details on update screen 3");
		
	}
	
	public void submit() throws AWTException {
		testUtil.init(this);
		Shortcuts.pressEnter();
		logger.info("press enter button");
	}

}
