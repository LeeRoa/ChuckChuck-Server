package rise.cc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpLoginException extends Exception {
    private final String errorCode;

    public EmpLoginException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
