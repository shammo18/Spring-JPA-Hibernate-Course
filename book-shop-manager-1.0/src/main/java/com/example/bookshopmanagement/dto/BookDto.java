package com.example.bookshopmanagement.dto;

import com.example.bookshopmanagement.enums.BookType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {

    @NotNull(message = "ISBN can't be null")
    @NotEmpty(message = "ISBN can't be null")
    private String isbn;

    @NotNull
    private BigDecimal discountRate;


//    @NotNull
//    private String bookName;
//
//    @NotNull
//    private BookType bookType;
//
//    @NotNull
//    private String author;
//
//    @NotNull
//    private BigDecimal price;
}
