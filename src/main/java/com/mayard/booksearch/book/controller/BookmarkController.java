package com.mayard.booksearch.book.controller;

import com.mayard.booksearch.book.exception.BookmarkDuplicatedException;
import com.mayard.booksearch.book.model.entity.Bookmark;
import com.mayard.booksearch.book.service.BookService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/book/bookmark")
public class BookmarkController {

    private Logger logger = LoggerFactory.getLogger(BookmarkController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping
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

    @DeleteMapping(value = "/{bookmarkNo}")
    @ResponseBody
    public ResponseEntity removeBookmark(@PathVariable int bookmarkNo) {

        try {
            bookService.removeBookmark(bookmarkNo);
            return ResponseEntity.status(HttpStatus.OK).body(messageUtil.getMessage("book.bookmark.remove.complete"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageUtil.getMessage("default.error"));
        }
    }

    @GetMapping
    public ModelAndView bookmark(@RequestParam(value = "page", defaultValue = "0") int page) {

        ModelAndView modelAndView = new ModelAndView();

        Page<Bookmark> result = bookService.getBookmarkList(userService.getLoginUser(), page);

        PaginationVo paginationVo = PaginationUtil.getPagination((result.getNumber() + 1), result.getSize(), (int) result.getTotalElements(), result.isLast());
        modelAndView.addObject("data", result.getContent());
        modelAndView.addObject("pagination", paginationVo);
        modelAndView.setViewName("/book/bookmark");

        return modelAndView;
    }

    @GetMapping(value = "/duplicated")
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
