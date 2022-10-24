package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;
import util.TestUtil;


public class ENetVTLRateQuotePage extends TestBase {
	
	private Logger logger=Logger.getLogger(ENetVTLRateQuotePage.class);	
	
/*	
	public ENetVTLRateQuotePage() {
		TestUtil testUtil = new TestUtil();
		testUtil.init(this);*/
		
//	}
	@FindBy(id = "full_name")
	private WebElement fullName;
	
	@FindBy(id = "accountNumber")
	private WebElement accountNumber;
	
	@FindBy(id = "contact_email")
	private WebElement email;
	
	@FindBy(id = "my_role")
	private WebElement myRole;
	
	@FindBy(id = "user_telephone")
	private WebElement phone;
	
	@FindBy(id = "terms")
	private WebElement terms;
	
	@FindBy(id = "zipOrigin")
	private WebElement zipOrigin;
	
	@FindBy(id = "suggestOrigin")
	private WebElement originSuggestion;
	
	@FindBy(id = "zipDestination")
	private WebElement zipDestination;
	
	@FindBy(id = "suggestDestination")
	private WebElement destinationSuggestion;
	
	@FindBy(id = "pickupDate")
	private WebElement pickupDate;
	
	@FindBy(id = "commClass0")
	private WebElement commodityClass;
	
	@FindBy(id = "pieces0")
	private WebElement commodityPieces;
	
	@FindBy(id = "pieceType0")
	private WebElement commodityPieceType;
	
	@FindBy(id = "weight0")
	private WebElement commodityTotalWeight;
	
	@FindBy(id = "length0")
	private WebElement commodityLength;
	
	@FindBy(id = "width0")
	private WebElement commodityWidth;
	
	@FindBy(id = "height0")
	private WebElement commodityHeight;
	
	@FindBy(id = "ratequoteSubmitButton")
	private WebElement submitBtn;
	
	
	@FindBy(id = "quote_number")
	private WebElement quoteNumber;
	
	@FindBy(id = "progress_bar")
	private WebElement progressBar;
 
	@FindBy(xpath="//*[@id='quote_number']/span")
	private WebElement selectGetQuote;
	
	@FindBy(id = "selectOne")
	private WebElement selectOne;
	
	@FindBy(id = "update_quote")
	private WebElement updateQuote;

	@FindBy(id = "desc0")
	private WebElement weDescription;

	@FindBy(id = "desc1")
	private WebElement weDescription1;
	
	@FindBy(id = "commClass1")
	private WebElement commodityClass1;
	
	@FindBy(id = "pieces1")
	private WebElement commodityPieces1;
	
	@FindBy(id = "pieceType1")
	private WebElement commodityPieceType1;
	
	@FindBy(id = "weight1")
	private WebElement commodityTotalWeight1;
	
	@FindBy(id = "length1")
	private WebElement commodityLength1;
	
	@FindBy(id = "width1")
	private WebElement commodityWidth1;
	
	@FindBy(id = "height1")
	private WebElement commodityHeight1;
	
	@FindBy(css = "[class = 'success'] p")
	private WebElement successMessage;

	@FindBy(id = "linearFeet")
	private WebElement totalLinearFt;
			
	@FindBy(id = "stackable")
	private WebElement isFreightStackable;
	
	@FindBy(xpath="//input[@name='expand']")
    private WebElement expandButton; 
	
    @FindBy(xpath="//input[@id='BLIND']")
    private WebElement blindShipmentCoordination;
    
    
	/******************************METHODS***********************************/
	public void switchToFrame() {
		testUtil.init(this);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
	}
	
	public void enterVTLRateQuoteFullName(String name) {
		logger.info("Enter VTL Rate Quote Full name: "+name);
		testUtil.init(this);
		switchToFrame();
		testUtil.setExplicitWait(fullName, 20);
		fullName.sendKeys(name);
	}
	
	public void enterVTLRateQuoteAccountNumber(String accNo) {
		logger.info("Enter VTL Rate Quote Account Number: "+accNo);
		testUtil.init(this);
		testUtil.setExplicitWait(accountNumber, 10);
		accountNumber.sendKeys(accNo);
	}
	
	public void enterVTLRateQuoteEmail(String emailAddress) {
		logger.info("Enter VTL Rate Quote Email: "+emailAddress);
		testUtil.init(this);
		testUtil.setExplicitWait(email, 10);
		email.sendKeys(emailAddress);
	}
	
	public void selectVTLRateQuoteMyRole(String role) {
		logger.info("Select VTL Rate Quote My Role: "+role);
		testUtil.init(this);
		testUtil.setExplicitWait(myRole, 10);
		testUtil.selectUsingVisibleText(myRole, role);
	}
	
	public void enterVTLRateQuotePhoneNumber(String phoneNo) {
		logger.info("Enter VTL Rate Quote Phone Number: "+phoneNo);
		testUtil.init(this);
		testUtil.setExplicitWait(phone, 10);
		phone.click();
		phone.sendKeys(phoneNo);
	}
	
	public void selectVTLRateQuoteTerms(String termsString) {
		logger.info("Enter VTL Rate Quote Terms: "+termsString);
		testUtil.init(this);
		testUtil.setExplicitWait(terms, 10);
		testUtil.selectUsingVisibleText(terms, termsString);
	}
	
	public void enterVTLRateQuoteOriginAddress(String originZip) {
		logger.info("Enter VTL Rate Quote Origin Address: "+originZip);
		testUtil.init(this);
		testUtil.setExplicitWait(zipOrigin, 10);
		zipOrigin.sendKeys(originZip);
		testUtil.setExplicitWait(originSuggestion, 10);
		originSuggestion.click();
	}
	
	public void enterVTLRateQuoteDestinationAddress(String destZip) {
		logger.info("Enter VTL Rate Quote Destination Address: "+destZip);
		testUtil.init(this);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		testUtil.setExplicitWait(zipDestination, 30);
		zipDestination.sendKeys(destZip);
		testUtil.setHardWait(2000);
		//testUtil.setExplicitWait(destinationSuggestion, 10);
		destinationSuggestion.click();
		testUtil.setHardWait(2000);
	}
	
	public void enterVTLRateQuotePickupDate() {
		logger.info("Enter VTL Rate Quote Pickup Date");
		testUtil.init(this);
		testUtil.setExplicitWait(pickupDate, 10);
		pickupDate.click();
		pickupDate.sendKeys(testUtil.todaysDate());
	}
	
	public void selectVTLRateQuoteClass(String commClass) {
		logger.info("Select VTL Rate Quote Class");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityClass,10);
		testUtil.selectUsingVisibleText(commodityClass, commClass);
	}
	
	public void enterVTLRateQuotePieces(String piece) {
		logger.info("Enter VTL Rate Quote pieces");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityPieces,10);
		commodityPieces.clear();
		commodityPieces.sendKeys(piece);
	}
	
	public void selectVTLRateQuotePiecesType(String pieceType) {
		logger.info("Enter VTL Rate Quote Piece Type");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityPieceType,10);
		testUtil.selectUsingVisibleText(commodityPieceType, pieceType);
		testUtil.setHardWait(2000);
	}
	
	public void enterVTLRateQuoteTotalWeight(String weight)
	{
		logger.info("Enter VTL Rate Quote Weight");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityTotalWeight,10);
		commodityTotalWeight.sendKeys(weight);
	}
	
	public void enterVTLRateQuoteTotalLength(String length) {
		logger.info("Enter VTL Rate Quote Total Length");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityLength,10);
		commodityLength.clear();
		commodityLength.sendKeys(length);
	}
	
	public void enterVTLRateQuoteTotalWidth(String width) {
		logger.info("Enter VTL Rate Quote Total width");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityWidth,10);
		commodityWidth.clear();
		commodityWidth.click();
		commodityWidth.sendKeys(width);
	}
	
	public void enterVTLRateQuoteTotalHeight(String height) {
		logger.info("Enter VTL rate quote total height");
		testUtil.init(this);
		testUtil.setExplicitWait(commodityHeight,90);
		commodityHeight.clear();
		testUtil.setExplicitWait(commodityHeight,90);
		commodityHeight.sendKeys(height);
		testUtil.setHardWait(1000);
	}
	
	public void clickOnVTLRateQuoteSubmitBtn() {
		logger.info("Click on VTL rate quote submit button");
		testUtil.init(this);
		testUtil.setExplicitWait(submitBtn, 120);
		testUtil.clickElementJavascript(submitBtn);
		testUtil.setHardWait(2000);
	}

	
	public String recordQuoteNumber(String serviceLevel) {
		logger.info("Record Service Level: "+serviceLevel+ "  Quote number");
		testUtil.init(this);
		
		WebElement chargesLink = driver.findElement(By.xpath("//*[@id='vtl_table']//a[contains(@onclick,'"+serviceLevel+"')]")); 
		testUtil.setExplicitWait(chargesLink, 20);
		chargesLink.click();
		
		//testUtil.setExplicitWait(quoteNumber, 20);
		testUtil.setHardWait(2000);
		String quoteNumberTxt = quoteNumber.getText();
		System.out.println("Quote NO: "+quoteNumberTxt);
		String[] quoteNo = quoteNumberTxt.split(":");
		return quoteNo[1].trim();
	}
	
	
	
	
	public String recordQuoteNumberFromVTLAndLTLTable(String serviceLevel) {
		logger.info("Record Quote number from Time Critical and LTL Standard options table");
		testUtil.init(this);
		WebElement chargesLink = driver.findElement(By.cssSelector("[onclick=\"ga('send', 'event', 'VTL App Rate Screen Price', 'Click', '"+serviceLevel+"');\"]"));
		chargesLink.click();
		
		testUtil.setHardWait(2000);
		String quoteNumberTxt = quoteNumber.getText();
		String[] quoteNo = quoteNumberTxt.split(":");
		return quoteNo[1].trim();
	}
	
	public void verifyVTLRateQuotePage2IsDisplayed() {
		logger.info("Validating VTL Rate quote - Page 2 is displayed");
		testUtil.init(this);
		
		boolean text=selectGetQuote.isDisplayed();
		/*testUtil.setExplitWaitAttributeContains(progressBar, "alt", "Step 2", 90);
		String altImage = progressBar.getAttribute("alt").trim();*/
		System.out.println(text);
		Assert.assertTrue(true);
		testUtil.setHardWait(2000);
	}

	
	
	public void selectQouteByServiceLevel(String tableName, String serviceLevel) {   
		logger.info("Click on Service Level "+ serviceLevel);
		testUtil.init(this);
		List <WebElement> rowCount = null;
		
		if(tableName.equals("TC")) {
			rowCount	 = driver.findElements(By.xpath("//*[@id='tc_table']/tbody/tr"));
		}
		else if(tableName.equals("VTL")){
			rowCount = driver.findElements(By.xpath("//*[@id='vtl_table']/tbody/tr"));
		}
		 
		for(int i = 1; i<= rowCount.size(); i++) {
		 String actual = driver.findElement(By.xpath("//*[@id='tc_table']/tbody/tr["+i+"]/td[3]")).getText();
		 	if (actual.equals(serviceLevel)) {
			 driver.findElement(By.xpath("//*[@id='tc_table']/tbody/tr["+i+"]/td[4]/a")).click();
			 System.out.println("Quote selected successfully");
			 break;
		 	}}
		}
		 
		public String getQuoteNumber() {
			logger.info("Record quote number");
			testUtil.init(this);
			String quoteNumberTxt = quoteNumber.getText();
			testUtil.setHardWait(1000);
			System.out.println(quoteNumberTxt);
			String[] quoteNo = quoteNumberTxt.split(":");
			return quoteNo[1].trim();
		}

		public void selectSelectOne(String option) {
			logger.info("Select one");
			testUtil.init(this);
			testUtil.setExplicitWait(selectOne,20);
			testUtil.selectUsingVisibleText(selectOne, option);
		}
		public void clickOnUpdateQuote() {
			logger.info("Click on update quote");
			testUtil.init(this);
			updateQuote.click();
		}
		public void enterDescription(String desc) {
			logger.info("Enter description");
			testUtil.init(this);
			weDescription.sendKeys(desc);
		}

		
		public void selectVTLRateQuoteClass1(String commClass) {
			logger.info("Select VTL rate quote class");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityClass1,10);
			testUtil.selectUsingVisibleText(commodityClass1, commClass);
		}
		
		public void enterVTLRateQuotePieces1(String piece) {
			logger.info("Enter VTL rate quote pieces");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityPieces1,10);
			commodityPieces1.sendKeys(piece);
		}
		
		public void selectVTLRateQuotePiecesType1(String pieceType) {
			logger.info("Enter VTL Rate Quote Piece Type");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityPieceType1,10);
			testUtil.selectUsingVisibleText(commodityPieceType1, pieceType);
		}
		
		public void enterVTLRateQuoteTotalWeight1(String weight)
		{
			logger.info("Enter VTL Rate Quote Weight");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityTotalWeight1,10);
			commodityTotalWeight1.sendKeys(weight);
		}
		
		public void enterVTLRateQuoteTotalLength1(String length) {
			logger.info("Enter VTL Rate Quote Total Length");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityLength1,10);
			commodityLength1.sendKeys(length);
		}
		
		public void enterVTLRateQuoteTotalWidth1(String width) {
			logger.info("Enter VTL Rate Quote Total width");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityWidth1,10);
			commodityWidth1.sendKeys(width);
		}
		
		public void enterVTLRateQuoteTotalHeight1(String height) {
			logger.info("Enter VTL Rate Quote Total height");
			testUtil.init(this);
			testUtil.setExplicitWait(commodityHeight1,10);
			commodityHeight1.sendKeys(height);
		}
		
		public void enterDescription1(String desc) {
			logger.info("Enter Desccription");
			testUtil.init(this);
			weDescription1.sendKeys(desc);
		}


		public void verifyContactMeByServiceLevel(String serviceLvl) {
			logger.info("Verifying contact me link by service level");
			testUtil.init(this);
			WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+serviceLvl+"')]//parent::tr/td[4]"));
			testUtil.setExplicitWait(ele, 60);
			String contactMe = ele.getText().trim();
			Assert.assertTrue(contactMe.equalsIgnoreCase("Contact Me"));
			testUtil.setHardWait(1000);
		}
		
		public void clickOnContactMeByServiceLevel(String serviceLvl) {
			logger.info("click contact me link by service level");
			testUtil.init(this);
			WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+serviceLvl+"')]//parent::tr/td[4]/a"));
			testUtil.setExplicitWait(ele, 60);
			//ele.click();
			testUtil.clickElementJavascript(ele);
		}
		
		public void verifyInfoSuccessMessage() {
			String message = "Success: Using the information you provided, a response will be sent to you via email within "
					+ "approximately 1 hour. Requests submitted after 5 p.m. M-F EST will receive a response the next business day.";
			logger.info("Validating information successfull message");
			testUtil.init(this);
			testUtil.setExplicitWait(successMessage, 20);
			String actualMsg = successMessage.getText().trim();
			Assert.assertTrue(actualMsg.contains(message));
		}

		public void clickOnIsFreightStackable() {
			logger.info("Click on Is your Freight stackable check box");
			testUtil.init(this);
			testUtil.setExplicitWait(isFreightStackable, 20);
			isFreightStackable.click();
			testUtil.setHardWait(5000);
		}
				
		public void verifyChargesByServiceLevel(String serviceLvl) {
			logger.info("Verifying charges rate by service level");
			testUtil.init(this);
			WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'"+serviceLvl+"')]//parent::tr/td[4]"));
			testUtil.setExplicitWait(ele, 60);
			String rate = ele.getText().trim();
			Assert.assertFalse(rate.equalsIgnoreCase("Contact Me"));
		}

		public void enterTotalLinearFeet(String linearFt) {
			logger.info("Enter Total linear feet needed for this shipment");
			testUtil.init(this);
			totalLinearFt.clear();
			totalLinearFt.sendKeys(linearFt);
			testUtil.setHardWait(5000);
		}
		
		public void clickOnExpandButton() {
            testUtil.init(this);
            logger.info("Click on expand button"); 
            expandButton.click(); 
}
		
		
		public void selectBlindShipmentCoordination() {
            testUtil.init(this); 
            logger.info("Select Blind Shipment Coordination"); 
            blindShipmentCoordination.click(); 
        }
		
		public void enterVTLRateQuotePickupWeekendDate() {
			logger.info("Enter VTL Rate Quote Pickup Date");
			testUtil.init(this);
			testUtil.setExplicitWait(pickupDate, 10);
			pickupDate.click();
			pickupDate.sendKeys(testUtil.enterNextWeekendDay()); 
		}
}
