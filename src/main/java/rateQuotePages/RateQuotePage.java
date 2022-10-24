package rateQuotePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.awaitility.Duration.*;

import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;


import static org.awaitility.Awaitility.*;


import com.github.javafaker.Faker;
import com.jagacy.Key;

import testBase.TestBase;
import util.TestUtil;

public class RateQuotePage extends TestBase {

	private Logger logger = Logger.getLogger(RateQuotePage.class);

	/*********** ELEMENTS ***********/

	@FindBy(how = How.XPATH, using = "//mat-select[@id='role']")
	private WebElement myRole;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='terms']")
	private WebElement terms;

	@FindBy(how = How.ID, using = "mat-input-0")
	private WebElement originZipField;

	@FindBy(how = How.ID, using = "mat-input-3")
	private WebElement destinationZipField;

	@FindBy(how = How.ID, using = "shipClass0")
	private WebElement selectClass;

	@FindBy(how = How.ID, using = "weight0")
	private WebElement totalWeightField;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	private WebElement submitButton;

	// Full Name
	@FindBy(how = How.ID, using = "contactName")
	private WebElement weFName;
	// Email
	@FindBy(how = How.ID, using = "contactEmail")
	private WebElement weEmail;
	// My Role
	@FindBy(how = How.XPATH, using = "//mat-select[@id='role']//div[@class='mat-select-arrow-wrapper']")
	private WebElement weMyRole;
	// Phone
	@FindBy(how = How.ID, using = "contactPhone")
	private WebElement wePhone;

	@FindBy(how = How.XPATH, using = "//div//*[@id='contactPhoneExt']")
	private WebElement extension;

	// Terms
	@FindBy(how = How.XPATH, using = "//mat-select[@id='terms']//div[@class='mat-select-arrow']")
	private WebElement weTerms;
	// Origin ZIP
	@FindBy(css = "[formcontrolname='origin'] [formcontrolname='zip']")
	private WebElement weOriginZip;
	// Destination ZIP
	@FindBy(css = "[formcontrolname='destination'] [formcontrolname='zip']")
	private WebElement weDesZip;
	// First Element from Drop Down
	@FindBy(how = How.ID, using = "pointlink0")
	private WebElement weFirstEleFDD;

	// Set today's day
	@FindBy(how = How.XPATH, using = "//input[@formcontrolname='pickupDate']")
	private WebElement weTodayDate;
	// Class
	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass0']//div[@class='mat-select-arrow']")
	private WebElement weClass;
	// Pieces
	@FindBy(how = How.ID, using = "pieces0")
	private WebElement wePieces;
	// Piece Type
	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType0']//div[@class='mat-select-arrow']")
	private WebElement wepieceType;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType0']//div[@class='mat-select-arrow']")
	private WebElement skidPieceType;

	// Total Weight
	@FindBy(how = How.ID, using = "weight0")
	private WebElement weTWeight;

	// Total Weight for second commodity
	@FindBy(how = How.ID, using = "weight1")
	private WebElement weTWeight1;

	// Length
	@FindBy(id = "length0")
	private WebElement weLength;

	// Width
	@FindBy(how = How.ID, using = "width0")
	private WebElement weWidth;
	// Height
	@FindBy(how = How.ID, using = "height0")
	private WebElement weHeight;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass1']//div[@class='mat-select-value']")
	private WebElement class1;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass2']//div[@class='mat-select-value']")
	private WebElement class2;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass3']//div[@class='mat-select-value']")
	private WebElement class3;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass4']//div[@class='mat-select-value']")
	private WebElement class4;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass5']//div[@class='mat-select-value']")
	private WebElement class5;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass6']//div[@class='mat-select-value']")
	private WebElement class6;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass7']//div[@class='mat-select-value']")
	private WebElement class7;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass8']//div[@class='mat-select-value']")
	private WebElement class8;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass9']//div[@class='mat-select-value']")
	private WebElement class9;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='shipClass10']//div[@class='mat-select-value']")
	private WebElement class10;

	// Pieces
	@FindBy(how = How.ID, using = "pieces1")
	private WebElement pieces;

	@FindBy(how = How.ID, using = "pieces2")
	private WebElement pieces2;

	@FindBy(how = How.ID, using = "pieces3")
	private WebElement pieces3;

	@FindBy(how = How.ID, using = "pieces4")
	private WebElement pieces4;

	@FindBy(how = How.ID, using = "pieces5")
	private WebElement pieces5;

	@FindBy(how = How.ID, using = "pieces6")
	private WebElement pieces6;

	@FindBy(how = How.ID, using = "pieces7")
	private WebElement pieces7;

	@FindBy(how = How.ID, using = "pieces8")
	private WebElement pieces8;

	@FindBy(how = How.ID, using = "pieces9")
	private WebElement pieces9;

	@FindBy(how = How.ID, using = "pieces10")
	private WebElement pieces10;

	// Piece Type
	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType1']//div[@class='mat-select-arrow']")
	private WebElement pieceType;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType2']//div[@class='mat-select-arrow']")
	private WebElement pieceType2;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType3']//div[@class='mat-select-arrow']")
	private WebElement pieceType3;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType4']//div[@class='mat-select-arrow']")
	private WebElement pieceType4;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType5']//div[@class='mat-select-arrow']")
	private WebElement pieceType5;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType6']//div[@class='mat-select-arrow']")
	private WebElement pieceType6;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType7']//div[@class='mat-select-arrow']")
	private WebElement pieceType7;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType8']//div[@class='mat-select-arrow']")
	private WebElement pieceType8;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType9']//div[@class='mat-select-arrow']")
	private WebElement pieceType9;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='pieceType10']//div[@class='mat-select-arrow']")
	private WebElement pieceType10;

	// Total Weight
	@FindBy(how = How.ID, using = "weight1")
	private WebElement weight1;

	@FindBy(how = How.ID, using = "weight2")
	private WebElement weight2;

	@FindBy(how = How.ID, using = "weight3")
	private WebElement weight3;

	@FindBy(how = How.ID, using = "weight4")
	private WebElement weight4;

	@FindBy(how = How.ID, using = "weight5")
	private WebElement weight5;

	@FindBy(how = How.ID, using = "weight6")
	private WebElement weight6;

	@FindBy(how = How.ID, using = "weight7")
	private WebElement weight7;

	@FindBy(how = How.ID, using = "weight8")
	private WebElement weight8;

	@FindBy(how = How.ID, using = "weight9")
	private WebElement weight9;

	@FindBy(how = How.ID, using = "weight10")
	private WebElement weight10;

	// Length
	@FindBy(how = How.ID, using = "length1")
	private WebElement length1;

	@FindBy(how = How.ID, using = "length2")
	private WebElement length2;

	@FindBy(how = How.ID, using = "length3")
	private WebElement length3;

	@FindBy(how = How.ID, using = "length4")
	private WebElement length4;

	@FindBy(how = How.ID, using = "length5")
	private WebElement length5;

	@FindBy(how = How.ID, using = "length6")
	private WebElement length6;

	@FindBy(how = How.ID, using = "length7")
	private WebElement length7;

	@FindBy(how = How.ID, using = "length8")
	private WebElement length8;

	@FindBy(how = How.ID, using = "length9")
	private WebElement length9;

	@FindBy(how = How.ID, using = "length10")
	private WebElement length10;

	// Width
	@FindBy(how = How.ID, using = "width1")
	private WebElement width1;

	@FindBy(how = How.ID, using = "width2")
	private WebElement width2;

	@FindBy(how = How.ID, using = "width3")
	private WebElement width3;

	@FindBy(how = How.ID, using = "width4")
	private WebElement width4;

	@FindBy(how = How.ID, using = "width5")
	private WebElement width5;

	@FindBy(how = How.ID, using = "width6")
	private WebElement width6;

	@FindBy(how = How.ID, using = "width7")
	private WebElement width7;

	@FindBy(how = How.ID, using = "width8")
	private WebElement width8;

	@FindBy(how = How.ID, using = "width9")
	private WebElement width9;

	@FindBy(how = How.ID, using = "width10")
	private WebElement width10;

	// Height
	@FindBy(how = How.ID, using = "height1")
	private WebElement height1;

	@FindBy(how = How.ID, using = "height2")
	private WebElement height2;

	@FindBy(how = How.ID, using = "height3")
	private WebElement height3;

	@FindBy(how = How.ID, using = "height4")
	private WebElement height4;

	@FindBy(how = How.ID, using = "height5")
	private WebElement height5;

	@FindBy(how = How.ID, using = "height6")
	private WebElement height6;

	@FindBy(how = How.ID, using = "height7")
	private WebElement height7;

	@FindBy(how = How.ID, using = "height8")
	private WebElement height8;

	@FindBy(how = How.ID, using = "height9")
	private WebElement height9;

	@FindBy(how = How.ID, using = "height10")
	private WebElement height10;

	// Description
	@FindBy(how = How.ID, using = "description0")
	private WebElement weDesc;

	@FindBy(how = How.ID, using = "description1")
	private WebElement desc1;

	@FindBy(how = How.ID, using = "description2")
	private WebElement desc2;

	@FindBy(how = How.ID, using = "description3")
	private WebElement desc3;

	@FindBy(how = How.ID, using = "description4")
	private WebElement desc4;

	@FindBy(how = How.ID, using = "description5")
	private WebElement desc5;

	@FindBy(how = How.ID, using = "description6")
	private WebElement desc6;

	@FindBy(how = How.ID, using = "description7")
	private WebElement desc7;

	@FindBy(how = How.ID, using = "description8")
	private WebElement desc8;

	@FindBy(how = How.ID, using = "description9")
	private WebElement desc9;

	@FindBy(how = How.ID, using = "description10")
	private WebElement desc10;

	// Linear Feet
	@FindBy(how = How.ID, using = "linearFeet")
	private WebElement welinearFeet;
	// Submit Button
	@FindBy(how = How.ID, using = "rateQuoteSubmitButton")
	private WebElement weSubmit;

	@FindBy(how = How.XPATH, using = "//*[@id='rateEstimateSubmitButton']")
	private WebElement submit;

	// Contact Me/VTL Basic Charge (BOTH HAS SAME XPATH)
	@FindBy(how = How.XPATH, using = "//a[@class='mr-2']")
	private WebElement weContMe;
	// Message for Contact Me
	@FindBy(how = How.XPATH, using = "//i[@class='fas fa-check-circle']")
	private WebElement weContMeMess;
	// Quote Number for VTL
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please write or type Guaranteed LTL Standard ')]")
	private WebElement rateQuoteMessage;

	@FindBy(how = How.ID, using = "quote_number")
	private WebElement weVTLQNumber;

	@FindBy(how = How.XPATH, using = "//tr[3]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement ltl10am;
	// Guaranteed LTL Standard Transit: 12PM
	@FindBy(how = How.XPATH, using = "//tr[5]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement weLTLST12PM;

	@FindBy(how = How.XPATH, using = "//tr[7]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement ltl5pm;

	@FindBy(how = How.XPATH, using = "//tr[11]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement guaranteedExclusiveUse;

	@FindBy(how = How.XPATH, using = "//tr[3]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement guaranteedValueAndTruckloadEconomy;

	// Quote Number for Time Critical
	@FindBy(how = How.ID, using = "quote_number")
	private WebElement weTCQNumber;

	@FindBy(how = How.ID, using = "contactName")
	private WebElement contactNameField;

	// @FindBy(how = How.XPATH, using = "//span[contains(text(),'Volume and
	// Truckload (incl. Guaranteed)')]")
	@FindBy(xpath = "//*[@id='volumeAndTruckload']/label/div")
	private WebElement volumeAndTruckload;

	// @FindBy(how = How.XPATH, using = "//span[contains(text(),'Time
	// Critical/Expedited')]")
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Time Critical Guaranteed')]")
//	@FindBy(how = How.XPATH, using = "//*[@id='accessorial24']/label/div/div[1]")
	private WebElement timeCritical;

	// @FindBy(how = How.XPATH, using = "//span[contains(text(),'Less than Truckload
	// (incl. Guaranteed)')]")
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Less than Truckload')]")
	private WebElement lessThanTuckload;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-0']//div[@class='mat-select-arrow-wrapper']")
	private WebElement country;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-1']//div[@class='mat-select-arrow']")
	private WebElement desCountry;

	@FindBy(how = How.XPATH, using = "//input[@id='addressSelect']")
	private WebElement account;

	@FindBy(how = How.XPATH, using = "//a[@class='mat-tab-link ng-star-inserted']")
	private WebElement history;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add from Commodity Library')]")
	private WebElement addFromCommodityLibrary;

	@FindBy(how = How.XPATH, using = "//lib-inline-button[@class='ml-1 font-weight-bold']")
	private WebElement addressBook;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary ng-star-inserted']")
	private WebElement commodity;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),' Revise Quote ')]")
	private WebElement revise;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(), 'Reserve Shipment')])")
	private WebElement reserveShipment1;

	@FindBy(how = How.XPATH, using = "//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[4]/td/div/app-quote-drawer/div/div/div[2]/app-quote-side-panel/div/app-quote-next-steps/app-guaranteed-next-steps/form/button")
	private WebElement reserveShipment2;

	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Create BOL')])[2]")
	private WebElement createBOL1;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[4]/td/div/app-quote-drawer/div/div/div[2]/app-quote-side-panel/div/app-quote-next-steps/app-guaranteed-next-steps/button[1]")
	private WebElement createBOL2;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(),'Clear')])[1]")
	private WebElement orClear;

	@FindBy(how = How.XPATH, using = "(//button[contains(text(),'Clear')])[2]")
	private WebElement desClear;

	@FindBy(how = How.XPATH, using = "//tr[1]//td[5]//app-service-level-action[1]//button[1]")
	private WebElement quoteButton;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'View All Accessorials')]")
	private WebElement viewAllAccessorials;

	// Secured Locations Pickup
	@FindBy(how = How.XPATH, using = "//span[contains(text(),' Secured Locations Pickup - prisons, military bases, airports, etc. ')]")
	private WebElement securedLocationsPickup;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Request Pickup')]")
	private WebElement requestPickup;

	@FindBy(xpath = "//mat-checkbox[@id='allAccessorials8']")
	private WebElement appointmentRequest;

	@FindBy(xpath = "//*[@id='allAccessorials75']/label/div[1]")
	private WebElement unloadingServiceRequestedByConsignee;

	@FindBy(xpath = "//mat-checkbox[@id='allAccessorials24']")
	private WebElement insideDeliveryChangesCollectAccessorial;

	@FindBy(xpath = "//mat-checkbox[@id='allAccessorials26']")
	private WebElement liftGateServiceDelivery;

	@FindBy(xpath = "//tr[12]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table//tr/td")
	private List<WebElement> chargeItemsTable;

	@FindBy(xpath = "//td[contains(@class,'column-serviceLevel')]")
	private List<WebElement> resultsTableServiceLevels;

	@FindBy(css = "tr:nth-child(2) app-quote-commodities th")
	private List<WebElement> commoditiesTableHeader;

	@FindBy(css = "tr:nth-child(2) app-quote-commodities td")
	private List<WebElement> commodiesTableDetails;

	@FindBy(xpath = "//span[contains(text(),'restricted for this account')]")
	private WebElement restrictedMessage;

	@FindBy(xpath = "//*[@id='timeCritical']//input")
	private WebElement tcCheckBox;

	@FindBy(xpath = "//*[@id='volumeAndTruckload']//input")
	private WebElement vtlCheckBox;

	@FindBy(xpath = "//*[@id='ltl']//input")
	private WebElement ltlCheckBox;

	@FindBy(css = "#rateQuoteFormContainer>div:nth-child(3) label>div")
	private WebElement useMyEstesAccountInfo;

	@FindBy(xpath = "//div[1]//app-quote-contact-information[1]//div[1]//div[1]")
	private WebElement contactInfoName;

	@FindBy(xpath = "//div[1]//app-quote-contact-information[1]//div[1]//div[2]")
	private WebElement contactInfoPhone;

	@FindBy(xpath = "//div[1]//app-quote-contact-information[1]//div[1]//div[3]")
	private WebElement contactInfoEmail;

	@FindBy(how = How.ID, using = "description1")
	private WebElement desc;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),' Revise Quote')]")
	private WebElement reviseQuote;

	@FindBy(how = How.XPATH, using = "//tr[td[contains(text(),'Volume and Truckload Basic')]]/td[4]")
	private WebElement weCharges;

	@FindBy(css = "tr:nth-child(2) app-quote-charge-items tr")
	private List<WebElement> chargeItemTableRows;

	@FindBy(id = "quote-disclaimer-content")
	private WebElement disclaimerContent;

	@FindBy(xpath = "//*[@id='main']//mat-card/table/tbody/tr[*]")
	private List<WebElement> serviceLevelTableRows;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/div/lib-feedback/div")
	// strong[contains(text(),'total linear footage')]
	// div[@class='ng-star-inserted alert alert-danger']
	private WebElement disclaimer;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),\"Overlength Freight (12.00' to 19.99')\")]")
	private List<WebElement> overlengthFreight;

	@FindBy(xpath = "//a[contains(text(), 'Fuel Surcharge')]")
	private WebElement fuelSurcharge;

	@FindBy(css = "#main mat-card > table > tbody >tr[class*='element']")
	private List<WebElement> servicelvlResultsTableRows;

	@FindBy(css = "tr:nth-child(2) [class*='alert-danger']>span")
	private WebElement alertMessage;

	@FindBy(xpath = "//app-ltl-quote-details/div/div[7]/a/lib-address/div/span[1]")
	private WebElement originAddressText;

	@FindBy(xpath = "//app-ltl-quote-details/div/div[7]/a/lib-address/div/span[2]")
	private WebElement originZipText;

	@FindBy(xpath = "//app-ltl-quote-details/div/div[8]/a/lib-address/div/span[1]")
	private WebElement destinationAddressText;

	@FindBy(xpath = "//app-ltl-quote-details/div/div[8]/a/lib-address/div/span[2]")
	private WebElement destinationZipText;

	@FindBy(css = "[class='mat-option-text']")
	private WebElement suggestionList;

	@FindBy(xpath = "//button[text()='Start New Quote']")
	private WebElement startNewQuote;

	@FindBy(xpath = "//*[@type='button'][contains(text(),'Confirm')]")
	private WebElement confirmButton;

	@FindBy(xpath = "//*[@class=\"mat-dialog-title\"]")
	private WebElement contactUsDialog;

	@FindBy(xpath = "//label[span[contains(text(),'I accept the Terms and Conditions')]]/div/input)[1]")
	private WebElement checkbox;

	@FindBy(xpath = "//app-guaranteed-next-steps/p[@class='ng-star-inserted']")
	private WebElement shipmentInfoMsg;

	@FindBy(how = How.XPATH, using = "//app-quote-commodities[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]")
	private WebElement chargeValue;

	@FindBy(how = How.XPATH, using = "//app-quote-charge-items[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]")
	private WebElement minChargeValue;

	@FindBy(how = How.XPATH, using = "//app-quote-commodities[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]")
	private WebElement rateValue;

	@FindBy(css = "[class='mat-dialog-content']")
	private WebElement reserveShipmentModel;

	@FindBy(xpath = "//*[text()='Results']")
	private WebElement rateQuoteResult;

	@FindBy(css = "[formcontrolname='email']")
	private WebElement reserveShipmentEmailAddress;

	@FindBy(xpath = "//mat-dialog-container//button[contains(text(),'Reserve Shipment')]")
	private WebElement reserveShipment;

	@FindBy(xpath = "//button/span[text()='Create BOL']")
	private List<WebElement> createBOL;

	@FindBy(css = "tr:nth-child(8) app-quote-contact-information div:nth-child(1)")
	private WebElement vtlbasicContactName;

	@FindBy(css = "tr:nth-child(8) app-quote-contact-information div:nth-child(3)")
	private WebElement vtlbasicContactEmail;

	@FindBy(css = "tr:nth-child(8) app-quote-contact-information div:nth-child(2)>div:nth-child(2)")
	private WebElement vtlbasicContactPhone;

	@FindBy(id = "declaredValue")
	private WebElement fullValueCoverage;

	@FindBy(xpath = "//tr[contains(@class,'expanded-row')]//following-sibling::tr//app-quote-disclosure//h6")
	private WebElement disclaimerMessage;

	@FindBy(xpath = "//lib-commodity-library-modal//tbody/tr[1]//a")
	private WebElement firstCommodityLink;

	@FindBy(xpath = "//mat-checkbox[@id='declaredValueWaived']")
	private WebElement checkboxFullCoverage;

	@FindBy(xpath = "//input[@id='declaredValue']")
	private WebElement freightvalue;

	@FindBy(xpath = "//button[contains(text(),'Service Level')]")
	private WebElement serviceLvlText;

	@FindBy(xpath = "//input[@id='linearFeet']")
	private WebElement linearFeet;

	@FindBy(xpath = "//mat-icon[contains(text(),'close')]//parent::div/span")
	private List<WebElement> closeAccessorialList;

	@FindBy(how = How.XPATH, using = "//app-quote-details/app-vtl-quote-details/div/div[17]")
	private WebElement systemLF;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Success! Shipment request received successfully.')]")
	private WebElement successMessage;
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'View Quote')]")
	private WebElement viewQuote;

	@FindBy(xpath = "//*[@id='main']/app-rate-quote/nav/div/a[1]")
	private WebElement createRateQuoteLink;

	@FindBy(xpath = "//*[@id='main']/app-rate-quote/nav/div/a[2]")
	private WebElement rateQuoteHistoryLink;

	@FindBy(xpath = "//*[@id='main']/app-rate-quote/app-quote-history/mat-card/mat-card-header/div[1]/mat-card-title")
	private WebElement historyTitle;

	@FindBy(xpath = "//*[@id='main']/app-rate-quote/app-quote-history/mat-card/mat-card-header/div[2]/button")
	private WebElement advanceSearchButton;

	@FindBy(css = "#main > app-rate-quote > app-quote-history > mat-card > app-history-advanced-search > div > div > form > lib-form-header > h6")
	private WebElement searchOption;

	@FindBy(xpath = "//*[@class='mat-checkbox-inner-container']")
	private WebElement showAllFilterCheckBox;

	@FindBy(xpath = "//span[contains(text(),'Show All Filters')]")
	private WebElement showAllFilterText;

	@FindBy(xpath = "//*[@class='btn btn-default']")
	private WebElement clearButton;

	@FindBy(xpath = "//*[@class='btn btn-primary mr-3']")
	private WebElement searchButton;

	@FindBy(xpath = "//*[@id='mat-input-10']")
	private WebElement fromDate;

	@FindBy(xpath = "//*[@id='mat-input-11']")
	private WebElement toDate;

	@FindBy(xpath = "//*[@id='mat-input-9']")
	private WebElement quoteNumTextBox;

	@FindBy(xpath = "//*[@id='main']/app-rate-quote/app-quote-history/mat-card/app-history-advanced-search/div/div/form/div[1]/div[2]/mat-form-field/div/div[1]/div[4]/mat-datepicker-toggle/button")
	private WebElement fromDateWidget;

	@FindBy(xpath = "//*[@id='main']/app-rate-quote/app-quote-history/mat-card/app-history-advanced-search/div/div/form/div[1]/div[3]/mat-form-field/div/div[1]/div[4]/mat-datepicker-toggle/button")
	private WebElement toDateWidget;

	@FindBy(how = How.XPATH, using = "//mat-radio-button[@id='mat-radio-3']//div[@class='mat-radio-outer-circle']")
	private WebElement truckloadRadioBtn;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Account Search')]")
	private WebElement accountSearchLink;

	@FindBy(how = How.XPATH, using = "//mat-card/mat-card-header/div[2]/div/div/div/input")
	private WebElement accSearch;

	@FindBy(how = How.XPATH, using = "//mat-row[1]//mat-cell[1]/a")
	private WebElement firstAccNum;

	@FindBy(css = "[formcontrolname='pickupDate']")
	private WebElement pickupDate;

	@FindBy(css = "[formcontrolname='weight']")
	private WebElement formWeight;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Book')]")
	private WebElement bookShipment;

	@FindBy(how = How.XPATH, using = "//mat-select[@formcontrolname='typeOfEquipment']//div[@class='mat-select-arrow']")
	private WebElement equipmentType;

	@FindBy(how = How.XPATH, using = "//h6[contains(text(),'QUOTE DETAILS')]")
	private WebElement quoteDetailsText;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Book Truckload Shipment')]")
	private WebElement tlShipmentPage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please note that next day pickups can be difficult')]")
	private WebElement difficultPickupMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Your chosen Pickup Date is more than 30 days from ')]")
	private WebElement pickUpMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total commodity weight exceeds the maximum of 45,0')]")
	private WebElement commodityMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Your chosen Pickup Date cannot be automatically ac')]")
	private WebElement todaysPickupMsg;

	@FindBy(how = How.XPATH, using = "//mat-card-content/div[4]/div/mat-checkbox/label/div")
	private WebElement hazardCheckBx;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Success! Using the information you provided, we will contact you shortly')]")
	private WebElement success;

	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[5]/app-truckload-actions[1]/span[1]")
	private WebElement checkIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'The destination address provided is not in the con')]")
	private WebElement destMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'The origin address provided is not in the contiguo')]")
	private WebElement originMessage;

	// @FindBy(how = How.XPATH, using = "//td[contains(text(),'Estes Truckload')]")
//	@FindBy(how = How.XPATH, using = "//span[@class='fal fa-chevron-down']")
	@FindBy(how = How.XPATH, using = "//*[@id='main']/app-rate-quote/app-truckload-results/mat-card/table/tbody/tr[1]/td[5]/app-truckload-actions/span[1]")
	private WebElement caretSymbol;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Email Quote')]")
	private WebElement emailQuote;

	@FindBy(how = How.ID, using = "emailAddresses")
	private WebElement emailAddresses;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Send')]")
	private WebElement sendBtn;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Success! Your quote has been sent')]")
	private WebElement successMsg;

	@FindBy(how = How.XPATH, using = "//mat-checkbox[@id='hazardousMaterial_hzTankerEndorsement_checkbox']/label/div")
	private WebElement tankerCheckBx;

	@FindBy(css = "[formcontrolname='hzUNnumber']")
	private WebElement unNumber;

	@FindBy(xpath = "//input[@id='hazardousMaterial_hzTankerEndorsement_hazmat_1_hzUNnumber']")
	private WebElement unNumber1;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_2_hzUNnumber']")
	private WebElement unNumber2;

	@FindBy(css = "[formcontrolname='hzPieces']")
	private WebElement hazmatPieceCount;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_1_hzPieces']")
	private WebElement hazmatPieceCount1;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_2_hzPieces']")
	private WebElement hazmatPieceCount2;

	@FindBy(css = "[formcontrolname='hzWeight']")
	private WebElement hazmatWeight;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_1_hzWeight']")
	private WebElement hazmatWeight1;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_2_hzWeight']")
	private WebElement hazmatWeight2;

	@FindBy(css = "[formcontrolname='hzDescription']")
	private WebElement hazmatDescription;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_1_hzDescription']")
	private WebElement hazmatDescription1;

	@FindBy(xpath = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_2_hzDescription']")
	private WebElement hazmatDescription2;

	@FindBy(how = How.XPATH, using = "//div[@id='rateQuoteFormContainer']/app-truckload-brokerage-create/form/mat-card[3]/mat-card-content/div[6]/div/div[2]/div/mat-form-field/div/div/div[3]")
	private WebElement hazmatPackagingCode;

	@FindBy(how = How.XPATH, using = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_1_hzPackagingCode']/div/div[1]")
	private WebElement hazmatPackagingCode1;

	@FindBy(how = How.XPATH, using = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_2_hzPackagingCode']/div/div[1]/span")
	private WebElement hazmatPackagingCode2;

	@FindBy(how = How.XPATH, using = "//div[@id='rateQuoteFormContainer']/app-truckload-brokerage-create/form/mat-card[3]/mat-card-content/div[6]/div/div[2]/div[2]/mat-form-field/div/div/div[3]")
	private WebElement hazmatCommodityType;

	@FindBy(how = How.XPATH, using = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_1_hzPieceType']/div/div[1]/span")
	private WebElement hazmatCommodityType1;

	@FindBy(how = How.XPATH, using = "//*[@id='hazardousMaterial_hzTankerEndorsement_hazmat_2_hzPieceType']/div/div[1]/span")
	private WebElement hazmatCommodityType2;

	@FindBy(how = How.XPATH, using = "//*[@id='accessorial12']/label/div")
	private WebElement overlenghtCheckbox;

	@FindBy(how = How.XPATH, using = "//*[@id='accessorial21']/label/div")
	private WebElement residentialDelivery;

	@FindBy(how = How.XPATH, using = "//*[@id='accessorial14']/label/div")
	private WebElement overlengthCheckbox16to1999;

	@FindBy(how = How.XPATH, using = "//div[@id='rateQuoteFormContainer']/div[5]/app-commodity-list/mat-card/mat-card-content/form/div/button")
	private WebElement addCommodity;

	@FindBy(how = How.XPATH, using = "//app-quote-next-steps/app-guaranteed-next-steps/button[2]/span[contains(text(),'Request Pickup')]")
	private WebElement pickupRequestBtn;

	@FindBy(how = How.XPATH, using = "//app-guaranteed-next-steps/button[1]")
	private WebElement createBol3;

	@FindBy(how = How.XPATH, using = "//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[6]/td/div/app-quote-drawer/div/div/div[2]/app-quote-side-panel/div/app-quote-next-steps/app-guaranteed-next-steps/form/button")
	private WebElement reserveShipment3;

	@FindBy(how = How.XPATH, using = "//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[4]/td/div/app-quote-drawer/div/div/div[2]/app-quote-side-panel/div/app-quote-next-steps/app-guaranteed-next-steps/button[1]")
	private WebElement createBol12pm;

	@FindBy(how = How.XPATH, using = "//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[6]/td/div/app-quote-drawer/div/div/div[2]/app-quote-side-panel/div/app-quote-next-steps/app-guaranteed-next-steps/button[1]")
	private WebElement createBol5pm;

	@FindBy(how = How.XPATH, using = "//*[@id='accessorial11']/label/div")
	private WebElement overlength8ft11in;

	@FindBy(how = How.XPATH, using = "//*[@id='accessorial9']/label/span")
	private WebElement liftGateService;

	@FindBy(how = How.XPATH, using = "//*[@id='mat-radio-3']/label/div[1]/div[1]")
	private WebElement estesTruckload;

	@FindBy(xpath = "//*[@id='weight']")
	private WebElement weightField;

	@FindBy(xpath = "//button[@id='incrementButton']")
	private WebElement addAddiontialRowBtn;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Success! Using the information you provided, a response will be sent to you')]")
	private WebElement contactMessage;

	@FindBy(xpath = "//span[@class='mat-option-text']")
	private WebElement matOptionText;

	@FindBy(how = How.XPATH, using = "//*[@id='contactExtension']")

	private WebElement phoneExtension;

	// public RateQuotePage() {
	// testUtil.init(this);
	// }

	/* HELPER METHODS */

	private WebElement spanWithText(String text) {
		return driver.findElement(By.xpath("//span[contains(text(),'" + text + "')]"));
	}

	private static DateTimeFormatter getDateTimeFormatter() {
		return DateTimeFormatter.ofPattern("MM/dd/yyyy");
	}

	/********************************* METHODS *********************************/

	public void selectRole() {
		logger.info("Select role");
		testUtil.init(this);
		testUtil.clickElementJavascript(myRole);
		WebElement role = spanWithText("Shipper");
		testUtil.clickElementJavascript(role);
	}

	public void selectTerms() {
		logger.info("Select Terms");
		testUtil.init(this);
		testUtil.clickElementJavascript(terms);
		WebElement term = driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Prepaid')]"));
		testUtil.clickElementJavascript(term);
	}

	public void enterOriginZip() {
		logger.info("Enter zip code");
		testUtil.init(this);
		originZipField.sendKeys("16159");
		testUtil.setHardWait(2000);
		Actions action = new Actions(driver);
		action.moveToElement(matOptionText).build().perform();
		matOptionText.click();
	}

	public void enterDestinationZip() {
		logger.info("Enter destination zip code");
		testUtil.init(this);
		destinationZipField.sendKeys("30307");
		testUtil.setHardWait(1000);
		Actions action = new Actions(driver);
		action.moveToElement(matOptionText).build().perform();
		matOptionText.click();
	}

	public void selectClass(String classVal) {
		logger.info("Select Class");
		testUtil.init(this);
		testUtil.clickElementJavascript(selectClass);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classVal + "')]")).click();

		// driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'50')]")).click();
	}

	public void enterContactName() {
		logger.info("Enter Contact Name");
		testUtil.init(this);
		testUtil.assetWait(null, contactNameField, 10, 200, TimeUnit.MICROSECONDS);
		contactNameField.click();

		testUtil.setHardWait(2000);
		Faker faker = new Faker();
		contactNameField.sendKeys(faker.address().firstName());

	}

	public void enterContactName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		contactNameField.clear();
		testUtil.setHardWait(500);
		contactNameField.sendKeys(name);
	}

	public void enterAccountNumber(String accNum) {
		logger.info("Enter account number");
		testUtil.init(this);
		account.clear();
		account.click();
		testUtil.setHardWait(2000);
		account.sendKeys(accNum);
	}

	public void accountSearch() {
		logger.info("Account Search");
		testUtil.init(this);
		driver.findElement(By.xpath("//a[contains(text(),'Account Search')]")).click();
		driver.findElement(By.xpath("(//mat-cell//a[@class='ng-star-inserted'])[1]")).click();
	}

	public void enterYourFullName(String name) {
		logger.info("Enter contact name");
		testUtil.init(this);
		weFName.sendKeys(name);
	}

	public void enterYourEmail(String email) {
		logger.info("Enter email address");
		testUtil.init(this);
		weEmail.sendKeys(email);
	}

	public void enterMyRole(String myRole) {
		logger.info("Select Role");
		testUtil.init(this);
		testUtil.clickElementJavascript(weMyRole);
		testUtil.setExplicitWait(weMyRole, 60);
		WebElement role = spanWithText(myRole);

		testUtil.clickElementJavascript(role);
		testUtil.setHardWait(1000);
	}

	public void enterPhoneNo(String phone) {
		logger.info("Enter phone number");
		testUtil.init(this);
		testUtil.setExplicitWait(wePhone, 90);
		wePhone.sendKeys(phone);
		testUtil.setHardWait(1000);
	}

	public void enterPhoneExtentionNo(String exNum) {
		logger.info("Enter phone number extention ");
		testUtil.init(this);
		testUtil.assetWait(null, extension, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(extension);
		testUtil.setHardWait(1000);
		extension.sendKeys(exNum);
	}

	public void enterPhoneExtentionNum(String exNum) {

		logger.info("Enter phone number extention ");

		testUtil.init(this);

		try {

			testUtil.assetWait(null, phoneExtension, 10, 200, TimeUnit.MILLISECONDS);

			phoneExtension.click();

			phoneExtension.sendKeys(exNum);

		} catch (NoSuchElementException e) {

			e.printStackTrace();

		}

	}

	public void enterTerms(String terms) {
		logger.info("Select Term");
		testUtil.init(this);
		testUtil.setExplicitWait(weTerms, 60);
		testUtil.clickElementJavascript(weTerms);
		WebElement term = spanWithText(terms);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(term);

		testUtil.setHardWait(500);
	}

	public void selectOrDeselectLessThanTruckload() {
		logger.info("Select or deselect Less Than Truckload");
		testUtil.init(this);
		testUtil.setExplicitWait(lessThanTuckload, 120);
		testUtil.clickElementJavascript(lessThanTuckload);
	}

	public void selectOrDeselectTimeCriticalExpedited() {
		testUtil.init(this);
		logger.info("Select or deselect Time Critical/Expedited");
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(timeCritical);
		testUtil.setHardWait(1000);
	}

	public void selectOrDeselectValumeAndTruckload() {
		logger.info("Select or deselect Volume and Truckload");
		testUtil.init(this);
		testUtil.assetWait(null, volumeAndTruckload, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(volumeAndTruckload);
		testUtil.setHardWait(1000);
	}

	public void selectOrDeselectEstesTruckload() {
		testUtil.init(this);
		logger.info("Click on  Estes Truckload radio button  under standard options ");
		testUtil.setExplicitWait(estesTruckload, 60);
		testUtil.clickElementJavascript(estesTruckload);

	}

	public void selectFirstEleFromDDown() {
		testUtil.init(this);
		Actions action = new Actions(driver);
		action.moveToElement(weFirstEleFDD).perform();
		weFirstEleFDD.click();
	}

	// Set's plus two date from today's date
	public void selectTodayDate() {
		logger.info("Enter today's date");
		testUtil.init(this);
		testUtil.setHardWait(500);

		DateTimeFormatter dtf = getDateTimeFormatter();
		LocalDate localDate = LocalDate.now().plusDays(2);

		weTodayDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		testUtil.setHardWait(500);
		weTodayDate.sendKeys(dtf.format(localDate));

	}
	
	//Sets today's date
	public void setTodayDate() {
		logger.info("Enter today's date");
		testUtil.init(this);
		testUtil.setHardWait(500);

		DateTimeFormatter dtf = getDateTimeFormatter();
		LocalDate localDate = LocalDate.now();

		weTodayDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		testUtil.setHardWait(500);
		weTodayDate.sendKeys(dtf.format(localDate));

	}

	public void addTomorrowDate1() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, M/d/yyyy");
		Date now = new Date();
		String todaysDate = dateFormat.format(now);

		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(todaysDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		DateTimeFormatter dtf = getDateTimeFormatter();
		LocalDate localDate = LocalDate.now().plusDays(2);

		weTodayDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		testUtil.setHardWait(500);
		weTodayDate.sendKeys(dtf.format(localDate));

		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == Calendar.FRIDAY)
			c.add(Calendar.DATE, +3);
		else if (dayOfWeek == Calendar.SATURDAY)
			c.add(Calendar.DATE, +2);
		else
			c.add(Calendar.DATE, +1);
	}

	public void selectNextSaturday() {
		logger.info("Enter next Sturday");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		DateTimeFormatter dtf = getDateTimeFormatter();
		LocalDate localDate = LocalDate.now();
		LocalDate prevSat = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

		weTodayDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		testUtil.setHardWait(500);
		weTodayDate.sendKeys(dtf.format(prevSat));

	}

	public void selectOriginCountry(String countryName) {
		testUtil.init(this);
		logger.info("Select Origin Country");
		testUtil.clickElementJavascript(orClear);
		testUtil.clickElementJavascript(country);
		WebElement weCountry = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + countryName + "')]"));
		testUtil.clickElementJavascript(weCountry);
		testUtil.setHardWait(1000);

	}

	public void selectDestinationCountry(String countryName) {
		logger.info("Select Destination Country");
		testUtil.init(this);
		testUtil.clickElementJavascript(desClear);
		testUtil.clickElementJavascript(desCountry);
		WebElement cName = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + countryName + "')]"));
		testUtil.clickElementJavascript(cName);
	}

	public void selectSecuredLocationsPickupCheckBox() {
		logger.info("Select Secured Locations Pickup - prisonsl, military bases, airports, act...");
		testUtil.init(this);
		securedLocationsPickup.click();
		testUtil.setHardWait(2000);
	}

	public void clickOnReviseQuoteButton() {
		logger.info("Click on Revise Quote button under Quote Details");
		testUtil.init(this);
		testUtil.clickElementJavascript(reviseQuote);
	}

	public String selectOrDeselectAccessorials(String accessorials) {
		logger.info("Select Or deselect Accessorials");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String accessorial = spanWithText(accessorials).getText();
		logger.info(accessorial + " is selected");
		WebElement accessorial1 = spanWithText(accessorials);
		testUtil.clickElementJavascript(accessorial1);
		testUtil.setHardWait(2000);
		return accessorial;
	}

	public String selectOrDeselectInsideDelivery() {
		logger.info("Select Or Deselect Accessorials Inside Delivery");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		WebElement accessorialEle = driver.findElement(By.xpath(
				"//*[@id='rateQuoteFormContainer']/div[7]/app-accessorials/mat-card/mat-card-content/div[1]/div[10]//input[@id='accessorial9-input']"));

		String txt = accessorialEle.getText();

		logger.info(txt + " is selected");
		testUtil.clickElementJavascript(accessorialEle);
		// return accessorial;
		return txt;

	}

	public String selectOrDeselectLiftGateServiceDelivery() {
		logger.info("Select Or Deselect Accessorials Lift Gate Service (Delivery)");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//*[@id='accessorial10']/label/div[1]")).getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//*[@id='accessorial10']/label/div[1]")).click();
		return accessorial;
	}

	public void selectOrDeselectOverLengthFreight() throws InterruptedException {
		logger.info("Select Or Deselect Accessorials Overlength Freight");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//span[@class='mat-checkbox-label'][contains(text(),\"Overlength Freight (12.00' to 15.99')\")]"))// updated
				.click();

	}

	public String selectOverLengthFreight() {
		logger.info("Select Or Deselect Accessorials overlength Freight");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),\"Overlength Freight (12.00' to 15.99')\")]")).getText(); // updated
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Overlength Freight (12.00' to 15.99')')]")).click();
		return accessorial;
	}

	public String selectOrDeselectAppointmentRequest() {
		logger.info("Select Or Deselect Accessorials Appointment Request");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Appointment Request')]")).getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Appointment Request')]")).click();
		return accessorial;
	}

	public String selectOrDeselectExhibitionCharge() {
		logger.info("Select Or Deselect Exhibition Charge");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Exhibition Charge')]")).getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Exhibition Charge')]")).click();
		return accessorial;
	}

	public String selectOrDeselectInsidePickup() {
		logger.info("Select Or Deselect Accessorials Inside Pickup");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//*[text()=' Inside Pickup ']")).getText();
		testUtil.setHardWait(1000);
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//*[text()=' Inside Pickup ']")).click();
		return accessorial;
	}

	public String selectOrDeselectConstructionSiteDelivery() {
		logger.info("Select Or Deselect Construction Site Delivery");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"allAccessorials10\"]/label/span"));
		String accessorial = driver.findElement(By.xpath("//*[@id=\"allAccessorials10\"]/label/span")).getText();
		logger.info(accessorial + " is selected");
		testUtil.clickElementJavascript(ele);
		return accessorial;
	}

	public String selectOrDeselectResidentialDelivery() {
		logger.info("Select Or Deselect Residential Delivery");
		testUtil.init(this);
		String accessorial = driver
				.findElement(
						By.xpath("//mat-checkbox[@id='allAccessorials59']//div[@class='mat-checkbox-inner-container']"))
				.getText();
		logger.info(accessorial + " is selected");
		driver.findElement(
				By.xpath("//mat-checkbox[@id='allAccessorials59']//div[@class='mat-checkbox-inner-container']"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectExhibitionChargeCallForChargesForLasVegasChicagoOrNY() {
		logger.info("Select Or Deselect  Exhibition Charge (Call for charges for Las Vegas, Chicago, or NY)t");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		testUtil.setHardWait(1000);

		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Exhibition Charge (Call for charges for Las Vegas,')]"))
				.getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Exhibition Charge (Call for charges for Las Vegas,')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectNotifyRequest() {
		logger.info("Select Or Deselect Accessorials Notify Request");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Notify Request')]")).getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Notify Request')]")).click();
		return accessorial;
	}

	public String selectOrDeselectSecuredLocationsPickupPrisonsMilitaryBases() {
		logger.info("Select Or Deselect Secured Locations Pickup - prisons, military bases, airports, etc..");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Secured Locations Pickup - prisons, military bases')]"))
				.getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Secured Locations Pickup - prisons, military bases,')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectSecuredLocationsDeliveryPrisonsMilitaryBases() {
		logger.info("Select Or Deselect Secured Locations Delivery - prisons, military bases,airports, etc..");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Secured Locations Delivery - prisons, military bas')]"))
				.getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Secured Locations Delivery - prisons, military bas')]"))
				.click();
		return accessorial;
	}

	public String selectOrDeselectSecuredDividerService() {
		logger.info("Select Or Deselect Secured Divider Service");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'Secured Divider Service')]"))
				.getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Secured Divider Service')]")).click();
		return accessorial;
	}

	public String selectIAcceptTheTermsAndConditionsCheckBox() {
		logger.info("Select I accept the Terms and Conditions check Box");
		testUtil.init(this);
		String checkBox = driver.findElement(By.xpath("(//span[contains(text(), 'I accept ')])[1]")).getText();
		logger.info(checkBox + " is selected");
		driver.findElement(By.xpath("(//span[contains(text(), 'I accept ')])[1]")).click();
		return checkBox;
	}

	public void selectIAcceptTheTermsAndConditionsCheckBox12PM() {
		logger.info("Select I accept the Terms and Conditions check Box");
		testUtil.init(this);
		WebElement checkBox = driver
				.findElement(By.xpath("//tr[4]//app-quote-side-panel//app-guaranteed-next-steps//mat-checkbox//label"));
		checkBox.click();
	}

	// Class
	public void enterClass(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(weClass);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass2(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class1);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass3(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class2);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass4(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class3);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass5(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class4);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass6(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class5);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass7(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class6);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass8(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class7);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass9(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class8);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass10(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class9);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	public void enterClass11(String classNo) {
		logger.info("Enter Class: " + classNo);
		testUtil.init(this);
		testUtil.clickElementJavascript(class10);
		testUtil.setHardWait(500);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + classNo + "')]")).click();
	}

	// Pieces
	public void enterPieces(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		wePieces.clear();
		wePieces.sendKeys(numPieces);
	}

	public void enterPieces2(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces.clear();
		pieces.sendKeys(numPieces);
	}

	public void enterPieces3(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces2.clear();
		pieces2.sendKeys(numPieces);
	}

	public void enterPieces4(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces3.clear();
		pieces3.sendKeys(numPieces);
	}

	public void enterPieces5(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces4.clear();
		pieces4.sendKeys(numPieces);
	}

	public void enterPieces6(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces5.clear();
		pieces5.sendKeys(numPieces);
	}

	public void enterPieces7(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces6.clear();
		pieces6.sendKeys(numPieces);
	}

	public void enterPieces8(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces7.clear();
		pieces7.sendKeys(numPieces);
	}

	public void enterPieces9(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces8.clear();
		pieces8.sendKeys(numPieces);
	}

	public void enterPieces10(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces9.clear();
		pieces9.sendKeys(numPieces);
	}

	public void enterPieces11(String numPieces) {
		logger.info("Enter Pieces: " + numPieces);
		testUtil.init(this);
		pieces10.clear();
		pieces10.sendKeys(numPieces);
	}

	// Piece type
	public void enterPieceType(String pieceType) {
		logger.info("Enter Pieces Type");
		testUtil.init(this);
		testUtil.clickElementJavascript(wepieceType);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + pieceType + "')]")).click(); // Ajitha
																														// //
																														// 03/27/2020
	}

	public void enterPieceTypeSkid() {
		logger.info("Enter Pieces Type Skid");
		testUtil.init(this);
		testUtil.clickElementJavascript(wepieceType);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'SKID')]")).click();
	}

	// THIS METHOD IS SPECIFICALLY FOR SECOND COMMODITY ==> SELECT PIECES TYPE FOR
	// "PALLET"!!
	public void enterPieceTypePallet() {
		logger.info("Enter Pieces Type PALLET");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'PALLET')])[2]")).click();
	}

	public void enterPieceType2(String pieceType2) {
		logger.info("Enter Pieces Type 2");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType);
		testUtil.setHardWait(4000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + pieceType2 + "')]")).click();
	}

	public void enterPieceType3(String pieceType3) {
		logger.info("Enter pieces type 3");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType2);
		testUtil.setHardWait(1000);
		spanWithText(pieceType3).click();
	}

	public void enterPieceType4(String pieceType4) {
		logger.info("enter pieces type 4");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType3);
		testUtil.setHardWait(1000);
		spanWithText(pieceType4).click();
	}

	public void enterPieceType5(String pieceType5) throws InterruptedException {
		logger.info("Enter Pieces Type 5");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(pieceType4);
		Thread.sleep(1000);
		spanWithText(pieceType5).click();
	}

	public void enterPieceType6(String pieceType6) throws InterruptedException {
		logger.info("Enter Pieces Type 6");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType5);
		Thread.sleep(1000);
		spanWithText(pieceType6).click();

	}

	public void enterPieceType7(String pieceType7) throws InterruptedException {
		logger.info("Enter Pieces Type 7");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType6);
		Thread.sleep(1000);
		spanWithText(pieceType7).click();
	}

	public void enterPieceType8(String pieceType8) throws InterruptedException {
		logger.info("Enter Pieces Type 8");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType7);
		Thread.sleep(1000);
		spanWithText(pieceType8).click();
	}

	public void enterPieceType9(String pieceType9) throws InterruptedException {
		logger.info("Enter Pieces Type 9");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType8);
		Thread.sleep(1000);
		spanWithText(pieceType9).click();
	}

	public void enterPieceType10(String pieceType10) throws InterruptedException {
		logger.info("Enter Pieces Type 10");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType9);
		Thread.sleep(1000);
		spanWithText(pieceType10).click();
	}

	public void enterPieceType11(String pieceType11) throws InterruptedException {
		logger.info("Enter Pieces Type 11");
		testUtil.init(this);
		testUtil.clickElementJavascript(pieceType10);
		Thread.sleep(1000);
		spanWithText(pieceType11).click();
	}

	// Total Weight
	public void enterTotalWeight(String totalWeight) {
		logger.info("Enter Weight 1");
		testUtil.init(this);
		weTWeight.clear();
		testUtil.setExplicitWait(weTWeight, 30);
		weTWeight.sendKeys(totalWeight);
	}

	public void enterWeightForShipmentInfo(String weight) {
		testUtil.init(this);
		logger.info("Enter total weight for shipment info");
		weightField.sendKeys(weight);
	}

	public void enterTotalWeight2(String totalWeight) {
		logger.info("Enter weight 2");
		testUtil.init(this);
		weight1.clear();
		testUtil.setHardWait(500);
		weight1.sendKeys(totalWeight);
	}

	public void enterTotalWeight3(String totalWeight) {
		logger.info("Enter weight 3");
		testUtil.init(this);
		weight2.clear();
		weight2.sendKeys(totalWeight);
	}

	public void enterTotalWeight4(String totalWeight) {
		logger.info("Enter weight 4");
		testUtil.init(this);
		weight3.clear();
		weight3.sendKeys(totalWeight);
	}

	public void enterTotalWeight5(String totalWeight) {
		logger.info("Enter Weight 5");
		testUtil.init(this);
		weight4.clear();
		weight4.sendKeys(totalWeight);
	}

	public void enterTotalWeight6(String totalWeight) {
		logger.info("Enter Weight 6");
		testUtil.init(this);
		weight5.clear();
		weight5.sendKeys(totalWeight);
	}

	public void enterTotalWeight7(String totalWeight) {
		logger.info("Enter Weightt");
		testUtil.init(this);
		weight6.clear();
		weight6.sendKeys(totalWeight);
	}

	public void enterTotalWeight8(String totalWeight) {
		logger.info("Enter Weight");
		testUtil.init(this);
		weight7.clear();
		weight7.sendKeys(totalWeight);
	}

	public void enterTotalWeight9(String totalWeight) {
		logger.info("Enter Weight");
		testUtil.init(this);
		weight8.clear();
		weight8.sendKeys(totalWeight);
	}

	public void enterTotalWeight10(String totalWeight) {
		logger.info("Enter Weight");
		testUtil.init(this);
		weight9.clear();
		weight9.sendKeys(totalWeight);
	}

	public void enterTotalWeight11(String totalWeight) {
		logger.info("Enter Weight");
		testUtil.init(this);
		weight10.clear();
		weight10.sendKeys(totalWeight);
	}

	// Length
	public void enterLength(String length) {
		logger.info("Enter Length 1");
		testUtil.init(this);
		weLength.clear();
		testUtil.setHardWait(1000);
		weLength.sendKeys(length);
	}

	public void enterLength2(String length) {
		logger.info("Enter Length 2");
		length1.clear();
		testUtil.init(this);
		length1.sendKeys(length);
	}

	public void enterLength3(String length) {
		logger.info("Enter Length 3");
		testUtil.init(this);
		length2.clear();
		length2.sendKeys(length);
	}

	public void enterLength4(String length) {
		logger.info("enter Length 4");
		testUtil.init(this);
		length3.clear();
		length3.sendKeys(length);
	}

	public void enterLength5(String length) {
		logger.info("enter Length 5");
		testUtil.init(this);
		length4.clear();
		length4.sendKeys(length);
	}

	public void enterLength6(String length) {
		logger.info("enter Length 6");
		testUtil.init(this);
		length5.clear();
		length5.sendKeys(length);
	}

	public void enterLength7(String length) {
		logger.info("enter Length 7");
		testUtil.init(this);
		length6.clear();
		length6.sendKeys(length);
	}

	public void enterLength8(String length) {
		logger.info("enter Length 8");
		testUtil.init(this);
		length7.clear();
		length7.sendKeys(length);
	}

	public void enterLength9(String length) {
		logger.info("enter Length 9");
		testUtil.init(this);
		length8.clear();
		length8.sendKeys(length);
	}

	public void enterLength10(String length) {
		logger.info("enter Length 10");
		testUtil.init(this);
		length9.clear();
		length9.sendKeys(length);
	}

	public void enterLength11(String length) {
		logger.info("enter Length 11");
		testUtil.init(this);
		length10.clear();
		length10.sendKeys(length);
	}

	// Width
	public void enterWidth(String width) {
		logger.info("Enter Width 1");
		weWidth.clear();
		testUtil.init(this);
		weWidth.sendKeys(width);
	}

	public void enterWidth2(String width) {
		logger.info("enter Width 2");
		testUtil.init(this);
		width1.clear();
		width1.sendKeys(width);
	}

	public void enterWidth3(String width) {
		logger.info("enter Width 3");
		testUtil.init(this);
		width2.clear();
		width2.sendKeys(width);
	}

	public void enterWidth4(String width) {
		logger.info("enter Width 4");
		testUtil.init(this);
		width3.clear();
		width3.sendKeys(width);
	}

	public void enterWidth5(String width) {
		logger.info("enter Width 5");
		testUtil.init(this);
		width4.clear();
		width4.sendKeys(width);
	}

	public void enterWidth6(String width) {
		logger.info("enter Width 6");
		testUtil.init(this);
		width5.clear();
		width5.sendKeys(width);
	}

	public void enterWidth7(String width) {
		logger.info("enter Width 7");
		testUtil.init(this);
		width6.clear();
		width6.sendKeys(width);
	}

	public void enterWidth8(String width) {
		logger.info("enter Width 8");
		testUtil.init(this);
		width7.clear();
		width7.sendKeys(width);
	}

	public void enterWidth9(String width) {
		logger.info("enter Width 9");
		testUtil.init(this);
		width8.clear();
		width8.sendKeys(width);
	}

	public void enterWidth10(String width) {
		logger.info("enter Width 10");
		testUtil.init(this);
		width9.clear();
		width9.sendKeys(width);
	}

	public void enterWidth11(String width) {
		logger.info("enter Width 11");
		testUtil.init(this);
		width10.clear();
		width10.sendKeys(width);
	}

	// Height
	public void enterHeight(String height) {
		logger.info("enter Height 1");
		testUtil.init(this);
		weHeight.clear();
		weHeight.sendKeys(height);
		testUtil.setHardWait(1000);
	}

	public void enterHeight2(String height) {
		logger.info("Enter Height 2");
		testUtil.init(this);
		height1.clear();
		height1.sendKeys(height);
	}

	public void enterHeight3(String height) {
		logger.info("enter Height 3");
		testUtil.init(this);
		height2.clear();
		height2.sendKeys(height);
	}

	public void enterHeight4(String height) {
		logger.info("enter Height 4");
		testUtil.init(this);
		height3.clear();
		height3.sendKeys(height);
	}

	public void enterHeight5(String height) {
		logger.info("enter Height 5");
		testUtil.init(this);
		height4.clear();
		height4.sendKeys(height);
	}

	public void enterHeight6(String height) {
		logger.info("enter Height 6");
		testUtil.init(this);
		height5.clear();
		height5.sendKeys(height);
	}

	public void enterHeight7(String height) {
		logger.info("enter Height 7");
		testUtil.init(this);
		height6.clear();
		height6.sendKeys(height);
	}

	public void enterHeight8(String height) {
		logger.info("enter Height 8");
		testUtil.init(this);
		height7.clear();
		height7.sendKeys(height);
	}

	public void enterHeight9(String height) {
		logger.info("enter Height 9");
		testUtil.init(this);
		height8.clear();
		height8.sendKeys(height);
	}

	public void enterHeight10(String height) {
		logger.info("enter Height 10");
		testUtil.init(this);
		height9.clear();
		height9.sendKeys(height);
	}

	public void enterHeight11(String height) {
		logger.info("enter Height 11");
		testUtil.init(this);
		height10.clear();
		height10.sendKeys(height);
	}

	// Description
	public void enterDesc(String des) {
		logger.info("Enter Description");
		testUtil.init(this);
		weDesc.clear();
		testUtil.setHardWait(500);
		weDesc.click();
		testUtil.setExplicitWait(weDesc, 60);
		weDesc.sendKeys(des);
		testUtil.setHardWait(1000);
	}

	public void enterDesc3(String des) {
		logger.info("enter Descriptions 3");
		testUtil.init(this);
		desc2.clear();
		desc2.sendKeys(des);
	}

	public void enterDesc4(String des) {
		logger.info("enter Descriptions 4");
		testUtil.init(this);
		desc3.clear();
		desc3.sendKeys(des);
	}

	public void enterDesc5(String des) {
		logger.info("enter Descriptions 5");
		testUtil.init(this);
		desc4.clear();
		desc4.sendKeys(des);
	}

	public void enterDesc6(String des) {
		logger.info("enter Descriptions 6");
		testUtil.init(this);
		desc5.clear();
		desc5.sendKeys(des);
	}

	public void enterDesc7(String des) {
		logger.info("enter Descriptions 7");
		testUtil.init(this);
		desc6.clear();
		desc6.sendKeys(des);
	}

	public void enterDesc8(String des) {
		logger.info("enter Descriptions 8");
		testUtil.init(this);
		desc7.clear();
		desc7.sendKeys(des);
	}

	public void enterDesc9(String des) {
		logger.info("enter Descriptions 9");
		testUtil.init(this);
		desc8.clear();
		desc8.sendKeys(des);
	}

	public void enterDesc10(String des) {
		logger.info("enter Descriptions 10");
		testUtil.init(this);
		desc9.clear();
		desc9.sendKeys(des);
	}

	public void enterDesc11(String des) {
		logger.info("enter Descriptions 11");
		testUtil.init(this);
		desc10.clear();
		desc10.sendKeys(des);
	}

	// Linear Feet
	public void enterLinearFeet(String linearFeet) {
		logger.info("enter Linear Feet");
		testUtil.init(this);
		welinearFeet.clear();
		welinearFeet.click();
		welinearFeet.sendKeys(linearFeet);
	}

	public void selectWarehouse(String wName) throws InterruptedException {
		testUtil.init(this);
		logger.info("Select warehouse");
		WebElement select = driver.findElement(By.xpath("//*[@id=\"foodWarehouse\"]/div/div[1]"));
		testUtil.clickElementJavascript(select);
		Thread.sleep(500);
		spanWithText(wName).click();
	}

	// ADDED FLUENT WAIT FOR SUBMIT BUTTON IN RATE QUOTE TO FIX LONGER LOADING ISSUE
	public void clikOnSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.setHardWait(2000);
		testUtil.WaitForTheElementClickable(weSubmit);
		testUtil.init(this);
		try {
			testUtil.setExplicitWait(weSubmit, 60);
			testUtil.clickElementJavascript(weSubmit);
			testUtil.setHardWait(2000);
			testUtil.fluentWait(By.xpath("//button[contains(text(),'Service Level')]"), 300, 5, "Service Level");
		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	public void clickOnSubmitButton() {
		logger.info("Click on Submit Request button");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int i = 0;
		int exist = 0;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary']")));
		do {
			testUtil.clickElementJavascript(submitButton);
			testUtil.setHardWait(1000);
			if (testUtil.isDisplayed(serviceLvlText)) {

				exist = 1;

			}

			i++;
		} while (i <= 5 && exist != 1);
		
		testUtil.awaitSpinningWheel(submitButton);
	}

	
	public void clikOnRequestPickup() {
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(requestPickup);
		logger.info("click on Request Pickup");
		testUtil.clickElementJavascript(requestPickup);
	}

	public void clikOnFreightPickUpOrDeliverAtFoodWarehouseOrDistributionCenter() {
		testUtil.init(this);
		logger.info("click on freight pick up or deliver at a food warehouse or distribution center checkbox");
		driver.findElement(By.xpath("//*[@id='deliversAtFoodWarehouse']//div[contains(text(),'Yes')]")).click();
		testUtil.setHardWait(1000);
	}

	// Contact Me/VTL Basic Charge (BOTH HAS SAME XPATH)
	public void clickOnContMe() {
		logger.info("click on Contact me");
		testUtil.init(this);
	
		weContMe.click();
	}

	public void clickOnRateQoute10am() {
		logger.info("click on Rate Qoute Guaranteed LTL Standard Transit: 10AM");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(ltl10am);
		ltl10am.click();
	}

	public void clickOnRateQoute12pm() {
		logger.info("click on Rate Qoute Guaranteed LTL Standard Transit: 12PM");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(weLTLST12PM);
		weLTLST12PM.click();
	}

	public void clickOnRateQoute5pm() {
		logger.info("click on Rate Qoute Guaranteed LTL Standard Transit: 5PM");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(ltl5pm);
		ltl5pm.click();
	}

	public void clickOnGuaranteedExclusiveUse() {
		logger.info("Click on Guaranteed Exclusive Use");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(guaranteedExclusiveUse);
		guaranteedExclusiveUse.click();
	}

	public String clickOnGuaranteedValueAndTruckloadEconomy() throws InterruptedException {
		logger.info("click on Guaranteed Valume and Truckload Economy");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(guaranteedValueAndTruckloadEconomy);
		guaranteedValueAndTruckloadEconomy.click();
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]"))
				.getText();
		logger.info(quoteNumber);
		return quoteNumber;
	}

	public void clickOnViewAllAccessorials() {
		logger.info("Click on view all Accessorials");
		testUtil.init(this);
		viewAllAccessorials.click();
	}

	public void clickOnGetQuoteButton() {
		testUtil.init(this);
		logger.info("Click on Get Quote Button");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//tr[1]//td[5]//app-service-level-action[1]//button[1]")));
		testUtil.clickElementJavascript(quoteButton);
	}

	public void clickOnGetQuoteButton(String eleName) {
		logger.info("Click on Get Quote Button" + eleName);
		testUtil.init(this);
		testUtil.setHardWait(2000);

		WebElement ele = driver.findElement(By.xpath("//td[contains(text(),  '" + eleName + "')]"));
		
		testUtil.assetWait(null, ele, 10, 200, TimeUnit.MILLISECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		testUtil.setHardWait(1000);

	}

	public void clickOnVTLBasicGetQouteButton() {
		testUtil.init(this);
		WebElement getQoute = driver.findElement(By.xpath("//tr[9]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.WaitForTheElementClickable(getQoute);
		logger.info("Click on Volume and Truckload Basic Get Qoute Button");
		testUtil.clickElementJavascript(getQoute);
	}

	public void clickOnReviseQouteButton() {
		logger.info("Click on Revise Quote Button");
		testUtil.init(this);
		testUtil.assetWait(null, revise, 10, 200, TimeUnit.MILLISECONDS);
		testUtil.clickElementJavascript(revise);
	}

	public void clickOnReserveShipmentButton() {
		logger.info("Click on Reserve Shipment button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(reserveShipment1);
	}

	public void clickOnReserveShipmentButton2() {
		logger.info("Click on Reserve Shipment button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		reserveShipment2.click();
	}

	public void clickOnCreateBOL() {
		logger.info("Click on Create BOL");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(createBOL1);
		testUtil.setHardWait(1000);

	}

	public void clickOnCreateBOL2() {
		logger.info("Click on Create BOL");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		createBOL2.click();
		testUtil.setHardWait(1000);

	}

	public void clickOnReviseQouteButtonBlowQouteOption() {
		logger.info("Click on Revise Qoute Button under Qoute Details");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.xpath("(//button[contains(text(),' Revise Quote')])[2]")).click();
	}

	public void clickOnReviseQouteButtonUnderVTLQouteDetails() {
		logger.info("click on Revise Qoute Button ");
		testUtil.init(this);
		driver.findElement(By.xpath(
				"(//div[@class='action-column']//button[@class='w-100 btn btn-default--action d-flex justify-content-between'][contains(text(),'Revise Quote')])[5]"))
				.click();
	}

	public void clickOnReviseQouteButtonUnderGuaranteedExclusiveUseQouteDetails() {
		logger.info("click on Revise Qoute Button ");
		testUtil.init(this);
		driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[12]/td/div/app-quote-drawer/div/div/div[2]/app-quote-side-panel/div/app-quote-options/button[1]"))
				.click();
	}

	public void clickOnAddressBookLink() throws InterruptedException {
		logger.info("Click on Address Book Link");
		testUtil.init(this);
		testUtil.clickElementJavascript(addressBook);
		Thread.sleep(1000);
	}

	public void clickOnAddFromCommodityLibrary() {
		logger.info("Click on Add From Commodity Library");
		testUtil.init(this);
		addFromCommodityLibrary.click();
	}

	public void clickOnQouteHistoryTab() {
		logger.info("Click on Rate Quote History");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(history);
	}

	public void clickOnAddCommodityButton() {
		logger.info("Click on Add commodity Button");
		testUtil.init(this);
		testUtil.setExplicitWait(commodity, 60);
		testUtil.clickElementJavascript(commodity);
		testUtil.setHardWait(1000);
	}

	public void clickOnLTLST10AMGetQoute() {
		logger.info("Click on LTL 10am Get qoute");
		testUtil.init(this);
		weLTLST12PM.click();
	}

	// Guaranteed LTL Standard Transit: 12PM
	public void clickOnLTLST12PMGetQoute() {
		logger.info("Click on LTL 12pm Get qoute");
		testUtil.init(this);
		weLTLST12PM.click();
	}

	public void addFromCommodityLibrary() {
		logger.info("Add from Commodity Library");
		testUtil.init(this);
		WebElement ele = driver.findElement(By.partialLinkText("TEST"));
		testUtil.clickElementJavascript(ele);
	}

	public void enterDesc2(String des) {
		logger.info("Enter descriptions");
		testUtil.init(this);
		desc.clear();
		desc.sendKeys(des);
	}

	public void verifyHistoryPage() {
		logger.info("Verify History Page display");
		String text = driver.findElement(By.xpath("(//*[contains(text(),'History')])[2]")).getText();
		logger.info("Page title is : " + text);
		Assert.assertEquals(text, "History");
	}

	public String getCharges() {
		logger.info("Get calculated charges value");
		testUtil.init(this);
		String charges = weCharges.getText();
		logger.info("Calculated charges is :" + charges);
		return charges;
	}

	//
	public void verifyCommodityWeightErrorMessage() {
		logger.info("verify commodity weight error message");
		String errorMessage = driver.findElement(By.xpath(
				"//*[@id=\"rateQuoteFormContainer\"]/div[5]/app-commodity-list/mat-card/mat-card-content/form/lib-feedback/div/span"))
				.getText();
		logger.info("Message is :" + errorMessage);
		String expected = "Your commodity weight exceeds the max of 42,500 lbs. If your shipment exceeds maximum weight, give us a call at 1-800-645-3952";
		Assert.assertEquals(errorMessage, expected);
	}

	public void verifyMessage() {
		logger.info("Verify attention message");
		String attnMessage = driver.findElement(By
				.xpath("//span[contains(text(),'Your shipment exceeds the cubic capacity threshold noted for your ')]"))
				.getText();
		logger.info("Message is :" + attnMessage);
		String expected = "Your shipment exceeds the cubic capacity threshold noted for your account. Thus, only volume and exclusive use rates are displayed. For other options such as LTL and time critical, or for more information, please give us a call at the number indicated above.";
		Assert.assertEquals(attnMessage, expected);
	}

	public void verifyAttentionMessage() {
		logger.info("Verify attention message");
		String attnMessage = driver
				.findElement(By.xpath("//span[contains(text(),'Our records indicate there is a problem with your')]"))
				.getText();
		logger.info("Message is :" + attnMessage);
		assertTrue(true, attnMessage);
	}

	public void verifyAttentionMessageForVLT() {
		logger.info("Verify attention message");
		testUtil.setHardWait(1000);
		String attnMessage = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/div/lib-feedback[2]/div/span"))
				.getText();
		logger.info("Message is :" + attnMessage);
		String expected = "Our records indicate there is a problem with your account. Please contact us at 804-353-1900 x2221 to obtain your Volume & Truckload quote.";
		Assert.assertEquals(attnMessage, expected);
	}

	public void verifyAttentionMessageOriginPoint() {
		logger.info("Verify attention message");
		String attnMessage = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-create-rate-estimate/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-estimate-drawer/div/div/div[1]/app-quote-details/app-transit-message/lib-feedback/div/span"))
				.getText();
		logger.info("Message is :" + attnMessage);
		String expected = "Origin point is not serviced direct.  Service is 9 days from LAREDO, TX exchange point.  Call LAREDO, TX for assistance.";
		Assert.assertEquals(attnMessage, expected);
	}

	public void verifyAttentionMessageDestinationPoint() {
		logger.info("verify attention message");
		String attnMessage = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-create-rate-estimate/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-estimate-drawer/div/div/div[1]/app-quote-details/app-transit-message/lib-feedback/div/span"))
				.getText();
		logger.info("Message is :" + attnMessage);
		String expected = "Origin point is not serviced direct.  Service is 9 days from LAREDO, TX exchange point.  Call LAREDO, TX for assistance.";
		Assert.assertEquals(attnMessage, expected);
	}

	public void verifyNoMessage() { // Modified to verify the absence of message - Ajitha - 12/19/2019
		logger.info("Verify attention message NOT display");

		boolean val = false;
		try {
			driver.findElement(By.xpath(
					"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/div/lib-feedback/div"))
					.isDisplayed();
			val = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			val = false;
		}

		Assert.assertFalse(val);
	}

	public void verifyGuarantedLTLstandardTransit5PMdeliveryDate() {
		logger.info("verify Guaranteed LTL Standard Transit: 5PM Delivery Date");
		String deliveyDate = driver.findElement(By.xpath("//tr[5]//td[2]")).getText();
		logger.info("Guaranteed LTL Standard Transit: 5PM Delivery Date is: " + deliveyDate);
	}

	public void verifyGuaranteedVolumeAndTruckloadEconomyDeliveryDate() {
		logger.info("Verify  Guaranteed Volume and Truckload Economy Delivery Date");
		String deliveyDate = driver.findElement(By.xpath("//tr[9]//td[2]")).getText();
		logger.info("Guaranteed Volume and Truckload Economy Delivery Date is: " + deliveyDate);
	}

	public void verifyGuaranteedVolumeAndTruckloadStandartDeliveryTime() {
		logger.info("Verify  Guaranteed Volume and Truckload Standart Delivery Time");
		String deliveyDate = driver.findElement(By.xpath("//tr[13]//td[3]")).getText();
		logger.info("Guaranteed Volume and Truckload Economy Delivery Date is: " + deliveyDate);
	}

	public void verifyRateQuotePageTitle() {
		logger.info("Verify Rate Quote Page Title");
		testUtil.setHardWait(2000);
		String title = driver.getTitle();
		logger.info(title);
		String expected = "My Estes: Rate Quote";
		Assert.assertEquals(title, expected);
	}

	public void verifyTableResult() throws InterruptedException {
		logger.info("Verify result table display");
		testUtil.checkPageIsReady();
		Thread.sleep(2000);
		testUtil.printWebTableData();
		testUtil.setHardWait(1000);
	}

	public void verifyCreateRateQoutePage() {
		logger.info("Verify Create Rate Qoute Page display");
		String actual = driver.findElement(By.xpath("//a[contains(text(),'Create Rate Quote')]")).getText();
		testUtil.setHardWait(1000);
		logger.info("page title is :" + actual);
		Assert.assertEquals(actual, "Create Rate Quote");

	}

	public void verifyAddFromCommodityLibraryPage() {
		logger.info("verify Add FromCommodity Library Page display");
		testUtil.setExplicitWait(driver.findElement(By.xpath("//mat-card-title[contains(text(),'Commodity Library')]")),
				30); // Added
		String actual = driver.findElement(By.xpath("//mat-card-title[contains(text(),'Commodity Library')]"))
				.getText();
		logger.info("page title is :" + actual);
		Assert.assertEquals(actual, "Commodity Library");

	}

	public void verifyNewCommodityRecordAdded() {
		logger.info("verify Add FromCommodity Library Page display");
		testUtil.verifyAndPrintWebTableData("//div[@class='form-row d-flex flex-wrap commodity-container']");

	}

	public void verifySelectedAccessorials() {
		logger.info("verify selected accessorials listed as line");
		testUtil.verifyAndPrintWebTableData(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table");

	}

	public void verifyCommoditiesSection() {
		logger.info("verify Commodities section");
		String comSection = driver.findElement(By.xpath("//body//app-quote-commodities//tr[2]")).getText();
		logger.info(comSection);
	}

	public void verifyCommoditiesDetails() {
		logger.info("verify Commodities Details");

		String comSection = driver.findElement(By.xpath(
				"//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-drawer/div/app-quote-commodities/div"))
				.getText();
		logger.info(comSection);
	}

	public void verifyVTLBasicCharesAreCalculated() {
		logger.info("Verify Volume and Truckload Basic charges are calculated");
	}

	// Verify Contact Me Message
	public void verifyMessForContMe() {
		logger.info(
				"Success! Using the information you provided, a response will be sent to you via email within approximately 1 hour."
						+ " Requests submitted after 5 p.m. M-F EST will receive a response the next business day.");

		String contMeMessage = weContMeMess.getText();
		assertTrue(true, contMeMessage);
	}

	public void verifyErrorMessage() {
		String expectedMessage = "Success! Using the information you provided, a response will be sent to you via email within approximately 1 hour. Requests submitted after 5 p.m. M-F EST will receive a response the next business day.";
		logger.info("Verify additional information Message");
		testUtil.init(this);
		String updatedQMessage = weContMeMess.getText().trim();
		logger.info("Success message is:" + updatedQMessage);
		assertEquals(updatedQMessage, expectedMessage);

	}

	public void verifyDisclaimer() {
		String expectedMessage = "Pieces, weights or dimensions may require special handling to ensure the guaranteed service level. Please contact the Estes Solution Center at 1-800-645-3952.";
		logger.info("Verify disclaimer Message");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String updatedQMessage = driver
				.findElement(By.xpath("//span[contains(text(),'Pieces, weights or dimensions may require')]")).getText()
				.trim();
		logger.info("Disclaimer message is:" + updatedQMessage);
		assertEquals(updatedQMessage, expectedMessage);

	}

	public void verifyMessageForRateQuote() {
		logger.info("Please write or type Guaranteed LTL Standard on your BOL (including the driver's copy).");
		String contMeMessage = rateQuoteMessage.getText();
		logger.info(contMeMessage);
		Assert.assertTrue(contMeMessage.length() > 0);
	}

	public void verifyRequestReceivedMessage() {
		logger.info("verify Request Received Message");
		String message = driver.findElement(By.xpath("//span[contains(text(),'Request Received')]")).getText();
		logger.info(message);
		Assert.assertEquals(message, "Request Received");
	}

	public String recordRateQouteNumber() {
		logger.info("Record the Rate Quote number ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)");
		testUtil.init(this);

		testUtil.setHardWait(2000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		logger.info("The quote number is: " + quoteNumber);
		return quoteNumber;
	}

	// Verify Quote Number for VTL/ TIME CRITICAL
	public void verifyVTLQNumberPrefixIs(String ch) throws InterruptedException {
		logger.info("verify Qoute Number prefix is ");
		testUtil.init(this);
		WebElement quote = driver.findElement(By.xpath("//tr[1]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote);
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		logger.info(quoteNumber);
		assertTrue(quoteNumber.contains(ch));

	}

	public String verifyVTLQNumberPrefixIs2() throws InterruptedException {
		logger.info("verify Qoute Number prefix is 2");
		testUtil.init(this);
		WebElement quote = driver.findElement(By.xpath("//tr[3]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote);
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]")).getText()
				.substring(19, 26);
		logger.info("Qoute Number is :" + quoteNumber);
		assertTrue(quoteNumber.contains("2"));
		return quoteNumber;
	}

	public void validateQouteNumber(String num) throws InterruptedException {
		logger.info("validate the qoute number that recorded ");
		testUtil.init(this);
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		driver.findElement(By.id("mat-input-20")).sendKeys(num);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary mr-3']")).click();
		String quoteNumber = driver.findElement(By.xpath(
				"(//td[@class='mat-cell cdk-column-quoteID mat-column-quoteID ng-tns-c59-52 ng-star-inserted'])[1]"))
				.getText();
		logger.info("Qoute Number is :" + quoteNumber);
		assertTrue(quoteNumber.contains(num));

	}

	public void validateVTLRateQouteinformations() {
		logger.info("validate VTL Rate Qoute Informations");
		testUtil.init(this);
		String qoute = driver.findElement(By.xpath(
				"//*[@id=\"content\"]/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-drawer/div/div/div[1]/app-quote-details/app-vtl-quote-details/div"))
				.getText();
		logger.info(qoute);
	}

	public void validateRequesterInformationField() {
		logger.info("validate requester information fields");
		testUtil.init(this);

		if (weFName.isDisplayed() && weEmail.isDisplayed() && wePhone.isDisplayed()) {
			logger.info("Required fields are populated and displayed !!!");
		} else {
			logger.info("Required fields are NOT populated and Not displayed !!!");
		}
	}

	public void varifyEnterValidAccountNumberError() {
		logger.info("Verify 'Please enter a valid account number' error message displays.");
		testUtil.init(this);
		String error = driver.findElement(By.xpath("//span[@class='ng-star-inserted']")).getText();
		logger.info("error is: " + error);
		Assert.assertEquals(error, "Please enter a valid account number to rate this quote.");
	}

	public void validateQouteNumberInformations() throws InterruptedException {

		logger.info("validate the qoute number informations");
		testUtil.init(this);
		WebElement quote = driver.findElement(By.xpath("//tr[1]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote);
		Thread.sleep(500);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		logger.info(quoteNumber);

		String chargeItems = driver
				.findElement(By.xpath(
						"//tr[2]//td[1]//div[1]//app-quote-drawer[1]//div[1]//app-quote-charge-items[1]//div[1]"))
				.getText();
		logger.info(chargeItems);

		WebElement quote1 = driver.findElement(By.xpath("//tr[3]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote1);
		Thread.sleep(500);
		String quoteNumber2 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[2]"))
				.getText();
		logger.info(quoteNumber2);

	}

	public void validateChargeItemsInformations() throws InterruptedException {
		logger.info("validate the charge Items informations");
		testUtil.init(this);
		Thread.sleep(500);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		logger.info(quoteNumber);

		String chargeItems = driver
				.findElement(By.xpath(
						"//tr[2]//td[1]//div[1]//app-quote-drawer[1]//div[1]//app-quote-charge-items[1]//div[1]"))
				.getText();
		logger.info(chargeItems);

	}

	public void clickOnAppointmentRequest() {
		logger.info("Click on Appointment Request Accessorial");
		testUtil.init(this);
		testUtil.setExplicitWait(appointmentRequest, 60);
		testUtil.clickElementJavascript(appointmentRequest);

	}

	public void clickOnUnloadingServiceRequestedByConsignee() {
		logger.info("Click on Unloading Services Requested By Consignee Accessorial");
		testUtil.init(this);
		unloadingServiceRequestedByConsignee.click();
	}

	public void clickOnInsideDeliveryChargeCollect() {
		logger.info("Clicking on Inside Delivery Charge (Collect) Accessorial");
		testUtil.init(this);
		insideDeliveryChangesCollectAccessorial.click();
	}

	public void clickOnLiftGateServiceDelivery() {
		logger.info("Clicki on Lift Gate Service Delivery Accessorial");
		testUtil.init(this);
		testUtil.setExplicitWait(liftGateServiceDelivery, 60);
		testUtil.clickElementJavascript(liftGateServiceDelivery);

	}

	public List<String> validateChargeItemsDetails() {
		ArrayList<String> chargeItems = new ArrayList<>();
		logger.info("Verify Charge Item details");
		testUtil.init(this);
		for (int i = 0; i < chargeItemsTable.size(); i++) {
			chargeItems.add(chargeItemsTable.get(i).getText().trim());
		}
		return chargeItems;
	}

	public void validateGetQuoteButtonByServiceLevel(String service) {
		logger.info("Validate 'Get Quote' button displayed by Service Level");
		testUtil.init(this);

		for (int i = 1; i <= resultsTableServiceLevels.size(); i++) {
			String serviceLevel = resultsTableServiceLevels.get(0).getText().trim();
			if (serviceLevel.equalsIgnoreCase(service)) {
				WebElement getQuoteElement = driver
						.findElement(By.xpath("//td[contains(@class,'column-serviceLevel')]//parent::tr//button"));
				assertTrue(getQuoteElement.isDisplayed(),
						"Failed to display 'Get Quote' button for the Service Level: " + service);

				String buttonName = getQuoteElement.getText().trim();
				Assert.assertTrue(buttonName.contains("GET QUOTE"));
				break;
			}
		}
	}

	public void verifyGetQuoteButtonIsNotDisplayedOnTheResultsPage(String serviceLevel) {
		logger.info("Verify if Contact_Me is Not displayed on the results table");
		testUtil.init(this);

		List<WebElement> eleServiceLevel = driver
				.findElements(By.xpath("//td[contains(text(),' " + serviceLevel + "')]"));

		for (int i = 0; i <= eleServiceLevel.size(); i++) {

			String str = eleServiceLevel.get(0).getText().trim();
			logger.info("ServiceLevel is: " + str);

			if (str.equalsIgnoreCase(serviceLevel)) {

				WebElement ele = driver.findElement(By.xpath("//*[@id='93']/td[5]"));

				assertTrue(ele.isDisplayed(), "Field to display Contact_Me link" + serviceLevel);

				String buttonName = ele.getText().trim();
				logger.info(buttonName);

				testUtil.setHardWait(1000);

				Assert.assertTrue(buttonName.contains("Contact Me"));

				break;
			}
		}
	}

	public void clickOnContactMeLink(String serviceLevel) {
		logger.info("Click on contact me link");
		testUtil.init(this);

		List<WebElement> eleServiceLevel = driver
				.findElements(By.xpath("//td[contains(text(),' " + serviceLevel + "')]"));

		for (int i = 0; i <= eleServiceLevel.size(); i++) {

			String str = eleServiceLevel.get(0).getText().trim();

			logger.info(str);

			if (str.equalsIgnoreCase(serviceLevel)) {
				WebElement ele = driver.findElement(By.xpath("//*[@id='92']/td[5]"));
				ele.click();
				break;
			}
		}

	}

	public String captureCommodiesDetails(String colName) {
		logger.info("Recording Commodities Details");
		testUtil.init(this);
		String details = null;
		testUtil.setHardWait(2000);
		for (int i = 0; i < commoditiesTableHeader.size(); i++) {
			String columnName = commoditiesTableHeader.get(i).getText().trim();
			if (columnName.equals(colName)) {
				details = commodiesTableDetails.get(i).getText().trim();
				break;
			}
		}
		return details;
	}

	public void verifyContactMe(String serviceLevel, boolean contactMe) {
		logger.info("Verify Contact Me in " + serviceLevel);
		testUtil.init(this);
		testUtil.setHardWait(1000);

		List<WebElement> rowCount = driver.findElements(By.xpath(
				"//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[*]"));

		for (int i = 0; i < rowCount.size(); i++) {
			int j = i + 1;
			String actualServiceLevel = driver.findElement(By.xpath(
					"//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr["
							+ j + "]/td[1]"))
					.getText();

			if (actualServiceLevel.equals(serviceLevel)) {
				String actual = driver.findElement(By.xpath(
						"//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr["
								+ j + "]/td[5]"))
						.getText();

				if (contactMe) {
					logger.info("Verify Contact Me displayed");
					Assert.assertEquals(actual, "Contact Me");
				} else {
					logger.info("Verify Contact Me is not displayed");
					Assert.assertEquals(actual, "");
				}
				break;
			}
		}
	}

	public void verifyBasicCharge(String serviceLevel, boolean basicCharge) {
		logger.info("Verify basic charge in " + serviceLevel);
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath(
				"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[*]"));

		for (int i = 0; i < rowCount.size(); i++) {
			int j = i + 1;
			String actualServiceLevel = driver.findElement(By.xpath(
					"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr["
							+ j + "]/td[1]"))
					.getText();

			if (actualServiceLevel.equals(serviceLevel)) {
				String actual = driver.findElement(By.xpath(
						"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr["
								+ j + "]/td[4]"))
						.getText();

				if (basicCharge) {
					logger.info("Verify Basic Charge is not blank");
					Assert.assertNotNull(actual);
				} else {
					logger.info("Verify Basic Charge is blank");
					Assert.assertEquals(actual.isEmpty(), true);
				}
				break;
			}
		}
	}

	public void verifyRestrictedMessage() {
		logger.info("Verify restricted message");
		testUtil.init(this);
		testUtil.setExplicitWait(restrictedMessage, 20);
		boolean existence = restrictedMessage.isDisplayed();
		logger.info("Message is : " + restrictedMessage.getText());
		Assert.assertTrue(existence);
	}

	public void verifyQuoteTypeIsDisable(String quoteType) {
		logger.info("Verify " + quoteType + " is in disable mode");
		testUtil.init(this);
		if (quoteType.equals("TC")) {
			boolean modeOfVisible = tcCheckBox.isEnabled();
			Assert.assertFalse(modeOfVisible);
		} else if (quoteType.equals("LTL")) {
			boolean modeOfVisible = ltlCheckBox.isEnabled();
			Assert.assertFalse(modeOfVisible);
		} else if (quoteType.equals("VTL")) {
			boolean modeOfVisible = vtlCheckBox.isEnabled();
			Assert.assertFalse(modeOfVisible);
		}
	}

	public void verifyQuoteTypeCheckboxIsSelected(String quoteType) {
		logger.info("Verify " + quoteType + " is selected");
		testUtil.init(this);
		if (quoteType.equals("TC")) {
			boolean selectedStatus = tcCheckBox.isSelected();
			Assert.assertTrue(selectedStatus);
		} else if (quoteType.equals("VTL")) {
			boolean selectedStatus = vtlCheckBox.isSelected();
			Assert.assertTrue(selectedStatus);
		} else if (quoteType.equals("LTL")) {
			boolean selectedStatus = ltlCheckBox.isSelected();
			Assert.assertTrue(selectedStatus);
		}
	}

	public void clickOnMyEstesAccountInfo() {
		testUtil.init(this);
		useMyEstesAccountInfo.click();
	}

	public void verifyNameFromContactInformation(String name) {
		logger.info("Verify Rate Quote Name from Contact Information");
		testUtil.init(this);
		testUtil.setExplicitWait(contactInfoName, 20);
		String contactNme = contactInfoName.getText().trim();
		Assert.assertTrue(contactNme.contains(name));
	}

	public void verifyPhoneNumberFromContactInformation(String phone) {
		logger.info("Verify Rate Quote Phone Number from Contact Information");
		testUtil.init(this);
		String phoneNum = contactInfoPhone.getText().trim();
		Assert.assertTrue(phoneNum.contains(phone));
	}

	public void verifyEmailAddressFromContactInformation(String email) {
		logger.info("Verify Rate Quote Email Address from Contact Information");
		testUtil.init(this);
		String emailAddr = contactInfoEmail.getText().trim();
		Assert.assertTrue(emailAddr.contains(email));
	}

	public void verifyDisclaimerIsDisplayed(String expected) {
		logger.info("Verify Disclaimer is displayed");
		testUtil.setHardWait(1000);
		String message = driver.findElement(By.xpath(
				"//*[@id=\"main\"]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/div/lib-feedback/div"))
				.getText();
		logger.info("Message is :" + message);
		Assert.assertEquals(message, expected);

	}

	public void verifyChargeItems(String expected) {
		logger.info("Verify Charge Items for pick up " + expected);

		testUtil.setHardWait(1000);
		String value = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[9]")).getText();
		testUtil.setHardWait(500);
		if (value.equalsIgnoreCase(expected)) {
			logger.info("Charge Item :" + value + "  Accessorial is displayed as a line item");
			Assert.assertEquals(value, expected);
		}
	}

//	public void clickOnReviseQouteButtonBelowQouteOption() {
//		logger.info("Click on Revise Quote button under Quote details");
//		testUtil.init(this);
//		driver.findElement(By.xpath("(//button[contains(text(),' Revise Quote')])[2]")).click();
//	}

	public String selectOrDelselectOverlengthCheckBox28feetOrGreater() {
		logger.info("Select or Deselect Overlength Freight (28 feet or greater)  check Box");
		testUtil.init(this);
		String checkBox = driver.findElement(By.id("accessorial13")).getText();
		logger.info(checkBox + " is selected or Deselected");
		driver.findElement(By.id("accessorial13")).click();
		return checkBox;
	}

	public void validateAccessorials(String expected) {
		logger.info("Validate Accessorial section");
		String accessorials = driver.findElement(By.xpath("//span[@class='text-left']")).getText();
		logger.info("The selected accessorials is : " + accessorials);
		Assert.assertEquals(accessorials, expected);
	}

	public String selectOverlengthCheckBox() {
		logger.info("Select Overlength (8.01'-11.99') check Box");
		testUtil.init(this);
		String checkBox = driver.findElement(By.id("accessorial10")).getText();
		logger.info(checkBox + " is selected");
		driver.findElement(By.id("accessorial10")).click();
		return checkBox;
	}

	public void verifyChargeItemsTable(String desc, String charge) {
		testUtil.init(this);
		WebElement e = testUtil.filterXpath("//td[text()=\"" + desc + "\"]/following-sibling::td", null, null, 0,
				"" + charge + "");
		System.out.println("Charge item " + desc + " amount: " + e.getText());
		Assert.assertTrue(e.isDisplayed());
	}

	public boolean verifyFuelSurchargeIsNotDisplayed() {
		logger.info("Verify fuel surcharge is not displayed");
		testUtil.init(this);
		try {
			fuelSurcharge.isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	// method for capturing Service level
	public String recordQuoteNumber(String serviceLevel) {
		logger.info("Capture quote number");
		testUtil.init(this);
		WebElement quoteDrawer = testUtil.filterSelector("app-quote-drawer", null, null, 1, serviceLevel);
		testUtil.setHardWait(700);
		WebElement quoteNumHeader = testUtil.filterSelector("h4", null, quoteDrawer, 0, "Rate Quote Number: ");
		testUtil.setHardWait(1000);
		// String quoteNumber = quoteNumHeader.getText().substring(19);
		String quoteNumber = testUtil.getTextFromElement(quoteNumHeader).substring(19);
		testUtil.setHardWait(700);
		System.out.println(quoteNumber);
		testUtil.setHardWait(700);
		return quoteNumber;
	}

	public void clickOnTermsAndConditionsFor(String serviceLevel) {
		logger.info("Click on Terms and Conditions for " + serviceLevel);
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath("//tbody/tr"));

		for (int i = 1; i < rowCount.size(); i++) {
			String actual = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText();
			int j = i + 1;
			if (actual.contains(serviceLevel)) {
				driver.findElement(
						By.xpath("//tr[" + j + "]/td[1]//span[contains(text(),'I accept the Terms and Conditions ')]"))
						.click();
				break;
			}
		}

	}

	public void clickOnResreveShipmentFor(String serviceLevel) {
		logger.info("Click on Reserve Shipment for " + serviceLevel);
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath("//tbody/tr"));

		for (int i = 1; i < rowCount.size(); i++) {
			String actual = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText();
			int j = i + 1;
			if (actual.contains(serviceLevel)) {
				driver.findElement(By.xpath("//tr[" + j + "]/td[1]//button[contains(text(),'Reserve Shipment')]"))
						.click();
				break;
			}
		}

	}

	public void verifyRequestReceivedDisclaimerMsg() {
		logger.info("Verify message displayed when request received");
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//p[2][contains(text(),'In the meantime')]")).isDisplayed());
		logger.info(driver.findElement(By.xpath("//p[2][contains(text(),'In the meantime')]")).getText());

	}

	public void validateChargeItemsTableDescription(String desc) {
		logger.info("Validate Charge Item table description details");
		testUtil.init(this);

		boolean flag = false;

		for (int i = 1; i < chargeItemTableRows.size(); i++) {

			String description = testUtil.getWebElement(chargeItemTableRows.get(i), "td:nth-child(1)").getText().trim();
			if (description.contains(desc)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag, "Failed to display Charge Item description: " + desc);
	}

	public String getChargeItemsTableCharges(String desc) {
		logger.info("Validating Charge Item table charges");
		testUtil.init(this);

		boolean flagVal = false;
		String charges = null;

		for (int i = 1; i < chargeItemTableRows.size(); i++) {

			String description = testUtil.getWebElement(chargeItemTableRows.get(i), "td:nth-child(1)").getText().trim();
			if (description.contains(desc)) {
				charges = testUtil.getWebElement(chargeItemTableRows.get(i), "td:nth-child(2)").getText().trim();
				if (!charges.isEmpty()) {
					flagVal = true;
					break;
				} else {
					flagVal = false;
				}
			}
		}
		assertTrue(flagVal, "Failed to display Charge Item description and its charges: " + desc);
		return charges;
	}

	public void verifyDisclaimerContent() {
		logger.info("Verifying Disclaimer Content");
		testUtil.init(this);
		boolean disclaimerExistence = disclaimerContent.isDisplayed();
		TestUtil.verifyTrue(disclaimerExistence);
	}

	public void validateContactMeLinkByServiceLevel(String serviceLevel) {
		logger.info("Validate Contact Me link by service level: " + serviceLevel);
		testUtil.init(this);
		for (int i = 0; i <= serviceLevelTableRows.size(); i++) {
			int j = i + 1;
			String serviceLvl = driver
					.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr[" + j + "]/td[1]")).getText()
					.trim();
			if (serviceLvl.equalsIgnoreCase(serviceLevel)) {
				String name = driver.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr[" + j + "]/td[5]"))
						.getText().trim();
				assertTrue(name.equals("Contact Me"), "Failed to display Contact Me link");

				logger.info("Contact Me is displayed for the service level: " + serviceLevel);
				break;
			}
		}
	}

	public void verifyOverlengthFreightIsDisplayed(String length) {
		logger.info("Verify overlength freight is displayed");
		testUtil.init(this);
		if (Integer.parseInt(length) > 100)
			Assert.assertTrue(!overlengthFreight.isEmpty());
		else
			Assert.assertTrue(overlengthFreight.isEmpty());
	}

	public void verifyOverlengthFreight() {
		logger.info("verify overlength freight is displayed");
		testUtil.init(this);
		Assert.assertTrue(
				driver.findElement(By.xpath("//td[contains(text(),\"Overlength Freight (12.00' to 15.99')\")]"))
						.isDisplayed());// updated

	}

	public void verifyOverlengthCharge() {
		logger.info("Verify overlength freight is displayed");
		testUtil.init(this);
		testUtil.setHardWait(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'OVERLENGTH CHARGE')]")).isDisplayed());

	}

	public String verifyOverLenghtChargeForLTLStandardTransite() {
		logger.info("Verify Overlenght Charge for LTL Standard Transit is 8.00 - 11.99");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement overLengthCharge = driver.findElement(By.xpath("//tr[3]//td[2] [contains(text(),'$')]"));
		String text = overLengthCharge.getText();
		logger.info("The value is :" + text);
		Assert.assertEquals(overLengthCharge.isDisplayed(), true);
		return text;

	}

	public void verifyOverlengthFreightIsSelected() throws InterruptedException {
		logger.info("verify overlength freight is selected");
		testUtil.init(this);
		Thread.sleep(3000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[span[contains(text(),'Overlength Freight (16.00')]]/div/input"))
						.isSelected()); // updated
	}

	public void verifyTransitRates(String serviceType) {
		logger.info("verify Transit rates are calculated");
		testUtil.init(this);
		String value = driver.findElement(By.xpath("//tr[td[contains(text(),'" + serviceType + "')]]/td[4]")).getText();
		Assert.assertTrue(value != null && !value.equalsIgnoreCase(""));
	}

	public String getTransitRates(String serviceType) {
		logger.info("get Transit rates are calculated");
		testUtil.init(this);
		return driver.findElement(By.xpath("//tr[td[contains(text(),'" + serviceType + "')]]/td[4]")).getText()
				.replace("$", "").trim();
	}

	public void selectNextSunday() {
		logger.info("Enter next Sunday");
		testUtil.init(this);
		DateTimeFormatter dtf = getDateTimeFormatter();
		LocalDate localDate = LocalDate.now();
		LocalDate prevSat = localDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		weTodayDate.clear();
		weTodayDate.sendKeys(dtf.format(prevSat));
	}

	public void verifyReserveShipment(String value) throws InterruptedException {
		logger.info("verify Reserve shipment button");
		testUtil.init(this);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//tr[td[contains(text(),'" + value
				+ "')]]/following-sibling::tr/descendant::button[contains(text(), 'Reserve Shipment')]"));
		Assert.assertEquals(element.isDisplayed(), true);
	}

	public String captureAlertMessage() {
		logger.info("Capturing Alert Message");
		testUtil.init(this);
		testUtil.setExplicitWait(alertMessage, 20);
		return alertMessage.getText().trim();
	}

	public String getChargesByServiceLevel(String serviceLevel) {
		logger.info("Get charges information by Service level");
		testUtil.init(this);
		WebElement chargeItem = testUtil.filterXpath("//td[text()=\" " + serviceLevel + " \"]/following-sibling::td[3]",
				null, null, 0, "$");
//		WebElement chargeItem = driver.findElement(By.xpath("/html/body/app-root/div/main/div/div[1]/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[13]/td[4]"));
		System.out.println("Charge item amount: " + chargeItem.getText());
		return chargeItem.getText();
	}

	public void verifyOriginAddressFromContactInfo(String address) {
		logger.info("Verifying Origin Address from Contact Information");
		testUtil.init(this);
		testUtil.setExplicitWait(originAddressText, 20);
		String originAdd = originAddressText.getText().trim();
		String originZip = originZipText.getText().trim();
		assertTrue(originAdd.contains(address));
		assertTrue(originZip.contains(address));
	}

	public void verifyDestinationAddressFromContactInfo(String address) {
		logger.info("Verifying Destination Address from Contact Information");
		testUtil.init(this);
		testUtil.setExplicitWait(destinationAddressText, 20);
		String destinationAdd = destinationAddressText.getText().trim();
		String destinationZip = destinationZipText.getText().trim();
		assertTrue(destinationAdd.contains(address));
		assertTrue(destinationZip.contains(address));
	}

	public String clickAndCaptureOriginZipFlyLookup(String zipCode) {
		logger.info("Record & Click on Origin Zip Fly Lookup");
		testUtil.init(this);
		String address = null;
		weOriginZip.sendKeys(zipCode);
		testUtil.setHardWait(2000);
		if (testUtil.isDisplayed(suggestionList)) {
			address = suggestionList.getText().trim();
			suggestionList.click();
		} else {
			logger.info("Failed to display Origin Fly Lookup");
		}
		return address;
	}

	public String clickAndCaptureDestZipFlyLookup(String zipCode) {
		logger.info("Record & Click on Destination Zip Fly Lookup");
		testUtil.init(this);
		String address = null;
		weDesZip.sendKeys(zipCode);
		testUtil.setExplicitWait(suggestionList, 10);
		if (testUtil.isDisplayed(suggestionList)) {
			address = suggestionList.getText().trim();
			suggestionList.click();
		} else {
			logger.info("Failed to display Destination Fly Lookup");
		}
		return address;
	}

	public void verifyDisclaimerMsgForOverLengthCharge() {
		logger.info("Verify Over length disclaimer message is displayed");
		testUtil.init(this);
		String expected = "Pieces, weights or dimensions may require special handling to ensure the guaranteed service level. Please contact the Estes Solution Center at 1-800-645-3952.";
		String actual = driver.findElement(By.xpath("//span[contains(text(), 'Pieces, weights or dimensions')]"))
				.getText();
		logger.info(actual);
		Assert.assertEquals(actual, expected);
	}

	public void selectOrDeselectOverLengthAccessorials() {
		logger.info("Select Or Deselect Accessorials");
		testUtil.init(this);

		driver.findElement(By.xpath("//span[contains(text(),\" Overlength Freight (20.00' to 27.99') \")]")).click();

	}

	public void verifyOverLength20To27AccessorialIsChecked(boolean existence) {
		logger.info("Verify Overlength 20.00 To 27.99 Freight accessorial is checked or not");
		testUtil.init(this);
		boolean actual = driver.findElement(By.xpath("//*[@id='accessorial12']//input")).isSelected();
		Assert.assertEquals(actual, existence);
	}

	public boolean verifyOverLengthChargeElementExistence() {
		logger.info("Verify element existence");
		testUtil.init(this);

		try {
			driver.findElement(By.xpath("//td[contains(text(),\"OVERLENGTH CHARGE-20.00' - 27.9\")]")).isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}

	}

	public void clickOnStartNewQuote() {
		logger.info("Click on Start New Quote button");
		testUtil.init(this);
		startNewQuote.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnConfirmButton() {
		logger.info("Click on Confirm button");
		testUtil.init(this);
		testUtil.setExplicitWait(confirmButton, 20);
		confirmButton.click();
		testUtil.setHardWait(1000);
	}

	public void verifyMessageforLTL10AM() {
		logger.info("Verify message for LTL 10AM");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver.findElement(By.xpath("(//*[contains(text(),'Please write or type ')])[1]")).getText()
				.trim();
		logger.info("Message is :" + message);
		String expected = "Please write or type Guaranteed LTL Standard 10AM on your BOL (including the driver's copy).";
		Assert.assertEquals(message, expected);
	}

	public void verifyMessageforLTL12PM() {
		logger.info("Verify message for LTL 12PM");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver.findElement(By.xpath("(//*[contains(text(),'Please write or type ')])[2]")).getText()
				.trim();
		logger.info("Message is :" + message);
		String expected = "Please write or type Guaranteed LTL Standard 12PM on your BOL (including the driver's copy).";
		Assert.assertEquals(message, expected);
	}

	public void verifyMessageforLTL5PM() {
		logger.info("Verify message for LTL 5PM");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver.findElement(By.xpath("(//*[contains(text(),'Please write or type ')])[3]")).getText()
				.trim();
		logger.info("Message is :" + message);
		String expected = "Please write or type Guaranteed LTL Standard 5PM on your BOL (including the driver's copy).";
		Assert.assertEquals(message, expected);
	}

	public void verifyAttentionMessageForOverWeight() {
		logger.info("verify alter message");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver
				.findElement(By.xpath("//span[contains(text(),'Your commodity weight exceeds the max of 42,500 lb')]"))
				.getText().trim();
		logger.info("Message is :" + message);
		String expected = "Your commodity weight exceeds the max of 42,500 lbs. If your shipment exceeds maximum weight, give us a call at 1-800-645-3952";
		Assert.assertEquals(message, expected);
	}

	public void verifyReserveShipmentMessageforLTL12PM() {
		logger.info("verify message below reserve shipment");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver.findElement(By.xpath("(//*[contains(text(),'*For Guaranteed and Exclusive Use')])[2]"))
				.getText().trim();
		logger.info("Message is :" + message);
		String expected = "*For Guaranteed and Exclusive Use shipments, please select Reserve Shipment. Our time-critical team will then contact you to finalize details and ensure you receive the highest level of personal service.*";
		Assert.assertEquals(message, expected);
	}

	public void verifyReserveShipmentMessageforLTL5PM() {
		logger.info("verify message below reserve shipment");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver.findElement(By.xpath("(//*[contains(text(),'*For Guaranteed and Exclusive Use')])[3]"))
				.getText().trim();
		logger.info("Message is :" + message);
		String expected = "*For Guaranteed and Exclusive Use shipments, please select Reserve Shipment. Our time-critical team will then contact you to finalize details and ensure you receive the highest level of personal service.*";
		Assert.assertEquals(message, expected);
	}

	public void verifyReserveShipmentMessageforLTL10AM() {
		logger.info("verify message below reserve shipment");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = driver.findElement(By.xpath("(//*[contains(text(),'*For Guaranteed and Exclusive Use')])[1]"))
				.getText().trim();
		logger.info("Message is :" + message);
		String expected = "*For Guaranteed and Exclusive Use shipments, please select Reserve Shipment. Our time-critical team will then contact you to finalize details and ensure you receive the highest level of personal service.*";
		Assert.assertEquals(message, expected);
	}

	public void verifyAlertMessage() {
		logger.info("verify error message");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(1000);
		String message = driver.findElement(By.xpath("//div[@class='ng-star-inserted alert alert-danger']")).getText()
				.trim();
		logger.info("Message is :" + message);
		String expected = "Your commodity weight exceeds the max of 42,500 lbs. If your shipment exceeds maximum weight, give us a call at 1-800-645-3952";
		Assert.assertEquals(message, expected);
	}

	public void verifyCommoditiesTable() {
		logger.info("Verify error message");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 450)");
		testUtil.verifyAndPrintWebTableData(
				"//tr[12]//td[1]//div[1]//app-quote-drawer[1]//div[1]//app-quote-commodities[1]//div[1]");

	}

	public void clickOnFuelSurchargeLink() {
		logger.info("Click on Fuel Surcharge link");
		testUtil.init(this);
		fuelSurcharge.click();
	}

	public void verifyContactUsDialogExistence() {
		logger.info("Verify existence of Contact Us Dialog");
		testUtil.init(this);
		Assert.assertTrue(contactUsDialog.isDisplayed());
	}

	public void clickOnContactMe(String serviceLevel) {
		logger.info("Click Contact Me in " + serviceLevel);
		testUtil.init(this);

		List<WebElement> rowCount = driver.findElements(By.xpath(
				"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[*]"));
		logger.info(rowCount.size());
		for (int i = 0; i < rowCount.size(); i++) {
			int j = i + 1;
			String actualServiceLevel = driver.findElement(By.xpath(
					"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr["
							+ j + "]/td[1]"))
					.getText();
			logger.info(actualServiceLevel);
			if (actualServiceLevel.equals(serviceLevel)) {
				driver.findElement(By.xpath(
						"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr["
								+ j + "]/td[5]/app-service-level-action/span/a"))
						.click();
				break;
			}
		}
	}

	public void verifyQuoteNumberPrefixIs(String ch) throws InterruptedException {
		logger.info("Verify Quote Number Prefix Is " + ch);
		testUtil.init(this);
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		logger.info(quoteNumber);
		assertTrue(quoteNumber.substring(19, 19 + ch.length()).equals(ch));
		logger.info("Prefix is: " + quoteNumber.substring(19, 20));
	}

	public void validateRateQuoteDisclosuresAndTermsOfService(String expected) {
		logger.info("Validate RATE QUOTE DISCLOSURES AND TERMS OF SERVICE");
		testUtil.init(this);
		String actual = driver.findElement(By.xpath("(//h6[text()='RATE QUOTE DISCLOSURES AND TERMS OF SERVICE'])[1]"))
				.getText();
		Assert.assertEquals(actual, expected);
	}

	public void clickOnItemPerPageDropdown(String number) {
		logger.info("click on Items per Page Carrot icon Dropdown");
		testUtil.init(this);
		WebElement carrot = driver.findElement(By.xpath("//div[@class='mat-select-arrow']"));
		testUtil.clickElementJavascript(carrot);
		testUtil.setHardWait(2000);
		spanWithText(number).click();
		testUtil.setHardWait(2000);
	}

	public void verifyServiceLevelPrifixFromQuoteHistoryPage(String sLevel, String prefix) {

		logger.info("verify Service Level Prifix From Quote History Page");
		List<WebElement> serviceLevel = driver
				.findElements(By.xpath("//app-quote-history[1]/mat-card[1]/table[1]/tbody[1]/tr[*]/td[3]"));//// *[contains(text(),'"+sLevel+"')]
		testUtil.setHardWait(1000);
		for (int i = 0; i < serviceLevel.size(); i++) {
			String serLevelName = serviceLevel.get(i).getText();
			if (serLevelName.equals(sLevel)) {
				logger.info(sLevel);
				int j = (i * 2) + 1;
				String quote = driver
						.findElement(
								By.xpath("//app-quote-history[1]/mat-card[1]/table[1]/tbody[1]/tr[" + j + "]/td[1]"))
						.getText();
				logger.info("Quote Number is : " + quote + " ------  Prefix is : " + quote.substring(0, 1));
				Assert.assertEquals(quote.substring(0, 1), prefix);
				break;
			}

		}
	}
	// ALL THE ABOVE SCRIPTS WERE UPDATED AND PASSED ON 11/20/2019

	public void verifyFieldsArePopulatedWithCurrentQuoteData() {
		logger.info("verify Fields Are Populated With Current Quote Data");

		boolean contactName = driver.findElement(By.id("contactName")).isDisplayed();
		assertTrue(contactName);
		testUtil.setHardWait(500);
		logger.info("contact name field displayed");
		boolean email = driver.findElement(By.id("contactEmail")).isDisplayed();
		assertTrue(email);
		testUtil.setHardWait(500);
		logger.info("emai field field displayed");
		boolean phone = driver.findElement(By.id("contactPhone")).isDisplayed();
		assertTrue(phone);
		testUtil.setHardWait(500);
		logger.info("phone number field displayed");
		boolean role = driver.findElement(By.xpath("//mat-select[@id='role']//div[@class='mat-select-trigger']"))
				.isSelected();
		assertFalse(role);
		testUtil.setHardWait(500);
		logger.info("role is selected");
		boolean term = driver.findElement(By.xpath("//mat-select[@id='terms']//div[@class='mat-select-value']"))
				.isSelected();
		assertFalse(term);
		testUtil.setHardWait(500);
		logger.info("term is selelcted");
		boolean date = driver.findElement(By.xpath("//*[@formcontrolname='pickupDate']")).isDisplayed();
		assertTrue(date);
		testUtil.setHardWait(500);
		logger.info("Date is displayed");
		boolean oCountry = driver.findElement(By.xpath(
				"//app-routing-information[1]/mat-card[1]/mat-card-content[1]/form[1]/div[1]//mat-select[1]/div[1]/div[1]/span[1]"))
				.isSelected();
		assertFalse(oCountry);
		testUtil.setHardWait(500);
		logger.info("Origin Country is selected");
		boolean oZip = driver.findElement(By.xpath("//*[@formcontrolname='zip']")).isDisplayed();
		assertTrue(oZip);
		testUtil.setHardWait(500);
		logger.info("Origin Zip fieldis displayed");
		boolean oCity = driver.findElement(By.xpath("//*[@formcontrolname='city']")).isDisplayed();
		assertTrue(oCity);
		testUtil.setHardWait(500);
		logger.info("Origin City field is displayed");
		boolean oState = driver.findElement(By.xpath("//*[@formcontrolname='state']")).isDisplayed();
		assertTrue(oState);
		testUtil.setHardWait(500);
		logger.info("Origin State field is displayed");
		boolean dCountry = driver.findElement(By.xpath(
				"//app-routing-information[1]/mat-card[1]/mat-card-content[1]/form[1]/div[2]/app-point-control[1]/form[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]/mat-select[1]"))
				.isSelected();
		assertFalse(dCountry);
		testUtil.setHardWait(500);
		logger.info("Destination Country is selected");
		boolean dZip = driver.findElement(By.xpath("//*[@formcontrolname='zip']")).isDisplayed();
		assertTrue(dZip);
		testUtil.setHardWait(500);
		logger.info("Destination Zip fieldis displayed");
		boolean dCity = driver.findElement(By.xpath("//*[@formcontrolname='city']")).isDisplayed();
		assertTrue(dCity);
		testUtil.setHardWait(500);
		logger.info("Destination City field is displayed");
		boolean dState = driver.findElement(By.xpath("//*[@formcontrolname='state']")).isDisplayed();
		assertTrue(dState);
		testUtil.setHardWait(500);
		logger.info("Destination State field is displayed");
		boolean pieces0 = driver.findElement(By.id("pieces0")).isDisplayed();
		assertTrue(pieces0);
		testUtil.setHardWait(500);
		logger.info("Pieces field is displayed");
		boolean weight = driver.findElement(By.id("weight0")).isDisplayed();
		assertTrue(weight);
		testUtil.setHardWait(500);
		logger.info("Weight field is displayed");
		boolean length = driver.findElement(By.id("length0")).isDisplayed();
		assertTrue(length);
		testUtil.setHardWait(500);
		logger.info("Length field is displayed");
		boolean height = driver.findElement(By.id("height0")).isDisplayed();
		assertTrue(height);
		testUtil.setHardWait(500);
		logger.info("Height field is displayed");
		boolean width = driver.findElement(By.id("width0")).isDisplayed();
		assertTrue(width);
		testUtil.setHardWait(500);
		logger.info("Width field is displayed");
	}

	public String selectOrDeselectSchoolDelivery() {
		logger.info("Select Or Deselect School Delivery");
		testUtil.init(this);
		String accessorial = driver.findElement(By.xpath("//span[contains(text(),'School Delivery')]")).getText();
		logger.info(accessorial + " is selected");
		driver.findElement(By.xpath("//span[contains(text(),'School Delivery')]")).click();
		return accessorial;
	}

	public void validateRateQuoteInformations2() {
		logger.info("Verify Rate Qoute Informations");
		testUtil.init(this);
		String quote = driver.findElement(By.xpath("(//app-quote-details[1]/app-quote-estimate-details[1]/div[1])[1]"))
				.getText();
		if (!quote.isEmpty()) {
			Assert.assertNotNull(quote);
			logger.info("Quote Details Displayed !!!!!");
			logger.info(quote);
		} else {
			logger.info("Quote Details NOT Dispalyed !!!!");
			Assert.assertFalse(true);
		}
	}

	public void verifyRateQuoteResultPage() {
		logger.info("Validate Rate Quote result page");
		testUtil.init(this);
		testUtil.setExplicitWait(rateQuoteResult, 20);
		boolean resultPage = rateQuoteResult.isDisplayed();
		TestUtil.verifyTrue(resultPage);
	}

	public void validateReserveShipmentModelIsOpened() {
		logger.info("Validate Reserve Shipment model open");
		testUtil.init(this);
		testUtil.setExplicitWait(reserveShipmentModel, 20);
		boolean model = reserveShipmentModel.isDisplayed();
		TestUtil.verifyTrue(model);
	}

	public void enterReserveShipmentEmailAddress(String email) {
		logger.info("Entering Reserve Shipment Email Address");
		testUtil.init(this);
		reserveShipmentEmailAddress.sendKeys(email);
	}

	public void clickReserveShipmentButtonOnDialog() {
		logger.info("Clicking Reserve shipment button on Reserve shipment dialog");
		testUtil.init(this);
		testUtil.setExplicitWait(reserveShipment, 20);
		reserveShipment.click();
	}

	public void clickOnCreateBOLButton() {
		logger.info("Clicking on create BOL");
		testUtil.init(this);
		for (int i = 0; i < createBOL.size(); i++) {
			assertTrue(createBOL.size() > 1, "Failed to display Create BOL button");

			boolean bolIsDisplayed = createBOL.get(i).isDisplayed();
			if (bolIsDisplayed) {
				createBOL.get(i).click();
				break;
			}
		}
	}

	public void validateRateQuoteNumberPrefix(String ch) throws InterruptedException {
		logger.info("validate volume and truckload basic Qoute ID Prifix is V");
		testUtil.init(this);
		WebElement quote = driver.findElement(By.xpath("//tr[1]//td[5]//app-service-level-action[1]//button[1]"));
		testUtil.clickElementJavascript(quote);
		Thread.sleep(1000);
		String quoteNumber = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[1]"))
				.getText();
		logger.info(quoteNumber);
		String actualCharacter = String.valueOf(quoteNumber.split(":")[1].trim().charAt(0));
		logger.info(actualCharacter);
		assertEquals(actualCharacter, ch);
	}

	public void lessThanTruckloadSelected() {
		logger.info("Less Than Truckload is selected");
		testUtil.init(this);
		lessThanTuckload.isSelected();
	}

	public void verifyRateIsZero() {
		logger.info("Verify rate is 0");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		String rate = rateValue.getText();
		logger.info("rate is :" + rate);
		String expected = "0";

		if (rate.equals(expected)) {
			logger.info("Rate value is equal 0 and passed !!!");
			Assert.assertEquals(rate, expected);
		} else {
			logger.info("Rate value is greater than 0 and passed !!!");
			Assert.assertTrue(true);
		}

	}

	public void verifyChargeIsZero() {
		logger.info("Verify charge is 0");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String charge = chargeValue.getText();
		logger.info("charge is :" + charge);
		String expected = "$0.00";
		Assert.assertEquals(charge, expected);

	}

	public void verifyMinChargeItem() {
		logger.info("Verify rate is 0");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		Assert.assertEquals(minChargeValue.isDisplayed(), true);
		Assert.assertNotEquals(minChargeValue.getText(), "$0.00");

	}

	public void verifyShipmentInfoMsg() {
		logger.info("verify message below reserve shipment");
		testUtil.setHardWait(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 250)");
		testUtil.setHardWait(2000);
		String message = shipmentInfoMsg.getText().trim();

		logger.info("Message is :" + message);
		String expected = "Thank you! We have received your request and will contact you to discuss your shipment. In the meantime, you can choose one of the options below to continue the shipment process. If you are not using our BOL, be sure to write your quote number on your document to ensure seamless processing. Have questions? Feel free to give us a call at 1-800-645-3952.";
		Assert.assertEquals(message, expected);

	}
	// Updated on 11/26/2019@3:15

	public void clickOnReviseQouteButton(String serviceLevel) {
		logger.info("click on Revise Qoute Button under Qoute Details");
		List<WebElement> rowCount = driver.findElements(By.xpath("//tbody/tr"));

		for (int i = 1; i < rowCount.size(); i++) {
			String actual = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText();
			int j = i + 1;
			if (actual.contains(serviceLevel)) {
				driver.findElement(By
						.xpath("//tr[" + j + "]/td[1]//app-quote-side-panel[1]/div[1]/app-quote-options[1]/button[1]"))
						.click();
				break;
			}
		}

	}

	public void validateRateQuoteInformations() {
		logger.info("verify Rate Quote Informations");
		testUtil.init(this);
		String quote = driver.findElement(By.xpath("(//*[@class=\"row d-flex flex-wrap\"])[1]")).getText();
		if (!quote.isEmpty()) {
			Assert.assertNotNull(quote);
			logger.info("Quote Details Displayed !!!!!");
			logger.info(quote);
		} else {
			logger.info("Quote Details NOT Dispalyed !!!!");
			Assert.assertFalse(true);
		}

	}

	// UPDATE QZ-3108 ===>
	public String selectAnyQuoteFromHistory() {
		logger.info("Select any quote from Rate Quote History");
		testUtil.init(this);

		try {
			// Selecting first row from the table
			driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).isDisplayed();
			String quoteNum = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).getText();
			driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).click();
			return quoteNum;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.info("Quote History table is not displayed");
		}
		return null;
	}

	public void verifyAndUpdatePickUpDate() {
		testUtil.init(this);
		try {
			driver.findElement(By.xpath("//input[@formcontrolname='pickupDate']")).isDisplayed();
			selectTodayDate();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.info(e);
		}
	}

	public void clickOnClearButtonForDest() {
		logger.info("Click on clear button for destination section");
		testUtil.init(this);
		testUtil.moveTo(desClear);
		desClear.click();
	}

	public void verifyRateQuoteEstimatePageTitle() {
		logger.info("Verify Rate Quote Estimate Page tilte");
		testUtil.setHardWait(2000);
		testUtil.getCurrentPageTitle();
	}

	public void verifyRateQuoteEstimatePage() {
		logger.info("Verify Rate Quote Estimate Page");
		testUtil.setHardWait(2000);
		String pageTitle = driver
				.findElement(By.xpath(
						"//*[@id='main']/app-create-rate-estimate//span[contains(text(),'Rate Quote Estimate')]"))
				.getText();
		Assert.assertEquals(pageTitle, "Rate Quote Estimate");
		logger.info("Page title is :" + pageTitle);
	}

	public void verifyContactName(String name) {
		logger.info("verifying Rate Quote Contact Name ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String contactNme = contactNameField.getAttribute("value");
		logger.info(contactNme);
		testUtil.setHardWait(1000);
		Assert.assertTrue(contactNme.contains(name));
	}

	public void verifyPhoneNumber(String phone) {
		logger.info("verifying Rate Quote Phone Number");
		testUtil.init(this);
		String phoneNum = wePhone.getAttribute("value").trim();
		testUtil.setHardWait(500);
		logger.info(phoneNum);
		Assert.assertTrue(phoneNum.contains(phone));
	}

	public void verifyEmailAddress(String email) {
		logger.info("Verify Rate Quote Email Address");
		testUtil.init(this);
		String emailAddr = weEmail.getAttribute("value");
		testUtil.setHardWait(500);
		logger.info(emailAddr);
		Assert.assertTrue(emailAddr.contains(email));
	}

	public String selectOrDeselectUnloadingServicesRequestedByCons() {
		logger.info("Select Unloading Services Requested By Consignee accessorial");
		testUtil.init(this);
		String accessorial = driver
				.findElement(By.xpath("//span[contains(text(),'Unloading Services Requested By Consignee')]"))
				.getText();
		logger.info(accessorial + "isselected");
		driver.findElement(By.xpath("//span[contains(text(),'Unloading Services Requested By Consignee')]")).click();
		return accessorial;

	}

	public void verifyVTLBasicContactName(String name) {
		logger.info("Verify VTL contact information -name");
		testUtil.init(this);
		testUtil.setExplicitWait(vtlbasicContactName, 20);
		String contactNme = vtlbasicContactName.getText().trim();
		Assert.assertTrue(contactNme.contains(name));

	}

	public void verifyVTLBasicContactPhone(String phone) {
		logger.info("Verify VTL contact information -Phone");
		testUtil.init(this);
		testUtil.setExplicitWait(vtlbasicContactPhone, 20);
		String contactPhone = vtlbasicContactPhone.getText().trim();
		Assert.assertTrue(contactPhone.contains(phone));

	}

	public void verifyVTLBasicContactEmail(String email) {
		logger.info("Verify VTL contact information -Email");
		testUtil.init(this);
		testUtil.setExplicitWait(vtlbasicContactEmail, 20);
		String contactEmail = vtlbasicContactEmail.getText().trim();
		Assert.assertTrue(contactEmail.contains(email));
	}

	public void verifyDiscountChargeItem() {
		logger.info("Verify Discount ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement discount = driver.findElement(By.xpath("//td[contains(text(),'Discount ')]"));
		Assert.assertEquals(discount.isDisplayed(), true);
	}

	public void verifyMexicoFreightChargeChargeItem() {
		logger.info("Verify Mexico Freight Charge ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement mexFC = driver.findElement(By.xpath("//td[contains(text(),'MEXICO FREIGHT CHARGES')]"));
		Assert.assertEquals(mexFC.isDisplayed(), true);
	}

	public void verifyMexicoFuelSurchargeChargeItem() {
		logger.info("Verify Mexico Fuel Surcharge");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		WebElement mexFS = driver.findElement(By.xpath("//a[contains(text(),'MEXICO FUEL SURCHARGE')]"));
		Assert.assertEquals(mexFS.isDisplayed(), true);
	}

	public void verifyChargeItemTableChargesAreNotEqualToZero(String accessorial) {
		logger.info("Validate charges are not equal to Zero in Charge Items table");
		testUtil.init(this);
		String charge = getChargeItemsTableCharges(accessorial);
		Assert.assertNotEquals(charge, "$0.0");
	}

	public void enterFullValueCoverage(String value) {
		logger.info("Enter Full value coverage: " + value);
		testUtil.init(this);
		testUtil.setExplicitWait(fullValueCoverage, 10);
		fullValueCoverage.sendKeys(value);
		testUtil.setHardWait(2000);
	}

	public void selectOrDeselectSchoolDeliveryAccessorial() {
		logger.info("Select or deselect school delivery");
		testUtil.init(this);
		driver.findElement(By.xpath(".//span[text()=' School Delivery ']")).click();

	}

	public void verifyDisclaimerMessageIsDisplayed(String expectedMessage) {
		logger.info("Select or deselect school delivery");
		testUtil.init(this);
		testUtil.setExplicitWait(disclaimerMessage, 10);
		String actualMessage = disclaimerMessage.getText().trim();
		Assert.assertEquals(actualMessage, expectedMessage);

	}

	public void clickFirstCommodityOnCommodityLibrary() {
		logger.info("Click on First Commodity link from Commodity library");
		testUtil.init(this);
		testUtil.setExplicitWait(firstCommodityLink, 20);
		firstCommodityLink.click();

	}

	public void verifyCheckBoxIsChecked() {
		logger.info("Validate I would like additional checkbox is checked");
		testUtil.init(this);
		String properties = checkboxFullCoverage.getAttribute("class");
		Assert.assertTrue(properties.contains("mat-checkbox-checked"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	}

	public void deselectAdditionalCoverage() {
		testUtil.init(this);
		logger.info("deselect additional liability coverage checkbox");
		testUtil.setHardWait(1000);
		checkboxFullCoverage.click();
	}

	public void verifyChargeItemTableChargesEqualToZero(String accessorial) {
		logger.info("Validate Charges are not equal to Zero in Charge Items table");
		testUtil.init(this);
		String charge = getChargeItemsTableCharges(accessorial);
		Assert.assertEquals(charge, "$0.00");
	}

	public void verifyServiceChargeItemDesc(String desc) {
		logger.info("Validate Charge Item table Description details");
		testUtil.init(this);
		String description = null;
		for (int i = 1; i < chargeItemTableRows.size(); i++) {
			description = testUtil.getWebElement(chargeItemTableRows.get(i), "td:nth-child(1)").getText().trim();
			logger.info(description);
			if (description.contains(desc)) {
				break;
			}
		}
		assertTrue(description != null && description.contains(desc));
	}

	public void verifyDisclaimerForLinearFootage() {
		logger.info("Verify disclaimer message for total linear footage");
		testUtil.init(this);
		String updatedQMessage = disclaimer.getText().trim();
		logger.info("Disclaimer message is:" + updatedQMessage);
		Assert.assertTrue(updatedQMessage.contains("total linear footage"));
	}

	public void clickOnArePalletStackable() {
		logger.info("Click on Are Pallet stackable");
		testUtil.init(this);
		WebElement checkBox = driver.findElement(By.xpath("//*[@id='mat-radio-6']/label/div[1]/div[2]"));

		if (!checkBox.isSelected())
			// checkBox.click();
			testUtil.clickElementJavascript(checkBox);
		testUtil.setHardWait(2000);
	}

	public void enterTotalLinearFeet(String linearFt) {
		testUtil.init(this);
		logger.info("Total Linear Feet needed for this shipment");
		linearFeet.clear();
		testUtil.setHardWait(1000);
		linearFeet.sendKeys(linearFt);
	}

	public void deselectSelectedAccessorials(String accName) {
		logger.info("Deselect selected accessorials");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		for (int i = 0; i < closeAccessorialList.size(); i++) {
			String accessorial = closeAccessorialList.get(i).getText().trim();
			if (accessorial.equalsIgnoreCase(accName)) {
				closeAccessorialList.get(i).click();
				break;
			}
		}
	}

	public void verifyAccessorialIsSelected(String accName) {
		boolean found = false;
		logger.info("Verify Accessorial Name");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		for (int i = 0; i < closeAccessorialList.size(); i++) {
			String accessorial = closeAccessorialList.get(i).getText().trim();
			if (accessorial.equalsIgnoreCase(accName)) {
				found = true;
				break;
			} else {
				found = false;
			}
		}
		Assert.assertTrue(found);
	}

	public boolean verifyOverLengthChargesExistence(String accessorials) {
		logger.info("Verify element existence");
		testUtil.init(this);
		try {
			driver.findElement(By.xpath("//td[contains(text(),'" + accessorials + "')]")).isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickOnZipInQuoteDetails(String cityName) {
		logger.info("Click on city name in Quote details section");
		testUtil.init(this);
		spanWithText(cityName).click();
		testUtil.setHardWait(2000);
	}

	public void verifySupportingTerminalInfoDisplayed() {
		logger.info("Verify supporting terminal information pop-up displayed");
		testUtil.init(this);
		Assert.assertTrue(driver.findElement(By.xpath("//app-point-details-modal//h1")).isDisplayed());
	}

	public void verifySupportingTerminalDetails() {
		logger.info("Verify Supporting terminal details");
		testUtil.init(this);
		Assert.assertTrue(
				driver.findElement(By.xpath("//app-point-details-modal//div/label[contains(text(),'Terminal')]"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//app-point-details-modal//div/label[contains(text(),'Address')]"))
						.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//app-point-details-modal//div/label[contains(text(),'Phone')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//app-point-details-modal//div/label[contains(text(),'Fax')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//app-point-details-modal//div/label[contains(text(),'Email')]"))
				.isDisplayed());
	}

	public void clickCloseButtonOnPopup() {
		logger.info("Click close button on popup");
		testUtil.init(this);
		driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
		testUtil.setHardWait(2000);
	}

	public void clickOnSubmit() { // Method without condition to check object on next page
		logger.info("Click on Submit button");
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(weSubmit);
		testUtil.clickElementJavascript(weSubmit);
		testUtil.awaitSpinningWheel(weSubmit);

	}

	

	public void verifyReqFldErrForReqInfo() {
		logger.info("Verify required fields in requester info section");
		testUtil.init(this);
		// Contact name
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='contactName']")).getAttribute("aria-required"),
				"true");
		// email address
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='contactEmail']")).getAttribute("aria-required"),
				"true");
		// Phone#
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='contactPhone']")).getAttribute("aria-required"),
				"true");
		// role
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='role']")).getAttribute("aria-required"), "true");
		// terms
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='terms']")).getAttribute("aria-required"), "true");

	}

	public void verifyReqFldErrForPickupDate() {
		logger.info("Verify required field for Pickup date");
		testUtil.init(this);
		Assert.assertEquals(weTodayDate.getAttribute("aria-required"), "true");
	}

	public void verifyReqFldErrForRoutingInfo() {
		logger.info("Verify required fields in routing info section");
		testUtil.init(this);

		Assert.assertEquals(driver.findElement(By.cssSelector("[formcontrolname='origin'] [formcontrolname='country']"))
				.getAttribute("aria-required"), "true");
		Assert.assertEquals(driver.findElement(By.cssSelector("[formcontrolname='origin'] [formcontrolname='zip']"))
				.getAttribute("aria-required"), "true");
		Assert.assertEquals(driver.findElement(By.cssSelector("[formcontrolname='origin'] [formcontrolname='city']"))
				.getAttribute("aria-required"), "true");
		Assert.assertEquals(driver.findElement(By.cssSelector("[formcontrolname='origin'] [formcontrolname='state']"))
				.getAttribute("aria-required"), "true");

		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='destination'] [formcontrolname='country']"))
						.getAttribute("aria-required"),
				"true");
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='destination'] [formcontrolname='zip']"))
						.getAttribute("aria-required"),
				"true");
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='destination'] [formcontrolname='city']"))
						.getAttribute("aria-required"),
				"true");
		Assert.assertEquals(
				driver.findElement(By.cssSelector("[formcontrolname='destination'] [formcontrolname='state']"))
						.getAttribute("aria-required"),
				"true");

	}

	public void verifyReqFldErrForCommodity() {
		logger.info("Verofy required fields in commodity section");
		testUtil.init(this);

		// class
		Assert.assertEquals(driver.findElement(By.id("shipClass0")).getAttribute("aria-required"), "true");
		// Piece
		Assert.assertEquals(driver.findElement(By.id("pieces0")).getAttribute("aria-required"), "true");
		// Piece type
		Assert.assertEquals(driver.findElement(By.id("pieceType0")).getAttribute("aria-required"), "true");
		// Total Weight
		Assert.assertEquals(driver.findElement(By.id("weight0")).getAttribute("aria-required"), "true");
		// Length
		Assert.assertEquals(driver.findElement(By.id("length0")).getAttribute("aria-required"), "true");
		// Width
		Assert.assertEquals(driver.findElement(By.id("width0")).getAttribute("aria-required"), "true");
		// Height
		Assert.assertEquals(driver.findElement(By.id("height0")).getAttribute("aria-required"), "true");

	}

	public void verifyRateQuoteNumberPrefix(String prefix) {
		logger.info("verify Qoute Number prefix is 2");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String quoteNumber2 = driver.findElement(By.xpath("(//h4[contains(text(),'Rate Quote Number: ')])[3]"))
				.getText();
		logger.info(quoteNumber2);
		assertTrue(quoteNumber2.substring(19, 19 + prefix.length()).equals(prefix));
		logger.info("Prefix is: " + quoteNumber2.substring(19, 20));

	}

	public boolean verifyDataDisplayedInCommodityLibraryModal() {
		logger.info("Verify and add data to commodity library modal");
		testUtil.init(this);
		boolean val = true;
		try {
			driver.findElement(By.xpath("//div[@class='no-results ng-star-inserted']")).isDisplayed();
			val = false;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.info(e);
		}

		driver.findElement(By.xpath("//button[@aria-label='Close dialog']")).click();
		testUtil.setHardWait(2000);
		return val;

	}

	public void verifyLinearFeet(String expected) {
		logger.info("Verify linear feet  " + expected);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)");

		testUtil.setHardWait(1000);

		String actualValue = driver.findElement(By.xpath(
				"//tbody/tr[2]/td[1]/div[1]/app-quote-drawer[1]/div[1]/div[1]/div[1]/app-quote-details[1]/app-vtl-quote-details[1]/div[1]/div[16]"))
				.getText();
		testUtil.setHardWait(4000);
		if (actualValue.contains(expected)) {
			logger.info("System calculated Linear Feet :" + actualValue);
			assertTrue(true);
		} else {
			Assert.fail("Validation failed. Please check actual and expected data");

		}
	}

	public void verifyTotalCalcLinearFeetGreaterThanKeyedValue(int charge) {
		logger.info("Validate Total linear feet greater than keyed in linear feet value");
		testUtil.init(this);
		String actualCharge = systemLF.getAttribute("value");

		int aCharge = Integer.parseInt(actualCharge);

		Assert.assertTrue(charge < aCharge);

	}

	public void verifySuccessMessage() {
		String message = "Success! Shipment request received successfully.";
		logger.info("Validating success message");
		testUtil.init(this);
		String actualMsg = successMessage.getText().trim();
		Assert.assertEquals(actualMsg, message);

	}

	public void clickOnViewQuoteButton() {
		logger.info("Click on view quote button");
		testUtil.init(this);
		viewQuote.click();

	}

	public void verifyCreateRateQuoteTabDisplayed() {
		logger.info("Verify Create Rate Quote Tab is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(createRateQuoteLink, 60);
		createRateQuoteLink.isDisplayed();
		testUtil.setHardWait(1000);
	}

	public void verifyRateQuoteHistoryTabDisplayed() {
		logger.info("Verify Rate Quote History Tab is displayed");
		testUtil.init(this);
		rateQuoteHistoryLink.isDisplayed();
	}

	public void verifyHistoryTitleDisplayed() {
		logger.info("Verify History Title is displayed");
		testUtil.init(this);
		historyTitle.isDisplayed();
	}

	public void verifySearchOptionDisplayed() {
		logger.info("Verify Search Option is displayed");
		testUtil.init(this);
		searchOption.isDisplayed();
		testUtil.setHardWait(1000);
	}

	public void verifyShowAllFilterCheckBoxDisplayed() {
		logger.info("Verify Show All Filter Check Box is displayed");
		testUtil.init(this);
		showAllFilterCheckBox.isDisplayed();
	}

	public void verifyShowAllFilterTextDisplayed() {
		logger.info("Verify Show All Filter Text is displayed");
		testUtil.init(this);
		String sText = showAllFilterText.getText();
		logger.info("Given text: " + sText);

	}

	public void verifyClearButtonDisplayed() {
		logger.info("Verify Clear Button is displayed");
		testUtil.init(this);
		clearButton.isDisplayed();
	}

	public void verifySearchButtonDisplayed() {
		logger.info("Verify Search Button is displayed");
		testUtil.init(this);
		searchButton.isDisplayed();
	}

	public void verifyQuoteHistoryTableHeader(List<String> headerInput) {
		logger.info("Verify Quote History Table column headings");
		testUtil.init(this);
		WebElement headings = driver.findElement(By.tagName("table"));
		List<WebElement> individualHeadings = headings.findElements(By.tagName("th"));
		List<String> columnheader = new ArrayList<>();
		for (WebElement allele : individualHeadings) {
			columnheader.add(allele.getText());
		}
		logger.info(columnheader);
		boolean booVal = headerInput.equals(columnheader);
		logger.info("Quote History Table column headings found to be: " + booVal);
	}

	public void clikOnAdvanceSearchButton() {
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(advanceSearchButton);
		logger.info("click on Advance Search Button");
		testUtil.clickElementJavascript(advanceSearchButton);
	}

	public void selectFromDateSearchOptions(String dateval) {
		logger.info("Enter From Date Range");
		testUtil.init(this);
		testUtil.setHardWait(500);
		testUtil.clickElementJavascript(fromDate);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + dateval + "';", fromDate);
		fromDate.sendKeys(Keys.TAB);
	}

	public void selectToDateSearchOptions() {
		logger.info("Enter today's date");
		testUtil.init(this);
		testUtil.setHardWait(500);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate localDate = LocalDate.now();
		testUtil.clickElementJavascript(toDate);
		String dateval2 = dtf.format(localDate);
		toDate.click();
		toDate.sendKeys(dateval2);
		toDate.sendKeys(Keys.TAB);
	}

	public void enterQuoteNumber(String quoteNum) {
		logger.info("Enter Captured Quote Number");
		testUtil.init(this);
		testUtil.clickElementJavascript(quoteNumTextBox);
		quoteNumTextBox.click();
		testUtil.setHardWait(500);
		quoteNumTextBox.sendKeys(quoteNum);
		testUtil.setHardWait(1000);

	}

	public void clikOnSearchButton() {
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(searchButton);
		logger.info("click on Search Button");
		testUtil.clickElementJavascript(searchButton);
	}

	public void clikOnClearButton() {
		testUtil.init(this);
		testUtil.WaitForTheElementClickable(clearButton);
		logger.info("click on Clear Button");
		testUtil.clickElementJavascript(clearButton);
	}

	public void verifySearchCountBetweenTheGivenDates() {

		logger.info("Verify Search COunt Between The Given Date Range");
		testUtil.init(this);
		WebElement rangecount = driver.findElement(
				By.xpath("//*[@id='main']/app-rate-quote/app-quote-history/mat-card/mat-paginator/div/div[2]/div"));
		String rangecount2 = rangecount.getText();
		logger.info("get text = " + rangecount2);
		String[] rangecount3 = rangecount2.split("\\s+");
		logger.info("final count: " + rangecount3[4]);
	}

	public void verifyFromDateCalenderDisplayed() {
		logger.info("Verify From Date Calender Button is displayed");
		testUtil.init(this);
		fromDateWidget.isDisplayed();
	}

	public void verifyToDateCalenderDisplayed() {
		logger.info("Verify To Date Calender Button is displayed");
		testUtil.init(this);
		toDateWidget.isDisplayed();
	}

	public void clickOnContactMeLinkByServiceLevel(String serviceLevel) {
		logger.info("Validate Contact Me link by service level: " + serviceLevel);
		testUtil.init(this);
		for (int i = 0; i <= serviceLevelTableRows.size(); i++) {
			int j = i + 1;
			String serviceLvl = driver
					.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr[" + j + "]/td[1]")).getText()
					.trim();
			if (serviceLvl.equalsIgnoreCase(serviceLevel)) {
				String name = driver.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr[" + j + "]/td[5]"))
						.getText().trim();
				if (name.equals("Contact Me")) {
					driver.findElement(By.xpath("//*[@id='main']//mat-card/table/tbody/tr[" + j + "]/td[5]")).click();
					break;
				}
			}
		}
	}

	public void clickOnEstesTruckloadBrokerageRadioButton() {
		logger.info("Click On estes truckload brokerage Radio Button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(truckloadRadioBtn);
		testUtil.setHardWait(2000);
	}

	public void clickOnAccountSearchLink() {
		logger.info("Click on account search link");
		testUtil.init(this);

		testUtil.WaitForTheElementClickable(accountSearchLink);

		testUtil.setHardWait(1000);

		testUtil.clickElementJavascript(accountSearchLink);

		testUtil.setHardWait(3000);

	}

	public void enterAccountNumberInAccSearch(String accNumber) {
		logger.info("Enter account number");
		testUtil.init(this);
		accSearch.clear();
		testUtil.setHardWait(2000);
		accSearch.click();
		accSearch.sendKeys(accNumber);
		testUtil.setHardWait(2000);

	}

	public void clickOnFirstAccountNumber() {
		logger.info("Click on first account number");
		testUtil.init(this);
		testUtil.setExplicitWait(firstAccNum, 60);
		testUtil.clickElementJavascript(firstAccNum);
		testUtil.setHardWait(2000);
	}

	public void enterPickupDate(String date) {
		logger.info("Enter pickup date: " + date);
		testUtil.init(this);
		testUtil.setExplicitWait(pickupDate, 60);
		pickupDate.sendKeys(date);

	}

	public void verifyTheMessageDisplayed(String expMsg) {
		logger.info("Verify scheduled delivery message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = difficultPickupMessage.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void verifyPickupDateMessageDisplayed(String expMsg) {
		logger.info("Verify pickup date message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = pickUpMessage.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void selectSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		testUtil.setHardWait(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		testUtil.WaitForTheElementClickable(weSubmit);
		testUtil.setExplicitWait(weSubmit, 60);
		testUtil.clickElementJavascript(weSubmit);
		testUtil.setExplicitWait(weSubmit, 60);
	}

	public void enterWeight(String wgt) {
		logger.info("Enter total weight: " + wgt);
		testUtil.init(this);
		testUtil.setExplicitWait(formWeight, 120);
		formWeight.sendKeys(wgt);
		testUtil.setHardWait(1000);
	}

	public void clickOnBookShipmentButton() {
		logger.info("Click on book shipment button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		bookShipment.click();
		testUtil.setHardWait(2000);
	}

	public void verifyTuckloadBasicCharge(String serviceLevel, boolean basicCharge) {
		logger.info("Verify basic charge in " + serviceLevel);
		testUtil.init(this);

		String actual = driver
				.findElement(By.xpath(
						"//*[@id='main']/app-rate-quote/app-truckload-results/mat-card/table/tbody/tr[1]/td[4]/span"))
				.getText();

		if (basicCharge) {
			logger.info("Verify Basic Charge is not blank");
			Assert.assertNotNull(actual);
			logger.info("basic charges are :" + actual);
		} else {
			logger.info("Verify Basic Charge is blank");
			Assert.assertEquals(actual.isEmpty(), true);
		}

	}

	public String recordQuoteNum() {
		logger.info("Capture quote number");
		testUtil.init(this);

		testUtil.setHardWait(2000);
		String quoteNumber = driver
				.findElement(By.xpath("//app-shipment-details/mat-card/mat-card-content/div/div/div[1]/div[1]"))
				.getText().substring(14).trim();
		logger.info("Quote Number is :" + quoteNumber);
		return quoteNumber;

	}

	public String recordQuoteNumberFromResultTable() {

		logger.info("Record quote number");
		testUtil.init(this);
		// WebElement
		// ele=driver.findElement(By.xpath("//tbody/tr[2]/td/div/app-quote-drawer/div/app-rate-quote-action-header/div/div[1]/h4"));
		WebElement ele = driver.findElement(
				By.xpath("//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-drawer/div/app-rate-quote-action-header/div/div[1]/h4"));
		String quoteNum = ele.getText();
		logger.info(quoteNum);
		String quote = ele.getText().substring(19);
		logger.info("The recorded quote number is: " + quote);

		return quote;

	}

	// new method for getting quote number

	public void selectEquipmentType(String type) {
		logger.info("Enter equipment Type");
		testUtil.init(this);
		testUtil.clickElementJavascript(equipmentType);
		testUtil.setHardWait(2000);

		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + type + "')]")).click();
	}

	public void expandResultsSection() {
		logger.info("Click on arrow to expand results");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		driver.findElement(By.xpath("//tbody/tr[1]/td[5]/app-truckload-actions/span[1]")).click();
		testUtil.setHardWait(2000);
	}

	public void verifyQuoteDetailsPageDisplayed() {
		logger.info("Verify quote details page displayed");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		boolean verifyMessage = quoteDetailsText.isDisplayed();
		Assert.assertEquals(verifyMessage, true);
		testUtil.setHardWait(1000);
	}

	public void verifyBookTruckloadShipmentPageDisplayed() {
		logger.info("Verify book truckload shipment page displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(tlShipmentPage, 30);
		boolean verifyPage = tlShipmentPage.isDisplayed();
		Assert.assertEquals(verifyPage, true);
		testUtil.setHardWait(1000);
	}

	public void verifyCommodityWgtMessageDisplayed(String expMsg) {
		logger.info("Verify message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = commodityMessage.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void verifyTodaysPickupDateMessageDisplayed(String expMsg) {
		logger.info("Verify message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = todaysPickupMsg.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void clickOnHazardousMaterialsCheckBox() {
		logger.info("Click on Hazardous Materials CheckBox");
		testUtil.init(this);
		testUtil.setExplicitWait(hazardCheckBx, 120);
		testUtil.clickElementJavascript(hazardCheckBx);
		testUtil.setHardWait(2000);
	}

	public void verifyHazmatMessageDisplays(String expMsg) {
		logger.info("Verify attention message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = driver.findElement(By.xpath("//div[@class='ng-star-inserted alert alert-danger']"))
				.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void verifyContactMeByServiceLevel(String serviceLvl) {
		logger.info("Verifying contact me link by service level");
		testUtil.init(this);
		WebElement contactMe = testUtil.filterXpath("//td[text()=\" " + serviceLvl + " \"]/following-sibling::td[4]",
				null, null, 1, "");
		Assert.assertTrue(contactMe.getText().contains("Contact Me"));
		testUtil.setHardWait(1000);
	}

	public void verifyEstimatedDeliveryDateIsBlank() {
		logger.info("Verify Estimated Delivery Date Is blank");
		testUtil.init(this);

		String date = driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText();

		Assert.assertEquals(date.isEmpty(), true);

	}

	public void verifyRequestPickupButtonIsDisplayed() {

		logger.info("Verify Request Pickup button is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(requestPickup, 60);
		Assert.assertEquals(requestPickup.isDisplayed(), true);

	}

	public void verifyRequestPickupButtonIsNotDisplayed() {

		logger.info("Verify Request Pickup button is not displayed");
		testUtil.init(this);

		testUtil.setHardWait(3000);
		Assert.assertEquals(requestPickup.isDisplayed(), false);

	}

	public void verifySuccessMessageDisplayed() {
		String message = "Success! Using the information you provided, we will contact you shortly. In the meantime, feel free to give us a call at 1-866-378-3748, extension 31 if you have any questions.";
		logger.info("Validating success message");
		testUtil.init(this);
		String actualMsg = success.getText().trim();
		Assert.assertEquals(actualMsg, message);

	}

	public void verifyContactMeButtonHasCheckIconAndIsDisabled() {

		logger.info("Verify the *Contact Me* button has a check icon next to it and cannot be clicked");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		checkIcon.isDisplayed();
		testUtil.setHardWait(2000);
		Assert.assertEquals(!checkIcon.isEnabled(), false);

	}

	public void verifyADestinationMessageDisplayed(String expMsg) {
		logger.info("Verify a message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = destMessage.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void verifyAOriginMessageDisplayed(String expMsg) {
		logger.info("Verify a message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = originMessage.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public void verifyLinearFeet2(String expected) {
		logger.info("Verify linear feet  " + expected);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,900)");

		testUtil.setHardWait(1000);

		String actualValue = driver.findElement(By.xpath(
				"//tbody/tr[10]/td[1]/div[1]/app-quote-drawer[1]/div[1]/div[1]/div[1]/app-quote-details[1]/app-vtl-quote-details[1]/div[1]/div[16]"))
				.getText();

		testUtil.setHardWait(4000);
		if (actualValue.contains(expected)) {
			logger.info("System calculated Linear Feet :" + actualValue);
			assertTrue(true);
		} else {
			Assert.fail("Validation failed. Please check actual and expected data");

		}
	}

	public void clickOnCaretSymbol() {
		testUtil.init(this);
		logger.info("Click on Caret symbol");
		testUtil.assetWaitClickable(null, caretSymbol, 10, 200, TimeUnit.MILLISECONDS);
		caretSymbol.click();
	}

	public void clickOnCraretSymbolIfQuoteDetailsNotDisplayed() {
		testUtil.init(this);
		logger.info("Click on crate if rate quote info is not displayed");

		WebElement quoteDetails = driver.findElement(
				By.xpath("//tbody/tr[2]/td/div/app-quote-drawer/div/app-rate-quote-action-header/div/div[1]/h4"));

		if (quoteDetails.isDisplayed()) {

			String str = quoteDetails.getText().trim();
			logger.info("Rate quote info is displayed :" + str);
			Assert.assertTrue(str.contains("RATE QUOTE NUMBER"));
		} else {

			WebElement caretBtn = driver
					.findElement(By.xpath("//*[@id='95']/td[5]/app-service-level-action/div/span[3]"));

			caretBtn.click();

		}
	}

	public void clickOnEmailQuoteButton() {
		logger.info("Click on email quote button");
		testUtil.init(this);
		emailQuote.click();

	}

	public void enterYourEmails(String email1, String email2) {
		logger.info("Enter email address");
		testUtil.init(this);
		emailAddresses.sendKeys(email1);
		emailAddresses.sendKeys(Keys.ENTER);
		emailAddresses.sendKeys(email2);

	}

	public void clickOnSendButton() {
		logger.info("Click on send button");
		testUtil.init(this);
		sendBtn.click();

	}

	public void verifySuccessMessageDisplayed(String expMsg) {
		logger.info("Verify a success message displays");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String actualMsg = successMsg.getText();
		logger.info(actualMsg);
		assertEquals(actualMsg, expMsg);
	}

	public String recordWeight(String wgt) {
		logger.info("Record weight ");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		testUtil.init(this);

		testUtil.setHardWait(1000);
		String weight = driver.findElement(By.xpath("//td[contains(text(),'" + wgt + "')]")).getText();
		logger.info(weight);
		return weight;
	}

	public void clickOnTankerEndorsementCheckBox() {
		logger.info("Click on Tanker Endorsement CheckBox");
		testUtil.init(this);
		testUtil.setExplicitWait(tankerCheckBx, 120);
		testUtil.clickElementJavascript(tankerCheckBx);
		testUtil.setHardWait(2000);
	}

	public void enterUnNumber(String unNum) {
		logger.info("Enter Un number");
		testUtil.init(this);
		unNumber.clear();
		unNumber.click();
		testUtil.setHardWait(2000);
		unNumber.sendKeys(unNum);
	}

	public void enterUnNumber1(String unNum) {
		logger.info("Enter Un number");
		testUtil.init(this);
		unNumber1.clear();
		unNumber1.click();
		testUtil.setHardWait(2000);
		unNumber1.sendKeys(unNum);

	}

	public void enterUnNumber2(String unNum) {
		logger.info("Enter Un number");
		testUtil.init(this);
		unNumber2.clear();
		unNumber2.click();
		testUtil.setHardWait(2000);
		unNumber2.sendKeys(unNum);

	}

	public void enterHazmatPieceCount(String count) {
		logger.info("Enter Un number");
		testUtil.init(this);
		hazmatPieceCount.clear();
		hazmatPieceCount.click();
		testUtil.setHardWait(2000);
		hazmatPieceCount.sendKeys(count);
	}

	public void enterHazmatPieceCount1(String count) {
		testUtil.init(this);
		logger.info("Enter piece count");
		hazmatPieceCount1.clear();
		hazmatPieceCount1.click();
		testUtil.setHardWait(2000);
		hazmatPieceCount1.sendKeys(count);

	}

	public void enterHazmatPieceCount2(String count) {
		testUtil.init(this);
		logger.info("Enter piece count");
		hazmatPieceCount2.clear();
		hazmatPieceCount2.click();
		testUtil.setHardWait(2000);
		hazmatPieceCount2.sendKeys(count);

	}

	public void enterHazmatWeight(String weight) {
		logger.info("Enter Un number");
		testUtil.init(this);
		hazmatWeight.clear();
		hazmatWeight.click();
		testUtil.setHardWait(2000);
		hazmatWeight.sendKeys(weight);
	}

	public void enterHazmatWeight1(String weight) {
		logger.info("Enter weight");
		testUtil.init(this);
		hazmatWeight1.clear();
		hazmatWeight1.click();
		testUtil.setHardWait(2000);
		hazmatWeight1.sendKeys(weight);
	}

	public void enterHazmatWeight2(String weight) {
		logger.info("Enter weight");
		testUtil.init(this);
		hazmatWeight2.clear();
		hazmatWeight2.click();
		testUtil.setHardWait(2000);
		hazmatWeight2.sendKeys(weight);
	}

	public void enterHazmatDescription(String description) {
		logger.info("Enter Freight description ");
		testUtil.init(this);
		testUtil.assetWait(null, hazmatDescription, 10, 200, TimeUnit.MILLISECONDS);
		hazmatDescription.clear();
		testUtil.setHardWait(500);
		hazmatDescription.click();
		hazmatDescription.sendKeys(description);
	}

	public void enterHazmatDescription1(String description) {
		logger.info("Enter Freight description ");
		testUtil.init(this);
		testUtil.assetWait(null, hazmatDescription, 10, 200, TimeUnit.MILLISECONDS);
		hazmatDescription1.clear();
		testUtil.setHardWait(500);
		hazmatDescription1.click();
		hazmatDescription1.sendKeys(description);
	}

	public void enterHazmatDescription2(String description) {
		logger.info("Enter Freight description ");
		testUtil.init(this);
		testUtil.assetWait(null, hazmatDescription2, 10, 200, TimeUnit.MILLISECONDS);
		hazmatDescription2.clear();
		testUtil.setHardWait(500);
		hazmatDescription2.click();
		hazmatDescription2.sendKeys(description);
	}

	public void selectPackagingCode(String codeName) {
		logger.info("Select Packaging Code " + codeName);
		testUtil.init(this);
		testUtil.clickElementJavascript(hazmatPackagingCode);
		WebElement code = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + codeName + "')]"));

		testUtil.clickElementJavascript(code);

	}

	public void selectPackagingCode1(String code) {
		testUtil.init(this);
		logger.info("Select packaging code" + code);
		testUtil.clickElementJavascript(hazmatPackagingCode1);
		WebElement packagingCode = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + code + "')]"));

		testUtil.clickElementJavascript(packagingCode);
	}

	public void selectPackagingCode2(String code) {
		testUtil.init(this);
		logger.info("Select packaging code" + code);
		testUtil.clickElementJavascript(hazmatPackagingCode2);
		WebElement packagingCode = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + code + "')]"));

		testUtil.clickElementJavascript(packagingCode);
	}

	public void selectCommodityType(String commodityType) {
		logger.info("Select Commodity Type " + commodityType);
		testUtil.init(this);
		testUtil.clickElementJavascript(hazmatCommodityType);
		WebElement type = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + commodityType + "')]"));
		testUtil.clickElementJavascript(type);
	}

	public void selectCommodityType1(String commodityType1) {
		logger.info("Select Commodity Type " + commodityType1);
		testUtil.init(this);
		testUtil.clickElementJavascript(hazmatCommodityType1);
		WebElement type = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + commodityType1 + "')]"));
		testUtil.clickElementJavascript(type);
	}

	public void selectCommodityType2(String commodityType1) {
		logger.info("Select Commodity Type " + commodityType1);
		testUtil.init(this);
		testUtil.clickElementJavascript(hazmatCommodityType2);
		WebElement type = driver
				.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + commodityType1 + "')]"));
		testUtil.clickElementJavascript(type);
	}

	public void clickOnOverlengthFreight() {
		logger.info("Click on \"Overlengh Freight\"");
		testUtil.init(this);
		testUtil.setExplicitWait(overlenghtCheckbox, 90);
		overlenghtCheckbox.click();

	}

	public void clickOnOverlengthFreight16to1999() {
		logger.info("Click on \"Overlength Freight\"");
		testUtil.init(this);
		testUtil.setExplicitWait(overlengthCheckbox16to1999, 90);
		overlengthCheckbox16to1999.click();

	}

	public void clickOnOverlengthFreight8ft11in() {
		logger.info("Click on \"Overlength Freight\" 8.00-11.99");
		testUtil.init(this);
		testUtil.setExplicitWait(overlength8ft11in, 60);
		overlength8ft11in.click();

	}

	public void clickOnResidentialDelivery() {

		testUtil.init(this);
		logger.info("Click on \"Residential Delivery\" under accessorials");
		testUtil.setExplicitWait(residentialDelivery, 60);
		testUtil.filterXpath("//*[@id='accessorial21']/label/div", residentialDelivery, null, 1, null);
		// residentialDelivery.click();
	}

	// Reusable method for clickingon Accessorials

	public void selectOrDeselectAccessorialsList(String accessorial) {
		logger.info("Select Or deselect Accessorials");
		testUtil.init(this);
		WebElement ele = testUtil.filterXpath("//span[text()=\" " + accessorial + " \"]/preceding-sibling::div", null,
				null, 4, "checkbox");

		ele.click();
//		testUtil.clickElementJavascript(ele);
	}

	public void clickAddCommodity() {
		logger.info("Click Add Commodity button");
		testUtil.init(this);
		testUtil.clickElementJavascript(addCommodity);
	}

	public void verifyOverlengthIsSelected() {
		logger.info("Verify overlength \"'8.00 -11.99'\" under accessorials is selected");
		testUtil.init(this);
		WebElement overlenght = driver.findElement(By.xpath("//mat-checkbox[@id='accessorial11']"));
		boolean isEnabled = overlenght.isDisplayed();

		if (isEnabled) {
			logger.info("The checkbox is selected");
		} else {
			logger.info("The checkbox is Not selected");
		}

	}

	public void verifyOverlengthIsDisplayed() {
		logger.info("Verify overlength \"'8.00 -11.99'\" under accessorials is displayed");
		testUtil.init(this);
		WebElement overlength = testUtil.filterSelector("span", null, null, 0, "11.99");
		String value = overlength.getText();
		logger.info("The overlength value selected is :" + value);
		Assert.assertEquals(overlength.isDisplayed(), true);

	}

	public void clickOnPickupRequestButton() {
		logger.info("Click on the pickup request button");
		testUtil.init(this);
		testUtil.assetWait(null, pickupRequestBtn, 10, 200, TimeUnit.MICROSECONDS);
		pickupRequestBtn.click();

	}

	public void verifyPickupRequestPage() {
		logger.info("Verify Pickup Request page is displayed");
		testUtil.init(this);

		WebElement ele = driver
				.findElement(By.xpath("//*[@id='main']/app-pickup-request/lib-routable-component-header/h1/span"));
		String title = ele.getText();
		logger.info("The title of the page is: " + title);
		Assert.assertEquals(ele.isDisplayed(), true);

	}

	public void clickOnCreateBOL3() {
		logger.info("Click on Create BOL");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		createBol3.click();
		testUtil.setHardWait(1000);

	}

	public void clickOnCreateBOL5PM() { // 5PM
		logger.info("Click on Create BOL");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		createBol5pm.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnCreateBOL12PM() { // 12PM
		logger.info("Click on Create BOL");
		testUtil.init(this);
		testUtil.setHardWait(1000);
		createBol12pm.click();
		testUtil.setHardWait(1000);
	}

	public void clickOnReserveShipmentButton3() { // 5PM
		logger.info("Click on Reserve Shipment button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		reserveShipment3.click();
	}

	public void selectIAcceptTheTermsAndConditionsCheckBox5PM() {
		logger.info("Select I accept the Terms and Conditions check Box");
		testUtil.init(this);
		WebElement checkBox = driver
				.findElement(By.xpath("//tr[6]//app-quote-side-panel//app-guaranteed-next-steps//mat-checkbox//label"));
		testUtil.moveTo(checkBox);
		checkBox.click();
	}

	public void clickOnAddAdditionalRowButton() {
		testUtil.init(this);
		logger.info("Click on add additional row button");
		testUtil.setExplicitWait(addAddiontialRowBtn, 20);
		addAddiontialRowBtn.click();

	}

	public void clickGetQuoteOrClickCaret(String serviceLevel) {
		logger.info("Click quote button, or click the caret if the quote button was already clicked");
		WebElement quote = driver
				.findElement(By.xpath("//td[contains(text(),'" + serviceLevel + "')]/parent::tr/td[5]"));
		quote.click();
		testUtil.setHardWait(3000);
	}

	public void validateChargeItem(String quoteNumber, String description) {
		logger.info("Validate [" + description + "] for quote number: [" + quoteNumber + "]");
		WebElement chargeItem = driver.findElement(By.xpath("//h4[contains(text(),'" + quoteNumber
				+ "')]/parent::div/parent::div/parent::app-rate-quote-action-header/parent::div/app-quote-charge-items/div/table/tbody/tr/td[contains(text(),\""
				+ description + "\")]"));
		assertTrue(chargeItem.isDisplayed());
	}

	public void validateChargeItemIsIncluded(String quoteNumber, String description) {
		logger.info("Validate [" + description + "] for quote number: [" + quoteNumber + "] is included in the price");

		WebElement chargeItem = driver.findElement(By.xpath("//h4[contains(text(),'" + quoteNumber
				+ "')]/parent::div/parent::div/parent::app-rate-quote-action-header/parent::div/app-quote-charge-items/div/table/tbody/tr/td[contains(text(),\""
				+ description + "\")]"));
		WebElement chargeItemIsIncluded = chargeItem
				.findElement(By.xpath("./following-sibling::td[contains(text(),'Included')]"));
		assertTrue(chargeItemIsIncluded.isDisplayed());
	}

	public void clickOnGetQuoteByServiceLevel(String serviceLevel) {
		logger.info("Click on get quote by service level on the results");
		testUtil.init(this);

		List<WebElement> row = driver.findElements(By.xpath("//tbody/tr[*]"));

		for (int i = 0; i <= row.size(); i++) {

			String str = row.get(0).getText().trim();

			logger.info("Servier level :" + str);

			int j = i + 1;

			String serviceLvl = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[1]")).getText().trim();

			if (serviceLvl.equalsIgnoreCase(serviceLevel)) {

				WebElement ele = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[5]"));
				ele.click();

				String str1 = ele.getText();
				logger.info("Element displayed:" + str1);
				Assert.assertTrue(str1.contains("QUOTE".trim()));
				break;

			} else {

				logger.info("Service level not found");

			}
		}

	}

	public void verifyMessageDisplayed() {
		String message = "Success! Using the information you provided, a response will be sent to you via email within approximately 1 hour. Requests submitted after 5 p.m. M-F EST will receive a response the next business day.";
		logger.info("Validating success message");
		testUtil.init(this);
		String actualMsg = contactMessage.getText().trim();
		logger.info(actualMsg);
		Assert.assertEquals(actualMsg, message);

	}

	public void clickOnReviseQouteButtonBelowQouteOption() {
		logger.info("Click on Revise Quote button under Quote details");
		testUtil.init(this);
		testUtil.filterXpath("//button", null, null, 0, "Revise", "w-100").click();
		testUtil.setHardWait(1000);
	}

	public String recordQuoteNumberFromResultTable(String serviceLevel) {
		logger.info("Capture quote number");
		testUtil.init(this);

		WebElement quoteDrawer = testUtil.filterSelector("app-quote-drawer", null, null, 1, serviceLevel);
		testUtil.setHardWait(500);
		WebElement quoteNumHeader = testUtil.filterSelector("h4", null, quoteDrawer, 0, "Rate Quote Number: ");
		// String quoteNumber = quoteNumHeader.getText().substring(19);
		String quoteNumber = testUtil.getTextFromElement(quoteNumHeader).substring(19);
		System.out.println(quoteNumber);
		return quoteNumber;
	}

	public void clikOnRateQuoteSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		WebElement submitButton = testUtil.filterSelector("button", null, null, 0, "rateQuoteSubmitButton");
		testUtil.clickElementJavascript(submitButton);
	}

	public void clikOnRateEstimateSubmitButton() {
		logger.info("Click on Submit button");
		testUtil.init(this);
		WebElement submitButton = testUtil.filterSelector(null, submit, null, 0, "<");
		testUtil.clickElementJavascript(submitButton);
	}

	public void verifyValidationErrorMessage() {
		logger.info("ERROR: There are validation errors in the form. Please review the form and submit again.");
		String error = testUtil.filterSelector("snack-bar-container", null, null, 1, "ERROR").getText();
		assertTrue(true, error);
	}

	public void selectOrDeselectCheckbox(String label) {
		testUtil.init(this);
		WebElement checkbox = testUtil.filterXpath("//*[text()=\" " + label + " \"]/preceding-sibling::div/input", null,
				null, 0, ">");
		testUtil.clickElementJavascript(checkbox);
		testUtil.setHardWait(700);
	}

	// Origin ZIP
	public void enterOriginZip(String originZipCode) {
		logger.info("Enter origin zip code");
		testUtil.init(this);

	      logger.info("Enter origin zip code");
	        testUtil.init(this);
	        //first we find the form containing the origin zip
	        WebElement originForm = testUtil.filterSelector("app-point-control", null, null, 1, "formcontrolname=\"origin\"");
	        //next we locate the clear button within the form and click it
	        WebElement clearButton = testUtil.filterXpath(".//button", null, originForm, 0, "Clear");
	        testUtil.clickElementJavascript(clearButton);
	        //Next we locate the input within the form and use JavaScript to set input
	        WebElement originZipInput = testUtil.filterXpath(".//input", null, originForm, 0, "zip");
	        originZipInput.sendKeys(originZipCode);
	        //Finally, we select from available options
	        WebElement option = testUtil.filterSelector("mat-option", null, null, 2, originZipCode);
	        testUtil.clickElementJavascript(option);
	    }
	

	// Destination ZIP
	  public void enterDesZip(String destZipCode) {
	        logger.info("Enter destination zip code");
	        testUtil.init(this);
	        //first we find the form containing the destination zip
	        WebElement destForm = testUtil.filterSelector("app-point-control", null, null, 1, "formcontrolname=\"destination\"");
	        //next we locate the clear button within the form and click it
	        WebElement clearButton = testUtil.filterXpath(".//button", null, destForm, 0, "Clear");
	        testUtil.clickElementJavascript(clearButton);
	        //Next we locate the input within the form and use JavaScript to set input
	        WebElement destZipInput = testUtil.filterXpath(".//input", null, destForm, 0, "zip");
	        destZipInput.sendKeys(destZipCode);
	        //Finally, we select from available options
	        WebElement option = testUtil.filterSelector("mat-option", null, null, 2, destZipCode);
	        testUtil.clickElementJavascript(option);
	        
	  }

	public void verifyToastMessage() {
		testUtil.init(this);
		WebElement toast = testUtil.filterSelector("*", null, null, 1, "Success! Using the information you provided");
		System.out.println("Full toast message: " + toast.getText());
		Assert.assertTrue(toast.isDisplayed());
	}

//		public void verifyChargeItemsTableDescription(String item) {
//
//			logger.info("Validate Charge Item table description details");
//
//			testUtil.init(this);
//
//			String desc;
//
//			boolean flag = false;
//
//			List<WebElement> chargeItemList = driver.findElements(By.xpath("//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[12]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table/tbody/tr[*]"));
//
//			for(int i=0;i<chargeItemList.size();i++) {
//
//				desc = chargeItemList.get(i).findElement(By.xpath("td")).getText();
//
//				logger.info("Desc : "+desc);
//
//				if(desc.contains(item)) {
//
//					flag = true;
//
//					break;
//
//				}
//
//			}
//
//			if(flag) {
//
//				logger.info("Found the Charge Item in the table.");
//
//			}else {
//
//				Assert.fail("Failed to display Charge Item description: "+item);
//
//			}
//
//		}

//		public void verifyTotalEstimatedFreightCharges(String desc) {
//
//			logger.info("Verify Total Estimated Freight Charges.");
//
//			testUtil.init(this);
//
//			WebElement totalFreightCharges = driver.findElement(By.xpath("//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[12]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table/tfoot/tr/td[1]"));
//
//			String totalFreightChargesDesc = totalFreightCharges.getText();
//
//			logger.info("totalFreightChargesDesc : "+totalFreightChargesDesc);
//
//			if(totalFreightChargesDesc.contains(desc)) {
//
//				logger.info("Found Total Estimated Freight Charges desc.");
//
//			}else {
//
//				Assert.fail("Total Estimated Freight Charges desc not found.");
//
//			}
//
//		}

	public void verifyTotalEstimatedFreightChargesNotEqualToZero() {

		logger.info("Validate Total Estimated Freight Charges not equal to Zero in Charge Items table");

		testUtil.init(this);

		WebElement totalEstimate = driver.findElement(By.xpath(

				"//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[2]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table/tfoot/tr/td[2]"));

		String totalEstimateVal = totalEstimate.getText();

		Assert.assertNotEquals(totalEstimateVal, "$0.0");

	}

//		public void verifyChargeItemsTableDescriptionNotPresent(String item) {
//
//			logger.info("Validate Charge Item table description details");
//
//			testUtil.init(this);
//
//			String desc;
//
//			boolean flag = false;
//
//			List<WebElement> chargeItemList = driver.findElements(By.xpath("//*[@id='main']/app-rate-quote/app-create-rate-quote/app-rate-request-results/mat-card/table/tbody/tr[12]/td/div/app-quote-drawer/div/app-quote-charge-items/div/table/tbody/tr[*]"));
//
//			for(int i=0;i<chargeItemList.size();i++) {
//
//				desc = chargeItemList.get(i).findElement(By.xpath("td")).getText();
//
//				logger.info("Desc : "+desc);
//
//				if(desc.contains(item)) {
//
//					flag = true;
//
//					break;
//
//				}
//
//			}
//
//			if(!flag) {
//
//				logger.info("Not found the Charge Item in the table.");
//
//			}else {
//
//				Assert.fail("Failed because display Charge Item descriptionis present : "+item);
//
//			}
//
//		}

	// Select's current date -newly created

	public void selectCurrentDate() {

		logger.info("Enter today's date");

		testUtil.init(this);

		testUtil.setHardWait(500);

		DateTimeFormatter dtf = getDateTimeFormatter();

		LocalDate localDate = LocalDate.now();

		weTodayDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		testUtil.setHardWait(500);

		weTodayDate.sendKeys(dtf.format(localDate));

	}

	public String getChargeItem(String desc) {
		testUtil.init(this);
		WebElement chargeItem = testUtil.filterXpath("//td[text()=\"" + desc + "\"]/following-sibling::td", null, null,
				0, "$");
		System.out.println("Charge item amount: " + chargeItem.getText());
		return chargeItem.getText();
	}

	public void verifyGuranteedExclusiveBOLMessage() {
		testUtil.init(this);
		WebElement appQuoteDrawer = testUtil.filterSelector("app-quote-drawer", null, null, 1,
				"Please write or type Guaranteed Exclusive Use on your BOL (including the driver's copy).");
		WebElement bolMessage = testUtil.filterSelector("span", appQuoteDrawer, null, 0,
				"Please write or type Guaranteed Exclusive Use on your BOL (including the driver's copy).");
		System.out.println("BOL message: " + bolMessage.getText());
		Assert.assertTrue(bolMessage.isDisplayed());
	}

	public void verifyChargeItemsTableDescription(String description) {
		testUtil.init(this);
		WebElement appQuoteDrawer = testUtil.filterSelector("app-quote-drawer", null, null, 1, description);
		WebElement chargeItemDescription = testUtil.filterSelector("td", appQuoteDrawer, null, 0, description);
		System.out.println("Charge item description: " + chargeItemDescription.getText());
		Assert.assertTrue(chargeItemDescription.isDisplayed());
	}

	public void verifyTotalEstimatedFreightCharges(String description) {
		verifyChargeItemsTableDescription(description);
	}

	public void verifyChargeItemsTableDescriptionNotPresent(String description) {
		String s = "!" + description;
		WebElement appQuoteDrawer = testUtil.filterSelector("app-quote-drawer", null, null, 1, s,
				"Please write or type Guaranteed Exclusive Use on your BOL (including the driver's copy).");
		Assert.assertTrue(appQuoteDrawer.isDisplayed());
	}

	public void verifyChargeItemsTable1(String desc, String charge) {

		logger.info("Validating Charge Description " + desc + " have charge as " + charge);
		testUtil.init(this);

		boolean flagVal = false;
		String charges = null;

		for (int i = 1; i < chargeItemTableRows.size(); i++) {

			String description = testUtil.getWebElement(chargeItemTableRows.get(i), "td:nth-child(1)").getText().trim();

			if (description.contains(desc)) {

				charges = testUtil.getWebElement(chargeItemTableRows.get(i), "td:nth-child(2)").getText().trim();
				System.out.println("The charges is:" + charges);
				if (!charges.isEmpty()) {

					flagVal = true;

					Assert.assertEquals(charges, charge);

					break;

				} else {

					flagVal = false;

					Assert.fail(desc);

				}

			}

		}

	}

	// }

	// assertTrue(flagVal, "Failed to display Charge Item description and its
	// charges: " + desc);

	// }

	// }

    public void verifyChargeItemByServiceLevel(String serviceLevel, String description, String amount) {
    	 testUtil.init(this);
         WebElement serviceLevelTable = testUtil.filterSelector("app-quote-drawer", null, null, 1,
                 serviceLevel);
         
         WebElement tableRow = testUtil.filterSelector("tr", null, serviceLevelTable, 2, description);
         WebElement chargeItemAmount = testUtil.filterXpath(
                 ".//td", null, tableRow, 0, amount);
         System.out.println("The tage name: " + chargeItemAmount.getTagName());
         
         await().atMost(5000, MILLISECONDS).until(() -> chargeItemAmount.getTagName(), equalTo("td"));
         
         String chargeAmount = testUtil.getTextFromElement(chargeItemAmount);
         
         System.out.println("Charge item " + description + " amount: " + chargeAmount);
         testUtil.verifyElementHasTextContent(chargeItemAmount);
    }


    public void verifyQuoteNumberPrefix(String serviceLevel, String prefix) {
        testUtil.init(this);
        testUtil.setHardWait(700);
        String quoteNumber = recordQuoteNumber(serviceLevel);
        System.out.println(quoteNumber);
        assertTrue(quoteNumber.startsWith(prefix));
        System.out.println("Prefix is: "+ quoteNumber.substring(0, 1));
    }
    

	}


