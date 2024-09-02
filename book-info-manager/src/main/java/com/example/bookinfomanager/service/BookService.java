package com.example.bookinfomanager.service;

import com.example.bookinfomanager.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto save(BookDto request);
    List<BookDto> getBooks();
    BookDto get(String isbn);
    BookDto update(String isbn, BookDto request);
    void delete(String isbn);
    List<BookDto> getBooksByIsbnList(List<String> isbnList);
}
