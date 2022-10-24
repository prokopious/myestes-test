package freightBillPages;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;

public class FreightBillUpdateScreen2 extends TestBase{
	
	private Logger logger = Logger.getLogger(FreightBillUpdateScreen2.class);
	public void enterData(String pcs, String pk, String description, String wgt,
			String po) throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		tabLoop(6);
		keyboard.type(pcs);
		logger.info("enter data to Pcs");
		Shortcuts.pressTab();
		keyboard.type(pk);
		logger.info("enter data to Pk");
		keyboard.type(description);
		logger.info("type Description");
		Shortcuts.pressTab();
		keyboard.type(wgt);
		logger.info("enter data to Weight");
		tabLoop(4);
		keyboard.type(po);
		logger.info("enter date on update screen2");
	}

	public void submit() throws AWTException {
		testUtil.init(this);
		Shortcuts.pressEnter();
		logger.info("press inter button");
		Shortcuts.pressEnter();
	}
	private void tabLoop(int j) throws AWTException {
		testUtil.init(this);
		for (int i = 0; i <= j; i++) {
			Shortcuts.pressTab();
			
		}

	}

}
