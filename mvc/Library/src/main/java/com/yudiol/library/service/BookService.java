package com.yudiol.library.service;

import com.yudiol.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BookService {
    Book findByBookId(Long bookId);

    Book save(Book book);

    Book update(Long bookId, Book book);

    void deleteByBookId(Long bookId);

    Page<Book> findAllByFilter(String name,  PageRequest pageRequest);
}
