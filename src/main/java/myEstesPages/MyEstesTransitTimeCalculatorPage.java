package myEstesPages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesTransitTimeCalculatorPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesTransitTimeCalculatorPage.class);


	// Origin City
	@FindBy(how = How.ID, using = "cityOrigin")
	private WebElement weOriginCity;
	// Origin ZipCode
	@FindBy(how = How.ID, using = "zipOrigin")
	private WebElement weOriginZip;
	//First Element From Drop Down
	@FindBy(how = How.ID, using = "pointlink0")
	private WebElement weFirstEFDDown;
	// Destination City
	@FindBy(how = How.ID, using = "zipDestination0")
	private WebElement weDestinationCity;
	// Destination ZipCode
	@FindBy(how = How.ID, using = "zipDestination0")
	private WebElement weDestinationZip;
	// Shipment Date
	@FindBy(how = How.ID, using = "shipmentDate0")
	private WebElement weShipmentDate;
	// Submit Button
	@FindBy(how = How.XPATH, using = "//*[@id='Body_Content']/form/table/tbody/tr[16]/td/input")
	private WebElement weSubmitButton;




	//Enter Origin City Name
	public void enterOriginCityName(String OriginCityName) {
		logger.info("Enter Origin City Name");
		testUtil.init(this);
		weOriginCity.sendKeys( OriginCityName);
	}
	//Enter Origin ZipCode Number
	public void enterOriginZipCodeNo(String OriginZipNo) {
		logger.info("Enter Origin Zipcode Number");
		testUtil.init(this);
		weOriginZip.sendKeys(OriginZipNo);
	}
	//Enter Destination City Name
	public void enterDesCityName(String DestinationCityName) {
		logger.info("Enter Destination City Name");
		testUtil.init(this);
		weDestinationCity.sendKeys( DestinationCityName);
	}
	//Enter Destination ZipCode Number
	public void enterDesZipCodeNo(String DestinationZipCode) {
		logger.info("Enter Destination ZipCode Number");
		testUtil.init(this);
		weDestinationCity.sendKeys( DestinationZipCode);
	}
	//First Element From Drop Down 
	public void clickOnFirstEleFDDown() {
		logger.info("Click on First Element From Drop Down");
		testUtil.init(this);
		Actions action = new Actions(driver);
		action.moveToElement(weFirstEFDDown).perform();
		weFirstEFDDown.click();
	}
	//Enter Shipment Date
	public void enterShipmentDate() throws InterruptedException {
		logger.info("Enter Shipment Date");
		testUtil.init(this);
		SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");            
		Calendar c = Calendar.getInstance();        
		String DFLoading = (String)(formattedDate.format(c.getTime()));
		weShipmentDate.sendKeys(DFLoading);
		Thread.sleep(1000);
		weShipmentDate.click();
	}
	//Click on Submit Button
	public void clickOnSubmitButton() throws InterruptedException {
		logger.info("Click on Submit Buttonr");
		testUtil.init(this);
		Thread.sleep(1000);
		weSubmitButton.click();
	}
}
