package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.TestBase;

public class VolumeAndTruckloadQuoteMaintenance extends TestBase {

 private Logger logger=Logger.getLogger(VolumeAndTruckloadQuoteMaintenance.class);	
	
				/*********CONTACT INFORMATION**********/

	@FindBy(id = "accountNumber")
	private WebElement accountNumber;
	
	@FindBy(id ="myRole")
	private WebElement myRole;
	
	@FindBy(id ="terms")
	private WebElement terms;

	@FindBy(id ="company")
	private WebElement company;

	@FindBy(id ="contactName")
	private WebElement contactName;
	
	@FindBy(id ="phone")
	private WebElement phone;
	
	@FindBy(id ="email")
	private WebElement email;
	
				/*********SHIPPING INFORMATION**********/
	
	@FindBy(id ="countryOrigin")
	private WebElement countryOrigin;
	
	@FindBy(id ="zipOrigin")
	private WebElement zipOrigin;

	@FindBy(id ="cityOrigin")
	private WebElement cityOrigin;

	@FindBy(id ="stateOrigin")
	private WebElement stateOrigin;

	@FindBy(xpath ="//*[@onclick=\"clearPointSuggestFields('origin');\"]")
	private WebElement clearOrigin;
	
	@FindBy(id ="countryDestination")
	private WebElement countryDestination;

	@FindBy(id ="zipDestination")
	private WebElement zipDestination;

	@FindBy(id ="cityDestination")
	private WebElement cityDestination;

	@FindBy(id ="stateDestination")
	private WebElement stateDestination;


	
				/*********SHIPMENT DETAILS**********/
	
	@FindBy(id = "totalWeightSystem")
	private WebElement totalWeightSystem;
	
	@FindBy(id = "linearFeetSystem")
	private WebElement linearFeetSystem;
	
	@FindBy(id = "linearFeetAnalyst")
	private WebElement linearFeetAnalyst;
	
	@FindBy(id = "cubicFeetSystem")
	private WebElement cubicFeetSystem;
	
	@FindBy(id = "cubicFeetAnalyst")
	private WebElement cubicFeetAnalyst;
	
	@FindBy(id = "trailerUsage")
	private WebElement trailerUsage;
	
	@FindBy(id = "pndTime")
	private WebElement pndTime;
	
	@FindBy(id = "extraTime")
	private WebElement extraTime;
	
	@FindBy(id = "finalQuotedRateSystem")
	private WebElement finalQuotedRateSystem;
	
	@FindBy(css = "[class*='page-title-strong']")
	private WebElement pageTitle;
	
	@FindBy(id = "totalLinearFeet")
	private WebElement totalLinearFt;

	@FindBy(id = "recalculate-button")
	private WebElement recalculateButton;
	
	@FindBy(id = "create-new-quote-button")
	private WebElement createNewQuote;
	
	@FindBy(xpath ="//*[@onclick=\"clearPointSuggestFields('Destination');\"]")
	private WebElement recordQuoteNo;
	
	@FindBy(xpath ="//input[@name='accessorialSystem_1']")
	private WebElement sysValue;
	


				/*************METHODS************/
	
	
	public void verifyLinearFeet(String expected) {
		logger.info("Verify Linear Feet");
		testUtil.init(this);
		String actual = linearFeetSystem.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyTrailerUsage(String expected) {
		logger.info("Verify Trailer Usage");
		testUtil.init(this);
		String actual = trailerUsage.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyTotalWeight(String expected) {
		logger.info("Verify Total Weight");
		testUtil.init(this);
		String actual = totalWeightSystem.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyVTLQuoteMaintenancePage() {
		logger.info("Verifying VTL Quote maintenace page");
		testUtil.init(this);
		String actualTitle = pageTitle.getText().trim();
		Assert.assertEquals(actualTitle, "Volume and Truckload Quote Maintenance");
	}

	public void verifyVTLQuoteMaintenceExceptionText(String exception) {
		logger.info("Verify Exception in VTL Quote Maintenance");
		testUtil.init(this);
		WebElement exceptionEle = driver.findElement(By.xpath("//div[contains(@style,'inline-block')]//span[contains(text(),'"+exception+"')]"));
		testUtil.setExplicitWait(exceptionEle, 20);
		Assert.assertTrue(exceptionEle.isDisplayed());
	}
	
	public void verifyTotalCalcChargesGreaterThanAnCharge(double charge) {
		logger.info("Validate Total Calculated Charges greater than or equal to an fixed charge");
		testUtil.init(this);
		String actualCharge = finalQuotedRateSystem.getAttribute("value");
		double aCharge = Double.valueOf(actualCharge);
		Assert.assertTrue(aCharge>=charge);
	}

	public void validateTotalLinearFeet(String feet) {
		logger.info("validate Total linear feet need for this shipment");
			testUtil.init(this);
			String actualLinearFt = totalLinearFt.getAttribute("value");
			Assert.assertEquals(actualLinearFt, feet);
		}

	public void clickOnRecalculateButton() {
		logger.info("Click on Recalculate button");
		testUtil.init(this);
		recalculateButton.click();
		testUtil.setHardWait(2000);
	}

	public void enterTotalLinearFeet(String linearFeet) {
		logger.info("Enter Total linear feet needed for this shipment");
		testUtil.init(this);
		totalLinearFt.clear();
		totalLinearFt.sendKeys(linearFeet);
		testUtil.setHardWait(5000);
	}
	
	public void clickOnCreateNewQuote() {
		logger.info("Click on create new quote button");
		testUtil.init(this);
		createNewQuote.click();
		testUtil.setHardWait(2000);
	}
	
	
	public String getQuoteNumber(String quoteNum) {
		logger.info("Record quote number");
		testUtil.init(this);
	
		String quoteNumber = driver
				.findElement(By.xpath("//span[contains(text(),'Quote: " + quoteNum + "')]")).getText();

		System.out.println(quoteNumber);
		String[] quoteNo = quoteNumber.split(":");
		return quoteNo[1].trim();
	}
	
	
	public void verifyContactName(String expected) {
		logger.info("Verify Contact Name");
		testUtil.init(this);
		String actual = contactName.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}
	
	
	public void verifyPhoneNumber(String expected) {
		logger.info("Verify Phone Number");
		testUtil.init(this);
		String actual = phone.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyEmail(String expected) {
		logger.info("Verify Email");
		testUtil.init(this);
		String actual = email.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}
	
	public void validateHazardousMaterialsIsSelected() {
		logger.info("verify Hazardous Materials Is Selected");
		String expected ="Hazardous Materials";
		boolean  ele =driver.findElement(By.xpath("//option[contains(text(),'Hazardous Materials')]")).isSelected();
		String actual = driver.findElement(By.xpath("//option[contains(text(),'Hazardous Materials')]")).getText();
		System.out.println("Selected name is :" + actual);
		Assert.assertEquals(actual, expected);
	}
	
	public void verifySystemValue(String expected) {
		logger.info("Verify System Value");
		testUtil.init(this);
		String actual = sysValue.getAttribute("value");
		Assert.assertEquals(actual, expected);
	}

	public void validateTerminalChargesApplied() {
		logger.info("verify Terminal Charges Applied ");

		String expected1 = "Terminal Charge Applied:   96";
		String expected2 = "Terminal Charge Applied:   92";
		WebElement hiddenElement1 = driver.findElement(By.xpath("//input[@id='additionalChargeText_1']"));
		String actualCharge1 = hiddenElement1.getAttribute("value");

		WebElement hiddenElement2 = driver.findElement(By.xpath("//input[@id='additionalChargeText_2']"));
		String actualCharge2 = hiddenElement2.getAttribute("value");

		Assert.assertEquals(actualCharge1, expected1);
		Assert.assertEquals(actualCharge2, expected2);
	}

	

}
