package com.example.demo.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // 관리자 대시보드 페이지
    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin/dashboard"; // admin/dashboard.html 페이지로 이동
    }
}
