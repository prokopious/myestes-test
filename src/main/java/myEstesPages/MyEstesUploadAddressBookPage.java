package myEstesPages;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesUploadAddressBookPage extends TestBase {
	
	private Logger logger = Logger.getLogger(MyEstesUploadAddressBookPage.class);
	
	// 
	// Add address(es) to your current Address Book (append)
	@FindBy(how = How.XPATH, using = "//*[@id='mat-radio-20']/label/div[1]/div[1]")
	private WebElement weAddAddressToYCurAddBook;
	// Browse
	@FindBy(how = How.XPATH, using = "//input[@id='addressFile']")
	private WebElement weBrowseButton;
	// Download Template
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Download Template')]")
	private WebElement weDownloadTemplate;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Upload')]")
	private WebElement upload; 
	
	/**************************************METHODS************************************/
	
	// Select Add address(es) to your current Address Book (append) Radio Button
	public void selectAddAddrToYCurAddBookRadioButton () {	
		logger.info("Select Add address(es) to your current Address Book (append) Radio Button");
		testUtil.init(this);
		testUtil.setExplicitWait(weAddAddressToYCurAddBook, 60);
		testUtil.clickElementJavascript(weAddAddressToYCurAddBook);
			
	}

    // Click On Browse

	 public void clickOnBrowseButton (String filePath) {
         logger.info("Click on Browse Button");
         testUtil.init(this);
         testUtil.setHardWait(1000);      
        // Actions actions = new Actions(driver);
        // WebElement elementLocator = driver.findElement(By.id("addressFile"));
        // actions.doubleClick(elementLocator).perform(); 
         
         driver.findElement(By.id("addressFile")).sendKeys(filePath);
  
  } 


	// Click On Download Template
	public void clickOnDownloadTemplate () {
		logger.info("Click on Download Template");
		testUtil.init(this);
		weDownloadTemplate.click();		
	}
	
	public void validateTheCompanyNameDisplayed(String cmpnyName) {
	    logger.info("Validate newly added address is added to the address box");
	    testUtil.init(this);
	    
	    List<WebElement> cmp = driver.findElements(By.xpath(
	                  "//*[@id=\"main\"]/app-address-book/mat-card/mat-card-content/div[2]/mat-table/mat-row[*]/mat-cell[1]/a"));


	    for (int i = 0; i < cmp.size(); i++) {

	           String companyNme = cmp.get(i).getText();
	          

	           if (companyNme.contains(cmpnyName)) {

	                 System.out.println("The company name is displayed in the address book : " + cmpnyName);
	                 Assert.assertTrue(true);
	                 break;
	           } else {
	                 System.out.println("Searching......");
	           }
	           
	    }
	}


	public void clickOnDeleteButton(String companyNme) throws InterruptedException {
	       logger.info("Delete the added address : " + companyNme);
	       testUtil.setHardWait(1000);

		WebElement ele = driver.findElement(By.xpath("//mat-row[1]//mat-cell[9]//button[2]//span[1]//mat-icon[1]"));
		testUtil.setExplicitWait(ele, 60);
		testUtil.clickElementJavascript(ele);
	   
	       //  driver.findElement(By.xpath("//mat-row[1]//mat-cell[9]//button[2]//span[1]//mat-icon[1]")).click();
	       testUtil.setHardWait(1000);
	       
	       driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
	    testUtil.setHardWait(1000);
	}

	public void deleteFilesFromFolder(String folderPath,String fileName) {
	       
	       logger.info("deleting a file: Name - "+fileName+" from path : "+folderPath);
	       
	       driver.switchTo().defaultContent();
	    File dir = new File(folderPath);
	    File[] dir_contents = dir.listFiles();
	           
	    if(dir_contents.length > 0) {
	       System.out.println("Files count "+dir_contents.length);
	           for (int i = 0; i < dir_contents.length; i++) {
	              if(dir_contents[i].getName().contains(fileName)) {
	                     dir_contents[i].delete();
	                     break;
	              }
	           }
	         }
	}

	public boolean verifyIsFileDownloaded(String downloadPath,String fileName) {
	              
	              logger.info("verify report download");
	              /*String downloadPath="C:/Users/walika/Downloads";
	              String fileName="missionTrailerReport-HURRICANE APRILmissionTrailerReport-HURRICANE APRIL.xls";*/
	              boolean flag = false;
	              String fileFound = "";
	           File dir = new File(downloadPath);
	           File[] dir_contents = dir.listFiles();
	                  
	           if(dir_contents.length > 0) {
	              System.out.println("Files count "+dir_contents.length);
	                  for (int i = 0; i < dir_contents.length; i++) {
	                     if(dir_contents[i].getName().contains(fileName)) {
	                            fileFound = dir_contents[i].getName();
	                            flag=true;
	                            break;
	                     }
	                  }
	                  System.out.println("File Found :"+fileFound+"\n"+"File we look for : "+fileName);
	                     assertTrue(fileFound.contains(fileName), "Downloaded file name is not matching with expected file name");  
	           }else if (dir_contents == null || dir_contents.length == 0) {
	              System.out.println("Files count "+dir_contents.length);
	               flag = false;
	               assertTrue(false, "No files are downloaded");
	           }
	           return flag;
	       }
	       
	       
	public boolean verifyDownloadedFileName(String dirPath,String fileName){
	              
	              logger.info("verify file name exist");
	              /*String dirPath="C:/Users/walika/Downloads";
	              String fileName="missionTrailerReport-HURRICANE APRILmissionTrailerReport-HURRICANE APRIL.xls";*/
	              boolean flag=false;
	              String fileFound = "";
	           File dir = new File(dirPath);
	           File[] files = dir.listFiles();
	           if(files.length > 0) {
	              System.out.println("Files count "+files.length);
	                  for (int i = 0; i < files.length; i++) {
	                     
	                     if(files[i].getName().contains(fileName)) {
	                            fileFound = files[i].getName();
	                            flag=true;
	                            break;
	                     }
	                  }
	                  System.out.println("File Found :"+fileFound+"\n"+"File we look for : "+fileName);
	                     assertTrue(fileFound.contains(fileName), "Downloaded file name is not matching with expected file name");  
	           } else if (files == null || files.length == 0) {
	              System.out.println("Files count "+files.length);
	               flag = false;
	               assertTrue(false, "No files are downloaded");
	           }
	           
	           return flag;
	       }


		// Click On upload button
		public void clickOnUploadButton () {
			logger.info("Click on upload button");
			testUtil.init(this);
			testUtil.setExplicitWait(upload, 120);
			testUtil.clickElementJavascript(upload);	
			
		} 
			

}
