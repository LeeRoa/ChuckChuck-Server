package rise.cc.common;

import lombok.Data;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Data
public class Page {
    private Integer currentPage;            // 현재 페이지
    private Integer totalPageCount;         // 전체 페이지 개 수(검색 x)
    private Integer searchTotalPageCount;   // 검색된 개 수에 맞는 전체 페이지 개 수
    private Integer viewCount;              // 화면에 보여질 건 수
    private Integer viewPageCount;          //  화면에 보여질 페이지 개 수
    private Integer totalRecordCount;       // 전체 조회 개 수
    private Integer searchRecordCount;      // 검색된 개 수

    private Integer startIndex;
    private Integer endIndex;
    private Integer startPage;
    private Integer endPage;

    Page(Integer currentPage, Integer viewCount) {
        this.currentPage = Objects.requireNonNullElse(currentPage, 1);
        this.viewCount = viewCount;
    }

    public Integer getTotalPageCount() {
        this.totalPageCount = ((getTotalRecordCount() - 1) / getViewCount() + 1);
        return this.totalPageCount;
    }

    public Integer getSearchTotalPageCount() {
        this.totalPageCount = ((getSearchRecordCount() - 1) / getViewCount() + 1);
        return this.totalPageCount;
    }

    public Integer getStartIndex() {
        this.startIndex = (getCurrentPage() - 1) * getViewCount();
        return this.startIndex;
    }

    public Integer getEndIndex() {
        this.endIndex = getCurrentPage() * getViewCount();
        return this.endIndex;
    }

    public Integer getStartPage() {
        this.startPage = max(1, currentPage - (getViewPageCount() / 2));
        return this.startPage;
    }

    public Integer getEndPage() {
        this.endPage = min(getTotalPageCount(), getStartIndex() + getViewPageCount() - 1);
        return this.endPage;
    }
}
