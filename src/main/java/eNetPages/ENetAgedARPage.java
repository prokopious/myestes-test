package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class ENetAgedARPage extends TestBase{
	
	private Logger logger=Logger.getLogger(ENetAgedARPage.class);
	
	@FindBy(how = How.ID, using = "accountNumber")
	private WebElement eAccField;
	
	@FindBy(how = How.ID, using = "comment")
	private WebElement eCommField;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Add']")
	private WebElement eAdd;
    @FindBy(how = How.ID, using = "accountNumberFilter")
    private WebElement accNumFilter;
    
    @FindBy(how = How.ID, using = "filterButton")
    private WebElement filterBtn;

    @FindBy(how = How.XPATH, using = "//*[@class='success']")
    private WebElement successMsg;
	
	public void enterAccountNumber(String aNum) {
		testUtil.init(this);
		logger.info("enter Account Number");
		testUtil.setHardWait(2000);
		eAccField.sendKeys(aNum);
	}
	
	public void typecomments(String comments) {
		testUtil.init(this);
		logger.info("type comments");
		eCommField.sendKeys(comments);
	}
	
	public void clickOnAddButton() {
		testUtil.init(this);
		logger.info("Click on Add button");
		eAdd.click();
	}

	public void deleteAccountNumberIfExistInTheTable() throws Exception {
		logger.info("Delete account number if it  already exists in the table");
		testUtil.init(this);
		
		/*WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);*/
		List<WebElement> we = driver.findElements(By.xpath("//*[@id=\"agedARTable\"]/tbody/tr[*]/td[1]"));

		int listSize = we.size();
		System.out.println("Element size : " + listSize);
		for (int i = 0; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);
			System.out.println(i);

			if (eleName.equals("5023958")) {
				int j = i+2;
				driver.findElement(By.xpath("//*[@id=\"agedARTable\"]/tbody/tr[" + j + "]/td[4]/a/img")).click();

				break;
			}

		}
		Thread.sleep(500);
		String popup=driver.switchTo().alert().getText();
		System.out.println("Popup message is: " + popup);
		driver.switchTo().alert().accept();
	}
	
    public void filterAccount() {
        testUtil.init(this);
        logger.info("click On Add Button");
        WebElement frame = driver.findElement(By.xpath("//iframe[@id='mainpage']"));
        driver.switchTo().frame(frame);
        accNumFilter.sendKeys("5023958");
        filterBtn.click();
        testUtil.setHardWait(2000);
    }

    public void verifyAcctAddedSuccessMsg(String acctNum) {
    	logger.info("Verify Account Added message");
    	testUtil.init(this);
        System.out.println(successMsg.getText());
        Assert.assertTrue(successMsg.getText().contains(acctNum+" added successfully"));

    }
    
    public void removeAccountNumber(String acctNum) {
    	logger.info("Remove account number");
    	testUtil.init(this);
    	List<WebElement> agedArTable = driver.findElements(By.xpath("//*[@id='agedARTable']//tr[*]//td[1]"));
    	
    	for(int i = 2; i < agedArTable.size(); i++) {
    		String actualAccNo = driver.findElement(By.xpath("//*[@id='agedARTable']//tr["+i+"]//td[1]")).getText();
    		
    		if(actualAccNo.equals(acctNum)) {
    			driver.findElement(By.xpath("//*[@id='agedARTable']//tr["+i+"]//td[4]//img")).click();
    			testUtil.setHardWait(2000);
    			driver.switchTo().alert().accept();
    			testUtil.setHardWait(2000);
    		}
    	}
    }

    public void verifyAcctRemovedSuccessMsg(String acctNum) {
    	logger.info("Verify Account Added message");
    	testUtil.init(this);
        System.out.println(successMsg.getText());
        Assert.assertTrue(successMsg.getText().contains(acctNum+" removed successfully"));
    }
    
    public void verifyAcctAddedToTheTable(String acct) {
    	logger.info("Verify account# - "+acct+ " added to the table");
    	
    	testUtil.init(this);
    	List<WebElement> agedArTable = driver.findElements(By.xpath("//*[@id='agedARTable']//tr[*]//td[1]"));
    	boolean value = false;
    	
    	for(int i = 2; i < agedArTable.size(); i++) {
    		String actualAccNo = driver.findElement(By.xpath("//*[@id='agedARTable']//tr["+i+"]//td[1]")).getText();
    		
    		if(actualAccNo.equals(acct)) {
    			System.out.println("Account added to the table succesfully");
    			value = true;
    			break;
    		}
    	}
    	
    	Assert.assertTrue(value);
    }
}
