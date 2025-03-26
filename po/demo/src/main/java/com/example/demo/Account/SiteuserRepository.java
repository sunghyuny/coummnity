package com.example.demo.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SiteuserRepository extends JpaRepository<Siteuser, Long> {
    // 이메일로 사용자를 조회하는 메서드
    Optional<Siteuser> findByEmail(String email);
}
