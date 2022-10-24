package freightBillPages;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;
import util.Sleep;

public class FreightBillUpdateScreen1 extends TestBase{
	
	private Logger logger = Logger.getLogger(FreightBillUpdateScreen1.class);
	public void enterData(String ts, String pcs, String terms, String wgt, String cons, 
			String pudr, String cubicFeet,String ship,String thirdPT) throws AWTException, InterruptedException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		
		keyboard.type(ts);
		logger.info("enter data to TS");
		keyboard.type(pcs);
		logger.info("enter data to Pcs");
		Shortcuts.pressTab();
		keyboard.type(terms);
		logger.info("enter data to Term");
		Shortcuts.pressTab();
		keyboard.type(wgt);
		logger.info("enter data to Weight");
		Sleep.sleepShort();
		tabLoop(6);
		keyboard.type(cons);
		logger.info("enter data to Consignee");
		tabLoop(6);
		keyboard.type(ship);
		logger.info("enter data to Shipper");
		tabLoop(6);
		keyboard.type(thirdPT);
		logger.info("enter data to 3rd Party");
		//tabLoop(22);
		tabLoop(8);
		keyboard.type(pudr);
		logger.info("enter data to PUDr");
		tabLoop(5);
		keyboard.type(cubicFeet);
		logger.info("enter data to Cubic Feet");
		Sleep.sleepShort();
		logger.info("enter data on update screen 1");
	}
	
	public void submit() throws AWTException {
		testUtil.init(this);
		Shortcuts.pressEnter();
		Shortcuts.pressEnter();
		logger.info("press enter button");
	}
	private void tabLoop(int j) throws AWTException {
		testUtil.init(this);
		for (int i = 0; i <= j; i++) {
			Shortcuts.pressTab();
			//logger.info("press Tab button");
		}
		
	}
	
	public void skip() throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		Shortcuts.pressTab();
		keyboard.type("x");
		Shortcuts.pressEnter();
	}
	
}
