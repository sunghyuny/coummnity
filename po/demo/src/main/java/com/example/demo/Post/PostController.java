package com.example.demo.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    // 게시판 목록 페이지 - 카테고리 목록 조회
    @GetMapping("/post/list")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "Post/Category"; // 카테고리 목록을 보여주는 페이지
    }

    // 게시글 작성 페이지로 이동
    @GetMapping("/post/write")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());  // 빈 Post 객체를 전달하여 폼에 데이터 바인딩
        model.addAttribute("categories", categoryService.getAllCategories());  // 카테고리 목록을 폼에 전달
        return "Post/PostCreate";  // write.html로 이동
    }

    // 게시글 작성 후 저장
    @PreAuthorize("hasRole('USER')")  // 사용자만 게시글을 작성할 수 있도록 설정
    @PostMapping("/post/write")
    public String savePost(@ModelAttribute Post post, @RequestParam Long categoryId, Principal principal) {
        String username = principal.getName();  // 로그인한 사용자의 이름을 가져옴
        post.setUsername(username);

        // 카테고리 설정
        Category category = categoryService.getCategoryById(categoryId);
        post.setCategory(category);

        // 게시글 저장
        postService.savePost(post);

        return "redirect:/post/" + categoryId;  // 게시글 저장 후 해당 카테고리 게시글 목록으로 리디렉션
    }

    // 카테고리별 게시글 목록 조회
    @GetMapping("/post/{categoryId}")
    public String getPostsByCategory(@PathVariable Long categoryId, Model model) {
        // 해당 카테고리의 게시글 목록을 가져옴
        model.addAttribute("posts", postService.getPostsByCategory(categoryId));
        
        // 해당 카테고리 정보도 가져옴 (선택사항)
        model.addAttribute("category", categoryService.getCategoryById(categoryId));

        return "Post/Postlist"; // 카테고리별 게시글 목록을 보여주는 페이지
    }

    // 게시글 상세 조회
    @GetMapping("/post/detail/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "Post/detail";
    }

    // 게시글 수정 페이지
    @GetMapping("/post/edit/{postId}")
    public String editPostForm(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "post/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/post/update/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestParam String postName, 
                             @RequestParam String content) {
        postService.updatePost(postId, postName, content);
        return "redirect:/post/list";
    }

    // 게시글 삭제 처리
    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/post/list";
    }
}
