package rateRetrieverTests;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLTLQuoteHistoryDetailPage;
import eNetPages.ENetLTLRateQuotePage;
import eNetPages.ENetLTLRateRequestPage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetQuoteHistoryLookupPage;
import jagacyVT.screens.DisplayRequestServiceInfoScreen;
import jagacyVT.screens.GoldMedalMainMenuScreen;
import jagacyVT.screens.IBMMainMenuScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.WorkWithRequestScreen;
import testBase.TestBase;
import util.TestUtil;

public class RateRetrieverSmokeTest extends TestBase {

    ENetLoginPage eNetLoginPage = new ENetLoginPage();
    ENetHomePage eNetHomePage = new ENetHomePage();
    ENetLTLRateQuotePage eNetLTLRateQuotePage = new ENetLTLRateQuotePage();
    ENetLTLRateRequestPage eNetLTLRateRequestPage = new ENetLTLRateRequestPage();
    ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage();
    ENetLTLQuoteHistoryDetailPage eNetLTLQuoteHistoryDetailPage = new ENetLTLQuoteHistoryDetailPage();
    ENetQuoteHistoryLookupPage eNetQuoteHistoryLookupPage = new ENetQuoteHistoryLookupPage();

    /******************************* TESTS *******************************/

    /**
     * This test passed on 6/29/22
     * 
     * eNet - LTL - Rate Retriever with Account Numbers-QZ659 is in QAEnvironment
     * Smoke Test
     **/

    /* Smoke - eNet - LTL - Rate Retriever */

    @Test(enabled = true, priority = 1)
    public void executeQZ_942() throws InterruptedException {

        String accontNumber = "7178618";
        String desc1 = "CALIFORNIA COMPLIANCE SURCHARGE";

        // Step 1: Login to application
        driver.get(url2);
        eNetLoginPage.enterUserID(username16);
        eNetLoginPage.enterUserPassword(password16);
        eNetLoginPage.clickOnLoginButton();
        testUtil.setHardWait(1000);

        // Step 2: Click Customer Service link
        eNetHomePage.clickOnCustomerServiceLink();
        testUtil.setHardWait(1000);
        // Step 3: Click Rate Retriever link
        eNetHomePage.clickOnRateRetrieverLink();
        testUtil.setHardWait(1000);
        // Step 4: Enter values and click submit
        eNetLTLRateQuotePage.verifLTLRateQuotePage();
        eNetLTLRateQuotePage.enterAccountNumber(accontNumber);
        eNetLTLRateQuotePage.selectRole("Shipper");
        eNetLTLRateQuotePage.selectTerm("Pre-paid");
        eNetLTLRateQuotePage.enterOriginZip("90001");
        eNetLTLRateQuotePage.enterDestinationZip("99205");
        eNetLTLRateQuotePage.selectClass("55");
        eNetLTLRateQuotePage.enterWeight("1000");
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // Verify Quote number returned
        String quoteNumber = eNetLTLRateRequestPage.recordQuoteNo();

        // Verify California Compliance Surcharge is displayed
        eNetLTLRateRequestPage.verifyQuoteSummaryByDesc(desc1);

        // Verify Net Freight charge is displayed
        eNetLTLRateRequestPage.verifyEstimatedFreightCharge();

        // Verify Guaranteed by 10 AM is returned
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
    }

    /**
     * This test passed on 6/29/22
     * 
     * eNet - LTL - Supporting Terminal information
     */

    @Test(enabled = true, priority = 2)
    public void executeQZ_907() throws InterruptedException {

        String acctNo = "5068692";

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();

        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();
        testUtil.setHardWait(2000);
        // Step 5: Enter Contact and Routing information
        eNetLTLRateQuotePage.verifLTLRateQuotePage(); // Added
        eNetLTLRateQuotePage.enterAccountNumber(acctNo);
        eNetLTLRateQuotePage.selectRole("Shipper");
        eNetLTLRateQuotePage.selectTerm("Pre-paid");
        eNetLTLRateQuotePage.enterOriginZip("60001");
        eNetLTLRateQuotePage.enterDestinationZip("30307");

        // Step 6: Enter Commodities details
        eNetLTLRateQuotePage.selectClass("55");
        eNetLTLRateQuotePage.enterWeight("3500");
        eNetLTLRateQuotePage.enterDesc("Line 1");
        eNetLTLRateQuotePage.selectClass1("100");
        eNetLTLRateQuotePage.enterWeight1("1217");
        eNetLTLRateQuotePage.enterDesc1("Line 2");

        // Step 7: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // LTL Rate Request page displays
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 8: Click on Origin Terminal Link
        eNetLTLRateRequestPage.clickOnOriginLink();
        eNetLTLRateRequestPage.verifySupportingTerminalPopup();
        eNetLTLRateRequestPage.verifyAddressDetailsInSupportingTerminalPopup("Rockford, IL   61109", "(815) 874-5453",
                "18158744815");
        eNetLTLRateRequestPage.clickOnCloseInPopUp();

        // Step 9: Click on Destination Terminal Link
        eNetLTLRateRequestPage.clickOnDestinationLink();
        eNetLTLRateRequestPage.verifySupportingTerminalPopup();
        eNetLTLRateRequestPage.verifyAddressDetailsInSupportingTerminalPopup("Ellenwood, GA   30294", "(404) 361-2430",
                "14043613193");
        eNetLTLRateRequestPage.clickOnCloseInPopUp();

        // Step 10: Logout
        eNetHomePage.clickOnLogout();
        eNetHomePage.clickOnLogoutButtonFreight();
    }

    /*
     * Rate Retriever - Verify when Base Rate is selected the Discount field
     * displays and is auto-populated with the default discount value (currently
     * 70%)
     */

    @Test(enabled = true, priority = 3)
    public void executeQZ_898() throws InterruptedException {

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();
        testUtil.setHardWait(1000);
        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();

        // Step 5: Verify Select one is defaulted to Account specific and Discount field
        // is not displayed
        eNetLTLRateQuotePage.verifyDefaultValueInSelectOne();
        eNetLTLRateQuotePage.verifyDiscountFieldIsDisplayed(false);

        // Step 6: Select Base Rate from selectOne drop down
        eNetLTLRateQuotePage.selectOne("BR");

        // Verify Discount field is displayed and auto populated with value 70
        eNetLTLRateQuotePage.verifyDiscountFieldIsDisplayed(true);

        // Step7: Enter Origin and destination
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
        testUtil.switchToFrame(frame1);
        eNetLTLRateQuotePage.enterOriginZip("23059");
        eNetLTLRateQuotePage.enterDestinationZip("60007");

        // Step 8: Enter Commodities details in line 1
        eNetLTLRateQuotePage.selectClass("65");
        eNetLTLRateQuotePage.enterWeight("300");
        eNetLTLRateQuotePage.enterDesc("QZ-898 Base Rate & Discount");

        // Step 9: Enter Commodities details in line 2
        eNetLTLRateQuotePage.selectClass1("100");
        eNetLTLRateQuotePage.enterWeight1("27");
        eNetLTLRateQuotePage.enterDesc1("QZ-898 Base Rate & Discount");

        // Step 10: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // LTL Rate Request page displays
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 11: Verify Account details from quote information section
        eNetLTLRateRequestPage.verifyAccountInQuoteInfoSection("TEST ACCOUNT/RATES/WEB 1 - 0212603");

        // Step 12: Verify Discount 70.00% dispalyes as a lone item in quote aummary
        // section
        eNetLTLRateRequestPage.verifyDiscountInQuoteSummarySection("Discount 70.00%");

        // Step 13: Logout
        eNetHomePage.clickOnLogout();

        // Step 14: Confirm logout
        eNetHomePage.clickOnLogoutButtonFreight();
    }

    /*
     * This test is turned off due to an actual validation error. [Attention:
     * Additional charges may apply. Please call our rate quote department at
     * 804-353-1900, Ext. 2269, so we can help make sure you are aware of any
     * applicable charges.] but found [Attention: Manual Accessorial]
     */

    /*
     * Rate Retriever - Verify when Origin or Destination is a blocked point, then
     * LTL Standard Transit rates are not calculated and an error message is
     * displayed
     */

    @Test(enabled = false, priority = 4)
    public void executeQZ_873() throws InterruptedException {

        String acctNo = "5068692";
        String errorText = "Attention: Additional charges may apply. Please call our rate quote department at 804-353-1900, Ext. 2269, "
                + "so we can help make sure you are aware of any applicable charges.";

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();

        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();

        // Step 5: Enter Contact and Routing information
        eNetLTLRateQuotePage.enterAccountNumber(acctNo);
        eNetLTLRateQuotePage.selectRole("Third Party");
        eNetLTLRateQuotePage.selectTerm("Pre-paid");
        eNetLTLRateQuotePage.enterOriginZip("90007");
        eNetLTLRateQuotePage.enterDestinationZip("98070");

        // Step 6: Enter Commodities details
        eNetLTLRateQuotePage.selectClass("50");
        eNetLTLRateQuotePage.enterWeight("350");
        eNetLTLRateQuotePage.enterDesc("QZ-873 Blocked Points");

        // Step 7: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmit();

        // Verify error message
        eNetLTLRateQuotePage.verifyErrorMsg(errorText);

        // Step 8: Click origin clear button
        eNetLTLRateQuotePage.clickOnOriginClearButton();

        // Step 9: Click on destination clear button
        eNetLTLRateQuotePage.clickOnDestinationClearButton();

        // Step 10: Enter blocked point as origin
        eNetLTLRateQuotePage.enterOriginZip("98070");
        eNetLTLRateQuotePage.enterDestinationZip("90007");

        // Step 11: Click on Submit
        eNetLTLRateQuotePage.clickOnSubmit();

        // Verify error message
        eNetLTLRateQuotePage.verifyErrorMsg(errorText);

        // Step 12: Logout
        eNetHomePage.clickOnLogout();

        // Step 13: Confirm logout
        eNetHomePage.clickOnLogoutButtonFreight();
    }

    /**
     * Rate Retriever - Verify when Overlength Freight (20.00' to 27.99')
     * accessorial is selected charges are displayed in Quote Summary and Guaranteed
     * By 10 AM, 12 PM, and 5 PM are not calculated Contact Us instead
     */

    @Test(enabled = true, priority = 5)
    public void executeQZ_917() throws InterruptedException {

        String acctNo = "9451455";
        String accessorialName = "Overlength Freight (20.00' to 27.99')";
        String charge = "$0.00";

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();
        testUtil.setHardWait(1000);
        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();
        testUtil.setHardWait(2000);
        // Step 5: Enter data in Contact and Routing information

        eNetLTLRateQuotePage.verifLTLRateQuotePage(); // Added

        eNetLTLRateQuotePage.enterAccountNumber(acctNo);
        eNetLTLRateQuotePage.selectRole("Third Party");
        eNetLTLRateQuotePage.selectTerm("Pre-paid");
        ;
        eNetLTLRateQuotePage.enterOriginZip("23230");
        eNetLTLRateQuotePage.enterDestinationZip("30307");

        // Step 6: Enter Commodities details in line 1
        eNetLTLRateQuotePage.selectClass("50");
        eNetLTLRateQuotePage.enterWeight("350");
        eNetLTLRateQuotePage.enterDesc("QZ-917 Long 28");

        // Step 7: Enter Commodities details in line 2
        eNetLTLRateQuotePage.selectClass1("60");
        eNetLTLRateQuotePage.enterWeight1("170");
        eNetLTLRateQuotePage.enterDesc1("QZ-917 Long 28");

        // Step 8: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // LTL Rate Request page displays
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 9: Verify Guaranteed by 10 AM,12PM and 5 PM are calculated
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");

        // Step 10: Click on Update Quote
        eNetLTLRateRequestPage.clickOnUpdateQuote();

        // Step 11: Click on Over-length Freight (20.00' to 27.99') checkbox
        eNetLTLRateQuotePage.clickOnOverlengthAccessorial(accessorialName);

        // Step 12: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // Step 13: Record quote#
        String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();

        // Step 14: Verify Over-length Freight (20.00' to 27.99') displayed with charges
        eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(accessorialName, charge);

        // Step 15: Verify Guaranteed displays Contact Us
        eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
        eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");
        eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed5");

        // Step 16: Logout
        eNetHomePage.clickOnLogout();

        // Step 17: Confirm logout
        eNetHomePage.clickOnLogoutButtonFreight();

    }

    /**
     * this test passed after adding iframe for account number
     * 
     * Rate Retriever - Verify if a zip code services are specified in the points
     * file, then the Guaranteed By 10AM, 12 PM, 5 PM are calculated based on the
     * points
     */

    @Test(enabled = true, priority = 6)
    public void executeQZ_887() throws InterruptedException {

        String acctNo = "5068692";

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();

        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();
        eNetLTLRateQuotePage.verifLTLRateQuotePage(); // Added
        // Step 5: Enter data in Contact and Routing information
        eNetLTLRateQuotePage.enterAccountNumber(acctNo);
        eNetLTLRateQuotePage.selectRole("Third Party");
        eNetLTLRateQuotePage.selectTerm("Collect");
        ;
        eNetLTLRateQuotePage.enterOriginZip("23230");
        eNetLTLRateQuotePage.enterDestinationZip("30307");

        // Step 6: Enter Commodities details in line 1
        eNetLTLRateQuotePage.selectClass("50");
        eNetLTLRateQuotePage.enterWeight("97");
        eNetLTLRateQuotePage.enterDesc("QZ-887 Gauranteed Points");

        // Step 7: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // LTL Rate Request page displays
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 8: Verify Guaranteed by 10 AM,12PM and 5 PM are calculated
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed10");
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");

        // Step 9: Click on Update Quote
        eNetLTLRateRequestPage.clickOnUpdateQuote();

        // Step 10: Update destination to 64501
        eNetLTLRateQuotePage.clickOnDestinationClearButton();
        eNetLTLRateQuotePage.enterDestinationZip("64501");

        // Step 11: Click on submit
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // Step 12: Verify charges not calculated for Guaranteed 10 AM
        eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
        testUtil.setHardWait(1000);
        // Step 13: Verify charges calculated for Guaranteed 12PM and 5 PM
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed12");
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");

        // Step 14: Click on update quote
        eNetLTLRateRequestPage.clickOnUpdateQuote();

        // Step 15: Update the below data for destination
        eNetLTLRateQuotePage.clickOnDestinationClearButton();
        eNetLTLRateQuotePage.selectDestinationCountry("CN");
        eNetLTLRateQuotePage.enterDestinationZip("H1A0A1");

        // Step 16: Click submit
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // Step 17: Verify charges not calculated for Guaranteed 10 AM and 12 PM
        eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed10");
        eNetLTLRateRequestPage.verifyContactUsDisplayed("guaranteed12");

        // Step 18: Verify charges calculated for Guaranteed 5 PM
        eNetLTLRateRequestPage.verifyRateDisplayed("guaranteed5");

        // Step 19: Logout
        eNetHomePage.clickOnLogout();

        // Step 20: Confirm logout
        eNetHomePage.clickOnLogoutButtonFreight();
    }

    /**
     * this test passed on 6/14/22. Sometimes test fail because of step 12, the
     * screen does not appear
     * 
     * Rate Retriever - Verify selected Guaranteed LTL Standard Transit quotes are
     * save to the GMS system in AS400 once the service level is selected
     * 
     * @throws InterruptedException
     */

    @Test(enabled = true, priority = 7)
    public void executeQZ_868() throws Exception {

        String acctNo = "9451455";
        String searchJob = "CALL GMR000";
        String searchValue = "5";
        String type = "5";
        String name = "myJagacyVT";
        String terminal = "dec-vt220";

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username16);
        eNetLoginPage.enterUserPassword(password16);
        eNetLoginPage.clickOnLoginButton();
        // eNet home page is displays
        eNetHomePage.verifyPageTitle();

        // VERIFICATION STEP FOR ENET HOME PAGE IS MISSING

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();

        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();
        // Verify Rate Retreiver tool
        eNetLTLRateQuotePage.verifLTLRateQuotePage(); // Added

        // Step 5: Select Account specific and Enter account 9451455
        eNetLTLRateQuotePage.enterAccountNumber(acctNo);
        // Data displays as entered
        eNetLTLRateQuotePage.verifyAccountNumber(acctNo); // Added

        // Step 6: Enter data in required fields and click on submit
        eNetLTLRateQuotePage.selectRole("Shipper");
        eNetLTLRateQuotePage.selectTerm("Pre-paid");

        eNetLTLRateQuotePage.enterOriginZip("30307");
        eNetLTLRateQuotePage.enterDestinationZip("77007");
        eNetLTLRateQuotePage.selectClass("50");
        eNetLTLRateQuotePage.enterWeight("100");
        eNetLTLRateQuotePage.enterDesc("QZ-868");
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // Verify LTL Rate Request page displays
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 7: Select Guaranteed option that a price returned
        eNetLTLRateRequestPage.SelectQuoteByServiceLevel("gauranteed10AM");

        // Step 8: Record quote number, rate selected, origin, destination
        String quoteNo = eNetLTLRateRequestPage.recordQuoteNo();
        System.out.println(quoteNo);
        Thread.sleep(2000);
        // Step 9: Login to exlaqa
        SessionVt session = null;
        session = new SessionVt(name, "exlaqa", terminal);
        session.open();
        Thread.sleep(2000);

        LoginScreen loginScreen = new LoginScreen(session);
        loginScreen.logon(username16, password16);
        Thread.sleep(3000);

        // EXLAQA main menu displays
        IBMMainMenuScreen ibmMainMenuScreen = new IBMMainMenuScreen(session);
        Thread.sleep(7000);
//		ibmMainMenuScreen.verifyIBMMainMenuScreen();

        // Step 10: Enter 'Call GMR000' in command line
        ibmMainMenuScreen.enterValueToComandLineField(searchJob);

        // Gold Medal screen displays
        GoldMedalMainMenuScreen goldMedalMainMenuScreen = new GoldMedalMainMenuScreen(session);
        goldMedalMainMenuScreen.verifyGoldMedalMainMenuScreen();

        // Step 11: Enter option 5 and press Enter
        goldMedalMainMenuScreen.enterOption(searchValue);
        testUtil.setHardWait(2000);

        // Verify Work with request screen displays
        WorkWithRequestScreen workWithRequestScreen = new WorkWithRequestScreen(session);
        workWithRequestScreen.verifyWorkWithRequestInfoScreen();
        // Newely added steps
        // Step 12: Enter shiftF5
        testUtil.setHardWait(2000);
        goldMedalMainMenuScreen.enterF17Key();

        // Step 12: Find quote number recorded in Step 8
        // Enter option 5 in the opt field and Press Enter
        workWithRequestScreen.enterQuoteNumber(quoteNo);
        workWithRequestScreen.enterValueInOptField(type);
        testUtil.setHardWait(7000);

        // Display request service information screen displays
        DisplayRequestServiceInfoScreen displayRequestServiceInfoScreen = new DisplayRequestServiceInfoScreen(session);
        displayRequestServiceInfoScreen.verifyDisplayRequestScreen();
        testUtil.setHardWait(5000);
        // Step 13: Verify data recorded in Step 8 is displayed
        displayRequestServiceInfoScreen.verifyQuoteDetails(quoteNo, "30307", "77007", acctNo);

        if (session != null) {
            session.abort();
            session.close();

        }

    }

    /**
     * This test passed on 6/29/22
     * 
     * Rate Retriever - Verify when the user selects any accessorial then the
     * accessorial description and charges are displayed as line items in the Quote
     * Summary section
     */

    @Test(enabled = true, priority = 8)
    public void executeQZ_911() throws InterruptedException {

        String acctNo = "9451455";
        String charge = "$0.00";
        String acc1 = "Notify Request";
        String acc2 = "Inside Pickup";
        String acc3 = "Lift-Gate Service (Delivery)";
        String acc4 = "Overlength (8.00'-11.99')";
        String acc5 = "Unloading Services Requested By Consignee";

        // Step 1: Open enet application
        driver.get(url2);

        // Step 2: Login to eNet app
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 3: Click on Applications
        eNetHomePage.clickOnApplicationsTab();
        testUtil.setHardWait(2000);
        // Step 4: Click on Rate Retriever link from customer service section
        eNetHomePage.clickOnRateRetrieverLink();
        eNetLTLRateQuotePage.verifLTLRateQuotePage(); // Added
        // Step 5: Enter data in Contact and Routing information
        eNetLTLRateQuotePage.enterAccountNumber(acctNo);
        eNetLTLRateQuotePage.selectRole("Shipper");
        eNetLTLRateQuotePage.selectTerm("Pre-paid");
        ;
        eNetLTLRateQuotePage.enterOriginZip("23230");
        eNetLTLRateQuotePage.enterDestinationZip("30307");

        // Step 6: Enter Commodities details in line 1
        eNetLTLRateQuotePage.selectClass("50");
        eNetLTLRateQuotePage.enterWeight("1200");
        eNetLTLRateQuotePage.enterDesc("QZ-911 Accessorials");

        // Step 7: From Accessorials section select the following accessorials
        eNetLTLRateQuotePage.clickOnExpandButton();
        eNetLTLRateQuotePage.clickOnAccessorial(acc1);
        eNetLTLRateQuotePage.clickOnAccessorialByCode("INP");
        eNetLTLRateQuotePage.clickOnAccessorial(acc3);
        testUtil.setHardWait(2000);
        eNetLTLRateQuotePage.clickOnOverlengthAccessorial(acc4);
        eNetLTLRateQuotePage.clickOnAccessorial(acc5);

        // Step 8: Click on Submit button
        eNetLTLRateQuotePage.clickOnSubmitButton();

        // LTL Rate Request page displays
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 9: Verify all selected accessorials are displayed
        // Note: Charges could be $0.00
        eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(acc1, charge);
        eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(acc2, charge);
        eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(acc3, charge);
        eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(acc4, charge);
        eNetLTLRateRequestPage.verifyAccessorialIsDisplayedWithCharge(acc5, charge);

        // Step 10: Click log out
        eNetHomePage.clickOnLogout();

        // Step 11: Confirm logout
        eNetHomePage.clickOnLogOutButton();
    }

    /**
     * this test is passing on 6/29/22
     * 
     * Rate Retriever - Verify on the fly lookup functionality for Origin and
     * Destination fields
     */

    @Test(enabled = true, priority = 9)
    public void executeQZ_913() throws InterruptedException {

        // Step1:Open the following link:<http://enetqa.estesinternal.com/>
        driver.get(url2);

        // Step2: Log into *eNet*
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step3: From eNet page top navigation Click *Applications*
        eNetHomePage.clickOnApplicationsTab();

        // Step4: From the *Applications* page, find the *Customer Service* applications
        // list, and click *Rate Retriever* link
        eNetApplicationsPage.clickOnRateRetrieverLink();

        // *LTL Rate Quote* form displays
        eNetLTLRateQuotePage.verifLTLRateQuotePage();
        testUtil.setHardWait(2000);
        // Step5: From the *LTL Rate Quote* page, *Contact and Routing Information*
        // section,
        // enter/select the following data:
        // Account: 5068692,My Role: Third
        // Party,Terms: Collect
        String account = "5068692";
        eNetLTLRateQuotePage.enterAccountNumber(account);
        eNetLTLRateQuotePage.selectRole("Third Party");
        eNetLTLRateQuotePage.selectTerm("Collect");

        // Step6:Enter a valid Origin Zip
        /*
         * User is provided with a 'on-the-fly' list of options associated with the
         * *Origin Zip* entered; this list may contain 1 or up to 10 valid locations for
         * selection
         */
        eNetLTLRateQuotePage.enterOrigZip("2323");
        eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();

        // Step7:Select a link
        // Origin is populated with the selected Zip, City, and State
        eNetLTLRateQuotePage.clickOnReqSuggests("RICHMOND, VA 23230");

        // Step8:Enter a valid Destination Zip
        eNetLTLRateQuotePage.enterDestZip("2323");

        /*
         * User is provided with a 'on-the-fly' list of options associated with the
         * *Destination Zip* entered; this list may contain 1 or up to 10 valid
         * locations for selection
         */
        eNetLTLRateQuotePage.verifyDestSuggestDisplayed();

        // Step9:Select a link.Destination is populated with the selected Zip, City, and
        // State
        eNetLTLRateQuotePage.clickOnReqSuggests("HENRICO, VA 23233");

        // Step10:From the *LTL Rate Quote* page, *Commodities* section,
        // enter/select the following :Class: 50Total Weight: 970
        // Description: QZ-913 on the fly lookup
        eNetLTLRateQuotePage.selectClass("50");
        eNetLTLRateQuotePage.enterWeight("970");
        eNetLTLRateQuotePage.enterDesc("QZ-913 on the fly lookup");

        // Step11: Click *Submit*
        eNetLTLRateQuotePage.clickOnSubmit();
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        String originZip = eNetLTLRateRequestPage.getRouteOrigin();
        TestUtil.verifyText(originZip, "RICHMOND, VA 23230 US");
        String destZip = eNetLTLRateRequestPage.getRouteDest();
        TestUtil.verifyText(destZip, "HENRICO, VA 23233 US");

        // Step12: Scroll down and click the *Update Quote* button
        eNetLTLRateRequestPage.clickOnUpdateQuote();

        // Step13:From the *LTL Rate Quote* page, *Contact and Routing Information*
        // section,click *Clear* Origin button
        eNetLTLRateQuotePage.clickOnOrigClrBtn();

        // Step14: Click *Clear* Destination button
        eNetLTLRateQuotePage.clickOnDestClrBtn();

        // Step15: Enter a valid Origin City
        eNetLTLRateQuotePage.enterOrigCity("ACHILLES");
        eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();

        // Step16: Select a link
        eNetLTLRateQuotePage.clickOnReqSuggests("ACHILLES, VA 23001");

        // Step17: Enter a valid Destination City
        eNetLTLRateQuotePage.enterDestCity("AMELIA");
        eNetLTLRateQuotePage.verifyDestSuggestDisplayed();

        // Step18: Select a link
        eNetLTLRateQuotePage.clickOnReqSuggests("AMELIA COURT HOUSE, VA 23002");

        // Step19: Click *Submit*
        eNetLTLRateQuotePage.clickOnSubmit();
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        String originCity = eNetLTLRateRequestPage.getRouteOrigin();
        TestUtil.verifyText(originCity, "ACHILLES, VA 23001 US");
        String destCity = eNetLTLRateRequestPage.getRouteDest();
        TestUtil.verifyText(destCity, "AMELIA COURT HOUSE, VA 23002 US");

        // Step20: Scroll down and click the *Update Quote* button
        eNetLTLRateRequestPage.clickOnUpdateQuote();

        // Step21: From the *LTL Rate Quote* page, *Contact and Routing Information*
        // section, click *Clear* Origin button
        eNetLTLRateQuotePage.clickOnOrigClrBtn();

        // Step22: Click *Clear* Destination button
        eNetLTLRateQuotePage.clickOnDestClrBtn();

        // Step23: Enter a valid Origin State
        eNetLTLRateQuotePage.enterOrigState("VA");
        eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();

        // Step24: Select a link
        eNetLTLRateQuotePage.clickOnReqSuggests("ALDIE, VA 20105");

        // Step25: Enter a valid Destination State
        eNetLTLRateQuotePage.enterDestState("SC");
        eNetLTLRateQuotePage.verifyOriginSuggestDisplayed();

        // Step26: Select a link
        eNetLTLRateQuotePage.clickOnReqSuggests("BALLENTINE, SC 29002");

        // Step27: Click *Submit*
        eNetLTLRateQuotePage.clickOnSubmit();

        // Step28:From the *LTL Rate Request* page,
        // Validate Origin and Destination data in the *Routing* section are correct
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        String originState = eNetLTLRateRequestPage.getRouteOrigin();
        TestUtil.verifyText(originState, "ALDIE, VA 20105 US");
        String destState = eNetLTLRateRequestPage.getRouteDest();
        TestUtil.verifyText(destState, "BALLENTINE, SC 29002 US");

        // Step29: From *eNet* home page top navigation menu,click *Log Out*
        eNetHomePage.clickOnLogout();

        // Step30: Confirm by clicking the *Logout* button
        eNetHomePage.clickOnLogoutButtonFreight();

    }

    /**
     * 
     * Failing at step 11 because quote is not being displayed, it also takes long
     * time for the quote to appear in the quote history
     * 
     * 
     * eNet - LTL - LTL Rate Quote saved in My Save Quotes
     * 
     * @throws Exception
     */

    @Test(enabled = true, priority = 10)
    public void executeQZ_888() throws Exception {

        String account = "7178618";
        String role = "Shipper";
        String terms = "Pre-paid";
        String origin = "90002";
        String dest = "23219";
        String cls = "55";
        String weight = "1000";

        // Step 1: Login to eNet
        driver.get(url2);
        System.out.println(
                "<**************************THIS TEST IS FAILING BECAUSE OF THE QUOTE DISPLAED IN THE QUOTE HISTORY WITH DELAY***********>");
        eNetLoginPage.enterUserID(username5);
        eNetLoginPage.enterUserPassword(password5);
        eNetLoginPage.clickOnLoginButton();

        // Step 2: Click on Customer Service

        eNetHomePage.clickOnCustomerServiceLink();

        // Step 3: Select Rate Retriever
        eNetHomePage.clickOnRateRetrieverLink();

        // Verify LTL Rate page
        eNetLTLRateQuotePage.validatePagetitle("LTL Rate Quote");

        // Step 4: Enter contact and routing information
        eNetLTLRateQuotePage.enterAccountNumber(account);
        eNetLTLRateQuotePage.selectRole(role);
        eNetLTLRateQuotePage.selectTerm(terms);
        eNetLTLRateQuotePage.enterOrigZip(origin);
        eNetLTLRateQuotePage.clickOnReqSuggests("LOS ANGELES, CA 90002");
        eNetLTLRateQuotePage.enterDestZip(dest);
        eNetLTLRateQuotePage.clickOnReqSuggests("RICHMOND, VA 23219");
        eNetLTLRateQuotePage.selectClass(cls);
        eNetLTLRateQuotePage.enterWeight(weight);

        // Record the values entered -> We have all this data entered

        // Step 5: Click *Submit*
        eNetLTLRateQuotePage.clickOnSubmit();

        // User is brought to the LTL Rate Request page
        eNetLTLRateRequestPage.verifLTLRateRequestPage();

        // Step 6: From LTL Rate Quote screen, Record the following details
        // Quote Number,Rate, Discount, Fuel Surcharge, Net Freight Charges
        String quote = eNetLTLRateRequestPage.getQuoteInfo("Quote Number:");
        String rate = eNetLTLRateRequestPage.getRateFromCommodities(1);
        String total = eNetLTLRateRequestPage.getQuoteSummaryValueOf("Discount");
        String fuel = eNetLTLRateRequestPage.getQuoteSummaryValueOf("Fuel Surcharge");
        String netCharge = eNetLTLRateRequestPage.getQuoteSummaryValueOf("Estimated Freight Charges");

        // Step 7: Click on the Application tab
        eNetLTLRateRequestPage.clickOnApplicationWithoutFrame();

        // Step 8: Click on the Quote History Lookup link
        eNetApplicationsPage.clickOnQuoteHistoryLookupLink();
        driver.switchTo().defaultContent();

        // Step 9: Enter the following details on Quote History look up screen
        WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
        testUtil.switchToFrame(frame);
        eNetQuoteHistoryLookupPage.clickOnShowFilters();

        eNetQuoteHistoryLookupPage.enterQuoteNum(quote);
        eNetQuoteHistoryLookupPage.clickSearchAndClickQuoteNum(quote, 30);

        // User is brought to the Quote History Details screen

        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainpage");
        // Step 12: Verify the details recorded in step 4 & 6 are equal
        // testUtil.switchToFrame(frame);

        Assert.assertTrue(eNetLTLQuoteHistoryDetailPage.getServiceInfo("Service Level:").contains("LTL Standard"));
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getServiceInfo("Quote Number:"), quote);
        Assert.assertTrue(eNetLTLQuoteHistoryDetailPage.getServiceInfo("Account:").contains(account));
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getServiceInfo("My Role:"), role);
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getServiceInfo("Terms:"), terms);
        Assert.assertTrue(eNetLTLQuoteHistoryDetailPage.getRoutingInfo("Origin:").contains(origin));
        Assert.assertTrue(eNetLTLQuoteHistoryDetailPage.getRoutingInfo("Destination:").contains(dest));
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getFeeSummaryValueOf("Class"), cls);
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getFeeSummaryValueOf("Weight"), weight);
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getFeeSummaryValueOf("Rate"), rate);
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getChargeItems("Discount"), total);
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getChargeItems("Fuel Surcharge"), fuel);
        Assert.assertEquals(eNetLTLQuoteHistoryDetailPage.getChargeItems("Estimated Freight Charges"), netCharge);

        // Step 13: Log out
        eNetLTLQuoteHistoryDetailPage.clickOnLogout();
        eNetLTLQuoteHistoryDetailPage.clickOnLogoutIntranet();

    }

    // THIS NEEDS TO RUN AFTER 3:00!!!
    /*
     * LTL Rate Retriever - Verify a message is displayed for Rate Quote created
     * after 3PM origin terminal local time
     */

    @Test(enabled = true, priority = 11)
    public void executeQZ_909() throws ParseException {
        // Step#1 : Note: Test case should be executed after 3PM Origin Terminal local
        // time

        SimpleDateFormat objSDF = new SimpleDateFormat("HH:mm aa");
        Date date = new Date();
        Date dt_1 = objSDF.parse("15:00 PM");
        System.out.println("Date1 : " + objSDF.format(dt_1));
        System.out.println("Date2 : " + objSDF.format(date));
        String currentTime = objSDF.format(date);
        if (currentTime.contains("PM")) {
            if ((dt_1.compareTo(date) > 0) && dt_1.compareTo(date) == 0) {
                System.out.println("Date 1 occurs after Date");

                // Step#2 : Open the following link:
                // <http://enetqa.estesinternal.com/>
                // Note : (UAT environment) : https://estessuremove-uat.estesinternal.com/
                driver.get(url2);

                // Step#3 : Login to *eNet* application using the following credentials:
                // User ID: qaenet01, Password: qaenet01
                eNetLoginPage.enterUserID(username5);
                eNetLoginPage.enterUserPassword(password5);
                eNetLoginPage.clickOnLoginButton();

                // Step#4 : From *eNet* home page top navigation menu, click *Applications*
                eNetHomePage.validatePage();
                eNetHomePage.clickOnApplicationsTab();

                // Step#5 : From the *Applications* page, find the *Customer Service*
                // applications list, and click *Rate Retriever* link
                eNetApplicationsPage.verifyPageTtl();
                eNetApplicationsPage.clickOnRateRetrieverLink();

                // Step#6 : From the *LTL Rate Quote* page, *Contact and Routing Information*
                // section, enter/select the following data:
                // Account: 5068692, My Role: Shipper, Terms: Collect, Origin: 30307,
                // Destination: 90007
                eNetLTLRateQuotePage.verifLTLRateQuotePage();
                eNetLTLRateQuotePage.enterAccountNumber("5068692");
                eNetLTLRateQuotePage.selectRole("Shipper");
                eNetLTLRateQuotePage.selectTerm("Collect");
                eNetLTLRateQuotePage.enterOriginZip("30307");
                eNetLTLRateQuotePage.enterDestinationZip("90007");

                // Step#7 : From the *LTL Rate Quote* page, *Commodities* section, enter/select
                // the following data:
                // Class: 50, Total Weight: 350, Description: QZ-909 After 3PM disclaimer
                eNetLTLRateQuotePage.selectClass("50");
                eNetLTLRateQuotePage.enterWeight("350");
                eNetLTLRateQuotePage.enterDesc("QZ-909 After 3PM disclaimer");

                // Step#8 : In Commodity #2 Enter/select the following data:
                // Class: 60, Total Weight: 130, Description: QZ-909 After 3PM disclaimer
                eNetLTLRateQuotePage.selectClass1("60");
                eNetLTLRateQuotePage.enterWeight1("130");
                eNetLTLRateQuotePage.enterDesc1("QZ-909 After 3PM disclaimer");

                // Step#9 : Click *Submit*
                eNetLTLRateQuotePage.clickOnSubmit();
                // *LTL Rate Request* page re-display with the following message:
                // *Attention: Please contact the Estes Solution Center at 1-800-645-3952,
                // or your local servicing terminal to confirm driver availability for all
                // pickups required after 3 p.m. local time.*
                eNetLTLRateQuotePage.ValidateAttentionMessage(
                        "Attention: Please contact the Estes Solution Center at 1-800-645-3952, "
                                + "or your local servicing terminal to confirm driver availability for all pickups required after 3 p.m. local time.");
                testUtil.setHardWait(40000);

                // Step#10 : From *eNet* home page top navigation menu, click *Log Out*
                eNetHomePage.clickOnLogout();

                // Step#11 : Confirm by clicking the *Logout* button
                eNetHomePage.clickOnLogOutButton();
            } else {
                System.out.println("3PM disclaimer.");
            }
        } else {
            System.out.println("3PM disclaimer.");
        }

    }

    @Test(enabled = true, priority = 11)
    public void executeQZ_0() {

        testUtil.init(this);
        
        
//        testUtil.runScript("QZ_11439.py");
        
//        testUtil.writeString("Hello World");
        
        

//        testUtil.printIdentifiers("frmLogin");
        
//        testUtil.pySendKeys("frmLogin", "Edit2", "891984");
//        testUtil.pySendKeys("frmLogin", "Edit", "01008");
//
        String text = testUtil.pyGetText("frmLogin", "Login");
        System.out.println(text);
//
//        testUtil.clickElementPython("frmLogin", "Reset");

    }

}
