package com.example.bookshopmanagement.entity;

import com.example.bookshopmanagement.enums.BookType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "BOOK_V2")
public class BookEntity extends BaseDomain{
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "DISCOUNT_RATE",nullable = false)
    private BigDecimal discountRate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "SHOP_NO", nullable = false)
    @JsonBackReference
    private ShopEntity shopEntity;

}
