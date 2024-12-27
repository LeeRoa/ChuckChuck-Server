package rise.cc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Code {
    /*
        코드 번호	code_no
        코드 타입	code_type
        코드 명	    code_name
        컬럼 시퀀스	col_seq
     */
    private String codeNo;
    private String codeName;
    private String codeType;
    private String colSeq;
    private String spare;
}
