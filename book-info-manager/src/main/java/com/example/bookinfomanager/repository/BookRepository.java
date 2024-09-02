package com.example.bookinfomanager.repository;

import com.example.bookinfomanager.dto.BookDto;
import com.example.bookinfomanager.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
   // List<Book> findAll();
    //List<Book> save(Book book);
    Optional<BookEntity> findByIsbn(String BookEntity);
}
