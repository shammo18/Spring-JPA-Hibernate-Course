package com.example.bookshopmanagement.controller;

import com.example.bookshopmanagement.dto.BookInfoDto;
import com.example.bookshopmanagement.dto.ShopBookDto;
import com.example.bookshopmanagement.service.ShopBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shop-books")
public class ShopBookController {

    ShopBookService shopBookService;

    public ShopBookController(ShopBookService shopBookService) {
        this.shopBookService = shopBookService;
    }



    @GetMapping(value = "book-info/{shopNo}")
    public List<BookInfoDto> getAllBookInfoFromShop(@PathVariable(value = "shopNo") String shopNo) {
        return shopBookService.getAllBookInfoFromShop(shopNo);
    }

    @GetMapping(value = "/{shopNo}")
    public ShopBookDto getShopBookDetails(@PathVariable(value = "shopNo") String shopNo) {
        return shopBookService.getShopBookDetails(shopNo);
    }
}
