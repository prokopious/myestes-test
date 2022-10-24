package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testBase.TestBase;

public class ExcelReaderAndWrite extends TestBase {

	public Object[][] readExcel(String filePath, String fileName,
			String sheetName) throws IOException {
		String[][] data = null;
		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook Workbook = null;

		// Find the file extension by splitting file name in substring and
		// getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet Sheet = Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it
		int totalNoOfCols = Sheet.getRow(0).getLastCellNum();
		data = new String[rowCount][totalNoOfCols];
		for (int i = 0; i < rowCount + 1; i++) {

			if (i > 0) {
				Row row = Sheet.getRow(i);
				if (i == 1) {
					Row row2 = Sheet.getRow(0);
					System.out.print(
							row2.getCell(0).getStringCellValue() + "|| ");
				}
				// Create a loop to print cell values in a row
				data[i - 1][0] = row.getCell(0).getStringCellValue();
				System.out.print(row.getCell(0).getStringCellValue() + "|| ");

				

				System.out.println();
			}
		}
		return data;

	}

	public void writeExcel(String filePath, String fileName, String sheetName,
			String qNumExcel, String newQuote,String Anum, String result)
			throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook Workbook = null;

		// Find the file extension by splitting file name in substring and
		// getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			Workbook = new HSSFWorkbook(inputStream);

		}

		// Read excel sheet by sheet name

		Sheet sheet = Workbook.getSheet(sheetName);

		// Get the current count of rows in excel file

		// int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		int rowCount = sheet.getLastRowNum();
		// Get the first row from the sheet

		

		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			if (i > 0) {
				
				String excelCol1Data = row.getCell(0).getStringCellValue()
						.trim();
				if (qNumExcel.contains(excelCol1Data)) {
					//System.out.println("");
					int colNum = row.getLastCellNum() + 3;
					for (int j = 0; j < colNum; j++) {
						if (j > 0) {
							if (j == 1) {
								Cell cell = row.createCell(j);
								cell.setCellValue(newQuote);
							}
							if (j == 2) {
								Cell cell = row.createCell(j);
								cell.setCellValue(Anum);
							}
							if (j ==3 ) {
								Cell cell = row.createCell(j);
								cell.setCellValue(result);
							}
						}
					}
				}
			}
		}

		

		// Close input stream

		inputStream.close();

		// Create an object of FileOutputStream class to create write data in
		// excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file

		Workbook.write(outputStream);

		// close output stream

		outputStream.close();

	}

	// Main function is calling readExcel function to read data from excel file

	/*
	 * public static void main(String... strings) throws IOException {
	 * 
	 * // Create an object of ReadGuru99ExcelFile class
	 * 
	 * try { ExcelReaderAndWrite objExcelFile = new ExcelReaderAndWrite();
	 * 
	 * // Prepare the path of excel file
	 * 
	 * // String filePath = "src\\main\\resources\\TestDataFolder";
	 * 
	 * // Call read file method of the class to read data
	 * 
	 * objExcelFile.readExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1");
	 * 
	 * // Create an array with the data in the same order in which you // expect
	 * to be filled in excel file
	 * 
	 * // Create an object of current class
	 * 
	 * // ExcelReaderAndWrite objExcelFile1 = new ExcelReaderAndWrite();
	 * 
	 * // Write the file using file name, sheet name and the data to be //
	 * filled
	 * 
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "1", "pass");
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "2", "pass");
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "3", "fail");
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "4", "pass");
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "5", "fail");
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "6", "fail");
	 * objExcelFile.writeExcel("src\\main\\resources\\TestDataFolder",
	 * "Data.xlsx", "Sheet1", "7", "pass"); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */
}
