package com.mayard.booksearch.book.service;

import com.mayard.booksearch.book.exception.BookmarkDuplicatedException;
import com.mayard.booksearch.book.model.entity.Bookmark;
import com.mayard.booksearch.book.model.entity.SearchHistory;
import com.mayard.booksearch.book.model.vo.ApiResponseVo;
import com.mayard.booksearch.book.model.vo.BookSearchRequestVo;
import com.mayard.booksearch.book.repository.BookmarkRepository;
import com.mayard.booksearch.book.repository.SearchHistoryRepository;
import com.mayard.booksearch.common.util.PageableUtil;
import com.mayard.booksearch.user.model.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    @Value("${list.per.page}")
    private int listPerPage;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookApiService apiService;

    public ApiResponseVo searchBook(BookSearchRequestVo requestVo, SecurityUser user) {

        ApiResponseVo responseVo = apiService.searchBook(requestVo);

        // 검색 결과 유무 상관없이 마지막 검색내용과 다를 경우 검색 히스토리에 저장
        SearchHistory lastHistory = searchHistoryRepository.findFirstByUserNoOrderBySearchDateDesc(user.getUserNo());
        if (lastHistory == null || !lastHistory.getQuery().equals(requestVo.getQuery())) {
            SearchHistory searchHistory = new SearchHistory(requestVo.getQuery(), LocalDateTime.now().toString());
            searchHistory.setUserNo(user.getUserNo());
            searchHistory.setSearchCount(responseVo.getMeta().getPageable_count());
            searchHistoryRepository.save(searchHistory);
        }

        return responseVo;
    }

    public Page<SearchHistory> getSearchHistory(SecurityUser user, int page) {
        // JPA 페이징 설정 (시간 내림차순)
        Pageable pageable = PageableUtil.getPageable(page, listPerPage, Sort.by(Sort.Direction.DESC, "searchDate"));

        return searchHistoryRepository.findByUserNo(user.getUserNo(), pageable);
    }

    @Transactional
    public Bookmark addBookmark(Bookmark bookmark, SecurityUser user) throws BookmarkDuplicatedException {


        if (getDuplicatedBookmark(bookmark, user) != null) {
            throw new BookmarkDuplicatedException();
        }

        bookmark.setUserNo(user.getUserNo());
        bookmark.setBookmarkDate(LocalDateTime.now().toString());

        return bookmarkRepository.save(bookmark);
    }

    // 기존에 북마크 한 책인지 확인 (책 제목, isbn, 바코드, e바코드 And 조건으로 확인)
    public Bookmark getDuplicatedBookmark(Bookmark bookmark, SecurityUser user) {

        Bookmark duplicatedBookmark = bookmarkRepository.findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(
                user.getUserNo(),
                bookmark.getTitle(),
                bookmark.getBarcode(),
                bookmark.getEbookBarcode(),
                bookmark.getIsbn());

        return duplicatedBookmark;
    }

    public void removeBookmark(int bookmarkNo) {
        bookmarkRepository.deleteById(bookmarkNo);
    }

    public Page<Bookmark> getBookmarkList(SecurityUser user, int page) {
        Pageable pageable = PageableUtil.getPageable(page, listPerPage, Sort.by(Sort.Direction.DESC, "bookmarkDate"));

        return bookmarkRepository.findByUserNo(user.getUserNo(), pageable);
    }
}
