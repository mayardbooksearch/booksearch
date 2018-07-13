package com.mayard.booksearch.book.repository;

import com.mayard.booksearch.book.model.entity.Bookmark;
import com.mayard.booksearch.book.model.entity.SearchHistory;
import com.mayard.booksearch.common.util.PageableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRespositoryTest {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Test
    public void setSearchHistoryRepositoryTest() {

        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setQuery("미움받을 용기");

        searchHistoryRepository.save(searchHistory);

        List<SearchHistory> histories = searchHistoryRepository.findAll();

        assertThat(histories, hasSize(1));
    }

    @Test
    public void duplicatedBookmarkTest() {

        String title= "미움받을 용기";
        String barcode= "bar_";
        String ebarcode = "ebar_";
        String isbn = "isbn_";

        Bookmark bookmark = new Bookmark(title,
                barcode,
                ebarcode,
                isbn);

        bookmark.setUserNo(1);
        bookmarkRepository.save(bookmark);

        Pageable pageable = PageableUtil.getPageable(0, 100, Sort.by(Sort.Direction.DESC, "bookmarkDate"));
        assertEquals(1, bookmarkRepository.findByUserNo(1, pageable).getContent().size());

        assertNotNull(bookmarkRepository.findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(1, title,  barcode, ebarcode, isbn));
        // 제목으로 중복 확인
        assertNull(bookmarkRepository.findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(1, null,  barcode, ebarcode, isbn));
        // 바코드로 중복 확인
        assertNull(bookmarkRepository.findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(1, title,  null, ebarcode, isbn));
        // e바코드로 중복 확인
        assertNull(bookmarkRepository.findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(1, title,  barcode, null, isbn));
        // isbn으로 중복 확인
        assertNull(bookmarkRepository.findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(1, title,  barcode, ebarcode, null));

    }

    @Test
    public void bookmarkRemoveTest() {

        String titlePrefix = "미움받을 용기";
        String barcodePrefix = "bar_";
        String ebarcodePrefix = "ebar_";
        String isbnPrefix = "isbn_";

        for (int i = 0; i < 100; i ++) {

            Bookmark bookmark = new Bookmark(titlePrefix + i,
                    barcodePrefix + i,
                    ebarcodePrefix + i,
                    isbnPrefix + i);

            bookmark.setUserNo(1);
            bookmarkRepository.save(bookmark);
        }

        Pageable pageable = PageableUtil.getPageable(0, 100, Sort.by(Sort.Direction.DESC, "bookmarkDate"));

        assertEquals(100, bookmarkRepository.findByUserNo(1, pageable).getContent().size());

        bookmarkRepository.deleteById(1);

        assertEquals(99, bookmarkRepository.findByUserNo(1, pageable).getContent().size());

        for (int i = 2; i <= 100; i ++) {
            bookmarkRepository.deleteById(i);
        }

        assertEquals(0, bookmarkRepository.findByUserNo(1, pageable).getContent().size());
    }

}
