/**
 * 
 */
package util;

import java.io.FileWriter;
import java.io.IOException;

import testBase.TestBase;

public class ESBUtil extends TestBase {

	public ESBUtil() {
		testUtil.init(this);
	}
	/**
	 * Place the 990 accept test file on the ESB folder in EstesEDI for processing.
	 * 
	 * @param otProNumber The PRO number that will be used to build the accept test
	 *                    file.
	 * @throws IOException The inbound path could not be written to
	 */
	public void placeThe820TestFileOnTheESBFolderForProcessing() throws IOException {
		String path = "\\\\ricesb\\ESB_UAT\\Active\\Batch\\EstesEDI\\1-inbound\\qa-820-test.txt";
		FileWriter fw = new FileWriter(path);
		String dateShort = testUtil.getTodayDateByFormat("yyMMdd");
		String date = testUtil.getTodayDateByFormat("yyyyMMdd");
		try {
			fw.write("ISA*00*          *00*          *ZZ*8003211115A    *02*EXLA           *" + dateShort
					+ "*1110*U*00400*000000651*0*P*:~");
			fw.write("GS*RA*8003211115A*EXLA*" + date + "*1110*651*X*004010~");
			fw.write("ST*820*1357~");
			fw.write("BPR*I*252.46*C*CAS~");
			fw.write("REF*TN*102855~");
			fw.write("DTM*007*" + date + "~");
			fw.write("N1*PE*ESTES EXPRESS LINES*02*EXLA~");
			fw.write("N1*PR*ACME MANUFACTURING, CO.~");
			fw.write("ENT*1~");
			fw.write("NM1*PR*9*ACME MANUFACTURING, CO.~");
			fw.write("RMR*CN*0244705577**252.46*252.46~");
			fw.write("SE*10*1357~");
			fw.write("GE*1*651~");
			fw.write("IEA*1*000000651~");
			fw.close();
		} catch (IOException e) {
			System.err.println("Couldn't write to path");
			e.printStackTrace();
		} finally {
			fw.close();
		}
		testUtil.setHardWait(5000);
	}
}

