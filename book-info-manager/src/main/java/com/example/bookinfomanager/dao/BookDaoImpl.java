package com.example.bookinfomanager.dao;

import com.example.bookinfomanager.entity.BookEntity;
import com.example.bookinfomanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookEntity save(BookEntity entity){
        return bookRepository.save(entity);
    }

    @Override
    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void deleteById(Long id) {

    }
}
