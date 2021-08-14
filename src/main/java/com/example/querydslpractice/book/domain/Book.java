package com.example.querydslpractice.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int price;
    private BookCategory category;

    public Book(String title, String author, String isbn, String publisher, int price, BookCategory category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.price = price;
        this.category = category;
    }
}
