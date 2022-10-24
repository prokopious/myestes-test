package freightBillPages;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;

public class LTL38MasterMenu extends TestBase{
	private Logger logger = Logger.getLogger(LTL38MasterMenu.class);
	public void selectFrieghtBill(String options) throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		keyboard.type(options);
		Shortcuts.pressEnter();
		logger.info("select freightBill options on L2L 38 Master Menu");
	}
}
