package com.mayard.booksearch.common.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageableUtil {

    public static Pageable getPageable(int page, int size, Sort sort) {
        return (Pageable) PageRequest.of(page, size, sort);
    }
}
