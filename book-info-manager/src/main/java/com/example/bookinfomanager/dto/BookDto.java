package com.example.bookinfomanager.dto;

import com.example.bookinfomanager.enums.BookType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.beans.JavaBean;
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
    private String bookName;

    @NotNull
    private BookType bookType;

    @NotNull
    private String author;

    @NotNull
    private BigDecimal price;
}
