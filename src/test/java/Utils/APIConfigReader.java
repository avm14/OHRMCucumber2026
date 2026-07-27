package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/*
 * System.getProperty("env", "qa")
This is a built-in Java method that reads JVM system properties — the key-value pairs you can set on the command line with -Dkey=value. It takes two arguments:

"env" — the property name to look up
"qa" — a default value to return if that property was never set
 */
public class APIConfigReader {
	
	 private static final Properties properties = new Properties();

	    static {
	        String env = System.getProperty("env", "qa").toLowerCase();
	        String filePath = "src/test/resources/Config/config-" + env + ".properties";

	        try (FileInputStream file = new FileInputStream(filePath)) 
	        {
	            properties.load(file);
	        } 
	        catch (IOException e) 
	        {
	            throw new RuntimeException("Unable to load config file: " + filePath, e);
	        }
	    }

	    public static String getBaseUrl() 
	    {
	        return properties.getProperty("base.url");
	    }
	    public static int getbaselineResponseTime()
	    {
	    	return Integer.parseInt((properties.getProperty("baselineResponseTime")));
	    }

}
