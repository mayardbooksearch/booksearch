package com.mayard.booksearch.book.model.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class SearchHistory {

    @Id
    @Column(name = "history_no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int historyNo;

    @Column(name = "query")
    private String query;

    @Column(name = "search_date")
    private String searchDate;

    @Column(name = "user_no")
    private int userNo;

    @Column(name = "search_count")
    private int searchCount;

    @Column(name = "large_category")
    private String largeCategory;

    @Column(name = "small_category")
    private String smallCategory;

    @Column(name = "sort")
    private String sort;

    @Column(name = "target")
    private String target;

    public SearchHistory(){}

    public SearchHistory(String query, String searchDate) {
        this.query = query;
        this.searchDate = searchDate;
    }

    public SearchHistory(String query, String searchDate, String target, int userNo, int searchCount, String largeCategory, String smallCategory, String sort) {
        this.query = query;
        this.searchDate = searchDate;
        this.target = target;
        this.userNo = userNo;
        this.searchCount = searchCount;
        this.largeCategory = largeCategory;
        this.smallCategory = smallCategory;
        this.sort = sort;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }


    public String getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(String largeCategory) {
        this.largeCategory = largeCategory;
    }

    public String getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(String smallCategory) {
        this.smallCategory = smallCategory;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
