package com.example.querydslpractice.book.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.example.querydslpractice.book.domain.QBook.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookTest {

    @Autowired
    BookRepository bookRepository;

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;
    @BeforeEach
    void init(){
        jpaQueryFactory=new JPAQueryFactory(em);
        bookRepository.save(new Book("일반화학","고태경","BDC3123AE","한빛",35000,BookCategory.education));
        bookRepository.save(new Book("현대물리학","김진표","BEW3563AQ","메가스터디",42000,BookCategory.education));
        bookRepository.save(new Book("수학의정석","홍성대","ATC3120VH","성지",18000,BookCategory.education));
        bookRepository.save(new Book("니체의말","니체","TPR8493RE","성지",32000,BookCategory.novel));
        bookRepository.save(new Book("청소력","가이크오쇼헤이","FLD3153AY","진화",25000,BookCategory.novel));
        bookRepository.save(new Book("아프니까청춘이다","고태경","GBN3156HV","진화",30000,BookCategory.novel));
        bookRepository.save(new Book("이외수시집","이외수","MFL0203HN","한빛",17000,BookCategory.poetry));
        bookRepository.save(new Book("해변의카프카","카프카","VMO3944NE","초우",19000,BookCategory.novel));
        bookRepository.save(new Book("회귀분석","김진경","LBO0473AH","초록",23000,BookCategory.education));
        bookRepository.save(new Book("맥심7월","박준상","MFC89423AE","맥심",20000,BookCategory.magazine));
        bookRepository.save(new Book("남성잡지","이천수","MAC3193GE","남잡",8000,BookCategory.magazine));
        bookRepository.save(new Book("도종환시집","도종환","PLC0115RE","초록",15000,BookCategory.poetry));
        bookRepository.save(new Book("VOGUE","한정수","BMV0481TE","맥심",12000,BookCategory.magazine));
        bookRepository.save(new Book("이육사시집","이육사","TPR0051RH","초록",12000,BookCategory.poetry));
    }

    @Test
    public void BasicTest(){
        List<Book> fetch = jpaQueryFactory.selectFrom(book)
                .fetch();
        for (Book fetch1 : fetch) {
            System.out.println(fetch1.getCategory());
        }
    }

    @Test
    public void searchByTitleLikeTest(){
        List<Book> poetry = jpaQueryFactory.selectFrom(book)
                .where(book.title.contains("시집"))
                .fetch();
        for (Book book1 : poetry) {
            System.out.println(book1.getCategory());
        }
    }
    @Test
    public void bookrepositorysearchTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> poe = bookRepository.searchbytitlelike(null, pageRequest);
        List<Book> content = poe.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getTitle());
        }
        System.out.println(poe.getTotalElements());
    }


    @Test
    public void bookrepositorysearchtitlecategoryTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> books = bookRepository.searchbytitleandCategory("심", BookCategory.magazine, pageRequest);
        List<Book> content = books.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getTitle());
        }
    }

    @Test
    public void bookrepositorypricebetween(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> searchpricebetween = bookRepository.searchpricebetween(12000, 21000, pageRequest);

        List<Book> content = searchpricebetween.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getTitle());
        }

    }
    @Test
    public void bookrepositorytitlepriceloe(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> searchpricebetween = bookRepository.searchbytitleandpriceloe("시집",16000,pageRequest);

        List<Book> content = searchpricebetween.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getTitle());
        }

    }
    @Test
    public void bookrepositorytitlepricegoe(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> searchpricebetween = bookRepository.searchbytitleandpricegoe("시집", 13000, pageRequest);

        List<Book> content = searchpricebetween.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getTitle());
        }

    }
    @Test
    public void bookrepositorypricebetweencategory(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> searchpricebetween = bookRepository.searchpricebetweencategory(BookCategory.education,20000,42000,pageRequest);

        List<Book> content = searchpricebetween.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getTitle());
        }

    }

}