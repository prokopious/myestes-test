package billOfLadingTests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class BillOfLadingSmokeTest extends TestBase {

    /**
     * @author qcuthbert (8/29/22)
     * BOL PDF Verify when the JSON request with BOL required and conditionally required fields only
     * is submitted & the user is authenticated with a valid bearer token then a valid response (status=21)
     * is received & the PDF displays the correct info
     */

    @Test(enabled = true, priority = 1)
    public void executeQZ_11937() throws Exception{
        RestAssured.baseURI = "https://uat-cloudapi.estes-express.com/v1/bol"; // AWS endpoint
        String apiKey = "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ";
        String bolFileName = "./testBol.pdf";
        PDFParser parseBol;
        String bolText = null;
        String businessDate = testUtil.addFutureWeekDay();
        String pickupDate[] = businessDate.split("/");
        String futureDate = pickupDate[2] + "-" + pickupDate[0] + "-" + pickupDate[1];
        String token = testUtil.generateAuthenticationToken(username2, password2, apiKey);

        Response response = RestAssured.given().auth().oauth2(token)
                .header("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"bol\": {\n" +
                        "    \"date\": \""+ futureDate +"T00:00:00.000\",\r\n" +
                        "    \"function\": \"Create\",\n" +
                        "    \"isTest\": true,\n" +
                        "    \"requestorRole\": \"Third Party\",\n" +
                        "    \"specialInstructions\": \"Please Read The Customer Special Instructions section - Up to 500 chars\"\n" +
                        "  },\n" +
                        "  \"images\": {\n" +
                        "    \"includeBol\": true,\n" +
                        "    \"includeShippingLabels\": true,\n" +
                        "    \"shippingLabels\": {\n" +
                        "      \"format\": \"Avery\",\n" +
                        "      \"quantity\": 12,\n" +
                        "      \"position\": 2\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"referenceNumbers\": {\n" +
                        "    \"pro\": \"\",\n" +
                        "    \"estimate\": \"LS2WV5G\",\n" +
                        "    \"shipmentId\": \"SIDMainID08312022\",\n" +
                        "    \"masterBol\": \"MasterBOL08312022\",\n" +
                        "    \"bol\": [\n" +
                        "      \"BL08312022-FIRST\",\n" +
                        "      \"BL08312022-2\",\n" +
                        "      \"BL08312022-3\",\n" +
                        "      \"BL08312022-4\"\n" +
                        "    ],\n" +
                        "    \"po\": [\n" +
                        "      {\n" +
                        "        \"number\": \"PO08312022-1\",\n" +
                        "        \"pieces\": 2,\n" +
                        "        \"weight\": 1300,\n" +
                        "        \"palletized\": false,\n" +
                        "        \"additionalShipperInfo\": \"PO 1 Call for pickup and delivery window\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO08312022-2\",\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"weight\": 1000,\n" +
                        "        \"palletized\": true,\n" +
                        "        \"additionalShipperInfo\": \"PO 2 shipper info Please read commodity description, stackable.\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO08312022-3\",\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"weight\": 600,\n" +
                        "        \"palletized\": false,\n" +
                        "        \"additionalShipperInfo\": \"PO 3 shipper info Freight must always stay upright. Please read commodity description. Do not stack\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"number\": \"PO08312022-4\",\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"weight\": 400,\n" +
                        "        \"palletized\": false,\n" +
                        "        \"additionalShipperInfo\": \"PO 4 Call for pickup and delivery window\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO08312022-5\",\n" +
                        "        \"pieces\": 2,\n" +
                        "        \"weight\": 1200,\n" +
                        "        \"palletized\": true,\n" +
                        "        \"additionalShipperInfo\": \"PO 5 shipper info Please read commodity description, stackable.\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO08312022-6\",\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"weight\": 900,\n" +
                        "        \"additionalShipperInfo\": \"PO 6 shipper info Freight must always stay upright. Please read commodity description. Do not stack\"\n" +
                        "      }\n" +
                        "      \n" +
                        "    ],\n" +
                        "    \"additionalReferences\": [\n" +
                        "        {\n" +
                        "        \"name\": \"CID\",\n" +
                        "        \"value\": \"CID08312022-1\"\n" +
                        "      },\n" +
                        "              {\n" +
                        "        \"name\": \"CID\",\n" +
                        "        \"value\": \"CID08312022-2\"\n" +
                        "      },\n" +
                        "        {\n" +
                        "        \"name\": \"PO\",\n" +
                        "        \"value\": \"PO08312022-1\"\n" +
                        "      },\n" +
                        "       {\n" +
                        "        \"name\": \"Load\",\n" +
                        "        \"value\": \"LoadNum08312022-1\"\n" +
                        "      },\n" +
                        "             {\n" +
                        "        \"name\": \"Load\",\n" +
                        "        \"value\": \"LoadNum08312022-2\"\n" +
                        "      },\n" +
                        "             {\n" +
                        "        \"name\": \"Cust Order\",\n" +
                        "        \"value\": \"CustNumber08312022-1\"\n" +
                        "      },\n" +
                        "      \n" +
                        "     {\n" +
                        "        \"name\": \"Other\",\n" +
                        "        \"value\": \"Other08312022-1\"\n" +
                        "      },\n" +
                        "                {\n" +
                        "        \"name\": \"SID\",\n" +
                        "        \"value\": \"SID08312022-1\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"payment\": {\n" +
                        "    \"terms\": \"PREPAID\"\n" +
                        "  },\n" +
                        "  \"commodities\": {\n" +
                        "    \"handlingUnits\": [\n" +
                        "      {\n" +
                        "        \"count\": 2,\n" +
                        "        \"type\": \"PT\",\n" +
                        "        \"tareWeigh\": 50,\n" +
                        "        \"weight\": 1500,\n" +
                        "        \"length\": 96,\n" +
                        "        \"width\": 12,\n" +
                        "        \"height\": 8,\n" +
                        "        \"stackable\": true,\n" +
                        "        \"lineItems\":[\n" +
                        "            {\n" +
                        "                \"description\": \"HU 1.1\",\n" +
                        "                \"weight\": 1300,\n" +
                        "                \"pieces\": 24,\n" +
                        "                \"packagingType\": \"RL\",\n" +
                        "                \"classification\": \"50\",\n" +
                        "                \"nmfc\": \"86900\",\n" +
                        "                \"nmfcSub\": \"3\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }\n" +
                        "             }\n" +
                        "            ]   \n" +
                        "      },\n" +
                        "        {\n" +
                        "        \"count\": 3,\n" +
                        "        \"type\": \"SK\",\n" +
                        "        \"tareWeigh\": 250,\n" +
                        "        \"weight\": 2750,\n" +
                        "        \"length\": 120,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48,\n" +
                        "        \"stackable\": true,\n" +
                        "        \"lineItems\": [\n" +
                        "            {\n" +
                        "                \"description\": \"HU-2.1\",\n" +
                        "                \"weight\": 1500,\n" +
                        "                \"pieces\": 4,\n" +
                        "                \"packagingType\": \"BD\",\n" +
                        "                \"classification\": \"50\",\n" +
                        "                \"nmfc\": \"86900\",\n" +
                        "                \"nmfcSub\": \"3\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "                {\n" +
                        "                \"description\": \"HU 2.2\",\n" +
                        "                \"weight\": 1000,\n" +
                        "                \"pieces\": 24,\n" +
                        "                \"packagingType\": \"BX\",\n" +
                        "                \"classification\": \"55\",\n" +
                        "                \"nmfc\": \"200130\",\n" +
                        "                \"nmfcSub\": \"5\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }   \n" +
                        "            }\n" +
                        "           ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "        \"count\": 3,\n" +
                        "        \"type\": \"CR\",\n" +
                        "        \"tareWeigh\": 150,\n" +
                        "        \"weight\": 1750,\n" +
                        "        \"length\": 60,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48,\n" +
                        "        \"stackable\": true,\n" +
                        "        \"lineItems\": [\n" +
                        "            {\n" +
                        "                \"description\": \"HU 3.1\",\n" +
                        "                \"weight\": 600,\n" +
                        "                \"pieces\": 6,\n" +
                        "                \"packagingType\": \"TO\",\n" +
                        "                \"classification\": \"50\",\n" +
                        "                \"nmfc\": \"86900\",\n" +
                        "                \"nmfcSub\": \"3\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                } },\n" +
                        "            {\n" +
                        "                \"description\": \"HU 3.2\",\n" +
                        "                \"weight\": 700,\n" +
                        "                \"pieces\": 24,\n" +
                        "                \"packagingType\": \"CT\",\n" +
                        "                \"classification\": \"60\",\n" +
                        "                \"nmfc\": \"123940\",\n" +
                        "                \"nmfcSub\": \"1\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                } },\n" +
                        "            {\n" +
                        "                \"description\": \"HU 3.3\",\n" +
                        "                \"weight\": 300,\n" +
                        "                \"pieces\": 1,\n" +
                        "                \"packagingType\": \"KT\",\n" +
                        "                \"classification\": \"70\",\n" +
                        "                \"nmfc\": \"150370\",\n" +
                        "                \"nmfcSub\": \"4\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                        }\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "  \"accessorials\": {\n" +
                        "    \"codes\": [\n" +
                        "      \"GTD_AM\",\n" +
                        "      \"OVR\",\n" +
                        "      \"IDL\",\n" +
                        "      \"MARK\"\n" +
                        "    ],\n" +
                        "    \"hazardousDetails\": {\n" +
                        "      \"emergencyContact\": {\n" +
                        "        \"name\": \"\",\n" +
                        "        \"phone\": \"\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"sortAndSegregateDetails\": {\n" +
                        "      \"pieces\": \"83\"\n" +
                        "    },\n" +
                        "    \"markDetails\": {\n" +
                        "      \"pieces\": \"8\"\n" +
                        "    },\n" +
                        "    \"limitedAccessType\": {\n" +
                        "      \"origin\": \"church\",\n" +
                        "      \"destination\": \"Fair\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"origin\": {\n" +
                        "    \"account\": \"B062273\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \" TALBOTS 4053 Origin Acct 30 ch Extra Data for Label Display\",\n" +
                        "    \"address1\": \"7100 S CROATAN HWY 51 30 chara Address\",\n" +
                        "    \"address2\": \"TANGER OUTLET CENTER 30 charac Address 2 Extra Data for Label Display\",\n" +
                        "    \"city\": \"Nags Head\",\n" +
                        "    \"stateProvince\": \"nc\",\n" +
                        "    \"postalCode\": \"27959\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8045038175\",\n" +
                        "      \"phoneExt\": \"1357924680\",\n" +
                        "      \"name\": \"Mr. Shipper Contact 30 characters, Extra Data for Label Display\",\n" +
                        "      \"email\": \"Shipper@Estes-Express.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"destination\": {\n" +
                        "    \"account\": \"\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \"GROUP 30 charac CONSIGNEE\",\n" +
                        "    \"address1\": \"WHITFIELD AVE 300\",\n" +
                        "    \"address2\": \"Address line 2\",\n" +
                        "    \"city\": \"Hamilton\",\n" +
                        "    \"stateProvince\": \"ON\",\n" +
                        "    \"postalCode\": \"L8L4B5\",\n" +
                        "    \"country\": \"CAN\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"7721231234\",\n" +
                        "      \"phoneExt\": \"9876543210\",\n" +
                        "      \"name\": \"Mr. Destination Contact 30 chars, Extra Data for Label Display\",\n" +
                        "      \"email\": \"Consignee@Estes-Express.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"billTo\": {\n" +
                        "    \"account\": \"B020310\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "   \"name\": \" TALBOTS\",\n" +
                        "    \"address1\": \"1 TALBOT RD\",\n" +
                        "    \"address2\": \"\",\n" +
                        "    \"city\": \"HINGHAM\",\n" +
                        "    \"stateProvince\": \"MA\",\n" +
                        "    \"postalCode\": \"02043\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2524490237\",\n" +
                        "      \"phoneExt\": \"3170\",\n" +
                        "      \"name\": \"Bill To Contact Name\",\n" +
                        "      \"email\": \"BillToEmail@yahoo.com\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")
                .when()
                .post(RestAssured.baseURI)
                .then()
                .log().all()
                .statusCode(201).statusLine("HTTP/1.1 201 ")
                .body("referenceNumbers.pro", notNullValue())
                .body("referenceNumbers.shipmentConfirmationNumber", notNullValue())
                .body("messageStatus.status", equalTo("PASS"))
                .body("messageStatus.code", equalTo("10000000"))
                .body("messageStatus.message", equalTo("Transaction was successful"))
                .body("messageStatus.resolution", equalTo(""))
                .extract().response();

        String bol = response.jsonPath().getString("images.bol");
        String pro = response.jsonPath().getString("referenceNumbers.pro");
        String eControl = response.jsonPath().getString("referenceNumbers.shipmentConfirmationNumber");

        File bolFile = testUtil.base64ToPdf(bol, bolFileName);

        try(FileInputStream bolContents = new FileInputStream(bolFile)) {
            parseBol = new PDFParser(bolContents);
            parseBol.parse();
            bolText = new PDFTextStripper().getText(parseBol.getPDDocument());
        }catch(Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(bolText.contains("BILL OF LADING"));
        Assert.assertTrue(bolText.contains(pro));
        Assert.assertTrue(bolText.contains(eControl));

        boolean wasDeleted = bolFile.delete();

        if (!wasDeleted) {
            System.out.println("File was not deleted");
        }

    }

    /**
     * @author qcuthbert (8/31/22)
     * BOL PDF Verify when the JSON request with BOL required and conditionally required fields only
     * is submitted & the user is authenticated with a valid bearer token then a valid response (status=21)
     * is received & the PDF displays the correct info
     */

    @Test(enabled = true, priority = 2)
    public void executeQZ_11938() throws Exception{
        RestAssured.baseURI = "https://uat-cloudapi.estes-express.com/v1/bol"; // AWS endpoint
        String apiKey = "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ";
        String bolFileName = "./testBol.pdf";
        PDFParser parseBol;
        String bolText = null;
        String businessDate = testUtil.addFutureWeekDay();
        String pickupDate[] = businessDate.split("/");
        String futureDate = pickupDate[2] + "-" + pickupDate[0] + "-" + pickupDate[1];
        String token = testUtil.generateAuthenticationToken(username2, password2, apiKey);

        Response response = RestAssured.given().auth().oauth2(token)
                .header("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"bol\": {\n" +
                        "    \"date\": \""+ futureDate +"T00:00:00.000\",\r\n" +
                        "    \"function\": \"Create\",\n" +
                        "    \"isTest\": true,\n" +
                        "    \"requestorRole\": \"Consignee\",\n" +
                        "    \"specialInstructions\": \"\"\n" +
                        "  },\n" +
                        "  \"images\": {\n" +
                        "    \"includeBol\": \"true\",\n" +
                        "    \"includeShippingLabels\": \"\",\n" +
                        "    \"shippingLabels\": {\n" +
                        "      \"format\": \"\",\n" +
                        "      \"quantity\": \"\",\n" +
                        "      \"position\": \"\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"referenceNumbers\": {\n" +
                        "    \"pro\": \"\",\n" +
                        "    \"estimate\": \"\",\n" +
                        "    \"shipmentId\": \"\",\n" +
                        "    \"masterBol\": \"\",\n" +
                        "    \"bol\": [\n" +
                        "      \"\",\n" +
                        "      \"\"\n" +
                        "    ],\n" +
                        "    \"po\": [\n" +
                        "      {\n" +
                        "        \"number\": \"\",\n" +
                        "        \"pieces\": \"\",\n" +
                        "        \"weight\": \"\",\n" +
                        "        \"palletized\": \"\",\n" +
                        "        \"additionalShipperInfo\": \"\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"additionalReferences\": [\n" +
                        "      {\n" +
                        "        \"name\": \"\",\n" +
                        "        \"value\": \"\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"payment\": {\n" +
                        "    \"terms\": \"Collect\"\n" +
                        "  },\n" +
                        " \"commodities\": {\n" +
                        "    \"handlingUnits\": [\n" +
                        "      {\n" +
                        "        \"count\": 4,\n" +
                        "        \"type\": \"SKD\",\n" +
                        "        \"weight\": 2000,\n" +
                        "        \"tareWeight\": \"100\",\n" +
                        "        \"length\": 48,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48,\n" +
                        "        \"stackable\": \"true\",\n" +
                        "    \"lineItems\": [\n" +
                        "      {\n" +
                        "        \"description\": \"HU 1.1\",\n" +
                        "        \"weight\": 1900,\n" +
                        "        \"pieces\": 12,\n" +
                        "        \"packagingType\": \"CTN\",\n" +
                        "        \"classification\": \"50\",\n" +
                        "        \"nmfc\": \"\",\n" +
                        "        \"nmfcSub\": \"\",\n" +
                        "        \"hazardous\": false,\n" +
                        "        \"hazardousDetails\": {\n" +
                        "          \"weight\": \"\",\n" +
                        "          \"class\": \"\",\n" +
                        "          \"unnaNumber\": \"\",\n" +
                        "          \"propername\": \"\",\n" +
                        "          \"technicalName\": \"\",\n" +
                        "          \"packagingGroup\": \"\",\n" +
                        "          \"contractNumber\": \"\"\n" +
                        "         }\n" +
                        "        }\n" +
                        "       ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"shipmentTotals\": {\n" +
                        "    \"grossWeight\": \"\",\n" +
                        "    \"netWeight\": \"\",\n" +
                        "    \"handlingUnits\": \"\",\n" +
                        "    \"linearLength\": \"\",\n" +
                        "    \"cubicFeet\": \"\"\n" +
                        "  },\n" +
                        "  \"accessorials\": {\n" +
                        "    \"codes\": [\n" +
                        "        \"\"\n" +
                        "    ],\n" +
                        "    \"hazardousDetails\": {\n" +
                        "      \"emergencyContact\": {\n" +
                        "        \"name\": \"\",\n" +
                        "        \"phone\": \"\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"cod\": {\n" +
                        "      \"amount\": \"\",\n" +
                        "      \"terms\": \"\",\n" +
                        "      \"customerCheckAcceptable\": \"\",\n" +
                        "      \"remitTo\": {\n" +
                        "        \"name\": \"\",\n" +
                        "        \"address1\": \"\",\n" +
                        "        \"address2\": \"\",\n" +
                        "        \"city\": \"\",\n" +
                        "        \"stateProvince\": \"\",\n" +
                        "        \"postalCode\": \"\",\n" +
                        "        \"country\": \"\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"sortAndSegregateDetails\": {\n" +
                        "      \"pieces\": \"\"\n" +
                        "    },\n" +
                        "    \"excessLiabilityDetails\": {\n" +
                        "      \"monetaryValue\": \"\",\n" +
                        "      \"excessDeclaredValue\": \"\"\n" +
                        "    },\n" +
                        "    \"markDetails\": {\n" +
                        "      \"pieces\": \"\"\n" +
                        "    },\n" +
                        "    \"limitedAccessType\": {\n" +
                        "      \"origin\": \"\",\n" +
                        "      \"destination\": \"\"\n" +
                        "    },\n" +
                        "    \"timeCriticalDetails\": {\n" +
                        "      \"type\": \"\",\n" +
                        "      \"date\": {\n" +
                        "        \"start\": \"\",\n" +
                        "        \"end\": \"\"\n" +
                        "\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"appointmentDetails\": {\n" +
                        "      \"pickup\": {\n" +
                        "        \"start\": \"\",\n" +
                        "        \"end\": \"\"\n" +
                        "      },\n" +
                        "      \"delivery\": {\n" +
                        "        \"start\": \"\",\n" +
                        "        \"end\": \"\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"origin\": {\n" +
                        "    \"account\": \"B062273\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \" TALBOTS 4053\",\n" +
                        "    \"address1\": \"7100 S CROATAN HWY 51\",\n" +
                        "    \"address2\": \"TANGER OUTLET CENTER\",\n" +
                        "    \"city\": \"Nags Head\",\n" +
                        "    \"stateProvince\": \"NC\",\n" +
                        "    \"postalCode\": \"27959\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2524490237\",\n" +
                        "      \"phoneExt\": \"\",\n" +
                        "      \"name\": \"\",\n" +
                        "      \"email\": \"\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"destination\": {\n" +
                        "    \"account\": \"\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \"James River Equipment Sales\",\n" +
                        "    \"address1\": \"200 N Arthur Ashe Blvd\",\n" +
                        "    \"address2\": \" \",\n" +
                        "    \"city\": \"Richmond\",\n" +
                        "    \"stateProvince\": \"VA\",\n" +
                        "    \"postalCode\": \"23220\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8045551212\",\n" +
                        "      \"phoneExt\": \"\",\n" +
                        "      \"name\": \"\",\n" +
                        "      \"email\": \"\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"billTo\": {\n" +
                        "    \"account\": \"\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \"Texas Branch\",\n" +
                        "    \"address1\": \"1717 N Harwood St.\",\n" +
                        "    \"address2\": \"\",\n" +
                        "    \"city\": \"Dallas\",\n" +
                        "    \"stateProvince\": \"TX\",\n" +
                        "    \"postalCode\": \"75201\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2224441234\",\n" +
                        "      \"phoneExt\": \"\",\n" +
                        "      \"name\": \"\",\n" +
                        "      \"email\": \"\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "    \"customsBroker\": {\n" +
                        "    \"name\": \"\",\n" +
                        "    \"address1\": \"\",\n" +
                        "    \"address2\": \" \",\n" +
                        "    \"city\": \"\",\n" +
                        "    \"stateProvince\": \"\",\n" +
                        "    \"postalCode\": \"\",\n" +
                        "    \"country\": \"\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"\",\n" +
                        "      \"phoneExt\": \"\",\n" +
                        "      \"name\": \"\",\n" +
                        "      \"email\": \"\"\n" +
                        "    }\n" +
                        "  }  \n" +
                        "}")
                .when()
                .post(RestAssured.baseURI)
                .then()
                .log().all()
                .statusCode(201).statusLine("HTTP/1.1 201 ")
                .body("referenceNumbers.pro", notNullValue())
                .body("referenceNumbers.shipmentConfirmationNumber", notNullValue())
                .body("messageStatus.status", equalTo("PASS"))
                .body("messageStatus.code", equalTo("10000000"))
                .body("messageStatus.message", equalTo("Transaction was successful"))
                .body("messageStatus.resolution", equalTo(""))
                .extract().response();

        String bol = response.jsonPath().getString("images.bol");
        String pro = response.jsonPath().getString("referenceNumbers.pro");
        String eControl = response.jsonPath().getString("referenceNumbers.shipmentConfirmationNumber");

        File bolFile = testUtil.base64ToPdf(bol, bolFileName);

        try(FileInputStream bolContents = new FileInputStream(bolFile)) {
            parseBol = new PDFParser(bolContents);
            parseBol.parse();
            bolText = new PDFTextStripper().getText(parseBol.getPDDocument());
        }catch(Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(bolText.contains("BILL OF LADING"));
        Assert.assertTrue(bolText.contains(pro));
        Assert.assertTrue(bolText.contains(eControl));

        boolean wasDeleted = bolFile.delete();

        if (!wasDeleted) {
            System.out.println("File was not deleted");
        }

    }

    /**
     * @author qcuthbert (8/29/22)
     * BOL SL PDF Verify when the JSON request is submitted & images.includeShippingLabels = true
     * & images.shippingLabels.format=Avery then a valid response with a link to the Shipping Labels
     * PDF is created & the SL PDF contains all the info
     */

    @Test(enabled = true, priority = 3)
    public void executeQZ_12133() throws Exception{
        RestAssured.baseURI = "https://uat-cloudapi.estes-express.com/v1/bol"; // AWS endpoint
        String apiKey = "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ";
        String labelFileName = "./testLabel.pdf";
        PDFParser parseLabel;
        String labelText = null;
        String businessDate = testUtil.addFutureWeekDay();
        String pickupDate[] = businessDate.split("/");
        String futureDate = pickupDate[2] + "-" + pickupDate[0] + "-" + pickupDate[1];
        String token = testUtil.generateAuthenticationToken(username2, password2, apiKey);

        Response response = RestAssured.given().auth().oauth2(token)
                .header("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"bol\": {\n" +
                        "    \"date\": \""+ futureDate +"T00:00:00.000\",\r\n" +
                        "    \"function\": \"Create\",\n" +
                        "    \"isTest\": true,\n" +
                        "    \"requestorRole\": \"Third Party\",\n" +
                        "    \"specialInstructions\": \"Please Read Customer Special Instruction section (Up to 500 chars)\"\n" +
                        "  },\n" +
                        "  \"images\": {\n" +
                        "    \"includeBol\": true,\n" +
                        "    \"includeShippingLabels\": true,\n" +
                        "    \"shippingLabels\": {\n" +
                        "      \"format\": \"avery\",\n" +
                        "      \"quantity\": 12,\n" +
                        "      \"position\": 2\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"referenceNumbers\": {\n" +
                        "    \"pro\": \"\",\n" +
                        "    \"estimate\": \"\",\n" +
                        "    \"shipmentId\": \"SID20220323\",\n" +
                        "    \"masterBol\": \"MasterBOL\",\n" +
                        "    \"bol\": [\n" +
                        "      \"BL20220325-FIRST\",\n" +
                        "      \"BL20220323-2\",\n" +
                        "      \"BL20220323-3\",\n" +
                        "      \"BL20220323-4\"\n" +
                        "    ],\n" +
                        "    \"po\": [\n" +
                        "      {\n" +
                        "        \"number\": \"PO20220412-1\",\n" +
                        "        \"pieces\": 3,\n" +
                        "        \"weight\": 2200,\n" +
                        "        \"palletized\": false,\n" +
                        "        \"additionalShipperInfo\": \"PO 1 Call for pickup and delivery window\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO20220412-2\",\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"weight\": 1800,\n" +
                        "        \"palletized\": true,\n" +
                        "        \"additionalShipperInfo\": \"PO 2 shipper info Please read commodity description, stackable.\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO20220412-3\",\n" +
                        "        \"pieces\": 3,\n" +
                        "        \"weight\": 1000,\n" +
                        "        \"additionalShipperInfo\": \"PO 3 shipper info Freight must always stay upright. Please read commodity description. Do not stack\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"number\": \"PO20220412-4\",\n" +
                        "        \"pieces\": 2,\n" +
                        "        \"weight\": 1200,\n" +
                        "        \"palletized\": false,\n" +
                        "        \"additionalShipperInfo\": \"PO 4 Call for pickup and delivery window\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO20220412-5\",\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"weight\": 1800,\n" +
                        "        \"palletized\": true,\n" +
                        "        \"additionalShipperInfo\": \"PO 5 shipper info Please read commodity description, stackable.\"\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"number\": \"PO20220412-6\",\n" +
                        "        \"pieces\": 2,\n" +
                        "        \"weight\": 2000,\n" +
                        "        \"additionalShipperInfo\": \"PO 6 shipper info Freight must always stay upright. Please read commodity description. Do not stack\"\n" +
                        "      }\n" +
                        "      \n" +
                        "    ],\n" +
                        "    \"additionalReferences\": [\n" +
                        "      {\n" +
                        "        \"name\": \"Customer Reference Id\",\n" +
                        "        \"value\": \"CRID3452-01\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"payment\": {\n" +
                        "    \"terms\": \"third PartY\"\n" +
                        "  },\n" +
                        "  \"commodities\": {\n" +
                        "    \"handlingUnits\": [\n" +
                        "      {\n" +
                        "        \"count\": 2,\n" +
                        "        \"type\": \"JCN\",\n" +
                        "        \"tareWeigh\": 50,\n" +
                        "        \"weight\": 1000,\n" +
                        "        \"length\": 96,\n" +
                        "        \"width\": 12,\n" +
                        "        \"height\": 8,\n" +
                        "        \"stackable\": true,\n" +
                        "        \"lineItems\":[\n" +
                        "            {\n" +
                        "                \"description\": \"HU 1.1\",\n" +
                        "                \"weight\": 1000,\n" +
                        "                \"pieces\": 3,\n" +
                        "                \"packagingType\": \"DRM\",\n" +
                        "                \"classification\": \"50\",\n" +
                        "                \"nmfc\": \"86900\",\n" +
                        "                \"nmfcSub\": \"3\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }\n" +
                        "             }\n" +
                        "            ]   \n" +
                        "      },\n" +
                        "        {\n" +
                        "        \"count\": 7,\n" +
                        "        \"type\": \"SKD\",\n" +
                        "        \"tareWeigh\": 250,\n" +
                        "        \"weight\": 2000,\n" +
                        "        \"length\": 120,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48,\n" +
                        "        \"stackable\": true,\n" +
                        "        \"lineItems\": [\n" +
                        "            {\n" +
                        "                \"description\": \"HU-2.1\",\n" +
                        "                \"weight\": 1200,\n" +
                        "                \"pieces\": 4,\n" +
                        "                \"packagingType\": \"DRM\",\n" +
                        "                \"classification\": \"50\",\n" +
                        "                \"nmfc\": \"86900\",\n" +
                        "                \"nmfcSub\": \"3\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "                {\n" +
                        "                \"description\": \"HU 2.2\",\n" +
                        "                \"weight\": 800,\n" +
                        "                \"pieces\": 24,\n" +
                        "                \"packagingType\": \"PAL\",\n" +
                        "                \"classification\": \"55\",\n" +
                        "                \"nmfc\": \"200130\",\n" +
                        "                \"nmfcSub\": \"5\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }   \n" +
                        "            }\n" +
                        "           ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "        \"count\": 3,\n" +
                        "        \"type\": \"PAT\",\n" +
                        "        \"tareWeigh\": 100,\n" +
                        "        \"weight\": 7250,\n" +
                        "        \"length\": 143,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48,\n" +
                        "        \"stackable\": true,\n" +
                        "        \"lineItems\": [\n" +
                        "            {\n" +
                        "                \"description\": \"HU 3.1\",\n" +
                        "                \"weight\": 3200,\n" +
                        "                \"pieces\": 10,\n" +
                        "                \"packagingType\": \"BCh\",\n" +
                        "                \"classification\": \"50\",\n" +
                        "                \"nmfc\": \"86900\",\n" +
                        "                \"nmfcSub\": \"3\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"description\": \"HU 3.2\",\n" +
                        "                \"weight\": 800,\n" +
                        "                \"pieces\": 20,\n" +
                        "                \"packagingType\": \"CSK\",\n" +
                        "                \"classification\": \"60\",\n" +
                        "                \"nmfc\": \"123940\",\n" +
                        "                \"nmfcSub\": \"1\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"description\": \"HU 3.3\",\n" +
                        "                \"weight\": 3000,\n" +
                        "                \"pieces\": 4,\n" +
                        "                \"packagingType\": \"CHS\",\n" +
                        "                \"classification\": \"70\",\n" +
                        "                \"nmfc\": \"150370\",\n" +
                        "                \"nmfcSub\": \"4\",\n" +
                        "                \"hazardous\": false,\n" +
                        "                \"hazardousDetails\": {\n" +
                        "                \"weight\": \"\",\n" +
                        "                \"class\": \"\",\n" +
                        "                \"unnaNumber\": \"\",\n" +
                        "                \"propername\": \"\",\n" +
                        "                \"technicalName\": \"\",\n" +
                        "                \"packagingGroup\": \"\",\n" +
                        "                \"contractNumber\": \"\"\n" +
                        "                        }\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "  \"accessorials\": {\n" +
                        "    \"codes\": [\n" +
                        "      \"GTD_AM\",\n" +
                        "      \"OVR\",\n" +
                        "      \"IDL\"\n" +
                        "    ],\n" +
                        "    \"hazardousDetails\": {\n" +
                        "      \"emergencyContact\": {\n" +
                        "        \"name\": \"\",\n" +
                        "        \"phone\": \"\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"sortAndSegregateDetails\": {\n" +
                        "      \"pieces\": \"65\"\n" +
                        "    },\n" +
                        "    \"markDetails\": {\n" +
                        "      \"pieces\": \"\"\n" +
                        "    },\n" +
                        "    \"limitedAccessType\": {\n" +
                        "      \"origin\": \"church\",\n" +
                        "      \"destination\": \"Fair\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"origin\": {\n" +
                        "    \"account\": \"B062273\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \" TALBOTS 4053 Origin Acct 30 ch Extra Data for Label Display\",\n" +
                        "    \"address1\": \"7100 S CROATAN HWY 51 30 chara Address 1 Extra Data for Label Display\",\n" +
                        "    \"address2\": \"TANGER OUTLET CENTER 30 charac Address 2 Extra Data for Label Display\",\n" +
                        "    \"city\": \"Nags head\",\n" +
                        "    \"stateProvince\": \"nc\",\n" +
                        "    \"postalCode\": \"27959\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8045038175\",\n" +
                        "      \"phoneExt\": \"1357924680\",\n" +
                        "      \"name\": \"Mr. Shipper Contact 30 characters, Extra Data for Label Display\",\n" +
                        "      \"email\": \"Shipper@Estes-Express.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"destination\": {\n" +
                        "    \"account\": \"9590098\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \"BAT GROUP 30 charac CONSIGNEE, Extra Data for Label Display\",\n" +
                        "    \"address1\": \"11 WHITFIELD AVE 300, 30 chars, Extra Data for Label Display\",\n" +
                        "    \"address2\": \"Address line 2, also up 30 char Extra Data for Label Display\",\n" +
                        "    \"city\": \"hamilton\",\n" +
                        "    \"stateProvince\": \"on\",\n" +
                        "    \"postalCode\": \"L8L4B5\",\n" +
                        "    \"country\": \"CAN\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"7721231234\",\n" +
                        "      \"phoneExt\": \"9876543210\",\n" +
                        "      \"name\": \"Mr. Destination Contact 30 chars, Extra Data for Label Display\",\n" +
                        "      \"email\": \"Consignee@Estes-Express.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"billTo\": {\n" +
                        "    \"account\": \"5001120\",\n" +
                        "    \"locationId\": \"\",\n" +
                        "    \"name\": \"AGFA CORP 5753\",\n" +
                        "    \"address1\": \"NVISION GLOBAL\",\n" +
                        "    \"address2\": \"1900 BRANNAN RD STE 100\",\n" +
                        "    \"city\": \"MCDONOUGH\",\n" +
                        "    \"stateProvince\": \"GA\",\n" +
                        "    \"postalCode\": \"30252\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2224441234\",\n" +
                        "      \"phoneExt\": \"246801357\",\n" +
                        "      \"name\": \"Bill To Contact Name\",\n" +
                        "      \"email\": \"BillToEmail@yahoo.com\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")
                .when()
                .post(RestAssured.baseURI)
                .then()
                .log().all()
                .statusCode(201).statusLine("HTTP/1.1 201 ")
                .body("referenceNumbers.pro", notNullValue())
                .body("referenceNumbers.shipmentConfirmationNumber", notNullValue())
                .body("messageStatus.status", equalTo("PASS"))
                .body("messageStatus.code", equalTo("10000000"))
                .body("messageStatus.message", equalTo("Transaction was successful"))
                .body("messageStatus.resolution", equalTo(""))
                .extract().response();

        String label = response.jsonPath().getString("images.shippingLabels");
        String pro = response.jsonPath().getString("referenceNumbers.pro");
        String eControl = response.jsonPath().getString("referenceNumbers.shipmentConfirmationNumber");

        File labelFile = testUtil.base64ToPdf(label, labelFileName);

        try(FileInputStream labelContents = new FileInputStream(labelFile)) {
            parseLabel = new PDFParser(labelContents);
            parseLabel.parse();
            labelText = new PDFTextStripper().getText(parseLabel.getPDDocument());
        }catch(Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(labelText.contains(pro));
        Assert.assertTrue(labelText.contains(eControl));

        boolean wasDeleted = labelFile.delete();

        if (!wasDeleted) {
            System.out.println("File was not deleted");
        }
    }

    /**
     * @author qcuthbert
     * BOL API Verify when the JSON request with valid BOL data is submitted
     * but the user is authenticated with invalid token then an unauthorized
     * (status=401) response is received with 'Failed authentication' message
     */

    @Test(enabled = true, priority = 4)
    public void executeQZ_11939() throws Exception{
        RestAssured.baseURI = "https://uat-cloudapi.estes-express.com/v1/bol";
        String apiKey = "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ";
        String businessDate = testUtil.addFutureWeekDay();
        String pickupDate[] = businessDate.split("/");
        String futureDate = pickupDate[2] + "-" + pickupDate[0] + "-" + pickupDate[1];

        Response response = RestAssured.given()
                .header("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"bol\": {\n" +
                        "    \"date\": \""+ futureDate +"T00:00:00.000\",\r\n" +
                        "    \"function\": \"Create\",\n" +
                        "    \"isTest\": true,\n" +
                        "    \"requestorRole\": \"Third Party\",\n" +
                        "    \"specialInstructions\": \"CustTeam4-Sprints\"\n" +
                        "  },\n" +
                        "  \"images\": {\n" +
                        "    \"includeBol\": true,\n" +
                        "    \"includeShippingLabels\": true,\n" +
                        "    \"shippingLabels\": {\n" +
                        "      \"format\": \"Avery\",\n" +
                        "      \"quantity\": 5,\n" +
                        "      \"position\": 2\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"referenceNumbers\": {\n" +
                        "    \"pro\": \"\",\n" +
                        "    \"estimate\": \"E556724\",\n" +
                        "    \"shipmentId\": \"SID9743985\",\n" +
                        "    \"masterBol\": \"MBL98472587\",\n" +
                        "    \"bol\": [\n" +
                        "      \"BL19498432\",\n" +
                        "      \"BL99744565\"\n" +
                        "    ],\n" +
                        "    \"po\": [\n" +
                        "      {\n" +
                        "        \"number\": \"PO20220315-1\",\n" +
                        "        \"pieces\": 5,\n" +
                        "        \"weight\": 1500,\n" +
                        "        \"palletized\": true,\n" +
                        "        \"additionalShipperInfo\": \"PO #1 shipper info Freight must always stay upright. Please read commodity description. Do not stack\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"number\": \"PO20220315-2\",\n" +
                        "        \"pieces\": 10,\n" +
                        "        \"weight\": 1000,\n" +
                        "        \"palletized\": false,\n" +
                        "        \"additionalShipperInfo\": \"PO #2 Call for pickup and delivery window\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"additionalReferences\": [\n" +
                        "      {\n" +
                        "        \"name\": \"Customer Reference Id\",\n" +
                        "        \"value\": \"CRID3452-01\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"payment\": {\n" +
                        "    \"terms\": \"third PartY\"\n" +
                        "  },\n" +
                        "  \"commodities\": {\n" +
                        "    \"handlingUnits\": [\n" +
                        "      {\n" +
                        "        \"id\": \"unit1\",\n" +
                        "        \"count\": 12,\n" +
                        "        \"type\": \"BOX\",\n" +
                        "        \"tareWeight\": 5,\n" +
                        "        \"weight\": 1993,\n" +
                        "        \"length\": 24,\n" +
                        "        \"width\": 36,\n" +
                        "        \"height\": 12,\n" +
                        "        \"stackable\": true\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"lineItems\": [\n" +
                        "      {\n" +
                        "        \"handlingUnitId\": \"unit1\",\n" +
                        "        \"description\": \"lineItems.handlingUnitId.description\",\n" +
                        "        \"weight\": 2500,\n" +
                        "        \"pieces\": 5,\n" +
                        "        \"packagingType\": \"SKD\",\n" +
                        "        \"classification\": \"60\",\n" +
                        "        \"nmfc\": \"86900\",\n" +
                        "        \"nmfcSub\": \"3\",\n" +
                        "        \"hazardous\": false,\n" +
                        "        \"hazardousDetails\": {\n" +
                        "          \"weight\": 45,\n" +
                        "          \"class\": \"3\",\n" +
                        "          \"unnaNumber\": \"1005\",\n" +
                        "          \"propername\": \"Anhydrous ammonia\",\n" +
                        "          \"technicalName\": \"NH3\",\n" +
                        "          \"packagingGroup\": \"2\",\n" +
                        "          \"contractNumber\": \"54321\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"shipmentTotals\": {\n" +
                        "    \"grossWeight\": 2520,\n" +
                        "    \"netWeight\": 2500,\n" +
                        "    \"handlingUnits\": 15,\n" +
                        "    \"linearLength\": 96,\n" +
                        "    \"cubicFeet\": 7\n" +
                        "  },\n" +
                        "  \"accessorials\": {\n" +
                        "    \"codes\": [\n" +
                        "      \"IDL\",\n" +
                        "      \"GTD_AM\",\n" +
                        "      \"COD\"\n" +
                        "\n" +
                        "    ],\n" +
                        "    \"hazardousDetails\": {\n" +
                        "      \"emergencyContact\": {\n" +
                        "        \"name\": \"Manny Delgato\",\n" +
                        "        \"phone\": \"7775558899\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"cod\": {\n" +
                        "      \"amount\": \"70.99\",\n" +
                        "      \"terms\": \"Collect\",\n" +
                        "      \"customerCheckAcceptable\": true,\n" +
                        "      \"remitTo\": {\n" +
                        "        \"name\": \"Dog Beds by Stella\",\n" +
                        "        \"address1\": \"121 S. Cliffwood Ave.\",\n" +
                        "        \"address2\": \"Suite 55\",\n" +
                        "        \"city\": \"Los Angeles\",\n" +
                        "        \"stateProvince\": \"CA\",\n" +
                        "        \"postalCode\": \"90001\",\n" +
                        "        \"country\": \"USA\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"sortAndSegregateDetails\": {\n" +
                        "      \"pieces\": 25\n" +
                        "    },\n" +
                        "    \"excessLiabilityDetails\": {\n" +
                        "      \"monetaryValue\": \"100.00\",\n" +
                        "      \"excessDeclaredValue\": \"500.00\"\n" +
                        "    },\n" +
                        "    \"markDetails\": {\n" +
                        "      \"pieces\": 25\n" +
                        "    },\n" +
                        "    \"limitedAccessType\": {\n" +
                        "      \"origin\": \"Church\",\n" +
                        "      \"destination\": \"Secure\"\n" +
                        "    },\n" +
                        "    \"timeCriticalDetails\": {\n" +
                        "      \"type\": \"Delivery Window\",\n" +
                        "      \"date\": {\n" +
                        "        \"start\": \"{{startDate}}\",\n" +
                        "        \"end\": \"{{endDate}}\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"appointmentDetails\": {\n" +
                        "      \"pickup\": {\n" +
                        "        \"start\": \"{{startDate}}\",\n" +
                        "        \"end\": \"{{endDate}}\"\n" +
                        "      },\n" +
                        "      \"delivery\": {\n" +
                        "        \"start\": \"{{startDate}}\",\n" +
                        "        \"end\": \"{{endDate}}\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  },\n" +
                        " \"origin\": {\n" +
                        "    \"account\": \"B064700\",\n" +
                        "    \"locationId\": \"Building 3\",\n" +
                        "    \"name\": \"Home Depot MDO #5885\",\n" +
                        "    \"address1\": \"825 Arthur Avenue\",\n" +
                        "    \"address2\": \" \",\n" +
                        "    \"city\": \"Elk Grove Village\",\n" +
                        "    \"stateProvince\": \"IL\",\n" +
                        "    \"postalCode\": \"60007\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8045038175\",\n" +
                        "      \"phoneExt\": \"1357924680\",\n" +
                        "      \"name\": \"Shipper FName\",\n" +
                        "      \"email\": \"Shipper@Estes-Express.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"destination\": {\n" +
                        "    \"account\": \"B071818\",\n" +
                        "    \"locationId\": \"6106\",\n" +
                        "    \"name\": \"Home Depot #6106\",\n" +
                        "    \"address1\": \"4450 W University DR\",\n" +
                        "    \"address2\": \"BOL API CUSTTEAM4-137 \",\n" +
                        "    \"city\": \"PROSPER\",\n" +
                        "    \"stateProvince\": \"TX\",\n" +
                        "    \"postalCode\": \"75078\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"7721231234\",\n" +
                        "      \"phoneExt\": \"9876543210\",\n" +
                        "      \"name\": \"testNat Account\",\n" +
                        "      \"email\": \"Consignee@Estes-Express.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"billTo\": {\n" +
                        "    \"account\": \"1829900\",\n" +
                        "    \"locationId\": \"Texas Office\",\n" +
                        "    \"name\": \"Texas Branch\",\n" +
                        "    \"address1\": \"1717 N Harwood St.\",\n" +
                        "    \"address2\": \" \",\n" +
                        "    \"city\": \"Dallas\",\n" +
                        "    \"stateProvince\": \"TX\",\n" +
                        "    \"postalCode\": \"75201\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2224441234\",\n" +
                        "      \"phoneExt\": \"246801357\",\n" +
                        "      \"name\": \"Shane, Ricky\",\n" +
                        "      \"email\": \"BillTo@yahoo.com\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"customsBroker\": {\n" +
                        "    \"name\": \"Closets Closets Closets\",\n" +
                        "    \"address1\": \"3000 E Grand Ave\",\n" +
                        "    \"address2\": \" \",\n" +
                        "    \"city\": \"Des Moines\",\n" +
                        "    \"stateProvince\": \"IA\",\n" +
                        "    \"postalCode\": \"50317\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8004331209\",\n" +
                        "      \"phoneExt\": \"1357902468\",\n" +
                        "      \"name\": \"Marge R. Pinkerberry\",\n" +
                        "      \"email\": \"mrp@closets.com\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")
                .when()
                .post(RestAssured.baseURI)
                .then()
                .log().all()
                .statusCode(401).statusLine("HTTP/1.1 401 Unauthorized")
                .body("error.details", equalTo("Missing credentials"))
                .body("error.code", equalTo(30010))
                .body("error.message", equalTo("Failed authentication"))
                .extract().response();

    }

    /**
     * @author qcuthbert (NEEDS UPDATE)
     * Verify BOL API response displayed 201 Created status code and body with bol and shippingLabels Base64
     * string when the BOL request submitted with images.includeBol = TRUE & images.includeShippingLabels = true
     */

    @Test(enabled = true, priority = 5)
    public void executeQZ_12144() throws Exception{
        RestAssured.baseURI = "https://uat-cloudapi.estes-express.com/v1/bol"; // AWS endpoint
        String apiKey = "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ";
        String businessDate = testUtil.addFutureWeekDay();
        String pickupDate[] = businessDate.split("/");
        String futureDate = pickupDate[2] + "-" + pickupDate[0] + "-" + pickupDate[1];
        String token = testUtil.generateAuthenticationToken(username2, password2, apiKey);

        Response response = RestAssured.given().auth().oauth2(token)
                .header("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"bol\": {\n" +
                        "    \"date\": \""+ futureDate +"T00:00:00.000\",\r\n" +
                        "    \"function\": \"Create\",\n" +
                        "    \"isTest\": true,\n" +
                        "    \"requestorRole\": \"Third Party\"\n" +
                        "  },\n" +
                        "  \"images\": {\n" +
                        "    \"includeBol\": \"True\",\n" +
                        "    \"includeShippingLabels\": \"true\",\n" +
                        "    \"shippingLabels\": {\n" +
                        "      \"format\": \"avery\",\n" +
                        "      \"quantity\": \"3\",\n" +
                        "      \"position\": \"2\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"payment\": {\n" +
                        "    \"terms\": \"Prepaid\"\n" +
                        "  },\n" +
                        "  \"commodities\": {\n" +
                        "    \"handlingUnits\": [\n" +
                        "      {\n" +
                        "        \"count\": 4,\n" +
                        "        \"type\": \"SKD\",\n" +
                        "        \"weight\": 2000,\n" +
                        "        \"tareWeight\": \"100\",\n" +
                        "        \"length\": 48,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48,\n" +
                        "        \"stackable\": \"true\",\n" +
                        "    \"lineItems\": [\n" +
                        "      {\n" +
                        "        \"description\": \"HU 1.1\",\n" +
                        "        \"weight\": 1900,\n" +
                        "        \"pieces\": 12,\n" +
                        "        \"packagingType\": \"CTN\",\n" +
                        "        \"classification\": \"50\",\n" +
                        "        \"nmfc\": \"\",\n" +
                        "        \"nmfcSub\": \"\",\n" +
                        "        \"hazardous\": false,\n" +
                        "        \"hazardousDetails\": {\n" +
                        "          \"weight\": \"\",\n" +
                        "          \"class\": \"\",\n" +
                        "          \"unnaNumber\": \"\",\n" +
                        "          \"propername\": \"\",\n" +
                        "          \"technicalName\": \"\",\n" +
                        "          \"packagingGroup\": \"\",\n" +
                        "          \"contractNumber\": \"\"\n" +
                        "         }\n" +
                        "        }\n" +
                        "       ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"origin\": {\n" +
                        "    \"name\": \"Home Depot Outreach Center\",\n" +
                        "    \"address1\": \"2800 E Observatory Rd\",\n" +
                        "    \"city\": \"Los Angeles\",\n" +
                        "    \"stateProvince\": \"CA\",\n" +
                        "    \"postalCode\": \"90027\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"5552226666\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"destination\": {\n" +
                        "    \"name\": \"James River Equipment Sales\",\n" +
                        "    \"address1\": \"200 N Arthur Ashe Blvd\",\n" +
                        "    \"city\": \"Richmond\",\n" +
                        "    \"stateProvince\": \"VA\",\n" +
                        "    \"postalCode\": \"23220\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8045551212\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"billTo\": {\n" +
                        "    \"name\": \"Texas Branch\",\n" +
                        "    \"address1\": \"1717 N Harwood St.\",\n" +
                        "    \"city\": \"Dallas\",\n" +
                        "    \"stateProvince\": \"TX\",\n" +
                        "    \"postalCode\": \"75201\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2224441234\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")
                .when()
                .post(RestAssured.baseURI)
                .then()
                .log().all()
                .statusCode(201).statusLine("HTTP/1.1 201 ")
                .body("referenceNumbers.pro", notNullValue())
                .body("referenceNumbers.shipmentConfirmationNumber", notNullValue())
                .body("messageStatus.status", equalTo("PASS"))
                .body("messageStatus.code", equalTo("10000000"))
                .body("messageStatus.message", equalTo("Transaction was successful"))
                .body("messageStatus.resolution", equalTo(""))
                .extract().response();

        String bol = response.jsonPath().getString("images.bol");
        String label = response.jsonPath().getString("images.shippingLabels");

        Assert.assertTrue(bol.length() > 100);
        Assert.assertTrue(label.length() > 100);

    }

    /**
     * @author qcuthbert
     * Verify BOL API response displayed 400 Bad Request status code and body with Error message
     * details when the BOL request submitted with incorrect data in the request
     */

    @Test(enabled = true, priority = 6)
    public void executeQZ_12146() throws Exception{
        RestAssured.baseURI = "https://uat-cloudapi.estes-express.com/v1/bol";
        String apiKey = "t2qCkXKQpcirjGUQ3u1VdLuCBgBnzpsZ";
        String bolFileName = "./testBol.pdf";
        PDFParser parseBol;
        String bolText = null;
        String businessDate = testUtil.addFutureWeekDay();
        String pickupDate[] = businessDate.split("/");
        String futureDate = pickupDate[2] + "-" + pickupDate[0] + "-" + pickupDate[1];
        String token = testUtil.generateAuthenticationToken(username2, password2, apiKey);

        Response response = RestAssured.given().auth().oauth2(token)
                .header("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"bol\": {\n" +
                        "    \"date\": \"2000-01-01T10:00:00.000\",\n" +
                        "    \"function\": \"Create\",\n" +
                        "    \"isTest\": true,\n" +
                        "    \"requestorRole\": \"Third Party\"\n" +
                        "  },\n" +
                        "  \"images\": {\n" +
                        "    \"includeBol\": \"\",\n" +
                        "    \"includeShippingLabels\": \"\",\n" +
                        "    \"shippingLabels\": {\n" +
                        "      \"format\": \"\",\n" +
                        "      \"quantity\": \"\",\n" +
                        "      \"position\": \"\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"payment\": {\n" +
                        "    \"terms\": \"Prepaid\"\n" +
                        "  },\n" +
                        "  \"commodities\": {\n" +
                        "    \"handlingUnits\": [\n" +
                        "      {\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"count\": 1,\n" +
                        "        \"type\": \"SKD\",\n" +
                        "        \"weight\": 1500,\n" +
                        "        \"length\": 48,\n" +
                        "        \"width\": 48,\n" +
                        "        \"height\": 48\n" +
                        "      },\n" +
                        "            {\n" +
                        "        \"id\": \"2\",\n" +
                        "        \"count\": 2,\n" +
                        "        \"type\": \"BOX\",\n" +
                        "        \"weight\": 100,\n" +
                        "        \"length\": 24,\n" +
                        "        \"width\": 18,\n" +
                        "        \"height\": 12\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"lineItems\": [\n" +
                        "      {\n" +
                        "        \"handlingUnitId\": \"1\",\n" +
                        "        \"description\": \"HU 1.1 STUFF\",\n" +
                        "        \"weight\": 500,\n" +
                        "        \"pieces\": 1,\n" +
                        "        \"packagingType\": \"PAT\",\n" +
                        "        \"classification\": \"60\",\n" +
                        "        \"hazardous\": false\n" +
                        "      },\n" +
                        "           {\n" +
                        "        \"handlingUnitId\": \"1\",\n" +
                        "        \"description\": \"HU 1.2 STUFF\",\n" +
                        "        \"weight\": 900,\n" +
                        "        \"pieces\": 2,\n" +
                        "        \"packagingType\": \"CTN\",\n" +
                        "        \"classification\": \"70\",\n" +
                        "        \"hazardous\": false\n" +
                        "      },\n" +
                        "           {\n" +
                        "        \"handlingUnitId\": \"2\",\n" +
                        "        \"description\": \"HU 2.2 STUFF\",\n" +
                        "        \"weight\": 100,\n" +
                        "        \"pieces\": 2,\n" +
                        "        \"packagingType\": \"BOX\",\n" +
                        "        \"classification\": \"55\",\n" +
                        "        \"hazardous\": false\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"origin\": {\n" +
                        "    \"name\": \"Home Depot Outreach Center\",\n" +
                        "    \"address1\": \"2800 E Observatory Rd\",\n" +
                        "    \"city\": \"Los Angeles\",\n" +
                        "    \"stateProvince\": \"CA\",\n" +
                        "    \"postalCode\": \"90027\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"5552226666\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"destination\": {\n" +
                        "    \"name\": \"James River Equipment Sales\",\n" +
                        "    \"address1\": \"200 N Arthur Ashe Blvd\",\n" +
                        "    \"city\": \"Richmond\",\n" +
                        "    \"stateProvince\": \"VA\",\n" +
                        "    \"postalCode\": \"23220\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"8045551212\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"billTo\": {\n" +
                        "    \"name\": \"Texas Branch\",\n" +
                        "    \"address1\": \"1717 N Harwood St.\",\n" +
                        "    \"city\": \"Dallas\",\n" +
                        "    \"stateProvince\": \"TX\",\n" +
                        "    \"postalCode\": \"75201\",\n" +
                        "    \"country\": \"USA\",\n" +
                        "    \"contact\": {\n" +
                        "      \"phone\": \"2224441234\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")
                .when()
                .post(RestAssured.baseURI)
                .then()
                .log().all()
                .statusCode(400).statusLine("HTTP/1.1 400 ")
                .body("messageStatus.status", equalTo("FAIL"))
                .body("messageStatus.code", equalTo("10000001"))
                .body("messageStatus.message", equalTo("Transaction failed"))
                .body("messageStatus.resolution", equalTo("See message list for possible solutions"))
                .body("messageStatus.information[0].type", equalTo("E"))
                .body("messageStatus.information[0].code", equalTo("EBG0002"))
                .body("messageStatus.information[0].message", equalTo("Your BOL timestamp is not valid. Please check to ensure the timestamp meets the ISO 8601 format (yyyy-mm-ddThh:mm:ss.mmm)."))
                .extract().response();

    }

}