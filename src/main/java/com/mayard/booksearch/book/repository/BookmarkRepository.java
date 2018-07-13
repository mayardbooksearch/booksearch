package com.mayard.booksearch.book.repository;

import com.mayard.booksearch.book.model.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    Bookmark findFirstByUserNoAndTitleAndBarcodeAndEbookBarcodeAndIsbn(int userNo, String title, String barCode, String ebookBarcode, String isbn);
    Page<Bookmark> findByUserNo(int userNo, Pageable pageable);

}
