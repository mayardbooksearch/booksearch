package com.mayard.booksearch.user.controller;

import com.mayard.booksearch.common.util.RSAUtil;
import com.mayard.booksearch.user.exception.UserIdExistException;
import com.mayard.booksearch.user.model.entity.BookUser;
import com.mayard.booksearch.common.util.MessageUtil;
import com.mayard.booksearch.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;

@Controller
@RequestMapping(value = "")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;


    @RequestMapping(value = "")
    public String signin(HttpServletRequest request) {

        RSAUtil.initRsa(request);
        return "/user/login";
    }

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity signUp(HttpServletRequest request, BookUser bookUser) {

        try {
            // 패스워드 복호화
            bookUser.setPassword(RSAUtil.decryptRsa(request.getSession(), bookUser.getPassword()));
            // 회원 생성
            userService.createUser(bookUser);
            return ResponseEntity.status(HttpStatus.OK).body(messageUtil.getMessage("user.id.created"));
        } catch(UserIdExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageUtil.getMessage("user.id.duplicated"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(BookUser bookUser) {

        System.out.println(bookUser.toString());
        return "";
    }
}
