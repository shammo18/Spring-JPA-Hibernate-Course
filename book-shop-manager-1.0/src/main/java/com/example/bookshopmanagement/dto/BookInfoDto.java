package com.example.bookshopmanagement.dto;

import com.example.bookshopmanagement.enums.BookType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookInfoDto {

    @NotNull(message = "ISBN can't be null")
    @NotEmpty(message = "ISBN can't be null")
    private String isbn;

    @NotNull
    private String bookName;

    @NotNull
    private BookType bookType;

    @NotNull
    private String author;

    @NotNull
    private BigDecimal price;
}
