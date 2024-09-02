package com.example.bookshopmanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShopBookDto {
    private String name;
    private String shopNo;
    private String address;
    private String hotline;

    private List<BookDetailsDto> bookDetails;
}
