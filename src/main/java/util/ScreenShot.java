package util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import testBase.TestBase;

import org.openqa.selenium.OutputType;

public class ScreenShot extends TestBase{
	
	 Calendar now = Calendar.getInstance();
	  private int numericDay;
	  private int numericMonth;
	  private int numericYear;
	  private int numericHour;
	  private int numericMinute;
	  private int numericSecond;
	  
	  public File takeScreenShot(WebDriver driver, String failureArea)
	    throws IOException
	  { testUtil.init(this);
	    this.numericMonth = (this.now.get(2) + 1);
	    this.numericDay = this.now.get(5);
	    this.numericYear = this.now.get(1);
	    this.numericHour = this.now.get(10);
	    this.numericMinute = this.now.get(12);
	    this.numericSecond = this.now.get(13);
	    
	  
    String timeStamp = "_" + this.numericMonth + this.numericDay + this.numericYear + "_" + this.numericHour + this.numericMinute + this.numericSecond;
	    
	    File file = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    File screenshotFile=new File("screenshots/ScreenShot" + failureArea + "_" + timeStamp + ".png");
	    FileUtils.copyFile(file, screenshotFile);
	    return screenshotFile;
	  }

	  
	  
	  
	  
	  
}
