package rise.cc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("unused")
public class EmpException extends Exception {

    private String errorCode;

    public EmpException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public EmpException(String message) {
        super(message);
    }
}
