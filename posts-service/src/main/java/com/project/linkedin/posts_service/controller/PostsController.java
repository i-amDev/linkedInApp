package com.project.linkedin.posts_service.controller;

import com.project.linkedin.posts_service.dto.PostCreateRequestDto;
import com.project.linkedin.posts_service.dto.PostDto;
import com.project.linkedin.posts_service.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/createPost")
    public ResponseEntity<PostDto> createPost (@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest) {
        PostDto createdPost = postsService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/getPost/{postId}")
    public ResponseEntity<PostDto> getPostById (@PathVariable Long postId) {
        PostDto postDto = postsService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/getAllPosts/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser (@PathVariable Long userId) {
        List<PostDto> posts = postsService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }

}
