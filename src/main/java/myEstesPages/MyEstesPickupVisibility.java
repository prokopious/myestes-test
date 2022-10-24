/**
 * 
 */
package myEstesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;
import org.openqa.selenium.ElementNotVisibleException;

public class MyEstesPickupVisibility extends TestBase {

private Logger logger = Logger.getLogger(MyEstesPickupVisibility.class);
	
	@FindBy(how = How.ID, using = "my-estes-dropdown")
	WebElement myEstesDropdown;
	
	/* LOGIN */
	@FindBy(how = How.XPATH, using = "//*[@id='main-nav']/ul[2]/li/div/a[1]")
	WebElement navLogin;
	@FindBy(how = How.ID, using = "inputUsername") 
	WebElement inputUsername;
	@FindBy(how = How.ID, using = "inputPassword") 
	WebElement inputPassword;
	@FindBy(how = How.XPATH, using = "//*[@id='login-component']//button[@type='submit']") 
	WebElement buttonSubmit;
	
	/* PICKUP VISIBILITY */
	@FindBy(how = How.XPATH, using = "//*[@id='sidebar']//a[contains(text(),'Pickup Visibility')]") 
	WebElement sidebarPickupVisibility;
	@FindBy(how = How.ID, using = "criteria") 
	WebElement textAreaCriteria;
	//Updating xpath, to select search button
	@FindBy(how = How.XPATH, using = "//button[@id='shipmentTrackingSubmitButton']") 
	WebElement buttonSearch;
	
	/* TRACKING RESULTS */
	@FindBy(how = How.XPATH, using = "//td[contains(concat(' ', @class, ' '), ' mat-column-toggle ')]") 
	WebElement firstTrackingResultDropdown;
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Shipment(s)')]/../../table/tbody/tr[1]/td[2]") 
	WebElement proNumberTrackingResults;
	//@FindBy(how = How.XPATH, using = "//*[contains(text(),'Shipment(s)')]/../../table/tbody/tr[1]/td[3]")  --changed the locator
	@FindBy(how = How.XPATH, using = "//td[contains(@class,'mat-column-pieces')]") 
	WebElement piecesTrackingResults;
	//@FindBy(how = How.XPATH, using = "//*[contains(text(),'Shipment(s)')]/../../table/tbody/tr[1]/td[4]") --changed the locator
	@FindBy(how = How.XPATH, using = "//td[contains(@class,'mat-column-weight')]") 
	WebElement weightTrackingResults;
	//@FindBy(how = How.XPATH, using = "//*[contains(text(),'Shipment(s)')]/../../table/tbody/tr[1]/td[5]") --changed the locator
	@FindBy(how = How.XPATH, using = "//td[@class='consignee']")
	WebElement consigneeLocationTrackingResults;
	@FindBy(how = How.XPATH, using = "//div[@class='ng-tns-c3-1 ng-star-inserted']/span[@class='fal fa-chevron-down']")
	WebElement dropDown; 
	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Expand All')]")
	WebElement expandAllButton; 
	
	/* SHIPMENT DETAILS */
	@FindBy(how = How.XPATH, using = "//tbody//th[contains(text(),'Pieces')]//../td") 
	WebElement shipmentDetailsPieces;
	@FindBy(how = How.XPATH, using = "//tbody//th[contains(text(),'Weight')]//../td") 
	WebElement pickupWeight;
	
	/* REFERENCE NUMBERS */
	@FindBy(how = How.XPATH, using = "//tbody//th[contains(text(),'Estes PRO Number')]/../td") 
	WebElement referenceNumbersProNumber;
    
	/* Clear Results */
	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Clear')]")
	WebElement clearResults; 
	
	@FindBy(how=How.XPATH, using = "(//div[@class='icon']/i[@class='fas fa-exclamation fa-lg'])[2]")
	WebElement exlamationPointIcon; 
	
	@FindBy(how=How.XPATH, using = "//button[contains(text(), 'Clear')]")
	WebElement clearButton; 
	
	/****************************************METHODS********************************/
//	public MyEstesPickupVisibility() {
//		testUtil.init(this);
//	}
	
	/* LOGIN */
	public void login(String username, String password) {
		logger.info("Login");
		myEstesDropdown.click();
		navLogin.click();
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		buttonSubmit.click();
		assertTrue(isLoggedIn());
	}
	
	public boolean isLoggedIn() {
		myEstesDropdown.click();
		return driver.findElements(By.xpath("//*[@id='main-nav']/ul[2]/li/div/div")).size() > 0;
	}
	
	/* PICKUP VISIBILITY */
	
	/**
	 * 	Enter one more Pickup Numbers separated by newlines
	 * @param pickupRequestNumbers Each pickup request number should be on separate lines
	 */
	public void enterPickupRequestNumbers(String pickupRequestNumbers) {
		logger.info("Enter pickup request numbers");
		textAreaCriteria.sendKeys(pickupRequestNumbers);
	}

	public void clickSearch() {
		logger.info("Click search");
		buttonSearch.click();
	}
	
	public void clickPickupVisibility() {
		logger.info("Click Pickup Visibility on the sidebar");
		sidebarPickupVisibility.click();
	}
	
	/* TRACKING RESULTS */
	
	public void clickFirstTrackingResultsDropdown() {
		logger.info("Click first tracking results dropdown");
		firstTrackingResultDropdown.click();
	}
	
	public void verifyProNumberTrackingResults(String proNumber) {
		logger.info("Verify PRO Number");
		String proNumOnScreen = driver.findElement(By.xpath("//a[contains(text(), '"+proNumber+"')]")).getText(); 
		assertEquals(proNumOnScreen, proNumber); 
	}
	
	public void verifyPiecesTrackingResults(String pieces) {
		logger.info("Verify Pieces");
		String actual = piecesTrackingResults.getText();
		assertTrue(actual.equals(pieces));
	}
	
	public void verifyWeightTrackingResults(String weight) {
		logger.info("Verify Weight");
		String actual = weightTrackingResults.getText();
		assertTrue(actual.equals(weight));
	}
	
	public void verifyConsigneeLocationTrackingResults(String city, String state, String zip) {
		logger.info("Verify Consignee Location");
		String[] actual = consigneeLocationTrackingResults.getText().split("(, )|( )");
		assertTrue(actual[0].equalsIgnoreCase(city));
		assertTrue(actual[1].equalsIgnoreCase(state));
		assertTrue(actual[2].equalsIgnoreCase(zip));
	}
	
	public void validateResultsFromTrackingBar(String ...value) {
		logger.info("Validate which object is checked on Tracking Results Bar");
		String activeValue = null; 
		String num; 
		String [] inputs =  new String [value.length]; 
		int i = 0; 
		for(String val: value) {
			activeValue = val; 
			switch (activeValue) {
				case "Pickup Accepted": num = "1"; 
					inputs[i] = num; 
					i++; 
					break; 
				case "Driver En Route": num = "2"; 
					inputs[i] = num; 
					i++;
					break; 
				case "Driver Arrived": num = "3"; 
					inputs[i] = num; 
					i++; 
					break; 
				case "Departed Pickup Location": num = "4"; 
					inputs[i] = num; 
					i++; 
					break; 
				case "Pickup Completed": num = "5"; 
					inputs[i] = num; 
					i++; 
					break; 
				default: break; 
			}
		}
		for(String in: inputs) {
			String xPathId = "step-"+in; 
			WebElement pickUpSelected = driver.findElement(By.xpath("//li[@id='"+xPathId+"']"));
			System.out.println("XPath: " + pickUpSelected); 
			String[] classValue = pickUpSelected.getAttribute("class").split(" "); 
			boolean found = false; 
				for(String j: classValue) {
					System.out.println(j); 
					if(j.contains("active") || j.contains("success")) {
						found = true; 
						break; 
					}
				}
				assertTrue(found); 
			}
	}
	
	public void clearResults() {
		logger.info("Clear results in search bar");
		clearResults.click(); 
	}
	
	public void clickDropDown() {
		logger.info("Click dropdown");
		try {
			dropDown.click(); 
		}catch(Exception e){
			logger.info("Screen is already displayed, proceed with next step"); 
			
		}
	}
	
	public void clickExpandAllButton() {
		logger.info("Click dropdown");
		try {
			expandAllButton.click(); 
		}catch(Exception e){
			logger.info("Items are already visible for viewing."); 
			
		}
	}
	//new method
	public void verifyProNumberTrackingResults2(String proNumber) {
		logger.info("Verify PRO Number");
		String findProElement = null; 
		try{
			findProElement = driver.findElement(By.xpath("//a[contains(text(), '"+proNumber+"')]")).getText(); 
		}catch(org.openqa.selenium.NoSuchElementException e) {
			if(driver.findElement(By.xpath("//p[contains(text(), 'Tracking numbers are not available for this pickup request at this time.')]")).isDisplayed()) {
				logger.info("Tracking number is not available for this specific pro number at this moment!"); 
			}
			e.printStackTrace(); 
		}finally {
			assertEquals(findProElement, proNumber); 
		}

	}
	
	public void verifyPiecesTrackingResults2(String pieces) {
		logger.info("Verify Pieces");
		String updatedPieces = pieces + " total pieces"; 
		String findTotalPieces = driver.findElement(By.xpath("//td[contains(text(), '"+updatedPieces+"')]")).getText(); 
		assertEquals(findTotalPieces, updatedPieces); 
	}
	
	public void verifyWeightTrackingResults2(String weight) {
		logger.info("Verify Weight");
		String updatedWeight = weight + " lbs. total weight"; 
		String findTotalWeight = driver.findElement(By.xpath("//td[contains(text(), '"+updatedWeight+"')]")).getText(); 
		assertTrue(findTotalWeight.equals(updatedWeight));
	}
	
	public void verifyConsigneeLocationTrackingResults2(String city, String state, String zip) {
		logger.info("Verify Consignee Location");
		//The string element below obtains the entire address of the shipper
		String identifyConsigneeAddress = driver.findElement(By.xpath("//td[contains(text(), '"+zip+"')]")).getText(); 
		String[] elements = {city, state, zip}; 
		for(String element : elements) {
			assertTrue(identifyConsigneeAddress.contains(element)); 
		}
		
	}
	
	public void validateShipmentNotFoundMessage(String phrase) {
		logger.info("Assert that parameter 'phrase' is visible in the webpage.");
		String visiblePhrase = driver.findElement(By.xpath("//div[@class='ng-tns-c3-1 ng-star-inserted'][text() = ' Are you trying to ']")).getText(); 
		Assert.assertEquals(visiblePhrase, phrase); 
	}
	
	public void clickOnTrackAShipmentLink() {
		logger.info("Click On 'track a shipment' link which is located when a pickup request number is not identified");
		Actions actions = new Actions(driver); 
		WebElement trackAShipmentLink = driver.findElement(By.linkText("track a shipment?"));
		actions.moveToElement(trackAShipmentLink); 
		actions.click().build().perform(); 
	}
	
	public void enterPickupRequestNumbers(String ...pickupRequestNumbers) {
		logger.info("Enter A group of Pickup Request Numbers");
		testUtil.init(this);
		StringBuilder pickupNumInAStringBuilder = new StringBuilder(); 
		int counter = 0; 
		for(String pickUp: pickupRequestNumbers) {
			++counter; 
			pickupNumInAStringBuilder = pickupNumInAStringBuilder.append(pickUp).append((counter == pickupRequestNumbers.length ? "" : "\n")); 
		}
		clearResults.click(); 
		textAreaCriteria.click(); 
		textAreaCriteria.sendKeys(pickupNumInAStringBuilder);
	}
	
	public void enterPickupRequestNumbers1(String pickupRequestNumbers) {
		logger.info("Enter pickup request numbers");
		testUtil.init(this);
		clearResults.click(); 
		textAreaCriteria.click(); 
		textAreaCriteria.sendKeys(pickupRequestNumbers);
	}
	
	public void validateExclamationPointIsVisible() throws ElementNotVisibleException{
		logger.info("Validate Exclamation Point");
		testUtil.init(this); 
		String getClassName = exlamationPointIcon.getAttribute("class"); 
		if(!getClassName.contains("exclamation")) {
			throw new ElementNotVisibleException("Exclamation Point is not visible"); 
		}
	}
	
	public void validateCancelledMessage(String date, String time) {
		logger.info("Confirming cancellation message by customer");
		String[] message = {"Pickup Request Canceled by Customer", date+", "+ time}; 
		String value1 = null; 
		String value2 = null; 
		for(int i = 1; i <=message.length; i++) {
			if(i == 1) {
				value1 = driver.findElement(By.xpath("(((//div[@class='ng-tns-c3-1 ng-star-inserted'])[8]/span)['"+i+"']/span)[1]")).getText();  
				Assert.assertTrue(driver.findElement(By.xpath("((//div[@class='ng-tns-c3-1 ng-star-inserted'])[8]/span)['"+i+"']")).getText().equals(message[i-1])); 
			}
			if(i == 2) {
				value2 = driver.findElement(By.xpath("(((//div[@class='ng-tns-c3-1 ng-star-inserted'])[8]/span)['"+i+"']/span)[2]")).getText();  
			}
		}
		String concatValue = value1 + " " + value2; 
		Assert.assertTrue(concatValue.equals(message[message.length-1])); 
	}
	
	public void clickClearButton() {
		logger.info("Click On Clear Button"); 
		clearButton.click(); 
	}
	
	public String verifyResultNumber(String selectedReqNum) {
		logger.info("Verify Result Number.");
		testUtil.init(this);
		logger.info("selectedReqNum : "+selectedReqNum);
		
		String resultNum = driver.findElement(By.xpath("//*[text()='"+selectedReqNum+"']")).getText();
		logger.info("resultNum : "+resultNum);
		if(selectedReqNum.equals(resultNum)) {
			logger.info("The Result numbers matched.");
		}else {
			Assert.fail("The Result numbers does not match.");
		}
		return resultNum;
	}
}




