package com.example.bookshopmanagement.controller;

import com.example.bookshopmanagement.dto.BookDto;
import com.example.bookshopmanagement.dto.ShopDto;
import com.example.bookshopmanagement.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public List<ShopDto> getBooks(){
        return shopService.getShops();
    }

    @PostMapping
    public ShopDto saveShop(@RequestBody @Validated ShopDto request) {
        System.out.println("here is the problem: " + request.getShopNo());
        return shopService.save(request);
    }

    @GetMapping(value = "/{shopNo}")
    public ShopDto get(@PathVariable("shopNo") String shopNo) {
        return shopService.get(shopNo);
    }

    @PutMapping(value = "/{shopNo}")
    public ShopDto updateShop(@PathVariable("shopNo") String shopNo, @RequestBody ShopDto request) {
        return shopService.update(shopNo, request);
    }

    @DeleteMapping(value = "/{shopNo}")
    public void deleteBook(@PathVariable("shopNo") String shopNo){
        shopService.delete(shopNo);
    }

}
