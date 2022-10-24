package eNetPages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class ENetUndeliveredFrieghtPage extends TestBase {
	
	 Logger logger = LogManager.getLogger(ENetUndeliveredFrieghtPage.class);
	 
	    @FindBy(how= How.LINK_TEXT, using = "Estes Northeast")
	    private WebElement estesNorthEast;
	    
	    @FindBy(how = How.LINK_TEXT,using = "Bob O'connor")
	    private WebElement manager;
	    
	    @FindBy(how=How.LINK_TEXT,using="112")
	    private WebElement terminal;
	    
	    @FindBy(how=How.LINK_TEXT,using = "Y")
	    private WebElement selectImage;
	    
	    @FindBy(how=How.CSS,using="button:nth-child(2) > img")
	    private WebElement selectPDF;
	    
	    @FindBy(how=How.CSS,using="button:nth-child(1) > img")
	    private WebElement selectExcel;

	    /******************************METHODS******************************/

	    public  void clickEstesNorthEast(){
	        logger.info("Click on  Estes NorthEast");
	        testUtil.init(this);
	        driver.switchTo().frame(0);
	        testUtil.setHardWait(1000);
	        testUtil.clickElementJavascript(estesNorthEast);
	      //  clickElement(estesNorthEast);
	    }
	    public void clickOnManager(){
	        logger.info("Click on a Manager");
	        testUtil.setHardWait(1000);
	        testUtil.clickElementJavascript(manager);
	       //clickElement(manager);
	    }
	    public void clickOnTerminal(){
	        logger.info("Click On Terminal");
	        clickElement(terminal);
	    }
	    public  void clickOnImageY(){
	        logger.info("Select  Image");
	        clickElement(selectImage);
	    }
	    public void clickOnPDF(){
	        logger.info("Select PDF");
	        clickElement(selectPDF);
	    }
	    public void clickOnExcel(){
	        logger.info("Click on Excel");
	        clickElement(selectExcel);
	    }
	    public void clickElement(WebElement element){
	        testUtil.setExplicitWait(element,60);
	        element.click();
	    }
	}



