package com.example.bookinfomanager.service;

import com.example.bookinfomanager.dao.BookDao;
import com.example.bookinfomanager.dto.BookDto;
import com.example.bookinfomanager.entity.BookEntity;
import com.mysql.cj.util.StringUtils;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public BookDto save(BookDto request) {
        Optional<BookEntity> existingEntity = bookDao.getByIsbn(request.getIsbn());
        if(existingEntity.isPresent()){
            throw new IllegalArgumentException("Book already exist in the database with isbn" + request.getIsbn());
        }

        BookEntity newEntity = getBookEntityFromBookDto(request);
        newEntity = bookDao.save(newEntity);

        return getBookDtoFromEntity(newEntity);
    }


    //DTO to Entity mapper
    private BookEntity getBookEntityFromBookDto(BookDto request) {
        return BookEntity.builder()
                .isbn(request.getIsbn())
                .bookName(request.getBookName())
                .author(request.getAuthor())
                .price(request.getPrice())
                .bookType(request.getBookType())
                .build();
    }

    //Entity to DTO mapper
    private BookDto getBookDtoFromEntity(BookEntity request) {
        return BookDto.builder()
                .isbn(request.getIsbn())
                .bookName(request.getBookName())
                .author(request.getAuthor())
                .price(request.getPrice())
                .bookType(request.getBookType())
                .build();
    }
    @Override
    public List<BookDto> getBooks() {
        List<BookDto>  result = new ArrayList<>();

        List<BookEntity> entities =  bookDao.getBooks();
        for (BookEntity entity : entities) {
            result.add(getBookDtoFromEntity(entity));
        }
        return result;
    }


    @Override
    public BookDto get(String isbn) {
        Optional<BookEntity> entity = bookDao.getByIsbn(isbn);

//        if(entity.isEmpty()) {
//            throw new PropertyNotFoundException("Book not found with ISBN: "  + isbn);
//        }

        return getBookDtoFromEntity(entity.get());
    }

    @Override
    public List<BookDto> getBooksByIsbnList(List<String> isbnList) {
        List<BookDto> result = new ArrayList<>();

        for(String isbn : isbnList ){
            result.add(get(isbn));
        }

        return result;
    }

    @Override
    public BookDto update(String isbn, BookDto request) {
        if(StringUtils.isNullOrEmpty(isbn)){
            throw new InvalidRequestStateException("ISBN cant' be null or empty.");
        }

        if(!isbn.equals(request.getIsbn())){
            Optional<BookEntity> requestedBookEntity = bookDao.getByIsbn(request.getIsbn());
            if(requestedBookEntity.isPresent()){
                throw new InvalidRequestStateException("Requested ISBN : " + request.getIsbn() + " in update request dto is already assigned in another book");
            }
        }

        Optional<BookEntity> existingEntity = bookDao.getByIsbn(isbn);
        if(existingEntity.isEmpty()){
            throw new PropertyNotFoundException("Book not found to update with ISBN: " + isbn);
        }

        BookEntity entityToUpdate = existingEntity.get();
        entityToUpdate.setIsbn(request.getIsbn());
        entityToUpdate.setBookName(request.getBookName());
        entityToUpdate.setAuthor(request.getAuthor());
        entityToUpdate.setPrice(request.getPrice());
        entityToUpdate.setBookType(request.getBookType());

        entityToUpdate = bookDao.save(entityToUpdate);

        return getBookDtoFromEntity(entityToUpdate);
    }

    @Override
    public void delete(String isbn) {
        Optional<BookEntity> existingEntity = bookDao.getByIsbn(isbn);
        if(existingEntity.isEmpty()){
            throw new PropertyNotFoundException("Book not found to delete with ISBN: " + isbn);
        }

        bookDao.deleteById(existingEntity.get().getId());
    }
}
