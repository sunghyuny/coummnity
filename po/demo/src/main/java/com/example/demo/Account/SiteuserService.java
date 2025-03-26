package com.example.demo.Account;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SiteuserService {
    private final SiteuserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Siteuser create(String username, String email, String password, Integer age, UserRole role) {
        Siteuser user = new Siteuser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));  // 비밀번호 암호화
        user.setAge(age);

        return userRepository.save(user);
    }
}

