package com.example.demo.Post;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    // 카테고리에 속한 모든 게시글 조회
    public List<Post> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return postRepository.findByCategory(category);
    }

    // 게시글 생성
    public Post createPost(Long categoryId, String postName, String username, String content) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Post post = new Post();
        post.setPostName(postName);
        post.setUsername(username);
        post.setContent(content);
        post.setCategory(category);

        // 카테고리의 게시글 수 증가
        category.setPostCount(category.getPostCount() + 1);
        categoryRepository.save(category);

        return postRepository.save(post);
    }

    // 게시글 수정
    public Post updatePost(Long postId, String postName, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setPostName(postName);
        post.setContent(content);
        return postRepository.save(post);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }
    // 게시글 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 카테고리의 게시글 수 감소
        Category category = post.getCategory();
        category.setPostCount(category.getPostCount() - 1);
        categoryRepository.save(category);

        postRepository.delete(post);
    }

    // 전체 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 상세 조회
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
