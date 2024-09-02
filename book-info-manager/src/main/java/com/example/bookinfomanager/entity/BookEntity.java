package com.example.bookinfomanager.entity;

import com.example.bookinfomanager.enums.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "BOOK_V2")
public class BookEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_TYPE", nullable = false)
    private BookType bookType;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Version
    private Long version;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "UPDATED")
    private Date updated;

    @PrePersist
    public void onCreate(){
        this.created = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = new Date();
    }
}
