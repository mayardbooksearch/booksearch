package com.mayard.booksearch.book.enumeration;

public enum BookSort {
    accuracy("정확도순"), recency("최신순"), sales("판매량");

    private String name;

    BookSort(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
