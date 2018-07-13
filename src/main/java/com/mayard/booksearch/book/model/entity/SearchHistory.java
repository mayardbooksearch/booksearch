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

    public SearchHistory(){}

    public SearchHistory(String query, String searchDate) {
        this.query = query;
        this.searchDate = searchDate;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
