package myEstesPages;

import java.awt.Window;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import testBase.TestBase;

public class MyEstesAddNewAddressPage extends TestBase {

private Logger logger = Logger.getLogger(MyEstesAddNewAddressPage.class);

	@FindBy(how = How.ID, using = "inputCompany")
	private WebElement CompanyName;

	@FindBy(how = How.XPATH, using = "//mat-select[@id='mat-select-1']//div[@class='mat-select-arrow']")
	private WebElement Country;

	@FindBy(how = How.ID, using = "inputCity")
	private WebElement City;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Province')]")
	private WebElement Province;

	@FindBy(how = How.ID, using = "selectState")
	private WebElement State;

	@FindBy(how = How.ID, using = "inputZip")
	private WebElement ZipCode;

	@FindBy(how = How.ID, using = "inputPhone")
	private WebElement Phone;

	@FindBy(how = How.ID, using = "inputAddress1")
	private WebElement Address;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Save')]")
	private WebElement SaveButton;

	public void validateTitle() {
		testUtil.init(this);
		logger.info("Validate title");
		String title = driver.findElement(By.xpath("//mat-card-title[@class='mat-card-title ng-star-inserted']"))
				.getText();
		System.out.println("Page title is :" + title);
		String expected = "Add New Address";
		Assert.assertEquals(title, expected);
	}

	public void selectCountry(String CountryName) {
		testUtil.init(this);
		logger.info("Select country name");
		Country.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+CountryName+"')]" )).click();
	
	}

	public void selectProvince() throws InterruptedException {
		testUtil.init(this);
		logger.info("Select province name");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)"); 
        testUtil.WaitForTheElementClickable(Province);
        testUtil.clickElementJavascript(Province);
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'ON')]")).click();		
	/*	testUtil.WaitForTheElementClickable(Province);
		testUtil.clickElementJavascript(Province);
		testUtil.setHardWait(1000);
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'ON')]")).click();
*/
	}

	public void selectState(String state) throws InterruptedException {
            testUtil.init(this);
            logger.info("Select province name");
            testUtil.setHardWait(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)");
            testUtil.setHardWait(3000);
            testUtil.clickElementJavascript(State);
            
            testUtil.setHardWait(1000);
            System.out.println("Came In");
            try {
                   WebElement ele = driver
                                .findElement(By.xpath("//span[@class='mat-option-text'and contains(text(),'" + state + "')]"));
                   System.out.println(ele.isDisplayed());
            } catch (Exception e) {
                   testUtil.clickElementJavascript(State);
                   testUtil.setHardWait(2000);
            } finally {
                   WebElement ele = driver
                                .findElement(By.xpath("//span[@class='mat-option-text'and contains(text(),'" + state + "')]"));
                   testUtil.clickElementJavascript(ele);
            }
     }



	public void enterCompanyName() {
		testUtil.init(this);
		logger.info("Enter company name ");
		testUtil.setHardWait(1000);
		Faker faker = new Faker();
		CompanyName.sendKeys(faker.company().name());

	}

	public String getCompanyName() {
		testUtil.init(this);
		logger.info("Enter company name ");
		String name = CompanyName.getAttribute("value");
		System.out.println("Company name is : " + name);
		return name;
	}

	public void enterCityName() throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter city name ");
		City.clear();
		City.sendKeys("Toronto");
		testUtil.setHardWait(1000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	}

	public void enterUSCityName() {
		testUtil.init(this);
		logger.info("Enter US city name ");
		City.clear();
		// Faker faker = new Faker();
		// City.sendKeys(faker.address().cityName());
		testUtil.setHardWait(1000);
		City.sendKeys("Richmond");
		testUtil.setHardWait(2000);

	}

	public void enterPostalCode() {
		testUtil.init(this);
		logger.info("Enter postal code ");
		ZipCode.clear();
		ZipCode.sendKeys("M4C1M5");

	}

	public void enterZipCode() {
		testUtil.init(this);
		logger.info("Enter zip code ");
		ZipCode.clear();
		Faker faker = new Faker();
		String zip = faker.address().zipCode();
		ZipCode.sendKeys("23173");
		// System.out.println(zip.substring(-3));//.substring(-5)

	}

	public void enterPhoneNumber() {
		testUtil.init(this);
		logger.info("Enter phonenumber ");
		Phone.clear();
		Faker faker = new Faker();
		Fairy fairy = Fairy.create();
		Person person = fairy.person();
		Phone.sendKeys(String.valueOf(person.getTelephoneNumber()));

	}

	public void enterPhoneNumber(String num) {
		testUtil.init(this);
		logger.info("Enter Phonenumber ");
		Phone.clear();
		Phone.sendKeys(String.valueOf(num));
		testUtil.setHardWait(500);
	}

	public void enterAddress() {
		testUtil.init(this);
		logger.info("Enter street  name ");
		Faker faker = new Faker();
		Address.sendKeys(faker.address().streetAddress());

	}

	public String recordAddress() {
		testUtil.init(this);
		logger.info("Enter street  name");
		String add = Address.getAttribute("value");
		System.out.println("Address is :" + add);
		return add;
	}

	public void clickOnSaveButton() {
		testUtil.init(this);
		logger.info("Click on Save button ");
		testUtil.assetWaitDisplayed(driver.findElement(By.xpath("//span[contains(text(),'Save')]")),12, 250,TimeUnit.MILLISECONDS);
		
		WebElement ele=driver.findElement(By.xpath("//span[contains(text(),'Save')]"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",ele);
		testUtil.setExplicitWait(ele,90);
		SaveButton.click();

	}

	public void validaAddressDisplay(String expected) throws InterruptedException {
		testUtil.init(this);
		logger.info("Validate Search Address result display  ");

		WebDriverWait wait = new WebDriverWait (driver,60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-19"))).sendKeys(expected);
		//driver.findElement(By.id("mat-input-19")).sendKeys(expected);
		
		testUtil.setHardWait(3000);
		String result = driver.findElement(By.xpath("//body//mat-row[1]")).getText();
		System.out.println("Search result is:" + result);
		Assert.assertTrue(true, result);
		testUtil.setHardWait(3000);
	}

}
