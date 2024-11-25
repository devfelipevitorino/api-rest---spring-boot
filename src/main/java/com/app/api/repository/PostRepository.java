package com.app.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.api.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
