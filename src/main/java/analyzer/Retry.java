package analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	
	private int counter=0;
	private static final int retrylimit=2;

	public boolean retry(ITestResult result) {
		
		if (counter < retrylimit) {
			
			counter ++;
			return true;
		}
		return false;
	
		}
	}



