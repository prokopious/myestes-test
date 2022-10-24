package freightBillPages;

import java.awt.AWTException;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;

public class HomeCommandPrompt extends TestBase{
	
	
	public void ibmMainMenu(String command) throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		
		keyboard.type(command);
		Shortcuts.pressEnter();
		
	}
}
