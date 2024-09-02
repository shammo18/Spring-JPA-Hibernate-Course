package com.example.bookshopmanagement.service;

import com.example.bookshopmanagement.config.ApplicationProperties;
import com.example.bookshopmanagement.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ShopBookServiceImpl implements ShopBookService{

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public  ShopBookServiceImpl(RestTemplate restTemplate , ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    @Autowired
    ShopService shopService;

    @Override
    public List<BookInfoDto> getAllBookInfoFromShop(String shopNo) {

        List<BookDto>  listOfBooks = shopService.get(shopNo).getBooks();
        List<String> isbnList = new ArrayList<>();
        for(BookDto book : listOfBooks){
            isbnList.add(book.getIsbn());
        }

        StringBuilder url = new StringBuilder(applicationProperties.getBookDetailsUrl()).append("?").append("isbnList=");
        for(String isbn: isbnList) {
            url.append(isbn).append(",");
        }
        url.deleteCharAt(url.length() - 1);
        System.out.println(url);

        //ResponseEntity<BookListDto> response = restTemplate.getForEntity(url.toString(), BookListDto.class);
        ResponseEntity<List<BookInfoDto>> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookInfoDto>>() {}
        );
        System.out.println();

        return response.getBody();
        //return response.getBody();
    }

    @Override
    public ShopBookDto getShopBookDetails(String shopNo) {
        List<BookInfoDto> listOfBookInfo  = getAllBookInfoFromShop(shopNo);
        ShopDto shopDto = shopService.get(shopNo);
        ShopBookDto shopBookDto = ShopBookDto.builder()
                .name(shopDto.getName())
                .shopNo(shopDto.getShopNo())
                .address(shopDto.getAddress())
                .hotline(shopDto.getHotline())
                .build();
    //   shopBookDto.getBookDetails().clear();

        HashMap<String, BigDecimal> hashMap= new HashMap<String, BigDecimal>();
        for(BookDto bookDto : shopDto.getBooks()) {
            hashMap.put(bookDto.getIsbn(), bookDto.getDiscountRate());
        }

        List<BookDetailsDto> listOfBookDetailsDto = new ArrayList<>();

        for(BookInfoDto bookInfo : listOfBookInfo){
            BigDecimal discountedPrice = bookInfo.getPrice().add(bookInfo.getPrice().multiply(hashMap.get(bookInfo.getIsbn())).divide(new BigDecimal(100).negate()));
            BookDetailsDto bookDetailsDto = BookDetailsDto.builder()
                    .bookName(bookInfo.getBookName())
                    .isbn(bookInfo.getIsbn())
                    .bookType(bookInfo.getBookType())
                    .author(bookInfo.getAuthor())
                    .originalPrice(bookInfo.getPrice())
                    .discountedPrice(discountedPrice)
                    .build();
            listOfBookDetailsDto.add(bookDetailsDto);
        }
        shopBookDto.setBookDetails(listOfBookDetailsDto);
        return shopBookDto;

    }
}
