package com.mayard.booksearch.book.controller;

import com.mayard.booksearch.book.exception.BookmarkDuplicatedException;
import com.mayard.booksearch.book.model.entity.Bookmark;
import com.mayard.booksearch.book.model.entity.SearchHistory;
import com.mayard.booksearch.book.model.vo.ApiResponseVo;
import com.mayard.booksearch.book.model.vo.BookSearchRequestVo;
import com.mayard.booksearch.book.service.BookService;
import com.mayard.booksearch.book.util.BookCategoryUtil;
import com.mayard.booksearch.book.validator.BookSearchRequestValidator;
import com.mayard.booksearch.common.model.PaginationVo;
import com.mayard.booksearch.common.util.MessageUtil;
import com.mayard.booksearch.common.util.PaginationUtil;
import com.mayard.booksearch.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
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

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView searchHistory(@RequestParam(value = "page", defaultValue = "0") int page) {

        ModelAndView modelAndView = new ModelAndView();

        Page<SearchHistory> result = bookService.getSearchHistory(userService.getLoginUser(), page);

        PaginationVo paginationVo = PaginationUtil.getPagination((result.getNumber() + 1), result.getSize(), (int) result.getTotalElements(), result.isLast());
        modelAndView.addObject("data", result.getContent());
        modelAndView.addObject("pagination", paginationVo);
        modelAndView.setViewName("/book/searchHistory");

        return modelAndView;
    }

    @RequestMapping(value = "/bookmark/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity bookmark(Bookmark bookmark) {

        try {
            Bookmark addedBookmark = bookService.addBookmark(bookmark, userService.getLoginUser());

            ModelMap modelMap = new ModelMap();

            modelMap.addAttribute("bookmark", addedBookmark);
            modelMap.addAttribute("message", messageUtil.getMessage("book.bookmark.complete"));

            return ResponseEntity.status(HttpStatus.OK).body(modelMap);
        } catch (BookmarkDuplicatedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageUtil.getMessage("book.bookmark.duplicated"));
        }
    }

    @RequestMapping(value = "/bookmark/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity removeBookmark(int bookmarkNo) {

        try {
            bookService.removeBookmark(bookmarkNo);
            return ResponseEntity.status(HttpStatus.OK).body(messageUtil.getMessage("book.bookmark.remove.complete"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }
    }

    @RequestMapping(value = "/bookmark/list", method = RequestMethod.GET)
    public ModelAndView bookmark(@RequestParam(value = "page", defaultValue = "0") int page) {

        ModelAndView modelAndView = new ModelAndView();

        Page<Bookmark> result = bookService.getBookmarkList(userService.getLoginUser(), page);

        PaginationVo paginationVo = PaginationUtil.getPagination((result.getNumber() + 1), result.getSize(), (int) result.getTotalElements(), result.isLast());
        modelAndView.addObject("data", result.getContent());
        modelAndView.addObject("pagination", paginationVo);
        modelAndView.setViewName("/book/bookmark");

        return modelAndView;
    }

    @RequestMapping(value = "/bookmark/duplicated", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity isDuplicatedBookmark(Bookmark bookmark) {

        try {
            Bookmark duplicatedBookmark = bookService.getDuplicatedBookmark(bookmark, userService.getLoginUser());

            ModelMap modelMap = new ModelMap();

            modelMap.addAttribute("duplicatedBookmark", duplicatedBookmark);
            return ResponseEntity.status(HttpStatus.OK).body(modelMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }
    }
}
