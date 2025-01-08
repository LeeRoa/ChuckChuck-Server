package rise.cc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Setter
@Getter
public class Pagination {
    private Integer currentPage;            // 현재 페이지
    private Integer viewCount;              // 화면에 보여질 건 수
    private Integer viewPageCount;          //  화면에 보여질 페이지 개 수
    private Integer startIndex;
    private Integer endIndex;

    private Integer searchTotalPageCount = 0;   // 검색된 개 수에 맞는 전체 페이지 개 수
    private Integer totalRecordCount = 0;       // 전체 조회 개 수
    private Integer searchRecordCount = 0;      // 검색된 개 수
    private Integer totalPageCount = 0;         // 전체 페이지 개 수(검색 x)
    private Integer startPage = 0;              // 현재 페이지의 첫 번째 페이지 번호
    private Integer endPage = 0;                // 현재 페이지의 마지막 페이지 번호

    private boolean prev = false; // 이전
    private boolean next = false; // 다음

    Pagination() {
        this.currentPage = 1;
        this.viewCount = 10;
        this.viewPageCount = 5;
        this.startIndex = getStartIndex();
        this.endIndex = getEndIndex();
    }

    Pagination(Integer currentPage, Integer viewCount, Integer viewPageCount) {
        this.currentPage = Objects.requireNonNullElse(currentPage, 1);
        this.viewCount = Objects.requireNonNullElse(viewCount, 10);
        this.viewPageCount = Objects.requireNonNullElse(viewPageCount, 5);
        this.startIndex = getStartIndex();
        this.endIndex = getEndIndex();
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
        return (getCurrentPage() - 1) * getViewCount();
    }

    public Integer getEndIndex() {
        return getCurrentPage() * getViewCount();
    }

    public Integer getStartPage() {
        this.startPage = max(1, currentPage - (getViewPageCount() / 2));
        return this.startPage;
    }

    public Integer getEndPage() {
        this.endPage = min(getTotalPageCount(), getStartIndex() + getViewPageCount() - 1);
        return this.endPage;
    }

    public boolean getPrev() {
        this.prev = getStartPage() != 1;
        return this.prev;
    }

    public boolean getNext() {
        this.next = !Objects.equals(getEndPage(), getTotalPageCount());
        return this.next;
    }
}
