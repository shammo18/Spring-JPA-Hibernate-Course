package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.dto.BookInfoDto;
import com.example.bookshopmanagement.dto.ShopBookDto;

import java.util.List;

public interface ShopBookService {
    List<BookInfoDto> getAllBookInfoFromShop(String shopNo);
    ShopBookDto getShopBookDetails(String shopNo);


}
