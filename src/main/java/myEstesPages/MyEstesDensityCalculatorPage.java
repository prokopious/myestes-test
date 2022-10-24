package myEstesPages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesDensityCalculatorPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesDensityCalculatorPage.class);

    //
    //################# Nonpalletized Freight  ####################
    // Length(in inches)
    @FindBy(how = How.ID, using = "nonp-length")
    private WebElement weNonPalletizedFLenth;
    // width(in inches)
    @FindBy(how = How.ID, using = "nonp-width")
    private WebElement weNonPalletizedFWidth;
    // Height(in inches)
    @FindBy(how = How.ID, using = "nonp-height")
    private WebElement weNonPalletizedFHeight;
    // Weight(in pounds)
    @FindBy(how = How.ID, using = "nonp-weight")
    private WebElement weNonPalletizedFWeight;
    // CALCULATE Button
    @FindBy(how = How.XPATH, using = "//*[@id='density-calc-form-nonp']/div[3]/div/button[1]")
    private WebElement weNonPalFCalculateButton;
    // Freight Density
    @FindBy(how = How.ID, using = "nonp-value")
    private WebElement weNonPallFFreightDensity;

    //################# Palletized Freight  #######################

    //Length(in inches)
    @FindBy(how = How.ID, using = "nonp-length")
    private WebElement wePalletizedFLenth;
    // width(in inches)
    @FindBy(how = How.ID, using = "nonp-width")
    private WebElement wePalletizedFWidth;
    //Height of Pallet(in inches)
    @FindBy(how = How.ID, using = "nonp-height")
    private WebElement wePalletizedFHeightOfPallet;
    //Height of Freight(in inches) 
    @FindBy(how = How.ID, using = "nonp-weight")
    private WebElement wePalletizedFHeightOfFreight;
    // Weight(in pounds)
    @FindBy(how = How.ID, using = "nonp-weight")
    private WebElement wePalletizedFWeight;
    // CALCULATE Button
    @FindBy(how = How.XPATH, using = "//*[@id='density-calc-form-nonp']/div[3]/div/button[1]")
    private WebElement wePalFCalculateButton;
    // Freight Density
    @FindBy(how = How.ID, using = "nonp-value")
    private WebElement wePalletizedFFreightDensity;

    //################# Cylindrical Freight  ######################
    // Height or Length(in inches)
    @FindBy(how = How.ID, using = "cylindrical-length")
    private WebElement weCynFHeightOrLength;
    // Weight(in pounds)
    @FindBy(how = How.ID, using = "cylindrical-weight")
    private WebElement weCynFWeight;
    // Greatest Dimension(in inches)
    @FindBy(how = How.ID, using = "cylindrical-dimension")
    private WebElement weCynFGreatestDimension;
    // CALCULATE Button
    @FindBy(how = How.XPATH, using = "//*[@id='density-calc-form-cylindrical']/div[3]/div/button[1]")
    private WebElement weCynFCalculateButton;
    // Freight Density
    @FindBy(how = How.ID, using = "cylindrical-value")
    private WebElement weCynFFreightDensity;



    //// Thread.sleep(500);

    //################# Nonpalletized Freight  ####################

    // Enter NonPalletized Freight Length(in inches)
    public void enterNonPalletizedFLength(String NonPalletizedFLenth) throws InterruptedException {
           logger.info("Enter NonPalletized Freight Length(in inches)");
           testUtil.init(this);
           weNonPalletizedFLenth.clear();
           Thread.sleep(500);
           weNonPalletizedFLenth.sendKeys(NonPalletizedFLenth);
    }
    // Enter NonPalletized Freight width(in inches)
    public void enterNonPalletizedFWidth(String NonPalletizedFWidth) throws InterruptedException {
           logger.info("Enter NonPalletized Freight Width(in inches)");
           testUtil.init(this);
           weNonPalletizedFWidth.clear();
           Thread.sleep(500);         
           weNonPalletizedFWidth.sendKeys(NonPalletizedFWidth);
    }
    // Enter NonPalletized Freight Height(in inches)
    public void enterNonPalletizedFHeight(String NonPalletizedFHeight) throws InterruptedException {
           logger.info("Enter NonPalletized Freight Height(in inches)");
           testUtil.init(this);
           weNonPalletizedFHeight.clear();
           Thread.sleep(500);
           weNonPalletizedFHeight.sendKeys(NonPalletizedFHeight);
    }
    // Enter NonPalletized Freight Weight(in pounds)
    public void enterNonPalletizedFWeight(String NonPalletizedFWeight) throws InterruptedException {
           logger.info("Enter NonPalletized Freight Weight(in pounds)");
           testUtil.init(this);
           weNonPalletizedFWeight.clear();
           Thread.sleep(500);
           weNonPalletizedFWeight.sendKeys(NonPalletizedFWeight);
    }
    // Click On NonPalletized Freight CALCULATE Button
    public void clickOnNonPalFCalculateButton() throws InterruptedException {
           logger.info("Click On NonPalletized Freight CALCULATE Button");
           testUtil.init(this);
           weNonPalFCalculateButton.click();
           Thread.sleep(1000);
    }
    // Get NonPalletized Freight Freight Density
    public String getNonPallFreightDensity() {
           logger.info("Get NonPalletized Freight width(in inches)");
           testUtil.init(this);
           String NonPalFreightDensity = weNonPallFFreightDensity.getAttribute("value");
           System.out.println("Your Freight Density (in pounds per cubic foot) is: " + NonPalFreightDensity );
           return NonPalFreightDensity;
    }

    //################# Palletized Freight  #######################

    // Enter Palletized Freight Length(in inches)
    public void enterPalletizedFLength(String PallFreightLength) throws InterruptedException {
           logger.info("Enter Palletized Freight Length(in inches)");
           testUtil.init(this);
           wePalletizedFLenth.clear();
           Thread.sleep(500);
           wePalletizedFLenth.sendKeys(PallFreightLength);
    }
    // Enter Palletized Freight width(in inches)
    public void enterPalletizedFWidth(String PallFreightWidth) throws InterruptedException {
           logger.info("Enter Palletized Freight width(in inches)");
           testUtil.init(this);
           wePalletizedFWidth.clear();
           Thread.sleep(500);
           wePalletizedFWidth.sendKeys(PallFreightWidth);
    }
    // Enter Palletized Freight Height of Pallet(in inches)
    public void enterPalletizedFHeightOfPallet(String HeightOfPallet) throws InterruptedException {
           logger.info("Enter Palletized Freight Height of Pallet(in inches)");
           testUtil.init(this);
           wePalletizedFHeightOfPallet.clear();
           Thread.sleep(500);
           wePalletizedFHeightOfPallet.sendKeys(HeightOfPallet);
    }
    // Enter Palletized Freight Height of Freight(in inches)
    public void enterPalletizedFHeightOfFreight(String HeightOfFreight) {
           logger.info("Enter Palletized Freight Height of Freight(in inches)");
           testUtil.init(this);
           wePalletizedFHeightOfFreight.clear();
           wePalletizedFHeightOfFreight.sendKeys(HeightOfFreight);
    }
    // Enter Palletized Freight Weight(in pounds)
    public void enterPalletizedFWeight(String PallFreighFWeight) throws InterruptedException {
           logger.info("Enter Palletized Freight width(in pounds)");
           testUtil.init(this);
           wePalletizedFWeight.clear();
           Thread.sleep(500);
           wePalletizedFWeight.sendKeys(PallFreighFWeight);
    }
    // Click On Palletized Freight CALCULATE Button
    public void clickOnPalFCalculateButton() throws InterruptedException {
           logger.info("Click On Palletized Freight CALCULATE Button");
           testUtil.init(this);
           wePalFCalculateButton.click();
           Thread.sleep(1000);
    }
    // Get Palletized Freight Freight Density
    public String getPalletizedFreightDensity() {
           logger.info("Get Palletized Freight width(in inches)");
           testUtil.init(this);
           String DenCal = wePalletizedFFreightDensity.getAttribute("value");
           return DenCal;
    }

    //################# Cylindrical Freight  ######################

    // Enter Cylindrical Freight Height or Length(in inches)
    public void enterCynFHeightOrLength(String CynFHeightOrLength) throws InterruptedException {
           logger.info("Enter Cylindrical Freight Height or Length(in inches)");
           testUtil.init(this);
           weCynFHeightOrLength.clear();
           Thread.sleep(500);
           weCynFHeightOrLength.sendKeys(CynFHeightOrLength);
    }
    // Enter Cylindrical Freight  Weight(in pounds)
    public void enterCynFWeight(String CynFWeight) throws InterruptedException {
           logger.info("Enter Cylindrical Freight width(in pounds)");
           testUtil.init(this);
           weCynFWeight.clear();
           Thread.sleep(500);
           weCynFWeight.sendKeys(CynFWeight);
    }
    // Enter Cylindrical Freight  Greatest Dimension(in inches)
    public void enterCynFGreatestDimension(String CynFGreatestDimension) throws InterruptedException {
           logger.info("Enter Cylindrical Freight Height or Length(in inches)");
           testUtil.init(this);
           weCynFGreatestDimension.clear();
           Thread.sleep(500);
           weCynFGreatestDimension.sendKeys(CynFGreatestDimension);
    }
    // Click On Cylindrical Freight  CALCULATE Button
    public void clickOnCynFCalculateButton() throws InterruptedException {
           logger.info("Click On Cylindrical Freight CALCULATE Button");
           testUtil.init(this);
           weCynFCalculateButton.click();
           Thread.sleep(500);
    }
    // Get Cylindrical Freight Freight Density
    public String getCynFFreightDensity() {
           logger.info("Get Palletized Freight width(in inches)");
           testUtil.init(this);
           String CynFreightDensity = weCynFFreightDensity.getAttribute("value");
           return CynFreightDensity;
    }

    public void validateCylindricalFreightDensityCalculatorCalcualtetionResult() {
           logger.info("Validate the Cylindrical Freight Density Calculator calculation result");
           testUtil.init(this);
           String Expected_CynFreightDensity = "15.36";
           String Actual_CynFreightDensity = driver.findElement(By.id("cylindrical-value")).getAttribute("value");
           System.out.println("Your Freight Density (in pounds per cubic foot) = " + Actual_CynFreightDensity);
           assertEquals(Actual_CynFreightDensity, Expected_CynFreightDensity);     
    }
    
    public void validatePalletizedAndNonpalletizedShipmentCalcualtetionResult() {
           logger.info("Validate the Palletized and Nonpalletized Shipment calculation result");
           testUtil.init(this);
           String Expected_NonPallFreightDensity = "11.27";
           String Actual_NonPallFreightDensity = driver.findElement(By.id("nonp-value")).getAttribute("value");
           System.out.println("Your Freight Density (in pounds per cubic foot) = " + Expected_NonPallFreightDensity);
           assertEquals(Actual_NonPallFreightDensity, Expected_NonPallFreightDensity);    
    }
    
    public void validateHoveOverMessageForLengthField() {
           logger.info("Validate the hover over message for Length field");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("nonp-length")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }
    
    public void validateHoveOverMessageForWidthField() {
           logger.info("Validate the hover over message for Width field");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("nonp-width")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }
    
    public void validateHoveOverMessageForWeightField() {
           logger.info("Validate the hover over message for Weight field");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("nonp-weight")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }
    
    public void validateHoveOverMessageForHeightField() {
           logger.info("Validate the hover over message for Height field");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("nonp-height")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }
    
    public void validateHoveOverMessageForHeightFieldForCylindricalFreightDensityCalculator() {
           logger.info("Validate the hover over message for Height field for Cylindrical Freight Density Calculator");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("cylindrical-length")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }
    
    public void validateHoveOverMessageForWeightForCylindricalFreightDensityCalculator() {
           logger.info("Validate the hover over message for Weight field for Cylindrical Freight Density Calculator");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("cylindrical-weight")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }
    
    public void validateHoveOverMessageForGreatestDimentionForCylindricalFreightDensityCalculator() {
           logger.info("Validate the hover over message for Greatest Dimention field for Cylindrical Freight Density Calculator");
           testUtil.init(this);
           String ExpectedMessage = "Please fill out this field.";
           String ActualMessage = driver.findElement(By.id("cylindrical-dimension")).getAttribute("validationMessage");
           System.out.println("Message is: " + ActualMessage);
           assertEquals(ActualMessage , ExpectedMessage);       
    }

}
