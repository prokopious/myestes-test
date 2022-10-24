package log4jGlobal;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


		public class Global {

		public static Logger _logger;
		public static final String fileName = "defaultlog";
		public static final String dateAndTimeFormat = "MM-dd-yyyy_hh.mm.ss";
		public static final String logProperttFilePath = "./src/main/resources/config.properties/log4j.properties";

		public static void logFile() {
		//static {
		    /**
		     * This is the static block which appends the log file name with the
		     * timestamp to make it unique
		     */
		    try {
		        
		    	//String dateTime = DateAndTime.getFormattedCurrentDateAndTime(dateAndTimeFormat);
		    	
		        String FileName = fileName + "-"+"log";
		        File file = new File("logs/" + FileName);

		        if (file.createNewFile()) {
		            Properties props = new Properties();
		            props.load(new FileInputStream(logProperttFilePath));
		            props.setProperty("log4j.appender.File.File", "logs/"
		                    + FileName);
		            LogManager.resetConfiguration();
		            PropertyConfigurator.configure(props);
		            System.out.println("Property log4j.appender.File.File = logs/"
		                    + FileName);
		        }
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        System.out.print("IO Exception in static method of Logger Class. "
		                + ex.getMessage());
		        System.exit(-1);
		    }

		}

		/**
		 * This method creates instance of the Logger class coming from log4j jar by
		 * implementing a singelton
		 * 
		 * @return _logger - new instance if no instance exist else an existing
		 *         instance if the method is invoked previously
		 */
		public static Logger createLogger() {
		    if (_logger == null) {
		        _logger = LogManager.getLogger(Global.class);
		        return _logger;
		    } else
		        return _logger;
		}
		


		

}

