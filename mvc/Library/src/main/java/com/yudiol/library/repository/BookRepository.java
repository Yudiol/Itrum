package com.yudiol.library.repository;

import com.yudiol.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("FROM Book WHERE bookId = :bookId")
    Optional<Book> findByBookId(Long bookId);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) ")
    Page<Book> searchByNameLike(@Param("title") String title, Pageable pageable);
}
