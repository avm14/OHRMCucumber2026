package Utils;

import java.util.HashMap;
import java.util.Map;

public class APIScenarioContext {

	private final Map<String, Object> store = new HashMap<>();

    public void set(String key, Object value) {
        store.put(key, value);
    }

    public Object get(String key) {
        if (!store.containsKey(key)) {
            throw new IllegalStateException("No value found in context for key '" + key + "'.");
        }
        return store.get(key);
    }

    public String getString(String key) {
        return String.valueOf(get(key));
    }

    public boolean has(String key) {
        return store.containsKey(key);
    }

}
