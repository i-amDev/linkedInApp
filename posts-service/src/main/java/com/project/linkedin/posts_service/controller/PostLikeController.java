package com.project.linkedin.posts_service.controller;

import com.project.linkedin.posts_service.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/likePost/{postId}")
    public ResponseEntity<Void> likePost (@PathVariable Long postId) {
        postLikeService.likePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unlikePost/{postId}")
    public ResponseEntity<Void> unlikePost (@PathVariable Long postId) {
        postLikeService.unlikePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }


}
