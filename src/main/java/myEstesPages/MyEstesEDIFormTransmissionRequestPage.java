
package myEstesPages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesEDIFormTransmissionRequestPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesEDIPage.class);

	// Company Name is a required entry field.
	@FindBy(how = How.ID, using = "mat-input-0")
	private WebElement weEDICompNameRField;
	// Company Address is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[2]")
	private WebElement weEDIAddressRField;
	// Company City is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[3]")
	private WebElement weEDICityRField;
	// Company State is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[5]")
	private WebElement weEDIStateRField;
	// Company ZIP Code is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[4]")
	private WebElement weEDIZipRField;
	// Company Phone is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[6]")
	private WebElement weEDICompPhoneRField;
	// Primary EDI Name is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[12]")
	private WebElement wePriEDINameRField;
	// Primary EDI Phone is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[15]")
	private WebElement wePriEDIPhoneRField;
	// Primary EDI E-mail is a required entry field
	@FindBy(how = How.XPATH, using = "(//*[@id=\"print-section\"]//div[@class='mat-form-field-flex'])[14]")
	private WebElement wePriEDIEmailRField;

	// Company Name
	@FindBy(how = How.ID, using = "mat-input-0")
	private WebElement weCoName;
	// Company Address
	@FindBy(how = How.ID, using = "mat-input-1")
	private WebElement weCoAddress;
	// Company City
	@FindBy(how = How.ID, using = "mat-input-2")
	private WebElement weCCity;
	// Company State
	@FindBy(how = How.ID, using = "mat-input-3")
	private WebElement weCState;
	// Company ZIP Code
	@FindBy(how = How.ID, using = "mat-input-4")
	private WebElement weCZCode;
	// Company Phone Area Code
	@FindBy(how = How.ID, using = "customerPhoneAreaCode")
	private WebElement weCPACode;
	// Company Phone Exchange
	@FindBy(how = How.ID, using = "customerPhoneExchange")
	private WebElement weCPExchange;
	// Company Phone Number
	@FindBy(how = How.ID, using = "mat-input-5")
	private WebElement weCPNumber;

	// **** Primary EDI Name ***
	@FindBy(how = How.ID, using = "mat-input-11")
	private WebElement wePrEDIName;
	// Primary EDI Phone Area Code
	@FindBy(how = How.ID, using = "prcPhoneAreaCode")
	private WebElement wePrEDIPACode;
	// Primary EDI Phone Exchange
	@FindBy(how = How.ID, using = "prcPhoneExchange")
	private WebElement wePrEDIPExchange;
	// Primary EDI Phone Number
	@FindBy(how = How.ID, using = "mat-input-14")
	private WebElement wePrEDIPNumber;
	// Primary EDI Email Address
	@FindBy(how = How.ID, using = "mat-input-13")
	private WebElement wePrEDIPEmail;

	// ******* EDI SUBMIT Button ***
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement weEDISubmit;
	// EDI REFERENCE Confirmation Message
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/font/b")
	private WebElement weEDIRConfMessage;

	// ** Secondary EDI Contact *****

	// Checked Mark on Same as Primary Contact(Secondary EDI Contact
	@FindBy(how = How.XPATH, using = "//mat-checkbox[@id='mat-checkbox-12']")
	private WebElement weCheckedPriCont;
	// Secondary EDI Contact Name
	@FindBy(how = How.ID, using = "mat-input-37")
	private WebElement weSecEDIContName;
	// Secondary EDI Contact Email
	@FindBy(how = How.ID, using = "mat-input-39")
	private WebElement weSecEDIContEmail;

	// ** Account Payable Contact *****
	// Expand Accounts Payable Contact
	@FindBy(how = How.XPATH, using = "//*[@id=\"print-section\"]/form/mat-card/div[2]/mat-card-header/i")
	private WebElement weAccPayableContExpand;
	// Checked Mark on Same as Primary Contact (Account Payable)
	@FindBy(how = How.XPATH, using = "//mat-checkbox[@id='mat-checkbox-13']")
	private WebElement weCheckedAccPay;
	// Account Payable Contact Name
	@FindBy(how = How.ID, using = "mat-input-43")
	private WebElement weAccPayContName;
	// Account Payable Contact Email
	@FindBy(how = How.ID, using = "mat-input-45")
	private WebElement weAccPayContEmail;

	// ** Business Contact *****
	// Expand Business Contact
	@FindBy(how = How.ID, using = "//*[@id=\"print-section\"]/form/mat-card/div[3]/mat-card-header/i")
	private WebElement weBusinessContExpand;
	// Checked Mark on Same as Primary Contact (Business Contact)
	@FindBy(how = How.XPATH, using = "(//*[@id='psci'])[3]")
	private WebElement weCheckedBusinessCont;
	// Business Contact Name
	@FindBy(how = How.ID, using = "bcName")
	private WebElement weBusinessContName;
	// Business Contact Email
	@FindBy(how = How.ID, using = "bcEmail")
	private WebElement weBusinessContEmail;

	// ** Traffic Contact *****
	// Expand Traffic Contact
	@FindBy(how = How.ID, using = "expandCollapseImg3")
	private WebElement weTrafficContExpand;
	// Checked Mark on Same as Primary Contact (Business Contact)
	@FindBy(how = How.XPATH, using = "(//*[@id='psci'])[4]")
	private WebElement weCheckedTrafficCont;
	// Business Contact Name
	@FindBy(how = How.ID, using = "tcName")
	private WebElement weTrafficContName;
	// Business Contact Email
	@FindBy(how = How.ID, using = "tcEmail")
	private WebElement weTrafficContEmail;

	// ** Additional Contact *****
	// Expand Additional Contact
	@FindBy(how = How.ID, using = "expandCollapseImg4")
	private WebElement weAdditionalContExpand;
	// Checked Mark on Same as Primary Contact (Additional Contact)
	@FindBy(how = How.XPATH, using = "(//*[@id='psci'])[5]")
	private WebElement weCheckedAdditionalCont;
	// Additional Contact Name
	@FindBy(how = How.ID, using = "acName")
	private WebElement weAdditionalContName;
	// Additional Contact Email
	@FindBy(how = How.ID, using = "acEmail")
	private WebElement weAdditionalContEmail;

	// ** Person Completing Form *****
	// Checked Mark on Same as Primary Contact (Person Completing Form)
	@FindBy(how = How.XPATH, using = "(//*[@id='psci'])[6]")
	private WebElement weCheckedPCForm;
	// Person Completing Form Name
	@FindBy(how = How.ID, using = "fillersName")
	private WebElement wePCFormName;

	// ** Check Mark on E-mail Address *****
	// Click On Select to send a copy of this request to your e-mail address
	// CHECKBOX
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Select to send a copy of this request to your emai')]")
	private WebElement weCheckedEmailAddress;

	// Enter your e-mail address at the BOTTOM of the Page
	@FindBy(how = How.XPATH, using = "//input[@id='mat-input-69']")
	private WebElement weEnterEmailAddress;

	// Estes Account Number
	@FindBy(xpath = "//a[contains(text(),'Get Account Number')]")
	private WebElement getAccountNumberLink;

	/****************************** METHODS ***************************************/
	//// Verify Company Name is a required entry field
	public void verifyCNameIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Company Name is a required entry field");
		String Expected_CNameMessage = "This field is required.";
		String CNameMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-0']")).getText();
		System.out.println(CNameMessage);
		assertEquals(CNameMessage, Expected_CNameMessage);
	}

	// Verify Company Address is a required entry field
	public void verifyCAddressIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Company Address is a required entry field");
		String Expected_CAddressMessage = "This field is required.";
		String CAddressMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-1']")).getText();
		System.out.println(CAddressMessage);
		assertEquals(CAddressMessage, Expected_CAddressMessage);
	}

	// Verify Company City is a required entry field
	public void verifyCCityIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Company City is a required entry field");
		String Expected_CCityMessage = "This field is required.";
		String CCityMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-2']")).getText();
		System.out.println(CCityMessage);
		assertEquals(CCityMessage, Expected_CCityMessage);
	}

	// Verify Company State is a required entry field
	public void verifyCStateIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Company State is a required entry field");
		String Expected_CStateMessage = "This field is required.";
		String CStateMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-3']")).getText();
		System.out.println(CStateMessage);
		assertEquals(CStateMessage, Expected_CStateMessage);
	}

	// Verify Company ZIP Code is a required entry field
	public void verifyCZCodeIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Company ZIP Code is a required entry field");
		String Expected_CZCodeMessage = "This field is required.";
		String CZCodeMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-4']")).getText();
		System.out.println(CZCodeMessage);
		assertEquals(CZCodeMessage, Expected_CZCodeMessage);
	}

	// Verify Company Phone is a required entry field
	public void verifyCPhoneIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Company Phone is a required entry field");
		String Expected_CPhoneMessage = "This field is required.";
		String CPhoneMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-5']")).getText();
		System.out.println(CPhoneMessage);
		assertEquals(CPhoneMessage, Expected_CPhoneMessage);
	}

	// Verify Primary EDI Name is a required entry field
	public void verifyEDINameIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Primary EDI Name is a required entry field");
		String Expected_EDINameMessage = "This field is required.";
		String EDINameMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-6']")).getText();
		System.out.println(EDINameMessage);
		assertEquals(EDINameMessage, Expected_EDINameMessage);
	}

	// Verify Primary EDI Phone is a required entry field
	public void verifyEDIPhoneIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Primary EDI Phone is a required entry field");
		String Expected_EDIPhoneMessage = "This field is required.";
		String EDIPhoneMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-8']")).getText();
		System.out.println(EDIPhoneMessage);
		assertEquals(EDIPhoneMessage, Expected_EDIPhoneMessage);
	}

	// Verify Primary EDI Email is a required entry field
	public void verifyEDIEmailIsAReqEntryFeild() {
		testUtil.init(this);
		logger.info("Verify Primary EDI Email is a required entry field");
		String Expected_EDIEmailMessage = "This field is required.";
		String Actual_EDIEmailMessage = driver.findElement(By.xpath("//mat-error[@id='mat-error-7']")).getText();
		System.out.println(Actual_EDIEmailMessage);
		assertEquals(Actual_EDIEmailMessage, Expected_EDIEmailMessage);
	}

	//// Enter Company Name
	public void enterCoName(String CoName) {
		logger.info("Enter Company Name");
		testUtil.init(this);
		weCoName.sendKeys(CoName);
	}

	// Enter Company Address
	public void enterCoAddress(String CoAddress) {
		logger.info("Enter Company Address");
		testUtil.init(this);
		weCoAddress.sendKeys(CoAddress);
	}

	// Enter Company City
	public void enterCoCity(String CoCity) {
		logger.info("Enter Company City");
		testUtil.init(this);
		weCCity.sendKeys(CoCity);
	}

	// Enter Company State
	public void enterCoState(String CoState) {
		logger.info("Enter Company State");
		testUtil.init(this);
		weCState.sendKeys(CoState);
	}

	// Enter Company ZIP Code
	public void enterCoZCode(String CoZCode) {
		logger.info("Enter Company ZIP Code");
		testUtil.init(this);
		weCZCode.sendKeys(CoZCode);
	}

	// Enter Company Phone Area Code
	public void enterCoPACode(String CoPACode) {
		logger.info("Enter Company Phone Area Code");
		testUtil.init(this);
		weCPACode.sendKeys(CoPACode);
	}

	// Enter Company Phone Exchange
	public void enterCoPExhange(String CoPExchange) {
		logger.info("Enter Company Phone Exchange");
		testUtil.init(this);
		weCPExchange.sendKeys(CoPExchange);
	}

	// Enter Company Phone Number
	public void enterCoPNumber(String CoPNumber) {
		logger.info("Enter Company Phone Number");
		testUtil.init(this);
		weCPNumber.sendKeys(CoPNumber);
	}

	// Enter Primary EDI Customer Name
	public void enterPrEDIName(String PrEDIName) {
		logger.info("Enter Primary EDI Customer Name");
		testUtil.init(this);
		wePrEDIName.sendKeys(PrEDIName);
	}

	// Enter Primary EDI Customer Phone Area Code
	public void enterPrEDIPACode(String PrEDIName) {
		logger.info("Enter Primary EDI Customer Phone Area Code");
		testUtil.init(this);
		wePrEDIPACode.sendKeys(PrEDIName);
	}

	// Enter Primary EDI Customer Phone Exchange
	public void enterPrEDIPExchange(String PrEDEPExchange) {
		logger.info("Enter Primary EDI Customer Phone Exchange");
		testUtil.init(this);
		wePrEDIPExchange.sendKeys(PrEDEPExchange);
	}

	// Enter Primary EDI Customer Phone Number
	public void enterPrEDIPNumber(String PrEDIPNumber) {
		logger.info("Enter Primary EDI Customer Phone Number");
		testUtil.init(this);
		wePrEDIPNumber.sendKeys(PrEDIPNumber);
	}

	// Enter Primary EDI Customer Email Address
	public void enterPrEDIPEmail(String PrEDIPEmail) {
		logger.info("Enter Primary EDI Customer Email Address");
		testUtil.init(this);
		wePrEDIPEmail.sendKeys(PrEDIPEmail);
	}

	// Click On EDI Submit Button
	public void clickOnEDISubmit() {
		testUtil.init(this);
		logger.info("click on EDI Submit Button");
		weEDISubmit.click();
	}

	// Verify Reference Confirmation Message
	public String verifyRefConfMessage() {
		logger.info("Verify Reference Confirmation Message");
		testUtil.init(this);
		String RefConfMessage = weEDIRConfMessage.getText();
		return RefConfMessage;
	}

	// ** Secondary EDI Contact *****
	// Click On Same as Secondary EDI Contact CHECKBOX
	public void clickOnSecEDIConCBox() {
		testUtil.init(this);
		logger.info("Click On Same as Secondary EDI Contact CheckBox");
		weCheckedPriCont.click();
	}

	// Verify Secondary EDI Contact Name
	public String verifySecEDIConName() {
		testUtil.init(this);
		logger.info("Verify Secondary EDI Contact Name ");
		String SecEDIContactName = weSecEDIContName.getAttribute("value");
		System.out.println(SecEDIContactName);
		return SecEDIContactName;
	}

	// Verify Secondary EDI Contact Email
	public String verifySecEDIConEmail() {
		testUtil.init(this);
		logger.info("Verify Secondary EDI Contact Email ");
		String SecEDIContactEmail = weSecEDIContEmail.getAttribute("value");
		return SecEDIContactEmail;
	}

	// ** Account Payable Contact *****
	// Click [+] button for Accounts Payable Contact
	public void clickOnAccPayContExpandButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click [+] button for Accounts Payable Contact");
		weAccPayableContExpand.click();
	}

	// Click On Account Payable Secondary EDI Contact CHECKBOX
	public void clickOnAccPaySAPConCBox() {
		testUtil.init(this);
		logger.info("Click On Account Payable contact Same as Primary contact CHECKBOX");
		weCheckedAccPay.click();
	}

	// Verify Accounts Payable Contact Name
	public String verifyAccPayConName() {
		testUtil.init(this);
		logger.info("Verify Accounts Payable Contact Name");
		String AccPayContactName = weAccPayContName.getAttribute("value");
		return AccPayContactName;
	}

	// Verify Accounts Payable Contact Email
	public String verifyAccPayConEmail() {
		testUtil.init(this);
		logger.info("Verify Accounts Payable Contact Email");
		String AccPayContactEmail = weAccPayContEmail.getAttribute("value");
		return AccPayContactEmail;
	}

	// ** Business Contact *****
	// Click [+] button for Business Contact
	public void clickOnBusinessContExpandButton() {
		testUtil.init(this);
		logger.info("Click [+] button for Business Contact");
		Actions act = new Actions(driver);
		act.moveToElement(weBusinessContExpand).click().build().perform();
		// weBusinessContExpand.click();

	}

	// Click On Same as Primary Contact CHECKBOX For Business Contact
	public void clickOnBusinessSAPConCBox() {
		testUtil.init(this);
		logger.info("Click On Same as Primary Contact CHECKBOX For Business Contact");
		weCheckedBusinessCont.click();
	}

	// Verify Business Contact Name
	public String verifyBusinessConName() {
		testUtil.init(this);
		logger.info("Verify Business Contact Name");
		String BusinessContactName = weBusinessContName.getAttribute("value");
		return BusinessContactName;
	}

	// Verify Business Contact Email
	public String verifyBusinessConEmail() {
		testUtil.init(this);
		logger.info("Verify Business Contact Email");
		String BusinessContactEmail = weBusinessContEmail.getAttribute("value");
		return BusinessContactEmail;
	}

	// ** Traffic Contact *****
	// Click [+] button for Traffic Contact
	public void clickOnTrafficContExpandButton() {
		testUtil.init(this);
		logger.info("Click [+] button for Traffic Contact");
		weTrafficContExpand.click();
	}

	// Click On Same as Primary Contact CHECKBOX For Traffic Contact
	public void clickOnTrafficSAPConCBox() {
		testUtil.init(this);
		logger.info("Click On Same as Primary Contact CHECKBOX For Traffic Contact");
		weCheckedTrafficCont.click();
	}

	// Verify Traffic Contact Name
	public String verifyTrafficConName() {
		testUtil.init(this);
		logger.info("Verify Traffic Contact Name");
		String TrafficContactName = weTrafficContName.getAttribute("value");
		return TrafficContactName;
	}

	// Verify Traffic Contact Email
	public String verifyTrafficConEmail() {
		testUtil.init(this);
		logger.info("Verify Traffic Contact Email");
		String TrafficContactEmail = weTrafficContEmail.getAttribute("value");
		return TrafficContactEmail;
	}

	// ** Additional Contact *****
	// Click [+] button for Additional Contact
	public void clickOnAdditionalContExpandButton() {
		testUtil.init(this);
		logger.info("Click [+] button for Additional Contact");
		weAdditionalContExpand.click();
	}

	// Click On Same as Primary Contact CHECKBOX For Additional Contact
	public void clickOnAdditionalSAPConCBox() {
		testUtil.init(this);
		logger.info("Click On Same as Primary Contact CHECKBOX For Additional Contact");
		weCheckedAdditionalCont.click();
	}

	// Verify Additional Contact Name
	public String verifyAdditionalConName() {
		testUtil.init(this);
		logger.info("Verify Additional Contact Name");
		String AdditionalContactName = weAdditionalContName.getAttribute("value");
		return AdditionalContactName;
	}

	// Verify Additional Contact Email
	public String verifyAdditionalConEmail() {
		testUtil.init(this);
		logger.info("Verify Additional Contact Email");
		String AdditionalContactEmail = weAdditionalContEmail.getAttribute("value");
		return AdditionalContactEmail;
	}

	// ** Person Completing Form *****
	// Click On Same as Primary Contact CHECKBOX For Person Completing Form
	public void clickOnPCFormSAPConCBox() {
		testUtil.init(this);
		logger.info("Click On Same as Primary Contact CHECKBOX For Person Completing Form");
		weCheckedPCForm.click();
	}

	// Verify Additional Contact Name
	public String verifyPCFormConName() {
		testUtil.init(this);
		logger.info("Verify Person Completing Form Name");
		String PCFormName = wePCFormName.getAttribute("value");
		return PCFormName;
	}

	// ** Check Mark on E-mail Address *****
	// Click On Select to send a copy of this request to your e-mail address
	// CHECKBOX
	public void clickOnEmailAddressCBox() {
		testUtil.init(this);
		logger.info("Click On Select to send a copy of this request to your e-mail address CHECKBOX");
		weCheckedEmailAddress.click();
	}

	public void verifyEmailTextFieldDisplay() {
		testUtil.init(this);
		logger.info("Validate E-mail text field is displayed.");
		WebElement email = driver.findElement(By.xpath("//input[@id='mat-input-69']"));
		if (email.isDisplayed()) {
			System.out.println("email field displayed!!");
		} else {
			System.out.println("email field NOT display");
		}

	}

	// Enter Email
	public void enterEmail(String enterEmail) {
		testUtil.init(this);
		logger.info("Enter your e-mail address");
		weEnterEmailAddress.sendKeys(enterEmail);
	}

	public void clickOnGetAccountNumberLink() {
		logger.info("Click on Get account number link ");
		testUtil.init(this);
		testUtil.setExplicitWait(getAccountNumberLink, 20);
		getAccountNumberLink.click();
	}

	//Verify success message
	public void verifySuccessMsg() {
		logger.info("Verify Success message");
		testUtil.init(this);
		String actualMsg = driver.findElement(By.xpath("//lib-feedback/div/span")).getText();
		String expectedMsg ="Your EDI request has been successfully submitted. An account representative will be in touch with you soon. Your request reference number is:";
		Assert.assertTrue(actualMsg.contains(expectedMsg));
		System.out.println(expectedMsg);	
	}
}

