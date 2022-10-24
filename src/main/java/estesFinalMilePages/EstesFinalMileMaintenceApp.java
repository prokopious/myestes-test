package estesFinalMilePages;

import com.github.javafaker.Faker;
import com.jagacy.SessionVt;

import jagacyVT.screens.LoginScreen;
import jagacyVT.screens.Ltl38MasterMenuScreen;
import jagacyVT.screens.UpdateScreen;
import jagacyVT.screens.UpdateScreen2;
import jagacyVT.screens.UpdateScreen3;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EstesFinalMileMaintenceApp extends TestBase {

	private Logger logger = Logger.getLogger(EstesFinalMileMaintenceApp.class);

	@FindBy(how = How.ID, using = "username")
	private WebElement username;

	@FindBy(how = How.ID, using = "password")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//a[@id='logout']/img")
	private WebElement logoutButton;

	@FindBy(how = How.ID, using = "loginButton")
	private WebElement loginButton;

	@FindBy(how = How.LINK_TEXT, using = "Signature options")
	private WebElement clickSignatureOptions;

	@FindBy(how = How.ID, using = "dtlAccountId")
	private WebElement enterAccountId;

	@FindBy(how = How.ID, using = "dtlIncludeExcludeId")
	private WebElement signatureRequired;

	@FindBy(how = How.ID, using = "dtlrbpTypeId")
	private WebElement optionType;

	@FindBy(how = How.ID, using = "manageUnattendedDeliveryAccountButtonId")
	private WebElement add;
	@FindBy(how = How.CSS, using = ".ui-state-focus > .ui-button-text")
	private WebElement confirmDelete;
	@FindBy(how = How.XPATH, using = "//*[@id='unattendedDeliveryAccountControllerContainer']/div/table/tbody/tr[*]")
	private List<WebElement> signatureTable;
	
	@FindBy(how = How.ID, using = "menu-account")
	private WebElement accountEclusions;
	@FindBy(how = How.XPATH, using = "//*[@id=\"accountContainer\"]/div/table/tbody/tr")
	private List<WebElement> accountRows;

	/* SIGNATURE OPTIONS */
	@FindBy(how = How.ID, using = "dtlAccountId")
	private WebElement accountField;
	
	@FindBy(how = How.ID, using = "dtlIncludeExcludeId")
	private WebElement signatureOptionField;
	
	@FindBy(how = How.ID, using = "dtlrbpTypeId")
	private WebElement signatureOptionTypeField;
	
	@FindBy(how = How.ID, using = "dtlAppointmentOptionId")
	private WebElement appointmentOptionField;
	
	@FindBy(how = How.ID, using = "unattendedDeliveryAccount.accountId.errors")
	private List<WebElement> cannotAddError;
	
	@FindBy(how = How.NAME, using = "manageUnattendedDeliveryAccountButton")
	private WebElement updateButton;
	
	@FindBy(how = How.XPATH, using = "//span[@class='ui-button-text' and contains(text(),'Yes')]")
	private WebElement updateYesButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='unattendedDeliveryAccountControllerContainer']/div/div[3]/div[1]/span[1]/span[7]")
	private WebElement nextButton;
	
	@FindBy(how=How.XPATH, using = "//*[@class='jtable-page-size-change']//select/option[@value='500']")
	private WebElement rowCount500; 

	/***********************************************METHODS********************************************/
	
	
	public void login(String username, String password) {
		testUtil.init(this);
		setWait(this.username, this.password, loginButton);

		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginButton.click();
	}

	public void clickSignatureOptions() {
		testUtil.init(this);
		setWait(clickSignatureOptions);
		clickSignatureOptions.click();
	}

	public void enterSignatureOptions(String accountId, String sigRequiredLabel, String optionTypeLabel) {
		testUtil.init(this);
		setWait(enterAccountId, signatureRequired, optionType, add);
		enterAccountId.sendKeys(accountId);
		selectComboBox(sigRequiredLabel, signatureRequired);
		selectComboBox(optionTypeLabel, optionType);
		add.click();

	}

	public void clickConfirmDelete() {
		testUtil.init(this);
		setWait(confirmDelete);
		confirmDelete.click();
	}

	public void setWait(WebElement... elements) {
		Arrays.stream(elements).forEach(ele -> testUtil.setExplicitWait(ele, 60));
	}

	public void selectComboBox(String value, WebElement element) {
		Select comboBox = new Select(element);
		comboBox.selectByVisibleText(value);
	}

	public void logout() {
		logger.info("Logging Out");
		testUtil.init(this);
		testUtil.setExplicitWait(logoutButton, 60);
		logoutButton.click();
	}

	public void deleteAccNumIfExistsInTable(String accNum) {
		logger.info("Select number of rows to be displayed");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.waitForJqueryToLoad();

		logger.info("Delete Account number if it is already exist in the table");

		logger.info("Searching Account " + accNum + ".....");

		testUtil.init(this);
		
		//Adding all row values in the signature page
		rowCount500.click();

		for (int i = 0; i < signatureTable.size(); i++) {

			int j = i + 1;
			String eleName = driver.findElement(By.xpath(
					"//*[@id='unattendedDeliveryAccountControllerContainer']/div/table/tbody/tr[" + j + "]/td[1]"))
					.getText();

			if (eleName.equals(accNum)) {
				logger.info("Delete Commodity:" + accNum);
				driver.findElement(By.xpath("//*[@onclick=\"deleteUnattendedDeliveryAccount('" + accNum + "')\"]"))
						.click();
				testUtil.setHardWait(1000);

				driver.findElement(By.xpath("//span[contains(text(),'Yes')]")).click();
				testUtil.setHardWait(1000);
				break;
			}

		}
	}

	public void loginAndCreate(String accountId, String signatureRequired, String always, String username,
			String password, int position) {

		login(username, password);
		clickSignatureOptions();
		checkWalMartPosition(accountId, signatureRequired, always, position);

	}

	public void loginAndDelete(String urlOne, String accountId, String username, String password) {
		testUtil.openNewTab(urlOne);
		login(username, password);
		clickSignatureOptions();
		deleteAccNumIfExistsInTable(accountId);

	}

	private void checkWalMartPosition(String accountId, String signatureRequired, String always, int position) {
		if (position == -1) {
			// enter signature options
			enterSignatureOptions(accountId, signatureRequired, always);

		} else {
			// delete from table
			deleteAccNumIfExistsInTable(accountId);
			// add
			enterSignatureOptions(accountId, signatureRequired, always);
		}
	}

	public String reserveFreightBill() throws Exception {
		String option = "1";
		String reserveOption = "82";
		String user = "REG";
		String originTerminal = "087";
		String sCode = "1129358";
		String consZip = "43228";
		String option1 = "2";

		// Jagacy
		SessionVt session = null;
		String userName = "QATSTFRTBL";
		String password = "qatest2019";
		String name = "myJagacyVT";

		// String host="exlaqa";
		String terminal = "dec-vt220";
		session = new SessionVt(name, "exlaqa", terminal);

		// MySession session = new MySession();
		session.open();
		Thread.sleep(2000);

		LoginScreen loginScreen = new LoginScreen(session);
		loginScreen.enterUserNPasswordCDOC(userName, password);
		jagacy.util.JagacyUtil jagacyUtil = new jagacy.util.JagacyUtil(session);

		//	jagacyUtil.pressEnter();

		// Enter Option 1 in LTL/38 Master Menu
		Ltl38MasterMenuScreen ltl38mastermenu = new Ltl38MasterMenuScreen(session);
		ltl38mastermenu.enterValueOptionField(option);
		// Option to reserve Freight bill
		jagacyVT.screens.FreightBillingMenuScreen freightBillingMenuScreen = new jagacyVT.screens.FreightBillingMenuScreen(
				session);
		freightBillingMenuScreen.enterFreightBillMenuOption(reserveOption, user, originTerminal);

		// Reserve freight Bill
		jagacyVT.screens.ReserveFreightBillScreen reserveFreightBillScreen = new jagacyVT.screens.ReserveFreightBillScreen(
				session);
		reserveFreightBillScreen.verifyScreenTitle();
		reserveFreightBillScreen.enterBillsToReserve(option);
		reserveFreightBillScreen.enterShipperCode(sCode);

		// Record the pro number
		String billNum = reserveFreightBillScreen.recordFBNumber();

		String otPro = originTerminal + billNum;
		System.out.println("Freight Bill No " + otPro);
		// Press F3 to exit
		jagacyUtil.pressF3();

		// Enter Freight bill
		freightBillingMenuScreen.enterFreightBillMenuOption(option1, user, originTerminal);
		freightBillingMenuScreen.enterFreightBill(originTerminal, billNum);
		UpdateScreen freightBillUpdateScreen = new UpdateScreen(session);
		freightBillUpdateScreen.enterTS("1");
		freightBillUpdateScreen.enterPcs("10");
		freightBillUpdateScreen.enterTerms("PPD");
		freightBillUpdateScreen.enterWgt("1000");
		freightBillUpdateScreen.enterCons("8700777");
		freightBillUpdateScreen.enterConsName("REG SIGRQD");
		freightBillUpdateScreen.enterConsAddress("2 KINGSHILL DR");
		freightBillUpdateScreen.enterConsCity("COLUMBUS");
		freightBillUpdateScreen.enterConsState("OH");
		freightBillUpdateScreen.enterConsZip(consZip);
		freightBillUpdateScreen.enterPONum("224091819");
		freightBillUpdateScreen.enterPuDrNum("9999999", "9");
		freightBillUpdateScreen.enterCubicFeet("11");
		freightBillUpdateScreen.enterCartTo("E087");
		// bl number needs to be different from previous rerun faker if test fails
		Faker faker = new Faker();
		String blNumber = "S" + faker.number().digits(7);
		freightBillUpdateScreen.enterMasterBlNo(blNumber);
		jagacyUtil.pressEnter();

		// Press Enter
		jagacyUtil.pressEnter();
		UpdateScreen2 freightBillUpdateScreen2 = new UpdateScreen2(session);
		freightBillUpdateScreen2.enterClass("50");
		freightBillUpdateScreen2.enterPcs2("10");
		freightBillUpdateScreen2.enterPK("SK");
		freightBillUpdateScreen2.enterDesc("FAK");
		freightBillUpdateScreen2.enterWgt2("1000");

		jagacyUtil.pressEnter();
		jagacyUtil.pressEnter();
		// Adding shipment instructions
		UpdateScreen3 freightBillUpdateScreen3 = new UpdateScreen3(session);
		freightBillUpdateScreen3.enterAccessoryInstruction1("HD");
		freightBillUpdateScreen3.enterShippingInstruction1("SIGRQD");
		// freightBillUpdateScreen3.enterShippingInstruction2("SGN");
		jagacyUtil.pressEnter();

		// Press F1 to exit
		jagacyUtil.pressF1();
		jagacyUtil.pressF3();

		if (session != null) {
			session.abort();
			session.close();
		}

		session = null;
		return otPro;
	}

	
	public void clickAccountExclusions() {
		logger.info("Click Account Exclusions");
		accountEclusions.click();
		testUtil.setHardWait(500);
	}

	public boolean isAccountDisplayed(String expected) {
		logger.info("Check is account displayed");
		for (WebElement accountRow : accountRows) {
			WebElement accountNo = accountRow.findElement(By.xpath("./td"));
			logger.info(accountNo.getText());
			if (expected.equals(accountNo.getText())) {
				logger.info("Account was found");
				return true;
			}
		}
		logger.info("Account not found");
		return false;
	}
	
	

	public boolean isAccountDisplayed(String[] expectedList) {
		logger.info("Check is account displayed");
		for (WebElement accountRow : accountRows) {
			WebElement accountNo = accountRow.findElement(By.xpath("./td"));
			logger.info(accountNo.getText());
			for (String expected : expectedList) {
				if (expected.equals(accountNo.getText())) {
					logger.info("Account was found");
					return true;
				}
			}
		}
		logger.info("Account not found");
		return false;
	}

	public void enterSignatureOptionsAll(String accountId, String sigRequiredLabel, String optionTypeLabel,
			String AppointmentOp) {
		testUtil.init(this);
		setWait(enterAccountId, signatureRequired, optionType, add);
		enterAccountId.sendKeys(accountId);
		selectComboBox(sigRequiredLabel, signatureRequired);
		selectComboBox(optionTypeLabel, optionType);
		selectComboBox(AppointmentOp, appointmentOptionField);
		add.click();
	}

	public void editSignatureOptions(String signatureOption, String signatureOptionType, String appointmentOption) {
		setWait(signatureRequired, optionType);
		selectComboBox(signatureOption, signatureRequired);
		selectComboBox(signatureOptionType, optionType);
		selectComboBox(appointmentOption, appointmentOptionField);
	}

	public void deleteAccountIfErrorIsDisplayedAndAddAgain(String accountId, String sigRequiredLabel,
			String optionTypeLabel, String appointmentOp) {
		if (cannotAddError.size() == 1 && cannotAddError.get(0).getText().equals("Cannot add.")) { // An error was
																									// displayed
			logger.info("Error is displayed");
			deleteAccNumIfExistsInTable(accountId);
			enterSignatureOptionsAll(accountId, sigRequiredLabel, optionTypeLabel, appointmentOp);
		}
	}

	public void clickEditAcount(String accountId) {
		logger.info("Click edit account for [" + accountId + "]");
		WebElement editButton = driver.findElement(By
				.xpath("//*[contains(text(),'" + accountId + "')]/following-sibling::td[5]/input[@name='editButton']"));
		editButton.click();
	}

	public void clickUpdateButton() {
		setWait(updateButton);
		updateButton.click();
		setWait(updateYesButton);
		updateYesButton.click();
	}

	public void verifySignatureOptionsArePopulated(String accountID, String signatureOption, String signatureOptionType,
			String appointmentOption) {
		String accVal = accountField.getAttribute("value");

		String sigOptionValue = driver
				.findElement(By.xpath("//option[@value='" + signatureOptionField.getAttribute("value") + "']"))
				.getText();
		String sigOptionTypeValue = driver
				.findElement(By.xpath("//option[@value='" + signatureOptionTypeField.getAttribute("value") + "']"))
				.getText();
		String apptOptionValue = driver
				.findElement(By.xpath("//option[@value='" + appointmentOptionField.getAttribute("value") + "']"))
				.getText();

		assertTrue(accountID.equals(accVal), "Unexpected account ID was shown");
		assertTrue(signatureOption.equals(sigOptionValue), "Unexpected signature option was shown");
		assertTrue(signatureOptionType.equals(sigOptionTypeValue), "Unexpected signature option type was shown");
		assertTrue(appointmentOption.equals(apptOptionValue), "Unexpected appointment option was shown");
	}

	public void clickNextButton() {
		testUtil.init(this);
		logger.info("Click Next Page");
		nextButton.click();
	}
	
	
	

	
}
