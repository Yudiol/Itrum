package com.yudiol.library.controller;

import com.yudiol.library.model.Book;
import com.yudiol.library.service.BookService;
import com.yudiol.library.validate.Checker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public Book findByBookId(@PathVariable Long bookId) {
        Book book = bookService.findByBookId(bookId);
        System.out.println(book);
        return book;
    }

    @GetMapping
    public Page<Book> findAllByFilter(@RequestParam("title") String title,
                                      @RequestParam("sort") String sort,
                                      @RequestParam("direction") String direction,
                                      @RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "3") int size) {
        return bookService.findAllByFilter(title, PageRequest.of(page, size, Sort.Direction.fromString(direction), sort));
    }

    @PostMapping
    public Book create(@RequestBody @Valid Book book, BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return bookService.save(book);
    }

    @PatchMapping("/{bookId}")
    public Book update(@PathVariable Long bookId, @RequestBody @Valid Book book, BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return bookService.update(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteByBookId(@PathVariable Long bookId) {
        bookService.deleteByBookId(bookId);
    }

}
