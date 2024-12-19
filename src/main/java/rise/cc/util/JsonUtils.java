package rise.cc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked, unused")
public class JsonUtils {

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
     * @param keyParam 파싱할 KEY 값
     * @param param 추가할 데이터
     * @return 파싱한 JSON 데이터에 새로운 데이터 추가된 JSON String
     * @throws ParseException 파싱할 KEY 값이 존재하지 않음
     */
    public static String addJsonValue(String jsonStr, String keyParam, Map<String, Object> param) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);
        if(keyParam == null){
            jsonObject.putAll(param);
            return jsonObject.toJSONString();
        }else {
            JSONObject jsonArrayObject = ((JSONObject) jsonObject.get(keyParam));
            jsonArrayObject.putAll(param);
            return jsonArrayObject.toJSONString();
        }
    }

    /**
     *
     * @param resultCode 처리 코드
     * @param resultMsg 처리 메시지
     */
    public static String makeResultJsonString(String resultCode, String resultMsg){
        JSONObject jsonStr	= new JSONObject();
        jsonStr.put("resultCode", resultCode);
        jsonStr.put("result",resultMsg);

        return jsonStr.toJSONString();
    }

    /**
     *
     * @param jsonStr 변환할 JSON 문자열
     * @return 변환된 MAP 객체
     * @throws JsonProcessingException JSON 문법 에러
     */
    public static Map<String, Object> jsonToMap(String jsonStr) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
        return objectMapper.readValue(jsonStr, typeReference);
    }

    /**
     *
     * @param listMap 변환할 LIST MAP 객체
     * @return 변환된 JSONARRAY 문자열
     * @throws JsonProcessingException JSON 문법 에러
     */
    public static String listMaptoJson(List<Map<String, Object>> listMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(listMap);

    }

    /**
     * Object 객체를 Json String으로 변환한다.
     * @param object (변환할 객체)
     * @return json String
     * @throws JsonProcessingException JSON 문법 에러
     */
    public static String ObjtoJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
