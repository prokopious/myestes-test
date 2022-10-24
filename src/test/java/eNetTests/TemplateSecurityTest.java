package eNetTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import eNetPages.ENetApplicationsPage;
import eNetPages.ENetHomePage;
import eNetPages.ENetLoginPage;
import eNetPages.ENetMyEstesManagementToolPage;
import myEstesPages.MyEstesHomePage;
import myEstesPages.MyEstesLoginPage;
import testBase.TestBase;
import util.SQLDataList;

public class TemplateSecurityTest extends TestBase {
	
	private final Logger logger= Logger.getLogger(TemplateSecurityTest.class);
	
	/***********************Instantiated Class********************************/
	ENetLoginPage eNetLoginPage = new ENetLoginPage();
	ENetHomePage eNetHomePage = new ENetHomePage(); 
	ENetApplicationsPage eNetApplicationsPage = new ENetApplicationsPage(); 
	ENetMyEstesManagementToolPage eNetMyEstesManagementToolPage = new ENetMyEstesManagementToolPage(); 
	SQLDataList sqlDataList = new SQLDataList(); 
	
	/*************************************************************************/
	/*
	 * Blocking MyEstes User from specific application in Security Table
	 * @throws Exception
	 */
	@Test(enabled=true, priority=1)
	public void executeQZ_12662() throws Exception {
		/*
		 * Step 1: *Procedure 1*
		 * 	Access the following test case to get the latest eNet url:
		 * 	https://estesexpress.atlassian.net/browse/QZ-10412
		 * 	*Note*: If test user doesn't have required access permissions to eNet they won't be able to perform eNet steps. In this situation, I recommend to submit a service ticket to get required access permissions. 
		 * 	Otherwise, just proceed to *Step 22* for *Procedure 2*
		 * Step 2: From the above test case, capture the eNet URL
		 * Step 3: From a browser like Chrome, access the above captured eNet URL
		 */
		driver.get(url2);
		
		/*
		 * Step 4: Step 4: From the *eNet login* page, enter the following fields with valid credentials:
		 * 	User ID: qaenet02 
		 * 	Password: qaenet02 
		 *  Step 5: Click *Login*
		 */
		eNetLoginPage.enterUserID(username12);
		eNetLoginPage.enterUserPassword(password12);
		eNetLoginPage.clickOnLoginButton(); 
		
		/*
		 * Step 6: From the *eNet Landing Page*, 
		 * Click <Applications> tab
		 */
		eNetHomePage.clickOnApplicationsTab(); 
		
		/*
		 * Step 7: From the *Applications* tab, navigate to *Customer Service* menu header then *My Estes Management Tool*
		 * *Note*: If test user doesn't have required access permissions they won't see the above link on applications page. In this situation, 
		 * I recommend you to submit a service ticket to get required access permissions. Otherwise, just proceed to *Step 22* for *Procedure 2* steps
		 * Step 8: Click *My Estes Management Tool* link
		 */
		eNetApplicationsPage.clickOnMyEstesManagementToolLink(); 
		
		/*
		 * Step 9: From the *My Estes Management Tool* page, enter the identified MyEstes username in the following field:
		 *  User ID:
		 * Step 10: Click *Search*
		 * 	The *My Estes Management Tool* page reloads with Approved & Non-Approved MyEstes Profiles sections
		 * Step 11: From the *My Estes Management Tool* page, the above searched User Name will be displayed either under *Approved MyEstes Profiles* or *Non-Approved MyEstes Profiles*
		 */
		
		String query = "SELECT A.QSACT#,A.QSUN USERNAME, decrypt_char(a.qspw,'TEAMESTES') PASSWRD FROM ESTESRTGY2.QNP230 A WHERE A.QSUN != 'SMOKENAT' AND A.QSACT# = '9991193'"; 
		Map<String, Object> map = sqlDataList.getValidUser(query); 
		String username1 =(String) map.get((String)"userName"); 
		String password1 =(String) map.get((String)"password"); 

		eNetMyEstesManagementToolPage.enterUserName(username1);
		eNetMyEstesManagementToolPage.clickOnSearch(); 
		
		/*
		 * Step 12: Click *UserName* link
		 * *My Estes User Detail* pop-up window will be displayed
		 */
		testUtil.setHardWait(5000);
		eNetMyEstesManagementToolPage.selectUserName(username1);
		
		/*
		 * Step 13: From the *My Estes User Detail* pop-up window, page down till you see *Edit Blocked Applications* link
		 * Step 14: Click *Edit Blocked Applications* link
		 */
		eNetMyEstesManagementToolPage.clickEditBlockApplications(); 
		
		/*
		 * Step 15: From the *My Estes Blocked Applications* page, Identify the application name under *Authorized Applications* section which needs to be blocked for test user.
		 * *Note*: If you don't find the application name under "Authorized Applications" table, then it should be under "Blocked Applications". That means the user is already Blocked from the application, You can stop here and No further actions required.  Complete the Logout steps from step 19.
		 * Step 16: Click *Application Name*
		 * Step 17: Click *Yellow Right direction Arrow*
		 */
		eNetMyEstesManagementToolPage.selectApplication("Image API V1"); 
		
		/*
		 * Step 18: Click Submit
		 */
		eNetMyEstesManagementToolPage.clickSubmitButton(); 
		
		/*
		 * Step 19: From the *My Estes User Detail /  Blocked Applications* window, Click *Red X Cross* button
		 * Step 20: From the My Estes Management Tool page, click Log Out. 
		 * Step 21: From the Logout page, Click *Logout* button
		 * Note: Good-Bye page will be displayed with *Your session was successfully logged off. Thank you.*
		 */
		eNetMyEstesManagementToolPage.clickRedXButton(); 
		eNetMyEstesManagementToolPage.clickLogoutButton(); 
		eNetMyEstesManagementToolPage.confirmationLogoutClick(); 
		
		/*
		 * Step 22-34 Per Bala's direction, we can ignore these steps. 
		 */
	}
}
