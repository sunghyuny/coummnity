package com.example.demo.Account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Siteuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;  // 닉네임

    @Column(unique = true)
    private String email;  // 이메일 (로그인용)

    private String password;
    private Integer age;

    
    // @Column(nullable = false)
    // private Integer role = 1;  // 기본값은 1 (유저)

}
