package weightAndResearchInquiryPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import testBase.TestBase;

public class WeightAndResearchResultPage extends TestBase {

	private Logger logger = Logger.getLogger(WeightAndResearchResultPage.class);

	@FindBy(how = How.XPATH, using = "//input[@value='171-0472161']")
	private WebElement checkBox;

	@FindBy(how = How.XPATH, using = "//input[@value='Submit']")
	private WebElement Submit;

	public void validateWeightAndResearchResult() {
		logger.info("validate Weight and Research Result");
		testUtil.init(this);
		testUtil.verifyAndPrintWebTableData("//form[@name='proForm']//table[@class='output-table']");
		
	}

	public void selectPROCheckBox() {
		logger.info("select check box");
		testUtil.init(this);
		checkBox.click();
	}

	public void clickOnSubmitButton() {
		logger.info("click on submit button");
		testUtil.init(this);
		Submit.click();
	}

}
