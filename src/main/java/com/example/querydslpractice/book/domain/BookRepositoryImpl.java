package com.example.querydslpractice.book.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.querydslpractice.book.domain.QBook.*;


public class BookRepositoryImpl implements BookRepositoryCustom{

    @Autowired
    EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public BookRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory =new JPAQueryFactory(em);
    }

    @Override
    public Page<Book> searchbytitlelike(String title, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(titlecontain(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchbytitleandCategory(String title, BookCategory bookCategory, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(titlecontain(title), categoryeq(bookCategory))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchbytitleandpriceloe(String title, Integer price, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(priceloe(price),titlecontain(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchbytitleandpricegoe(String title, Integer price, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(pricegoe(price),titlecontain(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchbytitleandpricebetween(String title, Integer low, Integer high, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(pricebetween(low,high),titlecontain(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchpricebetween(Integer low, Integer high, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(pricebetween(low, high))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchpricebetweencategory(BookCategory bookCategory, Integer low, Integer high, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(pricebetween(low, high),categoryeq(bookCategory))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    public BooleanExpression titlecontain(String title){
        return title != null ? book.title.contains(title) : null;
    }
    public BooleanExpression categoryeq(BookCategory bookCategory){
        return bookCategory!=null ? book.category.eq(bookCategory) : null;
    }
    public BooleanExpression priceloe(Integer price){
        return price != null ? book.price.loe(price) : null;
    }
    public BooleanExpression pricegoe(Integer price){
        return price != null ? book.price.goe(price) : null;
    }
    public BooleanExpression pricebetween(Integer low,Integer high){
        if(low==null||high==null) return null;
        return book.price.between(low,high);
    }
}
