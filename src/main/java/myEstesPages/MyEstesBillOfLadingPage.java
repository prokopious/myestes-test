
package myEstesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;

public class MyEstesBillOfLadingPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesBillOfLadingPage.class);

	// ####################### BOL Source #####################
	// Blank VICS BOL Radio Button
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Blank VICS BOL')]")
	private WebElement weBVICSBolRadioButton;
	
	@FindBy(xpath="//*[contains(text(),'Blank Estes BOL')]")
	private WebElement weBEstesBOL;

	// ####################### BOL Details #####################
	// Master BOL
	@FindBy(css = "[formcontrolname='masterBol'] div[class*='toggle-bar']")
	private WebElement weMasterBOL;
	// Master BOL#
	@FindBy(css = "[formcontrolname='masterBolNumber']")
	private WebElement weMasterBOLNum;
	// Auto-Assign PRO #
	@FindBy(how = How.XPATH, using = "//*[@id='mat-slide-toggle-1']/label")
	private WebElement weAutoAssignPRO;

	@FindBy(how = How.XPATH, using = "//*[@id='mat-slide-toggle-10']/label")
	private WebElement generatePickup;

	// BOL Reference #
	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='bolReferenceNumber']")
	private WebElement weBOLReference;

	
	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='name']")
	private WebElement contactName;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-11\"]")
	private WebElement email;

	//mat-card-content[lib-form-header[h6[text()='Requester Info']]]/descendant::input[@formcontrolname='email']
	
	@FindBy(how = How.XPATH, using = "//mat-card-content[lib-form-header[h6[text()='Requester Info']]]/descendant::input[@formcontrolname='phone']")
	private WebElement phone;

	// ####################### Quote Details #####################

	// ####################### Pickup Request #####################

	@FindBy(css = "[formcontrolname='pickupRequest'] div[class*='toggle-bar']")
	private WebElement pickupRequestToggle;

	@FindBy(css = "[formcontrolname='name']")
	private WebElement pickupRequestContactName;

	@FindBy(xpath = "//app-pickup-request//input[@formcontrolname='email']")
	private WebElement pickupRequestEmail;

	@FindBy(css = "app-pickup-request [formcontrolname='phone']")
	private WebElement pickupRequestPhoneNumber;

	@FindBy(id = "role")
	private WebElement pickupRole;

	@FindBy(xpath = "//span[text()='Consignee']")
	private WebElement consigneeRole;

	@FindBy(css = "[formcontrolname='pickupDate']")
	private WebElement pickupDate;

	// ####################### Accessorials #####################
	// Inside Delivery
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Inside Delivery')]")
	private WebElement weInsideDeliveryRadioButton;

	// ####################### Shipper and Consignee Details #####################
	// Use My Estes Account info For Shipper
	@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-1']/label/span")
	private WebElement weUseMyEstesAccInfoForShipper;
	// Address Line1 for Shipper
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='address1'])[1]")
	private WebElement weAddrLine1ForShipper;
	// City For Shipper
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='city'])[1]")
	private WebElement weCityForShipper;
	// Click on State For Shipper
	@FindBy(how = How.XPATH, using = "(//*[@id='state']/div/div[1]/span)[1]")
	private WebElement weStateForShipper;
	/*
	 * // Enter State Name for Shipper
	 * 
	 * @FindBy(how = How.XPATH, using = "//*[contains(text(),'VA')]") private
	 * WebElement weEnterStateNameForShipper;
	 */
	// ZIP Code For Shipper
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='zip'])[1]")
	private WebElement weZIPCodeForShipper;

	// Use My Estes Account info For consignee
	@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-2']/label/span")
	private WebElement weUseMyEstesAccInfoForConsignee;
	// Address Line1 for consignee
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='address1'])[2]")
	private WebElement weAddrLine1Forconsignee;
	// City For consignee
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='city'])[2]")
	private WebElement weCityForConsignee;
	// Click On State For consignee
	@FindBy(how = How.XPATH, using = "(//*[@id='state']/div/div[1]/span)[2]")
	private WebElement weStateForConsignee;
	/*
	 * // Enter State Name for Consignee
	 * 
	 * @FindBy(how = How.XPATH, using = "//*[contains(text(),'VA')]") private
	 * WebElement weEnterStateNameForConsignee;
	 */
	// ZIP Code For consignee
	@FindBy(how = How.XPATH, using = "(//*[@formcontrolname='zip'])[2]")
	private WebElement weZIPCodeForConsignee;

	@FindBy(xpath = "//*[@id='bolFormContainer']//app-shipper-consignee-details//div/div[1]//form//input[@formcontrolname='companyName']")
	private WebElement companyName;

	@FindBy(xpath = "//*[@id='bolFormContainer']//app-shipper-consignee-details//div/div[1]//form//input[@formcontrolname='firstName']")
	private WebElement firstName;

	@FindBy(xpath = "//*[@id='bolFormContainer']//app-shipper-consignee-details//div/div[1]//form//input[@formcontrolname='phone']")
	private WebElement phoneNumber;

	@FindBy(xpath = "//*[@id='bolFormContainer']//app-shipper-consignee-details//div/div[1]//form//input[@formcontrolname='email']")
	private WebElement emailAddress;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='reservedPro']")
	private WebElement reservedPronumber;

	// @Pickup Date
	@FindBy(how = How.XPATH, using = "//app-pickup-request//*[@formcontrolname='pickupDate']")
	private WebElement prPickupDate;

	// Bill To
	@FindBy(how = How.ID, using = "billTo")
	private WebElement weBillTo;

	// ####################### Billing Information #####################

	@FindBy(id = "billTo")
	private WebElement billTo;

	@FindBy(xpath = "//span[text()='Shipper']")
	private WebElement shipperBillTo;

	// ####################### Commodities #####################
	// Handling Units
	@FindBy(how = How.ID, using = "goodsUnit0")
	private WebElement weHandlingUnits;
	// Click On Type
	@FindBy(how = How.XPATH, using = "//*[@id='goodsType0']/div/div[1]/span")
	private WebElement weClickOnType;
	// Total Weight
	@FindBy(how = How.ID, using = "goodsWeight0")
	private WebElement weTotalWeight;
	// Click On Packaging Type
	@FindBy(how = How.XPATH, using = "//*[@id='packageType0']/div/div[2]")
	private WebElement weClickOnPackagesType;
	// # of Packages
	@FindBy(how = How.ID, using = "numberOfPackages0")
	private WebElement weNumberOfPackages;

	@FindBy(how = How.XPATH, using = "//*[@id='billTo']")
	private WebElement clickOnBillTo;
	
	//@FindBy(how = How.XPATH, using = "//span[@class='mat-select-placeholder ng-tns-c33-269 ng-star-inserted']")
	@FindBy(how = How.XPATH, using ="//mat-select[@id='fee']/div/div/span")
	private WebElement clickOnTerms;
	
	@FindBy(how = How.XPATH, using = "//td[contains(text(),'$Test123$')]")
	private WebElement tempName;

	@FindBy(css = "[formcontrolname='shipmentClass']")
	private WebElement shipmentClass;

	// td[contains(text(),'$Test123$')]
	// ####################### Reference Numbers #####################

	// ####################### Shipping Labels #####################

	// ####################### Notifications #####################

	@FindBy(id = "shipperEmail")
	private WebElement shipperEmailId;

	// ####################### Save BOL Template #####################

	// Submit BOL Button
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary mr-3']")
	private WebElement weSubmitBOLButton;
	// View Bill Of Lading Button
	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-default mr-3 mb-3']")
	private WebElement weViewBillOfLadingButton;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default mr-3 ng-star-inserted']")
	private WebElement weSaveAsDraftButton;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='filter']//div[@class='mat-select-value']")
	private WebElement weAdvSearchBy;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Advanced Search')]")
	private WebElement weAdvSearchButton;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='contains']")
	private WebElement weContains;

	@FindBy(how = How.XPATH, using = "//button[@type='button'][contains(text(),'Search')]")
	private WebElement weSearchButton;

	// BOL Drafts Link
	@FindBy(how = How.XPATH, using = "//*[@id=\"mat-tab-label-0-3\"]/div/a")
	private WebElement weBOLDraftsLink;

	// Enter Start Date
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='startDate']")
	private WebElement weStartDate;
	// Enter End Date
	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='endDate']")
	private WebElement weEndDate;

	@FindBy(how = How.XPATH, using = "//*[@title='Delete Bill of Lading']/span/mat-icon")
	private WebElement deleteBOLDraft;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Confirm')]")
	private WebElement btnConfirm;

	@FindBy(how = How.XPATH, using = "//lib-snackbar-message[contains(@class,'ng-star-inserted')]")
	private WebElement message;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='saveTemplate']/label/div")
	private WebElement saveTemplate;

	@FindBy(how = How.ID, using = "templateName")
	private WebElement templateName;

	@FindBy(how = How.ID, using = "bolSaveTemplateButton")
	private WebElement saveTemplateButton;

	@FindBy(how = How.XPATH, using = "//*[@id='mat-tab-label-0-2']/div/a")
	private WebElement weBOLTemplatesLink;

	@FindBy(how = How.XPATH, using = "//*[@title='Delete Template']/span/mat-icon")
	private WebElement deleteBOLTemplate;

	@FindBy(css = "[class*='alert alert-danger ng-star-inserted']")
	private WebElement alertMessage;

	@FindBy(id = "bolSubmitButton")
	private WebElement submitBOL;

	@FindBy(how = How.XPATH, using = "//*[@id='mat-tab-label-0-1']/div/a")
	private WebElement BOLHistoryLink;

	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/tbody/tr[1]/td[7]/div/button[1]")
	private WebElement BOLCopy;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='bolDate']")
	private WebElement bolDate;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Auto-assign PRO Number')]")
	private WebElement autoAssignToggle;

	@FindBy(how = How.XPATH, using = "//mat-card-content/lib-form-header[1]/h6")
	private WebElement bolNumber;

	@FindBy(how = How.XPATH, using = "//div[label[text()='PRO #']]")
	private WebElement proNumber;

	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/tbody/tr/td[1]")
	private List<WebElement> lstBolNumber;

	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/tbody/tr/td[3]")
	private List<WebElement> lstProNumber;

	@FindBy(how = How.ID, using = "role")
	private WebElement role;

	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'Shipper')]")
	private WebElement shipper;

	@FindBy(how = How.ID, using = "addressSelect")
	private WebElement accountNo;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='pickupDate']")
	private WebElement pickupdate;

	@FindBy(how = How.XPATH, using = "//label[span[contains(text(),'Use My Estes Account info')]][@for='mat-checkbox-2-input']/div")
	private WebElement addressInfo;

	@FindBy(how = How.ID, using = "goodsUnit0")
	private WebElement handlingUnit;

	@FindBy(how = How.ID, using = "goodsType0")
	private WebElement handlingType;

	@FindBy(how = How.XPATH, using = "//mat-option/span[contains(text(),'BAG')]")
	private WebElement handlingTypeValue;

	@FindBy(how = How.ID, using = "goodsWeight0")
	private WebElement weight;

	@FindBy(how = How.ID, using = "shipperEmail")
	private WebElement shipperEmail;

	@FindBy(how = How.ID, using = "bolSubmitButton")
	private WebElement submit;
	
	@FindBy(how = How.XPATH, using = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//input[@formcontrolname='email']")
	private WebElement shipperInfoEmail;
	//*[@ng-reflect-header-title='Shipper Info']/descendant::input[@formcontrolname='email']
	
	
	@FindBy(how = How.XPATH, using = "//h6[contains(text(),'BOL Number')]")
	private WebElement bolNumberField;

	@FindBy(how = How.XPATH, using = "//label[text()='BOL Date']")
	private WebElement bolDateField;

	@FindBy(how = How.XPATH, using = "//label[text()='Account']")
	private WebElement accountField;

	@FindBy(how = How.XPATH, using = "//label[text()='PRO #']")
	private WebElement proNoField;

	@FindBy(how = How.XPATH, using = "//h6[contains(text(),'Shipping Summary')]")
	private WebElement shipperSummaryField;

	@FindBy(how = How.XPATH, using = "//label[text()='Shipper']")
	private WebElement shipperField;

	@FindBy(how = How.XPATH, using = "//label[text()='Shipper Phone']")
	private WebElement shipperPhoneField;

	@FindBy(how = How.XPATH, using = "//label[text()='Shipper Email']")
	private WebElement shipperEmailField;

	@FindBy(how = How.XPATH, using = "//label[text()='Consignee']")
	private WebElement consigneeField;

	@FindBy(how = How.XPATH, using = "//label[text()='Consignee Phone']")
	private WebElement consigneePhoneField;

	@FindBy(how = How.XPATH, using = "//label[text()='Consignee Email']")
	private WebElement consigneeEmailField;

	@FindBy(how = How.XPATH, using = "//label[text()='Total Commodities']")
	private WebElement totalCommoditiesField;

	@FindBy(how = How.XPATH, using = "//label[text()='Total Shipment Weight']")
	private WebElement totalShipmentWeightField;

	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='quoteNumber']")
	private WebElement quoteNumber;

	@FindBy(how = How.XPATH, using = "//app-create-bol//div[3]/app-quote-details//div[*]/div/mat-slide-toggle/label/div")
	private WebElement guaranteedServiceToggle;

	// Term Of Service checkbox
	@FindBy(how = How.XPATH, using = "//*[@id=\"tosChecked\"]/label/div")
	private WebElement termsOfService;

	@FindBy(how = How.XPATH, using = "//app-pickup-request//*[@formcontrolname='name']")
	private WebElement prContactName;

	// Email
	@FindBy(how = How.XPATH, using = "//app-pickup-request//*[@formcontrolname='email']")
	private WebElement prEmail;

	// @PhoneNumber
	@FindBy(how = How.XPATH, using = "//app-pickup-request//*[@formcontrolname='phone']")
	private WebElement prPhoneNumber;

	@FindBy(how = How.XPATH, using = "//app-pickup-request//*[@formcontrolname='role']")
	private WebElement prRole;

	@FindBy(how = How.XPATH, using = "//app-shipper-consignee-details//*[@formcontrolname='firstName' and @ aria-required='true']")
	private WebElement weFirstNameForShipper;
	// Email
	@FindBy(how = How.XPATH, using = "//app-shipper-consignee-details//*[@formcontrolname='email' and @ aria-required='true']")
	private WebElement weEmailForShipper;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create New BOL')]")
	private WebElement weCreateNewBOLButton;

	@FindBy(xpath = "//a[text()='Account Search']")
	private WebElement accountSearch;

	@FindBy(css = "input[class*='table-search']")
	private WebElement accountSearchField;

	@FindBy(xpath = "//mat-header-cell//button")
	private List<WebElement> accountSearchTableHeader;

	@FindBy(xpath = "//mat-row")
	private List<WebElement> searchRows;

	@FindAll({ @FindBy(xpath = "//mat-cell//span"), @FindBy(xpath = "//mat-cell//a") })
	private List<WebElement> accountSearchResults;

	@FindBy(xpath = "//p[text() ='No accounts found.']")
	private WebElement noAccountsFound;

	@FindBy(css = "#main tr")
	private List<WebElement> templateRows;

	@FindBy(css = "[title = 'Use Template']")
	private List<WebElement> useTemplate;

	@FindBy(xpath = "//p[text()='Your Bill of Lading has been created.']")
	private WebElement bolCreatedMessage;

	@FindBy(xpath = "//div[contains(text(),'Create BOL')]/parent::div")
	private WebElement createBOLTab;

	@FindBy(css = "[formcontrolname='description']")
	private WebElement commodityDescription;

	@FindBy(xpath = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//input[@formcontrolname='lastName']")
	private WebElement shipperLastName;

	@FindBy(xpath = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//input[@formcontrolname='location']")
	private WebElement shipperLocation;

	@FindBy(xpath = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//input[@formcontrolname='phoneExt']")
	private WebElement shipperPhoneExt;

	@FindBy(xpath = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//input[@formcontrolname='fax']")
	private WebElement shipperFaxNumber;

	@FindBy(xpath = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//input[@formcontrolname='address2']")
	private WebElement shipperAddressLine2;

	@FindBy(xpath = "//mat-card-content/div/div[1]//button[contains(text(),'Save to Address')]")
	private WebElement shipperSaveToAddressBook;

	//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='firstName']
	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='firstName']")
	private WebElement consigneeFirstName;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='lastName']")
	private WebElement consigneeLastName;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='location']")
	private WebElement consigneeLocation;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='phone']")
	private WebElement consigneePhone;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='phoneExt']")
	private WebElement consigneePhoneExt;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='fax']")
	private WebElement consigneeFaxNumber;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='email']")
	private WebElement consigneeEmail;

//	@FindBy(css = "[ng-reflect-header-title*='Cons'] [formcontrolname='address1']")
//	private WebElement consigneeAddressLine1;
	
	@FindBy(xpath = "//h6[contains(text(),'Consignee')]/ancestor::app-address-details/parent::div//input[@formcontrolname='address1']")
	private WebElement consigneeAddressLine1;
	
	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//input[@formcontrolname='address2']")
	private WebElement consigneeAddressLine2;

	@FindBy(xpath = "//mat-card-content/div/div[2]//button[contains(text(),'Save to Address')]")
	private WebElement consigneeSaveToAddressBook;

	@FindBy(xpath = "//label[@for='shipper-input']/div")
	private WebElement shipperCheckBox;

	@FindBy(xpath = "//app-add-address-modal//button[@type='submit']")
	private WebElement submitAddressBookPopup;

	@FindBy(xpath = "//lib-snackbar-message[contains(text(),'The address has been added')]")
	private WebElement addAddressSuccessMessage;

	@FindBy(xpath = "//*[@id='bolFormContainer']//app-shipper-consignee-details//div/div[2]//form//input[@formcontrolname='companyName']")
	private WebElement consigneeCompanyName;

	@FindBy(xpath = "//label[@for='consignee-input']/div")
	private WebElement consigneeCheckBox;

	@FindBy(css = "[class*='dialog-container']")
	private WebElement saveToAddressPopup;

	@FindBy(xpath = "//h6[contains(text(),'Shipper')]//ancestor::app-address-details//lib-inline-button")
	private WebElement shipperAddressBook;

	@FindBy(tagName = "address-selector-dialog")
	private WebElement addressBookPopup;

	@FindBy(xpath = "//button[text()='Close']")
	private WebElement closeButton;

	@FindBy(css = "[class*='no-results']")
	private WebElement noResult;

	@FindBy(css = "[class*='expanded']")
	private WebElement expandFilerOption;
	
	// added for class method fordp2-1980
	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipmentClass0']//div[@class='mat-select-value']") 
	private WebElement weClass;

	// added for a missing step for QZ-7594
	// My Role
	@FindBy(how = How.XPATH, using = "//mat-select[@id='role']//div[@class='mat-select-arrow-wrapper']") 
	private WebElement weMyRole;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Bill of Lading'] [@class='mat-icon-button']")
	private WebElement edit_icon;

	@FindBy(xpath = "//h6[contains(text(),'Consignee')]//ancestor::app-address-details//lib-inline-button")
	private WebElement consigneeAddressBook;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'BOL Templates')]")
	// @FindBy(how = How.XPATH, using ="//*[@ng-reflect-router-link='templates']")
	private WebElement bolTemplateLink;

	@FindBy(css = "app-pickup-request [formcontrolname='phoneExt']")
	private WebElement pickupRequestPhoneExt;

	@FindBy(css = "[class*='mat-select-content'] mat-option span")
	private List<WebElement> pickupRequestRole;

	@FindBy(css = "mat-row:nth-child(2) mat-cell:nth-child(1)>a")
	private WebElement firstAddress;

	@FindBy(id = "addressSelect")
	private WebElement accountNumber;

	@FindBy(css = "[class*='mat-option-text']")
	private WebElement optionText;

	@FindBy(xpath = "//mat-checkbox[@id='allAccessorials27']")
	private WebElement liftGateServicePickUp;

	@FindBy(css = "[formcontrolname='codRemitTo'] [class*='toggle-bar']")
	private WebElement codRemitToToggle;

	@FindBy(css = "[formcontrolname='paymentType']")
	private WebElement paymentType;

	@FindBy(css = "[formcontrolname='codRemitToFee']")
	private WebElement feeToBePaid;

	@FindBy(css = "[formcontrolname='amount']")
	private WebElement amountToBePaid;

//	@FindBy(css = "[ng-reflect-header-title='Remit To Info'] div:nth-child(2) label>div")
//	private WebElement useShipperInfoAbove;
	
	@FindBy(xpath = ".//*[contains(text(),'Shipper')]/ancestor::label//div[contains(@class,'inner')]")
	private WebElement useShipperInfoAbove;

	@FindBy(xpath = "//h6[contains(text(),'Remit')]//following::input[@formcontrolname='companyName']")
	private WebElement remitToCompanyName;
	
	@FindBy(xpath = "//h6[contains(text(),'Remit')]//following::input[@formcontrolname='address1']")
	private WebElement remitToAddressLine1;

	@FindBy(xpath = "//*[contains(text(),'Total Commodities')]")
	private WebElement totalCommodities;

	@FindBy(xpath = "//*[contains(text(),'Total Shipment')]")
	private WebElement totalShipmentWeight;

	@FindBy(css = "[formcontrolname='acceptedNotify']")
	private WebElement acceptedCheckBox;

	@FindBy(css = "[formcontrolname='completedNotify']")
	private WebElement completedCheckBox;

	@FindBy(css = "[formcontrolname='rejectedNotify']")
	private WebElement rejectedCheckBox;

	@FindBy(css = "#main mat-card-content > div:nth-child(4) > div label")
	private List<WebElement> bolSummaryLabels;

	@FindBy(css = "#main div:nth-child(6) label")
	private List<WebElement> shippingSummaryLabel;

	@FindBy(xpath = "//*[text()='Contact Name']//parent::div")
	private WebElement summaryContactName;

	@FindBy(xpath = "//*[text()='Phone']//parent::div")
	private WebElement summaryPhoneNumber;

	@FindBy(xpath = "//*[text()='Email']//parent::div")
	private WebElement summaryEmailAddress;

	@FindBy(xpath = "//*[text()='Role']//parent::div")
	private WebElement summaryRole;

	@FindBy(xpath = "//*[text()='Pickup Date']//parent::div")
	private WebElement summaryPickupDate;

	@FindBy(xpath = "//*[text()='Available By']//parent::div")
	private WebElement summaryAvailableBy;

	@FindBy(xpath = "//*[text()='Closes By']//parent::div")
	private WebElement summaryClosesBy;

	@FindBy(xpath = "//*[text()='Pickup Notifications']//parent::div")
	private WebElement summaryPickupNotification;

	@FindBy(xpath = "//*[text()='Services']//parent::div")
	private WebElement summaryServices;

	@FindBy(xpath = "//*[text()='Pickup Accessorials']//parent::div")
	private WebElement summaryPickUpAccessorials;

	@FindBy(xpath = "//*[text()='Pieces Type']//parent::div")
	private WebElement summaryPiecesType;

	@FindBy(xpath = "//*[text()='Weight']//parent::div")
	private WebElement summaryWeight;

	@FindBy(xpath = "//*[text()='Pieces']//parent::div")
	private WebElement summaryPieces;

	@FindBy(xpath = "//*[text()='Hazmat']//parent::div")
	private WebElement summaryHazmat;

	@FindBy(xpath = "//*[text()='Pickup Request Number']//parent::div")
	private WebElement summaryPickupRequestNo;

	@FindBy(id = "contactName")
	private WebElement hazmatContactName;

	@FindBy(css = "[for='hazmat0-input']>div")
	private WebElement hazmatCheckbox;

//	@FindBy(css = "[ng-reflect-header-title='Remit To Info'] div:nth-child(1) label>div")
//	private WebElement useMyEstesAccount;

	@FindBy(xpath = "//*[contains(text(),'Remit To')]/parent::lib-form-header//following-sibling::div//label[@class='mat-checkbox-layout']//span[contains(text(),'My Estes')]")
	private WebElement useMyEstesAccount;
	
	@FindBy(id = "phone")
	private WebElement hazmatPhoneNumber;

	@FindBy(id = "otherEmail")
	private WebElement otherEmail;

	@FindBy(xpath = "//*[text()='Description']//parent::div")
	private WebElement summaryDescription;

	@FindBy(css = "[formcontrolname='hasShippingLabel'] [class='mat-slide-toggle-bar']")
	private WebElement printShippingLabels;

	@FindBy(css = "[aria-required='true'][class*='ng-invalid']")
	private List<WebElement> requiredFields;

	@FindBy(css = "[aria-describedby*='mat-error']")
	private List<WebElement> requiredFieldErrors;

//	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create One Now')]")
//	private WebElement createOneNew;

	@FindBy(how = How.XPATH, using = "//button[@type='button'][contains(text(),'Clear')]")
	private WebElement weClearButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Save this BOL as template to use again')]")
	private WebElement saveThisBOL;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'COD Remit-To')]")
	private WebElement codRemitTo;

	@FindBy(how = How.XPATH, using ="//*[@class='mat-tab-label-content'][contains(text(),' Create BOL ')]")
	private WebElement CreateBOLLink;
	
	@FindBy(xpath="//label[@for='thirdParty-input']/div")
	private WebElement thirdPartyCheckBox;
	
	@FindBy(xpath="//h6[contains(text(),'Bill')]//ancestor::app-address-details//input[@formcontrolname='companyName']")
	private WebElement billToInfoCompanyName;

	@FindBy(xpath="//h6[contains(text(),'Bill')]//ancestor::app-address-details//lib-inline-button")
	private WebElement billToInfoAddressBook;

	@FindBy(xpath="//span[contains(text(),'Use Consignee Info above')]")
	private WebElement useConsigneeInfoAbove;

	@FindBy(xpath="//h6[contains(text(),'Remit')]//ancestor::app-address-details//lib-inline-button")
	private WebElement remitToInfoAddressBook;
	
	@FindBy(how = How.ID, using ="description0")
	private WebElement description;
	
	@FindBy(css = "[class*='mat-datepicker-toggle-default-icon']")
	private WebElement datePickerIcon;
	
	@FindBy(css = "[class*='calendar-body-selected']")
	private WebElement currentDate;

	@FindBy(xpath = "//div[contains(@class,'today')]//parent::td[contains(@class,'calendar')]//following::td[1]")
	private WebElement futureDate;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary mt-3']")
    private WebElement createOneNew;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'The shipper city, state and ZIP code are currently')]")
    
    private WebElement errorMessage;
    
    @FindBy(how = How.XPATH, using = "//mat-checkbox[@id='mat-checkbox-3']/label/div")
	private WebElement weUseMyEstesAccInfoForThirdParty;
    
   

	/*********** METHODS ***********/
	// ####################### BOL Source #####################

	// Click On Blank VICS BOL Radio Button
	public void clickOnBVICSBolRadioButton() {
		logger.info("Click On Blank VICS BOL Radio Button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		//testUtil.setExplicitWait(weBVICSBolRadioButton, 30);
		testUtil.clickElementJavascript(weBVICSBolRadioButton);
	}
	
	public void clickOnBEstesBlankBOL() {
		logger.info("Click on blank estes BOL");
		testUtil.init(this);
		testUtil.moveTo(weBEstesBOL);
		weBEstesBOL.click();
	}

	// Click On Advanced Search Button
	public void ClickOnAdvSearchButton() {
		logger.info("Click On Advanced Search Button");
		testUtil.init(this);
		testUtil.setExplicitWait(weAdvSearchButton, 120);
		testUtil.clickElementJavascript(weAdvSearchButton);
		testUtil.setHardWait(1000);
		
	}

	// Click On Drafts Search By
	public void ClickOnSearchBy() {
		logger.info("Click On Search By");
		testUtil.init(this);
		testUtil.setExplicitWait(weAdvSearchBy, 20);
		testUtil.clickElementJavascript(weAdvSearchBy);
		// weAdvSearchBy.click();
	}

	// Select from Search By Option
	public void selectFromSearchByOption(String SelectSearchByOp) {
		logger.info("Select from Search By option");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("(//span[contains(text(),'" + SelectSearchByOp + "')])"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
	}

	// Enter Data for Search By Option
	public void enterSearchByOptionDataInContains(String SearchByOpData) {
		logger.info("Enter Data for  Search By Option");
		testUtil.init(this);
		testUtil.setExplicitWait(weContains, 20);
		weContains.clear();
		testUtil.setExplicitWait(weContains, 20);
		weContains.sendKeys(SearchByOpData);
	}

	// Get BOL Number
	public String getDataFromSearchBy() {
		logger.info("Get BOL number ");
		testUtil.init(this);
		//testUtil.setExplicitWait(weBOLReference,20);
		testUtil.setHardWait(2000);
		testUtil.moveTo(weBOLReference);
		testUtil.setExplicitWait(weBOLReference, 60);
		String BOLNum = weBOLReference.getAttribute("value");
		testUtil.setHardWait(3000);
		System.out.println("BOL number is :" + BOLNum);
		return BOLNum;
		
	}

	public void verifyNoResultMessageDisplayes() {
		logger.info("Verify No Result for the given criteria message displays");
		testUtil.init(this);
		testUtil.setExplicitWait(noResult, 20);
		boolean existence = noResult.isDisplayed();
		TestUtil.verifyTrue(existence);
	}

	public void enterDraftStartDate(String date) {
		logger.info("Enter Start Date");
		testUtil.init(this);
		testUtil.setExplicitWait(weStartDate, 20);
		weStartDate.sendKeys(date);
	}

	public void enterDraftEndDate(String date) {
		logger.info("Enter End Date");
		testUtil.init(this);
		testUtil.setExplicitWait(weEndDate, 20);
		weEndDate.sendKeys(date);
	}

	public void validateFilterOptionExpanded() {
		logger.info("Validate Filter Option expanded");
		testUtil.init(this);
		testUtil.setExplicitWait(expandFilerOption, 10);
		boolean expand = expandFilerOption.isDisplayed();
		TestUtil.verifyTrue(expand);

	}

	// ####################### BOL Details #####################

	// Click On Master BOL Toggle
	public void clickOnMasterBOLToggle() {
		logger.info("Click On Master BOL Toggle");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(weMasterBOL);
		//weMasterBOL.click();
		testUtil.setHardWait(1000);
	}

	// Enter Master BOL Number Toggle
	public void enterMasterBOLNumber(String MasterBOLNum) {
		logger.info("Enter Master BOL Number Toggle");
		testUtil.init(this);
		//weMasterBOLNum.click();
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(weMasterBOLNum);
		testUtil.setExplicitWait(weMasterBOLNum,60);
		weMasterBOLNum.clear();
		testUtil.setHardWait(2000);
		weMasterBOLNum.sendKeys(MasterBOLNum);
	}

	// Click On Auto-Assign PRO #
	public void clickOnAutoAssignPROToggle() {
		logger.info("Click On Auto-Assign PRO # Toggle");
		testUtil.init(this);
//		weAutoAssignPRO.click();
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(weAutoAssignPRO);
		testUtil.setHardWait(1000);
	}


	public void clickOnGeneratePickupToggle() {
		logger.info("Click On generate pickup request Toggle");
		testUtil.init(this);
		generatePickup.click();
	}

	// Enter BOL Reference #
	public void enterBOLReferenceNumber(String BOLRefNum) {
		logger.info("Enter Master BOL Number number");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		weBOLReference.sendKeys(BOLRefNum);
	}

	public void enterContactName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		contactName.sendKeys(name);
	}

	public void enterEmail(String emailadd) {
		logger.info("Enter email");
		testUtil.init(this);
		email.click();
		email.sendKeys(emailadd);
	}

	public void enterPhone(String phno) {
		logger.info("Enter email");
		testUtil.init(this);
		phone.sendKeys(phno);
	}

	public void verifyProNumberFieldIsDisplayed() {
		logger.info("Verify Pro number field is displayed");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Assert.assertEquals(reservedPronumber.isEnabled(), true);
	}

	public void verifyPageTitle() {
		logger.info("Verify page title");
		testUtil.init(this);
		String actual = driver.getTitle();
		testUtil.setHardWait(1000); 
		System.out.println(actual);
		Assert.assertEquals(actual, "My Estes: Bill of Lading");
	}

	// ####################### Quote Details #####################

	// ####################### Pickup Request #####################

	public void clickOnPickupRequestToggle() {
		logger.info("Clicking on Pickup Request Toggle");
		testUtil.init(this);
		pickupRequestToggle.click();
	}

	public void enterPickupRequestContactName(String name) {
		logger.info("Entering Pickup Request Contact name: " + name);
		testUtil.init(this);
		testUtil.setExplicitWait(pickupRequestContactName, 30);
		pickupRequestContactName.sendKeys(name);
	}

	public void enterPickupRequestEmailAddress(String email) {
		logger.info("Entering Pickup Request Email Address: " + email);
		testUtil.init(this);
		pickupRequestEmail.click();
		pickupRequestEmail.sendKeys(email);
	}

	public void enterPickupRequestPhoneNumber(String phone) {
		logger.info("Entering Pickup Request Phone number: " + phone);
		testUtil.init(this);
		pickupRequestPhoneNumber.sendKeys(phone);
	}

	public void clickOnPickupRole() {
		logger.info("Click on Pickup Role");
		testUtil.init(this);
		pickupRole.click();
	}

	public void selectPickupRequestConsigneeRole() {
		logger.info("Clicking on Pickup request consignee role");
		testUtil.init(this);
		clickOnPickupRole();
		testUtil.setExplicitWait(consigneeRole, 30);
		consigneeRole.click();
	}

	/*
	 * public void enterPickupDate() {
	 * logger.info("Entering today date in Pickup Date"); testUtil.init(this);
	 * DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	 * LocalDate localDate = LocalDate.now();
	 * pickupDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	 * testUtil.setHardWait(1000);
	 * pickupDate.sendKeys(dateFormat.format(localDate)); }
	 */

	// ####################### Accessorials #####################
	// Click On Inside Delivery Radio Button
	public void clickOnInsideDeliveryRadioButton() {
		logger.info("Click On Inside Delivery Radio Button");
		testUtil.init(this);
		weInsideDeliveryRadioButton.click();
	}

	// ####################### Shipper and Consignee Details #####################
	// Click On Use My Estes Account info For Shipper
	public void clickOnUseMyEstesAccInfoForShipper() {
		logger.info("Click On Use My Estes Account info For Shipper");
		testUtil.init(this);
		weUseMyEstesAccInfoForShipper.click();
	}

	// Enter Address Line1 for Shipper
	public void enterAddrLine1ForShipper(String AddForLine1) {
		logger.info(" Enter Address Line1 for Shipper");
		testUtil.init(this);
		weAddrLine1ForShipper.sendKeys(AddForLine1);
	}

	// Enter City For Shipper
	public void enterCityForShipper(String CityName) {
		logger.info("Enter City For Shipper");
		testUtil.init(this);
		weCityForShipper.sendKeys(CityName);
	}

	// Click On State For Shipper
	public void clickOnStateForShipper() {
		logger.info("Click On State For Shipper");
		testUtil.init(this);
		weStateForShipper.click();
	}

	// Enter State Name for Shipper
	public void enterStateNameForShipper(String StateName) {
		logger.info("Enter State Name For Shipper");
		testUtil.init(this);
		WebElement stateEle = driver.findElement(By.xpath("(//*[contains(text(),'" + StateName + "')])[1]"));
		testUtil.setExplicitWait(stateEle, 20);
		stateEle.click();
		// driver.findElement(By.xpath("(//*[contains(text(),'" + StateName +
		// "')])[1]")).click();
		// weEnterStateNameForShipper.sendKeys(StateName);
	}

	// Enter ZIP Code For Shipper
	public void enterZIPCodeForShipper(String Zipcode) {
		logger.info("Enter ZIP Code For Shipper");
		testUtil.init(this);
		weZIPCodeForShipper.sendKeys(Zipcode);
	}

	// Click On Use My Estes Account info For Consignee
	public void clickOnUseMyEstesAccInfoForConsignee() {
		logger.info("Click On Use My Estes Account info for Consignee");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		weUseMyEstesAccInfoForConsignee.click();
	}

	// Enter Address Line1 for Consignee
	public void enterAddrLine1ForConsignee(String AddForLine1) {
		logger.info(" Enter Address Line1 for Consignee");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		weAddrLine1Forconsignee.sendKeys(AddForLine1);
	}

	// Enter City For Consignee
	public void enterCityForConsignee(String CityName) {
		logger.info("Enter City For Consignee");
		testUtil.init(this);
		weCityForConsignee.sendKeys(CityName);
	}

	// Click On State For Consignee
	public void clickOnStateForConsignee() {
		logger.info("Click On State For Consignee");
		testUtil.init(this);
		testUtil.setExplicitWait(weStateForConsignee, 20);
		testUtil.clickElementJavascript(weStateForConsignee);
		
	}

	// Enter ZIP Code For Consignee
	public void enterZIPCodeForConsignee(String Zipcode) {
		logger.info("Enter ZIP Code For Consignee");
		testUtil.init(this);
		weZIPCodeForConsignee.sendKeys(Zipcode);
	}

	// To enter Shipper company name
	public void enterShipperCompanyName(String companyNme) {
		logger.info("Entering company name " + companyNme);
		testUtil.init(this);
		companyName.clear();
		companyName.sendKeys(companyNme);
	}

	// To enter Shipper First name
	public void enterShipperFirstName(String firstNme) {
		logger.info("Entering First name " + firstNme);
		testUtil.init(this);
		firstName.sendKeys(firstNme);
	}

	// To enter Shipper Phone number
	public void enterShipperPhoneNumber(String phone) {
		logger.info("Entering Phone Number");
		testUtil.init(this);
		phoneNumber.sendKeys(phone);
	}

	// To enter Shipper email address
	public void enterShipperEmailAddress(String email) {
		logger.info("Entering Shipper email address");
		testUtil.init(this);
		emailAddress.clear();
		emailAddress.sendKeys(email);
	}

	// To select shipper state
	public void selectShipperState(String state) {
		logger.info("Selecting Shipper state: " + state);
		testUtil.init(this);
		weStateForShipper.click();
		driver.findElement(By.cssSelector("mat-option[role='option']")).sendKeys(state);

		WebElement ele = driver.findElement(By.xpath("//span[text()='" + state + "']"));
		Actions act = new Actions(driver);
		act.click(ele).perform();
	}

	public void enterShipperLastName(String name) {
		logger.info("Enter Shipper Last name: " + name);
		testUtil.init(this);
		shipperLastName.sendKeys(name);
	}

	public void enterShipperLocation(String location) {
		logger.info("Enter Shipper Location#: " + location);
		testUtil.init(this);
		shipperLocation.sendKeys(location);
	}

	public void enterShipperPhoneExt(String ext) {
		logger.info("Enter Shipper Phone ext: " + ext);
		testUtil.init(this);
		shipperPhoneExt.sendKeys(ext);
	}

	public void enterShipperFaxNumber(String fax) {
		logger.info("Enter Shipper Fax number: " + fax);
		testUtil.init(this);
		shipperFaxNumber.sendKeys(fax);
	}

	public void enterShipperAddressLine2(String address) {
		logger.info("Enter Shipper Address Line 2: " + address);
		testUtil.init(this);
		shipperAddressLine2.sendKeys(address);
	}

	public void clickOnShipperSaveToAddressBook() {
		logger.info("Click Shipper Save to Address book");
		testUtil.init(this);
		shipperSaveToAddressBook.click();
	}

	public void enterConsigneeFirstName(String name) {
		logger.info("Enter Consignee First name: " + name);
		testUtil.init(this);
		consigneeFirstName.sendKeys(name);
	}

	public void enterConsigneeLastName(String name) {
		logger.info("Enter Consignee Last name: " + name);
		testUtil.init(this);
		consigneeLastName.sendKeys(name);
	}

	public void enterConsigneeLocation(String location) {
		logger.info("Enter Consignee Location#: " + location);
		testUtil.init(this);
		consigneeLocation.sendKeys(location);
	}

	public void enterConsigneePhoneNumber(String number) {
		logger.info("Enter Consignee Phone: " + number);
		testUtil.init(this);
		consigneePhone.sendKeys(number);
	}

	public void enterConsigneePhoneExt(String ext) {
		logger.info("Enter Consignee Phone ext: " + ext);
		testUtil.init(this);
		consigneePhoneExt.sendKeys(ext);
	}

	public void enterConsigneeFaxNumber(String fax) {
		logger.info("Enter Consignee Fax number: " + fax);
		testUtil.init(this);
		consigneeFaxNumber.sendKeys(fax);
	}

	public void enterConsigneeEmailAddress(String email) {
		logger.info("Enter Consignee email address: " + email);
		testUtil.init(this);
		consigneeEmail.sendKeys(email);
	}

	public void enterConsigneeAddressLine1(String address) {
		logger.info("Enter Consignee Address Line 1: " + address);
		testUtil.init(this);
		consigneeAddressLine1.sendKeys(address);
	}

	public void enterConsigneeAddressLine2(String address) {
		logger.info("Enter Consignee Address Line 2: " + address);
		testUtil.init(this);
		consigneeAddressLine2.sendKeys(address);
	}

	public void clickOnConsigneeSaveToAddressBook() {
		logger.info("Click Shipper Save to Address book");
		testUtil.init(this);
		consigneeSaveToAddressBook.click();
	}

	public void clickShipperOnSaveAddressPopUp() {
		logger.info("Click Shipper check box in Save address book popup");
		testUtil.init(this);
		testUtil.setExplicitWait(shipperCheckBox, 60);
		shipperCheckBox.click();
	}

	public void clickSubmitSaveAddressPopup() {
		logger.info("Click submit on Save address book popup");
		testUtil.init(this);
		testUtil.setExplicitWait(submitAddressBookPopup, 20);
		submitAddressBookPopup.click();
	}

	public void verifyAddressAddedSuccessMessage() {
		logger.info("Verify address added success message");
		testUtil.init(this);
		testUtil.setExplicitWait(addAddressSuccessMessage, 20);
		boolean message = addAddressSuccessMessage.isDisplayed();
		TestUtil.verifyTrue(message);
	}

	public void enterConsigneeCompanyName(String companyName) {
		logger.info("Enter Consignee Company name: " + companyName);
		testUtil.init(this);
		consigneeCompanyName.clear();
		consigneeCompanyName.sendKeys(companyName);
	}

	public void clickConsigneeOnSaveAddressPopUp() {
		logger.info("Click Consignee check box in Save address book popup");
		testUtil.init(this);
		testUtil.setExplicitWait(consigneeCheckBox, 60);
		consigneeCheckBox.click();
	}

	public void enterStateNameForConsignee(String StateName) {
		logger.info("Enter State Name For Consignee");
		testUtil.init(this);
		WebElement stateEle = driver.findElement(By.xpath("(//*[contains(text(),'" + StateName + "')])[1]"));
		testUtil.setExplicitWait(stateEle, 20);
		stateEle.click();

	}

	public void validateSaveToAddressPopupExistence() {
		logger.info("Verifying Save to address popup existence");
		testUtil.init(this);
		testUtil.setExplicitWait(saveToAddressPopup, 20);
		boolean popupExist = saveToAddressPopup.isDisplayed();
		TestUtil.verifyTrue(popupExist);
	}

	public void clickOnShipperAddressBook() {
		logger.info("Click on Shipper Address Book icon");
		testUtil.init(this);
		testUtil.setExplicitWait(shipperAddressBook, 20);
		shipperAddressBook.click();
	}

	public void verifyAddressBookPopupIsOpen() {
		logger.info("Verifying Address Book Popup existence");
		testUtil.init(this);
		testUtil.setExplicitWait(addressBookPopup, 20);
		boolean popupExist = addressBookPopup.isDisplayed();
		TestUtil.verifyTrue(popupExist);
	}

	public void clickCloseOnAddressBook() {
		logger.info("Click close on Address Book");
		testUtil.init(this);
		closeButton.click();
	}

	// ####################### Billing Information #####################

	public void clickOnBillTo() {
		logger.info("Clicking on Bill To");
		testUtil.init(this);
		billTo.click();
	}

	public void selectShipperBillTo() {
		logger.info("Clicking on Shipper Bill To");
		testUtil.init(this);
		clickOnBillTo();
		testUtil.setExplicitWait(shipperBillTo, 30);
		shipperBillTo.click();
	}

	// ####################### Commodities #####################
	// Enter Handling Units
	public void enterHandlingUnits(String HandlingUnits) {
		logger.info("Enter Handling Units");
		testUtil.init(this);
		weHandlingUnits.sendKeys(HandlingUnits);
	}

	// Click On Type
	public void clickOnType() {
		logger.info("Click on Type");
		testUtil.init(this);
		testUtil.setExplicitWait(weClickOnType, 60);
		weClickOnType.click();
		testUtil.setHardWait(2000);

	}

	// Enter Type
	public void enterType(String Type) {
		logger.info("Enter Type");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		//testUtil.clickElementJavascript(driver.findElement(By.xpath("//*[contains(text(),'" + Type + "')]")));
		WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'" + Type + "')]"));
		testUtil.clickElementJavascript(ele);
		testUtil.setHardWait(2000);
		
		
		//*[@id="goodsType0"]/div/div[1]
		
	}

	// Enter Total Weight
	public void enterTotalWeight(String TotalWeight) {
		logger.info("Enter Total Weight");
		testUtil.init(this);
		weTotalWeight.sendKeys(TotalWeight);
	}

	// Click On Packaging Type
	public void ClickOnPackagesType() {
		logger.info("Click On Packaging Type");
		testUtil.init(this);
		weClickOnPackagesType.click();
	}

	// Enter Packaging Type
	public void enterPackagingType(String PackagingType) throws InterruptedException {
		logger.info("Enter Packaging Type");
		testUtil.init(this);
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+PackagingType+"')]"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
		// driver.findElement(By.xpath("(//*[contains(text(),'" + PackagingType +
		// "')])[1]")).click();
	}

	// Enter Number of Packages
	public void enterNumberOfPackages(String NumOfPackages) {
		logger.info("Enter Number of Packages");
		testUtil.init(this);
		weNumberOfPackages.sendKeys(NumOfPackages);
		testUtil.setHardWait(1000);
	}

	// Click On BOL Drafts Link
	public void ClickOnBOLDraftsLink() {
		logger.info("Click On BOL Drafts Link");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)");
		testUtil.init(this);
		testUtil.clickElementJavascript(weBOLDraftsLink);
		// weBOLDraftsLink.click();
	}

	public void selectRole(String role) {
		logger.info("Select Role as " + role);
		testUtil.init(this);
		prRole.click();
		WebElement ele = driver.findElement(By.xpath("//*[@class='mat-option-text'][contains(text(),'" + role + "')]"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
	}

	public void enterPickupDate(String pickupDate) {
		logger.info("Enter pickupdate");
		testUtil.init(this);
		prPickupDate.sendKeys(pickupDate);
	}

	// Verify City for shipper is auto-populated from rate quote
	public void verifyConsigneeCity(String city) {
		logger.info("Verify City for Consignee");
		testUtil.init(this);
		String actual = weCityForConsignee.getAttribute("value");
		Assert.assertEquals(actual, city);
	}

	// Verify State for shipper is auto-populated from rate quote
	public void verifyConsigneeState(String state) {
		logger.info("Verify State for Consignee");
		testUtil.init(this);
		String actual = weStateForConsignee.getText();
		Assert.assertEquals(actual, state);
	}

	// Verify Zip for shipper is auto-populated from rate quote
	public void verifyConsigneeZip(String zip) {
		logger.info("Verify Zip for Consignee");
		testUtil.init(this);
		String actual = weZIPCodeForConsignee.getAttribute("value");
		Assert.assertEquals(actual, zip);
	}

	// Click on Guaranteed Service toggle
	public void clickOnGuaranteedServiceToggle() {
		logger.info("Click on guaranteed service toggle");
		testUtil.init(this);
		testUtil.setExplicitWait(guaranteedServiceToggle, 90);
		guaranteedServiceToggle.click();
	}

	// Click on Terms Of Service Check box
	public void clickOnTermsOfService() {
		logger.info("Click on Terms Of Service");
		testUtil.init(this);
		termsOfService.click();
	}

	// SEARCH Button
	public void ClickOnSearchButton() {
		logger.info("Click On Search Button");
		testUtil.init(this);
		testUtil.setExplicitWait(weSearchButton, 60);
		testUtil.clickElementJavascript(weSearchButton);
		testUtil.setHardWait(3000);
       // testUtil.fluentWait(By.xpath("//mat-card-title[contains(text(),'Tracking Results')]"), 100, 5, "Tracking Results"); 
		
	}

	// Click On Edit Bill of Lading Icon
	public void clickOnEditBillOfLadingIcon(String ReferenceNum) {
		List<WebElement> we = driver.findElements(By.xpath("//*[@class='mat-table']/tbody/tr/td"));
		int listSize = we.size();
		for (int i = 0; i < listSize; i++) {
			String eleName = we.get(i).getText();
			if (eleName.equals(ReferenceNum)) {
				driver.findElement(By.xpath("//tr[2]//td[6]//div[1]//button[1]//span[1]//mat-icon[1]")).click();
				break;
			}
		}
	}

	public void clickOnEditBillOfLadingIcon() {
		logger.info("Click On Edit Icon");
		testUtil.setHardWait(2000);
		testUtil.WaitForTheElementClickable(edit_icon);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(edit_icon);
		testUtil.setHardWait(1000);

	}

	public void selectClass(String ClassNo) {
		logger.info("Enter class");
		testUtil.init(this);
		testUtil.clickElementJavascript(shipmentClass);
		WebElement classOptions = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + ClassNo + "')]"));
		testUtil.setExplicitWait(classOptions, 20);
		classOptions.click();
	}

	public void enterCommoditiesDescription(String desc) {
		logger.info("Enter Commodity description");
		testUtil.init(this);
		commodityDescription.sendKeys(desc);
	}

	// ####################### Reference Numbers #####################

	// ####################### Shipping Labels #####################

	// ####################### Notifications #####################

	public void enterShipperNotificationEmailAddress(String email) {
		logger.info("Entering Shipper email address");
		testUtil.init(this);
		shipperEmailId.sendKeys(email);
	}

	// ####################### Save BOL Template #####################

	// Click On Submit BOL
	public void ClickOnSubmitBOL() {
		logger.info("Click On Submit BOL Button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(weSubmitBOLButton);
		testUtil.fluentWait(By.xpath("(//h6[@class='form-header form-header--red'])[2]"), 200, 5, "SHIPPING SUMMARY"); 
		//testUtil.setHardWait(2000);
	}

	// Click On View Bill Of Lading Button
	public void ClickOnViewBillOfLadingButton() {
		logger.info("Click On View Bill Of Lading Button");
		testUtil.init(this);
		//weViewBillOfLadingButton.click();
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(weViewBillOfLadingButton);
		
	}

	// Click On Save as Draft Button
	public void ClickOnSaveAsDraftButton() {
		logger.info("Click On Save as Draft Button");
		testUtil.init(this);
		weSaveAsDraftButton.click();
	}

	// Enter Start Date
	public void enterStartDate(int NoOfAddOrSubtractDay) {
		logger.info("Enter Start Date");
		testUtil.init(this);
		String noOfDay = testUtil.addOrSubstractDateFromTodayDate(NoOfAddOrSubtractDay);
		weStartDate.sendKeys(noOfDay);
	}

	// Enter End Date
	public void enterEndDate() {
		logger.info("Enter End Date");
		testUtil.init(this);
		weEndDate.sendKeys(testUtil.todaysDate());
	}

	public void clickOnDeleteBOLDraft() {
		logger.info("Click BOL Draft delete icon");
		testUtil.init(this);
		testUtil.setExplicitWait(deleteBOLDraft, 10);
		deleteBOLDraft.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnConfirmDeleteBOLDraft() {
		logger.info("Click Confirm BOL Draft delete icon");
		testUtil.init(this);
		//testUtil.setHardWait(2000);
		testUtil.setExplicitWait(btnConfirm, 60);
		testUtil.clickElementJavascript(btnConfirm);
	}

	public void verifySuccessMessage(String expectedMessage) {
		logger.info("Verify success message displayed");
		String actual = message.getText().trim();
		testUtil.setExplicitWait(message, 120);
		//testUtil.setHardWait(3000);
		assertEquals(actual, expectedMessage);
	}

	public void ClickOnSaveTemplateToggleBar() {
		logger.info("Click On Save Templates Toggle bar Link");
		testUtil.init(this);
		testUtil.setExplicitWait(saveTemplate, 20);
		testUtil.clickElementJavascript(saveTemplate);
	}

	public void enterTemplateName(String name) {
		logger.info("Enter Template Name");
		testUtil.init(this);
		templateName.sendKeys(name);
	}

	public void clickOnSaveTemplateButton() {
		logger.info("Click Save template button icon");
		testUtil.init(this);
		testUtil.setExplicitWait(saveTemplateButton, 60);
		testUtil.clickElementJavascript(saveTemplateButton);
	}

	public void clickOnBOLTemplatesLink() {
		logger.info("Click On BOL Templates Link");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)");
		testUtil.init(this);
		testUtil.setExplicitWait(weBOLTemplatesLink, 60);
		testUtil.clickElementJavascript(weBOLTemplatesLink);
	
	}

	public void clickOnDeleteBOLTemplate() {
		logger.info("Click BOL template delete icon");
		testUtil.init(this);
		testUtil.setExplicitWait(deleteBOLTemplate, 120);
		deleteBOLTemplate.click();
	}

	public void clickOnSubmitBOLAndGeneratePickupRequest() {
		logger.info("Clickon Submit BOL & Generate Pickup Request button");
		testUtil.init(this);
		testUtil.setExplicitWait(submitBOL, 60);
		submitBOL.click();
	}

	public void validateAlertMessage(String message) {
		logger.info("Validat Alert message");
		testUtil.init(this);
		testUtil.setExplicitWait(errorMessage, 20);
		String alertMsg = errorMessage.getText().trim();
		Assert.assertTrue(alertMsg.contains(message));
        
	}


	public void clickBillTo() {
		logger.info("Click On bill");
		testUtil.init(this);
		testUtil.moveTo(clickOnBillTo);
		testUtil.clickElementJavascript(clickOnBillTo);
		//driver.findElement(By.xpath("//*[@id=mat-option-4]/span[contains(text(),' " + shipper + "')]")).click();
		//clickOnBillTo.click();
	}

	public void enterBillTo(String Shipper) throws InterruptedException {
		logger.info("Enter Type");
		testUtil.init(this);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Shipper')]")).click();

		
	
	}

	public void enterBillToThirdParty() throws InterruptedException {
		logger.info("Enter Type");
		testUtil.init(this);
		testUtil.assetWait("//span[@class='mat-option-text'][contains(text(),'Third Party')]", null, 10, 200, TimeUnit.MILLISECONDS);
		WebElement el=driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Third Party')]"));
		testUtil.clickElementJavascript(el);
	}

	public void clickTerms() {
		logger.info("Click On terms");
		testUtil.init(this);
		clickOnTerms.click();
	}

	public void enterTerms(String Prepaid) throws InterruptedException {
		logger.info("Enter Type");
		testUtil.init(this);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Prepaid')]")).click();
	}

	public void tempNameDisplayed(String tempName) {
		testUtil.init(this);
		logger.info("saved temptlet name ");
		driver.findElement(By.xpath("//td[contains(text(),'" + tempName + "')]")).isDisplayed();
	}

	public void clickOnBOLHistoryLink() {
		logger.info("Click On BOL History Link");
		testUtil.setHardWait(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)");
		testUtil.init(this);
		testUtil.clickElementJavascript(BOLHistoryLink);
	}

	public void clickOnCopyLink() {
		logger.info("Click On Copy link");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(BOLCopy);
		BOLCopy.click();
		testUtil.WaitForTextVisible(driver, driver.findElement(By.xpath("//*[contains(text(),'BOL Source')]")), "BOL Source");
	}

	public void enterRequesterName(String name) {
		logger.info("Enter Requester contact name");
		testUtil.init(this);
		prContactName.clear();
		prContactName.sendKeys(name);
	}

	// Enter email
	public void enterRequesterEmail(String email) {
		logger.info("Enter Requester email");
		testUtil.init(this);
		prEmail.clear();
		prEmail.sendKeys(email);
	}

	// Enter Phone Number
	public void enterRequesterPhoneNo(String phoneNumber) {
		logger.info("Enter Phone number");
		testUtil.init(this);
		prPhoneNumber.clear();
		prPhoneNumber.sendKeys(phoneNumber);
	}

	public void clearBOLReferenceNumber() {
		logger.info("Clear Master BOL Number Toggle");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		weBOLReference.clear();
	}

	public void selectBillTo(String billTo) {
		logger.info("Select Bill To as " + billTo);
		testUtil.init(this);
		//weBillTo.click();

		testUtil.clickElementJavascript(weBillTo);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + billTo + "')]")).click();
	}

	public void enterBOLDate() throws InterruptedException {
		logger.info("Enter BOL Date");
		testUtil.init(this);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String date = testUtil.todaysDate();
		js.executeScript("document.getElementById('mat-input-70').value='" + date + "';");
	}

	// Verify BOL Number displayed
	public void verifyBolSection() {
		logger.info("Verify BOL Section");
		testUtil.init(this);
		String bolNumber = driver
				.findElement(By.xpath("//*[@class='form-header form-header--red'][contains(text(),'BOL Number')]"))
				.getText();
		System.out.println(bolNumber);
	}

	// Verify Pickup Request section
	public void verifyPickupRequestSection() {
		logger.info("Verify Pickup Request section");
		testUtil.init(this);
		Assert.assertTrue(driver
				.findElement(By.xpath("//*[@class='form-header form-header--red'][contains(text(),'Pickup Request')]"))
				.isDisplayed(), "Verify Pickup Request section is displayed");

		// Requester info
		List<WebElement> reqCount = driver.findElements(By.xpath(
				"//*[@id=\"main\"]/app-bill-of-lading/app-confirmation/mat-card/mat-card-content/div[4]/div/div[1]/div[*]"));
		logger.info("Requester info");
		for (int i = 0; i < reqCount.size(); i++) {
			String actual = reqCount.get(i).getText();
			System.out.println(actual);
		}
	}

	// Click on Create New BOL button
	public void clickOnCreateNewBolButton() {
		logger.info("Click on Create New BOL button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(weCreateNewBOLButton);
	//	weCreateNewBOLButton.click();
	}

	// Verify Shipping Summary section
	public void verifyShippingSummarySection() {
		logger.info("Verify Shipping summary");
		testUtil.init(this);
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//*[@class='form-header form-header--red'][contains(text(),'Shipping Summary')]"))
				.isDisplayed(), "Verify Pickup Request section is displayed");

		// Shipper info
		List<WebElement> shipperCount = driver.findElements(By.xpath(
				"//*[@id=\"main\"]/app-bill-of-lading/app-confirmation/mat-card/mat-card-content/div[3]/div[1]/div[2]/div[*]"));
		logger.info("Shipper info");
		for (int i = 0; i < shipperCount.size(); i++) {
			String actual = shipperCount.get(i).getText();
			System.out.println(actual);
		}
	}

	public void clickOnAutoAssign() {
		logger.info("Click On Auto Assign");
		testUtil.init(this);
		testUtil.clickElementJavascript(autoAssignToggle);
		// autoAssignToggle.click();
		testUtil.setHardWait(2000);
	}

	public String getBOLNumber() throws InterruptedException {
		String bolNo = null;
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(bolNumber, 50);
		bolNo = bolNumber.getText().split(":")[1].trim();
		return bolNo;
	}

	public String getProNumber() {
		String proNo = null;
		proNo = proNumber.getText().replace("PRO #", "").trim();
		return proNo;
	}

	public void verifyBOLNumber(String bolNo) {
		boolean flag = false;
		for (WebElement e : lstBolNumber) {
			System.out.println(e.getText().trim());
			if (e.getText().trim().equalsIgnoreCase(bolNo)) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	public void verifyProNumber(String proNo) {
		boolean flag = false;
		for (WebElement e : lstProNumber) {
			if (e.getText().trim().equals(proNo)) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	public void selectRole() throws InterruptedException {
		logger.info("Select Role");
		testUtil.init(this);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		testUtil.clickElementJavascript(role);
		Thread.sleep(2000);
		testUtil.clickElementJavascript(shipper);
	}

	public void enterAccountNumber(String accNo) throws InterruptedException {
		logger.info("Enter account Number");
		testUtil.init(this);
		Thread.sleep(2000);
		accountNo.sendKeys(accNo);
	}

	public void enterPickupDate() throws InterruptedException {

		logger.info("Enter todays's date");
		testUtil.init(this);
		Thread.sleep(3000);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		pickupdate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(1000);
		pickupdate.sendKeys(dtf.format(localDate));

	}

	public void selectAccessorials(String access) {
		logger.info("select Accessorials");
		testUtil.init(this);
		driver.findElement(By.xpath("//label[span[contains(text(),'" + access + "')]]/div")).click();
	}

	public void enterFirstName(String name) throws InterruptedException {
		logger.info("Enter First Name");
		testUtil.init(this);
		Thread.sleep(2000);
		firstName.sendKeys(name);
		testUtil.setHardWait(500);
	}

	public void selectAddressInfo() {
		logger.info("select Address Info");
		testUtil.init(this);
		testUtil.clickElementJavascript(addressInfo);
		// addressInfo.click();
	}

	public void enterHandlingUnit(String unit) throws InterruptedException {
		logger.info("Enter Handling Unit");
		testUtil.init(this);
		Thread.sleep(2000);
		handlingUnit.sendKeys(unit);
	}

	public void selectType() throws InterruptedException {
		logger.info("Select Type");
		testUtil.init(this);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		testUtil.clickElementJavascript(handlingType);
		Thread.sleep(2000);
		testUtil.clickElementJavascript(handlingTypeValue);
	}

	// Check on Specified Service
	public void clickOnPickupService(String serviceName) {
		logger.info("Click on " + serviceName);
		testUtil.init(this);
		driver.findElement(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'" + serviceName + "')]"))
				.click();
	}

	// Check on specified accessorial
	public void clickOnPickupAccessorial(String accName) {
		logger.info("Click on " + accName);
		testUtil.init(this);
		driver.findElement(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'" + accName + "')]")).click();
	}

	// Click on Generate Pickup Request Toggle
	public void clickOnGeneratePickupRequestToggle() {
		logger.info("Click on Generate Pickup Request toggle");
		testUtil.init(this);
		pickupRequestToggle.click();
	}

	public void enterWeight(String unit) throws InterruptedException {
		logger.info("Enter Handling Weight");
		testUtil.init(this);
		Thread.sleep(2000);
		weight.sendKeys(unit);
	}

	public void enterShipperEmail(String emailadd) {
		logger.info("Enter Shipper email");
		testUtil.init(this);
		testUtil.setExplicitWait(shipperEmail, 60);
		shipperEmail.clear();
		shipperEmail.click();
		shipperEmail.sendKeys(emailadd);
	}

	public void clickOnAccessorial(String accName) {
		logger.info("Click on " + accName + " accessorial");
		testUtil.init(this);
		testUtil.moveTo(driver.findElement(By.xpath("//app-accessorials//*[contains(text(),'" + accName + "')]")));
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//app-accessorials//*[contains(text(),'" + accName + "')]")).click();
		testUtil.setHardWait(2000);
	}

	// Enter First name for shipper
	public void enterFirstNameForShipper(String firstName) {
		logger.info("Enter First name");
		testUtil.init(this);
		weFirstNameForShipper.clear();
		weFirstNameForShipper.sendKeys(firstName);
	}

	// Enter email for Shipper
	public void enterEmailForShipper(String email) {
		logger.info("Enter Email");
		testUtil.init(this);
		weEmailForShipper.clear();
		weEmailForShipper.sendKeys(email);
	}

	public void clickSubmit() throws InterruptedException {
		logger.info("Click Submit");
		testUtil.init(this);
		testUtil.clickElementJavascript(submit);
		Thread.sleep(5000);
	}

	// Capture quote number
	public String captureQuoteNo() {
		logger.info("Capture Quote number");
		testUtil.init(this);

		String quoteNo = quoteNumber.getAttribute("value");
		System.out.println("Quote# :" + quoteNo);
		return quoteNo;
	}

	// Verify City for shipper is auto-populated from rate quote
	public void verifyShipperCity(String city) {
		logger.info("Verify City for shipper");
		testUtil.init(this);
		String actual = weCityForShipper.getAttribute("value");
		Assert.assertEquals(actual, city);
	}

	// Verify State for shipper is auto-populated from rate quote
	public void verifyShipperState(String state) {
		logger.info("Verify State for shipper");
		testUtil.init(this);
		String actual = weStateForShipper.getText();
		Assert.assertEquals(actual, state);
	}

	// Verify Zip for shipper is auto-populated from rate quote
	public void verifyShipperZip(String zip) {
		logger.info("Verify Zip for shipper");
		testUtil.init(this);
		String actual = weZIPCodeForShipper.getAttribute("value");
		Assert.assertEquals(actual, zip);
	}

	public void enterShipperInfoEmail(String emailadd) {
		logger.info("Enter Shipper Info email");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		shipperInfoEmail.click();
		shipperInfoEmail.clear();
		shipperInfoEmail.sendKeys(emailadd);
	}

	public void verifyAlertMessage(String expectedMessage) {
		logger.info("verify alert message displayed");
		testUtil.setExplicitWait(alertMessage, 120);
		String actual = alertMessage.getText().trim();
		assertEquals(actual, expectedMessage);
	}

	public void verifyBOLNumberField() {
		logger.info("verify BOL Number Field displayed");
		assertEquals(bolNumberField.isDisplayed(), true);
	}

	public void verifyBOLDateField() {
		logger.info("verify BOL Date Field displayed");
		testUtil.setHardWait(1000);
		assertEquals(bolDateField.isDisplayed(), true);
	}

	public void verifyAccountField() {
		logger.info("verify Account Field displayed");
		assertEquals(accountField.isDisplayed(), true);
	}

	public void verifyPRONumberField() {
		logger.info("verify PRO Number Field displayed");
		assertEquals(proNoField.isDisplayed(), true);
	}

	public void verifyShipperSummaryField() {
		logger.info("verify Shipper Summary Field displayed");
		assertEquals(shipperSummaryField.isDisplayed(), true);
	}

	public void verifyShipperField() {
		logger.info("verify Shipper Field displayed");
		assertEquals(shipperField.isDisplayed(), true);
	}

	public void verifyShipperPhoneField() {
		logger.info("verify Shipper Phone Field displayed");
		assertEquals(shipperPhoneField.isDisplayed(), true);
	}

	public void verifyShipperEmailField() {
		logger.info("verify Shipper Email Field displayed");
		assertEquals(shipperEmailField.isDisplayed(), true);
	}

	public void verifyConsigneeField() {
		logger.info("verify Consignee Field displayed");
		assertEquals(consigneeField.isDisplayed(), true);
	}

	public void verifyConsigneePhoneField() {
		logger.info("verify Consignee Phone Field displayed");
		assertEquals(consigneePhoneField.isDisplayed(), true);
	}

	public void verifyConsigneeEmailField() {
		logger.info("verify Consignee Email Field displayed");
		assertEquals(consigneeEmailField.isDisplayed(), true);
	}

	public void verifyTotalCommoditiesField() {
		logger.info("verify Total Commodities Field displayed");
		assertEquals(totalCommoditiesField.isDisplayed(), true);
	}

	public void verifyTotalShipmentWeightField() {
		logger.info("verify Total Shipment Weight Field displayed");
		assertEquals(totalShipmentWeightField.isDisplayed(), true);
	}

	public void verifyTemplateNameExistence(String tempName) {
		testUtil.init(this);
		logger.info("Verifying Template Name: " + tempName);
		boolean existence = driver.findElement(By.xpath("//td[contains(text(),'" + tempName + "')]")).isDisplayed();
		TestUtil.verifyTrue(existence);
	}

	public void clickOnAccountSearchLink() {
		logger.info("Clicking on Account Search link");
		testUtil.init(this);
		accountSearch.click();
	}

	public void enterAccountDetails(String account) {
		logger.info("Enter Account details in search field");
		testUtil.init(this);
		accountSearchField.click();
		accountSearchField.clear();
		accountSearchField.sendKeys(account);
	}

	public void verifyAccountSearchHeader(String colName[]) {
		logger.info("Verifying Account Search Table Header column name");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		testUtil.setExplicitWait(accountSearchTableHeader.get(2),120);
		for (int i = 0; i < accountSearchTableHeader.size(); i++) {
			String columnHeader = accountSearchTableHeader.get(i).getText().trim();
			Assert.assertEquals(columnHeader, colName[i]);
		}
	}

	public void verifyAccountSearchResults(String details) {
		logger.info("Verifying Account Search table results");
		testUtil.init(this);
		if (searchRows.size() > 0) {
			for (int i = 0; i < accountSearchResults.size(); i++) {
				String searchResult = accountSearchResults.get(i).getText().trim();
				if (searchResult.contains(details)) {
					logger.info("Account details displays in Account search: " + details);
					break;
				}
			}
		} else {
			try {
				throw (new Exception(new String("Account details failed to display")));
			} catch (Exception e) {
			}
		}
	}

	public void verifyNoAccountsFoundDisplayed() {
		logger.info("Verifying No Accounts found displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(noAccountsFound, 20);
		boolean noAccount = noAccountsFound.isDisplayed();
		TestUtil.verifyTrue(noAccount);
	}

	public void clickOnUseTemplateMarkButton(String template) {
		logger.info("Clicking on Use Template tick mark button");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		for (int i = 1; i < templateRows.size(); i++) {
			String tempName = testUtil.getWebElement(templateRows.get(i), "td:nth-child(1)").getText().trim();
			if (tempName.equalsIgnoreCase(template)) {
				useTemplate.get(i - 1).click();
				break;
			}
		}
	}

	public void verifyCreateBOLTabIsActive() {
		logger.info("Verifying Create BOL tab is active");
		testUtil.init(this);
		testUtil.setExplicitWait(createBOLTab, 20);
		String classProp = createBOLTab.getAttribute("class");
		Assert.assertTrue(classProp.contains("active"));
	}

	public void validateBOLCreatedMessage() {
		logger.info("Validating Bill of Landing has been created message existence");
		testUtil.init(this);
		testUtil.setExplicitWait(bolCreatedMessage, 120);
		boolean exist = bolCreatedMessage.isDisplayed();
		TestUtil.verifyTrue(exist);
	}

	public void enterClass(String ClassNo) throws InterruptedException {
		logger.info("Enter Class");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(weClass);
		// weClass.click();
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + ClassNo + "')]")).click();

	}

	public void enterMyRole(String MyRole) {
		logger.info("Select Role");
		testUtil.init(this);
		testUtil.clickElementJavascript(weMyRole);
		WebElement role = driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'" + MyRole + "')]"));
		testUtil.setExplicitWait(role, 20);
		testUtil.clickElementJavascript(role);
	}

	
	public void clickOnConsigneeAddressBook() {
		logger.info("Click on Consignee Address Book icon");
		testUtil.init(this);
		testUtil.setExplicitWait(consigneeAddressBook, 20);
		consigneeAddressBook.click();
	}

	// Copied method for QZ-7582
	// Capture error message
	public void captureAndVerifyErrMsg() {
		logger.info("Verify required field error message");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		Assert.assertEquals(
				//driver.findElement(By.xpath("//app-templates-bol//mat-form-field/div/div[*]/div/mat-error")).getText(),	"This field is required.");
				//Modified object property to use in QZ-7606,QZ-7579 as well - Ajitha - 12/05/2019
				driver.findElement(By.xpath("//mat-form-field/div/div[*]/div/mat-error")).getText(),"This field is required."); 
	}

	// Copied method for QZ-7582
	// Select search By in BOL Template
	public void selectSearchBy(String searchBy) {
		logger.info("Select search by as " + searchBy);
		testUtil.init(this);

		// Click on search by
		weAdvSearchBy.click();
		testUtil.setExplicitWait(
				driver.findElement(By.xpath("//*[@class='mat-option-text'] [contains(text(),'" + searchBy + "')]")),
				20);
		driver.findElement(By.xpath("//*[@class='mat-option-text'] [contains(text(),'" + searchBy + "')]")).click(); //Added click event - Ajitha - 12/05/2019

	}

	// Click BOL Templates link
	public void clickOnBolTemplatesLink() {
		logger.info("Click on BOL Templates");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		//testUtil.clickElementJavascript(bolTemplateLink);
		bolTemplateLink.click();			//Modified click event

	}

	// Copied from QZ7586

	public void verifyMasterBOLIsDisplayed() {
		logger.info("Verify Master BOL field is displayed when selecting 'Blank VICS BOL' type");
		testUtil.init(this);
		testUtil.setExplicitWait(weMasterBOLNum, 20);
		boolean masterBOL = weMasterBOLNum.isDisplayed();
		TestUtil.verifyTrue(masterBOL);
	}

	public void enterPickupRequestPhoneNumberExtension(String ext) {
		logger.info("Entering Pickup Request Phone number extension");
		testUtil.init(this);
		pickupRequestPhoneExt.sendKeys(ext);
	}

	public void verifyListOptionsInPickupRequestRole(String[] roleList) {
		logger.info("Verify list of Options in Pickup Request Role dropdown");
		testUtil.init(this);
		testUtil.setExplicitWait(pickupRequestRole.get(2), 20);
		if (pickupRequestRole.size() > 0) {
			for (int i = 0; i < pickupRequestRole.size(); i++) {
				String roles = pickupRequestRole.get(i).getText().trim();
				Assert.assertEquals(roles, roleList[i]);
			}
		}
	}

	public void selectRolesOnPickupRequest(String pickupRole) {
		logger.info("Select Pickup request roles");
		testUtil.init(this);
		for (int i = 0; i < pickupRequestRole.size(); i++) {
			String roles = pickupRequestRole.get(i).getText().trim();
			if (roles.equalsIgnoreCase(pickupRole)) {
				pickupRequestRole.get(i).click();
				break;
			}
		}
	}

	public void clickFirstAddressOnAddressBook() {
		logger.info("Click on First Address displayed on the address book");
		testUtil.init(this);
		testUtil.setExplicitWait(firstAddress, 120);
		testUtil.clickElementJavascript(firstAddress);
		
	}

	public void validatePickupRequestAccountNoIsPopulated() {
		logger.info("Validate pickup request account number is populated in drop down");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		// testUtil.setExplicitWait(accountSearchField, 20);
		accountNumber.click();
		testUtil.setExplicitWait(optionText, 10);
		boolean option = optionText.isDisplayed();
		Assert.assertTrue(option);
	}

	public void clickOnLiftGateServicePickup() {
		logger.info("Click on Lift Gate Service Pickup Accessorial");
		testUtil.init(this);
		liftGateServicePickUp.click();
	}

	public void clickOnCODRemitToToggle() {
		logger.info("Click on COD Remit to toggle bar");
		testUtil.init(this);
		codRemitToToggle.click();
	}

	public void clickOnPaymentType() {
		logger.info("Click on Payment type");
		testUtil.init(this);
		paymentType.click();
	}

	public void selectPaymentType(String paymentType) {
		logger.info("Select Payment type");
		testUtil.init(this);
		WebElement ele = driver
				.findElement(By.xpath("//span[@class='mat-option-text' and text()='" + paymentType + "']"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
	}

	public void clickOnFeeToBePaid() {
		logger.info("Click on Fee to be paid");
		testUtil.init(this);
		feeToBePaid.click();
	}

	public void selectFeeToBePaid(String role) {
		logger.info("Select Fee to be paid");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//span[@class='mat-option-text' and text()='" + role + "']"));
		testUtil.setExplicitWait(ele, 20);
		ele.click();
	}

	public void enterAmountToBePaid(String amount) {
		logger.info("Entering amoungt to be paid");
		testUtil.init(this);
		amountToBePaid.sendKeys(amount);
	}

	public void clickOnUseShipperInfoAboveCheckbox() {
		logger.info("Click on Use Shipper Info above checkbox");
		testUtil.init(this);
		useShipperInfoAbove.click();
	}

	public void verifyRemitToCompanyNameIsAutoFill() {
		logger.info("Verifying Remit to Company name field is auto populated");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String classProp = remitToCompanyName.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}

	public void verifyRemitToAddressLine1IsAutoFill() {
		logger.info("Verifying Remit to Address line 1 field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(remitToAddressLine1, 20);
		String classProp = remitToAddressLine1.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}

	public void validateTotalCommodities(String count) {
		logger.info("Validating total commodities count");
		testUtil.init(this);
		String commoditiesVal = totalCommodities.getText().trim();
		Assert.assertTrue(commoditiesVal.contains(count));
	}

	public void validateTotalShipmentWeight(String weight) {
		logger.info("Validating Total Shipment weight");
		testUtil.init(this);
		String weightVal = totalShipmentWeight.getText().trim();
		Assert.assertTrue(weightVal.contains(weight));
	}

	public void verifyAcceptedCheckboxIsDisplayed() {
		logger.info("Validating Accepted checkbox is displayed");
		testUtil.init(this);
		boolean exist = acceptedCheckBox.isDisplayed();
		Assert.assertTrue(exist);
	}

	public void verifyCompletedCheckboxIsDisplayed() {
		logger.info("Validating Completed checkbox is displayed");
		testUtil.init(this);
		boolean exist = completedCheckBox.isDisplayed();
		Assert.assertTrue(exist);
	}

	public void verifyRejectedCheckboxIsDisplayed() {
		logger.info("Validating Rejected checkbox is displayed");
		testUtil.init(this);
		boolean exist = rejectedCheckBox.isDisplayed();
		Assert.assertTrue(exist);
	}

	public void verifyRejectedCheckBoxIsChecked() {
		logger.info("Validating Rejected notify checkbox is checked");
		testUtil.init(this);
		String properties = rejectedCheckBox.getAttribute("class");
		Assert.assertTrue(properties.contains("mat-checkbox-checked"));
	}

	public void verifyBOLSummarySection(String[] labelText) {
		logger.info("Verify BOL summary section labels");
		testUtil.init(this);
		testUtil.setExplicitWait(bolSummaryLabels.get(2), 20);
		if (bolSummaryLabels.size() > 0) {
			for (int i = 0; i < bolSummaryLabels.size(); i++) {
				String label = bolSummaryLabels.get(i).getText().trim();
				Assert.assertEquals(label, labelText[i]);
			}
		}
	}

	public void verifyShippingSummarySection(String[] labelText) {
		logger.info("Verify Shipping summary section labels");
		testUtil.init(this);
		testUtil.setExplicitWait(shippingSummaryLabel.get(2), 20);
		if (shippingSummaryLabel.size() > 0) {
			for (int i = 0; i < shippingSummaryLabel.size() - 1; i++) {
				String label = shippingSummaryLabel.get(i).getText().trim();
				Assert.assertEquals(label, labelText[i]);
			}
		}
	}

	public void verifyContactNameFromBOLSummary(String contactName) {
		logger.info("Verify Contact Name from BOL summary page");
		testUtil.init(this);
		String name = summaryContactName.getText().trim();
		Assert.assertTrue(name.contains(contactName));
	}

	public void verifyPhoneNumberFromBOLSummary(String phoneNo) {
		logger.info("Verify Phone number from BOL summary page");
		testUtil.init(this);
		String phone = summaryPhoneNumber.getText().trim();
		Assert.assertTrue(phone.contains(phoneNo));
	}

	public void verifyEmailAddressFromBOLSummary(String emailAddress) {
		logger.info("Verify Email address from BOL summary page");
		testUtil.init(this);
		String email = summaryEmailAddress.getText().trim();
		Assert.assertTrue(email.contains(emailAddress));
	}

	public void verifyRoleFromBOLSummary(String roleName) {
		logger.info("Verify Role from BOL summary page");
		testUtil.init(this);
		String role = summaryRole.getText().trim();
		Assert.assertTrue(role.contains(roleName));
	}

	public void verifyPickupDateFromBOLSummary(String pickupDate) {
		logger.info("Verify Pickup date from BOL summary page");
		testUtil.init(this);
		String date = summaryPickupDate.getText().trim();
		Assert.assertTrue(date.contains(pickupDate));
	}

	public void verifyAvailableByFromBOLSummary(String aTime) {
		logger.info("Verify Available By from BOL summary page");
		testUtil.init(this);
		String time = summaryAvailableBy.getText().trim();
		Assert.assertTrue(time.contains(aTime));
	}

	public void verifyClosesByFromBOLSummary(String cTime) {
		logger.info("Verify Closes By from BOL summary page");
		testUtil.init(this);
		String time = summaryClosesBy.getText().trim();
		Assert.assertTrue(time.contains(cTime));
	}

	public void verifyPickupNotificationFromBOLSummary(String notification) {
		logger.info("Verify Pickup Notification from BOL summary page");
		testUtil.init(this);
		String label = summaryPickupNotification.getText().trim();
		Assert.assertTrue(label.contains(notification));
	}

	public void verifyServicesFromBOLSummary(String service) {
		logger.info("Verify Services from BOL summary page");
		testUtil.init(this);
		String serv = summaryServices.getText().trim();
		Assert.assertTrue(serv.contains(service));
	}

	public void verifyPickupAccessorialsFromBOLSummary(String accessorials) {
		logger.info("Verify Pickup Accessorials from BOL summary page");
		testUtil.init(this);
		String acc = summaryPickUpAccessorials.getText().trim();
		Assert.assertTrue(acc.contains(accessorials));
	}

	public void verifyPiecesFromBOLSummary(String pieces) {
		logger.info("Verify Pieces from BOL summary page");
		testUtil.init(this);
		String pieceCount = summaryPieces.getText().trim();
		Assert.assertTrue(pieceCount.contains(pieces));
	}

	public void verifyPiecesTypeFromBOLSummary(String piecesType) {
		logger.info("Verify Pieces Type from BOL summary page");
		testUtil.init(this);
		String type = summaryPiecesType.getText().trim();
		Assert.assertTrue(type.contains(piecesType));
	}

	public void verifyWeightFromBOLSummary(String weight) {
		logger.info("Verify Pieces Weight from BOL summary page");
		testUtil.init(this);
		String pieceWeight = summaryWeight.getText().trim();
		Assert.assertTrue(pieceWeight.contains(weight));
	}

	public void verifyHazmatFromBOLSummary(String status) {
		logger.info("Verify Hazmat status from BOL summary page");
		testUtil.init(this);
		String hazmatStatus = summaryHazmat.getText().trim();
		Assert.assertTrue(hazmatStatus.contains(status));
	}

	public String capturePickupRequestNoFromBOLSummary() {
		logger.info("Capturing Pickup Request number from BOL Summary page");
		testUtil.init(this);
		String reqNo = summaryPickupRequestNo.getText().trim();
		return reqNo;
	}

	// UPDATE FOR QZ-7588 SCRIPT ===>
	public void enterHazmatContactName(String name) {
		logger.info("Enter Hazmat Contact name");
		testUtil.init(this);
		hazmatContactName.sendKeys(name);
	}

	public void clickOnHazmatCheckbox() {
		logger.info("Click on Hazmat checkbox");
		testUtil.init(this);
		testUtil.setExplicitWait(hazmatCheckbox, 20);
		hazmatCheckbox.click();
	}

	public void clickOnUseMyEstesAccountCheckbox() {
		logger.info("Click on use My Estes account checkbox");
		testUtil.init(this);
		useMyEstesAccount.click();
	}

	public void enterHazmatPhoneNumber(String phone) {
		logger.info("Enter Hazmat Phone number");
		testUtil.init(this);
		hazmatPhoneNumber.sendKeys(phone);
	}

	public void enterOtherNotificationEmail(String email) {
		logger.info("Enter Other email address in notification section");
		testUtil.init(this);
		otherEmail.sendKeys(email);
	}

	public void verifyCommodityDescriptionFromBOLSummary(String desc) {
		logger.info("Verify Commodity description from BOL summary page");
		testUtil.init(this);
		String description = summaryDescription.getText().trim();
		Assert.assertTrue(description.contains(desc));
	}
	// END OF UPDATE FOR QZ- 7588 <===

	// UPDATE FOR QZ-7595 ===>

	public void clickOnPrintShippingLabelstoggle() {
		logger.info("Click on Print shipping labels toggle");
		testUtil.init(this);
		printShippingLabels.click();
	}

	public int captureTotalNoOfRequiredFields() {
		logger.info("Capturing total number of required fields in Bill of Landing page");
		testUtil.init(this);
		int requiredFieldCount = requiredFields.size();
		return requiredFieldCount;
	}

	public int captureTotalNoOfRequiredFieldErrors() {
		logger.info("Capturing total number 'Fields are required' error message in Bill of Landing page");
		testUtil.init(this);
		testUtil.setExplicitWait(requiredFieldErrors.get(2), 20);
		int requiredFieldErrorCount = requiredFieldErrors.size();
		return requiredFieldErrorCount;
	}

	// UPDATE FOR QZ-7595 END<===

	// UPDTE FOR QZ-7605,7602,7598,7599,7579,7606===>

	public void verifyExistenceOfTemplate(String templateName) {
		logger.info("Verify template " + templateName + " exist");
		testUtil.init(this);
		List<WebElement> li = driver.findElements(By.xpath("//table/tbody/tr"));
		boolean val = false;

		for (int i = 1; i <= li.size(); i++) {
			String actual = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText();
			if (actual.equals(templateName)) {
				val = true;
				System.out.println("Template " + templateName + " exist");
				break;
			} else {
				val = false;
			}
		}

		Assert.assertTrue(val);
	}

	public void verifyAndDeleteExistingBOLTemplate() {
		logger.info("Verify existence of 'Create one now' button");
		testUtil.init(this);
		boolean val;

		try {
			createOneNew.isDisplayed();
			val = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			val = false;
		}

		if (val == false) {
			List<WebElement> templteTable = driver.findElements(By.xpath("//table/tbody/tr"));

			for (int i = 1; i <= templteTable.size(); i++) {
				driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[7]//button[3]")).click();
				testUtil.setHardWait(2000);
				clickOnConfirmDeleteBOLDraft();
			}
		}

	}

	public void clickOnCreateOneNewButton() {
		logger.info("Click on create one new button");
		testUtil.init(this);
		testUtil.setExplicitWait(createOneNew, 60);
		testUtil.WaitForTheElementClickable(createOneNew);
		testUtil.clickElementJavascript(createOneNew);

	}
	
	// Click on Clear Button
	public void ClickOnClearButton() {
		logger.info("Click On Clear Button");
		testUtil.init(this);
		testUtil.setExplicitWait(weClearButton, 20);
		weClearButton.click();
		testUtil.setHardWait(500);
	}

	public void clickOnSaveThisTemplateToggle() {
		logger.info("Click on Save this BOL as template");
		testUtil.init(this);
		saveThisBOL.click();
	}

	public void clickOnCodRemitTo() {
		logger.info("Clicking  on COD Remit To");
		testUtil.init(this);
		codRemitTo.click();
	}

	public void clickOnHazmat() {
		logger.info("Click on Hazmat");
		testUtil.init(this);
		driver.findElement(By.xpath("//span[contains(text(),'Hazmat')]")).click();
	}

	public void clickOnPrintShippingLabelsToggle() {
		logger.info("Click on print shipping labels");
		testUtil.init(this);
		printShippingLabels.click();
	}

	public void verifyReqFieldsOfBolDetailsSection() {
		logger.info("Verify Required fields of BOL Details section");
		testUtil.init(this);
		// Bol Reference Number
		Assert.assertEquals(weBOLReference.getAttribute("aria-invalid"), "true");
		// Master Bol#
		Assert.assertEquals(weMasterBOLNum.getAttribute("aria-invalid"), "true");
		System.out.println("Verified BOL details required fields");
	}

	public void verifyRequiredFieldsOfPickupDetails() {
		logger.info("Verify Required fields of Pickup Details Section");
		testUtil.init(this);
		// Contact name
		Assert.assertEquals(pickupRequestContactName.getAttribute("aria-invalid"), "true");
		// email
		Assert.assertEquals(pickupRequestEmail.getAttribute("aria-invalid"), "true");
		// Phone#
		Assert.assertEquals(pickupRequestPhoneNumber.getAttribute("aria-invalid"), "true");
		// Account number
		Assert.assertEquals(accountNumber.getAttribute("aria-invalid"), "true");
		// Pickup Date
		Assert.assertEquals(pickupDate.getAttribute("aria-invalid"), "true");
		System.out.println("Verified Pickup details required fields");
	}

	public void verifyReqFieldsOfShipperAndConsigneeDetails() {
		logger.info("Verify Required fields of Shipper details");
		testUtil.init(this);

		// Shipper contact name
		testUtil.moveTo(companyName);
		Assert.assertEquals(companyName.getAttribute("aria-invalid"), "true");
		// Consignee Contact name
		Assert.assertEquals(consigneeCompanyName.getAttribute("aria-invalid"), "true");
		// Shipper first name
		Assert.assertEquals(firstName.getAttribute("aria-invalid"), "true");
		// Shipper phone#
		Assert.assertEquals(phoneNumber.getAttribute("aria-invalid"), "true");
		// Shipper email
		Assert.assertEquals(emailAddress.getAttribute("aria-invalid"), "true");
		// Shipper address
		testUtil.moveTo(weAddrLine1ForShipper);
		Assert.assertEquals(weAddrLine1ForShipper.getAttribute("aria-invalid"), "true");
		// Shipper city
		testUtil.moveTo(weCityForShipper);
		Assert.assertEquals(weCityForShipper.getAttribute("aria-invalid"), "true");
		// Shipper State
		testUtil.moveTo(weStateForShipper);
		Assert.assertEquals(driver.findElement(By.id("state")).getAttribute("aria-invalid"), "true");
		// Shipper zip
		testUtil.moveTo(weZIPCodeForShipper);
		Assert.assertEquals(weZIPCodeForShipper.getAttribute("aria-invalid"), "true");
		System.out.println("Verified required fields of Shipper details section");
		// Consignee address
		testUtil.moveTo(weAddrLine1Forconsignee);
		Assert.assertEquals(weAddrLine1Forconsignee.getAttribute("aria-invalid"), "true");
		// Consignee City
		testUtil.moveTo(weCityForConsignee);
		Assert.assertEquals(weCityForConsignee.getAttribute("aria-invalid"), "true");
		// Consignee State
		testUtil.moveTo(weStateForConsignee);
		Assert.assertEquals(driver.findElement(By.id("state")).getAttribute("aria-invalid"), "true");
		// Consignee Zip
		testUtil.moveTo(weZIPCodeForConsignee);
		Assert.assertEquals(weZIPCodeForConsignee.getAttribute("aria-invalid"), "true");
		System.out.println("Verified required fields of Consignee details section");

	}

	public void verifyReqFieldsOfBillingInfoSection() {
		logger.info("Verify required fields of Billing info section");
		testUtil.init(this);
		// Terms
		testUtil.moveTo(driver.findElement(By.id("fee")));
		Assert.assertEquals(driver.findElement(By.id("fee")).getAttribute("aria-invalid"), "true");
		// Payment Type
		testUtil.moveTo(driver.findElement(By.id("paymentType")));
		Assert.assertEquals(driver.findElement(By.id("paymentType")).getAttribute("aria-invalid"), "true");
		// Fee to paid by
		testUtil.moveTo(driver.findElement(By.id("codRemitToFee")));
		Assert.assertEquals(driver.findElement(By.id("codRemitToFee")).getAttribute("aria-invalid"), "true");
		// Amount to be paid
		testUtil.moveTo(driver.findElement(By.xpath("//*[@formcontrolname='amount']")));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@formcontrolname='amount']")).getAttribute("aria-invalid"),
				"true");
		System.out.println("Verified required fields in Billing information section");
	}

	public void verifyReqFieldsOfCommoditySection() {
		logger.info("Verify required fields of commodity section");
		testUtil.init(this);
		// Handling units
		testUtil.moveTo(weHandlingUnits);
		Assert.assertEquals(weHandlingUnits.getAttribute("aria-invalid"), "true");
		// Type
		testUtil.moveTo(weClickOnType);
		Assert.assertEquals(driver.findElement(By.id("goodsType0")).getAttribute("aria-invalid"), "true");
		// Total weight
		testUtil.moveTo(weTotalWeight);
		Assert.assertEquals(weTotalWeight.getAttribute("aria-invalid"), "true");
		// packaging type
		testUtil.moveTo(weClickOnPackagesType);
		Assert.assertEquals(driver.findElement(By.id("packageType0")).getAttribute("aria-invalid"), "true");
		// # of packages
		testUtil.moveTo(weNumberOfPackages);
		Assert.assertEquals(weNumberOfPackages.getAttribute("aria-invalid"), "true");
		// Description
		testUtil.moveTo(driver.findElement(By.id("description0")));
		Assert.assertEquals(driver.findElement(By.id("description0")).getAttribute("aria-invalid"), "true");
		// Hazmat contact name
		testUtil.moveTo(driver.findElement(By.id("contactName")));
		Assert.assertEquals(driver.findElement(By.id("contactName")).getAttribute("aria-invalid"), "true");
		// Hazmat Phone number
		testUtil.moveTo(driver.findElement(By.id("phone")));
		Assert.assertEquals(driver.findElement(By.id("phone")).getAttribute("aria-invalid"), "true");
		System.out.println("Verified required fields of commodities section");
	}

	public void verifyReqFieldsOfNotificationSection() {
		logger.info("Verify required fields of notification section");
		testUtil.init(this);
		// Shipper email
		testUtil.moveTo(driver.findElement(By.id("shipperEmail")));
		Assert.assertEquals(driver.findElement(By.id("shipperEmail")).getAttribute("aria-invalid"), "true");
		System.out.println("Verified required fields of Notification section");
	}

	public void verifyReqFieldsOfSaveBolTemplateSection() {
		logger.info("Verify required fields of Save Bol Template section");
		testUtil.init(this);
		testUtil.moveTo(templateName);
		Assert.assertEquals(templateName.getAttribute("aria-invalid"), "true");
		System.out.println("Verified required fields of Save Bol Template section");
	}

	public void clickOnCreateBOLLink() {
		logger.info("Click On Create BOL Link");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)");
		testUtil.init(this);
	//	testUtil.clickElementJavascript(CreateBOLLink);
		CreateBOLLink.click();
	}

	public void enterBOLDate(String date) throws InterruptedException {
		logger.info("Enter BOL Date " + date);
		testUtil.init(this);
		testUtil.setHardWait(2000);
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
	//	js.executeScript("document.getElementById('mat-input-70').value='" + date +"';");
		bolDate.click();
		testUtil.setHardWait(1000);
		bolDate.sendKeys(Keys.CONTROL+"a");
		bolDate.sendKeys(Keys.DELETE);
		bolDate.sendKeys(date);
				
	}

	public void cancelBOL(String bolReference) {
		logger.info("Cancel BOL");
		testUtil.init(this);

		List<WebElement> li = driver.findElements(By.xpath("//table/tbody/tr"));
		for (int i = 1; i <= li.size(); i++) {
			String actual = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText();
			if (actual.equals(bolReference)) {
				driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[7]//button[3]")).click();
				testUtil.setHardWait(2000);
				verifyConfirmationMsgForBol(bolReference);     //Included method 
				clickOnConfirmDeleteBOLDraft();
				testUtil.setHardWait(2000);
				break;
			}
		}

	}

	//QZ-7596===>
	
	public void verifyConfirmationMsgForBol(String bolReference) {
		logger.info("Verify confirmation message");
		testUtil.init(this);
		String actual = driver.findElement(By.xpath("//lib-confirmation//p")).getText();
		if (actual.contains(bolReference)) {
			System.out.println("Confirmation message displayed");
		} else {
			System.out.println("Confirmation message not displayed");
		}

	}
	
	public void verifyBOL(String bolReference, boolean existense) {
		logger.info("Verify BOL");
		testUtil.init(this);
		
		List <WebElement> li = driver.findElements(By.xpath("//table/tbody/tr"));
		boolean val = false;
		for(int i = 1; i <= li.size(); i ++ ) {
			String actual = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[1]")).getText();
			if(actual.equals(bolReference)) {
				System.out.println(actual);
				val = true;
				break;
			}
		}
		Assert.assertEquals(val, existense);
	}
	
	public void clickThirdPartyOnSaveAddressPopUp() {
		logger.info("Click Third Party check box in save address book popup");
		testUtil.init(this);
		testUtil.setExplicitWait(thirdPartyCheckBox, 60);
		thirdPartyCheckBox.click();
	}

	public void verifyAddressBookPopupIsClose() {
		logger.info("Verify address book pop up existence");
		testUtil.init(this);
		testUtil.setHardWait(500);
		boolean popupExist = testUtil.isDisplayed(addressBookPopup);
		Assert.assertFalse(popupExist);
	}

	public void verifyShipperCompanyNameIsAutoFilled() {
		logger.info("Verify Shipper-Company name is auto filled");
		testUtil.init(this);
		String companyNameValue = companyName.getAttribute("value").trim();
		Assert.assertNotEquals(companyNameValue, "");
	}

	public void verifyConsigneeCompanyNameIsAutoFilled() {
		logger.info("Verify Consignee-Company name is auto filled");
		testUtil.init(this);
		String companyNameValue = consigneeCompanyName.getAttribute("value").trim();
		Assert.assertNotEquals(companyNameValue, "");
	}

	public void verifyThirdPartyAdditionalFieldsIsDisplayed() {
		logger.info("Validate Third Party additional fields is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(billToInfoCompanyName, 20);
		boolean exist = billToInfoCompanyName.isDisplayed();
		Assert.assertTrue(exist);
	}
	public void clickOnBillToInfoAddressBook() {
		logger.info("Click on Bill to Info address book");
		testUtil.init(this);
		billToInfoAddressBook.click();
	}

	public void verifyBillToCompanyNameIsAutoFilled() {
		logger.info("Verify Bill To Third Party-Company name is auto filled");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String companyNameValue = billToInfoCompanyName.getAttribute("value").trim();
		Assert.assertNotEquals(companyNameValue, "");
	}


	public void clickOnUseConsigneeInfoAboveCheckbox() {
		logger.info("Click on Use Consignee Info above checkbox");
		testUtil.init(this);
		useConsigneeInfoAbove.click();
	}
	public void remitToInfoAddressBook() {
		logger.info("Click on Remit to info address book");
		testUtil.init(this);
		remitToInfoAddressBook.click();
	}
	
	//QZ-7596 ENDS <===
	
	public void clickOnTermsOfServiceIfDisplayed() {
		logger.info("Click Terms of service check box if it is displayed");
		testUtil.init(this);
		if(testUtil.isDisplayed(termsOfService)) {
			//termsOfService.click();
			testUtil.clickElementJavascript(termsOfService);
		}
	}
	
	public void verifyCommodity(String commodity) {
		logger.info("Verify commodity details");
		testUtil.init(this);
		Assert.assertEquals(totalCommodities.getText(), "Total Commodities: "+commodity);
	}
	
	public void verifyTotalShipmentWeight(String totalShipment) {
		logger.info("Verify Total Shipment details");
		testUtil.init(this);
		Assert.assertEquals(totalShipmentWeight.getText(), "Total Shipment Weight: "+totalShipment+" lbs");
	}
	public void enterDescription(String desc) {
		logger.info("Enter description");
		testUtil.init(description);
		description.sendKeys(desc);
	}
	
	public void enterMinimumBOLDate() {
		logger.info("Enter Minimum/Current BOL date");
		testUtil.init(this);
		datePickerIcon.click();
		testUtil.setExplicitWait(currentDate, 20);
		currentDate.click();
	}
	
	public void enterPickupDateIfExist() throws InterruptedException {
		logger.info("Enter todays's date");
		testUtil.init(this);
		Thread.sleep(3000);
		if(testUtil.isDisplayed(pickupdate)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();
			pickupdate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(1000);
			pickupdate.sendKeys(dtf.format(localDate));
		}
		
	}
	
	public void enterFutureBOLDate() {
		testUtil.init(this);
		datePickerIcon.click();
		testUtil.setExplicitWait(futureDate, 20);
		futureDate.click();
	}

	public void clickAcceptedCheckbox() {
		logger.info("Click Accepted checkbox");
		testUtil.init(this);
		acceptedCheckBox.click();
	}
	
	public void clickCompletedCheckbox() {
		logger.info("click Completed checkbox");
		testUtil.init(this);
		completedCheckBox.click();
	}
	
	public void verifyQuoteNumberFieldIsAutoFill() {
		logger.info("Verifying Quote number field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(quoteNumber,20);
		String classProp = quoteNumber.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}
	
	public void verifyShipperZipCodeIsAutoFill() {
		logger.info("Verifying Shipper Zip code field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(weZIPCodeForShipper,20);
		String classProp = weZIPCodeForShipper.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}
	
	public void verifyConsigneeZipCodeIsAutoFill() {
		logger.info("Verifying Consignee Zip code field is auto populated");
		testUtil.init(this);
		testUtil.setExplicitWait(weZIPCodeForConsignee,20);
		String classProp = weZIPCodeForConsignee.getAttribute("class");
		Assert.assertTrue(classProp.contains("ng-valid"));
	}
	
	public void verifyProNumberFieldIsNotDisplayed() {
		logger.info("Verify Pro number field is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		boolean exist = testUtil.isDisplayed(reservedPronumber);
		assertFalse(exist);
	}
	
	// Click On Use My Estes Account info For Third Party
		public void clickOnUseMyEstesAccInfoForThirdParty() {
			logger.info("Click On Use My Estes Account info For Third Party");
			testUtil.init(this);
			weUseMyEstesAccInfoForThirdParty.click();
		}
	
		public void clickOnSortColumn(String colHeader) {
			logger.info("Click on Column header to Sort");
			testUtil.init(this);
			driver.findElement(By.xpath("//button[contains(text(),'"+colHeader+"')]")).click();
			testUtil.setHardWait(2000);
		}
		
		public void selectItemsPerPage(String items) {
			logger.info("Select Items per page");
			testUtil.init(this);
			driver.findElement(By.xpath("//*[contains(text(),'Items per page:')]/following::span[1]")).click();
			driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+items+"')]")).click();
			testUtil.setHardWait(2000);
		}
	
	
}
