/*package com.myEstes.as400.jagacy.screens;

import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import com.jagacy.util.JagacyException;
import com.myEstes.as400.jagacy.fields.LabelField;
import com.myEstes.as400.jagacy.session.Session;



public class ReserveFreightBillScreen {
	
	private Session session;
	private String screenCrc= "0xcc6642e4";
	
	private LabelField waitForLabel = new LabelField(20, 46, "successfully reserved.");
	//private String results;
	
	public ReserveFreightBillScreen(final Session s) throws JagacyException {
		this.session = s;
		try {
			if(! session.waitForTextLabel(waitForLabel)) {
				throw new IllegalStateException("Not Freight Bill Reserved Screen!");
			}
			
			 if (session.getCrc32() != Long.decode(screenCrc)) {
		            throw new IllegalStateException("Reserved FreightBill Screen has been changed!");
		        }
			 
		}catch(Exception e) {
			session.logger.fatal(e.getMessage());
			assertFalse(true, "Step Failed...");
		}		
		
		try {
			session.isDemo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public final String getResults() throws JagacyException{
	
		session.logger.info("get the FreightBill Number");
		
		List<String> results = new ArrayList<String>();		 
        
		for (int row = 9; row <= 11; row++) {
            if (!session.readPosition(row, 9, 40).trim().equals("")) {
                results.add(session.readPosition(row, 9, 43).trim());
            }
        }
	
	 if (!session.readRow(9).equals("")) {
		 
		 System.out.println("FreightBill Numbers are :" + results );
     }
	 
	 List<String> billNo=results.subList(0, 1);	 			
	 String freightBillNo=billNo.toString().substring(16,27).replaceAll("\\s+", "");
     System.out.println("FreightBill Number is :" +freightBillNo );
               
       return freightBillNo;	
		




}
*/