package jp.creollc.smaregi.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON related utilities
 *
 * @author Yasuyuki Takeo
 */
public class JsonUtil {

    /**
     * Get JSON Mapped object
     *
     * @param path  path to the json file
     * @param clazz class to map the json file
     * @param <T>   Mapped class
     * @return Mapped Json object
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T getObject(
        String path, final Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {

        String jsonString = FileUtils.readFileToString(new File(path), "utf-8");

        return toObject(jsonString, clazz);
    }

    /**
     * Map json to the actual object
     *
     * @param json  json strings to map to the object
     * @param clazz class
     * @param <T>   Mapped class
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T toObject(final String json, final Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES);

        if (null == json) {
            throw new InvalidParameterException("json is null.");
        }

        return mapper.readValue(json, clazz);
    }

    /**
     * Map json to the actual object with type reference
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T toObject(final String json, TypeReference typeReference)
        throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES);

        if (null == json) {
            throw new InvalidParameterException("json is null.");
        }

        return mapper.readValue(json, typeReference);

    }

    /**
     * Convert map to Object List
     *
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertMapListToObject(final List<Map<String, String>> list, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES);
        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        List<T> result = new ArrayList<>();
        for( Map<String, String> map : list) {
            T obj = mapper.convertValue(map, clazz);
            result.add(obj);
        }

        return (List<T>)result;
    }

    /**
     * Write JSON file
     *
     * @param fullPath including target file name. e.g. /path/to/thisfile.txt
     * @param obj      Object to be serialized.
     * @param <T>
     * @throws IOException
     * @throws URISyntaxException
     */
    public static <T> void toJSONFile(String fullPath, T obj) throws IOException, URISyntaxException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ow.writeValue(new File(fullPath), obj);
    }

    /**
     * Write JSON Value as String
     *
     * @param obj Object to convert to JSON String
     * @param <T> Target class
     * @return JSON String
     * @throws JsonProcessingException
     */
    public static <T> String toJSON(T obj) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }
}
