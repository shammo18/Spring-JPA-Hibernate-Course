package com.example.bookinfomanager.controller;

import com.example.bookinfomanager.dto.BookDto;
import com.example.bookinfomanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDto> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public BookDto saveBook(@RequestBody @Validated BookDto request) {
        return bookService.save(request);
    }

    @GetMapping(value = "/{isbn}")
    public BookDto getBook(@PathVariable("isbn") String isbn){
        return bookService.get(isbn);
    }

    @PutMapping(value = "/{isbn}")
    public BookDto updateBook(@PathVariable("isbn") String isbn,@RequestBody BookDto request){
        return bookService.update(isbn, request);
    }
//
    @DeleteMapping(value = "/{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn){
        bookService.delete(isbn);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        //System.out.println("hello");
        return String.format("Hello %s!", name);
    }

    @GetMapping(value = "/isbn-list")
    public List<BookDto> getBooksByIsbnList(@RequestParam("isbnList") List<String> isbnList) {
        return bookService.getBooksByIsbnList(isbnList);
    }

}
