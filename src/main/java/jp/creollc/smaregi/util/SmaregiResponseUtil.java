package jp.creollc.smaregi.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Smaregi Response Util
 *
 * @author Yasuyuki Takeo
 */
public class SmaregiResponseUtil {

    /**
     * Convert success result json to object
     *
     * @param typeReference
     * @param <T>
     * @return Object list
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    static public <T> List<T> getRefResultByObject(final String json, TypeReference typeReference, Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {

        SmaregiResponseUtil.RefResultResponse resultResponse
            = JsonUtil.toObject(json, SmaregiResponseUtil.RefResultResponse.class);

        return JsonUtil.convertMapListToObject(resultResponse.getResult(), clazz);
    }

    /**
     * Inner POJO class for retrieving a map
     */
    @Data
    static public class RefResultResponse {
        protected List<Map<String, String>> result;
    }

}
