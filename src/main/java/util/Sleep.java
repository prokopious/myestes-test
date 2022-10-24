package util;

import testBase.TestBase;

public class Sleep extends TestBase{

	 public static void sleepShort ()
			    throws InterruptedException
			  {testUtil.init(Sleep.class);
			
			    Thread.sleep(2000L);
			  }
			  
			  public static void sleepMedium()
			    throws InterruptedException
			  {testUtil.init(Sleep.class);
			    Thread.sleep(10000L);
			  }
			  
			  public static void sleepLong()
			    throws InterruptedException
			  {testUtil.init(Sleep.class);
			    Thread.sleep(60000L);
			  }
			  
			  public static void sleepCustom(int seconds)
			    throws InterruptedException
			  {testUtil.init(Sleep.class);
			    int miliseconds = seconds * 1000;
			    Thread.sleep(miliseconds);
			  }

}
