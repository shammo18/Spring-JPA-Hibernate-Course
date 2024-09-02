//package com.example.bookshopmanagement.dao;
//
//import com.example.bookshopmanagement.entity.BookEntity;
//import com.example.bookshopmanagement.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class BookDaoImpl implements BookDao{
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Override
//    public BookEntity save(BookEntity entity){
//        return bookRepository.save(entity);
//    }
//
//    @Override
//    public List<BookEntity> getBooks() {
//        return bookRepository.findAll();
//    }
//
//    @Override
//    public Optional<BookEntity> getByIsbn(String isbn) {
//        return bookRepository.findByIsbn(isbn);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//}
