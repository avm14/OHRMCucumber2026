package Utils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.LoginData;
import POJO.NewCandidateData;

public class TestDataManager {

	public static LoginData getLoginData(String key) {
        return readJson("loginData.json", key, LoginData.class);
    }
	public static NewCandidateData getCandidateDetails(String key) {
        return readJson("newCandidateData.json", key, NewCandidateData.class);
    }
    
    // common reusable logic
    private static <T> T readJson(String fileName, String key, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, T> data = mapper.readValue(
                new File("src/test/resources/TestData/" + fileName),
                mapper.getTypeFactory().constructMapType(Map.class, String.class, clazz)
            );
            return data.get(key);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data", e);
        }
    }
}
