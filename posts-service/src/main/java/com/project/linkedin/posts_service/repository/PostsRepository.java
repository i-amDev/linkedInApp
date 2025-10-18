package com.project.linkedin.posts_service.repository;

import com.project.linkedin.posts_service.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByUserId(Long userId);
}
