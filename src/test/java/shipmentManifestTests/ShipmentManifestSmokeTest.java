package shipmentManifestTests;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
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
import util.FreightBill;
import util.Keyboard;
import util.Shortcuts;
import util.Sleep;

public class ShipmentManifestSmokeTest extends TestBase {

	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	ShipmentManifestPage shipmentManifestPage = new ShipmentManifestPage();
	TempletTests templetTests = new TempletTests();

	
	/**************************************************TEST**********************************************/
	
	  /**This is test is flaky on Jenkins- should run locally**/
	
	//Moved from MyEstesAS400SmokeTests class
	
	@Test(enabled = true, priority = 1)

	public void executeQZ_7697()
			throws JagacyException, Exception, InterruptedException {

		// get the terminal number and shippercode from the query to reserve the FB
		ArrayList<ArrayList<String>> sqlResults = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcls = 'R' and cmstat = 'A' and cmtid = '1'");

		String shipperCode = sqlResults.get(5).get(0);
		System.out.println("ShipperCode is : " + shipperCode);
		String ter = sqlResults.get(5).get(6);
		String ter1 = ("000" + ter).substring(ter.length());
		System.out.println("Terminal number is : " + ter1);

		// get the terminal number and consignee from the query to update the FB
		ArrayList<ArrayList<String>> sqlResults2 = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcls = 'R' and cmstat = 'A' and cmtid = '1'");

		String consignee = sqlResults2.get(3).get(0);
		System.out.println("Consignee is : " + consignee);
		String terNum = sqlResults2.get(3).get(6);
		String terNum1 = ("000" + terNum).substring(terNum.length());
		System.out.println("Terminal number is : " + terNum1);
		testUtil.setHardWait(4000);

		templetTests.executeQZ_384(ter1, "1", shipperCode, terNum1, consignee, "", "7178618");

		// Required data is generated.

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username2);
		myEstesLoginPage.enterPassword(password2);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnShipmentManifest();
		shipmentManifestPage.selectShipmentType();
		shipmentManifestPage.selectThirdParty();
		shipmentManifestPage.clickOnSubmitButton();

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

	  /**  /**This is test is flaky on Jenkins- should run locally**/
	
	//Moved from MyEstesAS400SmokeTests class
	
	@Test(enabled = true, priority = 2)

	public void executeQZ_6087()
			throws JagacyException, Exception, InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username1);
		myEstesLoginPage.enterPassword(username1);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnShipmentManifest();
		testUtil.setHardWait(3000);

		shipmentManifestPage.clickOnAccountSearchLink();
		String AccNum = shipmentManifestPage.getAccNumForFirstRow();
		shipmentManifestPage.clickOnFirstAccountNumber();

		// get the terminal number and shippercode from the query to reserve the FB

		ArrayList<ArrayList<String>> query1 = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcust = '"
						+ AccNum + "'");
		String ter = query1.get(0).get(6);
		String ter1 = ("000" + ter).substring(ter.length());
		System.out.println("Terminal number is : " + ter1);

		// get the terminal number and consignee from the query to update the FB
		ArrayList<ArrayList<String>> sqlResults2 = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcls = 'R' and cmstat = 'A' and cmtid = '1'");

		String consignee = sqlResults2.get(4).get(0);
		System.out.println("Consignee is : " + consignee);
		String terNum = sqlResults2.get(4).get(6);
		String terNum1 = ("000" + terNum).substring(terNum.length());
		System.out.println("Terminal number is : " + terNum1);

		testUtil.setHardWait(2000);
		templetTests.executeQZ_384(ter1, "1", AccNum, terNum1, consignee, "", "");

		testUtil.setHardWait(2000);

		shipmentManifestPage.selectShipmentType();
		testUtil.setHardWait(1000);
		shipmentManifestPage.selectOutbound();

		shipmentManifestPage.clickOnSubmitButton();

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
	
	 
	/**This is AS400 test that uses Jagacy**/
	
	//Moved from MyEstesAS400SmokeTests class
	
	@Test(enabled = true, priority = 3) //Disabled this test due to the blocker

	public void executeQZ_7696()
			throws JagacyException, Exception, InterruptedException {

		myEstesHomePage.clickOnMyEstes();
		myEstesHomePage.clickOnLogin();
		myEstesLoginPage.enterUserName(username3);
		myEstesLoginPage.enterPassword(username3);
		myEstesLoginPage.clickOnLoginButton();
		myEstesWelcomePage.clickOnManageLink();
		myEstesWelcomePage.clickOnShipmentManifest();
		testUtil.setHardWait(3000);

		shipmentManifestPage.clickOnAccountSearchLink();
		String AccNum = shipmentManifestPage.getAccNumForSecondRow();
		shipmentManifestPage.clickOnSecondAccountNumber();

		// get the terminal number and shippercode from the query to reserve the FB

		ArrayList<ArrayList<String>> query1 = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcust = '"
						+ AccNum + "'");
		String ter = query1.get(0).get(6);
		String ter1 = ("000" + ter).substring(ter.length());
		System.out.println("Terminal number is : " + ter1);

		// get the terminal number and consignee from the query to update the FB
		ArrayList<ArrayList<String>> sqlResults2 = testUtil.getDataByRow("exlaqa",
				"select cmcust, cmname, cmadr1, cmcity, cmst, cmzip, cmtid from fbfiles.rap001 where cmcls = 'R' and cmstat = 'A' and cmtid = '1'");

		String consignee = sqlResults2.get(4).get(0);
		System.out.println("Consignee is : " + consignee);
		String terNum = sqlResults2.get(4).get(6);
		String terNum1 = ("000" + terNum).substring(terNum.length());
		System.out.println("Terminal number is : " + terNum1);

		testUtil.setHardWait(2000);
		String fbNum =templetTests.executeQZ_384(ter1, "1", AccNum, terNum1, consignee, "", "");

		testUtil.setHardWait(2000);
		
//		String fbNum ="0388028656";
//		String terNum1 = "038";
		templetTests.executeQZ_2661(fbNum, terNum1);
		
		//testUtil.cityDeliveryManifest(pro, ter);

		shipmentManifestPage.selectShipmentType();
		testUtil.setHardWait(1000);
		shipmentManifestPage.selectOutbound();

		shipmentManifestPage.clickOnSubmitButton();

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
