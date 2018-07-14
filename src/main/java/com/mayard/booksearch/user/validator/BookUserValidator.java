package com.mayard.booksearch.user.validator;

import com.mayard.booksearch.user.model.entity.BookUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return BookUser.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        BookUser user = (BookUser) o;

        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,11}$";

        if (StringUtils.isEmpty(user.getUserId())) {
            errors.rejectValue("userId", "user.id.empty");
        } else if (StringUtils.isEmpty(user.getPassword())) {
            errors.rejectValue("password", "user.password.empty");
        } else if (!user.getUserId().matches(regex)) {
            errors.rejectValue("userId", "user.id.wrong");
        } else if (user.getPassword().length() < 4 || user.getPassword().length() > 16) {
            errors.rejectValue("password", "user.password.wrong");
        }
    }
}
