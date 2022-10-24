package eNetPages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import com.github.javafaker.Faker;

import testBase.TestBase;


public class ENetTimeCriticalRateQouteHistoryPage extends TestBase{
	
  private Logger logger=Logger.getLogger(ENetTimeCriticalRateQouteHistoryPage.class);
	
	@FindBy(xpath = "//*[@id='app_intro_module']/h1")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[@id=\"vtl_qds_container\"]/div[1]/fieldset[1]/div/div/div[3]/div[2]")
	private WebElement accountNo;
	
	@FindBy(xpath = "//*[@id=\"vtl_qds_container\"]/div[1]/fieldset[1]/div/div/div[4]/div[2]")
	private WebElement role;
	
	@FindBy(xpath = "//*[@id=\"vtl_qds_container\"]/div[1]/fieldset[1]/div/div/div[5]/div[2]")
	private WebElement terms;
	
	@FindBy(xpath = "//*[@id=\"scheduling\"]/div/div[2]/div[1]")
	private WebElement pickUpDate;
	
	@FindBy(xpath = "//*[@id=\"shipment_details_contact_information\"]/div/div[2]/div[2]")
	private WebElement phoneNo;
	
	@FindBy(xpath = "//*[@id=\"logout\"]")
	private WebElement logout;
	
	@FindBy(xpath = "/html/body/form/table/tbody/tr[3]/td/input")
	private WebElement logoutConfirmation;
	
	public void verifyPage() {
		testUtil.init(this);
		logger.info("Verify the TimeCritical Quote History Page.");
		String pageTtl = pageTitle.getText();
		Assert.assertEquals(pageTtl, "Time-Critical Quote History","Page Title does not match.");
	}
	
	public String fetchAccountNo() {
		testUtil.init(this);
		logger.info("Fetch account number.");
		String account = accountNo.getText();
		String separator ="-";
	      int sepPos = account.indexOf(separator);
	      if (sepPos == -1) {
	         System.out.println("");
	      }
	      String accountNum = account.substring(sepPos + separator.length()).trim();
		logger.info("Account Number : "+accountNum);
		return accountNum;
	}
	
	public String fetchRole() {
		testUtil.init(this);
		logger.info("Fetch Role.");
		String roleName = role.getText();
		logger.info("Role : "+roleName);
		return roleName;
	}
	
	public String fetchTerms() {
		testUtil.init(this);
		logger.info("Fetch terms.");
		String term = terms.getText();
		logger.info("Terms : "+term);
		return term;
	}
	
	public String fetchpickUpDate() {
		testUtil.init(this);
		String pickUp = pickUpDate.getText();
		logger.info("Pick Up Date : "+pickUp);
		return pickUp;
	}
	
	public String fetchPhoneNumber() {
		testUtil.init(this);
		String phone = phoneNo.getText().toString().replaceAll("-", "");
		phone = phone.replace("x", "").trim();
		logger.info("Phone Number : "+phone);
		return phone;
	}
	
	public void clickLogout() {
		testUtil.init(this);
		logger.info("Click on Logout button.");
		driver.switchTo().defaultContent();
		logout.click();
	}
	
	public void clickLogoutConfirmation() {
		testUtil.init(this);
		logger.info("Click on Logout confirmation button.");
		driver.switchTo().frame("mainpage");
		logoutConfirmation.click();
	}

}
