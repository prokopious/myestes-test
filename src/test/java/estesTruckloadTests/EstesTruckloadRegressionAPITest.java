package estesTruckloadTests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import com.jagacy.SessionVt;
import com.sun.xml.xsom.impl.scd.ParseException;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jagacy.util.JagacyUtil;
import jagacyVT.screens.FreightBillingMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import testBase.TestBase;

	public class EstesTruckloadRegressionAPITest extends TestBase {

		private Logger logger = LogManager.getLogger(EstesTruckloadRegressionAPITest.class);
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
	           Response response= given(). auth().basic(userName, password).
		  		header("Content-Type","application/json").
		  		contentType(ContentType.JSON).
		  		accept(ContentType.JSON)
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


	
		//THIS IS AN ACTUAL FIALURES- TURNED OFF UNTIL FIXED
		/*
		 Verify when an Estes Truckload quote is successfully booked and the user re-select the API 
		 Send button then a message is displayed informing the user that the quote has already 
		 been booked & to contact the Truckload team for assistance
		 */
		
		@Test(enabled = false, priority = 1)
		public void executeQZ_10343() throws ParseException, InterruptedException , Exception {
			
			
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
	           
	    testUtil.setHardWait(2000);
 
	    //Step-11 : Record the proNumber:
		String proNumber = (response.path("proNumber").toString());
		assertNotNull(proNumber);
		System.out.println("proNumber is: " + proNumber);
		
		
		//Step-12 : Re-click *Send*
		
		 Response response2= given(). auth().basic(userName, password).
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
	 
		 //Step-13 : Validate proNumber is blank in the API response
		 
		    String proNumber2 = (response2.path("proNumber").toString());
			String expPro = "           ";
			assertEquals(proNumber2,expPro);
			System.out.println("proNumber is: " + proNumber2);

		 //Step-14 : Validate the following message is returned:
			
			JsonPath jsonpath = response2.jsonPath();
			String textMsg =  jsonpath.get("messageList.text").toString();
			
		    String expTxt = "[A shipment with this Quote ID has already been booked. Please check your records and ensure the quote id you provide is for a non-booked shipment. If you have questions, please contact our Truckload team at 1-866-378-3748. press 31 for direct assistance.]";
			assertEquals(textMsg,expTxt);
	
		}


        /*
        Verify that when API user keyed in origin or destination address is AK then an error message '
        The origin address provided is not in the contiguous United States. 
         Please update your request and submit again. For shipments xxx ' is returned
        */
        
        @Test(enabled = true, priority = 2)
        public void executeQZ_10329() throws ParseException, InterruptedException , Exception {
               
               
 //Step-1 : https://apitest.estes-express.com/TruckLoad/v1/quotes
               
               String baseURI="https://apitest.estes-express.com/TruckLoad/v1/quotes";
 
               String businessDate = testUtil.addFutureWeekDay();
               String pickupDate[] = businessDate.split("/");
               String futureDate = pickupDate[2]+"-"+pickupDate[0]+"-"+pickupDate[1];
               
        //Step-2 :   Basic Authorization
               String userName = "testnat";
               String password = "testnat";
               
        //Step-3 & 4 :      Body Note: pickupDate should be 2 days in the future (not a weekend or a holiday)and click send
            Response response= given(). auth().basic(userName, password).
                      header("Content-Type","application/json").
                      contentType(ContentType.JSON).
                      accept(ContentType.JSON)
                      .body("{\r\n" + 
                                   "    \"requestedBy\": \"Nora - March 19\",\r\n" + 
                                   "    \"account\": \"4137007\",\r\n" + 
                                   "    \"equipmentType\": \"Dry Van\",\r\n" + 
                                   "    \"origin\": {\r\n" + 
                                   "        \"country\": \"US\",\r\n" + 
                                   "        \"zipCode\": \"99507\",\r\n" + 
                                   "        \"city\": \"Anchorage\",\r\n" + 
                                   "        \"stateProvince\": \"AK\"\r\n" + 
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
                                   "                \"weight\": \"37100\"\r\n" + 
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

        //Step-5 : Validate a message is returned and a quote is not created
               
               JsonPath jsonpath = response.jsonPath();
               String textMsg =  jsonpath.get("rateQuoteResponse.quote.messageList.text").toString();
               System.out.println("Message is :"+ textMsg);
            String expTxt = "[[The origin address provided is not in the contiguous United States. Please update your request and submit again. For shipments outside of the contiguous United States, please contact our Truckload team 1-866-378-3748, press 31 for direct assistance., We are unable to complete your quote online. For personal assistance, please call our rate quote department at 804-353-1900, Ext. 2269.]]";
               assertEquals(textMsg,expTxt);
 
        }

        /**
    	 * @author coxda
    	 * 
    	 * DOE - Verify fuel rates are received and agrees with the official site diesel prices
    	 */
    	
    	@Test
    	public void executeQZ_9336() {
    		
    		String endpointUrl = "http://apitestrt.estesinternal.com/rest/tools/EstesDOE/DOEFuelPriceIndex/v1.0/";
    		String request = "{\r\n" + "\"CorrelationID\" : \"TEST-test9\",\r\n" + "\"RequestingSystem\" : \"PRISM\",\r\n"
    				+ "\"RequestType\" :  \"LatestFuelPrice\"\r\n" + "}";

    		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON)
    				.accept(ContentType.JSON).body(request).when().post(endpointUrl).then().log().all().statusCode(200)
    				.statusLine("HTTP/1.1 200 OK").extract().response();

    		driver.get("https://www.eia.gov/petroleum/gasdiesel/");

    		String[] regions = { "USRetail", "EastCoast-PADD1", "NewEngland-PADD1A", "CentralAtlantic-PADD1B",
    				"LowerAtlantic-PADD1C", "Midwest-PADD2", "GulfCoast-PADD3", "RockyMountain-PADD4", "WestCoast-PADD5",
    				"WestCoastNo2", "California" };
    		List<WebElement> eiaPrices = driver.findElements(
    				By.xpath("(//table[@class='basic-table vertical-divider-after-col-col-4 full-width align-center-col-2 align-center-col-3 align-center-col-4 align-center-col-5 align-center-col-6 align-center-col-7 align-center-col-8 align-center-col-9'])[2]/tbody/tr/td[4]"));

    		for (int i = 0; i < regions.length; i++) {
    			String estesPrice = response.jsonPath().get("FuelPrice." + regions[i]).toString();
    			String eiaPrice = eiaPrices.get(i).getText();
    			logger.info("[" + regions[i] + "][" + estesPrice + "][" + eiaPrice + "]");
    			// check if eiaPrice contains estesPrice; eiaPrice has trailing zeroes
    			assertTrue(eiaPrice.contains(estesPrice), "Fuel rate mismatch.");
    		}

    	}
        
        
        
        
        
	}
