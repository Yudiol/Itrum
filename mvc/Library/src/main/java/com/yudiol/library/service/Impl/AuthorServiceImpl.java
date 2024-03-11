package com.yudiol.library.service.Impl;

import com.yudiol.library.model.Author;
import com.yudiol.library.repository.AuthorRepository;
import com.yudiol.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Author findByAuthorId(Long authorId) {
        return authorRepository.findByAuthorId(authorId);
    }

    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
