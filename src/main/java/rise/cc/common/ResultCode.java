package rise.cc.common;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked, unused")
public class ResultCode {

    /**
     * 공통 코드
     */
    public static final String SUCCESS = "0";
    public static final String SUCCESS_MSG = "정상 처리";
    public static final String FORMAT_ERROR = "1";
    public static final String FORMAT_ERROR_MSG = "요청 값 포맷 에러";

    public static final String NO_REQUIRED_PARAM = "2";
    public static final String NO_REQUIRED_PARAM_MSG = "필수 입력 값이 입력 또는 설정되지 않았습니다.";

    public static final String ERROR = "100";
    public static final String ERROR_MSG = "처리 실패";

    /**
     *
     * @param resultCode 결과 코드
     * @return 결과 메시지
     */
    public static String resultMsg(String resultCode) {
        return switch (resultCode) {
            case SUCCESS -> SUCCESS_MSG;
            case FORMAT_ERROR -> FORMAT_ERROR_MSG;
            case NO_REQUIRED_PARAM -> NO_REQUIRED_PARAM_MSG;
            default -> ERROR_MSG;
        };
    }
}
