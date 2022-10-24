package myEstesPages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesCustomizeQuickLinkPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesCustomizeQuickLinkPage.class);

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Rates')]")
	private WebElement Rate;

	@FindBy(how = How.XPATH, using = "(//*[@class='btn btn-primary'][contains(text(),'Remove')])[1]")
	private WebElement Remove;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='btn btn-primary'][contains(text(),'Add')])[1]")
	private WebElement Add;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Return to Welcome Page')]")
	private WebElement Return;
	
	@FindBy(xpath="//button[contains(text(),'Remove')]")
	private List<WebElement> listOfRemove;
	
	@FindBy(how = How.XPATH, using = "//a[@class='customize ng-star-inserted']")
	private WebElement Customize;
	
	@FindBy(xpath = "//div[@class='form-row']//button[contains(text(),'Add')]//parent::div")
	private List<WebElement> listOfApplication;
	
	@FindBy(xpath = "//div[@class='form-row']//button[contains(text(),'Add')]")
	private List<WebElement> listOfAddBtn;

	/**********************METHODS*********************/

	public void validateTitle() {
		logger.info("Validate title");
		testUtil.init(this);
		String title = driver.findElement(By.xpath("//span[contains(text(),'Customize Quick Links')]")).getText();
		System.out.println("Page title is: " + title);
		String expected = "Customize Quick Links";
		Assert.assertEquals(title, expected);
	}

	public String clickOnRemoveButton() throws Exception {
		logger.info("click on remove Button");
		testUtil.init(this);
		String qName=driver.findElement(By.xpath("//*[@id=\"main\"]/lib-manage-quick-links/div/div[3]/div[1]/div[1]")).getText().replace("REMOVE", "");		
		System.out.println(qName+"Removed to Availabe Applications Culumn");
		Remove.click();
		Thread.sleep(1000);
		return qName;
	}

	public String clickOnAddButton() throws Exception {
		logger.info("click on add Button");
		testUtil.init(this);
		String aName=driver.findElement(By.xpath("//*[@id=\"main\"]/lib-manage-quick-links/div/div[2]/div/div[1]")).getText().replace("ADD", "");		
		System.out.println(aName + "Add to Quick Link");
		Add.click();
		Thread.sleep(1000);
		return aName;
	}
	
	public void clickOnReturnToWelcomePageButton() throws Exception {
		logger.info("click on return to welcome  page Button");
		testUtil.init(this);
		testUtil.setExplicitWait(Return, 60);
		testUtil.clickElementJavascript(Return);
		//Return.click();
		
	}
	
	
	public void validateQuickLinkIsRemoved(String appName) throws InterruptedException, IOException {
		logger.info("Validate quick link removed");
		testUtil.init(this);
		List<WebElement> we = driver.findElements(
				By.xpath("//*[@id=\"main\"]/lib-manage-quick-links/div/div[2]/div/div[*]"));

		int listSize = we.size();
		for (int i = 0; i < listSize; i++) {
			String id = we.get(i).getText().replace("ADD", "");
			//System.out.println(we.get(i).getText());

			if (id.contains(appName)) {

				System.out.println(appName + "Application name in Available Application column");
				
				break;
			}else{
				
			System.out.println("Not Exist!!!");}

		}
		
	}

		public void validateAddToQuickLink(String name) throws InterruptedException {
			logger.info("Validate application add to quick link ");
			testUtil.init(this);
			
			List<WebElement> we = driver.findElements(
					By.xpath("//*[@id=\"main\"]/lib-manage-quick-links/div/div[3]/div[1]/div[*]"));

		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='main']/lib-manage-quick-links/div/div[3]/div[1]/div[*]")));
			
			int listSize = we.size();
			for (int i = 0; i < listSize; i++) {
				String id = we.get(i).getText().replace("REMOVE", "");
				//System.out.println(we.get(i).getText());

				if (id.contains(name)) {

					System.out.println(name+"Added to Quick Link");
					
					break;
				}else{
					
				System.out.println("Not Exist!!!");}

			}

		}
		
		public void recordQuickLink()  {
			logger.info("Validate application add to quick link ");
			testUtil.init(this);
			
			List<WebElement> we = driver.findElements(By.xpath("//*[@id=\"main\"]/lib-manage-quick-links/div/div[3]/div[1]/div[*]"));

			int listSize = we.size();
			
			for (int i = 0; i < listSize; i++) {
				String id = we.get(i).getText().replace("REMOVE", "");
				System.out.println(id);
			
				//return id;
			}
			
			
		}
	
		public String getQuickLinkName() throws IOException  {
			logger.info("record removed application name");
			testUtil.init(this);
			String text=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/lib-manage-quick-links/div/div[3]/div[1]/div[1]")).getText();
			//text.split(text);
			//String line= System.getProperty("line.separator");
			//String strArray[] = new String[13];
			//BufferedReader brTest = new BufferedReader(new FileReader(text));
			//String text1 = brTest.readLine();
			// Stop. text is the first line.
			//System.out.println(text);
			/*String[] strArray = text1.split(",");
			System.out.println(Arrays.toString(strArray));
			
			System.out.println("removed application name is : " +strArray);*/
			try {
				
				FileReader fileReader = new FileReader(text);
				BufferedReader reader = new BufferedReader(fileReader);
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					// read next line
					line = reader.readLine();
				
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return text;	
}
	
	public boolean validateErrorMessage() throws InterruptedException {
		logger.info("Validaate Error Message");

		String error = "  ERROR: You must remove a Quick Link before you can add a new one.";
		String success = "Success! Link added successfully.";
		
		WebElement ele = driver.findElement(By.xpath("//snack-bar-container[@role='alert' ]"));
		String message = ele.getText().trim();
		
		System.out.println(message);
		testUtil.setHardWait(1000);
		boolean flag=false;

		if (message.equalsIgnoreCase(success)) {
			System.out.println("Add Quick Link");
			flag = true;
		} else {
			System.out.println("Already More Than 5 Quick Links");
			flag = false;
		}

		return flag;

	}
	
	public boolean retriveSpecifiedQuickLinksIsDisplayed(String linkName) {
		logger.info("Verify "+linkName+" is displayed");
		testUtil.init(this);
		boolean existence = false;
		try {
			WebElement ele = driver.findElement(By.xpath("//div[@class='quick-links-container']//span[contains(text(),'"+linkName+"')]"));
			testUtil.setExplicitWait(ele, 30);
			existence = ele.isDisplayed();
		} catch(Exception e) {
			logger.error("element is not Displayed..", e.getCause());
		}
		return existence;
	}
	
	public void removeAnyQuickLinksFromContainers() {
		logger.info("Click Remove button on any displayed Quick links");
		testUtil.init(this);
		if(listOfRemove.size()>0) {
			listOfRemove.get(0).click();
		}
	}
	
	public void clickOnCustomizeLink() {
		logger.info("Click on Customize link");
		testUtil.init(this);
		Customize.click();
	}
	
	public void addQuickLink(String quickLink) throws InterruptedException {
		
		logger.info("Adding Quick links....");
		testUtil.init(this);
		System.out.println(listOfApplication.size());
		testUtil.setHardWait(2000);;
		for(int i=0; i<listOfApplication.size(); i++) {
			String name = listOfApplication.get(i).getText().trim();
			if(name.contains(quickLink)) {
				testUtil.clickElementJavascript(listOfAddBtn.get(i));
				break;
			}
		}
	}
	
	public void addOrRemoveQuickLinks(String linkName) throws Exception {
		
		logger.info("Add/Remove Quick links");
		testUtil.init(this);
		if(!retriveSpecifiedQuickLinksIsDisplayed(linkName)) {
			clickOnCustomizeLink();
			testUtil.setHardWait(1000);
			removeAnyQuickLinksFromContainers();
			testUtil.setHardWait(1000);
			addQuickLink(linkName);
			clickOnReturnToWelcomePageButton();
			testUtil.setHardWait(1000);
		}
	}
	

}
