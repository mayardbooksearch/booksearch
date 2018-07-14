package com.mayard.booksearch.user.service;

import com.mayard.booksearch.common.util.MessageUtil;
import com.mayard.booksearch.common.util.RSAUtil;
import com.mayard.booksearch.user.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider { //authenticationManager

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        SecurityUser userInfo = (SecurityUser) userService.loadUserByUsername(authToken.getName()); //UserDetailsService에서 유저정보를 불러온다.

        // 패스워드 복호화
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String password = (String) authToken.getCredentials();
        try {
            password = RSAUtil.decryptRsa(session, password);
        } catch (Exception e) {
            throw new BadCredentialsException(messageUtil.getMessage("default.error"));
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException(messageUtil.getMessage("user.password.invalid"));
        }

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userInfo.getAuthorities();

        return new UsernamePasswordAuthenticationToken(userInfo,null,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
