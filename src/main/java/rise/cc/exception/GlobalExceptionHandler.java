package rise.cc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import rise.cc.common.ResultCode;
import rise.cc.util.JsonUtils;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException 발생: {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(JsonUtils.resultJsonString(ResultCode.NO_REQUIRED_PARAM, e.getMessage() + "(필수값 누락)"));
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
}
