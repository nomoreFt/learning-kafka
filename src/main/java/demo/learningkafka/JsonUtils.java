package demo.learningkafka;

import tools.jackson.databind.json.JsonMapper;

public class JsonUtils {
    private static final JsonMapper mapper = new JsonMapper();

    public static String toJsonString(Object object) {
        return mapper.writeValueAsString(object);
    }

    public static <T> T fromJsonString(String json, Class<T> valueType) {
        return mapper.readValue(json, valueType);
    }
}


