package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.dto.BookDto;
import com.example.bookshopmanagement.dto.ShopDto;
import com.example.bookshopmanagement.entity.BookEntity;
import com.example.bookshopmanagement.entity.ShopEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopHelperServiceImpl implements ShopHelperService{

    @Override
    public ShopEntity getShopEntityFromShopDto(ShopDto request) {

        ShopEntity  shopEntity = ShopEntity.builder()
                .name(request.getName())
                .shopNo(request.getShopNo())
                .address(request.getAddress())
                .hotline(request.getHotline())
                .build();

        List<BookEntity> bookEntities = getBookEntitiesFromBookDtos(shopEntity, request.getBooks());
        shopEntity.setBookEntities(bookEntities);

        return shopEntity;
    }

    @Override
    public List<BookEntity> getBookEntitiesFromBookDtos(ShopEntity shopEntity, List<BookDto> books) {
        List<BookEntity> bookEntities = new ArrayList<>();

        for(BookDto bookDto : books) {
            bookEntities.add(getBookEntityFromBookDto(shopEntity, bookDto));
        }
        return bookEntities;
    }

    @Override
    public ShopDto getShopDtoFromShopEntity(ShopEntity request) {
        ShopDto shopDto =  ShopDto.builder()
                .name(request.getName())
                .shopNo(request.getShopNo())
                .address(request.getAddress())
                .hotline(request.getHotline())
                .build();

        List<BookDto> books = getBookDtosFromBookEntities(request.getBookEntities());
        shopDto.setBooks(books);
        return shopDto;

    }

    private List<BookDto> getBookDtosFromBookEntities(List<BookEntity> bookEntities) {
        List<BookDto> books = new ArrayList<>();
        for(BookEntity entity : bookEntities) {
            books.add(getBookDtoFromBookEntity(entity));
        }
        return books;
    }

    private BookDto getBookDtoFromBookEntity(BookEntity request) {
        return BookDto.builder()
                .isbn(request.getIsbn())
                .discountRate(request.getDiscountRate())
                .build();
    }


    //BookDTO to BookEntity mapper
    public BookEntity getBookEntityFromBookDto(ShopEntity shopEntity, BookDto request) {
        return BookEntity.builder()
                .isbn(request.getIsbn())
                .discountRate(request.getDiscountRate())
                .shopEntity(shopEntity)
                .build();
    }
}
