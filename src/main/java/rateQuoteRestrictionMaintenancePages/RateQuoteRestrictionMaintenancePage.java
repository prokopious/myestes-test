package rateQuoteRestrictionMaintenancePages;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import hudson.model.Build;
import testBase.TestBase;




public class RateQuoteRestrictionMaintenancePage extends TestBase{

	
	
private Logger logger=Logger.getLogger(RateQuoteRestrictionMaintenancePage.class);

	/*********** ELEMENTS ***********/
	
	
	@FindBy(how=How.XPATH, using = "//input[@id='eventName']")
	private WebElement eventName; 
	
	@FindBy(how = How.XPATH, using = "//input[@id='effectiveDate']")
	private WebElement effectiveDate;
	
	@FindBy(how = How.XPATH, using = "//input[@id='expirationDate']")
	private WebElement expirationDate;
	
	@FindBy(how = How.XPATH, using = "//select[@id='terminalId']")
	private WebElement terminalName;
	
	@FindBy(how = How.XPATH, using = "//button/span[@class='ui-icon ui-icon-triangle-2-n-s']")
	private WebElement zipCodeDropDown;
	
	@FindBy(how=How.XPATH, using = "//textarea[@id='message']")
	private WebElement rateQuoteResponseMessage; 
	
	@FindBy(how=How.XPATH, using = "//input[@id='manageTerminalButtonId']")
	private WebElement addButton; 
	
	@FindBy(how=How.XPATH, using="//div[contains(text(), 'Information successfully saved.')]")
	private WebElement successMessage; 
	
	@FindBy(how=How.XPATH, using="//span[contains(text(), 'Row count:')]/following-sibling::select")
	private WebElement rowCountDropDownButton; 
	
	@FindBy(how=How.XPATH, using="//input[@id='manageTerminalButtonId']")
	private WebElement updateButton; 
	
	//WebElement is located in the Edit Warning Tab that pops up after an update is selected
	@FindBy(how=How.XPATH, using = "//button[@type='button']/span[contains(text(), 'Yes')]")
	private WebElement clickYesButton; 
	
	@FindBy(how=How.XPATH, using="//div[@id='dialog-confirm']/p/span[contains(text(), 'Are you sure you want to edit this information?')]")
	private WebElement validateUpdateMessage; 
	
	@FindBy(how=How.XPATH, using="//a[@id='logout']")
	private WebElement logoutButton; 

	/*********** METHODS ***********/

	public void enterEffectiveDate(String date) {
		testUtil.init(this);
		logger.info("Enter Expiration Date");
		effectiveDate.sendKeys(date);
	}
	
	public void enterExpirationDate(String date) {
		testUtil.init(this);
		logger.info("Enter Expiration Date");
		expirationDate.sendKeys(date);
	}
	
	public void enterEventName(String name) {
		testUtil.init(this);
		logger.info("Enter Event Name");
		testUtil.setHardWait(500);
		eventName.sendKeys(name);
	}
	
	public void enterTerminalName(String name) {
		testUtil.init(this);
		logger.info("Enter Terminal Name");
		testUtil.setHardWait(500);
		terminalName.sendKeys(name);
	}
	
	public void selectZipCode(String zipCode) {
		testUtil.init(this);
		logger.info("Select 'Deselect All' then select zip code: "+ zipCode); 
		testUtil.setHardWait(500);
		
		Actions action = new Actions(driver); 
		action.click(zipCodeDropDown); 
		
		WebElement deselectAllTab = driver.findElement(By.xpath("//span[contains(text(), 'Deselect All')]")); 
		action.moveToElement(deselectAllTab);
		testUtil.clickElementJavascript(deselectAllTab);
		testUtil.setHardWait(500);
		//a space is needed right after the zip code element, or driver will not identify this web element. 
		WebElement selectZipCodeButton = driver.findElement(By.xpath("//input[@title='"+zipCode+""+' '+"']")); 
		action.moveToElement(selectZipCodeButton);
		testUtil.clickElementJavascript(selectZipCodeButton);
		
	}
	
	/**
	 * Options for this radio button, metho parameter inputs are: 
	 * <ul>
	 * <li> outbound </li>
	 * <li> inbound </li>
	 * <li> both </li>
	 *</ul>
	 * @param radioButtonOption
	 */
	public void selectRadioButton(String radioButtonOption) {
		logger.info("Select " + radioButtonOption + " in Restriction Setup"); 
		testUtil.init(this); 
		testUtil.setHardWait(1500);
		WebElement radioButton = driver.findElement(By.xpath("//input[@id='"+radioButtonOption+"']")); 
		testUtil.clickElementJavascript(radioButton);
		
		
	}
	
	public void enterRateQuoteResponseMessage(String message) {
		logger.info("Enter Rate Quote Response Message"); 
		testUtil.init(this); 
		testUtil.setHardWait(2500);
		rateQuoteResponseMessage.sendKeys(message);
	}
	
	public void clickAddButton() {
		logger.info("Click Add Button"); 
		testUtil.init(this); 
		testUtil.setHardWait(1000);		
		testUtil.setExplicitWait(addButton, 60);
		testUtil.highlightElementBeforeClickAction(addButton);
		testUtil.clickElementJavascript(addButton);
		
//		WebElement warningMsg=driver.findElement(By.xpath("//*[@id=\"ui-id-7\"][contains(text(),'Restriction Warning')]"));
//		if(warningMsg.isDisplayed()) {
//			driver.findElement(By.xpath("//button[1]/span[contains(text(),'Yes')]")).click();
//		}else {
//			System.out.println("No warning is displayed");
//		}
	}

	
	public void successMessageIsVisible() {
		logger.info("Validate Success Message Is Visible At Top Of Screen"); 
		testUtil.init(this);
		assertTrue(successMessage.isDisplayed());
	}
	
	public void clickOnRowCountDropDown() {
		testUtil.init(this);
		logger.info("Click On Row Count Dropdown"); 
		testUtil.setHardWait(1000);
//		Actions action = new Actions(driver); 
//		action.moveToElement(rowCountDropDownButton).click(); 
//		WebElement dropDownNumber500 = driver.findElement(By.xpath("//option[@value='500']")); 
//		action.moveToElement(dropDownNumber500);
//		testUtil.clickElementJavascript(dropDownNumber500);
//		WebElement dropDownNumber500 = driver.findElement(By.xpath("//option[@value='500']"));
		WebElement dropDownNumber500 = driver.findElement(By.xpath("//*[@id='terminalContainer']/div/div[3]/div[1]/span[3]/select"));
		Select select = new Select(dropDownNumber500);
		select.selectByValue("500");
		
	}
	
	public void clickEditButton(String eventName) {
		testUtil.init(this); 
		logger.info("Click Edit Button from specific Event in Restrictions Table"); 

		/**
		 * WebElement
		 * pageArrow=driver.findElement(By.xpath("//*[@id='terminalContainer']/div/div[3]/div[1]/span[1]/span[4]"));
		 * testUtil.clickElementJavascript(pageArrow); testUtil.setHardWait(2000);
		 * WebElement specifcEventToUpdate =
		 * driver.findElement(By.xpath("//table[@class='jtable']/tbody/tr/td[contains(text(),
		 * '"+eventName+""+' '+"')]/../td[8]/input[@id='ctEditButton']"));
		 * testUtil.clickElementJavascript(specifcEventToUpdate);
		 * specifcEventToUpdate.click();
		 */
		logger.info("Searching envent name " + eventName + ".....");
		List<WebElement>row=driver.findElements(By.xpath("//table[@class='jtable']/tbody/tr[*]"));
		
		//String str= row.get(0).getText();
		//System.out.println("The Web Elements are " + str );
		
		for(int i=0; i< row.size(); i++) {


			int j=i+1;

			String columnName= driver.findElement(By.xpath("//table[@class='jtable']/tbody/tr[ " + j + "]/td[1]")).getText();


			if(columnName.equals(eventName)) {
		
				WebElement editBtn=driver.findElement(By.xpath("//table[@class='jtable']/tbody/tr/td[8]//input[@id='ctEditButton']"));


				logger.info("Clicking on the edit button");
				testUtil.setHardWait(1000);
				
				testUtil.clickElementJavascript(editBtn);
				
				System.out.println("Event is found");
				
				Assert.assertEquals(columnName, eventName);
				break;
			}
		}
	}

	
	
	public void selectAllZipCodes() {
		logger.info("Select all zip codes"); 
		testUtil.init(this);
		testUtil.setHardWait(500);
		Actions action = new Actions(driver); 
		action.click(zipCodeDropDown); 
		WebElement selectAllTab = driver.findElement(By.xpath("//span[contains(text(), 'Select All')]")); 
		action.moveToElement(selectAllTab).click(); 
	}
	
	public void clickUpdateButton() {
		logger.info("Click Update Button"); 
		testUtil.init(this);
		testUtil.setHardWait(1000);
		testUtil.clickElementJavascript(updateButton);
		testUtil.setHardWait(1000);
		
//		WebElement warningMsg=driver.findElement(By.xpath("//*[@id=\"ui-id-7\"][contains(text(),'Restriction Warning')]"));
//		
//		if(warningMsg.isDisplayed()) {
//			WebElement yesBtn=driver.findElement(By.xpath("//button[1]/span[contains(text(),'Yes')]"));
//			testUtil.clickElementJavascript(yesBtn);
//		}else {
//			System.out.println("No warning is displayed");
//		}
	}

	public void validateUpdateMessage() {
		logger.info("Validating update message is visible."); 
		testUtil.init(this);
		testUtil.setHardWait(2000);
		
		//This is to make sure that if there is a rate quote w/ the same name will bypass the restriction message. 
		
		if (!driver.findElements(By.xpath("//span[text() = 'Restriction Warning']")).isEmpty()) {
			
			driver.findElement(By.xpath("//span[text()='Yes']")).click();
		} else {

		Assert.assertTrue(validateUpdateMessage.isDisplayed());
		
		clickYesButton();
		
		}
	}

	
	public void clickYesButton() {
		logger.info("Click Yes Button"); 
		testUtil.init(this);
		testUtil.setHardWait(500);
		clickYesButton.click();
	}
	
	public void clickDeleteButton(String eventName) {
		logger.info("Click Edit Button from specific Event in Restrictions Table"); 
		testUtil.init(this); 
		testUtil.setHardWait(1500);
		WebElement pageArrow=driver.findElement(By.xpath("//*[@id='terminalContainer']/div/div[3]/div[1]/span[1]/span[4]"));
		testUtil.clickElementJavascript(pageArrow);
		testUtil.setHardWait(2000);
		WebElement specificElementToDelete = driver.findElement(By.xpath("//table[@class='jtable']/tbody/tr/td[contains(text(), '"+eventName+""+' '+"')]/../td[8]/input[@id='ctDeleteButton']")); 
		testUtil.clickElementJavascript(specificElementToDelete);
		//specificElementToDelete.click(); 
	}

	public void validateDeleteMessage(String eventName, String effectiveDate, String expireDate) {
		logger.info("Validating delete message is visible."); 
		testUtil.init(this);
		testUtil.setHardWait(1500);
		WebElement terminal = (driver.findElement(By.xpath("//table[@class='jtable']/tbody/tr/td[contains(text(), '"+eventName+"')]/../td[2]"))); 
		System.out.println(("//div[@id='dialog-confirm']/p/span[contains(text(), 'Are you sure that you want to delete Restriction: Terminal: "+terminal.getText()+" with Dates: "+effectiveDate+" to "+expireDate+" ?')]"));  
		WebElement validateDeleteMessage = driver.findElement(By.xpath("//div[@id='dialog-confirm']/p/span[contains(text(), 'Are you sure that you want to delete Restriction: Terminal: "+terminal.getText()+" with Dates: "+effectiveDate+" to "+expireDate+" ?')]"));
		Assert.assertTrue(validateDeleteMessage.isDisplayed()); 
	}
	
	public void clickLogOut() {
		logger.info("Click On LogOut Button"); 
		testUtil.init(this);
		testUtil.setHardWait(500);
		testUtil.clickElementJavascript(logoutButton);
		
	}

	public void bypassIfRestrictionMessage() {
		logger.info("Click Yes if a restriction message pops up because of an already existing quote stored");
		try{
			if (!driver.findElements(By.xpath("//span[text() = 'Restriction Warning']")).isEmpty()) {
				WebElement ele= driver.findElement(By.xpath("//span[text()='Yes']"));
				testUtil.clickElementJavascript(ele);
			}
		}catch(NoSuchElementException e) {
			logger.info("The pop up for clicking yes to continue is not visible."); 

		}

		
	}


}
