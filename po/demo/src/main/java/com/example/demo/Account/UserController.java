package com.example.demo.Account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller 
@RequestMapping("/user")
public class UserController {
    private final SiteuserService uService;
    private final PasswordEncoder passwordEncoder;

 // 회원가입 폼 화면
 @GetMapping("/signup")
 public String signup(Model model) {
     model.addAttribute("CreateUser", new CreateUser());  // 폼을 위한 빈 객체 전달
     return "Account/Register";  // 회원가입 폼 페이지 리턴
 }

 // 회원가입 처리
 @PostMapping("/signup")
 public String signup(@Validated CreateUser createUser, BindingResult bindingResult) {
     // 유효성 검사 오류가 있으면 다시 폼을 보여줌
     if (bindingResult.hasErrors()) {
         return "Account/Register";
     }

     // 비밀번호 확인이 일치하지 않으면 오류 처리
     if (!createUser.getPassword1().equals(createUser.getPassword2())) {
         bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
         return "Account/Register";  // 비밀번호 불일치 시 다시 폼을 보여줌
     }

     // 패스워드 암호화
     String encodedPassword = passwordEncoder.encode(createUser.getPassword1());

     // role을 기본적으로 'USER'로 설정 (또는 UI에서 'ADMIN', 'MANAGER'를 설정할 수 있음)
     UserRole userRole = UserRole.USER;   // 기본적으로 'USER'로 설정

     // 회원 생성 처리
     uService.create(createUser.getUsername(),  // 닉네임
                     createUser.getEmail(),     // 로그인용 이메일
                     encodedPassword,          // 암호화된 비밀번호
                     createUser.getAge(),       // 나이
                     userRole);                // 기본 권한 'USER'

     return "redirect:/";  // 회원가입 후 홈페이지로 리디렉션
 }
    @GetMapping("/login")
    public String Login(@RequestParam(value = "error", required = false) String error, Model model) {
        if(error != null) {
            model.addAttribute("error", "로그인 실패");
        }
        return "Account/Login";
    }
    
    @ModelAttribute
    public void addUserToModel(@AuthenticationPrincipal Siteuser siteuser, Model model) {
        if (siteuser != null) {
            model.addAttribute("Siteuser", siteuser);  // 로그인된 사용자 정보 추가
        }
    }
}