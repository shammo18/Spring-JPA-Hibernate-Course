package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto save(BookDto request);
    List<BookDto> getBooks();
//    BookDto get(String isbn);
//    BookDto update(String isbn, BookDto request);
//    void delete(String isbn);
}
