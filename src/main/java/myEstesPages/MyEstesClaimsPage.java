package myEstesPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesClaimsPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesClaimsPage.class);
	
	//################# Claims Inquiry  ####################

	// Click On Search By 
	@FindBy(how = How.XPATH, using = "//*[@id='searchBy']/div/div[1]")
	private WebElement weSearchBy;
	// Click On PRO Number From Search By Drop Down
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'PRO Number')])[2]")
	private WebElement wePRONumber;
	// LOOKUP 
	@FindBy(how = How.XPATH, using = "//*[contains(@type,'submit')]")
	private WebElement weLOOKUPButton;
	// Valid PRO Number One Per Line For Claims Inquiry
	@FindBy(how = How.ID, using = "numbers")
	private WebElement wePRONumbers1PerLine;
	
	
	//################# File a Claim  ####################
	
	// File a Claim Link
	@FindBy(how = How.XPATH, using = "//*[@id='mat-tab-label-0-1']/div")
	private WebElement weFileAClaimLink;
	// File a Claim Button
	@FindBy(how = How.XPATH, using = "//*[@id='mat-tab-content-0-1']/div/app-file-claim/button")
	private WebElement weFileAClaimButton;

	////////////// ****** Enter Pro Number and Claim Type ****** 

	// PRO Number File a Claim
	@FindBy(how = How.ID, using = "otPro")
	private WebElement wePRONum;
	// Claim Type
	@FindBy(how = How.XPATH, using = "//*[@id='claimType']/div/div[2]/div")
	private WebElement weClaimType;
	// Claim Type Damage
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Damage')])[6]")
	private WebElement weClaimTypeDamage;
	// Claim Type Loss
	@FindBy(how = How.XPATH, using = "//*[@id='mat-option-17']/span")
	private WebElement weClaimTypeLoss;
	// Freight Type
	@FindBy(how = How.XPATH, using = "//*[@id='freightType']/div/div[1]")
	private WebElement weFreightType;
	// Freight Type Boxes
	@FindBy(how = How.XPATH, using = "//*[@id='mat-option-21']/span")
	private WebElement weFreightTypeBoxes;

	////////////// ****** Enter Claimant and Remittance Info ****** 
	
	// Claimant Info My Info Link
	@FindBy(how = How.XPATH, using = "(//*[@class='row pb-3']/div[1]/a)[1]")
	private WebElement weClaimantInfoMyInfoLink;
	//Claimant Info Phone Number
	@FindBy(how = How.ID, using = "phone")
	private WebElement weClaimantInfoPhoneNum;
	// Remittance Info Same as Claimant
	@FindBy(how = How.XPATH, using = "(//*[@class='row pb-3']/div[1]/a)[2]")
	private WebElement weRemittanceInfoSameAsClaimantLink;
	//Remittance Info Phone Number
	@FindBy(how = How.ID, using = "remitPhone")
	private WebElement weRemitInfoPhoneNum;
	
	////////////// ****** Upload Freight Documents ******
	
	// Browse for Related Claim Invoices
	@FindBy(how = How.ID, using = "invoiceFile")
	private WebElement weRelatedClaimInvoicesBrowse;
	////////////// ****** Claim Details ****** 
	
	// Loss / Damage Description
	@FindBy(how = How.ID, using = "descriptionDetails1")
	private WebElement weLossDamageDesc;
	// Qty
	@FindBy(how = How.ID, using = "qtyDetails1")
	private WebElement weQty;
	// Total Cost
	@FindBy(how = How.ID, using = "costDetails1")
	private WebElement weTotalCost;
	
	
	// Additional Comments
	@FindBy(how = How.ID, using = "additionalComments")
	private WebElement weAddiComments;
	// Acknowledge Button
	@FindBy(how = How.XPATH, using = "//*[@id='acknowledgement']/label/div")
	private WebElement weAcknowledgeButton;
	// Submit Button
	@FindBy(how = How.XPATH, using = "//*[contains(@type, 'submit')]")
	private WebElement weClaimsSubmitButton;
	
	@FindBy(how = How.ID, using = "descriptionDetails2")
	private WebElement weLossDamageDesc2;
	// Qty
	@FindBy(how = How.ID, using = "qtyDetails2")
	private WebElement weQty2;
	// Total Cost
	@FindBy(how = How.ID, using = "costDetails2")
	private WebElement weTotalCost2;
	

	////
	//################# Claims Inquiry  ####################
	
	// Click On Search By in Claims Inquiry
	public void clickOnSearchBy() {
		logger.info("Click On Search By in Claims Inquiry");
		testUtil.init(this);
		weSearchBy.click();
	}
	// Click On PRO Number From Search By Drop Down	
	public void clickOnProNumFromSearchByDDown () {
		logger.info("Click On PRO Number From Search By Drop Down");
		testUtil.init(this);
		wePRONumber.click();		
	}
	// Click On LOOKUP Button
	public void clickOnLookupButton() {
		logger.info("Click On Search By in Claims Inquiry");
		testUtil.init(this);
		weLOOKUPButton.click();
	}
	// Click On PRO Number(One per line)
	public void clickOnProNumberOnePerLine() {
		logger.info("Click On PRO Number(One per line)");
		testUtil.init(this);
		wePRONumbers1PerLine.click();
	}
	
	// Enter a Valid PRO Number One Per Line For Claims Inquiry
	public void enterProNumOnePerLine(String ProNum) {
		logger.info("Enter a Valid PRO Number One Per Line For Claims Inquiry");
		testUtil.init(this);
		String PNumber = ProNum.replace("-", "");
		wePRONumbers1PerLine.sendKeys(PNumber);
	}
	
	
	//################# File a Claim  ####################
	
	// Click On File a Claim Link
	public void clickOnFileAClaimLink () {
		logger.info("Click On File a Claim Link");
		testUtil.init(this);
		weFileAClaimLink.click();		
	}
	// Click On File a Claim Button
	public void clickOnFileAClaimButton () {
		logger.info("Click On File a Claim Button");
		testUtil.init(this);
		weFileAClaimButton.click();		
	}
	///// ****** Enter Pro Number and Claim Type ****** 
	// Enter PRO Number For File a Claim
	public void enterProNumberForFileAClaim(String PRONum) {
		logger.info("Enter PRO Number File a Claim");
		testUtil.init(this);
		wePRONum.sendKeys(PRONum);
	}
	// Click On Claim Type
	public void clickOnClaimType () {
		logger.info("Click On Claim Type");
		testUtil.init(this);
		weClaimType.click();		
	}
	// Select Claim Type Damage
	public void selectClaimTypeDamage () {
		logger.info("Select Claim Type Damage");
		testUtil.init(this);
		weClaimTypeDamage.click();		
	}
	// Select Claim Type Loss
	public void selectClaimTypeLoss () {
		logger.info("Select Claim Type Loss");
		testUtil.init(this);
		weClaimTypeLoss.click();		
	}
	// Click On Freight Type
	public void clickOnFreightType () {
		logger.info("Click On freight Type");
		testUtil.init(this);
		weFreightType.click();		
	}
	// Select Freight Type Boxes
	public void selectFreightTypeBoxes () {
		logger.info("Select Freight Type Boxes");
		testUtil.init(this);
		weFreightTypeBoxes.click();		
	}
	////////////// ****** Enter Claimant and Remittance Info ****** 
	
	// Click On Claimant Info My Info Link
	public void clickOnClaimantIMyInfoLink () {
		logger.info("Click On My Info Link");
		testUtil.init(this);
		weClaimantInfoMyInfoLink.click();		
	}
	// Enter Claimant Info Phone Number
	public void enterClaimantInfoPhoneNum(String ClaimPhoneNum) {
		logger.info("Enter Claimant Info Phone Numbe");
		testUtil.init(this);
		weClaimantInfoPhoneNum.sendKeys(ClaimPhoneNum);
	}
	// Click On  Remittance Info Same as Claimant
	public void clickOnRemitInfoSameAsClaimantLink () {
		logger.info("Click On  Remittance Info Same as Claimant");
		testUtil.init(this);
		weRemittanceInfoSameAsClaimantLink.click();		
	}
	// Enter Remittance Info Phone Number
	public void enterRemitInfoPhoneNum(String RemitPhoneNum) {
		logger.info("Enter Remittance Info Phone Numbe");
		testUtil.init(this);
		weRemitInfoPhoneNum.sendKeys(RemitPhoneNum);
	}
	
//////////////****** Upload Freight Documents ******
	
//  Related Claim Invoices Browse
	public void clickOnRelatedClaimInvoicesBrowse () {
		logger.info("Related Claim Invoices Browse");
		testUtil.init(this);
		weRelatedClaimInvoicesBrowse.click();		
	}
	
	////////////// ****** Claim Details ****** 	
	
	// Write Loss / Damage Description
	public void writeLossDamageDesc () {
		logger.info("Enter Remittance Info Phone Numbe");
		testUtil.init(this);
		weLossDamageDesc.sendKeys("QA Test this item is Broken OMG  FR");	
	}
	// Enter Qty
	public void enterQty(String EnterQty) {
		logger.info("Enter Qty");
		testUtil.init(this);
		weQty.sendKeys(EnterQty);	
	}
	// Enter Total Cost
	public void enterTotalCost (String EnterTotalCost) {
		logger.info("Enter Total Cost");
		testUtil.init(this);
		weTotalCost.sendKeys(EnterTotalCost);	
	}
	// Write Additional Comments
	public void writeAdditionalComments (String WriteYourComments) {
		logger.info("Write in Additional Comments Box");
		testUtil.init(this);
		weAddiComments.sendKeys(WriteYourComments);	
	}
	//  Acknowledge Button
	public void clickOnAcknowledgeButton () {
		logger.info("Select Acknowledge Button");
		testUtil.init(this);
		weAcknowledgeButton.click();		
	}
	// Click On ClaimsSubmit Button
	public void clickOnClaimsSubmitButton () {
		logger.info("Select Acknowledge Button");
		testUtil.init(this);
		weClaimsSubmitButton.click();		
	}
	
	public void writeLossDamageDescItem2 () {
		logger.info("Enter Remittance Info Phone Numbe");
		testUtil.init(this);
		weLossDamageDesc2.sendKeys("QA Test this item is Broken OMG  FR");	
	}
	// Enter Qty
	public void enterQtyInItem2(String EnterQty) {
		logger.info("Enter Qty");
		testUtil.init(this);
		weQty2.sendKeys(EnterQty);	
	}
	// Enter Total Cost
	public void enterTotalCostInItem2 (String EnterTotalCost) {
		logger.info("Enter Total Cost");
		testUtil.init(this);
		weTotalCost2.sendKeys(EnterTotalCost);	
	}
	
}
