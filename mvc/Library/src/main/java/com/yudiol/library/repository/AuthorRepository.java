package com.yudiol.library.repository;

import com.yudiol.library.model.Author;
import com.yudiol.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("FROM Author WHERE authorId = :authorId")
    Author findByAuthorId(Long authorId);
}
