package com.example.bookshopmanagement.dto;

import com.example.bookshopmanagement.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDetailsDto {
    private String isbn;
    private String bookName;
    private String author;
    private BookType bookType;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
}
