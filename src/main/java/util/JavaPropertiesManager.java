package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import testBase.TestBase;



public class JavaPropertiesManager extends TestBase {
	
	private String propertiesFile;
	private Properties prop;
	private OutputStream output;
	private InputStream input;
	
	
	
	public JavaPropertiesManager(String propertiesFilePath) {
		propertiesFile = propertiesFilePath;
		prop = new Properties();
	}

	public String readProperty(String key) {
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public void setProperty(String key, String value)
	{
		try
		{
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	

}
