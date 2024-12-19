package rise.cc.common;

@SuppressWarnings("unused")
public class UserResultCode extends ResultCode {

    public static final String ID_ALREADY_EXISTS = "101";
    public static final String ID_ALREADY_EXISTS_MSG = "이미 존재하는 아이디입니다.";
    public static final String PASSWORD_RULE_VIOLATION = "102";
    public static final String PASSWORD_RULE_VIOLATION_MSG = "비밀번호 규칙에 어긋납니다.";
    public static final String ID_NOT_FOUND = "103";
    public static final String ID_NOT_FOUND_MSG = "존재하지 않는 아이디입니다.";
    public static final String PASSWORD_INCORRECT = "104";
    public static final String PASSWORD_INCORRECT_MSG = "패스워드 오류입니다.";

    /**
     *
     * @param resultCode 결과 코드
     * @return 결과 메시지
     */
    public static String resultMsg(String resultCode) {
        return switch (resultCode) {
            case ID_ALREADY_EXISTS -> ID_ALREADY_EXISTS_MSG;
            case PASSWORD_RULE_VIOLATION -> PASSWORD_RULE_VIOLATION_MSG;
            case ID_NOT_FOUND -> ID_NOT_FOUND_MSG;
            case PASSWORD_INCORRECT -> PASSWORD_INCORRECT_MSG;
            default -> ERROR_MSG;
        };
    }
}
