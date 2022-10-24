package util;

import org.testng.Assert;

public class Assertions {
	
	public static void verifyTrue(boolean status)
	  {
	    Assert.assertTrue(status);
	  }
	  
	  public static void failTest() {}
	  
	  public static void passTest()
	  {
	    Assert.assertTrue(true);
	  }

}
