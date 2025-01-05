package rise.cc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SearchCriteria {
    private String searchType;
    private String searchResult;
    private String dateType;
    private Date startDt;
    private Date finDt;
    private String sort;
}
