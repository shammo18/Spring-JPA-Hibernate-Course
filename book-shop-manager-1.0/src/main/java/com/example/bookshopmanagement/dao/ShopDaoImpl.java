package com.example.bookshopmanagement.dao;

import com.example.bookshopmanagement.entity.ShopEntity;
import com.example.bookshopmanagement.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public class ShopDaoImpl implements ShopDao{

    @Autowired
    ShopRepository shopRepository;

    @Override
    public ShopEntity save(ShopEntity shopEntity) {
        return shopRepository.save(shopEntity);
    }

    @Override
    public List<ShopEntity> getShops() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<ShopEntity> getByShopNo(String shopNo) {
        return shopRepository.getByShopNo(shopNo);
    }

    @Override
    public void deleteById(Long id) {
        shopRepository.deleteById(id);
    }
}
