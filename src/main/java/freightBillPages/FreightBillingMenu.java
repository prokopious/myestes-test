package freightBillPages;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;

public class FreightBillingMenu extends TestBase{
	
	private Logger logger = Logger.getLogger(FreightBillingMenu.class);

	public void selectOption(String option, String user, String terminal)
			throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		keyboard.type(option);
		logger.info("select freightBill Option");
		Shortcuts.pressTab();
		keyboard.type(user);
		logger.info("enter user ");
		Shortcuts.pressTab();
		keyboard.type(terminal);
		logger.info("enter terminal number");
		Shortcuts.pressEnter();
		logger.info("select option ");
	}

}
