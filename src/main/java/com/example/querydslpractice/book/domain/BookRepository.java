package com.example.querydslpractice.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>,BookRepositoryCustom {
}
