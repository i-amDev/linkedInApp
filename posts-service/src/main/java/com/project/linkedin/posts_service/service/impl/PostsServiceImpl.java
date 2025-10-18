package com.project.linkedin.posts_service.service.impl;

import com.project.linkedin.posts_service.dto.PostCreateRequestDto;
import com.project.linkedin.posts_service.dto.PostDto;
import com.project.linkedin.posts_service.entity.PostEntity;
import com.project.linkedin.posts_service.exception.ResourceNotFoundException;
import com.project.linkedin.posts_service.repository.PostsRepository;
import com.project.linkedin.posts_service.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final ModelMapper modelMapper;

    /**
     * @param postCreateRequestDto
     * @return
     */
    @Override
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        PostEntity entity = modelMapper.map(postCreateRequestDto, PostEntity.class);
        entity.setUserId(userId);

        PostEntity savedEntity = postsRepository.save(entity);
        return modelMapper.map(savedEntity, PostDto.class);
    }

    /**
     * @param postId
     * @return
     */
    @Override
    public PostDto getPostById(Long postId) {
        log.debug("Retrieving post with id : {}", postId);
        PostEntity postEntity = postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        return modelMapper.map(postEntity, PostDto.class);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<PostEntity> posts = postsRepository.findByUserId(userId);
        return posts.stream().
                map((postEntity) -> modelMapper.map(postEntity, PostDto.class)).collect(Collectors.toList());
    }

}
