package estesTruckloadTests;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.jagacy.SessionVt;
import com.sun.xml.xsom.impl.scd.ParseException;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import testBase.TestBase;


	public class EstesTruckloadSmokeAPITest extends TestBase {

		/* Generates Quote Number */
		
		public String generateQuoteNumber() throws Exception {
			
			 //Step-1 : https://apitest.estes-express.com/TruckLoad/v1/quotes
			
			String baseURI="https://apitest.estes-express.com/TruckLoad/v1/quotes";
			String businessDate = testUtil.addFutureWeekDay();
			String pickupDate[] = businessDate.split("/");
			String futureDate = pickupDate[2]+"-"+pickupDate[0]+"-"+pickupDate[1];
			
			//Step-2 : 	Basic Authorization
			String userName = "testnat";
			String password = "testnat";
			
			//Step-3 & 4 : 	Body Note: pickupDate should be 2 days in the future (not a weekend or a holiday)and click send

			Response response= given(). auth().basic(userName, password)
		  		.header("Content-Type","application/json")
		  		.contentType(ContentType.JSON)
		  		.accept(ContentType.JSON)
		  		.body("{\r\n" + 
						"    \"requestedBy\": \"TBP-670\",\r\n" + 
						"    \"account\": \"4137007\",\r\n" + 
						"    \"equipmentType\": \"Dry van\",\r\n" + 
						"    \"origin\": {\r\n" + 
						"        \"country\": \"US\",\r\n" + 
						"        \"zipCode\": \"60045\",\r\n" + 
						"        \"city\": \"Lake Forest\",\r\n" + 
						"        \"stateProvince\": \"IL\"\r\n" + 
						"    },\r\n" + 
						"    \"destination\": {\r\n" + 
						"        \"country\": \"US\",\r\n" + 
						"        \"zipCode\": \"06507\",\r\n" + 
						"        \"city\": \"New Haven\",\r\n" + 
						"        \"stateProvince\": \"CT\"\r\n" + 
						"    },\r\n" + 
						"   \"pickupDate\": \""+futureDate+"\",\r\n" + 
						"    \"commodityInfo\": {\r\n" + 
						"        \"commodity\": [\r\n" + 
						"            {               \r\n" + 
						"                \"weight\": \"31621\"\r\n" + 
						"            }\r\n" + 
						"        ]\r\n" + 
						"            },\r\n" + 
						"    \"accessorialList\": [\r\n" + 
						"        {\r\n" + 
						"            \"accessorialCode\": \"\"\r\n" + 
						"        }\r\n" + 
						"    ]\r\n" + 
						"}")
			.when()
			.post(baseURI) 
			.then()
			.log().all()
			.statusCode(200).statusLine("HTTP/1.1 200 OK")
			.extract().response();
      
	   //Step- 5 : Record the quoteId from the previous response:
	           
		String quoteNumber = (response.path("rateQuoteResponse.quote.quoteId[0]").toString());
		assertNotNull(quoteNumber);
		System.out.println("quoteNumber is: " + quoteNumber);
		
		

		//Step -6 : Validate the disclaimers are displayed.
		JsonPath jsonpath = response.jsonPath();
		String disclaimers =  jsonpath.get("rateQuoteResponse.quote.disclaimer[0]").toString();
	    disclaimers.contains("1");
	    disclaimers.contains("2");
	    disclaimers.contains("3");
	    disclaimers.contains("4");
	
			return quoteNumber;

		}


		/*
		 * Verify that when API user book an Estes Truckload quote with Dry Van equipment type then a success message is 
		 * displayed the order is successfully created on CHR side and a PRO is reserved
		 */
		@Test(enabled = false, priority = 1)
		public void executeQZ_10324() throws ParseException, InterruptedException , Exception {
			
			
			
			//Steps-1-6 from above script
			String quoteNum = generateQuoteNumber();
			
			
			//Step-7 : API POST Booking/create Order endpoints:
			
			String pickupURI = "https://apitest.estes-express.com/TruckLoad/v1/orders/quotes";
			
			//Step-8 : Basic Authorization 
			String userName = "testnat";
			String password = "testnat";
		
			
			testUtil.setHardWait(5000);
			
			//Step-9 and Step-10 : replace quoteId by the quote id noted in step 5 and Click Send
			
	           Response response= given(). auth().basic(userName, password).
		  		header("Content-Type","application/json").
		  		contentType(ContentType.JSON).
		  		accept(ContentType.JSON)
		  		.body("{\r\n" + 
		  				"    \"quoteId\": \""+quoteNum+"\",\r\n" + 
		  				"    \"contactName\": \"Requester Contact Name\",\r\n" + 
		  				"    \"contactCompany\": \"ESTES to CHR\",\r\n" + 
		  				"    \"contactEmail\": \"RequesterContactEmail@Trucking.com\",\r\n" + 
		  				"    \"contactPhone\": \"(800)551-2234\",\r\n" + 
		  				"    \"contactRole\": \"Shipper\",\r\n" + 
		  				"    \"origin\": {\r\n" + 
		  				"        \"contactName\": \"Origin Contact Name\",\r\n" + 
		  				"        \"contactPhone\": \"222-310-4444\",\r\n" + 
		  				"        \"contactEmail\": \"OriginContact@Email.com\",\r\n" + 
		  				"        \"streetAddress1\": \"316 North Roadway Lane\",\r\n" + 
		  				"        \"streetAddress2\": \"Terminal 111\",\r\n" + 
		  				"        \"appointmentRequired\": \"Y\",\r\n" + 
		  				"        \"startTime\": \"18:30:00:000\",\r\n" + 
		  				"        \"endTime\": \"22:00:00:000\",\r\n" + 
		  				"        \"specialInstructions\": \"Please use bay doors outlined with Yellow paint\"\r\n" + 
		  				"    },\r\n" + 
		  				"    \"destination\": {\r\n" + 
		  				"        \"contactName\": \"Destination Contact Name\",\r\n" + 
		  				"        \"contactPhone\": \"(703)123-1234\",\r\n" + 
		  				"        \"contactEmail\": \"Destinationemail@API.com\",\r\n" + 
		  				"        \"streetAddress1\": \"437 Middle Street\",\r\n" + 
		  				"        \"streetAddress2\": \"Terminal 088\",\r\n" + 
		  				"        \"appointmentRequired\": \"Y\",\r\n" + 
		  				"        \"startTime\": \"08:30:00:000\",\r\n" + 
		  				"        \"endTime\": \"16:45:00:000\",\r\n" + 
		  				"        \"specialInstructions\": \"Ring bell beside door for assistance\"\r\n" + 
		  				"    },\r\n" + 
		  				"    \"commodityType\": \"Skid\",\r\n" + 
		  				"    \"quantity\": \"37\",\r\n" + 
		  				"    \"freightDescription\": \"TBP-670\",\r\n" + 
		  				"    \"freightValue\": \"77200\",\r\n" + 
		  				"    \"poNumber\": \"PO 31621-7\",\r\n" + 
		  				"    \"referenceNumber\": \"Dry Van\",\r\n" + 
		  				"    \"temperatureLow\": \"\",\r\n" + 
		  				"    \"temperatureHigh\": \"\"\r\n" + 
		  				"}")
			.when()
			.post(pickupURI) 
			.then()
			.log().all()
			.statusCode(200).statusLine("HTTP/1.1 200 OK")
			.extract().response();
 
	   //Step-11 : Record the proNumber:
	           
		String proNumber = (response.path("proNumber").toString());
		assertNotNull(proNumber);
		System.out.println("proNumber is: " + proNumber);
		
				//Split the Terminal and pro number
				String [] splitPro = proNumber.split("-");
				String firstPro = splitPro[0]; 
				String secondPro = splitPro[1]; 
		
		testUtil.setHardWait(2000);

		// Jagacy

		SessionVt session = null;

		String userName1 = "devabni";
		String password1 = "nithyadev";
		String name = "myJagacyVT";
		String terminal = "dec-vt220";
		


		session = new SessionVt(name, "exlaqa", terminal);
		session.open();

		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
		Ltl38MasterMenuScreen ltl38MasterMenuScreen = new Ltl38MasterMenuScreen(session);
		FreightBillingMenuScreen freightBillingMenuScreen = new FreightBillingMenuScreen(session);
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		
		
		// Step-12 : Logon to the  *EXLAQA* server, using your credentials:
		loginScreen.logon(userName1, password1);
		Thread.sleep(2000);
		ibmMainMenuScreen.verifyIBMMainMenuScreen();
		
		//Step-13 : From the command line, enter: CALL XXC870
		ibmMainMenuScreen.enterValueToComandLineField("CALL XXC870");

		// Step-14 : From the  *LTL Master Menu*, select: "1"
		ltl38MasterMenuScreen.enterValueOptionField("1");
		
		//Step-15 : From the  *Freight Billing Menu*, enter the following:
		
		freightBillingMenuScreen.enterValueOptionField("1");
	
		freightBillingMenuScreen.enterValueUserField("Truckload");
		freightBillingMenuScreen.enterTabKey();
		
		freightBillingMenuScreen.enterTerminalNumber("002");
		
		//Step-16 : Key in the PRO Number noted in step 17
		freightBillingMenuScreen.enterFreightBill(firstPro, secondPro);
		
		//Verify the PRO  is in R/Reserve Status
		freightBillingMenuScreen.verifyPROIsInRStatus();
		
		
		//Step-17 : To log out, press/enter
		//F1 (to exit FB inquiry)
		//90 (to exit FB menu)
		//90 (to exit and logout)
		
		jagacyUtil.pressF1();	
		freightBillingMenuScreen.enterValueOptionField("90");
		jagacyUtil.pressEnter();
		ltl38MasterMenuScreen.enterValueOptionField("90");
	
		if (session != null) {
			session.abort();
			session.close();
		}
			
		}


	

	
	}


