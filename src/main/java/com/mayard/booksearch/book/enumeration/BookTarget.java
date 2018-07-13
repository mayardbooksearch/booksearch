package com.mayard.booksearch.book.enumeration;

public enum BookTarget {
    all("전체"),
    title ("제목에서 검색"),
    isbn ("ISBN에서 검색"),
    keyword ("주제어에서 검색"),
    contents ("목차에서 검색"),
    overview ("책소개에서 검색"),
    publisher ("출판사에서 검색");

    private String name;

    BookTarget(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
