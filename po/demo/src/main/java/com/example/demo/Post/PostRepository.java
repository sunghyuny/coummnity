package com.example.demo.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 카테고리 기준으로 게시글을 조회하는 메서드 추가
    List<Post> findByCategory(Category category);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.category.categoryId = :categoryId")
    int countPostsByCategory(@Param("categoryId") Long categoryId);
}


