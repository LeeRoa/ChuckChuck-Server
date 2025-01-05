package rise.cc.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchCriteria extends Pagination {
    private String searchType;
    private String searchResult;
    private String dateType;
    private Date startDt;
    private Date finDt;
    private String sort = "DESC";
}
