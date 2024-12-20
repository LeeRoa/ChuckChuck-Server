package rise.cc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked, unused")
public class JsonUtils {

    static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    /**
     *
     * @param jsonStr 파싱할 JSON 문자열
     * @param param 파싱할 KEY 값
     * @return 파싱된 VALUE 값
     * @throws ParseException 파싱할 KEY 값이 존재하지 않음
     */
    public static String jsonParser(String jsonStr, String param) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);
        return jsonObject.get(param).toString();
    }

    public static JSONObject jsonStrToJsonObject(String jsonStr) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(jsonStr);

    }

    /**
     * @param jsonStr 파싱할 JSON 문자열
     * @param key 추가할 KEY 값
     * @param value 추가할 데이터
     * @return 파싱한 JSON 데이터에 새로운 데이터가 추가된 JSON String
     * @throws JsonProcessingException JSON 데이터 파싱 및 추가 실패
     */
    public static String addJsonValue(String jsonStr, String key, Object value) throws JsonProcessingException {
        if (jsonStr == null || key == null || value == null) {
            throw new NullPointerException();
        }
        Map<String, Object> jsonMap = objectMapper.readValue(jsonStr, new TypeReference<>() {});
        jsonMap.put(key, value);

        return objectMapper.writeValueAsString(jsonMap);
    }

    /**
     *
     * @param resultCode 처리 코드
     * @param resultMsg 처리 메시지
     */
    public static String resultJsonString(String resultCode, String resultMsg){
        JSONObject jsonStr	= new JSONObject();
        jsonStr.put("resultCode", resultCode);
        jsonStr.put("resultMsg",resultMsg);

        return jsonStr.toJSONString();
    }

    /**
     *
     * @param jsonStr 변환할 JSON 문자열
     * @return 변환된 MAP 객체
     * @throws JsonProcessingException JSON 문법 에러
     */
    public static Map<String, Object> jsonToMap(String jsonStr) throws JsonProcessingException {
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {};
        return objectMapper.readValue(jsonStr, typeReference);
    }

    /**
     *
     * @param listMap 변환할 LIST MAP 객체
     * @return 변환된 JSONARRAY 문자열
     * @throws JsonProcessingException JSON 문법 에러
     */
    public static String listMaptoJson(List<Map<String, Object>> listMap) throws JsonProcessingException {
        return objectMapper.writeValueAsString(listMap);

    }

    /**
     * Object 객체를 Json String으로 변환한다.
     * @param object (변환할 객체)
     * @return json String
     * @throws JsonProcessingException JSON 문법 에러
     */
    public static String objtoJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
