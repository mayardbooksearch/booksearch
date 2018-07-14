package com.mayard.booksearch.book.model.vo;

import com.mayard.booksearch.book.enumeration.BookSort;
import com.mayard.booksearch.book.enumeration.BookTarget;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class BookSearchRequestVo {

    private String query;

    @Enumerated(EnumType.STRING)
    private BookSort sort = BookSort.accuracy;

    private int page = 1;

    private int size = 10;

    @Enumerated(EnumType.STRING)
    private BookTarget target;

    private int category = -1;

    private int largeCategory = -1;
    private int smallCategory = -1;

    private String largeCategoryText;
    private String smallCategoryText;

    public BookSearchRequestVo(){}

    public BookSearchRequestVo(String query) {
        this.query = query;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(int largeCategory) {
        this.largeCategory = largeCategory;
    }

    public int getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(int smallCategory) {
        this.smallCategory = smallCategory;
    }


    public String getLargeCategoryText() {
        return largeCategoryText;
    }

    public void setLargeCategoryText(String largeCategoryText) {
        this.largeCategoryText = largeCategoryText;
    }

    public String getSmallCategoryText() {
        return smallCategoryText;
    }

    public void setSmallCategoryText(String smallCategoryText) {
        this.smallCategoryText = smallCategoryText;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
