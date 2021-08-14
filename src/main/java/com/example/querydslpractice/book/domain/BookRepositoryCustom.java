package com.example.querydslpractice.book.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookRepositoryCustom {

    Page<Book> searchbytitlelike(String title, Pageable pageable);

    Page<Book> searchbytitleandCategory(String title,BookCategory bookCategory, Pageable pageable);

    Page<Book> searchbytitleandpriceloe(String title,Integer price, Pageable pageable);

    Page<Book> searchbytitleandpricegoe(String title,Integer price, Pageable pageable);

    Page<Book> searchbytitleandpricebetween(String title,Integer low,Integer high, Pageable pageable);

    Page<Book> searchpricebetween(Integer low,Integer high, Pageable pageable);

    Page<Book> searchpricebetweencategory(BookCategory bookCategory,Integer low,Integer high, Pageable pageable);

}
