package com.mayard.booksearch.user.service;

import com.mayard.booksearch.common.util.MessageUtil;
import com.mayard.booksearch.user.exception.UserIdExistException;
import com.mayard.booksearch.user.model.SecurityUser;
import com.mayard.booksearch.user.model.entity.BookUser;
import com.mayard.booksearch.user.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageUtil messageUtil;

    private boolean isExistId(BookUser bookUser) {

        BookUser foundBookUser = userRepository.findByUserId(bookUser.getUserId());

        if (foundBookUser == null) {
            return false;
        }

        return true;
    }

    @Transactional
    public void createUser(BookUser bookUser) throws UserIdExistException {

        // ID 중복 확인
        if (isExistId(bookUser)) {
            throw new UserIdExistException();
        }

        bookUser.setDatetime(LocalDateTime.now().toString());

        // 패스워드 bCrypt 암호화하여 저장
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        bookUser.setPassword(passwordEncoder.encode(bookUser.getPassword()));

        userRepository.save(bookUser);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        BookUser bookUser = userRepository.findByUserId(userId);

        if (bookUser == null) {
            throw new UsernameNotFoundException(messageUtil.getMessage("user.id.invalid"));
        } else {
            return new SecurityUser(bookUser);
        }
    }

    public SecurityUser getLoginUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        return user;
    }

    public List<BookUser> findAllUser() {
        return userRepository.findAll();
    }
}
