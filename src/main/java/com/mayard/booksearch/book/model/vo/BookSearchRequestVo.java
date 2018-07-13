package com.mayard.booksearch.book.model.vo;

import com.mayard.booksearch.book.enumeration.BookCategory;
import com.mayard.booksearch.book.enumeration.BookSort;
import com.mayard.booksearch.book.enumeration.BookTarget;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class BookSearchRequestVo {

    private String query;

    @Enumerated(EnumType.STRING)
    private BookSort sort;

    private int page = 1;

    private int size = 10;

    @Enumerated(EnumType.STRING)
    private BookTarget target;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    public BookSearchRequestVo(){}

    public BookSearchRequestVo(String query) {
        this.query = query;
    }

    public BookSearchRequestVo(String query, BookSort sort, int page, int size, BookTarget target, BookCategory category) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
        this.target = target;
        this.category = category;
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public BookSort getSort() {
        return sort;
    }

    public void setSort(BookSort sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BookTarget getTarget() {
        return target;
    }

    public void setTarget(BookTarget target) {
        this.target = target;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
