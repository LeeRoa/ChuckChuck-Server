package rise.cc.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
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

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        String errorMessage = "NullpointerException 발생";
        logErrorWithLocation(e, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.DB_ERROR, errorMessage));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        String errorMessage = "DB 에러 발생";

        if(e instanceof CannotGetJdbcConnectionException) {
            errorMessage = "DB 연결 실패: 데이터베이스 서버와 연결할 수 없습니다";
            logErrorWithLocation(e, errorMessage);
        } else if(e instanceof DataIntegrityViolationException) {
            errorMessage = "DB 에러: 데이터베이스 제약 조건 위반이 발생";
            if(e instanceof DuplicateKeyException) {
                errorMessage = "DB 에러: 유니크 제약조건 위반(기본 키, 고유 키 중복)";
            }
        } else if(e instanceof QueryTimeoutException) {
            errorMessage = "DB 에러: DB 타임 아웃 발생";
        } else if(e instanceof TypeMismatchDataAccessException) {
            errorMessage = "DB 에러: SQL 결과 반환 타입 오류";
        } else if(e instanceof EmptyResultDataAccessException) {
            errorMessage = "DB 에러: 쿼리 결과가 비어 있습니다";
        }
        logErrorWithLocation(e, errorMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.DB_ERROR, errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "예상치 못한 오류가 발생했습니다(Exception)";
        log.error("Exception : {}", e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException e) {
        String errorMessage = "Json 처리 오류 발생";
        logErrorWithLocation(e, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JsonUtils.resultJsonString(ResultCode.ERROR, errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = getMessage(allErrors.iterator());
        String result = JsonUtils.resultJsonString(ResultCode.ERROR, message);

        logErrorWithLocation(e, message);
        return ResponseEntity.badRequest().body(result);
    }

    /**
     * 발생한 예외의 위치를 가지고 rise.cc 내에서 발생했을때, 아닐때 의 로그 메시지 출력
     * @param e 발생한 예외 객체
     * @param message 내가 정의한 에러 메시지
     */
    private void logErrorWithLocation(Exception e, String message) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement firstNonRiseElement = null;

        for (StackTraceElement element : stackTrace) {
            // 만약 rise.cc 내부의 코드라면 계속 건너뛰고
            if (element.getClassName().startsWith("rise.cc")) {
                if (element.getClassName().contains("$$SpringCGLIB$$")) {
                    continue;
                }
                // rise.cc 내에서 발생한 예외 로깅후 그후에 반복 로깅 x
                log.error("rise.cc 내부 예외 발생: ({}.{} - Line: {})\nError: {}",
                        element.getClassName(),
                        element.getMethodName(),
                        element.getLineNumber(),
                        message);
                continue;
            }
            // rise.cc가 아닌 외부에서 예외가 발생한다면 firstNonRiseElement 에 element 추가
            if (firstNonRiseElement == null) {
                firstNonRiseElement = element;
            }
        }
        
        // firstNonRiseElement 에 값이 들어와있으면 외부 예외 발생이므로 아래 로직 실행
        if (firstNonRiseElement != null) {
            log.error("rise.cc 외부 예외 발생: ({}.{} - Line: {})\nError: {}",
                    firstNonRiseElement.getClassName(),
                    firstNonRiseElement.getMethodName(),
                    firstNonRiseElement.getLineNumber(),
                    message);
        } else {
            log.error("Error occurred: {}", message);
        }
    }

    /**
     * 예외 발생시 보내지는 메시지 포멧 설정
     * @param errorIterator 오류 목록의 Iterator
     * @return 오류 메시지를 포함한 문자열
     */
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
        return resultMessageBuilder.toString();
    }
}
