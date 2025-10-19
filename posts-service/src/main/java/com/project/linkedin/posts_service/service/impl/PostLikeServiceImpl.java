package com.project.linkedin.posts_service.service.impl;

import com.project.linkedin.posts_service.entity.PostLikeEntity;
import com.project.linkedin.posts_service.exception.BadRequestException;
import com.project.linkedin.posts_service.exception.ResourceNotFoundException;
import com.project.linkedin.posts_service.repository.PostLikeRepository;
import com.project.linkedin.posts_service.repository.PostsRepository;
import com.project.linkedin.posts_service.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;

    private final PostsRepository postsRepository;

    /**
     * @param postId
     * @param userId
     */
    @Override
    public void likePost(Long postId, Long userId) {
        log.info("Attempting to like the post with id : {}", postId);

        boolean exist = postsRepository.existsById(postId);
        if (!exist) throw new ResourceNotFoundException("Post not found with id " + postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) throw new BadRequestException("Cannot like the same post again.");

        PostLikeEntity entity = new PostLikeEntity();
        entity.setPostId(postId);
        entity.setUserId(userId);
        postLikeRepository.save(entity);

        log.info("Post with id : {} liked successfully", postId);
    }

    /**
     * @param postId
     * @param userId
     */
    @Override
    public void unlikePost(Long postId, Long userId) {
        log.info("Attempting to unlike the post with id : {}", postId);

        boolean exist = postsRepository.existsById(postId);
        if (!exist) throw new ResourceNotFoundException("Post not found with id " + postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (!alreadyLiked) throw new BadRequestException("Cannot unlike the post which is not liked yet.");

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);

        log.info("Post with id : {} unliked successfully", postId);

    }
}
