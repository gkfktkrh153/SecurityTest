package com.example.securitytest.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long member_id;

    @Column
    private String username;

    @Column
    private String password;
}
