package com.example.querydslpractice.member.domain;

import com.example.querydslpractice.book.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;
    private String nickname;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public Member(Long id, String username, int age, String nickname, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.nickname = nickname;
        this.password = password;
        this.role=MemberRole.Role_manager;
    }


}
