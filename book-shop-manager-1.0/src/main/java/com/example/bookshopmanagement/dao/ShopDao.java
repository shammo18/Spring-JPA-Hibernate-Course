package com.example.bookshopmanagement.dao;

import com.example.bookshopmanagement.entity.ShopEntity;
import com.example.bookshopmanagement.service.ShopService;

import java.util.List;
import java.util.Optional;

public interface ShopDao {
    ShopEntity save(ShopEntity shopEntity);
    List<ShopEntity> getShops();
    Optional<ShopEntity> getByShopNo(String shopNo);
    void deleteById(Long id);
}
