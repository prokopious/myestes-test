package freightBillPages;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;
import util.Sleep;

public class Login extends TestBase{
	
	private Logger logger = Logger.getLogger(Login.class);
	
	public void loginDialog() throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		Shortcuts shortcuts= new Shortcuts();
		keyboard.type("qatstfrtbl");
		Shortcuts.pressTab();
		keyboard.type("qatest2019");
		Shortcuts.pressEnter();
		logger.info("enter userName and password on main dialog popup");
	}
	
	public void signOn(String uName,String Pass) throws AWTException, IOException, InterruptedException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		keyboard.type(uName);//"qatstfrtbl"
		Sleep.sleepShort();
		keyboard.type(Pass);
		Sleep.sleepShort();
		Shortcuts.pressEnter();
		logger.info("enter userName and password on freightbill sing On screen");
	}
	
	public void bypassWarning() throws AWTException {
		testUtil.init(this);
		Shortcuts shortcuts= new Shortcuts();
		shortcuts.pressEnter();
		
	}
	
	
}
