package shipmentManifestTests;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.jagacy.util.JagacyException;

import as400TempletTests.TempletTests;
import freightBillPages.FreightBillUpdateMainScreen;
import freightBillPages.FreightBillUpdateScreen1;
import freightBillPages.FreightBillUpdateScreen2;
import freightBillPages.FreightBillUpdateScreen3;
import freightBillPages.FreightBillingMenu;
import freightBillPages.LTL38MasterMenu;
import freightBillPages.Login;
import freightBillPages.ReserveFreightBills;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import rateQuotePages.MyEstesWelcomePage;
import shipmentManifestPages.ShipmentManifestPage;
import testBase.TestBase;
import util.Sleep;

public class ShipmentManifestRegressionTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	ShipmentManifestPage shipmentManifestPage = new ShipmentManifestPage();
	Login login = new Login();
	LTL38MasterMenu ltl38MasterMenu = new LTL38MasterMenu();
	FreightBillingMenu freightBillingMenu = new FreightBillingMenu();
	FreightBillUpdateMainScreen freightBillUpdateMainScreen = new FreightBillUpdateMainScreen();
	FreightBillUpdateScreen1 freightBillUpdateScreen1 = new FreightBillUpdateScreen1();
	FreightBillUpdateScreen2 freightBillUpdateScreen2 = new FreightBillUpdateScreen2();
	FreightBillUpdateScreen3 freightBillUpdateScreen3 = new FreightBillUpdateScreen3();
	ReserveFreightBills reserveFreightBills=new ReserveFreightBills();
	TempletTests templetTests = new TempletTests();
	
	/******************************* TESTS *******************************/
	
	
    /**This is AS400 test that uses Jagacy**/
	
	//Moved from MyEstesAS400RegressionTests class
	
	/*Shipment Manifest - Group Account - Verify the user can view Inbound shipments searched by Pickup Date*/
	

	@Test(enabled = true, priority = 1)


	public void executeQZ_7692()
			throws JagacyException, Exception, InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName("test91");
		myEstesLoginPage.enterPassword("test91");
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnShipmentManifest();
		testUtil.setHardWait(3000);

		shipmentManifestPage.clickOnAccountSearchLink();
		String AccNum = shipmentManifestPage.getAccNumForFirstRow();
		shipmentManifestPage.clickOnFirstAccountNumber();

		// get the terminal number and shippercode from the query to reserve the FB
		ArrayList<ArrayList<String>> sqlResults = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcls = 'R' and cmstat = 'A' and cmtid = '1'");

		String shipperCode = sqlResults.get(9).get(0);
		System.out.println("ShipperCode is : " + shipperCode);
		String ter = sqlResults.get(9).get(6);
		String ter1 = ("000" + ter).substring(ter.length());
		System.out.println("Terminal number is : " + ter1);
		testUtil.setHardWait(1000);
		templetTests.executeQZ_384(ter1, "1", shipperCode, "001", AccNum, "", "");
	
		testUtil.setHardWait(2000);

		shipmentManifestPage.selectShipmentType();
		shipmentManifestPage.selectInbound();

		shipmentManifestPage.clickOnSubmitButton();
		testUtil.setHardWait(4000);
		shipmentManifestPage.verifyHeaderPRONumber();
		shipmentManifestPage.verifyHeaderPickupDate();
		shipmentManifestPage.verifyHeaderDeliveryDate();
		shipmentManifestPage.verifyHeaderBOLNumber();
		shipmentManifestPage.verifyHeaderPONumber();
		shipmentManifestPage.verifyHeaderStatus();

		shipmentManifestPage.verifyShimentDetailsResult();
		shipmentManifestPage.clickOnCaret();
		shipmentManifestPage.verifyShimentInformationResult();

	}

	

}
