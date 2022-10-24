package densityCalculatorPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class MyEstesDensityCalculatorPage extends TestBase {


	private Logger logger = Logger.getLogger(MyEstesDensityCalculatorPage.class.getClass());

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



	//// 

	//################# Nonpalletized Freight  ####################

	// Enter NonPalletized Freight Length(in inches)
	public void enterNonPalletizedFLenth(String NonPalletizedFLenth) {
		logger.info("Enter NonPalletized Freight Length(in inches)(enterNonPalletizedFLenth)\r\n");
		testUtil.init(this);
		weNonPalletizedFLenth.sendKeys(NonPalletizedFLenth);
	}
	// Enter NonPalletized Freight width(in inches)
	public void enterNonPalletizedFWidth(String NonPalletizedFWidth) {
		logger.info("Enter NonPalletized Freight Width(in inches)");
		testUtil.init(this);
		weNonPalletizedFWidth.sendKeys(NonPalletizedFWidth);
	}
	// Enter NonPalletized Freight Height(in inches)
	public void enterNonPalletizedFHeight(String NonPalletizedFHeight) {
		logger.info("Enter NonPalletized Freight Height(in inches)");
		testUtil.init(this);
		weNonPalletizedFHeight.sendKeys(NonPalletizedFHeight);
	}
	// Enter NonPalletized Freight Weight(in pounds)
	public void enterNonPalletizedFWeight(String NonPalletizedFWeight) {
		logger.info("Enter NonPalletized Freight Weight(in pounds)");
		testUtil.init(this);
		weNonPalletizedFWeight.sendKeys(NonPalletizedFWeight);
	}
	// Click On NonPalletized Freight CALCULATE Button
	public void clickOnNonPalFCalculateButton() {
		logger.info("Click On NonPalletized Freight CALCULATE Button");
		testUtil.init(this);
		weNonPalFCalculateButton.click();
	}
	// Get NonPalletized Freight Freight Density
	public String getNonPallFFreightDensity() {
		logger.info("Get NonPalletized Freight width(in inches)");
		testUtil.init(this);
		String NonPalFreightDensity = weNonPallFFreightDensity.getAttribute("value");
		return NonPalFreightDensity;
	}

	//################# Palletized Freight  #######################

	// Enter Palletized Freight Length(in inches)
	public void enterPalletizedFLength(String PallFreightLength) {
		logger.info("Enter Palletized Freight Length(in inches)");
		testUtil.init(this);
		wePalletizedFLenth.sendKeys(PallFreightLength);
	}
	// Enter Palletized Freight width(in inches)
	public void enterPalletizedFWidth(String PallFreightWidth) {
		logger.info("Enter Palletized Freight width(in inches)");
		testUtil.init(this);
		wePalletizedFWidth.sendKeys(PallFreightWidth);
	}
	// Enter Palletized Freight Height of Pallet(in inches)
	public void enterPalletizedFHeightOfPallet(String HeightOfPallet) {
		logger.info("Enter Palletized Freight Height of Pallet(in inches)");
		testUtil.init(this);
		wePalletizedFHeightOfPallet.sendKeys(HeightOfPallet);
	}
	// Enter Palletized Freight Height of Freight(in inches)
	public void enterPalletizedFHeightOfFreight(String HeightOfFreight) {
		logger.info("Enter Palletized Freight Height of Freight(in inches)");
		testUtil.init(this);
		wePalletizedFHeightOfFreight.sendKeys(HeightOfFreight);
	}
	// Enter Palletized Freight Weight(in pounds)
	public void enterPalletizedFWeight(String PallFreighFWeight) {
		logger.info("Enter Palletized Freight width(in pounds)");
		testUtil.init(this);
		wePalletizedFWeight.sendKeys(PallFreighFWeight);
	}
	// Click On Palletized Freight CALCULATE Button
	public void clickOnPalFCalculateButton() {
		logger.info("Click On Palletized Freight CALCULATE Button");
		testUtil.init(this);
		wePalFCalculateButton.click();
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
	public void enterCynFHeightOrLength(String CynFHeightOrLength) {
		logger.info("Enter Cylindrical Freight Height or Length(in inches)");
		testUtil.init(this);
		weCynFHeightOrLength.sendKeys(CynFHeightOrLength);
	}
	// Enter Cylindrical Freight  Weight(in pounds)
	public void enterCynFWeight(String CynFWeight) {
		logger.info("Enter Cylindrical Freight width(in pounds)");
		testUtil.init(this);
		weCynFWeight.sendKeys(CynFWeight);
	}
	// Enter Cylindrical Freight  Greatest Dimension(in inches)
	public void enterCynFGreatestDimension(String CynFGreatestDimension) {
		logger.info("Enter Cylindrical Freight Height or Length(in inches)");
		testUtil.init(this);
		weCynFGreatestDimension.sendKeys(CynFGreatestDimension);
	}
	// Click On Cylindrical Freight  CALCULATE Button
	public void clickOnCynFCalculateButton() {
		logger.info("Click On Cylindrical Freight CALCULATE Button");
		testUtil.init(this);
		weCynFCalculateButton.click();
	}
	// Get Cylindrical Freight Freight Density
	public String getCynFFreightDensity() {
		logger.info("Get Palletized Freight width(in inches)");
		testUtil.init(this);
		String CynFreightDensity = weCynFFreightDensity.getAttribute("value");
		return CynFreightDensity;
	}


}
