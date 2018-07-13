package com.mayard.booksearch.common.model;

import java.util.List;

public class PaginationVo {

    private int currPage;
    private int perPage;
    private int totalCount;

    private int startEntry;
    private int endEntry;

    private int startPageNo;
    private int endPageNo;

    private boolean isEnd;

    public PaginationVo(){}

    public PaginationVo(int currPage, int perPage, int totalCount, int startPageNo, int endPageNo, boolean isEnd) {
        this.currPage = currPage;
        this.perPage = perPage;
        this.totalCount = totalCount;
        this.startPageNo = startPageNo;
        this.endPageNo = endPageNo;
        this.isEnd = isEnd;

        this.startEntry = ((currPage - 1) * perPage) + 1;
        this.endEntry = (startEntry + perPage) - 1;

        if (this.endEntry > totalCount) {
            this.endEntry = totalCount;
        }
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartEntry() {
        return startEntry;
    }

    public void setStartEntry(int startEntry) {
        this.startEntry = startEntry;
    }

    public int getEndEntry() {
        return endEntry;
    }

    public void setEndEntry(int endEntry) {
        this.endEntry = endEntry;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public int getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }
}
