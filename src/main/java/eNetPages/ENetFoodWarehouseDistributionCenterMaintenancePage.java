package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class ENetFoodWarehouseDistributionCenterMaintenancePage
		extends
			TestBase {

	private Logger logger = Logger.getLogger(ENetFoodWarehouseDistributionCenterMaintenancePage.class);

	@FindBy(how = How.ID, using = "addCompanyId")
	private WebElement eCompany;

	@FindBy(how = How.ID, using = "addCountry")
	private WebElement eCountry;

	@FindBy(how = How.ID, using = "addZipCode")
	private WebElement eZip;

	@FindBy(how = How.ID, using = "addRate")
	private WebElement eRate;

	@FindBy(how = How.ID, using = "addProhibited")
	private WebElement eProhibited;

	@FindBy(how = How.XPATH, using = "//*[@id=\"FoodWarehouseAddForm\"]/div/table/tbody/tr[2]/td[10]/input")
	private WebElement eAdd;

	public void selectCompany(String companyName) {
		testUtil.init(this);
		logger.info("select comapny name");
		testUtil.selectUsingVisibleText(eCompany, companyName);
		
	}

	public void selectCountry(String countryName) {
		testUtil.init(this);
		logger.info("select country name");
		testUtil.selectUsingVisibleText(eCountry, countryName);
	}

	public void entryZipCode(String zip) {
		testUtil.init(this);
		logger.info("enter Zip Code");
		eZip.clear();
		eZip.sendKeys(zip);
	}

	public void entryRate(String rate) {
		testUtil.init(this);
		logger.info("enter Rate(cwt)");
		eRate.clear();
		eRate.sendKeys(rate);
	}

	public void checkProhibited() {
		testUtil.init(this);
		logger.info("check Prohibited box");
		eProhibited.click();
	}

	public void clickOnAddButton() {
		testUtil.init(this);
		logger.info("click On Add Button");
		eAdd.click();
	}

	public void deleteCompanyNameIfExistInTheTable() throws Exception {
		logger.info("delete company name if it is already exist in the table");
		testUtil.init(this);
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mainpage']"));
		driver.switchTo().frame(frame);
		List<WebElement> we = driver.findElements(By.xpath("//*[@id=\"foodWarehouseTable\"]/tbody/tr[*]/td[1]"));

		int listSize = we.size();
		System.out.println("Element number : " + listSize);
		for (int i = 0; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);
			System.out.println(i);

			if (eleName.equals("Lowe's")) {
				int j = i + 2;
				driver.findElement(By.xpath("//*[@id=\"foodWarehouseTable\"]/tbody/tr[" + j + "]/td[11]/a/img")).click();

				break;
			}

		}
		Thread.sleep(500);
		String popup=driver.switchTo().alert().getText();
		System.out.println("Popup message is: " + popup);
		driver.switchTo().alert().accept();
	}

}
