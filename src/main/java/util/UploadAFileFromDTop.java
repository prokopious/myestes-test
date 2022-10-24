package util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;

import testBase.TestBase;

public class UploadAFileFromDTop extends TestBase {
	
	public void uploadAFile(String FilePath) throws AWTException {
		
		Robot robot = new Robot();
		//String filePath = "C:\\Users\\tahermo\\Desktop\\Dummy_Folder\\New Text Document.txt";
		//driver.findElement(By.id("invoiceFile")).click();
		
		robot.delay(2000);
		StringSelection stringSelection = new StringSelection(FilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		 
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.delay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
