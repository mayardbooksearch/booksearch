package com.mayard.booksearch.book.controller;

import com.mayard.booksearch.book.model.vo.ApiResponseVo;
import com.mayard.booksearch.book.model.vo.BookSearchRequestVo;
import com.mayard.booksearch.book.service.BookService;
import com.mayard.booksearch.book.util.BookCategoryUtil;
import com.mayard.booksearch.book.validator.BookSearchRequestValidator;
import com.mayard.booksearch.common.util.MessageUtil;
import com.mayard.booksearch.common.util.PaginationUtil;
import com.mayard.booksearch.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/book/search")
public class BookSearchController {

    private Logger logger = LoggerFactory.getLogger(BookSearchController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private BookCategoryUtil bookCategoryUtil;

    @Autowired
    private BookSearchRequestValidator bookSearchRequestValidator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String searchBook(HttpServletRequest request) {

        request.setAttribute("largeCategoryList", bookCategoryUtil.getLargeCategories());
        return "/book/bookSearch";
    }

    @RequestMapping(value = "/category/small/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSmallCategories(@PathVariable String categoryId) {

        try {

            ModelMap modelMap = new ModelMap();

            modelMap.addAttribute("smallCategoryList", bookCategoryUtil.getSmallCategories(categoryId));
            return ResponseEntity.status(HttpStatus.OK).body(modelMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity searchBook(BookSearchRequestVo requestVo, BindingResult bindingResult) {

        bookSearchRequestValidator.validate(requestVo, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> logger.error(messageUtil.getMessage(e.getCode())));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }

        try {

            ModelMap modelMap = new ModelMap();

            ApiResponseVo responseVo = bookService.searchBook(requestVo, userService.getLoginUser());

            modelMap.addAttribute("pagination", PaginationUtil.getPagination(requestVo.getPage(), requestVo.getSize(), responseVo.getMeta().getPageable_count(), responseVo.getMeta().isIs_end()));
            modelMap.addAttribute("data", responseVo.getDocuments());

            return ResponseEntity.status(HttpStatus.OK).body(modelMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }
    }
}
