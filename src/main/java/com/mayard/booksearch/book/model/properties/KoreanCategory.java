package com.mayard.booksearch.book.model.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "category")
public class KoreanCategory {

    private List<BookCategory> korean = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (BookCategory category: this.getKorean()) {
            System.out.println(category.getId());
        }
    }

    public List<BookCategory> getKorean() {
        return this.korean;
    }
}
