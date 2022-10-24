package eNetPages;


import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class ENetDigitalPictureViewingPage extends TestBase {
	
	private Logger logger = Logger.getLogger(ENetHomePage.class);
	
	@FindBy(how = How.XPATH, using = "//*[@name='doctype_select']/table/tbody/tr")
	private  List<WebElement> DocTypes;
	
	@FindBy(how = How.XPATH, using = "//table[@class='form1']")
	private  WebElement SearchTable;
	
	@FindBy(how = How.ID, using = "EAACCT")
	private WebElement EfwAccountNum;
	
	@FindBy(how = How.ID, using = "Submit")
	private WebElement SubmitBtn;
	
	//@FindBy(how = How.TAG_NAME , using = "a")
	@FindBy(how=How.XPATH,using="//div/div/table/tbody/tr[2]")
	private WebElement DocID ;
	
	////body//div/div/table/tbody/tr[2]/td[3]//a[1]
	/*************************************************************************/
	
	public void verifyDocTypesDisplayed() {
		testUtil.init(this);
		List<WebElement> AllDocs = driver.findElements(By.xpath("//*[@name='doctype_select']/table/tbody/tr"));
		for(WebElement doctype:AllDocs) {
			assertEquals(testUtil.isDisplayed(doctype), true);
		}
		logger.info("Total Displayed Document types are "+AllDocs.size());
		
	}
	
	public void selectDocType(String type) {
		testUtil.init(this);
		WebElement doc = driver.findElement(By.xpath("//input[@value='"+type+"']"));
		assertEquals(doc.isSelected(), false);
		doc.click();
		logger.info("Document type "+doc.getText()+" is Selected");
	}
	
	public void verifyTableDisplayed() {
		testUtil.init(this);
		testUtil.switchToFrame("searchInput");
		assertEquals(SearchTable.isDisplayed(), true);
		//String docType = driver.findElement(By.xpath("//table[@class='form1']/tbody/tr/th")).getText();
		String docType=driver.findElement(By.xpath("//html/body/form/table/tbody/tr[1]/th")).getText();
		logger.info("Table "+docType+" is Displayed");
	}
	
	public void enterEfwAccNum(String number) {
		logger.info("Enter EFW Account Number");
		testUtil.init(this);
		EfwAccountNum.sendKeys(number);

	}
	
	public void clickSubmit() {
		testUtil.init(this);
		logger.info("Click on Submit button ");
		assertEquals(SubmitBtn.isEnabled(), true);
		SubmitBtn.click();
		testUtil.setHardWait(2000);
	}

	public void verifyDocIDisDisplayed() {
		testUtil.init(this);
		logger.info("Document ID is Displayed");
		
		testUtil.setHardWait(3000);
	
		WebElement ele= driver.findElement(By.xpath("//a[@id='home']"));
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(90));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='home']")));
		
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeAsyncScript("arguments[0].scrollIntoView(true);", ele);
		//testUtil.setHardWait(1000);
		String str=ele.getText();
		System.out.println("The ID is:" + str);
	//	assertEquals(DocID.isDisplayed(), true);
		//"//html//body/div/div/table/tbody/tr[2]/td[1]
		
	}
	
	public  String getDocID() {
		testUtil.init(this);
		logger.info("Get Document ID text");
		driver.switchTo().frame("mainpage");
		WebElement ele= driver.findElement(By.xpath("//div/div/table/tbody/tr[2]/td[3]//a[1]"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeAsyncScript("return documents[0].innerHTML;",driver.findElement((By.xpath("//div/div/table/tbody/tr[2]/td[3]//a[1]"))).getText());
		js.executeAsyncScript("return documents[0].innerHTML;",ele);
		String str= ele.getText();
		//String id = DocID.getText();
	//	logger.info("Document ID is "+id);
		System.out.println("Document id is:" +str);
	//	return id;
		return str;
	}
	
	public  void clickOnDocID() {
		testUtil.init(this);
		DocID.click();
		logger.info("Clicked on Document Id Link");
	}
	
	public String getDocumentIDAfterLinkOpened(String exp) {
		testUtil.init(this);
		String value="";
		List<WebElement> col = driver.findElements(By.xpath("//table/tbody/tr/th"));
		for(int i =0;i<col.size();i++) {
			String name=col.get(i).getText();
			
			if(name.contains(exp))
				 value = driver.findElement(By.xpath("//table/tbody/tr/td["+(i+1)+"]")).getText();
		}
		logger.info("Value Retreive from Image page "+value);
		return value;
	}
	
	public void clickOnApplicationsTab() {
		logger.info("Click on Applications tab");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='Applications']")));
		ele.click();
		// Applications.click();
		testUtil.setHardWait(1000);

	}
}

