package com.example.demo.Post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    // 카테고리 생성 페이지 (GET)
    @GetMapping("/new")
    public String showCategoryForm() {
        return "Post/CategoryCreate"; // 카테고리 생성 폼을 보여줌
    }

    // 카테고리 생성 처리 (POST)
    @PostMapping("/new") // 여기서 "/create" 대신 "/new"로 변경
    public String createCategory(@RequestParam String name) {
        categoryService.createCategory(name); // 카테고리 생성
        return "redirect:/categories/list"; // 생성 후 목록 페이지로 이동
    }
}
