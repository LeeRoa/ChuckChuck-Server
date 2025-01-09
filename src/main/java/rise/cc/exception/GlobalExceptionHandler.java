package rise.cc.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import rise.cc.common.ResultCode;
import rise.cc.util.JsonUtils;

import java.util.Iterator;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    protected final LocalValidatorFactoryBean validator;

    public GlobalExceptionHandler(LocalValidatorFactoryBean validator) {
        this.validator = validator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CollectionValidator(validator));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        String errorMessage = "DB 에러 발생";

        if (e.getMessage().contains("Duplicate")) {
            errorMessage = "DB 에러: 중복된 값이 없는지 확인해 주세요.";
        } else if (e.getMessage().contains("constraint violation")) {
            errorMessage = "DB 에러: 데이터베이스 제약 조건 위반이 발생했습니다. 요청을 다시 확인해 주세요.";
        } else if (e.getMessage().contains("foreign key constraint")) {
            errorMessage = "DB 에러: 외래 키 제약 조건 위반 요청을 다시 확인해 주세요.";
        }

        log.error("DB 에러 발생: {}", errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.DB_ERROR, errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("알 수 없는 에러 발생: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.ERROR, "예상치 못한 오류가 발생했습니다."));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException e) {
        log.error("JsonProcessingException 발생: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.ERROR, "JsonProcessingException"));
    }

    // 유효성 검사 실패 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = getMessage(allErrors.iterator());

        String result = JsonUtils.resultJsonString(ResultCode.ERROR, message);

        return ResponseEntity.badRequest().body(result);
    }

    // 유효성 검사 실패 메시지
    private String getMessage(Iterator<ObjectError> errorIterator) {
        StringBuilder resultMessageBuilder = new StringBuilder();
        while (errorIterator.hasNext()) {
            ObjectError error = errorIterator.next();
            FieldError fieldError = (FieldError) error;

            resultMessageBuilder.append("['")
                    .append(fieldError.getField())
                    .append("' is '")
                    .append(fieldError.getRejectedValue())
                    .append("' :: ")
                    .append(error.getDefaultMessage())
                    .append("]");

            if (errorIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        log.error(resultMessageBuilder.toString());
        return resultMessageBuilder.toString();
    }

}
