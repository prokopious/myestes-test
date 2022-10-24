package eNetPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class ENetMyEstesUserDetailPage extends TestBase {

	private Logger logger = Logger.getLogger(ENetMyEstesUserDetailPage.class);

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Edit Menu Items')]")
	private WebElement EditMenuItems;
	
	@FindBy(css="[action*='addMenuItem'] tbody tbody tr")
	private List<WebElement> menuItems;
	
	@FindBy(name = "menuItem")
	private WebElement selectMenuItem;
	
	@FindBy(id="submit")
	private WebElement addButton;
	
	public void verifyTitle() {
		logger.info(" Verify page title");
		driver.switchTo().defaultContent();
		String title = driver.findElement(By.id("paneTitle")).getText();
		System.out.println(title);
	}

	public void clickOnEditMenuItemsLink() throws InterruptedException {
		testUtil.init(this);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("fullViewIframe")));
		testUtil.setHardWait(1000);
		logger.info("Click On Edit Menu Items Link");
		EditMenuItems.click();
		testUtil.setHardWait(1000);
	}

	public void removeCodeA55() throws InterruptedException {
		testUtil.init(this);
		logger.info("Remove the Code A55");
		List<WebElement> we = driver
				.findElements(By.xpath("//body//tr[2]//tr[*]"));
		int listSize = we.size();
		System.out.println("Element size : " + listSize);
		Thread.sleep(200);
		for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);


			if (eleName.equals("A55 Time Critical Rate Quote")) {
				int j = i + 1;

				driver.findElement(By.xpath(
						"//tr[" + j + "]//td[3]//center[1]//a[1]//img[1]"))
						.click();
				Thread.sleep(500);
				driver.switchTo().alert().accept();
				System.out.println("REMOVED");
				//break;
			}else {
				
			break;}
		}

		/*for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);

			if (!eleName.equals("A55 Time Critical Rate Quote")) {
				//int j = i + 1;

				driver.findElement(By.xpath("//input[@id='submit']")).click();
				Thread.sleep(500);
				System.out.println("ADDED");
				//break;
			}
			break;
		}*/
		
	}

	public void removeCodeA70() throws InterruptedException {
		testUtil.init(this);
		logger.info("remove the Code A70");
		List<WebElement> we = driver.findElements(By.xpath("//body//tr[2]//tr[*]"));
		int listSize = we.size();
		System.out.println("Element size : " + listSize);
		Thread.sleep(200);
		for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);

			// System.out.println(i);

			if (eleName.equals("A70 VTL Rate Quote")) {
				int j = i + 1;

				driver.findElement(By.xpath(
						"//tr[" + j + "]//td[3]//center[1]//a[1]//img[1]"))
						.click();
				Thread.sleep(500);
				driver.switchTo().alert().accept();
				System.out.println("REMOVED");
				//break;
			}
			//break;
		}

		/*for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);

			if (!eleName.equals("A70 VTL Rate Quote")) {
				//int j = i + 1;

				driver.findElement(By.xpath("//input[@id='submit']")).click();
				System.out.println("ADDED");
				//break;
			}
			
		}*/
		
		
		
}
	
	public void addRateQuote(String rateQuote) throws InterruptedException {
		logger.info("add rate quote");
		List<WebElement> we = driver.findElements(By.xpath("//body//tr[2]//tr[*]"));
		int listSize = we.size();
		Thread.sleep(200);
		for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();
			System.out.println("Name of Element is : " + eleName);

			if (eleName.equals(rateQuote)) {
				//int j = i + 1;
				System.out.println("exist");
				
				//break;
			}else {
				WebElement menu=driver.findElement(By.name("menuItem"));
				testUtil.selectUsingVisibleText(menu, rateQuote);
				driver.findElement(By.xpath("//input[@id='submit']")).click();
				System.out.println("ADDED");
			}
			
		}
	}
	
	//Add specified code
	public void addCode(String code, String rateQuote) throws InterruptedException {
		logger.info("Add rate quote: " + rateQuote);
		List<WebElement> we = driver.findElements(By.xpath("//body//tr[2]//tr[*]"));
		int listSize = we.size();
		Thread.sleep(200);

		String value = null;

		for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();

			if (eleName.contains(code)) {
				value = "Found";
				System.out.println("exist");
				break;
			} else {
				value = "Not Found";
			}
		}

		if (value.equals("Not Found")) {
			WebElement menu = driver.findElement(By.name("menuItem"));
			testUtil.selectUsingVisibleText(menu, code + " - " + rateQuote);
			driver.findElement(By.xpath("//input[@id='submit']")).click();
			System.out.println("ADDED");
			testUtil.setHardWait(2000);
		}
	}
		
	//Remove specified Code - A55 Time Critical Rate Quote
	public void removeCode(String code) throws InterruptedException {
		testUtil.init(this);
		logger.info("Remove the Code " + code);
		List<WebElement> we = driver.findElements(By.xpath("//body//tr[2]//tr[*]"));

		int listSize = we.size();
		System.out.println("Element size : " + listSize);
		Thread.sleep(200);

		for (int i = 1; i < listSize; i++) {
			String eleName = we.get(i).getText();

			if (eleName.equals(code)) {
				int j = i + 1;

				driver.findElement(By.xpath("//tr[" + j + "]//td[3]//center[1]//a[1]//img[1]")).click();
				Thread.sleep(500);
				driver.switchTo().alert().accept();
				System.out.println("REMOVED");
				testUtil.setHardWait(2000);
				break;
			}
		}
	}
	
	

	public boolean verifyDisplayedMenuItems(String code) {
		logger.info("Verifying displayed Menu Items.");
		testUtil.init(this);
		boolean flagVal = false;
		for(int i=0;i<menuItems.size();i++) {
			String menuCode = testUtil.getWebElement(menuItems.get(i), "td:nth-child(1)").getText().trim();
			if(menuCode.equalsIgnoreCase(code)) {
				logger.info("Menu item: "+code+ " is displayed in the table");
				flagVal = true;
				break;
			}
		}  return flagVal;
	}
	
	public void addMenuItems(String code) {
		logger.info("Adding menu item: "+code);
		testUtil.init(this);
		testUtil.setHardWait(1000);
		String[] Itemcode = code.split("-"); 
		boolean existence = verifyDisplayedMenuItems(Itemcode[0].trim());
		if(existence==false) {
			logger.info("Trying to add Menu Item: "+code);
			testUtil.selectUsingVisibleText(selectMenuItem, code);
			testUtil.setHardWait(1000);
			addButton.click();
		} else {
			logger.info("Menu Item: "+code+" already exist in the table");
		}
	}
	
	public void deleteMenuItem(String code) {
		logger.info("Deleting menu item: "+code);
		testUtil.init(this);
		testUtil.setHardWait(2000);
		String[] Itemcode = code.split("-"); 
		boolean existence = verifyDisplayedMenuItems(Itemcode[0].trim());
		if(existence==true) {
			for(int i=0;i<menuItems.size();i++) {
				String menuCode = testUtil.getWebElement(menuItems.get(i), "td:nth-child(1)").getText().trim();
				if(menuCode.equalsIgnoreCase(Itemcode[0].trim())) {
					logger.info("Trying to delete Menu Item: "+code);
					testUtil.getWebElement(menuItems.get(i), "td:nth-child(3)").click();
					testUtil.setHardWait(2000);
					testUtil.switchToAlertAndAccept();
					break;
				}
			}
		} else {
			logger.info("Menu Item: "+code+" already deleted from the table");
		}
	}	

}
