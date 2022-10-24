package freightBillPages;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import testBase.TestBase;
import util.Keyboard;
import util.Shortcuts;
import util.Sleep;

public class ReserveFreightBills extends TestBase{
	private Logger logger = Logger.getLogger(ReserveFreightBills.class);
	
	public void enterNumberOfBills(String numberOfBills) throws AWTException, InterruptedException {
		logger.info("enter number of the freightBill");
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		
		Shortcuts.pressHome();
		
		if (Integer.parseInt(numberOfBills) > 999) {
			Assert.fail();
		}
		else {
			keyboard.type(numberOfBills);
			Sleep.sleepShort();
			Shortcuts.pressTab();
		}
	}
	
	public void enterShipperCode(String shipperCode) throws AWTException {
		testUtil.init(this);
		Keyboard keyboard = new Keyboard();
		
		//Shortcuts.pressHome();
		//Shortcuts.pressTab();
		
		if (Integer.parseInt(shipperCode) > 9999999) {
			Assert.fail();
		}
		else {
			keyboard.type(shipperCode);
		}
	}
	
	public void submitRequest() throws AWTException {
		testUtil.init(this);
		Shortcuts.pressEnter();
		
	}
	
	public String getBillNumber() throws AWTException, InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		testUtil.init(this);
		Robot robot = new Robot();
		
		Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
		
//		robot.keyPress(KeyEvent.VK_DOWN);
//		robot.keyRelease(KeyEvent.VK_DOWN);
//		robot.keyPress(KeyEvent.VK_DOWN);
//		robot.keyRelease(KeyEvent.VK_DOWN);
//		
//		robot.keyPress(KeyEvent.VK_SHIFT);
//		for (int i = 0; i <= 9; i++) {
//			robot.keyPress(KeyEvent.VK_RIGHT);
//			robot.keyRelease(KeyEvent.VK_RIGHT);
//		}	
//		robot.keyRelease(KeyEvent.VK_SHIFT);
		
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_ALT);
		
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		Sleep.sleepShort();
		
		String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String temp = data.substring(769, 780);
		String pro = temp.replace(" ", "");
		System.out.println("Pro Number is : "+pro);
		return pro;
		
		
	}
	
	public void returnToPreviousMenu() throws AWTException {
		
		Shortcuts.pressF3();
	}
	
	public String getMessage() throws AWTException, HeadlessException, UnsupportedFlavorException, IOException, InterruptedException {
		testUtil.init(this);
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_ALT);
		
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		Sleep.sleepShort();
		
		String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		System.out.println(data);
		return data;
	}
	
}
