package features.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonParser {


    public static <T> List<T> getJsonListFromString(String stringJson, Class<T> type) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return mapper.readValue(stringJson, mapper.getTypeFactory().constructCollectionType(List.class, type));
    }

    public static <T> T getJsonFromString(String stringJson, Class<T> type) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stringJson, type);
    }

}
