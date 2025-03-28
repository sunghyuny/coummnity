package com.example.demo.Post;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryService categoryService;
    private final PostService postService; // final 필드로 선언

    // 생성자에서 초기화
    public CategoryInitializer(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService; // 의존성 주입
    }

    @Override
    @Transactional
    public void run(String... args) {
        postService.updatePostCounts();
        System.out.println("✅ 모든 카테고리의 postCount가 업데이트되었습니다.");
    }
}
