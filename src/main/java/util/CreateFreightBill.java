package util;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;

import myEstesPages.MyEstesHomePage;


public class CreateFreightBill {

	private Logger logger = Logger.getLogger(CreateFreightBill.class);
	public  void  tabLoop(int j) throws AWTException {
		
		for (int i = 0; i <= j; i++) {
			Shortcuts.pressTab();
		}
	}
	
	public  void createFreightBillWithUpdate  (String AccNum, String TerminalNum) throws AWTException, InterruptedException, HeadlessException, UnsupportedFlavorException, IOException, ClassNotFoundException {
		
		logger.info("create freightBill");
		CreateFreightBill createFreightBill = new CreateFreightBill();
		
		String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";

		Runtime.getRuntime().exec(path1);
	    Keyboard keyboard = new Keyboard();
		Robot robot = new Robot();

		Thread.sleep(5000);

		keyboard.type("QATSTFRTBL");
		Shortcuts.pressTab();
		keyboard.type("qatest2019");
		Shortcuts.pressEnter();

		Thread.sleep(5000);

		keyboard.type("QATSTFRTBL");
		//Shortcuts.pressTab();
		keyboard.type("qatest2019");
		Shortcuts.pressEnter();

		Thread.sleep(5000);
		/*//Step 4: Enter command in the command line to bring up the L2L38 master menu.
		keyboard.type("call xxc870");*/
		//Shortcuts.pressEnter();
		Thread.sleep(2000);
		//Step 5: Enter the menu option for the freight billing menu.
		keyboard.type("1");
		Shortcuts.pressEnter();
		//Step 6: Select the 'Reserve Freight Bill' menu option (82)
		keyboard.type("82");
		Shortcuts.pressTab();
		keyboard.type("test");
		Shortcuts.pressTab();
		
		keyboard.type(TerminalNum);		
		Shortcuts.pressEnter();
		keyboard.type("1");
		Shortcuts.pressTab();
		Thread.sleep(1000);
		
		keyboard.type(AccNum);
		//keyboard.type("0102260");

		Thread.sleep(3000);
		Shortcuts.pressEnter();
		Thread.sleep(500); 

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_ALT);

		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		Thread.sleep(500);

		//String status ="SPRNEP";
		String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String temp = data.substring(769, 780);
		//String temp = data.substring(30,40);
		System.out.println("temp Number: "+temp);
		String pro = temp.replace(" ", "");
		System.out.println("'"+pro+"'");
		System.out.println("Pro Number: "+pro);
		//return temp;
		
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_F3);
		robot.keyRelease(KeyEvent.VK_F3);
		
		keyboard.type("2");
		Shortcuts.pressTab();
		keyboard.type("TEST");
		Shortcuts.pressTab();
		// keyboard.type("001");
		keyboard.type(TerminalNum);
		Shortcuts.pressEnter();
		
		keyboard.type(pro);
		Shortcuts.pressEnter();
		
		keyboard.type("1");
		keyboard.type("1");
		Shortcuts.pressTab();
		keyboard.type("C");
		Shortcuts.pressTab();
		keyboard.type("500");
		
		//
		
		createFreightBill.tabLoop(6);
		
		keyboard.type("0123116");
		createFreightBill.tabLoop(22);
		keyboard.type("99999999");
		createFreightBill.tabLoop(5);
		keyboard.type("72");

		Shortcuts.pressEnter();
		Shortcuts.pressEnter();
		Thread.sleep(5000);
		
		createFreightBill.tabLoop(6);
		keyboard.type("1");
		Shortcuts.pressTab();
		keyboard.type("PT");
		keyboard.type("FAK");
		Shortcuts.pressTab();
		keyboard.type("500");
		createFreightBill.tabLoop(4);
		keyboard.type("123");
		
		Shortcuts.pressEnter();
		Shortcuts.pressEnter();
		Thread.sleep(5000);
		
		keyboard.type("APT");
		Shortcuts.pressEnter();
		//return pro;
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_F1);
		robot.keyRelease(KeyEvent.VK_F1);
		
		keyboard.type("90");
		Shortcuts.pressEnter();
		Thread.sleep(1000);
		
		keyboard.type("90");
		Shortcuts.pressEnter();
		Thread.sleep(1000);
		
		 Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
		 Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");
	}
		
}


