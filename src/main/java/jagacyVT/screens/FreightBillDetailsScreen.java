/**
 * 
 */
package jagacyVT.screens;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;


import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author habibja
 *
 */
public class FreightBillDetailsScreen {
	
	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	public FreightBillDetailsScreen(SessionVt s) {
		this.session = s;
		props = s.getProperties();
		logger = s.getLoggable();

	}
	
	public void checkForNoSignCodeAndDescription() throws JagacyException {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i=17; i<25; i++) {
			lst.add(session.readPosition(i, 7, 6).trim());
			lst.add(session.readPosition(i, 19, 12).trim());
		}
		System.out.println(lst);
		if(!lst.contains("NOSIGN"))
			Assert.fail("NOSIGN MISSING");
		if(!lst.contains("NO SIGNATURE"))
			Assert.fail("NO SIGNATURE MISSING");
	}
	
	public void checkForCodeAndDescription(String code, String desc) throws JagacyException {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i=17; i<25; i++) {
			lst.add(session.readPosition(i, 7, 6).trim());
			lst.add(session.readPosition(i, 19, 18).trim());
		}
		System.out.println(lst);
		if(!lst.contains(code))
			Assert.fail("MISSING: " + code);
		if(!lst.contains("NO SIGNATURE"))
			Assert.fail("MISSING: " + desc);
	}
	
	public void checkForNoSignatureActAndComment() throws JagacyException {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i=18; i<25; i++) {
			lst.add(session.readPosition(i, 21, 3).trim());
			lst.add(session.readPosition(i, 38, 12).trim());
		}
		System.out.println(lst);
		if(!lst.contains("NSG"))
			Assert.fail("NSG MISSING");
		if(!lst.contains("No Signature"))
			Assert.fail("No Signature comment MISSING");
				
	}
	
	public void checkActAndComment(String act, String comment) throws JagacyException {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i=18; i<25; i++) {
			lst.add(session.readPosition(i, 21, 3).trim());
			lst.add(session.readPosition(i, 38, 18).trim());
		}
		System.out.println(lst);
		if(!lst.contains(act))
			Assert.fail("MISSING: " + act);
		if(!lst.contains(comment))
			Assert.fail("MISSING: " + comment);
	}
	
	public void checkForCodeNotSupposeToBeThere(String code) throws JagacyException {
		String codes;
		for (int i=17; i<25; i++) {
			codes = session.readPosition(i, 7, 6).trim();
			if(codes.equals(code)) {
				Assert.fail(code + " is not suppose to be there");
			}
		}
	}
	
	
	public void checkForAcctUserCommentNotSupposeToBeThere(String act, String user, String comment) throws JagacyException {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i=18; i<25; i++) {
			lst.add(session.readPosition(i, 21, 3).trim());
			lst.add(session.readPosition(i, 26, 10).trim());
			lst.add(session.readPosition(i, 38, 18).trim());
		}
		System.out.println(lst);
		
		if(lst.contains(act))
			Assert.fail(act + " Not suppose to be there");
		if(lst.contains(user))
			Assert.fail(user + " Not suppose to be there");
		if(lst.contains(comment))
			Assert.fail(comment + " Not suppose to be there");
	}

	public void checkForCode(String code) throws JagacyException {
		ArrayList<String> lst = new ArrayList<>();
		for (int i=18; i<25; i++) {
			lst.add(session.readPosition(i, 7, 8).trim());
		}

		System.out.println(lst);
		if(!lst.contains(code))
			Assert.fail(code + " MISSING");
	}


	//Enter FB Number for Screen 'Freight Bill Inquiry' - This is needed after Freight Menu Options,
	public void enterFreightBillNum(String terminal, String freightBillNum) throws Exception {
		logger.info("Enter Freight Bill Number in 'Freight Bill Inquiry' Screen"); 
		session.waitForCursor(4, 41, 1000); 
		session.waitForChange(1000); 
		session.writeString(terminal);
		session.waitForChange(1000); 
		session.writeString(freightBillNum);
		session.waitForChange(1000); 
		session.writeKey(Key.ENTER); 
	}
	public void verifyCodeNDescription(String code, String desc) throws JagacyException {
		//Create an array to gather all potential values of the code 
		ArrayList<String> codeArray = new ArrayList<String>();
		boolean verified = false; 
		int counter = 0; 
		for (int i=17; i <23; i++) {
				codeArray.add(session.readPosition(i, 7, 50)); //readPosition(int row, int column, int length)
				String parameterValues = StringUtils.deleteWhitespace(code+desc); 
				System.out.println(StringUtils.deleteWhitespace(codeArray.get(counter)) + " " + parameterValues); 
				if(StringUtils.deleteWhitespace(codeArray.get(counter)).contains(parameterValues)) {
					verified = true; 
					break; 
				}else {
					counter++; 
				}
			}
		Assert.assertTrue(verified); 
	}
	
	
	

	public void checkForCode2(String code) throws JagacyException { // used if need to page down to view code on freight bill, might need
		session.writeKey(Key.PAGE_DOWN);
		String screen[] = session.readScreen();
		boolean codePresent = false;
		for (String line : screen) {
			System.out.println(line);
			if (line.contains(code))
				codePresent = true;
		}

		if(!codePresent)
			Assert.fail(code + " NOT PRESENT");
	}
	
	
	
}
