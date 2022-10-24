package eNetPages;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import testBase.TestBase;

public class ENetTimeCriticalQuoteMaintenancePage extends TestBase{
	
	
	private Logger logger=Logger.getLogger(ENetVTLTableMaintenancePage.class);
    @FindBy(how=How.XPATH, using="//iframe[@id='mainpage']")
    private WebElement mainPage; 
    @FindBy(how = How.XPATH, using = "//*[@id='accountNumber']")
    private WebElement account; 
    @FindBy(how=How.XPATH, using = "//select[@id='myRole']/option[@selected]")
    private WebElement role; 
    @FindBy(how=How.XPATH, using = "//input[@id='company']")
    private WebElement company; 
    @FindBy(how=How.XPATH, using="//input[@id='contactName']")
    private WebElement name; 
    @FindBy(how=How.XPATH, using="//input[@id='phone']")
    private WebElement phone; 
    @FindBy(how=How.XPATH, using="//input[@id='email']")
    private WebElement email; 
    @FindBy(how=How.XPATH, using="//input[@id='zipOrigin']")
    private WebElement zipOrigin; 
    @FindBy(how=How.XPATH, using="//input[@id='cityOrigin']")
    private WebElement cityOrigin; 
    @FindBy(how=How.XPATH, using="//input[@id='stateOrigin']")
    private WebElement stateOrigin; 
    @FindBy(how=How.XPATH, using="//input[@id='zipDestination']")
    private WebElement zipDestination; 
    @FindBy(how=How.XPATH, using="//input[@id='cityDestination']")
    private WebElement cityDestination; 
    @FindBy(how=How.XPATH, using="//input[@id='stateDestination']")
    private WebElement stateDestination; 
    @FindBy(how=How.XPATH, using ="//select[@name='commodityClass_1']")
    private WebElement commodityClass1; 
    @FindBy(how=How.XPATH, using ="//select[@name='commodityClass_2']")
    private WebElement commodityClass2; 
    @FindBy(how=How.XPATH, using="//input[@id='stackable']")
    private WebElement stackableBox; 
    @FindBy(how=How.XPATH, using="//a[@id='logout']")
    private WebElement logout; 
    @FindBy(how=How.XPATH, using="//input[@value='Logout']")
    private WebElement logout2; 
    
    //********************Methods*****************************//
    public void verifyExceptions(String ...exceptionValues) {
        logger.info("Validate Exceptions");
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage);
        testUtil.setHardWait(1000);
        logger.info("Identify specific Exceptions on screen");
        for(int i = 0; i < exceptionValues.length; i++) {
            assertTrue(driver.findElement(By.xpath("//span[contains(text(), '"+exceptionValues[i]+"')]")).isDisplayed()); 
        }
    }
    public void verifyContactShippingInfo(String ...contactShippingDetails) {
        logger.info("Validate Contact Shipping Info");
        testUtil.init(this); 
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage);
        testUtil.setHardWait(1000);
        logger.info("Validate Shipping and Contact information"); 
        assertTrue(role.getText().contains(contactShippingDetails[0])); 
        assertTrue(driver.findElement(By.xpath("//select[@id='terms']/option[@selected and contains(text(), '"+contactShippingDetails[1]+"')]")).isDisplayed()); 
        assertTrue(driver.findElement(By.xpath("(//input[@value='"+contactShippingDetails[2]+"'])[2]")).isDisplayed()); 
        for(int i = 3; i < contactShippingDetails.length; i++) {
            assertTrue(driver.findElement(By.xpath("//input[@value='"+contactShippingDetails[i]+"']")).isDisplayed()); 
        }
    }
    public void validateCommodityClass(String ...values) {
        logger.info("Validate Commodity Class Pieces"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//select[@name='commodityClass_"+(i+1)+"']/option[@selected]")).getText())); 
        }
    }
    public void validateCommodityPieces(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//input[@name='commodityPieces_"+(i+1)+"']")).getText()));
        }
    }
    public void validateCommodityPiecesType(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//select[@name='commodityPieceType_"+(i+1)+"']/option[@selected]")).getText())); 
        }
    }
    public void validateCommodityTotalWeight(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//input[@name='commodityTotalWeight_"+(i+1)+"']")).getText())); 
        }
    }
    public void validateCommodityLength(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//input[@name='commodityDimensionLength_"+(i+1)+"']")).getText())); 
        }
    }
    public void validateCommodityWidth(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//input[@name='commodityDimensionWidth_"+(i+1)+"']")).getText())); 
        }
    }
    public void validateCommodityHeight(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//input[@name='commodityDimensionHeight_"+(i+1)+"']")).getText())); 
        }
    }
    public void validateCommodityDescription(String ...values) {
        logger.info("Validate Commodity Class Values"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000); 
        for(int i = 0; i < values.length; i++) {
            assertTrue(values[i].contains(driver.findElement(By.xpath("//input[@name='commodityDescription_"+(i+1)+"']")).getText())); 
        }
    }
    public void validateStackable() {
        logger.info("Validate if stackable is selected"); 
        testUtil.init(this);
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000);
        assertTrue(stackableBox.isSelected()); 
    }
    public void validateAccessorials(String ...accessorials) {
        logger.info("Validate if Accessorial is present ");
        testUtil.init(this); 
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000);
        for(int i = 0; i < accessorials.length; i++ ) {
            assertTrue(driver.findElement(By.xpath("//select[@name='accessorial_"+(i+1)+"']/option[@selected and (contains(text(),'"+accessorials[i]+"'))]")).isDisplayed()); 
        }
    }
    public void validateAccessorial(String accessorial) {
        logger.info("Validate if Accessorial is present "+ accessorial);
        testUtil.init(this); 
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000);
        assertTrue(driver.findElement(By.xpath("//input[@value= \"" + accessorial + "\" ]")).isDisplayed()); 
    }
    public void clickLogOut() {
        logger.info("Click Logout Button");
        testUtil.init(this); 
        driver.switchTo().defaultContent(); 
        logout.click();
        testUtil.setHardWait(1000); 
    }
    public void clickLogOutFromIntranet() {
        logger.info("Click Logout Button");
        testUtil.init(this); 
        driver.switchTo().defaultContent(); 
        driver.switchTo().frame(mainPage); 
        testUtil.setHardWait(2000);
        logout2.click();
    }


}
