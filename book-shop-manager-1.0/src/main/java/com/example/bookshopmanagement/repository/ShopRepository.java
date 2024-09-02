package com.example.bookshopmanagement.repository;

import com.example.bookshopmanagement.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    //Derived Queries
    Optional<ShopEntity> getByShopNo(String shopNo);
}
