package com.example.demo.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.demo.Account.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurity implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserSecurity.class);
    private final SiteuserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // 패스워드 인코더 추가

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("🔍 로그인 요청 - 입력된 이메일: {}", email);

        Optional<Siteuser> _siteUser = this.userRepository.findByEmail(email);
        if (_siteUser.isEmpty()) {
            logger.error("❌ 로그인 실패 - 이메일 '{}'을 가진 사용자를 찾을 수 없음", email);
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        Siteuser siteUser = _siteUser.get();
        logger.info("✅ 사용자 찾음: 이메일={}, 패스워드 해시값={}", siteUser.getEmail(), siteUser.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin@example.com".equals(email)) { // 이메일 기준으로 관리자 설정
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));  // 'ADMIN' 권한 추가
            logger.info("🛠️ 관리자 권한 부여됨");
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));   // 'USER' 권한 추가
            logger.info("👤 일반 사용자 권한 부여됨");
        }

        return new User(siteUser.getEmail(), siteUser.getPassword(), authorities);
    }
}
