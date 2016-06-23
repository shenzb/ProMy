package util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonFactory jsonFactory = objectMapper.getFactory();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//兼容pojo增加字段的版本升级

    }

    public static String toJsonString(Object obj) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(out, JsonEncoding.UTF8);
            jsonGenerator.writeObject(obj);
            return new String(out.toByteArray(), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("toJsonString exception", e);
        }
    }

    public static <T> T parseObject(String jsonStr, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(jsonStr, clazz);
    }


}
