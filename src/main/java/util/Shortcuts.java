package util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import testBase.TestBase;

public class Shortcuts extends TestBase {

	public Shortcuts() {

	}

	public static void pressEnter() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void pressTab() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public static void selectAll() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void copy() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void pressHome() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_HOME);
		robot.keyRelease(KeyEvent.VK_HOME);
	}

	public static void pressLeft() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
	}

	public static void pressRight() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}

	public static void pressUp() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
	}

	public static void pressDown() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}

	public static void pressF1() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F1);
		robot.keyRelease(KeyEvent.VK_F1);
	}

	public static void pressF2() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F2);
		robot.keyRelease(KeyEvent.VK_F2);
	}

	public static void pressF3() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F3);
		robot.keyRelease(KeyEvent.VK_F3);
	}

	public static void pressF10() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_F10);
	}

	public static void pressF9() throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F9);
		robot.keyRelease(KeyEvent.VK_F9);
	}

	public static void tabLoop(int j) throws AWTException {
		testUtil.init(Shortcuts.class);
		Robot robot = new Robot();
		for (int i = 0; i <= j; i++) {

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
	}

}
