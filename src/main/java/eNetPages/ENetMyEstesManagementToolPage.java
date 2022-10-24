package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetMyEstesManagementToolPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetMyEstesManagementToolPage.class);

	@FindBy(how = How.XPATH, using = "//input[@id='accountCodeSearchField'][@name='accountCodeSearchField']")
	private WebElement AccountCode;

	@FindBy(how = How.XPATH, using = "//form[@id='myEstesUserSearchForm']/table/tbody/tr[6]/td/span[1]/input")
	private WebElement SearchBtn;

	@FindBy(how = How.XPATH, using = "//span[2]//a[2]//img[1]")
	private WebElement NextBtn;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'TESTSAT')]")
	private WebElement TestSAT;

	@FindBy(how = How.XPATH, using = "//a[text()='Edit Menu Items']")
	private WebElement lnkEditMenuItems;
	
	@FindBy(how = How.ID, using = "searchButton")
	private WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//a[text()='TESTSAT']")
	private WebElement lnkTestStat;
	
	@FindBy(how = How.XPATH, using = "(//a[@href='MainServlet?action=nextActivePage'])[1]")
	private WebElement Next; 
	
	@FindBy(how = How.XPATH, using = "//input[@id='userNameSearchField']")
	private WebElement userName; 
	
	@FindBy(how=How.XPATH, using = "//img[@src='images/next-arrow.gif']")
	private WebElement rightArrow; 
	
	@FindBy(how=How.XPATH, using = "//button[@class='button'][2]")
	private WebElement redXButton; 
	
	@FindBy(how=How.XPATH, using = "//a[@id='logout']")
	private WebElement logoutButton; 
	
	@FindBy(how=How.XPATH, using = "//input[@type='submit'][@value='Logout']")
	private WebElement confirmationLogOutButton; 
	/**********************************METHODS******************************************/

	public void enterAccountCode(String aNum) {
		testUtil.init(this);
		logger.info("Enter Account Code Number");

		//WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		//driver.switchTo().frame(frame1);

		testUtil.setHardWait(2000);
		testUtil.clickElementJavascript(AccountCode);
		AccountCode.clear();
		AccountCode.sendKeys(aNum);
	}

	public void clickOnSearchButton() {
		logger.info("Click On Search button");
		testUtil.init(this);
		SearchBtn.click();

	}

	public void clickOnNextButton() {
		logger.info("Click On Next Button");
		testUtil.init(this);
		NextBtn.click();

	}

	public boolean selectTestSAT() {
        logger.info("Select TestSAT ");
        testUtil.init(this); 
        
        List<WebElement> ele=driver.findElements(By.xpath("/html[1]/body[1]/div[2]/div[4]/div[3]/div[1]/table[1]/tbody[1]/tr[*]/td[2]/a[1]"));
        int size=ele.size();
        //int size=ele.size();
        System.out.println(size);
        String name="TESTSAT";
        
        for(int i=0; i<ele.size(); i++) {
               
               String userName=ele.get(i).getText();
               
               testUtil.setHardWait(300);
               System.out.println(userName);
               //testUtil.setHardWait(300);
               if(userName.equals(name)) {
                     boolean flag=true;
                     int j=i;
                     
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[4]/div[3]/div[1]/table[1]/tbody[1]/tr['"+j+"']/td[2]/a")).click();
               return flag;
               }
                            
		}
		return false;
	}

	public void selectTESTSAT() {
		logger.info("Select TestSAT ");
		testUtil.init(this);
		//TestSAT.click();
		testUtil.clickElementJavascript(TestSAT);
		
	}


	public void clickOnEditMenuItems() {
		logger.info("Click On Edit Menu Items Link");
		testUtil.init(this);
		driver.switchTo().defaultContent();
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='fullViewIframe']"));
		driver.switchTo().frame(frame);

		lnkEditMenuItems.click();

	}
	
	public void clickOnSearch() {
		testUtil.init(this);
		logger.info("click On Search");
		btnSearch.click();
	}

	public void clickOnTestSat() {
		testUtil.init(this);
		logger.info("click On Test Stat Link");
		lnkTestStat.click();
	}

	public void clickOnRemove(String code, String description) {
		testUtil.init(this);
		logger.info("click On Test Stat Link");
		List<WebElement> removeIcon = driver.findElements(
				By.xpath("//tr[td[text()='" + code + "'] and td[text()='" + description + "']]/td/center/a"));
		
		for (WebElement e : removeIcon) {
			e.click();
			driver.switchTo().alert().accept();
		//	break;
		}
	}
	public void clickOnNextActivePage() {
		testUtil.init(this);
		logger.info("Click On Next Active page");
		Next.click();
	} 
	
	public void enterUserName(String uName) { 
        testUtil.init(this);
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
        driver.switchTo().frame(frame1);
        logger.info("Enter Account Code Number");
        testUtil.setHardWait(1000);
        userName.sendKeys(uName);
    }
	
	public void selectUserName(String userName) {
		testUtil.init(this);
		WebElement name = driver.findElement(By.xpath("//a[contains(text(), '"+userName.trim()+"')]")); 
		name.click(); 
	}
	
	public void clickEditBlockApplications() {
		driver.switchTo().defaultContent();
		WebElement tableFrame = driver.findElement(By.xpath("//iframe[@class='full_view_iframe']"));
		driver.switchTo().frame(tableFrame);
		driver.findElement(By.xpath("//a[contains(text(),'Edit Blocked Applications')]")).click();
	}
	
	//My Estes Blocked Applications pop up
	public void selectApplication(String application) {
		testUtil.init(this);
		logger.info("Within the Authorized Application, I am validating if the specific application be blocked."); 
		try {
			logger.info("Specific Application is not already blocked, procceed to block as it is not visible with the 'Blocked Applications' column"); 
			driver.findElement(By.xpath("//select[@id='selectAccess']/option[contains(text(), '"+application+"')]")).click(); 
			clickOnRightArrow(); 
			}catch(NoSuchElementException e) {
				logger.info("Test passed because application is already blocked for this username"); 
			}catch(Exception w) {
				logger.info("There is another issue that should be investigated, test will fail here."); 
				w.printStackTrace(); 
				Assert.fail(); 
			}

		}
	
	private void clickOnRightArrow() {
		logger.info("Click on Right Pointed Arrow to allow the application to move from Authorized to Blocked Applications"); 
		rightArrow.click(); 
	}
	
	public void clickSubmitButton() {
		testUtil.init(this);
		driver.findElement(By.xpath("//form[@id='editBlockedApplicationsForm']/table/tbody/tr[4]/td/input")).click(); 
	}
	
	public void clickRedXButton() {
		driver.switchTo().defaultContent(); 
		WebElement redXButton = driver.findElement(By.xpath("//div[@class='pop_up_pane']/div/button[2]"));
		logger.info("Click Red X Button"); 
		redXButton.click(); 
	}
	
	public void clickLogoutButton() {
		testUtil.init(this); 
		logoutButton.click(); 
	}
	
	public void confirmationLogoutClick() {
		driver.switchTo().defaultContent();
		WebElement tableFrame = driver.findElement(By.xpath("//iframe[@class='mainpage']"));
		driver.switchTo().frame(tableFrame);
		logger.info("Clicking the confirmation logout button"); 
		confirmationLogOutButton.click(); 
	}
}
