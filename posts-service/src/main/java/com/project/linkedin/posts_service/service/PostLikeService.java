package com.project.linkedin.posts_service.service;

import org.springframework.stereotype.Service;

@Service
public interface PostLikeService {

    void likePost(Long postId, Long userId);

}
