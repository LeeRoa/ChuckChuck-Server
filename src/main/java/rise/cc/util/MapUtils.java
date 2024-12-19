package rise.cc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

@SuppressWarnings("unused")
public class MapUtils {

    /**
     * object를 Map으로 변환한다.
     *
     * @param obj (변환할 객체)
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> ObjtoMap(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(obj, Map.class);
    }
}
