package myEstesPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testBase.TestBase;

public class MyEstesAddressBookPage extends TestBase {

	private Logger logger = Logger.getLogger(MyEstesAddressBookPage.class);

	// Add Button
	@FindBy(how = How.ID, using = "add")
	private WebElement weClickOnAddButton;

	@FindBy(how = How.ID, using = "company")
	private WebElement companyNameField;

	@FindBy(how = How.ID, using = "locationNumber")
	private WebElement LocationNumberField;

	@FindBy(how = How.ID, using = "streetAddress")
	private WebElement StreetAddressField;

	@FindBy(how = How.ID, using = "state")
	private WebElement StateField;

	@FindBy(how = How.ID, using = "city")
	private WebElement CityField;

	@FindBy(how = How.ID, using = "zip")
	private WebElement ZipField;

	// Address Book Text
	@FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div/div[1]/app-address-book/h1/span")
	private WebElement weAddressBookText;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Address Book')]")
	private WebElement AddressBook;

	@FindBy(how = How.XPATH, using = "//a[@id='my-estes-dropdown']")
	private WebElement MyEstes;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Address']")
	private WebElement EditIcon;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Clear')]")
	private WebElement Clear;

	@FindBy(how = How.XPATH, using = "//button[@title='Delete Address']")
	private WebElement DeleteIcon;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete')]")
	private WebElement DeleteButton;

	@FindBy(how = How.ID, using = "mat-input-19")
	private WebElement SearchField;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add New Address')]")
	private WebElement AddNewAddress;

	@FindBy(how = How.XPATH, using = "//*[@class='fal fa-upload']")
	private WebElement weUpload;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Advanced Search')]")
	private WebElement AdvanceSearch;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	private WebElement Search;

	
	// Click On Add Button
	public void clickOnAddButton() {
		testUtil.init(this);
		logger.info("Click on Add button");

		weClickOnAddButton.click();
	}

	public void clickOnClearButton() {
		testUtil.init(this);
		logger.info("Click on Clear button");

		Clear.click();
	}

	public void clickOnSearchButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click On Search button");

		Search.click();
		Thread.sleep(500);
	}

	public void clickOnAdvanceSearchButton() throws InterruptedException {
		testUtil.init(this);
		logger.info("Click on Advance Search button");
		Thread.sleep(1000);
		testUtil.clickElementJavascript(AdvanceSearch);
		// AdvanceSearch.click();
	}

	// Address Book Displayed
	public void addressBookTextDisplayed() {
		logger.info("Address book is displayed");
		testUtil.init(this);
		weAddressBookText.isDisplayed();
	}

	public void clickOnAddressBookLink() {
		testUtil.init(this);
		AddressBook.click();
		logger.info("Click on Address Book link");
	}

	public void clickOnEditIcon() {
		logger.info("Click on Edit Icon ");
		testUtil.init(this);
		testUtil.setExplicitWait(EditIcon, 120);
		EditIcon.click();
		
	}

	public void clickOnAddNewAddressButton() {
		logger.info("Click on Add New Address button ");
		testUtil.init(this);
		AddNewAddress.click();
		testUtil.setHardWait(1000);
		
	}

	public void clickOnDeleteIcon() {
		testUtil.init(this);
		DeleteIcon.click();
		logger.info("Click on Delete Icon ");
	}

	public void enterValueToAdvancedSearchField(String value) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter value to the Advanced Search field ");
		
		testUtil.assetWait(null, SearchField, 13, 250, TimeUnit.MILLISECONDS);
		SearchField.clear();
		testUtil.setHardWait(1000);
		SearchField.sendKeys(value);
		

	}

	public void enterValueToSearchField(String value) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter value to the Search field ");
		driver.findElement(By.id("mat-input-6")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("mat-input-6")).sendKeys(value);

	}

	public void clickOnDeleteButton() {
		testUtil.init(this);
		DeleteButton.click();
		logger.info("Click on Delete button ");
	}

	public void enterCompanyName(String name) {
		testUtil.init(this);
		logger.info("Enter Company name ");
		driver.findElement(By.xpath("//button[@class='btn btn-default ml-3']")).click();
		companyNameField.sendKeys(name);

	}

	public void enterLocationNumber(String num) {
		testUtil.init(this);
		logger.info("Enter Location Number ");
		driver.findElement(By.xpath("//button[@class='btn btn-default ml-3']")).click();
		LocationNumberField.sendKeys(num);

	}

	public void enterStreetAddress(String add) {
		testUtil.init(this);
		logger.info("Enter Steet address ");
		driver.findElement(By.xpath("//button[@class='btn btn-default ml-3']")).click();
		StreetAddressField.sendKeys(add);

	}

	public void enterCity(String city) throws InterruptedException {
		testUtil.init(this);
		logger.info("Enter City ");
		//CityField.clear();
		driver.findElement(By.xpath("//button[@class='btn btn-default ml-3']")).click();
		CityField.sendKeys(city);
		Thread.sleep(500);
	}

	public void enterState(String state) {
		testUtil.init(this);
		logger.info("Enter State ");
		driver.findElement(By.xpath("//button[@class='btn btn-default ml-3']")).click();
		StateField.sendKeys(state);

	}

	public void enterZipCode(String zip) {
		testUtil.init(this);
		logger.info("Enter zip code ");
		ZipField.clear();
		driver.findElement(By.xpath("//button[@class='btn btn-default ml-3']")).click();
		testUtil.setHardWait(500);
		ZipField.sendKeys(zip);

	}

	public String recordCompanyName() {
		testUtil.init(this);
		logger.info("Record company name ");
		testUtil.setHardWait(2000);
		String name = driver.findElement(By.xpath("//mat-row[1]//mat-cell[1]//a[1]")).getText();
		System.out.println("company name is : " + name);

		return name;
	}

	public String recordLocationNumber() {
		testUtil.init(this);
		logger.info("Record location number ");
		String num = driver.findElement(By.xpath(
				"(//mat-cell[@class='d-none d-md-flex mat-cell cdk-column-locationNumber mat-column-locationNumber ng-tns-c2-1 ng-star-inserted'])[1]"))
				.getText();
		System.out.println("location number is : " + num);

		return num;
	}

	public String recordStreetAddress() {
		testUtil.init(this);
		logger.info("Record street address ");

		String add = driver.findElement(By.xpath("(//div[@class='ng-star-inserted'])[1]")).getText();
		System.out.println("street address is : " + add);

		return add;
	}

	public String recordCityName() {
		testUtil.init(this);
		logger.info("Record city name ");
		testUtil.assetWait("//span[@class='ng-star-inserted'])[1]", null, 10, 200, TimeUnit.MILLISECONDS);
		String address = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[1]")).getText();
		String[] addressArray = address.split(",");
		return addressArray[0];
	}



	public String recordStateName() {
		testUtil.init(this);
		logger.info("Record state name ");
		testUtil.assetWait("//*[@id='main']/app-address-book/mat-card/mat-card-content/div[2]/mat-table/mat-row/mat-cell[2]/lib-address/div[2]/span[1]", null, 10, 200, TimeUnit.MILLISECONDS);
		String address = driver.findElement(By.xpath("//*[@id='main']/app-address-book/mat-card/mat-card-content/div[2]/mat-table/mat-row/mat-cell[2]/lib-address/div[2]/span[1]")).getText();
		String[] addressArray = address.split(",");
			System.out.println(address); 
		return addressArray[1];
	}
	
	public String recordZipCode() {
		testUtil.init(this);
		logger.info("record zip code ");

		//String zip = driver
			//	.findElement(By.xpath("//mat-row[1]//mat-cell[2]//lib-address[1]//div[2]//span[2]"))
			//	.getText();
		String zip = driver
			.findElement(By.xpath("//*[@id='main']/app-address-book/mat-card/mat-card-content/div [2]/mat-table/mat-row/mat-cell[2]/lib-address/div[2]/span[2]"))
			.getText();
		
		System.out.println("zip code is : " + zip);

		return zip;
	}

	public void validateTitle() {
		testUtil.init(this);
		logger.info("Validate title");
		
		WebElement ele =driver
				.findElement(By.xpath(
						"//h3[@class='d-flex flex-column justify-content-center'][contains(.,'Delete Address?')]"));
		testUtil.setExplicitWait(ele, 60);
			String title=ele.getText();
		
		testUtil.setHardWait(2000);
		System.out.println(title);
		String expected = "Delete Address?";
		Assert.assertEquals(title, expected);
	}

    
    public void selectRecordFromAddressBookPage() {
     testUtil.init(this);
     logger.info("select record from address book page");
     testUtil.setHardWait(2000);
  //   driver.findElement(By.xpath("//a[text()='test']")).click(); 
     driver.findElement(By.xpath("//mat-table/mat-row/mat-cell[1]/a")).click();
}


	public void validateAddressBookPageTitle() {
		testUtil.init(this);
		logger.info("validate Address Book Page title");
		String title = driver.findElement(By.xpath("//h3[contains(text(), 'Address Book')]")).getText();
		testUtil.setHardWait(1000);
		System.out.println("page title is :" + title);
		String expected = "Address Book";
		Assert.assertEquals(title, expected);
	}

	public void validateAddressDisplay(String expected1) {
		testUtil.init(this);
		logger.info("validate Search Address result display  ");

		for(int i=0; i<=2;i ++) {
			try {
			String result = driver.findElement(By.xpath("(//*[@role='gridcell'])[2]")).getText();
			System.out.println("Search result is:" + result);
			Assert.assertTrue(result.contains(expected1));	
			break;
		}
		catch(Exception e) {
			System.out.println("Timeout exception occured");
		}
	}
	}

	public void validateContactNameDisplay(String expected1) {
		testUtil.init(this);
		logger.info("validate contact NAME display  ");

		String result = driver.findElement(By.xpath("(//*[@role='gridcell'])[7]")).getText();
		System.out.println("Search result is:" + result);
		Assert.assertEquals(result, expected1);
	}

	public void validateLocationDisplay(String expected1) {
		testUtil.init(this);
		logger.info("validate location# display  ");
		String result = driver.findElement(By.xpath("(//*[@role='gridcell'])[8]")).getText();
		testUtil.setHardWait(2000);
		System.out.println("Search result is:" + result);
		Assert.assertEquals(result, expected1);
	}

	public void validateNewAddressDisplay(String expected1) throws InterruptedException {
		testUtil.init(this);
		logger.info("validate Search Address result display  ");
		testUtil.verifyAndPrintWebTableData("//div[@class='table-container']");
		
	}

	public void validateCompanyNameDisplay(String expected1) throws InterruptedException {
		testUtil.init(this);
		logger.info("Validate Commany Name is displayed  ");
		// driver.findElement(By.id("mat-input-19")).sendKeys(expected1);
		testUtil.assetWaitDisplayed(driver.findElement(By.xpath("//*[@role='gridcell'][1]")), 10, 250, TimeUnit.MILLISECONDS);
		String result = driver.findElement(By.xpath("//*[@role='gridcell'][1]")).getText();
		testUtil.setHardWait(1000);
		System.out.println("Search result is:" + result);
		Assert.assertEquals(result, expected1);
	}


		
		public void validateInputFieldsDisplay()
				throws InterruptedException {
			logger.info("validate Input fields display  ");
			testUtil.init(this);
			//String fields=driver.findElement(By.xpath("//div[@class='mat-form-field-infix']//input")).getAttribute("name");
			//System.out.println(fields);
			testUtil.verifyAndPrintWebTableData("//div[@class='mat-form-field-infix']//input");
			
		/*
		 * driver.findElement(By.id("mat-input-6")).sendKeys(expected1);
		 * Thread.sleep(1000); String result= driver.findElement(By.
		 * xpath("//mat-cell[@class='d-none d-md-flex mat-cell cdk-column-company mat-column-company ng-tns-c2-1 ng-star-inserted']"
		 * )).getText(); System.out.println("Search result is:" +result); //String
		 * expected1= "Test Company"; Assert.assertTrue(result.contains(expected1));
		 */
	}

	// Click On Upload Button
	public void clickOnUploadButton() {
		testUtil.init(this);
		logger.info("Click On Address Book Upload Button");
		weUpload.click();
	}
	
	public void selectSecondRecordFromAddressBookPage() {
		testUtil.init(this);
		logger.info("select Second record from address book page");

        driver.findElement(By.xpath("//mat-table/mat-row[2]/mat-cell[1]/a")).click();  
	} 

	
    
public boolean isDisplyed() {
	testUtil.init(this);
	logger.info("Company Nam not displayed");
	
	try {
		
		driver.findElement(By.xpath("//mat-row[1]//mat-cell[1]//a[1]"));
			return true;
		}catch(org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
}



