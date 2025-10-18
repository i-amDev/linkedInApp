package com.project.linkedin.posts_service.service;

import com.project.linkedin.posts_service.dto.PostCreateRequestDto;
import com.project.linkedin.posts_service.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostsService {

    PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsOfUser(Long userId);
}
