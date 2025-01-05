package rise.cc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page {
    private int currentPage;
    private int totalPage;
    private int viewCount;
    private int totalRecordCount;
    private int searchRecordCount;
}
