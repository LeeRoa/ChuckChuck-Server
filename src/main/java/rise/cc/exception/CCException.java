package rise.cc.exception;

@SuppressWarnings("unused")
public class CCException extends Exception {

    public CCException(String errorCode, String message) {
        super("에러 코드: " + errorCode + ", 에러 메시지: " + message);
    }

    public CCException(String message) {
        super(message);
    }
}
