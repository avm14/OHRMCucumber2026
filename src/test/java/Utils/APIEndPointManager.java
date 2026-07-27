package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class APIEndPointManager {
	
	  private static final Properties props = new Properties();

	    static {
	        try (InputStream is = new FileInputStream("src/test/resources/Config/endpoints.properties")) {
	            props.load(is);
	        } catch (IOException e) {
	            throw new RuntimeException("Could not load endpoints.properties", e);
	        }
	    }

	    public static String getMethod(String endpointKey) {
	        String method = props.getProperty(endpointKey + ".method");
	        if (method == null) {
	            throw new RuntimeException("No '.method' configured for endpoint key: " + endpointKey);
	        }
	        return method.toUpperCase();
	    }
	    
	    public static String getPath(String endpointKey)
	    {
	    	String path = props.getProperty(endpointKey+".path");
	    	 if (path == null) {
		            throw new RuntimeException("No '.path' configured for endpoint key: " + endpointKey);
		        }
	    	return path;
	    }
	    
	    public static String getPath(String endpointKey, Map<String, String> pathParams) {
	        String path = props.getProperty(endpointKey + ".path");
	        if (path == null) {
	            throw new RuntimeException("No '.path' configured for endpoint key: " + endpointKey);
	        }
	        for (Map.Entry<String, String> entry : pathParams.entrySet()) {
	            path = path.replace("{" + entry.getKey() + "}", entry.getValue());
	        }
	        return path;
	    }

}
