package com.mayard.booksearch.common.util;

import com.mayard.booksearch.common.model.PaginationVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationUtil {

    static int pageSize;

    static int pageMaxSize;

    @Value("${page.size}")
    private void setPageSize(int size) {
        pageSize = size;
    }

    @Value("${page.max.size}")
    private void setPageMaxSize(int maxSize) {
        pageMaxSize = maxSize;
    }

    public static PaginationVo getPagination(int currPage, int perPage, int totalCount, boolean isEnd) {

        int startPageNo = (((currPage - 1) / pageSize) * pageSize) + 1;
        int endPageNo = startPageNo;

        List<Integer> pageNumbers = new ArrayList<>();

        for (int i = 0; i < pageSize; i ++) {
            pageNumbers.add(startPageNo + i);
            endPageNo = startPageNo + i;

            if ((startPageNo + i) >= 50 || (startPageNo + i) * perPage >= totalCount) {
                isEnd = true;
                break;
            }
        }

        PaginationVo paginationVo = new PaginationVo(currPage, perPage, totalCount, startPageNo, endPageNo, isEnd);

        return paginationVo;
    }
}
