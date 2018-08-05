package com.mayard.booksearch.book.controller;

import com.mayard.booksearch.book.model.entity.SearchHistory;
import com.mayard.booksearch.book.service.BookService;
import com.mayard.booksearch.common.model.PaginationVo;
import com.mayard.booksearch.common.util.PaginationUtil;
import com.mayard.booksearch.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/book/history")
public class BookSearchHistoryController {

    private Logger logger = LoggerFactory.getLogger(BookSearchHistoryController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView searchHistory(@RequestParam(value = "page", defaultValue = "0") int page) {

        ModelAndView modelAndView = new ModelAndView();

        Page<SearchHistory> result = bookService.getSearchHistory(userService.getLoginUser(), page);

        PaginationVo paginationVo = PaginationUtil.getPagination((result.getNumber() + 1), result.getSize(), (int) result.getTotalElements(), result.isLast());
        modelAndView.addObject("data", result.getContent());
        modelAndView.addObject("pagination", paginationVo);
        modelAndView.setViewName("/book/searchHistory");

        return modelAndView;
    }
}
