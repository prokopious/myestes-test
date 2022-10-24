package util;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ReserveAFreightBill {
	
	public  String reserveAFreightBill  (String AccNum, String TerminalNum) throws AWTException, InterruptedException, HeadlessException, UnsupportedFlavorException, IOException, ClassNotFoundException {

		//CreateFreightBill createFreightBill = new CreateFreightBill();

		String path1 = "C:\\Users\\tahermo\\Desktop\\As400_1.bat";

		Runtime.getRuntime().exec(path1);
		
	// To get rid of first time Eclipse's open problem	
		Thread.sleep(5000);
		Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
		Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");
		Thread.sleep(5000);
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
		
		Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
		Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");
		
		return pro;
	}
}
