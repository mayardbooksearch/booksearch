package com.mayard.booksearch.book.service;

import com.mayard.booksearch.book.model.vo.BookSearchRequestVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookApiServiceTest {

    @Autowired
    private BookApiService bookApiService;

    @Test
    public void apiTest() {

        BookSearchRequestVo requestVo = new BookSearchRequestVo("미움받을 용기");

        assertNotNull(bookApiService.searchBook(requestVo));
    }
}
