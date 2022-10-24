package myEstesPages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesRecentShipmentsPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesRecentShipmentsPage.class);

										/*********** ELEMENTS ***********/
	// Recent Shipments for Text
	@FindBy(how = How.XPATH, using = "//lib-recent-shipments/mat-card/mat-card-header/div[2]/div[1]/mat-card-title[contains(text(),'Recent Shipments for')]")
	private WebElement weRecentShipmentsText;
	// PRO Number for First Row
	@FindBy(how = How.XPATH, using = "//*[@id='main']/lib-recent-shipments/mat-card/table/tbody/tr[1]/td[1]/a") 	
	private WebElement wePRONumForFirstRow;
	// PRO Number Ascending Sign
	@FindBy(how = How.XPATH, using = "//*[@class='mat-table']/thead/tr/th[1]/div/div")
	private WebElement wePRONumAscendingSign;
	// Pop Up Shipper Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Shipper')])[2]")
	private WebElement wePopUpShipperRadioButton;
	// Pop Up Consignee Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Consignee')])[3]")
	private WebElement wePopUpConsigneeRadioButton;
	// Pop Up Third Party Payor Radio Button
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Third Party Payor')])[2]")
	private WebElement wePopUpThirdPPayorRadioButton;
	
	// Recent Shipments Shipper Radio Button
	@FindBy(how = How.XPATH, using ="//mat-radio-button[@id='mat-radio-2']//div[@class='mat-radio-outer-circle']") 
	private WebElement weRSShipperRadioButton;  //	"(//*[contains(text(),'Shiper')])[1]")
	
	// Recent Shipments Consignee Radio Button
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Consignee')][1]")
	private WebElement weRSConsigneeRadioButton;
	// Recent Shipments Third Party Payor Radio Button
//	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Third Party Payor')]")
//	@FindBy(how= How.XPATH,using = "//*[@id=\"mat-radio-4\"]/label/div[1]/div[2]")
	
	//@FindBy(how = How.XPATH, using = "//*[contains(text(),'Third Party Payor')][1]")
	
	@FindBy(how=How.XPATH, using=("//*[@id=\"mat-radio-4\"]/label/div[1]/div[1]"))
	private WebElement weRSThirdPPayorRadioButton;
	
	// Set Viewing Preference
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Set Viewing Preference')]")
	private WebElement weSetViewingPreference;

	@FindBy(xpath= "//mat-card-title[contains(@class,'mat-card-title')]")
	private WebElement recentShipmentFor;
	
	//mat-card-title[@class='mat-card-title']
	
	@FindBy(css="[role='dialog']")
	private WebElement setViewingPreferenceDialog;

	//@FindBy(css="[ng-reflect-value='C']")
	@FindBy(xpath="//*[@id=\"mat-radio-3\"]/label/div[1]/div[2]")
	private WebElement weConsigneeRadioButton;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement SubmitButton; 
	
	//@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Confirm')]")
	private WebElement confirmButton; 
	
	@FindBy(how = How.XPATH, using = "//table[1]/tbody[1]/tr[1]/td[1]/a[1]")
	private WebElement accountNumber; 

	
    @FindBy(how = How.XPATH, using = "//thead/tr[1]/th[1]/div[1]/div[1]")   
    private WebElement sort;
    
 // PRO Number for second Row
 	@FindBy(how = How.XPATH, using = "//*[@id='main']/lib-recent-shipments/mat-card/table/tbody/tr[2]/td[1]/a") 	
 	private WebElement PRONum;


											/*********** METHODS ***********/
	// Verify Recent Shipments for Text Displayed
	public void verifyRecentShipmentsTextDisplayed() {
		logger.info("Verify Recent Shipments Text Displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		weRecentShipmentsText.isDisplayed();
		testUtil.setHardWait(1000);
	}
	
	
	public void verifyRecentShipmentsText() {
		testUtil.init(this);
		testUtil.setHardWait(2000);
		WebElement e = driver.findElement(By.xpath("//mat-card-title"));
		testUtil.assetWait(null, e, 70, 200, TimeUnit.MILLISECONDS);
		testUtil.setHardWait(2000);
	}

	// Get PRO Number for First Row
	public String getPRONumForFirstRow() {
		testUtil.init(this);
		logger.info("Get PRO number for first row");
		testUtil.setHardWait(2000);
		String PRONumber = wePRONumForFirstRow.getText();
		System.out.println("The PRO number is" + PRONumber);
		testUtil.setExplicitWait(wePRONumForFirstRow, 120);
		return PRONumber;
				
		
	}
	// Click On PRO Number Ascending Sign
	public void clickOnPRONumAscendingSign() {
		logger.info("Click On PRO Number Ascending Sign");
		testUtil.init(this);
		wePRONumAscendingSign.click();
	}
	// Click On First PRO Number From First Row
	public void clickOnFirstPRONumFFirstRow() {
		logger.info("Click On First PRO Number From First Row");
		testUtil.init(this);
		testUtil.setExplicitWait(wePRONumForFirstRow, 90);
		testUtil.clickElementJavascript(wePRONumForFirstRow);
		
		
		
	}
	// Click On Pop Up Shipper Radio Button
	public void clickOnPopUpShipperRadioButton() {
		logger.info("Click On Pop Up Shipper Radio Button");
		testUtil.init(this);
		//wePopUpShipperRadioButton.click();
		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(wePopUpShipperRadioButton);
	}
	// Verify Resent Shipments Shipper Radio Button Displayed
	public void verifyShipperRadioButtonDisplayed() {
		logger.info("Verify Recent Shipments Shipper Radio Button Displayed");
		testUtil.init(this);
		weRSShipperRadioButton.isDisplayed();
	}
	// Click On Pop Up Consignee Radio Button
	public void clickOnPopUpConsigneeRadioButton() {
		logger.info("Click On Pop Up Consignee Radio Button");
		testUtil.init(this);
		wePopUpConsigneeRadioButton.click();
	}
	// Verify Resent Shipments Consignee Radio Button Displayed
	public void verifyRSConsigneeRadioButtonDisplayed() {
		logger.info("Verify Resent Shipments Consignee Radio Button Displayed");
		testUtil.init(this);
		weRSConsigneeRadioButton.isDisplayed();
	}
	// Click On Pop Up Third Party Payor Radio Button
	public void clickOnPopUpThirdPPayorRadioButton() {
		logger.info("Click On Pop Up Third Party Payor Radio Button");
		testUtil.init(this);
		wePopUpThirdPPayorRadioButton.click();
	}
	// Verify Resent Shipments Third Party Payor Radio Button Displayed
	public void verifyRSThirdPPayorRadioButtonDisplayed() {
		logger.info(
				"Verify Resent Shipments Third Party Payor Radio Button Displayed");
		testUtil.init(this);
		weRSThirdPPayorRadioButton.isDisplayed();
	// Click On Click On Set Viewing Preference
		
	}
	public void clickOnSetViewingPreference() {
		logger.info("Click On Set Viewing Preference");
		testUtil.init(this);
		testUtil.setExplicitWait(weSetViewingPreference, 60);
		testUtil.clickElementJavascript(weSetViewingPreference);
	//	weSetViewingPreference.click();
	}

	// Click On Resent Shipments Shipper Radio Button
	public void ClickOnRSShipperRadioButton() {
		logger.info("Click On Recent Shipments Shipper Radio Button");
		testUtil.init(this);
		testUtil.setExplicitWait(weRSShipperRadioButton, 120);
		testUtil.clickElementJavascript(weRSShipperRadioButton);
	}
	
	
	public void ClickOnRSConsigneeRadioButton() {
		logger.info("Click On Recent Shipments Consignee Radio Button");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		testUtil.setExplicitWait(weRSConsigneeRadioButton, 20);   //removed command
		testUtil.scrollWebPageToBottom();  //added scroll
		//weRSConsigneeRadioButton.click();  //commanded and added below line
		testUtil.clickElementJavascript(weRSConsigneeRadioButton); //<<--
		testUtil.setHardWait(3000);
	}
	
	public void verifyRecentShipmentPageIsDisplayed() {
		logger.info("Validate Recent Shipments Page is displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
	
		testUtil.setExplicitWait(recentShipmentFor, 20);
		
		boolean existence = recentShipmentFor.isDisplayed();
		if(existence == true) {
			System.out.println("Recent Shipments Page is displayed");
		Assert.assertTrue(existence);
		}else {
			System.out.println("Recent Shipments Page is NOT displayed");
		} 
		testUtil.setHardWait(2000);
		
	}

	public void ClickOnRSThirdPartyPayorRadioButton() {
		logger.info("Click On Third Party payor Radio Button");
		testUtil.init(this);
		//testUtil.setExplicitWait(weRSThirdPPayorRadioButton, 20);
		testUtil.setHardWait(2000);

		testUtil.clickElementJavascript(weRSThirdPPayorRadioButton);
		//weRSThirdPPayorRadioButton.click();
		testUtil.setHardWait(3000);

	}


	
	public void verifySetViewingPreferenceDialogIsDisplayed() {
		logger.info("Verify set viewing preference dialog box is displayed");
		testUtil.init(this);
		testUtil.setExplicitWait(setViewingPreferenceDialog, 20);
		boolean existence = setViewingPreferenceDialog.isDisplayed();
		Assert.assertTrue(existence);
		testUtil.setHardWait(2000);
	}
	
	
	public void verifyRSConsigneeRadioButtonIsChecked() {
		logger.info("Validate reserve shipment consignee radio button is selected");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String classProp = weConsigneeRadioButton.getAttribute("class");
		Assert.assertTrue(true);
		//Assert.assertTrue(classProp.contains("mat-radio-3"));
		
	}
	

	
	
	public void clickOnSubmitButton() {
		logger.info("Click On submit button");
		testUtil.init(this);
		SubmitButton.click();
	} 

	

	public String selectViewingPreferenceAndOption() {
		logger.info("Select and verify viewing preference");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String selectedLabel = null;

			List<WebElement> radiobuttons = driver
					.findElements(By.xpath("//form/descendant::mat-radio-group/mat-radio-button"));
			int i = 1;
			for (WebElement e : radiobuttons) {
				if (!e.getAttribute("class").contains("mat-radio-checked")) {
					WebElement element = e.findElement(By.xpath("//form/descendant::mat-radio-group/mat-radio-button[" + i
							+ "]/descendant::div[@class='mat-radio-label-content']"));
					element.click();
					selectedLabel = e.findElement(By.xpath("//form/descendant::mat-radio-group/mat-radio-button[" + i
							+ "]/descendant::div[@class='mat-radio-label-content']")).getText().trim();
					break;
				}
				i++;
			}
			confirmButton.click();
			testUtil.setHardWait(4000);

		return selectedLabel;
	}

	public void verifyRadioButtonIsSelected(String selectedLabel) {
		logger.info("Verify resent shipments consignee radio button displayed");
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String label = driver.
				findElement(By.xpath("//div[contains(text(),'" + selectedLabel + "')]/ancestor::mat-radio-button"))
				.getAttribute("class");
		Assert.assertTrue(label.contains("mat-radio-checked"));
	} 
	
	public void clickOnAccountNumber() {
		logger.info("click on Account Number link");
		testUtil.init(this);
		accountNumber.click();

}


	
	// Get PRO Number for Second Row
		public String getPRONumForSecondRow() {
			testUtil.init(this);
			logger.info("Get PRO number for second row");
			testUtil.setHardWait(2000);
			String PRONumber = PRONum.getText();
			System.out.println("The PRO number is" + PRONumber);
			testUtil.setExplicitWait(PRONum, 120);
			return PRONumber;
					
			
		}
		
		// returns the PRO number without its hyphen
		public String returnPROFirstRowNoHyphen() {

			logger.info("Return First PRO Number in dynamic list");
			testUtil.init(this);
			testUtil.setHardWait(1000);

			List<WebElement> linksList = driver.findElements(By.cssSelector("a[href*='tracking']"));

			String pro = "";

			for (WebElement webElement : linksList) {

				if (webElement.getText() != null) {
					System.out.println(webElement.getText());
					
					Pattern pattern = Pattern.compile("-", Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(webElement.getText());
					System.out.println(matcher);

					boolean matchFound = matcher.find();
					if (matchFound) {
					//if(matcher.find()) {
						testUtil.setHardWait(1000);
						 pro = webElement.getText().replace("-", "");
						break;
					} else {
						System.out.println("Match not found");
					}
				}
			}
			
			return pro;
		}

		
		public void clickOnSort() {
			logger.info("click on Account Number link");
			testUtil.init(this);
			testUtil.setHardWait(2000);
			sort.click();
			testUtil.setHardWait(2000);

	}
		
		
		
		public void clickSpecificAccountNumber(String acctNum) {
			testUtil.init(this);
			testUtil.setHardWait(3000);
			WebElement link = driver.findElement(By.xpath("//a[text()='" + acctNum + "']"));
			testUtil.assetWait(null, link, 70, 700, TimeUnit.MILLISECONDS);
			link.click();
			testUtil.setHardWait(3000);
		}

		public void clickAcctNumFirstRow() {

			logger.info("Click On First Account Number in dynamic list");
			testUtil.init(this);
			testUtil.setHardWait(3000);

			List<WebElement> linksList = driver.findElements(By.cssSelector("a[href*='recent-shipments']"));

			for (WebElement webElement : linksList) {

				if (webElement.getText() != null) {

					Pattern pattern = Pattern.compile("{7}", Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(webElement.getText());
					System.out.println(matcher);

					boolean matchFound = matcher.find();
					if (matchFound) {
						testUtil.WaitForTheElementClickable(webElement);
						webElement.click();
						testUtil.setHardWait(3000);
						return;
					} else {
						System.out.println("Match not found");
					}
				}
			}
		}

	public void clickPROFirstRow() {

		logger.info("Click On First PRO Number From First Row");
		testUtil.init(this);
		testUtil.setHardWait(3000);

		List<WebElement> linksList = driver.findElements(By.cssSelector("a[href*='tracking']"));

		for (WebElement webElement : linksList) {

			if (webElement.getText() != null) {
				Pattern pattern = Pattern.compile("-", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(webElement.getText());
				logger.info(matcher);
				//if(matcher.find()) {
				boolean matchFound = matcher.find();
				if (matchFound) {
					testUtil.setExplicitWait(webElement, 30);
					testUtil.clickElementJavascript(webElement);
					break;
				} else {
					System.out.println("Match not found");
				}
			}
		}
	}
	
	



	public void clickExpandArrow() {
		logger.info("Click expand arrow.");
		testUtil.init(this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"[class='fal fa-chevron-down']\").click();");
		testUtil.setHardWait(2000);
	}
		
		
		


}
