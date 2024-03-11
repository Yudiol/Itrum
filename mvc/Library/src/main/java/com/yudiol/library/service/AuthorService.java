package com.yudiol.library.service;

import com.yudiol.library.model.Author;
import com.yudiol.library.model.Book;

public interface AuthorService {
    Author findByAuthorId(Long authorId);

    Author save(Author author);
}
