package com.example.demo.Account;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUser {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;  // 사용자ID

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;
    
    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @Max(value = 100, message = "나이는 100세 이하여야 합니다.")
    @Min(value = 13, message = "나이는 13세 이상이여야 합니다.")
    @NotNull(message = "나이는 필수항목입니다.")
    private Integer age;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;  // 이메일 필드 추가

    // 역할을 지정할 수 있도록 추가 (기본값: USER)
    private String role = "USER"; // 기본값은 'USER'로 설정
}
