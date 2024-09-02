package com.example.bookshopmanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShopDto {

    @NotNull
    private String name;

    @NotNull(message = "Shop no can't be null")
    @NotEmpty(message = "Shop no can't be empty")
    private String shopNo;

    @NotNull
    private String hotline;

    @NotNull
    private String address;

    @NotNull
    private List<BookDto> books;

}
