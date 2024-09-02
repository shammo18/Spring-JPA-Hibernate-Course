package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.dto.BookDto;
import com.example.bookshopmanagement.dto.ShopDto;
import com.example.bookshopmanagement.entity.BookEntity;
import com.example.bookshopmanagement.entity.ShopEntity;

import java.awt.print.Book;
import java.util.List;

public interface ShopHelperService {

    ShopEntity getShopEntityFromShopDto(ShopDto request);

    List<BookEntity> getBookEntitiesFromBookDtos( ShopEntity shopEntity, List<BookDto> books);

    ShopDto getShopDtoFromShopEntity(ShopEntity entity);
}
