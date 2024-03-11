package com.yudiol.SpringDataJDBC.controller;

import com.yudiol.SpringDataJDBC.dao.BookDao;
import com.yudiol.SpringDataJDBC.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@ResponseStatus(HttpStatus.OK)
public class BookController {

    private final BookDao bookDao;

    @GetMapping("/{bookId}")
    public Book get(@PathVariable("bookId") Long bookId) {
        return bookDao.findByBookId(bookId);
    }

    @PostMapping
    public void save(@RequestBody Book book) {
        bookDao.save(book);
    }

    @PatchMapping("/{bookId}")
    public void update(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookDao.update(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable("bookId") Long bookId) {
        bookDao.deleteByBookId(bookId);
    }


}
