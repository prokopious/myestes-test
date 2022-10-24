package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;
import jagacy.util.JagacyUtil;

public class RoadManifestSetupEntryScreen {

	private SessionVt session;
	public Loggable logger;
	public JagacyProperties props;
	
	
	public RoadManifestSetupEntryScreen(SessionVt s) throws JagacyException {
		this.session=s;
		props = s.getProperties();
		logger=s.getLoggable();
		
	}
	
	public void enterDestTID(String tid) throws JagacyException, InterruptedException {
		logger.info("Enter Destination TID");
		session.waitForChange(2000);
		session.writePosition(8, 11, tid);
		session.waitForChange(2000);
	}

	public void enterTrailer(String trl) throws JagacyException, InterruptedException {
		logger.info("Enter Trailer");
		session.waitForChange(2000);
		session.writePosition(11, 11, trl);
		session.waitForChange(2000);
	}

	public void clickEnter()throws JagacyException, InterruptedException {
		logger.info("Click Enter");
		session.waitForChange(2000);
		session.writeKey(Key.ENTER);
		session.waitForChange(2000);
	}


	public void changeStatus(String status) throws JagacyException, InterruptedException {
		logger.info("Change Status");
		session.waitForChange(2000);
		session.writePosition(5, 28, status);
		session.waitForChange(2000);
	}

	public void selectTrailer()throws JagacyException, InterruptedException {
		logger.info("Select trailer" );
		for(int i = 8 ; i <=19;i++)
		{
			String getTrailer = session.readPosition(i, 31, 1);
			System.out.println(getTrailer);
			String expectedVal = "2";
			String actualVal = getTrailer.substring(0, 1);
//			if(getTrailer.contentEquals(expectedVal)) {
			if(actualVal.equalsIgnoreCase(expectedVal)) {
//				session.writePosition(i, 27, "");
//				session.waitForChange(i, 27, "");
				session.writeCursor(i, 27);
				session.writePosition(i, 27, "1");
//				session.writeKey("1");
				JagacyUtil jagacyUtil = new JagacyUtil(session);
				jagacyUtil.pressEnter();
				session.wait(5000);
				break;
			}
			
			if(i==19)
			{
				session.writeKey(Key.PAGE_DOWN);
				i = 7; //to bring the i value to 8
			}
		}
	}

	public String recordTrailerNumber()throws JagacyException, InterruptedException {
		logger.info("record trailer number ");
		session.waitForChange(2000);
		String trailerNum = session.readPosition(11, 11, 6);
		System.out.println("The trailer number is : "+trailerNum);
		return trailerNum;
	}

	public void clickF1() throws JagacyException, InterruptedException {
		logger.info("Click F1");
		session.waitForChange(2000);
		session.writeKey(Key.PF1);
		session.waitForChange(4000);
	}
	
	public void changePosTo(String pos) throws JagacyException, InterruptedException {
		logger.info("Change Pos To");
		session.waitForChange(2000);
		session.writePosition(3, 53, pos);
		session.waitForChange(2000);
	}

	public void selectTrailer2()throws JagacyException, InterruptedException {
		logger.info("Select trailer" );
		for(int i = 10 ; i <=19;i++)
		{
			String getTrailer = session.readPosition(i, 31, 1);
			System.out.println(getTrailer);
			String expectedVal = "5"; //trailers that start with 5
			String actualVal = getTrailer.substring(0, 1);
			if(actualVal.equalsIgnoreCase(expectedVal)) {
				session.writeCursor(i, 27);
				session.writePosition(i, 27, "1");
//				session.wait(8000);
//				session.writeKey(Key.ENTER);
				session.waitForChange(5000);
				break;
			}

//			if(i==19)
//			{
//				session.writeKey(Key.PAGE_DOWN);
//				i = 7; //to bring the i value to 8
//			}
		}
	}

//	public void selectTrailer2()throws JagacyException, InterruptedException {
//		logger.info("Select trailer" );
//		for(int i = 10; i <=19; i++)
//		{
//			String getTrailer = session.readPosition(i, 31, 1);
//			System.out.println(getTrailer);
//			String expectedVal = "5";
//			String actualVal = getTrailer.substring(0, 1);
//			if(actualVal.equalsIgnoreCase(expectedVal)) {
//				session.writeCursor(i, 27);
//				session.writePosition(i, 27, "1");
////				session.wait(8000);
////				session.writeKey(Key.ENTER);
//				session.waitForChange(5000);
//				session.wait(5000);
//
//				break;
//			}
//		}
//	}
	
	public void selectTrailer2(String expectedVal)throws JagacyException, InterruptedException { // expectedVal is number trailers start with
		logger.info("Select trailer" );
		for(int i = 10 ; i <=19;i++)
		{
			String getTrailer = session.readPosition(i, 31, 1);
			System.out.println(getTrailer);
			String actualVal = getTrailer.substring(0, 1);
			if(actualVal.equalsIgnoreCase(expectedVal)) {
				session.writeCursor(i, 27);
				session.writePosition(i, 27, "1");
				session.waitForChange(5000);
				break;
			}
		}
	}

	

	public void enterValueToSelectTrailer(String value) throws JagacyException, InterruptedException {
		
		logger.info("enter User value ");
		session.waitForChange(1000);
		session.writePosition(8, 27, value);
		session.waitForChange(1000);
		session.writeKey(Key.ENTER);
		session.waitForChange(3000);
		
	}


	public String setUpRoadManifest(String DTid, String trailerStartsWith) throws JagacyException, InterruptedException {
		logger.info("Set up road manifest for terminal "+DTid);

		//Enter Dest Tid
		session.waitForCursor(6, 13 ,2000);
		session.writeCursor(8, 11);
		session.writePosition(8, 11, DTid);
		session.waitForChange(2000);
		//Enter Trailer 
		session.writeCursor(11, 11);
		session.writePosition(11, 11, "?");
		session.waitForChange(2000);
		//Press Enter
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		jagacyUtil.pressEnter();
		session.waitForChange(3000);
		//Replace LTR with MTY
		session.writeCursor(5, 28);
		session.writePosition(5, 28, "MTY" );
		session.waitForChange(2000);
		jagacyUtil.pressEnter();
		session.waitForChange(3000);
		//To display numeric trailers
		session.writeCursor(3, 53);
		session.writePosition(3, 53, trailerStartsWith);
		session.waitForChange(2000);
		jagacyUtil.pressEnter();
		session.waitForChange(2000);
		//Select trailer
		session.writeCursor(8, 27);
		session.writePosition(8, 27, "1");
		session.waitForChange(2000);
		jagacyUtil.pressEnter();
		session.waitForChange(2000);
		//Capture trailer number
		String trailer = session.readPosition(11, 11, 6);
		System.out.println("Selected Trailer: "+trailer);
		//Press Enter to set up trailer
		jagacyUtil.pressEnter();
		session.waitForChange(2000);
		return trailer;
		
	}

	
	public String setUpRoadManifest2(String DTid, String trailerStartsWith) throws JagacyException, InterruptedException {
        logger.info("Set up road manifest for terminal "+DTid);
        String trailer = null;
        int j=8;
        for(int i = 0 ; i<=11;i++)
        {
        //Enter Dest Tid
        session.waitForCursor(6, 13 ,2000);
        session.writeCursor(8, 11);
        session.writePosition(8, 11, DTid);
        session.waitForChange(2000);
        //Enter Trailer
        session.writeCursor(11, 11);
        session.writePosition(11, 11, "?");
        session.waitForChange(2000);
        //Press Enter
		JagacyUtil jagacyUtil = new JagacyUtil(session);
        jagacyUtil.pressEnter();
        session.waitForChange(3000);
        //Replace LTR with MTY
        session.writeCursor(5, 28);
        session.writePosition(5, 28, "MTY" );
        session.waitForChange(2000);
        jagacyUtil.pressEnter();
        session.waitForChange(3000);
        //To display numeric trailers
        session.writeCursor(3, 53);
        session.writePosition(3, 53, trailerStartsWith);
        session.waitForChange(2000);
        jagacyUtil.pressEnter();
        session.waitForChange(2000);
        //Select trailer
        session.writeCursor(i, 27);
        
        session.writePosition(j, 27, "1");
        session.waitForChange(2000);
        jagacyUtil.pressEnter();
        session.waitForChange(2000);
        
        //Capture trailer number
        trailer = session.readPosition(11, 11, 6);
        System.out.println("Selected Trailer: "+trailer);
        //Press Enter to set up trailer
        jagacyUtil.pressEnter();
        session.waitForChange(5000);
   
        String actualVal = session.readPosition(23, 1, 12);
        System.out.println("Actual value: "+actualVal);
        
        String expectedVal = "Setup Record";
        if(actualVal.equalsIgnoreCase(expectedVal)) {
           break;
        }
        j++;
        }
       
        return trailer;
    }

}
