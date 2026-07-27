package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class APIPayloadManager {
	
	private static final Pattern PLACEHOLDER = Pattern.compile("\\{\\{(.+?)}}");
    private static final String PAYLOADS_DIR = "src/test/resources/TestData/API Payloads/";
	
    //Method to read file in the given path as a string and return
	private static String readRaw(String payloadKey) {
        Path file = Path.of(PAYLOADS_DIR + payloadKey + ".json");
        try {
            return Files.readString(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not read payload file: " + file +
                    ". Check that TestData/Payloads/" + payloadKey + ".json exists.", e);
        }
    }
	
	//Method that replaces placeholders in payload with values stored in context
	public static String getPayload(String payloadKey, APIScenarioContext context) {
        String json = readRaw(payloadKey);

        Matcher matcher = PLACEHOLDER.matcher(json);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = context.has(key) ? context.getString(key) : matcher.group(0);
            matcher.appendReplacement(result, Matcher.quoteReplacement(value));
        }
        matcher.appendTail(result);
        return result.toString();
    }

}
