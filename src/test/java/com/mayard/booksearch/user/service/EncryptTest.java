package com.mayard.booksearch.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptTest {

    @Test
    public void BCryptTest() {

        String rawPassword = "test1234##";
        String rawPassword2 = "test1234#$";

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        assertTrue(bCryptPasswordEncoder.matches(rawPassword, encPassword));

        assertFalse(bCryptPasswordEncoder.matches(rawPassword2, encPassword));

    }
}
