package com.example.bookshopmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SHOP")
public class ShopEntity extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SHOP_NO", nullable = false)
    private String shopNo;

    @Column(name = "ADDRESS",nullable = false)
    private String address;

    @Column(name = "HOTLINE", nullable = false)
    private String hotline;


    @OneToMany(mappedBy = "shopEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    private List<BookEntity> bookEntities;
}
