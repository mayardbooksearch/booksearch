package com.mayard.booksearch.book.repository;

import com.mayard.booksearch.book.model.entity.SearchHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {

    Page<SearchHistory> findByUserNo(int userNo, Pageable pageable);
    SearchHistory findFirstByUserNoOrderBySearchDateDesc(int userNo);
}
