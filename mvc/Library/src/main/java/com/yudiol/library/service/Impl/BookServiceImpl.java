package com.yudiol.library.service.Impl;

import com.yudiol.library.exception.errors.NotFoundException;
import com.yudiol.library.model.Book;
import com.yudiol.library.repository.BookRepository;
import com.yudiol.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Book findByBookId(Long bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Книга с id = " + bookId + " не найдена"));
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long bookId, Book updatedBook) {
        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Книга с id = " + bookId + " не найдена"));
        book.setTitle(updatedBook.getTitle());
        book.setYearOfProduction(updatedBook.getYearOfProduction());
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteByBookId(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional(readOnly = true)
    public Page<Book> findAllByFilter(String title, PageRequest pageRequest) {
        return bookRepository.searchByNameLike(title, pageRequest);
    }
}
