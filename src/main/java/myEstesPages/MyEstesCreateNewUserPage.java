package myEstesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.log4testng.Logger;

import testBase.TestBase;

public class MyEstesCreateNewUserPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesCreateNewUserPage.class);
	
	//First Name 
	@FindBy(how = How.ID, using = "first_name")
	private WebElement weCNUFirstName;	
	//Last Name 
	@FindBy(how = How.ID, using = "last_name")
	private WebElement weCNULastName;
	//E-mail Address 
	@FindBy(how = How.ID, using = "email")
	private WebElement weCNUEmail;
	//Area Code 	
	@FindBy(how = How.ID, using = "area_code")
	private WebElement weCNUPAreaCode;
	//Exchange	
	@FindBy(how = How.ID, using = "exchange")
	private WebElement weCNUPExNum;
	//Phone Last Four Digit
	@FindBy(how = How.ID, using = "last_four")
	private WebElement weCNUPL4Digit;
	//Username (between 5 and 10 characters) 
	@FindBy(how = How.ID, using = "user_name_req")
	private WebElement weCNUserName;
	//Password (between 5 and 10 characters) 
	@FindBy(how = How.ID, using = "password_req")
	private WebElement weCNUserPwd;
	//Confirm Password
	@FindBy(how = How.ID, using = "pw_confirm")
	private WebElement weCNUserConfPwd;
	//Submit
	@FindBy(how = How.XPATH, using = "//*[@id='col-2aa']/form/table/tbody/tr[11]/td/input")
	private WebElement weCNUSubmitButton;
	
	///////
	
	//Enter Create New User First Name
	public void enterCNUfirstName(String CNUFName) {
		testUtil.init(this);
		logger.info("Enter Create New User First Name");
		weCNUFirstName.sendKeys(CNUFName);
	}
	
		//Last Name 
	public void enterCNULastName(String CNULName) {
		testUtil.init(this);
		logger.info("Enter Create New User Last Name");
		weCNULastName.sendKeys(CNULName);
	}		
		//E-mail Address 
	public void enterCNUEmail(String CNUEmail) {
		testUtil.init(this);
		logger.info("Enter Create New User Email");
		weCNUEmail.sendKeys(CNUEmail);
	}		
		//Area Code	
	public void enterCNUPAreaCode(String CNUPAreaCode) {
		testUtil.init(this);
		logger.info("Enter Create New User Phone Area Code");
		weCNUPAreaCode.sendKeys(CNUPAreaCode);
	}	
	//Exchange	
		public void enterCNUPExNum(String CNUPExNum) {
			testUtil.init(this);
			logger.info("Enter Create New User Phone Exchange Number");
			weCNUPExNum.sendKeys(CNUPExNum);
		}	
		//Phone Last Four Digit	
		public void enterCNUPL4Digit(String CNUPL4Num) {
			testUtil.init(this);
			logger.info("Enter Create New User Phone Last Four Digit");
			weCNUPL4Digit.sendKeys(CNUPL4Num);
		}	
		//Username (between 5 and 10 characters) 
		public void enterCNUserName(String CNUserName) {
			testUtil.init(this);
			logger.info("Enter Create New User Name");
			weCNUserName.sendKeys(CNUserName);
		}
		//Password (between 5 and 10 characters) 
		public void enterCNUserPwd(String CNUserPwd) {
			testUtil.init(this);
			logger.info("Enter Create New User Password");
			weCNUserPwd.sendKeys(CNUserPwd);
		}
		//Confirm Password
		public void enterCNUserConfPwd(String CNUserConfPwd) {
			testUtil.init(this);
			logger.info("Enter Create New User Confirm Password");
			weCNUserConfPwd.sendKeys(CNUserConfPwd);
		}
		//Submit
		// Click on Create New User
		public void clickOnCNUSubmitButton() {
			testUtil.init(this);
			logger.info("Click On Create New User Submit Button");
			weCNUSubmitButton.click();
		}
		
	

}
