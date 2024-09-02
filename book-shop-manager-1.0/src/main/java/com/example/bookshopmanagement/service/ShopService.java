package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.dto.ShopDto;

import java.util.List;

public interface ShopService {

    ShopDto save(ShopDto request);
    List<ShopDto> getShops();
    ShopDto get(String shopNo);
    ShopDto update(String shopNo, ShopDto request);
    void delete(String isbn);
}
