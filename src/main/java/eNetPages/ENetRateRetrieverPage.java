package eNetPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class ENetRateRetrieverPage extends TestBase{
	
	private Logger logger=Logger.getLogger(ENetRateRetrieverPage.class);
	
	@FindBy(how = How.ID, using = "accountNumber")
	private WebElement AccountNumberField;
	
	@FindBy(how = How.ID, using = "my_role")
	private WebElement Role;
	
	@FindBy(how = How.ID, using = "terms")
	private WebElement Term;
	
	@FindBy(how = How.ID, using = "zipOrigin")
	private WebElement OriginZip;
	
	@FindBy(how = How.ID, using = "zipDestination")
	private WebElement DesZip;
	
	@FindBy(how = How.ID, using = "commClass0")
	private WebElement Class;
	
	@FindBy(how = How.ID, using = "weight0")
	private WebElement Weight;
	
	@FindBy(how = How.ID, using = "ratequoteSubmitButton")
	private WebElement Submit;
	
	
	
	
	
	public void enterAccountNumber(String aNum) {
		testUtil.init(this);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame1);
		logger.info("enter Account Number");
		AccountNumberField.sendKeys(aNum);
	}
	
	public void selectRole(String role) {
		testUtil.init(this);
		logger.info("select Role");
		testUtil.selectUsingVisibleText(Role, role);
	}
	
	public void selectTerm(String term) {
		testUtil.init(this);
		logger.info("select Term");
		testUtil.selectUsingVisibleText(Term, term);
	}
	
	public void selectClass(String mClass) {
		testUtil.init(this);
		logger.info("select Class");
		testUtil.selectUsingVisibleText(Class, mClass);
	}
	
	public void enterOriginZip(String oZip) {
		testUtil.init(this);
		logger.info("enter Origin Zip ");
		OriginZip.sendKeys(oZip);
		driver.findElement(By.xpath("//a[contains(text(),'WEST MIDDLESEX, PA 16159')]")).click();
	}
	
	public void enterDestinationZip(String dZip) {
		testUtil.init(this);
		logger.info("enter Destination Zip ");
		DesZip.sendKeys(dZip);
		driver.findElement(By.xpath("//a[contains(text(),'LOS ANGELES, CA 90007')]")).click();
	}
	
	public void enterWeight(String weight) {
		testUtil.init(this);
		logger.info("enter Weight");
		Weight.sendKeys(weight);
	}
	
	public void clickOnSubmitButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("click On Submit Button");
		Submit.click();
		
	}

}
