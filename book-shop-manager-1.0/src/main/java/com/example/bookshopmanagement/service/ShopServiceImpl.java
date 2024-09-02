package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.dao.ShopDao;
import com.example.bookshopmanagement.dto.BookDto;
import com.example.bookshopmanagement.dto.ShopDto;
import com.example.bookshopmanagement.entity.BookEntity;
import com.example.bookshopmanagement.entity.ShopEntity;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.el.PropertyNotFoundException;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    ShopDao shopDao;

    @Autowired
    ShopHelperService shopHelperService;

    @Override
    public List<ShopDto> getShops() {
        List<ShopEntity> shopEntities = shopDao.getShops();
        List<ShopDto> Shops = new ArrayList<>();
        for(ShopEntity entity : shopEntities) {
            Shops.add(shopHelperService.getShopDtoFromShopEntity(entity));
        }
        return Shops;
    }

    @Override
    public ShopDto get(String shopNo) {
        Optional<ShopEntity> existingEntity = shopDao.getByShopNo(shopNo);
        if(!existingEntity.isPresent()){
            throw new PropertyNotFoundException("Shop not found with shop no: " + shopNo);
        }
        return shopHelperService.getShopDtoFromShopEntity(existingEntity.get());
    }


    @Override
    public ShopDto save(ShopDto request) {
        Optional<ShopEntity> existingEntity = shopDao.getByShopNo(request.getShopNo());
        if(existingEntity.isPresent()){
            throw new IllegalArgumentException("Shop already exist in the database with shop no" + request.getShopNo());
        }

        ShopEntity newEntity = shopHelperService.getShopEntityFromShopDto(request);
        System.out.println("in service : " + newEntity.getShopNo());
        newEntity = shopDao.save(newEntity);

        return shopHelperService.getShopDtoFromShopEntity(newEntity);
    }

    @Override
    public ShopDto update(String shopNo, ShopDto request) {

        if(!StringUtils.hasLength(shopNo)){
            throw new InvalidRequestStateException("shopNo can't be null or empty");
        }

        if(shopNo != request.getShopNo()){
            Optional<ShopEntity> existingEntity = shopDao.getByShopNo(request.getShopNo());
            if(existingEntity.isPresent()) {
                throw new InvalidRequestStateException("requested Shop Dto already existed, shop no : " + request.getShopNo());
            }
        }

        Optional<ShopEntity> existingEntity = shopDao.getByShopNo(shopNo);
        if(existingEntity.isEmpty()){
            throw new PropertyNotFoundException("Shop not found to update with shopNo: " + shopNo);
        }

        ShopEntity entityToUpdate = existingEntity.get();
        entityToUpdate.setShopNo(request.getShopNo());
        entityToUpdate.setName(request.getName());
        entityToUpdate.setHotline(request.getHotline());
        entityToUpdate.setAddress(request.getAddress());
        entityToUpdate.getBookEntities().clear();
        entityToUpdate.getBookEntities().addAll(shopHelperService.getBookEntitiesFromBookDtos(entityToUpdate,request.getBooks()));
        entityToUpdate = shopDao.save(entityToUpdate);
        return shopHelperService.getShopDtoFromShopEntity(entityToUpdate);

    }

    @Override
    public void delete(String shopNo) {
        Optional<ShopEntity> requestedEntityToDelete = shopDao.getByShopNo(shopNo);
        if(!requestedEntityToDelete.isPresent()){
            throw new PropertyNotFoundException("Shop not found with shop no: " + shopNo);
        }
        shopDao.deleteById(requestedEntityToDelete.get().getId());
    }
}
