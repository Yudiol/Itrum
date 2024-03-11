package com.yudiol.library.controller;

import com.yudiol.library.model.Author;
import com.yudiol.library.service.AuthorService;
import com.yudiol.library.validate.Checker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{authorId}")
    public Author findByAuthorId(@PathVariable Long authorId) {
        return authorService.findByAuthorId(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author create(@RequestBody @Valid Author author, BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return authorService.save(author);
    }

}
