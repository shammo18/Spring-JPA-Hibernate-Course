package com.example.bookinfomanager.dao;

import com.example.bookinfomanager.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    BookEntity save(BookEntity entity);

    List<BookEntity> getBooks();

    Optional<BookEntity> getByIsbn(String isbn);

    void deleteById(Long id);
}
