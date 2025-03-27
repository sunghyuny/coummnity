package com.example.demo.Main;

import com.example.demo.Post.CategoryService;
import com.example.demo.Post.Category;
import com.example.demo.Post.Post;
import com.example.demo.Post.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PostService postService; 
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String Mainpage(Model model) {
        // 최근 포스트 목록 가져오기
        List<Post> posts = postService.getAllPosts();  
        model.addAttribute("posts", posts); // 모델에 포스트 목록 추가

        // 카테고리 목록 가져오기
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories); // 모델에 카테고리 목록 추가

        return "mainpage";  
    }
}

