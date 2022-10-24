package util;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.google.common.io.Files;
import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import freightBillPages.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import static org.awaitility.Awaitility.*;
import java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import testBase.TestBase;

public class TestUtil extends TestBase {

    private static Logger logger = Logger.getLogger(TestUtil.class);

    public static TestUtil testUtil;
    private static WebDriver driver;

    static Workbook book;
    static Sheet sheet;
    static String TESTDATA_SHEET_PATH = "src/main/resources/TestDataFolderTestData.xlsx";
    static Login login;

    /*
     * static LTL38MasterMenu ltl38MasterMenu; static FreightBillingMenu
     * freightBillingMenu; static FreightBillUpdateMainScreen
     * freightBillUpdateMainScreen; static FreightBillUpdateScreen1
     * freightBillUpdateScreen1; static FreightBillUpdateScreen2
     * freightBillUpdateScreen2; static FreightBillUpdateScreen3
     * freightBillUpdateScreen3; static ReserveFreightBills reserveFreightBills;
     */
    /*
     * private String uName; private String Pass;
     */

    public TestUtil(WebDriver _driver) {
        driver = _driver;

    }

    public TestUtil() {
    }

    public void init(Object page) {
        PageFactory.initElements(driver, page);
    }

    public WebDriver startChromeBrowser() {

        // System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,
        // "true");
        // System.setProperty("webdriver.chrome.driver",
        // "src/main/resources/drivers/chromedriver.exe");
        // Using ChromeManager which will be maintain in Gitlab instead of using locally

        WebDriverManager.chromedriver().setup();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);

        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);

        // Get the current working path
        String folderPath = TestUtil.getCurrentWorkingPath();

        // Verify downloads folder existence in current working path
        TestUtil.verifyFolderExistence(folderPath, "Downloads");
        String downLoadPath = folderPath + "\\Downloads";

        // Download files in Downloads folder
        chromePrefs.put("downloadPath", downLoadPath);
        chromePrefs.put("download.default_directory", downLoadPath);

        // Download the File
        chromePrefs.put("behavior", "allow");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        // DesiredCapabilities cap = DesiredCapabilities.chrome();
        // cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        // cap.setCapability(ChromeOptions.CAPABILITY, options);

        // options.addArguments("enable-automation");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        /*
         * options.addArguments("--disable-dev-shm-usage");
         * options.addArguments("--no-sandbox");
         * options.addArguments("start-maximized");
         * options.addArguments("--disable-gpu");
         * options.addArguments("--disable-setuid-sandbox");
         * options.addArguments("--window-size=1920,1080");
         * options.addArguments("--auto-open-devtools-for-tabs");
         * options.addArguments("--disable-extensions");
         */

        driver = new ChromeDriver(options); // open Chrome browser

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().window().maximize();
        return driver;
    }

    // Just added the code below to start the browser headless
    public WebDriver startLocalBrowserInHeadlessMode() {

        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);

        // Get the current working path
        String folderPath = TestUtil.getCurrentWorkingPath();

        // Verify downloads folder existence in current working path
        TestUtil.verifyFolderExistence(folderPath, "Downloads");
        String downLoadPath = folderPath + "\\Downloads";

        // Download files in Downloads folder
        chromePrefs.put("downloadPath", downLoadPath);
        chromePrefs.put("download.default_directory", downLoadPath);

        // Download the File
        chromePrefs.put("behavior", "allow");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        // DesiredCapabilities cap = DesiredCapabilities.chrome();
        // cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        // cap.setCapability(ChromeOptions.CAPABILITY, options);

        // options.addArguments("enable-automation");
        // options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        /*
         * options.addArguments("--disable-dev-shm-usage");
         * options.addArguments("--no-sandbox");
         * options.addArguments("start-maximized");
         * options.addArguments("--disable-gpu");
         * options.addArguments("--disable-setuid-sandbox");
         * options.addArguments("--window-size=1920,1080");
         * options.addArguments("--auto-open-devtools-for-tabs");
         * options.addArguments("--disable-extensions");
         */

        driver = new ChromeDriver(options); // open Chrome browser

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        return driver;

    }

    public WebDriver startLocalBrowser(String browser) {
        switch (browser) {
            case "ie":
                // start ie browser method call goes here
                logger.info("Starting 'ie' browser !");
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.ignoreZoomSettings();
                driver = new InternetExplorerDriver(options);
                break;

            case "Chrome":
                driver = startChromeBrowser();
                logger.info("Starting 'Chrome' browser !");
                break;
            case "Firefox":
                // start firefox browser method call goes here
                logger.info("Starting 'Firefox' browser !");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

                break;
            default:
                driver = startChromeBrowser();

                logger.info("User Selected Browser: " + browser + ", Starting Default Browser - Chrome!");
        }
        return driver;
    }

    public WebDriver startHeadlessChromeDriver() throws IOException {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=19200,1080");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        return driver;
    }

    public String captureScreenshot(String screenshotFileName, String filePath) throws IOException {
        String screenCaptureFile = null;
        String tempTime = getCurrentTime();
        File tempFile = new File(filePath);
        if (!tempFile.isDirectory()) {
            tempFile.mkdir(); // create the folder
        }
        if (!filePath.isEmpty()) {
            screenCaptureFile = filePath + screenshotFileName + tempTime + ".png";
        } else {
            screenCaptureFile = "src/test/resources/screenshots/" + screenshotFileName + tempTime + ".png";
        }
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(srcFile, new File(screenCaptureFile));
        File absFile = new File(screenCaptureFile);
        String absPath = absFile.getAbsolutePath();
        logger.info("Screenshot Path: " + absPath);
        return screenCaptureFile;
    }

    public String getCurrentTime() {
        Date date = new Date();
        String tempTime = new Timestamp(date.getTime()).toString();
        String finalTimeStamp = tempTime.replace(":", "_").replace("-", "_").replace(" ", "_").replace(".", "_");

        logger.info("TempTime: " + tempTime);
        logger.info("FinalTime: " + finalTimeStamp);
        return finalTimeStamp;
    }

    // Excel reader method --------------------------------------------

    public static Object[][] getTestData(String sheetName) throws EncryptedDocumentException, InvalidFormatException {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                // System.out.println(data[i][k]);
            }
        }
        return data;
    }

    // this will verify text

    public static void verifyText(String s1, String s2) {
        logger.info("veryfing test: " + s1 + " with " + s2);
        Assert.assertEquals(s1, s2);
    }

    public static void verifyTrue(boolean status) {
        Assert.assertTrue(status);
    }

    // this verify element display or not

    public boolean isDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            logger.info("element is Displayed.." + element.getText());

            return true;
        } catch (Exception e) {
            logger.error("element is not Displayed..", e.getCause());

            return false;
        }
    }

    // this will get the page title

    public String getTitle() {
        logger.info("page title is: " + driver.getTitle());
        return driver.getTitle();
    }

    // this will get the Current page title/ Current URL

    public String getCurrentPageTitle() {
        logger.info("page title is: " + driver.getCurrentUrl());

        return driver.getCurrentUrl();

    }

    // this select elements from dropdown by value

    public void selectUsingValue(WebElement element, String value) {
        Select select = new Select(element);
        logger.info("selectUsingValue and value is: " + value);
        select.selectByValue(value);
    }
    // this select elements from dropdown by index

    public void selectUsingIndex(WebElement element, int index) {
        Select select = new Select(element);
        logger.info("selectUsingIndex and index is: " + index);
        select.selectByIndex(index);
    }
    // this select elements from dropdown by visible text

    public void selectUsingVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        logger.info("selectUsingVisibleText and visibleText is: " + visibleText);
        select.selectByVisibleText(visibleText);
    }

    // this is list all the elements from dropdown menu

    public List<String> getAllDropDownData(WebElement element) {
        Select select = new Select(element);
        List<WebElement> elementList = select.getOptions();
        List<String> valueList = new LinkedList<String>();
        for (WebElement ele : elementList) {
            logger.info(ele.getText());
            valueList.add(ele.getText());
        }
        return valueList;
    }

    // this method will switchToFrame based on frame index
    public void switchToFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
        logger.info("switched to :" + frameIndex + " frame");
    }

    // this method will switchToFrame based on frame name

    public void switchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
        logger.info("switched to :" + frameName + " frame");

    }

    // this method will switchToFrame based on frame WebElement

    public void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);
        logger.info("switched to frame " + element.toString());
    }

    // This is ImplicitWait method

    public void setImplicitWait(long timeout, TimeUnit unit) {
        logger.info("Implicit Wait has been set to: " + timeout);
        // driver.manage().timeouts().implicitlyWait(timeout, unit);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public void clickElementJavascript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void enterTextJavaScript(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("document.getElementById('').value='0500845');
    }

    public void checkPageIsReady() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            // System.out.println("Page Is loaded.");
            return;
        }
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            // To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }

    }

    public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
            int pollingEveryInMiliSec) {
        logger.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("element is visible now");
    }

    public void WaitForTextVisible(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        logger.info("Text is visible now");
    }

    public void WaitForTheElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("Element is Clickable Now");
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        // TODO Auto-generated method stub
        return null;
    }

    // Mouse hover Over
    public void moveToElement(WebElement element) {

        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        ;
        element.click();
    }
    // This method get all the data from web table

    public void verifyAndPrintWebTableData(String tableName) {

        // Identify the table
        WebElement table = driver.findElement(By.xpath(tableName));

        // Get all rows (tr tags)
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String Expected = "CellName";
        // Print data from each row (Data from each td tag)

        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            for (WebElement col : cols) {

                System.out.print(col.getText() + "\t");// + "\t"

                String Actual = col.getText();

                // Check Expected Cell is present or not in WebTable
                if (Actual.equals(Expected)) {

                    System.out.println("Cell Exist in WebTable...");

                }
            }

            System.out.println();
        }
    }

    public void verifyAndPrintWebTableData() {

        // Identify the table
        WebElement table = driver.findElement(By.xpath("//table"));

        // Get all rows (tr tags)
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String Expected = "CellName";
        // Print data from each row (Data from each td tag)

        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            for (WebElement col : cols) {

                System.out.println(col.getText() + "\t");// + "\t"

                String Actual = col.getText();

                // Check Expected Cell is present or not in WebTable
                if (Actual.equals(Expected)) {

                    System.out.println("Cell Exist in WebTable...");

                }
            }

            System.out.println();
        }
    }

    public void printWebTableData() {

        List<WebElement> td = driver.findElements(By.xpath("//td"));
        // List<WebElement> tr=driver.findElements(By.xpath("//tr"));
        String data[] = new String[td.size()];
        for (int i = 0; i < td.size() - 1; i++) {
            WebElement wel = td.get(i);
            String x = data[i] = wel.getText();
            System.out.format(x + "\n\t");// "\t"

        }

    }

    // JDBC Connection And Retrieve Data(1st Row)

    public ArrayList<ArrayList<String>> getDataByRow(String databaseName, String query) throws ClassNotFoundException {
        logger.info("Get data from SQL ");

        // Create a 2d ArrayList to return results from
        ArrayList<ArrayList<String>> sqlResults = new ArrayList<>();

        // Set the driver (Note: Driver must still be added to the buildpath of the
        // project)

        Class.forName("com.ibm.as400.access.AS400JDBCDriver");

        try {
            // Class.forName("com.ibm.as400.access.AS400JDBCDriver");

            Connection con = DriverManager.getConnection("jdbc:as400:10.28.49.11/exla", "QATSTFRTBL", "qatest2019");

            // Create a Statement object that allows for a scrollable ResultSet
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Store the data returned from executing the sql query in a
            // ResultSet object
            ResultSet resultSet = statement.executeQuery(query);
            {
                // Get column count
                int columnCount = resultSet.getMetaData().getColumnCount();

                // Move cursor to last position of the ResultSet to get row
                // count

                resultSet.last();

                // Get the number of rows returned in the ResultSet
                int rowCount = resultSet.getRow();

                // Set the cursor at the beginning of the ResultSet
                resultSet.beforeFirst();

                // Loop through rows and add each one to an ArrayList
                for (int i = 1; i <= rowCount; i++) {

                    // Position the cursor over the data to be added
                    resultSet.next();

                    // Create temporary ArrayList for storing row data
                    ArrayList<String> row = new ArrayList<>();

                    // Create counter variable for iterating over columns
                    int j = 1;

                    // Loop over ResultSet to fill the temporary ArrayList
                    while (j <= columnCount) {

                        // Get the string value of the data
                        String n = resultSet.getString(j).trim();

                        // Add data to the the ArrayList
                        row.add(n);

                        // Increment counter
                        j++;
                    }
                    // Add ArrayList containing column data to the main
                    // ArrayList to be returned by the method
                    sqlResults.add(row);
                }
            }
        } catch (SQLException e) {
            // Print the exception if one is generated
            System.out.println("The exception is: " + e);
            // Print the vendor specific error code if an exception is generated
            System.out.println("The error code is: " + e.getErrorCode());
            // Print the vendor specific sql state if an exception is generated
            System.out.println("The sql state is: " + e.getSQLState());
        }
        return sqlResults;

    }
    // To Read a PDF file ( NOT for Scanner Type PDF)

    /*
     * public void readPDFFile(String YourFilePath) throws IOException,
     * InterruptedException { Thread.sleep(2000); URL url = new URL(YourFilePath);
     * InputStream is = url.openStream(); Thread.sleep(3000); BufferedInputStream
     * fileParse = new BufferedInputStream(is); PDDocument document = null;
     * 
     * document = PDDocument.load(fileParse); Thread.sleep(3000); String pdfcontent
     * = new PDFTextStripper().getText(document); System.out.println(pdfcontent); }
     */

    // generate random number

    public long randomNumber() {
        Random random = new Random();
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return number;
    }

    // This method will switch to parent window
    public void switchToParentWindow() {
        logger.info("Switch to parent window...");
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(0));

    }

    // This method will switch to child window based on index
    public void switchToWindow(int index) {
        Set<String> windows = driver.getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                logger.info("switched to : " + index + " window");
                driver.switchTo().window(window);
            } else {
                i++;
            }
        }
    }

    // This method will close all tabbed window and
    public void closeAllTabsAndSwitchToMainWindow() {
        Set<String> windows = driver.getWindowHandles();
        String mainwindow = driver.getWindowHandle();

        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainwindow)) {
                driver.close();
            }
        }
        logger.info("switched to main window");
        driver.switchTo().window(mainwindow);
    }

    // create and update freightBill

    public String createFreightBillWithUpdate(String AccNum, String TerminalNum) throws AWTException,
            InterruptedException, HeadlessException, UnsupportedFlavorException, IOException, ClassNotFoundException {
        logger.info("Create and Update FreightBill");

        CreateFreightBill createFreightBill = new CreateFreightBill();

        String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";

        Runtime.getRuntime().exec(path1);

        /*
         * Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
         * Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");
         */

        Keyboard keyboard = new Keyboard();
        Robot robot = new Robot();

        Thread.sleep(5000);

        // keyboard.type("QATSTFRTBL");
        // Shortcuts.pressTab();
        keyboard.type("qatest2019");
        Shortcuts.pressEnter();

        Thread.sleep(5000);

        keyboard.type("QATSTFRTBL");
        // Shortcuts.pressTab();
        keyboard.type("qatest2019");
        Shortcuts.pressEnter();

        Thread.sleep(5000);
        /*
         * //Step 4: Enter command in the command line to bring up the L2L38 master
         * menu. keyboard.type("call xxc870");
         */
        // Shortcuts.pressEnter();
        Thread.sleep(2000);
        // Step 5: Enter the menu option for the freight billing menu.
        keyboard.type("1");
        Shortcuts.pressEnter();
        // Step 6: Select the 'Reserve Freight Bill' menu option (82)
        keyboard.type("82");
        Shortcuts.pressTab();
        keyboard.type("test");
        Shortcuts.pressTab();

        keyboard.type(TerminalNum);
        Shortcuts.pressEnter();
        keyboard.type("1");
        Shortcuts.pressTab();
        Thread.sleep(1000);

        keyboard.type(AccNum);
        // keyboard.type("0102260");

        Thread.sleep(3000);
        Shortcuts.pressEnter();
        Thread.sleep(500);

        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_ALT);

        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(500);

        // String status ="SPRNEP";
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        String temp = data.substring(769, 780);
        // String temp = data.substring(30,40);
        System.out.println("temp Number: " + temp);
        String pro = temp.replace(" ", "");
        System.out.println("'" + pro + "'");
        System.out.println("Pro Number: " + pro);
        // return temp;

        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_F3);
        robot.keyRelease(KeyEvent.VK_F3);

        keyboard.type("3");
        Shortcuts.pressTab();
        keyboard.type("TEST");
        Shortcuts.pressTab();
        // keyboard.type("001");
        keyboard.type(TerminalNum);
        Shortcuts.pressEnter();

        keyboard.type(pro);
        Shortcuts.pressEnter();

        keyboard.type("1");
        keyboard.type("1");
        Shortcuts.pressTab();
        keyboard.type("C");
        Shortcuts.pressTab();
        keyboard.type("500");

        //

        createFreightBill.tabLoop(6);

        keyboard.type("0123116");
        createFreightBill.tabLoop(22);
        keyboard.type("99999999");
        createFreightBill.tabLoop(5);
        keyboard.type("72");

        Shortcuts.pressEnter();
        Shortcuts.pressEnter();
        Thread.sleep(5000);

        createFreightBill.tabLoop(6);
        keyboard.type("1");
        Shortcuts.pressTab();
        keyboard.type("PT");
        keyboard.type("FAK");
        Shortcuts.pressTab();
        keyboard.type("500");
        createFreightBill.tabLoop(4);
        keyboard.type("kw123");

        Shortcuts.pressEnter();
        Shortcuts.pressEnter();
        Thread.sleep(5000);

        keyboard.type("APT");
        Shortcuts.pressEnter();
        // return pro;
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);

        keyboard.type("90");
        Shortcuts.pressEnter();
        Thread.sleep(1000);

        keyboard.type("90");
        Shortcuts.pressEnter();
        Thread.sleep(1000);

        Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
        Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");

        return pro;

    }

    // update FreightBill

    public void updateFreightBill(String pro, String terNum, String cons, String shipper, String party)
            throws IOException, AWTException, InterruptedException {
        logger.info("Update FrieghtBill");
        CreateFreightBill createFreightBill = new CreateFreightBill();

        String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";

        Runtime.getRuntime().exec(path1);

        Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
        Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");

        Keyboard keyboard = new Keyboard();
        Robot robot = new Robot();

        Thread.sleep(5000);

        keyboard.type("QATSTFRTBL");
        keyboard.type("qatest2019");
        Shortcuts.pressEnter();

        Thread.sleep(5000);
        // enter option
        keyboard.type("1");
        Shortcuts.pressEnter();
        Thread.sleep(3000);

        keyboard.type("3");
        Shortcuts.pressTab();
        keyboard.type("TEST");
        Shortcuts.pressTab();
        // terminal number
        keyboard.type(terNum);
        Shortcuts.pressEnter();
        // PRO number
        keyboard.type(pro);
        Shortcuts.pressEnter();

        /*
         * // type x and skip
         * 
         * Shortcuts.pressTab(); keyboard.type("x"); Shortcuts.pressEnter();
         */

        keyboard.type("1");
        keyboard.type("1");
        Shortcuts.pressTab();
        keyboard.type("C");
        Shortcuts.pressTab();
        keyboard.type("500");

        //

        createFreightBill.tabLoop(6);
        // Consignee account
        keyboard.type(cons);
        createFreightBill.tabLoop(6);// tabLoop(22);
        // shipper accout
        keyboard.type(shipper);
        createFreightBill.tabLoop(6);
        // 3rd party
        keyboard.type(party);
        createFreightBill.tabLoop(8);

        keyboard.type("99999999");
        createFreightBill.tabLoop(5);
        keyboard.type("1");
        Sleep.sleepShort();
        Shortcuts.pressEnter();
        Shortcuts.pressEnter();
        Thread.sleep(5000);

        createFreightBill.tabLoop(6);
        keyboard.type("1");
        Shortcuts.pressTab();
        keyboard.type("PT");
        keyboard.type("FAK");
        Shortcuts.pressTab();
        keyboard.type("500");
        createFreightBill.tabLoop(4);
        // PO# number
        keyboard.type("kw123");

        Shortcuts.pressEnter();
        Shortcuts.pressEnter();
        Thread.sleep(5000);

        keyboard.type("APT");
        Shortcuts.pressEnter();
        // return pro;
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);

        keyboard.type("90");
        Shortcuts.pressEnter();
        Thread.sleep(1000);

        keyboard.type("90");
        Shortcuts.pressEnter();
        Thread.sleep(1000);

        Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
        Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");

    }

    public String reserveAFreightBill(String AccNum, String TerminalNum) throws AWTException, InterruptedException,
            HeadlessException, UnsupportedFlavorException, IOException, ClassNotFoundException {
        logger.info("Reserve FrieghtBill");

        String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";

        Thread.sleep(2000);
        Runtime.getRuntime().exec(path1);

        Keyboard keyboard = new Keyboard();
        Robot robot = new Robot();

        Thread.sleep(5000);

        keyboard.type("QATSTFRTBL");
        Shortcuts.pressTab();
        keyboard.type("qatest2019");
        Shortcuts.pressEnter();
        Thread.sleep(5000);

        keyboard.type("QATSTFRTBL");
        // Shortcuts.pressTab();
        keyboard.type("qatest2019");
        Shortcuts.pressEnter();

        Thread.sleep(5000);

        /*
         * //Step 4: Enter command in the command line to bring up the L2L38 master
         * menu. keyboard.type("call xxc870");
         */

        // Shortcuts.pressEnter();
        Thread.sleep(2000);
        // Step 5: Enter the menu option for the freight billing menu.
        keyboard.type("1");
        Shortcuts.pressEnter();
        // Step 6: Select the 'Reserve Freight Bill' menu option (82)
        keyboard.type("82");
        Shortcuts.pressTab();
        keyboard.type("test");
        Shortcuts.pressTab();

        keyboard.type(TerminalNum);
        Shortcuts.pressEnter();
        keyboard.type("1");
        Shortcuts.pressTab();
        Thread.sleep(1000);

        keyboard.type(AccNum);
        // keyboard.type("0102260");

        Thread.sleep(3000);
        Shortcuts.pressEnter();
        Thread.sleep(500);

        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_ALT);

        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(500);

        // String status ="SPRNEP";
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        String temp = data.substring(769, 780);
        // String temp = data.substring(30,40);
        // System.out.println("temp Number: " + temp);
        String pro = temp.replace(" ", "");
        // System.out.println("'" + pro + "'");
        System.out.println("Pro Number is : " + pro);

        Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
        Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");

        return pro;
    }

    /*
     * public void freightBillUpdateWithExcel(String uName, String Pass, String
     * option, String user, String terNum, String proNum, String ts, String pcs,
     * String terms, String wgt, String cons, String pudr, String cubicFeet, String
     * ship, String thirdParty, String pcs1, String pk, String desc, String wgt2,
     * String PO, String accesorialCode) throws IOException, AWTException,
     * InterruptedException {
     * 
     * String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";
     * Runtime.getRuntime().exec(path1); Sleep.sleepShort(); //
     * Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F"); //
     * Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F"); Sleep.sleepCustom(5);
     * login.signOn(uName, Pass); ltl38MasterMenu.selectFrieghtBill("1");
     * Sleep.sleepShort(); freightBillingMenu.selectOption(option, user, terNum);
     * Sleep.sleepShort(); freightBillUpdateMainScreen.enterPro(proNum);
     * freightBillUpdateMainScreen.submit(); Sleep.sleepShort();
     * freightBillUpdateScreen1.enterData(ts, pcs, terms, wgt, cons, pudr,
     * cubicFeet, ship, thirdParty); freightBillUpdateScreen1.submit();
     * Sleep.sleepShort(); freightBillUpdateScreen2.enterData(pcs1, pk, desc, wgt2,
     * PO); freightBillUpdateScreen2.submit(); Sleep.sleepShort();
     * freightBillUpdateScreen3.enterData(accesorialCode);
     * freightBillUpdateScreen3.submit();
     * Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
     * Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");
     */

    /*
     * // reserve frieghtBill with excel public String
     * reserveAFreightBillWithExcel(String uName, String Pass, String option, String
     * user, String terNum, String NumOfBill, String SHCode) throws AWTException,
     * InterruptedException, HeadlessException, UnsupportedFlavorException,
     * IOException, ClassNotFoundException { String path1 =
     * "C:\\Users\\walika\\Desktop\\As400_1.bat";
     * 
     * Runtime.getRuntime().exec(path1); Shortcuts.pressEnter();
     * Sleep.sleepMedium();
     * 
     * login.signOn(uName, Pass); Sleep.sleepShort();
     * ltl38MasterMenu.selectFrieghtBill("1"); Sleep.sleepShort();
     * freightBillingMenu.selectOption(option, user, terNum); Sleep.sleepShort();
     * reserveFreightBills.enterNumberOfBills(NumOfBill); Sleep.sleepShort();
     * reserveFreightBills.enterShipperCode(SHCode); Sleep.sleepShort();
     * reserveFreightBills.submitRequest(); Sleep.sleepShort(); String pro =
     * reserveFreightBills.getBillNumber(); Sleep.sleepShort();
     * reserveFreightBills.getMessage(); Sleep.sleepShort();
     * 
     * Runtime.getRuntime().exec("Taskkill /IM pcsws.exe /F");
     * Runtime.getRuntime().exec("Taskkill /IM pcscm.exe /F");
     * 
     * return pro;
     * 
     * }
     */

    public String randomLetter() {

        int length = 5;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String str = new Random().ints(length, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        return str;
    }

    public void cityDeliveryManifest(String pro, String ter)
            throws IOException, HeadlessException, UnsupportedFlavorException, InterruptedException, AWTException {

        logger.info("City Delivery Manifest");

        String path1 = "C:\\Users\\walika\\Desktop\\As400_1.bat";

        Thread.sleep(2000);
        Runtime.getRuntime().exec(path1);

        Keyboard keyboard = new Keyboard();
        Robot robot = new Robot();

        Thread.sleep(5000);

        keyboard.type("QATSTFRTBL");
        // Shortcuts.pressTab();
        keyboard.type("qatest2019");
        Shortcuts.pressEnter();

        Thread.sleep(5000);

        Thread.sleep(2000);
        // Step 5: Enter the menu option for the freight billing menu.

        keyboard.type("1");
        Shortcuts.pressEnter();
        // Step 6: Select the 'Reserve Freight Bill' menu option (82)
        keyboard.type("25");
        Shortcuts.pressTab();
        keyboard.type("QA");
        Shortcuts.pressTab();

        keyboard.type(ter);// TerminalNum
        Shortcuts.pressEnter();
        Thread.sleep(2000);
        Shortcuts.pressF10();
        keyboard.type("?");
        Thread.sleep(2000);
        Shortcuts.pressEnter();
        Thread.sleep(2000);
        Shortcuts.pressTab();
        keyboard.type("MTY");
        Shortcuts.pressEnter();
        Thread.sleep(2000);

        keyboard.type("2");
        Thread.sleep(2000);
        Shortcuts.pressEnter();
        Thread.sleep(2000);

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_ALT);

        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(500);

        // String status ="SPRNEP";
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        String tNum = data.substring(760, 766);
        // String temp = data.substring(30,40);
        System.out.println("Trailer Number: " + tNum);

        Shortcuts.pressTab();
        keyboard.type("1");
        Thread.sleep(200);
        Shortcuts.pressEnter();

        Shortcuts.tabLoop(11);
        Thread.sleep(200);
        keyboard.type(pro);
        Thread.sleep(5000);
        Shortcuts.pressEnter();

        Shortcuts.tabLoop(3);
        Thread.sleep(200);

        keyboard.type("Y");
        Thread.sleep(200);
        Shortcuts.pressEnter();
        Thread.sleep(2000);

        Shortcuts.tabLoop(1);
        Thread.sleep(2000);
        keyboard.type("?");
        Shortcuts.pressEnter();
        Thread.sleep(2000);

        Shortcuts.tabLoop(1);
        Thread.sleep(200);
        keyboard.type("1");
        Shortcuts.pressEnter();

        Thread.sleep(200);
        Shortcuts.tabLoop(2);
        Thread.sleep(200);
        keyboard.type("1121");
        Thread.sleep(200);
        Shortcuts.pressTab();
        keyboard.type("200");
        Thread.sleep(200);
        Shortcuts.pressTab();
        keyboard.type("20001");
        Thread.sleep(200);
        Shortcuts.pressEnter();
        Thread.sleep(2000);

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_ALT);

        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(2000);

        // String status ="SPRNEP";
        String message = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        String text = message.substring(1860, 1902);
        // String temp = data.substring(30,40);
        System.out.println("Text: " + text);

        /*
         * if (text.equals(text)) {
         * 
         * Thread.sleep(200); keyboard.type("201"); Shortcuts.pressEnter();
         * Thread.sleep(2000); Shortcuts.pressTab(); keyboard.type("20001");
         * Thread.sleep(200);
         * 
         * }
         */

        int i = 1;
        do {
            int a = 203 + i;
            int b = 20003 + i;

            Thread.sleep(200);
            keyboard.type(String.valueOf(a));
            Thread.sleep(2000);
            Shortcuts.pressTab();
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            keyboard.type(String.valueOf(b));
            Thread.sleep(2000);
            Shortcuts.pressEnter();
            Thread.sleep(2000);
        } while (text.equals(text));

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_ALT);

        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(2000);

        // String status ="SPRNEP";
        String city = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        String cNum = city.substring(945, 967);
        // String temp = data.substring(30,40);
        System.out.println("City manifest number is : " + cNum);

    }

    // Add or Subtract Date from Today's date
    public String addOrSubstractDateFromTodayDate(int NoOfAddOrSubtractDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        // Getting current date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, NoOfAddOrSubtractDay);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }

    // Today's Date
    public String todaysDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        // Getting current date
        Calendar cal = Calendar.getInstance();
        // Displaying current date in the desired format
        String todayDate = sdf.format(cal.getTime());

        return todayDate;

    }

    public String selectWeekendDays() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i <= 7; i++) {
            cal.add(Calendar.DATE, i);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                String weekend = sdf.format(cal.getTime());
                // System.out.println(weekend);
                return weekend;
            }

        }

        return null;
    }

    // To Delete in .CSV File
    public void deleteARowInCsvFile(String csvFilePath) {

        String filelocation = csvFilePath;
        try (CSVReader reader2 = new CSVReader(new FileReader(filelocation))) {
            List<String[]> allElements = reader2.readAll();
            allElements.remove(1);
            FileWriter sw = new FileWriter(filelocation);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(allElements);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // To Write in .CSV File
    public void writeToCsvFile(String CsvFilePath, String Company, String UFirstName, String ULastName, String Location,
            String PAreaCode, String PExchange, String PLast4, String PExtension, String FAreaCode, String FExchange,
            String FLast4, String Address1, String Address2, String City, String State, String ZipCode, String ZipLast4,
            String Country, String Email) throws IOException, AWTException {
        String csv = CsvFilePath;
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String[] record = ("X,X,X,X," + Company + "," + UFirstName + "," + ULastName + "," + Location + "," + PAreaCode
                + "," + PExchange + "," + PLast4 + "," + PExtension + "," + FAreaCode + "," + FExchange + "," + FLast4
                + "," + Address1 + "," + Address2 + "," + City + "," + State + "," + ZipCode + "," + ZipLast4 + ","
                + Country + "," + Email + "").split(",");
        writer.writeNext(record);
        writer.close();
    }

    // To Upload a File From Desktop
    public void uploadAFile(String FilePath) throws AWTException {

        Robot robot = new Robot();

        robot.delay(2000);
        StringSelection stringSelection = new StringSelection(FilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.delay(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    // Wait for page to load
    public static void waitForPageToLoad() {
        String pageLoadStatus;
        do {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            pageLoadStatus = (String) js.executeScript("return document.readyState");
            System.out.print("Loading......");
        } while (!pageLoadStatus.equals("complete"));

        System.out.println("Page Loaded.");
    }

    public void openNewTab(String newUrl) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        driver.get(newUrl);
    }

    // verify downloaded file name

    public boolean verifyDownloadedFileName(String dirPath, String fileName) {

        logger.info("verify file name exist");

        boolean flag = false;
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            flag = false;
        }

        for (int i = 1; i < files.length; i++) {
            if (files[i].getName().contains(fileName)) {
                flag = true;
            }
        }

        return flag;
    }

    public void clickOnEditButton() {
        logger.info("Click on Edit Button");
        testUtil.init(this);
        List<WebElement> we = driver
                .findElements(By.xpath("//*[@id='driverTractorContainer']/div/table/tbody/tr[*]/td[6]"));
        int listSize = we.size();
        System.out.println("Size of Ele: " + listSize);

        for (int i = 0; i < listSize; i++) {
            String eleName = we.get(i).getText();
            System.out.println("Name of Ele: " + eleName);
            System.out.println(i);
            if (eleName.equals("ACTIVE")) {
                int j = i + 1;
                driver.findElement(By.xpath("(//*[@id='driverTractorEditButton'])[" + j + "]")).click();
                break;
            }

        }
    }

    // open new window---- window handle

    public void openNewBrowser() {
        logger.info("Open New Browser..................");
        testUtil.init(this);
        ((JavascriptExecutor) driver).executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(url);
        // driver.switchTo().window(tabs.get(0));

    }

    // return to main window

    public void returnToMainWindow() {

        logger.info("return to the mian window");
        testUtil.init(this);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

    }

    // switch to the child window

    public void switchToChildWindow() {

        logger.info("return to the child Window");
        testUtil.init(this);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

    }

    public void highlightElement(By by) throws Exception {

        WebElement element = driver.findElement(by);

        for (int i = 0; i < 4; i++) {
            WrapsDriver wrappedElement = (WrapsDriver) element;
            JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
            Thread.sleep(500);// customWait(0.5);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 2px solid yellow;");
            Thread.sleep(500);// customWait(0.5);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }

    }

    public void setHardWait(long timeOutSeconds) {
        logger.info("Hard Wait has been set to: " + timeOutSeconds);
        try {
            Thread.sleep(timeOutSeconds);
        } catch (Exception ignore) {
        }
    }

    public void setExplicitWait(WebElement ele, long timeOutSeconds) {
        logger.info("Explicit wait has been set to: " + timeOutSeconds);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    /* Change option to not show pdf in browser directly. */
    public void setChromeOptions(WebDriver driver) {

        driver.get("chrome://settings/content/pdfDocuments");
        By pdfSectionBy = By.id("knob");
        WebElement pdfSectionElement = driver.findElement(pdfSectionBy);
        pdfSectionElement.click();
    }

    // Enter Random String of specific size
    public String randomString(int length) {
        logger.info("Entering random letters.....");

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str = new Random().ints(length, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        return str;

    }

    public void setExplitWaitAttributeContains(WebElement ele, String attribute, String value, long timeOutSeconds) {
        logger.info("Explicit Wait has been set to: " + timeOutSeconds);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.attributeContains(ele, attribute, value));
    }

    public void waitForJqueryToLoad() {
        while (true) {
            boolean response = (boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            if (response) {
                break;
            }
            try {
                Thread.sleep(300);
            } catch (Exception ignore) {
            }
        }
    }

    // To get Web Element
    public WebElement getWebElement(WebElement we, String cssSelector) {
        WebElement sd = null;
        sd = we.findElement(By.cssSelector(cssSelector));
        return sd;

    }

    // To get Text of WebElement
    public String getTextOfElement(WebElement element) {
        String TextOfElement = "";
        TextOfElement = element.getText();
        logger.info("Text of WebElement: " + TextOfElement);
        return TextOfElement;

    }

    public boolean waitForElementsToAppear(List<WebElement> element) throws InterruptedException {
        boolean status = false;
        int j = 0;
        waitForJqueryToLoad();
        do {
            waitForFixedTime(500);
            j++;
            int sizeOfObj = element.size();
            if (sizeOfObj > 0) {
                status = true;
            }
            if (j >= 180) {
                status = true;
            }
        } while (!status);

        if (j != 0) {
            System.out.println("WAITED FOR OBJECT TO APPEAR  -> " + j + " Secs");
        }

        if (j >= 180 && element.size() == 0) {
            status = false;
        }
        return status;
    }

    public void waitForFixedTime(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ignore) {
        }
    }

    // enter random company name
    public String enterRandomCompanyName() {
        logger.info("Enter Random company name");
        testUtil.init(this);
        Faker faker = new Faker();
        return faker.company().name();

    }

    // enter random first name
    public String enterRandomFirstName() {
        logger.info("Enter Random first name");
        testUtil.init(this);
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    // enter random last name
    public String enterRandomLastName() {
        logger.info("Enter Random last name");
        testUtil.init(this);
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    // enter random email address
    public String enterRandomEmailAddress() {
        logger.info("Enter Random email address");
        testUtil.init(this);
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        return email;
    }

    // enter random username
    public String enterRandomUserName() {
        logger.info("Enter Random user name");
        testUtil.init(this);
        Faker faker = new Faker();
        return faker.name().username();
    }

    // enter random phone number
    public String enterRandomPhoneNumber() {
        logger.info("Enter Random phone number");
        testUtil.init(this);
        Faker faker = new Faker();
        return faker.phoneNumber().phoneNumber();
    }

    public void reloadPage() {
        logger.info("Refreshing Page....");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("location.reload()");
    }

    // Get Past business date from today's Date
    public String addPastWeekDay() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.MONDAY)
            c.add(Calendar.DATE, -3);
        else if (dayOfWeek == Calendar.SUNDAY)
            c.add(Calendar.DATE, -2);
        else
            c.add(Calendar.DATE, -1);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    // Scrolling down the Web page
    public void scrollWebPageToBottom() {
        logger.info("Scrolling down the Web page at the bottom of the page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // scrow into view method

    public void scrollIntoView(WebElement element) {
        logger.info("Scrolling  into view");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public String isWeekend(String input_date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");

        Calendar c = Calendar.getInstance();
        c.setTime(format1.parse(input_date));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SATURDAY)
            c.add(Calendar.DATE, -1);
        else if (dayOfWeek == Calendar.SUNDAY)
            c.add(Calendar.DATE, -2);
        else
            c.add(Calendar.DATE, 0);

        String newDate = format1.format(c.getTime());
        return newDate;
    }

    // Scrolling Up the Web page
    public void scrollWebPageToUp() {
        logger.info("Scrolling up the Web page ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void switchToAlertAndAccept() {
        logger.info("Switching to Alert Popup and accepting");
        driver.switchTo().alert().accept();
    }

    // JDBC connection=
    public void sendQuery(String query) throws SQLException, ClassNotFoundException {
        logger.info("Executing Query: " + query);
        Class.forName(ConnectConfig.AS400_JDBC_DRIVER);
        Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
                ConnectConfig.PASSWORD1);
        Statement statement = con.createStatement();
        try {
            statement.executeUpdate(query);
            statement.close();

        } catch (SQLException e) {
            statement.close();
        }
    }

    public String getTodayDateByFormat(String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);

    }

    public int getCountOfOpenTabs() {
        logger.info("Get count of number of opened tabs");
        Set<String> allWindowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
        return tabs.size();
    }

    // FLUEN WAIT

    public void fluentWait(By by, long timeOut, long polling, String text) {
        @SuppressWarnings("deprecation")
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)

                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(polling))
                .ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(by);
                String getTextOnPage = element.getText();

                if (getTextOnPage.equals(text)) {
                    System.out.println(getTextOnPage);
                    return element;
                } else {
                    System.out.println("FluentWait Failed");
                    return null;
                }
            }
        });

    }

    public void moveTo(WebElement element) {

        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

    }

    public String getValueFromQuery(String query) throws Exception {
        logger.info("Executing Query: " + query);
        Class.forName(ConnectConfig.AS400_JDBC_DRIVER);
        Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
                ConnectConfig.PASSWORD1);
        Statement statement = con.createStatement();
        String destZip = null;
        try {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                destZip = rs.getString(1);
                System.out.println(rs.getString(1));
            }
            statement.close();

        } catch (SQLException e) {
            statement.close();
        }
        return destZip;
    }

    public void switchToChildTabIfExist() throws InterruptedException {
        logger.info("Switch to child window");
        testUtil.setHardWait(1000);

        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> childTab = new ArrayList<String>(windowHandles);
        if (childTab.size() > 1) {
            logger.info("Number of child windows: " + childTab.size());
            driver.switchTo().window(childTab.get(1));
        } else {
            logger.info("Unable to find child window");
        }
    }

    public String getProNumberFromDB() throws Exception {

        logger.info("Execute Query: ");
        Class.forName(ConnectConfig.AS400_JDBC_DRIVER);
        Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
                ConnectConfig.PASSWORD1);

        Statement statement = con.createStatement();
        String query = "select fhot, fhpro from fbfiles.frp001 where fhdc = '' order by fhpud8 desc";
        String proNumber = null;

        try {
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String ot = rs.getString(1);

            if (ot.length() == 2) {
                ot = "0" + ot;
            } else if (ot.length() == 1) {
                ot = "00" + ot;
            }

            String pro = rs.getString(2);
            proNumber = ot + pro;
            System.out.println("Pro number returned by query: " + proNumber);
            statement.close();
        } catch (SQLException e) {
            statement.close();
        }

        return proNumber;
    }

    public String checkAndAddLeadingZeroToFBData(int count, String text) {
        logger.info("Check and add leading zero to FB details");
        if (text.length() != count) {
            int missingLen = count - text.length();
            for (int i = 0; i < missingLen; i++) {
                String str = "0" + text;
                text = str;
            }
        }
        return text;
    }

    public String getCurrentTimeViaDate() {
        logger.info("Returns Current time");

        Date date = new Date();
        String TimeVal = new Timestamp(date.getTime()).toString();
        String res = TimeVal.substring(0, 16);
        return res;
    }

    public WebElement ValidateErrorMessage(By ErrorMessage, int timeout) {
        logger.info("Verify please correct the following error(s):");
        WebElement element = null;

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessage));

        return element;

    }

    // To Upload a File From Desktop
    public void SelectFile(String FilePath) throws AWTException {

        Robot robot = new Robot();
        Keyboard keyboard = new Keyboard();

        robot.delay(2000);
        StringSelection stringSelection = new StringSelection(FilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.delay(1000);

        robot.delay(300);

        keyboard.type(FilePath);
        Shortcuts.pressEnter();

        robot.delay(1000);

    }

    // To grab current working directory
    public static String getCurrentWorkingPath() {
        System.out.println("Capture Current Working Directory.....");
        Path currentRelativePath = Paths.get("");
        String currentWorkingPath = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current Working path -->" + currentWorkingPath);
        return currentWorkingPath;
    }

    // Verify Folder existence
    public static void verifyFolderExistence(String path, String folderName) {
        System.out.println("Verifying existence of folder - '" + folderName + "' in path -> " + path);
        String folderPath = path + "\\" + folderName;
        File newFolder = new File(folderPath);
        if (!newFolder.exists()) {
            createFolder(path, folderName);
        } else {
            System.out.println("Folder is already created in this path: " + folderPath);
        }
    }

    // Create a folder in a specific path
    public static void createFolder(String folderPath, String folderName) {
        System.out.println("Create Folder '" + folderName + "' in path --> " + folderPath);
        String folderAbsultePath = folderPath + "\\" + folderName;
        File folder = new File(folderAbsultePath);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created in the path:" + folderPath);
            } else {
                System.out.println("Failed to create folder: " + folderPath);
            }
        }
    }

    public boolean verifyIsFileDownloaded(String downloadPath, String fileName) {

        logger.info("Verify report download");
        boolean flag = false;
        String fileFound = "";

        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        if (dir_contents.length > 0) {
            System.out.println("Files count " + dir_contents.length);
            for (int i = 0; i < dir_contents.length; i++) {
                if (dir_contents[i].getName().contains(fileName)) {
                    fileFound = dir_contents[i].getName();
                    flag = true;
                    break;
                }
            }
            System.out.println("File Found :" + fileFound + "\n" + "File we look for :" + fileName);
            assertTrue(fileFound.contains(fileName), "Downloaded file name is not matching with expected file name");
        } else if (dir_contents == null || dir_contents.length == 0) {
            System.out.println("Files count " + dir_contents.length);
            flag = false;
            assertTrue(false, "No files are downloaded");
        }
        return flag;
    }

    public String verifyDownloadedFileName(String dirPath, String extNme, String fileFound) {

        logger.info("verify file name exist");
        boolean flag = false;
        // String fileFound = "";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files.length > 0) {
            System.out.println("Files count " + files.length);
            for (int i = 0; i < files.length; i++) {

                if (files[i].getName().contains(extNme)) {
                    fileFound = files[i].getName();
                    flag = true;
                    break;
                }
            }
            System.out.println("File Found :" + fileFound + "\n" + "File we look for : " + extNme);
            assertTrue(fileFound.contains(extNme), "Downloaded file name is not matching with expected file name");
        } else if (files == null || files.length == 0) {
            System.out.println("Files count " + files.length);
            flag = false;
            assertTrue(false, "No files are downloaded");
        }

        return fileFound;
    }

    public void deleteFilesFromFolder(String folderPath, String fileName) {

        logger.info("Delete a file: Name - " + fileName + " from path : " + folderPath);

        driver.switchTo().defaultContent();
        File dir = new File(folderPath);
        File[] dir_contents = dir.listFiles();

        if (dir_contents.length > 0) {
            System.out.println("Files count " + dir_contents.length);
            for (int i = 0; i < dir_contents.length; i++) {
                if (dir_contents[i].getName().contains(fileName)) {
                    dir_contents[i].delete();
                    break;
                }
            }
        }

        else {
            System.out.println("File not found"); // <--added newly
        }
    }

    /* Get the latest file from a specific directory */
    public File getLatestFilefromDir(String dirPath) {

        logger.info("verify file name exist");

        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
            System.out.println(lastModifiedFile);
        }
        return lastModifiedFile;
    }

    public String getupdatedfile(String dirPath) {

        getLatestFilefromDir(dirPath);
        System.out.println();
        return dirPath;
    }

//Get future business date from today's Date
    public String addFutureWeekDay() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.THURSDAY)
            c.add(Calendar.DATE, +4);
        else if (dayOfWeek == Calendar.FRIDAY)
            c.add(Calendar.DATE, +3);
        else
            c.add(Calendar.DATE, +2);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    // Get tommorw's business date from today's Date
    public String addTomorrowDate() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.FRIDAY)
            c.add(Calendar.DATE, +3);
        else
            c.add(Calendar.DATE, +1);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    public String getBusinessDate(String calType, int count) {

        Calendar cal = Calendar.getInstance();

        if (calType == "DATE") {
            cal.add(Calendar.DATE, count);
        }

        String workingDate = "";

        for (int i = 0; i < count; i++) {
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/YYYY");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, count + i);

            if (!(CheckHoliday.isHoliday(c))) {
                workingDate = (String) (formattedDate.format(c.getTime()));
                break;
            } else {
                System.out.println(
                        "Selected date" + c + " is Estes Holiday/Weekend,Retrying to select another date.....");
            }

        }
        return workingDate;

    }

    public void checkStringListSorted(ArrayList<String> actualList, String errorMsg) {
        ArrayList<String> expectedList = actualList;
        Collections.sort(actualList);
        Assert.assertEquals(actualList, expectedList, errorMsg);
    }

    public void checkStringListReverseSorted(ArrayList<String> actualList, String errorMsg) {
        ArrayList<String> expectedList = actualList;
        Collections.sort(actualList, Collections.reverseOrder());
        Assert.assertEquals(actualList, expectedList, errorMsg);
    }

    public static void verifyFirstValueGreaterThanSecond(String s1, String s2) {
        logger.info("Veryfing value: " + s1 + " with " + s2);
        double num1 = Double.parseDouble(s1);
        double num2 = Double.parseDouble(s2);
        if (num1 > num2) {
            logger.info(s1 + " is greated than " + s2);
        }
    }

    public String copyStringFromInputBox(WebElement element) throws UnsupportedFlavorException, IOException {
        element.sendKeys(Keys.CONTROL + "A");
        element.sendKeys(Keys.CONTROL + "C");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String copiedString = (String) contents.getTransferData(DataFlavor.stringFlavor);
        return copiedString;

    }

    public String getSelectedValue(WebElement Ele) {
        logger.info("Get Selected value from options");
        Select s = new Select(Ele);
        WebElement option = s.getFirstSelectedOption();
        String SMCStatus = option.getText();
        return SMCStatus;
    }

    public int getNumericCellValue(String path, int rowNum, int colNum) {

        File src = new File(path);
        int num = 0;
        try (FileInputStream fis = new FileInputStream(src); HSSFWorkbook wb = new HSSFWorkbook(fis);) {
            HSSFSheet sh = wb.getSheetAt(0);
            double number = sh.getRow(rowNum).getCell(colNum).getNumericCellValue();
            num = (int) number;
            logger.info(num);
            return num;
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return num;
    }

    public void printPDFFile(String docPath) throws IOException, PrinterException {
        setHardWait(5000);
        logger.info("Print the doc : " + docPath);
        PDDocument document = PDDocument.load(new File(docPath));
        PrinterJob job = PrinterJob.getPrinterJob();
        job.print();
    }

    public void verifyContentofPDF(String docPath, List<String> expectedVal) {
        logger.info("Validating content in Bill of Landing Receipt");
        ArrayList<String> flagList = new ArrayList<>();

        try {
            PDDocument pdfDoc = PDDocument.load(new File(docPath));
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfContent = pdfStripper.getText(pdfDoc);
            logger.info("pdfContent : " + pdfContent);

            Iterator<String> it = expectedVal.iterator();

            while (it.hasNext()) {
                String expectedContent = it.next();
                if (pdfContent.contains(expectedContent)) {
                    flagList.add(expectedContent + " status is True");
                } else {
                    Assert.fail("Validation failed. Please check actual and expected data");
                }
            }

            pdfDoc.close();
        } catch (Exception e) {
            logger.info("File not found");
        }
    }

    public void fetchStringfromPDF(String data, String docPath) throws IOException {
        PDDocument pdfDoc = PDDocument.load(new File(docPath));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfContent = pdfStripper.getText(pdfDoc);
        if (pdfContent.contains(data)) {
            logger.info("Required data found.");
        } else {
            Assert.fail("Required data not found.");
        }
        pdfDoc.close();
    }

    public String addFutureWeekDayFormattedForEXLAVerification() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.THURSDAY)
            c.add(Calendar.DATE, +4);
        else if (dayOfWeek == Calendar.FRIDAY)
            c.add(Calendar.DATE, +3);
        else
            c.add(Calendar.DATE, +2);
        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    public Workbook openExcelFile(String filepath)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        logger.info("Open Excel file.");
        FileInputStream file = new FileInputStream(filepath);
        book = WorkbookFactory.create(file);
        return book;
    }

    public int fetchNumberofRows(String sheetName, String fileName)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        book = openExcelFile(TestUtil.getCurrentWorkingPath() + "\\Downloads\\" + fileName);
        sheet = book.getSheet(sheetName);
        int rowNum = sheet.getLastRowNum();
        logger.info("rowNum : " + rowNum);
        book.close();
        return rowNum;
    }

    @SuppressWarnings({ "deprecation", "unused" })
    public Map<Integer, List<String>> fetchRecordsfromExcel(String sheetName, String fileName)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        book = openExcelFile(TestUtil.getCurrentWorkingPath() + "\\Downloads\\" + fileName);
        sheet = book.getSheet(sheetName);
        Map<Integer, List<String>> excelRecordsFetched = new HashMap<Integer, List<String>>();
        List<String> excelRecords = new ArrayList<String>();
        int count = 0;
        for (Row row : sheet) {
            if (count > 0) {
                for (Cell cell : row) {
                    if (Cell.CELL_TYPE_STRING == 1) {
                        logger.info("Cell " + count + " : " + cell.getStringCellValue());
                        excelRecords.add(cell.getStringCellValue());
                    } else if (Cell.CELL_TYPE_BLANK == 3) {
                        logger.info("Cell " + count + " : Value is null");
                        excelRecords.add("null");
                    }
                }
                excelRecordsFetched.put(count, excelRecords);
            }
            count++;
        }
        return excelRecordsFetched;
    }

    public void navigateBack() {
        logger.info("Navigate Back to Previous Page");
        driver.navigate().back();

    }

    public WebElement assetWait(String xpath, WebElement ele, int rotations, int time, TimeUnit unit) {
        logger.info("Asset Wait.");
        WebElement wEle = ele;
        for (int t = 0; t < rotations; t++) {
            try {
                if (unit.equals(TimeUnit.MILLISECONDS)) {
                    Thread.sleep(time);
                } else if (unit.equals(TimeUnit.SECONDS)) {
                    Thread.sleep(time * 1000);
                }

                if (!xpath.isEmpty()) {

                    wEle = driver.findElement(By.xpath(xpath));
                    logger.info("Rotation : " + t);
                    logger.info("Found element.");
                    break;
                }

                if (ExpectedConditions.visibilityOf(wEle) != null) {
                    logger.info("Rotation : " + t);
                    logger.info("Found element.");
                    break;
                }
            } catch (Exception exception) {
                logger.info(exception.getMessage());
            }
            logger.info("Searching for next rotation.");
        }
        return wEle;
    }

    public WebElement assetWaitDisplayed(WebElement ele, int rotations, int time, TimeUnit unit) {
        logger.info("Asset Wait.");

        WebElement webEle = null;

        for (int t = 0; t < rotations; t++) {
            try {
                if (unit.equals(TimeUnit.MILLISECONDS)) {
                    Thread.sleep(time);
                } else if (unit.equals(TimeUnit.SECONDS)) {
                    Thread.sleep(time * 1000);
                }

                if (ele.isDisplayed()) {
                    logger.info("Found element.");
                    logger.info("Rotation : " + t);
                    break;
                }
            } catch (Exception exception) {
                logger.info(exception.getMessage());
            }
            logger.info("Searching for next rotation.");
        }
        return webEle;
    }

    public WebElement assetWaitClickable(String xath, WebElement ele, int rotations, int time, TimeUnit unit) {
        logger.info("Asset Wait.");

        WebElement webEle = null;
        for (int t = 0; t < rotations; t++) {
            try {
                if (unit.equals(TimeUnit.MILLISECONDS)) {
                    Thread.sleep(time);
                } else if (unit.equals(TimeUnit.SECONDS)) {
                    Thread.sleep(time * 1000);
                }
                if (xath != null) {
                    ele = driver.findElement(By.xpath(xath));
                    webEle = ele;
                }

                if (ExpectedConditions.elementToBeClickable(ele) != null) {

                    webEle = ele;
                    logger.info("Element is clickable");
                    logger.info("Rotation : " + t);
                    break;
                }
            } catch (Exception exception) {
                logger.info(exception.getMessage());
            }
            logger.info("Searching for next rotation.");
        }
        return webEle;
    }

    public String addTomorrowDate1() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, M/d/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.FRIDAY)
            c.add(Calendar.DATE, +3);
        else if (dayOfWeek == Calendar.SATURDAY)
            c.add(Calendar.DATE, +2);
        else
            c.add(Calendar.DATE, +1);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    public String addDateFiveBusinessDaysInFuture() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, M/d/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SATURDAY)
            c.add(Calendar.DATE, +6);
        else if (dayOfWeek == Calendar.SUNDAY)
            c.add(Calendar.DATE, +5);
        else
            c.add(Calendar.DATE, +7);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    public String addTomorrowDate2() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.FRIDAY)
            c.add(Calendar.DATE, +3);
        else if (dayOfWeek == Calendar.SATURDAY)
            c.add(Calendar.DATE, +2);
        else
            c.add(Calendar.DATE, +1);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    public String addDateFiveBusinessDaysInFuture2() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SATURDAY)
            c.add(Calendar.DATE, +6);
        else if (dayOfWeek == Calendar.SUNDAY)
            c.add(Calendar.DATE, +5);
        else
            c.add(Calendar.DATE, +7);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    public String getHourBasedOnTimeZone(String timeZone) throws Exception {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        SimpleDateFormat formatter = new SimpleDateFormat("h");
        String hour = formatter.format(calendar.getTime());

        return hour;
    }

    public String getBusinessDateShortenedYear(String calType, int count) {

        Calendar cal = Calendar.getInstance();

        if (calType == "DATE") {
            cal.add(Calendar.DATE, count);
        }

        String workingDate = "";

        for (int i = 0; i < count; i++) {
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yy");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, count + i);

            if (!(CheckHoliday.isHoliday(c))) {
                workingDate = (String) (formattedDate.format(c.getTime()));
                break;
            } else {
                System.out.println(
                        "Selected date" + c + " is Estes Holiday/Weekend,Retrying to select another date.....");
            }

        }
        return workingDate;

    }

    public void readerOutboundFileAssertStringIsNotThere(String fileStartsWith, String fbNum, String timeStamp,
            String assertString) throws MalformedURLException, SmbException {
        // Directory - Todays date
        logger.info("Searching File from //exlaqa/root/edi/xlator/outbound/");
        String date = LocalDate.now().toString().replaceAll("-", "");
        String url = "smb://exlaqa/root/edi/xlator/outbound/" + date + "/";
        // Login here
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
        SmbFile smbDir = new SmbFile(url, auth);
        for (SmbFile f : smbDir.listFiles()) {
            if (f.getName().contains(fileStartsWith) && f.getName().contains(fbNum)
                    && f.getName().contains(timeStamp)) {
                try {
                    String fileContent = IOUtils.toString(new SmbFileInputStream(f));
                    logger.info("File name: " + f.getName());
                    System.out.println(fileContent);
                    if (fileContent.contains(assertString))
                        Assert.fail("NO SIGNATURE REQUIRED IS NOT SUPPOSE TO BE THERE");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void readerOutboundFileAssertStringIsThereAndL11ProNum(String fileStartsWith, String fbNum, String timeStamp,
            String assertString) throws MalformedURLException, SmbException {
        // Directory - Todays date
        logger.info("Searching File from //exlaqa/root/edi/xlator/outbound/");
        String date = LocalDate.now().toString().replaceAll("-", "");
        String url = "smb://exlaqa/root/edi/xlator/outbound/" + date + "/";
        // Login here
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
        SmbFile smbDir = new SmbFile(url, auth);
        for (SmbFile f : smbDir.listFiles()) {
            if (f.getName().contains(fileStartsWith) && f.getName().contains(fbNum)
                    && f.getName().contains(timeStamp)) {
                try {
                    String fileContent = IOUtils.toString(new SmbFileInputStream(f));
                    logger.info("File name: " + f.getName());
                    System.out.println(fileContent);
                    if (!fileContent.contains(assertString))
                        Assert.fail("NO SIGNATURE REQUIRED IS SUPPOSE TO BE THERE");
                    if (!fileContent.contains("L11*" + fbNum + "*CN~"))
                        Assert.fail("L11*<ProNum>*CN~ IS SUPPOSE TO BE THERE");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public String getOutboundPathToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return "smb://exlaqa/root/edi/xlator/outbound/" + formatter.format(new Date()) + "/";
    }

    public void validateFreightBillOutputHasLine(String proNumber, String timeCreated, String textToBeFound)
            throws SmbException, IOException {
        logger.info("Validate Freight Bill output has this line: [" + textToBeFound + "]");

        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
        SmbFile directory = new SmbFile(getOutboundPathToday(), auth);
        boolean foundFile = false;
        boolean foundLine = false;

        SmbFile e211File = null;
        long mostRecent = 0;
        for (SmbFile f : directory.listFiles()) {

            // Find the file that starts with E211, has the PRO number, and the time created
            if (f.getName().startsWith("E211")) {
//			if (f.getName().startsWith("E211") && f.getName().contains(proNumber)) {
                // check if the difference in seconds is less than 6
                // also check for the most recent

                long datetimeFull = Long.parseLong(f.getName().split("_")[4]);

                int datetimeActual = Integer.parseInt(f.getName().split("_")[4].substring(10, 12));
                int datetimeExepcted = Integer.parseInt(timeCreated.substring(timeCreated.length() - 2));
                int datetimeDiff = datetimeActual - datetimeExepcted;
//				if (datetimeDiff <= 6 || datetimeDiff >= 54 && datetimeFull > mostRecent) {
                if (datetimeFull > mostRecent) {
                    e211File = f;
                    logger.info("Found a more recent E211 file: [" + f.getName() + "]");
                    mostRecent = datetimeFull;
                }

            }
        }
        assertTrue(e211File != null, "Could not find the E211 file with PRO Number: [" + proNumber
                + "] and Time Created: [" + timeCreated + "]");

        Scanner sc = new Scanner(e211File.getInputStream());
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            logger.info("Current line: [" + line + "]");

            if (line.contains(textToBeFound)) {
                foundLine = true;
                break;
            }
        }
        sc.close();

        assertTrue(foundLine, "Could not find the text in the E211 file");

    }

    public void validateFreightBillOutputHasNoSignatureRequired(String proNumber, String timeCreated)
            throws SmbException, IOException {
        validateFreightBillOutputHasLine(proNumber, timeCreated, "AT5***NO SIGNATURE REQ'D_UNATTENDED~");
    }

    public void validateFreightBillOutputHasSignatureRequired(String proNumber, String timeCreated)
            throws SmbException, IOException {
        validateFreightBillOutputHasLine(proNumber, timeCreated, "AT5***SIGNATURE REQUIRED~");
    }

    public void readerOutboundFileAssertEmailIsThereAndL11ProNum(String fileStartsWith, String fbNum, String timeStamp,
            String FHOT, String FHPRO, String name, String email) throws MalformedURLException, SmbException {
        // Directory - Todays date
        logger.info("Searching File from //exlaqa/root/edi/xlator/outbound/");
        String date = LocalDate.now().toString().replaceAll("-", "");
        String url = "smb://exlaqa/root/edi/xlator/outbound/" + date + "/";
        // Login here
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
        SmbFile smbDir = new SmbFile(url, auth);
        for (SmbFile f : smbDir.listFiles()) {
            if (f.getName().contains(fileStartsWith) && f.getName().contains(fbNum)
                    && f.getName().contains(timeStamp)) {
                try {
                    String fileContent = IOUtils.toString(new SmbFileInputStream(f));
                    logger.info("File name: " + f.getName());
                    System.out.println(fileContent);
                    if (!fileContent.contains("L11*" + FHOT + FHPRO + "*CN~"))
                        Assert.fail("L11*<FHOT+FHPRO>*CN~ IS SUPPOSE TO BE THERE");
                    if (!fileContent.contains("G61*CN*" + name + "*EM*" + email + "~"))
                        Assert.fail("G61*CN*<name>*EM*<email>~ SUPPOSE TO BE THERE");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    // Used for https://estesexpress.atlassian.net/browse/QZ-6935
    public void saveOutboundFileToInboundFolder(String fileStartsWith, String fbNum)
            throws MalformedURLException, SmbException {
        String date = LocalDate.now().toString().replaceAll("-", "");
        String url = "smb://exlaqa/root/edi/xlator/outbound/" + date + "/";
        // Login here
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
        SmbFile smbDir = new SmbFile(url, auth);
        for (SmbFile f : smbDir.listFiles()) {
            if (f.getName().contains(fileStartsWith) && f.getName().contains(fbNum)) {
                String fileContent = "";
                try {
                    fileContent = IOUtils.toString(new SmbFileInputStream(f));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logger.info(fileContent);

                // Edit File According to test cases:
                fileContent = fileContent.replaceFirst("\\*02\\*EFMD", "\\*02\\*EXLA");
                fileContent = fileContent.replaceFirst("\\*02\\*EXLA", "\\*02\\*EFMD");
                fileContent = fileContent.replaceFirst("\\*0\\*P\\*:~", "\\*1\\*P\\*}~");
                fileContent = fileContent.replaceFirst("GS\\*BL\\*EXLA\\*EFMD\\*", "GS\\*QM\\*EFMD\\*EXLA\\*");
                fileContent = fileContent.replaceFirst("ST\\*211\\*", "ST\\*214\\*");
                fileContent = fileContent.concat("B10*" + fbNum + "*" + fbNum + "*EFMD~");
                fileContent = fileContent.concat("G62*BR*20220117*R*1441*ET~");
                fileContent = fileContent.concat("AT7*A9*NS***20190117*1138*ET~");
                fileContent = fileContent.concat("AT5*XYZ~");
                fileContent = fileContent.replaceFirst("SE\\*22\\*", "SE\\*13\\*");

                // EOL to Space
                fileContent = fileContent.replaceAll(System.lineSeparator(), " ");
                // Replace <~ > with <~>
                fileContent = fileContent.replaceAll("~ ", "~");
                // EOL Conversion to Unix
                fileContent = fileContent.replaceAll("\\r\\n", "\n");
                fileContent = fileContent.replaceAll("\\r", "\n");
                // TimeStamp for file
                Date date1 = new Date();
                DateFormat format = new SimpleDateFormat("HHmm");
                String t = format.format(date1);
                // Where to save file and Name of file: E214_<OTPRO>_<HHMM>.txt
                String outPath = "smb://exlaqa/root/edi/xlator/inbound/" + "E214_" + fbNum + "_" + t + ".txt";
                // Save to folder
                SmbFile outFile = new SmbFile(outPath, auth);
                try (SmbFileOutputStream smbfos = new SmbFileOutputStream(outFile)) {
                    smbfos.write(fileContent.getBytes());
                    logger.info("file Saved");
                } catch (MalformedURLException e) {
                    throw e;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
    }

    public String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
        // Getting current date
        Calendar cal = Calendar.getInstance();
        // Displaying current date in the desired format
        return sdf.format(cal.getTime());
    }

    public String addDateFourBusinessDaysInFuture() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, M/d/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SATURDAY)
            c.add(Calendar.DATE, +5);
        else if (dayOfWeek == Calendar.SUNDAY)
            c.add(Calendar.DATE, +4);
        else
            c.add(Calendar.DATE, +6);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    /**
     * Place the 990 accept test file on the ESB folder in EstesEDI for processing.
     * 
     * @param otProNumber The PRO number that will be used to build the accept test
     *                    file.
     */
    public void placeThe990AcceptTestFileOnTheESBFolderForProcessing(String otProNumber) {
        String path = "\\\\ricesb\\ESB_UAT\\Active\\Batch\\EstesEDI\\1-inbound\\qa-990-accept-test.txt";
        String dateShort = getTodayDateByFormat("yyMMdd");
        String date = getTodayDateByFormat("yyyyMMdd");

        try (FileWriter fw = new FileWriter(path)) {
            fw.write("ISA*00*          *00*          *02*EFMD           *02*EXLA           *" + dateShort
                    + "*1737*U*00201*000049780*1*P*}~");
            fw.write("GS*GF*EFMD*EXLA*" + date + "*1737*49780*X*004010~");
            fw.write("ST*990*497800001~");
            fw.write("B1*EFMD*1289751176*" + date + "*A~");
            fw.write("N9*AG*EFM/IEX- INTERSTATE EXPRESS ME~");
            fw.write("SE*4*497800001~");
            fw.write("ST*990*497800002~");
            fw.write("B1*EFMD*" + otProNumber + "*" + date + "*A~");
            fw.write("N9*AG*EFM/MEGA NICE TRUCKING LLC~");
            fw.write("SE*4*497800002~");
            fw.write("GE*2*49780~");
            fw.write("IEA*1*000049780~");
        } catch (IOException e) {
            logger.info("Couldn't write to path");
            e.printStackTrace();
        }
        setHardWait(5000);
    }

    /**
     * Verify the processed 990 file is displayed in the EstesEDI 'done' folder.
     * 
     * @author coxda
     * @param otProNumber The PRO number used to verify the file.
     * @param fileName    The file name for 990 files should either be 'accept' or
     *                    'decline'
     * @return The path of the file that was found.
     */
    public String verifyTheProcessed990FileIsDisplayed(String otProNumber, String fileName) {

        String outboundPath = "\\\\ricesb\\ESB_UAT\\Active\\Batch\\EstesEDI\\4-Done";
        File folder = new File(outboundPath);
        FileTime fileTime = FileTime.fromMillis(0);
        File mostRecentFile = null;
        File[] files = folder.listFiles();
        assertTrue(files.length > 0,
                "No files were found in the path: [\\\\ricesb\\ESB_UAT\\Active\\Batch\\EstesEDI\\4-Done]");

        for (File file : folder.listFiles()) {
            Path path = Paths.get(file.getAbsolutePath());

            try {
                BasicFileAttributes attr = java.nio.file.Files.readAttributes(path, BasicFileAttributes.class);
                logger.info("Found a more recent file: " + attr.lastModifiedTime());
                if (path.toFile().getName().contains("qa-990-" + fileName + "-test.txt")
                        && attr.lastModifiedTime().compareTo(fileTime) > 0) {
                    fileTime = attr.lastModifiedTime();
                    mostRecentFile = path.toFile();
                }
            } catch (IOException e) {
                logger.info("Could not get attributes of file");
                e.printStackTrace();
            }
        }
        logger.info(mostRecentFile.getName());
        try (Scanner sc = new Scanner(mostRecentFile)) {

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(otProNumber)) {
                    logger.info("Found file with PRO Number: [" + otProNumber + "]");
                }
            }
        } catch (FileNotFoundException e) {
            logger.info("Could not open file");
            e.printStackTrace();
        }
        return mostRecentFile.getAbsolutePath();
    }

    /**
     * Place the 990 decline test file on the ESB folder in EstesEDI for processing.
     * 
     * @param otProNumber The PRO number that will be used to build the decline test
     *                    file.
     */
    public void placeThe990DeclineTestFileOnTheESBFolderForProcessing(String otProNumber) {
        String path = "\\\\ricesb\\ESB_UAT\\Active\\Batch\\EstesEDI\\1-inbound\\qa-990-decline-test.txt";
        String dateShort = getTodayDateByFormat("yyMMdd");
        String date = getTodayDateByFormat("yyyyMMdd");
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("ISA*00*          *00*          *02*EFMD           *02*EXLA           *" + dateShort
                    + "*1938*U*00201*000049785*1*P*}~");
            fw.write("GS*GF*EFMD*EXLA*" + date + "*1938*49785*X*004010~");
            fw.write("ST*990*497850001~");
            fw.write("B1*EFMD*" + otProNumber + "*" + date + "*D~");
            fw.write("N9*AG*EFM/APTUS TRANSPORTATION~");
            fw.write("SE*4*497850001~");
            fw.write("GE*1*49785~");
            fw.write("IEA*1*000049785~");
        } catch (IOException e) {
            logger.info("Couldn't write to path");
            e.printStackTrace();
        }
        setHardWait(5000);
    }

    public void verifyFileContains(String fileStartsWith, String fbNum, String timeStamp, String[] assertString)
            throws IOException {
        // Directory - Todays date
        logger.info("Searching File from //exlaqa/root/edi/xlator/outbound/");
        String date = LocalDate.now().toString().replaceAll("-", "");
        boolean found = false;
        String url = "smb://exlaqa/root/edi/xlator/outbound/" + date + "/";
        // Login here
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "QATSTFRTBL", "QATEST2019");
        SmbFile smbDir = new SmbFile(url, auth);
        for (SmbFile f : smbDir.listFiles()) {
            if (f.getName().toString().startsWith(fileStartsWith + fbNum)) {
                logger.info("Name of identified file: " + f.getPath());
                try {
                    String fileContent = IOUtils.toString(new SmbFileInputStream(f), StandardCharsets.UTF_8.name());
                    int numOfTrueAsserts = 0;
                    for (int i = 0; i < assertString.length; i++) {
                        if (fileContent.contains(assertString[i])) {
                            numOfTrueAsserts++;
                        }
                        if (numOfTrueAsserts == assertString.length) {
                            logger.info("All Strings have been identified in file body");
                            found = true;
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
        Assert.assertTrue(found);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        logger.info("switched to default content");
    }

    public String addOrSubstractDateFromTodayDateFormat(int NoOfAddOrSubtractDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
        // Getting current date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, NoOfAddOrSubtractDay);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }

    public String formatDate(String dte, String format) throws Exception {
        Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(dte);
        SimpleDateFormat DateFor = new SimpleDateFormat(format);
        String stringDate = DateFor.format(date1);
        System.out.println("Date Format with " + format + ": " + stringDate);
        return stringDate;

    }

    public String changeDateFormat(String date, String actFormat, String expFormat) throws ParseException {

        DateFormat formatter = new SimpleDateFormat(actFormat);
        Date dt = (Date) formatter.parse(date);
        SimpleDateFormat newFormat = new SimpleDateFormat(expFormat);
        String finalDate = newFormat.format(dt);
        return finalDate;
    }

    public void verifyReconcilationBySID(SessionVt session, String pickUpNumber)
            throws JagacyException, InterruptedException {
        logger.info("Validate if 'Pickup System - Reconciliations by SID' is visible");
        int i = 7;
        String value = null;
        boolean found = false;
        try {
            System.out.println(session.readPosition(1, 21, 38).toString().trim());
            if (session.readPosition(1, 21, 38).toString().trim()
                    .equalsIgnoreCase("Pickup System - Reconciliations by SID")) {
                do {
                    value = session.readPosition(i + 1, 7, 11).trim();
                    setHardWait(1000);
                    session.writeCursor(i, 1);
                    if (value.equals(pickUpNumber) || value.equals("")) {
                        session.writeCursor(i, 1);
                        session.writePosition(i, 1, "X");
                        setHardWait(500);
                        session.writeKey(Key.ENTER);
                        found = true;
                    } else if (i == 19) {
                        session.writeCursor(20, 1);
                        session.readPosition(20, 78, 2000).trim().equals("+");
                        setHardWait(500);
                        session.writeKey(Key.PAGE_DOWN);
                    }
                    setHardWait(1000);
                    i = (i < 19) ? (i += 2) : 7;
                } while (!found);
            }
        } catch (JagacyException e) {
            logger.info("Screen is not visible, continue to next line of code.");

        }
    }

    // get time stamp

    public String getTimestamp() {

        logger.info("Get time stamp");

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String str = ts.toString();
        return str;

    }

    public String generateAuthenticationToken(String username, String password, String apiKey)
            throws InterruptedException {
        String baseURI = "https://uat-cloudapi.estes-express.com/authenticate";
        Response response = given().auth().basic(username, password)
                .header("apikey", apiKey)
                .when()
                .post(baseURI)
                .then()
                .log().all()
                .extract().response();
        String authenticationToken = response.path("token");
        Thread.sleep(2000);
        try {
            assertNotNull(authenticationToken);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Authentication token is " + authenticationToken);
        return authenticationToken;

    }

    public File base64ToPdf(String b64, String fileName) {
        File file = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file);) {

            byte[] decoder = Base64.getDecoder().decode(b64);

            fos.write(decoder);
            System.out.println("PDF File Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * <P>
     * Updated method which is a work around when the Calendar object doesn't
     * transition to the next month when searching for the next weekend value
     * </P>
     */
    public String enterNextWeekendDay() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i <= 7; i++) {
            cal.add(Calendar.DATE, i);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                String weekend = sdf.format(cal.getTime());
                // System.out.println(weekend);
                return weekend;
            }

        }
        return updateToNextMonth();

    }

    // This helper method allows a workaround when selectNextWeekendDay() cannot
    // obtain the next weekend day value, this will add another month and search the
    // next 7 days.
    public static String updateToNextMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        for (int i = 0; i <= 7; i++) {
            cal.set(Calendar.DATE, i + 1);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                String weekend = sdf.format(cal.getTime());
                // System.out.println(weekend);
                return weekend;
            }
        }
        return null;
    }

    // Get future business date from today's Date
    public String addFutureWeekDay2() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String todaysDate = dateFormat.format(now);

        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(todaysDate));

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.THURSDAY)
            c.add(Calendar.DATE, +11);
        else if (dayOfWeek == Calendar.FRIDAY)
            c.add(Calendar.DATE, +10);
        else
            c.add(Calendar.DATE, +9);

        String newDate = dateFormat.format(c.getTime());
        return newDate;
    }

    // highlight element before click action

    public void highlightElementBeforeClickAction(WebElement ele) {
        logger.info("Element highlighted before click action");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border:2px solid red;background:yellow')", ele);
    }

    public void switchWindow(Set<String> obj, String parentWindow) {
        obj.stream()
                .filter(window -> !(window.equals(parentWindow)))
                .map(window -> driver.switchTo().window(window))
                .findFirst();
    }

    public WebElement selectNthPath(String path, int timeoutSeconds, int n) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = (WebElement) js.executeScript("function getElementsByXPath(xpath, parent) {\r\n"
                + "    let results = [];\r\n" + "    let query = document.evaluate(xpath, parent || document,\r\n"
                + "        null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);\r\n"
                + "    for (let i = 0, length = query.snapshotLength; i < length; ++i) {\r\n"
                + "        results.push(query.snapshotItem(i));\r\n" + "    }\r\n" + "    return results;\r\n" + "}\r\n"
                + "\r\n" + "const elementOnPage = (query, timeout) => {\r\n"
                + "    return new Promise((resolve, reject) => {\r\n" + "        const startTime = Date.now();\r\n"
                + "        const tryQuery = () => {\r\n" + "            const elem = getElementsByXPath(query);\r\n"
                + "            console.log(elem)\r\n" + "            if (elem) resolve(elem);\r\n"
                + "            else if (Date.now() - startTime > timeout) resolve(null);\r\n"
                + "            else { setTimeout(tryQuery, 200); scroll(-100, 100); };\r\n" + "        }\r\n"
                + "        tryQuery();\r\n" + "    });\r\n" + "}\r\n" + "\r\n"
                + "let promise = elementOnPage(arguments[0], arguments[1])\r\n" + "let p = Object.values(promise);\r\n"
                + "let element = p[1][arguments[2]];\r\n" + "return element;\r\n" + "", path, timeoutSeconds, n);
        return e;
    }

    // new method with filterPath
    public WebElement filterXpath(String xpath, WebElement element, WebElement parentElement, int numberOfChildElements,
            String... filters) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        WebElement ele = (WebElement) js.executeScript("let allFalse = (e, filters) => {\r\n"
                + "    let falseArray = [];\r\n"
                + "    filters.forEach((n) => {\r\n"
                + "        if (!e.outerHTML.includes(n.substring(1))) {\r\n"
                + "            falseArray.push(\"false\");\r\n"
                + "        }\r\n"
                + "    })\r\n"
                + "    if (falseArray.length == filters.length) {\r\n"
                + "        return true;\r\n"
                + "    }\r\n"
                + "    else { return false }\r\n"
                + "};\r\n"
                + "\r\n"
                + "let allTrue = (e, trueFilters) => {\r\n"
                + "    let trueArray = [];\r\n"
                + "    trueFilters.forEach((n) => {\r\n"
                + "        if (e.outerHTML.includes(n)) {\r\n"
                + "            trueArray.push(\"true\");\r\n"
                + "        }\r\n"
                + "    })\r\n"
                + "    if (trueArray.length == trueFilters.length) {\r\n"
                + "        return true;\r\n"
                + "    }\r\n"
                + "    else { return false }\r\n"
                + "};\r\n"
                + "\r\n"
                + "const query = (path, webElement, parent, children, filters) => {\r\n"
                + "    return new Promise((resolve, reject) => {\r\n"
                + "        let filterArray = Object.values(filters);\r\n"
                + "        if (webElement != null) {\r\n"
                + "            filterArray.push(webElement.outerHTML);\r\n"
                + "        }\r\n"
                + "        const startTime = Date.now();\r\n"
                + "        const containsArray = filterArray.filter((n) => {\r\n"
                + "            if (n.charAt(0) != \"!\")\r\n"
                + "                return n;\r\n"
                + "        });\r\n"
                + "        const containsNOTArray = filterArray.filter((n) => {\r\n"
                + "            if (n.charAt(0) == \"!\") {\r\n"
                + "                return n;\r\n"
                + "            }\r\n"
                + "        });\r\n"
                + "        console.log(\"containsNOTArray: \" + containsNOTArray)\r\n"
                + "        const tryQuery = () => {\r\n"
                + "            function getElementsByXPath(path, parent) {\r\n"
                + "                let results = [];\r\n"
                + "                let query = document.evaluate(path, parent || document.body,\r\n"
                + "                    null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);\r\n"
                + "                for (let i = 0, length = query.snapshotLength; i < length; ++i) {\r\n"
                + "                    results.push(query.snapshotItem(i));\r\n"
                + "                }\r\n"
                + "                return results;\r\n"
                + "            }\r\n"
                + "            if (path != null) {\r\n"
                + "                const elements = getElementsByXPath(path, parent);\r\n"
                + "                const elem = elements.find(el => {\r\n"
                + "                    if (el.children.length == children) {\r\n"
                + "                        if (allTrue(el, containsArray) && allFalse(el, containsNOTArray)) {\r\n"
                + "                            return el;\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                });\r\n"
                + "                if (elem) resolve(elem);\r\n"
                + "                else if (Date.now() - startTime > (60000)) resolve(null);\r\n"
                + "                else {\r\n"
                + "                    setTimeout(tryQuery, 1000);\r\n"
                + "                }\r\n"
                + "            } else {\r\n"
                + "                if (parent != null) { path = \".//*\" } else { path = \"//*\" }\r\n"
                + "                const elements = getElementsByXPath(path, parent);\r\n"
                + "                const elem = elements.find(el => {\r\n"
                + "                    if (el.children.length == children && el.outerHTML == webElement.outerHTML) {\r\n"
                + "                        if (allTrue(el, containsArray) && allFalse(el, containsNOTArray)) {\r\n"
                + "                            return el;\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                });\r\n"
                + "                if (elem) resolve(elem);\r\n"
                + "                else if (Date.now() - startTime > (60000)) resolve(null);\r\n"
                + "                else {\r\n"
                + "                    setTimeout(tryQuery, 1000);\r\n"
                + "                }\r\n"
                + "            }\r\n"
                + "        }\r\n"
                + "        tryQuery();\r\n"
                + "    });\r\n"
                + "};\r\n"
                + "\r\n"
                + "\r\n"
                + "const element = await query(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);\r\n"
                + "\r\n"
                + "return element;", xpath, element, parentElement, numberOfChildElements, filters);
        return ele;

    };

    // new method
    public WebElement filterSelector(String cssSelector, WebElement element, WebElement parentElement,
            int numberOfChildElements, String... filters) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        WebElement ele = (WebElement) js.executeScript("let allFalse = (e, filters) => {\r\n"
                + "    let falseArray = [];\r\n"
                + "    filters.forEach((n) => {\r\n"
                + "        if (!e.outerHTML.includes(n.substring(1))) {\r\n"
                + "            falseArray.push(\"false\");\r\n"
                + "        }\r\n"
                + "    })\r\n"
                + "    if (falseArray.length == filters.length) {\r\n"
                + "        return true;\r\n"
                + "    }\r\n"
                + "    else { return false }\r\n"
                + "};\r\n"
                + "\r\n"
                + "let allTrue = (e, trueFilters) => {\r\n"
                + "    let trueArray = [];\r\n"
                + "    trueFilters.forEach((n) => {\r\n"
                + "        if (e.outerHTML.includes(n)) {\r\n"
                + "            trueArray.push(\"true\");\r\n"
                + "        }\r\n"
                + "    })\r\n"
                + "    if (trueArray.length == trueFilters.length) {\r\n"
                + "        return true;\r\n"
                + "    }\r\n"
                + "    else { return false }\r\n"
                + "};\r\n"
                + "\r\n"
                + "const query = (path, webElement, parent, children, filters) => {\r\n"
                + "    return new Promise((resolve, reject) => {\r\n"
                + "        let filterArray = Object.values(filters);\r\n"
                + "        if (webElement != null) {\r\n"
                + "            filterArray.push(webElement.outerHTML);\r\n"
                + "        }\r\n"
                + "        const startTime = Date.now();\r\n"
                + "        const containsArray = filterArray.filter((n) => {\r\n"
                + "            if (n.charAt(0) != \"!\")\r\n"
                + "                return n;\r\n"
                + "        });\r\n"
                + "        const containsNOTArray = filterArray.filter((n) => {\r\n"
                + "            if (n.charAt(0) == \"!\") {\r\n"
                + "                return n;\r\n"
                + "            }\r\n"
                + "        });\r\n"
                + "        console.log(\"containsNOTArray: \" + containsNOTArray)\r\n"
                + "        const tryQuery = () => {\r\n"
                + "            function getElementsBySelector(path, parent) {\r\n"
                + "                let par = document.body;\r\n"
                + "                if (parent != null) {\r\n"
                + "                    par = parent;\r\n"
                + "                }\r\n"
                + "                let results = [];\r\n"
                + "                let query = par.querySelectorAll(path);\r\n"
                + "                for (let i = 0, length = query.length; i < length; ++i) {\r\n"
                + "                    results.push(query[i]);\r\n"
                + "                }\r\n"
                + "                return results;\r\n"
                + "            }\r\n"
                + "\r\n"
                + "            if (path != null) {\r\n"
                + "                const elements = getElementsBySelector(path, parent);\r\n"
                + "                const elem = elements.find(el => {\r\n"
                + "                    if (el.children.length == children) {\r\n"
                + "                        if (allTrue(el, containsArray) && allFalse(el, containsNOTArray)) {\r\n"
                + "                            return el;\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                });\r\n"
                + "                if (elem) resolve(elem);\r\n"
                + "                else if (Date.now() - startTime > (60000)) resolve(null);\r\n"
                + "                else setTimeout(tryQuery, 1000);\r\n"
                + "            } else {\r\n"
                + "                const elements = getElementsBySelector(\"*\", parent);\r\n"
                + "                const elem = elements.find(el => {\r\n"
                + "                    if (el.children.length == children && el.outerHTML == webElement.outerHTML) {\r\n"
                + "                        if (allTrue(el, containsArray) && allFalse(el, containsNOTArray)) {\r\n"
                + "                            return el;\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                });\r\n"
                + "                if (elem) resolve(elem);\r\n"
                + "                else if (Date.now() - startTime > (60000)) resolve(null);\r\n"
                + "                else setTimeout(tryQuery, 1000);\r\n"
                + "            }\r\n"
                + "        }\r\n"
                + "        tryQuery();\r\n"
                + "    });\r\n"
                + "};\r\n"
                + "\r\n"
                + "const element = await query(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);\r\n"
                + "return element;", cssSelector, element, parentElement, numberOfChildElements, filters);
        return ele;

    }

    public WebElement filterXpathTimout(String path, WebElement pomElement, WebElement parentElement,
            int numberOfChildElements, int timeout, String... filters) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        WebElement e = (WebElement) js.executeScript("let allFalse = (e, filters) => {\r\n"
                + "    let falseArray = [];\r\n"
                + "    filters.forEach((n) => {\r\n"
                + "        if (!e.outerHTML.includes(n.substring(1))) {\r\n"
                + "            falseArray.push(\"false\");\r\n"
                + "        }\r\n"
                + "    })\r\n"
                + "    if (falseArray.length == filters.length) {\r\n"
                + "        return true;\r\n"
                + "    }\r\n"
                + "    else { return false }\r\n"
                + "};\r\n"
                + "\r\n"
                + "let allTrue = (e, trueFilters) => {\r\n"
                + "    let trueArray = [];\r\n"
                + "    trueFilters.forEach((n) => {\r\n"
                + "        if (e.outerHTML.includes(n)) {\r\n"
                + "            trueArray.push(\"true\");\r\n"
                + "        }\r\n"
                + "    })\r\n"
                + "    if (trueArray.length == trueFilters.length) {\r\n"
                + "        return true;\r\n"
                + "    }\r\n"
                + "    else { return false }\r\n"
                + "};\r\n"
                + "\r\n"
                + "const query = (path, webElement, parent, children, timeout, filters) => {\r\n"
                + "    return new Promise((resolve, reject) => {\r\n"
                + "        let filterArray = Object.values(filters);\r\n"
                + "        if (webElement != null) {\r\n"
                + "            filterArray.push(webElement.outerHTML);\r\n"
                + "        }\r\n"
                + "        const startTime = Date.now();\r\n"
                + "        const containsArray = filterArray.filter((n) => {\r\n"
                + "            if (n.charAt(0) != \"!\")\r\n"
                + "                return n;\r\n"
                + "        });\r\n"
                + "        const containsNOTArray = filterArray.filter((n) => {\r\n"
                + "            if (n.charAt(0) == \"!\") {\r\n"
                + "                return n;\r\n"
                + "            }\r\n"
                + "        });\r\n"
                + "        console.log(\"containsNOTArray: \" + containsNOTArray)\r\n"
                + "        const tryQuery = () => {\r\n"
                + "            function getElementsByXPath(path, parent) {\r\n"
                + "                let results = [];\r\n"
                + "                let query = document.evaluate(path, parent || document.body,\r\n"
                + "                    null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);\r\n"
                + "                for (let i = 0, length = query.snapshotLength; i < length; ++i) {\r\n"
                + "                    results.push(query.snapshotItem(i));\r\n"
                + "                }\r\n"
                + "                return results;\r\n"
                + "            }\r\n"
                + "            if (path != null) {\r\n"
                + "                const elements = getElementsByXPath(path, parent);\r\n"
                + "                const elem = elements.find(el => {\r\n"
                + "                    if (el.children.length == children) {\r\n"
                + "                        if (allTrue(el, containsArray) && allFalse(el, containsNOTArray)) {\r\n"
                + "                            return el;\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                });\r\n"
                + "                if (elem) resolve(elem);\r\n"
                + "                else if (Date.now() - startTime > (timeout)) resolve(null);\r\n"
                + "                else {\r\n"
                + "                    setTimeout(tryQuery, 1000);\r\n"
                + "                }\r\n"
                + "            } else {\r\n"
                + "                if (parent != null) { path = \".//*\" } else { path = \"//*\" }\r\n"
                + "                const elements = getElementsByXPath(path, parent);\r\n"
                + "                const elem = elements.find(el => {\r\n"
                + "                    if (el.children.length == children && el.outerHTML == webElement.outerHTML) {\r\n"
                + "                        if (allTrue(el, containsArray) && allFalse(el, containsNOTArray)) {\r\n"
                + "                            return el;\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                });\r\n"
                + "                if (elem) resolve(elem);\r\n"
                + "                else if (Date.now() - startTime > (timeout)) resolve(null);\r\n"
                + "                else {\r\n"
                + "                    setTimeout(tryQuery, 1000);\r\n"
                + "                }\r\n"
                + "            }\r\n"
                + "        }\r\n"
                + "        tryQuery();\r\n"
                + "    });\r\n"
                + "};\r\n"
                + "\r\n"
                + "\r\n"
                + "const element = await query(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5]);\r\n"
                + "\r\n"
                + "return element;", path, pomElement, parentElement, numberOfChildElements, timeout, filters);
        return e;

    }

    // JavaScript executor method for getting text innerHTML

    public String getTextFromElement(WebElement elementText) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String s = (String) js.executeScript("let text = arguments[0].textContent;\r\n" + "return text;", elementText);
        setHardWait(1000);
        return s;
    }

    public void setAngularInputValue(WebElement inputFieldElement, String desiredValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];\n"
                + "arguments[0].dispatchEvent(new Event('input'));\n"
                + "arguments[0].dispatchEvent(new Event('blur'));", inputFieldElement, desiredValue);
    }

    public void verifyElementHasTextContent(WebElement textElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean b = (boolean) js.executeScript(
                "let text = arguments[0].innerText;\r\n"
                        + "let checkForText = (textElement) => {\r\n"
                        + "    let boolean = false;\r\n"
                        + "if (textElement.replace(/\\s/g, \"\").length >= 1) {\r\n"
                        + "    boolean = true;\r\n"
                        + "} return boolean;\r\n"
                        + "}\r\n"
                        + "return checkForText(text);",
                textElement);
        setHardWait(200);
        Assert.assertTrue(b, "Text content present in element.");
    }

    public WebElement pollDOM(String xpath, WebElement element, int timeoutSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        WebElement ele = (WebElement) js.executeScript("const elementOnPage = (query, webElement, timeout) => {\n"
                + "\n"
                + "    return new Promise((resolve, reject) => {\n"
                + "\n"
                + "        const startTime = Date.now();\n"
                + "\n"
                + "        const tryQuery = () => {\n"
                + "            if (webElement == null) {\n"
                + "            let elem = document.evaluate(query, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\n"
                + "            if (elem) resolve(elem);\n"
                + "            else if (Date.now() - startTime > timeout) resolve(null);\n"
                + "            else { setTimeout(tryQuery, 200); scroll(-100, 100); };}\n"
                + "            else {\n"
                + "                let elem = webElement;\n"
                + "                if (elem) resolve(elem);\n"
                + "                else if (Date.now() - startTime > timeout) resolve(null);\n"
                + "                else { setTimeout(tryQuery, 200); scroll(-100, 100); };\n"
                + "            }\n"
                + "        }\n"
                + "        tryQuery();\n"
                + "    });\n"
                + "}\n"
                + "const selector = arguments[0];\n"
                + "const webElement = arguments[1];\n"
                + "const tm = arguments[2] * 1000;\n"
                + "const element = await elementOnPage(selector, webElement, tm);\n"
                + "return element;", xpath, element, timeoutSeconds);
        return ele;

    }

    public String getCurrentHourMinuteTime() {
        logger.info("Get current hour and minute time stamp with a default to east coast time");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("ET"));
        SimpleDateFormat formatter = new SimpleDateFormat("h" + ":" + "mm" + " aa");
        String time = formatter.format(calendar.getTime());
        logger.info("Current Time: " + time);
        return time;
    }

    // select Todays date

    public String selectTodaysDate() {
        logger.info("Selecting todays date");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        String todaysDate = sdf.format(cal.getTime());
        return todaysDate;
    }

    // this method is using awaitility class

    public void awaitSpinningWheel(WebElement button) {
        System.out.println("Wait for the spinning wheel to disappear.");

        scrollIntoView(button);
        WebElement spinningWheel = filterXpathTimout("//i", null, button, 0, 3000, "fa-spin");

        if (spinningWheel == null) {
        } else {
            try {
                await("Timeout: The wheel has spun for an unreasonable amount of time.").atMost(120, TimeUnit.SECONDS)
                        .until(spinningWheel::isDisplayed, is(false));
            } catch (StaleElementReferenceException e) {
                System.out.println("The spinning wheel went stale.");
            }
        }
    }

    public void keyboard(String... keys) {

        String args = String.join(",", keys);
        ProcessBuilder processBuilder = new ProcessBuilder("python/python", "python/keystrokes.py", args);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void writeString(String string) {

        ProcessBuilder processBuilder = new ProcessBuilder("python/python", "-u", "python/writeString.py", string);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void executePythonScript(String scriptName, String... arguments) {

        if (arguments.length != 0) {
            String args = String.join(",", arguments);
            ProcessBuilder processBuilder = new ProcessBuilder("python/python", "-u", "python/" + scriptName, args);
            processBuilder.redirectErrorStream(true);
            try {
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String readline;
                int i = 0;
                while ((readline = reader.readLine()) != null) {
                    System.out.println(++i + " " + readline);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            ProcessBuilder processBuilder = new ProcessBuilder("python/python", "python/" + scriptName);
            processBuilder.redirectErrorStream(true);
            try {
                Process process = processBuilder.start();
                try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public String executePythonReturnValue(String scriptName, String... arguments) {
        String elementText = null;
        if (arguments.length != 0) {
            String args = String.join(",", arguments);
            ProcessBuilder processBuilder = new ProcessBuilder("python/python", "-u", "python/" + scriptName, args);
            processBuilder.redirectErrorStream(true);

            try {
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String readline;

                ArrayList<String> theList = new ArrayList<String>();
                while ((readline = reader.readLine()) != null) {
                    theList.add(readline);
                }
//                System.out.println(theList.toString());
                for (String item : theList) {
                    if (item.contains("myVariable")) {
                        elementText = StringUtils.removeStart(item, StringUtils.left(item, 10));
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            ProcessBuilder processBuilder = new ProcessBuilder("python/python", "python/" + scriptName);
            processBuilder.redirectErrorStream(true);
            try {
                Process process = processBuilder.start();
                try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return elementText;
    }

    public void runScript(String fileName) {

        ProcessBuilder processBuilder = new ProcessBuilder("python/python", "-u", "python/" + fileName);
        processBuilder.redirectErrorStream(true);

        try {

            Process p = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String readline;
            int i = 0;
            String text;
            while ((readline = reader.readLine()) != null) {
                System.out.println(++i + " " + readline);
                if (i == 1) {
                    text = readline;
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pyClickElement(String... arguments) {
        executePythonScript("clickElement.py", arguments);
    }

    public void printIdentifiers(String...arguments) {
        executePythonScript("printControls.py", arguments);
    }

    public String pyGetText(String...arguments) {
        String text = executePythonReturnValue("getText.py", arguments);
        return text;
    }
    
    public String pySendKeys(String...arguments) {
        String text = executePythonReturnValue("pySendKeys.py", arguments);
        return text;
    }

}
