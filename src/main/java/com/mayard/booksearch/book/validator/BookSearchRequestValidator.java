package com.mayard.booksearch.book.validator;

import com.mayard.booksearch.book.model.vo.BookSearchRequestVo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookSearchRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BookSearchRequestVo.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "query", "book.search.query.empty");

        BookSearchRequestVo bookSearchRequestVo = (BookSearchRequestVo) o;

        if (bookSearchRequestVo.getSize() < 1 || bookSearchRequestVo.getSize() > 50) {
            errors.rejectValue("size", "book.search.size.wrong");
        }

        if (bookSearchRequestVo.getPage() < 1 || bookSearchRequestVo.getPage() > 50) {
            errors.rejectValue("page", "book.search.page.wrong");
        }
    }
}
