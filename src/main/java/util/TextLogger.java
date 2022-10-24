package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class TextLogger {

	Calendar now = Calendar.getInstance();
	private int numericDay;
	private int numericMonth;
	private int numericYear;
	private File data;
	private FileWriter output;

	public void textLogger(String message) throws IOException {

		this.numericMonth = (this.now.get(2) + 1);
		this.numericDay = this.now.get(5);
		this.numericYear = this.now.get(1);

		this.data = new File("results/Results" + this.numericMonth + this.numericDay + this.numericYear + ".txt");
		this.output = new FileWriter(this.data, true);
		this.output.write(message);
		this.output.close();
	}

}
