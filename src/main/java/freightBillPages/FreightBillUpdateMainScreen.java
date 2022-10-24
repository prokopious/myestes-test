package freightBillPages;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;

public class FreightBillUpdateMainScreen extends TestBase{
	
	private Logger logger = Logger.getLogger(FreightBillUpdateMainScreen.class);

	public void enterPro(String pro) throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		keyboard.type(pro);
		logger.info("enter PRO number");

	}

	public void submit() throws AWTException {
		testUtil.init(this);
		Shortcuts.pressEnter();
		logger.info("press enter button");
	}
}
