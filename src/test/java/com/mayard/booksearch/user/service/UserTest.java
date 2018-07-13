package com.mayard.booksearch.user.service;

import com.mayard.booksearch.user.model.entity.BookUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {

        // BookUser 0명
        assertEquals(0, userService.findAllUser().size());

        BookUser bookUser = new BookUser("tester", "1234");
        userService.createUser(bookUser);

        // BookUser 1명 생성됨
        assertEquals(1, userService.findAllUser().size());


    }
}
