package com.example.demo.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 카테고리 기준으로 게시글을 조회하는 메서드 추가
    List<Post> findByCategory(Category category);
}
