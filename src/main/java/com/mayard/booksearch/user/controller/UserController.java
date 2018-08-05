package com.mayard.booksearch.user.controller;

import com.mayard.booksearch.common.util.RSAUtil;
import com.mayard.booksearch.user.exception.UserIdExistException;
import com.mayard.booksearch.user.model.SecurityUser;
import com.mayard.booksearch.user.model.entity.BookUser;
import com.mayard.booksearch.common.util.MessageUtil;
import com.mayard.booksearch.user.service.UserService;
import com.mayard.booksearch.user.validator.BookUserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private BookUserValidator bookUserValidator;

    @GetMapping
    public String signin(HttpServletRequest request) {

        SecurityUser user = userService.getLoginUser();
        if (user != null) {
            return "redirect:/book/search";
        }

        RSAUtil.initRsa(request);
        return "/user/login";
    }

    @PostMapping(value = "/user/signup")
    @ResponseBody
    public ResponseEntity signUp(HttpServletRequest request, BookUser bookUser, BindingResult bindingResult) {

        try {
            // 패스워드 복호화
            bookUser.setPassword(RSAUtil.decryptRsa(request.getSession(), bookUser.getPassword()));

            bookUserValidator.validate(bookUser, bindingResult);
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage(bindingResult.getFieldError().getCode()));
            }

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

    @PostMapping(value = "/user/login")
    public String login(BookUser bookUser) {

        return "";
    }
}
