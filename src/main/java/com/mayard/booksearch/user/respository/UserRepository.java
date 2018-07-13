package com.mayard.booksearch.user.respository;

import com.mayard.booksearch.user.model.entity.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BookUser, Integer> {

    BookUser findByUserId(String userId);
}
