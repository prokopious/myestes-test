package ediTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;

import jagacy.util.JagacyUtil;
import jagacyVT.screens.CommandEntryScreen;
import jagacyVT.screens.JobScheduleListScreen;
import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.RobotAutomatedOperationsSolutionScreen;
import jagacyVT.screens.RobotMainMenuScreen;
import jagacyVT.screens.WorkWithActiveJobsScreen;
import myEstesPages.MyEstesAddressBookHomePage;
import myEstesPages.MyEstesDigitalServicesPage;
import myEstesPages.MyEstesEDIFormTransmissionRequestPage;
import myEstesPages.MyEstesEDIPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import myEstesPages.MyEstesSignUpPage;
import myEstesPages.MyEstesWelcomePage;
import testBase.TestBase;
import util.ESBUtil;
import util.SQLDataList;

public class MyEstesEDIRegressionTest extends TestBase {
	
	private Logger logger=LogManager.getLogger(MyEstesEDIRegressionTest.class);
	
	MyEstesSignUpPage myEstesSignUpPage = new MyEstesSignUpPage();
	MyEstesHomePage myEstesHomePage = new MyEstesHomePage();
	MyEstesAddressBookHomePage myEstesAddressBookHomePage = new MyEstesAddressBookHomePage();
	MyEstesEDIPage myEstesEDIPage = new MyEstesEDIPage();
	MyEstesEDIFormTransmissionRequestPage myEstesEDIFormTransRequestPage = new MyEstesEDIFormTransmissionRequestPage();
	MyEstesWelcomePage myEstesWelcomePage = new MyEstesWelcomePage();
	MyEstesLoginPage myEstesLoginPage = new MyEstesLoginPage();
	MyEstesDigitalServicesPage myEstesDigitalServicesPage = new MyEstesDigitalServicesPage();

	/******************************* TESTS *******************************/

	/**
	 * Verify 820 file is processed by the ESB and data is loaded into the staging
	 * and RAP tables
	 * 
	 * @author coxda
	 * @throws JagacyException
	 * @throws InterruptedException
	 */
	@Test(enabled=true,priority= 1)
	public void executeQZ_9314()
			throws JagacyException, InterruptedException {
		// Note: Some data is edited from the steps to accurately reflect the sample 820
		// file attached on step 5

		// Step 1: Verify customer setup in QA
		SQLDataList sql = new SQLDataList();
		List<List<String>> result = sql.viewQueryResults("SELECT * FROM FBFILES.CCI00P900");

		List<String> firstRow = result.get(0);
		assertTrue(firstRow.get(0).trim().equals("156471773"));
		assertTrue(firstRow.get(2).trim().equals("Deere Worldwide logistics"));

		List<String> secondRow = result.get(1);
		assertTrue(secondRow.get(0).trim().equals("8003211115A"));
		assertTrue(secondRow.get(2).trim().equals("ACME Manufacturing Co"));

		List<String> thirdRow = result.get(2);
		assertTrue(thirdRow.get(0).trim().equals("006132294L"));
		assertTrue(thirdRow.get(2).trim().equals("Little Rapids Corporation"));

		// Step 2: Verify job is running
		SessionVt session = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		JagacyUtil jagacyUtil = new JagacyUtil(session);
		session.open();
		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.logon(username16, password16);
		if (session.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil.pressEnter();
		CommandEntryScreen commandEntryScreen = new CommandEntryScreen(session);
		commandEntryScreen.enterCommand("WRKACTJOB");

		WorkWithActiveJobsScreen workWithActiveJobsScreen = new WorkWithActiveJobsScreen(session);
		workWithActiveJobsScreen.verifyJobIsRunningWithStatus("EDI10C025", "DLYW");
		session.abort();

		// Step 3 - 4: Verify EDP001
		if (sql.viewQueryResults(
				"SELECT EVCUST, EVSET FROM FBFILES.EDP001 WHERE EVCUST IN ('5025424','8664159') AND EVSET='821'")
				.size() > 0) {
			sql.updateQuery(
					"UPDATE FBFILES.EDP001 SET EVSET='820' WHERE EVCUST IN ('5025424','8664159') AND EVSET='821'");
		}

		// Step 5: Process the 820 file
		ESBUtil esbUtil = new ESBUtil();
		try {
			esbUtil.placeThe820TestFileOnTheESBFolderForProcessing();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Step 6 - 8: Verify the CCI820C100 job is on hold, otherwise release it
		SessionVt session2 = new SessionVt("myJagacyVT", "exlaqa", "dec-vt220");
		JagacyUtil jagacyUtil2 = new JagacyUtil(session2);
		session2.open();
		LoginScreen loginScreen2 = new LoginScreen(session2);
		loginScreen2.logon(username16, password16);
		if (session2.readPosition(0, 27, 7).toString().trim().equals("Display"))
			jagacyUtil2.pressEnter();
		CommandEntryScreen commandEntryScreen2 = new CommandEntryScreen(session2);
		commandEntryScreen2.enterCommand("RBO");
		RobotAutomatedOperationsSolutionScreen robotScreen1 = new RobotAutomatedOperationsSolutionScreen(session2);
		robotScreen1.verifyScreenTitle();
		robotScreen1.enterOption("1");
		RobotMainMenuScreen robotScreen2 = new RobotMainMenuScreen(session2);
		robotScreen2.verifyScreenTitle();
		robotScreen2.enterOption("1");
		JobScheduleListScreen jobScheduleListScreen = new JobScheduleListScreen(session2);
		jobScheduleListScreen.verifyScreenTitle();
		jobScheduleListScreen.enterVerifyAndReleaseJob("CCI820C100");

		// Step 9: Verify RAP004 record displays
		sql.viewQueryResults("SELECT * FROM FBFILES.RAP004 WHERE CRHUD8='20160831' ORDER BY CRHBDS DESC", 200);
		// single record may not display

		// Step 10: Acquire the CCIID to be used across staging tables
		List<List<String>> table = sql
				.viewQueryResults("SELECT * FROM FBFILES.CCI10P820H ORDER BY UPDATETIMESTAMP DESC");
		logger.info("Check 1 : "+table.get(0).get(3).trim());
//		assertTrue(table.get(0).get(3).trim().equals("156471773"));
		assertTrue(table.get(0).get(3).trim().equals("8003211115A"));
		logger.info("Check 2 : "+table.get(0).get(2).trim());
//		assertTrue(table.get(0).get(2).trim().equals("1663"));
		assertTrue(table.get(0).get(2).trim().equals("651"));
		String cciid = table.get(0).get(0).trim();
		logger.info("CCIID found: [" + cciid + "]");

		// Step 11: Verify the appropriate check header data is loaded from the 820 file
		List<List<String>> table2 = sql
				.viewQueryResults("SELECT * FROM FBFILES.CCI10P820C ORDER BY UPDATETIMESTAMP DESC");
		assertTrue(table2.get(0).get(0).trim().equals(cciid));
		// REF line
		logger.info("Check 3 :"+table2.get(0).get(1).trim());
		assertTrue(table2.get(0).get(1).trim().equals("TN"));
		logger.info("Check 4 :"+table2.get(0).get(2).trim());
		assertTrue(table2.get(0).get(2).trim().equals("102855"));
		// BPR/RMR line
		logger.info("Check 5 :"+table2.get(0).get(4).trim());
		assertTrue(table2.get(0).get(4).trim().equals("252.46"));
		// N1/NM1 line
		logger.info("Check 6 :"+table2.get(0).get(5).trim());
		assertTrue(table2.get(0).get(5).trim().equals("ACME MANUFACTURING, CO."));

		// Step 12: Verify payment data
		List<List<String>> table3 = sql
				.viewQueryResults("SELECT * FROM FBFILES.CCI10P820P WHERE CCIID='" + cciid + "'");
		assertTrue(table3.get(0).get(4).equals("252.46"));

		// Step 13: Verify PRO data is added in RAP004
		List<List<String>> table4 = sql
				.viewQueryResults("SELECT * FROM FBFILES.RAP004 WHERE CRHBAR='252.46' ORDER BY CRHBN DESC", 5);
		assertTrue(table4.get(0).get(3).contains("ACME MANUFACTURING, CO"));
		String proNumber = table4.get(0).get(0);
		logger.info("PRO Number: [" + proNumber + "]");

		// Step 14: Verify PRO data is added in RAP005
		assertTrue(sql.viewQueryResults("SELECT * FROM FBFILES.RAP005 WHERE CRDBN='" + proNumber + "'").size() > 0);

		// Step 15: Put job CCI820C100 back on hold
		jobScheduleListScreen.exit();
		robotScreen2.verifyScreenTitle();
		robotScreen2.enterOption("1");
		jobScheduleListScreen.enterVerifyAndHoldJob("CCI820C100");
	}



	
}
